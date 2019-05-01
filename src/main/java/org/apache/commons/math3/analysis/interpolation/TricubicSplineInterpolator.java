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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Generates a tricubic interpolating function.
 *
 * @since 2.2
 * @deprecated To be removed in 4.0 (see MATH-1166).
 */
@Deprecated
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {

    @Conditional
    public static boolean _mut95147 = false, _mut95148 = false, _mut95149 = false, _mut95150 = false, _mut95151 = false, _mut95152 = false, _mut95153 = false, _mut95154 = false, _mut95155 = false, _mut95156 = false, _mut95157 = false, _mut95158 = false, _mut95159 = false, _mut95160 = false, _mut95161 = false, _mut95162 = false, _mut95163 = false, _mut95164 = false, _mut95165 = false, _mut95166 = false, _mut95167 = false, _mut95168 = false, _mut95169 = false, _mut95170 = false, _mut95171 = false, _mut95172 = false, _mut95173 = false, _mut95174 = false, _mut95175 = false, _mut95176 = false, _mut95177 = false, _mut95178 = false, _mut95179 = false, _mut95180 = false, _mut95181 = false, _mut95182 = false, _mut95183 = false, _mut95184 = false, _mut95185 = false, _mut95186 = false, _mut95187 = false, _mut95188 = false, _mut95189 = false, _mut95190 = false, _mut95191 = false, _mut95192 = false, _mut95193 = false, _mut95194 = false, _mut95195 = false, _mut95196 = false, _mut95197 = false, _mut95198 = false, _mut95199 = false, _mut95200 = false, _mut95201 = false, _mut95202 = false, _mut95203 = false, _mut95204 = false, _mut95205 = false, _mut95206 = false, _mut95207 = false, _mut95208 = false, _mut95209 = false, _mut95210 = false, _mut95211 = false, _mut95212 = false, _mut95213 = false, _mut95214 = false, _mut95215 = false, _mut95216 = false, _mut95217 = false, _mut95218 = false, _mut95219 = false, _mut95220 = false, _mut95221 = false, _mut95222 = false, _mut95223 = false, _mut95224 = false, _mut95225 = false, _mut95226 = false, _mut95227 = false, _mut95228 = false, _mut95229 = false, _mut95230 = false, _mut95231 = false, _mut95232 = false, _mut95233 = false, _mut95234 = false, _mut95235 = false, _mut95236 = false, _mut95237 = false, _mut95238 = false, _mut95239 = false, _mut95240 = false, _mut95241 = false, _mut95242 = false, _mut95243 = false, _mut95244 = false, _mut95245 = false, _mut95246 = false, _mut95247 = false, _mut95248 = false, _mut95249 = false, _mut95250 = false, _mut95251 = false, _mut95252 = false, _mut95253 = false, _mut95254 = false, _mut95255 = false, _mut95256 = false, _mut95257 = false, _mut95258 = false, _mut95259 = false, _mut95260 = false, _mut95261 = false, _mut95262 = false, _mut95263 = false, _mut95264 = false, _mut95265 = false, _mut95266 = false, _mut95267 = false, _mut95268 = false, _mut95269 = false, _mut95270 = false, _mut95271 = false, _mut95272 = false, _mut95273 = false, _mut95274 = false, _mut95275 = false, _mut95276 = false, _mut95277 = false, _mut95278 = false, _mut95279 = false, _mut95280 = false, _mut95281 = false, _mut95282 = false, _mut95283 = false, _mut95284 = false, _mut95285 = false, _mut95286 = false, _mut95287 = false, _mut95288 = false, _mut95289 = false, _mut95290 = false, _mut95291 = false, _mut95292 = false, _mut95293 = false, _mut95294 = false, _mut95295 = false, _mut95296 = false, _mut95297 = false, _mut95298 = false, _mut95299 = false, _mut95300 = false, _mut95301 = false, _mut95302 = false, _mut95303 = false, _mut95304 = false, _mut95305 = false, _mut95306 = false, _mut95307 = false, _mut95308 = false, _mut95309 = false, _mut95310 = false, _mut95311 = false, _mut95312 = false, _mut95313 = false, _mut95314 = false, _mut95315 = false, _mut95316 = false, _mut95317 = false, _mut95318 = false, _mut95319 = false, _mut95320 = false, _mut95321 = false, _mut95322 = false, _mut95323 = false, _mut95324 = false, _mut95325 = false, _mut95326 = false, _mut95327 = false, _mut95328 = false, _mut95329 = false, _mut95330 = false, _mut95331 = false, _mut95332 = false, _mut95333 = false, _mut95334 = false, _mut95335 = false, _mut95336 = false, _mut95337 = false, _mut95338 = false, _mut95339 = false, _mut95340 = false, _mut95341 = false, _mut95342 = false, _mut95343 = false, _mut95344 = false, _mut95345 = false, _mut95346 = false, _mut95347 = false, _mut95348 = false;

    /**
     * {@inheritDoc}
     */
    public TricubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[] zval, final double[][][] fval) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
        if ((_mut95169 ? ((_mut95163 ? ((_mut95157 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156))) && ROR_equals(zval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95158, _mut95159, _mut95160, _mut95161, _mut95162)) : ((_mut95157 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156))) || ROR_equals(zval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95158, _mut95159, _mut95160, _mut95161, _mut95162))) && ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95164, _mut95165, _mut95166, _mut95167, _mut95168)) : ((_mut95163 ? ((_mut95157 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156))) && ROR_equals(zval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95158, _mut95159, _mut95160, _mut95161, _mut95162)) : ((_mut95157 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95147, _mut95148, _mut95149, _mut95150, _mut95151) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95152, _mut95153, _mut95154, _mut95155, _mut95156))) || ROR_equals(zval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95158, _mut95159, _mut95160, _mut95161, _mut95162))) || ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95164, _mut95165, _mut95166, _mut95167, _mut95168)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, fval.length, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95170, _mut95171, _mut95172, _mut95173, _mut95174)) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        MathArrays.checkOrder(zval);
        final int xLen = xval.length;
        final int yLen = yval.length;
        final int zLen = zval.length;
        // fvalZX[j][k][i] = f(xval[i], yval[j], zval[k])
        final double[][][] fvalXY = new double[zLen][xLen][yLen];
        final double[][][] fvalZX = new double[yLen][zLen][xLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95195, _mut95196, _mut95197, _mut95198, _mut95199); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            if (ROR_not_equals(fval[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95175, _mut95176, _mut95177, _mut95178, _mut95179)) {
                throw new DimensionMismatchException(fval[i].length, yLen);
            }
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95190, _mut95191, _mut95192, _mut95193, _mut95194); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                if (ROR_not_equals(fval[i][j].length, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95180, _mut95181, _mut95182, _mut95183, _mut95184)) {
                    throw new DimensionMismatchException(fval[i][j].length, zLen);
                }
                for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95185, _mut95186, _mut95187, _mut95188, _mut95189); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                    final double v = fval[i][j][k];
                    fvalXY[k][i][j] = v;
                    fvalZX[j][k][i] = v;
                }
            }
        }
        final BicubicSplineInterpolator bsi = new BicubicSplineInterpolator(true);
        // For each line x[i] (0 <= i < xLen), construct a 2D spline in y and z
        final BicubicSplineInterpolatingFunction[] xSplineYZ = new BicubicSplineInterpolatingFunction[xLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95200, _mut95201, _mut95202, _mut95203, _mut95204); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            xSplineYZ[i] = bsi.interpolate(yval, zval, fval[i]);
        }
        // For each line y[j] (0 <= j < yLen), construct a 2D spline in z and x
        final BicubicSplineInterpolatingFunction[] ySplineZX = new BicubicSplineInterpolatingFunction[yLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95205, _mut95206, _mut95207, _mut95208, _mut95209); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            ySplineZX[j] = bsi.interpolate(zval, xval, fvalZX[j]);
        }
        // For each line z[k] (0 <= k < zLen), construct a 2D spline in x and y
        final BicubicSplineInterpolatingFunction[] zSplineXY = new BicubicSplineInterpolatingFunction[zLen];
        for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95210, _mut95211, _mut95212, _mut95213, _mut95214); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            zSplineXY[k] = bsi.interpolate(xval, yval, fvalXY[k]);
        }
        // Partial derivatives wrt x and wrt y
        final double[][][] dFdX = new double[xLen][yLen][zLen];
        final double[][][] dFdY = new double[xLen][yLen][zLen];
        final double[][][] d2FdXdY = new double[xLen][yLen][zLen];
        for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95225, _mut95226, _mut95227, _mut95228, _mut95229); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            final BicubicSplineInterpolatingFunction f = zSplineXY[k];
            for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95220, _mut95221, _mut95222, _mut95223, _mut95224); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                final double x = xval[i];
                for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95215, _mut95216, _mut95217, _mut95218, _mut95219); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                    final double y = yval[j];
                    dFdX[i][j][k] = f.partialDerivativeX(x, y);
                    dFdY[i][j][k] = f.partialDerivativeY(x, y);
                    d2FdXdY[i][j][k] = f.partialDerivativeXY(x, y);
                }
            }
        }
        // Partial derivatives wrt y and wrt z
        final double[][][] dFdZ = new double[xLen][yLen][zLen];
        final double[][][] d2FdYdZ = new double[xLen][yLen][zLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95240, _mut95241, _mut95242, _mut95243, _mut95244); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            final BicubicSplineInterpolatingFunction f = xSplineYZ[i];
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95235, _mut95236, _mut95237, _mut95238, _mut95239); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                final double y = yval[j];
                for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95230, _mut95231, _mut95232, _mut95233, _mut95234); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                    final double z = zval[k];
                    dFdZ[i][j][k] = f.partialDerivativeY(y, z);
                    d2FdYdZ[i][j][k] = f.partialDerivativeXY(y, z);
                }
            }
        }
        // Partial derivatives wrt x and wrt z
        final double[][][] d2FdZdX = new double[xLen][yLen][zLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95255, _mut95256, _mut95257, _mut95258, _mut95259); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            final BicubicSplineInterpolatingFunction f = ySplineZX[j];
            for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95250, _mut95251, _mut95252, _mut95253, _mut95254); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                final double z = zval[k];
                for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95245, _mut95246, _mut95247, _mut95248, _mut95249); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                    final double x = xval[i];
                    d2FdZdX[i][j][k] = f.partialDerivativeXY(z, x);
                }
            }
        }
        // Third partial cross-derivatives
        final double[][][] d3FdXdYdZ = new double[xLen][yLen][zLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95322, _mut95323, _mut95324, _mut95325, _mut95326); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
            final int nI = nextIndex(i, xLen);
            final int pI = previousIndex(i);
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95317, _mut95318, _mut95319, _mut95320, _mut95321); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                final int nJ = nextIndex(j, yLen);
                final int pJ = previousIndex(j);
                for (int k = 0; ROR_less(k, zLen, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95312, _mut95313, _mut95314, _mut95315, _mut95316); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37");
                    final int nK = nextIndex(k, zLen);
                    final int pK = previousIndex(k);
                    // XXX Not sure about this formula
                    d3FdXdYdZ[i][j][k] = AOR_divide((AOR_minus(AOR_plus(AOR_plus(AOR_minus(AOR_plus(AOR_minus(AOR_minus(fval[nI][nJ][nK], fval[nI][pJ][nK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95260, _mut95261, _mut95262, _mut95263), fval[pI][nJ][nK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95264, _mut95265, _mut95266, _mut95267), fval[pI][pJ][nK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95268, _mut95269, _mut95270, _mut95271), fval[nI][nJ][pK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95272, _mut95273, _mut95274, _mut95275), fval[nI][pJ][pK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95276, _mut95277, _mut95278, _mut95279), fval[pI][nJ][pK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95280, _mut95281, _mut95282, _mut95283), fval[pI][pJ][pK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95284, _mut95285, _mut95286, _mut95287)), (AOR_multiply(AOR_multiply((AOR_minus(xval[nI], xval[pI], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95288, _mut95289, _mut95290, _mut95291)), (AOR_minus(yval[nJ], yval[pJ], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95292, _mut95293, _mut95294, _mut95295)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95296, _mut95297, _mut95298, _mut95299), (AOR_minus(zval[nK], zval[pK], "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95300, _mut95301, _mut95302, _mut95303)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95304, _mut95305, _mut95306, _mut95307)), "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.interpolate_37", _mut95308, _mut95309, _mut95310, _mut95311);
                }
            }
        }
        // Create the interpolating splines
        return new TricubicSplineInterpolatingFunction(xval, yval, zval, fval, dFdX, dFdY, dFdZ, d2FdXdY, d2FdZdX, d2FdYdZ, d3FdXdYdZ);
    }

    /**
     * Compute the next index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is larger than or equal to 0.
     *
     * @param i Index
     * @param max Upper limit of the array
     * @return the next index
     */
    private int nextIndex(int i, int max) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.nextIndex_186");
        final int index = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.nextIndex_186", _mut95327, _mut95328, _mut95329, _mut95330);
        return ROR_less(index, max, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.nextIndex_186", _mut95331, _mut95332, _mut95333, _mut95334, _mut95335) ? index : AOR_minus(index, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.nextIndex_186", _mut95336, _mut95337, _mut95338, _mut95339);
    }

    /**
     * Compute the previous index of an array, clipping if necessary.
     * It is assumed (but not checked) that {@code i} is smaller than the size of the array.
     *
     * @param i Index
     * @return the previous index
     */
    private int previousIndex(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.previousIndex_197");
        final int index = AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.previousIndex_197", _mut95340, _mut95341, _mut95342, _mut95343);
        return ROR_greater_equals(index, 0, "org.apache.commons.math3.analysis.interpolation.TricubicSplineInterpolator.previousIndex_197", _mut95344, _mut95345, _mut95346, _mut95347, _mut95348) ? index : 0;
    }
}
