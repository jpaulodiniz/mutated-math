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
package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {

    @Conditional
    public static boolean _mut21293 = false, _mut21294 = false, _mut21295 = false, _mut21296 = false, _mut21297 = false, _mut21298 = false, _mut21299 = false, _mut21300 = false, _mut21301 = false, _mut21302 = false, _mut21303 = false, _mut21304 = false, _mut21305 = false, _mut21306 = false, _mut21307 = false, _mut21308 = false, _mut21309 = false, _mut21310 = false, _mut21311 = false, _mut21312 = false, _mut21313 = false, _mut21314 = false, _mut21315 = false, _mut21316 = false, _mut21317 = false, _mut21318 = false, _mut21319 = false, _mut21320 = false, _mut21321 = false, _mut21322 = false, _mut21323 = false, _mut21324 = false, _mut21325 = false, _mut21326 = false, _mut21327 = false, _mut21328 = false, _mut21329 = false, _mut21330 = false, _mut21331 = false, _mut21332 = false, _mut21333 = false, _mut21334 = false, _mut21335 = false, _mut21336 = false, _mut21337 = false, _mut21338 = false, _mut21339 = false, _mut21340 = false, _mut21341 = false, _mut21342 = false, _mut21343 = false, _mut21344 = false, _mut21345 = false, _mut21346 = false, _mut21347 = false, _mut21348 = false, _mut21349 = false, _mut21350 = false, _mut21351 = false, _mut21352 = false, _mut21353 = false, _mut21354 = false, _mut21355 = false, _mut21356 = false, _mut21357 = false, _mut21358 = false, _mut21359 = false, _mut21360 = false, _mut21361 = false, _mut21362 = false, _mut21363 = false, _mut21364 = false, _mut21365 = false, _mut21366 = false, _mut21367 = false, _mut21368 = false, _mut21369 = false, _mut21370 = false, _mut21371 = false, _mut21372 = false, _mut21373 = false, _mut21374 = false, _mut21375 = false, _mut21376 = false, _mut21377 = false, _mut21378 = false, _mut21379 = false, _mut21380 = false, _mut21381 = false, _mut21382 = false, _mut21383 = false, _mut21384 = false, _mut21385 = false, _mut21386 = false, _mut21387 = false, _mut21388 = false, _mut21389 = false, _mut21390 = false, _mut21391 = false, _mut21392 = false, _mut21393 = false, _mut21394 = false, _mut21395 = false, _mut21396 = false, _mut21397 = false, _mut21398 = false, _mut21399 = false, _mut21400 = false, _mut21401 = false, _mut21402 = false, _mut21403 = false, _mut21404 = false, _mut21405 = false, _mut21406 = false, _mut21407 = false, _mut21408 = false, _mut21409 = false, _mut21410 = false, _mut21411 = false, _mut21412 = false, _mut21413 = false, _mut21414 = false, _mut21415 = false, _mut21416 = false, _mut21417 = false, _mut21418 = false, _mut21419 = false, _mut21420 = false, _mut21421 = false, _mut21422 = false, _mut21423 = false, _mut21424 = false, _mut21425 = false, _mut21426 = false, _mut21427 = false, _mut21428 = false, _mut21429 = false, _mut21430 = false, _mut21431 = false, _mut21432 = false, _mut21433 = false, _mut21434 = false, _mut21435 = false, _mut21436 = false, _mut21437 = false, _mut21438 = false, _mut21439 = false, _mut21440 = false, _mut21441 = false, _mut21442 = false, _mut21443 = false, _mut21444 = false, _mut21445 = false, _mut21446 = false, _mut21447 = false, _mut21448 = false, _mut21449 = false, _mut21450 = false, _mut21451 = false, _mut21452 = false, _mut21453 = false, _mut21454 = false, _mut21455 = false, _mut21456 = false, _mut21457 = false, _mut21458 = false, _mut21459 = false, _mut21460 = false, _mut21461 = false, _mut21462 = false, _mut21463 = false, _mut21464 = false, _mut21465 = false, _mut21466 = false, _mut21467 = false, _mut21468 = false, _mut21469 = false, _mut21470 = false, _mut21471 = false, _mut21472 = false, _mut21473 = false, _mut21474 = false, _mut21475 = false, _mut21476 = false, _mut21477 = false, _mut21478 = false, _mut21479 = false, _mut21480 = false, _mut21481 = false, _mut21482 = false, _mut21483 = false, _mut21484 = false, _mut21485 = false, _mut21486 = false, _mut21487 = false, _mut21488 = false, _mut21489 = false, _mut21490 = false, _mut21491 = false, _mut21492 = false, _mut21493 = false, _mut21494 = false, _mut21495 = false, _mut21496 = false, _mut21497 = false, _mut21498 = false, _mut21499 = false, _mut21500 = false, _mut21501 = false, _mut21502 = false, _mut21503 = false, _mut21504 = false, _mut21505 = false, _mut21506 = false, _mut21507 = false, _mut21508 = false, _mut21509 = false, _mut21510 = false, _mut21511 = false, _mut21512 = false, _mut21513 = false, _mut21514 = false, _mut21515 = false, _mut21516 = false, _mut21517 = false, _mut21518 = false, _mut21519 = false, _mut21520 = false, _mut21521 = false, _mut21522 = false, _mut21523 = false, _mut21524 = false, _mut21525 = false, _mut21526 = false, _mut21527 = false, _mut21528 = false, _mut21529 = false, _mut21530 = false, _mut21531 = false, _mut21532 = false, _mut21533 = false, _mut21534 = false, _mut21535 = false, _mut21536 = false, _mut21537 = false, _mut21538 = false, _mut21539 = false, _mut21540 = false, _mut21541 = false, _mut21542 = false, _mut21543 = false, _mut21544 = false, _mut21545 = false, _mut21546 = false, _mut21547 = false, _mut21548 = false, _mut21549 = false, _mut21550 = false, _mut21551 = false, _mut21552 = false, _mut21553 = false, _mut21554 = false, _mut21555 = false, _mut21556 = false, _mut21557 = false, _mut21558 = false, _mut21559 = false, _mut21560 = false, _mut21561 = false, _mut21562 = false, _mut21563 = false, _mut21564 = false, _mut21565 = false, _mut21566 = false, _mut21567 = false, _mut21568 = false, _mut21569 = false, _mut21570 = false, _mut21571 = false, _mut21572 = false, _mut21573 = false, _mut21574 = false, _mut21575 = false, _mut21576 = false, _mut21577 = false, _mut21578 = false, _mut21579 = false, _mut21580 = false, _mut21581 = false, _mut21582 = false, _mut21583 = false, _mut21584 = false, _mut21585 = false, _mut21586 = false, _mut21587 = false, _mut21588 = false, _mut21589 = false, _mut21590 = false, _mut21591 = false, _mut21592 = false, _mut21593 = false, _mut21594 = false, _mut21595 = false, _mut21596 = false, _mut21597 = false, _mut21598 = false, _mut21599 = false, _mut21600 = false, _mut21601 = false, _mut21602 = false, _mut21603 = false, _mut21604 = false, _mut21605 = false, _mut21606 = false, _mut21607 = false, _mut21608 = false, _mut21609 = false, _mut21610 = false, _mut21611 = false, _mut21612 = false, _mut21613 = false, _mut21614 = false, _mut21615 = false, _mut21616 = false, _mut21617 = false, _mut21618 = false, _mut21619 = false, _mut21620 = false, _mut21621 = false, _mut21622 = false, _mut21623 = false, _mut21624 = false, _mut21625 = false, _mut21626 = false, _mut21627 = false, _mut21628 = false, _mut21629 = false, _mut21630 = false, _mut21631 = false, _mut21632 = false, _mut21633 = false, _mut21634 = false, _mut21635 = false, _mut21636 = false, _mut21637 = false, _mut21638 = false, _mut21639 = false, _mut21640 = false, _mut21641 = false, _mut21642 = false, _mut21643 = false, _mut21644 = false, _mut21645 = false, _mut21646 = false, _mut21647 = false, _mut21648 = false, _mut21649 = false, _mut21650 = false, _mut21651 = false, _mut21652 = false, _mut21653 = false, _mut21654 = false, _mut21655 = false, _mut21656 = false, _mut21657 = false, _mut21658 = false, _mut21659 = false, _mut21660 = false, _mut21661 = false, _mut21662 = false, _mut21663 = false, _mut21664 = false, _mut21665 = false, _mut21666 = false, _mut21667 = false, _mut21668 = false, _mut21669 = false, _mut21670 = false, _mut21671 = false, _mut21672 = false, _mut21673 = false, _mut21674 = false, _mut21675 = false, _mut21676 = false, _mut21677 = false, _mut21678 = false, _mut21679 = false, _mut21680 = false, _mut21681 = false, _mut21682 = false, _mut21683 = false, _mut21684 = false, _mut21685 = false, _mut21686 = false, _mut21687 = false, _mut21688 = false, _mut21689 = false, _mut21690 = false, _mut21691 = false, _mut21692 = false, _mut21693 = false, _mut21694 = false, _mut21695 = false, _mut21696 = false, _mut21697 = false, _mut21698 = false, _mut21699 = false, _mut21700 = false, _mut21701 = false, _mut21702 = false, _mut21703 = false, _mut21704 = false, _mut21705 = false, _mut21706 = false, _mut21707 = false, _mut21708 = false, _mut21709 = false, _mut21710 = false, _mut21711 = false, _mut21712 = false, _mut21713 = false, _mut21714 = false, _mut21715 = false, _mut21716 = false, _mut21717 = false, _mut21718 = false, _mut21719 = false, _mut21720 = false, _mut21721 = false, _mut21722 = false, _mut21723 = false, _mut21724 = false, _mut21725 = false, _mut21726 = false, _mut21727 = false, _mut21728 = false, _mut21729 = false, _mut21730 = false, _mut21731 = false, _mut21732 = false, _mut21733 = false, _mut21734 = false, _mut21735 = false, _mut21736 = false, _mut21737 = false, _mut21738 = false, _mut21739 = false, _mut21740 = false, _mut21741 = false, _mut21742 = false, _mut21743 = false, _mut21744 = false, _mut21745 = false, _mut21746 = false, _mut21747 = false, _mut21748 = false, _mut21749 = false, _mut21750 = false, _mut21751 = false, _mut21752 = false, _mut21753 = false, _mut21754 = false, _mut21755 = false, _mut21756 = false, _mut21757 = false, _mut21758 = false, _mut21759 = false, _mut21760 = false, _mut21761 = false, _mut21762 = false, _mut21763 = false, _mut21764 = false, _mut21765 = false, _mut21766 = false, _mut21767 = false, _mut21768 = false, _mut21769 = false, _mut21770 = false, _mut21771 = false, _mut21772 = false, _mut21773 = false, _mut21774 = false, _mut21775 = false, _mut21776 = false, _mut21777 = false, _mut21778 = false, _mut21779 = false, _mut21780 = false, _mut21781 = false, _mut21782 = false, _mut21783 = false, _mut21784 = false, _mut21785 = false, _mut21786 = false, _mut21787 = false, _mut21788 = false, _mut21789 = false, _mut21790 = false, _mut21791 = false, _mut21792 = false, _mut21793 = false, _mut21794 = false, _mut21795 = false, _mut21796 = false, _mut21797 = false, _mut21798 = false, _mut21799 = false, _mut21800 = false, _mut21801 = false, _mut21802 = false, _mut21803 = false, _mut21804 = false, _mut21805 = false, _mut21806 = false, _mut21807 = false, _mut21808 = false, _mut21809 = false, _mut21810 = false, _mut21811 = false, _mut21812 = false, _mut21813 = false, _mut21814 = false, _mut21815 = false, _mut21816 = false, _mut21817 = false, _mut21818 = false, _mut21819 = false, _mut21820 = false, _mut21821 = false, _mut21822 = false, _mut21823 = false, _mut21824 = false, _mut21825 = false, _mut21826 = false, _mut21827 = false, _mut21828 = false, _mut21829 = false, _mut21830 = false, _mut21831 = false, _mut21832 = false, _mut21833 = false, _mut21834 = false, _mut21835 = false, _mut21836 = false, _mut21837 = false, _mut21838 = false, _mut21839 = false, _mut21840 = false, _mut21841 = false, _mut21842 = false, _mut21843 = false, _mut21844 = false, _mut21845 = false, _mut21846 = false, _mut21847 = false, _mut21848 = false, _mut21849 = false, _mut21850 = false, _mut21851 = false, _mut21852 = false, _mut21853 = false, _mut21854 = false, _mut21855 = false, _mut21856 = false, _mut21857 = false, _mut21858 = false, _mut21859 = false, _mut21860 = false, _mut21861 = false, _mut21862 = false, _mut21863 = false, _mut21864 = false, _mut21865 = false, _mut21866 = false, _mut21867 = false, _mut21868 = false, _mut21869 = false, _mut21870 = false, _mut21871 = false, _mut21872 = false, _mut21873 = false, _mut21874 = false, _mut21875 = false, _mut21876 = false, _mut21877 = false, _mut21878 = false, _mut21879 = false, _mut21880 = false, _mut21881 = false, _mut21882 = false, _mut21883 = false, _mut21884 = false, _mut21885 = false, _mut21886 = false, _mut21887 = false, _mut21888 = false, _mut21889 = false, _mut21890 = false, _mut21891 = false, _mut21892 = false, _mut21893 = false, _mut21894 = false, _mut21895 = false, _mut21896 = false, _mut21897 = false, _mut21898 = false, _mut21899 = false, _mut21900 = false, _mut21901 = false, _mut21902 = false, _mut21903 = false, _mut21904 = false, _mut21905 = false, _mut21906 = false, _mut21907 = false, _mut21908 = false, _mut21909 = false, _mut21910 = false, _mut21911 = false, _mut21912 = false, _mut21913 = false, _mut21914 = false, _mut21915 = false, _mut21916 = false, _mut21917 = false, _mut21918 = false, _mut21919 = false, _mut21920 = false, _mut21921 = false, _mut21922 = false, _mut21923 = false, _mut21924 = false, _mut21925 = false, _mut21926 = false, _mut21927 = false, _mut21928 = false, _mut21929 = false, _mut21930 = false, _mut21931 = false, _mut21932 = false, _mut21933 = false, _mut21934 = false, _mut21935 = false, _mut21936 = false, _mut21937 = false, _mut21938 = false, _mut21939 = false, _mut21940 = false, _mut21941 = false, _mut21942 = false, _mut21943 = false, _mut21944 = false, _mut21945 = false, _mut21946 = false, _mut21947 = false, _mut21948 = false, _mut21949 = false, _mut21950 = false, _mut21951 = false, _mut21952 = false, _mut21953 = false, _mut21954 = false, _mut21955 = false, _mut21956 = false, _mut21957 = false, _mut21958 = false, _mut21959 = false, _mut21960 = false, _mut21961 = false, _mut21962 = false, _mut21963 = false, _mut21964 = false, _mut21965 = false, _mut21966 = false, _mut21967 = false, _mut21968 = false, _mut21969 = false, _mut21970 = false, _mut21971 = false, _mut21972 = false, _mut21973 = false, _mut21974 = false, _mut21975 = false, _mut21976 = false, _mut21977 = false, _mut21978 = false, _mut21979 = false, _mut21980 = false, _mut21981 = false, _mut21982 = false, _mut21983 = false, _mut21984 = false, _mut21985 = false, _mut21986 = false, _mut21987 = false, _mut21988 = false, _mut21989 = false, _mut21990 = false, _mut21991 = false, _mut21992 = false, _mut21993 = false, _mut21994 = false, _mut21995 = false, _mut21996 = false, _mut21997 = false, _mut21998 = false, _mut21999 = false, _mut22000 = false, _mut22001 = false, _mut22002 = false, _mut22003 = false, _mut22004 = false, _mut22005 = false, _mut22006 = false, _mut22007 = false, _mut22008 = false, _mut22009 = false, _mut22010 = false, _mut22011 = false, _mut22012 = false, _mut22013 = false, _mut22014 = false, _mut22015 = false, _mut22016 = false, _mut22017 = false, _mut22018 = false, _mut22019 = false, _mut22020 = false, _mut22021 = false, _mut22022 = false, _mut22023 = false, _mut22024 = false, _mut22025 = false, _mut22026 = false, _mut22027 = false, _mut22028 = false, _mut22029 = false, _mut22030 = false, _mut22031 = false, _mut22032 = false, _mut22033 = false, _mut22034 = false, _mut22035 = false, _mut22036 = false, _mut22037 = false, _mut22038 = false, _mut22039 = false, _mut22040 = false, _mut22041 = false, _mut22042 = false, _mut22043 = false, _mut22044 = false, _mut22045 = false, _mut22046 = false, _mut22047 = false, _mut22048 = false, _mut22049 = false, _mut22050 = false, _mut22051 = false, _mut22052 = false, _mut22053 = false, _mut22054 = false, _mut22055 = false, _mut22056 = false, _mut22057 = false, _mut22058 = false, _mut22059 = false, _mut22060 = false, _mut22061 = false, _mut22062 = false, _mut22063 = false, _mut22064 = false, _mut22065 = false, _mut22066 = false, _mut22067 = false, _mut22068 = false, _mut22069 = false, _mut22070 = false, _mut22071 = false, _mut22072 = false, _mut22073 = false, _mut22074 = false, _mut22075 = false, _mut22076 = false, _mut22077 = false, _mut22078 = false, _mut22079 = false, _mut22080 = false, _mut22081 = false, _mut22082 = false, _mut22083 = false, _mut22084 = false, _mut22085 = false, _mut22086 = false, _mut22087 = false, _mut22088 = false, _mut22089 = false, _mut22090 = false, _mut22091 = false, _mut22092 = false, _mut22093 = false, _mut22094 = false, _mut22095 = false, _mut22096 = false, _mut22097 = false, _mut22098 = false, _mut22099 = false, _mut22100 = false, _mut22101 = false, _mut22102 = false, _mut22103 = false, _mut22104 = false, _mut22105 = false, _mut22106 = false, _mut22107 = false, _mut22108 = false, _mut22109 = false, _mut22110 = false, _mut22111 = false, _mut22112 = false, _mut22113 = false, _mut22114 = false, _mut22115 = false, _mut22116 = false, _mut22117 = false, _mut22118 = false, _mut22119 = false, _mut22120 = false, _mut22121 = false, _mut22122 = false, _mut22123 = false, _mut22124 = false, _mut22125 = false, _mut22126 = false, _mut22127 = false, _mut22128 = false, _mut22129 = false, _mut22130 = false, _mut22131 = false, _mut22132 = false, _mut22133 = false, _mut22134 = false, _mut22135 = false, _mut22136 = false, _mut22137 = false, _mut22138 = false, _mut22139 = false, _mut22140 = false, _mut22141 = false, _mut22142 = false, _mut22143 = false, _mut22144 = false, _mut22145 = false, _mut22146 = false, _mut22147 = false, _mut22148 = false, _mut22149 = false, _mut22150 = false, _mut22151 = false, _mut22152 = false, _mut22153 = false, _mut22154 = false, _mut22155 = false, _mut22156 = false, _mut22157 = false, _mut22158 = false, _mut22159 = false, _mut22160 = false, _mut22161 = false, _mut22162 = false, _mut22163 = false, _mut22164 = false, _mut22165 = false, _mut22166 = false, _mut22167 = false, _mut22168 = false, _mut22169 = false, _mut22170 = false, _mut22171 = false, _mut22172 = false, _mut22173 = false, _mut22174 = false, _mut22175 = false, _mut22176 = false, _mut22177 = false, _mut22178 = false, _mut22179 = false, _mut22180 = false, _mut22181 = false, _mut22182 = false, _mut22183 = false, _mut22184 = false, _mut22185 = false, _mut22186 = false, _mut22187 = false, _mut22188 = false, _mut22189 = false, _mut22190 = false, _mut22191 = false, _mut22192 = false, _mut22193 = false, _mut22194 = false, _mut22195 = false, _mut22196 = false, _mut22197 = false, _mut22198 = false, _mut22199 = false, _mut22200 = false, _mut22201 = false, _mut22202 = false, _mut22203 = false, _mut22204 = false, _mut22205 = false, _mut22206 = false, _mut22207 = false, _mut22208 = false, _mut22209 = false, _mut22210 = false, _mut22211 = false, _mut22212 = false, _mut22213 = false, _mut22214 = false, _mut22215 = false, _mut22216 = false, _mut22217 = false, _mut22218 = false, _mut22219 = false, _mut22220 = false, _mut22221 = false, _mut22222 = false, _mut22223 = false, _mut22224 = false, _mut22225 = false, _mut22226 = false, _mut22227 = false, _mut22228 = false, _mut22229 = false, _mut22230 = false, _mut22231 = false, _mut22232 = false, _mut22233 = false, _mut22234 = false, _mut22235 = false, _mut22236 = false, _mut22237 = false, _mut22238 = false, _mut22239 = false, _mut22240 = false, _mut22241 = false, _mut22242 = false, _mut22243 = false, _mut22244 = false, _mut22245 = false, _mut22246 = false, _mut22247 = false, _mut22248 = false, _mut22249 = false, _mut22250 = false, _mut22251 = false, _mut22252 = false, _mut22253 = false, _mut22254 = false, _mut22255 = false, _mut22256 = false, _mut22257 = false, _mut22258 = false, _mut22259 = false, _mut22260 = false, _mut22261 = false, _mut22262 = false, _mut22263 = false, _mut22264 = false, _mut22265 = false, _mut22266 = false, _mut22267 = false, _mut22268 = false, _mut22269 = false, _mut22270 = false, _mut22271 = false, _mut22272 = false, _mut22273 = false, _mut22274 = false, _mut22275 = false, _mut22276 = false, _mut22277 = false, _mut22278 = false, _mut22279 = false, _mut22280 = false, _mut22281 = false, _mut22282 = false, _mut22283 = false, _mut22284 = false, _mut22285 = false, _mut22286 = false, _mut22287 = false, _mut22288 = false, _mut22289 = false, _mut22290 = false, _mut22291 = false, _mut22292 = false, _mut22293 = false, _mut22294 = false, _mut22295 = false, _mut22296 = false, _mut22297 = false, _mut22298 = false, _mut22299 = false, _mut22300 = false, _mut22301 = false, _mut22302 = false, _mut22303 = false, _mut22304 = false, _mut22305 = false, _mut22306 = false, _mut22307 = false, _mut22308 = false, _mut22309 = false, _mut22310 = false, _mut22311 = false, _mut22312 = false, _mut22313 = false, _mut22314 = false, _mut22315 = false, _mut22316 = false, _mut22317 = false, _mut22318 = false, _mut22319 = false, _mut22320 = false, _mut22321 = false, _mut22322 = false, _mut22323 = false, _mut22324 = false, _mut22325 = false, _mut22326 = false, _mut22327 = false, _mut22328 = false, _mut22329 = false, _mut22330 = false, _mut22331 = false, _mut22332 = false, _mut22333 = false, _mut22334 = false, _mut22335 = false, _mut22336 = false, _mut22337 = false, _mut22338 = false, _mut22339 = false, _mut22340 = false, _mut22341 = false, _mut22342 = false, _mut22343 = false, _mut22344 = false, _mut22345 = false, _mut22346 = false, _mut22347 = false, _mut22348 = false, _mut22349 = false, _mut22350 = false, _mut22351 = false, _mut22352 = false, _mut22353 = false, _mut22354 = false, _mut22355 = false, _mut22356 = false, _mut22357 = false, _mut22358 = false, _mut22359 = false, _mut22360 = false, _mut22361 = false, _mut22362 = false, _mut22363 = false, _mut22364 = false, _mut22365 = false, _mut22366 = false, _mut22367 = false, _mut22368 = false, _mut22369 = false, _mut22370 = false, _mut22371 = false;

    /**
     * Integrator method name.
     */
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";

    /**
     * maximal order.
     */
    private int maxOrder;

    /**
     * step size sequence.
     */
    private int[] sequence;

    /**
     * overall cost of applying step reduction up to iteration k+1, in number of calls.
     */
    private int[] costPerStep;

    /**
     * cost per unit step.
     */
    private double[] costPerTimeUnit;

    /**
     * optimal steps for each order.
     */
    private double[] optimalStep;

    /**
     * extrapolation coefficients.
     */
    private double[][] coeff;

    /**
     * stability check enabling parameter.
     */
    private boolean performTest;

    /**
     * maximal number of checks for each iteration.
     */
    private int maxChecks;

    /**
     * maximal number of iterations for which checks are performed.
     */
    private int maxIter;

    /**
     * stepsize reduction factor in case of stability check failure.
     */
    private double stabilityReduction;

    /**
     * first stepsize control factor.
     */
    private double stepControl1;

    /**
     * second stepsize control factor.
     */
    private double stepControl2;

    /**
     * third stepsize control factor.
     */
    private double stepControl3;

    /**
     * fourth stepsize control factor.
     */
    private double stepControl4;

    /**
     * first order control factor.
     */
    private double orderControl1;

    /**
     * second order control factor.
     */
    private double orderControl2;

    /**
     * use interpolation error in stepsize control.
     */
    private boolean useInterpolationError;

    /**
     * interpolation order control parameter.
     */
    private int mudif;

    /**
     * Simple constructor.
     * Build a Gragg-Bulirsch-Stoer integrator with the given step
     * bounds. All tuning parameters are set to their default
     * values. The default step handler does nothing.
     * @param minStep minimal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param maxStep maximal step (sign is irrelevant, regardless of
     * integration direction, forward or backward), the last step can
     * be smaller than this
     * @param scalAbsoluteTolerance allowed absolute error
     * @param scalRelativeTolerance allowed relative error
     */
    public GraggBulirschStoerIntegrator(final double minStep, final double maxStep, final double scalAbsoluteTolerance, final double scalRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1);
        setControlFactors(-1, -1, -1, -1);
        setOrderControl(-1, -1, -1);
        setInterpolationControl(true, -1);
    }

    /**
     * Simple constructor.
     * Build a Gragg-Bulirsch-Stoer integrator with the given step
     * bounds. All tuning parameters are set to their default
     * values. The default step handler does nothing.
     * @param minStep minimal step (must be positive even for backward
     * integration), the last step can be smaller than this
     * @param maxStep maximal step (must be positive even for backward
     * integration)
     * @param vecAbsoluteTolerance allowed absolute error
     * @param vecRelativeTolerance allowed relative error
     */
    public GraggBulirschStoerIntegrator(final double minStep, final double maxStep, final double[] vecAbsoluteTolerance, final double[] vecRelativeTolerance) {
        super(METHOD_NAME, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        setStabilityCheck(true, -1, -1, -1);
        setControlFactors(-1, -1, -1, -1);
        setOrderControl(-1, -1, -1);
        setInterpolationControl(true, -1);
    }

    /**
     * Set the stability check controls.
     * <p>The stability check is performed on the first few iterations of
     * the extrapolation scheme. If this test fails, the step is rejected
     * and the stepsize is reduced.</p>
     * <p>By default, the test is performed, at most during two
     * iterations at each step, and at most once for each of these
     * iterations. The default stepsize reduction factor is 0.5.</p>
     * @param performStabilityCheck if true, stability check will be performed,
     *     if false, the check will be skipped
     * @param maxNumIter maximal number of iterations for which checks are
     * performed (the number of iterations is reset to default if negative
     * or null)
     * @param maxNumChecks maximal number of checks for each iteration
     * (the number of checks is reset to default if negative or null)
     * @param stepsizeReductionFactor stepsize reduction factor in case of
     * failure (the factor is reset to default if lower than 0.0001 or
     * greater than 0.9999)
     */
    public void setStabilityCheck(final boolean performStabilityCheck, final int maxNumIter, final int maxNumChecks, final double stepsizeReductionFactor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219");
        this.performTest = performStabilityCheck;
        this.maxIter = (ROR_less_equals(maxNumIter, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21293, _mut21294, _mut21295, _mut21296, _mut21297)) ? 2 : maxNumIter;
        this.maxChecks = (ROR_less_equals(maxNumChecks, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21298, _mut21299, _mut21300, _mut21301, _mut21302)) ? 1 : maxNumChecks;
        if ((_mut21313 ? ((ROR_less(stepsizeReductionFactor, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21303, _mut21304, _mut21305, _mut21306, _mut21307)) && (ROR_greater(stepsizeReductionFactor, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21308, _mut21309, _mut21310, _mut21311, _mut21312))) : ((ROR_less(stepsizeReductionFactor, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21303, _mut21304, _mut21305, _mut21306, _mut21307)) || (ROR_greater(stepsizeReductionFactor, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setStabilityCheck_219", _mut21308, _mut21309, _mut21310, _mut21311, _mut21312))))) {
            this.stabilityReduction = 0.5;
        } else {
            this.stabilityReduction = stepsizeReductionFactor;
        }
    }

    /**
     * Set the step size control factors.
     *
     * <p>The new step size hNew is computed from the old one h by:
     * <pre>
     * hNew = h * stepControl2 / (err/stepControl1)^(1/(2k+1))
     * </pre>
     * where err is the scaled error and k the iteration number of the
     * extrapolation scheme (counting from 0). The default values are
     * 0.65 for stepControl1 and 0.94 for stepControl2.</p>
     * <p>The step size is subject to the restriction:
     * <pre>
     * stepControl3^(1/(2k+1))/stepControl4 <= hNew/h <= 1/stepControl3^(1/(2k+1))
     * </pre>
     * The default values are 0.02 for stepControl3 and 4.0 for
     * stepControl4.</p>
     * @param control1 first stepsize control factor (the factor is
     * reset to default if lower than 0.0001 or greater than 0.9999)
     * @param control2 second stepsize control factor (the factor
     * is reset to default if lower than 0.0001 or greater than 0.9999)
     * @param control3 third stepsize control factor (the factor is
     * reset to default if lower than 0.0001 or greater than 0.9999)
     * @param control4 fourth stepsize control factor (the factor
     * is reset to default if lower than 1.0001 or greater than 999.9)
     */
    public void setControlFactors(final double control1, final double control2, final double control3, final double control4) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259");
        if ((_mut21324 ? ((ROR_less(control1, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21314, _mut21315, _mut21316, _mut21317, _mut21318)) && (ROR_greater(control1, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21319, _mut21320, _mut21321, _mut21322, _mut21323))) : ((ROR_less(control1, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21314, _mut21315, _mut21316, _mut21317, _mut21318)) || (ROR_greater(control1, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21319, _mut21320, _mut21321, _mut21322, _mut21323))))) {
            this.stepControl1 = 0.65;
        } else {
            this.stepControl1 = control1;
        }
        if ((_mut21335 ? ((ROR_less(control2, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21325, _mut21326, _mut21327, _mut21328, _mut21329)) && (ROR_greater(control2, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21330, _mut21331, _mut21332, _mut21333, _mut21334))) : ((ROR_less(control2, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21325, _mut21326, _mut21327, _mut21328, _mut21329)) || (ROR_greater(control2, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21330, _mut21331, _mut21332, _mut21333, _mut21334))))) {
            this.stepControl2 = 0.94;
        } else {
            this.stepControl2 = control2;
        }
        if ((_mut21346 ? ((ROR_less(control3, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21336, _mut21337, _mut21338, _mut21339, _mut21340)) && (ROR_greater(control3, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21341, _mut21342, _mut21343, _mut21344, _mut21345))) : ((ROR_less(control3, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21336, _mut21337, _mut21338, _mut21339, _mut21340)) || (ROR_greater(control3, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21341, _mut21342, _mut21343, _mut21344, _mut21345))))) {
            this.stepControl3 = 0.02;
        } else {
            this.stepControl3 = control3;
        }
        if ((_mut21357 ? ((ROR_less(control4, 1.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21347, _mut21348, _mut21349, _mut21350, _mut21351)) && (ROR_greater(control4, 999.9, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21352, _mut21353, _mut21354, _mut21355, _mut21356))) : ((ROR_less(control4, 1.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21347, _mut21348, _mut21349, _mut21350, _mut21351)) || (ROR_greater(control4, 999.9, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setControlFactors_259", _mut21352, _mut21353, _mut21354, _mut21355, _mut21356))))) {
            this.stepControl4 = 4.0;
        } else {
            this.stepControl4 = control4;
        }
    }

    /**
     * Set the order control parameters.
     * <p>The Gragg-Bulirsch-Stoer method changes both the step size and
     * the order during integration, in order to minimize computation
     * cost. Each extrapolation step increases the order by 2, so the
     * maximal order that will be used is always even, it is twice the
     * maximal number of columns in the extrapolation table.</p>
     * <pre>
     * order is decreased if w(k-1) <= w(k)   * orderControl1
     * order is increased if w(k)   <= w(k-1) * orderControl2
     * </pre>
     * <p>where w is the table of work per unit step for each order
     * (number of function calls divided by the step length), and k is
     * the current order.</p>
     * <p>The default maximal order after construction is 18 (i.e. the
     * maximal number of columns is 9). The default values are 0.8 for
     * orderControl1 and 0.9 for orderControl2.</p>
     * @param maximalOrder maximal order in the extrapolation table (the
     * maximal order is reset to default if order <= 6 or odd)
     * @param control1 first order control factor (the factor is
     * reset to default if lower than 0.0001 or greater than 0.9999)
     * @param control2 second order control factor (the factor
     * is reset to default if lower than 0.0001 or greater than 0.9999)
     */
    public void setOrderControl(final int maximalOrder, final double control1, final double control2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311");
        if ((_mut21372 ? ((ROR_less_equals(maximalOrder, 6, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21358, _mut21359, _mut21360, _mut21361, _mut21362)) && (ROR_not_equals(AOR_remainder(maximalOrder, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21363, _mut21364, _mut21365, _mut21366), 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21367, _mut21368, _mut21369, _mut21370, _mut21371))) : ((ROR_less_equals(maximalOrder, 6, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21358, _mut21359, _mut21360, _mut21361, _mut21362)) || (ROR_not_equals(AOR_remainder(maximalOrder, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21363, _mut21364, _mut21365, _mut21366), 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21367, _mut21368, _mut21369, _mut21370, _mut21371))))) {
            this.maxOrder = 18;
        }
        if ((_mut21383 ? ((ROR_less(control1, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21373, _mut21374, _mut21375, _mut21376, _mut21377)) && (ROR_greater(control1, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21378, _mut21379, _mut21380, _mut21381, _mut21382))) : ((ROR_less(control1, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21373, _mut21374, _mut21375, _mut21376, _mut21377)) || (ROR_greater(control1, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21378, _mut21379, _mut21380, _mut21381, _mut21382))))) {
            this.orderControl1 = 0.8;
        } else {
            this.orderControl1 = control1;
        }
        if ((_mut21394 ? ((ROR_less(control2, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21384, _mut21385, _mut21386, _mut21387, _mut21388)) && (ROR_greater(control2, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21389, _mut21390, _mut21391, _mut21392, _mut21393))) : ((ROR_less(control2, 0.0001, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21384, _mut21385, _mut21386, _mut21387, _mut21388)) || (ROR_greater(control2, 0.9999, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setOrderControl_311", _mut21389, _mut21390, _mut21391, _mut21392, _mut21393))))) {
            this.orderControl2 = 0.9;
        } else {
            this.orderControl2 = control2;
        }
        // reinitialize the arrays
        initializeArrays();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStepHandler(final StepHandler handler) {
        super.addStepHandler(handler);
        // reinitialize the arrays
        initializeArrays();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEventHandler(final EventHandler function, final double maxCheckInterval, final double convergence, final int maxIterationCount, final UnivariateSolver solver) {
        super.addEventHandler(function, maxCheckInterval, convergence, maxIterationCount, solver);
        // reinitialize the arrays
        initializeArrays();
    }

    /**
     * Initialize the integrator internal arrays.
     */
    private void initializeArrays() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362");
        final int size = AOR_divide(maxOrder, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21395, _mut21396, _mut21397, _mut21398);
        if ((_mut21404 ? ((sequence == null) && (ROR_not_equals(sequence.length, size, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21399, _mut21400, _mut21401, _mut21402, _mut21403))) : ((sequence == null) || (ROR_not_equals(sequence.length, size, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21399, _mut21400, _mut21401, _mut21402, _mut21403))))) {
            // all arrays should be reallocated with the right size
            sequence = new int[size];
            costPerStep = new int[size];
            coeff = new double[size][];
            costPerTimeUnit = new double[size];
            optimalStep = new double[size];
        }
        // step size sequence: 2, 6, 10, 14, ...
        for (int k = 0; ROR_less(k, size, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21413, _mut21414, _mut21415, _mut21416, _mut21417); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362");
            sequence[k] = AOR_plus(AOR_multiply(4, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21405, _mut21406, _mut21407, _mut21408), 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21409, _mut21410, _mut21411, _mut21412);
        }
        // (number of function calls for each column of the extrapolation table)
        costPerStep[0] = AOR_plus(sequence[0], 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21418, _mut21419, _mut21420, _mut21421);
        for (int k = 1; ROR_less(k, size, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21430, _mut21431, _mut21432, _mut21433, _mut21434); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362");
            costPerStep[k] = AOR_plus(costPerStep[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21422, _mut21423, _mut21424, _mut21425)], sequence[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21426, _mut21427, _mut21428, _mut21429);
        }
        // initialize the extrapolation tables
        for (int k = 0; ROR_less(k, size, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21469, _mut21470, _mut21471, _mut21472, _mut21473); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362");
            coeff[k] = (ROR_greater(k, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21435, _mut21436, _mut21437, _mut21438, _mut21439)) ? new double[k] : null;
            for (int l = 0; ROR_less(l, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21464, _mut21465, _mut21466, _mut21467, _mut21468); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362");
                final double ratio = AOR_divide(((double) sequence[k]), sequence[AOR_minus(AOR_minus(k, l, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21440, _mut21441, _mut21442, _mut21443), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21444, _mut21445, _mut21446, _mut21447)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21448, _mut21449, _mut21450, _mut21451);
                coeff[k][l] = AOR_divide(1.0, (AOR_minus(AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21452, _mut21453, _mut21454, _mut21455), 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21456, _mut21457, _mut21458, _mut21459)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.initializeArrays_362", _mut21460, _mut21461, _mut21462, _mut21463);
            }
        }
    }

    /**
     * Set the interpolation order control parameter.
     * The interpolation order for dense output is 2k - mudif + 1. The
     * default value for mudif is 4 and the interpolation error is used
     * in stepsize control by default.
     *
     * @param useInterpolationErrorForControl if true, interpolation error is used
     * for stepsize control
     * @param mudifControlParameter interpolation order control parameter (the parameter
     * is reset to default if <= 0 or >= 7)
     */
    public void setInterpolationControl(final boolean useInterpolationErrorForControl, final int mudifControlParameter) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setInterpolationControl_408");
        this.useInterpolationError = useInterpolationErrorForControl;
        if ((_mut21484 ? ((ROR_less_equals(mudifControlParameter, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setInterpolationControl_408", _mut21474, _mut21475, _mut21476, _mut21477, _mut21478)) && (ROR_greater_equals(mudifControlParameter, 7, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setInterpolationControl_408", _mut21479, _mut21480, _mut21481, _mut21482, _mut21483))) : ((ROR_less_equals(mudifControlParameter, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setInterpolationControl_408", _mut21474, _mut21475, _mut21476, _mut21477, _mut21478)) || (ROR_greater_equals(mudifControlParameter, 7, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.setInterpolationControl_408", _mut21479, _mut21480, _mut21481, _mut21482, _mut21483))))) {
            this.mudif = 4;
        } else {
            this.mudif = mudifControlParameter;
        }
    }

    /**
     * Update scaling array.
     * @param y1 first state vector to use for scaling
     * @param y2 second state vector to use for scaling
     * @param scale scaling array to update (can be shorter than state)
     */
    private void rescale(final double[] y1, final double[] y2, final double[] scale) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426");
        if (vecAbsoluteTolerance == null) {
            for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21506, _mut21507, _mut21508, _mut21509, _mut21510); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426");
                final double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
                scale[i] = AOR_plus(scalAbsoluteTolerance, AOR_multiply(scalRelativeTolerance, yi, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21498, _mut21499, _mut21500, _mut21501), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21502, _mut21503, _mut21504, _mut21505);
            }
        } else {
            for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21493, _mut21494, _mut21495, _mut21496, _mut21497); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426");
                final double yi = FastMath.max(FastMath.abs(y1[i]), FastMath.abs(y2[i]));
                scale[i] = AOR_plus(vecAbsoluteTolerance[i], AOR_multiply(vecRelativeTolerance[i], yi, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21485, _mut21486, _mut21487, _mut21488), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.rescale_426", _mut21489, _mut21490, _mut21491, _mut21492);
            }
        }
    }

    /**
     * Perform integration over one step using substeps of a modified
     * midpoint method.
     * @param t0 initial time
     * @param y0 initial value of the state vector at t0
     * @param step global step
     * @param k iteration number (from 0 to sequence.length - 1)
     * @param scale scaling array (can be shorter than state)
     * @param f placeholder where to put the state vector derivatives at each substep
     *          (element 0 already contains initial derivative)
     * @param yMiddle placeholder where to put the state vector at the middle of the step
     * @param yEnd placeholder where to put the state vector at the end
     * @param yTmp placeholder for one state vector
     * @return true if computation was done properly,
     *         false if stability check failed before end of computation
     * @exception MaxCountExceededException if the number of functions evaluations is exceeded
     * @exception DimensionMismatchException if arrays dimensions do not match equations settings
     */
    private boolean tryStep(final double t0, final double[] y0, final double step, final int k, final double[] scale, final double[][] f, final double[] yMiddle, final double[] yEnd, final double[] yTmp) throws MaxCountExceededException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
        final int n = sequence[k];
        final double subStep = AOR_divide(step, n, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21511, _mut21512, _mut21513, _mut21514);
        final double subStep2 = AOR_multiply(2, subStep, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21515, _mut21516, _mut21517, _mut21518);
        // first substep
        double t = AOR_plus(t0, subStep, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21519, _mut21520, _mut21521, _mut21522);
        for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21531, _mut21532, _mut21533, _mut21534, _mut21535); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
            yTmp[i] = y0[i];
            yEnd[i] = AOR_plus(y0[i], AOR_multiply(subStep, f[0][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21523, _mut21524, _mut21525, _mut21526), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21527, _mut21528, _mut21529, _mut21530);
        }
        computeDerivatives(t, yEnd, f[1]);
        // other substeps
        for (int j = 1; ROR_less(j, n, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21617, _mut21618, _mut21619, _mut21620, _mut21621); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
            if (ROR_equals(AOR_multiply(2, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21536, _mut21537, _mut21538, _mut21539), n, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21540, _mut21541, _mut21542, _mut21543, _mut21544)) {
                // save the point at the middle of the step
                System.arraycopy(yEnd, 0, yMiddle, 0, y0.length);
            }
            t += subStep;
            for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21553, _mut21554, _mut21555, _mut21556, _mut21557); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
                final double middle = yEnd[i];
                yEnd[i] = AOR_plus(yTmp[i], AOR_multiply(subStep2, f[j][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21545, _mut21546, _mut21547, _mut21548), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21549, _mut21550, _mut21551, _mut21552);
                yTmp[i] = middle;
            }
            computeDerivatives(t, yEnd, f[AOR_plus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21558, _mut21559, _mut21560, _mut21561)]);
            // stability check
            if ((_mut21573 ? ((_mut21567 ? (performTest || (ROR_less_equals(j, maxChecks, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21562, _mut21563, _mut21564, _mut21565, _mut21566))) : (performTest && (ROR_less_equals(j, maxChecks, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21562, _mut21563, _mut21564, _mut21565, _mut21566)))) || (ROR_less(k, maxIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21568, _mut21569, _mut21570, _mut21571, _mut21572))) : ((_mut21567 ? (performTest || (ROR_less_equals(j, maxChecks, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21562, _mut21563, _mut21564, _mut21565, _mut21566))) : (performTest && (ROR_less_equals(j, maxChecks, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21562, _mut21563, _mut21564, _mut21565, _mut21566)))) && (ROR_less(k, maxIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21568, _mut21569, _mut21570, _mut21571, _mut21572))))) {
                double initialNorm = 0.0;
                for (int l = 0; ROR_less(l, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21582, _mut21583, _mut21584, _mut21585, _mut21586); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
                    final double ratio = AOR_divide(f[0][l], scale[l], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21574, _mut21575, _mut21576, _mut21577);
                    initialNorm += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21578, _mut21579, _mut21580, _mut21581);
                }
                double deltaNorm = 0.0;
                for (int l = 0; ROR_less(l, scale.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21603, _mut21604, _mut21605, _mut21606, _mut21607); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
                    final double ratio = AOR_divide((AOR_minus(f[AOR_plus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21587, _mut21588, _mut21589, _mut21590)][l], f[0][l], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21591, _mut21592, _mut21593, _mut21594)), scale[l], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21595, _mut21596, _mut21597, _mut21598);
                    deltaNorm += AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21599, _mut21600, _mut21601, _mut21602);
                }
                if (ROR_greater(deltaNorm, AOR_multiply(4, FastMath.max(1.0e-15, initialNorm), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21608, _mut21609, _mut21610, _mut21611), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21612, _mut21613, _mut21614, _mut21615, _mut21616)) {
                    return false;
                }
            }
        }
        // correction of the last substep (at t0 + step)
        for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21638, _mut21639, _mut21640, _mut21641, _mut21642); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457");
            yEnd[i] = AOR_multiply(0.5, (AOR_plus(AOR_plus(yTmp[i], yEnd[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21622, _mut21623, _mut21624, _mut21625), AOR_multiply(subStep, f[n][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21626, _mut21627, _mut21628, _mut21629), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21630, _mut21631, _mut21632, _mut21633)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.tryStep_457", _mut21634, _mut21635, _mut21636, _mut21637);
        }
        return true;
    }

    /**
     * Extrapolate a vector.
     * @param offset offset to use in the coefficients table
     * @param k index of the last updated point
     * @param diag working diagonal of the Aitken-Neville's
     * triangle, without the last element
     * @param last last element
     */
    private void extrapolate(final int offset, final int k, final double[][] diag, final double[] last) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527");
        // update the diagonal
        for (int j = 1; ROR_less(j, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21692, _mut21693, _mut21694, _mut21695, _mut21696); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527");
            for (int i = 0; ROR_less(i, last.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21687, _mut21688, _mut21689, _mut21690, _mut21691); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527");
                // Aitken-Neville's recursive formula
                diag[AOR_minus(AOR_minus(k, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21643, _mut21644, _mut21645, _mut21646), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21647, _mut21648, _mut21649, _mut21650)][i] = AOR_plus(diag[AOR_minus(k, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21651, _mut21652, _mut21653, _mut21654)][i], AOR_multiply(coeff[AOR_plus(k, offset, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21659, _mut21660, _mut21661, _mut21662)][AOR_minus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21655, _mut21656, _mut21657, _mut21658)], (AOR_minus(diag[AOR_minus(k, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21663, _mut21664, _mut21665, _mut21666)][i], diag[AOR_minus(AOR_minus(k, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21667, _mut21668, _mut21669, _mut21670), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21671, _mut21672, _mut21673, _mut21674)][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21675, _mut21676, _mut21677, _mut21678)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21679, _mut21680, _mut21681, _mut21682), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21683, _mut21684, _mut21685, _mut21686);
            }
        }
        // update the last element
        for (int i = 0; ROR_less(i, last.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21717, _mut21718, _mut21719, _mut21720, _mut21721); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527");
            // Aitken-Neville's recursive formula
            last[i] = AOR_plus(diag[0][i], AOR_multiply(coeff[AOR_plus(k, offset, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21701, _mut21702, _mut21703, _mut21704)][AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21697, _mut21698, _mut21699, _mut21700)], (AOR_minus(diag[0][i], last[i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21705, _mut21706, _mut21707, _mut21708)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21709, _mut21710, _mut21711, _mut21712), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.extrapolate_527", _mut21713, _mut21714, _mut21715, _mut21716);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void integrate(final ExpandableStatefulODE equations, final double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
        sanityChecks(equations, t);
        setEquations(equations);
        final boolean forward = ROR_greater(t, equations.getTime(), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21722, _mut21723, _mut21724, _mut21725, _mut21726);
        // create some internal working arrays
        final double[] y0 = equations.getCompleteState();
        final double[] y = y0.clone();
        final double[] yDot0 = new double[y.length];
        final double[] y1 = new double[y.length];
        final double[] yTmp = new double[y.length];
        final double[] yTmpDot = new double[y.length];
        final double[][] diagonal = new double[AOR_minus(sequence.length, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21727, _mut21728, _mut21729, _mut21730)][];
        final double[][] y1Diag = new double[AOR_minus(sequence.length, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21731, _mut21732, _mut21733, _mut21734)][];
        for (int k = 0; ROR_less(k, AOR_minus(sequence.length, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21735, _mut21736, _mut21737, _mut21738), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21739, _mut21740, _mut21741, _mut21742, _mut21743); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
            diagonal[k] = new double[y.length];
            y1Diag[k] = new double[y.length];
        }
        final double[][][] fk = new double[sequence.length][][];
        for (int k = 0; ROR_less(k, sequence.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21757, _mut21758, _mut21759, _mut21760, _mut21761); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
            fk[k] = new double[AOR_plus(sequence[k], 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21744, _mut21745, _mut21746, _mut21747)][];
            // all substeps start at the same point, so share the first array
            fk[k][0] = yDot0;
            for (int l = 0; ROR_less(l, sequence[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21752, _mut21753, _mut21754, _mut21755, _mut21756); ++l) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                fk[k][AOR_plus(l, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21748, _mut21749, _mut21750, _mut21751)] = new double[y0.length];
            }
        }
        if (y != y0) {
            System.arraycopy(y0, 0, y, 0, y0.length);
        }
        final double[] yDot1 = new double[y0.length];
        final double[][] yMidDots = new double[AOR_plus(1, AOR_multiply(2, sequence.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21762, _mut21763, _mut21764, _mut21765), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21766, _mut21767, _mut21768, _mut21769)][y0.length];
        // initial scaling
        final double[] scale = new double[mainSetDimension];
        rescale(y, y, scale);
        // initial order selection
        final double tol = (vecRelativeTolerance == null) ? scalRelativeTolerance : vecRelativeTolerance[0];
        final double log10R = FastMath.log10(FastMath.max(1.0e-10, tol));
        int targetIter = FastMath.max(1, FastMath.min(AOR_minus(sequence.length, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21770, _mut21771, _mut21772, _mut21773), (int) FastMath.floor(AOR_minus(0.5, AOR_multiply(0.6, log10R, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21774, _mut21775, _mut21776, _mut21777), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21778, _mut21779, _mut21780, _mut21781))));
        // set up an interpolator sharing the integrator arrays
        final AbstractStepInterpolator interpolator = new GraggBulirschStoerStepInterpolator(y, yDot0, y1, yDot1, yMidDots, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        interpolator.storeTime(equations.getTime());
        stepStart = equations.getTime();
        double hNew = 0;
        double maxError = Double.MAX_VALUE;
        boolean previousRejected = false;
        boolean firstTime = true;
        boolean newStep = true;
        boolean firstStepAlreadyComputed = false;
        initIntegration(equations.getTime(), y0, t);
        costPerTimeUnit[0] = 0;
        isLastStep = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
            double error;
            boolean reject = false;
            if (newStep) {
                interpolator.shift();
                // first evaluation, at the beginning of the step
                if (!firstStepAlreadyComputed) {
                    computeDerivatives(stepStart, y, yDot0);
                }
                if (firstTime) {
                    hNew = initializeStep(forward, AOR_plus(AOR_multiply(2, targetIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21782, _mut21783, _mut21784, _mut21785), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21786, _mut21787, _mut21788, _mut21789), scale, stepStart, y, yDot0, yTmp, yTmpDot);
                }
                newStep = false;
            }
            stepSize = hNew;
            // step adjustment near bounds
            if ((_mut21810 ? (((_mut21799 ? (forward || (ROR_greater(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21790, _mut21791, _mut21792, _mut21793), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21794, _mut21795, _mut21796, _mut21797, _mut21798))) : (forward && (ROR_greater(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21790, _mut21791, _mut21792, _mut21793), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21794, _mut21795, _mut21796, _mut21797, _mut21798))))) && ((_mut21809 ? ((!forward) || (ROR_less(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21800, _mut21801, _mut21802, _mut21803), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21804, _mut21805, _mut21806, _mut21807, _mut21808))) : ((!forward) && (ROR_less(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21800, _mut21801, _mut21802, _mut21803), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21804, _mut21805, _mut21806, _mut21807, _mut21808)))))) : (((_mut21799 ? (forward || (ROR_greater(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21790, _mut21791, _mut21792, _mut21793), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21794, _mut21795, _mut21796, _mut21797, _mut21798))) : (forward && (ROR_greater(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21790, _mut21791, _mut21792, _mut21793), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21794, _mut21795, _mut21796, _mut21797, _mut21798))))) || ((_mut21809 ? ((!forward) || (ROR_less(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21800, _mut21801, _mut21802, _mut21803), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21804, _mut21805, _mut21806, _mut21807, _mut21808))) : ((!forward) && (ROR_less(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21800, _mut21801, _mut21802, _mut21803), t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21804, _mut21805, _mut21806, _mut21807, _mut21808)))))))) {
                stepSize = AOR_minus(t, stepStart, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21811, _mut21812, _mut21813, _mut21814);
            }
            final double nextT = AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21815, _mut21816, _mut21817, _mut21818);
            isLastStep = forward ? (ROR_greater_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21824, _mut21825, _mut21826, _mut21827, _mut21828)) : (ROR_less_equals(nextT, t, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21819, _mut21820, _mut21821, _mut21822, _mut21823));
            // iterate over several substep sizes
            int k = -1;
            for (boolean loop = true; loop; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                ++k;
                // modified midpoint integration with the current substep
                if (!tryStep(stepStart, y, stepSize, k, scale, fk[k], (ROR_equals(k, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21829, _mut21830, _mut21831, _mut21832, _mut21833)) ? yMidDots[0] : diagonal[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21834, _mut21835, _mut21836, _mut21837)], (ROR_equals(k, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21838, _mut21839, _mut21840, _mut21841, _mut21842)) ? y1 : y1Diag[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21843, _mut21844, _mut21845, _mut21846)], yTmp)) {
                    // the stability check failed, we reduce the global step
                    hNew = FastMath.abs(filterStep(AOR_multiply(stepSize, stabilityReduction, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22065, _mut22066, _mut22067, _mut22068), forward, false));
                    reject = true;
                    loop = false;
                } else {
                    // the substep was computed successfully
                    if (ROR_greater(k, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21847, _mut21848, _mut21849, _mut21850, _mut21851)) {
                        // using last iteration data
                        extrapolate(0, k, y1Diag, y1);
                        rescale(y, y1, scale);
                        // estimate the error at the end of the step.
                        error = 0;
                        for (int j = 0; ROR_less(j, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21864, _mut21865, _mut21866, _mut21867, _mut21868); ++j) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                            final double e = AOR_divide(FastMath.abs(AOR_minus(y1[j], y1Diag[0][j], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21852, _mut21853, _mut21854, _mut21855)), scale[j], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21856, _mut21857, _mut21858, _mut21859);
                            error += AOR_multiply(e, e, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21860, _mut21861, _mut21862, _mut21863);
                        }
                        error = FastMath.sqrt(AOR_divide(error, mainSetDimension, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21869, _mut21870, _mut21871, _mut21872));
                        if ((_mut21889 ? ((ROR_greater(error, 1.0e15, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21873, _mut21874, _mut21875, _mut21876, _mut21877)) && ((_mut21888 ? ((ROR_greater(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21878, _mut21879, _mut21880, _mut21881, _mut21882)) || (ROR_greater(error, maxError, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21883, _mut21884, _mut21885, _mut21886, _mut21887))) : ((ROR_greater(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21878, _mut21879, _mut21880, _mut21881, _mut21882)) && (ROR_greater(error, maxError, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21883, _mut21884, _mut21885, _mut21886, _mut21887)))))) : ((ROR_greater(error, 1.0e15, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21873, _mut21874, _mut21875, _mut21876, _mut21877)) || ((_mut21888 ? ((ROR_greater(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21878, _mut21879, _mut21880, _mut21881, _mut21882)) || (ROR_greater(error, maxError, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21883, _mut21884, _mut21885, _mut21886, _mut21887))) : ((ROR_greater(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21878, _mut21879, _mut21880, _mut21881, _mut21882)) && (ROR_greater(error, maxError, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21883, _mut21884, _mut21885, _mut21886, _mut21887)))))))) {
                            // error is too big, we reduce the global step
                            hNew = FastMath.abs(filterStep(AOR_multiply(stepSize, stabilityReduction, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22061, _mut22062, _mut22063, _mut22064), forward, false));
                            reject = true;
                            loop = false;
                        } else {
                            maxError = FastMath.max(AOR_multiply(4, error, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21890, _mut21891, _mut21892, _mut21893), 1.0);
                            // compute optimal stepsize for this order
                            final double exp = AOR_divide(1.0, (AOR_plus(AOR_multiply(2, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21894, _mut21895, _mut21896, _mut21897), 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21898, _mut21899, _mut21900, _mut21901)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21902, _mut21903, _mut21904, _mut21905);
                            double fac = AOR_divide(stepControl2, FastMath.pow(AOR_divide(error, stepControl1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21906, _mut21907, _mut21908, _mut21909), exp), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21910, _mut21911, _mut21912, _mut21913);
                            final double pow = FastMath.pow(stepControl3, exp);
                            fac = FastMath.max(AOR_divide(pow, stepControl4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21914, _mut21915, _mut21916, _mut21917), FastMath.min(AOR_divide(1, pow, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21918, _mut21919, _mut21920, _mut21921), fac));
                            optimalStep[k] = FastMath.abs(filterStep(AOR_multiply(stepSize, fac, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21922, _mut21923, _mut21924, _mut21925), forward, true));
                            costPerTimeUnit[k] = AOR_divide(costPerStep[k], optimalStep[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21926, _mut21927, _mut21928, _mut21929);
                            // check convergence
                            switch(AOR_minus(k, targetIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22057, _mut22058, _mut22059, _mut22060)) {
                                case -1:
                                    if ((_mut21935 ? ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21930, _mut21931, _mut21932, _mut21933, _mut21934)) || !previousRejected) : ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21930, _mut21931, _mut21932, _mut21933, _mut21934)) && !previousRejected))) {
                                        // check if we can stop iterations now
                                        if (ROR_less_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21936, _mut21937, _mut21938, _mut21939, _mut21940)) {
                                            // convergence have been reached just before targetIter
                                            loop = false;
                                        } else {
                                            // asymptotic evolution of error
                                            final double ratio = AOR_divide((AOR_multiply((double) sequence[targetIter], sequence[AOR_plus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21941, _mut21942, _mut21943, _mut21944)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21945, _mut21946, _mut21947, _mut21948)), (AOR_multiply(sequence[0], sequence[0], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21949, _mut21950, _mut21951, _mut21952)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21953, _mut21954, _mut21955, _mut21956);
                                            if (ROR_greater(error, AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21957, _mut21958, _mut21959, _mut21960), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21961, _mut21962, _mut21963, _mut21964, _mut21965)) {
                                                // we reject the step immediately and reduce order
                                                reject = true;
                                                loop = false;
                                                targetIter = k;
                                                if ((_mut21984 ? ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21966, _mut21967, _mut21968, _mut21969, _mut21970)) || (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21971, _mut21972, _mut21973, _mut21974)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21975, _mut21976, _mut21977, _mut21978), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21979, _mut21980, _mut21981, _mut21982, _mut21983))) : ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21966, _mut21967, _mut21968, _mut21969, _mut21970)) && (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21971, _mut21972, _mut21973, _mut21974)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21975, _mut21976, _mut21977, _mut21978), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21979, _mut21980, _mut21981, _mut21982, _mut21983))))) {
                                                    --targetIter;
                                                }
                                                hNew = optimalStep[targetIter];
                                            }
                                        }
                                    }
                                    break;
                                case 0:
                                    if (ROR_less_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21985, _mut21986, _mut21987, _mut21988, _mut21989)) {
                                        // convergence has been reached exactly at targetIter
                                        loop = false;
                                    } else {
                                        // asymptotic evolution of error
                                        final double ratio = AOR_divide(((double) sequence[AOR_plus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21990, _mut21991, _mut21992, _mut21993)]), sequence[0], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21994, _mut21995, _mut21996, _mut21997);
                                        if (ROR_greater(error, AOR_multiply(ratio, ratio, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut21998, _mut21999, _mut22000, _mut22001), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22002, _mut22003, _mut22004, _mut22005, _mut22006)) {
                                            // we reject the step immediately
                                            reject = true;
                                            loop = false;
                                            if ((_mut22025 ? ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22007, _mut22008, _mut22009, _mut22010, _mut22011)) || (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22012, _mut22013, _mut22014, _mut22015)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22016, _mut22017, _mut22018, _mut22019), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22020, _mut22021, _mut22022, _mut22023, _mut22024))) : ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22007, _mut22008, _mut22009, _mut22010, _mut22011)) && (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22012, _mut22013, _mut22014, _mut22015)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22016, _mut22017, _mut22018, _mut22019), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22020, _mut22021, _mut22022, _mut22023, _mut22024))))) {
                                                --targetIter;
                                            }
                                            hNew = optimalStep[targetIter];
                                        }
                                    }
                                    break;
                                case 1:
                                    if (ROR_greater(error, 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22026, _mut22027, _mut22028, _mut22029, _mut22030)) {
                                        reject = true;
                                        if ((_mut22049 ? ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22031, _mut22032, _mut22033, _mut22034, _mut22035)) || (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22036, _mut22037, _mut22038, _mut22039)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22040, _mut22041, _mut22042, _mut22043), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22044, _mut22045, _mut22046, _mut22047, _mut22048))) : ((ROR_greater(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22031, _mut22032, _mut22033, _mut22034, _mut22035)) && (ROR_less(costPerTimeUnit[AOR_minus(targetIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22036, _mut22037, _mut22038, _mut22039)], AOR_multiply(orderControl1, costPerTimeUnit[targetIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22040, _mut22041, _mut22042, _mut22043), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22044, _mut22045, _mut22046, _mut22047, _mut22048))))) {
                                            --targetIter;
                                        }
                                        hNew = optimalStep[targetIter];
                                    }
                                    loop = false;
                                    break;
                                default:
                                    if ((_mut22056 ? (((_mut22050 ? (firstTime && isLastStep) : (firstTime || isLastStep))) || (ROR_less_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22051, _mut22052, _mut22053, _mut22054, _mut22055))) : (((_mut22050 ? (firstTime && isLastStep) : (firstTime || isLastStep))) && (ROR_less_equals(error, 1.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22051, _mut22052, _mut22053, _mut22054, _mut22055))))) {
                                        loop = false;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
            if (!reject) {
                // derivatives at end of step
                computeDerivatives(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22069, _mut22070, _mut22071, _mut22072), y1, yDot1);
            }
            // dense output handling
            double hInt = getMaxStep();
            if (!reject) {
                // extrapolate state at middle point of the step
                for (int j = 1; ROR_less_equals(j, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22073, _mut22074, _mut22075, _mut22076, _mut22077); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                    extrapolate(0, j, diagonal, yMidDots[0]);
                }
                final int mu = AOR_plus(AOR_minus(AOR_multiply(2, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22078, _mut22079, _mut22080, _mut22081), mudif, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22082, _mut22083, _mut22084, _mut22085), 3, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22086, _mut22087, _mut22088, _mut22089);
                for (int l = 0; ROR_less(l, mu, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22205, _mut22206, _mut22207, _mut22208, _mut22209); ++l) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                    // derivative at middle point of the step
                    final int l2 = AOR_divide(l, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22090, _mut22091, _mut22092, _mut22093);
                    double factor = FastMath.pow(AOR_multiply(0.5, sequence[l2], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22094, _mut22095, _mut22096, _mut22097), l);
                    int middleIndex = AOR_divide(fk[l2].length, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22098, _mut22099, _mut22100, _mut22101);
                    for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22114, _mut22115, _mut22116, _mut22117, _mut22118); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                        yMidDots[AOR_plus(l, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22102, _mut22103, _mut22104, _mut22105)][i] = AOR_multiply(factor, fk[l2][AOR_plus(middleIndex, l, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22106, _mut22107, _mut22108, _mut22109)][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22110, _mut22111, _mut22112, _mut22113);
                    }
                    for (int j = 1; ROR_less_equals(j, AOR_minus(k, l2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22160, _mut22161, _mut22162, _mut22163), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22164, _mut22165, _mut22166, _mut22167, _mut22168); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                        factor = FastMath.pow(AOR_multiply(0.5, sequence[AOR_plus(j, l2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22119, _mut22120, _mut22121, _mut22122)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22123, _mut22124, _mut22125, _mut22126), l);
                        middleIndex = AOR_divide(fk[AOR_plus(l2, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22127, _mut22128, _mut22129, _mut22130)].length, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22131, _mut22132, _mut22133, _mut22134);
                        for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22151, _mut22152, _mut22153, _mut22154, _mut22155); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                            diagonal[AOR_minus(j, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22135, _mut22136, _mut22137, _mut22138)][i] = AOR_multiply(factor, fk[AOR_plus(l2, j, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22143, _mut22144, _mut22145, _mut22146)][AOR_plus(middleIndex, l, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22139, _mut22140, _mut22141, _mut22142)][i], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22147, _mut22148, _mut22149, _mut22150);
                        }
                        extrapolate(l2, j, diagonal, yMidDots[AOR_plus(l, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22156, _mut22157, _mut22158, _mut22159)]);
                    }
                    for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22173, _mut22174, _mut22175, _mut22176, _mut22177); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                        yMidDots[AOR_plus(l, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22169, _mut22170, _mut22171, _mut22172)][i] *= stepSize;
                    }
                    // compute centered differences to evaluate next derivatives
                    for (int j = (l + 1) / 2; ROR_less_equals(j, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22200, _mut22201, _mut22202, _mut22203, _mut22204); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                        for (int m = fk[j].length - 1; ROR_greater_equals(m, AOR_multiply(2, (AOR_plus(l, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22187, _mut22188, _mut22189, _mut22190)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22191, _mut22192, _mut22193, _mut22194), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22195, _mut22196, _mut22197, _mut22198, _mut22199); --m) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                            for (int i = 0; ROR_less(i, y0.length, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22182, _mut22183, _mut22184, _mut22185, _mut22186); ++i) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547");
                                fk[j][m][i] -= fk[j][AOR_minus(m, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22178, _mut22179, _mut22180, _mut22181)][i];
                            }
                        }
                    }
                }
                if (ROR_greater_equals(mu, 0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22210, _mut22211, _mut22212, _mut22213, _mut22214)) {
                    // estimate the dense output coefficients
                    final GraggBulirschStoerStepInterpolator gbsInterpolator = (GraggBulirschStoerStepInterpolator) interpolator;
                    gbsInterpolator.computeCoefficients(mu, stepSize);
                    if (useInterpolationError) {
                        // use the interpolation error to limit stepsize
                        final double interpError = gbsInterpolator.estimateError(scale);
                        hInt = FastMath.abs(AOR_divide(stepSize, FastMath.max(FastMath.pow(interpError, AOR_divide(1.0, (AOR_plus(mu, 4, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22215, _mut22216, _mut22217, _mut22218)), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22219, _mut22220, _mut22221, _mut22222)), 0.01), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22223, _mut22224, _mut22225, _mut22226));
                        if (ROR_greater(interpError, 10.0, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22227, _mut22228, _mut22229, _mut22230, _mut22231)) {
                            hNew = hInt;
                            reject = true;
                        }
                    }
                }
            }
            if (!reject) {
                // Discrete events handling
                interpolator.storeTime(AOR_plus(stepStart, stepSize, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22232, _mut22233, _mut22234, _mut22235));
                stepStart = acceptStep(interpolator, y1, yDot1, t);
                // prepare next step
                interpolator.storeTime(stepStart);
                System.arraycopy(y1, 0, y, 0, y0.length);
                System.arraycopy(yDot1, 0, yDot0, 0, y0.length);
                firstStepAlreadyComputed = true;
                int optimalIter;
                if (ROR_equals(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22236, _mut22237, _mut22238, _mut22239, _mut22240)) {
                    optimalIter = 2;
                    if (previousRejected) {
                        optimalIter = 1;
                    }
                } else if (ROR_less_equals(k, targetIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22241, _mut22242, _mut22243, _mut22244, _mut22245)) {
                    optimalIter = k;
                    if (ROR_less(costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22290, _mut22291, _mut22292, _mut22293)], AOR_multiply(orderControl1, costPerTimeUnit[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22294, _mut22295, _mut22296, _mut22297), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22298, _mut22299, _mut22300, _mut22301, _mut22302)) {
                        optimalIter = AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22324, _mut22325, _mut22326, _mut22327);
                    } else if (ROR_less(costPerTimeUnit[k], AOR_multiply(orderControl2, costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22303, _mut22304, _mut22305, _mut22306)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22307, _mut22308, _mut22309, _mut22310), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22311, _mut22312, _mut22313, _mut22314, _mut22315)) {
                        optimalIter = FastMath.min(AOR_plus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22316, _mut22317, _mut22318, _mut22319), AOR_minus(sequence.length, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22320, _mut22321, _mut22322, _mut22323));
                    }
                } else {
                    optimalIter = AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22246, _mut22247, _mut22248, _mut22249);
                    if ((_mut22272 ? ((ROR_greater(k, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22250, _mut22251, _mut22252, _mut22253, _mut22254)) || (ROR_less(costPerTimeUnit[AOR_minus(k, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22255, _mut22256, _mut22257, _mut22258)], AOR_multiply(orderControl1, costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22259, _mut22260, _mut22261, _mut22262)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22263, _mut22264, _mut22265, _mut22266), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22267, _mut22268, _mut22269, _mut22270, _mut22271))) : ((ROR_greater(k, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22250, _mut22251, _mut22252, _mut22253, _mut22254)) && (ROR_less(costPerTimeUnit[AOR_minus(k, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22255, _mut22256, _mut22257, _mut22258)], AOR_multiply(orderControl1, costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22259, _mut22260, _mut22261, _mut22262)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22263, _mut22264, _mut22265, _mut22266), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22267, _mut22268, _mut22269, _mut22270, _mut22271))))) {
                        optimalIter = AOR_minus(k, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22273, _mut22274, _mut22275, _mut22276);
                    }
                    if (ROR_less(costPerTimeUnit[k], AOR_multiply(orderControl2, costPerTimeUnit[optimalIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22277, _mut22278, _mut22279, _mut22280), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22281, _mut22282, _mut22283, _mut22284, _mut22285)) {
                        optimalIter = FastMath.min(k, AOR_minus(sequence.length, 2, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22286, _mut22287, _mut22288, _mut22289));
                    }
                }
                if (previousRejected) {
                    // should increase
                    targetIter = FastMath.min(optimalIter, k);
                    hNew = FastMath.min(FastMath.abs(stepSize), optimalStep[targetIter]);
                } else {
                    // stepsize control
                    if (ROR_less_equals(optimalIter, k, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22328, _mut22329, _mut22330, _mut22331, _mut22332)) {
                        hNew = optimalStep[optimalIter];
                    } else {
                        if ((_mut22351 ? ((ROR_less(k, targetIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22333, _mut22334, _mut22335, _mut22336, _mut22337)) || (ROR_less(costPerTimeUnit[k], AOR_multiply(orderControl2, costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22338, _mut22339, _mut22340, _mut22341)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22342, _mut22343, _mut22344, _mut22345), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22346, _mut22347, _mut22348, _mut22349, _mut22350))) : ((ROR_less(k, targetIter, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22333, _mut22334, _mut22335, _mut22336, _mut22337)) && (ROR_less(costPerTimeUnit[k], AOR_multiply(orderControl2, costPerTimeUnit[AOR_minus(k, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22338, _mut22339, _mut22340, _mut22341)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22342, _mut22343, _mut22344, _mut22345), "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22346, _mut22347, _mut22348, _mut22349, _mut22350))))) {
                            hNew = filterStep(AOR_divide(AOR_multiply(optimalStep[k], costPerStep[AOR_plus(optimalIter, 1, "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22360, _mut22361, _mut22362, _mut22363)], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22364, _mut22365, _mut22366, _mut22367), costPerStep[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22368, _mut22369, _mut22370, _mut22371), forward, false);
                        } else {
                            hNew = filterStep(AOR_divide(AOR_multiply(optimalStep[k], costPerStep[optimalIter], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22352, _mut22353, _mut22354, _mut22355), costPerStep[k], "org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate_547", _mut22356, _mut22357, _mut22358, _mut22359), forward, false);
                        }
                    }
                    targetIter = optimalIter;
                }
                newStep = true;
            }
            hNew = FastMath.min(hNew, hInt);
            if (!forward) {
                hNew = -hNew;
            }
            firstTime = false;
            if (reject) {
                isLastStep = false;
                previousRejected = true;
            } else {
                previousRejected = false;
            }
        } while (!isLastStep);
        // dispatch results
        equations.setTime(stepStart);
        equations.setCompleteState(y);
        resetInternalState();
    }
}
