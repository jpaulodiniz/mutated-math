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
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.WeightedEvaluation;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes the variance of the available values.  By default, the unbiased
 * "sample variance" definitional formula is used:
 * <p>
 * variance = sum((x_i - mean)^2) / (n - 1) </p>
 * <p>
 * where mean is the {@link Mean} and <code>n</code> is the number
 * of sample observations.</p>
 * <p>
 * The definitional formula does not have good numerical properties, so
 * this implementation does not compute the statistic using the definitional
 * formula. <ul>
 * <li> The <code>getResult</code> method computes the variance using
 * updating formulas based on West's algorithm, as described in
 * <a href="http://doi.acm.org/10.1145/359146.359152"> Chan, T. F. and
 * J. G. Lewis 1979, <i>Communications of the ACM</i>,
 * vol. 22 no. 9, pp. 526-531.</a></li>
 * <li> The <code>evaluate</code> methods leverage the fact that they have the
 * full array of values in memory to execute a two-pass algorithm.
 * Specifically, these methods use the "corrected two-pass algorithm" from
 * Chan, Golub, Levesque, <i>Algorithms for Computing the Sample Variance</i>,
 * American Statistician, vol. 37, no. 3 (1983) pp. 242-247.</li></ul>
 * Note that adding values using <code>increment</code> or
 * <code>incrementAll</code> and then executing <code>getResult</code> will
 * sometimes give a different, less accurate, result than executing
 * <code>evaluate</code> with the full array of values. The former approach
 * should only be used when the full array of values is not available.</p>
 * <p>
 * The "population variance"  ( sum((x_i - mean)^2) / n ) can also
 * be computed using this statistic.  The <code>isBiasCorrected</code>
 * property determines whether the "population" or "sample" value is
 * returned by the <code>evaluate</code> and <code>getResult</code> methods.
 * To compute population variances, set this property to <code>false.</code>
 * </p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Variance extends AbstractStorelessUnivariateStatistic implements Serializable, WeightedEvaluation {

    @Conditional
    public static boolean _mut2828 = false, _mut2829 = false, _mut2830 = false, _mut2831 = false, _mut2832 = false, _mut2833 = false, _mut2834 = false, _mut2835 = false, _mut2836 = false, _mut2837 = false, _mut2838 = false, _mut2839 = false, _mut2840 = false, _mut2841 = false, _mut2842 = false, _mut2843 = false, _mut2844 = false, _mut2845 = false, _mut2846 = false, _mut2847 = false, _mut2848 = false, _mut2849 = false, _mut2850 = false, _mut2851 = false, _mut2852 = false, _mut2853 = false, _mut2854 = false, _mut2855 = false, _mut2856 = false, _mut2857 = false, _mut2858 = false, _mut2859 = false, _mut2860 = false, _mut2861 = false, _mut2862 = false, _mut2863 = false, _mut2864 = false, _mut2865 = false, _mut2866 = false, _mut2867 = false, _mut2868 = false, _mut2869 = false, _mut2870 = false, _mut2871 = false, _mut2872 = false, _mut2873 = false, _mut2874 = false, _mut2875 = false, _mut2876 = false, _mut2877 = false, _mut2878 = false, _mut2879 = false, _mut2880 = false, _mut2881 = false, _mut2882 = false, _mut2883 = false, _mut2884 = false, _mut2885 = false, _mut2886 = false, _mut2887 = false, _mut2888 = false, _mut2889 = false, _mut2890 = false, _mut2891 = false, _mut2892 = false, _mut2893 = false, _mut2894 = false, _mut2895 = false, _mut2896 = false, _mut2897 = false, _mut2898 = false, _mut2899 = false, _mut2900 = false, _mut2901 = false, _mut2902 = false, _mut2903 = false, _mut2904 = false, _mut2905 = false, _mut2906 = false, _mut2907 = false, _mut2908 = false, _mut2909 = false, _mut2910 = false, _mut2911 = false, _mut2912 = false, _mut2913 = false, _mut2914 = false, _mut2915 = false, _mut2916 = false, _mut2917 = false, _mut2918 = false, _mut2919 = false, _mut2920 = false, _mut2921 = false, _mut2922 = false, _mut2923 = false, _mut2924 = false, _mut2925 = false, _mut2926 = false, _mut2927 = false, _mut2928 = false, _mut2929 = false, _mut2930 = false, _mut2931 = false, _mut2932 = false, _mut2933 = false, _mut2934 = false, _mut2935 = false, _mut2936 = false, _mut2937 = false, _mut2938 = false, _mut2939 = false, _mut2940 = false, _mut2941 = false, _mut2942 = false, _mut2943 = false, _mut2944 = false, _mut2945 = false, _mut2946 = false, _mut2947 = false, _mut2948 = false, _mut2949 = false, _mut2950 = false, _mut2951 = false, _mut2952 = false, _mut2953 = false, _mut2954 = false, _mut2955 = false, _mut2956 = false, _mut2957 = false, _mut2958 = false, _mut2959 = false, _mut2960 = false, _mut2961 = false, _mut2962 = false, _mut2963 = false, _mut2964 = false, _mut2965 = false, _mut2966 = false, _mut2967 = false, _mut2968 = false, _mut2969 = false, _mut2970 = false, _mut2971 = false, _mut2972 = false, _mut2973 = false, _mut2974 = false, _mut2975 = false, _mut2976 = false, _mut2977 = false, _mut2978 = false, _mut2979 = false, _mut2980 = false, _mut2981 = false, _mut2982 = false, _mut2983 = false, _mut2984 = false, _mut2985 = false, _mut2986 = false, _mut2987 = false, _mut2988 = false, _mut2989 = false, _mut2990 = false, _mut2991 = false, _mut2992 = false, _mut2993 = false, _mut2994 = false, _mut2995 = false, _mut2996 = false, _mut2997 = false, _mut2998 = false, _mut2999 = false, _mut3000 = false, _mut3001 = false, _mut3002 = false, _mut3003 = false, _mut3004 = false, _mut3005 = false, _mut3006 = false, _mut3007 = false, _mut3008 = false, _mut3009 = false, _mut3010 = false, _mut3011 = false, _mut3012 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -9111962718267217978L;

    /**
     * SecondMoment is used in incremental calculation of Variance
     */
    protected SecondMoment moment = null;

    /**
     * Whether or not {@link #increment(double)} should increment
     * the internal second moment. When a Variance is constructed with an
     * external SecondMoment as a constructor parameter, this property is
     * set to false and increments must be applied to the second moment
     * directly.
     */
    protected boolean incMoment = true;

    /**
     * Whether or not bias correction is applied when computing the
     * value of the statistic. True means that bias is corrected.  See
     * {@link Variance} for details on the formula.
     */
    private boolean isBiasCorrected = true;

    /**
     * Constructs a Variance with default (true) <code>isBiasCorrected</code>
     * property.
     */
    public Variance() {
        moment = new SecondMoment();
    }

    /**
     * Constructs a Variance based on an external second moment.
     * When this constructor is used, the statistic may only be
     * incremented via the moment, i.e., {@link #increment(double)}
     * does nothing; whereas {@code m2.increment(value)} increments
     * both {@code m2} and the Variance instance constructed from it.
     *
     * @param m2 the SecondMoment (Third or Fourth moments work
     * here as well.)
     */
    public Variance(final SecondMoment m2) {
        incMoment = false;
        this.moment = m2;
    }

    /**
     * Constructs a Variance with the specified <code>isBiasCorrected</code>
     * property
     *
     * @param isBiasCorrected  setting for bias correction - true means
     * bias will be corrected and is equivalent to using the argumentless
     * constructor
     */
    public Variance(boolean isBiasCorrected) {
        moment = new SecondMoment();
        this.isBiasCorrected = isBiasCorrected;
    }

    /**
     * Constructs a Variance with the specified <code>isBiasCorrected</code>
     * property and the supplied external second moment.
     *
     * @param isBiasCorrected  setting for bias correction - true means
     * bias will be corrected
     * @param m2 the SecondMoment (Third or Fourth moments work
     * here as well.)
     */
    public Variance(boolean isBiasCorrected, SecondMoment m2) {
        incMoment = false;
        this.moment = m2;
        this.isBiasCorrected = isBiasCorrected;
    }

    /**
     * Copy constructor, creates a new {@code Variance} identical
     * to the {@code original}
     *
     * @param original the {@code Variance} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Variance(Variance original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>If all values are available, it is more accurate to use
     * {@link #evaluate(double[])} rather than adding values one at a time
     * using this method and then executing {@link #getResult}, since
     * <code>evaluate</code> leverages the fact that is has the full
     * list of values together to execute a two-pass algorithm.
     * See {@link Variance}.</p>
     *
     * <p>Note also that when {@link #Variance(SecondMoment)} is used to
     * create a Variance, this method does nothing. In that case, the
     * SecondMoment should be incremented directly.</p>
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
    public double getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178");
        if (ROR_equals(moment.n, 0, "org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178", _mut2828, _mut2829, _mut2830, _mut2831, _mut2832)) {
            return Double.NaN;
        } else if (ROR_equals(moment.n, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178", _mut2833, _mut2834, _mut2835, _mut2836, _mut2837)) {
            return 0d;
        } else {
            if (isBiasCorrected) {
                return AOR_divide(moment.m2, (AOR_minus(moment.n, 1d, "org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178", _mut2842, _mut2843, _mut2844, _mut2845)), "org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178", _mut2846, _mut2847, _mut2848, _mut2849);
            } else {
                return AOR_divide(moment.m2, (moment.n), "org.apache.commons.math3.stat.descriptive.moment.Variance.getResult_178", _mut2838, _mut2839, _mut2840, _mut2841);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        return moment.getN();
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
     * Returns the variance of the entries in the input array, or
     * <code>Double.NaN</code> if the array is empty.
     * <p>
     * See {@link Variance} for details on the computing algorithm.</p>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     *
     * @param values the input array
     * @return the variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the array is null
     */
    @Override
    public double evaluate(final double[] values) throws MathIllegalArgumentException {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        return evaluate(values, 0, values.length);
    }

    /**
     * Returns the variance of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.  Note that Double.NaN may also be returned if the input
     * includes NaN and / or infinite values.
     * <p>
     * See {@link Variance} for details on the computing algorithm.</p>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_255");
        double var = Double.NaN;
        if (test(values, begin, length)) {
            clear();
            if (ROR_equals(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_255", _mut2850, _mut2851, _mut2852, _mut2853, _mut2854)) {
                var = 0.0;
            } else if (ROR_greater(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_255", _mut2855, _mut2856, _mut2857, _mut2858, _mut2859)) {
                Mean mean = new Mean();
                double m = mean.evaluate(values, begin, length);
                var = evaluate(values, m, begin, length);
            }
        }
        return var;
    }

    /**
     * <p>Returns the weighted variance of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.</p>
     * <p>
     * Uses the formula <pre>
     *   &Sigma;(weights[i]*(values[i] - weightedMean)<sup>2</sup>)/(&Sigma;(weights[i]) - 1)
     * </pre>
     * where weightedMean is the weighted mean</p>
     * <p>
     * This formula will not return the same result as the unweighted variance when all
     * weights are equal, unless all weights are equal to 1. The formula assumes that
     * weights are to be treated as "expansion values," as will be the case if for example
     * the weights represent frequency counts. To normalize weights so that the denominator
     * in the variance computation equals the length of the input vector minus one, use <pre>
     *   <code>evaluate(values, MathArrays.normalizeArray(weights, values.length)); </code>
     * </pre>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
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
     * <p>
     * Does not change the internal state of the statistic.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if either array is null.</p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the weighted variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_316");
        double var = Double.NaN;
        if (test(values, weights, begin, length)) {
            clear();
            if (ROR_equals(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_316", _mut2860, _mut2861, _mut2862, _mut2863, _mut2864)) {
                var = 0.0;
            } else if (ROR_greater(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_316", _mut2865, _mut2866, _mut2867, _mut2868, _mut2869)) {
                Mean mean = new Mean();
                double m = mean.evaluate(values, weights, begin, length);
                var = evaluate(values, weights, m, begin, length);
            }
        }
        return var;
    }

    /**
     * <p>
     * Returns the weighted variance of the entries in the the input array.</p>
     * <p>
     * Uses the formula <pre>
     *   &Sigma;(weights[i]*(values[i] - weightedMean)<sup>2</sup>)/(&Sigma;(weights[i]) - 1)
     * </pre>
     * where weightedMean is the weighted mean</p>
     * <p>
     * This formula will not return the same result as the unweighted variance when all
     * weights are equal, unless all weights are equal to 1. The formula assumes that
     * weights are to be treated as "expansion values," as will be the case if for example
     * the weights represent frequency counts. To normalize weights so that the denominator
     * in the variance computation equals the length of the input vector minus one, use <pre>
     *   <code>evaluate(values, MathArrays.normalizeArray(weights, values.length)); </code>
     * </pre>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     * </ul></p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if either array is null.</p>
     *
     * @param values the input array
     * @param weights the weights array
     * @return the weighted variance of the values
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights) throws MathIllegalArgumentException {
        return evaluate(values, weights, 0, values.length);
    }

    /**
     * Returns the variance of the entries in the specified portion of
     * the input array, using the precomputed mean value.  Returns
     * <code>Double.NaN</code> if the designated subarray is empty.
     * <p>
     * See {@link Variance} for details on the computing algorithm.</p>
     * <p>
     * The formula used assumes that the supplied mean value is the arithmetic
     * mean of the sample data, not a known population parameter.  This method
     * is supplied only to save computation when the mean has already been
     * computed.</p>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     *
     * @param values the input array
     * @param mean the precomputed mean value
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    public double evaluate(final double[] values, final double mean, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403");
        double var = Double.NaN;
        if (test(values, begin, length)) {
            if (ROR_equals(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2870, _mut2871, _mut2872, _mut2873, _mut2874)) {
                var = 0.0;
            } else if (ROR_greater(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2875, _mut2876, _mut2877, _mut2878, _mut2879)) {
                double accum = 0.0;
                double dev = 0.0;
                double accum2 = 0.0;
                for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2888, _mut2889, _mut2890, _mut2891), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2892, _mut2893, _mut2894, _mut2895, _mut2896); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403");
                    dev = AOR_minus(values[i], mean, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2880, _mut2881, _mut2882, _mut2883);
                    accum += AOR_multiply(dev, dev, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2884, _mut2885, _mut2886, _mut2887);
                    accum2 += dev;
                }
                double len = length;
                if (isBiasCorrected) {
                    var = AOR_divide((AOR_minus(accum, (AOR_divide(AOR_multiply(accum2, accum2, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2913, _mut2914, _mut2915, _mut2916), len, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2917, _mut2918, _mut2919, _mut2920)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2921, _mut2922, _mut2923, _mut2924)), (AOR_minus(len, 1.0, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2925, _mut2926, _mut2927, _mut2928)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2929, _mut2930, _mut2931, _mut2932);
                } else {
                    var = AOR_divide((AOR_minus(accum, (AOR_divide(AOR_multiply(accum2, accum2, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2897, _mut2898, _mut2899, _mut2900), len, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2901, _mut2902, _mut2903, _mut2904)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2905, _mut2906, _mut2907, _mut2908)), len, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_403", _mut2909, _mut2910, _mut2911, _mut2912);
                }
            }
        }
        return var;
    }

    /**
     * Returns the variance of the entries in the input array, using the
     * precomputed mean value.  Returns <code>Double.NaN</code> if the array
     * is empty.
     * <p>
     * See {@link Variance} for details on the computing algorithm.</p>
     * <p>
     * If <code>isBiasCorrected</code> is <code>true</code> the formula used
     * assumes that the supplied mean value is the arithmetic mean of the
     * sample data, not a known population parameter.  If the mean is a known
     * population parameter, or if the "population" version of the variance is
     * desired, set <code>isBiasCorrected</code> to <code>false</code> before
     * invoking this method.</p>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     *
     * @param values the input array
     * @param mean the precomputed mean value
     * @return the variance of the values or Double.NaN if the array is empty
     * @throws MathIllegalArgumentException if the array is null
     */
    public double evaluate(final double[] values, final double mean) throws MathIllegalArgumentException {
        return evaluate(values, mean, 0, values.length);
    }

    /**
     * Returns the weighted variance of the entries in the specified portion of
     * the input array, using the precomputed weighted mean value.  Returns
     * <code>Double.NaN</code> if the designated subarray is empty.
     * <p>
     * Uses the formula <pre>
     *   &Sigma;(weights[i]*(values[i] - mean)<sup>2</sup>)/(&Sigma;(weights[i]) - 1)
     * </pre></p>
     * <p>
     * The formula used assumes that the supplied mean value is the weighted arithmetic
     * mean of the sample data, not a known population parameter. This method
     * is supplied only to save computation when the mean has already been
     * computed.</p>
     * <p>
     * This formula will not return the same result as the unweighted variance when all
     * weights are equal, unless all weights are equal to 1. The formula assumes that
     * weights are to be treated as "expansion values," as will be the case if for example
     * the weights represent frequency counts. To normalize weights so that the denominator
     * in the variance computation equals the length of the input vector minus one, use <pre>
     *   <code>evaluate(values, MathArrays.normalizeArray(weights, values.length), mean); </code>
     * </pre>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li>
     * </ul></p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param mean the precomputed weighted mean value
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights, final double mean, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505");
        double var = Double.NaN;
        if (test(values, weights, begin, length)) {
            if (ROR_equals(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2933, _mut2934, _mut2935, _mut2936, _mut2937)) {
                var = 0.0;
            } else if (ROR_greater(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2938, _mut2939, _mut2940, _mut2941, _mut2942)) {
                double accum = 0.0;
                double dev = 0.0;
                double accum2 = 0.0;
                for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2959, _mut2960, _mut2961, _mut2962), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2963, _mut2964, _mut2965, _mut2966, _mut2967); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505");
                    dev = AOR_minus(values[i], mean, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2943, _mut2944, _mut2945, _mut2946);
                    accum += AOR_multiply(weights[i], (AOR_multiply(dev, dev, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2947, _mut2948, _mut2949, _mut2950)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2951, _mut2952, _mut2953, _mut2954);
                    accum2 += AOR_multiply(weights[i], dev, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2955, _mut2956, _mut2957, _mut2958);
                }
                double sumWts = 0;
                for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2968, _mut2969, _mut2970, _mut2971), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2972, _mut2973, _mut2974, _mut2975, _mut2976); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505");
                    sumWts += weights[i];
                }
                if (isBiasCorrected) {
                    var = AOR_divide((AOR_minus(accum, (AOR_divide(AOR_multiply(accum2, accum2, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2993, _mut2994, _mut2995, _mut2996), sumWts, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2997, _mut2998, _mut2999, _mut3000)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut3001, _mut3002, _mut3003, _mut3004)), (AOR_minus(sumWts, 1.0, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut3005, _mut3006, _mut3007, _mut3008)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut3009, _mut3010, _mut3011, _mut3012);
                } else {
                    var = AOR_divide((AOR_minus(accum, (AOR_divide(AOR_multiply(accum2, accum2, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2977, _mut2978, _mut2979, _mut2980), sumWts, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2981, _mut2982, _mut2983, _mut2984)), "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2985, _mut2986, _mut2987, _mut2988)), sumWts, "org.apache.commons.math3.stat.descriptive.moment.Variance.evaluate_505", _mut2989, _mut2990, _mut2991, _mut2992);
                }
            }
        }
        return var;
    }

    /**
     * <p>Returns the weighted variance of the values in the input array, using
     * the precomputed weighted mean value.</p>
     * <p>
     * Uses the formula <pre>
     *   &Sigma;(weights[i]*(values[i] - mean)<sup>2</sup>)/(&Sigma;(weights[i]) - 1)
     * </pre></p>
     * <p>
     * The formula used assumes that the supplied mean value is the weighted arithmetic
     * mean of the sample data, not a known population parameter. This method
     * is supplied only to save computation when the mean has already been
     * computed.</p>
     * <p>
     * This formula will not return the same result as the unweighted variance when all
     * weights are equal, unless all weights are equal to 1. The formula assumes that
     * weights are to be treated as "expansion values," as will be the case if for example
     * the weights represent frequency counts. To normalize weights so that the denominator
     * in the variance computation equals the length of the input vector minus one, use <pre>
     *   <code>evaluate(values, MathArrays.normalizeArray(weights, values.length), mean); </code>
     * </pre>
     * <p>
     * Returns 0 for a single-value (i.e. length = 1) sample.</p>
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     * </ul></p>
     * <p>
     * Does not change the internal state of the statistic.</p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param mean the precomputed weighted mean value
     * @return the variance of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights, final double mean) throws MathIllegalArgumentException {
        return evaluate(values, weights, mean, 0, values.length);
    }

    /**
     * @return Returns the isBiasCorrected.
     */
    public boolean isBiasCorrected() {
        return isBiasCorrected;
    }

    /**
     * @param biasCorrected The isBiasCorrected to set.
     */
    public void setBiasCorrected(boolean biasCorrected) {
        this.isBiasCorrected = biasCorrected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Variance copy() {
        Variance result = new Variance();
        // No try-catch or advertised exception because parameters are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Variance to copy
     * @param dest Variance to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Variance source, Variance dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.moment = source.moment.copy();
        dest.isBiasCorrected = source.isBiasCorrected;
        dest.incMoment = source.incMoment;
    }
}
