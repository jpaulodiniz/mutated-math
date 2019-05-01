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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Implementation of a real-valued {@link EnumeratedDistribution}.
 *
 * <p>Values with zero-probability are allowed but they do not extend the
 * support.<br/>
 * Duplicate values are allowed. Probabilities of duplicate values are combined
 * when computing cumulative probabilities and statistics.</p>
 *
 * @since 3.2
 */
public class EnumeratedRealDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut55359 = false, _mut55360 = false, _mut55361 = false, _mut55362 = false, _mut55363 = false, _mut55364 = false, _mut55365 = false, _mut55366 = false, _mut55367 = false, _mut55368 = false, _mut55369 = false, _mut55370 = false, _mut55371 = false, _mut55372 = false, _mut55373 = false, _mut55374 = false, _mut55375 = false, _mut55376 = false, _mut55377 = false, _mut55378 = false, _mut55379 = false, _mut55380 = false, _mut55381 = false, _mut55382 = false, _mut55383 = false, _mut55384 = false, _mut55385 = false, _mut55386 = false, _mut55387 = false, _mut55388 = false, _mut55389 = false, _mut55390 = false, _mut55391 = false, _mut55392 = false, _mut55393 = false, _mut55394 = false, _mut55395 = false, _mut55396 = false, _mut55397 = false, _mut55398 = false, _mut55399 = false, _mut55400 = false, _mut55401 = false, _mut55402 = false, _mut55403 = false, _mut55404 = false, _mut55405 = false, _mut55406 = false, _mut55407 = false, _mut55408 = false, _mut55409 = false, _mut55410 = false, _mut55411 = false, _mut55412 = false, _mut55413 = false, _mut55414 = false, _mut55415 = false, _mut55416 = false, _mut55417 = false, _mut55418 = false, _mut55419 = false, _mut55420 = false, _mut55421 = false, _mut55422 = false, _mut55423 = false, _mut55424 = false, _mut55425 = false, _mut55426 = false, _mut55427 = false, _mut55428 = false, _mut55429 = false, _mut55430 = false, _mut55431 = false, _mut55432 = false, _mut55433 = false, _mut55434 = false, _mut55435 = false, _mut55436 = false, _mut55437 = false, _mut55438 = false, _mut55439 = false, _mut55440 = false, _mut55441 = false, _mut55442 = false, _mut55443 = false, _mut55444 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20130308L;

    /**
     * {@link EnumeratedDistribution} (using the {@link Double} wrapper)
     * used to generate the pmf.
     */
    protected final EnumeratedDistribution<Double> innerDistribution;

    /**
     * Create a discrete real-valued distribution using the given probability mass function
     * enumeration.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param singletons array of random variable values.
     * @param probabilities array of probabilities.
     * @throws DimensionMismatchException if
     * {@code singletons.length != probabilities.length}
     * @throws NotPositiveException if any of the probabilities are negative.
     * @throws NotFiniteNumberException if any of the probabilities are infinite.
     * @throws NotANumberException if any of the probabilities are NaN.
     * @throws MathArithmeticException all of the probabilities are 0.
     */
    public EnumeratedRealDistribution(final double[] singletons, final double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), singletons, probabilities);
    }

    /**
     * Create a discrete real-valued distribution using the given random number generator
     * and probability mass function enumeration.
     *
     * @param rng random number generator.
     * @param singletons array of random variable values.
     * @param probabilities array of probabilities.
     * @throws DimensionMismatchException if
     * {@code singletons.length != probabilities.length}
     * @throws NotPositiveException if any of the probabilities are negative.
     * @throws NotFiniteNumberException if any of the probabilities are infinite.
     * @throws NotANumberException if any of the probabilities are NaN.
     * @throws MathArithmeticException all of the probabilities are 0.
     */
    public EnumeratedRealDistribution(final RandomGenerator rng, final double[] singletons, final double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(rng);
        innerDistribution = new EnumeratedDistribution<Double>(rng, createDistribution(singletons, probabilities));
    }

    /**
     * Create a discrete real-valued distribution from the input data.  Values are assigned
     * mass based on their frequency.
     *
     * @param rng random number generator used for sampling
     * @param data input dataset
     * @since 3.6
     */
    public EnumeratedRealDistribution(final RandomGenerator rng, final double[] data) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.EnumeratedRealDistribution_114");
        final Map<Double, Integer> dataMap = new HashMap<Double, Integer>();
        for (double value : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.EnumeratedRealDistribution_114");
            Integer count = dataMap.get(value);
            if (count == null) {
                count = 0;
            }
            dataMap.put(value, ++count);
        }
        final int massPoints = dataMap.size();
        final double denom = data.length;
        final double[] values = new double[massPoints];
        final double[] probabilities = new double[massPoints];
        int index = 0;
        for (Entry<Double, Integer> entry : dataMap.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.EnumeratedRealDistribution_114");
            values[index] = entry.getKey();
            probabilities[index] = AOR_divide(entry.getValue().intValue(), denom, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.EnumeratedRealDistribution_114", _mut55359, _mut55360, _mut55361, _mut55362);
            index++;
        }
        innerDistribution = new EnumeratedDistribution<Double>(rng, createDistribution(values, probabilities));
    }

    /**
     * Create a discrete real-valued distribution from the input data.  Values are assigned
     * mass based on their frequency.  For example, [0,1,1,2] as input creates a distribution
     * with values 0, 1 and 2 having probability masses 0.25, 0.5 and 0.25 respectively,
     *
     * @param data input dataset
     * @since 3.6
     */
    public EnumeratedRealDistribution(final double[] data) {
        this(new Well19937c(), data);
    }

    /**
     * Create the list of Pairs representing the distribution from singletons and probabilities.
     *
     * @param singletons values
     * @param probabilities probabilities
     * @return list of value/probability pairs
     */
    private static List<Pair<Double, Double>> createDistribution(double[] singletons, double[] probabilities) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.createDistribution_155");
        if (ROR_not_equals(singletons.length, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.createDistribution_155", _mut55363, _mut55364, _mut55365, _mut55366, _mut55367)) {
            throw new DimensionMismatchException(probabilities.length, singletons.length);
        }
        final List<Pair<Double, Double>> samples = new ArrayList<Pair<Double, Double>>(singletons.length);
        for (int i = 0; ROR_less(i, singletons.length, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.createDistribution_155", _mut55368, _mut55369, _mut55370, _mut55371, _mut55372); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.createDistribution_155");
            samples.add(new Pair<Double, Double>(singletons[i], probabilities[i]));
        }
        return samples;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double probability(final double x) {
        return innerDistribution.probability(x);
    }

    /**
     * For a random variable {@code X} whose values are distributed according to
     * this distribution, this method returns {@code P(X = x)}. In other words,
     * this method represents the probability mass function (PMF) for the
     * distribution.
     *
     * @param x the point at which the PMF is evaluated
     * @return the value of the probability mass function at point {@code x}
     */
    public double density(final double x) {
        return probability(x);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.cumulativeProbability_193");
        double probability = 0;
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.cumulativeProbability_193");
            if (ROR_less_equals(sample.getKey(), x, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.cumulativeProbability_193", _mut55373, _mut55374, _mut55375, _mut55376, _mut55377)) {
                probability += sample.getValue();
            }
        }
        return probability;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208");
        if ((_mut55388 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55378, _mut55379, _mut55380, _mut55381, _mut55382) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55383, _mut55384, _mut55385, _mut55386, _mut55387)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55378, _mut55379, _mut55380, _mut55381, _mut55382) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55383, _mut55384, _mut55385, _mut55386, _mut55387)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        double probability = 0;
        double x = getSupportLowerBound();
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208");
            if (ROR_equals(sample.getValue(), 0.0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55389, _mut55390, _mut55391, _mut55392, _mut55393)) {
                continue;
            }
            probability += sample.getValue();
            x = sample.getKey();
            if (ROR_greater_equals(probability, p, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.inverseCumulativeProbability_208", _mut55394, _mut55395, _mut55396, _mut55397, _mut55398)) {
                break;
            }
        }
        return x;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code sum(singletons[i] * probabilities[i])}
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalMean_237");
        double mean = 0;
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalMean_237");
            mean += AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalMean_237", _mut55399, _mut55400, _mut55401, _mut55402);
        }
        return mean;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code sum((singletons[i] - mean) ^ 2 * probabilities[i])}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252");
        double mean = 0;
        double meanOfSquares = 0;
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252");
            mean += AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252", _mut55403, _mut55404, _mut55405, _mut55406);
            meanOfSquares += AOR_multiply(AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252", _mut55407, _mut55408, _mut55409, _mut55410), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252", _mut55411, _mut55412, _mut55413, _mut55414);
        }
        return AOR_minus(meanOfSquares, AOR_multiply(mean, mean, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252", _mut55415, _mut55416, _mut55417, _mut55418), "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getNumericalVariance_252", _mut55419, _mut55420, _mut55421, _mut55422);
    }

    /**
     * {@inheritDoc}
     *
     * Returns the lowest value with non-zero probability.
     *
     * @return the lowest value with non-zero probability.
     */
    public double getSupportLowerBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271");
        double min = Double.POSITIVE_INFINITY;
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271");
            if ((_mut55433 ? (ROR_less(sample.getKey(), min, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271", _mut55423, _mut55424, _mut55425, _mut55426, _mut55427) || ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271", _mut55428, _mut55429, _mut55430, _mut55431, _mut55432)) : (ROR_less(sample.getKey(), min, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271", _mut55423, _mut55424, _mut55425, _mut55426, _mut55427) && ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportLowerBound_271", _mut55428, _mut55429, _mut55430, _mut55431, _mut55432)))) {
                min = sample.getKey();
            }
        }
        return min;
    }

    /**
     * {@inheritDoc}
     *
     * Returns the highest value with non-zero probability.
     *
     * @return the highest value with non-zero probability.
     */
    public double getSupportUpperBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289");
        double max = Double.NEGATIVE_INFINITY;
        for (final Pair<Double, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289");
            if ((_mut55444 ? (ROR_greater(sample.getKey(), max, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289", _mut55434, _mut55435, _mut55436, _mut55437, _mut55438) || ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289", _mut55439, _mut55440, _mut55441, _mut55442, _mut55443)) : (ROR_greater(sample.getKey(), max, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289", _mut55434, _mut55435, _mut55436, _mut55437, _mut55438) && ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedRealDistribution.getSupportUpperBound_289", _mut55439, _mut55440, _mut55441, _mut55442, _mut55443)))) {
                max = sample.getKey();
            }
        }
        return max;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution includes the lower bound.
     *
     * @return {@code true}
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution includes the upper bound.
     *
     * @return {@code true}
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
        return innerDistribution.sample();
    }
}
