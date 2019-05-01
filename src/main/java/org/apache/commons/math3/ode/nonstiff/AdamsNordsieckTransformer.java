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
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayFieldVector;
import org.apache.commons.math3.linear.FieldDecompositionSolver;
import org.apache.commons.math3.linear.FieldLUDecomposition;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
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
 * @since 2.0
 */
public class AdamsNordsieckTransformer {

    @Conditional
    public static boolean _mut17825 = false, _mut17826 = false, _mut17827 = false, _mut17828 = false, _mut17829 = false, _mut17830 = false, _mut17831 = false, _mut17832 = false, _mut17833 = false, _mut17834 = false, _mut17835 = false, _mut17836 = false, _mut17837 = false, _mut17838 = false, _mut17839 = false, _mut17840 = false, _mut17841 = false, _mut17842 = false, _mut17843 = false, _mut17844 = false, _mut17845 = false, _mut17846 = false, _mut17847 = false, _mut17848 = false, _mut17849 = false, _mut17850 = false, _mut17851 = false, _mut17852 = false, _mut17853 = false, _mut17854 = false, _mut17855 = false, _mut17856 = false, _mut17857 = false, _mut17858 = false, _mut17859 = false, _mut17860 = false, _mut17861 = false, _mut17862 = false, _mut17863 = false, _mut17864 = false, _mut17865 = false, _mut17866 = false, _mut17867 = false, _mut17868 = false, _mut17869 = false, _mut17870 = false, _mut17871 = false, _mut17872 = false, _mut17873 = false, _mut17874 = false, _mut17875 = false, _mut17876 = false, _mut17877 = false, _mut17878 = false, _mut17879 = false, _mut17880 = false, _mut17881 = false, _mut17882 = false, _mut17883 = false, _mut17884 = false, _mut17885 = false, _mut17886 = false, _mut17887 = false, _mut17888 = false, _mut17889 = false, _mut17890 = false, _mut17891 = false, _mut17892 = false, _mut17893 = false, _mut17894 = false, _mut17895 = false, _mut17896 = false, _mut17897 = false, _mut17898 = false, _mut17899 = false, _mut17900 = false, _mut17901 = false, _mut17902 = false, _mut17903 = false, _mut17904 = false, _mut17905 = false, _mut17906 = false, _mut17907 = false, _mut17908 = false, _mut17909 = false, _mut17910 = false, _mut17911 = false, _mut17912 = false, _mut17913 = false, _mut17914 = false, _mut17915 = false, _mut17916 = false, _mut17917 = false, _mut17918 = false, _mut17919 = false, _mut17920 = false, _mut17921 = false, _mut17922 = false, _mut17923 = false, _mut17924 = false, _mut17925 = false, _mut17926 = false, _mut17927 = false, _mut17928 = false, _mut17929 = false, _mut17930 = false, _mut17931 = false, _mut17932 = false, _mut17933 = false, _mut17934 = false, _mut17935 = false, _mut17936 = false, _mut17937 = false, _mut17938 = false, _mut17939 = false, _mut17940 = false, _mut17941 = false, _mut17942 = false, _mut17943 = false, _mut17944 = false, _mut17945 = false, _mut17946 = false, _mut17947 = false, _mut17948 = false, _mut17949 = false, _mut17950 = false, _mut17951 = false, _mut17952 = false, _mut17953 = false, _mut17954 = false, _mut17955 = false, _mut17956 = false, _mut17957 = false, _mut17958 = false, _mut17959 = false, _mut17960 = false, _mut17961 = false, _mut17962 = false, _mut17963 = false, _mut17964 = false, _mut17965 = false, _mut17966 = false, _mut17967 = false, _mut17968 = false, _mut17969 = false, _mut17970 = false, _mut17971 = false, _mut17972 = false, _mut17973 = false, _mut17974 = false, _mut17975 = false, _mut17976 = false, _mut17977 = false, _mut17978 = false, _mut17979 = false, _mut17980 = false, _mut17981 = false, _mut17982 = false, _mut17983 = false, _mut17984 = false, _mut17985 = false, _mut17986 = false, _mut17987 = false, _mut17988 = false, _mut17989 = false, _mut17990 = false, _mut17991 = false, _mut17992 = false, _mut17993 = false, _mut17994 = false, _mut17995 = false, _mut17996 = false, _mut17997 = false, _mut17998 = false, _mut17999 = false, _mut18000 = false, _mut18001 = false, _mut18002 = false, _mut18003 = false, _mut18004 = false, _mut18005 = false, _mut18006 = false, _mut18007 = false, _mut18008 = false, _mut18009 = false, _mut18010 = false, _mut18011 = false, _mut18012 = false, _mut18013 = false, _mut18014 = false, _mut18015 = false, _mut18016 = false, _mut18017 = false, _mut18018 = false, _mut18019 = false, _mut18020 = false, _mut18021 = false, _mut18022 = false, _mut18023 = false, _mut18024 = false, _mut18025 = false;

    /**
     * Cache for already computed coefficients.
     */
    private static final Map<Integer, AdamsNordsieckTransformer> CACHE = new HashMap<Integer, AdamsNordsieckTransformer>();

    /**
     * Update matrix for the higher order derivatives h<sup>2</sup>/2 y'', h<sup>3</sup>/6 y''' ...
     */
    private final Array2DRowRealMatrix update;

    /**
     * Update coefficients of the higher order derivatives wrt y'.
     */
    private final double[] c1;

    /**
     * Simple constructor.
     * @param n number of steps of the multistep method
     * (excluding the one being computed)
     */
    private AdamsNordsieckTransformer(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151");
        final int rows = AOR_minus(n, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151", _mut17825, _mut17826, _mut17827, _mut17828);
        // compute exact coefficients
        FieldMatrix<BigFraction> bigP = buildP(rows);
        FieldDecompositionSolver<BigFraction> pSolver = new FieldLUDecomposition<BigFraction>(bigP).getSolver();
        BigFraction[] u = new BigFraction[rows];
        Arrays.fill(u, BigFraction.ONE);
        BigFraction[] bigC1 = pSolver.solve(new ArrayFieldVector<BigFraction>(u, false)).toArray();
        // then applying inverse transform
        BigFraction[][] shiftedP = bigP.getData();
        for (int i = shiftedP.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151", _mut17833, _mut17834, _mut17835, _mut17836, _mut17837); --i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151");
            // shift rows
            shiftedP[i] = shiftedP[AOR_minus(i, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151", _mut17829, _mut17830, _mut17831, _mut17832)];
        }
        shiftedP[0] = new BigFraction[rows];
        Arrays.fill(shiftedP[0], BigFraction.ZERO);
        FieldMatrix<BigFraction> bigMSupdate = pSolver.solve(new Array2DRowFieldMatrix<BigFraction>(shiftedP, false));
        // convert coefficients to double
        update = MatrixUtils.bigFractionMatrixToRealMatrix(bigMSupdate);
        c1 = new double[rows];
        for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151", _mut17838, _mut17839, _mut17840, _mut17841, _mut17842); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.AdamsNordsieckTransformer_151");
            c1[i] = bigC1[i].doubleValue();
        }
    }

    /**
     * Get the Nordsieck transformer for a given number of steps.
     * @param nSteps number of steps of the multistep method
     * (excluding the one being computed)
     * @return Nordsieck transformer for the specified number of steps
     */
    public static AdamsNordsieckTransformer getInstance(final int nSteps) {
        synchronized (CACHE) {
            AdamsNordsieckTransformer t = CACHE.get(nSteps);
            if (t == null) {
                t = new AdamsNordsieckTransformer(nSteps);
                CACHE.put(nSteps, t);
            }
            return t;
        }
    }

    /**
     * Get the number of steps of the method
     * (excluding the one being computed).
     * @return number of steps of the method
     * (excluding the one being computed)
     * @deprecated as of 3.6, this method is not used anymore
     */
    @Deprecated
    public int getNSteps() {
        return c1.length;
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
    private FieldMatrix<BigFraction> buildP(final int rows) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227");
        final BigFraction[][] pData = new BigFraction[rows][rows];
        for (int i = 1; ROR_less_equals(i, pData.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17864, _mut17865, _mut17866, _mut17867, _mut17868); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227");
            // build the P matrix elements from Taylor series formulas
            final BigFraction[] pI = pData[AOR_minus(i, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17843, _mut17844, _mut17845, _mut17846)];
            final int factor = -i;
            int aj = factor;
            for (int j = 1; ROR_less_equals(j, pI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17859, _mut17860, _mut17861, _mut17862, _mut17863); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227");
                pI[AOR_minus(j, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17847, _mut17848, _mut17849, _mut17850)] = new BigFraction(AOR_multiply(aj, (AOR_plus(j, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17851, _mut17852, _mut17853, _mut17854)), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.buildP_227", _mut17855, _mut17856, _mut17857, _mut17858));
                aj *= factor;
            }
        }
        return new Array2DRowFieldMatrix<BigFraction>(pData, false);
    }

    public Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
        // to solve also for the remainder
        final double[][] a = new double[AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17869, _mut17870, _mut17871, _mut17872)][AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17873, _mut17874, _mut17875, _mut17876)];
        final double[][] b = new double[AOR_plus(c1.length, 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17877, _mut17878, _mut17879, _mut17880)][y[0].length];
        final double[] y0 = y[0];
        final double[] yDot0 = yDot[0];
        for (int i = 1; ROR_less(i, y.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17989, _mut17990, _mut17991, _mut17992, _mut17993); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
            final double di = AOR_minus(t[i], t[0], "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17881, _mut17882, _mut17883, _mut17884);
            final double ratio = AOR_divide(di, h, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17885, _mut17886, _mut17887, _mut17888);
            double dikM1Ohk = AOR_divide(1, h, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17889, _mut17890, _mut17891, _mut17892);
            // y(ti) - y(t0) - di y'(t0) and y'(ti) - y'(t0)
            final double[] aI = a[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17893, _mut17894, _mut17895, _mut17896), 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17897, _mut17898, _mut17899, _mut17900)];
            final double[] aDotI = ROR_less((AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17901, _mut17902, _mut17903, _mut17904), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17905, _mut17906, _mut17907, _mut17908)), a.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17909, _mut17910, _mut17911, _mut17912, _mut17913) ? a[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17914, _mut17915, _mut17916, _mut17917), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17918, _mut17919, _mut17920, _mut17921)] : null;
            for (int j = 0; ROR_less(j, aI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17934, _mut17935, _mut17936, _mut17937, _mut17938); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
                dikM1Ohk *= ratio;
                aI[j] = AOR_multiply(di, dikM1Ohk, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17922, _mut17923, _mut17924, _mut17925);
                if (aDotI != null) {
                    aDotI[j] = AOR_multiply((AOR_plus(j, 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17926, _mut17927, _mut17928, _mut17929)), dikM1Ohk, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17930, _mut17931, _mut17932, _mut17933);
                }
            }
            // expected value of the previous equations
            final double[] yI = y[i];
            final double[] yDotI = yDot[i];
            final double[] bI = b[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17939, _mut17940, _mut17941, _mut17942), 2, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17943, _mut17944, _mut17945, _mut17946)];
            final double[] bDotI = ROR_less((AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17947, _mut17948, _mut17949, _mut17950), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17951, _mut17952, _mut17953, _mut17954)), b.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17955, _mut17956, _mut17957, _mut17958, _mut17959) ? b[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17960, _mut17961, _mut17962, _mut17963), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17964, _mut17965, _mut17966, _mut17967)] : null;
            for (int j = 0; ROR_less(j, yI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17984, _mut17985, _mut17986, _mut17987, _mut17988); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
                bI[j] = AOR_minus(AOR_minus(yI[j], y0[j], "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17968, _mut17969, _mut17970, _mut17971), AOR_multiply(di, yDot0[j], "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17972, _mut17973, _mut17974, _mut17975), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17976, _mut17977, _mut17978, _mut17979);
                if (bDotI != null) {
                    bDotI[j] = AOR_minus(yDotI[j], yDot0[j], "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17980, _mut17981, _mut17982, _mut17983);
                }
            }
        }
        // with the additional terms s(k+1) and c grabbing the parts after the truncated Taylor expansion
        final QRDecomposition decomposition = new QRDecomposition(new Array2DRowRealMatrix(a, false));
        final RealMatrix x = decomposition.getSolver().solve(new Array2DRowRealMatrix(b, false));
        // extract just the Nordsieck vector [s2 ... sk]
        final Array2DRowRealMatrix truncatedX = new Array2DRowRealMatrix(AOR_minus(x.getRowDimension(), 1, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17994, _mut17995, _mut17996, _mut17997), x.getColumnDimension());
        for (int i = 0; ROR_less(i, truncatedX.getRowDimension(), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut18003, _mut18004, _mut18005, _mut18006, _mut18007); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
            for (int j = 0; ROR_less(j, truncatedX.getColumnDimension(), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255", _mut17998, _mut17999, _mut18000, _mut18001, _mut18002); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.initializeHighOrderDerivatives_255");
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
     * @see #updateHighOrderDerivativesPhase2(double[], double[], Array2DRowRealMatrix)
     */
    public Array2DRowRealMatrix updateHighOrderDerivativesPhase1(final Array2DRowRealMatrix highOrder) {
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
     * @see #updateHighOrderDerivativesPhase1(Array2DRowRealMatrix)
     */
    public void updateHighOrderDerivativesPhase2(final double[] start, final double[] end, final Array2DRowRealMatrix highOrder) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348");
        final double[][] data = highOrder.getDataRef();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348", _mut18021, _mut18022, _mut18023, _mut18024, _mut18025); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348");
            final double[] dataI = data[i];
            final double c1I = c1[i];
            for (int j = 0; ROR_less(j, dataI.length, "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348", _mut18016, _mut18017, _mut18018, _mut18019, _mut18020); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348");
                dataI[j] += AOR_multiply(c1I, (AOR_minus(start[j], end[j], "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348", _mut18008, _mut18009, _mut18010, _mut18011)), "org.apache.commons.math3.ode.nonstiff.AdamsNordsieckTransformer.updateHighOrderDerivativesPhase2_348", _mut18012, _mut18013, _mut18014, _mut18015);
            }
        }
    }
}
