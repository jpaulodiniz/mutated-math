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

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Abstract implementation of the {@link StorelessUnivariateStatistic} interface.
 * <p>
 * Provides default <code>evaluate()</code> and <code>incrementAll(double[])</code>
 * implementations.</p>
 * <p>
 * <strong>Note that these implementations are not synchronized.</strong></p>
 */
public abstract class AbstractStorelessUnivariateStatistic extends AbstractUnivariateStatistic implements StorelessUnivariateStatistic {

    @Conditional
    public static boolean _mut4450 = false, _mut4451 = false, _mut4452 = false, _mut4453 = false, _mut4454 = false, _mut4455 = false, _mut4456 = false, _mut4457 = false, _mut4458 = false, _mut4459 = false, _mut4460 = false, _mut4461 = false, _mut4462 = false, _mut4463 = false, _mut4464 = false, _mut4465 = false, _mut4466 = false, _mut4467 = false, _mut4468 = false, _mut4469 = false, _mut4470 = false, _mut4471 = false;

    /**
     * This default implementation calls {@link #clear}, then invokes
     * {@link #increment} in a loop over the the input array, and then uses
     * {@link #getResult} to compute the return value.
     * <p>
     * Note that this implementation changes the internal state of the
     * statistic.  Its side effects are the same as invoking {@link #clear} and
     * then {@link #incrementAll(double[])}.</p>
     * <p>
     * Implementations may override this method with a more efficient and
     * possibly more accurate implementation that works directly with the
     * input array.</p>
     * <p>
     * If the array is null, a MathIllegalArgumentException is thrown.</p>
     * @param values input array
     * @return the value of the statistic applied to the input array
     * @throws MathIllegalArgumentException if values is null
     * @see org.apache.commons.math3.stat.descriptive.UnivariateStatistic#evaluate(double[])
     */
    @Override
    public double evaluate(final double[] values) throws MathIllegalArgumentException {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        return evaluate(values, 0, values.length);
    }

    /**
     * This default implementation calls {@link #clear}, then invokes
     * {@link #increment} in a loop over the specified portion of the input
     * array, and then uses {@link #getResult} to compute the return value.
     * <p>
     * Note that this implementation changes the internal state of the
     * statistic.  Its side effects are the same as invoking {@link #clear} and
     * then {@link #incrementAll(double[], int, int)}.</p>
     * <p>
     * Implementations may override this method with a more efficient and
     * possibly more accurate implementation that works directly with the
     * input array.</p>
     * <p>
     * If the array is null or the index parameters are not valid, an
     * MathIllegalArgumentException is thrown.</p>
     * @param values the input array
     * @param begin the index of the first element to include
     * @param length the number of elements to include
     * @return the value of the statistic applied to the included array entries
     * @throws MathIllegalArgumentException if the array is null or the indices are not valid
     * @see org.apache.commons.math3.stat.descriptive.UnivariateStatistic#evaluate(double[], int, int)
     */
    @Override
    public double evaluate(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        if (test(values, begin, length)) {
            clear();
            incrementAll(values, begin, length);
        }
        return getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract StorelessUnivariateStatistic copy();

    /**
     * {@inheritDoc}
     */
    public abstract void clear();

    /**
     * {@inheritDoc}
     */
    public abstract double getResult();

    /**
     * {@inheritDoc}
     */
    public abstract void increment(final double d);

    /**
     * This default implementation just calls {@link #increment} in a loop over
     * the input array.
     * <p>
     * Throws IllegalArgumentException if the input values array is null.</p>
     *
     * @param values values to add
     * @throws MathIllegalArgumentException if values is null
     * @see org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic#incrementAll(double[])
     */
    public void incrementAll(double[] values) throws MathIllegalArgumentException {
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        incrementAll(values, 0, values.length);
    }

    /**
     * This default implementation just calls {@link #increment} in a loop over
     * the specified portion of the input array.
     * <p>
     * Throws IllegalArgumentException if the input values array is null.</p>
     *
     * @param values  array holding values to add
     * @param begin   index of the first array element to add
     * @param length  number of array elements to add
     * @throws MathIllegalArgumentException if values is null
     * @see org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic#incrementAll(double[], int, int)
     */
    public void incrementAll(double[] values, int begin, int length) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.incrementAll_148");
        if (test(values, begin, length)) {
            int k = AOR_plus(begin, length, "org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.incrementAll_148", _mut4450, _mut4451, _mut4452, _mut4453);
            for (int i = begin; ROR_less(i, k, "org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.incrementAll_148", _mut4454, _mut4455, _mut4456, _mut4457, _mut4458); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.incrementAll_148");
                increment(values[i]);
            }
        }
    }

    /**
     * Returns true iff <code>object</code> is an
     * <code>AbstractStorelessUnivariateStatistic</code> returning the same
     * values as this for <code>getResult()</code> and <code>getN()</code>
     * @param object object to test equality against.
     * @return true if object returns the same value as this
     */
    @Override
    public boolean equals(Object object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.equals_164");
        if (object == this) {
            return true;
        }
        if (object instanceof AbstractStorelessUnivariateStatistic == false) {
            return false;
        }
        AbstractStorelessUnivariateStatistic stat = (AbstractStorelessUnivariateStatistic) object;
        return (_mut4459 ? (Precision.equalsIncludingNaN(stat.getResult(), this.getResult()) || Precision.equalsIncludingNaN(stat.getN(), this.getN())) : (Precision.equalsIncludingNaN(stat.getResult(), this.getResult()) && Precision.equalsIncludingNaN(stat.getN(), this.getN())));
    }

    /**
     * Returns hash code based on getResult() and getN()
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.hashCode_182");
        return AOR_plus(AOR_multiply(31, (AOR_plus(31, MathUtils.hash(getResult()), "org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.hashCode_182", _mut4460, _mut4461, _mut4462, _mut4463)), "org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.hashCode_182", _mut4464, _mut4465, _mut4466, _mut4467), MathUtils.hash(getN()), "org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic.hashCode_182", _mut4468, _mut4469, _mut4470, _mut4471);
    }
}
