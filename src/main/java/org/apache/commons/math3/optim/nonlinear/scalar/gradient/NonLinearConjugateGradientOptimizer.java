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
package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Non-linear conjugate gradient optimizer.
 * <br/>
 * This class supports both the Fletcher-Reeves and the Polak-Ribière
 * update formulas for the conjugate search directions.
 * It also supports optional preconditioning.
 * <br/>
 * Constraints are not supported: the call to
 * {@link #optimize(OptimizationData[]) optimize} will throw
 * {@link MathUnsupportedOperationException} if bounds are passed to it.
 *
 * @since 2.0
 */
public class NonLinearConjugateGradientOptimizer extends GradientMultivariateOptimizer {

    @Conditional
    public static boolean _mut60052 = false, _mut60053 = false, _mut60054 = false, _mut60055 = false, _mut60056 = false, _mut60057 = false, _mut60058 = false, _mut60059 = false, _mut60060 = false, _mut60061 = false, _mut60062 = false, _mut60063 = false, _mut60064 = false, _mut60065 = false, _mut60066 = false, _mut60067 = false, _mut60068 = false, _mut60069 = false, _mut60070 = false, _mut60071 = false, _mut60072 = false, _mut60073 = false, _mut60074 = false, _mut60075 = false, _mut60076 = false, _mut60077 = false, _mut60078 = false, _mut60079 = false, _mut60080 = false, _mut60081 = false, _mut60082 = false, _mut60083 = false, _mut60084 = false, _mut60085 = false, _mut60086 = false, _mut60087 = false, _mut60088 = false, _mut60089 = false, _mut60090 = false, _mut60091 = false, _mut60092 = false, _mut60093 = false, _mut60094 = false, _mut60095 = false, _mut60096 = false, _mut60097 = false, _mut60098 = false, _mut60099 = false, _mut60100 = false, _mut60101 = false, _mut60102 = false, _mut60103 = false, _mut60104 = false, _mut60105 = false, _mut60106 = false, _mut60107 = false, _mut60108 = false, _mut60109 = false, _mut60110 = false, _mut60111 = false, _mut60112 = false, _mut60113 = false, _mut60114 = false, _mut60115 = false, _mut60116 = false, _mut60117 = false, _mut60118 = false, _mut60119 = false, _mut60120 = false, _mut60121 = false, _mut60122 = false, _mut60123 = false, _mut60124 = false, _mut60125 = false, _mut60126 = false, _mut60127 = false, _mut60128 = false, _mut60129 = false, _mut60130 = false, _mut60131 = false, _mut60132 = false, _mut60133 = false, _mut60134 = false, _mut60135 = false, _mut60136 = false, _mut60137 = false, _mut60138 = false, _mut60139 = false;

    /**
     * Update formula for the beta parameter.
     */
    private final Formula updateFormula;

    /**
     * Preconditioner (may be null).
     */
    private final Preconditioner preconditioner;

    /**
     * Line search algorithm.
     */
    private final LineSearch line;

    /**
     * Available choices of update formulas for the updating the parameter
     * that is used to compute the successive conjugate search directions.
     * For non-linear conjugate gradients, there are
     * two formulas:
     * <ul>
     *   <li>Fletcher-Reeves formula</li>
     *   <li>Polak-Ribière formula</li>
     * </ul>
     *
     * On the one hand, the Fletcher-Reeves formula is guaranteed to converge
     * if the start point is close enough of the optimum whether the
     * Polak-Ribière formula may not converge in rare cases. On the
     * other hand, the Polak-Ribière formula is often faster when it
     * does converge. Polak-Ribière is often used.
     *
     * @since 2.0
     */
    public enum Formula {

        /**
         * Fletcher-Reeves formula.
         */
        FLETCHER_REEVES,
        /**
         * Polak-Ribière formula.
         */
        POLAK_RIBIERE
    }

    /**
     * The initial step is a factor with respect to the search direction
     * (which itself is roughly related to the gradient of the function).
     * <br/>
     * It is used to find an interval that brackets the optimum in line
     * search.
     *
     * @since 3.1
     * @deprecated As of v3.3, this class is not used anymore.
     * This setting is replaced by the {@code initialBracketingRange}
     * argument to the new constructors.
     */
    @Deprecated
    public static class BracketingStep implements OptimizationData {

        /**
         * Initial step.
         */
        private final double initialStep;

        /**
         * @param step Initial step for the bracket search.
         */
        public BracketingStep(double step) {
            initialStep = step;
        }

        /**
         * Gets the initial step.
         *
         * @return the initial step.
         */
        public double getBracketingStep() {
            return initialStep;
        }
    }

    /**
     * Constructor with default tolerances for the line search (1e-8) and
     * {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link Formula#FLETCHER_REEVES} or
     * {@link Formula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     */
    public NonLinearConjugateGradientOptimizer(final Formula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, 1e-8, 1e-8, 1e-8, new IdentityPreconditioner());
    }

    /**
     * Constructor with default {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link Formula#FLETCHER_REEVES} or
     * {@link Formula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param lineSearchSolver Solver to use during line search.
     * @deprecated as of 3.3. Please use
     * {@link #NonLinearConjugateGradientOptimizer(Formula,ConvergenceChecker,double,double,double)} instead.
     */
    @Deprecated
    public NonLinearConjugateGradientOptimizer(final Formula updateFormula, ConvergenceChecker<PointValuePair> checker, final UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    /**
     * Constructor with default {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link Formula#FLETCHER_REEVES} or
     * {@link Formula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param relativeTolerance Relative threshold for line search.
     * @param absoluteTolerance Absolute threshold for line search.
     * @param initialBracketingRange Extent of the initial interval used to
     * find an interval that brackets the optimum in order to perform the
     * line search.
     *
     * @see LineSearch#LineSearch(MultivariateOptimizer,double,double,double)
     * @since 3.3
     */
    public NonLinearConjugateGradientOptimizer(final Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange) {
        this(updateFormula, checker, relativeTolerance, absoluteTolerance, initialBracketingRange, new IdentityPreconditioner());
    }

    /**
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link Formula#FLETCHER_REEVES} or
     * {@link Formula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param lineSearchSolver Solver to use during line search.
     * @param preconditioner Preconditioner.
     * @deprecated as of 3.3. Please use
     * {@link #NonLinearConjugateGradientOptimizer(Formula,ConvergenceChecker,double,double,double,Preconditioner)} instead.
     */
    @Deprecated
    public NonLinearConjugateGradientOptimizer(final Formula updateFormula, ConvergenceChecker<PointValuePair> checker, final UnivariateSolver lineSearchSolver, final Preconditioner preconditioner) {
        this(updateFormula, checker, lineSearchSolver.getRelativeAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), preconditioner);
    }

    /**
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link Formula#FLETCHER_REEVES} or
     * {@link Formula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param preconditioner Preconditioner.
     * @param relativeTolerance Relative threshold for line search.
     * @param absoluteTolerance Absolute threshold for line search.
     * @param initialBracketingRange Extent of the initial interval used to
     * find an interval that brackets the optimum in order to perform the
     * line search.
     *
     * @see LineSearch#LineSearch(MultivariateOptimizer,double,double,double)
     * @since 3.3
     */
    public NonLinearConjugateGradientOptimizer(final Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange, final Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        this.preconditioner = preconditioner;
        line = new LineSearch(this, relativeTolerance, absoluteTolerance, initialBracketingRange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
        // Set up base class and perform computation.
        return super.optimize(optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
        final ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
        final double[] point = getStartPoint();
        final GoalType goal = getGoalType();
        final int n = point.length;
        double[] r = computeObjectiveGradient(point);
        if (goal == GoalType.MINIMIZE) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60052, _mut60053, _mut60054, _mut60055, _mut60056); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                r[i] = -r[i];
            }
        }
        // Initial search direction.
        double[] steepestDescent = preconditioner.precondition(point, r);
        double[] searchDirection = steepestDescent.clone();
        double delta = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60061, _mut60062, _mut60063, _mut60064, _mut60065); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
            delta += AOR_multiply(r[i], searchDirection[i], "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60057, _mut60058, _mut60059, _mut60060);
        }
        PointValuePair current = null;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
            incrementIterationCount();
            final double objective = computeObjectiveValue(point);
            PointValuePair previous = current;
            current = new PointValuePair(point, objective);
            if ((_mut60066 ? (previous != null || checker.converged(getIterations(), previous, current)) : (previous != null && checker.converged(getIterations(), previous, current)))) {
                // We have found an optimum.
                return current;
            }
            final double step = line.search(point, searchDirection).getPoint();
            // Validate new point.
            for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60071, _mut60072, _mut60073, _mut60074, _mut60075); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                point[i] += AOR_multiply(step, searchDirection[i], "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60067, _mut60068, _mut60069, _mut60070);
            }
            r = computeObjectiveGradient(point);
            if (goal == GoalType.MINIMIZE) {
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60076, _mut60077, _mut60078, _mut60079, _mut60080); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                    r[i] = -r[i];
                }
            }
            // Compute beta.
            final double deltaOld = delta;
            final double[] newSteepestDescent = preconditioner.precondition(point, r);
            delta = 0;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60085, _mut60086, _mut60087, _mut60088, _mut60089); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                delta += AOR_multiply(r[i], newSteepestDescent[i], "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60081, _mut60082, _mut60083, _mut60084);
            }
            final double beta;
            switch(updateFormula) {
                case FLETCHER_REEVES:
                    beta = AOR_divide(delta, deltaOld, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60090, _mut60091, _mut60092, _mut60093);
                    break;
                case POLAK_RIBIERE:
                    double deltaMid = 0;
                    for (int i = 0; ROR_less(i, r.length, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60098, _mut60099, _mut60100, _mut60101, _mut60102); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                        deltaMid += AOR_multiply(r[i], steepestDescent[i], "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60094, _mut60095, _mut60096, _mut60097);
                    }
                    beta = AOR_divide((AOR_minus(delta, deltaMid, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60103, _mut60104, _mut60105, _mut60106)), deltaOld, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60107, _mut60108, _mut60109, _mut60110);
                    break;
                default:
                    // Should never happen.
                    throw new MathInternalError();
            }
            steepestDescent = newSteepestDescent;
            // Compute conjugate search direction.
            if ((_mut60125 ? (ROR_equals(AOR_remainder(getIterations(), n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60111, _mut60112, _mut60113, _mut60114), 0, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60115, _mut60116, _mut60117, _mut60118, _mut60119) && ROR_less(beta, 0, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60120, _mut60121, _mut60122, _mut60123, _mut60124)) : (ROR_equals(AOR_remainder(getIterations(), n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60111, _mut60112, _mut60113, _mut60114), 0, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60115, _mut60116, _mut60117, _mut60118, _mut60119) || ROR_less(beta, 0, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60120, _mut60121, _mut60122, _mut60123, _mut60124)))) {
                // Break conjugation: reset search direction.
                searchDirection = steepestDescent.clone();
            } else {
                // Compute new conjugate search direction.
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60134, _mut60135, _mut60136, _mut60137, _mut60138); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248");
                    searchDirection[i] = AOR_plus(steepestDescent[i], AOR_multiply(beta, searchDirection[i], "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60126, _mut60127, _mut60128, _mut60129), "org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.doOptimize_248", _mut60130, _mut60131, _mut60132, _mut60133);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // Allow base class to register its own data.
        super.parseOptimizationData(optData);
        checkParameters();
    }

    /**
     * Default identity preconditioner.
     */
    public static class IdentityPreconditioner implements Preconditioner {

        /**
         * {@inheritDoc}
         */
        public double[] precondition(double[] variables, double[] r) {
            return r.clone();
        }
    }

    /**
     * @throws MathUnsupportedOperationException if bounds were passed to the
     * {@link #optimize(OptimizationData[]) optimize} method.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.checkParameters_409");
        if ((_mut60139 ? (getLowerBound() != null && getUpperBound() != null) : (getLowerBound() != null || getUpperBound() != null))) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT);
        }
    }
}
