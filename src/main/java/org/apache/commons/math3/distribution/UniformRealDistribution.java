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

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the uniform real distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Uniform_distribution_(continuous)"
 * >Uniform distribution (continuous), at Wikipedia</a>
 *
 * @since 3.0
 */
public class UniformRealDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut53281 = false, _mut53282 = false, _mut53283 = false, _mut53284 = false, _mut53285 = false, _mut53286 = false, _mut53287 = false, _mut53288 = false, _mut53289 = false, _mut53290 = false, _mut53291 = false, _mut53292 = false, _mut53293 = false, _mut53294 = false, _mut53295 = false, _mut53296 = false, _mut53297 = false, _mut53298 = false, _mut53299 = false, _mut53300 = false, _mut53301 = false, _mut53302 = false, _mut53303 = false, _mut53304 = false, _mut53305 = false, _mut53306 = false, _mut53307 = false, _mut53308 = false, _mut53309 = false, _mut53310 = false, _mut53311 = false, _mut53312 = false, _mut53313 = false, _mut53314 = false, _mut53315 = false, _mut53316 = false, _mut53317 = false, _mut53318 = false, _mut53319 = false, _mut53320 = false, _mut53321 = false, _mut53322 = false, _mut53323 = false, _mut53324 = false, _mut53325 = false, _mut53326 = false, _mut53327 = false, _mut53328 = false, _mut53329 = false, _mut53330 = false, _mut53331 = false, _mut53332 = false, _mut53333 = false, _mut53334 = false, _mut53335 = false, _mut53336 = false, _mut53337 = false, _mut53338 = false, _mut53339 = false, _mut53340 = false, _mut53341 = false, _mut53342 = false, _mut53343 = false, _mut53344 = false, _mut53345 = false, _mut53346 = false, _mut53347 = false, _mut53348 = false, _mut53349 = false, _mut53350 = false, _mut53351 = false, _mut53352 = false, _mut53353 = false, _mut53354 = false, _mut53355 = false, _mut53356 = false, _mut53357 = false, _mut53358 = false, _mut53359 = false, _mut53360 = false, _mut53361 = false, _mut53362 = false, _mut53363 = false, _mut53364 = false, _mut53365 = false, _mut53366 = false, _mut53367 = false, _mut53368 = false, _mut53369 = false, _mut53370 = false, _mut53371 = false, _mut53372 = false, _mut53373 = false, _mut53374 = false, _mut53375 = false, _mut53376 = false, _mut53377 = false, _mut53378 = false, _mut53379 = false, _mut53380 = false, _mut53381 = false, _mut53382 = false, _mut53383 = false, _mut53384 = false, _mut53385 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @deprecated as of 3.2 not used anymore, will be removed in 4.0
     */
    @Deprecated
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20120109L;

    /**
     * Lower bound of this distribution (inclusive).
     */
    private final double lower;

    /**
     * Upper bound of this distribution (exclusive).
     */
    private final double upper;

    /**
     * Create a standard uniform real distribution with lower bound (inclusive)
     * equal to zero and upper bound (exclusive) equal to one.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     */
    public UniformRealDistribution() {
        this(0, 1);
    }

    /**
     * Create a uniform real distribution using the given lower and upper
     * bounds.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (exclusive).
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     */
    public UniformRealDistribution(double lower, double upper) throws NumberIsTooLargeException {
        this(new Well19937c(), lower, upper);
    }

    /**
     * Create a uniform distribution.
     *
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (exclusive).
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     * @deprecated as of 3.2, inverse CDF is now calculated analytically, use
     *             {@link #UniformRealDistribution(double, double)} instead.
     */
    @Deprecated
    public UniformRealDistribution(double lower, double upper, double inverseCumAccuracy) throws NumberIsTooLargeException {
        this(new Well19937c(), lower, upper);
    }

    /**
     * Creates a uniform distribution.
     *
     * @param rng Random number generator.
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (exclusive).
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     * @since 3.1
     * @deprecated as of 3.2, inverse CDF is now calculated analytically, use
     *             {@link #UniformRealDistribution(RandomGenerator, double, double)}
     *             instead.
     */
    @Deprecated
    public UniformRealDistribution(RandomGenerator rng, double lower, double upper, double inverseCumAccuracy) {
        this(rng, lower, upper);
    }

    /**
     * Creates a uniform distribution.
     *
     * @param rng Random number generator.
     * @param lower Lower bound of this distribution (inclusive).
     * @param upper Upper bound of this distribution (exclusive).
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     * @since 3.1
     */
    public UniformRealDistribution(RandomGenerator rng, double lower, double upper) throws NumberIsTooLargeException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.UniformRealDistribution_128");
        if (ROR_greater_equals(lower, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.UniformRealDistribution_128", _mut53281, _mut53282, _mut53283, _mut53284, _mut53285)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, lower, upper, false);
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.density_144");
        if ((_mut53296 ? (ROR_less(x, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53286, _mut53287, _mut53288, _mut53289, _mut53290) && ROR_greater(x, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53291, _mut53292, _mut53293, _mut53294, _mut53295)) : (ROR_less(x, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53286, _mut53287, _mut53288, _mut53289, _mut53290) || ROR_greater(x, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53291, _mut53292, _mut53293, _mut53294, _mut53295)))) {
            return 0.0;
        }
        return AOR_divide(1, (AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53297, _mut53298, _mut53299, _mut53300)), "org.apache.commons.math3.distribution.UniformRealDistribution.density_144", _mut53301, _mut53302, _mut53303, _mut53304);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152");
        if (ROR_less_equals(x, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152", _mut53305, _mut53306, _mut53307, _mut53308, _mut53309)) {
            return 0;
        }
        if (ROR_greater_equals(x, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152", _mut53310, _mut53311, _mut53312, _mut53313, _mut53314)) {
            return 1;
        }
        return AOR_divide((AOR_minus(x, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152", _mut53315, _mut53316, _mut53317, _mut53318)), (AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152", _mut53319, _mut53320, _mut53321, _mut53322)), "org.apache.commons.math3.distribution.UniformRealDistribution.cumulativeProbability_152", _mut53323, _mut53324, _mut53325, _mut53326);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163");
        if ((_mut53337 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53327, _mut53328, _mut53329, _mut53330, _mut53331) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53332, _mut53333, _mut53334, _mut53335, _mut53336)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53327, _mut53328, _mut53329, _mut53330, _mut53331) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53332, _mut53333, _mut53334, _mut53335, _mut53336)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        return AOR_plus(AOR_multiply(p, (AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53338, _mut53339, _mut53340, _mut53341)), "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53342, _mut53343, _mut53344, _mut53345), lower, "org.apache.commons.math3.distribution.UniformRealDistribution.inverseCumulativeProbability_163", _mut53346, _mut53347, _mut53348, _mut53349);
    }

    /**
     * {@inheritDoc}
     *
     * For lower bound {@code lower} and upper bound {@code upper}, the mean is
     * {@code 0.5 * (lower + upper)}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalMean_178");
        return AOR_multiply(0.5, (AOR_plus(lower, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalMean_178", _mut53350, _mut53351, _mut53352, _mut53353)), "org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalMean_178", _mut53354, _mut53355, _mut53356, _mut53357);
    }

    /**
     * {@inheritDoc}
     *
     * For lower bound {@code lower} and upper bound {@code upper}, the
     * variance is {@code (upper - lower)^2 / 12}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalVariance_188");
        double ul = AOR_minus(upper, lower, "org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalVariance_188", _mut53358, _mut53359, _mut53360, _mut53361);
        return AOR_divide(AOR_multiply(ul, ul, "org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalVariance_188", _mut53362, _mut53363, _mut53364, _mut53365), 12, "org.apache.commons.math3.distribution.UniformRealDistribution.getNumericalVariance_188", _mut53366, _mut53367, _mut53368, _mut53369);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is equal to the lower bound parameter
     * of the distribution.
     *
     * @return lower bound of the support
     */
    public double getSupportLowerBound() {
        return lower;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is equal to the upper bound parameter
     * of the distribution.
     *
     * @return upper bound of the support
     */
    public double getSupportUpperBound() {
        return upper;
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
        return true;
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.UniformRealDistribution.sample_239");
        final double u = random.nextDouble();
        return AOR_plus(AOR_multiply(u, upper, "org.apache.commons.math3.distribution.UniformRealDistribution.sample_239", _mut53370, _mut53371, _mut53372, _mut53373), AOR_multiply((AOR_minus(1, u, "org.apache.commons.math3.distribution.UniformRealDistribution.sample_239", _mut53374, _mut53375, _mut53376, _mut53377)), lower, "org.apache.commons.math3.distribution.UniformRealDistribution.sample_239", _mut53378, _mut53379, _mut53380, _mut53381), "org.apache.commons.math3.distribution.UniformRealDistribution.sample_239", _mut53382, _mut53383, _mut53384, _mut53385);
    }
}
