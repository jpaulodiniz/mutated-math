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
package org.apache.commons.math3.geometry.euclidean.threed;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class Rotation implements Serializable {

    @Conditional
    public static boolean _mut81227 = false, _mut81228 = false, _mut81229 = false, _mut81230 = false, _mut81231 = false, _mut81232 = false, _mut81233 = false, _mut81234 = false, _mut81235 = false, _mut81236 = false, _mut81237 = false, _mut81238 = false, _mut81239 = false, _mut81240 = false, _mut81241 = false, _mut81242 = false, _mut81243 = false, _mut81244 = false, _mut81245 = false, _mut81246 = false, _mut81247 = false, _mut81248 = false, _mut81249 = false, _mut81250 = false, _mut81251 = false, _mut81252 = false, _mut81253 = false, _mut81254 = false, _mut81255 = false, _mut81256 = false, _mut81257 = false, _mut81258 = false, _mut81259 = false, _mut81260 = false, _mut81261 = false, _mut81262 = false, _mut81263 = false, _mut81264 = false, _mut81265 = false, _mut81266 = false, _mut81267 = false, _mut81268 = false, _mut81269 = false, _mut81270 = false, _mut81271 = false, _mut81272 = false, _mut81273 = false, _mut81274 = false, _mut81275 = false, _mut81276 = false, _mut81277 = false, _mut81278 = false, _mut81279 = false, _mut81280 = false, _mut81281 = false, _mut81282 = false, _mut81283 = false, _mut81284 = false, _mut81285 = false, _mut81286 = false, _mut81287 = false, _mut81288 = false, _mut81289 = false, _mut81290 = false, _mut81291 = false, _mut81292 = false, _mut81293 = false, _mut81294 = false, _mut81295 = false, _mut81296 = false, _mut81297 = false, _mut81298 = false, _mut81299 = false, _mut81300 = false, _mut81301 = false, _mut81302 = false, _mut81303 = false, _mut81304 = false, _mut81305 = false, _mut81306 = false, _mut81307 = false, _mut81308 = false, _mut81309 = false, _mut81310 = false, _mut81311 = false, _mut81312 = false, _mut81313 = false, _mut81314 = false, _mut81315 = false, _mut81316 = false, _mut81317 = false, _mut81318 = false, _mut81319 = false, _mut81320 = false, _mut81321 = false, _mut81322 = false, _mut81323 = false, _mut81324 = false, _mut81325 = false, _mut81326 = false, _mut81327 = false, _mut81328 = false, _mut81329 = false, _mut81330 = false, _mut81331 = false, _mut81332 = false, _mut81333 = false, _mut81334 = false, _mut81335 = false, _mut81336 = false, _mut81337 = false, _mut81338 = false, _mut81339 = false, _mut81340 = false, _mut81341 = false, _mut81342 = false, _mut81343 = false, _mut81344 = false, _mut81345 = false, _mut81346 = false, _mut81347 = false, _mut81348 = false, _mut81349 = false, _mut81350 = false, _mut81351 = false, _mut81352 = false, _mut81353 = false, _mut81354 = false, _mut81355 = false, _mut81356 = false, _mut81357 = false, _mut81358 = false, _mut81359 = false, _mut81360 = false, _mut81361 = false, _mut81362 = false, _mut81363 = false, _mut81364 = false, _mut81365 = false, _mut81366 = false, _mut81367 = false, _mut81368 = false, _mut81369 = false, _mut81370 = false, _mut81371 = false, _mut81372 = false, _mut81373 = false, _mut81374 = false, _mut81375 = false, _mut81376 = false, _mut81377 = false, _mut81378 = false, _mut81379 = false, _mut81380 = false, _mut81381 = false, _mut81382 = false, _mut81383 = false, _mut81384 = false, _mut81385 = false, _mut81386 = false, _mut81387 = false, _mut81388 = false, _mut81389 = false, _mut81390 = false, _mut81391 = false, _mut81392 = false, _mut81393 = false, _mut81394 = false, _mut81395 = false, _mut81396 = false, _mut81397 = false, _mut81398 = false, _mut81399 = false, _mut81400 = false, _mut81401 = false, _mut81402 = false, _mut81403 = false, _mut81404 = false, _mut81405 = false, _mut81406 = false, _mut81407 = false, _mut81408 = false, _mut81409 = false, _mut81410 = false, _mut81411 = false, _mut81412 = false, _mut81413 = false, _mut81414 = false, _mut81415 = false, _mut81416 = false, _mut81417 = false, _mut81418 = false, _mut81419 = false, _mut81420 = false, _mut81421 = false, _mut81422 = false, _mut81423 = false, _mut81424 = false, _mut81425 = false, _mut81426 = false, _mut81427 = false, _mut81428 = false, _mut81429 = false, _mut81430 = false, _mut81431 = false, _mut81432 = false, _mut81433 = false, _mut81434 = false, _mut81435 = false, _mut81436 = false, _mut81437 = false, _mut81438 = false, _mut81439 = false, _mut81440 = false, _mut81441 = false, _mut81442 = false, _mut81443 = false, _mut81444 = false, _mut81445 = false, _mut81446 = false, _mut81447 = false, _mut81448 = false, _mut81449 = false, _mut81450 = false, _mut81451 = false, _mut81452 = false, _mut81453 = false, _mut81454 = false, _mut81455 = false, _mut81456 = false, _mut81457 = false, _mut81458 = false, _mut81459 = false, _mut81460 = false, _mut81461 = false, _mut81462 = false, _mut81463 = false, _mut81464 = false, _mut81465 = false, _mut81466 = false, _mut81467 = false, _mut81468 = false, _mut81469 = false, _mut81470 = false, _mut81471 = false, _mut81472 = false, _mut81473 = false, _mut81474 = false, _mut81475 = false, _mut81476 = false, _mut81477 = false, _mut81478 = false, _mut81479 = false, _mut81480 = false, _mut81481 = false, _mut81482 = false, _mut81483 = false, _mut81484 = false, _mut81485 = false, _mut81486 = false, _mut81487 = false, _mut81488 = false, _mut81489 = false, _mut81490 = false, _mut81491 = false, _mut81492 = false, _mut81493 = false, _mut81494 = false, _mut81495 = false, _mut81496 = false, _mut81497 = false, _mut81498 = false, _mut81499 = false, _mut81500 = false, _mut81501 = false, _mut81502 = false, _mut81503 = false, _mut81504 = false, _mut81505 = false, _mut81506 = false, _mut81507 = false, _mut81508 = false, _mut81509 = false, _mut81510 = false, _mut81511 = false, _mut81512 = false, _mut81513 = false, _mut81514 = false, _mut81515 = false, _mut81516 = false, _mut81517 = false, _mut81518 = false, _mut81519 = false, _mut81520 = false, _mut81521 = false, _mut81522 = false, _mut81523 = false, _mut81524 = false, _mut81525 = false, _mut81526 = false, _mut81527 = false, _mut81528 = false, _mut81529 = false, _mut81530 = false, _mut81531 = false, _mut81532 = false, _mut81533 = false, _mut81534 = false, _mut81535 = false, _mut81536 = false, _mut81537 = false, _mut81538 = false, _mut81539 = false, _mut81540 = false, _mut81541 = false, _mut81542 = false, _mut81543 = false, _mut81544 = false, _mut81545 = false, _mut81546 = false, _mut81547 = false, _mut81548 = false, _mut81549 = false, _mut81550 = false, _mut81551 = false, _mut81552 = false, _mut81553 = false, _mut81554 = false, _mut81555 = false, _mut81556 = false, _mut81557 = false, _mut81558 = false, _mut81559 = false, _mut81560 = false, _mut81561 = false, _mut81562 = false, _mut81563 = false, _mut81564 = false, _mut81565 = false, _mut81566 = false, _mut81567 = false, _mut81568 = false, _mut81569 = false, _mut81570 = false, _mut81571 = false, _mut81572 = false, _mut81573 = false, _mut81574 = false, _mut81575 = false, _mut81576 = false, _mut81577 = false, _mut81578 = false, _mut81579 = false, _mut81580 = false, _mut81581 = false, _mut81582 = false, _mut81583 = false, _mut81584 = false, _mut81585 = false, _mut81586 = false, _mut81587 = false, _mut81588 = false, _mut81589 = false, _mut81590 = false, _mut81591 = false, _mut81592 = false, _mut81593 = false, _mut81594 = false, _mut81595 = false, _mut81596 = false, _mut81597 = false, _mut81598 = false, _mut81599 = false, _mut81600 = false, _mut81601 = false, _mut81602 = false, _mut81603 = false, _mut81604 = false, _mut81605 = false, _mut81606 = false, _mut81607 = false, _mut81608 = false, _mut81609 = false, _mut81610 = false, _mut81611 = false, _mut81612 = false, _mut81613 = false, _mut81614 = false, _mut81615 = false, _mut81616 = false, _mut81617 = false, _mut81618 = false, _mut81619 = false, _mut81620 = false, _mut81621 = false, _mut81622 = false, _mut81623 = false, _mut81624 = false, _mut81625 = false, _mut81626 = false, _mut81627 = false, _mut81628 = false, _mut81629 = false, _mut81630 = false, _mut81631 = false, _mut81632 = false, _mut81633 = false, _mut81634 = false, _mut81635 = false, _mut81636 = false, _mut81637 = false, _mut81638 = false, _mut81639 = false, _mut81640 = false, _mut81641 = false, _mut81642 = false, _mut81643 = false, _mut81644 = false, _mut81645 = false, _mut81646 = false, _mut81647 = false, _mut81648 = false, _mut81649 = false, _mut81650 = false, _mut81651 = false, _mut81652 = false, _mut81653 = false, _mut81654 = false, _mut81655 = false, _mut81656 = false, _mut81657 = false, _mut81658 = false, _mut81659 = false, _mut81660 = false, _mut81661 = false, _mut81662 = false, _mut81663 = false, _mut81664 = false, _mut81665 = false, _mut81666 = false, _mut81667 = false, _mut81668 = false, _mut81669 = false, _mut81670 = false, _mut81671 = false, _mut81672 = false, _mut81673 = false, _mut81674 = false, _mut81675 = false, _mut81676 = false, _mut81677 = false, _mut81678 = false, _mut81679 = false, _mut81680 = false, _mut81681 = false, _mut81682 = false, _mut81683 = false, _mut81684 = false, _mut81685 = false, _mut81686 = false, _mut81687 = false, _mut81688 = false, _mut81689 = false, _mut81690 = false, _mut81691 = false, _mut81692 = false, _mut81693 = false, _mut81694 = false, _mut81695 = false, _mut81696 = false, _mut81697 = false, _mut81698 = false, _mut81699 = false, _mut81700 = false, _mut81701 = false, _mut81702 = false, _mut81703 = false, _mut81704 = false, _mut81705 = false, _mut81706 = false, _mut81707 = false, _mut81708 = false, _mut81709 = false, _mut81710 = false, _mut81711 = false, _mut81712 = false, _mut81713 = false, _mut81714 = false, _mut81715 = false, _mut81716 = false, _mut81717 = false, _mut81718 = false, _mut81719 = false, _mut81720 = false, _mut81721 = false, _mut81722 = false, _mut81723 = false, _mut81724 = false, _mut81725 = false, _mut81726 = false, _mut81727 = false, _mut81728 = false, _mut81729 = false, _mut81730 = false, _mut81731 = false, _mut81732 = false, _mut81733 = false, _mut81734 = false, _mut81735 = false, _mut81736 = false, _mut81737 = false, _mut81738 = false, _mut81739 = false, _mut81740 = false, _mut81741 = false, _mut81742 = false, _mut81743 = false, _mut81744 = false, _mut81745 = false, _mut81746 = false, _mut81747 = false, _mut81748 = false, _mut81749 = false, _mut81750 = false, _mut81751 = false, _mut81752 = false, _mut81753 = false, _mut81754 = false, _mut81755 = false, _mut81756 = false, _mut81757 = false, _mut81758 = false, _mut81759 = false, _mut81760 = false, _mut81761 = false, _mut81762 = false, _mut81763 = false, _mut81764 = false, _mut81765 = false, _mut81766 = false, _mut81767 = false, _mut81768 = false, _mut81769 = false, _mut81770 = false, _mut81771 = false, _mut81772 = false, _mut81773 = false, _mut81774 = false, _mut81775 = false, _mut81776 = false, _mut81777 = false, _mut81778 = false, _mut81779 = false, _mut81780 = false, _mut81781 = false, _mut81782 = false, _mut81783 = false, _mut81784 = false, _mut81785 = false, _mut81786 = false, _mut81787 = false, _mut81788 = false, _mut81789 = false, _mut81790 = false, _mut81791 = false, _mut81792 = false, _mut81793 = false, _mut81794 = false, _mut81795 = false, _mut81796 = false, _mut81797 = false, _mut81798 = false, _mut81799 = false, _mut81800 = false, _mut81801 = false, _mut81802 = false, _mut81803 = false, _mut81804 = false, _mut81805 = false, _mut81806 = false, _mut81807 = false, _mut81808 = false, _mut81809 = false, _mut81810 = false, _mut81811 = false, _mut81812 = false, _mut81813 = false, _mut81814 = false, _mut81815 = false, _mut81816 = false, _mut81817 = false, _mut81818 = false, _mut81819 = false, _mut81820 = false, _mut81821 = false, _mut81822 = false, _mut81823 = false, _mut81824 = false, _mut81825 = false, _mut81826 = false, _mut81827 = false, _mut81828 = false, _mut81829 = false, _mut81830 = false, _mut81831 = false, _mut81832 = false, _mut81833 = false, _mut81834 = false, _mut81835 = false, _mut81836 = false, _mut81837 = false, _mut81838 = false, _mut81839 = false, _mut81840 = false, _mut81841 = false, _mut81842 = false, _mut81843 = false, _mut81844 = false, _mut81845 = false, _mut81846 = false, _mut81847 = false, _mut81848 = false, _mut81849 = false, _mut81850 = false, _mut81851 = false, _mut81852 = false, _mut81853 = false, _mut81854 = false, _mut81855 = false, _mut81856 = false, _mut81857 = false, _mut81858 = false, _mut81859 = false, _mut81860 = false, _mut81861 = false, _mut81862 = false, _mut81863 = false, _mut81864 = false, _mut81865 = false, _mut81866 = false, _mut81867 = false, _mut81868 = false, _mut81869 = false, _mut81870 = false, _mut81871 = false, _mut81872 = false, _mut81873 = false, _mut81874 = false, _mut81875 = false, _mut81876 = false, _mut81877 = false, _mut81878 = false, _mut81879 = false, _mut81880 = false, _mut81881 = false, _mut81882 = false, _mut81883 = false, _mut81884 = false, _mut81885 = false, _mut81886 = false, _mut81887 = false, _mut81888 = false, _mut81889 = false, _mut81890 = false, _mut81891 = false, _mut81892 = false, _mut81893 = false, _mut81894 = false, _mut81895 = false, _mut81896 = false, _mut81897 = false, _mut81898 = false, _mut81899 = false, _mut81900 = false, _mut81901 = false, _mut81902 = false, _mut81903 = false, _mut81904 = false, _mut81905 = false, _mut81906 = false, _mut81907 = false, _mut81908 = false, _mut81909 = false, _mut81910 = false, _mut81911 = false, _mut81912 = false, _mut81913 = false, _mut81914 = false, _mut81915 = false, _mut81916 = false, _mut81917 = false, _mut81918 = false, _mut81919 = false, _mut81920 = false, _mut81921 = false, _mut81922 = false, _mut81923 = false, _mut81924 = false, _mut81925 = false, _mut81926 = false, _mut81927 = false, _mut81928 = false, _mut81929 = false, _mut81930 = false, _mut81931 = false, _mut81932 = false, _mut81933 = false, _mut81934 = false, _mut81935 = false, _mut81936 = false, _mut81937 = false, _mut81938 = false, _mut81939 = false, _mut81940 = false, _mut81941 = false, _mut81942 = false, _mut81943 = false, _mut81944 = false, _mut81945 = false, _mut81946 = false, _mut81947 = false, _mut81948 = false, _mut81949 = false, _mut81950 = false, _mut81951 = false, _mut81952 = false, _mut81953 = false, _mut81954 = false, _mut81955 = false, _mut81956 = false, _mut81957 = false, _mut81958 = false, _mut81959 = false, _mut81960 = false, _mut81961 = false, _mut81962 = false, _mut81963 = false, _mut81964 = false, _mut81965 = false, _mut81966 = false, _mut81967 = false, _mut81968 = false, _mut81969 = false, _mut81970 = false, _mut81971 = false, _mut81972 = false, _mut81973 = false, _mut81974 = false, _mut81975 = false, _mut81976 = false, _mut81977 = false, _mut81978 = false, _mut81979 = false, _mut81980 = false, _mut81981 = false, _mut81982 = false, _mut81983 = false, _mut81984 = false, _mut81985 = false, _mut81986 = false, _mut81987 = false, _mut81988 = false, _mut81989 = false, _mut81990 = false, _mut81991 = false, _mut81992 = false, _mut81993 = false, _mut81994 = false, _mut81995 = false, _mut81996 = false, _mut81997 = false, _mut81998 = false, _mut81999 = false, _mut82000 = false, _mut82001 = false, _mut82002 = false, _mut82003 = false, _mut82004 = false, _mut82005 = false, _mut82006 = false, _mut82007 = false, _mut82008 = false, _mut82009 = false, _mut82010 = false, _mut82011 = false, _mut82012 = false, _mut82013 = false, _mut82014 = false, _mut82015 = false, _mut82016 = false, _mut82017 = false, _mut82018 = false, _mut82019 = false, _mut82020 = false, _mut82021 = false, _mut82022 = false, _mut82023 = false, _mut82024 = false, _mut82025 = false, _mut82026 = false, _mut82027 = false, _mut82028 = false, _mut82029 = false, _mut82030 = false, _mut82031 = false, _mut82032 = false, _mut82033 = false, _mut82034 = false, _mut82035 = false, _mut82036 = false, _mut82037 = false, _mut82038 = false, _mut82039 = false, _mut82040 = false, _mut82041 = false, _mut82042 = false, _mut82043 = false, _mut82044 = false, _mut82045 = false, _mut82046 = false, _mut82047 = false, _mut82048 = false, _mut82049 = false, _mut82050 = false, _mut82051 = false, _mut82052 = false, _mut82053 = false, _mut82054 = false, _mut82055 = false, _mut82056 = false, _mut82057 = false, _mut82058 = false, _mut82059 = false, _mut82060 = false, _mut82061 = false, _mut82062 = false, _mut82063 = false, _mut82064 = false, _mut82065 = false, _mut82066 = false, _mut82067 = false, _mut82068 = false, _mut82069 = false, _mut82070 = false, _mut82071 = false, _mut82072 = false, _mut82073 = false, _mut82074 = false, _mut82075 = false, _mut82076 = false, _mut82077 = false, _mut82078 = false, _mut82079 = false, _mut82080 = false, _mut82081 = false, _mut82082 = false, _mut82083 = false, _mut82084 = false, _mut82085 = false, _mut82086 = false, _mut82087 = false, _mut82088 = false, _mut82089 = false, _mut82090 = false, _mut82091 = false, _mut82092 = false, _mut82093 = false, _mut82094 = false, _mut82095 = false, _mut82096 = false, _mut82097 = false, _mut82098 = false, _mut82099 = false, _mut82100 = false, _mut82101 = false, _mut82102 = false, _mut82103 = false, _mut82104 = false, _mut82105 = false, _mut82106 = false, _mut82107 = false, _mut82108 = false, _mut82109 = false, _mut82110 = false, _mut82111 = false, _mut82112 = false, _mut82113 = false, _mut82114 = false, _mut82115 = false, _mut82116 = false, _mut82117 = false, _mut82118 = false, _mut82119 = false, _mut82120 = false, _mut82121 = false, _mut82122 = false, _mut82123 = false, _mut82124 = false, _mut82125 = false, _mut82126 = false, _mut82127 = false, _mut82128 = false, _mut82129 = false, _mut82130 = false, _mut82131 = false, _mut82132 = false, _mut82133 = false, _mut82134 = false, _mut82135 = false, _mut82136 = false, _mut82137 = false, _mut82138 = false, _mut82139 = false, _mut82140 = false, _mut82141 = false, _mut82142 = false, _mut82143 = false, _mut82144 = false, _mut82145 = false, _mut82146 = false, _mut82147 = false, _mut82148 = false, _mut82149 = false, _mut82150 = false, _mut82151 = false, _mut82152 = false, _mut82153 = false, _mut82154 = false, _mut82155 = false, _mut82156 = false, _mut82157 = false, _mut82158 = false, _mut82159 = false, _mut82160 = false, _mut82161 = false, _mut82162 = false, _mut82163 = false, _mut82164 = false, _mut82165 = false, _mut82166 = false, _mut82167 = false, _mut82168 = false, _mut82169 = false, _mut82170 = false, _mut82171 = false, _mut82172 = false, _mut82173 = false, _mut82174 = false, _mut82175 = false, _mut82176 = false, _mut82177 = false, _mut82178 = false, _mut82179 = false, _mut82180 = false, _mut82181 = false, _mut82182 = false, _mut82183 = false, _mut82184 = false, _mut82185 = false, _mut82186 = false, _mut82187 = false, _mut82188 = false, _mut82189 = false, _mut82190 = false, _mut82191 = false, _mut82192 = false, _mut82193 = false, _mut82194 = false, _mut82195 = false, _mut82196 = false, _mut82197 = false, _mut82198 = false, _mut82199 = false, _mut82200 = false, _mut82201 = false, _mut82202 = false, _mut82203 = false, _mut82204 = false, _mut82205 = false, _mut82206 = false, _mut82207 = false, _mut82208 = false, _mut82209 = false, _mut82210 = false, _mut82211 = false, _mut82212 = false, _mut82213 = false, _mut82214 = false, _mut82215 = false, _mut82216 = false, _mut82217 = false, _mut82218 = false, _mut82219 = false, _mut82220 = false, _mut82221 = false, _mut82222 = false, _mut82223 = false, _mut82224 = false, _mut82225 = false, _mut82226 = false, _mut82227 = false, _mut82228 = false, _mut82229 = false, _mut82230 = false, _mut82231 = false, _mut82232 = false, _mut82233 = false, _mut82234 = false, _mut82235 = false, _mut82236 = false, _mut82237 = false, _mut82238 = false, _mut82239 = false, _mut82240 = false, _mut82241 = false, _mut82242 = false, _mut82243 = false, _mut82244 = false, _mut82245 = false, _mut82246 = false, _mut82247 = false, _mut82248 = false, _mut82249 = false, _mut82250 = false, _mut82251 = false, _mut82252 = false, _mut82253 = false, _mut82254 = false, _mut82255 = false, _mut82256 = false, _mut82257 = false, _mut82258 = false, _mut82259 = false, _mut82260 = false, _mut82261 = false, _mut82262 = false, _mut82263 = false, _mut82264 = false, _mut82265 = false, _mut82266 = false, _mut82267 = false, _mut82268 = false, _mut82269 = false, _mut82270 = false, _mut82271 = false, _mut82272 = false, _mut82273 = false, _mut82274 = false, _mut82275 = false, _mut82276 = false, _mut82277 = false, _mut82278 = false, _mut82279 = false, _mut82280 = false, _mut82281 = false, _mut82282 = false, _mut82283 = false, _mut82284 = false, _mut82285 = false, _mut82286 = false, _mut82287 = false, _mut82288 = false, _mut82289 = false, _mut82290 = false, _mut82291 = false, _mut82292 = false, _mut82293 = false, _mut82294 = false, _mut82295 = false, _mut82296 = false, _mut82297 = false, _mut82298 = false, _mut82299 = false, _mut82300 = false, _mut82301 = false, _mut82302 = false, _mut82303 = false, _mut82304 = false, _mut82305 = false, _mut82306 = false, _mut82307 = false, _mut82308 = false, _mut82309 = false, _mut82310 = false, _mut82311 = false, _mut82312 = false, _mut82313 = false, _mut82314 = false, _mut82315 = false, _mut82316 = false, _mut82317 = false, _mut82318 = false, _mut82319 = false, _mut82320 = false, _mut82321 = false, _mut82322 = false, _mut82323 = false, _mut82324 = false, _mut82325 = false, _mut82326 = false, _mut82327 = false, _mut82328 = false, _mut82329 = false, _mut82330 = false, _mut82331 = false, _mut82332 = false, _mut82333 = false, _mut82334 = false, _mut82335 = false, _mut82336 = false, _mut82337 = false, _mut82338 = false, _mut82339 = false, _mut82340 = false, _mut82341 = false, _mut82342 = false, _mut82343 = false, _mut82344 = false, _mut82345 = false, _mut82346 = false, _mut82347 = false, _mut82348 = false, _mut82349 = false, _mut82350 = false, _mut82351 = false, _mut82352 = false, _mut82353 = false, _mut82354 = false, _mut82355 = false, _mut82356 = false, _mut82357 = false, _mut82358 = false, _mut82359 = false, _mut82360 = false, _mut82361 = false, _mut82362 = false, _mut82363 = false, _mut82364 = false, _mut82365 = false, _mut82366 = false, _mut82367 = false, _mut82368 = false, _mut82369 = false, _mut82370 = false, _mut82371 = false, _mut82372 = false, _mut82373 = false, _mut82374 = false, _mut82375 = false, _mut82376 = false, _mut82377 = false, _mut82378 = false, _mut82379 = false, _mut82380 = false, _mut82381 = false, _mut82382 = false, _mut82383 = false, _mut82384 = false, _mut82385 = false, _mut82386 = false, _mut82387 = false, _mut82388 = false, _mut82389 = false, _mut82390 = false, _mut82391 = false, _mut82392 = false, _mut82393 = false, _mut82394 = false, _mut82395 = false, _mut82396 = false, _mut82397 = false, _mut82398 = false, _mut82399 = false, _mut82400 = false, _mut82401 = false, _mut82402 = false, _mut82403 = false, _mut82404 = false, _mut82405 = false, _mut82406 = false, _mut82407 = false, _mut82408 = false, _mut82409 = false, _mut82410 = false, _mut82411 = false, _mut82412 = false, _mut82413 = false, _mut82414 = false, _mut82415 = false, _mut82416 = false, _mut82417 = false, _mut82418 = false, _mut82419 = false, _mut82420 = false, _mut82421 = false, _mut82422 = false, _mut82423 = false, _mut82424 = false, _mut82425 = false, _mut82426 = false, _mut82427 = false, _mut82428 = false, _mut82429 = false, _mut82430 = false, _mut82431 = false, _mut82432 = false, _mut82433 = false, _mut82434 = false, _mut82435 = false, _mut82436 = false, _mut82437 = false, _mut82438 = false, _mut82439 = false, _mut82440 = false, _mut82441 = false, _mut82442 = false, _mut82443 = false, _mut82444 = false, _mut82445 = false, _mut82446 = false, _mut82447 = false, _mut82448 = false, _mut82449 = false, _mut82450 = false, _mut82451 = false, _mut82452 = false, _mut82453 = false, _mut82454 = false, _mut82455 = false, _mut82456 = false, _mut82457 = false, _mut82458 = false, _mut82459 = false, _mut82460 = false, _mut82461 = false, _mut82462 = false, _mut82463 = false, _mut82464 = false, _mut82465 = false, _mut82466 = false, _mut82467 = false, _mut82468 = false, _mut82469 = false, _mut82470 = false, _mut82471 = false, _mut82472 = false, _mut82473 = false, _mut82474 = false, _mut82475 = false, _mut82476 = false, _mut82477 = false, _mut82478 = false, _mut82479 = false, _mut82480 = false, _mut82481 = false, _mut82482 = false, _mut82483 = false, _mut82484 = false, _mut82485 = false, _mut82486 = false, _mut82487 = false, _mut82488 = false, _mut82489 = false, _mut82490 = false, _mut82491 = false, _mut82492 = false, _mut82493 = false, _mut82494 = false, _mut82495 = false, _mut82496 = false, _mut82497 = false, _mut82498 = false, _mut82499 = false, _mut82500 = false, _mut82501 = false, _mut82502 = false, _mut82503 = false, _mut82504 = false, _mut82505 = false, _mut82506 = false, _mut82507 = false, _mut82508 = false, _mut82509 = false, _mut82510 = false, _mut82511 = false, _mut82512 = false, _mut82513 = false, _mut82514 = false, _mut82515 = false, _mut82516 = false, _mut82517 = false, _mut82518 = false, _mut82519 = false, _mut82520 = false, _mut82521 = false, _mut82522 = false, _mut82523 = false, _mut82524 = false, _mut82525 = false, _mut82526 = false, _mut82527 = false, _mut82528 = false, _mut82529 = false, _mut82530 = false, _mut82531 = false, _mut82532 = false, _mut82533 = false, _mut82534 = false, _mut82535 = false, _mut82536 = false, _mut82537 = false, _mut82538 = false, _mut82539 = false, _mut82540 = false, _mut82541 = false, _mut82542 = false, _mut82543 = false, _mut82544 = false, _mut82545 = false, _mut82546 = false, _mut82547 = false, _mut82548 = false, _mut82549 = false, _mut82550 = false, _mut82551 = false, _mut82552 = false, _mut82553 = false, _mut82554 = false, _mut82555 = false, _mut82556 = false, _mut82557 = false, _mut82558 = false, _mut82559 = false, _mut82560 = false, _mut82561 = false, _mut82562 = false, _mut82563 = false, _mut82564 = false, _mut82565 = false, _mut82566 = false, _mut82567 = false, _mut82568 = false, _mut82569 = false, _mut82570 = false, _mut82571 = false, _mut82572 = false, _mut82573 = false, _mut82574 = false, _mut82575 = false, _mut82576 = false, _mut82577 = false, _mut82578 = false, _mut82579 = false, _mut82580 = false, _mut82581 = false, _mut82582 = false, _mut82583 = false, _mut82584 = false, _mut82585 = false, _mut82586 = false, _mut82587 = false, _mut82588 = false, _mut82589 = false, _mut82590 = false, _mut82591 = false, _mut82592 = false, _mut82593 = false, _mut82594 = false, _mut82595 = false, _mut82596 = false, _mut82597 = false, _mut82598 = false, _mut82599 = false, _mut82600 = false, _mut82601 = false, _mut82602 = false, _mut82603 = false, _mut82604 = false, _mut82605 = false, _mut82606 = false, _mut82607 = false, _mut82608 = false, _mut82609 = false, _mut82610 = false, _mut82611 = false, _mut82612 = false, _mut82613 = false, _mut82614 = false, _mut82615 = false, _mut82616 = false, _mut82617 = false, _mut82618 = false, _mut82619 = false, _mut82620 = false, _mut82621 = false, _mut82622 = false, _mut82623 = false, _mut82624 = false, _mut82625 = false, _mut82626 = false, _mut82627 = false, _mut82628 = false, _mut82629 = false, _mut82630 = false, _mut82631 = false, _mut82632 = false, _mut82633 = false, _mut82634 = false, _mut82635 = false, _mut82636 = false, _mut82637 = false, _mut82638 = false, _mut82639 = false, _mut82640 = false, _mut82641 = false, _mut82642 = false, _mut82643 = false, _mut82644 = false, _mut82645 = false, _mut82646 = false, _mut82647 = false, _mut82648 = false, _mut82649 = false, _mut82650 = false, _mut82651 = false, _mut82652 = false, _mut82653 = false, _mut82654 = false, _mut82655 = false, _mut82656 = false, _mut82657 = false, _mut82658 = false, _mut82659 = false, _mut82660 = false, _mut82661 = false, _mut82662 = false, _mut82663 = false, _mut82664 = false, _mut82665 = false, _mut82666 = false, _mut82667 = false, _mut82668 = false, _mut82669 = false, _mut82670 = false, _mut82671 = false, _mut82672 = false, _mut82673 = false, _mut82674 = false, _mut82675 = false, _mut82676 = false, _mut82677 = false, _mut82678 = false, _mut82679 = false, _mut82680 = false, _mut82681 = false, _mut82682 = false, _mut82683 = false, _mut82684 = false, _mut82685 = false, _mut82686 = false, _mut82687 = false, _mut82688 = false, _mut82689 = false, _mut82690 = false, _mut82691 = false, _mut82692 = false, _mut82693 = false, _mut82694 = false, _mut82695 = false, _mut82696 = false, _mut82697 = false, _mut82698 = false, _mut82699 = false, _mut82700 = false, _mut82701 = false, _mut82702 = false, _mut82703 = false, _mut82704 = false, _mut82705 = false, _mut82706 = false, _mut82707 = false, _mut82708 = false, _mut82709 = false, _mut82710 = false, _mut82711 = false, _mut82712 = false, _mut82713 = false, _mut82714 = false, _mut82715 = false, _mut82716 = false, _mut82717 = false, _mut82718 = false, _mut82719 = false, _mut82720 = false, _mut82721 = false, _mut82722 = false, _mut82723 = false, _mut82724 = false, _mut82725 = false, _mut82726 = false, _mut82727 = false, _mut82728 = false, _mut82729 = false, _mut82730 = false, _mut82731 = false, _mut82732 = false, _mut82733 = false, _mut82734 = false, _mut82735 = false, _mut82736 = false, _mut82737 = false, _mut82738 = false, _mut82739 = false, _mut82740 = false, _mut82741 = false, _mut82742 = false, _mut82743 = false, _mut82744 = false, _mut82745 = false, _mut82746 = false, _mut82747 = false, _mut82748 = false, _mut82749 = false, _mut82750 = false, _mut82751 = false, _mut82752 = false, _mut82753 = false, _mut82754 = false, _mut82755 = false, _mut82756 = false, _mut82757 = false, _mut82758 = false, _mut82759 = false, _mut82760 = false, _mut82761 = false, _mut82762 = false, _mut82763 = false, _mut82764 = false, _mut82765 = false, _mut82766 = false, _mut82767 = false, _mut82768 = false, _mut82769 = false, _mut82770 = false, _mut82771 = false, _mut82772 = false, _mut82773 = false, _mut82774 = false, _mut82775 = false, _mut82776 = false, _mut82777 = false, _mut82778 = false, _mut82779 = false, _mut82780 = false, _mut82781 = false, _mut82782 = false, _mut82783 = false, _mut82784 = false, _mut82785 = false, _mut82786 = false, _mut82787 = false, _mut82788 = false, _mut82789 = false, _mut82790 = false, _mut82791 = false, _mut82792 = false, _mut82793 = false, _mut82794 = false, _mut82795 = false, _mut82796 = false, _mut82797 = false, _mut82798 = false, _mut82799 = false, _mut82800 = false, _mut82801 = false, _mut82802 = false, _mut82803 = false, _mut82804 = false, _mut82805 = false, _mut82806 = false, _mut82807 = false, _mut82808 = false, _mut82809 = false, _mut82810 = false, _mut82811 = false, _mut82812 = false, _mut82813 = false, _mut82814 = false, _mut82815 = false, _mut82816 = false, _mut82817 = false, _mut82818 = false, _mut82819 = false, _mut82820 = false, _mut82821 = false, _mut82822 = false, _mut82823 = false, _mut82824 = false, _mut82825 = false, _mut82826 = false, _mut82827 = false, _mut82828 = false, _mut82829 = false, _mut82830 = false, _mut82831 = false, _mut82832 = false, _mut82833 = false, _mut82834 = false, _mut82835 = false, _mut82836 = false, _mut82837 = false, _mut82838 = false, _mut82839 = false, _mut82840 = false, _mut82841 = false, _mut82842 = false, _mut82843 = false, _mut82844 = false, _mut82845 = false, _mut82846 = false, _mut82847 = false, _mut82848 = false, _mut82849 = false, _mut82850 = false, _mut82851 = false, _mut82852 = false, _mut82853 = false, _mut82854 = false, _mut82855 = false, _mut82856 = false, _mut82857 = false, _mut82858 = false, _mut82859 = false, _mut82860 = false, _mut82861 = false, _mut82862 = false, _mut82863 = false, _mut82864 = false, _mut82865 = false, _mut82866 = false, _mut82867 = false, _mut82868 = false, _mut82869 = false, _mut82870 = false, _mut82871 = false, _mut82872 = false, _mut82873 = false, _mut82874 = false, _mut82875 = false, _mut82876 = false, _mut82877 = false, _mut82878 = false, _mut82879 = false, _mut82880 = false, _mut82881 = false, _mut82882 = false, _mut82883 = false, _mut82884 = false, _mut82885 = false, _mut82886 = false, _mut82887 = false, _mut82888 = false, _mut82889 = false, _mut82890 = false, _mut82891 = false, _mut82892 = false, _mut82893 = false, _mut82894 = false, _mut82895 = false, _mut82896 = false, _mut82897 = false, _mut82898 = false, _mut82899 = false, _mut82900 = false, _mut82901 = false, _mut82902 = false, _mut82903 = false, _mut82904 = false, _mut82905 = false, _mut82906 = false, _mut82907 = false, _mut82908 = false, _mut82909 = false, _mut82910 = false, _mut82911 = false, _mut82912 = false, _mut82913 = false, _mut82914 = false, _mut82915 = false, _mut82916 = false, _mut82917 = false, _mut82918 = false, _mut82919 = false, _mut82920 = false, _mut82921 = false, _mut82922 = false, _mut82923 = false, _mut82924 = false, _mut82925 = false, _mut82926 = false, _mut82927 = false, _mut82928 = false, _mut82929 = false, _mut82930 = false, _mut82931 = false, _mut82932 = false, _mut82933 = false, _mut82934 = false, _mut82935 = false, _mut82936 = false, _mut82937 = false, _mut82938 = false, _mut82939 = false, _mut82940 = false, _mut82941 = false, _mut82942 = false, _mut82943 = false, _mut82944 = false, _mut82945 = false, _mut82946 = false, _mut82947 = false, _mut82948 = false, _mut82949 = false, _mut82950 = false, _mut82951 = false, _mut82952 = false, _mut82953 = false, _mut82954 = false, _mut82955 = false, _mut82956 = false, _mut82957 = false, _mut82958 = false, _mut82959 = false, _mut82960 = false, _mut82961 = false, _mut82962 = false, _mut82963 = false, _mut82964 = false, _mut82965 = false, _mut82966 = false, _mut82967 = false, _mut82968 = false, _mut82969 = false, _mut82970 = false, _mut82971 = false, _mut82972 = false, _mut82973 = false, _mut82974 = false, _mut82975 = false, _mut82976 = false, _mut82977 = false, _mut82978 = false, _mut82979 = false, _mut82980 = false, _mut82981 = false, _mut82982 = false, _mut82983 = false, _mut82984 = false, _mut82985 = false, _mut82986 = false, _mut82987 = false, _mut82988 = false, _mut82989 = false, _mut82990 = false, _mut82991 = false, _mut82992 = false, _mut82993 = false, _mut82994 = false, _mut82995 = false, _mut82996 = false, _mut82997 = false, _mut82998 = false, _mut82999 = false, _mut83000 = false, _mut83001 = false, _mut83002 = false, _mut83003 = false, _mut83004 = false, _mut83005 = false, _mut83006 = false, _mut83007 = false, _mut83008 = false, _mut83009 = false, _mut83010 = false, _mut83011 = false, _mut83012 = false, _mut83013 = false, _mut83014 = false, _mut83015 = false, _mut83016 = false, _mut83017 = false, _mut83018 = false, _mut83019 = false, _mut83020 = false, _mut83021 = false, _mut83022 = false, _mut83023 = false, _mut83024 = false, _mut83025 = false, _mut83026 = false, _mut83027 = false, _mut83028 = false, _mut83029 = false, _mut83030 = false, _mut83031 = false, _mut83032 = false, _mut83033 = false, _mut83034 = false, _mut83035 = false, _mut83036 = false, _mut83037 = false, _mut83038 = false, _mut83039 = false, _mut83040 = false, _mut83041 = false, _mut83042 = false, _mut83043 = false, _mut83044 = false, _mut83045 = false, _mut83046 = false, _mut83047 = false, _mut83048 = false, _mut83049 = false, _mut83050 = false, _mut83051 = false, _mut83052 = false, _mut83053 = false, _mut83054 = false, _mut83055 = false, _mut83056 = false, _mut83057 = false, _mut83058 = false, _mut83059 = false, _mut83060 = false, _mut83061 = false, _mut83062 = false, _mut83063 = false, _mut83064 = false, _mut83065 = false, _mut83066 = false, _mut83067 = false, _mut83068 = false, _mut83069 = false, _mut83070 = false, _mut83071 = false, _mut83072 = false, _mut83073 = false, _mut83074 = false, _mut83075 = false, _mut83076 = false, _mut83077 = false, _mut83078 = false, _mut83079 = false, _mut83080 = false, _mut83081 = false, _mut83082 = false, _mut83083 = false, _mut83084 = false, _mut83085 = false, _mut83086 = false, _mut83087 = false, _mut83088 = false, _mut83089 = false, _mut83090 = false, _mut83091 = false, _mut83092 = false, _mut83093 = false, _mut83094 = false, _mut83095 = false, _mut83096 = false, _mut83097 = false, _mut83098 = false, _mut83099 = false, _mut83100 = false, _mut83101 = false, _mut83102 = false, _mut83103 = false, _mut83104 = false, _mut83105 = false, _mut83106 = false, _mut83107 = false, _mut83108 = false, _mut83109 = false, _mut83110 = false, _mut83111 = false, _mut83112 = false, _mut83113 = false, _mut83114 = false, _mut83115 = false, _mut83116 = false, _mut83117 = false, _mut83118 = false, _mut83119 = false, _mut83120 = false, _mut83121 = false, _mut83122 = false, _mut83123 = false, _mut83124 = false, _mut83125 = false, _mut83126 = false, _mut83127 = false, _mut83128 = false, _mut83129 = false, _mut83130 = false, _mut83131 = false, _mut83132 = false, _mut83133 = false, _mut83134 = false, _mut83135 = false, _mut83136 = false, _mut83137 = false, _mut83138 = false, _mut83139 = false, _mut83140 = false, _mut83141 = false, _mut83142 = false, _mut83143 = false, _mut83144 = false, _mut83145 = false, _mut83146 = false, _mut83147 = false, _mut83148 = false, _mut83149 = false, _mut83150 = false, _mut83151 = false, _mut83152 = false, _mut83153 = false, _mut83154 = false, _mut83155 = false, _mut83156 = false, _mut83157 = false, _mut83158 = false, _mut83159 = false, _mut83160 = false, _mut83161 = false, _mut83162 = false, _mut83163 = false, _mut83164 = false, _mut83165 = false, _mut83166 = false, _mut83167 = false, _mut83168 = false, _mut83169 = false, _mut83170 = false, _mut83171 = false, _mut83172 = false, _mut83173 = false, _mut83174 = false, _mut83175 = false, _mut83176 = false, _mut83177 = false, _mut83178 = false, _mut83179 = false, _mut83180 = false, _mut83181 = false, _mut83182 = false, _mut83183 = false, _mut83184 = false, _mut83185 = false, _mut83186 = false, _mut83187 = false, _mut83188 = false, _mut83189 = false, _mut83190 = false, _mut83191 = false, _mut83192 = false, _mut83193 = false, _mut83194 = false, _mut83195 = false, _mut83196 = false, _mut83197 = false, _mut83198 = false, _mut83199 = false, _mut83200 = false, _mut83201 = false, _mut83202 = false, _mut83203 = false, _mut83204 = false, _mut83205 = false, _mut83206 = false, _mut83207 = false, _mut83208 = false, _mut83209 = false, _mut83210 = false, _mut83211 = false, _mut83212 = false, _mut83213 = false, _mut83214 = false, _mut83215 = false, _mut83216 = false, _mut83217 = false, _mut83218 = false, _mut83219 = false, _mut83220 = false, _mut83221 = false, _mut83222 = false, _mut83223 = false, _mut83224 = false, _mut83225 = false, _mut83226 = false, _mut83227 = false, _mut83228 = false, _mut83229 = false, _mut83230 = false, _mut83231 = false, _mut83232 = false, _mut83233 = false, _mut83234 = false, _mut83235 = false, _mut83236 = false, _mut83237 = false, _mut83238 = false, _mut83239 = false, _mut83240 = false, _mut83241 = false, _mut83242 = false, _mut83243 = false, _mut83244 = false, _mut83245 = false, _mut83246 = false, _mut83247 = false, _mut83248 = false, _mut83249 = false, _mut83250 = false, _mut83251 = false, _mut83252 = false, _mut83253 = false, _mut83254 = false, _mut83255 = false, _mut83256 = false, _mut83257 = false, _mut83258 = false, _mut83259 = false, _mut83260 = false, _mut83261 = false, _mut83262 = false, _mut83263 = false, _mut83264 = false, _mut83265 = false, _mut83266 = false, _mut83267 = false, _mut83268 = false, _mut83269 = false, _mut83270 = false, _mut83271 = false, _mut83272 = false, _mut83273 = false, _mut83274 = false, _mut83275 = false, _mut83276 = false, _mut83277 = false, _mut83278 = false, _mut83279 = false, _mut83280 = false, _mut83281 = false, _mut83282 = false, _mut83283 = false, _mut83284 = false, _mut83285 = false, _mut83286 = false, _mut83287 = false, _mut83288 = false, _mut83289 = false, _mut83290 = false, _mut83291 = false, _mut83292 = false, _mut83293 = false, _mut83294 = false, _mut83295 = false, _mut83296 = false, _mut83297 = false, _mut83298 = false, _mut83299 = false, _mut83300 = false, _mut83301 = false, _mut83302 = false, _mut83303 = false, _mut83304 = false, _mut83305 = false, _mut83306 = false, _mut83307 = false, _mut83308 = false, _mut83309 = false, _mut83310 = false, _mut83311 = false, _mut83312 = false, _mut83313 = false, _mut83314 = false, _mut83315 = false, _mut83316 = false, _mut83317 = false, _mut83318 = false, _mut83319 = false, _mut83320 = false, _mut83321 = false, _mut83322 = false, _mut83323 = false, _mut83324 = false, _mut83325 = false, _mut83326 = false, _mut83327 = false, _mut83328 = false, _mut83329 = false, _mut83330 = false, _mut83331 = false, _mut83332 = false, _mut83333 = false, _mut83334 = false, _mut83335 = false, _mut83336 = false, _mut83337 = false, _mut83338 = false, _mut83339 = false, _mut83340 = false, _mut83341 = false, _mut83342 = false, _mut83343 = false, _mut83344 = false, _mut83345 = false, _mut83346 = false, _mut83347 = false, _mut83348 = false, _mut83349 = false, _mut83350 = false, _mut83351 = false, _mut83352 = false, _mut83353 = false, _mut83354 = false, _mut83355 = false, _mut83356 = false, _mut83357 = false, _mut83358 = false, _mut83359 = false, _mut83360 = false, _mut83361 = false, _mut83362 = false, _mut83363 = false, _mut83364 = false, _mut83365 = false, _mut83366 = false, _mut83367 = false, _mut83368 = false, _mut83369 = false, _mut83370 = false, _mut83371 = false, _mut83372 = false, _mut83373 = false, _mut83374 = false, _mut83375 = false, _mut83376 = false, _mut83377 = false, _mut83378 = false, _mut83379 = false, _mut83380 = false, _mut83381 = false, _mut83382 = false, _mut83383 = false, _mut83384 = false, _mut83385 = false, _mut83386 = false, _mut83387 = false, _mut83388 = false, _mut83389 = false, _mut83390 = false, _mut83391 = false, _mut83392 = false, _mut83393 = false, _mut83394 = false, _mut83395 = false, _mut83396 = false, _mut83397 = false, _mut83398 = false, _mut83399 = false, _mut83400 = false, _mut83401 = false, _mut83402 = false, _mut83403 = false, _mut83404 = false, _mut83405 = false, _mut83406 = false, _mut83407 = false, _mut83408 = false, _mut83409 = false, _mut83410 = false, _mut83411 = false, _mut83412 = false, _mut83413 = false, _mut83414 = false, _mut83415 = false, _mut83416 = false, _mut83417 = false, _mut83418 = false, _mut83419 = false, _mut83420 = false, _mut83421 = false, _mut83422 = false, _mut83423 = false, _mut83424 = false, _mut83425 = false, _mut83426 = false, _mut83427 = false, _mut83428 = false, _mut83429 = false, _mut83430 = false, _mut83431 = false, _mut83432 = false, _mut83433 = false, _mut83434 = false, _mut83435 = false, _mut83436 = false, _mut83437 = false, _mut83438 = false, _mut83439 = false, _mut83440 = false, _mut83441 = false, _mut83442 = false, _mut83443 = false, _mut83444 = false, _mut83445 = false, _mut83446 = false, _mut83447 = false, _mut83448 = false, _mut83449 = false, _mut83450 = false, _mut83451 = false, _mut83452 = false, _mut83453 = false, _mut83454 = false, _mut83455 = false, _mut83456 = false, _mut83457 = false, _mut83458 = false, _mut83459 = false, _mut83460 = false, _mut83461 = false, _mut83462 = false, _mut83463 = false, _mut83464 = false, _mut83465 = false, _mut83466 = false, _mut83467 = false, _mut83468 = false, _mut83469 = false, _mut83470 = false, _mut83471 = false, _mut83472 = false, _mut83473 = false, _mut83474 = false, _mut83475 = false, _mut83476 = false, _mut83477 = false, _mut83478 = false, _mut83479 = false, _mut83480 = false, _mut83481 = false, _mut83482 = false, _mut83483 = false, _mut83484 = false, _mut83485 = false, _mut83486 = false, _mut83487 = false, _mut83488 = false, _mut83489 = false, _mut83490 = false, _mut83491 = false, _mut83492 = false;

    /**
     * Identity rotation.
     */
    public static final Rotation IDENTITY = new Rotation(1.0, 0.0, 0.0, 0.0, false);

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -2153622329907944313L;

    /**
     * Scalar coordinate of the quaternion.
     */
    private final double q0;

    /**
     * First coordinate of the vectorial part of the quaternion.
     */
    private final double q1;

    /**
     * Second coordinate of the vectorial part of the quaternion.
     */
    private final double q2;

    /**
     * Third coordinate of the vectorial part of the quaternion.
     */
    private final double q3;

    /**
     * Build a rotation from the quaternion coordinates.
     * <p>A rotation can be built from a <em>normalized</em> quaternion,
     * i.e. a quaternion for which q<sub>0</sub><sup>2</sup> +
     * q<sub>1</sub><sup>2</sup> + q<sub>2</sub><sup>2</sup> +
     * q<sub>3</sub><sup>2</sup> = 1. If the quaternion is not normalized,
     * the constructor can normalize it in a preprocessing step.</p>
     * <p>Note that some conventions put the scalar part of the quaternion
     * as the 4<sup>th</sup> component and the vector part as the first three
     * components. This is <em>not</em> our convention. We put the scalar part
     * as the first component.</p>
     * @param q0 scalar part of the quaternion
     * @param q1 first coordinate of the vectorial part of the quaternion
     * @param q2 second coordinate of the vectorial part of the quaternion
     * @param q3 third coordinate of the vectorial part of the quaternion
     * @param needsNormalization if true, the coordinates are considered
     * not to be normalized, a normalization preprocessing step is performed
     * before using them
     */
    public Rotation(double q0, double q1, double q2, double q3, boolean needsNormalization) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134");
        if (needsNormalization) {
            // normalization preprocessing
            double inv = AOR_divide(1.0, FastMath.sqrt(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(q0, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81227, _mut81228, _mut81229, _mut81230), AOR_multiply(q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81231, _mut81232, _mut81233, _mut81234), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81235, _mut81236, _mut81237, _mut81238), AOR_multiply(q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81239, _mut81240, _mut81241, _mut81242), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81243, _mut81244, _mut81245, _mut81246), AOR_multiply(q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81247, _mut81248, _mut81249, _mut81250), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81251, _mut81252, _mut81253, _mut81254)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_134", _mut81255, _mut81256, _mut81257, _mut81258);
            q0 *= inv;
            q1 *= inv;
            q2 *= inv;
            q3 *= inv;
        }
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    /**
     * Build a rotation from an axis and an angle.
     * <p>
     * Calling this constructor is equivalent to call
     * {@link #Rotation(Vector3D, double, RotationConvention)
     * new Rotation(axis, angle, RotationConvention.VECTOR_OPERATOR)}
     * </p>
     * @param axis axis around which to rotate
     * @param angle rotation angle.
     * @exception MathIllegalArgumentException if the axis norm is zero
     * @deprecated as of 3.6, replaced with {@link #Rotation(Vector3D, double, RotationConvention)}
     */
    @Deprecated
    public Rotation(Vector3D axis, double angle) throws MathIllegalArgumentException {
        this(axis, angle, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Build a rotation from an axis and an angle.
     * @param axis axis around which to rotate
     * @param angle rotation angle
     * @param convention convention to use for the semantics of the angle
     * @exception MathIllegalArgumentException if the axis norm is zero
     * @since 3.6
     */
    public Rotation(final Vector3D axis, final double angle, final RotationConvention convention) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176");
        double norm = axis.getNorm();
        if (ROR_equals(norm, 0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81259, _mut81260, _mut81261, _mut81262, _mut81263)) {
            throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_AXIS);
        }
        double halfAngle = convention == RotationConvention.VECTOR_OPERATOR ? AOR_multiply(-0.5, angle, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81268, _mut81269, _mut81270, _mut81271) : AOR_multiply(+0.5, angle, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81264, _mut81265, _mut81266, _mut81267);
        double coeff = AOR_divide(FastMath.sin(halfAngle), norm, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81272, _mut81273, _mut81274, _mut81275);
        q0 = FastMath.cos(halfAngle);
        q1 = AOR_multiply(coeff, axis.getX(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81276, _mut81277, _mut81278, _mut81279);
        q2 = AOR_multiply(coeff, axis.getY(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81280, _mut81281, _mut81282, _mut81283);
        q3 = AOR_multiply(coeff, axis.getZ(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_176", _mut81284, _mut81285, _mut81286, _mut81287);
    }

    /**
     * Build a rotation from a 3X3 matrix.
     *
     * <p>Rotation matrices are orthogonal matrices, i.e. unit matrices
     * (which are matrices for which m.m<sup>T</sup> = I) with real
     * coefficients. The module of the determinant of unit matrices is
     * 1, among the orthogonal 3X3 matrices, only the ones having a
     * positive determinant (+1) are rotation matrices.</p>
     *
     * <p>When a rotation is defined by a matrix with truncated values
     * (typically when it is extracted from a technical sheet where only
     * four to five significant digits are available), the matrix is not
     * orthogonal anymore. This constructor handles this case
     * transparently by using a copy of the given matrix and applying a
     * correction to the copy in order to perfect its orthogonality. If
     * the Frobenius norm of the correction needed is above the given
     * threshold, then the matrix is considered to be too far from a
     * true rotation matrix and an exception is thrown.<p>
     *
     * @param m rotation matrix
     * @param threshold convergence threshold for the iterative
     * orthogonality correction (convergence is reached when the
     * difference between two steps of the Frobenius norm of the
     * correction is below this threshold)
     *
     * @exception NotARotationMatrixException if the matrix is not a 3X3
     * matrix, or if it cannot be transformed into an orthogonal matrix
     * with the given threshold, or if the determinant of the resulting
     * orthogonal matrix is negative
     */
    public Rotation(double[][] m, double threshold) throws NotARotationMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224");
        // dimension check
        if ((_mut81310 ? ((_mut81304 ? ((_mut81298 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297)))) && (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81299, _mut81300, _mut81301, _mut81302, _mut81303))) : ((_mut81298 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297)))) || (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81299, _mut81300, _mut81301, _mut81302, _mut81303)))) && (ROR_not_equals(m[2].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81305, _mut81306, _mut81307, _mut81308, _mut81309))) : ((_mut81304 ? ((_mut81298 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297)))) && (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81299, _mut81300, _mut81301, _mut81302, _mut81303))) : ((_mut81298 ? ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) && (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297))) : ((ROR_not_equals(m.length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81288, _mut81289, _mut81290, _mut81291, _mut81292)) || (ROR_not_equals(m[0].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81293, _mut81294, _mut81295, _mut81296, _mut81297)))) || (ROR_not_equals(m[1].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81299, _mut81300, _mut81301, _mut81302, _mut81303)))) || (ROR_not_equals(m[2].length, 3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81305, _mut81306, _mut81307, _mut81308, _mut81309))))) {
            throw new NotARotationMatrixException(LocalizedFormats.ROTATION_MATRIX_DIMENSIONS, m.length, m[0].length);
        }
        // compute a "close" orthogonal matrix
        double[][] ort = orthogonalizeMatrix(m, threshold);
        // check the sign of the determinant
        double det = AOR_plus(AOR_minus(AOR_multiply(ort[0][0], (AOR_minus(AOR_multiply(ort[1][1], ort[2][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81311, _mut81312, _mut81313, _mut81314), AOR_multiply(ort[2][1], ort[1][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81315, _mut81316, _mut81317, _mut81318), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81319, _mut81320, _mut81321, _mut81322)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81323, _mut81324, _mut81325, _mut81326), AOR_multiply(ort[1][0], (AOR_minus(AOR_multiply(ort[0][1], ort[2][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81327, _mut81328, _mut81329, _mut81330), AOR_multiply(ort[2][1], ort[0][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81331, _mut81332, _mut81333, _mut81334), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81335, _mut81336, _mut81337, _mut81338)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81339, _mut81340, _mut81341, _mut81342), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81343, _mut81344, _mut81345, _mut81346), AOR_multiply(ort[2][0], (AOR_minus(AOR_multiply(ort[0][1], ort[1][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81347, _mut81348, _mut81349, _mut81350), AOR_multiply(ort[1][1], ort[0][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81351, _mut81352, _mut81353, _mut81354), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81355, _mut81356, _mut81357, _mut81358)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81359, _mut81360, _mut81361, _mut81362), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81363, _mut81364, _mut81365, _mut81366);
        if (ROR_less(det, 0.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_224", _mut81367, _mut81368, _mut81369, _mut81370, _mut81371)) {
            throw new NotARotationMatrixException(LocalizedFormats.CLOSEST_ORTHOGONAL_MATRIX_HAS_NEGATIVE_DETERMINANT, det);
        }
        double[] quat = mat2quat(ort);
        q0 = quat[0];
        q1 = quat[1];
        q2 = quat[2];
        q3 = quat[3];
    }

    /**
     * Build the rotation that transforms a pair of vectors into another pair.
     *
     * <p>Except for possible scale factors, if the instance were applied to
     * the pair (u<sub>1</sub>, u<sub>2</sub>) it will produce the pair
     * (v<sub>1</sub>, v<sub>2</sub>).</p>
     *
     * <p>If the angular separation between u<sub>1</sub> and u<sub>2</sub> is
     * not the same as the angular separation between v<sub>1</sub> and
     * v<sub>2</sub>, then a corrected v'<sub>2</sub> will be used rather than
     * v<sub>2</sub>, the corrected vector will be in the (&pm;v<sub>1</sub>,
     * +v<sub>2</sub>) half-plane.</p>
     *
     * @param u1 first vector of the origin pair
     * @param u2 second vector of the origin pair
     * @param v1 desired image of u1 by the rotation
     * @param v2 desired image of u2 by the rotation
     * @exception MathArithmeticException if the norm of one of the vectors is zero,
     * or if one of the pair is degenerated (i.e. the vectors of the pair are collinear)
     */
    public Rotation(Vector3D u1, Vector3D u2, Vector3D v1, Vector3D v2) throws MathArithmeticException {
        // this fails when vectors are null or collinear, which is forbidden to define a rotation
        final Vector3D u3 = u1.crossProduct(u2).normalize();
        u2 = u3.crossProduct(u1).normalize();
        u1 = u1.normalize();
        // this fails when vectors are null or collinear, which is forbidden to define a rotation
        final Vector3D v3 = v1.crossProduct(v2).normalize();
        v2 = v3.crossProduct(v1).normalize();
        v1 = v1.normalize();
        // buid a matrix transforming the first base into the second one
        final double[][] m = new double[][] { { MathArrays.linearCombination(u1.getX(), v1.getX(), u2.getX(), v2.getX(), u3.getX(), v3.getX()), MathArrays.linearCombination(u1.getY(), v1.getX(), u2.getY(), v2.getX(), u3.getY(), v3.getX()), MathArrays.linearCombination(u1.getZ(), v1.getX(), u2.getZ(), v2.getX(), u3.getZ(), v3.getX()) }, { MathArrays.linearCombination(u1.getX(), v1.getY(), u2.getX(), v2.getY(), u3.getX(), v3.getY()), MathArrays.linearCombination(u1.getY(), v1.getY(), u2.getY(), v2.getY(), u3.getY(), v3.getY()), MathArrays.linearCombination(u1.getZ(), v1.getY(), u2.getZ(), v2.getY(), u3.getZ(), v3.getY()) }, { MathArrays.linearCombination(u1.getX(), v1.getZ(), u2.getX(), v2.getZ(), u3.getX(), v3.getZ()), MathArrays.linearCombination(u1.getY(), v1.getZ(), u2.getY(), v2.getZ(), u3.getY(), v3.getZ()), MathArrays.linearCombination(u1.getZ(), v1.getZ(), u2.getZ(), v2.getZ(), u3.getZ(), v3.getZ()) } };
        double[] quat = mat2quat(m);
        q0 = quat[0];
        q1 = quat[1];
        q2 = quat[2];
        q3 = quat[3];
    }

    /**
     * Build one of the rotations that transform one vector into another one.
     *
     * <p>Except for a possible scale factor, if the instance were
     * applied to the vector u it will produce the vector v. There is an
     * infinite number of such rotations, this constructor choose the
     * one with the smallest associated angle (i.e. the one whose axis
     * is orthogonal to the (u, v) plane). If u and v are collinear, an
     * arbitrary rotation axis is chosen.</p>
     *
     * @param u origin vector
     * @param v desired image of u by the rotation
     * @exception MathArithmeticException if the norm of one of the vectors is zero
     */
    public Rotation(Vector3D u, Vector3D v) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330");
        double normProduct = AOR_multiply(u.getNorm(), v.getNorm(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81372, _mut81373, _mut81374, _mut81375);
        if (ROR_equals(normProduct, 0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81376, _mut81377, _mut81378, _mut81379, _mut81380)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM_FOR_ROTATION_DEFINING_VECTOR);
        }
        double dot = u.dotProduct(v);
        if (ROR_less(dot, (AOR_multiply((AOR_minus(2.0e-15, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81381, _mut81382, _mut81383, _mut81384)), normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81385, _mut81386, _mut81387, _mut81388)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81389, _mut81390, _mut81391, _mut81392, _mut81393)) {
            // an arbitrary vector orthogonal to u
            Vector3D w = u.orthogonal();
            q0 = 0.0;
            q1 = -w.getX();
            q2 = -w.getY();
            q3 = -w.getZ();
        } else {
            // the shortest possible rotation: axis orthogonal to this plane
            q0 = FastMath.sqrt(AOR_multiply(0.5, (AOR_plus(1.0, AOR_divide(dot, normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81394, _mut81395, _mut81396, _mut81397), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81398, _mut81399, _mut81400, _mut81401)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81402, _mut81403, _mut81404, _mut81405));
            double coeff = AOR_divide(1.0, (AOR_multiply(AOR_multiply(2.0, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81406, _mut81407, _mut81408, _mut81409), normProduct, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81410, _mut81411, _mut81412, _mut81413)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81414, _mut81415, _mut81416, _mut81417);
            Vector3D q = v.crossProduct(u);
            q1 = AOR_multiply(coeff, q.getX(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81418, _mut81419, _mut81420, _mut81421);
            q2 = AOR_multiply(coeff, q.getY(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81422, _mut81423, _mut81424, _mut81425);
            q3 = AOR_multiply(coeff, q.getZ(), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.Rotation_330", _mut81426, _mut81427, _mut81428, _mut81429);
        }
    }

    /**
     * Build a rotation from three Cardan or Euler elementary rotations.
     *
     * <p>
     * Calling this constructor is equivalent to call
     * {@link #Rotation(RotationOrder, RotationConvention, double, double, double)
     * new Rotation(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3)}
     * </p>
     *
     * @param order order of rotations to use
     * @param alpha1 angle of the first elementary rotation
     * @param alpha2 angle of the second elementary rotation
     * @param alpha3 angle of the third elementary rotation
     * @deprecated as of 3.6, replaced with {@link
     * #Rotation(RotationOrder, RotationConvention, double, double, double)}
     */
    @Deprecated
    public Rotation(RotationOrder order, double alpha1, double alpha2, double alpha3) {
        this(order, RotationConvention.VECTOR_OPERATOR, alpha1, alpha2, alpha3);
    }

    /**
     * Build a rotation from three Cardan or Euler elementary rotations.
     *
     * <p>Cardan rotations are three successive rotations around the
     * canonical axes X, Y and Z, each axis being used once. There are
     * 6 such sets of rotations (XYZ, XZY, YXZ, YZX, ZXY and ZYX). Euler
     * rotations are three successive rotations around the canonical
     * axes X, Y and Z, the first and last rotations being around the
     * same axis. There are 6 such sets of rotations (XYX, XZX, YXY,
     * YZY, ZXZ and ZYZ), the most popular one being ZXZ.</p>
     * <p>Beware that many people routinely use the term Euler angles even
     * for what really are Cardan angles (this confusion is especially
     * widespread in the aerospace business where Roll, Pitch and Yaw angles
     * are often wrongly tagged as Euler angles).</p>
     *
     * @param order order of rotations to compose, from left to right
     * (i.e. we will use {@code r1.compose(r2.compose(r3, convention), convention)})
     * @param convention convention to use for the semantics of the angle
     * @param alpha1 angle of the first elementary rotation
     * @param alpha2 angle of the second elementary rotation
     * @param alpha3 angle of the third elementary rotation
     * @since 3.6
     */
    public Rotation(RotationOrder order, RotationConvention convention, double alpha1, double alpha2, double alpha3) {
        Rotation r1 = new Rotation(order.getA1(), alpha1, convention);
        Rotation r2 = new Rotation(order.getA2(), alpha2, convention);
        Rotation r3 = new Rotation(order.getA3(), alpha3, convention);
        Rotation composed = r1.compose(r2.compose(r3, convention), convention);
        q0 = composed.q0;
        q1 = composed.q1;
        q2 = composed.q2;
        q3 = composed.q3;
    }

    /**
     * Convert an orthogonal rotation matrix to a quaternion.
     * @param ort orthogonal rotation matrix
     * @return quaternion corresponding to the matrix
     */
    private static double[] mat2quat(final double[][] ort) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419");
        final double[] quat = new double[4];
        // test since qi = 0.45 implies 4 qi^2 - 1 = -0.19)
        double s = AOR_plus(AOR_plus(ort[0][0], ort[1][1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81430, _mut81431, _mut81432, _mut81433), ort[2][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81434, _mut81435, _mut81436, _mut81437);
        if (ROR_greater(s, -0.19, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81438, _mut81439, _mut81440, _mut81441, _mut81442)) {
            // compute q0 and deduce q1, q2 and q3
            quat[0] = AOR_multiply(0.5, FastMath.sqrt(AOR_plus(s, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81585, _mut81586, _mut81587, _mut81588)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81589, _mut81590, _mut81591, _mut81592);
            double inv = AOR_divide(0.25, quat[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81593, _mut81594, _mut81595, _mut81596);
            quat[1] = AOR_multiply(inv, (AOR_minus(ort[1][2], ort[2][1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81597, _mut81598, _mut81599, _mut81600)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81601, _mut81602, _mut81603, _mut81604);
            quat[2] = AOR_multiply(inv, (AOR_minus(ort[2][0], ort[0][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81605, _mut81606, _mut81607, _mut81608)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81609, _mut81610, _mut81611, _mut81612);
            quat[3] = AOR_multiply(inv, (AOR_minus(ort[0][1], ort[1][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81613, _mut81614, _mut81615, _mut81616)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81617, _mut81618, _mut81619, _mut81620);
        } else {
            s = AOR_minus(AOR_minus(ort[0][0], ort[1][1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81443, _mut81444, _mut81445, _mut81446), ort[2][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81447, _mut81448, _mut81449, _mut81450);
            if (ROR_greater(s, -0.19, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81451, _mut81452, _mut81453, _mut81454, _mut81455)) {
                // compute q1 and deduce q0, q2 and q3
                quat[1] = AOR_multiply(0.5, FastMath.sqrt(AOR_plus(s, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81549, _mut81550, _mut81551, _mut81552)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81553, _mut81554, _mut81555, _mut81556);
                double inv = AOR_divide(0.25, quat[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81557, _mut81558, _mut81559, _mut81560);
                quat[0] = AOR_multiply(inv, (AOR_minus(ort[1][2], ort[2][1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81561, _mut81562, _mut81563, _mut81564)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81565, _mut81566, _mut81567, _mut81568);
                quat[2] = AOR_multiply(inv, (AOR_plus(ort[0][1], ort[1][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81569, _mut81570, _mut81571, _mut81572)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81573, _mut81574, _mut81575, _mut81576);
                quat[3] = AOR_multiply(inv, (AOR_plus(ort[0][2], ort[2][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81577, _mut81578, _mut81579, _mut81580)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81581, _mut81582, _mut81583, _mut81584);
            } else {
                s = AOR_minus(AOR_minus(ort[1][1], ort[0][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81456, _mut81457, _mut81458, _mut81459), ort[2][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81460, _mut81461, _mut81462, _mut81463);
                if (ROR_greater(s, -0.19, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81464, _mut81465, _mut81466, _mut81467, _mut81468)) {
                    // compute q2 and deduce q0, q1 and q3
                    quat[2] = AOR_multiply(0.5, FastMath.sqrt(AOR_plus(s, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81513, _mut81514, _mut81515, _mut81516)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81517, _mut81518, _mut81519, _mut81520);
                    double inv = AOR_divide(0.25, quat[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81521, _mut81522, _mut81523, _mut81524);
                    quat[0] = AOR_multiply(inv, (AOR_minus(ort[2][0], ort[0][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81525, _mut81526, _mut81527, _mut81528)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81529, _mut81530, _mut81531, _mut81532);
                    quat[1] = AOR_multiply(inv, (AOR_plus(ort[0][1], ort[1][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81533, _mut81534, _mut81535, _mut81536)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81537, _mut81538, _mut81539, _mut81540);
                    quat[3] = AOR_multiply(inv, (AOR_plus(ort[2][1], ort[1][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81541, _mut81542, _mut81543, _mut81544)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81545, _mut81546, _mut81547, _mut81548);
                } else {
                    // compute q3 and deduce q0, q1 and q2
                    s = AOR_minus(AOR_minus(ort[2][2], ort[0][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81469, _mut81470, _mut81471, _mut81472), ort[1][1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81473, _mut81474, _mut81475, _mut81476);
                    quat[3] = AOR_multiply(0.5, FastMath.sqrt(AOR_plus(s, 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81477, _mut81478, _mut81479, _mut81480)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81481, _mut81482, _mut81483, _mut81484);
                    double inv = AOR_divide(0.25, quat[3], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81485, _mut81486, _mut81487, _mut81488);
                    quat[0] = AOR_multiply(inv, (AOR_minus(ort[0][1], ort[1][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81489, _mut81490, _mut81491, _mut81492)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81493, _mut81494, _mut81495, _mut81496);
                    quat[1] = AOR_multiply(inv, (AOR_plus(ort[0][2], ort[2][0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81497, _mut81498, _mut81499, _mut81500)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81501, _mut81502, _mut81503, _mut81504);
                    quat[2] = AOR_multiply(inv, (AOR_plus(ort[2][1], ort[1][2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81505, _mut81506, _mut81507, _mut81508)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.mat2quat_419", _mut81509, _mut81510, _mut81511, _mut81512);
                }
            }
        }
        return quat;
    }

    /**
     * Revert a rotation.
     * Build a rotation which reverse the effect of another
     * rotation. This means that if r(u) = v, then r.revert(v) = u. The
     * instance is not changed.
     * @return a new rotation whose effect is the reverse of the effect
     * of the instance
     */
    public Rotation revert() {
        return new Rotation(-q0, q1, q2, q3, false);
    }

    /**
     * Get the scalar coordinate of the quaternion.
     * @return scalar coordinate of the quaternion
     */
    public double getQ0() {
        return q0;
    }

    /**
     * Get the first coordinate of the vectorial part of the quaternion.
     * @return first coordinate of the vectorial part of the quaternion
     */
    public double getQ1() {
        return q1;
    }

    /**
     * Get the second coordinate of the vectorial part of the quaternion.
     * @return second coordinate of the vectorial part of the quaternion
     */
    public double getQ2() {
        return q2;
    }

    /**
     * Get the third coordinate of the vectorial part of the quaternion.
     * @return third coordinate of the vectorial part of the quaternion
     */
    public double getQ3() {
        return q3;
    }

    /**
     * Get the normalized axis of the rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #getAxis(RotationConvention) getAxis(RotationConvention.VECTOR_OPERATOR)}
     * </p>
     * @return normalized axis of the rotation
     * @see #Rotation(Vector3D, double, RotationConvention)
     * @deprecated as of 3.6, replaced with {@link #getAxis(RotationConvention)}
     */
    @Deprecated
    public Vector3D getAxis() {
        return getAxis(RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Get the normalized axis of the rotation.
     * <p>
     * Note that as {@link #getAngle()} always returns an angle
     * between 0 and &pi;, changing the convention changes the
     * direction of the axis, not the sign of the angle.
     * </p>
     * @param convention convention to use for the semantics of the angle
     * @return normalized axis of the rotation
     * @see #Rotation(Vector3D, double, RotationConvention)
     * @since 3.6
     */
    public Vector3D getAxis(final RotationConvention convention) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540");
        final double squaredSine = AOR_plus(AOR_plus(AOR_multiply(q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81621, _mut81622, _mut81623, _mut81624), AOR_multiply(q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81625, _mut81626, _mut81627, _mut81628), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81629, _mut81630, _mut81631, _mut81632), AOR_multiply(q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81633, _mut81634, _mut81635, _mut81636), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81637, _mut81638, _mut81639, _mut81640);
        if (ROR_equals(squaredSine, 0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81641, _mut81642, _mut81643, _mut81644, _mut81645)) {
            return convention == RotationConvention.VECTOR_OPERATOR ? Vector3D.PLUS_I : Vector3D.MINUS_I;
        } else {
            final double sgn = convention == RotationConvention.VECTOR_OPERATOR ? +1 : -1;
            if (ROR_less(q0, 0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81646, _mut81647, _mut81648, _mut81649, _mut81650)) {
                final double inverse = AOR_divide(sgn, FastMath.sqrt(squaredSine), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81651, _mut81652, _mut81653, _mut81654);
                return new Vector3D(AOR_multiply(q1, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81655, _mut81656, _mut81657, _mut81658), AOR_multiply(q2, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81659, _mut81660, _mut81661, _mut81662), AOR_multiply(q3, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81663, _mut81664, _mut81665, _mut81666));
            }
            final double inverse = AOR_divide(-sgn, FastMath.sqrt(squaredSine), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81667, _mut81668, _mut81669, _mut81670);
            return new Vector3D(AOR_multiply(q1, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81671, _mut81672, _mut81673, _mut81674), AOR_multiply(q2, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81675, _mut81676, _mut81677, _mut81678), AOR_multiply(q3, inverse, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAxis_540", _mut81679, _mut81680, _mut81681, _mut81682));
        }
    }

    /**
     * Get the angle of the rotation.
     * @return angle of the rotation (between 0 and &pi;)
     * @see #Rotation(Vector3D, double)
     */
    public double getAngle() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559");
        if ((_mut81693 ? ((ROR_less(q0, -0.1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81683, _mut81684, _mut81685, _mut81686, _mut81687)) && (ROR_greater(q0, 0.1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81688, _mut81689, _mut81690, _mut81691, _mut81692))) : ((ROR_less(q0, -0.1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81683, _mut81684, _mut81685, _mut81686, _mut81687)) || (ROR_greater(q0, 0.1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81688, _mut81689, _mut81690, _mut81691, _mut81692))))) {
            return AOR_multiply(2, FastMath.asin(FastMath.sqrt(AOR_plus(AOR_plus(AOR_multiply(q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81703, _mut81704, _mut81705, _mut81706), AOR_multiply(q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81707, _mut81708, _mut81709, _mut81710), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81711, _mut81712, _mut81713, _mut81714), AOR_multiply(q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81715, _mut81716, _mut81717, _mut81718), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81719, _mut81720, _mut81721, _mut81722))), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81723, _mut81724, _mut81725, _mut81726);
        } else if (ROR_less(q0, 0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81694, _mut81695, _mut81696, _mut81697, _mut81698)) {
            return AOR_multiply(2, FastMath.acos(-q0), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81699, _mut81700, _mut81701, _mut81702);
        }
        return AOR_multiply(2, FastMath.acos(q0), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngle_559", _mut81727, _mut81728, _mut81729, _mut81730);
    }

    /**
     * Get the Cardan or Euler angles corresponding to the instance.
     *
     * <p>
     * Calling this method is equivalent to call
     * {@link #getAngles(RotationOrder, RotationConvention)
     * getAngles(order, RotationConvention.VECTOR_OPERATOR)}
     * </p>
     *
     * @param order rotation order to use
     * @return an array of three angles, in the order specified by the set
     * @exception CardanEulerSingularityException if the rotation is
     * singular with respect to the angles set specified
     * @deprecated as of 3.6, replaced with {@link #getAngles(RotationOrder, RotationConvention)}
     */
    @Deprecated
    public double[] getAngles(RotationOrder order) throws CardanEulerSingularityException {
        return getAngles(order, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Get the Cardan or Euler angles corresponding to the instance.
     *
     * <p>The equations show that each rotation can be defined by two
     * different values of the Cardan or Euler angles set. For example
     * if Cardan angles are used, the rotation defined by the angles
     * a<sub>1</sub>, a<sub>2</sub> and a<sub>3</sub> is the same as
     * the rotation defined by the angles &pi; + a<sub>1</sub>, &pi;
     * - a<sub>2</sub> and &pi; + a<sub>3</sub>. This method implements
     * the following arbitrary choices:</p>
     * <ul>
     *   <li>for Cardan angles, the chosen set is the one for which the
     *   second angle is between -&pi;/2 and &pi;/2 (i.e its cosine is
     *   positive),</li>
     *   <li>for Euler angles, the chosen set is the one for which the
     *   second angle is between 0 and &pi; (i.e its sine is positive).</li>
     * </ul>
     *
     * <p>Cardan and Euler angle have a very disappointing drawback: all
     * of them have singularities. This means that if the instance is
     * too close to the singularities corresponding to the given
     * rotation order, it will be impossible to retrieve the angles. For
     * Cardan angles, this is often called gimbal lock. There is
     * <em>nothing</em> to do to prevent this, it is an intrinsic problem
     * with Cardan and Euler representation (but not a problem with the
     * rotation itself, which is perfectly well defined). For Cardan
     * angles, singularities occur when the second angle is close to
     * -&pi;/2 or +&pi;/2, for Euler angle singularities occur when the
     * second angle is close to 0 or &pi;, this implies that the identity
     * rotation is always singular for Euler angles!</p>
     *
     * @param order rotation order to use
     * @param convention convention to use for the semantics of the angle
     * @return an array of three angles, in the order specified by the set
     * @exception CardanEulerSingularityException if the rotation is
     * singular with respect to the angles set specified
     * @since 3.6
     */
    public double[] getAngles(RotationOrder order, RotationConvention convention) throws CardanEulerSingularityException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625");
        if (convention == RotationConvention.VECTOR_OPERATOR) {
            if (order == RotationOrder.XYZ) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81994 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81984, _mut81985, _mut81986, _mut81987, _mut81988)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81989, _mut81990, _mut81991, _mut81992, _mut81993))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81984, _mut81985, _mut81986, _mut81987, _mut81988)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81989, _mut81990, _mut81991, _mut81992, _mut81993))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-(v1.getY()), v1.getZ()), FastMath.asin(v2.getZ()), FastMath.atan2(-(v2.getY()), v2.getX()) };
            } else if (order == RotationOrder.XZY) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81983 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81973, _mut81974, _mut81975, _mut81976, _mut81977)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81978, _mut81979, _mut81980, _mut81981, _mut81982))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81973, _mut81974, _mut81975, _mut81976, _mut81977)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81978, _mut81979, _mut81980, _mut81981, _mut81982))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v1.getZ(), v1.getY()), -FastMath.asin(v2.getY()), FastMath.atan2(v2.getZ(), v2.getX()) };
            } else if (order == RotationOrder.YXZ) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81972 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81962, _mut81963, _mut81964, _mut81965, _mut81966)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81967, _mut81968, _mut81969, _mut81970, _mut81971))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81962, _mut81963, _mut81964, _mut81965, _mut81966)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81967, _mut81968, _mut81969, _mut81970, _mut81971))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v1.getX(), v1.getZ()), -FastMath.asin(v2.getZ()), FastMath.atan2(v2.getX(), v2.getY()) };
            } else if (order == RotationOrder.YZX) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81961 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81951, _mut81952, _mut81953, _mut81954, _mut81955)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81956, _mut81957, _mut81958, _mut81959, _mut81960))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81951, _mut81952, _mut81953, _mut81954, _mut81955)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81956, _mut81957, _mut81958, _mut81959, _mut81960))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-(v1.getZ()), v1.getX()), FastMath.asin(v2.getX()), FastMath.atan2(-(v2.getZ()), v2.getY()) };
            } else if (order == RotationOrder.ZXY) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81950 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81940, _mut81941, _mut81942, _mut81943, _mut81944)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81945, _mut81946, _mut81947, _mut81948, _mut81949))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81940, _mut81941, _mut81942, _mut81943, _mut81944)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81945, _mut81946, _mut81947, _mut81948, _mut81949))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-(v1.getX()), v1.getY()), FastMath.asin(v2.getY()), FastMath.atan2(-(v2.getX()), v2.getZ()) };
            } else if (order == RotationOrder.ZYX) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81939 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81929, _mut81930, _mut81931, _mut81932, _mut81933)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81934, _mut81935, _mut81936, _mut81937, _mut81938))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81929, _mut81930, _mut81931, _mut81932, _mut81933)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81934, _mut81935, _mut81936, _mut81937, _mut81938))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v1.getY(), v1.getX()), -FastMath.asin(v2.getX()), FastMath.atan2(v2.getY(), v2.getZ()) };
            } else if (order == RotationOrder.XYX) {
                // and we can choose to have theta in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81928 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81918, _mut81919, _mut81920, _mut81921, _mut81922)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81923, _mut81924, _mut81925, _mut81926, _mut81927))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81918, _mut81919, _mut81920, _mut81921, _mut81922)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81923, _mut81924, _mut81925, _mut81926, _mut81927))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getY(), -v1.getZ()), FastMath.acos(v2.getX()), FastMath.atan2(v2.getY(), v2.getZ()) };
            } else if (order == RotationOrder.XZX) {
                // and we can choose to have psi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81917 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81907, _mut81908, _mut81909, _mut81910, _mut81911)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81912, _mut81913, _mut81914, _mut81915, _mut81916))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81907, _mut81908, _mut81909, _mut81910, _mut81911)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81912, _mut81913, _mut81914, _mut81915, _mut81916))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getZ(), v1.getY()), FastMath.acos(v2.getX()), FastMath.atan2(v2.getZ(), -v2.getY()) };
            } else if (order == RotationOrder.YXY) {
                // and we can choose to have phi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81906 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81896, _mut81897, _mut81898, _mut81899, _mut81900)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81901, _mut81902, _mut81903, _mut81904, _mut81905))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81896, _mut81897, _mut81898, _mut81899, _mut81900)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81901, _mut81902, _mut81903, _mut81904, _mut81905))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getX(), v1.getZ()), FastMath.acos(v2.getY()), FastMath.atan2(v2.getX(), -v2.getZ()) };
            } else if (order == RotationOrder.YZY) {
                // and we can choose to have psi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81895 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81885, _mut81886, _mut81887, _mut81888, _mut81889)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81890, _mut81891, _mut81892, _mut81893, _mut81894))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81885, _mut81886, _mut81887, _mut81888, _mut81889)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81890, _mut81891, _mut81892, _mut81893, _mut81894))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getZ(), -v1.getX()), FastMath.acos(v2.getY()), FastMath.atan2(v2.getZ(), v2.getX()) };
            } else if (order == RotationOrder.ZXZ) {
                // and we can choose to have phi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81884 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81874, _mut81875, _mut81876, _mut81877, _mut81878)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81879, _mut81880, _mut81881, _mut81882, _mut81883))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81874, _mut81875, _mut81876, _mut81877, _mut81878)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81879, _mut81880, _mut81881, _mut81882, _mut81883))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getX(), -v1.getY()), FastMath.acos(v2.getZ()), FastMath.atan2(v2.getX(), v2.getY()) };
            } else {
                // and we can choose to have theta in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81873 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81863, _mut81864, _mut81865, _mut81866, _mut81867)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81868, _mut81869, _mut81870, _mut81871, _mut81872))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81863, _mut81864, _mut81865, _mut81866, _mut81867)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81868, _mut81869, _mut81870, _mut81871, _mut81872))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v1.getY(), v1.getX()), FastMath.acos(v2.getZ()), FastMath.atan2(v2.getY(), -v2.getX()) };
            }
        } else {
            if (order == RotationOrder.XYZ) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81862 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81852, _mut81853, _mut81854, _mut81855, _mut81856)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81857, _mut81858, _mut81859, _mut81860, _mut81861))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81852, _mut81853, _mut81854, _mut81855, _mut81856)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81857, _mut81858, _mut81859, _mut81860, _mut81861))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-v2.getY(), v2.getZ()), FastMath.asin(v2.getX()), FastMath.atan2(-v1.getY(), v1.getX()) };
            } else if (order == RotationOrder.XZY) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81851 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81841, _mut81842, _mut81843, _mut81844, _mut81845)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81846, _mut81847, _mut81848, _mut81849, _mut81850))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81841, _mut81842, _mut81843, _mut81844, _mut81845)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81846, _mut81847, _mut81848, _mut81849, _mut81850))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v2.getZ(), v2.getY()), -FastMath.asin(v2.getX()), FastMath.atan2(v1.getZ(), v1.getX()) };
            } else if (order == RotationOrder.YXZ) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81840 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81830, _mut81831, _mut81832, _mut81833, _mut81834)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81835, _mut81836, _mut81837, _mut81838, _mut81839))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81830, _mut81831, _mut81832, _mut81833, _mut81834)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81835, _mut81836, _mut81837, _mut81838, _mut81839))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v2.getX(), v2.getZ()), -FastMath.asin(v2.getY()), FastMath.atan2(v1.getX(), v1.getY()) };
            } else if (order == RotationOrder.YZX) {
                // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81829 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81819, _mut81820, _mut81821, _mut81822, _mut81823)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81824, _mut81825, _mut81826, _mut81827, _mut81828))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81819, _mut81820, _mut81821, _mut81822, _mut81823)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81824, _mut81825, _mut81826, _mut81827, _mut81828))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-v2.getZ(), v2.getX()), FastMath.asin(v2.getY()), FastMath.atan2(-v1.getZ(), v1.getY()) };
            } else if (order == RotationOrder.ZXY) {
                // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81818 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81808, _mut81809, _mut81810, _mut81811, _mut81812)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81813, _mut81814, _mut81815, _mut81816, _mut81817))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81808, _mut81809, _mut81810, _mut81811, _mut81812)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81813, _mut81814, _mut81815, _mut81816, _mut81817))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(-v2.getX(), v2.getY()), FastMath.asin(v2.getZ()), FastMath.atan2(-v1.getX(), v1.getZ()) };
            } else if (order == RotationOrder.ZYX) {
                // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81807 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81797, _mut81798, _mut81799, _mut81800, _mut81801)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81802, _mut81803, _mut81804, _mut81805, _mut81806))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81797, _mut81798, _mut81799, _mut81800, _mut81801)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81802, _mut81803, _mut81804, _mut81805, _mut81806))))) {
                    throw new CardanEulerSingularityException(true);
                }
                return new double[] { FastMath.atan2(v2.getY(), v2.getX()), -FastMath.asin(v2.getZ()), FastMath.atan2(v1.getY(), v1.getZ()) };
            } else if (order == RotationOrder.XYX) {
                // and we can choose to have theta in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81796 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81786, _mut81787, _mut81788, _mut81789, _mut81790)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81791, _mut81792, _mut81793, _mut81794, _mut81795))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81786, _mut81787, _mut81788, _mut81789, _mut81790)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81791, _mut81792, _mut81793, _mut81794, _mut81795))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getY(), -v2.getZ()), FastMath.acos(v2.getX()), FastMath.atan2(v1.getY(), v1.getZ()) };
            } else if (order == RotationOrder.XZX) {
                // and we can choose to have psi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_I);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
                if ((_mut81785 ? ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81775, _mut81776, _mut81777, _mut81778, _mut81779)) && (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81780, _mut81781, _mut81782, _mut81783, _mut81784))) : ((ROR_less(v2.getX(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81775, _mut81776, _mut81777, _mut81778, _mut81779)) || (ROR_greater(v2.getX(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81780, _mut81781, _mut81782, _mut81783, _mut81784))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getZ(), v2.getY()), FastMath.acos(v2.getX()), FastMath.atan2(v1.getZ(), -v1.getY()) };
            } else if (order == RotationOrder.YXY) {
                // and we can choose to have phi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81774 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81764, _mut81765, _mut81766, _mut81767, _mut81768)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81769, _mut81770, _mut81771, _mut81772, _mut81773))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81764, _mut81765, _mut81766, _mut81767, _mut81768)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81769, _mut81770, _mut81771, _mut81772, _mut81773))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getX(), v2.getZ()), FastMath.acos(v2.getY()), FastMath.atan2(v1.getX(), -v1.getZ()) };
            } else if (order == RotationOrder.YZY) {
                // and we can choose to have psi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_J);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
                if ((_mut81763 ? ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81753, _mut81754, _mut81755, _mut81756, _mut81757)) && (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81758, _mut81759, _mut81760, _mut81761, _mut81762))) : ((ROR_less(v2.getY(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81753, _mut81754, _mut81755, _mut81756, _mut81757)) || (ROR_greater(v2.getY(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81758, _mut81759, _mut81760, _mut81761, _mut81762))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getZ(), -v2.getX()), FastMath.acos(v2.getY()), FastMath.atan2(v1.getZ(), v1.getX()) };
            } else if (order == RotationOrder.ZXZ) {
                // and we can choose to have phi in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81752 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81742, _mut81743, _mut81744, _mut81745, _mut81746)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81747, _mut81748, _mut81749, _mut81750, _mut81751))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81742, _mut81743, _mut81744, _mut81745, _mut81746)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81747, _mut81748, _mut81749, _mut81750, _mut81751))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getX(), -v2.getY()), FastMath.acos(v2.getZ()), FastMath.atan2(v1.getX(), v1.getY()) };
            } else {
                // and we can choose to have theta in the interval [0 ; PI]
                Vector3D v1 = applyTo(Vector3D.PLUS_K);
                Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
                if ((_mut81741 ? ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81731, _mut81732, _mut81733, _mut81734, _mut81735)) && (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81736, _mut81737, _mut81738, _mut81739, _mut81740))) : ((ROR_less(v2.getZ(), -0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81731, _mut81732, _mut81733, _mut81734, _mut81735)) || (ROR_greater(v2.getZ(), 0.9999999999, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getAngles_625", _mut81736, _mut81737, _mut81738, _mut81739, _mut81740))))) {
                    throw new CardanEulerSingularityException(false);
                }
                return new double[] { FastMath.atan2(v2.getY(), v2.getX()), FastMath.acos(v2.getZ()), FastMath.atan2(v1.getY(), -v1.getX()) };
            }
        }
    }

    /**
     * Get the 3X3 matrix corresponding to the instance
     * @return the matrix corresponding to the instance
     */
    public double[][] getMatrix() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071");
        // products
        double q0q0 = AOR_multiply(q0, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut81995, _mut81996, _mut81997, _mut81998);
        double q0q1 = AOR_multiply(q0, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut81999, _mut82000, _mut82001, _mut82002);
        double q0q2 = AOR_multiply(q0, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82003, _mut82004, _mut82005, _mut82006);
        double q0q3 = AOR_multiply(q0, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82007, _mut82008, _mut82009, _mut82010);
        double q1q1 = AOR_multiply(q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82011, _mut82012, _mut82013, _mut82014);
        double q1q2 = AOR_multiply(q1, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82015, _mut82016, _mut82017, _mut82018);
        double q1q3 = AOR_multiply(q1, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82019, _mut82020, _mut82021, _mut82022);
        double q2q2 = AOR_multiply(q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82023, _mut82024, _mut82025, _mut82026);
        double q2q3 = AOR_multiply(q2, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82027, _mut82028, _mut82029, _mut82030);
        double q3q3 = AOR_multiply(q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82031, _mut82032, _mut82033, _mut82034);
        // create the matrix
        double[][] m = new double[3][];
        m[0] = new double[3];
        m[1] = new double[3];
        m[2] = new double[3];
        m[0][0] = AOR_minus(AOR_multiply(2.0, (AOR_plus(q0q0, q1q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82035, _mut82036, _mut82037, _mut82038)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82039, _mut82040, _mut82041, _mut82042), 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82043, _mut82044, _mut82045, _mut82046);
        m[1][0] = AOR_multiply(2.0, (AOR_minus(q1q2, q0q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82047, _mut82048, _mut82049, _mut82050)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82051, _mut82052, _mut82053, _mut82054);
        m[2][0] = AOR_multiply(2.0, (AOR_plus(q1q3, q0q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82055, _mut82056, _mut82057, _mut82058)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82059, _mut82060, _mut82061, _mut82062);
        m[0][1] = AOR_multiply(2.0, (AOR_plus(q1q2, q0q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82063, _mut82064, _mut82065, _mut82066)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82067, _mut82068, _mut82069, _mut82070);
        m[1][1] = AOR_minus(AOR_multiply(2.0, (AOR_plus(q0q0, q2q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82071, _mut82072, _mut82073, _mut82074)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82075, _mut82076, _mut82077, _mut82078), 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82079, _mut82080, _mut82081, _mut82082);
        m[2][1] = AOR_multiply(2.0, (AOR_minus(q2q3, q0q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82083, _mut82084, _mut82085, _mut82086)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82087, _mut82088, _mut82089, _mut82090);
        m[0][2] = AOR_multiply(2.0, (AOR_minus(q1q3, q0q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82091, _mut82092, _mut82093, _mut82094)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82095, _mut82096, _mut82097, _mut82098);
        m[1][2] = AOR_multiply(2.0, (AOR_plus(q2q3, q0q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82099, _mut82100, _mut82101, _mut82102)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82103, _mut82104, _mut82105, _mut82106);
        m[2][2] = AOR_minus(AOR_multiply(2.0, (AOR_plus(q0q0, q3q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82107, _mut82108, _mut82109, _mut82110)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82111, _mut82112, _mut82113, _mut82114), 1.0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.getMatrix_1071", _mut82115, _mut82116, _mut82117, _mut82118);
        return m;
    }

    /**
     * Apply the rotation to a vector.
     * @param u vector to apply the rotation to
     * @return a new vector which is the image of u by the rotation
     */
    public Vector3D applyTo(Vector3D u) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111");
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        double s = AOR_plus(AOR_plus(AOR_multiply(q1, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82119, _mut82120, _mut82121, _mut82122), AOR_multiply(q2, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82123, _mut82124, _mut82125, _mut82126), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82127, _mut82128, _mut82129, _mut82130), AOR_multiply(q3, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82131, _mut82132, _mut82133, _mut82134), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82135, _mut82136, _mut82137, _mut82138);
        return new Vector3D(AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(x, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82139, _mut82140, _mut82141, _mut82142), (AOR_minus(AOR_multiply(q2, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82143, _mut82144, _mut82145, _mut82146), AOR_multiply(q3, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82147, _mut82148, _mut82149, _mut82150), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82151, _mut82152, _mut82153, _mut82154)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82155, _mut82156, _mut82157, _mut82158)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82159, _mut82160, _mut82161, _mut82162), AOR_multiply(s, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82163, _mut82164, _mut82165, _mut82166), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82167, _mut82168, _mut82169, _mut82170)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82171, _mut82172, _mut82173, _mut82174), x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82175, _mut82176, _mut82177, _mut82178), AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(y, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82179, _mut82180, _mut82181, _mut82182), (AOR_minus(AOR_multiply(q3, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82183, _mut82184, _mut82185, _mut82186), AOR_multiply(q1, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82187, _mut82188, _mut82189, _mut82190), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82191, _mut82192, _mut82193, _mut82194)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82195, _mut82196, _mut82197, _mut82198)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82199, _mut82200, _mut82201, _mut82202), AOR_multiply(s, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82203, _mut82204, _mut82205, _mut82206), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82207, _mut82208, _mut82209, _mut82210)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82211, _mut82212, _mut82213, _mut82214), y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82215, _mut82216, _mut82217, _mut82218), AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(z, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82219, _mut82220, _mut82221, _mut82222), (AOR_minus(AOR_multiply(q1, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82223, _mut82224, _mut82225, _mut82226), AOR_multiply(q2, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82227, _mut82228, _mut82229, _mut82230), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82231, _mut82232, _mut82233, _mut82234)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82235, _mut82236, _mut82237, _mut82238)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82239, _mut82240, _mut82241, _mut82242), AOR_multiply(s, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82243, _mut82244, _mut82245, _mut82246), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82247, _mut82248, _mut82249, _mut82250)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82251, _mut82252, _mut82253, _mut82254), z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1111", _mut82255, _mut82256, _mut82257, _mut82258));
    }

    /**
     * Apply the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to (it can be the same
     * array as in)
     */
    public void applyTo(final double[] in, final double[] out) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130");
        final double x = in[0];
        final double y = in[1];
        final double z = in[2];
        final double s = AOR_plus(AOR_plus(AOR_multiply(q1, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82259, _mut82260, _mut82261, _mut82262), AOR_multiply(q2, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82263, _mut82264, _mut82265, _mut82266), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82267, _mut82268, _mut82269, _mut82270), AOR_multiply(q3, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82271, _mut82272, _mut82273, _mut82274), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82275, _mut82276, _mut82277, _mut82278);
        out[0] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(x, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82279, _mut82280, _mut82281, _mut82282), (AOR_minus(AOR_multiply(q2, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82283, _mut82284, _mut82285, _mut82286), AOR_multiply(q3, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82287, _mut82288, _mut82289, _mut82290), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82291, _mut82292, _mut82293, _mut82294)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82295, _mut82296, _mut82297, _mut82298)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82299, _mut82300, _mut82301, _mut82302), AOR_multiply(s, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82303, _mut82304, _mut82305, _mut82306), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82307, _mut82308, _mut82309, _mut82310)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82311, _mut82312, _mut82313, _mut82314), x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82315, _mut82316, _mut82317, _mut82318);
        out[1] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(y, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82319, _mut82320, _mut82321, _mut82322), (AOR_minus(AOR_multiply(q3, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82323, _mut82324, _mut82325, _mut82326), AOR_multiply(q1, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82327, _mut82328, _mut82329, _mut82330), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82331, _mut82332, _mut82333, _mut82334)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82335, _mut82336, _mut82337, _mut82338)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82339, _mut82340, _mut82341, _mut82342), AOR_multiply(s, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82343, _mut82344, _mut82345, _mut82346), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82347, _mut82348, _mut82349, _mut82350)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82351, _mut82352, _mut82353, _mut82354), y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82355, _mut82356, _mut82357, _mut82358);
        out[2] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(q0, (AOR_minus(AOR_multiply(z, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82359, _mut82360, _mut82361, _mut82362), (AOR_minus(AOR_multiply(q1, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82363, _mut82364, _mut82365, _mut82366), AOR_multiply(q2, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82367, _mut82368, _mut82369, _mut82370), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82371, _mut82372, _mut82373, _mut82374)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82375, _mut82376, _mut82377, _mut82378)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82379, _mut82380, _mut82381, _mut82382), AOR_multiply(s, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82383, _mut82384, _mut82385, _mut82386), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82387, _mut82388, _mut82389, _mut82390)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82391, _mut82392, _mut82393, _mut82394), z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyTo_1130", _mut82395, _mut82396, _mut82397, _mut82398);
    }

    /**
     * Apply the inverse of the rotation to a vector.
     * @param u vector to apply the inverse of the rotation to
     * @return a new vector which such that u is its image by the rotation
     */
    public Vector3D applyInverseTo(Vector3D u) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148");
        double x = u.getX();
        double y = u.getY();
        double z = u.getZ();
        double s = AOR_plus(AOR_plus(AOR_multiply(q1, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82399, _mut82400, _mut82401, _mut82402), AOR_multiply(q2, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82403, _mut82404, _mut82405, _mut82406), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82407, _mut82408, _mut82409, _mut82410), AOR_multiply(q3, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82411, _mut82412, _mut82413, _mut82414), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82415, _mut82416, _mut82417, _mut82418);
        double m0 = -q0;
        return new Vector3D(AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(x, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82419, _mut82420, _mut82421, _mut82422), (AOR_minus(AOR_multiply(q2, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82423, _mut82424, _mut82425, _mut82426), AOR_multiply(q3, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82427, _mut82428, _mut82429, _mut82430), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82431, _mut82432, _mut82433, _mut82434)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82435, _mut82436, _mut82437, _mut82438)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82439, _mut82440, _mut82441, _mut82442), AOR_multiply(s, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82443, _mut82444, _mut82445, _mut82446), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82447, _mut82448, _mut82449, _mut82450)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82451, _mut82452, _mut82453, _mut82454), x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82455, _mut82456, _mut82457, _mut82458), AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(y, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82459, _mut82460, _mut82461, _mut82462), (AOR_minus(AOR_multiply(q3, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82463, _mut82464, _mut82465, _mut82466), AOR_multiply(q1, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82467, _mut82468, _mut82469, _mut82470), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82471, _mut82472, _mut82473, _mut82474)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82475, _mut82476, _mut82477, _mut82478)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82479, _mut82480, _mut82481, _mut82482), AOR_multiply(s, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82483, _mut82484, _mut82485, _mut82486), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82487, _mut82488, _mut82489, _mut82490)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82491, _mut82492, _mut82493, _mut82494), y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82495, _mut82496, _mut82497, _mut82498), AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(z, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82499, _mut82500, _mut82501, _mut82502), (AOR_minus(AOR_multiply(q1, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82503, _mut82504, _mut82505, _mut82506), AOR_multiply(q2, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82507, _mut82508, _mut82509, _mut82510), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82511, _mut82512, _mut82513, _mut82514)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82515, _mut82516, _mut82517, _mut82518)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82519, _mut82520, _mut82521, _mut82522), AOR_multiply(s, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82523, _mut82524, _mut82525, _mut82526), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82527, _mut82528, _mut82529, _mut82530)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82531, _mut82532, _mut82533, _mut82534), z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1148", _mut82535, _mut82536, _mut82537, _mut82538));
    }

    /**
     * Apply the inverse of the rotation to a vector stored in an array.
     * @param in an array with three items which stores vector to rotate
     * @param out an array with three items to put result to (it can be the same
     * array as in)
     */
    public void applyInverseTo(final double[] in, final double[] out) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168");
        final double x = in[0];
        final double y = in[1];
        final double z = in[2];
        final double s = AOR_plus(AOR_plus(AOR_multiply(q1, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82539, _mut82540, _mut82541, _mut82542), AOR_multiply(q2, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82543, _mut82544, _mut82545, _mut82546), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82547, _mut82548, _mut82549, _mut82550), AOR_multiply(q3, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82551, _mut82552, _mut82553, _mut82554), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82555, _mut82556, _mut82557, _mut82558);
        final double m0 = -q0;
        out[0] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(x, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82559, _mut82560, _mut82561, _mut82562), (AOR_minus(AOR_multiply(q2, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82563, _mut82564, _mut82565, _mut82566), AOR_multiply(q3, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82567, _mut82568, _mut82569, _mut82570), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82571, _mut82572, _mut82573, _mut82574)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82575, _mut82576, _mut82577, _mut82578)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82579, _mut82580, _mut82581, _mut82582), AOR_multiply(s, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82583, _mut82584, _mut82585, _mut82586), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82587, _mut82588, _mut82589, _mut82590)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82591, _mut82592, _mut82593, _mut82594), x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82595, _mut82596, _mut82597, _mut82598);
        out[1] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(y, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82599, _mut82600, _mut82601, _mut82602), (AOR_minus(AOR_multiply(q3, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82603, _mut82604, _mut82605, _mut82606), AOR_multiply(q1, z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82607, _mut82608, _mut82609, _mut82610), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82611, _mut82612, _mut82613, _mut82614)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82615, _mut82616, _mut82617, _mut82618)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82619, _mut82620, _mut82621, _mut82622), AOR_multiply(s, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82623, _mut82624, _mut82625, _mut82626), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82627, _mut82628, _mut82629, _mut82630)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82631, _mut82632, _mut82633, _mut82634), y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82635, _mut82636, _mut82637, _mut82638);
        out[2] = AOR_minus(AOR_multiply(2, (AOR_plus(AOR_multiply(m0, (AOR_minus(AOR_multiply(z, m0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82639, _mut82640, _mut82641, _mut82642), (AOR_minus(AOR_multiply(q1, y, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82643, _mut82644, _mut82645, _mut82646), AOR_multiply(q2, x, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82647, _mut82648, _mut82649, _mut82650), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82651, _mut82652, _mut82653, _mut82654)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82655, _mut82656, _mut82657, _mut82658)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82659, _mut82660, _mut82661, _mut82662), AOR_multiply(s, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82663, _mut82664, _mut82665, _mut82666), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82667, _mut82668, _mut82669, _mut82670)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82671, _mut82672, _mut82673, _mut82674), z, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.applyInverseTo_1168", _mut82675, _mut82676, _mut82677, _mut82678);
    }

    /**
     * Apply the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #compose(Rotation, RotationConvention)
     * compose(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     */
    public Rotation applyTo(Rotation r) {
        return compose(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the instance to a rotation is computing the composition
     * in an order compliant with the following rule : let {@code u} be any
     * vector and {@code v} its image by {@code r1} (i.e.
     * {@code r1.applyTo(u) = v}). Let {@code w} be the image of {@code v} by
     * rotation {@code r2} (i.e. {@code r2.applyTo(v) = w}). Then
     * {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.compose(r1, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.compose(r2, RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the instance
     */
    public Rotation compose(final Rotation r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInternal(r) : r.composeInternal(this);
    }

    /**
     * Compose the instance with another rotation using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the instance
     * using vector operator convention
     */
    private Rotation composeInternal(final Rotation r) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230");
        return new Rotation(AOR_minus(AOR_multiply(r.q0, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82679, _mut82680, _mut82681, _mut82682), (AOR_plus(AOR_plus(AOR_multiply(r.q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82683, _mut82684, _mut82685, _mut82686), AOR_multiply(r.q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82687, _mut82688, _mut82689, _mut82690), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82691, _mut82692, _mut82693, _mut82694), AOR_multiply(r.q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82695, _mut82696, _mut82697, _mut82698), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82699, _mut82700, _mut82701, _mut82702)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82703, _mut82704, _mut82705, _mut82706), AOR_plus(AOR_plus(AOR_multiply(r.q1, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82707, _mut82708, _mut82709, _mut82710), AOR_multiply(r.q0, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82711, _mut82712, _mut82713, _mut82714), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82715, _mut82716, _mut82717, _mut82718), (AOR_minus(AOR_multiply(r.q2, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82719, _mut82720, _mut82721, _mut82722), AOR_multiply(r.q3, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82723, _mut82724, _mut82725, _mut82726), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82727, _mut82728, _mut82729, _mut82730)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82731, _mut82732, _mut82733, _mut82734), AOR_plus(AOR_plus(AOR_multiply(r.q2, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82735, _mut82736, _mut82737, _mut82738), AOR_multiply(r.q0, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82739, _mut82740, _mut82741, _mut82742), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82743, _mut82744, _mut82745, _mut82746), (AOR_minus(AOR_multiply(r.q3, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82747, _mut82748, _mut82749, _mut82750), AOR_multiply(r.q1, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82751, _mut82752, _mut82753, _mut82754), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82755, _mut82756, _mut82757, _mut82758)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82759, _mut82760, _mut82761, _mut82762), AOR_plus(AOR_plus(AOR_multiply(r.q3, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82763, _mut82764, _mut82765, _mut82766), AOR_multiply(r.q0, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82767, _mut82768, _mut82769, _mut82770), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82771, _mut82772, _mut82773, _mut82774), (AOR_minus(AOR_multiply(r.q1, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82775, _mut82776, _mut82777, _mut82778), AOR_multiply(r.q2, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82779, _mut82780, _mut82781, _mut82782), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82783, _mut82784, _mut82785, _mut82786)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInternal_1230", _mut82787, _mut82788, _mut82789, _mut82790), false);
    }

    /**
     * Apply the inverse of the instance to another rotation.
     * <p>
     * Calling this method is equivalent to call
     * {@link #composeInverse(Rotation, RotationConvention)
     * composeInverse(r, RotationConvention.VECTOR_OPERATOR)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public Rotation applyInverseTo(Rotation r) {
        return composeInverse(r, RotationConvention.VECTOR_OPERATOR);
    }

    /**
     * Compose the inverse of the instance with another rotation.
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#VECTOR_OPERATOR vector operator} convention,
     * applying the inverse of the instance to a rotation is computing
     * the composition in an order compliant with the following rule :
     * let {@code u} be any vector and {@code v} its image by {@code r1}
     * (i.e. {@code r1.applyTo(u) = v}). Let {@code w} be the inverse image
     * of {@code v} by {@code r2} (i.e. {@code r2.applyInverseTo(v) = w}).
     * Then {@code w = comp.applyTo(u)}, where
     * {@code comp = r2.composeInverse(r1)}.
     * </p>
     * <p>
     * If the semantics of the rotations composition corresponds to a
     * {@link RotationConvention#FRAME_TRANSFORM frame transform} convention,
     * the application order will be reversed, which means it is the
     * <em>innermost</em> rotation that will be reversed. So keeping the exact same
     * meaning of all {@code r1}, {@code r2}, {@code u}, {@code v}, {@code w}
     * and  {@code comp} as above, {@code comp} could also be computed as
     * {@code comp = r1.revert().composeInverse(r2.revert(), RotationConvention.FRAME_TRANSFORM)}.
     * </p>
     * @param r rotation to apply the rotation to
     * @param convention convention to use for the semantics of the angle
     * @return a new rotation which is the composition of r by the inverse
     * of the instance
     */
    public Rotation composeInverse(final Rotation r, final RotationConvention convention) {
        return convention == RotationConvention.VECTOR_OPERATOR ? composeInverseInternal(r) : r.composeInternal(revert());
    }

    /**
     * Compose the inverse of the instance with another rotation
     * using vector operator convention.
     * @param r rotation to apply the rotation to
     * @return a new rotation which is the composition of r by the inverse
     * of the instance using vector operator convention
     */
    private Rotation composeInverseInternal(Rotation r) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289");
        return new Rotation(AOR_minus(AOR_multiply(-r.q0, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82791, _mut82792, _mut82793, _mut82794), (AOR_plus(AOR_plus(AOR_multiply(r.q1, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82795, _mut82796, _mut82797, _mut82798), AOR_multiply(r.q2, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82799, _mut82800, _mut82801, _mut82802), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82803, _mut82804, _mut82805, _mut82806), AOR_multiply(r.q3, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82807, _mut82808, _mut82809, _mut82810), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82811, _mut82812, _mut82813, _mut82814)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82815, _mut82816, _mut82817, _mut82818), AOR_plus(AOR_plus(AOR_multiply(-r.q1, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82819, _mut82820, _mut82821, _mut82822), AOR_multiply(r.q0, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82823, _mut82824, _mut82825, _mut82826), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82827, _mut82828, _mut82829, _mut82830), (AOR_minus(AOR_multiply(r.q2, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82831, _mut82832, _mut82833, _mut82834), AOR_multiply(r.q3, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82835, _mut82836, _mut82837, _mut82838), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82839, _mut82840, _mut82841, _mut82842)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82843, _mut82844, _mut82845, _mut82846), AOR_plus(AOR_plus(AOR_multiply(-r.q2, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82847, _mut82848, _mut82849, _mut82850), AOR_multiply(r.q0, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82851, _mut82852, _mut82853, _mut82854), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82855, _mut82856, _mut82857, _mut82858), (AOR_minus(AOR_multiply(r.q3, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82859, _mut82860, _mut82861, _mut82862), AOR_multiply(r.q1, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82863, _mut82864, _mut82865, _mut82866), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82867, _mut82868, _mut82869, _mut82870)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82871, _mut82872, _mut82873, _mut82874), AOR_plus(AOR_plus(AOR_multiply(-r.q3, q0, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82875, _mut82876, _mut82877, _mut82878), AOR_multiply(r.q0, q3, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82879, _mut82880, _mut82881, _mut82882), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82883, _mut82884, _mut82885, _mut82886), (AOR_minus(AOR_multiply(r.q1, q2, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82887, _mut82888, _mut82889, _mut82890), AOR_multiply(r.q2, q1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82891, _mut82892, _mut82893, _mut82894), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82895, _mut82896, _mut82897, _mut82898)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.composeInverseInternal_1289", _mut82899, _mut82900, _mut82901, _mut82902), false);
    }

    /**
     * Perfect orthogonality on a 3X3 matrix.
     * @param m initial matrix (not exactly orthogonal)
     * @param threshold convergence threshold for the iterative
     * orthogonality correction (convergence is reached when the
     * difference between two steps of the Frobenius norm of the
     * correction is below this threshold)
     * @return an orthogonal matrix close to m
     * @exception NotARotationMatrixException if the matrix cannot be
     * orthogonalized with the given threshold after 10 iterations
     */
    private double[][] orthogonalizeMatrix(double[][] m, double threshold) throws NotARotationMatrixException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307");
        double[] m0 = m[0];
        double[] m1 = m[1];
        double[] m2 = m[2];
        double x00 = m0[0];
        double x01 = m0[1];
        double x02 = m0[2];
        double x10 = m1[0];
        double x11 = m1[1];
        double x12 = m1[2];
        double x20 = m2[0];
        double x21 = m2[1];
        double x22 = m2[2];
        double fn = 0;
        double fn1;
        double[][] o = new double[3][3];
        double[] o0 = o[0];
        double[] o1 = o[1];
        double[] o2 = o[2];
        // iterative correction: Xn+1 = Xn - 0.5 * (Xn.Mt.Xn - M)
        int i = 0;
        while (ROR_less(++i, 11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83484, _mut83485, _mut83486, _mut83487, _mut83488)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307");
            // Mt.Xn
            double mx00 = AOR_plus(AOR_plus(AOR_multiply(m0[0], x00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82903, _mut82904, _mut82905, _mut82906), AOR_multiply(m1[0], x10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82907, _mut82908, _mut82909, _mut82910), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82911, _mut82912, _mut82913, _mut82914), AOR_multiply(m2[0], x20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82915, _mut82916, _mut82917, _mut82918), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82919, _mut82920, _mut82921, _mut82922);
            double mx10 = AOR_plus(AOR_plus(AOR_multiply(m0[1], x00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82923, _mut82924, _mut82925, _mut82926), AOR_multiply(m1[1], x10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82927, _mut82928, _mut82929, _mut82930), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82931, _mut82932, _mut82933, _mut82934), AOR_multiply(m2[1], x20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82935, _mut82936, _mut82937, _mut82938), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82939, _mut82940, _mut82941, _mut82942);
            double mx20 = AOR_plus(AOR_plus(AOR_multiply(m0[2], x00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82943, _mut82944, _mut82945, _mut82946), AOR_multiply(m1[2], x10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82947, _mut82948, _mut82949, _mut82950), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82951, _mut82952, _mut82953, _mut82954), AOR_multiply(m2[2], x20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82955, _mut82956, _mut82957, _mut82958), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82959, _mut82960, _mut82961, _mut82962);
            double mx01 = AOR_plus(AOR_plus(AOR_multiply(m0[0], x01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82963, _mut82964, _mut82965, _mut82966), AOR_multiply(m1[0], x11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82967, _mut82968, _mut82969, _mut82970), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82971, _mut82972, _mut82973, _mut82974), AOR_multiply(m2[0], x21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82975, _mut82976, _mut82977, _mut82978), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82979, _mut82980, _mut82981, _mut82982);
            double mx11 = AOR_plus(AOR_plus(AOR_multiply(m0[1], x01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82983, _mut82984, _mut82985, _mut82986), AOR_multiply(m1[1], x11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82987, _mut82988, _mut82989, _mut82990), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82991, _mut82992, _mut82993, _mut82994), AOR_multiply(m2[1], x21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82995, _mut82996, _mut82997, _mut82998), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut82999, _mut83000, _mut83001, _mut83002);
            double mx21 = AOR_plus(AOR_plus(AOR_multiply(m0[2], x01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83003, _mut83004, _mut83005, _mut83006), AOR_multiply(m1[2], x11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83007, _mut83008, _mut83009, _mut83010), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83011, _mut83012, _mut83013, _mut83014), AOR_multiply(m2[2], x21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83015, _mut83016, _mut83017, _mut83018), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83019, _mut83020, _mut83021, _mut83022);
            double mx02 = AOR_plus(AOR_plus(AOR_multiply(m0[0], x02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83023, _mut83024, _mut83025, _mut83026), AOR_multiply(m1[0], x12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83027, _mut83028, _mut83029, _mut83030), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83031, _mut83032, _mut83033, _mut83034), AOR_multiply(m2[0], x22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83035, _mut83036, _mut83037, _mut83038), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83039, _mut83040, _mut83041, _mut83042);
            double mx12 = AOR_plus(AOR_plus(AOR_multiply(m0[1], x02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83043, _mut83044, _mut83045, _mut83046), AOR_multiply(m1[1], x12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83047, _mut83048, _mut83049, _mut83050), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83051, _mut83052, _mut83053, _mut83054), AOR_multiply(m2[1], x22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83055, _mut83056, _mut83057, _mut83058), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83059, _mut83060, _mut83061, _mut83062);
            double mx22 = AOR_plus(AOR_plus(AOR_multiply(m0[2], x02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83063, _mut83064, _mut83065, _mut83066), AOR_multiply(m1[2], x12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83067, _mut83068, _mut83069, _mut83070), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83071, _mut83072, _mut83073, _mut83074), AOR_multiply(m2[2], x22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83075, _mut83076, _mut83077, _mut83078), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83079, _mut83080, _mut83081, _mut83082);
            // Xn+1
            o0[0] = AOR_minus(x00, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x00, mx00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83083, _mut83084, _mut83085, _mut83086), AOR_multiply(x01, mx10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83087, _mut83088, _mut83089, _mut83090), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83091, _mut83092, _mut83093, _mut83094), AOR_multiply(x02, mx20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83095, _mut83096, _mut83097, _mut83098), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83099, _mut83100, _mut83101, _mut83102), m0[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83103, _mut83104, _mut83105, _mut83106)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83107, _mut83108, _mut83109, _mut83110), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83111, _mut83112, _mut83113, _mut83114);
            o0[1] = AOR_minus(x01, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x00, mx01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83115, _mut83116, _mut83117, _mut83118), AOR_multiply(x01, mx11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83119, _mut83120, _mut83121, _mut83122), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83123, _mut83124, _mut83125, _mut83126), AOR_multiply(x02, mx21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83127, _mut83128, _mut83129, _mut83130), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83131, _mut83132, _mut83133, _mut83134), m0[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83135, _mut83136, _mut83137, _mut83138)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83139, _mut83140, _mut83141, _mut83142), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83143, _mut83144, _mut83145, _mut83146);
            o0[2] = AOR_minus(x02, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x00, mx02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83147, _mut83148, _mut83149, _mut83150), AOR_multiply(x01, mx12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83151, _mut83152, _mut83153, _mut83154), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83155, _mut83156, _mut83157, _mut83158), AOR_multiply(x02, mx22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83159, _mut83160, _mut83161, _mut83162), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83163, _mut83164, _mut83165, _mut83166), m0[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83167, _mut83168, _mut83169, _mut83170)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83171, _mut83172, _mut83173, _mut83174), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83175, _mut83176, _mut83177, _mut83178);
            o1[0] = AOR_minus(x10, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x10, mx00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83179, _mut83180, _mut83181, _mut83182), AOR_multiply(x11, mx10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83183, _mut83184, _mut83185, _mut83186), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83187, _mut83188, _mut83189, _mut83190), AOR_multiply(x12, mx20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83191, _mut83192, _mut83193, _mut83194), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83195, _mut83196, _mut83197, _mut83198), m1[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83199, _mut83200, _mut83201, _mut83202)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83203, _mut83204, _mut83205, _mut83206), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83207, _mut83208, _mut83209, _mut83210);
            o1[1] = AOR_minus(x11, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x10, mx01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83211, _mut83212, _mut83213, _mut83214), AOR_multiply(x11, mx11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83215, _mut83216, _mut83217, _mut83218), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83219, _mut83220, _mut83221, _mut83222), AOR_multiply(x12, mx21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83223, _mut83224, _mut83225, _mut83226), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83227, _mut83228, _mut83229, _mut83230), m1[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83231, _mut83232, _mut83233, _mut83234)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83235, _mut83236, _mut83237, _mut83238), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83239, _mut83240, _mut83241, _mut83242);
            o1[2] = AOR_minus(x12, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x10, mx02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83243, _mut83244, _mut83245, _mut83246), AOR_multiply(x11, mx12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83247, _mut83248, _mut83249, _mut83250), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83251, _mut83252, _mut83253, _mut83254), AOR_multiply(x12, mx22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83255, _mut83256, _mut83257, _mut83258), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83259, _mut83260, _mut83261, _mut83262), m1[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83263, _mut83264, _mut83265, _mut83266)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83267, _mut83268, _mut83269, _mut83270), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83271, _mut83272, _mut83273, _mut83274);
            o2[0] = AOR_minus(x20, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x20, mx00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83275, _mut83276, _mut83277, _mut83278), AOR_multiply(x21, mx10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83279, _mut83280, _mut83281, _mut83282), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83283, _mut83284, _mut83285, _mut83286), AOR_multiply(x22, mx20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83287, _mut83288, _mut83289, _mut83290), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83291, _mut83292, _mut83293, _mut83294), m2[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83295, _mut83296, _mut83297, _mut83298)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83299, _mut83300, _mut83301, _mut83302), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83303, _mut83304, _mut83305, _mut83306);
            o2[1] = AOR_minus(x21, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x20, mx01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83307, _mut83308, _mut83309, _mut83310), AOR_multiply(x21, mx11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83311, _mut83312, _mut83313, _mut83314), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83315, _mut83316, _mut83317, _mut83318), AOR_multiply(x22, mx21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83319, _mut83320, _mut83321, _mut83322), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83323, _mut83324, _mut83325, _mut83326), m2[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83327, _mut83328, _mut83329, _mut83330)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83331, _mut83332, _mut83333, _mut83334), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83335, _mut83336, _mut83337, _mut83338);
            o2[2] = AOR_minus(x22, AOR_multiply(0.5, (AOR_minus(AOR_plus(AOR_plus(AOR_multiply(x20, mx02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83339, _mut83340, _mut83341, _mut83342), AOR_multiply(x21, mx12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83343, _mut83344, _mut83345, _mut83346), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83347, _mut83348, _mut83349, _mut83350), AOR_multiply(x22, mx22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83351, _mut83352, _mut83353, _mut83354), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83355, _mut83356, _mut83357, _mut83358), m2[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83359, _mut83360, _mut83361, _mut83362)), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83363, _mut83364, _mut83365, _mut83366), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83367, _mut83368, _mut83369, _mut83370);
            // correction on each elements
            double corr00 = AOR_minus(o0[0], m0[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83371, _mut83372, _mut83373, _mut83374);
            double corr01 = AOR_minus(o0[1], m0[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83375, _mut83376, _mut83377, _mut83378);
            double corr02 = AOR_minus(o0[2], m0[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83379, _mut83380, _mut83381, _mut83382);
            double corr10 = AOR_minus(o1[0], m1[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83383, _mut83384, _mut83385, _mut83386);
            double corr11 = AOR_minus(o1[1], m1[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83387, _mut83388, _mut83389, _mut83390);
            double corr12 = AOR_minus(o1[2], m1[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83391, _mut83392, _mut83393, _mut83394);
            double corr20 = AOR_minus(o2[0], m2[0], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83395, _mut83396, _mut83397, _mut83398);
            double corr21 = AOR_minus(o2[1], m2[1], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83399, _mut83400, _mut83401, _mut83402);
            double corr22 = AOR_minus(o2[2], m2[2], "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83403, _mut83404, _mut83405, _mut83406);
            // Frobenius norm of the correction
            fn1 = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(corr00, corr00, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83407, _mut83408, _mut83409, _mut83410), AOR_multiply(corr01, corr01, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83411, _mut83412, _mut83413, _mut83414), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83415, _mut83416, _mut83417, _mut83418), AOR_multiply(corr02, corr02, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83419, _mut83420, _mut83421, _mut83422), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83423, _mut83424, _mut83425, _mut83426), AOR_multiply(corr10, corr10, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83427, _mut83428, _mut83429, _mut83430), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83431, _mut83432, _mut83433, _mut83434), AOR_multiply(corr11, corr11, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83435, _mut83436, _mut83437, _mut83438), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83439, _mut83440, _mut83441, _mut83442), AOR_multiply(corr12, corr12, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83443, _mut83444, _mut83445, _mut83446), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83447, _mut83448, _mut83449, _mut83450), AOR_multiply(corr20, corr20, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83451, _mut83452, _mut83453, _mut83454), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83455, _mut83456, _mut83457, _mut83458), AOR_multiply(corr21, corr21, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83459, _mut83460, _mut83461, _mut83462), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83463, _mut83464, _mut83465, _mut83466), AOR_multiply(corr22, corr22, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83467, _mut83468, _mut83469, _mut83470), "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83471, _mut83472, _mut83473, _mut83474);
            // convergence test
            if (ROR_less_equals(FastMath.abs(AOR_minus(fn1, fn, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83475, _mut83476, _mut83477, _mut83478)), threshold, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83479, _mut83480, _mut83481, _mut83482, _mut83483)) {
                return o;
            }
            // prepare next iteration
            x00 = o0[0];
            x01 = o0[1];
            x02 = o0[2];
            x10 = o1[0];
            x11 = o1[1];
            x12 = o1[2];
            x20 = o2[0];
            x21 = o2[1];
            x22 = o2[2];
            fn = fn1;
        }
        // the algorithm did not converge after 10 iterations
        throw new NotARotationMatrixException(LocalizedFormats.UNABLE_TO_ORTHOGONOLIZE_MATRIX, AOR_minus(i, 1, "org.apache.commons.math3.geometry.euclidean.threed.Rotation.orthogonalizeMatrix_1307", _mut83489, _mut83490, _mut83491, _mut83492));
    }

    /**
     * Compute the <i>distance</i> between two rotations.
     * <p>The <i>distance</i> is intended here as a way to check if two
     * rotations are almost similar (i.e. they transform vectors the same way)
     * or very different. It is mathematically defined as the angle of
     * the rotation r that prepended to one of the rotations gives the other
     * one:</p>
     * <pre>
     *        r<sub>1</sub>(r) = r<sub>2</sub>
     * </pre>
     * <p>This distance is an angle between 0 and &pi;. Its value is the smallest
     * possible upper bound of the angle in radians between r<sub>1</sub>(v)
     * and r<sub>2</sub>(v) for all possible vectors v. This upper bound is
     * reached for some v. The distance is equal to 0 if and only if the two
     * rotations are identical.</p>
     * <p>Comparing two rotations should always be done using this value rather
     * than for example comparing the components of the quaternions. It is much
     * more stable, and has a geometric meaning. Also comparing quaternions
     * components is error prone since for example quaternions (0.36, 0.48, -0.48, -0.64)
     * and (-0.36, -0.48, 0.48, 0.64) represent exactly the same rotation despite
     * their components are different (they are exact opposites).</p>
     * @param r1 first rotation
     * @param r2 second rotation
     * @return <i>distance</i> between r1 and r2
     */
    public static double distance(Rotation r1, Rotation r2) {
        return r1.composeInverseInternal(r2).getAngle();
    }
}
