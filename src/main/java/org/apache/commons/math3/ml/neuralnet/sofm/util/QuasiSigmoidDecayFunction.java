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
import org.apache.commons.math3.analysis.function.Logistic;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Decay function whose shape is similar to a sigmoid.
 * <br/>
 * Class is immutable.
 *
 * @since 3.3
 */
public class QuasiSigmoidDecayFunction {

    @Conditional
    public static boolean _mut103596 = false, _mut103597 = false, _mut103598 = false, _mut103599 = false, _mut103600 = false, _mut103601 = false, _mut103602 = false, _mut103603 = false, _mut103604 = false, _mut103605 = false, _mut103606 = false, _mut103607 = false, _mut103608 = false, _mut103609 = false, _mut103610 = false, _mut103611 = false, _mut103612 = false, _mut103613 = false, _mut103614 = false, _mut103615 = false, _mut103616 = false, _mut103617 = false, _mut103618 = false, _mut103619 = false, _mut103620 = false, _mut103621 = false, _mut103622 = false, _mut103623 = false, _mut103624 = false, _mut103625 = false, _mut103626 = false;

    /**
     * Sigmoid.
     */
    private final Logistic sigmoid;

    /**
     * See {@link #value(long)}.
     */
    private final double scale;

    /**
     * Creates an instance.
     * The function {@code f} will have the following properties:
     * <ul>
     *  <li>{@code f(0) = initValue}</li>
     *  <li>{@code numCall} is the inflexion point</li>
     *  <li>{@code slope = f'(numCall)}</li>
     * </ul>
     *
     * @param initValue Initial value, i.e. {@link #value(long) value(0)}.
     * @param slope Value of the function derivative at {@code numCall}.
     * @param numCall Inflexion point.
     * @throws NotStrictlyPositiveException if {@code initValue <= 0}.
     * @throws NumberIsTooLargeException if {@code slope >= 0}.
     * @throws NotStrictlyPositiveException if {@code numCall <= 0}.
     */
    public QuasiSigmoidDecayFunction(double initValue, double slope, long numCall) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53");
        if (ROR_less_equals(initValue, 0, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103596, _mut103597, _mut103598, _mut103599, _mut103600)) {
            throw new NotStrictlyPositiveException(initValue);
        }
        if (ROR_greater_equals(slope, 0, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103601, _mut103602, _mut103603, _mut103604, _mut103605)) {
            throw new NumberIsTooLargeException(slope, 0, false);
        }
        if (ROR_less_equals(numCall, 1, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103606, _mut103607, _mut103608, _mut103609, _mut103610)) {
            throw new NotStrictlyPositiveException(numCall);
        }
        final double k = initValue;
        final double m = numCall;
        final double b = AOR_divide(AOR_multiply(4, slope, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103611, _mut103612, _mut103613, _mut103614), initValue, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103615, _mut103616, _mut103617, _mut103618);
        final double q = 1;
        final double a = 0;
        final double n = 1;
        sigmoid = new Logistic(k, m, b, q, a, n);
        final double y0 = sigmoid.value(0);
        scale = AOR_divide(k, y0, "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.QuasiSigmoidDecayFunction_53", _mut103619, _mut103620, _mut103621, _mut103622);
    }

    /**
     * Computes the value of the learning factor.
     *
     * @param numCall Current step of the training task.
     * @return the value of the function at {@code numCall}.
     */
    public double value(long numCall) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.value_84");
        return AOR_multiply(scale, sigmoid.value(numCall), "org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction.value_84", _mut103623, _mut103624, _mut103625, _mut103626);
    }
}
