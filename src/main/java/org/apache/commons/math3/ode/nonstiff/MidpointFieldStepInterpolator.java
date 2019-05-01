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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.ode.FieldEquationsMapper;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class MidpointFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {

    @Conditional
    public static boolean _mut18950 = false, _mut18951 = false, _mut18952 = false, _mut18953 = false, _mut18954 = false, _mut18955 = false;

    /**
     * Simple constructor.
     * @param field field to which the time and state vector elements belong
     * @param forward integration direction indicator
     * @param yDotK slopes at the intermediate points
     * @param globalPreviousState start of the global step
     * @param globalCurrentState end of the global step
     * @param softPreviousState start of the restricted step
     * @param softCurrentState end of the restricted step
     * @param mapper equations mapper for the all equations
     */
    MidpointFieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected MidpointFieldStepInterpolator<T> create(final Field<T> newField, final boolean newForward, final T[][] newYDotK, final FieldODEStateAndDerivative<T> newGlobalPreviousState, final FieldODEStateAndDerivative<T> newGlobalCurrentState, final FieldODEStateAndDerivative<T> newSoftPreviousState, final FieldODEStateAndDerivative<T> newSoftCurrentState, final FieldEquationsMapper<T> newMapper) {
        return new MidpointFieldStepInterpolator<T>(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(final FieldEquationsMapper<T> mapper, final T time, final T theta, final T thetaH, final T oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.MidpointFieldStepInterpolator.computeInterpolatedStateAndDerivatives_91");
        final T coeffDot2 = theta.multiply(2);
        final T coeffDot1 = time.getField().getOne().subtract(coeffDot2);
        final T[] interpolatedState;
        final T[] interpolatedDerivatives;
        if ((_mut18955 ? (getGlobalPreviousState() != null || ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.MidpointFieldStepInterpolator.computeInterpolatedStateAndDerivatives_91", _mut18950, _mut18951, _mut18952, _mut18953, _mut18954)) : (getGlobalPreviousState() != null && ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.MidpointFieldStepInterpolator.computeInterpolatedStateAndDerivatives_91", _mut18950, _mut18951, _mut18952, _mut18953, _mut18954)))) {
            final T coeff1 = theta.multiply(oneMinusThetaH);
            final T coeff2 = theta.multiply(thetaH);
            interpolatedState = previousStateLinearCombination(coeff1, coeff2);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot2);
        } else {
            final T coeff1 = oneMinusThetaH.multiply(theta);
            final T coeff2 = oneMinusThetaH.multiply(theta.add(1)).negate();
            interpolatedState = currentStateLinearCombination(coeff1, coeff2);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot2);
        }
        return new FieldODEStateAndDerivative<T>(time, interpolatedState, interpolatedDerivatives);
    }
}
