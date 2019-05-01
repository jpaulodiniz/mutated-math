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
package org.apache.commons.math3.util;

import java.util.Iterator;
import java.util.Comparator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.Serializable;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utility to create <a href="http://en.wikipedia.org/wiki/Combination">
 * combinations</a> {@code (n, k)} of {@code k} elements in a set of
 * {@code n} elements.
 *
 * @since 3.3
 */
public class Combinations implements Iterable<int[]> {

    @Conditional
    public static boolean _mut50168 = false, _mut50169 = false, _mut50170 = false, _mut50171 = false, _mut50172 = false, _mut50173 = false, _mut50174 = false, _mut50175 = false, _mut50176 = false, _mut50177 = false, _mut50178 = false, _mut50179 = false, _mut50180 = false, _mut50181 = false, _mut50182 = false, _mut50183 = false, _mut50184 = false, _mut50185 = false, _mut50186 = false, _mut50187 = false, _mut50188 = false, _mut50189 = false, _mut50190 = false, _mut50191 = false, _mut50192 = false, _mut50193 = false, _mut50194 = false, _mut50195 = false, _mut50196 = false, _mut50197 = false, _mut50198 = false, _mut50199 = false, _mut50200 = false, _mut50201 = false, _mut50202 = false, _mut50203 = false, _mut50204 = false, _mut50205 = false, _mut50206 = false, _mut50207 = false, _mut50208 = false, _mut50209 = false, _mut50210 = false, _mut50211 = false, _mut50212 = false, _mut50213 = false, _mut50214 = false, _mut50215 = false, _mut50216 = false, _mut50217 = false, _mut50218 = false, _mut50219 = false, _mut50220 = false, _mut50221 = false, _mut50222 = false, _mut50223 = false, _mut50224 = false, _mut50225 = false, _mut50226 = false, _mut50227 = false, _mut50228 = false, _mut50229 = false, _mut50230 = false, _mut50231 = false, _mut50232 = false, _mut50233 = false, _mut50234 = false, _mut50235 = false, _mut50236 = false, _mut50237 = false, _mut50238 = false, _mut50239 = false, _mut50240 = false, _mut50241 = false, _mut50242 = false, _mut50243 = false, _mut50244 = false, _mut50245 = false, _mut50246 = false, _mut50247 = false, _mut50248 = false, _mut50249 = false, _mut50250 = false, _mut50251 = false, _mut50252 = false, _mut50253 = false, _mut50254 = false, _mut50255 = false, _mut50256 = false, _mut50257 = false, _mut50258 = false, _mut50259 = false, _mut50260 = false, _mut50261 = false, _mut50262 = false, _mut50263 = false, _mut50264 = false, _mut50265 = false, _mut50266 = false, _mut50267 = false, _mut50268 = false, _mut50269 = false, _mut50270 = false, _mut50271 = false, _mut50272 = false, _mut50273 = false, _mut50274 = false, _mut50275 = false, _mut50276 = false, _mut50277 = false, _mut50278 = false, _mut50279 = false, _mut50280 = false, _mut50281 = false, _mut50282 = false, _mut50283 = false, _mut50284 = false, _mut50285 = false, _mut50286 = false, _mut50287 = false, _mut50288 = false, _mut50289 = false, _mut50290 = false, _mut50291 = false, _mut50292 = false, _mut50293 = false, _mut50294 = false;

    /**
     * Size of the set from which combinations are drawn.
     */
    private final int n;

    /**
     * Number of elements in each combination.
     */
    private final int k;

    /**
     * Iteration order.
     */
    private final IterationOrder iterationOrder;

    /**
     * Describes the type of iteration performed by the
     * {@link #iterator() iterator}.
     */
    private enum IterationOrder {

        /**
         * Lexicographic order.
         */
        LEXICOGRAPHIC
    }

    /**
     * Creates an instance whose range is the k-element subsets of
     * {0, ..., n - 1} represented as {@code int[]} arrays.
     * <p>
     * The iteration order is lexicographic: the arrays returned by the
     * {@link #iterator() iterator} are sorted in descending order and
     * they are visited in lexicographic order with significance from
     * right to left.
     * For example, {@code new Combinations(4, 2).iterator()} returns
     * an iterator that will generate the following sequence of arrays
     * on successive calls to
     * {@code next()}:<br/>
     * {@code [0, 1], [0, 2], [1, 2], [0, 3], [1, 3], [2, 3]}
     * </p>
     * If {@code k == 0} an iterator containing an empty array is returned;
     * if {@code k == n} an iterator containing [0, ..., n - 1] is returned.
     *
     * @param n Size of the set from which subsets are selected.
     * @param k Size of the subsets to be enumerated.
     * @throws org.apache.commons.math3.exception.NotPositiveException if {@code n < 0}.
     * @throws org.apache.commons.math3.exception.NumberIsTooLargeException if {@code k > n}.
     */
    public Combinations(int n, int k) {
        this(n, k, IterationOrder.LEXICOGRAPHIC);
    }

    /**
     * Creates an instance whose range is the k-element subsets of
     * {0, ..., n - 1} represented as {@code int[]} arrays.
     * <p>
     * If the {@code iterationOrder} argument is set to
     * {@link IterationOrder#LEXICOGRAPHIC}, the arrays returned by the
     * {@link #iterator() iterator} are sorted in descending order and
     * they are visited in lexicographic order with significance from
     * right to left.
     * For example, {@code new Combinations(4, 2).iterator()} returns
     * an iterator that will generate the following sequence of arrays
     * on successive calls to
     * {@code next()}:<br/>
     * {@code [0, 1], [0, 2], [1, 2], [0, 3], [1, 3], [2, 3]}
     * </p>
     * If {@code k == 0} an iterator containing an empty array is returned;
     * if {@code k == n} an iterator containing [0, ..., n - 1] is returned.
     *
     * @param n Size of the set from which subsets are selected.
     * @param k Size of the subsets to be enumerated.
     * @param iterationOrder Specifies the {@link #iterator() iteration order}.
     * @throws org.apache.commons.math3.exception.NotPositiveException if {@code n < 0}.
     * @throws org.apache.commons.math3.exception.NumberIsTooLargeException if {@code k > n}.
     */
    private Combinations(int n, int k, IterationOrder iterationOrder) {
        CombinatoricsUtils.checkBinomial(n, k);
        this.n = n;
        this.k = k;
        this.iterationOrder = iterationOrder;
    }

    /**
     * Gets the size of the set from which combinations are drawn.
     *
     * @return the size of the universe.
     */
    public int getN() {
        return n;
    }

    /**
     * Gets the number of elements in each combination.
     *
     * @return the size of the subsets to be enumerated.
     */
    public int getK() {
        return k;
    }

    /**
     * {@inheritDoc}
     */
    public Iterator<int[]> iterator() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.iterator_131");
        if ((_mut50178 ? (ROR_equals(k, 0, "org.apache.commons.math3.util.Combinations.iterator_131", _mut50168, _mut50169, _mut50170, _mut50171, _mut50172) && ROR_equals(k, n, "org.apache.commons.math3.util.Combinations.iterator_131", _mut50173, _mut50174, _mut50175, _mut50176, _mut50177)) : (ROR_equals(k, 0, "org.apache.commons.math3.util.Combinations.iterator_131", _mut50168, _mut50169, _mut50170, _mut50171, _mut50172) || ROR_equals(k, n, "org.apache.commons.math3.util.Combinations.iterator_131", _mut50173, _mut50174, _mut50175, _mut50176, _mut50177)))) {
            return new SingletonIterator(MathArrays.natural(k));
        }
        switch(iterationOrder) {
            case LEXICOGRAPHIC:
                return new LexicographicIterator(n, k);
            default:
                // Should never happen.
                throw new MathInternalError();
        }
    }

    /**
     * Defines a lexicographic ordering of combinations.
     * The returned comparator allows to compare any two combinations
     * that can be produced by this instance's {@link #iterator() iterator}.
     * Its {@code compare(int[],int[])} method will throw exceptions if
     * passed combinations that are inconsistent with this instance:
     * <ul>
     *  <li>{@code DimensionMismatchException} if the array lengths are not
     *      equal to {@code k},</li>
     *  <li>{@code OutOfRangeException} if an element of the array is not
     *      within the interval [0, {@code n}).</li>
     * </ul>
     * @return a lexicographic comparator.
     */
    public Comparator<int[]> comparator() {
        return new LexicographicComparator(n, k);
    }

    /**
     * Lexicographic combinations iterator.
     * <p>
     * Implementation follows Algorithm T in <i>The Art of Computer Programming</i>
     * Internet Draft (PRE-FASCICLE 3A), "A Draft of Section 7.2.1.3 Generating All
     * Combinations</a>, D. Knuth, 2004.</p>
     * <p>
     * The degenerate cases {@code k == 0} and {@code k == n} are NOT handled by this
     * implementation.  If constructor arguments satisfy {@code k == 0}
     * or {@code k >= n}, no exception is generated, but the iterator is empty.
     * </p>
     */
    private static class LexicographicIterator implements Iterator<int[]> {

        /**
         * Size of subsets returned by the iterator
         */
        private final int k;

        /**
         * c[1], ..., c[k] stores the next combination; c[k + 1], c[k + 2] are
         * sentinels.
         * <p>
         * Note that c[0] is "wasted" but this makes it a little easier to
         * follow the code.
         * </p>
         */
        private final int[] c;

        /**
         * Return value for {@link #hasNext()}
         */
        private boolean more = true;

        /**
         * Marker: smallest index such that c[j + 1] > j
         */
        private int j;

        /**
         * Construct a CombinationIterator to enumerate k-sets from n.
         * <p>
         * NOTE: If {@code k === 0} or {@code k >= n}, the Iterator will be empty
         * (that is, {@link #hasNext()} will return {@code false} immediately.
         * </p>
         *
         * @param n size of the set from which subsets are enumerated
         * @param k size of the subsets to enumerate
         */
        LexicographicIterator(int n, int k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.LexicographicIterator_206");
            this.k = k;
            c = new int[AOR_plus(k, 3, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50179, _mut50180, _mut50181, _mut50182)];
            if ((_mut50193 ? (ROR_equals(k, 0, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50183, _mut50184, _mut50185, _mut50186, _mut50187) && ROR_greater_equals(k, n, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50188, _mut50189, _mut50190, _mut50191, _mut50192)) : (ROR_equals(k, 0, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50183, _mut50184, _mut50185, _mut50186, _mut50187) || ROR_greater_equals(k, n, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50188, _mut50189, _mut50190, _mut50191, _mut50192)))) {
                more = false;
                return;
            }
            // Initialize c to start with lexicographically first k-set
            for (int i = 1; ROR_less_equals(i, k, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50198, _mut50199, _mut50200, _mut50201, _mut50202); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.LexicographicIterator_206");
                c[i] = AOR_minus(i, 1, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50194, _mut50195, _mut50196, _mut50197);
            }
            // Initialize sentinels
            c[AOR_plus(k, 1, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50203, _mut50204, _mut50205, _mut50206)] = n;
            c[AOR_plus(k, 2, "org.apache.commons.math3.util.Combinations.LexicographicIterator_206", _mut50207, _mut50208, _mut50209, _mut50210)] = 0;
            // Set up invariant: j is smallest index such that c[j + 1] > j
            j = k;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return more;
        }

        /**
         * {@inheritDoc}
         */
        public int[] next() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.next_233");
            if (!more) {
                throw new NoSuchElementException();
            }
            // Copy return value (prepared by last activation)
            final int[] ret = new int[k];
            System.arraycopy(c, 1, ret, 0, k);
            // T2 and T6 loop
            int x = 0;
            if (ROR_greater(j, 0, "org.apache.commons.math3.util.Combinations.next_233", _mut50211, _mut50212, _mut50213, _mut50214, _mut50215)) {
                x = j;
                c[j] = x;
                j--;
                return ret;
            }
            // T3
            if (ROR_less(AOR_plus(c[1], 1, "org.apache.commons.math3.util.Combinations.next_233", _mut50216, _mut50217, _mut50218, _mut50219), c[2], "org.apache.commons.math3.util.Combinations.next_233", _mut50220, _mut50221, _mut50222, _mut50223, _mut50224)) {
                c[1]++;
                return ret;
            } else {
                j = 2;
            }
            // T4
            boolean stepDone = false;
            while (!stepDone) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.next_233");
                c[AOR_minus(j, 1, "org.apache.commons.math3.util.Combinations.next_233", _mut50225, _mut50226, _mut50227, _mut50228)] = AOR_minus(j, 2, "org.apache.commons.math3.util.Combinations.next_233", _mut50229, _mut50230, _mut50231, _mut50232);
                x = AOR_plus(c[j], 1, "org.apache.commons.math3.util.Combinations.next_233", _mut50233, _mut50234, _mut50235, _mut50236);
                if (ROR_equals(x, c[AOR_plus(j, 1, "org.apache.commons.math3.util.Combinations.next_233", _mut50237, _mut50238, _mut50239, _mut50240)], "org.apache.commons.math3.util.Combinations.next_233", _mut50241, _mut50242, _mut50243, _mut50244, _mut50245)) {
                    j++;
                } else {
                    stepDone = true;
                }
            }
            // T5
            if (ROR_greater(j, k, "org.apache.commons.math3.util.Combinations.next_233", _mut50246, _mut50247, _mut50248, _mut50249, _mut50250)) {
                more = false;
                return ret;
            }
            // T6
            c[j] = x;
            j--;
            return ret;
        }

        /**
         * Not supported.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Iterator with just one element to handle degenerate cases (full array,
     * empty array) for combination iterator.
     */
    private static class SingletonIterator implements Iterator<int[]> {

        /**
         * Singleton array
         */
        private final int[] singleton;

        /**
         * True on initialization, false after first call to next
         */
        private boolean more = true;

        /**
         * Create a singleton iterator providing the given array.
         * @param singleton array returned by the iterator
         */
        SingletonIterator(final int[] singleton) {
            this.singleton = singleton;
        }

        /**
         * @return True until next is called the first time, then false
         */
        public boolean hasNext() {
            return more;
        }

        /**
         * @return the singleton in first activation; throws NSEE thereafter
         */
        public int[] next() {
            if (more) {
                more = false;
                return singleton;
            } else {
                throw new NoSuchElementException();
            }
        }

        /**
         * Not supported
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Defines the lexicographic ordering of combinations, using
     * the {@link #lexNorm(int[])} method.
     */
    private static class LexicographicComparator implements Comparator<int[]>, Serializable {

        /**
         * Serializable version identifier.
         */
        private static final long serialVersionUID = 20130906L;

        /**
         * Size of the set from which combinations are drawn.
         */
        private final int n;

        /**
         * Number of elements in each combination.
         */
        private final int k;

        /**
         * @param n Size of the set from which subsets are selected.
         * @param k Size of the subsets to be enumerated.
         */
        LexicographicComparator(int n, int k) {
            this.n = n;
            this.k = k;
        }

        /**
         * {@inheritDoc}
         *
         * @throws DimensionMismatchException if the array lengths are not
         * equal to {@code k}.
         * @throws OutOfRangeException if an element of the array is not
         * within the interval [0, {@code n}).
         */
        public int compare(int[] c1, int[] c2) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.compare_352");
            if (ROR_not_equals(c1.length, k, "org.apache.commons.math3.util.Combinations.compare_352", _mut50251, _mut50252, _mut50253, _mut50254, _mut50255)) {
                throw new DimensionMismatchException(c1.length, k);
            }
            if (ROR_not_equals(c2.length, k, "org.apache.commons.math3.util.Combinations.compare_352", _mut50256, _mut50257, _mut50258, _mut50259, _mut50260)) {
                throw new DimensionMismatchException(c2.length, k);
            }
            // Method "lexNorm" works with ordered arrays.
            final int[] c1s = MathArrays.copyOf(c1);
            Arrays.sort(c1s);
            final int[] c2s = MathArrays.copyOf(c2);
            Arrays.sort(c2s);
            final long v1 = lexNorm(c1s);
            final long v2 = lexNorm(c2s);
            if (ROR_less(v1, v2, "org.apache.commons.math3.util.Combinations.compare_352", _mut50261, _mut50262, _mut50263, _mut50264, _mut50265)) {
                return -1;
            } else if (ROR_greater(v1, v2, "org.apache.commons.math3.util.Combinations.compare_352", _mut50266, _mut50267, _mut50268, _mut50269, _mut50270)) {
                return 1;
            } else {
                return 0;
            }
        }

        /**
         * Computes the value (in base 10) represented by the digit
         * (interpreted in base {@code n}) in the input array in reverse
         * order.
         * For example if {@code c} is {@code {3, 2, 1}}, and {@code n}
         * is 3, the method will return 18.
         *
         * @param c Input array.
         * @return the lexicographic norm.
         * @throws OutOfRangeException if an element of the array is not
         * within the interval [0, {@code n}).
         */
        private long lexNorm(int[] c) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.lexNorm_391");
            long ret = 0;
            for (int i = 0; ROR_less(i, c.length, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50290, _mut50291, _mut50292, _mut50293, _mut50294); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Combinations.lexNorm_391");
                final int digit = c[i];
                if ((_mut50281 ? (ROR_less(digit, 0, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50271, _mut50272, _mut50273, _mut50274, _mut50275) && ROR_greater_equals(digit, n, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50276, _mut50277, _mut50278, _mut50279, _mut50280)) : (ROR_less(digit, 0, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50271, _mut50272, _mut50273, _mut50274, _mut50275) || ROR_greater_equals(digit, n, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50276, _mut50277, _mut50278, _mut50279, _mut50280)))) {
                    throw new OutOfRangeException(digit, 0, AOR_minus(n, 1, "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50282, _mut50283, _mut50284, _mut50285));
                }
                ret += AOR_multiply(c[i], ArithmeticUtils.pow(n, i), "org.apache.commons.math3.util.Combinations.lexNorm_391", _mut50286, _mut50287, _mut50288, _mut50289);
            }
            return ret;
        }
    }
}
