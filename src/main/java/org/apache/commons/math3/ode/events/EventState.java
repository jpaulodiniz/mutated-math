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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
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
 * @since 1.2
 */
public class EventState {

    @Conditional
    public static boolean _mut12454 = false, _mut12455 = false, _mut12456 = false, _mut12457 = false, _mut12458 = false, _mut12459 = false, _mut12460 = false, _mut12461 = false, _mut12462 = false, _mut12463 = false, _mut12464 = false, _mut12465 = false, _mut12466 = false, _mut12467 = false, _mut12468 = false, _mut12469 = false, _mut12470 = false, _mut12471 = false, _mut12472 = false, _mut12473 = false, _mut12474 = false, _mut12475 = false, _mut12476 = false, _mut12477 = false, _mut12478 = false, _mut12479 = false, _mut12480 = false, _mut12481 = false, _mut12482 = false, _mut12483 = false, _mut12484 = false, _mut12485 = false, _mut12486 = false, _mut12487 = false, _mut12488 = false, _mut12489 = false, _mut12490 = false, _mut12491 = false, _mut12492 = false, _mut12493 = false, _mut12494 = false, _mut12495 = false, _mut12496 = false, _mut12497 = false, _mut12498 = false, _mut12499 = false, _mut12500 = false, _mut12501 = false, _mut12502 = false, _mut12503 = false, _mut12504 = false, _mut12505 = false, _mut12506 = false, _mut12507 = false, _mut12508 = false, _mut12509 = false, _mut12510 = false, _mut12511 = false, _mut12512 = false, _mut12513 = false, _mut12514 = false, _mut12515 = false, _mut12516 = false, _mut12517 = false, _mut12518 = false, _mut12519 = false, _mut12520 = false, _mut12521 = false, _mut12522 = false, _mut12523 = false, _mut12524 = false, _mut12525 = false, _mut12526 = false, _mut12527 = false, _mut12528 = false, _mut12529 = false, _mut12530 = false, _mut12531 = false, _mut12532 = false, _mut12533 = false, _mut12534 = false, _mut12535 = false, _mut12536 = false, _mut12537 = false, _mut12538 = false, _mut12539 = false, _mut12540 = false, _mut12541 = false, _mut12542 = false, _mut12543 = false, _mut12544 = false, _mut12545 = false, _mut12546 = false, _mut12547 = false, _mut12548 = false, _mut12549 = false, _mut12550 = false, _mut12551 = false, _mut12552 = false, _mut12553 = false, _mut12554 = false, _mut12555 = false, _mut12556 = false, _mut12557 = false, _mut12558 = false, _mut12559 = false, _mut12560 = false, _mut12561 = false, _mut12562 = false, _mut12563 = false, _mut12564 = false, _mut12565 = false, _mut12566 = false, _mut12567 = false, _mut12568 = false, _mut12569 = false, _mut12570 = false, _mut12571 = false, _mut12572 = false, _mut12573 = false, _mut12574 = false, _mut12575 = false, _mut12576 = false, _mut12577 = false, _mut12578 = false, _mut12579 = false, _mut12580 = false, _mut12581 = false, _mut12582 = false, _mut12583 = false, _mut12584 = false, _mut12585 = false, _mut12586 = false, _mut12587 = false, _mut12588 = false, _mut12589 = false, _mut12590 = false, _mut12591 = false, _mut12592 = false, _mut12593 = false, _mut12594 = false, _mut12595 = false, _mut12596 = false, _mut12597 = false, _mut12598 = false, _mut12599 = false, _mut12600 = false, _mut12601 = false, _mut12602 = false, _mut12603 = false, _mut12604 = false, _mut12605 = false, _mut12606 = false, _mut12607 = false, _mut12608 = false, _mut12609 = false, _mut12610 = false, _mut12611 = false, _mut12612 = false;

    /**
     * Event handler.
     */
    private final EventHandler handler;

    /**
     * Maximal time interval between events handler checks.
     */
    private final double maxCheckInterval;

    /**
     * Convergence threshold for event localization.
     */
    private final double convergence;

    /**
     * Upper limit in the iteration count for event localization.
     */
    private final int maxIterationCount;

    /**
     * Equation being integrated.
     */
    private ExpandableStatefulODE expandable;

    /**
     * Time at the beginning of the step.
     */
    private double t0;

    /**
     * Value of the events handler at the beginning of the step.
     */
    private double g0;

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
    private double pendingEventTime;

    /**
     * Occurrence time of the previous event.
     */
    private double previousEventTime;

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
    private EventHandler.Action nextAction;

    /**
     * Root-finding algorithm to use to detect state events.
     */
    private final UnivariateSolver solver;

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
    public EventState(final EventHandler handler, final double maxCheckInterval, final double convergence, final int maxIterationCount, final UnivariateSolver solver) {
        this.handler = handler;
        this.maxCheckInterval = maxCheckInterval;
        this.convergence = FastMath.abs(convergence);
        this.maxIterationCount = maxIterationCount;
        this.solver = solver;
        // some dummy values ...
        expandable = null;
        t0 = Double.NaN;
        g0 = Double.NaN;
        g0Positive = true;
        pendingEvent = false;
        pendingEventTime = Double.NaN;
        previousEventTime = Double.NaN;
        increasing = true;
        nextAction = EventHandler.Action.CONTINUE;
    }

    /**
     * Get the underlying event handler.
     * @return underlying event handler
     */
    public EventHandler getEventHandler() {
        return handler;
    }

    /**
     * Set the equation.
     * @param expandable equation being integrated
     */
    public void setExpandable(final ExpandableStatefulODE expandable) {
        this.expandable = expandable;
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
    public double getConvergence() {
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
    public void reinitializeBegin(final StepInterpolator interpolator) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166");
        t0 = interpolator.getPreviousTime();
        interpolator.setInterpolatedTime(t0);
        g0 = handler.g(t0, getCompleteState(interpolator));
        if (ROR_equals(g0, 0, "org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166", _mut12454, _mut12455, _mut12456, _mut12457, _mut12458)) {
            // we will use the sign slightly after step beginning to force ignoring this zero
            final double epsilon = FastMath.max(solver.getAbsoluteAccuracy(), FastMath.abs(AOR_multiply(solver.getRelativeAccuracy(), t0, "org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166", _mut12459, _mut12460, _mut12461, _mut12462)));
            final double tStart = AOR_plus(t0, AOR_multiply(0.5, epsilon, "org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166", _mut12463, _mut12464, _mut12465, _mut12466), "org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166", _mut12467, _mut12468, _mut12469, _mut12470);
            interpolator.setInterpolatedTime(tStart);
            g0 = handler.g(tStart, getCompleteState(interpolator));
        }
        g0Positive = ROR_greater_equals(g0, 0, "org.apache.commons.math3.ode.events.EventState.reinitializeBegin_166", _mut12471, _mut12472, _mut12473, _mut12474, _mut12475);
    }

    /**
     * Get the complete state (primary and secondary).
     * @param interpolator interpolator to use
     * @return complete state
     */
    private double[] getCompleteState(final StepInterpolator interpolator) {
        final double[] complete = new double[expandable.getTotalDimension()];
        expandable.getPrimaryMapper().insertEquationData(interpolator.getInterpolatedState(), complete);
        int index = 0;
        for (EquationsMapper secondary : expandable.getSecondaryMappers()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.getCompleteState_200");
            secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index++), complete);
        }
        return complete;
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
    public boolean evaluateStep(final StepInterpolator interpolator) throws MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.value_240");
        try {
            forward = interpolator.isForward();
            final double t1 = interpolator.getCurrentTime();
            final double dt = AOR_minus(t1, t0, "org.apache.commons.math3.ode.events.EventState.evaluateStep_224", _mut12476, _mut12477, _mut12478, _mut12479);
            if (ROR_less(FastMath.abs(dt), convergence, "org.apache.commons.math3.ode.events.EventState.evaluateStep_224", _mut12480, _mut12481, _mut12482, _mut12483, _mut12484)) {
                // we cannot do anything on such a small step, don't trigger any events
                return false;
            }
            final int n = FastMath.max(1, (int) FastMath.ceil(AOR_divide(FastMath.abs(dt), maxCheckInterval, "org.apache.commons.math3.ode.events.EventState.evaluateStep_224", _mut12485, _mut12486, _mut12487, _mut12488)));
            final double h = AOR_divide(dt, n, "org.apache.commons.math3.ode.events.EventState.evaluateStep_224", _mut12489, _mut12490, _mut12491, _mut12492);
            final UnivariateFunction f = new UnivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(final double t) throws LocalMaxCountExceededException {
                    try {
                        interpolator.setInterpolatedTime(t);
                        return handler.g(t, getCompleteState(interpolator));
                    } catch (MaxCountExceededException mcee) {
                        throw new LocalMaxCountExceededException(mcee);
                    }
                }
            };
            double ta = t0;
            double ga = g0;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12582, _mut12583, _mut12584, _mut12585, _mut12586); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.value_240");
                // evaluate handler value at the end of the substep
                final double tb = (ROR_equals(i, AOR_minus(n, 1, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12493, _mut12494, _mut12495, _mut12496), "org.apache.commons.math3.ode.events.EventState.value_240", _mut12497, _mut12498, _mut12499, _mut12500, _mut12501)) ? t1 : AOR_plus(t0, AOR_multiply((AOR_plus(i, 1, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12502, _mut12503, _mut12504, _mut12505)), h, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12506, _mut12507, _mut12508, _mut12509), "org.apache.commons.math3.ode.events.EventState.value_240", _mut12510, _mut12511, _mut12512, _mut12513);
                interpolator.setInterpolatedTime(tb);
                final double gb = handler.g(tb, getCompleteState(interpolator));
                // check events occurrence
                if (g0Positive ^ (ROR_greater_equals(gb, 0, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12514, _mut12515, _mut12516, _mut12517, _mut12518))) {
                    // variation direction, with respect to the integration direction
                    increasing = ROR_greater_equals(gb, ga, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12519, _mut12520, _mut12521, _mut12522, _mut12523);
                    // find the event time making sure we select a solution just at or past the exact root
                    final double root;
                    if (solver instanceof BracketedUnivariateSolver<?>) {
                        @SuppressWarnings("unchecked")
                        BracketedUnivariateSolver<UnivariateFunction> bracketing = (BracketedUnivariateSolver<UnivariateFunction>) solver;
                        root = forward ? bracketing.solve(maxIterationCount, f, ta, tb, AllowedSolution.RIGHT_SIDE) : bracketing.solve(maxIterationCount, f, tb, ta, AllowedSolution.LEFT_SIDE);
                    } else {
                        final double baseRoot = forward ? solver.solve(maxIterationCount, f, ta, tb) : solver.solve(maxIterationCount, f, tb, ta);
                        final int remainingEval = AOR_minus(maxIterationCount, solver.getEvaluations(), "org.apache.commons.math3.ode.events.EventState.value_240", _mut12524, _mut12525, _mut12526, _mut12527);
                        BracketedUnivariateSolver<UnivariateFunction> bracketing = new PegasusSolver(solver.getRelativeAccuracy(), solver.getAbsoluteAccuracy());
                        root = forward ? UnivariateSolverUtils.forceSide(remainingEval, f, bracketing, baseRoot, ta, tb, AllowedSolution.RIGHT_SIDE) : UnivariateSolverUtils.forceSide(remainingEval, f, bracketing, baseRoot, tb, ta, AllowedSolution.LEFT_SIDE);
                    }
                    if ((_mut12547 ? ((_mut12537 ? ((!Double.isNaN(previousEventTime)) || (ROR_less_equals(FastMath.abs(AOR_minus(root, ta, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12528, _mut12529, _mut12530, _mut12531)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12532, _mut12533, _mut12534, _mut12535, _mut12536))) : ((!Double.isNaN(previousEventTime)) && (ROR_less_equals(FastMath.abs(AOR_minus(root, ta, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12528, _mut12529, _mut12530, _mut12531)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12532, _mut12533, _mut12534, _mut12535, _mut12536)))) || (ROR_less_equals(FastMath.abs(AOR_minus(root, previousEventTime, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12538, _mut12539, _mut12540, _mut12541)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12542, _mut12543, _mut12544, _mut12545, _mut12546))) : ((_mut12537 ? ((!Double.isNaN(previousEventTime)) || (ROR_less_equals(FastMath.abs(AOR_minus(root, ta, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12528, _mut12529, _mut12530, _mut12531)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12532, _mut12533, _mut12534, _mut12535, _mut12536))) : ((!Double.isNaN(previousEventTime)) && (ROR_less_equals(FastMath.abs(AOR_minus(root, ta, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12528, _mut12529, _mut12530, _mut12531)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12532, _mut12533, _mut12534, _mut12535, _mut12536)))) && (ROR_less_equals(FastMath.abs(AOR_minus(root, previousEventTime, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12538, _mut12539, _mut12540, _mut12541)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12542, _mut12543, _mut12544, _mut12545, _mut12546))))) {
                        // crosses the axis several times
                        do {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.value_240");
                            ta = forward ? AOR_plus(ta, convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12562, _mut12563, _mut12564, _mut12565) : AOR_minus(ta, convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12558, _mut12559, _mut12560, _mut12561);
                            ga = f.value(ta);
                        } while ((_mut12576 ? ((g0Positive ^ (ROR_greater_equals(ga, 0, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12566, _mut12567, _mut12568, _mut12569, _mut12570))) || (forward ^ (ROR_greater_equals(ta, tb, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12571, _mut12572, _mut12573, _mut12574, _mut12575)))) : ((g0Positive ^ (ROR_greater_equals(ga, 0, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12566, _mut12567, _mut12568, _mut12569, _mut12570))) && (forward ^ (ROR_greater_equals(ta, tb, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12571, _mut12572, _mut12573, _mut12574, _mut12575))))));
                        if (forward ^ (ROR_greater_equals(ta, tb, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12577, _mut12578, _mut12579, _mut12580, _mut12581))) {
                            // we were able to skip this spurious root
                            --i;
                        } else {
                            // maybe we have two very close roots
                            pendingEventTime = root;
                            pendingEvent = true;
                            return true;
                        }
                    } else if ((_mut12557 ? (Double.isNaN(previousEventTime) && (ROR_greater(FastMath.abs(AOR_minus(previousEventTime, root, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12548, _mut12549, _mut12550, _mut12551)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12552, _mut12553, _mut12554, _mut12555, _mut12556))) : (Double.isNaN(previousEventTime) || (ROR_greater(FastMath.abs(AOR_minus(previousEventTime, root, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12548, _mut12549, _mut12550, _mut12551)), convergence, "org.apache.commons.math3.ode.events.EventState.value_240", _mut12552, _mut12553, _mut12554, _mut12555, _mut12556))))) {
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
            pendingEventTime = Double.NaN;
            return false;
        } catch (LocalMaxCountExceededException lmcee) {
            throw lmcee.getException();
        }
    }

    /**
     * Get the occurrence time of the event triggered in the current step.
     * @return occurrence time of the event triggered in the current
     * step or infinity if no events are triggered
     */
    public double getEventTime() {
        return pendingEvent ? pendingEventTime : (forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    }

    /**
     * Acknowledge the fact the step has been accepted by the integrator.
     * @param t value of the independent <i>time</i> variable at the
     * end of the step
     * @param y array containing the current value of the state vector
     * at the end of the step
     */
    public void stepAccepted(final double t, final double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.stepAccepted_358");
        t0 = t;
        g0 = handler.g(t, y);
        if ((_mut12596 ? (pendingEvent || (ROR_less_equals(FastMath.abs(AOR_minus(pendingEventTime, t, "org.apache.commons.math3.ode.events.EventState.stepAccepted_358", _mut12587, _mut12588, _mut12589, _mut12590)), convergence, "org.apache.commons.math3.ode.events.EventState.stepAccepted_358", _mut12591, _mut12592, _mut12593, _mut12594, _mut12595))) : (pendingEvent && (ROR_less_equals(FastMath.abs(AOR_minus(pendingEventTime, t, "org.apache.commons.math3.ode.events.EventState.stepAccepted_358", _mut12587, _mut12588, _mut12589, _mut12590)), convergence, "org.apache.commons.math3.ode.events.EventState.stepAccepted_358", _mut12591, _mut12592, _mut12593, _mut12594, _mut12595))))) {
            // force the sign to its value "just after the event"
            previousEventTime = t;
            g0Positive = increasing;
            nextAction = handler.eventOccurred(t, y, !(increasing ^ forward));
        } else {
            g0Positive = ROR_greater_equals(g0, 0, "org.apache.commons.math3.ode.events.EventState.stepAccepted_358", _mut12597, _mut12598, _mut12599, _mut12600, _mut12601);
            nextAction = EventHandler.Action.CONTINUE;
        }
    }

    /**
     * Check if the integration should be stopped at the end of the
     * current step.
     * @return true if the integration should be stopped
     */
    public boolean stop() {
        return nextAction == EventHandler.Action.STOP;
    }

    /**
     * Let the event handler reset the state if it wants.
     * @param t value of the independent <i>time</i> variable at the
     * beginning of the next step
     * @param y array were to put the desired state vector at the beginning
     * of the next step
     * @return true if the integrator should reset the derivatives too
     */
    public boolean reset(final double t, final double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.events.EventState.reset_389");
        if (!((_mut12611 ? (pendingEvent || (ROR_less_equals(FastMath.abs(AOR_minus(pendingEventTime, t, "org.apache.commons.math3.ode.events.EventState.reset_389", _mut12602, _mut12603, _mut12604, _mut12605)), convergence, "org.apache.commons.math3.ode.events.EventState.reset_389", _mut12606, _mut12607, _mut12608, _mut12609, _mut12610))) : (pendingEvent && (ROR_less_equals(FastMath.abs(AOR_minus(pendingEventTime, t, "org.apache.commons.math3.ode.events.EventState.reset_389", _mut12602, _mut12603, _mut12604, _mut12605)), convergence, "org.apache.commons.math3.ode.events.EventState.reset_389", _mut12606, _mut12607, _mut12608, _mut12609, _mut12610)))))) {
            return false;
        }
        if (nextAction == EventHandler.Action.RESET_STATE) {
            handler.resetState(t, y);
        }
        pendingEvent = false;
        pendingEventTime = Double.NaN;
        return (_mut12612 ? ((nextAction == EventHandler.Action.RESET_STATE) && (nextAction == EventHandler.Action.RESET_DERIVATIVES)) : ((nextAction == EventHandler.Action.RESET_STATE) || (nextAction == EventHandler.Action.RESET_DERIVATIVES)));
    }

    /**
     * Local wrapper to propagate exceptions.
     */
    private static class LocalMaxCountExceededException extends RuntimeException {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 20120901L;

        /**
         * Wrapped exception.
         */
        private final MaxCountExceededException wrapped;

        /**
         * Simple constructor.
         * @param exception exception to wrap
         */
        LocalMaxCountExceededException(final MaxCountExceededException exception) {
            wrapped = exception;
        }

        /**
         * Get the wrapped exception.
         * @return wrapped exception
         */
        public MaxCountExceededException getException() {
            return wrapped;
        }
    }
}
