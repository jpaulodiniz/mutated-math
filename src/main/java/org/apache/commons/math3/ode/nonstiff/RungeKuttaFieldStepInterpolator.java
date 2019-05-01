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
import org.apache.commons.math3.ode.sampling.AbstractFieldStepInterpolator;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

abstract class RungeKuttaFieldStepInterpolator<T extends RealFieldElement<T>> extends AbstractFieldStepInterpolator<T> {

    @Conditional
    public static boolean _mut13430 = false, _mut13431 = false, _mut13432 = false, _mut13433 = false, _mut13434 = false, _mut13435 = false, _mut13436 = false, _mut13437 = false, _mut13438 = false, _mut13439 = false, _mut13440 = false, _mut13441 = false, _mut13442 = false, _mut13443 = false, _mut13444 = false;

    /**
     * Field to which the time and state vector elements belong.
     */
    private final Field<T> field;

    /**
     * Slopes at the intermediate points.
     */
    private final T[][] yDotK;

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
    protected RungeKuttaFieldStepInterpolator(final Field<T> field, final boolean forward, final T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldODEStateAndDerivative<T> softPreviousState, final FieldODEStateAndDerivative<T> softCurrentState, final FieldEquationsMapper<T> mapper) {
        super(forward, globalPreviousState, globalCurrentState, softPreviousState, softCurrentState, mapper);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.RungeKuttaFieldStepInterpolator_56");
        this.field = field;
        this.yDotK = MathArrays.buildArray(field, yDotK.length, -1);
        for (int i = 0; ROR_less(i, yDotK.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.RungeKuttaFieldStepInterpolator_56", _mut13430, _mut13431, _mut13432, _mut13433, _mut13434); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.RungeKuttaFieldStepInterpolator_56");
            this.yDotK[i] = yDotK[i].clone();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RungeKuttaFieldStepInterpolator<T> create(boolean newForward, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper) {
        return create(field, newForward, yDotK, newGlobalPreviousState, newGlobalCurrentState, newSoftPreviousState, newSoftCurrentState, newMapper);
    }

    /**
     * Create a new instance.
     * @param newField field to which the time and state vector elements belong
     * @param newForward integration direction indicator
     * @param newYDotK slopes at the intermediate points
     * @param newGlobalPreviousState start of the global step
     * @param newGlobalCurrentState end of the global step
     * @param newSoftPreviousState start of the restricted step
     * @param newSoftCurrentState end of the restricted step
     * @param newMapper equations mapper for the all equations
     * @return a new instance
     */
    protected abstract RungeKuttaFieldStepInterpolator<T> create(Field<T> newField, boolean newForward, T[][] newYDotK, FieldODEStateAndDerivative<T> newGlobalPreviousState, FieldODEStateAndDerivative<T> newGlobalCurrentState, FieldODEStateAndDerivative<T> newSoftPreviousState, FieldODEStateAndDerivative<T> newSoftCurrentState, FieldEquationsMapper<T> newMapper);

    /**
     * Compute a state by linear combination added to previous state.
     * @param coefficients coefficients to apply to the method staged derivatives
     * @return combined state
     */
    protected final T[] previousStateLinearCombination(final T... coefficients) {
        return combine(getPreviousState().getState(), coefficients);
    }

    /**
     * Compute a state by linear combination added to current state.
     * @param coefficients coefficients to apply to the method staged derivatives
     * @return combined state
     */
    protected T[] currentStateLinearCombination(final T... coefficients) {
        return combine(getCurrentState().getState(), coefficients);
    }

    /**
     * Compute a state derivative by linear combination.
     * @param coefficients coefficients to apply to the method staged derivatives
     * @return combined state
     */
    protected T[] derivativeLinearCombination(final T... coefficients) {
        return combine(MathArrays.buildArray(field, yDotK[0].length), coefficients);
    }

    /**
     * Linearly combine arrays.
     * @param a array to add to
     * @param coefficients coefficients to apply to the method staged derivatives
     * @return a itself, as a convenience for fluent API
     */
    private T[] combine(final T[] a, final T... coefficients) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.combine_134");
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.combine_134", _mut13440, _mut13441, _mut13442, _mut13443, _mut13444); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.combine_134");
            for (int k = 0; ROR_less(k, coefficients.length, "org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.combine_134", _mut13435, _mut13436, _mut13437, _mut13438, _mut13439); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.RungeKuttaFieldStepInterpolator.combine_134");
                a[i] = a[i].add(coefficients[k].multiply(yDotK[k][i]));
            }
        }
        return a;
    }
}
