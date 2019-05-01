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
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the exponential distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Exponential_distribution">Exponential distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/ExponentialDistribution.html">Exponential distribution (MathWorld)</a>
 */
public class ExponentialDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut53663 = false, _mut53664 = false, _mut53665 = false, _mut53666 = false, _mut53667 = false, _mut53668 = false, _mut53669 = false, _mut53670 = false, _mut53671 = false, _mut53672 = false, _mut53673 = false, _mut53674 = false, _mut53675 = false, _mut53676 = false, _mut53677 = false, _mut53678 = false, _mut53679 = false, _mut53680 = false, _mut53681 = false, _mut53682 = false, _mut53683 = false, _mut53684 = false, _mut53685 = false, _mut53686 = false, _mut53687 = false, _mut53688 = false, _mut53689 = false, _mut53690 = false, _mut53691 = false, _mut53692 = false, _mut53693 = false, _mut53694 = false, _mut53695 = false, _mut53696 = false, _mut53697 = false, _mut53698 = false, _mut53699 = false, _mut53700 = false, _mut53701 = false, _mut53702 = false, _mut53703 = false, _mut53704 = false, _mut53705 = false, _mut53706 = false, _mut53707 = false, _mut53708 = false, _mut53709 = false, _mut53710 = false, _mut53711 = false, _mut53712 = false, _mut53713 = false, _mut53714 = false, _mut53715 = false, _mut53716 = false, _mut53717 = false, _mut53718 = false, _mut53719 = false, _mut53720 = false, _mut53721 = false, _mut53722 = false, _mut53723 = false, _mut53724 = false, _mut53725 = false, _mut53726 = false, _mut53727 = false, _mut53728 = false, _mut53729 = false, _mut53730 = false, _mut53731 = false, _mut53732 = false, _mut53733 = false, _mut53734 = false, _mut53735 = false, _mut53736 = false, _mut53737 = false, _mut53738 = false, _mut53739 = false, _mut53740 = false, _mut53741 = false, _mut53742 = false, _mut53743 = false, _mut53744 = false, _mut53745 = false, _mut53746 = false, _mut53747 = false, _mut53748 = false, _mut53749 = false, _mut53750 = false, _mut53751 = false, _mut53752 = false, _mut53753 = false, _mut53754 = false, _mut53755 = false, _mut53756 = false, _mut53757 = false, _mut53758 = false, _mut53759 = false, _mut53760 = false, _mut53761 = false, _mut53762 = false, _mut53763 = false, _mut53764 = false, _mut53765 = false, _mut53766 = false, _mut53767 = false, _mut53768 = false, _mut53769 = false, _mut53770 = false, _mut53771 = false, _mut53772 = false, _mut53773 = false, _mut53774 = false, _mut53775 = false, _mut53776 = false, _mut53777 = false, _mut53778 = false, _mut53779 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 2401296428283614780L;

    /**
     * Used when generating Exponential samples.
     * Table containing the constants
     * q_i = sum_{j=1}^i (ln 2)^j/j! = ln 2 + (ln 2)^2/2 + ... + (ln 2)^i/i!
     * until the largest representable fraction below 1 is exceeded.
     *
     * Note that
     * 1 = 2 - 1 = exp(ln 2) - 1 = sum_{n=1}^infty (ln 2)^n / n!
     * thus q_i -> 1 as i -> +inf,
     * so the higher i, the closer to one we get (the series is not alternating).
     *
     * By trying, n = 16 in Java is enough to reach 1.0.
     */
    private static final double[] EXPONENTIAL_SA_QI;

    /**
     * The mean of this distribution.
     */
    private final double mean;

    /**
     * The logarithm of the mean, stored to reduce computing time. *
     */
    private final double logMean;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Initialize tables.
     */
    static {
        /**
         * Filling EXPONENTIAL_SA_QI table.
         * Note that we don't want qi = 0 in the table.
         */
        final double LN2 = FastMath.log(2);
        double qi = 0;
        int i = 1;
        /**
         * ArithmeticUtils provides factorials up to 20, so let's use that
         * limit together with Precision.EPSILON to generate the following
         * code (a priori, we know that there will be 16 elements, but it is
         * better to not hardcode it).
         */
        final ResizableDoubleArray ra = new ResizableDoubleArray(20);
        while (ROR_less(qi, 1, "org.apache.commons.math3.distribution.ExponentialDistribution.createComponents_98", _mut53667, _mut53668, _mut53669, _mut53670, _mut53671)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.createComponents_98");
            qi += AOR_divide(FastMath.pow(LN2, i), CombinatoricsUtils.factorial(i), "org.apache.commons.math3.distribution.ExponentialDistribution.createComponents_98", _mut53663, _mut53664, _mut53665, _mut53666);
            ra.addElement(qi);
            ++i;
        }
        EXPONENTIAL_SA_QI = ra.getElements();
    }

    /**
     * Create an exponential distribution with the given mean.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean mean of this distribution.
     */
    public ExponentialDistribution(double mean) {
        this(mean, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create an exponential distribution with the given mean.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean Mean of this distribution.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code mean <= 0}.
     * @since 2.1
     */
    public ExponentialDistribution(double mean, double inverseCumAccuracy) {
        this(new Well19937c(), mean, inverseCumAccuracy);
    }

    /**
     * Creates an exponential distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean of this distribution.
     * @throws NotStrictlyPositiveException if {@code mean <= 0}.
     * @since 3.3
     */
    public ExponentialDistribution(RandomGenerator rng, double mean) throws NotStrictlyPositiveException {
        this(rng, mean, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates an exponential distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean of this distribution.
     * @param inverseCumAccuracy Maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code mean <= 0}.
     * @since 3.1
     */
    public ExponentialDistribution(RandomGenerator rng, double mean, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.ExponentialDistribution_153");
        if (ROR_less_equals(mean, 0, "org.apache.commons.math3.distribution.ExponentialDistribution.ExponentialDistribution_153", _mut53672, _mut53673, _mut53674, _mut53675, _mut53676)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, mean);
        }
        this.mean = mean;
        logMean = FastMath.log(mean);
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the mean.
     *
     * @return the mean.
     */
    public double getMean() {
        return mean;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.density_177");
        final double logDensity = logDensity(x);
        return ROR_equals(logDensity, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.ExponentialDistribution.density_177", _mut53677, _mut53678, _mut53679, _mut53680, _mut53681) ? 0 : FastMath.exp(logDensity);
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.logDensity_183");
        if (ROR_less(x, 0, "org.apache.commons.math3.distribution.ExponentialDistribution.logDensity_183", _mut53682, _mut53683, _mut53684, _mut53685, _mut53686)) {
            return Double.NEGATIVE_INFINITY;
        }
        return AOR_minus(AOR_divide(-x, mean, "org.apache.commons.math3.distribution.ExponentialDistribution.logDensity_183", _mut53687, _mut53688, _mut53689, _mut53690), logMean, "org.apache.commons.math3.distribution.ExponentialDistribution.logDensity_183", _mut53691, _mut53692, _mut53693, _mut53694);
    }

    /**
     * {@inheritDoc}
     *
     * The implementation of this method is based on:
     * <ul>
     * <li>
     * <a href="http://mathworld.wolfram.com/ExponentialDistribution.html">
     * Exponential Distribution</a>, equation (1).</li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.cumulativeProbability_201");
        double ret;
        if (ROR_less_equals(x, 0.0, "org.apache.commons.math3.distribution.ExponentialDistribution.cumulativeProbability_201", _mut53695, _mut53696, _mut53697, _mut53698, _mut53699)) {
            ret = 0.0;
        } else {
            ret = AOR_minus(1.0, FastMath.exp(AOR_divide(-x, mean, "org.apache.commons.math3.distribution.ExponentialDistribution.cumulativeProbability_201", _mut53700, _mut53701, _mut53702, _mut53703)), "org.apache.commons.math3.distribution.ExponentialDistribution.cumulativeProbability_201", _mut53704, _mut53705, _mut53706, _mut53707);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * Returns {@code 0} when {@code p= = 0} and
     * {@code Double.POSITIVE_INFINITY} when {@code p == 1}.
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217");
        double ret;
        if ((_mut53718 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53708, _mut53709, _mut53710, _mut53711, _mut53712) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53713, _mut53714, _mut53715, _mut53716, _mut53717)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53708, _mut53709, _mut53710, _mut53711, _mut53712) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53713, _mut53714, _mut53715, _mut53716, _mut53717)))) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (ROR_equals(p, 1.0, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53719, _mut53720, _mut53721, _mut53722, _mut53723)) {
            ret = Double.POSITIVE_INFINITY;
        } else {
            ret = AOR_multiply(-mean, FastMath.log(AOR_minus(1.0, p, "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53724, _mut53725, _mut53726, _mut53727)), "org.apache.commons.math3.distribution.ExponentialDistribution.inverseCumulativeProbability_217", _mut53728, _mut53729, _mut53730, _mut53731);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p><strong>Algorithm Description</strong>: this implementation uses the
     * <a href="http://www.jesus.ox.ac.uk/~clifford/a5/chap1/node5.html">
     * Inversion Method</a> to generate exponentially distributed random values
     * from uniform deviates.</p>
     *
     * @return a random value.
     * @since 2.2
     */
    @Override
    public double sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.sample_243");
        // Step 1:
        double a = 0;
        double u = random.nextDouble();
        // Step 2 and 3:
        while (ROR_less(u, 0.5, "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53732, _mut53733, _mut53734, _mut53735, _mut53736)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.sample_243");
            a += EXPONENTIAL_SA_QI[0];
            u *= 2;
        }
        // Step 4 (now u >= 0.5):
        u += AOR_minus(u, 1, "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53737, _mut53738, _mut53739, _mut53740);
        // Step 5:
        if (ROR_less_equals(u, EXPONENTIAL_SA_QI[0], "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53741, _mut53742, _mut53743, _mut53744, _mut53745)) {
            return AOR_multiply(mean, (AOR_plus(a, u, "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53746, _mut53747, _mut53748, _mut53749)), "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53750, _mut53751, _mut53752, _mut53753);
        }
        // Should be 1, be we iterate before it in while using 0
        int i = 0;
        double u2 = random.nextDouble();
        double umin = u2;
        // Step 7 and 8:
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.sample_243");
            ++i;
            u2 = random.nextDouble();
            if (ROR_less(u2, umin, "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53754, _mut53755, _mut53756, _mut53757, _mut53758)) {
                umin = u2;
            }
        } while (ROR_greater(u, EXPONENTIAL_SA_QI[i], "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53759, _mut53760, _mut53761, _mut53762, _mut53763));
        return AOR_multiply(mean, (AOR_plus(a, AOR_multiply(umin, EXPONENTIAL_SA_QI[0], "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53764, _mut53765, _mut53766, _mut53767), "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53768, _mut53769, _mut53770, _mut53771)), "org.apache.commons.math3.distribution.ExponentialDistribution.sample_243", _mut53772, _mut53773, _mut53774, _mut53775);
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
     * For mean parameter {@code k}, the mean is {@code k}.
     */
    public double getNumericalMean() {
        return getMean();
    }

    /**
     * {@inheritDoc}
     *
     * For mean parameter {@code k}, the variance is {@code k^2}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ExponentialDistribution.getNumericalVariance_303");
        final double m = getMean();
        return AOR_multiply(m, m, "org.apache.commons.math3.distribution.ExponentialDistribution.getNumericalVariance_303", _mut53776, _mut53777, _mut53778, _mut53779);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the mean parameter.
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
     * no matter the mean parameter.
     *
     * @return upper bound of the support (always Double.POSITIVE_INFINITY)
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
