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

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements <a href="http://mathworld.wolfram.com/NewtonsMethod.html">
 * Newton's Method</a> for finding zeros of real univariate functions.
 * <p>
 * The function should be continuous but not necessarily smooth.</p>
 *
 * @deprecated as of 3.1, replaced by {@link NewtonRaphsonSolver}
 */
@Deprecated
public class NewtonSolver extends AbstractDifferentiableUnivariateSolver {

    @Conditional
    public static boolean _mut100623 = false, _mut100624 = false, _mut100625 = false, _mut100626 = false, _mut100627 = false, _mut100628 = false, _mut100629 = false, _mut100630 = false, _mut100631 = false, _mut100632 = false, _mut100633 = false, _mut100634 = false, _mut100635 = false, _mut100636 = false, _mut100637 = false, _mut100638 = false, _mut100639 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver.
     */
    public NewtonSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public NewtonSolver(double absoluteAccuracy) {
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
    public double solve(int maxEval, final DifferentiableUnivariateFunction f, final double min, final double max) throws TooManyEvaluationsException {
        return super.solve(maxEval, f, UnivariateSolverUtils.midpoint(min, max));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75");
        final double startValue = getStartValue();
        final double absoluteAccuracy = getAbsoluteAccuracy();
        double x0 = startValue;
        double x1;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75");
            x1 = AOR_minus(x0, (AOR_divide(computeObjectiveValue(x0), computeDerivativeObjectiveValue(x0), "org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75", _mut100623, _mut100624, _mut100625, _mut100626)), "org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75", _mut100627, _mut100628, _mut100629, _mut100630);
            if (ROR_less_equals(FastMath.abs(AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75", _mut100631, _mut100632, _mut100633, _mut100634)), absoluteAccuracy, "org.apache.commons.math3.analysis.solvers.NewtonSolver.doSolve_75", _mut100635, _mut100636, _mut100637, _mut100638, _mut100639)) {
                return x1;
            }
            x0 = x1;
        }
    }
}
