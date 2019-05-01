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

import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class GillIntegrator extends RungeKuttaIntegrator {

    @Conditional
    public static boolean _mut16524 = false, _mut16525 = false, _mut16526 = false, _mut16527 = false, _mut16528 = false, _mut16529 = false, _mut16530 = false, _mut16531 = false, _mut16532 = false, _mut16533 = false, _mut16534 = false, _mut16535 = false, _mut16536 = false, _mut16537 = false, _mut16538 = false, _mut16539 = false, _mut16540 = false, _mut16541 = false, _mut16542 = false, _mut16543 = false, _mut16544 = false, _mut16545 = false, _mut16546 = false, _mut16547 = false, _mut16548 = false, _mut16549 = false, _mut16550 = false, _mut16551 = false, _mut16552 = false, _mut16553 = false, _mut16554 = false, _mut16555 = false, _mut16556 = false, _mut16557 = false, _mut16558 = false, _mut16559 = false, _mut16560 = false, _mut16561 = false, _mut16562 = false, _mut16563 = false, _mut16564 = false, _mut16565 = false, _mut16566 = false, _mut16567 = false, _mut16568 = false, _mut16569 = false, _mut16570 = false, _mut16571 = false, _mut16572 = false, _mut16573 = false, _mut16574 = false, _mut16575 = false, _mut16576 = false, _mut16577 = false, _mut16578 = false, _mut16579 = false, _mut16580 = false, _mut16581 = false, _mut16582 = false, _mut16583 = false, _mut16584 = false, _mut16585 = false, _mut16586 = false, _mut16587 = false;

    /**
     * Time steps Butcher array.
     */
    private static final double[] STATIC_C = { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16524, _mut16525, _mut16526, _mut16527), AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16528, _mut16529, _mut16530, _mut16531), 1.0 };

    /**
     * Internal weights Butcher array.
     */
    private static final double[][] STATIC_A = { { AOR_divide(1.0, 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16532, _mut16533, _mut16534, _mut16535) }, { AOR_divide((AOR_minus(FastMath.sqrt(2.0), 1.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16536, _mut16537, _mut16538, _mut16539)), 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16540, _mut16541, _mut16542, _mut16543), AOR_divide((AOR_minus(2.0, FastMath.sqrt(2.0), "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16544, _mut16545, _mut16546, _mut16547)), 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16548, _mut16549, _mut16550, _mut16551) }, { 0.0, AOR_divide(-FastMath.sqrt(2.0), 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16552, _mut16553, _mut16554, _mut16555), AOR_divide((AOR_plus(2.0, FastMath.sqrt(2.0), "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16556, _mut16557, _mut16558, _mut16559)), 2.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16560, _mut16561, _mut16562, _mut16563) } };

    /**
     * Propagation weights Butcher array.
     */
    private static final double[] STATIC_B = { AOR_divide(1.0, 6.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16564, _mut16565, _mut16566, _mut16567), AOR_divide((AOR_minus(2.0, FastMath.sqrt(2.0), "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16568, _mut16569, _mut16570, _mut16571)), 6.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16572, _mut16573, _mut16574, _mut16575), AOR_divide((AOR_plus(2.0, FastMath.sqrt(2.0), "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16576, _mut16577, _mut16578, _mut16579)), 6.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16580, _mut16581, _mut16582, _mut16583), AOR_divide(1.0, 6.0, "org.apache.commons.math3.ode.nonstiff.GillIntegrator.createInterpolator_109", _mut16584, _mut16585, _mut16586, _mut16587) };

    /**
     * Simple constructor.
     * Build a fourth-order Gill integrator with the given step.
     * @param step integration step
     */
    public GillIntegrator(final double step) {
        super("Gill", STATIC_C, STATIC_A, STATIC_B, new GillStepInterpolator(), step);
    }
}
