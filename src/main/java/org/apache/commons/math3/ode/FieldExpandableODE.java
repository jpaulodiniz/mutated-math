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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class FieldExpandableODE<T extends RealFieldElement<T>> {

    @Conditional
    public static boolean _mut22492 = false, _mut22493 = false, _mut22494 = false, _mut22495 = false, _mut22496 = false, _mut22497 = false, _mut22498 = false, _mut22499 = false, _mut22500 = false, _mut22501 = false, _mut22502 = false, _mut22503 = false, _mut22504 = false, _mut22505 = false, _mut22506 = false, _mut22507 = false, _mut22508 = false, _mut22509 = false;

    /**
     * Primary differential equation.
     */
    private final FirstOrderFieldDifferentialEquations<T> primary;

    /**
     * Components of the expandable ODE.
     */
    private List<FieldSecondaryEquations<T>> components;

    /**
     * Mapper for all equations.
     */
    private FieldEquationsMapper<T> mapper;

    /**
     * Build an expandable set from its primary ODE set.
     * @param primary the primary set of differential equations to be integrated.
     */
    public FieldExpandableODE(final FirstOrderFieldDifferentialEquations<T> primary) {
        this.primary = primary;
        this.components = new ArrayList<FieldSecondaryEquations<T>>();
        this.mapper = new FieldEquationsMapper<T>(null, primary.getDimension());
    }

    /**
     * Get the mapper for the set of equations.
     * @return mapper for the set of equations
     */
    public FieldEquationsMapper<T> getMapper() {
        return mapper;
    }

    /**
     * Add a set of secondary equations to be integrated along with the primary set.
     * @param secondary secondary equations set
     * @return index of the secondary equation in the expanded state, to be used
     * as the parameter to {@link FieldODEState#getSecondaryState(int)} and
     * {@link FieldODEStateAndDerivative#getSecondaryDerivative(int)} (beware index
     * 0 corresponds to main state, additional states start at 1)
     */
    public int addSecondaryEquations(final FieldSecondaryEquations<T> secondary) {
        components.add(secondary);
        mapper = new FieldEquationsMapper<T>(mapper, secondary.getDimension());
        return components.size();
    }

    /**
     * Initialize equations at the start of an ODE integration.
     * @param t0 value of the independent <I>time</I> variable at integration start
     * @param y0 array containing the value of the state vector at integration start
     * @param finalTime target time for the integration
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public void init(final T t0, final T[] y0, final T finalTime) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldExpandableODE.init_102");
        // initialize primary equations
        int index = 0;
        final T[] primary0 = mapper.extractEquationData(index, y0);
        primary.init(t0, primary0, finalTime);
        // initialize secondary equations
        while (ROR_less(++index, mapper.getNumberOfEquations(), "org.apache.commons.math3.ode.FieldExpandableODE.init_102", _mut22496, _mut22497, _mut22498, _mut22499, _mut22500)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldExpandableODE.init_102");
            final T[] secondary0 = mapper.extractEquationData(index, y0);
            components.get(AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldExpandableODE.init_102", _mut22492, _mut22493, _mut22494, _mut22495)).init(t0, primary0, secondary0, finalTime);
        }
    }

    /**
     * Get the current time derivative of the complete state vector.
     * @param t current value of the independent <I>time</I> variable
     * @param y array containing the current value of the complete state vector
     * @return time derivative of the complete state vector
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public T[] computeDerivatives(final T t, final T[] y) throws MaxCountExceededException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldExpandableODE.computeDerivatives_124");
        final T[] yDot = MathArrays.buildArray(t.getField(), mapper.getTotalDimension());
        // compute derivatives of the primary equations
        int index = 0;
        final T[] primaryState = mapper.extractEquationData(index, y);
        final T[] primaryStateDot = primary.computeDerivatives(t, primaryState);
        mapper.insertEquationData(index, primaryStateDot, yDot);
        // Add contribution for secondary equations
        while (ROR_less(++index, mapper.getNumberOfEquations(), "org.apache.commons.math3.ode.FieldExpandableODE.computeDerivatives_124", _mut22505, _mut22506, _mut22507, _mut22508, _mut22509)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.FieldExpandableODE.computeDerivatives_124");
            final T[] componentState = mapper.extractEquationData(index, y);
            final T[] componentStateDot = components.get(AOR_minus(index, 1, "org.apache.commons.math3.ode.FieldExpandableODE.computeDerivatives_124", _mut22501, _mut22502, _mut22503, _mut22504)).computeDerivatives(t, primaryState, primaryStateDot, componentState);
            mapper.insertEquationData(index, componentStateDot, yDot);
        }
        return yDot;
    }
}
