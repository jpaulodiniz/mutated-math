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
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Weibull distribution. This implementation uses the
 * two parameter form of the distribution defined by
 * <a href="http://mathworld.wolfram.com/WeibullDistribution.html">
 * Weibull Distribution</a>, equations (1) and (2).
 *
 * @see <a href="http://en.wikipedia.org/wiki/Weibull_distribution">Weibull distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/WeibullDistribution.html">Weibull distribution (MathWorld)</a>
 * @since 1.1 (changed to concrete class in 3.0)
 */
public class WeibullDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut54279 = false, _mut54280 = false, _mut54281 = false, _mut54282 = false, _mut54283 = false, _mut54284 = false, _mut54285 = false, _mut54286 = false, _mut54287 = false, _mut54288 = false, _mut54289 = false, _mut54290 = false, _mut54291 = false, _mut54292 = false, _mut54293 = false, _mut54294 = false, _mut54295 = false, _mut54296 = false, _mut54297 = false, _mut54298 = false, _mut54299 = false, _mut54300 = false, _mut54301 = false, _mut54302 = false, _mut54303 = false, _mut54304 = false, _mut54305 = false, _mut54306 = false, _mut54307 = false, _mut54308 = false, _mut54309 = false, _mut54310 = false, _mut54311 = false, _mut54312 = false, _mut54313 = false, _mut54314 = false, _mut54315 = false, _mut54316 = false, _mut54317 = false, _mut54318 = false, _mut54319 = false, _mut54320 = false, _mut54321 = false, _mut54322 = false, _mut54323 = false, _mut54324 = false, _mut54325 = false, _mut54326 = false, _mut54327 = false, _mut54328 = false, _mut54329 = false, _mut54330 = false, _mut54331 = false, _mut54332 = false, _mut54333 = false, _mut54334 = false, _mut54335 = false, _mut54336 = false, _mut54337 = false, _mut54338 = false, _mut54339 = false, _mut54340 = false, _mut54341 = false, _mut54342 = false, _mut54343 = false, _mut54344 = false, _mut54345 = false, _mut54346 = false, _mut54347 = false, _mut54348 = false, _mut54349 = false, _mut54350 = false, _mut54351 = false, _mut54352 = false, _mut54353 = false, _mut54354 = false, _mut54355 = false, _mut54356 = false, _mut54357 = false, _mut54358 = false, _mut54359 = false, _mut54360 = false, _mut54361 = false, _mut54362 = false, _mut54363 = false, _mut54364 = false, _mut54365 = false, _mut54366 = false, _mut54367 = false, _mut54368 = false, _mut54369 = false, _mut54370 = false, _mut54371 = false, _mut54372 = false, _mut54373 = false, _mut54374 = false, _mut54375 = false, _mut54376 = false, _mut54377 = false, _mut54378 = false, _mut54379 = false, _mut54380 = false, _mut54381 = false, _mut54382 = false, _mut54383 = false, _mut54384 = false, _mut54385 = false, _mut54386 = false, _mut54387 = false, _mut54388 = false, _mut54389 = false, _mut54390 = false, _mut54391 = false, _mut54392 = false, _mut54393 = false, _mut54394 = false, _mut54395 = false, _mut54396 = false, _mut54397 = false, _mut54398 = false, _mut54399 = false, _mut54400 = false, _mut54401 = false, _mut54402 = false, _mut54403 = false, _mut54404 = false, _mut54405 = false, _mut54406 = false, _mut54407 = false, _mut54408 = false, _mut54409 = false, _mut54410 = false, _mut54411 = false, _mut54412 = false, _mut54413 = false, _mut54414 = false, _mut54415 = false, _mut54416 = false, _mut54417 = false, _mut54418 = false, _mut54419 = false, _mut54420 = false, _mut54421 = false, _mut54422 = false, _mut54423 = false, _mut54424 = false, _mut54425 = false, _mut54426 = false, _mut54427 = false, _mut54428 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 8589540077390120676L;

    /**
     * The shape parameter.
     */
    private final double shape;

    /**
     * The scale parameter.
     */
    private final double scale;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Cached numerical mean
     */
    private double numericalMean = Double.NaN;

    /**
     * Whether or not the numerical mean has been calculated
     */
    private boolean numericalMeanIsCalculated = false;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * Create a Weibull distribution with the given shape and scale and a
     * location equal to zero.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or
     * {@code beta <= 0}.
     */
    public WeibullDistribution(double alpha, double beta) throws NotStrictlyPositiveException {
        this(alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a Weibull distribution with the given shape, scale and inverse
     * cumulative probability accuracy and a location equal to zero.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or
     * {@code beta <= 0}.
     * @since 2.1
     */
    public WeibullDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this(new Well19937c(), alpha, beta, inverseCumAccuracy);
    }

    /**
     * Creates a Weibull distribution.
     *
     * @param rng Random number generator.
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or {@code beta <= 0}.
     * @since 3.3
     */
    public WeibullDistribution(RandomGenerator rng, double alpha, double beta) throws NotStrictlyPositiveException {
        this(rng, alpha, beta, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a Weibull distribution.
     *
     * @param rng Random number generator.
     * @param alpha Shape parameter.
     * @param beta Scale parameter.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code alpha <= 0} or {@code beta <= 0}.
     * @since 3.1
     */
    public WeibullDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.WeibullDistribution_133");
        if (ROR_less_equals(alpha, 0, "org.apache.commons.math3.distribution.WeibullDistribution.WeibullDistribution_133", _mut54279, _mut54280, _mut54281, _mut54282, _mut54283)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, alpha);
        }
        if (ROR_less_equals(beta, 0, "org.apache.commons.math3.distribution.WeibullDistribution.WeibullDistribution_133", _mut54284, _mut54285, _mut54286, _mut54287, _mut54288)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, beta);
        }
        scale = beta;
        shape = alpha;
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the shape parameter, {@code alpha}.
     *
     * @return the shape parameter, {@code alpha}.
     */
    public double getShape() {
        return shape;
    }

    /**
     * Access the scale parameter, {@code beta}.
     *
     * @return the scale parameter, {@code beta}.
     */
    public double getScale() {
        return scale;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.density_172");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54289, _mut54290, _mut54291, _mut54292, _mut54293)) {
            return 0;
        }
        final double xscale = AOR_divide(x, scale, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54294, _mut54295, _mut54296, _mut54297);
        final double xscalepow = FastMath.pow(xscale, AOR_minus(shape, 1, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54298, _mut54299, _mut54300, _mut54301));
        /*
         * FastMath.pow(x / scale, shape) =
         * FastMath.pow(xscale, shape) =
         * FastMath.pow(xscale, shape - 1) * xscale
         */
        final double xscalepowshape = AOR_multiply(xscalepow, xscale, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54302, _mut54303, _mut54304, _mut54305);
        return AOR_multiply(AOR_multiply((AOR_divide(shape, scale, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54306, _mut54307, _mut54308, _mut54309)), xscalepow, "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54310, _mut54311, _mut54312, _mut54313), FastMath.exp(-xscalepowshape), "org.apache.commons.math3.distribution.WeibullDistribution.density_172", _mut54314, _mut54315, _mut54316, _mut54317);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54318, _mut54319, _mut54320, _mut54321, _mut54322)) {
            return Double.NEGATIVE_INFINITY;
        }
        final double xscale = AOR_divide(x, scale, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54323, _mut54324, _mut54325, _mut54326);
        final double logxscalepow = AOR_multiply(FastMath.log(xscale), (AOR_minus(shape, 1, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54327, _mut54328, _mut54329, _mut54330)), "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54331, _mut54332, _mut54333, _mut54334);
        /*
         * FastMath.pow(x / scale, shape) =
         * FastMath.pow(xscale, shape) =
         * FastMath.pow(xscale, shape - 1) * xscale
         */
        final double xscalepowshape = AOR_multiply(FastMath.exp(logxscalepow), xscale, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54335, _mut54336, _mut54337, _mut54338);
        return AOR_minus(AOR_plus(FastMath.log(AOR_divide(shape, scale, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54339, _mut54340, _mut54341, _mut54342)), logxscalepow, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54343, _mut54344, _mut54345, _mut54346), xscalepowshape, "org.apache.commons.math3.distribution.WeibullDistribution.logDensity_191", _mut54347, _mut54348, _mut54349, _mut54350);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.cumulativeProbability_211");
        double ret;
        if (ROR_less_equals(x, 0.0, "org.apache.commons.math3.distribution.WeibullDistribution.cumulativeProbability_211", _mut54351, _mut54352, _mut54353, _mut54354, _mut54355)) {
            ret = 0.0;
        } else {
            ret = AOR_minus(1.0, FastMath.exp(-FastMath.pow(AOR_divide(x, scale, "org.apache.commons.math3.distribution.WeibullDistribution.cumulativeProbability_211", _mut54356, _mut54357, _mut54358, _mut54359), shape)), "org.apache.commons.math3.distribution.WeibullDistribution.cumulativeProbability_211", _mut54360, _mut54361, _mut54362, _mut54363);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * Returns {@code 0} when {@code p == 0} and
     * {@code Double.POSITIVE_INFINITY} when {@code p == 1}.
     */
    @Override
    public double inverseCumulativeProbability(double p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227");
        double ret;
        if ((_mut54374 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54364, _mut54365, _mut54366, _mut54367, _mut54368) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54369, _mut54370, _mut54371, _mut54372, _mut54373)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54364, _mut54365, _mut54366, _mut54367, _mut54368) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54369, _mut54370, _mut54371, _mut54372, _mut54373)))) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54375, _mut54376, _mut54377, _mut54378, _mut54379)) {
            ret = 0.0;
        } else if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54380, _mut54381, _mut54382, _mut54383, _mut54384)) {
            ret = Double.POSITIVE_INFINITY;
        } else {
            ret = AOR_multiply(scale, FastMath.pow(-FastMath.log1p(-p), AOR_divide(1.0, shape, "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54385, _mut54386, _mut54387, _mut54388)), "org.apache.commons.math3.distribution.WeibullDistribution.inverseCumulativeProbability_227", _mut54389, _mut54390, _mut54391, _mut54392);
        }
        return ret;
    }

    /**
     * Return the absolute accuracy setting of the solver used to estimate
     * inverse cumulative probabilities.
     *
     * @return the solver absolute accuracy.
     * @since 2.1
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * The mean is {@code scale * Gamma(1 + (1 / shape))}, where {@code Gamma()}
     * is the Gamma-function.
     */
    public double getNumericalMean() {
        if (!numericalMeanIsCalculated) {
            numericalMean = calculateNumericalMean();
            numericalMeanIsCalculated = true;
        }
        return numericalMean;
    }

    /**
     * used by {@link #getNumericalMean()}
     *
     * @return the mean of this distribution
     */
    protected double calculateNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalMean_273");
        final double sh = getShape();
        final double sc = getScale();
        return AOR_multiply(sc, FastMath.exp(Gamma.logGamma(AOR_plus(1, (AOR_divide(1, sh, "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalMean_273", _mut54393, _mut54394, _mut54395, _mut54396)), "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalMean_273", _mut54397, _mut54398, _mut54399, _mut54400))), "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalMean_273", _mut54401, _mut54402, _mut54403, _mut54404);
    }

    /**
     * {@inheritDoc}
     *
     * The variance is {@code scale^2 * Gamma(1 + (2 / shape)) - mean^2}
     * where {@code Gamma()} is the Gamma-function.
     */
    public double getNumericalVariance() {
        if (!numericalVarianceIsCalculated) {
            numericalVariance = calculateNumericalVariance();
            numericalVarianceIsCalculated = true;
        }
        return numericalVariance;
    }

    /**
     * used by {@link #getNumericalVariance()}
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299");
        final double sh = getShape();
        final double sc = getScale();
        final double mn = getNumericalMean();
        return AOR_minus(AOR_multiply((AOR_multiply(sc, sc, "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54405, _mut54406, _mut54407, _mut54408)), FastMath.exp(Gamma.logGamma(AOR_plus(1, (AOR_divide(2, sh, "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54409, _mut54410, _mut54411, _mut54412)), "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54413, _mut54414, _mut54415, _mut54416))), "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54417, _mut54418, _mut54419, _mut54420), (AOR_multiply(mn, mn, "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54421, _mut54422, _mut54423, _mut54424)), "org.apache.commons.math3.distribution.WeibullDistribution.calculateNumericalVariance_299", _mut54425, _mut54426, _mut54427, _mut54428);
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
}
