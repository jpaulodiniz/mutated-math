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
 * Implements <a href="http://mathworld.wolfram.com/SimpsonsRule.html">
 * Simpson's Rule</a> for integration of real univariate functions. For
 * reference, see <b>Introduction to Numerical Analysis</b>, ISBN 038795452X,
 * chapter 3.
 * <p>
 * This implementation employs the basic trapezoid rule to calculate Simpson's
 * rule.</p>
 *
 * @since 1.2
 */
public class SimpsonIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut102032 = false, _mut102033 = false, _mut102034 = false, _mut102035 = false, _mut102036 = false, _mut102037 = false, _mut102038 = false, _mut102039 = false, _mut102040 = false, _mut102041 = false, _mut102042 = false, _mut102043 = false, _mut102044 = false, _mut102045 = false, _mut102046 = false, _mut102047 = false, _mut102048 = false, _mut102049 = false, _mut102050 = false, _mut102051 = false, _mut102052 = false, _mut102053 = false, _mut102054 = false, _mut102055 = false, _mut102056 = false, _mut102057 = false, _mut102058 = false, _mut102059 = false, _mut102060 = false, _mut102061 = false, _mut102062 = false, _mut102063 = false, _mut102064 = false, _mut102065 = false, _mut102066 = false, _mut102067 = false, _mut102068 = false, _mut102069 = false, _mut102070 = false, _mut102071 = false, _mut102072 = false, _mut102073 = false, _mut102074 = false, _mut102075 = false, _mut102076 = false, _mut102077 = false, _mut102078 = false, _mut102079 = false, _mut102080 = false, _mut102081 = false, _mut102082 = false, _mut102083 = false, _mut102084 = false, _mut102085 = false, _mut102086 = false, _mut102087 = false, _mut102088 = false, _mut102089 = false, _mut102090 = false, _mut102091 = false, _mut102092 = false, _mut102093 = false, _mut102094 = false, _mut102095 = false, _mut102096 = false, _mut102097 = false, _mut102098 = false, _mut102099 = false, _mut102100 = false, _mut102101 = false, _mut102102 = false;

    /**
     * Maximal number of iterations for Simpson.
     */
    public static final int SIMPSON_MAX_ITERATIONS_COUNT = 64;

    /**
     * Build a Simpson integrator with given accuracies and iterations counts.
     * @param relativeAccuracy relative accuracy of the result
     * @param absoluteAccuracy absolute accuracy of the result
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #SIMPSON_MAX_ITERATIONS_COUNT})
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #SIMPSON_MAX_ITERATIONS_COUNT}
     */
    public SimpsonIntegrator(final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.SimpsonIntegrator.SimpsonIntegrator_56");
        if (ROR_greater(maximalIterationCount, SIMPSON_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.SimpsonIntegrator_56", _mut102032, _mut102033, _mut102034, _mut102035, _mut102036)) {
            throw new NumberIsTooLargeException(maximalIterationCount, SIMPSON_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Build a Simpson integrator with given iteration counts.
     * @param minimalIterationCount minimum number of iterations
     * @param maximalIterationCount maximum number of iterations
     * (must be less than or equal to {@link #SIMPSON_MAX_ITERATIONS_COUNT})
     * @exception NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive
     * @exception NumberIsTooSmallException if maximal number of iterations
     * is lesser than or equal to the minimal number of iterations
     * @exception NumberIsTooLargeException if maximal number of iterations
     * is greater than {@link #SIMPSON_MAX_ITERATIONS_COUNT}
     */
    public SimpsonIntegrator(final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.SimpsonIntegrator.SimpsonIntegrator_80");
        if (ROR_greater(maximalIterationCount, SIMPSON_MAX_ITERATIONS_COUNT, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.SimpsonIntegrator_80", _mut102037, _mut102038, _mut102039, _mut102040, _mut102041)) {
            throw new NumberIsTooLargeException(maximalIterationCount, SIMPSON_MAX_ITERATIONS_COUNT, false);
        }
    }

    /**
     * Construct an integrator with default settings.
     * (max iteration count set to {@link #SIMPSON_MAX_ITERATIONS_COUNT})
     */
    public SimpsonIntegrator() {
        super(DEFAULT_MIN_ITERATIONS_COUNT, SIMPSON_MAX_ITERATIONS_COUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99");
        TrapezoidIntegrator qtrap = new TrapezoidIntegrator();
        if (ROR_equals(getMinimalIterationCount(), 1, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102042, _mut102043, _mut102044, _mut102045, _mut102046)) {
            return AOR_divide((AOR_minus(AOR_multiply(4, qtrap.stage(this, 1), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102047, _mut102048, _mut102049, _mut102050), qtrap.stage(this, 0), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102051, _mut102052, _mut102053, _mut102054)), 3.0, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102055, _mut102056, _mut102057, _mut102058);
        }
        // Simpson's rule requires at least two trapezoid stages.
        double olds = 0;
        double oldt = qtrap.stage(this, 0);
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99");
            final double t = qtrap.stage(this, getIterations());
            incrementCount();
            final double s = AOR_divide((AOR_minus(AOR_multiply(4, t, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102059, _mut102060, _mut102061, _mut102062), oldt, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102063, _mut102064, _mut102065, _mut102066)), 3.0, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102067, _mut102068, _mut102069, _mut102070);
            if (ROR_greater_equals(getIterations(), getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102071, _mut102072, _mut102073, _mut102074, _mut102075)) {
                final double delta = FastMath.abs(AOR_minus(s, olds, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102076, _mut102077, _mut102078, _mut102079));
                final double rLimit = AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(olds), FastMath.abs(s), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102080, _mut102081, _mut102082, _mut102083)), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102084, _mut102085, _mut102086, _mut102087), 0.5, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102088, _mut102089, _mut102090, _mut102091);
                if ((_mut102102 ? ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102092, _mut102093, _mut102094, _mut102095, _mut102096)) && (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102097, _mut102098, _mut102099, _mut102100, _mut102101))) : ((ROR_less_equals(delta, rLimit, "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102092, _mut102093, _mut102094, _mut102095, _mut102096)) || (ROR_less_equals(delta, getAbsoluteAccuracy(), "org.apache.commons.math3.analysis.integration.SimpsonIntegrator.doIntegrate_99", _mut102097, _mut102098, _mut102099, _mut102100, _mut102101))))) {
                    return s;
                }
            }
            olds = s;
            oldt = t;
        }
    }
}
