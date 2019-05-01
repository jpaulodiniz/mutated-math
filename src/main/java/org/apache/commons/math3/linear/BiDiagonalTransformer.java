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
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class transforming any matrix to bi-diagonal shape.
 * <p>Any m &times; n matrix A can be written as the product of three matrices:
 * A = U &times; B &times; V<sup>T</sup> with U an m &times; m orthogonal matrix,
 * B an m &times; n bi-diagonal matrix (lower diagonal if m &lt; n, upper diagonal
 * otherwise), and V an n &times; n orthogonal matrix.</p>
 * <p>Transformation to bi-diagonal shape is often not a goal by itself, but it is
 * an intermediate step in more general decomposition algorithms like {@link
 * SingularValueDecomposition Singular Value Decomposition}. This class is therefore
 * intended for internal use by the library and is not public. As a consequence of
 * this explicitly limited scope, many methods directly returns references to
 * internal arrays, not copies.</p>
 * @since 2.0
 */
class BiDiagonalTransformer {

    @Conditional
    public static boolean _mut26132 = false, _mut26133 = false, _mut26134 = false, _mut26135 = false, _mut26136 = false, _mut26137 = false, _mut26138 = false, _mut26139 = false, _mut26140 = false, _mut26141 = false, _mut26142 = false, _mut26143 = false, _mut26144 = false, _mut26145 = false, _mut26146 = false, _mut26147 = false, _mut26148 = false, _mut26149 = false, _mut26150 = false, _mut26151 = false, _mut26152 = false, _mut26153 = false, _mut26154 = false, _mut26155 = false, _mut26156 = false, _mut26157 = false, _mut26158 = false, _mut26159 = false, _mut26160 = false, _mut26161 = false, _mut26162 = false, _mut26163 = false, _mut26164 = false, _mut26165 = false, _mut26166 = false, _mut26167 = false, _mut26168 = false, _mut26169 = false, _mut26170 = false, _mut26171 = false, _mut26172 = false, _mut26173 = false, _mut26174 = false, _mut26175 = false, _mut26176 = false, _mut26177 = false, _mut26178 = false, _mut26179 = false, _mut26180 = false, _mut26181 = false, _mut26182 = false, _mut26183 = false, _mut26184 = false, _mut26185 = false, _mut26186 = false, _mut26187 = false, _mut26188 = false, _mut26189 = false, _mut26190 = false, _mut26191 = false, _mut26192 = false, _mut26193 = false, _mut26194 = false, _mut26195 = false, _mut26196 = false, _mut26197 = false, _mut26198 = false, _mut26199 = false, _mut26200 = false, _mut26201 = false, _mut26202 = false, _mut26203 = false, _mut26204 = false, _mut26205 = false, _mut26206 = false, _mut26207 = false, _mut26208 = false, _mut26209 = false, _mut26210 = false, _mut26211 = false, _mut26212 = false, _mut26213 = false, _mut26214 = false, _mut26215 = false, _mut26216 = false, _mut26217 = false, _mut26218 = false, _mut26219 = false, _mut26220 = false, _mut26221 = false, _mut26222 = false, _mut26223 = false, _mut26224 = false, _mut26225 = false, _mut26226 = false, _mut26227 = false, _mut26228 = false, _mut26229 = false, _mut26230 = false, _mut26231 = false, _mut26232 = false, _mut26233 = false, _mut26234 = false, _mut26235 = false, _mut26236 = false, _mut26237 = false, _mut26238 = false, _mut26239 = false, _mut26240 = false, _mut26241 = false, _mut26242 = false, _mut26243 = false, _mut26244 = false, _mut26245 = false, _mut26246 = false, _mut26247 = false, _mut26248 = false, _mut26249 = false, _mut26250 = false, _mut26251 = false, _mut26252 = false, _mut26253 = false, _mut26254 = false, _mut26255 = false, _mut26256 = false, _mut26257 = false, _mut26258 = false, _mut26259 = false, _mut26260 = false, _mut26261 = false, _mut26262 = false, _mut26263 = false, _mut26264 = false, _mut26265 = false, _mut26266 = false, _mut26267 = false, _mut26268 = false, _mut26269 = false, _mut26270 = false, _mut26271 = false, _mut26272 = false, _mut26273 = false, _mut26274 = false, _mut26275 = false, _mut26276 = false, _mut26277 = false, _mut26278 = false, _mut26279 = false, _mut26280 = false, _mut26281 = false, _mut26282 = false, _mut26283 = false, _mut26284 = false, _mut26285 = false, _mut26286 = false, _mut26287 = false, _mut26288 = false, _mut26289 = false, _mut26290 = false, _mut26291 = false, _mut26292 = false, _mut26293 = false, _mut26294 = false, _mut26295 = false, _mut26296 = false, _mut26297 = false, _mut26298 = false, _mut26299 = false, _mut26300 = false, _mut26301 = false, _mut26302 = false, _mut26303 = false, _mut26304 = false, _mut26305 = false, _mut26306 = false, _mut26307 = false, _mut26308 = false, _mut26309 = false, _mut26310 = false, _mut26311 = false, _mut26312 = false, _mut26313 = false, _mut26314 = false, _mut26315 = false, _mut26316 = false, _mut26317 = false, _mut26318 = false, _mut26319 = false, _mut26320 = false, _mut26321 = false, _mut26322 = false, _mut26323 = false, _mut26324 = false, _mut26325 = false, _mut26326 = false, _mut26327 = false, _mut26328 = false, _mut26329 = false, _mut26330 = false, _mut26331 = false, _mut26332 = false, _mut26333 = false, _mut26334 = false, _mut26335 = false, _mut26336 = false, _mut26337 = false, _mut26338 = false, _mut26339 = false, _mut26340 = false, _mut26341 = false, _mut26342 = false, _mut26343 = false, _mut26344 = false, _mut26345 = false, _mut26346 = false, _mut26347 = false, _mut26348 = false, _mut26349 = false, _mut26350 = false, _mut26351 = false, _mut26352 = false, _mut26353 = false, _mut26354 = false, _mut26355 = false, _mut26356 = false, _mut26357 = false, _mut26358 = false, _mut26359 = false, _mut26360 = false, _mut26361 = false, _mut26362 = false, _mut26363 = false, _mut26364 = false, _mut26365 = false, _mut26366 = false, _mut26367 = false, _mut26368 = false, _mut26369 = false, _mut26370 = false, _mut26371 = false, _mut26372 = false, _mut26373 = false, _mut26374 = false, _mut26375 = false, _mut26376 = false, _mut26377 = false, _mut26378 = false, _mut26379 = false, _mut26380 = false, _mut26381 = false, _mut26382 = false, _mut26383 = false, _mut26384 = false, _mut26385 = false, _mut26386 = false, _mut26387 = false, _mut26388 = false, _mut26389 = false, _mut26390 = false, _mut26391 = false, _mut26392 = false, _mut26393 = false, _mut26394 = false, _mut26395 = false, _mut26396 = false, _mut26397 = false, _mut26398 = false, _mut26399 = false, _mut26400 = false, _mut26401 = false, _mut26402 = false, _mut26403 = false, _mut26404 = false, _mut26405 = false, _mut26406 = false, _mut26407 = false, _mut26408 = false, _mut26409 = false, _mut26410 = false, _mut26411 = false, _mut26412 = false, _mut26413 = false, _mut26414 = false, _mut26415 = false, _mut26416 = false, _mut26417 = false, _mut26418 = false, _mut26419 = false, _mut26420 = false, _mut26421 = false, _mut26422 = false, _mut26423 = false, _mut26424 = false, _mut26425 = false, _mut26426 = false, _mut26427 = false, _mut26428 = false, _mut26429 = false, _mut26430 = false, _mut26431 = false, _mut26432 = false, _mut26433 = false, _mut26434 = false, _mut26435 = false, _mut26436 = false, _mut26437 = false, _mut26438 = false, _mut26439 = false, _mut26440 = false, _mut26441 = false, _mut26442 = false, _mut26443 = false, _mut26444 = false, _mut26445 = false, _mut26446 = false, _mut26447 = false, _mut26448 = false, _mut26449 = false, _mut26450 = false, _mut26451 = false, _mut26452 = false, _mut26453 = false, _mut26454 = false, _mut26455 = false, _mut26456 = false, _mut26457 = false, _mut26458 = false, _mut26459 = false, _mut26460 = false, _mut26461 = false, _mut26462 = false, _mut26463 = false, _mut26464 = false, _mut26465 = false, _mut26466 = false, _mut26467 = false, _mut26468 = false, _mut26469 = false, _mut26470 = false, _mut26471 = false, _mut26472 = false, _mut26473 = false, _mut26474 = false, _mut26475 = false, _mut26476 = false, _mut26477 = false, _mut26478 = false, _mut26479 = false, _mut26480 = false, _mut26481 = false, _mut26482 = false, _mut26483 = false, _mut26484 = false, _mut26485 = false, _mut26486 = false, _mut26487 = false, _mut26488 = false, _mut26489 = false, _mut26490 = false, _mut26491 = false, _mut26492 = false, _mut26493 = false, _mut26494 = false, _mut26495 = false, _mut26496 = false, _mut26497 = false, _mut26498 = false, _mut26499 = false, _mut26500 = false, _mut26501 = false, _mut26502 = false, _mut26503 = false, _mut26504 = false, _mut26505 = false, _mut26506 = false, _mut26507 = false, _mut26508 = false, _mut26509 = false, _mut26510 = false, _mut26511 = false, _mut26512 = false, _mut26513 = false, _mut26514 = false, _mut26515 = false, _mut26516 = false, _mut26517 = false, _mut26518 = false, _mut26519 = false, _mut26520 = false, _mut26521 = false, _mut26522 = false, _mut26523 = false, _mut26524 = false, _mut26525 = false, _mut26526 = false, _mut26527 = false, _mut26528 = false, _mut26529 = false, _mut26530 = false, _mut26531 = false, _mut26532 = false, _mut26533 = false, _mut26534 = false, _mut26535 = false, _mut26536 = false, _mut26537 = false, _mut26538 = false, _mut26539 = false, _mut26540 = false, _mut26541 = false, _mut26542 = false, _mut26543 = false, _mut26544 = false, _mut26545 = false, _mut26546 = false, _mut26547 = false, _mut26548 = false, _mut26549 = false, _mut26550 = false, _mut26551 = false;

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
     * Cached value of U.
     */
    private RealMatrix cachedU;

    /**
     * Cached value of B.
     */
    private RealMatrix cachedB;

    /**
     * Cached value of V.
     */
    private RealMatrix cachedV;

    /**
     * Build the transformation to bi-diagonal shape of a matrix.
     * @param matrix the matrix to transform.
     */
    BiDiagonalTransformer(RealMatrix matrix) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.BiDiagonalTransformer_61");
        final int m = matrix.getRowDimension();
        final int n = matrix.getColumnDimension();
        final int p = FastMath.min(m, n);
        householderVectors = matrix.getData();
        main = new double[p];
        secondary = new double[AOR_minus(p, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.BiDiagonalTransformer_61", _mut26132, _mut26133, _mut26134, _mut26135)];
        cachedU = null;
        cachedB = null;
        cachedV = null;
        // transform matrix
        if (ROR_greater_equals(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.BiDiagonalTransformer_61", _mut26136, _mut26137, _mut26138, _mut26139, _mut26140)) {
            transformToUpperBiDiagonal();
        } else {
            transformToLowerBiDiagonal();
        }
    }

    /**
     * Returns the matrix U of the transform.
     * <p>U is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the U matrix
     */
    public RealMatrix getU() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
        if (cachedU == null) {
            final int m = householderVectors.length;
            final int n = householderVectors[0].length;
            final int p = main.length;
            final int diagOffset = (ROR_greater_equals(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26141, _mut26142, _mut26143, _mut26144, _mut26145)) ? 0 : 1;
            final double[] diagonal = (ROR_greater_equals(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26146, _mut26147, _mut26148, _mut26149, _mut26150)) ? main : secondary;
            double[][] ua = new double[m][m];
            // fill up the part of the matrix not affected by Householder transforms
            for (int k = m - 1; ROR_greater_equals(k, p, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26151, _mut26152, _mut26153, _mut26154, _mut26155); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
                ua[k][k] = 1;
            }
            // build up first part of the matrix by applying Householder transforms
            for (int k = p - 1; ROR_greater_equals(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26208, _mut26209, _mut26210, _mut26211, _mut26212); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
                final double[] hK = householderVectors[k];
                ua[k][k] = 1;
                if (ROR_not_equals(hK[AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26156, _mut26157, _mut26158, _mut26159)], 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26160, _mut26161, _mut26162, _mut26163, _mut26164)) {
                    for (int j = k; ROR_less(j, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26203, _mut26204, _mut26205, _mut26206, _mut26207); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
                        double alpha = 0;
                        for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26173, _mut26174, _mut26175, _mut26176, _mut26177); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
                            alpha -= AOR_multiply(ua[i][j], householderVectors[i][AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26165, _mut26166, _mut26167, _mut26168)], "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26169, _mut26170, _mut26171, _mut26172);
                        }
                        alpha /= AOR_multiply(diagonal[AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26178, _mut26179, _mut26180, _mut26181)], hK[AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26182, _mut26183, _mut26184, _mut26185)], "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26186, _mut26187, _mut26188, _mut26189);
                        for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26198, _mut26199, _mut26200, _mut26201, _mut26202); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87");
                            ua[i][j] += AOR_multiply(-alpha, householderVectors[i][AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26190, _mut26191, _mut26192, _mut26193)], "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26194, _mut26195, _mut26196, _mut26197);
                        }
                    }
                }
            }
            if (ROR_greater(diagOffset, 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.getU_87", _mut26213, _mut26214, _mut26215, _mut26216, _mut26217)) {
                ua[0][0] = 1;
            }
            cachedU = MatrixUtils.createRealMatrix(ua);
        }
        // return the cached matrix
        return cachedU;
    }

    /**
     * Returns the bi-diagonal matrix B of the transform.
     * @return the B matrix
     */
    public RealMatrix getB() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136");
        if (cachedB == null) {
            final int m = householderVectors.length;
            final int n = householderVectors[0].length;
            double[][] ba = new double[m][n];
            for (int i = 0; ROR_less(i, main.length, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26249, _mut26250, _mut26251, _mut26252, _mut26253); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136");
                ba[i][i] = main[i];
                if (ROR_less(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26218, _mut26219, _mut26220, _mut26221, _mut26222)) {
                    if (ROR_greater(i, 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26236, _mut26237, _mut26238, _mut26239, _mut26240)) {
                        ba[i][AOR_minus(i, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26241, _mut26242, _mut26243, _mut26244)] = secondary[AOR_minus(i, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26245, _mut26246, _mut26247, _mut26248)];
                    }
                } else {
                    if (ROR_less(i, AOR_minus(main.length, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26223, _mut26224, _mut26225, _mut26226), "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26227, _mut26228, _mut26229, _mut26230, _mut26231)) {
                        ba[i][AOR_plus(i, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.getB_136", _mut26232, _mut26233, _mut26234, _mut26235)] = secondary[i];
                    }
                }
            }
            cachedB = MatrixUtils.createRealMatrix(ba);
        }
        // return the cached matrix
        return cachedB;
    }

    /**
     * Returns the matrix V of the transform.
     * <p>V is an orthogonal matrix, i.e. its transpose is also its inverse.</p>
     * @return the V matrix
     */
    public RealMatrix getV() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
        if (cachedV == null) {
            final int m = householderVectors.length;
            final int n = householderVectors[0].length;
            final int p = main.length;
            final int diagOffset = (ROR_greater_equals(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26254, _mut26255, _mut26256, _mut26257, _mut26258)) ? 1 : 0;
            final double[] diagonal = (ROR_greater_equals(m, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26259, _mut26260, _mut26261, _mut26262, _mut26263)) ? secondary : main;
            double[][] va = new double[n][n];
            // fill up the part of the matrix not affected by Householder transforms
            for (int k = n - 1; ROR_greater_equals(k, p, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26264, _mut26265, _mut26266, _mut26267, _mut26268); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
                va[k][k] = 1;
            }
            // build up first part of the matrix by applying Householder transforms
            for (int k = p - 1; ROR_greater_equals(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26309, _mut26310, _mut26311, _mut26312, _mut26313); --k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
                final double[] hK = householderVectors[AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26269, _mut26270, _mut26271, _mut26272)];
                va[k][k] = 1;
                if (ROR_not_equals(hK[k], 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26273, _mut26274, _mut26275, _mut26276, _mut26277)) {
                    for (int j = k; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26304, _mut26305, _mut26306, _mut26307, _mut26308); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
                        double beta = 0;
                        for (int i = k; ROR_less(i, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26282, _mut26283, _mut26284, _mut26285, _mut26286); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
                            beta -= AOR_multiply(va[i][j], hK[i], "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26278, _mut26279, _mut26280, _mut26281);
                        }
                        beta /= AOR_multiply(diagonal[AOR_minus(k, diagOffset, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26287, _mut26288, _mut26289, _mut26290)], hK[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26291, _mut26292, _mut26293, _mut26294);
                        for (int i = k; ROR_less(i, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26299, _mut26300, _mut26301, _mut26302, _mut26303); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168");
                            va[i][j] += AOR_multiply(-beta, hK[i], "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26295, _mut26296, _mut26297, _mut26298);
                        }
                    }
                }
            }
            if (ROR_greater(diagOffset, 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.getV_168", _mut26314, _mut26315, _mut26316, _mut26317, _mut26318)) {
                va[0][0] = 1;
            }
            cachedV = MatrixUtils.createRealMatrix(va);
        }
        // return the cached matrix
        return cachedV;
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
     * Get the main diagonal elements of the matrix B of the transform.
     * <p>Note that since this class is only intended for internal use,
     * it returns directly a reference to its internal arrays, not a copy.</p>
     * @return the main diagonal elements of the B matrix
     */
    double[] getMainDiagonalRef() {
        return main;
    }

    /**
     * Get the secondary diagonal elements of the matrix B of the transform.
     * <p>Note that since this class is only intended for internal use,
     * it returns directly a reference to its internal arrays, not a copy.</p>
     * @return the secondary diagonal elements of the B matrix
     */
    double[] getSecondaryDiagonalRef() {
        return secondary;
    }

    /**
     * Check if the matrix is transformed to upper bi-diagonal.
     * @return true if the matrix is transformed to upper bi-diagonal
     */
    boolean isUpperBiDiagonal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.isUpperBiDiagonal_247");
        return ROR_greater_equals(householderVectors.length, householderVectors[0].length, "org.apache.commons.math3.linear.BiDiagonalTransformer.isUpperBiDiagonal_247", _mut26319, _mut26320, _mut26321, _mut26322, _mut26323);
    }

    /**
     * Transform original matrix to upper bi-diagonal form.
     * <p>Transformation is done using alternate Householder transforms
     * on columns and rows.</p>
     */
    private void transformToUpperBiDiagonal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
        final int m = householderVectors.length;
        final int n = householderVectors[0].length;
        for (int k = 0; ROR_less(k, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26437, _mut26438, _mut26439, _mut26440, _mut26441); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
            // zero-out a column
            double xNormSqr = 0;
            for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26328, _mut26329, _mut26330, _mut26331, _mut26332); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                final double c = householderVectors[i][k];
                xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26324, _mut26325, _mut26326, _mut26327);
            }
            final double[] hK = householderVectors[k];
            final double a = (ROR_greater(hK[k], 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26333, _mut26334, _mut26335, _mut26336, _mut26337)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            main[k] = a;
            if (ROR_not_equals(a, 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26338, _mut26339, _mut26340, _mut26341, _mut26342)) {
                hK[k] -= a;
                for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26365, _mut26366, _mut26367, _mut26368, _mut26369); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                    double alpha = 0;
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26347, _mut26348, _mut26349, _mut26350, _mut26351); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                        final double[] hI = householderVectors[i];
                        alpha -= AOR_multiply(hI[j], hI[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26343, _mut26344, _mut26345, _mut26346);
                    }
                    alpha /= AOR_multiply(a, householderVectors[k][k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26352, _mut26353, _mut26354, _mut26355);
                    for (int i = k; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26360, _mut26361, _mut26362, _mut26363, _mut26364); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                        final double[] hI = householderVectors[i];
                        hI[j] -= AOR_multiply(alpha, hI[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26356, _mut26357, _mut26358, _mut26359);
                    }
                }
            }
            if (ROR_less(k, AOR_minus(n, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26370, _mut26371, _mut26372, _mut26373), "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26374, _mut26375, _mut26376, _mut26377, _mut26378)) {
                // zero-out a row
                xNormSqr = 0;
                for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26383, _mut26384, _mut26385, _mut26386, _mut26387); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                    final double c = hK[j];
                    xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26379, _mut26380, _mut26381, _mut26382);
                }
                final double b = (ROR_greater(hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26388, _mut26389, _mut26390, _mut26391)], 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26392, _mut26393, _mut26394, _mut26395, _mut26396)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
                secondary[k] = b;
                if (ROR_not_equals(b, 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26397, _mut26398, _mut26399, _mut26400, _mut26401)) {
                    hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26402, _mut26403, _mut26404, _mut26405)] -= b;
                    for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26432, _mut26433, _mut26434, _mut26435, _mut26436); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                        final double[] hI = householderVectors[i];
                        double beta = 0;
                        for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26410, _mut26411, _mut26412, _mut26413, _mut26414); ++j) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                            beta -= AOR_multiply(hI[j], hK[j], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26406, _mut26407, _mut26408, _mut26409);
                        }
                        beta /= AOR_multiply(b, hK[AOR_plus(k, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26415, _mut26416, _mut26417, _mut26418)], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26419, _mut26420, _mut26421, _mut26422);
                        for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26427, _mut26428, _mut26429, _mut26430, _mut26431); ++j) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256");
                            hI[j] -= AOR_multiply(beta, hK[j], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToUpperBiDiagonal_256", _mut26423, _mut26424, _mut26425, _mut26426);
                        }
                    }
                }
            }
        }
    }

    /**
     * Transform original matrix to lower bi-diagonal form.
     * <p>Transformation is done using alternate Householder transforms
     * on rows and columns.</p>
     */
    private void transformToLowerBiDiagonal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
        final int m = householderVectors.length;
        final int n = householderVectors[0].length;
        for (int k = 0; ROR_less(k, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26547, _mut26548, _mut26549, _mut26550, _mut26551); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
            // zero-out a row
            final double[] hK = householderVectors[k];
            double xNormSqr = 0;
            for (int j = k; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26446, _mut26447, _mut26448, _mut26449, _mut26450); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                final double c = hK[j];
                xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26442, _mut26443, _mut26444, _mut26445);
            }
            final double a = (ROR_greater(hK[k], 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26451, _mut26452, _mut26453, _mut26454, _mut26455)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
            main[k] = a;
            if (ROR_not_equals(a, 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26456, _mut26457, _mut26458, _mut26459, _mut26460)) {
                hK[k] -= a;
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26483, _mut26484, _mut26485, _mut26486, _mut26487); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                    final double[] hI = householderVectors[i];
                    double alpha = 0;
                    for (int j = k; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26465, _mut26466, _mut26467, _mut26468, _mut26469); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                        alpha -= AOR_multiply(hI[j], hK[j], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26461, _mut26462, _mut26463, _mut26464);
                    }
                    alpha /= AOR_multiply(a, householderVectors[k][k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26470, _mut26471, _mut26472, _mut26473);
                    for (int j = k; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26478, _mut26479, _mut26480, _mut26481, _mut26482); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                        hI[j] -= AOR_multiply(alpha, hK[j], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26474, _mut26475, _mut26476, _mut26477);
                    }
                }
            }
            if (ROR_less(k, AOR_minus(m, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26488, _mut26489, _mut26490, _mut26491), "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26492, _mut26493, _mut26494, _mut26495, _mut26496)) {
                // zero-out a column
                final double[] hKp1 = householderVectors[AOR_plus(k, 1, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26497, _mut26498, _mut26499, _mut26500)];
                xNormSqr = 0;
                for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26505, _mut26506, _mut26507, _mut26508, _mut26509); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                    final double c = householderVectors[i][k];
                    xNormSqr += AOR_multiply(c, c, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26501, _mut26502, _mut26503, _mut26504);
                }
                final double b = (ROR_greater(hKp1[k], 0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26510, _mut26511, _mut26512, _mut26513, _mut26514)) ? -FastMath.sqrt(xNormSqr) : FastMath.sqrt(xNormSqr);
                secondary[k] = b;
                if (ROR_not_equals(b, 0.0, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26515, _mut26516, _mut26517, _mut26518, _mut26519)) {
                    hKp1[k] -= b;
                    for (int j = k + 1; ROR_less(j, n, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26542, _mut26543, _mut26544, _mut26545, _mut26546); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                        double beta = 0;
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26524, _mut26525, _mut26526, _mut26527, _mut26528); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                            final double[] hI = householderVectors[i];
                            beta -= AOR_multiply(hI[j], hI[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26520, _mut26521, _mut26522, _mut26523);
                        }
                        beta /= AOR_multiply(b, hKp1[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26529, _mut26530, _mut26531, _mut26532);
                        for (int i = k + 1; ROR_less(i, m, "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26537, _mut26538, _mut26539, _mut26540, _mut26541); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320");
                            final double[] hI = householderVectors[i];
                            hI[j] -= AOR_multiply(beta, hI[k], "org.apache.commons.math3.linear.BiDiagonalTransformer.transformToLowerBiDiagonal_320", _mut26533, _mut26534, _mut26535, _mut26536);
                        }
                    }
                }
            }
        }
    }
}
