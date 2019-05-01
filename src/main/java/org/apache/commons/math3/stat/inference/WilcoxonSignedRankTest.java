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
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * An implementation of the Wilcoxon signed-rank test.
 */
public class WilcoxonSignedRankTest {

    @Conditional
    public static boolean _mut7670 = false, _mut7671 = false, _mut7672 = false, _mut7673 = false, _mut7674 = false, _mut7675 = false, _mut7676 = false, _mut7677 = false, _mut7678 = false, _mut7679 = false, _mut7680 = false, _mut7681 = false, _mut7682 = false, _mut7683 = false, _mut7684 = false, _mut7685 = false, _mut7686 = false, _mut7687 = false, _mut7688 = false, _mut7689 = false, _mut7690 = false, _mut7691 = false, _mut7692 = false, _mut7693 = false, _mut7694 = false, _mut7695 = false, _mut7696 = false, _mut7697 = false, _mut7698 = false, _mut7699 = false, _mut7700 = false, _mut7701 = false, _mut7702 = false, _mut7703 = false, _mut7704 = false, _mut7705 = false, _mut7706 = false, _mut7707 = false, _mut7708 = false, _mut7709 = false, _mut7710 = false, _mut7711 = false, _mut7712 = false, _mut7713 = false, _mut7714 = false, _mut7715 = false, _mut7716 = false, _mut7717 = false, _mut7718 = false, _mut7719 = false, _mut7720 = false, _mut7721 = false, _mut7722 = false, _mut7723 = false, _mut7724 = false, _mut7725 = false, _mut7726 = false, _mut7727 = false, _mut7728 = false, _mut7729 = false, _mut7730 = false, _mut7731 = false, _mut7732 = false, _mut7733 = false, _mut7734 = false, _mut7735 = false, _mut7736 = false, _mut7737 = false, _mut7738 = false, _mut7739 = false, _mut7740 = false, _mut7741 = false, _mut7742 = false, _mut7743 = false, _mut7744 = false, _mut7745 = false, _mut7746 = false, _mut7747 = false, _mut7748 = false, _mut7749 = false, _mut7750 = false, _mut7751 = false, _mut7752 = false, _mut7753 = false, _mut7754 = false, _mut7755 = false, _mut7756 = false, _mut7757 = false, _mut7758 = false, _mut7759 = false, _mut7760 = false, _mut7761 = false, _mut7762 = false, _mut7763 = false, _mut7764 = false, _mut7765 = false, _mut7766 = false, _mut7767 = false, _mut7768 = false, _mut7769 = false, _mut7770 = false, _mut7771 = false, _mut7772 = false, _mut7773 = false, _mut7774 = false, _mut7775 = false, _mut7776 = false, _mut7777 = false, _mut7778 = false, _mut7779 = false, _mut7780 = false, _mut7781 = false, _mut7782 = false, _mut7783 = false, _mut7784 = false, _mut7785 = false, _mut7786 = false, _mut7787 = false, _mut7788 = false, _mut7789 = false, _mut7790 = false, _mut7791 = false, _mut7792 = false, _mut7793 = false, _mut7794 = false, _mut7795 = false, _mut7796 = false, _mut7797 = false, _mut7798 = false, _mut7799 = false, _mut7800 = false, _mut7801 = false, _mut7802 = false, _mut7803 = false, _mut7804 = false, _mut7805 = false, _mut7806 = false, _mut7807 = false, _mut7808 = false, _mut7809 = false, _mut7810 = false, _mut7811 = false, _mut7812 = false, _mut7813 = false, _mut7814 = false, _mut7815 = false, _mut7816 = false, _mut7817 = false, _mut7818 = false, _mut7819 = false, _mut7820 = false, _mut7821 = false, _mut7822 = false, _mut7823 = false, _mut7824 = false, _mut7825 = false, _mut7826 = false, _mut7827 = false, _mut7828 = false, _mut7829 = false;

    /**
     * Ranking algorithm.
     */
    private NaturalRanking naturalRanking;

    /**
     * Create a test instance where NaN's are left in place and ties get
     * the average of applicable ranks. Use this unless you are very sure
     * of what you are doing.
     */
    public WilcoxonSignedRankTest() {
        naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    /**
     * Create a test instance using the given strategies for NaN's and ties.
     * Only use this if you are sure of what you are doing.
     *
     * @param nanStrategy
     *            specifies the strategy that should be used for Double.NaN's
     * @param tiesStrategy
     *            specifies the strategy that should be used for ties
     */
    public WilcoxonSignedRankTest(final NaNStrategy nanStrategy, final TiesStrategy tiesStrategy) {
        naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
    }

    /**
     * Ensures that the provided arrays fulfills the assumptions.
     *
     * @param x first sample
     * @param y second sample
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length.
     */
    private void ensureDataConformance(final double[] x, final double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74");
        if ((_mut7670 ? (x == null && y == null) : (x == null || y == null))) {
            throw new NullArgumentException();
        }
        if ((_mut7681 ? (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74", _mut7671, _mut7672, _mut7673, _mut7674, _mut7675) && ROR_equals(y.length, 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74", _mut7676, _mut7677, _mut7678, _mut7679, _mut7680)) : (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74", _mut7671, _mut7672, _mut7673, _mut7674, _mut7675) || ROR_equals(y.length, 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74", _mut7676, _mut7677, _mut7678, _mut7679, _mut7680)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(y.length, x.length, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.ensureDataConformance_74", _mut7682, _mut7683, _mut7684, _mut7685, _mut7686)) {
            throw new DimensionMismatchException(y.length, x.length);
        }
    }

    /**
     * Calculates y[i] - x[i] for all i
     *
     * @param x first sample
     * @param y second sample
     * @return z = y - x
     */
    private double[] calculateDifferences(final double[] x, final double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateDifferences_97");
        final double[] z = new double[x.length];
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateDifferences_97", _mut7691, _mut7692, _mut7693, _mut7694, _mut7695); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateDifferences_97");
            z[i] = AOR_minus(y[i], x[i], "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateDifferences_97", _mut7687, _mut7688, _mut7689, _mut7690);
        }
        return z;
    }

    /**
     * Calculates |z[i]| for all i
     *
     * @param z sample
     * @return |z|
     * @throws NullArgumentException if {@code z} is {@code null}
     * @throws NoDataException if {@code z} is zero-length.
     */
    private double[] calculateAbsoluteDifferences(final double[] z) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAbsoluteDifferences_116");
        if (z == null) {
            throw new NullArgumentException();
        }
        if (ROR_equals(z.length, 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAbsoluteDifferences_116", _mut7696, _mut7697, _mut7698, _mut7699, _mut7700)) {
            throw new NoDataException();
        }
        final double[] zAbs = new double[z.length];
        for (int i = 0; ROR_less(i, z.length, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAbsoluteDifferences_116", _mut7701, _mut7702, _mut7703, _mut7704, _mut7705); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAbsoluteDifferences_116");
            zAbs[i] = FastMath.abs(z[i]);
        }
        return zAbs;
    }

    /**
     * Computes the <a
     * href="http://en.wikipedia.org/wiki/Wilcoxon_signed-rank_test">
     * Wilcoxon signed ranked statistic</a> comparing mean for two related
     * samples or repeated measurements on a single sample.
     * <p>
     * This statistic can be used to perform a Wilcoxon signed ranked test
     * evaluating the null hypothesis that the two related samples or repeated
     * measurements on a single sample has equal mean.
     * </p>
     * <p>
     * Let X<sub>i</sub> denote the i'th individual of the first sample and
     * Y<sub>i</sub> the related i'th individual in the second sample. Let
     * Z<sub>i</sub> = Y<sub>i</sub> - X<sub>i</sub>.
     * </p>
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>The differences Z<sub>i</sub> must be independent.</li>
     * <li>Each Z<sub>i</sub> comes from a continuous population (they must be
     * identical) and is symmetric about a common median.</li>
     * <li>The values that X<sub>i</sub> and Y<sub>i</sub> represent are
     * ordered, so the comparisons greater than, less than, and equal to are
     * meaningful.</li>
     * </ul>
     * </p>
     *
     * @param x the first sample
     * @param y the second sample
     * @return wilcoxonSignedRank statistic (the larger of W+ and W-)
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length.
     */
    public double wilcoxonSignedRank(final double[] x, final double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171");
        ensureDataConformance(x, y);
        // specified
        final double[] z = calculateDifferences(x, y);
        final double[] zAbs = calculateAbsoluteDifferences(z);
        final double[] ranks = naturalRanking.rank(zAbs);
        double Wplus = 0;
        for (int i = 0; ROR_less(i, z.length, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7711, _mut7712, _mut7713, _mut7714, _mut7715); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171");
            if (ROR_greater(z[i], 0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7706, _mut7707, _mut7708, _mut7709, _mut7710)) {
                Wplus += ranks[i];
            }
        }
        final int N = x.length;
        final double Wminus = AOR_minus((AOR_divide(((double) (AOR_multiply(N, (AOR_plus(N, 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7716, _mut7717, _mut7718, _mut7719)), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7720, _mut7721, _mut7722, _mut7723))), 2.0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7724, _mut7725, _mut7726, _mut7727)), Wplus, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRank_171", _mut7728, _mut7729, _mut7730, _mut7731);
        return FastMath.max(Wplus, Wminus);
    }

    /**
     * Algorithm inspired by
     * http://www.fon.hum.uva.nl/Service/Statistics/Signed_Rank_Algorihms.html#C
     * by Rob van Son, Institute of Phonetic Sciences & IFOTT,
     * University of Amsterdam
     *
     * @param Wmax largest Wilcoxon signed rank value
     * @param N number of subjects (corresponding to x.length)
     * @return two-sided exact p-value
     */
    private double calculateExactPValue(final double Wmax, final int N) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207");
        // Total number of outcomes (equal to 2^N but a lot faster)
        final int m = 1 << N;
        int largerRankSums = 0;
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7751, _mut7752, _mut7753, _mut7754, _mut7755); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207");
            int rankSum = 0;
            // Generate all possible rank sums
            for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7741, _mut7742, _mut7743, _mut7744, _mut7745); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207");
                // (i >> j) & 1 extract i's j-th bit from the right
                if (ROR_equals(((i >> j) & 1), 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7732, _mut7733, _mut7734, _mut7735, _mut7736)) {
                    rankSum += AOR_plus(j, 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7737, _mut7738, _mut7739, _mut7740);
                }
            }
            if (ROR_greater_equals(rankSum, Wmax, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7746, _mut7747, _mut7748, _mut7749, _mut7750)) {
                ++largerRankSums;
            }
        }
        /*
         * largerRankSums / m gives the one-sided p-value, so it's multiplied
         * with 2 to get the two-sided p-value
         */
        return AOR_divide(AOR_multiply(2, ((double) largerRankSums), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7756, _mut7757, _mut7758, _mut7759), ((double) m), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateExactPValue_207", _mut7760, _mut7761, _mut7762, _mut7763);
    }

    /**
     * @param Wmin smallest Wilcoxon signed rank value
     * @param N number of subjects (corresponding to x.length)
     * @return two-sided asymptotic p-value
     */
    private double calculateAsymptoticPValue(final double Wmin, final int N) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243");
        final double ES = AOR_divide((double) (AOR_multiply(N, (AOR_plus(N, 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7764, _mut7765, _mut7766, _mut7767)), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7768, _mut7769, _mut7770, _mut7771)), 4.0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7772, _mut7773, _mut7774, _mut7775);
        /* Same as (but saves computations):
         * final double VarW = ((double) (N * (N + 1) * (2*N + 1))) / 24;
         */
        final double VarS = AOR_multiply(ES, (AOR_divide((double) (AOR_plus(AOR_multiply(2, N, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7776, _mut7777, _mut7778, _mut7779), 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7780, _mut7781, _mut7782, _mut7783)), 6.0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7784, _mut7785, _mut7786, _mut7787)), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7788, _mut7789, _mut7790, _mut7791);
        // - 0.5 is a continuity correction
        final double z = AOR_divide((AOR_minus(AOR_minus(Wmin, ES, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7792, _mut7793, _mut7794, _mut7795), 0.5, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7796, _mut7797, _mut7798, _mut7799)), FastMath.sqrt(VarS), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7800, _mut7801, _mut7802, _mut7803);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final NormalDistribution standardNormal = new NormalDistribution(null, 0, 1);
        return AOR_multiply(2, standardNormal.cumulativeProbability(z), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.calculateAsymptoticPValue_243", _mut7804, _mut7805, _mut7806, _mut7807);
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a <a
     * href="http://en.wikipedia.org/wiki/Wilcoxon_signed-rank_test">
     * Wilcoxon signed ranked statistic</a> comparing mean for two related
     * samples or repeated measurements on a single sample.
     * <p>
     * Let X<sub>i</sub> denote the i'th individual of the first sample and
     * Y<sub>i</sub> the related i'th individual in the second sample. Let
     * Z<sub>i</sub> = Y<sub>i</sub> - X<sub>i</sub>.
     * </p>
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>The differences Z<sub>i</sub> must be independent.</li>
     * <li>Each Z<sub>i</sub> comes from a continuous population (they must be
     * identical) and is symmetric about a common median.</li>
     * <li>The values that X<sub>i</sub> and Y<sub>i</sub> represent are
     * ordered, so the comparisons greater than, less than, and equal to are
     * meaningful.</li>
     * </ul>
     * </p>
     *
     * @param x the first sample
     * @param y the second sample
     * @param exactPValue
     *            if the exact p-value is wanted (only works for x.length <= 30,
     *            if true and x.length > 30, this is ignored because
     *            calculations may take too long)
     * @return p-value
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length.
     * @throws NumberIsTooLargeException if {@code exactPValue} is {@code true}
     * and {@code x.length} > 30
     * @throws ConvergenceException if the p-value can not be computed due to
     * a convergence error
     * @throws MaxCountExceededException if the maximum number of iterations
     * is exceeded
     */
    public double wilcoxonSignedRankTest(final double[] x, final double[] y, final boolean exactPValue) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooLargeException, ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304");
        ensureDataConformance(x, y);
        final int N = x.length;
        final double Wmax = wilcoxonSignedRank(x, y);
        if ((_mut7813 ? (exactPValue || ROR_greater(N, 30, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7808, _mut7809, _mut7810, _mut7811, _mut7812)) : (exactPValue && ROR_greater(N, 30, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7808, _mut7809, _mut7810, _mut7811, _mut7812)))) {
            throw new NumberIsTooLargeException(N, 30, true);
        }
        if (exactPValue) {
            return calculateExactPValue(Wmax, N);
        } else {
            final double Wmin = AOR_minus((AOR_divide((double) (AOR_multiply(N, (AOR_plus(N, 1, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7814, _mut7815, _mut7816, _mut7817)), "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7818, _mut7819, _mut7820, _mut7821)), 2.0, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7822, _mut7823, _mut7824, _mut7825)), Wmax, "org.apache.commons.math3.stat.inference.WilcoxonSignedRankTest.wilcoxonSignedRankTest_304", _mut7826, _mut7827, _mut7828, _mut7829);
            return calculateAsymptoticPValue(Wmin, N);
        }
    }
}
