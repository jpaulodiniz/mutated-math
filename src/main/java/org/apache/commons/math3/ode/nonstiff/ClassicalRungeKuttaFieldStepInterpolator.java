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

class ClassicalRungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends RungeKuttaFieldStepInterpolator<T> {

    @Conditional
    public static boolean _mut13729 = false, _mut13730 = false, _mut13731 = false, _mut13732 = false, _mut13733 = false, _mut13734 = false;

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
    ClassicalRungeKuttaFieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(field, forward, yDotK, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ClassicalRungeKuttaFieldStepInterpolator<T> create(final Field<T> newField, final boolean newForward, final T[][] newYDotK, final FieldODEStateAndDerivative<T> newGlobalPreviousState, final FieldODEStateAndDerivative<T> newGlobalCurrentState, final FieldODEStateAndDerivative<T> newSoftPreviousState, final FieldODEStateAndDerivative<T> newSoftCurrentState, final FieldEquationsMapper<T> newMapper) {
        return new ClassicalRungeKuttaFieldStepInterpolator<T>(newField, newForward, newYDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected FieldODEStateAndDerivative<T> computeInterpolatedStateAndDerivatives(final FieldEquationsMapper<T> mapper, final T time, final T theta, final T thetaH, final T oneMinusThetaH) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaFieldStepInterpolator.computeInterpolatedStateAndDerivatives_99");
        final T one = time.getField().getOne();
        final T oneMinusTheta = one.subtract(theta);
        final T oneMinus2Theta = one.subtract(theta.multiply(2));
        final T coeffDot1 = oneMinusTheta.multiply(oneMinus2Theta);
        final T coeffDot23 = theta.multiply(oneMinusTheta).multiply(2);
        final T coeffDot4 = theta.multiply(oneMinus2Theta).negate();
        final T[] interpolatedState;
        final T[] interpolatedDerivatives;
        if ((_mut13734 ? (getGlobalPreviousState() != null || ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaFieldStepInterpolator.computeInterpolatedStateAndDerivatives_99", _mut13729, _mut13730, _mut13731, _mut13732, _mut13733)) : (getGlobalPreviousState() != null && ROR_less_equals(theta.getReal(), 0.5, "org.apache.commons.math3.ode.nonstiff.ClassicalRungeKuttaFieldStepInterpolator.computeInterpolatedStateAndDerivatives_99", _mut13729, _mut13730, _mut13731, _mut13732, _mut13733)))) {
            final T fourTheta2 = theta.multiply(theta).multiply(4);
            final T s = thetaH.divide(6.0);
            final T coeff1 = s.multiply(fourTheta2.subtract(theta.multiply(9)).add(6));
            final T coeff23 = s.multiply(theta.multiply(6).subtract(fourTheta2));
            final T coeff4 = s.multiply(fourTheta2.subtract(theta.multiply(3)));
            interpolatedState = previousStateLinearCombination(coeff1, coeff23, coeff23, coeff4);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot23, coeffDot23, coeffDot4);
        } else {
            final T fourTheta = theta.multiply(4);
            final T s = oneMinusThetaH.divide(6);
            final T coeff1 = s.multiply(theta.multiply(fourTheta.negate().add(5)).subtract(1));
            final T coeff23 = s.multiply(theta.multiply(fourTheta.subtract(2)).subtract(2));
            final T coeff4 = s.multiply(theta.multiply(fourTheta.negate().subtract(1)).subtract(1));
            interpolatedState = currentStateLinearCombination(coeff1, coeff23, coeff23, coeff4);
            interpolatedDerivatives = derivativeLinearCombination(coeffDot1, coeffDot23, coeffDot23, coeffDot4);
        }
        return new FieldODEStateAndDerivative<T>(time, interpolatedState, interpolatedDerivatives);
    }
}
