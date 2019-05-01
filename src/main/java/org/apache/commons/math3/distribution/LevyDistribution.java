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
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the <a href="http://en.wikipedia.org/wiki/L%C3%A9vy_distribution">
 * L&eacute;vy distribution</a>.
 *
 * @since 3.2
 */
public class LevyDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut57419 = false, _mut57420 = false, _mut57421 = false, _mut57422 = false, _mut57423 = false, _mut57424 = false, _mut57425 = false, _mut57426 = false, _mut57427 = false, _mut57428 = false, _mut57429 = false, _mut57430 = false, _mut57431 = false, _mut57432 = false, _mut57433 = false, _mut57434 = false, _mut57435 = false, _mut57436 = false, _mut57437 = false, _mut57438 = false, _mut57439 = false, _mut57440 = false, _mut57441 = false, _mut57442 = false, _mut57443 = false, _mut57444 = false, _mut57445 = false, _mut57446 = false, _mut57447 = false, _mut57448 = false, _mut57449 = false, _mut57450 = false, _mut57451 = false, _mut57452 = false, _mut57453 = false, _mut57454 = false, _mut57455 = false, _mut57456 = false, _mut57457 = false, _mut57458 = false, _mut57459 = false, _mut57460 = false, _mut57461 = false, _mut57462 = false, _mut57463 = false, _mut57464 = false, _mut57465 = false, _mut57466 = false, _mut57467 = false, _mut57468 = false, _mut57469 = false, _mut57470 = false, _mut57471 = false, _mut57472 = false, _mut57473 = false, _mut57474 = false, _mut57475 = false, _mut57476 = false, _mut57477 = false, _mut57478 = false, _mut57479 = false, _mut57480 = false, _mut57481 = false, _mut57482 = false, _mut57483 = false, _mut57484 = false, _mut57485 = false, _mut57486 = false, _mut57487 = false, _mut57488 = false, _mut57489 = false, _mut57490 = false, _mut57491 = false, _mut57492 = false, _mut57493 = false, _mut57494 = false, _mut57495 = false, _mut57496 = false, _mut57497 = false, _mut57498 = false, _mut57499 = false, _mut57500 = false, _mut57501 = false, _mut57502 = false, _mut57503 = false, _mut57504 = false, _mut57505 = false, _mut57506 = false, _mut57507 = false, _mut57508 = false, _mut57509 = false, _mut57510 = false, _mut57511 = false, _mut57512 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20130314L;

    /**
     * Location parameter.
     */
    private final double mu;

    // Setting this to 1 returns a cumProb of 1.0
    private final double c;

    /**
     * Half of c (for calculations).
     */
    private final double halfC;

    /**
     * Build a new instance.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mu location parameter
     * @param c scale parameter
     * @since 3.4
     */
    public LevyDistribution(final double mu, final double c) {
        this(new Well19937c(), mu, c);
    }

    /**
     * Creates a LevyDistribution.
     * @param rng random generator to be used for sampling
     * @param mu location
     * @param c scale parameter
     */
    public LevyDistribution(final RandomGenerator rng, final double mu, final double c) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LevyDistribution.LevyDistribution_69");
        this.mu = mu;
        this.c = c;
        this.halfC = AOR_multiply(0.5, c, "org.apache.commons.math3.distribution.LevyDistribution.LevyDistribution_69", _mut57419, _mut57420, _mut57421, _mut57422);
    }

    /**
     * {@inheritDoc}
     * <p>
     * From Wikipedia: The probability density function of the L&eacute;vy distribution
     * over the domain is
     * </p>
     * <pre>
     * f(x; &mu;, c) = &radic;(c / 2&pi;) * e<sup>-c / 2 (x - &mu;)</sup> / (x - &mu;)<sup>3/2</sup>
     * </pre>
     * <p>
     * For this distribution, {@code X}, this method returns {@code P(X < x)}.
     * If {@code x} is less than location parameter &mu;, {@code Double.NaN} is
     * returned, as in these cases the distribution is not defined.
     * </p>
     */
    public double density(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LevyDistribution.density_90");
        if (ROR_less(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57423, _mut57424, _mut57425, _mut57426, _mut57427)) {
            return Double.NaN;
        }
        final double delta = AOR_minus(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57428, _mut57429, _mut57430, _mut57431);
        final double f = AOR_divide(halfC, delta, "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57432, _mut57433, _mut57434, _mut57435);
        return AOR_divide(AOR_multiply(FastMath.sqrt(AOR_divide(f, FastMath.PI, "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57436, _mut57437, _mut57438, _mut57439)), FastMath.exp(-f), "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57440, _mut57441, _mut57442, _mut57443), delta, "org.apache.commons.math3.distribution.LevyDistribution.density_90", _mut57444, _mut57445, _mut57446, _mut57447);
    }

    /**
     * {@inheritDoc}
     *
     * See documentation of {@link #density(double)} for computation details.
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LevyDistribution.logDensity_104");
        if (ROR_less(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57448, _mut57449, _mut57450, _mut57451, _mut57452)) {
            return Double.NaN;
        }
        final double delta = AOR_minus(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57453, _mut57454, _mut57455, _mut57456);
        final double f = AOR_divide(halfC, delta, "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57457, _mut57458, _mut57459, _mut57460);
        return AOR_minus(AOR_minus(AOR_multiply(0.5, FastMath.log(AOR_divide(f, FastMath.PI, "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57461, _mut57462, _mut57463, _mut57464)), "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57465, _mut57466, _mut57467, _mut57468), f, "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57469, _mut57470, _mut57471, _mut57472), FastMath.log(delta), "org.apache.commons.math3.distribution.LevyDistribution.logDensity_104", _mut57473, _mut57474, _mut57475, _mut57476);
    }

    /**
     * {@inheritDoc}
     * <p>
     * From Wikipedia: the cumulative distribution function is
     * </p>
     * <pre>
     * f(x; u, c) = erfc (&radic; (c / 2 (x - u )))
     * </pre>
     */
    public double cumulativeProbability(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LevyDistribution.cumulativeProbability_123");
        if (ROR_less(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.cumulativeProbability_123", _mut57477, _mut57478, _mut57479, _mut57480, _mut57481)) {
            return Double.NaN;
        }
        return Erf.erfc(FastMath.sqrt(AOR_divide(halfC, (AOR_minus(x, mu, "org.apache.commons.math3.distribution.LevyDistribution.cumulativeProbability_123", _mut57482, _mut57483, _mut57484, _mut57485)), "org.apache.commons.math3.distribution.LevyDistribution.cumulativeProbability_123", _mut57486, _mut57487, _mut57488, _mut57489)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131");
        if ((_mut57500 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57490, _mut57491, _mut57492, _mut57493, _mut57494) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57495, _mut57496, _mut57497, _mut57498, _mut57499)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57490, _mut57491, _mut57492, _mut57493, _mut57494) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57495, _mut57496, _mut57497, _mut57498, _mut57499)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        final double t = Erf.erfcInv(p);
        return AOR_plus(mu, AOR_divide(halfC, (AOR_multiply(t, t, "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57501, _mut57502, _mut57503, _mut57504)), "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57505, _mut57506, _mut57507, _mut57508), "org.apache.commons.math3.distribution.LevyDistribution.inverseCumulativeProbability_131", _mut57509, _mut57510, _mut57511, _mut57512);
    }

    /**
     * Get the scale parameter of the distribution.
     * @return scale parameter of the distribution
     */
    public double getScale() {
        return c;
    }

    /**
     * Get the location parameter of the distribution.
     * @return location parameter of the distribution
     */
    public double getLocation() {
        return mu;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        return mu;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        // is not finite at lower bound, bound must be excluded
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        // upper bound is infinite, so it must be excluded
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportConnected() {
        return true;
    }
}
