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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements explicit Adams-Bashforth integrators for Ordinary
 * Differential Equations.
 *
 * <p>Adams-Bashforth methods (in fact due to Adams alone) are explicit
 * multistep ODE solvers. This implementation is a variation of the classical
 * one: it uses adaptive stepsize to implement error control, whereas
 * classical implementations are fixed step size. The value of state vector
 * at step n+1 is a simple combination of the value at step n and of the
 * derivatives at steps n, n-1, n-2 ... Depending on the number k of previous
 * steps one wants to use for computing the next value, different formulas
 * are available:</p>
 * <ul>
 *   <li>k = 1: y<sub>n+1</sub> = y<sub>n</sub> + h y'<sub>n</sub></li>
 *   <li>k = 2: y<sub>n+1</sub> = y<sub>n</sub> + h (3y'<sub>n</sub>-y'<sub>n-1</sub>)/2</li>
 *   <li>k = 3: y<sub>n+1</sub> = y<sub>n</sub> + h (23y'<sub>n</sub>-16y'<sub>n-1</sub>+5y'<sub>n-2</sub>)/12</li>
 *   <li>k = 4: y<sub>n+1</sub> = y<sub>n</sub> + h (55y'<sub>n</sub>-59y'<sub>n-1</sub>+37y'<sub>n-2</sub>-9y'<sub>n-3</sub>)/24</li>
 *   <li>...</li>
 * </ul>
 *
 * <p>A k-steps Adams-Bashforth method is of order k.</p>
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
 * Adams-Bashforth methods can be written:
 * <ul>
 *   <li>k = 1: y<sub>n+1</sub> = y<sub>n</sub> + s<sub>1</sub>(n)</li>
 *   <li>k = 2: y<sub>n+1</sub> = y<sub>n</sub> + 3/2 s<sub>1</sub>(n) + [ -1/2 ] q<sub>n</sub></li>
 *   <li>k = 3: y<sub>n+1</sub> = y<sub>n</sub> + 23/12 s<sub>1</sub>(n) + [ -16/12 5/12 ] q<sub>n</sub></li>
 *   <li>k = 4: y<sub>n+1</sub> = y<sub>n</sub> + 55/24 s<sub>1</sub>(n) + [ -59/24 37/24 -9/24 ] q<sub>n</sub></li>
 *   <li>...</li>
 * </ul></p>
 *
 * <p>Instead of using the classical representation with first derivatives only (y<sub>n</sub>,
 * s<sub>1</sub>(n) and q<sub>n</sub>), our implementation uses the Nordsieck vector with
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
 * <p>The Nordsieck vector at step n+1 is computed from the Nordsieck vector at step n as follows:
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
 * <p>The P<sup>-1</sup>u vector and the P<sup>-1</sup> A P matrix do not depend on the state,
 * they only depend on k and therefore are precomputed once for all.</p>
 *
 * @since 2.0
 */
public class AdamsBashforthIntegrator extends AdamsIntegrator {

    @Conditional
    public static boolean _mut17694 = false, _mut17695 = false, _mut17696 = false, _mut17697 = false, _mut17698 = false, _mut17699 = false, _mut17700 = false, _mut17701 = false, _mut17702 = false, _mut17703 = false, _mut17704 = false, _mut17705 = false, _mut17706 = false, _mut17707 = false, _mut17708 = false, _mut17709 = false, _mut17710 = false, _mut17711 = false, _mut17712 = false, _mut17713 = false, _mut17714 = false, _mut17715 = false, _mut17716 = false, _mut17717 = false, _mut17718 = false, _mut17719 = false, _mut17720 = false, _mut17721 = false, _mut17722 = false, _mut17723 = false, _mut17724 = false, _mut17725 = false, _mut17726 = false, _mut17727 = false, _mut17728 = false, _mut17729 = false, _mut17730 = false, _mut17731 = false, _mut17732 = false, _mut17733 = false, _mut17734 = false, _mut17735 = false, _mut17736 = false, _mut17737 = false, _mut17738 = false, _mut17739 = false, _mut17740 = false, _mut17741 = false, _mut17742 = false, _mut17743 = false, _mut17744 = false, _mut17745 = false, _mut17746 = false, _mut17747 = false, _mut17748 = false, _mut17749 = false, _mut17750 = false, _mut17751 = false, _mut17752 = false, _mut17753 = false, _mut17754 = false, _mut17755 = false, _mut17756 = false, _mut17757 = false, _mut17758 = false, _mut17759 = false, _mut17760 = false, _mut17761 = false, _mut17762 = false, _mut17763 = false, _mut17764 = false, _mut17765 = false, _mut17766 = false, _mut17767 = false, _mut17768 = false, _mut17769 = false, _mut17770 = false, _mut17771 = false, _mut17772 = false, _mut17773 = false, _mut17774 = false, _mut17775 = false, _mut17776 = false, _mut17777 = false, _mut17778 = false, _mut17779 = false, _mut17780 = false, _mut17781 = false, _mut17782 = false, _mut17783 = false, _mut17784 = false, _mut17785 = false, _mut17786 = false, _mut17787 = false, _mut17788 = false, _mut17789 = false, _mut17790 = false, _mut17791 = false, _mut17792 = false, _mut17793 = false, _mut17794 = false, _mut17795 = false, _mut17796 = false, _mut17797 = false, _mut17798 = false, _mut17799 = false, _mut17800 = false, _mut17801 = false, _mut17802 = false, _mut17803 = false, _mut17804 = false, _mut17805 = false, _mut17806 = false, _mut17807 = false, _mut17808 = false, _mut17809 = false, _mut17810 = false, _mut17811 = false, _mut17812 = false, _mut17813 = false, _mut17814 = false, _mut17815 = false, _mut17816 = false, _mut17817 = false, _mut17818 = false, _mut17819 = false, _mut17820 = false, _mut17821 = false, _mut17822 = false, _mut17823 = false, _mut17824 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Adams-Bashforth";

    /**
     * Build an Adams-Bashforth integrator with the given order and step control parameters.
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
    public AdamsBashforthIntegrator(final int nSteps, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(METHOD_NAME, nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    /**
     * Build an Adams-Bashforth integrator with the given order and step control parameters.
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
    public AdamsBashforthIntegrator(final int nSteps, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(METHOD_NAME, nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    /**
     * Estimate error.
     * <p>
     * Error is estimated by interpolating back to previous state using
     * the state Taylor expansion and comparing to real previous state.
     * </p>
     * @param previousState state vector at step start
     * @param predictedState predicted state vector at step end
     * @param predictedScaled predicted value of the scaled derivatives at step end
     * @param predictedNordsieck predicted value of the Nordsieck vector at step end
     * @return estimated normalized local discretization error
     */
    private double errorEstimation(final double[] previousState, final double[] predictedState, final double[] predictedScaled, final RealMatrix predictedNordsieck) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204");
        double error = 0;
        for (int i = 0; ROR_less(i, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17744, _mut17745, _mut17746, _mut17747, _mut17748); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204");
            final double yScale = FastMath.abs(predictedState[i]);
            final double tol = (vecAbsoluteTolerance == null) ? (AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, yScale, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17702, _mut17703, _mut17704, _mut17705), "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17706, _mut17707, _mut17708, _mut17709)) : (AOR_plus(vecAbsoluteTolerance[i], AOR_multiply(vecRelativeTolerance[i], yScale, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17694, _mut17695, _mut17696, _mut17697), "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17698, _mut17699, _mut17700, _mut17701));
            // for the sake of numerical accuracy
            double variation = 0;
            int sign = ROR_equals(AOR_remainder(predictedNordsieck.getRowDimension(), 2, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17710, _mut17711, _mut17712, _mut17713), 0, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17714, _mut17715, _mut17716, _mut17717, _mut17718) ? -1 : 1;
            for (int k = predictedNordsieck.getRowDimension() - 1; ROR_greater_equals(k, 0, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17723, _mut17724, _mut17725, _mut17726, _mut17727); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204");
                variation += AOR_multiply(sign, predictedNordsieck.getEntry(k, i), "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17719, _mut17720, _mut17721, _mut17722);
                sign = -sign;
            }
            variation -= predictedScaled[i];
            final double ratio = AOR_divide((AOR_plus(AOR_minus(predictedState[i], previousState[i], "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17728, _mut17729, _mut17730, _mut17731), variation, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17732, _mut17733, _mut17734, _mut17735)), tol, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17736, _mut17737, _mut17738, _mut17739);
            error += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17740, _mut17741, _mut17742, _mut17743);
        }
        return FastMath.sqrt(AOR_divide(error, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.errorEstimation_204", _mut17749, _mut17750, _mut17751, _mut17752));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(final ExpandableStatefulODE equations, final double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236");
        sanityChecks(equations, t);
        setEquations(equations);
        final boolean forward = ROR_greater(t, equations.getTime(), "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17753, _mut17754, _mut17755, _mut17756, _mut17757);
        // initialize working arrays
        final double[] y = equations.getCompleteState();
        final double[] yDot = new double[y.length];
        // set up an interpolator sharing the integrator arrays
        final NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
        interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        // set up integration control objects
        initIntegration(equations.getTime(), y, t);
        // compute the initial Nordsieck vector using the configured starter integrator
        start(equations.getTime(), y, t);
        interpolator.reinitialize(stepStart, stepSize, scaled, nordsieck);
        interpolator.storeTime(stepStart);
        // reuse the step that was chosen by the starter integrator
        double hNew = stepSize;
        interpolator.rescale(hNew);
        // main integration loop
        isLastStep = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236");
            interpolator.shift();
            final double[] predictedY = new double[y.length];
            final double[] predictedScaled = new double[y.length];
            Array2DRowRealMatrix predictedNordsieck = null;
            double error = 10;
            while (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17780, _mut17781, _mut17782, _mut17783, _mut17784)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236");
                // predict a first estimate of the state at step end
                final double stepEnd = AOR_plus(stepStart, hNew, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17758, _mut17759, _mut17760, _mut17761);
                interpolator.storeTime(stepEnd);
                final ExpandableStatefulODE expandable = getExpandable();
                final EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), predictedY);
                int index = 0;
                for (final EquationsMapper secondary : expandable.getSecondaryMappers()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236");
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), predictedY);
                    ++index;
                }
                // evaluate the derivative
                computeDerivatives(stepEnd, predictedY, yDot);
                // predict Nordsieck vector at step end
                for (int j = 0; ROR_less(j, predictedScaled.length, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17766, _mut17767, _mut17768, _mut17769, _mut17770); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236");
                    predictedScaled[j] = AOR_multiply(hNew, yDot[j], "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17762, _mut17763, _mut17764, _mut17765);
                }
                predictedNordsieck = updateHighOrderDerivativesPhase1(nordsieck);
                updateHighOrderDerivativesPhase2(scaled, predictedScaled, predictedNordsieck);
                // evaluate error
                error = errorEstimation(y, predictedY, predictedScaled, predictedNordsieck);
                if (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17771, _mut17772, _mut17773, _mut17774, _mut17775)) {
                    // reject the step and attempt to reduce error by stepsize control
                    final double factor = computeStepGrowShrinkFactor(error);
                    hNew = filterStep(AOR_multiply(hNew, factor, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17776, _mut17777, _mut17778, _mut17779), forward, false);
                    interpolator.rescale(hNew);
                }
            }
            stepSize = hNew;
            final double stepEnd = AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17785, _mut17786, _mut17787, _mut17788);
            interpolator.reinitialize(stepEnd, stepSize, predictedScaled, predictedNordsieck);
            // discrete events handling
            interpolator.storeTime(stepEnd);
            System.arraycopy(predictedY, 0, y, 0, y.length);
            stepStart = acceptStep(interpolator, y, yDot, t);
            scaled = predictedScaled;
            nordsieck = predictedNordsieck;
            interpolator.reinitialize(stepEnd, stepSize, scaled, nordsieck);
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
                final double scaledH = AOR_multiply(stepSize, factor, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17789, _mut17790, _mut17791, _mut17792);
                final double nextT = AOR_plus(stepStart, scaledH, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17793, _mut17794, _mut17795, _mut17796);
                final boolean nextIsLast = forward ? (ROR_greater_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17802, _mut17803, _mut17804, _mut17805, _mut17806)) : (ROR_less_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17797, _mut17798, _mut17799, _mut17800, _mut17801));
                hNew = filterStep(scaledH, forward, nextIsLast);
                final double filteredNextT = AOR_plus(stepStart, hNew, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17807, _mut17808, _mut17809, _mut17810);
                final boolean filteredNextIsLast = forward ? (ROR_greater_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17816, _mut17817, _mut17818, _mut17819, _mut17820)) : (ROR_less_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17811, _mut17812, _mut17813, _mut17814, _mut17815));
                if (filteredNextIsLast) {
                    hNew = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator.integrate_236", _mut17821, _mut17822, _mut17823, _mut17824);
                }
                interpolator.rescale(hNew);
            }
        } while (!isLastStep);
        // dispatch results
        equations.setTime(stepStart);
        equations.setCompleteState(y);
        resetInternalState();
    }
}
