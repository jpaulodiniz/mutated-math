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

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Class transforming a general real matrix to Schur form.
 * <p>A m &times; m matrix A can be written as the product of three matrices: A = P
 * &times; T &times; P<sup>T</sup> with P an orthogonal matrix and T an quasi-triangular
 * matrix. Both P and T are m &times; m matrices.</p>
 * <p>Transformation to Schur form is often not a goal by itself, but it is an
 * intermediate step in more general decomposition algorithms like
 * {@link EigenDecomposition eigen decomposition}. This class is therefore
 * intended for internal use by the library and is not public. As a consequence
 * of this explicitly limited scope, many methods directly returns references to
 * internal arrays, not copies.</p>
 * <p>This class is based on the method hqr2 in class EigenvalueDecomposition
 * from the <a href="http://math.nist.gov/javanumerics/jama/">JAMA</a> library.</p>
 *
 * @see <a href="http://mathworld.wolfram.com/SchurDecomposition.html">Schur Decomposition - MathWorld</a>
 * @see <a href="http://en.wikipedia.org/wiki/Schur_decomposition">Schur Decomposition - Wikipedia</a>
 * @see <a href="http://en.wikipedia.org/wiki/Householder_transformation">Householder Transformations</a>
 * @since 3.1
 */
class SchurTransformer {

    @Conditional
    public static boolean _mut35864 = false, _mut35865 = false, _mut35866 = false, _mut35867 = false, _mut35868 = false, _mut35869 = false, _mut35870 = false, _mut35871 = false, _mut35872 = false, _mut35873 = false, _mut35874 = false, _mut35875 = false, _mut35876 = false, _mut35877 = false, _mut35878 = false, _mut35879 = false, _mut35880 = false, _mut35881 = false, _mut35882 = false, _mut35883 = false, _mut35884 = false, _mut35885 = false, _mut35886 = false, _mut35887 = false, _mut35888 = false, _mut35889 = false, _mut35890 = false, _mut35891 = false, _mut35892 = false, _mut35893 = false, _mut35894 = false, _mut35895 = false, _mut35896 = false, _mut35897 = false, _mut35898 = false, _mut35899 = false, _mut35900 = false, _mut35901 = false, _mut35902 = false, _mut35903 = false, _mut35904 = false, _mut35905 = false, _mut35906 = false, _mut35907 = false, _mut35908 = false, _mut35909 = false, _mut35910 = false, _mut35911 = false, _mut35912 = false, _mut35913 = false, _mut35914 = false, _mut35915 = false, _mut35916 = false, _mut35917 = false, _mut35918 = false, _mut35919 = false, _mut35920 = false, _mut35921 = false, _mut35922 = false, _mut35923 = false, _mut35924 = false, _mut35925 = false, _mut35926 = false, _mut35927 = false, _mut35928 = false, _mut35929 = false, _mut35930 = false, _mut35931 = false, _mut35932 = false, _mut35933 = false, _mut35934 = false, _mut35935 = false, _mut35936 = false, _mut35937 = false, _mut35938 = false, _mut35939 = false, _mut35940 = false, _mut35941 = false, _mut35942 = false, _mut35943 = false, _mut35944 = false, _mut35945 = false, _mut35946 = false, _mut35947 = false, _mut35948 = false, _mut35949 = false, _mut35950 = false, _mut35951 = false, _mut35952 = false, _mut35953 = false, _mut35954 = false, _mut35955 = false, _mut35956 = false, _mut35957 = false, _mut35958 = false, _mut35959 = false, _mut35960 = false, _mut35961 = false, _mut35962 = false, _mut35963 = false, _mut35964 = false, _mut35965 = false, _mut35966 = false, _mut35967 = false, _mut35968 = false, _mut35969 = false, _mut35970 = false, _mut35971 = false, _mut35972 = false, _mut35973 = false, _mut35974 = false, _mut35975 = false, _mut35976 = false, _mut35977 = false, _mut35978 = false, _mut35979 = false, _mut35980 = false, _mut35981 = false, _mut35982 = false, _mut35983 = false, _mut35984 = false, _mut35985 = false, _mut35986 = false, _mut35987 = false, _mut35988 = false, _mut35989 = false, _mut35990 = false, _mut35991 = false, _mut35992 = false, _mut35993 = false, _mut35994 = false, _mut35995 = false, _mut35996 = false, _mut35997 = false, _mut35998 = false, _mut35999 = false, _mut36000 = false, _mut36001 = false, _mut36002 = false, _mut36003 = false, _mut36004 = false, _mut36005 = false, _mut36006 = false, _mut36007 = false, _mut36008 = false, _mut36009 = false, _mut36010 = false, _mut36011 = false, _mut36012 = false, _mut36013 = false, _mut36014 = false, _mut36015 = false, _mut36016 = false, _mut36017 = false, _mut36018 = false, _mut36019 = false, _mut36020 = false, _mut36021 = false, _mut36022 = false, _mut36023 = false, _mut36024 = false, _mut36025 = false, _mut36026 = false, _mut36027 = false, _mut36028 = false, _mut36029 = false, _mut36030 = false, _mut36031 = false, _mut36032 = false, _mut36033 = false, _mut36034 = false, _mut36035 = false, _mut36036 = false, _mut36037 = false, _mut36038 = false, _mut36039 = false, _mut36040 = false, _mut36041 = false, _mut36042 = false, _mut36043 = false, _mut36044 = false, _mut36045 = false, _mut36046 = false, _mut36047 = false, _mut36048 = false, _mut36049 = false, _mut36050 = false, _mut36051 = false, _mut36052 = false, _mut36053 = false, _mut36054 = false, _mut36055 = false, _mut36056 = false, _mut36057 = false, _mut36058 = false, _mut36059 = false, _mut36060 = false, _mut36061 = false, _mut36062 = false, _mut36063 = false, _mut36064 = false, _mut36065 = false, _mut36066 = false, _mut36067 = false, _mut36068 = false, _mut36069 = false, _mut36070 = false, _mut36071 = false, _mut36072 = false, _mut36073 = false, _mut36074 = false, _mut36075 = false, _mut36076 = false, _mut36077 = false, _mut36078 = false, _mut36079 = false, _mut36080 = false, _mut36081 = false, _mut36082 = false, _mut36083 = false, _mut36084 = false, _mut36085 = false, _mut36086 = false, _mut36087 = false, _mut36088 = false, _mut36089 = false, _mut36090 = false, _mut36091 = false, _mut36092 = false, _mut36093 = false, _mut36094 = false, _mut36095 = false, _mut36096 = false, _mut36097 = false, _mut36098 = false, _mut36099 = false, _mut36100 = false, _mut36101 = false, _mut36102 = false, _mut36103 = false, _mut36104 = false, _mut36105 = false, _mut36106 = false, _mut36107 = false, _mut36108 = false, _mut36109 = false, _mut36110 = false, _mut36111 = false, _mut36112 = false, _mut36113 = false, _mut36114 = false, _mut36115 = false, _mut36116 = false, _mut36117 = false, _mut36118 = false, _mut36119 = false, _mut36120 = false, _mut36121 = false, _mut36122 = false, _mut36123 = false, _mut36124 = false, _mut36125 = false, _mut36126 = false, _mut36127 = false, _mut36128 = false, _mut36129 = false, _mut36130 = false, _mut36131 = false, _mut36132 = false, _mut36133 = false, _mut36134 = false, _mut36135 = false, _mut36136 = false, _mut36137 = false, _mut36138 = false, _mut36139 = false, _mut36140 = false, _mut36141 = false, _mut36142 = false, _mut36143 = false, _mut36144 = false, _mut36145 = false, _mut36146 = false, _mut36147 = false, _mut36148 = false, _mut36149 = false, _mut36150 = false, _mut36151 = false, _mut36152 = false, _mut36153 = false, _mut36154 = false, _mut36155 = false, _mut36156 = false, _mut36157 = false, _mut36158 = false, _mut36159 = false, _mut36160 = false, _mut36161 = false, _mut36162 = false, _mut36163 = false, _mut36164 = false, _mut36165 = false, _mut36166 = false, _mut36167 = false, _mut36168 = false, _mut36169 = false, _mut36170 = false, _mut36171 = false, _mut36172 = false, _mut36173 = false, _mut36174 = false, _mut36175 = false, _mut36176 = false, _mut36177 = false, _mut36178 = false, _mut36179 = false, _mut36180 = false, _mut36181 = false, _mut36182 = false, _mut36183 = false, _mut36184 = false, _mut36185 = false, _mut36186 = false, _mut36187 = false, _mut36188 = false, _mut36189 = false, _mut36190 = false, _mut36191 = false, _mut36192 = false, _mut36193 = false, _mut36194 = false, _mut36195 = false, _mut36196 = false, _mut36197 = false, _mut36198 = false, _mut36199 = false, _mut36200 = false, _mut36201 = false, _mut36202 = false, _mut36203 = false, _mut36204 = false, _mut36205 = false, _mut36206 = false, _mut36207 = false, _mut36208 = false, _mut36209 = false, _mut36210 = false, _mut36211 = false, _mut36212 = false, _mut36213 = false, _mut36214 = false, _mut36215 = false, _mut36216 = false, _mut36217 = false, _mut36218 = false, _mut36219 = false, _mut36220 = false, _mut36221 = false, _mut36222 = false, _mut36223 = false, _mut36224 = false, _mut36225 = false, _mut36226 = false, _mut36227 = false, _mut36228 = false, _mut36229 = false, _mut36230 = false, _mut36231 = false, _mut36232 = false, _mut36233 = false, _mut36234 = false, _mut36235 = false, _mut36236 = false, _mut36237 = false, _mut36238 = false, _mut36239 = false, _mut36240 = false, _mut36241 = false, _mut36242 = false, _mut36243 = false, _mut36244 = false, _mut36245 = false, _mut36246 = false, _mut36247 = false, _mut36248 = false, _mut36249 = false, _mut36250 = false, _mut36251 = false, _mut36252 = false, _mut36253 = false, _mut36254 = false, _mut36255 = false, _mut36256 = false, _mut36257 = false, _mut36258 = false, _mut36259 = false, _mut36260 = false, _mut36261 = false, _mut36262 = false, _mut36263 = false, _mut36264 = false, _mut36265 = false, _mut36266 = false, _mut36267 = false, _mut36268 = false, _mut36269 = false, _mut36270 = false, _mut36271 = false, _mut36272 = false, _mut36273 = false, _mut36274 = false, _mut36275 = false, _mut36276 = false, _mut36277 = false, _mut36278 = false, _mut36279 = false, _mut36280 = false, _mut36281 = false, _mut36282 = false, _mut36283 = false, _mut36284 = false, _mut36285 = false, _mut36286 = false, _mut36287 = false, _mut36288 = false, _mut36289 = false, _mut36290 = false, _mut36291 = false, _mut36292 = false, _mut36293 = false, _mut36294 = false, _mut36295 = false, _mut36296 = false, _mut36297 = false, _mut36298 = false, _mut36299 = false, _mut36300 = false, _mut36301 = false, _mut36302 = false, _mut36303 = false, _mut36304 = false, _mut36305 = false, _mut36306 = false, _mut36307 = false, _mut36308 = false, _mut36309 = false, _mut36310 = false, _mut36311 = false, _mut36312 = false, _mut36313 = false, _mut36314 = false, _mut36315 = false, _mut36316 = false, _mut36317 = false, _mut36318 = false, _mut36319 = false, _mut36320 = false, _mut36321 = false, _mut36322 = false, _mut36323 = false, _mut36324 = false, _mut36325 = false, _mut36326 = false, _mut36327 = false, _mut36328 = false, _mut36329 = false, _mut36330 = false, _mut36331 = false, _mut36332 = false, _mut36333 = false, _mut36334 = false, _mut36335 = false, _mut36336 = false, _mut36337 = false, _mut36338 = false, _mut36339 = false, _mut36340 = false, _mut36341 = false, _mut36342 = false, _mut36343 = false, _mut36344 = false, _mut36345 = false, _mut36346 = false, _mut36347 = false, _mut36348 = false, _mut36349 = false, _mut36350 = false, _mut36351 = false, _mut36352 = false, _mut36353 = false, _mut36354 = false, _mut36355 = false, _mut36356 = false, _mut36357 = false, _mut36358 = false, _mut36359 = false, _mut36360 = false, _mut36361 = false, _mut36362 = false, _mut36363 = false, _mut36364 = false, _mut36365 = false, _mut36366 = false, _mut36367 = false, _mut36368 = false, _mut36369 = false, _mut36370 = false, _mut36371 = false, _mut36372 = false, _mut36373 = false, _mut36374 = false, _mut36375 = false, _mut36376 = false, _mut36377 = false, _mut36378 = false, _mut36379 = false, _mut36380 = false, _mut36381 = false, _mut36382 = false, _mut36383 = false, _mut36384 = false, _mut36385 = false, _mut36386 = false, _mut36387 = false, _mut36388 = false, _mut36389 = false, _mut36390 = false, _mut36391 = false, _mut36392 = false, _mut36393 = false, _mut36394 = false, _mut36395 = false, _mut36396 = false, _mut36397 = false, _mut36398 = false, _mut36399 = false, _mut36400 = false, _mut36401 = false, _mut36402 = false, _mut36403 = false, _mut36404 = false, _mut36405 = false, _mut36406 = false, _mut36407 = false, _mut36408 = false, _mut36409 = false, _mut36410 = false, _mut36411 = false, _mut36412 = false, _mut36413 = false, _mut36414 = false, _mut36415 = false, _mut36416 = false, _mut36417 = false, _mut36418 = false, _mut36419 = false, _mut36420 = false, _mut36421 = false, _mut36422 = false, _mut36423 = false, _mut36424 = false, _mut36425 = false, _mut36426 = false, _mut36427 = false, _mut36428 = false, _mut36429 = false, _mut36430 = false, _mut36431 = false, _mut36432 = false, _mut36433 = false, _mut36434 = false, _mut36435 = false, _mut36436 = false, _mut36437 = false, _mut36438 = false, _mut36439 = false, _mut36440 = false, _mut36441 = false, _mut36442 = false, _mut36443 = false, _mut36444 = false, _mut36445 = false, _mut36446 = false, _mut36447 = false, _mut36448 = false, _mut36449 = false, _mut36450 = false, _mut36451 = false, _mut36452 = false, _mut36453 = false, _mut36454 = false, _mut36455 = false, _mut36456 = false, _mut36457 = false, _mut36458 = false, _mut36459 = false, _mut36460 = false, _mut36461 = false, _mut36462 = false, _mut36463 = false, _mut36464 = false, _mut36465 = false, _mut36466 = false, _mut36467 = false, _mut36468 = false, _mut36469 = false, _mut36470 = false, _mut36471 = false, _mut36472 = false, _mut36473 = false, _mut36474 = false, _mut36475 = false, _mut36476 = false, _mut36477 = false, _mut36478 = false, _mut36479 = false, _mut36480 = false, _mut36481 = false, _mut36482 = false, _mut36483 = false, _mut36484 = false, _mut36485 = false, _mut36486 = false, _mut36487 = false, _mut36488 = false, _mut36489 = false, _mut36490 = false, _mut36491 = false, _mut36492 = false, _mut36493 = false, _mut36494 = false, _mut36495 = false, _mut36496 = false, _mut36497 = false, _mut36498 = false, _mut36499 = false, _mut36500 = false, _mut36501 = false, _mut36502 = false, _mut36503 = false, _mut36504 = false, _mut36505 = false, _mut36506 = false, _mut36507 = false, _mut36508 = false, _mut36509 = false, _mut36510 = false, _mut36511 = false, _mut36512 = false, _mut36513 = false, _mut36514 = false, _mut36515 = false, _mut36516 = false, _mut36517 = false, _mut36518 = false, _mut36519 = false, _mut36520 = false, _mut36521 = false, _mut36522 = false, _mut36523 = false, _mut36524 = false, _mut36525 = false, _mut36526 = false, _mut36527 = false, _mut36528 = false, _mut36529 = false, _mut36530 = false, _mut36531 = false, _mut36532 = false, _mut36533 = false, _mut36534 = false, _mut36535 = false, _mut36536 = false, _mut36537 = false, _mut36538 = false, _mut36539 = false, _mut36540 = false, _mut36541 = false, _mut36542 = false, _mut36543 = false, _mut36544 = false, _mut36545 = false, _mut36546 = false, _mut36547 = false, _mut36548 = false, _mut36549 = false, _mut36550 = false, _mut36551 = false, _mut36552 = false, _mut36553 = false, _mut36554 = false, _mut36555 = false, _mut36556 = false, _mut36557 = false, _mut36558 = false, _mut36559 = false, _mut36560 = false, _mut36561 = false, _mut36562 = false, _mut36563 = false, _mut36564 = false, _mut36565 = false, _mut36566 = false, _mut36567 = false, _mut36568 = false, _mut36569 = false, _mut36570 = false, _mut36571 = false, _mut36572 = false, _mut36573 = false, _mut36574 = false, _mut36575 = false, _mut36576 = false, _mut36577 = false, _mut36578 = false, _mut36579 = false, _mut36580 = false, _mut36581 = false, _mut36582 = false, _mut36583 = false, _mut36584 = false, _mut36585 = false, _mut36586 = false, _mut36587 = false, _mut36588 = false, _mut36589 = false, _mut36590 = false, _mut36591 = false, _mut36592 = false, _mut36593 = false, _mut36594 = false, _mut36595 = false, _mut36596 = false, _mut36597 = false, _mut36598 = false, _mut36599 = false, _mut36600 = false, _mut36601 = false, _mut36602 = false, _mut36603 = false, _mut36604 = false, _mut36605 = false, _mut36606 = false, _mut36607 = false, _mut36608 = false, _mut36609 = false, _mut36610 = false, _mut36611 = false, _mut36612 = false, _mut36613 = false, _mut36614 = false, _mut36615 = false, _mut36616 = false, _mut36617 = false, _mut36618 = false, _mut36619 = false, _mut36620 = false, _mut36621 = false, _mut36622 = false, _mut36623 = false, _mut36624 = false, _mut36625 = false, _mut36626 = false, _mut36627 = false, _mut36628 = false, _mut36629 = false, _mut36630 = false, _mut36631 = false, _mut36632 = false, _mut36633 = false, _mut36634 = false, _mut36635 = false, _mut36636 = false, _mut36637 = false, _mut36638 = false, _mut36639 = false, _mut36640 = false, _mut36641 = false, _mut36642 = false, _mut36643 = false, _mut36644 = false, _mut36645 = false, _mut36646 = false, _mut36647 = false, _mut36648 = false, _mut36649 = false, _mut36650 = false, _mut36651 = false, _mut36652 = false, _mut36653 = false, _mut36654 = false, _mut36655 = false, _mut36656 = false, _mut36657 = false, _mut36658 = false, _mut36659 = false, _mut36660 = false, _mut36661 = false, _mut36662 = false, _mut36663 = false, _mut36664 = false, _mut36665 = false, _mut36666 = false, _mut36667 = false, _mut36668 = false, _mut36669 = false, _mut36670 = false, _mut36671 = false;

    /**
     * Maximum allowed iterations for convergence of the transformation.
     */
    private static final int MAX_ITERATIONS = 100;

    /**
     * P matrix.
     */
    private final double[][] matrixP;

    /**
     * T matrix.
     */
    private final double[][] matrixT;

    /**
     * Cached value of P.
     */
    private RealMatrix cachedP;

    /**
     * Cached value of T.
     */
    private RealMatrix cachedT;

    /**
     * Cached value of PT.
     */
    private RealMatrix cachedPt;

    /**
     * Epsilon criteria taken from JAMA code (originally was 2^-52).
     */
    private final double epsilon = Precision.EPSILON;

    /**
     * Build the transformation to Schur form of a general real matrix.
     *
     * @param matrix matrix to transform
     * @throws NonSquareMatrixException if the matrix is not square
     */
    SchurTransformer(final RealMatrix matrix) {
        if (!matrix.isSquare()) {
            throw new NonSquareMatrixException(matrix.getRowDimension(), matrix.getColumnDimension());
        }
        HessenbergTransformer transformer = new HessenbergTransformer(matrix);
        matrixT = transformer.getH().getData();
        matrixP = transformer.getP().getData();
        cachedT = null;
        cachedP = null;
        cachedPt = null;
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
        if (cachedP == null) {
            cachedP = MatrixUtils.createRealMatrix(matrixP);
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
     * Returns the quasi-triangular Schur matrix T of the transform.
     *
     * @return the T matrix
     */
    public RealMatrix getT() {
        if (cachedT == null) {
            cachedT = MatrixUtils.createRealMatrix(matrixT);
        }
        // return the cached matrix
        return cachedT;
    }

    /**
     * Transform original matrix to Schur form.
     * @throws MaxCountExceededException if the transformation does not converge
     */
    private void transform() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.transform_131");
        final int n = matrixT.length;
        // compute matrix norm
        final double norm = getNorm();
        // shift information
        final ShiftInfo shift = new ShiftInfo();
        // Outer loop over eigenvalue index
        int iteration = 0;
        int iu = AOR_minus(n, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35864, _mut35865, _mut35866, _mut35867);
        while (ROR_greater_equals(iu, 0, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36092, _mut36093, _mut36094, _mut36095, _mut36096)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.transform_131");
            // Look for single small sub-diagonal element
            final int il = findSmallSubDiagonalElement(iu, norm);
            // Check for convergence
            if (ROR_equals(il, iu, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35868, _mut35869, _mut35870, _mut35871, _mut35872)) {
                // One root found
                matrixT[iu][iu] += shift.exShift;
                iu--;
                iteration = 0;
            } else if (ROR_equals(il, AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35873, _mut35874, _mut35875, _mut35876), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35877, _mut35878, _mut35879, _mut35880, _mut35881)) {
                // Two roots found
                double p = AOR_divide((AOR_minus(matrixT[AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35891, _mut35892, _mut35893, _mut35894)][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35887, _mut35888, _mut35889, _mut35890)], matrixT[iu][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35895, _mut35896, _mut35897, _mut35898)), 2.0, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35899, _mut35900, _mut35901, _mut35902);
                double q = AOR_plus(AOR_multiply(p, p, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35903, _mut35904, _mut35905, _mut35906), AOR_multiply(matrixT[iu][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35907, _mut35908, _mut35909, _mut35910)], matrixT[AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35911, _mut35912, _mut35913, _mut35914)][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35915, _mut35916, _mut35917, _mut35918), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35919, _mut35920, _mut35921, _mut35922);
                matrixT[iu][iu] += shift.exShift;
                matrixT[AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35927, _mut35928, _mut35929, _mut35930)][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35923, _mut35924, _mut35925, _mut35926)] += shift.exShift;
                if (ROR_greater_equals(q, 0, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35931, _mut35932, _mut35933, _mut35934, _mut35935)) {
                    double z = FastMath.sqrt(FastMath.abs(q));
                    if (ROR_greater_equals(p, 0, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35936, _mut35937, _mut35938, _mut35939, _mut35940)) {
                        z = AOR_plus(p, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35945, _mut35946, _mut35947, _mut35948);
                    } else {
                        z = AOR_minus(p, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35941, _mut35942, _mut35943, _mut35944);
                    }
                    final double x = matrixT[iu][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35949, _mut35950, _mut35951, _mut35952)];
                    final double s = AOR_plus(FastMath.abs(x), FastMath.abs(z), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35953, _mut35954, _mut35955, _mut35956);
                    p = AOR_divide(x, s, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35957, _mut35958, _mut35959, _mut35960);
                    q = AOR_divide(z, s, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35961, _mut35962, _mut35963, _mut35964);
                    final double r = FastMath.sqrt(AOR_plus(AOR_multiply(p, p, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35965, _mut35966, _mut35967, _mut35968), AOR_multiply(q, q, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35969, _mut35970, _mut35971, _mut35972), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35973, _mut35974, _mut35975, _mut35976));
                    p /= r;
                    q /= r;
                    // Row modification
                    for (int j = iu - 1; ROR_less(j, n, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36009, _mut36010, _mut36011, _mut36012, _mut36013); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.transform_131");
                        z = matrixT[AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35977, _mut35978, _mut35979, _mut35980)][j];
                        matrixT[AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35981, _mut35982, _mut35983, _mut35984)][j] = AOR_plus(AOR_multiply(q, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35985, _mut35986, _mut35987, _mut35988), AOR_multiply(p, matrixT[iu][j], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35989, _mut35990, _mut35991, _mut35992), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35993, _mut35994, _mut35995, _mut35996);
                        matrixT[iu][j] = AOR_minus(AOR_multiply(q, matrixT[iu][j], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35997, _mut35998, _mut35999, _mut36000), AOR_multiply(p, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36001, _mut36002, _mut36003, _mut36004), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36005, _mut36006, _mut36007, _mut36008);
                    }
                    // Column modification
                    for (int i = 0; ROR_less_equals(i, iu, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36046, _mut36047, _mut36048, _mut36049, _mut36050); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.transform_131");
                        z = matrixT[i][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36014, _mut36015, _mut36016, _mut36017)];
                        matrixT[i][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36018, _mut36019, _mut36020, _mut36021)] = AOR_plus(AOR_multiply(q, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36022, _mut36023, _mut36024, _mut36025), AOR_multiply(p, matrixT[i][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36026, _mut36027, _mut36028, _mut36029), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36030, _mut36031, _mut36032, _mut36033);
                        matrixT[i][iu] = AOR_minus(AOR_multiply(q, matrixT[i][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36034, _mut36035, _mut36036, _mut36037), AOR_multiply(p, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36038, _mut36039, _mut36040, _mut36041), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36042, _mut36043, _mut36044, _mut36045);
                    }
                    // Accumulate transformations
                    for (int i = 0; ROR_less_equals(i, AOR_minus(n, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36083, _mut36084, _mut36085, _mut36086), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36087, _mut36088, _mut36089, _mut36090, _mut36091); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.transform_131");
                        z = matrixP[i][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36051, _mut36052, _mut36053, _mut36054)];
                        matrixP[i][AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36055, _mut36056, _mut36057, _mut36058)] = AOR_plus(AOR_multiply(q, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36059, _mut36060, _mut36061, _mut36062), AOR_multiply(p, matrixP[i][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36063, _mut36064, _mut36065, _mut36066), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36067, _mut36068, _mut36069, _mut36070);
                        matrixP[i][iu] = AOR_minus(AOR_multiply(q, matrixP[i][iu], "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36071, _mut36072, _mut36073, _mut36074), AOR_multiply(p, z, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36075, _mut36076, _mut36077, _mut36078), "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut36079, _mut36080, _mut36081, _mut36082);
                    }
                }
                iu -= 2;
                iteration = 0;
            } else {
                // No convergence yet
                computeShift(il, iu, iteration, shift);
                // stop transformation after too many iterations
                if (ROR_greater(++iteration, MAX_ITERATIONS, "org.apache.commons.math3.linear.SchurTransformer.transform_131", _mut35882, _mut35883, _mut35884, _mut35885, _mut35886)) {
                    throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, MAX_ITERATIONS);
                }
                // the initial houseHolder vector for the QR step
                final double[] hVec = new double[3];
                final int im = initQRStep(il, iu, shift, hVec);
                performDoubleQRStep(il, im, iu, shift, hVec);
            }
        }
    }

    /**
     * Computes the L1 norm of the (quasi-)triangular matrix T.
     *
     * @return the L1 norm of matrix T
     */
    private double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.getNorm_223");
        double norm = 0.0;
        for (int i = 0; ROR_less(i, matrixT.length, "org.apache.commons.math3.linear.SchurTransformer.getNorm_223", _mut36102, _mut36103, _mut36104, _mut36105, _mut36106); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.getNorm_223");
            // as matrix T is (quasi-)triangular, also take the sub-diagonal element into account
            for (int j = FastMath.max(i - 1, 0); ROR_less(j, matrixT.length, "org.apache.commons.math3.linear.SchurTransformer.getNorm_223", _mut36097, _mut36098, _mut36099, _mut36100, _mut36101); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.getNorm_223");
                norm += FastMath.abs(matrixT[i][j]);
            }
        }
        return norm;
    }

    /**
     * Find the first small sub-diagonal element and returns its index.
     *
     * @param startIdx the starting index for the search
     * @param norm the L1 norm of the matrix
     * @return the index of the first small sub-diagonal element
     */
    private int findSmallSubDiagonalElement(final int startIdx, final double norm) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241");
        int l = startIdx;
        while (ROR_greater(l, 0, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36137, _mut36138, _mut36139, _mut36140, _mut36141)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241");
            double s = AOR_plus(FastMath.abs(matrixT[AOR_minus(l, 1, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36111, _mut36112, _mut36113, _mut36114)][AOR_minus(l, 1, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36107, _mut36108, _mut36109, _mut36110)]), FastMath.abs(matrixT[l][l]), "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36115, _mut36116, _mut36117, _mut36118);
            if (ROR_equals(s, 0.0, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36119, _mut36120, _mut36121, _mut36122, _mut36123)) {
                s = norm;
            }
            if (ROR_less(FastMath.abs(matrixT[l][AOR_minus(l, 1, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36124, _mut36125, _mut36126, _mut36127)]), AOR_multiply(epsilon, s, "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36128, _mut36129, _mut36130, _mut36131), "org.apache.commons.math3.linear.SchurTransformer.findSmallSubDiagonalElement_241", _mut36132, _mut36133, _mut36134, _mut36135, _mut36136)) {
                break;
            }
            l--;
        }
        return l;
    }

    /**
     * Compute the shift for the current iteration.
     *
     * @param l the index of the small sub-diagonal element
     * @param idx the current eigenvalue index
     * @param iteration the current iteration
     * @param shift holder for shift information
     */
    private void computeShift(final int l, final int idx, final int iteration, final ShiftInfo shift) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.computeShift_264");
        // Form shift
        shift.x = matrixT[idx][idx];
        shift.y = shift.w = 0.0;
        if (ROR_less(l, idx, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36142, _mut36143, _mut36144, _mut36145, _mut36146)) {
            shift.y = matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36151, _mut36152, _mut36153, _mut36154)][AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36147, _mut36148, _mut36149, _mut36150)];
            shift.w = AOR_multiply(matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36155, _mut36156, _mut36157, _mut36158)], matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36159, _mut36160, _mut36161, _mut36162)][idx], "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36163, _mut36164, _mut36165, _mut36166);
        }
        // Wilkinson's original ad hoc shift
        if (ROR_equals(iteration, 10, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36167, _mut36168, _mut36169, _mut36170, _mut36171)) {
            shift.exShift += shift.x;
            for (int i = 0; ROR_less_equals(i, idx, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36172, _mut36173, _mut36174, _mut36175, _mut36176); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.computeShift_264");
                matrixT[i][i] -= shift.x;
            }
            final double s = AOR_plus(FastMath.abs(matrixT[idx][AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36177, _mut36178, _mut36179, _mut36180)]), FastMath.abs(matrixT[AOR_minus(idx, 1, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36185, _mut36186, _mut36187, _mut36188)][AOR_minus(idx, 2, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36181, _mut36182, _mut36183, _mut36184)]), "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36189, _mut36190, _mut36191, _mut36192);
            shift.x = AOR_multiply(0.75, s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36193, _mut36194, _mut36195, _mut36196);
            shift.y = AOR_multiply(0.75, s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36197, _mut36198, _mut36199, _mut36200);
            shift.w = AOR_multiply(AOR_multiply(-0.4375, s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36201, _mut36202, _mut36203, _mut36204), s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36205, _mut36206, _mut36207, _mut36208);
        }
        // MATLAB's new ad hoc shift
        if (ROR_equals(iteration, 30, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36209, _mut36210, _mut36211, _mut36212, _mut36213)) {
            double s = AOR_divide((AOR_minus(shift.y, shift.x, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36214, _mut36215, _mut36216, _mut36217)), 2.0, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36218, _mut36219, _mut36220, _mut36221);
            s = AOR_plus(AOR_multiply(s, s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36222, _mut36223, _mut36224, _mut36225), shift.w, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36226, _mut36227, _mut36228, _mut36229);
            if (ROR_greater(s, 0.0, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36230, _mut36231, _mut36232, _mut36233, _mut36234)) {
                s = FastMath.sqrt(s);
                if (ROR_less(shift.y, shift.x, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36235, _mut36236, _mut36237, _mut36238, _mut36239)) {
                    s = -s;
                }
                s = AOR_minus(shift.x, AOR_divide(shift.w, (AOR_plus(AOR_divide((AOR_minus(shift.y, shift.x, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36240, _mut36241, _mut36242, _mut36243)), 2.0, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36244, _mut36245, _mut36246, _mut36247), s, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36248, _mut36249, _mut36250, _mut36251)), "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36252, _mut36253, _mut36254, _mut36255), "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36256, _mut36257, _mut36258, _mut36259);
                for (int i = 0; ROR_less_equals(i, idx, "org.apache.commons.math3.linear.SchurTransformer.computeShift_264", _mut36260, _mut36261, _mut36262, _mut36263, _mut36264); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.computeShift_264");
                    matrixT[i][i] -= s;
                }
                shift.exShift += s;
                shift.x = shift.y = shift.w = 0.964;
            }
        }
    }

    /**
     * Initialize the householder vectors for the QR step.
     *
     * @param il the index of the small sub-diagonal element
     * @param iu the current eigenvalue index
     * @param shift shift information holder
     * @param hVec the initial houseHolder vector
     * @return the start index for the QR step
     */
    private int initQRStep(int il, final int iu, final ShiftInfo shift, double[] hVec) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.initQRStep_313");
        // Look for two consecutive small sub-diagonal elements
        int im = AOR_minus(iu, 2, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36265, _mut36266, _mut36267, _mut36268);
        while (ROR_greater_equals(im, il, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36383, _mut36384, _mut36385, _mut36386, _mut36387)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.initQRStep_313");
            final double z = matrixT[im][im];
            final double r = AOR_minus(shift.x, z, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36269, _mut36270, _mut36271, _mut36272);
            double s = AOR_minus(shift.y, z, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36273, _mut36274, _mut36275, _mut36276);
            hVec[0] = AOR_plus(AOR_divide((AOR_minus(AOR_multiply(r, s, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36277, _mut36278, _mut36279, _mut36280), shift.w, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36281, _mut36282, _mut36283, _mut36284)), matrixT[AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36285, _mut36286, _mut36287, _mut36288)][im], "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36289, _mut36290, _mut36291, _mut36292), matrixT[im][AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36293, _mut36294, _mut36295, _mut36296)], "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36297, _mut36298, _mut36299, _mut36300);
            hVec[1] = AOR_minus(AOR_minus(AOR_minus(matrixT[AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36305, _mut36306, _mut36307, _mut36308)][AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36301, _mut36302, _mut36303, _mut36304)], z, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36309, _mut36310, _mut36311, _mut36312), r, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36313, _mut36314, _mut36315, _mut36316), s, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36317, _mut36318, _mut36319, _mut36320);
            hVec[2] = matrixT[AOR_plus(im, 2, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36325, _mut36326, _mut36327, _mut36328)][AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36321, _mut36322, _mut36323, _mut36324)];
            if (ROR_equals(im, il, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36329, _mut36330, _mut36331, _mut36332, _mut36333)) {
                break;
            }
            final double lhs = AOR_multiply(FastMath.abs(matrixT[im][AOR_minus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36334, _mut36335, _mut36336, _mut36337)]), (AOR_plus(FastMath.abs(hVec[1]), FastMath.abs(hVec[2]), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36338, _mut36339, _mut36340, _mut36341)), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36342, _mut36343, _mut36344, _mut36345);
            final double rhs = AOR_multiply(FastMath.abs(hVec[0]), (AOR_plus(AOR_plus(FastMath.abs(matrixT[AOR_minus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36350, _mut36351, _mut36352, _mut36353)][AOR_minus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36346, _mut36347, _mut36348, _mut36349)]), FastMath.abs(z), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36354, _mut36355, _mut36356, _mut36357), FastMath.abs(matrixT[AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36362, _mut36363, _mut36364, _mut36365)][AOR_plus(im, 1, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36358, _mut36359, _mut36360, _mut36361)]), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36366, _mut36367, _mut36368, _mut36369)), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36370, _mut36371, _mut36372, _mut36373);
            if (ROR_less(lhs, AOR_multiply(epsilon, rhs, "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36374, _mut36375, _mut36376, _mut36377), "org.apache.commons.math3.linear.SchurTransformer.initQRStep_313", _mut36378, _mut36379, _mut36380, _mut36381, _mut36382)) {
                break;
            }
            im--;
        }
        return im;
    }

    /**
     * Perform a double QR step involving rows l:idx and columns m:n
     *
     * @param il the index of the small sub-diagonal element
     * @param im the start index for the QR step
     * @param iu the current eigenvalue index
     * @param shift shift information holder
     * @param hVec the initial houseHolder vector
     */
    private void performDoubleQRStep(final int il, final int im, final int iu, final ShiftInfo shift, final double[] hVec) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
        final int n = matrixT.length;
        double p = hVec[0];
        double q = hVec[1];
        double r = hVec[2];
        for (int k = im; ROR_less_equals(k, AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36641, _mut36642, _mut36643, _mut36644), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36645, _mut36646, _mut36647, _mut36648, _mut36649); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
            boolean notlast = ROR_not_equals(k, (AOR_minus(iu, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36388, _mut36389, _mut36390, _mut36391)), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36392, _mut36393, _mut36394, _mut36395, _mut36396);
            if (ROR_not_equals(k, im, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36397, _mut36398, _mut36399, _mut36400, _mut36401)) {
                p = matrixT[k][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36402, _mut36403, _mut36404, _mut36405)];
                q = matrixT[AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36410, _mut36411, _mut36412, _mut36413)][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36406, _mut36407, _mut36408, _mut36409)];
                r = notlast ? matrixT[AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36418, _mut36419, _mut36420, _mut36421)][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36414, _mut36415, _mut36416, _mut36417)] : 0.0;
                shift.x = AOR_plus(AOR_plus(FastMath.abs(p), FastMath.abs(q), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36422, _mut36423, _mut36424, _mut36425), FastMath.abs(r), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36426, _mut36427, _mut36428, _mut36429);
                if (Precision.equals(shift.x, 0.0, epsilon)) {
                    continue;
                }
                p /= shift.x;
                q /= shift.x;
                r /= shift.x;
            }
            double s = FastMath.sqrt(AOR_plus(AOR_plus(AOR_multiply(p, p, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36430, _mut36431, _mut36432, _mut36433), AOR_multiply(q, q, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36434, _mut36435, _mut36436, _mut36437), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36438, _mut36439, _mut36440, _mut36441), AOR_multiply(r, r, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36442, _mut36443, _mut36444, _mut36445), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36446, _mut36447, _mut36448, _mut36449));
            if (ROR_less(p, 0.0, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36450, _mut36451, _mut36452, _mut36453, _mut36454)) {
                s = -s;
            }
            if (ROR_not_equals(s, 0.0, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36455, _mut36456, _mut36457, _mut36458, _mut36459)) {
                if (ROR_not_equals(k, im, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36460, _mut36461, _mut36462, _mut36463, _mut36464)) {
                    matrixT[k][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36478, _mut36479, _mut36480, _mut36481)] = AOR_multiply(-s, shift.x, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36482, _mut36483, _mut36484, _mut36485);
                } else if (ROR_not_equals(il, im, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36465, _mut36466, _mut36467, _mut36468, _mut36469)) {
                    matrixT[k][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36470, _mut36471, _mut36472, _mut36473)] = -matrixT[k][AOR_minus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36474, _mut36475, _mut36476, _mut36477)];
                }
                p += s;
                shift.x = AOR_divide(p, s, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36486, _mut36487, _mut36488, _mut36489);
                shift.y = AOR_divide(q, s, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36490, _mut36491, _mut36492, _mut36493);
                double z = AOR_divide(r, s, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36494, _mut36495, _mut36496, _mut36497);
                q /= p;
                r /= p;
                // Row modification
                for (int j = k; ROR_less(j, n, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36538, _mut36539, _mut36540, _mut36541, _mut36542); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
                    p = AOR_plus(matrixT[k][j], AOR_multiply(q, matrixT[AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36498, _mut36499, _mut36500, _mut36501)][j], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36502, _mut36503, _mut36504, _mut36505), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36506, _mut36507, _mut36508, _mut36509);
                    if (notlast) {
                        p += AOR_multiply(r, matrixT[AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36510, _mut36511, _mut36512, _mut36513)][j], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36514, _mut36515, _mut36516, _mut36517);
                        matrixT[AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36518, _mut36519, _mut36520, _mut36521)][j] -= AOR_multiply(p, z, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36522, _mut36523, _mut36524, _mut36525);
                    }
                    matrixT[k][j] -= AOR_multiply(p, shift.x, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36526, _mut36527, _mut36528, _mut36529);
                    matrixT[AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36530, _mut36531, _mut36532, _mut36533)][j] -= AOR_multiply(p, shift.y, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36534, _mut36535, _mut36536, _mut36537);
                }
                // Column modification
                for (int i = 0; ROR_less_equals(i, FastMath.min(iu, AOR_plus(k, 3, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36583, _mut36584, _mut36585, _mut36586)), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36587, _mut36588, _mut36589, _mut36590, _mut36591); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
                    p = AOR_plus(AOR_multiply(shift.x, matrixT[i][k], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36543, _mut36544, _mut36545, _mut36546), AOR_multiply(shift.y, matrixT[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36547, _mut36548, _mut36549, _mut36550)], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36551, _mut36552, _mut36553, _mut36554), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36555, _mut36556, _mut36557, _mut36558);
                    if (notlast) {
                        p += AOR_multiply(z, matrixT[i][AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36559, _mut36560, _mut36561, _mut36562)], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36563, _mut36564, _mut36565, _mut36566);
                        matrixT[i][AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36567, _mut36568, _mut36569, _mut36570)] -= AOR_multiply(p, r, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36571, _mut36572, _mut36573, _mut36574);
                    }
                    matrixT[i][k] -= p;
                    matrixT[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36575, _mut36576, _mut36577, _mut36578)] -= AOR_multiply(p, q, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36579, _mut36580, _mut36581, _mut36582);
                }
                // Accumulate transformations
                final int high = AOR_minus(matrixT.length, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36592, _mut36593, _mut36594, _mut36595);
                for (int i = 0; ROR_less_equals(i, high, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36636, _mut36637, _mut36638, _mut36639, _mut36640); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
                    p = AOR_plus(AOR_multiply(shift.x, matrixP[i][k], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36596, _mut36597, _mut36598, _mut36599), AOR_multiply(shift.y, matrixP[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36600, _mut36601, _mut36602, _mut36603)], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36604, _mut36605, _mut36606, _mut36607), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36608, _mut36609, _mut36610, _mut36611);
                    if (notlast) {
                        p += AOR_multiply(z, matrixP[i][AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36612, _mut36613, _mut36614, _mut36615)], "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36616, _mut36617, _mut36618, _mut36619);
                        matrixP[i][AOR_plus(k, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36620, _mut36621, _mut36622, _mut36623)] -= AOR_multiply(p, r, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36624, _mut36625, _mut36626, _mut36627);
                    }
                    matrixP[i][k] -= p;
                    matrixP[i][AOR_plus(k, 1, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36628, _mut36629, _mut36630, _mut36631)] -= AOR_multiply(p, q, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36632, _mut36633, _mut36634, _mut36635);
                }
            }
        }
        // clean up pollution due to round-off errors
        for (int i = im + 2; ROR_less_equals(i, iu, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36667, _mut36668, _mut36669, _mut36670, _mut36671); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351");
            matrixT[i][AOR_minus(i, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36650, _mut36651, _mut36652, _mut36653)] = 0.0;
            if (ROR_greater(i, AOR_plus(im, 2, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36654, _mut36655, _mut36656, _mut36657), "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36658, _mut36659, _mut36660, _mut36661, _mut36662)) {
                matrixT[i][AOR_minus(i, 3, "org.apache.commons.math3.linear.SchurTransformer.performDoubleQRStep_351", _mut36663, _mut36664, _mut36665, _mut36666)] = 0.0;
            }
        }
    }

    /**
     * Internal data structure holding the current shift information.
     * Contains variable names as present in the original JAMA code.
     */
    private static class ShiftInfo {

        /**
         * x shift info
         */
        double x;

        /**
         * y shift info
         */
        double y;

        /**
         * w shift info
         */
        double w;

        /**
         * Indicates an exceptional shift.
         */
        double exShift;
    }
}
