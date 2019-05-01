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
import org.apache.commons.math3.ode.AbstractFieldIntegrator;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldExpandableODE;
import org.apache.commons.math3.ode.FirstOrderFieldDifferentialEquations;
import org.apache.commons.math3.ode.FieldODEState;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class RungeKuttaFieldIntegrator<T extends RealFieldElement<T>> extends AbstractFieldIntegrator<T> implements FieldButcherArrayProvider<T> {

    @Conditional
    public static boolean _mut14205 = false, _mut14206 = false, _mut14207 = false, _mut14208 = false, _mut14209 = false, _mut14210 = false, _mut14211 = false, _mut14212 = false, _mut14213 = false, _mut14214 = false, _mut14215 = false, _mut14216 = false, _mut14217 = false, _mut14218 = false, _mut14219 = false, _mut14220 = false, _mut14221 = false, _mut14222 = false, _mut14223 = false, _mut14224 = false, _mut14225 = false, _mut14226 = false, _mut14227 = false, _mut14228 = false, _mut14229 = false, _mut14230 = false, _mut14231 = false, _mut14232 = false, _mut14233 = false, _mut14234 = false, _mut14235 = false, _mut14236 = false, _mut14237 = false, _mut14238 = false, _mut14239 = false, _mut14240 = false, _mut14241 = false, _mut14242 = false, _mut14243 = false, _mut14244 = false, _mut14245 = false, _mut14246 = false, _mut14247 = false, _mut14248 = false, _mut14249 = false, _mut14250 = false, _mut14251 = false, _mut14252 = false, _mut14253 = false, _mut14254 = false, _mut14255 = false, _mut14256 = false, _mut14257 = false, _mut14258 = false, _mut14259 = false, _mut14260 = false, _mut14261 = false, _mut14262 = false, _mut14263 = false, _mut14264 = false, _mut14265 = false, _mut14266 = false, _mut14267 = false, _mut14268 = false, _mut14269 = false, _mut14270 = false, _mut14271 = false, _mut14272 = false, _mut14273 = false, _mut14274 = false, _mut14275 = false, _mut14276 = false, _mut14277 = false, _mut14278 = false, _mut14279 = false, _mut14280 = false, _mut14281 = false, _mut14282 = false, _mut14283 = false, _mut14284 = false, _mut14285 = false, _mut14286 = false, _mut14287 = false, _mut14288 = false, _mut14289 = false, _mut14290 = false, _mut14291 = false, _mut14292 = false, _mut14293 = false, _mut14294 = false, _mut14295 = false, _mut14296 = false, _mut14297 = false, _mut14298 = false, _mut14299 = false, _mut14300 = false, _mut14301 = false, _mut14302 = false, _mut14303 = false, _mut14304 = false, _mut14305 = false, _mut14306 = false, _mut14307 = false, _mut14308 = false, _mut14309 = false, _mut14310 = false, _mut14311 = false;

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
     * Integration step.
     */
    private final T step;

    /**
     * Simple constructor.
     * Build a Runge-Kutta integrator with the given
     * step. The default step handler does nothing.
     * @param field field to which the time and state vector elements belong
     * @param name name of the method
     * @param step integration step
     */
    protected RungeKuttaFieldIntegrator(final Field<T> field, final String name, final T step) {
        super(field, name);
        this.c = getC();
        this.a = getA();
        this.b = getB();
        this.step = step.abs();
    }

    /**
     * Create a fraction.
     * @param p numerator
     * @param q denominator
     * @return p/q computed in the instance field
     */
    protected T fraction(final int p, final int q) {
        return getField().getZero().add(p).divide(q);
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
     * {@inheritDoc}
     */
    public FieldODEStateAndDerivative<T> integrate(final FieldExpandableODE<T> equations, final FieldODEState<T> initialState, final T finalTime) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
        sanityChecks(initialState, finalTime);
        final T t0 = initialState.getTime();
        final T[] y0 = equations.getMapper().mapState(initialState);
        setStepStart(initIntegration(equations, t0, y0, finalTime));
        final boolean forward = ROR_greater(finalTime.subtract(initialState.getTime()).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14205, _mut14206, _mut14207, _mut14208, _mut14209);
        // create some internal working arrays
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14210, _mut14211, _mut14212, _mut14213);
        T[] y = y0;
        final T[][] yDotK = MathArrays.buildArray(getField(), stages, -1);
        final T[] yTmp = MathArrays.buildArray(getField(), y0.length);
        // set up integration control objects
        if (forward) {
            if (ROR_greater_equals(getStepStart().getTime().add(step).subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14219, _mut14220, _mut14221, _mut14222, _mut14223)) {
                setStepSize(finalTime.subtract(getStepStart().getTime()));
            } else {
                setStepSize(step);
            }
        } else {
            if (ROR_less_equals(getStepStart().getTime().subtract(step).subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14214, _mut14215, _mut14216, _mut14217, _mut14218)) {
                setStepSize(finalTime.subtract(getStepStart().getTime()));
            } else {
                setStepSize(step.negate());
            }
        }
        // main integration loop
        setIsLastStep(false);
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
            // first stage
            y = equations.getMapper().mapState(getStepStart());
            yDotK[0] = equations.getMapper().mapDerivative(getStepStart());
            // next stages
            for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14246, _mut14247, _mut14248, _mut14249, _mut14250); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
                for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14237, _mut14238, _mut14239, _mut14240, _mut14241); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
                    T sum = yDotK[0][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14224, _mut14225, _mut14226, _mut14227)][0]);
                    for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14232, _mut14233, _mut14234, _mut14235, _mut14236); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
                        sum = sum.add(yDotK[l][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14228, _mut14229, _mut14230, _mut14231)][l]));
                    }
                    yTmp[j] = y[j].add(getStepSize().multiply(sum));
                }
                yDotK[k] = computeDerivatives(getStepStart().getTime().add(getStepSize().multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14242, _mut14243, _mut14244, _mut14245)])), yTmp);
            }
            // estimate the state at the end of the step
            for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14256, _mut14257, _mut14258, _mut14259, _mut14260); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
                T sum = yDotK[0][j].multiply(b[0]);
                for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14251, _mut14252, _mut14253, _mut14254, _mut14255); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114");
                    sum = sum.add(yDotK[l][j].multiply(b[l]));
                }
                yTmp[j] = y[j].add(getStepSize().multiply(sum));
            }
            final T stepEnd = getStepStart().getTime().add(getStepSize());
            final T[] yDotTmp = computeDerivatives(stepEnd, yTmp);
            final FieldODEStateAndDerivative<T> stateTmp = new FieldODEStateAndDerivative<T>(stepEnd, yTmp, yDotTmp);
            // discrete events handling
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            setStepStart(acceptStep(createInterpolator(forward, yDotK, getStepStart(), stateTmp, equations.getMapper()), finalTime));
            if (!isLastStep()) {
                // stepsize control for next step
                final T nextT = getStepStart().getTime().add(getStepSize());
                final boolean nextIsLast = forward ? (ROR_greater_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14266, _mut14267, _mut14268, _mut14269, _mut14270)) : (ROR_less_equals(nextT.subtract(finalTime).getReal(), 0, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.integrate_114", _mut14261, _mut14262, _mut14263, _mut14264, _mut14265));
                if (nextIsLast) {
                    setStepSize(finalTime.subtract(getStepStart().getTime()));
                }
            }
        } while (!isLastStep());
        final FieldODEStateAndDerivative<T> finalState = getStepStart();
        setStepStart(null);
        setStepSize(null);
        return finalState;
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
     * This method is <em>not</em> used at all by the {@link #integrate(FieldExpandableODE,
     * FieldODEState, RealFieldElement)} method. It also completely ignores the step set at
     * construction time, and uses only a single step to go from {@code t0} to {@code t}.
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
    public T[] singleStep(final FirstOrderFieldDifferentialEquations<T> equations, final T t0, final T[] y0, final T t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
        // create some internal working arrays
        final T[] y = y0.clone();
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14271, _mut14272, _mut14273, _mut14274);
        final T[][] yDotK = MathArrays.buildArray(getField(), stages, -1);
        final T[] yTmp = y0.clone();
        // first stage
        final T h = t.subtract(t0);
        yDotK[0] = equations.computeDerivatives(t0, y);
        // next stages
        for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14297, _mut14298, _mut14299, _mut14300, _mut14301); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
            for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14288, _mut14289, _mut14290, _mut14291, _mut14292); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
                T sum = yDotK[0][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14275, _mut14276, _mut14277, _mut14278)][0]);
                for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14283, _mut14284, _mut14285, _mut14286, _mut14287); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
                    sum = sum.add(yDotK[l][j].multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14279, _mut14280, _mut14281, _mut14282)][l]));
                }
                yTmp[j] = y[j].add(h.multiply(sum));
            }
            yDotK[k] = equations.computeDerivatives(t0.add(h.multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14293, _mut14294, _mut14295, _mut14296)])), yTmp);
        }
        // estimate the state at the end of the step
        for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14307, _mut14308, _mut14309, _mut14310, _mut14311); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
            T sum = yDotK[0][j].multiply(b[0]);
            for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232", _mut14302, _mut14303, _mut14304, _mut14305, _mut14306); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldIntegrator.singleStep_232");
                sum = sum.add(yDotK[l][j].multiply(b[l]));
            }
            y[j] = y[j].add(h.multiply(sum));
        }
        return y;
    }
}
