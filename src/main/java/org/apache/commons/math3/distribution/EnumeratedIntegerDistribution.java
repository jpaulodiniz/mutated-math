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
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Implementation of an integer-valued {@link EnumeratedDistribution}.</p>
 *
 * <p>Values with zero-probability are allowed but they do not extend the
 * support.<br/>
 * Duplicate values are allowed. Probabilities of duplicate values are combined
 * when computing cumulative probabilities and statistics.</p>
 *
 * @since 3.2
 */
public class EnumeratedIntegerDistribution extends AbstractIntegerDistribution {

    @Conditional
    public static boolean _mut55179 = false, _mut55180 = false, _mut55181 = false, _mut55182 = false, _mut55183 = false, _mut55184 = false, _mut55185 = false, _mut55186 = false, _mut55187 = false, _mut55188 = false, _mut55189 = false, _mut55190 = false, _mut55191 = false, _mut55192 = false, _mut55193 = false, _mut55194 = false, _mut55195 = false, _mut55196 = false, _mut55197 = false, _mut55198 = false, _mut55199 = false, _mut55200 = false, _mut55201 = false, _mut55202 = false, _mut55203 = false, _mut55204 = false, _mut55205 = false, _mut55206 = false, _mut55207 = false, _mut55208 = false, _mut55209 = false, _mut55210 = false, _mut55211 = false, _mut55212 = false, _mut55213 = false, _mut55214 = false, _mut55215 = false, _mut55216 = false, _mut55217 = false, _mut55218 = false, _mut55219 = false, _mut55220 = false, _mut55221 = false, _mut55222 = false, _mut55223 = false, _mut55224 = false, _mut55225 = false, _mut55226 = false, _mut55227 = false, _mut55228 = false, _mut55229 = false, _mut55230 = false, _mut55231 = false, _mut55232 = false, _mut55233 = false, _mut55234 = false, _mut55235 = false, _mut55236 = false, _mut55237 = false, _mut55238 = false, _mut55239 = false, _mut55240 = false, _mut55241 = false, _mut55242 = false, _mut55243 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20130308L;

    /**
     * {@link EnumeratedDistribution} instance (using the {@link Integer} wrapper)
     * used to generate the pmf.
     */
    protected final EnumeratedDistribution<Integer> innerDistribution;

    /**
     * Create a discrete distribution using the given probability mass function
     * definition.
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
    public EnumeratedIntegerDistribution(final int[] singletons, final double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), singletons, probabilities);
    }

    /**
     * Create a discrete distribution using the given random number generator
     * and probability mass function definition.
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
    public EnumeratedIntegerDistribution(final RandomGenerator rng, final int[] singletons, final double[] probabilities) throws DimensionMismatchException, NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        super(rng);
        innerDistribution = new EnumeratedDistribution<Integer>(rng, createDistribution(singletons, probabilities));
    }

    /**
     * Create a discrete integer-valued distribution from the input data.  Values are assigned
     * mass based on their frequency.
     *
     * @param rng random number generator used for sampling
     * @param data input dataset
     * @since 3.6
     */
    public EnumeratedIntegerDistribution(final RandomGenerator rng, final int[] data) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.EnumeratedIntegerDistribution_112");
        final Map<Integer, Integer> dataMap = new HashMap<Integer, Integer>();
        for (int value : data) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.EnumeratedIntegerDistribution_112");
            Integer count = dataMap.get(value);
            if (count == null) {
                count = 0;
            }
            dataMap.put(value, ++count);
        }
        final int massPoints = dataMap.size();
        final double denom = data.length;
        final int[] values = new int[massPoints];
        final double[] probabilities = new double[massPoints];
        int index = 0;
        for (Entry<Integer, Integer> entry : dataMap.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.EnumeratedIntegerDistribution_112");
            values[index] = entry.getKey();
            probabilities[index] = AOR_divide(entry.getValue().intValue(), denom, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.EnumeratedIntegerDistribution_112", _mut55179, _mut55180, _mut55181, _mut55182);
            index++;
        }
        innerDistribution = new EnumeratedDistribution<Integer>(rng, createDistribution(values, probabilities));
    }

    /**
     * Create a discrete integer-valued distribution from the input data.  Values are assigned
     * mass based on their frequency.  For example, [0,1,1,2] as input creates a distribution
     * with values 0, 1 and 2 having probability masses 0.25, 0.5 and 0.25 respectively,
     *
     * @param data input dataset
     * @since 3.6
     */
    public EnumeratedIntegerDistribution(final int[] data) {
        this(new Well19937c(), data);
    }

    /**
     * Create the list of Pairs representing the distribution from singletons and probabilities.
     *
     * @param singletons values
     * @param probabilities probabilities
     * @return list of value/probability pairs
     */
    private static List<Pair<Integer, Double>> createDistribution(int[] singletons, double[] probabilities) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.createDistribution_154");
        if (ROR_not_equals(singletons.length, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.createDistribution_154", _mut55183, _mut55184, _mut55185, _mut55186, _mut55187)) {
            throw new DimensionMismatchException(probabilities.length, singletons.length);
        }
        final List<Pair<Integer, Double>> samples = new ArrayList<Pair<Integer, Double>>(singletons.length);
        for (int i = 0; ROR_less(i, singletons.length, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.createDistribution_154", _mut55188, _mut55189, _mut55190, _mut55191, _mut55192); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.createDistribution_154");
            samples.add(new Pair<Integer, Double>(singletons[i], probabilities[i]));
        }
        return samples;
    }

    /**
     * {@inheritDoc}
     */
    public double probability(final int x) {
        return innerDistribution.probability(x);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.cumulativeProbability_178");
        double probability = 0;
        for (final Pair<Integer, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.cumulativeProbability_178");
            if (ROR_less_equals(sample.getKey(), x, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.cumulativeProbability_178", _mut55193, _mut55194, _mut55195, _mut55196, _mut55197)) {
                probability += sample.getValue();
            }
        }
        return probability;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code sum(singletons[i] * probabilities[i])}
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalMean_195");
        double mean = 0;
        for (final Pair<Integer, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalMean_195");
            mean += AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalMean_195", _mut55198, _mut55199, _mut55200, _mut55201);
        }
        return mean;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code sum((singletons[i] - mean) ^ 2 * probabilities[i])}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210");
        double mean = 0;
        double meanOfSquares = 0;
        for (final Pair<Integer, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210");
            mean += AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210", _mut55202, _mut55203, _mut55204, _mut55205);
            meanOfSquares += AOR_multiply(AOR_multiply(sample.getValue(), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210", _mut55206, _mut55207, _mut55208, _mut55209), sample.getKey(), "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210", _mut55210, _mut55211, _mut55212, _mut55213);
        }
        return AOR_minus(meanOfSquares, AOR_multiply(mean, mean, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210", _mut55214, _mut55215, _mut55216, _mut55217), "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getNumericalVariance_210", _mut55218, _mut55219, _mut55220, _mut55221);
    }

    /**
     * {@inheritDoc}
     *
     * Returns the lowest value with non-zero probability.
     *
     * @return the lowest value with non-zero probability.
     */
    public int getSupportLowerBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229");
        int min = Integer.MAX_VALUE;
        for (final Pair<Integer, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229");
            if ((_mut55232 ? (ROR_less(sample.getKey(), min, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229", _mut55222, _mut55223, _mut55224, _mut55225, _mut55226) || ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229", _mut55227, _mut55228, _mut55229, _mut55230, _mut55231)) : (ROR_less(sample.getKey(), min, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229", _mut55222, _mut55223, _mut55224, _mut55225, _mut55226) && ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportLowerBound_229", _mut55227, _mut55228, _mut55229, _mut55230, _mut55231)))) {
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
    public int getSupportUpperBound() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247");
        int max = Integer.MIN_VALUE;
        for (final Pair<Integer, Double> sample : innerDistribution.getPmf()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247");
            if ((_mut55243 ? (ROR_greater(sample.getKey(), max, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247", _mut55233, _mut55234, _mut55235, _mut55236, _mut55237) || ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247", _mut55238, _mut55239, _mut55240, _mut55241, _mut55242)) : (ROR_greater(sample.getKey(), max, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247", _mut55233, _mut55234, _mut55235, _mut55236, _mut55237) && ROR_greater(sample.getValue(), 0, "org.apache.commons.math3.distribution.EnumeratedIntegerDistribution.getSupportUpperBound_247", _mut55238, _mut55239, _mut55240, _mut55241, _mut55242)))) {
                max = sample.getKey();
            }
        }
        return max;
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
    public int sample() {
        return innerDistribution.sample();
    }
}
