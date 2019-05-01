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
package org.apache.commons.math3.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Arrays utilities.
 *
 * @since 3.0
 */
public class MathArrays {

    @Conditional
    public static boolean _mut40908 = false, _mut40909 = false, _mut40910 = false, _mut40911 = false, _mut40912 = false, _mut40913 = false, _mut40914 = false, _mut40915 = false, _mut40916 = false, _mut40917 = false, _mut40918 = false, _mut40919 = false, _mut40920 = false, _mut40921 = false, _mut40922 = false, _mut40923 = false, _mut40924 = false, _mut40925 = false, _mut40926 = false, _mut40927 = false, _mut40928 = false, _mut40929 = false, _mut40930 = false, _mut40931 = false, _mut40932 = false, _mut40933 = false, _mut40934 = false, _mut40935 = false, _mut40936 = false, _mut40937 = false, _mut40938 = false, _mut40939 = false, _mut40940 = false, _mut40941 = false, _mut40942 = false, _mut40943 = false, _mut40944 = false, _mut40945 = false, _mut40946 = false, _mut40947 = false, _mut40948 = false, _mut40949 = false, _mut40950 = false, _mut40951 = false, _mut40952 = false, _mut40953 = false, _mut40954 = false, _mut40955 = false, _mut40956 = false, _mut40957 = false, _mut40958 = false, _mut40959 = false, _mut40960 = false, _mut40961 = false, _mut40962 = false, _mut40963 = false, _mut40964 = false, _mut40965 = false, _mut40966 = false, _mut40967 = false, _mut40968 = false, _mut40969 = false, _mut40970 = false, _mut40971 = false, _mut40972 = false, _mut40973 = false, _mut40974 = false, _mut40975 = false, _mut40976 = false, _mut40977 = false, _mut40978 = false, _mut40979 = false, _mut40980 = false, _mut40981 = false, _mut40982 = false, _mut40983 = false, _mut40984 = false, _mut40985 = false, _mut40986 = false, _mut40987 = false, _mut40988 = false, _mut40989 = false, _mut40990 = false, _mut40991 = false, _mut40992 = false, _mut40993 = false, _mut40994 = false, _mut40995 = false, _mut40996 = false, _mut40997 = false, _mut40998 = false, _mut40999 = false, _mut41000 = false, _mut41001 = false, _mut41002 = false, _mut41003 = false, _mut41004 = false, _mut41005 = false, _mut41006 = false, _mut41007 = false, _mut41008 = false, _mut41009 = false, _mut41010 = false, _mut41011 = false, _mut41012 = false, _mut41013 = false, _mut41014 = false, _mut41015 = false, _mut41016 = false, _mut41017 = false, _mut41018 = false, _mut41019 = false, _mut41020 = false, _mut41021 = false, _mut41022 = false, _mut41023 = false, _mut41024 = false, _mut41025 = false, _mut41026 = false, _mut41027 = false, _mut41028 = false, _mut41029 = false, _mut41030 = false, _mut41031 = false, _mut41032 = false, _mut41033 = false, _mut41034 = false, _mut41035 = false, _mut41036 = false, _mut41037 = false, _mut41038 = false, _mut41039 = false, _mut41040 = false, _mut41041 = false, _mut41042 = false, _mut41043 = false, _mut41044 = false, _mut41045 = false, _mut41046 = false, _mut41047 = false, _mut41048 = false, _mut41049 = false, _mut41050 = false, _mut41051 = false, _mut41052 = false, _mut41053 = false, _mut41054 = false, _mut41055 = false, _mut41056 = false, _mut41057 = false, _mut41058 = false, _mut41059 = false, _mut41060 = false, _mut41061 = false, _mut41062 = false, _mut41063 = false, _mut41064 = false, _mut41065 = false, _mut41066 = false, _mut41067 = false, _mut41068 = false, _mut41069 = false, _mut41070 = false, _mut41071 = false, _mut41072 = false, _mut41073 = false, _mut41074 = false, _mut41075 = false, _mut41076 = false, _mut41077 = false, _mut41078 = false, _mut41079 = false, _mut41080 = false, _mut41081 = false, _mut41082 = false, _mut41083 = false, _mut41084 = false, _mut41085 = false, _mut41086 = false, _mut41087 = false, _mut41088 = false, _mut41089 = false, _mut41090 = false, _mut41091 = false, _mut41092 = false, _mut41093 = false, _mut41094 = false, _mut41095 = false, _mut41096 = false, _mut41097 = false, _mut41098 = false, _mut41099 = false, _mut41100 = false, _mut41101 = false, _mut41102 = false, _mut41103 = false, _mut41104 = false, _mut41105 = false, _mut41106 = false, _mut41107 = false, _mut41108 = false, _mut41109 = false, _mut41110 = false, _mut41111 = false, _mut41112 = false, _mut41113 = false, _mut41114 = false, _mut41115 = false, _mut41116 = false, _mut41117 = false, _mut41118 = false, _mut41119 = false, _mut41120 = false, _mut41121 = false, _mut41122 = false, _mut41123 = false, _mut41124 = false, _mut41125 = false, _mut41126 = false, _mut41127 = false, _mut41128 = false, _mut41129 = false, _mut41130 = false, _mut41131 = false, _mut41132 = false, _mut41133 = false, _mut41134 = false, _mut41135 = false, _mut41136 = false, _mut41137 = false, _mut41138 = false, _mut41139 = false, _mut41140 = false, _mut41141 = false, _mut41142 = false, _mut41143 = false, _mut41144 = false, _mut41145 = false, _mut41146 = false, _mut41147 = false, _mut41148 = false, _mut41149 = false, _mut41150 = false, _mut41151 = false, _mut41152 = false, _mut41153 = false, _mut41154 = false, _mut41155 = false, _mut41156 = false, _mut41157 = false, _mut41158 = false, _mut41159 = false, _mut41160 = false, _mut41161 = false, _mut41162 = false, _mut41163 = false, _mut41164 = false, _mut41165 = false, _mut41166 = false, _mut41167 = false, _mut41168 = false, _mut41169 = false, _mut41170 = false, _mut41171 = false, _mut41172 = false, _mut41173 = false, _mut41174 = false, _mut41175 = false, _mut41176 = false, _mut41177 = false, _mut41178 = false, _mut41179 = false, _mut41180 = false, _mut41181 = false, _mut41182 = false, _mut41183 = false, _mut41184 = false, _mut41185 = false, _mut41186 = false, _mut41187 = false, _mut41188 = false, _mut41189 = false, _mut41190 = false, _mut41191 = false, _mut41192 = false, _mut41193 = false, _mut41194 = false, _mut41195 = false, _mut41196 = false, _mut41197 = false, _mut41198 = false, _mut41199 = false, _mut41200 = false, _mut41201 = false, _mut41202 = false, _mut41203 = false, _mut41204 = false, _mut41205 = false, _mut41206 = false, _mut41207 = false, _mut41208 = false, _mut41209 = false, _mut41210 = false, _mut41211 = false, _mut41212 = false, _mut41213 = false, _mut41214 = false, _mut41215 = false, _mut41216 = false, _mut41217 = false, _mut41218 = false, _mut41219 = false, _mut41220 = false, _mut41221 = false, _mut41222 = false, _mut41223 = false, _mut41224 = false, _mut41225 = false, _mut41226 = false, _mut41227 = false, _mut41228 = false, _mut41229 = false, _mut41230 = false, _mut41231 = false, _mut41232 = false, _mut41233 = false, _mut41234 = false, _mut41235 = false, _mut41236 = false, _mut41237 = false, _mut41238 = false, _mut41239 = false, _mut41240 = false, _mut41241 = false, _mut41242 = false, _mut41243 = false, _mut41244 = false, _mut41245 = false, _mut41246 = false, _mut41247 = false, _mut41248 = false, _mut41249 = false, _mut41250 = false, _mut41251 = false, _mut41252 = false, _mut41253 = false, _mut41254 = false, _mut41255 = false, _mut41256 = false, _mut41257 = false, _mut41258 = false, _mut41259 = false, _mut41260 = false, _mut41261 = false, _mut41262 = false, _mut41263 = false, _mut41264 = false, _mut41265 = false, _mut41266 = false, _mut41267 = false, _mut41268 = false, _mut41269 = false, _mut41270 = false, _mut41271 = false, _mut41272 = false, _mut41273 = false, _mut41274 = false, _mut41275 = false, _mut41276 = false, _mut41277 = false, _mut41278 = false, _mut41279 = false, _mut41280 = false, _mut41281 = false, _mut41282 = false, _mut41283 = false, _mut41284 = false, _mut41285 = false, _mut41286 = false, _mut41287 = false, _mut41288 = false, _mut41289 = false, _mut41290 = false, _mut41291 = false, _mut41292 = false, _mut41293 = false, _mut41294 = false, _mut41295 = false, _mut41296 = false, _mut41297 = false, _mut41298 = false, _mut41299 = false, _mut41300 = false, _mut41301 = false, _mut41302 = false, _mut41303 = false, _mut41304 = false, _mut41305 = false, _mut41306 = false, _mut41307 = false, _mut41308 = false, _mut41309 = false, _mut41310 = false, _mut41311 = false, _mut41312 = false, _mut41313 = false, _mut41314 = false, _mut41315 = false, _mut41316 = false, _mut41317 = false, _mut41318 = false, _mut41319 = false, _mut41320 = false, _mut41321 = false, _mut41322 = false, _mut41323 = false, _mut41324 = false, _mut41325 = false, _mut41326 = false, _mut41327 = false, _mut41328 = false, _mut41329 = false, _mut41330 = false, _mut41331 = false, _mut41332 = false, _mut41333 = false, _mut41334 = false, _mut41335 = false, _mut41336 = false, _mut41337 = false, _mut41338 = false, _mut41339 = false, _mut41340 = false, _mut41341 = false, _mut41342 = false, _mut41343 = false, _mut41344 = false, _mut41345 = false, _mut41346 = false, _mut41347 = false, _mut41348 = false, _mut41349 = false, _mut41350 = false, _mut41351 = false, _mut41352 = false, _mut41353 = false, _mut41354 = false, _mut41355 = false, _mut41356 = false, _mut41357 = false, _mut41358 = false, _mut41359 = false, _mut41360 = false, _mut41361 = false, _mut41362 = false, _mut41363 = false, _mut41364 = false, _mut41365 = false, _mut41366 = false, _mut41367 = false, _mut41368 = false, _mut41369 = false, _mut41370 = false, _mut41371 = false, _mut41372 = false, _mut41373 = false, _mut41374 = false, _mut41375 = false, _mut41376 = false, _mut41377 = false, _mut41378 = false, _mut41379 = false, _mut41380 = false, _mut41381 = false, _mut41382 = false, _mut41383 = false, _mut41384 = false, _mut41385 = false, _mut41386 = false, _mut41387 = false, _mut41388 = false, _mut41389 = false, _mut41390 = false, _mut41391 = false, _mut41392 = false, _mut41393 = false, _mut41394 = false, _mut41395 = false, _mut41396 = false, _mut41397 = false, _mut41398 = false, _mut41399 = false, _mut41400 = false, _mut41401 = false, _mut41402 = false, _mut41403 = false, _mut41404 = false, _mut41405 = false, _mut41406 = false, _mut41407 = false, _mut41408 = false, _mut41409 = false, _mut41410 = false, _mut41411 = false, _mut41412 = false, _mut41413 = false, _mut41414 = false, _mut41415 = false, _mut41416 = false, _mut41417 = false, _mut41418 = false, _mut41419 = false, _mut41420 = false, _mut41421 = false, _mut41422 = false, _mut41423 = false, _mut41424 = false, _mut41425 = false, _mut41426 = false, _mut41427 = false, _mut41428 = false, _mut41429 = false, _mut41430 = false, _mut41431 = false, _mut41432 = false, _mut41433 = false, _mut41434 = false, _mut41435 = false, _mut41436 = false, _mut41437 = false, _mut41438 = false, _mut41439 = false, _mut41440 = false, _mut41441 = false, _mut41442 = false, _mut41443 = false, _mut41444 = false, _mut41445 = false, _mut41446 = false, _mut41447 = false, _mut41448 = false, _mut41449 = false, _mut41450 = false, _mut41451 = false, _mut41452 = false, _mut41453 = false, _mut41454 = false, _mut41455 = false, _mut41456 = false, _mut41457 = false, _mut41458 = false, _mut41459 = false, _mut41460 = false, _mut41461 = false, _mut41462 = false, _mut41463 = false, _mut41464 = false, _mut41465 = false, _mut41466 = false, _mut41467 = false, _mut41468 = false, _mut41469 = false, _mut41470 = false, _mut41471 = false, _mut41472 = false, _mut41473 = false, _mut41474 = false, _mut41475 = false, _mut41476 = false, _mut41477 = false, _mut41478 = false, _mut41479 = false, _mut41480 = false, _mut41481 = false, _mut41482 = false, _mut41483 = false, _mut41484 = false, _mut41485 = false, _mut41486 = false, _mut41487 = false, _mut41488 = false, _mut41489 = false, _mut41490 = false, _mut41491 = false, _mut41492 = false, _mut41493 = false, _mut41494 = false, _mut41495 = false, _mut41496 = false, _mut41497 = false, _mut41498 = false, _mut41499 = false, _mut41500 = false, _mut41501 = false, _mut41502 = false, _mut41503 = false, _mut41504 = false, _mut41505 = false, _mut41506 = false, _mut41507 = false, _mut41508 = false, _mut41509 = false, _mut41510 = false, _mut41511 = false, _mut41512 = false, _mut41513 = false, _mut41514 = false, _mut41515 = false, _mut41516 = false, _mut41517 = false, _mut41518 = false, _mut41519 = false, _mut41520 = false, _mut41521 = false, _mut41522 = false, _mut41523 = false, _mut41524 = false, _mut41525 = false, _mut41526 = false, _mut41527 = false, _mut41528 = false, _mut41529 = false, _mut41530 = false, _mut41531 = false, _mut41532 = false, _mut41533 = false, _mut41534 = false, _mut41535 = false, _mut41536 = false, _mut41537 = false, _mut41538 = false, _mut41539 = false, _mut41540 = false, _mut41541 = false, _mut41542 = false, _mut41543 = false, _mut41544 = false, _mut41545 = false, _mut41546 = false, _mut41547 = false, _mut41548 = false, _mut41549 = false, _mut41550 = false, _mut41551 = false, _mut41552 = false, _mut41553 = false, _mut41554 = false, _mut41555 = false, _mut41556 = false, _mut41557 = false, _mut41558 = false, _mut41559 = false, _mut41560 = false, _mut41561 = false, _mut41562 = false, _mut41563 = false, _mut41564 = false, _mut41565 = false, _mut41566 = false, _mut41567 = false, _mut41568 = false, _mut41569 = false, _mut41570 = false, _mut41571 = false, _mut41572 = false, _mut41573 = false, _mut41574 = false, _mut41575 = false, _mut41576 = false, _mut41577 = false, _mut41578 = false, _mut41579 = false, _mut41580 = false, _mut41581 = false, _mut41582 = false, _mut41583 = false, _mut41584 = false, _mut41585 = false, _mut41586 = false, _mut41587 = false, _mut41588 = false, _mut41589 = false, _mut41590 = false, _mut41591 = false, _mut41592 = false, _mut41593 = false, _mut41594 = false, _mut41595 = false, _mut41596 = false, _mut41597 = false, _mut41598 = false, _mut41599 = false, _mut41600 = false, _mut41601 = false, _mut41602 = false, _mut41603 = false, _mut41604 = false, _mut41605 = false, _mut41606 = false, _mut41607 = false, _mut41608 = false, _mut41609 = false, _mut41610 = false, _mut41611 = false, _mut41612 = false, _mut41613 = false, _mut41614 = false, _mut41615 = false, _mut41616 = false, _mut41617 = false, _mut41618 = false, _mut41619 = false, _mut41620 = false, _mut41621 = false, _mut41622 = false, _mut41623 = false, _mut41624 = false, _mut41625 = false, _mut41626 = false, _mut41627 = false, _mut41628 = false, _mut41629 = false, _mut41630 = false, _mut41631 = false, _mut41632 = false, _mut41633 = false, _mut41634 = false, _mut41635 = false, _mut41636 = false, _mut41637 = false, _mut41638 = false, _mut41639 = false, _mut41640 = false, _mut41641 = false, _mut41642 = false, _mut41643 = false, _mut41644 = false, _mut41645 = false, _mut41646 = false, _mut41647 = false, _mut41648 = false, _mut41649 = false, _mut41650 = false, _mut41651 = false, _mut41652 = false, _mut41653 = false, _mut41654 = false, _mut41655 = false, _mut41656 = false, _mut41657 = false, _mut41658 = false, _mut41659 = false, _mut41660 = false, _mut41661 = false, _mut41662 = false, _mut41663 = false, _mut41664 = false, _mut41665 = false, _mut41666 = false, _mut41667 = false, _mut41668 = false, _mut41669 = false, _mut41670 = false, _mut41671 = false, _mut41672 = false, _mut41673 = false, _mut41674 = false, _mut41675 = false, _mut41676 = false, _mut41677 = false, _mut41678 = false, _mut41679 = false, _mut41680 = false, _mut41681 = false, _mut41682 = false, _mut41683 = false, _mut41684 = false, _mut41685 = false, _mut41686 = false, _mut41687 = false, _mut41688 = false, _mut41689 = false, _mut41690 = false, _mut41691 = false, _mut41692 = false, _mut41693 = false, _mut41694 = false, _mut41695 = false, _mut41696 = false, _mut41697 = false, _mut41698 = false, _mut41699 = false, _mut41700 = false, _mut41701 = false, _mut41702 = false, _mut41703 = false, _mut41704 = false, _mut41705 = false, _mut41706 = false, _mut41707 = false, _mut41708 = false, _mut41709 = false, _mut41710 = false, _mut41711 = false, _mut41712 = false, _mut41713 = false, _mut41714 = false, _mut41715 = false, _mut41716 = false, _mut41717 = false, _mut41718 = false, _mut41719 = false, _mut41720 = false, _mut41721 = false, _mut41722 = false, _mut41723 = false, _mut41724 = false, _mut41725 = false, _mut41726 = false, _mut41727 = false, _mut41728 = false, _mut41729 = false, _mut41730 = false, _mut41731 = false, _mut41732 = false, _mut41733 = false, _mut41734 = false, _mut41735 = false, _mut41736 = false, _mut41737 = false, _mut41738 = false, _mut41739 = false, _mut41740 = false, _mut41741 = false, _mut41742 = false, _mut41743 = false, _mut41744 = false, _mut41745 = false, _mut41746 = false, _mut41747 = false, _mut41748 = false, _mut41749 = false, _mut41750 = false, _mut41751 = false, _mut41752 = false, _mut41753 = false, _mut41754 = false, _mut41755 = false, _mut41756 = false, _mut41757 = false, _mut41758 = false, _mut41759 = false, _mut41760 = false, _mut41761 = false, _mut41762 = false, _mut41763 = false, _mut41764 = false, _mut41765 = false, _mut41766 = false, _mut41767 = false, _mut41768 = false, _mut41769 = false, _mut41770 = false, _mut41771 = false, _mut41772 = false, _mut41773 = false, _mut41774 = false, _mut41775 = false, _mut41776 = false, _mut41777 = false, _mut41778 = false, _mut41779 = false, _mut41780 = false, _mut41781 = false, _mut41782 = false, _mut41783 = false, _mut41784 = false, _mut41785 = false, _mut41786 = false, _mut41787 = false, _mut41788 = false, _mut41789 = false, _mut41790 = false, _mut41791 = false, _mut41792 = false, _mut41793 = false, _mut41794 = false, _mut41795 = false, _mut41796 = false, _mut41797 = false, _mut41798 = false, _mut41799 = false, _mut41800 = false, _mut41801 = false, _mut41802 = false, _mut41803 = false, _mut41804 = false, _mut41805 = false, _mut41806 = false, _mut41807 = false, _mut41808 = false, _mut41809 = false, _mut41810 = false, _mut41811 = false, _mut41812 = false, _mut41813 = false, _mut41814 = false, _mut41815 = false, _mut41816 = false, _mut41817 = false, _mut41818 = false, _mut41819 = false, _mut41820 = false, _mut41821 = false, _mut41822 = false, _mut41823 = false, _mut41824 = false, _mut41825 = false, _mut41826 = false, _mut41827 = false, _mut41828 = false, _mut41829 = false, _mut41830 = false, _mut41831 = false, _mut41832 = false, _mut41833 = false, _mut41834 = false, _mut41835 = false, _mut41836 = false, _mut41837 = false, _mut41838 = false, _mut41839 = false, _mut41840 = false, _mut41841 = false, _mut41842 = false, _mut41843 = false, _mut41844 = false, _mut41845 = false, _mut41846 = false, _mut41847 = false, _mut41848 = false, _mut41849 = false, _mut41850 = false, _mut41851 = false, _mut41852 = false, _mut41853 = false, _mut41854 = false, _mut41855 = false, _mut41856 = false, _mut41857 = false, _mut41858 = false, _mut41859 = false, _mut41860 = false, _mut41861 = false, _mut41862 = false, _mut41863 = false, _mut41864 = false, _mut41865 = false, _mut41866 = false, _mut41867 = false, _mut41868 = false, _mut41869 = false, _mut41870 = false, _mut41871 = false, _mut41872 = false, _mut41873 = false, _mut41874 = false, _mut41875 = false, _mut41876 = false, _mut41877 = false, _mut41878 = false, _mut41879 = false, _mut41880 = false, _mut41881 = false, _mut41882 = false, _mut41883 = false, _mut41884 = false, _mut41885 = false, _mut41886 = false, _mut41887 = false, _mut41888 = false, _mut41889 = false, _mut41890 = false, _mut41891 = false, _mut41892 = false, _mut41893 = false, _mut41894 = false, _mut41895 = false, _mut41896 = false, _mut41897 = false, _mut41898 = false, _mut41899 = false, _mut41900 = false, _mut41901 = false, _mut41902 = false, _mut41903 = false, _mut41904 = false, _mut41905 = false, _mut41906 = false, _mut41907 = false, _mut41908 = false, _mut41909 = false, _mut41910 = false, _mut41911 = false, _mut41912 = false, _mut41913 = false, _mut41914 = false, _mut41915 = false, _mut41916 = false, _mut41917 = false, _mut41918 = false, _mut41919 = false, _mut41920 = false, _mut41921 = false, _mut41922 = false, _mut41923 = false, _mut41924 = false, _mut41925 = false, _mut41926 = false, _mut41927 = false, _mut41928 = false, _mut41929 = false, _mut41930 = false, _mut41931 = false, _mut41932 = false, _mut41933 = false, _mut41934 = false, _mut41935 = false, _mut41936 = false, _mut41937 = false, _mut41938 = false, _mut41939 = false, _mut41940 = false, _mut41941 = false, _mut41942 = false, _mut41943 = false, _mut41944 = false, _mut41945 = false, _mut41946 = false, _mut41947 = false, _mut41948 = false, _mut41949 = false, _mut41950 = false, _mut41951 = false, _mut41952 = false, _mut41953 = false, _mut41954 = false, _mut41955 = false, _mut41956 = false, _mut41957 = false, _mut41958 = false, _mut41959 = false, _mut41960 = false, _mut41961 = false, _mut41962 = false, _mut41963 = false, _mut41964 = false, _mut41965 = false, _mut41966 = false, _mut41967 = false, _mut41968 = false, _mut41969 = false, _mut41970 = false, _mut41971 = false, _mut41972 = false, _mut41973 = false, _mut41974 = false, _mut41975 = false, _mut41976 = false, _mut41977 = false, _mut41978 = false, _mut41979 = false, _mut41980 = false, _mut41981 = false, _mut41982 = false, _mut41983 = false, _mut41984 = false, _mut41985 = false, _mut41986 = false, _mut41987 = false, _mut41988 = false, _mut41989 = false, _mut41990 = false, _mut41991 = false, _mut41992 = false, _mut41993 = false, _mut41994 = false, _mut41995 = false, _mut41996 = false, _mut41997 = false, _mut41998 = false, _mut41999 = false, _mut42000 = false, _mut42001 = false, _mut42002 = false, _mut42003 = false, _mut42004 = false, _mut42005 = false, _mut42006 = false, _mut42007 = false, _mut42008 = false, _mut42009 = false, _mut42010 = false, _mut42011 = false, _mut42012 = false, _mut42013 = false, _mut42014 = false, _mut42015 = false, _mut42016 = false, _mut42017 = false, _mut42018 = false, _mut42019 = false, _mut42020 = false, _mut42021 = false, _mut42022 = false, _mut42023 = false, _mut42024 = false, _mut42025 = false, _mut42026 = false, _mut42027 = false, _mut42028 = false, _mut42029 = false, _mut42030 = false, _mut42031 = false, _mut42032 = false, _mut42033 = false, _mut42034 = false, _mut42035 = false, _mut42036 = false, _mut42037 = false, _mut42038 = false, _mut42039 = false, _mut42040 = false, _mut42041 = false, _mut42042 = false, _mut42043 = false, _mut42044 = false, _mut42045 = false, _mut42046 = false, _mut42047 = false, _mut42048 = false, _mut42049 = false, _mut42050 = false, _mut42051 = false, _mut42052 = false, _mut42053 = false, _mut42054 = false, _mut42055 = false, _mut42056 = false, _mut42057 = false, _mut42058 = false, _mut42059 = false, _mut42060 = false, _mut42061 = false, _mut42062 = false, _mut42063 = false, _mut42064 = false, _mut42065 = false, _mut42066 = false, _mut42067 = false, _mut42068 = false, _mut42069 = false, _mut42070 = false, _mut42071 = false, _mut42072 = false, _mut42073 = false, _mut42074 = false, _mut42075 = false, _mut42076 = false, _mut42077 = false, _mut42078 = false, _mut42079 = false, _mut42080 = false, _mut42081 = false, _mut42082 = false, _mut42083 = false, _mut42084 = false, _mut42085 = false, _mut42086 = false, _mut42087 = false, _mut42088 = false, _mut42089 = false, _mut42090 = false, _mut42091 = false, _mut42092 = false, _mut42093 = false, _mut42094 = false, _mut42095 = false, _mut42096 = false, _mut42097 = false, _mut42098 = false, _mut42099 = false, _mut42100 = false, _mut42101 = false, _mut42102 = false, _mut42103 = false, _mut42104 = false, _mut42105 = false, _mut42106 = false, _mut42107 = false, _mut42108 = false, _mut42109 = false, _mut42110 = false, _mut42111 = false, _mut42112 = false, _mut42113 = false, _mut42114 = false, _mut42115 = false, _mut42116 = false, _mut42117 = false, _mut42118 = false, _mut42119 = false, _mut42120 = false, _mut42121 = false, _mut42122 = false, _mut42123 = false, _mut42124 = false, _mut42125 = false, _mut42126 = false, _mut42127 = false, _mut42128 = false, _mut42129 = false, _mut42130 = false, _mut42131 = false, _mut42132 = false, _mut42133 = false, _mut42134 = false, _mut42135 = false, _mut42136 = false, _mut42137 = false, _mut42138 = false, _mut42139 = false, _mut42140 = false, _mut42141 = false, _mut42142 = false, _mut42143 = false, _mut42144 = false, _mut42145 = false, _mut42146 = false, _mut42147 = false, _mut42148 = false, _mut42149 = false, _mut42150 = false, _mut42151 = false, _mut42152 = false, _mut42153 = false, _mut42154 = false, _mut42155 = false, _mut42156 = false, _mut42157 = false, _mut42158 = false, _mut42159 = false, _mut42160 = false, _mut42161 = false, _mut42162 = false, _mut42163 = false, _mut42164 = false, _mut42165 = false, _mut42166 = false, _mut42167 = false, _mut42168 = false, _mut42169 = false, _mut42170 = false, _mut42171 = false, _mut42172 = false, _mut42173 = false, _mut42174 = false, _mut42175 = false, _mut42176 = false, _mut42177 = false, _mut42178 = false, _mut42179 = false, _mut42180 = false, _mut42181 = false, _mut42182 = false, _mut42183 = false, _mut42184 = false, _mut42185 = false, _mut42186 = false, _mut42187 = false, _mut42188 = false, _mut42189 = false, _mut42190 = false, _mut42191 = false, _mut42192 = false, _mut42193 = false, _mut42194 = false, _mut42195 = false, _mut42196 = false, _mut42197 = false, _mut42198 = false, _mut42199 = false, _mut42200 = false, _mut42201 = false, _mut42202 = false, _mut42203 = false, _mut42204 = false, _mut42205 = false, _mut42206 = false, _mut42207 = false, _mut42208 = false, _mut42209 = false, _mut42210 = false, _mut42211 = false, _mut42212 = false, _mut42213 = false, _mut42214 = false, _mut42215 = false, _mut42216 = false, _mut42217 = false, _mut42218 = false, _mut42219 = false, _mut42220 = false, _mut42221 = false, _mut42222 = false, _mut42223 = false, _mut42224 = false, _mut42225 = false, _mut42226 = false, _mut42227 = false, _mut42228 = false, _mut42229 = false, _mut42230 = false, _mut42231 = false, _mut42232 = false, _mut42233 = false, _mut42234 = false, _mut42235 = false, _mut42236 = false, _mut42237 = false, _mut42238 = false, _mut42239 = false, _mut42240 = false, _mut42241 = false, _mut42242 = false, _mut42243 = false, _mut42244 = false, _mut42245 = false, _mut42246 = false, _mut42247 = false, _mut42248 = false, _mut42249 = false, _mut42250 = false, _mut42251 = false, _mut42252 = false, _mut42253 = false, _mut42254 = false, _mut42255 = false, _mut42256 = false, _mut42257 = false, _mut42258 = false, _mut42259 = false, _mut42260 = false, _mut42261 = false, _mut42262 = false, _mut42263 = false, _mut42264 = false, _mut42265 = false, _mut42266 = false, _mut42267 = false, _mut42268 = false, _mut42269 = false, _mut42270 = false, _mut42271 = false, _mut42272 = false, _mut42273 = false, _mut42274 = false, _mut42275 = false, _mut42276 = false, _mut42277 = false, _mut42278 = false, _mut42279 = false, _mut42280 = false, _mut42281 = false, _mut42282 = false, _mut42283 = false, _mut42284 = false, _mut42285 = false, _mut42286 = false, _mut42287 = false, _mut42288 = false, _mut42289 = false, _mut42290 = false, _mut42291 = false, _mut42292 = false, _mut42293 = false, _mut42294 = false, _mut42295 = false, _mut42296 = false, _mut42297 = false, _mut42298 = false, _mut42299 = false, _mut42300 = false, _mut42301 = false, _mut42302 = false, _mut42303 = false, _mut42304 = false, _mut42305 = false, _mut42306 = false, _mut42307 = false, _mut42308 = false, _mut42309 = false, _mut42310 = false, _mut42311 = false, _mut42312 = false, _mut42313 = false, _mut42314 = false, _mut42315 = false, _mut42316 = false, _mut42317 = false, _mut42318 = false, _mut42319 = false, _mut42320 = false, _mut42321 = false, _mut42322 = false, _mut42323 = false, _mut42324 = false, _mut42325 = false, _mut42326 = false, _mut42327 = false, _mut42328 = false, _mut42329 = false, _mut42330 = false, _mut42331 = false, _mut42332 = false, _mut42333 = false, _mut42334 = false, _mut42335 = false, _mut42336 = false, _mut42337 = false, _mut42338 = false, _mut42339 = false, _mut42340 = false, _mut42341 = false, _mut42342 = false, _mut42343 = false, _mut42344 = false, _mut42345 = false, _mut42346 = false, _mut42347 = false, _mut42348 = false;

    /**
     * Private constructor.
     */
    private MathArrays() {
    }

    /**
     * Real-valued function that operate on an array or a part of it.
     * @since 3.1
     */
    public interface Function {

        /**
         * Operates on an entire array.
         *
         * @param array Array to operate on.
         * @return the result of the operation.
         */
        double evaluate(double[] array);

        /**
         * @param array Array to operate on.
         * @param startIndex Index of the first element to take into account.
         * @param numElements Number of elements to take into account.
         * @return the result of the operation.
         */
        double evaluate(double[] array, int startIndex, int numElements);
    }

    /**
     * Create a copy of an array scaled by a value.
     *
     * @param arr Array to scale.
     * @param val Scalar.
     * @return scaled copy of array with each entry multiplied by val.
     * @since 3.2
     */
    public static double[] scale(double val, final double[] arr) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.scale_89");
        double[] newArr = new double[arr.length];
        for (int i = 0; ROR_less(i, arr.length, "org.apache.commons.math3.util.MathArrays.scale_89", _mut40912, _mut40913, _mut40914, _mut40915, _mut40916); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.scale_89");
            newArr[i] = AOR_multiply(arr[i], val, "org.apache.commons.math3.util.MathArrays.scale_89", _mut40908, _mut40909, _mut40910, _mut40911);
        }
        return newArr;
    }

    /**
     * <p>Multiply each element of an array by a value.</p>
     *
     * <p>The array is modified in place (no copy is created).</p>
     *
     * @param arr Array to scale
     * @param val Scalar
     * @since 3.2
     */
    public static void scaleInPlace(double val, final double[] arr) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.scaleInPlace_106");
        for (int i = 0; ROR_less(i, arr.length, "org.apache.commons.math3.util.MathArrays.scaleInPlace_106", _mut40917, _mut40918, _mut40919, _mut40920, _mut40921); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.scaleInPlace_106");
            arr[i] *= val;
        }
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * addition of the arguments.
     *
     * @param a First term of the addition.
     * @param b Second term of the addition.
     * @return a new array {@code r} where {@code r[i] = a[i] + b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeAdd(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeAdd_122");
        checkEqualLength(a, b);
        final double[] result = a.clone();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.MathArrays.ebeAdd_122", _mut40922, _mut40923, _mut40924, _mut40925, _mut40926); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeAdd_122");
            result[i] += b[i];
        }
        return result;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * subtraction of the second argument from the first.
     *
     * @param a First term.
     * @param b Element to be subtracted.
     * @return a new array {@code r} where {@code r[i] = a[i] - b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeSubtract(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeSubtract_142");
        checkEqualLength(a, b);
        final double[] result = a.clone();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.MathArrays.ebeSubtract_142", _mut40927, _mut40928, _mut40929, _mut40930, _mut40931); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeSubtract_142");
            result[i] -= b[i];
        }
        return result;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * multiplication of the arguments.
     *
     * @param a First factor of the multiplication.
     * @param b Second factor of the multiplication.
     * @return a new array {@code r} where {@code r[i] = a[i] * b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeMultiply(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeMultiply_162");
        checkEqualLength(a, b);
        final double[] result = a.clone();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.MathArrays.ebeMultiply_162", _mut40932, _mut40933, _mut40934, _mut40935, _mut40936); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeMultiply_162");
            result[i] *= b[i];
        }
        return result;
    }

    /**
     * Creates an array whose contents will be the element-by-element
     * division of the first argument by the second.
     *
     * @param a Numerator of the division.
     * @param b Denominator of the division.
     * @return a new array {@code r} where {@code r[i] = a[i] / b[i]}.
     * @throws DimensionMismatchException if the array lengths differ.
     * @since 3.1
     */
    public static double[] ebeDivide(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeDivide_182");
        checkEqualLength(a, b);
        final double[] result = a.clone();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.MathArrays.ebeDivide_182", _mut40937, _mut40938, _mut40939, _mut40940, _mut40941); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.ebeDivide_182");
            result[i] /= b[i];
        }
        return result;
    }

    /**
     * Calculates the L<sub>1</sub> (sum of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>1</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance1(double[] p1, double[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance1_201");
        checkEqualLength(p1, p2);
        double sum = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distance1_201", _mut40946, _mut40947, _mut40948, _mut40949, _mut40950); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance1_201");
            sum += FastMath.abs(AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distance1_201", _mut40942, _mut40943, _mut40944, _mut40945));
        }
        return sum;
    }

    /**
     * Calculates the L<sub>1</sub> (sum of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>1</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static int distance1(int[] p1, int[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance1_219");
        checkEqualLength(p1, p2);
        int sum = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distance1_219", _mut40955, _mut40956, _mut40957, _mut40958, _mut40959); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance1_219");
            sum += FastMath.abs(AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distance1_219", _mut40951, _mut40952, _mut40953, _mut40954));
        }
        return sum;
    }

    /**
     * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>2</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance(double[] p1, double[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance_237");
        checkEqualLength(p1, p2);
        double sum = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distance_237", _mut40968, _mut40969, _mut40970, _mut40971, _mut40972); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance_237");
            final double dp = AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distance_237", _mut40960, _mut40961, _mut40962, _mut40963);
            sum += AOR_multiply(dp, dp, "org.apache.commons.math3.util.MathArrays.distance_237", _mut40964, _mut40965, _mut40966, _mut40967);
        }
        return FastMath.sqrt(sum);
    }

    /**
     * Calculates the cosine of the angle between two vectors.
     *
     * @param v1 Cartesian coordinates of the first vector.
     * @param v2 Cartesian coordinates of the second vector.
     * @return the cosine of the angle between the vectors.
     * @since 3.6
     */
    public static double cosAngle(double[] v1, double[] v2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.cosAngle_256");
        return AOR_divide(linearCombination(v1, v2), (AOR_multiply(safeNorm(v1), safeNorm(v2), "org.apache.commons.math3.util.MathArrays.cosAngle_256", _mut40973, _mut40974, _mut40975, _mut40976)), "org.apache.commons.math3.util.MathArrays.cosAngle_256", _mut40977, _mut40978, _mut40979, _mut40980);
    }

    /**
     * Calculates the L<sub>2</sub> (Euclidean) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>2</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distance(int[] p1, int[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance_268");
        checkEqualLength(p1, p2);
        double sum = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distance_268", _mut40989, _mut40990, _mut40991, _mut40992, _mut40993); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distance_268");
            final double dp = AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distance_268", _mut40981, _mut40982, _mut40983, _mut40984);
            sum += AOR_multiply(dp, dp, "org.apache.commons.math3.util.MathArrays.distance_268", _mut40985, _mut40986, _mut40987, _mut40988);
        }
        return FastMath.sqrt(sum);
    }

    /**
     * Calculates the L<sub>&infin;</sub> (max of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>&infin;</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static double distanceInf(double[] p1, double[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distanceInf_287");
        checkEqualLength(p1, p2);
        double max = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distanceInf_287", _mut40998, _mut40999, _mut41000, _mut41001, _mut41002); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distanceInf_287");
            max = FastMath.max(max, FastMath.abs(AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distanceInf_287", _mut40994, _mut40995, _mut40996, _mut40997)));
        }
        return max;
    }

    /**
     * Calculates the L<sub>&infin;</sub> (max of abs) distance between two points.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the L<sub>&infin;</sub> distance between the two points
     * @throws DimensionMismatchException if the array lengths differ.
     */
    public static int distanceInf(int[] p1, int[] p2) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distanceInf_305");
        checkEqualLength(p1, p2);
        int max = 0;
        for (int i = 0; ROR_less(i, p1.length, "org.apache.commons.math3.util.MathArrays.distanceInf_305", _mut41007, _mut41008, _mut41009, _mut41010, _mut41011); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.distanceInf_305");
            max = FastMath.max(max, FastMath.abs(AOR_minus(p1[i], p2[i], "org.apache.commons.math3.util.MathArrays.distanceInf_305", _mut41003, _mut41004, _mut41005, _mut41006)));
        }
        return max;
    }

    /**
     * Specification of ordering direction.
     */
    public enum OrderDirection {

        /**
         * Constant for increasing direction.
         */
        INCREASING,
        /**
         * Constant for decreasing direction.
         */
        DECREASING
    }

    /**
     * Check that an array is monotonically increasing or decreasing.
     *
     * @param <T> the type of the elements in the specified array
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @return {@code true} if sorted, {@code false} otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isMonotonic(T[] val, OrderDirection dir, boolean strict) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.isMonotonic_334");
        T previous = val[0];
        final int max = val.length;
        for (int i = 1; ROR_less(i, max, "org.apache.commons.math3.util.MathArrays.isMonotonic_334", _mut41032, _mut41033, _mut41034, _mut41035, _mut41036); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.isMonotonic_334");
            final int comp;
            switch(dir) {
                case INCREASING:
                    comp = previous.compareTo(val[i]);
                    if (strict) {
                        if (ROR_greater_equals(comp, 0, "org.apache.commons.math3.util.MathArrays.isMonotonic_334", _mut41017, _mut41018, _mut41019, _mut41020, _mut41021)) {
                            return false;
                        }
                    } else {
                        if (ROR_greater(comp, 0, "org.apache.commons.math3.util.MathArrays.isMonotonic_334", _mut41012, _mut41013, _mut41014, _mut41015, _mut41016)) {
                            return false;
                        }
                    }
                    break;
                case DECREASING:
                    comp = val[i].compareTo(previous);
                    if (strict) {
                        if (ROR_greater_equals(comp, 0, "org.apache.commons.math3.util.MathArrays.isMonotonic_334", _mut41027, _mut41028, _mut41029, _mut41030, _mut41031)) {
                            return false;
                        }
                    } else {
                        if (ROR_greater(comp, 0, "org.apache.commons.math3.util.MathArrays.isMonotonic_334", _mut41022, _mut41023, _mut41024, _mut41025, _mut41026)) {
                            return false;
                        }
                    }
                    break;
                default:
                    // Should never happen.
                    throw new MathInternalError();
            }
            previous = val[i];
        }
        return true;
    }

    /**
     * Check that an array is monotonically increasing or decreasing.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @return {@code true} if sorted, {@code false} otherwise.
     */
    public static boolean isMonotonic(double[] val, OrderDirection dir, boolean strict) {
        return checkOrder(val, dir, strict, false);
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the arrays have the same length.
     * @throws DimensionMismatchException if the lengths differ and
     * {@code abort} is {@code true}.
     * @since 3.6
     */
    public static boolean checkEqualLength(double[] a, double[] b, boolean abort) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkEqualLength_399");
        if (ROR_equals(a.length, b.length, "org.apache.commons.math3.util.MathArrays.checkEqualLength_399", _mut41037, _mut41038, _mut41039, _mut41040, _mut41041)) {
            return true;
        } else {
            if (abort) {
                throw new DimensionMismatchException(a.length, b.length);
            }
            return false;
        }
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @throws DimensionMismatchException if the lengths differ.
     * @since 3.6
     */
    public static void checkEqualLength(double[] a, double[] b) {
        checkEqualLength(a, b, true);
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the arrays have the same length.
     * @throws DimensionMismatchException if the lengths differ and
     * {@code abort} is {@code true}.
     * @since 3.6
     */
    public static boolean checkEqualLength(int[] a, int[] b, boolean abort) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkEqualLength_437");
        if (ROR_equals(a.length, b.length, "org.apache.commons.math3.util.MathArrays.checkEqualLength_437", _mut41042, _mut41043, _mut41044, _mut41045, _mut41046)) {
            return true;
        } else {
            if (abort) {
                throw new DimensionMismatchException(a.length, b.length);
            }
            return false;
        }
    }

    /**
     * Check that both arrays have the same length.
     *
     * @param a Array.
     * @param b Array.
     * @throws DimensionMismatchException if the lengths differ.
     * @since 3.6
     */
    public static void checkEqualLength(int[] a, int[] b) {
        checkEqualLength(a, b, true);
    }

    /**
     * Check that the given array is sorted.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @param abort Whether to throw an exception if the check fails.
     * @return {@code true} if the array is sorted.
     * @throws NonMonotonicSequenceException if the array is not sorted
     * and {@code abort} is {@code true}.
     */
    public static boolean checkOrder(double[] val, OrderDirection dir, boolean strict, boolean abort) throws NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkOrder_474");
        double previous = val[0];
        final int max = val.length;
        int index;
        ITEM: for (index = 1; ROR_less(index, max, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41067, _mut41068, _mut41069, _mut41070, _mut41071); index++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkOrder_474");
            switch(dir) {
                case INCREASING:
                    if (strict) {
                        if (ROR_less_equals(val[index], previous, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41052, _mut41053, _mut41054, _mut41055, _mut41056)) {
                            break ITEM;
                        }
                    } else {
                        if (ROR_less(val[index], previous, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41047, _mut41048, _mut41049, _mut41050, _mut41051)) {
                            break ITEM;
                        }
                    }
                    break;
                case DECREASING:
                    if (strict) {
                        if (ROR_greater_equals(val[index], previous, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41062, _mut41063, _mut41064, _mut41065, _mut41066)) {
                            break ITEM;
                        }
                    } else {
                        if (ROR_greater(val[index], previous, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41057, _mut41058, _mut41059, _mut41060, _mut41061)) {
                            break ITEM;
                        }
                    }
                    break;
                default:
                    // Should never happen.
                    throw new MathInternalError();
            }
            previous = val[index];
        }
        if (ROR_equals(index, max, "org.apache.commons.math3.util.MathArrays.checkOrder_474", _mut41072, _mut41073, _mut41074, _mut41075, _mut41076)) {
            // Loop completed.
            return true;
        }
        // Loop early exit means wrong ordering.
        if (abort) {
            throw new NonMonotonicSequenceException(val[index], previous, index, dir, strict);
        } else {
            return false;
        }
    }

    /**
     * Check that the given array is sorted.
     *
     * @param val Values.
     * @param dir Ordering direction.
     * @param strict Whether the order should be strict.
     * @throws NonMonotonicSequenceException if the array is not sorted.
     * @since 2.2
     */
    public static void checkOrder(double[] val, OrderDirection dir, boolean strict) throws NonMonotonicSequenceException {
        checkOrder(val, dir, strict, true);
    }

    /**
     * Check that the given array is sorted in strictly increasing order.
     *
     * @param val Values.
     * @throws NonMonotonicSequenceException if the array is not sorted.
     * @since 2.2
     */
    public static void checkOrder(double[] val) throws NonMonotonicSequenceException {
        checkOrder(val, OrderDirection.INCREASING, true);
    }

    /**
     * Throws DimensionMismatchException if the input array is not rectangular.
     *
     * @param in array to be tested
     * @throws NullArgumentException if input array is null
     * @throws DimensionMismatchException if input array is not rectangular
     * @since 3.1
     */
    public static void checkRectangular(final long[][] in) throws NullArgumentException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkRectangular_560");
        MathUtils.checkNotNull(in);
        for (int i = 1; ROR_less(i, in.length, "org.apache.commons.math3.util.MathArrays.checkRectangular_560", _mut41082, _mut41083, _mut41084, _mut41085, _mut41086); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkRectangular_560");
            if (ROR_not_equals(in[i].length, in[0].length, "org.apache.commons.math3.util.MathArrays.checkRectangular_560", _mut41077, _mut41078, _mut41079, _mut41080, _mut41081)) {
                throw new DimensionMismatchException(LocalizedFormats.DIFFERENT_ROWS_LENGTHS, in[i].length, in[0].length);
            }
        }
    }

    /**
     * Check that all entries of the input array are strictly positive.
     *
     * @param in Array to be tested
     * @throws NotStrictlyPositiveException if any entries of the array are not
     * strictly positive.
     * @since 3.1
     */
    public static void checkPositive(final double[] in) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkPositive_580");
        for (int i = 0; ROR_less(i, in.length, "org.apache.commons.math3.util.MathArrays.checkPositive_580", _mut41092, _mut41093, _mut41094, _mut41095, _mut41096); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkPositive_580");
            if (ROR_less_equals(in[i], 0, "org.apache.commons.math3.util.MathArrays.checkPositive_580", _mut41087, _mut41088, _mut41089, _mut41090, _mut41091)) {
                throw new NotStrictlyPositiveException(in[i]);
            }
        }
    }

    /**
     * Check that no entry of the input array is {@code NaN}.
     *
     * @param in Array to be tested.
     * @throws NotANumberException if an entry is {@code NaN}.
     * @since 3.4
     */
    public static void checkNotNaN(final double[] in) throws NotANumberException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNotNaN_596");
        for (int i = 0; ROR_less(i, in.length, "org.apache.commons.math3.util.MathArrays.checkNotNaN_596", _mut41097, _mut41098, _mut41099, _mut41100, _mut41101); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNotNaN_596");
            if (Double.isNaN(in[i])) {
                throw new NotANumberException();
            }
        }
    }

    /**
     * Check that all entries of the input array are >= 0.
     *
     * @param in Array to be tested
     * @throws NotPositiveException if any array entries are less than 0.
     * @since 3.1
     */
    public static void checkNonNegative(final long[] in) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNonNegative_612");
        for (int i = 0; ROR_less(i, in.length, "org.apache.commons.math3.util.MathArrays.checkNonNegative_612", _mut41107, _mut41108, _mut41109, _mut41110, _mut41111); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNonNegative_612");
            if (ROR_less(in[i], 0, "org.apache.commons.math3.util.MathArrays.checkNonNegative_612", _mut41102, _mut41103, _mut41104, _mut41105, _mut41106)) {
                throw new NotPositiveException(in[i]);
            }
        }
    }

    /**
     * Check all entries of the input array are >= 0.
     *
     * @param in Array to be tested
     * @throws NotPositiveException if any array entries are less than 0.
     * @since 3.1
     */
    public static void checkNonNegative(final long[][] in) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNonNegative_628");
        for (int i = 0; ROR_less(i, in.length, "org.apache.commons.math3.util.MathArrays.checkNonNegative_628", _mut41122, _mut41123, _mut41124, _mut41125, _mut41126); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNonNegative_628");
            for (int j = 0; ROR_less(j, in[i].length, "org.apache.commons.math3.util.MathArrays.checkNonNegative_628", _mut41117, _mut41118, _mut41119, _mut41120, _mut41121); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.checkNonNegative_628");
                if (ROR_less(in[i][j], 0, "org.apache.commons.math3.util.MathArrays.checkNonNegative_628", _mut41112, _mut41113, _mut41114, _mut41115, _mut41116)) {
                    throw new NotPositiveException(in[i][j]);
                }
            }
        }
    }

    /**
     * Returns the Cartesian norm (2-norm), handling both overflow and underflow.
     * Translation of the minpack enorm subroutine.
     *
     * The redistribution policy for MINPACK is available
     * <a href="http://www.netlib.org/minpack/disclaimer">here</a>, for
     * convenience, it is reproduced below.</p>
     *
     * <table border="0" width="80%" cellpadding="10" align="center" bgcolor="#E0E0E0">
     * <tr><td>
     *    Minpack Copyright Notice (1999) University of Chicago.
     *    All rights reserved
     * </td></tr>
     * <tr><td>
     * Redistribution and use in source and binary forms, with or without
     * modification, are permitted provided that the following conditions
     * are met:
     * <ol>
     *  <li>Redistributions of source code must retain the above copyright
     *      notice, this list of conditions and the following disclaimer.</li>
     * <li>Redistributions in binary form must reproduce the above
     *     copyright notice, this list of conditions and the following
     *     disclaimer in the documentation and/or other materials provided
     *     with the distribution.</li>
     * <li>The end-user documentation included with the redistribution, if any,
     *     must include the following acknowledgment:
     *     {@code This product includes software developed by the University of
     *           Chicago, as Operator of Argonne National Laboratory.}
     *     Alternately, this acknowledgment may appear in the software itself,
     *     if and wherever such third-party acknowledgments normally appear.</li>
     * <li><strong>WARRANTY DISCLAIMER. THE SOFTWARE IS SUPPLIED "AS IS"
     *     WITHOUT WARRANTY OF ANY KIND. THE COPYRIGHT HOLDER, THE
     *     UNITED STATES, THE UNITED STATES DEPARTMENT OF ENERGY, AND
     *     THEIR EMPLOYEES: (1) DISCLAIM ANY WARRANTIES, EXPRESS OR
     *     IMPLIED, INCLUDING BUT NOT LIMITED TO ANY IMPLIED WARRANTIES
     *     OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE
     *     OR NON-INFRINGEMENT, (2) DO NOT ASSUME ANY LEGAL LIABILITY
     *     OR RESPONSIBILITY FOR THE ACCURACY, COMPLETENESS, OR
     *     USEFULNESS OF THE SOFTWARE, (3) DO NOT REPRESENT THAT USE OF
     *     THE SOFTWARE WOULD NOT INFRINGE PRIVATELY OWNED RIGHTS, (4)
     *     DO NOT WARRANT THAT THE SOFTWARE WILL FUNCTION
     *     UNINTERRUPTED, THAT IT IS ERROR-FREE OR THAT ANY ERRORS WILL
     *     BE CORRECTED.</strong></li>
     * <li><strong>LIMITATION OF LIABILITY. IN NO EVENT WILL THE COPYRIGHT
     *     HOLDER, THE UNITED STATES, THE UNITED STATES DEPARTMENT OF
     *     ENERGY, OR THEIR EMPLOYEES: BE LIABLE FOR ANY INDIRECT,
     *     INCIDENTAL, CONSEQUENTIAL, SPECIAL OR PUNITIVE DAMAGES OF
     *     ANY KIND OR NATURE, INCLUDING BUT NOT LIMITED TO LOSS OF
     *     PROFITS OR LOSS OF DATA, FOR ANY REASON WHATSOEVER, WHETHER
     *     SUCH LIABILITY IS ASSERTED ON THE BASIS OF CONTRACT, TORT
     *     (INCLUDING NEGLIGENCE OR STRICT LIABILITY), OR OTHERWISE,
     *     EVEN IF ANY OF SAID PARTIES HAS BEEN WARNED OF THE
     *     POSSIBILITY OF SUCH LOSS OR DAMAGES.</strong></li>
     * <ol></td></tr>
     * </table>
     *
     * @param v Vector of doubles.
     * @return the 2-norm of the vector.
     * @since 2.2
     */
    public static double safeNorm(double[] v) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.safeNorm_699");
        double rdwarf = 3.834e-20;
        double rgiant = 1.304e+19;
        double s1 = 0;
        double s2 = 0;
        double s3 = 0;
        double x1max = 0;
        double x3max = 0;
        double floatn = v.length;
        double agiant = AOR_divide(rgiant, floatn, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41127, _mut41128, _mut41129, _mut41130);
        for (int i = 0; ROR_less(i, v.length, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41214, _mut41215, _mut41216, _mut41217, _mut41218); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.safeNorm_699");
            double xabs = FastMath.abs(v[i]);
            if ((_mut41141 ? (ROR_less(xabs, rdwarf, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41131, _mut41132, _mut41133, _mut41134, _mut41135) && ROR_greater(xabs, agiant, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41136, _mut41137, _mut41138, _mut41139, _mut41140)) : (ROR_less(xabs, rdwarf, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41131, _mut41132, _mut41133, _mut41134, _mut41135) || ROR_greater(xabs, agiant, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41136, _mut41137, _mut41138, _mut41139, _mut41140)))) {
                if (ROR_greater(xabs, rdwarf, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41146, _mut41147, _mut41148, _mut41149, _mut41150)) {
                    if (ROR_greater(xabs, x1max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41185, _mut41186, _mut41187, _mut41188, _mut41189)) {
                        double r = AOR_divide(x1max, xabs, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41198, _mut41199, _mut41200, _mut41201);
                        s1 = AOR_plus(1, AOR_multiply(AOR_multiply(s1, r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41202, _mut41203, _mut41204, _mut41205), r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41206, _mut41207, _mut41208, _mut41209), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41210, _mut41211, _mut41212, _mut41213);
                        x1max = xabs;
                    } else {
                        double r = AOR_divide(xabs, x1max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41190, _mut41191, _mut41192, _mut41193);
                        s1 += AOR_multiply(r, r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41194, _mut41195, _mut41196, _mut41197);
                    }
                } else {
                    if (ROR_greater(xabs, x3max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41151, _mut41152, _mut41153, _mut41154, _mut41155)) {
                        double r = AOR_divide(x3max, xabs, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41169, _mut41170, _mut41171, _mut41172);
                        s3 = AOR_plus(1, AOR_multiply(AOR_multiply(s3, r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41173, _mut41174, _mut41175, _mut41176), r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41177, _mut41178, _mut41179, _mut41180), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41181, _mut41182, _mut41183, _mut41184);
                        x3max = xabs;
                    } else {
                        if (ROR_not_equals(xabs, 0, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41156, _mut41157, _mut41158, _mut41159, _mut41160)) {
                            double r = AOR_divide(xabs, x3max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41161, _mut41162, _mut41163, _mut41164);
                            s3 += AOR_multiply(r, r, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41165, _mut41166, _mut41167, _mut41168);
                        }
                    }
                }
            } else {
                s2 += AOR_multiply(xabs, xabs, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41142, _mut41143, _mut41144, _mut41145);
            }
        }
        double norm;
        if (ROR_not_equals(s1, 0, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41219, _mut41220, _mut41221, _mut41222, _mut41223)) {
            norm = AOR_multiply(x1max, Math.sqrt(AOR_plus(s1, AOR_divide((AOR_divide(s2, x1max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41274, _mut41275, _mut41276, _mut41277)), x1max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41278, _mut41279, _mut41280, _mut41281), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41282, _mut41283, _mut41284, _mut41285)), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41286, _mut41287, _mut41288, _mut41289);
        } else {
            if (ROR_equals(s2, 0, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41224, _mut41225, _mut41226, _mut41227, _mut41228)) {
                norm = AOR_multiply(x3max, Math.sqrt(s3), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41270, _mut41271, _mut41272, _mut41273);
            } else {
                if (ROR_greater_equals(s2, x3max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41229, _mut41230, _mut41231, _mut41232, _mut41233)) {
                    norm = Math.sqrt(AOR_multiply(s2, (AOR_plus(1, AOR_multiply((AOR_divide(x3max, s2, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41250, _mut41251, _mut41252, _mut41253)), (AOR_multiply(x3max, s3, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41254, _mut41255, _mut41256, _mut41257)), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41258, _mut41259, _mut41260, _mut41261), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41262, _mut41263, _mut41264, _mut41265)), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41266, _mut41267, _mut41268, _mut41269));
                } else {
                    norm = Math.sqrt(AOR_multiply(x3max, (AOR_plus((AOR_divide(s2, x3max, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41234, _mut41235, _mut41236, _mut41237)), (AOR_multiply(x3max, s3, "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41238, _mut41239, _mut41240, _mut41241)), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41242, _mut41243, _mut41244, _mut41245)), "org.apache.commons.math3.util.MathArrays.safeNorm_699", _mut41246, _mut41247, _mut41248, _mut41249));
                }
            }
        }
        return norm;
    }

    /**
     * A helper data structure holding a double and an integer value.
     */
    private static class PairDoubleInteger {

        /**
         * Key
         */
        private final double key;

        /**
         * Value
         */
        private final int value;

        /**
         * @param key Key.
         * @param value Value.
         */
        PairDoubleInteger(double key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * @return the key.
         */
        public double getKey() {
            return key;
        }

        /**
         * @return the value.
         */
        public int getValue() {
            return value;
        }
    }

    /**
     * Sort an array in ascending order in place and perform the same reordering
     * of entries on other arrays. For example, if
     * {@code x = [3, 1, 2], y = [1, 2, 3]} and {@code z = [0, 5, 7]}, then
     * {@code sortInPlace(x, y, z)} will update {@code x} to {@code [1, 2, 3]},
     * {@code y} to {@code [2, 3, 1]} and {@code z} to {@code [5, 7, 0]}.
     *
     * @param x Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on {@code x}.
     * @throws DimensionMismatchException if any {@code y} is not the same
     * size as {@code x}.
     * @throws NullArgumentException if {@code x} or any {@code y} is null.
     * @since 3.0
     */
    public static void sortInPlace(double[] x, double[]... yList) throws DimensionMismatchException, NullArgumentException {
        sortInPlace(x, OrderDirection.INCREASING, yList);
    }

    /**
     * Sort an array in place and perform the same reordering of entries on
     * other arrays.  This method works the same as the other
     * {@link #sortInPlace(double[], double[][]) sortInPlace} method, but
     * allows the order of the sort to be provided in the {@code dir}
     * parameter.
     *
     * @param x Array to be sorted and used as a pattern for permutation
     * of the other arrays.
     * @param dir Order direction.
     * @param yList Set of arrays whose permutations of entries will follow
     * those performed on {@code x}.
     * @throws DimensionMismatchException if any {@code y} is not the same
     * size as {@code x}.
     * @throws NullArgumentException if {@code x} or any {@code y} is null
     * @since 3.0
     */
    public static void sortInPlace(double[] x, final OrderDirection dir, double[]... yList) throws NullArgumentException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.compare_857");
        // Consistency checks.
        if (x == null) {
            throw new NullArgumentException();
        }
        final int yListLen = yList.length;
        final int len = x.length;
        for (int j = 0; ROR_less(j, yListLen, "org.apache.commons.math3.util.MathArrays.sortInPlace_821", _mut41295, _mut41296, _mut41297, _mut41298, _mut41299); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.sortInPlace_821");
            final double[] y = yList[j];
            if (y == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(y.length, len, "org.apache.commons.math3.util.MathArrays.sortInPlace_821", _mut41290, _mut41291, _mut41292, _mut41293, _mut41294)) {
                throw new DimensionMismatchException(y.length, len);
            }
        }
        // Associate each abscissa "x[i]" with its index "i".
        final List<PairDoubleInteger> list = new ArrayList<PairDoubleInteger>(len);
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.sortInPlace_821", _mut41300, _mut41301, _mut41302, _mut41303, _mut41304); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.sortInPlace_821");
            list.add(new PairDoubleInteger(x[i], i));
        }
        // Create comparators for increasing and decreasing orders.
        final Comparator<PairDoubleInteger> comp = dir == MathArrays.OrderDirection.INCREASING ? new Comparator<PairDoubleInteger>() {

            /**
             * {@inheritDoc}
             */
            public int compare(PairDoubleInteger o1, PairDoubleInteger o2) {
                return Double.compare(o1.getKey(), o2.getKey());
            }
        } : new Comparator<PairDoubleInteger>() {

            /**
             * {@inheritDoc}
             */
            public int compare(PairDoubleInteger o1, PairDoubleInteger o2) {
                return Double.compare(o2.getKey(), o1.getKey());
            }
        };
        // Sort.
        Collections.sort(list, comp);
        // Retrieve indices of original locations.
        final int[] indices = new int[len];
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.compare_857", _mut41305, _mut41306, _mut41307, _mut41308, _mut41309); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.compare_857");
            final PairDoubleInteger e = list.get(i);
            x[i] = e.getKey();
            indices[i] = e.getValue();
        }
        // elements to their new location.
        for (int j = 0; ROR_less(j, yListLen, "org.apache.commons.math3.util.MathArrays.compare_857", _mut41315, _mut41316, _mut41317, _mut41318, _mut41319); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.compare_857");
            // Input array will be modified in place.
            final double[] yInPlace = yList[j];
            final double[] yOrig = yInPlace.clone();
            for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.compare_857", _mut41310, _mut41311, _mut41312, _mut41313, _mut41314); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.compare_857");
                yInPlace[i] = yOrig[indices[i]];
            }
        }
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @return the copied array.
     */
    public static int[] copyOf(int[] source) {
        return copyOf(source, source.length);
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @return the copied array.
     */
    public static double[] copyOf(double[] source) {
        return copyOf(source, source.length);
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param len Number of entries to copy. If smaller then the source
     * length, the copy will be truncated, if larger it will padded with
     * zeroes.
     * @return the copied array.
     */
    public static int[] copyOf(int[] source, int len) {
        final int[] output = new int[len];
        System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
        return output;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param len Number of entries to copy. If smaller then the source
     * length, the copy will be truncated, if larger it will padded with
     * zeroes.
     * @return the copied array.
     */
    public static double[] copyOf(double[] source, int len) {
        final double[] output = new double[len];
        System.arraycopy(source, 0, output, 0, FastMath.min(len, source.length));
        return output;
    }

    /**
     * Creates a copy of the {@code source} array.
     *
     * @param source Array to be copied.
     * @param from Initial index of the range to be copied, inclusive.
     * @param to Final index of the range to be copied, exclusive. (This index may lie outside the array.)
     * @return the copied array.
     */
    public static double[] copyOfRange(double[] source, int from, int to) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.copyOfRange_953");
        final int len = AOR_minus(to, from, "org.apache.commons.math3.util.MathArrays.copyOfRange_953", _mut41320, _mut41321, _mut41322, _mut41323);
        final double[] output = new double[len];
        System.arraycopy(source, from, output, 0, FastMath.min(len, AOR_minus(source.length, from, "org.apache.commons.math3.util.MathArrays.copyOfRange_953", _mut41324, _mut41325, _mut41326, _mut41327)));
        return output;
    }

    /**
     * Compute a linear combination accurately.
     * This method computes the sum of the products
     * <code>a<sub>i</sub> b<sub>i</sub></code> to high accuracy.
     * It does so by using specific multiplication and addition algorithms to
     * preserve accuracy and reduce cancellation effects.
     * <br/>
     * It is based on the 2005 paper
     * <a href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita, Siegfried M. Rump,
     * and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     *
     * @param a Factors.
     * @param b Factors.
     * @return <code>&Sigma;<sub>i</sub> a<sub>i</sub> b<sub>i</sub></code>.
     * @throws DimensionMismatchException if arrays dimensions don't match
     */
    public static double linearCombination(final double[] a, final double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_977");
        checkEqualLength(a, b);
        final int len = a.length;
        if (ROR_equals(len, 1, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41328, _mut41329, _mut41330, _mut41331, _mut41332)) {
            // Revert to scalar multiplication.
            return AOR_multiply(a[0], b[0], "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41333, _mut41334, _mut41335, _mut41336);
        }
        final double[] prodHigh = new double[len];
        double prodLowSum = 0;
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41381, _mut41382, _mut41383, _mut41384, _mut41385); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_977");
            final double ai = a[i];
            final double aHigh = Double.longBitsToDouble(Double.doubleToRawLongBits(ai) & ((-1L) << 27));
            final double aLow = AOR_minus(ai, aHigh, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41337, _mut41338, _mut41339, _mut41340);
            final double bi = b[i];
            final double bHigh = Double.longBitsToDouble(Double.doubleToRawLongBits(bi) & ((-1L) << 27));
            final double bLow = AOR_minus(bi, bHigh, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41341, _mut41342, _mut41343, _mut41344);
            prodHigh[i] = AOR_multiply(ai, bi, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41345, _mut41346, _mut41347, _mut41348);
            final double prodLow = AOR_minus(AOR_multiply(aLow, bLow, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41349, _mut41350, _mut41351, _mut41352), (AOR_minus((AOR_minus((AOR_minus(prodHigh[i], AOR_multiply(aHigh, bHigh, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41353, _mut41354, _mut41355, _mut41356), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41357, _mut41358, _mut41359, _mut41360)), AOR_multiply(aLow, bHigh, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41361, _mut41362, _mut41363, _mut41364), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41365, _mut41366, _mut41367, _mut41368)), AOR_multiply(aHigh, bLow, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41369, _mut41370, _mut41371, _mut41372), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41373, _mut41374, _mut41375, _mut41376)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41377, _mut41378, _mut41379, _mut41380);
            prodLowSum += prodLow;
        }
        final double prodHighCur = prodHigh[0];
        double prodHighNext = prodHigh[1];
        double sHighPrev = AOR_plus(prodHighCur, prodHighNext, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41386, _mut41387, _mut41388, _mut41389);
        double sPrime = AOR_minus(sHighPrev, prodHighNext, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41390, _mut41391, _mut41392, _mut41393);
        double sLowSum = AOR_plus((AOR_minus(prodHighNext, (AOR_minus(sHighPrev, sPrime, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41394, _mut41395, _mut41396, _mut41397)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41398, _mut41399, _mut41400, _mut41401)), (AOR_minus(prodHighCur, sPrime, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41402, _mut41403, _mut41404, _mut41405)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41406, _mut41407, _mut41408, _mut41409);
        final int lenMinusOne = AOR_minus(len, 1, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41410, _mut41411, _mut41412, _mut41413);
        for (int i = 1; ROR_less(i, lenMinusOne, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41442, _mut41443, _mut41444, _mut41445, _mut41446); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_977");
            prodHighNext = prodHigh[AOR_plus(i, 1, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41414, _mut41415, _mut41416, _mut41417)];
            final double sHighCur = AOR_plus(sHighPrev, prodHighNext, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41418, _mut41419, _mut41420, _mut41421);
            sPrime = AOR_minus(sHighCur, prodHighNext, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41422, _mut41423, _mut41424, _mut41425);
            sLowSum += AOR_plus((AOR_minus(prodHighNext, (AOR_minus(sHighCur, sPrime, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41426, _mut41427, _mut41428, _mut41429)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41430, _mut41431, _mut41432, _mut41433)), (AOR_minus(sHighPrev, sPrime, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41434, _mut41435, _mut41436, _mut41437)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41438, _mut41439, _mut41440, _mut41441);
            sHighPrev = sHighCur;
        }
        double result = AOR_plus(sHighPrev, (AOR_plus(prodLowSum, sLowSum, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41447, _mut41448, _mut41449, _mut41450)), "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41451, _mut41452, _mut41453, _mut41454);
        if (Double.isNaN(result)) {
            // just rely on the naive implementation and let IEEE754 handle this
            result = 0;
            for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41459, _mut41460, _mut41461, _mut41462, _mut41463); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_977");
                result += AOR_multiply(a[i], b[i], "org.apache.commons.math3.util.MathArrays.linearCombination_977", _mut41455, _mut41456, _mut41457, _mut41458);
            }
        }
        return result;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> to high accuracy. It does
     * so by using specific multiplication and addition algorithms to
     * preserve accuracy and reduce cancellation effects. It is based
     * on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub>
     * @see #linearCombination(double, double, double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_1057");
        // split a1 and b1 as one 26 bits number and one 27 bits number
        final double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & ((-1L) << 27));
        final double a1Low = AOR_minus(a1, a1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41464, _mut41465, _mut41466, _mut41467);
        final double b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & ((-1L) << 27));
        final double b1Low = AOR_minus(b1, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41468, _mut41469, _mut41470, _mut41471);
        // accurate multiplication a1 * b1
        final double prod1High = AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41472, _mut41473, _mut41474, _mut41475);
        final double prod1Low = AOR_minus(AOR_multiply(a1Low, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41476, _mut41477, _mut41478, _mut41479), (AOR_minus((AOR_minus((AOR_minus(prod1High, AOR_multiply(a1High, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41480, _mut41481, _mut41482, _mut41483), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41484, _mut41485, _mut41486, _mut41487)), AOR_multiply(a1Low, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41488, _mut41489, _mut41490, _mut41491), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41492, _mut41493, _mut41494, _mut41495)), AOR_multiply(a1High, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41496, _mut41497, _mut41498, _mut41499), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41500, _mut41501, _mut41502, _mut41503)), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41504, _mut41505, _mut41506, _mut41507);
        // split a2 and b2 as one 26 bits number and one 27 bits number
        final double a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & ((-1L) << 27));
        final double a2Low = AOR_minus(a2, a2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41508, _mut41509, _mut41510, _mut41511);
        final double b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & ((-1L) << 27));
        final double b2Low = AOR_minus(b2, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41512, _mut41513, _mut41514, _mut41515);
        // accurate multiplication a2 * b2
        final double prod2High = AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41516, _mut41517, _mut41518, _mut41519);
        final double prod2Low = AOR_minus(AOR_multiply(a2Low, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41520, _mut41521, _mut41522, _mut41523), (AOR_minus((AOR_minus((AOR_minus(prod2High, AOR_multiply(a2High, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41524, _mut41525, _mut41526, _mut41527), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41528, _mut41529, _mut41530, _mut41531)), AOR_multiply(a2Low, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41532, _mut41533, _mut41534, _mut41535), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41536, _mut41537, _mut41538, _mut41539)), AOR_multiply(a2High, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41540, _mut41541, _mut41542, _mut41543), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41544, _mut41545, _mut41546, _mut41547)), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41548, _mut41549, _mut41550, _mut41551);
        // accurate addition a1 * b1 + a2 * b2
        final double s12High = AOR_plus(prod1High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41552, _mut41553, _mut41554, _mut41555);
        final double s12Prime = AOR_minus(s12High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41556, _mut41557, _mut41558, _mut41559);
        final double s12Low = AOR_plus((AOR_minus(prod2High, (AOR_minus(s12High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41560, _mut41561, _mut41562, _mut41563)), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41564, _mut41565, _mut41566, _mut41567)), (AOR_minus(prod1High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41568, _mut41569, _mut41570, _mut41571)), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41572, _mut41573, _mut41574, _mut41575);
        // to recover some bits from the extra words we have saved up to now
        double result = AOR_plus(s12High, (AOR_plus(AOR_plus(prod1Low, prod2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41576, _mut41577, _mut41578, _mut41579), s12Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41580, _mut41581, _mut41582, _mut41583)), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41584, _mut41585, _mut41586, _mut41587);
        if (Double.isNaN(result)) {
            // just rely on the naive implementation and let IEEE754 handle this
            result = AOR_plus(AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41588, _mut41589, _mut41590, _mut41591), AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41592, _mut41593, _mut41594, _mut41595), "org.apache.commons.math3.util.MathArrays.linearCombination_1057", _mut41596, _mut41597, _mut41598, _mut41599);
        }
        return result;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub>
     * to high accuracy. It does so by using specific multiplication and
     * addition algorithms to preserve accuracy and reduce cancellation effects.
     * It is based on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @param a3 first factor of the third term
     * @param b3 second factor of the third term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub>
     * @see #linearCombination(double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_1131");
        // split a1 and b1 as one 26 bits number and one 27 bits number
        final double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & ((-1L) << 27));
        final double a1Low = AOR_minus(a1, a1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41600, _mut41601, _mut41602, _mut41603);
        final double b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & ((-1L) << 27));
        final double b1Low = AOR_minus(b1, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41604, _mut41605, _mut41606, _mut41607);
        // accurate multiplication a1 * b1
        final double prod1High = AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41608, _mut41609, _mut41610, _mut41611);
        final double prod1Low = AOR_minus(AOR_multiply(a1Low, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41612, _mut41613, _mut41614, _mut41615), (AOR_minus((AOR_minus((AOR_minus(prod1High, AOR_multiply(a1High, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41616, _mut41617, _mut41618, _mut41619), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41620, _mut41621, _mut41622, _mut41623)), AOR_multiply(a1Low, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41624, _mut41625, _mut41626, _mut41627), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41628, _mut41629, _mut41630, _mut41631)), AOR_multiply(a1High, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41632, _mut41633, _mut41634, _mut41635), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41636, _mut41637, _mut41638, _mut41639)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41640, _mut41641, _mut41642, _mut41643);
        // split a2 and b2 as one 26 bits number and one 27 bits number
        final double a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & ((-1L) << 27));
        final double a2Low = AOR_minus(a2, a2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41644, _mut41645, _mut41646, _mut41647);
        final double b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & ((-1L) << 27));
        final double b2Low = AOR_minus(b2, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41648, _mut41649, _mut41650, _mut41651);
        // accurate multiplication a2 * b2
        final double prod2High = AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41652, _mut41653, _mut41654, _mut41655);
        final double prod2Low = AOR_minus(AOR_multiply(a2Low, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41656, _mut41657, _mut41658, _mut41659), (AOR_minus((AOR_minus((AOR_minus(prod2High, AOR_multiply(a2High, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41660, _mut41661, _mut41662, _mut41663), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41664, _mut41665, _mut41666, _mut41667)), AOR_multiply(a2Low, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41668, _mut41669, _mut41670, _mut41671), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41672, _mut41673, _mut41674, _mut41675)), AOR_multiply(a2High, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41676, _mut41677, _mut41678, _mut41679), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41680, _mut41681, _mut41682, _mut41683)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41684, _mut41685, _mut41686, _mut41687);
        // split a3 and b3 as one 26 bits number and one 27 bits number
        final double a3High = Double.longBitsToDouble(Double.doubleToRawLongBits(a3) & ((-1L) << 27));
        final double a3Low = AOR_minus(a3, a3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41688, _mut41689, _mut41690, _mut41691);
        final double b3High = Double.longBitsToDouble(Double.doubleToRawLongBits(b3) & ((-1L) << 27));
        final double b3Low = AOR_minus(b3, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41692, _mut41693, _mut41694, _mut41695);
        // accurate multiplication a3 * b3
        final double prod3High = AOR_multiply(a3, b3, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41696, _mut41697, _mut41698, _mut41699);
        final double prod3Low = AOR_minus(AOR_multiply(a3Low, b3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41700, _mut41701, _mut41702, _mut41703), (AOR_minus((AOR_minus((AOR_minus(prod3High, AOR_multiply(a3High, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41704, _mut41705, _mut41706, _mut41707), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41708, _mut41709, _mut41710, _mut41711)), AOR_multiply(a3Low, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41712, _mut41713, _mut41714, _mut41715), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41716, _mut41717, _mut41718, _mut41719)), AOR_multiply(a3High, b3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41720, _mut41721, _mut41722, _mut41723), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41724, _mut41725, _mut41726, _mut41727)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41728, _mut41729, _mut41730, _mut41731);
        // accurate addition a1 * b1 + a2 * b2
        final double s12High = AOR_plus(prod1High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41732, _mut41733, _mut41734, _mut41735);
        final double s12Prime = AOR_minus(s12High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41736, _mut41737, _mut41738, _mut41739);
        final double s12Low = AOR_plus((AOR_minus(prod2High, (AOR_minus(s12High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41740, _mut41741, _mut41742, _mut41743)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41744, _mut41745, _mut41746, _mut41747)), (AOR_minus(prod1High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41748, _mut41749, _mut41750, _mut41751)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41752, _mut41753, _mut41754, _mut41755);
        // accurate addition a1 * b1 + a2 * b2 + a3 * b3
        final double s123High = AOR_plus(s12High, prod3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41756, _mut41757, _mut41758, _mut41759);
        final double s123Prime = AOR_minus(s123High, prod3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41760, _mut41761, _mut41762, _mut41763);
        final double s123Low = AOR_plus((AOR_minus(prod3High, (AOR_minus(s123High, s123Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41764, _mut41765, _mut41766, _mut41767)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41768, _mut41769, _mut41770, _mut41771)), (AOR_minus(s12High, s123Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41772, _mut41773, _mut41774, _mut41775)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41776, _mut41777, _mut41778, _mut41779);
        // to recover some bits from the extra words we have saved up to now
        double result = AOR_plus(s123High, (AOR_plus(AOR_plus(AOR_plus(AOR_plus(prod1Low, prod2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41780, _mut41781, _mut41782, _mut41783), prod3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41784, _mut41785, _mut41786, _mut41787), s12Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41788, _mut41789, _mut41790, _mut41791), s123Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41792, _mut41793, _mut41794, _mut41795)), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41796, _mut41797, _mut41798, _mut41799);
        if (Double.isNaN(result)) {
            // just rely on the naive implementation and let IEEE754 handle this
            result = AOR_plus(AOR_plus(AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41800, _mut41801, _mut41802, _mut41803), AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41804, _mut41805, _mut41806, _mut41807), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41808, _mut41809, _mut41810, _mut41811), AOR_multiply(a3, b3, "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41812, _mut41813, _mut41814, _mut41815), "org.apache.commons.math3.util.MathArrays.linearCombination_1131", _mut41816, _mut41817, _mut41818, _mut41819);
        }
        return result;
    }

    /**
     * Compute a linear combination accurately.
     * <p>
     * This method computes a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub> +
     * a<sub>4</sub>&times;b<sub>4</sub>
     * to high accuracy. It does so by using specific multiplication and
     * addition algorithms to preserve accuracy and reduce cancellation effects.
     * It is based on the 2005 paper <a
     * href="http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.2.1547">
     * Accurate Sum and Dot Product</a> by Takeshi Ogita,
     * Siegfried M. Rump, and Shin'ichi Oishi published in SIAM J. Sci. Comput.
     * </p>
     * @param a1 first factor of the first term
     * @param b1 second factor of the first term
     * @param a2 first factor of the second term
     * @param b2 second factor of the second term
     * @param a3 first factor of the third term
     * @param b3 second factor of the third term
     * @param a4 first factor of the third term
     * @param b4 second factor of the third term
     * @return a<sub>1</sub>&times;b<sub>1</sub> +
     * a<sub>2</sub>&times;b<sub>2</sub> + a<sub>3</sub>&times;b<sub>3</sub> +
     * a<sub>4</sub>&times;b<sub>4</sub>
     * @see #linearCombination(double, double, double, double)
     * @see #linearCombination(double, double, double, double, double, double)
     */
    public static double linearCombination(final double a1, final double b1, final double a2, final double b2, final double a3, final double b3, final double a4, final double b4) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.linearCombination_1225");
        // split a1 and b1 as one 26 bits number and one 27 bits number
        final double a1High = Double.longBitsToDouble(Double.doubleToRawLongBits(a1) & ((-1L) << 27));
        final double a1Low = AOR_minus(a1, a1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41820, _mut41821, _mut41822, _mut41823);
        final double b1High = Double.longBitsToDouble(Double.doubleToRawLongBits(b1) & ((-1L) << 27));
        final double b1Low = AOR_minus(b1, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41824, _mut41825, _mut41826, _mut41827);
        // accurate multiplication a1 * b1
        final double prod1High = AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41828, _mut41829, _mut41830, _mut41831);
        final double prod1Low = AOR_minus(AOR_multiply(a1Low, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41832, _mut41833, _mut41834, _mut41835), (AOR_minus((AOR_minus((AOR_minus(prod1High, AOR_multiply(a1High, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41836, _mut41837, _mut41838, _mut41839), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41840, _mut41841, _mut41842, _mut41843)), AOR_multiply(a1Low, b1High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41844, _mut41845, _mut41846, _mut41847), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41848, _mut41849, _mut41850, _mut41851)), AOR_multiply(a1High, b1Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41852, _mut41853, _mut41854, _mut41855), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41856, _mut41857, _mut41858, _mut41859)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41860, _mut41861, _mut41862, _mut41863);
        // split a2 and b2 as one 26 bits number and one 27 bits number
        final double a2High = Double.longBitsToDouble(Double.doubleToRawLongBits(a2) & ((-1L) << 27));
        final double a2Low = AOR_minus(a2, a2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41864, _mut41865, _mut41866, _mut41867);
        final double b2High = Double.longBitsToDouble(Double.doubleToRawLongBits(b2) & ((-1L) << 27));
        final double b2Low = AOR_minus(b2, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41868, _mut41869, _mut41870, _mut41871);
        // accurate multiplication a2 * b2
        final double prod2High = AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41872, _mut41873, _mut41874, _mut41875);
        final double prod2Low = AOR_minus(AOR_multiply(a2Low, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41876, _mut41877, _mut41878, _mut41879), (AOR_minus((AOR_minus((AOR_minus(prod2High, AOR_multiply(a2High, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41880, _mut41881, _mut41882, _mut41883), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41884, _mut41885, _mut41886, _mut41887)), AOR_multiply(a2Low, b2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41888, _mut41889, _mut41890, _mut41891), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41892, _mut41893, _mut41894, _mut41895)), AOR_multiply(a2High, b2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41896, _mut41897, _mut41898, _mut41899), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41900, _mut41901, _mut41902, _mut41903)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41904, _mut41905, _mut41906, _mut41907);
        // split a3 and b3 as one 26 bits number and one 27 bits number
        final double a3High = Double.longBitsToDouble(Double.doubleToRawLongBits(a3) & ((-1L) << 27));
        final double a3Low = AOR_minus(a3, a3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41908, _mut41909, _mut41910, _mut41911);
        final double b3High = Double.longBitsToDouble(Double.doubleToRawLongBits(b3) & ((-1L) << 27));
        final double b3Low = AOR_minus(b3, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41912, _mut41913, _mut41914, _mut41915);
        // accurate multiplication a3 * b3
        final double prod3High = AOR_multiply(a3, b3, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41916, _mut41917, _mut41918, _mut41919);
        final double prod3Low = AOR_minus(AOR_multiply(a3Low, b3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41920, _mut41921, _mut41922, _mut41923), (AOR_minus((AOR_minus((AOR_minus(prod3High, AOR_multiply(a3High, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41924, _mut41925, _mut41926, _mut41927), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41928, _mut41929, _mut41930, _mut41931)), AOR_multiply(a3Low, b3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41932, _mut41933, _mut41934, _mut41935), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41936, _mut41937, _mut41938, _mut41939)), AOR_multiply(a3High, b3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41940, _mut41941, _mut41942, _mut41943), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41944, _mut41945, _mut41946, _mut41947)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41948, _mut41949, _mut41950, _mut41951);
        // split a4 and b4 as one 26 bits number and one 27 bits number
        final double a4High = Double.longBitsToDouble(Double.doubleToRawLongBits(a4) & ((-1L) << 27));
        final double a4Low = AOR_minus(a4, a4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41952, _mut41953, _mut41954, _mut41955);
        final double b4High = Double.longBitsToDouble(Double.doubleToRawLongBits(b4) & ((-1L) << 27));
        final double b4Low = AOR_minus(b4, b4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41956, _mut41957, _mut41958, _mut41959);
        // accurate multiplication a4 * b4
        final double prod4High = AOR_multiply(a4, b4, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41960, _mut41961, _mut41962, _mut41963);
        final double prod4Low = AOR_minus(AOR_multiply(a4Low, b4Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41964, _mut41965, _mut41966, _mut41967), (AOR_minus((AOR_minus((AOR_minus(prod4High, AOR_multiply(a4High, b4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41968, _mut41969, _mut41970, _mut41971), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41972, _mut41973, _mut41974, _mut41975)), AOR_multiply(a4Low, b4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41976, _mut41977, _mut41978, _mut41979), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41980, _mut41981, _mut41982, _mut41983)), AOR_multiply(a4High, b4Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41984, _mut41985, _mut41986, _mut41987), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41988, _mut41989, _mut41990, _mut41991)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41992, _mut41993, _mut41994, _mut41995);
        // accurate addition a1 * b1 + a2 * b2
        final double s12High = AOR_plus(prod1High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut41996, _mut41997, _mut41998, _mut41999);
        final double s12Prime = AOR_minus(s12High, prod2High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42000, _mut42001, _mut42002, _mut42003);
        final double s12Low = AOR_plus((AOR_minus(prod2High, (AOR_minus(s12High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42004, _mut42005, _mut42006, _mut42007)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42008, _mut42009, _mut42010, _mut42011)), (AOR_minus(prod1High, s12Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42012, _mut42013, _mut42014, _mut42015)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42016, _mut42017, _mut42018, _mut42019);
        // accurate addition a1 * b1 + a2 * b2 + a3 * b3
        final double s123High = AOR_plus(s12High, prod3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42020, _mut42021, _mut42022, _mut42023);
        final double s123Prime = AOR_minus(s123High, prod3High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42024, _mut42025, _mut42026, _mut42027);
        final double s123Low = AOR_plus((AOR_minus(prod3High, (AOR_minus(s123High, s123Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42028, _mut42029, _mut42030, _mut42031)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42032, _mut42033, _mut42034, _mut42035)), (AOR_minus(s12High, s123Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42036, _mut42037, _mut42038, _mut42039)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42040, _mut42041, _mut42042, _mut42043);
        // accurate addition a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4
        final double s1234High = AOR_plus(s123High, prod4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42044, _mut42045, _mut42046, _mut42047);
        final double s1234Prime = AOR_minus(s1234High, prod4High, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42048, _mut42049, _mut42050, _mut42051);
        final double s1234Low = AOR_plus((AOR_minus(prod4High, (AOR_minus(s1234High, s1234Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42052, _mut42053, _mut42054, _mut42055)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42056, _mut42057, _mut42058, _mut42059)), (AOR_minus(s123High, s1234Prime, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42060, _mut42061, _mut42062, _mut42063)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42064, _mut42065, _mut42066, _mut42067);
        // to recover some bits from the extra words we have saved up to now
        double result = AOR_plus(s1234High, (AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(prod1Low, prod2Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42068, _mut42069, _mut42070, _mut42071), prod3Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42072, _mut42073, _mut42074, _mut42075), prod4Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42076, _mut42077, _mut42078, _mut42079), s12Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42080, _mut42081, _mut42082, _mut42083), s123Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42084, _mut42085, _mut42086, _mut42087), s1234Low, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42088, _mut42089, _mut42090, _mut42091)), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42092, _mut42093, _mut42094, _mut42095);
        if (Double.isNaN(result)) {
            // just rely on the naive implementation and let IEEE754 handle this
            result = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(a1, b1, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42096, _mut42097, _mut42098, _mut42099), AOR_multiply(a2, b2, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42100, _mut42101, _mut42102, _mut42103), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42104, _mut42105, _mut42106, _mut42107), AOR_multiply(a3, b3, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42108, _mut42109, _mut42110, _mut42111), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42112, _mut42113, _mut42114, _mut42115), AOR_multiply(a4, b4, "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42116, _mut42117, _mut42118, _mut42119), "org.apache.commons.math3.util.MathArrays.linearCombination_1225", _mut42120, _mut42121, _mut42122, _mut42123);
        }
        return result;
    }

    /**
     * Returns true iff both arguments are null or have same dimensions and all
     * their elements are equal as defined by
     * {@link Precision#equals(float,float)}.
     *
     * @param x first array
     * @param y second array
     * @return true if the values are both null or have same dimension
     * and equal elements.
     */
    public static boolean equals(float[] x, float[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equals_1318");
        if ((_mut42124 ? ((x == null) && (y == null)) : ((x == null) || (y == null)))) {
            return !((x == null) ^ (y == null));
        }
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.util.MathArrays.equals_1318", _mut42125, _mut42126, _mut42127, _mut42128, _mut42129)) {
            return false;
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.util.MathArrays.equals_1318", _mut42130, _mut42131, _mut42132, _mut42133, _mut42134); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equals_1318");
            if (!Precision.equals(x[i], y[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true iff both arguments are null or have same dimensions and all
     * their elements are equal as defined by
     * {@link Precision#equalsIncludingNaN(double,double) this method}.
     *
     * @param x first array
     * @param y second array
     * @return true if the values are both null or have same dimension and
     * equal elements
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(float[] x, float[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1344");
        if ((_mut42135 ? ((x == null) && (y == null)) : ((x == null) || (y == null)))) {
            return !((x == null) ^ (y == null));
        }
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1344", _mut42136, _mut42137, _mut42138, _mut42139, _mut42140)) {
            return false;
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1344", _mut42141, _mut42142, _mut42143, _mut42144, _mut42145); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1344");
            if (!Precision.equalsIncludingNaN(x[i], y[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns {@code true} iff both arguments are {@code null} or have same
     * dimensions and all their elements are equal as defined by
     * {@link Precision#equals(double,double)}.
     *
     * @param x First array.
     * @param y Second array.
     * @return {@code true} if the values are both {@code null} or have same
     * dimension and equal elements.
     */
    public static boolean equals(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equals_1369");
        if ((_mut42146 ? ((x == null) && (y == null)) : ((x == null) || (y == null)))) {
            return !((x == null) ^ (y == null));
        }
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.util.MathArrays.equals_1369", _mut42147, _mut42148, _mut42149, _mut42150, _mut42151)) {
            return false;
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.util.MathArrays.equals_1369", _mut42152, _mut42153, _mut42154, _mut42155, _mut42156); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equals_1369");
            if (!Precision.equals(x[i], y[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns {@code true} iff both arguments are {@code null} or have same
     * dimensions and all their elements are equal as defined by
     * {@link Precision#equalsIncludingNaN(double,double) this method}.
     *
     * @param x First array.
     * @param y Second array.
     * @return {@code true} if the values are both {@code null} or have same
     * dimension and equal elements.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(double[] x, double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1395");
        if ((_mut42157 ? ((x == null) && (y == null)) : ((x == null) || (y == null)))) {
            return !((x == null) ^ (y == null));
        }
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1395", _mut42158, _mut42159, _mut42160, _mut42161, _mut42162)) {
            return false;
        }
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1395", _mut42163, _mut42164, _mut42165, _mut42166, _mut42167); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.equalsIncludingNaN_1395");
            if (!Precision.equalsIncludingNaN(x[i], y[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Normalizes an array to make it sum to a specified value.
     * Returns the result of the transformation
     * <pre>
     *    x |-> x * normalizedSum / sum
     * </pre>
     * applied to each non-NaN element x of the input array, where sum is the
     * sum of the non-NaN entries in the input array.
     * <p>
     * Throws IllegalArgumentException if {@code normalizedSum} is infinite
     * or NaN and ArithmeticException if the input array contains any infinite elements
     * or sums to 0.
     * <p>
     * Ignores (i.e., copies unchanged to the output array) NaNs in the input array.
     *
     * @param values Input array to be normalized
     * @param normalizedSum Target sum for the normalized array
     * @return the normalized array.
     * @throws MathArithmeticException if the input array contains infinite
     * elements or sums to zero.
     * @throws MathIllegalArgumentException if the target sum is infinite or {@code NaN}.
     * @since 2.1
     */
    public static double[] normalizeArray(double[] values, double normalizedSum) throws MathIllegalArgumentException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.normalizeArray_1433");
        if (Double.isInfinite(normalizedSum)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_INFINITE);
        }
        if (Double.isNaN(normalizedSum)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NORMALIZE_NAN);
        }
        double sum = 0d;
        final int len = values.length;
        double[] out = new double[len];
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.normalizeArray_1433", _mut42168, _mut42169, _mut42170, _mut42171, _mut42172); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.normalizeArray_1433");
            if (Double.isInfinite(values[i])) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, values[i], i);
            }
            if (!Double.isNaN(values[i])) {
                sum += values[i];
            }
        }
        if (ROR_equals(sum, 0, "org.apache.commons.math3.util.MathArrays.normalizeArray_1433", _mut42173, _mut42174, _mut42175, _mut42176, _mut42177)) {
            throw new MathArithmeticException(LocalizedFormats.ARRAY_SUMS_TO_ZERO);
        }
        for (int i = 0; ROR_less(i, len, "org.apache.commons.math3.util.MathArrays.normalizeArray_1433", _mut42186, _mut42187, _mut42188, _mut42189, _mut42190); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.normalizeArray_1433");
            if (Double.isNaN(values[i])) {
                out[i] = Double.NaN;
            } else {
                out[i] = AOR_divide(AOR_multiply(values[i], normalizedSum, "org.apache.commons.math3.util.MathArrays.normalizeArray_1433", _mut42178, _mut42179, _mut42180, _mut42181), sum, "org.apache.commons.math3.util.MathArrays.normalizeArray_1433", _mut42182, _mut42183, _mut42184, _mut42185);
            }
        }
        return out;
    }

    /**
     * Build an array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     *
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param length of the array
     * @return a new array
     * @since 3.2
     */
    public static <T> T[] buildArray(final Field<T> field, final int length) {
        // OK because field must be correct class
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(field.getRuntimeClass(), length);
        Arrays.fill(array, field.getZero());
        return array;
    }

    /**
     * Build a double dimension  array of elements.
     * <p>
     * Arrays are filled with field.getZero()
     *
     * @param <T> the type of the field elements
     * @param field field to which array elements belong
     * @param rows number of rows in the array
     * @param columns number of columns (may be negative to build partial
     * arrays in the same way <code>new Field[rows][]</code> works)
     * @return a new array
     * @since 3.2
     */
    @SuppressWarnings("unchecked")
    public static <T> T[][] buildArray(final Field<T> field, final int rows, final int columns) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.buildArray_1494");
        final T[][] array;
        if (ROR_less(columns, 0, "org.apache.commons.math3.util.MathArrays.buildArray_1494", _mut42191, _mut42192, _mut42193, _mut42194, _mut42195)) {
            T[] dummyRow = buildArray(field, 0);
            array = (T[][]) Array.newInstance(dummyRow.getClass(), rows);
        } else {
            array = (T[][]) Array.newInstance(field.getRuntimeClass(), new int[] { rows, columns });
            for (int i = 0; ROR_less(i, rows, "org.apache.commons.math3.util.MathArrays.buildArray_1494", _mut42196, _mut42197, _mut42198, _mut42199, _mut42200); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.buildArray_1494");
                Arrays.fill(array[i], field.getZero());
            }
        }
        return array;
    }

    /**
     * Calculates the <a href="http://en.wikipedia.org/wiki/Convolution">
     * convolution</a> between two sequences.
     * <p>
     * The solution is obtained via straightforward computation of the
     * convolution sum (and not via FFT). Whenever the computation needs
     * an element that would be located at an index outside the input arrays,
     * the value is assumed to be zero.
     *
     * @param x First sequence.
     * Typically, this sequence will represent an input signal to a system.
     * @param h Second sequence.
     * Typically, this sequence will represent the impulse response of the system.
     * @return the convolution of {@code x} and {@code h}.
     * This array's length will be {@code x.length + h.length - 1}.
     * @throws NullArgumentException if either {@code x} or {@code h} is {@code null}.
     * @throws NoDataException if either {@code x} or {@code h} is empty.
     *
     * @since 3.3
     */
    public static double[] convolve(double[] x, double[] h) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.convolve_1532");
        MathUtils.checkNotNull(x);
        MathUtils.checkNotNull(h);
        final int xLen = x.length;
        final int hLen = h.length;
        if ((_mut42211 ? (ROR_equals(xLen, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42201, _mut42202, _mut42203, _mut42204, _mut42205) && ROR_equals(hLen, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42206, _mut42207, _mut42208, _mut42209, _mut42210)) : (ROR_equals(xLen, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42201, _mut42202, _mut42203, _mut42204, _mut42205) || ROR_equals(hLen, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42206, _mut42207, _mut42208, _mut42209, _mut42210)))) {
            throw new NoDataException();
        }
        // initialize the output array
        final int totalLength = AOR_minus(AOR_plus(xLen, hLen, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42212, _mut42213, _mut42214, _mut42215), 1, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42216, _mut42217, _mut42218, _mut42219);
        final double[] y = new double[totalLength];
        // straightforward implementation of the convolution sum
        for (int n = 0; ROR_less(n, totalLength, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42247, _mut42248, _mut42249, _mut42250, _mut42251); n++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.convolve_1532");
            double yn = 0;
            int k = FastMath.max(0, AOR_minus(AOR_plus(n, 1, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42220, _mut42221, _mut42222, _mut42223), xLen, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42224, _mut42225, _mut42226, _mut42227));
            int j = AOR_minus(n, k, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42228, _mut42229, _mut42230, _mut42231);
            while ((_mut42246 ? (ROR_less(k, hLen, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42236, _mut42237, _mut42238, _mut42239, _mut42240) || ROR_greater_equals(j, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42241, _mut42242, _mut42243, _mut42244, _mut42245)) : (ROR_less(k, hLen, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42236, _mut42237, _mut42238, _mut42239, _mut42240) && ROR_greater_equals(j, 0, "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42241, _mut42242, _mut42243, _mut42244, _mut42245)))) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.convolve_1532");
                yn += AOR_multiply(x[j--], h[k++], "org.apache.commons.math3.util.MathArrays.convolve_1532", _mut42232, _mut42233, _mut42234, _mut42235);
            }
            y[n] = yn;
        }
        return y;
    }

    /**
     * Specification for indicating that some operation applies
     * before or after a given index.
     */
    public enum Position {

        /**
         * Designates the beginning of the array (near index 0).
         */
        HEAD,
        /**
         * Designates the end of the array.
         */
        TAIL
    }

    /**
     * Shuffle the entries of the given array.
     * The {@code start} and {@code pos} parameters select which portion
     * of the array is randomized and which is left untouched.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param start Index at which shuffling begins.
     * @param pos Shuffling is performed for index positions between
     * {@code start} and either the end (if {@link Position#TAIL})
     * or the beginning (if {@link Position#HEAD}) of the array.
     */
    public static void shuffle(int[] list, int start, Position pos) {
        shuffle(list, start, pos, new Well19937c());
    }

    /**
     * Shuffle the entries of the given array, using the
     * <a href="http://en.wikipedia.org/wiki/Fisher–Yates_shuffle#The_modern_algorithm">
     * Fisher–Yates</a> algorithm.
     * The {@code start} and {@code pos} parameters select which portion
     * of the array is randomized and which is left untouched.
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param start Index at which shuffling begins.
     * @param pos Shuffling is performed for index positions between
     * {@code start} and either the end (if {@link Position#TAIL})
     * or the beginning (if {@link Position#HEAD}) of the array.
     * @param rng Random number generator.
     */
    public static void shuffle(int[] list, int start, Position pos, RandomGenerator rng) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.shuffle_1607");
        switch(pos) {
            case TAIL:
                {
                    for (int i = list.length - 1; ROR_greater_equals(i, start, "org.apache.commons.math3.util.MathArrays.shuffle_1607", _mut42257, _mut42258, _mut42259, _mut42260, _mut42261); i--) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.shuffle_1607");
                        final int target;
                        if (ROR_equals(i, start, "org.apache.commons.math3.util.MathArrays.shuffle_1607", _mut42252, _mut42253, _mut42254, _mut42255, _mut42256)) {
                            target = start;
                        } else {
                            // NumberIsTooLargeException cannot occur.
                            target = new UniformIntegerDistribution(rng, start, i).sample();
                        }
                        final int temp = list[target];
                        list[target] = list[i];
                        list[i] = temp;
                    }
                }
                break;
            case HEAD:
                {
                    for (int i = 0; ROR_less_equals(i, start, "org.apache.commons.math3.util.MathArrays.shuffle_1607", _mut42267, _mut42268, _mut42269, _mut42270, _mut42271); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.shuffle_1607");
                        final int target;
                        if (ROR_equals(i, start, "org.apache.commons.math3.util.MathArrays.shuffle_1607", _mut42262, _mut42263, _mut42264, _mut42265, _mut42266)) {
                            target = start;
                        } else {
                            // NumberIsTooLargeException cannot occur.
                            target = new UniformIntegerDistribution(rng, i, start).sample();
                        }
                        final int temp = list[target];
                        list[target] = list[i];
                        list[i] = temp;
                    }
                }
                break;
            default:
                // Should never happen.
                throw new MathInternalError();
        }
    }

    /**
     * Shuffle the entries of the given array.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     * @param rng Random number generator.
     */
    public static void shuffle(int[] list, RandomGenerator rng) {
        shuffle(list, 0, Position.TAIL, rng);
    }

    /**
     * Shuffle the entries of the given array.
     *
     * @see #shuffle(int[],int,Position,RandomGenerator)
     *
     * @param list Array whose entries will be shuffled (in-place).
     */
    public static void shuffle(int[] list) {
        shuffle(list, new Well19937c());
    }

    /**
     * Returns an array representing the natural number {@code n}.
     *
     * @param n Natural number.
     * @return an array whose entries are the numbers 0, 1, ..., {@code n}-1.
     * If {@code n == 0}, the returned array is empty.
     */
    public static int[] natural(int n) {
        return sequence(n, 0, 1);
    }

    /**
     * Returns an array of {@code size} integers starting at {@code start},
     * skipping {@code stride} numbers.
     *
     * @param size Natural number.
     * @param start Natural number.
     * @param stride Natural number.
     * @return an array whose entries are the numbers
     * {@code start, start + stride, ..., start + (size - 1) * stride}.
     * If {@code size == 0}, the returned array is empty.
     *
     * @since 3.4
     */
    public static int[] sequence(int size, int start, int stride) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.sequence_1694");
        final int[] a = new int[size];
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.util.MathArrays.sequence_1694", _mut42280, _mut42281, _mut42282, _mut42283, _mut42284); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.sequence_1694");
            a[i] = AOR_plus(start, AOR_multiply(i, stride, "org.apache.commons.math3.util.MathArrays.sequence_1694", _mut42272, _mut42273, _mut42274, _mut42275), "org.apache.commons.math3.util.MathArrays.sequence_1694", _mut42276, _mut42277, _mut42278, _mut42279);
        }
        return a;
    }

    /**
     * This method is used
     * to verify that the input parameters designate a subarray of positive length.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * positive length</li>
     * <li>throws <code>MathIllegalArgumentException</code> if the array is null or
     * or the indices are invalid</li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0.
     * </ul></p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return true if the parameters are valid and designate a subarray of positive length
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final int begin, final int length) throws MathIllegalArgumentException {
        return verifyValues(values, begin, length, false);
    }

    /**
     * This method is used
     * to verify that the input parameters designate a subarray of positive length.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * non-negative length</li>
     * <li>throws <code>IllegalArgumentException</code> if the array is null or
     * or the indices are invalid</li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0 unless <code>allowEmpty</code> is <code>true</code>
     * </ul></p>
     *
     * @param values the input array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @param allowEmpty if <code>true</code> then zero length arrays are allowed
     * @return true if the parameters are valid
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final int begin, final int length, final boolean allowEmpty) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.verifyValues_1749");
        if (values == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        if (ROR_less(begin, 0, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42285, _mut42286, _mut42287, _mut42288, _mut42289)) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(begin));
        }
        if (ROR_less(length, 0, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42290, _mut42291, _mut42292, _mut42293, _mut42294)) {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(length));
        }
        if (ROR_greater(AOR_plus(begin, length, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42295, _mut42296, _mut42297, _mut42298), values.length, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42299, _mut42300, _mut42301, _mut42302, _mut42303)) {
            throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(AOR_plus(begin, length, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42304, _mut42305, _mut42306, _mut42307)), Integer.valueOf(values.length), true);
        }
        if ((_mut42313 ? (ROR_equals(length, 0, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42308, _mut42309, _mut42310, _mut42311, _mut42312) || !allowEmpty) : (ROR_equals(length, 0, "org.apache.commons.math3.util.MathArrays.verifyValues_1749", _mut42308, _mut42309, _mut42310, _mut42311, _mut42312) && !allowEmpty))) {
            return false;
        }
        return true;
    }

    /**
     * This method is used
     * to verify that the begin and length parameters designate a subarray of positive length
     * and the weights are all non-negative, non-NaN, finite, and not all zero.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * positive length and the weights array contains legitimate values.</li>
     * <li>throws <code>IllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li></ul>
     * </li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0.
     * </ul></p>
     *
     * @param values the input array
     * @param weights the weights array
     * @param begin index of the first array element to include
     * @param length the number of elements to include
     * @return true if the parameters are valid and designate a subarray of positive length
     * @throws MathIllegalArgumentException if the indices are invalid or the array is null
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length) throws MathIllegalArgumentException {
        return verifyValues(values, weights, begin, length, false);
    }

    /**
     * This method is used
     * to verify that the begin and length parameters designate a subarray of positive length
     * and the weights are all non-negative, non-NaN, finite, and not all zero.
     * <p>
     * <ul>
     * <li>returns <code>true</code> iff the parameters designate a subarray of
     * non-negative length and the weights array contains legitimate values.</li>
     * <li>throws <code>MathIllegalArgumentException</code> if any of the following are true:
     * <ul><li>the values array is null</li>
     *     <li>the weights array is null</li>
     *     <li>the weights array does not have the same length as the values array</li>
     *     <li>the weights array contains one or more infinite values</li>
     *     <li>the weights array contains one or more NaN values</li>
     *     <li>the weights array contains negative values</li>
     *     <li>the start and length arguments do not determine a valid array</li></ul>
     * </li>
     * <li>returns <code>false</li> if the array is non-null, but
     * <code>length</code> is 0 unless <code>allowEmpty</code> is <code>true</code>.
     * </ul></p>
     *
     * @param values the input array.
     * @param weights the weights array.
     * @param begin index of the first array element to include.
     * @param length the number of elements to include.
     * @param allowEmpty if {@code true} than allow zero length arrays to pass.
     * @return {@code true} if the parameters are valid.
     * @throws NullArgumentException if either of the arrays are null
     * @throws MathIllegalArgumentException if the array indices are not valid,
     * the weights array contains NaN, infinite or negative elements, or there
     * are no positive weights.
     * @since 3.3
     */
    public static boolean verifyValues(final double[] values, final double[] weights, final int begin, final int length, final boolean allowEmpty) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.verifyValues_1847");
        if ((_mut42314 ? (weights == null && values == null) : (weights == null || values == null))) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
        }
        checkEqualLength(weights, values);
        boolean containsPositiveWeight = false;
        for (int i = begin; ROR_less(i, AOR_plus(begin, length, "org.apache.commons.math3.util.MathArrays.verifyValues_1847", _mut42326, _mut42327, _mut42328, _mut42329), "org.apache.commons.math3.util.MathArrays.verifyValues_1847", _mut42330, _mut42331, _mut42332, _mut42333, _mut42334); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.verifyValues_1847");
            final double weight = weights[i];
            if (Double.isNaN(weight)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NAN_ELEMENT_AT_INDEX, Integer.valueOf(i));
            }
            if (Double.isInfinite(weight)) {
                throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_ARRAY_ELEMENT, Double.valueOf(weight), Integer.valueOf(i));
            }
            if (ROR_less(weight, 0, "org.apache.commons.math3.util.MathArrays.verifyValues_1847", _mut42315, _mut42316, _mut42317, _mut42318, _mut42319)) {
                throw new MathIllegalArgumentException(LocalizedFormats.NEGATIVE_ELEMENT_AT_INDEX, Integer.valueOf(i), Double.valueOf(weight));
            }
            if ((_mut42325 ? (!containsPositiveWeight || ROR_greater(weight, 0.0, "org.apache.commons.math3.util.MathArrays.verifyValues_1847", _mut42320, _mut42321, _mut42322, _mut42323, _mut42324)) : (!containsPositiveWeight && ROR_greater(weight, 0.0, "org.apache.commons.math3.util.MathArrays.verifyValues_1847", _mut42320, _mut42321, _mut42322, _mut42323, _mut42324)))) {
                containsPositiveWeight = true;
            }
        }
        if (!containsPositiveWeight) {
            throw new MathIllegalArgumentException(LocalizedFormats.WEIGHT_AT_LEAST_ONE_NON_ZERO);
        }
        return verifyValues(values, begin, length, allowEmpty);
    }

    /**
     * Concatenates a sequence of arrays. The return array consists of the
     * entries of the input arrays concatenated in the order they appear in
     * the argument list.  Null arrays cause NullPointerExceptions; zero
     * length arrays are allowed (contributing nothing to the output array).
     *
     * @param x list of double[] arrays to concatenate
     * @return a new array consisting of the entries of the argument arrays
     * @throws NullPointerException if any of the arrays are null
     * @since 3.6
     */
    public static double[] concatenate(double[]... x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.concatenate_1891");
        int combinedLength = 0;
        for (double[] a : x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.concatenate_1891");
            combinedLength += a.length;
        }
        int offset = 0;
        int curLength = 0;
        final double[] combined = new double[combinedLength];
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.util.MathArrays.concatenate_1891", _mut42335, _mut42336, _mut42337, _mut42338, _mut42339); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.concatenate_1891");
            curLength = x[i].length;
            System.arraycopy(x[i], 0, combined, offset, curLength);
            offset += curLength;
        }
        return combined;
    }

    /**
     * Returns an array consisting of the unique values in {@code data}.
     * The return array is sorted in descending order.  Empty arrays
     * are allowed, but null arrays result in NullPointerException.
     * Infinities are allowed.  NaN values are allowed with maximum
     * sort order - i.e., if there are NaN values in {@code data},
     * {@code Double.NaN} will be the first element of the output array,
     * even if the array also contains {@code Double.POSITIVE_INFINITY}.
     *
     * @param data array to scan
     * @return descending list of values included in the input array
     * @throws NullPointerException if data is null
     * @since 3.6
     */
    public static double[] unique(double[] data) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.unique_1921");
        TreeSet<Double> values = new TreeSet<Double>();
        for (int i = 0; ROR_less(i, data.length, "org.apache.commons.math3.util.MathArrays.unique_1921", _mut42340, _mut42341, _mut42342, _mut42343, _mut42344); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.unique_1921");
            values.add(data[i]);
        }
        final int count = values.size();
        final double[] out = new double[count];
        Iterator<Double> iterator = values.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MathArrays.unique_1921");
            out[AOR_minus(count, ++i, "org.apache.commons.math3.util.MathArrays.unique_1921", _mut42345, _mut42346, _mut42347, _mut42348)] = iterator.next();
        }
        return out;
    }
}
