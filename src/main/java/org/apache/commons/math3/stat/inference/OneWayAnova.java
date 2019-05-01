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

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements one-way ANOVA (analysis of variance) statistics.
 *
 * <p> Tests for differences between two or more categories of univariate data
 * (for example, the body mass index of accountants, lawyers, doctors and
 * computer programmers).  When two categories are given, this is equivalent to
 * the {@link org.apache.commons.math3.stat.inference.TTest}.
 * </p><p>
 * Uses the {@link org.apache.commons.math3.distribution.FDistribution
 * commons-math F Distribution implementation} to estimate exact p-values.</p>
 * <p>This implementation is based on a description at
 * http://faculty.vassar.edu/lowry/ch13pt1.html</p>
 * <pre>
 * Abbreviations: bg = between groups,
 *                wg = within groups,
 *                ss = sum squared deviations
 * </pre>
 *
 * @since 1.2
 */
public class OneWayAnova {

    @Conditional
    public static boolean _mut5633 = false, _mut5634 = false, _mut5635 = false, _mut5636 = false, _mut5637 = false, _mut5638 = false, _mut5639 = false, _mut5640 = false, _mut5641 = false, _mut5642 = false, _mut5643 = false, _mut5644 = false, _mut5645 = false, _mut5646 = false, _mut5647 = false, _mut5648 = false, _mut5649 = false, _mut5650 = false, _mut5651 = false, _mut5652 = false, _mut5653 = false, _mut5654 = false, _mut5655 = false, _mut5656 = false, _mut5657 = false, _mut5658 = false, _mut5659 = false, _mut5660 = false, _mut5661 = false, _mut5662 = false, _mut5663 = false, _mut5664 = false, _mut5665 = false, _mut5666 = false, _mut5667 = false, _mut5668 = false, _mut5669 = false, _mut5670 = false, _mut5671 = false, _mut5672 = false, _mut5673 = false, _mut5674 = false, _mut5675 = false, _mut5676 = false, _mut5677 = false, _mut5678 = false, _mut5679 = false, _mut5680 = false, _mut5681 = false, _mut5682 = false, _mut5683 = false, _mut5684 = false, _mut5685 = false, _mut5686 = false, _mut5687 = false, _mut5688 = false, _mut5689 = false, _mut5690 = false, _mut5691 = false, _mut5692 = false, _mut5693 = false, _mut5694 = false, _mut5695 = false, _mut5696 = false, _mut5697 = false, _mut5698 = false, _mut5699 = false, _mut5700 = false, _mut5701 = false, _mut5702 = false, _mut5703 = false, _mut5704 = false, _mut5705 = false, _mut5706 = false, _mut5707 = false, _mut5708 = false, _mut5709 = false, _mut5710 = false, _mut5711 = false, _mut5712 = false, _mut5713 = false, _mut5714 = false;

    /**
     * Default constructor.
     */
    public OneWayAnova() {
    }

    /**
     * Computes the ANOVA F-value for a collection of <code>double[]</code>
     * arrays.
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>The categoryData <code>Collection</code> must contain
     * <code>double[]</code> arrays.</li>
     * <li> There must be at least two <code>double[]</code> arrays in the
     * <code>categoryData</code> collection and each of these arrays must
     * contain at least two values.</li></ul></p><p>
     * This implementation computes the F statistic using the definitional
     * formula<pre>
     *   F = msbg/mswg</pre>
     * where<pre>
     *  msbg = between group mean square
     *  mswg = within group mean square</pre>
     * are as defined <a href="http://faculty.vassar.edu/lowry/ch13pt1.html">
     * here</a></p>
     *
     * @param categoryData <code>Collection</code> of <code>double[]</code>
     * arrays each containing data for one category
     * @return Fvalue
     * @throws NullArgumentException if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException if the length of the <code>categoryData</code>
     * array is less than 2 or a contained <code>double[]</code> array does not have
     * at least two values
     */
    public double anovaFValue(final Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
        AnovaStats a = anovaStats(categoryData);
        return a.F;
    }

    /**
     * Computes the ANOVA P-value for a collection of <code>double[]</code>
     * arrays.
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>The categoryData <code>Collection</code> must contain
     * <code>double[]</code> arrays.</li>
     * <li> There must be at least two <code>double[]</code> arrays in the
     * <code>categoryData</code> collection and each of these arrays must
     * contain at least two values.</li></ul></p><p>
     * This implementation uses the
     * {@link org.apache.commons.math3.distribution.FDistribution
     * commons-math F Distribution implementation} to estimate the exact
     * p-value, using the formula<pre>
     *   p = 1 - cumulativeProbability(F)</pre>
     * where <code>F</code> is the F value and <code>cumulativeProbability</code>
     * is the commons-math implementation of the F distribution.</p>
     *
     * @param categoryData <code>Collection</code> of <code>double[]</code>
     * arrays each containing data for one category
     * @return Pvalue
     * @throws NullArgumentException if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException if the length of the <code>categoryData</code>
     * array is less than 2 or a contained <code>double[]</code> array does not have
     * at least two values
     * @throws ConvergenceException if the p-value can not be computed due to a convergence error
     * @throws MaxCountExceededException if the maximum number of iterations is exceeded
     */
    public double anovaPValue(final Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaPValue_123");
        final AnovaStats a = anovaStats(categoryData);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final FDistribution fdist = new FDistribution(null, a.dfbg, a.dfwg);
        return AOR_minus(1.0, fdist.cumulativeProbability(a.F), "org.apache.commons.math3.stat.inference.OneWayAnova.anovaPValue_123", _mut5633, _mut5634, _mut5635, _mut5636);
    }

    /**
     * Computes the ANOVA P-value for a collection of {@link SummaryStatistics}.
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>The categoryData <code>Collection</code> must contain
     * {@link SummaryStatistics}.</li>
     * <li> There must be at least two {@link SummaryStatistics} in the
     * <code>categoryData</code> collection and each of these statistics must
     * contain at least two values.</li></ul></p><p>
     * This implementation uses the
     * {@link org.apache.commons.math3.distribution.FDistribution
     * commons-math F Distribution implementation} to estimate the exact
     * p-value, using the formula<pre>
     *   p = 1 - cumulativeProbability(F)</pre>
     * where <code>F</code> is the F value and <code>cumulativeProbability</code>
     * is the commons-math implementation of the F distribution.</p>
     *
     * @param categoryData <code>Collection</code> of {@link SummaryStatistics}
     * each containing data for one category
     * @param allowOneElementData if true, allow computation for one catagory
     * only or for one data element per category
     * @return Pvalue
     * @throws NullArgumentException if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException if the length of the <code>categoryData</code>
     * array is less than 2 or a contained {@link SummaryStatistics} does not have
     * at least two values
     * @throws ConvergenceException if the p-value can not be computed due to a convergence error
     * @throws MaxCountExceededException if the maximum number of iterations is exceeded
     * @since 3.2
     */
    public double anovaPValue(final Collection<SummaryStatistics> categoryData, final boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException, ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaPValue_165");
        final AnovaStats a = anovaStats(categoryData, allowOneElementData);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final FDistribution fdist = new FDistribution(null, a.dfbg, a.dfwg);
        return AOR_minus(1.0, fdist.cumulativeProbability(a.F), "org.apache.commons.math3.stat.inference.OneWayAnova.anovaPValue_165", _mut5637, _mut5638, _mut5639, _mut5640);
    }

    /**
     * This method calls the method that actually does the calculations (except
     * P-value).
     *
     * @param categoryData
     *            <code>Collection</code> of <code>double[]</code> arrays each
     *            containing data for one category
     * @return computed AnovaStats
     * @throws NullArgumentException
     *             if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException
     *             if the length of the <code>categoryData</code> array is less
     *             than 2 or a contained <code>double[]</code> array does not
     *             contain at least two values
     */
    private AnovaStats anovaStats(final Collection<double[]> categoryData) throws NullArgumentException, DimensionMismatchException {
        MathUtils.checkNotNull(categoryData);
        final Collection<SummaryStatistics> categoryDataSummaryStatistics = new ArrayList<SummaryStatistics>(categoryData.size());
        // convert arrays to SummaryStatistics
        for (final double[] data : categoryData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_192");
            final SummaryStatistics dataSummaryStatistics = new SummaryStatistics();
            categoryDataSummaryStatistics.add(dataSummaryStatistics);
            for (final double val : data) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_192");
                dataSummaryStatistics.addValue(val);
            }
        }
        return anovaStats(categoryDataSummaryStatistics, false);
    }

    /**
     * Performs an ANOVA test, evaluating the null hypothesis that there
     * is no difference among the means of the data categories.
     *
     * <p><strong>Preconditions</strong>: <ul>
     * <li>The categoryData <code>Collection</code> must contain
     * <code>double[]</code> arrays.</li>
     * <li> There must be at least two <code>double[]</code> arrays in the
     * <code>categoryData</code> collection and each of these arrays must
     * contain at least two values.</li>
     * <li>alpha must be strictly greater than 0 and less than or equal to 0.5.
     * </li></ul></p><p>
     * This implementation uses the
     * {@link org.apache.commons.math3.distribution.FDistribution
     * commons-math F Distribution implementation} to estimate the exact
     * p-value, using the formula<pre>
     *   p = 1 - cumulativeProbability(F)</pre>
     * where <code>F</code> is the F value and <code>cumulativeProbability</code>
     * is the commons-math implementation of the F distribution.</p>
     * <p>True is returned iff the estimated p-value is less than alpha.</p>
     *
     * @param categoryData <code>Collection</code> of <code>double[]</code>
     * arrays each containing data for one category
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with
     * confidence 1 - alpha
     * @throws NullArgumentException if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException if the length of the <code>categoryData</code>
     * array is less than 2 or a contained <code>double[]</code> array does not have
     * at least two values
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws ConvergenceException if the p-value can not be computed due to a convergence error
     * @throws MaxCountExceededException if the maximum number of iterations is exceeded
     */
    public boolean anovaTest(final Collection<double[]> categoryData, final double alpha) throws NullArgumentException, DimensionMismatchException, OutOfRangeException, ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247");
        if ((_mut5651 ? ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247", _mut5641, _mut5642, _mut5643, _mut5644, _mut5645)) && (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247", _mut5646, _mut5647, _mut5648, _mut5649, _mut5650))) : ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247", _mut5641, _mut5642, _mut5643, _mut5644, _mut5645)) || (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247", _mut5646, _mut5647, _mut5648, _mut5649, _mut5650))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(anovaPValue(categoryData), alpha, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaTest_247", _mut5652, _mut5653, _mut5654, _mut5655, _mut5656);
    }

    /**
     * This method actually does the calculations (except P-value).
     *
     * @param categoryData <code>Collection</code> of <code>double[]</code>
     * arrays each containing data for one category
     * @param allowOneElementData if true, allow computation for one catagory
     * only or for one data element per category
     * @return computed AnovaStats
     * @throws NullArgumentException if <code>categoryData</code> is <code>null</code>
     * @throws DimensionMismatchException if <code>allowOneElementData</code> is false and the number of
     * categories is less than 2 or a contained SummaryStatistics does not contain
     * at least two values
     */
    private AnovaStats anovaStats(final Collection<SummaryStatistics> categoryData, final boolean allowOneElementData) throws NullArgumentException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274");
        MathUtils.checkNotNull(categoryData);
        if (!allowOneElementData) {
            // check if we have enough categories
            if (ROR_less(categoryData.size(), 2, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5657, _mut5658, _mut5659, _mut5660, _mut5661)) {
                throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_CATEGORIES_REQUIRED, categoryData.size(), 2);
            }
            // check if each category has enough data
            for (final SummaryStatistics array : categoryData) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274");
                if (ROR_less_equals(array.getN(), 1, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5662, _mut5663, _mut5664, _mut5665, _mut5666)) {
                    throw new DimensionMismatchException(LocalizedFormats.TWO_OR_MORE_VALUES_IN_CATEGORY_REQUIRED, (int) array.getN(), 2);
                }
            }
        }
        int dfwg = 0;
        double sswg = 0;
        double totsum = 0;
        double totsumsq = 0;
        int totnum = 0;
        for (final SummaryStatistics data : categoryData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274");
            final double sum = data.getSum();
            final double sumsq = data.getSumsq();
            final int num = (int) data.getN();
            totnum += num;
            totsum += sum;
            totsumsq += sumsq;
            dfwg += AOR_minus(num, 1, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5667, _mut5668, _mut5669, _mut5670);
            final double ss = AOR_minus(sumsq, (AOR_divide((AOR_multiply(sum, sum, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5671, _mut5672, _mut5673, _mut5674)), num, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5675, _mut5676, _mut5677, _mut5678)), "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5679, _mut5680, _mut5681, _mut5682);
            sswg += ss;
        }
        final double sst = AOR_minus(totsumsq, (AOR_divide((AOR_multiply(totsum, totsum, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5683, _mut5684, _mut5685, _mut5686)), totnum, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5687, _mut5688, _mut5689, _mut5690)), "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5691, _mut5692, _mut5693, _mut5694);
        final double ssbg = AOR_minus(sst, sswg, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5695, _mut5696, _mut5697, _mut5698);
        final int dfbg = AOR_minus(categoryData.size(), 1, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5699, _mut5700, _mut5701, _mut5702);
        final double msbg = AOR_divide(ssbg, dfbg, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5703, _mut5704, _mut5705, _mut5706);
        final double mswg = AOR_divide(sswg, dfwg, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5707, _mut5708, _mut5709, _mut5710);
        final double F = AOR_divide(msbg, mswg, "org.apache.commons.math3.stat.inference.OneWayAnova.anovaStats_274", _mut5711, _mut5712, _mut5713, _mut5714);
        return new AnovaStats(dfbg, dfwg, F);
    }

    /**
     *        Convenience class to pass dfbg,dfwg,F values around within OneWayAnova.
     *        No get/set methods provided.
     */
    private static class AnovaStats {

        /**
         * Degrees of freedom in numerator (between groups).
         */
        private final int dfbg;

        /**
         * Degrees of freedom in denominator (within groups).
         */
        private final int dfwg;

        /**
         * Statistic.
         */
        private final double F;

        /**
         * Constructor
         * @param dfbg degrees of freedom in numerator (between groups)
         * @param dfwg degrees of freedom in denominator (within groups)
         * @param F statistic
         */
        private AnovaStats(int dfbg, int dfwg, double F) {
            this.dfbg = dfbg;
            this.dfwg = dfwg;
            this.F = F;
        }
    }
}
