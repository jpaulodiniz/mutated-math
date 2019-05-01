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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class IterativeLegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {

    @Conditional
    public static boolean _mut100947 = false, _mut100948 = false, _mut100949 = false, _mut100950 = false, _mut100951 = false, _mut100952 = false, _mut100953 = false, _mut100954 = false, _mut100955 = false, _mut100956 = false, _mut100957 = false, _mut100958 = false, _mut100959 = false, _mut100960 = false, _mut100961 = false, _mut100962 = false, _mut100963 = false, _mut100964 = false, _mut100965 = false, _mut100966 = false, _mut100967 = false, _mut100968 = false, _mut100969 = false, _mut100970 = false, _mut100971 = false, _mut100972 = false, _mut100973 = false, _mut100974 = false, _mut100975 = false, _mut100976 = false, _mut100977 = false, _mut100978 = false, _mut100979 = false, _mut100980 = false, _mut100981 = false, _mut100982 = false, _mut100983 = false, _mut100984 = false, _mut100985 = false, _mut100986 = false, _mut100987 = false, _mut100988 = false, _mut100989 = false, _mut100990 = false, _mut100991 = false, _mut100992 = false, _mut100993 = false, _mut100994 = false, _mut100995 = false, _mut100996 = false, _mut100997 = false, _mut100998 = false, _mut100999 = false, _mut101000 = false, _mut101001 = false, _mut101002 = false, _mut101003 = false, _mut101004 = false, _mut101005 = false, _mut101006 = false, _mut101007 = false, _mut101008 = false, _mut101009 = false, _mut101010 = false, _mut101011 = false, _mut101012 = false, _mut101013 = false, _mut101014 = false, _mut101015 = false, _mut101016 = false, _mut101017 = false, _mut101018 = false, _mut101019 = false, _mut101020 = false, _mut101021 = false, _mut101022 = false, _mut101023 = false;

    /**
     * Factory that computes the points and weights.
     */
    private static final GaussIntegratorFactory FACTORY = new GaussIntegratorFactory();

    /**
     * Number of integration points (per interval).
     */
    private final int numberOfPoints;

    /**
     * Builds an integrator with given accuracies and iterations counts.
     *
     * @param n Number of integration points.
     * @param relativeAccuracy Relative accuracy of the result.
     * @param absoluteAccuracy Absolute accuracy of the result.
     * @param minimalIterationCount Minimum number of iterations.
     * @param maximalIterationCount Maximum number of iterations.
     * @throws NotStrictlyPositiveException if minimal number of iterations
     * or number of points are not strictly positive.
     * @throws NumberIsTooSmallException if maximal number of iterations
     * is smaller than or equal to the minimal number of iterations.
     */
    public IterativeLegendreGaussIntegrator(final int n, final double relativeAccuracy, final double absoluteAccuracy, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.IterativeLegendreGaussIntegrator_68");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.IterativeLegendreGaussIntegrator_68", _mut100947, _mut100948, _mut100949, _mut100950, _mut100951)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, n);
        }
        numberOfPoints = n;
    }

    /**
     * Builds an integrator with given accuracies.
     *
     * @param n Number of integration points.
     * @param relativeAccuracy Relative accuracy of the result.
     * @param absoluteAccuracy Absolute accuracy of the result.
     * @throws NotStrictlyPositiveException if {@code n < 1}.
     */
    public IterativeLegendreGaussIntegrator(final int n, final double relativeAccuracy, final double absoluteAccuracy) throws NotStrictlyPositiveException {
        this(n, relativeAccuracy, absoluteAccuracy, DEFAULT_MIN_ITERATIONS_COUNT, DEFAULT_MAX_ITERATIONS_COUNT);
    }

    /**
     * Builds an integrator with given iteration counts.
     *
     * @param n Number of integration points.
     * @param minimalIterationCount Minimum number of iterations.
     * @param maximalIterationCount Maximum number of iterations.
     * @throws NotStrictlyPositiveException if minimal number of iterations
     * is not strictly positive.
     * @throws NumberIsTooSmallException if maximal number of iterations
     * is smaller than or equal to the minimal number of iterations.
     * @throws NotStrictlyPositiveException if {@code n < 1}.
     */
    public IterativeLegendreGaussIntegrator(final int n, final int minimalIterationCount, final int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        this(n, DEFAULT_RELATIVE_ACCURACY, DEFAULT_ABSOLUTE_ACCURACY, minimalIterationCount, maximalIterationCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118");
        // Compute first estimate with a single step.
        double oldt = stage(1);
        int n = 2;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118");
            // Improve integral with a larger number of steps.
            final double t = stage(n);
            // Estimate the error.
            final double delta = FastMath.abs(AOR_minus(t, oldt, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100952, _mut100953, _mut100954, _mut100955));
            final double limit = FastMath.max(getAbsoluteAccuracy(), AOR_multiply(AOR_multiply(getRelativeAccuracy(), (AOR_plus(FastMath.abs(oldt), FastMath.abs(t), "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100956, _mut100957, _mut100958, _mut100959)), "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100960, _mut100961, _mut100962, _mut100963), 0.5, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100964, _mut100965, _mut100966, _mut100967));
            // check convergence
            if ((_mut100982 ? (ROR_greater_equals(AOR_plus(getIterations(), 1, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100968, _mut100969, _mut100970, _mut100971), getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100972, _mut100973, _mut100974, _mut100975, _mut100976) || ROR_less_equals(delta, limit, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100977, _mut100978, _mut100979, _mut100980, _mut100981)) : (ROR_greater_equals(AOR_plus(getIterations(), 1, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100968, _mut100969, _mut100970, _mut100971), getMinimalIterationCount(), "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100972, _mut100973, _mut100974, _mut100975, _mut100976) && ROR_less_equals(delta, limit, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100977, _mut100978, _mut100979, _mut100980, _mut100981)))) {
                return t;
            }
            // Prepare next iteration.
            final double ratio = FastMath.min(4, FastMath.pow(AOR_divide(delta, limit, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100983, _mut100984, _mut100985, _mut100986), AOR_divide(0.5, numberOfPoints, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100987, _mut100988, _mut100989, _mut100990)));
            n = FastMath.max((int) (AOR_multiply(ratio, n, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100991, _mut100992, _mut100993, _mut100994)), AOR_plus(n, 1, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.doIntegrate_118", _mut100995, _mut100996, _mut100997, _mut100998));
            oldt = t;
            incrementCount();
        }
    }

    /**
     * Compute the n-th stage integral.
     *
     * @param n Number of steps.
     * @return the value of n-th stage integral.
     * @throws TooManyEvaluationsException if the maximum number of evaluations
     * is exceeded.
     */
    private double stage(final int n) throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162");
        // Function to be integrated is stored in the base class.
        final UnivariateFunction f = new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) throws MathIllegalArgumentException, TooManyEvaluationsException {
                return computeObjectiveValue(x);
            }
        };
        final double min = getMin();
        final double max = getMax();
        final double step = AOR_divide((AOR_minus(max, min, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut100999, _mut101000, _mut101001, _mut101002)), n, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut101003, _mut101004, _mut101005, _mut101006);
        double sum = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut101019, _mut101020, _mut101021, _mut101022, _mut101023); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162");
            // Integrate over each sub-interval [a, b].
            final double a = AOR_plus(min, AOR_multiply(i, step, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut101007, _mut101008, _mut101009, _mut101010), "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut101011, _mut101012, _mut101013, _mut101014);
            final double b = AOR_plus(a, step, "org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.value_162", _mut101015, _mut101016, _mut101017, _mut101018);
            final GaussIntegrator g = FACTORY.legendreHighPrecision(numberOfPoints, a, b);
            sum += g.integrate(f);
        }
        return sum;
    }
}
