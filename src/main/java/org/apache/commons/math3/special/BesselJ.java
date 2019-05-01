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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class provides computation methods related to Bessel
 * functions of the first kind. Detailed descriptions of these functions are
 * available in <a
 * href="http://en.wikipedia.org/wiki/Bessel_function">Wikipedia</a>, <a
 * href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">Abrabowitz and
 * Stegun</a> (Ch. 9-11), and <a href="http://dlmf.nist.gov/">DLMF</a> (Ch. 10).
 * <p>
 * This implementation is based on the rjbesl Fortran routine at
 * <a href="http://www.netlib.org/specfun/rjbesl">Netlib</a>.</p>
 * <p>
 * From the Fortran code: </p>
 * <p>
 * This program is based on a program written by David J. Sookne (2) that
 * computes values of the Bessel functions J or I of real argument and integer
 * order. Modifications include the restriction of the computation to the J
 * Bessel function of non-negative real argument, the extension of the
 * computation to arbitrary positive order, and the elimination of most
 * underflow.</p>
 * <p>
 * References:</p>
 * <ul>
 * <li>"A Note on Backward Recurrence Algorithms," Olver, F. W. J., and Sookne,
 * D. J., Math. Comp. 26, 1972, pp 941-947.</li>
 * <li>"Bessel Functions of Real Argument and Integer Order," Sookne, D. J., NBS
 * Jour. of Res. B. 77B, 1973, pp 125-132.</li>
 * </ul> </p>
 * @since 3.4
 */
public class BesselJ implements UnivariateFunction {

    @Conditional
    public static boolean _mut68638 = false, _mut68639 = false, _mut68640 = false, _mut68641 = false, _mut68642 = false, _mut68643 = false, _mut68644 = false, _mut68645 = false, _mut68646 = false, _mut68647 = false, _mut68648 = false, _mut68649 = false, _mut68650 = false, _mut68651 = false, _mut68652 = false, _mut68653 = false, _mut68654 = false, _mut68655 = false, _mut68656 = false, _mut68657 = false, _mut68658 = false, _mut68659 = false, _mut68660 = false, _mut68661 = false, _mut68662 = false, _mut68663 = false, _mut68664 = false, _mut68665 = false, _mut68666 = false, _mut68667 = false, _mut68668 = false, _mut68669 = false, _mut68670 = false, _mut68671 = false, _mut68672 = false, _mut68673 = false, _mut68674 = false, _mut68675 = false, _mut68676 = false, _mut68677 = false, _mut68678 = false, _mut68679 = false, _mut68680 = false, _mut68681 = false, _mut68682 = false, _mut68683 = false, _mut68684 = false, _mut68685 = false, _mut68686 = false, _mut68687 = false, _mut68688 = false, _mut68689 = false, _mut68690 = false, _mut68691 = false, _mut68692 = false, _mut68693 = false, _mut68694 = false, _mut68695 = false, _mut68696 = false, _mut68697 = false, _mut68698 = false, _mut68699 = false, _mut68700 = false, _mut68701 = false, _mut68702 = false, _mut68703 = false, _mut68704 = false, _mut68705 = false, _mut68706 = false, _mut68707 = false, _mut68708 = false, _mut68709 = false, _mut68710 = false, _mut68711 = false, _mut68712 = false, _mut68713 = false, _mut68714 = false, _mut68715 = false, _mut68716 = false, _mut68717 = false, _mut68718 = false, _mut68719 = false, _mut68720 = false, _mut68721 = false, _mut68722 = false, _mut68723 = false, _mut68724 = false, _mut68725 = false, _mut68726 = false, _mut68727 = false, _mut68728 = false, _mut68729 = false, _mut68730 = false, _mut68731 = false, _mut68732 = false, _mut68733 = false, _mut68734 = false, _mut68735 = false, _mut68736 = false, _mut68737 = false, _mut68738 = false, _mut68739 = false, _mut68740 = false, _mut68741 = false, _mut68742 = false, _mut68743 = false, _mut68744 = false, _mut68745 = false, _mut68746 = false, _mut68747 = false, _mut68748 = false, _mut68749 = false, _mut68750 = false, _mut68751 = false, _mut68752 = false, _mut68753 = false, _mut68754 = false, _mut68755 = false, _mut68756 = false, _mut68757 = false, _mut68758 = false, _mut68759 = false, _mut68760 = false, _mut68761 = false, _mut68762 = false, _mut68763 = false, _mut68764 = false, _mut68765 = false, _mut68766 = false, _mut68767 = false, _mut68768 = false, _mut68769 = false, _mut68770 = false, _mut68771 = false, _mut68772 = false, _mut68773 = false, _mut68774 = false, _mut68775 = false, _mut68776 = false, _mut68777 = false, _mut68778 = false, _mut68779 = false, _mut68780 = false, _mut68781 = false, _mut68782 = false, _mut68783 = false, _mut68784 = false, _mut68785 = false, _mut68786 = false, _mut68787 = false, _mut68788 = false, _mut68789 = false, _mut68790 = false, _mut68791 = false, _mut68792 = false, _mut68793 = false, _mut68794 = false, _mut68795 = false, _mut68796 = false, _mut68797 = false, _mut68798 = false, _mut68799 = false, _mut68800 = false, _mut68801 = false, _mut68802 = false, _mut68803 = false, _mut68804 = false, _mut68805 = false, _mut68806 = false, _mut68807 = false, _mut68808 = false, _mut68809 = false, _mut68810 = false, _mut68811 = false, _mut68812 = false, _mut68813 = false, _mut68814 = false, _mut68815 = false, _mut68816 = false, _mut68817 = false, _mut68818 = false, _mut68819 = false, _mut68820 = false, _mut68821 = false, _mut68822 = false, _mut68823 = false, _mut68824 = false, _mut68825 = false, _mut68826 = false, _mut68827 = false, _mut68828 = false, _mut68829 = false, _mut68830 = false, _mut68831 = false, _mut68832 = false, _mut68833 = false, _mut68834 = false, _mut68835 = false, _mut68836 = false, _mut68837 = false, _mut68838 = false, _mut68839 = false, _mut68840 = false, _mut68841 = false, _mut68842 = false, _mut68843 = false, _mut68844 = false, _mut68845 = false, _mut68846 = false, _mut68847 = false, _mut68848 = false, _mut68849 = false, _mut68850 = false, _mut68851 = false, _mut68852 = false, _mut68853 = false, _mut68854 = false, _mut68855 = false, _mut68856 = false, _mut68857 = false, _mut68858 = false, _mut68859 = false, _mut68860 = false, _mut68861 = false, _mut68862 = false, _mut68863 = false, _mut68864 = false, _mut68865 = false, _mut68866 = false, _mut68867 = false, _mut68868 = false, _mut68869 = false, _mut68870 = false, _mut68871 = false, _mut68872 = false, _mut68873 = false, _mut68874 = false, _mut68875 = false, _mut68876 = false, _mut68877 = false, _mut68878 = false, _mut68879 = false, _mut68880 = false, _mut68881 = false, _mut68882 = false, _mut68883 = false, _mut68884 = false, _mut68885 = false, _mut68886 = false, _mut68887 = false, _mut68888 = false, _mut68889 = false, _mut68890 = false, _mut68891 = false, _mut68892 = false, _mut68893 = false, _mut68894 = false, _mut68895 = false, _mut68896 = false, _mut68897 = false, _mut68898 = false, _mut68899 = false, _mut68900 = false, _mut68901 = false, _mut68902 = false, _mut68903 = false, _mut68904 = false, _mut68905 = false, _mut68906 = false, _mut68907 = false, _mut68908 = false, _mut68909 = false, _mut68910 = false, _mut68911 = false, _mut68912 = false, _mut68913 = false, _mut68914 = false, _mut68915 = false, _mut68916 = false, _mut68917 = false, _mut68918 = false, _mut68919 = false, _mut68920 = false, _mut68921 = false, _mut68922 = false, _mut68923 = false, _mut68924 = false, _mut68925 = false, _mut68926 = false, _mut68927 = false, _mut68928 = false, _mut68929 = false, _mut68930 = false, _mut68931 = false, _mut68932 = false, _mut68933 = false, _mut68934 = false, _mut68935 = false, _mut68936 = false, _mut68937 = false, _mut68938 = false, _mut68939 = false, _mut68940 = false, _mut68941 = false, _mut68942 = false, _mut68943 = false, _mut68944 = false, _mut68945 = false, _mut68946 = false, _mut68947 = false, _mut68948 = false, _mut68949 = false, _mut68950 = false, _mut68951 = false, _mut68952 = false, _mut68953 = false, _mut68954 = false, _mut68955 = false, _mut68956 = false, _mut68957 = false, _mut68958 = false, _mut68959 = false, _mut68960 = false, _mut68961 = false, _mut68962 = false, _mut68963 = false, _mut68964 = false, _mut68965 = false, _mut68966 = false, _mut68967 = false, _mut68968 = false, _mut68969 = false, _mut68970 = false, _mut68971 = false, _mut68972 = false, _mut68973 = false, _mut68974 = false, _mut68975 = false, _mut68976 = false, _mut68977 = false, _mut68978 = false, _mut68979 = false, _mut68980 = false, _mut68981 = false, _mut68982 = false, _mut68983 = false, _mut68984 = false, _mut68985 = false, _mut68986 = false, _mut68987 = false, _mut68988 = false, _mut68989 = false, _mut68990 = false, _mut68991 = false, _mut68992 = false, _mut68993 = false, _mut68994 = false, _mut68995 = false, _mut68996 = false, _mut68997 = false, _mut68998 = false, _mut68999 = false, _mut69000 = false, _mut69001 = false, _mut69002 = false, _mut69003 = false, _mut69004 = false, _mut69005 = false, _mut69006 = false, _mut69007 = false, _mut69008 = false, _mut69009 = false, _mut69010 = false, _mut69011 = false, _mut69012 = false, _mut69013 = false, _mut69014 = false, _mut69015 = false, _mut69016 = false, _mut69017 = false, _mut69018 = false, _mut69019 = false, _mut69020 = false, _mut69021 = false, _mut69022 = false, _mut69023 = false, _mut69024 = false, _mut69025 = false, _mut69026 = false, _mut69027 = false, _mut69028 = false, _mut69029 = false, _mut69030 = false, _mut69031 = false, _mut69032 = false, _mut69033 = false, _mut69034 = false, _mut69035 = false, _mut69036 = false, _mut69037 = false, _mut69038 = false, _mut69039 = false, _mut69040 = false, _mut69041 = false, _mut69042 = false, _mut69043 = false, _mut69044 = false, _mut69045 = false, _mut69046 = false, _mut69047 = false, _mut69048 = false, _mut69049 = false, _mut69050 = false, _mut69051 = false, _mut69052 = false, _mut69053 = false, _mut69054 = false, _mut69055 = false, _mut69056 = false, _mut69057 = false, _mut69058 = false, _mut69059 = false, _mut69060 = false, _mut69061 = false, _mut69062 = false, _mut69063 = false, _mut69064 = false, _mut69065 = false, _mut69066 = false, _mut69067 = false, _mut69068 = false, _mut69069 = false, _mut69070 = false, _mut69071 = false, _mut69072 = false, _mut69073 = false, _mut69074 = false, _mut69075 = false, _mut69076 = false, _mut69077 = false, _mut69078 = false, _mut69079 = false, _mut69080 = false, _mut69081 = false, _mut69082 = false, _mut69083 = false, _mut69084 = false, _mut69085 = false, _mut69086 = false, _mut69087 = false, _mut69088 = false, _mut69089 = false, _mut69090 = false, _mut69091 = false, _mut69092 = false, _mut69093 = false, _mut69094 = false, _mut69095 = false, _mut69096 = false, _mut69097 = false, _mut69098 = false, _mut69099 = false, _mut69100 = false, _mut69101 = false, _mut69102 = false, _mut69103 = false, _mut69104 = false, _mut69105 = false, _mut69106 = false, _mut69107 = false, _mut69108 = false, _mut69109 = false, _mut69110 = false, _mut69111 = false, _mut69112 = false, _mut69113 = false, _mut69114 = false, _mut69115 = false, _mut69116 = false, _mut69117 = false, _mut69118 = false, _mut69119 = false, _mut69120 = false, _mut69121 = false, _mut69122 = false, _mut69123 = false, _mut69124 = false, _mut69125 = false, _mut69126 = false, _mut69127 = false, _mut69128 = false, _mut69129 = false, _mut69130 = false, _mut69131 = false, _mut69132 = false, _mut69133 = false, _mut69134 = false, _mut69135 = false, _mut69136 = false, _mut69137 = false, _mut69138 = false, _mut69139 = false, _mut69140 = false, _mut69141 = false, _mut69142 = false, _mut69143 = false, _mut69144 = false, _mut69145 = false, _mut69146 = false, _mut69147 = false, _mut69148 = false, _mut69149 = false, _mut69150 = false, _mut69151 = false, _mut69152 = false, _mut69153 = false, _mut69154 = false, _mut69155 = false, _mut69156 = false, _mut69157 = false, _mut69158 = false, _mut69159 = false, _mut69160 = false, _mut69161 = false, _mut69162 = false, _mut69163 = false, _mut69164 = false, _mut69165 = false, _mut69166 = false, _mut69167 = false, _mut69168 = false, _mut69169 = false, _mut69170 = false, _mut69171 = false, _mut69172 = false, _mut69173 = false, _mut69174 = false, _mut69175 = false, _mut69176 = false, _mut69177 = false, _mut69178 = false, _mut69179 = false, _mut69180 = false, _mut69181 = false, _mut69182 = false, _mut69183 = false, _mut69184 = false, _mut69185 = false, _mut69186 = false, _mut69187 = false, _mut69188 = false, _mut69189 = false, _mut69190 = false, _mut69191 = false, _mut69192 = false, _mut69193 = false, _mut69194 = false, _mut69195 = false, _mut69196 = false, _mut69197 = false, _mut69198 = false, _mut69199 = false, _mut69200 = false, _mut69201 = false, _mut69202 = false, _mut69203 = false, _mut69204 = false, _mut69205 = false, _mut69206 = false, _mut69207 = false, _mut69208 = false, _mut69209 = false, _mut69210 = false, _mut69211 = false, _mut69212 = false, _mut69213 = false, _mut69214 = false, _mut69215 = false, _mut69216 = false, _mut69217 = false, _mut69218 = false, _mut69219 = false, _mut69220 = false, _mut69221 = false, _mut69222 = false, _mut69223 = false, _mut69224 = false, _mut69225 = false, _mut69226 = false, _mut69227 = false, _mut69228 = false, _mut69229 = false, _mut69230 = false, _mut69231 = false, _mut69232 = false, _mut69233 = false, _mut69234 = false, _mut69235 = false, _mut69236 = false, _mut69237 = false, _mut69238 = false, _mut69239 = false, _mut69240 = false, _mut69241 = false, _mut69242 = false, _mut69243 = false, _mut69244 = false, _mut69245 = false, _mut69246 = false, _mut69247 = false, _mut69248 = false, _mut69249 = false, _mut69250 = false, _mut69251 = false, _mut69252 = false, _mut69253 = false, _mut69254 = false, _mut69255 = false, _mut69256 = false, _mut69257 = false, _mut69258 = false, _mut69259 = false, _mut69260 = false, _mut69261 = false, _mut69262 = false, _mut69263 = false, _mut69264 = false, _mut69265 = false, _mut69266 = false, _mut69267 = false, _mut69268 = false, _mut69269 = false, _mut69270 = false, _mut69271 = false, _mut69272 = false, _mut69273 = false, _mut69274 = false, _mut69275 = false, _mut69276 = false, _mut69277 = false, _mut69278 = false, _mut69279 = false, _mut69280 = false, _mut69281 = false, _mut69282 = false, _mut69283 = false, _mut69284 = false, _mut69285 = false, _mut69286 = false, _mut69287 = false, _mut69288 = false, _mut69289 = false, _mut69290 = false, _mut69291 = false, _mut69292 = false, _mut69293 = false, _mut69294 = false, _mut69295 = false, _mut69296 = false, _mut69297 = false, _mut69298 = false, _mut69299 = false, _mut69300 = false, _mut69301 = false, _mut69302 = false, _mut69303 = false, _mut69304 = false, _mut69305 = false, _mut69306 = false, _mut69307 = false, _mut69308 = false, _mut69309 = false, _mut69310 = false, _mut69311 = false, _mut69312 = false, _mut69313 = false, _mut69314 = false, _mut69315 = false, _mut69316 = false, _mut69317 = false, _mut69318 = false, _mut69319 = false, _mut69320 = false, _mut69321 = false, _mut69322 = false, _mut69323 = false, _mut69324 = false, _mut69325 = false, _mut69326 = false, _mut69327 = false, _mut69328 = false, _mut69329 = false, _mut69330 = false, _mut69331 = false, _mut69332 = false, _mut69333 = false, _mut69334 = false, _mut69335 = false, _mut69336 = false, _mut69337 = false, _mut69338 = false, _mut69339 = false, _mut69340 = false, _mut69341 = false, _mut69342 = false, _mut69343 = false, _mut69344 = false, _mut69345 = false, _mut69346 = false, _mut69347 = false, _mut69348 = false, _mut69349 = false, _mut69350 = false, _mut69351 = false, _mut69352 = false, _mut69353 = false, _mut69354 = false, _mut69355 = false, _mut69356 = false, _mut69357 = false, _mut69358 = false, _mut69359 = false, _mut69360 = false, _mut69361 = false, _mut69362 = false, _mut69363 = false, _mut69364 = false, _mut69365 = false, _mut69366 = false, _mut69367 = false, _mut69368 = false, _mut69369 = false, _mut69370 = false, _mut69371 = false, _mut69372 = false, _mut69373 = false, _mut69374 = false, _mut69375 = false, _mut69376 = false, _mut69377 = false, _mut69378 = false, _mut69379 = false, _mut69380 = false, _mut69381 = false, _mut69382 = false, _mut69383 = false, _mut69384 = false, _mut69385 = false, _mut69386 = false, _mut69387 = false, _mut69388 = false, _mut69389 = false, _mut69390 = false, _mut69391 = false, _mut69392 = false, _mut69393 = false, _mut69394 = false, _mut69395 = false, _mut69396 = false, _mut69397 = false, _mut69398 = false, _mut69399 = false, _mut69400 = false, _mut69401 = false, _mut69402 = false, _mut69403 = false, _mut69404 = false, _mut69405 = false, _mut69406 = false, _mut69407 = false, _mut69408 = false, _mut69409 = false, _mut69410 = false, _mut69411 = false, _mut69412 = false, _mut69413 = false, _mut69414 = false, _mut69415 = false, _mut69416 = false, _mut69417 = false, _mut69418 = false, _mut69419 = false, _mut69420 = false, _mut69421 = false, _mut69422 = false, _mut69423 = false, _mut69424 = false, _mut69425 = false, _mut69426 = false, _mut69427 = false, _mut69428 = false, _mut69429 = false, _mut69430 = false, _mut69431 = false, _mut69432 = false, _mut69433 = false, _mut69434 = false, _mut69435 = false, _mut69436 = false, _mut69437 = false, _mut69438 = false, _mut69439 = false, _mut69440 = false, _mut69441 = false, _mut69442 = false, _mut69443 = false, _mut69444 = false, _mut69445 = false, _mut69446 = false, _mut69447 = false, _mut69448 = false, _mut69449 = false, _mut69450 = false, _mut69451 = false, _mut69452 = false, _mut69453 = false, _mut69454 = false, _mut69455 = false, _mut69456 = false, _mut69457 = false, _mut69458 = false, _mut69459 = false, _mut69460 = false, _mut69461 = false, _mut69462 = false, _mut69463 = false, _mut69464 = false, _mut69465 = false, _mut69466 = false, _mut69467 = false, _mut69468 = false, _mut69469 = false, _mut69470 = false, _mut69471 = false, _mut69472 = false, _mut69473 = false, _mut69474 = false, _mut69475 = false, _mut69476 = false, _mut69477 = false, _mut69478 = false, _mut69479 = false, _mut69480 = false, _mut69481 = false, _mut69482 = false, _mut69483 = false, _mut69484 = false, _mut69485 = false, _mut69486 = false, _mut69487 = false, _mut69488 = false, _mut69489 = false, _mut69490 = false, _mut69491 = false, _mut69492 = false, _mut69493 = false, _mut69494 = false, _mut69495 = false, _mut69496 = false, _mut69497 = false, _mut69498 = false, _mut69499 = false, _mut69500 = false, _mut69501 = false, _mut69502 = false, _mut69503 = false, _mut69504 = false, _mut69505 = false, _mut69506 = false, _mut69507 = false, _mut69508 = false, _mut69509 = false, _mut69510 = false, _mut69511 = false, _mut69512 = false, _mut69513 = false, _mut69514 = false, _mut69515 = false, _mut69516 = false, _mut69517 = false, _mut69518 = false, _mut69519 = false, _mut69520 = false, _mut69521 = false, _mut69522 = false, _mut69523 = false, _mut69524 = false, _mut69525 = false, _mut69526 = false, _mut69527 = false, _mut69528 = false, _mut69529 = false, _mut69530 = false, _mut69531 = false, _mut69532 = false, _mut69533 = false, _mut69534 = false, _mut69535 = false, _mut69536 = false, _mut69537 = false, _mut69538 = false, _mut69539 = false, _mut69540 = false, _mut69541 = false, _mut69542 = false, _mut69543 = false, _mut69544 = false, _mut69545 = false, _mut69546 = false, _mut69547 = false, _mut69548 = false, _mut69549 = false, _mut69550 = false, _mut69551 = false, _mut69552 = false, _mut69553 = false, _mut69554 = false, _mut69555 = false, _mut69556 = false, _mut69557 = false, _mut69558 = false, _mut69559 = false, _mut69560 = false, _mut69561 = false, _mut69562 = false, _mut69563 = false, _mut69564 = false, _mut69565 = false, _mut69566 = false, _mut69567 = false, _mut69568 = false, _mut69569 = false, _mut69570 = false, _mut69571 = false, _mut69572 = false, _mut69573 = false, _mut69574 = false, _mut69575 = false, _mut69576 = false, _mut69577 = false, _mut69578 = false, _mut69579 = false, _mut69580 = false, _mut69581 = false, _mut69582 = false, _mut69583 = false, _mut69584 = false, _mut69585 = false, _mut69586 = false, _mut69587 = false, _mut69588 = false, _mut69589 = false, _mut69590 = false, _mut69591 = false, _mut69592 = false, _mut69593 = false, _mut69594 = false, _mut69595 = false, _mut69596 = false, _mut69597 = false, _mut69598 = false, _mut69599 = false, _mut69600 = false, _mut69601 = false, _mut69602 = false, _mut69603 = false, _mut69604 = false, _mut69605 = false, _mut69606 = false, _mut69607 = false, _mut69608 = false, _mut69609 = false, _mut69610 = false, _mut69611 = false, _mut69612 = false, _mut69613 = false, _mut69614 = false, _mut69615 = false, _mut69616 = false, _mut69617 = false, _mut69618 = false, _mut69619 = false, _mut69620 = false, _mut69621 = false, _mut69622 = false, _mut69623 = false, _mut69624 = false, _mut69625 = false, _mut69626 = false, _mut69627 = false, _mut69628 = false, _mut69629 = false, _mut69630 = false, _mut69631 = false, _mut69632 = false, _mut69633 = false, _mut69634 = false, _mut69635 = false, _mut69636 = false, _mut69637 = false, _mut69638 = false, _mut69639 = false, _mut69640 = false, _mut69641 = false, _mut69642 = false, _mut69643 = false, _mut69644 = false, _mut69645 = false, _mut69646 = false, _mut69647 = false, _mut69648 = false, _mut69649 = false, _mut69650 = false, _mut69651 = false, _mut69652 = false, _mut69653 = false, _mut69654 = false, _mut69655 = false, _mut69656 = false, _mut69657 = false, _mut69658 = false, _mut69659 = false, _mut69660 = false, _mut69661 = false, _mut69662 = false, _mut69663 = false, _mut69664 = false, _mut69665 = false, _mut69666 = false, _mut69667 = false, _mut69668 = false, _mut69669 = false, _mut69670 = false, _mut69671 = false, _mut69672 = false, _mut69673 = false, _mut69674 = false, _mut69675 = false, _mut69676 = false, _mut69677 = false, _mut69678 = false, _mut69679 = false, _mut69680 = false, _mut69681 = false, _mut69682 = false, _mut69683 = false, _mut69684 = false, _mut69685 = false, _mut69686 = false, _mut69687 = false, _mut69688 = false, _mut69689 = false, _mut69690 = false, _mut69691 = false, _mut69692 = false, _mut69693 = false, _mut69694 = false, _mut69695 = false, _mut69696 = false, _mut69697 = false, _mut69698 = false, _mut69699 = false, _mut69700 = false, _mut69701 = false, _mut69702 = false, _mut69703 = false, _mut69704 = false, _mut69705 = false, _mut69706 = false, _mut69707 = false, _mut69708 = false, _mut69709 = false, _mut69710 = false, _mut69711 = false, _mut69712 = false, _mut69713 = false, _mut69714 = false, _mut69715 = false, _mut69716 = false, _mut69717 = false, _mut69718 = false, _mut69719 = false, _mut69720 = false, _mut69721 = false, _mut69722 = false, _mut69723 = false, _mut69724 = false, _mut69725 = false, _mut69726 = false, _mut69727 = false, _mut69728 = false, _mut69729 = false, _mut69730 = false, _mut69731 = false, _mut69732 = false, _mut69733 = false, _mut69734 = false, _mut69735 = false, _mut69736 = false, _mut69737 = false, _mut69738 = false, _mut69739 = false, _mut69740 = false, _mut69741 = false, _mut69742 = false, _mut69743 = false, _mut69744 = false, _mut69745 = false, _mut69746 = false, _mut69747 = false, _mut69748 = false, _mut69749 = false, _mut69750 = false, _mut69751 = false, _mut69752 = false, _mut69753 = false, _mut69754 = false, _mut69755 = false, _mut69756 = false, _mut69757 = false, _mut69758 = false, _mut69759 = false, _mut69760 = false, _mut69761 = false, _mut69762 = false, _mut69763 = false, _mut69764 = false, _mut69765 = false, _mut69766 = false, _mut69767 = false, _mut69768 = false, _mut69769 = false, _mut69770 = false, _mut69771 = false, _mut69772 = false, _mut69773 = false, _mut69774 = false, _mut69775 = false, _mut69776 = false, _mut69777 = false, _mut69778 = false, _mut69779 = false, _mut69780 = false, _mut69781 = false;

    /**
     * -2 / pi
     */
    private static final double PI2 = 0.636619772367581343075535;

    /**
     * first few significant digits of 2pi
     */
    private static final double TOWPI1 = 6.28125;

    /**
     * 2pi - TWOPI1 to working precision
     */
    private static final double TWOPI2 = 1.935307179586476925286767e-3;

    /**
     * TOWPI1 + TWOPI2
     */
    private static final double TWOPI = AOR_plus(TOWPI1, TWOPI2, "org.apache.commons.math3.special.BesselJ.logBeta_421", _mut68638, _mut68639, _mut68640, _mut68641);

    /**
     * 10.0^K, where K is the largest integer such that ENTEN is
     * machine-representable in working precision
     */
    private static final double ENTEN = 1.0e308;

    /**
     * Decimal significance desired. Should be set to (INT(log_{10}(2) * (it)+1)).
     * Setting NSIG lower will result in decreased accuracy while setting
     * NSIG higher will increase CPU time without increasing accuracy.
     * The truncation error is limited to a relative error of
     * T=.5(10^(-NSIG)).
     */
    private static final double ENSIG = 1.0e16;

    /**
     * 10.0 ** (-K) for the smallest integer K such that K >= NSIG/4
     */
    private static final double RTNSIG = 1.0e-4;

    /**
     * Smallest ABS(X) such that X/4 does not underflow
     */
    private static final double ENMTEN = 8.90e-308;

    /**
     * Minimum acceptable value for x
     */
    private static final double X_MIN = 0.0;

    /**
     * Upper limit on the magnitude of x. If abs(x) = n, then at least
     * n iterations of the backward recursion will be executed. The value of
     * 10.0 ** 4 is used on every machine.
     */
    private static final double X_MAX = 1.0e4;

    /**
     * First 25 factorials as doubles
     */
    private static final double[] FACT = { 1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362880.0, 3628800.0, 39916800.0, 479001600.0, 6227020800.0, 87178291200.0, 1.307674368e12, 2.0922789888e13, 3.55687428096e14, 6.402373705728e15, 1.21645100408832e17, 2.43290200817664e18, 5.109094217170944e19, 1.12400072777760768e21, 2.585201673888497664e22, 6.2044840173323943936e23 };

    /**
     * Order of the function computed when {@link #value(double)} is used
     */
    private final double order;

    /**
     * Create a new BesselJ with the given order.
     *
     * @param order order of the function computed when using {@link #value(double)}.
     */
    public BesselJ(double order) {
        this.order = order;
    }

    /**
     * Returns the value of the constructed Bessel function of the first kind,
     * for the passed argument.
     *
     * @param x Argument
     * @return Value of the Bessel function at x
     * @throws MathIllegalArgumentException if {@code x} is too large relative to {@code order}
     * @throws ConvergenceException if the algorithm fails to converge
     */
    public double value(double x) throws MathIllegalArgumentException, ConvergenceException {
        return BesselJ.value(order, x);
    }

    /**
     * Returns the first Bessel function, \(J_{order}(x)\).
     *
     * @param order Order of the Bessel function
     * @param x Argument
     * @return Value of the Bessel function of the first kind, \(J_{order}(x)\)
     * @throws MathIllegalArgumentException if {@code x} is too large relative to {@code order}
     * @throws ConvergenceException if the algorithm fails to converge
     */
    public static double value(double order, double x) throws MathIllegalArgumentException, ConvergenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.value_155");
        final int n = (int) order;
        final double alpha = AOR_minus(order, n, "org.apache.commons.math3.special.BesselJ.value_155", _mut68642, _mut68643, _mut68644, _mut68645);
        final int nb = AOR_plus(n, 1, "org.apache.commons.math3.special.BesselJ.value_155", _mut68646, _mut68647, _mut68648, _mut68649);
        final BesselJResult res = rjBesl(x, alpha, nb);
        if (ROR_greater_equals(res.nVals, nb, "org.apache.commons.math3.special.BesselJ.value_155", _mut68650, _mut68651, _mut68652, _mut68653, _mut68654)) {
            return res.vals[n];
        } else if (ROR_less(res.nVals, 0, "org.apache.commons.math3.special.BesselJ.value_155", _mut68655, _mut68656, _mut68657, _mut68658, _mut68659)) {
            throw new MathIllegalArgumentException(LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT, order, x);
        } else if (ROR_less(FastMath.abs(res.vals[AOR_minus(res.nVals, 1, "org.apache.commons.math3.special.BesselJ.value_155", _mut68660, _mut68661, _mut68662, _mut68663)]), 1e-100, "org.apache.commons.math3.special.BesselJ.value_155", _mut68664, _mut68665, _mut68666, _mut68667, _mut68668)) {
            // underflow; return value (will be zero)
            return res.vals[n];
        }
        throw new ConvergenceException(LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE, order, x);
    }

    /**
     * Encapsulates the results returned by {@link BesselJ#rjBesl(double, double, int)}.
     * <p>
     * {@link #getVals()} returns the computed function values.
     * {@link #getnVals()} is the number of values among those returned by {@link #getnVals()}
     * that can be considered accurate.
     * </p><p>
     * <ul>
     * <li>nVals < 0: An argument is out of range. For example, nb <= 0, alpha
     * < 0 or > 1, or x is too large. In this case, b(0) is set to zero, the
     * remainder of the b-vector is not calculated, and nVals is set to
     * MIN(nb,0) - 1 so that nVals != nb.</li>
     * <li>nb > nVals > 0: Not all requested function values could be calculated
     * accurately. This usually occurs because nb is much larger than abs(x). In
     * this case, b(n) is calculated to the desired accuracy for n < nVals, but
     * precision is lost for nVals < n <= nb. If b(n) does not vanish for n >
     * nVals (because it is too small to be represented), and b(n)/b(nVals) =
     * \(10^{-k}\), then only the first NSIG-k significant figures of b(n) can be
     * trusted.</li></ul></p>
     */
    public static class BesselJResult {

        /**
         * Bessel function values
         */
        private final double[] vals;

        /**
         * Valid value count
         */
        private final int nVals;

        /**
         * Create a new BesselJResult with the given values and valid value count.
         *
         * @param b values
         * @param n count of valid values
         */
        public BesselJResult(double[] b, int n) {
            vals = MathArrays.copyOf(b, b.length);
            nVals = n;
        }

        /**
         * @return the computed function values
         */
        public double[] getVals() {
            return MathArrays.copyOf(vals, vals.length);
        }

        /**
         * @return the number of valid function values (normally the same as the
         *         length of the array returned by {@link #getnVals()})
         */
        public int getnVals() {
            return nVals;
        }
    }

    /**
     * Calculates Bessel functions \(J_{n+alpha}(x)\) for
     * non-negative argument x, and non-negative order n + alpha.
     * <p>
     * Before using the output vector, the user should check that
     * nVals = nb, i.e., all orders have been calculated to the desired accuracy.
     * See BesselResult class javadoc for details on return values.
     * </p>
     * @param x non-negative real argument for which J's are to be calculated
     * @param alpha fractional part of order for which J's or exponentially
     * scaled J's (\(J\cdot e^{x}\)) are to be calculated. 0 <= alpha < 1.0.
     * @param nb integer number of functions to be calculated, nb > 0. The first
     * function calculated is of order alpha, and the last is of order
     * nb - 1 + alpha.
     * @return BesselJResult a vector of the functions
     * \(J_{alpha}(x)\) through \(J_{nb-1+alpha}(x)\), or the corresponding exponentially
     * scaled functions and an integer output variable indicating possible errors
     */
    public static BesselJResult rjBesl(double x, double alpha, int nb) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
        final double[] b = new double[nb];
        int ncalc = 0;
        double alpem = 0;
        double alp2em = 0;
        // ---------------------------------------------------------------------
        final int magx = (int) x;
        if ((_mut68697 ? ((_mut68691 ? ((_mut68685 ? ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) || (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684))) : ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) && (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684)))) || (ROR_greater_equals(alpha, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68686, _mut68687, _mut68688, _mut68689, _mut68690))) : ((_mut68685 ? ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) || (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684))) : ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) && (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684)))) && (ROR_greater_equals(alpha, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68686, _mut68687, _mut68688, _mut68689, _mut68690)))) || (ROR_less(alpha, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68692, _mut68693, _mut68694, _mut68695, _mut68696))) : ((_mut68691 ? ((_mut68685 ? ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) || (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684))) : ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) && (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684)))) || (ROR_greater_equals(alpha, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68686, _mut68687, _mut68688, _mut68689, _mut68690))) : ((_mut68685 ? ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) || (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684))) : ((_mut68679 ? ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) || (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678))) : ((ROR_greater(nb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68669, _mut68670, _mut68671, _mut68672, _mut68673)) && (ROR_greater_equals(x, X_MIN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68674, _mut68675, _mut68676, _mut68677, _mut68678)))) && (ROR_less_equals(x, X_MAX, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68680, _mut68681, _mut68682, _mut68683, _mut68684)))) && (ROR_greater_equals(alpha, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68686, _mut68687, _mut68688, _mut68689, _mut68690)))) && (ROR_less(alpha, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68692, _mut68693, _mut68694, _mut68695, _mut68696))))) {
            // ---------------------------------------------------------------------
            ncalc = nb;
            for (int i = 0; ROR_less(i, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68707, _mut68708, _mut68709, _mut68710, _mut68711); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                b[i] = 0;
            }
            // ---------------------------------------------------------------------
            double tempa;
            double tempb;
            double tempc;
            double tover;
            if (ROR_less(x, RTNSIG, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68712, _mut68713, _mut68714, _mut68715, _mut68716)) {
                // ---------------------------------------------------------------------
                tempa = 1;
                alpem = AOR_plus(1, alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69651, _mut69652, _mut69653, _mut69654);
                double halfx = 0;
                if (ROR_greater(x, ENMTEN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69655, _mut69656, _mut69657, _mut69658, _mut69659)) {
                    halfx = AOR_multiply(0.5, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69660, _mut69661, _mut69662, _mut69663);
                }
                if (ROR_not_equals(alpha, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69664, _mut69665, _mut69666, _mut69667, _mut69668)) {
                    tempa = AOR_divide(FastMath.pow(halfx, alpha), (AOR_multiply(alpha, Gamma.gamma(alpha), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69669, _mut69670, _mut69671, _mut69672)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69673, _mut69674, _mut69675, _mut69676);
                }
                tempb = 0;
                if (ROR_greater(AOR_plus(x, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69677, _mut69678, _mut69679, _mut69680), 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69681, _mut69682, _mut69683, _mut69684, _mut69685)) {
                    tempb = AOR_multiply(-halfx, halfx, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69686, _mut69687, _mut69688, _mut69689);
                }
                b[0] = AOR_plus(tempa, (AOR_divide(AOR_multiply(tempa, tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69690, _mut69691, _mut69692, _mut69693), alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69694, _mut69695, _mut69696, _mut69697)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69698, _mut69699, _mut69700, _mut69701);
                if ((_mut69712 ? ((ROR_not_equals(x, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69702, _mut69703, _mut69704, _mut69705, _mut69706)) || (ROR_equals(b[0], 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69707, _mut69708, _mut69709, _mut69710, _mut69711))) : ((ROR_not_equals(x, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69702, _mut69703, _mut69704, _mut69705, _mut69706)) && (ROR_equals(b[0], 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69707, _mut69708, _mut69709, _mut69710, _mut69711))))) {
                    ncalc = 0;
                }
                if (ROR_not_equals(nb, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69713, _mut69714, _mut69715, _mut69716, _mut69717)) {
                    if (ROR_less_equals(x, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69718, _mut69719, _mut69720, _mut69721, _mut69722)) {
                        for (int n = 1; ROR_less(n, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69777, _mut69778, _mut69779, _mut69780, _mut69781); ++n) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                            b[n] = 0;
                        }
                    } else {
                        // ---------------------------------------------------------------------
                        tempc = halfx;
                        tover = ROR_not_equals(tempb, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69723, _mut69724, _mut69725, _mut69726, _mut69727) ? AOR_divide(ENMTEN, tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69736, _mut69737, _mut69738, _mut69739) : AOR_divide(AOR_multiply(2, ENMTEN, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69728, _mut69729, _mut69730, _mut69731), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69732, _mut69733, _mut69734, _mut69735);
                        for (int n = 1; ROR_less(n, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69772, _mut69773, _mut69774, _mut69775, _mut69776); ++n) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                            tempa /= alpem;
                            alpem += 1;
                            tempa *= tempc;
                            if (ROR_less_equals(tempa, AOR_multiply(tover, alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69740, _mut69741, _mut69742, _mut69743), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69744, _mut69745, _mut69746, _mut69747, _mut69748)) {
                                tempa = 0;
                            }
                            b[n] = AOR_plus(tempa, (AOR_divide(AOR_multiply(tempa, tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69749, _mut69750, _mut69751, _mut69752), alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69753, _mut69754, _mut69755, _mut69756)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69757, _mut69758, _mut69759, _mut69760);
                            if ((_mut69771 ? ((ROR_equals(b[n], 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69761, _mut69762, _mut69763, _mut69764, _mut69765)) || (ROR_greater(ncalc, n, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69766, _mut69767, _mut69768, _mut69769, _mut69770))) : ((ROR_equals(b[n], 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69761, _mut69762, _mut69763, _mut69764, _mut69765)) && (ROR_greater(ncalc, n, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69766, _mut69767, _mut69768, _mut69769, _mut69770))))) {
                                ncalc = n;
                            }
                        }
                    }
                }
            } else if ((_mut68731 ? ((ROR_greater(x, 25.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68717, _mut68718, _mut68719, _mut68720, _mut68721)) || (ROR_less_equals(nb, AOR_plus(magx, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68722, _mut68723, _mut68724, _mut68725), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68726, _mut68727, _mut68728, _mut68729, _mut68730))) : ((ROR_greater(x, 25.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68717, _mut68718, _mut68719, _mut68720, _mut68721)) && (ROR_less_equals(nb, AOR_plus(magx, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68722, _mut68723, _mut68724, _mut68725), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68726, _mut68727, _mut68728, _mut68729, _mut68730))))) {
                // ---------------------------------------------------------------------
                final double xc = FastMath.sqrt(AOR_divide(PI2, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69300, _mut69301, _mut69302, _mut69303));
                final double mul = AOR_divide(0.125, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69304, _mut69305, _mut69306, _mut69307);
                final double xin = AOR_multiply(mul, mul, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69308, _mut69309, _mut69310, _mut69311);
                int m = 0;
                if (ROR_greater_equals(x, 130.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69312, _mut69313, _mut69314, _mut69315, _mut69316)) {
                    m = 4;
                } else if (ROR_greater_equals(x, 35.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69317, _mut69318, _mut69319, _mut69320, _mut69321)) {
                    m = 8;
                } else {
                    m = 11;
                }
                final double xm = AOR_multiply(4.0, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69322, _mut69323, _mut69324, _mut69325);
                // ---------------------------------------------------------------------
                double t = (double) ((int) (AOR_plus((AOR_divide(x, TWOPI, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69326, _mut69327, _mut69328, _mut69329)), 0.5, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69330, _mut69331, _mut69332, _mut69333)));
                final double z = AOR_minus(AOR_minus(AOR_minus(x, AOR_multiply(t, TOWPI1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69334, _mut69335, _mut69336, _mut69337), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69338, _mut69339, _mut69340, _mut69341), AOR_multiply(t, TWOPI2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69342, _mut69343, _mut69344, _mut69345), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69346, _mut69347, _mut69348, _mut69349), AOR_divide((AOR_plus(alpha, 0.5, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69350, _mut69351, _mut69352, _mut69353)), PI2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69354, _mut69355, _mut69356, _mut69357), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69358, _mut69359, _mut69360, _mut69361);
                double vsin = FastMath.sin(z);
                double vcos = FastMath.cos(z);
                double gnu = AOR_multiply(2, alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69362, _mut69363, _mut69364, _mut69365);
                double capq;
                double capp;
                double s;
                double t1;
                double xk;
                for (int i = 1; ROR_less_equals(i, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69608, _mut69609, _mut69610, _mut69611, _mut69612); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                    s = AOR_multiply(AOR_multiply(AOR_multiply((AOR_minus(AOR_minus(xm, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69366, _mut69367, _mut69368, _mut69369), gnu, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69370, _mut69371, _mut69372, _mut69373)), (AOR_plus(AOR_minus(xm, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69374, _mut69375, _mut69376, _mut69377), gnu, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69378, _mut69379, _mut69380, _mut69381)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69382, _mut69383, _mut69384, _mut69385), xin, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69386, _mut69387, _mut69388, _mut69389), 0.5, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69390, _mut69391, _mut69392, _mut69393);
                    t = AOR_multiply((AOR_minus(gnu, (AOR_minus(xm, 3.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69394, _mut69395, _mut69396, _mut69397)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69398, _mut69399, _mut69400, _mut69401)), (AOR_plus(gnu, (AOR_minus(xm, 3.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69402, _mut69403, _mut69404, _mut69405)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69406, _mut69407, _mut69408, _mut69409)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69410, _mut69411, _mut69412, _mut69413);
                    capp = AOR_divide((AOR_multiply(s, t, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69414, _mut69415, _mut69416, _mut69417)), FACT[AOR_multiply(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69418, _mut69419, _mut69420, _mut69421)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69422, _mut69423, _mut69424, _mut69425);
                    t1 = AOR_multiply((AOR_minus(gnu, (AOR_plus(xm, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69426, _mut69427, _mut69428, _mut69429)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69430, _mut69431, _mut69432, _mut69433)), (AOR_plus(gnu, (AOR_plus(xm, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69434, _mut69435, _mut69436, _mut69437)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69438, _mut69439, _mut69440, _mut69441)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69442, _mut69443, _mut69444, _mut69445);
                    capq = AOR_divide((AOR_multiply(s, t1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69446, _mut69447, _mut69448, _mut69449)), FACT[AOR_plus(AOR_multiply(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69450, _mut69451, _mut69452, _mut69453), 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69454, _mut69455, _mut69456, _mut69457)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69458, _mut69459, _mut69460, _mut69461);
                    xk = xm;
                    int k = AOR_multiply(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69462, _mut69463, _mut69464, _mut69465);
                    t1 = t;
                    for (int j = 2; ROR_less_equals(j, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69554, _mut69555, _mut69556, _mut69557, _mut69558); j++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                        xk -= 4.0;
                        s = AOR_multiply((AOR_minus(AOR_minus(xk, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69466, _mut69467, _mut69468, _mut69469), gnu, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69470, _mut69471, _mut69472, _mut69473)), (AOR_plus(AOR_minus(xk, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69474, _mut69475, _mut69476, _mut69477), gnu, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69478, _mut69479, _mut69480, _mut69481)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69482, _mut69483, _mut69484, _mut69485);
                        t = AOR_multiply((AOR_minus(gnu, (AOR_minus(xk, 3.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69486, _mut69487, _mut69488, _mut69489)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69490, _mut69491, _mut69492, _mut69493)), (AOR_plus(gnu, (AOR_minus(xk, 3.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69494, _mut69495, _mut69496, _mut69497)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69498, _mut69499, _mut69500, _mut69501)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69502, _mut69503, _mut69504, _mut69505);
                        capp = AOR_multiply(AOR_multiply(AOR_multiply((AOR_plus(capp, AOR_divide(1, FACT[AOR_minus(k, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69506, _mut69507, _mut69508, _mut69509)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69510, _mut69511, _mut69512, _mut69513), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69514, _mut69515, _mut69516, _mut69517)), s, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69518, _mut69519, _mut69520, _mut69521), t, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69522, _mut69523, _mut69524, _mut69525), xin, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69526, _mut69527, _mut69528, _mut69529);
                        capq = AOR_multiply(AOR_multiply(AOR_multiply((AOR_plus(capq, AOR_divide(1, FACT[AOR_minus(k, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69530, _mut69531, _mut69532, _mut69533)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69534, _mut69535, _mut69536, _mut69537), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69538, _mut69539, _mut69540, _mut69541)), s, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69542, _mut69543, _mut69544, _mut69545), t1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69546, _mut69547, _mut69548, _mut69549), xin, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69550, _mut69551, _mut69552, _mut69553);
                        k -= 2;
                        t1 = t;
                    }
                    capp += 1;
                    capq = AOR_multiply(AOR_multiply((AOR_plus(capq, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69559, _mut69560, _mut69561, _mut69562)), (AOR_minus((AOR_multiply(gnu, gnu, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69563, _mut69564, _mut69565, _mut69566)), 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69567, _mut69568, _mut69569, _mut69570)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69571, _mut69572, _mut69573, _mut69574), (AOR_divide(0.125, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69575, _mut69576, _mut69577, _mut69578)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69579, _mut69580, _mut69581, _mut69582);
                    b[AOR_minus(i, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69583, _mut69584, _mut69585, _mut69586)] = AOR_multiply(xc, (AOR_minus(AOR_multiply(capp, vcos, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69587, _mut69588, _mut69589, _mut69590), AOR_multiply(capq, vsin, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69591, _mut69592, _mut69593, _mut69594), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69595, _mut69596, _mut69597, _mut69598)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69599, _mut69600, _mut69601, _mut69602);
                    if (ROR_equals(nb, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69603, _mut69604, _mut69605, _mut69606, _mut69607)) {
                        return new BesselJResult(MathArrays.copyOf(b, b.length), ncalc);
                    }
                    t = vsin;
                    vsin = -vcos;
                    vcos = t;
                    gnu += 2.0;
                }
                // ---------------------------------------------------------------------
                if (ROR_greater(nb, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69613, _mut69614, _mut69615, _mut69616, _mut69617)) {
                    gnu = AOR_plus(AOR_multiply(2, alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69618, _mut69619, _mut69620, _mut69621), 2.0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69622, _mut69623, _mut69624, _mut69625);
                    for (int j = 2; ROR_less(j, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69646, _mut69647, _mut69648, _mut69649, _mut69650); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                        b[j] = AOR_minus(AOR_divide(AOR_multiply(gnu, b[AOR_minus(j, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69626, _mut69627, _mut69628, _mut69629)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69630, _mut69631, _mut69632, _mut69633), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69634, _mut69635, _mut69636, _mut69637), b[AOR_minus(j, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69638, _mut69639, _mut69640, _mut69641)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69642, _mut69643, _mut69644, _mut69645);
                        gnu += 2.0;
                    }
                }
            } else {
                // ---------------------------------------------------------------------
                final int nbmx = AOR_minus(nb, magx, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68732, _mut68733, _mut68734, _mut68735);
                int n = AOR_plus(magx, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68736, _mut68737, _mut68738, _mut68739);
                int nstart = 0;
                int nend = 0;
                double en = AOR_multiply(2, (AOR_plus(n, alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68740, _mut68741, _mut68742, _mut68743)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68744, _mut68745, _mut68746, _mut68747);
                double plast = 1;
                double p = AOR_divide(en, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68748, _mut68749, _mut68750, _mut68751);
                double pold;
                // ---------------------------------------------------------------------
                double test = AOR_multiply(2, ENSIG, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68752, _mut68753, _mut68754, _mut68755);
                boolean readyToInitialize = false;
                if (ROR_greater_equals(nbmx, 3, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68756, _mut68757, _mut68758, _mut68759, _mut68760)) {
                    // ---------------------------------------------------------------------
                    tover = AOR_divide(ENTEN, ENSIG, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68761, _mut68762, _mut68763, _mut68764);
                    nstart = AOR_plus(magx, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68765, _mut68766, _mut68767, _mut68768);
                    nend = AOR_minus(nb, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68769, _mut68770, _mut68771, _mut68772);
                    en = AOR_multiply(2, (AOR_plus(AOR_minus(nstart, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68773, _mut68774, _mut68775, _mut68776), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68777, _mut68778, _mut68779, _mut68780)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68781, _mut68782, _mut68783, _mut68784);
                    double psave;
                    double psavel;
                    for (int k = nstart; ROR_less_equals(k, nend, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68881, _mut68882, _mut68883, _mut68884, _mut68885); k++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                        n = k;
                        en += 2.0;
                        pold = plast;
                        plast = p;
                        p = AOR_minus((AOR_divide(AOR_multiply(en, plast, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68785, _mut68786, _mut68787, _mut68788), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68789, _mut68790, _mut68791, _mut68792)), pold, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68793, _mut68794, _mut68795, _mut68796);
                        if (ROR_greater(p, tover, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68797, _mut68798, _mut68799, _mut68800, _mut68801)) {
                            // ---------------------------------------------------------------------
                            tover = ENTEN;
                            p /= tover;
                            plast /= tover;
                            psave = p;
                            psavel = plast;
                            nstart = AOR_plus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68802, _mut68803, _mut68804, _mut68805);
                            do {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                                n += 1;
                                en += 2.0;
                                pold = plast;
                                plast = p;
                                p = AOR_minus((AOR_divide(AOR_multiply(en, plast, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68806, _mut68807, _mut68808, _mut68809), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68810, _mut68811, _mut68812, _mut68813)), pold, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68814, _mut68815, _mut68816, _mut68817);
                            } while (ROR_less_equals(p, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68818, _mut68819, _mut68820, _mut68821, _mut68822));
                            tempb = AOR_divide(en, x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68823, _mut68824, _mut68825, _mut68826);
                            // ---------------------------------------------------------------------
                            test = AOR_multiply(AOR_multiply(pold, plast, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68827, _mut68828, _mut68829, _mut68830), (AOR_minus(0.5, AOR_divide(0.5, (AOR_multiply(tempb, tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68831, _mut68832, _mut68833, _mut68834)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68835, _mut68836, _mut68837, _mut68838), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68839, _mut68840, _mut68841, _mut68842)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68843, _mut68844, _mut68845, _mut68846);
                            test /= ENSIG;
                            p = AOR_multiply(plast, tover, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68847, _mut68848, _mut68849, _mut68850);
                            n -= 1;
                            en -= 2.0;
                            nend = FastMath.min(nb, n);
                            for (int l = nstart; ROR_less_equals(l, nend, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68876, _mut68877, _mut68878, _mut68879, _mut68880); l++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                                pold = psavel;
                                psavel = psave;
                                psave = AOR_minus((AOR_divide(AOR_multiply(en, psavel, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68851, _mut68852, _mut68853, _mut68854), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68855, _mut68856, _mut68857, _mut68858)), pold, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68859, _mut68860, _mut68861, _mut68862);
                                if (ROR_greater(AOR_multiply(psave, psavel, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68863, _mut68864, _mut68865, _mut68866), test, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68867, _mut68868, _mut68869, _mut68870, _mut68871)) {
                                    ncalc = AOR_minus(l, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68872, _mut68873, _mut68874, _mut68875);
                                    readyToInitialize = true;
                                    break;
                                }
                            }
                            ncalc = nend;
                            readyToInitialize = true;
                            break;
                        }
                    }
                    if (!readyToInitialize) {
                        n = nend;
                        en = AOR_multiply(2, (AOR_plus(n, alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68886, _mut68887, _mut68888, _mut68889)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68890, _mut68891, _mut68892, _mut68893);
                        // ---------------------------------------------------------------------
                        test = FastMath.max(test, AOR_multiply(FastMath.sqrt(AOR_multiply(plast, ENSIG, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68894, _mut68895, _mut68896, _mut68897)), FastMath.sqrt(AOR_multiply(2, p, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68898, _mut68899, _mut68900, _mut68901)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68902, _mut68903, _mut68904, _mut68905));
                    }
                }
                // ---------------------------------------------------------------------
                if (!readyToInitialize) {
                    do {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                        n += 1;
                        en += 2.0;
                        pold = plast;
                        plast = p;
                        p = AOR_minus((AOR_divide(AOR_multiply(en, plast, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68906, _mut68907, _mut68908, _mut68909), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68910, _mut68911, _mut68912, _mut68913)), pold, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68914, _mut68915, _mut68916, _mut68917);
                    } while (ROR_less(p, test, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68918, _mut68919, _mut68920, _mut68921, _mut68922));
                }
                // ---------------------------------------------------------------------
                n += 1;
                en += 2.0;
                tempb = 0;
                tempa = AOR_divide(1, p, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68923, _mut68924, _mut68925, _mut68926);
                int m = AOR_minus((AOR_multiply(2, n, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68927, _mut68928, _mut68929, _mut68930)), AOR_multiply(4, (AOR_divide(n, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68931, _mut68932, _mut68933, _mut68934)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68935, _mut68936, _mut68937, _mut68938), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68939, _mut68940, _mut68941, _mut68942);
                double sum = 0;
                double em = (double) (AOR_divide(n, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68943, _mut68944, _mut68945, _mut68946));
                alpem = AOR_plus(AOR_minus(em, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68947, _mut68948, _mut68949, _mut68950), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68951, _mut68952, _mut68953, _mut68954);
                alp2em = AOR_plus(AOR_multiply(2, em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68955, _mut68956, _mut68957, _mut68958), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68959, _mut68960, _mut68961, _mut68962);
                if (ROR_not_equals(m, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68963, _mut68964, _mut68965, _mut68966, _mut68967)) {
                    sum = AOR_divide(AOR_multiply(AOR_multiply(tempa, alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68968, _mut68969, _mut68970, _mut68971), alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68972, _mut68973, _mut68974, _mut68975), em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68976, _mut68977, _mut68978, _mut68979);
                }
                nend = AOR_minus(n, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68980, _mut68981, _mut68982, _mut68983);
                boolean readyToNormalize = false;
                boolean calculatedB0 = false;
                // ---------------------------------------------------------------------
                for (int l = 1; ROR_less_equals(l, nend, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69047, _mut69048, _mut69049, _mut69050, _mut69051); l++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                    n -= 1;
                    en -= 2.0;
                    tempc = tempb;
                    tempb = tempa;
                    tempa = AOR_minus((AOR_divide(AOR_multiply(en, tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68984, _mut68985, _mut68986, _mut68987), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68988, _mut68989, _mut68990, _mut68991)), tempc, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68992, _mut68993, _mut68994, _mut68995);
                    m = AOR_minus(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68996, _mut68997, _mut68998, _mut68999);
                    if (ROR_not_equals(m, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69000, _mut69001, _mut69002, _mut69003, _mut69004)) {
                        em -= 1;
                        alp2em = AOR_plus(AOR_multiply(2, em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69005, _mut69006, _mut69007, _mut69008), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69009, _mut69010, _mut69011, _mut69012);
                        if (ROR_equals(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69013, _mut69014, _mut69015, _mut69016, _mut69017)) {
                            break;
                        }
                        alpem = AOR_plus(AOR_minus(em, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69018, _mut69019, _mut69020, _mut69021), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69022, _mut69023, _mut69024, _mut69025);
                        if (ROR_equals(alpem, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69026, _mut69027, _mut69028, _mut69029, _mut69030)) {
                            alpem = 1;
                        }
                        sum = AOR_divide(AOR_multiply((AOR_plus(sum, AOR_multiply(tempa, alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69031, _mut69032, _mut69033, _mut69034), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69035, _mut69036, _mut69037, _mut69038)), alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69039, _mut69040, _mut69041, _mut69042), em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69043, _mut69044, _mut69045, _mut69046);
                    }
                }
                // ---------------------------------------------------------------------
                b[AOR_minus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69052, _mut69053, _mut69054, _mut69055)] = tempa;
                if (ROR_greater_equals(nend, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69056, _mut69057, _mut69058, _mut69059, _mut69060)) {
                    if (ROR_less_equals(nb, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69061, _mut69062, _mut69063, _mut69064, _mut69065)) {
                        alp2em = alpha;
                        if (ROR_equals(AOR_plus(alpha, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69137, _mut69138, _mut69139, _mut69140), 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69141, _mut69142, _mut69143, _mut69144, _mut69145)) {
                            alp2em = 1;
                        }
                        sum += AOR_multiply(b[0], alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69146, _mut69147, _mut69148, _mut69149);
                        readyToNormalize = true;
                    } else {
                        // ---------------------------------------------------------------------
                        n -= 1;
                        en -= 2.0;
                        b[AOR_minus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69066, _mut69067, _mut69068, _mut69069)] = AOR_minus((AOR_divide(AOR_multiply(en, tempa, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69070, _mut69071, _mut69072, _mut69073), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69074, _mut69075, _mut69076, _mut69077)), tempb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69078, _mut69079, _mut69080, _mut69081);
                        if (ROR_equals(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69082, _mut69083, _mut69084, _mut69085, _mut69086)) {
                            calculatedB0 = true;
                        } else {
                            m = AOR_minus(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69087, _mut69088, _mut69089, _mut69090);
                            if (ROR_not_equals(m, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69091, _mut69092, _mut69093, _mut69094, _mut69095)) {
                                em -= 1;
                                alp2em = AOR_plus(AOR_multiply(2, em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69096, _mut69097, _mut69098, _mut69099), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69100, _mut69101, _mut69102, _mut69103);
                                alpem = AOR_plus(AOR_minus(em, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69104, _mut69105, _mut69106, _mut69107), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69108, _mut69109, _mut69110, _mut69111);
                                if (ROR_equals(alpem, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69112, _mut69113, _mut69114, _mut69115, _mut69116)) {
                                    alpem = 1;
                                }
                                sum = AOR_divide(AOR_multiply((AOR_plus(sum, (AOR_multiply(b[AOR_minus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69117, _mut69118, _mut69119, _mut69120)], alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69121, _mut69122, _mut69123, _mut69124)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69125, _mut69126, _mut69127, _mut69128)), alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69129, _mut69130, _mut69131, _mut69132), em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69133, _mut69134, _mut69135, _mut69136);
                            }
                        }
                    }
                }
                if ((_mut69150 ? (!readyToNormalize || !calculatedB0) : (!readyToNormalize && !calculatedB0))) {
                    nend = AOR_minus(n, 2, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69151, _mut69152, _mut69153, _mut69154);
                    if (ROR_not_equals(nend, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69155, _mut69156, _mut69157, _mut69158, _mut69159)) {
                        for (int l = 1; ROR_less_equals(l, nend, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69230, _mut69231, _mut69232, _mut69233, _mut69234); l++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                            n -= 1;
                            en -= 2.0;
                            b[AOR_minus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69160, _mut69161, _mut69162, _mut69163)] = AOR_minus((AOR_divide(AOR_multiply(en, b[n], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69164, _mut69165, _mut69166, _mut69167), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69168, _mut69169, _mut69170, _mut69171)), b[AOR_plus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69172, _mut69173, _mut69174, _mut69175)], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69176, _mut69177, _mut69178, _mut69179);
                            m = AOR_minus(2, m, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69180, _mut69181, _mut69182, _mut69183);
                            if (ROR_not_equals(m, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69184, _mut69185, _mut69186, _mut69187, _mut69188)) {
                                em -= 1;
                                alp2em = AOR_plus(AOR_multiply(2, em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69189, _mut69190, _mut69191, _mut69192), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69193, _mut69194, _mut69195, _mut69196);
                                alpem = AOR_plus(AOR_minus(em, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69197, _mut69198, _mut69199, _mut69200), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69201, _mut69202, _mut69203, _mut69204);
                                if (ROR_equals(alpem, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69205, _mut69206, _mut69207, _mut69208, _mut69209)) {
                                    alpem = 1;
                                }
                                sum = AOR_divide(AOR_multiply((AOR_plus(sum, AOR_multiply(b[AOR_minus(n, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69210, _mut69211, _mut69212, _mut69213)], alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69214, _mut69215, _mut69216, _mut69217), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69218, _mut69219, _mut69220, _mut69221)), alpem, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69222, _mut69223, _mut69224, _mut69225), em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69226, _mut69227, _mut69228, _mut69229);
                            }
                        }
                    }
                }
                // ---------------------------------------------------------------------
                if (!readyToNormalize) {
                    if (!calculatedB0) {
                        b[0] = AOR_minus(AOR_divide(AOR_multiply(AOR_multiply(2.0, (AOR_plus(alpha, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69235, _mut69236, _mut69237, _mut69238)), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69239, _mut69240, _mut69241, _mut69242), b[1], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69243, _mut69244, _mut69245, _mut69246), x, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69247, _mut69248, _mut69249, _mut69250), b[2], "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69251, _mut69252, _mut69253, _mut69254);
                    }
                    em -= 1;
                    alp2em = AOR_plus(AOR_multiply(2, em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69255, _mut69256, _mut69257, _mut69258), alpha, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69259, _mut69260, _mut69261, _mut69262);
                    if (ROR_equals(alp2em, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69263, _mut69264, _mut69265, _mut69266, _mut69267)) {
                        alp2em = 1;
                    }
                    sum += AOR_multiply(b[0], alp2em, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69268, _mut69269, _mut69270, _mut69271);
                }
                if (ROR_greater(FastMath.abs(alpha), 1e-16, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69272, _mut69273, _mut69274, _mut69275, _mut69276)) {
                    sum *= AOR_multiply(Gamma.gamma(alpha), FastMath.pow(AOR_multiply(x, 0.5, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69277, _mut69278, _mut69279, _mut69280), -alpha), "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69281, _mut69282, _mut69283, _mut69284);
                }
                tempa = ENMTEN;
                if (ROR_greater(sum, 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69285, _mut69286, _mut69287, _mut69288, _mut69289)) {
                    tempa *= sum;
                }
                for (n = 0; ROR_less(n, nb, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69295, _mut69296, _mut69297, _mut69298, _mut69299); n++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.BesselJ.rjBesl_245");
                    if (ROR_less(FastMath.abs(b[n]), tempa, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut69290, _mut69291, _mut69292, _mut69293, _mut69294)) {
                        b[n] = 0;
                    }
                    b[n] /= sum;
                }
            }
        } else {
            if (ROR_greater(b.length, 0, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68698, _mut68699, _mut68700, _mut68701, _mut68702)) {
                b[0] = 0;
            }
            ncalc = AOR_minus(FastMath.min(nb, 0), 1, "org.apache.commons.math3.special.BesselJ.rjBesl_245", _mut68703, _mut68704, _mut68705, _mut68706);
        }
        return new BesselJResult(MathArrays.copyOf(b, b.length), ncalc);
    }
}
