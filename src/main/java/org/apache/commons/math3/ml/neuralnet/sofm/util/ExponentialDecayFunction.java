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
package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Exponential decay function: <code>a e<sup>-x / b</sup></code>,
 * where {@code x} is the (integer) independent variable.
 * <br/>
 * Class is immutable.
 *
 * @since 3.3
 */
public class ExponentialDecayFunction {

    @Conditional
    public static boolean _mut103560 = false, _mut103561 = false, _mut103562 = false, _mut103563 = false, _mut103564 = false, _mut103565 = false, _mut103566 = false, _mut103567 = false, _mut103568 = false, _mut103569 = false, _mut103570 = false, _mut103571 = false, _mut103572 = false, _mut103573 = false, _mut103574 = false, _mut103575 = false, _mut103576 = false, _mut103577 = false, _mut103578 = false, _mut103579 = false, _mut103580 = false, _mut103581 = false, _mut103582 = false, _mut103583 = false, _mut103584 = false, _mut103585 = false, _mut103586 = false, _mut103587 = false, _mut103588 = false, _mut103589 = false, _mut103590 = false, _mut103591 = false, _mut103592 = false, _mut103593 = false, _mut103594 = false, _mut103595 = false;

    /**
     * Factor {@code a}.
     */
    private final double a;

    /**
     * Factor {@code 1 / b}.
     */
    private final double oneOverB;

    /**
     * Creates an instance. It will be such that
     * <ul>
     *  <li>{@code a = initValue}</li>
     *  <li>{@code b = -numCall / ln(valueAtNumCall / initValue)}</li>
     * </ul>
     *
     * @param initValue Initial value, i.e. {@link #value(long) value(0)}.
     * @param valueAtNumCall Value of the function at {@code numCall}.
     * @param numCall Argument for which the function returns
     * {@code valueAtNumCall}.
     * @throws NotStrictlyPositiveException if {@code initValue <= 0}.
     * @throws NotStrictlyPositiveException if {@code valueAtNumCall <= 0}.
     * @throws NumberIsTooLargeException if {@code valueAtNumCall >= initValue}.
     * @throws NotStrictlyPositiveException if {@code numCall <= 0}.
     */
    public ExponentialDecayFunction(double initValue, double valueAtNumCall, long numCall) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54");
        if (ROR_less_equals(initValue, 0, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103560, _mut103561, _mut103562, _mut103563, _mut103564)) {
            throw new NotStrictlyPositiveException(initValue);
        }
        if (ROR_less_equals(valueAtNumCall, 0, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103565, _mut103566, _mut103567, _mut103568, _mut103569)) {
            throw new NotStrictlyPositiveException(valueAtNumCall);
        }
        if (ROR_greater_equals(valueAtNumCall, initValue, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103570, _mut103571, _mut103572, _mut103573, _mut103574)) {
            throw new NumberIsTooLargeException(valueAtNumCall, initValue, false);
        }
        if (ROR_less_equals(numCall, 0, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103575, _mut103576, _mut103577, _mut103578, _mut103579)) {
            throw new NotStrictlyPositiveException(numCall);
        }
        a = initValue;
        oneOverB = AOR_divide(-FastMath.log(AOR_divide(valueAtNumCall, initValue, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103580, _mut103581, _mut103582, _mut103583)), numCall, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.ExponentialDecayFunction_54", _mut103584, _mut103585, _mut103586, _mut103587);
    }

    /**
     * Computes <code>a e<sup>-numCall / b</sup></code>.
     *
     * @param numCall Current step of the training task.
     * @return the value of the function at {@code numCall}.
     */
    public double value(long numCall) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.value_80");
        return AOR_multiply(a, FastMath.exp(AOR_multiply(-numCall, oneOverB, "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.value_80", _mut103588, _mut103589, _mut103590, _mut103591)), "org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction.value_80", _mut103592, _mut103593, _mut103594, _mut103595);
    }
}
