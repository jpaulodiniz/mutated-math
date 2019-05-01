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
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class is the base class for multistep integrators for Ordinary
 * Differential Equations.
 * <p>We define scaled derivatives s<sub>i</sub>(n) at step n as:
 * <pre>
 * s<sub>1</sub>(n) = h y'<sub>n</sub> for first derivative
 * s<sub>2</sub>(n) = h<sup>2</sup>/2 y''<sub>n</sub> for second derivative
 * s<sub>3</sub>(n) = h<sup>3</sup>/6 y'''<sub>n</sub> for third derivative
 * ...
 * s<sub>k</sub>(n) = h<sup>k</sup>/k! y<sup>(k)</sup><sub>n</sub> for k<sup>th</sup> derivative
 * </pre></p>
 * <p>Rather than storing several previous steps separately, this implementation uses
 * the Nordsieck vector with higher degrees scaled derivatives all taken at the same
 * step (y<sub>n</sub>, s<sub>1</sub>(n) and r<sub>n</sub>) where r<sub>n</sub> is defined as:
 * <pre>
 * r<sub>n</sub> = [ s<sub>2</sub>(n), s<sub>3</sub>(n) ... s<sub>k</sub>(n) ]<sup>T</sup>
 * </pre>
 * (we omit the k index in the notation for clarity)</p>
 * <p>
 * Multistep integrators with Nordsieck representation are highly sensitive to
 * large step changes because when the step is multiplied by factor a, the
 * k<sup>th</sup> component of the Nordsieck vector is multiplied by a<sup>k</sup>
 * and the last components are the least accurate ones. The default max growth
 * factor is therefore set to a quite low value: 2<sup>1/order</sup>.
 * </p>
 *
 * @see org.apache.commons.math3.ode.nonstiff.AdamsBashforthIntegrator
 * @see org.apache.commons.math3.ode.nonstiff.AdamsMoultonIntegrator
 * @since 2.0
 */
public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {

    @Conditional
    public static boolean _mut11671 = false, _mut11672 = false, _mut11673 = false, _mut11674 = false, _mut11675 = false, _mut11676 = false, _mut11677 = false, _mut11678 = false, _mut11679 = false, _mut11680 = false, _mut11681 = false, _mut11682 = false, _mut11683 = false, _mut11684 = false, _mut11685 = false, _mut11686 = false, _mut11687 = false, _mut11688 = false, _mut11689 = false, _mut11690 = false, _mut11691 = false, _mut11692 = false, _mut11693 = false, _mut11694 = false, _mut11695 = false, _mut11696 = false, _mut11697 = false, _mut11698 = false, _mut11699 = false, _mut11700 = false, _mut11701 = false, _mut11702 = false, _mut11703 = false, _mut11704 = false, _mut11705 = false, _mut11706 = false, _mut11707 = false, _mut11708 = false, _mut11709 = false, _mut11710 = false, _mut11711 = false, _mut11712 = false, _mut11713 = false, _mut11714 = false, _mut11715 = false, _mut11716 = false, _mut11717 = false, _mut11718 = false, _mut11719 = false, _mut11720 = false, _mut11721 = false, _mut11722 = false, _mut11723 = false, _mut11724 = false, _mut11725 = false, _mut11726 = false, _mut11727 = false, _mut11728 = false, _mut11729 = false, _mut11730 = false;

    /**
     * First scaled derivative (h y').
     */
    protected double[] scaled;

    /**
     * Nordsieck matrix of the higher scaled derivatives.
     * <p>(h<sup>2</sup>/2 y'', h<sup>3</sup>/6 y''' ..., h<sup>k</sup>/k! y<sup>(k)</sup>)</p>
     */
    protected Array2DRowRealMatrix nordsieck;

    /**
     * Starter integrator.
     */
    private FirstOrderIntegrator starter;

    /**
     * Number of steps of the multistep method (excluding the one being computed).
     */
    private final int nSteps;

    /**
     * Stepsize control exponent.
     */
    private double exp;

    /**
     * Safety factor for stepsize control.
     */
    private double safety;

    /**
     * Minimal reduction factor for stepsize control.
     */
    private double minReduction;

    /**
     * Maximal growth factor for stepsize control.
     */
    private double maxGrowth;

    /**
     * Build a multistep integrator with the given stepsize bounds.
     * <p>The default starter integrator is set to the {@link
     * DormandPrince853Integrator Dormand-Prince 8(5,3)} integrator with
     * some defaults settings.</p>
     * <p>
     * The default max growth factor is set to a quite low value: 2<sup>1/order</sup>.
     * </p>
     * @param name name of the method
     * @param nSteps number of steps of the multistep method
     * (excluding the one being computed)
     * @param order order of the method
     * @param minStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maxStep maximal step (must be positive even for backward
     * integration)
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     * @exception NumberIsTooSmallException if number of steps is smaller than 2
     */
    protected MultistepIntegrator(final String name, final int nSteps, final int order, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.MultistepIntegrator_111");
        if (ROR_less(nSteps, 2, "org.apache.commons.math3.ode.MultistepIntegrator.MultistepIntegrator_111", _mut11671, _mut11672, _mut11673, _mut11674, _mut11675)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, nSteps, 2, true);
        }
        starter = new DormandPrince853Integrator(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.nSteps = nSteps;
        exp = AOR_divide(-1.0, order, "org.apache.commons.math3.ode.MultistepIntegrator.MultistepIntegrator_111", _mut11676, _mut11677, _mut11678, _mut11679);
        // set the default values of the algorithm control parameters
        setSafety(0.9);
        setMinReduction(0.2);
        setMaxGrowth(FastMath.pow(2.0, -exp));
    }

    /**
     * Build a multistep integrator with the given stepsize bounds.
     * <p>The default starter integrator is set to the {@link
     * DormandPrince853Integrator Dormand-Prince 8(5,3)} integrator with
     * some defaults settings.</p>
     * <p>
     * The default max growth factor is set to a quite low value: 2<sup>1/order</sup>.
     * </p>
     * @param name name of the method
     * @param nSteps number of steps of the multistep method
     * (excluding the one being computed)
     * @param order order of the method
     * @param minStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maxStep maximal step (must be positive even for backward
     * integration)
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    protected MultistepIntegrator(final String name, final int nSteps, final int order, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.MultistepIntegrator_159");
        starter = new DormandPrince853Integrator(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.nSteps = nSteps;
        exp = AOR_divide(-1.0, order, "org.apache.commons.math3.ode.MultistepIntegrator.MultistepIntegrator_159", _mut11680, _mut11681, _mut11682, _mut11683);
        // set the default values of the algorithm control parameters
        setSafety(0.9);
        setMinReduction(0.2);
        setMaxGrowth(FastMath.pow(2.0, -exp));
    }

    /**
     * Get the starter integrator.
     * @return starter integrator
     */
    public ODEIntegrator getStarterIntegrator() {
        return starter;
    }

    /**
     * Set the starter integrator.
     * <p>The various step and event handlers for this starter integrator
     * will be managed automatically by the multi-step integrator. Any
     * user configuration for these elements will be cleared before use.</p>
     * @param starterIntegrator starter integrator
     */
    public void setStarterIntegrator(FirstOrderIntegrator starterIntegrator) {
        this.starter = starterIntegrator;
    }

    /**
     * Start the integration.
     * <p>This method computes one step using the underlying starter integrator,
     * and initializes the Nordsieck vector at step start. The starter integrator
     * purpose is only to establish initial conditions, it does not really change
     * time by itself. The top level multistep integrator remains in charge of
     * handling time propagation and events handling as it will starts its own
     * computation right from the beginning. In a sense, the starter integrator
     * can be seen as a dummy one and so it will never trigger any user event nor
     * call any user step handler.</p>
     * @param t0 initial time
     * @param y0 initial value of the state vector at t0
     * @param t target time for the integration
     * (can be set to a value smaller than <code>t0</code> for backward integration)
     * @exception DimensionMismatchException if arrays dimension do not match equations settings
     * @exception NumberIsTooSmallException if integration step is too small
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception NoBracketingException if the location of an event cannot be bracketed
     */
    protected void start(final double t0, final double[] y0, final double t) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.computeDerivatives_243");
        // of the starter integrator
        starter.clearEventHandlers();
        starter.clearStepHandlers();
        // set up one specific step handler to extract initial Nordsieck vector
        starter.addStepHandler(new NordsieckInitializer(AOR_divide((AOR_plus(nSteps, 3, "org.apache.commons.math3.ode.MultistepIntegrator.start_216", _mut11684, _mut11685, _mut11686, _mut11687)), 2, "org.apache.commons.math3.ode.MultistepIntegrator.start_216", _mut11688, _mut11689, _mut11690, _mut11691), y0.length));
        // start integration, expecting a InitializationCompletedMarkerException
        try {
            if (starter instanceof AbstractIntegrator) {
                ((AbstractIntegrator) starter).integrate(getExpandable(), t);
            } else {
                starter.integrate(new FirstOrderDifferentialEquations() {

                    /**
                     * {@inheritDoc}
                     */
                    public int getDimension() {
                        return getExpandable().getTotalDimension();
                    }

                    /**
                     * {@inheritDoc}
                     */
                    public void computeDerivatives(double t, double[] y, double[] yDot) {
                        getExpandable().computeDerivatives(t, y, yDot);
                    }
                }, t0, y0, t, new double[y0.length]);
            }
            // we should not reach this step
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY);
        } catch (InitializationCompletedMarkerException icme) {
            // count the evaluations used by the starter
            getCounter().increment(starter.getEvaluations());
        }
        // remove the specific step handler
        starter.clearStepHandlers();
    }

    /**
     * Initialize the high order scaled derivatives at step start.
     * @param h step size to use for scaling
     * @param t first steps times
     * @param y first steps states
     * @param yDot first steps derivatives
     * @return Nordieck vector at first step (h<sup>2</sup>/2 y''<sub>n</sub>,
     * h<sup>3</sup>/6 y'''<sub>n</sub> ... h<sup>k</sup>/k! y<sup>(k)</sup><sub>n</sub>)
     */
    protected abstract Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot);

    /**
     * Get the minimal reduction factor for stepsize control.
     * @return minimal reduction factor
     */
    public double getMinReduction() {
        return minReduction;
    }

    /**
     * Set the minimal reduction factor for stepsize control.
     * @param minReduction minimal reduction factor
     */
    public void setMinReduction(final double minReduction) {
        this.minReduction = minReduction;
    }

    /**
     * Get the maximal growth factor for stepsize control.
     * @return maximal growth factor
     */
    public double getMaxGrowth() {
        return maxGrowth;
    }

    /**
     * Set the maximal growth factor for stepsize control.
     * @param maxGrowth maximal growth factor
     */
    public void setMaxGrowth(final double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    /**
     * Get the safety factor for stepsize control.
     * @return safety factor
     */
    public double getSafety() {
        return safety;
    }

    /**
     * Set the safety factor for stepsize control.
     * @param safety safety factor
     */
    public void setSafety(final double safety) {
        this.safety = safety;
    }

    /**
     * Get the number of steps of the multistep method (excluding the one being computed).
     * @return number of steps of the multistep method (excluding the one being computed)
     */
    public int getNSteps() {
        return nSteps;
    }

    /**
     * Compute step grow/shrink factor according to normalized error.
     * @param error normalized error of the current step
     * @return grow/shrink factor for next step
     */
    protected double computeStepGrowShrinkFactor(final double error) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.computeStepGrowShrinkFactor_331");
        return FastMath.min(maxGrowth, FastMath.max(minReduction, AOR_multiply(safety, FastMath.pow(error, exp), "org.apache.commons.math3.ode.MultistepIntegrator.computeStepGrowShrinkFactor_331", _mut11692, _mut11693, _mut11694, _mut11695)));
    }

    /**
     * Transformer used to convert the first step to Nordsieck representation.
     * @deprecated as of 3.6 this unused interface is deprecated
     */
    @Deprecated
    public interface NordsieckTransformer {

        /**
         * Initialize the high order scaled derivatives at step start.
         * @param h step size to use for scaling
         * @param t first steps times
         * @param y first steps states
         * @param yDot first steps derivatives
         * @return Nordieck vector at first step (h<sup>2</sup>/2 y''<sub>n</sub>,
         * h<sup>3</sup>/6 y'''<sub>n</sub> ... h<sup>k</sup>/k! y<sup>(k)</sup><sub>n</sub>)
         */
        Array2DRowRealMatrix initializeHighOrderDerivatives(final double h, final double[] t, final double[][] y, final double[][] yDot);
    }

    /**
     * Specialized step handler storing the first step.
     */
    private class NordsieckInitializer implements StepHandler {

        /**
         * Steps counter.
         */
        private int count;

        /**
         * First steps times.
         */
        private final double[] t;

        /**
         * First steps states.
         */
        private final double[][] y;

        /**
         * First steps derivatives.
         */
        private final double[][] yDot;

        /**
         * Simple constructor.
         * @param nbStartPoints number of start points (including the initial point)
         * @param n problem dimension
         */
        NordsieckInitializer(final int nbStartPoints, final int n) {
            this.count = 0;
            this.t = new double[nbStartPoints];
            this.y = new double[nbStartPoints][n];
            this.yDot = new double[nbStartPoints][n];
        }

        /**
         * {@inheritDoc}
         */
        public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380");
            final double prev = interpolator.getPreviousTime();
            final double curr = interpolator.getCurrentTime();
            if (ROR_equals(count, 0, "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11696, _mut11697, _mut11698, _mut11699, _mut11700)) {
                // first step, we need to store also the point at the beginning of the step
                interpolator.setInterpolatedTime(prev);
                t[0] = prev;
                final ExpandableStatefulODE expandable = getExpandable();
                final EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), y[count]);
                primary.insertEquationData(interpolator.getInterpolatedDerivatives(), yDot[count]);
                int index = 0;
                for (final EquationsMapper secondary : expandable.getSecondaryMappers()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380");
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), y[count]);
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index), yDot[count]);
                    ++index;
                }
            }
            // store the point at the end of the step
            ++count;
            interpolator.setInterpolatedTime(curr);
            t[count] = curr;
            final ExpandableStatefulODE expandable = getExpandable();
            final EquationsMapper primary = expandable.getPrimaryMapper();
            primary.insertEquationData(interpolator.getInterpolatedState(), y[count]);
            primary.insertEquationData(interpolator.getInterpolatedDerivatives(), yDot[count]);
            int index = 0;
            for (final EquationsMapper secondary : expandable.getSecondaryMappers()) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380");
                secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), y[count]);
                secondary.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index), yDot[count]);
                ++index;
            }
            if (ROR_equals(count, AOR_minus(t.length, 1, "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11701, _mut11702, _mut11703, _mut11704), "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11705, _mut11706, _mut11707, _mut11708, _mut11709)) {
                // this was the last point we needed, we can compute the derivatives
                stepStart = t[0];
                stepSize = AOR_divide((AOR_minus(t[AOR_minus(t.length, 1, "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11710, _mut11711, _mut11712, _mut11713)], t[0], "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11714, _mut11715, _mut11716, _mut11717)), (AOR_minus(t.length, 1, "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11718, _mut11719, _mut11720, _mut11721)), "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11722, _mut11723, _mut11724, _mut11725);
                // first scaled derivative
                scaled = yDot[0].clone();
                for (int j = 0; ROR_less(j, scaled.length, "org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380", _mut11726, _mut11727, _mut11728, _mut11729, _mut11730); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.MultistepIntegrator.handleStep_380");
                    scaled[j] *= stepSize;
                }
                // higher order derivatives
                nordsieck = initializeHighOrderDerivatives(stepSize, t, y, yDot);
                // stop the integrator now that all needed steps have been handled
                throw new InitializationCompletedMarkerException();
            }
        }

        /**
         * {@inheritDoc}
         */
        public void init(double t0, double[] y0, double time) {
        }
    }

    /**
     * Marker exception used ONLY to stop the starter integrator after first step.
     */
    private static class InitializationCompletedMarkerException extends RuntimeException {

        /**
         * Serializable version identifier.
         */
        private static final long serialVersionUID = -1914085471038046418L;

        /**
         * Simple constructor.
         */
        InitializationCompletedMarkerException() {
            super((Throwable) null);
        }
    }
}
