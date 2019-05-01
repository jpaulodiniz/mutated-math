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
package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://mathworld.wolfram.com/TrapezoidalRule.html">
 * Trapezoid Rule</a> for integration of real univariate functions. For
 * reference, see <b>Introduction to Numerical Analysis</b>, ISBN 038795452X,
 * chapter 3.
 * <p>
 * The function should be integrable.</p>
 *
 * @since 1.2
 */
public class TrapezoidIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut102113 = false, _mut102114 = false, _mut102115 = false, _mut102116 = false, _mut102117 = false, _mut102118 = false, _mut102119 = false, _mut102120 = false, _mut102121 = false, _mut102122 = false, _mut102123 = false, _mut102124 = false, _mut102125 = false, _mut102126 = false, _mut102127 = false, _mut102128 = false, _mut102129 = false, _mut102130 = false, _mut102131 = false, _mut102132 = false, _mut102133 = false, _mut102134 = false, _mut102135 = false, _mut102136 = false, _mut102137 = false, _mut102138 = false, _mut102139 = false, _mut102140 = false, _mut102141 = false, _mut102142 = false, _mut102143 = false, _mut102144 = false, _mut102145 = false, _mut102146 = false, _mut102147 = false, _mut102148 = false, _mut102149 = false, _mut102150 = false, _mut102151 = false, _mut102152 = false, _mut102153 = false, _mut102154 = false, _mut102155 = false, _mut102156 = false, _mut102157 = false, _mut102158 = false, _mut102159 = false, _mut102160 = false, _mut102161 = false, _mut102162 = false, _mut102163 = false, _mut102164 = false, _mut102165 = false, _mut102166 = false, _mut102167 = false, _mut102168 = false, _mut102169 = false, _mut102170 = false, _mut102171 = false, _mut102172 = false, _mut102173 = false, _mut102174 = false, _mut102175 = false, _mut102176 = false, _mut102177 = false, _mut102178 = false, _mut102179 = false, _mut102180 = false, _mut102181 = false, _mut102182 = false, _mut102183 = false, _mut102184 = false, _mut102185 = false, _mut102186 = false, _mut102187 = false, _mut102188 = false, _mut102189 = false, _mut102190 = false, _mut102191 = false, _mut102192 = false, _mut102193 = false, _mut102194 = false, _mut102195 = false, _mut102196 = false, _mut102197 = false, _mut102198 = false, _mut102199 = false, _mut102200 = false, _mut102201 = false, _mut102202 = false, _mut102203 = false, _mut102204 = false, _mut102205 = false, _mut102206 = false, _mut102207 = false, _mut102208 = false, _mut102209 = false, _mut102210 = false, _mut102211 = false, _mut102212 = false;

    /**
     * Maximum number of iterations for trapezoid.
     */
    public static final int TRAPEZOID_MAX_ITERATIONS_COUNT = 64;

    /**
     * Intermediate result.
     */
    private double s;

    /**
     * Build a trapezoid integrator with given accuracies and iterations counts.
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #TRAPEZOID_MAX_ITERATIONS_COUNT}
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #TRAPEZOID_MAX_ITERATIONS_COUNT}
     */
    public TrapezoidIntegrator(final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.TrapezoidIntegrator_59");
        if (ROR_greater(maximalIterationCount, TRAPEZOID_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.TrapezoidIntegrator_59", _mut102113, _mut102114, _mut102115, _mut102116, _mut102117)) {
            throw new NumberIsTooLargeException(maximalIterationCount, TRAPEZOID_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Build a trapezoid integrator with given iteration counts.
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #TRAPEZOID_MAX_ITERATIONS_COUNT}
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #TRAPEZOID_MAX_ITERATIONS_COUNT}
     */
    public TrapezoidIntegrator(final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.TrapezoidIntegrator_83");
        if (ROR_greater(maximalIterationCount, TRAPEZOID_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.TrapezoidIntegrator_83", _mut102118, _mut102119, _mut102120, _mut102121, _mut102122)) {
            throw new NumberIsTooLargeException(maximalIterationCount, TRAPEZOID_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Construct a trapezoid integrator with default settings.
     * (max iteration count set to {@link #TRAPEZOID_MAX_ITERATIONS_COUNT})
     */
    public TrapezoidIntegrator() {
        super(DEFAULT_MIN_ITERATIONS_COUNT, TRAPEZOID_MAX_ITERATIONS_COUNT);
    }

    /**
     * Compute the n-th stage integral of trapezoid rule. This function
     * should only be called by API <code>integrate()</code> in the package.
     * To save time it does not verify arguments - caller does.
     * <p>
     * The interval is divided equally into 2^n sections rather than an
     * arbitrary m sections because this configuration can best utilize the
     * already computed values.</p>
     *
     * @param baseIntegrator integrator holding integration parameters
     * @param n the stage of 1/2 refinement, n = 0 is no refinement
     * @return the value of n-th stage integral
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     */
    double stage(final BaseAbstractUnivariateIntegrator baseIntegrator, final int n) throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116");
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102123, _mut102124, _mut102125, _mut102126, _mut102127)) {
            final double max = baseIntegrator.getMax();
            final double min = baseIntegrator.getMin();
            s = AOR_multiply(AOR_multiply(0.5, (AOR_minus(max, min, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102165, _mut102166, _mut102167, _mut102168)), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102169, _mut102170, _mut102171, _mut102172), (AOR_plus(baseIntegrator.computeObjectiveValue(min), baseIntegrator.computeObjectiveValue(max), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102173, _mut102174, _mut102175, _mut102176)), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102177, _mut102178, _mut102179, _mut102180);
            return s;
        } else {
            // number of new points in this stage
            final long np = 1L << (AOR_minus(n, 1, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102128, _mut102129, _mut102130, _mut102131));
            double sum = 0;
            final double max = baseIntegrator.getMax();
            final double min = baseIntegrator.getMin();
            // spacing between adjacent new points
            final double spacing = AOR_divide((AOR_minus(max, min, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102132, _mut102133, _mut102134, _mut102135)), np, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102136, _mut102137, _mut102138, _mut102139);
            // the first new point
            double x = AOR_plus(min, AOR_multiply(0.5, spacing, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102140, _mut102141, _mut102142, _mut102143), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102144, _mut102145, _mut102146, _mut102147);
            for (long i = 0; ROR_less(i, np, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102148, _mut102149, _mut102150, _mut102151, _mut102152); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116");
                sum += baseIntegrator.computeObjectiveValue(x);
                x += spacing;
            }
            // add the new sum to previously calculated result
            s = AOR_multiply(0.5, (AOR_plus(s, AOR_multiply(sum, spacing, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102153, _mut102154, _mut102155, _mut102156), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102157, _mut102158, _mut102159, _mut102160)), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.stage_116", _mut102161, _mut102162, _mut102163, _mut102164);
            return s;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145");
        double oldt = stage(this, 0);
        incrementCount();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145");
            final int i = getIterations();
            final double t = stage(this, i);
            if (ROR_greater_equals(i, getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102181, _mut102182, _mut102183, _mut102184, _mut102185)) {
                final double delta = FastMath.abs(AOR_minus(t, oldt, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102186, _mut102187, _mut102188, _mut102189));
                final double rLimit = AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(oldt), FastMath.abs(t), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102190, _mut102191, _mut102192, _mut102193)), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102194, _mut102195, _mut102196, _mut102197), 0.5, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102198, _mut102199, _mut102200, _mut102201);
                if ((_mut102212 ? ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102202, _mut102203, _mut102204, _mut102205, _mut102206)) && (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102207, _mut102208, _mut102209, _mut102210, _mut102211))) : ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102202, _mut102203, _mut102204, _mut102205, _mut102206)) || (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.TrapezoidIntegrator.doIntegrate_145", _mut102207, _mut102208, _mut102209, _mut102210, _mut102211))))) {
                    return t;
                }
            }
            oldt = t;
            incrementCount();
        }
    }
}
