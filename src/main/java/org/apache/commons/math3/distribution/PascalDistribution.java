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
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * Implementation of the Pascal distribution. The Pascal distribution is a
 * special case of the Negative Binomial distribution where the number of
 * successes parameter is an integer.
 * </p>
 * <p>
 * There are various ways to express the probability mass and distribution
 * functions for the Pascal distribution. The present implementation represents
 * the distribution of the number of failures before {@code r} successes occur.
 * This is the convention adopted in e.g.
 * <a href="http://mathworld.wolfram.com/NegativeBinomialDistribution.html">MathWorld</a>,
 * but <em>not</em> in
 * <a href="http://en.wikipedia.org/wiki/Negative_binomial_distribution">Wikipedia</a>.
 * </p>
 * <p>
 * For a random variable {@code X} whose values are distributed according to this
 * distribution, the probability mass function is given by<br/>
 * {@code P(X = k) = C(k + r - 1, r - 1) * p^r * (1 - p)^k,}<br/>
 * where {@code r} is the number of successes, {@code p} is the probability of
 * success, and {@code X} is the total number of failures. {@code C(n, k)} is
 * the binomial coefficient ({@code n} choose {@code k}). The mean and variance
 * of {@code X} are<br/>
 * {@code E(X) = (1 - p) * r / p, var(X) = (1 - p) * r / p^2.}<br/>
 * Finally, the cumulative distribution function is given by<br/>
 * {@code P(X <= k) = I(p, r, k + 1)},
 * where I is the regularized incomplete Beta function.
 * </p>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Negative_binomial_distribution">
 * Negative binomial distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/NegativeBinomialDistribution.html">
 * Negative binomial distribution (MathWorld)</a>
 * @since 1.2 (changed to concrete class in 3.0)
 */
public class PascalDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut55244 = false, _mut55245 = false, _mut55246 = false, _mut55247 = false, _mut55248 = false, _mut55249 = false, _mut55250 = false, _mut55251 = false, _mut55252 = false, _mut55253 = false, _mut55254 = false, _mut55255 = false, _mut55256 = false, _mut55257 = false, _mut55258 = false, _mut55259 = false, _mut55260 = false, _mut55261 = false, _mut55262 = false, _mut55263 = false, _mut55264 = false, _mut55265 = false, _mut55266 = false, _mut55267 = false, _mut55268 = false, _mut55269 = false, _mut55270 = false, _mut55271 = false, _mut55272 = false, _mut55273 = false, _mut55274 = false, _mut55275 = false, _mut55276 = false, _mut55277 = false, _mut55278 = false, _mut55279 = false, _mut55280 = false, _mut55281 = false, _mut55282 = false, _mut55283 = false, _mut55284 = false, _mut55285 = false, _mut55286 = false, _mut55287 = false, _mut55288 = false, _mut55289 = false, _mut55290 = false, _mut55291 = false, _mut55292 = false, _mut55293 = false, _mut55294 = false, _mut55295 = false, _mut55296 = false, _mut55297 = false, _mut55298 = false, _mut55299 = false, _mut55300 = false, _mut55301 = false, _mut55302 = false, _mut55303 = false, _mut55304 = false, _mut55305 = false, _mut55306 = false, _mut55307 = false, _mut55308 = false, _mut55309 = false, _mut55310 = false, _mut55311 = false, _mut55312 = false, _mut55313 = false, _mut55314 = false, _mut55315 = false, _mut55316 = false, _mut55317 = false, _mut55318 = false, _mut55319 = false, _mut55320 = false, _mut55321 = false, _mut55322 = false, _mut55323 = false, _mut55324 = false, _mut55325 = false, _mut55326 = false, _mut55327 = false, _mut55328 = false, _mut55329 = false, _mut55330 = false, _mut55331 = false, _mut55332 = false, _mut55333 = false, _mut55334 = false, _mut55335 = false, _mut55336 = false, _mut55337 = false, _mut55338 = false, _mut55339 = false, _mut55340 = false, _mut55341 = false, _mut55342 = false, _mut55343 = false, _mut55344 = false, _mut55345 = false, _mut55346 = false, _mut55347 = false, _mut55348 = false, _mut55349 = false, _mut55350 = false, _mut55351 = false, _mut55352 = false, _mut55353 = false, _mut55354 = false, _mut55355 = false, _mut55356 = false, _mut55357 = false, _mut55358 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 6751309484392813623L;

    /**
     * The number of successes.
     */
    private final int numberOfSuccesses;

    /**
     * The probability of success.
     */
    private final double probabilityOfSuccess;

    /**
     * The value of {@code log(p)}, where {@code p} is the probability of success,
     * stored for faster computation.
     */
    private final double logProbabilityOfSuccess;

    /**
     * The value of {@code log(1-p)}, where {@code p} is the probability of success,
     * stored for faster computation.
     */
    private final double log1mProbabilityOfSuccess;

    /**
     * Create a Pascal distribution with the given number of successes and
     * probability of success.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param r Number of successes.
     * @param p Probability of success.
     * @throws NotStrictlyPositiveException if the number of successes is not positive
     * @throws OutOfRangeException if the probability of success is not in the
     * range {@code [0, 1]}.
     */
    public PascalDistribution(int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        this(new Well19937c(), r, p);
    }

    /**
     * Create a Pascal distribution with the given number of successes and
     * probability of success.
     *
     * @param rng Random number generator.
     * @param r Number of successes.
     * @param p Probability of success.
     * @throws NotStrictlyPositiveException if the number of successes is not positive
     * @throws OutOfRangeException if the probability of success is not in the
     * range {@code [0, 1]}.
     * @since 3.1
     */
    public PascalDistribution(RandomGenerator rng, int r, double p) throws NotStrictlyPositiveException, OutOfRangeException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111");
        if (ROR_less_equals(r, 0, "org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111", _mut55244, _mut55245, _mut55246, _mut55247, _mut55248)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, r);
        }
        if ((_mut55259 ? (ROR_less(p, 0, "org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111", _mut55249, _mut55250, _mut55251, _mut55252, _mut55253) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111", _mut55254, _mut55255, _mut55256, _mut55257, _mut55258)) : (ROR_less(p, 0, "org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111", _mut55249, _mut55250, _mut55251, _mut55252, _mut55253) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.PascalDistribution.PascalDistribution_111", _mut55254, _mut55255, _mut55256, _mut55257, _mut55258)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        numberOfSuccesses = r;
        probabilityOfSuccess = p;
        logProbabilityOfSuccess = FastMath.log(p);
        log1mProbabilityOfSuccess = FastMath.log1p(-p);
    }

    /**
     * Access the number of successes for this distribution.
     *
     * @return the number of successes.
     */
    public int getNumberOfSuccesses() {
        return numberOfSuccesses;
    }

    /**
     * Access the probability of success for this distribution.
     *
     * @return the probability of success.
     */
    public double getProbabilityOfSuccess() {
        return probabilityOfSuccess;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.probability_150");
        double ret;
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55260, _mut55261, _mut55262, _mut55263, _mut55264)) {
            ret = 0.0;
        } else {
            ret = AOR_multiply(AOR_multiply(CombinatoricsUtils.binomialCoefficientDouble(AOR_minus(AOR_plus(x, numberOfSuccesses, "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55265, _mut55266, _mut55267, _mut55268), 1, "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55269, _mut55270, _mut55271, _mut55272), AOR_minus(numberOfSuccesses, 1, "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55273, _mut55274, _mut55275, _mut55276)), FastMath.pow(probabilityOfSuccess, numberOfSuccesses), "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55277, _mut55278, _mut55279, _mut55280), FastMath.pow(AOR_minus(1.0, probabilityOfSuccess, "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55281, _mut55282, _mut55283, _mut55284), x), "org.apache.commons.math3.distribution.PascalDistribution.probability_150", _mut55285, _mut55286, _mut55287, _mut55288);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.logProbability_164");
        double ret;
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55289, _mut55290, _mut55291, _mut55292, _mut55293)) {
            ret = Double.NEGATIVE_INFINITY;
        } else {
            ret = AOR_plus(AOR_plus(CombinatoricsUtils.binomialCoefficientLog(AOR_minus(AOR_plus(x, numberOfSuccesses, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55294, _mut55295, _mut55296, _mut55297), 1, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55298, _mut55299, _mut55300, _mut55301), AOR_minus(numberOfSuccesses, 1, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55302, _mut55303, _mut55304, _mut55305)), AOR_multiply(logProbabilityOfSuccess, numberOfSuccesses, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55306, _mut55307, _mut55308, _mut55309), "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55310, _mut55311, _mut55312, _mut55313), AOR_multiply(log1mProbabilityOfSuccess, x, "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55314, _mut55315, _mut55316, _mut55317), "org.apache.commons.math3.distribution.PascalDistribution.logProbability_164", _mut55318, _mut55319, _mut55320, _mut55321);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.cumulativeProbability_179");
        double ret;
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.PascalDistribution.cumulativeProbability_179", _mut55322, _mut55323, _mut55324, _mut55325, _mut55326)) {
            ret = 0.0;
        } else {
            ret = Beta.regularizedBeta(probabilityOfSuccess, numberOfSuccesses, AOR_plus(x, 1.0, "org.apache.commons.math3.distribution.PascalDistribution.cumulativeProbability_179", _mut55327, _mut55328, _mut55329, _mut55330));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * For number of successes {@code r} and probability of success {@code p},
     * the mean is {@code r * (1 - p) / p}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.getNumericalMean_196");
        final double p = getProbabilityOfSuccess();
        final double r = getNumberOfSuccesses();
        return AOR_divide((AOR_multiply(r, (AOR_minus(1, p, "org.apache.commons.math3.distribution.PascalDistribution.getNumericalMean_196", _mut55331, _mut55332, _mut55333, _mut55334)), "org.apache.commons.math3.distribution.PascalDistribution.getNumericalMean_196", _mut55335, _mut55336, _mut55337, _mut55338)), p, "org.apache.commons.math3.distribution.PascalDistribution.getNumericalMean_196", _mut55339, _mut55340, _mut55341, _mut55342);
    }

    /**
     * {@inheritDoc}
     *
     * For number of successes {@code r} and probability of success {@code p},
     * the variance is {@code r * (1 - p) / p^2}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.PascalDistribution.getNumericalVariance_208");
        final double p = getProbabilityOfSuccess();
        final double r = getNumberOfSuccesses();
        return AOR_divide(AOR_multiply(r, (AOR_minus(1, p, "org.apache.commons.math3.distribution.PascalDistribution.getNumericalVariance_208", _mut55343, _mut55344, _mut55345, _mut55346)), "org.apache.commons.math3.distribution.PascalDistribution.getNumericalVariance_208", _mut55347, _mut55348, _mut55349, _mut55350), (AOR_multiply(p, p, "org.apache.commons.math3.distribution.PascalDistribution.getNumericalVariance_208", _mut55351, _mut55352, _mut55353, _mut55354)), "org.apache.commons.math3.distribution.PascalDistribution.getNumericalVariance_208", _mut55355, _mut55356, _mut55357, _mut55358);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the parameters.
     *
     * @return lower bound of the support (always 0)
     */
    public int getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity no matter the
     * parameters. Positive infinity is symbolized by {@code Integer.MAX_VALUE}.
     *
     * @return upper bound of the support (always {@code Integer.MAX_VALUE}
     * for positive infinity)
     */
    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
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
