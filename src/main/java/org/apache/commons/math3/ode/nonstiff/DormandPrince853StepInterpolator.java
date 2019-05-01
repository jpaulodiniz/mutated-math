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

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

class DormandPrince853StepInterpolator extends RungeKuttaStepInterpolator {

    @Conditional
    public static boolean _mut19761 = false, _mut19762 = false, _mut19763 = false, _mut19764 = false, _mut19765 = false, _mut19766 = false, _mut19767 = false, _mut19768 = false, _mut19769 = false, _mut19770 = false, _mut19771 = false, _mut19772 = false, _mut19773 = false, _mut19774 = false, _mut19775 = false, _mut19776 = false, _mut19777 = false, _mut19778 = false, _mut19779 = false, _mut19780 = false, _mut19781 = false, _mut19782 = false, _mut19783 = false, _mut19784 = false, _mut19785 = false, _mut19786 = false, _mut19787 = false, _mut19788 = false, _mut19789 = false, _mut19790 = false, _mut19791 = false, _mut19792 = false, _mut19793 = false, _mut19794 = false, _mut19795 = false, _mut19796 = false, _mut19797 = false, _mut19798 = false, _mut19799 = false, _mut19800 = false, _mut19801 = false, _mut19802 = false, _mut19803 = false, _mut19804 = false, _mut19805 = false, _mut19806 = false, _mut19807 = false, _mut19808 = false, _mut19809 = false, _mut19810 = false, _mut19811 = false, _mut19812 = false, _mut19813 = false, _mut19814 = false, _mut19815 = false, _mut19816 = false, _mut19817 = false, _mut19818 = false, _mut19819 = false, _mut19820 = false, _mut19821 = false, _mut19822 = false, _mut19823 = false, _mut19824 = false, _mut19825 = false, _mut19826 = false, _mut19827 = false, _mut19828 = false, _mut19829 = false, _mut19830 = false, _mut19831 = false, _mut19832 = false, _mut19833 = false, _mut19834 = false, _mut19835 = false, _mut19836 = false, _mut19837 = false, _mut19838 = false, _mut19839 = false, _mut19840 = false, _mut19841 = false, _mut19842 = false, _mut19843 = false, _mut19844 = false, _mut19845 = false, _mut19846 = false, _mut19847 = false, _mut19848 = false, _mut19849 = false, _mut19850 = false, _mut19851 = false, _mut19852 = false, _mut19853 = false, _mut19854 = false, _mut19855 = false, _mut19856 = false, _mut19857 = false, _mut19858 = false, _mut19859 = false, _mut19860 = false, _mut19861 = false, _mut19862 = false, _mut19863 = false, _mut19864 = false, _mut19865 = false, _mut19866 = false, _mut19867 = false, _mut19868 = false, _mut19869 = false, _mut19870 = false, _mut19871 = false, _mut19872 = false, _mut19873 = false, _mut19874 = false, _mut19875 = false, _mut19876 = false, _mut19877 = false, _mut19878 = false, _mut19879 = false, _mut19880 = false, _mut19881 = false, _mut19882 = false, _mut19883 = false, _mut19884 = false, _mut19885 = false, _mut19886 = false, _mut19887 = false, _mut19888 = false, _mut19889 = false, _mut19890 = false, _mut19891 = false, _mut19892 = false, _mut19893 = false, _mut19894 = false, _mut19895 = false, _mut19896 = false, _mut19897 = false, _mut19898 = false, _mut19899 = false, _mut19900 = false, _mut19901 = false, _mut19902 = false, _mut19903 = false, _mut19904 = false, _mut19905 = false, _mut19906 = false, _mut19907 = false, _mut19908 = false, _mut19909 = false, _mut19910 = false, _mut19911 = false, _mut19912 = false, _mut19913 = false, _mut19914 = false, _mut19915 = false, _mut19916 = false, _mut19917 = false, _mut19918 = false, _mut19919 = false, _mut19920 = false, _mut19921 = false, _mut19922 = false, _mut19923 = false, _mut19924 = false, _mut19925 = false, _mut19926 = false, _mut19927 = false, _mut19928 = false, _mut19929 = false, _mut19930 = false, _mut19931 = false, _mut19932 = false, _mut19933 = false, _mut19934 = false, _mut19935 = false, _mut19936 = false, _mut19937 = false, _mut19938 = false, _mut19939 = false, _mut19940 = false, _mut19941 = false, _mut19942 = false, _mut19943 = false, _mut19944 = false, _mut19945 = false, _mut19946 = false, _mut19947 = false, _mut19948 = false, _mut19949 = false, _mut19950 = false, _mut19951 = false, _mut19952 = false, _mut19953 = false, _mut19954 = false, _mut19955 = false, _mut19956 = false, _mut19957 = false, _mut19958 = false, _mut19959 = false, _mut19960 = false, _mut19961 = false, _mut19962 = false, _mut19963 = false, _mut19964 = false, _mut19965 = false, _mut19966 = false, _mut19967 = false, _mut19968 = false, _mut19969 = false, _mut19970 = false, _mut19971 = false, _mut19972 = false, _mut19973 = false, _mut19974 = false, _mut19975 = false, _mut19976 = false, _mut19977 = false, _mut19978 = false, _mut19979 = false, _mut19980 = false, _mut19981 = false, _mut19982 = false, _mut19983 = false, _mut19984 = false, _mut19985 = false, _mut19986 = false, _mut19987 = false, _mut19988 = false, _mut19989 = false, _mut19990 = false, _mut19991 = false, _mut19992 = false, _mut19993 = false, _mut19994 = false, _mut19995 = false, _mut19996 = false, _mut19997 = false, _mut19998 = false, _mut19999 = false, _mut20000 = false, _mut20001 = false, _mut20002 = false, _mut20003 = false, _mut20004 = false, _mut20005 = false, _mut20006 = false, _mut20007 = false, _mut20008 = false, _mut20009 = false, _mut20010 = false, _mut20011 = false, _mut20012 = false, _mut20013 = false, _mut20014 = false, _mut20015 = false, _mut20016 = false, _mut20017 = false, _mut20018 = false, _mut20019 = false, _mut20020 = false, _mut20021 = false, _mut20022 = false, _mut20023 = false, _mut20024 = false, _mut20025 = false, _mut20026 = false, _mut20027 = false, _mut20028 = false, _mut20029 = false, _mut20030 = false, _mut20031 = false, _mut20032 = false, _mut20033 = false, _mut20034 = false, _mut20035 = false, _mut20036 = false, _mut20037 = false, _mut20038 = false, _mut20039 = false, _mut20040 = false, _mut20041 = false, _mut20042 = false, _mut20043 = false, _mut20044 = false, _mut20045 = false, _mut20046 = false, _mut20047 = false, _mut20048 = false, _mut20049 = false, _mut20050 = false, _mut20051 = false, _mut20052 = false, _mut20053 = false, _mut20054 = false, _mut20055 = false, _mut20056 = false, _mut20057 = false, _mut20058 = false, _mut20059 = false, _mut20060 = false, _mut20061 = false, _mut20062 = false, _mut20063 = false, _mut20064 = false, _mut20065 = false, _mut20066 = false, _mut20067 = false, _mut20068 = false, _mut20069 = false, _mut20070 = false, _mut20071 = false, _mut20072 = false, _mut20073 = false, _mut20074 = false, _mut20075 = false, _mut20076 = false, _mut20077 = false, _mut20078 = false, _mut20079 = false, _mut20080 = false, _mut20081 = false, _mut20082 = false, _mut20083 = false, _mut20084 = false, _mut20085 = false, _mut20086 = false, _mut20087 = false, _mut20088 = false, _mut20089 = false, _mut20090 = false, _mut20091 = false, _mut20092 = false, _mut20093 = false, _mut20094 = false, _mut20095 = false, _mut20096 = false, _mut20097 = false, _mut20098 = false, _mut20099 = false, _mut20100 = false, _mut20101 = false, _mut20102 = false, _mut20103 = false, _mut20104 = false, _mut20105 = false, _mut20106 = false, _mut20107 = false, _mut20108 = false, _mut20109 = false, _mut20110 = false, _mut20111 = false, _mut20112 = false, _mut20113 = false, _mut20114 = false, _mut20115 = false, _mut20116 = false, _mut20117 = false, _mut20118 = false, _mut20119 = false, _mut20120 = false, _mut20121 = false, _mut20122 = false, _mut20123 = false, _mut20124 = false, _mut20125 = false, _mut20126 = false, _mut20127 = false, _mut20128 = false, _mut20129 = false, _mut20130 = false, _mut20131 = false, _mut20132 = false, _mut20133 = false, _mut20134 = false, _mut20135 = false, _mut20136 = false, _mut20137 = false, _mut20138 = false, _mut20139 = false, _mut20140 = false, _mut20141 = false, _mut20142 = false, _mut20143 = false, _mut20144 = false, _mut20145 = false, _mut20146 = false, _mut20147 = false, _mut20148 = false, _mut20149 = false, _mut20150 = false, _mut20151 = false, _mut20152 = false, _mut20153 = false, _mut20154 = false, _mut20155 = false, _mut20156 = false, _mut20157 = false, _mut20158 = false, _mut20159 = false, _mut20160 = false, _mut20161 = false, _mut20162 = false, _mut20163 = false, _mut20164 = false, _mut20165 = false, _mut20166 = false, _mut20167 = false, _mut20168 = false, _mut20169 = false, _mut20170 = false, _mut20171 = false, _mut20172 = false, _mut20173 = false, _mut20174 = false, _mut20175 = false, _mut20176 = false, _mut20177 = false, _mut20178 = false, _mut20179 = false, _mut20180 = false, _mut20181 = false, _mut20182 = false, _mut20183 = false, _mut20184 = false, _mut20185 = false, _mut20186 = false, _mut20187 = false, _mut20188 = false, _mut20189 = false, _mut20190 = false, _mut20191 = false, _mut20192 = false, _mut20193 = false, _mut20194 = false, _mut20195 = false, _mut20196 = false, _mut20197 = false, _mut20198 = false, _mut20199 = false, _mut20200 = false, _mut20201 = false, _mut20202 = false, _mut20203 = false, _mut20204 = false, _mut20205 = false, _mut20206 = false, _mut20207 = false, _mut20208 = false, _mut20209 = false, _mut20210 = false, _mut20211 = false, _mut20212 = false, _mut20213 = false, _mut20214 = false, _mut20215 = false, _mut20216 = false, _mut20217 = false, _mut20218 = false, _mut20219 = false, _mut20220 = false, _mut20221 = false, _mut20222 = false, _mut20223 = false, _mut20224 = false, _mut20225 = false, _mut20226 = false, _mut20227 = false, _mut20228 = false, _mut20229 = false, _mut20230 = false, _mut20231 = false, _mut20232 = false, _mut20233 = false, _mut20234 = false, _mut20235 = false, _mut20236 = false, _mut20237 = false, _mut20238 = false, _mut20239 = false, _mut20240 = false, _mut20241 = false, _mut20242 = false, _mut20243 = false, _mut20244 = false, _mut20245 = false, _mut20246 = false, _mut20247 = false, _mut20248 = false, _mut20249 = false, _mut20250 = false, _mut20251 = false, _mut20252 = false, _mut20253 = false, _mut20254 = false, _mut20255 = false, _mut20256 = false, _mut20257 = false, _mut20258 = false, _mut20259 = false, _mut20260 = false, _mut20261 = false, _mut20262 = false, _mut20263 = false, _mut20264 = false, _mut20265 = false, _mut20266 = false, _mut20267 = false, _mut20268 = false, _mut20269 = false, _mut20270 = false, _mut20271 = false, _mut20272 = false, _mut20273 = false, _mut20274 = false, _mut20275 = false, _mut20276 = false, _mut20277 = false, _mut20278 = false, _mut20279 = false, _mut20280 = false, _mut20281 = false, _mut20282 = false, _mut20283 = false, _mut20284 = false, _mut20285 = false, _mut20286 = false, _mut20287 = false, _mut20288 = false, _mut20289 = false, _mut20290 = false, _mut20291 = false, _mut20292 = false, _mut20293 = false, _mut20294 = false, _mut20295 = false, _mut20296 = false, _mut20297 = false, _mut20298 = false, _mut20299 = false, _mut20300 = false, _mut20301 = false, _mut20302 = false, _mut20303 = false, _mut20304 = false, _mut20305 = false, _mut20306 = false, _mut20307 = false, _mut20308 = false, _mut20309 = false, _mut20310 = false, _mut20311 = false, _mut20312 = false, _mut20313 = false, _mut20314 = false, _mut20315 = false, _mut20316 = false, _mut20317 = false, _mut20318 = false, _mut20319 = false, _mut20320 = false, _mut20321 = false, _mut20322 = false, _mut20323 = false, _mut20324 = false, _mut20325 = false, _mut20326 = false, _mut20327 = false, _mut20328 = false, _mut20329 = false, _mut20330 = false, _mut20331 = false, _mut20332 = false, _mut20333 = false, _mut20334 = false, _mut20335 = false, _mut20336 = false, _mut20337 = false, _mut20338 = false, _mut20339 = false, _mut20340 = false, _mut20341 = false, _mut20342 = false, _mut20343 = false, _mut20344 = false, _mut20345 = false, _mut20346 = false, _mut20347 = false, _mut20348 = false, _mut20349 = false, _mut20350 = false, _mut20351 = false, _mut20352 = false, _mut20353 = false, _mut20354 = false, _mut20355 = false, _mut20356 = false, _mut20357 = false, _mut20358 = false, _mut20359 = false, _mut20360 = false, _mut20361 = false, _mut20362 = false, _mut20363 = false, _mut20364 = false, _mut20365 = false, _mut20366 = false, _mut20367 = false, _mut20368 = false, _mut20369 = false, _mut20370 = false, _mut20371 = false, _mut20372 = false, _mut20373 = false, _mut20374 = false, _mut20375 = false, _mut20376 = false, _mut20377 = false, _mut20378 = false, _mut20379 = false, _mut20380 = false, _mut20381 = false, _mut20382 = false, _mut20383 = false, _mut20384 = false, _mut20385 = false, _mut20386 = false, _mut20387 = false, _mut20388 = false, _mut20389 = false, _mut20390 = false, _mut20391 = false, _mut20392 = false, _mut20393 = false, _mut20394 = false, _mut20395 = false, _mut20396 = false, _mut20397 = false, _mut20398 = false, _mut20399 = false, _mut20400 = false, _mut20401 = false, _mut20402 = false, _mut20403 = false, _mut20404 = false, _mut20405 = false, _mut20406 = false, _mut20407 = false, _mut20408 = false, _mut20409 = false, _mut20410 = false, _mut20411 = false, _mut20412 = false, _mut20413 = false, _mut20414 = false, _mut20415 = false, _mut20416 = false, _mut20417 = false, _mut20418 = false, _mut20419 = false, _mut20420 = false, _mut20421 = false, _mut20422 = false, _mut20423 = false, _mut20424 = false, _mut20425 = false, _mut20426 = false, _mut20427 = false, _mut20428 = false, _mut20429 = false, _mut20430 = false, _mut20431 = false, _mut20432 = false, _mut20433 = false, _mut20434 = false, _mut20435 = false, _mut20436 = false, _mut20437 = false, _mut20438 = false, _mut20439 = false, _mut20440 = false, _mut20441 = false, _mut20442 = false, _mut20443 = false, _mut20444 = false, _mut20445 = false, _mut20446 = false, _mut20447 = false, _mut20448 = false, _mut20449 = false, _mut20450 = false, _mut20451 = false, _mut20452 = false, _mut20453 = false, _mut20454 = false, _mut20455 = false, _mut20456 = false, _mut20457 = false, _mut20458 = false, _mut20459 = false, _mut20460 = false, _mut20461 = false, _mut20462 = false, _mut20463 = false, _mut20464 = false, _mut20465 = false, _mut20466 = false, _mut20467 = false, _mut20468 = false, _mut20469 = false, _mut20470 = false, _mut20471 = false, _mut20472 = false, _mut20473 = false, _mut20474 = false, _mut20475 = false, _mut20476 = false, _mut20477 = false, _mut20478 = false, _mut20479 = false, _mut20480 = false, _mut20481 = false, _mut20482 = false, _mut20483 = false, _mut20484 = false, _mut20485 = false, _mut20486 = false, _mut20487 = false, _mut20488 = false, _mut20489 = false, _mut20490 = false, _mut20491 = false, _mut20492 = false, _mut20493 = false, _mut20494 = false, _mut20495 = false, _mut20496 = false, _mut20497 = false, _mut20498 = false, _mut20499 = false, _mut20500 = false, _mut20501 = false, _mut20502 = false, _mut20503 = false, _mut20504 = false, _mut20505 = false, _mut20506 = false, _mut20507 = false, _mut20508 = false, _mut20509 = false, _mut20510 = false, _mut20511 = false, _mut20512 = false, _mut20513 = false, _mut20514 = false, _mut20515 = false, _mut20516 = false, _mut20517 = false, _mut20518 = false, _mut20519 = false, _mut20520 = false, _mut20521 = false, _mut20522 = false, _mut20523 = false, _mut20524 = false, _mut20525 = false, _mut20526 = false, _mut20527 = false, _mut20528 = false, _mut20529 = false, _mut20530 = false, _mut20531 = false, _mut20532 = false, _mut20533 = false, _mut20534 = false, _mut20535 = false, _mut20536 = false, _mut20537 = false, _mut20538 = false, _mut20539 = false, _mut20540 = false, _mut20541 = false, _mut20542 = false, _mut20543 = false, _mut20544 = false, _mut20545 = false, _mut20546 = false, _mut20547 = false, _mut20548 = false, _mut20549 = false, _mut20550 = false, _mut20551 = false, _mut20552 = false, _mut20553 = false, _mut20554 = false, _mut20555 = false, _mut20556 = false, _mut20557 = false, _mut20558 = false, _mut20559 = false, _mut20560 = false, _mut20561 = false, _mut20562 = false, _mut20563 = false, _mut20564 = false, _mut20565 = false, _mut20566 = false, _mut20567 = false, _mut20568 = false, _mut20569 = false, _mut20570 = false, _mut20571 = false, _mut20572 = false, _mut20573 = false, _mut20574 = false, _mut20575 = false, _mut20576 = false, _mut20577 = false, _mut20578 = false, _mut20579 = false, _mut20580 = false, _mut20581 = false, _mut20582 = false, _mut20583 = false, _mut20584 = false, _mut20585 = false, _mut20586 = false, _mut20587 = false, _mut20588 = false, _mut20589 = false, _mut20590 = false, _mut20591 = false, _mut20592 = false, _mut20593 = false, _mut20594 = false, _mut20595 = false, _mut20596 = false, _mut20597 = false, _mut20598 = false, _mut20599 = false, _mut20600 = false, _mut20601 = false, _mut20602 = false, _mut20603 = false, _mut20604 = false, _mut20605 = false, _mut20606 = false, _mut20607 = false, _mut20608 = false, _mut20609 = false, _mut20610 = false, _mut20611 = false, _mut20612 = false, _mut20613 = false, _mut20614 = false, _mut20615 = false, _mut20616 = false, _mut20617 = false, _mut20618 = false, _mut20619 = false, _mut20620 = false, _mut20621 = false, _mut20622 = false, _mut20623 = false, _mut20624 = false, _mut20625 = false, _mut20626 = false, _mut20627 = false, _mut20628 = false, _mut20629 = false, _mut20630 = false, _mut20631 = false, _mut20632 = false, _mut20633 = false, _mut20634 = false, _mut20635 = false, _mut20636 = false, _mut20637 = false, _mut20638 = false, _mut20639 = false, _mut20640 = false, _mut20641 = false, _mut20642 = false, _mut20643 = false, _mut20644 = false, _mut20645 = false, _mut20646 = false, _mut20647 = false, _mut20648 = false, _mut20649 = false, _mut20650 = false, _mut20651 = false, _mut20652 = false, _mut20653 = false, _mut20654 = false, _mut20655 = false, _mut20656 = false, _mut20657 = false, _mut20658 = false, _mut20659 = false, _mut20660 = false, _mut20661 = false, _mut20662 = false, _mut20663 = false, _mut20664 = false, _mut20665 = false, _mut20666 = false, _mut20667 = false, _mut20668 = false, _mut20669 = false, _mut20670 = false, _mut20671 = false, _mut20672 = false, _mut20673 = false, _mut20674 = false, _mut20675 = false, _mut20676 = false, _mut20677 = false, _mut20678 = false, _mut20679 = false, _mut20680 = false, _mut20681 = false, _mut20682 = false, _mut20683 = false, _mut20684 = false, _mut20685 = false, _mut20686 = false, _mut20687 = false, _mut20688 = false, _mut20689 = false, _mut20690 = false, _mut20691 = false, _mut20692 = false, _mut20693 = false, _mut20694 = false, _mut20695 = false, _mut20696 = false, _mut20697 = false, _mut20698 = false, _mut20699 = false, _mut20700 = false, _mut20701 = false, _mut20702 = false, _mut20703 = false, _mut20704 = false, _mut20705 = false, _mut20706 = false, _mut20707 = false, _mut20708 = false, _mut20709 = false, _mut20710 = false, _mut20711 = false, _mut20712 = false, _mut20713 = false, _mut20714 = false, _mut20715 = false, _mut20716 = false, _mut20717 = false, _mut20718 = false, _mut20719 = false, _mut20720 = false, _mut20721 = false, _mut20722 = false, _mut20723 = false, _mut20724 = false, _mut20725 = false, _mut20726 = false, _mut20727 = false, _mut20728 = false, _mut20729 = false, _mut20730 = false, _mut20731 = false, _mut20732 = false, _mut20733 = false, _mut20734 = false, _mut20735 = false, _mut20736 = false, _mut20737 = false, _mut20738 = false, _mut20739 = false, _mut20740 = false, _mut20741 = false, _mut20742 = false, _mut20743 = false, _mut20744 = false, _mut20745 = false, _mut20746 = false, _mut20747 = false, _mut20748 = false, _mut20749 = false, _mut20750 = false, _mut20751 = false, _mut20752 = false, _mut20753 = false, _mut20754 = false, _mut20755 = false, _mut20756 = false, _mut20757 = false, _mut20758 = false, _mut20759 = false, _mut20760 = false, _mut20761 = false, _mut20762 = false, _mut20763 = false, _mut20764 = false, _mut20765 = false, _mut20766 = false, _mut20767 = false, _mut20768 = false, _mut20769 = false, _mut20770 = false, _mut20771 = false, _mut20772 = false, _mut20773 = false, _mut20774 = false, _mut20775 = false, _mut20776 = false, _mut20777 = false, _mut20778 = false, _mut20779 = false, _mut20780 = false, _mut20781 = false, _mut20782 = false, _mut20783 = false, _mut20784 = false, _mut20785 = false, _mut20786 = false, _mut20787 = false, _mut20788 = false, _mut20789 = false, _mut20790 = false, _mut20791 = false, _mut20792 = false, _mut20793 = false, _mut20794 = false, _mut20795 = false, _mut20796 = false, _mut20797 = false, _mut20798 = false, _mut20799 = false, _mut20800 = false, _mut20801 = false, _mut20802 = false, _mut20803 = false, _mut20804 = false, _mut20805 = false, _mut20806 = false, _mut20807 = false, _mut20808 = false, _mut20809 = false, _mut20810 = false, _mut20811 = false, _mut20812 = false, _mut20813 = false, _mut20814 = false, _mut20815 = false, _mut20816 = false, _mut20817 = false, _mut20818 = false, _mut20819 = false, _mut20820 = false, _mut20821 = false, _mut20822 = false, _mut20823 = false, _mut20824 = false, _mut20825 = false, _mut20826 = false, _mut20827 = false, _mut20828 = false, _mut20829 = false, _mut20830 = false, _mut20831 = false, _mut20832 = false, _mut20833 = false, _mut20834 = false, _mut20835 = false, _mut20836 = false, _mut20837 = false, _mut20838 = false, _mut20839 = false, _mut20840 = false, _mut20841 = false, _mut20842 = false, _mut20843 = false, _mut20844 = false, _mut20845 = false, _mut20846 = false, _mut20847 = false, _mut20848 = false, _mut20849 = false, _mut20850 = false, _mut20851 = false, _mut20852 = false, _mut20853 = false, _mut20854 = false, _mut20855 = false, _mut20856 = false, _mut20857 = false, _mut20858 = false, _mut20859 = false, _mut20860 = false, _mut20861 = false, _mut20862 = false, _mut20863 = false, _mut20864 = false, _mut20865 = false, _mut20866 = false, _mut20867 = false, _mut20868 = false, _mut20869 = false, _mut20870 = false, _mut20871 = false, _mut20872 = false, _mut20873 = false, _mut20874 = false, _mut20875 = false, _mut20876 = false, _mut20877 = false, _mut20878 = false, _mut20879 = false, _mut20880 = false, _mut20881 = false, _mut20882 = false, _mut20883 = false, _mut20884 = false, _mut20885 = false, _mut20886 = false, _mut20887 = false, _mut20888 = false, _mut20889 = false, _mut20890 = false, _mut20891 = false, _mut20892 = false, _mut20893 = false, _mut20894 = false, _mut20895 = false, _mut20896 = false, _mut20897 = false, _mut20898 = false, _mut20899 = false, _mut20900 = false, _mut20901 = false, _mut20902 = false, _mut20903 = false, _mut20904 = false, _mut20905 = false, _mut20906 = false, _mut20907 = false, _mut20908 = false, _mut20909 = false, _mut20910 = false, _mut20911 = false, _mut20912 = false, _mut20913 = false, _mut20914 = false, _mut20915 = false, _mut20916 = false, _mut20917 = false, _mut20918 = false, _mut20919 = false, _mut20920 = false, _mut20921 = false, _mut20922 = false, _mut20923 = false, _mut20924 = false, _mut20925 = false, _mut20926 = false, _mut20927 = false, _mut20928 = false, _mut20929 = false, _mut20930 = false, _mut20931 = false, _mut20932 = false, _mut20933 = false, _mut20934 = false, _mut20935 = false, _mut20936 = false, _mut20937 = false, _mut20938 = false, _mut20939 = false, _mut20940 = false, _mut20941 = false, _mut20942 = false, _mut20943 = false, _mut20944 = false, _mut20945 = false, _mut20946 = false, _mut20947 = false, _mut20948 = false, _mut20949 = false, _mut20950 = false, _mut20951 = false, _mut20952 = false, _mut20953 = false, _mut20954 = false, _mut20955 = false, _mut20956 = false, _mut20957 = false, _mut20958 = false, _mut20959 = false, _mut20960 = false, _mut20961 = false, _mut20962 = false, _mut20963 = false, _mut20964 = false, _mut20965 = false, _mut20966 = false, _mut20967 = false, _mut20968 = false, _mut20969 = false, _mut20970 = false, _mut20971 = false, _mut20972 = false, _mut20973 = false, _mut20974 = false, _mut20975 = false, _mut20976 = false, _mut20977 = false, _mut20978 = false, _mut20979 = false, _mut20980 = false, _mut20981 = false, _mut20982 = false, _mut20983 = false, _mut20984 = false, _mut20985 = false, _mut20986 = false, _mut20987 = false, _mut20988 = false, _mut20989 = false, _mut20990 = false, _mut20991 = false, _mut20992 = false, _mut20993 = false, _mut20994 = false, _mut20995 = false, _mut20996 = false, _mut20997 = false, _mut20998 = false, _mut20999 = false, _mut21000 = false, _mut21001 = false, _mut21002 = false, _mut21003 = false, _mut21004 = false, _mut21005 = false, _mut21006 = false, _mut21007 = false, _mut21008 = false, _mut21009 = false, _mut21010 = false, _mut21011 = false, _mut21012 = false, _mut21013 = false, _mut21014 = false, _mut21015 = false, _mut21016 = false, _mut21017 = false, _mut21018 = false, _mut21019 = false, _mut21020 = false, _mut21021 = false, _mut21022 = false, _mut21023 = false, _mut21024 = false, _mut21025 = false, _mut21026 = false, _mut21027 = false, _mut21028 = false, _mut21029 = false, _mut21030 = false, _mut21031 = false, _mut21032 = false, _mut21033 = false, _mut21034 = false, _mut21035 = false, _mut21036 = false, _mut21037 = false, _mut21038 = false, _mut21039 = false, _mut21040 = false, _mut21041 = false, _mut21042 = false, _mut21043 = false, _mut21044 = false, _mut21045 = false, _mut21046 = false, _mut21047 = false, _mut21048 = false, _mut21049 = false, _mut21050 = false, _mut21051 = false, _mut21052 = false, _mut21053 = false, _mut21054 = false, _mut21055 = false, _mut21056 = false, _mut21057 = false, _mut21058 = false, _mut21059 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20111120L;

    /**
     * Propagation weights, element 1.
     */
    private static final double B_01 = AOR_divide(104257.0, 1920240.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19761, _mut19762, _mut19763, _mut19764);

    /**
     * Propagation weights, element 6.
     */
    private static final double B_06 = AOR_divide(3399327.0, 763840.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19765, _mut19766, _mut19767, _mut19768);

    /**
     * Propagation weights, element 7.
     */
    private static final double B_07 = AOR_divide(66578432.0, 35198415.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19769, _mut19770, _mut19771, _mut19772);

    /**
     * Propagation weights, element 8.
     */
    private static final double B_08 = AOR_divide(-1674902723.0, 288716400.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19773, _mut19774, _mut19775, _mut19776);

    /**
     * Propagation weights, element 9.
     */
    private static final double B_09 = AOR_divide(54980371265625.0, 176692375811392.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19777, _mut19778, _mut19779, _mut19780);

    /**
     * Propagation weights, element 10.
     */
    private static final double B_10 = AOR_divide(-734375.0, 4826304.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19781, _mut19782, _mut19783, _mut19784);

    /**
     * Propagation weights, element 11.
     */
    private static final double B_11 = AOR_divide(171414593.0, 851261400.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19785, _mut19786, _mut19787, _mut19788);

    /**
     * Propagation weights, element 12.
     */
    private static final double B_12 = AOR_divide(137909.0, 3084480.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19789, _mut19790, _mut19791, _mut19792);

    /**
     * Time step for stage 14 (interpolation only).
     */
    private static final double C14 = AOR_divide(1.0, 10.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19793, _mut19794, _mut19795, _mut19796);

    /**
     * Internal weights for stage 14, element 1.
     */
    private static final double K14_01 = AOR_minus(AOR_divide(13481885573.0, 240030000000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19797, _mut19798, _mut19799, _mut19800), B_01, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19801, _mut19802, _mut19803, _mut19804);

    /**
     * Internal weights for stage 14, element 6.
     */
    private static final double K14_06 = AOR_minus(0.0, B_06, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19805, _mut19806, _mut19807, _mut19808);

    /**
     * Internal weights for stage 14, element 7.
     */
    private static final double K14_07 = AOR_minus(AOR_divide(139418837528.0, 549975234375.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19809, _mut19810, _mut19811, _mut19812), B_07, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19813, _mut19814, _mut19815, _mut19816);

    /**
     * Internal weights for stage 14, element 8.
     */
    private static final double K14_08 = AOR_minus(AOR_divide(-11108320068443.0, 45111937500000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19817, _mut19818, _mut19819, _mut19820), B_08, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19821, _mut19822, _mut19823, _mut19824);

    /**
     * Internal weights for stage 14, element 9.
     */
    private static final double K14_09 = AOR_minus(AOR_divide(-1769651421925959.0, 14249385146080000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19825, _mut19826, _mut19827, _mut19828), B_09, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19829, _mut19830, _mut19831, _mut19832);

    /**
     * Internal weights for stage 14, element 10.
     */
    private static final double K14_10 = AOR_minus(AOR_divide(57799439.0, 377055000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19833, _mut19834, _mut19835, _mut19836), B_10, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19837, _mut19838, _mut19839, _mut19840);

    /**
     * Internal weights for stage 14, element 11.
     */
    private static final double K14_11 = AOR_minus(AOR_divide(793322643029.0, 96734250000000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19841, _mut19842, _mut19843, _mut19844), B_11, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19845, _mut19846, _mut19847, _mut19848);

    /**
     * Internal weights for stage 14, element 12.
     */
    private static final double K14_12 = AOR_minus(AOR_divide(1458939311.0, 192780000000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19849, _mut19850, _mut19851, _mut19852), B_12, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19853, _mut19854, _mut19855, _mut19856);

    /**
     * Internal weights for stage 14, element 13.
     */
    private static final double K14_13 = AOR_divide(-4149.0, 500000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19857, _mut19858, _mut19859, _mut19860);

    /**
     * Time step for stage 15 (interpolation only).
     */
    private static final double C15 = AOR_divide(1.0, 5.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19861, _mut19862, _mut19863, _mut19864);

    /**
     * Internal weights for stage 15, element 1.
     */
    private static final double K15_01 = AOR_minus(AOR_divide(1595561272731.0, 50120273500000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19865, _mut19866, _mut19867, _mut19868), B_01, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19869, _mut19870, _mut19871, _mut19872);

    /**
     * Internal weights for stage 15, element 6.
     */
    private static final double K15_06 = AOR_minus(AOR_divide(975183916491.0, 34457688031250.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19873, _mut19874, _mut19875, _mut19876), B_06, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19877, _mut19878, _mut19879, _mut19880);

    /**
     * Internal weights for stage 15, element 7.
     */
    private static final double K15_07 = AOR_minus(AOR_divide(38492013932672.0, 718912673015625.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19881, _mut19882, _mut19883, _mut19884), B_07, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19885, _mut19886, _mut19887, _mut19888);

    /**
     * Internal weights for stage 15, element 8.
     */
    private static final double K15_08 = AOR_minus(AOR_divide(-1114881286517557.0, 20298710767500000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19889, _mut19890, _mut19891, _mut19892), B_08, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19893, _mut19894, _mut19895, _mut19896);

    /**
     * Internal weights for stage 15, element 9.
     */
    private static final double K15_09 = AOR_minus(0.0, B_09, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19897, _mut19898, _mut19899, _mut19900);

    /**
     * Internal weights for stage 15, element 10.
     */
    private static final double K15_10 = AOR_minus(0.0, B_10, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19901, _mut19902, _mut19903, _mut19904);

    /**
     * Internal weights for stage 15, element 11.
     */
    private static final double K15_11 = AOR_minus(AOR_divide(-2538710946863.0, 23431227861250000.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19905, _mut19906, _mut19907, _mut19908), B_11, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19909, _mut19910, _mut19911, _mut19912);

    /**
     * Internal weights for stage 15, element 12.
     */
    private static final double K15_12 = AOR_minus(AOR_divide(8824659001.0, 23066716781250.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19913, _mut19914, _mut19915, _mut19916), B_12, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19917, _mut19918, _mut19919, _mut19920);

    /**
     * Internal weights for stage 15, element 13.
     */
    private static final double K15_13 = AOR_divide(-11518334563.0, 33831184612500.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19921, _mut19922, _mut19923, _mut19924);

    /**
     * Internal weights for stage 15, element 14.
     */
    private static final double K15_14 = AOR_divide(1912306948.0, 13532473845.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19925, _mut19926, _mut19927, _mut19928);

    /**
     * Time step for stage 16 (interpolation only).
     */
    private static final double C16 = AOR_divide(7.0, 9.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19929, _mut19930, _mut19931, _mut19932);

    /**
     * Internal weights for stage 16, element 1.
     */
    private static final double K16_01 = AOR_minus(AOR_divide(-13613986967.0, 31741908048.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19933, _mut19934, _mut19935, _mut19936), B_01, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19937, _mut19938, _mut19939, _mut19940);

    /**
     * Internal weights for stage 16, element 6.
     */
    private static final double K16_06 = AOR_minus(AOR_divide(-4755612631.0, 1012344804.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19941, _mut19942, _mut19943, _mut19944), B_06, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19945, _mut19946, _mut19947, _mut19948);

    /**
     * Internal weights for stage 16, element 7.
     */
    private static final double K16_07 = AOR_minus(AOR_divide(42939257944576.0, 5588559685701.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19949, _mut19950, _mut19951, _mut19952), B_07, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19953, _mut19954, _mut19955, _mut19956);

    /**
     * Internal weights for stage 16, element 8.
     */
    private static final double K16_08 = AOR_minus(AOR_divide(77881972900277.0, 19140370552944.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19957, _mut19958, _mut19959, _mut19960), B_08, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19961, _mut19962, _mut19963, _mut19964);

    /**
     * Internal weights for stage 16, element 9.
     */
    private static final double K16_09 = AOR_minus(AOR_divide(22719829234375.0, 63689648654052.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19965, _mut19966, _mut19967, _mut19968), B_09, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19969, _mut19970, _mut19971, _mut19972);

    /**
     * Internal weights for stage 16, element 10.
     */
    private static final double K16_10 = AOR_minus(0.0, B_10, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19973, _mut19974, _mut19975, _mut19976);

    /**
     * Internal weights for stage 16, element 11.
     */
    private static final double K16_11 = AOR_minus(0.0, B_11, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19977, _mut19978, _mut19979, _mut19980);

    /**
     * Internal weights for stage 16, element 12.
     */
    private static final double K16_12 = AOR_minus(0.0, B_12, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19981, _mut19982, _mut19983, _mut19984);

    /**
     * Internal weights for stage 16, element 13.
     */
    private static final double K16_13 = AOR_divide(-1199007803.0, 857031517296.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19985, _mut19986, _mut19987, _mut19988);

    /**
     * Internal weights for stage 16, element 14.
     */
    private static final double K16_14 = AOR_divide(157882067000.0, 53564469831.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19989, _mut19990, _mut19991, _mut19992);

    /**
     * Internal weights for stage 16, element 15.
     */
    private static final double K16_15 = AOR_divide(-290468882375.0, 31741908048.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19993, _mut19994, _mut19995, _mut19996);

    /**
     * Interpolation weights.
     * (beware that only the non-null values are in the table)
     */
    private static final double[][] D = { { AOR_divide(-17751989329.0, 2106076560.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut19997, _mut19998, _mut19999, _mut20000), AOR_divide(4272954039.0, 7539864640.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20001, _mut20002, _mut20003, _mut20004), AOR_divide(-118476319744.0, 38604839385.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20005, _mut20006, _mut20007, _mut20008), AOR_divide(755123450731.0, 316657731600.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20009, _mut20010, _mut20011, _mut20012), AOR_divide(3692384461234828125.0, 1744130441634250432.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20013, _mut20014, _mut20015, _mut20016), AOR_divide(-4612609375.0, 5293382976.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20017, _mut20018, _mut20019, _mut20020), AOR_divide(2091772278379.0, 933644586600.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20021, _mut20022, _mut20023, _mut20024), AOR_divide(2136624137.0, 3382989120.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20025, _mut20026, _mut20027, _mut20028), AOR_divide(-126493.0, 1421424.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20029, _mut20030, _mut20031, _mut20032), AOR_divide(98350000.0, 5419179.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20033, _mut20034, _mut20035, _mut20036), AOR_divide(-18878125.0, 2053168.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20037, _mut20038, _mut20039, _mut20040), AOR_divide(-1944542619.0, 438351368.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20041, _mut20042, _mut20043, _mut20044) }, { AOR_divide(32941697297.0, 3159114840.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20045, _mut20046, _mut20047, _mut20048), AOR_divide(456696183123.0, 1884966160.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20049, _mut20050, _mut20051, _mut20052), AOR_divide(19132610714624.0, 115814518155.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20053, _mut20054, _mut20055, _mut20056), AOR_divide(-177904688592943.0, 474986597400.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20057, _mut20058, _mut20059, _mut20060), AOR_divide(-4821139941836765625.0, 218016305204281304.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20061, _mut20062, _mut20063, _mut20064), AOR_divide(30702015625.0, 3970037232.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20065, _mut20066, _mut20067, _mut20068), AOR_divide(-85916079474274.0, 2800933759800.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20069, _mut20070, _mut20071, _mut20072), AOR_divide(-5919468007.0, 634310460.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20073, _mut20074, _mut20075, _mut20076), AOR_divide(2479159.0, 157936.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20077, _mut20078, _mut20079, _mut20080), AOR_divide(-18750000.0, 602131.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20081, _mut20082, _mut20083, _mut20084), AOR_divide(-19203125.0, 2053168.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20085, _mut20086, _mut20087, _mut20088), AOR_divide(15700361463.0, 438351368.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20089, _mut20090, _mut20091, _mut20092) }, { AOR_divide(12627015655.0, 631822968.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20093, _mut20094, _mut20095, _mut20096), AOR_divide(-72955222965.0, 188496616.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20097, _mut20098, _mut20099, _mut20100), AOR_divide(-13145744952320.0, 69488710893.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20101, _mut20102, _mut20103, _mut20104), AOR_divide(30084216194513.0, 56998391688.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20105, _mut20106, _mut20107, _mut20108), AOR_divide(-296858761006640625.0, 25648977082856624.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20109, _mut20110, _mut20111, _mut20112), AOR_divide(569140625.0, 82709109.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20113, _mut20114, _mut20115, _mut20116), AOR_divide(-18684190637.0, 18672891732.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20117, _mut20118, _mut20119, _mut20120), AOR_divide(69644045.0, 89549712.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20121, _mut20122, _mut20123, _mut20124), AOR_divide(-11847025.0, 4264272.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20125, _mut20126, _mut20127, _mut20128), AOR_divide(-978650000.0, 16257537.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20129, _mut20130, _mut20131, _mut20132), AOR_divide(519371875.0, 6159504.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20133, _mut20134, _mut20135, _mut20136), AOR_divide(5256837225.0, 438351368.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20137, _mut20138, _mut20139, _mut20140) }, { AOR_divide(-450944925.0, 17550638.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20141, _mut20142, _mut20143, _mut20144), AOR_divide(-14532122925.0, 94248308.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20145, _mut20146, _mut20147, _mut20148), AOR_divide(-595876966400.0, 2573655959.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20149, _mut20150, _mut20151, _mut20152), AOR_divide(188748653015.0, 527762886.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20153, _mut20154, _mut20155, _mut20156), AOR_divide(2545485458115234375.0, 27252038150535163.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20157, _mut20158, _mut20159, _mut20160), AOR_divide(-1376953125.0, 36759604.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20161, _mut20162, _mut20163, _mut20164), AOR_divide(53995596795.0, 518691437.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20165, _mut20166, _mut20167, _mut20168), AOR_divide(210311225.0, 7047894.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20169, _mut20170, _mut20171, _mut20172), AOR_divide(-1718875.0, 39484.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20173, _mut20174, _mut20175, _mut20176), AOR_divide(58000000.0, 602131.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20177, _mut20178, _mut20179, _mut20180), AOR_divide(-1546875.0, 39484.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20181, _mut20182, _mut20183, _mut20184), AOR_divide(-1262172375.0, 8429834.0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.singleStep_225", _mut20185, _mut20186, _mut20187, _mut20188) } };

    /**
     * Last evaluations.
     */
    private double[][] yDotKLast;

    /**
     * Vectors for interpolation.
     */
    private double[][] v;

    /**
     * Initialization indicator for the interpolation vectors.
     */
    private boolean vectorsInitialized;

    // the public modifier here is needed for serialization
    public DormandPrince853StepInterpolator() {
        super();
        yDotKLast = null;
        v = null;
        vectorsInitialized = false;
    }

    /**
     * Copy constructor.
     * @param interpolator interpolator to copy from. The copy is a deep
     * copy: its arrays are separated from the original arrays of the
     * instance
     */
    DormandPrince853StepInterpolator(final DormandPrince853StepInterpolator interpolator) {
        super(interpolator);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.DormandPrince853StepInterpolator_245");
        if (interpolator.currentState == null) {
            yDotKLast = null;
            v = null;
            vectorsInitialized = false;
        } else {
            final int dimension = interpolator.currentState.length;
            yDotKLast = new double[3][];
            for (int k = 0; ROR_less(k, yDotKLast.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.DormandPrince853StepInterpolator_245", _mut20189, _mut20190, _mut20191, _mut20192, _mut20193); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.DormandPrince853StepInterpolator_245");
                yDotKLast[k] = new double[dimension];
                System.arraycopy(interpolator.yDotKLast[k], 0, yDotKLast[k], 0, dimension);
            }
            v = new double[7][];
            for (int k = 0; ROR_less(k, v.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.DormandPrince853StepInterpolator_245", _mut20194, _mut20195, _mut20196, _mut20197, _mut20198); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.DormandPrince853StepInterpolator_245");
                v[k] = new double[dimension];
                System.arraycopy(interpolator.v[k], 0, v[k], 0, dimension);
            }
            vectorsInitialized = interpolator.vectorsInitialized;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected StepInterpolator doCopy() {
        return new DormandPrince853StepInterpolator(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reinitialize(final AbstractIntegrator integrator, final double[] y, final double[][] yDotK, final boolean forward, final EquationsMapper primaryMapper, final EquationsMapper[] secondaryMappers) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.reinitialize_285");
        super.reinitialize(integrator, y, yDotK, forward, primaryMapper, secondaryMappers);
        final int dimension = currentState.length;
        yDotKLast = new double[3][];
        for (int k = 0; ROR_less(k, yDotKLast.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.reinitialize_285", _mut20199, _mut20200, _mut20201, _mut20202, _mut20203); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.reinitialize_285");
            yDotKLast[k] = new double[dimension];
        }
        v = new double[7][];
        for (int k = 0; ROR_less(k, v.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.reinitialize_285", _mut20204, _mut20205, _mut20206, _mut20207, _mut20208); ++k) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.reinitialize_285");
            v[k] = new double[dimension];
        }
        vectorsInitialized = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeTime(final double t) {
        super.storeTime(t);
        vectorsInitialized = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void computeInterpolatedStateAndDerivatives(final double theta, final double oneMinusThetaH) throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
        if (!vectorsInitialized) {
            if (v == null) {
                v = new double[7][];
                for (int k = 0; ROR_less(k, 7, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20209, _mut20210, _mut20211, _mut20212, _mut20213); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
                    v[k] = new double[interpolatedState.length];
                }
            }
            // perform the last evaluations if they have not been done yet
            finalizeStep();
            // compute the interpolation vectors for this time step
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20387, _mut20388, _mut20389, _mut20390, _mut20391); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
                final double yDot1 = yDotK[0][i];
                final double yDot6 = yDotK[5][i];
                final double yDot7 = yDotK[6][i];
                final double yDot8 = yDotK[7][i];
                final double yDot9 = yDotK[8][i];
                final double yDot10 = yDotK[9][i];
                final double yDot11 = yDotK[10][i];
                final double yDot12 = yDotK[11][i];
                final double yDot13 = yDotK[12][i];
                final double yDot14 = yDotKLast[0][i];
                final double yDot15 = yDotKLast[1][i];
                final double yDot16 = yDotKLast[2][i];
                v[0][i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(B_01, yDot1, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20214, _mut20215, _mut20216, _mut20217), AOR_multiply(B_06, yDot6, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20218, _mut20219, _mut20220, _mut20221), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20222, _mut20223, _mut20224, _mut20225), AOR_multiply(B_07, yDot7, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20226, _mut20227, _mut20228, _mut20229), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20230, _mut20231, _mut20232, _mut20233), AOR_multiply(B_08, yDot8, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20234, _mut20235, _mut20236, _mut20237), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20238, _mut20239, _mut20240, _mut20241), AOR_multiply(B_09, yDot9, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20242, _mut20243, _mut20244, _mut20245), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20246, _mut20247, _mut20248, _mut20249), AOR_multiply(B_10, yDot10, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20250, _mut20251, _mut20252, _mut20253), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20254, _mut20255, _mut20256, _mut20257), AOR_multiply(B_11, yDot11, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20258, _mut20259, _mut20260, _mut20261), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20262, _mut20263, _mut20264, _mut20265), AOR_multiply(B_12, yDot12, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20266, _mut20267, _mut20268, _mut20269), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20270, _mut20271, _mut20272, _mut20273);
                v[1][i] = AOR_minus(yDot1, v[0][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20274, _mut20275, _mut20276, _mut20277);
                v[2][i] = AOR_minus(AOR_minus(v[0][i], v[1][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20278, _mut20279, _mut20280, _mut20281), yDotK[12][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20282, _mut20283, _mut20284, _mut20285);
                for (int k = 0; ROR_less(k, D.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20382, _mut20383, _mut20384, _mut20385, _mut20386); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
                    v[AOR_plus(k, 3, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20286, _mut20287, _mut20288, _mut20289)][i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(D[k][0], yDot1, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20290, _mut20291, _mut20292, _mut20293), AOR_multiply(D[k][1], yDot6, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20294, _mut20295, _mut20296, _mut20297), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20298, _mut20299, _mut20300, _mut20301), AOR_multiply(D[k][2], yDot7, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20302, _mut20303, _mut20304, _mut20305), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20306, _mut20307, _mut20308, _mut20309), AOR_multiply(D[k][3], yDot8, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20310, _mut20311, _mut20312, _mut20313), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20314, _mut20315, _mut20316, _mut20317), AOR_multiply(D[k][4], yDot9, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20318, _mut20319, _mut20320, _mut20321), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20322, _mut20323, _mut20324, _mut20325), AOR_multiply(D[k][5], yDot10, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20326, _mut20327, _mut20328, _mut20329), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20330, _mut20331, _mut20332, _mut20333), AOR_multiply(D[k][6], yDot11, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20334, _mut20335, _mut20336, _mut20337), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20338, _mut20339, _mut20340, _mut20341), AOR_multiply(D[k][7], yDot12, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20342, _mut20343, _mut20344, _mut20345), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20346, _mut20347, _mut20348, _mut20349), AOR_multiply(D[k][8], yDot13, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20350, _mut20351, _mut20352, _mut20353), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20354, _mut20355, _mut20356, _mut20357), AOR_multiply(D[k][9], yDot14, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20358, _mut20359, _mut20360, _mut20361), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20362, _mut20363, _mut20364, _mut20365), AOR_multiply(D[k][10], yDot15, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20366, _mut20367, _mut20368, _mut20369), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20370, _mut20371, _mut20372, _mut20373), AOR_multiply(D[k][11], yDot16, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20374, _mut20375, _mut20376, _mut20377), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20378, _mut20379, _mut20380, _mut20381);
                }
            }
            vectorsInitialized = true;
        }
        final double eta = AOR_minus(1, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20392, _mut20393, _mut20394, _mut20395);
        final double twoTheta = AOR_multiply(2, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20396, _mut20397, _mut20398, _mut20399);
        final double theta2 = AOR_multiply(theta, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20400, _mut20401, _mut20402, _mut20403);
        final double dot1 = AOR_minus(1, twoTheta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20404, _mut20405, _mut20406, _mut20407);
        final double dot2 = AOR_multiply(theta, (AOR_minus(2, AOR_multiply(3, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20408, _mut20409, _mut20410, _mut20411), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20412, _mut20413, _mut20414, _mut20415)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20416, _mut20417, _mut20418, _mut20419);
        final double dot3 = AOR_multiply(twoTheta, (AOR_plus(1, AOR_multiply(theta, (AOR_minus(twoTheta, 3, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20420, _mut20421, _mut20422, _mut20423)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20424, _mut20425, _mut20426, _mut20427), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20428, _mut20429, _mut20430, _mut20431)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20432, _mut20433, _mut20434, _mut20435);
        final double dot4 = AOR_multiply(theta2, (AOR_plus(3, AOR_multiply(theta, (AOR_minus(AOR_multiply(5, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20436, _mut20437, _mut20438, _mut20439), 8, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20440, _mut20441, _mut20442, _mut20443)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20444, _mut20445, _mut20446, _mut20447), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20448, _mut20449, _mut20450, _mut20451)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20452, _mut20453, _mut20454, _mut20455);
        final double dot5 = AOR_multiply(theta2, (AOR_plus(3, AOR_multiply(theta, (AOR_plus(-12, AOR_multiply(theta, (AOR_minus(15, AOR_multiply(6, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20456, _mut20457, _mut20458, _mut20459), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20460, _mut20461, _mut20462, _mut20463)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20464, _mut20465, _mut20466, _mut20467), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20468, _mut20469, _mut20470, _mut20471)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20472, _mut20473, _mut20474, _mut20475), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20476, _mut20477, _mut20478, _mut20479)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20480, _mut20481, _mut20482, _mut20483);
        final double dot6 = AOR_multiply(AOR_multiply(theta2, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20484, _mut20485, _mut20486, _mut20487), (AOR_plus(4, AOR_multiply(theta, (AOR_plus(-15, AOR_multiply(theta, (AOR_minus(18, AOR_multiply(7, theta, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20488, _mut20489, _mut20490, _mut20491), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20492, _mut20493, _mut20494, _mut20495)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20496, _mut20497, _mut20498, _mut20499), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20500, _mut20501, _mut20502, _mut20503)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20504, _mut20505, _mut20506, _mut20507), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20508, _mut20509, _mut20510, _mut20511)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20512, _mut20513, _mut20514, _mut20515);
        if ((_mut20521 ? ((previousState != null) || (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20516, _mut20517, _mut20518, _mut20519, _mut20520))) : ((previousState != null) && (ROR_less_equals(theta, 0.5, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20516, _mut20517, _mut20518, _mut20519, _mut20520))))) {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20739, _mut20740, _mut20741, _mut20742, _mut20743); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
                interpolatedState[i] = AOR_plus(previousState[i], AOR_multiply(AOR_multiply(theta, h, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20631, _mut20632, _mut20633, _mut20634), (AOR_plus(v[0][i], AOR_multiply(eta, (AOR_plus(v[1][i], AOR_multiply(theta, (AOR_plus(v[2][i], AOR_multiply(eta, (AOR_plus(v[3][i], AOR_multiply(theta, (AOR_plus(v[4][i], AOR_multiply(eta, (AOR_plus(v[5][i], AOR_multiply(theta, (v[6][i]), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20635, _mut20636, _mut20637, _mut20638), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20639, _mut20640, _mut20641, _mut20642)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20643, _mut20644, _mut20645, _mut20646), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20647, _mut20648, _mut20649, _mut20650)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20651, _mut20652, _mut20653, _mut20654), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20655, _mut20656, _mut20657, _mut20658)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20659, _mut20660, _mut20661, _mut20662), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20663, _mut20664, _mut20665, _mut20666)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20667, _mut20668, _mut20669, _mut20670), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20671, _mut20672, _mut20673, _mut20674)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20675, _mut20676, _mut20677, _mut20678), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20679, _mut20680, _mut20681, _mut20682)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20683, _mut20684, _mut20685, _mut20686), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20687, _mut20688, _mut20689, _mut20690);
                interpolatedDerivatives[i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(v[0][i], AOR_multiply(dot1, v[1][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20691, _mut20692, _mut20693, _mut20694), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20695, _mut20696, _mut20697, _mut20698), AOR_multiply(dot2, v[2][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20699, _mut20700, _mut20701, _mut20702), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20703, _mut20704, _mut20705, _mut20706), AOR_multiply(dot3, v[3][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20707, _mut20708, _mut20709, _mut20710), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20711, _mut20712, _mut20713, _mut20714), AOR_multiply(dot4, v[4][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20715, _mut20716, _mut20717, _mut20718), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20719, _mut20720, _mut20721, _mut20722), AOR_multiply(dot5, v[5][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20723, _mut20724, _mut20725, _mut20726), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20727, _mut20728, _mut20729, _mut20730), AOR_multiply(dot6, v[6][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20731, _mut20732, _mut20733, _mut20734), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20735, _mut20736, _mut20737, _mut20738);
            }
        } else {
            for (int i = 0; ROR_less(i, interpolatedState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20626, _mut20627, _mut20628, _mut20629, _mut20630); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317");
                interpolatedState[i] = AOR_minus(currentState[i], AOR_multiply(oneMinusThetaH, (AOR_minus(v[0][i], AOR_multiply(theta, (AOR_plus(v[1][i], AOR_multiply(theta, (AOR_plus(v[2][i], AOR_multiply(eta, (AOR_plus(v[3][i], AOR_multiply(theta, (AOR_plus(v[4][i], AOR_multiply(eta, (AOR_plus(v[5][i], AOR_multiply(theta, (v[6][i]), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20522, _mut20523, _mut20524, _mut20525), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20526, _mut20527, _mut20528, _mut20529)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20530, _mut20531, _mut20532, _mut20533), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20534, _mut20535, _mut20536, _mut20537)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20538, _mut20539, _mut20540, _mut20541), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20542, _mut20543, _mut20544, _mut20545)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20546, _mut20547, _mut20548, _mut20549), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20550, _mut20551, _mut20552, _mut20553)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20554, _mut20555, _mut20556, _mut20557), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20558, _mut20559, _mut20560, _mut20561)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20562, _mut20563, _mut20564, _mut20565), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20566, _mut20567, _mut20568, _mut20569)), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20570, _mut20571, _mut20572, _mut20573), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20574, _mut20575, _mut20576, _mut20577);
                interpolatedDerivatives[i] = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(v[0][i], AOR_multiply(dot1, v[1][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20578, _mut20579, _mut20580, _mut20581), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20582, _mut20583, _mut20584, _mut20585), AOR_multiply(dot2, v[2][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20586, _mut20587, _mut20588, _mut20589), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20590, _mut20591, _mut20592, _mut20593), AOR_multiply(dot3, v[3][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20594, _mut20595, _mut20596, _mut20597), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20598, _mut20599, _mut20600, _mut20601), AOR_multiply(dot4, v[4][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20602, _mut20603, _mut20604, _mut20605), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20606, _mut20607, _mut20608, _mut20609), AOR_multiply(dot5, v[5][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20610, _mut20611, _mut20612, _mut20613), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20614, _mut20615, _mut20616, _mut20617), AOR_multiply(dot6, v[6][i], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20618, _mut20619, _mut20620, _mut20621), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.computeInterpolatedStateAndDerivatives_317", _mut20622, _mut20623, _mut20624, _mut20625);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFinalize() throws MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408");
        if (currentState == null) {
            // we are finalizing an uninitialized instance
            return;
        }
        double s;
        final double[] yTmp = new double[currentState.length];
        final double pT = getGlobalPreviousTime();
        // k14
        for (int j = 0; ROR_less(j, currentState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20820, _mut20821, _mut20822, _mut20823, _mut20824); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408");
            s = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(K14_01, yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20744, _mut20745, _mut20746, _mut20747), AOR_multiply(K14_06, yDotK[5][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20748, _mut20749, _mut20750, _mut20751), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20752, _mut20753, _mut20754, _mut20755), AOR_multiply(K14_07, yDotK[6][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20756, _mut20757, _mut20758, _mut20759), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20760, _mut20761, _mut20762, _mut20763), AOR_multiply(K14_08, yDotK[7][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20764, _mut20765, _mut20766, _mut20767), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20768, _mut20769, _mut20770, _mut20771), AOR_multiply(K14_09, yDotK[8][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20772, _mut20773, _mut20774, _mut20775), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20776, _mut20777, _mut20778, _mut20779), AOR_multiply(K14_10, yDotK[9][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20780, _mut20781, _mut20782, _mut20783), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20784, _mut20785, _mut20786, _mut20787), AOR_multiply(K14_11, yDotK[10][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20788, _mut20789, _mut20790, _mut20791), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20792, _mut20793, _mut20794, _mut20795), AOR_multiply(K14_12, yDotK[11][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20796, _mut20797, _mut20798, _mut20799), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20800, _mut20801, _mut20802, _mut20803), AOR_multiply(K14_13, yDotK[12][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20804, _mut20805, _mut20806, _mut20807), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20808, _mut20809, _mut20810, _mut20811);
            yTmp[j] = AOR_plus(currentState[j], AOR_multiply(h, s, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20812, _mut20813, _mut20814, _mut20815), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20816, _mut20817, _mut20818, _mut20819);
        }
        integrator.computeDerivatives(AOR_plus(pT, AOR_multiply(C14, h, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20825, _mut20826, _mut20827, _mut20828), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20829, _mut20830, _mut20831, _mut20832), yTmp, yDotKLast[0]);
        // k15
        for (int j = 0; ROR_less(j, currentState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20917, _mut20918, _mut20919, _mut20920, _mut20921); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408");
            s = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(K15_01, yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20833, _mut20834, _mut20835, _mut20836), AOR_multiply(K15_06, yDotK[5][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20837, _mut20838, _mut20839, _mut20840), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20841, _mut20842, _mut20843, _mut20844), AOR_multiply(K15_07, yDotK[6][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20845, _mut20846, _mut20847, _mut20848), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20849, _mut20850, _mut20851, _mut20852), AOR_multiply(K15_08, yDotK[7][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20853, _mut20854, _mut20855, _mut20856), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20857, _mut20858, _mut20859, _mut20860), AOR_multiply(K15_09, yDotK[8][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20861, _mut20862, _mut20863, _mut20864), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20865, _mut20866, _mut20867, _mut20868), AOR_multiply(K15_10, yDotK[9][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20869, _mut20870, _mut20871, _mut20872), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20873, _mut20874, _mut20875, _mut20876), AOR_multiply(K15_11, yDotK[10][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20877, _mut20878, _mut20879, _mut20880), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20881, _mut20882, _mut20883, _mut20884), AOR_multiply(K15_12, yDotK[11][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20885, _mut20886, _mut20887, _mut20888), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20889, _mut20890, _mut20891, _mut20892), AOR_multiply(K15_13, yDotK[12][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20893, _mut20894, _mut20895, _mut20896), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20897, _mut20898, _mut20899, _mut20900), AOR_multiply(K15_14, yDotKLast[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20901, _mut20902, _mut20903, _mut20904), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20905, _mut20906, _mut20907, _mut20908);
            yTmp[j] = AOR_plus(currentState[j], AOR_multiply(h, s, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20909, _mut20910, _mut20911, _mut20912), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20913, _mut20914, _mut20915, _mut20916);
        }
        integrator.computeDerivatives(AOR_plus(pT, AOR_multiply(C15, h, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20922, _mut20923, _mut20924, _mut20925), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20926, _mut20927, _mut20928, _mut20929), yTmp, yDotKLast[1]);
        // k16
        for (int j = 0; ROR_less(j, currentState.length, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21022, _mut21023, _mut21024, _mut21025, _mut21026); ++j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408");
            s = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(K16_01, yDotK[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20930, _mut20931, _mut20932, _mut20933), AOR_multiply(K16_06, yDotK[5][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20934, _mut20935, _mut20936, _mut20937), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20938, _mut20939, _mut20940, _mut20941), AOR_multiply(K16_07, yDotK[6][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20942, _mut20943, _mut20944, _mut20945), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20946, _mut20947, _mut20948, _mut20949), AOR_multiply(K16_08, yDotK[7][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20950, _mut20951, _mut20952, _mut20953), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20954, _mut20955, _mut20956, _mut20957), AOR_multiply(K16_09, yDotK[8][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20958, _mut20959, _mut20960, _mut20961), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20962, _mut20963, _mut20964, _mut20965), AOR_multiply(K16_10, yDotK[9][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20966, _mut20967, _mut20968, _mut20969), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20970, _mut20971, _mut20972, _mut20973), AOR_multiply(K16_11, yDotK[10][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20974, _mut20975, _mut20976, _mut20977), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20978, _mut20979, _mut20980, _mut20981), AOR_multiply(K16_12, yDotK[11][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20982, _mut20983, _mut20984, _mut20985), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20986, _mut20987, _mut20988, _mut20989), AOR_multiply(K16_13, yDotK[12][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20990, _mut20991, _mut20992, _mut20993), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20994, _mut20995, _mut20996, _mut20997), AOR_multiply(K16_14, yDotKLast[0][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut20998, _mut20999, _mut21000, _mut21001), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21002, _mut21003, _mut21004, _mut21005), AOR_multiply(K16_15, yDotKLast[1][j], "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21006, _mut21007, _mut21008, _mut21009), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21010, _mut21011, _mut21012, _mut21013);
            yTmp[j] = AOR_plus(currentState[j], AOR_multiply(h, s, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21014, _mut21015, _mut21016, _mut21017), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21018, _mut21019, _mut21020, _mut21021);
        }
        integrator.computeDerivatives(AOR_plus(pT, AOR_multiply(C16, h, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21027, _mut21028, _mut21029, _mut21030), "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.doFinalize_408", _mut21031, _mut21032, _mut21033, _mut21034), yTmp, yDotKLast[2]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.writeExternal_452");
        try {
            // save the local attributes
            finalizeStep();
        } catch (MaxCountExceededException mcee) {
            final IOException ioe = new IOException(mcee.getLocalizedMessage());
            ioe.initCause(mcee);
            throw ioe;
        }
        final int dimension = (currentState == null) ? -1 : currentState.length;
        out.writeInt(dimension);
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.writeExternal_452", _mut21035, _mut21036, _mut21037, _mut21038, _mut21039); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.writeExternal_452");
            out.writeDouble(yDotKLast[0][i]);
            out.writeDouble(yDotKLast[1][i]);
            out.writeDouble(yDotKLast[2][i]);
        }
        // save the state of the base class
        super.writeExternal(out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479");
        // read the local attributes
        yDotKLast = new double[3][];
        final int dimension = in.readInt();
        yDotKLast[0] = (ROR_less(dimension, 0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479", _mut21040, _mut21041, _mut21042, _mut21043, _mut21044)) ? null : new double[dimension];
        yDotKLast[1] = (ROR_less(dimension, 0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479", _mut21045, _mut21046, _mut21047, _mut21048, _mut21049)) ? null : new double[dimension];
        yDotKLast[2] = (ROR_less(dimension, 0, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479", _mut21050, _mut21051, _mut21052, _mut21053, _mut21054)) ? null : new double[dimension];
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479", _mut21055, _mut21056, _mut21057, _mut21058, _mut21059); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ode.nonstiff.DormandPrince853StepInterpolator.readExternal_479");
            yDotKLast[0][i] = in.readDouble();
            yDotKLast[1][i] = in.readDouble();
            yDotKLast[2][i] = in.readDouble();
        }
        // read the base state
        super.readExternal(in);
    }
}
