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
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes a statistic related to the Fourth Central Moment.  Specifically,
 * what is computed is the sum of
 * <p>
 * (x_i - xbar) ^ 4, </p>
 * <p>
 * where the x_i are the
 * sample observations and xbar is the sample mean. </p>
 * <p>
 * The following recursive updating formula is used: </p>
 * <p>
 * Let <ul>
 * <li> dev = (current obs - previous mean) </li>
 * <li> m2 = previous value of {@link SecondMoment} </li>
 * <li> m2 = previous value of {@link ThirdMoment} </li>
 * <li> n = number of observations (including current obs) </li>
 * </ul>
 * Then </p>
 * <p>
 * new value = old value - 4 * (dev/n) * m3 + 6 * (dev/n)^2 * m2 + <br>
 * [n^2 - 3 * (n-1)] * dev^4 * (n-1) / n^3 </p>
 * <p>
 * Returns <code>Double.NaN</code> if no data values have been added and
 * returns <code>0</code> if there is just one value in the data set. Note that
 * Double.NaN may also be returned if the input includes NaN and / or infinite
 * values. </p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally. </p>
 */
class FourthMoment extends ThirdMoment implements Serializable {

    @Conditional
    public static boolean _mut3193 = false, _mut3194 = false, _mut3195 = false, _mut3196 = false, _mut3197 = false, _mut3198 = false, _mut3199 = false, _mut3200 = false, _mut3201 = false, _mut3202 = false, _mut3203 = false, _mut3204 = false, _mut3205 = false, _mut3206 = false, _mut3207 = false, _mut3208 = false, _mut3209 = false, _mut3210 = false, _mut3211 = false, _mut3212 = false, _mut3213 = false, _mut3214 = false, _mut3215 = false, _mut3216 = false, _mut3217 = false, _mut3218 = false, _mut3219 = false, _mut3220 = false, _mut3221 = false, _mut3222 = false, _mut3223 = false, _mut3224 = false, _mut3225 = false, _mut3226 = false, _mut3227 = false, _mut3228 = false, _mut3229 = false, _mut3230 = false, _mut3231 = false, _mut3232 = false, _mut3233 = false, _mut3234 = false, _mut3235 = false, _mut3236 = false, _mut3237 = false, _mut3238 = false, _mut3239 = false, _mut3240 = false, _mut3241 = false, _mut3242 = false, _mut3243 = false, _mut3244 = false, _mut3245 = false, _mut3246 = false, _mut3247 = false, _mut3248 = false, _mut3249 = false, _mut3250 = false, _mut3251 = false, _mut3252 = false, _mut3253 = false, _mut3254 = false, _mut3255 = false, _mut3256 = false, _mut3257 = false, _mut3258 = false, _mut3259 = false, _mut3260 = false, _mut3261 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 4763990447117157611L;

    /**
     * fourth moment of values that have been added
     */
    private double m4;

    /**
     * Create a FourthMoment instance
     */
    FourthMoment() {
        super();
        m4 = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code FourthMoment} identical
     * to the {@code original}
     *
     * @param original the {@code FourthMoment} instance to copy
     * @throws NullArgumentException if original is null
     */
    FourthMoment(FourthMoment original) throws NullArgumentException {
        super();
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88");
        if (ROR_less(n, 1, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3193, _mut3194, _mut3195, _mut3196, _mut3197)) {
            m4 = 0.0;
            m3 = 0.0;
            m2 = 0.0;
            m1 = 0.0;
        }
        double prevM3 = m3;
        double prevM2 = m2;
        super.increment(d);
        double n0 = n;
        m4 = AOR_plus(AOR_plus(AOR_minus(m4, AOR_multiply(AOR_multiply(4.0, nDev, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3198, _mut3199, _mut3200, _mut3201), prevM3, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3202, _mut3203, _mut3204, _mut3205), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3206, _mut3207, _mut3208, _mut3209), AOR_multiply(AOR_multiply(6.0, nDevSq, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3210, _mut3211, _mut3212, _mut3213), prevM2, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3214, _mut3215, _mut3216, _mut3217), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3218, _mut3219, _mut3220, _mut3221), AOR_multiply((AOR_minus((AOR_multiply(n0, n0, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3222, _mut3223, _mut3224, _mut3225)), AOR_multiply(3, (AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3226, _mut3227, _mut3228, _mut3229)), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3230, _mut3231, _mut3232, _mut3233), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3234, _mut3235, _mut3236, _mut3237)), (AOR_multiply(AOR_multiply(AOR_multiply(nDevSq, nDevSq, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3238, _mut3239, _mut3240, _mut3241), (AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3242, _mut3243, _mut3244, _mut3245)), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3246, _mut3247, _mut3248, _mut3249), n0, "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3250, _mut3251, _mut3252, _mut3253)), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3254, _mut3255, _mut3256, _mut3257), "org.apache.commons.math3.stat.descriptive.moment.FourthMoment.increment_88", _mut3258, _mut3259, _mut3260, _mut3261);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return m4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        m4 = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FourthMoment copy() {
        FourthMoment result = new FourthMoment();
        // No try-catch or advertised exception because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source FourthMoment to copy
     * @param dest FourthMoment to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(FourthMoment source, FourthMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        ThirdMoment.copy(source, dest);
        dest.m4 = source.m4;
    }
}
