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
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the Gumbel distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Gumbel_distribution">Gumbel Distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/GumbelDistribution.html">Gumbel Distribution (Mathworld)</a>
 *
 * @since 3.4
 */
public class GumbelDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut55445 = false, _mut55446 = false, _mut55447 = false, _mut55448 = false, _mut55449 = false, _mut55450 = false, _mut55451 = false, _mut55452 = false, _mut55453 = false, _mut55454 = false, _mut55455 = false, _mut55456 = false, _mut55457 = false, _mut55458 = false, _mut55459 = false, _mut55460 = false, _mut55461 = false, _mut55462 = false, _mut55463 = false, _mut55464 = false, _mut55465 = false, _mut55466 = false, _mut55467 = false, _mut55468 = false, _mut55469 = false, _mut55470 = false, _mut55471 = false, _mut55472 = false, _mut55473 = false, _mut55474 = false, _mut55475 = false, _mut55476 = false, _mut55477 = false, _mut55478 = false, _mut55479 = false, _mut55480 = false, _mut55481 = false, _mut55482 = false, _mut55483 = false, _mut55484 = false, _mut55485 = false, _mut55486 = false, _mut55487 = false, _mut55488 = false, _mut55489 = false, _mut55490 = false, _mut55491 = false, _mut55492 = false, _mut55493 = false, _mut55494 = false, _mut55495 = false, _mut55496 = false, _mut55497 = false, _mut55498 = false, _mut55499 = false, _mut55500 = false, _mut55501 = false, _mut55502 = false, _mut55503 = false, _mut55504 = false, _mut55505 = false, _mut55506 = false, _mut55507 = false, _mut55508 = false, _mut55509 = false, _mut55510 = false, _mut55511 = false, _mut55512 = false, _mut55513 = false, _mut55514 = false, _mut55515 = false, _mut55516 = false, _mut55517 = false, _mut55518 = false, _mut55519 = false, _mut55520 = false, _mut55521 = false, _mut55522 = false, _mut55523 = false, _mut55524 = false, _mut55525 = false, _mut55526 = false, _mut55527 = false, _mut55528 = false, _mut55529 = false, _mut55530 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20141003;

    /**
     * Approximation of Euler's constant
     * see http://mathworld.wolfram.com/Euler-MascheroniConstantApproximations.html
     */
    private static final double EULER = AOR_divide(FastMath.PI, (AOR_multiply(2, FastMath.E, "org.apache.commons.math3.distribution.GumbelDistribution.sample_336", _mut55445, _mut55446, _mut55447, _mut55448)), "org.apache.commons.math3.distribution.GumbelDistribution.sample_336", _mut55449, _mut55450, _mut55451, _mut55452);

    /**
     * The location parameter.
     */
    private final double mu;

    /**
     * The scale parameter.
     */
    private final double beta;

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
     * @param beta scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public GumbelDistribution(double mu, double beta) {
        this(new Well19937c(), mu, beta);
    }

    /**
     * Build a new instance.
     *
     * @param rng Random number generator
     * @param mu location parameter
     * @param beta scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public GumbelDistribution(RandomGenerator rng, double mu, double beta) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.GumbelDistribution_77");
        if (ROR_less_equals(beta, 0, "org.apache.commons.math3.distribution.GumbelDistribution.GumbelDistribution_77", _mut55453, _mut55454, _mut55455, _mut55456, _mut55457)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, beta);
        }
        this.beta = beta;
        this.mu = mu;
    }

    /**
     * Access the location parameter, {@code mu}.
     *
     * @return the location parameter.
     */
    public double getLocation() {
        return mu;
    }

    /**
     * Access the scale parameter, {@code beta}.
     *
     * @return the scale parameter.
     */
    public double getScale() {
        return beta;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.density_107");
        final double z = AOR_divide((AOR_minus(x, mu, "org.apache.commons.math3.distribution.GumbelDistribution.density_107", _mut55458, _mut55459, _mut55460, _mut55461)), beta, "org.apache.commons.math3.distribution.GumbelDistribution.density_107", _mut55462, _mut55463, _mut55464, _mut55465);
        final double t = FastMath.exp(-z);
        return AOR_divide(FastMath.exp(AOR_minus(-z, t, "org.apache.commons.math3.distribution.GumbelDistribution.density_107", _mut55466, _mut55467, _mut55468, _mut55469)), beta, "org.apache.commons.math3.distribution.GumbelDistribution.density_107", _mut55470, _mut55471, _mut55472, _mut55473);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.cumulativeProbability_114");
        final double z = AOR_divide((AOR_minus(x, mu, "org.apache.commons.math3.distribution.GumbelDistribution.cumulativeProbability_114", _mut55474, _mut55475, _mut55476, _mut55477)), beta, "org.apache.commons.math3.distribution.GumbelDistribution.cumulativeProbability_114", _mut55478, _mut55479, _mut55480, _mut55481);
        return FastMath.exp(-FastMath.exp(-z));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120");
        if ((_mut55492 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55482, _mut55483, _mut55484, _mut55485, _mut55486) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55487, _mut55488, _mut55489, _mut55490, _mut55491)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55482, _mut55483, _mut55484, _mut55485, _mut55486) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55487, _mut55488, _mut55489, _mut55490, _mut55491)))) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55493, _mut55494, _mut55495, _mut55496, _mut55497)) {
            return Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55498, _mut55499, _mut55500, _mut55501, _mut55502)) {
            return Double.POSITIVE_INFINITY;
        }
        return AOR_minus(mu, AOR_multiply(FastMath.log(-FastMath.log(p)), beta, "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55503, _mut55504, _mut55505, _mut55506), "org.apache.commons.math3.distribution.GumbelDistribution.inverseCumulativeProbability_120", _mut55507, _mut55508, _mut55509, _mut55510);
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.getNumericalMean_133");
        return AOR_plus(mu, AOR_multiply(EULER, beta, "org.apache.commons.math3.distribution.GumbelDistribution.getNumericalMean_133", _mut55511, _mut55512, _mut55513, _mut55514), "org.apache.commons.math3.distribution.GumbelDistribution.getNumericalMean_133", _mut55515, _mut55516, _mut55517, _mut55518);
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.GumbelDistribution.getNumericalVariance_138");
        return AOR_multiply(AOR_divide((MathUtils.PI_SQUARED), 6.0, "org.apache.commons.math3.distribution.GumbelDistribution.getNumericalVariance_138", _mut55519, _mut55520, _mut55521, _mut55522), (AOR_multiply(beta, beta, "org.apache.commons.math3.distribution.GumbelDistribution.getNumericalVariance_138", _mut55523, _mut55524, _mut55525, _mut55526)), "org.apache.commons.math3.distribution.GumbelDistribution.getNumericalVariance_138", _mut55527, _mut55528, _mut55529, _mut55530);
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
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
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportConnected() {
        return true;
    }
}
