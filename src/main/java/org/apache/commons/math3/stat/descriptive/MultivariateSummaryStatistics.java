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
import java.util.Arrays;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.descriptive.moment.GeometricMean;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfLogs;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Computes summary statistics for a stream of n-tuples added using the
 * {@link #addValue(double[]) addValue} method. The data values are not stored
 * in memory, so this class can be used to compute statistics for very large
 * n-tuple streams.</p>
 *
 * <p>The {@link StorelessUnivariateStatistic} instances used to maintain
 * summary state and compute statistics are configurable via setters.
 * For example, the default implementation for the mean can be overridden by
 * calling {@link #setMeanImpl(StorelessUnivariateStatistic[])}. Actual
 * parameters to these methods must implement the
 * {@link StorelessUnivariateStatistic} interface and configuration must be
 * completed before <code>addValue</code> is called. No configuration is
 * necessary to use the default, commons-math provided implementations.</p>
 *
 * <p>To compute statistics for a stream of n-tuples, construct a
 * MultivariateStatistics instance with dimension n and then use
 * {@link #addValue(double[])} to add n-tuples. The <code>getXxx</code>
 * methods where Xxx is a statistic return an array of <code>double</code>
 * values, where for <code>i = 0,...,n-1</code> the i<sup>th</sup> array element is the
 * value of the given statistic for data range consisting of the i<sup>th</sup> element of
 * each of the input n-tuples.  For example, if <code>addValue</code> is called
 * with actual parameters {0, 1, 2}, then {3, 4, 5} and finally {6, 7, 8},
 * <code>getSum</code> will return a three-element array with values
 * {0+3+6, 1+4+7, 2+5+8}</p>
 *
 * <p>Note: This class is not thread-safe. Use
 * {@link SynchronizedMultivariateSummaryStatistics} if concurrent access from multiple
 * threads is required.</p>
 *
 * @since 1.2
 */
public class MultivariateSummaryStatistics implements StatisticalMultivariateSummary, Serializable {

    @Conditional
    public static boolean _mut4311 = false, _mut4312 = false, _mut4313 = false, _mut4314 = false, _mut4315 = false, _mut4316 = false, _mut4317 = false, _mut4318 = false, _mut4319 = false, _mut4320 = false, _mut4321 = false, _mut4322 = false, _mut4323 = false, _mut4324 = false, _mut4325 = false, _mut4326 = false, _mut4327 = false, _mut4328 = false, _mut4329 = false, _mut4330 = false, _mut4331 = false, _mut4332 = false, _mut4333 = false, _mut4334 = false, _mut4335 = false, _mut4336 = false, _mut4337 = false, _mut4338 = false, _mut4339 = false, _mut4340 = false, _mut4341 = false, _mut4342 = false, _mut4343 = false, _mut4344 = false, _mut4345 = false, _mut4346 = false, _mut4347 = false, _mut4348 = false, _mut4349 = false, _mut4350 = false, _mut4351 = false, _mut4352 = false, _mut4353 = false, _mut4354 = false, _mut4355 = false, _mut4356 = false, _mut4357 = false, _mut4358 = false, _mut4359 = false, _mut4360 = false, _mut4361 = false, _mut4362 = false, _mut4363 = false, _mut4364 = false, _mut4365 = false, _mut4366 = false, _mut4367 = false, _mut4368 = false, _mut4369 = false, _mut4370 = false, _mut4371 = false, _mut4372 = false, _mut4373 = false, _mut4374 = false, _mut4375 = false, _mut4376 = false, _mut4377 = false, _mut4378 = false, _mut4379 = false, _mut4380 = false, _mut4381 = false, _mut4382 = false, _mut4383 = false, _mut4384 = false, _mut4385 = false, _mut4386 = false, _mut4387 = false, _mut4388 = false, _mut4389 = false, _mut4390 = false, _mut4391 = false, _mut4392 = false, _mut4393 = false, _mut4394 = false, _mut4395 = false, _mut4396 = false, _mut4397 = false, _mut4398 = false, _mut4399 = false, _mut4400 = false, _mut4401 = false, _mut4402 = false, _mut4403 = false, _mut4404 = false, _mut4405 = false, _mut4406 = false, _mut4407 = false, _mut4408 = false, _mut4409 = false, _mut4410 = false, _mut4411 = false, _mut4412 = false, _mut4413 = false, _mut4414 = false, _mut4415 = false, _mut4416 = false, _mut4417 = false, _mut4418 = false, _mut4419 = false, _mut4420 = false, _mut4421 = false, _mut4422 = false, _mut4423 = false, _mut4424 = false, _mut4425 = false, _mut4426 = false, _mut4427 = false, _mut4428 = false, _mut4429 = false, _mut4430 = false, _mut4431 = false, _mut4432 = false, _mut4433 = false, _mut4434 = false, _mut4435 = false, _mut4436 = false, _mut4437 = false, _mut4438 = false, _mut4439 = false, _mut4440 = false, _mut4441 = false, _mut4442 = false, _mut4443 = false, _mut4444 = false, _mut4445 = false, _mut4446 = false, _mut4447 = false, _mut4448 = false, _mut4449 = false;

    /**
     * Serialization UID
     */
    private static final long serialVersionUID = 2271900808994826718L;

    /**
     * Dimension of the data.
     */
    private int k;

    /**
     * Count of values that have been added
     */
    private long n = 0;

    /**
     * Sum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumImpl;

    /**
     * Sum of squares statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumSqImpl;

    /**
     * Minimum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] minImpl;

    /**
     * Maximum statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] maxImpl;

    /**
     * Sum of log statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] sumLogImpl;

    /**
     * Geometric mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] geoMeanImpl;

    /**
     * Mean statistic implementation - can be reset by setter.
     */
    private StorelessUnivariateStatistic[] meanImpl;

    /**
     * Covariance statistic implementation - cannot be reset.
     */
    private VectorialCovariance covarianceImpl;

    /**
     * Construct a MultivariateSummaryStatistics instance
     * @param k dimension of the data
     * @param isCovarianceBiasCorrected if true, the unbiased sample
     * covariance is computed, otherwise the biased population covariance
     * is computed
     */
    public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.MultivariateSummaryStatistics_114");
        this.k = k;
        sumImpl = new StorelessUnivariateStatistic[k];
        sumSqImpl = new StorelessUnivariateStatistic[k];
        minImpl = new StorelessUnivariateStatistic[k];
        maxImpl = new StorelessUnivariateStatistic[k];
        sumLogImpl = new StorelessUnivariateStatistic[k];
        geoMeanImpl = new StorelessUnivariateStatistic[k];
        meanImpl = new StorelessUnivariateStatistic[k];
        for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.MultivariateSummaryStatistics_114", _mut4311, _mut4312, _mut4313, _mut4314, _mut4315); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.MultivariateSummaryStatistics_114");
            sumImpl[i] = new Sum();
            sumSqImpl[i] = new SumOfSquares();
            minImpl[i] = new Min();
            maxImpl[i] = new Max();
            sumLogImpl[i] = new SumOfLogs();
            geoMeanImpl[i] = new GeometricMean();
            meanImpl[i] = new Mean();
        }
        covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
    }

    /**
     * Add an n-tuple to the data
     *
     * @param value  the n-tuple to add
     * @throws DimensionMismatchException if the length of the array
     * does not match the one used at construction
     */
    public void addValue(double[] value) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.addValue_147");
        checkDimension(value.length);
        for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.addValue_147", _mut4316, _mut4317, _mut4318, _mut4319, _mut4320); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.addValue_147");
            double v = value[i];
            sumImpl[i].increment(v);
            sumSqImpl[i].increment(v);
            minImpl[i].increment(v);
            maxImpl[i].increment(v);
            sumLogImpl[i].increment(v);
            geoMeanImpl[i].increment(v);
            meanImpl[i].increment(v);
        }
        covarianceImpl.increment(value);
        n++;
    }

    /**
     * Returns the dimension of the data
     * @return The dimension of the data
     */
    public int getDimension() {
        return k;
    }

    /**
     * Returns the number of available values
     * @return The number of available values
     */
    public long getN() {
        return n;
    }

    /**
     * Returns an array of the results of a statistic.
     * @param stats univariate statistic array
     * @return results array
     */
    private double[] getResults(StorelessUnivariateStatistic[] stats) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getResults_184");
        double[] results = new double[stats.length];
        for (int i = 0; ROR_less(i, results.length, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getResults_184", _mut4321, _mut4322, _mut4323, _mut4324, _mut4325); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getResults_184");
            results[i] = stats[i].getResult();
        }
        return results;
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component sums
     */
    public double[] getSum() {
        return getResults(sumImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of squares of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component sums of squares
     */
    public double[] getSumSq() {
        return getResults(sumSqImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the sum of logs of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component log sums
     */
    public double[] getSumLog() {
        return getResults(sumLogImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the mean of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component means
     */
    public double[] getMean() {
        return getResults(meanImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the standard deviation of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component standard deviations
     */
    public double[] getStandardDeviation() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getStandardDeviation_243");
        double[] stdDev = new double[k];
        if (ROR_less(getN(), 1, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getStandardDeviation_243", _mut4326, _mut4327, _mut4328, _mut4329, _mut4330)) {
            Arrays.fill(stdDev, Double.NaN);
        } else if (ROR_less(getN(), 2, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getStandardDeviation_243", _mut4331, _mut4332, _mut4333, _mut4334, _mut4335)) {
            Arrays.fill(stdDev, 0.0);
        } else {
            RealMatrix matrix = covarianceImpl.getResult();
            for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getStandardDeviation_243", _mut4336, _mut4337, _mut4338, _mut4339, _mut4340); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.getStandardDeviation_243");
                stdDev[i] = FastMath.sqrt(matrix.getEntry(i, i));
            }
        }
        return stdDev;
    }

    /**
     * Returns the covariance matrix of the values that have been added.
     *
     * @return the covariance matrix
     */
    public RealMatrix getCovariance() {
        return covarianceImpl.getResult();
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the maximum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component maxima
     */
    public double[] getMax() {
        return getResults(maxImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the minimum of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component minima
     */
    public double[] getMin() {
        return getResults(minImpl);
    }

    /**
     * Returns an array whose i<sup>th</sup> entry is the geometric mean of the
     * i<sup>th</sup> entries of the arrays that have been added using
     * {@link #addValue(double[])}
     *
     * @return the array of component geometric means
     */
    public double[] getGeometricMean() {
        return getResults(geoMeanImpl);
    }

    /**
     * Generates a text report displaying
     * summary statistics from values that
     * have been added.
     * @return String with line feeds displaying statistics
     */
    @Override
    public String toString() {
        final String separator = ", ";
        final String suffix = System.getProperty("line.separator");
        StringBuilder outBuffer = new StringBuilder();
        outBuffer.append("MultivariateSummaryStatistics:" + suffix);
        outBuffer.append("n: " + getN() + suffix);
        append(outBuffer, getMin(), "min: ", separator, suffix);
        append(outBuffer, getMax(), "max: ", separator, suffix);
        append(outBuffer, getMean(), "mean: ", separator, suffix);
        append(outBuffer, getGeometricMean(), "geometric mean: ", separator, suffix);
        append(outBuffer, getSumSq(), "sum of squares: ", separator, suffix);
        append(outBuffer, getSumLog(), "sum of logarithms: ", separator, suffix);
        append(outBuffer, getStandardDeviation(), "standard deviation: ", separator, suffix);
        outBuffer.append("covariance: " + getCovariance().toString() + suffix);
        return outBuffer.toString();
    }

    /**
     * Append a text representation of an array to a buffer.
     * @param buffer buffer to fill
     * @param data data array
     * @param prefix text prefix
     * @param separator elements separator
     * @param suffix text suffix
     */
    private void append(StringBuilder buffer, double[] data, String prefix, String separator, String suffix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.append_332");
        buffer.append(prefix);
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.append_332", _mut4346, _mut4347, _mut4348, _mut4349, _mut4350); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.append_332");
            if (ROR_greater(i, 0, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.append_332", _mut4341, _mut4342, _mut4343, _mut4344, _mut4345)) {
                buffer.append(separator);
            }
            buffer.append(data[i]);
        }
        buffer.append(suffix);
    }

    /**
     * Resets all statistics and storage
     */
    public void clear() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.clear_347");
        this.n = 0;
        for (int i = 0; ROR_less(i, k, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.clear_347", _mut4351, _mut4352, _mut4353, _mut4354, _mut4355); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.clear_347");
            minImpl[i].clear();
            maxImpl[i].clear();
            sumImpl[i].clear();
            sumLogImpl[i].clear();
            sumSqImpl[i].clear();
            geoMeanImpl[i].clear();
            meanImpl[i].clear();
        }
        covarianceImpl.clear();
    }

    /**
     * Returns true iff <code>object</code> is a <code>MultivariateSummaryStatistics</code>
     * instance and all statistics have the same values as this.
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.equals_367");
        if (object == this) {
            return true;
        }
        if (object instanceof MultivariateSummaryStatistics == false) {
            return false;
        }
        MultivariateSummaryStatistics stat = (MultivariateSummaryStatistics) object;
        return (_mut4363 ? ((_mut4362 ? ((_mut4361 ? ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) || MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq())) : ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) && MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq()))) || MathArrays.equalsIncludingNaN(stat.getSumLog(), getSumLog())) : ((_mut4361 ? ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) || MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq())) : ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) && MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq()))) && MathArrays.equalsIncludingNaN(stat.getSumLog(), getSumLog()))) || stat.getCovariance().equals(getCovariance())) : ((_mut4362 ? ((_mut4361 ? ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) || MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq())) : ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) && MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq()))) || MathArrays.equalsIncludingNaN(stat.getSumLog(), getSumLog())) : ((_mut4361 ? ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) || MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq())) : ((_mut4360 ? ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || MathArrays.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4359 ? ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4358 ? ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) || MathArrays.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4357 ? ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) || MathArrays.equalsIncludingNaN(stat.getMean(), getMean())) : ((_mut4356 ? (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) || MathArrays.equalsIncludingNaN(stat.getMax(), getMax())) : (MathArrays.equalsIncludingNaN(stat.getGeometricMean(), getGeometricMean()) && MathArrays.equalsIncludingNaN(stat.getMax(), getMax()))) && MathArrays.equalsIncludingNaN(stat.getMean(), getMean()))) && MathArrays.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && MathArrays.equalsIncludingNaN(stat.getSum(), getSum()))) && MathArrays.equalsIncludingNaN(stat.getSumSq(), getSumSq()))) && MathArrays.equalsIncludingNaN(stat.getSumLog(), getSumLog()))) && stat.getCovariance().equals(getCovariance())));
    }

    /**
     * Returns hash code based on values of statistics
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392");
        int result = AOR_plus(31, MathUtils.hash(getGeometricMean()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4364, _mut4365, _mut4366, _mut4367);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4368, _mut4369, _mut4370, _mut4371), MathUtils.hash(getGeometricMean()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4372, _mut4373, _mut4374, _mut4375);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4376, _mut4377, _mut4378, _mut4379), MathUtils.hash(getMax()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4380, _mut4381, _mut4382, _mut4383);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4384, _mut4385, _mut4386, _mut4387), MathUtils.hash(getMean()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4388, _mut4389, _mut4390, _mut4391);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4392, _mut4393, _mut4394, _mut4395), MathUtils.hash(getMin()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4396, _mut4397, _mut4398, _mut4399);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4400, _mut4401, _mut4402, _mut4403), MathUtils.hash(getN()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4404, _mut4405, _mut4406, _mut4407);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4408, _mut4409, _mut4410, _mut4411), MathUtils.hash(getSum()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4412, _mut4413, _mut4414, _mut4415);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4416, _mut4417, _mut4418, _mut4419), MathUtils.hash(getSumSq()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4420, _mut4421, _mut4422, _mut4423);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4424, _mut4425, _mut4426, _mut4427), MathUtils.hash(getSumLog()), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4428, _mut4429, _mut4430, _mut4431);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4432, _mut4433, _mut4434, _mut4435), getCovariance().hashCode(), "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.hashCode_392", _mut4436, _mut4437, _mut4438, _mut4439);
        return result;
    }

    /**
     * Sets statistics implementations.
     * @param newImpl new implementations for statistics
     * @param oldImpl old implementations for statistics
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     * (i.e. if n > 0)
     */
    private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl) throws MathIllegalStateException, DimensionMismatchException {
        checkEmpty();
        checkDimension(newImpl.length);
        System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
    }

    /**
     * Returns the currently configured Sum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the sum
     */
    public StorelessUnivariateStatistic[] getSumImpl() {
        return sumImpl.clone();
    }

    /**
     * <p>Sets the implementation for the Sum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumImpl the StorelessUnivariateStatistic instance to use
     * for computing the Sum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumImpl(StorelessUnivariateStatistic[] sumImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(sumImpl, this.sumImpl);
    }

    /**
     * Returns the currently configured sum of squares implementation
     *
     * @return the StorelessUnivariateStatistic implementing the sum of squares
     */
    public StorelessUnivariateStatistic[] getSumsqImpl() {
        return sumSqImpl.clone();
    }

    /**
     * <p>Sets the implementation for the sum of squares.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumsqImpl the StorelessUnivariateStatistic instance to use
     * for computing the sum of squares
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(sumsqImpl, this.sumSqImpl);
    }

    /**
     * Returns the currently configured minimum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the minimum
     */
    public StorelessUnivariateStatistic[] getMinImpl() {
        return minImpl.clone();
    }

    /**
     * <p>Sets the implementation for the minimum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param minImpl the StorelessUnivariateStatistic instance to use
     * for computing the minimum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMinImpl(StorelessUnivariateStatistic[] minImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(minImpl, this.minImpl);
    }

    /**
     * Returns the currently configured maximum implementation
     *
     * @return the StorelessUnivariateStatistic implementing the maximum
     */
    public StorelessUnivariateStatistic[] getMaxImpl() {
        return maxImpl.clone();
    }

    /**
     * <p>Sets the implementation for the maximum.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param maxImpl the StorelessUnivariateStatistic instance to use
     * for computing the maximum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(maxImpl, this.maxImpl);
    }

    /**
     * Returns the currently configured sum of logs implementation
     *
     * @return the StorelessUnivariateStatistic implementing the log sum
     */
    public StorelessUnivariateStatistic[] getSumLogImpl() {
        return sumLogImpl.clone();
    }

    /**
     * <p>Sets the implementation for the sum of logs.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param sumLogImpl the StorelessUnivariateStatistic instance to use
     * for computing the log sum
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(sumLogImpl, this.sumLogImpl);
    }

    /**
     * Returns the currently configured geometric mean implementation
     *
     * @return the StorelessUnivariateStatistic implementing the geometric mean
     */
    public StorelessUnivariateStatistic[] getGeoMeanImpl() {
        return geoMeanImpl.clone();
    }

    /**
     * <p>Sets the implementation for the geometric mean.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param geoMeanImpl the StorelessUnivariateStatistic instance to use
     * for computing the geometric mean
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(geoMeanImpl, this.geoMeanImpl);
    }

    /**
     * Returns the currently configured mean implementation
     *
     * @return the StorelessUnivariateStatistic implementing the mean
     */
    public StorelessUnivariateStatistic[] getMeanImpl() {
        return meanImpl.clone();
    }

    /**
     * <p>Sets the implementation for the mean.</p>
     * <p>This method must be activated before any data has been added - i.e.,
     * before {@link #addValue(double[]) addValue} has been used to add data;
     * otherwise an IllegalStateException will be thrown.</p>
     *
     * @param meanImpl the StorelessUnivariateStatistic instance to use
     * for computing the mean
     * @throws DimensionMismatchException if the array dimension
     * does not match the one used at construction
     * @throws MathIllegalStateException if data has already been added
     *  (i.e if n > 0)
     */
    public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl) throws MathIllegalStateException, DimensionMismatchException {
        setImpl(meanImpl, this.meanImpl);
    }

    /**
     * Throws MathIllegalStateException if the statistic is not empty.
     * @throws MathIllegalStateException if n > 0.
     */
    private void checkEmpty() throws MathIllegalStateException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.checkEmpty_618");
        if (ROR_greater(n, 0, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.checkEmpty_618", _mut4440, _mut4441, _mut4442, _mut4443, _mut4444)) {
            throw new MathIllegalStateException(LocalizedFormats.VALUES_ADDED_BEFORE_CONFIGURING_STATISTIC, n);
        }
    }

    /**
     * Throws DimensionMismatchException if dimension != k.
     * @param dimension dimension to check
     * @throws DimensionMismatchException if dimension != k
     */
    private void checkDimension(int dimension) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.checkDimension_630");
        if (ROR_not_equals(dimension, k, "org.apache.commons.math3.stat.descriptive.MultivariateSummaryStatistics.checkDimension_630", _mut4445, _mut4446, _mut4447, _mut4448, _mut4449)) {
            throw new DimensionMismatchException(dimension, k);
        }
    }
}
