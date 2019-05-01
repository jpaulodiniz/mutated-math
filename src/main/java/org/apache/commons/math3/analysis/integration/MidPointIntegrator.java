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
 * Implements the <a href="http://en.wikipedia.org/wiki/Midpoint_method">
 * Midpoint Rule</a> for integration of real univariate functions. For
 * reference, see <b>Numerical Mathematics</b>, ISBN 0387989595,
 * chapter 9.2.
 * <p>
 * The function should be integrable.</p>
 *
 * @since 3.3
 */
public class MidPointIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut102292 = false, _mut102293 = false, _mut102294 = false, _mut102295 = false, _mut102296 = false, _mut102297 = false, _mut102298 = false, _mut102299 = false, _mut102300 = false, _mut102301 = false, _mut102302 = false, _mut102303 = false, _mut102304 = false, _mut102305 = false, _mut102306 = false, _mut102307 = false, _mut102308 = false, _mut102309 = false, _mut102310 = false, _mut102311 = false, _mut102312 = false, _mut102313 = false, _mut102314 = false, _mut102315 = false, _mut102316 = false, _mut102317 = false, _mut102318 = false, _mut102319 = false, _mut102320 = false, _mut102321 = false, _mut102322 = false, _mut102323 = false, _mut102324 = false, _mut102325 = false, _mut102326 = false, _mut102327 = false, _mut102328 = false, _mut102329 = false, _mut102330 = false, _mut102331 = false, _mut102332 = false, _mut102333 = false, _mut102334 = false, _mut102335 = false, _mut102336 = false, _mut102337 = false, _mut102338 = false, _mut102339 = false, _mut102340 = false, _mut102341 = false, _mut102342 = false, _mut102343 = false, _mut102344 = false, _mut102345 = false, _mut102346 = false, _mut102347 = false, _mut102348 = false, _mut102349 = false, _mut102350 = false, _mut102351 = false, _mut102352 = false, _mut102353 = false, _mut102354 = false, _mut102355 = false, _mut102356 = false, _mut102357 = false, _mut102358 = false, _mut102359 = false, _mut102360 = false, _mut102361 = false, _mut102362 = false, _mut102363 = false, _mut102364 = false, _mut102365 = false, _mut102366 = false, _mut102367 = false, _mut102368 = false, _mut102369 = false, _mut102370 = false, _mut102371 = false, _mut102372 = false, _mut102373 = false, _mut102374 = false, _mut102375 = false, _mut102376 = false, _mut102377 = false, _mut102378 = false, _mut102379 = false, _mut102380 = false, _mut102381 = false, _mut102382 = false;

    /**
     * Maximum number of iterations for midpoint.
     */
    public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

    /**
     * Build a midpoint integrator with given accuracies and iterations counts.
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #MIDPOINT_MAX_ITERATIONS_COUNT}
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #MIDPOINT_MAX_ITERATIONS_COUNT}
     */
    public MidPointIntegrator(final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.MidPointIntegrator_56");
        if (ROR_greater(maximalIterationCount, MIDPOINT_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.MidPointIntegrator_56", _mut102292, _mut102293, _mut102294, _mut102295, _mut102296)) {
            throw new NumberIsTooLargeException(maximalIterationCount, MIDPOINT_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Build a midpoint integrator with given iteration counts.
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #MIDPOINT_MAX_ITERATIONS_COUNT}
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #MIDPOINT_MAX_ITERATIONS_COUNT}
     */
    public MidPointIntegrator(final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.MidPointIntegrator_80");
        if (ROR_greater(maximalIterationCount, MIDPOINT_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.MidPointIntegrator_80", _mut102297, _mut102298, _mut102299, _mut102300, _mut102301)) {
            throw new NumberIsTooLargeException(maximalIterationCount, MIDPOINT_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Construct a midpoint integrator with default settings.
     * (max iteration count set to {@link #MIDPOINT_MAX_ITERATIONS_COUNT})
     */
    public MidPointIntegrator() {
        super(DEFAULT_MIN_ITERATIONS_COUNT, MIDPOINT_MAX_ITERATIONS_COUNT);
    }

    /**
     * Compute the n-th stage integral of midpoint rule.
     * This function should only be called by API <code>integrate()</code> in the package.
     * To save time it does not verify arguments - caller does.
     * <p>
     * The interval is divided equally into 2^n sections rather than an
     * arbitrary m sections because this configuration can best utilize the
     * already computed values.</p>
     *
     * @param n the stage of 1/2 refinement. Must be larger than 0.
     * @param previousStageResult Result from the previous call to the
     * {@code stage} method.
     * @param min Lower bound of the integration interval.
     * @param diffMaxMin Difference between the lower bound and upper bound
     * of the integration interval.
     * @return the value of n-th stage integral
     * @throws TooManyEvaluationsException if the maximal number of evaluations
     * is exceeded.
     */
    private double stage(final int n, double previousStageResult, double min, double diffMaxMin) throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117");
        // number of new points in this stage
        final long np = 1L << (AOR_minus(n, 1, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102302, _mut102303, _mut102304, _mut102305));
        double sum = 0;
        // spacing between adjacent new points
        final double spacing = AOR_divide(diffMaxMin, np, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102306, _mut102307, _mut102308, _mut102309);
        // the first new point
        double x = AOR_plus(min, AOR_multiply(0.5, spacing, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102310, _mut102311, _mut102312, _mut102313), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102314, _mut102315, _mut102316, _mut102317);
        for (long i = 0; ROR_less(i, np, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102318, _mut102319, _mut102320, _mut102321, _mut102322); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117");
            sum += computeObjectiveValue(x);
            x += spacing;
        }
        // add the new sum to previously calculated result
        return AOR_multiply(0.5, (AOR_plus(previousStageResult, AOR_multiply(sum, spacing, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102323, _mut102324, _mut102325, _mut102326), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102327, _mut102328, _mut102329, _mut102330)), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.stage_117", _mut102331, _mut102332, _mut102333, _mut102334);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142");
        final double min = getMin();
        final double diff = AOR_minus(getMax(), min, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102335, _mut102336, _mut102337, _mut102338);
        final double midPoint = AOR_plus(min, AOR_multiply(0.5, diff, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102339, _mut102340, _mut102341, _mut102342), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102343, _mut102344, _mut102345, _mut102346);
        double oldt = AOR_multiply(diff, computeObjectiveValue(midPoint), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102347, _mut102348, _mut102349, _mut102350);
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142");
            incrementCount();
            final int i = getIterations();
            final double t = stage(i, oldt, min, diff);
            if (ROR_greater_equals(i, getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102351, _mut102352, _mut102353, _mut102354, _mut102355)) {
                final double delta = FastMath.abs(AOR_minus(t, oldt, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102356, _mut102357, _mut102358, _mut102359));
                final double rLimit = AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(oldt), FastMath.abs(t), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102360, _mut102361, _mut102362, _mut102363)), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102364, _mut102365, _mut102366, _mut102367), 0.5, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102368, _mut102369, _mut102370, _mut102371);
                if ((_mut102382 ? ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102372, _mut102373, _mut102374, _mut102375, _mut102376)) && (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102377, _mut102378, _mut102379, _mut102380, _mut102381))) : ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102372, _mut102373, _mut102374, _mut102375, _mut102376)) || (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.MidPointIntegrator.doIntegrate_142", _mut102377, _mut102378, _mut102379, _mut102380, _mut102381))))) {
                    return t;
                }
            }
            oldt = t;
        }
    }
}
