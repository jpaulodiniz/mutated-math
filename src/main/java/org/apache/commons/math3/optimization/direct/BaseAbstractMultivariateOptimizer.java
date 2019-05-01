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
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.SimpleBounds;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for implementing optimizers for multivariate scalar functions.
 * This base class handles the boiler-plate methods associated to thresholds,
 * evaluations counting, initial guess and simple bounds settings.
 *
 * @param <FUNC> Type of the objective function to be optimized.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.2
 */
@Deprecated
public abstract class BaseAbstractMultivariateOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {

    @Conditional
    public static boolean _mut75105 = false, _mut75106 = false, _mut75107 = false, _mut75108 = false, _mut75109 = false, _mut75110 = false, _mut75111 = false, _mut75112 = false, _mut75113 = false, _mut75114 = false, _mut75115 = false, _mut75116 = false, _mut75117 = false, _mut75118 = false, _mut75119 = false, _mut75120 = false, _mut75121 = false, _mut75122 = false, _mut75123 = false, _mut75124 = false, _mut75125 = false, _mut75126 = false, _mut75127 = false, _mut75128 = false, _mut75129 = false, _mut75130 = false, _mut75131 = false, _mut75132 = false, _mut75133 = false, _mut75134 = false, _mut75135 = false, _mut75136 = false, _mut75137 = false, _mut75138 = false, _mut75139 = false, _mut75140 = false, _mut75141 = false, _mut75142 = false, _mut75143 = false, _mut75144 = false;

    /**
     * Evaluations counter.
     */
    protected final Incrementor evaluations = new Incrementor();

    /**
     * Convergence checker.
     */
    private ConvergenceChecker<PointValuePair> checker;

    /**
     * Type of optimization.
     */
    private GoalType goal;

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
     * Objective function.
     */
    private MultivariateFunction function;

    /**
     * Simple constructor with default settings.
     * The convergence check is set to a {@link SimpleValueChecker}.
     * @deprecated See {@link SimpleValueChecker#SimpleValueChecker()}
     */
    @Deprecated
    protected BaseAbstractMultivariateOptimizer() {
        this(new SimpleValueChecker());
    }

    /**
     * @param checker Convergence checker.
     */
    protected BaseAbstractMultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        this.checker = checker;
    }

    /**
     * {@inheritDoc}
     */
    public int getMaxEvaluations() {
        return evaluations.getMaximalCount();
    }

    /**
     * {@inheritDoc}
     */
    public int getEvaluations() {
        return evaluations.getCount();
    }

    /**
     * {@inheritDoc}
     */
    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return checker;
    }

    /**
     * Compute the objective function value.
     *
     * @param point Point at which the objective function must be evaluated.
     * @return the objective function value at the specified point.
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     */
    protected double computeObjectiveValue(double[] point) {
        try {
            evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return function.value(point);
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated As of 3.1. Please use
     * {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])}
     * instead.
     */
    @Deprecated
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
        return optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
    }

    /**
     * Optimize an objective function.
     *
     * @param maxEval Allowed number of evaluations of the objective function.
     * @param f Objective function.
     * @param goalType Optimization type.
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link InitialGuess}</li>
     *  <li>{@link SimpleBounds}</li>
     * </ul>
     * @return the point/value pair giving the optimal value of the objective
     * function.
     * @since 3.1
     */
    public PointValuePair optimize(int maxEval, FUNC f, GoalType goalType, OptimizationData... optData) {
        return optimizeInternal(maxEval, f, goalType, optData);
    }

    /**
     * Optimize an objective function.
     *
     * @param f Objective function.
     * @param goalType Type of optimization goal: either
     * {@link GoalType#MAXIMIZE} or {@link GoalType#MINIMIZE}.
     * @param startPoint Start point for optimization.
     * @param maxEval Maximum number of function evaluations.
     * @return the point/value pair giving the optimal value for objective
     * function.
     * @throws org.apache.commons.math3.exception.DimensionMismatchException
     * if the start point dimension is wrong.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximal number of evaluations is exceeded.
     * @throws org.apache.commons.math3.exception.NullArgumentException if
     * any argument is {@code null}.
     * @deprecated As of 3.1. Please use
     * {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])}
     * instead.
     */
    @Deprecated
    protected PointValuePair optimizeInternal(int maxEval, FUNC f, GoalType goalType, double[] startPoint) {
        return optimizeInternal(maxEval, f, goalType, new InitialGuess(startPoint));
    }

    /**
     * Optimize an objective function.
     *
     * @param maxEval Allowed number of evaluations of the objective function.
     * @param f Objective function.
     * @param goalType Optimization type.
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link InitialGuess}</li>
     *  <li>{@link SimpleBounds}</li>
     * </ul>
     * @return the point/value pair giving the optimal value of the objective
     * function.
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     * @since 3.1
     */
    protected PointValuePair optimizeInternal(int maxEval, FUNC f, GoalType goalType, OptimizationData... optData) throws TooManyEvaluationsException {
        // Set internal state.
        evaluations.setMaximalCount(maxEval);
        evaluations.resetCount();
        function = f;
        goal = goalType;
        // Retrieve other settings.
        parseOptimizationData(optData);
        // Check input consistency.
        checkParameters();
        // Perform computation.
        return doOptimize();
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
    private void parseOptimizationData(OptimizationData... optData) {
        // not provided in the argument list.
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.parseOptimizationData_218");
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
    }

    /**
     * @return the optimization type.
     */
    public GoalType getGoalType() {
        return goal;
    }

    /**
     * @return the initial guess.
     */
    public double[] getStartPoint() {
        return start == null ? null : start.clone();
    }

    /**
     * @return the lower bounds.
     * @since 3.1
     */
    public double[] getLowerBound() {
        return lowerBound == null ? null : lowerBound.clone();
    }

    /**
     * @return the upper bounds.
     * @since 3.1
     */
    public double[] getUpperBound() {
        return upperBound == null ? null : upperBound.clone();
    }

    /**
     * Perform the bulk of the optimization algorithm.
     *
     * @return the point/value pair giving the optimal value of the
     * objective function.
     */
    protected abstract PointValuePair doOptimize();

    /**
     * Check parameters consistency.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274");
        if (start != null) {
            final int dim = start.length;
            if (lowerBound != null) {
                if (ROR_not_equals(lowerBound.length, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75105, _mut75106, _mut75107, _mut75108, _mut75109)) {
                    throw new DimensionMismatchException(lowerBound.length, dim);
                }
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75115, _mut75116, _mut75117, _mut75118, _mut75119); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274");
                    final double v = start[i];
                    final double lo = lowerBound[i];
                    if (ROR_less(v, lo, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75110, _mut75111, _mut75112, _mut75113, _mut75114)) {
                        throw new NumberIsTooSmallException(v, lo, true);
                    }
                }
            }
            if (upperBound != null) {
                if (ROR_not_equals(upperBound.length, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75120, _mut75121, _mut75122, _mut75123, _mut75124)) {
                    throw new DimensionMismatchException(upperBound.length, dim);
                }
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75130, _mut75131, _mut75132, _mut75133, _mut75134); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274");
                    final double v = start[i];
                    final double hi = upperBound[i];
                    if (ROR_greater(v, hi, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75125, _mut75126, _mut75127, _mut75128, _mut75129)) {
                        throw new NumberIsTooLargeException(v, hi, true);
                    }
                }
            }
            // assumed to be [-inf, +inf].
            if (lowerBound == null) {
                lowerBound = new double[dim];
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75135, _mut75136, _mut75137, _mut75138, _mut75139); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274");
                    lowerBound[i] = Double.NEGATIVE_INFINITY;
                }
            }
            if (upperBound == null) {
                upperBound = new double[dim];
                for (int i = 0; ROR_less(i, dim, "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274", _mut75140, _mut75141, _mut75142, _mut75143, _mut75144); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer.checkParameters_274");
                    upperBound[i] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }
}
