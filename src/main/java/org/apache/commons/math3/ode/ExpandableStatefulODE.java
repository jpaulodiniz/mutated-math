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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class ExpandableStatefulODE {

    @Conditional
    public static boolean _mut22452 = false, _mut22453 = false, _mut22454 = false, _mut22455 = false, _mut22456 = false, _mut22457 = false, _mut22458 = false, _mut22459 = false, _mut22460 = false, _mut22461 = false, _mut22462 = false, _mut22463 = false, _mut22464 = false, _mut22465 = false, _mut22466 = false, _mut22467 = false, _mut22468 = false, _mut22469 = false, _mut22470 = false, _mut22471 = false, _mut22472 = false, _mut22473 = false, _mut22474 = false, _mut22475 = false, _mut22476 = false, _mut22477 = false, _mut22478 = false, _mut22479 = false, _mut22480 = false, _mut22481 = false, _mut22482 = false, _mut22483 = false, _mut22484 = false, _mut22485 = false, _mut22486 = false, _mut22487 = false, _mut22488 = false, _mut22489 = false, _mut22490 = false, _mut22491 = false;

    /**
     * Primary differential equation.
     */
    private final FirstOrderDifferentialEquations primary;

    /**
     * Mapper for primary equation.
     */
    private final EquationsMapper primaryMapper;

    /**
     * Time.
     */
    private double time;

    /**
     * State.
     */
    private final double[] primaryState;

    /**
     * State derivative.
     */
    private final double[] primaryStateDot;

    /**
     * Components of the expandable ODE.
     */
    private List<SecondaryComponent> components;

    /**
     * Build an expandable set from its primary ODE set.
     * @param primary the primary set of differential equations to be integrated.
     */
    public ExpandableStatefulODE(final FirstOrderDifferentialEquations primary) {
        final int n = primary.getDimension();
        this.primary = primary;
        this.primaryMapper = new EquationsMapper(0, n);
        this.time = Double.NaN;
        this.primaryState = new double[n];
        this.primaryStateDot = new double[n];
        this.components = new ArrayList<ExpandableStatefulODE.SecondaryComponent>();
    }

    /**
     * Get the primary set of differential equations.
     * @return primary set of differential equations
     */
    public FirstOrderDifferentialEquations getPrimary() {
        return primary;
    }

    /**
     * Return the dimension of the complete set of equations.
     * <p>
     * The complete set of equations correspond to the primary set plus all secondary sets.
     * </p>
     * @return dimension of the complete set of equations
     */
    public int getTotalDimension() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.getTotalDimension_95");
        if (components.isEmpty()) {
            // there are no secondary equations, the complete set is limited to the primary set
            return primaryMapper.getDimension();
        } else {
            // there are secondary equations, the complete set ends after the last set
            final EquationsMapper lastMapper = components.get(AOR_minus(components.size(), 1, "org.apache.commons.math3.ode.ExpandableStatefulODE.getTotalDimension_95", _mut22452, _mut22453, _mut22454, _mut22455)).mapper;
            return AOR_plus(lastMapper.getFirstIndex(), lastMapper.getDimension(), "org.apache.commons.math3.ode.ExpandableStatefulODE.getTotalDimension_95", _mut22456, _mut22457, _mut22458, _mut22459);
        }
    }

    /**
     * Get the current time derivative of the complete state vector.
     * @param t current value of the independent <I>time</I> variable
     * @param y array containing the current value of the complete state vector
     * @param yDot placeholder array where to put the time derivative of the complete state vector
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    public void computeDerivatives(final double t, final double[] y, final double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
        // compute derivatives of the primary equations
        primaryMapper.extractEquationData(y, primaryState);
        primary.computeDerivatives(t, primaryState, primaryStateDot);
        // Add contribution for secondary equations
        for (final SecondaryComponent component : components) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.computeDerivatives_113");
            component.mapper.extractEquationData(y, component.state);
            component.equation.computeDerivatives(t, primaryState, primaryStateDot, component.state, component.stateDot);
            component.mapper.insertEquationData(component.stateDot, yDot);
        }
        primaryMapper.insertEquationData(primaryStateDot, yDot);
    }

    /**
     * Add a set of secondary equations to be integrated along with the primary set.
     * @param secondary secondary equations set
     * @return index of the secondary equation in the expanded state
     */
    public int addSecondaryEquations(final SecondaryEquations secondary) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.addSecondaryEquations_136");
        final int firstIndex;
        if (components.isEmpty()) {
            // lazy creation of the components list
            components = new ArrayList<ExpandableStatefulODE.SecondaryComponent>();
            firstIndex = primary.getDimension();
        } else {
            final SecondaryComponent last = components.get(AOR_minus(components.size(), 1, "org.apache.commons.math3.ode.ExpandableStatefulODE.addSecondaryEquations_136", _mut22460, _mut22461, _mut22462, _mut22463));
            firstIndex = AOR_plus(last.mapper.getFirstIndex(), last.mapper.getDimension(), "org.apache.commons.math3.ode.ExpandableStatefulODE.addSecondaryEquations_136", _mut22464, _mut22465, _mut22466, _mut22467);
        }
        components.add(new SecondaryComponent(secondary, firstIndex));
        return AOR_minus(components.size(), 1, "org.apache.commons.math3.ode.ExpandableStatefulODE.addSecondaryEquations_136", _mut22468, _mut22469, _mut22470, _mut22471);
    }

    /**
     * Get an equations mapper for the primary equations set.
     * @return mapper for the primary set
     * @see #getSecondaryMappers()
     */
    public EquationsMapper getPrimaryMapper() {
        return primaryMapper;
    }

    /**
     * Get the equations mappers for the secondary equations sets.
     * @return equations mappers for the secondary equations sets
     * @see #getPrimaryMapper()
     */
    public EquationsMapper[] getSecondaryMappers() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.getSecondaryMappers_166");
        final EquationsMapper[] mappers = new EquationsMapper[components.size()];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.ode.ExpandableStatefulODE.getSecondaryMappers_166", _mut22472, _mut22473, _mut22474, _mut22475, _mut22476); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.getSecondaryMappers_166");
            mappers[i] = components.get(i).mapper;
        }
        return mappers;
    }

    /**
     * Set current time.
     * @param time current time
     */
    public void setTime(final double time) {
        this.time = time;
    }

    /**
     * Get current time.
     * @return current time
     */
    public double getTime() {
        return time;
    }

    /**
     * Set primary part of the current state.
     * @param primaryState primary part of the current state
     * @throws DimensionMismatchException if the dimension of the array does not
     * match the primary set
     */
    public void setPrimaryState(final double[] primaryState) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.setPrimaryState_193");
        // safety checks
        if (ROR_not_equals(primaryState.length, this.primaryState.length, "org.apache.commons.math3.ode.ExpandableStatefulODE.setPrimaryState_193", _mut22477, _mut22478, _mut22479, _mut22480, _mut22481)) {
            throw new DimensionMismatchException(primaryState.length, this.primaryState.length);
        }
        // set the data
        System.arraycopy(primaryState, 0, this.primaryState, 0, primaryState.length);
    }

    /**
     * Get primary part of the current state.
     * @return primary part of the current state
     */
    public double[] getPrimaryState() {
        return primaryState.clone();
    }

    /**
     * Get primary part of the current state derivative.
     * @return primary part of the current state derivative
     */
    public double[] getPrimaryStateDot() {
        return primaryStateDot.clone();
    }

    /**
     * Set secondary part of the current state.
     * @param index index of the part to set as returned by {@link
     * #addSecondaryEquations(SecondaryEquations)}
     * @param secondaryState secondary part of the current state
     * @throws DimensionMismatchException if the dimension of the partial state does not
     * match the selected equations set dimension
     */
    public void setSecondaryState(final int index, final double[] secondaryState) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.setSecondaryState_226");
        // get either the secondary state
        double[] localArray = components.get(index).state;
        // safety checks
        if (ROR_not_equals(secondaryState.length, localArray.length, "org.apache.commons.math3.ode.ExpandableStatefulODE.setSecondaryState_226", _mut22482, _mut22483, _mut22484, _mut22485, _mut22486)) {
            throw new DimensionMismatchException(secondaryState.length, localArray.length);
        }
        // set the data
        System.arraycopy(secondaryState, 0, localArray, 0, secondaryState.length);
    }

    /**
     * Get secondary part of the current state.
     * @param index index of the part to set as returned by {@link
     * #addSecondaryEquations(SecondaryEquations)}
     * @return secondary part of the current state
     */
    public double[] getSecondaryState(final int index) {
        return components.get(index).state.clone();
    }

    /**
     * Get secondary part of the current state derivative.
     * @param index index of the part to set as returned by {@link
     * #addSecondaryEquations(SecondaryEquations)}
     * @return secondary part of the current state derivative
     */
    public double[] getSecondaryStateDot(final int index) {
        return components.get(index).stateDot.clone();
    }

    /**
     * Set the complete current state.
     * @param completeState complete current state to copy data from
     * @throws DimensionMismatchException if the dimension of the complete state does not
     * match the complete equations sets dimension
     */
    public void setCompleteState(final double[] completeState) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.setCompleteState_265");
        // safety checks
        if (ROR_not_equals(completeState.length, getTotalDimension(), "org.apache.commons.math3.ode.ExpandableStatefulODE.setCompleteState_265", _mut22487, _mut22488, _mut22489, _mut22490, _mut22491)) {
            throw new DimensionMismatchException(completeState.length, getTotalDimension());
        }
        // set the data
        primaryMapper.extractEquationData(completeState, primaryState);
        for (final SecondaryComponent component : components) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.setCompleteState_265");
            component.mapper.extractEquationData(completeState, component.state);
        }
    }

    /**
     * Get the complete current state.
     * @return complete current state
     * @throws DimensionMismatchException if the dimension of the complete state does not
     * match the complete equations sets dimension
     */
    public double[] getCompleteState() throws DimensionMismatchException {
        // allocate complete array
        double[] completeState = new double[getTotalDimension()];
        // set the data
        primaryMapper.insertEquationData(primaryState, completeState);
        for (final SecondaryComponent component : components) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.ExpandableStatefulODE.getCompleteState_286");
            component.mapper.insertEquationData(component.state, completeState);
        }
        return completeState;
    }

    /**
     * Components of the compound stateful ODE.
     */
    private static class SecondaryComponent {

        /**
         * Secondary differential equation.
         */
        private final SecondaryEquations equation;

        /**
         * Mapper between local and complete arrays.
         */
        private final EquationsMapper mapper;

        /**
         * State.
         */
        private final double[] state;

        /**
         * State derivative.
         */
        private final double[] stateDot;

        /**
         * Simple constructor.
         * @param equation secondary differential equation
         * @param firstIndex index to use for the first element in the complete arrays
         */
        SecondaryComponent(final SecondaryEquations equation, final int firstIndex) {
            final int n = equation.getDimension();
            this.equation = equation;
            mapper = new EquationsMapper(firstIndex, n);
            state = new double[n];
            stateDot = new double[n];
        }
    }
}
