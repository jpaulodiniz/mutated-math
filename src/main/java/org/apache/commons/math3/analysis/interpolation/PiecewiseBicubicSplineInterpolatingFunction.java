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

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Function that implements the
 * <a href="http://www.paulinternet.nl/?page=bicubic">bicubic spline</a>
 * interpolation.
 * This implementation currently uses {@link AkimaSplineInterpolator} as the
 * underlying one-dimensional interpolator, which requires 5 sample points;
 * insufficient data will raise an exception when the
 * {@link #value(double,double) value} method is called.
 *
 * @since 3.4
 */
public class PiecewiseBicubicSplineInterpolatingFunction implements BivariateFunction {

    @Conditional
    public static boolean _mut96072 = false, _mut96073 = false, _mut96074 = false, _mut96075 = false, _mut96076 = false, _mut96077 = false, _mut96078 = false, _mut96079 = false, _mut96080 = false, _mut96081 = false, _mut96082 = false, _mut96083 = false, _mut96084 = false, _mut96085 = false, _mut96086 = false, _mut96087 = false, _mut96088 = false, _mut96089 = false, _mut96090 = false, _mut96091 = false, _mut96092 = false, _mut96093 = false, _mut96094 = false, _mut96095 = false, _mut96096 = false, _mut96097 = false, _mut96098 = false, _mut96099 = false, _mut96100 = false, _mut96101 = false, _mut96102 = false, _mut96103 = false, _mut96104 = false, _mut96105 = false, _mut96106 = false, _mut96107 = false, _mut96108 = false, _mut96109 = false, _mut96110 = false, _mut96111 = false, _mut96112 = false, _mut96113 = false, _mut96114 = false, _mut96115 = false, _mut96116 = false, _mut96117 = false, _mut96118 = false, _mut96119 = false, _mut96120 = false, _mut96121 = false, _mut96122 = false, _mut96123 = false, _mut96124 = false, _mut96125 = false, _mut96126 = false, _mut96127 = false, _mut96128 = false, _mut96129 = false, _mut96130 = false, _mut96131 = false, _mut96132 = false, _mut96133 = false, _mut96134 = false, _mut96135 = false, _mut96136 = false, _mut96137 = false, _mut96138 = false, _mut96139 = false, _mut96140 = false, _mut96141 = false, _mut96142 = false, _mut96143 = false, _mut96144 = false, _mut96145 = false, _mut96146 = false, _mut96147 = false, _mut96148 = false, _mut96149 = false, _mut96150 = false, _mut96151 = false, _mut96152 = false, _mut96153 = false, _mut96154 = false, _mut96155 = false, _mut96156 = false, _mut96157 = false, _mut96158 = false, _mut96159 = false, _mut96160 = false, _mut96161 = false, _mut96162 = false, _mut96163 = false, _mut96164 = false, _mut96165 = false, _mut96166 = false, _mut96167 = false, _mut96168 = false, _mut96169 = false, _mut96170 = false, _mut96171 = false, _mut96172 = false, _mut96173 = false, _mut96174 = false, _mut96175 = false, _mut96176 = false, _mut96177 = false, _mut96178 = false, _mut96179 = false, _mut96180 = false, _mut96181 = false, _mut96182 = false, _mut96183 = false, _mut96184 = false, _mut96185 = false, _mut96186 = false, _mut96187 = false, _mut96188 = false, _mut96189 = false, _mut96190 = false, _mut96191 = false, _mut96192 = false, _mut96193 = false, _mut96194 = false, _mut96195 = false, _mut96196 = false, _mut96197 = false, _mut96198 = false, _mut96199 = false, _mut96200 = false, _mut96201 = false, _mut96202 = false, _mut96203 = false, _mut96204 = false, _mut96205 = false, _mut96206 = false, _mut96207 = false, _mut96208 = false, _mut96209 = false, _mut96210 = false, _mut96211 = false, _mut96212 = false, _mut96213 = false, _mut96214 = false, _mut96215 = false, _mut96216 = false, _mut96217 = false, _mut96218 = false, _mut96219 = false, _mut96220 = false, _mut96221 = false, _mut96222 = false, _mut96223 = false, _mut96224 = false, _mut96225 = false, _mut96226 = false, _mut96227 = false, _mut96228 = false, _mut96229 = false, _mut96230 = false, _mut96231 = false, _mut96232 = false, _mut96233 = false, _mut96234 = false, _mut96235 = false, _mut96236 = false, _mut96237 = false, _mut96238 = false, _mut96239 = false, _mut96240 = false, _mut96241 = false, _mut96242 = false, _mut96243 = false, _mut96244 = false, _mut96245 = false, _mut96246 = false;

    /**
     * The minimum number of points that are needed to compute the function.
     */
    private static final int MIN_NUM_POINTS = 5;

    /**
     * Samples x-coordinates
     */
    private final double[] xval;

    /**
     * Samples y-coordinates
     */
    private final double[] yval;

    /**
     * Set of cubic splines patching the whole data grid
     */
    private final double[][] fval;

    /**
     * @param x Sample values of the x-coordinate, in increasing order.
     * @param y Sample values of the y-coordinate, in increasing order.
     * @param f Values of the function on every grid point. the expected number
     *        of elements.
     * @throws NonMonotonicSequenceException if {@code x} or {@code y} are not
     *         strictly increasing.
     * @throws NullArgumentException if any of the arguments are null
     * @throws NoDataException if any of the arrays has zero length.
     * @throws DimensionMismatchException if the length of x and y don't match the row, column
     *         height of f
     */
    public PiecewiseBicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64");
        if ((_mut96074 ? ((_mut96073 ? ((_mut96072 ? (x == null && y == null) : (x == null || y == null)) && f == null) : ((_mut96072 ? (x == null && y == null) : (x == null || y == null)) || f == null)) && f[0] == null) : ((_mut96073 ? ((_mut96072 ? (x == null && y == null) : (x == null || y == null)) && f == null) : ((_mut96072 ? (x == null && y == null) : (x == null || y == null)) || f == null)) || f[0] == null))) {
            throw new NullArgumentException();
        }
        final int xLen = x.length;
        final int yLen = y.length;
        if ((_mut96097 ? ((_mut96091 ? ((_mut96085 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96086, _mut96087, _mut96088, _mut96089, _mut96090)) : ((_mut96085 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96086, _mut96087, _mut96088, _mut96089, _mut96090))) && ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96092, _mut96093, _mut96094, _mut96095, _mut96096)) : ((_mut96091 ? ((_mut96085 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96086, _mut96087, _mut96088, _mut96089, _mut96090)) : ((_mut96085 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96075, _mut96076, _mut96077, _mut96078, _mut96079) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96080, _mut96081, _mut96082, _mut96083, _mut96084))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96086, _mut96087, _mut96088, _mut96089, _mut96090))) || ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96092, _mut96093, _mut96094, _mut96095, _mut96096)))) {
            throw new NoDataException();
        }
        if ((_mut96120 ? ((_mut96114 ? ((_mut96108 ? (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) && ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107)) : (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) || ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107))) && ROR_less(f.length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96109, _mut96110, _mut96111, _mut96112, _mut96113)) : ((_mut96108 ? (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) && ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107)) : (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) || ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107))) || ROR_less(f.length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96109, _mut96110, _mut96111, _mut96112, _mut96113))) && ROR_less(f[0].length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96115, _mut96116, _mut96117, _mut96118, _mut96119)) : ((_mut96114 ? ((_mut96108 ? (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) && ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107)) : (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) || ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107))) && ROR_less(f.length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96109, _mut96110, _mut96111, _mut96112, _mut96113)) : ((_mut96108 ? (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) && ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107)) : (ROR_less(xLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96098, _mut96099, _mut96100, _mut96101, _mut96102) || ROR_less(yLen, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96103, _mut96104, _mut96105, _mut96106, _mut96107))) || ROR_less(f.length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96109, _mut96110, _mut96111, _mut96112, _mut96113))) || ROR_less(f[0].length, MIN_NUM_POINTS, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96115, _mut96116, _mut96117, _mut96118, _mut96119)))) {
            throw new InsufficientDataException();
        }
        if (ROR_not_equals(xLen, f.length, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96121, _mut96122, _mut96123, _mut96124, _mut96125)) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (ROR_not_equals(yLen, f[0].length, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.PiecewiseBicubicSplineInterpolatingFunction_64", _mut96126, _mut96127, _mut96128, _mut96129, _mut96130)) {
            throw new DimensionMismatchException(yLen, f[0].length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        xval = x.clone();
        yval = y.clone();
        fval = f.clone();
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114");
        final AkimaSplineInterpolator interpolator = new AkimaSplineInterpolator();
        final int offset = 2;
        final int count = AOR_plus(offset, 3, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96131, _mut96132, _mut96133, _mut96134);
        final int i = searchIndex(x, xval, offset, count);
        final int j = searchIndex(y, yval, offset, count);
        final double[] xArray = new double[count];
        final double[] yArray = new double[count];
        final double[] zArray = new double[count];
        final double[] interpArray = new double[count];
        for (int index = 0; ROR_less(index, count, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96143, _mut96144, _mut96145, _mut96146, _mut96147); index++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114");
            xArray[index] = xval[AOR_plus(i, index, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96135, _mut96136, _mut96137, _mut96138)];
            yArray[index] = yval[AOR_plus(j, index, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96139, _mut96140, _mut96141, _mut96142)];
        }
        for (int zIndex = 0; ROR_less(zIndex, count, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96161, _mut96162, _mut96163, _mut96164, _mut96165); zIndex++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114");
            for (int index = 0; ROR_less(index, count, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96156, _mut96157, _mut96158, _mut96159, _mut96160); index++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114");
                zArray[index] = fval[AOR_plus(i, index, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96152, _mut96153, _mut96154, _mut96155)][AOR_plus(j, zIndex, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.value_114", _mut96148, _mut96149, _mut96150, _mut96151)];
            }
            final PolynomialSplineFunction spline = interpolator.interpolate(xArray, zArray);
            interpArray[zIndex] = spline.value(x);
        }
        final PolynomialSplineFunction spline = interpolator.interpolate(yArray, interpArray);
        double returnValue = spline.value(y);
        return returnValue;
    }

    /**
     * Indicates whether a point is within the interpolation range.
     *
     * @param x First coordinate.
     * @param y Second coordinate.
     * @return {@code true} if (x, y) is a valid point.
     * @since 3.3
     */
    public boolean isValidPoint(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156");
        if ((_mut96196 ? ((_mut96186 ? ((_mut96180 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96181, _mut96182, _mut96183, _mut96184, _mut96185)) : ((_mut96180 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96181, _mut96182, _mut96183, _mut96184, _mut96185))) && ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96187, _mut96188, _mut96189, _mut96190)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96191, _mut96192, _mut96193, _mut96194, _mut96195)) : ((_mut96186 ? ((_mut96180 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96181, _mut96182, _mut96183, _mut96184, _mut96185)) : ((_mut96180 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96166, _mut96167, _mut96168, _mut96169, _mut96170) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96171, _mut96172, _mut96173, _mut96174)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96175, _mut96176, _mut96177, _mut96178, _mut96179))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96181, _mut96182, _mut96183, _mut96184, _mut96185))) || ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96187, _mut96188, _mut96189, _mut96190)], "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.isValidPoint_156", _mut96191, _mut96192, _mut96193, _mut96194, _mut96195)))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param c Coordinate.
     * @param val Coordinate samples.
     * @param offset how far back from found value to offset for querying
     * @param count total number of elements forward from beginning that will be
     *        queried
     * @return the index in {@code val} corresponding to the interval containing
     *         {@code c}.
     * @throws OutOfRangeException if {@code c} is out of the range defined by
     *         the boundary values of {@code val}.
     */
    private int searchIndex(double c, double[] val, int offset, int count) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179");
        int r = Arrays.binarySearch(val, c);
        if ((_mut96211 ? (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96197, _mut96198, _mut96199, _mut96200, _mut96201) && ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96202, _mut96203, _mut96204, _mut96205), "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96206, _mut96207, _mut96208, _mut96209, _mut96210)) : (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96197, _mut96198, _mut96199, _mut96200, _mut96201) || ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96202, _mut96203, _mut96204, _mut96205), "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96206, _mut96207, _mut96208, _mut96209, _mut96210)))) {
            throw new OutOfRangeException(c, val[0], val[AOR_minus(val.length, 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96212, _mut96213, _mut96214, _mut96215)]);
        }
        if (ROR_less(r, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96216, _mut96217, _mut96218, _mut96219, _mut96220)) {
            // need to remove the negative sign for consistency
            r = AOR_minus(AOR_minus(-r, offset, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96221, _mut96222, _mut96223, _mut96224), 1, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96225, _mut96226, _mut96227, _mut96228);
        } else {
            r -= offset;
        }
        if (ROR_less(r, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96229, _mut96230, _mut96231, _mut96232, _mut96233)) {
            r = 0;
        }
        if (ROR_greater_equals((AOR_plus(r, count, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96234, _mut96235, _mut96236, _mut96237)), val.length, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96238, _mut96239, _mut96240, _mut96241, _mut96242)) {
            // of the sample at the lower end of the last sub-interval.
            r = AOR_minus(val.length, count, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolatingFunction.searchIndex_179", _mut96243, _mut96244, _mut96245, _mut96246);
        }
        return r;
    }
}
