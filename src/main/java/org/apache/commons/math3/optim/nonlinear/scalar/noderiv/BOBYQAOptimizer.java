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
// CHECKSTYLE: stop all
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Powell's BOBYQA algorithm. This implementation is translated and
 * adapted from the Fortran version available
 * <a href="http://plato.asu.edu/ftp/other_software/bobyqa.zip">here</a>.
 * See <a href="http://www.optimization-online.org/DB_HTML/2010/05/2616.html">
 * this paper</a> for an introduction.
 * <br/>
 * BOBYQA is particularly well suited for high dimensional problems
 * where derivatives are not available. In most cases it outperforms the
 * {@link PowellOptimizer} significantly. Stochastic algorithms like
 * {@link CMAESOptimizer} succeed more often than BOBYQA, but are more
 * expensive. BOBYQA could also be considered as a replacement of any
 * derivative-based optimizer when the derivatives are approximated by
 * finite differences.
 *
 * @since 3.0
 */
public class BOBYQAOptimizer extends MultivariateOptimizer {

    @Conditional
    public static boolean _mut61914 = false, _mut61915 = false, _mut61916 = false, _mut61917 = false, _mut61918 = false, _mut61919 = false, _mut61920 = false, _mut61921 = false, _mut61922 = false, _mut61923 = false, _mut61924 = false, _mut61925 = false, _mut61926 = false, _mut61927 = false, _mut61928 = false, _mut61929 = false, _mut61930 = false, _mut61931 = false, _mut61932 = false, _mut61933 = false, _mut61934 = false, _mut61935 = false, _mut61936 = false, _mut61937 = false, _mut61938 = false, _mut61939 = false, _mut61940 = false, _mut61941 = false, _mut61942 = false, _mut61943 = false, _mut61944 = false, _mut61945 = false, _mut61946 = false, _mut61947 = false, _mut61948 = false, _mut61949 = false, _mut61950 = false, _mut61951 = false, _mut61952 = false, _mut61953 = false, _mut61954 = false, _mut61955 = false, _mut61956 = false, _mut61957 = false, _mut61958 = false, _mut61959 = false, _mut61960 = false, _mut61961 = false, _mut61962 = false, _mut61963 = false, _mut61964 = false, _mut61965 = false, _mut61966 = false, _mut61967 = false, _mut61968 = false, _mut61969 = false, _mut61970 = false, _mut61971 = false, _mut61972 = false, _mut61973 = false, _mut61974 = false, _mut61975 = false, _mut61976 = false, _mut61977 = false, _mut61978 = false, _mut61979 = false, _mut61980 = false, _mut61981 = false, _mut61982 = false, _mut61983 = false, _mut61984 = false, _mut61985 = false, _mut61986 = false, _mut61987 = false, _mut61988 = false, _mut61989 = false, _mut61990 = false, _mut61991 = false, _mut61992 = false, _mut61993 = false, _mut61994 = false, _mut61995 = false, _mut61996 = false, _mut61997 = false, _mut61998 = false, _mut61999 = false, _mut62000 = false, _mut62001 = false, _mut62002 = false, _mut62003 = false, _mut62004 = false, _mut62005 = false, _mut62006 = false, _mut62007 = false, _mut62008 = false, _mut62009 = false, _mut62010 = false, _mut62011 = false, _mut62012 = false, _mut62013 = false, _mut62014 = false, _mut62015 = false, _mut62016 = false, _mut62017 = false, _mut62018 = false, _mut62019 = false, _mut62020 = false, _mut62021 = false, _mut62022 = false, _mut62023 = false, _mut62024 = false, _mut62025 = false, _mut62026 = false, _mut62027 = false, _mut62028 = false, _mut62029 = false, _mut62030 = false, _mut62031 = false, _mut62032 = false, _mut62033 = false, _mut62034 = false, _mut62035 = false, _mut62036 = false, _mut62037 = false, _mut62038 = false, _mut62039 = false, _mut62040 = false, _mut62041 = false, _mut62042 = false, _mut62043 = false, _mut62044 = false, _mut62045 = false, _mut62046 = false, _mut62047 = false, _mut62048 = false, _mut62049 = false, _mut62050 = false, _mut62051 = false, _mut62052 = false, _mut62053 = false, _mut62054 = false, _mut62055 = false, _mut62056 = false, _mut62057 = false, _mut62058 = false, _mut62059 = false, _mut62060 = false, _mut62061 = false, _mut62062 = false, _mut62063 = false, _mut62064 = false, _mut62065 = false, _mut62066 = false, _mut62067 = false, _mut62068 = false, _mut62069 = false, _mut62070 = false, _mut62071 = false, _mut62072 = false, _mut62073 = false, _mut62074 = false, _mut62075 = false, _mut62076 = false, _mut62077 = false, _mut62078 = false, _mut62079 = false, _mut62080 = false, _mut62081 = false, _mut62082 = false, _mut62083 = false, _mut62084 = false, _mut62085 = false, _mut62086 = false, _mut62087 = false, _mut62088 = false, _mut62089 = false, _mut62090 = false, _mut62091 = false, _mut62092 = false, _mut62093 = false, _mut62094 = false, _mut62095 = false, _mut62096 = false, _mut62097 = false, _mut62098 = false, _mut62099 = false, _mut62100 = false, _mut62101 = false, _mut62102 = false, _mut62103 = false, _mut62104 = false, _mut62105 = false, _mut62106 = false, _mut62107 = false, _mut62108 = false, _mut62109 = false, _mut62110 = false, _mut62111 = false, _mut62112 = false, _mut62113 = false, _mut62114 = false, _mut62115 = false, _mut62116 = false, _mut62117 = false, _mut62118 = false, _mut62119 = false, _mut62120 = false, _mut62121 = false, _mut62122 = false, _mut62123 = false, _mut62124 = false, _mut62125 = false, _mut62126 = false, _mut62127 = false, _mut62128 = false, _mut62129 = false, _mut62130 = false, _mut62131 = false, _mut62132 = false, _mut62133 = false, _mut62134 = false, _mut62135 = false, _mut62136 = false, _mut62137 = false, _mut62138 = false, _mut62139 = false, _mut62140 = false, _mut62141 = false, _mut62142 = false, _mut62143 = false, _mut62144 = false, _mut62145 = false, _mut62146 = false, _mut62147 = false, _mut62148 = false, _mut62149 = false, _mut62150 = false, _mut62151 = false, _mut62152 = false, _mut62153 = false, _mut62154 = false, _mut62155 = false, _mut62156 = false, _mut62157 = false, _mut62158 = false, _mut62159 = false, _mut62160 = false, _mut62161 = false, _mut62162 = false, _mut62163 = false, _mut62164 = false, _mut62165 = false, _mut62166 = false, _mut62167 = false, _mut62168 = false, _mut62169 = false, _mut62170 = false, _mut62171 = false, _mut62172 = false, _mut62173 = false, _mut62174 = false, _mut62175 = false, _mut62176 = false, _mut62177 = false, _mut62178 = false, _mut62179 = false, _mut62180 = false, _mut62181 = false, _mut62182 = false, _mut62183 = false, _mut62184 = false, _mut62185 = false, _mut62186 = false, _mut62187 = false, _mut62188 = false, _mut62189 = false, _mut62190 = false, _mut62191 = false, _mut62192 = false, _mut62193 = false, _mut62194 = false, _mut62195 = false, _mut62196 = false, _mut62197 = false, _mut62198 = false, _mut62199 = false, _mut62200 = false, _mut62201 = false, _mut62202 = false, _mut62203 = false, _mut62204 = false, _mut62205 = false, _mut62206 = false, _mut62207 = false, _mut62208 = false, _mut62209 = false, _mut62210 = false, _mut62211 = false, _mut62212 = false, _mut62213 = false, _mut62214 = false, _mut62215 = false, _mut62216 = false, _mut62217 = false, _mut62218 = false, _mut62219 = false, _mut62220 = false, _mut62221 = false, _mut62222 = false, _mut62223 = false, _mut62224 = false, _mut62225 = false, _mut62226 = false, _mut62227 = false, _mut62228 = false, _mut62229 = false, _mut62230 = false, _mut62231 = false, _mut62232 = false, _mut62233 = false, _mut62234 = false, _mut62235 = false, _mut62236 = false, _mut62237 = false, _mut62238 = false, _mut62239 = false, _mut62240 = false, _mut62241 = false, _mut62242 = false, _mut62243 = false, _mut62244 = false, _mut62245 = false, _mut62246 = false, _mut62247 = false, _mut62248 = false, _mut62249 = false, _mut62250 = false, _mut62251 = false, _mut62252 = false, _mut62253 = false, _mut62254 = false, _mut62255 = false, _mut62256 = false, _mut62257 = false, _mut62258 = false, _mut62259 = false, _mut62260 = false, _mut62261 = false, _mut62262 = false, _mut62263 = false, _mut62264 = false, _mut62265 = false, _mut62266 = false, _mut62267 = false, _mut62268 = false, _mut62269 = false, _mut62270 = false, _mut62271 = false, _mut62272 = false, _mut62273 = false, _mut62274 = false, _mut62275 = false, _mut62276 = false, _mut62277 = false, _mut62278 = false, _mut62279 = false, _mut62280 = false, _mut62281 = false, _mut62282 = false, _mut62283 = false, _mut62284 = false, _mut62285 = false, _mut62286 = false, _mut62287 = false, _mut62288 = false, _mut62289 = false, _mut62290 = false, _mut62291 = false, _mut62292 = false, _mut62293 = false, _mut62294 = false, _mut62295 = false, _mut62296 = false, _mut62297 = false, _mut62298 = false, _mut62299 = false, _mut62300 = false, _mut62301 = false, _mut62302 = false, _mut62303 = false, _mut62304 = false, _mut62305 = false, _mut62306 = false, _mut62307 = false, _mut62308 = false, _mut62309 = false, _mut62310 = false, _mut62311 = false, _mut62312 = false, _mut62313 = false, _mut62314 = false, _mut62315 = false, _mut62316 = false, _mut62317 = false, _mut62318 = false, _mut62319 = false, _mut62320 = false, _mut62321 = false, _mut62322 = false, _mut62323 = false, _mut62324 = false, _mut62325 = false, _mut62326 = false, _mut62327 = false, _mut62328 = false, _mut62329 = false, _mut62330 = false, _mut62331 = false, _mut62332 = false, _mut62333 = false, _mut62334 = false, _mut62335 = false, _mut62336 = false, _mut62337 = false, _mut62338 = false, _mut62339 = false, _mut62340 = false, _mut62341 = false, _mut62342 = false, _mut62343 = false, _mut62344 = false, _mut62345 = false, _mut62346 = false, _mut62347 = false, _mut62348 = false, _mut62349 = false, _mut62350 = false, _mut62351 = false, _mut62352 = false, _mut62353 = false, _mut62354 = false, _mut62355 = false, _mut62356 = false, _mut62357 = false, _mut62358 = false, _mut62359 = false, _mut62360 = false, _mut62361 = false, _mut62362 = false, _mut62363 = false, _mut62364 = false, _mut62365 = false, _mut62366 = false, _mut62367 = false, _mut62368 = false, _mut62369 = false, _mut62370 = false, _mut62371 = false, _mut62372 = false, _mut62373 = false, _mut62374 = false, _mut62375 = false, _mut62376 = false, _mut62377 = false, _mut62378 = false, _mut62379 = false, _mut62380 = false, _mut62381 = false, _mut62382 = false, _mut62383 = false, _mut62384 = false, _mut62385 = false, _mut62386 = false, _mut62387 = false, _mut62388 = false, _mut62389 = false, _mut62390 = false, _mut62391 = false, _mut62392 = false, _mut62393 = false, _mut62394 = false, _mut62395 = false, _mut62396 = false, _mut62397 = false, _mut62398 = false, _mut62399 = false, _mut62400 = false, _mut62401 = false, _mut62402 = false, _mut62403 = false, _mut62404 = false, _mut62405 = false, _mut62406 = false, _mut62407 = false, _mut62408 = false, _mut62409 = false, _mut62410 = false, _mut62411 = false, _mut62412 = false, _mut62413 = false, _mut62414 = false, _mut62415 = false, _mut62416 = false, _mut62417 = false, _mut62418 = false, _mut62419 = false, _mut62420 = false, _mut62421 = false, _mut62422 = false, _mut62423 = false, _mut62424 = false, _mut62425 = false, _mut62426 = false, _mut62427 = false, _mut62428 = false, _mut62429 = false, _mut62430 = false, _mut62431 = false, _mut62432 = false, _mut62433 = false, _mut62434 = false, _mut62435 = false, _mut62436 = false, _mut62437 = false, _mut62438 = false, _mut62439 = false, _mut62440 = false, _mut62441 = false, _mut62442 = false, _mut62443 = false, _mut62444 = false, _mut62445 = false, _mut62446 = false, _mut62447 = false, _mut62448 = false, _mut62449 = false, _mut62450 = false, _mut62451 = false, _mut62452 = false, _mut62453 = false, _mut62454 = false, _mut62455 = false, _mut62456 = false, _mut62457 = false, _mut62458 = false, _mut62459 = false, _mut62460 = false, _mut62461 = false, _mut62462 = false, _mut62463 = false, _mut62464 = false, _mut62465 = false, _mut62466 = false, _mut62467 = false, _mut62468 = false, _mut62469 = false, _mut62470 = false, _mut62471 = false, _mut62472 = false, _mut62473 = false, _mut62474 = false, _mut62475 = false, _mut62476 = false, _mut62477 = false, _mut62478 = false, _mut62479 = false, _mut62480 = false, _mut62481 = false, _mut62482 = false, _mut62483 = false, _mut62484 = false, _mut62485 = false, _mut62486 = false, _mut62487 = false, _mut62488 = false, _mut62489 = false, _mut62490 = false, _mut62491 = false, _mut62492 = false, _mut62493 = false, _mut62494 = false, _mut62495 = false, _mut62496 = false, _mut62497 = false, _mut62498 = false, _mut62499 = false, _mut62500 = false, _mut62501 = false, _mut62502 = false, _mut62503 = false, _mut62504 = false, _mut62505 = false, _mut62506 = false, _mut62507 = false, _mut62508 = false, _mut62509 = false, _mut62510 = false, _mut62511 = false, _mut62512 = false, _mut62513 = false, _mut62514 = false, _mut62515 = false, _mut62516 = false, _mut62517 = false, _mut62518 = false, _mut62519 = false, _mut62520 = false, _mut62521 = false, _mut62522 = false, _mut62523 = false, _mut62524 = false, _mut62525 = false, _mut62526 = false, _mut62527 = false, _mut62528 = false, _mut62529 = false, _mut62530 = false, _mut62531 = false, _mut62532 = false, _mut62533 = false, _mut62534 = false, _mut62535 = false, _mut62536 = false, _mut62537 = false, _mut62538 = false, _mut62539 = false, _mut62540 = false, _mut62541 = false, _mut62542 = false, _mut62543 = false, _mut62544 = false, _mut62545 = false, _mut62546 = false, _mut62547 = false, _mut62548 = false, _mut62549 = false, _mut62550 = false, _mut62551 = false, _mut62552 = false, _mut62553 = false, _mut62554 = false, _mut62555 = false, _mut62556 = false, _mut62557 = false, _mut62558 = false, _mut62559 = false, _mut62560 = false, _mut62561 = false, _mut62562 = false, _mut62563 = false, _mut62564 = false, _mut62565 = false, _mut62566 = false, _mut62567 = false, _mut62568 = false, _mut62569 = false, _mut62570 = false, _mut62571 = false, _mut62572 = false, _mut62573 = false, _mut62574 = false, _mut62575 = false, _mut62576 = false, _mut62577 = false, _mut62578 = false, _mut62579 = false, _mut62580 = false, _mut62581 = false, _mut62582 = false, _mut62583 = false, _mut62584 = false, _mut62585 = false, _mut62586 = false, _mut62587 = false, _mut62588 = false, _mut62589 = false, _mut62590 = false, _mut62591 = false, _mut62592 = false, _mut62593 = false, _mut62594 = false, _mut62595 = false, _mut62596 = false, _mut62597 = false, _mut62598 = false, _mut62599 = false, _mut62600 = false, _mut62601 = false, _mut62602 = false, _mut62603 = false, _mut62604 = false, _mut62605 = false, _mut62606 = false, _mut62607 = false, _mut62608 = false, _mut62609 = false, _mut62610 = false, _mut62611 = false, _mut62612 = false, _mut62613 = false, _mut62614 = false, _mut62615 = false, _mut62616 = false, _mut62617 = false, _mut62618 = false, _mut62619 = false, _mut62620 = false, _mut62621 = false, _mut62622 = false, _mut62623 = false, _mut62624 = false, _mut62625 = false, _mut62626 = false, _mut62627 = false, _mut62628 = false, _mut62629 = false, _mut62630 = false, _mut62631 = false, _mut62632 = false, _mut62633 = false, _mut62634 = false, _mut62635 = false, _mut62636 = false, _mut62637 = false, _mut62638 = false, _mut62639 = false, _mut62640 = false, _mut62641 = false, _mut62642 = false, _mut62643 = false, _mut62644 = false, _mut62645 = false, _mut62646 = false, _mut62647 = false, _mut62648 = false, _mut62649 = false, _mut62650 = false, _mut62651 = false, _mut62652 = false, _mut62653 = false, _mut62654 = false, _mut62655 = false, _mut62656 = false, _mut62657 = false, _mut62658 = false, _mut62659 = false, _mut62660 = false, _mut62661 = false, _mut62662 = false, _mut62663 = false, _mut62664 = false, _mut62665 = false, _mut62666 = false, _mut62667 = false, _mut62668 = false, _mut62669 = false, _mut62670 = false, _mut62671 = false, _mut62672 = false, _mut62673 = false, _mut62674 = false, _mut62675 = false, _mut62676 = false, _mut62677 = false, _mut62678 = false, _mut62679 = false, _mut62680 = false, _mut62681 = false, _mut62682 = false, _mut62683 = false, _mut62684 = false, _mut62685 = false, _mut62686 = false, _mut62687 = false, _mut62688 = false, _mut62689 = false, _mut62690 = false, _mut62691 = false, _mut62692 = false, _mut62693 = false, _mut62694 = false, _mut62695 = false, _mut62696 = false, _mut62697 = false, _mut62698 = false, _mut62699 = false, _mut62700 = false, _mut62701 = false, _mut62702 = false, _mut62703 = false, _mut62704 = false, _mut62705 = false, _mut62706 = false, _mut62707 = false, _mut62708 = false, _mut62709 = false, _mut62710 = false, _mut62711 = false, _mut62712 = false, _mut62713 = false, _mut62714 = false, _mut62715 = false, _mut62716 = false, _mut62717 = false, _mut62718 = false, _mut62719 = false, _mut62720 = false, _mut62721 = false, _mut62722 = false, _mut62723 = false, _mut62724 = false, _mut62725 = false, _mut62726 = false, _mut62727 = false, _mut62728 = false, _mut62729 = false, _mut62730 = false, _mut62731 = false, _mut62732 = false, _mut62733 = false, _mut62734 = false, _mut62735 = false, _mut62736 = false, _mut62737 = false, _mut62738 = false, _mut62739 = false, _mut62740 = false, _mut62741 = false, _mut62742 = false, _mut62743 = false, _mut62744 = false, _mut62745 = false, _mut62746 = false, _mut62747 = false, _mut62748 = false, _mut62749 = false, _mut62750 = false, _mut62751 = false, _mut62752 = false, _mut62753 = false, _mut62754 = false, _mut62755 = false, _mut62756 = false, _mut62757 = false, _mut62758 = false, _mut62759 = false, _mut62760 = false, _mut62761 = false, _mut62762 = false, _mut62763 = false, _mut62764 = false, _mut62765 = false, _mut62766 = false, _mut62767 = false, _mut62768 = false, _mut62769 = false, _mut62770 = false, _mut62771 = false, _mut62772 = false, _mut62773 = false, _mut62774 = false, _mut62775 = false, _mut62776 = false, _mut62777 = false, _mut62778 = false, _mut62779 = false, _mut62780 = false, _mut62781 = false, _mut62782 = false, _mut62783 = false, _mut62784 = false, _mut62785 = false, _mut62786 = false, _mut62787 = false, _mut62788 = false, _mut62789 = false, _mut62790 = false, _mut62791 = false, _mut62792 = false, _mut62793 = false, _mut62794 = false, _mut62795 = false, _mut62796 = false, _mut62797 = false, _mut62798 = false, _mut62799 = false, _mut62800 = false, _mut62801 = false, _mut62802 = false, _mut62803 = false, _mut62804 = false, _mut62805 = false, _mut62806 = false, _mut62807 = false, _mut62808 = false, _mut62809 = false, _mut62810 = false, _mut62811 = false, _mut62812 = false, _mut62813 = false, _mut62814 = false, _mut62815 = false, _mut62816 = false, _mut62817 = false, _mut62818 = false, _mut62819 = false, _mut62820 = false, _mut62821 = false, _mut62822 = false, _mut62823 = false, _mut62824 = false, _mut62825 = false, _mut62826 = false, _mut62827 = false, _mut62828 = false, _mut62829 = false, _mut62830 = false, _mut62831 = false, _mut62832 = false, _mut62833 = false, _mut62834 = false, _mut62835 = false, _mut62836 = false, _mut62837 = false, _mut62838 = false, _mut62839 = false, _mut62840 = false, _mut62841 = false, _mut62842 = false, _mut62843 = false, _mut62844 = false, _mut62845 = false, _mut62846 = false, _mut62847 = false, _mut62848 = false, _mut62849 = false, _mut62850 = false, _mut62851 = false, _mut62852 = false, _mut62853 = false, _mut62854 = false, _mut62855 = false, _mut62856 = false, _mut62857 = false, _mut62858 = false, _mut62859 = false, _mut62860 = false, _mut62861 = false, _mut62862 = false, _mut62863 = false, _mut62864 = false, _mut62865 = false, _mut62866 = false, _mut62867 = false, _mut62868 = false, _mut62869 = false, _mut62870 = false, _mut62871 = false, _mut62872 = false, _mut62873 = false, _mut62874 = false, _mut62875 = false, _mut62876 = false, _mut62877 = false, _mut62878 = false, _mut62879 = false, _mut62880 = false, _mut62881 = false, _mut62882 = false, _mut62883 = false, _mut62884 = false, _mut62885 = false, _mut62886 = false, _mut62887 = false, _mut62888 = false, _mut62889 = false, _mut62890 = false, _mut62891 = false, _mut62892 = false, _mut62893 = false, _mut62894 = false, _mut62895 = false, _mut62896 = false, _mut62897 = false, _mut62898 = false, _mut62899 = false, _mut62900 = false, _mut62901 = false, _mut62902 = false, _mut62903 = false, _mut62904 = false, _mut62905 = false, _mut62906 = false, _mut62907 = false, _mut62908 = false, _mut62909 = false, _mut62910 = false, _mut62911 = false, _mut62912 = false, _mut62913 = false, _mut62914 = false, _mut62915 = false, _mut62916 = false, _mut62917 = false, _mut62918 = false, _mut62919 = false, _mut62920 = false, _mut62921 = false, _mut62922 = false, _mut62923 = false, _mut62924 = false, _mut62925 = false, _mut62926 = false, _mut62927 = false, _mut62928 = false, _mut62929 = false, _mut62930 = false, _mut62931 = false, _mut62932 = false, _mut62933 = false, _mut62934 = false, _mut62935 = false, _mut62936 = false, _mut62937 = false, _mut62938 = false, _mut62939 = false, _mut62940 = false, _mut62941 = false, _mut62942 = false, _mut62943 = false, _mut62944 = false, _mut62945 = false, _mut62946 = false, _mut62947 = false, _mut62948 = false, _mut62949 = false, _mut62950 = false, _mut62951 = false, _mut62952 = false, _mut62953 = false, _mut62954 = false, _mut62955 = false, _mut62956 = false, _mut62957 = false, _mut62958 = false, _mut62959 = false, _mut62960 = false, _mut62961 = false, _mut62962 = false, _mut62963 = false, _mut62964 = false, _mut62965 = false, _mut62966 = false, _mut62967 = false, _mut62968 = false, _mut62969 = false, _mut62970 = false, _mut62971 = false, _mut62972 = false, _mut62973 = false, _mut62974 = false, _mut62975 = false, _mut62976 = false, _mut62977 = false, _mut62978 = false, _mut62979 = false, _mut62980 = false, _mut62981 = false, _mut62982 = false, _mut62983 = false, _mut62984 = false, _mut62985 = false, _mut62986 = false, _mut62987 = false, _mut62988 = false, _mut62989 = false, _mut62990 = false, _mut62991 = false, _mut62992 = false, _mut62993 = false, _mut62994 = false, _mut62995 = false, _mut62996 = false, _mut62997 = false, _mut62998 = false, _mut62999 = false, _mut63000 = false, _mut63001 = false, _mut63002 = false, _mut63003 = false, _mut63004 = false, _mut63005 = false, _mut63006 = false, _mut63007 = false, _mut63008 = false, _mut63009 = false, _mut63010 = false, _mut63011 = false, _mut63012 = false, _mut63013 = false, _mut63014 = false, _mut63015 = false, _mut63016 = false, _mut63017 = false, _mut63018 = false, _mut63019 = false, _mut63020 = false, _mut63021 = false, _mut63022 = false, _mut63023 = false, _mut63024 = false, _mut63025 = false, _mut63026 = false, _mut63027 = false, _mut63028 = false, _mut63029 = false, _mut63030 = false, _mut63031 = false, _mut63032 = false, _mut63033 = false, _mut63034 = false, _mut63035 = false, _mut63036 = false, _mut63037 = false, _mut63038 = false, _mut63039 = false, _mut63040 = false, _mut63041 = false, _mut63042 = false, _mut63043 = false, _mut63044 = false, _mut63045 = false, _mut63046 = false, _mut63047 = false, _mut63048 = false, _mut63049 = false, _mut63050 = false, _mut63051 = false, _mut63052 = false, _mut63053 = false, _mut63054 = false, _mut63055 = false, _mut63056 = false, _mut63057 = false, _mut63058 = false, _mut63059 = false, _mut63060 = false, _mut63061 = false, _mut63062 = false, _mut63063 = false, _mut63064 = false, _mut63065 = false, _mut63066 = false, _mut63067 = false, _mut63068 = false, _mut63069 = false, _mut63070 = false, _mut63071 = false, _mut63072 = false, _mut63073 = false, _mut63074 = false, _mut63075 = false, _mut63076 = false, _mut63077 = false, _mut63078 = false, _mut63079 = false, _mut63080 = false, _mut63081 = false, _mut63082 = false, _mut63083 = false, _mut63084 = false, _mut63085 = false, _mut63086 = false, _mut63087 = false, _mut63088 = false, _mut63089 = false, _mut63090 = false, _mut63091 = false, _mut63092 = false, _mut63093 = false, _mut63094 = false, _mut63095 = false, _mut63096 = false, _mut63097 = false, _mut63098 = false, _mut63099 = false, _mut63100 = false, _mut63101 = false, _mut63102 = false, _mut63103 = false, _mut63104 = false, _mut63105 = false, _mut63106 = false, _mut63107 = false, _mut63108 = false, _mut63109 = false, _mut63110 = false, _mut63111 = false, _mut63112 = false, _mut63113 = false, _mut63114 = false, _mut63115 = false, _mut63116 = false, _mut63117 = false, _mut63118 = false, _mut63119 = false, _mut63120 = false, _mut63121 = false, _mut63122 = false, _mut63123 = false, _mut63124 = false, _mut63125 = false, _mut63126 = false, _mut63127 = false, _mut63128 = false, _mut63129 = false, _mut63130 = false, _mut63131 = false, _mut63132 = false, _mut63133 = false, _mut63134 = false, _mut63135 = false, _mut63136 = false, _mut63137 = false, _mut63138 = false, _mut63139 = false, _mut63140 = false, _mut63141 = false, _mut63142 = false, _mut63143 = false, _mut63144 = false, _mut63145 = false, _mut63146 = false, _mut63147 = false, _mut63148 = false, _mut63149 = false, _mut63150 = false, _mut63151 = false, _mut63152 = false, _mut63153 = false, _mut63154 = false, _mut63155 = false, _mut63156 = false, _mut63157 = false, _mut63158 = false, _mut63159 = false, _mut63160 = false, _mut63161 = false, _mut63162 = false, _mut63163 = false, _mut63164 = false, _mut63165 = false, _mut63166 = false, _mut63167 = false, _mut63168 = false, _mut63169 = false, _mut63170 = false, _mut63171 = false, _mut63172 = false, _mut63173 = false, _mut63174 = false, _mut63175 = false, _mut63176 = false, _mut63177 = false, _mut63178 = false, _mut63179 = false, _mut63180 = false, _mut63181 = false, _mut63182 = false, _mut63183 = false, _mut63184 = false, _mut63185 = false, _mut63186 = false, _mut63187 = false, _mut63188 = false, _mut63189 = false, _mut63190 = false, _mut63191 = false, _mut63192 = false, _mut63193 = false, _mut63194 = false, _mut63195 = false, _mut63196 = false, _mut63197 = false, _mut63198 = false, _mut63199 = false, _mut63200 = false, _mut63201 = false, _mut63202 = false, _mut63203 = false, _mut63204 = false, _mut63205 = false, _mut63206 = false, _mut63207 = false, _mut63208 = false, _mut63209 = false, _mut63210 = false, _mut63211 = false, _mut63212 = false, _mut63213 = false, _mut63214 = false, _mut63215 = false, _mut63216 = false, _mut63217 = false, _mut63218 = false, _mut63219 = false, _mut63220 = false, _mut63221 = false, _mut63222 = false, _mut63223 = false, _mut63224 = false, _mut63225 = false, _mut63226 = false, _mut63227 = false, _mut63228 = false, _mut63229 = false, _mut63230 = false, _mut63231 = false, _mut63232 = false, _mut63233 = false, _mut63234 = false, _mut63235 = false, _mut63236 = false, _mut63237 = false, _mut63238 = false, _mut63239 = false, _mut63240 = false, _mut63241 = false, _mut63242 = false, _mut63243 = false, _mut63244 = false, _mut63245 = false, _mut63246 = false, _mut63247 = false, _mut63248 = false, _mut63249 = false, _mut63250 = false, _mut63251 = false, _mut63252 = false, _mut63253 = false, _mut63254 = false, _mut63255 = false, _mut63256 = false, _mut63257 = false, _mut63258 = false, _mut63259 = false, _mut63260 = false, _mut63261 = false, _mut63262 = false, _mut63263 = false, _mut63264 = false, _mut63265 = false, _mut63266 = false, _mut63267 = false, _mut63268 = false, _mut63269 = false, _mut63270 = false, _mut63271 = false, _mut63272 = false, _mut63273 = false, _mut63274 = false, _mut63275 = false, _mut63276 = false, _mut63277 = false, _mut63278 = false, _mut63279 = false, _mut63280 = false, _mut63281 = false, _mut63282 = false, _mut63283 = false, _mut63284 = false, _mut63285 = false, _mut63286 = false, _mut63287 = false, _mut63288 = false, _mut63289 = false, _mut63290 = false, _mut63291 = false, _mut63292 = false, _mut63293 = false, _mut63294 = false, _mut63295 = false, _mut63296 = false, _mut63297 = false, _mut63298 = false, _mut63299 = false, _mut63300 = false, _mut63301 = false, _mut63302 = false, _mut63303 = false, _mut63304 = false, _mut63305 = false, _mut63306 = false, _mut63307 = false, _mut63308 = false, _mut63309 = false, _mut63310 = false, _mut63311 = false, _mut63312 = false, _mut63313 = false, _mut63314 = false, _mut63315 = false, _mut63316 = false, _mut63317 = false, _mut63318 = false, _mut63319 = false, _mut63320 = false, _mut63321 = false, _mut63322 = false, _mut63323 = false, _mut63324 = false, _mut63325 = false, _mut63326 = false, _mut63327 = false, _mut63328 = false, _mut63329 = false, _mut63330 = false, _mut63331 = false, _mut63332 = false, _mut63333 = false, _mut63334 = false, _mut63335 = false, _mut63336 = false, _mut63337 = false, _mut63338 = false, _mut63339 = false, _mut63340 = false, _mut63341 = false, _mut63342 = false, _mut63343 = false, _mut63344 = false, _mut63345 = false, _mut63346 = false, _mut63347 = false, _mut63348 = false, _mut63349 = false, _mut63350 = false, _mut63351 = false, _mut63352 = false, _mut63353 = false, _mut63354 = false, _mut63355 = false, _mut63356 = false, _mut63357 = false, _mut63358 = false, _mut63359 = false, _mut63360 = false, _mut63361 = false, _mut63362 = false, _mut63363 = false, _mut63364 = false, _mut63365 = false, _mut63366 = false, _mut63367 = false, _mut63368 = false, _mut63369 = false, _mut63370 = false, _mut63371 = false, _mut63372 = false, _mut63373 = false, _mut63374 = false, _mut63375 = false, _mut63376 = false, _mut63377 = false, _mut63378 = false, _mut63379 = false, _mut63380 = false, _mut63381 = false, _mut63382 = false, _mut63383 = false, _mut63384 = false, _mut63385 = false, _mut63386 = false, _mut63387 = false, _mut63388 = false, _mut63389 = false, _mut63390 = false, _mut63391 = false, _mut63392 = false, _mut63393 = false, _mut63394 = false, _mut63395 = false, _mut63396 = false, _mut63397 = false, _mut63398 = false, _mut63399 = false, _mut63400 = false, _mut63401 = false, _mut63402 = false, _mut63403 = false, _mut63404 = false, _mut63405 = false, _mut63406 = false, _mut63407 = false, _mut63408 = false, _mut63409 = false, _mut63410 = false, _mut63411 = false, _mut63412 = false, _mut63413 = false, _mut63414 = false, _mut63415 = false, _mut63416 = false, _mut63417 = false, _mut63418 = false, _mut63419 = false, _mut63420 = false, _mut63421 = false, _mut63422 = false, _mut63423 = false, _mut63424 = false, _mut63425 = false, _mut63426 = false, _mut63427 = false, _mut63428 = false, _mut63429 = false, _mut63430 = false, _mut63431 = false, _mut63432 = false, _mut63433 = false, _mut63434 = false, _mut63435 = false, _mut63436 = false, _mut63437 = false, _mut63438 = false, _mut63439 = false, _mut63440 = false, _mut63441 = false, _mut63442 = false, _mut63443 = false, _mut63444 = false, _mut63445 = false, _mut63446 = false, _mut63447 = false, _mut63448 = false, _mut63449 = false, _mut63450 = false, _mut63451 = false, _mut63452 = false, _mut63453 = false, _mut63454 = false, _mut63455 = false, _mut63456 = false, _mut63457 = false, _mut63458 = false, _mut63459 = false, _mut63460 = false, _mut63461 = false, _mut63462 = false, _mut63463 = false, _mut63464 = false, _mut63465 = false, _mut63466 = false, _mut63467 = false, _mut63468 = false, _mut63469 = false, _mut63470 = false, _mut63471 = false, _mut63472 = false, _mut63473 = false, _mut63474 = false, _mut63475 = false, _mut63476 = false, _mut63477 = false, _mut63478 = false, _mut63479 = false, _mut63480 = false, _mut63481 = false, _mut63482 = false, _mut63483 = false, _mut63484 = false, _mut63485 = false, _mut63486 = false, _mut63487 = false, _mut63488 = false, _mut63489 = false, _mut63490 = false, _mut63491 = false, _mut63492 = false, _mut63493 = false, _mut63494 = false, _mut63495 = false, _mut63496 = false, _mut63497 = false, _mut63498 = false, _mut63499 = false, _mut63500 = false, _mut63501 = false, _mut63502 = false, _mut63503 = false, _mut63504 = false, _mut63505 = false, _mut63506 = false, _mut63507 = false, _mut63508 = false, _mut63509 = false, _mut63510 = false, _mut63511 = false, _mut63512 = false, _mut63513 = false, _mut63514 = false, _mut63515 = false, _mut63516 = false, _mut63517 = false, _mut63518 = false, _mut63519 = false, _mut63520 = false, _mut63521 = false, _mut63522 = false, _mut63523 = false, _mut63524 = false, _mut63525 = false, _mut63526 = false, _mut63527 = false, _mut63528 = false, _mut63529 = false, _mut63530 = false, _mut63531 = false, _mut63532 = false, _mut63533 = false, _mut63534 = false, _mut63535 = false, _mut63536 = false, _mut63537 = false, _mut63538 = false, _mut63539 = false, _mut63540 = false, _mut63541 = false, _mut63542 = false, _mut63543 = false, _mut63544 = false, _mut63545 = false, _mut63546 = false, _mut63547 = false, _mut63548 = false, _mut63549 = false, _mut63550 = false, _mut63551 = false, _mut63552 = false, _mut63553 = false, _mut63554 = false, _mut63555 = false, _mut63556 = false, _mut63557 = false, _mut63558 = false, _mut63559 = false, _mut63560 = false, _mut63561 = false, _mut63562 = false, _mut63563 = false, _mut63564 = false, _mut63565 = false, _mut63566 = false, _mut63567 = false, _mut63568 = false, _mut63569 = false, _mut63570 = false, _mut63571 = false, _mut63572 = false, _mut63573 = false, _mut63574 = false, _mut63575 = false, _mut63576 = false, _mut63577 = false, _mut63578 = false, _mut63579 = false, _mut63580 = false, _mut63581 = false, _mut63582 = false, _mut63583 = false, _mut63584 = false, _mut63585 = false, _mut63586 = false, _mut63587 = false, _mut63588 = false, _mut63589 = false, _mut63590 = false, _mut63591 = false, _mut63592 = false, _mut63593 = false, _mut63594 = false, _mut63595 = false, _mut63596 = false, _mut63597 = false, _mut63598 = false, _mut63599 = false, _mut63600 = false, _mut63601 = false, _mut63602 = false, _mut63603 = false, _mut63604 = false, _mut63605 = false, _mut63606 = false, _mut63607 = false, _mut63608 = false, _mut63609 = false, _mut63610 = false, _mut63611 = false, _mut63612 = false, _mut63613 = false, _mut63614 = false, _mut63615 = false, _mut63616 = false, _mut63617 = false, _mut63618 = false, _mut63619 = false, _mut63620 = false, _mut63621 = false, _mut63622 = false, _mut63623 = false, _mut63624 = false, _mut63625 = false, _mut63626 = false, _mut63627 = false, _mut63628 = false, _mut63629 = false, _mut63630 = false, _mut63631 = false, _mut63632 = false, _mut63633 = false, _mut63634 = false, _mut63635 = false, _mut63636 = false, _mut63637 = false, _mut63638 = false, _mut63639 = false, _mut63640 = false, _mut63641 = false, _mut63642 = false, _mut63643 = false, _mut63644 = false, _mut63645 = false, _mut63646 = false, _mut63647 = false, _mut63648 = false, _mut63649 = false, _mut63650 = false, _mut63651 = false, _mut63652 = false, _mut63653 = false, _mut63654 = false, _mut63655 = false, _mut63656 = false, _mut63657 = false, _mut63658 = false, _mut63659 = false, _mut63660 = false, _mut63661 = false, _mut63662 = false, _mut63663 = false, _mut63664 = false, _mut63665 = false, _mut63666 = false, _mut63667 = false, _mut63668 = false, _mut63669 = false, _mut63670 = false, _mut63671 = false, _mut63672 = false, _mut63673 = false, _mut63674 = false, _mut63675 = false, _mut63676 = false, _mut63677 = false, _mut63678 = false, _mut63679 = false, _mut63680 = false, _mut63681 = false, _mut63682 = false, _mut63683 = false, _mut63684 = false, _mut63685 = false, _mut63686 = false, _mut63687 = false, _mut63688 = false, _mut63689 = false, _mut63690 = false, _mut63691 = false, _mut63692 = false, _mut63693 = false, _mut63694 = false, _mut63695 = false, _mut63696 = false, _mut63697 = false, _mut63698 = false, _mut63699 = false, _mut63700 = false, _mut63701 = false, _mut63702 = false, _mut63703 = false, _mut63704 = false, _mut63705 = false, _mut63706 = false, _mut63707 = false, _mut63708 = false, _mut63709 = false, _mut63710 = false, _mut63711 = false, _mut63712 = false, _mut63713 = false, _mut63714 = false, _mut63715 = false, _mut63716 = false, _mut63717 = false, _mut63718 = false, _mut63719 = false, _mut63720 = false, _mut63721 = false, _mut63722 = false, _mut63723 = false, _mut63724 = false, _mut63725 = false, _mut63726 = false, _mut63727 = false, _mut63728 = false, _mut63729 = false, _mut63730 = false, _mut63731 = false, _mut63732 = false, _mut63733 = false, _mut63734 = false, _mut63735 = false, _mut63736 = false, _mut63737 = false, _mut63738 = false, _mut63739 = false, _mut63740 = false, _mut63741 = false, _mut63742 = false, _mut63743 = false, _mut63744 = false, _mut63745 = false, _mut63746 = false, _mut63747 = false, _mut63748 = false, _mut63749 = false, _mut63750 = false, _mut63751 = false, _mut63752 = false, _mut63753 = false, _mut63754 = false, _mut63755 = false, _mut63756 = false, _mut63757 = false, _mut63758 = false, _mut63759 = false, _mut63760 = false, _mut63761 = false, _mut63762 = false, _mut63763 = false, _mut63764 = false, _mut63765 = false, _mut63766 = false, _mut63767 = false, _mut63768 = false, _mut63769 = false, _mut63770 = false, _mut63771 = false, _mut63772 = false, _mut63773 = false, _mut63774 = false, _mut63775 = false, _mut63776 = false, _mut63777 = false, _mut63778 = false, _mut63779 = false, _mut63780 = false, _mut63781 = false, _mut63782 = false, _mut63783 = false, _mut63784 = false, _mut63785 = false, _mut63786 = false, _mut63787 = false, _mut63788 = false, _mut63789 = false, _mut63790 = false, _mut63791 = false, _mut63792 = false, _mut63793 = false, _mut63794 = false, _mut63795 = false, _mut63796 = false, _mut63797 = false, _mut63798 = false, _mut63799 = false, _mut63800 = false, _mut63801 = false, _mut63802 = false, _mut63803 = false, _mut63804 = false, _mut63805 = false, _mut63806 = false, _mut63807 = false, _mut63808 = false, _mut63809 = false, _mut63810 = false, _mut63811 = false, _mut63812 = false, _mut63813 = false, _mut63814 = false, _mut63815 = false, _mut63816 = false, _mut63817 = false, _mut63818 = false, _mut63819 = false, _mut63820 = false, _mut63821 = false, _mut63822 = false, _mut63823 = false, _mut63824 = false, _mut63825 = false, _mut63826 = false, _mut63827 = false, _mut63828 = false, _mut63829 = false, _mut63830 = false, _mut63831 = false, _mut63832 = false, _mut63833 = false, _mut63834 = false, _mut63835 = false, _mut63836 = false, _mut63837 = false, _mut63838 = false, _mut63839 = false, _mut63840 = false, _mut63841 = false, _mut63842 = false, _mut63843 = false, _mut63844 = false, _mut63845 = false, _mut63846 = false, _mut63847 = false, _mut63848 = false, _mut63849 = false, _mut63850 = false, _mut63851 = false, _mut63852 = false, _mut63853 = false, _mut63854 = false, _mut63855 = false, _mut63856 = false, _mut63857 = false, _mut63858 = false, _mut63859 = false, _mut63860 = false, _mut63861 = false, _mut63862 = false, _mut63863 = false, _mut63864 = false, _mut63865 = false, _mut63866 = false, _mut63867 = false, _mut63868 = false, _mut63869 = false, _mut63870 = false, _mut63871 = false, _mut63872 = false, _mut63873 = false, _mut63874 = false, _mut63875 = false, _mut63876 = false, _mut63877 = false, _mut63878 = false, _mut63879 = false, _mut63880 = false, _mut63881 = false, _mut63882 = false, _mut63883 = false, _mut63884 = false, _mut63885 = false, _mut63886 = false, _mut63887 = false, _mut63888 = false, _mut63889 = false, _mut63890 = false, _mut63891 = false, _mut63892 = false, _mut63893 = false, _mut63894 = false, _mut63895 = false, _mut63896 = false, _mut63897 = false, _mut63898 = false, _mut63899 = false, _mut63900 = false, _mut63901 = false, _mut63902 = false, _mut63903 = false, _mut63904 = false, _mut63905 = false, _mut63906 = false, _mut63907 = false, _mut63908 = false, _mut63909 = false, _mut63910 = false, _mut63911 = false, _mut63912 = false, _mut63913 = false, _mut63914 = false, _mut63915 = false, _mut63916 = false, _mut63917 = false, _mut63918 = false, _mut63919 = false, _mut63920 = false, _mut63921 = false, _mut63922 = false, _mut63923 = false, _mut63924 = false, _mut63925 = false, _mut63926 = false, _mut63927 = false, _mut63928 = false, _mut63929 = false, _mut63930 = false, _mut63931 = false, _mut63932 = false, _mut63933 = false, _mut63934 = false, _mut63935 = false, _mut63936 = false, _mut63937 = false, _mut63938 = false, _mut63939 = false, _mut63940 = false, _mut63941 = false, _mut63942 = false, _mut63943 = false, _mut63944 = false, _mut63945 = false, _mut63946 = false, _mut63947 = false, _mut63948 = false, _mut63949 = false, _mut63950 = false, _mut63951 = false, _mut63952 = false, _mut63953 = false, _mut63954 = false, _mut63955 = false, _mut63956 = false, _mut63957 = false, _mut63958 = false, _mut63959 = false, _mut63960 = false, _mut63961 = false, _mut63962 = false, _mut63963 = false, _mut63964 = false, _mut63965 = false, _mut63966 = false, _mut63967 = false, _mut63968 = false, _mut63969 = false, _mut63970 = false, _mut63971 = false, _mut63972 = false, _mut63973 = false, _mut63974 = false, _mut63975 = false, _mut63976 = false, _mut63977 = false, _mut63978 = false, _mut63979 = false, _mut63980 = false, _mut63981 = false, _mut63982 = false, _mut63983 = false, _mut63984 = false, _mut63985 = false, _mut63986 = false, _mut63987 = false, _mut63988 = false, _mut63989 = false, _mut63990 = false, _mut63991 = false, _mut63992 = false, _mut63993 = false, _mut63994 = false, _mut63995 = false, _mut63996 = false, _mut63997 = false, _mut63998 = false, _mut63999 = false, _mut64000 = false, _mut64001 = false, _mut64002 = false, _mut64003 = false, _mut64004 = false, _mut64005 = false, _mut64006 = false, _mut64007 = false, _mut64008 = false, _mut64009 = false, _mut64010 = false, _mut64011 = false, _mut64012 = false, _mut64013 = false, _mut64014 = false, _mut64015 = false, _mut64016 = false, _mut64017 = false, _mut64018 = false, _mut64019 = false, _mut64020 = false, _mut64021 = false, _mut64022 = false, _mut64023 = false, _mut64024 = false, _mut64025 = false, _mut64026 = false, _mut64027 = false, _mut64028 = false, _mut64029 = false, _mut64030 = false, _mut64031 = false, _mut64032 = false, _mut64033 = false, _mut64034 = false, _mut64035 = false, _mut64036 = false, _mut64037 = false, _mut64038 = false, _mut64039 = false, _mut64040 = false, _mut64041 = false, _mut64042 = false, _mut64043 = false, _mut64044 = false, _mut64045 = false, _mut64046 = false, _mut64047 = false, _mut64048 = false, _mut64049 = false, _mut64050 = false, _mut64051 = false, _mut64052 = false, _mut64053 = false, _mut64054 = false, _mut64055 = false, _mut64056 = false, _mut64057 = false, _mut64058 = false, _mut64059 = false, _mut64060 = false, _mut64061 = false, _mut64062 = false, _mut64063 = false, _mut64064 = false, _mut64065 = false, _mut64066 = false, _mut64067 = false, _mut64068 = false, _mut64069 = false, _mut64070 = false, _mut64071 = false, _mut64072 = false, _mut64073 = false, _mut64074 = false, _mut64075 = false, _mut64076 = false, _mut64077 = false, _mut64078 = false, _mut64079 = false, _mut64080 = false, _mut64081 = false, _mut64082 = false, _mut64083 = false, _mut64084 = false, _mut64085 = false, _mut64086 = false, _mut64087 = false, _mut64088 = false, _mut64089 = false, _mut64090 = false, _mut64091 = false, _mut64092 = false, _mut64093 = false, _mut64094 = false, _mut64095 = false, _mut64096 = false, _mut64097 = false, _mut64098 = false, _mut64099 = false, _mut64100 = false, _mut64101 = false, _mut64102 = false, _mut64103 = false, _mut64104 = false, _mut64105 = false, _mut64106 = false, _mut64107 = false, _mut64108 = false, _mut64109 = false, _mut64110 = false, _mut64111 = false, _mut64112 = false, _mut64113 = false, _mut64114 = false, _mut64115 = false, _mut64116 = false, _mut64117 = false, _mut64118 = false, _mut64119 = false, _mut64120 = false, _mut64121 = false, _mut64122 = false, _mut64123 = false, _mut64124 = false, _mut64125 = false, _mut64126 = false, _mut64127 = false, _mut64128 = false, _mut64129 = false, _mut64130 = false, _mut64131 = false, _mut64132 = false, _mut64133 = false, _mut64134 = false, _mut64135 = false, _mut64136 = false, _mut64137 = false, _mut64138 = false, _mut64139 = false, _mut64140 = false, _mut64141 = false, _mut64142 = false, _mut64143 = false, _mut64144 = false, _mut64145 = false, _mut64146 = false, _mut64147 = false, _mut64148 = false, _mut64149 = false, _mut64150 = false, _mut64151 = false, _mut64152 = false, _mut64153 = false, _mut64154 = false, _mut64155 = false, _mut64156 = false, _mut64157 = false, _mut64158 = false, _mut64159 = false, _mut64160 = false, _mut64161 = false, _mut64162 = false, _mut64163 = false, _mut64164 = false, _mut64165 = false, _mut64166 = false, _mut64167 = false, _mut64168 = false, _mut64169 = false, _mut64170 = false, _mut64171 = false, _mut64172 = false, _mut64173 = false, _mut64174 = false, _mut64175 = false, _mut64176 = false, _mut64177 = false, _mut64178 = false, _mut64179 = false, _mut64180 = false, _mut64181 = false, _mut64182 = false, _mut64183 = false, _mut64184 = false, _mut64185 = false, _mut64186 = false, _mut64187 = false, _mut64188 = false, _mut64189 = false, _mut64190 = false, _mut64191 = false, _mut64192 = false, _mut64193 = false, _mut64194 = false, _mut64195 = false, _mut64196 = false, _mut64197 = false, _mut64198 = false, _mut64199 = false, _mut64200 = false, _mut64201 = false, _mut64202 = false, _mut64203 = false, _mut64204 = false, _mut64205 = false, _mut64206 = false, _mut64207 = false, _mut64208 = false, _mut64209 = false, _mut64210 = false, _mut64211 = false, _mut64212 = false, _mut64213 = false, _mut64214 = false, _mut64215 = false, _mut64216 = false, _mut64217 = false, _mut64218 = false, _mut64219 = false, _mut64220 = false, _mut64221 = false, _mut64222 = false, _mut64223 = false, _mut64224 = false, _mut64225 = false, _mut64226 = false, _mut64227 = false, _mut64228 = false, _mut64229 = false, _mut64230 = false, _mut64231 = false, _mut64232 = false, _mut64233 = false, _mut64234 = false, _mut64235 = false, _mut64236 = false, _mut64237 = false, _mut64238 = false, _mut64239 = false, _mut64240 = false, _mut64241 = false, _mut64242 = false, _mut64243 = false, _mut64244 = false, _mut64245 = false, _mut64246 = false, _mut64247 = false, _mut64248 = false, _mut64249 = false, _mut64250 = false, _mut64251 = false, _mut64252 = false, _mut64253 = false, _mut64254 = false, _mut64255 = false, _mut64256 = false, _mut64257 = false, _mut64258 = false, _mut64259 = false, _mut64260 = false, _mut64261 = false, _mut64262 = false, _mut64263 = false, _mut64264 = false, _mut64265 = false, _mut64266 = false, _mut64267 = false, _mut64268 = false, _mut64269 = false, _mut64270 = false, _mut64271 = false, _mut64272 = false, _mut64273 = false, _mut64274 = false, _mut64275 = false, _mut64276 = false, _mut64277 = false, _mut64278 = false, _mut64279 = false, _mut64280 = false, _mut64281 = false, _mut64282 = false, _mut64283 = false, _mut64284 = false, _mut64285 = false, _mut64286 = false, _mut64287 = false, _mut64288 = false, _mut64289 = false, _mut64290 = false, _mut64291 = false, _mut64292 = false, _mut64293 = false, _mut64294 = false, _mut64295 = false, _mut64296 = false, _mut64297 = false, _mut64298 = false, _mut64299 = false, _mut64300 = false, _mut64301 = false, _mut64302 = false, _mut64303 = false, _mut64304 = false, _mut64305 = false, _mut64306 = false, _mut64307 = false, _mut64308 = false, _mut64309 = false, _mut64310 = false, _mut64311 = false, _mut64312 = false, _mut64313 = false, _mut64314 = false, _mut64315 = false, _mut64316 = false, _mut64317 = false, _mut64318 = false, _mut64319 = false, _mut64320 = false, _mut64321 = false, _mut64322 = false, _mut64323 = false, _mut64324 = false, _mut64325 = false, _mut64326 = false, _mut64327 = false, _mut64328 = false, _mut64329 = false, _mut64330 = false, _mut64331 = false, _mut64332 = false, _mut64333 = false, _mut64334 = false, _mut64335 = false, _mut64336 = false, _mut64337 = false, _mut64338 = false, _mut64339 = false, _mut64340 = false, _mut64341 = false, _mut64342 = false, _mut64343 = false, _mut64344 = false, _mut64345 = false, _mut64346 = false, _mut64347 = false, _mut64348 = false, _mut64349 = false, _mut64350 = false, _mut64351 = false, _mut64352 = false, _mut64353 = false, _mut64354 = false, _mut64355 = false, _mut64356 = false, _mut64357 = false, _mut64358 = false, _mut64359 = false, _mut64360 = false, _mut64361 = false, _mut64362 = false, _mut64363 = false, _mut64364 = false, _mut64365 = false, _mut64366 = false, _mut64367 = false, _mut64368 = false, _mut64369 = false, _mut64370 = false, _mut64371 = false, _mut64372 = false, _mut64373 = false, _mut64374 = false, _mut64375 = false, _mut64376 = false, _mut64377 = false, _mut64378 = false, _mut64379 = false, _mut64380 = false, _mut64381 = false, _mut64382 = false, _mut64383 = false, _mut64384 = false, _mut64385 = false, _mut64386 = false, _mut64387 = false, _mut64388 = false, _mut64389 = false, _mut64390 = false, _mut64391 = false, _mut64392 = false, _mut64393 = false, _mut64394 = false, _mut64395 = false, _mut64396 = false, _mut64397 = false, _mut64398 = false, _mut64399 = false, _mut64400 = false, _mut64401 = false, _mut64402 = false, _mut64403 = false, _mut64404 = false, _mut64405 = false, _mut64406 = false, _mut64407 = false, _mut64408 = false, _mut64409 = false, _mut64410 = false, _mut64411 = false, _mut64412 = false, _mut64413 = false, _mut64414 = false, _mut64415 = false, _mut64416 = false, _mut64417 = false, _mut64418 = false, _mut64419 = false, _mut64420 = false, _mut64421 = false, _mut64422 = false, _mut64423 = false, _mut64424 = false, _mut64425 = false, _mut64426 = false, _mut64427 = false, _mut64428 = false, _mut64429 = false, _mut64430 = false, _mut64431 = false, _mut64432 = false, _mut64433 = false, _mut64434 = false, _mut64435 = false, _mut64436 = false, _mut64437 = false, _mut64438 = false, _mut64439 = false, _mut64440 = false, _mut64441 = false, _mut64442 = false, _mut64443 = false, _mut64444 = false, _mut64445 = false, _mut64446 = false, _mut64447 = false, _mut64448 = false, _mut64449 = false, _mut64450 = false, _mut64451 = false, _mut64452 = false, _mut64453 = false, _mut64454 = false, _mut64455 = false, _mut64456 = false, _mut64457 = false, _mut64458 = false, _mut64459 = false, _mut64460 = false, _mut64461 = false, _mut64462 = false, _mut64463 = false, _mut64464 = false, _mut64465 = false, _mut64466 = false, _mut64467 = false, _mut64468 = false, _mut64469 = false, _mut64470 = false, _mut64471 = false, _mut64472 = false, _mut64473 = false, _mut64474 = false, _mut64475 = false, _mut64476 = false, _mut64477 = false, _mut64478 = false, _mut64479 = false, _mut64480 = false, _mut64481 = false, _mut64482 = false, _mut64483 = false, _mut64484 = false, _mut64485 = false, _mut64486 = false, _mut64487 = false, _mut64488 = false, _mut64489 = false, _mut64490 = false, _mut64491 = false, _mut64492 = false, _mut64493 = false, _mut64494 = false, _mut64495 = false, _mut64496 = false, _mut64497 = false, _mut64498 = false, _mut64499 = false, _mut64500 = false, _mut64501 = false, _mut64502 = false, _mut64503 = false, _mut64504 = false, _mut64505 = false, _mut64506 = false, _mut64507 = false, _mut64508 = false, _mut64509 = false, _mut64510 = false, _mut64511 = false, _mut64512 = false, _mut64513 = false, _mut64514 = false, _mut64515 = false, _mut64516 = false, _mut64517 = false, _mut64518 = false, _mut64519 = false, _mut64520 = false, _mut64521 = false, _mut64522 = false, _mut64523 = false, _mut64524 = false, _mut64525 = false, _mut64526 = false, _mut64527 = false, _mut64528 = false, _mut64529 = false, _mut64530 = false, _mut64531 = false, _mut64532 = false, _mut64533 = false, _mut64534 = false, _mut64535 = false, _mut64536 = false, _mut64537 = false, _mut64538 = false, _mut64539 = false, _mut64540 = false, _mut64541 = false, _mut64542 = false, _mut64543 = false, _mut64544 = false, _mut64545 = false, _mut64546 = false, _mut64547 = false, _mut64548 = false, _mut64549 = false, _mut64550 = false, _mut64551 = false, _mut64552 = false, _mut64553 = false, _mut64554 = false, _mut64555 = false, _mut64556 = false, _mut64557 = false, _mut64558 = false, _mut64559 = false, _mut64560 = false, _mut64561 = false, _mut64562 = false, _mut64563 = false, _mut64564 = false, _mut64565 = false, _mut64566 = false, _mut64567 = false, _mut64568 = false, _mut64569 = false, _mut64570 = false, _mut64571 = false, _mut64572 = false, _mut64573 = false, _mut64574 = false, _mut64575 = false, _mut64576 = false, _mut64577 = false, _mut64578 = false, _mut64579 = false, _mut64580 = false, _mut64581 = false, _mut64582 = false, _mut64583 = false, _mut64584 = false, _mut64585 = false, _mut64586 = false, _mut64587 = false, _mut64588 = false, _mut64589 = false, _mut64590 = false, _mut64591 = false, _mut64592 = false, _mut64593 = false, _mut64594 = false, _mut64595 = false, _mut64596 = false, _mut64597 = false, _mut64598 = false, _mut64599 = false, _mut64600 = false, _mut64601 = false, _mut64602 = false, _mut64603 = false, _mut64604 = false, _mut64605 = false, _mut64606 = false, _mut64607 = false, _mut64608 = false, _mut64609 = false, _mut64610 = false, _mut64611 = false, _mut64612 = false, _mut64613 = false, _mut64614 = false, _mut64615 = false, _mut64616 = false, _mut64617 = false, _mut64618 = false, _mut64619 = false, _mut64620 = false, _mut64621 = false, _mut64622 = false, _mut64623 = false, _mut64624 = false, _mut64625 = false, _mut64626 = false, _mut64627 = false, _mut64628 = false, _mut64629 = false, _mut64630 = false, _mut64631 = false, _mut64632 = false, _mut64633 = false, _mut64634 = false, _mut64635 = false, _mut64636 = false, _mut64637 = false, _mut64638 = false, _mut64639 = false, _mut64640 = false, _mut64641 = false, _mut64642 = false, _mut64643 = false, _mut64644 = false, _mut64645 = false, _mut64646 = false, _mut64647 = false, _mut64648 = false, _mut64649 = false, _mut64650 = false, _mut64651 = false, _mut64652 = false, _mut64653 = false, _mut64654 = false, _mut64655 = false, _mut64656 = false, _mut64657 = false, _mut64658 = false, _mut64659 = false, _mut64660 = false, _mut64661 = false, _mut64662 = false, _mut64663 = false, _mut64664 = false, _mut64665 = false, _mut64666 = false, _mut64667 = false, _mut64668 = false, _mut64669 = false, _mut64670 = false, _mut64671 = false, _mut64672 = false, _mut64673 = false, _mut64674 = false, _mut64675 = false, _mut64676 = false, _mut64677 = false, _mut64678 = false, _mut64679 = false, _mut64680 = false, _mut64681 = false, _mut64682 = false, _mut64683 = false, _mut64684 = false, _mut64685 = false, _mut64686 = false, _mut64687 = false, _mut64688 = false, _mut64689 = false, _mut64690 = false, _mut64691 = false, _mut64692 = false, _mut64693 = false, _mut64694 = false, _mut64695 = false, _mut64696 = false, _mut64697 = false, _mut64698 = false, _mut64699 = false, _mut64700 = false, _mut64701 = false, _mut64702 = false, _mut64703 = false, _mut64704 = false, _mut64705 = false, _mut64706 = false, _mut64707 = false, _mut64708 = false, _mut64709 = false, _mut64710 = false, _mut64711 = false, _mut64712 = false, _mut64713 = false, _mut64714 = false, _mut64715 = false, _mut64716 = false, _mut64717 = false, _mut64718 = false, _mut64719 = false, _mut64720 = false, _mut64721 = false, _mut64722 = false, _mut64723 = false, _mut64724 = false, _mut64725 = false, _mut64726 = false, _mut64727 = false, _mut64728 = false, _mut64729 = false, _mut64730 = false, _mut64731 = false, _mut64732 = false, _mut64733 = false, _mut64734 = false, _mut64735 = false, _mut64736 = false, _mut64737 = false, _mut64738 = false, _mut64739 = false, _mut64740 = false, _mut64741 = false, _mut64742 = false, _mut64743 = false, _mut64744 = false, _mut64745 = false, _mut64746 = false, _mut64747 = false, _mut64748 = false, _mut64749 = false, _mut64750 = false, _mut64751 = false, _mut64752 = false, _mut64753 = false, _mut64754 = false, _mut64755 = false, _mut64756 = false, _mut64757 = false, _mut64758 = false, _mut64759 = false, _mut64760 = false, _mut64761 = false, _mut64762 = false, _mut64763 = false, _mut64764 = false, _mut64765 = false, _mut64766 = false, _mut64767 = false, _mut64768 = false, _mut64769 = false, _mut64770 = false, _mut64771 = false, _mut64772 = false, _mut64773 = false, _mut64774 = false, _mut64775 = false, _mut64776 = false, _mut64777 = false, _mut64778 = false, _mut64779 = false, _mut64780 = false, _mut64781 = false, _mut64782 = false, _mut64783 = false, _mut64784 = false, _mut64785 = false, _mut64786 = false, _mut64787 = false, _mut64788 = false, _mut64789 = false, _mut64790 = false, _mut64791 = false, _mut64792 = false, _mut64793 = false, _mut64794 = false, _mut64795 = false, _mut64796 = false, _mut64797 = false, _mut64798 = false, _mut64799 = false, _mut64800 = false, _mut64801 = false, _mut64802 = false, _mut64803 = false, _mut64804 = false, _mut64805 = false, _mut64806 = false, _mut64807 = false, _mut64808 = false, _mut64809 = false, _mut64810 = false, _mut64811 = false, _mut64812 = false, _mut64813 = false, _mut64814 = false, _mut64815 = false, _mut64816 = false, _mut64817 = false, _mut64818 = false, _mut64819 = false, _mut64820 = false, _mut64821 = false, _mut64822 = false, _mut64823 = false, _mut64824 = false, _mut64825 = false, _mut64826 = false, _mut64827 = false, _mut64828 = false, _mut64829 = false, _mut64830 = false, _mut64831 = false, _mut64832 = false, _mut64833 = false, _mut64834 = false, _mut64835 = false, _mut64836 = false, _mut64837 = false, _mut64838 = false, _mut64839 = false, _mut64840 = false, _mut64841 = false, _mut64842 = false, _mut64843 = false, _mut64844 = false, _mut64845 = false, _mut64846 = false, _mut64847 = false, _mut64848 = false, _mut64849 = false, _mut64850 = false, _mut64851 = false, _mut64852 = false, _mut64853 = false, _mut64854 = false, _mut64855 = false, _mut64856 = false, _mut64857 = false, _mut64858 = false, _mut64859 = false, _mut64860 = false, _mut64861 = false, _mut64862 = false, _mut64863 = false, _mut64864 = false, _mut64865 = false, _mut64866 = false, _mut64867 = false, _mut64868 = false, _mut64869 = false, _mut64870 = false, _mut64871 = false, _mut64872 = false, _mut64873 = false, _mut64874 = false, _mut64875 = false, _mut64876 = false, _mut64877 = false, _mut64878 = false, _mut64879 = false, _mut64880 = false, _mut64881 = false, _mut64882 = false, _mut64883 = false, _mut64884 = false, _mut64885 = false, _mut64886 = false, _mut64887 = false, _mut64888 = false, _mut64889 = false, _mut64890 = false, _mut64891 = false, _mut64892 = false, _mut64893 = false, _mut64894 = false, _mut64895 = false, _mut64896 = false, _mut64897 = false, _mut64898 = false, _mut64899 = false, _mut64900 = false, _mut64901 = false, _mut64902 = false, _mut64903 = false, _mut64904 = false, _mut64905 = false, _mut64906 = false, _mut64907 = false, _mut64908 = false, _mut64909 = false, _mut64910 = false, _mut64911 = false, _mut64912 = false, _mut64913 = false, _mut64914 = false, _mut64915 = false, _mut64916 = false, _mut64917 = false, _mut64918 = false, _mut64919 = false, _mut64920 = false, _mut64921 = false, _mut64922 = false, _mut64923 = false, _mut64924 = false, _mut64925 = false, _mut64926 = false, _mut64927 = false, _mut64928 = false, _mut64929 = false, _mut64930 = false, _mut64931 = false, _mut64932 = false, _mut64933 = false, _mut64934 = false, _mut64935 = false, _mut64936 = false, _mut64937 = false, _mut64938 = false, _mut64939 = false, _mut64940 = false, _mut64941 = false, _mut64942 = false, _mut64943 = false, _mut64944 = false, _mut64945 = false, _mut64946 = false, _mut64947 = false, _mut64948 = false, _mut64949 = false, _mut64950 = false, _mut64951 = false, _mut64952 = false, _mut64953 = false, _mut64954 = false, _mut64955 = false, _mut64956 = false, _mut64957 = false, _mut64958 = false, _mut64959 = false, _mut64960 = false, _mut64961 = false, _mut64962 = false, _mut64963 = false, _mut64964 = false, _mut64965 = false, _mut64966 = false, _mut64967 = false, _mut64968 = false, _mut64969 = false, _mut64970 = false, _mut64971 = false, _mut64972 = false, _mut64973 = false, _mut64974 = false, _mut64975 = false, _mut64976 = false, _mut64977 = false, _mut64978 = false, _mut64979 = false, _mut64980 = false, _mut64981 = false, _mut64982 = false, _mut64983 = false, _mut64984 = false, _mut64985 = false, _mut64986 = false, _mut64987 = false, _mut64988 = false, _mut64989 = false, _mut64990 = false, _mut64991 = false, _mut64992 = false, _mut64993 = false, _mut64994 = false, _mut64995 = false, _mut64996 = false, _mut64997 = false, _mut64998 = false, _mut64999 = false, _mut65000 = false, _mut65001 = false, _mut65002 = false, _mut65003 = false, _mut65004 = false, _mut65005 = false, _mut65006 = false, _mut65007 = false, _mut65008 = false, _mut65009 = false, _mut65010 = false, _mut65011 = false, _mut65012 = false, _mut65013 = false, _mut65014 = false, _mut65015 = false, _mut65016 = false, _mut65017 = false, _mut65018 = false, _mut65019 = false, _mut65020 = false, _mut65021 = false, _mut65022 = false, _mut65023 = false, _mut65024 = false, _mut65025 = false, _mut65026 = false, _mut65027 = false, _mut65028 = false, _mut65029 = false, _mut65030 = false, _mut65031 = false, _mut65032 = false, _mut65033 = false, _mut65034 = false, _mut65035 = false, _mut65036 = false, _mut65037 = false, _mut65038 = false, _mut65039 = false, _mut65040 = false, _mut65041 = false, _mut65042 = false, _mut65043 = false, _mut65044 = false, _mut65045 = false, _mut65046 = false, _mut65047 = false, _mut65048 = false, _mut65049 = false, _mut65050 = false, _mut65051 = false, _mut65052 = false, _mut65053 = false, _mut65054 = false, _mut65055 = false, _mut65056 = false, _mut65057 = false, _mut65058 = false, _mut65059 = false, _mut65060 = false, _mut65061 = false, _mut65062 = false, _mut65063 = false, _mut65064 = false, _mut65065 = false, _mut65066 = false, _mut65067 = false, _mut65068 = false, _mut65069 = false, _mut65070 = false, _mut65071 = false, _mut65072 = false, _mut65073 = false, _mut65074 = false, _mut65075 = false, _mut65076 = false, _mut65077 = false, _mut65078 = false, _mut65079 = false, _mut65080 = false, _mut65081 = false, _mut65082 = false, _mut65083 = false, _mut65084 = false, _mut65085 = false, _mut65086 = false, _mut65087 = false, _mut65088 = false, _mut65089 = false, _mut65090 = false, _mut65091 = false, _mut65092 = false, _mut65093 = false, _mut65094 = false, _mut65095 = false, _mut65096 = false, _mut65097 = false, _mut65098 = false, _mut65099 = false, _mut65100 = false, _mut65101 = false, _mut65102 = false, _mut65103 = false, _mut65104 = false, _mut65105 = false, _mut65106 = false, _mut65107 = false, _mut65108 = false, _mut65109 = false, _mut65110 = false, _mut65111 = false, _mut65112 = false, _mut65113 = false, _mut65114 = false, _mut65115 = false, _mut65116 = false, _mut65117 = false, _mut65118 = false, _mut65119 = false, _mut65120 = false, _mut65121 = false, _mut65122 = false, _mut65123 = false, _mut65124 = false, _mut65125 = false, _mut65126 = false, _mut65127 = false, _mut65128 = false, _mut65129 = false, _mut65130 = false, _mut65131 = false, _mut65132 = false, _mut65133 = false, _mut65134 = false, _mut65135 = false, _mut65136 = false, _mut65137 = false, _mut65138 = false, _mut65139 = false, _mut65140 = false, _mut65141 = false, _mut65142 = false, _mut65143 = false, _mut65144 = false, _mut65145 = false, _mut65146 = false, _mut65147 = false, _mut65148 = false, _mut65149 = false, _mut65150 = false, _mut65151 = false, _mut65152 = false, _mut65153 = false, _mut65154 = false, _mut65155 = false, _mut65156 = false, _mut65157 = false, _mut65158 = false, _mut65159 = false, _mut65160 = false, _mut65161 = false, _mut65162 = false, _mut65163 = false, _mut65164 = false, _mut65165 = false, _mut65166 = false, _mut65167 = false, _mut65168 = false, _mut65169 = false, _mut65170 = false, _mut65171 = false, _mut65172 = false, _mut65173 = false, _mut65174 = false, _mut65175 = false, _mut65176 = false, _mut65177 = false, _mut65178 = false, _mut65179 = false, _mut65180 = false, _mut65181 = false, _mut65182 = false, _mut65183 = false, _mut65184 = false, _mut65185 = false, _mut65186 = false, _mut65187 = false, _mut65188 = false, _mut65189 = false, _mut65190 = false, _mut65191 = false, _mut65192 = false, _mut65193 = false, _mut65194 = false, _mut65195 = false, _mut65196 = false, _mut65197 = false, _mut65198 = false, _mut65199 = false, _mut65200 = false, _mut65201 = false, _mut65202 = false, _mut65203 = false, _mut65204 = false, _mut65205 = false, _mut65206 = false, _mut65207 = false, _mut65208 = false, _mut65209 = false, _mut65210 = false, _mut65211 = false, _mut65212 = false, _mut65213 = false, _mut65214 = false, _mut65215 = false, _mut65216 = false, _mut65217 = false, _mut65218 = false, _mut65219 = false, _mut65220 = false, _mut65221 = false, _mut65222 = false, _mut65223 = false, _mut65224 = false, _mut65225 = false, _mut65226 = false, _mut65227 = false, _mut65228 = false, _mut65229 = false, _mut65230 = false, _mut65231 = false, _mut65232 = false, _mut65233 = false, _mut65234 = false, _mut65235 = false, _mut65236 = false, _mut65237 = false, _mut65238 = false, _mut65239 = false, _mut65240 = false, _mut65241 = false, _mut65242 = false, _mut65243 = false, _mut65244 = false, _mut65245 = false, _mut65246 = false, _mut65247 = false, _mut65248 = false, _mut65249 = false, _mut65250 = false, _mut65251 = false, _mut65252 = false, _mut65253 = false, _mut65254 = false, _mut65255 = false, _mut65256 = false, _mut65257 = false, _mut65258 = false, _mut65259 = false, _mut65260 = false, _mut65261 = false, _mut65262 = false, _mut65263 = false, _mut65264 = false, _mut65265 = false, _mut65266 = false, _mut65267 = false, _mut65268 = false, _mut65269 = false, _mut65270 = false, _mut65271 = false, _mut65272 = false, _mut65273 = false, _mut65274 = false, _mut65275 = false, _mut65276 = false, _mut65277 = false, _mut65278 = false, _mut65279 = false, _mut65280 = false, _mut65281 = false, _mut65282 = false, _mut65283 = false, _mut65284 = false, _mut65285 = false, _mut65286 = false, _mut65287 = false, _mut65288 = false, _mut65289 = false, _mut65290 = false, _mut65291 = false, _mut65292 = false, _mut65293 = false, _mut65294 = false, _mut65295 = false, _mut65296 = false, _mut65297 = false, _mut65298 = false, _mut65299 = false, _mut65300 = false, _mut65301 = false, _mut65302 = false, _mut65303 = false, _mut65304 = false, _mut65305 = false, _mut65306 = false, _mut65307 = false, _mut65308 = false, _mut65309 = false, _mut65310 = false, _mut65311 = false, _mut65312 = false, _mut65313 = false, _mut65314 = false, _mut65315 = false, _mut65316 = false, _mut65317 = false, _mut65318 = false, _mut65319 = false, _mut65320 = false, _mut65321 = false, _mut65322 = false, _mut65323 = false, _mut65324 = false, _mut65325 = false, _mut65326 = false, _mut65327 = false, _mut65328 = false, _mut65329 = false, _mut65330 = false, _mut65331 = false, _mut65332 = false, _mut65333 = false, _mut65334 = false, _mut65335 = false, _mut65336 = false, _mut65337 = false, _mut65338 = false, _mut65339 = false, _mut65340 = false, _mut65341 = false, _mut65342 = false, _mut65343 = false, _mut65344 = false, _mut65345 = false, _mut65346 = false, _mut65347 = false, _mut65348 = false, _mut65349 = false, _mut65350 = false, _mut65351 = false, _mut65352 = false, _mut65353 = false, _mut65354 = false, _mut65355 = false, _mut65356 = false, _mut65357 = false, _mut65358 = false, _mut65359 = false, _mut65360 = false, _mut65361 = false, _mut65362 = false, _mut65363 = false, _mut65364 = false, _mut65365 = false, _mut65366 = false, _mut65367 = false, _mut65368 = false, _mut65369 = false, _mut65370 = false, _mut65371 = false, _mut65372 = false, _mut65373 = false, _mut65374 = false, _mut65375 = false, _mut65376 = false, _mut65377 = false, _mut65378 = false, _mut65379 = false, _mut65380 = false, _mut65381 = false, _mut65382 = false, _mut65383 = false, _mut65384 = false, _mut65385 = false, _mut65386 = false, _mut65387 = false, _mut65388 = false, _mut65389 = false, _mut65390 = false, _mut65391 = false, _mut65392 = false, _mut65393 = false, _mut65394 = false, _mut65395 = false, _mut65396 = false, _mut65397 = false, _mut65398 = false, _mut65399 = false, _mut65400 = false, _mut65401 = false, _mut65402 = false, _mut65403 = false, _mut65404 = false, _mut65405 = false, _mut65406 = false, _mut65407 = false, _mut65408 = false, _mut65409 = false, _mut65410 = false, _mut65411 = false, _mut65412 = false, _mut65413 = false, _mut65414 = false, _mut65415 = false, _mut65416 = false, _mut65417 = false, _mut65418 = false, _mut65419 = false, _mut65420 = false, _mut65421 = false, _mut65422 = false, _mut65423 = false, _mut65424 = false, _mut65425 = false, _mut65426 = false, _mut65427 = false, _mut65428 = false, _mut65429 = false, _mut65430 = false, _mut65431 = false, _mut65432 = false, _mut65433 = false, _mut65434 = false, _mut65435 = false, _mut65436 = false, _mut65437 = false, _mut65438 = false, _mut65439 = false, _mut65440 = false, _mut65441 = false, _mut65442 = false, _mut65443 = false, _mut65444 = false, _mut65445 = false, _mut65446 = false, _mut65447 = false, _mut65448 = false, _mut65449 = false, _mut65450 = false, _mut65451 = false, _mut65452 = false, _mut65453 = false, _mut65454 = false, _mut65455 = false, _mut65456 = false, _mut65457 = false, _mut65458 = false, _mut65459 = false, _mut65460 = false, _mut65461 = false, _mut65462 = false, _mut65463 = false, _mut65464 = false, _mut65465 = false, _mut65466 = false, _mut65467 = false, _mut65468 = false, _mut65469 = false, _mut65470 = false, _mut65471 = false, _mut65472 = false, _mut65473 = false, _mut65474 = false, _mut65475 = false, _mut65476 = false, _mut65477 = false, _mut65478 = false, _mut65479 = false, _mut65480 = false, _mut65481 = false, _mut65482 = false, _mut65483 = false, _mut65484 = false, _mut65485 = false, _mut65486 = false, _mut65487 = false, _mut65488 = false, _mut65489 = false, _mut65490 = false, _mut65491 = false, _mut65492 = false, _mut65493 = false, _mut65494 = false, _mut65495 = false, _mut65496 = false, _mut65497 = false, _mut65498 = false, _mut65499 = false, _mut65500 = false, _mut65501 = false, _mut65502 = false, _mut65503 = false, _mut65504 = false, _mut65505 = false, _mut65506 = false, _mut65507 = false, _mut65508 = false, _mut65509 = false, _mut65510 = false, _mut65511 = false, _mut65512 = false, _mut65513 = false, _mut65514 = false, _mut65515 = false, _mut65516 = false, _mut65517 = false, _mut65518 = false, _mut65519 = false, _mut65520 = false, _mut65521 = false, _mut65522 = false, _mut65523 = false, _mut65524 = false, _mut65525 = false, _mut65526 = false, _mut65527 = false, _mut65528 = false, _mut65529 = false, _mut65530 = false, _mut65531 = false, _mut65532 = false, _mut65533 = false, _mut65534 = false, _mut65535 = false, _mut65536 = false, _mut65537 = false, _mut65538 = false, _mut65539 = false, _mut65540 = false, _mut65541 = false, _mut65542 = false, _mut65543 = false, _mut65544 = false, _mut65545 = false, _mut65546 = false, _mut65547 = false, _mut65548 = false, _mut65549 = false, _mut65550 = false, _mut65551 = false, _mut65552 = false, _mut65553 = false, _mut65554 = false, _mut65555 = false, _mut65556 = false, _mut65557 = false, _mut65558 = false, _mut65559 = false, _mut65560 = false, _mut65561 = false, _mut65562 = false, _mut65563 = false, _mut65564 = false, _mut65565 = false, _mut65566 = false, _mut65567 = false, _mut65568 = false, _mut65569 = false, _mut65570 = false, _mut65571 = false, _mut65572 = false, _mut65573 = false, _mut65574 = false, _mut65575 = false, _mut65576 = false, _mut65577 = false, _mut65578 = false, _mut65579 = false, _mut65580 = false, _mut65581 = false, _mut65582 = false, _mut65583 = false, _mut65584 = false, _mut65585 = false, _mut65586 = false, _mut65587 = false, _mut65588 = false, _mut65589 = false, _mut65590 = false, _mut65591 = false, _mut65592 = false, _mut65593 = false, _mut65594 = false, _mut65595 = false, _mut65596 = false, _mut65597 = false, _mut65598 = false, _mut65599 = false, _mut65600 = false, _mut65601 = false, _mut65602 = false, _mut65603 = false, _mut65604 = false, _mut65605 = false, _mut65606 = false, _mut65607 = false, _mut65608 = false, _mut65609 = false, _mut65610 = false, _mut65611 = false, _mut65612 = false, _mut65613 = false, _mut65614 = false, _mut65615 = false, _mut65616 = false, _mut65617 = false, _mut65618 = false, _mut65619 = false, _mut65620 = false, _mut65621 = false, _mut65622 = false, _mut65623 = false, _mut65624 = false, _mut65625 = false, _mut65626 = false, _mut65627 = false, _mut65628 = false, _mut65629 = false, _mut65630 = false, _mut65631 = false, _mut65632 = false, _mut65633 = false, _mut65634 = false, _mut65635 = false, _mut65636 = false, _mut65637 = false, _mut65638 = false, _mut65639 = false, _mut65640 = false, _mut65641 = false, _mut65642 = false, _mut65643 = false, _mut65644 = false, _mut65645 = false, _mut65646 = false, _mut65647 = false, _mut65648 = false, _mut65649 = false, _mut65650 = false, _mut65651 = false, _mut65652 = false, _mut65653 = false, _mut65654 = false, _mut65655 = false, _mut65656 = false, _mut65657 = false, _mut65658 = false, _mut65659 = false, _mut65660 = false, _mut65661 = false, _mut65662 = false, _mut65663 = false, _mut65664 = false, _mut65665 = false, _mut65666 = false, _mut65667 = false, _mut65668 = false, _mut65669 = false, _mut65670 = false, _mut65671 = false, _mut65672 = false, _mut65673 = false, _mut65674 = false, _mut65675 = false, _mut65676 = false, _mut65677 = false, _mut65678 = false, _mut65679 = false, _mut65680 = false, _mut65681 = false, _mut65682 = false, _mut65683 = false, _mut65684 = false, _mut65685 = false, _mut65686 = false, _mut65687 = false, _mut65688 = false, _mut65689 = false, _mut65690 = false, _mut65691 = false, _mut65692 = false, _mut65693 = false, _mut65694 = false, _mut65695 = false, _mut65696 = false, _mut65697 = false, _mut65698 = false, _mut65699 = false, _mut65700 = false, _mut65701 = false, _mut65702 = false, _mut65703 = false, _mut65704 = false, _mut65705 = false, _mut65706 = false, _mut65707 = false;

    /**
     * Minimum dimension of the problem: {@value}
     */
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;

    /**
     * Default value for {@link #initialTrustRegionRadius}: {@value} .
     */
    public static final double DEFAULT_INITIAL_RADIUS = 10.0;

    /**
     * Default value for {@link #stoppingTrustRegionRadius}: {@value} .
     */
    public static final double DEFAULT_STOPPING_RADIUS = 1E-8;

    /**
     * Constant 0.
     */
    private static final double ZERO = 0d;

    /**
     * Constant 1.
     */
    private static final double ONE = 1d;

    /**
     * Constant 2.
     */
    private static final double TWO = 2d;

    /**
     * Constant 10.
     */
    private static final double TEN = 10d;

    /**
     * Constant 16.
     */
    private static final double SIXTEEN = 16d;

    /**
     * Constant 250.
     */
    private static final double TWO_HUNDRED_FIFTY = 250d;

    /**
     * Constant -1.
     */
    private static final double MINUS_ONE = -ONE;

    /**
     * Constant 1/2.
     */
    private static final double HALF = AOR_divide(ONE, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.checkParameters_213", _mut61914, _mut61915, _mut61916, _mut61917);

    /**
     * Constant 1/4.
     */
    private static final double ONE_OVER_FOUR = AOR_divide(ONE, 4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.checkParameters_213", _mut61918, _mut61919, _mut61920, _mut61921);

    /**
     * Constant 1/8.
     */
    private static final double ONE_OVER_EIGHT = AOR_divide(ONE, 8, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.checkParameters_213", _mut61922, _mut61923, _mut61924, _mut61925);

    /**
     * Constant 1/10.
     */
    private static final double ONE_OVER_TEN = AOR_divide(ONE, 10, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.checkParameters_213", _mut61926, _mut61927, _mut61928, _mut61929);

    /**
     * Constant 1/1000.
     */
    private static final double ONE_OVER_A_THOUSAND = AOR_divide(ONE, 1000, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.checkParameters_213", _mut61930, _mut61931, _mut61932, _mut61933);

    /**
     * numberOfInterpolationPoints XXX
     */
    private final int numberOfInterpolationPoints;

    /**
     * initialTrustRegionRadius XXX
     */
    private double initialTrustRegionRadius;

    /**
     * stoppingTrustRegionRadius XXX
     */
    private final double stoppingTrustRegionRadius;

    /**
     * Goal type (minimize or maximize).
     */
    private boolean isMinimize;

    /**
     * Current best values for the variables to be optimized.
     * The vector will be changed in-place to contain the values of the least
     * calculated objective function values.
     */
    private ArrayRealVector currentBest;

    /**
     * Differences between the upper and lower bounds.
     */
    private double[] boundDifference;

    /**
     * Index of the interpolation point at the trust region center.
     */
    private int trustRegionCenterInterpolationPointIndex;

    /**
     * Last <em>n</em> columns of matrix H (where <em>n</em> is the dimension
     * of the problem).
     * XXX "bmat" in the original code.
     */
    private Array2DRowRealMatrix bMatrix;

    /**
     * Factorization of the leading <em>npt</em> square submatrix of H, this
     * factorization being Z Z<sup>T</sup>, which provides both the correct
     * rank and positive semi-definiteness.
     * XXX "zmat" in the original code.
     */
    private Array2DRowRealMatrix zMatrix;

    /**
     * Coordinates of the interpolation points relative to {@link #originShift}.
     * XXX "xpt" in the original code.
     */
    private Array2DRowRealMatrix interpolationPoints;

    /**
     * Shift of origin that should reduce the contributions from rounding
     * errors to values of the model and Lagrange functions.
     * XXX "xbase" in the original code.
     */
    private ArrayRealVector originShift;

    /**
     * Values of the objective function at the interpolation points.
     * XXX "fval" in the original code.
     */
    private ArrayRealVector fAtInterpolationPoints;

    /**
     * Displacement from {@link #originShift} of the trust region center.
     * XXX "xopt" in the original code.
     */
    private ArrayRealVector trustRegionCenterOffset;

    /**
     * Gradient of the quadratic model at {@link #originShift} +
     * {@link #trustRegionCenterOffset}.
     * XXX "gopt" in the original code.
     */
    private ArrayRealVector gradientAtTrustRegionCenter;

    /**
     * Differences {@link #getLowerBound()} - {@link #originShift}.
     * All the components of every {@link #trustRegionCenterOffset} are going
     * to satisfy the bounds<br/>
     * {@link #getLowerBound() lowerBound}<sub>i</sub> &le;
     * {@link #trustRegionCenterOffset}<sub>i</sub>,<br/>
     * with appropriate equalities when {@link #trustRegionCenterOffset} is
     * on a constraint boundary.
     * XXX "sl" in the original code.
     */
    private ArrayRealVector lowerDifference;

    /**
     * Differences {@link #getUpperBound()} - {@link #originShift}
     * All the components of every {@link #trustRegionCenterOffset} are going
     * to satisfy the bounds<br/>
     *  {@link #trustRegionCenterOffset}<sub>i</sub> &le;
     *  {@link #getUpperBound() upperBound}<sub>i</sub>,<br/>
     * with appropriate equalities when {@link #trustRegionCenterOffset} is
     * on a constraint boundary.
     * XXX "su" in the original code.
     */
    private ArrayRealVector upperDifference;

    /**
     * Parameters of the implicit second derivatives of the quadratic model.
     * XXX "pq" in the original code.
     */
    private ArrayRealVector modelSecondDerivativesParameters;

    /**
     * Point chosen by function {@link #trsbox(double,ArrayRealVector,
     * ArrayRealVector, ArrayRealVector,ArrayRealVector,ArrayRealVector) trsbox}
     * or {@link #altmov(int,double) altmov}.
     * Usually {@link #originShift} + {@link #newPoint} is the vector of
     * variables for the next evaluation of the objective function.
     * It also satisfies the constraints indicated in {@link #lowerDifference}
     * and {@link #upperDifference}.
     * XXX "xnew" in the original code.
     */
    private ArrayRealVector newPoint;

    /**
     * Alternative to {@link #newPoint}, chosen by
     * {@link #altmov(int,double) altmov}.
     * It may replace {@link #newPoint} in order to increase the denominator
     * in the {@link #update(double, double, int) updating procedure}.
     * XXX "xalt" in the original code.
     */
    private ArrayRealVector alternativeNewPoint;

    /**
     * Trial step from {@link #trustRegionCenterOffset} which is usually
     * {@link #newPoint} - {@link #trustRegionCenterOffset}.
     * XXX "d__" in the original code.
     */
    private ArrayRealVector trialStepPoint;

    /**
     * Values of the Lagrange functions at a new point.
     * XXX "vlag" in the original code.
     */
    private ArrayRealVector lagrangeValuesAtNewPoint;

    /**
     * Explicit second derivatives of the quadratic model.
     * XXX "hq" in the original code.
     */
    private ArrayRealVector modelSecondDerivativesValues;

    /**
     * @param numberOfInterpolationPoints Number of interpolation conditions.
     * For a problem of dimension {@code n}, its value must be in the interval
     * {@code [n+2, (n+1)(n+2)/2]}.
     * Choices that exceed {@code 2n+1} are not recommended.
     */
    public BOBYQAOptimizer(int numberOfInterpolationPoints) {
        this(numberOfInterpolationPoints, DEFAULT_INITIAL_RADIUS, DEFAULT_STOPPING_RADIUS);
    }

    /**
     * @param numberOfInterpolationPoints Number of interpolation conditions.
     * For a problem of dimension {@code n}, its value must be in the interval
     * {@code [n+2, (n+1)(n+2)/2]}.
     * Choices that exceed {@code 2n+1} are not recommended.
     * @param initialTrustRegionRadius Initial trust region radius.
     * @param stoppingTrustRegionRadius Stopping trust region radius.
     */
    public BOBYQAOptimizer(int numberOfInterpolationPoints, double initialTrustRegionRadius, double stoppingTrustRegionRadius) {
        // No custom convergence criterion.
        super(null);
        this.numberOfInterpolationPoints = numberOfInterpolationPoints;
        this.initialTrustRegionRadius = initialTrustRegionRadius;
        this.stoppingTrustRegionRadius = stoppingTrustRegionRadius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        final double[] lowerBound = getLowerBound();
        final double[] upperBound = getUpperBound();
        // Validity checks.
        setup(lowerBound, upperBound);
        isMinimize = (getGoalType() == GoalType.MINIMIZE);
        currentBest = new ArrayRealVector(getStartPoint());
        final double value = bobyqa(lowerBound, upperBound);
        return new PointValuePair(currentBest.getDataRef(), isMinimize ? value : -value);
    }

    /**
     *     This subroutine seeks the least value of a function of many variables,
     *     by applying a trust region method that forms quadratic models by
     *     interpolation. There is usually some freedom in the interpolation
     *     conditions, which is taken up by minimizing the Frobenius norm of
     *     the change to the second derivative of the model, beginning with the
     *     zero matrix. The values of the variables are constrained by upper and
     *     lower bounds. The arguments of the subroutine are as follows.
     *
     *     N must be set to the number of variables and must be at least two.
     *     NPT is the number of interpolation conditions. Its value must be in
     *       the interval [N+2,(N+1)(N+2)/2]. Choices that exceed 2*N+1 are not
     *       recommended.
     *     Initial values of the variables must be set in X(1),X(2),...,X(N). They
     *       will be changed to the values that give the least calculated F.
     *     For I=1,2,...,N, XL(I) and XU(I) must provide the lower and upper
     *       bounds, respectively, on X(I). The construction of quadratic models
     *       requires XL(I) to be strictly less than XU(I) for each I. Further,
     *       the contribution to a model from changes to the I-th variable is
     *       damaged severely by rounding errors if XU(I)-XL(I) is too small.
     *     RHOBEG and RHOEND must be set to the initial and final values of a trust
     *       region radius, so both must be positive with RHOEND no greater than
     *       RHOBEG. Typically, RHOBEG should be about one tenth of the greatest
     *       expected change to a variable, while RHOEND should indicate the
     *       accuracy that is required in the final values of the variables. An
     *       error return occurs if any of the differences XU(I)-XL(I), I=1,...,N,
     *       is less than 2*RHOBEG.
     *     MAXFUN must be set to an upper bound on the number of calls of CALFUN.
     *     The array W will be used for working space. Its length must be at least
     *       (NPT+5)*(NPT+N)+3*N*(N+5)/2.
     *
     * @param lowerBound Lower bounds.
     * @param upperBound Upper bounds.
     * @return the value of the objective at the optimum.
     */
    private double bobyqa(double[] lowerBound, double[] upperBound) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61978, _mut61979, _mut61980, _mut61981, _mut61982); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293");
            final double boundDiff = boundDifference[j];
            lowerDifference.setEntry(j, AOR_minus(lowerBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61934, _mut61935, _mut61936, _mut61937));
            upperDifference.setEntry(j, AOR_minus(upperBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61938, _mut61939, _mut61940, _mut61941));
            if (ROR_greater_equals(lowerDifference.getEntry(j), -initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61942, _mut61943, _mut61944, _mut61945, _mut61946)) {
                if (ROR_greater_equals(lowerDifference.getEntry(j), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61965, _mut61966, _mut61967, _mut61968, _mut61969)) {
                    currentBest.setEntry(j, lowerBound[j]);
                    lowerDifference.setEntry(j, ZERO);
                    upperDifference.setEntry(j, boundDiff);
                } else {
                    currentBest.setEntry(j, AOR_plus(lowerBound[j], initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61970, _mut61971, _mut61972, _mut61973));
                    lowerDifference.setEntry(j, -initialTrustRegionRadius);
                    // Computing MAX
                    final double deltaOne = AOR_minus(upperBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61974, _mut61975, _mut61976, _mut61977);
                    upperDifference.setEntry(j, FastMath.max(deltaOne, initialTrustRegionRadius));
                }
            } else if (ROR_less_equals(upperDifference.getEntry(j), initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61947, _mut61948, _mut61949, _mut61950, _mut61951)) {
                if (ROR_less_equals(upperDifference.getEntry(j), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61952, _mut61953, _mut61954, _mut61955, _mut61956)) {
                    currentBest.setEntry(j, upperBound[j]);
                    lowerDifference.setEntry(j, -boundDiff);
                    upperDifference.setEntry(j, ZERO);
                } else {
                    currentBest.setEntry(j, AOR_minus(upperBound[j], initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61957, _mut61958, _mut61959, _mut61960));
                    // Computing MIN
                    final double deltaOne = AOR_minus(lowerBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqa_293", _mut61961, _mut61962, _mut61963, _mut61964);
                    final double deltaTwo = -initialTrustRegionRadius;
                    lowerDifference.setEntry(j, FastMath.min(deltaOne, deltaTwo));
                    upperDifference.setEntry(j, initialTrustRegionRadius);
                }
            }
        }
        return bobyqb(lowerBound, upperBound);
    }

    /**
     *     The arguments N, NPT, X, XL, XU, RHOBEG, RHOEND, IPRINT and MAXFUN
     *       are identical to the corresponding arguments in SUBROUTINE BOBYQA.
     *     XBASE holds a shift of origin that should reduce the contributions
     *       from rounding errors to values of the model and Lagrange functions.
     *     XPT is a two-dimensional array that holds the coordinates of the
     *       interpolation points relative to XBASE.
     *     FVAL holds the values of F at the interpolation points.
     *     XOPT is set to the displacement from XBASE of the trust region centre.
     *     GOPT holds the gradient of the quadratic model at XBASE+XOPT.
     *     HQ holds the explicit second derivatives of the quadratic model.
     *     PQ contains the parameters of the implicit second derivatives of the
     *       quadratic model.
     *     BMAT holds the last N columns of H.
     *     ZMAT holds the factorization of the leading NPT by NPT submatrix of H,
     *       this factorization being ZMAT times ZMAT^T, which provides both the
     *       correct rank and positive semi-definiteness.
     *     NDIM is the first dimension of BMAT and has the value NPT+N.
     *     SL and SU hold the differences XL-XBASE and XU-XBASE, respectively.
     *       All the components of every XOPT are going to satisfy the bounds
     *       SL(I) .LEQ. XOPT(I) .LEQ. SU(I), with appropriate equalities when
     *       XOPT is on a constraint boundary.
     *     XNEW is chosen by SUBROUTINE TRSBOX or ALTMOV. Usually XBASE+XNEW is the
     *       vector of variables for the next call of CALFUN. XNEW also satisfies
     *       the SL and SU constraints in the way that has just been mentioned.
     *     XALT is an alternative to XNEW, chosen by ALTMOV, that may replace XNEW
     *       in order to increase the denominator in the updating of UPDATE.
     *     D is reserved for a trial step from XOPT, which is usually XNEW-XOPT.
     *     VLAG contains the values of the Lagrange functions at a new point X.
     *       They are part of a product that requires VLAG to be of length NDIM.
     *     W is a one-dimensional array that is used for working space. Its length
     *       must be at least 3*NDIM = 3*(NPT+N).
     *
     * @param lowerBound Lower bounds.
     * @param upperBound Upper bounds.
     * @return the value of the objective at the optimum.
     */
    private double bobyqb(double[] lowerBound, double[] upperBound) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int np = AOR_plus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut61983, _mut61984, _mut61985, _mut61986);
        final int nptm = AOR_minus(npt, np, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut61987, _mut61988, _mut61989, _mut61990);
        final int nh = AOR_divide(AOR_multiply(n, np, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut61991, _mut61992, _mut61993, _mut61994), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut61995, _mut61996, _mut61997, _mut61998);
        final ArrayRealVector work1 = new ArrayRealVector(n);
        final ArrayRealVector work2 = new ArrayRealVector(npt);
        final ArrayRealVector work3 = new ArrayRealVector(npt);
        double cauchy = Double.NaN;
        double alpha = Double.NaN;
        double dsq = Double.NaN;
        double crvmin = Double.NaN;
        trustRegionCenterInterpolationPointIndex = 0;
        prelim(lowerBound, upperBound);
        double xoptsq = ZERO;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62003, _mut62004, _mut62005, _mut62006, _mut62007); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
            trustRegionCenterOffset.setEntry(i, interpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex, i));
            // Computing 2nd power
            final double deltaOne = trustRegionCenterOffset.getEntry(i);
            xoptsq += AOR_multiply(deltaOne, deltaOne, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut61999, _mut62000, _mut62001, _mut62002);
        }
        double fsave = fAtInterpolationPoints.getEntry(0);
        final int kbase = 0;
        int ntrits = 0;
        int itest = 0;
        int knew = 0;
        int nfsav = getEvaluations();
        double rho = initialTrustRegionRadius;
        double delta = rho;
        double diffa = ZERO;
        double diffb = ZERO;
        double diffc = ZERO;
        double f = ZERO;
        double beta = ZERO;
        double adelt = ZERO;
        double denom = ZERO;
        double ratio = ZERO;
        double dnorm = ZERO;
        double scaden = ZERO;
        double biglsq = ZERO;
        double distsq = ZERO;
        int state = 20;
        for (; ; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
            switch(state) {
                case 20:
                    {
                        // XXX
                        printState(20);
                        if (ROR_not_equals(trustRegionCenterInterpolationPointIndex, kbase, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62008, _mut62009, _mut62010, _mut62011, _mut62012)) {
                            int ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62039, _mut62040, _mut62041, _mut62042, _mut62043); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62034, _mut62035, _mut62036, _mut62037, _mut62038); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    if (ROR_less(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62013, _mut62014, _mut62015, _mut62016, _mut62017)) {
                                        gradientAtTrustRegionCenter.setEntry(j, AOR_plus(gradientAtTrustRegionCenter.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62018, _mut62019, _mut62020, _mut62021), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62022, _mut62023, _mut62024, _mut62025));
                                    }
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62026, _mut62027, _mut62028, _mut62029), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62030, _mut62031, _mut62032, _mut62033));
                                    ih++;
                                }
                            }
                            if (ROR_greater(getEvaluations(), npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62044, _mut62045, _mut62046, _mut62047, _mut62048)) {
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62071, _mut62072, _mut62073, _mut62074, _mut62075); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    double temp = ZERO;
                                    for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62053, _mut62054, _mut62055, _mut62056, _mut62057); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        temp += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62049, _mut62050, _mut62051, _mut62052);
                                    }
                                    temp *= modelSecondDerivativesParameters.getEntry(k);
                                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62066, _mut62067, _mut62068, _mut62069, _mut62070); i++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62058, _mut62059, _mut62060, _mut62061), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62062, _mut62063, _mut62064, _mut62065));
                                    }
                                }
                            }
                        }
                    }
                case 60:
                    {
                        // XXX
                        printState(60);
                        final ArrayRealVector gnew = new ArrayRealVector(n);
                        final ArrayRealVector xbdi = new ArrayRealVector(n);
                        final ArrayRealVector s = new ArrayRealVector(n);
                        final ArrayRealVector hs = new ArrayRealVector(n);
                        final ArrayRealVector hred = new ArrayRealVector(n);
                        final double[] dsqCrvmin = trsbox(delta, gnew, xbdi, s, hs, hred);
                        dsq = dsqCrvmin[0];
                        crvmin = dsqCrvmin[1];
                        // Computing MIN
                        double deltaOne = delta;
                        double deltaTwo = FastMath.sqrt(dsq);
                        dnorm = FastMath.min(deltaOne, deltaTwo);
                        if (ROR_less(dnorm, AOR_multiply(HALF, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62076, _mut62077, _mut62078, _mut62079), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62080, _mut62081, _mut62082, _mut62083, _mut62084)) {
                            ntrits = -1;
                            // Computing 2nd power
                            deltaOne = AOR_multiply(TEN, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62085, _mut62086, _mut62087, _mut62088);
                            distsq = AOR_multiply(deltaOne, deltaOne, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62089, _mut62090, _mut62091, _mut62092);
                            if (ROR_less_equals(getEvaluations(), AOR_plus(nfsav, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62093, _mut62094, _mut62095, _mut62096), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62097, _mut62098, _mut62099, _mut62100, _mut62101)) {
                                state = 650;
                                break;
                            }
                            // Computing MAX
                            deltaOne = FastMath.max(diffa, diffb);
                            final double errbig = FastMath.max(deltaOne, diffc);
                            final double frhosq = AOR_multiply(AOR_multiply(rho, ONE_OVER_EIGHT, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62102, _mut62103, _mut62104, _mut62105), rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62106, _mut62107, _mut62108, _mut62109);
                            if ((_mut62124 ? (ROR_greater(crvmin, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62110, _mut62111, _mut62112, _mut62113, _mut62114) || ROR_greater(errbig, AOR_multiply(frhosq, crvmin, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62115, _mut62116, _mut62117, _mut62118), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62119, _mut62120, _mut62121, _mut62122, _mut62123)) : (ROR_greater(crvmin, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62110, _mut62111, _mut62112, _mut62113, _mut62114) && ROR_greater(errbig, AOR_multiply(frhosq, crvmin, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62115, _mut62116, _mut62117, _mut62118), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62119, _mut62120, _mut62121, _mut62122, _mut62123)))) {
                                state = 650;
                                break;
                            }
                            final double bdtol = AOR_divide(errbig, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62125, _mut62126, _mut62127, _mut62128);
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62182, _mut62183, _mut62184, _mut62185, _mut62186); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double bdtest = bdtol;
                                if (ROR_equals(newPoint.getEntry(j), lowerDifference.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62129, _mut62130, _mut62131, _mut62132, _mut62133)) {
                                    bdtest = work1.getEntry(j);
                                }
                                if (ROR_equals(newPoint.getEntry(j), upperDifference.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62134, _mut62135, _mut62136, _mut62137, _mut62138)) {
                                    bdtest = -work1.getEntry(j);
                                }
                                if (ROR_less(bdtest, bdtol, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62139, _mut62140, _mut62141, _mut62142, _mut62143)) {
                                    double curv = modelSecondDerivativesValues.getEntry(AOR_divide((AOR_plus(j, AOR_multiply(j, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62144, _mut62145, _mut62146, _mut62147), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62148, _mut62149, _mut62150, _mut62151)), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62152, _mut62153, _mut62154, _mut62155));
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62164, _mut62165, _mut62166, _mut62167, _mut62168); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        // Computing 2nd power
                                        final double d1 = interpolationPoints.getEntry(k, j);
                                        curv += AOR_multiply(modelSecondDerivativesParameters.getEntry(k), (AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62156, _mut62157, _mut62158, _mut62159)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62160, _mut62161, _mut62162, _mut62163);
                                    }
                                    bdtest += AOR_multiply(AOR_multiply(HALF, curv, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62169, _mut62170, _mut62171, _mut62172), rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62173, _mut62174, _mut62175, _mut62176);
                                    if (ROR_less(bdtest, bdtol, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62177, _mut62178, _mut62179, _mut62180, _mut62181)) {
                                        state = 650;
                                        break;
                                    }
                                }
                            }
                            state = 680;
                            break;
                        }
                        ++ntrits;
                    }
                case 90:
                    {
                        // XXX
                        printState(90);
                        if (ROR_less_equals(dsq, AOR_multiply(xoptsq, ONE_OVER_A_THOUSAND, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62187, _mut62188, _mut62189, _mut62190), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62191, _mut62192, _mut62193, _mut62194, _mut62195)) {
                            final double fracsq = AOR_multiply(xoptsq, ONE_OVER_FOUR, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62196, _mut62197, _mut62198, _mut62199);
                            double sumpq = ZERO;
                            // = new ArrayRealVector(npt, -HALF * xoptsq).add(interpolationPoints.operate(trustRegionCenter));
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62263, _mut62264, _mut62265, _mut62266, _mut62267); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                sumpq += modelSecondDerivativesParameters.getEntry(k);
                                double sum = AOR_multiply(-HALF, xoptsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62200, _mut62201, _mut62202, _mut62203);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62208, _mut62209, _mut62210, _mut62211, _mut62212); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    sum += AOR_multiply(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62204, _mut62205, _mut62206, _mut62207);
                                }
                                // sum = sumVector.getEntry(k); // XXX "testAckley" and "testDiffPow" fail.
                                work2.setEntry(k, sum);
                                final double temp = AOR_minus(fracsq, AOR_multiply(HALF, sum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62213, _mut62214, _mut62215, _mut62216), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62217, _mut62218, _mut62219, _mut62220);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62258, _mut62259, _mut62260, _mut62261, _mut62262); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    work1.setEntry(i, bMatrix.getEntry(k, i));
                                    lagrangeValuesAtNewPoint.setEntry(i, AOR_plus(AOR_multiply(sum, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62221, _mut62222, _mut62223, _mut62224), AOR_multiply(temp, trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62225, _mut62226, _mut62227, _mut62228), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62229, _mut62230, _mut62231, _mut62232));
                                    final int ip = AOR_plus(npt, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62233, _mut62234, _mut62235, _mut62236);
                                    for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62253, _mut62254, _mut62255, _mut62256, _mut62257); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        bMatrix.setEntry(ip, j, AOR_plus(AOR_plus(bMatrix.getEntry(ip, j), AOR_multiply(work1.getEntry(i), lagrangeValuesAtNewPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62237, _mut62238, _mut62239, _mut62240), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62241, _mut62242, _mut62243, _mut62244), AOR_multiply(lagrangeValuesAtNewPoint.getEntry(i), work1.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62245, _mut62246, _mut62247, _mut62248), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62249, _mut62250, _mut62251, _mut62252));
                                    }
                                }
                            }
                            for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62342, _mut62343, _mut62344, _mut62345, _mut62346); m++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double sumz = ZERO;
                                double sumw = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62272, _mut62273, _mut62274, _mut62275, _mut62276); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    sumz += zMatrix.getEntry(k, m);
                                    lagrangeValuesAtNewPoint.setEntry(k, AOR_multiply(work2.getEntry(k), zMatrix.getEntry(k, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62268, _mut62269, _mut62270, _mut62271));
                                    sumw += lagrangeValuesAtNewPoint.getEntry(k);
                                }
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62315, _mut62316, _mut62317, _mut62318, _mut62319); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    double sum = AOR_multiply((AOR_minus(AOR_multiply(fracsq, sumz, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62277, _mut62278, _mut62279, _mut62280), AOR_multiply(HALF, sumw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62281, _mut62282, _mut62283, _mut62284), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62285, _mut62286, _mut62287, _mut62288)), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62289, _mut62290, _mut62291, _mut62292);
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62297, _mut62298, _mut62299, _mut62300, _mut62301); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        sum += AOR_multiply(lagrangeValuesAtNewPoint.getEntry(k), interpolationPoints.getEntry(k, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62293, _mut62294, _mut62295, _mut62296);
                                    }
                                    work1.setEntry(j, sum);
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62310, _mut62311, _mut62312, _mut62313, _mut62314); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        bMatrix.setEntry(k, j, AOR_plus(bMatrix.getEntry(k, j), AOR_multiply(sum, zMatrix.getEntry(k, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62302, _mut62303, _mut62304, _mut62305), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62306, _mut62307, _mut62308, _mut62309));
                                    }
                                }
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62337, _mut62338, _mut62339, _mut62340, _mut62341); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    final int ip = AOR_plus(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62320, _mut62321, _mut62322, _mut62323);
                                    final double temp = work1.getEntry(i);
                                    for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62332, _mut62333, _mut62334, _mut62335, _mut62336); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        bMatrix.setEntry(ip, j, AOR_plus(bMatrix.getEntry(ip, j), AOR_multiply(temp, work1.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62324, _mut62325, _mut62326, _mut62327), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62328, _mut62329, _mut62330, _mut62331));
                                    }
                                }
                            }
                            int ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62401, _mut62402, _mut62403, _mut62404, _mut62405); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                work1.setEntry(j, AOR_multiply(AOR_multiply(-HALF, sumpq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62347, _mut62348, _mut62349, _mut62350), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62351, _mut62352, _mut62353, _mut62354));
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62367, _mut62368, _mut62369, _mut62370, _mut62371); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    work1.setEntry(j, AOR_plus(work1.getEntry(j), AOR_multiply(modelSecondDerivativesParameters.getEntry(k), interpolationPoints.getEntry(k, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62355, _mut62356, _mut62357, _mut62358), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62359, _mut62360, _mut62361, _mut62362));
                                    interpolationPoints.setEntry(k, j, AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62363, _mut62364, _mut62365, _mut62366));
                                }
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62396, _mut62397, _mut62398, _mut62399, _mut62400); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    modelSecondDerivativesValues.setEntry(ih, AOR_plus(AOR_plus(modelSecondDerivativesValues.getEntry(ih), AOR_multiply(work1.getEntry(i), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62372, _mut62373, _mut62374, _mut62375), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62376, _mut62377, _mut62378, _mut62379), AOR_multiply(trustRegionCenterOffset.getEntry(i), work1.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62380, _mut62381, _mut62382, _mut62383), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62384, _mut62385, _mut62386, _mut62387));
                                    bMatrix.setEntry(AOR_plus(npt, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62388, _mut62389, _mut62390, _mut62391), j, bMatrix.getEntry(AOR_plus(npt, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62392, _mut62393, _mut62394, _mut62395), i));
                                    ih++;
                                }
                            }
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62422, _mut62423, _mut62424, _mut62425, _mut62426); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                originShift.setEntry(i, AOR_plus(originShift.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62406, _mut62407, _mut62408, _mut62409));
                                newPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62410, _mut62411, _mut62412, _mut62413));
                                lowerDifference.setEntry(i, AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62414, _mut62415, _mut62416, _mut62417));
                                upperDifference.setEntry(i, AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62418, _mut62419, _mut62420, _mut62421));
                                trustRegionCenterOffset.setEntry(i, ZERO);
                            }
                            xoptsq = ZERO;
                        }
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62427, _mut62428, _mut62429, _mut62430, _mut62431)) {
                            state = 210;
                            break;
                        }
                        state = 230;
                        break;
                    }
                case 210:
                    {
                        // XXX
                        printState(210);
                        final double[] alphaCauchy = altmov(knew, adelt);
                        alpha = alphaCauchy[0];
                        cauchy = alphaCauchy[1];
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62436, _mut62437, _mut62438, _mut62439, _mut62440); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62432, _mut62433, _mut62434, _mut62435));
                        }
                    }
                case 230:
                    {
                        // XXX
                        printState(230);
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62470, _mut62471, _mut62472, _mut62473, _mut62474); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            double suma = ZERO;
                            double sumb = ZERO;
                            double sum = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62453, _mut62454, _mut62455, _mut62456, _mut62457); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                suma += AOR_multiply(interpolationPoints.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62441, _mut62442, _mut62443, _mut62444);
                                sumb += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62445, _mut62446, _mut62447, _mut62448);
                                sum += AOR_multiply(bMatrix.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62449, _mut62450, _mut62451, _mut62452);
                            }
                            work3.setEntry(k, AOR_multiply(suma, (AOR_plus(AOR_multiply(HALF, suma, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62458, _mut62459, _mut62460, _mut62461), sumb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62462, _mut62463, _mut62464, _mut62465)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62466, _mut62467, _mut62468, _mut62469));
                            lagrangeValuesAtNewPoint.setEntry(k, sum);
                            work2.setEntry(k, suma);
                        }
                        beta = ZERO;
                        for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62501, _mut62502, _mut62503, _mut62504, _mut62505); m++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            double sum = ZERO;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62479, _mut62480, _mut62481, _mut62482, _mut62483); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                sum += AOR_multiply(zMatrix.getEntry(k, m), work3.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62475, _mut62476, _mut62477, _mut62478);
                            }
                            beta -= AOR_multiply(sum, sum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62484, _mut62485, _mut62486, _mut62487);
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62496, _mut62497, _mut62498, _mut62499, _mut62500); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                lagrangeValuesAtNewPoint.setEntry(k, AOR_plus(lagrangeValuesAtNewPoint.getEntry(k), AOR_multiply(sum, zMatrix.getEntry(k, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62488, _mut62489, _mut62490, _mut62491), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62492, _mut62493, _mut62494, _mut62495));
                            }
                        }
                        dsq = ZERO;
                        double bsum = ZERO;
                        double dx = ZERO;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62544, _mut62545, _mut62546, _mut62547, _mut62548); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(j);
                            dsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62506, _mut62507, _mut62508, _mut62509);
                            double sum = ZERO;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62514, _mut62515, _mut62516, _mut62517, _mut62518); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                sum += AOR_multiply(work3.getEntry(k), bMatrix.getEntry(k, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62510, _mut62511, _mut62512, _mut62513);
                            }
                            bsum += AOR_multiply(sum, trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62519, _mut62520, _mut62521, _mut62522);
                            final int jp = AOR_plus(npt, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62523, _mut62524, _mut62525, _mut62526);
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62531, _mut62532, _mut62533, _mut62534, _mut62535); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                sum += AOR_multiply(bMatrix.getEntry(jp, i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62527, _mut62528, _mut62529, _mut62530);
                            }
                            lagrangeValuesAtNewPoint.setEntry(jp, sum);
                            bsum += AOR_multiply(sum, trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62536, _mut62537, _mut62538, _mut62539);
                            dx += AOR_multiply(trialStepPoint.getEntry(j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62540, _mut62541, _mut62542, _mut62543);
                        }
                        // Original
                        beta = AOR_minus(AOR_plus(AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62549, _mut62550, _mut62551, _mut62552), AOR_multiply(dsq, (AOR_plus(AOR_plus(AOR_plus(xoptsq, dx, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62553, _mut62554, _mut62555, _mut62556), dx, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62557, _mut62558, _mut62559, _mut62560), AOR_multiply(HALF, dsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62561, _mut62562, _mut62563, _mut62564), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62565, _mut62566, _mut62567, _mut62568)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62569, _mut62570, _mut62571, _mut62572), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62573, _mut62574, _mut62575, _mut62576), beta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62577, _mut62578, _mut62579, _mut62580), bsum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62581, _mut62582, _mut62583, _mut62584);
                        lagrangeValuesAtNewPoint.setEntry(trustRegionCenterInterpolationPointIndex, AOR_plus(lagrangeValuesAtNewPoint.getEntry(trustRegionCenterInterpolationPointIndex), ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62585, _mut62586, _mut62587, _mut62588));
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62589, _mut62590, _mut62591, _mut62592, _mut62593)) {
                            // Computing 2nd power
                            final double d1 = lagrangeValuesAtNewPoint.getEntry(knew);
                            denom = AOR_plus(AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62671, _mut62672, _mut62673, _mut62674), AOR_multiply(alpha, beta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62675, _mut62676, _mut62677, _mut62678), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62679, _mut62680, _mut62681, _mut62682);
                            if ((_mut62693 ? (ROR_less(denom, cauchy, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62683, _mut62684, _mut62685, _mut62686, _mut62687) || ROR_greater(cauchy, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62688, _mut62689, _mut62690, _mut62691, _mut62692)) : (ROR_less(denom, cauchy, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62683, _mut62684, _mut62685, _mut62686, _mut62687) && ROR_greater(cauchy, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62688, _mut62689, _mut62690, _mut62691, _mut62692)))) {
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62698, _mut62699, _mut62700, _mut62701, _mut62702); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    newPoint.setEntry(i, alternativeNewPoint.getEntry(i));
                                    trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62694, _mut62695, _mut62696, _mut62697));
                                }
                                // XXX Useful statement?
                                cauchy = ZERO;
                                state = 230;
                                break;
                            }
                        } else {
                            final double delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62594, _mut62595, _mut62596, _mut62597);
                            scaden = ZERO;
                            biglsq = ZERO;
                            knew = 0;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62666, _mut62667, _mut62668, _mut62669, _mut62670); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                if (ROR_equals(k, trustRegionCenterInterpolationPointIndex, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62598, _mut62599, _mut62600, _mut62601, _mut62602)) {
                                    continue;
                                }
                                double hdiag = ZERO;
                                for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62607, _mut62608, _mut62609, _mut62610, _mut62611); m++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    // Computing 2nd power
                                    final double d1 = zMatrix.getEntry(k, m);
                                    hdiag += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62603, _mut62604, _mut62605, _mut62606);
                                }
                                // Computing 2nd power
                                final double d2 = lagrangeValuesAtNewPoint.getEntry(k);
                                final double den = AOR_plus(AOR_multiply(beta, hdiag, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62612, _mut62613, _mut62614, _mut62615), AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62616, _mut62617, _mut62618, _mut62619), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62620, _mut62621, _mut62622, _mut62623);
                                distsq = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62632, _mut62633, _mut62634, _mut62635, _mut62636); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    // Computing 2nd power
                                    final double d3 = AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62624, _mut62625, _mut62626, _mut62627);
                                    distsq += AOR_multiply(d3, d3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62628, _mut62629, _mut62630, _mut62631);
                                }
                                // Computing 2nd power
                                final double d4 = AOR_divide(distsq, delsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62637, _mut62638, _mut62639, _mut62640);
                                final double temp = FastMath.max(ONE, AOR_multiply(d4, d4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62641, _mut62642, _mut62643, _mut62644));
                                if (ROR_greater(AOR_multiply(temp, den, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62645, _mut62646, _mut62647, _mut62648), scaden, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62649, _mut62650, _mut62651, _mut62652, _mut62653)) {
                                    scaden = AOR_multiply(temp, den, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62654, _mut62655, _mut62656, _mut62657);
                                    knew = k;
                                    denom = den;
                                }
                                // Computing 2nd power
                                final double d5 = lagrangeValuesAtNewPoint.getEntry(k);
                                biglsq = FastMath.max(biglsq, AOR_multiply(temp, (AOR_multiply(d5, d5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62658, _mut62659, _mut62660, _mut62661)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62662, _mut62663, _mut62664, _mut62665));
                            }
                        }
                    }
                case 360:
                    {
                        // XXX
                        printState(360);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62717, _mut62718, _mut62719, _mut62720, _mut62721); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            // Computing MAX
                            final double d3 = lowerBound[i];
                            final double d4 = AOR_plus(originShift.getEntry(i), newPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62703, _mut62704, _mut62705, _mut62706);
                            final double d1 = FastMath.max(d3, d4);
                            final double d2 = upperBound[i];
                            currentBest.setEntry(i, FastMath.min(d1, d2));
                            if (ROR_equals(newPoint.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62707, _mut62708, _mut62709, _mut62710, _mut62711)) {
                                currentBest.setEntry(i, lowerBound[i]);
                            }
                            if (ROR_equals(newPoint.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62712, _mut62713, _mut62714, _mut62715, _mut62716)) {
                                currentBest.setEntry(i, upperBound[i]);
                            }
                        }
                        f = computeObjectiveValue(currentBest.toArray());
                        if (!isMinimize) {
                            f = -f;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62722, _mut62723, _mut62724, _mut62725, _mut62726)) {
                            fsave = f;
                            state = 720;
                            break;
                        }
                        final double fopt = fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex);
                        double vquad = ZERO;
                        int ih = 0;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62749, _mut62750, _mut62751, _mut62752, _mut62753); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            vquad += AOR_multiply(trialStepPoint.getEntry(j), gradientAtTrustRegionCenter.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62727, _mut62728, _mut62729, _mut62730);
                            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62744, _mut62745, _mut62746, _mut62747, _mut62748); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double temp = AOR_multiply(trialStepPoint.getEntry(i), trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62731, _mut62732, _mut62733, _mut62734);
                                if (ROR_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62735, _mut62736, _mut62737, _mut62738, _mut62739)) {
                                    temp *= HALF;
                                }
                                vquad += AOR_multiply(modelSecondDerivativesValues.getEntry(ih), temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62740, _mut62741, _mut62742, _mut62743);
                                ih++;
                            }
                        }
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62766, _mut62767, _mut62768, _mut62769, _mut62770); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            // Computing 2nd power
                            final double d1 = work2.getEntry(k);
                            // "d1" must be squared first to prevent test failures.
                            final double d2 = AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62754, _mut62755, _mut62756, _mut62757);
                            vquad += AOR_multiply(AOR_multiply(HALF, modelSecondDerivativesParameters.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62758, _mut62759, _mut62760, _mut62761), d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62762, _mut62763, _mut62764, _mut62765);
                        }
                        final double diff = AOR_minus(AOR_minus(f, fopt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62771, _mut62772, _mut62773, _mut62774), vquad, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62775, _mut62776, _mut62777, _mut62778);
                        diffc = diffb;
                        diffb = diffa;
                        diffa = FastMath.abs(diff);
                        if (ROR_greater(dnorm, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62779, _mut62780, _mut62781, _mut62782, _mut62783)) {
                            nfsav = getEvaluations();
                        }
                        if (ROR_greater(ntrits, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62784, _mut62785, _mut62786, _mut62787, _mut62788)) {
                            if (ROR_greater_equals(vquad, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62789, _mut62790, _mut62791, _mut62792, _mut62793)) {
                                throw new MathIllegalStateException(LocalizedFormats.TRUST_REGION_STEP_FAILED, vquad);
                            }
                            ratio = AOR_divide((AOR_minus(f, fopt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62794, _mut62795, _mut62796, _mut62797)), vquad, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62798, _mut62799, _mut62800, _mut62801);
                            final double hDelta = AOR_multiply(HALF, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62802, _mut62803, _mut62804, _mut62805);
                            if (ROR_less_equals(ratio, ONE_OVER_TEN, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62806, _mut62807, _mut62808, _mut62809, _mut62810)) {
                                // Computing MIN
                                delta = FastMath.min(hDelta, dnorm);
                            } else if (ROR_less_equals(ratio, .7, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62811, _mut62812, _mut62813, _mut62814, _mut62815)) {
                                // Computing MAX
                                delta = FastMath.max(hDelta, dnorm);
                            } else {
                                // Computing MAX
                                delta = FastMath.max(hDelta, AOR_multiply(2, dnorm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62816, _mut62817, _mut62818, _mut62819));
                            }
                            if (ROR_less_equals(delta, AOR_multiply(rho, 1.5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62820, _mut62821, _mut62822, _mut62823), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62824, _mut62825, _mut62826, _mut62827, _mut62828)) {
                                delta = rho;
                            }
                            if (ROR_less(f, fopt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62829, _mut62830, _mut62831, _mut62832, _mut62833)) {
                                final int ksav = knew;
                                final double densav = denom;
                                final double delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62834, _mut62835, _mut62836, _mut62837);
                                scaden = ZERO;
                                biglsq = ZERO;
                                knew = 0;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62901, _mut62902, _mut62903, _mut62904, _mut62905); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    double hdiag = ZERO;
                                    for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62842, _mut62843, _mut62844, _mut62845, _mut62846); m++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        // Computing 2nd power
                                        final double d1 = zMatrix.getEntry(k, m);
                                        hdiag += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62838, _mut62839, _mut62840, _mut62841);
                                    }
                                    // Computing 2nd power
                                    final double d1 = lagrangeValuesAtNewPoint.getEntry(k);
                                    final double den = AOR_plus(AOR_multiply(beta, hdiag, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62847, _mut62848, _mut62849, _mut62850), AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62851, _mut62852, _mut62853, _mut62854), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62855, _mut62856, _mut62857, _mut62858);
                                    distsq = ZERO;
                                    for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62867, _mut62868, _mut62869, _mut62870, _mut62871); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                        // Computing 2nd power
                                        final double d2 = AOR_minus(interpolationPoints.getEntry(k, j), newPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62859, _mut62860, _mut62861, _mut62862);
                                        distsq += AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62863, _mut62864, _mut62865, _mut62866);
                                    }
                                    // Computing 2nd power
                                    final double d3 = AOR_divide(distsq, delsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62872, _mut62873, _mut62874, _mut62875);
                                    final double temp = FastMath.max(ONE, AOR_multiply(d3, d3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62876, _mut62877, _mut62878, _mut62879));
                                    if (ROR_greater(AOR_multiply(temp, den, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62880, _mut62881, _mut62882, _mut62883), scaden, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62884, _mut62885, _mut62886, _mut62887, _mut62888)) {
                                        scaden = AOR_multiply(temp, den, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62889, _mut62890, _mut62891, _mut62892);
                                        knew = k;
                                        denom = den;
                                    }
                                    // Computing 2nd power
                                    final double d4 = lagrangeValuesAtNewPoint.getEntry(k);
                                    final double d5 = AOR_multiply(temp, (AOR_multiply(d4, d4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62893, _mut62894, _mut62895, _mut62896)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62897, _mut62898, _mut62899, _mut62900);
                                    biglsq = FastMath.max(biglsq, d5);
                                }
                                if (ROR_less_equals(scaden, AOR_multiply(HALF, biglsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62906, _mut62907, _mut62908, _mut62909), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62910, _mut62911, _mut62912, _mut62913, _mut62914)) {
                                    knew = ksav;
                                    denom = densav;
                                }
                            }
                        }
                        update(beta, denom, knew);
                        ih = 0;
                        final double pqold = modelSecondDerivativesParameters.getEntry(knew);
                        modelSecondDerivativesParameters.setEntry(knew, ZERO);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62932, _mut62933, _mut62934, _mut62935, _mut62936); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            final double temp = AOR_multiply(pqold, interpolationPoints.getEntry(knew, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62915, _mut62916, _mut62917, _mut62918);
                            for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62927, _mut62928, _mut62929, _mut62930, _mut62931); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                modelSecondDerivativesValues.setEntry(ih, AOR_plus(modelSecondDerivativesValues.getEntry(ih), AOR_multiply(temp, interpolationPoints.getEntry(knew, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62919, _mut62920, _mut62921, _mut62922), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62923, _mut62924, _mut62925, _mut62926));
                                ih++;
                            }
                        }
                        for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62954, _mut62955, _mut62956, _mut62957, _mut62958); m++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            final double temp = AOR_multiply(diff, zMatrix.getEntry(knew, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62937, _mut62938, _mut62939, _mut62940);
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62949, _mut62950, _mut62951, _mut62952, _mut62953); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                modelSecondDerivativesParameters.setEntry(k, AOR_plus(modelSecondDerivativesParameters.getEntry(k), AOR_multiply(temp, zMatrix.getEntry(k, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62941, _mut62942, _mut62943, _mut62944), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62945, _mut62946, _mut62947, _mut62948));
                            }
                        }
                        fAtInterpolationPoints.setEntry(knew, f);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62959, _mut62960, _mut62961, _mut62962, _mut62963); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            interpolationPoints.setEntry(knew, i, newPoint.getEntry(i));
                            work1.setEntry(i, bMatrix.getEntry(knew, i));
                        }
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62999, _mut63000, _mut63001, _mut63002, _mut63003); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            double suma = ZERO;
                            for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62968, _mut62969, _mut62970, _mut62971, _mut62972); m++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                suma += AOR_multiply(zMatrix.getEntry(knew, m), zMatrix.getEntry(k, m), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62964, _mut62965, _mut62966, _mut62967);
                            }
                            double sumb = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62977, _mut62978, _mut62979, _mut62980, _mut62981); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                sumb += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62973, _mut62974, _mut62975, _mut62976);
                            }
                            final double temp = AOR_multiply(suma, sumb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62982, _mut62983, _mut62984, _mut62985);
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62994, _mut62995, _mut62996, _mut62997, _mut62998); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                work1.setEntry(i, AOR_plus(work1.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62986, _mut62987, _mut62988, _mut62989), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut62990, _mut62991, _mut62992, _mut62993));
                            }
                        }
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63012, _mut63013, _mut63014, _mut63015, _mut63016); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(diff, work1.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63004, _mut63005, _mut63006, _mut63007), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63008, _mut63009, _mut63010, _mut63011));
                        }
                        if (ROR_less(f, fopt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63017, _mut63018, _mut63019, _mut63020, _mut63021)) {
                            trustRegionCenterInterpolationPointIndex = knew;
                            xoptsq = ZERO;
                            ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63052, _mut63053, _mut63054, _mut63055, _mut63056); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                trustRegionCenterOffset.setEntry(j, newPoint.getEntry(j));
                                // Computing 2nd power
                                final double d1 = trustRegionCenterOffset.getEntry(j);
                                xoptsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63022, _mut63023, _mut63024, _mut63025);
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63047, _mut63048, _mut63049, _mut63050, _mut63051); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    if (ROR_less(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63026, _mut63027, _mut63028, _mut63029, _mut63030)) {
                                        gradientAtTrustRegionCenter.setEntry(j, AOR_plus(gradientAtTrustRegionCenter.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63031, _mut63032, _mut63033, _mut63034), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63035, _mut63036, _mut63037, _mut63038));
                                    }
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63039, _mut63040, _mut63041, _mut63042), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63043, _mut63044, _mut63045, _mut63046));
                                    ih++;
                                }
                            }
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63079, _mut63080, _mut63081, _mut63082, _mut63083); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double temp = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63061, _mut63062, _mut63063, _mut63064, _mut63065); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    temp += AOR_multiply(interpolationPoints.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63057, _mut63058, _mut63059, _mut63060);
                                }
                                temp *= modelSecondDerivativesParameters.getEntry(k);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63074, _mut63075, _mut63076, _mut63077, _mut63078); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63066, _mut63067, _mut63068, _mut63069), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63070, _mut63071, _mut63072, _mut63073));
                                }
                            }
                        }
                        if (ROR_greater(ntrits, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63084, _mut63085, _mut63086, _mut63087, _mut63088)) {
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63093, _mut63094, _mut63095, _mut63096, _mut63097); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                lagrangeValuesAtNewPoint.setEntry(k, AOR_minus(fAtInterpolationPoints.getEntry(k), fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63089, _mut63090, _mut63091, _mut63092));
                                work3.setEntry(k, ZERO);
                            }
                            for (int j = 0; ROR_less(j, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63120, _mut63121, _mut63122, _mut63123, _mut63124); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double sum = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63102, _mut63103, _mut63104, _mut63105, _mut63106); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    sum += AOR_multiply(zMatrix.getEntry(k, j), lagrangeValuesAtNewPoint.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63098, _mut63099, _mut63100, _mut63101);
                                }
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63115, _mut63116, _mut63117, _mut63118, _mut63119); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    work3.setEntry(k, AOR_plus(work3.getEntry(k), AOR_multiply(sum, zMatrix.getEntry(k, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63107, _mut63108, _mut63109, _mut63110), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63111, _mut63112, _mut63113, _mut63114));
                                }
                            }
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63138, _mut63139, _mut63140, _mut63141, _mut63142); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double sum = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63129, _mut63130, _mut63131, _mut63132, _mut63133); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    sum += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63125, _mut63126, _mut63127, _mut63128);
                                }
                                work2.setEntry(k, work3.getEntry(k));
                                work3.setEntry(k, AOR_multiply(sum, work3.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63134, _mut63135, _mut63136, _mut63137));
                            }
                            double gqsq = ZERO;
                            double gisq = ZERO;
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63198, _mut63199, _mut63200, _mut63201, _mut63202); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                double sum = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63155, _mut63156, _mut63157, _mut63158, _mut63159); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    sum += AOR_plus(AOR_multiply(bMatrix.getEntry(k, i), lagrangeValuesAtNewPoint.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63143, _mut63144, _mut63145, _mut63146), AOR_multiply(interpolationPoints.getEntry(k, i), work3.getEntry(k), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63147, _mut63148, _mut63149, _mut63150), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63151, _mut63152, _mut63153, _mut63154);
                                }
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63160, _mut63161, _mut63162, _mut63163, _mut63164)) {
                                    // Computing 2nd power
                                    final double d1 = FastMath.min(ZERO, gradientAtTrustRegionCenter.getEntry(i));
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63186, _mut63187, _mut63188, _mut63189);
                                    // Computing 2nd power
                                    final double d2 = FastMath.min(ZERO, sum);
                                    gisq += AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63190, _mut63191, _mut63192, _mut63193);
                                } else if (ROR_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63165, _mut63166, _mut63167, _mut63168, _mut63169)) {
                                    // Computing 2nd power
                                    final double d1 = FastMath.max(ZERO, gradientAtTrustRegionCenter.getEntry(i));
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63178, _mut63179, _mut63180, _mut63181);
                                    // Computing 2nd power
                                    final double d2 = FastMath.max(ZERO, sum);
                                    gisq += AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63182, _mut63183, _mut63184, _mut63185);
                                } else {
                                    // Computing 2nd power
                                    final double d1 = gradientAtTrustRegionCenter.getEntry(i);
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63170, _mut63171, _mut63172, _mut63173);
                                    gisq += AOR_multiply(sum, sum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63174, _mut63175, _mut63176, _mut63177);
                                }
                                lagrangeValuesAtNewPoint.setEntry(AOR_plus(npt, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63194, _mut63195, _mut63196, _mut63197), sum);
                            }
                            ++itest;
                            if (ROR_less(gqsq, AOR_multiply(TEN, gisq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63203, _mut63204, _mut63205, _mut63206), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63207, _mut63208, _mut63209, _mut63210, _mut63211)) {
                                itest = 0;
                            }
                            if (ROR_greater_equals(itest, 3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63212, _mut63213, _mut63214, _mut63215, _mut63216)) {
                                for (int i = 0, max = FastMath.max(npt, nh); ROR_less(i, max, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63236, _mut63237, _mut63238, _mut63239, _mut63240); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                    if (ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63217, _mut63218, _mut63219, _mut63220, _mut63221)) {
                                        gradientAtTrustRegionCenter.setEntry(i, lagrangeValuesAtNewPoint.getEntry(AOR_plus(npt, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63222, _mut63223, _mut63224, _mut63225)));
                                    }
                                    if (ROR_less(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63226, _mut63227, _mut63228, _mut63229, _mut63230)) {
                                        modelSecondDerivativesParameters.setEntry(i, work2.getEntry(i));
                                    }
                                    if (ROR_less(i, nh, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63231, _mut63232, _mut63233, _mut63234, _mut63235)) {
                                        modelSecondDerivativesValues.setEntry(i, ZERO);
                                    }
                                    itest = 0;
                                }
                            }
                        }
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63241, _mut63242, _mut63243, _mut63244, _mut63245)) {
                            state = 60;
                            break;
                        }
                        if (ROR_less_equals(f, AOR_plus(fopt, AOR_multiply(ONE_OVER_TEN, vquad, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63246, _mut63247, _mut63248, _mut63249), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63250, _mut63251, _mut63252, _mut63253), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63254, _mut63255, _mut63256, _mut63257, _mut63258)) {
                            state = 60;
                            break;
                        }
                        // Computing 2nd power
                        final double d1 = AOR_multiply(TWO, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63259, _mut63260, _mut63261, _mut63262);
                        // Computing 2nd power
                        final double d2 = AOR_multiply(TEN, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63263, _mut63264, _mut63265, _mut63266);
                        distsq = FastMath.max(AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63267, _mut63268, _mut63269, _mut63270), AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63271, _mut63272, _mut63273, _mut63274));
                    }
                case 650:
                    {
                        // XXX
                        printState(650);
                        knew = -1;
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63293, _mut63294, _mut63295, _mut63296, _mut63297); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                            double sum = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63283, _mut63284, _mut63285, _mut63286, _mut63287); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                // Computing 2nd power
                                final double d1 = AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63275, _mut63276, _mut63277, _mut63278);
                                sum += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63279, _mut63280, _mut63281, _mut63282);
                            }
                            if (ROR_greater(sum, distsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63288, _mut63289, _mut63290, _mut63291, _mut63292)) {
                                knew = k;
                                distsq = sum;
                            }
                        }
                        if (ROR_greater_equals(knew, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63298, _mut63299, _mut63300, _mut63301, _mut63302)) {
                            final double dist = FastMath.sqrt(distsq);
                            if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63303, _mut63304, _mut63305, _mut63306, _mut63307)) {
                                // Computing MIN
                                delta = FastMath.min(AOR_multiply(ONE_OVER_TEN, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63308, _mut63309, _mut63310, _mut63311), AOR_multiply(HALF, dist, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63312, _mut63313, _mut63314, _mut63315));
                                if (ROR_less_equals(delta, AOR_multiply(rho, 1.5, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63316, _mut63317, _mut63318, _mut63319), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63320, _mut63321, _mut63322, _mut63323, _mut63324)) {
                                    delta = rho;
                                }
                            }
                            ntrits = 0;
                            // Computing MIN
                            final double d1 = FastMath.min(AOR_multiply(ONE_OVER_TEN, dist, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63325, _mut63326, _mut63327, _mut63328), delta);
                            adelt = FastMath.max(d1, rho);
                            dsq = AOR_multiply(adelt, adelt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63329, _mut63330, _mut63331, _mut63332);
                            state = 90;
                            break;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63333, _mut63334, _mut63335, _mut63336, _mut63337)) {
                            state = 680;
                            break;
                        }
                        if (ROR_greater(ratio, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63338, _mut63339, _mut63340, _mut63341, _mut63342)) {
                            state = 60;
                            break;
                        }
                        if (ROR_greater(FastMath.max(delta, dnorm), rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63343, _mut63344, _mut63345, _mut63346, _mut63347)) {
                            state = 60;
                            break;
                        }
                    }
                case 680:
                    {
                        // XXX
                        printState(680);
                        if (ROR_greater(rho, stoppingTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63348, _mut63349, _mut63350, _mut63351, _mut63352)) {
                            delta = AOR_multiply(HALF, rho, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63353, _mut63354, _mut63355, _mut63356);
                            ratio = AOR_divide(rho, stoppingTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63357, _mut63358, _mut63359, _mut63360);
                            if (ROR_less_equals(ratio, SIXTEEN, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63361, _mut63362, _mut63363, _mut63364, _mut63365)) {
                                rho = stoppingTrustRegionRadius;
                            } else if (ROR_less_equals(ratio, TWO_HUNDRED_FIFTY, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63366, _mut63367, _mut63368, _mut63369, _mut63370)) {
                                rho = AOR_multiply(FastMath.sqrt(ratio), stoppingTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63371, _mut63372, _mut63373, _mut63374);
                            } else {
                                rho *= ONE_OVER_TEN;
                            }
                            delta = FastMath.max(delta, rho);
                            ntrits = 0;
                            nfsav = getEvaluations();
                            state = 60;
                            break;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63375, _mut63376, _mut63377, _mut63378, _mut63379)) {
                            state = 360;
                            break;
                        }
                    }
                case 720:
                    {
                        // XXX
                        printState(720);
                        if (ROR_less_equals(fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), fsave, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63380, _mut63381, _mut63382, _mut63383, _mut63384)) {
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63399, _mut63400, _mut63401, _mut63402, _mut63403); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382");
                                // Computing MAX
                                final double d3 = lowerBound[i];
                                final double d4 = AOR_plus(originShift.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63385, _mut63386, _mut63387, _mut63388);
                                final double d1 = FastMath.max(d3, d4);
                                final double d2 = upperBound[i];
                                currentBest.setEntry(i, FastMath.min(d1, d2));
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63389, _mut63390, _mut63391, _mut63392, _mut63393)) {
                                    currentBest.setEntry(i, lowerBound[i]);
                                }
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.bobyqb_382", _mut63394, _mut63395, _mut63396, _mut63397, _mut63398)) {
                                    currentBest.setEntry(i, upperBound[i]);
                                }
                            }
                            f = fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex);
                        }
                        return f;
                    }
                default:
                    {
                        throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, "bobyqb");
                    }
            }
        }
    }

    /**
     *     The arguments N, NPT, XPT, XOPT, BMAT, ZMAT, NDIM, SL and SU all have
     *       the same meanings as the corresponding arguments of BOBYQB.
     *     KOPT is the index of the optimal interpolation point.
     *     KNEW is the index of the interpolation point that is going to be moved.
     *     ADELT is the current trust region bound.
     *     XNEW will be set to a suitable new position for the interpolation point
     *       XPT(KNEW,.). Specifically, it satisfies the SL, SU and trust region
     *       bounds and it should provide a large denominator in the next call of
     *       UPDATE. The step XNEW-XOPT from XOPT is restricted to moves along the
     *       straight lines through XOPT and another interpolation point.
     *     XALT also provides a large value of the modulus of the KNEW-th Lagrange
     *       function subject to the constraints that have been mentioned, its main
     *       difference from XNEW being that XALT-XOPT is a constrained version of
     *       the Cauchy step within the trust region. An exception is that XALT is
     *       not calculated if all components of GLAG (see below) are zero.
     *     ALPHA will be set to the KNEW-th diagonal element of the H matrix.
     *     CAUCHY will be set to the square of the KNEW-th Lagrange function at
     *       the step XALT-XOPT from XOPT for the vector XALT that is returned,
     *       except that CAUCHY is set to zero if XALT is not calculated.
     *     GLAG is a working space vector of length N for the gradient of the
     *       KNEW-th Lagrange function at XOPT.
     *     HCOL is a working space vector of length NPT for the second derivative
     *       coefficients of the KNEW-th Lagrange function.
     *     W is a working space vector of length 2N that is going to hold the
     *       constrained Cauchy step from XOPT of the Lagrange function, followed
     *       by the downhill version of XALT when the uphill step is calculated.
     *
     *     Set the first NPT components of W to the leading elements of the
     *     KNEW-th column of the H matrix.
     * @param knew
     * @param adelt
     */
    private double[] altmov(int knew, double adelt) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final ArrayRealVector glag = new ArrayRealVector(n);
        final ArrayRealVector hcol = new ArrayRealVector(npt);
        final ArrayRealVector work1 = new ArrayRealVector(n);
        final ArrayRealVector work2 = new ArrayRealVector(n);
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63404, _mut63405, _mut63406, _mut63407, _mut63408); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            hcol.setEntry(k, ZERO);
        }
        for (int j = 0, max = npt - n - 1; ROR_less(j, max, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63422, _mut63423, _mut63424, _mut63425, _mut63426); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            final double tmp = zMatrix.getEntry(knew, j);
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63417, _mut63418, _mut63419, _mut63420, _mut63421); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                hcol.setEntry(k, AOR_plus(hcol.getEntry(k), AOR_multiply(tmp, zMatrix.getEntry(k, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63409, _mut63410, _mut63411, _mut63412), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63413, _mut63414, _mut63415, _mut63416));
            }
        }
        final double alpha = hcol.getEntry(knew);
        final double ha = AOR_multiply(HALF, alpha, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63427, _mut63428, _mut63429, _mut63430);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63431, _mut63432, _mut63433, _mut63434, _mut63435); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            glag.setEntry(i, bMatrix.getEntry(knew, i));
        }
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63458, _mut63459, _mut63460, _mut63461, _mut63462); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            double tmp = ZERO;
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63440, _mut63441, _mut63442, _mut63443, _mut63444); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                tmp += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63436, _mut63437, _mut63438, _mut63439);
            }
            tmp *= hcol.getEntry(k);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63453, _mut63454, _mut63455, _mut63456, _mut63457); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                glag.setEntry(i, AOR_plus(glag.getEntry(i), AOR_multiply(tmp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63445, _mut63446, _mut63447, _mut63448), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63449, _mut63450, _mut63451, _mut63452));
            }
        }
        double presav = ZERO;
        double step = Double.NaN;
        int ksav = 0;
        int ibdsav = 0;
        double stpsav = 0;
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63765, _mut63766, _mut63767, _mut63768, _mut63769); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            if (ROR_equals(k, trustRegionCenterInterpolationPointIndex, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63463, _mut63464, _mut63465, _mut63466, _mut63467)) {
                continue;
            }
            double dderiv = ZERO;
            double distsq = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63480, _mut63481, _mut63482, _mut63483, _mut63484); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                final double tmp = AOR_minus(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63468, _mut63469, _mut63470, _mut63471);
                dderiv += AOR_multiply(glag.getEntry(i), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63472, _mut63473, _mut63474, _mut63475);
                distsq += AOR_multiply(tmp, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63476, _mut63477, _mut63478, _mut63479);
            }
            double subd = AOR_divide(adelt, FastMath.sqrt(distsq), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63485, _mut63486, _mut63487, _mut63488);
            double slbd = -subd;
            int ilbd = 0;
            int iubd = 0;
            final double sumin = FastMath.min(ONE, subd);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63603, _mut63604, _mut63605, _mut63606, _mut63607); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                final double tmp = AOR_minus(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63489, _mut63490, _mut63491, _mut63492);
                if (ROR_greater(tmp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63493, _mut63494, _mut63495, _mut63496, _mut63497)) {
                    if (ROR_less(AOR_multiply(slbd, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63553, _mut63554, _mut63555, _mut63556), AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63557, _mut63558, _mut63559, _mut63560), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63561, _mut63562, _mut63563, _mut63564, _mut63565)) {
                        slbd = AOR_divide((AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63566, _mut63567, _mut63568, _mut63569)), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63570, _mut63571, _mut63572, _mut63573);
                        ilbd = AOR_minus(-i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63574, _mut63575, _mut63576, _mut63577);
                    }
                    if (ROR_greater(AOR_multiply(subd, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63578, _mut63579, _mut63580, _mut63581), AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63582, _mut63583, _mut63584, _mut63585), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63586, _mut63587, _mut63588, _mut63589, _mut63590)) {
                        // Computing MAX
                        subd = FastMath.max(sumin, AOR_divide((AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63591, _mut63592, _mut63593, _mut63594)), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63595, _mut63596, _mut63597, _mut63598));
                        iubd = AOR_plus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63599, _mut63600, _mut63601, _mut63602);
                    }
                } else if (ROR_less(tmp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63498, _mut63499, _mut63500, _mut63501, _mut63502)) {
                    if (ROR_greater(AOR_multiply(slbd, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63503, _mut63504, _mut63505, _mut63506), AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63507, _mut63508, _mut63509, _mut63510), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63511, _mut63512, _mut63513, _mut63514, _mut63515)) {
                        slbd = AOR_divide((AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63516, _mut63517, _mut63518, _mut63519)), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63520, _mut63521, _mut63522, _mut63523);
                        ilbd = AOR_plus(i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63524, _mut63525, _mut63526, _mut63527);
                    }
                    if (ROR_less(AOR_multiply(subd, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63528, _mut63529, _mut63530, _mut63531), AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63532, _mut63533, _mut63534, _mut63535), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63536, _mut63537, _mut63538, _mut63539, _mut63540)) {
                        // Computing MAX
                        subd = FastMath.max(sumin, AOR_divide((AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63541, _mut63542, _mut63543, _mut63544)), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63545, _mut63546, _mut63547, _mut63548));
                        iubd = AOR_minus(-i, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63549, _mut63550, _mut63551, _mut63552);
                    }
                }
            }
            step = slbd;
            int isbd = ilbd;
            double vlag = Double.NaN;
            if (ROR_equals(k, knew, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63608, _mut63609, _mut63610, _mut63611, _mut63612)) {
                final double diff = AOR_minus(dderiv, ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63645, _mut63646, _mut63647, _mut63648);
                vlag = AOR_multiply(slbd, (AOR_minus(dderiv, AOR_multiply(slbd, diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63649, _mut63650, _mut63651, _mut63652), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63653, _mut63654, _mut63655, _mut63656)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63657, _mut63658, _mut63659, _mut63660);
                final double d1 = AOR_multiply(subd, (AOR_minus(dderiv, AOR_multiply(subd, diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63661, _mut63662, _mut63663, _mut63664), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63665, _mut63666, _mut63667, _mut63668)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63669, _mut63670, _mut63671, _mut63672);
                if (ROR_greater(FastMath.abs(d1), FastMath.abs(vlag), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63673, _mut63674, _mut63675, _mut63676, _mut63677)) {
                    step = subd;
                    vlag = d1;
                    isbd = iubd;
                }
                final double d2 = AOR_multiply(HALF, dderiv, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63678, _mut63679, _mut63680, _mut63681);
                final double d3 = AOR_minus(d2, AOR_multiply(diff, slbd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63682, _mut63683, _mut63684, _mut63685), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63686, _mut63687, _mut63688, _mut63689);
                final double d4 = AOR_minus(d2, AOR_multiply(diff, subd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63690, _mut63691, _mut63692, _mut63693), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63694, _mut63695, _mut63696, _mut63697);
                if (ROR_less(AOR_multiply(d3, d4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63698, _mut63699, _mut63700, _mut63701), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63702, _mut63703, _mut63704, _mut63705, _mut63706)) {
                    final double d5 = AOR_divide(AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63707, _mut63708, _mut63709, _mut63710), diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63711, _mut63712, _mut63713, _mut63714);
                    if (ROR_greater(FastMath.abs(d5), FastMath.abs(vlag), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63715, _mut63716, _mut63717, _mut63718, _mut63719)) {
                        step = AOR_divide(d2, diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63720, _mut63721, _mut63722, _mut63723);
                        vlag = d5;
                        isbd = 0;
                    }
                }
            } else {
                vlag = AOR_multiply(slbd, (AOR_minus(ONE, slbd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63613, _mut63614, _mut63615, _mut63616)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63617, _mut63618, _mut63619, _mut63620);
                final double tmp = AOR_multiply(subd, (AOR_minus(ONE, subd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63621, _mut63622, _mut63623, _mut63624)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63625, _mut63626, _mut63627, _mut63628);
                if (ROR_greater(FastMath.abs(tmp), FastMath.abs(vlag), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63629, _mut63630, _mut63631, _mut63632, _mut63633)) {
                    step = subd;
                    vlag = tmp;
                    isbd = iubd;
                }
                if ((_mut63644 ? (ROR_greater(subd, HALF, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63634, _mut63635, _mut63636, _mut63637, _mut63638) || ROR_less(FastMath.abs(vlag), ONE_OVER_FOUR, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63639, _mut63640, _mut63641, _mut63642, _mut63643)) : (ROR_greater(subd, HALF, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63634, _mut63635, _mut63636, _mut63637, _mut63638) && ROR_less(FastMath.abs(vlag), ONE_OVER_FOUR, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63639, _mut63640, _mut63641, _mut63642, _mut63643)))) {
                    step = HALF;
                    vlag = ONE_OVER_FOUR;
                    isbd = 0;
                }
                vlag *= dderiv;
            }
            final double tmp = AOR_multiply(AOR_multiply(step, (AOR_minus(ONE, step, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63724, _mut63725, _mut63726, _mut63727)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63728, _mut63729, _mut63730, _mut63731), distsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63732, _mut63733, _mut63734, _mut63735);
            final double predsq = AOR_multiply(AOR_multiply(vlag, vlag, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63736, _mut63737, _mut63738, _mut63739), (AOR_plus(AOR_multiply(vlag, vlag, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63740, _mut63741, _mut63742, _mut63743), AOR_multiply(AOR_multiply(ha, tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63744, _mut63745, _mut63746, _mut63747), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63748, _mut63749, _mut63750, _mut63751), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63752, _mut63753, _mut63754, _mut63755)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63756, _mut63757, _mut63758, _mut63759);
            if (ROR_greater(predsq, presav, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63760, _mut63761, _mut63762, _mut63763, _mut63764)) {
                presav = predsq;
                ksav = k;
                stpsav = step;
                ibdsav = isbd;
            }
        }
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63782, _mut63783, _mut63784, _mut63785, _mut63786); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            final double tmp = AOR_plus(trustRegionCenterOffset.getEntry(i), AOR_multiply(stpsav, (AOR_minus(interpolationPoints.getEntry(ksav, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63770, _mut63771, _mut63772, _mut63773)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63774, _mut63775, _mut63776, _mut63777), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63778, _mut63779, _mut63780, _mut63781);
            newPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), FastMath.min(upperDifference.getEntry(i), tmp)));
        }
        if (ROR_less(ibdsav, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63787, _mut63788, _mut63789, _mut63790, _mut63791)) {
            newPoint.setEntry(AOR_minus(-ibdsav, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63792, _mut63793, _mut63794, _mut63795), lowerDifference.getEntry(AOR_minus(-ibdsav, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63796, _mut63797, _mut63798, _mut63799)));
        }
        if (ROR_greater(ibdsav, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63800, _mut63801, _mut63802, _mut63803, _mut63804)) {
            newPoint.setEntry(AOR_minus(ibdsav, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63805, _mut63806, _mut63807, _mut63808), upperDifference.getEntry(AOR_minus(ibdsav, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63809, _mut63810, _mut63811, _mut63812)));
        }
        final double bigstp = AOR_plus(adelt, adelt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63813, _mut63814, _mut63815, _mut63816);
        int iflag = 0;
        double cauchy = Double.NaN;
        double csave = ZERO;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
            double wfixsq = ZERO;
            double ggfree = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63840, _mut63841, _mut63842, _mut63843, _mut63844); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                final double glagValue = glag.getEntry(i);
                work1.setEntry(i, ZERO);
                if ((_mut63835 ? (ROR_greater(FastMath.min(AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63817, _mut63818, _mut63819, _mut63820), glagValue), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63821, _mut63822, _mut63823, _mut63824, _mut63825) && ROR_less(FastMath.max(AOR_minus(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63826, _mut63827, _mut63828, _mut63829), glagValue), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63830, _mut63831, _mut63832, _mut63833, _mut63834)) : (ROR_greater(FastMath.min(AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63817, _mut63818, _mut63819, _mut63820), glagValue), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63821, _mut63822, _mut63823, _mut63824, _mut63825) || ROR_less(FastMath.max(AOR_minus(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63826, _mut63827, _mut63828, _mut63829), glagValue), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63830, _mut63831, _mut63832, _mut63833, _mut63834)))) {
                    work1.setEntry(i, bigstp);
                    // Computing 2nd power
                    ggfree += AOR_multiply(glagValue, glagValue, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63836, _mut63837, _mut63838, _mut63839);
                }
            }
            if (ROR_equals(ggfree, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63845, _mut63846, _mut63847, _mut63848, _mut63849)) {
                return new double[] { alpha, ZERO };
            }
            // Investigate whether more components of W can be fixed.
            final double tmp1 = AOR_minus(AOR_multiply(adelt, adelt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63850, _mut63851, _mut63852, _mut63853), wfixsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63854, _mut63855, _mut63856, _mut63857);
            if (ROR_greater(tmp1, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63858, _mut63859, _mut63860, _mut63861, _mut63862)) {
                step = FastMath.sqrt(AOR_divide(tmp1, ggfree, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63863, _mut63864, _mut63865, _mut63866));
                ggfree = ZERO;
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63910, _mut63911, _mut63912, _mut63913, _mut63914); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                    if (ROR_equals(work1.getEntry(i), bigstp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63867, _mut63868, _mut63869, _mut63870, _mut63871)) {
                        final double tmp2 = AOR_minus(trustRegionCenterOffset.getEntry(i), AOR_multiply(step, glag.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63872, _mut63873, _mut63874, _mut63875), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63876, _mut63877, _mut63878, _mut63879);
                        if (ROR_less_equals(tmp2, lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63880, _mut63881, _mut63882, _mut63883, _mut63884)) {
                            work1.setEntry(i, AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63902, _mut63903, _mut63904, _mut63905));
                            // Computing 2nd power
                            final double d1 = work1.getEntry(i);
                            wfixsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63906, _mut63907, _mut63908, _mut63909);
                        } else if (ROR_greater_equals(tmp2, upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63885, _mut63886, _mut63887, _mut63888, _mut63889)) {
                            work1.setEntry(i, AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63894, _mut63895, _mut63896, _mut63897));
                            // Computing 2nd power
                            final double d1 = work1.getEntry(i);
                            wfixsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63898, _mut63899, _mut63900, _mut63901);
                        } else {
                            // Computing 2nd power
                            final double d1 = glag.getEntry(i);
                            ggfree += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63890, _mut63891, _mut63892, _mut63893);
                        }
                    }
                }
            }
            double gw = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63942, _mut63943, _mut63944, _mut63945, _mut63946); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                final double glagValue = glag.getEntry(i);
                if (ROR_equals(work1.getEntry(i), bigstp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63915, _mut63916, _mut63917, _mut63918, _mut63919)) {
                    work1.setEntry(i, AOR_multiply(-step, glagValue, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63930, _mut63931, _mut63932, _mut63933));
                    final double min = FastMath.min(upperDifference.getEntry(i), AOR_plus(trustRegionCenterOffset.getEntry(i), work1.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63934, _mut63935, _mut63936, _mut63937));
                    alternativeNewPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), min));
                } else if (ROR_equals(work1.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63920, _mut63921, _mut63922, _mut63923, _mut63924)) {
                    alternativeNewPoint.setEntry(i, trustRegionCenterOffset.getEntry(i));
                } else if (ROR_greater(glagValue, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63925, _mut63926, _mut63927, _mut63928, _mut63929)) {
                    alternativeNewPoint.setEntry(i, lowerDifference.getEntry(i));
                } else {
                    alternativeNewPoint.setEntry(i, upperDifference.getEntry(i));
                }
                gw += AOR_multiply(glagValue, work1.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63938, _mut63939, _mut63940, _mut63941);
            }
            double curv = ZERO;
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63964, _mut63965, _mut63966, _mut63967, _mut63968); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                double tmp = ZERO;
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63951, _mut63952, _mut63953, _mut63954, _mut63955); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                    tmp += AOR_multiply(interpolationPoints.getEntry(k, j), work1.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63947, _mut63948, _mut63949, _mut63950);
                }
                curv += AOR_multiply(AOR_multiply(hcol.getEntry(k), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63956, _mut63957, _mut63958, _mut63959), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63960, _mut63961, _mut63962, _mut63963);
            }
            if (ROR_equals(iflag, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63969, _mut63970, _mut63971, _mut63972, _mut63973)) {
                curv = -curv;
            }
            if ((_mut63992 ? (ROR_greater(curv, -gw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63974, _mut63975, _mut63976, _mut63977, _mut63978) || ROR_less(curv, AOR_multiply(-gw, (AOR_plus(ONE, FastMath.sqrt(TWO), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63979, _mut63980, _mut63981, _mut63982)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63983, _mut63984, _mut63985, _mut63986), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63987, _mut63988, _mut63989, _mut63990, _mut63991)) : (ROR_greater(curv, -gw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63974, _mut63975, _mut63976, _mut63977, _mut63978) && ROR_less(curv, AOR_multiply(-gw, (AOR_plus(ONE, FastMath.sqrt(TWO), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63979, _mut63980, _mut63981, _mut63982)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63983, _mut63984, _mut63985, _mut63986), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63987, _mut63988, _mut63989, _mut63990, _mut63991)))) {
                final double scale = AOR_divide(-gw, curv, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64005, _mut64006, _mut64007, _mut64008);
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64017, _mut64018, _mut64019, _mut64020, _mut64021); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                    final double tmp = AOR_plus(trustRegionCenterOffset.getEntry(i), AOR_multiply(scale, work1.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64009, _mut64010, _mut64011, _mut64012), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64013, _mut64014, _mut64015, _mut64016);
                    alternativeNewPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), FastMath.min(upperDifference.getEntry(i), tmp)));
                }
                // Computing 2nd power
                final double d1 = AOR_multiply(AOR_multiply(HALF, gw, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64022, _mut64023, _mut64024, _mut64025), scale, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64026, _mut64027, _mut64028, _mut64029);
                cauchy = AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64030, _mut64031, _mut64032, _mut64033);
            } else {
                // Computing 2nd power
                final double d1 = AOR_plus(gw, AOR_multiply(HALF, curv, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63993, _mut63994, _mut63995, _mut63996), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut63997, _mut63998, _mut63999, _mut64000);
                cauchy = AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64001, _mut64002, _mut64003, _mut64004);
            }
            if (ROR_equals(iflag, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64034, _mut64035, _mut64036, _mut64037, _mut64038)) {
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64039, _mut64040, _mut64041, _mut64042, _mut64043); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                    glag.setEntry(i, -glag.getEntry(i));
                    work2.setEntry(i, alternativeNewPoint.getEntry(i));
                }
                csave = cauchy;
                iflag = 1;
            } else {
                break;
            }
        }
        if (ROR_greater(csave, cauchy, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64044, _mut64045, _mut64046, _mut64047, _mut64048)) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266", _mut64049, _mut64050, _mut64051, _mut64052, _mut64053); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.altmov_1266");
                alternativeNewPoint.setEntry(i, work2.getEntry(i));
            }
            cauchy = csave;
        }
        return new double[] { alpha, cauchy };
    }

    /**
     *     SUBROUTINE PRELIM sets the elements of XBASE, XPT, FVAL, GOPT, HQ, PQ,
     *     BMAT and ZMAT for the first iteration, and it maintains the values of
     *     NF and KOPT. The vector X is also changed by PRELIM.
     *
     *     The arguments N, NPT, X, XL, XU, RHOBEG, IPRINT and MAXFUN are the
     *       same as the corresponding arguments in SUBROUTINE BOBYQA.
     *     The arguments XBASE, XPT, FVAL, HQ, PQ, BMAT, ZMAT, NDIM, SL and SU
     *       are the same as the corresponding arguments in BOBYQB, the elements
     *       of SL and SU being set in BOBYQA.
     *     GOPT is usually the gradient of the quadratic model at XOPT+XBASE, but
     *       it is set by PRELIM to the gradient of the quadratic model at XBASE.
     *       If XOPT is nonzero, BOBYQB will change it to its usual value later.
     *     NF is maintaned as the number of calls of CALFUN so far.
     *     KOPT will be such that the least calculated value of F so far is at
     *       the point XPT(KOPT,.)+XBASE in the space of the variables.
     *
     * @param lowerBound Lower bounds.
     * @param upperBound Upper bounds.
     */
    private void prelim(double[] lowerBound, double[] upperBound) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int ndim = bMatrix.getRowDimension();
        final double rhosq = AOR_multiply(initialTrustRegionRadius, initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64054, _mut64055, _mut64056, _mut64057);
        final double recip = AOR_divide(1d, rhosq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64058, _mut64059, _mut64060, _mut64061);
        final int np = AOR_plus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64062, _mut64063, _mut64064, _mut64065);
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64076, _mut64077, _mut64078, _mut64079, _mut64080); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
            originShift.setEntry(j, currentBest.getEntry(j));
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64066, _mut64067, _mut64068, _mut64069, _mut64070); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
                interpolationPoints.setEntry(k, j, ZERO);
            }
            for (int i = 0; ROR_less(i, ndim, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64071, _mut64072, _mut64073, _mut64074, _mut64075); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
                bMatrix.setEntry(i, j, ZERO);
            }
        }
        for (int i = 0, max = n * np / 2; ROR_less(i, max, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64081, _mut64082, _mut64083, _mut64084, _mut64085); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
            modelSecondDerivativesValues.setEntry(i, ZERO);
        }
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64091, _mut64092, _mut64093, _mut64094, _mut64095); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
            modelSecondDerivativesParameters.setEntry(k, ZERO);
            for (int j = 0, max = npt - np; ROR_less(j, max, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64086, _mut64087, _mut64088, _mut64089, _mut64090); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
                zMatrix.setEntry(k, j, ZERO);
            }
        }
        int ipt = 0;
        int jpt = 0;
        double fbeg = Double.NaN;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
            final int nfm = getEvaluations();
            final int nfx = AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64096, _mut64097, _mut64098, _mut64099);
            final int nfmm = AOR_minus(nfm, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64100, _mut64101, _mut64102, _mut64103);
            final int nfxm = AOR_minus(nfx, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64104, _mut64105, _mut64106, _mut64107);
            double stepa = 0;
            double stepb = 0;
            if (ROR_less_equals(nfm, AOR_multiply(2, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64108, _mut64109, _mut64110, _mut64111), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64112, _mut64113, _mut64114, _mut64115, _mut64116)) {
                if ((_mut64168 ? (ROR_greater_equals(nfm, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64158, _mut64159, _mut64160, _mut64161, _mut64162) || ROR_less_equals(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64163, _mut64164, _mut64165, _mut64166, _mut64167)) : (ROR_greater_equals(nfm, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64158, _mut64159, _mut64160, _mut64161, _mut64162) && ROR_less_equals(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64163, _mut64164, _mut64165, _mut64166, _mut64167)))) {
                    stepa = initialTrustRegionRadius;
                    if (ROR_equals(upperDifference.getEntry(nfmm), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64192, _mut64193, _mut64194, _mut64195, _mut64196)) {
                        stepa = -stepa;
                    }
                    interpolationPoints.setEntry(nfm, nfmm, stepa);
                } else if (ROR_greater(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64169, _mut64170, _mut64171, _mut64172, _mut64173)) {
                    stepa = interpolationPoints.getEntry(nfx, nfxm);
                    stepb = -initialTrustRegionRadius;
                    if (ROR_equals(lowerDifference.getEntry(nfxm), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64174, _mut64175, _mut64176, _mut64177, _mut64178)) {
                        stepb = FastMath.min(AOR_multiply(TWO, initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64179, _mut64180, _mut64181, _mut64182), upperDifference.getEntry(nfxm));
                    }
                    if (ROR_equals(upperDifference.getEntry(nfxm), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64183, _mut64184, _mut64185, _mut64186, _mut64187)) {
                        stepb = FastMath.max(AOR_multiply(-TWO, initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64188, _mut64189, _mut64190, _mut64191), lowerDifference.getEntry(nfxm));
                    }
                    interpolationPoints.setEntry(nfm, nfxm, stepb);
                }
            } else {
                final int tmp1 = AOR_divide((AOR_minus(nfm, np, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64117, _mut64118, _mut64119, _mut64120)), n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64121, _mut64122, _mut64123, _mut64124);
                jpt = AOR_minus(AOR_minus(nfm, AOR_multiply(tmp1, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64125, _mut64126, _mut64127, _mut64128), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64129, _mut64130, _mut64131, _mut64132), n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64133, _mut64134, _mut64135, _mut64136);
                ipt = AOR_plus(jpt, tmp1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64137, _mut64138, _mut64139, _mut64140);
                if (ROR_greater(ipt, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64141, _mut64142, _mut64143, _mut64144, _mut64145)) {
                    final int tmp2 = jpt;
                    jpt = AOR_minus(ipt, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64146, _mut64147, _mut64148, _mut64149);
                    ipt = tmp2;
                }
                final int iptMinus1 = AOR_minus(ipt, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64150, _mut64151, _mut64152, _mut64153);
                final int jptMinus1 = AOR_minus(jpt, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64154, _mut64155, _mut64156, _mut64157);
                interpolationPoints.setEntry(nfm, iptMinus1, interpolationPoints.getEntry(ipt, iptMinus1));
                interpolationPoints.setEntry(nfm, jptMinus1, interpolationPoints.getEntry(jpt, jptMinus1));
            }
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64211, _mut64212, _mut64213, _mut64214, _mut64215); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590");
                currentBest.setEntry(j, FastMath.min(FastMath.max(lowerBound[j], AOR_plus(originShift.getEntry(j), interpolationPoints.getEntry(nfm, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64197, _mut64198, _mut64199, _mut64200)), upperBound[j]));
                if (ROR_equals(interpolationPoints.getEntry(nfm, j), lowerDifference.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64201, _mut64202, _mut64203, _mut64204, _mut64205)) {
                    currentBest.setEntry(j, lowerBound[j]);
                }
                if (ROR_equals(interpolationPoints.getEntry(nfm, j), upperDifference.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64206, _mut64207, _mut64208, _mut64209, _mut64210)) {
                    currentBest.setEntry(j, upperBound[j]);
                }
            }
            final double objectiveValue = computeObjectiveValue(currentBest.toArray());
            final double f = isMinimize ? objectiveValue : -objectiveValue;
            // nfm + 1
            final int numEval = getEvaluations();
            fAtInterpolationPoints.setEntry(nfm, f);
            if (ROR_equals(numEval, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64216, _mut64217, _mut64218, _mut64219, _mut64220)) {
                fbeg = f;
                trustRegionCenterInterpolationPointIndex = 0;
            } else if (ROR_less(f, fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64221, _mut64222, _mut64223, _mut64224, _mut64225)) {
                trustRegionCenterInterpolationPointIndex = nfm;
            }
            if (ROR_less_equals(numEval, AOR_plus(AOR_multiply(2, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64226, _mut64227, _mut64228, _mut64229), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64230, _mut64231, _mut64232, _mut64233), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64234, _mut64235, _mut64236, _mut64237, _mut64238)) {
                if ((_mut64301 ? (ROR_greater_equals(numEval, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64287, _mut64288, _mut64289, _mut64290, _mut64291) || ROR_less_equals(numEval, AOR_plus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64292, _mut64293, _mut64294, _mut64295), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64296, _mut64297, _mut64298, _mut64299, _mut64300)) : (ROR_greater_equals(numEval, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64287, _mut64288, _mut64289, _mut64290, _mut64291) && ROR_less_equals(numEval, AOR_plus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64292, _mut64293, _mut64294, _mut64295), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64296, _mut64297, _mut64298, _mut64299, _mut64300)))) {
                    gradientAtTrustRegionCenter.setEntry(nfmm, AOR_divide((AOR_minus(f, fbeg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64455, _mut64456, _mut64457, _mut64458)), stepa, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64459, _mut64460, _mut64461, _mut64462));
                    if (ROR_less(npt, AOR_plus(numEval, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64463, _mut64464, _mut64465, _mut64466), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64467, _mut64468, _mut64469, _mut64470, _mut64471)) {
                        final double oneOverStepA = AOR_divide(ONE, stepa, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64472, _mut64473, _mut64474, _mut64475);
                        bMatrix.setEntry(0, nfmm, -oneOverStepA);
                        bMatrix.setEntry(nfm, nfmm, oneOverStepA);
                        bMatrix.setEntry(AOR_plus(npt, nfmm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64476, _mut64477, _mut64478, _mut64479), nfmm, AOR_multiply(-HALF, rhosq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64480, _mut64481, _mut64482, _mut64483));
                    }
                } else if (ROR_greater_equals(numEval, AOR_plus(n, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64302, _mut64303, _mut64304, _mut64305), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64306, _mut64307, _mut64308, _mut64309, _mut64310)) {
                    final int ih = AOR_minus(AOR_divide(AOR_multiply(nfx, (AOR_plus(nfx, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64311, _mut64312, _mut64313, _mut64314)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64315, _mut64316, _mut64317, _mut64318), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64319, _mut64320, _mut64321, _mut64322), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64323, _mut64324, _mut64325, _mut64326);
                    final double tmp = AOR_divide((AOR_minus(f, fbeg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64327, _mut64328, _mut64329, _mut64330)), stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64331, _mut64332, _mut64333, _mut64334);
                    final double diff = AOR_minus(stepb, stepa, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64335, _mut64336, _mut64337, _mut64338);
                    modelSecondDerivativesValues.setEntry(ih, AOR_divide(AOR_multiply(TWO, (AOR_minus(tmp, gradientAtTrustRegionCenter.getEntry(nfxm), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64339, _mut64340, _mut64341, _mut64342)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64343, _mut64344, _mut64345, _mut64346), diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64347, _mut64348, _mut64349, _mut64350));
                    gradientAtTrustRegionCenter.setEntry(nfxm, AOR_divide((AOR_minus(AOR_multiply(gradientAtTrustRegionCenter.getEntry(nfxm), stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64351, _mut64352, _mut64353, _mut64354), AOR_multiply(tmp, stepa, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64355, _mut64356, _mut64357, _mut64358), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64359, _mut64360, _mut64361, _mut64362)), diff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64363, _mut64364, _mut64365, _mut64366));
                    if ((_mut64385 ? (ROR_less(AOR_multiply(stepa, stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64367, _mut64368, _mut64369, _mut64370), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64371, _mut64372, _mut64373, _mut64374, _mut64375) || ROR_less(f, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64376, _mut64377, _mut64378, _mut64379)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64380, _mut64381, _mut64382, _mut64383, _mut64384)) : (ROR_less(AOR_multiply(stepa, stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64367, _mut64368, _mut64369, _mut64370), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64371, _mut64372, _mut64373, _mut64374, _mut64375) && ROR_less(f, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64376, _mut64377, _mut64378, _mut64379)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64380, _mut64381, _mut64382, _mut64383, _mut64384)))) {
                        fAtInterpolationPoints.setEntry(nfm, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64386, _mut64387, _mut64388, _mut64389)));
                        fAtInterpolationPoints.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64390, _mut64391, _mut64392, _mut64393), f);
                        if (ROR_equals(trustRegionCenterInterpolationPointIndex, nfm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64394, _mut64395, _mut64396, _mut64397, _mut64398)) {
                            trustRegionCenterInterpolationPointIndex = AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64399, _mut64400, _mut64401, _mut64402);
                        }
                        interpolationPoints.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64403, _mut64404, _mut64405, _mut64406), nfxm, stepb);
                        interpolationPoints.setEntry(nfm, nfxm, stepa);
                    }
                    bMatrix.setEntry(0, nfxm, AOR_divide(-(AOR_plus(stepa, stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64407, _mut64408, _mut64409, _mut64410)), (AOR_multiply(stepa, stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64411, _mut64412, _mut64413, _mut64414)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64415, _mut64416, _mut64417, _mut64418));
                    bMatrix.setEntry(nfm, nfxm, AOR_divide(-HALF, interpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64419, _mut64420, _mut64421, _mut64422), nfxm), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64423, _mut64424, _mut64425, _mut64426));
                    bMatrix.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64427, _mut64428, _mut64429, _mut64430), nfxm, AOR_minus(-bMatrix.getEntry(0, nfxm), bMatrix.getEntry(nfm, nfxm), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64431, _mut64432, _mut64433, _mut64434));
                    zMatrix.setEntry(0, nfxm, AOR_divide(FastMath.sqrt(TWO), (AOR_multiply(stepa, stepb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64435, _mut64436, _mut64437, _mut64438)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64439, _mut64440, _mut64441, _mut64442));
                    zMatrix.setEntry(nfm, nfxm, AOR_divide(FastMath.sqrt(HALF), rhosq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64443, _mut64444, _mut64445, _mut64446));
                    // zMatrix.setEntry(nfm, nfxm, FastMath.sqrt(HALF) * recip); // XXX "testAckley" and "testDiffPow" fail.
                    zMatrix.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64447, _mut64448, _mut64449, _mut64450), nfxm, AOR_minus(-zMatrix.getEntry(0, nfxm), zMatrix.getEntry(nfm, nfxm), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64451, _mut64452, _mut64453, _mut64454));
                }
            } else {
                zMatrix.setEntry(0, nfxm, recip);
                zMatrix.setEntry(nfm, nfxm, recip);
                zMatrix.setEntry(ipt, nfxm, -recip);
                zMatrix.setEntry(jpt, nfxm, -recip);
                final int ih = AOR_minus(AOR_plus(AOR_divide(AOR_multiply(ipt, (AOR_minus(ipt, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64239, _mut64240, _mut64241, _mut64242)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64243, _mut64244, _mut64245, _mut64246), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64247, _mut64248, _mut64249, _mut64250), jpt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64251, _mut64252, _mut64253, _mut64254), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64255, _mut64256, _mut64257, _mut64258);
                final double tmp = AOR_multiply(interpolationPoints.getEntry(nfm, AOR_minus(ipt, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64259, _mut64260, _mut64261, _mut64262)), interpolationPoints.getEntry(nfm, AOR_minus(jpt, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64263, _mut64264, _mut64265, _mut64266)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64267, _mut64268, _mut64269, _mut64270);
                modelSecondDerivativesValues.setEntry(ih, AOR_divide((AOR_plus(AOR_minus(AOR_minus(fbeg, fAtInterpolationPoints.getEntry(ipt), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64271, _mut64272, _mut64273, _mut64274), fAtInterpolationPoints.getEntry(jpt), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64275, _mut64276, _mut64277, _mut64278), f, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64279, _mut64280, _mut64281, _mut64282)), tmp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64283, _mut64284, _mut64285, _mut64286));
            }
        } while (ROR_less(getEvaluations(), npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.prelim_1590", _mut64484, _mut64485, _mut64486, _mut64487, _mut64488));
    }

    /**
     *     A version of the truncated conjugate gradient is applied. If a line
     *     search is restricted by a constraint, then the procedure is restarted,
     *     the values of the variables that are at their bounds being fixed. If
     *     the trust region boundary is reached, then further changes may be made
     *     to D, each one being in the two dimensional space that is spanned
     *     by the current D and the gradient of Q at XOPT+D, staying on the trust
     *     region boundary. Termination occurs when the reduction in Q seems to
     *     be close to the greatest reduction that can be achieved.
     *     The arguments N, NPT, XPT, XOPT, GOPT, HQ, PQ, SL and SU have the same
     *       meanings as the corresponding arguments of BOBYQB.
     *     DELTA is the trust region radius for the present calculation, which
     *       seeks a small value of the quadratic model within distance DELTA of
     *       XOPT subject to the bounds on the variables.
     *     XNEW will be set to a new vector of variables that is approximately
     *       the one that minimizes the quadratic model within the trust region
     *       subject to the SL and SU constraints on the variables. It satisfies
     *       as equations the bounds that become active during the calculation.
     *     D is the calculated trial step from XOPT, generated iteratively from an
     *       initial value of zero. Thus XNEW is XOPT+D after the final iteration.
     *     GNEW holds the gradient of the quadratic model at XOPT+D. It is updated
     *       when D is updated.
     *     xbdi.get( is a working space vector. For I=1,2,...,N, the element xbdi.get((I) is
     *       set to -1.0, 0.0, or 1.0, the value being nonzero if and only if the
     *       I-th variable has become fixed at a bound, the bound being SL(I) or
     *       SU(I) in the case xbdi.get((I)=-1.0 or xbdi.get((I)=1.0, respectively. This
     *       information is accumulated during the construction of XNEW.
     *     The arrays S, HS and HRED are also used for working space. They hold the
     *       current search direction, and the changes in the gradient of Q along S
     *       and the reduced D, respectively, where the reduced D is the same as D,
     *       except that the components of the fixed variables are zero.
     *     DSQ will be set to the square of the length of XNEW-XOPT.
     *     CRVMIN is set to zero if D reaches the trust region boundary. Otherwise
     *       it is set to the least curvature of H that occurs in the conjugate
     *       gradient searches that are not restricted by any constraints. The
     *       value CRVMIN=-1.0D0 is set, however, if all of these searches are
     *       constrained.
     * @param delta
     * @param gnew
     * @param xbdi
     * @param s
     * @param hs
     * @param hred
     */
    private double[] trsbox(double delta, ArrayRealVector gnew, ArrayRealVector xbdi, ArrayRealVector s, ArrayRealVector hs, ArrayRealVector hred) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        double dsq = Double.NaN;
        double crvmin = Double.NaN;
        // Local variables
        double ds;
        int iu;
        double dhd, dhs, cth, shs, sth, ssq, beta = 0, sdec, blen;
        int iact = -1;
        int nact = 0;
        double angt = 0, qred;
        int isav;
        double temp = 0, xsav = 0, xsum = 0, angbd = 0, dredg = 0, sredg = 0;
        int iterc;
        double resid = 0, delsq = 0, ggsav = 0, tempa = 0, tempb = 0, redmax = 0, dredsq = 0, redsav = 0, gredsq = 0, rednew = 0;
        int itcsav = 0;
        double rdprev = 0, rdnext = 0, stplen = 0, stepsq = 0;
        int itermax = 0;
        iterc = 0;
        nact = 0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64515, _mut64516, _mut64517, _mut64518, _mut64519); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
            xbdi.setEntry(i, ZERO);
            if (ROR_less_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64489, _mut64490, _mut64491, _mut64492, _mut64493)) {
                if (ROR_greater_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64505, _mut64506, _mut64507, _mut64508, _mut64509)) {
                    xbdi.setEntry(i, MINUS_ONE);
                }
            } else if ((_mut64504 ? (ROR_greater_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64494, _mut64495, _mut64496, _mut64497, _mut64498) || ROR_less_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64499, _mut64500, _mut64501, _mut64502, _mut64503)) : (ROR_greater_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64494, _mut64495, _mut64496, _mut64497, _mut64498) && ROR_less_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64499, _mut64500, _mut64501, _mut64502, _mut64503)))) {
                xbdi.setEntry(i, ONE);
            }
            if (ROR_not_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64510, _mut64511, _mut64512, _mut64513, _mut64514)) {
                ++nact;
            }
            trialStepPoint.setEntry(i, ZERO);
            gnew.setEntry(i, gradientAtTrustRegionCenter.getEntry(i));
        }
        delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64520, _mut64521, _mut64522, _mut64523);
        qred = ZERO;
        crvmin = MINUS_ONE;
        int state = 20;
        for (; ; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
            switch(state) {
                case 20:
                    {
                        // XXX
                        printState(20);
                        beta = ZERO;
                    }
                case 30:
                    {
                        // XXX
                        printState(30);
                        stepsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64546, _mut64547, _mut64548, _mut64549, _mut64550); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_not_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64524, _mut64525, _mut64526, _mut64527, _mut64528)) {
                                s.setEntry(i, ZERO);
                            } else if (ROR_equals(beta, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64529, _mut64530, _mut64531, _mut64532, _mut64533)) {
                                s.setEntry(i, -gnew.getEntry(i));
                            } else {
                                s.setEntry(i, AOR_minus(AOR_multiply(beta, s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64534, _mut64535, _mut64536, _mut64537), gnew.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64538, _mut64539, _mut64540, _mut64541));
                            }
                            // Computing 2nd power
                            final double d1 = s.getEntry(i);
                            stepsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64542, _mut64543, _mut64544, _mut64545);
                        }
                        if (ROR_equals(stepsq, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64551, _mut64552, _mut64553, _mut64554, _mut64555)) {
                            state = 190;
                            break;
                        }
                        if (ROR_equals(beta, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64556, _mut64557, _mut64558, _mut64559, _mut64560)) {
                            gredsq = stepsq;
                            itermax = AOR_minus(AOR_plus(iterc, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64561, _mut64562, _mut64563, _mut64564), nact, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64565, _mut64566, _mut64567, _mut64568);
                        }
                        if (ROR_less_equals(AOR_multiply(gredsq, delsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64569, _mut64570, _mut64571, _mut64572), AOR_multiply(AOR_multiply(qred, 1e-4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64573, _mut64574, _mut64575, _mut64576), qred, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64577, _mut64578, _mut64579, _mut64580), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64581, _mut64582, _mut64583, _mut64584, _mut64585)) {
                            state = 190;
                            break;
                        }
                        state = 210;
                        break;
                    }
                case 50:
                    {
                        // XXX
                        printState(50);
                        resid = delsq;
                        ds = ZERO;
                        shs = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64603, _mut64604, _mut64605, _mut64606, _mut64607); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64586, _mut64587, _mut64588, _mut64589, _mut64590)) {
                                // Computing 2nd power
                                final double d1 = trialStepPoint.getEntry(i);
                                resid -= AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64591, _mut64592, _mut64593, _mut64594);
                                ds += AOR_multiply(s.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64595, _mut64596, _mut64597, _mut64598);
                                shs += AOR_multiply(s.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64599, _mut64600, _mut64601, _mut64602);
                            }
                        }
                        if (ROR_less_equals(resid, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64608, _mut64609, _mut64610, _mut64611, _mut64612)) {
                            state = 90;
                            break;
                        }
                        temp = FastMath.sqrt(AOR_plus(AOR_multiply(stepsq, resid, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64613, _mut64614, _mut64615, _mut64616), AOR_multiply(ds, ds, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64617, _mut64618, _mut64619, _mut64620), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64621, _mut64622, _mut64623, _mut64624));
                        if (ROR_less(ds, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64625, _mut64626, _mut64627, _mut64628, _mut64629)) {
                            blen = AOR_divide((AOR_minus(temp, ds, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64638, _mut64639, _mut64640, _mut64641)), stepsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64642, _mut64643, _mut64644, _mut64645);
                        } else {
                            blen = AOR_divide(resid, (AOR_plus(temp, ds, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64630, _mut64631, _mut64632, _mut64633)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64634, _mut64635, _mut64636, _mut64637);
                        }
                        stplen = blen;
                        if (ROR_greater(shs, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64646, _mut64647, _mut64648, _mut64649, _mut64650)) {
                            // Computing MIN
                            stplen = FastMath.min(blen, AOR_divide(gredsq, shs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64651, _mut64652, _mut64653, _mut64654));
                        }
                        iact = -1;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64690, _mut64691, _mut64692, _mut64693, _mut64694); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_not_equals(s.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64655, _mut64656, _mut64657, _mut64658, _mut64659)) {
                                xsum = AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64660, _mut64661, _mut64662, _mut64663);
                                if (ROR_greater(s.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64664, _mut64665, _mut64666, _mut64667, _mut64668)) {
                                    temp = AOR_divide((AOR_minus(upperDifference.getEntry(i), xsum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64677, _mut64678, _mut64679, _mut64680)), s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64681, _mut64682, _mut64683, _mut64684);
                                } else {
                                    temp = AOR_divide((AOR_minus(lowerDifference.getEntry(i), xsum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64669, _mut64670, _mut64671, _mut64672)), s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64673, _mut64674, _mut64675, _mut64676);
                                }
                                if (ROR_less(temp, stplen, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64685, _mut64686, _mut64687, _mut64688, _mut64689)) {
                                    stplen = temp;
                                    iact = i;
                                }
                            }
                        }
                        sdec = ZERO;
                        if (ROR_greater(stplen, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64695, _mut64696, _mut64697, _mut64698, _mut64699)) {
                            ++iterc;
                            temp = AOR_divide(shs, stepsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64700, _mut64701, _mut64702, _mut64703);
                            if ((_mut64714 ? (ROR_equals(iact, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64704, _mut64705, _mut64706, _mut64707, _mut64708) || ROR_greater(temp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64709, _mut64710, _mut64711, _mut64712, _mut64713)) : (ROR_equals(iact, -1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64704, _mut64705, _mut64706, _mut64707, _mut64708) && ROR_greater(temp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64709, _mut64710, _mut64711, _mut64712, _mut64713)))) {
                                crvmin = FastMath.min(crvmin, temp);
                                if (ROR_equals(crvmin, MINUS_ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64715, _mut64716, _mut64717, _mut64718, _mut64719)) {
                                    crvmin = temp;
                                }
                            }
                            ggsav = gredsq;
                            gredsq = ZERO;
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64745, _mut64746, _mut64747, _mut64748, _mut64749); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                                gnew.setEntry(i, AOR_plus(gnew.getEntry(i), AOR_multiply(stplen, hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64720, _mut64721, _mut64722, _mut64723), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64724, _mut64725, _mut64726, _mut64727));
                                if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64728, _mut64729, _mut64730, _mut64731, _mut64732)) {
                                    // Computing 2nd power
                                    final double d1 = gnew.getEntry(i);
                                    gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64733, _mut64734, _mut64735, _mut64736);
                                }
                                trialStepPoint.setEntry(i, AOR_plus(trialStepPoint.getEntry(i), AOR_multiply(stplen, s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64737, _mut64738, _mut64739, _mut64740), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64741, _mut64742, _mut64743, _mut64744));
                            }
                            // Computing MAX
                            final double d1 = AOR_multiply(stplen, (AOR_minus(ggsav, AOR_multiply(AOR_multiply(HALF, stplen, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64750, _mut64751, _mut64752, _mut64753), shs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64754, _mut64755, _mut64756, _mut64757), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64758, _mut64759, _mut64760, _mut64761)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64762, _mut64763, _mut64764, _mut64765);
                            sdec = FastMath.max(d1, ZERO);
                            qred += sdec;
                        }
                        if (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64766, _mut64767, _mut64768, _mut64769, _mut64770)) {
                            ++nact;
                            xbdi.setEntry(iact, ONE);
                            if (ROR_less(s.getEntry(iact), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64771, _mut64772, _mut64773, _mut64774, _mut64775)) {
                                xbdi.setEntry(iact, MINUS_ONE);
                            }
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(iact);
                            delsq -= AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64776, _mut64777, _mut64778, _mut64779);
                            if (ROR_less_equals(delsq, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64780, _mut64781, _mut64782, _mut64783, _mut64784)) {
                                state = 190;
                                break;
                            }
                            state = 20;
                            break;
                        }
                        if (ROR_less(stplen, blen, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64785, _mut64786, _mut64787, _mut64788, _mut64789)) {
                            if (ROR_equals(iterc, itermax, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64790, _mut64791, _mut64792, _mut64793, _mut64794)) {
                                state = 190;
                                break;
                            }
                            if (ROR_less_equals(sdec, AOR_multiply(qred, .01, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64795, _mut64796, _mut64797, _mut64798), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64799, _mut64800, _mut64801, _mut64802, _mut64803)) {
                                state = 190;
                                break;
                            }
                            beta = AOR_divide(gredsq, ggsav, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64804, _mut64805, _mut64806, _mut64807);
                            state = 30;
                            break;
                        }
                    }
                case 90:
                    {
                        // XXX
                        printState(90);
                        crvmin = ZERO;
                    }
                case 100:
                    {
                        // XXX
                        printState(100);
                        if (ROR_greater_equals(nact, AOR_minus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64808, _mut64809, _mut64810, _mut64811), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64812, _mut64813, _mut64814, _mut64815, _mut64816)) {
                            state = 190;
                            break;
                        }
                        dredsq = ZERO;
                        dredg = ZERO;
                        gredsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64834, _mut64835, _mut64836, _mut64837, _mut64838); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64817, _mut64818, _mut64819, _mut64820, _mut64821)) {
                                // Computing 2nd power
                                double d1 = trialStepPoint.getEntry(i);
                                dredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64822, _mut64823, _mut64824, _mut64825);
                                dredg += AOR_multiply(trialStepPoint.getEntry(i), gnew.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64826, _mut64827, _mut64828, _mut64829);
                                // Computing 2nd power
                                d1 = gnew.getEntry(i);
                                gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64830, _mut64831, _mut64832, _mut64833);
                                s.setEntry(i, trialStepPoint.getEntry(i));
                            } else {
                                s.setEntry(i, ZERO);
                            }
                        }
                        itcsav = iterc;
                        state = 210;
                        break;
                    }
                case 120:
                    {
                        // XXX
                        printState(120);
                        ++iterc;
                        temp = AOR_minus(AOR_multiply(gredsq, dredsq, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64839, _mut64840, _mut64841, _mut64842), AOR_multiply(dredg, dredg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64843, _mut64844, _mut64845, _mut64846), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64847, _mut64848, _mut64849, _mut64850);
                        if (ROR_less_equals(temp, AOR_multiply(AOR_multiply(qred, 1e-4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64851, _mut64852, _mut64853, _mut64854), qred, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64855, _mut64856, _mut64857, _mut64858), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64859, _mut64860, _mut64861, _mut64862, _mut64863)) {
                            state = 190;
                            break;
                        }
                        temp = FastMath.sqrt(temp);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64885, _mut64886, _mut64887, _mut64888, _mut64889); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64864, _mut64865, _mut64866, _mut64867, _mut64868)) {
                                s.setEntry(i, AOR_divide((AOR_minus(AOR_multiply(dredg, trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64869, _mut64870, _mut64871, _mut64872), AOR_multiply(dredsq, gnew.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64873, _mut64874, _mut64875, _mut64876), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64877, _mut64878, _mut64879, _mut64880)), temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64881, _mut64882, _mut64883, _mut64884));
                            } else {
                                s.setEntry(i, ZERO);
                            }
                        }
                        sredg = -temp;
                        angbd = ONE;
                        iact = -1;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65001, _mut65002, _mut65003, _mut65004, _mut65005); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64890, _mut64891, _mut64892, _mut64893, _mut64894)) {
                                tempa = AOR_minus(AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64895, _mut64896, _mut64897, _mut64898), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64899, _mut64900, _mut64901, _mut64902);
                                tempb = AOR_minus(AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64903, _mut64904, _mut64905, _mut64906), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64907, _mut64908, _mut64909, _mut64910);
                                if (ROR_less_equals(tempa, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64911, _mut64912, _mut64913, _mut64914, _mut64915)) {
                                    ++nact;
                                    xbdi.setEntry(i, MINUS_ONE);
                                    state = 100;
                                    break;
                                } else if (ROR_less_equals(tempb, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64916, _mut64917, _mut64918, _mut64919, _mut64920)) {
                                    ++nact;
                                    xbdi.setEntry(i, ONE);
                                    state = 100;
                                    break;
                                }
                                // Computing 2nd power
                                double d1 = trialStepPoint.getEntry(i);
                                // Computing 2nd power
                                double d2 = s.getEntry(i);
                                ssq = AOR_plus(AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64921, _mut64922, _mut64923, _mut64924), AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64925, _mut64926, _mut64927, _mut64928), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64929, _mut64930, _mut64931, _mut64932);
                                // Computing 2nd power
                                d1 = AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64933, _mut64934, _mut64935, _mut64936);
                                temp = AOR_minus(ssq, AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64937, _mut64938, _mut64939, _mut64940), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64941, _mut64942, _mut64943, _mut64944);
                                if (ROR_greater(temp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64945, _mut64946, _mut64947, _mut64948, _mut64949)) {
                                    temp = AOR_minus(FastMath.sqrt(temp), s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64950, _mut64951, _mut64952, _mut64953);
                                    if (ROR_greater(AOR_multiply(angbd, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64954, _mut64955, _mut64956, _mut64957), tempa, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64958, _mut64959, _mut64960, _mut64961, _mut64962)) {
                                        angbd = AOR_divide(tempa, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64963, _mut64964, _mut64965, _mut64966);
                                        iact = i;
                                        xsav = MINUS_ONE;
                                    }
                                }
                                // Computing 2nd power
                                d1 = AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64967, _mut64968, _mut64969, _mut64970);
                                temp = AOR_minus(ssq, AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64971, _mut64972, _mut64973, _mut64974), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64975, _mut64976, _mut64977, _mut64978);
                                if (ROR_greater(temp, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64979, _mut64980, _mut64981, _mut64982, _mut64983)) {
                                    temp = AOR_plus(FastMath.sqrt(temp), s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64984, _mut64985, _mut64986, _mut64987);
                                    if (ROR_greater(AOR_multiply(angbd, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64988, _mut64989, _mut64990, _mut64991), tempb, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64992, _mut64993, _mut64994, _mut64995, _mut64996)) {
                                        angbd = AOR_divide(tempb, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut64997, _mut64998, _mut64999, _mut65000);
                                        iact = i;
                                        xsav = ONE;
                                    }
                                }
                            }
                        }
                        state = 210;
                        break;
                    }
                case 150:
                    {
                        // XXX
                        printState(150);
                        shs = ZERO;
                        dhs = ZERO;
                        dhd = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65023, _mut65024, _mut65025, _mut65026, _mut65027); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65006, _mut65007, _mut65008, _mut65009, _mut65010)) {
                                shs += AOR_multiply(s.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65011, _mut65012, _mut65013, _mut65014);
                                dhs += AOR_multiply(trialStepPoint.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65015, _mut65016, _mut65017, _mut65018);
                                dhd += AOR_multiply(trialStepPoint.getEntry(i), hred.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65019, _mut65020, _mut65021, _mut65022);
                            }
                        }
                        redmax = ZERO;
                        isav = -1;
                        redsav = ZERO;
                        iu = (int) (AOR_plus(AOR_multiply(angbd, 17., "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65028, _mut65029, _mut65030, _mut65031), 3.1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65032, _mut65033, _mut65034, _mut65035));
                        for (int i = 0; ROR_less(i, iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65118, _mut65119, _mut65120, _mut65121, _mut65122); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            angt = AOR_divide(AOR_multiply(angbd, i, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65036, _mut65037, _mut65038, _mut65039), iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65040, _mut65041, _mut65042, _mut65043);
                            sth = AOR_divide((AOR_plus(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65044, _mut65045, _mut65046, _mut65047)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65048, _mut65049, _mut65050, _mut65051), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65052, _mut65053, _mut65054, _mut65055)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65056, _mut65057, _mut65058, _mut65059);
                            temp = AOR_plus(shs, AOR_multiply(angt, (AOR_minus(AOR_minus(AOR_multiply(angt, dhd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65060, _mut65061, _mut65062, _mut65063), dhs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65064, _mut65065, _mut65066, _mut65067), dhs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65068, _mut65069, _mut65070, _mut65071)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65072, _mut65073, _mut65074, _mut65075), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65076, _mut65077, _mut65078, _mut65079);
                            rednew = AOR_multiply(sth, (AOR_minus(AOR_minus(AOR_multiply(angt, dredg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65080, _mut65081, _mut65082, _mut65083), sredg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65084, _mut65085, _mut65086, _mut65087), AOR_multiply(AOR_multiply(HALF, sth, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65088, _mut65089, _mut65090, _mut65091), temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65092, _mut65093, _mut65094, _mut65095), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65096, _mut65097, _mut65098, _mut65099)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65100, _mut65101, _mut65102, _mut65103);
                            if (ROR_greater(rednew, redmax, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65104, _mut65105, _mut65106, _mut65107, _mut65108)) {
                                redmax = rednew;
                                isav = i;
                                rdprev = redsav;
                            } else if (ROR_equals(i, AOR_plus(isav, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65109, _mut65110, _mut65111, _mut65112), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65113, _mut65114, _mut65115, _mut65116, _mut65117)) {
                                rdnext = rednew;
                            }
                            redsav = rednew;
                        }
                        if (ROR_less(isav, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65123, _mut65124, _mut65125, _mut65126, _mut65127)) {
                            state = 190;
                            break;
                        }
                        if (ROR_less(isav, iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65128, _mut65129, _mut65130, _mut65131, _mut65132)) {
                            temp = AOR_divide((AOR_minus(rdnext, rdprev, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65133, _mut65134, _mut65135, _mut65136)), (AOR_minus(AOR_minus(AOR_plus(redmax, redmax, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65137, _mut65138, _mut65139, _mut65140), rdprev, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65141, _mut65142, _mut65143, _mut65144), rdnext, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65145, _mut65146, _mut65147, _mut65148)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65149, _mut65150, _mut65151, _mut65152);
                            angt = AOR_divide(AOR_multiply(angbd, (AOR_plus(isav, AOR_multiply(HALF, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65153, _mut65154, _mut65155, _mut65156), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65157, _mut65158, _mut65159, _mut65160)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65161, _mut65162, _mut65163, _mut65164), iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65165, _mut65166, _mut65167, _mut65168);
                        }
                        cth = AOR_divide((AOR_minus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65169, _mut65170, _mut65171, _mut65172), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65173, _mut65174, _mut65175, _mut65176)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65177, _mut65178, _mut65179, _mut65180), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65181, _mut65182, _mut65183, _mut65184)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65185, _mut65186, _mut65187, _mut65188);
                        sth = AOR_divide((AOR_plus(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65189, _mut65190, _mut65191, _mut65192)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65193, _mut65194, _mut65195, _mut65196), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65197, _mut65198, _mut65199, _mut65200)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65201, _mut65202, _mut65203, _mut65204);
                        temp = AOR_plus(shs, AOR_multiply(angt, (AOR_minus(AOR_minus(AOR_multiply(angt, dhd, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65205, _mut65206, _mut65207, _mut65208), dhs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65209, _mut65210, _mut65211, _mut65212), dhs, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65213, _mut65214, _mut65215, _mut65216)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65217, _mut65218, _mut65219, _mut65220), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65221, _mut65222, _mut65223, _mut65224);
                        sdec = AOR_multiply(sth, (AOR_minus(AOR_minus(AOR_multiply(angt, dredg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65225, _mut65226, _mut65227, _mut65228), sredg, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65229, _mut65230, _mut65231, _mut65232), AOR_multiply(AOR_multiply(HALF, sth, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65233, _mut65234, _mut65235, _mut65236), temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65237, _mut65238, _mut65239, _mut65240), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65241, _mut65242, _mut65243, _mut65244)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65245, _mut65246, _mut65247, _mut65248);
                        if (ROR_less_equals(sdec, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65249, _mut65250, _mut65251, _mut65252, _mut65253)) {
                            state = 190;
                            break;
                        }
                        dredg = ZERO;
                        gredsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65311, _mut65312, _mut65313, _mut65314, _mut65315); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            gnew.setEntry(i, AOR_plus(AOR_plus(gnew.getEntry(i), AOR_multiply((AOR_minus(cth, ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65254, _mut65255, _mut65256, _mut65257)), hred.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65258, _mut65259, _mut65260, _mut65261), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65262, _mut65263, _mut65264, _mut65265), AOR_multiply(sth, hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65266, _mut65267, _mut65268, _mut65269), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65270, _mut65271, _mut65272, _mut65273));
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65274, _mut65275, _mut65276, _mut65277, _mut65278)) {
                                trialStepPoint.setEntry(i, AOR_plus(AOR_multiply(cth, trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65279, _mut65280, _mut65281, _mut65282), AOR_multiply(sth, s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65283, _mut65284, _mut65285, _mut65286), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65287, _mut65288, _mut65289, _mut65290));
                                dredg += AOR_multiply(trialStepPoint.getEntry(i), gnew.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65291, _mut65292, _mut65293, _mut65294);
                                // Computing 2nd power
                                final double d1 = gnew.getEntry(i);
                                gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65295, _mut65296, _mut65297, _mut65298);
                            }
                            hred.setEntry(i, AOR_plus(AOR_multiply(cth, hred.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65299, _mut65300, _mut65301, _mut65302), AOR_multiply(sth, hs.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65303, _mut65304, _mut65305, _mut65306), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65307, _mut65308, _mut65309, _mut65310));
                        }
                        qred += sdec;
                        if ((_mut65326 ? (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65316, _mut65317, _mut65318, _mut65319, _mut65320) || ROR_equals(isav, iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65321, _mut65322, _mut65323, _mut65324, _mut65325)) : (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65316, _mut65317, _mut65318, _mut65319, _mut65320) && ROR_equals(isav, iu, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65321, _mut65322, _mut65323, _mut65324, _mut65325)))) {
                            ++nact;
                            xbdi.setEntry(iact, xsav);
                            state = 100;
                            break;
                        }
                        if (ROR_greater(sdec, AOR_multiply(qred, .01, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65327, _mut65328, _mut65329, _mut65330), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65331, _mut65332, _mut65333, _mut65334, _mut65335)) {
                            state = 120;
                            break;
                        }
                    }
                case 190:
                    {
                        // XXX
                        printState(190);
                        dsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65358, _mut65359, _mut65360, _mut65361, _mut65362); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            // Computing MIN
                            final double min = FastMath.min(AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65336, _mut65337, _mut65338, _mut65339), upperDifference.getEntry(i));
                            newPoint.setEntry(i, FastMath.max(min, lowerDifference.getEntry(i)));
                            if (ROR_equals(xbdi.getEntry(i), MINUS_ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65340, _mut65341, _mut65342, _mut65343, _mut65344)) {
                                newPoint.setEntry(i, lowerDifference.getEntry(i));
                            }
                            if (ROR_equals(xbdi.getEntry(i), ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65345, _mut65346, _mut65347, _mut65348, _mut65349)) {
                                newPoint.setEntry(i, upperDifference.getEntry(i));
                            }
                            trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65350, _mut65351, _mut65352, _mut65353));
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(i);
                            dsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65354, _mut65355, _mut65356, _mut65357);
                        }
                        return new double[] { dsq, crvmin };
                    }
                case 210:
                    {
                        // XXX
                        printState(210);
                        int ih = 0;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65389, _mut65390, _mut65391, _mut65392, _mut65393); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            hs.setEntry(j, ZERO);
                            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65384, _mut65385, _mut65386, _mut65387, _mut65388); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                                if (ROR_less(i, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65363, _mut65364, _mut65365, _mut65366, _mut65367)) {
                                    hs.setEntry(j, AOR_plus(hs.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), s.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65368, _mut65369, _mut65370, _mut65371), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65372, _mut65373, _mut65374, _mut65375));
                                }
                                hs.setEntry(i, AOR_plus(hs.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), s.getEntry(j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65376, _mut65377, _mut65378, _mut65379), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65380, _mut65381, _mut65382, _mut65383));
                                ih++;
                            }
                        }
                        final RealVector tmp = interpolationPoints.operate(s).ebeMultiply(modelSecondDerivativesParameters);
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65412, _mut65413, _mut65414, _mut65415, _mut65416); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            if (ROR_not_equals(modelSecondDerivativesParameters.getEntry(k), ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65394, _mut65395, _mut65396, _mut65397, _mut65398)) {
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65407, _mut65408, _mut65409, _mut65410, _mut65411); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                                    hs.setEntry(i, AOR_plus(hs.getEntry(i), AOR_multiply(tmp.getEntry(k), interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65399, _mut65400, _mut65401, _mut65402), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65403, _mut65404, _mut65405, _mut65406));
                                }
                            }
                        }
                        if (ROR_not_equals(crvmin, ZERO, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65417, _mut65418, _mut65419, _mut65420, _mut65421)) {
                            state = 50;
                            break;
                        }
                        if (ROR_greater(iterc, itcsav, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65422, _mut65423, _mut65424, _mut65425, _mut65426)) {
                            state = 150;
                            break;
                        }
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810", _mut65427, _mut65428, _mut65429, _mut65430, _mut65431); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.trsbox_1810");
                            hred.setEntry(i, hs.getEntry(i));
                        }
                        state = 120;
                        break;
                    }
                default:
                    {
                        throw new MathIllegalStateException(LocalizedFormats.SIMPLE_MESSAGE, "trsbox");
                    }
            }
        }
    }

    /**
     *     The arrays BMAT and ZMAT are updated, as required by the new position
     *     of the interpolation point that has the index KNEW. The vector VLAG has
     *     N+NPT components, set on entry to the first NPT and last N components
     *     of the product Hw in equation (4.11) of the Powell (2006) paper on
     *     NEWUOA. Further, BETA is set on entry to the value of the parameter
     *     with that name, and DENOM is set to the denominator of the updating
     *     formula. Elements of ZMAT may be treated as zero if their moduli are
     *     at most ZTEST. The first NDIM elements of W are used for working space.
     * @param beta
     * @param denom
     * @param knew
     */
    private void update(double beta, double denom, int knew) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int nptm = AOR_minus(AOR_minus(npt, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65432, _mut65433, _mut65434, _mut65435), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65436, _mut65437, _mut65438, _mut65439);
        // XXX Should probably be split into two arrays.
        final ArrayRealVector work = new ArrayRealVector(AOR_plus(npt, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65440, _mut65441, _mut65442, _mut65443));
        double ztest = ZERO;
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65449, _mut65450, _mut65451, _mut65452, _mut65453); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
            for (int j = 0; ROR_less(j, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65444, _mut65445, _mut65446, _mut65447, _mut65448); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
                // Computing MAX
                ztest = FastMath.max(ztest, FastMath.abs(zMatrix.getEntry(k, j)));
            }
        }
        ztest *= 1e-20;
        for (int j = 1; ROR_less(j, nptm, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65508, _mut65509, _mut65510, _mut65511, _mut65512); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
            final double d1 = zMatrix.getEntry(knew, j);
            if (ROR_greater(FastMath.abs(d1), ztest, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65454, _mut65455, _mut65456, _mut65457, _mut65458)) {
                // Computing 2nd power
                final double d2 = zMatrix.getEntry(knew, 0);
                // Computing 2nd power
                final double d3 = zMatrix.getEntry(knew, j);
                final double d4 = FastMath.sqrt(AOR_plus(AOR_multiply(d2, d2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65459, _mut65460, _mut65461, _mut65462), AOR_multiply(d3, d3, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65463, _mut65464, _mut65465, _mut65466), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65467, _mut65468, _mut65469, _mut65470));
                final double d5 = AOR_divide(zMatrix.getEntry(knew, 0), d4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65471, _mut65472, _mut65473, _mut65474);
                final double d6 = AOR_divide(zMatrix.getEntry(knew, j), d4, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65475, _mut65476, _mut65477, _mut65478);
                for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65503, _mut65504, _mut65505, _mut65506, _mut65507); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
                    final double d7 = AOR_plus(AOR_multiply(d5, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65479, _mut65480, _mut65481, _mut65482), AOR_multiply(d6, zMatrix.getEntry(i, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65483, _mut65484, _mut65485, _mut65486), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65487, _mut65488, _mut65489, _mut65490);
                    zMatrix.setEntry(i, j, AOR_minus(AOR_multiply(d5, zMatrix.getEntry(i, j), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65491, _mut65492, _mut65493, _mut65494), AOR_multiply(d6, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65495, _mut65496, _mut65497, _mut65498), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65499, _mut65500, _mut65501, _mut65502));
                    zMatrix.setEntry(i, 0, d7);
                }
            }
            zMatrix.setEntry(knew, j, ZERO);
        }
        for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65517, _mut65518, _mut65519, _mut65520, _mut65521); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
            work.setEntry(i, AOR_multiply(zMatrix.getEntry(knew, 0), zMatrix.getEntry(i, 0), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65513, _mut65514, _mut65515, _mut65516));
        }
        final double alpha = work.getEntry(knew);
        final double tau = lagrangeValuesAtNewPoint.getEntry(knew);
        lagrangeValuesAtNewPoint.setEntry(knew, AOR_minus(lagrangeValuesAtNewPoint.getEntry(knew), ONE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65522, _mut65523, _mut65524, _mut65525));
        final double sqrtDenom = FastMath.sqrt(denom);
        final double d1 = AOR_divide(tau, sqrtDenom, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65526, _mut65527, _mut65528, _mut65529);
        final double d2 = AOR_divide(zMatrix.getEntry(knew, 0), sqrtDenom, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65530, _mut65531, _mut65532, _mut65533);
        for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65546, _mut65547, _mut65548, _mut65549, _mut65550); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
            zMatrix.setEntry(i, 0, AOR_minus(AOR_multiply(d1, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65534, _mut65535, _mut65536, _mut65537), AOR_multiply(d2, lagrangeValuesAtNewPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65538, _mut65539, _mut65540, _mut65541), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65542, _mut65543, _mut65544, _mut65545));
        }
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65617, _mut65618, _mut65619, _mut65620, _mut65621); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
            final int jp = AOR_plus(npt, j, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65551, _mut65552, _mut65553, _mut65554);
            work.setEntry(jp, bMatrix.getEntry(knew, j));
            final double d3 = AOR_divide((AOR_minus(AOR_multiply(alpha, lagrangeValuesAtNewPoint.getEntry(jp), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65555, _mut65556, _mut65557, _mut65558), AOR_multiply(tau, work.getEntry(jp), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65559, _mut65560, _mut65561, _mut65562), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65563, _mut65564, _mut65565, _mut65566)), denom, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65567, _mut65568, _mut65569, _mut65570);
            final double d4 = AOR_divide((AOR_minus(AOR_multiply(-beta, work.getEntry(jp), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65571, _mut65572, _mut65573, _mut65574), AOR_multiply(tau, lagrangeValuesAtNewPoint.getEntry(jp), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65575, _mut65576, _mut65577, _mut65578), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65579, _mut65580, _mut65581, _mut65582)), denom, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65583, _mut65584, _mut65585, _mut65586);
            for (int i = 0; ROR_less_equals(i, jp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65612, _mut65613, _mut65614, _mut65615, _mut65616); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300");
                bMatrix.setEntry(i, j, AOR_plus(AOR_plus(bMatrix.getEntry(i, j), AOR_multiply(d3, lagrangeValuesAtNewPoint.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65587, _mut65588, _mut65589, _mut65590), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65591, _mut65592, _mut65593, _mut65594), AOR_multiply(d4, work.getEntry(i), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65595, _mut65596, _mut65597, _mut65598), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65599, _mut65600, _mut65601, _mut65602));
                if (ROR_greater_equals(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65603, _mut65604, _mut65605, _mut65606, _mut65607)) {
                    bMatrix.setEntry(jp, (AOR_minus(i, npt, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.update_2300", _mut65608, _mut65609, _mut65610, _mut65611)), bMatrix.getEntry(i, j));
                }
            }
        }
    }

    /**
     * Performs validity checks.
     *
     * @param lowerBound Lower bounds (constraints) of the objective variables.
     * @param upperBound Upperer bounds (constraints) of the objective variables.
     */
    private void setup(double[] lowerBound, double[] upperBound) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387");
        // XXX
        printMethod();
        double[] init = getStartPoint();
        final int dimension = init.length;
        // Check problem dimension.
        if (ROR_less(dimension, MINIMUM_PROBLEM_DIMENSION, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65622, _mut65623, _mut65624, _mut65625, _mut65626)) {
            throw new NumberIsTooSmallException(dimension, MINIMUM_PROBLEM_DIMENSION, true);
        }
        // Check number of interpolation points.
        final int[] nPointsInterval = { AOR_plus(dimension, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65627, _mut65628, _mut65629, _mut65630), AOR_divide(AOR_multiply((AOR_plus(dimension, 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65631, _mut65632, _mut65633, _mut65634)), (AOR_plus(dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65635, _mut65636, _mut65637, _mut65638)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65639, _mut65640, _mut65641, _mut65642), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65643, _mut65644, _mut65645, _mut65646) };
        if ((_mut65657 ? (ROR_less(numberOfInterpolationPoints, nPointsInterval[0], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65647, _mut65648, _mut65649, _mut65650, _mut65651) && ROR_greater(numberOfInterpolationPoints, nPointsInterval[1], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65652, _mut65653, _mut65654, _mut65655, _mut65656)) : (ROR_less(numberOfInterpolationPoints, nPointsInterval[0], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65647, _mut65648, _mut65649, _mut65650, _mut65651) || ROR_greater(numberOfInterpolationPoints, nPointsInterval[1], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65652, _mut65653, _mut65654, _mut65655, _mut65656)))) {
            throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, numberOfInterpolationPoints, nPointsInterval[0], nPointsInterval[1]);
        }
        // Initialize bound differences.
        boundDifference = new double[dimension];
        double requiredMinDiff = AOR_multiply(2, initialTrustRegionRadius, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65658, _mut65659, _mut65660, _mut65661);
        double minDiff = Double.POSITIVE_INFINITY;
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65666, _mut65667, _mut65668, _mut65669, _mut65670); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387");
            boundDifference[i] = AOR_minus(upperBound[i], lowerBound[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65662, _mut65663, _mut65664, _mut65665);
            minDiff = FastMath.min(minDiff, boundDifference[i]);
        }
        if (ROR_less(minDiff, requiredMinDiff, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65671, _mut65672, _mut65673, _mut65674, _mut65675)) {
            initialTrustRegionRadius = AOR_divide(minDiff, 3.0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65676, _mut65677, _mut65678, _mut65679);
        }
        // Initialize the data structures used by the "bobyqa" method.
        bMatrix = new Array2DRowRealMatrix(AOR_plus(dimension, numberOfInterpolationPoints, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65680, _mut65681, _mut65682, _mut65683), dimension);
        zMatrix = new Array2DRowRealMatrix(numberOfInterpolationPoints, AOR_minus(AOR_minus(numberOfInterpolationPoints, dimension, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65684, _mut65685, _mut65686, _mut65687), 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65688, _mut65689, _mut65690, _mut65691));
        interpolationPoints = new Array2DRowRealMatrix(numberOfInterpolationPoints, dimension);
        originShift = new ArrayRealVector(dimension);
        fAtInterpolationPoints = new ArrayRealVector(numberOfInterpolationPoints);
        trustRegionCenterOffset = new ArrayRealVector(dimension);
        gradientAtTrustRegionCenter = new ArrayRealVector(dimension);
        lowerDifference = new ArrayRealVector(dimension);
        upperDifference = new ArrayRealVector(dimension);
        modelSecondDerivativesParameters = new ArrayRealVector(numberOfInterpolationPoints);
        newPoint = new ArrayRealVector(dimension);
        alternativeNewPoint = new ArrayRealVector(dimension);
        trialStepPoint = new ArrayRealVector(dimension);
        lagrangeValuesAtNewPoint = new ArrayRealVector(AOR_plus(dimension, numberOfInterpolationPoints, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65692, _mut65693, _mut65694, _mut65695));
        modelSecondDerivativesValues = new ArrayRealVector(AOR_divide(AOR_multiply(dimension, (AOR_plus(dimension, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65696, _mut65697, _mut65698, _mut65699)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65700, _mut65701, _mut65702, _mut65703), 2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer.setup_2387", _mut65704, _mut65705, _mut65706, _mut65707));
    }

    // XXX utility for figuring out call sequence.
    private static String caller(int n) {
        final Throwable t = new Throwable();
        final StackTraceElement[] elements = t.getStackTrace();
        final StackTraceElement e = elements[n];
        return e.getMethodName() + " (at line " + e.getLineNumber() + ")";
    }

    // XXX utility for figuring out call sequence.
    private static void printState(int s) {
    }

    // XXX utility for figuring out call sequence.
    private static void printMethod() {
    }

    /**
     * Marker for code paths that are not explored with the current unit tests.
     * If the path becomes explored, it should just be removed from the code.
     */
    private static class PathIsExploredException extends RuntimeException {

        /**
         * Serializable UID.
         */
        private static final long serialVersionUID = 745350979634801853L;

        /**
         * Message string.
         */
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";

        PathIsExploredException() {
            super(PATH_IS_EXPLORED + " " + BOBYQAOptimizer.caller(3));
        }
    }
}
