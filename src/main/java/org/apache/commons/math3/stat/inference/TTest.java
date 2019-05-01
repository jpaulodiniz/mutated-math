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

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * An implementation for Student's t-tests.
 * <p>
 * Tests can be:<ul>
 * <li>One-sample or two-sample</li>
 * <li>One-sided or two-sided</li>
 * <li>Paired or unpaired (for two-sample tests)</li>
 * <li>Homoscedastic (equal variance assumption) or heteroscedastic
 * (for two sample tests)</li>
 * <li>Fixed significance level (boolean-valued) or returning p-values.
 * </li></ul></p>
 * <p>
 * Test statistics are available for all tests.  Methods including "Test" in
 * in their names perform tests, all other methods return t-statistics.  Among
 * the "Test" methods, <code>double-</code>valued methods return p-values;
 * <code>boolean-</code>valued methods perform fixed significance level tests.
 * Significance levels are always specified as numbers between 0 and 0.5
 * (e.g. tests at the 95% level  use <code>alpha=0.05</code>).</p>
 * <p>
 * Input to tests can be either <code>double[]</code> arrays or
 * {@link StatisticalSummary} instances.</p><p>
 * Uses commons-math {@link org.apache.commons.math3.distribution.TDistribution}
 * implementation to estimate exact p-values.</p>
 */
public class TTest {

    @Conditional
    public static boolean _mut5394 = false, _mut5395 = false, _mut5396 = false, _mut5397 = false, _mut5398 = false, _mut5399 = false, _mut5400 = false, _mut5401 = false, _mut5402 = false, _mut5403 = false, _mut5404 = false, _mut5405 = false, _mut5406 = false, _mut5407 = false, _mut5408 = false, _mut5409 = false, _mut5410 = false, _mut5411 = false, _mut5412 = false, _mut5413 = false, _mut5414 = false, _mut5415 = false, _mut5416 = false, _mut5417 = false, _mut5418 = false, _mut5419 = false, _mut5420 = false, _mut5421 = false, _mut5422 = false, _mut5423 = false, _mut5424 = false, _mut5425 = false, _mut5426 = false, _mut5427 = false, _mut5428 = false, _mut5429 = false, _mut5430 = false, _mut5431 = false, _mut5432 = false, _mut5433 = false, _mut5434 = false, _mut5435 = false, _mut5436 = false, _mut5437 = false, _mut5438 = false, _mut5439 = false, _mut5440 = false, _mut5441 = false, _mut5442 = false, _mut5443 = false, _mut5444 = false, _mut5445 = false, _mut5446 = false, _mut5447 = false, _mut5448 = false, _mut5449 = false, _mut5450 = false, _mut5451 = false, _mut5452 = false, _mut5453 = false, _mut5454 = false, _mut5455 = false, _mut5456 = false, _mut5457 = false, _mut5458 = false, _mut5459 = false, _mut5460 = false, _mut5461 = false, _mut5462 = false, _mut5463 = false, _mut5464 = false, _mut5465 = false, _mut5466 = false, _mut5467 = false, _mut5468 = false, _mut5469 = false, _mut5470 = false, _mut5471 = false, _mut5472 = false, _mut5473 = false, _mut5474 = false, _mut5475 = false, _mut5476 = false, _mut5477 = false, _mut5478 = false, _mut5479 = false, _mut5480 = false, _mut5481 = false, _mut5482 = false, _mut5483 = false, _mut5484 = false, _mut5485 = false, _mut5486 = false, _mut5487 = false, _mut5488 = false, _mut5489 = false, _mut5490 = false, _mut5491 = false, _mut5492 = false, _mut5493 = false, _mut5494 = false, _mut5495 = false, _mut5496 = false, _mut5497 = false, _mut5498 = false, _mut5499 = false, _mut5500 = false, _mut5501 = false, _mut5502 = false, _mut5503 = false, _mut5504 = false, _mut5505 = false, _mut5506 = false, _mut5507 = false, _mut5508 = false, _mut5509 = false, _mut5510 = false, _mut5511 = false, _mut5512 = false, _mut5513 = false, _mut5514 = false, _mut5515 = false, _mut5516 = false, _mut5517 = false, _mut5518 = false, _mut5519 = false, _mut5520 = false, _mut5521 = false, _mut5522 = false, _mut5523 = false, _mut5524 = false, _mut5525 = false, _mut5526 = false, _mut5527 = false, _mut5528 = false, _mut5529 = false, _mut5530 = false, _mut5531 = false, _mut5532 = false, _mut5533 = false, _mut5534 = false, _mut5535 = false, _mut5536 = false, _mut5537 = false, _mut5538 = false, _mut5539 = false, _mut5540 = false, _mut5541 = false, _mut5542 = false, _mut5543 = false, _mut5544 = false, _mut5545 = false, _mut5546 = false, _mut5547 = false, _mut5548 = false, _mut5549 = false, _mut5550 = false, _mut5551 = false, _mut5552 = false, _mut5553 = false, _mut5554 = false, _mut5555 = false, _mut5556 = false, _mut5557 = false, _mut5558 = false, _mut5559 = false, _mut5560 = false, _mut5561 = false, _mut5562 = false, _mut5563 = false, _mut5564 = false, _mut5565 = false, _mut5566 = false, _mut5567 = false, _mut5568 = false, _mut5569 = false, _mut5570 = false, _mut5571 = false, _mut5572 = false, _mut5573 = false, _mut5574 = false, _mut5575 = false, _mut5576 = false, _mut5577 = false, _mut5578 = false, _mut5579 = false, _mut5580 = false, _mut5581 = false, _mut5582 = false, _mut5583 = false, _mut5584 = false, _mut5585 = false, _mut5586 = false, _mut5587 = false, _mut5588 = false, _mut5589 = false, _mut5590 = false, _mut5591 = false, _mut5592 = false, _mut5593 = false, _mut5594 = false, _mut5595 = false, _mut5596 = false, _mut5597 = false, _mut5598 = false, _mut5599 = false, _mut5600 = false, _mut5601 = false, _mut5602 = false, _mut5603 = false, _mut5604 = false, _mut5605 = false, _mut5606 = false, _mut5607 = false, _mut5608 = false, _mut5609 = false, _mut5610 = false, _mut5611 = false, _mut5612 = false, _mut5613 = false, _mut5614 = false, _mut5615 = false, _mut5616 = false, _mut5617 = false, _mut5618 = false, _mut5619 = false, _mut5620 = false, _mut5621 = false, _mut5622 = false, _mut5623 = false, _mut5624 = false, _mut5625 = false, _mut5626 = false, _mut5627 = false, _mut5628 = false, _mut5629 = false, _mut5630 = false, _mut5631 = false, _mut5632 = false;

    /**
     * Computes a paired, 2-sample t-statistic based on the data in the input
     * arrays.  The t-statistic returned is equivalent to what would be returned by
     * computing the one-sample t-statistic {@link #t(double, double[])}, with
     * <code>mu = 0</code> and the sample array consisting of the (signed)
     * differences between corresponding entries in <code>sample1</code> and
     * <code>sample2.</code>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The input arrays must have the same length and their common length
     * must be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return t statistic
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NoDataException if the arrays are empty
     * @throws DimensionMismatchException if the length of the arrays is not equal
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     */
    public double pairedT(final double[] sample1, final double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException {
        checkSampleData(sample1);
        checkSampleData(sample2);
        double meanDifference = StatUtils.meanDifference(sample1, sample2);
        return t(meanDifference, 0, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i> p-value</i>, associated with a paired, two-sample, two-tailed t-test
     * based on the data in the input arrays.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the mean of the paired
     * differences is 0 in favor of the two-sided alternative that the mean paired
     * difference is not equal to 0. For a one-sided test, divide the returned
     * value by 2.</p>
     * <p>
     * This test is equivalent to a one-sample t-test computed using
     * {@link #tTest(double, double[])} with <code>mu = 0</code> and the sample
     * array consisting of the signed differences between corresponding elements of
     * <code>sample1</code> and <code>sample2.</code></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the p-value depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The input array lengths must be the same and their common length must
     * be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return p-value for t-test
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NoDataException if the arrays are empty
     * @throws DimensionMismatchException if the length of the arrays is not equal
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double pairedTTest(final double[] sample1, final double[] sample2) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException {
        double meanDifference = StatUtils.meanDifference(sample1, sample2);
        return tTest(meanDifference, 0, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
    }

    /**
     * Performs a paired t-test evaluating the null hypothesis that the
     * mean of the paired differences between <code>sample1</code> and
     * <code>sample2</code> is 0 in favor of the two-sided alternative that the
     * mean paired difference is not equal to 0, with significance level
     * <code>alpha</code>.
     * <p>
     * Returns <code>true</code> iff the null hypothesis can be rejected with
     * confidence <code>1 - alpha</code>.  To perform a 1-sided test, use
     * <code>alpha * 2</code></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The input array lengths must be the same and their common length
     * must be at least 2.
     * </li>
     * <li> <code> 0 &lt; alpha &lt; 0.5 </code>
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with
     * confidence 1 - alpha
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NoDataException if the arrays are empty
     * @throws DimensionMismatchException if the length of the arrays is not equal
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean pairedTTest(final double[] sample1, final double[] sample2, final double alpha) throws NullArgumentException, NoDataException, DimensionMismatchException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.pairedTTest_176");
        checkSignificanceLevel(alpha);
        return ROR_less(pairedTTest(sample1, sample2), alpha, "org.apache.commons.math3.stat.inference.TTest.pairedTTest_176", _mut5394, _mut5395, _mut5396, _mut5397, _mut5398);
    }

    /**
     * Computes a <a href="http://www.itl.nist.gov/div898/handbook/prc/section2/prc22.htm#formula">
     * t statistic </a> given observed values and a comparison constant.
     * <p>
     * This statistic can be used to perform a one sample t-test for the mean.
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array length must be at least 2.
     * </li></ul></p>
     *
     * @param mu comparison constant
     * @param observed array of values
     * @return t statistic
     * @throws NullArgumentException if <code>observed</code> is <code>null</code>
     * @throws NumberIsTooSmallException if the length of <code>observed</code> is &lt; 2
     */
    public double t(final double mu, final double[] observed) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(observed);
        // No try-catch or advertised exception because args have just been checked
        return t(StatUtils.mean(observed), mu, StatUtils.variance(observed), observed.length);
    }

    /**
     * Computes a <a href="http://www.itl.nist.gov/div898/handbook/prc/section2/prc22.htm#formula">
     * t statistic </a> to use in comparing the mean of the dataset described by
     * <code>sampleStats</code> to <code>mu</code>.
     * <p>
     * This statistic can be used to perform a one sample t-test for the mean.
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li><code>observed.getN() &ge; 2</code>.
     * </li></ul></p>
     *
     * @param mu comparison constant
     * @param sampleStats DescriptiveStatistics holding sample summary statitstics
     * @return t statistic
     * @throws NullArgumentException if <code>sampleStats</code> is <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     */
    public double t(final double mu, final StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(sampleStats);
        return t(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
    }

    /**
     * Computes a 2-sample t statistic,  under the hypothesis of equal
     * subpopulation variances.  To compute a t-statistic without the
     * equal variances hypothesis, use {@link #t(double[], double[])}.
     * <p>
     * This statistic can be used to perform a (homoscedastic) two-sample
     * t-test to compare sample means.</p>
     * <p>
     * The t-statistic is</p>
     * <p>
     * &nbsp;&nbsp;<code>  t = (m1 - m2) / (sqrt(1/n1 +1/n2) sqrt(var))</code>
     * </p><p>
     * where <strong><code>n1</code></strong> is the size of first sample;
     * <strong><code> n2</code></strong> is the size of second sample;
     * <strong><code> m1</code></strong> is the mean of first sample;
     * <strong><code> m2</code></strong> is the mean of second sample</li>
     * </ul>
     * and <strong><code>var</code></strong> is the pooled variance estimate:
     * </p><p>
     * <code>var = sqrt(((n1 - 1)var1 + (n2 - 1)var2) / ((n1-1) + (n2-1)))</code>
     * </p><p>
     * with <strong><code>var1</code></strong> the variance of the first sample and
     * <strong><code>var2</code></strong> the variance of the second sample.
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return t statistic
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     */
    public double homoscedasticT(final double[] sample1, final double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(sample1);
        checkSampleData(sample2);
        // No try-catch or advertised exception because args have just been checked
        return homoscedasticT(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    /**
     * Computes a 2-sample t statistic, without the hypothesis of equal
     * subpopulation variances.  To compute a t-statistic assuming equal
     * variances, use {@link #homoscedasticT(double[], double[])}.
     * <p>
     * This statistic can be used to perform a two-sample t-test to compare
     * sample means.</p>
     * <p>
     * The t-statistic is</p>
     * <p>
     * &nbsp;&nbsp; <code>  t = (m1 - m2) / sqrt(var1/n1 + var2/n2)</code>
     * </p><p>
     *  where <strong><code>n1</code></strong> is the size of the first sample
     * <strong><code> n2</code></strong> is the size of the second sample;
     * <strong><code> m1</code></strong> is the mean of the first sample;
     * <strong><code> m2</code></strong> is the mean of the second sample;
     * <strong><code> var1</code></strong> is the variance of the first sample;
     * <strong><code> var2</code></strong> is the variance of the second sample;
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return t statistic
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     */
    public double t(final double[] sample1, final double[] sample2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(sample1);
        checkSampleData(sample2);
        // No try-catch or advertised exception because args have just been checked
        return t(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    /**
     * Computes a 2-sample t statistic </a>, comparing the means of the datasets
     * described by two {@link StatisticalSummary} instances, without the
     * assumption of equal subpopulation variances.  Use
     * {@link #homoscedasticT(StatisticalSummary, StatisticalSummary)} to
     * compute a t-statistic under the equal variances assumption.
     * <p>
     * This statistic can be used to perform a two-sample t-test to compare
     * sample means.</p>
     * <p>
     * The returned  t-statistic is</p>
     * <p>
     * &nbsp;&nbsp; <code>  t = (m1 - m2) / sqrt(var1/n1 + var2/n2)</code>
     * </p><p>
     * where <strong><code>n1</code></strong> is the size of the first sample;
     * <strong><code> n2</code></strong> is the size of the second sample;
     * <strong><code> m1</code></strong> is the mean of the first sample;
     * <strong><code> m2</code></strong> is the mean of the second sample
     * <strong><code> var1</code></strong> is the variance of the first sample;
     * <strong><code> var2</code></strong> is the variance of the second sample
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The datasets described by the two Univariates must each contain
     * at least 2 observations.
     * </li></ul></p>
     *
     * @param sampleStats1 StatisticalSummary describing data from the first sample
     * @param sampleStats2 StatisticalSummary describing data from the second sample
     * @return t statistic
     * @throws NullArgumentException if the sample statistics are <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     */
    public double t(final StatisticalSummary sampleStats1, final StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(sampleStats1);
        checkSampleData(sampleStats2);
        return t(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    /**
     * Computes a 2-sample t statistic, comparing the means of the datasets
     * described by two {@link StatisticalSummary} instances, under the
     * assumption of equal subpopulation variances.  To compute a t-statistic
     * without the equal variances assumption, use
     * {@link #t(StatisticalSummary, StatisticalSummary)}.
     * <p>
     * This statistic can be used to perform a (homoscedastic) two-sample
     * t-test to compare sample means.</p>
     * <p>
     * The t-statistic returned is</p>
     * <p>
     * &nbsp;&nbsp;<code>  t = (m1 - m2) / (sqrt(1/n1 +1/n2) sqrt(var))</code>
     * </p><p>
     * where <strong><code>n1</code></strong> is the size of first sample;
     * <strong><code> n2</code></strong> is the size of second sample;
     * <strong><code> m1</code></strong> is the mean of first sample;
     * <strong><code> m2</code></strong> is the mean of second sample
     * and <strong><code>var</code></strong> is the pooled variance estimate:
     * </p><p>
     * <code>var = sqrt(((n1 - 1)var1 + (n2 - 1)var2) / ((n1-1) + (n2-1)))</code>
     * </p><p>
     * with <strong><code>var1</code></strong> the variance of the first sample and
     * <strong><code>var2</code></strong> the variance of the second sample.
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The datasets described by the two Univariates must each contain
     * at least 2 observations.
     * </li></ul></p>
     *
     * @param sampleStats1 StatisticalSummary describing data from the first sample
     * @param sampleStats2 StatisticalSummary describing data from the second sample
     * @return t statistic
     * @throws NullArgumentException if the sample statistics are <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     */
    public double homoscedasticT(final StatisticalSummary sampleStats1, final StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException {
        checkSampleData(sampleStats1);
        checkSampleData(sampleStats2);
        return homoscedasticT(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a one-sample, two-tailed t-test
     * comparing the mean of the input array with the constant <code>mu</code>.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the mean equals
     * <code>mu</code> in favor of the two-sided alternative that the mean
     * is different from <code>mu</code>. For a one-sided test, divide the
     * returned value by 2.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">here</a>
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array length must be at least 2.
     * </li></ul></p>
     *
     * @param mu constant value to compare sample mean against
     * @param sample array of sample data values
     * @return p-value
     * @throws NullArgumentException if the sample array is <code>null</code>
     * @throws NumberIsTooSmallException if the length of the array is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double tTest(final double mu, final double[] sample) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sample);
        // No try-catch or advertised exception because args have just been checked
        return tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample), sample.length);
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda353.htm">
     * two-sided t-test</a> evaluating the null hypothesis that the mean of the population from
     * which <code>sample</code> is drawn equals <code>mu</code>.
     * <p>
     * Returns <code>true</code> iff the null hypothesis can be
     * rejected with confidence <code>1 - alpha</code>.  To
     * perform a 1-sided test, use <code>alpha * 2</code></p>
     * <p>
     * <strong>Examples:</strong><br><ol>
     * <li>To test the (2-sided) hypothesis <code>sample mean = mu </code> at
     * the 95% level, use <br><code>tTest(mu, sample, 0.05) </code>
     * </li>
     * <li>To test the (one-sided) hypothesis <code> sample mean < mu </code>
     * at the 99% level, first verify that the measured sample mean is less
     * than <code>mu</code> and then use
     * <br><code>tTest(mu, sample, 0.02) </code>
     * </li></ol></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the one-sample
     * parametric t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/sg_glos.html#one-sample">here</a>
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array length must be at least 2.
     * </li></ul></p>
     *
     * @param mu constant value to compare sample mean against
     * @param sample array of sample data values
     * @param alpha significance level of the test
     * @return p-value
     * @throws NullArgumentException if the sample array is <code>null</code>
     * @throws NumberIsTooSmallException if the length of the array is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error computing the p-value
     */
    public boolean tTest(final double mu, final double[] sample, final double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_492");
        checkSignificanceLevel(alpha);
        return ROR_less(tTest(mu, sample), alpha, "org.apache.commons.math3.stat.inference.TTest.tTest_492", _mut5399, _mut5400, _mut5401, _mut5402, _mut5403);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a one-sample, two-tailed t-test
     * comparing the mean of the dataset described by <code>sampleStats</code>
     * with the constant <code>mu</code>.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the mean equals
     * <code>mu</code> in favor of the two-sided alternative that the mean
     * is different from <code>mu</code>. For a one-sided test, divide the
     * returned value by 2.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The sample must contain at least 2 observations.
     * </li></ul></p>
     *
     * @param mu constant value to compare sample mean against
     * @param sampleStats StatisticalSummary describing sample data
     * @return p-value
     * @throws NullArgumentException if <code>sampleStats</code> is <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double tTest(final double mu, final StatisticalSummary sampleStats) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sampleStats);
        return tTest(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda353.htm">
     * two-sided t-test</a> evaluating the null hypothesis that the mean of the
     * population from which the dataset described by <code>stats</code> is
     * drawn equals <code>mu</code>.
     * <p>
     * Returns <code>true</code> iff the null hypothesis can be rejected with
     * confidence <code>1 - alpha</code>.  To  perform a 1-sided test, use
     * <code>alpha * 2.</code></p>
     * <p>
     * <strong>Examples:</strong><br><ol>
     * <li>To test the (2-sided) hypothesis <code>sample mean = mu </code> at
     * the 95% level, use <br><code>tTest(mu, sampleStats, 0.05) </code>
     * </li>
     * <li>To test the (one-sided) hypothesis <code> sample mean < mu </code>
     * at the 99% level, first verify that the measured sample mean is less
     * than <code>mu</code> and then use
     * <br><code>tTest(mu, sampleStats, 0.02) </code>
     * </li></ol></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the one-sample
     * parametric t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/sg_glos.html#one-sample">here</a>
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The sample must include at least 2 observations.
     * </li></ul></p>
     *
     * @param mu constant value to compare sample mean against
     * @param sampleStats StatisticalSummary describing sample data values
     * @param alpha significance level of the test
     * @return p-value
     * @throws NullArgumentException if <code>sampleStats</code> is <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean tTest(final double mu, final StatisticalSummary sampleStats, final double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_578");
        checkSignificanceLevel(alpha);
        return ROR_less(tTest(mu, sampleStats), alpha, "org.apache.commons.math3.stat.inference.TTest.tTest_578", _mut5404, _mut5405, _mut5406, _mut5407, _mut5408);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a two-sample, two-tailed t-test
     * comparing the means of the input arrays.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the two means are
     * equal in favor of the two-sided alternative that they are different.
     * For a one-sided test, divide the returned value by 2.</p>
     * <p>
     * The test does not assume that the underlying popuation variances are
     * equal  and it uses approximated degrees of freedom computed from the
     * sample data to compute the p-value.  The t-statistic used is as defined in
     * {@link #t(double[], double[])} and the Welch-Satterthwaite approximation
     * to the degrees of freedom is used,
     * as described
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section3/prc31.htm">
     * here.</a>  To perform the test under the assumption of equal subpopulation
     * variances, use {@link #homoscedasticTTest(double[], double[])}.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the p-value depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return p-value for t-test
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double tTest(final double[] sample1, final double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sample1);
        checkSampleData(sample2);
        // No try-catch or advertised exception because args have just been checked
        return tTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a two-sample, two-tailed t-test
     * comparing the means of the input arrays, under the assumption that
     * the two samples are drawn from subpopulations with equal variances.
     * To perform the test without the equal variances assumption, use
     * {@link #tTest(double[], double[])}.</p>
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the two means are
     * equal in favor of the two-sided alternative that they are different.
     * For a one-sided test, divide the returned value by 2.</p>
     * <p>
     * A pooled variance estimate is used to compute the t-statistic.  See
     * {@link #homoscedasticT(double[], double[])}. The sum of the sample sizes
     * minus 2 is used as the degrees of freedom.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the p-value depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @return p-value for t-test
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double homoscedasticTTest(final double[] sample1, final double[] sample2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sample1);
        checkSampleData(sample2);
        // No try-catch or advertised exception because args have just been checked
        return homoscedasticTTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
    }

    /**
     * Performs a
     * <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda353.htm">
     * two-sided t-test</a> evaluating the null hypothesis that <code>sample1</code>
     * and <code>sample2</code> are drawn from populations with the same mean,
     * with significance level <code>alpha</code>.  This test does not assume
     * that the subpopulation variances are equal.  To perform the test assuming
     * equal variances, use
     * {@link #homoscedasticTTest(double[], double[], double)}.
     * <p>
     * Returns <code>true</code> iff the null hypothesis that the means are
     * equal can be rejected with confidence <code>1 - alpha</code>.  To
     * perform a 1-sided test, use <code>alpha * 2</code></p>
     * <p>
     * See {@link #t(double[], double[])} for the formula used to compute the
     * t-statistic.  Degrees of freedom are approximated using the
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section3/prc31.htm">
     * Welch-Satterthwaite approximation.</a></p>
     * <p>
     * <strong>Examples:</strong><br><ol>
     * <li>To test the (2-sided) hypothesis <code>mean 1 = mean 2 </code> at
     * the 95% level,  use
     * <br><code>tTest(sample1, sample2, 0.05). </code>
     * </li>
     * <li>To test the (one-sided) hypothesis <code> mean 1 < mean 2 </code>,
     * at the 99% level, first verify that the measured  mean of <code>sample 1</code>
     * is less than the mean of <code>sample 2</code> and then use
     * <br><code>tTest(sample1, sample2, 0.02) </code>
     * </li></ol></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li>
     * <li> <code> 0 < alpha < 0.5 </code>
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with
     * confidence 1 - alpha
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean tTest(final double[] sample1, final double[] sample2, final double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_739");
        checkSignificanceLevel(alpha);
        return ROR_less(tTest(sample1, sample2), alpha, "org.apache.commons.math3.stat.inference.TTest.tTest_739", _mut5409, _mut5410, _mut5411, _mut5412, _mut5413);
    }

    /**
     * Performs a
     * <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda353.htm">
     * two-sided t-test</a> evaluating the null hypothesis that <code>sample1</code>
     * and <code>sample2</code> are drawn from populations with the same mean,
     * with significance level <code>alpha</code>,  assuming that the
     * subpopulation variances are equal.  Use
     * {@link #tTest(double[], double[], double)} to perform the test without
     * the assumption of equal variances.
     * <p>
     * Returns <code>true</code> iff the null hypothesis that the means are
     * equal can be rejected with confidence <code>1 - alpha</code>.  To
     * perform a 1-sided test, use <code>alpha * 2.</code>  To perform the test
     * without the assumption of equal subpopulation variances, use
     * {@link #tTest(double[], double[], double)}.</p>
     * <p>
     * A pooled variance estimate is used to compute the t-statistic. See
     * {@link #t(double[], double[])} for the formula. The sum of the sample
     * sizes minus 2 is used as the degrees of freedom.</p>
     * <p>
     * <strong>Examples:</strong><br><ol>
     * <li>To test the (2-sided) hypothesis <code>mean 1 = mean 2 </code> at
     * the 95% level, use <br><code>tTest(sample1, sample2, 0.05). </code>
     * </li>
     * <li>To test the (one-sided) hypothesis <code> mean 1 < mean 2, </code>
     * at the 99% level, first verify that the measured mean of
     * <code>sample 1</code> is less than the mean of <code>sample 2</code>
     * and then use
     * <br><code>tTest(sample1, sample2, 0.02) </code>
     * </li></ol></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The observed array lengths must both be at least 2.
     * </li>
     * <li> <code> 0 < alpha < 0.5 </code>
     * </li></ul></p>
     *
     * @param sample1 array of sample data values
     * @param sample2 array of sample data values
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with
     * confidence 1 - alpha
     * @throws NullArgumentException if the arrays are <code>null</code>
     * @throws NumberIsTooSmallException if the length of the arrays is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean homoscedasticTTest(final double[] sample1, final double[] sample2, final double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_802");
        checkSignificanceLevel(alpha);
        return ROR_less(homoscedasticTTest(sample1, sample2), alpha, "org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_802", _mut5414, _mut5415, _mut5416, _mut5417, _mut5418);
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a two-sample, two-tailed t-test
     * comparing the means of the datasets described by two StatisticalSummary
     * instances.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the two means are
     * equal in favor of the two-sided alternative that they are different.
     * For a one-sided test, divide the returned value by 2.</p>
     * <p>
     * The test does not assume that the underlying population variances are
     * equal  and it uses approximated degrees of freedom computed from the
     * sample data to compute the p-value.   To perform the test assuming
     * equal variances, use
     * {@link #homoscedasticTTest(StatisticalSummary, StatisticalSummary)}.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the p-value depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The datasets described by the two Univariates must each contain
     * at least 2 observations.
     * </li></ul></p>
     *
     * @param sampleStats1  StatisticalSummary describing data from the first sample
     * @param sampleStats2  StatisticalSummary describing data from the second sample
     * @return p-value for t-test
     * @throws NullArgumentException if the sample statistics are <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double tTest(final StatisticalSummary sampleStats1, final StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sampleStats1);
        checkSampleData(sampleStats2);
        return tTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    /**
     * Returns the <i>observed significance level</i>, or
     * <i>p-value</i>, associated with a two-sample, two-tailed t-test
     * comparing the means of the datasets described by two StatisticalSummary
     * instances, under the hypothesis of equal subpopulation variances. To
     * perform a test without the equal variances assumption, use
     * {@link #tTest(StatisticalSummary, StatisticalSummary)}.
     * <p>
     * The number returned is the smallest significance level
     * at which one can reject the null hypothesis that the two means are
     * equal in favor of the two-sided alternative that they are different.
     * For a one-sided test, divide the returned value by 2.</p>
     * <p>
     * See {@link #homoscedasticT(double[], double[])} for the formula used to
     * compute the t-statistic. The sum of the  sample sizes minus 2 is used as
     * the degrees of freedom.</p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the p-value depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">here</a>
     * </p><p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The datasets described by the two Univariates must each contain
     * at least 2 observations.
     * </li></ul></p>
     *
     * @param sampleStats1  StatisticalSummary describing data from the first sample
     * @param sampleStats2  StatisticalSummary describing data from the second sample
     * @return p-value for t-test
     * @throws NullArgumentException if the sample statistics are <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double homoscedasticTTest(final StatisticalSummary sampleStats1, final StatisticalSummary sampleStats2) throws NullArgumentException, NumberIsTooSmallException, MaxCountExceededException {
        checkSampleData(sampleStats1);
        checkSampleData(sampleStats2);
        return homoscedasticTTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
    }

    /**
     * Performs a
     * <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda353.htm">
     * two-sided t-test</a> evaluating the null hypothesis that
     * <code>sampleStats1</code> and <code>sampleStats2</code> describe
     * datasets drawn from populations with the same mean, with significance
     * level <code>alpha</code>.   This test does not assume that the
     * subpopulation variances are equal.  To perform the test under the equal
     * variances assumption, use
     * {@link #homoscedasticTTest(StatisticalSummary, StatisticalSummary)}.
     * <p>
     * Returns <code>true</code> iff the null hypothesis that the means are
     * equal can be rejected with confidence <code>1 - alpha</code>.  To
     * perform a 1-sided test, use <code>alpha * 2</code></p>
     * <p>
     * See {@link #t(double[], double[])} for the formula used to compute the
     * t-statistic.  Degrees of freedom are approximated using the
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section3/prc31.htm">
     * Welch-Satterthwaite approximation.</a></p>
     * <p>
     * <strong>Examples:</strong><br><ol>
     * <li>To test the (2-sided) hypothesis <code>mean 1 = mean 2 </code> at
     * the 95%, use
     * <br><code>tTest(sampleStats1, sampleStats2, 0.05) </code>
     * </li>
     * <li>To test the (one-sided) hypothesis <code> mean 1 < mean 2 </code>
     * at the 99% level,  first verify that the measured mean of
     * <code>sample 1</code> is less than  the mean of <code>sample 2</code>
     * and then use
     * <br><code>tTest(sampleStats1, sampleStats2, 0.02) </code>
     * </li></ol></p>
     * <p>
     * <strong>Usage Note:</strong><br>
     * The validity of the test depends on the assumptions of the parametric
     * t-test procedure, as discussed
     * <a href="http://www.basic.nwu.edu/statguidefiles/ttest_unpaired_ass_viol.html">
     * here</a></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>The datasets described by the two Univariates must each contain
     * at least 2 observations.
     * </li>
     * <li> <code> 0 < alpha < 0.5 </code>
     * </li></ul></p>
     *
     * @param sampleStats1 StatisticalSummary describing sample data values
     * @param sampleStats2 StatisticalSummary describing sample data values
     * @param alpha significance level of the test
     * @return true if the null hypothesis can be rejected with
     * confidence 1 - alpha
     * @throws NullArgumentException if the sample statistics are <code>null</code>
     * @throws NumberIsTooSmallException if the number of samples is &lt; 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean tTest(final StatisticalSummary sampleStats1, final StatisticalSummary sampleStats2, final double alpha) throws NullArgumentException, NumberIsTooSmallException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_964");
        checkSignificanceLevel(alpha);
        return ROR_less(tTest(sampleStats1, sampleStats2), alpha, "org.apache.commons.math3.stat.inference.TTest.tTest_964", _mut5419, _mut5420, _mut5421, _mut5422, _mut5423);
    }

    /**
     * Computes approximate degrees of freedom for 2-sample t-test.
     *
     * @param v1 first sample variance
     * @param v2 second sample variance
     * @param n1 first sample n
     * @param n2 second sample n
     * @return approximate degrees of freedom
     */
    protected double df(double v1, double v2, double n1, double n2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.df_986");
        return AOR_divide((AOR_multiply((AOR_plus((AOR_divide(v1, n1, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5424, _mut5425, _mut5426, _mut5427)), (AOR_divide(v2, n2, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5428, _mut5429, _mut5430, _mut5431)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5432, _mut5433, _mut5434, _mut5435)), (AOR_plus((AOR_divide(v1, n1, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5436, _mut5437, _mut5438, _mut5439)), (AOR_divide(v2, n2, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5440, _mut5441, _mut5442, _mut5443)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5444, _mut5445, _mut5446, _mut5447)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5448, _mut5449, _mut5450, _mut5451)), (AOR_plus(AOR_divide((AOR_multiply(v1, v1, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5452, _mut5453, _mut5454, _mut5455)), (AOR_multiply(AOR_multiply(n1, n1, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5456, _mut5457, _mut5458, _mut5459), (AOR_minus(n1, 1d, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5460, _mut5461, _mut5462, _mut5463)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5464, _mut5465, _mut5466, _mut5467)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5468, _mut5469, _mut5470, _mut5471), AOR_divide((AOR_multiply(v2, v2, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5472, _mut5473, _mut5474, _mut5475)), (AOR_multiply(AOR_multiply(n2, n2, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5476, _mut5477, _mut5478, _mut5479), (AOR_minus(n2, 1d, "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5480, _mut5481, _mut5482, _mut5483)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5484, _mut5485, _mut5486, _mut5487)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5488, _mut5489, _mut5490, _mut5491), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5492, _mut5493, _mut5494, _mut5495)), "org.apache.commons.math3.stat.inference.TTest.df_986", _mut5496, _mut5497, _mut5498, _mut5499);
    }

    /**
     * Computes t test statistic for 1-sample t-test.
     *
     * @param m sample mean
     * @param mu constant to test against
     * @param v sample variance
     * @param n sample n
     * @return t test statistic
     */
    protected double t(final double m, final double mu, final double v, final double n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.t_1001");
        return AOR_divide((AOR_minus(m, mu, "org.apache.commons.math3.stat.inference.TTest.t_1001", _mut5500, _mut5501, _mut5502, _mut5503)), FastMath.sqrt(AOR_divide(v, n, "org.apache.commons.math3.stat.inference.TTest.t_1001", _mut5504, _mut5505, _mut5506, _mut5507)), "org.apache.commons.math3.stat.inference.TTest.t_1001", _mut5508, _mut5509, _mut5510, _mut5511);
    }

    /**
     * Computes t test statistic for 2-sample t-test.
     * <p>
     * Does not assume that subpopulation variances are equal.</p>
     *
     * @param m1 first sample mean
     * @param m2 second sample mean
     * @param v1 first sample variance
     * @param v2 second sample variance
     * @param n1 first sample n
     * @param n2 second sample n
     * @return t test statistic
     */
    protected double t(final double m1, final double m2, final double v1, final double v2, final double n1, final double n2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.t_1019");
        return AOR_divide((AOR_minus(m1, m2, "org.apache.commons.math3.stat.inference.TTest.t_1019", _mut5512, _mut5513, _mut5514, _mut5515)), FastMath.sqrt(AOR_plus((AOR_divide(v1, n1, "org.apache.commons.math3.stat.inference.TTest.t_1019", _mut5516, _mut5517, _mut5518, _mut5519)), (AOR_divide(v2, n2, "org.apache.commons.math3.stat.inference.TTest.t_1019", _mut5520, _mut5521, _mut5522, _mut5523)), "org.apache.commons.math3.stat.inference.TTest.t_1019", _mut5524, _mut5525, _mut5526, _mut5527)), "org.apache.commons.math3.stat.inference.TTest.t_1019", _mut5528, _mut5529, _mut5530, _mut5531);
    }

    /**
     * Computes t test statistic for 2-sample t-test under the hypothesis
     * of equal subpopulation variances.
     *
     * @param m1 first sample mean
     * @param m2 second sample mean
     * @param v1 first sample variance
     * @param v2 second sample variance
     * @param n1 first sample n
     * @param n2 second sample n
     * @return t test statistic
     */
    protected double homoscedasticT(final double m1, final double m2, final double v1, final double v2, final double n1, final double n2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037");
        final double pooledVariance = AOR_divide((AOR_plus(AOR_multiply((AOR_minus(n1, 1, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5532, _mut5533, _mut5534, _mut5535)), v1, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5536, _mut5537, _mut5538, _mut5539), AOR_multiply((AOR_minus(n2, 1, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5540, _mut5541, _mut5542, _mut5543)), v2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5544, _mut5545, _mut5546, _mut5547), "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5548, _mut5549, _mut5550, _mut5551)), (AOR_minus(AOR_plus(n1, n2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5552, _mut5553, _mut5554, _mut5555), 2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5556, _mut5557, _mut5558, _mut5559)), "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5560, _mut5561, _mut5562, _mut5563);
        return AOR_divide((AOR_minus(m1, m2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5564, _mut5565, _mut5566, _mut5567)), FastMath.sqrt(AOR_multiply(pooledVariance, (AOR_plus(AOR_divide(1d, n1, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5568, _mut5569, _mut5570, _mut5571), AOR_divide(1d, n2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5572, _mut5573, _mut5574, _mut5575), "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5576, _mut5577, _mut5578, _mut5579)), "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5580, _mut5581, _mut5582, _mut5583)), "org.apache.commons.math3.stat.inference.TTest.homoscedasticT_1037", _mut5584, _mut5585, _mut5586, _mut5587);
    }

    /**
     * Computes p-value for 2-sided, 1-sample t-test.
     *
     * @param m sample mean
     * @param mu constant to test against
     * @param v sample variance
     * @param n sample n
     * @return p-value
     * @throws MaxCountExceededException if an error occurs computing the p-value
     * @throws MathIllegalArgumentException if n is not greater than 1
     */
    protected double tTest(final double m, final double mu, final double v, final double n) throws MaxCountExceededException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_1055");
        final double t = FastMath.abs(t(m, mu, v, n));
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final TDistribution distribution = new TDistribution(null, AOR_minus(n, 1, "org.apache.commons.math3.stat.inference.TTest.tTest_1055", _mut5588, _mut5589, _mut5590, _mut5591));
        return AOR_multiply(2.0, distribution.cumulativeProbability(-t), "org.apache.commons.math3.stat.inference.TTest.tTest_1055", _mut5592, _mut5593, _mut5594, _mut5595);
    }

    /**
     * Computes p-value for 2-sided, 2-sample t-test.
     * <p>
     * Does not assume subpopulation variances are equal. Degrees of freedom
     * are estimated from the data.</p>
     *
     * @param m1 first sample mean
     * @param m2 second sample mean
     * @param v1 first sample variance
     * @param v2 second sample variance
     * @param n1 first sample n
     * @param n2 second sample n
     * @return p-value
     * @throws MaxCountExceededException if an error occurs computing the p-value
     * @throws NotStrictlyPositiveException if the estimated degrees of freedom is not
     * strictly positive
     */
    protected double tTest(final double m1, final double m2, final double v1, final double v2, final double n1, final double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.tTest_1083");
        final double t = FastMath.abs(t(m1, m2, v1, v2, n1, n2));
        final double degreesOfFreedom = df(v1, v2, n1, n2);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final TDistribution distribution = new TDistribution(null, degreesOfFreedom);
        return AOR_multiply(2.0, distribution.cumulativeProbability(-t), "org.apache.commons.math3.stat.inference.TTest.tTest_1083", _mut5596, _mut5597, _mut5598, _mut5599);
    }

    /**
     * Computes p-value for 2-sided, 2-sample t-test, under the assumption
     * of equal subpopulation variances.
     * <p>
     * The sum of the sample sizes minus 2 is used as degrees of freedom.</p>
     *
     * @param m1 first sample mean
     * @param m2 second sample mean
     * @param v1 first sample variance
     * @param v2 second sample variance
     * @param n1 first sample n
     * @param n2 second sample n
     * @return p-value
     * @throws MaxCountExceededException if an error occurs computing the p-value
     * @throws NotStrictlyPositiveException if the estimated degrees of freedom is not
     * strictly positive
     */
    protected double homoscedasticTTest(double m1, double m2, double v1, double v2, double n1, double n2) throws MaxCountExceededException, NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_1113");
        final double t = FastMath.abs(homoscedasticT(m1, m2, v1, v2, n1, n2));
        final double degreesOfFreedom = AOR_minus(AOR_plus(n1, n2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_1113", _mut5600, _mut5601, _mut5602, _mut5603), 2, "org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_1113", _mut5604, _mut5605, _mut5606, _mut5607);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final TDistribution distribution = new TDistribution(null, degreesOfFreedom);
        return AOR_multiply(2.0, distribution.cumulativeProbability(-t), "org.apache.commons.math3.stat.inference.TTest.homoscedasticTTest_1113", _mut5608, _mut5609, _mut5610, _mut5611);
    }

    /**
     * Check significance level.
     *
     * @param alpha significance level
     * @throws OutOfRangeException if the significance level is out of bounds.
     */
    private void checkSignificanceLevel(final double alpha) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.checkSignificanceLevel_1132");
        if ((_mut5622 ? (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.TTest.checkSignificanceLevel_1132", _mut5612, _mut5613, _mut5614, _mut5615, _mut5616) && ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.TTest.checkSignificanceLevel_1132", _mut5617, _mut5618, _mut5619, _mut5620, _mut5621)) : (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.TTest.checkSignificanceLevel_1132", _mut5612, _mut5613, _mut5614, _mut5615, _mut5616) || ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.TTest.checkSignificanceLevel_1132", _mut5617, _mut5618, _mut5619, _mut5620, _mut5621)))) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, alpha, 0.0, 0.5);
        }
    }

    /**
     * Check sample data.
     *
     * @param data Sample data.
     * @throws NullArgumentException if {@code data} is {@code null}.
     * @throws NumberIsTooSmallException if there is not enough sample data.
     */
    private void checkSampleData(final double[] data) throws NullArgumentException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.checkSampleData_1149");
        if (data == null) {
            throw new NullArgumentException();
        }
        if (ROR_less(data.length, 2, "org.apache.commons.math3.stat.inference.TTest.checkSampleData_1149", _mut5623, _mut5624, _mut5625, _mut5626, _mut5627)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, data.length, 2, true);
        }
    }

    /**
     * Check sample data.
     *
     * @param stat Statistical summary.
     * @throws NullArgumentException if {@code data} is {@code null}.
     * @throws NumberIsTooSmallException if there is not enough sample data.
     */
    private void checkSampleData(final StatisticalSummary stat) throws NullArgumentException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.TTest.checkSampleData_1170");
        if (stat == null) {
            throw new NullArgumentException();
        }
        if (ROR_less(stat.getN(), 2, "org.apache.commons.math3.stat.inference.TTest.checkSampleData_1170", _mut5628, _mut5629, _mut5630, _mut5631, _mut5632)) {
            throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_DATA_FOR_T_STATISTIC, stat.getN(), 2, true);
        }
    }
}
