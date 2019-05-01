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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>A generic implementation of a
 * <a href="http://en.wikipedia.org/wiki/Probability_distribution#Discrete_probability_distribution">
 * discrete probability distribution (Wikipedia)</a> over a finite sample space,
 * based on an enumerated list of &lt;value, probability&gt; pairs.  Input probabilities must all be non-negative,
 * but zero values are allowed and their sum does not have to equal one. Constructors will normalize input
 * probabilities to make them sum to one.</p>
 *
 * <p>The list of <value, probability> pairs does not, strictly speaking, have to be a function and it can
 * contain null values.  The pmf created by the constructor will combine probabilities of equal values and
 * will treat null values as equal.  For example, if the list of pairs &lt;"dog", 0.2&gt;, &lt;null, 0.1&gt;,
 * &lt;"pig", 0.2&gt;, &lt;"dog", 0.1&gt;, &lt;null, 0.4&gt; is provided to the constructor, the resulting
 * pmf will assign mass of 0.5 to null, 0.3 to "dog" and 0.2 to null.</p>
 *
 * @param <T> type of the elements in the sample space.
 * @since 3.2
 */
public class EnumeratedDistribution<T> implements Serializable {

    @Conditional
    public static boolean _mut56974 = false, _mut56975 = false, _mut56976 = false, _mut56977 = false, _mut56978 = false, _mut56979 = false, _mut56980 = false, _mut56981 = false, _mut56982 = false, _mut56983 = false, _mut56984 = false, _mut56985 = false, _mut56986 = false, _mut56987 = false, _mut56988 = false, _mut56989 = false, _mut56990 = false, _mut56991 = false, _mut56992 = false, _mut56993 = false, _mut56994 = false, _mut56995 = false, _mut56996 = false, _mut56997 = false, _mut56998 = false, _mut56999 = false, _mut57000 = false, _mut57001 = false, _mut57002 = false, _mut57003 = false, _mut57004 = false, _mut57005 = false, _mut57006 = false, _mut57007 = false, _mut57008 = false, _mut57009 = false, _mut57010 = false, _mut57011 = false, _mut57012 = false, _mut57013 = false, _mut57014 = false, _mut57015 = false, _mut57016 = false, _mut57017 = false, _mut57018 = false, _mut57019 = false, _mut57020 = false, _mut57021 = false, _mut57022 = false, _mut57023 = false, _mut57024 = false, _mut57025 = false, _mut57026 = false, _mut57027 = false, _mut57028 = false, _mut57029 = false, _mut57030 = false, _mut57031 = false, _mut57032 = false, _mut57033 = false, _mut57034 = false, _mut57035 = false, _mut57036 = false, _mut57037 = false, _mut57038 = false, _mut57039 = false, _mut57040 = false, _mut57041 = false, _mut57042 = false, _mut57043 = false, _mut57044 = false, _mut57045 = false, _mut57046 = false, _mut57047 = false, _mut57048 = false, _mut57049 = false, _mut57050 = false, _mut57051 = false, _mut57052 = false, _mut57053 = false, _mut57054 = false, _mut57055 = false, _mut57056 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20123308L;

    /**
     * RNG instance used to generate samples from the distribution.
     */
    protected final RandomGenerator random;

    /**
     * List of random variable values.
     */
    private final List<T> singletons;

    /**
     * Probabilities of respective random variable values. For i = 0, ..., singletons.size() - 1,
     * probability[i] is the probability that a random variable following this distribution takes
     * the value singletons[i].
     */
    private final double[] probabilities;

    /**
     * Cumulative probabilities, cached to speed up sampling.
     */
    private final double[] cumulativeProbabilities;

    /**
     * Create an enumerated distribution using the given probability mass function
     * enumeration.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param pmf probability mass function enumerated as a list of <T, probability>
     * pairs.
     * @throws NotPositiveException if any of the probabilities are negative.
     * @throws NotFiniteNumberException if any of the probabilities are infinite.
     * @throws NotANumberException if any of the probabilities are NaN.
     * @throws MathArithmeticException all of the probabilities are 0.
     */
    public EnumeratedDistribution(final List<Pair<T, Double>> pmf) throws NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), pmf);
    }

    /**
     * Create an enumerated distribution using the given random number generator
     * and probability mass function enumeration.
     *
     * @param rng random number generator.
     * @param pmf probability mass function enumerated as a list of <T, probability>
     * pairs.
     * @throws NotPositiveException if any of the probabilities are negative.
     * @throws NotFiniteNumberException if any of the probabilities are infinite.
     * @throws NotANumberException if any of the probabilities are NaN.
     * @throws MathArithmeticException all of the probabilities are 0.
     */
    public EnumeratedDistribution(final RandomGenerator rng, final List<Pair<T, Double>> pmf) throws NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116");
        random = rng;
        singletons = new ArrayList<T>(pmf.size());
        final double[] probs = new double[pmf.size()];
        for (int i = 0; ROR_less(i, pmf.size(), "org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116", _mut56979, _mut56980, _mut56981, _mut56982, _mut56983); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116");
            final Pair<T, Double> sample = pmf.get(i);
            singletons.add(sample.getKey());
            final double p = sample.getValue();
            if (ROR_less(p, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116", _mut56974, _mut56975, _mut56976, _mut56977, _mut56978)) {
                throw new NotPositiveException(sample.getValue());
            }
            if (Double.isInfinite(p)) {
                throw new NotFiniteNumberException(p);
            }
            if (Double.isNaN(p)) {
                throw new NotANumberException();
            }
            probs[i] = p;
        }
        probabilities = MathArrays.normalizeArray(probs, 1.0);
        cumulativeProbabilities = new double[probabilities.length];
        double sum = 0;
        for (int i = 0; ROR_less(i, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116", _mut56984, _mut56985, _mut56986, _mut56987, _mut56988); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.EnumeratedDistribution_116");
            sum += probabilities[i];
            cumulativeProbabilities[i] = sum;
        }
    }

    /**
     * Reseed the random generator used to generate samples.
     *
     * @param seed the new seed
     */
    public void reseedRandomGenerator(long seed) {
        random.setSeed(seed);
    }

    /**
     * <p>For a random variable {@code X} whose values are distributed according to
     * this distribution, this method returns {@code P(X = x)}. In other words,
     * this method represents the probability mass function (PMF) for the
     * distribution.</p>
     *
     * <p>Note that if {@code x1} and {@code x2} satisfy {@code x1.equals(x2)},
     * or both are null, then {@code probability(x1) = probability(x2)}.</p>
     *
     * @param x the point at which the PMF is evaluated
     * @return the value of the probability mass function at {@code x}
     */
    double probability(final T x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.probability_170");
        double probability = 0;
        for (int i = 0; ROR_less(i, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.probability_170", _mut56992, _mut56993, _mut56994, _mut56995, _mut56996); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.probability_170");
            if ((_mut56991 ? (((_mut56989 ? (x == null || singletons.get(i) == null) : (x == null && singletons.get(i) == null))) && ((_mut56990 ? (x != null || x.equals(singletons.get(i))) : (x != null && x.equals(singletons.get(i)))))) : (((_mut56989 ? (x == null || singletons.get(i) == null) : (x == null && singletons.get(i) == null))) || ((_mut56990 ? (x != null || x.equals(singletons.get(i))) : (x != null && x.equals(singletons.get(i)))))))) {
                probability += probabilities[i];
            }
        }
        return probability;
    }

    /**
     * <p>Return the probability mass function as a list of <value, probability> pairs.</p>
     *
     * <p>Note that if duplicate and / or null values were provided to the constructor
     * when creating this EnumeratedDistribution, the returned list will contain these
     * values.  If duplicates values exist, what is returned will not represent
     * a pmf (i.e., it is up to the caller to consolidate duplicate mass points).</p>
     *
     * @return the probability mass function.
     */
    public List<Pair<T, Double>> getPmf() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.getPmf_193");
        final List<Pair<T, Double>> samples = new ArrayList<Pair<T, Double>>(probabilities.length);
        for (int i = 0; ROR_less(i, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.getPmf_193", _mut56997, _mut56998, _mut56999, _mut57000, _mut57001); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.getPmf_193");
            samples.add(new Pair<T, Double>(singletons.get(i), probabilities[i]));
        }
        return samples;
    }

    /**
     * Generate a random value sampled from this distribution.
     *
     * @return a random value.
     */
    public T sample() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208");
        final double randomValue = random.nextDouble();
        int index = Arrays.binarySearch(cumulativeProbabilities, randomValue);
        if (ROR_less(index, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57002, _mut57003, _mut57004, _mut57005, _mut57006)) {
            index = AOR_minus(-index, 1, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57007, _mut57008, _mut57009, _mut57010);
        }
        if ((_mut57027 ? ((_mut57021 ? (ROR_greater_equals(index, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57011, _mut57012, _mut57013, _mut57014, _mut57015) || ROR_less(index, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57016, _mut57017, _mut57018, _mut57019, _mut57020)) : (ROR_greater_equals(index, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57011, _mut57012, _mut57013, _mut57014, _mut57015) && ROR_less(index, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57016, _mut57017, _mut57018, _mut57019, _mut57020))) || ROR_less(randomValue, cumulativeProbabilities[index], "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57022, _mut57023, _mut57024, _mut57025, _mut57026)) : ((_mut57021 ? (ROR_greater_equals(index, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57011, _mut57012, _mut57013, _mut57014, _mut57015) || ROR_less(index, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57016, _mut57017, _mut57018, _mut57019, _mut57020)) : (ROR_greater_equals(index, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57011, _mut57012, _mut57013, _mut57014, _mut57015) && ROR_less(index, probabilities.length, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57016, _mut57017, _mut57018, _mut57019, _mut57020))) && ROR_less(randomValue, cumulativeProbabilities[index], "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57022, _mut57023, _mut57024, _mut57025, _mut57026)))) {
            return singletons.get(index);
        }
        /* This should never happen, but it ensures we will return a correct
         * object in case there is some floating point inequality problem
         * wrt the cumulative probabilities. */
        return singletons.get(AOR_minus(singletons.size(), 1, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_208", _mut57028, _mut57029, _mut57030, _mut57031));
    }

    /**
     * Generate a random sample from the distribution.
     *
     * @param sampleSize the number of random values to generate.
     * @return an array representing the random sample.
     * @throws NotStrictlyPositiveException if {@code sampleSize} is not
     * positive.
     */
    public Object[] sample(int sampleSize) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.sample_236");
        if (ROR_less_equals(sampleSize, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_236", _mut57032, _mut57033, _mut57034, _mut57035, _mut57036)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        final Object[] out = new Object[sampleSize];
        for (int i = 0; ROR_less(i, sampleSize, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_236", _mut57037, _mut57038, _mut57039, _mut57040, _mut57041); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.sample_236");
            out[i] = sample();
        }
        return out;
    }

    /**
     * Generate a random sample from the distribution.
     * <p>
     * If the requested samples fit in the specified array, it is returned
     * therein. Otherwise, a new array is allocated with the runtime type of
     * the specified array and the size of this collection.
     *
     * @param sampleSize the number of random values to generate.
     * @param array the array to populate.
     * @return an array representing the random sample.
     * @throws NotStrictlyPositiveException if {@code sampleSize} is not positive.
     * @throws NullArgumentException if {@code array} is null
     */
    public T[] sample(int sampleSize, final T[] array) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.sample_265");
        if (ROR_less_equals(sampleSize, 0, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_265", _mut57042, _mut57043, _mut57044, _mut57045, _mut57046)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        if (array == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        T[] out;
        if (ROR_less(array.length, sampleSize, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_265", _mut57047, _mut57048, _mut57049, _mut57050, _mut57051)) {
            // safe as both are of type T
            @SuppressWarnings("unchecked")
            final T[] unchecked = (T[]) Array.newInstance(array.getClass().getComponentType(), sampleSize);
            out = unchecked;
        } else {
            out = array;
        }
        for (int i = 0; ROR_less(i, sampleSize, "org.apache.commons.math3.distribution.EnumeratedDistribution.sample_265", _mut57052, _mut57053, _mut57054, _mut57055, _mut57056); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.EnumeratedDistribution.sample_265");
            out[i] = sample();
        }
        return out;
    }
}
