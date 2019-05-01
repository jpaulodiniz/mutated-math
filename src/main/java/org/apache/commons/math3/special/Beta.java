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
package org.apache.commons.math3.special;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.ContinuedFraction;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * This is a utility class that provides computation methods related to the
 * Beta family of functions.
 * </p>
 * <p>
 * Implementation of {@link #logBeta(double, double)} is based on the
 * algorithms described in
 * <ul>
 * <li><a href="http://dx.doi.org/10.1145/22721.23109">Didonato and Morris
 *     (1986)</a>, <em>Computation of the Incomplete Gamma Function Ratios
 *     and their Inverse</em>, TOMS 12(4), 377-393,</li>
 * <li><a href="http://dx.doi.org/10.1145/131766.131776">Didonato and Morris
 *     (1992)</a>, <em>Algorithm 708: Significant Digit Computation of the
 *     Incomplete Beta Function Ratios</em>, TOMS 18(3), 360-373,</li>
 * </ul>
 * and implemented in the
 * <a href="http://www.dtic.mil/docs/citations/ADA476840">NSWC Library of Mathematical Functions</a>,
 * available
 * <a href="http://www.ualberta.ca/CNS/RESEARCH/Software/NumericalNSWC/site.html">here</a>.
 * This library is "approved for public release", and the
 * <a href="http://www.dtic.mil/dtic/pdf/announcements/CopyrightGuidance.pdf">Copyright guidance</a>
 * indicates that unless otherwise stated in the code, all FORTRAN functions in
 * this library are license free. Since no such notice appears in the code these
 * functions can safely be ported to Commons-Math.
 * </p>
 */
public class Beta {

    @Conditional
    public static boolean _mut67869 = false, _mut67870 = false, _mut67871 = false, _mut67872 = false, _mut67873 = false, _mut67874 = false, _mut67875 = false, _mut67876 = false, _mut67877 = false, _mut67878 = false, _mut67879 = false, _mut67880 = false, _mut67881 = false, _mut67882 = false, _mut67883 = false, _mut67884 = false, _mut67885 = false, _mut67886 = false, _mut67887 = false, _mut67888 = false, _mut67889 = false, _mut67890 = false, _mut67891 = false, _mut67892 = false, _mut67893 = false, _mut67894 = false, _mut67895 = false, _mut67896 = false, _mut67897 = false, _mut67898 = false, _mut67899 = false, _mut67900 = false, _mut67901 = false, _mut67902 = false, _mut67903 = false, _mut67904 = false, _mut67905 = false, _mut67906 = false, _mut67907 = false, _mut67908 = false, _mut67909 = false, _mut67910 = false, _mut67911 = false, _mut67912 = false, _mut67913 = false, _mut67914 = false, _mut67915 = false, _mut67916 = false, _mut67917 = false, _mut67918 = false, _mut67919 = false, _mut67920 = false, _mut67921 = false, _mut67922 = false, _mut67923 = false, _mut67924 = false, _mut67925 = false, _mut67926 = false, _mut67927 = false, _mut67928 = false, _mut67929 = false, _mut67930 = false, _mut67931 = false, _mut67932 = false, _mut67933 = false, _mut67934 = false, _mut67935 = false, _mut67936 = false, _mut67937 = false, _mut67938 = false, _mut67939 = false, _mut67940 = false, _mut67941 = false, _mut67942 = false, _mut67943 = false, _mut67944 = false, _mut67945 = false, _mut67946 = false, _mut67947 = false, _mut67948 = false, _mut67949 = false, _mut67950 = false, _mut67951 = false, _mut67952 = false, _mut67953 = false, _mut67954 = false, _mut67955 = false, _mut67956 = false, _mut67957 = false, _mut67958 = false, _mut67959 = false, _mut67960 = false, _mut67961 = false, _mut67962 = false, _mut67963 = false, _mut67964 = false, _mut67965 = false, _mut67966 = false, _mut67967 = false, _mut67968 = false, _mut67969 = false, _mut67970 = false, _mut67971 = false, _mut67972 = false, _mut67973 = false, _mut67974 = false, _mut67975 = false, _mut67976 = false, _mut67977 = false, _mut67978 = false, _mut67979 = false, _mut67980 = false, _mut67981 = false, _mut67982 = false, _mut67983 = false, _mut67984 = false, _mut67985 = false, _mut67986 = false, _mut67987 = false, _mut67988 = false, _mut67989 = false, _mut67990 = false, _mut67991 = false, _mut67992 = false, _mut67993 = false, _mut67994 = false, _mut67995 = false, _mut67996 = false, _mut67997 = false, _mut67998 = false, _mut67999 = false, _mut68000 = false, _mut68001 = false, _mut68002 = false, _mut68003 = false, _mut68004 = false, _mut68005 = false, _mut68006 = false, _mut68007 = false, _mut68008 = false, _mut68009 = false, _mut68010 = false, _mut68011 = false, _mut68012 = false, _mut68013 = false, _mut68014 = false, _mut68015 = false, _mut68016 = false, _mut68017 = false, _mut68018 = false, _mut68019 = false, _mut68020 = false, _mut68021 = false, _mut68022 = false, _mut68023 = false, _mut68024 = false, _mut68025 = false, _mut68026 = false, _mut68027 = false, _mut68028 = false, _mut68029 = false, _mut68030 = false, _mut68031 = false, _mut68032 = false, _mut68033 = false, _mut68034 = false, _mut68035 = false, _mut68036 = false, _mut68037 = false, _mut68038 = false, _mut68039 = false, _mut68040 = false, _mut68041 = false, _mut68042 = false, _mut68043 = false, _mut68044 = false, _mut68045 = false, _mut68046 = false, _mut68047 = false, _mut68048 = false, _mut68049 = false, _mut68050 = false, _mut68051 = false, _mut68052 = false, _mut68053 = false, _mut68054 = false, _mut68055 = false, _mut68056 = false, _mut68057 = false, _mut68058 = false, _mut68059 = false, _mut68060 = false, _mut68061 = false, _mut68062 = false, _mut68063 = false, _mut68064 = false, _mut68065 = false, _mut68066 = false, _mut68067 = false, _mut68068 = false, _mut68069 = false, _mut68070 = false, _mut68071 = false, _mut68072 = false, _mut68073 = false, _mut68074 = false, _mut68075 = false, _mut68076 = false, _mut68077 = false, _mut68078 = false, _mut68079 = false, _mut68080 = false, _mut68081 = false, _mut68082 = false, _mut68083 = false, _mut68084 = false, _mut68085 = false, _mut68086 = false, _mut68087 = false, _mut68088 = false, _mut68089 = false, _mut68090 = false, _mut68091 = false, _mut68092 = false, _mut68093 = false, _mut68094 = false, _mut68095 = false, _mut68096 = false, _mut68097 = false, _mut68098 = false, _mut68099 = false, _mut68100 = false, _mut68101 = false, _mut68102 = false, _mut68103 = false, _mut68104 = false, _mut68105 = false, _mut68106 = false, _mut68107 = false, _mut68108 = false, _mut68109 = false, _mut68110 = false, _mut68111 = false, _mut68112 = false, _mut68113 = false, _mut68114 = false, _mut68115 = false, _mut68116 = false, _mut68117 = false, _mut68118 = false, _mut68119 = false, _mut68120 = false, _mut68121 = false, _mut68122 = false, _mut68123 = false, _mut68124 = false, _mut68125 = false, _mut68126 = false, _mut68127 = false, _mut68128 = false, _mut68129 = false, _mut68130 = false, _mut68131 = false, _mut68132 = false, _mut68133 = false, _mut68134 = false, _mut68135 = false, _mut68136 = false, _mut68137 = false, _mut68138 = false, _mut68139 = false, _mut68140 = false, _mut68141 = false, _mut68142 = false, _mut68143 = false, _mut68144 = false, _mut68145 = false, _mut68146 = false, _mut68147 = false, _mut68148 = false, _mut68149 = false, _mut68150 = false, _mut68151 = false, _mut68152 = false, _mut68153 = false, _mut68154 = false, _mut68155 = false, _mut68156 = false, _mut68157 = false, _mut68158 = false, _mut68159 = false, _mut68160 = false, _mut68161 = false, _mut68162 = false, _mut68163 = false, _mut68164 = false, _mut68165 = false, _mut68166 = false, _mut68167 = false, _mut68168 = false, _mut68169 = false, _mut68170 = false, _mut68171 = false, _mut68172 = false, _mut68173 = false, _mut68174 = false, _mut68175 = false, _mut68176 = false, _mut68177 = false, _mut68178 = false, _mut68179 = false, _mut68180 = false, _mut68181 = false, _mut68182 = false, _mut68183 = false, _mut68184 = false, _mut68185 = false, _mut68186 = false, _mut68187 = false, _mut68188 = false, _mut68189 = false, _mut68190 = false, _mut68191 = false, _mut68192 = false, _mut68193 = false, _mut68194 = false, _mut68195 = false, _mut68196 = false, _mut68197 = false, _mut68198 = false, _mut68199 = false, _mut68200 = false, _mut68201 = false, _mut68202 = false, _mut68203 = false, _mut68204 = false, _mut68205 = false, _mut68206 = false, _mut68207 = false, _mut68208 = false, _mut68209 = false, _mut68210 = false, _mut68211 = false, _mut68212 = false, _mut68213 = false, _mut68214 = false, _mut68215 = false, _mut68216 = false, _mut68217 = false, _mut68218 = false, _mut68219 = false, _mut68220 = false, _mut68221 = false, _mut68222 = false, _mut68223 = false, _mut68224 = false, _mut68225 = false, _mut68226 = false, _mut68227 = false, _mut68228 = false, _mut68229 = false, _mut68230 = false, _mut68231 = false, _mut68232 = false, _mut68233 = false, _mut68234 = false, _mut68235 = false, _mut68236 = false, _mut68237 = false, _mut68238 = false, _mut68239 = false, _mut68240 = false, _mut68241 = false, _mut68242 = false, _mut68243 = false, _mut68244 = false, _mut68245 = false, _mut68246 = false, _mut68247 = false, _mut68248 = false, _mut68249 = false, _mut68250 = false, _mut68251 = false, _mut68252 = false, _mut68253 = false, _mut68254 = false, _mut68255 = false, _mut68256 = false, _mut68257 = false, _mut68258 = false, _mut68259 = false, _mut68260 = false, _mut68261 = false, _mut68262 = false, _mut68263 = false, _mut68264 = false, _mut68265 = false, _mut68266 = false, _mut68267 = false, _mut68268 = false, _mut68269 = false, _mut68270 = false, _mut68271 = false, _mut68272 = false, _mut68273 = false, _mut68274 = false, _mut68275 = false, _mut68276 = false, _mut68277 = false, _mut68278 = false, _mut68279 = false, _mut68280 = false, _mut68281 = false, _mut68282 = false, _mut68283 = false, _mut68284 = false, _mut68285 = false, _mut68286 = false, _mut68287 = false, _mut68288 = false, _mut68289 = false, _mut68290 = false, _mut68291 = false, _mut68292 = false, _mut68293 = false, _mut68294 = false, _mut68295 = false, _mut68296 = false, _mut68297 = false, _mut68298 = false, _mut68299 = false, _mut68300 = false, _mut68301 = false, _mut68302 = false, _mut68303 = false, _mut68304 = false, _mut68305 = false, _mut68306 = false, _mut68307 = false, _mut68308 = false, _mut68309 = false, _mut68310 = false, _mut68311 = false, _mut68312 = false, _mut68313 = false, _mut68314 = false, _mut68315 = false, _mut68316 = false, _mut68317 = false, _mut68318 = false, _mut68319 = false, _mut68320 = false, _mut68321 = false, _mut68322 = false, _mut68323 = false, _mut68324 = false, _mut68325 = false, _mut68326 = false, _mut68327 = false, _mut68328 = false, _mut68329 = false, _mut68330 = false, _mut68331 = false, _mut68332 = false, _mut68333 = false, _mut68334 = false, _mut68335 = false, _mut68336 = false, _mut68337 = false, _mut68338 = false, _mut68339 = false, _mut68340 = false, _mut68341 = false, _mut68342 = false, _mut68343 = false, _mut68344 = false, _mut68345 = false, _mut68346 = false, _mut68347 = false, _mut68348 = false, _mut68349 = false, _mut68350 = false, _mut68351 = false, _mut68352 = false, _mut68353 = false, _mut68354 = false, _mut68355 = false, _mut68356 = false, _mut68357 = false, _mut68358 = false, _mut68359 = false, _mut68360 = false, _mut68361 = false, _mut68362 = false, _mut68363 = false, _mut68364 = false, _mut68365 = false, _mut68366 = false, _mut68367 = false, _mut68368 = false, _mut68369 = false, _mut68370 = false, _mut68371 = false, _mut68372 = false, _mut68373 = false, _mut68374 = false, _mut68375 = false, _mut68376 = false, _mut68377 = false, _mut68378 = false, _mut68379 = false, _mut68380 = false, _mut68381 = false, _mut68382 = false, _mut68383 = false, _mut68384 = false, _mut68385 = false, _mut68386 = false, _mut68387 = false, _mut68388 = false, _mut68389 = false, _mut68390 = false, _mut68391 = false, _mut68392 = false, _mut68393 = false, _mut68394 = false, _mut68395 = false, _mut68396 = false, _mut68397 = false, _mut68398 = false, _mut68399 = false, _mut68400 = false, _mut68401 = false, _mut68402 = false, _mut68403 = false, _mut68404 = false, _mut68405 = false, _mut68406 = false, _mut68407 = false, _mut68408 = false, _mut68409 = false, _mut68410 = false, _mut68411 = false, _mut68412 = false, _mut68413 = false, _mut68414 = false, _mut68415 = false, _mut68416 = false, _mut68417 = false, _mut68418 = false, _mut68419 = false, _mut68420 = false, _mut68421 = false, _mut68422 = false, _mut68423 = false, _mut68424 = false, _mut68425 = false, _mut68426 = false, _mut68427 = false, _mut68428 = false, _mut68429 = false, _mut68430 = false, _mut68431 = false, _mut68432 = false, _mut68433 = false, _mut68434 = false, _mut68435 = false, _mut68436 = false, _mut68437 = false, _mut68438 = false, _mut68439 = false, _mut68440 = false, _mut68441 = false, _mut68442 = false, _mut68443 = false, _mut68444 = false, _mut68445 = false, _mut68446 = false, _mut68447 = false, _mut68448 = false, _mut68449 = false, _mut68450 = false, _mut68451 = false, _mut68452 = false, _mut68453 = false, _mut68454 = false, _mut68455 = false, _mut68456 = false, _mut68457 = false, _mut68458 = false, _mut68459 = false, _mut68460 = false, _mut68461 = false, _mut68462 = false, _mut68463 = false, _mut68464 = false, _mut68465 = false, _mut68466 = false, _mut68467 = false, _mut68468 = false, _mut68469 = false, _mut68470 = false, _mut68471 = false, _mut68472 = false, _mut68473 = false, _mut68474 = false, _mut68475 = false, _mut68476 = false, _mut68477 = false, _mut68478 = false, _mut68479 = false, _mut68480 = false, _mut68481 = false, _mut68482 = false, _mut68483 = false, _mut68484 = false, _mut68485 = false, _mut68486 = false, _mut68487 = false, _mut68488 = false, _mut68489 = false, _mut68490 = false, _mut68491 = false, _mut68492 = false, _mut68493 = false, _mut68494 = false, _mut68495 = false, _mut68496 = false, _mut68497 = false, _mut68498 = false, _mut68499 = false, _mut68500 = false, _mut68501 = false, _mut68502 = false, _mut68503 = false, _mut68504 = false, _mut68505 = false, _mut68506 = false, _mut68507 = false, _mut68508 = false, _mut68509 = false, _mut68510 = false, _mut68511 = false, _mut68512 = false, _mut68513 = false, _mut68514 = false, _mut68515 = false, _mut68516 = false, _mut68517 = false, _mut68518 = false, _mut68519 = false, _mut68520 = false, _mut68521 = false, _mut68522 = false, _mut68523 = false, _mut68524 = false, _mut68525 = false, _mut68526 = false, _mut68527 = false, _mut68528 = false, _mut68529 = false, _mut68530 = false, _mut68531 = false, _mut68532 = false, _mut68533 = false, _mut68534 = false, _mut68535 = false, _mut68536 = false, _mut68537 = false, _mut68538 = false, _mut68539 = false, _mut68540 = false, _mut68541 = false, _mut68542 = false, _mut68543 = false, _mut68544 = false, _mut68545 = false, _mut68546 = false, _mut68547 = false, _mut68548 = false, _mut68549 = false, _mut68550 = false, _mut68551 = false, _mut68552 = false, _mut68553 = false, _mut68554 = false, _mut68555 = false, _mut68556 = false, _mut68557 = false, _mut68558 = false, _mut68559 = false, _mut68560 = false, _mut68561 = false, _mut68562 = false, _mut68563 = false, _mut68564 = false, _mut68565 = false, _mut68566 = false, _mut68567 = false, _mut68568 = false, _mut68569 = false, _mut68570 = false, _mut68571 = false, _mut68572 = false, _mut68573 = false, _mut68574 = false, _mut68575 = false, _mut68576 = false, _mut68577 = false, _mut68578 = false, _mut68579 = false, _mut68580 = false, _mut68581 = false, _mut68582 = false, _mut68583 = false, _mut68584 = false, _mut68585 = false, _mut68586 = false, _mut68587 = false, _mut68588 = false, _mut68589 = false, _mut68590 = false, _mut68591 = false, _mut68592 = false, _mut68593 = false, _mut68594 = false, _mut68595 = false, _mut68596 = false, _mut68597 = false, _mut68598 = false, _mut68599 = false, _mut68600 = false, _mut68601 = false, _mut68602 = false, _mut68603 = false, _mut68604 = false, _mut68605 = false, _mut68606 = false, _mut68607 = false, _mut68608 = false, _mut68609 = false, _mut68610 = false, _mut68611 = false, _mut68612 = false, _mut68613 = false, _mut68614 = false, _mut68615 = false, _mut68616 = false, _mut68617 = false, _mut68618 = false, _mut68619 = false, _mut68620 = false, _mut68621 = false, _mut68622 = false, _mut68623 = false, _mut68624 = false, _mut68625 = false, _mut68626 = false, _mut68627 = false, _mut68628 = false, _mut68629 = false, _mut68630 = false, _mut68631 = false, _mut68632 = false, _mut68633 = false, _mut68634 = false, _mut68635 = false, _mut68636 = false, _mut68637 = false;

    /**
     * Maximum allowed numerical error.
     */
    private static final double DEFAULT_EPSILON = 1E-14;

    /**
     * The constant value of ½log 2π.
     */
    private static final double HALF_LOG_TWO_PI = .9189385332046727;

    /**
     * <p>
     * The coefficients of the series expansion of the Δ function. This function
     * is defined as follows
     * </p>
     * <center>Δ(x) = log Γ(x) - (x - 0.5) log a + a - 0.5 log 2π,</center>
     * <p>
     * see equation (23) in Didonato and Morris (1992). The series expansion,
     * which applies for x ≥ 10, reads
     * </p>
     * <pre>
     *                 14
     *                ====
     *             1  \                2 n
     *     Δ(x) = ---  >    d  (10 / x)
     *             x  /      n
     *                ====
     *                n = 0
     * <pre>
     */
    private static final double[] DELTA = { .833333333333333333333333333333E-01, -.277777777777777777777777752282E-04, .793650793650793650791732130419E-07, -.595238095238095232389839236182E-09, .841750841750832853294451671990E-11, -.191752691751854612334149171243E-12, .641025640510325475730918472625E-14, -.295506514125338232839867823991E-15, .179643716359402238723287696452E-16, -.139228964661627791231203060395E-17, .133802855014020915603275339093E-18, -.154246009867966094273710216533E-19, .197701992980957427278370133333E-20, -.234065664793997056856992426667E-21, .171348014966398575409015466667E-22 };

    /**
     * Default constructor.  Prohibit instantiation.
     */
    private Beta() {
    }

    /**
     * Returns the
     * <a href="http://mathworld.wolfram.com/RegularizedBetaFunction.html">
     * regularized beta function</a> I(x, a, b).
     *
     * @param x Value.
     * @param a Parameter {@code a}.
     * @param b Parameter {@code b}.
     * @return the regularized beta function I(x, a, b).
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     */
    public static double regularizedBeta(double x, double a, double b) {
        return regularizedBeta(x, a, b, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    /**
     * Returns the
     * <a href="http://mathworld.wolfram.com/RegularizedBetaFunction.html">
     * regularized beta function</a> I(x, a, b).
     *
     * @param x Value.
     * @param a Parameter {@code a}.
     * @param b Parameter {@code b}.
     * @param epsilon When the absolute value of the nth item in the
     * series is less than epsilon the approximation ceases to calculate
     * further elements in the series.
     * @return the regularized beta function I(x, a, b)
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     */
    public static double regularizedBeta(double x, double a, double b, double epsilon) {
        return regularizedBeta(x, a, b, epsilon, Integer.MAX_VALUE);
    }

    /**
     * Returns the regularized beta function I(x, a, b).
     *
     * @param x the value.
     * @param a Parameter {@code a}.
     * @param b Parameter {@code b}.
     * @param maxIterations Maximum number of "iterations" to complete.
     * @return the regularized beta function I(x, a, b)
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     */
    public static double regularizedBeta(double x, double a, double b, int maxIterations) {
        return regularizedBeta(x, a, b, DEFAULT_EPSILON, maxIterations);
    }

    /**
     * Returns the regularized beta function I(x, a, b).
     *
     * The implementation of this method is based on:
     * <ul>
     * <li>
     * <a href="http://mathworld.wolfram.com/RegularizedBetaFunction.html">
     * Regularized Beta Function</a>.</li>
     * <li>
     * <a href="http://functions.wolfram.com/06.21.10.0001.01">
     * Regularized Beta Function</a>.</li>
     * </ul>
     *
     * @param x the value.
     * @param a Parameter {@code a}.
     * @param b Parameter {@code b}.
     * @param epsilon When the absolute value of the nth item in the
     * series is less than epsilon the approximation ceases to calculate
     * further elements in the series.
     * @param maxIterations Maximum number of "iterations" to complete.
     * @return the regularized beta function I(x, a, b)
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     */
    public static double regularizedBeta(double x, final double a, final double b, double epsilon, int maxIterations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.getA_218");
        double ret;
        if ((_mut67894 ? ((_mut67888 ? ((_mut67882 ? ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) && ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881)) : ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) || ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881))) && ROR_less_equals(a, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67883, _mut67884, _mut67885, _mut67886, _mut67887)) : ((_mut67882 ? ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) && ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881)) : ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) || ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881))) || ROR_less_equals(a, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67883, _mut67884, _mut67885, _mut67886, _mut67887))) && ROR_less_equals(b, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67889, _mut67890, _mut67891, _mut67892, _mut67893)) : ((_mut67888 ? ((_mut67882 ? ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) && ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881)) : ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) || ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881))) && ROR_less_equals(a, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67883, _mut67884, _mut67885, _mut67886, _mut67887)) : ((_mut67882 ? ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) && ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881)) : ((_mut67876 ? ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) && ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875)) : ((_mut67870 ? ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) && Double.isNaN(b)) : ((_mut67869 ? (Double.isNaN(x) && Double.isNaN(a)) : (Double.isNaN(x) || Double.isNaN(a))) || Double.isNaN(b))) || ROR_less(x, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67871, _mut67872, _mut67873, _mut67874, _mut67875))) || ROR_greater(x, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67877, _mut67878, _mut67879, _mut67880, _mut67881))) || ROR_less_equals(a, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67883, _mut67884, _mut67885, _mut67886, _mut67887))) || ROR_less_equals(b, 0, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67889, _mut67890, _mut67891, _mut67892, _mut67893)))) {
            ret = Double.NaN;
        } else if ((_mut67941 ? (ROR_greater(x, AOR_divide((AOR_plus(a, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67895, _mut67896, _mut67897, _mut67898)), (AOR_plus(AOR_plus(2, b, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67899, _mut67900, _mut67901, _mut67902), a, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67903, _mut67904, _mut67905, _mut67906)), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67907, _mut67908, _mut67909, _mut67910), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67911, _mut67912, _mut67913, _mut67914, _mut67915) || ROR_less_equals(AOR_minus(1, x, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67916, _mut67917, _mut67918, _mut67919), AOR_divide((AOR_plus(b, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67920, _mut67921, _mut67922, _mut67923)), (AOR_plus(AOR_plus(2, b, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67924, _mut67925, _mut67926, _mut67927), a, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67928, _mut67929, _mut67930, _mut67931)), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67932, _mut67933, _mut67934, _mut67935), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67936, _mut67937, _mut67938, _mut67939, _mut67940)) : (ROR_greater(x, AOR_divide((AOR_plus(a, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67895, _mut67896, _mut67897, _mut67898)), (AOR_plus(AOR_plus(2, b, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67899, _mut67900, _mut67901, _mut67902), a, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67903, _mut67904, _mut67905, _mut67906)), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67907, _mut67908, _mut67909, _mut67910), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67911, _mut67912, _mut67913, _mut67914, _mut67915) && ROR_less_equals(AOR_minus(1, x, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67916, _mut67917, _mut67918, _mut67919), AOR_divide((AOR_plus(b, 1, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67920, _mut67921, _mut67922, _mut67923)), (AOR_plus(AOR_plus(2, b, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67924, _mut67925, _mut67926, _mut67927), a, "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67928, _mut67929, _mut67930, _mut67931)), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67932, _mut67933, _mut67934, _mut67935), "org.apache.commons.math3.special.Beta.regularizedBeta_181", _mut67936, _mut67937, _mut67938, _mut67939, _mut67940)))) {
            ret = AOR_minus(1, regularizedBeta(AOR_minus(1, x, "org.apache.commons.math3.special.Beta.getA_218", _mut68079, _mut68080, _mut68081, _mut68082), b, a, epsilon, maxIterations), "org.apache.commons.math3.special.Beta.getA_218", _mut68083, _mut68084, _mut68085, _mut68086);
        } else {
            ContinuedFraction fraction = new ContinuedFraction() {

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected double getB(int n, double x) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.getB_201");
                    double ret;
                    double m;
                    if (ROR_equals(AOR_remainder(n, 2, "org.apache.commons.math3.special.Beta.getB_201", _mut67942, _mut67943, _mut67944, _mut67945), 0, "org.apache.commons.math3.special.Beta.getB_201", _mut67946, _mut67947, _mut67948, _mut67949, _mut67950)) {
                        // even
                        m = AOR_divide(n, 2.0, "org.apache.commons.math3.special.Beta.getB_201", _mut68007, _mut68008, _mut68009, _mut68010);
                        ret = AOR_divide((AOR_multiply(AOR_multiply(m, (AOR_minus(b, m, "org.apache.commons.math3.special.Beta.getB_201", _mut68011, _mut68012, _mut68013, _mut68014)), "org.apache.commons.math3.special.Beta.getB_201", _mut68015, _mut68016, _mut68017, _mut68018), x, "org.apache.commons.math3.special.Beta.getB_201", _mut68019, _mut68020, _mut68021, _mut68022)), (AOR_multiply((AOR_minus(AOR_plus(a, (AOR_multiply(2, m, "org.apache.commons.math3.special.Beta.getB_201", _mut68023, _mut68024, _mut68025, _mut68026)), "org.apache.commons.math3.special.Beta.getB_201", _mut68027, _mut68028, _mut68029, _mut68030), 1, "org.apache.commons.math3.special.Beta.getB_201", _mut68031, _mut68032, _mut68033, _mut68034)), (AOR_plus(a, (AOR_multiply(2, m, "org.apache.commons.math3.special.Beta.getB_201", _mut68035, _mut68036, _mut68037, _mut68038)), "org.apache.commons.math3.special.Beta.getB_201", _mut68039, _mut68040, _mut68041, _mut68042)), "org.apache.commons.math3.special.Beta.getB_201", _mut68043, _mut68044, _mut68045, _mut68046)), "org.apache.commons.math3.special.Beta.getB_201", _mut68047, _mut68048, _mut68049, _mut68050);
                    } else {
                        m = AOR_divide((AOR_minus(n, 1.0, "org.apache.commons.math3.special.Beta.getB_201", _mut67951, _mut67952, _mut67953, _mut67954)), 2.0, "org.apache.commons.math3.special.Beta.getB_201", _mut67955, _mut67956, _mut67957, _mut67958);
                        ret = AOR_divide(-(AOR_multiply(AOR_multiply((AOR_plus(a, m, "org.apache.commons.math3.special.Beta.getB_201", _mut67959, _mut67960, _mut67961, _mut67962)), (AOR_plus(AOR_plus(a, b, "org.apache.commons.math3.special.Beta.getB_201", _mut67963, _mut67964, _mut67965, _mut67966), m, "org.apache.commons.math3.special.Beta.getB_201", _mut67967, _mut67968, _mut67969, _mut67970)), "org.apache.commons.math3.special.Beta.getB_201", _mut67971, _mut67972, _mut67973, _mut67974), x, "org.apache.commons.math3.special.Beta.getB_201", _mut67975, _mut67976, _mut67977, _mut67978)), (AOR_multiply((AOR_plus(a, (AOR_multiply(2, m, "org.apache.commons.math3.special.Beta.getB_201", _mut67979, _mut67980, _mut67981, _mut67982)), "org.apache.commons.math3.special.Beta.getB_201", _mut67983, _mut67984, _mut67985, _mut67986)), (AOR_plus(AOR_plus(a, (AOR_multiply(2, m, "org.apache.commons.math3.special.Beta.getB_201", _mut67987, _mut67988, _mut67989, _mut67990)), "org.apache.commons.math3.special.Beta.getB_201", _mut67991, _mut67992, _mut67993, _mut67994), 1.0, "org.apache.commons.math3.special.Beta.getB_201", _mut67995, _mut67996, _mut67997, _mut67998)), "org.apache.commons.math3.special.Beta.getB_201", _mut67999, _mut68000, _mut68001, _mut68002)), "org.apache.commons.math3.special.Beta.getB_201", _mut68003, _mut68004, _mut68005, _mut68006);
                    }
                    return ret;
                }

                /**
                 * {@inheritDoc}
                 */
                @Override
                protected double getA(int n, double x) {
                    return 1.0;
                }
            };
            ret = AOR_divide(AOR_multiply(FastMath.exp(AOR_minus(AOR_minus(AOR_plus((AOR_multiply(a, FastMath.log(x), "org.apache.commons.math3.special.Beta.getA_218", _mut68051, _mut68052, _mut68053, _mut68054)), (AOR_multiply(b, FastMath.log1p(-x), "org.apache.commons.math3.special.Beta.getA_218", _mut68055, _mut68056, _mut68057, _mut68058)), "org.apache.commons.math3.special.Beta.getA_218", _mut68059, _mut68060, _mut68061, _mut68062), FastMath.log(a), "org.apache.commons.math3.special.Beta.getA_218", _mut68063, _mut68064, _mut68065, _mut68066), logBeta(a, b), "org.apache.commons.math3.special.Beta.getA_218", _mut68067, _mut68068, _mut68069, _mut68070)), 1.0, "org.apache.commons.math3.special.Beta.getA_218", _mut68071, _mut68072, _mut68073, _mut68074), fraction.evaluate(x, epsilon, maxIterations), "org.apache.commons.math3.special.Beta.getA_218", _mut68075, _mut68076, _mut68077, _mut68078);
        }
        return ret;
    }

    /**
     * Returns the natural logarithm of the beta function B(a, b).
     *
     * The implementation of this method is based on:
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/BetaFunction.html">
     * Beta Function</a>, equation (1).</li>
     * </ul>
     *
     * @param a Parameter {@code a}.
     * @param b Parameter {@code b}.
     * @param epsilon This parameter is ignored.
     * @param maxIterations This parameter is ignored.
     * @return log(B(a, b)).
     * @deprecated as of version 3.1, this method is deprecated as the
     * computation of the beta function is no longer iterative; it will be
     * removed in version 4.0. Current implementation of this method
     * internally calls {@link #logBeta(double, double)}.
     */
    @Deprecated
    public static double logBeta(double a, double b, double epsilon, int maxIterations) {
        return logBeta(a, b);
    }

    /**
     * Returns the value of log Γ(a + b) for 1 ≤ a, b ≤ 2. Based on the
     * <em>NSWC Library of Mathematics Subroutines</em> double precision
     * implementation, {@code DGSMLN}. In {@code BetaTest.testLogGammaSum()},
     * this private method is accessed through reflection.
     *
     * @param a First argument.
     * @param b Second argument.
     * @return the value of {@code log(Gamma(a + b))}.
     * @throws OutOfRangeException if {@code a} or {@code b} is lower than
     * {@code 1.0} or greater than {@code 2.0}.
     */
    private static double logGammaSum(final double a, final double b) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logGammaSum_271");
        if ((_mut68097 ? ((ROR_less(a, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68087, _mut68088, _mut68089, _mut68090, _mut68091)) && (ROR_greater(a, 2.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68092, _mut68093, _mut68094, _mut68095, _mut68096))) : ((ROR_less(a, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68087, _mut68088, _mut68089, _mut68090, _mut68091)) || (ROR_greater(a, 2.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68092, _mut68093, _mut68094, _mut68095, _mut68096))))) {
            throw new OutOfRangeException(a, 1.0, 2.0);
        }
        if ((_mut68108 ? ((ROR_less(b, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68098, _mut68099, _mut68100, _mut68101, _mut68102)) && (ROR_greater(b, 2.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68103, _mut68104, _mut68105, _mut68106, _mut68107))) : ((ROR_less(b, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68098, _mut68099, _mut68100, _mut68101, _mut68102)) || (ROR_greater(b, 2.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68103, _mut68104, _mut68105, _mut68106, _mut68107))))) {
            throw new OutOfRangeException(b, 1.0, 2.0);
        }
        final double x = AOR_plus((AOR_minus(a, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68109, _mut68110, _mut68111, _mut68112)), (AOR_minus(b, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68113, _mut68114, _mut68115, _mut68116)), "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68117, _mut68118, _mut68119, _mut68120);
        if (ROR_less_equals(x, 0.5, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68121, _mut68122, _mut68123, _mut68124, _mut68125)) {
            return Gamma.logGamma1p(AOR_plus(1.0, x, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68151, _mut68152, _mut68153, _mut68154));
        } else if (ROR_less_equals(x, 1.5, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68126, _mut68127, _mut68128, _mut68129, _mut68130)) {
            return AOR_plus(Gamma.logGamma1p(x), FastMath.log1p(x), "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68147, _mut68148, _mut68149, _mut68150);
        } else {
            return AOR_plus(Gamma.logGamma1p(AOR_minus(x, 1.0, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68131, _mut68132, _mut68133, _mut68134)), FastMath.log(AOR_multiply(x, (AOR_plus(1.0, x, "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68135, _mut68136, _mut68137, _mut68138)), "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68139, _mut68140, _mut68141, _mut68142)), "org.apache.commons.math3.special.Beta.logGammaSum_271", _mut68143, _mut68144, _mut68145, _mut68146);
        }
    }

    /**
     * Returns the value of log[Γ(b) / Γ(a + b)] for a ≥ 0 and b ≥ 10. Based on
     * the <em>NSWC Library of Mathematics Subroutines</em> double precision
     * implementation, {@code DLGDIV}. In
     * {@code BetaTest.testLogGammaMinusLogGammaSum()}, this private method is
     * accessed through reflection.
     *
     * @param a First argument.
     * @param b Second argument.
     * @return the value of {@code log(Gamma(b) / Gamma(a + b))}.
     * @throws NumberIsTooSmallException if {@code a < 0.0} or {@code b < 10.0}.
     */
    private static double logGammaMinusLogGammaSum(final double a, final double b) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303");
        if (ROR_less(a, 0.0, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68155, _mut68156, _mut68157, _mut68158, _mut68159)) {
            throw new NumberIsTooSmallException(a, 0.0, true);
        }
        if (ROR_less(b, 10.0, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68160, _mut68161, _mut68162, _mut68163, _mut68164)) {
            throw new NumberIsTooSmallException(b, 10.0, true);
        }
        /*
         * d = a + b - 0.5
         */
        final double d;
        final double w;
        if (ROR_less_equals(a, b, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68165, _mut68166, _mut68167, _mut68168, _mut68169)) {
            d = AOR_plus(b, (AOR_minus(a, 0.5, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68178, _mut68179, _mut68180, _mut68181)), "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68182, _mut68183, _mut68184, _mut68185);
            w = deltaMinusDeltaSum(a, b);
        } else {
            d = AOR_plus(a, (AOR_minus(b, 0.5, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68170, _mut68171, _mut68172, _mut68173)), "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68174, _mut68175, _mut68176, _mut68177);
            w = deltaMinusDeltaSum(b, a);
        }
        final double u = AOR_multiply(d, FastMath.log1p(AOR_divide(a, b, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68186, _mut68187, _mut68188, _mut68189)), "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68190, _mut68191, _mut68192, _mut68193);
        final double v = AOR_multiply(a, (AOR_minus(FastMath.log(b), 1.0, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68194, _mut68195, _mut68196, _mut68197)), "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68198, _mut68199, _mut68200, _mut68201);
        return ROR_less_equals(u, v, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68202, _mut68203, _mut68204, _mut68205, _mut68206) ? AOR_minus((AOR_minus(w, u, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68215, _mut68216, _mut68217, _mut68218)), v, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68219, _mut68220, _mut68221, _mut68222) : AOR_minus((AOR_minus(w, v, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68207, _mut68208, _mut68209, _mut68210)), u, "org.apache.commons.math3.special.Beta.logGammaMinusLogGammaSum_303", _mut68211, _mut68212, _mut68213, _mut68214);
    }

    /**
     * Returns the value of Δ(b) - Δ(a + b), with 0 ≤ a ≤ b and b ≥ 10. Based
     * on equations (26), (27) and (28) in Didonato and Morris (1992).
     *
     * @param a First argument.
     * @param b Second argument.
     * @return the value of {@code Delta(b) - Delta(a + b)}
     * @throws OutOfRangeException if {@code a < 0} or {@code a > b}
     * @throws NumberIsTooSmallException if {@code b < 10}
     */
    private static double deltaMinusDeltaSum(final double a, final double b) throws OutOfRangeException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343");
        if ((_mut68233 ? ((ROR_less(a, 0, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68223, _mut68224, _mut68225, _mut68226, _mut68227)) && (ROR_greater(a, b, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68228, _mut68229, _mut68230, _mut68231, _mut68232))) : ((ROR_less(a, 0, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68223, _mut68224, _mut68225, _mut68226, _mut68227)) || (ROR_greater(a, b, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68228, _mut68229, _mut68230, _mut68231, _mut68232))))) {
            throw new OutOfRangeException(a, 0, b);
        }
        if (ROR_less(b, 10, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68234, _mut68235, _mut68236, _mut68237, _mut68238)) {
            throw new NumberIsTooSmallException(b, 10, true);
        }
        final double h = AOR_divide(a, b, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68239, _mut68240, _mut68241, _mut68242);
        final double p = AOR_divide(h, (AOR_plus(1.0, h, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68243, _mut68244, _mut68245, _mut68246)), "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68247, _mut68248, _mut68249, _mut68250);
        final double q = AOR_divide(1.0, (AOR_plus(1.0, h, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68251, _mut68252, _mut68253, _mut68254)), "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68255, _mut68256, _mut68257, _mut68258);
        final double q2 = AOR_multiply(q, q, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68259, _mut68260, _mut68261, _mut68262);
        /*
         * s[i] = 1 + q + ... - q**(2 * i)
         */
        final double[] s = new double[DELTA.length];
        s[0] = 1.0;
        for (int i = 1; ROR_less(i, s.length, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68279, _mut68280, _mut68281, _mut68282, _mut68283); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343");
            s[i] = AOR_plus(1.0, (AOR_plus(q, AOR_multiply(q2, s[AOR_minus(i, 1, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68263, _mut68264, _mut68265, _mut68266)], "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68267, _mut68268, _mut68269, _mut68270), "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68271, _mut68272, _mut68273, _mut68274)), "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68275, _mut68276, _mut68277, _mut68278);
        }
        /*
         * w = Delta(b) - Delta(a + b)
         */
        final double sqrtT = AOR_divide(10.0, b, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68284, _mut68285, _mut68286, _mut68287);
        final double t = AOR_multiply(sqrtT, sqrtT, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68288, _mut68289, _mut68290, _mut68291);
        double w = AOR_multiply(DELTA[AOR_minus(DELTA.length, 1, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68292, _mut68293, _mut68294, _mut68295)], s[AOR_minus(s.length, 1, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68296, _mut68297, _mut68298, _mut68299)], "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68300, _mut68301, _mut68302, _mut68303);
        for (int i = DELTA.length - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68316, _mut68317, _mut68318, _mut68319, _mut68320); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343");
            w = AOR_plus(AOR_multiply(t, w, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68304, _mut68305, _mut68306, _mut68307), AOR_multiply(DELTA[i], s[i], "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68308, _mut68309, _mut68310, _mut68311), "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68312, _mut68313, _mut68314, _mut68315);
        }
        return AOR_divide(AOR_multiply(w, p, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68321, _mut68322, _mut68323, _mut68324), b, "org.apache.commons.math3.special.Beta.deltaMinusDeltaSum_343", _mut68325, _mut68326, _mut68327, _mut68328);
    }

    /**
     * Returns the value of Δ(p) + Δ(q) - Δ(p + q), with p, q ≥ 10. Based on
     * the <em>NSWC Library of Mathematics Subroutines</em> double precision
     * implementation, {@code DBCORR}. In
     * {@code BetaTest.testSumDeltaMinusDeltaSum()}, this private method is
     * accessed through reflection.
     *
     * @param p First argument.
     * @param q Second argument.
     * @return the value of {@code Delta(p) + Delta(q) - Delta(p + q)}.
     * @throws NumberIsTooSmallException if {@code p < 10.0} or {@code q < 10.0}.
     */
    private static double sumDeltaMinusDeltaSum(final double p, final double q) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390");
        if (ROR_less(p, 10.0, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68329, _mut68330, _mut68331, _mut68332, _mut68333)) {
            throw new NumberIsTooSmallException(p, 10.0, true);
        }
        if (ROR_less(q, 10.0, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68334, _mut68335, _mut68336, _mut68337, _mut68338)) {
            throw new NumberIsTooSmallException(q, 10.0, true);
        }
        final double a = FastMath.min(p, q);
        final double b = FastMath.max(p, q);
        final double sqrtT = AOR_divide(10.0, a, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68339, _mut68340, _mut68341, _mut68342);
        final double t = AOR_multiply(sqrtT, sqrtT, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68343, _mut68344, _mut68345, _mut68346);
        double z = DELTA[AOR_minus(DELTA.length, 1, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68347, _mut68348, _mut68349, _mut68350)];
        for (int i = DELTA.length - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68359, _mut68360, _mut68361, _mut68362, _mut68363); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390");
            z = AOR_plus(AOR_multiply(t, z, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68351, _mut68352, _mut68353, _mut68354), DELTA[i], "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68355, _mut68356, _mut68357, _mut68358);
        }
        return AOR_plus(AOR_divide(z, a, "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68364, _mut68365, _mut68366, _mut68367), deltaMinusDeltaSum(a, b), "org.apache.commons.math3.special.Beta.sumDeltaMinusDeltaSum_390", _mut68368, _mut68369, _mut68370, _mut68371);
    }

    /**
     * Returns the value of log B(p, q) for 0 ≤ x ≤ 1 and p, q > 0. Based on the
     * <em>NSWC Library of Mathematics Subroutines</em> implementation,
     * {@code DBETLN}.
     *
     * @param p First argument.
     * @param q Second argument.
     * @return the value of {@code log(Beta(p, q))}, {@code NaN} if
     * {@code p <= 0} or {@code q <= 0}.
     */
    public static double logBeta(final double p, final double q) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logBeta_421");
        if ((_mut68384 ? ((_mut68378 ? ((_mut68372 ? (Double.isNaN(p) && Double.isNaN(q)) : (Double.isNaN(p) || Double.isNaN(q))) && (ROR_less_equals(p, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68373, _mut68374, _mut68375, _mut68376, _mut68377))) : ((_mut68372 ? (Double.isNaN(p) && Double.isNaN(q)) : (Double.isNaN(p) || Double.isNaN(q))) || (ROR_less_equals(p, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68373, _mut68374, _mut68375, _mut68376, _mut68377)))) && (ROR_less_equals(q, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68379, _mut68380, _mut68381, _mut68382, _mut68383))) : ((_mut68378 ? ((_mut68372 ? (Double.isNaN(p) && Double.isNaN(q)) : (Double.isNaN(p) || Double.isNaN(q))) && (ROR_less_equals(p, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68373, _mut68374, _mut68375, _mut68376, _mut68377))) : ((_mut68372 ? (Double.isNaN(p) && Double.isNaN(q)) : (Double.isNaN(p) || Double.isNaN(q))) || (ROR_less_equals(p, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68373, _mut68374, _mut68375, _mut68376, _mut68377)))) || (ROR_less_equals(q, 0.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68379, _mut68380, _mut68381, _mut68382, _mut68383))))) {
            return Double.NaN;
        }
        final double a = FastMath.min(p, q);
        final double b = FastMath.max(p, q);
        if (ROR_greater_equals(a, 10.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68385, _mut68386, _mut68387, _mut68388, _mut68389)) {
            final double w = sumDeltaMinusDeltaSum(a, b);
            final double h = AOR_divide(a, b, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68569, _mut68570, _mut68571, _mut68572);
            final double c = AOR_divide(h, (AOR_plus(1.0, h, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68573, _mut68574, _mut68575, _mut68576)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68577, _mut68578, _mut68579, _mut68580);
            final double u = AOR_multiply(-(AOR_minus(a, 0.5, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68581, _mut68582, _mut68583, _mut68584)), FastMath.log(c), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68585, _mut68586, _mut68587, _mut68588);
            final double v = AOR_multiply(b, FastMath.log1p(h), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68589, _mut68590, _mut68591, _mut68592);
            if (ROR_less_equals(u, v, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68593, _mut68594, _mut68595, _mut68596, _mut68597)) {
                return AOR_minus((AOR_minus((AOR_plus((AOR_plus(AOR_multiply(-0.5, FastMath.log(b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68618, _mut68619, _mut68620, _mut68621), HALF_LOG_TWO_PI, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68622, _mut68623, _mut68624, _mut68625)), w, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68626, _mut68627, _mut68628, _mut68629)), u, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68630, _mut68631, _mut68632, _mut68633)), v, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68634, _mut68635, _mut68636, _mut68637);
            } else {
                return AOR_minus((AOR_minus((AOR_plus((AOR_plus(AOR_multiply(-0.5, FastMath.log(b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68598, _mut68599, _mut68600, _mut68601), HALF_LOG_TWO_PI, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68602, _mut68603, _mut68604, _mut68605)), w, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68606, _mut68607, _mut68608, _mut68609)), v, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68610, _mut68611, _mut68612, _mut68613)), u, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68614, _mut68615, _mut68616, _mut68617);
            }
        } else if (ROR_greater(a, 2.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68390, _mut68391, _mut68392, _mut68393, _mut68394)) {
            if (ROR_greater(b, 1000.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68468, _mut68469, _mut68470, _mut68471, _mut68472)) {
                final int n = (int) FastMath.floor(AOR_minus(a, 1.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68532, _mut68533, _mut68534, _mut68535));
                double prod = 1.0;
                double ared = a;
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68548, _mut68549, _mut68550, _mut68551, _mut68552); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logBeta_421");
                    ared -= 1.0;
                    prod *= AOR_divide(ared, (AOR_plus(1.0, AOR_divide(ared, b, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68536, _mut68537, _mut68538, _mut68539), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68540, _mut68541, _mut68542, _mut68543)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68544, _mut68545, _mut68546, _mut68547);
                }
                return AOR_plus((AOR_minus(FastMath.log(prod), AOR_multiply(n, FastMath.log(b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68553, _mut68554, _mut68555, _mut68556), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68557, _mut68558, _mut68559, _mut68560)), (AOR_plus(Gamma.logGamma(ared), logGammaMinusLogGammaSum(ared, b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68561, _mut68562, _mut68563, _mut68564)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68565, _mut68566, _mut68567, _mut68568);
            } else {
                double prod1 = 1.0;
                double ared = a;
                while (ROR_greater(ared, 2.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68485, _mut68486, _mut68487, _mut68488, _mut68489)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logBeta_421");
                    ared -= 1.0;
                    final double h = AOR_divide(ared, b, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68473, _mut68474, _mut68475, _mut68476);
                    prod1 *= AOR_divide(h, (AOR_plus(1.0, h, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68477, _mut68478, _mut68479, _mut68480)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68481, _mut68482, _mut68483, _mut68484);
                }
                if (ROR_less(b, 10.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68490, _mut68491, _mut68492, _mut68493, _mut68494)) {
                    double prod2 = 1.0;
                    double bred = b;
                    while (ROR_greater(bred, 2.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68511, _mut68512, _mut68513, _mut68514, _mut68515)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logBeta_421");
                        bred -= 1.0;
                        prod2 *= AOR_divide(bred, (AOR_plus(ared, bred, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68503, _mut68504, _mut68505, _mut68506)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68507, _mut68508, _mut68509, _mut68510);
                    }
                    return AOR_plus(AOR_plus(FastMath.log(prod1), FastMath.log(prod2), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68516, _mut68517, _mut68518, _mut68519), (AOR_plus(Gamma.logGamma(ared), (AOR_minus(Gamma.logGamma(bred), logGammaSum(ared, bred), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68520, _mut68521, _mut68522, _mut68523)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68524, _mut68525, _mut68526, _mut68527)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68528, _mut68529, _mut68530, _mut68531);
                } else {
                    return AOR_plus(AOR_plus(FastMath.log(prod1), Gamma.logGamma(ared), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68495, _mut68496, _mut68497, _mut68498), logGammaMinusLogGammaSum(ared, b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68499, _mut68500, _mut68501, _mut68502);
                }
            }
        } else if (ROR_greater_equals(a, 1.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68395, _mut68396, _mut68397, _mut68398, _mut68399)) {
            if (ROR_greater(b, 2.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68421, _mut68422, _mut68423, _mut68424, _mut68425)) {
                if (ROR_less(b, 10.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68434, _mut68435, _mut68436, _mut68437, _mut68438)) {
                    double prod = 1.0;
                    double bred = b;
                    while (ROR_greater(bred, 2.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68451, _mut68452, _mut68453, _mut68454, _mut68455)) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Beta.logBeta_421");
                        bred -= 1.0;
                        prod *= AOR_divide(bred, (AOR_plus(a, bred, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68443, _mut68444, _mut68445, _mut68446)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68447, _mut68448, _mut68449, _mut68450);
                    }
                    return AOR_plus(FastMath.log(prod), (AOR_plus(Gamma.logGamma(a), (AOR_minus(Gamma.logGamma(bred), logGammaSum(a, bred), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68456, _mut68457, _mut68458, _mut68459)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68460, _mut68461, _mut68462, _mut68463)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68464, _mut68465, _mut68466, _mut68467);
                } else {
                    return AOR_plus(Gamma.logGamma(a), logGammaMinusLogGammaSum(a, b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68439, _mut68440, _mut68441, _mut68442);
                }
            } else {
                return AOR_minus(AOR_plus(Gamma.logGamma(a), Gamma.logGamma(b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68426, _mut68427, _mut68428, _mut68429), logGammaSum(a, b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68430, _mut68431, _mut68432, _mut68433);
            }
        } else {
            if (ROR_greater_equals(b, 10.0, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68400, _mut68401, _mut68402, _mut68403, _mut68404)) {
                return AOR_plus(Gamma.logGamma(a), logGammaMinusLogGammaSum(a, b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68417, _mut68418, _mut68419, _mut68420);
            } else {
                // The following command turns out to be more accurate.
                return FastMath.log(AOR_divide(AOR_multiply(Gamma.gamma(a), Gamma.gamma(b), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68405, _mut68406, _mut68407, _mut68408), Gamma.gamma(AOR_plus(a, b, "org.apache.commons.math3.special.Beta.logBeta_421", _mut68409, _mut68410, _mut68411, _mut68412)), "org.apache.commons.math3.special.Beta.logBeta_421", _mut68413, _mut68414, _mut68415, _mut68416));
            }
        }
    }
}
