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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements implicit Adams-Moulton integrators for Ordinary
 * Differential Equations.
 *
 * <p>Adams-Moulton methods (in fact due to Adams alone) are implicit
 * multistep ODE solvers. This implementation is a variation of the classical
 * one: it uses adaptive stepsize to implement error control, whereas
 * classical implementations are fixed step size. The value of state vector
 * at step n+1 is a simple combination of the value at step n and of the
 * derivatives at steps n+1, n, n-1 ... Since y'<sub>n+1</sub> is needed to
 * compute y<sub>n+1</sub>, another method must be used to compute a first
 * estimate of y<sub>n+1</sub>, then compute y'<sub>n+1</sub>, then compute
 * a final estimate of y<sub>n+1</sub> using the following formulas. Depending
 * on the number k of previous steps one wants to use for computing the next
 * value, different formulas are available for the final estimate:</p>
 * <ul>
 *   <li>k = 1: y<sub>n+1</sub> = y<sub>n</sub> + h y'<sub>n+1</sub></li>
 *   <li>k = 2: y<sub>n+1</sub> = y<sub>n</sub> + h (y'<sub>n+1</sub>+y'<sub>n</sub>)/2</li>
 *   <li>k = 3: y<sub>n+1</sub> = y<sub>n</sub> + h (5y'<sub>n+1</sub>+8y'<sub>n</sub>-y'<sub>n-1</sub>)/12</li>
 *   <li>k = 4: y<sub>n+1</sub> = y<sub>n</sub> + h (9y'<sub>n+1</sub>+19y'<sub>n</sub>-5y'<sub>n-1</sub>+y'<sub>n-2</sub>)/24</li>
 *   <li>...</li>
 * </ul>
 *
 * <p>A k-steps Adams-Moulton method is of order k+1.</p>
 *
 * <h3>Implementation details</h3>
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
 * <p>The definitions above use the classical representation with several previous first
 * derivatives. Lets define
 * <pre>
 *   q<sub>n</sub> = [ s<sub>1</sub>(n-1) s<sub>1</sub>(n-2) ... s<sub>1</sub>(n-(k-1)) ]<sup>T</sup>
 * </pre>
 * (we omit the k index in the notation for clarity). With these definitions,
 * Adams-Moulton methods can be written:
 * <ul>
 *   <li>k = 1: y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n+1)</li>
 *   <li>k = 2: y<sub>n+1</sub> = y<sub>n</sub> + 1/2 s<sub>1</sub>(n+1) + [ 1/2 ] q<sub>n+1</sub></li>
 *   <li>k = 3: y<sub>n+1</sub> = y<sub>n</sub> + 5/12 s<sub>1</sub>(n+1) + [ 8/12 -1/12 ] q<sub>n+1</sub></li>
 *   <li>k = 4: y<sub>n+1</sub> = y<sub>n</sub> + 9/24 s<sub>1</sub>(n+1) + [ 19/24 -5/24 1/24 ] q<sub>n+1</sub></li>
 *   <li>...</li>
 * </ul></p>
 *
 * <p>Instead of using the classical representation with first derivatives only (y<sub>n</sub>,
 * s<sub>1</sub>(n+1) and q<sub>n+1</sub>), our implementation uses the Nordsieck vector with
 * higher degrees scaled derivatives all taken at the same step (y<sub>n</sub>, s<sub>1</sub>(n)
 * and r<sub>n</sub>) where r<sub>n</sub> is defined as:
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
 * classical representation and Nordsieck vector. The transform between r<sub>n</sub>
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
 * <p>Using the Nordsieck vector has several advantages:
 * <ul>
 *   <li>it greatly simplifies step interpolation as the interpolator mainly applies
 *   Taylor series formulas,</li>
 *   <li>it simplifies step changes that occur when discrete events that truncate
 *   the step are triggered,</li>
 *   <li>it allows to extend the methods in order to support adaptive stepsize.</li>
 * </ul></p>
 *
 * <p>The predicted Nordsieck vector at step n+1 is computed from the Nordsieck vector at step
 * n as follows:
 * <ul>
 *   <li>Y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n) + u<sup>T</sup> r<sub>n</sub></li>
 *   <li>S<sub>1</sub>(n+1) = h f(t<sub>n+1</sub>, Y<sub>n+1</sub>)</li>
 *   <li>R<sub>n+1</sub> = (s<sub>1</sub>(n) - S<sub>1</sub>(n+1)) P<sup>-1</sup> u + P<sup>-1</sup> A P r<sub>n</sub></li>
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
 * </pre>
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
 * <p>The P<sup>-1</sup>u vector and the P<sup>-1</sup> A P matrix do not depend on the state,
 * they only depend on k and therefore are precomputed once for all.</p>
 *
 * @since 2.0
 */
public class AdamsMoultonIntegrator extends AdamsIntegrator {

    @Conditional
    public static boolean _mut19060 = false, _mut19061 = false, _mut19062 = false, _mut19063 = false, _mut19064 = false, _mut19065 = false, _mut19066 = false, _mut19067 = false, _mut19068 = false, _mut19069 = false, _mut19070 = false, _mut19071 = false, _mut19072 = false, _mut19073 = false, _mut19074 = false, _mut19075 = false, _mut19076 = false, _mut19077 = false, _mut19078 = false, _mut19079 = false, _mut19080 = false, _mut19081 = false, _mut19082 = false, _mut19083 = false, _mut19084 = false, _mut19085 = false, _mut19086 = false, _mut19087 = false, _mut19088 = false, _mut19089 = false, _mut19090 = false, _mut19091 = false, _mut19092 = false, _mut19093 = false, _mut19094 = false, _mut19095 = false, _mut19096 = false, _mut19097 = false, _mut19098 = false, _mut19099 = false, _mut19100 = false, _mut19101 = false, _mut19102 = false, _mut19103 = false, _mut19104 = false, _mut19105 = false, _mut19106 = false, _mut19107 = false, _mut19108 = false, _mut19109 = false, _mut19110 = false, _mut19111 = false, _mut19112 = false, _mut19113 = false, _mut19114 = false, _mut19115 = false, _mut19116 = false, _mut19117 = false, _mut19118 = false, _mut19119 = false, _mut19120 = false, _mut19121 = false, _mut19122 = false, _mut19123 = false, _mut19124 = false, _mut19125 = false, _mut19126 = false, _mut19127 = false, _mut19128 = false, _mut19129 = false, _mut19130 = false, _mut19131 = false, _mut19132 = false, _mut19133 = false, _mut19134 = false, _mut19135 = false, _mut19136 = false, _mut19137 = false, _mut19138 = false, _mut19139 = false, _mut19140 = false, _mut19141 = false, _mut19142 = false, _mut19143 = false, _mut19144 = false, _mut19145 = false, _mut19146 = false, _mut19147 = false, _mut19148 = false, _mut19149 = false, _mut19150 = false, _mut19151 = false, _mut19152 = false, _mut19153 = false, _mut19154 = false, _mut19155 = false, _mut19156 = false, _mut19157 = false, _mut19158 = false, _mut19159 = false, _mut19160 = false, _mut19161 = false, _mut19162 = false, _mut19163 = false, _mut19164 = false, _mut19165 = false, _mut19166 = false, _mut19167 = false, _mut19168 = false, _mut19169 = false, _mut19170 = false, _mut19171 = false, _mut19172 = false, _mut19173 = false, _mut19174 = false, _mut19175 = false, _mut19176 = false, _mut19177 = false, _mut19178 = false, _mut19179 = false, _mut19180 = false, _mut19181 = false, _mut19182 = false, _mut19183 = false, _mut19184 = false, _mut19185 = false, _mut19186 = false, _mut19187 = false, _mut19188 = false, _mut19189 = false, _mut19190 = false, _mut19191 = false, _mut19192 = false, _mut19193 = false, _mut19194 = false, _mut19195 = false, _mut19196 = false, _mut19197 = false, _mut19198 = false, _mut19199 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Adams-Moulton";

    /**
     * Build an Adams-Moulton integrator with the given order and error control parameters.
     * @param nSteps number of steps of the method excluding the one being computed
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     * @exception NumberIsTooSmallException if order is 1 or less
     */
    public AdamsMoultonIntegrator(final int nSteps, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(METHOD_NAME, nSteps, AOR_plus(nSteps, 1, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.AdamsMoultonIntegrator_177", _mut19060, _mut19061, _mut19062, _mut19063), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.AdamsMoultonIntegrator_177");
    }

    /**
     * Build an Adams-Moulton integrator with the given order and error control parameters.
     * @param nSteps number of steps of the method excluding the one being computed
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     * @exception IllegalArgumentException if order is 1 or less
     */
    public AdamsMoultonIntegrator(final int nSteps, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(METHOD_NAME, nSteps, AOR_plus(nSteps, 1, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.AdamsMoultonIntegrator_199", _mut19064, _mut19065, _mut19066, _mut19067), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.AdamsMoultonIntegrator_199");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(final ExpandableStatefulODE equations, final double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
        sanityChecks(equations, t);
        setEquations(equations);
        final boolean forward = ROR_greater(t, equations.getTime(), "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19068, _mut19069, _mut19070, _mut19071, _mut19072);
        // initialize working arrays
        final double[] y0 = equations.getCompleteState();
        final double[] y = y0.clone();
        final double[] yDot = new double[y.length];
        final double[] yTmp = new double[y.length];
        final double[] predictedScaled = new double[y.length];
        Array2DRowRealMatrix nordsieckTmp = null;
        // set up two interpolators sharing the integrator arrays
        final NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
        interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        // set up integration control objects
        initIntegration(equations.getTime(), y0, t);
        // compute the initial Nordsieck vector using the configured starter integrator
        start(equations.getTime(), y, t);
        interpolator.reinitialize(stepStart, stepSize, scaled, nordsieck);
        interpolator.storeTime(stepStart);
        double hNew = stepSize;
        interpolator.rescale(hNew);
        isLastStep = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
            double error = 10;
            while (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19095, _mut19096, _mut19097, _mut19098, _mut19099)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
                stepSize = hNew;
                // predict a first estimate of the state at step end (P in the PECE sequence)
                final double stepEnd = AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19073, _mut19074, _mut19075, _mut19076);
                interpolator.setInterpolatedTime(stepEnd);
                final ExpandableStatefulODE expandable = getExpandable();
                final EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), yTmp);
                int index = 0;
                for (final EquationsMapper secondary : expandable.getSecondaryMappers()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), yTmp);
                    ++index;
                }
                // evaluate a first estimate of the derivative (first E in the PECE sequence)
                computeDerivatives(stepEnd, yTmp, yDot);
                // update Nordsieck vector
                for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19081, _mut19082, _mut19083, _mut19084, _mut19085); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
                    predictedScaled[j] = AOR_multiply(stepSize, yDot[j], "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19077, _mut19078, _mut19079, _mut19080);
                }
                nordsieckTmp = updateHighOrderDerivativesPhase1(nordsieck);
                updateHighOrderDerivativesPhase2(scaled, predictedScaled, nordsieckTmp);
                // apply correction (C in the PECE sequence)
                error = nordsieckTmp.walkInOptimizedOrder(new Corrector(y, predictedScaled, yTmp));
                if (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19086, _mut19087, _mut19088, _mut19089, _mut19090)) {
                    // reject the step and attempt to reduce error by stepsize control
                    final double factor = computeStepGrowShrinkFactor(error);
                    hNew = filterStep(AOR_multiply(stepSize, factor, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19091, _mut19092, _mut19093, _mut19094), forward, false);
                    interpolator.rescale(hNew);
                }
            }
            // evaluate a final estimate of the derivative (second E in the PECE sequence)
            final double stepEnd = AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19100, _mut19101, _mut19102, _mut19103);
            computeDerivatives(stepEnd, yTmp, yDot);
            // update Nordsieck vector
            final double[] correctedScaled = new double[y0.length];
            for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19108, _mut19109, _mut19110, _mut19111, _mut19112); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209");
                correctedScaled[j] = AOR_multiply(stepSize, yDot[j], "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19104, _mut19105, _mut19106, _mut19107);
            }
            updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, nordsieckTmp);
            // discrete events handling
            System.arraycopy(yTmp, 0, y, 0, y.length);
            interpolator.reinitialize(stepEnd, stepSize, correctedScaled, nordsieckTmp);
            interpolator.storeTime(stepStart);
            interpolator.shift();
            interpolator.storeTime(stepEnd);
            stepStart = acceptStep(interpolator, y, yDot, t);
            scaled = correctedScaled;
            nordsieck = nordsieckTmp;
            if (!isLastStep) {
                // prepare next step
                interpolator.storeTime(stepStart);
                if (resetOccurred) {
                    // invalidate the derivatives, we need to restart from scratch
                    start(stepStart, y, t);
                    interpolator.reinitialize(stepStart, stepSize, scaled, nordsieck);
                }
                // stepsize control for next step
                final double factor = computeStepGrowShrinkFactor(error);
                final double scaledH = AOR_multiply(stepSize, factor, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19113, _mut19114, _mut19115, _mut19116);
                final double nextT = AOR_plus(stepStart, scaledH, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19117, _mut19118, _mut19119, _mut19120);
                final boolean nextIsLast = forward ? (ROR_greater_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19126, _mut19127, _mut19128, _mut19129, _mut19130)) : (ROR_less_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19121, _mut19122, _mut19123, _mut19124, _mut19125));
                hNew = filterStep(scaledH, forward, nextIsLast);
                final double filteredNextT = AOR_plus(stepStart, hNew, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19131, _mut19132, _mut19133, _mut19134);
                final boolean filteredNextIsLast = forward ? (ROR_greater_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19140, _mut19141, _mut19142, _mut19143, _mut19144)) : (ROR_less_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19135, _mut19136, _mut19137, _mut19138, _mut19139));
                if (filteredNextIsLast) {
                    hNew = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.integrate_209", _mut19145, _mut19146, _mut19147, _mut19148);
                }
                interpolator.rescale(hNew);
            }
        } while (!isLastStep);
        // dispatch results
        equations.setTime(stepStart);
        equations.setCompleteState(y);
        resetInternalState();
    }

    /**
     * Corrector for current state in Adams-Moulton method.
     * <p>
     * This visitor implements the Taylor series formula:
     * <pre>
     * Y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n+1) + [ -1 +1 -1 +1 ... &plusmn;1 ] r<sub>n+1</sub>
     * </pre>
     * </p>
     */
    private class Corrector implements RealMatrixPreservingVisitor {

        /**
         * Previous state.
         */
        private final double[] previous;

        /**
         * Current scaled first derivative.
         */
        private final double[] scaled;

        /**
         * Current state before correction.
         */
        private final double[] before;

        /**
         * Current state after correction.
         */
        private final double[] after;

        /**
         * Simple constructor.
         * @param previous previous state
         * @param scaled current scaled first derivative
         * @param state state to correct (will be overwritten after visit)
         */
        Corrector(final double[] previous, final double[] scaled, final double[] state) {
            this.previous = previous;
            this.scaled = scaled;
            this.after = state;
            this.before = state.clone();
        }

        /**
         * {@inheritDoc}
         */
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            Arrays.fill(after, 0.0);
        }

        /**
         * {@inheritDoc}
         */
        public void visit(int row, int column, double value) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.visit_384");
            if (ROR_equals((row & 0x1), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.visit_384", _mut19149, _mut19150, _mut19151, _mut19152, _mut19153)) {
                after[column] -= value;
            } else {
                after[column] += value;
            }
        }

        /**
         * End visiting the Nordsieck vector.
         * <p>The correction is used to control stepsize. So its amplitude is
         * considered to be an error, which must be normalized according to
         * error control settings. If the normalized value is greater than 1,
         * the correction was too large and the step must be rejected.</p>
         * @return the normalized correction, if greater than 1, the step
         * must be rejected
         */
        public double end() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401");
            double error = 0;
            for (int i = 0; ROR_less(i, after.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19191, _mut19192, _mut19193, _mut19194, _mut19195); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401");
                after[i] += AOR_plus(previous[i], scaled[i], "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19154, _mut19155, _mut19156, _mut19157);
                if (ROR_less(i, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19158, _mut19159, _mut19160, _mut19161, _mut19162)) {
                    final double yScale = FastMath.max(FastMath.abs(previous[i]), FastMath.abs(after[i]));
                    final double tol = (vecAbsoluteTolerance == null) ? (AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, yScale, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19171, _mut19172, _mut19173, _mut19174), "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19175, _mut19176, _mut19177, _mut19178)) : (AOR_plus(vecAbsoluteTolerance[i], AOR_multiply(vecRelativeTolerance[i], yScale, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19163, _mut19164, _mut19165, _mut19166), "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19167, _mut19168, _mut19169, _mut19170));
                    // (corrected-predicted)/tol
                    final double ratio = AOR_divide((AOR_minus(after[i], before[i], "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19179, _mut19180, _mut19181, _mut19182)), tol, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19183, _mut19184, _mut19185, _mut19186);
                    error += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19187, _mut19188, _mut19189, _mut19190);
                }
            }
            return FastMath.sqrt(AOR_divide(error, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator.end_401", _mut19196, _mut19197, _mut19198, _mut19199));
        }
    }
}
