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
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes the first moment (arithmetic mean).  Uses the definitional formula:
 * <p>
 * mean = sum(x_i) / n </p>
 * <p>
 * where <code>n</code> is the number of observations. </p>
 * <p>
 * To limit numeric errors, the value of the statistic is computed using the
 * following recursive updating algorithm: </p>
 * <p>
 * <ol>
 * <li>Initialize <code>m = </code> the first value</li>
 * <li>For each additional value, update using <br>
 *   <code>m = m + (new value - m) / (number of observations)</code></li>
 * </ol></p>
 * <p>
 *  Returns <code>Double.NaN</code> if the dataset is empty. Note that
 *  Double.NaN may also be returned if the input includes NaN and / or infinite
 *  values.</p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an instance of this class concurrently, and at least
 * one of the threads invokes the <code>increment()</code> or
 * <code>clear()</code> method, it must be synchronized externally.</p>
 */
class FirstMoment extends AbstractStorelessUnivariateStatistic implements Serializable {

    @Conditional
    public static boolean _mut3013 = false, _mut3014 = false, _mut3015 = false, _mut3016 = false, _mut3017 = false, _mut3018 = false, _mut3019 = false, _mut3020 = false, _mut3021 = false, _mut3022 = false, _mut3023 = false, _mut3024 = false, _mut3025 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 6112755307178490473L;

    /**
     * Count of values that have been added
     */
    protected long n;

    /**
     * First moment of values that have been added
     */
    protected double m1;

    /**
     * Deviation of most recently added value from previous first moment.
     * Retained to prevent repeated computation in higher order moments.
     */
    protected double dev;

    /**
     * Deviation of most recently added value from previous first moment,
     * normalized by previous sample size.  Retained to prevent repeated
     * computation in higher order moments
     */
    protected double nDev;

    /**
     * Create a FirstMoment instance
     */
    FirstMoment() {
        n = 0;
        m1 = Double.NaN;
        dev = Double.NaN;
        nDev = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code FirstMoment} identical
     * to the {@code original}
     *
     * @param original the {@code FirstMoment} instance to copy
     * @throws NullArgumentException if original is null
     */
    FirstMoment(FirstMoment original) throws NullArgumentException {
        super();
        copy(original, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.FirstMoment.increment_102");
        if (ROR_equals(n, 0, "org.apache.commons.math3.stat.descriptive.moment.FirstMoment.increment_102", _mut3013, _mut3014, _mut3015, _mut3016, _mut3017)) {
            m1 = 0.0;
        }
        n++;
        double n0 = n;
        dev = AOR_minus(d, m1, "org.apache.commons.math3.stat.descriptive.moment.FirstMoment.increment_102", _mut3018, _mut3019, _mut3020, _mut3021);
        nDev = AOR_divide(dev, n0, "org.apache.commons.math3.stat.descriptive.moment.FirstMoment.increment_102", _mut3022, _mut3023, _mut3024, _mut3025);
        m1 += nDev;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        m1 = Double.NaN;
        n = 0;
        dev = Double.NaN;
        nDev = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return m1;
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
    public FirstMoment copy() {
        FirstMoment result = new FirstMoment();
        // No try-catch or advertised exception because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source FirstMoment to copy
     * @param dest FirstMoment to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(FirstMoment source, FirstMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        dest.setData(source.getDataRef());
        dest.n = source.n;
        dest.m1 = source.m1;
        dest.dev = source.dev;
        dest.nDev = source.nDev;
    }
}
