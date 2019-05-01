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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class ContinuousOutputFieldModel<T extends RealFieldElement<T>> implements FieldStepHandler<T> {

    @Conditional
    public static boolean _mut22510 = false, _mut22511 = false, _mut22512 = false, _mut22513 = false, _mut22514 = false, _mut22515 = false, _mut22516 = false, _mut22517 = false, _mut22518 = false, _mut22519 = false, _mut22520 = false, _mut22521 = false, _mut22522 = false, _mut22523 = false, _mut22524 = false, _mut22525 = false, _mut22526 = false, _mut22527 = false, _mut22528 = false, _mut22529 = false, _mut22530 = false, _mut22531 = false, _mut22532 = false, _mut22533 = false, _mut22534 = false, _mut22535 = false, _mut22536 = false, _mut22537 = false, _mut22538 = false, _mut22539 = false, _mut22540 = false, _mut22541 = false, _mut22542 = false, _mut22543 = false, _mut22544 = false, _mut22545 = false, _mut22546 = false, _mut22547 = false, _mut22548 = false, _mut22549 = false, _mut22550 = false, _mut22551 = false, _mut22552 = false, _mut22553 = false, _mut22554 = false, _mut22555 = false, _mut22556 = false, _mut22557 = false, _mut22558 = false, _mut22559 = false, _mut22560 = false, _mut22561 = false, _mut22562 = false, _mut22563 = false, _mut22564 = false, _mut22565 = false, _mut22566 = false, _mut22567 = false, _mut22568 = false, _mut22569 = false, _mut22570 = false, _mut22571 = false, _mut22572 = false, _mut22573 = false, _mut22574 = false, _mut22575 = false, _mut22576 = false, _mut22577 = false, _mut22578 = false, _mut22579 = false, _mut22580 = false, _mut22581 = false, _mut22582 = false, _mut22583 = false, _mut22584 = false, _mut22585 = false, _mut22586 = false, _mut22587 = false, _mut22588 = false, _mut22589 = false, _mut22590 = false, _mut22591 = false, _mut22592 = false, _mut22593 = false, _mut22594 = false, _mut22595 = false, _mut22596 = false, _mut22597 = false, _mut22598 = false, _mut22599 = false, _mut22600 = false, _mut22601 = false, _mut22602 = false, _mut22603 = false, _mut22604 = false, _mut22605 = false, _mut22606 = false, _mut22607 = false, _mut22608 = false, _mut22609 = false, _mut22610 = false, _mut22611 = false, _mut22612 = false, _mut22613 = false, _mut22614 = false, _mut22615 = false, _mut22616 = false, _mut22617 = false, _mut22618 = false, _mut22619 = false, _mut22620 = false, _mut22621 = false, _mut22622 = false, _mut22623 = false, _mut22624 = false, _mut22625 = false, _mut22626 = false, _mut22627 = false, _mut22628 = false, _mut22629 = false, _mut22630 = false, _mut22631 = false, _mut22632 = false, _mut22633 = false, _mut22634 = false, _mut22635 = false, _mut22636 = false, _mut22637 = false, _mut22638 = false, _mut22639 = false, _mut22640 = false, _mut22641 = false, _mut22642 = false, _mut22643 = false, _mut22644 = false, _mut22645 = false, _mut22646 = false, _mut22647 = false, _mut22648 = false, _mut22649 = false, _mut22650 = false, _mut22651 = false, _mut22652 = false, _mut22653 = false, _mut22654 = false, _mut22655 = false, _mut22656 = false, _mut22657 = false, _mut22658 = false, _mut22659 = false, _mut22660 = false, _mut22661 = false, _mut22662 = false, _mut22663 = false, _mut22664 = false, _mut22665 = false, _mut22666 = false, _mut22667 = false, _mut22668 = false, _mut22669 = false, _mut22670 = false, _mut22671 = false, _mut22672 = false;

    /**
     * Initial integration time.
     */
    private T initialTime;

    /**
     * Final integration time.
     */
    private T finalTime;

    /**
     * Integration direction indicator.
     */
    private boolean forward;

    /**
     * Current interpolator index.
     */
    private int index;

    /**
     * Steps table.
     */
    private List<FieldStepInterpolator<T>> steps;

    /**
     * Simple constructor.
     * Build an empty continuous output model.
     */
    public ContinuousOutputFieldModel() {
        steps = new ArrayList<FieldStepInterpolator<T>>();
        initialTime = null;
        finalTime = null;
        forward = true;
        index = 0;
    }

    /**
     * Append another model at the end of the instance.
     * @param model model to add at the end of the instance
     * @exception MathIllegalArgumentException if the model to append is not
     * compatible with the instance (dimension of the state vector,
     * propagation direction, hole between the dates)
     * @exception DimensionMismatchException if the dimensions of the states or
     * the number of secondary states do not match
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void append(final ContinuousOutputFieldModel<T> model) throws MathIllegalArgumentException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120");
        if (ROR_equals(model.steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120", _mut22510, _mut22511, _mut22512, _mut22513, _mut22514)) {
            return;
        }
        if (ROR_equals(steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120", _mut22515, _mut22516, _mut22517, _mut22518, _mut22519)) {
            initialTime = model.initialTime;
            forward = model.forward;
        } else {
            // safety checks
            final FieldODEStateAndDerivative<T> s1 = steps.get(0).getPreviousState();
            final FieldODEStateAndDerivative<T> s2 = model.steps.get(0).getPreviousState();
            checkDimensionsEquality(s1.getStateDimension(), s2.getStateDimension());
            checkDimensionsEquality(s1.getNumberOfSecondaryStates(), s2.getNumberOfSecondaryStates());
            for (int i = 0; ROR_less(i, s1.getNumberOfSecondaryStates(), "org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120", _mut22520, _mut22521, _mut22522, _mut22523, _mut22524); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120");
                checkDimensionsEquality(s1.getSecondaryStateDimension(i), s2.getSecondaryStateDimension(i));
            }
            if (forward ^ model.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH);
            }
            final FieldStepInterpolator<T> lastInterpolator = steps.get(index);
            final T current = lastInterpolator.getCurrentState().getTime();
            final T previous = lastInterpolator.getPreviousState().getTime();
            final T step = current.subtract(previous);
            final T gap = model.getInitialTime().subtract(current);
            if (ROR_greater(gap.abs().subtract(step.abs().multiply(1.0e-3)).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120", _mut22525, _mut22526, _mut22527, _mut22528, _mut22529)) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, gap.abs().getReal());
            }
        }
        for (FieldStepInterpolator<T> interpolator : model.steps) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120");
            steps.add(interpolator);
        }
        index = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.append_120", _mut22530, _mut22531, _mut22532, _mut22533);
        finalTime = (steps.get(index)).getCurrentState().getTime();
    }

    /**
     * Check dimensions equality.
     * @param d1 first dimension
     * @param d2 second dimansion
     * @exception DimensionMismatchException if dimensions do not match
     */
    private void checkDimensionsEquality(final int d1, final int d2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.checkDimensionsEquality_171");
        if (ROR_not_equals(d1, d2, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.checkDimensionsEquality_171", _mut22534, _mut22535, _mut22536, _mut22537, _mut22538)) {
            throw new DimensionMismatchException(d2, d1);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void init(final FieldODEStateAndDerivative<T> initialState, final T t) {
        initialTime = initialState.getTime();
        finalTime = t;
        forward = true;
        index = 0;
        steps.clear();
    }

    /**
     * Handle the last accepted step.
     * A copy of the information provided by the last step is stored in
     * the instance for later use.
     * @param interpolator interpolator for the last accepted step.
     * @param isLast true if the step is the last one
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * during step finalization
     */
    public void handleStep(final FieldStepInterpolator<T> interpolator, final boolean isLast) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.handleStep_195");
        if (ROR_equals(steps.size(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.handleStep_195", _mut22539, _mut22540, _mut22541, _mut22542, _mut22543)) {
            initialTime = interpolator.getPreviousState().getTime();
            forward = interpolator.isForward();
        }
        steps.add(interpolator);
        if (isLast) {
            finalTime = interpolator.getCurrentState().getTime();
            index = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.handleStep_195", _mut22544, _mut22545, _mut22546, _mut22547);
        }
    }

    /**
     * Get the initial integration time.
     * @return initial integration time
     */
    public T getInitialTime() {
        return initialTime;
    }

    /**
     * Get the final integration time.
     * @return final integration time
     */
    public T getFinalTime() {
        return finalTime;
    }

    /**
     * Get the state at interpolated time.
     * @param time time of the interpolated point
     * @return state at interpolated time
     */
    public FieldODEStateAndDerivative<T> getInterpolatedState(final T time) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233");
        // initialize the search with the complete steps table
        int iMin = 0;
        final FieldStepInterpolator<T> sMin = steps.get(iMin);
        T tMin = sMin.getPreviousState().getTime().add(sMin.getCurrentState().getTime()).multiply(0.5);
        int iMax = AOR_minus(steps.size(), 1, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22548, _mut22549, _mut22550, _mut22551);
        final FieldStepInterpolator<T> sMax = steps.get(iMax);
        T tMax = sMax.getPreviousState().getTime().add(sMax.getCurrentState().getTime()).multiply(0.5);
        // or in the first and last step
        if (ROR_less_equals(locatePoint(time, sMin), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22552, _mut22553, _mut22554, _mut22555, _mut22556)) {
            index = iMin;
            return sMin.getInterpolatedState(time);
        }
        if (ROR_greater_equals(locatePoint(time, sMax), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22557, _mut22558, _mut22559, _mut22560, _mut22561)) {
            index = iMax;
            return sMax.getInterpolatedState(time);
        }
        // reduction of the table slice size
        while (ROR_greater(AOR_minus(iMax, iMin, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22633, _mut22634, _mut22635, _mut22636), 5, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22637, _mut22638, _mut22639, _mut22640, _mut22641)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233");
            // use the last estimated index as the splitting index
            final FieldStepInterpolator<T> si = steps.get(index);
            final int location = locatePoint(time, si);
            if (ROR_less(location, 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22562, _mut22563, _mut22564, _mut22565, _mut22566)) {
                iMax = index;
                tMax = si.getPreviousState().getTime().add(si.getCurrentState().getTime()).multiply(0.5);
            } else if (ROR_greater(location, 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22567, _mut22568, _mut22569, _mut22570, _mut22571)) {
                iMin = index;
                tMin = si.getPreviousState().getTime().add(si.getCurrentState().getTime()).multiply(0.5);
            } else {
                // we have found the target step, no need to continue searching
                return si.getInterpolatedState(time);
            }
            // compute a new estimate of the index in the reduced table slice
            final int iMed = AOR_divide((AOR_plus(iMin, iMax, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22572, _mut22573, _mut22574, _mut22575)), 2, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22576, _mut22577, _mut22578, _mut22579);
            final FieldStepInterpolator<T> sMed = steps.get(iMed);
            final T tMed = sMed.getPreviousState().getTime().add(sMed.getCurrentState().getTime()).multiply(0.5);
            if ((_mut22590 ? (ROR_less(tMed.subtract(tMin).abs().subtract(1.0e-6).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22580, _mut22581, _mut22582, _mut22583, _mut22584) && ROR_less(tMax.subtract(tMed).abs().subtract(1.0e-6).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22585, _mut22586, _mut22587, _mut22588, _mut22589)) : (ROR_less(tMed.subtract(tMin).abs().subtract(1.0e-6).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22580, _mut22581, _mut22582, _mut22583, _mut22584) || ROR_less(tMax.subtract(tMed).abs().subtract(1.0e-6).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22585, _mut22586, _mut22587, _mut22588, _mut22589)))) {
                // too close to the bounds, we estimate using a simple dichotomy
                index = iMed;
            } else {
                // compute index = P(time) rather than solving a quadratic equation)
                final T d12 = tMax.subtract(tMed);
                final T d23 = tMed.subtract(tMin);
                final T d13 = tMax.subtract(tMin);
                final T dt1 = time.subtract(tMax);
                final T dt2 = time.subtract(tMed);
                final T dt3 = time.subtract(tMin);
                final T iLagrange = dt2.multiply(dt3).multiply(d23).multiply(iMax).subtract(dt1.multiply(dt3).multiply(d13).multiply(iMed)).add(dt1.multiply(dt2).multiply(d12).multiply(iMin)).divide(d12.multiply(d23).multiply(d13));
                index = (int) FastMath.rint(iLagrange.getReal());
            }
            // force the next size reduction to be at least one tenth
            final int low = FastMath.max(AOR_plus(iMin, 1, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22591, _mut22592, _mut22593, _mut22594), AOR_divide((AOR_plus(AOR_multiply(9, iMin, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22595, _mut22596, _mut22597, _mut22598), iMax, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22599, _mut22600, _mut22601, _mut22602)), 10, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22603, _mut22604, _mut22605, _mut22606));
            final int high = FastMath.min(AOR_minus(iMax, 1, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22607, _mut22608, _mut22609, _mut22610), AOR_divide((AOR_plus(iMin, AOR_multiply(9, iMax, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22611, _mut22612, _mut22613, _mut22614), "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22615, _mut22616, _mut22617, _mut22618)), 10, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22619, _mut22620, _mut22621, _mut22622));
            if (ROR_less(index, low, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22623, _mut22624, _mut22625, _mut22626, _mut22627)) {
                index = low;
            } else if (ROR_greater(index, high, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22628, _mut22629, _mut22630, _mut22631, _mut22632)) {
                index = high;
            }
        }
        // now the table slice is very small, we perform an iterative search
        index = iMin;
        while ((_mut22652 ? (ROR_less_equals(index, iMax, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22642, _mut22643, _mut22644, _mut22645, _mut22646) || ROR_greater(locatePoint(time, steps.get(index)), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22647, _mut22648, _mut22649, _mut22650, _mut22651)) : (ROR_less_equals(index, iMax, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22642, _mut22643, _mut22644, _mut22645, _mut22646) && ROR_greater(locatePoint(time, steps.get(index)), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233", _mut22647, _mut22648, _mut22649, _mut22650, _mut22651)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState_233");
            ++index;
        }
        return steps.get(index).getInterpolatedState(time);
    }

    /**
     * Compare a step interval and a double.
     * @param time point to locate
     * @param interval step interval
     * @return -1 if the double is before the interval, 0 if it is in
     * the interval, and +1 if it is after the interval, according to
     * the interval direction
     */
    private int locatePoint(final T time, final FieldStepInterpolator<T> interval) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ContinuousOutputFieldModel.locatePoint_326");
        if (forward) {
            if (ROR_less(time.subtract(interval.getPreviousState().getTime()).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.locatePoint_326", _mut22653, _mut22654, _mut22655, _mut22656, _mut22657)) {
                return -1;
            } else if (ROR_greater(time.subtract(interval.getCurrentState().getTime()).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.locatePoint_326", _mut22658, _mut22659, _mut22660, _mut22661, _mut22662)) {
                return +1;
            } else {
                return 0;
            }
        }
        if (ROR_greater(time.subtract(interval.getPreviousState().getTime()).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.locatePoint_326", _mut22663, _mut22664, _mut22665, _mut22666, _mut22667)) {
            return -1;
        } else if (ROR_less(time.subtract(interval.getCurrentState().getTime()).getReal(), 0, "org.apache.commons.math3.ode.ContinuousOutputFieldModel.locatePoint_326", _mut22668, _mut22669, _mut22670, _mut22671, _mut22672)) {
            return +1;
        } else {
            return 0;
        }
    }
}
