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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Polynomial interpolator using both sample values and sample derivatives.
 * <p>
 * The interpolation polynomials match all sample points, including both values
 * and provided derivatives. There is one polynomial for each component of
 * the values vector. All polynomials have the same degree. The degree of the
 * polynomials depends on the number of points and number of derivatives at each
 * point. For example the interpolation polynomials for n sample points without
 * any derivatives all have degree n-1. The interpolation polynomials for n
 * sample points with the two extreme points having value and first derivative
 * and the remaining points having value only all have degree n+1. The
 * interpolation polynomial for n sample points with value, first and second
 * derivative for all points all have degree 3n-1.
 * </p>
 *
 * @param <T> Type of the field elements.
 *
 * @since 3.2
 */
public class FieldHermiteInterpolator<T extends FieldElement<T>> {

    @Conditional
    public static boolean _mut92328 = false, _mut92329 = false, _mut92330 = false, _mut92331 = false, _mut92332 = false, _mut92333 = false, _mut92334 = false, _mut92335 = false, _mut92336 = false, _mut92337 = false, _mut92338 = false, _mut92339 = false, _mut92340 = false, _mut92341 = false, _mut92342 = false, _mut92343 = false, _mut92344 = false, _mut92345 = false, _mut92346 = false, _mut92347 = false, _mut92348 = false, _mut92349 = false, _mut92350 = false, _mut92351 = false, _mut92352 = false, _mut92353 = false, _mut92354 = false, _mut92355 = false, _mut92356 = false, _mut92357 = false, _mut92358 = false, _mut92359 = false, _mut92360 = false, _mut92361 = false, _mut92362 = false, _mut92363 = false, _mut92364 = false, _mut92365 = false, _mut92366 = false, _mut92367 = false, _mut92368 = false, _mut92369 = false, _mut92370 = false, _mut92371 = false, _mut92372 = false, _mut92373 = false, _mut92374 = false, _mut92375 = false, _mut92376 = false, _mut92377 = false, _mut92378 = false, _mut92379 = false, _mut92380 = false, _mut92381 = false, _mut92382 = false, _mut92383 = false, _mut92384 = false, _mut92385 = false, _mut92386 = false, _mut92387 = false, _mut92388 = false, _mut92389 = false, _mut92390 = false, _mut92391 = false, _mut92392 = false, _mut92393 = false, _mut92394 = false, _mut92395 = false, _mut92396 = false, _mut92397 = false, _mut92398 = false, _mut92399 = false, _mut92400 = false, _mut92401 = false, _mut92402 = false, _mut92403 = false, _mut92404 = false, _mut92405 = false, _mut92406 = false, _mut92407 = false, _mut92408 = false, _mut92409 = false, _mut92410 = false, _mut92411 = false, _mut92412 = false, _mut92413 = false, _mut92414 = false, _mut92415 = false, _mut92416 = false, _mut92417 = false, _mut92418 = false, _mut92419 = false, _mut92420 = false, _mut92421 = false, _mut92422 = false, _mut92423 = false, _mut92424 = false, _mut92425 = false, _mut92426 = false, _mut92427 = false, _mut92428 = false, _mut92429 = false, _mut92430 = false, _mut92431 = false, _mut92432 = false, _mut92433 = false, _mut92434 = false, _mut92435 = false;

    /**
     * Sample abscissae.
     */
    private final List<T> abscissae;

    /**
     * Top diagonal of the divided differences array.
     */
    private final List<T[]> topDiagonal;

    /**
     * Bottom diagonal of the divided differences array.
     */
    private final List<T[]> bottomDiagonal;

    /**
     * Create an empty interpolator.
     */
    public FieldHermiteInterpolator() {
        this.abscissae = new ArrayList<T>();
        this.topDiagonal = new ArrayList<T[]>();
        this.bottomDiagonal = new ArrayList<T[]>();
    }

    /**
     * Add a sample point.
     * <p>
     * This method must be called once for each sample point. It is allowed to
     * mix some calls with values only with calls with values and first
     * derivatives.
     * </p>
     * <p>
     * The point abscissae for all calls <em>must</em> be different.
     * </p>
     * @param x abscissa of the sample point
     * @param value value and derivatives of the sample point
     * (if only one row is passed, it is the value, if two rows are
     * passed the first one is the value and the second the derivative
     * and so on)
     * @exception ZeroException if the abscissa difference between added point
     * and a previous point is zero (i.e. the two points are at same abscissa)
     * @exception MathArithmeticException if the number of derivatives is larger
     * than 20, which prevents computation of a factorial
     * @throws DimensionMismatchException if derivative structures are inconsistent
     * @throws NullArgumentException if x is null
     */
    public void addSamplePoint(final T x, final T[]... value) throws ZeroException, MathArithmeticException, DimensionMismatchException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90");
        MathUtils.checkNotNull(x);
        T factorial = x.getField().getOne();
        for (int i = 0; ROR_less(i, value.length, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92376, _mut92377, _mut92378, _mut92379, _mut92380); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90");
            final T[] y = value[i].clone();
            if (ROR_greater(i, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92328, _mut92329, _mut92330, _mut92331, _mut92332)) {
                factorial = factorial.multiply(i);
                final T inv = factorial.reciprocal();
                for (int j = 0; ROR_less(j, y.length, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92333, _mut92334, _mut92335, _mut92336, _mut92337); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90");
                    y[j] = y[j].multiply(inv);
                }
            }
            // update the bottom diagonal of the divided differences array
            final int n = abscissae.size();
            bottomDiagonal.add(AOR_minus(n, i, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92338, _mut92339, _mut92340, _mut92341), y);
            T[] bottom0 = y;
            for (int j = i; ROR_less(j, n, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92371, _mut92372, _mut92373, _mut92374, _mut92375); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90");
                final T[] bottom1 = bottomDiagonal.get(AOR_minus(n, (AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92342, _mut92343, _mut92344, _mut92345)), "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92346, _mut92347, _mut92348, _mut92349));
                if (x.equals(abscissae.get(AOR_minus(n, (AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92350, _mut92351, _mut92352, _mut92353)), "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92354, _mut92355, _mut92356, _mut92357)))) {
                    throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, x);
                }
                final T inv = x.subtract(abscissae.get(AOR_minus(n, (AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92358, _mut92359, _mut92360, _mut92361)), "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92362, _mut92363, _mut92364, _mut92365))).reciprocal();
                for (int k = 0; ROR_less(k, y.length, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90", _mut92366, _mut92367, _mut92368, _mut92369, _mut92370); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.addSamplePoint_90");
                    bottom1[k] = inv.multiply(bottom0[k].subtract(bottom1[k]));
                }
                bottom0 = bottom1;
            }
            // update the top diagonal of the divided differences array
            topDiagonal.add(bottom0.clone());
            // update the abscissae array
            abscissae.add(x);
        }
    }

    /**
     * Interpolate value at a specified abscissa.
     * @param x interpolation abscissa
     * @return interpolated value
     * @exception NoDataException if sample is empty
     * @throws NullArgumentException if x is null
     */
    public T[] value(T x) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.value_139");
        // safety check
        MathUtils.checkNotNull(x);
        if (abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        final T[] value = MathArrays.buildArray(x.getField(), topDiagonal.get(0).length);
        T valueCoeff = x.getField().getOne();
        for (int i = 0; ROR_less(i, topDiagonal.size(), "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.value_139", _mut92386, _mut92387, _mut92388, _mut92389, _mut92390); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.value_139");
            T[] dividedDifference = topDiagonal.get(i);
            for (int k = 0; ROR_less(k, value.length, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.value_139", _mut92381, _mut92382, _mut92383, _mut92384, _mut92385); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.value_139");
                value[k] = value[k].add(dividedDifference[k].multiply(valueCoeff));
            }
            final T deltaX = x.subtract(abscissae.get(i));
            valueCoeff = valueCoeff.multiply(deltaX);
        }
        return value;
    }

    /**
     * Interpolate value and first derivatives at a specified abscissa.
     * @param x interpolation abscissa
     * @param order maximum derivation order
     * @return interpolated value and derivatives (value in row 0,
     * 1<sup>st</sup> derivative in row 1, ... n<sup>th</sup> derivative in row n)
     * @exception NoDataException if sample is empty
     * @throws NullArgumentException if x is null
     */
    public T[][] derivatives(T x, int order) throws NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170");
        // safety check
        MathUtils.checkNotNull(x);
        if (abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
        final T zero = x.getField().getZero();
        final T one = x.getField().getOne();
        final T[] tj = MathArrays.buildArray(x.getField(), AOR_plus(order, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92391, _mut92392, _mut92393, _mut92394));
        tj[0] = zero;
        for (int i = 0; ROR_less(i, order, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92399, _mut92400, _mut92401, _mut92402, _mut92403); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170");
            tj[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92395, _mut92396, _mut92397, _mut92398)] = tj[i].add(one);
        }
        final T[][] derivatives = MathArrays.buildArray(x.getField(), AOR_plus(order, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92404, _mut92405, _mut92406, _mut92407), topDiagonal.get(0).length);
        final T[] valueCoeff = MathArrays.buildArray(x.getField(), AOR_plus(order, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92408, _mut92409, _mut92410, _mut92411));
        valueCoeff[0] = x.getField().getOne();
        for (int i = 0; ROR_less(i, topDiagonal.size(), "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92431, _mut92432, _mut92433, _mut92434, _mut92435); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170");
            T[] dividedDifference = topDiagonal.get(i);
            final T deltaX = x.subtract(abscissae.get(i));
            for (int j = order; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92426, _mut92427, _mut92428, _mut92429, _mut92430); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170");
                for (int k = 0; ROR_less(k, derivatives[j].length, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92412, _mut92413, _mut92414, _mut92415, _mut92416); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170");
                    derivatives[j][k] = derivatives[j][k].add(dividedDifference[k].multiply(valueCoeff[j]));
                }
                valueCoeff[j] = valueCoeff[j].multiply(deltaX);
                if (ROR_greater(j, 0, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92417, _mut92418, _mut92419, _mut92420, _mut92421)) {
                    valueCoeff[j] = valueCoeff[j].add(tj[j].multiply(valueCoeff[AOR_minus(j, 1, "org.apache.commons.math3.analysis.interpolation.FieldHermiteInterpolator.derivatives_170", _mut92422, _mut92423, _mut92424, _mut92425)]));
                }
            }
        }
        return derivatives;
    }
}
