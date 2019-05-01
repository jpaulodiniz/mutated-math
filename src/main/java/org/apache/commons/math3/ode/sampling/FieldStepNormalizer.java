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
package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FieldStepNormalizer<T extends RealFieldElement<T>> implements FieldStepHandler<T> {

    @Conditional
    public static boolean _mut11491 = false, _mut11492 = false, _mut11493 = false, _mut11494 = false, _mut11495 = false, _mut11496 = false, _mut11497 = false, _mut11498 = false, _mut11499 = false, _mut11500 = false, _mut11501 = false, _mut11502 = false, _mut11503 = false, _mut11504 = false, _mut11505 = false, _mut11506 = false, _mut11507 = false, _mut11508 = false, _mut11509 = false, _mut11510 = false, _mut11511 = false, _mut11512 = false, _mut11513 = false, _mut11514 = false, _mut11515 = false, _mut11516 = false, _mut11517 = false, _mut11518 = false, _mut11519 = false, _mut11520 = false, _mut11521 = false, _mut11522 = false, _mut11523 = false, _mut11524 = false, _mut11525 = false;

    /**
     * Fixed time step.
     */
    private double h;

    /**
     * Underlying step handler.
     */
    private final FieldFixedStepHandler<T> handler;

    /**
     * First step state.
     */
    private FieldODEStateAndDerivative<T> first;

    /**
     * Last step step.
     */
    private FieldODEStateAndDerivative<T> last;

    /**
     * Integration direction indicator.
     */
    private boolean forward;

    /**
     * The step normalizer bounds settings to use.
     */
    private final StepNormalizerBounds bounds;

    /**
     * The step normalizer mode to use.
     */
    private final StepNormalizerMode mode;

    /**
     * Simple constructor. Uses {@link StepNormalizerMode#INCREMENT INCREMENT}
     * mode, and {@link StepNormalizerBounds#FIRST FIRST} bounds setting, for
     * backwards compatibility.
     * @param h fixed time step (sign is not used)
     * @param handler fixed time step handler to wrap
     */
    public FieldStepNormalizer(final double h, final FieldFixedStepHandler<T> handler) {
        this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    /**
     * Simple constructor. Uses {@link StepNormalizerBounds#FIRST FIRST}
     * bounds setting.
     * @param h fixed time step (sign is not used)
     * @param handler fixed time step handler to wrap
     * @param mode step normalizer mode to use
     * @since 3.0
     */
    public FieldStepNormalizer(final double h, final FieldFixedStepHandler<T> handler, final StepNormalizerMode mode) {
        this(h, handler, mode, StepNormalizerBounds.FIRST);
    }

    /**
     * Simple constructor. Uses {@link StepNormalizerMode#INCREMENT INCREMENT}
     * mode.
     * @param h fixed time step (sign is not used)
     * @param handler fixed time step handler to wrap
     * @param bounds step normalizer bounds setting to use
     * @since 3.0
     */
    public FieldStepNormalizer(final double h, final FieldFixedStepHandler<T> handler, final StepNormalizerBounds bounds) {
        this(h, handler, StepNormalizerMode.INCREMENT, bounds);
    }

    /**
     * Simple constructor.
     * @param h fixed time step (sign is not used)
     * @param handler fixed time step handler to wrap
     * @param mode step normalizer mode to use
     * @param bounds step normalizer bounds setting to use
     * @since 3.0
     */
    public FieldStepNormalizer(final double h, final FieldFixedStepHandler<T> handler, final StepNormalizerMode mode, final StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        first = null;
        last = null;
        forward = true;
    }

    /**
     * {@inheritDoc}
     */
    public void init(final FieldODEStateAndDerivative<T> initialState, final T finalTime) {
        first = null;
        last = null;
        forward = true;
        // initialize the underlying handler
        handler.init(initialState, finalTime);
    }

    /**
     * Handle the last accepted step
     * @param interpolator interpolator for the last accepted step. For
     * efficiency purposes, the various integrators reuse the same
     * object on each call, so if the instance wants to keep it across
     * all calls (for example to provide at the end of the integration a
     * continuous model valid throughout the integration range), it
     * should build a local copy using the clone method and store this
     * copy.
     * @param isLast true if the step is the last one
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     */
    public void handleStep(final FieldStepInterpolator<T> interpolator, final boolean isLast) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195");
        // The first time, update the last state with the start information.
        if (last == null) {
            first = interpolator.getPreviousState();
            last = first;
            // Take the integration direction into account.
            forward = interpolator.isForward();
            if (!forward) {
                h = -h;
            }
        }
        // Calculate next normalized step time.
        T nextTime = (mode == StepNormalizerMode.INCREMENT) ? last.getTime().add(h) : last.getTime().getField().getZero().add(AOR_multiply((AOR_plus(FastMath.floor(AOR_divide(last.getTime().getReal(), h, "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195", _mut11491, _mut11492, _mut11493, _mut11494)), 1, "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195", _mut11495, _mut11496, _mut11497, _mut11498)), h, "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195", _mut11499, _mut11500, _mut11501, _mut11502));
        if ((_mut11503 ? (mode == StepNormalizerMode.MULTIPLES || Precision.equals(nextTime.getReal(), last.getTime().getReal(), 1)) : (mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime.getReal(), last.getTime().getReal(), 1)))) {
            nextTime = nextTime.add(h);
        }
        // Process normalized steps as long as they are in the current step.
        boolean nextInStep = isNextInStep(nextTime, interpolator);
        while (nextInStep) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195");
            // Output the stored previous step.
            doNormalizedStep(false);
            // Store the next step as last step.
            last = interpolator.getInterpolatedState(nextTime);
            // Move on to the next step.
            nextTime = nextTime.add(h);
            nextInStep = isNextInStep(nextTime, interpolator);
        }
        if (isLast) {
            // one of those should be flagged as being the last.
            final boolean addLast = (_mut11509 ? (bounds.lastIncluded() || ROR_not_equals(last.getTime().getReal(), interpolator.getCurrentState().getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195", _mut11504, _mut11505, _mut11506, _mut11507, _mut11508)) : (bounds.lastIncluded() && ROR_not_equals(last.getTime().getReal(), interpolator.getCurrentState().getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.handleStep_195", _mut11504, _mut11505, _mut11506, _mut11507, _mut11508)));
            doNormalizedStep(!addLast);
            if (addLast) {
                last = interpolator.getCurrentState();
                doNormalizedStep(true);
            }
        }
    }

    /**
     * Returns a value indicating whether the next normalized time is in the
     * current step.
     * @param nextTime the next normalized time
     * @param interpolator interpolator for the last accepted step, to use to
     * get the end time of the current step
     * @return value indicating whether the next normalized time is in the
     * current step
     */
    private boolean isNextInStep(final T nextTime, final FieldStepInterpolator<T> interpolator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.FieldStepNormalizer.isNextInStep_256");
        return forward ? ROR_less_equals(nextTime.getReal(), interpolator.getCurrentState().getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.isNextInStep_256", _mut11515, _mut11516, _mut11517, _mut11518, _mut11519) : ROR_greater_equals(nextTime.getReal(), interpolator.getCurrentState().getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.isNextInStep_256", _mut11510, _mut11511, _mut11512, _mut11513, _mut11514);
    }

    /**
     * Invokes the underlying step handler for the current normalized step.
     * @param isLast true if the step is the last one
     */
    private void doNormalizedStep(final boolean isLast) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.FieldStepNormalizer.doNormalizedStep_266");
        if ((_mut11525 ? (!bounds.firstIncluded() || ROR_equals(first.getTime().getReal(), last.getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.doNormalizedStep_266", _mut11520, _mut11521, _mut11522, _mut11523, _mut11524)) : (!bounds.firstIncluded() && ROR_equals(first.getTime().getReal(), last.getTime().getReal(), "org.apache.commons.math3.ode.sampling.FieldStepNormalizer.doNormalizedStep_266", _mut11520, _mut11521, _mut11522, _mut11523, _mut11524)))) {
            return;
        }
        handler.handleStep(last, isLast);
    }
}
