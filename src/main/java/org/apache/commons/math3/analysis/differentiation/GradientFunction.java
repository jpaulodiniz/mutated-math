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
package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class representing the gradient of a multivariate function.
 * <p>
 * The vectorial components of the function represent the derivatives
 * with respect to each function parameters.
 * </p>
 * @since 3.1
 */
public class GradientFunction implements MultivariateVectorFunction {

    @Conditional
    public static boolean _mut96758 = false, _mut96759 = false, _mut96760 = false, _mut96761 = false, _mut96762 = false, _mut96763 = false, _mut96764 = false, _mut96765 = false, _mut96766 = false, _mut96767 = false;

    /**
     * Underlying real-valued function.
     */
    private final MultivariateDifferentiableFunction f;

    /**
     * Simple constructor.
     * @param f underlying real-valued function
     */
    public GradientFunction(final MultivariateDifferentiableFunction f) {
        this.f = f;
    }

    /**
     * {@inheritDoc}
     */
    public double[] value(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.GradientFunction.value_41");
        // set up parameters
        final DerivativeStructure[] dsX = new DerivativeStructure[point.length];
        for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.analysis.differentiation.GradientFunction.value_41", _mut96758, _mut96759, _mut96760, _mut96761, _mut96762); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.GradientFunction.value_41");
            dsX[i] = new DerivativeStructure(point.length, 1, i, point[i]);
        }
        // compute the derivatives
        final DerivativeStructure dsY = f.value(dsX);
        // extract the gradient
        final double[] y = new double[point.length];
        final int[] orders = new int[point.length];
        for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.analysis.differentiation.GradientFunction.value_41", _mut96763, _mut96764, _mut96765, _mut96766, _mut96767); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.GradientFunction.value_41");
            orders[i] = 1;
            y[i] = dsY.getPartialDerivative(orders);
            orders[i] = 0;
        }
        return y;
    }
}
