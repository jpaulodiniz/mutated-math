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
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for all bracketing <em>Secant</em>-based methods for root-finding
 * (approximating a zero of a univariate real function).
 *
 * <p>Implementation of the {@link RegulaFalsiSolver <em>Regula Falsi</em>} and
 * {@link IllinoisSolver <em>Illinois</em>} methods is based on the
 * following article: M. Dowell and P. Jarratt,
 * <em>A modified regula falsi method for computing the root of an
 * equation</em>, BIT Numerical Mathematics, volume 11, number 2,
 * pages 168-174, Springer, 1971.</p>
 *
 * <p>Implementation of the {@link PegasusSolver <em>Pegasus</em>} method is
 * based on the following article: M. Dowell and P. Jarratt,
 * <em>The "Pegasus" method for computing the root of an equation</em>,
 * BIT Numerical Mathematics, volume 12, number 4, pages 503-508, Springer,
 * 1972.</p>
 *
 * <p>The {@link SecantSolver <em>Secant</em>} method is <em>not</em> a
 * bracketing method, so it is not implemented here. It has a separate
 * implementation.</p>
 *
 * @since 3.0
 */
public abstract class BaseSecantSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {

    @Conditional
    public static boolean _mut99260 = false, _mut99261 = false, _mut99262 = false, _mut99263 = false, _mut99264 = false, _mut99265 = false, _mut99266 = false, _mut99267 = false, _mut99268 = false, _mut99269 = false, _mut99270 = false, _mut99271 = false, _mut99272 = false, _mut99273 = false, _mut99274 = false, _mut99275 = false, _mut99276 = false, _mut99277 = false, _mut99278 = false, _mut99279 = false, _mut99280 = false, _mut99281 = false, _mut99282 = false, _mut99283 = false, _mut99284 = false, _mut99285 = false, _mut99286 = false, _mut99287 = false, _mut99288 = false, _mut99289 = false, _mut99290 = false, _mut99291 = false, _mut99292 = false, _mut99293 = false, _mut99294 = false, _mut99295 = false, _mut99296 = false, _mut99297 = false, _mut99298 = false, _mut99299 = false, _mut99300 = false, _mut99301 = false, _mut99302 = false, _mut99303 = false, _mut99304 = false, _mut99305 = false, _mut99306 = false, _mut99307 = false, _mut99308 = false, _mut99309 = false, _mut99310 = false, _mut99311 = false, _mut99312 = false, _mut99313 = false, _mut99314 = false, _mut99315 = false, _mut99316 = false, _mut99317 = false, _mut99318 = false, _mut99319 = false, _mut99320 = false, _mut99321 = false, _mut99322 = false, _mut99323 = false, _mut99324 = false, _mut99325 = false, _mut99326 = false, _mut99327 = false, _mut99328 = false, _mut99329 = false, _mut99330 = false, _mut99331 = false, _mut99332 = false, _mut99333 = false, _mut99334 = false, _mut99335 = false, _mut99336 = false, _mut99337 = false, _mut99338 = false, _mut99339 = false, _mut99340 = false, _mut99341 = false, _mut99342 = false, _mut99343 = false, _mut99344 = false, _mut99345 = false, _mut99346 = false, _mut99347 = false, _mut99348 = false, _mut99349 = false, _mut99350 = false, _mut99351 = false, _mut99352 = false, _mut99353 = false, _mut99354 = false, _mut99355 = false, _mut99356 = false, _mut99357 = false, _mut99358 = false, _mut99359 = false, _mut99360 = false, _mut99361 = false, _mut99362 = false, _mut99363 = false, _mut99364 = false, _mut99365 = false, _mut99366 = false;

    /**
     * Default absolute accuracy.
     */
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * The kinds of solutions that the algorithm may accept.
     */
    private AllowedSolution allowed;

    /**
     * The <em>Secant</em>-based root-finding method to use.
     */
    private final Method method;

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     * @param method <em>Secant</em>-based root-finding method to use.
     */
    protected BaseSecantSolver(final double absoluteAccuracy, final Method method) {
        super(absoluteAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param method <em>Secant</em>-based root-finding method to use.
     */
    protected BaseSecantSolver(final double relativeAccuracy, final double absoluteAccuracy, final Method method) {
        super(relativeAccuracy, absoluteAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Maximum relative error.
     * @param absoluteAccuracy Maximum absolute error.
     * @param functionValueAccuracy Maximum function value error.
     * @param method <em>Secant</em>-based root-finding method to use
     */
    protected BaseSecantSolver(final double relativeAccuracy, final double absoluteAccuracy, final double functionValueAccuracy, final Method method) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    /**
     * {@inheritDoc}
     */
    public double solve(final int maxEval, final UnivariateFunction f, final double min, final double max, final AllowedSolution allowedSolution) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BaseSecantSolver.solve_106");
        return solve(maxEval, f, min, max, AOR_plus(min, AOR_multiply(0.5, (AOR_minus(max, min, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.solve_106", _mut99260, _mut99261, _mut99262, _mut99263)), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.solve_106", _mut99264, _mut99265, _mut99266, _mut99267), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.solve_106", _mut99268, _mut99269, _mut99270, _mut99271), allowedSolution);
    }

    /**
     * {@inheritDoc}
     */
    public double solve(final int maxEval, final UnivariateFunction f, final double min, final double max, final double startValue, final AllowedSolution allowedSolution) {
        this.allowed = allowedSolution;
        return super.solve(maxEval, f, min, max, startValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double solve(final int maxEval, final UnivariateFunction f, final double min, final double max, final double startValue) {
        return solve(maxEval, f, min, max, startValue, AllowedSolution.ANY_SIDE);
    }

    /**
     * {@inheritDoc}
     *
     * @throws ConvergenceException if the algorithm failed due to finite
     * precision.
     */
    @Override
    protected final double doSolve() throws ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133");
        // Get initial solution
        double x0 = getMin();
        double x1 = getMax();
        double f0 = computeObjectiveValue(x0);
        double f1 = computeObjectiveValue(x1);
        // regardless of the allowed solutions.
        if (ROR_equals(f0, 0.0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99272, _mut99273, _mut99274, _mut99275, _mut99276)) {
            return x0;
        }
        if (ROR_equals(f1, 0.0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99277, _mut99278, _mut99279, _mut99280, _mut99281)) {
            return x1;
        }
        // Verify bracketing of initial solution.
        verifyBracketing(x0, x1);
        // Get accuracies.
        final double ftol = getFunctionValueAccuracy();
        final double atol = getAbsoluteAccuracy();
        final double rtol = getRelativeAccuracy();
        // larger than the right bound.
        boolean inverted = false;
        // Keep finding better approximations.
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133");
            // Calculate the next approximation.
            final double x = AOR_minus(x1, (AOR_divide((AOR_multiply(f1, (AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99282, _mut99283, _mut99284, _mut99285)), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99286, _mut99287, _mut99288, _mut99289)), (AOR_minus(f1, f0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99290, _mut99291, _mut99292, _mut99293)), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99294, _mut99295, _mut99296, _mut99297)), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99298, _mut99299, _mut99300, _mut99301);
            final double fx = computeObjectiveValue(x);
            // we can return it regardless of the allowed solutions.
            if (ROR_equals(fx, 0.0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99302, _mut99303, _mut99304, _mut99305, _mut99306)) {
                return x;
            }
            // Update the bounds with the new approximation.
            if (ROR_less(AOR_multiply(f1, fx, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99307, _mut99308, _mut99309, _mut99310), 0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99311, _mut99312, _mut99313, _mut99314, _mut99315)) {
                // the interval.
                x0 = x1;
                f0 = f1;
                inverted = !inverted;
            } else {
                switch(method) {
                    case ILLINOIS:
                        f0 *= 0.5;
                        break;
                    case PEGASUS:
                        f0 *= AOR_divide(f1, (AOR_plus(f1, fx, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99316, _mut99317, _mut99318, _mut99319)), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99320, _mut99321, _mut99322, _mut99323);
                        break;
                    case REGULA_FALSI:
                        // for the maximum number of iterations to be exceeded.
                        if (ROR_equals(x, x1, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99324, _mut99325, _mut99326, _mut99327, _mut99328)) {
                            throw new ConvergenceException();
                        }
                        break;
                    default:
                        // Should never happen.
                        throw new MathInternalError();
                }
            }
            // Update from [x0, x1] to [x0, x].
            x1 = x;
            f1 = fx;
            // the root than we already are.
            if (ROR_less_equals(FastMath.abs(f1), ftol, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99329, _mut99330, _mut99331, _mut99332, _mut99333)) {
                switch(allowed) {
                    case ANY_SIDE:
                        return x1;
                    case LEFT_SIDE:
                        if (inverted) {
                            return x1;
                        }
                        break;
                    case RIGHT_SIDE:
                        if (!inverted) {
                            return x1;
                        }
                        break;
                    case BELOW_SIDE:
                        if (ROR_less_equals(f1, 0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99334, _mut99335, _mut99336, _mut99337, _mut99338)) {
                            return x1;
                        }
                        break;
                    case ABOVE_SIDE:
                        if (ROR_greater_equals(f1, 0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99339, _mut99340, _mut99341, _mut99342, _mut99343)) {
                            return x1;
                        }
                        break;
                    default:
                        throw new MathInternalError();
                }
            }
            // are satisfied with the current approximation.
            if (ROR_less(FastMath.abs(AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99344, _mut99345, _mut99346, _mut99347)), FastMath.max(AOR_multiply(rtol, FastMath.abs(x1), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99348, _mut99349, _mut99350, _mut99351), atol), "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99352, _mut99353, _mut99354, _mut99355, _mut99356)) {
                switch(allowed) {
                    case ANY_SIDE:
                        return x1;
                    case LEFT_SIDE:
                        return inverted ? x1 : x0;
                    case RIGHT_SIDE:
                        return inverted ? x0 : x1;
                    case BELOW_SIDE:
                        return (ROR_less_equals(f1, 0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99357, _mut99358, _mut99359, _mut99360, _mut99361)) ? x1 : x0;
                    case ABOVE_SIDE:
                        return (ROR_greater_equals(f1, 0, "org.apache.commons.math3.analysis.solvers.BaseSecantSolver.doSolve_133", _mut99362, _mut99363, _mut99364, _mut99365, _mut99366)) ? x1 : x0;
                    default:
                        throw new MathInternalError();
                }
            }
        }
    }

    /**
     * <em>Secant</em>-based root-finding methods.
     */
    protected enum Method {

        /**
         * The {@link RegulaFalsiSolver <em>Regula Falsi</em>} or
         * <em>False Position</em> method.
         */
        REGULA_FALSI,
        /**
         * The {@link IllinoisSolver <em>Illinois</em>} method.
         */
        ILLINOIS,
        /**
         * The {@link PegasusSolver <em>Pegasus</em>} method.
         */
        PEGASUS
    }
}
