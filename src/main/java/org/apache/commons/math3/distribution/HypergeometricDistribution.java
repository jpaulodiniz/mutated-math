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
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the hypergeometric distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Hypergeometric_distribution">Hypergeometric distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/HypergeometricDistribution.html">Hypergeometric distribution (MathWorld)</a>
 */
public class HypergeometricDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut57057 = false, _mut57058 = false, _mut57059 = false, _mut57060 = false, _mut57061 = false, _mut57062 = false, _mut57063 = false, _mut57064 = false, _mut57065 = false, _mut57066 = false, _mut57067 = false, _mut57068 = false, _mut57069 = false, _mut57070 = false, _mut57071 = false, _mut57072 = false, _mut57073 = false, _mut57074 = false, _mut57075 = false, _mut57076 = false, _mut57077 = false, _mut57078 = false, _mut57079 = false, _mut57080 = false, _mut57081 = false, _mut57082 = false, _mut57083 = false, _mut57084 = false, _mut57085 = false, _mut57086 = false, _mut57087 = false, _mut57088 = false, _mut57089 = false, _mut57090 = false, _mut57091 = false, _mut57092 = false, _mut57093 = false, _mut57094 = false, _mut57095 = false, _mut57096 = false, _mut57097 = false, _mut57098 = false, _mut57099 = false, _mut57100 = false, _mut57101 = false, _mut57102 = false, _mut57103 = false, _mut57104 = false, _mut57105 = false, _mut57106 = false, _mut57107 = false, _mut57108 = false, _mut57109 = false, _mut57110 = false, _mut57111 = false, _mut57112 = false, _mut57113 = false, _mut57114 = false, _mut57115 = false, _mut57116 = false, _mut57117 = false, _mut57118 = false, _mut57119 = false, _mut57120 = false, _mut57121 = false, _mut57122 = false, _mut57123 = false, _mut57124 = false, _mut57125 = false, _mut57126 = false, _mut57127 = false, _mut57128 = false, _mut57129 = false, _mut57130 = false, _mut57131 = false, _mut57132 = false, _mut57133 = false, _mut57134 = false, _mut57135 = false, _mut57136 = false, _mut57137 = false, _mut57138 = false, _mut57139 = false, _mut57140 = false, _mut57141 = false, _mut57142 = false, _mut57143 = false, _mut57144 = false, _mut57145 = false, _mut57146 = false, _mut57147 = false, _mut57148 = false, _mut57149 = false, _mut57150 = false, _mut57151 = false, _mut57152 = false, _mut57153 = false, _mut57154 = false, _mut57155 = false, _mut57156 = false, _mut57157 = false, _mut57158 = false, _mut57159 = false, _mut57160 = false, _mut57161 = false, _mut57162 = false, _mut57163 = false, _mut57164 = false, _mut57165 = false, _mut57166 = false, _mut57167 = false, _mut57168 = false, _mut57169 = false, _mut57170 = false, _mut57171 = false, _mut57172 = false, _mut57173 = false, _mut57174 = false, _mut57175 = false, _mut57176 = false, _mut57177 = false, _mut57178 = false, _mut57179 = false, _mut57180 = false, _mut57181 = false, _mut57182 = false, _mut57183 = false, _mut57184 = false, _mut57185 = false, _mut57186 = false, _mut57187 = false, _mut57188 = false, _mut57189 = false, _mut57190 = false, _mut57191 = false, _mut57192 = false, _mut57193 = false, _mut57194 = false, _mut57195 = false, _mut57196 = false, _mut57197 = false, _mut57198 = false, _mut57199 = false, _mut57200 = false, _mut57201 = false, _mut57202 = false, _mut57203 = false, _mut57204 = false, _mut57205 = false, _mut57206 = false, _mut57207 = false, _mut57208 = false, _mut57209 = false, _mut57210 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -436928820673516179L;

    /**
     * The number of successes in the population.
     */
    private final int numberOfSuccesses;

    /**
     * The population size.
     */
    private final int populationSize;

    /**
     * The sample size.
     */
    private final int sampleSize;

    /**
     * Cached numerical variance
     */
    private double numericalVariance = Double.NaN;

    /**
     * Whether or not the numerical variance has been calculated
     */
    private boolean numericalVarianceIsCalculated = false;

    /**
     * Construct a new hypergeometric distribution with the specified population
     * size, number of successes in the population, and sample size.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param populationSize Population size.
     * @param numberOfSuccesses Number of successes in the population.
     * @param sampleSize Sample size.
     * @throws NotPositiveException if {@code numberOfSuccesses < 0}.
     * @throws NotStrictlyPositiveException if {@code populationSize <= 0}.
     * @throws NumberIsTooLargeException if {@code numberOfSuccesses > populationSize},
     * or {@code sampleSize > populationSize}.
     */
    public HypergeometricDistribution(int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        this(new Well19937c(), populationSize, numberOfSuccesses, sampleSize);
    }

    /**
     * Creates a new hypergeometric distribution.
     *
     * @param rng Random number generator.
     * @param populationSize Population size.
     * @param numberOfSuccesses Number of successes in the population.
     * @param sampleSize Sample size.
     * @throws NotPositiveException if {@code numberOfSuccesses < 0}.
     * @throws NotStrictlyPositiveException if {@code populationSize <= 0}.
     * @throws NumberIsTooLargeException if {@code numberOfSuccesses > populationSize},
     * or {@code sampleSize > populationSize}.
     * @since 3.1
     */
    public HypergeometricDistribution(RandomGenerator rng, int populationSize, int numberOfSuccesses, int sampleSize) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85");
        if (ROR_less_equals(populationSize, 0, "org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85", _mut57057, _mut57058, _mut57059, _mut57060, _mut57061)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.POPULATION_SIZE, populationSize);
        }
        if (ROR_less(numberOfSuccesses, 0, "org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85", _mut57062, _mut57063, _mut57064, _mut57065, _mut57066)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, numberOfSuccesses);
        }
        if (ROR_less(sampleSize, 0, "org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85", _mut57067, _mut57068, _mut57069, _mut57070, _mut57071)) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        if (ROR_greater(numberOfSuccesses, populationSize, "org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85", _mut57072, _mut57073, _mut57074, _mut57075, _mut57076)) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_OF_SUCCESS_LARGER_THAN_POPULATION_SIZE, numberOfSuccesses, populationSize, true);
        }
        if (ROR_greater(sampleSize, populationSize, "org.apache.commons.math3.distribution.HypergeometricDistribution.HypergeometricDistribution_85", _mut57077, _mut57078, _mut57079, _mut57080, _mut57081)) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_LARGER_THAN_POPULATION_SIZE, sampleSize, populationSize, true);
        }
        this.numberOfSuccesses = numberOfSuccesses;
        this.populationSize = populationSize;
        this.sampleSize = sampleSize;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.cumulativeProbability_120");
        double ret;
        int[] domain = getDomain(populationSize, numberOfSuccesses, sampleSize);
        if (ROR_less(x, domain[0], "org.apache.commons.math3.distribution.HypergeometricDistribution.cumulativeProbability_120", _mut57082, _mut57083, _mut57084, _mut57085, _mut57086)) {
            ret = 0.0;
        } else if (ROR_greater_equals(x, domain[1], "org.apache.commons.math3.distribution.HypergeometricDistribution.cumulativeProbability_120", _mut57087, _mut57088, _mut57089, _mut57090, _mut57091)) {
            ret = 1.0;
        } else {
            ret = innerCumulativeProbability(domain[0], x, 1);
        }
        return ret;
    }

    /**
     * Return the domain for the given hypergeometric distribution parameters.
     *
     * @param n Population size.
     * @param m Number of successes in the population.
     * @param k Sample size.
     * @return a two element array containing the lower and upper bounds of the
     * hypergeometric distribution.
     */
    private int[] getDomain(int n, int m, int k) {
        return new int[] { getLowerDomain(n, m, k), getUpperDomain(m, k) };
    }

    /**
     * Return the lowest domain value for the given hypergeometric distribution
     * parameters.
     *
     * @param n Population size.
     * @param m Number of successes in the population.
     * @param k Sample size.
     * @return the lowest domain value of the hypergeometric distribution.
     */
    private int getLowerDomain(int n, int m, int k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.getLowerDomain_157");
        return FastMath.max(0, AOR_minus(m, (AOR_minus(n, k, "org.apache.commons.math3.distribution.HypergeometricDistribution.getLowerDomain_157", _mut57092, _mut57093, _mut57094, _mut57095)), "org.apache.commons.math3.distribution.HypergeometricDistribution.getLowerDomain_157", _mut57096, _mut57097, _mut57098, _mut57099));
    }

    /**
     * Access the number of successes.
     *
     * @return the number of successes.
     */
    public int getNumberOfSuccesses() {
        return numberOfSuccesses;
    }

    /**
     * Access the population size.
     *
     * @return the population size.
     */
    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * Access the sample size.
     *
     * @return the sample size.
     */
    public int getSampleSize() {
        return sampleSize;
    }

    /**
     * Return the highest domain value for the given hypergeometric distribution
     * parameters.
     *
     * @param m Number of successes in the population.
     * @param k Sample size.
     * @return the highest domain value of the hypergeometric distribution.
     */
    private int getUpperDomain(int m, int k) {
        return FastMath.min(k, m);
    }

    /**
     * {@inheritDoc}
     */
    public double probability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.probability_201");
        final double logProbability = logProbability(x);
        return ROR_equals(logProbability, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.distribution.HypergeometricDistribution.probability_201", _mut57100, _mut57101, _mut57102, _mut57103, _mut57104) ? 0 : FastMath.exp(logProbability);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207");
        double ret;
        int[] domain = getDomain(populationSize, numberOfSuccesses, sampleSize);
        if ((_mut57115 ? (ROR_less(x, domain[0], "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57105, _mut57106, _mut57107, _mut57108, _mut57109) && ROR_greater(x, domain[1], "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57110, _mut57111, _mut57112, _mut57113, _mut57114)) : (ROR_less(x, domain[0], "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57105, _mut57106, _mut57107, _mut57108, _mut57109) || ROR_greater(x, domain[1], "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57110, _mut57111, _mut57112, _mut57113, _mut57114)))) {
            ret = Double.NEGATIVE_INFINITY;
        } else {
            double p = AOR_divide((double) sampleSize, (double) populationSize, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57116, _mut57117, _mut57118, _mut57119);
            double q = AOR_divide((double) (AOR_minus(populationSize, sampleSize, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57120, _mut57121, _mut57122, _mut57123)), (double) populationSize, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57124, _mut57125, _mut57126, _mut57127);
            double p1 = SaddlePointExpansion.logBinomialProbability(x, numberOfSuccesses, p, q);
            double p2 = SaddlePointExpansion.logBinomialProbability(AOR_minus(sampleSize, x, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57128, _mut57129, _mut57130, _mut57131), AOR_minus(populationSize, numberOfSuccesses, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57132, _mut57133, _mut57134, _mut57135), p, q);
            double p3 = SaddlePointExpansion.logBinomialProbability(sampleSize, populationSize, p, q);
            ret = AOR_minus(AOR_plus(p1, p2, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57136, _mut57137, _mut57138, _mut57139), p3, "org.apache.commons.math3.distribution.HypergeometricDistribution.logProbability_207", _mut57140, _mut57141, _mut57142, _mut57143);
        }
        return ret;
    }

    /**
     * For this distribution, {@code X}, this method returns {@code P(X >= x)}.
     *
     * @param x Value at which the CDF is evaluated.
     * @return the upper tail CDF for this distribution.
     * @since 1.1
     */
    public double upperCumulativeProbability(int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.upperCumulativeProbability_237");
        double ret;
        final int[] domain = getDomain(populationSize, numberOfSuccesses, sampleSize);
        if (ROR_less_equals(x, domain[0], "org.apache.commons.math3.distribution.HypergeometricDistribution.upperCumulativeProbability_237", _mut57144, _mut57145, _mut57146, _mut57147, _mut57148)) {
            ret = 1.0;
        } else if (ROR_greater(x, domain[1], "org.apache.commons.math3.distribution.HypergeometricDistribution.upperCumulativeProbability_237", _mut57149, _mut57150, _mut57151, _mut57152, _mut57153)) {
            ret = 0.0;
        } else {
            ret = innerCumulativeProbability(domain[1], x, -1);
        }
        return ret;
    }

    /**
     * For this distribution, {@code X}, this method returns
     * {@code P(x0 <= X <= x1)}.
     * This probability is computed by summing the point probabilities for the
     * values {@code x0, x0 + 1, x0 + 2, ..., x1}, in the order directed by
     * {@code dx}.
     *
     * @param x0 Inclusive lower bound.
     * @param x1 Inclusive upper bound.
     * @param dx Direction of summation (1 indicates summing from x0 to x1, and
     * 0 indicates summing from x1 to x0).
     * @return {@code P(x0 <= X <= x1)}.
     */
    private double innerCumulativeProbability(int x0, int x1, int dx) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.innerCumulativeProbability_265");
        double ret = probability(x0);
        while (ROR_not_equals(x0, x1, "org.apache.commons.math3.distribution.HypergeometricDistribution.innerCumulativeProbability_265", _mut57154, _mut57155, _mut57156, _mut57157, _mut57158)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.innerCumulativeProbability_265");
            x0 += dx;
            ret += probability(x0);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * For population size {@code N}, number of successes {@code m}, and sample
     * size {@code n}, the mean is {@code n * m / N}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.getNumericalMean_280");
        return AOR_multiply(getSampleSize(), (AOR_divide(getNumberOfSuccesses(), (double) getPopulationSize(), "org.apache.commons.math3.distribution.HypergeometricDistribution.getNumericalMean_280", _mut57159, _mut57160, _mut57161, _mut57162)), "org.apache.commons.math3.distribution.HypergeometricDistribution.getNumericalMean_280", _mut57163, _mut57164, _mut57165, _mut57166);
    }

    /**
     * {@inheritDoc}
     *
     * For population size {@code N}, number of successes {@code m}, and sample
     * size {@code n}, the variance is
     * {@code [n * m * (N - n) * (N - m)] / [N^2 * (N - 1)]}.
     */
    public double getNumericalVariance() {
        if (!numericalVarianceIsCalculated) {
            numericalVariance = calculateNumericalVariance();
            numericalVarianceIsCalculated = true;
        }
        return numericalVariance;
    }

    /**
     * Used by {@link #getNumericalVariance()}.
     *
     * @return the variance of this distribution
     */
    protected double calculateNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304");
        final double N = getPopulationSize();
        final double m = getNumberOfSuccesses();
        final double n = getSampleSize();
        return AOR_divide((AOR_multiply(AOR_multiply(AOR_multiply(n, m, "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57167, _mut57168, _mut57169, _mut57170), (AOR_minus(N, n, "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57171, _mut57172, _mut57173, _mut57174)), "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57175, _mut57176, _mut57177, _mut57178), (AOR_minus(N, m, "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57179, _mut57180, _mut57181, _mut57182)), "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57183, _mut57184, _mut57185, _mut57186)), (AOR_multiply(AOR_multiply(N, N, "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57187, _mut57188, _mut57189, _mut57190), (AOR_minus(N, 1, "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57191, _mut57192, _mut57193, _mut57194)), "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57195, _mut57196, _mut57197, _mut57198)), "org.apache.commons.math3.distribution.HypergeometricDistribution.calculateNumericalVariance_304", _mut57199, _mut57200, _mut57201, _mut57202);
    }

    /**
     * {@inheritDoc}
     *
     * For population size {@code N}, number of successes {@code m}, and sample
     * size {@code n}, the lower bound of the support is
     * {@code max(0, n + m - N)}.
     *
     * @return lower bound of the support
     */
    public int getSupportLowerBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.HypergeometricDistribution.getSupportLowerBound_320");
        return FastMath.max(0, AOR_minus(AOR_plus(getSampleSize(), getNumberOfSuccesses(), "org.apache.commons.math3.distribution.HypergeometricDistribution.getSupportLowerBound_320", _mut57203, _mut57204, _mut57205, _mut57206), getPopulationSize(), "org.apache.commons.math3.distribution.HypergeometricDistribution.getSupportLowerBound_320", _mut57207, _mut57208, _mut57209, _mut57210));
    }

    /**
     * {@inheritDoc}
     *
     * For number of successes {@code m} and sample size {@code n}, the upper
     * bound of the support is {@code min(m, n)}.
     *
     * @return upper bound of the support
     */
    public int getSupportUpperBound() {
        return FastMath.min(getNumberOfSuccesses(), getSampleSize());
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
