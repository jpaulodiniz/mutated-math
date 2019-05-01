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
package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Harmonic_oscillator">
 *  simple harmonic oscillator</a> function.
 *
 * @since 3.0
 */
public class HarmonicOscillator implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut91287 = false, _mut91288 = false, _mut91289 = false, _mut91290 = false, _mut91291 = false, _mut91292 = false, _mut91293 = false, _mut91294 = false, _mut91295 = false, _mut91296 = false, _mut91297 = false, _mut91298 = false, _mut91299 = false, _mut91300 = false, _mut91301 = false, _mut91302 = false, _mut91303 = false, _mut91304 = false, _mut91305 = false, _mut91306 = false, _mut91307 = false, _mut91308 = false, _mut91309 = false, _mut91310 = false, _mut91311 = false, _mut91312 = false, _mut91313 = false, _mut91314 = false, _mut91315 = false, _mut91316 = false, _mut91317 = false, _mut91318 = false, _mut91319 = false, _mut91320 = false, _mut91321 = false, _mut91322 = false, _mut91323 = false, _mut91324 = false, _mut91325 = false, _mut91326 = false, _mut91327 = false, _mut91328 = false, _mut91329 = false, _mut91330 = false, _mut91331 = false, _mut91332 = false, _mut91333 = false, _mut91334 = false, _mut91335 = false, _mut91336 = false, _mut91337 = false, _mut91338 = false, _mut91339 = false, _mut91340 = false, _mut91341 = false, _mut91342 = false, _mut91343 = false, _mut91344 = false, _mut91345 = false, _mut91346 = false, _mut91347 = false, _mut91348 = false, _mut91349 = false, _mut91350 = false, _mut91351 = false, _mut91352 = false, _mut91353 = false, _mut91354 = false, _mut91355 = false, _mut91356 = false, _mut91357 = false, _mut91358 = false, _mut91359 = false, _mut91360 = false, _mut91361 = false, _mut91362 = false, _mut91363 = false, _mut91364 = false, _mut91365 = false, _mut91366 = false, _mut91367 = false, _mut91368 = false, _mut91369 = false, _mut91370 = false, _mut91371 = false, _mut91372 = false, _mut91373 = false;

    /**
     * Amplitude.
     */
    private final double amplitude;

    /**
     * Angular frequency.
     */
    private final double omega;

    /**
     * Phase.
     */
    private final double phase;

    /**
     * Harmonic oscillator function.
     *
     * @param amplitude Amplitude.
     * @param omega Angular frequency.
     * @param phase Phase.
     */
    public HarmonicOscillator(double amplitude, double omega, double phase) {
        this.amplitude = amplitude;
        this.omega = omega;
        this.phase = phase;
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.value_60");
        return value(AOR_plus(AOR_multiply(omega, x, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_60", _mut91287, _mut91288, _mut91289, _mut91290), phase, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_60", _mut91291, _mut91292, _mut91293, _mut91294), amplitude);
    }

    /**
     * {@inheritDoc}
     * @deprecated as of 3.1, replaced by {@link #value(DerivativeStructure)}
     */
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /**
     * Parametric function where the input array contains the parameters of
     * the harmonic oscillator function, ordered as follows:
     * <ul>
     *  <li>Amplitude</li>
     *  <li>Angular frequency</li>
     *  <li>Phase</li>
     * </ul>
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * Computes the value of the harmonic oscillator at {@code x}.
         *
         * @param x Value for which the function must be computed.
         * @param param Values of norm, mean and standard deviation.
         * @return the value of the function.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         */
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.value_92");
            validateParameters(param);
            return HarmonicOscillator.value(AOR_plus(AOR_multiply(x, param[1], "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_92", _mut91295, _mut91296, _mut91297, _mut91298), param[2], "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_92", _mut91299, _mut91300, _mut91301, _mut91302), param[0]);
        }

        /**
         * Computes the value of the gradient at {@code x}.
         * The components of the gradient vector are the partial
         * derivatives of the function with respect to each of the
         * <em>parameters</em> (amplitude, angular frequency and phase).
         *
         * @param x Value at which the gradient must be computed.
         * @param param Values of amplitude, angular frequency and phase.
         * @return the gradient vector at {@code x}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         */
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.gradient_112");
            validateParameters(param);
            final double amplitude = param[0];
            final double omega = param[1];
            final double phase = param[2];
            final double xTimesOmegaPlusPhase = AOR_plus(AOR_multiply(omega, x, "org.apache.commons.math3.analysis.function.HarmonicOscillator.gradient_112", _mut91303, _mut91304, _mut91305, _mut91306), phase, "org.apache.commons.math3.analysis.function.HarmonicOscillator.gradient_112", _mut91307, _mut91308, _mut91309, _mut91310);
            final double a = HarmonicOscillator.value(xTimesOmegaPlusPhase, 1);
            final double p = AOR_multiply(-amplitude, FastMath.sin(xTimesOmegaPlusPhase), "org.apache.commons.math3.analysis.function.HarmonicOscillator.gradient_112", _mut91311, _mut91312, _mut91313, _mut91314);
            final double w = AOR_multiply(p, x, "org.apache.commons.math3.analysis.function.HarmonicOscillator.gradient_112", _mut91315, _mut91316, _mut91317, _mut91318);
            return new double[] { a, w, p };
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double,double[])} and {@link #gradient(double,double[])}
         * methods.
         *
         * @param param Values of norm, mean and standard deviation.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         */
        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.validateParameters_139");
            if (param == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(param.length, 3, "org.apache.commons.math3.analysis.function.HarmonicOscillator.validateParameters_139", _mut91319, _mut91320, _mut91321, _mut91322, _mut91323)) {
                throw new DimensionMismatchException(param.length, 3);
            }
        }
    }

    /**
     * @param xTimesOmegaPlusPhase {@code omega * x + phase}.
     * @param amplitude Amplitude.
     * @return the value of the harmonic oscillator function at {@code x}.
     */
    private static double value(double xTimesOmegaPlusPhase, double amplitude) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.value_156");
        return AOR_multiply(amplitude, FastMath.cos(xTimesOmegaPlusPhase), "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_156", _mut91324, _mut91325, _mut91326, _mut91327);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164");
        final double x = t.getValue();
        double[] f = new double[AOR_plus(t.getOrder(), 1, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91328, _mut91329, _mut91330, _mut91331)];
        final double alpha = AOR_plus(AOR_multiply(omega, x, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91332, _mut91333, _mut91334, _mut91335), phase, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91336, _mut91337, _mut91338, _mut91339);
        f[0] = AOR_multiply(amplitude, FastMath.cos(alpha), "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91340, _mut91341, _mut91342, _mut91343);
        if (ROR_greater(f.length, 1, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91344, _mut91345, _mut91346, _mut91347, _mut91348)) {
            f[1] = AOR_multiply(AOR_multiply(-amplitude, omega, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91349, _mut91350, _mut91351, _mut91352), FastMath.sin(alpha), "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91353, _mut91354, _mut91355, _mut91356);
            final double mo2 = AOR_multiply(-omega, omega, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91357, _mut91358, _mut91359, _mut91360);
            for (int i = 2; ROR_less(i, f.length, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91369, _mut91370, _mut91371, _mut91372, _mut91373); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164");
                f[i] = AOR_multiply(mo2, f[AOR_minus(i, 2, "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91361, _mut91362, _mut91363, _mut91364)], "org.apache.commons.math3.analysis.function.HarmonicOscillator.value_164", _mut91365, _mut91366, _mut91367, _mut91368);
            }
        }
        return t.compose(f);
    }
}
