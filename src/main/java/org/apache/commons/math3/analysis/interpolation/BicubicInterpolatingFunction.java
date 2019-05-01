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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Function that implements the
 * <a href="http://en.wikipedia.org/wiki/Bicubic_interpolation">
 * bicubic spline interpolation</a>.
 *
 * @since 3.4
 */
public class BicubicInterpolatingFunction implements BivariateFunction {

    @Conditional
    public static boolean _mut93961 = false, _mut93962 = false, _mut93963 = false, _mut93964 = false, _mut93965 = false, _mut93966 = false, _mut93967 = false, _mut93968 = false, _mut93969 = false, _mut93970 = false, _mut93971 = false, _mut93972 = false, _mut93973 = false, _mut93974 = false, _mut93975 = false, _mut93976 = false, _mut93977 = false, _mut93978 = false, _mut93979 = false, _mut93980 = false, _mut93981 = false, _mut93982 = false, _mut93983 = false, _mut93984 = false, _mut93985 = false, _mut93986 = false, _mut93987 = false, _mut93988 = false, _mut93989 = false, _mut93990 = false, _mut93991 = false, _mut93992 = false, _mut93993 = false, _mut93994 = false, _mut93995 = false, _mut93996 = false, _mut93997 = false, _mut93998 = false, _mut93999 = false, _mut94000 = false, _mut94001 = false, _mut94002 = false, _mut94003 = false, _mut94004 = false, _mut94005 = false, _mut94006 = false, _mut94007 = false, _mut94008 = false, _mut94009 = false, _mut94010 = false, _mut94011 = false, _mut94012 = false, _mut94013 = false, _mut94014 = false, _mut94015 = false, _mut94016 = false, _mut94017 = false, _mut94018 = false, _mut94019 = false, _mut94020 = false, _mut94021 = false, _mut94022 = false, _mut94023 = false, _mut94024 = false, _mut94025 = false, _mut94026 = false, _mut94027 = false, _mut94028 = false, _mut94029 = false, _mut94030 = false, _mut94031 = false, _mut94032 = false, _mut94033 = false, _mut94034 = false, _mut94035 = false, _mut94036 = false, _mut94037 = false, _mut94038 = false, _mut94039 = false, _mut94040 = false, _mut94041 = false, _mut94042 = false, _mut94043 = false, _mut94044 = false, _mut94045 = false, _mut94046 = false, _mut94047 = false, _mut94048 = false, _mut94049 = false, _mut94050 = false, _mut94051 = false, _mut94052 = false, _mut94053 = false, _mut94054 = false, _mut94055 = false, _mut94056 = false, _mut94057 = false, _mut94058 = false, _mut94059 = false, _mut94060 = false, _mut94061 = false, _mut94062 = false, _mut94063 = false, _mut94064 = false, _mut94065 = false, _mut94066 = false, _mut94067 = false, _mut94068 = false, _mut94069 = false, _mut94070 = false, _mut94071 = false, _mut94072 = false, _mut94073 = false, _mut94074 = false, _mut94075 = false, _mut94076 = false, _mut94077 = false, _mut94078 = false, _mut94079 = false, _mut94080 = false, _mut94081 = false, _mut94082 = false, _mut94083 = false, _mut94084 = false, _mut94085 = false, _mut94086 = false, _mut94087 = false, _mut94088 = false, _mut94089 = false, _mut94090 = false, _mut94091 = false, _mut94092 = false, _mut94093 = false, _mut94094 = false, _mut94095 = false, _mut94096 = false, _mut94097 = false, _mut94098 = false, _mut94099 = false, _mut94100 = false, _mut94101 = false, _mut94102 = false, _mut94103 = false, _mut94104 = false, _mut94105 = false, _mut94106 = false, _mut94107 = false, _mut94108 = false, _mut94109 = false, _mut94110 = false, _mut94111 = false, _mut94112 = false, _mut94113 = false, _mut94114 = false, _mut94115 = false, _mut94116 = false, _mut94117 = false, _mut94118 = false, _mut94119 = false, _mut94120 = false, _mut94121 = false, _mut94122 = false, _mut94123 = false, _mut94124 = false, _mut94125 = false, _mut94126 = false, _mut94127 = false, _mut94128 = false, _mut94129 = false, _mut94130 = false, _mut94131 = false, _mut94132 = false, _mut94133 = false, _mut94134 = false, _mut94135 = false, _mut94136 = false, _mut94137 = false, _mut94138 = false, _mut94139 = false, _mut94140 = false, _mut94141 = false, _mut94142 = false, _mut94143 = false, _mut94144 = false, _mut94145 = false, _mut94146 = false, _mut94147 = false, _mut94148 = false, _mut94149 = false, _mut94150 = false, _mut94151 = false, _mut94152 = false, _mut94153 = false, _mut94154 = false, _mut94155 = false, _mut94156 = false, _mut94157 = false, _mut94158 = false, _mut94159 = false, _mut94160 = false, _mut94161 = false, _mut94162 = false, _mut94163 = false, _mut94164 = false, _mut94165 = false, _mut94166 = false, _mut94167 = false, _mut94168 = false, _mut94169 = false, _mut94170 = false, _mut94171 = false, _mut94172 = false, _mut94173 = false, _mut94174 = false, _mut94175 = false, _mut94176 = false, _mut94177 = false, _mut94178 = false, _mut94179 = false, _mut94180 = false, _mut94181 = false, _mut94182 = false, _mut94183 = false, _mut94184 = false, _mut94185 = false, _mut94186 = false, _mut94187 = false, _mut94188 = false, _mut94189 = false, _mut94190 = false, _mut94191 = false, _mut94192 = false, _mut94193 = false, _mut94194 = false, _mut94195 = false, _mut94196 = false, _mut94197 = false, _mut94198 = false, _mut94199 = false, _mut94200 = false, _mut94201 = false, _mut94202 = false, _mut94203 = false, _mut94204 = false, _mut94205 = false, _mut94206 = false, _mut94207 = false, _mut94208 = false, _mut94209 = false, _mut94210 = false, _mut94211 = false, _mut94212 = false, _mut94213 = false, _mut94214 = false, _mut94215 = false, _mut94216 = false, _mut94217 = false, _mut94218 = false, _mut94219 = false, _mut94220 = false, _mut94221 = false, _mut94222 = false, _mut94223 = false, _mut94224 = false, _mut94225 = false, _mut94226 = false, _mut94227 = false;

    /**
     * Number of coefficients.
     */
    private static final int NUM_COEFF = 16;

    /**
     * Matrix to compute the spline coefficients from the function values
     * and function derivatives values
     */
    private static final double[][] AINV = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { -3, 3, 0, 0, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 2, -2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, -3, 3, 0, 0, -2, -1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 2, -2, 0, 0, 1, 1, 0, 0 }, { -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, -3, 0, 3, 0, 0, 0, 0, 0, -2, 0, -1, 0 }, { 9, -9, -9, 9, 6, 3, -6, -3, 6, -6, 3, -3, 4, 2, 2, 1 }, { -6, 6, 6, -6, -3, -3, 3, 3, -4, 4, -2, 2, -2, -2, -1, -1 }, { 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 2, 0, -2, 0, 0, 0, 0, 0, 1, 0, 1, 0 }, { -6, 6, 6, -6, -4, -2, 4, 2, -3, 3, -3, 3, -2, -1, -2, -1 }, { 4, -4, -4, 4, 2, 2, -2, -2, 2, -2, 2, -2, 1, 1, 1, 1 } };

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
    private final BicubicFunction[][] splines;

    /**
     * @param x Sample values of the x-coordinate, in increasing order.
     * @param y Sample values of the y-coordinate, in increasing order.
     * @param f Values of the function on every grid point.
     * @param dFdX Values of the partial derivative of function with respect
     * to x on every grid point.
     * @param dFdY Values of the partial derivative of function with respect
     * to y on every grid point.
     * @param d2FdXdY Values of the cross partial derivative of function on
     * every grid point.
     * @throws DimensionMismatchException if the various arrays do not contain
     * the expected number of elements.
     * @throws NonMonotonicSequenceException if {@code x} or {@code y} are
     * not strictly increasing.
     * @throws NoDataException if any of the arrays has zero length.
     */
    public BicubicInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84");
        final int xLen = x.length;
        final int yLen = y.length;
        if ((_mut93983 ? ((_mut93977 ? ((_mut93971 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93972, _mut93973, _mut93974, _mut93975, _mut93976)) : ((_mut93971 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93972, _mut93973, _mut93974, _mut93975, _mut93976))) && ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93978, _mut93979, _mut93980, _mut93981, _mut93982)) : ((_mut93977 ? ((_mut93971 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970))) && ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93972, _mut93973, _mut93974, _mut93975, _mut93976)) : ((_mut93971 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) && ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93961, _mut93962, _mut93963, _mut93964, _mut93965) || ROR_equals(yLen, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93966, _mut93967, _mut93968, _mut93969, _mut93970))) || ROR_equals(f.length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93972, _mut93973, _mut93974, _mut93975, _mut93976))) || ROR_equals(f[0].length, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93978, _mut93979, _mut93980, _mut93981, _mut93982)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xLen, f.length, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93984, _mut93985, _mut93986, _mut93987, _mut93988)) {
            throw new DimensionMismatchException(xLen, f.length);
        }
        if (ROR_not_equals(xLen, dFdX.length, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93989, _mut93990, _mut93991, _mut93992, _mut93993)) {
            throw new DimensionMismatchException(xLen, dFdX.length);
        }
        if (ROR_not_equals(xLen, dFdY.length, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93994, _mut93995, _mut93996, _mut93997, _mut93998)) {
            throw new DimensionMismatchException(xLen, dFdY.length);
        }
        if (ROR_not_equals(xLen, d2FdXdY.length, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut93999, _mut94000, _mut94001, _mut94002, _mut94003)) {
            throw new DimensionMismatchException(xLen, d2FdXdY.length);
        }
        MathArrays.checkOrder(x);
        MathArrays.checkOrder(y);
        xval = x.clone();
        yval = y.clone();
        final int lastI = AOR_minus(xLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94004, _mut94005, _mut94006, _mut94007);
        final int lastJ = AOR_minus(yLen, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94008, _mut94009, _mut94010, _mut94011);
        splines = new BicubicFunction[lastI][lastJ];
        for (int i = 0; ROR_less(i, lastI, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94105, _mut94106, _mut94107, _mut94108, _mut94109); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84");
            if (ROR_not_equals(f[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94012, _mut94013, _mut94014, _mut94015, _mut94016)) {
                throw new DimensionMismatchException(f[i].length, yLen);
            }
            if (ROR_not_equals(dFdX[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94017, _mut94018, _mut94019, _mut94020, _mut94021)) {
                throw new DimensionMismatchException(dFdX[i].length, yLen);
            }
            if (ROR_not_equals(dFdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94022, _mut94023, _mut94024, _mut94025, _mut94026)) {
                throw new DimensionMismatchException(dFdY[i].length, yLen);
            }
            if (ROR_not_equals(d2FdXdY[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94027, _mut94028, _mut94029, _mut94030, _mut94031)) {
                throw new DimensionMismatchException(d2FdXdY[i].length, yLen);
            }
            final int ip1 = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94032, _mut94033, _mut94034, _mut94035);
            final double xR = AOR_minus(xval[ip1], xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94036, _mut94037, _mut94038, _mut94039);
            for (int j = 0; ROR_less(j, lastJ, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94100, _mut94101, _mut94102, _mut94103, _mut94104); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84");
                final int jp1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94040, _mut94041, _mut94042, _mut94043);
                final double yR = AOR_minus(yval[jp1], yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94044, _mut94045, _mut94046, _mut94047);
                final double xRyR = AOR_multiply(xR, yR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94048, _mut94049, _mut94050, _mut94051);
                final double[] beta = new double[] { f[i][j], f[ip1][j], f[i][jp1], f[ip1][jp1], AOR_multiply(dFdX[i][j], xR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94052, _mut94053, _mut94054, _mut94055), AOR_multiply(dFdX[ip1][j], xR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94056, _mut94057, _mut94058, _mut94059), AOR_multiply(dFdX[i][jp1], xR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94060, _mut94061, _mut94062, _mut94063), AOR_multiply(dFdX[ip1][jp1], xR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94064, _mut94065, _mut94066, _mut94067), AOR_multiply(dFdY[i][j], yR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94068, _mut94069, _mut94070, _mut94071), AOR_multiply(dFdY[ip1][j], yR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94072, _mut94073, _mut94074, _mut94075), AOR_multiply(dFdY[i][jp1], yR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94076, _mut94077, _mut94078, _mut94079), AOR_multiply(dFdY[ip1][jp1], yR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94080, _mut94081, _mut94082, _mut94083), AOR_multiply(d2FdXdY[i][j], xRyR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94084, _mut94085, _mut94086, _mut94087), AOR_multiply(d2FdXdY[ip1][j], xRyR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94088, _mut94089, _mut94090, _mut94091), AOR_multiply(d2FdXdY[i][jp1], xRyR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94092, _mut94093, _mut94094, _mut94095), AOR_multiply(d2FdXdY[ip1][jp1], xRyR, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.BicubicInterpolatingFunction_84", _mut94096, _mut94097, _mut94098, _mut94099) };
                splines[i][j] = new BicubicFunction(computeSplineCoefficients(beta));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156");
        final int i = searchIndex(x, xval);
        final int j = searchIndex(y, yval);
        final double xN = AOR_divide((AOR_minus(x, xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94110, _mut94111, _mut94112, _mut94113)), (AOR_minus(xval[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94114, _mut94115, _mut94116, _mut94117)], xval[i], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94118, _mut94119, _mut94120, _mut94121)), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94122, _mut94123, _mut94124, _mut94125);
        final double yN = AOR_divide((AOR_minus(y, yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94126, _mut94127, _mut94128, _mut94129)), (AOR_minus(yval[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94130, _mut94131, _mut94132, _mut94133)], yval[j], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94134, _mut94135, _mut94136, _mut94137)), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.value_156", _mut94138, _mut94139, _mut94140, _mut94141);
        return splines[i][j].value(xN, yN);
    }

    /**
     * Indicates whether a point is within the interpolation range.
     *
     * @param x First coordinate.
     * @param y Second coordinate.
     * @return {@code true} if (x, y) is a valid point.
     */
    public boolean isValidPoint(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174");
        if ((_mut94172 ? ((_mut94162 ? ((_mut94156 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94157, _mut94158, _mut94159, _mut94160, _mut94161)) : ((_mut94156 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94157, _mut94158, _mut94159, _mut94160, _mut94161))) && ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94163, _mut94164, _mut94165, _mut94166)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94167, _mut94168, _mut94169, _mut94170, _mut94171)) : ((_mut94162 ? ((_mut94156 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155))) && ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94157, _mut94158, _mut94159, _mut94160, _mut94161)) : ((_mut94156 ? (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) && ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155)) : (ROR_less(x, xval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94142, _mut94143, _mut94144, _mut94145, _mut94146) || ROR_greater(x, xval[AOR_minus(xval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94147, _mut94148, _mut94149, _mut94150)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94151, _mut94152, _mut94153, _mut94154, _mut94155))) || ROR_less(y, yval[0], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94157, _mut94158, _mut94159, _mut94160, _mut94161))) || ROR_greater(y, yval[AOR_minus(yval.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94163, _mut94164, _mut94165, _mut94166)], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.isValidPoint_174", _mut94167, _mut94168, _mut94169, _mut94170, _mut94171)))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param c Coordinate.
     * @param val Coordinate samples.
     * @return the index in {@code val} corresponding to the interval
     * containing {@code c}.
     * @throws OutOfRangeException if {@code c} is out of the
     * range defined by the boundary values of {@code val}.
     */
    private int searchIndex(double c, double[] val) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193");
        final int r = Arrays.binarySearch(val, c);
        if ((_mut94187 ? (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94173, _mut94174, _mut94175, _mut94176, _mut94177) && ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94178, _mut94179, _mut94180, _mut94181), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94182, _mut94183, _mut94184, _mut94185, _mut94186)) : (ROR_equals(r, -1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94173, _mut94174, _mut94175, _mut94176, _mut94177) || ROR_equals(r, AOR_minus(-val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94178, _mut94179, _mut94180, _mut94181), "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94182, _mut94183, _mut94184, _mut94185, _mut94186)))) {
            throw new OutOfRangeException(c, val[0], val[AOR_minus(val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94188, _mut94189, _mut94190, _mut94191)]);
        }
        if (ROR_less(r, 0, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94192, _mut94193, _mut94194, _mut94195, _mut94196)) {
            // index of the sample at the lower end of the sub-interval.
            return AOR_minus(-r, 2, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94197, _mut94198, _mut94199, _mut94200);
        }
        final int last = AOR_minus(val.length, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94201, _mut94202, _mut94203, _mut94204);
        if (ROR_equals(r, last, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94205, _mut94206, _mut94207, _mut94208, _mut94209)) {
            // of the sample at the lower end of the last sub-interval.
            return AOR_minus(last, 1, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.searchIndex_193", _mut94210, _mut94211, _mut94212, _mut94213);
        }
        // "c" is another sample point.
        return r;
    }

    /**
     * Compute the spline coefficients from the list of function values and
     * function partial derivatives values at the four corners of a grid
     * element. They must be specified in the following order:
     * <ul>
     *  <li>f(0,0)</li>
     *  <li>f(1,0)</li>
     *  <li>f(0,1)</li>
     *  <li>f(1,1)</li>
     *  <li>f<sub>x</sub>(0,0)</li>
     *  <li>f<sub>x</sub>(1,0)</li>
     *  <li>f<sub>x</sub>(0,1)</li>
     *  <li>f<sub>x</sub>(1,1)</li>
     *  <li>f<sub>y</sub>(0,0)</li>
     *  <li>f<sub>y</sub>(1,0)</li>
     *  <li>f<sub>y</sub>(0,1)</li>
     *  <li>f<sub>y</sub>(1,1)</li>
     *  <li>f<sub>xy</sub>(0,0)</li>
     *  <li>f<sub>xy</sub>(1,0)</li>
     *  <li>f<sub>xy</sub>(0,1)</li>
     *  <li>f<sub>xy</sub>(1,1)</li>
     * </ul>
     * where the subscripts indicate the partial derivative with respect to
     * the corresponding variable(s).
     *
     * @param beta List of function values and function partial derivatives
     * values.
     * @return the spline coefficients.
     */
    private double[] computeSplineCoefficients(double[] beta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246");
        final double[] a = new double[NUM_COEFF];
        for (int i = 0; ROR_less(i, NUM_COEFF, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246", _mut94223, _mut94224, _mut94225, _mut94226, _mut94227); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246");
            double result = 0;
            final double[] row = AINV[i];
            for (int j = 0; ROR_less(j, NUM_COEFF, "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246", _mut94218, _mut94219, _mut94220, _mut94221, _mut94222); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246");
                result += AOR_multiply(row[j], beta[j], "org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction.computeSplineCoefficients_246", _mut94214, _mut94215, _mut94216, _mut94217);
            }
            a[i] = result;
        }
        return a;
    }
}

/**
 * Bicubic function.
 */
class BicubicFunction implements BivariateFunction {

    @Conditional
    public static boolean _mut94228 = false, _mut94229 = false, _mut94230 = false, _mut94231 = false, _mut94232 = false, _mut94233 = false, _mut94234 = false, _mut94235 = false, _mut94236 = false, _mut94237 = false, _mut94238 = false, _mut94239 = false, _mut94240 = false, _mut94241 = false, _mut94242 = false, _mut94243 = false, _mut94244 = false, _mut94245 = false, _mut94246 = false, _mut94247 = false, _mut94248 = false, _mut94249 = false, _mut94250 = false, _mut94251 = false, _mut94252 = false, _mut94253 = false, _mut94254 = false, _mut94255 = false, _mut94256 = false, _mut94257 = false, _mut94258 = false, _mut94259 = false, _mut94260 = false, _mut94261 = false, _mut94262 = false, _mut94263 = false, _mut94264 = false, _mut94265 = false, _mut94266 = false, _mut94267 = false, _mut94268 = false, _mut94269 = false, _mut94270 = false, _mut94271 = false, _mut94272 = false, _mut94273 = false, _mut94274 = false, _mut94275 = false, _mut94276 = false, _mut94277 = false, _mut94278 = false, _mut94279 = false, _mut94280 = false, _mut94281 = false, _mut94282 = false, _mut94283 = false, _mut94284 = false, _mut94285 = false, _mut94286 = false, _mut94287 = false, _mut94288 = false, _mut94289 = false, _mut94290 = false, _mut94291 = false, _mut94292 = false;

    /**
     * Number of points.
     */
    private static final short N = 4;

    /**
     * Coefficients
     */
    private final double[][] a;

    /**
     * Simple constructor.
     *
     * @param coeff Spline coefficients.
     */
    BicubicFunction(double[] coeff) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276");
        a = new double[N][N];
        for (int j = 0; ROR_less(j, N, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276", _mut94241, _mut94242, _mut94243, _mut94244, _mut94245); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276");
            final double[] aJ = a[j];
            for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276", _mut94236, _mut94237, _mut94238, _mut94239, _mut94240); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276");
                aJ[i] = coeff[AOR_plus(AOR_multiply(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276", _mut94228, _mut94229, _mut94230, _mut94231), j, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.BicubicFunction_276", _mut94232, _mut94233, _mut94234, _mut94235)];
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289");
        if ((_mut94256 ? (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94246, _mut94247, _mut94248, _mut94249, _mut94250) && ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94251, _mut94252, _mut94253, _mut94254, _mut94255)) : (ROR_less(x, 0, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94246, _mut94247, _mut94248, _mut94249, _mut94250) || ROR_greater(x, 1, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94251, _mut94252, _mut94253, _mut94254, _mut94255)))) {
            throw new OutOfRangeException(x, 0, 1);
        }
        if ((_mut94267 ? (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94257, _mut94258, _mut94259, _mut94260, _mut94261) && ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94262, _mut94263, _mut94264, _mut94265, _mut94266)) : (ROR_less(y, 0, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94257, _mut94258, _mut94259, _mut94260, _mut94261) || ROR_greater(y, 1, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94262, _mut94263, _mut94264, _mut94265, _mut94266)))) {
            throw new OutOfRangeException(y, 0, 1);
        }
        final double x2 = AOR_multiply(x, x, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94268, _mut94269, _mut94270, _mut94271);
        final double x3 = AOR_multiply(x2, x, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94272, _mut94273, _mut94274, _mut94275);
        final double[] pX = { 1, x, x2, x3 };
        final double y2 = AOR_multiply(y, y, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94276, _mut94277, _mut94278, _mut94279);
        final double y3 = AOR_multiply(y2, y, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.value_289", _mut94280, _mut94281, _mut94282, _mut94283);
        final double[] pY = { 1, y, y2, y3 };
        return apply(pX, pY, a);
    }

    /**
     * Compute the value of the bicubic polynomial.
     *
     * @param pX Powers of the x-coordinate.
     * @param pY Powers of the y-coordinate.
     * @param coeff Spline coefficients.
     * @return the interpolated value.
     */
    private double apply(double[] pX, double[] pY, double[][] coeff) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.apply_316");
        double result = 0;
        for (int i = 0; ROR_less(i, N, "org.apache.commons.math3.analysis.interpolation.BicubicFunction.apply_316", _mut94288, _mut94289, _mut94290, _mut94291, _mut94292); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.BicubicFunction.apply_316");
            final double r = MathArrays.linearCombination(coeff[i], pY);
            result += AOR_multiply(r, pX[i], "org.apache.commons.math3.analysis.interpolation.BicubicFunction.apply_316", _mut94284, _mut94285, _mut94286, _mut94287);
        }
        return result;
    }
}
