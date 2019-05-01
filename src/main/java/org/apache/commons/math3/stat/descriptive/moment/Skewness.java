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
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes the skewness of the available values.
 * <p>
 * We use the following (unbiased) formula to define skewness:</p>
 * <p>
 * skewness = [n / (n -1) (n - 2)] sum[(x_i - mean)^3] / std^3 </p>
 * <p>
 * where n is the number of values, mean is the {@link Mean} and std is the
 * {@link StandardDeviation} </p>
 * <p>
 * Note that this statistic is undefined for n < 3.  <code>Double.Nan</code>
 * is returned when there is not sufficient data to compute the statistic.
 * Double.NaN may also be returned if the input includes NaN and / or
 * infinite values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally. </p>
 */
public class Skewness extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut3026 = false, _mut3027 = false, _mut3028 = false, _mut3029 = false, _mut3030 = false, _mut3031 = false, _mut3032 = false, _mut3033 = false, _mut3034 = false, _mut3035 = false, _mut3036 = false, _mut3037 = false, _mut3038 = false, _mut3039 = false, _mut3040 = false, _mut3041 = false, _mut3042 = false, _mut3043 = false, _mut3044 = false, _mut3045 = false, _mut3046 = false, _mut3047 = false, _mut3048 = false, _mut3049 = false, _mut3050 = false, _mut3051 = false, _mut3052 = false, _mut3053 = false, _mut3054 = false, _mut3055 = false, _mut3056 = false, _mut3057 = false, _mut3058 = false, _mut3059 = false, _mut3060 = false, _mut3061 = false, _mut3062 = false, _mut3063 = false, _mut3064 = false, _mut3065 = false, _mut3066 = false, _mut3067 = false, _mut3068 = false, _mut3069 = false, _mut3070 = false, _mut3071 = false, _mut3072 = false, _mut3073 = false, _mut3074 = false, _mut3075 = false, _mut3076 = false, _mut3077 = false, _mut3078 = false, _mut3079 = false, _mut3080 = false, _mut3081 = false, _mut3082 = false, _mut3083 = false, _mut3084 = false, _mut3085 = false, _mut3086 = false, _mut3087 = false, _mut3088 = false, _mut3089 = false, _mut3090 = false, _mut3091 = false, _mut3092 = false, _mut3093 = false, _mut3094 = false, _mut3095 = false, _mut3096 = false, _mut3097 = false, _mut3098 = false, _mut3099 = false, _mut3100 = false, _mut3101 = false, _mut3102 = false, _mut3103 = false, _mut3104 = false, _mut3105 = false, _mut3106 = false, _mut3107 = false, _mut3108 = false, _mut3109 = false, _mut3110 = false, _mut3111 = false, _mut3112 = false, _mut3113 = false, _mut3114 = false, _mut3115 = false, _mut3116 = false, _mut3117 = false, _mut3118 = false, _mut3119 = false, _mut3120 = false, _mut3121 = false, _mut3122 = false, _mut3123 = false, _mut3124 = false, _mut3125 = false, _mut3126 = false, _mut3127 = false, _mut3128 = false, _mut3129 = false, _mut3130 = false, _mut3131 = false, _mut3132 = false, _mut3133 = false, _mut3134 = false, _mut3135 = false, _mut3136 = false, _mut3137 = false, _mut3138 = false, _mut3139 = false, _mut3140 = false, _mut3141 = false, _mut3142 = false, _mut3143 = false, _mut3144 = false, _mut3145 = false, _mut3146 = false, _mut3147 = false, _mut3148 = false, _mut3149 = false, _mut3150 = false, _mut3151 = false, _mut3152 = false, _mut3153 = false, _mut3154 = false, _mut3155 = false, _mut3156 = false, _mut3157 = false, _mut3158 = false, _mut3159 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 7101857578996691352L;

    /**
     * Third moment on which this statistic is based
     */
    protected ThirdMoment moment = null;

    /**
     * Determines whether or not this statistic can be incremented or cleared.
     * <p>
     * Statistics based on (constructed from) external moments cannot
     * be incremented or cleared.</p>
     */
    protected boolean incMoment;

    /**
     * Constructs a Skewness
     */
    public Skewness() {
        incMoment = true;
        moment = new ThirdMoment();
    }

    /**
     * Constructs a Skewness with an external moment
     * @param m3 external moment
     */
    public Skewness(final ThirdMoment m3) {
        incMoment = false;
        this.moment = m3;
    }

    /**
     * Copy constructor, creates a new {@code Skewness} identical
     * to the {@code original}
     *
     * @param original the {@code Skewness} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Skewness(Skewness original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     * <p>Note that when {@link #Skewness(ThirdMoment)} is used to
     * create a Skewness, this method does nothing. In that case, the
     * ThirdMoment should be incremented directly.</p>
     */
    @Override
    public void increment(final double d) {
        if (incMoment) {
            moment.increment(d);
        }
    }

    /**
     * Returns the value of the statistic based on the values that have been added.
     * <p>
     * See {@link Skewness} for the definition used in the computation.</p>
     *
     * @return the skewness of the available values.
     */
    @Override
    public double getResult() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112");
        if (ROR_less(moment.n, 3, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3026, _mut3027, _mut3028, _mut3029, _mut3030)) {
            return Double.NaN;
        }
        double variance = AOR_divide(moment.m2, (AOR_minus(moment.n, 1, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3031, _mut3032, _mut3033, _mut3034)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3035, _mut3036, _mut3037, _mut3038);
        if (ROR_less(variance, 10E-20, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3039, _mut3040, _mut3041, _mut3042, _mut3043)) {
            return 0.0d;
        } else {
            double n0 = moment.getN();
            return AOR_divide((AOR_multiply(n0, moment.m3, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3044, _mut3045, _mut3046, _mut3047)), (AOR_multiply(AOR_multiply(AOR_multiply((AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3048, _mut3049, _mut3050, _mut3051)), (AOR_minus(n0, 2, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3052, _mut3053, _mut3054, _mut3055)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3056, _mut3057, _mut3058, _mut3059), FastMath.sqrt(variance), "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3060, _mut3061, _mut3062, _mut3063), variance, "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3064, _mut3065, _mut3066, _mut3067)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.getResult_112", _mut3068, _mut3069, _mut3070, _mut3071);
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
     * Returns the Skewness of the entries in the specifed portion of the
     * input array.
     * <p>
     * See {@link Skewness} for the definition used in the computation.</p>
     * <p>
     * Throws <code>IllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin the index of the first array element to include
     * @param length the number of elements to include
     * @return the skewness of the values or Double.NaN if length is less than
     * 3
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161");
        // Initialize the skewness
        double skew = Double.NaN;
        if ((_mut3077 ? (test(values, begin, length) || ROR_greater(length, 2, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3072, _mut3073, _mut3074, _mut3075, _mut3076)) : (test(values, begin, length) && ROR_greater(length, 2, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3072, _mut3073, _mut3074, _mut3075, _mut3076)))) {
            Mean mean = new Mean();
            // Get the mean and the standard deviation
            double m = mean.evaluate(values, begin, length);
            // a duplicate pass to get the mean
            double accum = 0.0;
            double accum2 = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3086, _mut3087, _mut3088, _mut3089), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3090, _mut3091, _mut3092, _mut3093, _mut3094); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161");
                final double d = AOR_minus(values[i], m, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3078, _mut3079, _mut3080, _mut3081);
                accum += AOR_multiply(d, d, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3082, _mut3083, _mut3084, _mut3085);
                accum2 += d;
            }
            final double variance = AOR_divide((AOR_minus(accum, (AOR_divide(AOR_multiply(accum2, accum2, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3095, _mut3096, _mut3097, _mut3098), length, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3099, _mut3100, _mut3101, _mut3102)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3103, _mut3104, _mut3105, _mut3106)), (AOR_minus(length, 1, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3107, _mut3108, _mut3109, _mut3110)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3111, _mut3112, _mut3113, _mut3114);
            double accum3 = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3127, _mut3128, _mut3129, _mut3130), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3131, _mut3132, _mut3133, _mut3134, _mut3135); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161");
                final double d = AOR_minus(values[i], m, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3115, _mut3116, _mut3117, _mut3118);
                accum3 += AOR_multiply(AOR_multiply(d, d, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3119, _mut3120, _mut3121, _mut3122), d, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3123, _mut3124, _mut3125, _mut3126);
            }
            accum3 /= AOR_multiply(variance, FastMath.sqrt(variance), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3136, _mut3137, _mut3138, _mut3139);
            // Get N
            double n0 = length;
            // Calculate skewness
            skew = AOR_multiply((AOR_divide(n0, (AOR_multiply((AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3140, _mut3141, _mut3142, _mut3143)), (AOR_minus(n0, 2, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3144, _mut3145, _mut3146, _mut3147)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3148, _mut3149, _mut3150, _mut3151)), "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3152, _mut3153, _mut3154, _mut3155)), accum3, "org.apache.commons.math3.stat.descriptive.moment.Skewness.evaluate_161", _mut3156, _mut3157, _mut3158, _mut3159);
        }
        return skew;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Skewness copy() {
        Skewness result = new Skewness();
        // No try-catch or advertised exception because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Skewness to copy
     * @param dest Skewness to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Skewness source, Skewness dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.moment = new ThirdMoment(source.moment.copy());
        dest.incMoment = source.incMoment;
    }
}
