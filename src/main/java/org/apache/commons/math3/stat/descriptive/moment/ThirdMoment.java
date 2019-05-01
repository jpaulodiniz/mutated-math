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
 * Computes a statistic related to the Third Central Moment.  Specifically,
 * what is computed is the sum of cubed deviations from the sample mean.
 * <p>
 * The following recursive updating formula is used:</p>
 * <p>
 * Let <ul>
 * <li> dev = (current obs - previous mean) </li>
 * <li> m2 = previous value of {@link SecondMoment} </li>
 * <li> n = number of observations (including current obs) </li>
 * </ul>
 * Then</p>
 * <p>
 * new value = old value - 3 * (dev/n) * m2 + (n-1) * (n -2) * (dev^3/n^2)</p>
 * <p>
 * Returns <code>Double.NaN</code> if no data values have been added and
 * returns <code>0</code> if there is just one value in the data set.
 * Note that Double.NaN may also be returned if the input includes NaN
 * and / or infinite values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
class ThirdMoment extends SecondMoment implements Serializable {

    @Conditional
    public static boolean _mut2541 = false, _mut2542 = false, _mut2543 = false, _mut2544 = false, _mut2545 = false, _mut2546 = false, _mut2547 = false, _mut2548 = false, _mut2549 = false, _mut2550 = false, _mut2551 = false, _mut2552 = false, _mut2553 = false, _mut2554 = false, _mut2555 = false, _mut2556 = false, _mut2557 = false, _mut2558 = false, _mut2559 = false, _mut2560 = false, _mut2561 = false, _mut2562 = false, _mut2563 = false, _mut2564 = false, _mut2565 = false, _mut2566 = false, _mut2567 = false, _mut2568 = false, _mut2569 = false, _mut2570 = false, _mut2571 = false, _mut2572 = false, _mut2573 = false, _mut2574 = false, _mut2575 = false, _mut2576 = false, _mut2577 = false, _mut2578 = false, _mut2579 = false, _mut2580 = false, _mut2581 = false, _mut2582 = false, _mut2583 = false, _mut2584 = false, _mut2585 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -7818711964045118679L;

    /**
     * third moment of values that have been added
     */
    protected double m3;

    /**
     * Square of deviation of most recently added value from previous first
     * moment, normalized by previous sample size.  Retained to prevent
     * repeated computation in higher order moments.  nDevSq = nDev * nDev.
     */
    protected double nDevSq;

    /**
     * Create a FourthMoment instance
     */
    ThirdMoment() {
        super();
        m3 = Double.NaN;
        nDevSq = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code ThirdMoment} identical
     * to the {@code original}
     *
     * @param original the {@code ThirdMoment} instance to copy
     * @throws NullArgumentException if orginal is null
     */
    ThirdMoment(ThirdMoment original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89");
        if (ROR_less(n, 1, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2541, _mut2542, _mut2543, _mut2544, _mut2545)) {
            m3 = m2 = m1 = 0.0;
        }
        double prevM2 = m2;
        super.increment(d);
        nDevSq = AOR_multiply(nDev, nDev, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2546, _mut2547, _mut2548, _mut2549);
        double n0 = n;
        m3 = AOR_plus(AOR_minus(m3, AOR_multiply(AOR_multiply(3.0, nDev, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2550, _mut2551, _mut2552, _mut2553), prevM2, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2554, _mut2555, _mut2556, _mut2557), "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2558, _mut2559, _mut2560, _mut2561), AOR_multiply(AOR_multiply(AOR_multiply((AOR_minus(n0, 1, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2562, _mut2563, _mut2564, _mut2565)), (AOR_minus(n0, 2, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2566, _mut2567, _mut2568, _mut2569)), "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2570, _mut2571, _mut2572, _mut2573), nDevSq, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2574, _mut2575, _mut2576, _mut2577), dev, "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2578, _mut2579, _mut2580, _mut2581), "org.apache.commons.math3.stat.descriptive.moment.ThirdMoment.increment_89", _mut2582, _mut2583, _mut2584, _mut2585);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return m3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        m3 = Double.NaN;
        nDevSq = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThirdMoment copy() {
        ThirdMoment result = new ThirdMoment();
        // No try-catch or advertised exception because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source ThirdMoment to copy
     * @param dest ThirdMoment to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(ThirdMoment source, ThirdMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        SecondMoment.copy(source, dest);
        dest.m3 = source.m3;
        dest.nDevSq = source.nDevSq;
    }
}
