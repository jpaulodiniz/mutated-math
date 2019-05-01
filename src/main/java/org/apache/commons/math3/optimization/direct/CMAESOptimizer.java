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
package org.apache.commons.math3.optimization.direct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>An implementation of the active Covariance Matrix Adaptation Evolution Strategy (CMA-ES)
 * for non-linear, non-convex, non-smooth, global function minimization.
 * The CMA-Evolution Strategy (CMA-ES) is a reliable stochastic optimization method
 * which should be applied if derivative-based methods, e.g. quasi-Newton BFGS or
 * conjugate gradient, fail due to a rugged search landscape (e.g. noise, local
 * optima, outlier, etc.) of the objective function. Like a
 * quasi-Newton method, the CMA-ES learns and applies a variable metric
 * on the underlying search space. Unlike a quasi-Newton method, the
 * CMA-ES neither estimates nor uses gradients, making it considerably more
 * reliable in terms of finding a good, or even close to optimal, solution.</p>
 *
 * <p>In general, on smooth objective functions the CMA-ES is roughly ten times
 * slower than BFGS (counting objective function evaluations, no gradients provided).
 * For up to <math>N=10</math> variables also the derivative-free simplex
 * direct search method (Nelder and Mead) can be faster, but it is
 * far less reliable than CMA-ES.</p>
 *
 * <p>The CMA-ES is particularly well suited for non-separable
 * and/or badly conditioned problems. To observe the advantage of CMA compared
 * to a conventional evolution strategy, it will usually take about
 * <math>30 N</math> function evaluations. On difficult problems the complete
 * optimization (a single run) is expected to take <em>roughly</em> between
 * <math>30 N</math> and <math>300 N<sup>2</sup></math>
 * function evaluations.</p>
 *
 * <p>This implementation is translated and adapted from the Matlab version
 * of the CMA-ES algorithm as implemented in module {@code cmaes.m} version 3.51.</p>
 *
 * For more information, please refer to the following links:
 * <ul>
 *  <li><a href="http://www.lri.fr/~hansen/cmaes.m">Matlab code</a></li>
 *  <li><a href="http://www.lri.fr/~hansen/cmaesintro.html">Introduction to CMA-ES</a></li>
 *  <li><a href="http://en.wikipedia.org/wiki/CMA-ES">Wikipedia</a></li>
 * </ul>
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class CMAESOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {

    @Conditional
    public static boolean _mut73306 = false, _mut73307 = false, _mut73308 = false, _mut73309 = false, _mut73310 = false, _mut73311 = false, _mut73312 = false, _mut73313 = false, _mut73314 = false, _mut73315 = false, _mut73316 = false, _mut73317 = false, _mut73318 = false, _mut73319 = false, _mut73320 = false, _mut73321 = false, _mut73322 = false, _mut73323 = false, _mut73324 = false, _mut73325 = false, _mut73326 = false, _mut73327 = false, _mut73328 = false, _mut73329 = false, _mut73330 = false, _mut73331 = false, _mut73332 = false, _mut73333 = false, _mut73334 = false, _mut73335 = false, _mut73336 = false, _mut73337 = false, _mut73338 = false, _mut73339 = false, _mut73340 = false, _mut73341 = false, _mut73342 = false, _mut73343 = false, _mut73344 = false, _mut73345 = false, _mut73346 = false, _mut73347 = false, _mut73348 = false, _mut73349 = false, _mut73350 = false, _mut73351 = false, _mut73352 = false, _mut73353 = false, _mut73354 = false, _mut73355 = false, _mut73356 = false, _mut73357 = false, _mut73358 = false, _mut73359 = false, _mut73360 = false, _mut73361 = false, _mut73362 = false, _mut73363 = false, _mut73364 = false, _mut73365 = false, _mut73366 = false, _mut73367 = false, _mut73368 = false, _mut73369 = false, _mut73370 = false, _mut73371 = false, _mut73372 = false, _mut73373 = false, _mut73374 = false, _mut73375 = false, _mut73376 = false, _mut73377 = false, _mut73378 = false, _mut73379 = false, _mut73380 = false, _mut73381 = false, _mut73382 = false, _mut73383 = false, _mut73384 = false, _mut73385 = false, _mut73386 = false, _mut73387 = false, _mut73388 = false, _mut73389 = false, _mut73390 = false, _mut73391 = false, _mut73392 = false, _mut73393 = false, _mut73394 = false, _mut73395 = false, _mut73396 = false, _mut73397 = false, _mut73398 = false, _mut73399 = false, _mut73400 = false, _mut73401 = false, _mut73402 = false, _mut73403 = false, _mut73404 = false, _mut73405 = false, _mut73406 = false, _mut73407 = false, _mut73408 = false, _mut73409 = false, _mut73410 = false, _mut73411 = false, _mut73412 = false, _mut73413 = false, _mut73414 = false, _mut73415 = false, _mut73416 = false, _mut73417 = false, _mut73418 = false, _mut73419 = false, _mut73420 = false, _mut73421 = false, _mut73422 = false, _mut73423 = false, _mut73424 = false, _mut73425 = false, _mut73426 = false, _mut73427 = false, _mut73428 = false, _mut73429 = false, _mut73430 = false, _mut73431 = false, _mut73432 = false, _mut73433 = false, _mut73434 = false, _mut73435 = false, _mut73436 = false, _mut73437 = false, _mut73438 = false, _mut73439 = false, _mut73440 = false, _mut73441 = false, _mut73442 = false, _mut73443 = false, _mut73444 = false, _mut73445 = false, _mut73446 = false, _mut73447 = false, _mut73448 = false, _mut73449 = false, _mut73450 = false, _mut73451 = false, _mut73452 = false, _mut73453 = false, _mut73454 = false, _mut73455 = false, _mut73456 = false, _mut73457 = false, _mut73458 = false, _mut73459 = false, _mut73460 = false, _mut73461 = false, _mut73462 = false, _mut73463 = false, _mut73464 = false, _mut73465 = false, _mut73466 = false, _mut73467 = false, _mut73468 = false, _mut73469 = false, _mut73470 = false, _mut73471 = false, _mut73472 = false, _mut73473 = false, _mut73474 = false, _mut73475 = false, _mut73476 = false, _mut73477 = false, _mut73478 = false, _mut73479 = false, _mut73480 = false, _mut73481 = false, _mut73482 = false, _mut73483 = false, _mut73484 = false, _mut73485 = false, _mut73486 = false, _mut73487 = false, _mut73488 = false, _mut73489 = false, _mut73490 = false, _mut73491 = false, _mut73492 = false, _mut73493 = false, _mut73494 = false, _mut73495 = false, _mut73496 = false, _mut73497 = false, _mut73498 = false, _mut73499 = false, _mut73500 = false, _mut73501 = false, _mut73502 = false, _mut73503 = false, _mut73504 = false, _mut73505 = false, _mut73506 = false, _mut73507 = false, _mut73508 = false, _mut73509 = false, _mut73510 = false, _mut73511 = false, _mut73512 = false, _mut73513 = false, _mut73514 = false, _mut73515 = false, _mut73516 = false, _mut73517 = false, _mut73518 = false, _mut73519 = false, _mut73520 = false, _mut73521 = false, _mut73522 = false, _mut73523 = false, _mut73524 = false, _mut73525 = false, _mut73526 = false, _mut73527 = false, _mut73528 = false, _mut73529 = false, _mut73530 = false, _mut73531 = false, _mut73532 = false, _mut73533 = false, _mut73534 = false, _mut73535 = false, _mut73536 = false, _mut73537 = false, _mut73538 = false, _mut73539 = false, _mut73540 = false, _mut73541 = false, _mut73542 = false, _mut73543 = false, _mut73544 = false, _mut73545 = false, _mut73546 = false, _mut73547 = false, _mut73548 = false, _mut73549 = false, _mut73550 = false, _mut73551 = false, _mut73552 = false, _mut73553 = false, _mut73554 = false, _mut73555 = false, _mut73556 = false, _mut73557 = false, _mut73558 = false, _mut73559 = false, _mut73560 = false, _mut73561 = false, _mut73562 = false, _mut73563 = false, _mut73564 = false, _mut73565 = false, _mut73566 = false, _mut73567 = false, _mut73568 = false, _mut73569 = false, _mut73570 = false, _mut73571 = false, _mut73572 = false, _mut73573 = false, _mut73574 = false, _mut73575 = false, _mut73576 = false, _mut73577 = false, _mut73578 = false, _mut73579 = false, _mut73580 = false, _mut73581 = false, _mut73582 = false, _mut73583 = false, _mut73584 = false, _mut73585 = false, _mut73586 = false, _mut73587 = false, _mut73588 = false, _mut73589 = false, _mut73590 = false, _mut73591 = false, _mut73592 = false, _mut73593 = false, _mut73594 = false, _mut73595 = false, _mut73596 = false, _mut73597 = false, _mut73598 = false, _mut73599 = false, _mut73600 = false, _mut73601 = false, _mut73602 = false, _mut73603 = false, _mut73604 = false, _mut73605 = false, _mut73606 = false, _mut73607 = false, _mut73608 = false, _mut73609 = false, _mut73610 = false, _mut73611 = false, _mut73612 = false, _mut73613 = false, _mut73614 = false, _mut73615 = false, _mut73616 = false, _mut73617 = false, _mut73618 = false, _mut73619 = false, _mut73620 = false, _mut73621 = false, _mut73622 = false, _mut73623 = false, _mut73624 = false, _mut73625 = false, _mut73626 = false, _mut73627 = false, _mut73628 = false, _mut73629 = false, _mut73630 = false, _mut73631 = false, _mut73632 = false, _mut73633 = false, _mut73634 = false, _mut73635 = false, _mut73636 = false, _mut73637 = false, _mut73638 = false, _mut73639 = false, _mut73640 = false, _mut73641 = false, _mut73642 = false, _mut73643 = false, _mut73644 = false, _mut73645 = false, _mut73646 = false, _mut73647 = false, _mut73648 = false, _mut73649 = false, _mut73650 = false, _mut73651 = false, _mut73652 = false, _mut73653 = false, _mut73654 = false, _mut73655 = false, _mut73656 = false, _mut73657 = false, _mut73658 = false, _mut73659 = false, _mut73660 = false, _mut73661 = false, _mut73662 = false, _mut73663 = false, _mut73664 = false, _mut73665 = false, _mut73666 = false, _mut73667 = false, _mut73668 = false, _mut73669 = false, _mut73670 = false, _mut73671 = false, _mut73672 = false, _mut73673 = false, _mut73674 = false, _mut73675 = false, _mut73676 = false, _mut73677 = false, _mut73678 = false, _mut73679 = false, _mut73680 = false, _mut73681 = false, _mut73682 = false, _mut73683 = false, _mut73684 = false, _mut73685 = false, _mut73686 = false, _mut73687 = false, _mut73688 = false, _mut73689 = false, _mut73690 = false, _mut73691 = false, _mut73692 = false, _mut73693 = false, _mut73694 = false, _mut73695 = false, _mut73696 = false, _mut73697 = false, _mut73698 = false, _mut73699 = false, _mut73700 = false, _mut73701 = false, _mut73702 = false, _mut73703 = false, _mut73704 = false, _mut73705 = false, _mut73706 = false, _mut73707 = false, _mut73708 = false, _mut73709 = false, _mut73710 = false, _mut73711 = false, _mut73712 = false, _mut73713 = false, _mut73714 = false, _mut73715 = false, _mut73716 = false, _mut73717 = false, _mut73718 = false, _mut73719 = false, _mut73720 = false, _mut73721 = false, _mut73722 = false, _mut73723 = false, _mut73724 = false, _mut73725 = false, _mut73726 = false, _mut73727 = false, _mut73728 = false, _mut73729 = false, _mut73730 = false, _mut73731 = false, _mut73732 = false, _mut73733 = false, _mut73734 = false, _mut73735 = false, _mut73736 = false, _mut73737 = false, _mut73738 = false, _mut73739 = false, _mut73740 = false, _mut73741 = false, _mut73742 = false, _mut73743 = false, _mut73744 = false, _mut73745 = false, _mut73746 = false, _mut73747 = false, _mut73748 = false, _mut73749 = false, _mut73750 = false, _mut73751 = false, _mut73752 = false, _mut73753 = false, _mut73754 = false, _mut73755 = false, _mut73756 = false, _mut73757 = false, _mut73758 = false, _mut73759 = false, _mut73760 = false, _mut73761 = false, _mut73762 = false, _mut73763 = false, _mut73764 = false, _mut73765 = false, _mut73766 = false, _mut73767 = false, _mut73768 = false, _mut73769 = false, _mut73770 = false, _mut73771 = false, _mut73772 = false, _mut73773 = false, _mut73774 = false, _mut73775 = false, _mut73776 = false, _mut73777 = false, _mut73778 = false, _mut73779 = false, _mut73780 = false, _mut73781 = false, _mut73782 = false, _mut73783 = false, _mut73784 = false, _mut73785 = false, _mut73786 = false, _mut73787 = false, _mut73788 = false, _mut73789 = false, _mut73790 = false, _mut73791 = false, _mut73792 = false, _mut73793 = false, _mut73794 = false, _mut73795 = false, _mut73796 = false, _mut73797 = false, _mut73798 = false, _mut73799 = false, _mut73800 = false, _mut73801 = false, _mut73802 = false, _mut73803 = false, _mut73804 = false, _mut73805 = false, _mut73806 = false, _mut73807 = false, _mut73808 = false, _mut73809 = false, _mut73810 = false, _mut73811 = false, _mut73812 = false, _mut73813 = false, _mut73814 = false, _mut73815 = false, _mut73816 = false, _mut73817 = false, _mut73818 = false, _mut73819 = false, _mut73820 = false, _mut73821 = false, _mut73822 = false, _mut73823 = false, _mut73824 = false, _mut73825 = false, _mut73826 = false, _mut73827 = false, _mut73828 = false, _mut73829 = false, _mut73830 = false, _mut73831 = false, _mut73832 = false, _mut73833 = false, _mut73834 = false, _mut73835 = false, _mut73836 = false, _mut73837 = false, _mut73838 = false, _mut73839 = false, _mut73840 = false, _mut73841 = false, _mut73842 = false, _mut73843 = false, _mut73844 = false, _mut73845 = false, _mut73846 = false, _mut73847 = false, _mut73848 = false, _mut73849 = false, _mut73850 = false, _mut73851 = false, _mut73852 = false, _mut73853 = false, _mut73854 = false, _mut73855 = false, _mut73856 = false, _mut73857 = false, _mut73858 = false, _mut73859 = false, _mut73860 = false, _mut73861 = false, _mut73862 = false, _mut73863 = false, _mut73864 = false, _mut73865 = false, _mut73866 = false, _mut73867 = false, _mut73868 = false, _mut73869 = false, _mut73870 = false, _mut73871 = false, _mut73872 = false, _mut73873 = false, _mut73874 = false, _mut73875 = false, _mut73876 = false, _mut73877 = false, _mut73878 = false, _mut73879 = false, _mut73880 = false, _mut73881 = false, _mut73882 = false, _mut73883 = false, _mut73884 = false, _mut73885 = false, _mut73886 = false, _mut73887 = false, _mut73888 = false, _mut73889 = false, _mut73890 = false, _mut73891 = false, _mut73892 = false, _mut73893 = false, _mut73894 = false, _mut73895 = false, _mut73896 = false, _mut73897 = false, _mut73898 = false, _mut73899 = false, _mut73900 = false, _mut73901 = false, _mut73902 = false, _mut73903 = false, _mut73904 = false, _mut73905 = false, _mut73906 = false, _mut73907 = false, _mut73908 = false, _mut73909 = false, _mut73910 = false, _mut73911 = false, _mut73912 = false, _mut73913 = false, _mut73914 = false, _mut73915 = false, _mut73916 = false, _mut73917 = false, _mut73918 = false, _mut73919 = false, _mut73920 = false, _mut73921 = false, _mut73922 = false, _mut73923 = false, _mut73924 = false, _mut73925 = false, _mut73926 = false, _mut73927 = false, _mut73928 = false, _mut73929 = false, _mut73930 = false, _mut73931 = false, _mut73932 = false, _mut73933 = false, _mut73934 = false, _mut73935 = false, _mut73936 = false, _mut73937 = false, _mut73938 = false, _mut73939 = false, _mut73940 = false, _mut73941 = false, _mut73942 = false, _mut73943 = false, _mut73944 = false, _mut73945 = false, _mut73946 = false, _mut73947 = false, _mut73948 = false, _mut73949 = false, _mut73950 = false, _mut73951 = false, _mut73952 = false, _mut73953 = false, _mut73954 = false, _mut73955 = false, _mut73956 = false, _mut73957 = false, _mut73958 = false, _mut73959 = false, _mut73960 = false, _mut73961 = false, _mut73962 = false, _mut73963 = false, _mut73964 = false, _mut73965 = false, _mut73966 = false, _mut73967 = false, _mut73968 = false, _mut73969 = false, _mut73970 = false, _mut73971 = false, _mut73972 = false, _mut73973 = false, _mut73974 = false, _mut73975 = false, _mut73976 = false, _mut73977 = false, _mut73978 = false, _mut73979 = false, _mut73980 = false, _mut73981 = false, _mut73982 = false, _mut73983 = false, _mut73984 = false, _mut73985 = false, _mut73986 = false, _mut73987 = false, _mut73988 = false, _mut73989 = false, _mut73990 = false, _mut73991 = false, _mut73992 = false, _mut73993 = false, _mut73994 = false, _mut73995 = false, _mut73996 = false, _mut73997 = false, _mut73998 = false, _mut73999 = false, _mut74000 = false, _mut74001 = false, _mut74002 = false, _mut74003 = false, _mut74004 = false, _mut74005 = false, _mut74006 = false, _mut74007 = false, _mut74008 = false, _mut74009 = false, _mut74010 = false, _mut74011 = false, _mut74012 = false, _mut74013 = false, _mut74014 = false, _mut74015 = false, _mut74016 = false, _mut74017 = false, _mut74018 = false, _mut74019 = false, _mut74020 = false, _mut74021 = false, _mut74022 = false, _mut74023 = false, _mut74024 = false, _mut74025 = false, _mut74026 = false, _mut74027 = false, _mut74028 = false, _mut74029 = false, _mut74030 = false, _mut74031 = false, _mut74032 = false, _mut74033 = false, _mut74034 = false, _mut74035 = false, _mut74036 = false, _mut74037 = false, _mut74038 = false, _mut74039 = false, _mut74040 = false, _mut74041 = false, _mut74042 = false, _mut74043 = false, _mut74044 = false, _mut74045 = false, _mut74046 = false, _mut74047 = false, _mut74048 = false, _mut74049 = false, _mut74050 = false, _mut74051 = false, _mut74052 = false, _mut74053 = false, _mut74054 = false, _mut74055 = false, _mut74056 = false, _mut74057 = false, _mut74058 = false, _mut74059 = false, _mut74060 = false, _mut74061 = false, _mut74062 = false, _mut74063 = false, _mut74064 = false, _mut74065 = false, _mut74066 = false, _mut74067 = false, _mut74068 = false, _mut74069 = false, _mut74070 = false, _mut74071 = false, _mut74072 = false, _mut74073 = false, _mut74074 = false, _mut74075 = false, _mut74076 = false, _mut74077 = false, _mut74078 = false, _mut74079 = false, _mut74080 = false, _mut74081 = false, _mut74082 = false, _mut74083 = false, _mut74084 = false, _mut74085 = false, _mut74086 = false, _mut74087 = false, _mut74088 = false, _mut74089 = false, _mut74090 = false, _mut74091 = false, _mut74092 = false, _mut74093 = false, _mut74094 = false, _mut74095 = false, _mut74096 = false, _mut74097 = false, _mut74098 = false, _mut74099 = false, _mut74100 = false, _mut74101 = false, _mut74102 = false, _mut74103 = false, _mut74104 = false, _mut74105 = false, _mut74106 = false, _mut74107 = false, _mut74108 = false, _mut74109 = false, _mut74110 = false, _mut74111 = false, _mut74112 = false, _mut74113 = false, _mut74114 = false, _mut74115 = false, _mut74116 = false, _mut74117 = false, _mut74118 = false, _mut74119 = false, _mut74120 = false, _mut74121 = false, _mut74122 = false, _mut74123 = false, _mut74124 = false, _mut74125 = false, _mut74126 = false, _mut74127 = false, _mut74128 = false, _mut74129 = false, _mut74130 = false, _mut74131 = false, _mut74132 = false, _mut74133 = false, _mut74134 = false, _mut74135 = false, _mut74136 = false, _mut74137 = false, _mut74138 = false, _mut74139 = false, _mut74140 = false, _mut74141 = false, _mut74142 = false, _mut74143 = false, _mut74144 = false, _mut74145 = false, _mut74146 = false, _mut74147 = false, _mut74148 = false, _mut74149 = false, _mut74150 = false, _mut74151 = false, _mut74152 = false, _mut74153 = false, _mut74154 = false, _mut74155 = false, _mut74156 = false, _mut74157 = false, _mut74158 = false, _mut74159 = false, _mut74160 = false, _mut74161 = false, _mut74162 = false, _mut74163 = false, _mut74164 = false, _mut74165 = false, _mut74166 = false, _mut74167 = false, _mut74168 = false, _mut74169 = false, _mut74170 = false, _mut74171 = false, _mut74172 = false, _mut74173 = false, _mut74174 = false, _mut74175 = false, _mut74176 = false, _mut74177 = false, _mut74178 = false, _mut74179 = false, _mut74180 = false, _mut74181 = false, _mut74182 = false, _mut74183 = false, _mut74184 = false, _mut74185 = false, _mut74186 = false, _mut74187 = false, _mut74188 = false, _mut74189 = false, _mut74190 = false, _mut74191 = false, _mut74192 = false, _mut74193 = false, _mut74194 = false, _mut74195 = false, _mut74196 = false, _mut74197 = false, _mut74198 = false, _mut74199 = false, _mut74200 = false, _mut74201 = false, _mut74202 = false, _mut74203 = false, _mut74204 = false, _mut74205 = false, _mut74206 = false, _mut74207 = false, _mut74208 = false, _mut74209 = false, _mut74210 = false, _mut74211 = false, _mut74212 = false, _mut74213 = false, _mut74214 = false, _mut74215 = false, _mut74216 = false, _mut74217 = false, _mut74218 = false, _mut74219 = false, _mut74220 = false, _mut74221 = false, _mut74222 = false, _mut74223 = false, _mut74224 = false, _mut74225 = false, _mut74226 = false, _mut74227 = false, _mut74228 = false, _mut74229 = false, _mut74230 = false, _mut74231 = false, _mut74232 = false, _mut74233 = false, _mut74234 = false, _mut74235 = false, _mut74236 = false, _mut74237 = false, _mut74238 = false, _mut74239 = false, _mut74240 = false, _mut74241 = false, _mut74242 = false, _mut74243 = false, _mut74244 = false, _mut74245 = false, _mut74246 = false, _mut74247 = false, _mut74248 = false, _mut74249 = false, _mut74250 = false, _mut74251 = false, _mut74252 = false, _mut74253 = false, _mut74254 = false, _mut74255 = false, _mut74256 = false, _mut74257 = false, _mut74258 = false, _mut74259 = false, _mut74260 = false, _mut74261 = false, _mut74262 = false, _mut74263 = false, _mut74264 = false, _mut74265 = false, _mut74266 = false, _mut74267 = false, _mut74268 = false, _mut74269 = false, _mut74270 = false, _mut74271 = false, _mut74272 = false, _mut74273 = false, _mut74274 = false, _mut74275 = false, _mut74276 = false, _mut74277 = false, _mut74278 = false, _mut74279 = false, _mut74280 = false, _mut74281 = false, _mut74282 = false, _mut74283 = false, _mut74284 = false, _mut74285 = false, _mut74286 = false, _mut74287 = false, _mut74288 = false, _mut74289 = false, _mut74290 = false, _mut74291 = false, _mut74292 = false, _mut74293 = false, _mut74294 = false, _mut74295 = false, _mut74296 = false, _mut74297 = false, _mut74298 = false, _mut74299 = false, _mut74300 = false, _mut74301 = false, _mut74302 = false, _mut74303 = false, _mut74304 = false, _mut74305 = false, _mut74306 = false, _mut74307 = false, _mut74308 = false, _mut74309 = false, _mut74310 = false, _mut74311 = false, _mut74312 = false, _mut74313 = false, _mut74314 = false, _mut74315 = false, _mut74316 = false, _mut74317 = false, _mut74318 = false, _mut74319 = false, _mut74320 = false, _mut74321 = false, _mut74322 = false, _mut74323 = false, _mut74324 = false, _mut74325 = false, _mut74326 = false, _mut74327 = false, _mut74328 = false, _mut74329 = false, _mut74330 = false, _mut74331 = false, _mut74332 = false, _mut74333 = false, _mut74334 = false, _mut74335 = false, _mut74336 = false, _mut74337 = false, _mut74338 = false, _mut74339 = false, _mut74340 = false, _mut74341 = false, _mut74342 = false, _mut74343 = false, _mut74344 = false, _mut74345 = false, _mut74346 = false, _mut74347 = false, _mut74348 = false, _mut74349 = false, _mut74350 = false, _mut74351 = false, _mut74352 = false, _mut74353 = false, _mut74354 = false, _mut74355 = false, _mut74356 = false, _mut74357 = false, _mut74358 = false, _mut74359 = false, _mut74360 = false, _mut74361 = false, _mut74362 = false, _mut74363 = false, _mut74364 = false, _mut74365 = false, _mut74366 = false, _mut74367 = false, _mut74368 = false, _mut74369 = false, _mut74370 = false, _mut74371 = false, _mut74372 = false, _mut74373 = false, _mut74374 = false, _mut74375 = false, _mut74376 = false, _mut74377 = false, _mut74378 = false, _mut74379 = false, _mut74380 = false, _mut74381 = false, _mut74382 = false, _mut74383 = false, _mut74384 = false, _mut74385 = false, _mut74386 = false, _mut74387 = false, _mut74388 = false, _mut74389 = false, _mut74390 = false, _mut74391 = false, _mut74392 = false, _mut74393 = false, _mut74394 = false, _mut74395 = false, _mut74396 = false, _mut74397 = false, _mut74398 = false, _mut74399 = false, _mut74400 = false, _mut74401 = false, _mut74402 = false, _mut74403 = false, _mut74404 = false, _mut74405 = false, _mut74406 = false, _mut74407 = false, _mut74408 = false, _mut74409 = false, _mut74410 = false, _mut74411 = false, _mut74412 = false, _mut74413 = false, _mut74414 = false, _mut74415 = false, _mut74416 = false, _mut74417 = false, _mut74418 = false, _mut74419 = false, _mut74420 = false, _mut74421 = false, _mut74422 = false, _mut74423 = false, _mut74424 = false, _mut74425 = false, _mut74426 = false, _mut74427 = false, _mut74428 = false, _mut74429 = false, _mut74430 = false, _mut74431 = false, _mut74432 = false, _mut74433 = false, _mut74434 = false, _mut74435 = false, _mut74436 = false, _mut74437 = false, _mut74438 = false, _mut74439 = false, _mut74440 = false, _mut74441 = false, _mut74442 = false, _mut74443 = false, _mut74444 = false, _mut74445 = false, _mut74446 = false, _mut74447 = false, _mut74448 = false;

    /**
     * Default value for {@link #checkFeasableCount}: {@value}.
     */
    public static final int DEFAULT_CHECKFEASABLECOUNT = 0;

    /**
     * Default value for {@link #stopFitness}: {@value}.
     */
    public static final double DEFAULT_STOPFITNESS = 0;

    /**
     * Default value for {@link #isActiveCMA}: {@value}.
     */
    public static final boolean DEFAULT_ISACTIVECMA = true;

    /**
     * Default value for {@link #maxIterations}: {@value}.
     */
    public static final int DEFAULT_MAXITERATIONS = 30000;

    /**
     * Default value for {@link #diagonalOnly}: {@value}.
     */
    public static final int DEFAULT_DIAGONALONLY = 0;

    /**
     * Default value for {@link #random}.
     */
    public static final RandomGenerator DEFAULT_RANDOMGENERATOR = new MersenneTwister();

    // population size
    private int lambda;

    /**
     * Covariance update mechanism, default is active CMA. isActiveCMA = true
     * turns on "active CMA" with a negative update of the covariance matrix and
     * checks for positive definiteness. OPTS.CMA.active = 2 does not check for
     * pos. def. and is numerically faster. Active CMA usually speeds up the
     * adaptation.
     */
    private boolean isActiveCMA;

    /**
     * Determines how often a new random offspring is generated in case it is
     * not feasible / beyond the defined limits, default is 0.
     */
    private int checkFeasableCount;

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
    private int diagonalOnly = 0;

    /**
     * Number of objective variables/problem dimension
     */
    private boolean isMinimize = true;

    /**
     * Indicates whether statistic data is collected.
     */
    private boolean generateStatistics = false;

    /**
     * Maximal number of iterations allowed.
     */
    private int maxIterations;

    /**
     * Limit for fitness value.
     */
    private double stopFitness;

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
    private RandomGenerator random;

    /**
     * History of sigma values.
     */
    private List<Double> statisticsSigmaHistory = new ArrayList<Double>();

    /**
     * History of mean matrix.
     */
    private List<RealMatrix> statisticsMeanHistory = new ArrayList<RealMatrix>();

    /**
     * History of fitness values.
     */
    private List<Double> statisticsFitnessHistory = new ArrayList<Double>();

    /**
     * History of D matrix.
     */
    private List<RealMatrix> statisticsDHistory = new ArrayList<RealMatrix>();

    /**
     * Default constructor, uses default parameters
     *
     * @deprecated As of version 3.1: Parameter {@code lambda} must be
     * passed with the call to {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])
     * optimize} (whereas in the current code it is set to an undocumented value).
     */
    @Deprecated
    public CMAESOptimizer() {
        this(0);
    }

    /**
     * @param lambda Population size.
     * @deprecated As of version 3.1: Parameter {@code lambda} must be
     * passed with the call to {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])
     * optimize} (whereas in the current code it is set to an undocumented value)..
     */
    @Deprecated
    public CMAESOptimizer(int lambda) {
        this(lambda, null, DEFAULT_MAXITERATIONS, DEFAULT_STOPFITNESS, DEFAULT_ISACTIVECMA, DEFAULT_DIAGONALONLY, DEFAULT_CHECKFEASABLECOUNT, DEFAULT_RANDOMGENERATOR, false, null);
    }

    /**
     * @param lambda Population size.
     * @param inputSigma Initial standard deviations to sample new points
     * around the initial guess.
     * @deprecated As of version 3.1: Parameters {@code lambda} and {@code inputSigma} must be
     * passed with the call to {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])
     * optimize}.
     */
    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma) {
        this(lambda, inputSigma, DEFAULT_MAXITERATIONS, DEFAULT_STOPFITNESS, DEFAULT_ISACTIVECMA, DEFAULT_DIAGONALONLY, DEFAULT_CHECKFEASABLECOUNT, DEFAULT_RANDOMGENERATOR, false);
    }

    /**
     * @param lambda Population size.
     * @param inputSigma Initial standard deviations to sample new points
     * around the initial guess.
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
     * @deprecated See {@link SimpleValueChecker#SimpleValueChecker()}
     */
    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics) {
        this(lambda, inputSigma, maxIterations, stopFitness, isActiveCMA, diagonalOnly, checkFeasableCount, random, generateStatistics, new SimpleValueChecker());
    }

    /**
     * @param lambda Population size.
     * @param inputSigma Initial standard deviations to sample new points
     * around the initial guess.
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
     * @deprecated As of version 3.1: Parameters {@code lambda} and {@code inputSigma} must be
     * passed with the call to {@link #optimize(int,MultivariateFunction,GoalType,OptimizationData[])
     * optimize}.
     */
    @Deprecated
    public CMAESOptimizer(int lambda, double[] inputSigma, int maxIterations, double stopFitness, boolean isActiveCMA, int diagonalOnly, int checkFeasableCount, RandomGenerator random, boolean generateStatistics, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        this.lambda = lambda;
        this.inputSigma = inputSigma == null ? null : (double[]) inputSigma.clone();
        this.maxIterations = maxIterations;
        this.stopFitness = stopFitness;
        this.isActiveCMA = isActiveCMA;
        this.diagonalOnly = diagonalOnly;
        this.checkFeasableCount = checkFeasableCount;
        this.random = random;
        this.generateStatistics = generateStatistics;
    }

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
     * @since 3.1
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.Sigma_414");
            for (int i = 0; ROR_less(i, s.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.Sigma_414", _mut73311, _mut73312, _mut73313, _mut73314, _mut73315); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.Sigma_414");
                if (ROR_less(s[i], 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.Sigma_414", _mut73306, _mut73307, _mut73308, _mut73309, _mut73310)) {
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
     * @since 3.1
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.PopulationSize_452");
            if (ROR_less_equals(size, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.PopulationSize_452", _mut73316, _mut73317, _mut73318, _mut73319, _mut73320)) {
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
     * Optimize an objective function.
     *
     * @param maxEval Allowed number of evaluations of the objective function.
     * @param f Objective function.
     * @param goalType Optimization type.
     * @param optData Optimization data. The following data will be looked for:
     * <ul>
     *  <li>{@link org.apache.commons.math3.optimization.InitialGuess InitialGuess}</li>
     *  <li>{@link Sigma}</li>
     *  <li>{@link PopulationSize}</li>
     * </ul>
     * @return the point/value pair giving the optimal value for objective
     * function.
     */
    @Override
    protected PointValuePair optimizeInternal(int maxEval, MultivariateFunction f, GoalType goalType, OptimizationData... optData) {
        // Scan "optData" for the input specific to this optimizer.
        parseOptimizationData(optData);
        // "optData" and call "doOptimize".
        return super.optimizeInternal(maxEval, f, goalType, optData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
        checkParameters();
        // -------------------- Initialization --------------------------------
        isMinimize = getGoalType().equals(GoalType.MINIMIZE);
        final FitnessFunction fitfun = new FitnessFunction();
        final double[] guess = getStartPoint();
        // number of objective variables/problem dimension
        dimension = guess.length;
        initializeCMA(guess);
        iterations = 0;
        double bestValue = fitfun.value(guess);
        push(fitnessHistory, bestValue);
        PointValuePair optimum = new PointValuePair(getStartPoint(), isMinimize ? bestValue : -bestValue);
        PointValuePair lastResult = null;
        generationLoop: for (iterations = 1; ROR_less_equals(iterations, maxIterations, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73514, _mut73515, _mut73516, _mut73517, _mut73518); iterations++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
            // Generate and evaluate lambda offspring
            final RealMatrix arz = randn1(dimension, lambda);
            final RealMatrix arx = zeros(dimension, lambda);
            final double[] fitness = new double[lambda];
            // generate random offspring
            for (int k = 0; ROR_less(k, lambda, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73341, _mut73342, _mut73343, _mut73344, _mut73345); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
                RealMatrix arxk = null;
                for (int i = 0; ROR_less(i, AOR_plus(checkFeasableCount, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73332, _mut73333, _mut73334, _mut73335), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73336, _mut73337, _mut73338, _mut73339, _mut73340); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
                    if (ROR_less_equals(diagonalOnly, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73321, _mut73322, _mut73323, _mut73324, _mut73325)) {
                        arxk = xmean.add(BD.multiply(arz.getColumnMatrix(k)).scalarMultiply(// m + sig * Normal(0,C)
                        sigma));
                    } else {
                        arxk = xmean.add(times(diagD, arz.getColumnMatrix(k)).scalarMultiply(sigma));
                    }
                    if ((_mut73331 ? (ROR_greater_equals(i, checkFeasableCount, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73326, _mut73327, _mut73328, _mut73329, _mut73330) && fitfun.isFeasible(arxk.getColumn(0))) : (ROR_greater_equals(i, checkFeasableCount, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73326, _mut73327, _mut73328, _mut73329, _mut73330) || fitfun.isFeasible(arxk.getColumn(0))))) {
                        break;
                    }
                    // regenerate random arguments for row
                    arz.setColumn(k, randn(dimension));
                }
                copyColumn(arxk, 0, arx, k);
                try {
                    // compute fitness
                    fitness[k] = fitfun.value(arx.getColumn(k));
                } catch (TooManyEvaluationsException e) {
                    break generationLoop;
                }
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
            if (ROR_less_equals(diagonalOnly, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73346, _mut73347, _mut73348, _mut73349, _mut73350)) {
                updateCovariance(hsig, bestArx, arz, arindex, xold);
            } else {
                updateCovarianceDiagonalOnly(hsig, bestArz);
            }
            // Adapt step size sigma - Eq. (5)
            sigma *= FastMath.exp(FastMath.min(1, AOR_divide(AOR_multiply((AOR_minus(AOR_divide(normps, chiN, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73351, _mut73352, _mut73353, _mut73354), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73355, _mut73356, _mut73357, _mut73358)), cs, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73359, _mut73360, _mut73361, _mut73362), damps, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73363, _mut73364, _mut73365, _mut73366)));
            final double bestFitness = fitness[arindex[0]];
            final double worstFitness = fitness[arindex[AOR_minus(arindex.length, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73367, _mut73368, _mut73369, _mut73370)]];
            if (ROR_greater(bestValue, bestFitness, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73371, _mut73372, _mut73373, _mut73374, _mut73375)) {
                bestValue = bestFitness;
                lastResult = optimum;
                optimum = new PointValuePair(fitfun.repair(bestArx.getColumn(0)), isMinimize ? bestFitness : -bestFitness);
                if ((_mut73377 ? ((_mut73376 ? (getConvergenceChecker() != null || lastResult != null) : (getConvergenceChecker() != null && lastResult != null)) || getConvergenceChecker().converged(iterations, optimum, lastResult)) : ((_mut73376 ? (getConvergenceChecker() != null || lastResult != null) : (getConvergenceChecker() != null && lastResult != null)) && getConvergenceChecker().converged(iterations, optimum, lastResult)))) {
                    break generationLoop;
                }
            }
            // Break, if fitness is good enough
            if ((_mut73388 ? (ROR_not_equals(stopFitness, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73378, _mut73379, _mut73380, _mut73381, _mut73382) || ROR_less(bestFitness, (isMinimize ? stopFitness : -stopFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73383, _mut73384, _mut73385, _mut73386, _mut73387)) : (ROR_not_equals(stopFitness, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73378, _mut73379, _mut73380, _mut73381, _mut73382) && ROR_less(bestFitness, (isMinimize ? stopFitness : -stopFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73383, _mut73384, _mut73385, _mut73386, _mut73387)))) {
                break generationLoop;
            }
            final double[] sqrtDiagC = sqrt(diagC).getColumn(0);
            final double[] pcCol = pc.getColumn(0);
            for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73407, _mut73408, _mut73409, _mut73410, _mut73411); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
                if (ROR_greater(AOR_multiply(sigma, FastMath.max(FastMath.abs(pcCol[i]), sqrtDiagC[i]), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73389, _mut73390, _mut73391, _mut73392), stopTolX, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73393, _mut73394, _mut73395, _mut73396, _mut73397)) {
                    break;
                }
                if (ROR_greater_equals(i, AOR_minus(dimension, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73398, _mut73399, _mut73400, _mut73401), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73402, _mut73403, _mut73404, _mut73405, _mut73406)) {
                    break generationLoop;
                }
            }
            for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73421, _mut73422, _mut73423, _mut73424, _mut73425); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496");
                if (ROR_greater(AOR_multiply(sigma, sqrtDiagC[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73412, _mut73413, _mut73414, _mut73415), stopTolUpX, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73416, _mut73417, _mut73418, _mut73419, _mut73420)) {
                    break generationLoop;
                }
            }
            final double historyBest = min(fitnessHistory);
            final double historyWorst = max(fitnessHistory);
            if ((_mut73440 ? (ROR_greater(iterations, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73426, _mut73427, _mut73428, _mut73429, _mut73430) || ROR_less(AOR_minus(FastMath.max(historyWorst, worstFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73431, _mut73432, _mut73433, _mut73434), stopTolFun, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73435, _mut73436, _mut73437, _mut73438, _mut73439)) : (ROR_greater(iterations, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73426, _mut73427, _mut73428, _mut73429, _mut73430) && ROR_less(AOR_minus(FastMath.max(historyWorst, worstFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73431, _mut73432, _mut73433, _mut73434), stopTolFun, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73435, _mut73436, _mut73437, _mut73438, _mut73439)))) {
                break generationLoop;
            }
            if ((_mut73455 ? (ROR_greater(iterations, fitnessHistory.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73441, _mut73442, _mut73443, _mut73444, _mut73445) || ROR_less(AOR_minus(historyWorst, historyBest, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73446, _mut73447, _mut73448, _mut73449), stopTolHistFun, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73450, _mut73451, _mut73452, _mut73453, _mut73454)) : (ROR_greater(iterations, fitnessHistory.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73441, _mut73442, _mut73443, _mut73444, _mut73445) && ROR_less(AOR_minus(historyWorst, historyBest, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73446, _mut73447, _mut73448, _mut73449), stopTolHistFun, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73450, _mut73451, _mut73452, _mut73453, _mut73454)))) {
                break generationLoop;
            }
            // condition number of the covariance matrix exceeds 1e14
            if (ROR_greater(AOR_divide(max(diagD), min(diagD), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73456, _mut73457, _mut73458, _mut73459), 1e7, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73460, _mut73461, _mut73462, _mut73463, _mut73464)) {
                break generationLoop;
            }
            // user defined termination
            if (getConvergenceChecker() != null) {
                final PointValuePair current = new PointValuePair(bestArx.getColumn(0), isMinimize ? bestFitness : -bestFitness);
                if ((_mut73465 ? (lastResult != null || getConvergenceChecker().converged(iterations, current, lastResult)) : (lastResult != null && getConvergenceChecker().converged(iterations, current, lastResult)))) {
                    break generationLoop;
                }
                lastResult = current;
            }
            // Adjust step size in case of equal function values (flat fitness)
            if (ROR_equals(bestValue, fitness[arindex[(int) (AOR_plus(0.1, AOR_divide(lambda, 4., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73466, _mut73467, _mut73468, _mut73469), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73470, _mut73471, _mut73472, _mut73473))]], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73474, _mut73475, _mut73476, _mut73477, _mut73478)) {
                sigma *= FastMath.exp(AOR_plus(0.2, AOR_divide(cs, damps, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73479, _mut73480, _mut73481, _mut73482), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73483, _mut73484, _mut73485, _mut73486));
            }
            if ((_mut73501 ? (ROR_greater(iterations, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73487, _mut73488, _mut73489, _mut73490, _mut73491) || ROR_equals(AOR_minus(FastMath.max(historyWorst, bestFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73492, _mut73493, _mut73494, _mut73495), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73496, _mut73497, _mut73498, _mut73499, _mut73500)) : (ROR_greater(iterations, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73487, _mut73488, _mut73489, _mut73490, _mut73491) && ROR_equals(AOR_minus(FastMath.max(historyWorst, bestFitness), FastMath.min(historyBest, bestFitness), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73492, _mut73493, _mut73494, _mut73495), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73496, _mut73497, _mut73498, _mut73499, _mut73500)))) {
                sigma *= FastMath.exp(AOR_plus(0.2, AOR_divide(cs, damps, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73502, _mut73503, _mut73504, _mut73505), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73506, _mut73507, _mut73508, _mut73509));
            }
            // store best in history
            push(fitnessHistory, bestFitness);
            fitfun.setValueRange(AOR_minus(worstFitness, bestFitness, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.doOptimize_496", _mut73510, _mut73511, _mut73512, _mut73513));
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
    private void parseOptimizationData(OptimizationData... optData) {
        // not provided in the argument list.
        for (OptimizationData data : optData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.parseOptimizationData_651");
            if (data instanceof Sigma) {
                inputSigma = ((Sigma) data).getSigma();
                continue;
            }
            if (data instanceof PopulationSize) {
                lambda = ((PopulationSize) data).getPopulationSize();
                continue;
            }
        }
    }

    /**
     * Checks dimensions and values of boundaries and inputSigma if defined.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669");
        final double[] init = getStartPoint();
        final double[] lB = getLowerBound();
        final double[] uB = getUpperBound();
        if (inputSigma != null) {
            if (ROR_not_equals(inputSigma.length, init.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73519, _mut73520, _mut73521, _mut73522, _mut73523)) {
                throw new DimensionMismatchException(inputSigma.length, init.length);
            }
            for (int i = 0; ROR_less(i, init.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73542, _mut73543, _mut73544, _mut73545, _mut73546); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669");
                if (ROR_less(inputSigma[i], 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73524, _mut73525, _mut73526, _mut73527, _mut73528)) {
                    // XXX Remove this block in 4.0 (check performed in "Sigma" class).
                    throw new NotPositiveException(inputSigma[i]);
                }
                if (ROR_greater(inputSigma[i], AOR_minus(uB[i], lB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73529, _mut73530, _mut73531, _mut73532), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73533, _mut73534, _mut73535, _mut73536, _mut73537)) {
                    throw new OutOfRangeException(inputSigma[i], 0, AOR_minus(uB[i], lB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.checkParameters_669", _mut73538, _mut73539, _mut73540, _mut73541));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695");
        if (ROR_less_equals(lambda, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73547, _mut73548, _mut73549, _mut73550, _mut73551)) {
            // throw new NotStrictlyPositiveException(lambda);
            lambda = AOR_plus(4, (int) (AOR_multiply(3, FastMath.log(dimension), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73552, _mut73553, _mut73554, _mut73555)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73556, _mut73557, _mut73558, _mut73559);
        }
        // initialize sigma
        final double[][] sigmaArray = new double[guess.length][1];
        for (int i = 0; ROR_less(i, guess.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73560, _mut73561, _mut73562, _mut73563, _mut73564); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695");
            // sigmaArray[i][0] = inputSigma[i];
            sigmaArray[i][0] = inputSigma == null ? 0.3 : inputSigma[i];
        }
        final RealMatrix insigma = new Array2DRowRealMatrix(sigmaArray, false);
        // overall standard deviation
        sigma = max(insigma);
        // initialize termination criteria
        stopTolUpX = AOR_multiply(1e3, max(insigma), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73565, _mut73566, _mut73567, _mut73568);
        stopTolX = AOR_multiply(1e-11, max(insigma), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73569, _mut73570, _mut73571, _mut73572);
        stopTolFun = 1e-12;
        stopTolHistFun = 1e-13;
        // number of parents/points for recombination
        mu = AOR_divide(lambda, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73573, _mut73574, _mut73575, _mut73576);
        logMu2 = FastMath.log(AOR_plus(mu, 0.5, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73577, _mut73578, _mut73579, _mut73580));
        weights = log(sequence(1, mu, 1)).scalarMultiply(-1).scalarAdd(logMu2);
        double sumw = 0;
        double sumwq = 0;
        for (int i = 0; ROR_less(i, mu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73585, _mut73586, _mut73587, _mut73588, _mut73589); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695");
            double w = weights.getEntry(i, 0);
            sumw += w;
            sumwq += AOR_multiply(w, w, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73581, _mut73582, _mut73583, _mut73584);
        }
        weights = weights.scalarMultiply(AOR_divide(1, sumw, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73590, _mut73591, _mut73592, _mut73593));
        // variance-effectiveness of sum w_i x_i
        mueff = AOR_divide(AOR_multiply(sumw, sumw, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73594, _mut73595, _mut73596, _mut73597), sumwq, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73598, _mut73599, _mut73600, _mut73601);
        // initialize dynamic strategy parameters and constants
        cc = AOR_divide((AOR_plus(4, AOR_divide(mueff, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73602, _mut73603, _mut73604, _mut73605), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73606, _mut73607, _mut73608, _mut73609)), (AOR_plus(AOR_plus(dimension, 4, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73610, _mut73611, _mut73612, _mut73613), AOR_divide(AOR_multiply(2, mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73614, _mut73615, _mut73616, _mut73617), dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73618, _mut73619, _mut73620, _mut73621), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73622, _mut73623, _mut73624, _mut73625)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73626, _mut73627, _mut73628, _mut73629);
        cs = AOR_divide((AOR_plus(mueff, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73630, _mut73631, _mut73632, _mut73633)), (AOR_plus(AOR_plus(dimension, mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73634, _mut73635, _mut73636, _mut73637), 3., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73638, _mut73639, _mut73640, _mut73641)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73642, _mut73643, _mut73644, _mut73645);
        damps = AOR_plus(AOR_multiply((AOR_plus(1, AOR_multiply(2, FastMath.max(0, AOR_minus(FastMath.sqrt(AOR_divide((AOR_minus(mueff, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73646, _mut73647, _mut73648, _mut73649)), (AOR_plus(dimension, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73650, _mut73651, _mut73652, _mut73653)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73654, _mut73655, _mut73656, _mut73657)), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73658, _mut73659, _mut73660, _mut73661)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73662, _mut73663, _mut73664, _mut73665), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73666, _mut73667, _mut73668, _mut73669)), FastMath.max(0.3, AOR_minus(1, AOR_divide(dimension, (AOR_plus(1e-6, maxIterations, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73670, _mut73671, _mut73672, _mut73673)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73674, _mut73675, _mut73676, _mut73677), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73678, _mut73679, _mut73680, _mut73681)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73682, _mut73683, _mut73684, _mut73685), // minor increment
        cs, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73686, _mut73687, _mut73688, _mut73689);
        ccov1 = AOR_divide(2, (AOR_plus(AOR_multiply((AOR_plus(dimension, 1.3, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73690, _mut73691, _mut73692, _mut73693)), (AOR_plus(dimension, 1.3, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73694, _mut73695, _mut73696, _mut73697)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73698, _mut73699, _mut73700, _mut73701), mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73702, _mut73703, _mut73704, _mut73705)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73706, _mut73707, _mut73708, _mut73709);
        ccovmu = FastMath.min(AOR_minus(1, ccov1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73710, _mut73711, _mut73712, _mut73713), AOR_divide(AOR_multiply(2, (AOR_plus(AOR_minus(mueff, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73714, _mut73715, _mut73716, _mut73717), AOR_divide(1, mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73718, _mut73719, _mut73720, _mut73721), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73722, _mut73723, _mut73724, _mut73725)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73726, _mut73727, _mut73728, _mut73729), (AOR_plus(AOR_multiply((AOR_plus(dimension, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73730, _mut73731, _mut73732, _mut73733)), (AOR_plus(dimension, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73734, _mut73735, _mut73736, _mut73737)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73738, _mut73739, _mut73740, _mut73741), mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73742, _mut73743, _mut73744, _mut73745)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73746, _mut73747, _mut73748, _mut73749));
        ccov1Sep = FastMath.min(1, AOR_divide(AOR_multiply(ccov1, (AOR_plus(dimension, 1.5, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73750, _mut73751, _mut73752, _mut73753)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73754, _mut73755, _mut73756, _mut73757), 3, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73758, _mut73759, _mut73760, _mut73761));
        ccovmuSep = FastMath.min(AOR_minus(1, ccov1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73762, _mut73763, _mut73764, _mut73765), AOR_divide(AOR_multiply(ccovmu, (AOR_plus(dimension, 1.5, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73766, _mut73767, _mut73768, _mut73769)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73770, _mut73771, _mut73772, _mut73773), 3, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73774, _mut73775, _mut73776, _mut73777));
        chiN = AOR_multiply(FastMath.sqrt(dimension), (AOR_plus(AOR_minus(1, AOR_divide(1, (AOR_multiply((double) 4, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73778, _mut73779, _mut73780, _mut73781)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73782, _mut73783, _mut73784, _mut73785), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73786, _mut73787, _mut73788, _mut73789), AOR_divide(1, (AOR_multiply(AOR_multiply((double) 21, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73790, _mut73791, _mut73792, _mut73793), dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73794, _mut73795, _mut73796, _mut73797)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73798, _mut73799, _mut73800, _mut73801), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73802, _mut73803, _mut73804, _mut73805)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73806, _mut73807, _mut73808, _mut73809);
        // objective variables
        xmean = MatrixUtils.createColumnRealMatrix(guess);
        diagD = insigma.scalarMultiply(AOR_divide(1, sigma, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73810, _mut73811, _mut73812, _mut73813));
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
        historySize = AOR_plus(10, (int) (AOR_divide(AOR_multiply(AOR_multiply(3, 10, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73814, _mut73815, _mut73816, _mut73817), dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73818, _mut73819, _mut73820, _mut73821), (double) lambda, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73822, _mut73823, _mut73824, _mut73825)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73826, _mut73827, _mut73828, _mut73829);
        // history of fitness values
        fitnessHistory = new double[historySize];
        for (int i = 0; ROR_less(i, historySize, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695", _mut73830, _mut73831, _mut73832, _mut73833, _mut73834); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.initializeCMA_695");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773");
        ps = ps.scalarMultiply(AOR_minus(1, cs, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73847, _mut73848, _mut73849, _mut73850)).add(B.multiply(zmean).scalarMultiply(FastMath.sqrt(AOR_multiply(AOR_multiply(cs, (AOR_minus(2, cs, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73835, _mut73836, _mut73837, _mut73838)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73839, _mut73840, _mut73841, _mut73842), mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73843, _mut73844, _mut73845, _mut73846))));
        normps = ps.getFrobeniusNorm();
        final boolean hsig = ROR_less(AOR_divide(AOR_divide(normps, FastMath.sqrt(AOR_minus(1, FastMath.pow(AOR_minus(1, cs, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73851, _mut73852, _mut73853, _mut73854), AOR_multiply(2, iterations, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73855, _mut73856, _mut73857, _mut73858)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73859, _mut73860, _mut73861, _mut73862)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73863, _mut73864, _mut73865, _mut73866), chiN, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73867, _mut73868, _mut73869, _mut73870), AOR_plus(1.4, AOR_divide(2, (AOR_plus((double) dimension, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73871, _mut73872, _mut73873, _mut73874)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73875, _mut73876, _mut73877, _mut73878), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73879, _mut73880, _mut73881, _mut73882), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73883, _mut73884, _mut73885, _mut73886, _mut73887);
        pc = pc.scalarMultiply(AOR_minus(1, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73888, _mut73889, _mut73890, _mut73891));
        if (hsig) {
            pc = pc.add(xmean.subtract(xold).scalarMultiply(AOR_divide(FastMath.sqrt(AOR_multiply(AOR_multiply(cc, (AOR_minus(2, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73892, _mut73893, _mut73894, _mut73895)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73896, _mut73897, _mut73898, _mut73899), mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73900, _mut73901, _mut73902, _mut73903)), sigma, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateEvolutionPaths_773", _mut73904, _mut73905, _mut73906, _mut73907)));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794");
        // minor correction if hsig==false
        double oldFac = hsig ? 0 : AOR_multiply(AOR_multiply(ccov1Sep, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73908, _mut73909, _mut73910, _mut73911), (AOR_minus(2, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73912, _mut73913, _mut73914, _mut73915)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73916, _mut73917, _mut73918, _mut73919);
        oldFac += AOR_minus(AOR_minus(1, ccov1Sep, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73920, _mut73921, _mut73922, _mut73923), ccovmuSep, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73924, _mut73925, _mut73926, _mut73927);
        diagC = // regard old matrix
        diagC.scalarMultiply(oldFac).add(// plus rank one update
        square(pc).scalarMultiply(ccov1Sep)).add(// plus rank mu update
        (times(diagC, square(bestArz).multiply(weights))).scalarMultiply(ccovmuSep));
        // replaces eig(C)
        diagD = sqrt(diagC);
        if ((_mut73938 ? (ROR_greater(diagonalOnly, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73928, _mut73929, _mut73930, _mut73931, _mut73932) || ROR_greater(iterations, diagonalOnly, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73933, _mut73934, _mut73935, _mut73936, _mut73937)) : (ROR_greater(diagonalOnly, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73928, _mut73929, _mut73930, _mut73931, _mut73932) && ROR_greater(iterations, diagonalOnly, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovarianceDiagonalOnly_794", _mut73933, _mut73934, _mut73935, _mut73936, _mut73937)))) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825");
        double negccov = 0;
        if (ROR_greater(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73939, _mut73940, _mut73941, _mut73942), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73943, _mut73944, _mut73945, _mut73946, _mut73947)) {
            final RealMatrix arpos = bestArx.subtract(repmat(xold, 1, mu)).scalarMultiply(AOR_divide(1, sigma, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73948, _mut73949, _mut73950, _mut73951));
            final RealMatrix roneu = pc.multiply(pc.transpose()).scalarMultiply(// rank one update
            ccov1);
            // minor correction if hsig==false
            double oldFac = hsig ? 0 : AOR_multiply(AOR_multiply(ccov1, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73952, _mut73953, _mut73954, _mut73955), (AOR_minus(2, cc, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73956, _mut73957, _mut73958, _mut73959)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73960, _mut73961, _mut73962, _mut73963);
            oldFac += AOR_minus(AOR_minus(1, ccov1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73964, _mut73965, _mut73966, _mut73967), ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73968, _mut73969, _mut73970, _mut73971);
            if (isActiveCMA) {
                // Adapt covariance matrix C active CMA
                negccov = AOR_divide(AOR_multiply(AOR_multiply((AOR_minus(1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73972, _mut73973, _mut73974, _mut73975)), 0.25, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73976, _mut73977, _mut73978, _mut73979), mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73980, _mut73981, _mut73982, _mut73983), (AOR_plus(FastMath.pow(AOR_plus(dimension, 2, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73984, _mut73985, _mut73986, _mut73987), 1.5), AOR_multiply(2, mueff, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73988, _mut73989, _mut73990, _mut73991), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73992, _mut73993, _mut73994, _mut73995)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut73996, _mut73997, _mut73998, _mut73999);
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
                final double negcovMax = AOR_divide((AOR_minus(1, negminresidualvariance, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74000, _mut74001, _mut74002, _mut74003)), square(arnormsInv).multiply(weights).getEntry(0, 0), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74004, _mut74005, _mut74006, _mut74007);
                if (ROR_greater(negccov, negcovMax, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74008, _mut74009, _mut74010, _mut74011, _mut74012)) {
                    negccov = negcovMax;
                }
                arzneg = times(arzneg, repmat(arnormsInv, dimension, 1));
                final RealMatrix artmp = BD.multiply(arzneg);
                final RealMatrix Cneg = artmp.multiply(diag(weights)).multiply(artmp.transpose());
                oldFac += AOR_multiply(negalphaold, negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74013, _mut74014, _mut74015, _mut74016);
                C = C.scalarMultiply(oldFac).add(// regard old matrix
                roneu).add(// plus rank one update
                arpos.scalarMultiply(AOR_plus(ccovmu, AOR_multiply((AOR_minus(1, negalphaold, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74017, _mut74018, _mut74019, _mut74020)), negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74021, _mut74022, _mut74023, _mut74024), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateCovariance_825", _mut74025, _mut74026, _mut74027, _mut74028)).multiply(times(repmat(weights, 1, dimension), arpos.transpose()))).subtract(Cneg.scalarMultiply(negccov));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890");
        if ((_mut74071 ? (ROR_greater(AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74029, _mut74030, _mut74031, _mut74032), negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74033, _mut74034, _mut74035, _mut74036), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74037, _mut74038, _mut74039, _mut74040, _mut74041) || ROR_less((AOR_divide(AOR_divide(AOR_divide(AOR_remainder(iterations, 1., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74042, _mut74043, _mut74044, _mut74045), (AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74046, _mut74047, _mut74048, _mut74049), negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74050, _mut74051, _mut74052, _mut74053)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74054, _mut74055, _mut74056, _mut74057), dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74058, _mut74059, _mut74060, _mut74061), 10., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74062, _mut74063, _mut74064, _mut74065)), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74066, _mut74067, _mut74068, _mut74069, _mut74070)) : (ROR_greater(AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74029, _mut74030, _mut74031, _mut74032), negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74033, _mut74034, _mut74035, _mut74036), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74037, _mut74038, _mut74039, _mut74040, _mut74041) && ROR_less((AOR_divide(AOR_divide(AOR_divide(AOR_remainder(iterations, 1., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74042, _mut74043, _mut74044, _mut74045), (AOR_plus(AOR_plus(ccov1, ccovmu, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74046, _mut74047, _mut74048, _mut74049), negccov, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74050, _mut74051, _mut74052, _mut74053)), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74054, _mut74055, _mut74056, _mut74057), dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74058, _mut74059, _mut74060, _mut74061), 10., "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74062, _mut74063, _mut74064, _mut74065)), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74066, _mut74067, _mut74068, _mut74069, _mut74070)))) {
            // to achieve O(N^2)
            C = triu(C, 0).add(triu(C, 1).transpose());
            // enforce symmetry to prevent complex numbers
            final EigenDecomposition eig = new EigenDecomposition(C);
            // eigen decomposition, B==normalized eigenvectors
            B = eig.getV();
            D = eig.getD();
            diagD = diag(D);
            if (ROR_less_equals(min(diagD), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74072, _mut74073, _mut74074, _mut74075, _mut74076)) {
                for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74082, _mut74083, _mut74084, _mut74085, _mut74086); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890");
                    if (ROR_less(diagD.getEntry(i, 0), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74077, _mut74078, _mut74079, _mut74080, _mut74081)) {
                        diagD.setEntry(i, 0, 0);
                    }
                }
                final double tfac = AOR_divide(max(diagD), 1e14, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74087, _mut74088, _mut74089, _mut74090);
                C = C.add(eye(dimension, dimension).scalarMultiply(tfac));
                diagD = diagD.add(ones(dimension, 1).scalarMultiply(tfac));
            }
            if (ROR_greater(max(diagD), AOR_multiply(1e14, min(diagD), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74091, _mut74092, _mut74093, _mut74094), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74095, _mut74096, _mut74097, _mut74098, _mut74099)) {
                final double tfac = AOR_minus(AOR_divide(max(diagD), 1e14, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74100, _mut74101, _mut74102, _mut74103), min(diagD), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.updateBD_890", _mut74104, _mut74105, _mut74106, _mut74107);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.push_927");
        for (int i = vals.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.push_927", _mut74112, _mut74113, _mut74114, _mut74115, _mut74116); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.push_927");
            vals[i] = vals[AOR_minus(i, 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.push_927", _mut74108, _mut74109, _mut74110, _mut74111)];
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sortedIndices_940");
        final DoubleIndex[] dis = new DoubleIndex[doubles.length];
        for (int i = 0; ROR_less(i, doubles.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sortedIndices_940", _mut74117, _mut74118, _mut74119, _mut74120, _mut74121); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sortedIndices_940");
            dis[i] = new DoubleIndex(doubles[i], i);
        }
        Arrays.sort(dis);
        final int[] indices = new int[doubles.length];
        for (int i = 0; ROR_less(i, doubles.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sortedIndices_940", _mut74122, _mut74123, _mut74124, _mut74125, _mut74126); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sortedIndices_940");
            indices[i] = dis[i].index;
        }
        return indices;
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.equals_978");
            if (this == other) {
                return true;
            }
            if (other instanceof DoubleIndex) {
                return ROR_equals(Double.compare(value, ((DoubleIndex) other).value), 0, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.equals_978", _mut74127, _mut74128, _mut74129, _mut74130, _mut74131);
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
     * Normalizes fitness values to the range [0,1]. Adds a penalty to the
     * fitness value if out of range. The penalty is adjusted by calling
     * setValueRange().
     */
    private class FitnessFunction {

        /**
         * Determines the penalty for boundary violations
         */
        private double valueRange;

        /**
         * Flag indicating whether the objective variables are forced into their
         * bounds if defined
         */
        private final boolean isRepairMode;

        /**
         * Simple constructor.
         */
        FitnessFunction() {
            valueRange = 1;
            isRepairMode = true;
        }

        /**
         * @param point Normalized objective variables.
         * @return the objective value + penalty for violated bounds.
         */
        public double value(final double[] point) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.value_1025");
            double value;
            if (isRepairMode) {
                double[] repaired = repair(point);
                value = AOR_plus(CMAESOptimizer.this.computeObjectiveValue(repaired), penalty(point, repaired), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.value_1025", _mut74132, _mut74133, _mut74134, _mut74135);
            } else {
                value = CMAESOptimizer.this.computeObjectiveValue(point);
            }
            return isMinimize ? value : -value;
        }

        /**
         * @param x Normalized objective variables.
         * @return {@code true} if in bounds.
         */
        public boolean isFeasible(final double[] x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.isFeasible_1041");
            final double[] lB = CMAESOptimizer.this.getLowerBound();
            final double[] uB = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.isFeasible_1041", _mut74146, _mut74147, _mut74148, _mut74149, _mut74150); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.isFeasible_1041");
                if (ROR_less(x[i], lB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.isFeasible_1041", _mut74136, _mut74137, _mut74138, _mut74139, _mut74140)) {
                    return false;
                }
                if (ROR_greater(x[i], uB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.isFeasible_1041", _mut74141, _mut74142, _mut74143, _mut74144, _mut74145)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * @param valueRange Adjusts the penalty computation.
         */
        public void setValueRange(double valueRange) {
            this.valueRange = valueRange;
        }

        /**
         * @param x Normalized objective variables.
         * @return the repaired (i.e. all in bounds) objective variables.
         */
        private double[] repair(final double[] x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.repair_1067");
            final double[] lB = CMAESOptimizer.this.getLowerBound();
            final double[] uB = CMAESOptimizer.this.getUpperBound();
            final double[] repaired = new double[x.length];
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repair_1067", _mut74161, _mut74162, _mut74163, _mut74164, _mut74165); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.repair_1067");
                if (ROR_less(x[i], lB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repair_1067", _mut74151, _mut74152, _mut74153, _mut74154, _mut74155)) {
                    repaired[i] = lB[i];
                } else if (ROR_greater(x[i], uB[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repair_1067", _mut74156, _mut74157, _mut74158, _mut74159, _mut74160)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.penalty_1089");
            double penalty = 0;
            for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.penalty_1089", _mut74174, _mut74175, _mut74176, _mut74177, _mut74178); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.penalty_1089");
                double diff = FastMath.abs(AOR_minus(x[i], repaired[i], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.penalty_1089", _mut74166, _mut74167, _mut74168, _mut74169));
                penalty += AOR_multiply(diff, valueRange, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.penalty_1089", _mut74170, _mut74171, _mut74172, _mut74173);
            }
            return isMinimize ? penalty : -penalty;
        }
    }

    /**
     * @param m Input matrix
     * @return Matrix representing the element-wise logarithm of m.
     */
    private static RealMatrix log(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.log_1105");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.log_1105", _mut74184, _mut74185, _mut74186, _mut74187, _mut74188); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.log_1105");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.log_1105", _mut74179, _mut74180, _mut74181, _mut74182, _mut74183); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.log_1105");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sqrt_1119");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sqrt_1119", _mut74194, _mut74195, _mut74196, _mut74197, _mut74198); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sqrt_1119");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sqrt_1119", _mut74189, _mut74190, _mut74191, _mut74192, _mut74193); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sqrt_1119");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133", _mut74208, _mut74209, _mut74210, _mut74211, _mut74212); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133", _mut74203, _mut74204, _mut74205, _mut74206, _mut74207); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133");
                double e = m.getEntry(r, c);
                d[r][c] = AOR_multiply(e, e, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.square_1133", _mut74199, _mut74200, _mut74201, _mut74202);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149", _mut74222, _mut74223, _mut74224, _mut74225, _mut74226); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149", _mut74217, _mut74218, _mut74219, _mut74220, _mut74221); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149");
                d[r][c] = AOR_multiply(m.getEntry(r, c), n.getEntry(r, c), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.times_1149", _mut74213, _mut74214, _mut74215, _mut74216);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164", _mut74236, _mut74237, _mut74238, _mut74239, _mut74240); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164", _mut74231, _mut74232, _mut74233, _mut74234, _mut74235); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164");
                d[r][c] = AOR_divide(m.getEntry(r, c), n.getEntry(r, c), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.divide_1164", _mut74227, _mut74228, _mut74229, _mut74230);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.selectColumns_1179");
        final double[][] d = new double[m.getRowDimension()][cols.length];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.selectColumns_1179", _mut74246, _mut74247, _mut74248, _mut74249, _mut74250); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.selectColumns_1179");
            for (int c = 0; ROR_less(c, cols.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.selectColumns_1179", _mut74241, _mut74242, _mut74243, _mut74244, _mut74245); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.selectColumns_1179");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194");
        final double[][] d = new double[m.getRowDimension()][m.getColumnDimension()];
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194", _mut74265, _mut74266, _mut74267, _mut74268, _mut74269); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194", _mut74260, _mut74261, _mut74262, _mut74263, _mut74264); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194");
                d[r][c] = ROR_less_equals(r, AOR_minus(c, k, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194", _mut74251, _mut74252, _mut74253, _mut74254), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.triu_1194", _mut74255, _mut74256, _mut74257, _mut74258, _mut74259) ? m.getEntry(r, c) : 0;
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }

    /**
     * @param m Input matrix.
     * @return Row matrix representing the sums of the rows.
     */
    private static RealMatrix sumRows(final RealMatrix m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sumRows_1208");
        final double[][] d = new double[1][m.getColumnDimension()];
        for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sumRows_1208", _mut74275, _mut74276, _mut74277, _mut74278, _mut74279); c++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sumRows_1208");
            double sum = 0;
            for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sumRows_1208", _mut74270, _mut74271, _mut74272, _mut74273, _mut74274); r++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sumRows_1208");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225");
        if (ROR_equals(m.getColumnDimension(), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225", _mut74280, _mut74281, _mut74282, _mut74283, _mut74284)) {
            final double[][] d = new double[m.getRowDimension()][m.getRowDimension()];
            for (int i = 0; ROR_less(i, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225", _mut74290, _mut74291, _mut74292, _mut74293, _mut74294); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225");
                d[i][i] = m.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(d, false);
        } else {
            final double[][] d = new double[m.getRowDimension()][1];
            for (int i = 0; ROR_less(i, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225", _mut74285, _mut74286, _mut74287, _mut74288, _mut74289); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.diag_1225");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.copyColumn_1249");
        for (int i = 0; ROR_less(i, m1.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.copyColumn_1249", _mut74295, _mut74296, _mut74297, _mut74298, _mut74299); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.copyColumn_1249");
            m2.setEntry(i, col2, m1.getEntry(i, col1));
        }
    }

    /**
     * @param n Number of rows.
     * @param m Number of columns.
     * @return n-by-m matrix filled with 1.
     */
    private static RealMatrix ones(int n, int m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.ones_1261");
        final double[][] d = new double[n][m];
        for (int r = 0; ROR_less(r, n, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.ones_1261", _mut74300, _mut74301, _mut74302, _mut74303, _mut74304); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.ones_1261");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.eye_1275");
        final double[][] d = new double[n][m];
        for (int r = 0; ROR_less(r, n, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.eye_1275", _mut74310, _mut74311, _mut74312, _mut74313, _mut74314); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.eye_1275");
            if (ROR_less(r, m, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.eye_1275", _mut74305, _mut74306, _mut74307, _mut74308, _mut74309)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300");
        final int rd = mat.getRowDimension();
        final int cd = mat.getColumnDimension();
        final double[][] d = new double[AOR_multiply(n, rd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74315, _mut74316, _mut74317, _mut74318)][AOR_multiply(m, cd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74319, _mut74320, _mut74321, _mut74322)];
        for (int r = 0; ROR_less(r, AOR_multiply(n, rd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74340, _mut74341, _mut74342, _mut74343), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74344, _mut74345, _mut74346, _mut74347, _mut74348); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300");
            for (int c = 0; ROR_less(c, AOR_multiply(m, cd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74331, _mut74332, _mut74333, _mut74334), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74335, _mut74336, _mut74337, _mut74338, _mut74339); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300");
                d[r][c] = mat.getEntry(AOR_remainder(r, rd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74323, _mut74324, _mut74325, _mut74326), AOR_remainder(c, cd, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.repmat_1300", _mut74327, _mut74328, _mut74329, _mut74330));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318");
        final int size = (int) (AOR_plus(AOR_divide((AOR_minus(end, start, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318", _mut74349, _mut74350, _mut74351, _mut74352)), step, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318", _mut74353, _mut74354, _mut74355, _mut74356), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318", _mut74357, _mut74358, _mut74359, _mut74360));
        final double[][] d = new double[size][1];
        double value = start;
        for (int r = 0; ROR_less(r, size, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318", _mut74361, _mut74362, _mut74363, _mut74364, _mut74365); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.sequence_1318");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333");
        double max = -Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333", _mut74376, _mut74377, _mut74378, _mut74379, _mut74380); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333", _mut74371, _mut74372, _mut74373, _mut74374, _mut74375); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333");
                double e = m.getEntry(r, c);
                if (ROR_less(max, e, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1333", _mut74366, _mut74367, _mut74368, _mut74369, _mut74370)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350");
        double min = Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.getRowDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350", _mut74391, _mut74392, _mut74393, _mut74394, _mut74395); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350");
            for (int c = 0; ROR_less(c, m.getColumnDimension(), "org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350", _mut74386, _mut74387, _mut74388, _mut74389, _mut74390); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350");
                double e = m.getEntry(r, c);
                if (ROR_greater(min, e, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1350", _mut74381, _mut74382, _mut74383, _mut74384, _mut74385)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1367");
        double max = -Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1367", _mut74401, _mut74402, _mut74403, _mut74404, _mut74405); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1367");
            if (ROR_less(max, m[r], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.max_1367", _mut74396, _mut74397, _mut74398, _mut74399, _mut74400)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1381");
        double min = Double.MAX_VALUE;
        for (int r = 0; ROR_less(r, m.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1381", _mut74411, _mut74412, _mut74413, _mut74414, _mut74415); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1381");
            if (ROR_greater(min, m[r], "org.apache.commons.math3.optimization.direct.CMAESOptimizer.min_1381", _mut74406, _mut74407, _mut74408, _mut74409, _mut74410)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.inverse_1395");
        final int[] inverse = new int[indices.length];
        for (int i = 0; ROR_less(i, indices.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.inverse_1395", _mut74416, _mut74417, _mut74418, _mut74419, _mut74420); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.inverse_1395");
            inverse[indices[i]] = i;
        }
        return inverse;
    }

    /**
     * @param indices Input index array.
     * @return the indices in inverse order (last is first).
     */
    private static int[] reverse(final int[] indices) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.reverse_1407");
        final int[] reverse = new int[indices.length];
        for (int i = 0; ROR_less(i, indices.length, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.reverse_1407", _mut74429, _mut74430, _mut74431, _mut74432, _mut74433); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.reverse_1407");
            reverse[i] = indices[AOR_minus(AOR_minus(indices.length, i, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.reverse_1407", _mut74421, _mut74422, _mut74423, _mut74424), 1, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.reverse_1407", _mut74425, _mut74426, _mut74427, _mut74428)];
        }
        return reverse;
    }

    /**
     * @param size Length of random array.
     * @return an array of Gaussian random numbers.
     */
    private double[] randn(int size) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn_1419");
        final double[] randn = new double[size];
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn_1419", _mut74434, _mut74435, _mut74436, _mut74437, _mut74438); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn_1419");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn1_1432");
        final double[][] d = new double[size][popSize];
        for (int r = 0; ROR_less(r, size, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn1_1432", _mut74444, _mut74445, _mut74446, _mut74447, _mut74448); r++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn1_1432");
            for (int c = 0; ROR_less(c, popSize, "org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn1_1432", _mut74439, _mut74440, _mut74441, _mut74442, _mut74443); c++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.CMAESOptimizer.randn1_1432");
                d[r][c] = random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(d, false);
    }
}
