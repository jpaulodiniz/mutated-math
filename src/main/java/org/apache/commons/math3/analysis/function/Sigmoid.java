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

import java.util.Arrays;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Sigmoid_function">
 *  Sigmoid</a> function.
 * It is the inverse of the {@link Logit logit} function.
 * A more flexible version, the generalised logistic, is implemented
 * by the {@link Logistic} class.
 *
 * @since 3.0
 */
public class Sigmoid implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut91409 = false, _mut91410 = false, _mut91411 = false, _mut91412 = false, _mut91413 = false, _mut91414 = false, _mut91415 = false, _mut91416 = false, _mut91417 = false, _mut91418 = false, _mut91419 = false, _mut91420 = false, _mut91421 = false, _mut91422 = false, _mut91423 = false, _mut91424 = false, _mut91425 = false, _mut91426 = false, _mut91427 = false, _mut91428 = false, _mut91429 = false, _mut91430 = false, _mut91431 = false, _mut91432 = false, _mut91433 = false, _mut91434 = false, _mut91435 = false, _mut91436 = false, _mut91437 = false, _mut91438 = false, _mut91439 = false, _mut91440 = false, _mut91441 = false, _mut91442 = false, _mut91443 = false, _mut91444 = false, _mut91445 = false, _mut91446 = false, _mut91447 = false, _mut91448 = false, _mut91449 = false, _mut91450 = false, _mut91451 = false, _mut91452 = false, _mut91453 = false, _mut91454 = false, _mut91455 = false, _mut91456 = false, _mut91457 = false, _mut91458 = false, _mut91459 = false, _mut91460 = false, _mut91461 = false, _mut91462 = false, _mut91463 = false, _mut91464 = false, _mut91465 = false, _mut91466 = false, _mut91467 = false, _mut91468 = false, _mut91469 = false, _mut91470 = false, _mut91471 = false, _mut91472 = false, _mut91473 = false, _mut91474 = false, _mut91475 = false, _mut91476 = false, _mut91477 = false, _mut91478 = false, _mut91479 = false, _mut91480 = false, _mut91481 = false, _mut91482 = false, _mut91483 = false, _mut91484 = false, _mut91485 = false, _mut91486 = false, _mut91487 = false, _mut91488 = false, _mut91489 = false, _mut91490 = false, _mut91491 = false, _mut91492 = false, _mut91493 = false, _mut91494 = false, _mut91495 = false, _mut91496 = false, _mut91497 = false, _mut91498 = false, _mut91499 = false, _mut91500 = false, _mut91501 = false, _mut91502 = false, _mut91503 = false, _mut91504 = false, _mut91505 = false, _mut91506 = false, _mut91507 = false, _mut91508 = false, _mut91509 = false, _mut91510 = false, _mut91511 = false, _mut91512 = false, _mut91513 = false, _mut91514 = false, _mut91515 = false, _mut91516 = false, _mut91517 = false, _mut91518 = false, _mut91519 = false, _mut91520 = false;

    /**
     * Lower asymptote.
     */
    private final double lo;

    /**
     * Higher asymptote.
     */
    private final double hi;

    /**
     * Usual sigmoid function, where the lower asymptote is 0 and the higher
     * asymptote is 1.
     */
    public Sigmoid() {
        this(0, 1);
    }

    /**
     * Sigmoid function.
     *
     * @param lo Lower asymptote.
     * @param hi Higher asymptote.
     */
    public Sigmoid(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
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
     * {@inheritDoc}
     */
    public double value(double x) {
        return value(x, lo, hi);
    }

    /**
     * Parametric function where the input array contains the parameters of
     * the {@link Sigmoid#Sigmoid(double,double) sigmoid function}, ordered
     * as follows:
     * <ul>
     *  <li>Lower asymptote</li>
     *  <li>Higher asymptote</li>
     * </ul>
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * Computes the value of the sigmoid at {@code x}.
         *
         * @param x Value for which the function must be computed.
         * @param param Values of lower asymptote and higher asymptote.
         * @return the value of the function.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            return Sigmoid.value(x, param[0], param[1]);
        }

        /**
         * Computes the value of the gradient at {@code x}.
         * The components of the gradient vector are the partial
         * derivatives of the function with respect to each of the
         * <em>parameters</em> (lower asymptote and higher asymptote).
         *
         * @param x Value at which the gradient must be computed.
         * @param param Values for lower asymptote and higher asymptote.
         * @return the gradient vector at {@code x}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.gradient_120");
            validateParameters(param);
            final double invExp1 = AOR_divide(1, (AOR_plus(1, FastMath.exp(-x), "org.apache.commons.math3.analysis.function.Sigmoid.gradient_120", _mut91409, _mut91410, _mut91411, _mut91412)), "org.apache.commons.math3.analysis.function.Sigmoid.gradient_120", _mut91413, _mut91414, _mut91415, _mut91416);
            return new double[] { AOR_minus(1, invExp1, "org.apache.commons.math3.analysis.function.Sigmoid.gradient_120", _mut91417, _mut91418, _mut91419, _mut91420), invExp1 };
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double,double[])} and {@link #gradient(double,double[])}
         * methods.
         *
         * @param param Values for lower and higher asymptotes.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.validateParameters_140");
            if (param == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(param.length, 2, "org.apache.commons.math3.analysis.function.Sigmoid.validateParameters_140", _mut91421, _mut91422, _mut91423, _mut91424, _mut91425)) {
                throw new DimensionMismatchException(param.length, 2);
            }
        }
    }

    /**
     * @param x Value at which to compute the sigmoid.
     * @param lo Lower asymptote.
     * @param hi Higher asymptote.
     * @return the value of the sigmoid function at {@code x}.
     */
    private static double value(double x, double lo, double hi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.value_158");
        return AOR_plus(lo, AOR_divide((AOR_minus(hi, lo, "org.apache.commons.math3.analysis.function.Sigmoid.value_158", _mut91426, _mut91427, _mut91428, _mut91429)), (AOR_plus(1, FastMath.exp(-x), "org.apache.commons.math3.analysis.function.Sigmoid.value_158", _mut91430, _mut91431, _mut91432, _mut91433)), "org.apache.commons.math3.analysis.function.Sigmoid.value_158", _mut91434, _mut91435, _mut91436, _mut91437), "org.apache.commons.math3.analysis.function.Sigmoid.value_158", _mut91438, _mut91439, _mut91440, _mut91441);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.value_167");
        double[] f = new double[AOR_plus(t.getOrder(), 1, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91442, _mut91443, _mut91444, _mut91445)];
        final double exp = FastMath.exp(-t.getValue());
        if (Double.isInfinite(exp)) {
            // special handling near lower boundary, to avoid NaN
            f[0] = lo;
            Arrays.fill(f, 1, f.length, 0.0);
        } else {
            // P_n(x) = n t P_(n-1)(t) - t (1 + t) P_(n-1)'(t)
            final double[] p = new double[f.length];
            final double inv = AOR_divide(1, (AOR_plus(1, exp, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91446, _mut91447, _mut91448, _mut91449)), "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91450, _mut91451, _mut91452, _mut91453);
            double coeff = AOR_minus(hi, lo, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91454, _mut91455, _mut91456, _mut91457);
            for (int n = 0; ROR_less(n, f.length, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91516, _mut91517, _mut91518, _mut91519, _mut91520); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.value_167");
                // update and evaluate polynomial P_n(t)
                double v = 0;
                p[n] = 1;
                for (int k = n; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91507, _mut91508, _mut91509, _mut91510, _mut91511); --k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Sigmoid.value_167");
                    v = AOR_plus(AOR_multiply(v, exp, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91458, _mut91459, _mut91460, _mut91461), p[k], "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91462, _mut91463, _mut91464, _mut91465);
                    if (ROR_greater(k, 1, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91466, _mut91467, _mut91468, _mut91469, _mut91470)) {
                        p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91471, _mut91472, _mut91473, _mut91474)] = AOR_minus(AOR_multiply((AOR_plus(AOR_minus(n, k, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91475, _mut91476, _mut91477, _mut91478), 2, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91479, _mut91480, _mut91481, _mut91482)), p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91483, _mut91484, _mut91485, _mut91486)], "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91487, _mut91488, _mut91489, _mut91490), AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91491, _mut91492, _mut91493, _mut91494)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91495, _mut91496, _mut91497, _mut91498)], "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91499, _mut91500, _mut91501, _mut91502), "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91503, _mut91504, _mut91505, _mut91506);
                    } else {
                        p[0] = 0;
                    }
                }
                coeff *= inv;
                f[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.function.Sigmoid.value_167", _mut91512, _mut91513, _mut91514, _mut91515);
            }
            // fix function value
            f[0] += lo;
        }
        return t.compose(f);
    }
}
