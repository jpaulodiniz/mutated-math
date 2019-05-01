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
package org.apache.commons.math3.linear;

import java.util.Arrays;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class transforming a symmetrical matrix to tridiagonal shape.
 * <p>A symmetrical m &times; m matrix A can be written as the product of three matrices:
 * A = Q &times; T &times; Q<sup>T</sup> with Q an orthogonal matrix and T a symmetrical
 * tridiagonal matrix. Both Q and T are m &times; m matrices.</p>
 * <p>This implementation only uses the upper part of the matrix, the part below the
 * diagonal is not accessed at all.</p>
 * <p>Transformation to tridiagonal shape is often not a goal by itself, but it is
 * an intermediate step in more general decomposition algorithms like {@link
 * EigenDecomposition eigen decomposition}. This class is therefore intended for internal
 * use by the library and is not public. As a consequence of this explicitly limited scope,
 * many methods directly returns references to internal arrays, not copies.</p>
 * @since 2.0
 */
class TriDiagonalTransformer {

    @Conditional
    public static boolean _mut33206 = false, _mut33207 = false, _mut33208 = false, _mut33209 = false, _mut33210 = false, _mut33211 = false, _mut33212 = false, _mut33213 = false, _mut33214 = false, _mut33215 = false, _mut33216 = false, _mut33217 = false, _mut33218 = false, _mut33219 = false, _mut33220 = false, _mut33221 = false, _mut33222 = false, _mut33223 = false, _mut33224 = false, _mut33225 = false, _mut33226 = false, _mut33227 = false, _mut33228 = false, _mut33229 = false, _mut33230 = false, _mut33231 = false, _mut33232 = false, _mut33233 = false, _mut33234 = false, _mut33235 = false, _mut33236 = false, _mut33237 = false, _mut33238 = false, _mut33239 = false, _mut33240 = false, _mut33241 = false, _mut33242 = false, _mut33243 = false, _mut33244 = false, _mut33245 = false, _mut33246 = false, _mut33247 = false, _mut33248 = false, _mut33249 = false, _mut33250 = false, _mut33251 = false, _mut33252 = false, _mut33253 = false, _mut33254 = false, _mut33255 = false, _mut33256 = false, _mut33257 = false, _mut33258 = false, _mut33259 = false, _mut33260 = false, _mut33261 = false, _mut33262 = false, _mut33263 = false, _mut33264 = false, _mut33265 = false, _mut33266 = false, _mut33267 = false, _mut33268 = false, _mut33269 = false, _mut33270 = false, _mut33271 = false, _mut33272 = false, _mut33273 = false, _mut33274 = false, _mut33275 = false, _mut33276 = false, _mut33277 = false, _mut33278 = false, _mut33279 = false, _mut33280 = false, _mut33281 = false, _mut33282 = false, _mut33283 = false, _mut33284 = false, _mut33285 = false, _mut33286 = false, _mut33287 = false, _mut33288 = false, _mut33289 = false, _mut33290 = false, _mut33291 = false, _mut33292 = false, _mut33293 = false, _mut33294 = false, _mut33295 = false, _mut33296 = false, _mut33297 = false, _mut33298 = false, _mut33299 = false, _mut33300 = false, _mut33301 = false, _mut33302 = false, _mut33303 = false, _mut33304 = false, _mut33305 = false, _mut33306 = false, _mut33307 = false, _mut33308 = false, _mut33309 = false, _mut33310 = false, _mut33311 = false, _mut33312 = false, _mut33313 = false, _mut33314 = false, _mut33315 = false, _mut33316 = false, _mut33317 = false, _mut33318 = false, _mut33319 = false, _mut33320 = false, _mut33321 = false, _mut33322 = false, _mut33323 = false, _mut33324 = false, _mut33325 = false, _mut33326 = false, _mut33327 = false, _mut33328 = false, _mut33329 = false, _mut33330 = false, _mut33331 = false, _mut33332 = false, _mut33333 = false, _mut33334 = false, _mut33335 = false, _mut33336 = false, _mut33337 = false, _mut33338 = false, _mut33339 = false, _mut33340 = false, _mut33341 = false, _mut33342 = false, _mut33343 = false, _mut33344 = false, _mut33345 = false, _mut33346 = false, _mut33347 = false, _mut33348 = false, _mut33349 = false, _mut33350 = false, _mut33351 = false, _mut33352 = false, _mut33353 = false, _mut33354 = false, _mut33355 = false, _mut33356 = false, _mut33357 = false, _mut33358 = false, _mut33359 = false, _mut33360 = false, _mut33361 = false, _mut33362 = false, _mut33363 = false, _mut33364 = false, _mut33365 = false, _mut33366 = false, _mut33367 = false, _mut33368 = false, _mut33369 = false, _mut33370 = false, _mut33371 = false, _mut33372 = false, _mut33373 = false, _mut33374 = false, _mut33375 = false, _mut33376 = false, _mut33377 = false, _mut33378 = false, _mut33379 = false, _mut33380 = false, _mut33381 = false, _mut33382 = false, _mut33383 = false, _mut33384 = false, _mut33385 = false, _mut33386 = false, _mut33387 = false, _mut33388 = false, _mut33389 = false, _mut33390 = false, _mut33391 = false, _mut33392 = false, _mut33393 = false, _mut33394 = false, _mut33395 = false, _mut33396 = false, _mut33397 = false, _mut33398 = false, _mut33399 = false, _mut33400 = false, _mut33401 = false, _mut33402 = false, _mut33403 = false, _mut33404 = false, _mut33405 = false, _mut33406 = false, _mut33407 = false, _mut33408 = false, _mut33409 = false, _mut33410 = false, _mut33411 = false, _mut33412 = false, _mut33413 = false, _mut33414 = false, _mut33415 = false, _mut33416 = false, _mut33417 = false, _mut33418 = false, _mut33419 = false, _mut33420 = false, _mut33421 = false, _mut33422 = false, _mut33423 = false, _mut33424 = false, _mut33425 = false, _mut33426 = false, _mut33427 = false, _mut33428 = false, _mut33429 = false, _mut33430 = false, _mut33431 = false, _mut33432 = false, _mut33433 = false, _mut33434 = false, _mut33435 = false, _mut33436 = false, _mut33437 = false, _mut33438 = false, _mut33439 = false, _mut33440 = false, _mut33441 = false, _mut33442 = false, _mut33443 = false, _mut33444 = false, _mut33445 = false, _mut33446 = false, _mut33447 = false, _mut33448 = false, _mut33449 = false, _mut33450 = false, _mut33451 = false, _mut33452 = false, _mut33453 = false, _mut33454 = false, _mut33455 = false, _mut33456 = false;

    /**
     * Householder vectors.
     */
    private final double[][] householderVectors;

    /**
     * Main diagonal.
     */
    private final double[] main;

    /**
     * Secondary diagonal.
     */
    private final double[] secondary;

    /**
     * Cached value of Q.
     */
    private RealMatrix cachedQ;

    /**
     * Cached value of Qt.
     */
    private RealMatrix cachedQt;

    /**
     * Cached value of T.
     */
    private RealMatrix cachedT;

    /**
     * Build the transformation to tridiagonal shape of a symmetrical matrix.
     * <p>The specified matrix is assumed to be symmetrical without any check.
     * Only the upper triangular part of the matrix is used.</p>
     *
     * @param matrix Symmetrical matrix to transform.
     * @throws NonSquareMatrixException if the matrix is not square.
     */
    TriDiagonalTransformer(RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.TriDiagonalTransformer_61");
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int m = matrix.getRowDimension();
        householderVectors = matrix.getData();
        main = new double[m];
        secondary = new double[AOR_minus(m, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.TriDiagonalTransformer_61", _mut33206, _mut33207, _mut33208, _mut33209)];
        cachedQ = null;
        cachedQt = null;
        cachedT = null;
        // transform matrix
        transform();
    }

    /**
     * Returns the matrix Q of the transform.
     * <p>Q is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the Q matrix
     */
    public RealMatrix getQ() {
        if (cachedQ == null) {
            cachedQ = getQT().transpose();
        }
        return cachedQ;
    }

    /**
     * Returns the transpose of the matrix Q of the transform.
     * <p>Q is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the Q matrix
     */
    public RealMatrix getQT() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
        if (cachedQt == null) {
            final int m = householderVectors.length;
            double[][] qta = new double[m][m];
            // build up first part of the matrix by applying Householder transforms
            for (int k = m - 1; ROR_greater_equals(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33283, _mut33284, _mut33285, _mut33286, _mut33287); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
                final double[] hK = householderVectors[AOR_minus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33210, _mut33211, _mut33212, _mut33213)];
                qta[k][k] = 1;
                if (ROR_not_equals(hK[k], 0.0, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33214, _mut33215, _mut33216, _mut33217, _mut33218)) {
                    final double inv = AOR_divide(1.0, (AOR_multiply(secondary[AOR_minus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33219, _mut33220, _mut33221, _mut33222)], hK[k], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33223, _mut33224, _mut33225, _mut33226)), "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33227, _mut33228, _mut33229, _mut33230);
                    double beta = AOR_divide(1.0, secondary[AOR_minus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33231, _mut33232, _mut33233, _mut33234)], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33235, _mut33236, _mut33237, _mut33238);
                    qta[k][k] = AOR_plus(1, AOR_multiply(beta, hK[k], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33239, _mut33240, _mut33241, _mut33242), "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33243, _mut33244, _mut33245, _mut33246);
                    for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33251, _mut33252, _mut33253, _mut33254, _mut33255); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
                        qta[k][i] = AOR_multiply(beta, hK[i], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33247, _mut33248, _mut33249, _mut33250);
                    }
                    for (int j = k + 1; ROR_less(j, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33278, _mut33279, _mut33280, _mut33281, _mut33282); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
                        beta = 0;
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33260, _mut33261, _mut33262, _mut33263, _mut33264); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
                            beta += AOR_multiply(qta[j][i], hK[i], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33256, _mut33257, _mut33258, _mut33259);
                        }
                        beta *= inv;
                        qta[j][k] = AOR_multiply(beta, hK[k], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33265, _mut33266, _mut33267, _mut33268);
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33273, _mut33274, _mut33275, _mut33276, _mut33277); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96");
                            qta[j][i] += AOR_multiply(beta, hK[i], "org.apache.commons.math3.linear.TriDiagonalTransformer.getQT_96", _mut33269, _mut33270, _mut33271, _mut33272);
                        }
                    }
                }
            }
            qta[0][0] = 1;
            cachedQt = MatrixUtils.createRealMatrix(qta);
        }
        // return the cached matrix
        return cachedQt;
    }

    /**
     * Returns the tridiagonal matrix T of the transform.
     * @return the T matrix
     */
    public RealMatrix getT() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137");
        if (cachedT == null) {
            final int m = main.length;
            double[][] ta = new double[m][m];
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33314, _mut33315, _mut33316, _mut33317, _mut33318); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137");
                ta[i][i] = main[i];
                if (ROR_greater(i, 0, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33288, _mut33289, _mut33290, _mut33291, _mut33292)) {
                    ta[i][AOR_minus(i, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33293, _mut33294, _mut33295, _mut33296)] = secondary[AOR_minus(i, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33297, _mut33298, _mut33299, _mut33300)];
                }
                if (ROR_less(i, AOR_minus(main.length, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33301, _mut33302, _mut33303, _mut33304), "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33305, _mut33306, _mut33307, _mut33308, _mut33309)) {
                    ta[i][AOR_plus(i, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.getT_137", _mut33310, _mut33311, _mut33312, _mut33313)] = secondary[i];
                }
            }
            cachedT = MatrixUtils.createRealMatrix(ta);
        }
        // return the cached matrix
        return cachedT;
    }

    /**
     * Get the Householder vectors of the transform.
     * <p>Note that since this class is only intended for internal use,
     * it returns directly a reference to its internal arrays, not a copy.</p>
     * @return the main diagonal elements of the B matrix
     */
    double[][] getHouseholderVectorsRef() {
        return householderVectors;
    }

    /**
     * Get the main diagonal elements of the matrix T of the transform.
     * <p>Note that since this class is only intended for internal use,
     * it returns directly a reference to its internal arrays, not a copy.</p>
     * @return the main diagonal elements of the T matrix
     */
    double[] getMainDiagonalRef() {
        return main;
    }

    /**
     * Get the secondary diagonal elements of the matrix T of the transform.
     * <p>Note that since this class is only intended for internal use,
     * it returns directly a reference to its internal arrays, not a copy.</p>
     * @return the secondary diagonal elements of the T matrix
     */
    double[] getSecondaryDiagonalRef() {
        return secondary;
    }

    /**
     * Transform original matrix to tridiagonal form.
     * <p>Transformation is done using Householder transforms.</p>
     */
    private void transform() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
        final int m = householderVectors.length;
        final double[] z = new double[m];
        for (int k = 0; ROR_less(k, AOR_minus(m, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33436, _mut33437, _mut33438, _mut33439), "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33440, _mut33441, _mut33442, _mut33443, _mut33444); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
            // zero-out a row and a column simultaneously
            final double[] hK = householderVectors[k];
            main[k] = hK[k];
            double xNormSqr = 0;
            for (int j = k + 1; ROR_less(j, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33323, _mut33324, _mut33325, _mut33326, _mut33327); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                final double c = hK[j];
                xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33319, _mut33320, _mut33321, _mut33322);
            }
            final double a = (ROR_greater(hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33328, _mut33329, _mut33330, _mut33331)], 0, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33332, _mut33333, _mut33334, _mut33335, _mut33336)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            secondary[k] = a;
            if (ROR_not_equals(a, 0.0, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33337, _mut33338, _mut33339, _mut33340, _mut33341)) {
                hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33342, _mut33343, _mut33344, _mut33345)] -= a;
                final double beta = AOR_divide(-1, (AOR_multiply(a, hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33346, _mut33347, _mut33348, _mut33349)], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33350, _mut33351, _mut33352, _mut33353)), "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33354, _mut33355, _mut33356, _mut33357);
                // 2) access is cache-friendly for a matrix stored in rows
                Arrays.fill(z, AOR_plus(k, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33358, _mut33359, _mut33360, _mut33361), m, 0);
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33387, _mut33388, _mut33389, _mut33390, _mut33391); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                    final double[] hI = householderVectors[i];
                    final double hKI = hK[i];
                    double zI = AOR_multiply(hI[i], hKI, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33362, _mut33363, _mut33364, _mut33365);
                    for (int j = i + 1; ROR_less(j, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33374, _mut33375, _mut33376, _mut33377, _mut33378); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                        final double hIJ = hI[j];
                        zI += AOR_multiply(hIJ, hK[j], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33366, _mut33367, _mut33368, _mut33369);
                        z[j] += AOR_multiply(hIJ, hKI, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33370, _mut33371, _mut33372, _mut33373);
                    }
                    z[i] = AOR_multiply(beta, (AOR_plus(z[i], zI, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33379, _mut33380, _mut33381, _mut33382)), "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33383, _mut33384, _mut33385, _mut33386);
                }
                // compute gamma = beta vT z / 2
                double gamma = 0;
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33396, _mut33397, _mut33398, _mut33399, _mut33400); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                    gamma += AOR_multiply(z[i], hK[i], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33392, _mut33393, _mut33394, _mut33395);
                }
                gamma *= AOR_divide(beta, 2, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33401, _mut33402, _mut33403, _mut33404);
                // compute z = z - gamma v
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33409, _mut33410, _mut33411, _mut33412, _mut33413); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                    z[i] -= AOR_multiply(gamma, hK[i], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33405, _mut33406, _mut33407, _mut33408);
                }
                // only the upper triangular part of the matrix is updated
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33431, _mut33432, _mut33433, _mut33434, _mut33435); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                    final double[] hI = householderVectors[i];
                    for (int j = i; ROR_less(j, m, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33426, _mut33427, _mut33428, _mut33429, _mut33430); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191");
                        hI[j] -= AOR_plus(AOR_multiply(hK[i], z[j], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33414, _mut33415, _mut33416, _mut33417), AOR_multiply(z[i], hK[j], "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33418, _mut33419, _mut33420, _mut33421), "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33422, _mut33423, _mut33424, _mut33425);
                    }
                }
            }
        }
        main[AOR_minus(m, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33445, _mut33446, _mut33447, _mut33448)] = householderVectors[AOR_minus(m, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33453, _mut33454, _mut33455, _mut33456)][AOR_minus(m, 1, "org.apache.commons.math3.linear.TriDiagonalTransformer.transform_191", _mut33449, _mut33450, _mut33451, _mut33452)];
    }
}
