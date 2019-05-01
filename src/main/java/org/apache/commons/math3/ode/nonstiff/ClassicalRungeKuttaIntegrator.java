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

public class ClassicalRungeKuttaIntegrator extends RungeKuttaIntegrator {

    @Conditional
    public static boolean _mut13225 = false, _mut13226 = false, _mut13227 = false, _mut13228 = false, _mut13229 = false, _mut13230 = false, _mut13231 = false, _mut13232 = false, _mut13233 = false, _mut13234 = false, _mut13235 = false, _mut13236 = false, _mut13237 = false, _mut13238 = false, _mut13239 = false, _mut13240 = false, _mut13241 = false, _mut13242 = false, _mut13243 = false, _mut13244 = false, _mut13245 = false, _mut13246 = false, _mut13247 = false, _mut13248 = false, _mut13249 = false, _mut13250 = false, _mut13251 = false, _mut13252 = false, _mut13253 = false, _mut13254 = false, _mut13255 = false, _mut13256 = false;

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13225, _mut13226, _mut13227, _mut13228), AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13229, _mut13230, _mut13231, _mut13232), 1.0 };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13233, _mut13234, _mut13235, _mut13236) }, { 0.0, AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13237, _mut13238, _mut13239, _mut13240) }, { 0.0, 0.0, 1.0 } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { AOR_divide(1.0, 6.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13241, _mut13242, _mut13243, _mut13244), AOR_divide(1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13245, _mut13246, _mut13247, _mut13248), AOR_divide(1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13249, _mut13250, _mut13251, _mut13252), AOR_divide(1.0, 6.0, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaIntegrator.computeInterpolatedStateAndDerivatives_93", _mut13253, _mut13254, _mut13255, _mut13256) };

    /**
     * Simple constructor.
     * Build a fourth-order Runge-Kutta integrator with the given
     * step.
     * @param step integration step
     */
    public ClassicalRungeKuttaIntegrator(final double step) {
        super("classical Runge-Kutta", STATIC_C, STATIC_A, STATIC_B, new ClassicalRungeKuttaStepInterpolator(), step);
    }
}
