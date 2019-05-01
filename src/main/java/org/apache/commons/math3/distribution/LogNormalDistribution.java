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
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the log-normal (gaussian) distribution.
 *
 * <p>
 * <strong>Parameters:</strong>
 * {@code X} is log-normally distributed if its natural logarithm {@code log(X)}
 * is normally distributed. The probability distribution function of {@code X}
 * is given by (for {@code x > 0})
 * </p>
 * <p>
 * {@code exp(-0.5 * ((ln(x) - m) / s)^2) / (s * sqrt(2 * pi) * x)}
 * </p>
 * <ul>
 * <li>{@code m} is the <em>scale</em> parameter: this is the mean of the
 * normally distributed natural logarithm of this distribution,</li>
 * <li>{@code s} is the <em>shape</em> parameter: this is the standard
 * deviation of the normally distributed natural logarithm of this
 * distribution.
 * </ul>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Log-normal_distribution">
 * Log-normal distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/LogNormalDistribution.html">
 * Log Normal distribution (MathWorld)</a>
 *
 * @since 3.0
 */
public class LogNormalDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut57221 = false, _mut57222 = false, _mut57223 = false, _mut57224 = false, _mut57225 = false, _mut57226 = false, _mut57227 = false, _mut57228 = false, _mut57229 = false, _mut57230 = false, _mut57231 = false, _mut57232 = false, _mut57233 = false, _mut57234 = false, _mut57235 = false, _mut57236 = false, _mut57237 = false, _mut57238 = false, _mut57239 = false, _mut57240 = false, _mut57241 = false, _mut57242 = false, _mut57243 = false, _mut57244 = false, _mut57245 = false, _mut57246 = false, _mut57247 = false, _mut57248 = false, _mut57249 = false, _mut57250 = false, _mut57251 = false, _mut57252 = false, _mut57253 = false, _mut57254 = false, _mut57255 = false, _mut57256 = false, _mut57257 = false, _mut57258 = false, _mut57259 = false, _mut57260 = false, _mut57261 = false, _mut57262 = false, _mut57263 = false, _mut57264 = false, _mut57265 = false, _mut57266 = false, _mut57267 = false, _mut57268 = false, _mut57269 = false, _mut57270 = false, _mut57271 = false, _mut57272 = false, _mut57273 = false, _mut57274 = false, _mut57275 = false, _mut57276 = false, _mut57277 = false, _mut57278 = false, _mut57279 = false, _mut57280 = false, _mut57281 = false, _mut57282 = false, _mut57283 = false, _mut57284 = false, _mut57285 = false, _mut57286 = false, _mut57287 = false, _mut57288 = false, _mut57289 = false, _mut57290 = false, _mut57291 = false, _mut57292 = false, _mut57293 = false, _mut57294 = false, _mut57295 = false, _mut57296 = false, _mut57297 = false, _mut57298 = false, _mut57299 = false, _mut57300 = false, _mut57301 = false, _mut57302 = false, _mut57303 = false, _mut57304 = false, _mut57305 = false, _mut57306 = false, _mut57307 = false, _mut57308 = false, _mut57309 = false, _mut57310 = false, _mut57311 = false, _mut57312 = false, _mut57313 = false, _mut57314 = false, _mut57315 = false, _mut57316 = false, _mut57317 = false, _mut57318 = false, _mut57319 = false, _mut57320 = false, _mut57321 = false, _mut57322 = false, _mut57323 = false, _mut57324 = false, _mut57325 = false, _mut57326 = false, _mut57327 = false, _mut57328 = false, _mut57329 = false, _mut57330 = false, _mut57331 = false, _mut57332 = false, _mut57333 = false, _mut57334 = false, _mut57335 = false, _mut57336 = false, _mut57337 = false, _mut57338 = false, _mut57339 = false, _mut57340 = false, _mut57341 = false, _mut57342 = false, _mut57343 = false, _mut57344 = false, _mut57345 = false, _mut57346 = false, _mut57347 = false, _mut57348 = false, _mut57349 = false, _mut57350 = false, _mut57351 = false, _mut57352 = false, _mut57353 = false, _mut57354 = false, _mut57355 = false, _mut57356 = false, _mut57357 = false, _mut57358 = false, _mut57359 = false, _mut57360 = false, _mut57361 = false, _mut57362 = false, _mut57363 = false, _mut57364 = false, _mut57365 = false, _mut57366 = false, _mut57367 = false, _mut57368 = false, _mut57369 = false, _mut57370 = false, _mut57371 = false, _mut57372 = false, _mut57373 = false, _mut57374 = false, _mut57375 = false, _mut57376 = false, _mut57377 = false, _mut57378 = false, _mut57379 = false, _mut57380 = false, _mut57381 = false, _mut57382 = false, _mut57383 = false, _mut57384 = false, _mut57385 = false, _mut57386 = false, _mut57387 = false, _mut57388 = false, _mut57389 = false, _mut57390 = false, _mut57391 = false, _mut57392 = false, _mut57393 = false, _mut57394 = false, _mut57395 = false, _mut57396 = false, _mut57397 = false, _mut57398 = false, _mut57399 = false, _mut57400 = false, _mut57401 = false, _mut57402 = false, _mut57403 = false, _mut57404 = false, _mut57405 = false, _mut57406 = false, _mut57407 = false, _mut57408 = false, _mut57409 = false, _mut57410 = false, _mut57411 = false, _mut57412 = false, _mut57413 = false, _mut57414 = false, _mut57415 = false, _mut57416 = false, _mut57417 = false, _mut57418 = false;

    /**
     * Default inverse cumulative probability accuracy.
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20120112;

    /**
     * &radic;(2 &pi;)
     */
    private static final double SQRT2PI = FastMath.sqrt(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.distribution.LogNormalDistribution.sample_59", _mut57221, _mut57222, _mut57223, _mut57224));

    /**
     * &radic;(2)
     */
    private static final double SQRT2 = FastMath.sqrt(2.0);

    /**
     * The scale parameter of this distribution.
     */
    private final double scale;

    /**
     * The shape parameter of this distribution.
     */
    private final double shape;

    /**
     * The value of {@code log(shape) + 0.5 * log(2*PI)} stored for faster computation.
     */
    private final double logShapePlusHalfLog2Pi;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Create a log-normal distribution, where the mean and standard deviation
     * of the {@link NormalDistribution normally distributed} natural
     * logarithm of the log-normal distribution are equal to zero and one
     * respectively. In other words, the scale of the returned distribution is
     * {@code 0}, while its shape is {@code 1}.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     */
    public LogNormalDistribution() {
        this(0, 1);
    }

    /**
     * Create a log-normal distribution using the specified scale and shape.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param scale the scale parameter of this distribution
     * @param shape the shape parameter of this distribution
     * @throws NotStrictlyPositiveException if {@code shape <= 0}.
     */
    public LogNormalDistribution(double scale, double shape) throws NotStrictlyPositiveException {
        this(scale, shape, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a log-normal distribution using the specified scale, shape and
     * inverse cumulative distribution accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param scale the scale parameter of this distribution
     * @param shape the shape parameter of this distribution
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code shape <= 0}.
     */
    public LogNormalDistribution(double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), scale, shape, inverseCumAccuracy);
    }

    /**
     * Creates a log-normal distribution.
     *
     * @param rng Random number generator.
     * @param scale Scale parameter of this distribution.
     * @param shape Shape parameter of this distribution.
     * @throws NotStrictlyPositiveException if {@code shape <= 0}.
     * @since 3.3
     */
    public LogNormalDistribution(RandomGenerator rng, double scale, double shape) throws NotStrictlyPositiveException {
        this(rng, scale, shape, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a log-normal distribution.
     *
     * @param rng Random number generator.
     * @param scale Scale parameter of this distribution.
     * @param shape Shape parameter of this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code shape <= 0}.
     * @since 3.1
     */
    public LogNormalDistribution(RandomGenerator rng, double scale, double shape, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.LogNormalDistribution_161");
        if (ROR_less_equals(shape, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.LogNormalDistribution_161", _mut57225, _mut57226, _mut57227, _mut57228, _mut57229)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, shape);
        }
        this.scale = scale;
        this.shape = shape;
        this.logShapePlusHalfLog2Pi = AOR_plus(FastMath.log(shape), AOR_multiply(0.5, FastMath.log(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.distribution.LogNormalDistribution.LogNormalDistribution_161", _mut57230, _mut57231, _mut57232, _mut57233)), "org.apache.commons.math3.distribution.LogNormalDistribution.LogNormalDistribution_161", _mut57234, _mut57235, _mut57236, _mut57237), "org.apache.commons.math3.distribution.LogNormalDistribution.LogNormalDistribution_161", _mut57238, _mut57239, _mut57240, _mut57241);
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Returns the scale parameter of this distribution.
     *
     * @return the scale parameter
     */
    public double getScale() {
        return scale;
    }

    /**
     * Returns the shape parameter of this distribution.
     *
     * @return the shape parameter
     */
    public double getShape() {
        return shape;
    }

    /**
     * {@inheritDoc}
     *
     * For scale {@code m}, and shape {@code s} of this distribution, the PDF
     * is given by
     * <ul>
     * <li>{@code 0} if {@code x <= 0},</li>
     * <li>{@code exp(-0.5 * ((ln(x) - m) / s)^2) / (s * sqrt(2 * pi) * x)}
     * otherwise.</li>
     * </ul>
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.density_207");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57242, _mut57243, _mut57244, _mut57245, _mut57246)) {
            return 0;
        }
        final double x0 = AOR_minus(FastMath.log(x), scale, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57247, _mut57248, _mut57249, _mut57250);
        final double x1 = AOR_divide(x0, shape, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57251, _mut57252, _mut57253, _mut57254);
        return AOR_divide(FastMath.exp(AOR_multiply(AOR_multiply(-0.5, x1, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57255, _mut57256, _mut57257, _mut57258), x1, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57259, _mut57260, _mut57261, _mut57262)), (AOR_multiply(AOR_multiply(shape, SQRT2PI, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57263, _mut57264, _mut57265, _mut57266), x, "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57267, _mut57268, _mut57269, _mut57270)), "org.apache.commons.math3.distribution.LogNormalDistribution.density_207", _mut57271, _mut57272, _mut57273, _mut57274);
    }

    /**
     * {@inheritDoc}
     *
     * See documentation of {@link #density(double)} for computation details.
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57275, _mut57276, _mut57277, _mut57278, _mut57279)) {
            return Double.NEGATIVE_INFINITY;
        }
        final double logX = FastMath.log(x);
        final double x0 = AOR_minus(logX, scale, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57280, _mut57281, _mut57282, _mut57283);
        final double x1 = AOR_divide(x0, shape, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57284, _mut57285, _mut57286, _mut57287);
        return AOR_minus(AOR_multiply(AOR_multiply(-0.5, x1, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57288, _mut57289, _mut57290, _mut57291), x1, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57292, _mut57293, _mut57294, _mut57295), (AOR_plus(logShapePlusHalfLog2Pi, logX, "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57296, _mut57297, _mut57298, _mut57299)), "org.apache.commons.math3.distribution.LogNormalDistribution.logDensity_220", _mut57300, _mut57301, _mut57302, _mut57303);
    }

    /**
     * {@inheritDoc}
     *
     * For scale {@code m}, and shape {@code s} of this distribution, the CDF
     * is given by
     * <ul>
     * <li>{@code 0} if {@code x <= 0},</li>
     * <li>{@code 0} if {@code ln(x) - m < 0} and {@code m - ln(x) > 40 * s}, as
     * in these cases the actual value is within {@code Double.MIN_VALUE} of 0,
     * <li>{@code 1} if {@code ln(x) - m >= 0} and {@code ln(x) - m > 40 * s},
     * as in these cases the actual value is within {@code Double.MIN_VALUE} of
     * 1,</li>
     * <li>{@code 0.5 + 0.5 * erf((ln(x) - m) / (s * sqrt(2))} otherwise.</li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57304, _mut57305, _mut57306, _mut57307, _mut57308)) {
            return 0;
        }
        final double dev = AOR_minus(FastMath.log(x), scale, "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57309, _mut57310, _mut57311, _mut57312);
        if (ROR_greater(FastMath.abs(dev), AOR_multiply(40, shape, "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57313, _mut57314, _mut57315, _mut57316), "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57317, _mut57318, _mut57319, _mut57320, _mut57321)) {
            return ROR_less(dev, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57322, _mut57323, _mut57324, _mut57325, _mut57326) ? 0.0d : 1.0d;
        }
        return AOR_plus(0.5, AOR_multiply(0.5, Erf.erf(AOR_divide(dev, (AOR_multiply(shape, SQRT2, "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57327, _mut57328, _mut57329, _mut57330)), "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57331, _mut57332, _mut57333, _mut57334)), "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57335, _mut57336, _mut57337, _mut57338), "org.apache.commons.math3.distribution.LogNormalDistribution.cumulativeProbability_246", _mut57339, _mut57340, _mut57341, _mut57342);
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated See {@link RealDistribution#cumulativeProbability(double,double)}
     */
    @Override
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(double x0, double x1) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.probability_269");
        if (ROR_greater(x0, x1, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57343, _mut57344, _mut57345, _mut57346, _mut57347)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, x0, x1, true);
        }
        if ((_mut57358 ? (ROR_less_equals(x0, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57348, _mut57349, _mut57350, _mut57351, _mut57352) && ROR_less_equals(x1, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57353, _mut57354, _mut57355, _mut57356, _mut57357)) : (ROR_less_equals(x0, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57348, _mut57349, _mut57350, _mut57351, _mut57352) || ROR_less_equals(x1, 0, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57353, _mut57354, _mut57355, _mut57356, _mut57357)))) {
            return super.probability(x0, x1);
        }
        final double denom = AOR_multiply(shape, SQRT2, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57359, _mut57360, _mut57361, _mut57362);
        final double v0 = AOR_divide((AOR_minus(FastMath.log(x0), scale, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57363, _mut57364, _mut57365, _mut57366)), denom, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57367, _mut57368, _mut57369, _mut57370);
        final double v1 = AOR_divide((AOR_minus(FastMath.log(x1), scale, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57371, _mut57372, _mut57373, _mut57374)), denom, "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57375, _mut57376, _mut57377, _mut57378);
        return AOR_multiply(0.5, Erf.erf(v0, v1), "org.apache.commons.math3.distribution.LogNormalDistribution.probability_269", _mut57379, _mut57380, _mut57381, _mut57382);
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
     * For scale {@code m} and shape {@code s}, the mean is
     * {@code exp(m + s^2 / 2)}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalMean_298");
        double s = shape;
        return FastMath.exp(AOR_plus(scale, (AOR_divide(AOR_multiply(s, s, "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalMean_298", _mut57383, _mut57384, _mut57385, _mut57386), 2, "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalMean_298", _mut57387, _mut57388, _mut57389, _mut57390)), "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalMean_298", _mut57391, _mut57392, _mut57393, _mut57394));
    }

    /**
     * {@inheritDoc}
     *
     * For scale {@code m} and shape {@code s}, the variance is
     * {@code (exp(s^2) - 1) * exp(2 * m + s^2)}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalVariance_309");
        final double s = shape;
        final double ss = AOR_multiply(s, s, "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalVariance_309", _mut57395, _mut57396, _mut57397, _mut57398);
        return AOR_multiply((FastMath.expm1(ss)), FastMath.exp(AOR_plus(AOR_multiply(2, scale, "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalVariance_309", _mut57399, _mut57400, _mut57401, _mut57402), ss, "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalVariance_309", _mut57403, _mut57404, _mut57405, _mut57406)), "org.apache.commons.math3.distribution.LogNormalDistribution.getNumericalVariance_309", _mut57407, _mut57408, _mut57409, _mut57410);
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
     * @return upper bound of the support (always
     * {@code Double.POSITIVE_INFINITY})
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
     * {@inheritDoc}
     */
    @Override
    public double sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogNormalDistribution.sample_361");
        final double n = random.nextGaussian();
        return FastMath.exp(AOR_plus(scale, AOR_multiply(shape, n, "org.apache.commons.math3.distribution.LogNormalDistribution.sample_361", _mut57411, _mut57412, _mut57413, _mut57414), "org.apache.commons.math3.distribution.LogNormalDistribution.sample_361", _mut57415, _mut57416, _mut57417, _mut57418));
    }
}
