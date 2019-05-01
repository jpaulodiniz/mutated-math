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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class defines a set of {@link SecondaryEquations secondary equations} to
 * compute the Jacobian matrices with respect to the initial state vector and, if
 * any, to some parameters of the primary ODE set.
 * <p>
 * It is intended to be packed into an {@link ExpandableStatefulODE}
 * in conjunction with a primary set of ODE, which may be:
 * <ul>
 * <li>a {@link FirstOrderDifferentialEquations}</li>
 * <li>a {@link MainStateJacobianProvider}</li>
 * </ul>
 * In order to compute Jacobian matrices with respect to some parameters of the
 * primary ODE set, the following parameter Jacobian providers may be set:
 * <ul>
 * <li>a {@link ParameterJacobianProvider}</li>
 * <li>a {@link ParameterizedODE}</li>
 * </ul>
 * </p>
 *
 * @see ExpandableStatefulODE
 * @see FirstOrderDifferentialEquations
 * @see MainStateJacobianProvider
 * @see ParameterJacobianProvider
 * @see ParameterizedODE
 *
 * @since 3.0
 */
public class JacobianMatrices {

    @Conditional
    public static boolean _mut11526 = false, _mut11527 = false, _mut11528 = false, _mut11529 = false, _mut11530 = false, _mut11531 = false, _mut11532 = false, _mut11533 = false, _mut11534 = false, _mut11535 = false, _mut11536 = false, _mut11537 = false, _mut11538 = false, _mut11539 = false, _mut11540 = false, _mut11541 = false, _mut11542 = false, _mut11543 = false, _mut11544 = false, _mut11545 = false, _mut11546 = false, _mut11547 = false, _mut11548 = false, _mut11549 = false, _mut11550 = false, _mut11551 = false, _mut11552 = false, _mut11553 = false, _mut11554 = false, _mut11555 = false, _mut11556 = false, _mut11557 = false, _mut11558 = false, _mut11559 = false, _mut11560 = false, _mut11561 = false, _mut11562 = false, _mut11563 = false, _mut11564 = false, _mut11565 = false, _mut11566 = false, _mut11567 = false, _mut11568 = false, _mut11569 = false, _mut11570 = false, _mut11571 = false, _mut11572 = false, _mut11573 = false, _mut11574 = false, _mut11575 = false, _mut11576 = false, _mut11577 = false, _mut11578 = false, _mut11579 = false, _mut11580 = false, _mut11581 = false, _mut11582 = false, _mut11583 = false, _mut11584 = false, _mut11585 = false, _mut11586 = false, _mut11587 = false, _mut11588 = false, _mut11589 = false, _mut11590 = false, _mut11591 = false, _mut11592 = false, _mut11593 = false, _mut11594 = false, _mut11595 = false, _mut11596 = false, _mut11597 = false, _mut11598 = false, _mut11599 = false, _mut11600 = false, _mut11601 = false, _mut11602 = false, _mut11603 = false, _mut11604 = false, _mut11605 = false, _mut11606 = false, _mut11607 = false, _mut11608 = false, _mut11609 = false, _mut11610 = false, _mut11611 = false, _mut11612 = false, _mut11613 = false, _mut11614 = false, _mut11615 = false, _mut11616 = false, _mut11617 = false, _mut11618 = false, _mut11619 = false, _mut11620 = false, _mut11621 = false, _mut11622 = false, _mut11623 = false, _mut11624 = false, _mut11625 = false, _mut11626 = false, _mut11627 = false, _mut11628 = false, _mut11629 = false, _mut11630 = false, _mut11631 = false, _mut11632 = false, _mut11633 = false, _mut11634 = false, _mut11635 = false, _mut11636 = false, _mut11637 = false, _mut11638 = false, _mut11639 = false, _mut11640 = false, _mut11641 = false, _mut11642 = false, _mut11643 = false, _mut11644 = false, _mut11645 = false, _mut11646 = false, _mut11647 = false, _mut11648 = false, _mut11649 = false, _mut11650 = false, _mut11651 = false, _mut11652 = false, _mut11653 = false, _mut11654 = false, _mut11655 = false, _mut11656 = false, _mut11657 = false, _mut11658 = false, _mut11659 = false, _mut11660 = false, _mut11661 = false, _mut11662 = false, _mut11663 = false, _mut11664 = false, _mut11665 = false, _mut11666 = false, _mut11667 = false, _mut11668 = false, _mut11669 = false, _mut11670 = false;

    /**
     * Expandable first order differential equation.
     */
    private ExpandableStatefulODE efode;

    /**
     * Index of the instance in the expandable set.
     */
    private int index;

    /**
     * FODE with exact primary Jacobian computation skill.
     */
    private MainStateJacobianProvider jode;

    /**
     * FODE without exact parameter Jacobian computation skill.
     */
    private ParameterizedODE pode;

    /**
     * Main state vector dimension.
     */
    private int stateDim;

    /**
     * Selected parameters for parameter Jacobian computation.
     */
    private ParameterConfiguration[] selectedParameters;

    /**
     * FODE with exact parameter Jacobian computation skill.
     */
    private List<ParameterJacobianProvider> jacobianProviders;

    /**
     * Parameters dimension.
     */
    private int paramDim;

    /**
     * Boolean for selected parameters consistency.
     */
    private boolean dirtyParameter;

    /**
     * State and parameters Jacobian matrices in a row.
     */
    private double[] matricesData;

    /**
     * Simple constructor for a secondary equations set computing Jacobian matrices.
     * <p>
     * Parameters must belong to the supported ones given by {@link
     * Parameterizable#getParametersNames()}, so the primary set of differential
     * equations must be {@link Parameterizable}.
     * </p>
     * <p>Note that each selection clears the previous selected parameters.</p>
     *
     * @param fode the primary first order differential equations set to extend
     * @param hY step used for finite difference computation with respect to state vector
     * @param parameters parameters to consider for Jacobian matrices processing
     * (may be null if parameters Jacobians is not desired)
     * @exception DimensionMismatchException if there is a dimension mismatch between
     * the steps array {@code hY} and the equation dimension
     */
    public JacobianMatrices(final FirstOrderDifferentialEquations fode, final double[] hY, final String... parameters) throws DimensionMismatchException {
        this(new MainStateJacobianWrapper(fode, hY), parameters);
    }

    /**
     * Simple constructor for a secondary equations set computing Jacobian matrices.
     * <p>
     * Parameters must belong to the supported ones given by {@link
     * Parameterizable#getParametersNames()}, so the primary set of differential
     * equations must be {@link Parameterizable}.
     * </p>
     * <p>Note that each selection clears the previous selected parameters.</p>
     *
     * @param jode the primary first order differential equations set to extend
     * @param parameters parameters to consider for Jacobian matrices processing
     * (may be null if parameters Jacobians is not desired)
     */
    public JacobianMatrices(final MainStateJacobianProvider jode, final String... parameters) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121");
        this.efode = null;
        this.index = -1;
        this.jode = jode;
        this.pode = null;
        this.stateDim = jode.getDimension();
        if (parameters == null) {
            selectedParameters = null;
            paramDim = 0;
        } else {
            this.selectedParameters = new ParameterConfiguration[parameters.length];
            for (int i = 0; ROR_less(i, parameters.length, "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11526, _mut11527, _mut11528, _mut11529, _mut11530); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121");
                selectedParameters[i] = new ParameterConfiguration(parameters[i], Double.NaN);
            }
            paramDim = parameters.length;
        }
        this.dirtyParameter = false;
        this.jacobianProviders = new ArrayList<ParameterJacobianProvider>();
        // and the default initial parameters Jacobian to the null matrix
        matricesData = new double[AOR_multiply((AOR_plus(stateDim, paramDim, "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11531, _mut11532, _mut11533, _mut11534)), stateDim, "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11535, _mut11536, _mut11537, _mut11538)];
        for (int i = 0; ROR_less(i, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11547, _mut11548, _mut11549, _mut11550, _mut11551); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121");
            matricesData[AOR_multiply(i, (AOR_plus(stateDim, 1, "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11539, _mut11540, _mut11541, _mut11542)), "org.apache.commons.math3.ode.JacobianMatrices.JacobianMatrices_121", _mut11543, _mut11544, _mut11545, _mut11546)] = 1.0;
        }
    }

    /**
     * Register the variational equations for the Jacobians matrices to the expandable set.
     * @param expandable expandable set into which variational equations should be registered
     * @throws DimensionMismatchException if the dimension of the partial state does not
     * match the selected equations set dimension
     * @exception MismatchedEquations if the primary set of the expandable set does
     * not match the one used to build the instance
     * @see ExpandableStatefulODE#addSecondaryEquations(SecondaryEquations)
     */
    public void registerVariationalEquations(final ExpandableStatefulODE expandable) throws DimensionMismatchException, MismatchedEquations {
        // safety checks
        final FirstOrderDifferentialEquations ode = (jode instanceof MainStateJacobianWrapper) ? ((MainStateJacobianWrapper) jode).ode : jode;
        if (expandable.getPrimary() != ode) {
            throw new MismatchedEquations();
        }
        efode = expandable;
        index = efode.addSecondaryEquations(new JacobiansSecondaryEquations());
        efode.setSecondaryState(index, matricesData);
    }

    /**
     * Add a parameter Jacobian provider.
     * @param provider the parameter Jacobian provider to compute exactly the parameter Jacobian matrix
     */
    public void addParameterJacobianProvider(final ParameterJacobianProvider provider) {
        jacobianProviders.add(provider);
    }

    /**
     * Set a parameter Jacobian provider.
     * @param parameterizedOde the parameterized ODE to compute the parameter Jacobian matrix using finite differences
     */
    public void setParameterizedODE(final ParameterizedODE parameterizedOde) {
        this.pode = parameterizedOde;
        dirtyParameter = true;
    }

    /**
     * Set the step associated to a parameter in order to compute by finite
     *  difference the Jacobian matrix.
     * <p>
     * Needed if and only if the primary ODE set is a {@link ParameterizedODE}.
     * </p>
     * <p>
     * Given a non zero parameter value pval for the parameter, a reasonable value
     * for such a step is {@code pval * FastMath.sqrt(Precision.EPSILON)}.
     * </p>
     * <p>
     * A zero value for such a step doesn't enable to compute the parameter Jacobian matrix.
     * </p>
     * @param parameter parameter to consider for Jacobian processing
     * @param hP step for Jacobian finite difference computation w.r.t. the specified parameter
     * @see ParameterizedODE
     * @exception UnknownParameterException if the parameter is not supported
     */
    public void setParameterStep(final String parameter, final double hP) throws UnknownParameterException {
        for (ParameterConfiguration param : selectedParameters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.setParameterStep_212");
            if (parameter.equals(param.getParameterName())) {
                param.setHP(hP);
                dirtyParameter = true;
                return;
            }
        }
        throw new UnknownParameterException(parameter);
    }

    /**
     * Set the initial value of the Jacobian matrix with respect to state.
     * <p>
     * If this method is not called, the initial value of the Jacobian
     * matrix with respect to state is set to identity.
     * </p>
     * @param dYdY0 initial Jacobian matrix w.r.t. state
     * @exception DimensionMismatchException if matrix dimensions are incorrect
     */
    public void setInitialMainStateJacobian(final double[][] dYdY0) throws DimensionMismatchException {
        // Check dimensions
        checkDimension(stateDim, dYdY0);
        checkDimension(stateDim, dYdY0[0]);
        // store the matrix in row major order as a single dimension array
        int i = 0;
        for (final double[] row : dYdY0) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.setInitialMainStateJacobian_235");
            System.arraycopy(row, 0, matricesData, i, stateDim);
            i += stateDim;
        }
        if (efode != null) {
            efode.setSecondaryState(index, matricesData);
        }
    }

    /**
     * Set the initial value of a column of the Jacobian matrix with respect to one parameter.
     * <p>
     * If this method is not called for some parameter, the initial value of
     * the column of the Jacobian matrix with respect to this parameter is set to zero.
     * </p>
     * @param pName parameter name
     * @param dYdP initial Jacobian column vector with respect to the parameter
     * @exception UnknownParameterException if a parameter is not supported
     * @throws DimensionMismatchException if the column vector does not match state dimension
     */
    public void setInitialParameterJacobian(final String pName, final double[] dYdP) throws UnknownParameterException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.setInitialParameterJacobian_265");
        // Check dimensions
        checkDimension(stateDim, dYdP);
        // store the column in a global single dimension array
        int i = AOR_multiply(stateDim, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.setInitialParameterJacobian_265", _mut11552, _mut11553, _mut11554, _mut11555);
        for (ParameterConfiguration param : selectedParameters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.setInitialParameterJacobian_265");
            if (pName.equals(param.getParameterName())) {
                System.arraycopy(dYdP, 0, matricesData, i, stateDim);
                if (efode != null) {
                    efode.setSecondaryState(index, matricesData);
                }
                return;
            }
            i += stateDim;
        }
        throw new UnknownParameterException(pName);
    }

    /**
     * Get the current value of the Jacobian matrix with respect to state.
     * @param dYdY0 current Jacobian matrix with respect to state.
     */
    public void getCurrentMainSetJacobian(final double[][] dYdY0) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.getCurrentMainSetJacobian_291");
        // get current state for this set of equations from the expandable fode
        double[] p = efode.getSecondaryState(index);
        int j = 0;
        for (int i = 0; ROR_less(i, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.getCurrentMainSetJacobian_291", _mut11556, _mut11557, _mut11558, _mut11559, _mut11560); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.getCurrentMainSetJacobian_291");
            System.arraycopy(p, j, dYdY0[i], 0, stateDim);
            j += stateDim;
        }
    }

    /**
     * Get the current value of the Jacobian matrix with respect to one parameter.
     * @param pName name of the parameter for the computed Jacobian matrix
     * @param dYdP current Jacobian matrix with respect to the named parameter
     */
    public void getCurrentParameterJacobian(String pName, final double[] dYdP) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.getCurrentParameterJacobian_308");
        // get current state for this set of equations from the expandable fode
        double[] p = efode.getSecondaryState(index);
        int i = AOR_multiply(stateDim, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.getCurrentParameterJacobian_308", _mut11561, _mut11562, _mut11563, _mut11564);
        for (ParameterConfiguration param : selectedParameters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.getCurrentParameterJacobian_308");
            if (param.getParameterName().equals(pName)) {
                System.arraycopy(p, i, dYdP, 0, stateDim);
                return;
            }
            i += stateDim;
        }
    }

    /**
     * Check array dimensions.
     * @param expected expected dimension
     * @param array (may be null if expected is 0)
     * @throws DimensionMismatchException if the array dimension does not match the expected one
     */
    private void checkDimension(final int expected, final Object array) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.checkDimension_329");
        int arrayDimension = (array == null) ? 0 : Array.getLength(array);
        if (ROR_not_equals(arrayDimension, expected, "org.apache.commons.math3.ode.JacobianMatrices.checkDimension_329", _mut11565, _mut11566, _mut11567, _mut11568, _mut11569)) {
            throw new DimensionMismatchException(arrayDimension, expected);
        }
    }

    /**
     * Local implementation of secondary equations.
     * <p>
     * This class is an inner class to ensure proper scheduling of calls
     * by forcing the use of {@link JacobianMatrices#registerVariationalEquations(ExpandableStatefulODE)}.
     * </p>
     */
    private class JacobiansSecondaryEquations implements SecondaryEquations {

        /**
         * {@inheritDoc}
         */
        public int getDimension() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.getDimension_346");
            return AOR_multiply(stateDim, (AOR_plus(stateDim, paramDim, "org.apache.commons.math3.ode.JacobianMatrices.getDimension_346", _mut11570, _mut11571, _mut11572, _mut11573)), "org.apache.commons.math3.ode.JacobianMatrices.getDimension_346", _mut11574, _mut11575, _mut11576, _mut11577);
        }

        /**
         * {@inheritDoc}
         */
        public void computeDerivatives(final double t, final double[] y, final double[] yDot, final double[] z, final double[] zDot) throws MaxCountExceededException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
            // Lazy initialization
            if ((_mut11583 ? (dirtyParameter || (ROR_not_equals(paramDim, 0, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11578, _mut11579, _mut11580, _mut11581, _mut11582))) : (dirtyParameter && (ROR_not_equals(paramDim, 0, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11578, _mut11579, _mut11580, _mut11581, _mut11582))))) {
                jacobianProviders.add(new ParameterJacobianWrapper(jode, pode, selectedParameters));
                dirtyParameter = false;
            }
            // compute Jacobian matrix with respect to primary state
            double[][] dFdY = new double[stateDim][stateDim];
            jode.computeMainStateJacobian(t, y, yDot, dFdY);
            // Dispatch Jacobian matrix in the compound secondary state vector
            for (int i = 0; ROR_less(i, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11606, _mut11607, _mut11608, _mut11609, _mut11610); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                final double[] dFdYi = dFdY[i];
                for (int j = 0; ROR_less(j, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11601, _mut11602, _mut11603, _mut11604, _mut11605); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                    double s = 0;
                    final int startIndex = j;
                    int zIndex = startIndex;
                    for (int l = 0; ROR_less(l, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11588, _mut11589, _mut11590, _mut11591, _mut11592); ++l) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                        s += AOR_multiply(dFdYi[l], z[zIndex], "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11584, _mut11585, _mut11586, _mut11587);
                        zIndex += stateDim;
                    }
                    zDot[AOR_plus(startIndex, AOR_multiply(i, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11593, _mut11594, _mut11595, _mut11596), "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11597, _mut11598, _mut11599, _mut11600)] = s;
                }
            }
            if (ROR_not_equals(paramDim, 0, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11611, _mut11612, _mut11613, _mut11614, _mut11615)) {
                // compute Jacobian matrices with respect to parameters
                double[] dFdP = new double[stateDim];
                int startIndex = AOR_multiply(stateDim, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11616, _mut11617, _mut11618, _mut11619);
                for (ParameterConfiguration param : selectedParameters) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                    boolean found = false;
                    for (int k = 0; (_mut11643 ? ((!found) || (ROR_less(k, jacobianProviders.size(), "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11638, _mut11639, _mut11640, _mut11641, _mut11642))) : ((!found) && (ROR_less(k, jacobianProviders.size(), "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11638, _mut11639, _mut11640, _mut11641, _mut11642)))); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                        final ParameterJacobianProvider provider = jacobianProviders.get(k);
                        if (provider.isSupported(param.getParameterName())) {
                            provider.computeParameterJacobian(t, y, yDot, param.getParameterName(), dFdP);
                            for (int i = 0; ROR_less(i, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11633, _mut11634, _mut11635, _mut11636, _mut11637); ++i) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                                final double[] dFdYi = dFdY[i];
                                int zIndex = startIndex;
                                double s = dFdP[i];
                                for (int l = 0; ROR_less(l, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11624, _mut11625, _mut11626, _mut11627, _mut11628); ++l) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351");
                                    s += AOR_multiply(dFdYi[l], z[zIndex], "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11620, _mut11621, _mut11622, _mut11623);
                                    zIndex++;
                                }
                                zDot[AOR_plus(startIndex, i, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11629, _mut11630, _mut11631, _mut11632)] = s;
                            }
                            found = true;
                        }
                    }
                    if (!found) {
                        Arrays.fill(zDot, startIndex, AOR_plus(startIndex, stateDim, "org.apache.commons.math3.ode.JacobianMatrices.computeDerivatives_351", _mut11644, _mut11645, _mut11646, _mut11647), 0.0);
                    }
                    startIndex += stateDim;
                }
            }
        }
    }

    /**
     * Wrapper class to compute jacobian matrices by finite differences for ODE
     *  which do not compute them by themselves.
     */
    private static class MainStateJacobianWrapper implements MainStateJacobianProvider {

        /**
         * Raw ODE without jacobians computation skill to be wrapped into a MainStateJacobianProvider.
         */
        private final FirstOrderDifferentialEquations ode;

        /**
         * Steps for finite difference computation of the jacobian df/dy w.r.t. state.
         */
        private final double[] hY;

        /**
         * Wrap a {@link FirstOrderDifferentialEquations} into a {@link MainStateJacobianProvider}.
         * @param ode original ODE problem, without jacobians computation skill
         * @param hY step sizes to compute the jacobian df/dy
         * @exception DimensionMismatchException if there is a dimension mismatch between
         * the steps array {@code hY} and the equation dimension
         */
        MainStateJacobianWrapper(final FirstOrderDifferentialEquations ode, final double[] hY) throws DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.MainStateJacobianWrapper_434");
            this.ode = ode;
            this.hY = hY.clone();
            if (ROR_not_equals(hY.length, ode.getDimension(), "org.apache.commons.math3.ode.JacobianMatrices.MainStateJacobianWrapper_434", _mut11648, _mut11649, _mut11650, _mut11651, _mut11652)) {
                throw new DimensionMismatchException(ode.getDimension(), hY.length);
            }
        }

        /**
         * {@inheritDoc}
         */
        public int getDimension() {
            return ode.getDimension();
        }

        /**
         * {@inheritDoc}
         */
        public void computeDerivatives(double t, double[] y, double[] yDot) throws MaxCountExceededException, DimensionMismatchException {
            ode.computeDerivatives(t, y, yDot);
        }

        /**
         * {@inheritDoc}
         */
        public void computeMainStateJacobian(double t, double[] y, double[] yDot, double[][] dFdY) throws MaxCountExceededException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456");
            final int n = ode.getDimension();
            final double[] tmpDot = new double[n];
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456", _mut11666, _mut11667, _mut11668, _mut11669, _mut11670); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456");
                final double savedYj = y[j];
                y[j] += hY[j];
                ode.computeDerivatives(t, y, tmpDot);
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456", _mut11661, _mut11662, _mut11663, _mut11664, _mut11665); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456");
                    dFdY[i][j] = AOR_divide((AOR_minus(tmpDot[i], yDot[i], "org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456", _mut11653, _mut11654, _mut11655, _mut11656)), hY[j], "org.apache.commons.math3.ode.JacobianMatrices.computeMainStateJacobian_456", _mut11657, _mut11658, _mut11659, _mut11660);
                }
                y[j] = savedYj;
            }
        }
    }

    /**
     * Special exception for equations mismatch.
     * @since 3.1
     */
    public static class MismatchedEquations extends MathIllegalArgumentException {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 20120902L;

        /**
         * Simple constructor.
         */
        public MismatchedEquations() {
            super(LocalizedFormats.UNMATCHED_ODE_IN_EXPANDED_SET);
        }
    }
}
