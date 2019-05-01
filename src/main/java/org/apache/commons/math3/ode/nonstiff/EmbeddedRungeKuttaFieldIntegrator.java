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
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class EmbeddedRungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AdaptiveStepsizeFieldIntegrator<T> implements FieldButcherArrayProvider<T> {

    @Conditional
    public static boolean _mut13735 = false, _mut13736 = false, _mut13737 = false, _mut13738 = false, _mut13739 = false, _mut13740 = false, _mut13741 = false, _mut13742 = false, _mut13743 = false, _mut13744 = false, _mut13745 = false, _mut13746 = false, _mut13747 = false, _mut13748 = false, _mut13749 = false, _mut13750 = false, _mut13751 = false, _mut13752 = false, _mut13753 = false, _mut13754 = false, _mut13755 = false, _mut13756 = false, _mut13757 = false, _mut13758 = false, _mut13759 = false, _mut13760 = false, _mut13761 = false, _mut13762 = false, _mut13763 = false, _mut13764 = false, _mut13765 = false, _mut13766 = false, _mut13767 = false, _mut13768 = false, _mut13769 = false, _mut13770 = false, _mut13771 = false, _mut13772 = false, _mut13773 = false, _mut13774 = false, _mut13775 = false, _mut13776 = false, _mut13777 = false, _mut13778 = false, _mut13779 = false, _mut13780 = false, _mut13781 = false, _mut13782 = false, _mut13783 = false, _mut13784 = false, _mut13785 = false, _mut13786 = false, _mut13787 = false, _mut13788 = false, _mut13789 = false, _mut13790 = false, _mut13791 = false, _mut13792 = false, _mut13793 = false, _mut13794 = false, _mut13795 = false, _mut13796 = false, _mut13797 = false, _mut13798 = false, _mut13799 = false, _mut13800 = false, _mut13801 = false, _mut13802 = false, _mut13803 = false, _mut13804 = false, _mut13805 = false, _mut13806 = false, _mut13807 = false, _mut13808 = false, _mut13809 = false, _mut13810 = false, _mut13811 = false, _mut13812 = false, _mut13813 = false, _mut13814 = false, _mut13815 = false, _mut13816 = false, _mut13817 = false, _mut13818 = false, _mut13819 = false, _mut13820 = false, _mut13821 = false, _mut13822 = false, _mut13823 = false, _mut13824 = false, _mut13825 = false, _mut13826 = false, _mut13827 = false, _mut13828 = false, _mut13829 = false, _mut13830 = false, _mut13831 = false, _mut13832 = false, _mut13833 = false, _mut13834 = false, _mut13835 = false;

    /**
     * Index of the pre-computed derivative for <i>fsal</i> methods.
     */
    private final int fsal;

    /**
     * Time steps from Butcher array (without the first zero).
     */
    private final T[] c;

    /**
     * Internal weights from Butcher array (without the first empty row).
     */
    private final T[][] a;

    /**
     * External weights for the high order method from Butcher array.
     */
    private final T[] b;

    /**
     * Stepsize control exponent.
     */
    private final T exp;

    /**
     * Safety factor for stepsize control.
     */
    private T safety;

    /**
     * Minimal reduction factor for stepsize control.
     */
    private T minReduction;

    /**
     * Maximal growth factor for stepsize control.
     */
    private T maxGrowth;

    /**
     * Build a Runge-Kutta integrator with the given Butcher array.
     * @param field field to which the time and state vector elements belong
     * @param name name of the method
     * @param fsal index of the pre-computed derivative for <i>fsal</i> methods
     * or -1 if method is not <i>fsal</i>
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    protected EmbeddedRungeKuttaFieldIntegrator(final Field<T> field, final String name, final int fsal, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(field, name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.fsal = fsal;
        this.c = getC();
        this.a = getA();
        this.b = getB();
        exp = field.getOne().divide(-getOrder());
        // set the default values of the algorithm control parameters
        setSafety(field.getZero().add(0.9));
        setMinReduction(field.getZero().add(0.2));
        setMaxGrowth(field.getZero().add(10.0));
    }

    /**
     * Build a Runge-Kutta integrator with the given Butcher array.
     * @param field field to which the time and state vector elements belong
     * @param name name of the method
     * @param fsal index of the pre-computed derivative for <i>fsal</i> methods
     * or -1 if method is not <i>fsal</i>
     * @param minStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maxStep maximal step (must be positive even for backward
     * integration)
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    protected EmbeddedRungeKuttaFieldIntegrator(final Field<T> field, final String name, final int fsal, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(field, name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.fsal = fsal;
        this.c = getC();
        this.a = getA();
        this.b = getB();
        exp = field.getOne().divide(-getOrder());
        // set the default values of the algorithm control parameters
        setSafety(field.getZero().add(0.9));
        setMinReduction(field.getZero().add(0.2));
        setMaxGrowth(field.getZero().add(10.0));
    }

    /**
     * Create a fraction.
     * @param p numerator
     * @param q denominator
     * @return p/q computed in the instance field
     */
    protected T fraction(final int p, final int q) {
        return getField().getOne().multiply(p).divide(q);
    }

    /**
     * Create a fraction.
     * @param p numerator
     * @param q denominator
     * @return p/q computed in the instance field
     */
    protected T fraction(final double p, final double q) {
        return getField().getOne().multiply(p).divide(q);
    }

    /**
     * Create an interpolator.
     * @param forward integration direction indicator
     * @param yDotK slopes at the intermediate points
     * @param globalPreviousState start of the global step
     * @param globalCurrentState end of the global step
     * @param mapper equations mapper for the all equations
     * @return external weights for the high order method from Butcher array
     */
    protected abstract RungeKuttaFieldStepInterpolator<T> createInterpolator(boolean forward, T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, FieldEquationsMapper<T> mapper);

    /**
     * Get the order of the method.
     * @return order of the method
     */
    public abstract int getOrder();

    /**
     * Get the safety factor for stepsize control.
     * @return safety factor
     */
    public T getSafety() {
        return safety;
    }

    /**
     * Set the safety factor for stepsize control.
     * @param safety safety factor
     */
    public void setSafety(final T safety) {
        this.safety = safety;
    }

    /**
     * {@inheritDoc}
     */
    public FieldODEStateAndDerivative<T> integrate(final FieldExpandableODE<T> equations, final FieldODEState<T> initialState, final T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
        sanityChecks(initialState, finalTime);
        final T t0 = initialState.getTime();
        final T[] y0 = equations.getMapper().mapState(initialState);
        setStepStart(initIntegration(equations, t0, y0, finalTime));
        final boolean forward = ROR_greater(finalTime.subtract(initialState.getTime()).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13735, _mut13736, _mut13737, _mut13738, _mut13739);
        // create some internal working arrays
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13740, _mut13741, _mut13742, _mut13743);
        T[] y = y0;
        final T[][] yDotK = MathArrays.buildArray(getField(), stages, -1);
        final T[] yTmp = MathArrays.buildArray(getField(), y0.length);
        // set up integration control objects
        T hNew = getField().getZero();
        boolean firstTime = true;
        // main integration loop
        setIsLastStep(false);
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
            // iterate over step size, ensuring local normalized error is smaller than 1
            T error = getField().getZero().add(10);
            while (ROR_greater_equals(error.subtract(1.0).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13806, _mut13807, _mut13808, _mut13809, _mut13810)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                // first stage
                y = equations.getMapper().mapState(getStepStart());
                yDotK[0] = equations.getMapper().mapDerivative(getStepStart());
                if (firstTime) {
                    final T[] scale = MathArrays.buildArray(getField(), mainSetDimension);
                    if (vecAbsoluteTolerance == null) {
                        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13749, _mut13750, _mut13751, _mut13752, _mut13753); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                            scale[i] = y[i].abs().multiply(scalRelativeTolerance).add(scalAbsoluteTolerance);
                        }
                    } else {
                        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13744, _mut13745, _mut13746, _mut13747, _mut13748); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                            scale[i] = y[i].abs().multiply(vecRelativeTolerance[i]).add(vecAbsoluteTolerance[i]);
                        }
                    }
                    hNew = initializeStep(forward, getOrder(), scale, getStepStart(), equations.getMapper());
                    firstTime = false;
                }
                setStepSize(hNew);
                if (forward) {
                    if (ROR_greater_equals(getStepStart().getTime().add(getStepSize()).subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13759, _mut13760, _mut13761, _mut13762, _mut13763)) {
                        setStepSize(finalTime.subtract(getStepStart().getTime()));
                    }
                } else {
                    if (ROR_less_equals(getStepStart().getTime().add(getStepSize()).subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13754, _mut13755, _mut13756, _mut13757, _mut13758)) {
                        setStepSize(finalTime.subtract(getStepStart().getTime()));
                    }
                }
                // next stages
                for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13786, _mut13787, _mut13788, _mut13789, _mut13790); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                    for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13777, _mut13778, _mut13779, _mut13780, _mut13781); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                        T sum = yDotK[0][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13764, _mut13765, _mut13766, _mut13767)][0]);
                        for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13772, _mut13773, _mut13774, _mut13775, _mut13776); ++l) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                            sum = sum.add(yDotK[l][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13768, _mut13769, _mut13770, _mut13771)][l]));
                        }
                        yTmp[j] = y[j].add(getStepSize().multiply(sum));
                    }
                    yDotK[k] = computeDerivatives(getStepStart().getTime().add(getStepSize().multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13782, _mut13783, _mut13784, _mut13785)])), yTmp);
                }
                // estimate the state at the end of the step
                for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13796, _mut13797, _mut13798, _mut13799, _mut13800); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                    T sum = yDotK[0][j].multiply(b[0]);
                    for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13791, _mut13792, _mut13793, _mut13794, _mut13795); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216");
                        sum = sum.add(yDotK[l][j].multiply(b[l]));
                    }
                    yTmp[j] = y[j].add(getStepSize().multiply(sum));
                }
                // estimate the error at the end of the step
                error = estimateError(yDotK, y, yTmp, getStepSize());
                if (ROR_greater_equals(error.subtract(1.0).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13801, _mut13802, _mut13803, _mut13804, _mut13805)) {
                    // reject the step and attempt to reduce error by stepsize control
                    final T factor = MathUtils.min(maxGrowth, MathUtils.max(minReduction, safety.multiply(error.pow(exp))));
                    hNew = filterStep(getStepSize().multiply(factor), forward, false);
                }
            }
            final T stepEnd = getStepStart().getTime().add(getStepSize());
            final T[] yDotTmp = (ROR_greater_equals(fsal, 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13811, _mut13812, _mut13813, _mut13814, _mut13815)) ? yDotK[fsal] : computeDerivatives(stepEnd, yTmp);
            final FieldODEStateAndDerivative<T> stateTmp = new FieldODEStateAndDerivative<T>(stepEnd, yTmp, yDotTmp);
            // local error is small enough: accept the step, trigger events and step handlers
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            setStepStart(acceptStep(createInterpolator(forward, yDotK, getStepStart(), stateTmp, equations.getMapper()), finalTime));
            if (!isLastStep()) {
                // stepsize control for next step
                final T factor = MathUtils.min(maxGrowth, MathUtils.max(minReduction, safety.multiply(error.pow(exp))));
                final T scaledH = getStepSize().multiply(factor);
                final T nextT = getStepStart().getTime().add(scaledH);
                final boolean nextIsLast = forward ? ROR_greater_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13821, _mut13822, _mut13823, _mut13824, _mut13825) : ROR_less_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13816, _mut13817, _mut13818, _mut13819, _mut13820);
                hNew = filterStep(scaledH, forward, nextIsLast);
                final T filteredNextT = getStepStart().getTime().add(hNew);
                final boolean filteredNextIsLast = forward ? ROR_greater_equals(filteredNextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13831, _mut13832, _mut13833, _mut13834, _mut13835) : ROR_less_equals(filteredNextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaFieldIntegrator.integrate_216", _mut13826, _mut13827, _mut13828, _mut13829, _mut13830);
                if (filteredNextIsLast) {
                    hNew = finalTime.subtract(getStepStart().getTime());
                }
            }
        } while (!isLastStep());
        final FieldODEStateAndDerivative<T> finalState = getStepStart();
        resetInternalState();
        return finalState;
    }

    /**
     * Get the minimal reduction factor for stepsize control.
     * @return minimal reduction factor
     */
    public T getMinReduction() {
        return minReduction;
    }

    /**
     * Set the minimal reduction factor for stepsize control.
     * @param minReduction minimal reduction factor
     */
    public void setMinReduction(final T minReduction) {
        this.minReduction = minReduction;
    }

    /**
     * Get the maximal growth factor for stepsize control.
     * @return maximal growth factor
     */
    public T getMaxGrowth() {
        return maxGrowth;
    }

    /**
     * Set the maximal growth factor for stepsize control.
     * @param maxGrowth maximal growth factor
     */
    public void setMaxGrowth(final T maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    /**
     * Compute the error ratio.
     * @param yDotK derivatives computed during the first stages
     * @param y0 estimate of the step at the start of the step
     * @param y1 estimate of the step at the end of the step
     * @param h  current step
     * @return error ratio, greater than 1 if step should be rejected
     */
    protected abstract T estimateError(T[][] yDotK, T[] y0, T[] y1, T h);
}
