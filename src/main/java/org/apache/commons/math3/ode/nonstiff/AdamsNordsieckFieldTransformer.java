/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Transformer to Nordsieck vectors for Adams integrators.
 * <p>This class is used by {@link AdamsBashforthIntegrator Adams-Bashforth} and
 * {@link AdamsMoultonIntegrator Adams-Moulton} integrators to convert between
 * classical representation with several previous first derivatives and Nordsieck
 * representation with higher order scaled derivatives.</p>
 *
 * <p>We define scaled derivatives s<sub>i</sub>(n) at step n as:
 * <pre>
 * s<sub>1</sub>(n) = h y'<sub>n</sub> for first derivative
 * s<sub>2</sub>(n) = h<sup>2</sup>/2 y''<sub>n</sub> for second derivative
 * s<sub>3</sub>(n) = h<sup>3</sup>/6 y'''<sub>n</sub> for third derivative
 * ...
 * s<sub>k</sub>(n) = h<sup>k</sup>/k! y<sup>(k)</sup><sub>n</sub> for k<sup>th</sup> derivative
 * </pre></p>
 *
 * <p>With the previous definition, the classical representation of multistep methods
 * uses first derivatives only, i.e. it handles y<sub>n</sub>, s<sub>1</sub>(n) and
 * q<sub>n</sub> where q<sub>n</sub> is defined as:
 * <pre>
 *   q<sub>n</sub> = [ s<sub>1</sub>(n-1) s<sub>1</sub>(n-2) ... s<sub>1</sub>(n-(k-1)) ]<sup>T</sup>
 * </pre>
 * (we omit the k index in the notation for clarity).</p>
 *
 * <p>Another possible representation uses the Nordsieck vector with
 * higher degrees scaled derivatives all taken at the same step, i.e it handles y<sub>n</sub>,
 * s<sub>1</sub>(n) and r<sub>n</sub>) where r<sub>n</sub> is defined as:
 * <pre>
 * r<sub>n</sub> = [ s<sub>2</sub>(n), s<sub>3</sub>(n) ... s<sub>k</sub>(n) ]<sup>T</sup>
 * </pre>
 * (here again we omit the k index in the notation for clarity)
 * </p>
 *
 * <p>Taylor series formulas show that for any index offset i, s<sub>1</sub>(n-i) can be
 * computed from s<sub>1</sub>(n), s<sub>2</sub>(n) ... s<sub>k</sub>(n), the formula being exact
 * for degree k polynomials.
 * <pre>
 * s<sub>1</sub>(n-i) = s<sub>1</sub>(n) + &sum;<sub>j&gt;0</sub> (j+1) (-i)<sup>j</sup> s<sub>j+1</sub>(n)
 * </pre>
 * The previous formula can be used with several values for i to compute the transform between
 * classical representation and Nordsieck vector at step end. The transform between r<sub>n</sub>
 * and q<sub>n</sub> resulting from the Taylor series formulas above is:
 * <pre>
 * q<sub>n</sub> = s<sub>1</sub>(n) u + P r<sub>n</sub>
 * </pre>
 * where u is the [ 1 1 ... 1 ]<sup>T</sup> vector and P is the (k-1)&times;(k-1) matrix built
 * with the (j+1) (-i)<sup>j</sup> terms with i being the row number starting from 1 and j being
 * the column number starting from 1:
 * <pre>
 *        [  -2   3   -4    5  ... ]
 *        [  -4  12  -32   80  ... ]
 *   P =  [  -6  27 -108  405  ... ]
 *        [  -8  48 -256 1280  ... ]
 *        [          ...           ]
 * </pre></p>
 *
 * <p>Changing -i into +i in the formula above can be used to compute a similar transform between
 * classical representation and Nordsieck vector at step start. The resulting matrix is simply
 * the absolute value of matrix P.</p>
 *
 * <p>For {@link AdamsBashforthIntegrator Adams-Bashforth} method, the Nordsieck vector
 * at step n+1 is computed from the Nordsieck vector at step n as follows:
 * <ul>
 *   <li>y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n) + u<sup>T</sup> r<sub>n</sub></li>
 *   <li>s<sub>1</sub>(n+1) = h f(t<sub>n+1</sub>, y<sub>n+1</sub>)</li>
 *   <li>r<sub>n+1</sub> = (s<sub>1</sub>(n) - s<sub>1</sub>(n+1)) P<sup>-1</sup> u + P<sup>-1</sup> A P r<sub>n</sub></li>
 * </ul>
 * where A is a rows shifting matrix (the lower left part is an identity matrix):
 * <pre>
 *        [ 0 0   ...  0 0 | 0 ]
 *        [ ---------------+---]
 *        [ 1 0   ...  0 0 | 0 ]
 *    A = [ 0 1   ...  0 0 | 0 ]
 *        [       ...      | 0 ]
 *        [ 0 0   ...  1 0 | 0 ]
 *        [ 0 0   ...  0 1 | 0 ]
 * </pre></p>
 *
 * <p>For {@link AdamsMoultonIntegrator Adams-Moulton} method, the predicted Nordsieck vector
 * at step n+1 is computed from the Nordsieck vector at step n as follows:
 * <ul>
 *   <li>Y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n) + u<sup>T</sup> r<sub>n</sub></li>
 *   <li>S<sub>1</sub>(n+1) = h f(t<sub>n+1</sub>, Y<sub>n+1</sub>)</li>
 *   <li>R<sub>n+1</sub> = (s<sub>1</sub>(n) - s<sub>1</sub>(n+1)) P<sup>-1</sup> u + P<sup>-1</sup> A P r<sub>n</sub></li>
 * </ul>
 * From this predicted vector, the corrected vector is computed as follows:
 * <ul>
 *   <li>y<sub>n+1</sub> = y<sub>n</sub> + S<sub>1</sub>(n+1) + [ -1 +1 -1 +1 ... &plusmn;1 ] r<sub>n+1</sub></li>
 *   <li>s<sub>1</sub>(n+1) = h f(t<sub>n+1</sub>, y<sub>n+1</sub>)</li>
 *   <li>r<sub>n+1</sub> = R<sub>n+1</sub> + (s<sub>1</sub>(n+1) - S<sub>1</sub>(n+1)) P<sup>-1</sup> u</li>
 * </ul>
 * where the upper case Y<sub>n+1</sub>, S<sub>1</sub>(n+1) and R<sub>n+1</sub> represent the
 * predicted states whereas the lower case y<sub>n+1</sub>, s<sub>n+1</sub> and r<sub>n+1</sub>
 * represent the corrected states.</p>
 *
 * <p>We observe that both methods use similar update formulas. In both cases a P<sup>-1</sup>u
 * vector and a P<sup>-1</sup> A P matrix are used that do not depend on the state,
 * they only depend on k. This class handles these transformations.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class AdamsNordsieckFieldTransformer<T extends RealFieldElement<T>> {

    @Conditional
    public static boolean _mut13549 = false, _mut13550 = false, _mut13551 = false, _mut13552 = false, _mut13553 = false, _mut13554 = false, _mut13555 = false, _mut13556 = false, _mut13557 = false, _mut13558 = false, _mut13559 = false, _mut13560 = false, _mut13561 = false, _mut13562 = false, _mut13563 = false, _mut13564 = false, _mut13565 = false, _mut13566 = false, _mut13567 = false, _mut13568 = false, _mut13569 = false, _mut13570 = false, _mut13571 = false, _mut13572 = false, _mut13573 = false, _mut13574 = false, _mut13575 = false, _mut13576 = false, _mut13577 = false, _mut13578 = false, _mut13579 = false, _mut13580 = false, _mut13581 = false, _mut13582 = false, _mut13583 = false, _mut13584 = false, _mut13585 = false, _mut13586 = false, _mut13587 = false, _mut13588 = false, _mut13589 = false, _mut13590 = false, _mut13591 = false, _mut13592 = false, _mut13593 = false, _mut13594 = false, _mut13595 = false, _mut13596 = false, _mut13597 = false, _mut13598 = false, _mut13599 = false, _mut13600 = false, _mut13601 = false, _mut13602 = false, _mut13603 = false, _mut13604 = false, _mut13605 = false, _mut13606 = false, _mut13607 = false, _mut13608 = false, _mut13609 = false, _mut13610 = false, _mut13611 = false, _mut13612 = false, _mut13613 = false, _mut13614 = false, _mut13615 = false, _mut13616 = false, _mut13617 = false, _mut13618 = false, _mut13619 = false, _mut13620 = false, _mut13621 = false, _mut13622 = false, _mut13623 = false, _mut13624 = false, _mut13625 = false, _mut13626 = false, _mut13627 = false, _mut13628 = false, _mut13629 = false, _mut13630 = false, _mut13631 = false, _mut13632 = false, _mut13633 = false, _mut13634 = false, _mut13635 = false, _mut13636 = false, _mut13637 = false, _mut13638 = false, _mut13639 = false, _mut13640 = false, _mut13641 = false, _mut13642 = false, _mut13643 = false, _mut13644 = false, _mut13645 = false, _mut13646 = false, _mut13647 = false, _mut13648 = false, _mut13649 = false, _mut13650 = false, _mut13651 = false, _mut13652 = false, _mut13653 = false, _mut13654 = false, _mut13655 = false, _mut13656 = false, _mut13657 = false, _mut13658 = false, _mut13659 = false, _mut13660 = false, _mut13661 = false, _mut13662 = false, _mut13663 = false, _mut13664 = false, _mut13665 = false, _mut13666 = false, _mut13667 = false, _mut13668 = false, _mut13669 = false, _mut13670 = false, _mut13671 = false, _mut13672 = false, _mut13673 = false, _mut13674 = false, _mut13675 = false, _mut13676 = false, _mut13677 = false, _mut13678 = false, _mut13679 = false, _mut13680 = false, _mut13681 = false, _mut13682 = false, _mut13683 = false, _mut13684 = false, _mut13685 = false, _mut13686 = false, _mut13687 = false, _mut13688 = false, _mut13689 = false, _mut13690 = false, _mut13691 = false, _mut13692 = false, _mut13693 = false, _mut13694 = false, _mut13695 = false, _mut13696 = false;

    /**
     * Cache for already computed coefficients.
     */
    private static final Map<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>> CACHE = new HashMap<Integer, Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>>();

    /**
     * Field to which the time and state vector elements belong.
     */
    private final Field<T> field;

    /**
     * Update matrix for the higher order derivatives h<sup>2</sup>/2 y'', h<sup>3</sup>/6 y''' ...
     */
    private final Array2DRowFieldMatrix<T> update;

    /**
     * Update coefficients of the higher order derivatives wrt y'.
     */
    private final T[] c1;

    /**
     * Simple constructor.
     * @param field field to which the time and state vector elements belong
     * @param n number of steps of the multistep method
     * (excluding the one being computed)
     */
    private AdamsNordsieckFieldTransformer(final Field<T> field, final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.AdamsNordsieckFieldTransformer_157");
        this.field = field;
        final int rows = AOR_minus(n, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.AdamsNordsieckFieldTransformer_157", _mut13549, _mut13550, _mut13551, _mut13552);
        // compute coefficients
        FieldMatrix<T> bigP = buildP(rows);
        FieldDecompositionSolver<T> pSolver = new FieldLUDecomposition<T>(bigP).getSolver();
        T[] u = MathArrays.buildArray(field, rows);
        Arrays.fill(u, field.getOne());
        c1 = pSolver.solve(new ArrayFieldVector<T>(u, false)).toArray();
        // then applying inverse transform
        T[][] shiftedP = bigP.getData();
        for (int i = shiftedP.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.AdamsNordsieckFieldTransformer_157", _mut13557, _mut13558, _mut13559, _mut13560, _mut13561); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.AdamsNordsieckFieldTransformer_157");
            // shift rows
            shiftedP[i] = shiftedP[AOR_minus(i, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.AdamsNordsieckFieldTransformer_157", _mut13553, _mut13554, _mut13555, _mut13556)];
        }
        shiftedP[0] = MathArrays.buildArray(field, rows);
        Arrays.fill(shiftedP[0], field.getZero());
        update = new Array2DRowFieldMatrix<T>(pSolver.solve(new Array2DRowFieldMatrix<T>(shiftedP, false)).getData());
    }

    /**
     * Get the Nordsieck transformer for a given field and number of steps.
     * @param field field to which the time and state vector elements belong
     * @param nSteps number of steps of the multistep method
     * (excluding the one being computed)
     * @return Nordsieck transformer for the specified field and number of steps
     * @param <T> the type of the field elements
     */
    @SuppressWarnings("unchecked")
    public static <T extends RealFieldElement<T>> AdamsNordsieckFieldTransformer<T> getInstance(final Field<T> field, final int nSteps) {
        synchronized (CACHE) {
            Map<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>> map = CACHE.get(nSteps);
            if (map == null) {
                map = new HashMap<Field<? extends RealFieldElement<?>>, AdamsNordsieckFieldTransformer<? extends RealFieldElement<?>>>();
                CACHE.put(nSteps, map);
            }
            // use rawtype to avoid compilation problems with java 1.5
            @SuppressWarnings("rawtypes")
            AdamsNordsieckFieldTransformer t = map.get(field);
            if (t == null) {
                t = new AdamsNordsieckFieldTransformer<T>(field, nSteps);
                map.put(field, (AdamsNordsieckFieldTransformer<T>) t);
            }
            return (AdamsNordsieckFieldTransformer<T>) t;
        }
    }

    /**
     * Build the P matrix.
     * <p>The P matrix general terms are shifted (j+1) (-i)<sup>j</sup> terms
     * with i being the row number starting from 1 and j being the column
     * number starting from 1:
     * <pre>
     *        [  -2   3   -4    5  ... ]
     *        [  -4  12  -32   80  ... ]
     *   P =  [  -6  27 -108  405  ... ]
     *        [  -8  48 -256 1280  ... ]
     *        [          ...           ]
     * </pre></p>
     * @param rows number of rows of the matrix
     * @return P matrix
     */
    private FieldMatrix<T> buildP(final int rows) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228");
        final T[][] pData = MathArrays.buildArray(field, rows, rows);
        for (int i = 1; ROR_less_equals(i, pData.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228", _mut13579, _mut13580, _mut13581, _mut13582, _mut13583); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228");
            // build the P matrix elements from Taylor series formulas
            final T[] pI = pData[AOR_minus(i, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228", _mut13562, _mut13563, _mut13564, _mut13565)];
            final int factor = -i;
            T aj = field.getZero().add(factor);
            for (int j = 1; ROR_less_equals(j, pI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228", _mut13574, _mut13575, _mut13576, _mut13577, _mut13578); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228");
                pI[AOR_minus(j, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228", _mut13566, _mut13567, _mut13568, _mut13569)] = aj.multiply(AOR_plus(j, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.buildP_228", _mut13570, _mut13571, _mut13572, _mut13573));
                aj = aj.multiply(factor);
            }
        }
        return new Array2DRowFieldMatrix<T>(pData, false);
    }

    public Array2DRowFieldMatrix<T> initializeHighOrderDerivatives(final T h, final T[] t, final T[][] y, final T[][] yDot) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
        // to solve also for the remainder
        final T[][] a = MathArrays.buildArray(field, AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13584, _mut13585, _mut13586, _mut13587), AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13588, _mut13589, _mut13590, _mut13591));
        final T[][] b = MathArrays.buildArray(field, AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13592, _mut13593, _mut13594, _mut13595), y[0].length);
        final T[] y0 = y[0];
        final T[] yDot0 = yDot[0];
        for (int i = 1; ROR_less(i, y.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13668, _mut13669, _mut13670, _mut13671, _mut13672); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
            final T di = t[i].subtract(t[0]);
            final T ratio = di.divide(h);
            T dikM1Ohk = h.reciprocal();
            // y(ti) - y(t0) - di y'(t0) and y'(ti) - y'(t0)
            final T[] aI = a[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13596, _mut13597, _mut13598, _mut13599), 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13600, _mut13601, _mut13602, _mut13603)];
            final T[] aDotI = ROR_less((AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13604, _mut13605, _mut13606, _mut13607), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13608, _mut13609, _mut13610, _mut13611)), a.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13612, _mut13613, _mut13614, _mut13615, _mut13616) ? a[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13617, _mut13618, _mut13619, _mut13620), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13621, _mut13622, _mut13623, _mut13624)] : null;
            for (int j = 0; ROR_less(j, aI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13629, _mut13630, _mut13631, _mut13632, _mut13633); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
                dikM1Ohk = dikM1Ohk.multiply(ratio);
                aI[j] = di.multiply(dikM1Ohk);
                if (aDotI != null) {
                    aDotI[j] = dikM1Ohk.multiply(AOR_plus(j, 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13625, _mut13626, _mut13627, _mut13628));
                }
            }
            // expected value of the previous equations
            final T[] yI = y[i];
            final T[] yDotI = yDot[i];
            final T[] bI = b[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13634, _mut13635, _mut13636, _mut13637), 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13638, _mut13639, _mut13640, _mut13641)];
            final T[] bDotI = ROR_less((AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13642, _mut13643, _mut13644, _mut13645), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13646, _mut13647, _mut13648, _mut13649)), b.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13650, _mut13651, _mut13652, _mut13653, _mut13654) ? b[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13655, _mut13656, _mut13657, _mut13658), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13659, _mut13660, _mut13661, _mut13662)] : null;
            for (int j = 0; ROR_less(j, yI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13663, _mut13664, _mut13665, _mut13666, _mut13667); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
                bI[j] = yI[j].subtract(y0[j]).subtract(di.multiply(yDot0[j]));
                if (bDotI != null) {
                    bDotI[j] = yDotI[j].subtract(yDot0[j]);
                }
            }
        }
        // with the additional terms s(k+1) and c grabbing the parts after the truncated Taylor expansion
        final FieldLUDecomposition<T> decomposition = new FieldLUDecomposition<T>(new Array2DRowFieldMatrix<T>(a, false));
        final FieldMatrix<T> x = decomposition.getSolver().solve(new Array2DRowFieldMatrix<T>(b, false));
        // extract just the Nordsieck vector [s2 ... sk]
        final Array2DRowFieldMatrix<T> truncatedX = new Array2DRowFieldMatrix<T>(field, AOR_minus(x.getRowDimension(), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13673, _mut13674, _mut13675, _mut13676), x.getColumnDimension());
        for (int i = 0; ROR_less(i, truncatedX.getRowDimension(), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13682, _mut13683, _mut13684, _mut13685, _mut13686); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
            for (int j = 0; ROR_less(j, truncatedX.getColumnDimension(), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256", _mut13677, _mut13678, _mut13679, _mut13680, _mut13681); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.initializeHighOrderDerivatives_256");
                truncatedX.setEntry(i, j, x.getEntry(i, j));
            }
        }
        return truncatedX;
    }

    /**
     * Update the high order scaled derivatives for Adams integrators (phase 1).
     * <p>The complete update of high order derivatives has a form similar to:
     * <pre>
     * r<sub>n+1</sub> = (s<sub>1</sub>(n) - s<sub>1</sub>(n+1)) P<sup>-1</sup> u + P<sup>-1</sup> A P r<sub>n</sub>
     * </pre>
     * this method computes the P<sup>-1</sup> A P r<sub>n</sub> part.</p>
     * @param highOrder high order scaled derivatives
     * (h<sup>2</sup>/2 y'', ... h<sup>k</sup>/k! y(k))
     * @return updated high order derivatives
     * @see #updateHighOrderDerivativesPhase2(RealFieldElement[], RealFieldElement[], Array2DRowFieldMatrix)
     */
    public Array2DRowFieldMatrix<T> updateHighOrderDerivativesPhase1(final Array2DRowFieldMatrix<T> highOrder) {
        return update.multiply(highOrder);
    }

    /**
     * Update the high order scaled derivatives Adams integrators (phase 2).
     * <p>The complete update of high order derivatives has a form similar to:
     * <pre>
     * r<sub>n+1</sub> = (s<sub>1</sub>(n) - s<sub>1</sub>(n+1)) P<sup>-1</sup> u + P<sup>-1</sup> A P r<sub>n</sub>
     * </pre>
     * this method computes the (s<sub>1</sub>(n) - s<sub>1</sub>(n+1)) P<sup>-1</sup> u part.</p>
     * <p>Phase 1 of the update must already have been performed.</p>
     * @param start first order scaled derivatives at step start
     * @param end first order scaled derivatives at step end
     * @param highOrder high order scaled derivatives, will be modified
     * (h<sup>2</sup>/2 y'', ... h<sup>k</sup>/k! y(k))
     * @see #updateHighOrderDerivativesPhase1(Array2DRowFieldMatrix)
     */
    public void updateHighOrderDerivativesPhase2(final T[] start, final T[] end, final Array2DRowFieldMatrix<T> highOrder) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.updateHighOrderDerivativesPhase2_350");
        final T[][] data = highOrder.getDataRef();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.updateHighOrderDerivativesPhase2_350", _mut13692, _mut13693, _mut13694, _mut13695, _mut13696); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.updateHighOrderDerivativesPhase2_350");
            final T[] dataI = data[i];
            final T c1I = c1[i];
            for (int j = 0; ROR_less(j, dataI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.updateHighOrderDerivativesPhase2_350", _mut13687, _mut13688, _mut13689, _mut13690, _mut13691); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckFieldTransformer.updateHighOrderDerivativesPhase2_350");
                dataI[j] = dataI[j].add(c1I.multiply(start[j].subtract(end[j])));
            }
        }
    }
}
