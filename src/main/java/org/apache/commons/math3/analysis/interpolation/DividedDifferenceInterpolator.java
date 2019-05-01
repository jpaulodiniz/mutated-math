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
package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href=
 * "http://mathworld.wolfram.com/NewtonsDividedDifferenceInterpolationFormula.html">
 * Divided Difference Algorithm</a> for interpolation of real univariate
 * functions. For reference, see <b>Introduction to Numerical Analysis</b>,
 * ISBN 038795452X, chapter 2.
 * <p>
 * The actual code of Neville's evaluation is in PolynomialFunctionLagrangeForm,
 * this class provides an easy-to-use interface to it.</p>
 *
 * @since 1.2
 */
public class DividedDifferenceInterpolator implements UnivariateInterpolator, Serializable {

    @Conditional
    public static boolean _mut92436 = false, _mut92437 = false, _mut92438 = false, _mut92439 = false, _mut92440 = false, _mut92441 = false, _mut92442 = false, _mut92443 = false, _mut92444 = false, _mut92445 = false, _mut92446 = false, _mut92447 = false, _mut92448 = false, _mut92449 = false, _mut92450 = false, _mut92451 = false, _mut92452 = false, _mut92453 = false, _mut92454 = false, _mut92455 = false, _mut92456 = false, _mut92457 = false, _mut92458 = false, _mut92459 = false, _mut92460 = false, _mut92461 = false, _mut92462 = false, _mut92463 = false, _mut92464 = false, _mut92465 = false, _mut92466 = false, _mut92467 = false, _mut92468 = false, _mut92469 = false, _mut92470 = false, _mut92471 = false, _mut92472 = false, _mut92473 = false;

    /**
     * serializable version identifier
     */
    private static final long serialVersionUID = 107049519551235069L;

    /**
     * Compute an interpolating function for the dataset.
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @return a function which interpolates the dataset.
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws NonMonotonicSequenceException if {@code x} is not sorted in
     * strictly increasing order.
     */
    public PolynomialFunctionNewtonForm interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.interpolate_54");
        /**
         * a[] and c[] are defined in the general formula of Newton form:
         * p(x) = a[0] + a[1](x-c[0]) + a[2](x-c[0])(x-c[1]) + ... +
         *        a[n](x-c[0])(x-c[1])...(x-c[n-1])
         */
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
        /**
         * When used for interpolation, the Newton form formula becomes
         * p(x) = f[x0] + f[x0,x1](x-x0) + f[x0,x1,x2](x-x0)(x-x1) + ... +
         *        f[x0,x1,...,x[n-1]](x-x0)(x-x1)...(x-x[n-2])
         * Therefore, a[k] = f[x0,x1,...,xk], c[k] = x[k].
         * <p>
         * Note x[], y[], a[] have the same length but c[]'s size is one less.</p>
         */
        final double[] c = new double[AOR_minus(x.length, 1, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.interpolate_54", _mut92436, _mut92437, _mut92438, _mut92439)];
        System.arraycopy(x, 0, c, 0, c.length);
        final double[] a = computeDividedDifference(x, y);
        return new PolynomialFunctionNewtonForm(a, c);
    }

    /**
     * Return a copy of the divided difference array.
     * <p>
     * The divided difference array is defined recursively by <pre>
     * f[x0] = f(x0)
     * f[x0,x1,...,xk] = (f[x1,...,xk] - f[x0,...,x[k-1]]) / (xk - x0)
     * </pre>
     * <p>
     * The computational complexity is \(O(n^2)\) where \(n\) is the common
     * length of {@code x} and {@code y}.</p>
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @return a fresh copy of the divided difference array.
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order.
     */
    protected static double[] computeDividedDifference(final double[] x, final double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99");
        PolynomialFunctionLagrangeForm.verifyInterpolationArray(x, y, true);
        // initialization
        final double[] divdiff = y.clone();
        final int n = x.length;
        final double[] a = new double[n];
        a[0] = divdiff[0];
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92469, _mut92470, _mut92471, _mut92472, _mut92473); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99");
            for (int j = 0; ROR_less(j, AOR_minus(n, i, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92460, _mut92461, _mut92462, _mut92463), "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92464, _mut92465, _mut92466, _mut92467, _mut92468); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99");
                final double denominator = AOR_minus(x[AOR_plus(j, i, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92440, _mut92441, _mut92442, _mut92443)], x[j], "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92444, _mut92445, _mut92446, _mut92447);
                divdiff[j] = AOR_divide((AOR_minus(divdiff[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92448, _mut92449, _mut92450, _mut92451)], divdiff[j], "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92452, _mut92453, _mut92454, _mut92455)), denominator, "org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator.computeDividedDifference_99", _mut92456, _mut92457, _mut92458, _mut92459);
            }
            a[i] = divdiff[0];
        }
        return a;
    }
}
