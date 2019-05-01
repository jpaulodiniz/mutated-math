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
package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Random Key chromosome is used for permutation representation. It is a vector
 * of a fixed length of real numbers in [0,1] interval. The index of the i-th
 * smallest value in the vector represents an i-th member of the permutation.
 * <p>
 * For example, the random key [0.2, 0.3, 0.8, 0.1] corresponds to the
 * permutation of indices (3,0,1,2). If the original (unpermuted) sequence would
 * be (a,b,c,d), this would mean the sequence (d,a,b,c).
 * <p>
 * With this representation, common operators like n-point crossover can be
 * used, because any such chromosome represents a valid permutation.
 * <p>
 * Since the chromosome (and thus its arrayRepresentation) is immutable, the
 * array representation is sorted only once in the constructor.
 * <p>
 * For details, see:
 * <ul>
 *   <li>Bean, J.C.: Genetic algorithms and random keys for sequencing and
 *       optimization. ORSA Journal on Computing 6 (1994) 154-160</li>
 *   <li>Rothlauf, F.: Representations for Genetic and Evolutionary Algorithms.
 *       Volume 104 of Studies in Fuzziness and Soft Computing. Physica-Verlag,
 *       Heidelberg (2002)</li>
 * </ul>
 *
 * @param <T> type of the permuted objects
 * @since 2.0
 */
public abstract class RandomKey<T> extends AbstractListChromosome<Double> implements PermutationChromosome<T> {

    @Conditional
    public static boolean _mut2241 = false, _mut2242 = false, _mut2243 = false, _mut2244 = false, _mut2245 = false, _mut2246 = false, _mut2247 = false, _mut2248 = false, _mut2249 = false, _mut2250 = false, _mut2251 = false, _mut2252 = false, _mut2253 = false, _mut2254 = false, _mut2255 = false, _mut2256 = false, _mut2257 = false, _mut2258 = false, _mut2259 = false, _mut2260 = false, _mut2261 = false, _mut2262 = false, _mut2263 = false, _mut2264 = false, _mut2265 = false, _mut2266 = false, _mut2267 = false, _mut2268 = false, _mut2269 = false, _mut2270 = false, _mut2271 = false, _mut2272 = false, _mut2273 = false, _mut2274 = false, _mut2275 = false, _mut2276 = false, _mut2277 = false, _mut2278 = false, _mut2279 = false, _mut2280 = false, _mut2281 = false, _mut2282 = false, _mut2283 = false, _mut2284 = false, _mut2285 = false, _mut2286 = false, _mut2287 = false, _mut2288 = false, _mut2289 = false, _mut2290 = false, _mut2291 = false, _mut2292 = false, _mut2293 = false, _mut2294 = false, _mut2295 = false, _mut2296 = false, _mut2297 = false, _mut2298 = false, _mut2299 = false, _mut2300 = false, _mut2301 = false, _mut2302 = false, _mut2303 = false, _mut2304 = false, _mut2305 = false, _mut2306 = false, _mut2307 = false, _mut2308 = false, _mut2309 = false, _mut2310 = false, _mut2311 = false, _mut2312 = false, _mut2313 = false, _mut2314 = false, _mut2315 = false, _mut2316 = false, _mut2317 = false, _mut2318 = false, _mut2319 = false;

    /**
     * Cache of sorted representation (unmodifiable).
     */
    private final List<Double> sortedRepresentation;

    /**
     * Base sequence [0,1,...,n-1], permuted according to the representation (unmodifiable).
     */
    private final List<Integer> baseSeqPermutation;

    /**
     * Constructor.
     *
     * @param representation list of [0,1] values representing the permutation
     * @throws InvalidRepresentationException iff the <code>representation</code> can not represent a valid chromosome
     */
    public RandomKey(final List<Double> representation) throws InvalidRepresentationException {
        super(representation);
        // store the sorted representation
        List<Double> sortedRepr = new ArrayList<Double>(getRepresentation());
        Collections.sort(sortedRepr);
        sortedRepresentation = Collections.unmodifiableList(sortedRepr);
        // store the permutation of [0,1,...,n-1] list for toString() and isSame() methods
        baseSeqPermutation = Collections.unmodifiableList(decodeGeneric(baseSequence(getLength()), getRepresentation(), sortedRepresentation));
    }

    /**
     * Constructor.
     *
     * @param representation array of [0,1] values representing the permutation
     * @throws InvalidRepresentationException iff the <code>representation</code> can not represent a valid chromosome
     */
    public RandomKey(final Double[] representation) throws InvalidRepresentationException {
        this(Arrays.asList(representation));
    }

    /**
     * {@inheritDoc}
     */
    public List<T> decode(final List<T> sequence) {
        return decodeGeneric(sequence, getRepresentation(), sortedRepresentation);
    }

    /**
     * Decodes a permutation represented by <code>representation</code> and
     * returns a (generic) list with the permuted values.
     *
     * @param <S> generic type of the sequence values
     * @param sequence the unpermuted sequence
     * @param representation representation of the permutation ([0,1] vector)
     * @param sortedRepr sorted <code>representation</code>
     * @return list with the sequence values permuted according to the representation
     * @throws DimensionMismatchException iff the length of the <code>sequence</code>,
     *   <code>representation</code> or <code>sortedRepr</code> lists are not equal
     */
    private static <S> List<S> decodeGeneric(final List<S> sequence, List<Double> representation, final List<Double> sortedRepr) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.decodeGeneric_113");
        int l = sequence.size();
        // the size of the three lists must be equal
        if (ROR_not_equals(representation.size(), l, "org.apache.commons.math3.genetics.RandomKey.decodeGeneric_113", _mut2241, _mut2242, _mut2243, _mut2244, _mut2245)) {
            throw new DimensionMismatchException(representation.size(), l);
        }
        if (ROR_not_equals(sortedRepr.size(), l, "org.apache.commons.math3.genetics.RandomKey.decodeGeneric_113", _mut2246, _mut2247, _mut2248, _mut2249, _mut2250)) {
            throw new DimensionMismatchException(sortedRepr.size(), l);
        }
        // do not modify the original representation
        List<Double> reprCopy = new ArrayList<Double>(representation);
        // now find the indices in the original repr and use them for permuting
        List<S> res = new ArrayList<S>(l);
        for (int i = 0; ROR_less(i, l, "org.apache.commons.math3.genetics.RandomKey.decodeGeneric_113", _mut2251, _mut2252, _mut2253, _mut2254, _mut2255); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.decodeGeneric_113");
            int index = reprCopy.indexOf(sortedRepr.get(i));
            res.add(sequence.get(index));
            reprCopy.set(index, null);
        }
        return res;
    }

    /**
     * Returns <code>true</code> iff <code>another</code> is a RandomKey and
     * encodes the same permutation.
     *
     * @param another chromosome to compare
     * @return true iff chromosomes encode the same permutation
     */
    @Override
    protected boolean isSame(final Chromosome another) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.isSame_147");
        // type check
        if (!(another instanceof RandomKey<?>)) {
            return false;
        }
        RandomKey<?> anotherRk = (RandomKey<?>) another;
        // size check
        if (ROR_not_equals(getLength(), anotherRk.getLength(), "org.apache.commons.math3.genetics.RandomKey.isSame_147", _mut2256, _mut2257, _mut2258, _mut2259, _mut2260)) {
            return false;
        }
        // the ordering is what counts
        List<Integer> thisPerm = this.baseSeqPermutation;
        List<Integer> anotherPerm = anotherRk.baseSeqPermutation;
        for (int i = 0; ROR_less(i, getLength(), "org.apache.commons.math3.genetics.RandomKey.isSame_147", _mut2266, _mut2267, _mut2268, _mut2269, _mut2270); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.isSame_147");
            if (ROR_not_equals(thisPerm.get(i), anotherPerm.get(i), "org.apache.commons.math3.genetics.RandomKey.isSame_147", _mut2261, _mut2262, _mut2263, _mut2264, _mut2265)) {
                return false;
            }
        }
        // the permutations are the same
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void checkValidity(final List<Double> chromosomeRepresentation) throws InvalidRepresentationException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.checkValidity_176");
        for (double val : chromosomeRepresentation) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.checkValidity_176");
            if ((_mut2281 ? (ROR_less(val, 0, "org.apache.commons.math3.genetics.RandomKey.checkValidity_176", _mut2271, _mut2272, _mut2273, _mut2274, _mut2275) && ROR_greater(val, 1, "org.apache.commons.math3.genetics.RandomKey.checkValidity_176", _mut2276, _mut2277, _mut2278, _mut2279, _mut2280)) : (ROR_less(val, 0, "org.apache.commons.math3.genetics.RandomKey.checkValidity_176", _mut2271, _mut2272, _mut2273, _mut2274, _mut2275) || ROR_greater(val, 1, "org.apache.commons.math3.genetics.RandomKey.checkValidity_176", _mut2276, _mut2277, _mut2278, _mut2279, _mut2280)))) {
                throw new InvalidRepresentationException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, val, 0, 1);
            }
        }
    }

    /**
     * Generates a representation corresponding to a random permutation of
     * length l which can be passed to the RandomKey constructor.
     *
     * @param l length of the permutation
     * @return representation of a random permutation
     */
    public static final List<Double> randomPermutation(final int l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.randomPermutation_196");
        List<Double> repr = new ArrayList<Double>(l);
        for (int i = 0; ROR_less(i, l, "org.apache.commons.math3.genetics.RandomKey.randomPermutation_196", _mut2282, _mut2283, _mut2284, _mut2285, _mut2286); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.randomPermutation_196");
            repr.add(GeneticAlgorithm.getRandomGenerator().nextDouble());
        }
        return repr;
    }

    /**
     * Generates a representation corresponding to an identity permutation of
     * length l which can be passed to the RandomKey constructor.
     *
     * @param l length of the permutation
     * @return representation of an identity permutation
     */
    public static final List<Double> identityPermutation(final int l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.identityPermutation_211");
        List<Double> repr = new ArrayList<Double>(l);
        for (int i = 0; ROR_less(i, l, "org.apache.commons.math3.genetics.RandomKey.identityPermutation_211", _mut2291, _mut2292, _mut2293, _mut2294, _mut2295); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.identityPermutation_211");
            repr.add(AOR_divide((double) i, l, "org.apache.commons.math3.genetics.RandomKey.identityPermutation_211", _mut2287, _mut2288, _mut2289, _mut2290));
        }
        return repr;
    }

    /**
     * Generates a representation of a permutation corresponding to the
     * <code>data</code> sorted by <code>comparator</code>. The
     * <code>data</code> is not modified during the process.
     *
     * This is useful if you want to inject some permutations to the initial
     * population.
     *
     * @param <S> type of the data
     * @param data list of data determining the order
     * @param comparator how the data will be compared
     * @return list representation of the permutation corresponding to the parameters
     */
    public static <S> List<Double> comparatorPermutation(final List<S> data, final Comparator<S> comparator) {
        List<S> sortedData = new ArrayList<S>(data);
        Collections.sort(sortedData, comparator);
        return inducedPermutation(data, sortedData);
    }

    /**
     * Generates a representation of a permutation corresponding to a
     * permutation which yields <code>permutedData</code> when applied to
     * <code>originalData</code>.
     *
     * This method can be viewed as an inverse to {@link #decode(List)}.
     *
     * @param <S> type of the data
     * @param originalData the original, unpermuted data
     * @param permutedData the data, somehow permuted
     * @return representation of a permutation corresponding to the permutation
     *   <code>originalData -> permutedData</code>
     * @throws DimensionMismatchException iff the length of <code>originalData</code>
     *   and <code>permutedData</code> lists are not equal
     * @throws MathIllegalArgumentException iff the <code>permutedData</code> and
     *   <code>originalData</code> lists contain different data
     */
    public static <S> List<Double> inducedPermutation(final List<S> originalData, final List<S> permutedData) throws DimensionMismatchException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257");
        if (ROR_not_equals(originalData.size(), permutedData.size(), "org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257", _mut2296, _mut2297, _mut2298, _mut2299, _mut2300)) {
            throw new DimensionMismatchException(permutedData.size(), originalData.size());
        }
        int l = originalData.size();
        List<S> origDataCopy = new ArrayList<S>(originalData);
        Double[] res = new Double[l];
        for (int i = 0; ROR_less(i, l, "org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257", _mut2310, _mut2311, _mut2312, _mut2313, _mut2314); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257");
            int index = origDataCopy.indexOf(permutedData.get(i));
            if (ROR_equals(index, -1, "org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257", _mut2301, _mut2302, _mut2303, _mut2304, _mut2305)) {
                throw new MathIllegalArgumentException(LocalizedFormats.DIFFERENT_ORIG_AND_PERMUTED_DATA);
            }
            res[index] = AOR_divide((double) i, l, "org.apache.commons.math3.genetics.RandomKey.inducedPermutation_257", _mut2306, _mut2307, _mut2308, _mut2309);
            origDataCopy.set(index, null);
        }
        return Arrays.asList(res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("(f=%s pi=(%s))", getFitness(), baseSeqPermutation);
    }

    /**
     * Helper for constructor. Generates a list of natural numbers (0,1,...,l-1).
     *
     * @param l length of list to generate
     * @return list of integers from 0 to l-1
     */
    private static List<Integer> baseSequence(final int l) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.baseSequence_292");
        List<Integer> baseSequence = new ArrayList<Integer>(l);
        for (int i = 0; ROR_less(i, l, "org.apache.commons.math3.genetics.RandomKey.baseSequence_292", _mut2315, _mut2316, _mut2317, _mut2318, _mut2319); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.RandomKey.baseSequence_292");
            baseSequence.add(i);
        }
        return baseSequence;
    }
}
