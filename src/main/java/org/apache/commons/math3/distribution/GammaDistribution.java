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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Gamma distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Gamma_distribution">Gamma distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/GammaDistribution.html">Gamma distribution (MathWorld)</a>
 */
public class GammaDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut58134 = false, _mut58135 = false, _mut58136 = false, _mut58137 = false, _mut58138 = false, _mut58139 = false, _mut58140 = false, _mut58141 = false, _mut58142 = false, _mut58143 = false, _mut58144 = false, _mut58145 = false, _mut58146 = false, _mut58147 = false, _mut58148 = false, _mut58149 = false, _mut58150 = false, _mut58151 = false, _mut58152 = false, _mut58153 = false, _mut58154 = false, _mut58155 = false, _mut58156 = false, _mut58157 = false, _mut58158 = false, _mut58159 = false, _mut58160 = false, _mut58161 = false, _mut58162 = false, _mut58163 = false, _mut58164 = false, _mut58165 = false, _mut58166 = false, _mut58167 = false, _mut58168 = false, _mut58169 = false, _mut58170 = false, _mut58171 = false, _mut58172 = false, _mut58173 = false, _mut58174 = false, _mut58175 = false, _mut58176 = false, _mut58177 = false, _mut58178 = false, _mut58179 = false, _mut58180 = false, _mut58181 = false, _mut58182 = false, _mut58183 = false, _mut58184 = false, _mut58185 = false, _mut58186 = false, _mut58187 = false, _mut58188 = false, _mut58189 = false, _mut58190 = false, _mut58191 = false, _mut58192 = false, _mut58193 = false, _mut58194 = false, _mut58195 = false, _mut58196 = false, _mut58197 = false, _mut58198 = false, _mut58199 = false, _mut58200 = false, _mut58201 = false, _mut58202 = false, _mut58203 = false, _mut58204 = false, _mut58205 = false, _mut58206 = false, _mut58207 = false, _mut58208 = false, _mut58209 = false, _mut58210 = false, _mut58211 = false, _mut58212 = false, _mut58213 = false, _mut58214 = false, _mut58215 = false, _mut58216 = false, _mut58217 = false, _mut58218 = false, _mut58219 = false, _mut58220 = false, _mut58221 = false, _mut58222 = false, _mut58223 = false, _mut58224 = false, _mut58225 = false, _mut58226 = false, _mut58227 = false, _mut58228 = false, _mut58229 = false, _mut58230 = false, _mut58231 = false, _mut58232 = false, _mut58233 = false, _mut58234 = false, _mut58235 = false, _mut58236 = false, _mut58237 = false, _mut58238 = false, _mut58239 = false, _mut58240 = false, _mut58241 = false, _mut58242 = false, _mut58243 = false, _mut58244 = false, _mut58245 = false, _mut58246 = false, _mut58247 = false, _mut58248 = false, _mut58249 = false, _mut58250 = false, _mut58251 = false, _mut58252 = false, _mut58253 = false, _mut58254 = false, _mut58255 = false, _mut58256 = false, _mut58257 = false, _mut58258 = false, _mut58259 = false, _mut58260 = false, _mut58261 = false, _mut58262 = false, _mut58263 = false, _mut58264 = false, _mut58265 = false, _mut58266 = false, _mut58267 = false, _mut58268 = false, _mut58269 = false, _mut58270 = false, _mut58271 = false, _mut58272 = false, _mut58273 = false, _mut58274 = false, _mut58275 = false, _mut58276 = false, _mut58277 = false, _mut58278 = false, _mut58279 = false, _mut58280 = false, _mut58281 = false, _mut58282 = false, _mut58283 = false, _mut58284 = false, _mut58285 = false, _mut58286 = false, _mut58287 = false, _mut58288 = false, _mut58289 = false, _mut58290 = false, _mut58291 = false, _mut58292 = false, _mut58293 = false, _mut58294 = false, _mut58295 = false, _mut58296 = false, _mut58297 = false, _mut58298 = false, _mut58299 = false, _mut58300 = false, _mut58301 = false, _mut58302 = false, _mut58303 = false, _mut58304 = false, _mut58305 = false, _mut58306 = false, _mut58307 = false, _mut58308 = false, _mut58309 = false, _mut58310 = false, _mut58311 = false, _mut58312 = false, _mut58313 = false, _mut58314 = false, _mut58315 = false, _mut58316 = false, _mut58317 = false, _mut58318 = false, _mut58319 = false, _mut58320 = false, _mut58321 = false, _mut58322 = false, _mut58323 = false, _mut58324 = false, _mut58325 = false, _mut58326 = false, _mut58327 = false, _mut58328 = false, _mut58329 = false, _mut58330 = false, _mut58331 = false, _mut58332 = false, _mut58333 = false, _mut58334 = false, _mut58335 = false, _mut58336 = false, _mut58337 = false, _mut58338 = false, _mut58339 = false, _mut58340 = false, _mut58341 = false, _mut58342 = false, _mut58343 = false, _mut58344 = false, _mut58345 = false, _mut58346 = false, _mut58347 = false, _mut58348 = false, _mut58349 = false, _mut58350 = false, _mut58351 = false, _mut58352 = false, _mut58353 = false, _mut58354 = false, _mut58355 = false, _mut58356 = false, _mut58357 = false, _mut58358 = false, _mut58359 = false, _mut58360 = false, _mut58361 = false, _mut58362 = false, _mut58363 = false, _mut58364 = false, _mut58365 = false, _mut58366 = false, _mut58367 = false, _mut58368 = false, _mut58369 = false, _mut58370 = false, _mut58371 = false, _mut58372 = false, _mut58373 = false, _mut58374 = false, _mut58375 = false, _mut58376 = false, _mut58377 = false, _mut58378 = false, _mut58379 = false, _mut58380 = false, _mut58381 = false, _mut58382 = false, _mut58383 = false, _mut58384 = false, _mut58385 = false, _mut58386 = false, _mut58387 = false, _mut58388 = false, _mut58389 = false, _mut58390 = false, _mut58391 = false, _mut58392 = false, _mut58393 = false, _mut58394 = false, _mut58395 = false, _mut58396 = false, _mut58397 = false, _mut58398 = false, _mut58399 = false, _mut58400 = false, _mut58401 = false, _mut58402 = false, _mut58403 = false, _mut58404 = false, _mut58405 = false, _mut58406 = false, _mut58407 = false, _mut58408 = false, _mut58409 = false, _mut58410 = false, _mut58411 = false, _mut58412 = false, _mut58413 = false, _mut58414 = false, _mut58415 = false, _mut58416 = false, _mut58417 = false, _mut58418 = false, _mut58419 = false, _mut58420 = false, _mut58421 = false, _mut58422 = false, _mut58423 = false, _mut58424 = false, _mut58425 = false, _mut58426 = false, _mut58427 = false, _mut58428 = false, _mut58429 = false, _mut58430 = false, _mut58431 = false, _mut58432 = false, _mut58433 = false, _mut58434 = false, _mut58435 = false, _mut58436 = false, _mut58437 = false, _mut58438 = false, _mut58439 = false, _mut58440 = false, _mut58441 = false, _mut58442 = false, _mut58443 = false, _mut58444 = false, _mut58445 = false, _mut58446 = false, _mut58447 = false, _mut58448 = false, _mut58449 = false, _mut58450 = false, _mut58451 = false, _mut58452 = false, _mut58453 = false, _mut58454 = false, _mut58455 = false, _mut58456 = false, _mut58457 = false, _mut58458 = false, _mut58459 = false, _mut58460 = false, _mut58461 = false, _mut58462 = false, _mut58463 = false, _mut58464 = false, _mut58465 = false, _mut58466 = false, _mut58467 = false, _mut58468 = false, _mut58469 = false, _mut58470 = false, _mut58471 = false, _mut58472 = false, _mut58473 = false, _mut58474 = false, _mut58475 = false, _mut58476 = false, _mut58477 = false, _mut58478 = false, _mut58479 = false, _mut58480 = false, _mut58481 = false, _mut58482 = false, _mut58483 = false, _mut58484 = false, _mut58485 = false, _mut58486 = false, _mut58487 = false, _mut58488 = false, _mut58489 = false, _mut58490 = false, _mut58491 = false, _mut58492 = false, _mut58493 = false, _mut58494 = false, _mut58495 = false, _mut58496 = false, _mut58497 = false, _mut58498 = false, _mut58499 = false, _mut58500 = false, _mut58501 = false, _mut58502 = false, _mut58503 = false, _mut58504 = false, _mut58505 = false, _mut58506 = false, _mut58507 = false, _mut58508 = false, _mut58509 = false, _mut58510 = false, _mut58511 = false, _mut58512 = false, _mut58513 = false, _mut58514 = false, _mut58515 = false, _mut58516 = false, _mut58517 = false, _mut58518 = false, _mut58519 = false, _mut58520 = false, _mut58521 = false, _mut58522 = false, _mut58523 = false, _mut58524 = false, _mut58525 = false, _mut58526 = false, _mut58527 = false, _mut58528 = false, _mut58529 = false, _mut58530 = false, _mut58531 = false, _mut58532 = false, _mut58533 = false, _mut58534 = false, _mut58535 = false, _mut58536 = false, _mut58537 = false, _mut58538 = false, _mut58539 = false, _mut58540 = false, _mut58541 = false, _mut58542 = false, _mut58543 = false, _mut58544 = false, _mut58545 = false, _mut58546 = false, _mut58547 = false, _mut58548 = false, _mut58549 = false, _mut58550 = false, _mut58551 = false, _mut58552 = false, _mut58553 = false, _mut58554 = false, _mut58555 = false, _mut58556 = false, _mut58557 = false, _mut58558 = false, _mut58559 = false, _mut58560 = false, _mut58561 = false, _mut58562 = false, _mut58563 = false, _mut58564 = false, _mut58565 = false, _mut58566 = false, _mut58567 = false, _mut58568 = false, _mut58569 = false, _mut58570 = false, _mut58571 = false, _mut58572 = false, _mut58573 = false, _mut58574 = false, _mut58575 = false, _mut58576 = false, _mut58577 = false, _mut58578 = false, _mut58579 = false, _mut58580 = false, _mut58581 = false, _mut58582 = false, _mut58583 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20120524L;

    /**
     * The shape parameter.
     */
    private final double shape;

    /**
     * The scale parameter.
     */
    private final double scale;

    /**
     * The constant value of {@code shape + g + 0.5}, where {@code g} is the
     * Lanczos constant {@link Gamma#LANCZOS_G}.
     */
    private final double shiftedShape;

    /**
     * The constant value of
     * {@code shape / scale * sqrt(e / (2 * pi * (shape + g + 0.5))) / L(shape)},
     * where {@code L(shape)} is the Lanczos approximation returned by
     * {@link Gamma#lanczos(double)}. This prefactor is used in
     * {@link #density(double)}, when no overflow occurs with the natural
     * calculation.
     */
    private final double densityPrefactor1;

    /**
     * The constant value of
     * {@code log(shape / scale * sqrt(e / (2 * pi * (shape + g + 0.5))) / L(shape))},
     * where {@code L(shape)} is the Lanczos approximation returned by
     * {@link Gamma#lanczos(double)}. This prefactor is used in
     * {@link #logDensity(double)}, when no overflow occurs with the natural
     * calculation.
     */
    private final double logDensityPrefactor1;

    /**
     * The constant value of
     * {@code shape * sqrt(e / (2 * pi * (shape + g + 0.5))) / L(shape)},
     * where {@code L(shape)} is the Lanczos approximation returned by
     * {@link Gamma#lanczos(double)}. This prefactor is used in
     * {@link #density(double)}, when overflow occurs with the natural
     * calculation.
     */
    private final double densityPrefactor2;

    /**
     * The constant value of
     * {@code log(shape * sqrt(e / (2 * pi * (shape + g + 0.5))) / L(shape))},
     * where {@code L(shape)} is the Lanczos approximation returned by
     * {@link Gamma#lanczos(double)}. This prefactor is used in
     * {@link #logDensity(double)}, when overflow occurs with the natural
     * calculation.
     */
    private final double logDensityPrefactor2;

    /**
     * Lower bound on {@code y = x / scale} for the selection of the computation
     * method in {@link #density(double)}. For {@code y <= minY}, the natural
     * calculation overflows.
     */
    private final double minY;

    /**
     * Upper bound on {@code log(y)} ({@code y = x / scale}) for the selection
     * of the computation method in {@link #density(double)}. For
     * {@code log(y) >= maxLogY}, the natural calculation overflows.
     */
    private final double maxLogY;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Creates a new gamma distribution with specified values of the shape and
     * scale parameters.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param shape the shape parameter
     * @param scale the scale parameter
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     */
    public GammaDistribution(double shape, double scale) throws NotStrictlyPositiveException {
        this(shape, scale, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a new gamma distribution with specified values of the shape and
     * scale parameters.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param shape the shape parameter
     * @param scale the scale parameter
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     * @since 2.1
     */
    public GammaDistribution(double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), shape, scale, inverseCumAccuracy);
    }

    /**
     * Creates a Gamma distribution.
     *
     * @param rng Random number generator.
     * @param shape the shape parameter
     * @param scale the scale parameter
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     * @since 3.3
     */
    public GammaDistribution(RandomGenerator rng, double shape, double scale) throws NotStrictlyPositiveException {
        this(rng, shape, scale, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Gamma distribution.
     *
     * @param rng Random number generator.
     * @param shape the shape parameter
     * @param scale the scale parameter
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code shape <= 0} or
     * {@code scale <= 0}.
     * @since 3.1
     */
    public GammaDistribution(RandomGenerator rng, double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173");
        if (ROR_less_equals(shape, 0, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58134, _mut58135, _mut58136, _mut58137, _mut58138)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
        }
        if (ROR_less_equals(scale, 0, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58139, _mut58140, _mut58141, _mut58142, _mut58143)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, scale);
        }
        this.shape = shape;
        this.scale = scale;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
        this.shiftedShape = AOR_plus(AOR_plus(shape, Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58144, _mut58145, _mut58146, _mut58147), 0.5, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58148, _mut58149, _mut58150, _mut58151);
        final double aux = AOR_divide(FastMath.E, (AOR_multiply(AOR_multiply(2.0, FastMath.PI, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58152, _mut58153, _mut58154, _mut58155), shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58156, _mut58157, _mut58158, _mut58159)), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58160, _mut58161, _mut58162, _mut58163);
        this.densityPrefactor2 = AOR_divide(AOR_multiply(shape, FastMath.sqrt(aux), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58164, _mut58165, _mut58166, _mut58167), Gamma.lanczos(shape), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58168, _mut58169, _mut58170, _mut58171);
        this.logDensityPrefactor2 = AOR_minus(AOR_plus(FastMath.log(shape), AOR_multiply(0.5, FastMath.log(aux), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58172, _mut58173, _mut58174, _mut58175), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58176, _mut58177, _mut58178, _mut58179), FastMath.log(Gamma.lanczos(shape)), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58180, _mut58181, _mut58182, _mut58183);
        this.densityPrefactor1 = AOR_multiply(AOR_multiply(AOR_divide(this.densityPrefactor2, scale, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58184, _mut58185, _mut58186, _mut58187), FastMath.pow(shiftedShape, -shape), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58188, _mut58189, _mut58190, _mut58191), FastMath.exp(AOR_plus(shape, Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58192, _mut58193, _mut58194, _mut58195)), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58196, _mut58197, _mut58198, _mut58199);
        this.logDensityPrefactor1 = AOR_plus(AOR_plus(AOR_minus(AOR_minus(this.logDensityPrefactor2, FastMath.log(scale), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58200, _mut58201, _mut58202, _mut58203), AOR_multiply(FastMath.log(shiftedShape), shape, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58204, _mut58205, _mut58206, _mut58207), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58208, _mut58209, _mut58210, _mut58211), shape, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58212, _mut58213, _mut58214, _mut58215), Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58216, _mut58217, _mut58218, _mut58219);
        this.minY = AOR_minus(AOR_plus(shape, Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58220, _mut58221, _mut58222, _mut58223), FastMath.log(Double.MAX_VALUE), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58224, _mut58225, _mut58226, _mut58227);
        this.maxLogY = AOR_divide(FastMath.log(Double.MAX_VALUE), (AOR_minus(shape, 1.0, "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58228, _mut58229, _mut58230, _mut58231)), "org.apache.commons.math3.distribution.GammaDistribution.GammaDistribution_173", _mut58232, _mut58233, _mut58234, _mut58235);
    }

    /**
     * Returns the shape parameter of {@code this} distribution.
     *
     * @return the shape parameter
     * @deprecated as of version 3.1, {@link #getShape()} should be preferred.
     * This method will be removed in version 4.0.
     */
    @Deprecated
    public double getAlpha() {
        return shape;
    }

    /**
     * Returns the shape parameter of {@code this} distribution.
     *
     * @return the shape parameter
     * @since 3.1
     */
    public double getShape() {
        return shape;
    }

    /**
     * Returns the scale parameter of {@code this} distribution.
     *
     * @return the scale parameter
     * @deprecated as of version 3.1, {@link #getScale()} should be preferred.
     * This method will be removed in version 4.0.
     */
    @Deprecated
    public double getBeta() {
        return scale;
    }

    /**
     * Returns the scale parameter of {@code this} distribution.
     *
     * @return the scale parameter
     * @since 3.1
     */
    public double getScale() {
        return scale;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.density_250");
        /* The present method must return the value of
        *
        *     1       x a     - x
        * ---------- (-)  exp(---)
        * x Gamma(a)  b        b
        *
        * where a is the shape parameter, and b the scale parameter.
        * Substituting the Lanczos approximation of Gamma(a) leads to the
        * following expression of the density
        *
        * a              e            1         y      a
        * - sqrt(------------------) ---- (-----------)  exp(a - y + g),
        * x      2 pi (a + g + 0.5)  L(a)  a + g + 0.5
        *
        * where y = x / b. The above formula is the "natural" computation, which
        * is implemented when no overflow is likely to occur. If overflow occurs
        * with the natural computation, the following identity is used. It is
        * based on the BOOST library
        * http://www.boost.org/doc/libs/1_35_0/libs/math/doc/sf_and_dist/html/math_toolkit/special/sf_gamma/igamma.html
        * Formula (15) needs adaptations, which are detailed below.
        *
        *       y      a
        * (-----------)  exp(a - y + g)
        *  a + g + 0.5
        *                              y - a - g - 0.5    y (g + 0.5)
        *               = exp(a log1pm(---------------) - ----------- + g),
        *                                a + g + 0.5      a + g + 0.5
        *
        *  where log1pm(z) = log(1 + z) - z. Therefore, the value to be
        *  returned is
        *
        * a              e            1
        * - sqrt(------------------) ----
        * x      2 pi (a + g + 0.5)  L(a)
        *                              y - a - g - 0.5    y (g + 0.5)
        *               * exp(a log1pm(---------------) - ----------- + g).
        *                                a + g + 0.5      a + g + 0.5
        */
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58236, _mut58237, _mut58238, _mut58239, _mut58240)) {
            return 0;
        }
        final double y = AOR_divide(x, scale, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58241, _mut58242, _mut58243, _mut58244);
        if ((_mut58255 ? ((ROR_less_equals(y, minY, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58245, _mut58246, _mut58247, _mut58248, _mut58249)) && (ROR_greater_equals(FastMath.log(y), maxLogY, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58250, _mut58251, _mut58252, _mut58253, _mut58254))) : ((ROR_less_equals(y, minY, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58245, _mut58246, _mut58247, _mut58248, _mut58249)) || (ROR_greater_equals(FastMath.log(y), maxLogY, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58250, _mut58251, _mut58252, _mut58253, _mut58254))))) {
            /*
             * Overflow.
             */
            final double aux1 = AOR_divide((AOR_minus(y, shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58256, _mut58257, _mut58258, _mut58259)), shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58260, _mut58261, _mut58262, _mut58263);
            final double aux2 = AOR_multiply(shape, (AOR_minus(FastMath.log1p(aux1), aux1, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58264, _mut58265, _mut58266, _mut58267)), "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58268, _mut58269, _mut58270, _mut58271);
            final double aux3 = AOR_plus(AOR_plus(AOR_divide(AOR_multiply(-y, (AOR_plus(Gamma.LANCZOS_G, 0.5, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58272, _mut58273, _mut58274, _mut58275)), "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58276, _mut58277, _mut58278, _mut58279), shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58280, _mut58281, _mut58282, _mut58283), Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58284, _mut58285, _mut58286, _mut58287), aux2, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58288, _mut58289, _mut58290, _mut58291);
            return AOR_multiply(AOR_divide(densityPrefactor2, x, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58292, _mut58293, _mut58294, _mut58295), FastMath.exp(aux3), "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58296, _mut58297, _mut58298, _mut58299);
        }
        /*
         * Natural calculation.
         */
        return AOR_multiply(AOR_multiply(densityPrefactor1, FastMath.exp(-y), "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58300, _mut58301, _mut58302, _mut58303), FastMath.pow(y, AOR_minus(shape, 1, "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58304, _mut58305, _mut58306, _mut58307)), "org.apache.commons.math3.distribution.GammaDistribution.density_250", _mut58308, _mut58309, _mut58310, _mut58311);
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.logDensity_310");
        /*
         * see the comment in {@link #density(double)} for computation details
         */
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58312, _mut58313, _mut58314, _mut58315, _mut58316)) {
            return Double.NEGATIVE_INFINITY;
        }
        final double y = AOR_divide(x, scale, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58317, _mut58318, _mut58319, _mut58320);
        if ((_mut58331 ? ((ROR_less_equals(y, minY, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58321, _mut58322, _mut58323, _mut58324, _mut58325)) && (ROR_greater_equals(FastMath.log(y), maxLogY, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58326, _mut58327, _mut58328, _mut58329, _mut58330))) : ((ROR_less_equals(y, minY, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58321, _mut58322, _mut58323, _mut58324, _mut58325)) || (ROR_greater_equals(FastMath.log(y), maxLogY, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58326, _mut58327, _mut58328, _mut58329, _mut58330))))) {
            /*
             * Overflow.
             */
            final double aux1 = AOR_divide((AOR_minus(y, shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58332, _mut58333, _mut58334, _mut58335)), shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58336, _mut58337, _mut58338, _mut58339);
            final double aux2 = AOR_multiply(shape, (AOR_minus(FastMath.log1p(aux1), aux1, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58340, _mut58341, _mut58342, _mut58343)), "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58344, _mut58345, _mut58346, _mut58347);
            final double aux3 = AOR_plus(AOR_plus(AOR_divide(AOR_multiply(-y, (AOR_plus(Gamma.LANCZOS_G, 0.5, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58348, _mut58349, _mut58350, _mut58351)), "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58352, _mut58353, _mut58354, _mut58355), shiftedShape, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58356, _mut58357, _mut58358, _mut58359), Gamma.LANCZOS_G, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58360, _mut58361, _mut58362, _mut58363), aux2, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58364, _mut58365, _mut58366, _mut58367);
            return AOR_plus(AOR_minus(logDensityPrefactor2, FastMath.log(x), "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58368, _mut58369, _mut58370, _mut58371), aux3, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58372, _mut58373, _mut58374, _mut58375);
        }
        /*
         * Natural calculation.
         */
        return AOR_plus(AOR_minus(logDensityPrefactor1, y, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58376, _mut58377, _mut58378, _mut58379), AOR_multiply(FastMath.log(y), (AOR_minus(shape, 1, "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58380, _mut58381, _mut58382, _mut58383)), "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58384, _mut58385, _mut58386, _mut58387), "org.apache.commons.math3.distribution.GammaDistribution.logDensity_310", _mut58388, _mut58389, _mut58390, _mut58391);
    }

    /**
     * {@inheritDoc}
     *
     * The implementation of this method is based on:
     * <ul>
     *  <li>
     *   <a href="http://mathworld.wolfram.com/Chi-SquaredDistribution.html">
     *    Chi-Squared Distribution</a>, equation (9).
     *  </li>
     *  <li>Casella, G., & Berger, R. (1990). <i>Statistical Inference</i>.
     *    Belmont, CA: Duxbury Press.
     *  </li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.cumulativeProbability_349");
        double ret;
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.GammaDistribution.cumulativeProbability_349", _mut58392, _mut58393, _mut58394, _mut58395, _mut58396)) {
            ret = 0;
        } else {
            ret = Gamma.regularizedGammaP(shape, AOR_divide(x, scale, "org.apache.commons.math3.distribution.GammaDistribution.cumulativeProbability_349", _mut58397, _mut58398, _mut58399, _mut58400));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * For shape parameter {@code alpha} and scale parameter {@code beta}, the
     * mean is {@code alpha * beta}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.getNumericalMean_373");
        return AOR_multiply(shape, scale, "org.apache.commons.math3.distribution.GammaDistribution.getNumericalMean_373", _mut58401, _mut58402, _mut58403, _mut58404);
    }

    /**
     * {@inheritDoc}
     *
     * For shape parameter {@code alpha} and scale parameter {@code beta}, the
     * variance is {@code alpha * beta^2}.
     *
     * @return {@inheritDoc}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.getNumericalVariance_385");
        return AOR_multiply(AOR_multiply(shape, scale, "org.apache.commons.math3.distribution.GammaDistribution.getNumericalVariance_385", _mut58405, _mut58406, _mut58407, _mut58408), scale, "org.apache.commons.math3.distribution.GammaDistribution.getNumericalVariance_385", _mut58409, _mut58410, _mut58411, _mut58412);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
     */
    public double getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity
     * no matter the parameters.
     *
     * @return upper bound of the support (always Double.POSITIVE_INFINITY)
     */
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * <p>This implementation uses the following algorithms: </p>
     *
     * <p>For 0 < shape < 1: <br/>
     * Ahrens, J. H. and Dieter, U., <i>Computer methods for
     * sampling from gamma, beta, Poisson and binomial distributions.</i>
     * Computing, 12, 223-246, 1974.</p>
     *
     * <p>For shape >= 1: <br/>
     * Marsaglia and Tsang, <i>A Simple Method for Generating
     * Gamma Variables.</i> ACM Transactions on Mathematical Software,
     * Volume 26 Issue 3, September, 2000.</p>
     *
     * @return random value sampled from the Gamma(shape, scale) distribution
     */
    @Override
    public double sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.sample_448");
        if (ROR_less(shape, 1, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58413, _mut58414, _mut58415, _mut58416, _mut58417)) {
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.sample_448");
                // Step 1:
                final double u = random.nextDouble();
                final double bGS = AOR_plus(1, AOR_divide(shape, FastMath.E, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58418, _mut58419, _mut58420, _mut58421), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58422, _mut58423, _mut58424, _mut58425);
                final double p = AOR_multiply(bGS, u, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58426, _mut58427, _mut58428, _mut58429);
                if (ROR_less_equals(p, 1, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58430, _mut58431, _mut58432, _mut58433, _mut58434)) {
                    final double x = FastMath.pow(p, AOR_divide(1, shape, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58460, _mut58461, _mut58462, _mut58463));
                    final double u2 = random.nextDouble();
                    if (ROR_greater(u2, FastMath.exp(-x), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58464, _mut58465, _mut58466, _mut58467, _mut58468)) {
                        // Reject
                        continue;
                    } else {
                        return AOR_multiply(scale, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58469, _mut58470, _mut58471, _mut58472);
                    }
                } else {
                    final double x = AOR_multiply(-1, FastMath.log(AOR_divide((AOR_minus(bGS, p, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58435, _mut58436, _mut58437, _mut58438)), shape, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58439, _mut58440, _mut58441, _mut58442)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58443, _mut58444, _mut58445, _mut58446);
                    final double u2 = random.nextDouble();
                    if (ROR_greater(u2, FastMath.pow(x, AOR_minus(shape, 1, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58447, _mut58448, _mut58449, _mut58450)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58451, _mut58452, _mut58453, _mut58454, _mut58455)) {
                        // Reject
                        continue;
                    } else {
                        return AOR_multiply(scale, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58456, _mut58457, _mut58458, _mut58459);
                    }
                }
            }
        }
        final double d = AOR_minus(shape, 0.333333333333333333, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58473, _mut58474, _mut58475, _mut58476);
        final double c = AOR_divide(1, (AOR_multiply(3, FastMath.sqrt(d), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58477, _mut58478, _mut58479, _mut58480)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58481, _mut58482, _mut58483, _mut58484);
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GammaDistribution.sample_448");
            final double x = random.nextGaussian();
            final double v = AOR_multiply(AOR_multiply((AOR_plus(1, AOR_multiply(c, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58485, _mut58486, _mut58487, _mut58488), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58489, _mut58490, _mut58491, _mut58492)), (AOR_plus(1, AOR_multiply(c, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58493, _mut58494, _mut58495, _mut58496), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58497, _mut58498, _mut58499, _mut58500)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58501, _mut58502, _mut58503, _mut58504), (AOR_plus(1, AOR_multiply(c, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58505, _mut58506, _mut58507, _mut58508), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58509, _mut58510, _mut58511, _mut58512)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58513, _mut58514, _mut58515, _mut58516);
            if (ROR_less_equals(v, 0, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58517, _mut58518, _mut58519, _mut58520, _mut58521)) {
                continue;
            }
            final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58522, _mut58523, _mut58524, _mut58525);
            final double u = random.nextDouble();
            // Squeeze
            if (ROR_less(u, AOR_minus(1, AOR_multiply(AOR_multiply(0.0331, x2, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58526, _mut58527, _mut58528, _mut58529), x2, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58530, _mut58531, _mut58532, _mut58533), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58534, _mut58535, _mut58536, _mut58537), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58538, _mut58539, _mut58540, _mut58541, _mut58542)) {
                return AOR_multiply(AOR_multiply(scale, d, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58543, _mut58544, _mut58545, _mut58546), v, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58547, _mut58548, _mut58549, _mut58550);
            }
            if (ROR_less(FastMath.log(u), AOR_plus(AOR_multiply(0.5, x2, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58551, _mut58552, _mut58553, _mut58554), AOR_multiply(d, (AOR_plus(AOR_minus(1, v, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58555, _mut58556, _mut58557, _mut58558), FastMath.log(v), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58559, _mut58560, _mut58561, _mut58562)), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58563, _mut58564, _mut58565, _mut58566), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58567, _mut58568, _mut58569, _mut58570), "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58571, _mut58572, _mut58573, _mut58574, _mut58575)) {
                return AOR_multiply(AOR_multiply(scale, d, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58576, _mut58577, _mut58578, _mut58579), v, "org.apache.commons.math3.distribution.GammaDistribution.sample_448", _mut58580, _mut58581, _mut58582, _mut58583);
            }
        }
    }
}
