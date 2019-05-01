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
 * Computes a statistic related to the Second Central Moment.  Specifically,
 * what is computed is the sum of squared deviations from the sample mean.
 * <p>
 * The following recursive updating formula is used:</p>
 * <p>
 * Let <ul>
 * <li> dev = (current obs - previous mean) </li>
 * <li> n = number of observations (including current obs) </li>
 * </ul>
 * Then</p>
 * <p>
 * new value = old value + dev^2 * (n -1) / n.</p>
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
public class SecondMoment extends FirstMoment implements Serializable {

    @Conditional
    public static boolean _mut2757 = false, _mut2758 = false, _mut2759 = false, _mut2760 = false, _mut2761 = false, _mut2762 = false, _mut2763 = false, _mut2764 = false, _mut2765 = false, _mut2766 = false, _mut2767 = false, _mut2768 = false, _mut2769 = false, _mut2770 = false, _mut2771 = false, _mut2772 = false, _mut2773 = false;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 3942403127395076445L;

    /**
     * second moment of values that have been added
     */
    protected double m2;

    /**
     * Create a SecondMoment instance
     */
    public SecondMoment() {
        super();
        m2 = Double.NaN;
    }

    /**
     * Copy constructor, creates a new {@code SecondMoment} identical
     * to the {@code original}
     *
     * @param original the {@code SecondMoment} instance to copy
     * @throws NullArgumentException if original is null
     */
    public SecondMoment(SecondMoment original) throws NullArgumentException {
        super(original);
        this.m2 = original.m2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increment(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.descriptive.moment.SecondMoment.increment_81");
        if (ROR_less(n, 1, "org.apache.commons.math3.stat.descriptive.moment.SecondMoment.increment_81", _mut2757, _mut2758, _mut2759, _mut2760, _mut2761)) {
            m1 = m2 = 0.0;
        }
        super.increment(d);
        m2 += AOR_multiply(AOR_multiply((AOR_minus((double) n, 1, "org.apache.commons.math3.stat.descriptive.moment.SecondMoment.increment_81", _mut2762, _mut2763, _mut2764, _mut2765)), dev, "org.apache.commons.math3.stat.descriptive.moment.SecondMoment.increment_81", _mut2766, _mut2767, _mut2768, _mut2769), nDev, "org.apache.commons.math3.stat.descriptive.moment.SecondMoment.increment_81", _mut2770, _mut2771, _mut2772, _mut2773);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        m2 = Double.NaN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getResult() {
        return m2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecondMoment copy() {
        SecondMoment result = new SecondMoment();
        // no try-catch or advertised NAE because args are guaranteed non-null
        copy(this, result);
        return result;
    }

    /**
     * Copies source to dest.
     * <p>Neither source nor dest can be null.</p>
     *
     * @param source SecondMoment to copy
     * @param dest SecondMoment to copy to
     * @throws NullArgumentException if either source or dest is null
     */
    public static void copy(SecondMoment source, SecondMoment dest) throws NullArgumentException {
        MathUtils.checkNotNull(source);
        MathUtils.checkNotNull(dest);
        FirstMoment.copy(source, dest);
        dest.m2 = source.m2;
    }
}
