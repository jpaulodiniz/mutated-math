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

import org.apache.commons.math3.RealFieldElement;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FieldODEStateAndDerivative<T extends RealFieldElement<T>> extends FieldODEState<T> {

    @Conditional
    public static boolean _mut11731 = false, _mut11732 = false, _mut11733 = false, _mut11734 = false, _mut11735 = false, _mut11736 = false, _mut11737 = false, _mut11738 = false, _mut11739 = false;

    /**
     * Derivative of the main state at time.
     */
    private final T[] derivative;

    /**
     * Derivative of the secondary state at time.
     */
    private final T[][] secondaryDerivative;

    /**
     * Simple constructor.
     * <p>Calling this constructor is equivalent to call {@link
     * #FieldODEStateAndDerivative(RealFieldElement, RealFieldElement[], RealFieldElement[],
     * RealFieldElement[][], RealFieldElement[][]) FieldODEStateAndDerivative(time, state,
     * derivative, null, null)}.</p>
     * @param time time
     * @param state state at time
     * @param derivative derivative of the state at time
     */
    public FieldODEStateAndDerivative(T time, T[] state, T[] derivative) {
        this(time, state, derivative, null, null);
    }

    /**
     * Simple constructor.
     * @param time time
     * @param state state at time
     * @param derivative derivative of the state at time
     * @param secondaryState state at time (may be null)
     * @param secondaryDerivative derivative of the state at time (may be null)
     */
    public FieldODEStateAndDerivative(T time, T[] state, T[] derivative, T[][] secondaryState, T[][] secondaryDerivative) {
        super(time, state, secondaryState);
        this.derivative = derivative.clone();
        this.secondaryDerivative = copy(time.getField(), secondaryDerivative);
    }

    /**
     * Get derivative of the main state at time.
     * @return derivative of the main state at time
     */
    public T[] getDerivative() {
        return derivative.clone();
    }

    /**
     * Get derivative of the secondary state at time.
     * @param index index of the secondary set as returned
     * by {@link FieldExpandableODE#addSecondaryEquations(FieldSecondaryEquations)}
     * (beware index 0 corresponds to main state, additional states start at 1)
     * @return derivative of the secondary state at time
     */
    public T[] getSecondaryDerivative(final int index) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldODEStateAndDerivative.getSecondaryDerivative_78");
        return ROR_equals(index, 0, "org.apache.commons.math3.ode.FieldODEStateAndDerivative.getSecondaryDerivative_78", _mut11731, _mut11732, _mut11733, _mut11734, _mut11735) ? derivative.clone() : secondaryDerivative[AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldODEStateAndDerivative.getSecondaryDerivative_78", _mut11736, _mut11737, _mut11738, _mut11739)].clone();
    }
}
