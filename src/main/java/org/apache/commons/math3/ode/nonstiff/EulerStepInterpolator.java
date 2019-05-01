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

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class EulerStepInterpolator extends RungeKuttaStepInterpolator {

    @Conditional
    public static boolean _mut13454 = false, _mut13455 = false, _mut13456 = false, _mut13457 = false, _mut13458 = false, _mut13459 = false, _mut13460 = false, _mut13461 = false, _mut13462 = false, _mut13463 = false, _mut13464 = false, _mut13465 = false, _mut13466 = false, _mut13467 = false, _mut13468 = false, _mut13469 = false, _mut13470 = false, _mut13471 = false, _mut13472 = false, _mut13473 = false, _mut13474 = false, _mut13475 = false, _mut13476 = false, _mut13477 = false, _mut13478 = false, _mut13479 = false, _mut13480 = false, _mut13481 = false, _mut13482 = false, _mut13483 = false, _mut13484 = false, _mut13485 = false, _mut13486 = false, _mut13487 = false, _mut13488 = false, _mut13489 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20111120L;

    // the public modifier here is needed for serialization
    public EulerStepInterpolator() {
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    EulerStepInterpolator(final EulerStepInterpolator interpolator) {
        super(interpolator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new EulerStepInterpolator(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85");
        if ((_mut13459 ? ((previousState != null) || (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13454, _mut13455, _mut13456, _mut13457, _mut13458))) : ((previousState != null) && (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13454, _mut13455, _mut13456, _mut13457, _mut13458))))) {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13485, _mut13486, _mut13487, _mut13488, _mut13489); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85");
                interpolatedState[i] = AOR_plus(previousState[i], AOR_multiply(AOR_multiply(theta, h, "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13473, _mut13474, _mut13475, _mut13476), yDotK[0][i], "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13477, _mut13478, _mut13479, _mut13480), "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13481, _mut13482, _mut13483, _mut13484);
            }
            System.arraycopy(yDotK[0], 0, interpolatedDerivatives, 0, interpolatedDerivatives.length);
        } else {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13468, _mut13469, _mut13470, _mut13471, _mut13472); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85");
                interpolatedState[i] = AOR_minus(currentState[i], AOR_multiply(oneMinusThetaH, yDotK[0][i], "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13460, _mut13461, _mut13462, _mut13463), "org.apache.commons.math3.ode.nonstiff.EulerStepInterpolator.computeInterpolatedStateAndDerivatives_85", _mut13464, _mut13465, _mut13466, _mut13467);
            }
            System.arraycopy(yDotK[0], 0, interpolatedDerivatives, 0, interpolatedDerivatives.length);
        }
    }
}
