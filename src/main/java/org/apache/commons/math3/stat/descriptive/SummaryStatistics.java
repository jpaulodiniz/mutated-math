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
package org.apache.commons.math3.stat.descriptive;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.SecondMoment;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * Computes summary statistics for a stream of data values added using the
 * {@link #addValue(double) addValue} method. The data values are not stored in
 * memory, so this class can be used to compute statistics for very large data
 * streams.
 * </p>
 * <p>
 * The {@link StorelessUnivariateStatistic} instances used to maintain summary
 * state and compute statistics are configurable via setters. For example, the
 * default implementation for the variance can be overridden by calling
 * {@link #setVarianceImpl(StorelessUnivariateStatistic)}. Actual parameters to
 * these methods must implement the {@link StorelessUnivariateStatistic}
 * interface and configuration must be completed before <code>addValue</code>
 * is called. No configuration is necessary to use the default, commons-math
 * provided implementations.
 * </p>
 * <p>
 * Note: This class is not thread-safe. Use
 * {@link SynchronizedSummaryStatistics} if concurrent access from multiple
 * threads is required.
 * </p>
 */
public class SummaryStatistics implements StatisticalSummary, Serializable {

    @Conditional
    public static boolean _mut4610 = false, _mut4611 = false, _mut4612 = false, _mut4613 = false, _mut4614 = false, _mut4615 = false, _mut4616 = false, _mut4617 = false, _mut4618 = false, _mut4619 = false, _mut4620 = false, _mut4621 = false, _mut4622 = false, _mut4623 = false, _mut4624 = false, _mut4625 = false, _mut4626 = false, _mut4627 = false, _mut4628 = false, _mut4629 = false, _mut4630 = false, _mut4631 = false, _mut4632 = false, _mut4633 = false, _mut4634 = false, _mut4635 = false, _mut4636 = false, _mut4637 = false, _mut4638 = false, _mut4639 = false, _mut4640 = false, _mut4641 = false, _mut4642 = false, _mut4643 = false, _mut4644 = false, _mut4645 = false, _mut4646 = false, _mut4647 = false, _mut4648 = false, _mut4649 = false, _mut4650 = false, _mut4651 = false, _mut4652 = false, _mut4653 = false, _mut4654 = false, _mut4655 = false, _mut4656 = false, _mut4657 = false, _mut4658 = false, _mut4659 = false, _mut4660 = false, _mut4661 = false, _mut4662 = false, _mut4663 = false, _mut4664 = false, _mut4665 = false, _mut4666 = false, _mut4667 = false, _mut4668 = false, _mut4669 = false, _mut4670 = false, _mut4671 = false, _mut4672 = false, _mut4673 = false, _mut4674 = false, _mut4675 = false, _mut4676 = false, _mut4677 = false, _mut4678 = false, _mut4679 = false, _mut4680 = false, _mut4681 = false, _mut4682 = false, _mut4683 = false, _mut4684 = false, _mut4685 = false, _mut4686 = false, _mut4687 = false, _mut4688 = false, _mut4689 = false, _mut4690 = false, _mut4691 = false, _mut4692 = false, _mut4693 = false, _mut4694 = false, _mut4695 = false, _mut4696 = false, _mut4697 = false, _mut4698 = false, _mut4699 = false, _mut4700 = false, _mut4701 = false, _mut4702 = false, _mut4703 = false, _mut4704 = false, _mut4705 = false, _mut4706 = false, _mut4707 = false, _mut4708 = false;

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = -2021321786743555871L;

    /**
     * count of values that have been added
     */
    private long n = 0;

    /**
     * SecondMoment is used to compute the mean and variance
     */
    private SecondMoment secondMoment = new SecondMoment();

    /**
     * sum of values that have been added
     */
    private Sum sum = new Sum();

    /**
     * sum of the square of each value that has been added
     */
    private SumOfSquares sumsq = new SumOfSquares();

    /**
     * min of values that have been added
     */
    private Min min = new Min();

    /**
     * max of values that have been added
     */
    private Max max = new Max();

    /**
     * sumLog of values that have been added
     */
    private SumOfLogs sumLog = new SumOfLogs();

    /**
     * geoMean of values that have been added
     */
    private GeometricMean geoMean = new GeometricMean(sumLog);

    /**
     * mean of values that have been added
     */
    private Mean mean = new Mean(secondMoment);

    /**
     * variance of values that have been added
     */
    private Variance variance = new Variance(secondMoment);

    /**
     * Sum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumImpl = sum;

    /**
     * Sum of squares statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumsqImpl = sumsq;

    /**
     * Minimum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic minImpl = min;

    /**
     * Maximum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic maxImpl = max;

    /**
     * Sum of log statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic sumLogImpl = sumLog;

    /**
     * Geometric mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic geoMeanImpl = geoMean;

    /**
     * Mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic meanImpl = mean;

    /**
     * Variance statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic varianceImpl = variance;

    /**
     * Construct a SummaryStatistics instance
     */
    public SummaryStatistics() {
    }

    /**
     * A copy constructor. Creates a deep-copy of the {@code original}.
     *
     * @param original the {@code SummaryStatistics} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SummaryStatistics(SummaryStatistics original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * Return a {@link StatisticalSummaryValues} instance reporting current
     * statistics.
     * @return Current values of statistics
     */
    public StatisticalSummary getSummary() {
        return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
    }

    /**
     * Add a value to the data
     * @param value the value to add
     */
    public void addValue(double value) {
        sumImpl.increment(value);
        sumsqImpl.increment(value);
        minImpl.increment(value);
        maxImpl.increment(value);
        sumLogImpl.increment(value);
        secondMoment.increment(value);
        // need to increment these
        if (meanImpl != mean) {
            meanImpl.increment(value);
        }
        if (varianceImpl != variance) {
            varianceImpl.increment(value);
        }
        if (geoMeanImpl != geoMean) {
            geoMeanImpl.increment(value);
        }
        n++;
    }

    /**
     * Returns the number of available values
     * @return The number of available values
     */
    public long getN() {
        return n;
    }

    /**
     * Returns the sum of the values that have been added
     * @return The sum or <code>Double.NaN</code> if no values have been added
     */
    public double getSum() {
        return sumImpl.getResult();
    }

    /**
     * Returns the sum of the squares of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return The sum of squares
     */
    public double getSumsq() {
        return sumsqImpl.getResult();
    }

    /**
     * Returns the mean of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the mean
     */
    public double getMean() {
        return meanImpl.getResult();
    }

    /**
     * Returns the standard deviation of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the standard deviation
     */
    public double getStandardDeviation() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.SummaryStatistics.getStandardDeviation_215");
        double stdDev = Double.NaN;
        if (ROR_greater(getN(), 0, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.getStandardDeviation_215", _mut4610, _mut4611, _mut4612, _mut4613, _mut4614)) {
            if (ROR_greater(getN(), 1, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.getStandardDeviation_215", _mut4615, _mut4616, _mut4617, _mut4618, _mut4619)) {
                stdDev = FastMath.sqrt(getVariance());
            } else {
                stdDev = 0.0;
            }
        }
        return stdDev;
    }

    /**
     * Returns the quadratic mean, a.k.a.
     * <a href="http://mathworld.wolfram.com/Root-Mean-Square.html">
     * root-mean-square</a> of the available values
     * @return The quadratic mean or {@code Double.NaN} if no values
     * have been added.
     */
    public double getQuadraticMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.SummaryStatistics.getQuadraticMean_234");
        final long size = getN();
        return ROR_greater(size, 0, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.getQuadraticMean_234", _mut4620, _mut4621, _mut4622, _mut4623, _mut4624) ? FastMath.sqrt(AOR_divide(getSumsq(), size, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.getQuadraticMean_234", _mut4625, _mut4626, _mut4627, _mut4628)) : Double.NaN;
    }

    /**
     * Returns the (sample) variance of the available values.
     *
     * <p>This method returns the bias-corrected sample variance (using {@code n - 1} in
     * the denominator).  Use {@link #getPopulationVariance()} for the non-bias-corrected
     * population variance.</p>
     *
     * <p>Double.NaN is returned if no values have been added.</p>
     *
     * @return the variance
     */
    public double getVariance() {
        return varianceImpl.getResult();
    }

    /**
     * Returns the <a href="http://en.wikibooks.org/wiki/Statistics/Summary/Variance">
     * population variance</a> of the values that have been added.
     *
     * <p>Double.NaN is returned if no values have been added.</p>
     *
     * @return the population variance
     */
    public double getPopulationVariance() {
        Variance populationVariance = new Variance(secondMoment);
        populationVariance.setBiasCorrected(false);
        return populationVariance.getResult();
    }

    /**
     * Returns the maximum of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the maximum
     */
    public double getMax() {
        return maxImpl.getResult();
    }

    /**
     * Returns the minimum of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the minimum
     */
    public double getMin() {
        return minImpl.getResult();
    }

    /**
     * Returns the geometric mean of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the geometric mean
     */
    public double getGeometricMean() {
        return geoMeanImpl.getResult();
    }

    /**
     * Returns the sum of the logs of the values that have been added.
     * <p>
     * Double.NaN is returned if no values have been added.
     * </p>
     * @return the sum of logs
     * @since 1.2
     */
    public double getSumOfLogs() {
        return sumLogImpl.getResult();
    }

    /**
     * Returns a statistic related to the Second Central Moment.  Specifically,
     * what is returned is the sum of squared deviations from the sample mean
     * among the values that have been added.
     * <p>
     * Returns <code>Double.NaN</code> if no data values have been added and
     * returns <code>0</code> if there is just one value in the data set.</p>
     * <p>
     * @return second central moment statistic
     * @since 2.0
     */
    public double getSecondMoment() {
        return secondMoment.getResult();
    }

    /**
     * Generates a text report displaying summary statistics from values that
     * have been added.
     * @return String with line feeds displaying statistics
     * @since 1.2
     */
    @Override
    public String toString() {
        StringBuilder outBuffer = new StringBuilder();
        String endl = "\n";
        outBuffer.append("SummaryStatistics:").append(endl);
        outBuffer.append("n: ").append(getN()).append(endl);
        outBuffer.append("min: ").append(getMin()).append(endl);
        outBuffer.append("max: ").append(getMax()).append(endl);
        outBuffer.append("sum: ").append(getSum()).append(endl);
        outBuffer.append("mean: ").append(getMean()).append(endl);
        outBuffer.append("geometric mean: ").append(getGeometricMean()).append(endl);
        outBuffer.append("variance: ").append(getVariance()).append(endl);
        outBuffer.append("population variance: ").append(getPopulationVariance()).append(endl);
        outBuffer.append("second moment: ").append(getSecondMoment()).append(endl);
        outBuffer.append("sum of squares: ").append(getSumsq()).append(endl);
        outBuffer.append("standard deviation: ").append(getStandardDeviation()).append(endl);
        outBuffer.append("sum of logs: ").append(getSumOfLogs()).append(endl);
        return outBuffer.toString();
    }

    /**
     * Resets all statistics and storage
     */
    public void clear() {
        this.n = 0;
        minImpl.clear();
        maxImpl.clear();
        sumImpl.clear();
        sumLogImpl.clear();
        sumsqImpl.clear();
        geoMeanImpl.clear();
        secondMoment.clear();
        if (meanImpl != mean) {
            meanImpl.clear();
        }
        if (varianceImpl != variance) {
            varianceImpl.clear();
        }
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>SummaryStatistics</code> instance and all statistics have the
     * same values as this.
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.SummaryStatistics.equals_383");
        if (object == this) {
            return true;
        }
        if (object instanceof SummaryStatistics == false) {
            return false;
        }
        SummaryStatistics stat = (SummaryStatistics) object;
        return (_mut4635 ? ((_mut4634 ? ((_mut4633 ? ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) || Precision.equalsIncludingNaN(stat.getSumsq(), getSumsq())) : ((_mut4633 ? ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) && Precision.equalsIncludingNaN(stat.getSumsq(), getSumsq()))) || Precision.equalsIncludingNaN(stat.getVariance(), getVariance())) : ((_mut4634 ? ((_mut4633 ? ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) || Precision.equalsIncludingNaN(stat.getSumsq(), getSumsq())) : ((_mut4633 ? ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4632 ? ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4631 ? ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4630 ? ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4629 ? (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || Precision.equalsIncludingNaN(stat.getMax(), getMax())) : (Precision.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && Precision.equalsIncludingNaN(stat.getMax(), getMax()))) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) && Precision.equalsIncludingNaN(stat.getSumsq(), getSumsq()))) && Precision.equalsIncludingNaN(stat.getVariance(), getVariance())));
    }

    /**
     * Returns hash code based on values of statistics
     * @return hash code
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406");
        int result = AOR_plus(31, MathUtils.hash(getGeometricMean()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4636, _mut4637, _mut4638, _mut4639);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4640, _mut4641, _mut4642, _mut4643), MathUtils.hash(getGeometricMean()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4644, _mut4645, _mut4646, _mut4647);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4648, _mut4649, _mut4650, _mut4651), MathUtils.hash(getMax()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4652, _mut4653, _mut4654, _mut4655);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4656, _mut4657, _mut4658, _mut4659), MathUtils.hash(getMean()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4660, _mut4661, _mut4662, _mut4663);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4664, _mut4665, _mut4666, _mut4667), MathUtils.hash(getMin()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4668, _mut4669, _mut4670, _mut4671);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4672, _mut4673, _mut4674, _mut4675), MathUtils.hash(getN()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4676, _mut4677, _mut4678, _mut4679);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4680, _mut4681, _mut4682, _mut4683), MathUtils.hash(getSum()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4684, _mut4685, _mut4686, _mut4687);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4688, _mut4689, _mut4690, _mut4691), MathUtils.hash(getSumsq()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4692, _mut4693, _mut4694, _mut4695);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4696, _mut4697, _mut4698, _mut4699), MathUtils.hash(getVariance()), "org.apache.commons.math3.stat.descriptive.SummaryStatistics.hashCode_406", _mut4700, _mut4701, _mut4702, _mut4703);
        return result;
    }

    /**
     * Returns the currently configured Sum implementation
     * @return the StorelessUnivariateStatistic implementing the sum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumImpl() {
        return sumImpl;
    }

    /**
     * <p>
     * Sets the implementation for the Sum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumImpl the StorelessUnivariateStatistic instance to use for
     *        computing the Sum
     * @throws MathIllegalStateException if data has already been added (i.e if n >0)
     * @since 1.2
     */
    public void setSumImpl(StorelessUnivariateStatistic sumImpl) throws MathIllegalStateException {
        checkEmpty();
        this.sumImpl = sumImpl;
    }

    /**
     * Returns the currently configured sum of squares implementation
     * @return the StorelessUnivariateStatistic implementing the sum of squares
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumsqImpl() {
        return sumsqImpl;
    }

    /**
     * <p>
     * Sets the implementation for the sum of squares.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumsqImpl the StorelessUnivariateStatistic instance to use for
     *        computing the sum of squares
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl) throws MathIllegalStateException {
        checkEmpty();
        this.sumsqImpl = sumsqImpl;
    }

    /**
     * Returns the currently configured minimum implementation
     * @return the StorelessUnivariateStatistic implementing the minimum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMinImpl() {
        return minImpl;
    }

    /**
     * <p>
     * Sets the implementation for the minimum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param minImpl the StorelessUnivariateStatistic instance to use for
     *        computing the minimum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMinImpl(StorelessUnivariateStatistic minImpl) throws MathIllegalStateException {
        checkEmpty();
        this.minImpl = minImpl;
    }

    /**
     * Returns the currently configured maximum implementation
     * @return the StorelessUnivariateStatistic implementing the maximum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMaxImpl() {
        return maxImpl;
    }

    /**
     * <p>
     * Sets the implementation for the maximum.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param maxImpl the StorelessUnivariateStatistic instance to use for
     *        computing the maximum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMaxImpl(StorelessUnivariateStatistic maxImpl) throws MathIllegalStateException {
        checkEmpty();
        this.maxImpl = maxImpl;
    }

    /**
     * Returns the currently configured sum of logs implementation
     * @return the StorelessUnivariateStatistic implementing the log sum
     * @since 1.2
     */
    public StorelessUnivariateStatistic getSumLogImpl() {
        return sumLogImpl;
    }

    /**
     * <p>
     * Sets the implementation for the sum of logs.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param sumLogImpl the StorelessUnivariateStatistic instance to use for
     *        computing the log sum
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl) throws MathIllegalStateException {
        checkEmpty();
        this.sumLogImpl = sumLogImpl;
        geoMean.setSumLogImpl(sumLogImpl);
    }

    /**
     * Returns the currently configured geometric mean implementation
     * @return the StorelessUnivariateStatistic implementing the geometric mean
     * @since 1.2
     */
    public StorelessUnivariateStatistic getGeoMeanImpl() {
        return geoMeanImpl;
    }

    /**
     * <p>
     * Sets the implementation for the geometric mean.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param geoMeanImpl the StorelessUnivariateStatistic instance to use for
     *        computing the geometric mean
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl) throws MathIllegalStateException {
        checkEmpty();
        this.geoMeanImpl = geoMeanImpl;
    }

    /**
     * Returns the currently configured mean implementation
     * @return the StorelessUnivariateStatistic implementing the mean
     * @since 1.2
     */
    public StorelessUnivariateStatistic getMeanImpl() {
        return meanImpl;
    }

    /**
     * <p>
     * Sets the implementation for the mean.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param meanImpl the StorelessUnivariateStatistic instance to use for
     *        computing the mean
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setMeanImpl(StorelessUnivariateStatistic meanImpl) throws MathIllegalStateException {
        checkEmpty();
        this.meanImpl = meanImpl;
    }

    /**
     * Returns the currently configured variance implementation
     * @return the StorelessUnivariateStatistic implementing the variance
     * @since 1.2
     */
    public StorelessUnivariateStatistic getVarianceImpl() {
        return varianceImpl;
    }

    /**
     * <p>
     * Sets the implementation for the variance.
     * </p>
     * <p>
     * This method cannot be activated after data has been added - i.e.,
     * after {@link #addValue(double) addValue} has been used to add data.
     * If it is activated after data has been added, an IllegalStateException
     * will be thrown.
     * </p>
     * @param varianceImpl the StorelessUnivariateStatistic instance to use for
     *        computing the variance
     * @throws MathIllegalStateException if data has already been added (i.e if n > 0)
     * @since 1.2
     */
    public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl) throws MathIllegalStateException {
        checkEmpty();
        this.varianceImpl = varianceImpl;
    }

    /**
     * Throws IllegalStateException if n > 0.
     * @throws MathIllegalStateException if data has been added
     */
    private void checkEmpty() throws MathIllegalStateException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.SummaryStatistics.checkEmpty_666");
        if (ROR_greater(n, 0, "org.apache.commons.math3.stat.descriptive.SummaryStatistics.checkEmpty_666", _mut4704, _mut4705, _mut4706, _mut4707, _mut4708)) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, n);
        }
    }

    /**
     * Returns a copy of this SummaryStatistics instance with the same internal state.
     *
     * @return a copy of this
     */
    public SummaryStatistics copy() {
        SummaryStatistics result = new SummaryStatistics();
        // No try-catch or advertised exception because arguments are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source SummaryStatistics to copy
     * @param dest SummaryStatistics to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SummaryStatistics source, SummaryStatistics dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.maxImpl = source.maxImpl.copy();
        dest.minImpl = source.minImpl.copy();
        dest.sumImpl = source.sumImpl.copy();
        dest.sumLogImpl = source.sumLogImpl.copy();
        dest.sumsqImpl = source.sumsqImpl.copy();
        dest.secondMoment = source.secondMoment.copy();
        dest.n = source.n;
        // Keep commons-math supplied statistics with embedded moments in synch
        if (source.getVarianceImpl() instanceof Variance) {
            dest.varianceImpl = new Variance(dest.secondMoment);
        } else {
            dest.varianceImpl = source.varianceImpl.copy();
        }
        if (source.meanImpl instanceof Mean) {
            dest.meanImpl = new Mean(dest.secondMoment);
        } else {
            dest.meanImpl = source.meanImpl.copy();
        }
        if (source.getGeoMeanImpl() instanceof GeometricMean) {
            dest.geoMeanImpl = new GeometricMean((SumOfLogs) dest.sumLogImpl);
        } else {
            dest.geoMeanImpl = source.geoMeanImpl.copy();
        }
        // holds in dest; otherwise copy stat
        if (source.geoMean == source.geoMeanImpl) {
            dest.geoMean = (GeometricMean) dest.geoMeanImpl;
        } else {
            GeometricMean.copy(source.geoMean, dest.geoMean);
        }
        if (source.max == source.maxImpl) {
            dest.max = (Max) dest.maxImpl;
        } else {
            Max.copy(source.max, dest.max);
        }
        if (source.mean == source.meanImpl) {
            dest.mean = (Mean) dest.meanImpl;
        } else {
            Mean.copy(source.mean, dest.mean);
        }
        if (source.min == source.minImpl) {
            dest.min = (Min) dest.minImpl;
        } else {
            Min.copy(source.min, dest.min);
        }
        if (source.sum == source.sumImpl) {
            dest.sum = (Sum) dest.sumImpl;
        } else {
            Sum.copy(source.sum, dest.sum);
        }
        if (source.variance == source.varianceImpl) {
            dest.variance = (Variance) dest.varianceImpl;
        } else {
            Variance.copy(source.variance, dest.variance);
        }
        if (source.sumLog == source.sumLogImpl) {
            dest.sumLog = (SumOfLogs) dest.sumLogImpl;
        } else {
            SumOfLogs.copy(source.sumLog, dest.sumLog);
        }
        if (source.sumsq == source.sumsqImpl) {
            dest.sumsq = (SumOfSquares) dest.sumsqImpl;
        } else {
            SumOfSquares.copy(source.sumsq, dest.sumsq);
        }
    }
}
