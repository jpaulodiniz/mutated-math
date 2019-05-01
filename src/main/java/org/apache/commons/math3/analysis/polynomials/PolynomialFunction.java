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
package org.apache.commons.math3.analysis.polynomials;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Immutable representation of a real polynomial function with real coefficients.
 * <p>
 * <a href="http://mathworld.wolfram.com/HornersMethod.html">Horner's Method</a>
 * is used to evaluate the function.</p>
 */
public class PolynomialFunction implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction, Serializable {

    @Conditional
    public static boolean _mut90183 = false, _mut90184 = false, _mut90185 = false, _mut90186 = false, _mut90187 = false, _mut90188 = false, _mut90189 = false, _mut90190 = false, _mut90191 = false, _mut90192 = false, _mut90193 = false, _mut90194 = false, _mut90195 = false, _mut90196 = false, _mut90197 = false, _mut90198 = false, _mut90199 = false, _mut90200 = false, _mut90201 = false, _mut90202 = false, _mut90203 = false, _mut90204 = false, _mut90205 = false, _mut90206 = false, _mut90207 = false, _mut90208 = false, _mut90209 = false, _mut90210 = false, _mut90211 = false, _mut90212 = false, _mut90213 = false, _mut90214 = false, _mut90215 = false, _mut90216 = false, _mut90217 = false, _mut90218 = false, _mut90219 = false, _mut90220 = false, _mut90221 = false, _mut90222 = false, _mut90223 = false, _mut90224 = false, _mut90225 = false, _mut90226 = false, _mut90227 = false, _mut90228 = false, _mut90229 = false, _mut90230 = false, _mut90231 = false, _mut90232 = false, _mut90233 = false, _mut90234 = false, _mut90235 = false, _mut90236 = false, _mut90237 = false, _mut90238 = false, _mut90239 = false, _mut90240 = false, _mut90241 = false, _mut90242 = false, _mut90243 = false, _mut90244 = false, _mut90245 = false, _mut90246 = false, _mut90247 = false, _mut90248 = false, _mut90249 = false, _mut90250 = false, _mut90251 = false, _mut90252 = false, _mut90253 = false, _mut90254 = false, _mut90255 = false, _mut90256 = false, _mut90257 = false, _mut90258 = false, _mut90259 = false, _mut90260 = false, _mut90261 = false, _mut90262 = false, _mut90263 = false, _mut90264 = false, _mut90265 = false, _mut90266 = false, _mut90267 = false, _mut90268 = false, _mut90269 = false, _mut90270 = false, _mut90271 = false, _mut90272 = false, _mut90273 = false, _mut90274 = false, _mut90275 = false, _mut90276 = false, _mut90277 = false, _mut90278 = false, _mut90279 = false, _mut90280 = false, _mut90281 = false, _mut90282 = false, _mut90283 = false, _mut90284 = false, _mut90285 = false, _mut90286 = false, _mut90287 = false, _mut90288 = false, _mut90289 = false, _mut90290 = false, _mut90291 = false, _mut90292 = false, _mut90293 = false, _mut90294 = false, _mut90295 = false, _mut90296 = false, _mut90297 = false, _mut90298 = false, _mut90299 = false, _mut90300 = false, _mut90301 = false, _mut90302 = false, _mut90303 = false, _mut90304 = false, _mut90305 = false, _mut90306 = false, _mut90307 = false, _mut90308 = false, _mut90309 = false, _mut90310 = false, _mut90311 = false, _mut90312 = false, _mut90313 = false, _mut90314 = false, _mut90315 = false, _mut90316 = false, _mut90317 = false, _mut90318 = false, _mut90319 = false, _mut90320 = false, _mut90321 = false, _mut90322 = false, _mut90323 = false, _mut90324 = false, _mut90325 = false, _mut90326 = false, _mut90327 = false, _mut90328 = false, _mut90329 = false, _mut90330 = false, _mut90331 = false, _mut90332 = false, _mut90333 = false, _mut90334 = false, _mut90335 = false, _mut90336 = false, _mut90337 = false, _mut90338 = false, _mut90339 = false, _mut90340 = false, _mut90341 = false, _mut90342 = false, _mut90343 = false, _mut90344 = false, _mut90345 = false, _mut90346 = false, _mut90347 = false, _mut90348 = false, _mut90349 = false, _mut90350 = false, _mut90351 = false, _mut90352 = false, _mut90353 = false, _mut90354 = false, _mut90355 = false, _mut90356 = false, _mut90357 = false, _mut90358 = false, _mut90359 = false, _mut90360 = false, _mut90361 = false, _mut90362 = false, _mut90363 = false, _mut90364 = false, _mut90365 = false, _mut90366 = false, _mut90367 = false, _mut90368 = false, _mut90369 = false, _mut90370 = false, _mut90371 = false, _mut90372 = false, _mut90373 = false, _mut90374 = false, _mut90375 = false, _mut90376 = false, _mut90377 = false, _mut90378 = false, _mut90379 = false, _mut90380 = false, _mut90381 = false, _mut90382 = false, _mut90383 = false, _mut90384 = false, _mut90385 = false, _mut90386 = false, _mut90387 = false, _mut90388 = false, _mut90389 = false, _mut90390 = false, _mut90391 = false, _mut90392 = false, _mut90393 = false, _mut90394 = false, _mut90395 = false, _mut90396 = false, _mut90397 = false, _mut90398 = false, _mut90399 = false, _mut90400 = false, _mut90401 = false, _mut90402 = false, _mut90403 = false, _mut90404 = false, _mut90405 = false, _mut90406 = false, _mut90407 = false, _mut90408 = false, _mut90409 = false, _mut90410 = false, _mut90411 = false;

    /**
     * Serialization identifier
     */
    private static final long serialVersionUID = -7726511984200295583L;

    /**
     * The coefficients of the polynomial, ordered by degree -- i.e.,
     * coefficients[0] is the constant term and coefficients[n] is the
     * coefficient of x^n where n is the degree of the polynomial.
     */
    private final double[] coefficients;

    /**
     * Construct a polynomial with the given coefficients.  The first element
     * of the coefficients array is the constant term.  Higher degree
     * coefficients follow in sequence.  The degree of the resulting polynomial
     * is the index of the last non-null element of the array, or 0 if all elements
     * are null.
     * <p>
     * The constructor makes a copy of the input array and assigns the copy to
     * the coefficients property.</p>
     *
     * @param c Polynomial coefficients.
     * @throws NullArgumentException if {@code c} is {@code null}.
     * @throws NoDataException if {@code c} is empty.
     */
    public PolynomialFunction(double[] c) throws NullArgumentException, NoDataException {
        super();
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66");
        MathUtils.checkNotNull(c);
        int n = c.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90183, _mut90184, _mut90185, _mut90186, _mut90187)) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        while ((_mut90202 ? ((ROR_greater(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90188, _mut90189, _mut90190, _mut90191, _mut90192)) || (ROR_equals(c[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90193, _mut90194, _mut90195, _mut90196)], 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90197, _mut90198, _mut90199, _mut90200, _mut90201))) : ((ROR_greater(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90188, _mut90189, _mut90190, _mut90191, _mut90192)) && (ROR_equals(c[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90193, _mut90194, _mut90195, _mut90196)], 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66", _mut90197, _mut90198, _mut90199, _mut90200, _mut90201))))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.PolynomialFunction_66");
            --n;
        }
        this.coefficients = new double[n];
        System.arraycopy(c, 0, this.coefficients, 0, n);
    }

    /**
     * Compute the value of the function for the given argument.
     * <p>
     *  The value returned is </p><p>
     *  {@code coefficients[n] * x^n + ... + coefficients[1] * x  + coefficients[0]}
     * </p>
     *
     * @param x Argument for which the function value should be computed.
     * @return the value of the polynomial at the given point.
     * @see UnivariateFunction#value(double)
     */
    public double value(double x) {
        return evaluate(coefficients, x);
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the degree of the polynomial.
     */
    public int degree() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.degree_101");
        return AOR_minus(coefficients.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.degree_101", _mut90203, _mut90204, _mut90205, _mut90206);
    }

    /**
     * Returns a copy of the coefficients array.
     * <p>
     * Changes made to the returned copy will not affect the coefficients of
     * the polynomial.</p>
     *
     * @return a fresh copy of the coefficients array.
     */
    public double[] getCoefficients() {
        return coefficients.clone();
    }

    /**
     * Uses Horner's Method to evaluate the polynomial with the given coefficients at
     * the argument.
     *
     * @param coefficients Coefficients of the polynomial to evaluate.
     * @param argument Input value.
     * @return the value of the polynomial.
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    protected static double evaluate(double[] coefficients, double argument) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127");
        MathUtils.checkNotNull(coefficients);
        int n = coefficients.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127", _mut90207, _mut90208, _mut90209, _mut90210, _mut90211)) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        double result = coefficients[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127", _mut90212, _mut90213, _mut90214, _mut90215)];
        for (int j = n - 2; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127", _mut90224, _mut90225, _mut90226, _mut90227, _mut90228); j--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127");
            result = AOR_plus(AOR_multiply(argument, result, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127", _mut90216, _mut90217, _mut90218, _mut90219), coefficients[j], "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.evaluate_127", _mut90220, _mut90221, _mut90222, _mut90223);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    public DerivativeStructure value(final DerivativeStructure t) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.value_147");
        MathUtils.checkNotNull(coefficients);
        int n = coefficients.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.value_147", _mut90229, _mut90230, _mut90231, _mut90232, _mut90233)) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        DerivativeStructure result = new DerivativeStructure(t.getFreeParameters(), t.getOrder(), coefficients[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.value_147", _mut90234, _mut90235, _mut90236, _mut90237)]);
        for (int j = n - 2; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.value_147", _mut90238, _mut90239, _mut90240, _mut90241, _mut90242); j--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.value_147");
            result = result.multiply(t).add(coefficients[j]);
        }
        return result;
    }

    /**
     * Add a polynomial to the instance.
     *
     * @param p Polynomial to add.
     * @return a new polynomial which is the sum of the instance and {@code p}.
     */
    public PolynomialFunction add(final PolynomialFunction p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168");
        // identify the lowest degree polynomial
        final int lowLength = FastMath.min(coefficients.length, p.coefficients.length);
        final int highLength = FastMath.max(coefficients.length, p.coefficients.length);
        // build the coefficients array
        double[] newCoefficients = new double[highLength];
        for (int i = 0; ROR_less(i, lowLength, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168", _mut90247, _mut90248, _mut90249, _mut90250, _mut90251); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168");
            newCoefficients[i] = AOR_plus(coefficients[i], p.coefficients[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168", _mut90243, _mut90244, _mut90245, _mut90246);
        }
        System.arraycopy((ROR_less(coefficients.length, p.coefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168", _mut90252, _mut90253, _mut90254, _mut90255, _mut90256)) ? p.coefficients : coefficients, lowLength, newCoefficients, lowLength, AOR_minus(highLength, lowLength, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.add_168", _mut90257, _mut90258, _mut90259, _mut90260));
        return new PolynomialFunction(newCoefficients);
    }

    /**
     * Subtract a polynomial from the instance.
     *
     * @param p Polynomial to subtract.
     * @return a new polynomial which is the instance minus {@code p}.
     */
    public PolynomialFunction subtract(final PolynomialFunction p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193");
        // identify the lowest degree polynomial
        int lowLength = FastMath.min(coefficients.length, p.coefficients.length);
        int highLength = FastMath.max(coefficients.length, p.coefficients.length);
        // build the coefficients array
        double[] newCoefficients = new double[highLength];
        for (int i = 0; ROR_less(i, lowLength, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193", _mut90265, _mut90266, _mut90267, _mut90268, _mut90269); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193");
            newCoefficients[i] = AOR_minus(coefficients[i], p.coefficients[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193", _mut90261, _mut90262, _mut90263, _mut90264);
        }
        if (ROR_less(coefficients.length, p.coefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193", _mut90270, _mut90271, _mut90272, _mut90273, _mut90274)) {
            for (int i = lowLength; ROR_less(i, highLength, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193", _mut90279, _mut90280, _mut90281, _mut90282, _mut90283); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193");
                newCoefficients[i] = -p.coefficients[i];
            }
        } else {
            System.arraycopy(coefficients, lowLength, newCoefficients, lowLength, AOR_minus(highLength, lowLength, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.subtract_193", _mut90275, _mut90276, _mut90277, _mut90278));
        }
        return new PolynomialFunction(newCoefficients);
    }

    /**
     * Negate the instance.
     *
     * @return a new polynomial with all coefficients negated
     */
    public PolynomialFunction negate() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.negate_220");
        double[] newCoefficients = new double[coefficients.length];
        for (int i = 0; ROR_less(i, coefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.negate_220", _mut90284, _mut90285, _mut90286, _mut90287, _mut90288); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.negate_220");
            newCoefficients[i] = -coefficients[i];
        }
        return new PolynomialFunction(newCoefficients);
    }

    /**
     * Multiply the instance by a polynomial.
     *
     * @param p Polynomial to multiply by.
     * @return a new polynomial equal to this times {@code p}
     */
    public PolynomialFunction multiply(final PolynomialFunction p) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234");
        double[] newCoefficients = new double[AOR_minus(AOR_plus(coefficients.length, p.coefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90289, _mut90290, _mut90291, _mut90292), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90293, _mut90294, _mut90295, _mut90296)];
        for (int i = 0; ROR_less(i, newCoefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90314, _mut90315, _mut90316, _mut90317, _mut90318); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234");
            newCoefficients[i] = 0.0;
            for (int j = FastMath.max(0, i + 1 - p.coefficients.length); ROR_less(j, FastMath.min(coefficients.length, AOR_plus(i, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90305, _mut90306, _mut90307, _mut90308)), "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90309, _mut90310, _mut90311, _mut90312, _mut90313); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234");
                newCoefficients[i] += AOR_multiply(coefficients[j], p.coefficients[AOR_minus(i, j, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90297, _mut90298, _mut90299, _mut90300)], "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.multiply_234", _mut90301, _mut90302, _mut90303, _mut90304);
            }
        }
        return new PolynomialFunction(newCoefficients);
    }

    /**
     * Returns the coefficients of the derivative of the polynomial with the given coefficients.
     *
     * @param coefficients Coefficients of the polynomial to differentiate.
     * @return the coefficients of the derivative or {@code null} if coefficients has length 1.
     * @throws NoDataException if {@code coefficients} is empty.
     * @throws NullArgumentException if {@code coefficients} is {@code null}.
     */
    protected static double[] differentiate(double[] coefficients) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257");
        MathUtils.checkNotNull(coefficients);
        int n = coefficients.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90319, _mut90320, _mut90321, _mut90322, _mut90323)) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        if (ROR_equals(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90324, _mut90325, _mut90326, _mut90327, _mut90328)) {
            return new double[] { 0 };
        }
        double[] result = new double[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90329, _mut90330, _mut90331, _mut90332)];
        for (int i = n - 1; ROR_greater(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90341, _mut90342, _mut90343, _mut90344, _mut90345); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257");
            result[AOR_minus(i, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90333, _mut90334, _mut90335, _mut90336)] = AOR_multiply(i, coefficients[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.differentiate_257", _mut90337, _mut90338, _mut90339, _mut90340);
        }
        return result;
    }

    /**
     * Returns the derivative as a {@link PolynomialFunction}.
     *
     * @return the derivative polynomial.
     */
    public PolynomialFunction polynomialDerivative() {
        return new PolynomialFunction(differentiate(coefficients));
    }

    /**
     * Returns the derivative as a {@link UnivariateFunction}.
     *
     * @return the derivative function.
     */
    public UnivariateFunction derivative() {
        return polynomialDerivative();
    }

    /**
     * Returns a string representation of the polynomial.
     *
     * <p>The representation is user oriented. Terms are displayed lowest
     * degrees first. The multiplications signs, coefficients equals to
     * one and null terms are not displayed (except if the polynomial is 0,
     * in which case the 0 constant term is displayed). Addition of terms
     * with negative coefficients are replaced by subtraction of terms
     * with positive coefficients except for the first displayed term
     * (i.e. we display <code>-3</code> for a constant negative polynomial,
     * but <code>1 - 3 x + x^2</code> if the negative coefficient is not
     * the first one displayed).</p>
     *
     * @return a string representation of the polynomial.
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307");
        StringBuilder s = new StringBuilder();
        if (ROR_equals(coefficients[0], 0.0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90346, _mut90347, _mut90348, _mut90349, _mut90350)) {
            if (ROR_equals(coefficients.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90351, _mut90352, _mut90353, _mut90354, _mut90355)) {
                return "0";
            }
        } else {
            s.append(toString(coefficients[0]));
        }
        for (int i = 1; ROR_less(i, coefficients.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90390, _mut90391, _mut90392, _mut90393, _mut90394); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307");
            if (ROR_not_equals(coefficients[i], 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90356, _mut90357, _mut90358, _mut90359, _mut90360)) {
                if (ROR_greater(s.length(), 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90361, _mut90362, _mut90363, _mut90364, _mut90365)) {
                    if (ROR_less(coefficients[i], 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90371, _mut90372, _mut90373, _mut90374, _mut90375)) {
                        s.append(" - ");
                    } else {
                        s.append(" + ");
                    }
                } else {
                    if (ROR_less(coefficients[i], 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90366, _mut90367, _mut90368, _mut90369, _mut90370)) {
                        s.append("-");
                    }
                }
                double absAi = FastMath.abs(coefficients[i]);
                if (ROR_not_equals((AOR_minus(absAi, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90376, _mut90377, _mut90378, _mut90379)), 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90380, _mut90381, _mut90382, _mut90383, _mut90384)) {
                    s.append(toString(absAi));
                    s.append(' ');
                }
                s.append("x");
                if (ROR_greater(i, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_307", _mut90385, _mut90386, _mut90387, _mut90388, _mut90389)) {
                    s.append('^');
                    s.append(Integer.toString(i));
                }
            }
        }
        return s.toString();
    }

    /**
     * Creates a string representing a coefficient, removing ".0" endings.
     *
     * @param coeff Coefficient.
     * @return a string representation of {@code coeff}.
     */
    private static String toString(double coeff) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_355");
        final String c = Double.toString(coeff);
        if (c.endsWith(".0")) {
            return c.substring(0, AOR_minus(c.length(), 2, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.toString_355", _mut90395, _mut90396, _mut90397, _mut90398));
        } else {
            return c;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.hashCode_365");
        final int prime = 31;
        int result = 1;
        result = AOR_plus(AOR_multiply(prime, result, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.hashCode_365", _mut90399, _mut90400, _mut90401, _mut90402), Arrays.hashCode(coefficients), "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.hashCode_365", _mut90403, _mut90404, _mut90405, _mut90406);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolynomialFunction)) {
            return false;
        }
        PolynomialFunction other = (PolynomialFunction) obj;
        if (!Arrays.equals(coefficients, other.coefficients)) {
            return false;
        }
        return true;
    }

    /**
     * Dedicated parametric polynomial class.
     *
     * @since 3.0
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * {@inheritDoc}
         */
        public double[] gradient(double x, double... parameters) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.gradient_396");
            final double[] gradient = new double[parameters.length];
            double xn = 1.0;
            for (int i = 0; ROR_less(i, parameters.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunction.gradient_396", _mut90407, _mut90408, _mut90409, _mut90410, _mut90411); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunction.gradient_396");
                gradient[i] = xn;
                xn *= x;
            }
            return gradient;
        }

        /**
         * {@inheritDoc}
         */
        public double value(final double x, final double... parameters) throws NoDataException {
            return PolynomialFunction.evaluate(parameters, x);
        }
    }
}
