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
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 *  Value object representing the results of a univariate statistical summary.
 */
public class StatisticalSummaryValues implements Serializable, StatisticalSummary {

    @Conditional
    public static boolean _mut4472 = false, _mut4473 = false, _mut4474 = false, _mut4475 = false, _mut4476 = false, _mut4477 = false, _mut4478 = false, _mut4479 = false, _mut4480 = false, _mut4481 = false, _mut4482 = false, _mut4483 = false, _mut4484 = false, _mut4485 = false, _mut4486 = false, _mut4487 = false, _mut4488 = false, _mut4489 = false, _mut4490 = false, _mut4491 = false, _mut4492 = false, _mut4493 = false, _mut4494 = false, _mut4495 = false, _mut4496 = false, _mut4497 = false, _mut4498 = false, _mut4499 = false, _mut4500 = false, _mut4501 = false, _mut4502 = false, _mut4503 = false, _mut4504 = false, _mut4505 = false, _mut4506 = false, _mut4507 = false, _mut4508 = false, _mut4509 = false, _mut4510 = false, _mut4511 = false, _mut4512 = false, _mut4513 = false, _mut4514 = false, _mut4515 = false, _mut4516 = false, _mut4517 = false, _mut4518 = false, _mut4519 = false, _mut4520 = false;

    /**
     * Serialization id
     */
    private static final long serialVersionUID = -5108854841843722536L;

    /**
     * The sample mean
     */
    private final double mean;

    /**
     * The sample variance
     */
    private final double variance;

    /**
     * The number of observations in the sample
     */
    private final long n;

    /**
     * The maximum value
     */
    private final double max;

    /**
     * The minimum value
     */
    private final double min;

    /**
     * The sum of the sample values
     */
    private final double sum;

    /**
     * Constructor
     *
     * @param mean  the sample mean
     * @param variance  the sample variance
     * @param n  the number of observations in the sample
     * @param max  the maximum value
     * @param min  the minimum value
     * @param sum  the sum of the values
     */
    public StatisticalSummaryValues(double mean, double variance, long n, double max, double min, double sum) {
        super();
        this.mean = mean;
        this.variance = variance;
        this.n = n;
        this.max = max;
        this.min = min;
        this.sum = sum;
    }

    /**
     * @return Returns the max.
     */
    public double getMax() {
        return max;
    }

    /**
     * @return Returns the mean.
     */
    public double getMean() {
        return mean;
    }

    /**
     * @return Returns the min.
     */
    public double getMin() {
        return min;
    }

    /**
     * @return Returns the number of values.
     */
    public long getN() {
        return n;
    }

    /**
     * @return Returns the sum.
     */
    public double getSum() {
        return sum;
    }

    /**
     * @return Returns the standard deviation
     */
    public double getStandardDeviation() {
        return FastMath.sqrt(variance);
    }

    /**
     * @return Returns the variance.
     */
    public double getVariance() {
        return variance;
    }

    /**
     * Returns true iff <code>object</code> is a
     * <code>StatisticalSummaryValues</code> instance and all statistics have
     *  the same values as this.
     *
     * @param object the object to test equality against.
     * @return true if object equals this
     */
    @Override
    public boolean equals(Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.equals_131");
        if (object == this) {
            return true;
        }
        if (object instanceof StatisticalSummaryValues == false) {
            return false;
        }
        StatisticalSummaryValues stat = (StatisticalSummaryValues) object;
        return (_mut4476 ? ((_mut4475 ? ((_mut4474 ? ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4474 ? ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) || Precision.equalsIncludingNaN(stat.getVariance(), getVariance())) : ((_mut4475 ? ((_mut4474 ? ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) || Precision.equalsIncludingNaN(stat.getSum(), getSum())) : ((_mut4474 ? ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) || Precision.equalsIncludingNaN(stat.getN(), getN())) : ((_mut4473 ? ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) || Precision.equalsIncludingNaN(stat.getMin(), getMin())) : ((_mut4472 ? (Precision.equalsIncludingNaN(stat.getMax(), getMax()) || Precision.equalsIncludingNaN(stat.getMean(), getMean())) : (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()))) && Precision.equalsIncludingNaN(stat.getMin(), getMin()))) && Precision.equalsIncludingNaN(stat.getN(), getN()))) && Precision.equalsIncludingNaN(stat.getSum(), getSum()))) && Precision.equalsIncludingNaN(stat.getVariance(), getVariance())));
    }

    /**
     * Returns hash code based on values of statistics
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153");
        int result = AOR_plus(31, MathUtils.hash(getMax()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4477, _mut4478, _mut4479, _mut4480);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4481, _mut4482, _mut4483, _mut4484), MathUtils.hash(getMean()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4485, _mut4486, _mut4487, _mut4488);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4489, _mut4490, _mut4491, _mut4492), MathUtils.hash(getMin()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4493, _mut4494, _mut4495, _mut4496);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4497, _mut4498, _mut4499, _mut4500), MathUtils.hash(getN()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4501, _mut4502, _mut4503, _mut4504);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4505, _mut4506, _mut4507, _mut4508), MathUtils.hash(getSum()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4509, _mut4510, _mut4511, _mut4512);
        result = AOR_plus(AOR_multiply(result, 31, "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4513, _mut4514, _mut4515, _mut4516), MathUtils.hash(getVariance()), "org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues.hashCode_153", _mut4517, _mut4518, _mut4519, _mut4520);
        return result;
    }

    /**
     * Generates a text report displaying values of statistics.
     * Each statistic is displayed on a separate line.
     *
     * @return String with line feeds displaying statistics
     */
    @Override
    public String toString() {
        StringBuffer outBuffer = new StringBuffer();
        String endl = "\n";
        outBuffer.append("StatisticalSummaryValues:").append(endl);
        outBuffer.append("n: ").append(getN()).append(endl);
        outBuffer.append("min: ").append(getMin()).append(endl);
        outBuffer.append("max: ").append(getMax()).append(endl);
        outBuffer.append("mean: ").append(getMean()).append(endl);
        outBuffer.append("std dev: ").append(getStandardDeviation()).append(endl);
        outBuffer.append("variance: ").append(getVariance()).append(endl);
        outBuffer.append("sum: ").append(getSum()).append(endl);
        return outBuffer.toString();
    }
}
