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
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the normal (gaussian) distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Normal_distribution">Normal distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/NormalDistribution.html">Normal distribution (MathWorld)</a>
 */
public class NormalDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut55040 = false, _mut55041 = false, _mut55042 = false, _mut55043 = false, _mut55044 = false, _mut55045 = false, _mut55046 = false, _mut55047 = false, _mut55048 = false, _mut55049 = false, _mut55050 = false, _mut55051 = false, _mut55052 = false, _mut55053 = false, _mut55054 = false, _mut55055 = false, _mut55056 = false, _mut55057 = false, _mut55058 = false, _mut55059 = false, _mut55060 = false, _mut55061 = false, _mut55062 = false, _mut55063 = false, _mut55064 = false, _mut55065 = false, _mut55066 = false, _mut55067 = false, _mut55068 = false, _mut55069 = false, _mut55070 = false, _mut55071 = false, _mut55072 = false, _mut55073 = false, _mut55074 = false, _mut55075 = false, _mut55076 = false, _mut55077 = false, _mut55078 = false, _mut55079 = false, _mut55080 = false, _mut55081 = false, _mut55082 = false, _mut55083 = false, _mut55084 = false, _mut55085 = false, _mut55086 = false, _mut55087 = false, _mut55088 = false, _mut55089 = false, _mut55090 = false, _mut55091 = false, _mut55092 = false, _mut55093 = false, _mut55094 = false, _mut55095 = false, _mut55096 = false, _mut55097 = false, _mut55098 = false, _mut55099 = false, _mut55100 = false, _mut55101 = false, _mut55102 = false, _mut55103 = false, _mut55104 = false, _mut55105 = false, _mut55106 = false, _mut55107 = false, _mut55108 = false, _mut55109 = false, _mut55110 = false, _mut55111 = false, _mut55112 = false, _mut55113 = false, _mut55114 = false, _mut55115 = false, _mut55116 = false, _mut55117 = false, _mut55118 = false, _mut55119 = false, _mut55120 = false, _mut55121 = false, _mut55122 = false, _mut55123 = false, _mut55124 = false, _mut55125 = false, _mut55126 = false, _mut55127 = false, _mut55128 = false, _mut55129 = false, _mut55130 = false, _mut55131 = false, _mut55132 = false, _mut55133 = false, _mut55134 = false, _mut55135 = false, _mut55136 = false, _mut55137 = false, _mut55138 = false, _mut55139 = false, _mut55140 = false, _mut55141 = false, _mut55142 = false, _mut55143 = false, _mut55144 = false, _mut55145 = false, _mut55146 = false, _mut55147 = false, _mut55148 = false, _mut55149 = false, _mut55150 = false, _mut55151 = false, _mut55152 = false, _mut55153 = false, _mut55154 = false, _mut55155 = false, _mut55156 = false, _mut55157 = false, _mut55158 = false, _mut55159 = false, _mut55160 = false, _mut55161 = false, _mut55162 = false, _mut55163 = false, _mut55164 = false, _mut55165 = false, _mut55166 = false, _mut55167 = false, _mut55168 = false, _mut55169 = false, _mut55170 = false, _mut55171 = false, _mut55172 = false, _mut55173 = false, _mut55174 = false, _mut55175 = false, _mut55176 = false, _mut55177 = false, _mut55178 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 8589540077390120676L;

    /**
     * &radic;(2)
     */
    private static final double SQRT2 = FastMath.sqrt(2.0);

    /**
     * Mean of this distribution.
     */
    private final double mean;

    /**
     * Standard deviation of this distribution.
     */
    private final double standardDeviation;

    /**
     * The value of {@code log(sd) + 0.5*log(2*pi)} stored for faster computation.
     */
    private final double logStandardDeviationPlusHalfLog2Pi;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Create a normal distribution with mean equal to zero and standard
     * deviation equal to one.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     */
    public NormalDistribution() {
        this(0, 1);
    }

    /**
     * Create a normal distribution using the given mean and standard deviation.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     */
    public NormalDistribution(double mean, double sd) throws NotStrictlyPositiveException {
        this(mean, sd, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a normal distribution using the given mean, standard deviation and
     * inverse cumulative distribution accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 2.1
     */
    public NormalDistribution(double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), mean, sd, inverseCumAccuracy);
    }

    /**
     * Creates a normal distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 3.3
     */
    public NormalDistribution(RandomGenerator rng, double mean, double sd) throws NotStrictlyPositiveException {
        this(rng, mean, sd, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a normal distribution.
     *
     * @param rng Random number generator.
     * @param mean Mean for this distribution.
     * @param sd Standard deviation for this distribution.
     * @param inverseCumAccuracy Inverse cumulative probability accuracy.
     * @throws NotStrictlyPositiveException if {@code sd <= 0}.
     * @since 3.1
     */
    public NormalDistribution(RandomGenerator rng, double mean, double sd, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.NormalDistribution_134");
        if (ROR_less_equals(sd, 0, "org.apache.commons.math3.distribution.NormalDistribution.NormalDistribution_134", _mut55040, _mut55041, _mut55042, _mut55043, _mut55044)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, sd);
        }
        this.mean = mean;
        standardDeviation = sd;
        logStandardDeviationPlusHalfLog2Pi = AOR_plus(FastMath.log(sd), AOR_multiply(0.5, FastMath.log(AOR_multiply(2, FastMath.PI, "org.apache.commons.math3.distribution.NormalDistribution.NormalDistribution_134", _mut55045, _mut55046, _mut55047, _mut55048)), "org.apache.commons.math3.distribution.NormalDistribution.NormalDistribution_134", _mut55049, _mut55050, _mut55051, _mut55052), "org.apache.commons.math3.distribution.NormalDistribution.NormalDistribution_134", _mut55053, _mut55054, _mut55055, _mut55056);
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the mean.
     *
     * @return the mean for this distribution.
     */
    public double getMean() {
        return mean;
    }

    /**
     * Access the standard deviation.
     *
     * @return the standard deviation for this distribution.
     */
    public double getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        return FastMath.exp(logDensity(x));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.logDensity_175");
        final double x0 = AOR_minus(x, mean, "org.apache.commons.math3.distribution.NormalDistribution.logDensity_175", _mut55057, _mut55058, _mut55059, _mut55060);
        final double x1 = AOR_divide(x0, standardDeviation, "org.apache.commons.math3.distribution.NormalDistribution.logDensity_175", _mut55061, _mut55062, _mut55063, _mut55064);
        return AOR_minus(AOR_multiply(AOR_multiply(-0.5, x1, "org.apache.commons.math3.distribution.NormalDistribution.logDensity_175", _mut55065, _mut55066, _mut55067, _mut55068), x1, "org.apache.commons.math3.distribution.NormalDistribution.logDensity_175", _mut55069, _mut55070, _mut55071, _mut55072), logStandardDeviationPlusHalfLog2Pi, "org.apache.commons.math3.distribution.NormalDistribution.logDensity_175", _mut55073, _mut55074, _mut55075, _mut55076);
    }

    /**
     * {@inheritDoc}
     *
     * If {@code x} is more than 40 standard deviations from the mean, 0 or 1
     * is returned, as in these cases the actual value is within
     * {@code Double.MIN_VALUE} of 0 or 1.
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189");
        final double dev = AOR_minus(x, mean, "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55077, _mut55078, _mut55079, _mut55080);
        if (ROR_greater(FastMath.abs(dev), AOR_multiply(40, standardDeviation, "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55081, _mut55082, _mut55083, _mut55084), "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55085, _mut55086, _mut55087, _mut55088, _mut55089)) {
            return ROR_less(dev, 0, "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55090, _mut55091, _mut55092, _mut55093, _mut55094) ? 0.0d : 1.0d;
        }
        return AOR_multiply(0.5, Erf.erfc(AOR_divide(-dev, (AOR_multiply(standardDeviation, SQRT2, "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55095, _mut55096, _mut55097, _mut55098)), "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55099, _mut55100, _mut55101, _mut55102)), "org.apache.commons.math3.distribution.NormalDistribution.cumulativeProbability_189", _mut55103, _mut55104, _mut55105, _mut55106);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200");
        if ((_mut55117 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55107, _mut55108, _mut55109, _mut55110, _mut55111) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55112, _mut55113, _mut55114, _mut55115, _mut55116)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55107, _mut55108, _mut55109, _mut55110, _mut55111) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55112, _mut55113, _mut55114, _mut55115, _mut55116)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        return AOR_plus(mean, AOR_multiply(AOR_multiply(standardDeviation, SQRT2, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55118, _mut55119, _mut55120, _mut55121), Erf.erfInv(AOR_minus(AOR_multiply(2, p, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55122, _mut55123, _mut55124, _mut55125), 1, "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55126, _mut55127, _mut55128, _mut55129)), "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55130, _mut55131, _mut55132, _mut55133), "org.apache.commons.math3.distribution.NormalDistribution.inverseCumulativeProbability_200", _mut55134, _mut55135, _mut55136, _mut55137);
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated See {@link RealDistribution#cumulativeProbability(double,double)}
     */
    @Override
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(double x0, double x1) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.probability_220");
        if (ROR_greater(x0, x1, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55138, _mut55139, _mut55140, _mut55141, _mut55142)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, x0, x1, true);
        }
        final double denom = AOR_multiply(standardDeviation, SQRT2, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55143, _mut55144, _mut55145, _mut55146);
        final double v0 = AOR_divide((AOR_minus(x0, mean, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55147, _mut55148, _mut55149, _mut55150)), denom, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55151, _mut55152, _mut55153, _mut55154);
        final double v1 = AOR_divide((AOR_minus(x1, mean, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55155, _mut55156, _mut55157, _mut55158)), denom, "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55159, _mut55160, _mut55161, _mut55162);
        return AOR_multiply(0.5, Erf.erf(v0, v1), "org.apache.commons.math3.distribution.NormalDistribution.probability_220", _mut55163, _mut55164, _mut55165, _mut55166);
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
     * For mean parameter {@code mu}, the mean is {@code mu}.
     */
    public double getNumericalMean() {
        return getMean();
    }

    /**
     * {@inheritDoc}
     *
     * For standard deviation parameter {@code s}, the variance is {@code s^2}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.getNumericalVariance_254");
        final double s = getStandardDeviation();
        return AOR_multiply(s, s, "org.apache.commons.math3.distribution.NormalDistribution.getNumericalVariance_254", _mut55167, _mut55168, _mut55169, _mut55170);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always negative infinity
     * no matter the parameters.
     *
     * @return lower bound of the support (always
     * {@code Double.NEGATIVE_INFINITY})
     */
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity
     * no matter the parameters.
     *
     * @return upper bound of the support (always
     * {@code Double.POSITIVE_INFINITY})
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NormalDistribution.sample_307");
        return AOR_plus(AOR_multiply(standardDeviation, random.nextGaussian(), "org.apache.commons.math3.distribution.NormalDistribution.sample_307", _mut55171, _mut55172, _mut55173, _mut55174), mean, "org.apache.commons.math3.distribution.NormalDistribution.sample_307", _mut55175, _mut55176, _mut55177, _mut55178);
    }
}
