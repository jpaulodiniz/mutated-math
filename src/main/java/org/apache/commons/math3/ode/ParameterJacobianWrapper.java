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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Wrapper class to compute Jacobian matrices by finite differences for ODE
 *  which do not compute them by themselves.
 *
 * @since 3.0
 */
class ParameterJacobianWrapper implements ParameterJacobianProvider {

    @Conditional
    public static boolean _mut12160 = false, _mut12161 = false, _mut12162 = false, _mut12163 = false, _mut12164 = false, _mut12165 = false, _mut12166 = false, _mut12167 = false, _mut12168 = false, _mut12169 = false, _mut12170 = false, _mut12171 = false, _mut12172 = false, _mut12173 = false, _mut12174 = false, _mut12175 = false, _mut12176 = false;

    /**
     * Main ODE set.
     */
    private final FirstOrderDifferentialEquations fode;

    /**
     * Raw ODE without Jacobian computation skill to be wrapped into a ParameterJacobianProvider.
     */
    private final ParameterizedODE pode;

    /**
     * Steps for finite difference computation of the Jacobian df/dp w.r.t. parameters.
     */
    private final Map<String, Double> hParam;

    /**
     * Wrap a {@link ParameterizedODE} into a {@link ParameterJacobianProvider}.
     * @param fode main first order differential equations set
     * @param pode secondary problem, without parameter Jacobian computation skill
     * @param paramsAndSteps parameters and steps to compute the Jacobians df/dp
     * @see JacobianMatrices#setParameterStep(String, double)
     */
    ParameterJacobianWrapper(final FirstOrderDifferentialEquations fode, final ParameterizedODE pode, final ParameterConfiguration[] paramsAndSteps) {
        this.fode = fode;
        this.pode = pode;
        this.hParam = new HashMap<String, Double>();
        // set up parameters for jacobian computation
        for (final ParameterConfiguration param : paramsAndSteps) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ParameterJacobianWrapper.ParameterJacobianWrapper_49");
            final String name = param.getParameterName();
            if (pode.isSupported(name)) {
                hParam.put(name, param.getHP());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<String> getParametersNames() {
        return pode.getParametersNames();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupported(String name) {
        return pode.isSupported(name);
    }

    /**
     * {@inheritDoc}
     */
    public void computeParameterJacobian(double t, double[] y, double[] yDot, String paramName, double[] dFdP) throws DimensionMismatchException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76");
        final int n = fode.getDimension();
        if (pode.isSupported(paramName)) {
            final double[] tmpDot = new double[n];
            // compute the jacobian df/dp w.r.t. parameter
            final double p = pode.getParameter(paramName);
            final double hP = hParam.get(paramName);
            pode.setParameter(paramName, AOR_plus(p, hP, "org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76", _mut12160, _mut12161, _mut12162, _mut12163));
            fode.computeDerivatives(t, y, tmpDot);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76", _mut12172, _mut12173, _mut12174, _mut12175, _mut12176); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76");
                dFdP[i] = AOR_divide((AOR_minus(tmpDot[i], yDot[i], "org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76", _mut12164, _mut12165, _mut12166, _mut12167)), hP, "org.apache.commons.math3.ode.ParameterJacobianWrapper.computeParameterJacobian_76", _mut12168, _mut12169, _mut12170, _mut12171);
            }
            pode.setParameter(paramName, p);
        } else {
            Arrays.fill(dFdP, 0, n, 0.0);
        }
    }
}
