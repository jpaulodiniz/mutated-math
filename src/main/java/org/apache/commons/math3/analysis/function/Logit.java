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
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Logit">
 *  Logit</a> function.
 * It is the inverse of the {@link Sigmoid sigmoid} function.
 *
 * @since 3.0
 */
public class Logit implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut91169 = false, _mut91170 = false, _mut91171 = false, _mut91172 = false, _mut91173 = false, _mut91174 = false, _mut91175 = false, _mut91176 = false, _mut91177 = false, _mut91178 = false, _mut91179 = false, _mut91180 = false, _mut91181 = false, _mut91182 = false, _mut91183 = false, _mut91184 = false, _mut91185 = false, _mut91186 = false, _mut91187 = false, _mut91188 = false, _mut91189 = false, _mut91190 = false, _mut91191 = false, _mut91192 = false, _mut91193 = false, _mut91194 = false, _mut91195 = false, _mut91196 = false, _mut91197 = false, _mut91198 = false, _mut91199 = false, _mut91200 = false, _mut91201 = false, _mut91202 = false, _mut91203 = false, _mut91204 = false, _mut91205 = false, _mut91206 = false, _mut91207 = false, _mut91208 = false, _mut91209 = false, _mut91210 = false, _mut91211 = false, _mut91212 = false, _mut91213 = false, _mut91214 = false, _mut91215 = false, _mut91216 = false, _mut91217 = false, _mut91218 = false, _mut91219 = false, _mut91220 = false, _mut91221 = false, _mut91222 = false, _mut91223 = false, _mut91224 = false, _mut91225 = false, _mut91226 = false, _mut91227 = false, _mut91228 = false, _mut91229 = false, _mut91230 = false, _mut91231 = false, _mut91232 = false, _mut91233 = false, _mut91234 = false, _mut91235 = false, _mut91236 = false, _mut91237 = false, _mut91238 = false, _mut91239 = false, _mut91240 = false, _mut91241 = false, _mut91242 = false, _mut91243 = false, _mut91244 = false, _mut91245 = false, _mut91246 = false, _mut91247 = false, _mut91248 = false, _mut91249 = false, _mut91250 = false, _mut91251 = false, _mut91252 = false, _mut91253 = false, _mut91254 = false, _mut91255 = false, _mut91256 = false, _mut91257 = false, _mut91258 = false, _mut91259 = false, _mut91260 = false, _mut91261 = false, _mut91262 = false, _mut91263 = false, _mut91264 = false, _mut91265 = false, _mut91266 = false, _mut91267 = false, _mut91268 = false, _mut91269 = false, _mut91270 = false, _mut91271 = false, _mut91272 = false, _mut91273 = false, _mut91274 = false, _mut91275 = false, _mut91276 = false, _mut91277 = false, _mut91278 = false, _mut91279 = false, _mut91280 = false, _mut91281 = false, _mut91282 = false, _mut91283 = false, _mut91284 = false, _mut91285 = false, _mut91286 = false;

    /**
     * Lower bound.
     */
    private final double lo;

    /**
     * Higher bound.
     */
    private final double hi;

    /**
     * Usual logit function, where the lower bound is 0 and the higher
     * bound is 1.
     */
    public Logit() {
        this(0, 1);
    }

    /**
     * Logit function.
     *
     * @param lo Lower bound of the function domain.
     * @param hi Higher bound of the function domain.
     */
    public Logit(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x) throws OutOfRangeException {
        return value(x, lo, hi);
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
     * the logit function, ordered as follows:
     * <ul>
     *  <li>Lower bound</li>
     *  <li>Higher bound</li>
     * </ul>
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * Computes the value of the logit at {@code x}.
         *
         * @param x Value for which the function must be computed.
         * @param param Values of lower bound and higher bounds.
         * @return the value of the function.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            return Logit.value(x, param[0], param[1]);
        }

        /**
         * Computes the value of the gradient at {@code x}.
         * The components of the gradient vector are the partial
         * derivatives of the function with respect to each of the
         * <em>parameters</em> (lower bound and higher bound).
         *
         * @param x Value at which the gradient must be computed.
         * @param param Values for lower and higher bounds.
         * @return the gradient vector at {@code x}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.gradient_117");
            validateParameters(param);
            final double lo = param[0];
            final double hi = param[1];
            return new double[] { AOR_divide(1, (AOR_minus(lo, x, "org.apache.commons.math3.analysis.function.Logit.gradient_117", _mut91169, _mut91170, _mut91171, _mut91172)), "org.apache.commons.math3.analysis.function.Logit.gradient_117", _mut91173, _mut91174, _mut91175, _mut91176), AOR_divide(1, (AOR_minus(hi, x, "org.apache.commons.math3.analysis.function.Logit.gradient_117", _mut91177, _mut91178, _mut91179, _mut91180)), "org.apache.commons.math3.analysis.function.Logit.gradient_117", _mut91181, _mut91182, _mut91183, _mut91184) };
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double,double[])} and {@link #gradient(double,double[])}
         * methods.
         *
         * @param param Values for lower and higher bounds.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 2.
         */
        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.validateParameters_138");
            if (param == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(param.length, 2, "org.apache.commons.math3.analysis.function.Logit.validateParameters_138", _mut91185, _mut91186, _mut91187, _mut91188, _mut91189)) {
                throw new DimensionMismatchException(param.length, 2);
            }
        }
    }

    /**
     * @param x Value at which to compute the logit.
     * @param lo Lower bound.
     * @param hi Higher bound.
     * @return the value of the logit function at {@code x}.
     * @throws OutOfRangeException if {@code x < lo} or {@code x > hi}.
     */
    private static double value(double x, double lo, double hi) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.value_157");
        if ((_mut91200 ? (ROR_less(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91190, _mut91191, _mut91192, _mut91193, _mut91194) && ROR_greater(x, hi, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91195, _mut91196, _mut91197, _mut91198, _mut91199)) : (ROR_less(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91190, _mut91191, _mut91192, _mut91193, _mut91194) || ROR_greater(x, hi, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91195, _mut91196, _mut91197, _mut91198, _mut91199)))) {
            throw new OutOfRangeException(x, lo, hi);
        }
        return FastMath.log(AOR_divide((AOR_minus(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91201, _mut91202, _mut91203, _mut91204)), (AOR_minus(hi, x, "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91205, _mut91206, _mut91207, _mut91208)), "org.apache.commons.math3.analysis.function.Logit.value_157", _mut91209, _mut91210, _mut91211, _mut91212));
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     * @exception OutOfRangeException if parameter is outside of function domain
     */
    public DerivativeStructure value(final DerivativeStructure t) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.value_171");
        final double x = t.getValue();
        if ((_mut91223 ? (ROR_less(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91213, _mut91214, _mut91215, _mut91216, _mut91217) && ROR_greater(x, hi, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91218, _mut91219, _mut91220, _mut91221, _mut91222)) : (ROR_less(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91213, _mut91214, _mut91215, _mut91216, _mut91217) || ROR_greater(x, hi, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91218, _mut91219, _mut91220, _mut91221, _mut91222)))) {
            throw new OutOfRangeException(x, lo, hi);
        }
        double[] f = new double[AOR_plus(t.getOrder(), 1, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91224, _mut91225, _mut91226, _mut91227)];
        // function value
        f[0] = FastMath.log(AOR_divide((AOR_minus(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91228, _mut91229, _mut91230, _mut91231)), (AOR_minus(hi, x, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91232, _mut91233, _mut91234, _mut91235)), "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91236, _mut91237, _mut91238, _mut91239));
        if (Double.isInfinite(f[0])) {
            if (ROR_greater(f.length, 1, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91273, _mut91274, _mut91275, _mut91276, _mut91277)) {
                f[1] = Double.POSITIVE_INFINITY;
            }
            // of the method will transform most infinities into NaN ...
            for (int i = 2; ROR_less(i, f.length, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91282, _mut91283, _mut91284, _mut91285, _mut91286); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.value_171");
                f[i] = f[AOR_minus(i, 2, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91278, _mut91279, _mut91280, _mut91281)];
            }
        } else {
            // function derivatives
            final double invL = AOR_divide(1.0, (AOR_minus(x, lo, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91240, _mut91241, _mut91242, _mut91243)), "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91244, _mut91245, _mut91246, _mut91247);
            double xL = invL;
            final double invH = AOR_divide(1.0, (AOR_minus(hi, x, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91248, _mut91249, _mut91250, _mut91251)), "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91252, _mut91253, _mut91254, _mut91255);
            double xH = invH;
            for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91268, _mut91269, _mut91270, _mut91271, _mut91272); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logit.value_171");
                f[i] = AOR_plus(xL, xH, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91256, _mut91257, _mut91258, _mut91259);
                xL *= AOR_multiply(-i, invL, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91260, _mut91261, _mut91262, _mut91263);
                xH *= AOR_multiply(i, invH, "org.apache.commons.math3.analysis.function.Logit.value_171", _mut91264, _mut91265, _mut91266, _mut91267);
            }
        }
        return t.compose(f);
    }
}
