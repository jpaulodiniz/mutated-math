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
 * This class implements the Logistic distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Logistic_distribution">Logistic Distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/LogisticDistribution.html">Logistic Distribution (Mathworld)</a>
 *
 * @since 3.4
 */
public class LogisticDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut53398 = false, _mut53399 = false, _mut53400 = false, _mut53401 = false, _mut53402 = false, _mut53403 = false, _mut53404 = false, _mut53405 = false, _mut53406 = false, _mut53407 = false, _mut53408 = false, _mut53409 = false, _mut53410 = false, _mut53411 = false, _mut53412 = false, _mut53413 = false, _mut53414 = false, _mut53415 = false, _mut53416 = false, _mut53417 = false, _mut53418 = false, _mut53419 = false, _mut53420 = false, _mut53421 = false, _mut53422 = false, _mut53423 = false, _mut53424 = false, _mut53425 = false, _mut53426 = false, _mut53427 = false, _mut53428 = false, _mut53429 = false, _mut53430 = false, _mut53431 = false, _mut53432 = false, _mut53433 = false, _mut53434 = false, _mut53435 = false, _mut53436 = false, _mut53437 = false, _mut53438 = false, _mut53439 = false, _mut53440 = false, _mut53441 = false, _mut53442 = false, _mut53443 = false, _mut53444 = false, _mut53445 = false, _mut53446 = false, _mut53447 = false, _mut53448 = false, _mut53449 = false, _mut53450 = false, _mut53451 = false, _mut53452 = false, _mut53453 = false, _mut53454 = false, _mut53455 = false, _mut53456 = false, _mut53457 = false, _mut53458 = false, _mut53459 = false, _mut53460 = false, _mut53461 = false, _mut53462 = false, _mut53463 = false, _mut53464 = false, _mut53465 = false, _mut53466 = false, _mut53467 = false, _mut53468 = false, _mut53469 = false, _mut53470 = false, _mut53471 = false, _mut53472 = false, _mut53473 = false, _mut53474 = false, _mut53475 = false, _mut53476 = false, _mut53477 = false, _mut53478 = false, _mut53479 = false, _mut53480 = false, _mut53481 = false, _mut53482 = false, _mut53483 = false, _mut53484 = false, _mut53485 = false, _mut53486 = false, _mut53487 = false, _mut53488 = false, _mut53489 = false, _mut53490 = false, _mut53491 = false, _mut53492 = false, _mut53493 = false, _mut53494 = false, _mut53495 = false, _mut53496 = false, _mut53497 = false, _mut53498 = false, _mut53499 = false, _mut53500 = false, _mut53501 = false, _mut53502 = false, _mut53503 = false, _mut53504 = false, _mut53505 = false, _mut53506 = false, _mut53507 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20141003;

    /**
     * The location parameter.
     */
    private final double mu;

    /**
     * The scale parameter.
     */
    private final double s;

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
     * @param s scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public LogisticDistribution(double mu, double s) {
        this(new Well19937c(), mu, s);
    }

    /**
     * Build a new instance.
     *
     * @param rng Random number generator
     * @param mu location parameter
     * @param s scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public LogisticDistribution(RandomGenerator rng, double mu, double s) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogisticDistribution.LogisticDistribution_71");
        if (ROR_less_equals(s, 0.0, "org.apache.commons.math3.distribution.LogisticDistribution.LogisticDistribution_71", _mut53398, _mut53399, _mut53400, _mut53401, _mut53402)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, s);
        }
        this.mu = mu;
        this.s = s;
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
     * Access the scale parameter, {@code s}.
     *
     * @return the scale parameter.
     */
    public double getScale() {
        return s;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogisticDistribution.density_101");
        double z = AOR_divide((AOR_minus(x, mu, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53403, _mut53404, _mut53405, _mut53406)), s, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53407, _mut53408, _mut53409, _mut53410);
        double v = FastMath.exp(-z);
        return AOR_divide(AOR_multiply(AOR_divide(1, s, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53411, _mut53412, _mut53413, _mut53414), v, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53415, _mut53416, _mut53417, _mut53418), (AOR_multiply((AOR_plus(1.0, v, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53419, _mut53420, _mut53421, _mut53422)), (AOR_plus(1.0, v, "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53423, _mut53424, _mut53425, _mut53426)), "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53427, _mut53428, _mut53429, _mut53430)), "org.apache.commons.math3.distribution.LogisticDistribution.density_101", _mut53431, _mut53432, _mut53433, _mut53434);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108");
        double z = AOR_multiply(AOR_divide(1, s, "org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108", _mut53435, _mut53436, _mut53437, _mut53438), (AOR_minus(x, mu, "org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108", _mut53439, _mut53440, _mut53441, _mut53442)), "org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108", _mut53443, _mut53444, _mut53445, _mut53446);
        return AOR_divide(1.0, (AOR_plus(1.0, FastMath.exp(-z), "org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108", _mut53447, _mut53448, _mut53449, _mut53450)), "org.apache.commons.math3.distribution.LogisticDistribution.cumulativeProbability_108", _mut53451, _mut53452, _mut53453, _mut53454);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114");
        if ((_mut53465 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53455, _mut53456, _mut53457, _mut53458, _mut53459) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53460, _mut53461, _mut53462, _mut53463, _mut53464)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53455, _mut53456, _mut53457, _mut53458, _mut53459) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53460, _mut53461, _mut53462, _mut53463, _mut53464)))) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53466, _mut53467, _mut53468, _mut53469, _mut53470)) {
            return 0.0;
        } else if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53471, _mut53472, _mut53473, _mut53474, _mut53475)) {
            return Double.POSITIVE_INFINITY;
        }
        return AOR_plus(AOR_multiply(s, Math.log(AOR_divide(p, (AOR_minus(1.0, p, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53476, _mut53477, _mut53478, _mut53479)), "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53480, _mut53481, _mut53482, _mut53483)), "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53484, _mut53485, _mut53486, _mut53487), mu, "org.apache.commons.math3.distribution.LogisticDistribution.inverseCumulativeProbability_114", _mut53488, _mut53489, _mut53490, _mut53491);
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        return mu;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LogisticDistribution.getNumericalVariance_132");
        return AOR_multiply((AOR_divide(MathUtils.PI_SQUARED, 3.0, "org.apache.commons.math3.distribution.LogisticDistribution.getNumericalVariance_132", _mut53492, _mut53493, _mut53494, _mut53495)), (AOR_divide(1.0, (AOR_multiply(s, s, "org.apache.commons.math3.distribution.LogisticDistribution.getNumericalVariance_132", _mut53496, _mut53497, _mut53498, _mut53499)), "org.apache.commons.math3.distribution.LogisticDistribution.getNumericalVariance_132", _mut53500, _mut53501, _mut53502, _mut53503)), "org.apache.commons.math3.distribution.LogisticDistribution.getNumericalVariance_132", _mut53504, _mut53505, _mut53506, _mut53507);
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
