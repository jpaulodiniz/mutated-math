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

import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class DormandPrince54StepInterpolator extends RungeKuttaStepInterpolator {

    @Conditional
    public static boolean _mut17221 = false, _mut17222 = false, _mut17223 = false, _mut17224 = false, _mut17225 = false, _mut17226 = false, _mut17227 = false, _mut17228 = false, _mut17229 = false, _mut17230 = false, _mut17231 = false, _mut17232 = false, _mut17233 = false, _mut17234 = false, _mut17235 = false, _mut17236 = false, _mut17237 = false, _mut17238 = false, _mut17239 = false, _mut17240 = false, _mut17241 = false, _mut17242 = false, _mut17243 = false, _mut17244 = false, _mut17245 = false, _mut17246 = false, _mut17247 = false, _mut17248 = false, _mut17249 = false, _mut17250 = false, _mut17251 = false, _mut17252 = false, _mut17253 = false, _mut17254 = false, _mut17255 = false, _mut17256 = false, _mut17257 = false, _mut17258 = false, _mut17259 = false, _mut17260 = false, _mut17261 = false, _mut17262 = false, _mut17263 = false, _mut17264 = false, _mut17265 = false, _mut17266 = false, _mut17267 = false, _mut17268 = false, _mut17269 = false, _mut17270 = false, _mut17271 = false, _mut17272 = false, _mut17273 = false, _mut17274 = false, _mut17275 = false, _mut17276 = false, _mut17277 = false, _mut17278 = false, _mut17279 = false, _mut17280 = false, _mut17281 = false, _mut17282 = false, _mut17283 = false, _mut17284 = false, _mut17285 = false, _mut17286 = false, _mut17287 = false, _mut17288 = false, _mut17289 = false, _mut17290 = false, _mut17291 = false, _mut17292 = false, _mut17293 = false, _mut17294 = false, _mut17295 = false, _mut17296 = false, _mut17297 = false, _mut17298 = false, _mut17299 = false, _mut17300 = false, _mut17301 = false, _mut17302 = false, _mut17303 = false, _mut17304 = false, _mut17305 = false, _mut17306 = false, _mut17307 = false, _mut17308 = false, _mut17309 = false, _mut17310 = false, _mut17311 = false, _mut17312 = false, _mut17313 = false, _mut17314 = false, _mut17315 = false, _mut17316 = false, _mut17317 = false, _mut17318 = false, _mut17319 = false, _mut17320 = false, _mut17321 = false, _mut17322 = false, _mut17323 = false, _mut17324 = false, _mut17325 = false, _mut17326 = false, _mut17327 = false, _mut17328 = false, _mut17329 = false, _mut17330 = false, _mut17331 = false, _mut17332 = false, _mut17333 = false, _mut17334 = false, _mut17335 = false, _mut17336 = false, _mut17337 = false, _mut17338 = false, _mut17339 = false, _mut17340 = false, _mut17341 = false, _mut17342 = false, _mut17343 = false, _mut17344 = false, _mut17345 = false, _mut17346 = false, _mut17347 = false, _mut17348 = false, _mut17349 = false, _mut17350 = false, _mut17351 = false, _mut17352 = false, _mut17353 = false, _mut17354 = false, _mut17355 = false, _mut17356 = false, _mut17357 = false, _mut17358 = false, _mut17359 = false, _mut17360 = false, _mut17361 = false, _mut17362 = false, _mut17363 = false, _mut17364 = false, _mut17365 = false, _mut17366 = false, _mut17367 = false, _mut17368 = false, _mut17369 = false, _mut17370 = false, _mut17371 = false, _mut17372 = false, _mut17373 = false, _mut17374 = false, _mut17375 = false, _mut17376 = false, _mut17377 = false, _mut17378 = false, _mut17379 = false, _mut17380 = false, _mut17381 = false, _mut17382 = false, _mut17383 = false, _mut17384 = false, _mut17385 = false, _mut17386 = false, _mut17387 = false, _mut17388 = false, _mut17389 = false, _mut17390 = false, _mut17391 = false, _mut17392 = false, _mut17393 = false, _mut17394 = false, _mut17395 = false, _mut17396 = false, _mut17397 = false, _mut17398 = false, _mut17399 = false, _mut17400 = false, _mut17401 = false, _mut17402 = false, _mut17403 = false, _mut17404 = false, _mut17405 = false, _mut17406 = false, _mut17407 = false, _mut17408 = false, _mut17409 = false, _mut17410 = false, _mut17411 = false, _mut17412 = false, _mut17413 = false, _mut17414 = false, _mut17415 = false, _mut17416 = false, _mut17417 = false, _mut17418 = false, _mut17419 = false, _mut17420 = false, _mut17421 = false, _mut17422 = false, _mut17423 = false, _mut17424 = false, _mut17425 = false, _mut17426 = false, _mut17427 = false, _mut17428 = false, _mut17429 = false, _mut17430 = false, _mut17431 = false, _mut17432 = false, _mut17433 = false, _mut17434 = false, _mut17435 = false, _mut17436 = false, _mut17437 = false, _mut17438 = false, _mut17439 = false, _mut17440 = false, _mut17441 = false, _mut17442 = false, _mut17443 = false, _mut17444 = false, _mut17445 = false, _mut17446 = false, _mut17447 = false, _mut17448 = false, _mut17449 = false, _mut17450 = false, _mut17451 = false, _mut17452 = false, _mut17453 = false, _mut17454 = false, _mut17455 = false, _mut17456 = false, _mut17457 = false, _mut17458 = false, _mut17459 = false, _mut17460 = false, _mut17461 = false, _mut17462 = false, _mut17463 = false, _mut17464 = false, _mut17465 = false, _mut17466 = false, _mut17467 = false, _mut17468 = false, _mut17469 = false, _mut17470 = false, _mut17471 = false, _mut17472 = false, _mut17473 = false, _mut17474 = false, _mut17475 = false, _mut17476 = false, _mut17477 = false, _mut17478 = false, _mut17479 = false, _mut17480 = false, _mut17481 = false, _mut17482 = false, _mut17483 = false, _mut17484 = false, _mut17485 = false, _mut17486 = false, _mut17487 = false, _mut17488 = false, _mut17489 = false, _mut17490 = false, _mut17491 = false, _mut17492 = false, _mut17493 = false, _mut17494 = false, _mut17495 = false, _mut17496 = false, _mut17497 = false, _mut17498 = false, _mut17499 = false, _mut17500 = false, _mut17501 = false, _mut17502 = false, _mut17503 = false, _mut17504 = false, _mut17505 = false, _mut17506 = false, _mut17507 = false, _mut17508 = false, _mut17509 = false, _mut17510 = false, _mut17511 = false, _mut17512 = false, _mut17513 = false, _mut17514 = false, _mut17515 = false, _mut17516 = false, _mut17517 = false, _mut17518 = false, _mut17519 = false, _mut17520 = false, _mut17521 = false, _mut17522 = false, _mut17523 = false, _mut17524 = false, _mut17525 = false, _mut17526 = false, _mut17527 = false, _mut17528 = false, _mut17529 = false, _mut17530 = false, _mut17531 = false, _mut17532 = false, _mut17533 = false;

    /**
     * Last row of the Butcher-array internal weights, element 0.
     */
    private static final double A70 = AOR_divide(35.0, 384.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17221, _mut17222, _mut17223, _mut17224);

    /**
     * Last row of the Butcher-array internal weights, element 2.
     */
    private static final double A72 = AOR_divide(500.0, 1113.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17225, _mut17226, _mut17227, _mut17228);

    /**
     * Last row of the Butcher-array internal weights, element 3.
     */
    private static final double A73 = AOR_divide(125.0, 192.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17229, _mut17230, _mut17231, _mut17232);

    /**
     * Last row of the Butcher-array internal weights, element 4.
     */
    private static final double A74 = AOR_divide(-2187.0, 6784.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17233, _mut17234, _mut17235, _mut17236);

    /**
     * Last row of the Butcher-array internal weights, element 5.
     */
    private static final double A75 = AOR_divide(11.0, 84.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17237, _mut17238, _mut17239, _mut17240);

    /**
     * Shampine (1986) Dense output, element 0.
     */
    private static final double D0 = AOR_divide(-12715105075.0, 11282082432.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17241, _mut17242, _mut17243, _mut17244);

    /**
     * Shampine (1986) Dense output, element 2.
     */
    private static final double D2 = AOR_divide(87487479700.0, 32700410799.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17245, _mut17246, _mut17247, _mut17248);

    /**
     * Shampine (1986) Dense output, element 3.
     */
    private static final double D3 = AOR_divide(-10690763975.0, 1880347072.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17249, _mut17250, _mut17251, _mut17252);

    /**
     * Shampine (1986) Dense output, element 4.
     */
    private static final double D4 = AOR_divide(701980252875.0, 199316789632.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17253, _mut17254, _mut17255, _mut17256);

    /**
     * Shampine (1986) Dense output, element 5.
     */
    private static final double D5 = AOR_divide(-1453857185.0, 822651844.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17257, _mut17258, _mut17259, _mut17260);

    /**
     * Shampine (1986) Dense output, element 6.
     */
    private static final double D6 = AOR_divide(69997945.0, 29380423.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.end_396", _mut17261, _mut17262, _mut17263, _mut17264);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20111120L;

    /**
     * First vector for interpolation.
     */
    private double[] v1;

    /**
     * Second vector for interpolation.
     */
    private double[] v2;

    /**
     * Third vector for interpolation.
     */
    private double[] v3;

    /**
     * Fourth vector for interpolation.
     */
    private double[] v4;

    /**
     * Initialization indicator for the interpolation vectors.
     */
    private boolean vectorsInitialized;

    // the public modifier here is needed for serialization
    public DormandPrince54StepInterpolator() {
        super();
        v1 = null;
        v2 = null;
        v3 = null;
        v4 = null;
        vectorsInitialized = false;
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    DormandPrince54StepInterpolator(final DormandPrince54StepInterpolator interpolator) {
        super(interpolator);
        if (interpolator.v1 == null) {
            v1 = null;
            v2 = null;
            v3 = null;
            v4 = null;
            vectorsInitialized = false;
        } else {
            v1 = interpolator.v1.clone();
            v2 = interpolator.v2.clone();
            v3 = interpolator.v3.clone();
            v4 = interpolator.v4.clone();
            vectorsInitialized = interpolator.vectorsInitialized;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new DormandPrince54StepInterpolator(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reinitialize(final AbstractIntegrator integrator, final double[] y, final double[][] yDotK, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        super.reinitialize(integrator, y, yDotK, forward, primaryMapper, secondaryMappers);
        v1 = null;
        v2 = null;
        v3 = null;
        v4 = null;
        vectorsInitialized = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeTime(final double t) {
        super.storeTime(t);
        vectorsInitialized = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170");
        if (!vectorsInitialized) {
            if (v1 == null) {
                v1 = new double[interpolatedState.length];
                v2 = new double[interpolatedState.length];
                v3 = new double[interpolatedState.length];
                v4 = new double[interpolatedState.length];
            }
            // we need to compute the interpolation vectors for this time step
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17357, _mut17358, _mut17359, _mut17360, _mut17361); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170");
                final double yDot0 = yDotK[0][i];
                final double yDot2 = yDotK[2][i];
                final double yDot3 = yDotK[3][i];
                final double yDot4 = yDotK[4][i];
                final double yDot5 = yDotK[5][i];
                final double yDot6 = yDotK[6][i];
                v1[i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(A70, yDot0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17265, _mut17266, _mut17267, _mut17268), AOR_multiply(A72, yDot2, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17269, _mut17270, _mut17271, _mut17272), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17273, _mut17274, _mut17275, _mut17276), AOR_multiply(A73, yDot3, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17277, _mut17278, _mut17279, _mut17280), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17281, _mut17282, _mut17283, _mut17284), AOR_multiply(A74, yDot4, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17285, _mut17286, _mut17287, _mut17288), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17289, _mut17290, _mut17291, _mut17292), AOR_multiply(A75, yDot5, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17293, _mut17294, _mut17295, _mut17296), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17297, _mut17298, _mut17299, _mut17300);
                v2[i] = AOR_minus(yDot0, v1[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17301, _mut17302, _mut17303, _mut17304);
                v3[i] = AOR_minus(AOR_minus(v1[i], v2[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17305, _mut17306, _mut17307, _mut17308), yDot6, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17309, _mut17310, _mut17311, _mut17312);
                v4[i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(D0, yDot0, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17313, _mut17314, _mut17315, _mut17316), AOR_multiply(D2, yDot2, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17317, _mut17318, _mut17319, _mut17320), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17321, _mut17322, _mut17323, _mut17324), AOR_multiply(D3, yDot3, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17325, _mut17326, _mut17327, _mut17328), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17329, _mut17330, _mut17331, _mut17332), AOR_multiply(D4, yDot4, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17333, _mut17334, _mut17335, _mut17336), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17337, _mut17338, _mut17339, _mut17340), AOR_multiply(D5, yDot5, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17341, _mut17342, _mut17343, _mut17344), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17345, _mut17346, _mut17347, _mut17348), AOR_multiply(D6, yDot6, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17349, _mut17350, _mut17351, _mut17352), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17353, _mut17354, _mut17355, _mut17356);
            }
            vectorsInitialized = true;
        }
        // interpolate
        final double eta = AOR_minus(1, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17362, _mut17363, _mut17364, _mut17365);
        final double twoTheta = AOR_multiply(2, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17366, _mut17367, _mut17368, _mut17369);
        final double dot2 = AOR_minus(1, twoTheta, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17370, _mut17371, _mut17372, _mut17373);
        final double dot3 = AOR_multiply(theta, (AOR_minus(2, AOR_multiply(3, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17374, _mut17375, _mut17376, _mut17377), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17378, _mut17379, _mut17380, _mut17381)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17382, _mut17383, _mut17384, _mut17385);
        final double dot4 = AOR_multiply(twoTheta, (AOR_plus(1, AOR_multiply(theta, (AOR_minus(twoTheta, 3, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17386, _mut17387, _mut17388, _mut17389)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17390, _mut17391, _mut17392, _mut17393), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17394, _mut17395, _mut17396, _mut17397)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17398, _mut17399, _mut17400, _mut17401);
        if ((_mut17407 ? ((previousState != null) || (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17402, _mut17403, _mut17404, _mut17405, _mut17406))) : ((previousState != null) && (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17402, _mut17403, _mut17404, _mut17405, _mut17406))))) {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17529, _mut17530, _mut17531, _mut17532, _mut17533); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170");
                interpolatedState[i] = AOR_plus(previousState[i], AOR_multiply(AOR_multiply(theta, h, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17469, _mut17470, _mut17471, _mut17472), (AOR_plus(v1[i], AOR_multiply(eta, (AOR_plus(v2[i], AOR_multiply(theta, (AOR_plus(v3[i], AOR_multiply(eta, v4[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17473, _mut17474, _mut17475, _mut17476), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17477, _mut17478, _mut17479, _mut17480)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17481, _mut17482, _mut17483, _mut17484), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17485, _mut17486, _mut17487, _mut17488)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17489, _mut17490, _mut17491, _mut17492), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17493, _mut17494, _mut17495, _mut17496)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17497, _mut17498, _mut17499, _mut17500), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17501, _mut17502, _mut17503, _mut17504);
                interpolatedDerivatives[i] = AOR_plus(AOR_plus(AOR_plus(v1[i], AOR_multiply(dot2, v2[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17505, _mut17506, _mut17507, _mut17508), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17509, _mut17510, _mut17511, _mut17512), AOR_multiply(dot3, v3[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17513, _mut17514, _mut17515, _mut17516), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17517, _mut17518, _mut17519, _mut17520), AOR_multiply(dot4, v4[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17521, _mut17522, _mut17523, _mut17524), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17525, _mut17526, _mut17527, _mut17528);
            }
        } else {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17464, _mut17465, _mut17466, _mut17467, _mut17468); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170");
                interpolatedState[i] = AOR_minus(currentState[i], AOR_multiply(oneMinusThetaH, (AOR_minus(v1[i], AOR_multiply(theta, (AOR_plus(v2[i], AOR_multiply(theta, (AOR_plus(v3[i], AOR_multiply(eta, v4[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17408, _mut17409, _mut17410, _mut17411), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17412, _mut17413, _mut17414, _mut17415)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17416, _mut17417, _mut17418, _mut17419), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17420, _mut17421, _mut17422, _mut17423)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17424, _mut17425, _mut17426, _mut17427), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17428, _mut17429, _mut17430, _mut17431)), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17432, _mut17433, _mut17434, _mut17435), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17436, _mut17437, _mut17438, _mut17439);
                interpolatedDerivatives[i] = AOR_plus(AOR_plus(AOR_plus(v1[i], AOR_multiply(dot2, v2[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17440, _mut17441, _mut17442, _mut17443), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17444, _mut17445, _mut17446, _mut17447), AOR_multiply(dot3, v3[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17448, _mut17449, _mut17450, _mut17451), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17452, _mut17453, _mut17454, _mut17455), AOR_multiply(dot4, v4[i], "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17456, _mut17457, _mut17458, _mut17459), "org.apache.commons.math3.ode.nonstiff.DormandPrince54StepInterpolator.computeInterpolatedStateAndDerivatives_170", _mut17460, _mut17461, _mut17462, _mut17463);
            }
        }
    }
}
