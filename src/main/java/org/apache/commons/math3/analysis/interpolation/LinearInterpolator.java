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

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements a linear function for interpolation of real univariate functions.
 */
public class LinearInterpolator implements UnivariateInterpolator {

    @Conditional
    public static boolean _mut95798 = false, _mut95799 = false, _mut95800 = false, _mut95801 = false, _mut95802 = false, _mut95803 = false, _mut95804 = false, _mut95805 = false, _mut95806 = false, _mut95807 = false, _mut95808 = false, _mut95809 = false, _mut95810 = false, _mut95811 = false, _mut95812 = false, _mut95813 = false, _mut95814 = false, _mut95815 = false, _mut95816 = false, _mut95817 = false, _mut95818 = false, _mut95819 = false, _mut95820 = false, _mut95821 = false, _mut95822 = false, _mut95823 = false, _mut95824 = false, _mut95825 = false, _mut95826 = false, _mut95827 = false, _mut95828 = false, _mut95829 = false, _mut95830 = false, _mut95831 = false, _mut95832 = false, _mut95833 = false, _mut95834 = false, _mut95835 = false, _mut95836 = false, _mut95837 = false, _mut95838 = false, _mut95839 = false, _mut95840 = false, _mut95841 = false;

    /**
     * Computes a linear interpolating function for the data set.
     *
     * @param x the arguments for the interpolation points
     * @param y the values for the interpolation points
     * @return a function which interpolates the data set
     * @throws DimensionMismatchException if {@code x} and {@code y}
     * have different sizes.
     * @throws NonMonotonicSequenceException if {@code x} is not sorted in
     * strict increasing order.
     * @throws NumberIsTooSmallException if the size of {@code x} is smaller
     * than 2.
     */
    public PolynomialSplineFunction interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45");
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95798, _mut95799, _mut95800, _mut95801, _mut95802)) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (ROR_less(x.length, 2, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95803, _mut95804, _mut95805, _mut95806, _mut95807)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, x.length, 2, true);
        }
        // Number of intervals.  The number of data points is n + 1.
        int n = AOR_minus(x.length, 1, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95808, _mut95809, _mut95810, _mut95811);
        MathArrays.checkOrder(x);
        // Slope of the lines between the datapoints.
        final double[] m = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95832, _mut95833, _mut95834, _mut95835, _mut95836); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45");
            m[i] = AOR_divide((AOR_minus(y[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95812, _mut95813, _mut95814, _mut95815)], y[i], "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95816, _mut95817, _mut95818, _mut95819)), (AOR_minus(x[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95820, _mut95821, _mut95822, _mut95823)], x[i], "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95824, _mut95825, _mut95826, _mut95827)), "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95828, _mut95829, _mut95830, _mut95831);
        }
        final PolynomialFunction[] polynomials = new PolynomialFunction[n];
        final double[] coefficients = new double[2];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45", _mut95837, _mut95838, _mut95839, _mut95840, _mut95841); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LinearInterpolator.interpolate_45");
            coefficients[0] = y[i];
            coefficients[1] = m[i];
            polynomials[i] = new PolynomialFunction(coefficients);
        }
        return new PolynomialSplineFunction(x, polynomials);
    }
}
