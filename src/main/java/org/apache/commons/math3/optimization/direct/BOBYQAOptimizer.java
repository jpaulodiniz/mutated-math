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
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
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
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class BOBYQAOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {

    @Conditional
    public static boolean _mut75145 = false, _mut75146 = false, _mut75147 = false, _mut75148 = false, _mut75149 = false, _mut75150 = false, _mut75151 = false, _mut75152 = false, _mut75153 = false, _mut75154 = false, _mut75155 = false, _mut75156 = false, _mut75157 = false, _mut75158 = false, _mut75159 = false, _mut75160 = false, _mut75161 = false, _mut75162 = false, _mut75163 = false, _mut75164 = false, _mut75165 = false, _mut75166 = false, _mut75167 = false, _mut75168 = false, _mut75169 = false, _mut75170 = false, _mut75171 = false, _mut75172 = false, _mut75173 = false, _mut75174 = false, _mut75175 = false, _mut75176 = false, _mut75177 = false, _mut75178 = false, _mut75179 = false, _mut75180 = false, _mut75181 = false, _mut75182 = false, _mut75183 = false, _mut75184 = false, _mut75185 = false, _mut75186 = false, _mut75187 = false, _mut75188 = false, _mut75189 = false, _mut75190 = false, _mut75191 = false, _mut75192 = false, _mut75193 = false, _mut75194 = false, _mut75195 = false, _mut75196 = false, _mut75197 = false, _mut75198 = false, _mut75199 = false, _mut75200 = false, _mut75201 = false, _mut75202 = false, _mut75203 = false, _mut75204 = false, _mut75205 = false, _mut75206 = false, _mut75207 = false, _mut75208 = false, _mut75209 = false, _mut75210 = false, _mut75211 = false, _mut75212 = false, _mut75213 = false, _mut75214 = false, _mut75215 = false, _mut75216 = false, _mut75217 = false, _mut75218 = false, _mut75219 = false, _mut75220 = false, _mut75221 = false, _mut75222 = false, _mut75223 = false, _mut75224 = false, _mut75225 = false, _mut75226 = false, _mut75227 = false, _mut75228 = false, _mut75229 = false, _mut75230 = false, _mut75231 = false, _mut75232 = false, _mut75233 = false, _mut75234 = false, _mut75235 = false, _mut75236 = false, _mut75237 = false, _mut75238 = false, _mut75239 = false, _mut75240 = false, _mut75241 = false, _mut75242 = false, _mut75243 = false, _mut75244 = false, _mut75245 = false, _mut75246 = false, _mut75247 = false, _mut75248 = false, _mut75249 = false, _mut75250 = false, _mut75251 = false, _mut75252 = false, _mut75253 = false, _mut75254 = false, _mut75255 = false, _mut75256 = false, _mut75257 = false, _mut75258 = false, _mut75259 = false, _mut75260 = false, _mut75261 = false, _mut75262 = false, _mut75263 = false, _mut75264 = false, _mut75265 = false, _mut75266 = false, _mut75267 = false, _mut75268 = false, _mut75269 = false, _mut75270 = false, _mut75271 = false, _mut75272 = false, _mut75273 = false, _mut75274 = false, _mut75275 = false, _mut75276 = false, _mut75277 = false, _mut75278 = false, _mut75279 = false, _mut75280 = false, _mut75281 = false, _mut75282 = false, _mut75283 = false, _mut75284 = false, _mut75285 = false, _mut75286 = false, _mut75287 = false, _mut75288 = false, _mut75289 = false, _mut75290 = false, _mut75291 = false, _mut75292 = false, _mut75293 = false, _mut75294 = false, _mut75295 = false, _mut75296 = false, _mut75297 = false, _mut75298 = false, _mut75299 = false, _mut75300 = false, _mut75301 = false, _mut75302 = false, _mut75303 = false, _mut75304 = false, _mut75305 = false, _mut75306 = false, _mut75307 = false, _mut75308 = false, _mut75309 = false, _mut75310 = false, _mut75311 = false, _mut75312 = false, _mut75313 = false, _mut75314 = false, _mut75315 = false, _mut75316 = false, _mut75317 = false, _mut75318 = false, _mut75319 = false, _mut75320 = false, _mut75321 = false, _mut75322 = false, _mut75323 = false, _mut75324 = false, _mut75325 = false, _mut75326 = false, _mut75327 = false, _mut75328 = false, _mut75329 = false, _mut75330 = false, _mut75331 = false, _mut75332 = false, _mut75333 = false, _mut75334 = false, _mut75335 = false, _mut75336 = false, _mut75337 = false, _mut75338 = false, _mut75339 = false, _mut75340 = false, _mut75341 = false, _mut75342 = false, _mut75343 = false, _mut75344 = false, _mut75345 = false, _mut75346 = false, _mut75347 = false, _mut75348 = false, _mut75349 = false, _mut75350 = false, _mut75351 = false, _mut75352 = false, _mut75353 = false, _mut75354 = false, _mut75355 = false, _mut75356 = false, _mut75357 = false, _mut75358 = false, _mut75359 = false, _mut75360 = false, _mut75361 = false, _mut75362 = false, _mut75363 = false, _mut75364 = false, _mut75365 = false, _mut75366 = false, _mut75367 = false, _mut75368 = false, _mut75369 = false, _mut75370 = false, _mut75371 = false, _mut75372 = false, _mut75373 = false, _mut75374 = false, _mut75375 = false, _mut75376 = false, _mut75377 = false, _mut75378 = false, _mut75379 = false, _mut75380 = false, _mut75381 = false, _mut75382 = false, _mut75383 = false, _mut75384 = false, _mut75385 = false, _mut75386 = false, _mut75387 = false, _mut75388 = false, _mut75389 = false, _mut75390 = false, _mut75391 = false, _mut75392 = false, _mut75393 = false, _mut75394 = false, _mut75395 = false, _mut75396 = false, _mut75397 = false, _mut75398 = false, _mut75399 = false, _mut75400 = false, _mut75401 = false, _mut75402 = false, _mut75403 = false, _mut75404 = false, _mut75405 = false, _mut75406 = false, _mut75407 = false, _mut75408 = false, _mut75409 = false, _mut75410 = false, _mut75411 = false, _mut75412 = false, _mut75413 = false, _mut75414 = false, _mut75415 = false, _mut75416 = false, _mut75417 = false, _mut75418 = false, _mut75419 = false, _mut75420 = false, _mut75421 = false, _mut75422 = false, _mut75423 = false, _mut75424 = false, _mut75425 = false, _mut75426 = false, _mut75427 = false, _mut75428 = false, _mut75429 = false, _mut75430 = false, _mut75431 = false, _mut75432 = false, _mut75433 = false, _mut75434 = false, _mut75435 = false, _mut75436 = false, _mut75437 = false, _mut75438 = false, _mut75439 = false, _mut75440 = false, _mut75441 = false, _mut75442 = false, _mut75443 = false, _mut75444 = false, _mut75445 = false, _mut75446 = false, _mut75447 = false, _mut75448 = false, _mut75449 = false, _mut75450 = false, _mut75451 = false, _mut75452 = false, _mut75453 = false, _mut75454 = false, _mut75455 = false, _mut75456 = false, _mut75457 = false, _mut75458 = false, _mut75459 = false, _mut75460 = false, _mut75461 = false, _mut75462 = false, _mut75463 = false, _mut75464 = false, _mut75465 = false, _mut75466 = false, _mut75467 = false, _mut75468 = false, _mut75469 = false, _mut75470 = false, _mut75471 = false, _mut75472 = false, _mut75473 = false, _mut75474 = false, _mut75475 = false, _mut75476 = false, _mut75477 = false, _mut75478 = false, _mut75479 = false, _mut75480 = false, _mut75481 = false, _mut75482 = false, _mut75483 = false, _mut75484 = false, _mut75485 = false, _mut75486 = false, _mut75487 = false, _mut75488 = false, _mut75489 = false, _mut75490 = false, _mut75491 = false, _mut75492 = false, _mut75493 = false, _mut75494 = false, _mut75495 = false, _mut75496 = false, _mut75497 = false, _mut75498 = false, _mut75499 = false, _mut75500 = false, _mut75501 = false, _mut75502 = false, _mut75503 = false, _mut75504 = false, _mut75505 = false, _mut75506 = false, _mut75507 = false, _mut75508 = false, _mut75509 = false, _mut75510 = false, _mut75511 = false, _mut75512 = false, _mut75513 = false, _mut75514 = false, _mut75515 = false, _mut75516 = false, _mut75517 = false, _mut75518 = false, _mut75519 = false, _mut75520 = false, _mut75521 = false, _mut75522 = false, _mut75523 = false, _mut75524 = false, _mut75525 = false, _mut75526 = false, _mut75527 = false, _mut75528 = false, _mut75529 = false, _mut75530 = false, _mut75531 = false, _mut75532 = false, _mut75533 = false, _mut75534 = false, _mut75535 = false, _mut75536 = false, _mut75537 = false, _mut75538 = false, _mut75539 = false, _mut75540 = false, _mut75541 = false, _mut75542 = false, _mut75543 = false, _mut75544 = false, _mut75545 = false, _mut75546 = false, _mut75547 = false, _mut75548 = false, _mut75549 = false, _mut75550 = false, _mut75551 = false, _mut75552 = false, _mut75553 = false, _mut75554 = false, _mut75555 = false, _mut75556 = false, _mut75557 = false, _mut75558 = false, _mut75559 = false, _mut75560 = false, _mut75561 = false, _mut75562 = false, _mut75563 = false, _mut75564 = false, _mut75565 = false, _mut75566 = false, _mut75567 = false, _mut75568 = false, _mut75569 = false, _mut75570 = false, _mut75571 = false, _mut75572 = false, _mut75573 = false, _mut75574 = false, _mut75575 = false, _mut75576 = false, _mut75577 = false, _mut75578 = false, _mut75579 = false, _mut75580 = false, _mut75581 = false, _mut75582 = false, _mut75583 = false, _mut75584 = false, _mut75585 = false, _mut75586 = false, _mut75587 = false, _mut75588 = false, _mut75589 = false, _mut75590 = false, _mut75591 = false, _mut75592 = false, _mut75593 = false, _mut75594 = false, _mut75595 = false, _mut75596 = false, _mut75597 = false, _mut75598 = false, _mut75599 = false, _mut75600 = false, _mut75601 = false, _mut75602 = false, _mut75603 = false, _mut75604 = false, _mut75605 = false, _mut75606 = false, _mut75607 = false, _mut75608 = false, _mut75609 = false, _mut75610 = false, _mut75611 = false, _mut75612 = false, _mut75613 = false, _mut75614 = false, _mut75615 = false, _mut75616 = false, _mut75617 = false, _mut75618 = false, _mut75619 = false, _mut75620 = false, _mut75621 = false, _mut75622 = false, _mut75623 = false, _mut75624 = false, _mut75625 = false, _mut75626 = false, _mut75627 = false, _mut75628 = false, _mut75629 = false, _mut75630 = false, _mut75631 = false, _mut75632 = false, _mut75633 = false, _mut75634 = false, _mut75635 = false, _mut75636 = false, _mut75637 = false, _mut75638 = false, _mut75639 = false, _mut75640 = false, _mut75641 = false, _mut75642 = false, _mut75643 = false, _mut75644 = false, _mut75645 = false, _mut75646 = false, _mut75647 = false, _mut75648 = false, _mut75649 = false, _mut75650 = false, _mut75651 = false, _mut75652 = false, _mut75653 = false, _mut75654 = false, _mut75655 = false, _mut75656 = false, _mut75657 = false, _mut75658 = false, _mut75659 = false, _mut75660 = false, _mut75661 = false, _mut75662 = false, _mut75663 = false, _mut75664 = false, _mut75665 = false, _mut75666 = false, _mut75667 = false, _mut75668 = false, _mut75669 = false, _mut75670 = false, _mut75671 = false, _mut75672 = false, _mut75673 = false, _mut75674 = false, _mut75675 = false, _mut75676 = false, _mut75677 = false, _mut75678 = false, _mut75679 = false, _mut75680 = false, _mut75681 = false, _mut75682 = false, _mut75683 = false, _mut75684 = false, _mut75685 = false, _mut75686 = false, _mut75687 = false, _mut75688 = false, _mut75689 = false, _mut75690 = false, _mut75691 = false, _mut75692 = false, _mut75693 = false, _mut75694 = false, _mut75695 = false, _mut75696 = false, _mut75697 = false, _mut75698 = false, _mut75699 = false, _mut75700 = false, _mut75701 = false, _mut75702 = false, _mut75703 = false, _mut75704 = false, _mut75705 = false, _mut75706 = false, _mut75707 = false, _mut75708 = false, _mut75709 = false, _mut75710 = false, _mut75711 = false, _mut75712 = false, _mut75713 = false, _mut75714 = false, _mut75715 = false, _mut75716 = false, _mut75717 = false, _mut75718 = false, _mut75719 = false, _mut75720 = false, _mut75721 = false, _mut75722 = false, _mut75723 = false, _mut75724 = false, _mut75725 = false, _mut75726 = false, _mut75727 = false, _mut75728 = false, _mut75729 = false, _mut75730 = false, _mut75731 = false, _mut75732 = false, _mut75733 = false, _mut75734 = false, _mut75735 = false, _mut75736 = false, _mut75737 = false, _mut75738 = false, _mut75739 = false, _mut75740 = false, _mut75741 = false, _mut75742 = false, _mut75743 = false, _mut75744 = false, _mut75745 = false, _mut75746 = false, _mut75747 = false, _mut75748 = false, _mut75749 = false, _mut75750 = false, _mut75751 = false, _mut75752 = false, _mut75753 = false, _mut75754 = false, _mut75755 = false, _mut75756 = false, _mut75757 = false, _mut75758 = false, _mut75759 = false, _mut75760 = false, _mut75761 = false, _mut75762 = false, _mut75763 = false, _mut75764 = false, _mut75765 = false, _mut75766 = false, _mut75767 = false, _mut75768 = false, _mut75769 = false, _mut75770 = false, _mut75771 = false, _mut75772 = false, _mut75773 = false, _mut75774 = false, _mut75775 = false, _mut75776 = false, _mut75777 = false, _mut75778 = false, _mut75779 = false, _mut75780 = false, _mut75781 = false, _mut75782 = false, _mut75783 = false, _mut75784 = false, _mut75785 = false, _mut75786 = false, _mut75787 = false, _mut75788 = false, _mut75789 = false, _mut75790 = false, _mut75791 = false, _mut75792 = false, _mut75793 = false, _mut75794 = false, _mut75795 = false, _mut75796 = false, _mut75797 = false, _mut75798 = false, _mut75799 = false, _mut75800 = false, _mut75801 = false, _mut75802 = false, _mut75803 = false, _mut75804 = false, _mut75805 = false, _mut75806 = false, _mut75807 = false, _mut75808 = false, _mut75809 = false, _mut75810 = false, _mut75811 = false, _mut75812 = false, _mut75813 = false, _mut75814 = false, _mut75815 = false, _mut75816 = false, _mut75817 = false, _mut75818 = false, _mut75819 = false, _mut75820 = false, _mut75821 = false, _mut75822 = false, _mut75823 = false, _mut75824 = false, _mut75825 = false, _mut75826 = false, _mut75827 = false, _mut75828 = false, _mut75829 = false, _mut75830 = false, _mut75831 = false, _mut75832 = false, _mut75833 = false, _mut75834 = false, _mut75835 = false, _mut75836 = false, _mut75837 = false, _mut75838 = false, _mut75839 = false, _mut75840 = false, _mut75841 = false, _mut75842 = false, _mut75843 = false, _mut75844 = false, _mut75845 = false, _mut75846 = false, _mut75847 = false, _mut75848 = false, _mut75849 = false, _mut75850 = false, _mut75851 = false, _mut75852 = false, _mut75853 = false, _mut75854 = false, _mut75855 = false, _mut75856 = false, _mut75857 = false, _mut75858 = false, _mut75859 = false, _mut75860 = false, _mut75861 = false, _mut75862 = false, _mut75863 = false, _mut75864 = false, _mut75865 = false, _mut75866 = false, _mut75867 = false, _mut75868 = false, _mut75869 = false, _mut75870 = false, _mut75871 = false, _mut75872 = false, _mut75873 = false, _mut75874 = false, _mut75875 = false, _mut75876 = false, _mut75877 = false, _mut75878 = false, _mut75879 = false, _mut75880 = false, _mut75881 = false, _mut75882 = false, _mut75883 = false, _mut75884 = false, _mut75885 = false, _mut75886 = false, _mut75887 = false, _mut75888 = false, _mut75889 = false, _mut75890 = false, _mut75891 = false, _mut75892 = false, _mut75893 = false, _mut75894 = false, _mut75895 = false, _mut75896 = false, _mut75897 = false, _mut75898 = false, _mut75899 = false, _mut75900 = false, _mut75901 = false, _mut75902 = false, _mut75903 = false, _mut75904 = false, _mut75905 = false, _mut75906 = false, _mut75907 = false, _mut75908 = false, _mut75909 = false, _mut75910 = false, _mut75911 = false, _mut75912 = false, _mut75913 = false, _mut75914 = false, _mut75915 = false, _mut75916 = false, _mut75917 = false, _mut75918 = false, _mut75919 = false, _mut75920 = false, _mut75921 = false, _mut75922 = false, _mut75923 = false, _mut75924 = false, _mut75925 = false, _mut75926 = false, _mut75927 = false, _mut75928 = false, _mut75929 = false, _mut75930 = false, _mut75931 = false, _mut75932 = false, _mut75933 = false, _mut75934 = false, _mut75935 = false, _mut75936 = false, _mut75937 = false, _mut75938 = false, _mut75939 = false, _mut75940 = false, _mut75941 = false, _mut75942 = false, _mut75943 = false, _mut75944 = false, _mut75945 = false, _mut75946 = false, _mut75947 = false, _mut75948 = false, _mut75949 = false, _mut75950 = false, _mut75951 = false, _mut75952 = false, _mut75953 = false, _mut75954 = false, _mut75955 = false, _mut75956 = false, _mut75957 = false, _mut75958 = false, _mut75959 = false, _mut75960 = false, _mut75961 = false, _mut75962 = false, _mut75963 = false, _mut75964 = false, _mut75965 = false, _mut75966 = false, _mut75967 = false, _mut75968 = false, _mut75969 = false, _mut75970 = false, _mut75971 = false, _mut75972 = false, _mut75973 = false, _mut75974 = false, _mut75975 = false, _mut75976 = false, _mut75977 = false, _mut75978 = false, _mut75979 = false, _mut75980 = false, _mut75981 = false, _mut75982 = false, _mut75983 = false, _mut75984 = false, _mut75985 = false, _mut75986 = false, _mut75987 = false, _mut75988 = false, _mut75989 = false, _mut75990 = false, _mut75991 = false, _mut75992 = false, _mut75993 = false, _mut75994 = false, _mut75995 = false, _mut75996 = false, _mut75997 = false, _mut75998 = false, _mut75999 = false, _mut76000 = false, _mut76001 = false, _mut76002 = false, _mut76003 = false, _mut76004 = false, _mut76005 = false, _mut76006 = false, _mut76007 = false, _mut76008 = false, _mut76009 = false, _mut76010 = false, _mut76011 = false, _mut76012 = false, _mut76013 = false, _mut76014 = false, _mut76015 = false, _mut76016 = false, _mut76017 = false, _mut76018 = false, _mut76019 = false, _mut76020 = false, _mut76021 = false, _mut76022 = false, _mut76023 = false, _mut76024 = false, _mut76025 = false, _mut76026 = false, _mut76027 = false, _mut76028 = false, _mut76029 = false, _mut76030 = false, _mut76031 = false, _mut76032 = false, _mut76033 = false, _mut76034 = false, _mut76035 = false, _mut76036 = false, _mut76037 = false, _mut76038 = false, _mut76039 = false, _mut76040 = false, _mut76041 = false, _mut76042 = false, _mut76043 = false, _mut76044 = false, _mut76045 = false, _mut76046 = false, _mut76047 = false, _mut76048 = false, _mut76049 = false, _mut76050 = false, _mut76051 = false, _mut76052 = false, _mut76053 = false, _mut76054 = false, _mut76055 = false, _mut76056 = false, _mut76057 = false, _mut76058 = false, _mut76059 = false, _mut76060 = false, _mut76061 = false, _mut76062 = false, _mut76063 = false, _mut76064 = false, _mut76065 = false, _mut76066 = false, _mut76067 = false, _mut76068 = false, _mut76069 = false, _mut76070 = false, _mut76071 = false, _mut76072 = false, _mut76073 = false, _mut76074 = false, _mut76075 = false, _mut76076 = false, _mut76077 = false, _mut76078 = false, _mut76079 = false, _mut76080 = false, _mut76081 = false, _mut76082 = false, _mut76083 = false, _mut76084 = false, _mut76085 = false, _mut76086 = false, _mut76087 = false, _mut76088 = false, _mut76089 = false, _mut76090 = false, _mut76091 = false, _mut76092 = false, _mut76093 = false, _mut76094 = false, _mut76095 = false, _mut76096 = false, _mut76097 = false, _mut76098 = false, _mut76099 = false, _mut76100 = false, _mut76101 = false, _mut76102 = false, _mut76103 = false, _mut76104 = false, _mut76105 = false, _mut76106 = false, _mut76107 = false, _mut76108 = false, _mut76109 = false, _mut76110 = false, _mut76111 = false, _mut76112 = false, _mut76113 = false, _mut76114 = false, _mut76115 = false, _mut76116 = false, _mut76117 = false, _mut76118 = false, _mut76119 = false, _mut76120 = false, _mut76121 = false, _mut76122 = false, _mut76123 = false, _mut76124 = false, _mut76125 = false, _mut76126 = false, _mut76127 = false, _mut76128 = false, _mut76129 = false, _mut76130 = false, _mut76131 = false, _mut76132 = false, _mut76133 = false, _mut76134 = false, _mut76135 = false, _mut76136 = false, _mut76137 = false, _mut76138 = false, _mut76139 = false, _mut76140 = false, _mut76141 = false, _mut76142 = false, _mut76143 = false, _mut76144 = false, _mut76145 = false, _mut76146 = false, _mut76147 = false, _mut76148 = false, _mut76149 = false, _mut76150 = false, _mut76151 = false, _mut76152 = false, _mut76153 = false, _mut76154 = false, _mut76155 = false, _mut76156 = false, _mut76157 = false, _mut76158 = false, _mut76159 = false, _mut76160 = false, _mut76161 = false, _mut76162 = false, _mut76163 = false, _mut76164 = false, _mut76165 = false, _mut76166 = false, _mut76167 = false, _mut76168 = false, _mut76169 = false, _mut76170 = false, _mut76171 = false, _mut76172 = false, _mut76173 = false, _mut76174 = false, _mut76175 = false, _mut76176 = false, _mut76177 = false, _mut76178 = false, _mut76179 = false, _mut76180 = false, _mut76181 = false, _mut76182 = false, _mut76183 = false, _mut76184 = false, _mut76185 = false, _mut76186 = false, _mut76187 = false, _mut76188 = false, _mut76189 = false, _mut76190 = false, _mut76191 = false, _mut76192 = false, _mut76193 = false, _mut76194 = false, _mut76195 = false, _mut76196 = false, _mut76197 = false, _mut76198 = false, _mut76199 = false, _mut76200 = false, _mut76201 = false, _mut76202 = false, _mut76203 = false, _mut76204 = false, _mut76205 = false, _mut76206 = false, _mut76207 = false, _mut76208 = false, _mut76209 = false, _mut76210 = false, _mut76211 = false, _mut76212 = false, _mut76213 = false, _mut76214 = false, _mut76215 = false, _mut76216 = false, _mut76217 = false, _mut76218 = false, _mut76219 = false, _mut76220 = false, _mut76221 = false, _mut76222 = false, _mut76223 = false, _mut76224 = false, _mut76225 = false, _mut76226 = false, _mut76227 = false, _mut76228 = false, _mut76229 = false, _mut76230 = false, _mut76231 = false, _mut76232 = false, _mut76233 = false, _mut76234 = false, _mut76235 = false, _mut76236 = false, _mut76237 = false, _mut76238 = false, _mut76239 = false, _mut76240 = false, _mut76241 = false, _mut76242 = false, _mut76243 = false, _mut76244 = false, _mut76245 = false, _mut76246 = false, _mut76247 = false, _mut76248 = false, _mut76249 = false, _mut76250 = false, _mut76251 = false, _mut76252 = false, _mut76253 = false, _mut76254 = false, _mut76255 = false, _mut76256 = false, _mut76257 = false, _mut76258 = false, _mut76259 = false, _mut76260 = false, _mut76261 = false, _mut76262 = false, _mut76263 = false, _mut76264 = false, _mut76265 = false, _mut76266 = false, _mut76267 = false, _mut76268 = false, _mut76269 = false, _mut76270 = false, _mut76271 = false, _mut76272 = false, _mut76273 = false, _mut76274 = false, _mut76275 = false, _mut76276 = false, _mut76277 = false, _mut76278 = false, _mut76279 = false, _mut76280 = false, _mut76281 = false, _mut76282 = false, _mut76283 = false, _mut76284 = false, _mut76285 = false, _mut76286 = false, _mut76287 = false, _mut76288 = false, _mut76289 = false, _mut76290 = false, _mut76291 = false, _mut76292 = false, _mut76293 = false, _mut76294 = false, _mut76295 = false, _mut76296 = false, _mut76297 = false, _mut76298 = false, _mut76299 = false, _mut76300 = false, _mut76301 = false, _mut76302 = false, _mut76303 = false, _mut76304 = false, _mut76305 = false, _mut76306 = false, _mut76307 = false, _mut76308 = false, _mut76309 = false, _mut76310 = false, _mut76311 = false, _mut76312 = false, _mut76313 = false, _mut76314 = false, _mut76315 = false, _mut76316 = false, _mut76317 = false, _mut76318 = false, _mut76319 = false, _mut76320 = false, _mut76321 = false, _mut76322 = false, _mut76323 = false, _mut76324 = false, _mut76325 = false, _mut76326 = false, _mut76327 = false, _mut76328 = false, _mut76329 = false, _mut76330 = false, _mut76331 = false, _mut76332 = false, _mut76333 = false, _mut76334 = false, _mut76335 = false, _mut76336 = false, _mut76337 = false, _mut76338 = false, _mut76339 = false, _mut76340 = false, _mut76341 = false, _mut76342 = false, _mut76343 = false, _mut76344 = false, _mut76345 = false, _mut76346 = false, _mut76347 = false, _mut76348 = false, _mut76349 = false, _mut76350 = false, _mut76351 = false, _mut76352 = false, _mut76353 = false, _mut76354 = false, _mut76355 = false, _mut76356 = false, _mut76357 = false, _mut76358 = false, _mut76359 = false, _mut76360 = false, _mut76361 = false, _mut76362 = false, _mut76363 = false, _mut76364 = false, _mut76365 = false, _mut76366 = false, _mut76367 = false, _mut76368 = false, _mut76369 = false, _mut76370 = false, _mut76371 = false, _mut76372 = false, _mut76373 = false, _mut76374 = false, _mut76375 = false, _mut76376 = false, _mut76377 = false, _mut76378 = false, _mut76379 = false, _mut76380 = false, _mut76381 = false, _mut76382 = false, _mut76383 = false, _mut76384 = false, _mut76385 = false, _mut76386 = false, _mut76387 = false, _mut76388 = false, _mut76389 = false, _mut76390 = false, _mut76391 = false, _mut76392 = false, _mut76393 = false, _mut76394 = false, _mut76395 = false, _mut76396 = false, _mut76397 = false, _mut76398 = false, _mut76399 = false, _mut76400 = false, _mut76401 = false, _mut76402 = false, _mut76403 = false, _mut76404 = false, _mut76405 = false, _mut76406 = false, _mut76407 = false, _mut76408 = false, _mut76409 = false, _mut76410 = false, _mut76411 = false, _mut76412 = false, _mut76413 = false, _mut76414 = false, _mut76415 = false, _mut76416 = false, _mut76417 = false, _mut76418 = false, _mut76419 = false, _mut76420 = false, _mut76421 = false, _mut76422 = false, _mut76423 = false, _mut76424 = false, _mut76425 = false, _mut76426 = false, _mut76427 = false, _mut76428 = false, _mut76429 = false, _mut76430 = false, _mut76431 = false, _mut76432 = false, _mut76433 = false, _mut76434 = false, _mut76435 = false, _mut76436 = false, _mut76437 = false, _mut76438 = false, _mut76439 = false, _mut76440 = false, _mut76441 = false, _mut76442 = false, _mut76443 = false, _mut76444 = false, _mut76445 = false, _mut76446 = false, _mut76447 = false, _mut76448 = false, _mut76449 = false, _mut76450 = false, _mut76451 = false, _mut76452 = false, _mut76453 = false, _mut76454 = false, _mut76455 = false, _mut76456 = false, _mut76457 = false, _mut76458 = false, _mut76459 = false, _mut76460 = false, _mut76461 = false, _mut76462 = false, _mut76463 = false, _mut76464 = false, _mut76465 = false, _mut76466 = false, _mut76467 = false, _mut76468 = false, _mut76469 = false, _mut76470 = false, _mut76471 = false, _mut76472 = false, _mut76473 = false, _mut76474 = false, _mut76475 = false, _mut76476 = false, _mut76477 = false, _mut76478 = false, _mut76479 = false, _mut76480 = false, _mut76481 = false, _mut76482 = false, _mut76483 = false, _mut76484 = false, _mut76485 = false, _mut76486 = false, _mut76487 = false, _mut76488 = false, _mut76489 = false, _mut76490 = false, _mut76491 = false, _mut76492 = false, _mut76493 = false, _mut76494 = false, _mut76495 = false, _mut76496 = false, _mut76497 = false, _mut76498 = false, _mut76499 = false, _mut76500 = false, _mut76501 = false, _mut76502 = false, _mut76503 = false, _mut76504 = false, _mut76505 = false, _mut76506 = false, _mut76507 = false, _mut76508 = false, _mut76509 = false, _mut76510 = false, _mut76511 = false, _mut76512 = false, _mut76513 = false, _mut76514 = false, _mut76515 = false, _mut76516 = false, _mut76517 = false, _mut76518 = false, _mut76519 = false, _mut76520 = false, _mut76521 = false, _mut76522 = false, _mut76523 = false, _mut76524 = false, _mut76525 = false, _mut76526 = false, _mut76527 = false, _mut76528 = false, _mut76529 = false, _mut76530 = false, _mut76531 = false, _mut76532 = false, _mut76533 = false, _mut76534 = false, _mut76535 = false, _mut76536 = false, _mut76537 = false, _mut76538 = false, _mut76539 = false, _mut76540 = false, _mut76541 = false, _mut76542 = false, _mut76543 = false, _mut76544 = false, _mut76545 = false, _mut76546 = false, _mut76547 = false, _mut76548 = false, _mut76549 = false, _mut76550 = false, _mut76551 = false, _mut76552 = false, _mut76553 = false, _mut76554 = false, _mut76555 = false, _mut76556 = false, _mut76557 = false, _mut76558 = false, _mut76559 = false, _mut76560 = false, _mut76561 = false, _mut76562 = false, _mut76563 = false, _mut76564 = false, _mut76565 = false, _mut76566 = false, _mut76567 = false, _mut76568 = false, _mut76569 = false, _mut76570 = false, _mut76571 = false, _mut76572 = false, _mut76573 = false, _mut76574 = false, _mut76575 = false, _mut76576 = false, _mut76577 = false, _mut76578 = false, _mut76579 = false, _mut76580 = false, _mut76581 = false, _mut76582 = false, _mut76583 = false, _mut76584 = false, _mut76585 = false, _mut76586 = false, _mut76587 = false, _mut76588 = false, _mut76589 = false, _mut76590 = false, _mut76591 = false, _mut76592 = false, _mut76593 = false, _mut76594 = false, _mut76595 = false, _mut76596 = false, _mut76597 = false, _mut76598 = false, _mut76599 = false, _mut76600 = false, _mut76601 = false, _mut76602 = false, _mut76603 = false, _mut76604 = false, _mut76605 = false, _mut76606 = false, _mut76607 = false, _mut76608 = false, _mut76609 = false, _mut76610 = false, _mut76611 = false, _mut76612 = false, _mut76613 = false, _mut76614 = false, _mut76615 = false, _mut76616 = false, _mut76617 = false, _mut76618 = false, _mut76619 = false, _mut76620 = false, _mut76621 = false, _mut76622 = false, _mut76623 = false, _mut76624 = false, _mut76625 = false, _mut76626 = false, _mut76627 = false, _mut76628 = false, _mut76629 = false, _mut76630 = false, _mut76631 = false, _mut76632 = false, _mut76633 = false, _mut76634 = false, _mut76635 = false, _mut76636 = false, _mut76637 = false, _mut76638 = false, _mut76639 = false, _mut76640 = false, _mut76641 = false, _mut76642 = false, _mut76643 = false, _mut76644 = false, _mut76645 = false, _mut76646 = false, _mut76647 = false, _mut76648 = false, _mut76649 = false, _mut76650 = false, _mut76651 = false, _mut76652 = false, _mut76653 = false, _mut76654 = false, _mut76655 = false, _mut76656 = false, _mut76657 = false, _mut76658 = false, _mut76659 = false, _mut76660 = false, _mut76661 = false, _mut76662 = false, _mut76663 = false, _mut76664 = false, _mut76665 = false, _mut76666 = false, _mut76667 = false, _mut76668 = false, _mut76669 = false, _mut76670 = false, _mut76671 = false, _mut76672 = false, _mut76673 = false, _mut76674 = false, _mut76675 = false, _mut76676 = false, _mut76677 = false, _mut76678 = false, _mut76679 = false, _mut76680 = false, _mut76681 = false, _mut76682 = false, _mut76683 = false, _mut76684 = false, _mut76685 = false, _mut76686 = false, _mut76687 = false, _mut76688 = false, _mut76689 = false, _mut76690 = false, _mut76691 = false, _mut76692 = false, _mut76693 = false, _mut76694 = false, _mut76695 = false, _mut76696 = false, _mut76697 = false, _mut76698 = false, _mut76699 = false, _mut76700 = false, _mut76701 = false, _mut76702 = false, _mut76703 = false, _mut76704 = false, _mut76705 = false, _mut76706 = false, _mut76707 = false, _mut76708 = false, _mut76709 = false, _mut76710 = false, _mut76711 = false, _mut76712 = false, _mut76713 = false, _mut76714 = false, _mut76715 = false, _mut76716 = false, _mut76717 = false, _mut76718 = false, _mut76719 = false, _mut76720 = false, _mut76721 = false, _mut76722 = false, _mut76723 = false, _mut76724 = false, _mut76725 = false, _mut76726 = false, _mut76727 = false, _mut76728 = false, _mut76729 = false, _mut76730 = false, _mut76731 = false, _mut76732 = false, _mut76733 = false, _mut76734 = false, _mut76735 = false, _mut76736 = false, _mut76737 = false, _mut76738 = false, _mut76739 = false, _mut76740 = false, _mut76741 = false, _mut76742 = false, _mut76743 = false, _mut76744 = false, _mut76745 = false, _mut76746 = false, _mut76747 = false, _mut76748 = false, _mut76749 = false, _mut76750 = false, _mut76751 = false, _mut76752 = false, _mut76753 = false, _mut76754 = false, _mut76755 = false, _mut76756 = false, _mut76757 = false, _mut76758 = false, _mut76759 = false, _mut76760 = false, _mut76761 = false, _mut76762 = false, _mut76763 = false, _mut76764 = false, _mut76765 = false, _mut76766 = false, _mut76767 = false, _mut76768 = false, _mut76769 = false, _mut76770 = false, _mut76771 = false, _mut76772 = false, _mut76773 = false, _mut76774 = false, _mut76775 = false, _mut76776 = false, _mut76777 = false, _mut76778 = false, _mut76779 = false, _mut76780 = false, _mut76781 = false, _mut76782 = false, _mut76783 = false, _mut76784 = false, _mut76785 = false, _mut76786 = false, _mut76787 = false, _mut76788 = false, _mut76789 = false, _mut76790 = false, _mut76791 = false, _mut76792 = false, _mut76793 = false, _mut76794 = false, _mut76795 = false, _mut76796 = false, _mut76797 = false, _mut76798 = false, _mut76799 = false, _mut76800 = false, _mut76801 = false, _mut76802 = false, _mut76803 = false, _mut76804 = false, _mut76805 = false, _mut76806 = false, _mut76807 = false, _mut76808 = false, _mut76809 = false, _mut76810 = false, _mut76811 = false, _mut76812 = false, _mut76813 = false, _mut76814 = false, _mut76815 = false, _mut76816 = false, _mut76817 = false, _mut76818 = false, _mut76819 = false, _mut76820 = false, _mut76821 = false, _mut76822 = false, _mut76823 = false, _mut76824 = false, _mut76825 = false, _mut76826 = false, _mut76827 = false, _mut76828 = false, _mut76829 = false, _mut76830 = false, _mut76831 = false, _mut76832 = false, _mut76833 = false, _mut76834 = false, _mut76835 = false, _mut76836 = false, _mut76837 = false, _mut76838 = false, _mut76839 = false, _mut76840 = false, _mut76841 = false, _mut76842 = false, _mut76843 = false, _mut76844 = false, _mut76845 = false, _mut76846 = false, _mut76847 = false, _mut76848 = false, _mut76849 = false, _mut76850 = false, _mut76851 = false, _mut76852 = false, _mut76853 = false, _mut76854 = false, _mut76855 = false, _mut76856 = false, _mut76857 = false, _mut76858 = false, _mut76859 = false, _mut76860 = false, _mut76861 = false, _mut76862 = false, _mut76863 = false, _mut76864 = false, _mut76865 = false, _mut76866 = false, _mut76867 = false, _mut76868 = false, _mut76869 = false, _mut76870 = false, _mut76871 = false, _mut76872 = false, _mut76873 = false, _mut76874 = false, _mut76875 = false, _mut76876 = false, _mut76877 = false, _mut76878 = false, _mut76879 = false, _mut76880 = false, _mut76881 = false, _mut76882 = false, _mut76883 = false, _mut76884 = false, _mut76885 = false, _mut76886 = false, _mut76887 = false, _mut76888 = false, _mut76889 = false, _mut76890 = false, _mut76891 = false, _mut76892 = false, _mut76893 = false, _mut76894 = false, _mut76895 = false, _mut76896 = false, _mut76897 = false, _mut76898 = false, _mut76899 = false, _mut76900 = false, _mut76901 = false, _mut76902 = false, _mut76903 = false, _mut76904 = false, _mut76905 = false, _mut76906 = false, _mut76907 = false, _mut76908 = false, _mut76909 = false, _mut76910 = false, _mut76911 = false, _mut76912 = false, _mut76913 = false, _mut76914 = false, _mut76915 = false, _mut76916 = false, _mut76917 = false, _mut76918 = false, _mut76919 = false, _mut76920 = false, _mut76921 = false, _mut76922 = false, _mut76923 = false, _mut76924 = false, _mut76925 = false, _mut76926 = false, _mut76927 = false, _mut76928 = false, _mut76929 = false, _mut76930 = false, _mut76931 = false, _mut76932 = false, _mut76933 = false, _mut76934 = false, _mut76935 = false, _mut76936 = false, _mut76937 = false, _mut76938 = false, _mut76939 = false, _mut76940 = false, _mut76941 = false, _mut76942 = false, _mut76943 = false, _mut76944 = false, _mut76945 = false, _mut76946 = false, _mut76947 = false, _mut76948 = false, _mut76949 = false, _mut76950 = false, _mut76951 = false, _mut76952 = false, _mut76953 = false, _mut76954 = false, _mut76955 = false, _mut76956 = false, _mut76957 = false, _mut76958 = false, _mut76959 = false, _mut76960 = false, _mut76961 = false, _mut76962 = false, _mut76963 = false, _mut76964 = false, _mut76965 = false, _mut76966 = false, _mut76967 = false, _mut76968 = false, _mut76969 = false, _mut76970 = false, _mut76971 = false, _mut76972 = false, _mut76973 = false, _mut76974 = false, _mut76975 = false, _mut76976 = false, _mut76977 = false, _mut76978 = false, _mut76979 = false, _mut76980 = false, _mut76981 = false, _mut76982 = false, _mut76983 = false, _mut76984 = false, _mut76985 = false, _mut76986 = false, _mut76987 = false, _mut76988 = false, _mut76989 = false, _mut76990 = false, _mut76991 = false, _mut76992 = false, _mut76993 = false, _mut76994 = false, _mut76995 = false, _mut76996 = false, _mut76997 = false, _mut76998 = false, _mut76999 = false, _mut77000 = false, _mut77001 = false, _mut77002 = false, _mut77003 = false, _mut77004 = false, _mut77005 = false, _mut77006 = false, _mut77007 = false, _mut77008 = false, _mut77009 = false, _mut77010 = false, _mut77011 = false, _mut77012 = false, _mut77013 = false, _mut77014 = false, _mut77015 = false, _mut77016 = false, _mut77017 = false, _mut77018 = false, _mut77019 = false, _mut77020 = false, _mut77021 = false, _mut77022 = false, _mut77023 = false, _mut77024 = false, _mut77025 = false, _mut77026 = false, _mut77027 = false, _mut77028 = false, _mut77029 = false, _mut77030 = false, _mut77031 = false, _mut77032 = false, _mut77033 = false, _mut77034 = false, _mut77035 = false, _mut77036 = false, _mut77037 = false, _mut77038 = false, _mut77039 = false, _mut77040 = false, _mut77041 = false, _mut77042 = false, _mut77043 = false, _mut77044 = false, _mut77045 = false, _mut77046 = false, _mut77047 = false, _mut77048 = false, _mut77049 = false, _mut77050 = false, _mut77051 = false, _mut77052 = false, _mut77053 = false, _mut77054 = false, _mut77055 = false, _mut77056 = false, _mut77057 = false, _mut77058 = false, _mut77059 = false, _mut77060 = false, _mut77061 = false, _mut77062 = false, _mut77063 = false, _mut77064 = false, _mut77065 = false, _mut77066 = false, _mut77067 = false, _mut77068 = false, _mut77069 = false, _mut77070 = false, _mut77071 = false, _mut77072 = false, _mut77073 = false, _mut77074 = false, _mut77075 = false, _mut77076 = false, _mut77077 = false, _mut77078 = false, _mut77079 = false, _mut77080 = false, _mut77081 = false, _mut77082 = false, _mut77083 = false, _mut77084 = false, _mut77085 = false, _mut77086 = false, _mut77087 = false, _mut77088 = false, _mut77089 = false, _mut77090 = false, _mut77091 = false, _mut77092 = false, _mut77093 = false, _mut77094 = false, _mut77095 = false, _mut77096 = false, _mut77097 = false, _mut77098 = false, _mut77099 = false, _mut77100 = false, _mut77101 = false, _mut77102 = false, _mut77103 = false, _mut77104 = false, _mut77105 = false, _mut77106 = false, _mut77107 = false, _mut77108 = false, _mut77109 = false, _mut77110 = false, _mut77111 = false, _mut77112 = false, _mut77113 = false, _mut77114 = false, _mut77115 = false, _mut77116 = false, _mut77117 = false, _mut77118 = false, _mut77119 = false, _mut77120 = false, _mut77121 = false, _mut77122 = false, _mut77123 = false, _mut77124 = false, _mut77125 = false, _mut77126 = false, _mut77127 = false, _mut77128 = false, _mut77129 = false, _mut77130 = false, _mut77131 = false, _mut77132 = false, _mut77133 = false, _mut77134 = false, _mut77135 = false, _mut77136 = false, _mut77137 = false, _mut77138 = false, _mut77139 = false, _mut77140 = false, _mut77141 = false, _mut77142 = false, _mut77143 = false, _mut77144 = false, _mut77145 = false, _mut77146 = false, _mut77147 = false, _mut77148 = false, _mut77149 = false, _mut77150 = false, _mut77151 = false, _mut77152 = false, _mut77153 = false, _mut77154 = false, _mut77155 = false, _mut77156 = false, _mut77157 = false, _mut77158 = false, _mut77159 = false, _mut77160 = false, _mut77161 = false, _mut77162 = false, _mut77163 = false, _mut77164 = false, _mut77165 = false, _mut77166 = false, _mut77167 = false, _mut77168 = false, _mut77169 = false, _mut77170 = false, _mut77171 = false, _mut77172 = false, _mut77173 = false, _mut77174 = false, _mut77175 = false, _mut77176 = false, _mut77177 = false, _mut77178 = false, _mut77179 = false, _mut77180 = false, _mut77181 = false, _mut77182 = false, _mut77183 = false, _mut77184 = false, _mut77185 = false, _mut77186 = false, _mut77187 = false, _mut77188 = false, _mut77189 = false, _mut77190 = false, _mut77191 = false, _mut77192 = false, _mut77193 = false, _mut77194 = false, _mut77195 = false, _mut77196 = false, _mut77197 = false, _mut77198 = false, _mut77199 = false, _mut77200 = false, _mut77201 = false, _mut77202 = false, _mut77203 = false, _mut77204 = false, _mut77205 = false, _mut77206 = false, _mut77207 = false, _mut77208 = false, _mut77209 = false, _mut77210 = false, _mut77211 = false, _mut77212 = false, _mut77213 = false, _mut77214 = false, _mut77215 = false, _mut77216 = false, _mut77217 = false, _mut77218 = false, _mut77219 = false, _mut77220 = false, _mut77221 = false, _mut77222 = false, _mut77223 = false, _mut77224 = false, _mut77225 = false, _mut77226 = false, _mut77227 = false, _mut77228 = false, _mut77229 = false, _mut77230 = false, _mut77231 = false, _mut77232 = false, _mut77233 = false, _mut77234 = false, _mut77235 = false, _mut77236 = false, _mut77237 = false, _mut77238 = false, _mut77239 = false, _mut77240 = false, _mut77241 = false, _mut77242 = false, _mut77243 = false, _mut77244 = false, _mut77245 = false, _mut77246 = false, _mut77247 = false, _mut77248 = false, _mut77249 = false, _mut77250 = false, _mut77251 = false, _mut77252 = false, _mut77253 = false, _mut77254 = false, _mut77255 = false, _mut77256 = false, _mut77257 = false, _mut77258 = false, _mut77259 = false, _mut77260 = false, _mut77261 = false, _mut77262 = false, _mut77263 = false, _mut77264 = false, _mut77265 = false, _mut77266 = false, _mut77267 = false, _mut77268 = false, _mut77269 = false, _mut77270 = false, _mut77271 = false, _mut77272 = false, _mut77273 = false, _mut77274 = false, _mut77275 = false, _mut77276 = false, _mut77277 = false, _mut77278 = false, _mut77279 = false, _mut77280 = false, _mut77281 = false, _mut77282 = false, _mut77283 = false, _mut77284 = false, _mut77285 = false, _mut77286 = false, _mut77287 = false, _mut77288 = false, _mut77289 = false, _mut77290 = false, _mut77291 = false, _mut77292 = false, _mut77293 = false, _mut77294 = false, _mut77295 = false, _mut77296 = false, _mut77297 = false, _mut77298 = false, _mut77299 = false, _mut77300 = false, _mut77301 = false, _mut77302 = false, _mut77303 = false, _mut77304 = false, _mut77305 = false, _mut77306 = false, _mut77307 = false, _mut77308 = false, _mut77309 = false, _mut77310 = false, _mut77311 = false, _mut77312 = false, _mut77313 = false, _mut77314 = false, _mut77315 = false, _mut77316 = false, _mut77317 = false, _mut77318 = false, _mut77319 = false, _mut77320 = false, _mut77321 = false, _mut77322 = false, _mut77323 = false, _mut77324 = false, _mut77325 = false, _mut77326 = false, _mut77327 = false, _mut77328 = false, _mut77329 = false, _mut77330 = false, _mut77331 = false, _mut77332 = false, _mut77333 = false, _mut77334 = false, _mut77335 = false, _mut77336 = false, _mut77337 = false, _mut77338 = false, _mut77339 = false, _mut77340 = false, _mut77341 = false, _mut77342 = false, _mut77343 = false, _mut77344 = false, _mut77345 = false, _mut77346 = false, _mut77347 = false, _mut77348 = false, _mut77349 = false, _mut77350 = false, _mut77351 = false, _mut77352 = false, _mut77353 = false, _mut77354 = false, _mut77355 = false, _mut77356 = false, _mut77357 = false, _mut77358 = false, _mut77359 = false, _mut77360 = false, _mut77361 = false, _mut77362 = false, _mut77363 = false, _mut77364 = false, _mut77365 = false, _mut77366 = false, _mut77367 = false, _mut77368 = false, _mut77369 = false, _mut77370 = false, _mut77371 = false, _mut77372 = false, _mut77373 = false, _mut77374 = false, _mut77375 = false, _mut77376 = false, _mut77377 = false, _mut77378 = false, _mut77379 = false, _mut77380 = false, _mut77381 = false, _mut77382 = false, _mut77383 = false, _mut77384 = false, _mut77385 = false, _mut77386 = false, _mut77387 = false, _mut77388 = false, _mut77389 = false, _mut77390 = false, _mut77391 = false, _mut77392 = false, _mut77393 = false, _mut77394 = false, _mut77395 = false, _mut77396 = false, _mut77397 = false, _mut77398 = false, _mut77399 = false, _mut77400 = false, _mut77401 = false, _mut77402 = false, _mut77403 = false, _mut77404 = false, _mut77405 = false, _mut77406 = false, _mut77407 = false, _mut77408 = false, _mut77409 = false, _mut77410 = false, _mut77411 = false, _mut77412 = false, _mut77413 = false, _mut77414 = false, _mut77415 = false, _mut77416 = false, _mut77417 = false, _mut77418 = false, _mut77419 = false, _mut77420 = false, _mut77421 = false, _mut77422 = false, _mut77423 = false, _mut77424 = false, _mut77425 = false, _mut77426 = false, _mut77427 = false, _mut77428 = false, _mut77429 = false, _mut77430 = false, _mut77431 = false, _mut77432 = false, _mut77433 = false, _mut77434 = false, _mut77435 = false, _mut77436 = false, _mut77437 = false, _mut77438 = false, _mut77439 = false, _mut77440 = false, _mut77441 = false, _mut77442 = false, _mut77443 = false, _mut77444 = false, _mut77445 = false, _mut77446 = false, _mut77447 = false, _mut77448 = false, _mut77449 = false, _mut77450 = false, _mut77451 = false, _mut77452 = false, _mut77453 = false, _mut77454 = false, _mut77455 = false, _mut77456 = false, _mut77457 = false, _mut77458 = false, _mut77459 = false, _mut77460 = false, _mut77461 = false, _mut77462 = false, _mut77463 = false, _mut77464 = false, _mut77465 = false, _mut77466 = false, _mut77467 = false, _mut77468 = false, _mut77469 = false, _mut77470 = false, _mut77471 = false, _mut77472 = false, _mut77473 = false, _mut77474 = false, _mut77475 = false, _mut77476 = false, _mut77477 = false, _mut77478 = false, _mut77479 = false, _mut77480 = false, _mut77481 = false, _mut77482 = false, _mut77483 = false, _mut77484 = false, _mut77485 = false, _mut77486 = false, _mut77487 = false, _mut77488 = false, _mut77489 = false, _mut77490 = false, _mut77491 = false, _mut77492 = false, _mut77493 = false, _mut77494 = false, _mut77495 = false, _mut77496 = false, _mut77497 = false, _mut77498 = false, _mut77499 = false, _mut77500 = false, _mut77501 = false, _mut77502 = false, _mut77503 = false, _mut77504 = false, _mut77505 = false, _mut77506 = false, _mut77507 = false, _mut77508 = false, _mut77509 = false, _mut77510 = false, _mut77511 = false, _mut77512 = false, _mut77513 = false, _mut77514 = false, _mut77515 = false, _mut77516 = false, _mut77517 = false, _mut77518 = false, _mut77519 = false, _mut77520 = false, _mut77521 = false, _mut77522 = false, _mut77523 = false, _mut77524 = false, _mut77525 = false, _mut77526 = false, _mut77527 = false, _mut77528 = false, _mut77529 = false, _mut77530 = false, _mut77531 = false, _mut77532 = false, _mut77533 = false, _mut77534 = false, _mut77535 = false, _mut77536 = false, _mut77537 = false, _mut77538 = false, _mut77539 = false, _mut77540 = false, _mut77541 = false, _mut77542 = false, _mut77543 = false, _mut77544 = false, _mut77545 = false, _mut77546 = false, _mut77547 = false, _mut77548 = false, _mut77549 = false, _mut77550 = false, _mut77551 = false, _mut77552 = false, _mut77553 = false, _mut77554 = false, _mut77555 = false, _mut77556 = false, _mut77557 = false, _mut77558 = false, _mut77559 = false, _mut77560 = false, _mut77561 = false, _mut77562 = false, _mut77563 = false, _mut77564 = false, _mut77565 = false, _mut77566 = false, _mut77567 = false, _mut77568 = false, _mut77569 = false, _mut77570 = false, _mut77571 = false, _mut77572 = false, _mut77573 = false, _mut77574 = false, _mut77575 = false, _mut77576 = false, _mut77577 = false, _mut77578 = false, _mut77579 = false, _mut77580 = false, _mut77581 = false, _mut77582 = false, _mut77583 = false, _mut77584 = false, _mut77585 = false, _mut77586 = false, _mut77587 = false, _mut77588 = false, _mut77589 = false, _mut77590 = false, _mut77591 = false, _mut77592 = false, _mut77593 = false, _mut77594 = false, _mut77595 = false, _mut77596 = false, _mut77597 = false, _mut77598 = false, _mut77599 = false, _mut77600 = false, _mut77601 = false, _mut77602 = false, _mut77603 = false, _mut77604 = false, _mut77605 = false, _mut77606 = false, _mut77607 = false, _mut77608 = false, _mut77609 = false, _mut77610 = false, _mut77611 = false, _mut77612 = false, _mut77613 = false, _mut77614 = false, _mut77615 = false, _mut77616 = false, _mut77617 = false, _mut77618 = false, _mut77619 = false, _mut77620 = false, _mut77621 = false, _mut77622 = false, _mut77623 = false, _mut77624 = false, _mut77625 = false, _mut77626 = false, _mut77627 = false, _mut77628 = false, _mut77629 = false, _mut77630 = false, _mut77631 = false, _mut77632 = false, _mut77633 = false, _mut77634 = false, _mut77635 = false, _mut77636 = false, _mut77637 = false, _mut77638 = false, _mut77639 = false, _mut77640 = false, _mut77641 = false, _mut77642 = false, _mut77643 = false, _mut77644 = false, _mut77645 = false, _mut77646 = false, _mut77647 = false, _mut77648 = false, _mut77649 = false, _mut77650 = false, _mut77651 = false, _mut77652 = false, _mut77653 = false, _mut77654 = false, _mut77655 = false, _mut77656 = false, _mut77657 = false, _mut77658 = false, _mut77659 = false, _mut77660 = false, _mut77661 = false, _mut77662 = false, _mut77663 = false, _mut77664 = false, _mut77665 = false, _mut77666 = false, _mut77667 = false, _mut77668 = false, _mut77669 = false, _mut77670 = false, _mut77671 = false, _mut77672 = false, _mut77673 = false, _mut77674 = false, _mut77675 = false, _mut77676 = false, _mut77677 = false, _mut77678 = false, _mut77679 = false, _mut77680 = false, _mut77681 = false, _mut77682 = false, _mut77683 = false, _mut77684 = false, _mut77685 = false, _mut77686 = false, _mut77687 = false, _mut77688 = false, _mut77689 = false, _mut77690 = false, _mut77691 = false, _mut77692 = false, _mut77693 = false, _mut77694 = false, _mut77695 = false, _mut77696 = false, _mut77697 = false, _mut77698 = false, _mut77699 = false, _mut77700 = false, _mut77701 = false, _mut77702 = false, _mut77703 = false, _mut77704 = false, _mut77705 = false, _mut77706 = false, _mut77707 = false, _mut77708 = false, _mut77709 = false, _mut77710 = false, _mut77711 = false, _mut77712 = false, _mut77713 = false, _mut77714 = false, _mut77715 = false, _mut77716 = false, _mut77717 = false, _mut77718 = false, _mut77719 = false, _mut77720 = false, _mut77721 = false, _mut77722 = false, _mut77723 = false, _mut77724 = false, _mut77725 = false, _mut77726 = false, _mut77727 = false, _mut77728 = false, _mut77729 = false, _mut77730 = false, _mut77731 = false, _mut77732 = false, _mut77733 = false, _mut77734 = false, _mut77735 = false, _mut77736 = false, _mut77737 = false, _mut77738 = false, _mut77739 = false, _mut77740 = false, _mut77741 = false, _mut77742 = false, _mut77743 = false, _mut77744 = false, _mut77745 = false, _mut77746 = false, _mut77747 = false, _mut77748 = false, _mut77749 = false, _mut77750 = false, _mut77751 = false, _mut77752 = false, _mut77753 = false, _mut77754 = false, _mut77755 = false, _mut77756 = false, _mut77757 = false, _mut77758 = false, _mut77759 = false, _mut77760 = false, _mut77761 = false, _mut77762 = false, _mut77763 = false, _mut77764 = false, _mut77765 = false, _mut77766 = false, _mut77767 = false, _mut77768 = false, _mut77769 = false, _mut77770 = false, _mut77771 = false, _mut77772 = false, _mut77773 = false, _mut77774 = false, _mut77775 = false, _mut77776 = false, _mut77777 = false, _mut77778 = false, _mut77779 = false, _mut77780 = false, _mut77781 = false, _mut77782 = false, _mut77783 = false, _mut77784 = false, _mut77785 = false, _mut77786 = false, _mut77787 = false, _mut77788 = false, _mut77789 = false, _mut77790 = false, _mut77791 = false, _mut77792 = false, _mut77793 = false, _mut77794 = false, _mut77795 = false, _mut77796 = false, _mut77797 = false, _mut77798 = false, _mut77799 = false, _mut77800 = false, _mut77801 = false, _mut77802 = false, _mut77803 = false, _mut77804 = false, _mut77805 = false, _mut77806 = false, _mut77807 = false, _mut77808 = false, _mut77809 = false, _mut77810 = false, _mut77811 = false, _mut77812 = false, _mut77813 = false, _mut77814 = false, _mut77815 = false, _mut77816 = false, _mut77817 = false, _mut77818 = false, _mut77819 = false, _mut77820 = false, _mut77821 = false, _mut77822 = false, _mut77823 = false, _mut77824 = false, _mut77825 = false, _mut77826 = false, _mut77827 = false, _mut77828 = false, _mut77829 = false, _mut77830 = false, _mut77831 = false, _mut77832 = false, _mut77833 = false, _mut77834 = false, _mut77835 = false, _mut77836 = false, _mut77837 = false, _mut77838 = false, _mut77839 = false, _mut77840 = false, _mut77841 = false, _mut77842 = false, _mut77843 = false, _mut77844 = false, _mut77845 = false, _mut77846 = false, _mut77847 = false, _mut77848 = false, _mut77849 = false, _mut77850 = false, _mut77851 = false, _mut77852 = false, _mut77853 = false, _mut77854 = false, _mut77855 = false, _mut77856 = false, _mut77857 = false, _mut77858 = false, _mut77859 = false, _mut77860 = false, _mut77861 = false, _mut77862 = false, _mut77863 = false, _mut77864 = false, _mut77865 = false, _mut77866 = false, _mut77867 = false, _mut77868 = false, _mut77869 = false, _mut77870 = false, _mut77871 = false, _mut77872 = false, _mut77873 = false, _mut77874 = false, _mut77875 = false, _mut77876 = false, _mut77877 = false, _mut77878 = false, _mut77879 = false, _mut77880 = false, _mut77881 = false, _mut77882 = false, _mut77883 = false, _mut77884 = false, _mut77885 = false, _mut77886 = false, _mut77887 = false, _mut77888 = false, _mut77889 = false, _mut77890 = false, _mut77891 = false, _mut77892 = false, _mut77893 = false, _mut77894 = false, _mut77895 = false, _mut77896 = false, _mut77897 = false, _mut77898 = false, _mut77899 = false, _mut77900 = false, _mut77901 = false, _mut77902 = false, _mut77903 = false, _mut77904 = false, _mut77905 = false, _mut77906 = false, _mut77907 = false, _mut77908 = false, _mut77909 = false, _mut77910 = false, _mut77911 = false, _mut77912 = false, _mut77913 = false, _mut77914 = false, _mut77915 = false, _mut77916 = false, _mut77917 = false, _mut77918 = false, _mut77919 = false, _mut77920 = false, _mut77921 = false, _mut77922 = false, _mut77923 = false, _mut77924 = false, _mut77925 = false, _mut77926 = false, _mut77927 = false, _mut77928 = false, _mut77929 = false, _mut77930 = false, _mut77931 = false, _mut77932 = false, _mut77933 = false, _mut77934 = false, _mut77935 = false, _mut77936 = false, _mut77937 = false, _mut77938 = false, _mut77939 = false, _mut77940 = false, _mut77941 = false, _mut77942 = false, _mut77943 = false, _mut77944 = false, _mut77945 = false, _mut77946 = false, _mut77947 = false, _mut77948 = false, _mut77949 = false, _mut77950 = false, _mut77951 = false, _mut77952 = false, _mut77953 = false, _mut77954 = false, _mut77955 = false, _mut77956 = false, _mut77957 = false, _mut77958 = false, _mut77959 = false, _mut77960 = false, _mut77961 = false, _mut77962 = false, _mut77963 = false, _mut77964 = false, _mut77965 = false, _mut77966 = false, _mut77967 = false, _mut77968 = false, _mut77969 = false, _mut77970 = false, _mut77971 = false, _mut77972 = false, _mut77973 = false, _mut77974 = false, _mut77975 = false, _mut77976 = false, _mut77977 = false, _mut77978 = false, _mut77979 = false, _mut77980 = false, _mut77981 = false, _mut77982 = false, _mut77983 = false, _mut77984 = false, _mut77985 = false, _mut77986 = false, _mut77987 = false, _mut77988 = false, _mut77989 = false, _mut77990 = false, _mut77991 = false, _mut77992 = false, _mut77993 = false, _mut77994 = false, _mut77995 = false, _mut77996 = false, _mut77997 = false, _mut77998 = false, _mut77999 = false, _mut78000 = false, _mut78001 = false, _mut78002 = false, _mut78003 = false, _mut78004 = false, _mut78005 = false, _mut78006 = false, _mut78007 = false, _mut78008 = false, _mut78009 = false, _mut78010 = false, _mut78011 = false, _mut78012 = false, _mut78013 = false, _mut78014 = false, _mut78015 = false, _mut78016 = false, _mut78017 = false, _mut78018 = false, _mut78019 = false, _mut78020 = false, _mut78021 = false, _mut78022 = false, _mut78023 = false, _mut78024 = false, _mut78025 = false, _mut78026 = false, _mut78027 = false, _mut78028 = false, _mut78029 = false, _mut78030 = false, _mut78031 = false, _mut78032 = false, _mut78033 = false, _mut78034 = false, _mut78035 = false, _mut78036 = false, _mut78037 = false, _mut78038 = false, _mut78039 = false, _mut78040 = false, _mut78041 = false, _mut78042 = false, _mut78043 = false, _mut78044 = false, _mut78045 = false, _mut78046 = false, _mut78047 = false, _mut78048 = false, _mut78049 = false, _mut78050 = false, _mut78051 = false, _mut78052 = false, _mut78053 = false, _mut78054 = false, _mut78055 = false, _mut78056 = false, _mut78057 = false, _mut78058 = false, _mut78059 = false, _mut78060 = false, _mut78061 = false, _mut78062 = false, _mut78063 = false, _mut78064 = false, _mut78065 = false, _mut78066 = false, _mut78067 = false, _mut78068 = false, _mut78069 = false, _mut78070 = false, _mut78071 = false, _mut78072 = false, _mut78073 = false, _mut78074 = false, _mut78075 = false, _mut78076 = false, _mut78077 = false, _mut78078 = false, _mut78079 = false, _mut78080 = false, _mut78081 = false, _mut78082 = false, _mut78083 = false, _mut78084 = false, _mut78085 = false, _mut78086 = false, _mut78087 = false, _mut78088 = false, _mut78089 = false, _mut78090 = false, _mut78091 = false, _mut78092 = false, _mut78093 = false, _mut78094 = false, _mut78095 = false, _mut78096 = false, _mut78097 = false, _mut78098 = false, _mut78099 = false, _mut78100 = false, _mut78101 = false, _mut78102 = false, _mut78103 = false, _mut78104 = false, _mut78105 = false, _mut78106 = false, _mut78107 = false, _mut78108 = false, _mut78109 = false, _mut78110 = false, _mut78111 = false, _mut78112 = false, _mut78113 = false, _mut78114 = false, _mut78115 = false, _mut78116 = false, _mut78117 = false, _mut78118 = false, _mut78119 = false, _mut78120 = false, _mut78121 = false, _mut78122 = false, _mut78123 = false, _mut78124 = false, _mut78125 = false, _mut78126 = false, _mut78127 = false, _mut78128 = false, _mut78129 = false, _mut78130 = false, _mut78131 = false, _mut78132 = false, _mut78133 = false, _mut78134 = false, _mut78135 = false, _mut78136 = false, _mut78137 = false, _mut78138 = false, _mut78139 = false, _mut78140 = false, _mut78141 = false, _mut78142 = false, _mut78143 = false, _mut78144 = false, _mut78145 = false, _mut78146 = false, _mut78147 = false, _mut78148 = false, _mut78149 = false, _mut78150 = false, _mut78151 = false, _mut78152 = false, _mut78153 = false, _mut78154 = false, _mut78155 = false, _mut78156 = false, _mut78157 = false, _mut78158 = false, _mut78159 = false, _mut78160 = false, _mut78161 = false, _mut78162 = false, _mut78163 = false, _mut78164 = false, _mut78165 = false, _mut78166 = false, _mut78167 = false, _mut78168 = false, _mut78169 = false, _mut78170 = false, _mut78171 = false, _mut78172 = false, _mut78173 = false, _mut78174 = false, _mut78175 = false, _mut78176 = false, _mut78177 = false, _mut78178 = false, _mut78179 = false, _mut78180 = false, _mut78181 = false, _mut78182 = false, _mut78183 = false, _mut78184 = false, _mut78185 = false, _mut78186 = false, _mut78187 = false, _mut78188 = false, _mut78189 = false, _mut78190 = false, _mut78191 = false, _mut78192 = false, _mut78193 = false, _mut78194 = false, _mut78195 = false, _mut78196 = false, _mut78197 = false, _mut78198 = false, _mut78199 = false, _mut78200 = false, _mut78201 = false, _mut78202 = false, _mut78203 = false, _mut78204 = false, _mut78205 = false, _mut78206 = false, _mut78207 = false, _mut78208 = false, _mut78209 = false, _mut78210 = false, _mut78211 = false, _mut78212 = false, _mut78213 = false, _mut78214 = false, _mut78215 = false, _mut78216 = false, _mut78217 = false, _mut78218 = false, _mut78219 = false, _mut78220 = false, _mut78221 = false, _mut78222 = false, _mut78223 = false, _mut78224 = false, _mut78225 = false, _mut78226 = false, _mut78227 = false, _mut78228 = false, _mut78229 = false, _mut78230 = false, _mut78231 = false, _mut78232 = false, _mut78233 = false, _mut78234 = false, _mut78235 = false, _mut78236 = false, _mut78237 = false, _mut78238 = false, _mut78239 = false, _mut78240 = false, _mut78241 = false, _mut78242 = false, _mut78243 = false, _mut78244 = false, _mut78245 = false, _mut78246 = false, _mut78247 = false, _mut78248 = false, _mut78249 = false, _mut78250 = false, _mut78251 = false, _mut78252 = false, _mut78253 = false, _mut78254 = false, _mut78255 = false, _mut78256 = false, _mut78257 = false, _mut78258 = false, _mut78259 = false, _mut78260 = false, _mut78261 = false, _mut78262 = false, _mut78263 = false, _mut78264 = false, _mut78265 = false, _mut78266 = false, _mut78267 = false, _mut78268 = false, _mut78269 = false, _mut78270 = false, _mut78271 = false, _mut78272 = false, _mut78273 = false, _mut78274 = false, _mut78275 = false, _mut78276 = false, _mut78277 = false, _mut78278 = false, _mut78279 = false, _mut78280 = false, _mut78281 = false, _mut78282 = false, _mut78283 = false, _mut78284 = false, _mut78285 = false, _mut78286 = false, _mut78287 = false, _mut78288 = false, _mut78289 = false, _mut78290 = false, _mut78291 = false, _mut78292 = false, _mut78293 = false, _mut78294 = false, _mut78295 = false, _mut78296 = false, _mut78297 = false, _mut78298 = false, _mut78299 = false, _mut78300 = false, _mut78301 = false, _mut78302 = false, _mut78303 = false, _mut78304 = false, _mut78305 = false, _mut78306 = false, _mut78307 = false, _mut78308 = false, _mut78309 = false, _mut78310 = false, _mut78311 = false, _mut78312 = false, _mut78313 = false, _mut78314 = false, _mut78315 = false, _mut78316 = false, _mut78317 = false, _mut78318 = false, _mut78319 = false, _mut78320 = false, _mut78321 = false, _mut78322 = false, _mut78323 = false, _mut78324 = false, _mut78325 = false, _mut78326 = false, _mut78327 = false, _mut78328 = false, _mut78329 = false, _mut78330 = false, _mut78331 = false, _mut78332 = false, _mut78333 = false, _mut78334 = false, _mut78335 = false, _mut78336 = false, _mut78337 = false, _mut78338 = false, _mut78339 = false, _mut78340 = false, _mut78341 = false, _mut78342 = false, _mut78343 = false, _mut78344 = false, _mut78345 = false, _mut78346 = false, _mut78347 = false, _mut78348 = false, _mut78349 = false, _mut78350 = false, _mut78351 = false, _mut78352 = false, _mut78353 = false, _mut78354 = false, _mut78355 = false, _mut78356 = false, _mut78357 = false, _mut78358 = false, _mut78359 = false, _mut78360 = false, _mut78361 = false, _mut78362 = false, _mut78363 = false, _mut78364 = false, _mut78365 = false, _mut78366 = false, _mut78367 = false, _mut78368 = false, _mut78369 = false, _mut78370 = false, _mut78371 = false, _mut78372 = false, _mut78373 = false, _mut78374 = false, _mut78375 = false, _mut78376 = false, _mut78377 = false, _mut78378 = false, _mut78379 = false, _mut78380 = false, _mut78381 = false, _mut78382 = false, _mut78383 = false, _mut78384 = false, _mut78385 = false, _mut78386 = false, _mut78387 = false, _mut78388 = false, _mut78389 = false, _mut78390 = false, _mut78391 = false, _mut78392 = false, _mut78393 = false, _mut78394 = false, _mut78395 = false, _mut78396 = false, _mut78397 = false, _mut78398 = false, _mut78399 = false, _mut78400 = false, _mut78401 = false, _mut78402 = false, _mut78403 = false, _mut78404 = false, _mut78405 = false, _mut78406 = false, _mut78407 = false, _mut78408 = false, _mut78409 = false, _mut78410 = false, _mut78411 = false, _mut78412 = false, _mut78413 = false, _mut78414 = false, _mut78415 = false, _mut78416 = false, _mut78417 = false, _mut78418 = false, _mut78419 = false, _mut78420 = false, _mut78421 = false, _mut78422 = false, _mut78423 = false, _mut78424 = false, _mut78425 = false, _mut78426 = false, _mut78427 = false, _mut78428 = false, _mut78429 = false, _mut78430 = false, _mut78431 = false, _mut78432 = false, _mut78433 = false, _mut78434 = false, _mut78435 = false, _mut78436 = false, _mut78437 = false, _mut78438 = false, _mut78439 = false, _mut78440 = false, _mut78441 = false, _mut78442 = false, _mut78443 = false, _mut78444 = false, _mut78445 = false, _mut78446 = false, _mut78447 = false, _mut78448 = false, _mut78449 = false, _mut78450 = false, _mut78451 = false, _mut78452 = false, _mut78453 = false, _mut78454 = false, _mut78455 = false, _mut78456 = false, _mut78457 = false, _mut78458 = false, _mut78459 = false, _mut78460 = false, _mut78461 = false, _mut78462 = false, _mut78463 = false, _mut78464 = false, _mut78465 = false, _mut78466 = false, _mut78467 = false, _mut78468 = false, _mut78469 = false, _mut78470 = false, _mut78471 = false, _mut78472 = false, _mut78473 = false, _mut78474 = false, _mut78475 = false, _mut78476 = false, _mut78477 = false, _mut78478 = false, _mut78479 = false, _mut78480 = false, _mut78481 = false, _mut78482 = false, _mut78483 = false, _mut78484 = false, _mut78485 = false, _mut78486 = false, _mut78487 = false, _mut78488 = false, _mut78489 = false, _mut78490 = false, _mut78491 = false, _mut78492 = false, _mut78493 = false, _mut78494 = false, _mut78495 = false, _mut78496 = false, _mut78497 = false, _mut78498 = false, _mut78499 = false, _mut78500 = false, _mut78501 = false, _mut78502 = false, _mut78503 = false, _mut78504 = false, _mut78505 = false, _mut78506 = false, _mut78507 = false, _mut78508 = false, _mut78509 = false, _mut78510 = false, _mut78511 = false, _mut78512 = false, _mut78513 = false, _mut78514 = false, _mut78515 = false, _mut78516 = false, _mut78517 = false, _mut78518 = false, _mut78519 = false, _mut78520 = false, _mut78521 = false, _mut78522 = false, _mut78523 = false, _mut78524 = false, _mut78525 = false, _mut78526 = false, _mut78527 = false, _mut78528 = false, _mut78529 = false, _mut78530 = false, _mut78531 = false, _mut78532 = false, _mut78533 = false, _mut78534 = false, _mut78535 = false, _mut78536 = false, _mut78537 = false, _mut78538 = false, _mut78539 = false, _mut78540 = false, _mut78541 = false, _mut78542 = false, _mut78543 = false, _mut78544 = false, _mut78545 = false, _mut78546 = false, _mut78547 = false, _mut78548 = false, _mut78549 = false, _mut78550 = false, _mut78551 = false, _mut78552 = false, _mut78553 = false, _mut78554 = false, _mut78555 = false, _mut78556 = false, _mut78557 = false, _mut78558 = false, _mut78559 = false, _mut78560 = false, _mut78561 = false, _mut78562 = false, _mut78563 = false, _mut78564 = false, _mut78565 = false, _mut78566 = false, _mut78567 = false, _mut78568 = false, _mut78569 = false, _mut78570 = false, _mut78571 = false, _mut78572 = false, _mut78573 = false, _mut78574 = false, _mut78575 = false, _mut78576 = false, _mut78577 = false, _mut78578 = false, _mut78579 = false, _mut78580 = false, _mut78581 = false, _mut78582 = false, _mut78583 = false, _mut78584 = false, _mut78585 = false, _mut78586 = false, _mut78587 = false, _mut78588 = false, _mut78589 = false, _mut78590 = false, _mut78591 = false, _mut78592 = false, _mut78593 = false, _mut78594 = false, _mut78595 = false, _mut78596 = false, _mut78597 = false, _mut78598 = false, _mut78599 = false, _mut78600 = false, _mut78601 = false, _mut78602 = false, _mut78603 = false, _mut78604 = false, _mut78605 = false, _mut78606 = false, _mut78607 = false, _mut78608 = false, _mut78609 = false, _mut78610 = false, _mut78611 = false, _mut78612 = false, _mut78613 = false, _mut78614 = false, _mut78615 = false, _mut78616 = false, _mut78617 = false, _mut78618 = false, _mut78619 = false, _mut78620 = false, _mut78621 = false, _mut78622 = false, _mut78623 = false, _mut78624 = false, _mut78625 = false, _mut78626 = false, _mut78627 = false, _mut78628 = false, _mut78629 = false, _mut78630 = false, _mut78631 = false, _mut78632 = false, _mut78633 = false, _mut78634 = false, _mut78635 = false, _mut78636 = false, _mut78637 = false, _mut78638 = false, _mut78639 = false, _mut78640 = false, _mut78641 = false, _mut78642 = false, _mut78643 = false, _mut78644 = false, _mut78645 = false, _mut78646 = false, _mut78647 = false, _mut78648 = false, _mut78649 = false, _mut78650 = false, _mut78651 = false, _mut78652 = false, _mut78653 = false, _mut78654 = false, _mut78655 = false, _mut78656 = false, _mut78657 = false, _mut78658 = false, _mut78659 = false, _mut78660 = false, _mut78661 = false, _mut78662 = false, _mut78663 = false, _mut78664 = false, _mut78665 = false, _mut78666 = false, _mut78667 = false, _mut78668 = false, _mut78669 = false, _mut78670 = false, _mut78671 = false, _mut78672 = false, _mut78673 = false, _mut78674 = false, _mut78675 = false, _mut78676 = false, _mut78677 = false, _mut78678 = false, _mut78679 = false, _mut78680 = false, _mut78681 = false, _mut78682 = false, _mut78683 = false, _mut78684 = false, _mut78685 = false, _mut78686 = false, _mut78687 = false, _mut78688 = false, _mut78689 = false, _mut78690 = false, _mut78691 = false, _mut78692 = false, _mut78693 = false, _mut78694 = false, _mut78695 = false, _mut78696 = false, _mut78697 = false, _mut78698 = false, _mut78699 = false, _mut78700 = false, _mut78701 = false, _mut78702 = false, _mut78703 = false, _mut78704 = false, _mut78705 = false, _mut78706 = false, _mut78707 = false, _mut78708 = false, _mut78709 = false, _mut78710 = false, _mut78711 = false, _mut78712 = false, _mut78713 = false, _mut78714 = false, _mut78715 = false, _mut78716 = false, _mut78717 = false, _mut78718 = false, _mut78719 = false, _mut78720 = false, _mut78721 = false, _mut78722 = false, _mut78723 = false, _mut78724 = false, _mut78725 = false, _mut78726 = false, _mut78727 = false, _mut78728 = false, _mut78729 = false, _mut78730 = false, _mut78731 = false, _mut78732 = false, _mut78733 = false, _mut78734 = false, _mut78735 = false, _mut78736 = false, _mut78737 = false, _mut78738 = false, _mut78739 = false, _mut78740 = false, _mut78741 = false, _mut78742 = false, _mut78743 = false, _mut78744 = false, _mut78745 = false, _mut78746 = false, _mut78747 = false, _mut78748 = false, _mut78749 = false, _mut78750 = false, _mut78751 = false, _mut78752 = false, _mut78753 = false, _mut78754 = false, _mut78755 = false, _mut78756 = false, _mut78757 = false, _mut78758 = false, _mut78759 = false, _mut78760 = false, _mut78761 = false, _mut78762 = false, _mut78763 = false, _mut78764 = false, _mut78765 = false, _mut78766 = false, _mut78767 = false, _mut78768 = false, _mut78769 = false, _mut78770 = false, _mut78771 = false, _mut78772 = false, _mut78773 = false, _mut78774 = false, _mut78775 = false, _mut78776 = false, _mut78777 = false, _mut78778 = false, _mut78779 = false, _mut78780 = false, _mut78781 = false, _mut78782 = false, _mut78783 = false, _mut78784 = false, _mut78785 = false, _mut78786 = false, _mut78787 = false, _mut78788 = false, _mut78789 = false, _mut78790 = false, _mut78791 = false, _mut78792 = false, _mut78793 = false, _mut78794 = false, _mut78795 = false, _mut78796 = false, _mut78797 = false, _mut78798 = false, _mut78799 = false, _mut78800 = false, _mut78801 = false, _mut78802 = false, _mut78803 = false, _mut78804 = false, _mut78805 = false, _mut78806 = false, _mut78807 = false, _mut78808 = false, _mut78809 = false, _mut78810 = false, _mut78811 = false, _mut78812 = false, _mut78813 = false, _mut78814 = false, _mut78815 = false, _mut78816 = false, _mut78817 = false, _mut78818 = false, _mut78819 = false, _mut78820 = false, _mut78821 = false, _mut78822 = false, _mut78823 = false, _mut78824 = false, _mut78825 = false, _mut78826 = false, _mut78827 = false, _mut78828 = false, _mut78829 = false, _mut78830 = false, _mut78831 = false, _mut78832 = false, _mut78833 = false, _mut78834 = false, _mut78835 = false, _mut78836 = false, _mut78837 = false, _mut78838 = false, _mut78839 = false, _mut78840 = false, _mut78841 = false, _mut78842 = false, _mut78843 = false, _mut78844 = false, _mut78845 = false, _mut78846 = false, _mut78847 = false, _mut78848 = false, _mut78849 = false, _mut78850 = false, _mut78851 = false, _mut78852 = false, _mut78853 = false, _mut78854 = false, _mut78855 = false, _mut78856 = false, _mut78857 = false, _mut78858 = false, _mut78859 = false, _mut78860 = false, _mut78861 = false, _mut78862 = false, _mut78863 = false, _mut78864 = false, _mut78865 = false, _mut78866 = false, _mut78867 = false, _mut78868 = false, _mut78869 = false, _mut78870 = false, _mut78871 = false, _mut78872 = false, _mut78873 = false, _mut78874 = false, _mut78875 = false, _mut78876 = false, _mut78877 = false, _mut78878 = false, _mut78879 = false, _mut78880 = false, _mut78881 = false, _mut78882 = false, _mut78883 = false, _mut78884 = false, _mut78885 = false, _mut78886 = false, _mut78887 = false, _mut78888 = false, _mut78889 = false, _mut78890 = false, _mut78891 = false, _mut78892 = false, _mut78893 = false, _mut78894 = false, _mut78895 = false, _mut78896 = false, _mut78897 = false, _mut78898 = false, _mut78899 = false, _mut78900 = false, _mut78901 = false, _mut78902 = false, _mut78903 = false, _mut78904 = false, _mut78905 = false, _mut78906 = false, _mut78907 = false, _mut78908 = false, _mut78909 = false, _mut78910 = false, _mut78911 = false, _mut78912 = false, _mut78913 = false, _mut78914 = false, _mut78915 = false, _mut78916 = false, _mut78917 = false, _mut78918 = false, _mut78919 = false, _mut78920 = false, _mut78921 = false, _mut78922 = false, _mut78923 = false, _mut78924 = false, _mut78925 = false, _mut78926 = false, _mut78927 = false, _mut78928 = false, _mut78929 = false, _mut78930 = false, _mut78931 = false, _mut78932 = false, _mut78933 = false, _mut78934 = false, _mut78935 = false, _mut78936 = false, _mut78937 = false, _mut78938 = false;

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
    private static final double HALF = AOR_divide(ONE, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.checkParameters_274", _mut75145, _mut75146, _mut75147, _mut75148);

    /**
     * Constant 1/4.
     */
    private static final double ONE_OVER_FOUR = AOR_divide(ONE, 4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.checkParameters_274", _mut75149, _mut75150, _mut75151, _mut75152);

    /**
     * Constant 1/8.
     */
    private static final double ONE_OVER_EIGHT = AOR_divide(ONE, 8, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.checkParameters_274", _mut75153, _mut75154, _mut75155, _mut75156);

    /**
     * Constant 1/10.
     */
    private static final double ONE_OVER_TEN = AOR_divide(ONE, 10, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.checkParameters_274", _mut75157, _mut75158, _mut75159, _mut75160);

    /**
     * Constant 1/1000.
     */
    private static final double ONE_OVER_A_THOUSAND = AOR_divide(ONE, 1000, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.checkParameters_274", _mut75161, _mut75162, _mut75163, _mut75164);

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75209, _mut75210, _mut75211, _mut75212, _mut75213); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298");
            final double boundDiff = boundDifference[j];
            lowerDifference.setEntry(j, AOR_minus(lowerBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75165, _mut75166, _mut75167, _mut75168));
            upperDifference.setEntry(j, AOR_minus(upperBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75169, _mut75170, _mut75171, _mut75172));
            if (ROR_greater_equals(lowerDifference.getEntry(j), -initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75173, _mut75174, _mut75175, _mut75176, _mut75177)) {
                if (ROR_greater_equals(lowerDifference.getEntry(j), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75196, _mut75197, _mut75198, _mut75199, _mut75200)) {
                    currentBest.setEntry(j, lowerBound[j]);
                    lowerDifference.setEntry(j, ZERO);
                    upperDifference.setEntry(j, boundDiff);
                } else {
                    currentBest.setEntry(j, AOR_plus(lowerBound[j], initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75201, _mut75202, _mut75203, _mut75204));
                    lowerDifference.setEntry(j, -initialTrustRegionRadius);
                    // Computing MAX
                    final double deltaOne = AOR_minus(upperBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75205, _mut75206, _mut75207, _mut75208);
                    upperDifference.setEntry(j, FastMath.max(deltaOne, initialTrustRegionRadius));
                }
            } else if (ROR_less_equals(upperDifference.getEntry(j), initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75178, _mut75179, _mut75180, _mut75181, _mut75182)) {
                if (ROR_less_equals(upperDifference.getEntry(j), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75183, _mut75184, _mut75185, _mut75186, _mut75187)) {
                    currentBest.setEntry(j, upperBound[j]);
                    lowerDifference.setEntry(j, -boundDiff);
                    upperDifference.setEntry(j, ZERO);
                } else {
                    currentBest.setEntry(j, AOR_minus(upperBound[j], initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75188, _mut75189, _mut75190, _mut75191));
                    // Computing MIN
                    final double deltaOne = AOR_minus(lowerBound[j], currentBest.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqa_298", _mut75192, _mut75193, _mut75194, _mut75195);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int np = AOR_plus(n, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75214, _mut75215, _mut75216, _mut75217);
        final int nptm = AOR_minus(npt, np, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75218, _mut75219, _mut75220, _mut75221);
        final int nh = AOR_divide(AOR_multiply(n, np, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75222, _mut75223, _mut75224, _mut75225), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75226, _mut75227, _mut75228, _mut75229);
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
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75234, _mut75235, _mut75236, _mut75237, _mut75238); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
            trustRegionCenterOffset.setEntry(i, interpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex, i));
            // Computing 2nd power
            final double deltaOne = trustRegionCenterOffset.getEntry(i);
            xoptsq += AOR_multiply(deltaOne, deltaOne, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75230, _mut75231, _mut75232, _mut75233);
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
            switch(state) {
                case 20:
                    {
                        // XXX
                        printState(20);
                        if (ROR_not_equals(trustRegionCenterInterpolationPointIndex, kbase, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75239, _mut75240, _mut75241, _mut75242, _mut75243)) {
                            int ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75270, _mut75271, _mut75272, _mut75273, _mut75274); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75265, _mut75266, _mut75267, _mut75268, _mut75269); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    if (ROR_less(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75244, _mut75245, _mut75246, _mut75247, _mut75248)) {
                                        gradientAtTrustRegionCenter.setEntry(j, AOR_plus(gradientAtTrustRegionCenter.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75249, _mut75250, _mut75251, _mut75252), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75253, _mut75254, _mut75255, _mut75256));
                                    }
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75257, _mut75258, _mut75259, _mut75260), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75261, _mut75262, _mut75263, _mut75264));
                                    ih++;
                                }
                            }
                            if (ROR_greater(getEvaluations(), npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75275, _mut75276, _mut75277, _mut75278, _mut75279)) {
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75302, _mut75303, _mut75304, _mut75305, _mut75306); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    double temp = ZERO;
                                    for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75284, _mut75285, _mut75286, _mut75287, _mut75288); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        temp += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75280, _mut75281, _mut75282, _mut75283);
                                    }
                                    temp *= modelSecondDerivativesParameters.getEntry(k);
                                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75297, _mut75298, _mut75299, _mut75300, _mut75301); i++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75289, _mut75290, _mut75291, _mut75292), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75293, _mut75294, _mut75295, _mut75296));
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
                        if (ROR_less(dnorm, AOR_multiply(HALF, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75307, _mut75308, _mut75309, _mut75310), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75311, _mut75312, _mut75313, _mut75314, _mut75315)) {
                            ntrits = -1;
                            // Computing 2nd power
                            deltaOne = AOR_multiply(TEN, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75316, _mut75317, _mut75318, _mut75319);
                            distsq = AOR_multiply(deltaOne, deltaOne, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75320, _mut75321, _mut75322, _mut75323);
                            if (ROR_less_equals(getEvaluations(), AOR_plus(nfsav, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75324, _mut75325, _mut75326, _mut75327), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75328, _mut75329, _mut75330, _mut75331, _mut75332)) {
                                state = 650;
                                break;
                            }
                            // Computing MAX
                            deltaOne = FastMath.max(diffa, diffb);
                            final double errbig = FastMath.max(deltaOne, diffc);
                            final double frhosq = AOR_multiply(AOR_multiply(rho, ONE_OVER_EIGHT, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75333, _mut75334, _mut75335, _mut75336), rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75337, _mut75338, _mut75339, _mut75340);
                            if ((_mut75355 ? (ROR_greater(crvmin, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75341, _mut75342, _mut75343, _mut75344, _mut75345) || ROR_greater(errbig, AOR_multiply(frhosq, crvmin, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75346, _mut75347, _mut75348, _mut75349), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75350, _mut75351, _mut75352, _mut75353, _mut75354)) : (ROR_greater(crvmin, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75341, _mut75342, _mut75343, _mut75344, _mut75345) && ROR_greater(errbig, AOR_multiply(frhosq, crvmin, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75346, _mut75347, _mut75348, _mut75349), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75350, _mut75351, _mut75352, _mut75353, _mut75354)))) {
                                state = 650;
                                break;
                            }
                            final double bdtol = AOR_divide(errbig, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75356, _mut75357, _mut75358, _mut75359);
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75413, _mut75414, _mut75415, _mut75416, _mut75417); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double bdtest = bdtol;
                                if (ROR_equals(newPoint.getEntry(j), lowerDifference.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75360, _mut75361, _mut75362, _mut75363, _mut75364)) {
                                    bdtest = work1.getEntry(j);
                                }
                                if (ROR_equals(newPoint.getEntry(j), upperDifference.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75365, _mut75366, _mut75367, _mut75368, _mut75369)) {
                                    bdtest = -work1.getEntry(j);
                                }
                                if (ROR_less(bdtest, bdtol, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75370, _mut75371, _mut75372, _mut75373, _mut75374)) {
                                    double curv = modelSecondDerivativesValues.getEntry(AOR_divide((AOR_plus(j, AOR_multiply(j, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75375, _mut75376, _mut75377, _mut75378), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75379, _mut75380, _mut75381, _mut75382)), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75383, _mut75384, _mut75385, _mut75386));
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75395, _mut75396, _mut75397, _mut75398, _mut75399); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        // Computing 2nd power
                                        final double d1 = interpolationPoints.getEntry(k, j);
                                        curv += AOR_multiply(modelSecondDerivativesParameters.getEntry(k), (AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75387, _mut75388, _mut75389, _mut75390)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75391, _mut75392, _mut75393, _mut75394);
                                    }
                                    bdtest += AOR_multiply(AOR_multiply(HALF, curv, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75400, _mut75401, _mut75402, _mut75403), rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75404, _mut75405, _mut75406, _mut75407);
                                    if (ROR_less(bdtest, bdtol, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75408, _mut75409, _mut75410, _mut75411, _mut75412)) {
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
                        if (ROR_less_equals(dsq, AOR_multiply(xoptsq, ONE_OVER_A_THOUSAND, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75418, _mut75419, _mut75420, _mut75421), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75422, _mut75423, _mut75424, _mut75425, _mut75426)) {
                            final double fracsq = AOR_multiply(xoptsq, ONE_OVER_FOUR, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75427, _mut75428, _mut75429, _mut75430);
                            double sumpq = ZERO;
                            // = new ArrayRealVector(npt, -HALF * xoptsq).add(interpolationPoints.operate(trustRegionCenter));
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75494, _mut75495, _mut75496, _mut75497, _mut75498); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                sumpq += modelSecondDerivativesParameters.getEntry(k);
                                double sum = AOR_multiply(-HALF, xoptsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75431, _mut75432, _mut75433, _mut75434);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75439, _mut75440, _mut75441, _mut75442, _mut75443); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    sum += AOR_multiply(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75435, _mut75436, _mut75437, _mut75438);
                                }
                                // sum = sumVector.getEntry(k); // XXX "testAckley" and "testDiffPow" fail.
                                work2.setEntry(k, sum);
                                final double temp = AOR_minus(fracsq, AOR_multiply(HALF, sum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75444, _mut75445, _mut75446, _mut75447), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75448, _mut75449, _mut75450, _mut75451);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75489, _mut75490, _mut75491, _mut75492, _mut75493); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    work1.setEntry(i, bMatrix.getEntry(k, i));
                                    lagrangeValuesAtNewPoint.setEntry(i, AOR_plus(AOR_multiply(sum, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75452, _mut75453, _mut75454, _mut75455), AOR_multiply(temp, trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75456, _mut75457, _mut75458, _mut75459), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75460, _mut75461, _mut75462, _mut75463));
                                    final int ip = AOR_plus(npt, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75464, _mut75465, _mut75466, _mut75467);
                                    for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75484, _mut75485, _mut75486, _mut75487, _mut75488); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        bMatrix.setEntry(ip, j, AOR_plus(AOR_plus(bMatrix.getEntry(ip, j), AOR_multiply(work1.getEntry(i), lagrangeValuesAtNewPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75468, _mut75469, _mut75470, _mut75471), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75472, _mut75473, _mut75474, _mut75475), AOR_multiply(lagrangeValuesAtNewPoint.getEntry(i), work1.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75476, _mut75477, _mut75478, _mut75479), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75480, _mut75481, _mut75482, _mut75483));
                                    }
                                }
                            }
                            for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75573, _mut75574, _mut75575, _mut75576, _mut75577); m++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double sumz = ZERO;
                                double sumw = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75503, _mut75504, _mut75505, _mut75506, _mut75507); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    sumz += zMatrix.getEntry(k, m);
                                    lagrangeValuesAtNewPoint.setEntry(k, AOR_multiply(work2.getEntry(k), zMatrix.getEntry(k, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75499, _mut75500, _mut75501, _mut75502));
                                    sumw += lagrangeValuesAtNewPoint.getEntry(k);
                                }
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75546, _mut75547, _mut75548, _mut75549, _mut75550); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    double sum = AOR_multiply((AOR_minus(AOR_multiply(fracsq, sumz, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75508, _mut75509, _mut75510, _mut75511), AOR_multiply(HALF, sumw, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75512, _mut75513, _mut75514, _mut75515), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75516, _mut75517, _mut75518, _mut75519)), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75520, _mut75521, _mut75522, _mut75523);
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75528, _mut75529, _mut75530, _mut75531, _mut75532); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        sum += AOR_multiply(lagrangeValuesAtNewPoint.getEntry(k), interpolationPoints.getEntry(k, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75524, _mut75525, _mut75526, _mut75527);
                                    }
                                    work1.setEntry(j, sum);
                                    for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75541, _mut75542, _mut75543, _mut75544, _mut75545); k++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        bMatrix.setEntry(k, j, AOR_plus(bMatrix.getEntry(k, j), AOR_multiply(sum, zMatrix.getEntry(k, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75533, _mut75534, _mut75535, _mut75536), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75537, _mut75538, _mut75539, _mut75540));
                                    }
                                }
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75568, _mut75569, _mut75570, _mut75571, _mut75572); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    final int ip = AOR_plus(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75551, _mut75552, _mut75553, _mut75554);
                                    final double temp = work1.getEntry(i);
                                    for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75563, _mut75564, _mut75565, _mut75566, _mut75567); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        bMatrix.setEntry(ip, j, AOR_plus(bMatrix.getEntry(ip, j), AOR_multiply(temp, work1.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75555, _mut75556, _mut75557, _mut75558), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75559, _mut75560, _mut75561, _mut75562));
                                    }
                                }
                            }
                            int ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75632, _mut75633, _mut75634, _mut75635, _mut75636); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                work1.setEntry(j, AOR_multiply(AOR_multiply(-HALF, sumpq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75578, _mut75579, _mut75580, _mut75581), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75582, _mut75583, _mut75584, _mut75585));
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75598, _mut75599, _mut75600, _mut75601, _mut75602); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    work1.setEntry(j, AOR_plus(work1.getEntry(j), AOR_multiply(modelSecondDerivativesParameters.getEntry(k), interpolationPoints.getEntry(k, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75586, _mut75587, _mut75588, _mut75589), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75590, _mut75591, _mut75592, _mut75593));
                                    interpolationPoints.setEntry(k, j, AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75594, _mut75595, _mut75596, _mut75597));
                                }
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75627, _mut75628, _mut75629, _mut75630, _mut75631); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    modelSecondDerivativesValues.setEntry(ih, AOR_plus(AOR_plus(modelSecondDerivativesValues.getEntry(ih), AOR_multiply(work1.getEntry(i), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75603, _mut75604, _mut75605, _mut75606), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75607, _mut75608, _mut75609, _mut75610), AOR_multiply(trustRegionCenterOffset.getEntry(i), work1.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75611, _mut75612, _mut75613, _mut75614), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75615, _mut75616, _mut75617, _mut75618));
                                    bMatrix.setEntry(AOR_plus(npt, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75619, _mut75620, _mut75621, _mut75622), j, bMatrix.getEntry(AOR_plus(npt, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75623, _mut75624, _mut75625, _mut75626), i));
                                    ih++;
                                }
                            }
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75653, _mut75654, _mut75655, _mut75656, _mut75657); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                originShift.setEntry(i, AOR_plus(originShift.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75637, _mut75638, _mut75639, _mut75640));
                                newPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75641, _mut75642, _mut75643, _mut75644));
                                lowerDifference.setEntry(i, AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75645, _mut75646, _mut75647, _mut75648));
                                upperDifference.setEntry(i, AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75649, _mut75650, _mut75651, _mut75652));
                                trustRegionCenterOffset.setEntry(i, ZERO);
                            }
                            xoptsq = ZERO;
                        }
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75658, _mut75659, _mut75660, _mut75661, _mut75662)) {
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
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75667, _mut75668, _mut75669, _mut75670, _mut75671); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75663, _mut75664, _mut75665, _mut75666));
                        }
                    }
                case 230:
                    {
                        // XXX
                        printState(230);
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75701, _mut75702, _mut75703, _mut75704, _mut75705); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            double suma = ZERO;
                            double sumb = ZERO;
                            double sum = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75684, _mut75685, _mut75686, _mut75687, _mut75688); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                suma += AOR_multiply(interpolationPoints.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75672, _mut75673, _mut75674, _mut75675);
                                sumb += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75676, _mut75677, _mut75678, _mut75679);
                                sum += AOR_multiply(bMatrix.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75680, _mut75681, _mut75682, _mut75683);
                            }
                            work3.setEntry(k, AOR_multiply(suma, (AOR_plus(AOR_multiply(HALF, suma, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75689, _mut75690, _mut75691, _mut75692), sumb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75693, _mut75694, _mut75695, _mut75696)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75697, _mut75698, _mut75699, _mut75700));
                            lagrangeValuesAtNewPoint.setEntry(k, sum);
                            work2.setEntry(k, suma);
                        }
                        beta = ZERO;
                        for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75732, _mut75733, _mut75734, _mut75735, _mut75736); m++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            double sum = ZERO;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75710, _mut75711, _mut75712, _mut75713, _mut75714); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                sum += AOR_multiply(zMatrix.getEntry(k, m), work3.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75706, _mut75707, _mut75708, _mut75709);
                            }
                            beta -= AOR_multiply(sum, sum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75715, _mut75716, _mut75717, _mut75718);
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75727, _mut75728, _mut75729, _mut75730, _mut75731); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                lagrangeValuesAtNewPoint.setEntry(k, AOR_plus(lagrangeValuesAtNewPoint.getEntry(k), AOR_multiply(sum, zMatrix.getEntry(k, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75719, _mut75720, _mut75721, _mut75722), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75723, _mut75724, _mut75725, _mut75726));
                            }
                        }
                        dsq = ZERO;
                        double bsum = ZERO;
                        double dx = ZERO;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75775, _mut75776, _mut75777, _mut75778, _mut75779); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(j);
                            dsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75737, _mut75738, _mut75739, _mut75740);
                            double sum = ZERO;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75745, _mut75746, _mut75747, _mut75748, _mut75749); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                sum += AOR_multiply(work3.getEntry(k), bMatrix.getEntry(k, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75741, _mut75742, _mut75743, _mut75744);
                            }
                            bsum += AOR_multiply(sum, trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75750, _mut75751, _mut75752, _mut75753);
                            final int jp = AOR_plus(npt, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75754, _mut75755, _mut75756, _mut75757);
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75762, _mut75763, _mut75764, _mut75765, _mut75766); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                sum += AOR_multiply(bMatrix.getEntry(jp, i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75758, _mut75759, _mut75760, _mut75761);
                            }
                            lagrangeValuesAtNewPoint.setEntry(jp, sum);
                            bsum += AOR_multiply(sum, trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75767, _mut75768, _mut75769, _mut75770);
                            dx += AOR_multiply(trialStepPoint.getEntry(j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75771, _mut75772, _mut75773, _mut75774);
                        }
                        // Original
                        beta = AOR_minus(AOR_plus(AOR_plus(AOR_multiply(dx, dx, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75780, _mut75781, _mut75782, _mut75783), AOR_multiply(dsq, (AOR_plus(AOR_plus(AOR_plus(xoptsq, dx, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75784, _mut75785, _mut75786, _mut75787), dx, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75788, _mut75789, _mut75790, _mut75791), AOR_multiply(HALF, dsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75792, _mut75793, _mut75794, _mut75795), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75796, _mut75797, _mut75798, _mut75799)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75800, _mut75801, _mut75802, _mut75803), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75804, _mut75805, _mut75806, _mut75807), beta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75808, _mut75809, _mut75810, _mut75811), bsum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75812, _mut75813, _mut75814, _mut75815);
                        lagrangeValuesAtNewPoint.setEntry(trustRegionCenterInterpolationPointIndex, AOR_plus(lagrangeValuesAtNewPoint.getEntry(trustRegionCenterInterpolationPointIndex), ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75816, _mut75817, _mut75818, _mut75819));
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75820, _mut75821, _mut75822, _mut75823, _mut75824)) {
                            // Computing 2nd power
                            final double d1 = lagrangeValuesAtNewPoint.getEntry(knew);
                            denom = AOR_plus(AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75902, _mut75903, _mut75904, _mut75905), AOR_multiply(alpha, beta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75906, _mut75907, _mut75908, _mut75909), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75910, _mut75911, _mut75912, _mut75913);
                            if ((_mut75924 ? (ROR_less(denom, cauchy, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75914, _mut75915, _mut75916, _mut75917, _mut75918) || ROR_greater(cauchy, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75919, _mut75920, _mut75921, _mut75922, _mut75923)) : (ROR_less(denom, cauchy, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75914, _mut75915, _mut75916, _mut75917, _mut75918) && ROR_greater(cauchy, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75919, _mut75920, _mut75921, _mut75922, _mut75923)))) {
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75929, _mut75930, _mut75931, _mut75932, _mut75933); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    newPoint.setEntry(i, alternativeNewPoint.getEntry(i));
                                    trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75925, _mut75926, _mut75927, _mut75928));
                                }
                                // XXX Useful statement?
                                cauchy = ZERO;
                                state = 230;
                                break;
                            }
                        } else {
                            final double delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75825, _mut75826, _mut75827, _mut75828);
                            scaden = ZERO;
                            biglsq = ZERO;
                            knew = 0;
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75897, _mut75898, _mut75899, _mut75900, _mut75901); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                if (ROR_equals(k, trustRegionCenterInterpolationPointIndex, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75829, _mut75830, _mut75831, _mut75832, _mut75833)) {
                                    continue;
                                }
                                double hdiag = ZERO;
                                for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75838, _mut75839, _mut75840, _mut75841, _mut75842); m++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    // Computing 2nd power
                                    final double d1 = zMatrix.getEntry(k, m);
                                    hdiag += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75834, _mut75835, _mut75836, _mut75837);
                                }
                                // Computing 2nd power
                                final double d2 = lagrangeValuesAtNewPoint.getEntry(k);
                                final double den = AOR_plus(AOR_multiply(beta, hdiag, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75843, _mut75844, _mut75845, _mut75846), AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75847, _mut75848, _mut75849, _mut75850), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75851, _mut75852, _mut75853, _mut75854);
                                distsq = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75863, _mut75864, _mut75865, _mut75866, _mut75867); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    // Computing 2nd power
                                    final double d3 = AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75855, _mut75856, _mut75857, _mut75858);
                                    distsq += AOR_multiply(d3, d3, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75859, _mut75860, _mut75861, _mut75862);
                                }
                                // Computing 2nd power
                                final double d4 = AOR_divide(distsq, delsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75868, _mut75869, _mut75870, _mut75871);
                                final double temp = FastMath.max(ONE, AOR_multiply(d4, d4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75872, _mut75873, _mut75874, _mut75875));
                                if (ROR_greater(AOR_multiply(temp, den, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75876, _mut75877, _mut75878, _mut75879), scaden, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75880, _mut75881, _mut75882, _mut75883, _mut75884)) {
                                    scaden = AOR_multiply(temp, den, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75885, _mut75886, _mut75887, _mut75888);
                                    knew = k;
                                    denom = den;
                                }
                                // Computing 2nd power
                                final double d5 = lagrangeValuesAtNewPoint.getEntry(k);
                                biglsq = FastMath.max(biglsq, AOR_multiply(temp, (AOR_multiply(d5, d5, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75889, _mut75890, _mut75891, _mut75892)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75893, _mut75894, _mut75895, _mut75896));
                            }
                        }
                    }
                case 360:
                    {
                        // XXX
                        printState(360);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75948, _mut75949, _mut75950, _mut75951, _mut75952); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            // Computing MAX
                            final double d3 = lowerBound[i];
                            final double d4 = AOR_plus(originShift.getEntry(i), newPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75934, _mut75935, _mut75936, _mut75937);
                            final double d1 = FastMath.max(d3, d4);
                            final double d2 = upperBound[i];
                            currentBest.setEntry(i, FastMath.min(d1, d2));
                            if (ROR_equals(newPoint.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75938, _mut75939, _mut75940, _mut75941, _mut75942)) {
                                currentBest.setEntry(i, lowerBound[i]);
                            }
                            if (ROR_equals(newPoint.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75943, _mut75944, _mut75945, _mut75946, _mut75947)) {
                                currentBest.setEntry(i, upperBound[i]);
                            }
                        }
                        f = computeObjectiveValue(currentBest.toArray());
                        if (!isMinimize) {
                            f = -f;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75953, _mut75954, _mut75955, _mut75956, _mut75957)) {
                            fsave = f;
                            state = 720;
                            break;
                        }
                        final double fopt = fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex);
                        double vquad = ZERO;
                        int ih = 0;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75980, _mut75981, _mut75982, _mut75983, _mut75984); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            vquad += AOR_multiply(trialStepPoint.getEntry(j), gradientAtTrustRegionCenter.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75958, _mut75959, _mut75960, _mut75961);
                            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75975, _mut75976, _mut75977, _mut75978, _mut75979); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double temp = AOR_multiply(trialStepPoint.getEntry(i), trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75962, _mut75963, _mut75964, _mut75965);
                                if (ROR_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75966, _mut75967, _mut75968, _mut75969, _mut75970)) {
                                    temp *= HALF;
                                }
                                vquad += AOR_multiply(modelSecondDerivativesValues.getEntry(ih), temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75971, _mut75972, _mut75973, _mut75974);
                                ih++;
                            }
                        }
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75997, _mut75998, _mut75999, _mut76000, _mut76001); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            // Computing 2nd power
                            final double d1 = work2.getEntry(k);
                            // "d1" must be squared first to prevent test failures.
                            final double d2 = AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75985, _mut75986, _mut75987, _mut75988);
                            vquad += AOR_multiply(AOR_multiply(HALF, modelSecondDerivativesParameters.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75989, _mut75990, _mut75991, _mut75992), d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut75993, _mut75994, _mut75995, _mut75996);
                        }
                        final double diff = AOR_minus(AOR_minus(f, fopt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76002, _mut76003, _mut76004, _mut76005), vquad, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76006, _mut76007, _mut76008, _mut76009);
                        diffc = diffb;
                        diffb = diffa;
                        diffa = FastMath.abs(diff);
                        if (ROR_greater(dnorm, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76010, _mut76011, _mut76012, _mut76013, _mut76014)) {
                            nfsav = getEvaluations();
                        }
                        if (ROR_greater(ntrits, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76015, _mut76016, _mut76017, _mut76018, _mut76019)) {
                            if (ROR_greater_equals(vquad, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76020, _mut76021, _mut76022, _mut76023, _mut76024)) {
                                throw new MathIllegalStateException(LocalizedFormats.TRUST_REGION_STEP_FAILED, vquad);
                            }
                            ratio = AOR_divide((AOR_minus(f, fopt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76025, _mut76026, _mut76027, _mut76028)), vquad, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76029, _mut76030, _mut76031, _mut76032);
                            final double hDelta = AOR_multiply(HALF, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76033, _mut76034, _mut76035, _mut76036);
                            if (ROR_less_equals(ratio, ONE_OVER_TEN, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76037, _mut76038, _mut76039, _mut76040, _mut76041)) {
                                // Computing MIN
                                delta = FastMath.min(hDelta, dnorm);
                            } else if (ROR_less_equals(ratio, .7, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76042, _mut76043, _mut76044, _mut76045, _mut76046)) {
                                // Computing MAX
                                delta = FastMath.max(hDelta, dnorm);
                            } else {
                                // Computing MAX
                                delta = FastMath.max(hDelta, AOR_multiply(2, dnorm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76047, _mut76048, _mut76049, _mut76050));
                            }
                            if (ROR_less_equals(delta, AOR_multiply(rho, 1.5, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76051, _mut76052, _mut76053, _mut76054), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76055, _mut76056, _mut76057, _mut76058, _mut76059)) {
                                delta = rho;
                            }
                            if (ROR_less(f, fopt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76060, _mut76061, _mut76062, _mut76063, _mut76064)) {
                                final int ksav = knew;
                                final double densav = denom;
                                final double delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76065, _mut76066, _mut76067, _mut76068);
                                scaden = ZERO;
                                biglsq = ZERO;
                                knew = 0;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76132, _mut76133, _mut76134, _mut76135, _mut76136); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    double hdiag = ZERO;
                                    for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76073, _mut76074, _mut76075, _mut76076, _mut76077); m++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        // Computing 2nd power
                                        final double d1 = zMatrix.getEntry(k, m);
                                        hdiag += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76069, _mut76070, _mut76071, _mut76072);
                                    }
                                    // Computing 2nd power
                                    final double d1 = lagrangeValuesAtNewPoint.getEntry(k);
                                    final double den = AOR_plus(AOR_multiply(beta, hdiag, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76078, _mut76079, _mut76080, _mut76081), AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76082, _mut76083, _mut76084, _mut76085), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76086, _mut76087, _mut76088, _mut76089);
                                    distsq = ZERO;
                                    for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76098, _mut76099, _mut76100, _mut76101, _mut76102); j++) {
                                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                        // Computing 2nd power
                                        final double d2 = AOR_minus(interpolationPoints.getEntry(k, j), newPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76090, _mut76091, _mut76092, _mut76093);
                                        distsq += AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76094, _mut76095, _mut76096, _mut76097);
                                    }
                                    // Computing 2nd power
                                    final double d3 = AOR_divide(distsq, delsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76103, _mut76104, _mut76105, _mut76106);
                                    final double temp = FastMath.max(ONE, AOR_multiply(d3, d3, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76107, _mut76108, _mut76109, _mut76110));
                                    if (ROR_greater(AOR_multiply(temp, den, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76111, _mut76112, _mut76113, _mut76114), scaden, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76115, _mut76116, _mut76117, _mut76118, _mut76119)) {
                                        scaden = AOR_multiply(temp, den, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76120, _mut76121, _mut76122, _mut76123);
                                        knew = k;
                                        denom = den;
                                    }
                                    // Computing 2nd power
                                    final double d4 = lagrangeValuesAtNewPoint.getEntry(k);
                                    final double d5 = AOR_multiply(temp, (AOR_multiply(d4, d4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76124, _mut76125, _mut76126, _mut76127)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76128, _mut76129, _mut76130, _mut76131);
                                    biglsq = FastMath.max(biglsq, d5);
                                }
                                if (ROR_less_equals(scaden, AOR_multiply(HALF, biglsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76137, _mut76138, _mut76139, _mut76140), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76141, _mut76142, _mut76143, _mut76144, _mut76145)) {
                                    knew = ksav;
                                    denom = densav;
                                }
                            }
                        }
                        update(beta, denom, knew);
                        ih = 0;
                        final double pqold = modelSecondDerivativesParameters.getEntry(knew);
                        modelSecondDerivativesParameters.setEntry(knew, ZERO);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76163, _mut76164, _mut76165, _mut76166, _mut76167); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            final double temp = AOR_multiply(pqold, interpolationPoints.getEntry(knew, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76146, _mut76147, _mut76148, _mut76149);
                            for (int j = 0; ROR_less_equals(j, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76158, _mut76159, _mut76160, _mut76161, _mut76162); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                modelSecondDerivativesValues.setEntry(ih, AOR_plus(modelSecondDerivativesValues.getEntry(ih), AOR_multiply(temp, interpolationPoints.getEntry(knew, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76150, _mut76151, _mut76152, _mut76153), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76154, _mut76155, _mut76156, _mut76157));
                                ih++;
                            }
                        }
                        for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76185, _mut76186, _mut76187, _mut76188, _mut76189); m++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            final double temp = AOR_multiply(diff, zMatrix.getEntry(knew, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76168, _mut76169, _mut76170, _mut76171);
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76180, _mut76181, _mut76182, _mut76183, _mut76184); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                modelSecondDerivativesParameters.setEntry(k, AOR_plus(modelSecondDerivativesParameters.getEntry(k), AOR_multiply(temp, zMatrix.getEntry(k, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76172, _mut76173, _mut76174, _mut76175), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76176, _mut76177, _mut76178, _mut76179));
                            }
                        }
                        fAtInterpolationPoints.setEntry(knew, f);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76190, _mut76191, _mut76192, _mut76193, _mut76194); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            interpolationPoints.setEntry(knew, i, newPoint.getEntry(i));
                            work1.setEntry(i, bMatrix.getEntry(knew, i));
                        }
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76230, _mut76231, _mut76232, _mut76233, _mut76234); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            double suma = ZERO;
                            for (int m = 0; ROR_less(m, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76199, _mut76200, _mut76201, _mut76202, _mut76203); m++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                suma += AOR_multiply(zMatrix.getEntry(knew, m), zMatrix.getEntry(k, m), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76195, _mut76196, _mut76197, _mut76198);
                            }
                            double sumb = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76208, _mut76209, _mut76210, _mut76211, _mut76212); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                sumb += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76204, _mut76205, _mut76206, _mut76207);
                            }
                            final double temp = AOR_multiply(suma, sumb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76213, _mut76214, _mut76215, _mut76216);
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76225, _mut76226, _mut76227, _mut76228, _mut76229); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                work1.setEntry(i, AOR_plus(work1.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76217, _mut76218, _mut76219, _mut76220), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76221, _mut76222, _mut76223, _mut76224));
                            }
                        }
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76243, _mut76244, _mut76245, _mut76246, _mut76247); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(diff, work1.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76235, _mut76236, _mut76237, _mut76238), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76239, _mut76240, _mut76241, _mut76242));
                        }
                        if (ROR_less(f, fopt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76248, _mut76249, _mut76250, _mut76251, _mut76252)) {
                            trustRegionCenterInterpolationPointIndex = knew;
                            xoptsq = ZERO;
                            ih = 0;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76283, _mut76284, _mut76285, _mut76286, _mut76287); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                trustRegionCenterOffset.setEntry(j, newPoint.getEntry(j));
                                // Computing 2nd power
                                final double d1 = trustRegionCenterOffset.getEntry(j);
                                xoptsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76253, _mut76254, _mut76255, _mut76256);
                                for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76278, _mut76279, _mut76280, _mut76281, _mut76282); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    if (ROR_less(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76257, _mut76258, _mut76259, _mut76260, _mut76261)) {
                                        gradientAtTrustRegionCenter.setEntry(j, AOR_plus(gradientAtTrustRegionCenter.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76262, _mut76263, _mut76264, _mut76265), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76266, _mut76267, _mut76268, _mut76269));
                                    }
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76270, _mut76271, _mut76272, _mut76273), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76274, _mut76275, _mut76276, _mut76277));
                                    ih++;
                                }
                            }
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76310, _mut76311, _mut76312, _mut76313, _mut76314); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double temp = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76292, _mut76293, _mut76294, _mut76295, _mut76296); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    temp += AOR_multiply(interpolationPoints.getEntry(k, j), trialStepPoint.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76288, _mut76289, _mut76290, _mut76291);
                                }
                                temp *= modelSecondDerivativesParameters.getEntry(k);
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76305, _mut76306, _mut76307, _mut76308, _mut76309); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    gradientAtTrustRegionCenter.setEntry(i, AOR_plus(gradientAtTrustRegionCenter.getEntry(i), AOR_multiply(temp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76297, _mut76298, _mut76299, _mut76300), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76301, _mut76302, _mut76303, _mut76304));
                                }
                            }
                        }
                        if (ROR_greater(ntrits, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76315, _mut76316, _mut76317, _mut76318, _mut76319)) {
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76324, _mut76325, _mut76326, _mut76327, _mut76328); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                lagrangeValuesAtNewPoint.setEntry(k, AOR_minus(fAtInterpolationPoints.getEntry(k), fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76320, _mut76321, _mut76322, _mut76323));
                                work3.setEntry(k, ZERO);
                            }
                            for (int j = 0; ROR_less(j, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76351, _mut76352, _mut76353, _mut76354, _mut76355); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double sum = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76333, _mut76334, _mut76335, _mut76336, _mut76337); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    sum += AOR_multiply(zMatrix.getEntry(k, j), lagrangeValuesAtNewPoint.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76329, _mut76330, _mut76331, _mut76332);
                                }
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76346, _mut76347, _mut76348, _mut76349, _mut76350); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    work3.setEntry(k, AOR_plus(work3.getEntry(k), AOR_multiply(sum, zMatrix.getEntry(k, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76338, _mut76339, _mut76340, _mut76341), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76342, _mut76343, _mut76344, _mut76345));
                                }
                            }
                            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76369, _mut76370, _mut76371, _mut76372, _mut76373); k++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double sum = ZERO;
                                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76360, _mut76361, _mut76362, _mut76363, _mut76364); j++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    sum += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76356, _mut76357, _mut76358, _mut76359);
                                }
                                work2.setEntry(k, work3.getEntry(k));
                                work3.setEntry(k, AOR_multiply(sum, work3.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76365, _mut76366, _mut76367, _mut76368));
                            }
                            double gqsq = ZERO;
                            double gisq = ZERO;
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76429, _mut76430, _mut76431, _mut76432, _mut76433); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                double sum = ZERO;
                                for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76386, _mut76387, _mut76388, _mut76389, _mut76390); k++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    sum += AOR_plus(AOR_multiply(bMatrix.getEntry(k, i), lagrangeValuesAtNewPoint.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76374, _mut76375, _mut76376, _mut76377), AOR_multiply(interpolationPoints.getEntry(k, i), work3.getEntry(k), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76378, _mut76379, _mut76380, _mut76381), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76382, _mut76383, _mut76384, _mut76385);
                                }
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76391, _mut76392, _mut76393, _mut76394, _mut76395)) {
                                    // Computing 2nd power
                                    final double d1 = FastMath.min(ZERO, gradientAtTrustRegionCenter.getEntry(i));
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76417, _mut76418, _mut76419, _mut76420);
                                    // Computing 2nd power
                                    final double d2 = FastMath.min(ZERO, sum);
                                    gisq += AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76421, _mut76422, _mut76423, _mut76424);
                                } else if (ROR_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76396, _mut76397, _mut76398, _mut76399, _mut76400)) {
                                    // Computing 2nd power
                                    final double d1 = FastMath.max(ZERO, gradientAtTrustRegionCenter.getEntry(i));
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76409, _mut76410, _mut76411, _mut76412);
                                    // Computing 2nd power
                                    final double d2 = FastMath.max(ZERO, sum);
                                    gisq += AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76413, _mut76414, _mut76415, _mut76416);
                                } else {
                                    // Computing 2nd power
                                    final double d1 = gradientAtTrustRegionCenter.getEntry(i);
                                    gqsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76401, _mut76402, _mut76403, _mut76404);
                                    gisq += AOR_multiply(sum, sum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76405, _mut76406, _mut76407, _mut76408);
                                }
                                lagrangeValuesAtNewPoint.setEntry(AOR_plus(npt, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76425, _mut76426, _mut76427, _mut76428), sum);
                            }
                            ++itest;
                            if (ROR_less(gqsq, AOR_multiply(TEN, gisq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76434, _mut76435, _mut76436, _mut76437), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76438, _mut76439, _mut76440, _mut76441, _mut76442)) {
                                itest = 0;
                            }
                            if (ROR_greater_equals(itest, 3, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76443, _mut76444, _mut76445, _mut76446, _mut76447)) {
                                for (int i = 0, max = FastMath.max(npt, nh); ROR_less(i, max, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76467, _mut76468, _mut76469, _mut76470, _mut76471); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                    if (ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76448, _mut76449, _mut76450, _mut76451, _mut76452)) {
                                        gradientAtTrustRegionCenter.setEntry(i, lagrangeValuesAtNewPoint.getEntry(AOR_plus(npt, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76453, _mut76454, _mut76455, _mut76456)));
                                    }
                                    if (ROR_less(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76457, _mut76458, _mut76459, _mut76460, _mut76461)) {
                                        modelSecondDerivativesParameters.setEntry(i, work2.getEntry(i));
                                    }
                                    if (ROR_less(i, nh, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76462, _mut76463, _mut76464, _mut76465, _mut76466)) {
                                        modelSecondDerivativesValues.setEntry(i, ZERO);
                                    }
                                    itest = 0;
                                }
                            }
                        }
                        if (ROR_equals(ntrits, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76472, _mut76473, _mut76474, _mut76475, _mut76476)) {
                            state = 60;
                            break;
                        }
                        if (ROR_less_equals(f, AOR_plus(fopt, AOR_multiply(ONE_OVER_TEN, vquad, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76477, _mut76478, _mut76479, _mut76480), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76481, _mut76482, _mut76483, _mut76484), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76485, _mut76486, _mut76487, _mut76488, _mut76489)) {
                            state = 60;
                            break;
                        }
                        // Computing 2nd power
                        final double d1 = AOR_multiply(TWO, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76490, _mut76491, _mut76492, _mut76493);
                        // Computing 2nd power
                        final double d2 = AOR_multiply(TEN, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76494, _mut76495, _mut76496, _mut76497);
                        distsq = FastMath.max(AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76498, _mut76499, _mut76500, _mut76501), AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76502, _mut76503, _mut76504, _mut76505));
                    }
                case 650:
                    {
                        // XXX
                        printState(650);
                        knew = -1;
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76524, _mut76525, _mut76526, _mut76527, _mut76528); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                            double sum = ZERO;
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76514, _mut76515, _mut76516, _mut76517, _mut76518); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                // Computing 2nd power
                                final double d1 = AOR_minus(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76506, _mut76507, _mut76508, _mut76509);
                                sum += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76510, _mut76511, _mut76512, _mut76513);
                            }
                            if (ROR_greater(sum, distsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76519, _mut76520, _mut76521, _mut76522, _mut76523)) {
                                knew = k;
                                distsq = sum;
                            }
                        }
                        if (ROR_greater_equals(knew, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76529, _mut76530, _mut76531, _mut76532, _mut76533)) {
                            final double dist = FastMath.sqrt(distsq);
                            if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76534, _mut76535, _mut76536, _mut76537, _mut76538)) {
                                // Computing MIN
                                delta = FastMath.min(AOR_multiply(ONE_OVER_TEN, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76539, _mut76540, _mut76541, _mut76542), AOR_multiply(HALF, dist, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76543, _mut76544, _mut76545, _mut76546));
                                if (ROR_less_equals(delta, AOR_multiply(rho, 1.5, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76547, _mut76548, _mut76549, _mut76550), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76551, _mut76552, _mut76553, _mut76554, _mut76555)) {
                                    delta = rho;
                                }
                            }
                            ntrits = 0;
                            // Computing MIN
                            final double d1 = FastMath.min(AOR_multiply(ONE_OVER_TEN, dist, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76556, _mut76557, _mut76558, _mut76559), delta);
                            adelt = FastMath.max(d1, rho);
                            dsq = AOR_multiply(adelt, adelt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76560, _mut76561, _mut76562, _mut76563);
                            state = 90;
                            break;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76564, _mut76565, _mut76566, _mut76567, _mut76568)) {
                            state = 680;
                            break;
                        }
                        if (ROR_greater(ratio, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76569, _mut76570, _mut76571, _mut76572, _mut76573)) {
                            state = 60;
                            break;
                        }
                        if (ROR_greater(FastMath.max(delta, dnorm), rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76574, _mut76575, _mut76576, _mut76577, _mut76578)) {
                            state = 60;
                            break;
                        }
                    }
                case 680:
                    {
                        // XXX
                        printState(680);
                        if (ROR_greater(rho, stoppingTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76579, _mut76580, _mut76581, _mut76582, _mut76583)) {
                            delta = AOR_multiply(HALF, rho, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76584, _mut76585, _mut76586, _mut76587);
                            ratio = AOR_divide(rho, stoppingTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76588, _mut76589, _mut76590, _mut76591);
                            if (ROR_less_equals(ratio, SIXTEEN, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76592, _mut76593, _mut76594, _mut76595, _mut76596)) {
                                rho = stoppingTrustRegionRadius;
                            } else if (ROR_less_equals(ratio, TWO_HUNDRED_FIFTY, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76597, _mut76598, _mut76599, _mut76600, _mut76601)) {
                                rho = AOR_multiply(FastMath.sqrt(ratio), stoppingTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76602, _mut76603, _mut76604, _mut76605);
                            } else {
                                rho *= ONE_OVER_TEN;
                            }
                            delta = FastMath.max(delta, rho);
                            ntrits = 0;
                            nfsav = getEvaluations();
                            state = 60;
                            break;
                        }
                        if (ROR_equals(ntrits, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76606, _mut76607, _mut76608, _mut76609, _mut76610)) {
                            state = 360;
                            break;
                        }
                    }
                case 720:
                    {
                        // XXX
                        printState(720);
                        if (ROR_less_equals(fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), fsave, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76611, _mut76612, _mut76613, _mut76614, _mut76615)) {
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76630, _mut76631, _mut76632, _mut76633, _mut76634); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387");
                                // Computing MAX
                                final double d3 = lowerBound[i];
                                final double d4 = AOR_plus(originShift.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76616, _mut76617, _mut76618, _mut76619);
                                final double d1 = FastMath.max(d3, d4);
                                final double d2 = upperBound[i];
                                currentBest.setEntry(i, FastMath.min(d1, d2));
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76620, _mut76621, _mut76622, _mut76623, _mut76624)) {
                                    currentBest.setEntry(i, lowerBound[i]);
                                }
                                if (ROR_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb_387", _mut76625, _mut76626, _mut76627, _mut76628, _mut76629)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final ArrayRealVector glag = new ArrayRealVector(n);
        final ArrayRealVector hcol = new ArrayRealVector(npt);
        final ArrayRealVector work1 = new ArrayRealVector(n);
        final ArrayRealVector work2 = new ArrayRealVector(n);
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76635, _mut76636, _mut76637, _mut76638, _mut76639); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            hcol.setEntry(k, ZERO);
        }
        for (int j = 0, max = npt - n - 1; ROR_less(j, max, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76653, _mut76654, _mut76655, _mut76656, _mut76657); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            final double tmp = zMatrix.getEntry(knew, j);
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76648, _mut76649, _mut76650, _mut76651, _mut76652); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                hcol.setEntry(k, AOR_plus(hcol.getEntry(k), AOR_multiply(tmp, zMatrix.getEntry(k, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76640, _mut76641, _mut76642, _mut76643), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76644, _mut76645, _mut76646, _mut76647));
            }
        }
        final double alpha = hcol.getEntry(knew);
        final double ha = AOR_multiply(HALF, alpha, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76658, _mut76659, _mut76660, _mut76661);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76662, _mut76663, _mut76664, _mut76665, _mut76666); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            glag.setEntry(i, bMatrix.getEntry(knew, i));
        }
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76689, _mut76690, _mut76691, _mut76692, _mut76693); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            double tmp = ZERO;
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76671, _mut76672, _mut76673, _mut76674, _mut76675); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                tmp += AOR_multiply(interpolationPoints.getEntry(k, j), trustRegionCenterOffset.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76667, _mut76668, _mut76669, _mut76670);
            }
            tmp *= hcol.getEntry(k);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76684, _mut76685, _mut76686, _mut76687, _mut76688); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                glag.setEntry(i, AOR_plus(glag.getEntry(i), AOR_multiply(tmp, interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76676, _mut76677, _mut76678, _mut76679), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76680, _mut76681, _mut76682, _mut76683));
            }
        }
        double presav = ZERO;
        double step = Double.NaN;
        int ksav = 0;
        int ibdsav = 0;
        double stpsav = 0;
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76996, _mut76997, _mut76998, _mut76999, _mut77000); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            if (ROR_equals(k, trustRegionCenterInterpolationPointIndex, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76694, _mut76695, _mut76696, _mut76697, _mut76698)) {
                continue;
            }
            double dderiv = ZERO;
            double distsq = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76711, _mut76712, _mut76713, _mut76714, _mut76715); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                final double tmp = AOR_minus(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76699, _mut76700, _mut76701, _mut76702);
                dderiv += AOR_multiply(glag.getEntry(i), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76703, _mut76704, _mut76705, _mut76706);
                distsq += AOR_multiply(tmp, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76707, _mut76708, _mut76709, _mut76710);
            }
            double subd = AOR_divide(adelt, FastMath.sqrt(distsq), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76716, _mut76717, _mut76718, _mut76719);
            double slbd = -subd;
            int ilbd = 0;
            int iubd = 0;
            final double sumin = FastMath.min(ONE, subd);
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76834, _mut76835, _mut76836, _mut76837, _mut76838); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                final double tmp = AOR_minus(interpolationPoints.getEntry(k, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76720, _mut76721, _mut76722, _mut76723);
                if (ROR_greater(tmp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76724, _mut76725, _mut76726, _mut76727, _mut76728)) {
                    if (ROR_less(AOR_multiply(slbd, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76784, _mut76785, _mut76786, _mut76787), AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76788, _mut76789, _mut76790, _mut76791), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76792, _mut76793, _mut76794, _mut76795, _mut76796)) {
                        slbd = AOR_divide((AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76797, _mut76798, _mut76799, _mut76800)), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76801, _mut76802, _mut76803, _mut76804);
                        ilbd = AOR_minus(-i, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76805, _mut76806, _mut76807, _mut76808);
                    }
                    if (ROR_greater(AOR_multiply(subd, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76809, _mut76810, _mut76811, _mut76812), AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76813, _mut76814, _mut76815, _mut76816), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76817, _mut76818, _mut76819, _mut76820, _mut76821)) {
                        // Computing MAX
                        subd = FastMath.max(sumin, AOR_divide((AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76822, _mut76823, _mut76824, _mut76825)), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76826, _mut76827, _mut76828, _mut76829));
                        iubd = AOR_plus(i, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76830, _mut76831, _mut76832, _mut76833);
                    }
                } else if (ROR_less(tmp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76729, _mut76730, _mut76731, _mut76732, _mut76733)) {
                    if (ROR_greater(AOR_multiply(slbd, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76734, _mut76735, _mut76736, _mut76737), AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76738, _mut76739, _mut76740, _mut76741), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76742, _mut76743, _mut76744, _mut76745, _mut76746)) {
                        slbd = AOR_divide((AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76747, _mut76748, _mut76749, _mut76750)), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76751, _mut76752, _mut76753, _mut76754);
                        ilbd = AOR_plus(i, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76755, _mut76756, _mut76757, _mut76758);
                    }
                    if (ROR_less(AOR_multiply(subd, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76759, _mut76760, _mut76761, _mut76762), AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76763, _mut76764, _mut76765, _mut76766), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76767, _mut76768, _mut76769, _mut76770, _mut76771)) {
                        // Computing MAX
                        subd = FastMath.max(sumin, AOR_divide((AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76772, _mut76773, _mut76774, _mut76775)), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76776, _mut76777, _mut76778, _mut76779));
                        iubd = AOR_minus(-i, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76780, _mut76781, _mut76782, _mut76783);
                    }
                }
            }
            step = slbd;
            int isbd = ilbd;
            double vlag = Double.NaN;
            if (ROR_equals(k, knew, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76839, _mut76840, _mut76841, _mut76842, _mut76843)) {
                final double diff = AOR_minus(dderiv, ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76876, _mut76877, _mut76878, _mut76879);
                vlag = AOR_multiply(slbd, (AOR_minus(dderiv, AOR_multiply(slbd, diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76880, _mut76881, _mut76882, _mut76883), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76884, _mut76885, _mut76886, _mut76887)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76888, _mut76889, _mut76890, _mut76891);
                final double d1 = AOR_multiply(subd, (AOR_minus(dderiv, AOR_multiply(subd, diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76892, _mut76893, _mut76894, _mut76895), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76896, _mut76897, _mut76898, _mut76899)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76900, _mut76901, _mut76902, _mut76903);
                if (ROR_greater(FastMath.abs(d1), FastMath.abs(vlag), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76904, _mut76905, _mut76906, _mut76907, _mut76908)) {
                    step = subd;
                    vlag = d1;
                    isbd = iubd;
                }
                final double d2 = AOR_multiply(HALF, dderiv, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76909, _mut76910, _mut76911, _mut76912);
                final double d3 = AOR_minus(d2, AOR_multiply(diff, slbd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76913, _mut76914, _mut76915, _mut76916), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76917, _mut76918, _mut76919, _mut76920);
                final double d4 = AOR_minus(d2, AOR_multiply(diff, subd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76921, _mut76922, _mut76923, _mut76924), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76925, _mut76926, _mut76927, _mut76928);
                if (ROR_less(AOR_multiply(d3, d4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76929, _mut76930, _mut76931, _mut76932), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76933, _mut76934, _mut76935, _mut76936, _mut76937)) {
                    final double d5 = AOR_divide(AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76938, _mut76939, _mut76940, _mut76941), diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76942, _mut76943, _mut76944, _mut76945);
                    if (ROR_greater(FastMath.abs(d5), FastMath.abs(vlag), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76946, _mut76947, _mut76948, _mut76949, _mut76950)) {
                        step = AOR_divide(d2, diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76951, _mut76952, _mut76953, _mut76954);
                        vlag = d5;
                        isbd = 0;
                    }
                }
            } else {
                vlag = AOR_multiply(slbd, (AOR_minus(ONE, slbd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76844, _mut76845, _mut76846, _mut76847)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76848, _mut76849, _mut76850, _mut76851);
                final double tmp = AOR_multiply(subd, (AOR_minus(ONE, subd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76852, _mut76853, _mut76854, _mut76855)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76856, _mut76857, _mut76858, _mut76859);
                if (ROR_greater(FastMath.abs(tmp), FastMath.abs(vlag), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76860, _mut76861, _mut76862, _mut76863, _mut76864)) {
                    step = subd;
                    vlag = tmp;
                    isbd = iubd;
                }
                if ((_mut76875 ? (ROR_greater(subd, HALF, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76865, _mut76866, _mut76867, _mut76868, _mut76869) || ROR_less(FastMath.abs(vlag), ONE_OVER_FOUR, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76870, _mut76871, _mut76872, _mut76873, _mut76874)) : (ROR_greater(subd, HALF, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76865, _mut76866, _mut76867, _mut76868, _mut76869) && ROR_less(FastMath.abs(vlag), ONE_OVER_FOUR, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76870, _mut76871, _mut76872, _mut76873, _mut76874)))) {
                    step = HALF;
                    vlag = ONE_OVER_FOUR;
                    isbd = 0;
                }
                vlag *= dderiv;
            }
            final double tmp = AOR_multiply(AOR_multiply(step, (AOR_minus(ONE, step, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76955, _mut76956, _mut76957, _mut76958)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76959, _mut76960, _mut76961, _mut76962), distsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76963, _mut76964, _mut76965, _mut76966);
            final double predsq = AOR_multiply(AOR_multiply(vlag, vlag, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76967, _mut76968, _mut76969, _mut76970), (AOR_plus(AOR_multiply(vlag, vlag, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76971, _mut76972, _mut76973, _mut76974), AOR_multiply(AOR_multiply(ha, tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76975, _mut76976, _mut76977, _mut76978), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76979, _mut76980, _mut76981, _mut76982), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76983, _mut76984, _mut76985, _mut76986)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76987, _mut76988, _mut76989, _mut76990);
            if (ROR_greater(predsq, presav, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut76991, _mut76992, _mut76993, _mut76994, _mut76995)) {
                presav = predsq;
                ksav = k;
                stpsav = step;
                ibdsav = isbd;
            }
        }
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77013, _mut77014, _mut77015, _mut77016, _mut77017); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            final double tmp = AOR_plus(trustRegionCenterOffset.getEntry(i), AOR_multiply(stpsav, (AOR_minus(interpolationPoints.getEntry(ksav, i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77001, _mut77002, _mut77003, _mut77004)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77005, _mut77006, _mut77007, _mut77008), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77009, _mut77010, _mut77011, _mut77012);
            newPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), FastMath.min(upperDifference.getEntry(i), tmp)));
        }
        if (ROR_less(ibdsav, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77018, _mut77019, _mut77020, _mut77021, _mut77022)) {
            newPoint.setEntry(AOR_minus(-ibdsav, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77023, _mut77024, _mut77025, _mut77026), lowerDifference.getEntry(AOR_minus(-ibdsav, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77027, _mut77028, _mut77029, _mut77030)));
        }
        if (ROR_greater(ibdsav, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77031, _mut77032, _mut77033, _mut77034, _mut77035)) {
            newPoint.setEntry(AOR_minus(ibdsav, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77036, _mut77037, _mut77038, _mut77039), upperDifference.getEntry(AOR_minus(ibdsav, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77040, _mut77041, _mut77042, _mut77043)));
        }
        final double bigstp = AOR_plus(adelt, adelt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77044, _mut77045, _mut77046, _mut77047);
        int iflag = 0;
        double cauchy = Double.NaN;
        double csave = ZERO;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
            double wfixsq = ZERO;
            double ggfree = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77071, _mut77072, _mut77073, _mut77074, _mut77075); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                final double glagValue = glag.getEntry(i);
                work1.setEntry(i, ZERO);
                if ((_mut77066 ? (ROR_greater(FastMath.min(AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77048, _mut77049, _mut77050, _mut77051), glagValue), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77052, _mut77053, _mut77054, _mut77055, _mut77056) && ROR_less(FastMath.max(AOR_minus(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77057, _mut77058, _mut77059, _mut77060), glagValue), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77061, _mut77062, _mut77063, _mut77064, _mut77065)) : (ROR_greater(FastMath.min(AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77048, _mut77049, _mut77050, _mut77051), glagValue), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77052, _mut77053, _mut77054, _mut77055, _mut77056) || ROR_less(FastMath.max(AOR_minus(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77057, _mut77058, _mut77059, _mut77060), glagValue), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77061, _mut77062, _mut77063, _mut77064, _mut77065)))) {
                    work1.setEntry(i, bigstp);
                    // Computing 2nd power
                    ggfree += AOR_multiply(glagValue, glagValue, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77067, _mut77068, _mut77069, _mut77070);
                }
            }
            if (ROR_equals(ggfree, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77076, _mut77077, _mut77078, _mut77079, _mut77080)) {
                return new double[] { alpha, ZERO };
            }
            // Investigate whether more components of W can be fixed.
            final double tmp1 = AOR_minus(AOR_multiply(adelt, adelt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77081, _mut77082, _mut77083, _mut77084), wfixsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77085, _mut77086, _mut77087, _mut77088);
            if (ROR_greater(tmp1, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77089, _mut77090, _mut77091, _mut77092, _mut77093)) {
                step = FastMath.sqrt(AOR_divide(tmp1, ggfree, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77094, _mut77095, _mut77096, _mut77097));
                ggfree = ZERO;
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77141, _mut77142, _mut77143, _mut77144, _mut77145); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                    if (ROR_equals(work1.getEntry(i), bigstp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77098, _mut77099, _mut77100, _mut77101, _mut77102)) {
                        final double tmp2 = AOR_minus(trustRegionCenterOffset.getEntry(i), AOR_multiply(step, glag.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77103, _mut77104, _mut77105, _mut77106), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77107, _mut77108, _mut77109, _mut77110);
                        if (ROR_less_equals(tmp2, lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77111, _mut77112, _mut77113, _mut77114, _mut77115)) {
                            work1.setEntry(i, AOR_minus(lowerDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77133, _mut77134, _mut77135, _mut77136));
                            // Computing 2nd power
                            final double d1 = work1.getEntry(i);
                            wfixsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77137, _mut77138, _mut77139, _mut77140);
                        } else if (ROR_greater_equals(tmp2, upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77116, _mut77117, _mut77118, _mut77119, _mut77120)) {
                            work1.setEntry(i, AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77125, _mut77126, _mut77127, _mut77128));
                            // Computing 2nd power
                            final double d1 = work1.getEntry(i);
                            wfixsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77129, _mut77130, _mut77131, _mut77132);
                        } else {
                            // Computing 2nd power
                            final double d1 = glag.getEntry(i);
                            ggfree += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77121, _mut77122, _mut77123, _mut77124);
                        }
                    }
                }
            }
            double gw = ZERO;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77173, _mut77174, _mut77175, _mut77176, _mut77177); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                final double glagValue = glag.getEntry(i);
                if (ROR_equals(work1.getEntry(i), bigstp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77146, _mut77147, _mut77148, _mut77149, _mut77150)) {
                    work1.setEntry(i, AOR_multiply(-step, glagValue, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77161, _mut77162, _mut77163, _mut77164));
                    final double min = FastMath.min(upperDifference.getEntry(i), AOR_plus(trustRegionCenterOffset.getEntry(i), work1.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77165, _mut77166, _mut77167, _mut77168));
                    alternativeNewPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), min));
                } else if (ROR_equals(work1.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77151, _mut77152, _mut77153, _mut77154, _mut77155)) {
                    alternativeNewPoint.setEntry(i, trustRegionCenterOffset.getEntry(i));
                } else if (ROR_greater(glagValue, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77156, _mut77157, _mut77158, _mut77159, _mut77160)) {
                    alternativeNewPoint.setEntry(i, lowerDifference.getEntry(i));
                } else {
                    alternativeNewPoint.setEntry(i, upperDifference.getEntry(i));
                }
                gw += AOR_multiply(glagValue, work1.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77169, _mut77170, _mut77171, _mut77172);
            }
            double curv = ZERO;
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77195, _mut77196, _mut77197, _mut77198, _mut77199); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                double tmp = ZERO;
                for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77182, _mut77183, _mut77184, _mut77185, _mut77186); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                    tmp += AOR_multiply(interpolationPoints.getEntry(k, j), work1.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77178, _mut77179, _mut77180, _mut77181);
                }
                curv += AOR_multiply(AOR_multiply(hcol.getEntry(k), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77187, _mut77188, _mut77189, _mut77190), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77191, _mut77192, _mut77193, _mut77194);
            }
            if (ROR_equals(iflag, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77200, _mut77201, _mut77202, _mut77203, _mut77204)) {
                curv = -curv;
            }
            if ((_mut77223 ? (ROR_greater(curv, -gw, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77205, _mut77206, _mut77207, _mut77208, _mut77209) || ROR_less(curv, AOR_multiply(-gw, (AOR_plus(ONE, FastMath.sqrt(TWO), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77210, _mut77211, _mut77212, _mut77213)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77214, _mut77215, _mut77216, _mut77217), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77218, _mut77219, _mut77220, _mut77221, _mut77222)) : (ROR_greater(curv, -gw, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77205, _mut77206, _mut77207, _mut77208, _mut77209) && ROR_less(curv, AOR_multiply(-gw, (AOR_plus(ONE, FastMath.sqrt(TWO), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77210, _mut77211, _mut77212, _mut77213)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77214, _mut77215, _mut77216, _mut77217), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77218, _mut77219, _mut77220, _mut77221, _mut77222)))) {
                final double scale = AOR_divide(-gw, curv, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77236, _mut77237, _mut77238, _mut77239);
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77248, _mut77249, _mut77250, _mut77251, _mut77252); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                    final double tmp = AOR_plus(trustRegionCenterOffset.getEntry(i), AOR_multiply(scale, work1.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77240, _mut77241, _mut77242, _mut77243), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77244, _mut77245, _mut77246, _mut77247);
                    alternativeNewPoint.setEntry(i, FastMath.max(lowerDifference.getEntry(i), FastMath.min(upperDifference.getEntry(i), tmp)));
                }
                // Computing 2nd power
                final double d1 = AOR_multiply(AOR_multiply(HALF, gw, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77253, _mut77254, _mut77255, _mut77256), scale, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77257, _mut77258, _mut77259, _mut77260);
                cauchy = AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77261, _mut77262, _mut77263, _mut77264);
            } else {
                // Computing 2nd power
                final double d1 = AOR_plus(gw, AOR_multiply(HALF, curv, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77224, _mut77225, _mut77226, _mut77227), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77228, _mut77229, _mut77230, _mut77231);
                cauchy = AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77232, _mut77233, _mut77234, _mut77235);
            }
            if (ROR_equals(iflag, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77265, _mut77266, _mut77267, _mut77268, _mut77269)) {
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77270, _mut77271, _mut77272, _mut77273, _mut77274); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
                    glag.setEntry(i, -glag.getEntry(i));
                    work2.setEntry(i, alternativeNewPoint.getEntry(i));
                }
                csave = cauchy;
                iflag = 1;
            } else {
                break;
            }
        }
        if (ROR_greater(csave, cauchy, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77275, _mut77276, _mut77277, _mut77278, _mut77279)) {
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271", _mut77280, _mut77281, _mut77282, _mut77283, _mut77284); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov_1271");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int ndim = bMatrix.getRowDimension();
        final double rhosq = AOR_multiply(initialTrustRegionRadius, initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77285, _mut77286, _mut77287, _mut77288);
        final double recip = AOR_divide(1d, rhosq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77289, _mut77290, _mut77291, _mut77292);
        final int np = AOR_plus(n, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77293, _mut77294, _mut77295, _mut77296);
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77307, _mut77308, _mut77309, _mut77310, _mut77311); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
            originShift.setEntry(j, currentBest.getEntry(j));
            for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77297, _mut77298, _mut77299, _mut77300, _mut77301); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
                interpolationPoints.setEntry(k, j, ZERO);
            }
            for (int i = 0; ROR_less(i, ndim, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77302, _mut77303, _mut77304, _mut77305, _mut77306); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
                bMatrix.setEntry(i, j, ZERO);
            }
        }
        for (int i = 0, max = n * np / 2; ROR_less(i, max, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77312, _mut77313, _mut77314, _mut77315, _mut77316); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
            modelSecondDerivativesValues.setEntry(i, ZERO);
        }
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77322, _mut77323, _mut77324, _mut77325, _mut77326); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
            modelSecondDerivativesParameters.setEntry(k, ZERO);
            for (int j = 0, max = npt - np; ROR_less(j, max, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77317, _mut77318, _mut77319, _mut77320, _mut77321); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
                zMatrix.setEntry(k, j, ZERO);
            }
        }
        int ipt = 0;
        int jpt = 0;
        double fbeg = Double.NaN;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
            final int nfm = getEvaluations();
            final int nfx = AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77327, _mut77328, _mut77329, _mut77330);
            final int nfmm = AOR_minus(nfm, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77331, _mut77332, _mut77333, _mut77334);
            final int nfxm = AOR_minus(nfx, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77335, _mut77336, _mut77337, _mut77338);
            double stepa = 0;
            double stepb = 0;
            if (ROR_less_equals(nfm, AOR_multiply(2, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77339, _mut77340, _mut77341, _mut77342), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77343, _mut77344, _mut77345, _mut77346, _mut77347)) {
                if ((_mut77399 ? (ROR_greater_equals(nfm, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77389, _mut77390, _mut77391, _mut77392, _mut77393) || ROR_less_equals(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77394, _mut77395, _mut77396, _mut77397, _mut77398)) : (ROR_greater_equals(nfm, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77389, _mut77390, _mut77391, _mut77392, _mut77393) && ROR_less_equals(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77394, _mut77395, _mut77396, _mut77397, _mut77398)))) {
                    stepa = initialTrustRegionRadius;
                    if (ROR_equals(upperDifference.getEntry(nfmm), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77423, _mut77424, _mut77425, _mut77426, _mut77427)) {
                        stepa = -stepa;
                    }
                    interpolationPoints.setEntry(nfm, nfmm, stepa);
                } else if (ROR_greater(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77400, _mut77401, _mut77402, _mut77403, _mut77404)) {
                    stepa = interpolationPoints.getEntry(nfx, nfxm);
                    stepb = -initialTrustRegionRadius;
                    if (ROR_equals(lowerDifference.getEntry(nfxm), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77405, _mut77406, _mut77407, _mut77408, _mut77409)) {
                        stepb = FastMath.min(AOR_multiply(TWO, initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77410, _mut77411, _mut77412, _mut77413), upperDifference.getEntry(nfxm));
                    }
                    if (ROR_equals(upperDifference.getEntry(nfxm), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77414, _mut77415, _mut77416, _mut77417, _mut77418)) {
                        stepb = FastMath.max(AOR_multiply(-TWO, initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77419, _mut77420, _mut77421, _mut77422), lowerDifference.getEntry(nfxm));
                    }
                    interpolationPoints.setEntry(nfm, nfxm, stepb);
                }
            } else {
                final int tmp1 = AOR_divide((AOR_minus(nfm, np, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77348, _mut77349, _mut77350, _mut77351)), n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77352, _mut77353, _mut77354, _mut77355);
                jpt = AOR_minus(AOR_minus(nfm, AOR_multiply(tmp1, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77356, _mut77357, _mut77358, _mut77359), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77360, _mut77361, _mut77362, _mut77363), n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77364, _mut77365, _mut77366, _mut77367);
                ipt = AOR_plus(jpt, tmp1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77368, _mut77369, _mut77370, _mut77371);
                if (ROR_greater(ipt, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77372, _mut77373, _mut77374, _mut77375, _mut77376)) {
                    final int tmp2 = jpt;
                    jpt = AOR_minus(ipt, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77377, _mut77378, _mut77379, _mut77380);
                    ipt = tmp2;
                }
                final int iptMinus1 = AOR_minus(ipt, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77381, _mut77382, _mut77383, _mut77384);
                final int jptMinus1 = AOR_minus(jpt, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77385, _mut77386, _mut77387, _mut77388);
                interpolationPoints.setEntry(nfm, iptMinus1, interpolationPoints.getEntry(ipt, iptMinus1));
                interpolationPoints.setEntry(nfm, jptMinus1, interpolationPoints.getEntry(jpt, jptMinus1));
            }
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77442, _mut77443, _mut77444, _mut77445, _mut77446); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595");
                currentBest.setEntry(j, FastMath.min(FastMath.max(lowerBound[j], AOR_plus(originShift.getEntry(j), interpolationPoints.getEntry(nfm, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77428, _mut77429, _mut77430, _mut77431)), upperBound[j]));
                if (ROR_equals(interpolationPoints.getEntry(nfm, j), lowerDifference.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77432, _mut77433, _mut77434, _mut77435, _mut77436)) {
                    currentBest.setEntry(j, lowerBound[j]);
                }
                if (ROR_equals(interpolationPoints.getEntry(nfm, j), upperDifference.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77437, _mut77438, _mut77439, _mut77440, _mut77441)) {
                    currentBest.setEntry(j, upperBound[j]);
                }
            }
            final double objectiveValue = computeObjectiveValue(currentBest.toArray());
            final double f = isMinimize ? objectiveValue : -objectiveValue;
            // nfm + 1
            final int numEval = getEvaluations();
            fAtInterpolationPoints.setEntry(nfm, f);
            if (ROR_equals(numEval, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77447, _mut77448, _mut77449, _mut77450, _mut77451)) {
                fbeg = f;
                trustRegionCenterInterpolationPointIndex = 0;
            } else if (ROR_less(f, fAtInterpolationPoints.getEntry(trustRegionCenterInterpolationPointIndex), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77452, _mut77453, _mut77454, _mut77455, _mut77456)) {
                trustRegionCenterInterpolationPointIndex = nfm;
            }
            if (ROR_less_equals(numEval, AOR_plus(AOR_multiply(2, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77457, _mut77458, _mut77459, _mut77460), 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77461, _mut77462, _mut77463, _mut77464), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77465, _mut77466, _mut77467, _mut77468, _mut77469)) {
                if ((_mut77532 ? (ROR_greater_equals(numEval, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77518, _mut77519, _mut77520, _mut77521, _mut77522) || ROR_less_equals(numEval, AOR_plus(n, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77523, _mut77524, _mut77525, _mut77526), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77527, _mut77528, _mut77529, _mut77530, _mut77531)) : (ROR_greater_equals(numEval, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77518, _mut77519, _mut77520, _mut77521, _mut77522) && ROR_less_equals(numEval, AOR_plus(n, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77523, _mut77524, _mut77525, _mut77526), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77527, _mut77528, _mut77529, _mut77530, _mut77531)))) {
                    gradientAtTrustRegionCenter.setEntry(nfmm, AOR_divide((AOR_minus(f, fbeg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77686, _mut77687, _mut77688, _mut77689)), stepa, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77690, _mut77691, _mut77692, _mut77693));
                    if (ROR_less(npt, AOR_plus(numEval, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77694, _mut77695, _mut77696, _mut77697), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77698, _mut77699, _mut77700, _mut77701, _mut77702)) {
                        final double oneOverStepA = AOR_divide(ONE, stepa, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77703, _mut77704, _mut77705, _mut77706);
                        bMatrix.setEntry(0, nfmm, -oneOverStepA);
                        bMatrix.setEntry(nfm, nfmm, oneOverStepA);
                        bMatrix.setEntry(AOR_plus(npt, nfmm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77707, _mut77708, _mut77709, _mut77710), nfmm, AOR_multiply(-HALF, rhosq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77711, _mut77712, _mut77713, _mut77714));
                    }
                } else if (ROR_greater_equals(numEval, AOR_plus(n, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77533, _mut77534, _mut77535, _mut77536), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77537, _mut77538, _mut77539, _mut77540, _mut77541)) {
                    final int ih = AOR_minus(AOR_divide(AOR_multiply(nfx, (AOR_plus(nfx, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77542, _mut77543, _mut77544, _mut77545)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77546, _mut77547, _mut77548, _mut77549), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77550, _mut77551, _mut77552, _mut77553), 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77554, _mut77555, _mut77556, _mut77557);
                    final double tmp = AOR_divide((AOR_minus(f, fbeg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77558, _mut77559, _mut77560, _mut77561)), stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77562, _mut77563, _mut77564, _mut77565);
                    final double diff = AOR_minus(stepb, stepa, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77566, _mut77567, _mut77568, _mut77569);
                    modelSecondDerivativesValues.setEntry(ih, AOR_divide(AOR_multiply(TWO, (AOR_minus(tmp, gradientAtTrustRegionCenter.getEntry(nfxm), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77570, _mut77571, _mut77572, _mut77573)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77574, _mut77575, _mut77576, _mut77577), diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77578, _mut77579, _mut77580, _mut77581));
                    gradientAtTrustRegionCenter.setEntry(nfxm, AOR_divide((AOR_minus(AOR_multiply(gradientAtTrustRegionCenter.getEntry(nfxm), stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77582, _mut77583, _mut77584, _mut77585), AOR_multiply(tmp, stepa, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77586, _mut77587, _mut77588, _mut77589), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77590, _mut77591, _mut77592, _mut77593)), diff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77594, _mut77595, _mut77596, _mut77597));
                    if ((_mut77616 ? (ROR_less(AOR_multiply(stepa, stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77598, _mut77599, _mut77600, _mut77601), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77602, _mut77603, _mut77604, _mut77605, _mut77606) || ROR_less(f, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77607, _mut77608, _mut77609, _mut77610)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77611, _mut77612, _mut77613, _mut77614, _mut77615)) : (ROR_less(AOR_multiply(stepa, stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77598, _mut77599, _mut77600, _mut77601), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77602, _mut77603, _mut77604, _mut77605, _mut77606) && ROR_less(f, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77607, _mut77608, _mut77609, _mut77610)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77611, _mut77612, _mut77613, _mut77614, _mut77615)))) {
                        fAtInterpolationPoints.setEntry(nfm, fAtInterpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77617, _mut77618, _mut77619, _mut77620)));
                        fAtInterpolationPoints.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77621, _mut77622, _mut77623, _mut77624), f);
                        if (ROR_equals(trustRegionCenterInterpolationPointIndex, nfm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77625, _mut77626, _mut77627, _mut77628, _mut77629)) {
                            trustRegionCenterInterpolationPointIndex = AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77630, _mut77631, _mut77632, _mut77633);
                        }
                        interpolationPoints.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77634, _mut77635, _mut77636, _mut77637), nfxm, stepb);
                        interpolationPoints.setEntry(nfm, nfxm, stepa);
                    }
                    bMatrix.setEntry(0, nfxm, AOR_divide(-(AOR_plus(stepa, stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77638, _mut77639, _mut77640, _mut77641)), (AOR_multiply(stepa, stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77642, _mut77643, _mut77644, _mut77645)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77646, _mut77647, _mut77648, _mut77649));
                    bMatrix.setEntry(nfm, nfxm, AOR_divide(-HALF, interpolationPoints.getEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77650, _mut77651, _mut77652, _mut77653), nfxm), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77654, _mut77655, _mut77656, _mut77657));
                    bMatrix.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77658, _mut77659, _mut77660, _mut77661), nfxm, AOR_minus(-bMatrix.getEntry(0, nfxm), bMatrix.getEntry(nfm, nfxm), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77662, _mut77663, _mut77664, _mut77665));
                    zMatrix.setEntry(0, nfxm, AOR_divide(FastMath.sqrt(TWO), (AOR_multiply(stepa, stepb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77666, _mut77667, _mut77668, _mut77669)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77670, _mut77671, _mut77672, _mut77673));
                    zMatrix.setEntry(nfm, nfxm, AOR_divide(FastMath.sqrt(HALF), rhosq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77674, _mut77675, _mut77676, _mut77677));
                    // zMatrix.setEntry(nfm, nfxm, FastMath.sqrt(HALF) * recip); // XXX "testAckley" and "testDiffPow" fail.
                    zMatrix.setEntry(AOR_minus(nfm, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77678, _mut77679, _mut77680, _mut77681), nfxm, AOR_minus(-zMatrix.getEntry(0, nfxm), zMatrix.getEntry(nfm, nfxm), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77682, _mut77683, _mut77684, _mut77685));
                }
            } else {
                zMatrix.setEntry(0, nfxm, recip);
                zMatrix.setEntry(nfm, nfxm, recip);
                zMatrix.setEntry(ipt, nfxm, -recip);
                zMatrix.setEntry(jpt, nfxm, -recip);
                final int ih = AOR_minus(AOR_plus(AOR_divide(AOR_multiply(ipt, (AOR_minus(ipt, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77470, _mut77471, _mut77472, _mut77473)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77474, _mut77475, _mut77476, _mut77477), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77478, _mut77479, _mut77480, _mut77481), jpt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77482, _mut77483, _mut77484, _mut77485), 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77486, _mut77487, _mut77488, _mut77489);
                final double tmp = AOR_multiply(interpolationPoints.getEntry(nfm, AOR_minus(ipt, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77490, _mut77491, _mut77492, _mut77493)), interpolationPoints.getEntry(nfm, AOR_minus(jpt, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77494, _mut77495, _mut77496, _mut77497)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77498, _mut77499, _mut77500, _mut77501);
                modelSecondDerivativesValues.setEntry(ih, AOR_divide((AOR_plus(AOR_minus(AOR_minus(fbeg, fAtInterpolationPoints.getEntry(ipt), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77502, _mut77503, _mut77504, _mut77505), fAtInterpolationPoints.getEntry(jpt), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77506, _mut77507, _mut77508, _mut77509), f, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77510, _mut77511, _mut77512, _mut77513)), tmp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77514, _mut77515, _mut77516, _mut77517));
            }
        } while (ROR_less(getEvaluations(), npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.prelim_1595", _mut77715, _mut77716, _mut77717, _mut77718, _mut77719));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
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
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77746, _mut77747, _mut77748, _mut77749, _mut77750); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
            xbdi.setEntry(i, ZERO);
            if (ROR_less_equals(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77720, _mut77721, _mut77722, _mut77723, _mut77724)) {
                if (ROR_greater_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77736, _mut77737, _mut77738, _mut77739, _mut77740)) {
                    xbdi.setEntry(i, MINUS_ONE);
                }
            } else if ((_mut77735 ? (ROR_greater_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77725, _mut77726, _mut77727, _mut77728, _mut77729) || ROR_less_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77730, _mut77731, _mut77732, _mut77733, _mut77734)) : (ROR_greater_equals(trustRegionCenterOffset.getEntry(i), upperDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77725, _mut77726, _mut77727, _mut77728, _mut77729) && ROR_less_equals(gradientAtTrustRegionCenter.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77730, _mut77731, _mut77732, _mut77733, _mut77734)))) {
                xbdi.setEntry(i, ONE);
            }
            if (ROR_not_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77741, _mut77742, _mut77743, _mut77744, _mut77745)) {
                ++nact;
            }
            trialStepPoint.setEntry(i, ZERO);
            gnew.setEntry(i, gradientAtTrustRegionCenter.getEntry(i));
        }
        delsq = AOR_multiply(delta, delta, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77751, _mut77752, _mut77753, _mut77754);
        qred = ZERO;
        crvmin = MINUS_ONE;
        int state = 20;
        for (; ; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
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
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77777, _mut77778, _mut77779, _mut77780, _mut77781); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_not_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77755, _mut77756, _mut77757, _mut77758, _mut77759)) {
                                s.setEntry(i, ZERO);
                            } else if (ROR_equals(beta, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77760, _mut77761, _mut77762, _mut77763, _mut77764)) {
                                s.setEntry(i, -gnew.getEntry(i));
                            } else {
                                s.setEntry(i, AOR_minus(AOR_multiply(beta, s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77765, _mut77766, _mut77767, _mut77768), gnew.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77769, _mut77770, _mut77771, _mut77772));
                            }
                            // Computing 2nd power
                            final double d1 = s.getEntry(i);
                            stepsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77773, _mut77774, _mut77775, _mut77776);
                        }
                        if (ROR_equals(stepsq, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77782, _mut77783, _mut77784, _mut77785, _mut77786)) {
                            state = 190;
                            break;
                        }
                        if (ROR_equals(beta, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77787, _mut77788, _mut77789, _mut77790, _mut77791)) {
                            gredsq = stepsq;
                            itermax = AOR_minus(AOR_plus(iterc, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77792, _mut77793, _mut77794, _mut77795), nact, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77796, _mut77797, _mut77798, _mut77799);
                        }
                        if (ROR_less_equals(AOR_multiply(gredsq, delsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77800, _mut77801, _mut77802, _mut77803), AOR_multiply(AOR_multiply(qred, 1e-4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77804, _mut77805, _mut77806, _mut77807), qred, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77808, _mut77809, _mut77810, _mut77811), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77812, _mut77813, _mut77814, _mut77815, _mut77816)) {
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
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77834, _mut77835, _mut77836, _mut77837, _mut77838); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77817, _mut77818, _mut77819, _mut77820, _mut77821)) {
                                // Computing 2nd power
                                final double d1 = trialStepPoint.getEntry(i);
                                resid -= AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77822, _mut77823, _mut77824, _mut77825);
                                ds += AOR_multiply(s.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77826, _mut77827, _mut77828, _mut77829);
                                shs += AOR_multiply(s.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77830, _mut77831, _mut77832, _mut77833);
                            }
                        }
                        if (ROR_less_equals(resid, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77839, _mut77840, _mut77841, _mut77842, _mut77843)) {
                            state = 90;
                            break;
                        }
                        temp = FastMath.sqrt(AOR_plus(AOR_multiply(stepsq, resid, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77844, _mut77845, _mut77846, _mut77847), AOR_multiply(ds, ds, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77848, _mut77849, _mut77850, _mut77851), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77852, _mut77853, _mut77854, _mut77855));
                        if (ROR_less(ds, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77856, _mut77857, _mut77858, _mut77859, _mut77860)) {
                            blen = AOR_divide((AOR_minus(temp, ds, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77869, _mut77870, _mut77871, _mut77872)), stepsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77873, _mut77874, _mut77875, _mut77876);
                        } else {
                            blen = AOR_divide(resid, (AOR_plus(temp, ds, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77861, _mut77862, _mut77863, _mut77864)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77865, _mut77866, _mut77867, _mut77868);
                        }
                        stplen = blen;
                        if (ROR_greater(shs, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77877, _mut77878, _mut77879, _mut77880, _mut77881)) {
                            // Computing MIN
                            stplen = FastMath.min(blen, AOR_divide(gredsq, shs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77882, _mut77883, _mut77884, _mut77885));
                        }
                        iact = -1;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77921, _mut77922, _mut77923, _mut77924, _mut77925); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_not_equals(s.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77886, _mut77887, _mut77888, _mut77889, _mut77890)) {
                                xsum = AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77891, _mut77892, _mut77893, _mut77894);
                                if (ROR_greater(s.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77895, _mut77896, _mut77897, _mut77898, _mut77899)) {
                                    temp = AOR_divide((AOR_minus(upperDifference.getEntry(i), xsum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77908, _mut77909, _mut77910, _mut77911)), s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77912, _mut77913, _mut77914, _mut77915);
                                } else {
                                    temp = AOR_divide((AOR_minus(lowerDifference.getEntry(i), xsum, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77900, _mut77901, _mut77902, _mut77903)), s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77904, _mut77905, _mut77906, _mut77907);
                                }
                                if (ROR_less(temp, stplen, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77916, _mut77917, _mut77918, _mut77919, _mut77920)) {
                                    stplen = temp;
                                    iact = i;
                                }
                            }
                        }
                        sdec = ZERO;
                        if (ROR_greater(stplen, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77926, _mut77927, _mut77928, _mut77929, _mut77930)) {
                            ++iterc;
                            temp = AOR_divide(shs, stepsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77931, _mut77932, _mut77933, _mut77934);
                            if ((_mut77945 ? (ROR_equals(iact, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77935, _mut77936, _mut77937, _mut77938, _mut77939) || ROR_greater(temp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77940, _mut77941, _mut77942, _mut77943, _mut77944)) : (ROR_equals(iact, -1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77935, _mut77936, _mut77937, _mut77938, _mut77939) && ROR_greater(temp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77940, _mut77941, _mut77942, _mut77943, _mut77944)))) {
                                crvmin = FastMath.min(crvmin, temp);
                                if (ROR_equals(crvmin, MINUS_ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77946, _mut77947, _mut77948, _mut77949, _mut77950)) {
                                    crvmin = temp;
                                }
                            }
                            ggsav = gredsq;
                            gredsq = ZERO;
                            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77976, _mut77977, _mut77978, _mut77979, _mut77980); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                                gnew.setEntry(i, AOR_plus(gnew.getEntry(i), AOR_multiply(stplen, hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77951, _mut77952, _mut77953, _mut77954), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77955, _mut77956, _mut77957, _mut77958));
                                if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77959, _mut77960, _mut77961, _mut77962, _mut77963)) {
                                    // Computing 2nd power
                                    final double d1 = gnew.getEntry(i);
                                    gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77964, _mut77965, _mut77966, _mut77967);
                                }
                                trialStepPoint.setEntry(i, AOR_plus(trialStepPoint.getEntry(i), AOR_multiply(stplen, s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77968, _mut77969, _mut77970, _mut77971), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77972, _mut77973, _mut77974, _mut77975));
                            }
                            // Computing MAX
                            final double d1 = AOR_multiply(stplen, (AOR_minus(ggsav, AOR_multiply(AOR_multiply(HALF, stplen, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77981, _mut77982, _mut77983, _mut77984), shs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77985, _mut77986, _mut77987, _mut77988), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77989, _mut77990, _mut77991, _mut77992)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77993, _mut77994, _mut77995, _mut77996);
                            sdec = FastMath.max(d1, ZERO);
                            qred += sdec;
                        }
                        if (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut77997, _mut77998, _mut77999, _mut78000, _mut78001)) {
                            ++nact;
                            xbdi.setEntry(iact, ONE);
                            if (ROR_less(s.getEntry(iact), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78002, _mut78003, _mut78004, _mut78005, _mut78006)) {
                                xbdi.setEntry(iact, MINUS_ONE);
                            }
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(iact);
                            delsq -= AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78007, _mut78008, _mut78009, _mut78010);
                            if (ROR_less_equals(delsq, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78011, _mut78012, _mut78013, _mut78014, _mut78015)) {
                                state = 190;
                                break;
                            }
                            state = 20;
                            break;
                        }
                        if (ROR_less(stplen, blen, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78016, _mut78017, _mut78018, _mut78019, _mut78020)) {
                            if (ROR_equals(iterc, itermax, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78021, _mut78022, _mut78023, _mut78024, _mut78025)) {
                                state = 190;
                                break;
                            }
                            if (ROR_less_equals(sdec, AOR_multiply(qred, .01, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78026, _mut78027, _mut78028, _mut78029), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78030, _mut78031, _mut78032, _mut78033, _mut78034)) {
                                state = 190;
                                break;
                            }
                            beta = AOR_divide(gredsq, ggsav, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78035, _mut78036, _mut78037, _mut78038);
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
                        if (ROR_greater_equals(nact, AOR_minus(n, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78039, _mut78040, _mut78041, _mut78042), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78043, _mut78044, _mut78045, _mut78046, _mut78047)) {
                            state = 190;
                            break;
                        }
                        dredsq = ZERO;
                        dredg = ZERO;
                        gredsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78065, _mut78066, _mut78067, _mut78068, _mut78069); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78048, _mut78049, _mut78050, _mut78051, _mut78052)) {
                                // Computing 2nd power
                                double d1 = trialStepPoint.getEntry(i);
                                dredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78053, _mut78054, _mut78055, _mut78056);
                                dredg += AOR_multiply(trialStepPoint.getEntry(i), gnew.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78057, _mut78058, _mut78059, _mut78060);
                                // Computing 2nd power
                                d1 = gnew.getEntry(i);
                                gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78061, _mut78062, _mut78063, _mut78064);
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
                        temp = AOR_minus(AOR_multiply(gredsq, dredsq, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78070, _mut78071, _mut78072, _mut78073), AOR_multiply(dredg, dredg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78074, _mut78075, _mut78076, _mut78077), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78078, _mut78079, _mut78080, _mut78081);
                        if (ROR_less_equals(temp, AOR_multiply(AOR_multiply(qred, 1e-4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78082, _mut78083, _mut78084, _mut78085), qred, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78086, _mut78087, _mut78088, _mut78089), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78090, _mut78091, _mut78092, _mut78093, _mut78094)) {
                            state = 190;
                            break;
                        }
                        temp = FastMath.sqrt(temp);
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78116, _mut78117, _mut78118, _mut78119, _mut78120); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78095, _mut78096, _mut78097, _mut78098, _mut78099)) {
                                s.setEntry(i, AOR_divide((AOR_minus(AOR_multiply(dredg, trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78100, _mut78101, _mut78102, _mut78103), AOR_multiply(dredsq, gnew.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78104, _mut78105, _mut78106, _mut78107), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78108, _mut78109, _mut78110, _mut78111)), temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78112, _mut78113, _mut78114, _mut78115));
                            } else {
                                s.setEntry(i, ZERO);
                            }
                        }
                        sredg = -temp;
                        angbd = ONE;
                        iact = -1;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78232, _mut78233, _mut78234, _mut78235, _mut78236); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78121, _mut78122, _mut78123, _mut78124, _mut78125)) {
                                tempa = AOR_minus(AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78126, _mut78127, _mut78128, _mut78129), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78130, _mut78131, _mut78132, _mut78133);
                                tempb = AOR_minus(AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78134, _mut78135, _mut78136, _mut78137), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78138, _mut78139, _mut78140, _mut78141);
                                if (ROR_less_equals(tempa, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78142, _mut78143, _mut78144, _mut78145, _mut78146)) {
                                    ++nact;
                                    xbdi.setEntry(i, MINUS_ONE);
                                    state = 100;
                                    break;
                                } else if (ROR_less_equals(tempb, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78147, _mut78148, _mut78149, _mut78150, _mut78151)) {
                                    ++nact;
                                    xbdi.setEntry(i, ONE);
                                    state = 100;
                                    break;
                                }
                                // Computing 2nd power
                                double d1 = trialStepPoint.getEntry(i);
                                // Computing 2nd power
                                double d2 = s.getEntry(i);
                                ssq = AOR_plus(AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78152, _mut78153, _mut78154, _mut78155), AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78156, _mut78157, _mut78158, _mut78159), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78160, _mut78161, _mut78162, _mut78163);
                                // Computing 2nd power
                                d1 = AOR_minus(trustRegionCenterOffset.getEntry(i), lowerDifference.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78164, _mut78165, _mut78166, _mut78167);
                                temp = AOR_minus(ssq, AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78168, _mut78169, _mut78170, _mut78171), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78172, _mut78173, _mut78174, _mut78175);
                                if (ROR_greater(temp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78176, _mut78177, _mut78178, _mut78179, _mut78180)) {
                                    temp = AOR_minus(FastMath.sqrt(temp), s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78181, _mut78182, _mut78183, _mut78184);
                                    if (ROR_greater(AOR_multiply(angbd, temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78185, _mut78186, _mut78187, _mut78188), tempa, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78189, _mut78190, _mut78191, _mut78192, _mut78193)) {
                                        angbd = AOR_divide(tempa, temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78194, _mut78195, _mut78196, _mut78197);
                                        iact = i;
                                        xsav = MINUS_ONE;
                                    }
                                }
                                // Computing 2nd power
                                d1 = AOR_minus(upperDifference.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78198, _mut78199, _mut78200, _mut78201);
                                temp = AOR_minus(ssq, AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78202, _mut78203, _mut78204, _mut78205), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78206, _mut78207, _mut78208, _mut78209);
                                if (ROR_greater(temp, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78210, _mut78211, _mut78212, _mut78213, _mut78214)) {
                                    temp = AOR_plus(FastMath.sqrt(temp), s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78215, _mut78216, _mut78217, _mut78218);
                                    if (ROR_greater(AOR_multiply(angbd, temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78219, _mut78220, _mut78221, _mut78222), tempb, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78223, _mut78224, _mut78225, _mut78226, _mut78227)) {
                                        angbd = AOR_divide(tempb, temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78228, _mut78229, _mut78230, _mut78231);
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
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78254, _mut78255, _mut78256, _mut78257, _mut78258); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78237, _mut78238, _mut78239, _mut78240, _mut78241)) {
                                shs += AOR_multiply(s.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78242, _mut78243, _mut78244, _mut78245);
                                dhs += AOR_multiply(trialStepPoint.getEntry(i), hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78246, _mut78247, _mut78248, _mut78249);
                                dhd += AOR_multiply(trialStepPoint.getEntry(i), hred.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78250, _mut78251, _mut78252, _mut78253);
                            }
                        }
                        redmax = ZERO;
                        isav = -1;
                        redsav = ZERO;
                        iu = (int) (AOR_plus(AOR_multiply(angbd, 17., "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78259, _mut78260, _mut78261, _mut78262), 3.1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78263, _mut78264, _mut78265, _mut78266));
                        for (int i = 0; ROR_less(i, iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78349, _mut78350, _mut78351, _mut78352, _mut78353); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            angt = AOR_divide(AOR_multiply(angbd, i, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78267, _mut78268, _mut78269, _mut78270), iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78271, _mut78272, _mut78273, _mut78274);
                            sth = AOR_divide((AOR_plus(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78275, _mut78276, _mut78277, _mut78278)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78279, _mut78280, _mut78281, _mut78282), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78283, _mut78284, _mut78285, _mut78286)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78287, _mut78288, _mut78289, _mut78290);
                            temp = AOR_plus(shs, AOR_multiply(angt, (AOR_minus(AOR_minus(AOR_multiply(angt, dhd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78291, _mut78292, _mut78293, _mut78294), dhs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78295, _mut78296, _mut78297, _mut78298), dhs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78299, _mut78300, _mut78301, _mut78302)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78303, _mut78304, _mut78305, _mut78306), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78307, _mut78308, _mut78309, _mut78310);
                            rednew = AOR_multiply(sth, (AOR_minus(AOR_minus(AOR_multiply(angt, dredg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78311, _mut78312, _mut78313, _mut78314), sredg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78315, _mut78316, _mut78317, _mut78318), AOR_multiply(AOR_multiply(HALF, sth, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78319, _mut78320, _mut78321, _mut78322), temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78323, _mut78324, _mut78325, _mut78326), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78327, _mut78328, _mut78329, _mut78330)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78331, _mut78332, _mut78333, _mut78334);
                            if (ROR_greater(rednew, redmax, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78335, _mut78336, _mut78337, _mut78338, _mut78339)) {
                                redmax = rednew;
                                isav = i;
                                rdprev = redsav;
                            } else if (ROR_equals(i, AOR_plus(isav, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78340, _mut78341, _mut78342, _mut78343), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78344, _mut78345, _mut78346, _mut78347, _mut78348)) {
                                rdnext = rednew;
                            }
                            redsav = rednew;
                        }
                        if (ROR_less(isav, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78354, _mut78355, _mut78356, _mut78357, _mut78358)) {
                            state = 190;
                            break;
                        }
                        if (ROR_less(isav, iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78359, _mut78360, _mut78361, _mut78362, _mut78363)) {
                            temp = AOR_divide((AOR_minus(rdnext, rdprev, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78364, _mut78365, _mut78366, _mut78367)), (AOR_minus(AOR_minus(AOR_plus(redmax, redmax, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78368, _mut78369, _mut78370, _mut78371), rdprev, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78372, _mut78373, _mut78374, _mut78375), rdnext, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78376, _mut78377, _mut78378, _mut78379)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78380, _mut78381, _mut78382, _mut78383);
                            angt = AOR_divide(AOR_multiply(angbd, (AOR_plus(isav, AOR_multiply(HALF, temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78384, _mut78385, _mut78386, _mut78387), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78388, _mut78389, _mut78390, _mut78391)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78392, _mut78393, _mut78394, _mut78395), iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78396, _mut78397, _mut78398, _mut78399);
                        }
                        cth = AOR_divide((AOR_minus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78400, _mut78401, _mut78402, _mut78403), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78404, _mut78405, _mut78406, _mut78407)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78408, _mut78409, _mut78410, _mut78411), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78412, _mut78413, _mut78414, _mut78415)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78416, _mut78417, _mut78418, _mut78419);
                        sth = AOR_divide((AOR_plus(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78420, _mut78421, _mut78422, _mut78423)), (AOR_plus(ONE, AOR_multiply(angt, angt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78424, _mut78425, _mut78426, _mut78427), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78428, _mut78429, _mut78430, _mut78431)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78432, _mut78433, _mut78434, _mut78435);
                        temp = AOR_plus(shs, AOR_multiply(angt, (AOR_minus(AOR_minus(AOR_multiply(angt, dhd, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78436, _mut78437, _mut78438, _mut78439), dhs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78440, _mut78441, _mut78442, _mut78443), dhs, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78444, _mut78445, _mut78446, _mut78447)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78448, _mut78449, _mut78450, _mut78451), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78452, _mut78453, _mut78454, _mut78455);
                        sdec = AOR_multiply(sth, (AOR_minus(AOR_minus(AOR_multiply(angt, dredg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78456, _mut78457, _mut78458, _mut78459), sredg, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78460, _mut78461, _mut78462, _mut78463), AOR_multiply(AOR_multiply(HALF, sth, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78464, _mut78465, _mut78466, _mut78467), temp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78468, _mut78469, _mut78470, _mut78471), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78472, _mut78473, _mut78474, _mut78475)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78476, _mut78477, _mut78478, _mut78479);
                        if (ROR_less_equals(sdec, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78480, _mut78481, _mut78482, _mut78483, _mut78484)) {
                            state = 190;
                            break;
                        }
                        dredg = ZERO;
                        gredsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78542, _mut78543, _mut78544, _mut78545, _mut78546); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            gnew.setEntry(i, AOR_plus(AOR_plus(gnew.getEntry(i), AOR_multiply((AOR_minus(cth, ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78485, _mut78486, _mut78487, _mut78488)), hred.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78489, _mut78490, _mut78491, _mut78492), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78493, _mut78494, _mut78495, _mut78496), AOR_multiply(sth, hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78497, _mut78498, _mut78499, _mut78500), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78501, _mut78502, _mut78503, _mut78504));
                            if (ROR_equals(xbdi.getEntry(i), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78505, _mut78506, _mut78507, _mut78508, _mut78509)) {
                                trialStepPoint.setEntry(i, AOR_plus(AOR_multiply(cth, trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78510, _mut78511, _mut78512, _mut78513), AOR_multiply(sth, s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78514, _mut78515, _mut78516, _mut78517), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78518, _mut78519, _mut78520, _mut78521));
                                dredg += AOR_multiply(trialStepPoint.getEntry(i), gnew.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78522, _mut78523, _mut78524, _mut78525);
                                // Computing 2nd power
                                final double d1 = gnew.getEntry(i);
                                gredsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78526, _mut78527, _mut78528, _mut78529);
                            }
                            hred.setEntry(i, AOR_plus(AOR_multiply(cth, hred.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78530, _mut78531, _mut78532, _mut78533), AOR_multiply(sth, hs.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78534, _mut78535, _mut78536, _mut78537), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78538, _mut78539, _mut78540, _mut78541));
                        }
                        qred += sdec;
                        if ((_mut78557 ? (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78547, _mut78548, _mut78549, _mut78550, _mut78551) || ROR_equals(isav, iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78552, _mut78553, _mut78554, _mut78555, _mut78556)) : (ROR_greater_equals(iact, 0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78547, _mut78548, _mut78549, _mut78550, _mut78551) && ROR_equals(isav, iu, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78552, _mut78553, _mut78554, _mut78555, _mut78556)))) {
                            ++nact;
                            xbdi.setEntry(iact, xsav);
                            state = 100;
                            break;
                        }
                        if (ROR_greater(sdec, AOR_multiply(qred, .01, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78558, _mut78559, _mut78560, _mut78561), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78562, _mut78563, _mut78564, _mut78565, _mut78566)) {
                            state = 120;
                            break;
                        }
                    }
                case 190:
                    {
                        // XXX
                        printState(190);
                        dsq = ZERO;
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78589, _mut78590, _mut78591, _mut78592, _mut78593); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            // Computing MIN
                            final double min = FastMath.min(AOR_plus(trustRegionCenterOffset.getEntry(i), trialStepPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78567, _mut78568, _mut78569, _mut78570), upperDifference.getEntry(i));
                            newPoint.setEntry(i, FastMath.max(min, lowerDifference.getEntry(i)));
                            if (ROR_equals(xbdi.getEntry(i), MINUS_ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78571, _mut78572, _mut78573, _mut78574, _mut78575)) {
                                newPoint.setEntry(i, lowerDifference.getEntry(i));
                            }
                            if (ROR_equals(xbdi.getEntry(i), ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78576, _mut78577, _mut78578, _mut78579, _mut78580)) {
                                newPoint.setEntry(i, upperDifference.getEntry(i));
                            }
                            trialStepPoint.setEntry(i, AOR_minus(newPoint.getEntry(i), trustRegionCenterOffset.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78581, _mut78582, _mut78583, _mut78584));
                            // Computing 2nd power
                            final double d1 = trialStepPoint.getEntry(i);
                            dsq += AOR_multiply(d1, d1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78585, _mut78586, _mut78587, _mut78588);
                        }
                        return new double[] { dsq, crvmin };
                    }
                case 210:
                    {
                        // XXX
                        printState(210);
                        int ih = 0;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78620, _mut78621, _mut78622, _mut78623, _mut78624); j++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            hs.setEntry(j, ZERO);
                            for (int i = 0; ROR_less_equals(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78615, _mut78616, _mut78617, _mut78618, _mut78619); i++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                                if (ROR_less(i, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78594, _mut78595, _mut78596, _mut78597, _mut78598)) {
                                    hs.setEntry(j, AOR_plus(hs.getEntry(j), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), s.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78599, _mut78600, _mut78601, _mut78602), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78603, _mut78604, _mut78605, _mut78606));
                                }
                                hs.setEntry(i, AOR_plus(hs.getEntry(i), AOR_multiply(modelSecondDerivativesValues.getEntry(ih), s.getEntry(j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78607, _mut78608, _mut78609, _mut78610), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78611, _mut78612, _mut78613, _mut78614));
                                ih++;
                            }
                        }
                        final RealVector tmp = interpolationPoints.operate(s).ebeMultiply(modelSecondDerivativesParameters);
                        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78643, _mut78644, _mut78645, _mut78646, _mut78647); k++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                            if (ROR_not_equals(modelSecondDerivativesParameters.getEntry(k), ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78625, _mut78626, _mut78627, _mut78628, _mut78629)) {
                                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78638, _mut78639, _mut78640, _mut78641, _mut78642); i++) {
                                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
                                    hs.setEntry(i, AOR_plus(hs.getEntry(i), AOR_multiply(tmp.getEntry(k), interpolationPoints.getEntry(k, i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78630, _mut78631, _mut78632, _mut78633), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78634, _mut78635, _mut78636, _mut78637));
                                }
                            }
                        }
                        if (ROR_not_equals(crvmin, ZERO, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78648, _mut78649, _mut78650, _mut78651, _mut78652)) {
                            state = 50;
                            break;
                        }
                        if (ROR_greater(iterc, itcsav, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78653, _mut78654, _mut78655, _mut78656, _mut78657)) {
                            state = 150;
                            break;
                        }
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815", _mut78658, _mut78659, _mut78660, _mut78661, _mut78662); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox_1815");
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
        // XXX
        printMethod();
        final int n = currentBest.getDimension();
        final int npt = numberOfInterpolationPoints;
        final int nptm = AOR_minus(AOR_minus(npt, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78663, _mut78664, _mut78665, _mut78666), 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78667, _mut78668, _mut78669, _mut78670);
        // XXX Should probably be split into two arrays.
        final ArrayRealVector work = new ArrayRealVector(AOR_plus(npt, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78671, _mut78672, _mut78673, _mut78674));
        double ztest = ZERO;
        for (int k = 0; ROR_less(k, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78680, _mut78681, _mut78682, _mut78683, _mut78684); k++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
            for (int j = 0; ROR_less(j, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78675, _mut78676, _mut78677, _mut78678, _mut78679); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
                // Computing MAX
                ztest = FastMath.max(ztest, FastMath.abs(zMatrix.getEntry(k, j)));
            }
        }
        ztest *= 1e-20;
        for (int j = 1; ROR_less(j, nptm, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78739, _mut78740, _mut78741, _mut78742, _mut78743); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
            final double d1 = zMatrix.getEntry(knew, j);
            if (ROR_greater(FastMath.abs(d1), ztest, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78685, _mut78686, _mut78687, _mut78688, _mut78689)) {
                // Computing 2nd power
                final double d2 = zMatrix.getEntry(knew, 0);
                // Computing 2nd power
                final double d3 = zMatrix.getEntry(knew, j);
                final double d4 = FastMath.sqrt(AOR_plus(AOR_multiply(d2, d2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78690, _mut78691, _mut78692, _mut78693), AOR_multiply(d3, d3, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78694, _mut78695, _mut78696, _mut78697), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78698, _mut78699, _mut78700, _mut78701));
                final double d5 = AOR_divide(zMatrix.getEntry(knew, 0), d4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78702, _mut78703, _mut78704, _mut78705);
                final double d6 = AOR_divide(zMatrix.getEntry(knew, j), d4, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78706, _mut78707, _mut78708, _mut78709);
                for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78734, _mut78735, _mut78736, _mut78737, _mut78738); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
                    final double d7 = AOR_plus(AOR_multiply(d5, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78710, _mut78711, _mut78712, _mut78713), AOR_multiply(d6, zMatrix.getEntry(i, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78714, _mut78715, _mut78716, _mut78717), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78718, _mut78719, _mut78720, _mut78721);
                    zMatrix.setEntry(i, j, AOR_minus(AOR_multiply(d5, zMatrix.getEntry(i, j), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78722, _mut78723, _mut78724, _mut78725), AOR_multiply(d6, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78726, _mut78727, _mut78728, _mut78729), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78730, _mut78731, _mut78732, _mut78733));
                    zMatrix.setEntry(i, 0, d7);
                }
            }
            zMatrix.setEntry(knew, j, ZERO);
        }
        for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78748, _mut78749, _mut78750, _mut78751, _mut78752); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
            work.setEntry(i, AOR_multiply(zMatrix.getEntry(knew, 0), zMatrix.getEntry(i, 0), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78744, _mut78745, _mut78746, _mut78747));
        }
        final double alpha = work.getEntry(knew);
        final double tau = lagrangeValuesAtNewPoint.getEntry(knew);
        lagrangeValuesAtNewPoint.setEntry(knew, AOR_minus(lagrangeValuesAtNewPoint.getEntry(knew), ONE, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78753, _mut78754, _mut78755, _mut78756));
        final double sqrtDenom = FastMath.sqrt(denom);
        final double d1 = AOR_divide(tau, sqrtDenom, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78757, _mut78758, _mut78759, _mut78760);
        final double d2 = AOR_divide(zMatrix.getEntry(knew, 0), sqrtDenom, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78761, _mut78762, _mut78763, _mut78764);
        for (int i = 0; ROR_less(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78777, _mut78778, _mut78779, _mut78780, _mut78781); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
            zMatrix.setEntry(i, 0, AOR_minus(AOR_multiply(d1, zMatrix.getEntry(i, 0), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78765, _mut78766, _mut78767, _mut78768), AOR_multiply(d2, lagrangeValuesAtNewPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78769, _mut78770, _mut78771, _mut78772), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78773, _mut78774, _mut78775, _mut78776));
        }
        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78848, _mut78849, _mut78850, _mut78851, _mut78852); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
            final int jp = AOR_plus(npt, j, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78782, _mut78783, _mut78784, _mut78785);
            work.setEntry(jp, bMatrix.getEntry(knew, j));
            final double d3 = AOR_divide((AOR_minus(AOR_multiply(alpha, lagrangeValuesAtNewPoint.getEntry(jp), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78786, _mut78787, _mut78788, _mut78789), AOR_multiply(tau, work.getEntry(jp), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78790, _mut78791, _mut78792, _mut78793), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78794, _mut78795, _mut78796, _mut78797)), denom, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78798, _mut78799, _mut78800, _mut78801);
            final double d4 = AOR_divide((AOR_minus(AOR_multiply(-beta, work.getEntry(jp), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78802, _mut78803, _mut78804, _mut78805), AOR_multiply(tau, lagrangeValuesAtNewPoint.getEntry(jp), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78806, _mut78807, _mut78808, _mut78809), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78810, _mut78811, _mut78812, _mut78813)), denom, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78814, _mut78815, _mut78816, _mut78817);
            for (int i = 0; ROR_less_equals(i, jp, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78843, _mut78844, _mut78845, _mut78846, _mut78847); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305");
                bMatrix.setEntry(i, j, AOR_plus(AOR_plus(bMatrix.getEntry(i, j), AOR_multiply(d3, lagrangeValuesAtNewPoint.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78818, _mut78819, _mut78820, _mut78821), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78822, _mut78823, _mut78824, _mut78825), AOR_multiply(d4, work.getEntry(i), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78826, _mut78827, _mut78828, _mut78829), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78830, _mut78831, _mut78832, _mut78833));
                if (ROR_greater_equals(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78834, _mut78835, _mut78836, _mut78837, _mut78838)) {
                    bMatrix.setEntry(jp, (AOR_minus(i, npt, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.update_2305", _mut78839, _mut78840, _mut78841, _mut78842)), bMatrix.getEntry(i, j));
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392");
        // XXX
        printMethod();
        double[] init = getStartPoint();
        final int dimension = init.length;
        // Check problem dimension.
        if (ROR_less(dimension, MINIMUM_PROBLEM_DIMENSION, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78853, _mut78854, _mut78855, _mut78856, _mut78857)) {
            throw new NumberIsTooSmallException(dimension, MINIMUM_PROBLEM_DIMENSION, true);
        }
        // Check number of interpolation points.
        final int[] nPointsInterval = { AOR_plus(dimension, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78858, _mut78859, _mut78860, _mut78861), AOR_divide(AOR_multiply((AOR_plus(dimension, 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78862, _mut78863, _mut78864, _mut78865)), (AOR_plus(dimension, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78866, _mut78867, _mut78868, _mut78869)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78870, _mut78871, _mut78872, _mut78873), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78874, _mut78875, _mut78876, _mut78877) };
        if ((_mut78888 ? (ROR_less(numberOfInterpolationPoints, nPointsInterval[0], "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78878, _mut78879, _mut78880, _mut78881, _mut78882) && ROR_greater(numberOfInterpolationPoints, nPointsInterval[1], "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78883, _mut78884, _mut78885, _mut78886, _mut78887)) : (ROR_less(numberOfInterpolationPoints, nPointsInterval[0], "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78878, _mut78879, _mut78880, _mut78881, _mut78882) || ROR_greater(numberOfInterpolationPoints, nPointsInterval[1], "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78883, _mut78884, _mut78885, _mut78886, _mut78887)))) {
            throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, numberOfInterpolationPoints, nPointsInterval[0], nPointsInterval[1]);
        }
        // Initialize bound differences.
        boundDifference = new double[dimension];
        double requiredMinDiff = AOR_multiply(2, initialTrustRegionRadius, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78889, _mut78890, _mut78891, _mut78892);
        double minDiff = Double.POSITIVE_INFINITY;
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78897, _mut78898, _mut78899, _mut78900, _mut78901); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392");
            boundDifference[i] = AOR_minus(upperBound[i], lowerBound[i], "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78893, _mut78894, _mut78895, _mut78896);
            minDiff = FastMath.min(minDiff, boundDifference[i]);
        }
        if (ROR_less(minDiff, requiredMinDiff, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78902, _mut78903, _mut78904, _mut78905, _mut78906)) {
            initialTrustRegionRadius = AOR_divide(minDiff, 3.0, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78907, _mut78908, _mut78909, _mut78910);
        }
        // Initialize the data structures used by the "bobyqa" method.
        bMatrix = new Array2DRowRealMatrix(AOR_plus(dimension, numberOfInterpolationPoints, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78911, _mut78912, _mut78913, _mut78914), dimension);
        zMatrix = new Array2DRowRealMatrix(numberOfInterpolationPoints, AOR_minus(AOR_minus(numberOfInterpolationPoints, dimension, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78915, _mut78916, _mut78917, _mut78918), 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78919, _mut78920, _mut78921, _mut78922));
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
        lagrangeValuesAtNewPoint = new ArrayRealVector(AOR_plus(dimension, numberOfInterpolationPoints, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78923, _mut78924, _mut78925, _mut78926));
        modelSecondDerivativesValues = new ArrayRealVector(AOR_divide(AOR_multiply(dimension, (AOR_plus(dimension, 1, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78927, _mut78928, _mut78929, _mut78930)), "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78931, _mut78932, _mut78933, _mut78934), 2, "org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.setup_2392", _mut78935, _mut78936, _mut78937, _mut78938));
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
