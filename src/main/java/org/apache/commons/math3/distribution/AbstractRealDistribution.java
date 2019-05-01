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
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for probability distributions on the reals.
 * Default implementations are provided for some of the methods
 * that do not vary from distribution to distribution.
 *
 * @since 3.0
 */
public abstract class AbstractRealDistribution implements RealDistribution, Serializable {

    @Conditional
    public static boolean _mut56835 = false, _mut56836 = false, _mut56837 = false, _mut56838 = false, _mut56839 = false, _mut56840 = false, _mut56841 = false, _mut56842 = false, _mut56843 = false, _mut56844 = false, _mut56845 = false, _mut56846 = false, _mut56847 = false, _mut56848 = false, _mut56849 = false, _mut56850 = false, _mut56851 = false, _mut56852 = false, _mut56853 = false, _mut56854 = false, _mut56855 = false, _mut56856 = false, _mut56857 = false, _mut56858 = false, _mut56859 = false, _mut56860 = false, _mut56861 = false, _mut56862 = false, _mut56863 = false, _mut56864 = false, _mut56865 = false, _mut56866 = false, _mut56867 = false, _mut56868 = false, _mut56869 = false, _mut56870 = false, _mut56871 = false, _mut56872 = false, _mut56873 = false, _mut56874 = false, _mut56875 = false, _mut56876 = false, _mut56877 = false, _mut56878 = false, _mut56879 = false, _mut56880 = false, _mut56881 = false, _mut56882 = false, _mut56883 = false, _mut56884 = false, _mut56885 = false, _mut56886 = false, _mut56887 = false, _mut56888 = false, _mut56889 = false, _mut56890 = false, _mut56891 = false, _mut56892 = false, _mut56893 = false, _mut56894 = false, _mut56895 = false, _mut56896 = false, _mut56897 = false, _mut56898 = false, _mut56899 = false, _mut56900 = false, _mut56901 = false, _mut56902 = false, _mut56903 = false, _mut56904 = false, _mut56905 = false, _mut56906 = false, _mut56907 = false, _mut56908 = false, _mut56909 = false, _mut56910 = false, _mut56911 = false, _mut56912 = false, _mut56913 = false, _mut56914 = false, _mut56915 = false, _mut56916 = false, _mut56917 = false, _mut56918 = false, _mut56919 = false, _mut56920 = false, _mut56921 = false, _mut56922 = false, _mut56923 = false, _mut56924 = false, _mut56925 = false, _mut56926 = false, _mut56927 = false, _mut56928 = false, _mut56929 = false, _mut56930 = false, _mut56931 = false, _mut56932 = false, _mut56933 = false, _mut56934 = false, _mut56935 = false, _mut56936 = false, _mut56937 = false, _mut56938 = false, _mut56939 = false, _mut56940 = false, _mut56941 = false, _mut56942 = false, _mut56943 = false, _mut56944 = false, _mut56945 = false, _mut56946 = false, _mut56947 = false, _mut56948 = false, _mut56949 = false, _mut56950 = false, _mut56951 = false, _mut56952 = false, _mut56953 = false, _mut56954 = false, _mut56955 = false, _mut56956 = false, _mut56957 = false, _mut56958 = false, _mut56959 = false, _mut56960 = false, _mut56961 = false, _mut56962 = false, _mut56963 = false, _mut56964 = false, _mut56965 = false, _mut56966 = false, _mut56967 = false, _mut56968 = false, _mut56969 = false, _mut56970 = false, _mut56971 = false, _mut56972 = false, _mut56973 = false;

    /**
     * Default accuracy.
     */
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -38038050983108802L;

    /**
     * RandomData instance used to generate samples from the distribution.
     * @deprecated As of 3.1, to be removed in 4.0. Please use the
     * {@link #random} instance variable instead.
     */
    @Deprecated
    protected org.apache.commons.math3.random.RandomDataImpl randomData = new org.apache.commons.math3.random.RandomDataImpl();

    /**
     * RNG instance used to generate samples from the distribution.
     * @since 3.1
     */
    protected final RandomGenerator random;

    /**
     * Solver absolute accuracy for inverse cumulative computation
     */
    private double solverAbsoluteAccuracy = SOLVER_DEFAULT_ABSOLUTE_ACCURACY;

    /**
     * @deprecated As of 3.1, to be removed in 4.0. Please use
     * {@link #AbstractRealDistribution(RandomGenerator)} instead.
     */
    @Deprecated
    protected AbstractRealDistribution() {
        // New users are forbidden to use this constructor.
        random = null;
    }

    /**
     * @param rng Random number generator.
     * @since 3.1
     */
    protected AbstractRealDistribution(RandomGenerator rng) {
        random = rng;
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation uses the identity
     * <p>{@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}</p>
     *
     * @deprecated As of 3.1 (to be removed in 4.0). Please use
     * {@link #probability(double,double)} instead.
     */
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    /**
     * For a random variable {@code X} whose values are distributed according
     * to this distribution, this method returns {@code P(x0 < X <= x1)}.
     *
     * @param x0 Lower bound (excluded).
     * @param x1 Upper bound (included).
     * @return the probability that a random variable with this distribution
     * takes a value between {@code x0} and {@code x1}, excluding the lower
     * and including the upper endpoint.
     * @throws NumberIsTooLargeException if {@code x0 > x1}.
     *
     * The default implementation uses the identity
     * {@code P(x0 < X <= x1) = P(X <= x1) - P(X <= x0)}
     *
     * @since 3.1
     */
    public double probability(double x0, double x1) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.probability_109");
        if (ROR_greater(x0, x1, "org.apache.commons.math3.distribution.AbstractRealDistribution.probability_109", _mut56835, _mut56836, _mut56837, _mut56838, _mut56839)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, x0, x1, true);
        }
        return AOR_minus(cumulativeProbability(x1), cumulativeProbability(x0), "org.apache.commons.math3.distribution.AbstractRealDistribution.probability_109", _mut56840, _mut56841, _mut56842, _mut56843);
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation returns
     * <ul>
     * <li>{@link #getSupportLowerBound()} for {@code p = 0},</li>
     * <li>{@link #getSupportUpperBound()} for {@code p = 1}.</li>
     * </ul>
     */
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.value_200");
        /*
         * IMPLEMENTATION NOTES
         * --------------------
         * Where applicable, use is made of the one-sided Chebyshev inequality
         * to bracket the root. This inequality states that
         * P(X - mu >= k * sig) <= 1 / (1 + k^2),
         * mu: mean, sig: standard deviation. Equivalently
         * 1 - P(X < mu + k * sig) <= 1 / (1 + k^2),
         * F(mu + k * sig) >= k^2 / (1 + k^2).
         *
         * For k = sqrt(p / (1 - p)), we find
         * F(mu + k * sig) >= p,
         * and (mu + k * sig) is an upper-bound for the root.
         *
         * Then, introducing Y = -X, mean(Y) = -mu, sd(Y) = sig, and
         * P(Y >= -mu + k * sig) <= 1 / (1 + k^2),
         * P(-X >= -mu + k * sig) <= 1 / (1 + k^2),
         * P(X <= mu - k * sig) <= 1 / (1 + k^2),
         * F(mu - k * sig) <= 1 / (1 + k^2).
         *
         * For k = sqrt((1 - p) / p), we find
         * F(mu - k * sig) <= p,
         * and (mu - k * sig) is a lower-bound for the root.
         *
         * In cases where the Chebyshev inequality does not apply, geometric
         * progressions 1, 2, 4, ... and -1, -2, -4, ... are used to bracket
         * the root.
         */
        if ((_mut56854 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56844, _mut56845, _mut56846, _mut56847, _mut56848) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56849, _mut56850, _mut56851, _mut56852, _mut56853)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56844, _mut56845, _mut56846, _mut56847, _mut56848) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56849, _mut56850, _mut56851, _mut56852, _mut56853)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        double lowerBound = getSupportLowerBound();
        if (ROR_equals(p, 0.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56855, _mut56856, _mut56857, _mut56858, _mut56859)) {
            return lowerBound;
        }
        double upperBound = getSupportUpperBound();
        if (ROR_equals(p, 1.0, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56860, _mut56861, _mut56862, _mut56863, _mut56864)) {
            return upperBound;
        }
        final double mu = getNumericalMean();
        final double sig = FastMath.sqrt(getNumericalVariance());
        final boolean chebyshevApplies;
        chebyshevApplies = !((_mut56867 ? ((_mut56866 ? ((_mut56865 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sig)) : ((_mut56865 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sig))) && Double.isNaN(sig)) : ((_mut56866 ? ((_mut56865 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) && Double.isInfinite(sig)) : ((_mut56865 ? (Double.isInfinite(mu) && Double.isNaN(mu)) : (Double.isInfinite(mu) || Double.isNaN(mu))) || Double.isInfinite(sig))) || Double.isNaN(sig))));
        if (ROR_equals(lowerBound, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56868, _mut56869, _mut56870, _mut56871, _mut56872)) {
            if (chebyshevApplies) {
                lowerBound = AOR_minus(mu, AOR_multiply(sig, FastMath.sqrt(AOR_divide((AOR_minus(1., p, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56878, _mut56879, _mut56880, _mut56881)), p, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56882, _mut56883, _mut56884, _mut56885)), "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56886, _mut56887, _mut56888, _mut56889), "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56890, _mut56891, _mut56892, _mut56893);
            } else {
                lowerBound = -1.0;
                while (ROR_greater_equals(cumulativeProbability(lowerBound), p, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56873, _mut56874, _mut56875, _mut56876, _mut56877)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127");
                    lowerBound *= 2.0;
                }
            }
        }
        if (ROR_equals(upperBound, Double.POSITIVE_INFINITY, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56894, _mut56895, _mut56896, _mut56897, _mut56898)) {
            if (chebyshevApplies) {
                upperBound = AOR_plus(mu, AOR_multiply(sig, FastMath.sqrt(AOR_divide(p, (AOR_minus(1., p, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56904, _mut56905, _mut56906, _mut56907)), "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56908, _mut56909, _mut56910, _mut56911)), "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56912, _mut56913, _mut56914, _mut56915), "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56916, _mut56917, _mut56918, _mut56919);
            } else {
                upperBound = 1.0;
                while (ROR_less(cumulativeProbability(upperBound), p, "org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127", _mut56899, _mut56900, _mut56901, _mut56902, _mut56903)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability_127");
                    upperBound *= 2.0;
                }
            }
        }
        final UnivariateFunction toSolve = new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.value_200");
                return AOR_minus(cumulativeProbability(x), p, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56920, _mut56921, _mut56922, _mut56923);
            }
        };
        double x = UnivariateSolverUtils.solve(toSolve, lowerBound, upperBound, getSolverAbsoluteAccuracy());
        if (!isSupportConnected()) {
            /* Test for plateau. */
            final double dx = getSolverAbsoluteAccuracy();
            if (ROR_greater_equals(AOR_minus(x, dx, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56924, _mut56925, _mut56926, _mut56927), getSupportLowerBound(), "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56928, _mut56929, _mut56930, _mut56931, _mut56932)) {
                double px = cumulativeProbability(x);
                if (ROR_equals(cumulativeProbability(AOR_minus(x, dx, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56933, _mut56934, _mut56935, _mut56936)), px, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56937, _mut56938, _mut56939, _mut56940, _mut56941)) {
                    upperBound = x;
                    while (ROR_greater(AOR_minus(upperBound, lowerBound, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56955, _mut56956, _mut56957, _mut56958), dx, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56959, _mut56960, _mut56961, _mut56962, _mut56963)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.value_200");
                        final double midPoint = AOR_multiply(0.5, (AOR_plus(lowerBound, upperBound, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56942, _mut56943, _mut56944, _mut56945)), "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56946, _mut56947, _mut56948, _mut56949);
                        if (ROR_less(cumulativeProbability(midPoint), px, "org.apache.commons.math3.distribution.AbstractRealDistribution.value_200", _mut56950, _mut56951, _mut56952, _mut56953, _mut56954)) {
                            lowerBound = midPoint;
                        } else {
                            upperBound = midPoint;
                        }
                    }
                    return upperBound;
                }
            }
        }
        return x;
    }

    /**
     * Returns the solver absolute accuracy for inverse cumulative computation.
     * You can override this method in order to use a Brent solver with an
     * absolute accuracy different from the default.
     *
     * @return the maximum absolute error in inverse cumulative probability estimates
     */
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
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
     * inversion method.
     * </a>
     */
    public double sample() {
        return inverseCumulativeProbability(random.nextDouble());
    }

    /**
     * {@inheritDoc}
     *
     * The default implementation generates the sample by calling
     * {@link #sample()} in a loop.
     */
    public double[] sample(int sampleSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.sample_267");
        if (ROR_less_equals(sampleSize, 0, "org.apache.commons.math3.distribution.AbstractRealDistribution.sample_267", _mut56964, _mut56965, _mut56966, _mut56967, _mut56968)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        double[] out = new double[sampleSize];
        for (int i = 0; ROR_less(i, sampleSize, "org.apache.commons.math3.distribution.AbstractRealDistribution.sample_267", _mut56969, _mut56970, _mut56971, _mut56972, _mut56973); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractRealDistribution.sample_267");
            out[i] = sample();
        }
        return out;
    }

    /**
     * {@inheritDoc}
     *
     * @return zero.
     * @since 3.1
     */
    public double probability(double x) {
        return 0d;
    }

    /**
     * Returns the natural logarithm of the probability density function (PDF) of this distribution
     * evaluated at the specified point {@code x}. In general, the PDF is the derivative of the
     * {@link #cumulativeProbability(double) CDF}. If the derivative does not exist at {@code x},
     * then an appropriate replacement should be returned, e.g. {@code Double.POSITIVE_INFINITY},
     * {@code Double.NaN}, or the limit inferior or limit superior of the difference quotient. Note
     * that due to the floating point precision and under/overflow issues, this method will for some
     * distributions be more precise and faster than computing the logarithm of
     * {@link #density(double)}. The default implementation simply computes the logarithm of
     * {@code density(x)}.
     *
     * @param x the point at which the PDF is evaluated
     * @return the logarithm of the value of the probability density function at point {@code x}
     */
    public double logDensity(double x) {
        return FastMath.log(density(x));
    }
}
