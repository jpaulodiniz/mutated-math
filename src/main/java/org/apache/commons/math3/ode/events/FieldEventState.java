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
package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedRealFieldUnivariateSolver;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class handles the state for one {@link EventHandler
 * event handler} during integration steps.
 *
 * <p>Each time the integrator proposes a step, the event handler
 * switching function should be checked. This class handles the state
 * of one handler during one integration step, with references to the
 * state at the end of the preceding step. This information is used to
 * decide if the handler should trigger an event or not during the
 * proposed step.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldEventState<T extends RealFieldElement<T>> {

    @Conditional
    public static boolean _mut12297 = false, _mut12298 = false, _mut12299 = false, _mut12300 = false, _mut12301 = false, _mut12302 = false, _mut12303 = false, _mut12304 = false, _mut12305 = false, _mut12306 = false, _mut12307 = false, _mut12308 = false, _mut12309 = false, _mut12310 = false, _mut12311 = false, _mut12312 = false, _mut12313 = false, _mut12314 = false, _mut12315 = false, _mut12316 = false, _mut12317 = false, _mut12318 = false, _mut12319 = false, _mut12320 = false, _mut12321 = false, _mut12322 = false, _mut12323 = false, _mut12324 = false, _mut12325 = false, _mut12326 = false, _mut12327 = false, _mut12328 = false, _mut12329 = false, _mut12330 = false, _mut12331 = false, _mut12332 = false, _mut12333 = false, _mut12334 = false, _mut12335 = false, _mut12336 = false, _mut12337 = false, _mut12338 = false, _mut12339 = false, _mut12340 = false, _mut12341 = false, _mut12342 = false, _mut12343 = false, _mut12344 = false, _mut12345 = false, _mut12346 = false, _mut12347 = false, _mut12348 = false, _mut12349 = false, _mut12350 = false, _mut12351 = false, _mut12352 = false, _mut12353 = false, _mut12354 = false, _mut12355 = false, _mut12356 = false, _mut12357 = false, _mut12358 = false, _mut12359 = false, _mut12360 = false, _mut12361 = false, _mut12362 = false, _mut12363 = false, _mut12364 = false, _mut12365 = false, _mut12366 = false, _mut12367 = false, _mut12368 = false, _mut12369 = false, _mut12370 = false, _mut12371 = false, _mut12372 = false, _mut12373 = false, _mut12374 = false, _mut12375 = false, _mut12376 = false, _mut12377 = false, _mut12378 = false, _mut12379 = false, _mut12380 = false, _mut12381 = false, _mut12382 = false, _mut12383 = false, _mut12384 = false, _mut12385 = false, _mut12386 = false, _mut12387 = false, _mut12388 = false, _mut12389 = false, _mut12390 = false, _mut12391 = false, _mut12392 = false, _mut12393 = false, _mut12394 = false, _mut12395 = false, _mut12396 = false, _mut12397 = false, _mut12398 = false;

    /**
     * Event handler.
     */
    private final FieldEventHandler<T> handler;

    /**
     * Maximal time interval between events handler checks.
     */
    private final double maxCheckInterval;

    /**
     * Convergence threshold for event localization.
     */
    private final T convergence;

    /**
     * Upper limit in the iteration count for event localization.
     */
    private final int maxIterationCount;

    /**
     * Time at the beginning of the step.
     */
    private T t0;

    /**
     * Value of the events handler at the beginning of the step.
     */
    private T g0;

    /**
     * Simulated sign of g0 (we cheat when crossing events).
     */
    private boolean g0Positive;

    /**
     * Indicator of event expected during the step.
     */
    private boolean pendingEvent;

    /**
     * Occurrence time of the pending event.
     */
    private T pendingEventTime;

    /**
     * Occurrence time of the previous event.
     */
    private T previousEventTime;

    /**
     * Integration direction.
     */
    private boolean forward;

    /**
     * Variation direction around pending event.
     *  (this is considered with respect to the integration direction)
     */
    private boolean increasing;

    /**
     * Next action indicator.
     */
    private Action nextAction;

    /**
     * Root-finding algorithm to use to detect state events.
     */
    private final BracketedRealFieldUnivariateSolver<T> solver;

    /**
     * Simple constructor.
     * @param handler event handler
     * @param maxCheckInterval maximal time interval between switching
     * function checks (this interval prevents missing sign changes in
     * case the integration steps becomes very large)
     * @param convergence convergence threshold in the event time search
     * @param maxIterationCount upper limit of the iteration count in
     * the event time search
     * @param solver Root-finding algorithm to use to detect state events
     */
    public FieldEventState(final FieldEventHandler<T> handler, final double maxCheckInterval, final T convergence, final int maxIterationCount, final BracketedRealFieldUnivariateSolver<T> solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = convergence.abs();
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
        // some dummy values ...
        t0 = null;
        g0 = null;
        g0Positive = true;
        pendingEvent = false;
        pendingEventTime = null;
        previousEventTime = null;
        increasing = true;
        nextAction = Action.CONTINUE;
    }

    /**
     * Get the underlying event handler.
     * @return underlying event handler
     */
    public FieldEventHandler<T> getEventHandler() {
        return handler;
    }

    /**
     * Get the maximal time interval between events handler checks.
     * @return maximal time interval between events handler checks
     */
    public double getMaxCheckInterval() {
        return maxCheckInterval;
    }

    /**
     * Get the convergence threshold for event localization.
     * @return convergence threshold for event localization
     */
    public T getConvergence() {
        return convergence;
    }

    /**
     * Get the upper limit in the iteration count for event localization.
     * @return upper limit in the iteration count for event localization
     */
    public int getMaxIterationCount() {
        return maxIterationCount;
    }

    /**
     * Reinitialize the beginning of the step.
     * @param interpolator valid for the current step
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     */
    public void reinitializeBegin(final FieldStepInterpolator<T> interpolator) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.reinitializeBegin_154");
        final FieldODEStateAndDerivative<T> s0 = interpolator.getPreviousState();
        t0 = s0.getTime();
        g0 = handler.g(s0);
        if (ROR_equals(g0.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.reinitializeBegin_154", _mut12297, _mut12298, _mut12299, _mut12300, _mut12301)) {
            // we will use the sign slightly after step beginning to force ignoring this zero
            final double epsilon = FastMath.max(solver.getAbsoluteAccuracy().getReal(), FastMath.abs(solver.getRelativeAccuracy().multiply(t0).getReal()));
            final T tStart = t0.add(AOR_multiply(0.5, epsilon, "org.apache.commons.math3.ode.events.FieldEventState.reinitializeBegin_154", _mut12302, _mut12303, _mut12304, _mut12305));
            g0 = handler.g(interpolator.getInterpolatedState(tStart));
        }
        g0Positive = ROR_greater_equals(g0.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.reinitializeBegin_154", _mut12306, _mut12307, _mut12308, _mut12309, _mut12310);
    }

    /**
     * Evaluate the impact of the proposed step on the event handler.
     * @param interpolator step interpolator for the proposed step
     * @return true if the event handler triggers an event before
     * the end of the proposed step
     * @exception MaxCountExceededException if the interpolator throws one because
     * the number of functions evaluations is exceeded
     * @exception NoBracketingException if the event cannot be bracketed
     */
    public boolean evaluateStep(final FieldStepInterpolator<T> interpolator) throws MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.value_207");
        forward = interpolator.isForward();
        final FieldODEStateAndDerivative<T> s1 = interpolator.getCurrentState();
        final T t1 = s1.getTime();
        final T dt = t1.subtract(t0);
        if (ROR_less(dt.abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.evaluateStep_191", _mut12311, _mut12312, _mut12313, _mut12314, _mut12315)) {
            // we cannot do anything on such a small step, don't trigger any events
            return false;
        }
        final int n = FastMath.max(1, (int) FastMath.ceil(AOR_divide(FastMath.abs(dt.getReal()), maxCheckInterval, "org.apache.commons.math3.ode.events.FieldEventState.evaluateStep_191", _mut12316, _mut12317, _mut12318, _mut12319)));
        final T h = dt.divide(n);
        final RealFieldUnivariateFunction<T> f = new RealFieldUnivariateFunction<T>() {

            /**
             * {@inheritDoc}
             */
            public T value(final T t) {
                return handler.g(interpolator.getInterpolatedState(t));
            }
        };
        T ta = t0;
        T ga = g0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12377, _mut12378, _mut12379, _mut12380, _mut12381); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.value_207");
            // evaluate handler value at the end of the substep
            final T tb = (ROR_equals(i, AOR_minus(n, 1, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12320, _mut12321, _mut12322, _mut12323), "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12324, _mut12325, _mut12326, _mut12327, _mut12328)) ? t1 : t0.add(h.multiply(AOR_plus(i, 1, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12329, _mut12330, _mut12331, _mut12332)));
            final T gb = handler.g(interpolator.getInterpolatedState(tb));
            // check events occurrence
            if (g0Positive ^ (ROR_greater_equals(gb.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12333, _mut12334, _mut12335, _mut12336, _mut12337))) {
                // variation direction, with respect to the integration direction
                increasing = ROR_greater_equals(gb.subtract(ga).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12338, _mut12339, _mut12340, _mut12341, _mut12342);
                // find the event time making sure we select a solution just at or past the exact root
                final T root = forward ? solver.solve(maxIterationCount, f, ta, tb, AllowedSolution.RIGHT_SIDE) : solver.solve(maxIterationCount, f, tb, ta, AllowedSolution.LEFT_SIDE);
                if ((_mut12354 ? ((_mut12348 ? (previousEventTime != null || ROR_less_equals(root.subtract(ta).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12343, _mut12344, _mut12345, _mut12346, _mut12347)) : (previousEventTime != null && ROR_less_equals(root.subtract(ta).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12343, _mut12344, _mut12345, _mut12346, _mut12347))) || ROR_less_equals(root.subtract(previousEventTime).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12349, _mut12350, _mut12351, _mut12352, _mut12353)) : ((_mut12348 ? (previousEventTime != null || ROR_less_equals(root.subtract(ta).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12343, _mut12344, _mut12345, _mut12346, _mut12347)) : (previousEventTime != null && ROR_less_equals(root.subtract(ta).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12343, _mut12344, _mut12345, _mut12346, _mut12347))) && ROR_less_equals(root.subtract(previousEventTime).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12349, _mut12350, _mut12351, _mut12352, _mut12353)))) {
                    // crosses the axis several times
                    do {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.value_207");
                        ta = forward ? ta.add(convergence) : ta.subtract(convergence);
                        ga = f.value(ta);
                    } while ((_mut12371 ? ((g0Positive ^ (ROR_greater_equals(ga.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12361, _mut12362, _mut12363, _mut12364, _mut12365))) || (forward ^ (ROR_greater_equals(ta.subtract(tb).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12366, _mut12367, _mut12368, _mut12369, _mut12370)))) : ((g0Positive ^ (ROR_greater_equals(ga.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12361, _mut12362, _mut12363, _mut12364, _mut12365))) && (forward ^ (ROR_greater_equals(ta.subtract(tb).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12366, _mut12367, _mut12368, _mut12369, _mut12370))))));
                    if (forward ^ (ROR_greater_equals(ta.subtract(tb).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12372, _mut12373, _mut12374, _mut12375, _mut12376))) {
                        // we were able to skip this spurious root
                        --i;
                    } else {
                        // maybe we have two very close roots
                        pendingEventTime = root;
                        pendingEvent = true;
                        return true;
                    }
                } else if ((_mut12360 ? (previousEventTime == null && ROR_greater(previousEventTime.subtract(root).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12355, _mut12356, _mut12357, _mut12358, _mut12359)) : (previousEventTime == null || ROR_greater(previousEventTime.subtract(root).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.value_207", _mut12355, _mut12356, _mut12357, _mut12358, _mut12359)))) {
                    pendingEventTime = root;
                    pendingEvent = true;
                    return true;
                } else {
                    // no sign change: there is no event for now
                    ta = tb;
                    ga = gb;
                }
            } else {
                // no sign change: there is no event for now
                ta = tb;
                ga = gb;
            }
        }
        // no event during the whole step
        pendingEvent = false;
        pendingEventTime = null;
        return false;
    }

    /**
     * Get the occurrence time of the event triggered in the current step.
     * @return occurrence time of the event triggered in the current
     * step or infinity if no events are triggered
     */
    public T getEventTime() {
        return pendingEvent ? pendingEventTime : t0.getField().getZero().add(forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    /**
     * Acknowledge the fact the step has been accepted by the integrator.
     * @param state state at the end of the step
     */
    public void stepAccepted(final FieldODEStateAndDerivative<T> state) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.stepAccepted_294");
        t0 = state.getTime();
        g0 = handler.g(state);
        if ((_mut12387 ? (pendingEvent || ROR_less_equals(pendingEventTime.subtract(state.getTime()).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.stepAccepted_294", _mut12382, _mut12383, _mut12384, _mut12385, _mut12386)) : (pendingEvent && ROR_less_equals(pendingEventTime.subtract(state.getTime()).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.stepAccepted_294", _mut12382, _mut12383, _mut12384, _mut12385, _mut12386)))) {
            // force the sign to its value "just after the event"
            previousEventTime = state.getTime();
            g0Positive = increasing;
            nextAction = handler.eventOccurred(state, !(increasing ^ forward));
        } else {
            g0Positive = ROR_greater_equals(g0.getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.stepAccepted_294", _mut12388, _mut12389, _mut12390, _mut12391, _mut12392);
            nextAction = Action.CONTINUE;
        }
    }

    /**
     * Check if the integration should be stopped at the end of the
     * current step.
     * @return true if the integration should be stopped
     */
    public boolean stop() {
        return nextAction == Action.STOP;
    }

    /**
     * Let the event handler reset the state if it wants.
     * @param state state at the beginning of the next step
     * @return reset state (may by the same as initial state if only
     * derivatives should be reset), or null if nothing is reset
     */
    public FieldODEState<T> reset(final FieldODEStateAndDerivative<T> state) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.FieldEventState.reset_323");
        if (!((_mut12398 ? (pendingEvent || ROR_less_equals(pendingEventTime.subtract(state.getTime()).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.reset_323", _mut12393, _mut12394, _mut12395, _mut12396, _mut12397)) : (pendingEvent && ROR_less_equals(pendingEventTime.subtract(state.getTime()).abs().subtract(convergence).getReal(), 0, "org.apache.commons.math3.ode.events.FieldEventState.reset_323", _mut12393, _mut12394, _mut12395, _mut12396, _mut12397))))) {
            return null;
        }
        final FieldODEState<T> newState;
        if (nextAction == Action.RESET_STATE) {
            newState = handler.resetState(state);
        } else if (nextAction == Action.RESET_DERIVATIVES) {
            newState = state;
        } else {
            newState = null;
        }
        pendingEvent = false;
        pendingEventTime = null;
        return newState;
    }
}
