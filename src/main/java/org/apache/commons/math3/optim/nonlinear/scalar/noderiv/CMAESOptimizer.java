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
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * An implementation of the active Covariance Matrix Adaptation Evolution Strategy (CMA-ES)
 * for non-linear, non-convex, non-smooth, global function minimization.
 * <p>
 * The CMA-Evolution Strategy (CMA-ES) is a reliable stochastic optimization method
 * which should be applied if derivative-based methods, e.g. quasi-Newton BFGS or
 * conjugate gradient, fail due to a rugged search landscape (e.g. noise, local
 * optima, outlier, etc.) of the objective function. Like a
 * quasi-Newton method, the CMA-ES learns and applies a variable metric
 * on the underlying search space. Unlike a quasi-Newton method, the
 * CMA-ES neither estimates nor uses gradients, making it considerably more
 * reliable in terms of finding a good, or even close to optimal, solution.
 * <p>
 * In general, on smooth objective functions the CMA-ES is roughly ten times
 * slower than BFGS (counting objective function evaluations, no gradients provided).
 * For up to <math>N=10</math> variables also the derivative-free simplex
 * direct search method (Nelder and Mead) can be faster, but it is
 * far less reliable than CMA-ES.
 * <p>
 * The CMA-ES is particularly well suited for non-separable
 * and/or badly conditioned problems. To observe the advantage of CMA compared
 * to a conventional evolution strategy, it will usually take about
 * <math>30 N</math> function evaluations. On difficult problems the complete
 * optimization (a single run) is expected to take <em>roughly</em> between
 * <math>30 N</math> and <math>300 N<sup>2</sup></math>
 * function evaluations.
 * <p>
 * This implementation is translated and adapted from the Matlab version
 * of the CMA-ES algorithm as implemented in module {@code cmaes.m} version 3.51.
 * <p>
 * For more information, please refer to the following links:
 * <ul>
 *  <li><a href="http://www.lri.fr/~hansen/cmaes.m">Matlab code</a></li>
 *  <li><a href="http://www.lri.fr/~hansen/cmaesintro.html">Introduction to CMA-ES</a></li>
 *  <li><a href="http://en.wikipedia.org/wiki/CMA-ES">Wikipedia</a></li>
 * </ul>
 *
 * @since 3.0
 */
public class CMAESOptimizer extends MultivariateOptimizer {

    @Conditional
    public static boolean _mut60252 = false, _mut60253 = false, _mut60254 = false, _mut60255 = false, _mut60256 = false, _mut60257 = false, _mut60258 = false, _mut60259 = false, _mut60260 = false, _mut60261 = false, _mut60262 = false, _mut60263 = false, _mut60264 = false, _mut60265 = false, _mut60266 = false, _mut60267 = false, _mut60268 = false, _mut60269 = false, _mut60270 = false, _mut60271 = false, _mut60272 = false, _mut60273 = false, _mut60274 = false, _mut60275 = false, _mut60276 = false, _mut60277 = false, _mut60278 = false, _mut60279 = false, _mut60280 = false, _mut60281 = false, _mut60282 = false, _mut60283 = false, _mut60284 = false, _mut60285 = false, _mut60286 = false, _mut60287 = false, _mut60288 = false, _mut60289 = false, _mut60290 = false, _mut60291 = false, _mut60292 = false, _mut60293 = false, _mut60294 = false, _mut60295 = false, _mut60296 = false, _mut60297 = false, _mut60298 = false, _mut60299 = false, _mut60300 = false, _mut60301 = false, _mut60302 = false, _mut60303 = false, _mut60304 = false, _mut60305 = false, _mut60306 = false, _mut60307 = false, _mut60308 = false, _mut60309 = false, _mut60310 = false, _mut60311 = false, _mut60312 = false, _mut60313 = false, _mut60314 = false, _mut60315 = false, _mut60316 = false, _mut60317 = false, _mut60318 = false, _mut60319 = false, _mut60320 = false, _mut60321 = false, _mut60322 = false, _mut60323 = false, _mut60324 = false, _mut60325 = false, _mut60326 = false, _mut60327 = false, _mut60328 = false, _mut60329 = false, _mut60330 = false, _mut60331 = false, _mut60332 = false, _mut60333 = false, _mut60334 = false, _mut60335 = false, _mut60336 = false, _mut60337 = false, _mut60338 = false, _mut60339 = false, _mut60340 = false, _mut60341 = false, _mut60342 = false, _mut60343 = false, _mut60344 = false, _mut60345 = false, _mut60346 = false, _mut60347 = false, _mut60348 = false, _mut60349 = false, _mut60350 = false, _mut60351 = false, _mut60352 = false, _mut60353 = false, _mut60354 = false, _mut60355 = false, _mut60356 = false, _mut60357 = false, _mut60358 = false, _mut60359 = false, _mut60360 = false, _mut60361 = false, _mut60362 = false, _mut60363 = false, _mut60364 = false, _mut60365 = false, _mut60366 = false, _mut60367 = false, _mut60368 = false, _mut60369 = false, _mut60370 = false, _mut60371 = false, _mut60372 = false, _mut60373 = false, _mut60374 = false, _mut60375 = false, _mut60376 = false, _mut60377 = false, _mut60378 = false, _mut60379 = false, _mut60380 = false, _mut60381 = false, _mut60382 = false, _mut60383 = false, _mut60384 = false, _mut60385 = false, _mut60386 = false, _mut60387 = false, _mut60388 = false, _mut60389 = false, _mut60390 = false, _mut60391 = false, _mut60392 = false, _mut60393 = false, _mut60394 = false, _mut60395 = false, _mut60396 = false, _mut60397 = false, _mut60398 = false, _mut60399 = false, _mut60400 = false, _mut60401 = false, _mut60402 = false, _mut60403 = false, _mut60404 = false, _mut60405 = false, _mut60406 = false, _mut60407 = false, _mut60408 = false, _mut60409 = false, _mut60410 = false, _mut60411 = false, _mut60412 = false, _mut60413 = false, _mut60414 = false, _mut60415 = false, _mut60416 = false, _mut60417 = false, _mut60418 = false, _mut60419 = false, _mut60420 = false, _mut60421 = false, _mut60422 = false, _mut60423 = false, _mut60424 = false, _mut60425 = false, _mut60426 = false, _mut60427 = false, _mut60428 = false, _mut60429 = false, _mut60430 = false, _mut60431 = false, _mut60432 = false, _mut60433 = false, _mut60434 = false, _mut60435 = false, _mut60436 = false, _mut60437 = false, _mut60438 = false, _mut60439 = false, _mut60440 = false, _mut60441 = false, _mut60442 = false, _mut60443 = false, _mut60444 = false, _mut60445 = false, _mut60446 = false, _mut60447 = false, _mut60448 = false, _mut60449 = false, _mut60450 = false, _mut60451 = false, _mut60452 = false, _mut60453 = false, _mut60454 = false, _mut60455 = false, _mut60456 = false, _mut60457 = false, _mut60458 = false, _mut60459 = false, _mut60460 = false, _mut60461 = false, _mut60462 = false, _mut60463 = false, _mut60464 = false, _mut60465 = false, _mut60466 = false, _mut60467 = false, _mut60468 = false, _mut60469 = false, _mut60470 = false, _mut60471 = false, _mut60472 = false, _mut60473 = false, _mut60474 = false, _mut60475 = false, _mut60476 = false, _mut60477 = false, _mut60478 = false, _mut60479 = false, _mut60480 = false, _mut60481 = false, _mut60482 = false, _mut60483 = false, _mut60484 = false, _mut60485 = false, _mut60486 = false, _mut60487 = false, _mut60488 = false, _mut60489 = false, _mut60490 = false, _mut60491 = false, _mut60492 = false, _mut60493 = false, _mut60494 = false, _mut60495 = false, _mut60496 = false, _mut60497 = false, _mut60498 = false, _mut60499 = false, _mut60500 = false, _mut60501 = false, _mut60502 = false, _mut60503 = false, _mut60504 = false, _mut60505 = false, _mut60506 = false, _mut60507 = false, _mut60508 = false, _mut60509 = false, _mut60510 = false, _mut60511 = false, _mut60512 = false, _mut60513 = false, _mut60514 = false, _mut60515 = false, _mut60516 = false, _mut60517 = false, _mut60518 = false, _mut60519 = false, _mut60520 = false, _mut60521 = false, _mut60522 = false, _mut60523 = false, _mut60524 = false, _mut60525 = false, _mut60526 = false, _mut60527 = false, _mut60528 = false, _mut60529 = false, _mut60530 = false, _mut60531 = false, _mut60532 = false, _mut60533 = false, _mut60534 = false, _mut60535 = false, _mut60536 = false, _mut60537 = false, _mut60538 = false, _mut60539 = false, _mut60540 = false, _mut60541 = false, _mut60542 = false, _mut60543 = false, _mut60544 = false, _mut60545 = false, _mut60546 = false, _mut60547 = false, _mut60548 = false, _mut60549 = false, _mut60550 = false, _mut60551 = false, _mut60552 = false, _mut60553 = false, _mut60554 = false, _mut60555 = false, _mut60556 = false, _mut60557 = false, _mut60558 = false, _mut60559 = false, _mut60560 = false, _mut60561 = false, _mut60562 = false, _mut60563 = false, _mut60564 = false, _mut60565 = false, _mut60566 = false, _mut60567 = false, _mut60568 = false, _mut60569 = false, _mut60570 = false, _mut60571 = false, _mut60572 = false, _mut60573 = false, _mut60574 = false, _mut60575 = false, _mut60576 = false, _mut60577 = false, _mut60578 = false, _mut60579 = false, _mut60580 = false, _mut60581 = false, _mut60582 = false, _mut60583 = false, _mut60584 = false, _mut60585 = false, _mut60586 = false, _mut60587 = false, _mut60588 = false, _mut60589 = false, _mut60590 = false, _mut60591 = false, _mut60592 = false, _mut60593 = false, _mut60594 = false, _mut60595 = false, _mut60596 = false, _mut60597 = false, _mut60598 = false, _mut60599 = false, _mut60600 = false, _mut60601 = false, _mut60602 = false, _mut60603 = false, _mut60604 = false, _mut60605 = false, _mut60606 = false, _mut60607 = false, _mut60608 = false, _mut60609 = false, _mut60610 = false, _mut60611 = false, _mut60612 = false, _mut60613 = false, _mut60614 = false, _mut60615 = false, _mut60616 = false, _mut60617 = false, _mut60618 = false, _mut60619 = false, _mut60620 = false, _mut60621 = false, _mut60622 = false, _mut60623 = false, _mut60624 = false, _mut60625 = false, _mut60626 = false, _mut60627 = false, _mut60628 = false, _mut60629 = false, _mut60630 = false, _mut60631 = false, _mut60632 = false, _mut60633 = false, _mut60634 = false, _mut60635 = false, _mut60636 = false, _mut60637 = false, _mut60638 = false, _mut60639 = false, _mut60640 = false, _mut60641 = false, _mut60642 = false, _mut60643 = false, _mut60644 = false, _mut60645 = false, _mut60646 = false, _mut60647 = false, _mut60648 = false, _mut60649 = false, _mut60650 = false, _mut60651 = false, _mut60652 = false, _mut60653 = false, _mut60654 = false, _mut60655 = false, _mut60656 = false, _mut60657 = false, _mut60658 = false, _mut60659 = false, _mut60660 = false, _mut60661 = false, _mut60662 = false, _mut60663 = false, _mut60664 = false, _mut60665 = false, _mut60666 = false, _mut60667 = false, _mut60668 = false, _mut60669 = false, _mut60670 = false, _mut60671 = false, _mut60672 = false, _mut60673 = false, _mut60674 = false, _mut60675 = false, _mut60676 = false, _mut60677 = false, _mut60678 = false, _mut60679 = false, _mut60680 = false, _mut60681 = false, _mut60682 = false, _mut60683 = false, _mut60684 = false, _mut60685 = false, _mut60686 = false, _mut60687 = false, _mut60688 = false, _mut60689 = false, _mut60690 = false, _mut60691 = false, _mut60692 = false, _mut60693 = false, _mut60694 = false, _mut60695 = false, _mut60696 = false, _mut60697 = false, _mut60698 = false, _mut60699 = false, _mut60700 = false, _mut60701 = false, _mut60702 = false, _mut60703 = false, _mut60704 = false, _mut60705 = false, _mut60706 = false, _mut60707 = false, _mut60708 = false, _mut60709 = false, _mut60710 = false, _mut60711 = false, _mut60712 = false, _mut60713 = false, _mut60714 = false, _mut60715 = false, _mut60716 = false, _mut60717 = false, _mut60718 = false, _mut60719 = false, _mut60720 = false, _mut60721 = false, _mut60722 = false, _mut60723 = false, _mut60724 = false, _mut60725 = false, _mut60726 = false, _mut60727 = false, _mut60728 = false, _mut60729 = false, _mut60730 = false, _mut60731 = false, _mut60732 = false, _mut60733 = false, _mut60734 = false, _mut60735 = false, _mut60736 = false, _mut60737 = false, _mut60738 = false, _mut60739 = false, _mut60740 = false, _mut60741 = false, _mut60742 = false, _mut60743 = false, _mut60744 = false, _mut60745 = false, _mut60746 = false, _mut60747 = false, _mut60748 = false, _mut60749 = false, _mut60750 = false, _mut60751 = false, _mut60752 = false, _mut60753 = false, _mut60754 = false, _mut60755 = false, _mut60756 = false, _mut60757 = false, _mut60758 = false, _mut60759 = false, _mut60760 = false, _mut60761 = false, _mut60762 = false, _mut60763 = false, _mut60764 = false, _mut60765 = false, _mut60766 = false, _mut60767 = false, _mut60768 = false, _mut60769 = false, _mut60770 = false, _mut60771 = false, _mut60772 = false, _mut60773 = false, _mut60774 = false, _mut60775 = false, _mut60776 = false, _mut60777 = false, _mut60778 = false, _mut60779 = false, _mut60780 = false, _mut60781 = false, _mut60782 = false, _mut60783 = false, _mut60784 = false, _mut60785 = false, _mut60786 = false, _mut60787 = false, _mut60788 = false, _mut60789 = false, _mut60790 = false, _mut60791 = false, _mut60792 = false, _mut60793 = false, _mut60794 = false, _mut60795 = false, _mut60796 = false, _mut60797 = false, _mut60798 = false, _mut60799 = false, _mut60800 = false, _mut60801 = false, _mut60802 = false, _mut60803 = false, _mut60804 = false, _mut60805 = false, _mut60806 = false, _mut60807 = false, _mut60808 = false, _mut60809 = false, _mut60810 = false, _mut60811 = false, _mut60812 = false, _mut60813 = false, _mut60814 = false, _mut60815 = false, _mut60816 = false, _mut60817 = false, _mut60818 = false, _mut60819 = false, _mut60820 = false, _mut60821 = false, _mut60822 = false, _mut60823 = false, _mut60824 = false, _mut60825 = false, _mut60826 = false, _mut60827 = false, _mut60828 = false, _mut60829 = false, _mut60830 = false, _mut60831 = false, _mut60832 = false, _mut60833 = false, _mut60834 = false, _mut60835 = false, _mut60836 = false, _mut60837 = false, _mut60838 = false, _mut60839 = false, _mut60840 = false, _mut60841 = false, _mut60842 = false, _mut60843 = false, _mut60844 = false, _mut60845 = false, _mut60846 = false, _mut60847 = false, _mut60848 = false, _mut60849 = false, _mut60850 = false, _mut60851 = false, _mut60852 = false, _mut60853 = false, _mut60854 = false, _mut60855 = false, _mut60856 = false, _mut60857 = false, _mut60858 = false, _mut60859 = false, _mut60860 = false, _mut60861 = false, _mut60862 = false, _mut60863 = false, _mut60864 = false, _mut60865 = false, _mut60866 = false, _mut60867 = false, _mut60868 = false, _mut60869 = false, _mut60870 = false, _mut60871 = false, _mut60872 = false, _mut60873 = false, _mut60874 = false, _mut60875 = false, _mut60876 = false, _mut60877 = false, _mut60878 = false, _mut60879 = false, _mut60880 = false, _mut60881 = false, _mut60882 = false, _mut60883 = false, _mut60884 = false, _mut60885 = false, _mut60886 = false, _mut60887 = false, _mut60888 = false, _mut60889 = false, _mut60890 = false, _mut60891 = false, _mut60892 = false, _mut60893 = false, _mut60894 = false, _mut60895 = false, _mut60896 = false, _mut60897 = false, _mut60898 = false, _mut60899 = false, _mut60900 = false, _mut60901 = false, _mut60902 = false, _mut60903 = false, _mut60904 = false, _mut60905 = false, _mut60906 = false, _mut60907 = false, _mut60908 = false, _mut60909 = false, _mut60910 = false, _mut60911 = false, _mut60912 = false, _mut60913 = false, _mut60914 = false, _mut60915 = false, _mut60916 = false, _mut60917 = false, _mut60918 = false, _mut60919 = false, _mut60920 = false, _mut60921 = false, _mut60922 = false, _mut60923 = false, _mut60924 = false, _mut60925 = false, _mut60926 = false, _mut60927 = false, _mut60928 = false, _mut60929 = false, _mut60930 = false, _mut60931 = false, _mut60932 = false, _mut60933 = false, _mut60934 = false, _mut60935 = false, _mut60936 = false, _mut60937 = false, _mut60938 = false, _mut60939 = false, _mut60940 = false, _mut60941 = false, _mut60942 = false, _mut60943 = false, _mut60944 = false, _mut60945 = false, _mut60946 = false, _mut60947 = false, _mut60948 = false, _mut60949 = false, _mut60950 = false, _mut60951 = false, _mut60952 = false, _mut60953 = false, _mut60954 = false, _mut60955 = false, _mut60956 = false, _mut60957 = false, _mut60958 = false, _mut60959 = false, _mut60960 = false, _mut60961 = false, _mut60962 = false, _mut60963 = false, _mut60964 = false, _mut60965 = false, _mut60966 = false, _mut60967 = false, _mut60968 = false, _mut60969 = false, _mut60970 = false, _mut60971 = false, _mut60972 = false, _mut60973 = false, _mut60974 = false, _mut60975 = false, _mut60976 = false, _mut60977 = false, _mut60978 = false, _mut60979 = false, _mut60980 = false, _mut60981 = false, _mut60982 = false, _mut60983 = false, _mut60984 = false, _mut60985 = false, _mut60986 = false, _mut60987 = false, _mut60988 = false, _mut60989 = false, _mut60990 = false, _mut60991 = false, _mut60992 = false, _mut60993 = false, _mut60994 = false, _mut60995 = false, _mut60996 = false, _mut60997 = false, _mut60998 = false, _mut60999 = false, _mut61000 = false, _mut61001 = false, _mut61002 = false, _mut61003 = false, _mut61004 = false, _mut61005 = false, _mut61006 = false, _mut61007 = false, _mut61008 = false, _mut61009 = false, _mut61010 = false, _mut61011 = false, _mut61012 = false, _mut61013 = false, _mut61014 = false, _mut61015 = false, _mut61016 = false, _mut61017 = false, _mut61018 = false, _mut61019 = false, _mut61020 = false, _mut61021 = false, _mut61022 = false, _mut61023 = false, _mut61024 = false, _mut61025 = false, _mut61026 = false, _mut61027 = false, _mut61028 = false, _mut61029 = false, _mut61030 = false, _mut61031 = false, _mut61032 = false, _mut61033 = false, _mut61034 = false, _mut61035 = false, _mut61036 = false, _mut61037 = false, _mut61038 = false, _mut61039 = false, _mut61040 = false, _mut61041 = false, _mut61042 = false, _mut61043 = false, _mut61044 = false, _mut61045 = false, _mut61046 = false, _mut61047 = false, _mut61048 = false, _mut61049 = false, _mut61050 = false, _mut61051 = false, _mut61052 = false, _mut61053 = false, _mut61054 = false, _mut61055 = false, _mut61056 = false, _mut61057 = false, _mut61058 = false, _mut61059 = false, _mut61060 = false, _mut61061 = false, _mut61062 = false, _mut61063 = false, _mut61064 = false, _mut61065 = false, _mut61066 = false, _mut61067 = false, _mut61068 = false, _mut61069 = false, _mut61070 = false, _mut61071 = false, _mut61072 = false, _mut61073 = false, _mut61074 = false, _mut61075 = false, _mut61076 = false, _mut61077 = false, _mut61078 = false, _mut61079 = false, _mut61080 = false, _mut61081 = false, _mut61082 = false, _mut61083 = false, _mut61084 = false, _mut61085 = false, _mut61086 = false, _mut61087 = false, _mut61088 = false, _mut61089 = false, _mut61090 = false, _mut61091 = false, _mut61092 = false, _mut61093 = false, _mut61094 = false, _mut61095 = false, _mut61096 = false, _mut61097 = false, _mut61098 = false, _mut61099 = false, _mut61100 = false, _mut61101 = false, _mut61102 = false, _mut61103 = false, _mut61104 = false, _mut61105 = false, _mut61106 = false, _mut61107 = false, _mut61108 = false, _mut61109 = false, _mut61110 = false, _mut61111 = false, _mut61112 = false, _mut61113 = false, _mut61114 = false, _mut61115 = false, _mut61116 = false, _mut61117 = false, _mut61118 = false, _mut61119 = false, _mut61120 = false, _mut61121 = false, _mut61122 = false, _mut61123 = false, _mut61124 = false, _mut61125 = false, _mut61126 = false, _mut61127 = false, _mut61128 = false, _mut61129 = false, _mut61130 = false, _mut61131 = false, _mut61132 = false, _mut61133 = false, _mut61134 = false, _mut61135 = false, _mut61136 = false, _mut61137 = false, _mut61138 = false, _mut61139 = false, _mut61140 = false, _mut61141 = false, _mut61142 = false, _mut61143 = false, _mut61144 = false, _mut61145 = false, _mut61146 = false, _mut61147 = false, _mut61148 = false, _mut61149 = false, _mut61150 = false, _mut61151 = false, _mut61152 = false, _mut61153 = false, _mut61154 = false, _mut61155 = false, _mut61156 = false, _mut61157 = false, _mut61158 = false, _mut61159 = false, _mut61160 = false, _mut61161 = false, _mut61162 = false, _mut61163 = false, _mut61164 = false, _mut61165 = false, _mut61166 = false, _mut61167 = false, _mut61168 = false, _mut61169 = false, _mut61170 = false, _mut61171 = false, _mut61172 = false, _mut61173 = false, _mut61174 = false, _mut61175 = false, _mut61176 = false, _mut61177 = false, _mut61178 = false, _mut61179 = false, _mut61180 = false, _mut61181 = false, _mut61182 = false, _mut61183 = false, _mut61184 = false, _mut61185 = false, _mut61186 = false, _mut61187 = false, _mut61188 = false, _mut61189 = false, _mut61190 = false, _mut61191 = false, _mut61192 = false, _mut61193 = false, _mut61194 = false, _mut61195 = false, _mut61196 = false, _mut61197 = false, _mut61198 = false, _mut61199 = false, _mut61200 = false, _mut61201 = false, _mut61202 = false, _mut61203 = false, _mut61204 = false, _mut61205 = false, _mut61206 = false, _mut61207 = false, _mut61208 = false, _mut61209 = false, _mut61210 = false, _mut61211 = false, _mut61212 = false, _mut61213 = false, _mut61214 = false, _mut61215 = false, _mut61216 = false, _mut61217 = false, _mut61218 = false, _mut61219 = false, _mut61220 = false, _mut61221 = false, _mut61222 = false, _mut61223 = false, _mut61224 = false, _mut61225 = false, _mut61226 = false, _mut61227 = false, _mut61228 = false, _mut61229 = false, _mut61230 = false, _mut61231 = false, _mut61232 = false, _mut61233 = false, _mut61234 = false, _mut61235 = false, _mut61236 = false, _mut61237 = false, _mut61238 = false, _mut61239 = false, _mut61240 = false, _mut61241 = false, _mut61242 = false, _mut61243 = false, _mut61244 = false, _mut61245 = false, _mut61246 = false, _mut61247 = false, _mut61248 = false, _mut61249 = false, _mut61250 = false, _mut61251 = false, _mut61252 = false, _mut61253 = false, _mut61254 = false, _mut61255 = false, _mut61256 = false, _mut61257 = false, _mut61258 = false, _mut61259 = false, _mut61260 = false, _mut61261 = false, _mut61262 = false, _mut61263 = false, _mut61264 = false, _mut61265 = false, _mut61266 = false, _mut61267 = false, _mut61268 = false, _mut61269 = false, _mut61270 = false, _mut61271 = false, _mut61272 = false, _mut61273 = false, _mut61274 = false, _mut61275 = false, _mut61276 = false, _mut61277 = false, _mut61278 = false, _mut61279 = false, _mut61280 = false, _mut61281 = false, _mut61282 = false, _mut61283 = false, _mut61284 = false, _mut61285 = false, _mut61286 = false, _mut61287 = false, _mut61288 = false, _mut61289 = false, _mut61290 = false, _mut61291 = false, _mut61292 = false, _mut61293 = false, _mut61294 = false, _mut61295 = false, _mut61296 = false, _mut61297 = false, _mut61298 = false, _mut61299 = false, _mut61300 = false, _mut61301 = false, _mut61302 = false, _mut61303 = false, _mut61304 = false, _mut61305 = false, _mut61306 = false, _mut61307 = false, _mut61308 = false, _mut61309 = false, _mut61310 = false, _mut61311 = false, _mut61312 = false, _mut61313 = false, _mut61314 = false, _mut61315 = false, _mut61316 = false, _mut61317 = false, _mut61318 = false, _mut61319 = false, _mut61320 = false, _mut61321 = false, _mut61322 = false, _mut61323 = false, _mut61324 = false, _mut61325 = false, _mut61326 = false, _mut61327 = false, _mut61328 = false, _mut61329 = false, _mut61330 = false, _mut61331 = false, _mut61332 = false, _mut61333 = false, _mut61334 = false, _mut61335 = false, _mut61336 = false, _mut61337 = false, _mut61338 = false, _mut61339 = false, _mut61340 = false, _mut61341 = false, _mut61342 = false, _mut61343 = false, _mut61344 = false, _mut61345 = false, _mut61346 = false, _mut61347 = false, _mut61348 = false, _mut61349 = false, _mut61350 = false, _mut61351 = false, _mut61352 = false, _mut61353 = false, _mut61354 = false, _mut61355 = false, _mut61356 = false, _mut61357 = false, _mut61358 = false, _mut61359 = false, _mut61360 = false, _mut61361 = false, _mut61362 = false, _mut61363 = false, _mut61364 = false, _mut61365 = false, _mut61366 = false, _mut61367 = false, _mut61368 = false, _mut61369 = false, _mut61370 = false, _mut61371 = false, _mut61372 = false, _mut61373 = false, _mut61374 = false, _mut61375 = false, _mut61376 = false, _mut61377 = false, _mut61378 = false, _mut61379 = false, _mut61380 = false, _mut61381 = false, _mut61382 = false, _mut61383 = false, _mut61384 = false, _mut61385 = false, _mut61386 = false, _mut61387 = false, _mut61388 = false, _mut61389 = false, _mut61390 = false, _mut61391 = false, _mut61392 = false, _mut61393 = false, _mut61394 = false, _mut61395 = false, _mut61396 = false, _mut61397 = false, _mut61398 = false, _mut61399 = false, _mut61400 = false;

    // population size
    private int lambda;

    /**
     * Covariance update mechanism, default is active CMA. isActiveCMA = true
     * turns on "active CMA" with a negative update of the covariance matrix and
     * checks for positive definiteness. OPTS.CMA.active = 2 does not check for
     * pos. def. and is numerically faster. Active CMA usually speeds up the
     * adaptation.
     */
    private final boolean isActiveCMA;

    /**
     * Determines how often a new random offspring is generated in case it is
     * not feasible / beyond the defined limits, default is 0.
     */
    private final int checkFeasableCount;

    /**
     * @see Sigma
     */
    private double[] inputSigma;

    /**
     * Number of objective variables/problem dimension
     */
    private int dimension;

    /**
     * Defines the number of initial iterations, where the covariance matrix
     * remains diagonal and the algorithm has internally linear time complexity.
     * diagonalOnly = 1 means keeping the covariance matrix always diagonal and
     * this setting also exhibits linear space complexity. This can be
     * particularly useful for dimension > 100.
     * @see <a href="http://hal.archives-ouvertes.fr/inria-00287367/en">A Simple Modification in CMA-ES</a>
     */
    private int diagonalOnly;

    /**
     * Number of objective variables/problem dimension
     */
    private boolean isMinimize = true;

    /**
     * Indicates whether statistic data is collected.
     */
    private final boolean generateStatistics;

    /**
     * Maximal number of iterations allowed.
     */
    private final int maxIterations;

    /**
     * Limit for fitness value.
     */
    private final double stopFitness;

    /**
     * Stop if x-changes larger stopTolUpX.
     */
    private double stopTolUpX;

    /**
     * Stop if x-change smaller stopTolX.
     */
    private double stopTolX;

    /**
     * Stop if fun-changes smaller stopTolFun.
     */
    private double stopTolFun;

    /**
     * Stop if back fun-changes smaller stopTolHistFun.
     */
    private double stopTolHistFun;

    // 
    private int mu;

    /**
     * log(mu + 0.5), stored for efficiency.
     */
    private double logMu2;

    /**
     * Array for weighted recombination.
     */
    private RealMatrix weights;

    // 
    private double mueff;

    /**
     * Overall standard deviation - search volume.
     */
    private double sigma;

    /**
     * Cumulation constant.
     */
    private double cc;

    /**
     * Cumulation constant for step-size.
     */
    private double cs;

    /**
     * Damping for step-size.
     */
    private double damps;

    /**
     * Learning rate for rank-one update.
     */
    private double ccov1;

    /**
     * Learning rate for rank-mu update'
     */
    private double ccovmu;

    /**
     * Expectation of ||N(0,I)|| == norm(randn(N,1)).
     */
    private double chiN;

    /**
     * Learning rate for rank-one update - diagonalOnly
     */
    private double ccov1Sep;

    /**
     * Learning rate for rank-mu update - diagonalOnly
     */
    private double ccovmuSep;

    /**
     * Objective variables.
     */
    private RealMatrix xmean;

    /**
     * Evolution path.
     */
    private RealMatrix pc;

    /**
     * Evolution path for sigma.
     */
    private RealMatrix ps;

    /**
     * Norm of ps, stored for efficiency.
     */
    private double normps;

    /**
     * Coordinate system.
     */
    private RealMatrix B;

    /**
     * Scaling.
     */
    private RealMatrix D;

    /**
     * B*D, stored for efficiency.
     */
    private RealMatrix BD;

    /**
     * Diagonal of sqrt(D), stored for efficiency.
     */
    private RealMatrix diagD;

    /**
     * Covariance matrix.
     */
    private RealMatrix C;

    /**
     * Diagonal of C, used for diagonalOnly.
     */
    private RealMatrix diagC;

    /**
     * Number of iterations already performed.
     */
    private int iterations;

    /**
     * History queue of best values.
     */
    private double[] fitnessHistory;

    /**
     * Size of history queue of best values.
     */
    private int historySize;

    /**
     * Random generator.
     */
    private final RandomGenerator random;

    /**
     * History of sigma values.
     */
    private final List<Double> statisticsSigmaHistory = new ArrayList<Double>();

    /**
     * History of mean matrix.
     */
    private final List<RealMatrix> statisticsMeanHistory = new ArrayList<RealMatrix>();

    /**
     * History of fitness values.
     */
    private final List<Double> statisticsFitnessHistory = new ArrayList<Double>();

    /**
     * History of D matrix.
     */
    private final List<RealMatrix> statisticsDHistory = new ArrayList<RealMatrix>();

    /**
     * @param maxIterations Maximal number of iterations.
     * @param stopFitness Whether to stop if objective function value is smaller than
     * {@code stopFitness}.
     * @param isActiveCMA Chooses the covariance matrix update method.
     * @param diagonalOnly Number of initial iterations, where the covariance matrix
     * remains diagonal.
     * @param checkFeasableCount Determines how often new random objective variables are
     * generated in case they are out of bounds.
     * @param random Random generator.
     * @param generateStatistics Whether statistic data is collected.
     * @param checker Convergence checker.
     *
     * @since 3.1
     */
    public CMAESOptimizer(int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        this.maxIterations = maxIterations;
        this.stopFitness = stopFitness;
        this.isActiveCMA = isActiveCMA;
        this.diagonalOnly = diagonalOnly;
        this.checkFeasableCount = checkFeasableCount;
        this.random = random;
        this.generateStatistics = generateStatistics;
    }

    /**
     * @return History of sigma values.
     */
    public List<Double> getStatisticsSigmaHistory() {
        return statisticsSigmaHistory;
    }

    /**
     * @return History of mean matrix.
     */
    public List<RealMatrix> getStatisticsMeanHistory() {
        return statisticsMeanHistory;
    }

    /**
     * @return History of fitness values.
     */
    public List<Double> getStatisticsFitnessHistory() {
        return statisticsFitnessHistory;
    }

    /**
     * @return History of D matrix.
     */
    public List<RealMatrix> getStatisticsDHistory() {
        return statisticsDHistory;
    }

    /**
     * Input sigma values.
     * They define the initial coordinate-wise standard deviations for
     * sampling new search points around the initial guess.
     * It is suggested to set them to the estimated distance from the
     * initial to the desired optimum.
     * Small values induce the search to be more local (and very small
     * values are more likely to find a local optimum close to the initial
     * guess).
     * Too small values might however lead to early termination.
     */
    public static class Sigma implements OptimizationData {

        /**
         * Sigma values.
         */
        private final double[] sigma;

        /**
         * @param s Sigma values.
         * @throws NotPositiveException if any of the array entries is smaller
         * than zero.
         */
        public Sigma(double[] s) throws NotPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.Sigma_291");
            for (int i = 0; ROR_less(i, s.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.Sigma_291", _mut60257, _mut60258, _mut60259, _mut60260, _mut60261); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.Sigma_291");
                if (ROR_less(s[i], 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.Sigma_291", _mut60252, _mut60253, _mut60254, _mut60255, _mut60256)) {
                    throw new NotPositiveException(s[i]);
                }
            }
            sigma = s.clone();
        }

        /**
         * @return the sigma values.
         */
        public double[] getSigma() {
            return sigma.clone();
        }
    }

    /**
     * Population size.
     * The number of offspring is the primary strategy parameter.
     * In the absence of better clues, a good default could be an
     * integer close to {@code 4 + 3 ln(n)}, where {@code n} is the
     * number of optimized parameters.
     * Increasing the population size improves global search properties
     * at the expense of speed (which in general decreases at most
     * linearly with increasing population size).
     */
    public static class PopulationSize implements OptimizationData {

        /**
         * Population size.
         */
        private final int lambda;

        /**
         * @param size Population size.
         * @throws NotStrictlyPositiveException if {@code size <= 0}.
         */
        public PopulationSize(int size) throws NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.PopulationSize_328");
            if (ROR_less_equals(size, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.PopulationSize_328", _mut60262, _mut60263, _mut60264, _mut60265, _mut60266)) {
                throw new NotStrictlyPositiveException(size);
            }
            lambda = size;
        }

        /**
         * @return the population size.
         */
        public int getPopulationSize() {
            return lambda;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param optData Optimization data. In addition to those documented in
     * {@link MultivariateOptimizer#parseOptimizationData(OptimizationData[])
     * MultivariateOptimizer}, this method will register the following data:
     * <ul>
     *  <li>{@link Sigma}</li>
     *  <li>{@link PopulationSize}</li>
     * </ul>
     * @return {@inheritDoc}
     * @throws TooManyEvaluationsException if the maximal number of
     * evaluations is exceeded.
     * @throws DimensionMismatchException if the initial guess, target, and weight
     * arguments have inconsistent dimensions.
     */
    @Override
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        // Set up base class and perform computation.
        return super.optimize(optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
        // -------------------- Initialization --------------------------------
        isMinimize = getGoalType().equals(GoalType.MINIMIZE);
        final FitnessFunction fitfun = new FitnessFunction();
        final double[] guess = getStartPoint();
        // number of objective variables/problem dimension
        dimension = guess.length;
        initializeCMA(guess);
        iterations = 0;
        ValuePenaltyPair valuePenalty = fitfun.value(guess);
        double bestValue = AOR_plus(valuePenalty.value, valuePenalty.penalty, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60267, _mut60268, _mut60269, _mut60270);
        push(fitnessHistory, bestValue);
        PointValuePair optimum = new PointValuePair(getStartPoint(), isMinimize ? bestValue : -bestValue);
        PointValuePair lastResult = null;
        generationLoop: for (iterations = 1; ROR_less_equals(iterations, maxIterations, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60473, _mut60474, _mut60475, _mut60476, _mut60477); iterations++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
            incrementIterationCount();
            // Generate and evaluate lambda offspring
            final RealMatrix arz = randn1(dimension, lambda);
            final RealMatrix arx = zeros(dimension, lambda);
            final double[] fitness = new double[lambda];
            final ValuePenaltyPair[] valuePenaltyPairs = new ValuePenaltyPair[lambda];
            // generate random offspring
            for (int k = 0; ROR_less(k, lambda, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60291, _mut60292, _mut60293, _mut60294, _mut60295); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
                RealMatrix arxk = null;
                for (int i = 0; ROR_less(i, AOR_plus(checkFeasableCount, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60282, _mut60283, _mut60284, _mut60285), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60286, _mut60287, _mut60288, _mut60289, _mut60290); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
                    if (ROR_less_equals(diagonalOnly, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60271, _mut60272, _mut60273, _mut60274, _mut60275)) {
                        arxk = xmean.add(BD.multiply(arz.getColumnMatrix(k)).scalarMultiply(// m + sig * Normal(0,C)
                        sigma));
                    } else {
                        arxk = xmean.add(times(diagD, arz.getColumnMatrix(k)).scalarMultiply(sigma));
                    }
                    if ((_mut60281 ? (ROR_greater_equals(i, checkFeasableCount, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60276, _mut60277, _mut60278, _mut60279, _mut60280) && fitfun.isFeasible(arxk.getColumn(0))) : (ROR_greater_equals(i, checkFeasableCount, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60276, _mut60277, _mut60278, _mut60279, _mut60280) || fitfun.isFeasible(arxk.getColumn(0))))) {
                        break;
                    }
                    // regenerate random arguments for row
                    arz.setColumn(k, randn(dimension));
                }
                copyColumn(arxk, 0, arx, k);
                try {
                    // compute fitness
                    valuePenaltyPairs[k] = fitfun.value(arx.getColumn(k));
                } catch (TooManyEvaluationsException e) {
                    break generationLoop;
                }
            }
            // Compute fitnesses by adding value and penalty after scaling by value range.
            double valueRange = valueRange(valuePenaltyPairs);
            for (int iValue = 0; ROR_less(iValue, valuePenaltyPairs.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60304, _mut60305, _mut60306, _mut60307, _mut60308); iValue++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
                fitness[iValue] = AOR_plus(valuePenaltyPairs[iValue].value, AOR_multiply(valuePenaltyPairs[iValue].penalty, valueRange, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60296, _mut60297, _mut60298, _mut60299), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60300, _mut60301, _mut60302, _mut60303);
            }
            // Sort by fitness and compute weighted mean into xmean
            final int[] arindex = sortedIndices(fitness);
            // for speed up of Eq. (2) and (3)
            final RealMatrix xold = xmean;
            final RealMatrix bestArx = selectColumns(arx, MathArrays.copyOf(arindex, mu));
            xmean = bestArx.multiply(weights);
            final RealMatrix bestArz = selectColumns(arz, MathArrays.copyOf(arindex, mu));
            final RealMatrix zmean = bestArz.multiply(weights);
            final boolean hsig = updateEvolutionPaths(zmean, xold);
            if (ROR_less_equals(diagonalOnly, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60309, _mut60310, _mut60311, _mut60312, _mut60313)) {
                updateCovariance(hsig, bestArx, arz, arindex, xold);
            } else {
                updateCovarianceDiagonalOnly(hsig, bestArz);
            }
            // Adapt step size sigma - Eq. (5)
            sigma *= FastMath.exp(FastMath.min(1, AOR_divide(AOR_multiply((AOR_minus(AOR_divide(normps, chiN, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60314, _mut60315, _mut60316, _mut60317), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60318, _mut60319, _mut60320, _mut60321)), cs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60322, _mut60323, _mut60324, _mut60325), damps, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60326, _mut60327, _mut60328, _mut60329)));
            final double bestFitness = fitness[arindex[0]];
            final double worstFitness = fitness[arindex[AOR_minus(arindex.length, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60330, _mut60331, _mut60332, _mut60333)]];
            if (ROR_greater(bestValue, bestFitness, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60334, _mut60335, _mut60336, _mut60337, _mut60338)) {
                bestValue = bestFitness;
                lastResult = optimum;
                optimum = new PointValuePair(fitfun.repair(bestArx.getColumn(0)), isMinimize ? bestFitness : -bestFitness);
                if ((_mut60340 ? ((_mut60339 ? (getConvergenceChecker() != null || lastResult != null) : (getConvergenceChecker() != null && lastResult != null)) || getConvergenceChecker().converged(iterations, optimum, lastResult)) : ((_mut60339 ? (getConvergenceChecker() != null || lastResult != null) : (getConvergenceChecker() != null && lastResult != null)) && getConvergenceChecker().converged(iterations, optimum, lastResult)))) {
                    break generationLoop;
                }
            }
            // Break, if fitness is good enough
            if ((_mut60351 ? (ROR_not_equals(stopFitness, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60341, _mut60342, _mut60343, _mut60344, _mut60345) || ROR_less(bestFitness, (isMinimize ? stopFitness : -stopFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60346, _mut60347, _mut60348, _mut60349, _mut60350)) : (ROR_not_equals(stopFitness, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60341, _mut60342, _mut60343, _mut60344, _mut60345) && ROR_less(bestFitness, (isMinimize ? stopFitness : -stopFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60346, _mut60347, _mut60348, _mut60349, _mut60350)))) {
                break generationLoop;
            }
            final double[] sqrtDiagC = sqrt(diagC).getColumn(0);
            final double[] pcCol = pc.getColumn(0);
            for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60370, _mut60371, _mut60372, _mut60373, _mut60374); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
                if (ROR_greater(AOR_multiply(sigma, FastMath.max(FastMath.abs(pcCol[i]), sqrtDiagC[i]), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60352, _mut60353, _mut60354, _mut60355), stopTolX, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60356, _mut60357, _mut60358, _mut60359, _mut60360)) {
                    break;
                }
                if (ROR_greater_equals(i, AOR_minus(dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60361, _mut60362, _mut60363, _mut60364), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60365, _mut60366, _mut60367, _mut60368, _mut60369)) {
                    break generationLoop;
                }
            }
            for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60384, _mut60385, _mut60386, _mut60387, _mut60388); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369");
                if (ROR_greater(AOR_multiply(sigma, sqrtDiagC[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60375, _mut60376, _mut60377, _mut60378), stopTolUpX, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60379, _mut60380, _mut60381, _mut60382, _mut60383)) {
                    break generationLoop;
                }
            }
            final double historyBest = min(fitnessHistory);
            final double historyWorst = max(fitnessHistory);
            if ((_mut60403 ? (ROR_greater(iterations, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60389, _mut60390, _mut60391, _mut60392, _mut60393) || ROR_less(AOR_minus(FastMath.max(historyWorst, worstFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60394, _mut60395, _mut60396, _mut60397), stopTolFun, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60398, _mut60399, _mut60400, _mut60401, _mut60402)) : (ROR_greater(iterations, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60389, _mut60390, _mut60391, _mut60392, _mut60393) && ROR_less(AOR_minus(FastMath.max(historyWorst, worstFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60394, _mut60395, _mut60396, _mut60397), stopTolFun, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60398, _mut60399, _mut60400, _mut60401, _mut60402)))) {
                break generationLoop;
            }
            if ((_mut60418 ? (ROR_greater(iterations, fitnessHistory.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60404, _mut60405, _mut60406, _mut60407, _mut60408) || ROR_less(AOR_minus(historyWorst, historyBest, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60409, _mut60410, _mut60411, _mut60412), stopTolHistFun, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60413, _mut60414, _mut60415, _mut60416, _mut60417)) : (ROR_greater(iterations, fitnessHistory.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60404, _mut60405, _mut60406, _mut60407, _mut60408) && ROR_less(AOR_minus(historyWorst, historyBest, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60409, _mut60410, _mut60411, _mut60412), stopTolHistFun, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60413, _mut60414, _mut60415, _mut60416, _mut60417)))) {
                break generationLoop;
            }
            // condition number of the covariance matrix exceeds 1e14
            if (ROR_greater(AOR_divide(max(diagD), min(diagD), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60419, _mut60420, _mut60421, _mut60422), 1e7, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60423, _mut60424, _mut60425, _mut60426, _mut60427)) {
                break generationLoop;
            }
            // user defined termination
            if (getConvergenceChecker() != null) {
                final PointValuePair current = new PointValuePair(bestArx.getColumn(0), isMinimize ? bestFitness : -bestFitness);
                if ((_mut60428 ? (lastResult != null || getConvergenceChecker().converged(iterations, current, lastResult)) : (lastResult != null && getConvergenceChecker().converged(iterations, current, lastResult)))) {
                    break generationLoop;
                }
                lastResult = current;
            }
            // Adjust step size in case of equal function values (flat fitness)
            if (ROR_equals(bestValue, fitness[arindex[(int) (AOR_plus(0.1, AOR_divide(lambda, 4., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60429, _mut60430, _mut60431, _mut60432), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60433, _mut60434, _mut60435, _mut60436))]], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60437, _mut60438, _mut60439, _mut60440, _mut60441)) {
                sigma *= FastMath.exp(AOR_plus(0.2, AOR_divide(cs, damps, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60442, _mut60443, _mut60444, _mut60445), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60446, _mut60447, _mut60448, _mut60449));
            }
            if ((_mut60464 ? (ROR_greater(iterations, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60450, _mut60451, _mut60452, _mut60453, _mut60454) || ROR_equals(AOR_minus(FastMath.max(historyWorst, bestFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60455, _mut60456, _mut60457, _mut60458), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60459, _mut60460, _mut60461, _mut60462, _mut60463)) : (ROR_greater(iterations, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60450, _mut60451, _mut60452, _mut60453, _mut60454) && ROR_equals(AOR_minus(FastMath.max(historyWorst, bestFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60455, _mut60456, _mut60457, _mut60458), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60459, _mut60460, _mut60461, _mut60462, _mut60463)))) {
                sigma *= FastMath.exp(AOR_plus(0.2, AOR_divide(cs, damps, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60465, _mut60466, _mut60467, _mut60468), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize_369", _mut60469, _mut60470, _mut60471, _mut60472));
            }
            // store best in history
            push(fitnessHistory, bestFitness);
            if (generateStatistics) {
                statisticsSigmaHistory.add(sigma);
                statisticsFitnessHistory.add(bestFitness);
                statisticsMeanHistory.add(xmean.transpose());
                statisticsDHistory.add(diagD.transpose().scalarMultiply(1E5));
            }
        }
        return optimum;
    }

    /**
     * Scans the list of (required and optional) optimization data that
     * characterize the problem.
     *
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link Sigma}</li>
     *  <li>{@link PopulationSize}</li>
     * </ul>
     */
    @Override
    protected void parseOptimizationData(OptimizationData... optData) {
        // Allow base class to register its own data.
        super.parseOptimizationData(optData);
        // not provided in the argument list.
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.parseOptimizationData_534");
            if (data instanceof Sigma) {
                inputSigma = ((Sigma) data).getSigma();
                continue;
            }
            if (data instanceof PopulationSize) {
                lambda = ((PopulationSize) data).getPopulationSize();
                continue;
            }
        }
        checkParameters();
    }

    /**
     * Checks dimensions and values of boundaries and inputSigma if defined.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558");
        final double[] init = getStartPoint();
        final double[] lB = getLowerBound();
        final double[] uB = getUpperBound();
        if (inputSigma != null) {
            if (ROR_not_equals(inputSigma.length, init.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558", _mut60478, _mut60479, _mut60480, _mut60481, _mut60482)) {
                throw new DimensionMismatchException(inputSigma.length, init.length);
            }
            for (int i = 0; ROR_less(i, init.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558", _mut60496, _mut60497, _mut60498, _mut60499, _mut60500); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558");
                if (ROR_greater(inputSigma[i], AOR_minus(uB[i], lB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558", _mut60483, _mut60484, _mut60485, _mut60486), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558", _mut60487, _mut60488, _mut60489, _mut60490, _mut60491)) {
                    throw new OutOfRangeException(inputSigma[i], 0, AOR_minus(uB[i], lB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.checkParameters_558", _mut60492, _mut60493, _mut60494, _mut60495));
                }
            }
        }
    }

    /**
     * Initialization of the dynamic search parameters
     *
     * @param guess Initial guess for the arguments of the fitness function.
     */
    private void initializeCMA(double[] guess) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580");
        if (ROR_less_equals(lambda, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60501, _mut60502, _mut60503, _mut60504, _mut60505)) {
            throw new NotStrictlyPositiveException(lambda);
        }
        // initialize sigma
        final double[][] sigmaArray = new double[guess.length][1];
        for (int i = 0; ROR_less(i, guess.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60506, _mut60507, _mut60508, _mut60509, _mut60510); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580");
            sigmaArray[i][0] = inputSigma[i];
        }
        final RealMatrix insigma = new Array2DRowRealMatrix(sigmaArray, false);
        // overall standard deviation
        sigma = max(insigma);
        // initialize termination criteria
        stopTolUpX = AOR_multiply(1e3, max(insigma), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60511, _mut60512, _mut60513, _mut60514);
        stopTolX = AOR_multiply(1e-11, max(insigma), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60515, _mut60516, _mut60517, _mut60518);
        stopTolFun = 1e-12;
        stopTolHistFun = 1e-13;
        // number of parents/points for recombination
        mu = AOR_divide(lambda, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60519, _mut60520, _mut60521, _mut60522);
        logMu2 = FastMath.log(AOR_plus(mu, 0.5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60523, _mut60524, _mut60525, _mut60526));
        weights = log(sequence(1, mu, 1)).scalarMultiply(-1).scalarAdd(logMu2);
        double sumw = 0;
        double sumwq = 0;
        for (int i = 0; ROR_less(i, mu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60531, _mut60532, _mut60533, _mut60534, _mut60535); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580");
            double w = weights.getEntry(i, 0);
            sumw += w;
            sumwq += AOR_multiply(w, w, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60527, _mut60528, _mut60529, _mut60530);
        }
        weights = weights.scalarMultiply(AOR_divide(1, sumw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60536, _mut60537, _mut60538, _mut60539));
        // variance-effectiveness of sum w_i x_i
        mueff = AOR_divide(AOR_multiply(sumw, sumw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60540, _mut60541, _mut60542, _mut60543), sumwq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60544, _mut60545, _mut60546, _mut60547);
        // initialize dynamic strategy parameters and constants
        cc = AOR_divide((AOR_plus(4, AOR_divide(mueff, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60548, _mut60549, _mut60550, _mut60551), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60552, _mut60553, _mut60554, _mut60555)), (AOR_plus(AOR_plus(dimension, 4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60556, _mut60557, _mut60558, _mut60559), AOR_divide(AOR_multiply(2, mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60560, _mut60561, _mut60562, _mut60563), dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60564, _mut60565, _mut60566, _mut60567), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60568, _mut60569, _mut60570, _mut60571)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60572, _mut60573, _mut60574, _mut60575);
        cs = AOR_divide((AOR_plus(mueff, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60576, _mut60577, _mut60578, _mut60579)), (AOR_plus(AOR_plus(dimension, mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60580, _mut60581, _mut60582, _mut60583), 3., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60584, _mut60585, _mut60586, _mut60587)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60588, _mut60589, _mut60590, _mut60591);
        damps = AOR_plus(AOR_multiply((AOR_plus(1, AOR_multiply(2, FastMath.max(0, AOR_minus(FastMath.sqrt(AOR_divide((AOR_minus(mueff, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60592, _mut60593, _mut60594, _mut60595)), (AOR_plus(dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60596, _mut60597, _mut60598, _mut60599)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60600, _mut60601, _mut60602, _mut60603)), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60604, _mut60605, _mut60606, _mut60607)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60608, _mut60609, _mut60610, _mut60611), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60612, _mut60613, _mut60614, _mut60615)), FastMath.max(0.3, AOR_minus(1, AOR_divide(dimension, (AOR_plus(1e-6, maxIterations, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60616, _mut60617, _mut60618, _mut60619)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60620, _mut60621, _mut60622, _mut60623), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60624, _mut60625, _mut60626, _mut60627)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60628, _mut60629, _mut60630, _mut60631), // minor increment
        cs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60632, _mut60633, _mut60634, _mut60635);
        ccov1 = AOR_divide(2, (AOR_plus(AOR_multiply((AOR_plus(dimension, 1.3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60636, _mut60637, _mut60638, _mut60639)), (AOR_plus(dimension, 1.3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60640, _mut60641, _mut60642, _mut60643)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60644, _mut60645, _mut60646, _mut60647), mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60648, _mut60649, _mut60650, _mut60651)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60652, _mut60653, _mut60654, _mut60655);
        ccovmu = FastMath.min(AOR_minus(1, ccov1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60656, _mut60657, _mut60658, _mut60659), AOR_divide(AOR_multiply(2, (AOR_plus(AOR_minus(mueff, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60660, _mut60661, _mut60662, _mut60663), AOR_divide(1, mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60664, _mut60665, _mut60666, _mut60667), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60668, _mut60669, _mut60670, _mut60671)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60672, _mut60673, _mut60674, _mut60675), (AOR_plus(AOR_multiply((AOR_plus(dimension, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60676, _mut60677, _mut60678, _mut60679)), (AOR_plus(dimension, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60680, _mut60681, _mut60682, _mut60683)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60684, _mut60685, _mut60686, _mut60687), mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60688, _mut60689, _mut60690, _mut60691)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60692, _mut60693, _mut60694, _mut60695));
        ccov1Sep = FastMath.min(1, AOR_divide(AOR_multiply(ccov1, (AOR_plus(dimension, 1.5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60696, _mut60697, _mut60698, _mut60699)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60700, _mut60701, _mut60702, _mut60703), 3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60704, _mut60705, _mut60706, _mut60707));
        ccovmuSep = FastMath.min(AOR_minus(1, ccov1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60708, _mut60709, _mut60710, _mut60711), AOR_divide(AOR_multiply(ccovmu, (AOR_plus(dimension, 1.5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60712, _mut60713, _mut60714, _mut60715)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60716, _mut60717, _mut60718, _mut60719), 3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60720, _mut60721, _mut60722, _mut60723));
        chiN = AOR_multiply(FastMath.sqrt(dimension), (AOR_plus(AOR_minus(1, AOR_divide(1, (AOR_multiply((double) 4, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60724, _mut60725, _mut60726, _mut60727)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60728, _mut60729, _mut60730, _mut60731), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60732, _mut60733, _mut60734, _mut60735), AOR_divide(1, (AOR_multiply(AOR_multiply((double) 21, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60736, _mut60737, _mut60738, _mut60739), dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60740, _mut60741, _mut60742, _mut60743)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60744, _mut60745, _mut60746, _mut60747), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60748, _mut60749, _mut60750, _mut60751)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60752, _mut60753, _mut60754, _mut60755);
        // objective variables
        xmean = MatrixUtils.createColumnRealMatrix(guess);
        diagD = insigma.scalarMultiply(AOR_divide(1, sigma, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60756, _mut60757, _mut60758, _mut60759));
        diagC = square(diagD);
        // evolution paths for C and sigma
        pc = zeros(dimension, 1);
        // B defines the coordinate system
        ps = zeros(dimension, 1);
        normps = ps.getFrobeniusNorm();
        B = eye(dimension, dimension);
        // diagonal D defines the scaling
        D = ones(dimension, 1);
        BD = times(B, repmat(diagD.transpose(), dimension, 1));
        // covariance
        C = B.multiply(diag(square(D)).multiply(B.transpose()));
        historySize = AOR_plus(10, (int) (AOR_divide(AOR_multiply(AOR_multiply(3, 10, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60760, _mut60761, _mut60762, _mut60763), dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60764, _mut60765, _mut60766, _mut60767), (double) lambda, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60768, _mut60769, _mut60770, _mut60771)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60772, _mut60773, _mut60774, _mut60775);
        // history of fitness values
        fitnessHistory = new double[historySize];
        for (int i = 0; ROR_less(i, historySize, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580", _mut60776, _mut60777, _mut60778, _mut60779, _mut60780); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.initializeCMA_580");
            fitnessHistory[i] = Double.MAX_VALUE;
        }
    }

    /**
     * Update of the evolution paths ps and pc.
     *
     * @param zmean Weighted row matrix of the gaussian random numbers generating
     * the current offspring.
     * @param xold xmean matrix of the previous generation.
     * @return hsig flag indicating a small correction.
     */
    private boolean updateEvolutionPaths(RealMatrix zmean, RealMatrix xold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654");
        ps = ps.scalarMultiply(AOR_minus(1, cs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60793, _mut60794, _mut60795, _mut60796)).add(B.multiply(zmean).scalarMultiply(FastMath.sqrt(AOR_multiply(AOR_multiply(cs, (AOR_minus(2, cs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60781, _mut60782, _mut60783, _mut60784)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60785, _mut60786, _mut60787, _mut60788), mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60789, _mut60790, _mut60791, _mut60792))));
        normps = ps.getFrobeniusNorm();
        final boolean hsig = ROR_less(AOR_divide(AOR_divide(normps, FastMath.sqrt(AOR_minus(1, FastMath.pow(AOR_minus(1, cs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60797, _mut60798, _mut60799, _mut60800), AOR_multiply(2, iterations, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60801, _mut60802, _mut60803, _mut60804)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60805, _mut60806, _mut60807, _mut60808)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60809, _mut60810, _mut60811, _mut60812), chiN, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60813, _mut60814, _mut60815, _mut60816), AOR_plus(1.4, AOR_divide(2, (AOR_plus((double) dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60817, _mut60818, _mut60819, _mut60820)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60821, _mut60822, _mut60823, _mut60824), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60825, _mut60826, _mut60827, _mut60828), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60829, _mut60830, _mut60831, _mut60832, _mut60833);
        pc = pc.scalarMultiply(AOR_minus(1, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60834, _mut60835, _mut60836, _mut60837));
        if (hsig) {
            pc = pc.add(xmean.subtract(xold).scalarMultiply(AOR_divide(FastMath.sqrt(AOR_multiply(AOR_multiply(cc, (AOR_minus(2, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60838, _mut60839, _mut60840, _mut60841)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60842, _mut60843, _mut60844, _mut60845), mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60846, _mut60847, _mut60848, _mut60849)), sigma, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateEvolutionPaths_654", _mut60850, _mut60851, _mut60852, _mut60853)));
        }
        return hsig;
    }

    /**
     * Update of the covariance matrix C for diagonalOnly > 0
     *
     * @param hsig Flag indicating a small correction.
     * @param bestArz Fitness-sorted matrix of the gaussian random values of the
     * current offspring.
     */
    private void updateCovarianceDiagonalOnly(boolean hsig, final RealMatrix bestArz) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676");
        // minor correction if hsig==false
        double oldFac = hsig ? 0 : AOR_multiply(AOR_multiply(ccov1Sep, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60854, _mut60855, _mut60856, _mut60857), (AOR_minus(2, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60858, _mut60859, _mut60860, _mut60861)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60862, _mut60863, _mut60864, _mut60865);
        oldFac += AOR_minus(AOR_minus(1, ccov1Sep, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60866, _mut60867, _mut60868, _mut60869), ccovmuSep, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60870, _mut60871, _mut60872, _mut60873);
        diagC = // regard old matrix
        diagC.scalarMultiply(oldFac).add(// plus rank one update
        square(pc).scalarMultiply(ccov1Sep)).add(// plus rank mu update
        (times(diagC, square(bestArz).multiply(weights))).scalarMultiply(ccovmuSep));
        // replaces eig(C)
        diagD = sqrt(diagC);
        if ((_mut60884 ? (ROR_greater(diagonalOnly, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60874, _mut60875, _mut60876, _mut60877, _mut60878) || ROR_greater(iterations, diagonalOnly, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60879, _mut60880, _mut60881, _mut60882, _mut60883)) : (ROR_greater(diagonalOnly, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60874, _mut60875, _mut60876, _mut60877, _mut60878) && ROR_greater(iterations, diagonalOnly, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovarianceDiagonalOnly_676", _mut60879, _mut60880, _mut60881, _mut60882, _mut60883)))) {
            // full covariance matrix from now on
            diagonalOnly = 0;
            B = eye(dimension, dimension);
            BD = diag(diagD);
            C = diag(diagC);
        }
    }

    /**
     * Update of the covariance matrix C.
     *
     * @param hsig Flag indicating a small correction.
     * @param bestArx Fitness-sorted matrix of the argument vectors producing the
     * current offspring.
     * @param arz Unsorted matrix containing the gaussian random values of the
     * current offspring.
     * @param arindex Indices indicating the fitness-order of the current offspring.
     * @param xold xmean matrix of the previous generation.
     */
    private void updateCovariance(boolean hsig, final RealMatrix bestArx, final RealMatrix arz, final int[] arindex, final RealMatrix xold) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707");
        double negccov = 0;
        if (ROR_greater(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60885, _mut60886, _mut60887, _mut60888), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60889, _mut60890, _mut60891, _mut60892, _mut60893)) {
            final RealMatrix arpos = bestArx.subtract(repmat(xold, 1, mu)).scalarMultiply(AOR_divide(1, sigma, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60894, _mut60895, _mut60896, _mut60897));
            final RealMatrix roneu = pc.multiply(pc.transpose()).scalarMultiply(// rank one update
            ccov1);
            // minor correction if hsig==false
            double oldFac = hsig ? 0 : AOR_multiply(AOR_multiply(ccov1, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60898, _mut60899, _mut60900, _mut60901), (AOR_minus(2, cc, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60902, _mut60903, _mut60904, _mut60905)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60906, _mut60907, _mut60908, _mut60909);
            oldFac += AOR_minus(AOR_minus(1, ccov1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60910, _mut60911, _mut60912, _mut60913), ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60914, _mut60915, _mut60916, _mut60917);
            if (isActiveCMA) {
                // Adapt covariance matrix C active CMA
                negccov = AOR_divide(AOR_multiply(AOR_multiply((AOR_minus(1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60918, _mut60919, _mut60920, _mut60921)), 0.25, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60922, _mut60923, _mut60924, _mut60925), mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60926, _mut60927, _mut60928, _mut60929), (AOR_plus(FastMath.pow(AOR_plus(dimension, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60930, _mut60931, _mut60932, _mut60933), 1.5), AOR_multiply(2, mueff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60934, _mut60935, _mut60936, _mut60937), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60938, _mut60939, _mut60940, _mut60941)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60942, _mut60943, _mut60944, _mut60945);
                // critical
                final double negminresidualvariance = 0.66;
                // where to make up for the variance loss
                final double negalphaold = 0.5;
                // prepare vectors, compute negative updating matrix Cneg
                final int[] arReverseIndex = reverse(arindex);
                RealMatrix arzneg = selectColumns(arz, MathArrays.copyOf(arReverseIndex, mu));
                RealMatrix arnorms = sqrt(sumRows(square(arzneg)));
                final int[] idxnorms = sortedIndices(arnorms.getRow(0));
                final RealMatrix arnormsSorted = selectColumns(arnorms, idxnorms);
                final int[] idxReverse = reverse(idxnorms);
                final RealMatrix arnormsReverse = selectColumns(arnorms, idxReverse);
                arnorms = divide(arnormsReverse, arnormsSorted);
                final int[] idxInv = inverse(idxnorms);
                final RealMatrix arnormsInv = selectColumns(arnorms, idxInv);
                // check and set learning rate negccov
                final double negcovMax = AOR_divide((AOR_minus(1, negminresidualvariance, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60946, _mut60947, _mut60948, _mut60949)), square(arnormsInv).multiply(weights).getEntry(0, 0), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60950, _mut60951, _mut60952, _mut60953);
                if (ROR_greater(negccov, negcovMax, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60954, _mut60955, _mut60956, _mut60957, _mut60958)) {
                    negccov = negcovMax;
                }
                arzneg = times(arzneg, repmat(arnormsInv, dimension, 1));
                final RealMatrix artmp = BD.multiply(arzneg);
                final RealMatrix Cneg = artmp.multiply(diag(weights)).multiply(artmp.transpose());
                oldFac += AOR_multiply(negalphaold, negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60959, _mut60960, _mut60961, _mut60962);
                C = C.scalarMultiply(oldFac).add(// regard old matrix
                roneu).add(// plus rank one update
                arpos.scalarMultiply(AOR_plus(ccovmu, AOR_multiply((AOR_minus(1, negalphaold, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60963, _mut60964, _mut60965, _mut60966)), negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60967, _mut60968, _mut60969, _mut60970), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateCovariance_707", _mut60971, _mut60972, _mut60973, _mut60974)).multiply(times(repmat(weights, 1, dimension), arpos.transpose()))).subtract(Cneg.scalarMultiply(negccov));
            } else {
                // Adapt covariance matrix C - nonactive
                C = // regard old matrix
                C.scalarMultiply(oldFac).add(// plus rank one update
                roneu).add(// plus rank mu update
                arpos.scalarMultiply(ccovmu).multiply(times(repmat(weights, 1, dimension), arpos.transpose())));
            }
        }
        updateBD(negccov);
    }

    /**
     * Update B and D from C.
     *
     * @param negccov Negative covariance factor.
     */
    private void updateBD(double negccov) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773");
        if ((_mut61017 ? (ROR_greater(AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60975, _mut60976, _mut60977, _mut60978), negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60979, _mut60980, _mut60981, _mut60982), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60983, _mut60984, _mut60985, _mut60986, _mut60987) || ROR_less((AOR_divide(AOR_divide(AOR_divide(AOR_remainder(iterations, 1., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60988, _mut60989, _mut60990, _mut60991), (AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60992, _mut60993, _mut60994, _mut60995), negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60996, _mut60997, _mut60998, _mut60999)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61000, _mut61001, _mut61002, _mut61003), dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61004, _mut61005, _mut61006, _mut61007), 10., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61008, _mut61009, _mut61010, _mut61011)), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61012, _mut61013, _mut61014, _mut61015, _mut61016)) : (ROR_greater(AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60975, _mut60976, _mut60977, _mut60978), negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60979, _mut60980, _mut60981, _mut60982), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60983, _mut60984, _mut60985, _mut60986, _mut60987) && ROR_less((AOR_divide(AOR_divide(AOR_divide(AOR_remainder(iterations, 1., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60988, _mut60989, _mut60990, _mut60991), (AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60992, _mut60993, _mut60994, _mut60995), negccov, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut60996, _mut60997, _mut60998, _mut60999)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61000, _mut61001, _mut61002, _mut61003), dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61004, _mut61005, _mut61006, _mut61007), 10., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61008, _mut61009, _mut61010, _mut61011)), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61012, _mut61013, _mut61014, _mut61015, _mut61016)))) {
            // to achieve O(N^2)
            C = triu(C, 0).add(triu(C, 1).transpose());
            // enforce symmetry to prevent complex numbers
            final EigenDecomposition eig = new EigenDecomposition(C);
            // eigen decomposition, B==normalized eigenvectors
            B = eig.getV();
            D = eig.getD();
            diagD = diag(D);
            if (ROR_less_equals(min(diagD), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61018, _mut61019, _mut61020, _mut61021, _mut61022)) {
                for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61028, _mut61029, _mut61030, _mut61031, _mut61032); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773");
                    if (ROR_less(diagD.getEntry(i, 0), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61023, _mut61024, _mut61025, _mut61026, _mut61027)) {
                        diagD.setEntry(i, 0, 0);
                    }
                }
                final double tfac = AOR_divide(max(diagD), 1e14, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61033, _mut61034, _mut61035, _mut61036);
                C = C.add(eye(dimension, dimension).scalarMultiply(tfac));
                diagD = diagD.add(ones(dimension, 1).scalarMultiply(tfac));
            }
            if (ROR_greater(max(diagD), AOR_multiply(1e14, min(diagD), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61037, _mut61038, _mut61039, _mut61040), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61041, _mut61042, _mut61043, _mut61044, _mut61045)) {
                final double tfac = AOR_minus(AOR_divide(max(diagD), 1e14, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61046, _mut61047, _mut61048, _mut61049), min(diagD), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.updateBD_773", _mut61050, _mut61051, _mut61052, _mut61053);
                C = C.add(eye(dimension, dimension).scalarMultiply(tfac));
                diagD = diagD.add(ones(dimension, 1).scalarMultiply(tfac));
            }
            diagC = diag(C);
            // D contains standard deviations now
            diagD = sqrt(diagD);
            // O(n^2)
            BD = times(B, repmat(diagD.transpose(), dimension, 1));
        }
    }

    /**
     * Pushes the current best fitness value in a history queue.
     *
     * @param vals History queue.
     * @param val Current best fitness value.
     */
    private static void push(double[] vals, double val) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.push_810");
        for (int i = vals.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.push_810", _mut61058, _mut61059, _mut61060, _mut61061, _mut61062); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.push_810");
            vals[i] = vals[AOR_minus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.push_810", _mut61054, _mut61055, _mut61056, _mut61057)];
        }
        vals[0] = val;
    }

    /**
     * Sorts fitness values.
     *
     * @param doubles Array of values to be sorted.
     * @return a sorted array of indices pointing into doubles.
     */
    private int[] sortedIndices(final double[] doubles) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sortedIndices_823");
        final DoubleIndex[] dis = new DoubleIndex[doubles.length];
        for (int i = 0; ROR_less(i, doubles.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sortedIndices_823", _mut61063, _mut61064, _mut61065, _mut61066, _mut61067); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sortedIndices_823");
            dis[i] = new DoubleIndex(doubles[i], i);
        }
        Arrays.sort(dis);
        final int[] indices = new int[doubles.length];
        for (int i = 0; ROR_less(i, doubles.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sortedIndices_823", _mut61068, _mut61069, _mut61070, _mut61071, _mut61072); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sortedIndices_823");
            indices[i] = dis[i].index;
        }
        return indices;
    }

    /**
     * Get range of values.
     *
     * @param vpPairs Array of valuePenaltyPairs to get range from.
     * @return a double equal to maximum value minus minimum value.
     */
    private double valueRange(final ValuePenaltyPair[] vpPairs) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.valueRange_841");
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.MAX_VALUE;
        for (ValuePenaltyPair vpPair : vpPairs) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.valueRange_841");
            if (ROR_greater(vpPair.value, max, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.valueRange_841", _mut61073, _mut61074, _mut61075, _mut61076, _mut61077)) {
                max = vpPair.value;
            }
            if (ROR_less(vpPair.value, min, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.valueRange_841", _mut61078, _mut61079, _mut61080, _mut61081, _mut61082)) {
                min = vpPair.value;
            }
        }
        return AOR_minus(max, min, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.valueRange_841", _mut61083, _mut61084, _mut61085, _mut61086);
    }

    /**
     * Used to sort fitness values. Sorting is always in lower value first
     * order.
     */
    private static class DoubleIndex implements Comparable<DoubleIndex> {

        /**
         * Value to compare.
         */
        private final double value;

        /**
         * Index into sorted array.
         */
        private final int index;

        /**
         * @param value Value to compare.
         * @param index Index into sorted array.
         */
        DoubleIndex(double value, int index) {
            this.value = value;
            this.index = index;
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(DoubleIndex o) {
            return Double.compare(value, o.value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object other) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.equals_880");
            if (this == other) {
                return true;
            }
            if (other instanceof DoubleIndex) {
                return ROR_equals(Double.compare(value, ((DoubleIndex) other).value), 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.equals_880", _mut61087, _mut61088, _mut61089, _mut61090, _mut61091);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            long bits = Double.doubleToLongBits(value);
            return (int) ((1438542 ^ (bits >>> 32) ^ bits) & 0xffffffff);
        }
    }

    /**
     * Stores the value and penalty (for repair of out of bounds point).
     */
    private static class ValuePenaltyPair {

        /**
         * Objective function value.
         */
        private double value;

        /**
         * Penalty value for repair of out out of bounds points.
         */
        private double penalty;

        /**
         * @param value Function value.
         * @param penalty Out-of-bounds penalty.
         */
        ValuePenaltyPair(final double value, final double penalty) {
            this.value = value;
            this.penalty = penalty;
        }
    }

    /**
     * Normalizes fitness values to the range [0,1]. Adds a penalty to the
     * fitness value if out of range.
     */
    private class FitnessFunction {

        /**
         * Flag indicating whether the objective variables are forced into their
         * bounds if defined
         */
        private final boolean isRepairMode;

        /**
         * Simple constructor.
         */
        FitnessFunction() {
            isRepairMode = true;
        }

        /**
         * @param point Normalized objective variables.
         * @return the objective value + penalty for violated bounds.
         */
        public ValuePenaltyPair value(final double[] point) {
            double value;
            double penalty = 0.0;
            if (isRepairMode) {
                double[] repaired = repair(point);
                value = CMAESOptimizer.this.computeObjectiveValue(repaired);
                penalty = penalty(point, repaired);
            } else {
                value = CMAESOptimizer.this.computeObjectiveValue(point);
            }
            value = isMinimize ? value : -value;
            penalty = isMinimize ? penalty : -penalty;
            return new ValuePenaltyPair(value, penalty);
        }

        /**
         * @param x Normalized objective variables.
         * @return {@code true} if in bounds.
         */
        public boolean isFeasible(final double[] x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.isFeasible_961");
            final double[] lB = CMAESOptimizer.this.getLowerBound();
            final double[] uB = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.isFeasible_961", _mut61102, _mut61103, _mut61104, _mut61105, _mut61106); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.isFeasible_961");
                if (ROR_less(x[i], lB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.isFeasible_961", _mut61092, _mut61093, _mut61094, _mut61095, _mut61096)) {
                    return false;
                }
                if (ROR_greater(x[i], uB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.isFeasible_961", _mut61097, _mut61098, _mut61099, _mut61100, _mut61101)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * @param x Normalized objective variables.
         * @return the repaired (i.e. all in bounds) objective variables.
         */
        private double[] repair(final double[] x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repair_980");
            final double[] lB = CMAESOptimizer.this.getLowerBound();
            final double[] uB = CMAESOptimizer.this.getUpperBound();
            final double[] repaired = new double[x.length];
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repair_980", _mut61117, _mut61118, _mut61119, _mut61120, _mut61121); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repair_980");
                if (ROR_less(x[i], lB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repair_980", _mut61107, _mut61108, _mut61109, _mut61110, _mut61111)) {
                    repaired[i] = lB[i];
                } else if (ROR_greater(x[i], uB[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repair_980", _mut61112, _mut61113, _mut61114, _mut61115, _mut61116)) {
                    repaired[i] = uB[i];
                } else {
                    repaired[i] = x[i];
                }
            }
            return repaired;
        }

        /**
         * @param x Normalized objective variables.
         * @param repaired Repaired objective variables.
         * @return Penalty value according to the violation of the bounds.
         */
        private double penalty(final double[] x, final double[] repaired) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.penalty_1002");
            double penalty = 0;
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.penalty_1002", _mut61126, _mut61127, _mut61128, _mut61129, _mut61130); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.penalty_1002");
                double diff = FastMath.abs(AOR_minus(x[i], repaired[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.penalty_1002", _mut61122, _mut61123, _mut61124, _mut61125));
                penalty += diff;
            }
            return isMinimize ? penalty : -penalty;
        }
    }

    /**
     * @param m Input matrix
     * @return Matrix representing the element-wise logarithm of m.
     */
    private static RealMatrix log(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.log_1018");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.log_1018", _mut61136, _mut61137, _mut61138, _mut61139, _mut61140); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.log_1018");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.log_1018", _mut61131, _mut61132, _mut61133, _mut61134, _mut61135); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.log_1018");
                d[r][c] = FastMath.log(m.getEntry(r, c));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return Matrix representing the element-wise square root of m.
     */
    private static RealMatrix sqrt(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sqrt_1032");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sqrt_1032", _mut61146, _mut61147, _mut61148, _mut61149, _mut61150); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sqrt_1032");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sqrt_1032", _mut61141, _mut61142, _mut61143, _mut61144, _mut61145); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sqrt_1032");
                d[r][c] = FastMath.sqrt(m.getEntry(r, c));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return Matrix representing the element-wise square of m.
     */
    private static RealMatrix square(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046", _mut61160, _mut61161, _mut61162, _mut61163, _mut61164); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046", _mut61155, _mut61156, _mut61157, _mut61158, _mut61159); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046");
                double e = m.getEntry(r, c);
                d[r][c] = AOR_multiply(e, e, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.square_1046", _mut61151, _mut61152, _mut61153, _mut61154);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix 1.
     * @param n Input matrix 2.
     * @return the matrix where the elements of m and n are element-wise multiplied.
     */
    private static RealMatrix times(final RealMatrix m, final RealMatrix n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062", _mut61174, _mut61175, _mut61176, _mut61177, _mut61178); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062", _mut61169, _mut61170, _mut61171, _mut61172, _mut61173); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062");
                d[r][c] = AOR_multiply(m.getEntry(r, c), n.getEntry(r, c), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.times_1062", _mut61165, _mut61166, _mut61167, _mut61168);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix 1.
     * @param n Input matrix 2.
     * @return Matrix where the elements of m and n are element-wise divided.
     */
    private static RealMatrix divide(final RealMatrix m, final RealMatrix n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077", _mut61188, _mut61189, _mut61190, _mut61191, _mut61192); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077", _mut61183, _mut61184, _mut61185, _mut61186, _mut61187); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077");
                d[r][c] = AOR_divide(m.getEntry(r, c), n.getEntry(r, c), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.divide_1077", _mut61179, _mut61180, _mut61181, _mut61182);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @param cols Columns to select.
     * @return Matrix representing the selected columns.
     */
    private static RealMatrix selectColumns(final RealMatrix m, final int[] cols) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.selectColumns_1092");
        final double[][] d = new double[m.getRowDimension()][cols.length];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.selectColumns_1092", _mut61198, _mut61199, _mut61200, _mut61201, _mut61202); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.selectColumns_1092");
            for (int c = 0; ROR_less(c, cols.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.selectColumns_1092", _mut61193, _mut61194, _mut61195, _mut61196, _mut61197); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.selectColumns_1092");
                d[r][c] = m.getEntry(r, cols[c]);
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @param k Diagonal position.
     * @return Upper triangular part of matrix.
     */
    private static RealMatrix triu(final RealMatrix m, int k) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107", _mut61217, _mut61218, _mut61219, _mut61220, _mut61221); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107", _mut61212, _mut61213, _mut61214, _mut61215, _mut61216); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107");
                d[r][c] = ROR_less_equals(r, AOR_minus(c, k, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107", _mut61203, _mut61204, _mut61205, _mut61206), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.triu_1107", _mut61207, _mut61208, _mut61209, _mut61210, _mut61211) ? m.getEntry(r, c) : 0;
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return Row matrix representing the sums of the rows.
     */
    private static RealMatrix sumRows(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sumRows_1121");
        final double[][] d = new double[1][m.getColumnDimension()];
        for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sumRows_1121", _mut61227, _mut61228, _mut61229, _mut61230, _mut61231); c++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sumRows_1121");
            double sum = 0;
            for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sumRows_1121", _mut61222, _mut61223, _mut61224, _mut61225, _mut61226); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sumRows_1121");
                sum += m.getEntry(r, c);
            }
            d[0][c] = sum;
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return the diagonal n-by-n matrix if m is a column matrix or the column
     * matrix representing the diagonal if m is a n-by-n matrix.
     */
    private static RealMatrix diag(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138");
        if (ROR_equals(m.getColumnDimension(), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138", _mut61232, _mut61233, _mut61234, _mut61235, _mut61236)) {
            final double[][] d = new double[m.getRowDimension()][m.getRowDimension()];
            for (int i = 0; ROR_less(i, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138", _mut61242, _mut61243, _mut61244, _mut61245, _mut61246); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138");
                d[i][i] = m.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(d, false);
        } else {
            final double[][] d = new double[m.getRowDimension()][1];
            for (int i = 0; ROR_less(i, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138", _mut61237, _mut61238, _mut61239, _mut61240, _mut61241); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.diag_1138");
                d[i][0] = m.getEntry(i, i);
            }
            return new Array2DRowRealMatrix(d, false);
        }
    }

    /**
     * Copies a column from m1 to m2.
     *
     * @param m1 Source matrix.
     * @param col1 Source column.
     * @param m2 Target matrix.
     * @param col2 Target column.
     */
    private static void copyColumn(final RealMatrix m1, int col1, RealMatrix m2, int col2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.copyColumn_1162");
        for (int i = 0; ROR_less(i, m1.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.copyColumn_1162", _mut61247, _mut61248, _mut61249, _mut61250, _mut61251); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.copyColumn_1162");
            m2.setEntry(i, col2, m1.getEntry(i, col1));
        }
    }

    /**
     * @param n Number of rows.
     * @param m Number of columns.
     * @return n-by-m matrix filled with 1.
     */
    private static RealMatrix ones(int n, int m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.ones_1174");
        final double[][] d = new double[n][m];
        for (int r = 0; ROR_less(r, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.ones_1174", _mut61252, _mut61253, _mut61254, _mut61255, _mut61256); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.ones_1174");
            Arrays.fill(d[r], 1);
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param n Number of rows.
     * @param m Number of columns.
     * @return n-by-m matrix of 0 values out of diagonal, and 1 values on
     * the diagonal.
     */
    private static RealMatrix eye(int n, int m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.eye_1188");
        final double[][] d = new double[n][m];
        for (int r = 0; ROR_less(r, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.eye_1188", _mut61262, _mut61263, _mut61264, _mut61265, _mut61266); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.eye_1188");
            if (ROR_less(r, m, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.eye_1188", _mut61257, _mut61258, _mut61259, _mut61260, _mut61261)) {
                d[r][r] = 1;
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param n Number of rows.
     * @param m Number of columns.
     * @return n-by-m matrix of zero values.
     */
    private static RealMatrix zeros(int n, int m) {
        return new Array2DRowRealMatrix(n, m);
    }

    /**
     * @param mat Input matrix.
     * @param n Number of row replicates.
     * @param m Number of column replicates.
     * @return a matrix which replicates the input matrix in both directions.
     */
    private static RealMatrix repmat(final RealMatrix mat, int n, int m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213");
        final int rd = mat.getRowDimension();
        final int cd = mat.getColumnDimension();
        final double[][] d = new double[AOR_multiply(n, rd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61267, _mut61268, _mut61269, _mut61270)][AOR_multiply(m, cd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61271, _mut61272, _mut61273, _mut61274)];
        for (int r = 0; ROR_less(r, AOR_multiply(n, rd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61292, _mut61293, _mut61294, _mut61295), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61296, _mut61297, _mut61298, _mut61299, _mut61300); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213");
            for (int c = 0; ROR_less(c, AOR_multiply(m, cd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61283, _mut61284, _mut61285, _mut61286), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61287, _mut61288, _mut61289, _mut61290, _mut61291); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213");
                d[r][c] = mat.getEntry(AOR_remainder(r, rd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61275, _mut61276, _mut61277, _mut61278), AOR_remainder(c, cd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.repmat_1213", _mut61279, _mut61280, _mut61281, _mut61282));
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param start Start value.
     * @param end End value.
     * @param step Step size.
     * @return a sequence as column matrix.
     */
    private static RealMatrix sequence(double start, double end, double step) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231");
        final int size = (int) (AOR_plus(AOR_divide((AOR_minus(end, start, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231", _mut61301, _mut61302, _mut61303, _mut61304)), step, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231", _mut61305, _mut61306, _mut61307, _mut61308), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231", _mut61309, _mut61310, _mut61311, _mut61312));
        final double[][] d = new double[size][1];
        double value = start;
        for (int r = 0; ROR_less(r, size, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231", _mut61313, _mut61314, _mut61315, _mut61316, _mut61317); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.sequence_1231");
            d[r][0] = value;
            value += step;
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return the maximum of the matrix element values.
     */
    private static double max(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246");
        double max = -Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246", _mut61328, _mut61329, _mut61330, _mut61331, _mut61332); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246", _mut61323, _mut61324, _mut61325, _mut61326, _mut61327); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246");
                double e = m.getEntry(r, c);
                if (ROR_less(max, e, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1246", _mut61318, _mut61319, _mut61320, _mut61321, _mut61322)) {
                    max = e;
                }
            }
        }
        return max;
    }

    /**
     * @param m Input matrix.
     * @return the minimum of the matrix element values.
     */
    private static double min(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263");
        double min = Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263", _mut61343, _mut61344, _mut61345, _mut61346, _mut61347); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263", _mut61338, _mut61339, _mut61340, _mut61341, _mut61342); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263");
                double e = m.getEntry(r, c);
                if (ROR_greater(min, e, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1263", _mut61333, _mut61334, _mut61335, _mut61336, _mut61337)) {
                    min = e;
                }
            }
        }
        return min;
    }

    /**
     * @param m Input array.
     * @return the maximum of the array values.
     */
    private static double max(final double[] m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1280");
        double max = -Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1280", _mut61353, _mut61354, _mut61355, _mut61356, _mut61357); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1280");
            if (ROR_less(max, m[r], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.max_1280", _mut61348, _mut61349, _mut61350, _mut61351, _mut61352)) {
                max = m[r];
            }
        }
        return max;
    }

    /**
     * @param m Input array.
     * @return the minimum of the array values.
     */
    private static double min(final double[] m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1294");
        double min = Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1294", _mut61363, _mut61364, _mut61365, _mut61366, _mut61367); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1294");
            if (ROR_greater(min, m[r], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.min_1294", _mut61358, _mut61359, _mut61360, _mut61361, _mut61362)) {
                min = m[r];
            }
        }
        return min;
    }

    /**
     * @param indices Input index array.
     * @return the inverse of the mapping defined by indices.
     */
    private static int[] inverse(final int[] indices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.inverse_1308");
        final int[] inverse = new int[indices.length];
        for (int i = 0; ROR_less(i, indices.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.inverse_1308", _mut61368, _mut61369, _mut61370, _mut61371, _mut61372); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.inverse_1308");
            inverse[indices[i]] = i;
        }
        return inverse;
    }

    /**
     * @param indices Input index array.
     * @return the indices in inverse order (last is first).
     */
    private static int[] reverse(final int[] indices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.reverse_1320");
        final int[] reverse = new int[indices.length];
        for (int i = 0; ROR_less(i, indices.length, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.reverse_1320", _mut61381, _mut61382, _mut61383, _mut61384, _mut61385); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.reverse_1320");
            reverse[i] = indices[AOR_minus(AOR_minus(indices.length, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.reverse_1320", _mut61373, _mut61374, _mut61375, _mut61376), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.reverse_1320", _mut61377, _mut61378, _mut61379, _mut61380)];
        }
        return reverse;
    }

    /**
     * @param size Length of random array.
     * @return an array of Gaussian random numbers.
     */
    private double[] randn(int size) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn_1332");
        final double[] randn = new double[size];
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn_1332", _mut61386, _mut61387, _mut61388, _mut61389, _mut61390); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn_1332");
            randn[i] = random.nextGaussian();
        }
        return randn;
    }

    /**
     * @param size Number of rows.
     * @param popSize Population size.
     * @return a 2-dimensional matrix of Gaussian random numbers.
     */
    private RealMatrix randn1(int size, int popSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn1_1345");
        final double[][] d = new double[size][popSize];
        for (int r = 0; ROR_less(r, size, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn1_1345", _mut61396, _mut61397, _mut61398, _mut61399, _mut61400); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn1_1345");
            for (int c = 0; ROR_less(c, popSize, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn1_1345", _mut61391, _mut61392, _mut61393, _mut61394, _mut61395); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.randn1_1345");
                d[r][c] = random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }
}
