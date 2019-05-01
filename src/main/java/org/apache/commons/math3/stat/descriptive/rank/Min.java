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
package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Returns the minimum of the available values.
 * <p>
 * <ul>
 * <li>The result is <code>NaN</code> iff all values are <code>NaN</code>
 * (i.e. <code>NaN</code> values have no impact on the value of the statistic).</li>
 * <li>If any of the values equals <code>Double.NEGATIVE_INFINITY</code>,
 * the result is <code>Double.NEGATIVE_INFINITY.</code></li>
 * </ul></p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Min extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut3798 = false, _mut3799 = false, _mut3800 = false, _mut3801 = false, _mut3802 = false, _mut3803 = false, _mut3804 = false, _mut3805 = false, _mut3806 = false, _mut3807 = false, _mut3808 = false, _mut3809 = false, _mut3810 = false, _mut3811 = false, _mut3812 = false, _mut3813 = false, _mut3814 = false, _mut3815 = false, _mut3816 = false, _mut3817 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -2941995784909003131L;

    /**
     * Number of values that have been added
     */
    private long n;

    /**
     * Current value of the statistic
     */
    private double value;

    /**
     * Create a Min instance
     */
    public Min() {
        n = 0;
        value = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code Min} identical
     * to the {@code original}
     *
     * @param original the {@code Min} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Min(Min original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Min.increment_75");
        if ((_mut3803 ? (ROR_less(d, value, "org.apache.commons.math3.stat.descriptive.rank.Min.increment_75", _mut3798, _mut3799, _mut3800, _mut3801, _mut3802) && Double.isNaN(value)) : (ROR_less(d, value, "org.apache.commons.math3.stat.descriptive.rank.Min.increment_75", _mut3798, _mut3799, _mut3800, _mut3801, _mut3802) || Double.isNaN(value)))) {
            value = d;
        }
        n++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        value = Double.NaN;
        n = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public long getN() {
        return n;
    }

    /**
     * Returns the minimum of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null or
     * the array index parameters are not valid.</p>
     * <p>
     * <ul>
     * <li>The result is <code>NaN</code> iff all values are <code>NaN</code>
     * (i.e. <code>NaN</code> values have no impact on the value of the statistic).</li>
     * <li>If any of the values equals <code>Double.NEGATIVE_INFINITY</code>,
     * the result is <code>Double.NEGATIVE_INFINITY.</code></li>
     * </ul> </p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the minimum of the values or Double.NaN if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Min.evaluate_129");
        double min = Double.NaN;
        if (test(values, begin, length)) {
            min = values[begin];
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.rank.Min.evaluate_129", _mut3809, _mut3810, _mut3811, _mut3812), "org.apache.commons.math3.stat.descriptive.rank.Min.evaluate_129", _mut3813, _mut3814, _mut3815, _mut3816, _mut3817); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.rank.Min.evaluate_129");
                if (!Double.isNaN(values[i])) {
                    min = (ROR_less(min, values[i], "org.apache.commons.math3.stat.descriptive.rank.Min.evaluate_129", _mut3804, _mut3805, _mut3806, _mut3807, _mut3808)) ? min : values[i];
                }
            }
        }
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Min copy() {
        Min result = new Min();
        // No try-catch or advertised exception - args are non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Min to copy
     * @param dest Min to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Min source, Min dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}
