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
package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for implementing optimizers for multivariate functions.
 * It contains the boiler-plate code for initial guess and bounds
 * specifications.
 * <em>It is not a "user" class.</em>
 *
 * @param <PAIR> Type of the point/value pair returned by the optimization
 * algorithm.
 *
 * @since 3.1
 */
public abstract class BaseMultivariateOptimizer<PAIR> extends BaseOptimizer<PAIR> {

    @Conditional
    public static boolean _mut66767 = false, _mut66768 = false, _mut66769 = false, _mut66770 = false, _mut66771 = false, _mut66772 = false, _mut66773 = false, _mut66774 = false, _mut66775 = false, _mut66776 = false, _mut66777 = false, _mut66778 = false, _mut66779 = false, _mut66780 = false, _mut66781 = false, _mut66782 = false, _mut66783 = false, _mut66784 = false, _mut66785 = false, _mut66786 = false, _mut66787 = false, _mut66788 = false, _mut66789 = false, _mut66790 = false, _mut66791 = false, _mut66792 = false, _mut66793 = false, _mut66794 = false, _mut66795 = false, _mut66796 = false;

    /**
     * Initial guess.
     */
    private double[] start;

    /**
     * Lower bounds.
     */
    private double[] lowerBound;

    /**
     * Upper bounds.
     */
    private double[] upperBound;

    /**
     * @param checker Convergence checker.
     */
    protected BaseMultivariateOptimizer(ConvergenceChecker<PAIR> checker) {
        super(checker);
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link BaseOptimizer#parseOptimizationData(OptimizationData[]) BaseOptimizer},
     * this method will register the following data:
     * <ul>
     *  <li>{@link InitialGuess}</li>
     *  <li>{@link SimpleBounds}</li>
     * </ul>
     * @return {@inheritDoc}
     */
    @Override
    public PAIR optimize(OptimizationData... optData) {
        // Perform optimization.
        return super.optimize(optData);
    }

    /**
     * Scans the list of (required and optional) optimization data that
     * characterize the problem.
     *
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link InitialGuess}</li>
     *  <li>{@link SimpleBounds}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // Allow base class to register its own data.
        super.parseOptimizationData(optData);
        // not provided in the argument list.
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultivariateOptimizer.parseOptimizationData_78");
            if (data instanceof InitialGuess) {
                start = ((InitialGuess) data).getInitialGuess();
                continue;
            }
            if (data instanceof SimpleBounds) {
                final SimpleBounds bounds = (SimpleBounds) data;
                lowerBound = bounds.getLower();
                upperBound = bounds.getUpper();
                continue;
            }
        }
        // Check input consistency.
        checkParameters();
    }

    /**
     * Gets the initial guess.
     *
     * @return the initial guess, or {@code null} if not set.
     */
    public double[] getStartPoint() {
        return start == null ? null : start.clone();
    }

    /**
     * @return the lower bounds, or {@code null} if not set.
     */
    public double[] getLowerBound() {
        return lowerBound == null ? null : lowerBound.clone();
    }

    /**
     * @return the upper bounds, or {@code null} if not set.
     */
    public double[] getUpperBound() {
        return upperBound == null ? null : upperBound.clone();
    }

    /**
     * Check parameters consistency.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126");
        if (start != null) {
            final int dim = start.length;
            if (lowerBound != null) {
                if (ROR_not_equals(lowerBound.length, dim, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66767, _mut66768, _mut66769, _mut66770, _mut66771)) {
                    throw new DimensionMismatchException(lowerBound.length, dim);
                }
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66777, _mut66778, _mut66779, _mut66780, _mut66781); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126");
                    final double v = start[i];
                    final double lo = lowerBound[i];
                    if (ROR_less(v, lo, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66772, _mut66773, _mut66774, _mut66775, _mut66776)) {
                        throw new NumberIsTooSmallException(v, lo, true);
                    }
                }
            }
            if (upperBound != null) {
                if (ROR_not_equals(upperBound.length, dim, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66782, _mut66783, _mut66784, _mut66785, _mut66786)) {
                    throw new DimensionMismatchException(upperBound.length, dim);
                }
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66792, _mut66793, _mut66794, _mut66795, _mut66796); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126");
                    final double v = start[i];
                    final double hi = upperBound[i];
                    if (ROR_greater(v, hi, "org.apache.commons.math3.optim.BaseMultivariateOptimizer.checkParameters_126", _mut66787, _mut66788, _mut66789, _mut66790, _mut66791)) {
                        throw new NumberIsTooLargeException(v, hi, true);
                    }
                }
            }
        }
    }
}
