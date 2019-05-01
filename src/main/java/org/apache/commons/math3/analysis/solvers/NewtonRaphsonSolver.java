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

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements <a href="http://mathworld.wolfram.com/NewtonsMethod.html">
 * Newton's Method</a> for finding zeros of real univariate differentiable
 * functions.
 *
 * @since 3.1
 */
public class NewtonRaphsonSolver extends AbstractUnivariateDifferentiableSolver {

    @Conditional
    public static boolean _mut99715 = false, _mut99716 = false, _mut99717 = false, _mut99718 = false, _mut99719 = false, _mut99720 = false, _mut99721 = false, _mut99722 = false, _mut99723 = false, _mut99724 = false, _mut99725 = false, _mut99726 = false, _mut99727 = false, _mut99728 = false, _mut99729 = false, _mut99730 = false, _mut99731 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver.
     */
    public NewtonRaphsonSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public NewtonRaphsonSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Find a zero near the midpoint of {@code min} and {@code max}.
     *
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param maxEval Maximum number of evaluations.
     * @return the value where the function is zero.
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum evaluation count is exceeded.
     * @throws org.apache.commons.math3.exception.NumberIsTooLargeException
     * if {@code min >= max}.
     */
    @Override
    public double solve(int maxEval, final UnivariateDifferentiableFunction f, final double min, final double max) throws TooManyEvaluationsException {
        return super.solve(maxEval, f, UnivariateSolverUtils.midpoint(min, max));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74");
        final double startValue = getStartValue();
        final double absoluteAccuracy = getAbsoluteAccuracy();
        double x0 = startValue;
        double x1;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74");
            final DerivativeStructure y0 = computeObjectiveValueAndDerivative(x0);
            x1 = AOR_minus(x0, (AOR_divide(y0.getValue(), y0.getPartialDerivative(1), "org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74", _mut99715, _mut99716, _mut99717, _mut99718)), "org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74", _mut99719, _mut99720, _mut99721, _mut99722);
            if (ROR_less_equals(FastMath.abs(AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74", _mut99723, _mut99724, _mut99725, _mut99726)), absoluteAccuracy, "org.apache.commons.math3.analysis.solvers.NewtonRaphsonSolver.doSolve_74", _mut99727, _mut99728, _mut99729, _mut99730, _mut99731)) {
                return x1;
            }
            x0 = x1;
        }
    }
}
