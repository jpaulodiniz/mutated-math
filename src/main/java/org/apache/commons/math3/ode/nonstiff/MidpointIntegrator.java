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
package org.apache.commons.math3.ode.nonstiff;

import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class MidpointIntegrator extends RungeKuttaIntegrator {

    @Conditional
    public static boolean _mut19200 = false, _mut19201 = false, _mut19202 = false, _mut19203 = false, _mut19204 = false, _mut19205 = false, _mut19206 = false, _mut19207 = false;

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.MidpointIntegrator.end_401", _mut19200, _mut19201, _mut19202, _mut19203) };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.MidpointIntegrator.end_401", _mut19204, _mut19205, _mut19206, _mut19207) } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { 0.0, 1.0 };

    /**
     * Simple constructor.
     * Build a midpoint integrator with the given step.
     * @param step integration step
     */
    public MidpointIntegrator(final double step) {
        super("midpoint", STATIC_C, STATIC_A, STATIC_B, new MidpointStepInterpolator(), step);
    }
}
