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

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Represents a polynomial spline function.
 * <p>
 * A <strong>polynomial spline function</strong> consists of a set of
 * <i>interpolating polynomials</i> and an ascending array of domain
 * <i>knot points</i>, determining the intervals over which the spline function
 * is defined by the constituent polynomials.  The polynomials are assumed to
 * have been computed to match the values of another function at the knot
 * points.  The value consistency constraints are not currently enforced by
 * <code>PolynomialSplineFunction</code> itself, but are assumed to hold among
 * the polynomials and knot points passed to the constructor.</p>
 * <p>
 * N.B.:  The polynomials in the <code>polynomials</code> property must be
 * centered on the knot points to compute the spline function values.
 * See below.</p>
 * <p>
 * The domain of the polynomial spline function is
 * <code>[smallest knot, largest knot]</code>.  Attempts to evaluate the
 * function at values outside of this range generate IllegalArgumentExceptions.
 * </p>
 * <p>
 * The value of the polynomial spline function for an argument <code>x</code>
 * is computed as follows:
 * <ol>
 * <li>The knot array is searched to find the segment to which <code>x</code>
 * belongs.  If <code>x</code> is less than the smallest knot point or greater
 * than the largest one, an <code>IllegalArgumentException</code>
 * is thrown.</li>
 * <li> Let <code>j</code> be the index of the largest knot point that is less
 * than or equal to <code>x</code>.  The value returned is
 * {@code polynomials[j](x - knot[j])}</li></ol>
 */
public class PolynomialSplineFunction implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut90078 = false, _mut90079 = false, _mut90080 = false, _mut90081 = false, _mut90082 = false, _mut90083 = false, _mut90084 = false, _mut90085 = false, _mut90086 = false, _mut90087 = false, _mut90088 = false, _mut90089 = false, _mut90090 = false, _mut90091 = false, _mut90092 = false, _mut90093 = false, _mut90094 = false, _mut90095 = false, _mut90096 = false, _mut90097 = false, _mut90098 = false, _mut90099 = false, _mut90100 = false, _mut90101 = false, _mut90102 = false, _mut90103 = false, _mut90104 = false, _mut90105 = false, _mut90106 = false, _mut90107 = false, _mut90108 = false, _mut90109 = false, _mut90110 = false, _mut90111 = false, _mut90112 = false, _mut90113 = false, _mut90114 = false, _mut90115 = false, _mut90116 = false, _mut90117 = false, _mut90118 = false, _mut90119 = false, _mut90120 = false, _mut90121 = false, _mut90122 = false, _mut90123 = false, _mut90124 = false, _mut90125 = false, _mut90126 = false, _mut90127 = false, _mut90128 = false, _mut90129 = false, _mut90130 = false, _mut90131 = false, _mut90132 = false, _mut90133 = false, _mut90134 = false, _mut90135 = false, _mut90136 = false, _mut90137 = false, _mut90138 = false, _mut90139 = false, _mut90140 = false, _mut90141 = false, _mut90142 = false, _mut90143 = false, _mut90144 = false, _mut90145 = false, _mut90146 = false, _mut90147 = false, _mut90148 = false, _mut90149 = false, _mut90150 = false, _mut90151 = false, _mut90152 = false, _mut90153 = false, _mut90154 = false, _mut90155 = false, _mut90156 = false, _mut90157 = false, _mut90158 = false, _mut90159 = false, _mut90160 = false, _mut90161 = false, _mut90162 = false, _mut90163 = false, _mut90164 = false, _mut90165 = false, _mut90166 = false, _mut90167 = false, _mut90168 = false, _mut90169 = false, _mut90170 = false, _mut90171 = false, _mut90172 = false, _mut90173 = false, _mut90174 = false, _mut90175 = false, _mut90176 = false, _mut90177 = false, _mut90178 = false, _mut90179 = false, _mut90180 = false, _mut90181 = false, _mut90182 = false;

    /**
     * Spline segment interval delimiters (knots).
     * Size is n + 1 for n segments.
     */
    private final double[] knots;

    /**
     * The polynomial functions that make up the spline.  The first element
     * determines the value of the spline over the first subinterval, the
     * second over the second, etc.   Spline function values are determined by
     * evaluating these functions at {@code (x - knot[i])} where i is the
     * knot segment to which x belongs.
     */
    private final PolynomialFunction[] polynomials;

    /**
     * Number of spline segments. It is equal to the number of polynomials and
     * to the number of partition points - 1.
     */
    private final int n;

    /**
     * Construct a polynomial spline function with the given segment delimiters
     * and interpolating polynomials.
     * The constructor copies both arrays and assigns the copies to the knots
     * and polynomials properties, respectively.
     *
     * @param knots Spline segment interval delimiters.
     * @param polynomials Polynomial functions that make up the spline.
     * @throws NullArgumentException if either of the input arrays is {@code null}.
     * @throws NumberIsTooSmallException if knots has length less than 2.
     * @throws DimensionMismatchException if {@code polynomials.length != knots.length - 1}.
     * @throws NonMonotonicSequenceException if the {@code knots} array is not strictly increasing.
     */
    public PolynomialSplineFunction(double[] knots, PolynomialFunction[] polynomials) throws NullArgumentException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101");
        if ((_mut90078 ? (knots == null && polynomials == null) : (knots == null || polynomials == null))) {
            throw new NullArgumentException();
        }
        if (ROR_less(knots.length, 2, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90079, _mut90080, _mut90081, _mut90082, _mut90083)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NOT_ENOUGH_POINTS_IN_SPLINE_PARTITION, 2, knots.length, false);
        }
        if (ROR_not_equals(AOR_minus(knots.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90084, _mut90085, _mut90086, _mut90087), polynomials.length, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90088, _mut90089, _mut90090, _mut90091, _mut90092)) {
            throw new DimensionMismatchException(polynomials.length, knots.length);
        }
        MathArrays.checkOrder(knots);
        this.n = AOR_minus(knots.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90093, _mut90094, _mut90095, _mut90096);
        this.knots = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90097, _mut90098, _mut90099, _mut90100)];
        System.arraycopy(knots, 0, this.knots, 0, AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.PolynomialSplineFunction_101", _mut90101, _mut90102, _mut90103, _mut90104));
        this.polynomials = new PolynomialFunction[n];
        System.arraycopy(polynomials, 0, this.polynomials, 0, n);
    }

    /**
     * Compute the value for the function.
     * See {@link PolynomialSplineFunction} for details on the algorithm for
     * computing the value of the function.
     *
     * @param v Point for which the function value should be computed.
     * @return the value.
     * @throws OutOfRangeException if {@code v} is outside of the domain of the
     * spline function (smaller than the smallest knot point or larger than the
     * largest knot point).
     */
    public double value(double v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135");
        if ((_mut90115 ? (ROR_less(v, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90105, _mut90106, _mut90107, _mut90108, _mut90109) && ROR_greater(v, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90110, _mut90111, _mut90112, _mut90113, _mut90114)) : (ROR_less(v, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90105, _mut90106, _mut90107, _mut90108, _mut90109) || ROR_greater(v, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90110, _mut90111, _mut90112, _mut90113, _mut90114)))) {
            throw new OutOfRangeException(v, knots[0], knots[n]);
        }
        int i = Arrays.binarySearch(knots, v);
        if (ROR_less(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90116, _mut90117, _mut90118, _mut90119, _mut90120)) {
            i = AOR_minus(-i, 2, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90121, _mut90122, _mut90123, _mut90124);
        }
        // then we will use the last polynomial to calculate the value.
        if (ROR_greater_equals(i, polynomials.length, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90125, _mut90126, _mut90127, _mut90128, _mut90129)) {
            i--;
        }
        return polynomials[i].value(AOR_minus(v, knots[i], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_135", _mut90130, _mut90131, _mut90132, _mut90133));
    }

    /**
     * Get the derivative of the polynomial spline function.
     *
     * @return the derivative function.
     */
    public UnivariateFunction derivative() {
        return polynomialSplineDerivative();
    }

    /**
     * Get the derivative of the polynomial spline function.
     *
     * @return the derivative function.
     */
    public PolynomialSplineFunction polynomialSplineDerivative() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.polynomialSplineDerivative_166");
        PolynomialFunction[] derivativePolynomials = new PolynomialFunction[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.polynomialSplineDerivative_166", _mut90134, _mut90135, _mut90136, _mut90137, _mut90138); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.polynomialSplineDerivative_166");
            derivativePolynomials[i] = polynomials[i].polynomialDerivative();
        }
        return new PolynomialSplineFunction(knots, derivativePolynomials);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178");
        final double t0 = t.getValue();
        if ((_mut90149 ? (ROR_less(t0, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90139, _mut90140, _mut90141, _mut90142, _mut90143) && ROR_greater(t0, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90144, _mut90145, _mut90146, _mut90147, _mut90148)) : (ROR_less(t0, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90139, _mut90140, _mut90141, _mut90142, _mut90143) || ROR_greater(t0, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90144, _mut90145, _mut90146, _mut90147, _mut90148)))) {
            throw new OutOfRangeException(t0, knots[0], knots[n]);
        }
        int i = Arrays.binarySearch(knots, t0);
        if (ROR_less(i, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90150, _mut90151, _mut90152, _mut90153, _mut90154)) {
            i = AOR_minus(-i, 2, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90155, _mut90156, _mut90157, _mut90158);
        }
        // then we will use the last polynomial to calculate the value.
        if (ROR_greater_equals(i, polynomials.length, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.value_178", _mut90159, _mut90160, _mut90161, _mut90162, _mut90163)) {
            i--;
        }
        return polynomials[i].value(t.subtract(knots[i]));
    }

    /**
     * Get the number of spline segments.
     * It is also the number of polynomials and the number of knot points - 1.
     *
     * @return the number of spline segments.
     */
    public int getN() {
        return n;
    }

    /**
     * Get a copy of the interpolating polynomials array.
     * It returns a fresh copy of the array. Changes made to the copy will
     * not affect the polynomials property.
     *
     * @return the interpolating polynomials.
     */
    public PolynomialFunction[] getPolynomials() {
        PolynomialFunction[] p = new PolynomialFunction[n];
        System.arraycopy(polynomials, 0, p, 0, n);
        return p;
    }

    /**
     * Get an array copy of the knot points.
     * It returns a fresh copy of the array. Changes made to the copy
     * will not affect the knots property.
     *
     * @return the knot points.
     */
    public double[] getKnots() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.getKnots_226");
        double[] out = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.getKnots_226", _mut90164, _mut90165, _mut90166, _mut90167)];
        System.arraycopy(knots, 0, out, 0, AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.getKnots_226", _mut90168, _mut90169, _mut90170, _mut90171));
        return out;
    }

    /**
     * Indicates whether a point is within the interpolation range.
     *
     * @param x Point.
     * @return {@code true} if {@code x} is a valid point.
     */
    public boolean isValidPoint(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.isValidPoint_238");
        if ((_mut90182 ? (ROR_less(x, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.isValidPoint_238", _mut90172, _mut90173, _mut90174, _mut90175, _mut90176) && ROR_greater(x, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.isValidPoint_238", _mut90177, _mut90178, _mut90179, _mut90180, _mut90181)) : (ROR_less(x, knots[0], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.isValidPoint_238", _mut90172, _mut90173, _mut90174, _mut90175, _mut90176) || ROR_greater(x, knots[n], "org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction.isValidPoint_238", _mut90177, _mut90178, _mut90179, _mut90180, _mut90181)))) {
            return false;
        } else {
            return true;
        }
    }
}
