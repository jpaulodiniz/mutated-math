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
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class ThreeEighthesFieldIntegrator<T extends RealFieldElement<T>> extends RungeKuttaFieldIntegrator<T> {

    @Conditional
    public static boolean _mut13445 = false, _mut13446 = false, _mut13447 = false, _mut13448 = false, _mut13449 = false, _mut13450 = false, _mut13451 = false, _mut13452 = false, _mut13453 = false;

    /**
     * Simple constructor.
     * Build a 3/8 integrator with the given step.
     * @param field field to which the time and state vector elements belong
     * @param step integration step
     */
    public ThreeEighthesFieldIntegrator(final Field<T> field, final T step) {
        super(field, "3/8", step);
    }

    /**
     * {@inheritDoc}
     */
    public T[] getC() {
        final T[] c = MathArrays.buildArray(getField(), 3);
        c[0] = fraction(1, 3);
        c[1] = c[0].add(c[0]);
        c[2] = getField().getOne();
        return c;
    }

    /**
     * {@inheritDoc}
     */
    public T[][] getA() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.ThreeEighthesFieldIntegrator.getA_73");
        final T[][] a = MathArrays.buildArray(getField(), 3, -1);
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesFieldIntegrator.getA_73", _mut13449, _mut13450, _mut13451, _mut13452, _mut13453); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.ThreeEighthesFieldIntegrator.getA_73");
            a[i] = MathArrays.buildArray(getField(), AOR_plus(i, 1, "org.apache.commons.math3.ode.nonstiff.ThreeEighthesFieldIntegrator.getA_73", _mut13445, _mut13446, _mut13447, _mut13448));
        }
        a[0][0] = fraction(1, 3);
        a[1][0] = a[0][0].negate();
        a[1][1] = getField().getOne();
        a[2][0] = getField().getOne();
        a[2][1] = getField().getOne().negate();
        a[2][2] = getField().getOne();
        return a;
    }

    /**
     * {@inheritDoc}
     */
    public T[] getB() {
        final T[] b = MathArrays.buildArray(getField(), 4);
        b[0] = fraction(1, 8);
        b[1] = fraction(3, 8);
        b[2] = b[1];
        b[3] = b[0];
        return b;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ThreeEighthesFieldStepInterpolator<T> createInterpolator(final boolean forward, T[][] yDotK, final FieldODEStateAndDerivative<T> globalPreviousState, final FieldODEStateAndDerivative<T> globalCurrentState, final FieldEquationsMapper<T> mapper) {
        return new ThreeEighthesFieldStepInterpolator<T>(getField(), forward, yDotK, globalPreviousState, globalCurrentState, globalPreviousState, globalCurrentState, mapper);
    }
}
