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
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://mathworld.wolfram.com/Bisection.html">
 * bisection algorithm</a> for finding zeros of univariate real functions.
 * <p>
 * The function should be continuous but not necessarily smooth.</p>
 */
public class BisectionSolver extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut100876 = false, _mut100877 = false, _mut100878 = false, _mut100879 = false, _mut100880 = false, _mut100881 = false, _mut100882 = false, _mut100883 = false, _mut100884 = false, _mut100885 = false, _mut100886 = false, _mut100887 = false, _mut100888 = false, _mut100889 = false, _mut100890 = false, _mut100891 = false, _mut100892 = false, _mut100893 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public BisectionSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public BisectionSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public BisectionSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61");
        double min = getMin();
        double max = getMax();
        verifyInterval(min, max);
        final double absoluteAccuracy = getAbsoluteAccuracy();
        double m;
        double fm;
        double fmin;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61");
            m = UnivariateSolverUtils.midpoint(min, max);
            fmin = computeObjectiveValue(min);
            fm = computeObjectiveValue(m);
            if (ROR_greater(AOR_multiply(fm, fmin, "org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61", _mut100876, _mut100877, _mut100878, _mut100879), 0, "org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61", _mut100880, _mut100881, _mut100882, _mut100883, _mut100884)) {
                // max and m bracket the root.
                min = m;
            } else {
                // min and m bracket the root.
                max = m;
            }
            if (ROR_less_equals(FastMath.abs(AOR_minus(max, min, "org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61", _mut100885, _mut100886, _mut100887, _mut100888)), absoluteAccuracy, "org.apache.commons.math3.analysis.solvers.BisectionSolver.doSolve_61", _mut100889, _mut100890, _mut100891, _mut100892, _mut100893)) {
                m = UnivariateSolverUtils.midpoint(min, max);
                return m;
            }
        }
    }
}
