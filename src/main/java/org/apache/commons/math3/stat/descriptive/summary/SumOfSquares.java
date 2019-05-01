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
 * Returns the sum of the squares of the available values.
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
public class SumOfSquares extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut4521 = false, _mut4522 = false, _mut4523 = false, _mut4524 = false, _mut4525 = false, _mut4526 = false, _mut4527 = false, _mut4528 = false, _mut4529 = false, _mut4530 = false, _mut4531 = false, _mut4532 = false, _mut4533 = false, _mut4534 = false, _mut4535 = false, _mut4536 = false, _mut4537 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 1460986908574398008L;

    /**
     */
    private long n;

    /**
     * The currently running sumSq
     */
    private double value;

    /**
     * Create a SumOfSquares instance
     */
    public SumOfSquares() {
        n = 0;
        value = 0;
    }

    /**
     * Copy constructor, creates a new {@code SumOfSquares} identical
     * to the {@code original}
     *
     * @param original the {@code SumOfSquares} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SumOfSquares(SumOfSquares original) throws NullArgumentException {
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.increment_74");
        value += AOR_multiply(d, d, "org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.increment_74", _mut4521, _mut4522, _mut4523, _mut4524);
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
     * Returns the sum of the squares of the entries in the specified portion of
     * the input array, or <code>Double.NaN</code> if the designated subarray
     * is empty.
     * <p>
     * Throws <code>MathIllegalArgumentException</code> if the array is null.</p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return the sum of the squares of the values or 0 if length = 0
     * @throws MathIllegalArgumentException if the array is null or the array index
     *  parameters are not valid
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.evaluate_118");
        double sumSq = Double.NaN;
        if (test(values, begin, length, true)) {
            sumSq = 0.0;
            for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.evaluate_118", _mut4529, _mut4530, _mut4531, _mut4532), "org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.evaluate_118", _mut4533, _mut4534, _mut4535, _mut4536, _mut4537); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.evaluate_118");
                sumSq += AOR_multiply(values[i], values[i], "org.apache.commons.math3.stat.descriptive.summary.SumOfSquares.evaluate_118", _mut4525, _mut4526, _mut4527, _mut4528);
            }
        }
        return sumSq;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SumOfSquares copy() {
        SumOfSquares result = new SumOfSquares();
        // no try-catch or advertised exception here because args are valid
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source SumOfSquares to copy
     * @param dest SumOfSquares to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SumOfSquares source, SumOfSquares dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.value = source.value;
    }
}
