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
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <em>Secant</em> method for root-finding (approximating a
 * zero of a univariate real function). The solution that is maintained is
 * not bracketed, and as such convergence is not guaranteed.
 *
 * <p>Implementation based on the following article: M. Dowell and P. Jarratt,
 * <em>A modified regula falsi method for computing the root of an
 * equation</em>, BIT Numerical Mathematics, volume 11, number 2,
 * pages 168-174, Springer, 1971.</p>
 *
 * <p>Note that since release 3.0 this class implements the actual
 * <em>Secant</em> algorithm, and not a modified one. As such, the 3.0 version
 * is not backwards compatible with previous versions. To use an algorithm
 * similar to the pre-3.0 releases, use the
 * {@link IllinoisSolver <em>Illinois</em>} algorithm or the
 * {@link PegasusSolver <em>Pegasus</em>} algorithm.</p>
 */
public class SecantSolver extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut100894 = false, _mut100895 = false, _mut100896 = false, _mut100897 = false, _mut100898 = false, _mut100899 = false, _mut100900 = false, _mut100901 = false, _mut100902 = false, _mut100903 = false, _mut100904 = false, _mut100905 = false, _mut100906 = false, _mut100907 = false, _mut100908 = false, _mut100909 = false, _mut100910 = false, _mut100911 = false, _mut100912 = false, _mut100913 = false, _mut100914 = false, _mut100915 = false, _mut100916 = false, _mut100917 = false, _mut100918 = false, _mut100919 = false, _mut100920 = false, _mut100921 = false, _mut100922 = false, _mut100923 = false, _mut100924 = false, _mut100925 = false, _mut100926 = false, _mut100927 = false, _mut100928 = false, _mut100929 = false, _mut100930 = false, _mut100931 = false, _mut100932 = false, _mut100933 = false, _mut100934 = false, _mut100935 = false, _mut100936 = false, _mut100937 = false, _mut100938 = false, _mut100939 = false, _mut100940 = false, _mut100941 = false, _mut100942 = false, _mut100943 = false, _mut100944 = false, _mut100945 = false, _mut100946 = false;

    /**
     * Default absolute accuracy.
     */
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public SecantSolver() {
        super(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy absolute accuracy
     */
    public SecantSolver(final double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy relative accuracy
     * @param absoluteAccuracy absolute accuracy
     */
    public SecantSolver(final double relativeAccuracy, final double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73");
        // Get initial solution
        double x0 = getMin();
        double x1 = getMax();
        double f0 = computeObjectiveValue(x0);
        double f1 = computeObjectiveValue(x1);
        // regardless of the allowed solutions.
        if (ROR_equals(f0, 0.0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100894, _mut100895, _mut100896, _mut100897, _mut100898)) {
            return x0;
        }
        if (ROR_equals(f1, 0.0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100899, _mut100900, _mut100901, _mut100902, _mut100903)) {
            return x1;
        }
        // Verify bracketing of initial solution.
        verifyBracketing(x0, x1);
        // Get accuracies.
        final double ftol = getFunctionValueAccuracy();
        final double atol = getAbsoluteAccuracy();
        final double rtol = getRelativeAccuracy();
        // Keep finding better approximations.
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73");
            // Calculate the next approximation.
            final double x = AOR_minus(x1, (AOR_divide((AOR_multiply(f1, (AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100904, _mut100905, _mut100906, _mut100907)), "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100908, _mut100909, _mut100910, _mut100911)), (AOR_minus(f1, f0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100912, _mut100913, _mut100914, _mut100915)), "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100916, _mut100917, _mut100918, _mut100919)), "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100920, _mut100921, _mut100922, _mut100923);
            final double fx = computeObjectiveValue(x);
            // we can return it regardless of the allowed solutions.
            if (ROR_equals(fx, 0.0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100924, _mut100925, _mut100926, _mut100927, _mut100928)) {
                return x;
            }
            // Update the bounds with the new approximation.
            x0 = x1;
            f0 = f1;
            x1 = x;
            f1 = fx;
            // the root than we already are.
            if (ROR_less_equals(FastMath.abs(f1), ftol, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100929, _mut100930, _mut100931, _mut100932, _mut100933)) {
                return x1;
            }
            // are satisfied with the current approximation.
            if (ROR_less(FastMath.abs(AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100934, _mut100935, _mut100936, _mut100937)), FastMath.max(AOR_multiply(rtol, FastMath.abs(x1), "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100938, _mut100939, _mut100940, _mut100941), atol), "org.apache.commons.math3.analysis.solvers.SecantSolver.doSolve_73", _mut100942, _mut100943, _mut100944, _mut100945, _mut100946)) {
                return x1;
            }
        }
    }
}
