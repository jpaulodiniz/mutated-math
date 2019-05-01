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
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class RungeKuttaIntegrator extends AbstractIntegrator {

    @Conditional
    public static boolean _mut19532 = false, _mut19533 = false, _mut19534 = false, _mut19535 = false, _mut19536 = false, _mut19537 = false, _mut19538 = false, _mut19539 = false, _mut19540 = false, _mut19541 = false, _mut19542 = false, _mut19543 = false, _mut19544 = false, _mut19545 = false, _mut19546 = false, _mut19547 = false, _mut19548 = false, _mut19549 = false, _mut19550 = false, _mut19551 = false, _mut19552 = false, _mut19553 = false, _mut19554 = false, _mut19555 = false, _mut19556 = false, _mut19557 = false, _mut19558 = false, _mut19559 = false, _mut19560 = false, _mut19561 = false, _mut19562 = false, _mut19563 = false, _mut19564 = false, _mut19565 = false, _mut19566 = false, _mut19567 = false, _mut19568 = false, _mut19569 = false, _mut19570 = false, _mut19571 = false, _mut19572 = false, _mut19573 = false, _mut19574 = false, _mut19575 = false, _mut19576 = false, _mut19577 = false, _mut19578 = false, _mut19579 = false, _mut19580 = false, _mut19581 = false, _mut19582 = false, _mut19583 = false, _mut19584 = false, _mut19585 = false, _mut19586 = false, _mut19587 = false, _mut19588 = false, _mut19589 = false, _mut19590 = false, _mut19591 = false, _mut19592 = false, _mut19593 = false, _mut19594 = false, _mut19595 = false, _mut19596 = false, _mut19597 = false, _mut19598 = false, _mut19599 = false, _mut19600 = false, _mut19601 = false, _mut19602 = false, _mut19603 = false, _mut19604 = false, _mut19605 = false, _mut19606 = false, _mut19607 = false, _mut19608 = false, _mut19609 = false, _mut19610 = false, _mut19611 = false, _mut19612 = false, _mut19613 = false, _mut19614 = false, _mut19615 = false, _mut19616 = false, _mut19617 = false, _mut19618 = false, _mut19619 = false, _mut19620 = false, _mut19621 = false, _mut19622 = false, _mut19623 = false, _mut19624 = false, _mut19625 = false, _mut19626 = false, _mut19627 = false, _mut19628 = false, _mut19629 = false, _mut19630 = false, _mut19631 = false, _mut19632 = false, _mut19633 = false, _mut19634 = false, _mut19635 = false, _mut19636 = false, _mut19637 = false, _mut19638 = false, _mut19639 = false, _mut19640 = false, _mut19641 = false, _mut19642 = false, _mut19643 = false, _mut19644 = false, _mut19645 = false, _mut19646 = false, _mut19647 = false, _mut19648 = false, _mut19649 = false, _mut19650 = false, _mut19651 = false, _mut19652 = false, _mut19653 = false, _mut19654 = false, _mut19655 = false, _mut19656 = false, _mut19657 = false, _mut19658 = false, _mut19659 = false, _mut19660 = false, _mut19661 = false, _mut19662 = false, _mut19663 = false, _mut19664 = false, _mut19665 = false, _mut19666 = false, _mut19667 = false, _mut19668 = false, _mut19669 = false, _mut19670 = false, _mut19671 = false, _mut19672 = false, _mut19673 = false, _mut19674 = false, _mut19675 = false, _mut19676 = false, _mut19677 = false, _mut19678 = false, _mut19679 = false, _mut19680 = false, _mut19681 = false, _mut19682 = false, _mut19683 = false, _mut19684 = false, _mut19685 = false, _mut19686 = false, _mut19687 = false, _mut19688 = false, _mut19689 = false, _mut19690 = false, _mut19691 = false, _mut19692 = false, _mut19693 = false, _mut19694 = false, _mut19695 = false, _mut19696 = false, _mut19697 = false, _mut19698 = false, _mut19699 = false, _mut19700 = false, _mut19701 = false, _mut19702 = false, _mut19703 = false, _mut19704 = false, _mut19705 = false, _mut19706 = false, _mut19707 = false, _mut19708 = false, _mut19709 = false, _mut19710 = false, _mut19711 = false, _mut19712 = false, _mut19713 = false, _mut19714 = false, _mut19715 = false, _mut19716 = false, _mut19717 = false, _mut19718 = false, _mut19719 = false, _mut19720 = false, _mut19721 = false, _mut19722 = false, _mut19723 = false, _mut19724 = false, _mut19725 = false, _mut19726 = false, _mut19727 = false, _mut19728 = false, _mut19729 = false, _mut19730 = false, _mut19731 = false, _mut19732 = false, _mut19733 = false, _mut19734 = false, _mut19735 = false, _mut19736 = false, _mut19737 = false, _mut19738 = false, _mut19739 = false, _mut19740 = false, _mut19741 = false, _mut19742 = false, _mut19743 = false, _mut19744 = false, _mut19745 = false, _mut19746 = false, _mut19747 = false, _mut19748 = false, _mut19749 = false, _mut19750 = false, _mut19751 = false, _mut19752 = false, _mut19753 = false, _mut19754 = false, _mut19755 = false, _mut19756 = false, _mut19757 = false, _mut19758 = false, _mut19759 = false, _mut19760 = false;

    /**
     * Time steps from Butcher array (without the first zero).
     */
    private final double[] c;

    /**
     * Internal weights from Butcher array (without the first empty row).
     */
    private final double[][] a;

    /**
     * External weights for the high order method from Butcher array.
     */
    private final double[] b;

    /**
     * Prototype of the step interpolator.
     */
    private final RungeKuttaStepInterpolator prototype;

    /**
     * Integration step.
     */
    private final double step;

    /**
     * Simple constructor.
     * Build a Runge-Kutta integrator with the given
     * step. The default step handler does nothing.
     * @param name name of the method
     * @param c time steps from Butcher array (without the first zero)
     * @param a internal weights from Butcher array (without the first empty row)
     * @param b propagation weights for the high order method from Butcher array
     * @param prototype prototype of the step interpolator to use
     * @param step integration step
     */
    protected RungeKuttaIntegrator(final String name, final double[] c, final double[][] a, final double[] b, final RungeKuttaStepInterpolator prototype, final double step) {
        super(name);
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.step = FastMath.abs(step);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(final ExpandableStatefulODE equations, final double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
        sanityChecks(equations, t);
        setEquations(equations);
        final boolean forward = ROR_greater(t, equations.getTime(), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19532, _mut19533, _mut19534, _mut19535, _mut19536);
        // create some internal working arrays
        final double[] y0 = equations.getCompleteState();
        final double[] y = y0.clone();
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19537, _mut19538, _mut19539, _mut19540);
        final double[][] yDotK = new double[stages][];
        for (int i = 0; ROR_less(i, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19541, _mut19542, _mut19543, _mut19544, _mut19545); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
            yDotK[i] = new double[y0.length];
        }
        final double[] yTmp = y0.clone();
        final double[] yDotTmp = new double[y0.length];
        // set up an interpolator sharing the integrator arrays
        final RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator) prototype.copy();
        interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        interpolator.storeTime(equations.getTime());
        // set up integration control objects
        stepStart = equations.getTime();
        if (forward) {
            if (ROR_greater_equals(AOR_plus(stepStart, step, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19559, _mut19560, _mut19561, _mut19562), t, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19563, _mut19564, _mut19565, _mut19566, _mut19567)) {
                stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19568, _mut19569, _mut19570, _mut19571);
            } else {
                stepSize = step;
            }
        } else {
            if (ROR_less_equals(AOR_minus(stepStart, step, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19546, _mut19547, _mut19548, _mut19549), t, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19550, _mut19551, _mut19552, _mut19553, _mut19554)) {
                stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19555, _mut19556, _mut19557, _mut19558);
            } else {
                stepSize = -step;
            }
        }
        initIntegration(equations.getTime(), y0, t);
        // main integration loop
        isLastStep = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
            interpolator.shift();
            // first stage
            computeDerivatives(stepStart, y, yDotK[0]);
            // next stages
            for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19618, _mut19619, _mut19620, _mut19621, _mut19622); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
                for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19601, _mut19602, _mut19603, _mut19604, _mut19605); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
                    double sum = AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19572, _mut19573, _mut19574, _mut19575)][0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19576, _mut19577, _mut19578, _mut19579);
                    for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19588, _mut19589, _mut19590, _mut19591, _mut19592); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
                        sum += AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19580, _mut19581, _mut19582, _mut19583)][l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19584, _mut19585, _mut19586, _mut19587);
                    }
                    yTmp[j] = AOR_plus(y[j], AOR_multiply(stepSize, sum, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19593, _mut19594, _mut19595, _mut19596), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19597, _mut19598, _mut19599, _mut19600);
                }
                computeDerivatives(AOR_plus(stepStart, AOR_multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19606, _mut19607, _mut19608, _mut19609)], stepSize, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19610, _mut19611, _mut19612, _mut19613), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19614, _mut19615, _mut19616, _mut19617), yTmp, yDotK[k]);
            }
            // estimate the state at the end of the step
            for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19644, _mut19645, _mut19646, _mut19647, _mut19648); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
                double sum = AOR_multiply(b[0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19623, _mut19624, _mut19625, _mut19626);
                for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19631, _mut19632, _mut19633, _mut19634, _mut19635); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94");
                    sum += AOR_multiply(b[l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19627, _mut19628, _mut19629, _mut19630);
                }
                yTmp[j] = AOR_plus(y[j], AOR_multiply(stepSize, sum, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19636, _mut19637, _mut19638, _mut19639), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19640, _mut19641, _mut19642, _mut19643);
            }
            // discrete events handling
            interpolator.storeTime(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19649, _mut19650, _mut19651, _mut19652));
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            System.arraycopy(yDotK[AOR_minus(stages, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19653, _mut19654, _mut19655, _mut19656)], 0, yDotTmp, 0, y0.length);
            stepStart = acceptStep(interpolator, y, yDotTmp, t);
            if (!isLastStep) {
                // prepare next step
                interpolator.storeTime(stepStart);
                // stepsize control for next step
                final double nextT = AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19657, _mut19658, _mut19659, _mut19660);
                final boolean nextIsLast = forward ? (ROR_greater_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19666, _mut19667, _mut19668, _mut19669, _mut19670)) : (ROR_less_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19661, _mut19662, _mut19663, _mut19664, _mut19665));
                if (nextIsLast) {
                    stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.integrate_94", _mut19671, _mut19672, _mut19673, _mut19674);
                }
            }
        } while (!isLastStep);
        // dispatch results
        equations.setTime(stepStart);
        equations.setCompleteState(y);
        stepStart = Double.NaN;
        stepSize = Double.NaN;
    }

    /**
     * Fast computation of a single step of ODE integration.
     * <p>This method is intended for the limited use case of
     * very fast computation of only one step without using any of the
     * rich features of general integrators that may take some time
     * to set up (i.e. no step handlers, no events handlers, no additional
     * states, no interpolators, no error control, no evaluations count,
     * no sanity checks ...). It handles the strict minimum of computation,
     * so it can be embedded in outer loops.</p>
     * <p>
     * This method is <em>not</em> used at all by the {@link #integrate(ExpandableStatefulODE, double)}
     * method. It also completely ignores the step set at construction time, and
     * uses only a single step to go from {@code t0} to {@code t}.
     * </p>
     * <p>
     * As this method does not use any of the state-dependent features of the integrator,
     * it should be reasonably thread-safe <em>if and only if</em> the provided differential
     * equations are themselves thread-safe.
     * </p>
     * @param equations differential equations to integrate
     * @param t0 initial time
     * @param y0 initial value of the state vector at t0
     * @param t target time for the integration
     * (can be set to a value smaller than {@code t0} for backward integration)
     * @return state vector at {@code t}
     */
    public double[] singleStep(final FirstOrderDifferentialEquations equations, final double t0, final double[] y0, final double t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
        // create some internal working arrays
        final double[] y = y0.clone();
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19675, _mut19676, _mut19677, _mut19678);
        final double[][] yDotK = new double[stages][];
        for (int i = 0; ROR_less(i, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19679, _mut19680, _mut19681, _mut19682, _mut19683); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
            yDotK[i] = new double[y0.length];
        }
        final double[] yTmp = y0.clone();
        // first stage
        final double h = AOR_minus(t, t0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19684, _mut19685, _mut19686, _mut19687);
        equations.computeDerivatives(t0, y, yDotK[0]);
        // next stages
        for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19734, _mut19735, _mut19736, _mut19737, _mut19738); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
            for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19717, _mut19718, _mut19719, _mut19720, _mut19721); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
                double sum = AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19688, _mut19689, _mut19690, _mut19691)][0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19692, _mut19693, _mut19694, _mut19695);
                for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19704, _mut19705, _mut19706, _mut19707, _mut19708); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
                    sum += AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19696, _mut19697, _mut19698, _mut19699)][l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19700, _mut19701, _mut19702, _mut19703);
                }
                yTmp[j] = AOR_plus(y[j], AOR_multiply(h, sum, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19709, _mut19710, _mut19711, _mut19712), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19713, _mut19714, _mut19715, _mut19716);
            }
            equations.computeDerivatives(AOR_plus(t0, AOR_multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19722, _mut19723, _mut19724, _mut19725)], h, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19726, _mut19727, _mut19728, _mut19729), "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19730, _mut19731, _mut19732, _mut19733), yTmp, yDotK[k]);
        }
        // estimate the state at the end of the step
        for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19756, _mut19757, _mut19758, _mut19759, _mut19760); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
            double sum = AOR_multiply(b[0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19739, _mut19740, _mut19741, _mut19742);
            for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19747, _mut19748, _mut19749, _mut19750, _mut19751); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225");
                sum += AOR_multiply(b[l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19743, _mut19744, _mut19745, _mut19746);
            }
            y[j] += AOR_multiply(h, sum, "org.apache.commons.math3.ode.nonstiff.RungeKuttaIntegrator.singleStep_225", _mut19752, _mut19753, _mut19754, _mut19755);
        }
        return y;
    }
}
