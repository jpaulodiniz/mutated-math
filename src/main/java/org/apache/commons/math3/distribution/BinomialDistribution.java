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

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the binomial distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Binomial_distribution">Binomial distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/BinomialDistribution.html">Binomial Distribution (MathWorld)</a>
 */
public class BinomialDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut58584 = false, _mut58585 = false, _mut58586 = false, _mut58587 = false, _mut58588 = false, _mut58589 = false, _mut58590 = false, _mut58591 = false, _mut58592 = false, _mut58593 = false, _mut58594 = false, _mut58595 = false, _mut58596 = false, _mut58597 = false, _mut58598 = false, _mut58599 = false, _mut58600 = false, _mut58601 = false, _mut58602 = false, _mut58603 = false, _mut58604 = false, _mut58605 = false, _mut58606 = false, _mut58607 = false, _mut58608 = false, _mut58609 = false, _mut58610 = false, _mut58611 = false, _mut58612 = false, _mut58613 = false, _mut58614 = false, _mut58615 = false, _mut58616 = false, _mut58617 = false, _mut58618 = false, _mut58619 = false, _mut58620 = false, _mut58621 = false, _mut58622 = false, _mut58623 = false, _mut58624 = false, _mut58625 = false, _mut58626 = false, _mut58627 = false, _mut58628 = false, _mut58629 = false, _mut58630 = false, _mut58631 = false, _mut58632 = false, _mut58633 = false, _mut58634 = false, _mut58635 = false, _mut58636 = false, _mut58637 = false, _mut58638 = false, _mut58639 = false, _mut58640 = false, _mut58641 = false, _mut58642 = false, _mut58643 = false, _mut58644 = false, _mut58645 = false, _mut58646 = false, _mut58647 = false, _mut58648 = false, _mut58649 = false, _mut58650 = false, _mut58651 = false, _mut58652 = false, _mut58653 = false, _mut58654 = false, _mut58655 = false, _mut58656 = false, _mut58657 = false, _mut58658 = false, _mut58659 = false, _mut58660 = false, _mut58661 = false, _mut58662 = false, _mut58663 = false, _mut58664 = false, _mut58665 = false, _mut58666 = false, _mut58667 = false, _mut58668 = false, _mut58669 = false, _mut58670 = false, _mut58671 = false, _mut58672 = false, _mut58673 = false, _mut58674 = false, _mut58675 = false, _mut58676 = false, _mut58677 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 6751309484392813623L;

    /**
     * The number of trials.
     */
    private final int numberOfTrials;

    /**
     * The probability of success.
     */
    private final double probabilityOfSuccess;

    /**
     * Create a binomial distribution with the given number of trials and
     * probability of success.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param trials Number of trials.
     * @param p Probability of success.
     * @throws NotPositiveException if {@code trials < 0}.
     * @throws OutOfRangeException if {@code p < 0} or {@code p > 1}.
     */
    public BinomialDistribution(int trials, double p) {
        this(new Well19937c(), trials, p);
    }

    /**
     * Creates a binomial distribution.
     *
     * @param rng Random number generator.
     * @param trials Number of trials.
     * @param p Probability of success.
     * @throws NotPositiveException if {@code trials < 0}.
     * @throws OutOfRangeException if {@code p < 0} or {@code p > 1}.
     * @since 3.1
     */
    public BinomialDistribution(RandomGenerator rng, int trials, double p) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71");
        if (ROR_less(trials, 0, "org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71", _mut58584, _mut58585, _mut58586, _mut58587, _mut58588)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_TRIALS, trials);
        }
        if ((_mut58599 ? (ROR_less(p, 0, "org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71", _mut58589, _mut58590, _mut58591, _mut58592, _mut58593) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71", _mut58594, _mut58595, _mut58596, _mut58597, _mut58598)) : (ROR_less(p, 0, "org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71", _mut58589, _mut58590, _mut58591, _mut58592, _mut58593) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.BinomialDistribution.BinomialDistribution_71", _mut58594, _mut58595, _mut58596, _mut58597, _mut58598)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        probabilityOfSuccess = p;
        numberOfTrials = trials;
    }

    /**
     * Access the number of trials for this distribution.
     *
     * @return the number of trials.
     */
    public int getNumberOfTrials() {
        return numberOfTrials;
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.probability_107");
        final double logProbability = logProbability(x);
        return ROR_equals(logProbability, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.BinomialDistribution.probability_107", _mut58600, _mut58601, _mut58602, _mut58603, _mut58604) ? 0 : FastMath.exp(logProbability);
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113");
        if (ROR_equals(numberOfTrials, 0, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58605, _mut58606, _mut58607, _mut58608, _mut58609)) {
            return (ROR_equals(x, 0, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58610, _mut58611, _mut58612, _mut58613, _mut58614)) ? 0. : Double.NEGATIVE_INFINITY;
        }
        double ret;
        if ((_mut58625 ? (ROR_less(x, 0, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58615, _mut58616, _mut58617, _mut58618, _mut58619) && ROR_greater(x, numberOfTrials, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58620, _mut58621, _mut58622, _mut58623, _mut58624)) : (ROR_less(x, 0, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58615, _mut58616, _mut58617, _mut58618, _mut58619) || ROR_greater(x, numberOfTrials, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58620, _mut58621, _mut58622, _mut58623, _mut58624)))) {
            ret = Double.NEGATIVE_INFINITY;
        } else {
            ret = SaddlePointExpansion.logBinomialProbability(x, numberOfTrials, probabilityOfSuccess, AOR_minus(1.0, probabilityOfSuccess, "org.apache.commons.math3.distribution.BinomialDistribution.logProbability_113", _mut58626, _mut58627, _mut58628, _mut58629));
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130");
        double ret;
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130", _mut58630, _mut58631, _mut58632, _mut58633, _mut58634)) {
            ret = 0.0;
        } else if (ROR_greater_equals(x, numberOfTrials, "org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130", _mut58635, _mut58636, _mut58637, _mut58638, _mut58639)) {
            ret = 1.0;
        } else {
            ret = AOR_minus(1.0, Beta.regularizedBeta(probabilityOfSuccess, AOR_plus(x, 1.0, "org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130", _mut58640, _mut58641, _mut58642, _mut58643), AOR_minus(numberOfTrials, x, "org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130", _mut58644, _mut58645, _mut58646, _mut58647)), "org.apache.commons.math3.distribution.BinomialDistribution.cumulativeProbability_130", _mut58648, _mut58649, _mut58650, _mut58651);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * For {@code n} trials and probability parameter {@code p}, the mean is
     * {@code n * p}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.getNumericalMean_149");
        return AOR_multiply(numberOfTrials, probabilityOfSuccess, "org.apache.commons.math3.distribution.BinomialDistribution.getNumericalMean_149", _mut58652, _mut58653, _mut58654, _mut58655);
    }

    /**
     * {@inheritDoc}
     *
     * For {@code n} trials and probability parameter {@code p}, the variance is
     * {@code n * p * (1 - p)}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.getNumericalVariance_159");
        final double p = probabilityOfSuccess;
        return AOR_multiply(AOR_multiply(numberOfTrials, p, "org.apache.commons.math3.distribution.BinomialDistribution.getNumericalVariance_159", _mut58656, _mut58657, _mut58658, _mut58659), (AOR_minus(1, p, "org.apache.commons.math3.distribution.BinomialDistribution.getNumericalVariance_159", _mut58660, _mut58661, _mut58662, _mut58663)), "org.apache.commons.math3.distribution.BinomialDistribution.getNumericalVariance_159", _mut58664, _mut58665, _mut58666, _mut58667);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 except for the probability
     * parameter {@code p = 1}.
     *
     * @return lower bound of the support (0 or the number of trials)
     */
    public int getSupportLowerBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.getSupportLowerBound_172");
        return ROR_less(probabilityOfSuccess, 1.0, "org.apache.commons.math3.distribution.BinomialDistribution.getSupportLowerBound_172", _mut58668, _mut58669, _mut58670, _mut58671, _mut58672) ? 0 : numberOfTrials;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is the number of trials except for the
     * probability parameter {@code p = 0}.
     *
     * @return upper bound of the support (number of trials or 0)
     */
    public int getSupportUpperBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.BinomialDistribution.getSupportUpperBound_184");
        return ROR_greater(probabilityOfSuccess, 0.0, "org.apache.commons.math3.distribution.BinomialDistribution.getSupportUpperBound_184", _mut58673, _mut58674, _mut58675, _mut58676, _mut58677) ? numberOfTrials : 0;
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
