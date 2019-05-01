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

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class representing the Jacobian of a multivariate vector function.
 * <p>
 * The rows iterate on the model functions while the columns iterate on the parameters; thus,
 * the numbers of rows is equal to the dimension of the underlying function vector
 * value and the number of columns is equal to the number of free parameters of
 * the underlying function.
 * </p>
 * @since 3.1
 */
public class JacobianFunction implements MultivariateMatrixFunction {

    @Conditional
    public static boolean _mut96997 = false, _mut96998 = false, _mut96999 = false, _mut97000 = false, _mut97001 = false, _mut97002 = false, _mut97003 = false, _mut97004 = false, _mut97005 = false, _mut97006 = false, _mut97007 = false, _mut97008 = false, _mut97009 = false, _mut97010 = false, _mut97011 = false;

    /**
     * Underlying vector-valued function.
     */
    private final MultivariateDifferentiableVectorFunction f;

    /**
     * Simple constructor.
     * @param f underlying vector-valued function
     */
    public JacobianFunction(final MultivariateDifferentiableVectorFunction f) {
        this.f = f;
    }

    /**
     * {@inheritDoc}
     */
    public double[][] value(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43");
        // set up parameters
        final DerivativeStructure[] dsX = new DerivativeStructure[point.length];
        for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43", _mut96997, _mut96998, _mut96999, _mut97000, _mut97001); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43");
            dsX[i] = new DerivativeStructure(point.length, 1, i, point[i]);
        }
        // compute the derivatives
        final DerivativeStructure[] dsY = f.value(dsX);
        // extract the Jacobian
        final double[][] y = new double[dsY.length][point.length];
        final int[] orders = new int[point.length];
        for (int i = 0; ROR_less(i, dsY.length, "org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43", _mut97007, _mut97008, _mut97009, _mut97010, _mut97011); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43");
            for (int j = 0; ROR_less(j, point.length, "org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43", _mut97002, _mut97003, _mut97004, _mut97005, _mut97006); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.JacobianFunction.value_43");
                orders[j] = 1;
                y[i][j] = dsY[i].getPartialDerivative(orders);
                orders[j] = 0;
            }
        }
        return y;
    }
}
