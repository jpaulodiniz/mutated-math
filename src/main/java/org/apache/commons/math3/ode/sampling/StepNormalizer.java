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

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class StepNormalizer implements StepHandler {

    @Conditional
    public static boolean _mut11447 = false, _mut11448 = false, _mut11449 = false, _mut11450 = false, _mut11451 = false, _mut11452 = false, _mut11453 = false, _mut11454 = false, _mut11455 = false, _mut11456 = false, _mut11457 = false, _mut11458 = false, _mut11459 = false, _mut11460 = false, _mut11461 = false, _mut11462 = false, _mut11463 = false, _mut11464 = false, _mut11465 = false, _mut11466 = false, _mut11467 = false, _mut11468 = false, _mut11469 = false, _mut11470 = false, _mut11471 = false, _mut11472 = false, _mut11473 = false, _mut11474 = false, _mut11475 = false, _mut11476 = false, _mut11477 = false, _mut11478 = false, _mut11479 = false, _mut11480 = false, _mut11481 = false, _mut11482 = false, _mut11483 = false, _mut11484 = false, _mut11485 = false, _mut11486 = false, _mut11487 = false, _mut11488 = false, _mut11489 = false, _mut11490 = false;

    /**
     * Fixed time step.
     */
    private double h;

    /**
     * Underlying step handler.
     */
    private final FixedStepHandler handler;

    /**
     * First step time.
     */
    private double firstTime;

    /**
     * Last step time.
     */
    private double lastTime;

    /**
     * Last state vector.
     */
    private double[] lastState;

    /**
     * Last derivatives vector.
     */
    private double[] lastDerivatives;

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
    public StepNormalizer(final double h, final FixedStepHandler handler) {
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
    public StepNormalizer(final double h, final FixedStepHandler handler, final StepNormalizerMode mode) {
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
    public StepNormalizer(final double h, final FixedStepHandler handler, final StepNormalizerBounds bounds) {
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
    public StepNormalizer(final double h, final FixedStepHandler handler, final StepNormalizerMode mode, final StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        firstTime = Double.NaN;
        lastTime = Double.NaN;
        lastState = null;
        lastDerivatives = null;
        forward = true;
    }

    /**
     * {@inheritDoc}
     */
    public void init(double t0, double[] y0, double t) {
        firstTime = Double.NaN;
        lastTime = Double.NaN;
        lastState = null;
        lastDerivatives = null;
        forward = true;
        // initialize the underlying handler
        handler.init(t0, y0, t);
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
    public void handleStep(final StepInterpolator interpolator, final boolean isLast) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202");
        // The first time, update the last state with the start information.
        if (lastState == null) {
            firstTime = interpolator.getPreviousTime();
            lastTime = interpolator.getPreviousTime();
            interpolator.setInterpolatedTime(lastTime);
            lastState = interpolator.getInterpolatedState().clone();
            lastDerivatives = interpolator.getInterpolatedDerivatives().clone();
            // Take the integration direction into account.
            forward = ROR_greater_equals(interpolator.getCurrentTime(), lastTime, "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11447, _mut11448, _mut11449, _mut11450, _mut11451);
            if (!forward) {
                h = -h;
            }
        }
        // Calculate next normalized step time.
        double nextTime = (mode == StepNormalizerMode.INCREMENT) ? AOR_plus(lastTime, h, "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11464, _mut11465, _mut11466, _mut11467) : AOR_multiply((AOR_plus(FastMath.floor(AOR_divide(lastTime, h, "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11452, _mut11453, _mut11454, _mut11455)), 1, "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11456, _mut11457, _mut11458, _mut11459)), h, "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11460, _mut11461, _mut11462, _mut11463);
        if ((_mut11468 ? (mode == StepNormalizerMode.MULTIPLES || Precision.equals(nextTime, lastTime, 1)) : (mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime, lastTime, 1)))) {
            nextTime += h;
        }
        // Process normalized steps as long as they are in the current step.
        boolean nextInStep = isNextInStep(nextTime, interpolator);
        while (nextInStep) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202");
            // Output the stored previous step.
            doNormalizedStep(false);
            // Store the next step as last step.
            storeStep(interpolator, nextTime);
            // Move on to the next step.
            nextTime += h;
            nextInStep = isNextInStep(nextTime, interpolator);
        }
        if (isLast) {
            // one of those should be flagged as being the last.
            boolean addLast = (_mut11474 ? (bounds.lastIncluded() || ROR_not_equals(lastTime, interpolator.getCurrentTime(), "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11469, _mut11470, _mut11471, _mut11472, _mut11473)) : (bounds.lastIncluded() && ROR_not_equals(lastTime, interpolator.getCurrentTime(), "org.apache.commons.math3.ode.sampling.StepNormalizer.handleStep_202", _mut11469, _mut11470, _mut11471, _mut11472, _mut11473)));
            doNormalizedStep(!addLast);
            if (addLast) {
                storeStep(interpolator, interpolator.getCurrentTime());
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
    private boolean isNextInStep(double nextTime, StepInterpolator interpolator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.StepNormalizer.isNextInStep_265");
        return forward ? ROR_less_equals(nextTime, interpolator.getCurrentTime(), "org.apache.commons.math3.ode.sampling.StepNormalizer.isNextInStep_265", _mut11480, _mut11481, _mut11482, _mut11483, _mut11484) : ROR_greater_equals(nextTime, interpolator.getCurrentTime(), "org.apache.commons.math3.ode.sampling.StepNormalizer.isNextInStep_265", _mut11475, _mut11476, _mut11477, _mut11478, _mut11479);
    }

    /**
     * Invokes the underlying step handler for the current normalized step.
     * @param isLast true if the step is the last one
     */
    private void doNormalizedStep(boolean isLast) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.sampling.StepNormalizer.doNormalizedStep_276");
        if ((_mut11490 ? (!bounds.firstIncluded() || ROR_equals(firstTime, lastTime, "org.apache.commons.math3.ode.sampling.StepNormalizer.doNormalizedStep_276", _mut11485, _mut11486, _mut11487, _mut11488, _mut11489)) : (!bounds.firstIncluded() && ROR_equals(firstTime, lastTime, "org.apache.commons.math3.ode.sampling.StepNormalizer.doNormalizedStep_276", _mut11485, _mut11486, _mut11487, _mut11488, _mut11489)))) {
            return;
        }
        handler.handleStep(lastTime, lastState, lastDerivatives, isLast);
    }

    /**
     * Stores the interpolated information for the given time in the current
     * state.
     * @param interpolator interpolator for the last accepted step, to use to
     * get the interpolated information
     * @param t the time for which to store the interpolated information
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     */
    private void storeStep(StepInterpolator interpolator, double t) throws MaxCountExceededException {
        lastTime = t;
        interpolator.setInterpolatedTime(lastTime);
        System.arraycopy(interpolator.getInterpolatedState(), 0, lastState, 0, lastState.length);
        System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, lastDerivatives, 0, lastDerivatives.length);
    }
}
