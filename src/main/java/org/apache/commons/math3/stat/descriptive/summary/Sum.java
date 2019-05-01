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
package org.apache.commons.math3.stat.descriptive.summary;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Returns the sum of the available values.
 * <p>
 * If there are no values in the dataset, then 0 is returned.
 * If any of the values are
 * <code>NaN</code>, then <code>NaN</code> is returned.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
public class Sum extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut4565 = false, _mut4566 = false, _mut4567 = false, _mut4568 = false, _mut4569 = false, _mut4570 = false, _mut4571 = false, _mut4572 = false, _mut4573 = false, _mut4574 = false, _mut4575 = false, _mut4576 = false, _mut4577 = false, _mut4578 = false, _mut4579 = false, _mut4580 = false, _mut4581 = false, _mut4582 = false, _mut4583 = false, _mut4584 = false, _mut4585 = false, _mut4586 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -8231831954703408316L;

    /**
     */
    private long n;

    /**
     * The currently running sum.
     */
    private double value;

    /**
     * Create a Sum instance
     */
    public Sum() {
        n = 0;
        value = 0;
    }

    /**
     * Copy constructor, creates a new {@code Sum} identical
     * to the {@code original}
     *
     * @param original the {@code Sum} instance to copy
     * @throws NullArgumentException if original is null
     */
    public Sum(Sum original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        value += d;
        n++;
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
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        value = 0;
        n = 0;
    }

    /**
     * The sum of the entries in the specified portion of
     * the input array, or 0 if the designated subarray
     * is empty.
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the sum of the values or 0 if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_119");
        double sum = Double.NaN;
        if (test(values, begin, length, true)) {
            sum = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_119", _mut4565, _mut4566, _mut4567, _mut4568), "org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_119", _mut4569, _mut4570, _mut4571, _mut4572, _mut4573); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_119");
                sum += values[i];
            }
        }
        return sum;
    }

    /**
     * The weighted sum of the entries in the specified portion of
     * the input array, or 0 if the designated subarray
     * is empty.
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
     * Uses the formula, <pre>
     *    weighted sum = &Sigma;(values[i] * weights[i])
     * </pre></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the sum of the values or 0 if length = 0
     * @throws MathIllegalArgumentException if the parameters are not valid
     * @since 2.1
     */
    public double evaluate(final double[] values, final double[] weights, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_159");
        double sum = Double.NaN;
        if (test(values, weights, begin, length, true)) {
            sum = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_159", _mut4578, _mut4579, _mut4580, _mut4581), "org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_159", _mut4582, _mut4583, _mut4584, _mut4585, _mut4586); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_159");
                sum += AOR_multiply(values[i], weights[i], "org.apache.commons.math3.stat.descriptive.summary.Sum.evaluate_159", _mut4574, _mut4575, _mut4576, _mut4577);
            }
        }
        return sum;
    }

    /**
     * The weighted sum of the entries in the the input array.
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
     * Uses the formula, <pre>
     *    weighted sum = &Sigma;(values[i] * weights[i])
     * </pre></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @return the sum of the values or Double.NaN if length = 0
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
    public Sum copy() {
        Sum result = new Sum();
        // No try-catch or advertised exception because args are valid
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source Sum to copy
     * @param dest Sum to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(Sum source, Sum dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}
