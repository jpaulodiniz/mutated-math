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

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the geometric distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Geometric_distribution">Geometric distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/GeometricDistribution.html">Geometric Distribution (MathWorld)</a>
 * @since 3.3
 */
public class GeometricDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut54429 = false, _mut54430 = false, _mut54431 = false, _mut54432 = false, _mut54433 = false, _mut54434 = false, _mut54435 = false, _mut54436 = false, _mut54437 = false, _mut54438 = false, _mut54439 = false, _mut54440 = false, _mut54441 = false, _mut54442 = false, _mut54443 = false, _mut54444 = false, _mut54445 = false, _mut54446 = false, _mut54447 = false, _mut54448 = false, _mut54449 = false, _mut54450 = false, _mut54451 = false, _mut54452 = false, _mut54453 = false, _mut54454 = false, _mut54455 = false, _mut54456 = false, _mut54457 = false, _mut54458 = false, _mut54459 = false, _mut54460 = false, _mut54461 = false, _mut54462 = false, _mut54463 = false, _mut54464 = false, _mut54465 = false, _mut54466 = false, _mut54467 = false, _mut54468 = false, _mut54469 = false, _mut54470 = false, _mut54471 = false, _mut54472 = false, _mut54473 = false, _mut54474 = false, _mut54475 = false, _mut54476 = false, _mut54477 = false, _mut54478 = false, _mut54479 = false, _mut54480 = false, _mut54481 = false, _mut54482 = false, _mut54483 = false, _mut54484 = false, _mut54485 = false, _mut54486 = false, _mut54487 = false, _mut54488 = false, _mut54489 = false, _mut54490 = false, _mut54491 = false, _mut54492 = false, _mut54493 = false, _mut54494 = false, _mut54495 = false, _mut54496 = false, _mut54497 = false, _mut54498 = false, _mut54499 = false, _mut54500 = false, _mut54501 = false, _mut54502 = false, _mut54503 = false, _mut54504 = false, _mut54505 = false, _mut54506 = false, _mut54507 = false, _mut54508 = false, _mut54509 = false, _mut54510 = false, _mut54511 = false, _mut54512 = false, _mut54513 = false, _mut54514 = false, _mut54515 = false, _mut54516 = false, _mut54517 = false, _mut54518 = false, _mut54519 = false, _mut54520 = false, _mut54521 = false, _mut54522 = false, _mut54523 = false, _mut54524 = false, _mut54525 = false, _mut54526 = false, _mut54527 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20130507L;

    /**
     * The probability of success.
     */
    private final double probabilityOfSuccess;

    /**
     * {@code log(p)} where p is the probability of success.
     */
    private final double logProbabilityOfSuccess;

    /**
     * {@code log(1 - p)} where p is the probability of success.
     */
    private final double log1mProbabilityOfSuccess;

    /**
     * Create a geometric distribution with the given probability of success.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param p probability of success.
     * @throws OutOfRangeException if {@code p <= 0} or {@code p > 1}.
     */
    public GeometricDistribution(double p) {
        this(new Well19937c(), p);
    }

    /**
     * Creates a geometric distribution.
     *
     * @param rng Random number generator.
     * @param p Probability of success.
     * @throws OutOfRangeException if {@code p <= 0} or {@code p > 1}.
     */
    public GeometricDistribution(RandomGenerator rng, double p) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.GeometricDistribution_67");
        if ((_mut54439 ? (ROR_less_equals(p, 0, "org.apache.commons.math3.distribution.GeometricDistribution.GeometricDistribution_67", _mut54429, _mut54430, _mut54431, _mut54432, _mut54433) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.GeometricDistribution.GeometricDistribution_67", _mut54434, _mut54435, _mut54436, _mut54437, _mut54438)) : (ROR_less_equals(p, 0, "org.apache.commons.math3.distribution.GeometricDistribution.GeometricDistribution_67", _mut54429, _mut54430, _mut54431, _mut54432, _mut54433) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.GeometricDistribution.GeometricDistribution_67", _mut54434, _mut54435, _mut54436, _mut54437, _mut54438)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, p, 0, 1);
        }
        probabilityOfSuccess = p;
        logProbabilityOfSuccess = FastMath.log(p);
        log1mProbabilityOfSuccess = FastMath.log1p(-p);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.probability_89");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.GeometricDistribution.probability_89", _mut54440, _mut54441, _mut54442, _mut54443, _mut54444)) {
            return 0.0;
        } else {
            return AOR_multiply(FastMath.exp(AOR_multiply(log1mProbabilityOfSuccess, x, "org.apache.commons.math3.distribution.GeometricDistribution.probability_89", _mut54445, _mut54446, _mut54447, _mut54448)), probabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.probability_89", _mut54449, _mut54450, _mut54451, _mut54452);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.logProbability_98");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.GeometricDistribution.logProbability_98", _mut54453, _mut54454, _mut54455, _mut54456, _mut54457)) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return AOR_plus(AOR_multiply(x, log1mProbabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.logProbability_98", _mut54458, _mut54459, _mut54460, _mut54461), logProbabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.logProbability_98", _mut54462, _mut54463, _mut54464, _mut54465);
        }
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.cumulativeProbability_108");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.GeometricDistribution.cumulativeProbability_108", _mut54466, _mut54467, _mut54468, _mut54469, _mut54470)) {
            return 0.0;
        } else {
            return -FastMath.expm1(AOR_multiply(log1mProbabilityOfSuccess, (AOR_plus(x, 1, "org.apache.commons.math3.distribution.GeometricDistribution.cumulativeProbability_108", _mut54471, _mut54472, _mut54473, _mut54474)), "org.apache.commons.math3.distribution.GeometricDistribution.cumulativeProbability_108", _mut54475, _mut54476, _mut54477, _mut54478));
        }
    }

    /**
     * {@inheritDoc}
     *
     * For probability parameter {@code p}, the mean is {@code (1 - p) / p}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.getNumericalMean_121");
        return AOR_divide((AOR_minus(1, probabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.getNumericalMean_121", _mut54479, _mut54480, _mut54481, _mut54482)), probabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.getNumericalMean_121", _mut54483, _mut54484, _mut54485, _mut54486);
    }

    /**
     * {@inheritDoc}
     *
     * For probability parameter {@code p}, the variance is
     * {@code (1 - p) / (p * p)}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.getNumericalVariance_131");
        return AOR_divide((AOR_minus(1, probabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.getNumericalVariance_131", _mut54487, _mut54488, _mut54489, _mut54490)), (AOR_multiply(probabilityOfSuccess, probabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.getNumericalVariance_131", _mut54491, _mut54492, _mut54493, _mut54494)), "org.apache.commons.math3.distribution.GeometricDistribution.getNumericalVariance_131", _mut54495, _mut54496, _mut54497, _mut54498);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0.
     *
     * @return lower bound of the support (always 0)
     */
    public int getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is infinite (which we approximate as
     * {@code Integer.MAX_VALUE}).
     *
     * @return upper bound of the support (always Integer.MAX_VALUE)
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172");
        if ((_mut54509 ? (ROR_less(p, 0, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54499, _mut54500, _mut54501, _mut54502, _mut54503) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54504, _mut54505, _mut54506, _mut54507, _mut54508)) : (ROR_less(p, 0, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54499, _mut54500, _mut54501, _mut54502, _mut54503) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54504, _mut54505, _mut54506, _mut54507, _mut54508)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54510, _mut54511, _mut54512, _mut54513, _mut54514)) {
            return Integer.MAX_VALUE;
        }
        if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54515, _mut54516, _mut54517, _mut54518, _mut54519)) {
            return 0;
        }
        return Math.max(0, (int) Math.ceil(AOR_minus(AOR_divide(FastMath.log1p(-p), log1mProbabilityOfSuccess, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54520, _mut54521, _mut54522, _mut54523), 1, "org.apache.commons.math3.distribution.GeometricDistribution.inverseCumulativeProbability_172", _mut54524, _mut54525, _mut54526, _mut54527)));
    }
}
