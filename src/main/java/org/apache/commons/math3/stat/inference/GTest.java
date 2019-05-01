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

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements <a href="http://en.wikipedia.org/wiki/G-test">G Test</a>
 * statistics.
 *
 * <p>This is known in statistical genetics as the McDonald-Kreitman test.
 * The implementation handles both known and unknown distributions.</p>
 *
 * <p>Two samples tests can be used when the distribution is unknown <i>a priori</i>
 * but provided by one sample, or when the hypothesis under test is that the two
 * samples come from the same underlying distribution.</p>
 *
 * @since 3.1
 */
public class GTest {

    @Conditional
    public static boolean _mut5715 = false, _mut5716 = false, _mut5717 = false, _mut5718 = false, _mut5719 = false, _mut5720 = false, _mut5721 = false, _mut5722 = false, _mut5723 = false, _mut5724 = false, _mut5725 = false, _mut5726 = false, _mut5727 = false, _mut5728 = false, _mut5729 = false, _mut5730 = false, _mut5731 = false, _mut5732 = false, _mut5733 = false, _mut5734 = false, _mut5735 = false, _mut5736 = false, _mut5737 = false, _mut5738 = false, _mut5739 = false, _mut5740 = false, _mut5741 = false, _mut5742 = false, _mut5743 = false, _mut5744 = false, _mut5745 = false, _mut5746 = false, _mut5747 = false, _mut5748 = false, _mut5749 = false, _mut5750 = false, _mut5751 = false, _mut5752 = false, _mut5753 = false, _mut5754 = false, _mut5755 = false, _mut5756 = false, _mut5757 = false, _mut5758 = false, _mut5759 = false, _mut5760 = false, _mut5761 = false, _mut5762 = false, _mut5763 = false, _mut5764 = false, _mut5765 = false, _mut5766 = false, _mut5767 = false, _mut5768 = false, _mut5769 = false, _mut5770 = false, _mut5771 = false, _mut5772 = false, _mut5773 = false, _mut5774 = false, _mut5775 = false, _mut5776 = false, _mut5777 = false, _mut5778 = false, _mut5779 = false, _mut5780 = false, _mut5781 = false, _mut5782 = false, _mut5783 = false, _mut5784 = false, _mut5785 = false, _mut5786 = false, _mut5787 = false, _mut5788 = false, _mut5789 = false, _mut5790 = false, _mut5791 = false, _mut5792 = false, _mut5793 = false, _mut5794 = false, _mut5795 = false, _mut5796 = false, _mut5797 = false, _mut5798 = false, _mut5799 = false, _mut5800 = false, _mut5801 = false, _mut5802 = false, _mut5803 = false, _mut5804 = false, _mut5805 = false, _mut5806 = false, _mut5807 = false, _mut5808 = false, _mut5809 = false, _mut5810 = false, _mut5811 = false, _mut5812 = false, _mut5813 = false, _mut5814 = false, _mut5815 = false, _mut5816 = false, _mut5817 = false, _mut5818 = false, _mut5819 = false, _mut5820 = false, _mut5821 = false, _mut5822 = false, _mut5823 = false, _mut5824 = false, _mut5825 = false, _mut5826 = false, _mut5827 = false, _mut5828 = false, _mut5829 = false, _mut5830 = false, _mut5831 = false, _mut5832 = false, _mut5833 = false, _mut5834 = false, _mut5835 = false, _mut5836 = false, _mut5837 = false, _mut5838 = false, _mut5839 = false, _mut5840 = false, _mut5841 = false, _mut5842 = false, _mut5843 = false, _mut5844 = false, _mut5845 = false, _mut5846 = false, _mut5847 = false, _mut5848 = false, _mut5849 = false, _mut5850 = false, _mut5851 = false, _mut5852 = false, _mut5853 = false, _mut5854 = false, _mut5855 = false, _mut5856 = false, _mut5857 = false, _mut5858 = false, _mut5859 = false, _mut5860 = false, _mut5861 = false, _mut5862 = false, _mut5863 = false, _mut5864 = false, _mut5865 = false, _mut5866 = false, _mut5867 = false, _mut5868 = false, _mut5869 = false, _mut5870 = false, _mut5871 = false, _mut5872 = false, _mut5873 = false, _mut5874 = false, _mut5875 = false, _mut5876 = false, _mut5877 = false, _mut5878 = false, _mut5879 = false, _mut5880 = false, _mut5881 = false, _mut5882 = false, _mut5883 = false, _mut5884 = false, _mut5885 = false, _mut5886 = false, _mut5887 = false, _mut5888 = false, _mut5889 = false, _mut5890 = false, _mut5891 = false, _mut5892 = false, _mut5893 = false, _mut5894 = false, _mut5895 = false, _mut5896 = false, _mut5897 = false, _mut5898 = false, _mut5899 = false, _mut5900 = false, _mut5901 = false, _mut5902 = false, _mut5903 = false, _mut5904 = false, _mut5905 = false, _mut5906 = false, _mut5907 = false, _mut5908 = false, _mut5909 = false, _mut5910 = false, _mut5911 = false, _mut5912 = false, _mut5913 = false, _mut5914 = false, _mut5915 = false, _mut5916 = false, _mut5917 = false, _mut5918 = false, _mut5919 = false, _mut5920 = false, _mut5921 = false, _mut5922 = false, _mut5923 = false, _mut5924 = false, _mut5925 = false, _mut5926 = false, _mut5927 = false, _mut5928 = false, _mut5929 = false, _mut5930 = false, _mut5931 = false, _mut5932 = false, _mut5933 = false, _mut5934 = false, _mut5935 = false, _mut5936 = false, _mut5937 = false, _mut5938 = false, _mut5939 = false, _mut5940 = false, _mut5941 = false, _mut5942 = false, _mut5943 = false, _mut5944 = false, _mut5945 = false, _mut5946 = false, _mut5947 = false, _mut5948 = false, _mut5949 = false, _mut5950 = false, _mut5951 = false, _mut5952 = false, _mut5953 = false, _mut5954 = false, _mut5955 = false, _mut5956 = false, _mut5957 = false, _mut5958 = false, _mut5959 = false, _mut5960 = false, _mut5961 = false;

    /**
     * Computes the <a href="http://en.wikipedia.org/wiki/G-test">G statistic
     * for Goodness of Fit</a> comparing {@code observed} and {@code expected}
     * frequency counts.
     *
     * <p>This statistic can be used to perform a G test (Log-Likelihood Ratio
     * Test) evaluating the null hypothesis that the observed counts follow the
     * expected distribution.</p>
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive. </li>
     * <li>Observed counts must all be &ge; 0. </li>
     * <li>The observed and expected arrays must have the same length and their
     * common length must be at least 2. </li></ul></p>
     *
     * <p>If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * <p><strong>Note:</strong>This implementation rescales the
     * {@code expected} array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return G-Test statistic
     * @throws NotPositiveException if {@code observed} has negative entries
     * @throws NotStrictlyPositiveException if {@code expected} has entries that
     * are not strictly positive
     * @throws DimensionMismatchException if the array lengths do not match or
     * are less than 2.
     */
    public double g(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.g_76");
        if (ROR_less(expected.length, 2, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5715, _mut5716, _mut5717, _mut5718, _mut5719)) {
            throw new DimensionMismatchException(expected.length, 2);
        }
        if (ROR_not_equals(expected.length, observed.length, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5720, _mut5721, _mut5722, _mut5723, _mut5724)) {
            throw new DimensionMismatchException(expected.length, observed.length);
        }
        MathArrays.checkPositive(expected);
        MathArrays.checkNonNegative(observed);
        double sumExpected = 0d;
        double sumObserved = 0d;
        for (int i = 0; ROR_less(i, observed.length, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5725, _mut5726, _mut5727, _mut5728, _mut5729); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.g_76");
            sumExpected += expected[i];
            sumObserved += observed[i];
        }
        double ratio = 1d;
        boolean rescale = false;
        if (ROR_greater(FastMath.abs(AOR_minus(sumExpected, sumObserved, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5730, _mut5731, _mut5732, _mut5733)), 10E-6, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5734, _mut5735, _mut5736, _mut5737, _mut5738)) {
            ratio = AOR_divide(sumObserved, sumExpected, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5739, _mut5740, _mut5741, _mut5742);
            rescale = true;
        }
        double sum = 0d;
        for (int i = 0; ROR_less(i, observed.length, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5759, _mut5760, _mut5761, _mut5762, _mut5763); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.g_76");
            final double dev = rescale ? FastMath.log(AOR_divide((double) observed[i], (AOR_multiply(ratio, expected[i], "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5747, _mut5748, _mut5749, _mut5750)), "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5751, _mut5752, _mut5753, _mut5754)) : FastMath.log(AOR_divide((double) observed[i], expected[i], "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5743, _mut5744, _mut5745, _mut5746));
            sum += AOR_multiply(((double) observed[i]), dev, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5755, _mut5756, _mut5757, _mut5758);
        }
        return AOR_multiply(2d, sum, "org.apache.commons.math3.stat.inference.GTest.g_76", _mut5764, _mut5765, _mut5766, _mut5767);
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue"> p-value</a>,
     * associated with a G-Test for goodness of fit</a> comparing the
     * {@code observed} frequency counts to those in the {@code expected} array.
     *
     * <p>The number returned is the smallest significance level at which one
     * can reject the null hypothesis that the observed counts conform to the
     * frequency distribution described by the expected counts.</p>
     *
     * <p>The probability returned is the tail probability beyond
     * {@link #g(double[], long[]) g(expected, observed)}
     * in the ChiSquare distribution with degrees of freedom one less than the
     * common length of {@code expected} and {@code observed}.</p>
     *
     * <p> <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive. </li>
     * <li>Observed counts must all be &ge; 0. </li>
     * <li>The observed and expected arrays must have the
     * same length and their common length must be at least 2.</li>
     * </ul></p>
     *
     * <p>If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * <p><strong>Note:</strong>This implementation rescales the
     * {@code expected} array if necessary to ensure that the sum of the
     *  expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return p-value
     * @throws NotPositiveException if {@code observed} has negative entries
     * @throws NotStrictlyPositiveException if {@code expected} has entries that
     * are not strictly positive
     * @throws DimensionMismatchException if the array lengths do not match or
     * are less than 2.
     * @throws MaxCountExceededException if an error occurs computing the
     * p-value.
     */
    public double gTest(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gTest_151");
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, AOR_minus(expected.length, 1.0, "org.apache.commons.math3.stat.inference.GTest.gTest_151", _mut5768, _mut5769, _mut5770, _mut5771));
        return AOR_minus(1.0, distribution.cumulativeProbability(g(expected, observed)), "org.apache.commons.math3.stat.inference.GTest.gTest_151", _mut5772, _mut5773, _mut5774, _mut5775);
    }

    /**
     * Returns the intrinsic (Hardy-Weinberg proportions) p-Value, as described
     * in p64-69 of McDonald, J.H. 2009. Handbook of Biological Statistics
     * (2nd ed.). Sparky House Publishing, Baltimore, Maryland.
     *
     * <p> The probability returned is the tail probability beyond
     * {@link #g(double[], long[]) g(expected, observed)}
     * in the ChiSquare distribution with degrees of freedom two less than the
     * common length of {@code expected} and {@code observed}.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return p-value
     * @throws NotPositiveException if {@code observed} has negative entries
     * @throws NotStrictlyPositiveException {@code expected} has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the array lengths do not match or
     * are less than 2.
     * @throws MaxCountExceededException if an error occurs computing the
     * p-value.
     */
    public double gTestIntrinsic(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gTestIntrinsic_182");
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, AOR_minus(expected.length, 2.0, "org.apache.commons.math3.stat.inference.GTest.gTestIntrinsic_182", _mut5776, _mut5777, _mut5778, _mut5779));
        return AOR_minus(1.0, distribution.cumulativeProbability(g(expected, observed)), "org.apache.commons.math3.stat.inference.GTest.gTestIntrinsic_182", _mut5780, _mut5781, _mut5782, _mut5783);
    }

    /**
     * Performs a G-Test (Log-Likelihood Ratio Test) for goodness of fit
     * evaluating the null hypothesis that the observed counts conform to the
     * frequency distribution described by the expected counts, with
     * significance level {@code alpha}. Returns true iff the null
     * hypothesis can be rejected with {@code 100 * (1 - alpha)} percent confidence.
     *
     * <p><strong>Example:</strong><br> To test the hypothesis that
     * {@code observed} follows {@code expected} at the 99% level,
     * use </p><p>
     * {@code gTest(expected, observed, 0.01)}</p>
     *
     * <p>Returns true iff {@link #gTest(double[], long[])
     *  gTestGoodnessOfFitPValue(expected, observed)} < alpha</p>
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive. </li>
     * <li>Observed counts must all be &ge; 0. </li>
     * <li>The observed and expected arrays must have the same length and their
     * common length must be at least 2.
     * <li> {@code 0 < alpha < 0.5} </li></ul></p>
     *
     * <p>If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * <p><strong>Note:</strong>This implementation rescales the
     * {@code expected} array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence 1 -
     * alpha
     * @throws NotPositiveException if {@code observed} has negative entries
     * @throws NotStrictlyPositiveException if {@code expected} has entries that
     * are not strictly positive
     * @throws DimensionMismatchException if the array lengths do not match or
     * are less than 2.
     * @throws MaxCountExceededException if an error occurs computing the
     * p-value.
     * @throws OutOfRangeException if alpha is not strictly greater than zero
     * and less than or equal to 0.5
     */
    public boolean gTest(final double[] expected, final long[] observed, final double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gTest_236");
        if ((_mut5794 ? ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.GTest.gTest_236", _mut5784, _mut5785, _mut5786, _mut5787, _mut5788)) && (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.GTest.gTest_236", _mut5789, _mut5790, _mut5791, _mut5792, _mut5793))) : ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.GTest.gTest_236", _mut5784, _mut5785, _mut5786, _mut5787, _mut5788)) || (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.GTest.gTest_236", _mut5789, _mut5790, _mut5791, _mut5792, _mut5793))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(gTest(expected, observed), alpha, "org.apache.commons.math3.stat.inference.GTest.gTest_236", _mut5795, _mut5796, _mut5797, _mut5798, _mut5799);
    }

    /**
     * Calculates the <a href=
     * "http://en.wikipedia.org/wiki/Entropy_%28information_theory%29">Shannon
     * entropy</a> for 2 Dimensional Matrix.  The value returned is the entropy
     * of the vector formed by concatenating the rows (or columns) of {@code k}
     * to form a vector. See {@link #entropy(long[])}.
     *
     * @param k 2 Dimensional Matrix of long values (for ex. the counts of a
     * trials)
     * @return Shannon Entropy of the given Matrix
     */
    private double entropy(final long[][] k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_260");
        double h = 0d;
        double sum_k = 0d;
        for (int i = 0; ROR_less(i, k.length, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5805, _mut5806, _mut5807, _mut5808, _mut5809); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_260");
            for (int j = 0; ROR_less(j, k[i].length, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5800, _mut5801, _mut5802, _mut5803, _mut5804); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_260");
                sum_k += (double) k[i][j];
            }
        }
        for (int i = 0; ROR_less(i, k.length, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5828, _mut5829, _mut5830, _mut5831, _mut5832); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_260");
            for (int j = 0; ROR_less(j, k[i].length, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5823, _mut5824, _mut5825, _mut5826, _mut5827); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_260");
                if (ROR_not_equals(k[i][j], 0, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5810, _mut5811, _mut5812, _mut5813, _mut5814)) {
                    final double p_ij = AOR_divide((double) k[i][j], sum_k, "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5815, _mut5816, _mut5817, _mut5818);
                    h += AOR_multiply(p_ij, FastMath.log(p_ij), "org.apache.commons.math3.stat.inference.GTest.entropy_260", _mut5819, _mut5820, _mut5821, _mut5822);
                }
            }
        }
        return -h;
    }

    /**
     * Calculates the <a href="http://en.wikipedia.org/wiki/Entropy_%28information_theory%29">
     * Shannon entropy</a> for a vector.  The values of {@code k} are taken to be
     * incidence counts of the values of a random variable. What is returned is <br/>
     * &sum;p<sub>i</sub>log(p<sub>i</sub><br/>
     * where p<sub>i</sub> = k[i] / (sum of elements in k)
     *
     * @param k Vector (for ex. Row Sums of a trials)
     * @return Shannon Entropy of the given Vector
     */
    private double entropy(final long[] k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_290");
        double h = 0d;
        double sum_k = 0d;
        for (int i = 0; ROR_less(i, k.length, "org.apache.commons.math3.stat.inference.GTest.entropy_290", _mut5833, _mut5834, _mut5835, _mut5836, _mut5837); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_290");
            sum_k += (double) k[i];
        }
        for (int i = 0; ROR_less(i, k.length, "org.apache.commons.math3.stat.inference.GTest.entropy_290", _mut5851, _mut5852, _mut5853, _mut5854, _mut5855); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.entropy_290");
            if (ROR_not_equals(k[i], 0, "org.apache.commons.math3.stat.inference.GTest.entropy_290", _mut5838, _mut5839, _mut5840, _mut5841, _mut5842)) {
                final double p_i = AOR_divide((double) k[i], sum_k, "org.apache.commons.math3.stat.inference.GTest.entropy_290", _mut5843, _mut5844, _mut5845, _mut5846);
                h += AOR_multiply(p_i, FastMath.log(p_i), "org.apache.commons.math3.stat.inference.GTest.entropy_290", _mut5847, _mut5848, _mut5849, _mut5850);
            }
        }
        return -h;
    }

    /**
     * <p>Computes a G (Log-Likelihood Ratio) two sample test statistic for
     * independence comparing frequency counts in
     * {@code observed1} and {@code observed2}. The sums of frequency
     * counts in the two samples are not required to be the same. The formula
     * used to compute the test statistic is </p>
     *
     * <p>{@code 2 * totalSum * [H(rowSums) + H(colSums) - H(k)]}</p>
     *
     * <p> where {@code H} is the
     * <a href="http://en.wikipedia.org/wiki/Entropy_%28information_theory%29">
     * Shannon Entropy</a> of the random variable formed by viewing the elements
     * of the argument array as incidence counts; <br/>
     * {@code k} is a matrix with rows {@code [observed1, observed2]}; <br/>
     * {@code rowSums, colSums} are the row/col sums of {@code k}; <br>
     * and {@code totalSum} is the overall sum of all entries in {@code k}.</p>
     *
     * <p>This statistic can be used to perform a G test evaluating the null
     * hypothesis that both observed counts are independent </p>
     *
     * <p> <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative. </li>
     * <li>Observed counts for a specific bin must not both be zero. </li>
     * <li>Observed counts for a specific sample must not all be  0. </li>
     * <li>The arrays {@code observed1} and {@code observed2} must have
     * the same length and their common length must be at least 2. </li></ul></p>
     *
     * <p>If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data
     * set
     * @return G-Test statistic
     * @throws DimensionMismatchException the the lengths of the arrays do not
     * match or their common length is less than 2
     * @throws NotPositiveException if any entry in {@code observed1} or
     * {@code observed2} is negative
     * @throws ZeroException if either all counts of
     * {@code observed1} or {@code observed2} are zero, or if the count
     * at the same index is zero for both arrays.
     */
    public double gDataSetsComparison(final long[] observed1, final long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347");
        // Make sure lengths are same
        if (ROR_less(observed1.length, 2, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5856, _mut5857, _mut5858, _mut5859, _mut5860)) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (ROR_not_equals(observed1.length, observed2.length, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5861, _mut5862, _mut5863, _mut5864, _mut5865)) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }
        // Ensure non-negative counts
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);
        // Compute and compare count sums
        long countSum1 = 0;
        long countSum2 = 0;
        // Compute and compare count sums
        final long[] collSums = new long[observed1.length];
        final long[][] k = new long[2][observed1.length];
        for (int i = 0; ROR_less(i, observed1.length, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5881, _mut5882, _mut5883, _mut5884, _mut5885); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347");
            if ((_mut5876 ? (ROR_equals(observed1[i], 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5866, _mut5867, _mut5868, _mut5869, _mut5870) || ROR_equals(observed2[i], 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5871, _mut5872, _mut5873, _mut5874, _mut5875)) : (ROR_equals(observed1[i], 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5866, _mut5867, _mut5868, _mut5869, _mut5870) && ROR_equals(observed2[i], 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5871, _mut5872, _mut5873, _mut5874, _mut5875)))) {
                throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, i);
            } else {
                countSum1 += observed1[i];
                countSum2 += observed2[i];
                collSums[i] = AOR_plus(observed1[i], observed2[i], "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5877, _mut5878, _mut5879, _mut5880);
                k[0][i] = observed1[i];
                k[1][i] = observed2[i];
            }
        }
        // Ensure neither sample is uniformly 0
        if ((_mut5896 ? (ROR_equals(countSum1, 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5886, _mut5887, _mut5888, _mut5889, _mut5890) && ROR_equals(countSum2, 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5891, _mut5892, _mut5893, _mut5894, _mut5895)) : (ROR_equals(countSum1, 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5886, _mut5887, _mut5888, _mut5889, _mut5890) || ROR_equals(countSum2, 0, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5891, _mut5892, _mut5893, _mut5894, _mut5895)))) {
            throw new ZeroException();
        }
        final long[] rowSums = { countSum1, countSum2 };
        final double sum = AOR_plus((double) countSum1, (double) countSum2, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5897, _mut5898, _mut5899, _mut5900);
        return AOR_multiply(AOR_multiply(2, sum, "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5901, _mut5902, _mut5903, _mut5904), (AOR_minus(AOR_plus(entropy(rowSums), entropy(collSums), "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5905, _mut5906, _mut5907, _mut5908), entropy(k), "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5909, _mut5910, _mut5911, _mut5912)), "org.apache.commons.math3.stat.inference.GTest.gDataSetsComparison_347", _mut5913, _mut5914, _mut5915, _mut5916);
    }

    /**
     * Calculates the root log-likelihood ratio for 2 state Datasets. See
     * {@link #gDataSetsComparison(long[], long[] )}.
     *
     * <p>Given two events A and B, let k11 be the number of times both events
     * occur, k12 the incidence of B without A, k21 the count of A without B,
     * and k22 the number of times neither A nor B occurs.  What is returned
     * by this method is </p>
     *
     * <p>{@code (sgn) sqrt(gValueDataSetsComparison({k11, k12}, {k21, k22})}</p>
     *
     * <p>where {@code sgn} is -1 if {@code k11 / (k11 + k12) < k21 / (k21 + k22))};<br/>
     * 1 otherwise.</p>
     *
     * <p>Signed root LLR has two advantages over the basic LLR: a) it is positive
     * where k11 is bigger than expected, negative where it is lower b) if there is
     * no difference it is asymptotically normally distributed. This allows one
     * to talk about "number of standard deviations" which is a more common frame
     * of reference than the chi^2 distribution.</p>
     *
     * @param k11 number of times the two events occurred together (AB)
     * @param k12 number of times the second event occurred WITHOUT the
     * first event (notA,B)
     * @param k21 number of times the first event occurred WITHOUT the
     * second event (A, notB)
     * @param k22 number of times something else occurred (i.e. was neither
     * of these events (notA, notB)
     * @return root log-likelihood ratio
     */
    public double rootLogLikelihoodRatio(final long k11, long k12, final long k21, final long k22) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420");
        final double llr = gDataSetsComparison(new long[] { k11, k12 }, new long[] { k21, k22 });
        double sqrt = FastMath.sqrt(llr);
        if (ROR_less(AOR_divide((double) k11, (AOR_plus(k11, k12, "org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420", _mut5917, _mut5918, _mut5919, _mut5920)), "org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420", _mut5921, _mut5922, _mut5923, _mut5924), AOR_divide((double) k21, (AOR_plus(k21, k22, "org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420", _mut5925, _mut5926, _mut5927, _mut5928)), "org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420", _mut5929, _mut5930, _mut5931, _mut5932), "org.apache.commons.math3.stat.inference.GTest.rootLogLikelihoodRatio_420", _mut5933, _mut5934, _mut5935, _mut5936, _mut5937)) {
            sqrt = -sqrt;
        }
        return sqrt;
    }

    /**
     * <p>Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a G-Value (Log-Likelihood Ratio) for two
     * sample test comparing bin frequency counts in {@code observed1} and
     * {@code observed2}.</p>
     *
     * <p>The number returned is the smallest significance level at which one
     * can reject the null hypothesis that the observed counts conform to the
     * same distribution. </p>
     *
     * <p>See {@link #gTest(double[], long[])} for details
     * on how the p-value is computed.  The degrees of of freedom used to
     * perform the test is one less than the common length of the input observed
     * count arrays.</p>
     *
     * <p><strong>Preconditions</strong>:
     * <ul> <li>Observed counts must be non-negative. </li>
     * <li>Observed counts for a specific bin must not both be zero. </li>
     * <li>Observed counts for a specific sample must not all be 0. </li>
     * <li>The arrays {@code observed1} and {@code observed2} must
     * have the same length and their common length must be at least 2. </li>
     * </ul><p>
     * <p> If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data
     * set
     * @return p-value
     * @throws DimensionMismatchException the the length of the arrays does not
     * match or their common length is less than 2
     * @throws NotPositiveException if any of the entries in {@code observed1} or
     * {@code observed2} are negative
     * @throws ZeroException if either all counts of {@code observed1} or
     * {@code observed2} are zero, or if the count at some index is
     * zero for both arrays
     * @throws MaxCountExceededException if an error occurs computing the
     * p-value.
     */
    public double gTestDataSetsComparison(final long[] observed1, final long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_471");
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, AOR_minus((double) observed1.length, 1, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_471", _mut5938, _mut5939, _mut5940, _mut5941));
        return AOR_minus(1, distribution.cumulativeProbability(gDataSetsComparison(observed1, observed2)), "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_471", _mut5942, _mut5943, _mut5944, _mut5945);
    }

    /**
     * <p>Performs a G-Test (Log-Likelihood Ratio Test) comparing two binned
     * data sets. The test evaluates the null hypothesis that the two lists
     * of observed counts conform to the same frequency distribution, with
     * significance level {@code alpha}. Returns true iff the null
     * hypothesis can be rejected  with 100 * (1 - alpha) percent confidence.
     * </p>
     * <p>See {@link #gDataSetsComparison(long[], long[])} for details
     * on the formula used to compute the G (LLR) statistic used in the test and
     * {@link #gTest(double[], long[])} for information on how
     * the observed significance level is computed. The degrees of of freedom used
     * to perform the test is one less than the common length of the input observed
     * count arrays. </p>
     *
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative. </li>
     * <li>Observed counts for a specific bin must not both be zero. </li>
     * <li>Observed counts for a specific sample must not all be 0. </li>
     * <li>The arrays {@code observed1} and {@code observed2} must
     * have the same length and their common length must be at least 2. </li>
     * <li>{@code 0 < alpha < 0.5} </li></ul></p>
     *
     * <p>If any of the preconditions are not met, a
     * {@code MathIllegalArgumentException} is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data
     * set
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence 1 -
     * alpha
     * @throws DimensionMismatchException the the length of the arrays does not
     * match
     * @throws NotPositiveException if any of the entries in {@code observed1} or
     * {@code observed2} are negative
     * @throws ZeroException if either all counts of {@code observed1} or
     * {@code observed2} are zero, or if the count at some index is
     * zero for both arrays
     * @throws OutOfRangeException if {@code alpha} is not in the range
     * (0, 0.5]
     * @throws MaxCountExceededException if an error occurs performing the test
     */
    public boolean gTestDataSetsComparison(final long[] observed1, final long[] observed2, final double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525");
        if ((_mut5956 ? (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525", _mut5946, _mut5947, _mut5948, _mut5949, _mut5950) && ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525", _mut5951, _mut5952, _mut5953, _mut5954, _mut5955)) : (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525", _mut5946, _mut5947, _mut5948, _mut5949, _mut5950) || ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525", _mut5951, _mut5952, _mut5953, _mut5954, _mut5955)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(gTestDataSetsComparison(observed1, observed2), alpha, "org.apache.commons.math3.stat.inference.GTest.gTestDataSetsComparison_525", _mut5957, _mut5958, _mut5959, _mut5960, _mut5961);
    }
}
