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

public class ThreeEighthesIntegrator extends RungeKuttaIntegrator {

    @Conditional
    public static boolean _mut13697 = false, _mut13698 = false, _mut13699 = false, _mut13700 = false, _mut13701 = false, _mut13702 = false, _mut13703 = false, _mut13704 = false, _mut13705 = false, _mut13706 = false, _mut13707 = false, _mut13708 = false, _mut13709 = false, _mut13710 = false, _mut13711 = false, _mut13712 = false, _mut13713 = false, _mut13714 = false, _mut13715 = false, _mut13716 = false, _mut13717 = false, _mut13718 = false, _mut13719 = false, _mut13720 = false, _mut13721 = false, _mut13722 = false, _mut13723 = false, _mut13724 = false, _mut13725 = false, _mut13726 = false, _mut13727 = false, _mut13728 = false;

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13697, _mut13698, _mut13699, _mut13700), AOR_divide(2.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13701, _mut13702, _mut13703, _mut13704), 1.0 };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13705, _mut13706, _mut13707, _mut13708) }, { AOR_divide(-1.0, 3.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13709, _mut13710, _mut13711, _mut13712), 1.0 }, { 1.0, -1.0, 1.0 } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { AOR_divide(1.0, 8.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13713, _mut13714, _mut13715, _mut13716), AOR_divide(3.0, 8.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13717, _mut13718, _mut13719, _mut13720), AOR_divide(3.0, 8.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13721, _mut13722, _mut13723, _mut13724), AOR_divide(1.0, 8.0, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesIntegrator.updateHighOrderDerivativesPhase2_350", _mut13725, _mut13726, _mut13727, _mut13728) };

    /**
     * Simple constructor.
     * Build a 3/8 integrator with the given step.
     * @param step integration step
     */
    public ThreeEighthesIntegrator(final double step) {
        super("3/8", STATIC_C, STATIC_A, STATIC_B, new ThreeEighthesStepInterpolator(), step);
    }
}
