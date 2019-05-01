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
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class AdaptiveStepsizeIntegrator extends AbstractIntegrator {

    @Conditional
    public static boolean _mut17534 = false, _mut17535 = false, _mut17536 = false, _mut17537 = false, _mut17538 = false, _mut17539 = false, _mut17540 = false, _mut17541 = false, _mut17542 = false, _mut17543 = false, _mut17544 = false, _mut17545 = false, _mut17546 = false, _mut17547 = false, _mut17548 = false, _mut17549 = false, _mut17550 = false, _mut17551 = false, _mut17552 = false, _mut17553 = false, _mut17554 = false, _mut17555 = false, _mut17556 = false, _mut17557 = false, _mut17558 = false, _mut17559 = false, _mut17560 = false, _mut17561 = false, _mut17562 = false, _mut17563 = false, _mut17564 = false, _mut17565 = false, _mut17566 = false, _mut17567 = false, _mut17568 = false, _mut17569 = false, _mut17570 = false, _mut17571 = false, _mut17572 = false, _mut17573 = false, _mut17574 = false, _mut17575 = false, _mut17576 = false, _mut17577 = false, _mut17578 = false, _mut17579 = false, _mut17580 = false, _mut17581 = false, _mut17582 = false, _mut17583 = false, _mut17584 = false, _mut17585 = false, _mut17586 = false, _mut17587 = false, _mut17588 = false, _mut17589 = false, _mut17590 = false, _mut17591 = false, _mut17592 = false, _mut17593 = false, _mut17594 = false, _mut17595 = false, _mut17596 = false, _mut17597 = false, _mut17598 = false, _mut17599 = false, _mut17600 = false, _mut17601 = false, _mut17602 = false, _mut17603 = false, _mut17604 = false, _mut17605 = false, _mut17606 = false, _mut17607 = false, _mut17608 = false, _mut17609 = false, _mut17610 = false, _mut17611 = false, _mut17612 = false, _mut17613 = false, _mut17614 = false, _mut17615 = false, _mut17616 = false, _mut17617 = false, _mut17618 = false, _mut17619 = false, _mut17620 = false, _mut17621 = false, _mut17622 = false, _mut17623 = false, _mut17624 = false, _mut17625 = false, _mut17626 = false, _mut17627 = false, _mut17628 = false, _mut17629 = false, _mut17630 = false, _mut17631 = false, _mut17632 = false, _mut17633 = false, _mut17634 = false, _mut17635 = false, _mut17636 = false, _mut17637 = false, _mut17638 = false, _mut17639 = false, _mut17640 = false, _mut17641 = false, _mut17642 = false, _mut17643 = false, _mut17644 = false, _mut17645 = false, _mut17646 = false, _mut17647 = false, _mut17648 = false, _mut17649 = false, _mut17650 = false, _mut17651 = false, _mut17652 = false, _mut17653 = false, _mut17654 = false, _mut17655 = false, _mut17656 = false, _mut17657 = false, _mut17658 = false, _mut17659 = false, _mut17660 = false, _mut17661 = false, _mut17662 = false, _mut17663 = false, _mut17664 = false, _mut17665 = false, _mut17666 = false, _mut17667 = false, _mut17668 = false, _mut17669 = false, _mut17670 = false, _mut17671 = false, _mut17672 = false, _mut17673 = false, _mut17674 = false, _mut17675 = false, _mut17676 = false, _mut17677 = false, _mut17678 = false, _mut17679 = false, _mut17680 = false, _mut17681 = false, _mut17682 = false, _mut17683 = false, _mut17684 = false, _mut17685 = false, _mut17686 = false, _mut17687 = false, _mut17688 = false, _mut17689 = false, _mut17690 = false, _mut17691 = false, _mut17692 = false, _mut17693 = false;

    /**
     * Allowed absolute scalar error.
     */
    protected double scalAbsoluteTolerance;

    /**
     * Allowed relative scalar error.
     */
    protected double scalRelativeTolerance;

    /**
     * Allowed absolute vectorial error.
     */
    protected double[] vecAbsoluteTolerance;

    /**
     * Allowed relative vectorial error.
     */
    protected double[] vecRelativeTolerance;

    /**
     * Main set dimension.
     */
    protected int mainSetDimension;

    /**
     * User supplied initial step.
     */
    private double initialStep;

    /**
     * Minimal step.
     */
    private double minStep;

    /**
     * Maximal step.
     */
    private double maxStep;

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param name name of the method
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public AdaptiveStepsizeIntegrator(final String name, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(name);
        setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        resetInternalState();
    }

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param name name of the method
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public AdaptiveStepsizeIntegrator(final String name, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(name);
        setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        resetInternalState();
    }

    /**
     * Set the adaptive step size control parameters.
     * <p>
     * A side effect of this method is to also reset the initial
     * step so it will be automatically computed by the integrator
     * if {@link #setInitialStepSize(double) setInitialStepSize}
     * is not called by the user.
     * </p>
     * @param minimalStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maximalStep maximal step (must be positive even for backward
     * integration)
     * @param absoluteTolerance allowed absolute error
     * @param relativeTolerance allowed relative error
     */
    public void setStepSizeControl(final double minimalStep, final double maximalStep, final double absoluteTolerance, final double relativeTolerance) {
        minStep = FastMath.abs(minimalStep);
        maxStep = FastMath.abs(maximalStep);
        initialStep = -1;
        scalAbsoluteTolerance = absoluteTolerance;
        scalRelativeTolerance = relativeTolerance;
        vecAbsoluteTolerance = null;
        vecRelativeTolerance = null;
    }

    /**
     * Set the adaptive step size control parameters.
     * <p>
     * A side effect of this method is to also reset the initial
     * step so it will be automatically computed by the integrator
     * if {@link #setInitialStepSize(double) setInitialStepSize}
     * is not called by the user.
     * </p>
     * @param minimalStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maximalStep maximal step (must be positive even for backward
     * integration)
     * @param absoluteTolerance allowed absolute error
     * @param relativeTolerance allowed relative error
     */
    public void setStepSizeControl(final double minimalStep, final double maximalStep, final double[] absoluteTolerance, final double[] relativeTolerance) {
        minStep = FastMath.abs(minimalStep);
        maxStep = FastMath.abs(maximalStep);
        initialStep = -1;
        scalAbsoluteTolerance = 0;
        scalRelativeTolerance = 0;
        vecAbsoluteTolerance = absoluteTolerance.clone();
        vecRelativeTolerance = relativeTolerance.clone();
    }

    /**
     * Set the initial step size.
     * <p>This method allows the user to specify an initial positive
     * step size instead of letting the integrator guess it by
     * itself. If this method is not called before integration is
     * started, the initial step size will be estimated by the
     * integrator.</p>
     * @param initialStepSize initial step size to use (must be positive even
     * for backward integration ; providing a negative value or a value
     * outside of the min/max step interval will lead the integrator to
     * ignore the value and compute the initial step size by itself)
     */
    public void setInitialStepSize(final double initialStepSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.setInitialStepSize_207");
        if ((_mut17544 ? ((ROR_less(initialStepSize, minStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.setInitialStepSize_207", _mut17534, _mut17535, _mut17536, _mut17537, _mut17538)) && (ROR_greater(initialStepSize, maxStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.setInitialStepSize_207", _mut17539, _mut17540, _mut17541, _mut17542, _mut17543))) : ((ROR_less(initialStepSize, minStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.setInitialStepSize_207", _mut17534, _mut17535, _mut17536, _mut17537, _mut17538)) || (ROR_greater(initialStepSize, maxStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.setInitialStepSize_207", _mut17539, _mut17540, _mut17541, _mut17542, _mut17543))))) {
            initialStep = -1.0;
        } else {
            initialStep = initialStepSize;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sanityChecks(final ExpandableStatefulODE equations, final double t) throws DimensionMismatchException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.sanityChecks_216");
        super.sanityChecks(equations, t);
        mainSetDimension = equations.getPrimaryMapper().getDimension();
        if ((_mut17550 ? ((vecAbsoluteTolerance != null) || (ROR_not_equals(vecAbsoluteTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.sanityChecks_216", _mut17545, _mut17546, _mut17547, _mut17548, _mut17549))) : ((vecAbsoluteTolerance != null) && (ROR_not_equals(vecAbsoluteTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.sanityChecks_216", _mut17545, _mut17546, _mut17547, _mut17548, _mut17549))))) {
            throw new DimensionMismatchException(mainSetDimension, vecAbsoluteTolerance.length);
        }
        if ((_mut17556 ? ((vecRelativeTolerance != null) || (ROR_not_equals(vecRelativeTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.sanityChecks_216", _mut17551, _mut17552, _mut17553, _mut17554, _mut17555))) : ((vecRelativeTolerance != null) && (ROR_not_equals(vecRelativeTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.sanityChecks_216", _mut17551, _mut17552, _mut17553, _mut17554, _mut17555))))) {
            throw new DimensionMismatchException(mainSetDimension, vecRelativeTolerance.length);
        }
    }

    /**
     * Initialize the integration step.
     * @param forward forward integration indicator
     * @param order order of the method
     * @param scale scaling vector for the state vector (can be shorter than state vector)
     * @param t0 start time
     * @param y0 state vector at t0
     * @param yDot0 first time derivative of y0
     * @param y1 work array for a state vector
     * @param yDot1 work array for the first time derivative of y1
     * @return first integration step
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public double initializeStep(final boolean forward, final int order, final double[] scale, final double t0, final double[] y0, final double[] yDot0, final double[] y1, final double[] yDot1) throws MaxCountExceededException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247");
        if (ROR_greater(initialStep, 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17557, _mut17558, _mut17559, _mut17560, _mut17561)) {
            // use the user provided value
            return forward ? initialStep : -initialStep;
        }
        // this guess will be used to perform an Euler step
        double ratio;
        double yOnScale2 = 0;
        double yDotOnScale2 = 0;
        for (int j = 0; ROR_less(j, scale.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17578, _mut17579, _mut17580, _mut17581, _mut17582); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247");
            ratio = AOR_divide(y0[j], scale[j], "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17562, _mut17563, _mut17564, _mut17565);
            yOnScale2 += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17566, _mut17567, _mut17568, _mut17569);
            ratio = AOR_divide(yDot0[j], scale[j], "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17570, _mut17571, _mut17572, _mut17573);
            yDotOnScale2 += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17574, _mut17575, _mut17576, _mut17577);
        }
        double h = ((_mut17593 ? ((ROR_less(yOnScale2, 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17583, _mut17584, _mut17585, _mut17586, _mut17587)) && (ROR_less(yDotOnScale2, 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17588, _mut17589, _mut17590, _mut17591, _mut17592))) : ((ROR_less(yOnScale2, 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17583, _mut17584, _mut17585, _mut17586, _mut17587)) || (ROR_less(yDotOnScale2, 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17588, _mut17589, _mut17590, _mut17591, _mut17592))))) ? 1.0e-6 : (AOR_multiply(0.01, FastMath.sqrt(AOR_divide(yOnScale2, yDotOnScale2, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17594, _mut17595, _mut17596, _mut17597)), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17598, _mut17599, _mut17600, _mut17601));
        if (!forward) {
            h = -h;
        }
        // perform an Euler step using the preceding rough guess
        for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17610, _mut17611, _mut17612, _mut17613, _mut17614); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247");
            y1[j] = AOR_plus(y0[j], AOR_multiply(h, yDot0[j], "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17602, _mut17603, _mut17604, _mut17605), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17606, _mut17607, _mut17608, _mut17609);
        }
        computeDerivatives(AOR_plus(t0, h, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17615, _mut17616, _mut17617, _mut17618), y1, yDot1);
        // estimate the second derivative of the solution
        double yDDotOnScale = 0;
        for (int j = 0; ROR_less(j, scale.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17631, _mut17632, _mut17633, _mut17634, _mut17635); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247");
            ratio = AOR_divide((AOR_minus(yDot1[j], yDot0[j], "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17619, _mut17620, _mut17621, _mut17622)), scale[j], "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17623, _mut17624, _mut17625, _mut17626);
            yDDotOnScale += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17627, _mut17628, _mut17629, _mut17630);
        }
        yDDotOnScale = AOR_divide(FastMath.sqrt(yDDotOnScale), h, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17636, _mut17637, _mut17638, _mut17639);
        // h^order * max (||y'/tol||, ||y''/tol||) = 0.01
        final double maxInv2 = FastMath.max(FastMath.sqrt(yDotOnScale2), yDDotOnScale);
        final double h1 = (ROR_less(maxInv2, 1.0e-15, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17640, _mut17641, _mut17642, _mut17643, _mut17644)) ? FastMath.max(1.0e-6, AOR_multiply(0.001, FastMath.abs(h), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17653, _mut17654, _mut17655, _mut17656)) : FastMath.pow(AOR_divide(0.01, maxInv2, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17645, _mut17646, _mut17647, _mut17648), AOR_divide(1.0, order, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17649, _mut17650, _mut17651, _mut17652));
        h = FastMath.min(AOR_multiply(100.0, FastMath.abs(h), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17657, _mut17658, _mut17659, _mut17660), h1);
        // avoids cancellation when computing t1 - t0
        h = FastMath.max(h, AOR_multiply(1.0e-12, FastMath.abs(t0), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17661, _mut17662, _mut17663, _mut17664));
        if (ROR_less(h, getMinStep(), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17665, _mut17666, _mut17667, _mut17668, _mut17669)) {
            h = getMinStep();
        }
        if (ROR_greater(h, getMaxStep(), "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.initializeStep_247", _mut17670, _mut17671, _mut17672, _mut17673, _mut17674)) {
            h = getMaxStep();
        }
        if (!forward) {
            h = -h;
        }
        return h;
    }

    /**
     * Filter the integration step.
     * @param h signed step
     * @param forward forward integration indicator
     * @param acceptSmall if true, steps smaller than the minimal value
     * are silently increased up to this value, if false such small
     * steps generate an exception
     * @return a bounded integration step (h if no bound is reach, or a bounded value)
     * @exception NumberIsTooSmallException if the step is too small and acceptSmall is false
     */
    protected double filterStep(final double h, final boolean forward, final boolean acceptSmall) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.filterStep_320");
        double filteredH = h;
        if (ROR_less(FastMath.abs(h), minStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.filterStep_320", _mut17675, _mut17676, _mut17677, _mut17678, _mut17679)) {
            if (acceptSmall) {
                filteredH = forward ? minStep : -minStep;
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, FastMath.abs(h), minStep, true);
            }
        }
        if (ROR_greater(filteredH, maxStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.filterStep_320", _mut17680, _mut17681, _mut17682, _mut17683, _mut17684)) {
            filteredH = maxStep;
        } else if (ROR_less(filteredH, -maxStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.filterStep_320", _mut17685, _mut17686, _mut17687, _mut17688, _mut17689)) {
            filteredH = -maxStep;
        }
        return filteredH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentStepStart() {
        return stepStart;
    }

    /**
     * Reset internal state to dummy values.
     */
    protected void resetInternalState() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.resetInternalState_356");
        stepStart = Double.NaN;
        stepSize = FastMath.sqrt(AOR_multiply(minStep, maxStep, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator.resetInternalState_356", _mut17690, _mut17691, _mut17692, _mut17693));
    }

    /**
     * Get the minimal step.
     * @return minimal step
     */
    public double getMinStep() {
        return minStep;
    }

    /**
     * Get the maximal step.
     * @return maximal step
     */
    public double getMaxStep() {
        return maxStep;
    }
}
