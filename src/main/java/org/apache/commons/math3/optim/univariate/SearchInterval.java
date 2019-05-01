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
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Search interval and (optional) start value.
 * <br/>
 * Immutable class.
 *
 * @since 3.1
 */
public class SearchInterval implements OptimizationData {

    @Conditional
    public static boolean _mut58885 = false, _mut58886 = false, _mut58887 = false, _mut58888 = false, _mut58889 = false, _mut58890 = false, _mut58891 = false, _mut58892 = false, _mut58893 = false, _mut58894 = false, _mut58895 = false, _mut58896 = false, _mut58897 = false, _mut58898 = false, _mut58899 = false, _mut58900 = false, _mut58901 = false, _mut58902 = false, _mut58903 = false, _mut58904 = false, _mut58905 = false, _mut58906 = false, _mut58907 = false, _mut58908 = false;

    /**
     * Lower bound.
     */
    private final double lower;

    /**
     * Upper bound.
     */
    private final double upper;

    /**
     * Start value.
     */
    private final double start;

    /**
     * @param lo Lower bound.
     * @param hi Upper bound.
     * @param init Start value.
     * @throws NumberIsTooLargeException if {@code lo >= hi}.
     * @throws OutOfRangeException if {@code init < lo} or {@code init > hi}.
     */
    public SearchInterval(double lo, double hi, double init) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45");
        if (ROR_greater_equals(lo, hi, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45", _mut58885, _mut58886, _mut58887, _mut58888, _mut58889)) {
            throw new NumberIsTooLargeException(lo, hi, false);
        }
        if ((_mut58900 ? (ROR_less(init, lo, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45", _mut58890, _mut58891, _mut58892, _mut58893, _mut58894) && ROR_greater(init, hi, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45", _mut58895, _mut58896, _mut58897, _mut58898, _mut58899)) : (ROR_less(init, lo, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45", _mut58890, _mut58891, _mut58892, _mut58893, _mut58894) || ROR_greater(init, hi, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_45", _mut58895, _mut58896, _mut58897, _mut58898, _mut58899)))) {
            throw new OutOfRangeException(init, lo, hi);
        }
        lower = lo;
        upper = hi;
        start = init;
    }

    /**
     * @param lo Lower bound.
     * @param hi Upper bound.
     * @throws NumberIsTooLargeException if {@code lo >= hi}.
     */
    public SearchInterval(double lo, double hi) {
        this(lo, hi, AOR_multiply(0.5, (AOR_plus(lo, hi, "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_66", _mut58901, _mut58902, _mut58903, _mut58904)), "org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_66", _mut58905, _mut58906, _mut58907, _mut58908));
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.SearchInterval.SearchInterval_66");
    }

    /**
     * Gets the lower bound.
     *
     * @return the lower bound.
     */
    public double getMin() {
        return lower;
    }

    /**
     * Gets the upper bound.
     *
     * @return the upper bound.
     */
    public double getMax() {
        return upper;
    }

    /**
     * Gets the start value.
     *
     * @return the start value.
     */
    public double getStartValue() {
        return start;
    }
}
