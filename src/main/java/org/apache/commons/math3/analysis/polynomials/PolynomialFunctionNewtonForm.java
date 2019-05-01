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

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the representation of a real polynomial function in
 * Newton Form. For reference, see <b>Elementary Numerical Analysis</b>,
 * ISBN 0070124477, chapter 2.
 * <p>
 * The formula of polynomial in Newton form is
 *     p(x) = a[0] + a[1](x-c[0]) + a[2](x-c[0])(x-c[1]) + ... +
 *            a[n](x-c[0])(x-c[1])...(x-c[n-1])
 * Note that the length of a[] is one more than the length of c[]</p>
 *
 * @since 1.2
 */
public class PolynomialFunctionNewtonForm implements UnivariateDifferentiableFunction {

    @Conditional
    public static boolean _mut90879 = false, _mut90880 = false, _mut90881 = false, _mut90882 = false, _mut90883 = false, _mut90884 = false, _mut90885 = false, _mut90886 = false, _mut90887 = false, _mut90888 = false, _mut90889 = false, _mut90890 = false, _mut90891 = false, _mut90892 = false, _mut90893 = false, _mut90894 = false, _mut90895 = false, _mut90896 = false, _mut90897 = false, _mut90898 = false, _mut90899 = false, _mut90900 = false, _mut90901 = false, _mut90902 = false, _mut90903 = false, _mut90904 = false, _mut90905 = false, _mut90906 = false, _mut90907 = false, _mut90908 = false, _mut90909 = false, _mut90910 = false, _mut90911 = false, _mut90912 = false, _mut90913 = false, _mut90914 = false, _mut90915 = false, _mut90916 = false, _mut90917 = false, _mut90918 = false, _mut90919 = false, _mut90920 = false, _mut90921 = false, _mut90922 = false, _mut90923 = false, _mut90924 = false, _mut90925 = false, _mut90926 = false, _mut90927 = false, _mut90928 = false, _mut90929 = false, _mut90930 = false, _mut90931 = false, _mut90932 = false, _mut90933 = false, _mut90934 = false, _mut90935 = false, _mut90936 = false, _mut90937 = false, _mut90938 = false, _mut90939 = false, _mut90940 = false, _mut90941 = false, _mut90942 = false, _mut90943 = false, _mut90944 = false, _mut90945 = false, _mut90946 = false, _mut90947 = false, _mut90948 = false, _mut90949 = false, _mut90950 = false, _mut90951 = false, _mut90952 = false, _mut90953 = false, _mut90954 = false, _mut90955 = false, _mut90956 = false, _mut90957 = false, _mut90958 = false, _mut90959 = false;

    /**
     * The coefficients of the polynomial, ordered by degree -- i.e.
     * coefficients[0] is the constant term and coefficients[n] is the
     * coefficient of x^n where n is the degree of the polynomial.
     */
    private double[] coefficients;

    /**
     * Centers of the Newton polynomial.
     */
    private final double[] c;

    /**
     * When all c[i] = 0, a[] becomes normal polynomial coefficients,
     * i.e. a[i] = coefficients[i].
     */
    private final double[] a;

    /**
     * Whether the polynomial coefficients are available.
     */
    private boolean coefficientsComputed;

    /**
     * Construct a Newton polynomial with the given a[] and c[]. The order of
     * centers are important in that if c[] shuffle, then values of a[] would
     * completely change, not just a permutation of old a[].
     * <p>
     * The constructor makes copy of the input arrays and assigns them.</p>
     *
     * @param a Coefficients in Newton form formula.
     * @param c Centers.
     * @throws NullArgumentException if any argument is {@code null}.
     * @throws NoDataException if any array has zero length.
     * @throws DimensionMismatchException if the size difference between
     * {@code a} and {@code c} is not equal to 1.
     */
    public PolynomialFunctionNewtonForm(double[] a, double[] c) throws NullArgumentException, NoDataException, DimensionMismatchException {
        verifyInputArray(a, c);
        this.a = new double[a.length];
        this.c = new double[c.length];
        System.arraycopy(a, 0, this.a, 0, a.length);
        System.arraycopy(c, 0, this.c, 0, c.length);
        coefficientsComputed = false;
    }

    /**
     * Calculate the function value at the given point.
     *
     * @param z Point at which the function value is to be computed.
     * @return the function value.
     */
    public double value(double z) {
        return evaluate(a, c, z);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.value_103");
        verifyInputArray(a, c);
        final int n = c.length;
        DerivativeStructure value = new DerivativeStructure(t.getFreeParameters(), t.getOrder(), a[n]);
        for (int i = n - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.value_103", _mut90879, _mut90880, _mut90881, _mut90882, _mut90883); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.value_103");
            value = t.subtract(c[i]).multiply(value).add(a[i]);
        }
        return value;
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the degree of the polynomial
     */
    public int degree() {
        return c.length;
    }

    /**
     * Returns a copy of coefficients in Newton form formula.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     *
     * @return a fresh copy of coefficients in Newton form formula
     */
    public double[] getNewtonCoefficients() {
        double[] out = new double[a.length];
        System.arraycopy(a, 0, out, 0, a.length);
        return out;
    }

    /**
     * Returns a copy of the centers array.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     *
     * @return a fresh copy of the centers array.
     */
    public double[] getCenters() {
        double[] out = new double[c.length];
        System.arraycopy(c, 0, out, 0, c.length);
        return out;
    }

    /**
     * Returns a copy of the coefficients array.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     *
     * @return a fresh copy of the coefficients array.
     */
    public double[] getCoefficients() {
        if (!coefficientsComputed) {
            computeCoefficients();
        }
        double[] out = new double[coefficients.length];
        System.arraycopy(coefficients, 0, out, 0, coefficients.length);
        return out;
    }

    /**
     * Evaluate the Newton polynomial using nested multiplication. It is
     * also called <a href="http://mathworld.wolfram.com/HornersRule.html">
     * Horner's Rule</a> and takes O(N) time.
     *
     * @param a Coefficients in Newton form formula.
     * @param c Centers.
     * @param z Point at which the function value is to be computed.
     * @return the function value.
     * @throws NullArgumentException if any argument is {@code null}.
     * @throws NoDataException if any array has zero length.
     * @throws DimensionMismatchException if the size difference between
     * {@code a} and {@code c} is not equal to 1.
     */
    public static double evaluate(double[] a, double[] c, double z) throws NullArgumentException, DimensionMismatchException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181");
        verifyInputArray(a, c);
        final int n = c.length;
        double value = a[n];
        for (int i = n - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181", _mut90896, _mut90897, _mut90898, _mut90899, _mut90900); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181");
            value = AOR_plus(a[i], AOR_multiply((AOR_minus(z, c[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181", _mut90884, _mut90885, _mut90886, _mut90887)), value, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181", _mut90888, _mut90889, _mut90890, _mut90891), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.evaluate_181", _mut90892, _mut90893, _mut90894, _mut90895);
        }
        return value;
    }

    /**
     * Calculate the normal polynomial coefficients given the Newton form.
     * It also uses nested multiplication but takes O(N^2) time.
     */
    protected void computeCoefficients() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198");
        final int n = degree();
        coefficients = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90901, _mut90902, _mut90903, _mut90904)];
        for (int i = 0; ROR_less_equals(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90905, _mut90906, _mut90907, _mut90908, _mut90909); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198");
            coefficients[i] = 0.0;
        }
        coefficients[0] = a[n];
        for (int i = n - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90935, _mut90936, _mut90937, _mut90938, _mut90939); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198");
            for (int j = n - i; ROR_greater(j, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90922, _mut90923, _mut90924, _mut90925, _mut90926); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198");
                coefficients[j] = AOR_minus(coefficients[AOR_minus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90910, _mut90911, _mut90912, _mut90913)], AOR_multiply(c[i], coefficients[j], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90914, _mut90915, _mut90916, _mut90917), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90918, _mut90919, _mut90920, _mut90921);
            }
            coefficients[0] = AOR_minus(a[i], AOR_multiply(c[i], coefficients[0], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90927, _mut90928, _mut90929, _mut90930), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.computeCoefficients_198", _mut90931, _mut90932, _mut90933, _mut90934);
        }
        coefficientsComputed = true;
    }

    /**
     * Verifies that the input arrays are valid.
     * <p>
     * The centers must be distinct for interpolation purposes, but not
     * for general use. Thus it is not verified here.</p>
     *
     * @param a the coefficients in Newton form formula
     * @param c the centers
     * @throws NullArgumentException if any argument is {@code null}.
     * @throws NoDataException if any array has zero length.
     * @throws DimensionMismatchException if the size difference between
     * {@code a} and {@code c} is not equal to 1.
     * @see org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator#computeDividedDifference(double[],
     * double[])
     */
    protected static void verifyInputArray(double[] a, double[] c) throws NullArgumentException, NoDataException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232");
        MathUtils.checkNotNull(a);
        MathUtils.checkNotNull(c);
        if ((_mut90950 ? (ROR_equals(a.length, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90940, _mut90941, _mut90942, _mut90943, _mut90944) && ROR_equals(c.length, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90945, _mut90946, _mut90947, _mut90948, _mut90949)) : (ROR_equals(a.length, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90940, _mut90941, _mut90942, _mut90943, _mut90944) || ROR_equals(c.length, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90945, _mut90946, _mut90947, _mut90948, _mut90949)))) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        if (ROR_not_equals(a.length, AOR_plus(c.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90951, _mut90952, _mut90953, _mut90954), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm.verifyInputArray_232", _mut90955, _mut90956, _mut90957, _mut90958, _mut90959)) {
            throw new DimensionMismatchException(LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1, a.length, c.length);
        }
    }
}
