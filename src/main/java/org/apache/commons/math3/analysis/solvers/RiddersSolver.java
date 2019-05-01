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
 * Implements the <a href="http://mathworld.wolfram.com/RiddersMethod.html">
 * Ridders' Method</a> for root finding of real univariate functions. For
 * reference, see C. Ridders, <i>A new algorithm for computing a single root
 * of a real continuous function </i>, IEEE Transactions on Circuits and
 * Systems, 26 (1979), 979 - 980.
 * <p>
 * The function should be continuous but not necessarily smooth.</p>
 *
 * @since 1.2
 */
public class RiddersSolver extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut99022 = false, _mut99023 = false, _mut99024 = false, _mut99025 = false, _mut99026 = false, _mut99027 = false, _mut99028 = false, _mut99029 = false, _mut99030 = false, _mut99031 = false, _mut99032 = false, _mut99033 = false, _mut99034 = false, _mut99035 = false, _mut99036 = false, _mut99037 = false, _mut99038 = false, _mut99039 = false, _mut99040 = false, _mut99041 = false, _mut99042 = false, _mut99043 = false, _mut99044 = false, _mut99045 = false, _mut99046 = false, _mut99047 = false, _mut99048 = false, _mut99049 = false, _mut99050 = false, _mut99051 = false, _mut99052 = false, _mut99053 = false, _mut99054 = false, _mut99055 = false, _mut99056 = false, _mut99057 = false, _mut99058 = false, _mut99059 = false, _mut99060 = false, _mut99061 = false, _mut99062 = false, _mut99063 = false, _mut99064 = false, _mut99065 = false, _mut99066 = false, _mut99067 = false, _mut99068 = false, _mut99069 = false, _mut99070 = false, _mut99071 = false, _mut99072 = false, _mut99073 = false, _mut99074 = false, _mut99075 = false, _mut99076 = false, _mut99077 = false, _mut99078 = false, _mut99079 = false, _mut99080 = false, _mut99081 = false, _mut99082 = false, _mut99083 = false, _mut99084 = false, _mut99085 = false, _mut99086 = false, _mut99087 = false, _mut99088 = false, _mut99089 = false, _mut99090 = false, _mut99091 = false, _mut99092 = false, _mut99093 = false, _mut99094 = false, _mut99095 = false, _mut99096 = false, _mut99097 = false, _mut99098 = false, _mut99099 = false, _mut99100 = false, _mut99101 = false, _mut99102 = false, _mut99103 = false, _mut99104 = false, _mut99105 = false, _mut99106 = false, _mut99107 = false, _mut99108 = false, _mut99109 = false, _mut99110 = false, _mut99111 = false, _mut99112 = false, _mut99113 = false, _mut99114 = false, _mut99115 = false, _mut99116 = false, _mut99117 = false, _mut99118 = false, _mut99119 = false, _mut99120 = false, _mut99121 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public RiddersSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public RiddersSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public RiddersSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66");
        double min = getMin();
        double max = getMax();
        // x is the new root approximation and an endpoint of the new interval
        double x1 = min;
        double y1 = computeObjectiveValue(x1);
        double x2 = max;
        double y2 = computeObjectiveValue(x2);
        // check for zeros before verifying bracketing
        if (ROR_equals(y1, 0, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99022, _mut99023, _mut99024, _mut99025, _mut99026)) {
            return min;
        }
        if (ROR_equals(y2, 0, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99027, _mut99028, _mut99029, _mut99030, _mut99031)) {
            return max;
        }
        verifyBracketing(min, max);
        final double absoluteAccuracy = getAbsoluteAccuracy();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        final double relativeAccuracy = getRelativeAccuracy();
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66");
            // calculate the new root approximation
            final double x3 = AOR_multiply(0.5, (AOR_plus(x1, x2, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99032, _mut99033, _mut99034, _mut99035)), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99036, _mut99037, _mut99038, _mut99039);
            final double y3 = computeObjectiveValue(x3);
            if (ROR_less_equals(FastMath.abs(y3), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99040, _mut99041, _mut99042, _mut99043, _mut99044)) {
                return x3;
            }
            // delta > 1 due to bracketing
            final double delta = AOR_minus(1, AOR_divide((AOR_multiply(y1, y2, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99045, _mut99046, _mut99047, _mut99048)), (AOR_multiply(y3, y3, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99049, _mut99050, _mut99051, _mut99052)), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99053, _mut99054, _mut99055, _mut99056), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99057, _mut99058, _mut99059, _mut99060);
            final double correction = AOR_divide(AOR_multiply((AOR_multiply(FastMath.signum(y2), FastMath.signum(y3), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99061, _mut99062, _mut99063, _mut99064)), (AOR_minus(x3, x1, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99065, _mut99066, _mut99067, _mut99068)), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99069, _mut99070, _mut99071, _mut99072), FastMath.sqrt(delta), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99073, _mut99074, _mut99075, _mut99076);
            // correction != 0
            final double x = AOR_minus(x3, correction, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99077, _mut99078, _mut99079, _mut99080);
            final double y = computeObjectiveValue(x);
            // check for convergence
            final double tolerance = FastMath.max(AOR_multiply(relativeAccuracy, FastMath.abs(x), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99081, _mut99082, _mut99083, _mut99084), absoluteAccuracy);
            if (ROR_less_equals(FastMath.abs(AOR_minus(x, oldx, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99085, _mut99086, _mut99087, _mut99088)), tolerance, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99089, _mut99090, _mut99091, _mut99092, _mut99093)) {
                return x;
            }
            if (ROR_less_equals(FastMath.abs(y), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99094, _mut99095, _mut99096, _mut99097, _mut99098)) {
                return x;
            }
            // Ridders' method guarantees x1 < x < x2
            if (ROR_greater(correction, 0.0, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99099, _mut99100, _mut99101, _mut99102, _mut99103)) {
                // x1 < x < x3
                if (ROR_equals(AOR_plus(FastMath.signum(y1), FastMath.signum(y), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99113, _mut99114, _mut99115, _mut99116), 0.0, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99117, _mut99118, _mut99119, _mut99120, _mut99121)) {
                    x2 = x;
                    y2 = y;
                } else {
                    x1 = x;
                    x2 = x3;
                    y1 = y;
                    y2 = y3;
                }
            } else {
                // x3 < x < x2
                if (ROR_equals(AOR_plus(FastMath.signum(y2), FastMath.signum(y), "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99104, _mut99105, _mut99106, _mut99107), 0.0, "org.apache.commons.math3.analysis.solvers.RiddersSolver.doSolve_66", _mut99108, _mut99109, _mut99110, _mut99111, _mut99112)) {
                    x1 = x;
                    y1 = y;
                } else {
                    x1 = x3;
                    x2 = x;
                    y1 = y3;
                    y2 = y;
                }
            }
            oldx = x;
        }
    }
}
