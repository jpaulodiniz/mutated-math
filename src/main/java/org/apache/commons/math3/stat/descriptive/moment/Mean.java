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
package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Computes the arithmetic mean of a set of values. Uses the definitional
 * formula:</p>
 * <p>
 * mean = sum(x_i) / n
 * </p>
 * <p>where <code>n</code> is the number of observations.
 * </p>
 * <p>When {@link #increment(double)} is used to add data incrementally from a
 * stream of (unstored) values, the value of the statistic that
 * {@link #getResult()} returns is computed using the following recursive
 * updating algorithm: </p>
 * <ol>
 * <li>Initialize <code>m = </code> the first value</li>
 * <li>For each additional value, update using <br>
 *   <code>m = m + (new value - m) / (number of observations)</code></li>
 * </ol>
 * <p> If {@link #evaluate(double[])} is used to compute the mean of an array
 * of stored values, a two-pass, corrected algorithm is used, starting with
 * the definitional formula computed using the array of stored values and then
 * correcting this by adding the mean deviation of the data values from the
 * arithmetic mean. See, e.g. "Comparison of Several Algorithms for Computing
 * Sample Means and Variances," Robert F. Ling, Journal of the American
 * Statistical Association, Vol. 69, No. 348 (Dec., 1974), pp. 859-866. </p>
 * <p>
 *  Returns <code>Double.NaN</code> if the dataset is empty. Note that
 *  Double.NaN may also be returned if the input includes NaN and / or infinite
 *  values.
 * </p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.
 */
public class Mean extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {

    @Conditional
    public static boolean _mut2774 = false, _mut2775 = false, _mut2776 = false, _mut2777 = false, _mut2778 = false, _mut2779 = false, _mut2780 = false, _mut2781 = false, _mut2782 = false, _mut2783 = false, _mut2784 = false, _mut2785 = false, _mut2786 = false, _mut2787 = false, _mut2788 = false, _mut2789 = false, _mut2790 = false, _mut2791 = false, _mut2792 = false, _mut2793 = false, _mut2794 = false, _mut2795 = false, _mut2796 = false, _mut2797 = false, _mut2798 = false, _mut2799 = false, _mut2800 = false, _mut2801 = false, _mut2802 = false, _mut2803 = false, _mut2804 = false, _mut2805 = false, _mut2806 = false, _mut2807 = false, _mut2808 = false, _mut2809 = false, _mut2810 = false, _mut2811 = false, _mut2812 = false, _mut2813 = false, _mut2814 = false, _mut2815 = false, _mut2816 = false, _mut2817 = false, _mut2818 = false, _mut2819 = false, _mut2820 = false, _mut2821 = false, _mut2822 = false, _mut2823 = false, _mut2824 = false, _mut2825 = false, _mut2826 = false, _mut2827 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -1296043746617791564L;

    /**
     * First moment on which this statistic is based.
     */
    protected FirstMoment moment;

    /**
     * Determines whether or not this statistic can be incremented or cleared.
     * <p>
     * Statistics based on (constructed from) external moments cannot
     * be incremented or cleared.</p>
     */
    protected boolean incMoment;

    /**
     * Constructs a Mean.
     */
    public Mean() {
        incMoment = true;
        moment = new FirstMoment();
    }

    /**
     * Constructs a Mean with an External Moment.
     *
     * @param m1 the moment
     */
    public Mean(final FirstMoment m1) {
        this.moment = m1;
        incMoment = false;
    }

    /**
     * Copy constructor, creates a new {@code Mean} identical
     * to the {@code original}
     *
     * @param original the {@code Mean} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Mean(Mean original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>Note that when {@link #Mean(FirstMoment)} is used to
     * create a Mean, this method does nothing. In that case, the
     * FirstMoment should be incremented directly.</p>
     */
    @Override
    public void increment(final double d) {
        if (incMoment) {
            moment.increment(d);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        if (incMoment) {
            moment.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return moment.m1;
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        return moment.getN();
    }

    /**
     * Returns the arithmetic mean of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.
     * <p>
     * Throws <code>IllegalArgumentException</code> if the array is null.</p>
     * <p>
     * See {@link Mean} for details on the computing algorithm.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the mean of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161");
        if (test(values, begin, length)) {
            Sum sum = new Sum();
            double sampleSize = length;
            // Compute initial estimate using definitional formula
            double xbar = AOR_divide(sum.evaluate(values, begin, length), sampleSize, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2774, _mut2775, _mut2776, _mut2777);
            // Compute correction factor in second pass
            double correction = 0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2782, _mut2783, _mut2784, _mut2785), "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2786, _mut2787, _mut2788, _mut2789, _mut2790); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161");
                correction += AOR_minus(values[i], xbar, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2778, _mut2779, _mut2780, _mut2781);
            }
            return AOR_plus(xbar, (AOR_divide(correction, sampleSize, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2791, _mut2792, _mut2793, _mut2794)), "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_161", _mut2795, _mut2796, _mut2797, _mut2798);
        }
        return Double.NaN;
    }

    /**
     * Returns the weighted arithmetic mean of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.
     * <p>
     * Throws <code>IllegalArgumentException</code> if either array is null.</p>
     * <p>
     * See {@link Mean} for details on the computing algorithm. The two-pass algorithm
     * described above is used here, with weights applied in computing both the original
     * estimate and the correction factor.</p>
     * <p>
     * Throws <code>IllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li>
     * </ul></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the mean of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210");
        if (test(values, weights, begin, length)) {
            Sum sum = new Sum();
            // Compute initial estimate using definitional formula
            double sumw = sum.evaluate(weights, begin, length);
            double xbarw = AOR_divide(sum.evaluate(values, weights, begin, length), sumw, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2799, _mut2800, _mut2801, _mut2802);
            // Compute correction factor in second pass
            double correction = 0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2811, _mut2812, _mut2813, _mut2814), "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2815, _mut2816, _mut2817, _mut2818, _mut2819); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210");
                correction += AOR_multiply(weights[i], (AOR_minus(values[i], xbarw, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2803, _mut2804, _mut2805, _mut2806)), "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2807, _mut2808, _mut2809, _mut2810);
            }
            return AOR_plus(xbarw, (AOR_divide(correction, sumw, "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2820, _mut2821, _mut2822, _mut2823)), "org.apache.commons.math3.stat.descriptive.moment.Mean.evaluate_210", _mut2824, _mut2825, _mut2826, _mut2827);
        }
        return Double.NaN;
    }

    /**
     * Returns the weighted arithmetic mean of the entries in the input array.
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if either array is null.</p>
     * <p>
     * See {@link Mean} for details on the computing algorithm. The two-pass algorithm
     * described above is used here, with weights applied in computing both the original
     * estimate and the correction factor.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     * </ul></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @return the mean of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights) throws MathIllegalArgumentException {
        return evaluate(values, weights, 0, values.length);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mean copy() {
        Mean result = new Mean();
        // No try-catch or advertised exception because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Mean to copy
     * @param dest Mean to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Mean source, Mean dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.incMoment = source.incMoment;
        dest.moment = source.moment.copy();
    }
}
