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

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://mathworld.wolfram.com/RombergIntegration.html">
 * Romberg Algorithm</a> for integration of real univariate functions. For
 * reference, see <b>Introduction to Numerical Analysis</b>, ISBN 038795452X,
 * chapter 3.
 * <p>
 * Romberg integration employs k successive refinements of the trapezoid
 * rule to remove error terms less than order O(N^(-2k)). Simpson's rule
 * is a special case of k = 2.</p>
 *
 * @since 1.2
 */
public class RombergIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut102213 = false, _mut102214 = false, _mut102215 = false, _mut102216 = false, _mut102217 = false, _mut102218 = false, _mut102219 = false, _mut102220 = false, _mut102221 = false, _mut102222 = false, _mut102223 = false, _mut102224 = false, _mut102225 = false, _mut102226 = false, _mut102227 = false, _mut102228 = false, _mut102229 = false, _mut102230 = false, _mut102231 = false, _mut102232 = false, _mut102233 = false, _mut102234 = false, _mut102235 = false, _mut102236 = false, _mut102237 = false, _mut102238 = false, _mut102239 = false, _mut102240 = false, _mut102241 = false, _mut102242 = false, _mut102243 = false, _mut102244 = false, _mut102245 = false, _mut102246 = false, _mut102247 = false, _mut102248 = false, _mut102249 = false, _mut102250 = false, _mut102251 = false, _mut102252 = false, _mut102253 = false, _mut102254 = false, _mut102255 = false, _mut102256 = false, _mut102257 = false, _mut102258 = false, _mut102259 = false, _mut102260 = false, _mut102261 = false, _mut102262 = false, _mut102263 = false, _mut102264 = false, _mut102265 = false, _mut102266 = false, _mut102267 = false, _mut102268 = false, _mut102269 = false, _mut102270 = false, _mut102271 = false, _mut102272 = false, _mut102273 = false, _mut102274 = false, _mut102275 = false, _mut102276 = false, _mut102277 = false, _mut102278 = false, _mut102279 = false, _mut102280 = false, _mut102281 = false, _mut102282 = false, _mut102283 = false, _mut102284 = false, _mut102285 = false, _mut102286 = false, _mut102287 = false, _mut102288 = false, _mut102289 = false, _mut102290 = false, _mut102291 = false;

    /**
     * Maximal number of iterations for Romberg.
     */
    public static final int ROMBERG_MAX_ITERATIONS_COUNT = 32;

    /**
     * Build a Romberg integrator with given accuracies and iterations counts.
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #ROMBERG_MAX_ITERATIONS_COUNT})
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #ROMBERG_MAX_ITERATIONS_COUNT}
     */
    public RombergIntegrator(final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.RombergIntegrator.RombergIntegrator_57");
        if (ROR_greater(maximalIterationCount, ROMBERG_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.RombergIntegrator.RombergIntegrator_57", _mut102213, _mut102214, _mut102215, _mut102216, _mut102217)) {
            throw new NumberIsTooLargeException(maximalIterationCount, ROMBERG_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Build a Romberg integrator with given iteration counts.
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #ROMBERG_MAX_ITERATIONS_COUNT})
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #ROMBERG_MAX_ITERATIONS_COUNT}
     */
    public RombergIntegrator(final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.RombergIntegrator.RombergIntegrator_81");
        if (ROR_greater(maximalIterationCount, ROMBERG_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.RombergIntegrator.RombergIntegrator_81", _mut102218, _mut102219, _mut102220, _mut102221, _mut102222)) {
            throw new NumberIsTooLargeException(maximalIterationCount, ROMBERG_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Construct a Romberg integrator with default settings
     * (max iteration count set to {@link #ROMBERG_MAX_ITERATIONS_COUNT})
     */
    public RombergIntegrator() {
        super(DEFAULT_MIN_ITERATIONS_COUNT, ROMBERG_MAX_ITERATIONS_COUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100");
        final int m = AOR_plus(getMaximalIterationCount(), 1, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102223, _mut102224, _mut102225, _mut102226);
        double[] previousRow = new double[m];
        double[] currentRow = new double[m];
        TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
        currentRow[0] = qtrap.stage(this, 0);
        incrementCount();
        double olds = currentRow[0];
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100");
            final int i = getIterations();
            // switch rows
            final double[] tmpRow = previousRow;
            previousRow = currentRow;
            currentRow = tmpRow;
            currentRow[0] = qtrap.stage(this, i);
            incrementCount();
            for (int j = 1; ROR_less_equals(j, i, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102255, _mut102256, _mut102257, _mut102258, _mut102259); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100");
                // Richardson extrapolation coefficient
                final double r = AOR_minus((1L << (AOR_multiply(2, j, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102227, _mut102228, _mut102229, _mut102230))), 1, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102231, _mut102232, _mut102233, _mut102234);
                final double tIJm1 = currentRow[AOR_minus(j, 1, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102235, _mut102236, _mut102237, _mut102238)];
                currentRow[j] = AOR_plus(tIJm1, AOR_divide((AOR_minus(tIJm1, previousRow[AOR_minus(j, 1, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102239, _mut102240, _mut102241, _mut102242)], "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102243, _mut102244, _mut102245, _mut102246)), r, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102247, _mut102248, _mut102249, _mut102250), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102251, _mut102252, _mut102253, _mut102254);
            }
            final double s = currentRow[i];
            if (ROR_greater_equals(i, getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102260, _mut102261, _mut102262, _mut102263, _mut102264)) {
                final double delta = FastMath.abs(AOR_minus(s, olds, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102265, _mut102266, _mut102267, _mut102268));
                final double rLimit = AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(olds), FastMath.abs(s), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102269, _mut102270, _mut102271, _mut102272)), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102273, _mut102274, _mut102275, _mut102276), 0.5, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102277, _mut102278, _mut102279, _mut102280);
                if ((_mut102291 ? ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102281, _mut102282, _mut102283, _mut102284, _mut102285)) && (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102286, _mut102287, _mut102288, _mut102289, _mut102290))) : ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102281, _mut102282, _mut102283, _mut102284, _mut102285)) || (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.RombergIntegrator.doIntegrate_100", _mut102286, _mut102287, _mut102288, _mut102289, _mut102290))))) {
                    return s;
                }
            }
            olds = s;
        }
    }
}
