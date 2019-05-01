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
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {

    @Conditional
    public static boolean _mut16224 = false, _mut16225 = false, _mut16226 = false, _mut16227 = false, _mut16228 = false, _mut16229 = false, _mut16230 = false, _mut16231 = false, _mut16232 = false, _mut16233 = false, _mut16234 = false, _mut16235 = false, _mut16236 = false, _mut16237 = false, _mut16238 = false, _mut16239 = false, _mut16240 = false, _mut16241 = false, _mut16242 = false, _mut16243 = false, _mut16244 = false, _mut16245 = false, _mut16246 = false, _mut16247 = false, _mut16248 = false, _mut16249 = false, _mut16250 = false, _mut16251 = false, _mut16252 = false, _mut16253 = false, _mut16254 = false, _mut16255 = false, _mut16256 = false, _mut16257 = false, _mut16258 = false, _mut16259 = false, _mut16260 = false, _mut16261 = false, _mut16262 = false, _mut16263 = false, _mut16264 = false, _mut16265 = false, _mut16266 = false, _mut16267 = false, _mut16268 = false, _mut16269 = false, _mut16270 = false, _mut16271 = false, _mut16272 = false, _mut16273 = false, _mut16274 = false, _mut16275 = false, _mut16276 = false, _mut16277 = false, _mut16278 = false, _mut16279 = false, _mut16280 = false, _mut16281 = false, _mut16282 = false, _mut16283 = false, _mut16284 = false, _mut16285 = false, _mut16286 = false, _mut16287 = false, _mut16288 = false, _mut16289 = false, _mut16290 = false, _mut16291 = false, _mut16292 = false, _mut16293 = false, _mut16294 = false, _mut16295 = false, _mut16296 = false, _mut16297 = false, _mut16298 = false, _mut16299 = false, _mut16300 = false, _mut16301 = false, _mut16302 = false, _mut16303 = false, _mut16304 = false, _mut16305 = false, _mut16306 = false, _mut16307 = false, _mut16308 = false, _mut16309 = false, _mut16310 = false, _mut16311 = false, _mut16312 = false, _mut16313 = false, _mut16314 = false, _mut16315 = false, _mut16316 = false, _mut16317 = false, _mut16318 = false, _mut16319 = false, _mut16320 = false, _mut16321 = false, _mut16322 = false, _mut16323 = false, _mut16324 = false, _mut16325 = false, _mut16326 = false, _mut16327 = false, _mut16328 = false, _mut16329 = false, _mut16330 = false, _mut16331 = false, _mut16332 = false, _mut16333 = false, _mut16334 = false, _mut16335 = false, _mut16336 = false, _mut16337 = false, _mut16338 = false, _mut16339 = false, _mut16340 = false, _mut16341 = false, _mut16342 = false, _mut16343 = false, _mut16344 = false, _mut16345 = false, _mut16346 = false, _mut16347 = false, _mut16348 = false, _mut16349 = false, _mut16350 = false, _mut16351 = false, _mut16352 = false, _mut16353 = false, _mut16354 = false, _mut16355 = false, _mut16356 = false, _mut16357 = false, _mut16358 = false, _mut16359 = false, _mut16360 = false, _mut16361 = false, _mut16362 = false, _mut16363 = false, _mut16364 = false, _mut16365 = false, _mut16366 = false, _mut16367 = false, _mut16368 = false, _mut16369 = false, _mut16370 = false, _mut16371 = false, _mut16372 = false, _mut16373 = false, _mut16374 = false, _mut16375 = false, _mut16376 = false, _mut16377 = false, _mut16378 = false, _mut16379 = false, _mut16380 = false, _mut16381 = false, _mut16382 = false, _mut16383 = false, _mut16384 = false, _mut16385 = false, _mut16386 = false, _mut16387 = false, _mut16388 = false, _mut16389 = false, _mut16390 = false, _mut16391 = false, _mut16392 = false, _mut16393 = false, _mut16394 = false, _mut16395 = false, _mut16396 = false, _mut16397 = false, _mut16398 = false, _mut16399 = false, _mut16400 = false, _mut16401 = false, _mut16402 = false, _mut16403 = false, _mut16404 = false, _mut16405 = false, _mut16406 = false, _mut16407 = false, _mut16408 = false, _mut16409 = false, _mut16410 = false, _mut16411 = false, _mut16412 = false, _mut16413 = false, _mut16414 = false, _mut16415 = false, _mut16416 = false, _mut16417 = false, _mut16418 = false, _mut16419 = false, _mut16420 = false, _mut16421 = false, _mut16422 = false, _mut16423 = false, _mut16424 = false, _mut16425 = false, _mut16426 = false, _mut16427 = false, _mut16428 = false, _mut16429 = false, _mut16430 = false, _mut16431 = false, _mut16432 = false, _mut16433 = false, _mut16434 = false, _mut16435 = false, _mut16436 = false;

    /**
     * Indicator for <i>fsal</i> methods.
     */
    private final boolean fsal;

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
     * Stepsize control exponent.
     */
    private final double exp;

    /**
     * Safety factor for stepsize control.
     */
    private double safety;

    /**
     * Minimal reduction factor for stepsize control.
     */
    private double minReduction;

    /**
     * Maximal growth factor for stepsize control.
     */
    private double maxGrowth;

    /**
     * Build a Runge-Kutta integrator with the given Butcher array.
     * @param name name of the method
     * @param fsal indicate that the method is an <i>fsal</i>
     * @param c time steps from Butcher array (without the first zero)
     * @param a internal weights from Butcher array (without the first empty row)
     * @param b propagation weights for the high order method from Butcher array
     * @param prototype prototype of the step interpolator to use
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    protected EmbeddedRungeKuttaIntegrator(final String name, final boolean fsal, final double[] c, final double[][] a, final double[] b, final RungeKuttaStepInterpolator prototype, final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.EmbeddedRungeKuttaIntegrator_109");
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        exp = AOR_divide(-1.0, getOrder(), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.EmbeddedRungeKuttaIntegrator_109", _mut16224, _mut16225, _mut16226, _mut16227);
        // set the default values of the algorithm control parameters
        setSafety(0.9);
        setMinReduction(0.2);
        setMaxGrowth(10.0);
    }

    /**
     * Build a Runge-Kutta integrator with the given Butcher array.
     * @param name name of the method
     * @param fsal indicate that the method is an <i>fsal</i>
     * @param c time steps from Butcher array (without the first zero)
     * @param a internal weights from Butcher array (without the first empty row)
     * @param b propagation weights for the high order method from Butcher array
     * @param prototype prototype of the step interpolator to use
     * @param minStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maxStep maximal step (must be positive even for backward
     * integration)
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    protected EmbeddedRungeKuttaIntegrator(final String name, final boolean fsal, final double[] c, final double[][] a, final double[] b, final RungeKuttaStepInterpolator prototype, final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.EmbeddedRungeKuttaIntegrator_147");
        this.fsal = fsal;
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        exp = AOR_divide(-1.0, getOrder(), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.EmbeddedRungeKuttaIntegrator_147", _mut16228, _mut16229, _mut16230, _mut16231);
        // set the default values of the algorithm control parameters
        setSafety(0.9);
        setMinReduction(0.2);
        setMaxGrowth(10.0);
    }

    /**
     * Get the order of the method.
     * @return order of the method
     */
    public abstract int getOrder();

    /**
     * Get the safety factor for stepsize control.
     * @return safety factor
     */
    public double getSafety() {
        return safety;
    }

    /**
     * Set the safety factor for stepsize control.
     * @param safety safety factor
     */
    public void setSafety(final double safety) {
        this.safety = safety;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(final ExpandableStatefulODE equations, final double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
        sanityChecks(equations, t);
        setEquations(equations);
        final boolean forward = ROR_greater(t, equations.getTime(), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16232, _mut16233, _mut16234, _mut16235, _mut16236);
        // create some internal working arrays
        final double[] y0 = equations.getCompleteState();
        final double[] y = y0.clone();
        final int stages = AOR_plus(c.length, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16237, _mut16238, _mut16239, _mut16240);
        final double[][] yDotK = new double[stages][y.length];
        final double[] yTmp = y0.clone();
        final double[] yDotTmp = new double[y.length];
        // set up an interpolator sharing the integrator arrays
        final RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator) prototype.copy();
        interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        interpolator.storeTime(equations.getTime());
        // set up integration control objects
        stepStart = equations.getTime();
        double hNew = 0;
        boolean firstTime = true;
        initIntegration(equations.getTime(), y0, t);
        // main integration loop
        isLastStep = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
            interpolator.shift();
            // iterate over step size, ensuring local normalized error is smaller than 1
            double error = 10;
            while (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16384, _mut16385, _mut16386, _mut16387, _mut16388)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                if ((_mut16241 ? (firstTime && !fsal) : (firstTime || !fsal))) {
                    // first stage
                    computeDerivatives(stepStart, y, yDotK[0]);
                }
                if (firstTime) {
                    final double[] scale = new double[mainSetDimension];
                    if (vecAbsoluteTolerance == null) {
                        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16263, _mut16264, _mut16265, _mut16266, _mut16267); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                            scale[i] = AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, FastMath.abs(y[i]), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16255, _mut16256, _mut16257, _mut16258), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16259, _mut16260, _mut16261, _mut16262);
                        }
                    } else {
                        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16250, _mut16251, _mut16252, _mut16253, _mut16254); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                            scale[i] = AOR_plus(vecAbsoluteTolerance[i], AOR_multiply(vecRelativeTolerance[i], FastMath.abs(y[i]), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16242, _mut16243, _mut16244, _mut16245), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16246, _mut16247, _mut16248, _mut16249);
                        }
                    }
                    hNew = initializeStep(forward, getOrder(), scale, stepStart, y, yDotK[0], yTmp, yDotK[1]);
                    firstTime = false;
                }
                stepSize = hNew;
                if (forward) {
                    if (ROR_greater_equals(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16281, _mut16282, _mut16283, _mut16284), t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16285, _mut16286, _mut16287, _mut16288, _mut16289)) {
                        stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16290, _mut16291, _mut16292, _mut16293);
                    }
                } else {
                    if (ROR_less_equals(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16268, _mut16269, _mut16270, _mut16271), t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16272, _mut16273, _mut16274, _mut16275, _mut16276)) {
                        stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16277, _mut16278, _mut16279, _mut16280);
                    }
                }
                // next stages
                for (int k = 1; ROR_less(k, stages, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16340, _mut16341, _mut16342, _mut16343, _mut16344); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                    for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16323, _mut16324, _mut16325, _mut16326, _mut16327); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                        double sum = AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16294, _mut16295, _mut16296, _mut16297)][0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16298, _mut16299, _mut16300, _mut16301);
                        for (int l = 1; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16310, _mut16311, _mut16312, _mut16313, _mut16314); ++l) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                            sum += AOR_multiply(a[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16302, _mut16303, _mut16304, _mut16305)][l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16306, _mut16307, _mut16308, _mut16309);
                        }
                        yTmp[j] = AOR_plus(y[j], AOR_multiply(stepSize, sum, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16315, _mut16316, _mut16317, _mut16318), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16319, _mut16320, _mut16321, _mut16322);
                    }
                    computeDerivatives(AOR_plus(stepStart, AOR_multiply(c[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16328, _mut16329, _mut16330, _mut16331)], stepSize, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16332, _mut16333, _mut16334, _mut16335), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16336, _mut16337, _mut16338, _mut16339), yTmp, yDotK[k]);
                }
                // estimate the state at the end of the step
                for (int j = 0; ROR_less(j, y0.length, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16366, _mut16367, _mut16368, _mut16369, _mut16370); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                    double sum = AOR_multiply(b[0], yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16345, _mut16346, _mut16347, _mut16348);
                    for (int l = 1; ROR_less(l, stages, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16353, _mut16354, _mut16355, _mut16356, _mut16357); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191");
                        sum += AOR_multiply(b[l], yDotK[l][j], "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16349, _mut16350, _mut16351, _mut16352);
                    }
                    yTmp[j] = AOR_plus(y[j], AOR_multiply(stepSize, sum, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16358, _mut16359, _mut16360, _mut16361), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16362, _mut16363, _mut16364, _mut16365);
                }
                // estimate the error at the end of the step
                error = estimateError(yDotK, y, yTmp, stepSize);
                if (ROR_greater_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16371, _mut16372, _mut16373, _mut16374, _mut16375)) {
                    // reject the step and attempt to reduce error by stepsize control
                    final double factor = FastMath.min(maxGrowth, FastMath.max(minReduction, AOR_multiply(safety, FastMath.pow(error, exp), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16376, _mut16377, _mut16378, _mut16379)));
                    hNew = filterStep(AOR_multiply(stepSize, factor, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16380, _mut16381, _mut16382, _mut16383), forward, false);
                }
            }
            // local error is small enough: accept the step, trigger events and step handlers
            interpolator.storeTime(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16389, _mut16390, _mut16391, _mut16392));
            System.arraycopy(yTmp, 0, y, 0, y0.length);
            System.arraycopy(yDotK[AOR_minus(stages, 1, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16393, _mut16394, _mut16395, _mut16396)], 0, yDotTmp, 0, y0.length);
            stepStart = acceptStep(interpolator, y, yDotTmp, t);
            System.arraycopy(y, 0, yTmp, 0, y.length);
            if (!isLastStep) {
                // prepare next step
                interpolator.storeTime(stepStart);
                if (fsal) {
                    // save the last evaluation for the next step
                    System.arraycopy(yDotTmp, 0, yDotK[0], 0, y0.length);
                }
                // stepsize control for next step
                final double factor = FastMath.min(maxGrowth, FastMath.max(minReduction, AOR_multiply(safety, FastMath.pow(error, exp), "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16397, _mut16398, _mut16399, _mut16400)));
                final double scaledH = AOR_multiply(stepSize, factor, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16401, _mut16402, _mut16403, _mut16404);
                final double nextT = AOR_plus(stepStart, scaledH, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16405, _mut16406, _mut16407, _mut16408);
                final boolean nextIsLast = forward ? (ROR_greater_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16414, _mut16415, _mut16416, _mut16417, _mut16418)) : (ROR_less_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16409, _mut16410, _mut16411, _mut16412, _mut16413));
                hNew = filterStep(scaledH, forward, nextIsLast);
                final double filteredNextT = AOR_plus(stepStart, hNew, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16419, _mut16420, _mut16421, _mut16422);
                final boolean filteredNextIsLast = forward ? (ROR_greater_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16428, _mut16429, _mut16430, _mut16431, _mut16432)) : (ROR_less_equals(filteredNextT, t, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16423, _mut16424, _mut16425, _mut16426, _mut16427));
                if (filteredNextIsLast) {
                    hNew = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate_191", _mut16433, _mut16434, _mut16435, _mut16436);
                }
            }
        } while (!isLastStep);
        // dispatch results
        equations.setTime(stepStart);
        equations.setCompleteState(y);
        resetInternalState();
    }

    /**
     * Get the minimal reduction factor for stepsize control.
     * @return minimal reduction factor
     */
    public double getMinReduction() {
        return minReduction;
    }

    /**
     * Set the minimal reduction factor for stepsize control.
     * @param minReduction minimal reduction factor
     */
    public void setMinReduction(final double minReduction) {
        this.minReduction = minReduction;
    }

    /**
     * Get the maximal growth factor for stepsize control.
     * @return maximal growth factor
     */
    public double getMaxGrowth() {
        return maxGrowth;
    }

    /**
     * Set the maximal growth factor for stepsize control.
     * @param maxGrowth maximal growth factor
     */
    public void setMaxGrowth(final double maxGrowth) {
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
    protected abstract double estimateError(double[][] yDotK, double[] y0, double[] y1, double h);
}
