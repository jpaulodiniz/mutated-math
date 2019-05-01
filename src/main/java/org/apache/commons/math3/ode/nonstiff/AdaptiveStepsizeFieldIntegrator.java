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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class AdaptiveStepsizeFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> {

    @Conditional
    public static boolean _mut16437 = false, _mut16438 = false, _mut16439 = false, _mut16440 = false, _mut16441 = false, _mut16442 = false, _mut16443 = false, _mut16444 = false, _mut16445 = false, _mut16446 = false, _mut16447 = false, _mut16448 = false, _mut16449 = false, _mut16450 = false, _mut16451 = false, _mut16452 = false, _mut16453 = false, _mut16454 = false, _mut16455 = false, _mut16456 = false, _mut16457 = false, _mut16458 = false, _mut16459 = false, _mut16460 = false, _mut16461 = false, _mut16462 = false, _mut16463 = false, _mut16464 = false, _mut16465 = false, _mut16466 = false, _mut16467 = false, _mut16468 = false, _mut16469 = false, _mut16470 = false, _mut16471 = false, _mut16472 = false, _mut16473 = false, _mut16474 = false, _mut16475 = false, _mut16476 = false, _mut16477 = false, _mut16478 = false, _mut16479 = false, _mut16480 = false, _mut16481 = false, _mut16482 = false, _mut16483 = false, _mut16484 = false, _mut16485 = false, _mut16486 = false, _mut16487 = false, _mut16488 = false, _mut16489 = false, _mut16490 = false, _mut16491 = false, _mut16492 = false, _mut16493 = false, _mut16494 = false, _mut16495 = false, _mut16496 = false, _mut16497 = false, _mut16498 = false, _mut16499 = false, _mut16500 = false, _mut16501 = false, _mut16502 = false, _mut16503 = false, _mut16504 = false, _mut16505 = false, _mut16506 = false, _mut16507 = false, _mut16508 = false, _mut16509 = false, _mut16510 = false, _mut16511 = false, _mut16512 = false, _mut16513 = false, _mut16514 = false;

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
    private T initialStep;

    /**
     * Minimal step.
     */
    private T minStep;

    /**
     * Maximal step.
     */
    private T maxStep;

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param field field to which the time and state vector elements belong
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
    public AdaptiveStepsizeFieldIntegrator(final Field<T> field, final String name, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(field, name);
        setStepSizeControl(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        resetInternalState();
    }

    /**
     * Build an integrator with the given stepsize bounds.
     * The default step handler does nothing.
     * @param field field to which the time and state vector elements belong
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
    public AdaptiveStepsizeFieldIntegrator(final Field<T> field, final String name, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(field, name);
        setStepSizeControl(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        resetInternalState();
    }

    /**
     * Set the adaptive step size control parameters.
     * <p>
     * A side effect of this method is to also reset the initial
     * step so it will be automatically computed by the integrator
     * if {@link #setInitialStepSize(RealFieldElement) setInitialStepSize}
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
        minStep = getField().getZero().add(FastMath.abs(minimalStep));
        maxStep = getField().getZero().add(FastMath.abs(maximalStep));
        initialStep = getField().getOne().negate();
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
     * if {@link #setInitialStepSize(RealFieldElement) setInitialStepSize}
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
        minStep = getField().getZero().add(FastMath.abs(minimalStep));
        maxStep = getField().getZero().add(FastMath.abs(maximalStep));
        initialStep = getField().getOne().negate();
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
    public void setInitialStepSize(final T initialStepSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.setInitialStepSize_214");
        if ((_mut16447 ? (ROR_less(initialStepSize.subtract(minStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.setInitialStepSize_214", _mut16437, _mut16438, _mut16439, _mut16440, _mut16441) && ROR_greater(initialStepSize.subtract(maxStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.setInitialStepSize_214", _mut16442, _mut16443, _mut16444, _mut16445, _mut16446)) : (ROR_less(initialStepSize.subtract(minStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.setInitialStepSize_214", _mut16437, _mut16438, _mut16439, _mut16440, _mut16441) || ROR_greater(initialStepSize.subtract(maxStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.setInitialStepSize_214", _mut16442, _mut16443, _mut16444, _mut16445, _mut16446)))) {
            initialStep = getField().getOne().negate();
        } else {
            initialStep = initialStepSize;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void sanityChecks(final FieldODEState<T> eqn, final T t) throws DimensionMismatchException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.sanityChecks_224");
        super.sanityChecks(eqn, t);
        mainSetDimension = eqn.getStateDimension();
        if ((_mut16453 ? (vecAbsoluteTolerance != null || ROR_not_equals(vecAbsoluteTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.sanityChecks_224", _mut16448, _mut16449, _mut16450, _mut16451, _mut16452)) : (vecAbsoluteTolerance != null && ROR_not_equals(vecAbsoluteTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.sanityChecks_224", _mut16448, _mut16449, _mut16450, _mut16451, _mut16452)))) {
            throw new DimensionMismatchException(mainSetDimension, vecAbsoluteTolerance.length);
        }
        if ((_mut16459 ? (vecRelativeTolerance != null || ROR_not_equals(vecRelativeTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.sanityChecks_224", _mut16454, _mut16455, _mut16456, _mut16457, _mut16458)) : (vecRelativeTolerance != null && ROR_not_equals(vecRelativeTolerance.length, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.sanityChecks_224", _mut16454, _mut16455, _mut16456, _mut16457, _mut16458)))) {
            throw new DimensionMismatchException(mainSetDimension, vecRelativeTolerance.length);
        }
    }

    /**
     * Initialize the integration step.
     * @param forward forward integration indicator
     * @param order order of the method
     * @param scale scaling vector for the state vector (can be shorter than state vector)
     * @param state0 state at integration start time
     * @param mapper mapper for all the equations
     * @return first integration step
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public T initializeStep(final boolean forward, final int order, final T[] scale, final FieldODEStateAndDerivative<T> state0, final FieldEquationsMapper<T> mapper) throws MaxCountExceededException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252");
        if (ROR_greater(initialStep.getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16460, _mut16461, _mut16462, _mut16463, _mut16464)) {
            // use the user provided value
            return forward ? initialStep : initialStep.negate();
        }
        // this guess will be used to perform an Euler step
        final T[] y0 = mapper.mapState(state0);
        final T[] yDot0 = mapper.mapDerivative(state0);
        T yOnScale2 = getField().getZero();
        T yDotOnScale2 = getField().getZero();
        for (int j = 0; ROR_less(j, scale.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16465, _mut16466, _mut16467, _mut16468, _mut16469); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252");
            final T ratio = y0[j].divide(scale[j]);
            yOnScale2 = yOnScale2.add(ratio.multiply(ratio));
            final T ratioDot = yDot0[j].divide(scale[j]);
            yDotOnScale2 = yDotOnScale2.add(ratioDot.multiply(ratioDot));
        }
        T h = ((_mut16480 ? (ROR_less(yOnScale2.getReal(), 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16470, _mut16471, _mut16472, _mut16473, _mut16474) && ROR_less(yDotOnScale2.getReal(), 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16475, _mut16476, _mut16477, _mut16478, _mut16479)) : (ROR_less(yOnScale2.getReal(), 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16470, _mut16471, _mut16472, _mut16473, _mut16474) || ROR_less(yDotOnScale2.getReal(), 1.0e-10, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16475, _mut16476, _mut16477, _mut16478, _mut16479)))) ? getField().getZero().add(1.0e-6) : yOnScale2.divide(yDotOnScale2).sqrt().multiply(0.01);
        if (!forward) {
            h = h.negate();
        }
        // perform an Euler step using the preceding rough guess
        final T[] y1 = MathArrays.buildArray(getField(), y0.length);
        for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16481, _mut16482, _mut16483, _mut16484, _mut16485); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252");
            y1[j] = y0[j].add(yDot0[j].multiply(h));
        }
        final T[] yDot1 = computeDerivatives(state0.getTime().add(h), y1);
        // estimate the second derivative of the solution
        T yDDotOnScale = getField().getZero();
        for (int j = 0; ROR_less(j, scale.length, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16486, _mut16487, _mut16488, _mut16489, _mut16490); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252");
            final T ratioDotDot = yDot1[j].subtract(yDot0[j]).divide(scale[j]);
            yDDotOnScale = yDDotOnScale.add(ratioDotDot.multiply(ratioDotDot));
        }
        yDDotOnScale = yDDotOnScale.sqrt().divide(h);
        // h^order * max (||y'/tol||, ||y''/tol||) = 0.01
        final T maxInv2 = MathUtils.max(yDotOnScale2.sqrt(), yDDotOnScale);
        final T h1 = ROR_less(maxInv2.getReal(), 1.0e-15, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16491, _mut16492, _mut16493, _mut16494, _mut16495) ? MathUtils.max(getField().getZero().add(1.0e-6), h.abs().multiply(0.001)) : maxInv2.multiply(100).reciprocal().pow(AOR_divide(1.0, order, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.initializeStep_252", _mut16496, _mut16497, _mut16498, _mut16499));
        h = MathUtils.min(h.abs().multiply(100), h1);
        // avoids cancellation when computing t1 - t0
        h = MathUtils.max(h, state0.getTime().abs().multiply(1.0e-12));
        h = MathUtils.max(minStep, MathUtils.min(maxStep, h));
        if (!forward) {
            h = h.negate();
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
    protected T filterStep(final T h, final boolean forward, final boolean acceptSmall) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.filterStep_323");
        T filteredH = h;
        if (ROR_less(h.abs().subtract(minStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.filterStep_323", _mut16500, _mut16501, _mut16502, _mut16503, _mut16504)) {
            if (acceptSmall) {
                filteredH = forward ? minStep : minStep.negate();
            } else {
                throw new NumberIsTooSmallException(LocalizedFormats.MINIMAL_STEPSIZE_REACHED_DURING_INTEGRATION, h.abs().getReal(), minStep.getReal(), true);
            }
        }
        if (ROR_greater(filteredH.subtract(maxStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.filterStep_323", _mut16505, _mut16506, _mut16507, _mut16508, _mut16509)) {
            filteredH = maxStep;
        } else if (ROR_less(filteredH.add(maxStep).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeFieldIntegrator.filterStep_323", _mut16510, _mut16511, _mut16512, _mut16513, _mut16514)) {
            filteredH = maxStep.negate();
        }
        return filteredH;
    }

    /**
     * Reset internal state to dummy values.
     */
    protected void resetInternalState() {
        setStepStart(null);
        setStepSize(minStep.multiply(maxStep).sqrt());
    }

    /**
     * Get the minimal step.
     * @return minimal step
     */
    public T getMinStep() {
        return minStep;
    }

    /**
     * Get the maximal step.
     * @return maximal step
     */
    public T getMaxStep() {
        return maxStep;
    }
}
