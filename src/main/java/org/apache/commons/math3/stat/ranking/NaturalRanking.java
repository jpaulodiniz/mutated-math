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
package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p> Ranking based on the natural ordering on doubles.</p>
 * <p>NaNs are treated according to the configured {@link NaNStrategy} and ties
 * are handled using the selected {@link TiesStrategy}.
 * Configuration settings are supplied in optional constructor arguments.
 * Defaults are {@link NaNStrategy#FAILED} and {@link TiesStrategy#AVERAGE},
 * respectively. When using {@link TiesStrategy#RANDOM}, a
 * {@link RandomGenerator} may be supplied as a constructor argument.</p>
 * <p>Examples:
 * <table border="1" cellpadding="3">
 * <tr><th colspan="3">
 * Input data: (20, 17, 30, 42.3, 17, 50, Double.NaN, Double.NEGATIVE_INFINITY, 17)
 * </th></tr>
 * <tr><th>NaNStrategy</th><th>TiesStrategy</th>
 * <th><code>rank(data)</code></th>
 * <tr>
 * <td>default (NaNs maximal)</td>
 * <td>default (ties averaged)</td>
 * <td>(5, 3, 6, 7, 3, 8, 9, 1, 3)</td></tr>
 * <tr>
 * <td>default (NaNs maximal)</td>
 * <td>MINIMUM</td>
 * <td>(5, 2, 6, 7, 2, 8, 9, 1, 2)</td></tr>
 * <tr>
 * <td>MINIMAL</td>
 * <td>default (ties averaged)</td>
 * <td>(6, 4, 7, 8, 4, 9, 1.5, 1.5, 4)</td></tr>
 * <tr>
 * <td>REMOVED</td>
 * <td>SEQUENTIAL</td>
 * <td>(5, 2, 6, 7, 3, 8, 1, 4)</td></tr>
 * <tr>
 * <td>MINIMAL</td>
 * <td>MAXIMUM</td>
 * <td>(6, 5, 7, 8, 5, 9, 2, 2, 5)</td></tr></table></p>
 *
 * @since 2.0
 */
public class NaturalRanking implements RankingAlgorithm {

    @Conditional
    public static boolean _mut10488 = false, _mut10489 = false, _mut10490 = false, _mut10491 = false, _mut10492 = false, _mut10493 = false, _mut10494 = false, _mut10495 = false, _mut10496 = false, _mut10497 = false, _mut10498 = false, _mut10499 = false, _mut10500 = false, _mut10501 = false, _mut10502 = false, _mut10503 = false, _mut10504 = false, _mut10505 = false, _mut10506 = false, _mut10507 = false, _mut10508 = false, _mut10509 = false, _mut10510 = false, _mut10511 = false, _mut10512 = false, _mut10513 = false, _mut10514 = false, _mut10515 = false, _mut10516 = false, _mut10517 = false, _mut10518 = false, _mut10519 = false, _mut10520 = false, _mut10521 = false, _mut10522 = false, _mut10523 = false, _mut10524 = false, _mut10525 = false, _mut10526 = false, _mut10527 = false, _mut10528 = false, _mut10529 = false, _mut10530 = false, _mut10531 = false, _mut10532 = false, _mut10533 = false, _mut10534 = false, _mut10535 = false, _mut10536 = false, _mut10537 = false, _mut10538 = false, _mut10539 = false, _mut10540 = false, _mut10541 = false, _mut10542 = false, _mut10543 = false, _mut10544 = false, _mut10545 = false, _mut10546 = false, _mut10547 = false, _mut10548 = false, _mut10549 = false, _mut10550 = false, _mut10551 = false, _mut10552 = false, _mut10553 = false, _mut10554 = false, _mut10555 = false, _mut10556 = false, _mut10557 = false, _mut10558 = false, _mut10559 = false, _mut10560 = false, _mut10561 = false, _mut10562 = false, _mut10563 = false, _mut10564 = false, _mut10565 = false, _mut10566 = false, _mut10567 = false, _mut10568 = false, _mut10569 = false, _mut10570 = false, _mut10571 = false, _mut10572 = false, _mut10573 = false, _mut10574 = false, _mut10575 = false, _mut10576 = false, _mut10577 = false, _mut10578 = false, _mut10579 = false, _mut10580 = false, _mut10581 = false, _mut10582 = false, _mut10583 = false, _mut10584 = false, _mut10585 = false, _mut10586 = false, _mut10587 = false, _mut10588 = false, _mut10589 = false, _mut10590 = false, _mut10591 = false, _mut10592 = false, _mut10593 = false, _mut10594 = false, _mut10595 = false;

    /**
     * default NaN strategy
     */
    public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;

    /**
     * default ties strategy
     */
    public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;

    /**
     * NaN strategy - defaults to NaNs maximal
     */
    private final NaNStrategy nanStrategy;

    /**
     * Ties strategy - defaults to ties averaged
     */
    private final TiesStrategy tiesStrategy;

    /**
     * Source of random data - used only when ties strategy is RANDOM
     */
    private final RandomDataGenerator randomData;

    /**
     * Create a NaturalRanking with default strategies for handling ties and NaNs.
     */
    public NaturalRanking() {
        super();
        tiesStrategy = DEFAULT_TIES_STRATEGY;
        nanStrategy = DEFAULT_NAN_STRATEGY;
        randomData = null;
    }

    /**
     * Create a NaturalRanking with the given TiesStrategy.
     *
     * @param tiesStrategy the TiesStrategy to use
     */
    public NaturalRanking(TiesStrategy tiesStrategy) {
        super();
        this.tiesStrategy = tiesStrategy;
        nanStrategy = DEFAULT_NAN_STRATEGY;
        randomData = new RandomDataGenerator();
    }

    /**
     * Create a NaturalRanking with the given NaNStrategy.
     *
     * @param nanStrategy the NaNStrategy to use
     */
    public NaturalRanking(NaNStrategy nanStrategy) {
        super();
        this.nanStrategy = nanStrategy;
        tiesStrategy = DEFAULT_TIES_STRATEGY;
        randomData = null;
    }

    /**
     * Create a NaturalRanking with the given NaNStrategy and TiesStrategy.
     *
     * @param nanStrategy NaNStrategy to use
     * @param tiesStrategy TiesStrategy to use
     */
    public NaturalRanking(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        super();
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = tiesStrategy;
        randomData = new RandomDataGenerator();
    }

    /**
     * Create a NaturalRanking with TiesStrategy.RANDOM and the given
     * RandomGenerator as the source of random data.
     *
     * @param randomGenerator source of random data
     */
    public NaturalRanking(RandomGenerator randomGenerator) {
        super();
        this.tiesStrategy = TiesStrategy.RANDOM;
        nanStrategy = DEFAULT_NAN_STRATEGY;
        randomData = new RandomDataGenerator(randomGenerator);
    }

    /**
     * Create a NaturalRanking with the given NaNStrategy, TiesStrategy.RANDOM
     * and the given source of random data.
     *
     * @param nanStrategy NaNStrategy to use
     * @param randomGenerator source of random data
     */
    public NaturalRanking(NaNStrategy nanStrategy, RandomGenerator randomGenerator) {
        super();
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = TiesStrategy.RANDOM;
        randomData = new RandomDataGenerator(randomGenerator);
    }

    /**
     * Return the NaNStrategy
     *
     * @return returns the NaNStrategy
     */
    public NaNStrategy getNanStrategy() {
        return nanStrategy;
    }

    /**
     * Return the TiesStrategy
     *
     * @return the TiesStrategy
     */
    public TiesStrategy getTiesStrategy() {
        return tiesStrategy;
    }

    /**
     * Rank <code>data</code> using the natural ordering on Doubles, with
     * NaN values handled according to <code>nanStrategy</code> and ties
     * resolved using <code>tiesStrategy.</code>
     *
     * @param data array to be ranked
     * @return array of ranks
     * @throws NotANumberException if the selected {@link NaNStrategy} is {@code FAILED}
     * and a {@link Double#NaN} is encountered in the input data
     */
    public double[] rank(double[] data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191");
        // Array recording initial positions of data to be ranked
        IntDoublePair[] ranks = new IntDoublePair[data.length];
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10488, _mut10489, _mut10490, _mut10491, _mut10492); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191");
            ranks[i] = new IntDoublePair(data[i], i);
        }
        // Recode, remove or record positions of NaNs
        List<Integer> nanPositions = null;
        switch(nanStrategy) {
            case // Replace NaNs with +INFs
            MAXIMAL:
                recodeNaNs(ranks, Double.POSITIVE_INFINITY);
                break;
            case // Replace NaNs with -INFs
            MINIMAL:
                recodeNaNs(ranks, Double.NEGATIVE_INFINITY);
                break;
            case // Drop NaNs from data
            REMOVED:
                ranks = removeNaNs(ranks);
                break;
            case // Record positions of NaNs
            FIXED:
                nanPositions = getNanPositions(ranks);
                break;
            case FAILED:
                nanPositions = getNanPositions(ranks);
                if (ROR_greater(nanPositions.size(), 0, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10493, _mut10494, _mut10495, _mut10496, _mut10497)) {
                    throw new NotANumberException();
                }
                break;
            default:
                // this should not happen unless NaNStrategy enum is changed
                throw new MathInternalError();
        }
        // Sort the IntDoublePairs
        Arrays.sort(ranks);
        // resolving ties as we go
        double[] out = new double[ranks.length];
        // position in sorted array
        int pos = 1;
        out[ranks[0].getPosition()] = pos;
        List<Integer> tiesTrace = new ArrayList<Integer>();
        tiesTrace.add(ranks[0].getPosition());
        for (int i = 1; ROR_less(i, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10516, _mut10517, _mut10518, _mut10519, _mut10520); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191");
            if (ROR_greater(Double.compare(ranks[i].getValue(), ranks[AOR_minus(i, 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10498, _mut10499, _mut10500, _mut10501)].getValue()), 0, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10502, _mut10503, _mut10504, _mut10505, _mut10506)) {
                // tie sequence has ended (or had length 1)
                pos = AOR_plus(i, 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10507, _mut10508, _mut10509, _mut10510);
                if (ROR_greater(tiesTrace.size(), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10511, _mut10512, _mut10513, _mut10514, _mut10515)) {
                    // if seq is nontrivial, resolve
                    resolveTie(out, tiesTrace);
                }
                tiesTrace = new ArrayList<Integer>();
                tiesTrace.add(ranks[i].getPosition());
            } else {
                // tie sequence continues
                tiesTrace.add(ranks[i].getPosition());
            }
            out[ranks[i].getPosition()] = pos;
        }
        if (ROR_greater(tiesTrace.size(), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.rank_191", _mut10521, _mut10522, _mut10523, _mut10524, _mut10525)) {
            // handle tie sequence at end
            resolveTie(out, tiesTrace);
        }
        if (nanStrategy == NaNStrategy.FIXED) {
            restoreNaNs(out, nanPositions);
        }
        return out;
    }

    /**
     * Returns an array that is a copy of the input array with IntDoublePairs
     * having NaN values removed.
     *
     * @param ranks input array
     * @return array with NaN-valued entries removed
     */
    private IntDoublePair[] removeNaNs(IntDoublePair[] ranks) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265");
        if (!containsNaNs(ranks)) {
            return ranks;
        }
        IntDoublePair[] outRanks = new IntDoublePair[ranks.length];
        int j = 0;
        for (int i = 0; ROR_less(i, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265", _mut10535, _mut10536, _mut10537, _mut10538, _mut10539); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265");
            if (Double.isNaN(ranks[i].getValue())) {
                // drop, but adjust original ranks of later elements
                for (int k = i + 1; ROR_less(k, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265", _mut10530, _mut10531, _mut10532, _mut10533, _mut10534); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265");
                    ranks[k] = new IntDoublePair(ranks[k].getValue(), AOR_minus(ranks[k].getPosition(), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.removeNaNs_265", _mut10526, _mut10527, _mut10528, _mut10529));
                }
            } else {
                outRanks[j] = new IntDoublePair(ranks[i].getValue(), ranks[i].getPosition());
                j++;
            }
        }
        IntDoublePair[] returnRanks = new IntDoublePair[j];
        System.arraycopy(outRanks, 0, returnRanks, 0, j);
        return returnRanks;
    }

    /**
     * Recodes NaN values to the given value.
     *
     * @param ranks array to recode
     * @param value the value to replace NaNs with
     */
    private void recodeNaNs(IntDoublePair[] ranks, double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.recodeNaNs_295");
        for (int i = 0; ROR_less(i, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.recodeNaNs_295", _mut10540, _mut10541, _mut10542, _mut10543, _mut10544); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.recodeNaNs_295");
            if (Double.isNaN(ranks[i].getValue())) {
                ranks[i] = new IntDoublePair(value, ranks[i].getPosition());
            }
        }
    }

    /**
     * Checks for presence of NaNs in <code>ranks.</code>
     *
     * @param ranks array to be searched for NaNs
     * @return true iff ranks contains one or more NaNs
     */
    private boolean containsNaNs(IntDoublePair[] ranks) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.containsNaNs_310");
        for (int i = 0; ROR_less(i, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.containsNaNs_310", _mut10545, _mut10546, _mut10547, _mut10548, _mut10549); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.containsNaNs_310");
            if (Double.isNaN(ranks[i].getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resolve a sequence of ties, using the configured {@link TiesStrategy}.
     * The input <code>ranks</code> array is expected to take the same value
     * for all indices in <code>tiesTrace</code>.  The common value is recoded
     * according to the tiesStrategy. For example, if ranks = <5,8,2,6,2,7,1,2>,
     * tiesTrace = <2,4,7> and tiesStrategy is MINIMUM, ranks will be unchanged.
     * The same array and trace with tiesStrategy AVERAGE will come out
     * <5,8,3,6,3,7,1,3>.
     *
     * @param ranks array of ranks
     * @param tiesTrace list of indices where <code>ranks</code> is constant
     * -- that is, for any i and j in TiesTrace, <code> ranks[i] == ranks[j]
     * </code>
     */
    private void resolveTie(double[] ranks, List<Integer> tiesTrace) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333");
        // constant value of ranks over tiesTrace
        final double c = ranks[tiesTrace.get(0)];
        // length of sequence of tied ranks
        final int length = tiesTrace.size();
        switch(tiesStrategy) {
            case // Replace ranks with average
            AVERAGE:
                fill(ranks, tiesTrace, AOR_divide((AOR_minus(AOR_plus(AOR_multiply(2, c, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10550, _mut10551, _mut10552, _mut10553), length, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10554, _mut10555, _mut10556, _mut10557), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10558, _mut10559, _mut10560, _mut10561)), 2d, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10562, _mut10563, _mut10564, _mut10565));
                break;
            case // Replace ranks with maximum values
            MAXIMUM:
                fill(ranks, tiesTrace, AOR_minus(AOR_plus(c, length, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10566, _mut10567, _mut10568, _mut10569), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10570, _mut10571, _mut10572, _mut10573));
                break;
            case // Replace ties with minimum
            MINIMUM:
                fill(ranks, tiesTrace, c);
                break;
            case // Fill with random integral values in [c, c + length - 1]
            RANDOM:
                Iterator<Integer> iterator = tiesTrace.iterator();
                long f = FastMath.round(c);
                while (iterator.hasNext()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333");
                    // No advertised exception because args are guaranteed valid
                    ranks[iterator.next()] = randomData.nextLong(f, AOR_minus(AOR_plus(f, length, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10574, _mut10575, _mut10576, _mut10577), 1, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10578, _mut10579, _mut10580, _mut10581));
                }
                break;
            case // Fill sequentially from c to c + length - 1
            SEQUENTIAL:
                // walk and fill
                iterator = tiesTrace.iterator();
                f = FastMath.round(c);
                int i = 0;
                while (iterator.hasNext()) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333");
                    ranks[iterator.next()] = AOR_plus(f, i++, "org.apache.commons.math3.stat.ranking.NaturalRanking.resolveTie_333", _mut10582, _mut10583, _mut10584, _mut10585);
                }
                break;
            default:
                // this should not happen unless TiesStrategy enum is changed
                throw new MathInternalError();
        }
    }

    /**
     * Sets<code>data[i] = value</code> for each i in <code>tiesTrace.</code>
     *
     * @param data array to modify
     * @param tiesTrace list of index values to set
     * @param value value to set
     */
    private void fill(double[] data, List<Integer> tiesTrace, double value) {
        Iterator<Integer> iterator = tiesTrace.iterator();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.fill_381");
            data[iterator.next()] = value;
        }
    }

    /**
     * Set <code>ranks[i] = Double.NaN</code> for each i in <code>nanPositions.</code>
     *
     * @param ranks array to modify
     * @param nanPositions list of index values to set to <code>Double.NaN</code>
     */
    private void restoreNaNs(double[] ranks, List<Integer> nanPositions) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.restoreNaNs_394");
        if (ROR_equals(nanPositions.size(), 0, "org.apache.commons.math3.stat.ranking.NaturalRanking.restoreNaNs_394", _mut10586, _mut10587, _mut10588, _mut10589, _mut10590)) {
            return;
        }
        Iterator<Integer> iterator = nanPositions.iterator();
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.restoreNaNs_394");
            ranks[iterator.next().intValue()] = Double.NaN;
        }
    }

    /**
     * Returns a list of indexes where <code>ranks</code> is <code>NaN.</code>
     *
     * @param ranks array to search for <code>NaNs</code>
     * @return list of indexes i such that <code>ranks[i] = NaN</code>
     */
    private List<Integer> getNanPositions(IntDoublePair[] ranks) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.getNanPositions_411");
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i = 0; ROR_less(i, ranks.length, "org.apache.commons.math3.stat.ranking.NaturalRanking.getNanPositions_411", _mut10591, _mut10592, _mut10593, _mut10594, _mut10595); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.ranking.NaturalRanking.getNanPositions_411");
            if (Double.isNaN(ranks[i].getValue())) {
                out.add(Integer.valueOf(i));
            }
        }
        return out;
    }

    /**
     * Represents the position of a double value in an ordering.
     * Comparable interface is implemented so Arrays.sort can be used
     * to sort an array of IntDoublePairs by value.  Note that the
     * implicitly defined natural ordering is NOT consistent with equals.
     */
    private static class IntDoublePair implements Comparable<IntDoublePair> {

        /**
         * Value of the pair
         */
        private final double value;

        /**
         * Original position of the pair
         */
        private final int position;

        /**
         * Construct an IntDoublePair with the given value and position.
         * @param value the value of the pair
         * @param position the original position
         */
        IntDoublePair(double value, int position) {
            this.value = value;
            this.position = position;
        }

        /**
         * Compare this IntDoublePair to another pair.
         * Only the <strong>values</strong> are compared.
         *
         * @param other the other pair to compare this to
         * @return result of <code>Double.compare(value, other.value)</code>
         */
        public int compareTo(IntDoublePair other) {
            return Double.compare(value, other.value);
        }

        /**
         * Returns the value of the pair.
         * @return value
         */
        public double getValue() {
            return value;
        }

        /**
         * Returns the original position of the pair.
         * @return position
         */
        public int getPosition() {
            return position;
        }
    }
}
