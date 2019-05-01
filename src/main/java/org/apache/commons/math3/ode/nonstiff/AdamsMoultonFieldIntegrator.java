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
import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrixPreservingVisitor;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
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
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class AdamsMoultonFieldIntegrator<T extends RealFieldElement<T>> extends AdamsFieldIntegrator<T> {

    @Conditional
    public static boolean _mut17153 = false, _mut17154 = false, _mut17155 = false, _mut17156 = false, _mut17157 = false, _mut17158 = false, _mut17159 = false, _mut17160 = false, _mut17161 = false, _mut17162 = false, _mut17163 = false, _mut17164 = false, _mut17165 = false, _mut17166 = false, _mut17167 = false, _mut17168 = false, _mut17169 = false, _mut17170 = false, _mut17171 = false, _mut17172 = false, _mut17173 = false, _mut17174 = false, _mut17175 = false, _mut17176 = false, _mut17177 = false, _mut17178 = false, _mut17179 = false, _mut17180 = false, _mut17181 = false, _mut17182 = false, _mut17183 = false, _mut17184 = false, _mut17185 = false, _mut17186 = false, _mut17187 = false, _mut17188 = false, _mut17189 = false, _mut17190 = false, _mut17191 = false, _mut17192 = false, _mut17193 = false, _mut17194 = false, _mut17195 = false, _mut17196 = false, _mut17197 = false, _mut17198 = false, _mut17199 = false, _mut17200 = false, _mut17201 = false, _mut17202 = false, _mut17203 = false, _mut17204 = false, _mut17205 = false, _mut17206 = false, _mut17207 = false, _mut17208 = false, _mut17209 = false, _mut17210 = false, _mut17211 = false, _mut17212 = false, _mut17213 = false, _mut17214 = false, _mut17215 = false, _mut17216 = false, _mut17217 = false, _mut17218 = false, _mut17219 = false, _mut17220 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Adams-Moulton";

    /**
     * Build an Adams-Moulton integrator with the given order and error control parameters.
     * @param field field to which the time and state vector elements belong
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
    public AdamsMoultonFieldIntegrator(final Field<T> field, final int nSteps, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(field, METHOD_NAME, nSteps, AOR_plus(nSteps, 1, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.AdamsMoultonFieldIntegrator_182", _mut17153, _mut17154, _mut17155, _mut17156), minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.AdamsMoultonFieldIntegrator_182");
    }

    /**
     * Build an Adams-Moulton integrator with the given order and error control parameters.
     * @param field field to which the time and state vector elements belong
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
    public AdamsMoultonFieldIntegrator(final Field<T> field, final int nSteps, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(field, METHOD_NAME, nSteps, AOR_plus(nSteps, 1, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.AdamsMoultonFieldIntegrator_205", _mut17157, _mut17158, _mut17159, _mut17160), minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.AdamsMoultonFieldIntegrator_205");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FieldODEStateAndDerivative<T> integrate(final FieldExpandableODE<T> equations, final FieldODEState<T> initialState, final T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215");
        sanityChecks(initialState, finalTime);
        final T t0 = initialState.getTime();
        final T[] y = equations.getMapper().mapState(initialState);
        setStepStart(initIntegration(equations, t0, y, finalTime));
        final boolean forward = ROR_greater(finalTime.subtract(initialState.getTime()).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17161, _mut17162, _mut17163, _mut17164, _mut17165);
        // compute the initial Nordsieck vector using the configured starter integrator
        start(equations, getStepStart(), finalTime);
        // reuse the step that was chosen by the starter integrator
        FieldODEStateAndDerivative<T> stepStart = getStepStart();
        FieldODEStateAndDerivative<T> stepEnd = AdamsFieldStepInterpolator.taylor(stepStart, stepStart.getTime().add(getStepSize()), getStepSize(), scaled, nordsieck);
        // main integration loop
        setIsLastStep(false);
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215");
            T[] predictedY = null;
            final T[] predictedScaled = MathArrays.buildArray(getField(), y.length);
            Array2DRowFieldMatrix<T> predictedNordsieck = null;
            T error = getField().getZero().add(10);
            while (ROR_greater_equals(error.subtract(1.0).getReal(), 0.0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17176, _mut17177, _mut17178, _mut17179, _mut17180)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215");
                // predict a first estimate of the state at step end (P in the PECE sequence)
                predictedY = stepEnd.getState();
                // evaluate a first estimate of the derivative (first E in the PECE sequence)
                final T[] yDot = computeDerivatives(stepEnd.getTime(), predictedY);
                // update Nordsieck vector
                for (int j = 0; ROR_less(j, predictedScaled.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17166, _mut17167, _mut17168, _mut17169, _mut17170); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215");
                    predictedScaled[j] = getStepSize().multiply(yDot[j]);
                }
                predictedNordsieck = updateHighOrderDerivativesPhase1(nordsieck);
                updateHighOrderDerivativesPhase2(scaled, predictedScaled, predictedNordsieck);
                // apply correction (C in the PECE sequence)
                error = predictedNordsieck.walkInOptimizedOrder(new Corrector(y, predictedScaled, predictedY));
                if (ROR_greater_equals(error.subtract(1.0).getReal(), 0.0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17171, _mut17172, _mut17173, _mut17174, _mut17175)) {
                    // reject the step and attempt to reduce error by stepsize control
                    final T factor = computeStepGrowShrinkFactor(error);
                    rescale(filterStep(getStepSize().multiply(factor), forward, false));
                    stepEnd = AdamsFieldStepInterpolator.taylor(getStepStart(), getStepStart().getTime().add(getStepSize()), getStepSize(), scaled, nordsieck);
                }
            }
            // evaluate a final estimate of the derivative (second E in the PECE sequence)
            final T[] correctedYDot = computeDerivatives(stepEnd.getTime(), predictedY);
            // update Nordsieck vector
            final T[] correctedScaled = MathArrays.buildArray(getField(), y.length);
            for (int j = 0; ROR_less(j, correctedScaled.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17181, _mut17182, _mut17183, _mut17184, _mut17185); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215");
                correctedScaled[j] = getStepSize().multiply(correctedYDot[j]);
            }
            updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, predictedNordsieck);
            // discrete events handling
            stepEnd = new FieldODEStateAndDerivative<T>(stepEnd.getTime(), predictedY, correctedYDot);
            setStepStart(acceptStep(new AdamsFieldStepInterpolator<T>(getStepSize(), stepEnd, correctedScaled, predictedNordsieck, forward, getStepStart(), stepEnd, equations.getMapper()), finalTime));
            scaled = correctedScaled;
            nordsieck = predictedNordsieck;
            if (!isLastStep()) {
                System.arraycopy(predictedY, 0, y, 0, y.length);
                if (resetOccurred()) {
                    // invalidate the derivatives, we need to restart from scratch
                    start(equations, getStepStart(), finalTime);
                }
                // stepsize control for next step
                final T factor = computeStepGrowShrinkFactor(error);
                final T scaledH = getStepSize().multiply(factor);
                final T nextT = getStepStart().getTime().add(scaledH);
                final boolean nextIsLast = forward ? ROR_greater_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17191, _mut17192, _mut17193, _mut17194, _mut17195) : ROR_less_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17186, _mut17187, _mut17188, _mut17189, _mut17190);
                T hNew = filterStep(scaledH, forward, nextIsLast);
                final T filteredNextT = getStepStart().getTime().add(hNew);
                final boolean filteredNextIsLast = forward ? ROR_greater_equals(filteredNextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17201, _mut17202, _mut17203, _mut17204, _mut17205) : ROR_less_equals(filteredNextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.integrate_215", _mut17196, _mut17197, _mut17198, _mut17199, _mut17200);
                if (filteredNextIsLast) {
                    hNew = finalTime.subtract(getStepStart().getTime());
                }
                rescale(hNew);
                stepEnd = AdamsFieldStepInterpolator.taylor(getStepStart(), getStepStart().getTime().add(getStepSize()), getStepSize(), scaled, nordsieck);
            }
        } while (!isLastStep());
        final FieldODEStateAndDerivative<T> finalState = getStepStart();
        setStepStart(null);
        setStepSize(null);
        return finalState;
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
    private class Corrector implements FieldMatrixPreservingVisitor<T> {

        /**
         * Previous state.
         */
        private final T[] previous;

        /**
         * Current scaled first derivative.
         */
        private final T[] scaled;

        /**
         * Current state before correction.
         */
        private final T[] before;

        /**
         * Current state after correction.
         */
        private final T[] after;

        /**
         * Simple constructor.
         * @param previous previous state
         * @param scaled current scaled first derivative
         * @param state state to correct (will be overwritten after visit)
         */
        Corrector(final T[] previous, final T[] scaled, final T[] state) {
            this.previous = previous;
            this.scaled = scaled;
            this.after = state;
            this.before = state.clone();
        }

        /**
         * {@inheritDoc}
         */
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            Arrays.fill(after, getField().getZero());
        }

        /**
         * {@inheritDoc}
         */
        public void visit(int row, int column, T value) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.visit_379");
            if (ROR_equals((row & 0x1), 0, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.visit_379", _mut17206, _mut17207, _mut17208, _mut17209, _mut17210)) {
                after[column] = after[column].subtract(value);
            } else {
                after[column] = after[column].add(value);
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
        public T end() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.end_396");
            T error = getField().getZero();
            for (int i = 0; ROR_less(i, after.length, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.end_396", _mut17216, _mut17217, _mut17218, _mut17219, _mut17220); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.end_396");
                after[i] = after[i].add(previous[i].add(scaled[i]));
                if (ROR_less(i, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdamsMoultonFieldIntegrator.end_396", _mut17211, _mut17212, _mut17213, _mut17214, _mut17215)) {
                    final T yScale = MathUtils.max(previous[i].abs(), after[i].abs());
                    final T tol = (vecAbsoluteTolerance == null) ? yScale.multiply(scalRelativeTolerance).add(scalAbsoluteTolerance) : yScale.multiply(vecRelativeTolerance[i]).add(vecAbsoluteTolerance[i]);
                    // (corrected-predicted)/tol
                    final T ratio = after[i].subtract(before[i]).divide(tol);
                    error = error.add(ratio.multiply(ratio));
                }
            }
            return error.divide(mainSetDimension).sqrt();
        }
    }
}
