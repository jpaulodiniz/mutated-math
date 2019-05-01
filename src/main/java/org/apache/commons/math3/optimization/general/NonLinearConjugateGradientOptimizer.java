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
package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Non-linear conjugate gradient optimizer.
 * <p>
 * This class supports both the Fletcher-Reeves and the Polak-Ribi&egrave;re
 * update formulas for the conjugate search directions. It also supports
 * optional preconditioning.
 * </p>
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {

    @Conditional
    public static boolean _mut72126 = false, _mut72127 = false, _mut72128 = false, _mut72129 = false, _mut72130 = false, _mut72131 = false, _mut72132 = false, _mut72133 = false, _mut72134 = false, _mut72135 = false, _mut72136 = false, _mut72137 = false, _mut72138 = false, _mut72139 = false, _mut72140 = false, _mut72141 = false, _mut72142 = false, _mut72143 = false, _mut72144 = false, _mut72145 = false, _mut72146 = false, _mut72147 = false, _mut72148 = false, _mut72149 = false, _mut72150 = false, _mut72151 = false, _mut72152 = false, _mut72153 = false, _mut72154 = false, _mut72155 = false, _mut72156 = false, _mut72157 = false, _mut72158 = false, _mut72159 = false, _mut72160 = false, _mut72161 = false, _mut72162 = false, _mut72163 = false, _mut72164 = false, _mut72165 = false, _mut72166 = false, _mut72167 = false, _mut72168 = false, _mut72169 = false, _mut72170 = false, _mut72171 = false, _mut72172 = false, _mut72173 = false, _mut72174 = false, _mut72175 = false, _mut72176 = false, _mut72177 = false, _mut72178 = false, _mut72179 = false, _mut72180 = false, _mut72181 = false, _mut72182 = false, _mut72183 = false, _mut72184 = false, _mut72185 = false, _mut72186 = false, _mut72187 = false, _mut72188 = false, _mut72189 = false, _mut72190 = false, _mut72191 = false, _mut72192 = false, _mut72193 = false, _mut72194 = false, _mut72195 = false, _mut72196 = false, _mut72197 = false, _mut72198 = false, _mut72199 = false, _mut72200 = false, _mut72201 = false, _mut72202 = false, _mut72203 = false, _mut72204 = false, _mut72205 = false, _mut72206 = false, _mut72207 = false, _mut72208 = false, _mut72209 = false, _mut72210 = false, _mut72211 = false, _mut72212 = false, _mut72213 = false, _mut72214 = false, _mut72215 = false, _mut72216 = false, _mut72217 = false, _mut72218 = false, _mut72219 = false, _mut72220 = false, _mut72221 = false, _mut72222 = false, _mut72223 = false, _mut72224 = false, _mut72225 = false, _mut72226 = false, _mut72227 = false, _mut72228 = false, _mut72229 = false, _mut72230 = false, _mut72231 = false, _mut72232 = false, _mut72233 = false, _mut72234 = false, _mut72235 = false, _mut72236 = false, _mut72237 = false, _mut72238 = false, _mut72239 = false, _mut72240 = false, _mut72241 = false, _mut72242 = false, _mut72243 = false, _mut72244 = false, _mut72245 = false, _mut72246 = false, _mut72247 = false, _mut72248 = false, _mut72249 = false, _mut72250 = false, _mut72251 = false, _mut72252 = false, _mut72253 = false;

    /**
     * Update formula for the beta parameter.
     */
    private final ConjugateGradientFormula updateFormula;

    /**
     * Preconditioner (may be null).
     */
    private final Preconditioner preconditioner;

    /**
     * solver to use in the line search (may be null).
     */
    private final UnivariateSolver solver;

    /**
     * Initial step used to bracket the optimum in line search.
     */
    private double initialStep;

    /**
     * Current point.
     */
    private double[] point;

    /**
     * Constructor with default {@link SimpleValueChecker checker},
     * {@link BrentSolver line search solver} and
     * {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link ConjugateGradientFormula#FLETCHER_REEVES} or {@link
     * ConjugateGradientFormula#POLAK_RIBIERE}.
     * @deprecated See {@link SimpleValueChecker#SimpleValueChecker()}
     */
    @Deprecated
    public NonLinearConjugateGradientOptimizer(final ConjugateGradientFormula updateFormula) {
        this(updateFormula, new SimpleValueChecker());
    }

    /**
     * Constructor with default {@link BrentSolver line search solver} and
     * {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link ConjugateGradientFormula#FLETCHER_REEVES} or {@link
     * ConjugateGradientFormula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     */
    public NonLinearConjugateGradientOptimizer(final ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, new BrentSolver(), new IdentityPreconditioner());
    }

    /**
     * Constructor with default {@link IdentityPreconditioner preconditioner}.
     *
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link ConjugateGradientFormula#FLETCHER_REEVES} or {@link
     * ConjugateGradientFormula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param lineSearchSolver Solver to use during line search.
     */
    public NonLinearConjugateGradientOptimizer(final ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, final UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    /**
     * @param updateFormula formula to use for updating the &beta; parameter,
     * must be one of {@link ConjugateGradientFormula#FLETCHER_REEVES} or {@link
     * ConjugateGradientFormula#POLAK_RIBIERE}.
     * @param checker Convergence checker.
     * @param lineSearchSolver Solver to use during line search.
     * @param preconditioner Preconditioner.
     */
    public NonLinearConjugateGradientOptimizer(final ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, final UnivariateSolver lineSearchSolver, final Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        solver = lineSearchSolver;
        this.preconditioner = preconditioner;
        initialStep = 1.0;
    }

    /**
     * Set the initial step used to bracket the optimum in line search.
     * <p>
     * The initial step is a factor with respect to the search direction,
     * which itself is roughly related to the gradient of the function
     * </p>
     * @param initialStep initial step used to bracket the optimum in line search,
     * if a non-positive value is used, the initial step is reset to its
     * default value of 1.0
     */
    public void setInitialStep(final double initialStep) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.setInitialStep_139");
        if (ROR_less_equals(initialStep, 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.setInitialStep_139", _mut72126, _mut72127, _mut72128, _mut72129, _mut72130)) {
            this.initialStep = 1.0;
        } else {
            this.initialStep = initialStep;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
        final ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
        point = getStartPoint();
        final GoalType goal = getGoalType();
        final int n = point.length;
        double[] r = computeObjectiveGradient(point);
        if (goal == GoalType.MINIMIZE) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72131, _mut72132, _mut72133, _mut72134, _mut72135); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                r[i] = -r[i];
            }
        }
        // Initial search direction.
        double[] steepestDescent = preconditioner.precondition(point, r);
        double[] searchDirection = steepestDescent.clone();
        double delta = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72140, _mut72141, _mut72142, _mut72143, _mut72144); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
            delta += AOR_multiply(r[i], searchDirection[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72136, _mut72137, _mut72138, _mut72139);
        }
        PointValuePair current = null;
        int iter = 0;
        int maxEval = getMaxEvaluations();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
            ++iter;
            final double objective = computeObjectiveValue(point);
            PointValuePair previous = current;
            current = new PointValuePair(point, objective);
            if ((_mut72145 ? (previous != null || checker.converged(iter, previous, current)) : (previous != null && checker.converged(iter, previous, current)))) {
                // We have found an optimum.
                return current;
            }
            // Find the optimal step in the search direction.
            final UnivariateFunction lsf = new LineSearchFunction(searchDirection);
            final double uB = findUpperBound(lsf, 0, initialStep);
            // unit test (see MATH-439).
            final double step = solver.solve(maxEval, lsf, 0, uB, 1e-15);
            // Subtract used up evaluations.
            maxEval -= solver.getEvaluations();
            // Validate new point.
            for (int i = 0; ROR_less(i, point.length, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72150, _mut72151, _mut72152, _mut72153, _mut72154); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                point[i] += AOR_multiply(step, searchDirection[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72146, _mut72147, _mut72148, _mut72149);
            }
            r = computeObjectiveGradient(point);
            if (goal == GoalType.MINIMIZE) {
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72155, _mut72156, _mut72157, _mut72158, _mut72159); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                    r[i] = -r[i];
                }
            }
            // Compute beta.
            final double deltaOld = delta;
            final double[] newSteepestDescent = preconditioner.precondition(point, r);
            delta = 0;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72164, _mut72165, _mut72166, _mut72167, _mut72168); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                delta += AOR_multiply(r[i], newSteepestDescent[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72160, _mut72161, _mut72162, _mut72163);
            }
            final double beta;
            if (updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                beta = AOR_divide(delta, deltaOld, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72186, _mut72187, _mut72188, _mut72189);
            } else {
                double deltaMid = 0;
                for (int i = 0; ROR_less(i, r.length, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72173, _mut72174, _mut72175, _mut72176, _mut72177); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                    deltaMid += AOR_multiply(r[i], steepestDescent[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72169, _mut72170, _mut72171, _mut72172);
                }
                beta = AOR_divide((AOR_minus(delta, deltaMid, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72178, _mut72179, _mut72180, _mut72181)), deltaOld, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72182, _mut72183, _mut72184, _mut72185);
            }
            steepestDescent = newSteepestDescent;
            // Compute conjugate search direction.
            if ((_mut72204 ? (ROR_equals(AOR_remainder(iter, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72190, _mut72191, _mut72192, _mut72193), 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72194, _mut72195, _mut72196, _mut72197, _mut72198) && ROR_less(beta, 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72199, _mut72200, _mut72201, _mut72202, _mut72203)) : (ROR_equals(AOR_remainder(iter, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72190, _mut72191, _mut72192, _mut72193), 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72194, _mut72195, _mut72196, _mut72197, _mut72198) || ROR_less(beta, 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72199, _mut72200, _mut72201, _mut72202, _mut72203)))) {
                // Break conjugation: reset search direction.
                searchDirection = steepestDescent.clone();
            } else {
                // Compute new conjugate search direction.
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72213, _mut72214, _mut72215, _mut72216, _mut72217); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148");
                    searchDirection[i] = AOR_plus(steepestDescent[i], AOR_multiply(beta, searchDirection[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72205, _mut72206, _mut72207, _mut72208), "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.doOptimize_148", _mut72209, _mut72210, _mut72211, _mut72212);
                }
            }
        }
    }

    /**
     * Find the upper bound b ensuring bracketing of a root between a and b.
     *
     * @param f function whose root must be bracketed.
     * @param a lower bound of the interval.
     * @param h initial step to try.
     * @return b such that f(a) and f(b) have opposite signs.
     * @throws MathIllegalStateException if no bracket can be found.
     */
    private double findUpperBound(final UnivariateFunction f, final double a, final double h) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248");
        final double yA = f.value(a);
        double yB = yA;
        for (double step = h; ROR_less(step, Double.MAX_VALUE, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248", _mut72231, _mut72232, _mut72233, _mut72234, _mut72235); step *= FastMath.max(2, yA / yB)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248");
            final double b = AOR_plus(a, step, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248", _mut72218, _mut72219, _mut72220, _mut72221);
            yB = f.value(b);
            if (ROR_less_equals(AOR_multiply(yA, yB, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248", _mut72222, _mut72223, _mut72224, _mut72225), 0, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.findUpperBound_248", _mut72226, _mut72227, _mut72228, _mut72229, _mut72230)) {
                return b;
            }
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH);
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
     * Internal class for line search.
     * <p>
     * The function represented by this class is the dot product of
     * the objective function gradient and the search direction. Its
     * value is zero when the gradient is orthogonal to the search
     * direction, i.e. when the objective function value is a local
     * extremum along the search direction.
     * </p>
     */
    private class LineSearchFunction implements UnivariateFunction {

        /**
         * Search direction.
         */
        private final double[] searchDirection;

        /**
         * Simple constructor.
         * @param searchDirection search direction
         */
        LineSearchFunction(final double[] searchDirection) {
            this.searchDirection = searchDirection;
        }

        /**
         * {@inheritDoc}
         */
        public double value(double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292");
            // current point in the search direction
            final double[] shiftedPoint = point.clone();
            for (int i = 0; ROR_less(i, shiftedPoint.length, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292", _mut72240, _mut72241, _mut72242, _mut72243, _mut72244); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292");
                shiftedPoint[i] += AOR_multiply(x, searchDirection[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292", _mut72236, _mut72237, _mut72238, _mut72239);
            }
            // gradient of the objective function
            final double[] gradient = computeObjectiveGradient(shiftedPoint);
            // dot product with the search direction
            double dotProduct = 0;
            for (int i = 0; ROR_less(i, gradient.length, "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292", _mut72249, _mut72250, _mut72251, _mut72252, _mut72253); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292");
                dotProduct += AOR_multiply(gradient[i], searchDirection[i], "org.apache.commons.math3.optimization.general.NonLinearConjugateGradientOptimizer.value_292", _mut72245, _mut72246, _mut72247, _mut72248);
            }
            return dotProduct;
        }
    }
}
