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
package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Step_function">
 *  Step function</a>.
 *
 * @since 3.0
 */
public class StepFunction implements UnivariateFunction {

    @Conditional
    public static boolean _mut91374 = false, _mut91375 = false, _mut91376 = false, _mut91377 = false, _mut91378 = false, _mut91379 = false, _mut91380 = false, _mut91381 = false, _mut91382 = false, _mut91383 = false, _mut91384 = false, _mut91385 = false, _mut91386 = false, _mut91387 = false, _mut91388 = false, _mut91389 = false, _mut91390 = false, _mut91391 = false, _mut91392 = false, _mut91393 = false, _mut91394 = false, _mut91395 = false, _mut91396 = false, _mut91397 = false, _mut91398 = false, _mut91399 = false, _mut91400 = false, _mut91401 = false, _mut91402 = false, _mut91403 = false, _mut91404 = false;

    /**
     * Abscissae.
     */
    private final double[] abscissa;

    /**
     * Ordinates.
     */
    private final double[] ordinate;

    /**
     * Builds a step function from a list of arguments and the corresponding
     * values. Specifically, returns the function h(x) defined by <pre><code>
     * h(x) = y[0] for all x &lt; x[1]
     *        y[1] for x[1] &le; x &lt; x[2]
     *        ...
     *        y[y.length - 1] for x &ge; x[x.length - 1]
     * </code></pre>
     * The value of {@code x[0]} is ignored, but it must be strictly less than
     * {@code x[1]}.
     *
     * @param x Domain values where the function changes value.
     * @param y Values of the function.
     * @throws NonMonotonicSequenceException
     * if the {@code x} array is not sorted in strictly increasing order.
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     * @throws DimensionMismatchException if {@code x} and {@code y} do not
     * have the same length.
     */
    public StepFunction(double[] x, double[] y) throws NullArgumentException, NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61");
        if ((_mut91374 ? (x == null && y == null) : (x == null || y == null))) {
            throw new NullArgumentException();
        }
        if ((_mut91385 ? (ROR_equals(x.length, 0, "org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61", _mut91375, _mut91376, _mut91377, _mut91378, _mut91379) && ROR_equals(y.length, 0, "org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61", _mut91380, _mut91381, _mut91382, _mut91383, _mut91384)) : (ROR_equals(x.length, 0, "org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61", _mut91375, _mut91376, _mut91377, _mut91378, _mut91379) || ROR_equals(y.length, 0, "org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61", _mut91380, _mut91381, _mut91382, _mut91383, _mut91384)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(y.length, x.length, "org.apache.commons.math3.analysis.function.StepFunction.StepFunction_61", _mut91386, _mut91387, _mut91388, _mut91389, _mut91390)) {
            throw new DimensionMismatchException(y.length, x.length);
        }
        MathArrays.checkOrder(x);
        abscissa = MathArrays.copyOf(x);
        ordinate = MathArrays.copyOf(y);
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.StepFunction.value_83");
        int index = Arrays.binarySearch(abscissa, x);
        double fx = 0;
        if (ROR_less(index, -1, "org.apache.commons.math3.analysis.function.StepFunction.value_83", _mut91391, _mut91392, _mut91393, _mut91394, _mut91395)) {
            // "x" is between "abscissa[-index-2]" and "abscissa[-index-1]".
            fx = ordinate[AOR_minus(-index, 2, "org.apache.commons.math3.analysis.function.StepFunction.value_83", _mut91401, _mut91402, _mut91403, _mut91404)];
        } else if (ROR_greater_equals(index, 0, "org.apache.commons.math3.analysis.function.StepFunction.value_83", _mut91396, _mut91397, _mut91398, _mut91399, _mut91400)) {
            // "x" is exactly "abscissa[index]".
            fx = ordinate[index];
        } else {
            // (hence the returned value should be "ordinate[0]").
            fx = ordinate[0];
        }
        return fx;
    }
}
