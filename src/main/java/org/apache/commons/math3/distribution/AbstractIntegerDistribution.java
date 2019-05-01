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

import java.io.Serializable;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for integer-valued discrete distributions.  Default
 * implementations are provided for some of the methods that do not vary
 * from distribution to distribution.
 */
public abstract class AbstractIntegerDistribution implements IntegerDistribution, Serializable {

    @Conditional
    public static boolean _mut53508 = false, _mut53509 = false, _mut53510 = false, _mut53511 = false, _mut53512 = false, _mut53513 = false, _mut53514 = false, _mut53515 = false, _mut53516 = false, _mut53517 = false, _mut53518 = false, _mut53519 = false, _mut53520 = false, _mut53521 = false, _mut53522 = false, _mut53523 = false, _mut53524 = false, _mut53525 = false, _mut53526 = false, _mut53527 = false, _mut53528 = false, _mut53529 = false, _mut53530 = false, _mut53531 = false, _mut53532 = false, _mut53533 = false, _mut53534 = false, _mut53535 = false, _mut53536 = false, _mut53537 = false, _mut53538 = false, _mut53539 = false, _mut53540 = false, _mut53541 = false, _mut53542 = false, _mut53543 = false, _mut53544 = false, _mut53545 = false, _mut53546 = false, _mut53547 = false, _mut53548 = false, _mut53549 = false, _mut53550 = false, _mut53551 = false, _mut53552 = false, _mut53553 = false, _mut53554 = false, _mut53555 = false, _mut53556 = false, _mut53557 = false, _mut53558 = false, _mut53559 = false, _mut53560 = false, _mut53561 = false, _mut53562 = false, _mut53563 = false, _mut53564 = false, _mut53565 = false, _mut53566 = false, _mut53567 = false, _mut53568 = false, _mut53569 = false, _mut53570 = false, _mut53571 = false, _mut53572 = false, _mut53573 = false, _mut53574 = false, _mut53575 = false, _mut53576 = false, _mut53577 = false, _mut53578 = false, _mut53579 = false, _mut53580 = false, _mut53581 = false, _mut53582 = false, _mut53583 = false, _mut53584 = false, _mut53585 = false, _mut53586 = false, _mut53587 = false, _mut53588 = false, _mut53589 = false, _mut53590 = false, _mut53591 = false, _mut53592 = false, _mut53593 = false, _mut53594 = false, _mut53595 = false, _mut53596 = false, _mut53597 = false, _mut53598 = false, _mut53599 = false, _mut53600 = false, _mut53601 = false, _mut53602 = false, _mut53603 = false, _mut53604 = false, _mut53605 = false, _mut53606 = false, _mut53607 = false, _mut53608 = false, _mut53609 = false, _mut53610 = false, _mut53611 = false, _mut53612 = false, _mut53613 = false, _mut53614 = false, _mut53615 = false, _mut53616 = false, _mut53617 = false, _mut53618 = false, _mut53619 = false, _mut53620 = false, _mut53621 = false, _mut53622 = false, _mut53623 = false, _mut53624 = false, _mut53625 = false, _mut53626 = false, _mut53627 = false, _mut53628 = false, _mut53629 = false, _mut53630 = false, _mut53631 = false, _mut53632 = false, _mut53633 = false, _mut53634 = false, _mut53635 = false, _mut53636 = false, _mut53637 = false, _mut53638 = false, _mut53639 = false, _mut53640 = false, _mut53641 = false, _mut53642 = false, _mut53643 = false, _mut53644 = false, _mut53645 = false, _mut53646 = false, _mut53647 = false, _mut53648 = false, _mut53649 = false, _mut53650 = false, _mut53651 = false, _mut53652 = false, _mut53653 = false, _mut53654 = false, _mut53655 = false, _mut53656 = false, _mut53657 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -1146319659338487221L;

    /**
     * RandomData instance used to generate samples from the distribution.
     * @deprecated As of 3.1, to be removed in 4.0. Please use the
     * {@link #random} instance variable instead.
     */
    @Deprecated
    protected final org.apache.commons.math3.random.RandomDataImpl randomData = new org.apache.commons.math3.random.RandomDataImpl();

    /**
     * RNG instance used to generate samples from the distribution.
     * @since 3.1
     */
    protected final RandomGenerator random;

    /**
     * @deprecated As of 3.1, to be removed in 4.0. Please use
     * {@link #AbstractIntegerDistribution(RandomGenerator)} instead.
     */
    @Deprecated
    protected AbstractIntegerDistribution() {
        // New users are forbidden to use this constructor.
        random = null;
    }

    /**
     * @param rng Random number generator.
     * @since 3.1
     */
    protected AbstractIntegerDistribution(RandomGenerator rng) {
        random = rng;
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation uses the identity
     * <p>{@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}</p>
     */
    public double cumulativeProbability(int x0, int x1) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.cumulativeProbability_80");
        if (ROR_less(x1, x0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.cumulativeProbability_80", _mut53508, _mut53509, _mut53510, _mut53511, _mut53512)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, x0, x1, true);
        }
        return AOR_minus(cumulativeProbability(x1), cumulativeProbability(x0), "org.apache.commons.math3.distribution.AbstractIntegerDistribution.cumulativeProbability_80", _mut53513, _mut53514, _mut53515, _mut53516);
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation returns
     * <ul>
     * <li>{@link #getSupportLowerBound()} for {@code p = 0},</li>
     * <li>{@link #getSupportUpperBound()} for {@code p = 1}, and</li>
     * <li>{@link #solveInverseCumulativeProbability(double, int, int)} for
     *     {@code 0 < p < 1}.</li>
     * </ul>
     */
    public int inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99");
        if ((_mut53527 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53517, _mut53518, _mut53519, _mut53520, _mut53521) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53522, _mut53523, _mut53524, _mut53525, _mut53526)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53517, _mut53518, _mut53519, _mut53520, _mut53521) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53522, _mut53523, _mut53524, _mut53525, _mut53526)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        int lower = getSupportLowerBound();
        if (ROR_equals(p, 0.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53528, _mut53529, _mut53530, _mut53531, _mut53532)) {
            return lower;
        }
        if (ROR_equals(lower, Integer.MIN_VALUE, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53533, _mut53534, _mut53535, _mut53536, _mut53537)) {
            if (ROR_greater_equals(checkedCumulativeProbability(lower), p, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53538, _mut53539, _mut53540, _mut53541, _mut53542)) {
                return lower;
            }
        } else {
            // this ensures cumulativeProbability(lower) < p, which
            lower -= 1;
        }
        int upper = getSupportUpperBound();
        if (ROR_equals(p, 1.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53543, _mut53544, _mut53545, _mut53546, _mut53547)) {
            return upper;
        }
        // cf. AbstractRealDistribution.inverseCumulativeProbability(double)
        final double mu = getNumericalMean();
        final double sigma = FastMath.sqrt(getNumericalVariance());
        final boolean chebyshevApplies = !((_mut53556 ? ((_mut53550 ? ((_mut53549 ? ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sigma)) : ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sigma))) && Double.isNaN(sigma)) : ((_mut53549 ? ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sigma)) : ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sigma))) || Double.isNaN(sigma))) && ROR_equals(sigma, 0.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53551, _mut53552, _mut53553, _mut53554, _mut53555)) : ((_mut53550 ? ((_mut53549 ? ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sigma)) : ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sigma))) && Double.isNaN(sigma)) : ((_mut53549 ? ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sigma)) : ((_mut53548 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sigma))) || Double.isNaN(sigma))) || ROR_equals(sigma, 0.0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53551, _mut53552, _mut53553, _mut53554, _mut53555))));
        if (chebyshevApplies) {
            double k = FastMath.sqrt(AOR_divide((AOR_minus(1.0, p, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53557, _mut53558, _mut53559, _mut53560)), p, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53561, _mut53562, _mut53563, _mut53564));
            double tmp = AOR_minus(mu, AOR_multiply(k, sigma, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53565, _mut53566, _mut53567, _mut53568), "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53569, _mut53570, _mut53571, _mut53572);
            if (ROR_greater(tmp, lower, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53573, _mut53574, _mut53575, _mut53576, _mut53577)) {
                lower = AOR_minus(((int) FastMath.ceil(tmp)), 1, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53578, _mut53579, _mut53580, _mut53581);
            }
            k = AOR_divide(1.0, k, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53582, _mut53583, _mut53584, _mut53585);
            tmp = AOR_plus(mu, AOR_multiply(k, sigma, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53586, _mut53587, _mut53588, _mut53589), "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53590, _mut53591, _mut53592, _mut53593);
            if (ROR_less(tmp, upper, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53594, _mut53595, _mut53596, _mut53597, _mut53598)) {
                upper = AOR_minus(((int) FastMath.ceil(tmp)), 1, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.inverseCumulativeProbability_99", _mut53599, _mut53600, _mut53601, _mut53602);
            }
        }
        return solveInverseCumulativeProbability(p, lower, upper);
    }

    /**
     * This is a utility function used by {@link
     * #inverseCumulativeProbability(double)}. It assumes {@code 0 < p < 1} and
     * that the inverse cumulative probability lies in the bracket {@code
     * (lower, upper]}. The implementation does simple bisection to find the
     * smallest {@code p}-quantile <code>inf{x in Z | P(X<=x) >= p}</code>.
     *
     * @param p the cumulative probability
     * @param lower a value satisfying {@code cumulativeProbability(lower) < p}
     * @param upper a value satisfying {@code p <= cumulativeProbability(upper)}
     * @return the smallest {@code p}-quantile of this distribution
     */
    protected int solveInverseCumulativeProbability(final double p, int lower, int upper) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156");
        while (ROR_less(AOR_plus(lower, 1, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53639, _mut53640, _mut53641, _mut53642), upper, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53643, _mut53644, _mut53645, _mut53646, _mut53647)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156");
            int xm = AOR_divide((AOR_plus(lower, upper, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53603, _mut53604, _mut53605, _mut53606)), 2, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53607, _mut53608, _mut53609, _mut53610);
            if ((_mut53621 ? (ROR_less(xm, lower, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53611, _mut53612, _mut53613, _mut53614, _mut53615) && ROR_greater(xm, upper, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53616, _mut53617, _mut53618, _mut53619, _mut53620)) : (ROR_less(xm, lower, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53611, _mut53612, _mut53613, _mut53614, _mut53615) || ROR_greater(xm, upper, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53616, _mut53617, _mut53618, _mut53619, _mut53620)))) {
                /*
                 * Overflow.
                 * There will never be an overflow in both calculation methods
                 * for xm at the same time
                 */
                xm = AOR_plus(lower, AOR_divide((AOR_minus(upper, lower, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53622, _mut53623, _mut53624, _mut53625)), 2, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53626, _mut53627, _mut53628, _mut53629), "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53630, _mut53631, _mut53632, _mut53633);
            }
            double pm = checkedCumulativeProbability(xm);
            if (ROR_greater_equals(pm, p, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.solveInverseCumulativeProbability_156", _mut53634, _mut53635, _mut53636, _mut53637, _mut53638)) {
                upper = xm;
            } else {
                lower = xm;
            }
        }
        return upper;
    }

    /**
     * {@inheritDoc}
     */
    public void reseedRandomGenerator(long seed) {
        random.setSeed(seed);
        randomData.reSeed(seed);
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation uses the
     * <a href="http://en.wikipedia.org/wiki/Inverse_transform_sampling">
     * inversion method</a>.
     */
    public int sample() {
        return inverseCumulativeProbability(random.nextDouble());
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation generates the sample by calling
     * {@link #sample()} in a loop.
     */
    public int[] sample(int sampleSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.sample_201");
        if (ROR_less_equals(sampleSize, 0, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.sample_201", _mut53648, _mut53649, _mut53650, _mut53651, _mut53652)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        int[] out = new int[sampleSize];
        for (int i = 0; ROR_less(i, sampleSize, "org.apache.commons.math3.distribution.AbstractIntegerDistribution.sample_201", _mut53653, _mut53654, _mut53655, _mut53656, _mut53657); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractIntegerDistribution.sample_201");
            out[i] = sample();
        }
        return out;
    }

    /**
     * Computes the cumulative probability function and checks for {@code NaN}
     * values returned. Throws {@code MathInternalError} if the value is
     * {@code NaN}. Rethrows any exception encountered evaluating the cumulative
     * probability function. Throws {@code MathInternalError} if the cumulative
     * probability function returns {@code NaN}.
     *
     * @param argument input value
     * @return the cumulative probability
     * @throws MathInternalError if the cumulative probability is {@code NaN}
     */
    private double checkedCumulativeProbability(int argument) throws MathInternalError {
        double result = Double.NaN;
        result = cumulativeProbability(argument);
        if (Double.isNaN(result)) {
            throw new MathInternalError(LocalizedFormats.DISCRETE_CUMULATIVE_PROBABILITY_RETURNED_NAN, argument);
        }
        return result;
    }

    /**
     * For a random variable {@code X} whose values are distributed according to
     * this distribution, this method returns {@code log(P(X = x))}, where
     * {@code log} is the natural logarithm. In other words, this method
     * represents the logarithm of the probability mass function (PMF) for the
     * distribution. Note that due to the floating point precision and
     * under/overflow issues, this method will for some distributions be more
     * precise and faster than computing the logarithm of
     * {@link #probability(int)}.
     * <p>
     * The default implementation simply computes the logarithm of {@code probability(x)}.</p>
     *
     * @param x the point at which the PMF is evaluated
     * @return the logarithm of the value of the probability mass function at {@code x}
     */
    public double logProbability(int x) {
        return FastMath.log(probability(x));
    }
}
