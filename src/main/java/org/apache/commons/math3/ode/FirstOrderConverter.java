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
package org.apache.commons.math3.ode;

import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FirstOrderConverter implements FirstOrderDifferentialEquations {

    @Conditional
    public static boolean _mut22413 = false, _mut22414 = false, _mut22415 = false, _mut22416 = false;

    /**
     * Underlying second order equations set.
     */
    private final SecondOrderDifferentialEquations equations;

    /**
     * second order problem dimension.
     */
    private final int dimension;

    /**
     * state vector.
     */
    private final double[] z;

    /**
     * first time derivative of the state vector.
     */
    private final double[] zDot;

    /**
     * second time derivative of the state vector.
     */
    private final double[] zDDot;

    /**
     * Simple constructor.
     * Build a converter around a second order equations set.
     * @param equations second order equations set to convert
     */
    public FirstOrderConverter(final SecondOrderDifferentialEquations equations) {
        this.equations = equations;
        dimension = equations.getDimension();
        z = new double[dimension];
        zDot = new double[dimension];
        zDDot = new double[dimension];
    }

    /**
     * Get the dimension of the problem.
     * <p>The dimension of the first order problem is twice the
     * dimension of the underlying second order problem.</p>
     * @return dimension of the problem
     */
    public int getDimension() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FirstOrderConverter.getDimension_90");
        return AOR_multiply(2, dimension, "org.apache.commons.math3.ode.FirstOrderConverter.getDimension_90", _mut22413, _mut22414, _mut22415, _mut22416);
    }

    /**
     * Get the current time derivative of the state vector.
     * @param t current value of the independent <I>time</I> variable
     * @param y array containing the current value of the state vector
     * @param yDot placeholder array where to put the time derivative of the state vector
     */
    public void computeDerivatives(final double t, final double[] y, final double[] yDot) {
        // split the state vector in two
        System.arraycopy(y, 0, z, 0, dimension);
        System.arraycopy(y, dimension, zDot, 0, dimension);
        // apply the underlying equations set
        equations.computeSecondDerivatives(t, z, zDot, zDDot);
        // build the result state derivative
        System.arraycopy(zDot, 0, yDot, 0, dimension);
        System.arraycopy(zDDot, 0, yDot, dimension, dimension);
    }
}
