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

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class transforming a general real matrix to Hessenberg form.
 * <p>A m &times; m matrix A can be written as the product of three matrices: A = P
 * &times; H &times; P<sup>T</sup> with P an orthogonal matrix and H a Hessenberg
 * matrix. Both P and H are m &times; m matrices.</p>
 * <p>Transformation to Hessenberg form is often not a goal by itself, but it is an
 * intermediate step in more general decomposition algorithms like
 * {@link EigenDecomposition eigen decomposition}. This class is therefore
 * intended for internal use by the library and is not public. As a consequence
 * of this explicitly limited scope, many methods directly returns references to
 * internal arrays, not copies.</p>
 * <p>This class is based on the method orthes in class EigenvalueDecomposition
 * from the <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library.</p>
 *
 * @see <a href="http://mathworld.wolfram.com/HessenbergDecomposition.html">MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Householder_transformation">Householder Transformations</a>
 * @since 3.1
 */
class HessenbergTransformer {

    @Conditional
    public static boolean _mut32315 = false, _mut32316 = false, _mut32317 = false, _mut32318 = false, _mut32319 = false, _mut32320 = false, _mut32321 = false, _mut32322 = false, _mut32323 = false, _mut32324 = false, _mut32325 = false, _mut32326 = false, _mut32327 = false, _mut32328 = false, _mut32329 = false, _mut32330 = false, _mut32331 = false, _mut32332 = false, _mut32333 = false, _mut32334 = false, _mut32335 = false, _mut32336 = false, _mut32337 = false, _mut32338 = false, _mut32339 = false, _mut32340 = false, _mut32341 = false, _mut32342 = false, _mut32343 = false, _mut32344 = false, _mut32345 = false, _mut32346 = false, _mut32347 = false, _mut32348 = false, _mut32349 = false, _mut32350 = false, _mut32351 = false, _mut32352 = false, _mut32353 = false, _mut32354 = false, _mut32355 = false, _mut32356 = false, _mut32357 = false, _mut32358 = false, _mut32359 = false, _mut32360 = false, _mut32361 = false, _mut32362 = false, _mut32363 = false, _mut32364 = false, _mut32365 = false, _mut32366 = false, _mut32367 = false, _mut32368 = false, _mut32369 = false, _mut32370 = false, _mut32371 = false, _mut32372 = false, _mut32373 = false, _mut32374 = false, _mut32375 = false, _mut32376 = false, _mut32377 = false, _mut32378 = false, _mut32379 = false, _mut32380 = false, _mut32381 = false, _mut32382 = false, _mut32383 = false, _mut32384 = false, _mut32385 = false, _mut32386 = false, _mut32387 = false, _mut32388 = false, _mut32389 = false, _mut32390 = false, _mut32391 = false, _mut32392 = false, _mut32393 = false, _mut32394 = false, _mut32395 = false, _mut32396 = false, _mut32397 = false, _mut32398 = false, _mut32399 = false, _mut32400 = false, _mut32401 = false, _mut32402 = false, _mut32403 = false, _mut32404 = false, _mut32405 = false, _mut32406 = false, _mut32407 = false, _mut32408 = false, _mut32409 = false, _mut32410 = false, _mut32411 = false, _mut32412 = false, _mut32413 = false, _mut32414 = false, _mut32415 = false, _mut32416 = false, _mut32417 = false, _mut32418 = false, _mut32419 = false, _mut32420 = false, _mut32421 = false, _mut32422 = false, _mut32423 = false, _mut32424 = false, _mut32425 = false, _mut32426 = false, _mut32427 = false, _mut32428 = false, _mut32429 = false, _mut32430 = false, _mut32431 = false, _mut32432 = false, _mut32433 = false, _mut32434 = false, _mut32435 = false, _mut32436 = false, _mut32437 = false, _mut32438 = false, _mut32439 = false, _mut32440 = false, _mut32441 = false, _mut32442 = false, _mut32443 = false, _mut32444 = false, _mut32445 = false, _mut32446 = false, _mut32447 = false, _mut32448 = false, _mut32449 = false, _mut32450 = false, _mut32451 = false, _mut32452 = false, _mut32453 = false, _mut32454 = false, _mut32455 = false, _mut32456 = false, _mut32457 = false, _mut32458 = false, _mut32459 = false, _mut32460 = false, _mut32461 = false, _mut32462 = false, _mut32463 = false, _mut32464 = false, _mut32465 = false, _mut32466 = false, _mut32467 = false, _mut32468 = false, _mut32469 = false, _mut32470 = false, _mut32471 = false, _mut32472 = false, _mut32473 = false, _mut32474 = false, _mut32475 = false, _mut32476 = false, _mut32477 = false, _mut32478 = false, _mut32479 = false, _mut32480 = false, _mut32481 = false, _mut32482 = false, _mut32483 = false, _mut32484 = false, _mut32485 = false, _mut32486 = false, _mut32487 = false, _mut32488 = false, _mut32489 = false, _mut32490 = false, _mut32491 = false, _mut32492 = false, _mut32493 = false, _mut32494 = false, _mut32495 = false, _mut32496 = false, _mut32497 = false, _mut32498 = false, _mut32499 = false, _mut32500 = false, _mut32501 = false, _mut32502 = false, _mut32503 = false, _mut32504 = false, _mut32505 = false, _mut32506 = false, _mut32507 = false, _mut32508 = false, _mut32509 = false, _mut32510 = false, _mut32511 = false, _mut32512 = false, _mut32513 = false, _mut32514 = false, _mut32515 = false, _mut32516 = false, _mut32517 = false, _mut32518 = false, _mut32519 = false, _mut32520 = false;

    /**
     * Householder vectors.
     */
    private final double[][] householderVectors;

    /**
     * Temporary storage vector.
     */
    private final double[] ort;

    /**
     * Cached value of P.
     */
    private RealMatrix cachedP;

    /**
     * Cached value of Pt.
     */
    private RealMatrix cachedPt;

    /**
     * Cached value of H.
     */
    private RealMatrix cachedH;

    /**
     * Build the transformation to Hessenberg form of a general matrix.
     *
     * @param matrix matrix to transform
     * @throws NonSquareMatrixException if the matrix is not square
     */
    HessenbergTransformer(final RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        final int m = matrix.getRowDimension();
        householderVectors = matrix.getData();
        ort = new double[m];
        cachedP = null;
        cachedPt = null;
        cachedH = null;
        // transform matrix
        transform();
    }

    /**
     * Returns the matrix P of the transform.
     * <p>P is an orthogonal matrix, i.e. its inverse is also its transpose.</p>
     *
     * @return the P matrix
     */
    public RealMatrix getP() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
        if (cachedP == null) {
            final int n = householderVectors.length;
            final int high = AOR_minus(n, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32315, _mut32316, _mut32317, _mut32318);
            final double[][] pa = new double[n][n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32329, _mut32330, _mut32331, _mut32332, _mut32333); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32324, _mut32325, _mut32326, _mut32327, _mut32328); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                    pa[i][j] = (ROR_equals(i, j, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32319, _mut32320, _mut32321, _mut32322, _mut32323)) ? 1 : 0;
                }
            }
            for (int m = high - 1; ROR_greater_equals(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32387, _mut32388, _mut32389, _mut32390, _mut32391); m--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                if (ROR_not_equals(householderVectors[m][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32334, _mut32335, _mut32336, _mut32337)], 0.0, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32338, _mut32339, _mut32340, _mut32341, _mut32342)) {
                    for (int i = m + 1; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32347, _mut32348, _mut32349, _mut32350, _mut32351); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                        ort[i] = householderVectors[i][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32343, _mut32344, _mut32345, _mut32346)];
                    }
                    for (int j = m; ROR_less_equals(j, high, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32382, _mut32383, _mut32384, _mut32385, _mut32386); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                        double g = 0.0;
                        for (int i = m; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32356, _mut32357, _mut32358, _mut32359, _mut32360); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                            g += AOR_multiply(ort[i], pa[i][j], "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32352, _mut32353, _mut32354, _mut32355);
                        }
                        // Double division avoids possible underflow
                        g = AOR_divide((AOR_divide(g, ort[m], "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32361, _mut32362, _mut32363, _mut32364)), householderVectors[m][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32365, _mut32366, _mut32367, _mut32368)], "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32369, _mut32370, _mut32371, _mut32372);
                        for (int i = m; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32377, _mut32378, _mut32379, _mut32380, _mut32381); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getP_82");
                            pa[i][j] += AOR_multiply(g, ort[i], "org.apache.commons.math3.linear.HessenbergTransformer.getP_82", _mut32373, _mut32374, _mut32375, _mut32376);
                        }
                    }
                }
            }
            cachedP = MatrixUtils.createRealMatrix(pa);
        }
        return cachedP;
    }

    /**
     * Returns the transpose of the matrix P of the transform.
     * <p>P is an orthogonal matrix, i.e. its inverse is also its transpose.</p>
     *
     * @return the transpose of the P matrix
     */
    public RealMatrix getPT() {
        if (cachedPt == null) {
            cachedPt = getP().transpose();
        }
        // return the cached matrix
        return cachedPt;
    }

    /**
     * Returns the Hessenberg matrix H of the transform.
     *
     * @return the H matrix
     */
    public RealMatrix getH() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getH_142");
        if (cachedH == null) {
            final int m = householderVectors.length;
            final double[][] h = new double[m][m];
            for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.linear.HessenbergTransformer.getH_142", _mut32410, _mut32411, _mut32412, _mut32413, _mut32414); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getH_142");
                if (ROR_greater(i, 0, "org.apache.commons.math3.linear.HessenbergTransformer.getH_142", _mut32392, _mut32393, _mut32394, _mut32395, _mut32396)) {
                    // copy the entry of the lower sub-diagonal
                    h[i][AOR_minus(i, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getH_142", _mut32397, _mut32398, _mut32399, _mut32400)] = householderVectors[i][AOR_minus(i, 1, "org.apache.commons.math3.linear.HessenbergTransformer.getH_142", _mut32401, _mut32402, _mut32403, _mut32404)];
                }
                // copy upper triangular part of the matrix
                for (int j = i; ROR_less(j, m, "org.apache.commons.math3.linear.HessenbergTransformer.getH_142", _mut32405, _mut32406, _mut32407, _mut32408, _mut32409); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.getH_142");
                    h[i][j] = householderVectors[i][j];
                }
            }
            cachedH = MatrixUtils.createRealMatrix(h);
        }
        // return the cached matrix
        return cachedH;
    }

    /**
     * Get the Householder vectors of the transform.
     * <p>Note that since this class is only intended for internal use, it returns
     * directly a reference to its internal arrays, not a copy.</p>
     *
     * @return the main diagonal elements of the B matrix
     */
    double[][] getHouseholderVectorsRef() {
        return householderVectors;
    }

    /**
     * Transform original matrix to Hessenberg form.
     * <p>Transformation is done using Householder transforms.</p>
     */
    private void transform() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
        final int n = householderVectors.length;
        final int high = AOR_minus(n, 1, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32415, _mut32416, _mut32417, _mut32418);
        for (int m = 1; ROR_less_equals(m, AOR_minus(high, 1, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32512, _mut32513, _mut32514, _mut32515), "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32516, _mut32517, _mut32518, _mut32519, _mut32520); m++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
            // Scale column.
            double scale = 0;
            for (int i = m; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32423, _mut32424, _mut32425, _mut32426, _mut32427); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                scale += FastMath.abs(householderVectors[i][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32419, _mut32420, _mut32421, _mut32422)]);
            }
            if (!Precision.equals(scale, 0)) {
                // Compute Householder transformation.
                double h = 0;
                for (int i = high; ROR_greater_equals(i, m, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32440, _mut32441, _mut32442, _mut32443, _mut32444); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                    ort[i] = AOR_divide(householderVectors[i][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32428, _mut32429, _mut32430, _mut32431)], scale, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32432, _mut32433, _mut32434, _mut32435);
                    h += AOR_multiply(ort[i], ort[i], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32436, _mut32437, _mut32438, _mut32439);
                }
                final double g = (ROR_greater(ort[m], 0, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32445, _mut32446, _mut32447, _mut32448, _mut32449)) ? -FastMath.sqrt(h) : FastMath.sqrt(h);
                h -= AOR_multiply(ort[m], g, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32450, _mut32451, _mut32452, _mut32453);
                ort[m] -= g;
                for (int j = m; ROR_less(j, n, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32472, _mut32473, _mut32474, _mut32475, _mut32476); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                    double f = 0;
                    for (int i = high; ROR_greater_equals(i, m, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32458, _mut32459, _mut32460, _mut32461, _mut32462); i--) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                        f += AOR_multiply(ort[i], householderVectors[i][j], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32454, _mut32455, _mut32456, _mut32457);
                    }
                    f /= h;
                    for (int i = m; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32467, _mut32468, _mut32469, _mut32470, _mut32471); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                        householderVectors[i][j] -= AOR_multiply(f, ort[i], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32463, _mut32464, _mut32465, _mut32466);
                    }
                }
                for (int i = 0; ROR_less_equals(i, high, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32495, _mut32496, _mut32497, _mut32498, _mut32499); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                    double f = 0;
                    for (int j = high; ROR_greater_equals(j, m, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32481, _mut32482, _mut32483, _mut32484, _mut32485); j--) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                        f += AOR_multiply(ort[j], householderVectors[i][j], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32477, _mut32478, _mut32479, _mut32480);
                    }
                    f /= h;
                    for (int j = m; ROR_less_equals(j, high, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32490, _mut32491, _mut32492, _mut32493, _mut32494); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.HessenbergTransformer.transform_179");
                        householderVectors[i][j] -= AOR_multiply(f, ort[j], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32486, _mut32487, _mut32488, _mut32489);
                    }
                }
                ort[m] = AOR_multiply(scale, ort[m], "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32500, _mut32501, _mut32502, _mut32503);
                householderVectors[m][AOR_minus(m, 1, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32504, _mut32505, _mut32506, _mut32507)] = AOR_multiply(scale, g, "org.apache.commons.math3.linear.HessenbergTransformer.transform_179", _mut32508, _mut32509, _mut32510, _mut32511);
            }
        }
    }
}
