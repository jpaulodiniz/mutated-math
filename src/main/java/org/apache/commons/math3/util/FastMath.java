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

import java.io.PrintStream;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Faster, more accurate, portable alternative to {@link Math} and
 * {@link StrictMath} for large scale computation.
 * <p>
 * FastMath is a drop-in replacement for both Math and StrictMath. This
 * means that for any method in Math (say {@code Math.sin(x)} or
 * {@code Math.cbrt(y)}), user can directly change the class and use the
 * methods as is (using {@code FastMath.sin(x)} or {@code FastMath.cbrt(y)}
 * in the previous example).
 * </p>
 * <p>
 * FastMath speed is achieved by relying heavily on optimizing compilers
 * to native code present in many JVMs today and use of large tables.
 * The larger tables are lazily initialised on first use, so that the setup
 * time does not penalise methods that don't need them.
 * </p>
 * <p>
 * Note that FastMath is
 * extensively used inside Apache Commons Math, so by calling some algorithms,
 * the overhead when the the tables need to be intialised will occur
 * regardless of the end-user calling FastMath methods directly or not.
 * Performance figures for a specific JVM and hardware can be evaluated by
 * running the FastMathTestPerformance tests in the test directory of the source
 * distribution.
 * </p>
 * <p>
 * FastMath accuracy should be mostly independent of the JVM as it relies only
 * on IEEE-754 basic operations and on embedded tables. Almost all operations
 * are accurate to about 0.5 ulp throughout the domain range. This statement,
 * of course is only a rough global observed behavior, it is <em>not</em> a
 * guarantee for <em>every</em> double numbers input (see William Kahan's <a
 * href="http://en.wikipedia.org/wiki/Rounding#The_table-maker.27s_dilemma">Table
 * Maker's Dilemma</a>).
 * </p>
 * <p>
 * FastMath additionally implements the following methods not found in Math/StrictMath:
 * <ul>
 * <li>{@link #asinh(double)}</li>
 * <li>{@link #acosh(double)}</li>
 * <li>{@link #atanh(double)}</li>
 * </ul>
 * The following methods are found in Math/StrictMath since 1.6 only, they are provided
 * by FastMath even in 1.5 Java virtual machines
 * <ul>
 * <li>{@link #copySign(double, double)}</li>
 * <li>{@link #getExponent(double)}</li>
 * <li>{@link #nextAfter(double,double)}</li>
 * <li>{@link #nextUp(double)}</li>
 * <li>{@link #scalb(double, int)}</li>
 * <li>{@link #copySign(float, float)}</li>
 * <li>{@link #getExponent(float)}</li>
 * <li>{@link #nextAfter(float,double)}</li>
 * <li>{@link #nextUp(float)}</li>
 * <li>{@link #scalb(float, int)}</li>
 * </ul>
 * </p>
 * @since 2.2
 */
public class FastMath {

    @Conditional
    public static boolean _mut43023 = false, _mut43024 = false, _mut43025 = false, _mut43026 = false, _mut43027 = false, _mut43028 = false, _mut43029 = false, _mut43030 = false, _mut43031 = false, _mut43032 = false, _mut43033 = false, _mut43034 = false, _mut43035 = false, _mut43036 = false, _mut43037 = false, _mut43038 = false, _mut43039 = false, _mut43040 = false, _mut43041 = false, _mut43042 = false, _mut43043 = false, _mut43044 = false, _mut43045 = false, _mut43046 = false, _mut43047 = false, _mut43048 = false, _mut43049 = false, _mut43050 = false, _mut43051 = false, _mut43052 = false, _mut43053 = false, _mut43054 = false, _mut43055 = false, _mut43056 = false, _mut43057 = false, _mut43058 = false, _mut43059 = false, _mut43060 = false, _mut43061 = false, _mut43062 = false, _mut43063 = false, _mut43064 = false, _mut43065 = false, _mut43066 = false, _mut43067 = false, _mut43068 = false, _mut43069 = false, _mut43070 = false, _mut43071 = false, _mut43072 = false, _mut43073 = false, _mut43074 = false, _mut43075 = false, _mut43076 = false, _mut43077 = false, _mut43078 = false, _mut43079 = false, _mut43080 = false, _mut43081 = false, _mut43082 = false, _mut43083 = false, _mut43084 = false, _mut43085 = false, _mut43086 = false, _mut43087 = false, _mut43088 = false, _mut43089 = false, _mut43090 = false, _mut43091 = false, _mut43092 = false, _mut43093 = false, _mut43094 = false, _mut43095 = false, _mut43096 = false, _mut43097 = false, _mut43098 = false, _mut43099 = false, _mut43100 = false, _mut43101 = false, _mut43102 = false, _mut43103 = false, _mut43104 = false, _mut43105 = false, _mut43106 = false, _mut43107 = false, _mut43108 = false, _mut43109 = false, _mut43110 = false, _mut43111 = false, _mut43112 = false, _mut43113 = false, _mut43114 = false, _mut43115 = false, _mut43116 = false, _mut43117 = false, _mut43118 = false, _mut43119 = false, _mut43120 = false, _mut43121 = false, _mut43122 = false, _mut43123 = false, _mut43124 = false, _mut43125 = false, _mut43126 = false, _mut43127 = false, _mut43128 = false, _mut43129 = false, _mut43130 = false, _mut43131 = false, _mut43132 = false, _mut43133 = false, _mut43134 = false, _mut43135 = false, _mut43136 = false, _mut43137 = false, _mut43138 = false, _mut43139 = false, _mut43140 = false, _mut43141 = false, _mut43142 = false, _mut43143 = false, _mut43144 = false, _mut43145 = false, _mut43146 = false, _mut43147 = false, _mut43148 = false, _mut43149 = false, _mut43150 = false, _mut43151 = false, _mut43152 = false, _mut43153 = false, _mut43154 = false, _mut43155 = false, _mut43156 = false, _mut43157 = false, _mut43158 = false, _mut43159 = false, _mut43160 = false, _mut43161 = false, _mut43162 = false, _mut43163 = false, _mut43164 = false, _mut43165 = false, _mut43166 = false, _mut43167 = false, _mut43168 = false, _mut43169 = false, _mut43170 = false, _mut43171 = false, _mut43172 = false, _mut43173 = false, _mut43174 = false, _mut43175 = false, _mut43176 = false, _mut43177 = false, _mut43178 = false, _mut43179 = false, _mut43180 = false, _mut43181 = false, _mut43182 = false, _mut43183 = false, _mut43184 = false, _mut43185 = false, _mut43186 = false, _mut43187 = false, _mut43188 = false, _mut43189 = false, _mut43190 = false, _mut43191 = false, _mut43192 = false, _mut43193 = false, _mut43194 = false, _mut43195 = false, _mut43196 = false, _mut43197 = false, _mut43198 = false, _mut43199 = false, _mut43200 = false, _mut43201 = false, _mut43202 = false, _mut43203 = false, _mut43204 = false, _mut43205 = false, _mut43206 = false, _mut43207 = false, _mut43208 = false, _mut43209 = false, _mut43210 = false, _mut43211 = false, _mut43212 = false, _mut43213 = false, _mut43214 = false, _mut43215 = false, _mut43216 = false, _mut43217 = false, _mut43218 = false, _mut43219 = false, _mut43220 = false, _mut43221 = false, _mut43222 = false, _mut43223 = false, _mut43224 = false, _mut43225 = false, _mut43226 = false, _mut43227 = false, _mut43228 = false, _mut43229 = false, _mut43230 = false, _mut43231 = false, _mut43232 = false, _mut43233 = false, _mut43234 = false, _mut43235 = false, _mut43236 = false, _mut43237 = false, _mut43238 = false, _mut43239 = false, _mut43240 = false, _mut43241 = false, _mut43242 = false, _mut43243 = false, _mut43244 = false, _mut43245 = false, _mut43246 = false, _mut43247 = false, _mut43248 = false, _mut43249 = false, _mut43250 = false, _mut43251 = false, _mut43252 = false, _mut43253 = false, _mut43254 = false, _mut43255 = false, _mut43256 = false, _mut43257 = false, _mut43258 = false, _mut43259 = false, _mut43260 = false, _mut43261 = false, _mut43262 = false, _mut43263 = false, _mut43264 = false, _mut43265 = false, _mut43266 = false, _mut43267 = false, _mut43268 = false, _mut43269 = false, _mut43270 = false, _mut43271 = false, _mut43272 = false, _mut43273 = false, _mut43274 = false, _mut43275 = false, _mut43276 = false, _mut43277 = false, _mut43278 = false, _mut43279 = false, _mut43280 = false, _mut43281 = false, _mut43282 = false, _mut43283 = false, _mut43284 = false, _mut43285 = false, _mut43286 = false, _mut43287 = false, _mut43288 = false, _mut43289 = false, _mut43290 = false, _mut43291 = false, _mut43292 = false, _mut43293 = false, _mut43294 = false, _mut43295 = false, _mut43296 = false, _mut43297 = false, _mut43298 = false, _mut43299 = false, _mut43300 = false, _mut43301 = false, _mut43302 = false, _mut43303 = false, _mut43304 = false, _mut43305 = false, _mut43306 = false, _mut43307 = false, _mut43308 = false, _mut43309 = false, _mut43310 = false, _mut43311 = false, _mut43312 = false, _mut43313 = false, _mut43314 = false, _mut43315 = false, _mut43316 = false, _mut43317 = false, _mut43318 = false, _mut43319 = false, _mut43320 = false, _mut43321 = false, _mut43322 = false, _mut43323 = false, _mut43324 = false, _mut43325 = false, _mut43326 = false, _mut43327 = false, _mut43328 = false, _mut43329 = false, _mut43330 = false, _mut43331 = false, _mut43332 = false, _mut43333 = false, _mut43334 = false, _mut43335 = false, _mut43336 = false, _mut43337 = false, _mut43338 = false, _mut43339 = false, _mut43340 = false, _mut43341 = false, _mut43342 = false, _mut43343 = false, _mut43344 = false, _mut43345 = false, _mut43346 = false, _mut43347 = false, _mut43348 = false, _mut43349 = false, _mut43350 = false, _mut43351 = false, _mut43352 = false, _mut43353 = false, _mut43354 = false, _mut43355 = false, _mut43356 = false, _mut43357 = false, _mut43358 = false, _mut43359 = false, _mut43360 = false, _mut43361 = false, _mut43362 = false, _mut43363 = false, _mut43364 = false, _mut43365 = false, _mut43366 = false, _mut43367 = false, _mut43368 = false, _mut43369 = false, _mut43370 = false, _mut43371 = false, _mut43372 = false, _mut43373 = false, _mut43374 = false, _mut43375 = false, _mut43376 = false, _mut43377 = false, _mut43378 = false, _mut43379 = false, _mut43380 = false, _mut43381 = false, _mut43382 = false, _mut43383 = false, _mut43384 = false, _mut43385 = false, _mut43386 = false, _mut43387 = false, _mut43388 = false, _mut43389 = false, _mut43390 = false, _mut43391 = false, _mut43392 = false, _mut43393 = false, _mut43394 = false, _mut43395 = false, _mut43396 = false, _mut43397 = false, _mut43398 = false, _mut43399 = false, _mut43400 = false, _mut43401 = false, _mut43402 = false, _mut43403 = false, _mut43404 = false, _mut43405 = false, _mut43406 = false, _mut43407 = false, _mut43408 = false, _mut43409 = false, _mut43410 = false, _mut43411 = false, _mut43412 = false, _mut43413 = false, _mut43414 = false, _mut43415 = false, _mut43416 = false, _mut43417 = false, _mut43418 = false, _mut43419 = false, _mut43420 = false, _mut43421 = false, _mut43422 = false, _mut43423 = false, _mut43424 = false, _mut43425 = false, _mut43426 = false, _mut43427 = false, _mut43428 = false, _mut43429 = false, _mut43430 = false, _mut43431 = false, _mut43432 = false, _mut43433 = false, _mut43434 = false, _mut43435 = false, _mut43436 = false, _mut43437 = false, _mut43438 = false, _mut43439 = false, _mut43440 = false, _mut43441 = false, _mut43442 = false, _mut43443 = false, _mut43444 = false, _mut43445 = false, _mut43446 = false, _mut43447 = false, _mut43448 = false, _mut43449 = false, _mut43450 = false, _mut43451 = false, _mut43452 = false, _mut43453 = false, _mut43454 = false, _mut43455 = false, _mut43456 = false, _mut43457 = false, _mut43458 = false, _mut43459 = false, _mut43460 = false, _mut43461 = false, _mut43462 = false, _mut43463 = false, _mut43464 = false, _mut43465 = false, _mut43466 = false, _mut43467 = false, _mut43468 = false, _mut43469 = false, _mut43470 = false, _mut43471 = false, _mut43472 = false, _mut43473 = false, _mut43474 = false, _mut43475 = false, _mut43476 = false, _mut43477 = false, _mut43478 = false, _mut43479 = false, _mut43480 = false, _mut43481 = false, _mut43482 = false, _mut43483 = false, _mut43484 = false, _mut43485 = false, _mut43486 = false, _mut43487 = false, _mut43488 = false, _mut43489 = false, _mut43490 = false, _mut43491 = false, _mut43492 = false, _mut43493 = false, _mut43494 = false, _mut43495 = false, _mut43496 = false, _mut43497 = false, _mut43498 = false, _mut43499 = false, _mut43500 = false, _mut43501 = false, _mut43502 = false, _mut43503 = false, _mut43504 = false, _mut43505 = false, _mut43506 = false, _mut43507 = false, _mut43508 = false, _mut43509 = false, _mut43510 = false, _mut43511 = false, _mut43512 = false, _mut43513 = false, _mut43514 = false, _mut43515 = false, _mut43516 = false, _mut43517 = false, _mut43518 = false, _mut43519 = false, _mut43520 = false, _mut43521 = false, _mut43522 = false, _mut43523 = false, _mut43524 = false, _mut43525 = false, _mut43526 = false, _mut43527 = false, _mut43528 = false, _mut43529 = false, _mut43530 = false, _mut43531 = false, _mut43532 = false, _mut43533 = false, _mut43534 = false, _mut43535 = false, _mut43536 = false, _mut43537 = false, _mut43538 = false, _mut43539 = false, _mut43540 = false, _mut43541 = false, _mut43542 = false, _mut43543 = false, _mut43544 = false, _mut43545 = false, _mut43546 = false, _mut43547 = false, _mut43548 = false, _mut43549 = false, _mut43550 = false, _mut43551 = false, _mut43552 = false, _mut43553 = false, _mut43554 = false, _mut43555 = false, _mut43556 = false, _mut43557 = false, _mut43558 = false, _mut43559 = false, _mut43560 = false, _mut43561 = false, _mut43562 = false, _mut43563 = false, _mut43564 = false, _mut43565 = false, _mut43566 = false, _mut43567 = false, _mut43568 = false, _mut43569 = false, _mut43570 = false, _mut43571 = false, _mut43572 = false, _mut43573 = false, _mut43574 = false, _mut43575 = false, _mut43576 = false, _mut43577 = false, _mut43578 = false, _mut43579 = false, _mut43580 = false, _mut43581 = false, _mut43582 = false, _mut43583 = false, _mut43584 = false, _mut43585 = false, _mut43586 = false, _mut43587 = false, _mut43588 = false, _mut43589 = false, _mut43590 = false, _mut43591 = false, _mut43592 = false, _mut43593 = false, _mut43594 = false, _mut43595 = false, _mut43596 = false, _mut43597 = false, _mut43598 = false, _mut43599 = false, _mut43600 = false, _mut43601 = false, _mut43602 = false, _mut43603 = false, _mut43604 = false, _mut43605 = false, _mut43606 = false, _mut43607 = false, _mut43608 = false, _mut43609 = false, _mut43610 = false, _mut43611 = false, _mut43612 = false, _mut43613 = false, _mut43614 = false, _mut43615 = false, _mut43616 = false, _mut43617 = false, _mut43618 = false, _mut43619 = false, _mut43620 = false, _mut43621 = false, _mut43622 = false, _mut43623 = false, _mut43624 = false, _mut43625 = false, _mut43626 = false, _mut43627 = false, _mut43628 = false, _mut43629 = false, _mut43630 = false, _mut43631 = false, _mut43632 = false, _mut43633 = false, _mut43634 = false, _mut43635 = false, _mut43636 = false, _mut43637 = false, _mut43638 = false, _mut43639 = false, _mut43640 = false, _mut43641 = false, _mut43642 = false, _mut43643 = false, _mut43644 = false, _mut43645 = false, _mut43646 = false, _mut43647 = false, _mut43648 = false, _mut43649 = false, _mut43650 = false, _mut43651 = false, _mut43652 = false, _mut43653 = false, _mut43654 = false, _mut43655 = false, _mut43656 = false, _mut43657 = false, _mut43658 = false, _mut43659 = false, _mut43660 = false, _mut43661 = false, _mut43662 = false, _mut43663 = false, _mut43664 = false, _mut43665 = false, _mut43666 = false, _mut43667 = false, _mut43668 = false, _mut43669 = false, _mut43670 = false, _mut43671 = false, _mut43672 = false, _mut43673 = false, _mut43674 = false, _mut43675 = false, _mut43676 = false, _mut43677 = false, _mut43678 = false, _mut43679 = false, _mut43680 = false, _mut43681 = false, _mut43682 = false, _mut43683 = false, _mut43684 = false, _mut43685 = false, _mut43686 = false, _mut43687 = false, _mut43688 = false, _mut43689 = false, _mut43690 = false, _mut43691 = false, _mut43692 = false, _mut43693 = false, _mut43694 = false, _mut43695 = false, _mut43696 = false, _mut43697 = false, _mut43698 = false, _mut43699 = false, _mut43700 = false, _mut43701 = false, _mut43702 = false, _mut43703 = false, _mut43704 = false, _mut43705 = false, _mut43706 = false, _mut43707 = false, _mut43708 = false, _mut43709 = false, _mut43710 = false, _mut43711 = false, _mut43712 = false, _mut43713 = false, _mut43714 = false, _mut43715 = false, _mut43716 = false, _mut43717 = false, _mut43718 = false, _mut43719 = false, _mut43720 = false, _mut43721 = false, _mut43722 = false, _mut43723 = false, _mut43724 = false, _mut43725 = false, _mut43726 = false, _mut43727 = false, _mut43728 = false, _mut43729 = false, _mut43730 = false, _mut43731 = false, _mut43732 = false, _mut43733 = false, _mut43734 = false, _mut43735 = false, _mut43736 = false, _mut43737 = false, _mut43738 = false, _mut43739 = false, _mut43740 = false, _mut43741 = false, _mut43742 = false, _mut43743 = false, _mut43744 = false, _mut43745 = false, _mut43746 = false, _mut43747 = false, _mut43748 = false, _mut43749 = false, _mut43750 = false, _mut43751 = false, _mut43752 = false, _mut43753 = false, _mut43754 = false, _mut43755 = false, _mut43756 = false, _mut43757 = false, _mut43758 = false, _mut43759 = false, _mut43760 = false, _mut43761 = false, _mut43762 = false, _mut43763 = false, _mut43764 = false, _mut43765 = false, _mut43766 = false, _mut43767 = false, _mut43768 = false, _mut43769 = false, _mut43770 = false, _mut43771 = false, _mut43772 = false, _mut43773 = false, _mut43774 = false, _mut43775 = false, _mut43776 = false, _mut43777 = false, _mut43778 = false, _mut43779 = false, _mut43780 = false, _mut43781 = false, _mut43782 = false, _mut43783 = false, _mut43784 = false, _mut43785 = false, _mut43786 = false, _mut43787 = false, _mut43788 = false, _mut43789 = false, _mut43790 = false, _mut43791 = false, _mut43792 = false, _mut43793 = false, _mut43794 = false, _mut43795 = false, _mut43796 = false, _mut43797 = false, _mut43798 = false, _mut43799 = false, _mut43800 = false, _mut43801 = false, _mut43802 = false, _mut43803 = false, _mut43804 = false, _mut43805 = false, _mut43806 = false, _mut43807 = false, _mut43808 = false, _mut43809 = false, _mut43810 = false, _mut43811 = false, _mut43812 = false, _mut43813 = false, _mut43814 = false, _mut43815 = false, _mut43816 = false, _mut43817 = false, _mut43818 = false, _mut43819 = false, _mut43820 = false, _mut43821 = false, _mut43822 = false, _mut43823 = false, _mut43824 = false, _mut43825 = false, _mut43826 = false, _mut43827 = false, _mut43828 = false, _mut43829 = false, _mut43830 = false, _mut43831 = false, _mut43832 = false, _mut43833 = false, _mut43834 = false, _mut43835 = false, _mut43836 = false, _mut43837 = false, _mut43838 = false, _mut43839 = false, _mut43840 = false, _mut43841 = false, _mut43842 = false, _mut43843 = false, _mut43844 = false, _mut43845 = false, _mut43846 = false, _mut43847 = false, _mut43848 = false, _mut43849 = false, _mut43850 = false, _mut43851 = false, _mut43852 = false, _mut43853 = false, _mut43854 = false, _mut43855 = false, _mut43856 = false, _mut43857 = false, _mut43858 = false, _mut43859 = false, _mut43860 = false, _mut43861 = false, _mut43862 = false, _mut43863 = false, _mut43864 = false, _mut43865 = false, _mut43866 = false, _mut43867 = false, _mut43868 = false, _mut43869 = false, _mut43870 = false, _mut43871 = false, _mut43872 = false, _mut43873 = false, _mut43874 = false, _mut43875 = false, _mut43876 = false, _mut43877 = false, _mut43878 = false, _mut43879 = false, _mut43880 = false, _mut43881 = false, _mut43882 = false, _mut43883 = false, _mut43884 = false, _mut43885 = false, _mut43886 = false, _mut43887 = false, _mut43888 = false, _mut43889 = false, _mut43890 = false, _mut43891 = false, _mut43892 = false, _mut43893 = false, _mut43894 = false, _mut43895 = false, _mut43896 = false, _mut43897 = false, _mut43898 = false, _mut43899 = false, _mut43900 = false, _mut43901 = false, _mut43902 = false, _mut43903 = false, _mut43904 = false, _mut43905 = false, _mut43906 = false, _mut43907 = false, _mut43908 = false, _mut43909 = false, _mut43910 = false, _mut43911 = false, _mut43912 = false, _mut43913 = false, _mut43914 = false, _mut43915 = false, _mut43916 = false, _mut43917 = false, _mut43918 = false, _mut43919 = false, _mut43920 = false, _mut43921 = false, _mut43922 = false, _mut43923 = false, _mut43924 = false, _mut43925 = false, _mut43926 = false, _mut43927 = false, _mut43928 = false, _mut43929 = false, _mut43930 = false, _mut43931 = false, _mut43932 = false, _mut43933 = false, _mut43934 = false, _mut43935 = false, _mut43936 = false, _mut43937 = false, _mut43938 = false, _mut43939 = false, _mut43940 = false, _mut43941 = false, _mut43942 = false, _mut43943 = false, _mut43944 = false, _mut43945 = false, _mut43946 = false, _mut43947 = false, _mut43948 = false, _mut43949 = false, _mut43950 = false, _mut43951 = false, _mut43952 = false, _mut43953 = false, _mut43954 = false, _mut43955 = false, _mut43956 = false, _mut43957 = false, _mut43958 = false, _mut43959 = false, _mut43960 = false, _mut43961 = false, _mut43962 = false, _mut43963 = false, _mut43964 = false, _mut43965 = false, _mut43966 = false, _mut43967 = false, _mut43968 = false, _mut43969 = false, _mut43970 = false, _mut43971 = false, _mut43972 = false, _mut43973 = false, _mut43974 = false, _mut43975 = false, _mut43976 = false, _mut43977 = false, _mut43978 = false, _mut43979 = false, _mut43980 = false, _mut43981 = false, _mut43982 = false, _mut43983 = false, _mut43984 = false, _mut43985 = false, _mut43986 = false, _mut43987 = false, _mut43988 = false, _mut43989 = false, _mut43990 = false, _mut43991 = false, _mut43992 = false, _mut43993 = false, _mut43994 = false, _mut43995 = false, _mut43996 = false, _mut43997 = false, _mut43998 = false, _mut43999 = false, _mut44000 = false, _mut44001 = false, _mut44002 = false, _mut44003 = false, _mut44004 = false, _mut44005 = false, _mut44006 = false, _mut44007 = false, _mut44008 = false, _mut44009 = false, _mut44010 = false, _mut44011 = false, _mut44012 = false, _mut44013 = false, _mut44014 = false, _mut44015 = false, _mut44016 = false, _mut44017 = false, _mut44018 = false, _mut44019 = false, _mut44020 = false, _mut44021 = false, _mut44022 = false, _mut44023 = false, _mut44024 = false, _mut44025 = false, _mut44026 = false, _mut44027 = false, _mut44028 = false, _mut44029 = false, _mut44030 = false, _mut44031 = false, _mut44032 = false, _mut44033 = false, _mut44034 = false, _mut44035 = false, _mut44036 = false, _mut44037 = false, _mut44038 = false, _mut44039 = false, _mut44040 = false, _mut44041 = false, _mut44042 = false, _mut44043 = false, _mut44044 = false, _mut44045 = false, _mut44046 = false, _mut44047 = false, _mut44048 = false, _mut44049 = false, _mut44050 = false, _mut44051 = false, _mut44052 = false, _mut44053 = false, _mut44054 = false, _mut44055 = false, _mut44056 = false, _mut44057 = false, _mut44058 = false, _mut44059 = false, _mut44060 = false, _mut44061 = false, _mut44062 = false, _mut44063 = false, _mut44064 = false, _mut44065 = false, _mut44066 = false, _mut44067 = false, _mut44068 = false, _mut44069 = false, _mut44070 = false, _mut44071 = false, _mut44072 = false, _mut44073 = false, _mut44074 = false, _mut44075 = false, _mut44076 = false, _mut44077 = false, _mut44078 = false, _mut44079 = false, _mut44080 = false, _mut44081 = false, _mut44082 = false, _mut44083 = false, _mut44084 = false, _mut44085 = false, _mut44086 = false, _mut44087 = false, _mut44088 = false, _mut44089 = false, _mut44090 = false, _mut44091 = false, _mut44092 = false, _mut44093 = false, _mut44094 = false, _mut44095 = false, _mut44096 = false, _mut44097 = false, _mut44098 = false, _mut44099 = false, _mut44100 = false, _mut44101 = false, _mut44102 = false, _mut44103 = false, _mut44104 = false, _mut44105 = false, _mut44106 = false, _mut44107 = false, _mut44108 = false, _mut44109 = false, _mut44110 = false, _mut44111 = false, _mut44112 = false, _mut44113 = false, _mut44114 = false, _mut44115 = false, _mut44116 = false, _mut44117 = false, _mut44118 = false, _mut44119 = false, _mut44120 = false, _mut44121 = false, _mut44122 = false, _mut44123 = false, _mut44124 = false, _mut44125 = false, _mut44126 = false, _mut44127 = false, _mut44128 = false, _mut44129 = false, _mut44130 = false, _mut44131 = false, _mut44132 = false, _mut44133 = false, _mut44134 = false, _mut44135 = false, _mut44136 = false, _mut44137 = false, _mut44138 = false, _mut44139 = false, _mut44140 = false, _mut44141 = false, _mut44142 = false, _mut44143 = false, _mut44144 = false, _mut44145 = false, _mut44146 = false, _mut44147 = false, _mut44148 = false, _mut44149 = false, _mut44150 = false, _mut44151 = false, _mut44152 = false, _mut44153 = false, _mut44154 = false, _mut44155 = false, _mut44156 = false, _mut44157 = false, _mut44158 = false, _mut44159 = false, _mut44160 = false, _mut44161 = false, _mut44162 = false, _mut44163 = false, _mut44164 = false, _mut44165 = false, _mut44166 = false, _mut44167 = false, _mut44168 = false, _mut44169 = false, _mut44170 = false, _mut44171 = false, _mut44172 = false, _mut44173 = false, _mut44174 = false, _mut44175 = false, _mut44176 = false, _mut44177 = false, _mut44178 = false, _mut44179 = false, _mut44180 = false, _mut44181 = false, _mut44182 = false, _mut44183 = false, _mut44184 = false, _mut44185 = false, _mut44186 = false, _mut44187 = false, _mut44188 = false, _mut44189 = false, _mut44190 = false, _mut44191 = false, _mut44192 = false, _mut44193 = false, _mut44194 = false, _mut44195 = false, _mut44196 = false, _mut44197 = false, _mut44198 = false, _mut44199 = false, _mut44200 = false, _mut44201 = false, _mut44202 = false, _mut44203 = false, _mut44204 = false, _mut44205 = false, _mut44206 = false, _mut44207 = false, _mut44208 = false, _mut44209 = false, _mut44210 = false, _mut44211 = false, _mut44212 = false, _mut44213 = false, _mut44214 = false, _mut44215 = false, _mut44216 = false, _mut44217 = false, _mut44218 = false, _mut44219 = false, _mut44220 = false, _mut44221 = false, _mut44222 = false, _mut44223 = false, _mut44224 = false, _mut44225 = false, _mut44226 = false, _mut44227 = false, _mut44228 = false, _mut44229 = false, _mut44230 = false, _mut44231 = false, _mut44232 = false, _mut44233 = false, _mut44234 = false, _mut44235 = false, _mut44236 = false, _mut44237 = false, _mut44238 = false, _mut44239 = false, _mut44240 = false, _mut44241 = false, _mut44242 = false, _mut44243 = false, _mut44244 = false, _mut44245 = false, _mut44246 = false, _mut44247 = false, _mut44248 = false, _mut44249 = false, _mut44250 = false, _mut44251 = false, _mut44252 = false, _mut44253 = false, _mut44254 = false, _mut44255 = false, _mut44256 = false, _mut44257 = false, _mut44258 = false, _mut44259 = false, _mut44260 = false, _mut44261 = false, _mut44262 = false, _mut44263 = false, _mut44264 = false, _mut44265 = false, _mut44266 = false, _mut44267 = false, _mut44268 = false, _mut44269 = false, _mut44270 = false, _mut44271 = false, _mut44272 = false, _mut44273 = false, _mut44274 = false, _mut44275 = false, _mut44276 = false, _mut44277 = false, _mut44278 = false, _mut44279 = false, _mut44280 = false, _mut44281 = false, _mut44282 = false, _mut44283 = false, _mut44284 = false, _mut44285 = false, _mut44286 = false, _mut44287 = false, _mut44288 = false, _mut44289 = false, _mut44290 = false, _mut44291 = false, _mut44292 = false, _mut44293 = false, _mut44294 = false, _mut44295 = false, _mut44296 = false, _mut44297 = false, _mut44298 = false, _mut44299 = false, _mut44300 = false, _mut44301 = false, _mut44302 = false, _mut44303 = false, _mut44304 = false, _mut44305 = false, _mut44306 = false, _mut44307 = false, _mut44308 = false, _mut44309 = false, _mut44310 = false, _mut44311 = false, _mut44312 = false, _mut44313 = false, _mut44314 = false, _mut44315 = false, _mut44316 = false, _mut44317 = false, _mut44318 = false, _mut44319 = false, _mut44320 = false, _mut44321 = false, _mut44322 = false, _mut44323 = false, _mut44324 = false, _mut44325 = false, _mut44326 = false, _mut44327 = false, _mut44328 = false, _mut44329 = false, _mut44330 = false, _mut44331 = false, _mut44332 = false, _mut44333 = false, _mut44334 = false, _mut44335 = false, _mut44336 = false, _mut44337 = false, _mut44338 = false, _mut44339 = false, _mut44340 = false, _mut44341 = false, _mut44342 = false, _mut44343 = false, _mut44344 = false, _mut44345 = false, _mut44346 = false, _mut44347 = false, _mut44348 = false, _mut44349 = false, _mut44350 = false, _mut44351 = false, _mut44352 = false, _mut44353 = false, _mut44354 = false, _mut44355 = false, _mut44356 = false, _mut44357 = false, _mut44358 = false, _mut44359 = false, _mut44360 = false, _mut44361 = false, _mut44362 = false, _mut44363 = false, _mut44364 = false, _mut44365 = false, _mut44366 = false, _mut44367 = false, _mut44368 = false, _mut44369 = false, _mut44370 = false, _mut44371 = false, _mut44372 = false, _mut44373 = false, _mut44374 = false, _mut44375 = false, _mut44376 = false, _mut44377 = false, _mut44378 = false, _mut44379 = false, _mut44380 = false, _mut44381 = false, _mut44382 = false, _mut44383 = false, _mut44384 = false, _mut44385 = false, _mut44386 = false, _mut44387 = false, _mut44388 = false, _mut44389 = false, _mut44390 = false, _mut44391 = false, _mut44392 = false, _mut44393 = false, _mut44394 = false, _mut44395 = false, _mut44396 = false, _mut44397 = false, _mut44398 = false, _mut44399 = false, _mut44400 = false, _mut44401 = false, _mut44402 = false, _mut44403 = false, _mut44404 = false, _mut44405 = false, _mut44406 = false, _mut44407 = false, _mut44408 = false, _mut44409 = false, _mut44410 = false, _mut44411 = false, _mut44412 = false, _mut44413 = false, _mut44414 = false, _mut44415 = false, _mut44416 = false, _mut44417 = false, _mut44418 = false, _mut44419 = false, _mut44420 = false, _mut44421 = false, _mut44422 = false, _mut44423 = false, _mut44424 = false, _mut44425 = false, _mut44426 = false, _mut44427 = false, _mut44428 = false, _mut44429 = false, _mut44430 = false, _mut44431 = false, _mut44432 = false, _mut44433 = false, _mut44434 = false, _mut44435 = false, _mut44436 = false, _mut44437 = false, _mut44438 = false, _mut44439 = false, _mut44440 = false, _mut44441 = false, _mut44442 = false, _mut44443 = false, _mut44444 = false, _mut44445 = false, _mut44446 = false, _mut44447 = false, _mut44448 = false, _mut44449 = false, _mut44450 = false, _mut44451 = false, _mut44452 = false, _mut44453 = false, _mut44454 = false, _mut44455 = false, _mut44456 = false, _mut44457 = false, _mut44458 = false, _mut44459 = false, _mut44460 = false, _mut44461 = false, _mut44462 = false, _mut44463 = false, _mut44464 = false, _mut44465 = false, _mut44466 = false, _mut44467 = false, _mut44468 = false, _mut44469 = false, _mut44470 = false, _mut44471 = false, _mut44472 = false, _mut44473 = false, _mut44474 = false, _mut44475 = false, _mut44476 = false, _mut44477 = false, _mut44478 = false, _mut44479 = false, _mut44480 = false, _mut44481 = false, _mut44482 = false, _mut44483 = false, _mut44484 = false, _mut44485 = false, _mut44486 = false, _mut44487 = false, _mut44488 = false, _mut44489 = false, _mut44490 = false, _mut44491 = false, _mut44492 = false, _mut44493 = false, _mut44494 = false, _mut44495 = false, _mut44496 = false, _mut44497 = false, _mut44498 = false, _mut44499 = false, _mut44500 = false, _mut44501 = false, _mut44502 = false, _mut44503 = false, _mut44504 = false, _mut44505 = false, _mut44506 = false, _mut44507 = false, _mut44508 = false, _mut44509 = false, _mut44510 = false, _mut44511 = false, _mut44512 = false, _mut44513 = false, _mut44514 = false, _mut44515 = false, _mut44516 = false, _mut44517 = false, _mut44518 = false, _mut44519 = false, _mut44520 = false, _mut44521 = false, _mut44522 = false, _mut44523 = false, _mut44524 = false, _mut44525 = false, _mut44526 = false, _mut44527 = false, _mut44528 = false, _mut44529 = false, _mut44530 = false, _mut44531 = false, _mut44532 = false, _mut44533 = false, _mut44534 = false, _mut44535 = false, _mut44536 = false, _mut44537 = false, _mut44538 = false, _mut44539 = false, _mut44540 = false, _mut44541 = false, _mut44542 = false, _mut44543 = false, _mut44544 = false, _mut44545 = false, _mut44546 = false, _mut44547 = false, _mut44548 = false, _mut44549 = false, _mut44550 = false, _mut44551 = false, _mut44552 = false, _mut44553 = false, _mut44554 = false, _mut44555 = false, _mut44556 = false, _mut44557 = false, _mut44558 = false, _mut44559 = false, _mut44560 = false, _mut44561 = false, _mut44562 = false, _mut44563 = false, _mut44564 = false, _mut44565 = false, _mut44566 = false, _mut44567 = false, _mut44568 = false, _mut44569 = false, _mut44570 = false, _mut44571 = false, _mut44572 = false, _mut44573 = false, _mut44574 = false, _mut44575 = false, _mut44576 = false, _mut44577 = false, _mut44578 = false, _mut44579 = false, _mut44580 = false, _mut44581 = false, _mut44582 = false, _mut44583 = false, _mut44584 = false, _mut44585 = false, _mut44586 = false, _mut44587 = false, _mut44588 = false, _mut44589 = false, _mut44590 = false, _mut44591 = false, _mut44592 = false, _mut44593 = false, _mut44594 = false, _mut44595 = false, _mut44596 = false, _mut44597 = false, _mut44598 = false, _mut44599 = false, _mut44600 = false, _mut44601 = false, _mut44602 = false, _mut44603 = false, _mut44604 = false, _mut44605 = false, _mut44606 = false, _mut44607 = false, _mut44608 = false, _mut44609 = false, _mut44610 = false, _mut44611 = false, _mut44612 = false, _mut44613 = false, _mut44614 = false, _mut44615 = false, _mut44616 = false, _mut44617 = false, _mut44618 = false, _mut44619 = false, _mut44620 = false, _mut44621 = false, _mut44622 = false, _mut44623 = false, _mut44624 = false, _mut44625 = false, _mut44626 = false, _mut44627 = false, _mut44628 = false, _mut44629 = false, _mut44630 = false, _mut44631 = false, _mut44632 = false, _mut44633 = false, _mut44634 = false, _mut44635 = false, _mut44636 = false, _mut44637 = false, _mut44638 = false, _mut44639 = false, _mut44640 = false, _mut44641 = false, _mut44642 = false, _mut44643 = false, _mut44644 = false, _mut44645 = false, _mut44646 = false, _mut44647 = false, _mut44648 = false, _mut44649 = false, _mut44650 = false, _mut44651 = false, _mut44652 = false, _mut44653 = false, _mut44654 = false, _mut44655 = false, _mut44656 = false, _mut44657 = false, _mut44658 = false, _mut44659 = false, _mut44660 = false, _mut44661 = false, _mut44662 = false, _mut44663 = false, _mut44664 = false, _mut44665 = false, _mut44666 = false, _mut44667 = false, _mut44668 = false, _mut44669 = false, _mut44670 = false, _mut44671 = false, _mut44672 = false, _mut44673 = false, _mut44674 = false, _mut44675 = false, _mut44676 = false, _mut44677 = false, _mut44678 = false, _mut44679 = false, _mut44680 = false, _mut44681 = false, _mut44682 = false, _mut44683 = false, _mut44684 = false, _mut44685 = false, _mut44686 = false, _mut44687 = false, _mut44688 = false, _mut44689 = false, _mut44690 = false, _mut44691 = false, _mut44692 = false, _mut44693 = false, _mut44694 = false, _mut44695 = false, _mut44696 = false, _mut44697 = false, _mut44698 = false, _mut44699 = false, _mut44700 = false, _mut44701 = false, _mut44702 = false, _mut44703 = false, _mut44704 = false, _mut44705 = false, _mut44706 = false, _mut44707 = false, _mut44708 = false, _mut44709 = false, _mut44710 = false, _mut44711 = false, _mut44712 = false, _mut44713 = false, _mut44714 = false, _mut44715 = false, _mut44716 = false, _mut44717 = false, _mut44718 = false, _mut44719 = false, _mut44720 = false, _mut44721 = false, _mut44722 = false, _mut44723 = false, _mut44724 = false, _mut44725 = false, _mut44726 = false, _mut44727 = false, _mut44728 = false, _mut44729 = false, _mut44730 = false, _mut44731 = false, _mut44732 = false, _mut44733 = false, _mut44734 = false, _mut44735 = false, _mut44736 = false, _mut44737 = false, _mut44738 = false, _mut44739 = false, _mut44740 = false, _mut44741 = false, _mut44742 = false, _mut44743 = false, _mut44744 = false, _mut44745 = false, _mut44746 = false, _mut44747 = false, _mut44748 = false, _mut44749 = false, _mut44750 = false, _mut44751 = false, _mut44752 = false, _mut44753 = false, _mut44754 = false, _mut44755 = false, _mut44756 = false, _mut44757 = false, _mut44758 = false, _mut44759 = false, _mut44760 = false, _mut44761 = false, _mut44762 = false, _mut44763 = false, _mut44764 = false, _mut44765 = false, _mut44766 = false, _mut44767 = false, _mut44768 = false, _mut44769 = false, _mut44770 = false, _mut44771 = false, _mut44772 = false, _mut44773 = false, _mut44774 = false, _mut44775 = false, _mut44776 = false, _mut44777 = false, _mut44778 = false, _mut44779 = false, _mut44780 = false, _mut44781 = false, _mut44782 = false, _mut44783 = false, _mut44784 = false, _mut44785 = false, _mut44786 = false, _mut44787 = false, _mut44788 = false, _mut44789 = false, _mut44790 = false, _mut44791 = false, _mut44792 = false, _mut44793 = false, _mut44794 = false, _mut44795 = false, _mut44796 = false, _mut44797 = false, _mut44798 = false, _mut44799 = false, _mut44800 = false, _mut44801 = false, _mut44802 = false, _mut44803 = false, _mut44804 = false, _mut44805 = false, _mut44806 = false, _mut44807 = false, _mut44808 = false, _mut44809 = false, _mut44810 = false, _mut44811 = false, _mut44812 = false, _mut44813 = false, _mut44814 = false, _mut44815 = false, _mut44816 = false, _mut44817 = false, _mut44818 = false, _mut44819 = false, _mut44820 = false, _mut44821 = false, _mut44822 = false, _mut44823 = false, _mut44824 = false, _mut44825 = false, _mut44826 = false, _mut44827 = false, _mut44828 = false, _mut44829 = false, _mut44830 = false, _mut44831 = false, _mut44832 = false, _mut44833 = false, _mut44834 = false, _mut44835 = false, _mut44836 = false, _mut44837 = false, _mut44838 = false, _mut44839 = false, _mut44840 = false, _mut44841 = false, _mut44842 = false, _mut44843 = false, _mut44844 = false, _mut44845 = false, _mut44846 = false, _mut44847 = false, _mut44848 = false, _mut44849 = false, _mut44850 = false, _mut44851 = false, _mut44852 = false, _mut44853 = false, _mut44854 = false, _mut44855 = false, _mut44856 = false, _mut44857 = false, _mut44858 = false, _mut44859 = false, _mut44860 = false, _mut44861 = false, _mut44862 = false, _mut44863 = false, _mut44864 = false, _mut44865 = false, _mut44866 = false, _mut44867 = false, _mut44868 = false, _mut44869 = false, _mut44870 = false, _mut44871 = false, _mut44872 = false, _mut44873 = false, _mut44874 = false, _mut44875 = false, _mut44876 = false, _mut44877 = false, _mut44878 = false, _mut44879 = false, _mut44880 = false, _mut44881 = false, _mut44882 = false, _mut44883 = false, _mut44884 = false, _mut44885 = false, _mut44886 = false, _mut44887 = false, _mut44888 = false, _mut44889 = false, _mut44890 = false, _mut44891 = false, _mut44892 = false, _mut44893 = false, _mut44894 = false, _mut44895 = false, _mut44896 = false, _mut44897 = false, _mut44898 = false, _mut44899 = false, _mut44900 = false, _mut44901 = false, _mut44902 = false, _mut44903 = false, _mut44904 = false, _mut44905 = false, _mut44906 = false, _mut44907 = false, _mut44908 = false, _mut44909 = false, _mut44910 = false, _mut44911 = false, _mut44912 = false, _mut44913 = false, _mut44914 = false, _mut44915 = false, _mut44916 = false, _mut44917 = false, _mut44918 = false, _mut44919 = false, _mut44920 = false, _mut44921 = false, _mut44922 = false, _mut44923 = false, _mut44924 = false, _mut44925 = false, _mut44926 = false, _mut44927 = false, _mut44928 = false, _mut44929 = false, _mut44930 = false, _mut44931 = false, _mut44932 = false, _mut44933 = false, _mut44934 = false, _mut44935 = false, _mut44936 = false, _mut44937 = false, _mut44938 = false, _mut44939 = false, _mut44940 = false, _mut44941 = false, _mut44942 = false, _mut44943 = false, _mut44944 = false, _mut44945 = false, _mut44946 = false, _mut44947 = false, _mut44948 = false, _mut44949 = false, _mut44950 = false, _mut44951 = false, _mut44952 = false, _mut44953 = false, _mut44954 = false, _mut44955 = false, _mut44956 = false, _mut44957 = false, _mut44958 = false, _mut44959 = false, _mut44960 = false, _mut44961 = false, _mut44962 = false, _mut44963 = false, _mut44964 = false, _mut44965 = false, _mut44966 = false, _mut44967 = false, _mut44968 = false, _mut44969 = false, _mut44970 = false, _mut44971 = false, _mut44972 = false, _mut44973 = false, _mut44974 = false, _mut44975 = false, _mut44976 = false, _mut44977 = false, _mut44978 = false, _mut44979 = false, _mut44980 = false, _mut44981 = false, _mut44982 = false, _mut44983 = false, _mut44984 = false, _mut44985 = false, _mut44986 = false, _mut44987 = false, _mut44988 = false, _mut44989 = false, _mut44990 = false, _mut44991 = false, _mut44992 = false, _mut44993 = false, _mut44994 = false, _mut44995 = false, _mut44996 = false, _mut44997 = false, _mut44998 = false, _mut44999 = false, _mut45000 = false, _mut45001 = false, _mut45002 = false, _mut45003 = false, _mut45004 = false, _mut45005 = false, _mut45006 = false, _mut45007 = false, _mut45008 = false, _mut45009 = false, _mut45010 = false, _mut45011 = false, _mut45012 = false, _mut45013 = false, _mut45014 = false, _mut45015 = false, _mut45016 = false, _mut45017 = false, _mut45018 = false, _mut45019 = false, _mut45020 = false, _mut45021 = false, _mut45022 = false, _mut45023 = false, _mut45024 = false, _mut45025 = false, _mut45026 = false, _mut45027 = false, _mut45028 = false, _mut45029 = false, _mut45030 = false, _mut45031 = false, _mut45032 = false, _mut45033 = false, _mut45034 = false, _mut45035 = false, _mut45036 = false, _mut45037 = false, _mut45038 = false, _mut45039 = false, _mut45040 = false, _mut45041 = false, _mut45042 = false, _mut45043 = false, _mut45044 = false, _mut45045 = false, _mut45046 = false, _mut45047 = false, _mut45048 = false, _mut45049 = false, _mut45050 = false, _mut45051 = false, _mut45052 = false, _mut45053 = false, _mut45054 = false, _mut45055 = false, _mut45056 = false, _mut45057 = false, _mut45058 = false, _mut45059 = false, _mut45060 = false, _mut45061 = false, _mut45062 = false, _mut45063 = false, _mut45064 = false, _mut45065 = false, _mut45066 = false, _mut45067 = false, _mut45068 = false, _mut45069 = false, _mut45070 = false, _mut45071 = false, _mut45072 = false, _mut45073 = false, _mut45074 = false, _mut45075 = false, _mut45076 = false, _mut45077 = false, _mut45078 = false, _mut45079 = false, _mut45080 = false, _mut45081 = false, _mut45082 = false, _mut45083 = false, _mut45084 = false, _mut45085 = false, _mut45086 = false, _mut45087 = false, _mut45088 = false, _mut45089 = false, _mut45090 = false, _mut45091 = false, _mut45092 = false, _mut45093 = false, _mut45094 = false, _mut45095 = false, _mut45096 = false, _mut45097 = false, _mut45098 = false, _mut45099 = false, _mut45100 = false, _mut45101 = false, _mut45102 = false, _mut45103 = false, _mut45104 = false, _mut45105 = false, _mut45106 = false, _mut45107 = false, _mut45108 = false, _mut45109 = false, _mut45110 = false, _mut45111 = false, _mut45112 = false, _mut45113 = false, _mut45114 = false, _mut45115 = false, _mut45116 = false, _mut45117 = false, _mut45118 = false, _mut45119 = false, _mut45120 = false, _mut45121 = false, _mut45122 = false, _mut45123 = false, _mut45124 = false, _mut45125 = false, _mut45126 = false, _mut45127 = false, _mut45128 = false, _mut45129 = false, _mut45130 = false, _mut45131 = false, _mut45132 = false, _mut45133 = false, _mut45134 = false, _mut45135 = false, _mut45136 = false, _mut45137 = false, _mut45138 = false, _mut45139 = false, _mut45140 = false, _mut45141 = false, _mut45142 = false, _mut45143 = false, _mut45144 = false, _mut45145 = false, _mut45146 = false, _mut45147 = false, _mut45148 = false, _mut45149 = false, _mut45150 = false, _mut45151 = false, _mut45152 = false, _mut45153 = false, _mut45154 = false, _mut45155 = false, _mut45156 = false, _mut45157 = false, _mut45158 = false, _mut45159 = false, _mut45160 = false, _mut45161 = false, _mut45162 = false, _mut45163 = false, _mut45164 = false, _mut45165 = false, _mut45166 = false, _mut45167 = false, _mut45168 = false, _mut45169 = false, _mut45170 = false, _mut45171 = false, _mut45172 = false, _mut45173 = false, _mut45174 = false, _mut45175 = false, _mut45176 = false, _mut45177 = false, _mut45178 = false, _mut45179 = false, _mut45180 = false, _mut45181 = false, _mut45182 = false, _mut45183 = false, _mut45184 = false, _mut45185 = false, _mut45186 = false, _mut45187 = false, _mut45188 = false, _mut45189 = false, _mut45190 = false, _mut45191 = false, _mut45192 = false, _mut45193 = false, _mut45194 = false, _mut45195 = false, _mut45196 = false, _mut45197 = false, _mut45198 = false, _mut45199 = false, _mut45200 = false, _mut45201 = false, _mut45202 = false, _mut45203 = false, _mut45204 = false, _mut45205 = false, _mut45206 = false, _mut45207 = false, _mut45208 = false, _mut45209 = false, _mut45210 = false, _mut45211 = false, _mut45212 = false, _mut45213 = false, _mut45214 = false, _mut45215 = false, _mut45216 = false, _mut45217 = false, _mut45218 = false, _mut45219 = false, _mut45220 = false, _mut45221 = false, _mut45222 = false, _mut45223 = false, _mut45224 = false, _mut45225 = false, _mut45226 = false, _mut45227 = false, _mut45228 = false, _mut45229 = false, _mut45230 = false, _mut45231 = false, _mut45232 = false, _mut45233 = false, _mut45234 = false, _mut45235 = false, _mut45236 = false, _mut45237 = false, _mut45238 = false, _mut45239 = false, _mut45240 = false, _mut45241 = false, _mut45242 = false, _mut45243 = false, _mut45244 = false, _mut45245 = false, _mut45246 = false, _mut45247 = false, _mut45248 = false, _mut45249 = false, _mut45250 = false, _mut45251 = false, _mut45252 = false, _mut45253 = false, _mut45254 = false, _mut45255 = false, _mut45256 = false, _mut45257 = false, _mut45258 = false, _mut45259 = false, _mut45260 = false, _mut45261 = false, _mut45262 = false, _mut45263 = false, _mut45264 = false, _mut45265 = false, _mut45266 = false, _mut45267 = false, _mut45268 = false, _mut45269 = false, _mut45270 = false, _mut45271 = false, _mut45272 = false, _mut45273 = false, _mut45274 = false, _mut45275 = false, _mut45276 = false, _mut45277 = false, _mut45278 = false, _mut45279 = false, _mut45280 = false, _mut45281 = false, _mut45282 = false, _mut45283 = false, _mut45284 = false, _mut45285 = false, _mut45286 = false, _mut45287 = false, _mut45288 = false, _mut45289 = false, _mut45290 = false, _mut45291 = false, _mut45292 = false, _mut45293 = false, _mut45294 = false, _mut45295 = false, _mut45296 = false, _mut45297 = false, _mut45298 = false, _mut45299 = false, _mut45300 = false, _mut45301 = false, _mut45302 = false, _mut45303 = false, _mut45304 = false, _mut45305 = false, _mut45306 = false, _mut45307 = false, _mut45308 = false, _mut45309 = false, _mut45310 = false, _mut45311 = false, _mut45312 = false, _mut45313 = false, _mut45314 = false, _mut45315 = false, _mut45316 = false, _mut45317 = false, _mut45318 = false, _mut45319 = false, _mut45320 = false, _mut45321 = false, _mut45322 = false, _mut45323 = false, _mut45324 = false, _mut45325 = false, _mut45326 = false, _mut45327 = false, _mut45328 = false, _mut45329 = false, _mut45330 = false, _mut45331 = false, _mut45332 = false, _mut45333 = false, _mut45334 = false, _mut45335 = false, _mut45336 = false, _mut45337 = false, _mut45338 = false, _mut45339 = false, _mut45340 = false, _mut45341 = false, _mut45342 = false, _mut45343 = false, _mut45344 = false, _mut45345 = false, _mut45346 = false, _mut45347 = false, _mut45348 = false, _mut45349 = false, _mut45350 = false, _mut45351 = false, _mut45352 = false, _mut45353 = false, _mut45354 = false, _mut45355 = false, _mut45356 = false, _mut45357 = false, _mut45358 = false, _mut45359 = false, _mut45360 = false, _mut45361 = false, _mut45362 = false, _mut45363 = false, _mut45364 = false, _mut45365 = false, _mut45366 = false, _mut45367 = false, _mut45368 = false, _mut45369 = false, _mut45370 = false, _mut45371 = false, _mut45372 = false, _mut45373 = false, _mut45374 = false, _mut45375 = false, _mut45376 = false, _mut45377 = false, _mut45378 = false, _mut45379 = false, _mut45380 = false, _mut45381 = false, _mut45382 = false, _mut45383 = false, _mut45384 = false, _mut45385 = false, _mut45386 = false, _mut45387 = false, _mut45388 = false, _mut45389 = false, _mut45390 = false, _mut45391 = false, _mut45392 = false, _mut45393 = false, _mut45394 = false, _mut45395 = false, _mut45396 = false, _mut45397 = false, _mut45398 = false, _mut45399 = false, _mut45400 = false, _mut45401 = false, _mut45402 = false, _mut45403 = false, _mut45404 = false, _mut45405 = false, _mut45406 = false, _mut45407 = false, _mut45408 = false, _mut45409 = false, _mut45410 = false, _mut45411 = false, _mut45412 = false, _mut45413 = false, _mut45414 = false, _mut45415 = false, _mut45416 = false, _mut45417 = false, _mut45418 = false, _mut45419 = false, _mut45420 = false, _mut45421 = false, _mut45422 = false, _mut45423 = false, _mut45424 = false, _mut45425 = false, _mut45426 = false, _mut45427 = false, _mut45428 = false, _mut45429 = false, _mut45430 = false, _mut45431 = false, _mut45432 = false, _mut45433 = false, _mut45434 = false, _mut45435 = false, _mut45436 = false, _mut45437 = false, _mut45438 = false, _mut45439 = false, _mut45440 = false, _mut45441 = false, _mut45442 = false, _mut45443 = false, _mut45444 = false, _mut45445 = false, _mut45446 = false, _mut45447 = false, _mut45448 = false, _mut45449 = false, _mut45450 = false, _mut45451 = false, _mut45452 = false, _mut45453 = false, _mut45454 = false, _mut45455 = false, _mut45456 = false, _mut45457 = false, _mut45458 = false, _mut45459 = false, _mut45460 = false, _mut45461 = false, _mut45462 = false, _mut45463 = false, _mut45464 = false, _mut45465 = false, _mut45466 = false, _mut45467 = false, _mut45468 = false, _mut45469 = false, _mut45470 = false, _mut45471 = false, _mut45472 = false, _mut45473 = false, _mut45474 = false, _mut45475 = false, _mut45476 = false, _mut45477 = false, _mut45478 = false, _mut45479 = false, _mut45480 = false, _mut45481 = false, _mut45482 = false, _mut45483 = false, _mut45484 = false, _mut45485 = false, _mut45486 = false, _mut45487 = false, _mut45488 = false, _mut45489 = false, _mut45490 = false, _mut45491 = false, _mut45492 = false, _mut45493 = false, _mut45494 = false, _mut45495 = false, _mut45496 = false, _mut45497 = false, _mut45498 = false, _mut45499 = false, _mut45500 = false, _mut45501 = false, _mut45502 = false, _mut45503 = false, _mut45504 = false, _mut45505 = false, _mut45506 = false, _mut45507 = false, _mut45508 = false, _mut45509 = false, _mut45510 = false, _mut45511 = false, _mut45512 = false, _mut45513 = false, _mut45514 = false, _mut45515 = false, _mut45516 = false, _mut45517 = false, _mut45518 = false, _mut45519 = false, _mut45520 = false, _mut45521 = false, _mut45522 = false, _mut45523 = false, _mut45524 = false, _mut45525 = false, _mut45526 = false, _mut45527 = false, _mut45528 = false, _mut45529 = false, _mut45530 = false, _mut45531 = false, _mut45532 = false, _mut45533 = false, _mut45534 = false, _mut45535 = false, _mut45536 = false, _mut45537 = false, _mut45538 = false, _mut45539 = false, _mut45540 = false, _mut45541 = false, _mut45542 = false, _mut45543 = false, _mut45544 = false, _mut45545 = false, _mut45546 = false, _mut45547 = false, _mut45548 = false, _mut45549 = false, _mut45550 = false, _mut45551 = false, _mut45552 = false, _mut45553 = false, _mut45554 = false, _mut45555 = false, _mut45556 = false, _mut45557 = false, _mut45558 = false, _mut45559 = false, _mut45560 = false, _mut45561 = false, _mut45562 = false, _mut45563 = false, _mut45564 = false, _mut45565 = false, _mut45566 = false, _mut45567 = false, _mut45568 = false, _mut45569 = false, _mut45570 = false, _mut45571 = false, _mut45572 = false, _mut45573 = false, _mut45574 = false, _mut45575 = false, _mut45576 = false, _mut45577 = false, _mut45578 = false, _mut45579 = false, _mut45580 = false, _mut45581 = false, _mut45582 = false, _mut45583 = false, _mut45584 = false, _mut45585 = false, _mut45586 = false, _mut45587 = false, _mut45588 = false, _mut45589 = false, _mut45590 = false, _mut45591 = false, _mut45592 = false, _mut45593 = false, _mut45594 = false, _mut45595 = false, _mut45596 = false, _mut45597 = false, _mut45598 = false, _mut45599 = false, _mut45600 = false, _mut45601 = false, _mut45602 = false, _mut45603 = false, _mut45604 = false, _mut45605 = false, _mut45606 = false, _mut45607 = false, _mut45608 = false, _mut45609 = false, _mut45610 = false, _mut45611 = false, _mut45612 = false, _mut45613 = false, _mut45614 = false, _mut45615 = false, _mut45616 = false, _mut45617 = false, _mut45618 = false, _mut45619 = false, _mut45620 = false, _mut45621 = false, _mut45622 = false, _mut45623 = false, _mut45624 = false, _mut45625 = false, _mut45626 = false, _mut45627 = false, _mut45628 = false, _mut45629 = false, _mut45630 = false, _mut45631 = false, _mut45632 = false, _mut45633 = false, _mut45634 = false, _mut45635 = false, _mut45636 = false, _mut45637 = false, _mut45638 = false, _mut45639 = false, _mut45640 = false, _mut45641 = false, _mut45642 = false, _mut45643 = false, _mut45644 = false, _mut45645 = false, _mut45646 = false, _mut45647 = false, _mut45648 = false, _mut45649 = false, _mut45650 = false, _mut45651 = false, _mut45652 = false, _mut45653 = false, _mut45654 = false, _mut45655 = false, _mut45656 = false, _mut45657 = false, _mut45658 = false, _mut45659 = false, _mut45660 = false, _mut45661 = false, _mut45662 = false, _mut45663 = false, _mut45664 = false, _mut45665 = false, _mut45666 = false, _mut45667 = false, _mut45668 = false, _mut45669 = false, _mut45670 = false, _mut45671 = false, _mut45672 = false, _mut45673 = false, _mut45674 = false, _mut45675 = false, _mut45676 = false, _mut45677 = false, _mut45678 = false, _mut45679 = false, _mut45680 = false, _mut45681 = false, _mut45682 = false, _mut45683 = false, _mut45684 = false, _mut45685 = false, _mut45686 = false, _mut45687 = false, _mut45688 = false, _mut45689 = false, _mut45690 = false, _mut45691 = false, _mut45692 = false, _mut45693 = false, _mut45694 = false, _mut45695 = false, _mut45696 = false, _mut45697 = false, _mut45698 = false, _mut45699 = false, _mut45700 = false, _mut45701 = false, _mut45702 = false, _mut45703 = false, _mut45704 = false, _mut45705 = false, _mut45706 = false, _mut45707 = false, _mut45708 = false, _mut45709 = false, _mut45710 = false, _mut45711 = false, _mut45712 = false, _mut45713 = false, _mut45714 = false, _mut45715 = false, _mut45716 = false, _mut45717 = false, _mut45718 = false, _mut45719 = false, _mut45720 = false, _mut45721 = false, _mut45722 = false, _mut45723 = false, _mut45724 = false, _mut45725 = false, _mut45726 = false, _mut45727 = false, _mut45728 = false, _mut45729 = false, _mut45730 = false, _mut45731 = false, _mut45732 = false, _mut45733 = false, _mut45734 = false, _mut45735 = false, _mut45736 = false, _mut45737 = false, _mut45738 = false, _mut45739 = false, _mut45740 = false, _mut45741 = false, _mut45742 = false, _mut45743 = false, _mut45744 = false, _mut45745 = false, _mut45746 = false, _mut45747 = false, _mut45748 = false, _mut45749 = false, _mut45750 = false, _mut45751 = false, _mut45752 = false, _mut45753 = false, _mut45754 = false, _mut45755 = false, _mut45756 = false, _mut45757 = false, _mut45758 = false, _mut45759 = false, _mut45760 = false, _mut45761 = false, _mut45762 = false, _mut45763 = false, _mut45764 = false, _mut45765 = false, _mut45766 = false, _mut45767 = false, _mut45768 = false, _mut45769 = false, _mut45770 = false, _mut45771 = false, _mut45772 = false, _mut45773 = false, _mut45774 = false, _mut45775 = false, _mut45776 = false, _mut45777 = false, _mut45778 = false, _mut45779 = false, _mut45780 = false, _mut45781 = false, _mut45782 = false, _mut45783 = false, _mut45784 = false, _mut45785 = false, _mut45786 = false, _mut45787 = false, _mut45788 = false, _mut45789 = false, _mut45790 = false, _mut45791 = false, _mut45792 = false, _mut45793 = false, _mut45794 = false, _mut45795 = false, _mut45796 = false, _mut45797 = false, _mut45798 = false, _mut45799 = false, _mut45800 = false, _mut45801 = false, _mut45802 = false, _mut45803 = false, _mut45804 = false, _mut45805 = false, _mut45806 = false, _mut45807 = false, _mut45808 = false, _mut45809 = false, _mut45810 = false, _mut45811 = false, _mut45812 = false, _mut45813 = false, _mut45814 = false, _mut45815 = false, _mut45816 = false, _mut45817 = false, _mut45818 = false, _mut45819 = false, _mut45820 = false, _mut45821 = false, _mut45822 = false, _mut45823 = false, _mut45824 = false, _mut45825 = false, _mut45826 = false, _mut45827 = false, _mut45828 = false, _mut45829 = false, _mut45830 = false, _mut45831 = false, _mut45832 = false, _mut45833 = false, _mut45834 = false, _mut45835 = false, _mut45836 = false, _mut45837 = false, _mut45838 = false, _mut45839 = false, _mut45840 = false, _mut45841 = false, _mut45842 = false, _mut45843 = false, _mut45844 = false, _mut45845 = false, _mut45846 = false, _mut45847 = false, _mut45848 = false, _mut45849 = false, _mut45850 = false, _mut45851 = false, _mut45852 = false, _mut45853 = false, _mut45854 = false, _mut45855 = false, _mut45856 = false, _mut45857 = false, _mut45858 = false, _mut45859 = false, _mut45860 = false, _mut45861 = false, _mut45862 = false, _mut45863 = false, _mut45864 = false, _mut45865 = false, _mut45866 = false, _mut45867 = false, _mut45868 = false, _mut45869 = false, _mut45870 = false, _mut45871 = false, _mut45872 = false, _mut45873 = false, _mut45874 = false, _mut45875 = false, _mut45876 = false, _mut45877 = false, _mut45878 = false, _mut45879 = false, _mut45880 = false, _mut45881 = false, _mut45882 = false, _mut45883 = false, _mut45884 = false, _mut45885 = false, _mut45886 = false, _mut45887 = false, _mut45888 = false, _mut45889 = false, _mut45890 = false, _mut45891 = false, _mut45892 = false, _mut45893 = false, _mut45894 = false, _mut45895 = false, _mut45896 = false, _mut45897 = false, _mut45898 = false, _mut45899 = false, _mut45900 = false, _mut45901 = false, _mut45902 = false, _mut45903 = false, _mut45904 = false, _mut45905 = false, _mut45906 = false, _mut45907 = false, _mut45908 = false, _mut45909 = false, _mut45910 = false, _mut45911 = false, _mut45912 = false, _mut45913 = false, _mut45914 = false, _mut45915 = false, _mut45916 = false, _mut45917 = false, _mut45918 = false, _mut45919 = false, _mut45920 = false, _mut45921 = false, _mut45922 = false, _mut45923 = false, _mut45924 = false, _mut45925 = false, _mut45926 = false, _mut45927 = false, _mut45928 = false, _mut45929 = false, _mut45930 = false, _mut45931 = false, _mut45932 = false, _mut45933 = false, _mut45934 = false, _mut45935 = false, _mut45936 = false, _mut45937 = false, _mut45938 = false, _mut45939 = false, _mut45940 = false, _mut45941 = false, _mut45942 = false, _mut45943 = false, _mut45944 = false, _mut45945 = false, _mut45946 = false, _mut45947 = false, _mut45948 = false, _mut45949 = false, _mut45950 = false, _mut45951 = false, _mut45952 = false, _mut45953 = false, _mut45954 = false, _mut45955 = false, _mut45956 = false, _mut45957 = false, _mut45958 = false, _mut45959 = false, _mut45960 = false, _mut45961 = false, _mut45962 = false, _mut45963 = false, _mut45964 = false, _mut45965 = false, _mut45966 = false, _mut45967 = false, _mut45968 = false, _mut45969 = false, _mut45970 = false, _mut45971 = false, _mut45972 = false, _mut45973 = false, _mut45974 = false, _mut45975 = false, _mut45976 = false, _mut45977 = false, _mut45978 = false, _mut45979 = false, _mut45980 = false, _mut45981 = false, _mut45982 = false, _mut45983 = false, _mut45984 = false, _mut45985 = false, _mut45986 = false, _mut45987 = false, _mut45988 = false, _mut45989 = false, _mut45990 = false, _mut45991 = false, _mut45992 = false, _mut45993 = false, _mut45994 = false, _mut45995 = false, _mut45996 = false, _mut45997 = false, _mut45998 = false, _mut45999 = false, _mut46000 = false, _mut46001 = false, _mut46002 = false, _mut46003 = false, _mut46004 = false, _mut46005 = false, _mut46006 = false, _mut46007 = false, _mut46008 = false, _mut46009 = false, _mut46010 = false, _mut46011 = false, _mut46012 = false, _mut46013 = false, _mut46014 = false, _mut46015 = false, _mut46016 = false, _mut46017 = false, _mut46018 = false, _mut46019 = false, _mut46020 = false, _mut46021 = false, _mut46022 = false, _mut46023 = false, _mut46024 = false, _mut46025 = false, _mut46026 = false, _mut46027 = false, _mut46028 = false, _mut46029 = false, _mut46030 = false, _mut46031 = false, _mut46032 = false, _mut46033 = false, _mut46034 = false, _mut46035 = false, _mut46036 = false, _mut46037 = false, _mut46038 = false, _mut46039 = false, _mut46040 = false, _mut46041 = false, _mut46042 = false, _mut46043 = false, _mut46044 = false, _mut46045 = false, _mut46046 = false, _mut46047 = false, _mut46048 = false, _mut46049 = false, _mut46050 = false, _mut46051 = false, _mut46052 = false, _mut46053 = false, _mut46054 = false, _mut46055 = false, _mut46056 = false, _mut46057 = false, _mut46058 = false, _mut46059 = false, _mut46060 = false, _mut46061 = false, _mut46062 = false, _mut46063 = false, _mut46064 = false, _mut46065 = false, _mut46066 = false, _mut46067 = false, _mut46068 = false, _mut46069 = false, _mut46070 = false, _mut46071 = false, _mut46072 = false, _mut46073 = false, _mut46074 = false, _mut46075 = false, _mut46076 = false, _mut46077 = false, _mut46078 = false, _mut46079 = false, _mut46080 = false, _mut46081 = false, _mut46082 = false, _mut46083 = false, _mut46084 = false, _mut46085 = false, _mut46086 = false, _mut46087 = false, _mut46088 = false, _mut46089 = false, _mut46090 = false, _mut46091 = false, _mut46092 = false, _mut46093 = false, _mut46094 = false, _mut46095 = false, _mut46096 = false, _mut46097 = false, _mut46098 = false, _mut46099 = false, _mut46100 = false, _mut46101 = false, _mut46102 = false, _mut46103 = false, _mut46104 = false, _mut46105 = false, _mut46106 = false, _mut46107 = false, _mut46108 = false, _mut46109 = false, _mut46110 = false, _mut46111 = false, _mut46112 = false, _mut46113 = false, _mut46114 = false, _mut46115 = false, _mut46116 = false, _mut46117 = false, _mut46118 = false, _mut46119 = false, _mut46120 = false, _mut46121 = false, _mut46122 = false, _mut46123 = false, _mut46124 = false, _mut46125 = false, _mut46126 = false, _mut46127 = false, _mut46128 = false, _mut46129 = false, _mut46130 = false, _mut46131 = false, _mut46132 = false, _mut46133 = false, _mut46134 = false, _mut46135 = false, _mut46136 = false, _mut46137 = false, _mut46138 = false, _mut46139 = false, _mut46140 = false, _mut46141 = false, _mut46142 = false, _mut46143 = false, _mut46144 = false, _mut46145 = false, _mut46146 = false, _mut46147 = false, _mut46148 = false, _mut46149 = false, _mut46150 = false, _mut46151 = false, _mut46152 = false, _mut46153 = false, _mut46154 = false, _mut46155 = false, _mut46156 = false, _mut46157 = false, _mut46158 = false, _mut46159 = false, _mut46160 = false, _mut46161 = false, _mut46162 = false, _mut46163 = false, _mut46164 = false, _mut46165 = false, _mut46166 = false, _mut46167 = false, _mut46168 = false, _mut46169 = false, _mut46170 = false, _mut46171 = false, _mut46172 = false, _mut46173 = false, _mut46174 = false, _mut46175 = false, _mut46176 = false, _mut46177 = false, _mut46178 = false, _mut46179 = false, _mut46180 = false, _mut46181 = false, _mut46182 = false, _mut46183 = false, _mut46184 = false, _mut46185 = false, _mut46186 = false, _mut46187 = false, _mut46188 = false, _mut46189 = false, _mut46190 = false, _mut46191 = false, _mut46192 = false, _mut46193 = false, _mut46194 = false, _mut46195 = false, _mut46196 = false, _mut46197 = false, _mut46198 = false, _mut46199 = false, _mut46200 = false, _mut46201 = false, _mut46202 = false, _mut46203 = false, _mut46204 = false, _mut46205 = false, _mut46206 = false, _mut46207 = false, _mut46208 = false, _mut46209 = false, _mut46210 = false, _mut46211 = false, _mut46212 = false, _mut46213 = false, _mut46214 = false, _mut46215 = false, _mut46216 = false, _mut46217 = false, _mut46218 = false, _mut46219 = false, _mut46220 = false, _mut46221 = false, _mut46222 = false, _mut46223 = false, _mut46224 = false, _mut46225 = false, _mut46226 = false, _mut46227 = false, _mut46228 = false, _mut46229 = false, _mut46230 = false, _mut46231 = false, _mut46232 = false, _mut46233 = false, _mut46234 = false, _mut46235 = false, _mut46236 = false, _mut46237 = false, _mut46238 = false, _mut46239 = false, _mut46240 = false, _mut46241 = false, _mut46242 = false, _mut46243 = false, _mut46244 = false, _mut46245 = false, _mut46246 = false, _mut46247 = false, _mut46248 = false, _mut46249 = false, _mut46250 = false, _mut46251 = false, _mut46252 = false, _mut46253 = false, _mut46254 = false, _mut46255 = false, _mut46256 = false, _mut46257 = false, _mut46258 = false, _mut46259 = false, _mut46260 = false, _mut46261 = false, _mut46262 = false, _mut46263 = false, _mut46264 = false, _mut46265 = false, _mut46266 = false, _mut46267 = false, _mut46268 = false, _mut46269 = false, _mut46270 = false, _mut46271 = false, _mut46272 = false, _mut46273 = false, _mut46274 = false, _mut46275 = false, _mut46276 = false, _mut46277 = false, _mut46278 = false, _mut46279 = false, _mut46280 = false, _mut46281 = false, _mut46282 = false, _mut46283 = false, _mut46284 = false, _mut46285 = false, _mut46286 = false, _mut46287 = false, _mut46288 = false, _mut46289 = false, _mut46290 = false, _mut46291 = false, _mut46292 = false, _mut46293 = false, _mut46294 = false, _mut46295 = false, _mut46296 = false, _mut46297 = false, _mut46298 = false, _mut46299 = false, _mut46300 = false, _mut46301 = false, _mut46302 = false, _mut46303 = false, _mut46304 = false, _mut46305 = false, _mut46306 = false, _mut46307 = false, _mut46308 = false, _mut46309 = false, _mut46310 = false, _mut46311 = false, _mut46312 = false, _mut46313 = false, _mut46314 = false, _mut46315 = false, _mut46316 = false, _mut46317 = false, _mut46318 = false, _mut46319 = false, _mut46320 = false, _mut46321 = false, _mut46322 = false, _mut46323 = false, _mut46324 = false, _mut46325 = false, _mut46326 = false, _mut46327 = false, _mut46328 = false, _mut46329 = false, _mut46330 = false, _mut46331 = false, _mut46332 = false, _mut46333 = false, _mut46334 = false, _mut46335 = false, _mut46336 = false, _mut46337 = false, _mut46338 = false, _mut46339 = false, _mut46340 = false, _mut46341 = false, _mut46342 = false, _mut46343 = false, _mut46344 = false, _mut46345 = false, _mut46346 = false, _mut46347 = false, _mut46348 = false, _mut46349 = false, _mut46350 = false, _mut46351 = false, _mut46352 = false, _mut46353 = false, _mut46354 = false, _mut46355 = false, _mut46356 = false, _mut46357 = false, _mut46358 = false, _mut46359 = false, _mut46360 = false, _mut46361 = false, _mut46362 = false, _mut46363 = false, _mut46364 = false, _mut46365 = false, _mut46366 = false, _mut46367 = false, _mut46368 = false, _mut46369 = false, _mut46370 = false, _mut46371 = false, _mut46372 = false, _mut46373 = false, _mut46374 = false, _mut46375 = false, _mut46376 = false, _mut46377 = false, _mut46378 = false, _mut46379 = false, _mut46380 = false, _mut46381 = false, _mut46382 = false, _mut46383 = false, _mut46384 = false, _mut46385 = false, _mut46386 = false, _mut46387 = false, _mut46388 = false, _mut46389 = false, _mut46390 = false, _mut46391 = false, _mut46392 = false, _mut46393 = false, _mut46394 = false, _mut46395 = false, _mut46396 = false, _mut46397 = false, _mut46398 = false, _mut46399 = false, _mut46400 = false, _mut46401 = false, _mut46402 = false, _mut46403 = false, _mut46404 = false, _mut46405 = false, _mut46406 = false, _mut46407 = false, _mut46408 = false, _mut46409 = false, _mut46410 = false, _mut46411 = false, _mut46412 = false, _mut46413 = false, _mut46414 = false, _mut46415 = false, _mut46416 = false, _mut46417 = false, _mut46418 = false, _mut46419 = false, _mut46420 = false, _mut46421 = false, _mut46422 = false, _mut46423 = false, _mut46424 = false, _mut46425 = false, _mut46426 = false, _mut46427 = false, _mut46428 = false, _mut46429 = false, _mut46430 = false, _mut46431 = false, _mut46432 = false, _mut46433 = false, _mut46434 = false, _mut46435 = false, _mut46436 = false, _mut46437 = false, _mut46438 = false, _mut46439 = false, _mut46440 = false, _mut46441 = false, _mut46442 = false, _mut46443 = false, _mut46444 = false, _mut46445 = false, _mut46446 = false, _mut46447 = false, _mut46448 = false, _mut46449 = false, _mut46450 = false, _mut46451 = false, _mut46452 = false, _mut46453 = false, _mut46454 = false, _mut46455 = false, _mut46456 = false, _mut46457 = false, _mut46458 = false, _mut46459 = false, _mut46460 = false, _mut46461 = false, _mut46462 = false, _mut46463 = false, _mut46464 = false, _mut46465 = false, _mut46466 = false, _mut46467 = false, _mut46468 = false, _mut46469 = false, _mut46470 = false, _mut46471 = false, _mut46472 = false, _mut46473 = false, _mut46474 = false, _mut46475 = false, _mut46476 = false, _mut46477 = false, _mut46478 = false, _mut46479 = false, _mut46480 = false, _mut46481 = false, _mut46482 = false, _mut46483 = false, _mut46484 = false, _mut46485 = false, _mut46486 = false, _mut46487 = false, _mut46488 = false, _mut46489 = false, _mut46490 = false, _mut46491 = false, _mut46492 = false, _mut46493 = false, _mut46494 = false, _mut46495 = false, _mut46496 = false, _mut46497 = false, _mut46498 = false, _mut46499 = false, _mut46500 = false, _mut46501 = false, _mut46502 = false, _mut46503 = false, _mut46504 = false, _mut46505 = false, _mut46506 = false, _mut46507 = false, _mut46508 = false, _mut46509 = false, _mut46510 = false, _mut46511 = false, _mut46512 = false, _mut46513 = false, _mut46514 = false, _mut46515 = false, _mut46516 = false, _mut46517 = false, _mut46518 = false, _mut46519 = false, _mut46520 = false, _mut46521 = false, _mut46522 = false, _mut46523 = false, _mut46524 = false, _mut46525 = false, _mut46526 = false, _mut46527 = false, _mut46528 = false, _mut46529 = false, _mut46530 = false, _mut46531 = false, _mut46532 = false, _mut46533 = false, _mut46534 = false, _mut46535 = false, _mut46536 = false, _mut46537 = false, _mut46538 = false, _mut46539 = false, _mut46540 = false, _mut46541 = false, _mut46542 = false, _mut46543 = false, _mut46544 = false, _mut46545 = false, _mut46546 = false, _mut46547 = false, _mut46548 = false, _mut46549 = false, _mut46550 = false, _mut46551 = false, _mut46552 = false, _mut46553 = false, _mut46554 = false, _mut46555 = false, _mut46556 = false, _mut46557 = false, _mut46558 = false, _mut46559 = false, _mut46560 = false, _mut46561 = false, _mut46562 = false, _mut46563 = false, _mut46564 = false, _mut46565 = false, _mut46566 = false, _mut46567 = false, _mut46568 = false, _mut46569 = false, _mut46570 = false, _mut46571 = false, _mut46572 = false, _mut46573 = false, _mut46574 = false, _mut46575 = false, _mut46576 = false, _mut46577 = false, _mut46578 = false, _mut46579 = false, _mut46580 = false, _mut46581 = false, _mut46582 = false, _mut46583 = false, _mut46584 = false, _mut46585 = false, _mut46586 = false, _mut46587 = false, _mut46588 = false, _mut46589 = false, _mut46590 = false, _mut46591 = false, _mut46592 = false, _mut46593 = false, _mut46594 = false, _mut46595 = false, _mut46596 = false, _mut46597 = false, _mut46598 = false, _mut46599 = false, _mut46600 = false, _mut46601 = false, _mut46602 = false, _mut46603 = false, _mut46604 = false, _mut46605 = false, _mut46606 = false, _mut46607 = false, _mut46608 = false, _mut46609 = false, _mut46610 = false, _mut46611 = false, _mut46612 = false, _mut46613 = false, _mut46614 = false, _mut46615 = false, _mut46616 = false, _mut46617 = false, _mut46618 = false, _mut46619 = false, _mut46620 = false, _mut46621 = false, _mut46622 = false, _mut46623 = false, _mut46624 = false, _mut46625 = false, _mut46626 = false, _mut46627 = false, _mut46628 = false, _mut46629 = false, _mut46630 = false, _mut46631 = false, _mut46632 = false, _mut46633 = false, _mut46634 = false, _mut46635 = false, _mut46636 = false, _mut46637 = false, _mut46638 = false, _mut46639 = false, _mut46640 = false, _mut46641 = false, _mut46642 = false, _mut46643 = false, _mut46644 = false, _mut46645 = false, _mut46646 = false, _mut46647 = false, _mut46648 = false, _mut46649 = false, _mut46650 = false, _mut46651 = false, _mut46652 = false, _mut46653 = false, _mut46654 = false, _mut46655 = false, _mut46656 = false, _mut46657 = false, _mut46658 = false, _mut46659 = false, _mut46660 = false, _mut46661 = false, _mut46662 = false, _mut46663 = false, _mut46664 = false, _mut46665 = false, _mut46666 = false, _mut46667 = false, _mut46668 = false, _mut46669 = false, _mut46670 = false, _mut46671 = false, _mut46672 = false, _mut46673 = false, _mut46674 = false, _mut46675 = false, _mut46676 = false, _mut46677 = false, _mut46678 = false, _mut46679 = false, _mut46680 = false, _mut46681 = false, _mut46682 = false, _mut46683 = false, _mut46684 = false, _mut46685 = false, _mut46686 = false, _mut46687 = false, _mut46688 = false, _mut46689 = false, _mut46690 = false, _mut46691 = false, _mut46692 = false, _mut46693 = false, _mut46694 = false, _mut46695 = false, _mut46696 = false, _mut46697 = false, _mut46698 = false, _mut46699 = false, _mut46700 = false, _mut46701 = false, _mut46702 = false, _mut46703 = false, _mut46704 = false, _mut46705 = false, _mut46706 = false, _mut46707 = false, _mut46708 = false, _mut46709 = false, _mut46710 = false, _mut46711 = false, _mut46712 = false, _mut46713 = false, _mut46714 = false, _mut46715 = false, _mut46716 = false, _mut46717 = false, _mut46718 = false, _mut46719 = false, _mut46720 = false, _mut46721 = false, _mut46722 = false, _mut46723 = false, _mut46724 = false, _mut46725 = false, _mut46726 = false, _mut46727 = false, _mut46728 = false, _mut46729 = false, _mut46730 = false, _mut46731 = false, _mut46732 = false, _mut46733 = false, _mut46734 = false, _mut46735 = false, _mut46736 = false, _mut46737 = false, _mut46738 = false, _mut46739 = false, _mut46740 = false, _mut46741 = false, _mut46742 = false, _mut46743 = false, _mut46744 = false, _mut46745 = false, _mut46746 = false, _mut46747 = false, _mut46748 = false, _mut46749 = false, _mut46750 = false, _mut46751 = false, _mut46752 = false, _mut46753 = false, _mut46754 = false, _mut46755 = false, _mut46756 = false, _mut46757 = false, _mut46758 = false, _mut46759 = false, _mut46760 = false, _mut46761 = false, _mut46762 = false, _mut46763 = false, _mut46764 = false, _mut46765 = false, _mut46766 = false, _mut46767 = false, _mut46768 = false, _mut46769 = false, _mut46770 = false, _mut46771 = false, _mut46772 = false, _mut46773 = false, _mut46774 = false, _mut46775 = false, _mut46776 = false, _mut46777 = false, _mut46778 = false, _mut46779 = false, _mut46780 = false, _mut46781 = false, _mut46782 = false, _mut46783 = false, _mut46784 = false, _mut46785 = false, _mut46786 = false, _mut46787 = false, _mut46788 = false, _mut46789 = false, _mut46790 = false, _mut46791 = false, _mut46792 = false, _mut46793 = false, _mut46794 = false, _mut46795 = false, _mut46796 = false, _mut46797 = false, _mut46798 = false, _mut46799 = false, _mut46800 = false, _mut46801 = false, _mut46802 = false, _mut46803 = false, _mut46804 = false, _mut46805 = false, _mut46806 = false, _mut46807 = false, _mut46808 = false, _mut46809 = false, _mut46810 = false, _mut46811 = false, _mut46812 = false, _mut46813 = false, _mut46814 = false, _mut46815 = false, _mut46816 = false, _mut46817 = false, _mut46818 = false, _mut46819 = false, _mut46820 = false, _mut46821 = false, _mut46822 = false, _mut46823 = false, _mut46824 = false, _mut46825 = false, _mut46826 = false, _mut46827 = false, _mut46828 = false, _mut46829 = false, _mut46830 = false, _mut46831 = false, _mut46832 = false, _mut46833 = false, _mut46834 = false, _mut46835 = false, _mut46836 = false, _mut46837 = false, _mut46838 = false, _mut46839 = false, _mut46840 = false, _mut46841 = false, _mut46842 = false, _mut46843 = false, _mut46844 = false, _mut46845 = false, _mut46846 = false, _mut46847 = false, _mut46848 = false, _mut46849 = false, _mut46850 = false, _mut46851 = false, _mut46852 = false, _mut46853 = false, _mut46854 = false, _mut46855 = false, _mut46856 = false, _mut46857 = false, _mut46858 = false, _mut46859 = false, _mut46860 = false, _mut46861 = false, _mut46862 = false, _mut46863 = false, _mut46864 = false, _mut46865 = false, _mut46866 = false, _mut46867 = false, _mut46868 = false, _mut46869 = false, _mut46870 = false, _mut46871 = false, _mut46872 = false, _mut46873 = false, _mut46874 = false, _mut46875 = false, _mut46876 = false, _mut46877 = false, _mut46878 = false, _mut46879 = false, _mut46880 = false, _mut46881 = false, _mut46882 = false, _mut46883 = false, _mut46884 = false, _mut46885 = false, _mut46886 = false, _mut46887 = false, _mut46888 = false, _mut46889 = false, _mut46890 = false, _mut46891 = false, _mut46892 = false, _mut46893 = false, _mut46894 = false, _mut46895 = false, _mut46896 = false, _mut46897 = false, _mut46898 = false, _mut46899 = false, _mut46900 = false, _mut46901 = false, _mut46902 = false, _mut46903 = false, _mut46904 = false, _mut46905 = false, _mut46906 = false, _mut46907 = false, _mut46908 = false, _mut46909 = false, _mut46910 = false, _mut46911 = false, _mut46912 = false, _mut46913 = false, _mut46914 = false, _mut46915 = false, _mut46916 = false, _mut46917 = false, _mut46918 = false, _mut46919 = false, _mut46920 = false, _mut46921 = false, _mut46922 = false, _mut46923 = false, _mut46924 = false, _mut46925 = false, _mut46926 = false, _mut46927 = false, _mut46928 = false, _mut46929 = false, _mut46930 = false, _mut46931 = false, _mut46932 = false, _mut46933 = false, _mut46934 = false, _mut46935 = false, _mut46936 = false, _mut46937 = false, _mut46938 = false, _mut46939 = false, _mut46940 = false, _mut46941 = false, _mut46942 = false, _mut46943 = false, _mut46944 = false, _mut46945 = false, _mut46946 = false, _mut46947 = false, _mut46948 = false, _mut46949 = false, _mut46950 = false, _mut46951 = false, _mut46952 = false, _mut46953 = false, _mut46954 = false, _mut46955 = false, _mut46956 = false, _mut46957 = false, _mut46958 = false, _mut46959 = false, _mut46960 = false, _mut46961 = false, _mut46962 = false, _mut46963 = false, _mut46964 = false, _mut46965 = false, _mut46966 = false, _mut46967 = false, _mut46968 = false, _mut46969 = false, _mut46970 = false, _mut46971 = false, _mut46972 = false, _mut46973 = false, _mut46974 = false, _mut46975 = false, _mut46976 = false, _mut46977 = false, _mut46978 = false, _mut46979 = false, _mut46980 = false, _mut46981 = false, _mut46982 = false, _mut46983 = false, _mut46984 = false, _mut46985 = false, _mut46986 = false, _mut46987 = false, _mut46988 = false, _mut46989 = false, _mut46990 = false, _mut46991 = false, _mut46992 = false, _mut46993 = false, _mut46994 = false, _mut46995 = false, _mut46996 = false, _mut46997 = false, _mut46998 = false, _mut46999 = false, _mut47000 = false, _mut47001 = false, _mut47002 = false, _mut47003 = false, _mut47004 = false, _mut47005 = false, _mut47006 = false, _mut47007 = false, _mut47008 = false, _mut47009 = false, _mut47010 = false, _mut47011 = false, _mut47012 = false, _mut47013 = false, _mut47014 = false, _mut47015 = false, _mut47016 = false, _mut47017 = false, _mut47018 = false, _mut47019 = false, _mut47020 = false, _mut47021 = false, _mut47022 = false, _mut47023 = false, _mut47024 = false, _mut47025 = false, _mut47026 = false, _mut47027 = false, _mut47028 = false, _mut47029 = false, _mut47030 = false, _mut47031 = false, _mut47032 = false, _mut47033 = false, _mut47034 = false, _mut47035 = false, _mut47036 = false, _mut47037 = false, _mut47038 = false, _mut47039 = false, _mut47040 = false, _mut47041 = false, _mut47042 = false, _mut47043 = false, _mut47044 = false, _mut47045 = false, _mut47046 = false, _mut47047 = false, _mut47048 = false, _mut47049 = false, _mut47050 = false, _mut47051 = false, _mut47052 = false, _mut47053 = false, _mut47054 = false, _mut47055 = false, _mut47056 = false, _mut47057 = false, _mut47058 = false, _mut47059 = false, _mut47060 = false, _mut47061 = false, _mut47062 = false, _mut47063 = false, _mut47064 = false, _mut47065 = false, _mut47066 = false, _mut47067 = false, _mut47068 = false, _mut47069 = false, _mut47070 = false, _mut47071 = false, _mut47072 = false, _mut47073 = false, _mut47074 = false, _mut47075 = false, _mut47076 = false, _mut47077 = false, _mut47078 = false, _mut47079 = false, _mut47080 = false, _mut47081 = false, _mut47082 = false, _mut47083 = false, _mut47084 = false, _mut47085 = false, _mut47086 = false, _mut47087 = false, _mut47088 = false, _mut47089 = false, _mut47090 = false, _mut47091 = false, _mut47092 = false, _mut47093 = false, _mut47094 = false, _mut47095 = false, _mut47096 = false, _mut47097 = false, _mut47098 = false, _mut47099 = false, _mut47100 = false, _mut47101 = false, _mut47102 = false, _mut47103 = false, _mut47104 = false, _mut47105 = false, _mut47106 = false, _mut47107 = false, _mut47108 = false, _mut47109 = false, _mut47110 = false, _mut47111 = false, _mut47112 = false, _mut47113 = false, _mut47114 = false, _mut47115 = false, _mut47116 = false, _mut47117 = false, _mut47118 = false, _mut47119 = false, _mut47120 = false, _mut47121 = false, _mut47122 = false, _mut47123 = false, _mut47124 = false, _mut47125 = false, _mut47126 = false, _mut47127 = false, _mut47128 = false, _mut47129 = false, _mut47130 = false, _mut47131 = false, _mut47132 = false, _mut47133 = false, _mut47134 = false, _mut47135 = false, _mut47136 = false, _mut47137 = false, _mut47138 = false, _mut47139 = false, _mut47140 = false, _mut47141 = false, _mut47142 = false, _mut47143 = false, _mut47144 = false, _mut47145 = false, _mut47146 = false, _mut47147 = false, _mut47148 = false, _mut47149 = false, _mut47150 = false, _mut47151 = false, _mut47152 = false, _mut47153 = false, _mut47154 = false, _mut47155 = false, _mut47156 = false, _mut47157 = false, _mut47158 = false, _mut47159 = false, _mut47160 = false, _mut47161 = false, _mut47162 = false, _mut47163 = false, _mut47164 = false, _mut47165 = false, _mut47166 = false, _mut47167 = false, _mut47168 = false, _mut47169 = false, _mut47170 = false, _mut47171 = false, _mut47172 = false, _mut47173 = false, _mut47174 = false, _mut47175 = false, _mut47176 = false, _mut47177 = false, _mut47178 = false, _mut47179 = false, _mut47180 = false, _mut47181 = false, _mut47182 = false, _mut47183 = false, _mut47184 = false, _mut47185 = false, _mut47186 = false, _mut47187 = false, _mut47188 = false, _mut47189 = false, _mut47190 = false, _mut47191 = false, _mut47192 = false, _mut47193 = false, _mut47194 = false, _mut47195 = false, _mut47196 = false, _mut47197 = false, _mut47198 = false, _mut47199 = false, _mut47200 = false, _mut47201 = false, _mut47202 = false, _mut47203 = false, _mut47204 = false, _mut47205 = false, _mut47206 = false, _mut47207 = false, _mut47208 = false, _mut47209 = false, _mut47210 = false, _mut47211 = false, _mut47212 = false, _mut47213 = false, _mut47214 = false, _mut47215 = false, _mut47216 = false, _mut47217 = false, _mut47218 = false, _mut47219 = false, _mut47220 = false, _mut47221 = false, _mut47222 = false, _mut47223 = false, _mut47224 = false, _mut47225 = false, _mut47226 = false, _mut47227 = false, _mut47228 = false, _mut47229 = false, _mut47230 = false, _mut47231 = false, _mut47232 = false, _mut47233 = false, _mut47234 = false, _mut47235 = false, _mut47236 = false, _mut47237 = false, _mut47238 = false, _mut47239 = false, _mut47240 = false, _mut47241 = false, _mut47242 = false, _mut47243 = false, _mut47244 = false, _mut47245 = false, _mut47246 = false, _mut47247 = false, _mut47248 = false, _mut47249 = false, _mut47250 = false, _mut47251 = false, _mut47252 = false, _mut47253 = false, _mut47254 = false, _mut47255 = false, _mut47256 = false, _mut47257 = false, _mut47258 = false, _mut47259 = false, _mut47260 = false, _mut47261 = false, _mut47262 = false, _mut47263 = false, _mut47264 = false, _mut47265 = false, _mut47266 = false, _mut47267 = false, _mut47268 = false, _mut47269 = false, _mut47270 = false, _mut47271 = false, _mut47272 = false, _mut47273 = false, _mut47274 = false, _mut47275 = false, _mut47276 = false, _mut47277 = false, _mut47278 = false, _mut47279 = false, _mut47280 = false, _mut47281 = false, _mut47282 = false, _mut47283 = false, _mut47284 = false, _mut47285 = false, _mut47286 = false, _mut47287 = false, _mut47288 = false, _mut47289 = false, _mut47290 = false, _mut47291 = false, _mut47292 = false, _mut47293 = false, _mut47294 = false, _mut47295 = false, _mut47296 = false, _mut47297 = false, _mut47298 = false, _mut47299 = false, _mut47300 = false, _mut47301 = false, _mut47302 = false, _mut47303 = false, _mut47304 = false, _mut47305 = false, _mut47306 = false, _mut47307 = false, _mut47308 = false, _mut47309 = false, _mut47310 = false, _mut47311 = false, _mut47312 = false, _mut47313 = false, _mut47314 = false, _mut47315 = false, _mut47316 = false, _mut47317 = false, _mut47318 = false, _mut47319 = false, _mut47320 = false, _mut47321 = false, _mut47322 = false, _mut47323 = false, _mut47324 = false, _mut47325 = false, _mut47326 = false, _mut47327 = false, _mut47328 = false, _mut47329 = false, _mut47330 = false, _mut47331 = false, _mut47332 = false, _mut47333 = false, _mut47334 = false, _mut47335 = false, _mut47336 = false, _mut47337 = false, _mut47338 = false, _mut47339 = false, _mut47340 = false, _mut47341 = false, _mut47342 = false, _mut47343 = false, _mut47344 = false, _mut47345 = false, _mut47346 = false, _mut47347 = false, _mut47348 = false, _mut47349 = false, _mut47350 = false, _mut47351 = false, _mut47352 = false, _mut47353 = false, _mut47354 = false, _mut47355 = false, _mut47356 = false, _mut47357 = false, _mut47358 = false, _mut47359 = false, _mut47360 = false, _mut47361 = false, _mut47362 = false, _mut47363 = false, _mut47364 = false, _mut47365 = false, _mut47366 = false, _mut47367 = false, _mut47368 = false, _mut47369 = false, _mut47370 = false, _mut47371 = false, _mut47372 = false, _mut47373 = false, _mut47374 = false, _mut47375 = false, _mut47376 = false, _mut47377 = false, _mut47378 = false, _mut47379 = false, _mut47380 = false, _mut47381 = false, _mut47382 = false, _mut47383 = false, _mut47384 = false, _mut47385 = false, _mut47386 = false, _mut47387 = false, _mut47388 = false, _mut47389 = false, _mut47390 = false, _mut47391 = false, _mut47392 = false, _mut47393 = false, _mut47394 = false, _mut47395 = false, _mut47396 = false, _mut47397 = false, _mut47398 = false, _mut47399 = false, _mut47400 = false, _mut47401 = false, _mut47402 = false, _mut47403 = false, _mut47404 = false, _mut47405 = false, _mut47406 = false, _mut47407 = false, _mut47408 = false, _mut47409 = false, _mut47410 = false, _mut47411 = false, _mut47412 = false, _mut47413 = false, _mut47414 = false, _mut47415 = false, _mut47416 = false, _mut47417 = false, _mut47418 = false, _mut47419 = false, _mut47420 = false, _mut47421 = false, _mut47422 = false, _mut47423 = false, _mut47424 = false, _mut47425 = false, _mut47426 = false, _mut47427 = false, _mut47428 = false, _mut47429 = false, _mut47430 = false, _mut47431 = false, _mut47432 = false, _mut47433 = false, _mut47434 = false, _mut47435 = false, _mut47436 = false, _mut47437 = false, _mut47438 = false, _mut47439 = false, _mut47440 = false, _mut47441 = false, _mut47442 = false, _mut47443 = false, _mut47444 = false, _mut47445 = false, _mut47446 = false, _mut47447 = false, _mut47448 = false, _mut47449 = false, _mut47450 = false, _mut47451 = false, _mut47452 = false, _mut47453 = false, _mut47454 = false, _mut47455 = false, _mut47456 = false, _mut47457 = false, _mut47458 = false, _mut47459 = false, _mut47460 = false, _mut47461 = false, _mut47462 = false, _mut47463 = false, _mut47464 = false, _mut47465 = false, _mut47466 = false, _mut47467 = false, _mut47468 = false, _mut47469 = false, _mut47470 = false, _mut47471 = false, _mut47472 = false, _mut47473 = false, _mut47474 = false, _mut47475 = false, _mut47476 = false, _mut47477 = false, _mut47478 = false, _mut47479 = false, _mut47480 = false, _mut47481 = false, _mut47482 = false, _mut47483 = false, _mut47484 = false, _mut47485 = false, _mut47486 = false, _mut47487 = false, _mut47488 = false, _mut47489 = false, _mut47490 = false, _mut47491 = false, _mut47492 = false, _mut47493 = false, _mut47494 = false, _mut47495 = false, _mut47496 = false, _mut47497 = false, _mut47498 = false, _mut47499 = false, _mut47500 = false, _mut47501 = false, _mut47502 = false, _mut47503 = false, _mut47504 = false, _mut47505 = false, _mut47506 = false, _mut47507 = false, _mut47508 = false, _mut47509 = false, _mut47510 = false, _mut47511 = false, _mut47512 = false, _mut47513 = false, _mut47514 = false, _mut47515 = false, _mut47516 = false, _mut47517 = false, _mut47518 = false, _mut47519 = false, _mut47520 = false, _mut47521 = false, _mut47522 = false, _mut47523 = false, _mut47524 = false, _mut47525 = false, _mut47526 = false, _mut47527 = false, _mut47528 = false, _mut47529 = false, _mut47530 = false, _mut47531 = false, _mut47532 = false, _mut47533 = false, _mut47534 = false, _mut47535 = false, _mut47536 = false, _mut47537 = false, _mut47538 = false, _mut47539 = false, _mut47540 = false, _mut47541 = false, _mut47542 = false, _mut47543 = false, _mut47544 = false, _mut47545 = false, _mut47546 = false, _mut47547 = false, _mut47548 = false, _mut47549 = false, _mut47550 = false, _mut47551 = false, _mut47552 = false, _mut47553 = false, _mut47554 = false, _mut47555 = false, _mut47556 = false, _mut47557 = false, _mut47558 = false, _mut47559 = false, _mut47560 = false, _mut47561 = false, _mut47562 = false, _mut47563 = false, _mut47564 = false, _mut47565 = false, _mut47566 = false, _mut47567 = false, _mut47568 = false, _mut47569 = false, _mut47570 = false, _mut47571 = false, _mut47572 = false, _mut47573 = false, _mut47574 = false, _mut47575 = false, _mut47576 = false, _mut47577 = false, _mut47578 = false, _mut47579 = false, _mut47580 = false, _mut47581 = false, _mut47582 = false, _mut47583 = false, _mut47584 = false, _mut47585 = false, _mut47586 = false, _mut47587 = false, _mut47588 = false, _mut47589 = false, _mut47590 = false, _mut47591 = false, _mut47592 = false, _mut47593 = false, _mut47594 = false, _mut47595 = false, _mut47596 = false, _mut47597 = false, _mut47598 = false, _mut47599 = false, _mut47600 = false, _mut47601 = false, _mut47602 = false, _mut47603 = false, _mut47604 = false, _mut47605 = false, _mut47606 = false, _mut47607 = false, _mut47608 = false, _mut47609 = false, _mut47610 = false, _mut47611 = false, _mut47612 = false, _mut47613 = false, _mut47614 = false, _mut47615 = false, _mut47616 = false, _mut47617 = false, _mut47618 = false, _mut47619 = false, _mut47620 = false, _mut47621 = false, _mut47622 = false, _mut47623 = false, _mut47624 = false, _mut47625 = false, _mut47626 = false, _mut47627 = false, _mut47628 = false, _mut47629 = false, _mut47630 = false, _mut47631 = false, _mut47632 = false, _mut47633 = false, _mut47634 = false, _mut47635 = false, _mut47636 = false, _mut47637 = false, _mut47638 = false, _mut47639 = false, _mut47640 = false, _mut47641 = false, _mut47642 = false, _mut47643 = false, _mut47644 = false, _mut47645 = false, _mut47646 = false, _mut47647 = false, _mut47648 = false, _mut47649 = false, _mut47650 = false, _mut47651 = false, _mut47652 = false, _mut47653 = false, _mut47654 = false, _mut47655 = false, _mut47656 = false, _mut47657 = false, _mut47658 = false, _mut47659 = false, _mut47660 = false, _mut47661 = false, _mut47662 = false, _mut47663 = false, _mut47664 = false, _mut47665 = false, _mut47666 = false, _mut47667 = false, _mut47668 = false, _mut47669 = false, _mut47670 = false, _mut47671 = false, _mut47672 = false, _mut47673 = false, _mut47674 = false, _mut47675 = false, _mut47676 = false, _mut47677 = false, _mut47678 = false, _mut47679 = false, _mut47680 = false, _mut47681 = false, _mut47682 = false, _mut47683 = false, _mut47684 = false, _mut47685 = false, _mut47686 = false, _mut47687 = false, _mut47688 = false, _mut47689 = false, _mut47690 = false, _mut47691 = false, _mut47692 = false, _mut47693 = false, _mut47694 = false, _mut47695 = false, _mut47696 = false, _mut47697 = false, _mut47698 = false, _mut47699 = false, _mut47700 = false, _mut47701 = false, _mut47702 = false, _mut47703 = false, _mut47704 = false, _mut47705 = false, _mut47706 = false, _mut47707 = false, _mut47708 = false, _mut47709 = false, _mut47710 = false, _mut47711 = false, _mut47712 = false, _mut47713 = false, _mut47714 = false, _mut47715 = false, _mut47716 = false, _mut47717 = false, _mut47718 = false, _mut47719 = false, _mut47720 = false, _mut47721 = false, _mut47722 = false, _mut47723 = false, _mut47724 = false, _mut47725 = false, _mut47726 = false, _mut47727 = false, _mut47728 = false, _mut47729 = false, _mut47730 = false, _mut47731 = false, _mut47732 = false, _mut47733 = false, _mut47734 = false, _mut47735 = false, _mut47736 = false, _mut47737 = false, _mut47738 = false, _mut47739 = false, _mut47740 = false, _mut47741 = false, _mut47742 = false, _mut47743 = false, _mut47744 = false, _mut47745 = false, _mut47746 = false, _mut47747 = false, _mut47748 = false, _mut47749 = false, _mut47750 = false, _mut47751 = false, _mut47752 = false, _mut47753 = false, _mut47754 = false, _mut47755 = false, _mut47756 = false, _mut47757 = false, _mut47758 = false, _mut47759 = false, _mut47760 = false, _mut47761 = false, _mut47762 = false, _mut47763 = false, _mut47764 = false, _mut47765 = false, _mut47766 = false, _mut47767 = false, _mut47768 = false, _mut47769 = false, _mut47770 = false, _mut47771 = false, _mut47772 = false, _mut47773 = false, _mut47774 = false, _mut47775 = false, _mut47776 = false, _mut47777 = false, _mut47778 = false, _mut47779 = false, _mut47780 = false, _mut47781 = false, _mut47782 = false, _mut47783 = false, _mut47784 = false, _mut47785 = false, _mut47786 = false, _mut47787 = false, _mut47788 = false, _mut47789 = false, _mut47790 = false, _mut47791 = false, _mut47792 = false, _mut47793 = false, _mut47794 = false, _mut47795 = false, _mut47796 = false, _mut47797 = false, _mut47798 = false, _mut47799 = false, _mut47800 = false, _mut47801 = false, _mut47802 = false, _mut47803 = false, _mut47804 = false, _mut47805 = false, _mut47806 = false, _mut47807 = false, _mut47808 = false, _mut47809 = false, _mut47810 = false, _mut47811 = false, _mut47812 = false, _mut47813 = false, _mut47814 = false, _mut47815 = false, _mut47816 = false, _mut47817 = false, _mut47818 = false, _mut47819 = false, _mut47820 = false, _mut47821 = false, _mut47822 = false, _mut47823 = false, _mut47824 = false, _mut47825 = false, _mut47826 = false, _mut47827 = false, _mut47828 = false, _mut47829 = false, _mut47830 = false, _mut47831 = false, _mut47832 = false, _mut47833 = false, _mut47834 = false, _mut47835 = false, _mut47836 = false, _mut47837 = false, _mut47838 = false, _mut47839 = false, _mut47840 = false, _mut47841 = false, _mut47842 = false, _mut47843 = false, _mut47844 = false, _mut47845 = false, _mut47846 = false, _mut47847 = false, _mut47848 = false, _mut47849 = false, _mut47850 = false, _mut47851 = false, _mut47852 = false, _mut47853 = false, _mut47854 = false, _mut47855 = false, _mut47856 = false, _mut47857 = false, _mut47858 = false, _mut47859 = false, _mut47860 = false, _mut47861 = false, _mut47862 = false, _mut47863 = false, _mut47864 = false, _mut47865 = false, _mut47866 = false, _mut47867 = false, _mut47868 = false, _mut47869 = false, _mut47870 = false, _mut47871 = false, _mut47872 = false, _mut47873 = false, _mut47874 = false, _mut47875 = false, _mut47876 = false, _mut47877 = false, _mut47878 = false, _mut47879 = false, _mut47880 = false, _mut47881 = false, _mut47882 = false, _mut47883 = false, _mut47884 = false, _mut47885 = false, _mut47886 = false, _mut47887 = false, _mut47888 = false, _mut47889 = false, _mut47890 = false, _mut47891 = false, _mut47892 = false, _mut47893 = false, _mut47894 = false, _mut47895 = false, _mut47896 = false, _mut47897 = false, _mut47898 = false, _mut47899 = false, _mut47900 = false, _mut47901 = false, _mut47902 = false, _mut47903 = false, _mut47904 = false, _mut47905 = false, _mut47906 = false, _mut47907 = false, _mut47908 = false, _mut47909 = false, _mut47910 = false, _mut47911 = false, _mut47912 = false, _mut47913 = false, _mut47914 = false, _mut47915 = false, _mut47916 = false, _mut47917 = false, _mut47918 = false, _mut47919 = false, _mut47920 = false, _mut47921 = false, _mut47922 = false, _mut47923 = false, _mut47924 = false, _mut47925 = false, _mut47926 = false, _mut47927 = false, _mut47928 = false, _mut47929 = false, _mut47930 = false, _mut47931 = false, _mut47932 = false, _mut47933 = false, _mut47934 = false, _mut47935 = false, _mut47936 = false, _mut47937 = false, _mut47938 = false, _mut47939 = false, _mut47940 = false, _mut47941 = false, _mut47942 = false, _mut47943 = false, _mut47944 = false, _mut47945 = false, _mut47946 = false, _mut47947 = false, _mut47948 = false, _mut47949 = false, _mut47950 = false, _mut47951 = false, _mut47952 = false, _mut47953 = false, _mut47954 = false, _mut47955 = false, _mut47956 = false, _mut47957 = false, _mut47958 = false, _mut47959 = false, _mut47960 = false, _mut47961 = false, _mut47962 = false, _mut47963 = false, _mut47964 = false, _mut47965 = false, _mut47966 = false, _mut47967 = false, _mut47968 = false, _mut47969 = false, _mut47970 = false, _mut47971 = false, _mut47972 = false, _mut47973 = false, _mut47974 = false, _mut47975 = false, _mut47976 = false, _mut47977 = false, _mut47978 = false, _mut47979 = false, _mut47980 = false, _mut47981 = false, _mut47982 = false, _mut47983 = false, _mut47984 = false, _mut47985 = false, _mut47986 = false, _mut47987 = false, _mut47988 = false, _mut47989 = false, _mut47990 = false, _mut47991 = false, _mut47992 = false, _mut47993 = false, _mut47994 = false, _mut47995 = false, _mut47996 = false, _mut47997 = false, _mut47998 = false, _mut47999 = false, _mut48000 = false, _mut48001 = false, _mut48002 = false, _mut48003 = false, _mut48004 = false, _mut48005 = false, _mut48006 = false, _mut48007 = false, _mut48008 = false, _mut48009 = false, _mut48010 = false, _mut48011 = false, _mut48012 = false, _mut48013 = false, _mut48014 = false, _mut48015 = false, _mut48016 = false, _mut48017 = false, _mut48018 = false, _mut48019 = false, _mut48020 = false, _mut48021 = false, _mut48022 = false, _mut48023 = false, _mut48024 = false, _mut48025 = false, _mut48026 = false, _mut48027 = false, _mut48028 = false, _mut48029 = false, _mut48030 = false, _mut48031 = false, _mut48032 = false, _mut48033 = false, _mut48034 = false, _mut48035 = false, _mut48036 = false, _mut48037 = false, _mut48038 = false, _mut48039 = false, _mut48040 = false, _mut48041 = false, _mut48042 = false, _mut48043 = false, _mut48044 = false, _mut48045 = false, _mut48046 = false, _mut48047 = false, _mut48048 = false, _mut48049 = false, _mut48050 = false, _mut48051 = false, _mut48052 = false, _mut48053 = false, _mut48054 = false, _mut48055 = false, _mut48056 = false, _mut48057 = false, _mut48058 = false, _mut48059 = false, _mut48060 = false, _mut48061 = false, _mut48062 = false, _mut48063 = false, _mut48064 = false, _mut48065 = false, _mut48066 = false, _mut48067 = false, _mut48068 = false, _mut48069 = false, _mut48070 = false, _mut48071 = false, _mut48072 = false, _mut48073 = false, _mut48074 = false, _mut48075 = false, _mut48076 = false, _mut48077 = false, _mut48078 = false, _mut48079 = false, _mut48080 = false, _mut48081 = false, _mut48082 = false, _mut48083 = false, _mut48084 = false, _mut48085 = false, _mut48086 = false, _mut48087 = false, _mut48088 = false, _mut48089 = false, _mut48090 = false, _mut48091 = false, _mut48092 = false, _mut48093 = false, _mut48094 = false, _mut48095 = false, _mut48096 = false, _mut48097 = false, _mut48098 = false, _mut48099 = false, _mut48100 = false, _mut48101 = false, _mut48102 = false, _mut48103 = false, _mut48104 = false, _mut48105 = false, _mut48106 = false, _mut48107 = false, _mut48108 = false, _mut48109 = false, _mut48110 = false, _mut48111 = false, _mut48112 = false, _mut48113 = false, _mut48114 = false, _mut48115 = false, _mut48116 = false, _mut48117 = false, _mut48118 = false, _mut48119 = false, _mut48120 = false, _mut48121 = false, _mut48122 = false, _mut48123 = false, _mut48124 = false, _mut48125 = false, _mut48126 = false, _mut48127 = false, _mut48128 = false, _mut48129 = false, _mut48130 = false, _mut48131 = false, _mut48132 = false, _mut48133 = false, _mut48134 = false, _mut48135 = false, _mut48136 = false, _mut48137 = false, _mut48138 = false, _mut48139 = false, _mut48140 = false, _mut48141 = false, _mut48142 = false, _mut48143 = false, _mut48144 = false, _mut48145 = false, _mut48146 = false, _mut48147 = false, _mut48148 = false, _mut48149 = false, _mut48150 = false, _mut48151 = false, _mut48152 = false, _mut48153 = false, _mut48154 = false, _mut48155 = false, _mut48156 = false, _mut48157 = false, _mut48158 = false, _mut48159 = false, _mut48160 = false, _mut48161 = false, _mut48162 = false, _mut48163 = false, _mut48164 = false, _mut48165 = false, _mut48166 = false, _mut48167 = false, _mut48168 = false, _mut48169 = false, _mut48170 = false, _mut48171 = false, _mut48172 = false, _mut48173 = false, _mut48174 = false, _mut48175 = false, _mut48176 = false, _mut48177 = false, _mut48178 = false, _mut48179 = false, _mut48180 = false, _mut48181 = false, _mut48182 = false, _mut48183 = false, _mut48184 = false, _mut48185 = false, _mut48186 = false, _mut48187 = false, _mut48188 = false, _mut48189 = false, _mut48190 = false, _mut48191 = false, _mut48192 = false, _mut48193 = false, _mut48194 = false, _mut48195 = false, _mut48196 = false, _mut48197 = false, _mut48198 = false, _mut48199 = false, _mut48200 = false, _mut48201 = false, _mut48202 = false, _mut48203 = false, _mut48204 = false, _mut48205 = false, _mut48206 = false, _mut48207 = false, _mut48208 = false, _mut48209 = false, _mut48210 = false, _mut48211 = false, _mut48212 = false, _mut48213 = false, _mut48214 = false, _mut48215 = false, _mut48216 = false, _mut48217 = false, _mut48218 = false, _mut48219 = false, _mut48220 = false, _mut48221 = false, _mut48222 = false, _mut48223 = false, _mut48224 = false, _mut48225 = false, _mut48226 = false, _mut48227 = false, _mut48228 = false, _mut48229 = false, _mut48230 = false, _mut48231 = false, _mut48232 = false, _mut48233 = false, _mut48234 = false, _mut48235 = false, _mut48236 = false, _mut48237 = false, _mut48238 = false, _mut48239 = false, _mut48240 = false, _mut48241 = false, _mut48242 = false, _mut48243 = false, _mut48244 = false, _mut48245 = false, _mut48246 = false, _mut48247 = false, _mut48248 = false, _mut48249 = false, _mut48250 = false, _mut48251 = false, _mut48252 = false, _mut48253 = false, _mut48254 = false, _mut48255 = false, _mut48256 = false, _mut48257 = false, _mut48258 = false, _mut48259 = false, _mut48260 = false, _mut48261 = false, _mut48262 = false, _mut48263 = false, _mut48264 = false, _mut48265 = false, _mut48266 = false, _mut48267 = false, _mut48268 = false, _mut48269 = false, _mut48270 = false, _mut48271 = false, _mut48272 = false, _mut48273 = false, _mut48274 = false, _mut48275 = false, _mut48276 = false, _mut48277 = false, _mut48278 = false, _mut48279 = false, _mut48280 = false, _mut48281 = false, _mut48282 = false, _mut48283 = false, _mut48284 = false, _mut48285 = false, _mut48286 = false, _mut48287 = false, _mut48288 = false, _mut48289 = false, _mut48290 = false, _mut48291 = false, _mut48292 = false, _mut48293 = false, _mut48294 = false, _mut48295 = false, _mut48296 = false, _mut48297 = false, _mut48298 = false, _mut48299 = false, _mut48300 = false, _mut48301 = false, _mut48302 = false, _mut48303 = false, _mut48304 = false, _mut48305 = false, _mut48306 = false, _mut48307 = false, _mut48308 = false, _mut48309 = false, _mut48310 = false, _mut48311 = false, _mut48312 = false, _mut48313 = false, _mut48314 = false, _mut48315 = false, _mut48316 = false, _mut48317 = false, _mut48318 = false, _mut48319 = false, _mut48320 = false, _mut48321 = false, _mut48322 = false, _mut48323 = false, _mut48324 = false, _mut48325 = false, _mut48326 = false, _mut48327 = false, _mut48328 = false, _mut48329 = false, _mut48330 = false, _mut48331 = false, _mut48332 = false, _mut48333 = false, _mut48334 = false, _mut48335 = false, _mut48336 = false, _mut48337 = false, _mut48338 = false, _mut48339 = false, _mut48340 = false, _mut48341 = false, _mut48342 = false, _mut48343 = false, _mut48344 = false, _mut48345 = false, _mut48346 = false, _mut48347 = false, _mut48348 = false, _mut48349 = false, _mut48350 = false, _mut48351 = false, _mut48352 = false, _mut48353 = false, _mut48354 = false, _mut48355 = false, _mut48356 = false, _mut48357 = false, _mut48358 = false, _mut48359 = false, _mut48360 = false, _mut48361 = false, _mut48362 = false, _mut48363 = false, _mut48364 = false, _mut48365 = false, _mut48366 = false, _mut48367 = false, _mut48368 = false, _mut48369 = false, _mut48370 = false, _mut48371 = false, _mut48372 = false, _mut48373 = false, _mut48374 = false, _mut48375 = false, _mut48376 = false, _mut48377 = false, _mut48378 = false, _mut48379 = false, _mut48380 = false, _mut48381 = false, _mut48382 = false, _mut48383 = false, _mut48384 = false, _mut48385 = false, _mut48386 = false, _mut48387 = false, _mut48388 = false, _mut48389 = false, _mut48390 = false, _mut48391 = false, _mut48392 = false, _mut48393 = false, _mut48394 = false, _mut48395 = false, _mut48396 = false, _mut48397 = false, _mut48398 = false, _mut48399 = false, _mut48400 = false, _mut48401 = false, _mut48402 = false, _mut48403 = false, _mut48404 = false, _mut48405 = false, _mut48406 = false, _mut48407 = false, _mut48408 = false, _mut48409 = false, _mut48410 = false, _mut48411 = false, _mut48412 = false, _mut48413 = false, _mut48414 = false, _mut48415 = false, _mut48416 = false, _mut48417 = false, _mut48418 = false, _mut48419 = false, _mut48420 = false, _mut48421 = false, _mut48422 = false, _mut48423 = false, _mut48424 = false, _mut48425 = false, _mut48426 = false, _mut48427 = false, _mut48428 = false, _mut48429 = false, _mut48430 = false, _mut48431 = false, _mut48432 = false, _mut48433 = false, _mut48434 = false, _mut48435 = false, _mut48436 = false, _mut48437 = false, _mut48438 = false, _mut48439 = false, _mut48440 = false, _mut48441 = false, _mut48442 = false, _mut48443 = false, _mut48444 = false, _mut48445 = false, _mut48446 = false, _mut48447 = false, _mut48448 = false, _mut48449 = false, _mut48450 = false, _mut48451 = false, _mut48452 = false, _mut48453 = false, _mut48454 = false, _mut48455 = false, _mut48456 = false, _mut48457 = false, _mut48458 = false, _mut48459 = false, _mut48460 = false, _mut48461 = false, _mut48462 = false, _mut48463 = false, _mut48464 = false, _mut48465 = false, _mut48466 = false, _mut48467 = false, _mut48468 = false, _mut48469 = false, _mut48470 = false, _mut48471 = false, _mut48472 = false, _mut48473 = false, _mut48474 = false, _mut48475 = false, _mut48476 = false, _mut48477 = false, _mut48478 = false, _mut48479 = false, _mut48480 = false, _mut48481 = false, _mut48482 = false, _mut48483 = false, _mut48484 = false, _mut48485 = false, _mut48486 = false, _mut48487 = false, _mut48488 = false, _mut48489 = false, _mut48490 = false, _mut48491 = false, _mut48492 = false, _mut48493 = false, _mut48494 = false, _mut48495 = false, _mut48496 = false, _mut48497 = false, _mut48498 = false, _mut48499 = false, _mut48500 = false, _mut48501 = false, _mut48502 = false, _mut48503 = false, _mut48504 = false, _mut48505 = false, _mut48506 = false, _mut48507 = false, _mut48508 = false, _mut48509 = false, _mut48510 = false, _mut48511 = false, _mut48512 = false, _mut48513 = false, _mut48514 = false, _mut48515 = false, _mut48516 = false, _mut48517 = false, _mut48518 = false, _mut48519 = false, _mut48520 = false, _mut48521 = false, _mut48522 = false, _mut48523 = false, _mut48524 = false, _mut48525 = false, _mut48526 = false, _mut48527 = false, _mut48528 = false, _mut48529 = false, _mut48530 = false, _mut48531 = false, _mut48532 = false, _mut48533 = false, _mut48534 = false, _mut48535 = false, _mut48536 = false, _mut48537 = false, _mut48538 = false, _mut48539 = false, _mut48540 = false, _mut48541 = false, _mut48542 = false, _mut48543 = false, _mut48544 = false, _mut48545 = false, _mut48546 = false, _mut48547 = false, _mut48548 = false, _mut48549 = false, _mut48550 = false, _mut48551 = false, _mut48552 = false, _mut48553 = false, _mut48554 = false, _mut48555 = false, _mut48556 = false, _mut48557 = false, _mut48558 = false, _mut48559 = false, _mut48560 = false, _mut48561 = false, _mut48562 = false, _mut48563 = false, _mut48564 = false, _mut48565 = false, _mut48566 = false, _mut48567 = false, _mut48568 = false, _mut48569 = false, _mut48570 = false, _mut48571 = false, _mut48572 = false, _mut48573 = false, _mut48574 = false, _mut48575 = false, _mut48576 = false, _mut48577 = false, _mut48578 = false, _mut48579 = false, _mut48580 = false, _mut48581 = false, _mut48582 = false, _mut48583 = false, _mut48584 = false, _mut48585 = false, _mut48586 = false, _mut48587 = false, _mut48588 = false, _mut48589 = false, _mut48590 = false, _mut48591 = false, _mut48592 = false, _mut48593 = false, _mut48594 = false, _mut48595 = false, _mut48596 = false, _mut48597 = false, _mut48598 = false, _mut48599 = false, _mut48600 = false, _mut48601 = false, _mut48602 = false, _mut48603 = false, _mut48604 = false, _mut48605 = false, _mut48606 = false, _mut48607 = false, _mut48608 = false, _mut48609 = false, _mut48610 = false, _mut48611 = false, _mut48612 = false, _mut48613 = false, _mut48614 = false, _mut48615 = false, _mut48616 = false, _mut48617 = false, _mut48618 = false, _mut48619 = false, _mut48620 = false, _mut48621 = false, _mut48622 = false, _mut48623 = false, _mut48624 = false, _mut48625 = false, _mut48626 = false, _mut48627 = false, _mut48628 = false, _mut48629 = false, _mut48630 = false, _mut48631 = false, _mut48632 = false, _mut48633 = false, _mut48634 = false, _mut48635 = false, _mut48636 = false, _mut48637 = false, _mut48638 = false, _mut48639 = false, _mut48640 = false, _mut48641 = false, _mut48642 = false, _mut48643 = false, _mut48644 = false, _mut48645 = false, _mut48646 = false, _mut48647 = false, _mut48648 = false, _mut48649 = false, _mut48650 = false, _mut48651 = false, _mut48652 = false, _mut48653 = false, _mut48654 = false, _mut48655 = false, _mut48656 = false, _mut48657 = false, _mut48658 = false, _mut48659 = false, _mut48660 = false, _mut48661 = false, _mut48662 = false, _mut48663 = false, _mut48664 = false, _mut48665 = false, _mut48666 = false, _mut48667 = false, _mut48668 = false, _mut48669 = false, _mut48670 = false, _mut48671 = false, _mut48672 = false, _mut48673 = false, _mut48674 = false, _mut48675 = false, _mut48676 = false, _mut48677 = false, _mut48678 = false, _mut48679 = false, _mut48680 = false, _mut48681 = false, _mut48682 = false, _mut48683 = false, _mut48684 = false, _mut48685 = false, _mut48686 = false, _mut48687 = false, _mut48688 = false, _mut48689 = false, _mut48690 = false, _mut48691 = false, _mut48692 = false, _mut48693 = false, _mut48694 = false, _mut48695 = false, _mut48696 = false, _mut48697 = false, _mut48698 = false, _mut48699 = false, _mut48700 = false, _mut48701 = false, _mut48702 = false, _mut48703 = false, _mut48704 = false, _mut48705 = false, _mut48706 = false, _mut48707 = false, _mut48708 = false, _mut48709 = false, _mut48710 = false, _mut48711 = false, _mut48712 = false, _mut48713 = false, _mut48714 = false, _mut48715 = false, _mut48716 = false, _mut48717 = false, _mut48718 = false, _mut48719 = false, _mut48720 = false, _mut48721 = false, _mut48722 = false, _mut48723 = false, _mut48724 = false, _mut48725 = false, _mut48726 = false, _mut48727 = false, _mut48728 = false, _mut48729 = false, _mut48730 = false, _mut48731 = false, _mut48732 = false, _mut48733 = false, _mut48734 = false, _mut48735 = false, _mut48736 = false, _mut48737 = false, _mut48738 = false, _mut48739 = false, _mut48740 = false, _mut48741 = false, _mut48742 = false, _mut48743 = false, _mut48744 = false, _mut48745 = false, _mut48746 = false, _mut48747 = false, _mut48748 = false, _mut48749 = false, _mut48750 = false, _mut48751 = false, _mut48752 = false, _mut48753 = false, _mut48754 = false, _mut48755 = false, _mut48756 = false, _mut48757 = false, _mut48758 = false, _mut48759 = false, _mut48760 = false, _mut48761 = false, _mut48762 = false, _mut48763 = false, _mut48764 = false, _mut48765 = false, _mut48766 = false, _mut48767 = false, _mut48768 = false, _mut48769 = false, _mut48770 = false, _mut48771 = false, _mut48772 = false, _mut48773 = false, _mut48774 = false, _mut48775 = false, _mut48776 = false, _mut48777 = false, _mut48778 = false, _mut48779 = false, _mut48780 = false, _mut48781 = false, _mut48782 = false, _mut48783 = false, _mut48784 = false, _mut48785 = false, _mut48786 = false, _mut48787 = false, _mut48788 = false, _mut48789 = false, _mut48790 = false, _mut48791 = false, _mut48792 = false, _mut48793 = false, _mut48794 = false, _mut48795 = false, _mut48796 = false, _mut48797 = false, _mut48798 = false, _mut48799 = false, _mut48800 = false, _mut48801 = false, _mut48802 = false, _mut48803 = false, _mut48804 = false, _mut48805 = false, _mut48806 = false, _mut48807 = false, _mut48808 = false, _mut48809 = false, _mut48810 = false, _mut48811 = false, _mut48812 = false, _mut48813 = false, _mut48814 = false, _mut48815 = false, _mut48816 = false, _mut48817 = false, _mut48818 = false, _mut48819 = false, _mut48820 = false, _mut48821 = false, _mut48822 = false, _mut48823 = false, _mut48824 = false, _mut48825 = false, _mut48826 = false, _mut48827 = false, _mut48828 = false, _mut48829 = false, _mut48830 = false, _mut48831 = false, _mut48832 = false, _mut48833 = false, _mut48834 = false, _mut48835 = false, _mut48836 = false, _mut48837 = false, _mut48838 = false, _mut48839 = false, _mut48840 = false, _mut48841 = false, _mut48842 = false, _mut48843 = false, _mut48844 = false, _mut48845 = false, _mut48846 = false, _mut48847 = false, _mut48848 = false, _mut48849 = false, _mut48850 = false, _mut48851 = false, _mut48852 = false, _mut48853 = false, _mut48854 = false, _mut48855 = false, _mut48856 = false, _mut48857 = false, _mut48858 = false, _mut48859 = false, _mut48860 = false, _mut48861 = false, _mut48862 = false, _mut48863 = false, _mut48864 = false, _mut48865 = false, _mut48866 = false, _mut48867 = false, _mut48868 = false, _mut48869 = false, _mut48870 = false, _mut48871 = false, _mut48872 = false, _mut48873 = false, _mut48874 = false, _mut48875 = false, _mut48876 = false, _mut48877 = false, _mut48878 = false, _mut48879 = false, _mut48880 = false, _mut48881 = false, _mut48882 = false, _mut48883 = false, _mut48884 = false, _mut48885 = false, _mut48886 = false, _mut48887 = false, _mut48888 = false, _mut48889 = false, _mut48890 = false, _mut48891 = false, _mut48892 = false, _mut48893 = false, _mut48894 = false, _mut48895 = false, _mut48896 = false, _mut48897 = false, _mut48898 = false, _mut48899 = false, _mut48900 = false, _mut48901 = false, _mut48902 = false, _mut48903 = false, _mut48904 = false, _mut48905 = false, _mut48906 = false, _mut48907 = false, _mut48908 = false, _mut48909 = false, _mut48910 = false, _mut48911 = false, _mut48912 = false, _mut48913 = false, _mut48914 = false, _mut48915 = false, _mut48916 = false, _mut48917 = false, _mut48918 = false, _mut48919 = false, _mut48920 = false, _mut48921 = false, _mut48922 = false, _mut48923 = false, _mut48924 = false, _mut48925 = false, _mut48926 = false, _mut48927 = false, _mut48928 = false, _mut48929 = false, _mut48930 = false, _mut48931 = false, _mut48932 = false, _mut48933 = false, _mut48934 = false, _mut48935 = false, _mut48936 = false, _mut48937 = false, _mut48938 = false, _mut48939 = false, _mut48940 = false, _mut48941 = false, _mut48942 = false, _mut48943 = false, _mut48944 = false, _mut48945 = false, _mut48946 = false, _mut48947 = false, _mut48948 = false, _mut48949 = false, _mut48950 = false, _mut48951 = false, _mut48952 = false, _mut48953 = false, _mut48954 = false, _mut48955 = false, _mut48956 = false, _mut48957 = false, _mut48958 = false, _mut48959 = false, _mut48960 = false, _mut48961 = false, _mut48962 = false, _mut48963 = false, _mut48964 = false, _mut48965 = false, _mut48966 = false, _mut48967 = false, _mut48968 = false, _mut48969 = false, _mut48970 = false, _mut48971 = false, _mut48972 = false, _mut48973 = false, _mut48974 = false, _mut48975 = false, _mut48976 = false, _mut48977 = false, _mut48978 = false, _mut48979 = false, _mut48980 = false, _mut48981 = false, _mut48982 = false, _mut48983 = false, _mut48984 = false, _mut48985 = false, _mut48986 = false, _mut48987 = false, _mut48988 = false, _mut48989 = false, _mut48990 = false, _mut48991 = false, _mut48992 = false, _mut48993 = false, _mut48994 = false, _mut48995 = false, _mut48996 = false, _mut48997 = false, _mut48998 = false, _mut48999 = false, _mut49000 = false, _mut49001 = false, _mut49002 = false, _mut49003 = false, _mut49004 = false, _mut49005 = false, _mut49006 = false, _mut49007 = false, _mut49008 = false, _mut49009 = false, _mut49010 = false, _mut49011 = false, _mut49012 = false, _mut49013 = false, _mut49014 = false, _mut49015 = false, _mut49016 = false, _mut49017 = false, _mut49018 = false, _mut49019 = false, _mut49020 = false, _mut49021 = false, _mut49022 = false, _mut49023 = false, _mut49024 = false, _mut49025 = false, _mut49026 = false, _mut49027 = false, _mut49028 = false, _mut49029 = false, _mut49030 = false, _mut49031 = false, _mut49032 = false, _mut49033 = false, _mut49034 = false, _mut49035 = false, _mut49036 = false, _mut49037 = false, _mut49038 = false, _mut49039 = false, _mut49040 = false, _mut49041 = false, _mut49042 = false, _mut49043 = false, _mut49044 = false, _mut49045 = false, _mut49046 = false, _mut49047 = false, _mut49048 = false, _mut49049 = false, _mut49050 = false, _mut49051 = false, _mut49052 = false, _mut49053 = false, _mut49054 = false, _mut49055 = false, _mut49056 = false, _mut49057 = false, _mut49058 = false, _mut49059 = false, _mut49060 = false, _mut49061 = false, _mut49062 = false, _mut49063 = false, _mut49064 = false, _mut49065 = false, _mut49066 = false, _mut49067 = false, _mut49068 = false, _mut49069 = false, _mut49070 = false, _mut49071 = false, _mut49072 = false, _mut49073 = false, _mut49074 = false, _mut49075 = false, _mut49076 = false, _mut49077 = false, _mut49078 = false, _mut49079 = false, _mut49080 = false, _mut49081 = false, _mut49082 = false, _mut49083 = false, _mut49084 = false, _mut49085 = false, _mut49086 = false, _mut49087 = false, _mut49088 = false, _mut49089 = false, _mut49090 = false, _mut49091 = false, _mut49092 = false, _mut49093 = false, _mut49094 = false, _mut49095 = false, _mut49096 = false, _mut49097 = false, _mut49098 = false, _mut49099 = false, _mut49100 = false, _mut49101 = false, _mut49102 = false, _mut49103 = false, _mut49104 = false, _mut49105 = false, _mut49106 = false, _mut49107 = false, _mut49108 = false, _mut49109 = false, _mut49110 = false, _mut49111 = false, _mut49112 = false, _mut49113 = false, _mut49114 = false, _mut49115 = false, _mut49116 = false, _mut49117 = false, _mut49118 = false, _mut49119 = false, _mut49120 = false, _mut49121 = false, _mut49122 = false, _mut49123 = false, _mut49124 = false, _mut49125 = false, _mut49126 = false, _mut49127 = false, _mut49128 = false, _mut49129 = false, _mut49130 = false, _mut49131 = false, _mut49132 = false, _mut49133 = false, _mut49134 = false, _mut49135 = false, _mut49136 = false, _mut49137 = false, _mut49138 = false, _mut49139 = false, _mut49140 = false, _mut49141 = false, _mut49142 = false, _mut49143 = false, _mut49144 = false, _mut49145 = false, _mut49146 = false, _mut49147 = false, _mut49148 = false, _mut49149 = false, _mut49150 = false, _mut49151 = false, _mut49152 = false, _mut49153 = false, _mut49154 = false, _mut49155 = false, _mut49156 = false, _mut49157 = false, _mut49158 = false, _mut49159 = false, _mut49160 = false, _mut49161 = false, _mut49162 = false, _mut49163 = false, _mut49164 = false, _mut49165 = false, _mut49166 = false, _mut49167 = false, _mut49168 = false, _mut49169 = false, _mut49170 = false, _mut49171 = false, _mut49172 = false, _mut49173 = false, _mut49174 = false, _mut49175 = false, _mut49176 = false, _mut49177 = false, _mut49178 = false, _mut49179 = false, _mut49180 = false, _mut49181 = false, _mut49182 = false, _mut49183 = false, _mut49184 = false, _mut49185 = false, _mut49186 = false, _mut49187 = false, _mut49188 = false, _mut49189 = false, _mut49190 = false, _mut49191 = false, _mut49192 = false, _mut49193 = false, _mut49194 = false, _mut49195 = false, _mut49196 = false, _mut49197 = false, _mut49198 = false, _mut49199 = false, _mut49200 = false, _mut49201 = false, _mut49202 = false, _mut49203 = false, _mut49204 = false, _mut49205 = false, _mut49206 = false, _mut49207 = false, _mut49208 = false, _mut49209 = false, _mut49210 = false, _mut49211 = false, _mut49212 = false, _mut49213 = false, _mut49214 = false, _mut49215 = false, _mut49216 = false, _mut49217 = false, _mut49218 = false, _mut49219 = false, _mut49220 = false, _mut49221 = false, _mut49222 = false, _mut49223 = false, _mut49224 = false, _mut49225 = false, _mut49226 = false, _mut49227 = false, _mut49228 = false, _mut49229 = false, _mut49230 = false, _mut49231 = false, _mut49232 = false, _mut49233 = false, _mut49234 = false, _mut49235 = false, _mut49236 = false, _mut49237 = false, _mut49238 = false, _mut49239 = false, _mut49240 = false, _mut49241 = false, _mut49242 = false, _mut49243 = false, _mut49244 = false, _mut49245 = false, _mut49246 = false, _mut49247 = false, _mut49248 = false, _mut49249 = false, _mut49250 = false, _mut49251 = false, _mut49252 = false, _mut49253 = false, _mut49254 = false, _mut49255 = false, _mut49256 = false, _mut49257 = false, _mut49258 = false, _mut49259 = false, _mut49260 = false, _mut49261 = false, _mut49262 = false, _mut49263 = false, _mut49264 = false, _mut49265 = false, _mut49266 = false, _mut49267 = false, _mut49268 = false, _mut49269 = false, _mut49270 = false, _mut49271 = false, _mut49272 = false, _mut49273 = false, _mut49274 = false, _mut49275 = false, _mut49276 = false, _mut49277 = false, _mut49278 = false, _mut49279 = false, _mut49280 = false, _mut49281 = false, _mut49282 = false, _mut49283 = false, _mut49284 = false, _mut49285 = false, _mut49286 = false, _mut49287 = false, _mut49288 = false, _mut49289 = false, _mut49290 = false, _mut49291 = false, _mut49292 = false, _mut49293 = false, _mut49294 = false, _mut49295 = false, _mut49296 = false, _mut49297 = false, _mut49298 = false, _mut49299 = false, _mut49300 = false, _mut49301 = false, _mut49302 = false, _mut49303 = false, _mut49304 = false, _mut49305 = false, _mut49306 = false, _mut49307 = false, _mut49308 = false, _mut49309 = false, _mut49310 = false, _mut49311 = false, _mut49312 = false, _mut49313 = false, _mut49314 = false, _mut49315 = false, _mut49316 = false, _mut49317 = false, _mut49318 = false, _mut49319 = false, _mut49320 = false, _mut49321 = false, _mut49322 = false, _mut49323 = false, _mut49324 = false, _mut49325 = false, _mut49326 = false, _mut49327 = false, _mut49328 = false, _mut49329 = false, _mut49330 = false, _mut49331 = false, _mut49332 = false, _mut49333 = false, _mut49334 = false, _mut49335 = false, _mut49336 = false, _mut49337 = false, _mut49338 = false, _mut49339 = false, _mut49340 = false, _mut49341 = false, _mut49342 = false, _mut49343 = false, _mut49344 = false, _mut49345 = false, _mut49346 = false, _mut49347 = false, _mut49348 = false, _mut49349 = false, _mut49350 = false, _mut49351 = false, _mut49352 = false, _mut49353 = false, _mut49354 = false, _mut49355 = false, _mut49356 = false, _mut49357 = false, _mut49358 = false, _mut49359 = false, _mut49360 = false, _mut49361 = false, _mut49362 = false, _mut49363 = false, _mut49364 = false, _mut49365 = false, _mut49366 = false, _mut49367 = false, _mut49368 = false, _mut49369 = false, _mut49370 = false, _mut49371 = false, _mut49372 = false, _mut49373 = false, _mut49374 = false, _mut49375 = false, _mut49376 = false, _mut49377 = false, _mut49378 = false, _mut49379 = false, _mut49380 = false, _mut49381 = false, _mut49382 = false, _mut49383 = false, _mut49384 = false, _mut49385 = false, _mut49386 = false, _mut49387 = false, _mut49388 = false, _mut49389 = false, _mut49390 = false, _mut49391 = false, _mut49392 = false, _mut49393 = false, _mut49394 = false, _mut49395 = false, _mut49396 = false, _mut49397 = false, _mut49398 = false, _mut49399 = false, _mut49400 = false, _mut49401 = false, _mut49402 = false, _mut49403 = false, _mut49404 = false, _mut49405 = false, _mut49406 = false, _mut49407 = false, _mut49408 = false, _mut49409 = false, _mut49410 = false, _mut49411 = false, _mut49412 = false, _mut49413 = false, _mut49414 = false, _mut49415 = false, _mut49416 = false, _mut49417 = false, _mut49418 = false, _mut49419 = false, _mut49420 = false, _mut49421 = false, _mut49422 = false, _mut49423 = false, _mut49424 = false, _mut49425 = false, _mut49426 = false, _mut49427 = false, _mut49428 = false, _mut49429 = false, _mut49430 = false, _mut49431 = false, _mut49432 = false, _mut49433 = false, _mut49434 = false, _mut49435 = false, _mut49436 = false, _mut49437 = false, _mut49438 = false, _mut49439 = false, _mut49440 = false, _mut49441 = false, _mut49442 = false, _mut49443 = false, _mut49444 = false, _mut49445 = false, _mut49446 = false, _mut49447 = false, _mut49448 = false, _mut49449 = false, _mut49450 = false, _mut49451 = false, _mut49452 = false, _mut49453 = false, _mut49454 = false, _mut49455 = false, _mut49456 = false, _mut49457 = false, _mut49458 = false, _mut49459 = false, _mut49460 = false, _mut49461 = false, _mut49462 = false, _mut49463 = false, _mut49464 = false, _mut49465 = false, _mut49466 = false, _mut49467 = false, _mut49468 = false, _mut49469 = false, _mut49470 = false, _mut49471 = false, _mut49472 = false, _mut49473 = false, _mut49474 = false, _mut49475 = false, _mut49476 = false, _mut49477 = false, _mut49478 = false, _mut49479 = false, _mut49480 = false, _mut49481 = false, _mut49482 = false, _mut49483 = false, _mut49484 = false, _mut49485 = false, _mut49486 = false, _mut49487 = false, _mut49488 = false, _mut49489 = false, _mut49490 = false, _mut49491 = false, _mut49492 = false, _mut49493 = false, _mut49494 = false, _mut49495 = false, _mut49496 = false, _mut49497 = false, _mut49498 = false, _mut49499 = false, _mut49500 = false, _mut49501 = false, _mut49502 = false, _mut49503 = false, _mut49504 = false, _mut49505 = false, _mut49506 = false, _mut49507 = false, _mut49508 = false, _mut49509 = false, _mut49510 = false, _mut49511 = false, _mut49512 = false, _mut49513 = false, _mut49514 = false, _mut49515 = false, _mut49516 = false, _mut49517 = false, _mut49518 = false, _mut49519 = false, _mut49520 = false, _mut49521 = false, _mut49522 = false, _mut49523 = false, _mut49524 = false, _mut49525 = false, _mut49526 = false, _mut49527 = false, _mut49528 = false, _mut49529 = false, _mut49530 = false, _mut49531 = false, _mut49532 = false, _mut49533 = false, _mut49534 = false, _mut49535 = false, _mut49536 = false, _mut49537 = false, _mut49538 = false, _mut49539 = false, _mut49540 = false, _mut49541 = false, _mut49542 = false, _mut49543 = false, _mut49544 = false, _mut49545 = false, _mut49546 = false, _mut49547 = false, _mut49548 = false, _mut49549 = false, _mut49550 = false, _mut49551 = false, _mut49552 = false, _mut49553 = false, _mut49554 = false, _mut49555 = false, _mut49556 = false, _mut49557 = false, _mut49558 = false, _mut49559 = false, _mut49560 = false, _mut49561 = false, _mut49562 = false, _mut49563 = false, _mut49564 = false, _mut49565 = false, _mut49566 = false, _mut49567 = false, _mut49568 = false, _mut49569 = false, _mut49570 = false, _mut49571 = false, _mut49572 = false, _mut49573 = false, _mut49574 = false, _mut49575 = false, _mut49576 = false, _mut49577 = false, _mut49578 = false, _mut49579 = false, _mut49580 = false, _mut49581 = false, _mut49582 = false, _mut49583 = false, _mut49584 = false, _mut49585 = false, _mut49586 = false, _mut49587 = false, _mut49588 = false, _mut49589 = false, _mut49590 = false, _mut49591 = false, _mut49592 = false, _mut49593 = false, _mut49594 = false, _mut49595 = false, _mut49596 = false, _mut49597 = false, _mut49598 = false, _mut49599 = false, _mut49600 = false, _mut49601 = false, _mut49602 = false, _mut49603 = false, _mut49604 = false, _mut49605 = false, _mut49606 = false, _mut49607 = false, _mut49608 = false, _mut49609 = false, _mut49610 = false, _mut49611 = false, _mut49612 = false, _mut49613 = false, _mut49614 = false, _mut49615 = false, _mut49616 = false, _mut49617 = false, _mut49618 = false, _mut49619 = false, _mut49620 = false, _mut49621 = false, _mut49622 = false, _mut49623 = false, _mut49624 = false, _mut49625 = false, _mut49626 = false, _mut49627 = false, _mut49628 = false, _mut49629 = false, _mut49630 = false, _mut49631 = false, _mut49632 = false, _mut49633 = false, _mut49634 = false, _mut49635 = false, _mut49636 = false, _mut49637 = false, _mut49638 = false, _mut49639 = false, _mut49640 = false, _mut49641 = false, _mut49642 = false, _mut49643 = false, _mut49644 = false, _mut49645 = false, _mut49646 = false, _mut49647 = false, _mut49648 = false, _mut49649 = false, _mut49650 = false, _mut49651 = false, _mut49652 = false, _mut49653 = false, _mut49654 = false, _mut49655 = false, _mut49656 = false, _mut49657 = false, _mut49658 = false, _mut49659 = false, _mut49660 = false;

    /**
     * Archimede's constant PI, ratio of circle circumference to diameter.
     */
    public static final double PI = AOR_plus(AOR_divide(105414357.0, 33554432.0, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43023, _mut43024, _mut43025, _mut43026), 1.984187159361080883e-9, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43027, _mut43028, _mut43029, _mut43030);

    /**
     * Napier's constant e, base of the natural logarithm.
     */
    public static final double E = AOR_plus(AOR_divide(2850325.0, 1048576.0, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43031, _mut43032, _mut43033, _mut43034), 8.254840070411028747e-8, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43035, _mut43036, _mut43037, _mut43038);

    /**
     * Index of exp(0) in the array of integer exponentials.
     */
    static final int EXP_INT_TABLE_MAX_INDEX = 750;

    /**
     * Length of the array of integer exponentials.
     */
    static final int EXP_INT_TABLE_LEN = AOR_multiply(EXP_INT_TABLE_MAX_INDEX, 2, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43039, _mut43040, _mut43041, _mut43042);

    /**
     * Logarithm table length.
     */
    static final int LN_MANT_LEN = 1024;

    // 0, 1/1024, ... 1024/1024
    static final int EXP_FRAC_TABLE_LEN = 1025;

    /**
     * StrictMath.log(Double.MAX_VALUE): {@value}
     */
    private static final double LOG_MAX_VALUE = StrictMath.log(Double.MAX_VALUE);

    /**
     * Indicator for tables initialization.
     * <p>
     * This compile-time constant should be set to true only if one explicitly
     * wants to compute the tables at class loading time instead of using the
     * already computed ones provided as literal arrays below.
     * </p>
     */
    private static final boolean RECOMPUTE_TABLES_AT_RUNTIME = false;

    /**
     * log(2) (high bits).
     */
    private static final double LN_2_A = 0.693147063255310059;

    /**
     * log(2) (low bits).
     */
    private static final double LN_2_B = 1.17304635250823482e-7;

    /**
     * Coefficients for log, when input 0.99 < x < 1.01.
     */
    private static final double[][] LN_QUICK_COEF = { { 1.0, 5.669184079525E-24 }, { -0.25, -0.25 }, { 0.3333333134651184, 1.986821492305628E-8 }, { -0.25, -6.663542893624021E-14 }, { 0.19999998807907104, 1.1921056801463227E-8 }, { -0.1666666567325592, -7.800414592973399E-9 }, { 0.1428571343421936, 5.650007086920087E-9 }, { -0.12502530217170715, -7.44321345601866E-11 }, { 0.11113807559013367, 9.219544613762692E-9 } };

    /**
     * Coefficients for log in the range of 1.0 < x < 1.0 + 2^-10.
     */
    private static final double[][] LN_HI_PREC_COEF = { { 1.0, -6.032174644509064E-23 }, { -0.25, -0.25 }, { 0.3333333134651184, 1.9868161777724352E-8 }, { -0.2499999701976776, -2.957007209750105E-8 }, { 0.19999954104423523, 1.5830993332061267E-10 }, { -0.16624879837036133, -2.6033824355191673E-8 } };

    /**
     * Sine, Cosine, Tangent tables are for 0, 1/8, 2/8, ... 13/8 = PI/2 approx.
     */
    private static final int SINE_TABLE_LEN = 14;

    /**
     * Sine table (high bits).
     */
    private static final double[] SINE_TABLE_A = { +0.0d, +0.1246747374534607d, +0.24740394949913025d, +0.366272509098053d, +0.4794255495071411d, +0.5850973129272461d, +0.6816387176513672d, +0.7675435543060303d, +0.8414709568023682d, +0.902267575263977d, +0.9489846229553223d, +0.9808930158615112d, +0.9974949359893799d, +0.9985313415527344d };

    /**
     * Sine table (low bits).
     */
    private static final double[] SINE_TABLE_B = { +0.0d, -4.068233003401932E-9d, +9.755392680573412E-9d, +1.9987994582857286E-8d, -1.0902938113007961E-8d, -3.9986783938944604E-8d, +4.23719669792332E-8d, -5.207000323380292E-8d, +2.800552834259E-8d, +1.883511811213715E-8d, -3.5997360512765566E-9d, +4.116164446561962E-8d, +5.0614674548127384E-8d, -1.0129027912496858E-9d };

    /**
     * Cosine table (high bits).
     */
    private static final double[] COSINE_TABLE_A = { +1.0d, +0.9921976327896118d, +0.9689123630523682d, +0.9305076599121094d, +0.8775825500488281d, +0.8109631538391113d, +0.7316888570785522d, +0.6409968137741089d, +0.5403022766113281d, +0.4311765432357788d, +0.3153223395347595d, +0.19454771280288696d, +0.07073719799518585d, -0.05417713522911072d };

    /**
     * Cosine table (low bits).
     */
    private static final double[] COSINE_TABLE_B = { +0.0d, +3.4439717236742845E-8d, +5.865827662008209E-8d, -3.7999795083850525E-8d, +1.184154459111628E-8d, -3.43338934259355E-8d, +1.1795268640216787E-8d, +4.438921624363781E-8d, +2.925681159240093E-8d, -2.6437112632041807E-8d, +2.2860509143963117E-8d, -4.813899778443457E-9d, +3.6725170580355583E-9d, +2.0217439756338078E-10d };

    /**
     * Tangent table, used by atan() (high bits).
     */
    private static final double[] TANGENT_TABLE_A = { +0.0d, +0.1256551444530487d, +0.25534194707870483d, +0.3936265707015991d, +0.5463024377822876d, +0.7214844226837158d, +0.9315965175628662d, +1.1974215507507324d, +1.5574076175689697d, +2.092571258544922d, +3.0095696449279785d, +5.041914939880371d, +14.101419448852539d, -18.430862426757812d };

    /**
     * Tangent table, used by atan() (low bits).
     */
    private static final double[] TANGENT_TABLE_B = { +0.0d, -7.877917738262007E-9d, -2.5857668567479893E-8d, +5.2240336371356666E-9d, +5.206150291559893E-8d, +1.8307188599677033E-8d, -5.7618793749770706E-8d, +7.848361555046424E-8d, +1.0708593250394448E-7d, +1.7827257129423813E-8d, +2.893485277253286E-8d, +3.1660099222737955E-7d, +4.983191803254889E-7d, -3.356118100840571E-7d };

    /**
     * Bits of 1/(2*pi), need for reducePayneHanek().
     */
    private static final long[] RECIP_2PI = new long[] { (0x28be60dbL << 32) | 0x9391054aL, (0x7f09d5f4L << 32) | 0x7d4d3770L, (0x36d8a566L << 32) | 0x4f10e410L, (0x7f9458eaL << 32) | 0xf7aef158L, (0x6dc91b8eL << 32) | 0x909374b8L, (0x01924bbaL << 32) | 0x82746487L, (0x3f877ac7L << 32) | 0x2c4a69cfL, (0xba208d7dL << 32) | 0x4baed121L, (0x3a671c09L << 32) | 0xad17df90L, (0x4e64758eL << 32) | 0x60d4ce7dL, (0x272117e2L << 32) | 0xef7e4a0eL, (0xc7fe25ffL << 32) | 0xf7816603L, (0xfbcbc462L << 32) | 0xd6829b47L, (0xdb4d9fb3L << 32) | 0xc9f2c26dL, (0xd3d18fd9L << 32) | 0xa797fa8bL, (0x5d49eeb1L << 32) | 0xfaf97c5eL, (0xcf41ce7dL << 32) | 0xe294a4baL, 0x9afed7ecL << 32 };

    /**
     * Bits of pi/4, need for reducePayneHanek().
     */
    private static final long[] PI_O_4_BITS = new long[] { (0xc90fdaa2L << 32) | 0x2168c234L, (0xc4c6628bL << 32) | 0x80dc1cd1L };

    /**
     * Eighths.
     * This is used by sinQ, because its faster to do a table lookup than
     * a multiply in this time-critical routine
     */
    private static final double[] EIGHTHS = { 0, 0.125, 0.25, 0.375, 0.5, 0.625, 0.75, 0.875, 1.0, 1.125, 1.25, 1.375, 1.5, 1.625 };

    /**
     * Table of 2^((n+2)/3)
     */
    private static final double[] CBRTTWO = { 0.6299605249474366, 0.7937005259840998, 1.0, 1.2599210498948732, 1.5874010519681994 };

    // 1073741824L
    private static final long HEX_40000000 = 0x40000000L;

    // 0xFFFFFFFFC0000000L;
    private static final long MASK_30BITS = AOR_minus(-1L, (AOR_minus(HEX_40000000, 1, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43043, _mut43044, _mut43045, _mut43046)), "org.apache.commons.math3.util.FastMath.readObject_589", _mut43047, _mut43048, _mut43049, _mut43050);

    /**
     * Mask used to clear the non-sign part of an int.
     */
    private static final int MASK_NON_SIGN_INT = 0x7fffffff;

    /**
     * Mask used to clear the non-sign part of a long.
     */
    private static final long MASK_NON_SIGN_LONG = 0x7fffffffffffffffl;

    /**
     * Mask used to extract exponent from double bits.
     */
    private static final long MASK_DOUBLE_EXPONENT = 0x7ff0000000000000L;

    /**
     * Mask used to extract mantissa from double bits.
     */
    private static final long MASK_DOUBLE_MANTISSA = 0x000fffffffffffffL;

    /**
     * Mask used to add implicit high order bit for normalized double.
     */
    private static final long IMPLICIT_HIGH_BIT = 0x0010000000000000L;

    /**
     * 2^52 - double numbers this large must be integral (no fraction) or NaN or Infinite
     */
    private static final double TWO_POWER_52 = 4503599627370496.0;

    /**
     * Constant: {@value}.
     */
    private static final double F_1_3 = AOR_divide(1d, 3d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43051, _mut43052, _mut43053, _mut43054);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_5 = AOR_divide(1d, 5d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43055, _mut43056, _mut43057, _mut43058);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_7 = AOR_divide(1d, 7d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43059, _mut43060, _mut43061, _mut43062);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_9 = AOR_divide(1d, 9d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43063, _mut43064, _mut43065, _mut43066);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_11 = AOR_divide(1d, 11d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43067, _mut43068, _mut43069, _mut43070);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_13 = AOR_divide(1d, 13d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43071, _mut43072, _mut43073, _mut43074);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_15 = AOR_divide(1d, 15d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43075, _mut43076, _mut43077, _mut43078);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_17 = AOR_divide(1d, 17d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43079, _mut43080, _mut43081, _mut43082);

    /**
     * Constant: {@value}.
     */
    private static final double F_3_4 = AOR_divide(3d, 4d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43083, _mut43084, _mut43085, _mut43086);

    /**
     * Constant: {@value}.
     */
    private static final double F_15_16 = AOR_divide(15d, 16d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43087, _mut43088, _mut43089, _mut43090);

    /**
     * Constant: {@value}.
     */
    private static final double F_13_14 = AOR_divide(13d, 14d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43091, _mut43092, _mut43093, _mut43094);

    /**
     * Constant: {@value}.
     */
    private static final double F_11_12 = AOR_divide(11d, 12d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43095, _mut43096, _mut43097, _mut43098);

    /**
     * Constant: {@value}.
     */
    private static final double F_9_10 = AOR_divide(9d, 10d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43099, _mut43100, _mut43101, _mut43102);

    /**
     * Constant: {@value}.
     */
    private static final double F_7_8 = AOR_divide(7d, 8d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43103, _mut43104, _mut43105, _mut43106);

    /**
     * Constant: {@value}.
     */
    private static final double F_5_6 = AOR_divide(5d, 6d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43107, _mut43108, _mut43109, _mut43110);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_2 = AOR_divide(1d, 2d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43111, _mut43112, _mut43113, _mut43114);

    /**
     * Constant: {@value}.
     */
    private static final double F_1_4 = AOR_divide(1d, 4d, "org.apache.commons.math3.util.FastMath.readObject_589", _mut43115, _mut43116, _mut43117, _mut43118);

    /**
     * Private Constructor
     */
    private FastMath() {
    }

    /**
     * Get the high order bits from the mantissa.
     * Equivalent to adding and subtracting HEX_40000 but also works for very large numbers
     *
     * @param d the value to split
     * @return the high order part of the mantissa
     */
    private static double doubleHighPart(double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.doubleHighPart_379");
        if ((_mut43129 ? (ROR_greater(d, -Precision.SAFE_MIN, "org.apache.commons.math3.util.FastMath.doubleHighPart_379", _mut43119, _mut43120, _mut43121, _mut43122, _mut43123) || ROR_less(d, Precision.SAFE_MIN, "org.apache.commons.math3.util.FastMath.doubleHighPart_379", _mut43124, _mut43125, _mut43126, _mut43127, _mut43128)) : (ROR_greater(d, -Precision.SAFE_MIN, "org.apache.commons.math3.util.FastMath.doubleHighPart_379", _mut43119, _mut43120, _mut43121, _mut43122, _mut43123) && ROR_less(d, Precision.SAFE_MIN, "org.apache.commons.math3.util.FastMath.doubleHighPart_379", _mut43124, _mut43125, _mut43126, _mut43127, _mut43128)))) {
            // These are un-normalised - don't try to convert
            return d;
        }
        // can take raw bits because just gonna convert it back
        long xl = Double.doubleToRawLongBits(d);
        // Drop low order bits
        xl &= MASK_30BITS;
        return Double.longBitsToDouble(xl);
    }

    /**
     * Compute the square root of a number.
     * <p><b>Note:</b> this implementation currently delegates to {@link Math#sqrt}
     * @param a number on which evaluation is done
     * @return square root of a
     */
    public static double sqrt(final double a) {
        return Math.sqrt(a);
    }

    /**
     * Compute the hyperbolic cosine of a number.
     * @param x number on which evaluation is done
     * @return hyperbolic cosine of x
     */
    public static double cosh(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.cosh_401");
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43130, _mut43131, _mut43132, _mut43133, _mut43134)) {
            return x;
        }
        if (ROR_greater(x, 20, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43135, _mut43136, _mut43137, _mut43138, _mut43139)) {
            if (ROR_greater_equals(x, LOG_MAX_VALUE, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43166, _mut43167, _mut43168, _mut43169, _mut43170)) {
                // Avoid overflow (MATH-905).
                final double t = exp(AOR_multiply(0.5, x, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43175, _mut43176, _mut43177, _mut43178));
                return AOR_multiply((AOR_multiply(0.5, t, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43179, _mut43180, _mut43181, _mut43182)), t, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43183, _mut43184, _mut43185, _mut43186);
            } else {
                return AOR_multiply(0.5, exp(x), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43171, _mut43172, _mut43173, _mut43174);
            }
        } else if (ROR_less(x, -20, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43140, _mut43141, _mut43142, _mut43143, _mut43144)) {
            if (ROR_less_equals(x, -LOG_MAX_VALUE, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43145, _mut43146, _mut43147, _mut43148, _mut43149)) {
                // Avoid overflow (MATH-905).
                final double t = exp(AOR_multiply(-0.5, x, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43154, _mut43155, _mut43156, _mut43157));
                return AOR_multiply((AOR_multiply(0.5, t, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43158, _mut43159, _mut43160, _mut43161)), t, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43162, _mut43163, _mut43164, _mut43165);
            } else {
                return AOR_multiply(0.5, exp(-x), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43150, _mut43151, _mut43152, _mut43153);
            }
        }
        final double[] hiPrec = new double[2];
        if (ROR_less(x, 0.0, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43187, _mut43188, _mut43189, _mut43190, _mut43191)) {
            x = -x;
        }
        exp(x, 0.0, hiPrec);
        double ya = AOR_plus(hiPrec[0], hiPrec[1], "org.apache.commons.math3.util.FastMath.cosh_401", _mut43192, _mut43193, _mut43194, _mut43195);
        double yb = -(AOR_minus(AOR_minus(ya, hiPrec[0], "org.apache.commons.math3.util.FastMath.cosh_401", _mut43196, _mut43197, _mut43198, _mut43199), hiPrec[1], "org.apache.commons.math3.util.FastMath.cosh_401", _mut43200, _mut43201, _mut43202, _mut43203));
        double temp = AOR_multiply(ya, HEX_40000000, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43204, _mut43205, _mut43206, _mut43207);
        double yaa = AOR_minus(AOR_plus(ya, temp, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43208, _mut43209, _mut43210, _mut43211), temp, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43212, _mut43213, _mut43214, _mut43215);
        double yab = AOR_minus(ya, yaa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43216, _mut43217, _mut43218, _mut43219);
        // recip = 1/y
        double recip = AOR_divide(1.0, ya, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43220, _mut43221, _mut43222, _mut43223);
        temp = AOR_multiply(recip, HEX_40000000, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43224, _mut43225, _mut43226, _mut43227);
        double recipa = AOR_minus(AOR_plus(recip, temp, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43228, _mut43229, _mut43230, _mut43231), temp, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43232, _mut43233, _mut43234, _mut43235);
        double recipb = AOR_minus(recip, recipa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43236, _mut43237, _mut43238, _mut43239);
        // Correct for rounding in division
        recipb += AOR_multiply((AOR_minus(AOR_minus(AOR_minus(AOR_minus(1.0, AOR_multiply(yaa, recipa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43240, _mut43241, _mut43242, _mut43243), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43244, _mut43245, _mut43246, _mut43247), AOR_multiply(yaa, recipb, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43248, _mut43249, _mut43250, _mut43251), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43252, _mut43253, _mut43254, _mut43255), AOR_multiply(yab, recipa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43256, _mut43257, _mut43258, _mut43259), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43260, _mut43261, _mut43262, _mut43263), AOR_multiply(yab, recipb, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43264, _mut43265, _mut43266, _mut43267), "org.apache.commons.math3.util.FastMath.cosh_401", _mut43268, _mut43269, _mut43270, _mut43271)), recip, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43272, _mut43273, _mut43274, _mut43275);
        // Account for yb
        recipb += AOR_multiply(AOR_multiply(-yb, recip, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43276, _mut43277, _mut43278, _mut43279), recip, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43280, _mut43281, _mut43282, _mut43283);
        // y = y + 1/y
        temp = AOR_plus(ya, recipa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43284, _mut43285, _mut43286, _mut43287);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43288, _mut43289, _mut43290, _mut43291), recipa, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43292, _mut43293, _mut43294, _mut43295));
        ya = temp;
        temp = AOR_plus(ya, recipb, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43296, _mut43297, _mut43298, _mut43299);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43300, _mut43301, _mut43302, _mut43303), recipb, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43304, _mut43305, _mut43306, _mut43307));
        ya = temp;
        double result = AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.cosh_401", _mut43308, _mut43309, _mut43310, _mut43311);
        result *= 0.5;
        return result;
    }

    /**
     * Compute the hyperbolic sine of a number.
     * @param x number on which evaluation is done
     * @return hyperbolic sine of x
     */
    public static double sinh(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.sinh_470");
        boolean negate = false;
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43312, _mut43313, _mut43314, _mut43315, _mut43316)) {
            return x;
        }
        if (ROR_greater(x, 20, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43317, _mut43318, _mut43319, _mut43320, _mut43321)) {
            if (ROR_greater_equals(x, LOG_MAX_VALUE, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43348, _mut43349, _mut43350, _mut43351, _mut43352)) {
                // Avoid overflow (MATH-905).
                final double t = exp(AOR_multiply(0.5, x, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43357, _mut43358, _mut43359, _mut43360));
                return AOR_multiply((AOR_multiply(0.5, t, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43361, _mut43362, _mut43363, _mut43364)), t, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43365, _mut43366, _mut43367, _mut43368);
            } else {
                return AOR_multiply(0.5, exp(x), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43353, _mut43354, _mut43355, _mut43356);
            }
        } else if (ROR_less(x, -20, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43322, _mut43323, _mut43324, _mut43325, _mut43326)) {
            if (ROR_less_equals(x, -LOG_MAX_VALUE, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43327, _mut43328, _mut43329, _mut43330, _mut43331)) {
                // Avoid overflow (MATH-905).
                final double t = exp(AOR_multiply(-0.5, x, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43336, _mut43337, _mut43338, _mut43339));
                return AOR_multiply((AOR_multiply(-0.5, t, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43340, _mut43341, _mut43342, _mut43343)), t, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43344, _mut43345, _mut43346, _mut43347);
            } else {
                return AOR_multiply(-0.5, exp(-x), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43332, _mut43333, _mut43334, _mut43335);
            }
        }
        if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43369, _mut43370, _mut43371, _mut43372, _mut43373)) {
            return x;
        }
        if (ROR_less(x, 0.0, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43374, _mut43375, _mut43376, _mut43377, _mut43378)) {
            x = -x;
            negate = true;
        }
        double result;
        if (ROR_greater(x, 0.25, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43379, _mut43380, _mut43381, _mut43382, _mut43383)) {
            double[] hiPrec = new double[2];
            exp(x, 0.0, hiPrec);
            double ya = AOR_plus(hiPrec[0], hiPrec[1], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43532, _mut43533, _mut43534, _mut43535);
            double yb = -(AOR_minus(AOR_minus(ya, hiPrec[0], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43536, _mut43537, _mut43538, _mut43539), hiPrec[1], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43540, _mut43541, _mut43542, _mut43543));
            double temp = AOR_multiply(ya, HEX_40000000, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43544, _mut43545, _mut43546, _mut43547);
            double yaa = AOR_minus(AOR_plus(ya, temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43548, _mut43549, _mut43550, _mut43551), temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43552, _mut43553, _mut43554, _mut43555);
            double yab = AOR_minus(ya, yaa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43556, _mut43557, _mut43558, _mut43559);
            // recip = 1/y
            double recip = AOR_divide(1.0, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43560, _mut43561, _mut43562, _mut43563);
            temp = AOR_multiply(recip, HEX_40000000, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43564, _mut43565, _mut43566, _mut43567);
            double recipa = AOR_minus(AOR_plus(recip, temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43568, _mut43569, _mut43570, _mut43571), temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43572, _mut43573, _mut43574, _mut43575);
            double recipb = AOR_minus(recip, recipa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43576, _mut43577, _mut43578, _mut43579);
            // Correct for rounding in division
            recipb += AOR_multiply((AOR_minus(AOR_minus(AOR_minus(AOR_minus(1.0, AOR_multiply(yaa, recipa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43580, _mut43581, _mut43582, _mut43583), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43584, _mut43585, _mut43586, _mut43587), AOR_multiply(yaa, recipb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43588, _mut43589, _mut43590, _mut43591), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43592, _mut43593, _mut43594, _mut43595), AOR_multiply(yab, recipa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43596, _mut43597, _mut43598, _mut43599), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43600, _mut43601, _mut43602, _mut43603), AOR_multiply(yab, recipb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43604, _mut43605, _mut43606, _mut43607), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43608, _mut43609, _mut43610, _mut43611)), recip, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43612, _mut43613, _mut43614, _mut43615);
            // Account for yb
            recipb += AOR_multiply(AOR_multiply(-yb, recip, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43616, _mut43617, _mut43618, _mut43619), recip, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43620, _mut43621, _mut43622, _mut43623);
            recipa = -recipa;
            recipb = -recipb;
            // y = y + 1/y
            temp = AOR_plus(ya, recipa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43624, _mut43625, _mut43626, _mut43627);
            yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43628, _mut43629, _mut43630, _mut43631), recipa, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43632, _mut43633, _mut43634, _mut43635));
            ya = temp;
            temp = AOR_plus(ya, recipb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43636, _mut43637, _mut43638, _mut43639);
            yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43640, _mut43641, _mut43642, _mut43643), recipb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43644, _mut43645, _mut43646, _mut43647));
            ya = temp;
            result = AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43648, _mut43649, _mut43650, _mut43651);
            result *= 0.5;
        } else {
            double[] hiPrec = new double[2];
            expm1(x, hiPrec);
            double ya = AOR_plus(hiPrec[0], hiPrec[1], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43384, _mut43385, _mut43386, _mut43387);
            double yb = -(AOR_minus(AOR_minus(ya, hiPrec[0], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43388, _mut43389, _mut43390, _mut43391), hiPrec[1], "org.apache.commons.math3.util.FastMath.sinh_470", _mut43392, _mut43393, _mut43394, _mut43395));
            /* Compute expm1(-x) = -expm1(x) / (expm1(x) + 1) */
            double denom = AOR_plus(1.0, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43396, _mut43397, _mut43398, _mut43399);
            double denomr = AOR_divide(1.0, denom, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43400, _mut43401, _mut43402, _mut43403);
            double denomb = AOR_plus(-(AOR_minus(AOR_minus(denom, 1.0, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43404, _mut43405, _mut43406, _mut43407), ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43408, _mut43409, _mut43410, _mut43411)), yb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43412, _mut43413, _mut43414, _mut43415);
            double ratio = AOR_multiply(ya, denomr, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43416, _mut43417, _mut43418, _mut43419);
            double temp = AOR_multiply(ratio, HEX_40000000, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43420, _mut43421, _mut43422, _mut43423);
            double ra = AOR_minus(AOR_plus(ratio, temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43424, _mut43425, _mut43426, _mut43427), temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43428, _mut43429, _mut43430, _mut43431);
            double rb = AOR_minus(ratio, ra, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43432, _mut43433, _mut43434, _mut43435);
            temp = AOR_multiply(denom, HEX_40000000, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43436, _mut43437, _mut43438, _mut43439);
            double za = AOR_minus(AOR_plus(denom, temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43440, _mut43441, _mut43442, _mut43443), temp, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43444, _mut43445, _mut43446, _mut43447);
            double zb = AOR_minus(denom, za, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43448, _mut43449, _mut43450, _mut43451);
            rb += AOR_multiply((AOR_minus(AOR_minus(AOR_minus(AOR_minus(ya, AOR_multiply(za, ra, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43452, _mut43453, _mut43454, _mut43455), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43456, _mut43457, _mut43458, _mut43459), AOR_multiply(za, rb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43460, _mut43461, _mut43462, _mut43463), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43464, _mut43465, _mut43466, _mut43467), AOR_multiply(zb, ra, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43468, _mut43469, _mut43470, _mut43471), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43472, _mut43473, _mut43474, _mut43475), AOR_multiply(zb, rb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43476, _mut43477, _mut43478, _mut43479), "org.apache.commons.math3.util.FastMath.sinh_470", _mut43480, _mut43481, _mut43482, _mut43483)), denomr, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43484, _mut43485, _mut43486, _mut43487);
            // numerator
            rb += AOR_multiply(yb, denomr, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43488, _mut43489, _mut43490, _mut43491);
            // denominator
            rb += AOR_multiply(AOR_multiply(AOR_multiply(-ya, denomb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43492, _mut43493, _mut43494, _mut43495), denomr, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43496, _mut43497, _mut43498, _mut43499), denomr, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43500, _mut43501, _mut43502, _mut43503);
            // y = y - 1/y
            temp = AOR_plus(ya, ra, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43504, _mut43505, _mut43506, _mut43507);
            yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43508, _mut43509, _mut43510, _mut43511), ra, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43512, _mut43513, _mut43514, _mut43515));
            ya = temp;
            temp = AOR_plus(ya, rb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43516, _mut43517, _mut43518, _mut43519);
            yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43520, _mut43521, _mut43522, _mut43523), rb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43524, _mut43525, _mut43526, _mut43527));
            ya = temp;
            result = AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.sinh_470", _mut43528, _mut43529, _mut43530, _mut43531);
            result *= 0.5;
        }
        if (negate) {
            result = -result;
        }
        return result;
    }

    /**
     * Compute the hyperbolic tangent of a number.
     * @param x number on which evaluation is done
     * @return hyperbolic tangent of x
     */
    public static double tanh(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.tanh_595");
        boolean negate = false;
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43652, _mut43653, _mut43654, _mut43655, _mut43656)) {
            return x;
        }
        if (ROR_greater(x, 20.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43657, _mut43658, _mut43659, _mut43660, _mut43661)) {
            return 1.0;
        }
        if (ROR_less(x, -20, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43662, _mut43663, _mut43664, _mut43665, _mut43666)) {
            return -1.0;
        }
        if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43667, _mut43668, _mut43669, _mut43670, _mut43671)) {
            return x;
        }
        if (ROR_less(x, 0.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43672, _mut43673, _mut43674, _mut43675, _mut43676)) {
            x = -x;
            negate = true;
        }
        double result;
        if (ROR_greater_equals(x, 0.5, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43677, _mut43678, _mut43679, _mut43680, _mut43681)) {
            double[] hiPrec = new double[2];
            // tanh(x) = (exp(2x) - 1) / (exp(2x) + 1)
            exp(AOR_multiply(x, 2.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43814, _mut43815, _mut43816, _mut43817), 0.0, hiPrec);
            double ya = AOR_plus(hiPrec[0], hiPrec[1], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43818, _mut43819, _mut43820, _mut43821);
            double yb = -(AOR_minus(AOR_minus(ya, hiPrec[0], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43822, _mut43823, _mut43824, _mut43825), hiPrec[1], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43826, _mut43827, _mut43828, _mut43829));
            /* Numerator */
            double na = AOR_plus(-1.0, ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43830, _mut43831, _mut43832, _mut43833);
            double nb = -(AOR_minus(AOR_plus(na, 1.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43834, _mut43835, _mut43836, _mut43837), ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43838, _mut43839, _mut43840, _mut43841));
            double temp = AOR_plus(na, yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43842, _mut43843, _mut43844, _mut43845);
            nb += -(AOR_minus(AOR_minus(temp, na, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43846, _mut43847, _mut43848, _mut43849), yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43850, _mut43851, _mut43852, _mut43853));
            na = temp;
            /* Denominator */
            double da = AOR_plus(1.0, ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43854, _mut43855, _mut43856, _mut43857);
            double db = -(AOR_minus(AOR_minus(da, 1.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43858, _mut43859, _mut43860, _mut43861), ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43862, _mut43863, _mut43864, _mut43865));
            temp = AOR_plus(da, yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43866, _mut43867, _mut43868, _mut43869);
            db += -(AOR_minus(AOR_minus(temp, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43870, _mut43871, _mut43872, _mut43873), yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43874, _mut43875, _mut43876, _mut43877));
            da = temp;
            temp = AOR_multiply(da, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43878, _mut43879, _mut43880, _mut43881);
            double daa = AOR_minus(AOR_plus(da, temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43882, _mut43883, _mut43884, _mut43885), temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43886, _mut43887, _mut43888, _mut43889);
            double dab = AOR_minus(da, daa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43890, _mut43891, _mut43892, _mut43893);
            // ratio = na/da
            double ratio = AOR_divide(na, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43894, _mut43895, _mut43896, _mut43897);
            temp = AOR_multiply(ratio, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43898, _mut43899, _mut43900, _mut43901);
            double ratioa = AOR_minus(AOR_plus(ratio, temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43902, _mut43903, _mut43904, _mut43905), temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43906, _mut43907, _mut43908, _mut43909);
            double ratiob = AOR_minus(ratio, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43910, _mut43911, _mut43912, _mut43913);
            // Correct for rounding in division
            ratiob += AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(na, AOR_multiply(daa, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43914, _mut43915, _mut43916, _mut43917), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43918, _mut43919, _mut43920, _mut43921), AOR_multiply(daa, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43922, _mut43923, _mut43924, _mut43925), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43926, _mut43927, _mut43928, _mut43929), AOR_multiply(dab, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43930, _mut43931, _mut43932, _mut43933), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43934, _mut43935, _mut43936, _mut43937), AOR_multiply(dab, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43938, _mut43939, _mut43940, _mut43941), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43942, _mut43943, _mut43944, _mut43945)), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43946, _mut43947, _mut43948, _mut43949);
            // Account for nb
            ratiob += AOR_divide(nb, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43950, _mut43951, _mut43952, _mut43953);
            // Account for db
            ratiob += AOR_divide(AOR_divide(AOR_multiply(-db, na, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43954, _mut43955, _mut43956, _mut43957), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43958, _mut43959, _mut43960, _mut43961), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43962, _mut43963, _mut43964, _mut43965);
            result = AOR_plus(ratioa, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43966, _mut43967, _mut43968, _mut43969);
        } else {
            double[] hiPrec = new double[2];
            // tanh(x) = expm1(2x) / (expm1(2x) + 2)
            expm1(AOR_multiply(x, 2.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43682, _mut43683, _mut43684, _mut43685), hiPrec);
            double ya = AOR_plus(hiPrec[0], hiPrec[1], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43686, _mut43687, _mut43688, _mut43689);
            double yb = -(AOR_minus(AOR_minus(ya, hiPrec[0], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43690, _mut43691, _mut43692, _mut43693), hiPrec[1], "org.apache.commons.math3.util.FastMath.tanh_595", _mut43694, _mut43695, _mut43696, _mut43697));
            /* Numerator */
            double na = ya;
            double nb = yb;
            /* Denominator */
            double da = AOR_plus(2.0, ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43698, _mut43699, _mut43700, _mut43701);
            double db = -(AOR_minus(AOR_minus(da, 2.0, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43702, _mut43703, _mut43704, _mut43705), ya, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43706, _mut43707, _mut43708, _mut43709));
            double temp = AOR_plus(da, yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43710, _mut43711, _mut43712, _mut43713);
            db += -(AOR_minus(AOR_minus(temp, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43714, _mut43715, _mut43716, _mut43717), yb, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43718, _mut43719, _mut43720, _mut43721));
            da = temp;
            temp = AOR_multiply(da, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43722, _mut43723, _mut43724, _mut43725);
            double daa = AOR_minus(AOR_plus(da, temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43726, _mut43727, _mut43728, _mut43729), temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43730, _mut43731, _mut43732, _mut43733);
            double dab = AOR_minus(da, daa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43734, _mut43735, _mut43736, _mut43737);
            // ratio = na/da
            double ratio = AOR_divide(na, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43738, _mut43739, _mut43740, _mut43741);
            temp = AOR_multiply(ratio, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43742, _mut43743, _mut43744, _mut43745);
            double ratioa = AOR_minus(AOR_plus(ratio, temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43746, _mut43747, _mut43748, _mut43749), temp, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43750, _mut43751, _mut43752, _mut43753);
            double ratiob = AOR_minus(ratio, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43754, _mut43755, _mut43756, _mut43757);
            // Correct for rounding in division
            ratiob += AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(na, AOR_multiply(daa, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43758, _mut43759, _mut43760, _mut43761), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43762, _mut43763, _mut43764, _mut43765), AOR_multiply(daa, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43766, _mut43767, _mut43768, _mut43769), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43770, _mut43771, _mut43772, _mut43773), AOR_multiply(dab, ratioa, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43774, _mut43775, _mut43776, _mut43777), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43778, _mut43779, _mut43780, _mut43781), AOR_multiply(dab, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43782, _mut43783, _mut43784, _mut43785), "org.apache.commons.math3.util.FastMath.tanh_595", _mut43786, _mut43787, _mut43788, _mut43789)), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43790, _mut43791, _mut43792, _mut43793);
            // Account for nb
            ratiob += AOR_divide(nb, da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43794, _mut43795, _mut43796, _mut43797);
            // Account for db
            ratiob += AOR_divide(AOR_divide(AOR_multiply(-db, na, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43798, _mut43799, _mut43800, _mut43801), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43802, _mut43803, _mut43804, _mut43805), da, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43806, _mut43807, _mut43808, _mut43809);
            result = AOR_plus(ratioa, ratiob, "org.apache.commons.math3.util.FastMath.tanh_595", _mut43810, _mut43811, _mut43812, _mut43813);
        }
        if (negate) {
            result = -result;
        }
        return result;
    }

    /**
     * Compute the inverse hyperbolic cosine of a number.
     * @param a number on which evaluation is done
     * @return inverse hyperbolic cosine of a
     */
    public static double acosh(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.acosh_719");
        return FastMath.log(AOR_plus(a, FastMath.sqrt(AOR_minus(AOR_multiply(a, a, "org.apache.commons.math3.util.FastMath.acosh_719", _mut43970, _mut43971, _mut43972, _mut43973), 1, "org.apache.commons.math3.util.FastMath.acosh_719", _mut43974, _mut43975, _mut43976, _mut43977)), "org.apache.commons.math3.util.FastMath.acosh_719", _mut43978, _mut43979, _mut43980, _mut43981));
    }

    /**
     * Compute the inverse hyperbolic sine of a number.
     * @param a number on which evaluation is done
     * @return inverse hyperbolic sine of a
     */
    public static double asinh(double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.asinh_727");
        boolean negative = false;
        if (ROR_less(a, 0, "org.apache.commons.math3.util.FastMath.asinh_727", _mut43982, _mut43983, _mut43984, _mut43985, _mut43986)) {
            negative = true;
            a = -a;
        }
        double absAsinh;
        if (ROR_greater(a, 0.167, "org.apache.commons.math3.util.FastMath.asinh_727", _mut43987, _mut43988, _mut43989, _mut43990, _mut43991)) {
            absAsinh = FastMath.log(AOR_plus(FastMath.sqrt(AOR_plus(AOR_multiply(a, a, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44267, _mut44268, _mut44269, _mut44270), 1, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44271, _mut44272, _mut44273, _mut44274)), a, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44275, _mut44276, _mut44277, _mut44278));
        } else {
            final double a2 = AOR_multiply(a, a, "org.apache.commons.math3.util.FastMath.asinh_727", _mut43992, _mut43993, _mut43994, _mut43995);
            if (ROR_greater(a, 0.097, "org.apache.commons.math3.util.FastMath.asinh_727", _mut43996, _mut43997, _mut43998, _mut43999, _mut44000)) {
                absAsinh = AOR_multiply(a, (AOR_minus(1, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_3, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_5, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_7, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_9, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_11, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_13, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_15, AOR_multiply(AOR_multiply(a2, F_1_17, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44167, _mut44168, _mut44169, _mut44170), F_15_16, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44171, _mut44172, _mut44173, _mut44174), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44175, _mut44176, _mut44177, _mut44178)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44179, _mut44180, _mut44181, _mut44182), F_13_14, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44183, _mut44184, _mut44185, _mut44186), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44187, _mut44188, _mut44189, _mut44190)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44191, _mut44192, _mut44193, _mut44194), F_11_12, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44195, _mut44196, _mut44197, _mut44198), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44199, _mut44200, _mut44201, _mut44202)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44203, _mut44204, _mut44205, _mut44206), F_9_10, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44207, _mut44208, _mut44209, _mut44210), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44211, _mut44212, _mut44213, _mut44214)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44215, _mut44216, _mut44217, _mut44218), F_7_8, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44219, _mut44220, _mut44221, _mut44222), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44223, _mut44224, _mut44225, _mut44226)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44227, _mut44228, _mut44229, _mut44230), F_5_6, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44231, _mut44232, _mut44233, _mut44234), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44235, _mut44236, _mut44237, _mut44238)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44239, _mut44240, _mut44241, _mut44242), F_3_4, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44243, _mut44244, _mut44245, _mut44246), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44247, _mut44248, _mut44249, _mut44250)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44251, _mut44252, _mut44253, _mut44254), F_1_2, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44255, _mut44256, _mut44257, _mut44258), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44259, _mut44260, _mut44261, _mut44262)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44263, _mut44264, _mut44265, _mut44266);
            } else if (ROR_greater(a, 0.036, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44001, _mut44002, _mut44003, _mut44004, _mut44005)) {
                absAsinh = AOR_multiply(a, (AOR_minus(1, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_3, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_5, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_7, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_9, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_11, AOR_multiply(AOR_multiply(a2, F_1_13, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44091, _mut44092, _mut44093, _mut44094), F_11_12, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44095, _mut44096, _mut44097, _mut44098), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44099, _mut44100, _mut44101, _mut44102)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44103, _mut44104, _mut44105, _mut44106), F_9_10, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44107, _mut44108, _mut44109, _mut44110), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44111, _mut44112, _mut44113, _mut44114)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44115, _mut44116, _mut44117, _mut44118), F_7_8, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44119, _mut44120, _mut44121, _mut44122), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44123, _mut44124, _mut44125, _mut44126)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44127, _mut44128, _mut44129, _mut44130), F_5_6, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44131, _mut44132, _mut44133, _mut44134), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44135, _mut44136, _mut44137, _mut44138)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44139, _mut44140, _mut44141, _mut44142), F_3_4, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44143, _mut44144, _mut44145, _mut44146), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44147, _mut44148, _mut44149, _mut44150)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44151, _mut44152, _mut44153, _mut44154), F_1_2, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44155, _mut44156, _mut44157, _mut44158), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44159, _mut44160, _mut44161, _mut44162)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44163, _mut44164, _mut44165, _mut44166);
            } else if (ROR_greater(a, 0.0036, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44006, _mut44007, _mut44008, _mut44009, _mut44010)) {
                absAsinh = AOR_multiply(a, (AOR_minus(1, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_3, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_5, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_7, AOR_multiply(AOR_multiply(a2, F_1_9, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44039, _mut44040, _mut44041, _mut44042), F_7_8, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44043, _mut44044, _mut44045, _mut44046), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44047, _mut44048, _mut44049, _mut44050)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44051, _mut44052, _mut44053, _mut44054), F_5_6, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44055, _mut44056, _mut44057, _mut44058), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44059, _mut44060, _mut44061, _mut44062)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44063, _mut44064, _mut44065, _mut44066), F_3_4, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44067, _mut44068, _mut44069, _mut44070), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44071, _mut44072, _mut44073, _mut44074)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44075, _mut44076, _mut44077, _mut44078), F_1_2, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44079, _mut44080, _mut44081, _mut44082), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44083, _mut44084, _mut44085, _mut44086)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44087, _mut44088, _mut44089, _mut44090);
            } else {
                absAsinh = AOR_multiply(a, (AOR_minus(1, AOR_multiply(AOR_multiply(a2, (AOR_minus(F_1_3, AOR_multiply(AOR_multiply(a2, F_1_5, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44011, _mut44012, _mut44013, _mut44014), F_3_4, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44015, _mut44016, _mut44017, _mut44018), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44019, _mut44020, _mut44021, _mut44022)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44023, _mut44024, _mut44025, _mut44026), F_1_2, "org.apache.commons.math3.util.FastMath.asinh_727", _mut44027, _mut44028, _mut44029, _mut44030), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44031, _mut44032, _mut44033, _mut44034)), "org.apache.commons.math3.util.FastMath.asinh_727", _mut44035, _mut44036, _mut44037, _mut44038);
            }
        }
        return negative ? -absAsinh : absAsinh;
    }

    /**
     * Compute the inverse hyperbolic tangent of a number.
     * @param a number on which evaluation is done
     * @return inverse hyperbolic tangent of a
     */
    public static double atanh(double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.atanh_757");
        boolean negative = false;
        if (ROR_less(a, 0, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44279, _mut44280, _mut44281, _mut44282, _mut44283)) {
            negative = true;
            a = -a;
        }
        double absAtanh;
        if (ROR_greater(a, 0.15, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44284, _mut44285, _mut44286, _mut44287, _mut44288)) {
            absAtanh = AOR_multiply(0.5, FastMath.log(AOR_divide((AOR_plus(1, a, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44484, _mut44485, _mut44486, _mut44487)), (AOR_minus(1, a, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44488, _mut44489, _mut44490, _mut44491)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44492, _mut44493, _mut44494, _mut44495)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44496, _mut44497, _mut44498, _mut44499);
        } else {
            final double a2 = AOR_multiply(a, a, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44289, _mut44290, _mut44291, _mut44292);
            if (ROR_greater(a, 0.087, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44293, _mut44294, _mut44295, _mut44296, _mut44297)) {
                absAtanh = AOR_multiply(a, (AOR_plus(1, AOR_multiply(a2, (AOR_plus(F_1_3, AOR_multiply(a2, (AOR_plus(F_1_5, AOR_multiply(a2, (AOR_plus(F_1_7, AOR_multiply(a2, (AOR_plus(F_1_9, AOR_multiply(a2, (AOR_plus(F_1_11, AOR_multiply(a2, (AOR_plus(F_1_13, AOR_multiply(a2, (AOR_plus(F_1_15, AOR_multiply(a2, F_1_17, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44416, _mut44417, _mut44418, _mut44419), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44420, _mut44421, _mut44422, _mut44423)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44424, _mut44425, _mut44426, _mut44427), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44428, _mut44429, _mut44430, _mut44431)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44432, _mut44433, _mut44434, _mut44435), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44436, _mut44437, _mut44438, _mut44439)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44440, _mut44441, _mut44442, _mut44443), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44444, _mut44445, _mut44446, _mut44447)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44448, _mut44449, _mut44450, _mut44451), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44452, _mut44453, _mut44454, _mut44455)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44456, _mut44457, _mut44458, _mut44459), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44460, _mut44461, _mut44462, _mut44463)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44464, _mut44465, _mut44466, _mut44467), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44468, _mut44469, _mut44470, _mut44471)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44472, _mut44473, _mut44474, _mut44475), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44476, _mut44477, _mut44478, _mut44479)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44480, _mut44481, _mut44482, _mut44483);
            } else if (ROR_greater(a, 0.031, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44298, _mut44299, _mut44300, _mut44301, _mut44302)) {
                absAtanh = AOR_multiply(a, (AOR_plus(1, AOR_multiply(a2, (AOR_plus(F_1_3, AOR_multiply(a2, (AOR_plus(F_1_5, AOR_multiply(a2, (AOR_plus(F_1_7, AOR_multiply(a2, (AOR_plus(F_1_9, AOR_multiply(a2, (AOR_plus(F_1_11, AOR_multiply(a2, F_1_13, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44364, _mut44365, _mut44366, _mut44367), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44368, _mut44369, _mut44370, _mut44371)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44372, _mut44373, _mut44374, _mut44375), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44376, _mut44377, _mut44378, _mut44379)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44380, _mut44381, _mut44382, _mut44383), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44384, _mut44385, _mut44386, _mut44387)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44388, _mut44389, _mut44390, _mut44391), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44392, _mut44393, _mut44394, _mut44395)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44396, _mut44397, _mut44398, _mut44399), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44400, _mut44401, _mut44402, _mut44403)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44404, _mut44405, _mut44406, _mut44407), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44408, _mut44409, _mut44410, _mut44411)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44412, _mut44413, _mut44414, _mut44415);
            } else if (ROR_greater(a, 0.003, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44303, _mut44304, _mut44305, _mut44306, _mut44307)) {
                absAtanh = AOR_multiply(a, (AOR_plus(1, AOR_multiply(a2, (AOR_plus(F_1_3, AOR_multiply(a2, (AOR_plus(F_1_5, AOR_multiply(a2, (AOR_plus(F_1_7, AOR_multiply(a2, F_1_9, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44328, _mut44329, _mut44330, _mut44331), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44332, _mut44333, _mut44334, _mut44335)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44336, _mut44337, _mut44338, _mut44339), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44340, _mut44341, _mut44342, _mut44343)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44344, _mut44345, _mut44346, _mut44347), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44348, _mut44349, _mut44350, _mut44351)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44352, _mut44353, _mut44354, _mut44355), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44356, _mut44357, _mut44358, _mut44359)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44360, _mut44361, _mut44362, _mut44363);
            } else {
                absAtanh = AOR_multiply(a, (AOR_plus(1, AOR_multiply(a2, (AOR_plus(F_1_3, AOR_multiply(a2, F_1_5, "org.apache.commons.math3.util.FastMath.atanh_757", _mut44308, _mut44309, _mut44310, _mut44311), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44312, _mut44313, _mut44314, _mut44315)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44316, _mut44317, _mut44318, _mut44319), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44320, _mut44321, _mut44322, _mut44323)), "org.apache.commons.math3.util.FastMath.atanh_757", _mut44324, _mut44325, _mut44326, _mut44327);
            }
        }
        return negative ? -absAtanh : absAtanh;
    }

    /**
     * Compute the signum of a number.
     * The signum is -1 for negative numbers, +1 for positive numbers and 0 otherwise
     * @param a number on which evaluation is done
     * @return -1.0, -0.0, +0.0, +1.0 or NaN depending on sign of a
     */
    public static double signum(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.signum_788");
        // return +0.0/-0.0/NaN depending on a
        return (ROR_less(a, 0.0, "org.apache.commons.math3.util.FastMath.signum_788", _mut44500, _mut44501, _mut44502, _mut44503, _mut44504)) ? -1.0 : ((ROR_greater(a, 0.0, "org.apache.commons.math3.util.FastMath.signum_788", _mut44505, _mut44506, _mut44507, _mut44508, _mut44509)) ? 1.0 : a);
    }

    /**
     * Compute the signum of a number.
     * The signum is -1 for negative numbers, +1 for positive numbers and 0 otherwise
     * @param a number on which evaluation is done
     * @return -1.0, -0.0, +0.0, +1.0 or NaN depending on sign of a
     */
    public static float signum(final float a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.signum_797");
        // return +0.0/-0.0/NaN depending on a
        return (ROR_less(a, 0.0f, "org.apache.commons.math3.util.FastMath.signum_797", _mut44510, _mut44511, _mut44512, _mut44513, _mut44514)) ? -1.0f : ((ROR_greater(a, 0.0f, "org.apache.commons.math3.util.FastMath.signum_797", _mut44515, _mut44516, _mut44517, _mut44518, _mut44519)) ? 1.0f : a);
    }

    /**
     * Compute next number towards positive infinity.
     * @param a number to which neighbor should be computed
     * @return neighbor of a towards positive infinity
     */
    public static double nextUp(final double a) {
        return nextAfter(a, Double.POSITIVE_INFINITY);
    }

    /**
     * Compute next number towards positive infinity.
     * @param a number to which neighbor should be computed
     * @return neighbor of a towards positive infinity
     */
    public static float nextUp(final float a) {
        return nextAfter(a, Float.POSITIVE_INFINITY);
    }

    /**
     * Compute next number towards negative infinity.
     * @param a number to which neighbor should be computed
     * @return neighbor of a towards negative infinity
     * @since 3.4
     */
    public static double nextDown(final double a) {
        return nextAfter(a, Double.NEGATIVE_INFINITY);
    }

    /**
     * Compute next number towards negative infinity.
     * @param a number to which neighbor should be computed
     * @return neighbor of a towards negative infinity
     * @since 3.4
     */
    public static float nextDown(final float a) {
        return nextAfter(a, Float.NEGATIVE_INFINITY);
    }

    /**
     * Returns a pseudo-random number between 0.0 and 1.0.
     * <p><b>Note:</b> this implementation currently delegates to {@link Math#random}
     * @return a random number between 0.0 and 1.0
     */
    public static double random() {
        return Math.random();
    }

    /**
     * Exponential function.
     *
     * Computes exp(x), function result is nearly rounded.   It will be correctly
     * rounded to the theoretical value for 99.9% of input values, otherwise it will
     * have a 1 ULP error.
     *
     * Method:
     *    Lookup intVal = exp(int(x))
     *    Lookup fracVal = exp(int(x-int(x) / 1024.0) * 1024.0 );
     *    Compute z as the exponential of the remaining bits by a polynomial minus one
     *    exp(x) = intVal * fracVal * (1 + z)
     *
     * Accuracy:
     *    Calculation is done with 63 bits of precision, so result should be correctly
     *    rounded for 99.9% of input values, with less than 1 ULP error otherwise.
     *
     * @param x   a double
     * @return double e<sup>x</sup>
     */
    public static double exp(double x) {
        return exp(x, 0.0, null);
    }

    /**
     * Internal helper method for exponential function.
     * @param x original argument of the exponential function
     * @param extra extra bits of precision on input (To Be Confirmed)
     * @param hiPrec extra bits of precision on output (To Be Confirmed)
     * @return exp(x)
     */
    private static double exp(double x, double extra, double[] hiPrec) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.exp_874");
        double intPartA;
        double intPartB;
        int intVal = (int) x;
        /* Lookup exp(floor(x)).
         * intPartA will have the upper 22 bits, intPartB will have the lower
         * 52 bits.
         */
        if (ROR_less(x, 0.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44520, _mut44521, _mut44522, _mut44523, _mut44524)) {
            // may be affected by a JIT bug. Subsequent comparisons can safely use intVal
            if (ROR_less(x, -746d, "org.apache.commons.math3.util.FastMath.exp_874", _mut44530, _mut44531, _mut44532, _mut44533, _mut44534)) {
                if (hiPrec != null) {
                    hiPrec[0] = 0.0;
                    hiPrec[1] = 0.0;
                }
                return 0.0;
            }
            if (ROR_less(intVal, -709, "org.apache.commons.math3.util.FastMath.exp_874", _mut44535, _mut44536, _mut44537, _mut44538, _mut44539)) {
                /* This will produce a subnormal output */
                final double result = AOR_divide(exp(AOR_plus(x, 40.19140625, "org.apache.commons.math3.util.FastMath.exp_874", _mut44540, _mut44541, _mut44542, _mut44543), extra, hiPrec), 285040095144011776.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44544, _mut44545, _mut44546, _mut44547);
                if (hiPrec != null) {
                    hiPrec[0] /= 285040095144011776.0;
                    hiPrec[1] /= 285040095144011776.0;
                }
                return result;
            }
            if (ROR_equals(intVal, -709, "org.apache.commons.math3.util.FastMath.exp_874", _mut44548, _mut44549, _mut44550, _mut44551, _mut44552)) {
                /* exp(1.494140625) is nearly a machine number... */
                final double result = AOR_divide(exp(AOR_plus(x, 1.494140625, "org.apache.commons.math3.util.FastMath.exp_874", _mut44553, _mut44554, _mut44555, _mut44556), extra, hiPrec), 4.455505956692756620, "org.apache.commons.math3.util.FastMath.exp_874", _mut44557, _mut44558, _mut44559, _mut44560);
                if (hiPrec != null) {
                    hiPrec[0] /= 4.455505956692756620;
                    hiPrec[1] /= 4.455505956692756620;
                }
                return result;
            }
            intVal--;
        } else {
            if (ROR_greater(intVal, 709, "org.apache.commons.math3.util.FastMath.exp_874", _mut44525, _mut44526, _mut44527, _mut44528, _mut44529)) {
                if (hiPrec != null) {
                    hiPrec[0] = Double.POSITIVE_INFINITY;
                    hiPrec[1] = 0.0;
                }
                return Double.POSITIVE_INFINITY;
            }
        }
        intPartA = ExpIntTable.EXP_INT_TABLE_A[AOR_plus(EXP_INT_TABLE_MAX_INDEX, intVal, "org.apache.commons.math3.util.FastMath.exp_874", _mut44561, _mut44562, _mut44563, _mut44564)];
        intPartB = ExpIntTable.EXP_INT_TABLE_B[AOR_plus(EXP_INT_TABLE_MAX_INDEX, intVal, "org.apache.commons.math3.util.FastMath.exp_874", _mut44565, _mut44566, _mut44567, _mut44568)];
        /* Get the fractional part of x, find the greatest multiple of 2^-10 less than
         * x and look up the exp function of it.
         * fracPartA will have the upper 22 bits, fracPartB the lower 52 bits.
         */
        final int intFrac = (int) (AOR_multiply((AOR_minus(x, intVal, "org.apache.commons.math3.util.FastMath.exp_874", _mut44569, _mut44570, _mut44571, _mut44572)), 1024.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44573, _mut44574, _mut44575, _mut44576));
        final double fracPartA = ExpFracTable.EXP_FRAC_TABLE_A[intFrac];
        final double fracPartB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
        /* epsilon is the difference in x from the nearest multiple of 2^-10.  It
         * has a value in the range 0 <= epsilon < 2^-10.
         * Do the subtraction from x as the last step to avoid possible loss of precision.
         */
        final double epsilon = AOR_minus(x, (AOR_plus(intVal, AOR_divide(intFrac, 1024.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44577, _mut44578, _mut44579, _mut44580), "org.apache.commons.math3.util.FastMath.exp_874", _mut44581, _mut44582, _mut44583, _mut44584)), "org.apache.commons.math3.util.FastMath.exp_874", _mut44585, _mut44586, _mut44587, _mut44588);
        /* Remez generated polynomial.  Converges on the interval [0, 2^-10], error
       is less than 0.5 ULP */
        double z = 0.04168701738764507;
        z = AOR_plus(AOR_multiply(z, epsilon, "org.apache.commons.math3.util.FastMath.exp_874", _mut44589, _mut44590, _mut44591, _mut44592), 0.1666666505023083, "org.apache.commons.math3.util.FastMath.exp_874", _mut44593, _mut44594, _mut44595, _mut44596);
        z = AOR_plus(AOR_multiply(z, epsilon, "org.apache.commons.math3.util.FastMath.exp_874", _mut44597, _mut44598, _mut44599, _mut44600), 0.5000000000042687, "org.apache.commons.math3.util.FastMath.exp_874", _mut44601, _mut44602, _mut44603, _mut44604);
        z = AOR_plus(AOR_multiply(z, epsilon, "org.apache.commons.math3.util.FastMath.exp_874", _mut44605, _mut44606, _mut44607, _mut44608), 1.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44609, _mut44610, _mut44611, _mut44612);
        z = AOR_plus(AOR_multiply(z, epsilon, "org.apache.commons.math3.util.FastMath.exp_874", _mut44613, _mut44614, _mut44615, _mut44616), -3.940510424527919E-20, "org.apache.commons.math3.util.FastMath.exp_874", _mut44617, _mut44618, _mut44619, _mut44620);
        /* Compute (intPartA+intPartB) * (fracPartA+fracPartB) by binomial
       expansion.
       tempA is exact since intPartA and intPartB only have 22 bits each.
       tempB will have 52 bits of precision.
         */
        double tempA = AOR_multiply(intPartA, fracPartA, "org.apache.commons.math3.util.FastMath.exp_874", _mut44621, _mut44622, _mut44623, _mut44624);
        double tempB = AOR_plus(AOR_plus(AOR_multiply(intPartA, fracPartB, "org.apache.commons.math3.util.FastMath.exp_874", _mut44625, _mut44626, _mut44627, _mut44628), AOR_multiply(intPartB, fracPartA, "org.apache.commons.math3.util.FastMath.exp_874", _mut44629, _mut44630, _mut44631, _mut44632), "org.apache.commons.math3.util.FastMath.exp_874", _mut44633, _mut44634, _mut44635, _mut44636), AOR_multiply(intPartB, fracPartB, "org.apache.commons.math3.util.FastMath.exp_874", _mut44637, _mut44638, _mut44639, _mut44640), "org.apache.commons.math3.util.FastMath.exp_874", _mut44641, _mut44642, _mut44643, _mut44644);
        /* Compute the result.  (1+z)(tempA+tempB).  Order of operations is
       important.  For accuracy add by increasing size.  tempA is exact and
       much larger than the others.  If there are extra bits specified from the
       pow() function, use them. */
        final double tempC = AOR_plus(tempB, tempA, "org.apache.commons.math3.util.FastMath.exp_874", _mut44645, _mut44646, _mut44647, _mut44648);
        // because z could be negative at the same time.
        if (ROR_equals(tempC, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.exp_874", _mut44649, _mut44650, _mut44651, _mut44652, _mut44653)) {
            return Double.POSITIVE_INFINITY;
        }
        final double result;
        if (ROR_not_equals(extra, 0.0, "org.apache.commons.math3.util.FastMath.exp_874", _mut44654, _mut44655, _mut44656, _mut44657, _mut44658)) {
            result = AOR_plus(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(AOR_multiply(tempC, extra, "org.apache.commons.math3.util.FastMath.exp_874", _mut44671, _mut44672, _mut44673, _mut44674), z, "org.apache.commons.math3.util.FastMath.exp_874", _mut44675, _mut44676, _mut44677, _mut44678), AOR_multiply(tempC, extra, "org.apache.commons.math3.util.FastMath.exp_874", _mut44679, _mut44680, _mut44681, _mut44682), "org.apache.commons.math3.util.FastMath.exp_874", _mut44683, _mut44684, _mut44685, _mut44686), AOR_multiply(tempC, z, "org.apache.commons.math3.util.FastMath.exp_874", _mut44687, _mut44688, _mut44689, _mut44690), "org.apache.commons.math3.util.FastMath.exp_874", _mut44691, _mut44692, _mut44693, _mut44694), tempB, "org.apache.commons.math3.util.FastMath.exp_874", _mut44695, _mut44696, _mut44697, _mut44698), tempA, "org.apache.commons.math3.util.FastMath.exp_874", _mut44699, _mut44700, _mut44701, _mut44702);
        } else {
            result = AOR_plus(AOR_plus(AOR_multiply(tempC, z, "org.apache.commons.math3.util.FastMath.exp_874", _mut44659, _mut44660, _mut44661, _mut44662), tempB, "org.apache.commons.math3.util.FastMath.exp_874", _mut44663, _mut44664, _mut44665, _mut44666), tempA, "org.apache.commons.math3.util.FastMath.exp_874", _mut44667, _mut44668, _mut44669, _mut44670);
        }
        if (hiPrec != null) {
            // If requesting high precision
            hiPrec[0] = tempA;
            hiPrec[1] = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(AOR_multiply(tempC, extra, "org.apache.commons.math3.util.FastMath.exp_874", _mut44703, _mut44704, _mut44705, _mut44706), z, "org.apache.commons.math3.util.FastMath.exp_874", _mut44707, _mut44708, _mut44709, _mut44710), AOR_multiply(tempC, extra, "org.apache.commons.math3.util.FastMath.exp_874", _mut44711, _mut44712, _mut44713, _mut44714), "org.apache.commons.math3.util.FastMath.exp_874", _mut44715, _mut44716, _mut44717, _mut44718), AOR_multiply(tempC, z, "org.apache.commons.math3.util.FastMath.exp_874", _mut44719, _mut44720, _mut44721, _mut44722), "org.apache.commons.math3.util.FastMath.exp_874", _mut44723, _mut44724, _mut44725, _mut44726), tempB, "org.apache.commons.math3.util.FastMath.exp_874", _mut44727, _mut44728, _mut44729, _mut44730);
        }
        return result;
    }

    /**
     * Compute exp(x) - 1
     * @param x number to compute shifted exponential
     * @return exp(x) - 1
     */
    public static double expm1(double x) {
        return expm1(x, null);
    }

    /**
     * Internal helper method for expm1
     * @param x number to compute shifted exponential
     * @param hiPrecOut receive high precision result for -1.0 < x < 1.0
     * @return exp(x) - 1
     */
    private static double expm1(double x, double[] hiPrecOut) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.expm1_1007");
        if ((_mut44741 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44731, _mut44732, _mut44733, _mut44734, _mut44735) && ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44736, _mut44737, _mut44738, _mut44739, _mut44740)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44731, _mut44732, _mut44733, _mut44734, _mut44735) || ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44736, _mut44737, _mut44738, _mut44739, _mut44740)))) {
            // NaN or zero
            return x;
        }
        if ((_mut44752 ? (ROR_less_equals(x, -1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44742, _mut44743, _mut44744, _mut44745, _mut44746) && ROR_greater_equals(x, 1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44747, _mut44748, _mut44749, _mut44750, _mut44751)) : (ROR_less_equals(x, -1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44742, _mut44743, _mut44744, _mut44745, _mut44746) || ROR_greater_equals(x, 1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44747, _mut44748, _mut44749, _mut44750, _mut44751)))) {
            // return exp(x) - 1.0;
            double[] hiPrec = new double[2];
            exp(x, 0.0, hiPrec);
            if (ROR_greater(x, 0.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44753, _mut44754, _mut44755, _mut44756, _mut44757)) {
                return AOR_plus(AOR_plus(-1.0, hiPrec[0], "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44774, _mut44775, _mut44776, _mut44777), hiPrec[1], "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44778, _mut44779, _mut44780, _mut44781);
            } else {
                final double ra = AOR_plus(-1.0, hiPrec[0], "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44758, _mut44759, _mut44760, _mut44761);
                double rb = -(AOR_minus(AOR_plus(ra, 1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44762, _mut44763, _mut44764, _mut44765), hiPrec[0], "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44766, _mut44767, _mut44768, _mut44769));
                rb += hiPrec[1];
                return AOR_plus(ra, rb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44770, _mut44771, _mut44772, _mut44773);
            }
        }
        double baseA;
        double baseB;
        double epsilon;
        boolean negative = false;
        if (ROR_less(x, 0.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44782, _mut44783, _mut44784, _mut44785, _mut44786)) {
            x = -x;
            negative = true;
        }
        {
            int intFrac = (int) (AOR_multiply(x, 1024.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44787, _mut44788, _mut44789, _mut44790));
            double tempA = AOR_minus(ExpFracTable.EXP_FRAC_TABLE_A[intFrac], 1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44791, _mut44792, _mut44793, _mut44794);
            double tempB = ExpFracTable.EXP_FRAC_TABLE_B[intFrac];
            double temp = AOR_plus(tempA, tempB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44795, _mut44796, _mut44797, _mut44798);
            tempB = -(AOR_minus(AOR_minus(temp, tempA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44799, _mut44800, _mut44801, _mut44802), tempB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44803, _mut44804, _mut44805, _mut44806));
            tempA = temp;
            temp = AOR_multiply(tempA, HEX_40000000, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44807, _mut44808, _mut44809, _mut44810);
            baseA = AOR_minus(AOR_plus(tempA, temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44811, _mut44812, _mut44813, _mut44814), temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44815, _mut44816, _mut44817, _mut44818);
            baseB = AOR_plus(tempB, (AOR_minus(tempA, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44819, _mut44820, _mut44821, _mut44822)), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44823, _mut44824, _mut44825, _mut44826);
            epsilon = AOR_minus(x, AOR_divide(intFrac, 1024.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44827, _mut44828, _mut44829, _mut44830), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44831, _mut44832, _mut44833, _mut44834);
        }
        /* Compute expm1(epsilon) */
        double zb = 0.008336750013465571;
        zb = AOR_plus(AOR_multiply(zb, epsilon, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44835, _mut44836, _mut44837, _mut44838), 0.041666663879186654, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44839, _mut44840, _mut44841, _mut44842);
        zb = AOR_plus(AOR_multiply(zb, epsilon, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44843, _mut44844, _mut44845, _mut44846), 0.16666666666745392, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44847, _mut44848, _mut44849, _mut44850);
        zb = AOR_plus(AOR_multiply(zb, epsilon, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44851, _mut44852, _mut44853, _mut44854), 0.49999999999999994, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44855, _mut44856, _mut44857, _mut44858);
        zb *= epsilon;
        zb *= epsilon;
        double za = epsilon;
        double temp = AOR_plus(za, zb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44859, _mut44860, _mut44861, _mut44862);
        zb = -(AOR_minus(AOR_minus(temp, za, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44863, _mut44864, _mut44865, _mut44866), zb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44867, _mut44868, _mut44869, _mut44870));
        za = temp;
        temp = AOR_multiply(za, HEX_40000000, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44871, _mut44872, _mut44873, _mut44874);
        temp = AOR_minus(AOR_plus(za, temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44875, _mut44876, _mut44877, _mut44878), temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44879, _mut44880, _mut44881, _mut44882);
        zb += AOR_minus(za, temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44883, _mut44884, _mut44885, _mut44886);
        za = temp;
        /* Combine the parts.   expm1(a+b) = expm1(a) + expm1(b) + expm1(a)*expm1(b) */
        double ya = AOR_multiply(za, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44887, _mut44888, _mut44889, _mut44890);
        // double yb = za*baseB + zb*baseA + zb*baseB;
        temp = AOR_plus(ya, AOR_multiply(za, baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44891, _mut44892, _mut44893, _mut44894), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44895, _mut44896, _mut44897, _mut44898);
        double yb = -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44899, _mut44900, _mut44901, _mut44902), AOR_multiply(za, baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44903, _mut44904, _mut44905, _mut44906), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44907, _mut44908, _mut44909, _mut44910));
        ya = temp;
        temp = AOR_plus(ya, AOR_multiply(zb, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44911, _mut44912, _mut44913, _mut44914), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44915, _mut44916, _mut44917, _mut44918);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44919, _mut44920, _mut44921, _mut44922), AOR_multiply(zb, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44923, _mut44924, _mut44925, _mut44926), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44927, _mut44928, _mut44929, _mut44930));
        ya = temp;
        temp = AOR_plus(ya, AOR_multiply(zb, baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44931, _mut44932, _mut44933, _mut44934), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44935, _mut44936, _mut44937, _mut44938);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44939, _mut44940, _mut44941, _mut44942), AOR_multiply(zb, baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44943, _mut44944, _mut44945, _mut44946), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44947, _mut44948, _mut44949, _mut44950));
        ya = temp;
        // yb = yb + zb + baseB;
        temp = AOR_plus(ya, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44951, _mut44952, _mut44953, _mut44954);
        yb += -(AOR_minus(AOR_minus(temp, baseA, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44955, _mut44956, _mut44957, _mut44958), ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44959, _mut44960, _mut44961, _mut44962));
        ya = temp;
        temp = AOR_plus(ya, za, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44963, _mut44964, _mut44965, _mut44966);
        // yb += (ya > za) ? -(temp - ya - za) : -(temp - za - ya);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44967, _mut44968, _mut44969, _mut44970), za, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44971, _mut44972, _mut44973, _mut44974));
        ya = temp;
        temp = AOR_plus(ya, baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44975, _mut44976, _mut44977, _mut44978);
        // yb += (ya > baseB) ? -(temp - ya - baseB) : -(temp - baseB - ya);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44979, _mut44980, _mut44981, _mut44982), baseB, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44983, _mut44984, _mut44985, _mut44986));
        ya = temp;
        temp = AOR_plus(ya, zb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44987, _mut44988, _mut44989, _mut44990);
        // yb += (ya > zb) ? -(temp - ya - zb) : -(temp - zb - ya);
        yb += -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44991, _mut44992, _mut44993, _mut44994), zb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44995, _mut44996, _mut44997, _mut44998));
        ya = temp;
        if (negative) {
            /* Compute expm1(-x) = -expm1(x) / (expm1(x) + 1) */
            double denom = AOR_plus(1.0, ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut44999, _mut45000, _mut45001, _mut45002);
            double denomr = AOR_divide(1.0, denom, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45003, _mut45004, _mut45005, _mut45006);
            double denomb = AOR_plus(-(AOR_minus(AOR_minus(denom, 1.0, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45007, _mut45008, _mut45009, _mut45010), ya, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45011, _mut45012, _mut45013, _mut45014)), yb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45015, _mut45016, _mut45017, _mut45018);
            double ratio = AOR_multiply(ya, denomr, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45019, _mut45020, _mut45021, _mut45022);
            temp = AOR_multiply(ratio, HEX_40000000, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45023, _mut45024, _mut45025, _mut45026);
            final double ra = AOR_minus(AOR_plus(ratio, temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45027, _mut45028, _mut45029, _mut45030), temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45031, _mut45032, _mut45033, _mut45034);
            double rb = AOR_minus(ratio, ra, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45035, _mut45036, _mut45037, _mut45038);
            temp = AOR_multiply(denom, HEX_40000000, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45039, _mut45040, _mut45041, _mut45042);
            za = AOR_minus(AOR_plus(denom, temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45043, _mut45044, _mut45045, _mut45046), temp, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45047, _mut45048, _mut45049, _mut45050);
            zb = AOR_minus(denom, za, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45051, _mut45052, _mut45053, _mut45054);
            rb += AOR_multiply((AOR_minus(AOR_minus(AOR_minus(AOR_minus(ya, AOR_multiply(za, ra, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45055, _mut45056, _mut45057, _mut45058), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45059, _mut45060, _mut45061, _mut45062), AOR_multiply(za, rb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45063, _mut45064, _mut45065, _mut45066), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45067, _mut45068, _mut45069, _mut45070), AOR_multiply(zb, ra, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45071, _mut45072, _mut45073, _mut45074), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45075, _mut45076, _mut45077, _mut45078), AOR_multiply(zb, rb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45079, _mut45080, _mut45081, _mut45082), "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45083, _mut45084, _mut45085, _mut45086)), denomr, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45087, _mut45088, _mut45089, _mut45090);
            // numerator
            rb += AOR_multiply(yb, denomr, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45091, _mut45092, _mut45093, _mut45094);
            // denominator
            rb += AOR_multiply(AOR_multiply(AOR_multiply(-ya, denomb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45095, _mut45096, _mut45097, _mut45098), denomr, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45099, _mut45100, _mut45101, _mut45102), denomr, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45103, _mut45104, _mut45105, _mut45106);
            // negate
            ya = -ra;
            yb = -rb;
        }
        if (hiPrecOut != null) {
            hiPrecOut[0] = ya;
            hiPrecOut[1] = yb;
        }
        return AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.expm1_1007", _mut45107, _mut45108, _mut45109, _mut45110);
    }

    /**
     * Natural logarithm.
     *
     * @param x   a double
     * @return log(x)
     */
    public static double log(final double x) {
        return log(x, null);
    }

    /**
     * Internal helper method for natural logarithm function.
     * @param x original argument of the natural logarithm function
     * @param hiPrec extra bits of precision on output (To Be Confirmed)
     * @return log(x)
     */
    private static double log(final double x, final double[] hiPrec) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log_1165");
        if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45111, _mut45112, _mut45113, _mut45114, _mut45115)) {
            // Handle special case of +0/-0
            return Double.NEGATIVE_INFINITY;
        }
        long bits = Double.doubleToRawLongBits(x);
        /* Handle special cases of negative input, and NaN */
        if ((_mut45132 ? (((_mut45126 ? (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45116, _mut45117, _mut45118, _mut45119, _mut45120) && ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.log_1165", _mut45121, _mut45122, _mut45123, _mut45124, _mut45125)) : (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45116, _mut45117, _mut45118, _mut45119, _mut45120) || ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.log_1165", _mut45121, _mut45122, _mut45123, _mut45124, _mut45125)))) || ROR_not_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45127, _mut45128, _mut45129, _mut45130, _mut45131)) : (((_mut45126 ? (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45116, _mut45117, _mut45118, _mut45119, _mut45120) && ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.log_1165", _mut45121, _mut45122, _mut45123, _mut45124, _mut45125)) : (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45116, _mut45117, _mut45118, _mut45119, _mut45120) || ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.log_1165", _mut45121, _mut45122, _mut45123, _mut45124, _mut45125)))) && ROR_not_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45127, _mut45128, _mut45129, _mut45130, _mut45131)))) {
            if (hiPrec != null) {
                hiPrec[0] = Double.NaN;
            }
            return Double.NaN;
        }
        /* Handle special cases of Positive infinity. */
        if (ROR_equals(x, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.log_1165", _mut45133, _mut45134, _mut45135, _mut45136, _mut45137)) {
            if (hiPrec != null) {
                hiPrec[0] = Double.POSITIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        /* Extract the exponent */
        int exp = AOR_minus((int) (bits >> 52), 1023, "org.apache.commons.math3.util.FastMath.log_1165", _mut45138, _mut45139, _mut45140, _mut45141);
        if (ROR_equals((bits & 0x7ff0000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45142, _mut45143, _mut45144, _mut45145, _mut45146)) {
            // Subnormal!
            if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45147, _mut45148, _mut45149, _mut45150, _mut45151)) {
                // Zero
                if (hiPrec != null) {
                    hiPrec[0] = Double.NEGATIVE_INFINITY;
                }
                return Double.NEGATIVE_INFINITY;
            }
            /* Normalize the subnormal number. */
            bits <<= 1;
            while (ROR_equals((bits & 0x0010000000000000L), 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45152, _mut45153, _mut45154, _mut45155, _mut45156)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log_1165");
                --exp;
                bits <<= 1;
            }
        }
        if ((_mut45180 ? ((_mut45179 ? ((_mut45173 ? (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) || ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172)) : (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) && ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172))) || ROR_greater(x, 0.99, "org.apache.commons.math3.util.FastMath.log_1165", _mut45174, _mut45175, _mut45176, _mut45177, _mut45178)) : ((_mut45173 ? (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) || ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172)) : (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) && ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172))) && ROR_greater(x, 0.99, "org.apache.commons.math3.util.FastMath.log_1165", _mut45174, _mut45175, _mut45176, _mut45177, _mut45178))) || hiPrec == null) : ((_mut45179 ? ((_mut45173 ? (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) || ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172)) : (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) && ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172))) || ROR_greater(x, 0.99, "org.apache.commons.math3.util.FastMath.log_1165", _mut45174, _mut45175, _mut45176, _mut45177, _mut45178)) : ((_mut45173 ? (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) || ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172)) : (((_mut45167 ? (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) && ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)) : (ROR_equals(exp, -1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45157, _mut45158, _mut45159, _mut45160, _mut45161) || ROR_equals(exp, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45162, _mut45163, _mut45164, _mut45165, _mut45166)))) && ROR_less(x, 1.01, "org.apache.commons.math3.util.FastMath.log_1165", _mut45168, _mut45169, _mut45170, _mut45171, _mut45172))) && ROR_greater(x, 0.99, "org.apache.commons.math3.util.FastMath.log_1165", _mut45174, _mut45175, _mut45176, _mut45177, _mut45178))) && hiPrec == null))) {
            /* Compute x - 1.0 and split it */
            double xa = AOR_minus(x, 1.0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45181, _mut45182, _mut45183, _mut45184);
            double xb = AOR_plus(AOR_minus(xa, x, "org.apache.commons.math3.util.FastMath.log_1165", _mut45185, _mut45186, _mut45187, _mut45188), 1.0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45189, _mut45190, _mut45191, _mut45192);
            double tmp = AOR_multiply(xa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45193, _mut45194, _mut45195, _mut45196);
            double aa = AOR_minus(AOR_plus(xa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45197, _mut45198, _mut45199, _mut45200), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45201, _mut45202, _mut45203, _mut45204);
            double ab = AOR_minus(xa, aa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45205, _mut45206, _mut45207, _mut45208);
            xa = aa;
            xb = ab;
            final double[] lnCoef_last = LN_QUICK_COEF[AOR_minus(LN_QUICK_COEF.length, 1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45209, _mut45210, _mut45211, _mut45212)];
            double ya = lnCoef_last[0];
            double yb = lnCoef_last[1];
            for (int i = LN_QUICK_COEF.length - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45285, _mut45286, _mut45287, _mut45288, _mut45289); i--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log_1165");
                /* Multiply a = y * x */
                aa = AOR_multiply(ya, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45213, _mut45214, _mut45215, _mut45216);
                ab = AOR_plus(AOR_plus(AOR_multiply(ya, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45217, _mut45218, _mut45219, _mut45220), AOR_multiply(yb, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45221, _mut45222, _mut45223, _mut45224), "org.apache.commons.math3.util.FastMath.log_1165", _mut45225, _mut45226, _mut45227, _mut45228), AOR_multiply(yb, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45229, _mut45230, _mut45231, _mut45232), "org.apache.commons.math3.util.FastMath.log_1165", _mut45233, _mut45234, _mut45235, _mut45236);
                /* split, so now y = a */
                tmp = AOR_multiply(aa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45237, _mut45238, _mut45239, _mut45240);
                ya = AOR_minus(AOR_plus(aa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45241, _mut45242, _mut45243, _mut45244), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45245, _mut45246, _mut45247, _mut45248);
                yb = AOR_plus(AOR_minus(aa, ya, "org.apache.commons.math3.util.FastMath.log_1165", _mut45249, _mut45250, _mut45251, _mut45252), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45253, _mut45254, _mut45255, _mut45256);
                /* Add  a = y + lnQuickCoef */
                final double[] lnCoef_i = LN_QUICK_COEF[i];
                aa = AOR_plus(ya, lnCoef_i[0], "org.apache.commons.math3.util.FastMath.log_1165", _mut45257, _mut45258, _mut45259, _mut45260);
                ab = AOR_plus(yb, lnCoef_i[1], "org.apache.commons.math3.util.FastMath.log_1165", _mut45261, _mut45262, _mut45263, _mut45264);
                /* Split y = a */
                tmp = AOR_multiply(aa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45265, _mut45266, _mut45267, _mut45268);
                ya = AOR_minus(AOR_plus(aa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45269, _mut45270, _mut45271, _mut45272), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45273, _mut45274, _mut45275, _mut45276);
                yb = AOR_plus(AOR_minus(aa, ya, "org.apache.commons.math3.util.FastMath.log_1165", _mut45277, _mut45278, _mut45279, _mut45280), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45281, _mut45282, _mut45283, _mut45284);
            }
            /* Multiply a = y * x */
            aa = AOR_multiply(ya, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45290, _mut45291, _mut45292, _mut45293);
            ab = AOR_plus(AOR_plus(AOR_multiply(ya, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45294, _mut45295, _mut45296, _mut45297), AOR_multiply(yb, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45298, _mut45299, _mut45300, _mut45301), "org.apache.commons.math3.util.FastMath.log_1165", _mut45302, _mut45303, _mut45304, _mut45305), AOR_multiply(yb, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45306, _mut45307, _mut45308, _mut45309), "org.apache.commons.math3.util.FastMath.log_1165", _mut45310, _mut45311, _mut45312, _mut45313);
            /* split, so now y = a */
            tmp = AOR_multiply(aa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45314, _mut45315, _mut45316, _mut45317);
            ya = AOR_minus(AOR_plus(aa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45318, _mut45319, _mut45320, _mut45321), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45322, _mut45323, _mut45324, _mut45325);
            yb = AOR_plus(AOR_minus(aa, ya, "org.apache.commons.math3.util.FastMath.log_1165", _mut45326, _mut45327, _mut45328, _mut45329), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45330, _mut45331, _mut45332, _mut45333);
            return AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45334, _mut45335, _mut45336, _mut45337);
        }
        // lnm is a log of a number in the range of 1.0 - 2.0, so 0 <= lnm < ln(2)
        final double[] lnm = lnMant.LN_MANT[(int) ((bits & 0x000ffc0000000000L) >> 42)];
        // double epsilon = (x - y) / y;
        final double epsilon = AOR_divide((bits & 0x3ffffffffffL), (AOR_plus(TWO_POWER_52, (bits & 0x000ffc0000000000L), "org.apache.commons.math3.util.FastMath.log_1165", _mut45338, _mut45339, _mut45340, _mut45341)), "org.apache.commons.math3.util.FastMath.log_1165", _mut45342, _mut45343, _mut45344, _mut45345);
        double lnza = 0.0;
        double lnzb = 0.0;
        if (hiPrec != null) {
            /* split epsilon -> x */
            double tmp = AOR_multiply(epsilon, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45386, _mut45387, _mut45388, _mut45389);
            double aa = AOR_minus(AOR_plus(epsilon, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45390, _mut45391, _mut45392, _mut45393), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45394, _mut45395, _mut45396, _mut45397);
            double ab = AOR_minus(epsilon, aa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45398, _mut45399, _mut45400, _mut45401);
            double xa = aa;
            double xb = ab;
            /* Need a more accurate epsilon, so adjust the division. */
            final double numer = bits & 0x3ffffffffffL;
            final double denom = AOR_plus(TWO_POWER_52, (bits & 0x000ffc0000000000L), "org.apache.commons.math3.util.FastMath.log_1165", _mut45402, _mut45403, _mut45404, _mut45405);
            aa = AOR_minus(AOR_minus(numer, AOR_multiply(xa, denom, "org.apache.commons.math3.util.FastMath.log_1165", _mut45406, _mut45407, _mut45408, _mut45409), "org.apache.commons.math3.util.FastMath.log_1165", _mut45410, _mut45411, _mut45412, _mut45413), AOR_multiply(xb, denom, "org.apache.commons.math3.util.FastMath.log_1165", _mut45414, _mut45415, _mut45416, _mut45417), "org.apache.commons.math3.util.FastMath.log_1165", _mut45418, _mut45419, _mut45420, _mut45421);
            xb += AOR_divide(aa, denom, "org.apache.commons.math3.util.FastMath.log_1165", _mut45422, _mut45423, _mut45424, _mut45425);
            /* Remez polynomial evaluation */
            final double[] lnCoef_last = LN_HI_PREC_COEF[AOR_minus(LN_HI_PREC_COEF.length, 1, "org.apache.commons.math3.util.FastMath.log_1165", _mut45426, _mut45427, _mut45428, _mut45429)];
            double ya = lnCoef_last[0];
            double yb = lnCoef_last[1];
            for (int i = LN_HI_PREC_COEF.length - 2; ROR_greater_equals(i, 0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45502, _mut45503, _mut45504, _mut45505, _mut45506); i--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log_1165");
                /* Multiply a = y * x */
                aa = AOR_multiply(ya, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45430, _mut45431, _mut45432, _mut45433);
                ab = AOR_plus(AOR_plus(AOR_multiply(ya, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45434, _mut45435, _mut45436, _mut45437), AOR_multiply(yb, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45438, _mut45439, _mut45440, _mut45441), "org.apache.commons.math3.util.FastMath.log_1165", _mut45442, _mut45443, _mut45444, _mut45445), AOR_multiply(yb, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45446, _mut45447, _mut45448, _mut45449), "org.apache.commons.math3.util.FastMath.log_1165", _mut45450, _mut45451, _mut45452, _mut45453);
                /* split, so now y = a */
                tmp = AOR_multiply(aa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45454, _mut45455, _mut45456, _mut45457);
                ya = AOR_minus(AOR_plus(aa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45458, _mut45459, _mut45460, _mut45461), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45462, _mut45463, _mut45464, _mut45465);
                yb = AOR_plus(AOR_minus(aa, ya, "org.apache.commons.math3.util.FastMath.log_1165", _mut45466, _mut45467, _mut45468, _mut45469), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45470, _mut45471, _mut45472, _mut45473);
                /* Add  a = y + lnHiPrecCoef */
                final double[] lnCoef_i = LN_HI_PREC_COEF[i];
                aa = AOR_plus(ya, lnCoef_i[0], "org.apache.commons.math3.util.FastMath.log_1165", _mut45474, _mut45475, _mut45476, _mut45477);
                ab = AOR_plus(yb, lnCoef_i[1], "org.apache.commons.math3.util.FastMath.log_1165", _mut45478, _mut45479, _mut45480, _mut45481);
                /* Split y = a */
                tmp = AOR_multiply(aa, HEX_40000000, "org.apache.commons.math3.util.FastMath.log_1165", _mut45482, _mut45483, _mut45484, _mut45485);
                ya = AOR_minus(AOR_plus(aa, tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45486, _mut45487, _mut45488, _mut45489), tmp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45490, _mut45491, _mut45492, _mut45493);
                yb = AOR_plus(AOR_minus(aa, ya, "org.apache.commons.math3.util.FastMath.log_1165", _mut45494, _mut45495, _mut45496, _mut45497), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45498, _mut45499, _mut45500, _mut45501);
            }
            /* Multiply a = y * x */
            aa = AOR_multiply(ya, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45507, _mut45508, _mut45509, _mut45510);
            ab = AOR_plus(AOR_plus(AOR_multiply(ya, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45511, _mut45512, _mut45513, _mut45514), AOR_multiply(yb, xa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45515, _mut45516, _mut45517, _mut45518), "org.apache.commons.math3.util.FastMath.log_1165", _mut45519, _mut45520, _mut45521, _mut45522), AOR_multiply(yb, xb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45523, _mut45524, _mut45525, _mut45526), "org.apache.commons.math3.util.FastMath.log_1165", _mut45527, _mut45528, _mut45529, _mut45530);
            /*
      tmp = aa * 1073741824.0;
      lnza = aa + tmp - tmp;
      lnzb = aa - lnza + ab;
             */
            lnza = AOR_plus(aa, ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45531, _mut45532, _mut45533, _mut45534);
            lnzb = -(AOR_minus(AOR_minus(lnza, aa, "org.apache.commons.math3.util.FastMath.log_1165", _mut45535, _mut45536, _mut45537, _mut45538), ab, "org.apache.commons.math3.util.FastMath.log_1165", _mut45539, _mut45540, _mut45541, _mut45542));
        } else {
            /* High precision not required.  Eval Remez polynomial
         using standard double precision */
            lnza = -0.16624882440418567;
            lnza = AOR_plus(AOR_multiply(lnza, epsilon, "org.apache.commons.math3.util.FastMath.log_1165", _mut45346, _mut45347, _mut45348, _mut45349), 0.19999954120254515, "org.apache.commons.math3.util.FastMath.log_1165", _mut45350, _mut45351, _mut45352, _mut45353);
            lnza = AOR_plus(AOR_multiply(lnza, epsilon, "org.apache.commons.math3.util.FastMath.log_1165", _mut45354, _mut45355, _mut45356, _mut45357), -0.2499999997677497, "org.apache.commons.math3.util.FastMath.log_1165", _mut45358, _mut45359, _mut45360, _mut45361);
            lnza = AOR_plus(AOR_multiply(lnza, epsilon, "org.apache.commons.math3.util.FastMath.log_1165", _mut45362, _mut45363, _mut45364, _mut45365), 0.3333333333332802, "org.apache.commons.math3.util.FastMath.log_1165", _mut45366, _mut45367, _mut45368, _mut45369);
            lnza = AOR_plus(AOR_multiply(lnza, epsilon, "org.apache.commons.math3.util.FastMath.log_1165", _mut45370, _mut45371, _mut45372, _mut45373), -0.5, "org.apache.commons.math3.util.FastMath.log_1165", _mut45374, _mut45375, _mut45376, _mut45377);
            lnza = AOR_plus(AOR_multiply(lnza, epsilon, "org.apache.commons.math3.util.FastMath.log_1165", _mut45378, _mut45379, _mut45380, _mut45381), 1.0, "org.apache.commons.math3.util.FastMath.log_1165", _mut45382, _mut45383, _mut45384, _mut45385);
            lnza *= epsilon;
        }
        // return lnzb + lnm[1] + ln2B*exp + lnza + lnm[0] + ln2A*exp;
        double a = AOR_multiply(LN_2_A, exp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45543, _mut45544, _mut45545, _mut45546);
        double b = 0.0;
        double c = AOR_plus(a, lnm[0], "org.apache.commons.math3.util.FastMath.log_1165", _mut45547, _mut45548, _mut45549, _mut45550);
        double d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.log_1165", _mut45551, _mut45552, _mut45553, _mut45554), lnm[0], "org.apache.commons.math3.util.FastMath.log_1165", _mut45555, _mut45556, _mut45557, _mut45558));
        a = c;
        b += d;
        c = AOR_plus(a, lnza, "org.apache.commons.math3.util.FastMath.log_1165", _mut45559, _mut45560, _mut45561, _mut45562);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.log_1165", _mut45563, _mut45564, _mut45565, _mut45566), lnza, "org.apache.commons.math3.util.FastMath.log_1165", _mut45567, _mut45568, _mut45569, _mut45570));
        a = c;
        b += d;
        c = AOR_plus(a, AOR_multiply(LN_2_B, exp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45571, _mut45572, _mut45573, _mut45574), "org.apache.commons.math3.util.FastMath.log_1165", _mut45575, _mut45576, _mut45577, _mut45578);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.log_1165", _mut45579, _mut45580, _mut45581, _mut45582), AOR_multiply(LN_2_B, exp, "org.apache.commons.math3.util.FastMath.log_1165", _mut45583, _mut45584, _mut45585, _mut45586), "org.apache.commons.math3.util.FastMath.log_1165", _mut45587, _mut45588, _mut45589, _mut45590));
        a = c;
        b += d;
        c = AOR_plus(a, lnm[1], "org.apache.commons.math3.util.FastMath.log_1165", _mut45591, _mut45592, _mut45593, _mut45594);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.log_1165", _mut45595, _mut45596, _mut45597, _mut45598), lnm[1], "org.apache.commons.math3.util.FastMath.log_1165", _mut45599, _mut45600, _mut45601, _mut45602));
        a = c;
        b += d;
        c = AOR_plus(a, lnzb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45603, _mut45604, _mut45605, _mut45606);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.log_1165", _mut45607, _mut45608, _mut45609, _mut45610), lnzb, "org.apache.commons.math3.util.FastMath.log_1165", _mut45611, _mut45612, _mut45613, _mut45614));
        a = c;
        b += d;
        if (hiPrec != null) {
            hiPrec[0] = a;
            hiPrec[1] = b;
        }
        return AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.log_1165", _mut45615, _mut45616, _mut45617, _mut45618);
    }

    /**
     * Computes log(1 + x).
     *
     * @param x Number.
     * @return {@code log(1 + x)}.
     */
    public static double log1p(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log1p_1393");
        if (ROR_equals(x, -1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45619, _mut45620, _mut45621, _mut45622, _mut45623)) {
            return Double.NEGATIVE_INFINITY;
        }
        if (ROR_equals(x, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45624, _mut45625, _mut45626, _mut45627, _mut45628)) {
            return Double.POSITIVE_INFINITY;
        }
        if ((_mut45639 ? (ROR_greater(x, 1e-6, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45629, _mut45630, _mut45631, _mut45632, _mut45633) && ROR_less(x, -1e-6, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45634, _mut45635, _mut45636, _mut45637, _mut45638)) : (ROR_greater(x, 1e-6, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45629, _mut45630, _mut45631, _mut45632, _mut45633) || ROR_less(x, -1e-6, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45634, _mut45635, _mut45636, _mut45637, _mut45638)))) {
            final double xpa = AOR_plus(1, x, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45660, _mut45661, _mut45662, _mut45663);
            final double xpb = -(AOR_minus(AOR_minus(xpa, 1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45664, _mut45665, _mut45666, _mut45667), x, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45668, _mut45669, _mut45670, _mut45671));
            final double[] hiPrec = new double[2];
            final double lores = log(xpa, hiPrec);
            if (Double.isInfinite(lores)) {
                // Don't allow this to be converted to NaN
                return lores;
            }
            // f(x+y) = f(x) + f'(x) y + f''(x)/2 y^2
            final double fx1 = AOR_divide(xpb, xpa, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45672, _mut45673, _mut45674, _mut45675);
            final double epsilon = AOR_plus(AOR_multiply(0.5, fx1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45676, _mut45677, _mut45678, _mut45679), 1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45680, _mut45681, _mut45682, _mut45683);
            return AOR_plus(AOR_plus(AOR_multiply(epsilon, fx1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45684, _mut45685, _mut45686, _mut45687), hiPrec[1], "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45688, _mut45689, _mut45690, _mut45691), hiPrec[0], "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45692, _mut45693, _mut45694, _mut45695);
        } else {
            // Value is small |x| < 1e6, do a Taylor series centered on 1.
            final double y = AOR_plus(AOR_multiply((AOR_minus(AOR_multiply(x, F_1_3, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45640, _mut45641, _mut45642, _mut45643), F_1_2, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45644, _mut45645, _mut45646, _mut45647)), x, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45648, _mut45649, _mut45650, _mut45651), 1, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45652, _mut45653, _mut45654, _mut45655);
            return AOR_multiply(y, x, "org.apache.commons.math3.util.FastMath.log1p_1393", _mut45656, _mut45657, _mut45658, _mut45659);
        }
    }

    /**
     * Compute the base 10 logarithm.
     * @param x a number
     * @return log10(x)
     */
    public static double log10(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log10_1429");
        final double[] hiPrec = new double[2];
        final double lores = log(x, hiPrec);
        if (Double.isInfinite(lores)) {
            // don't allow this to be converted to NaN
            return lores;
        }
        final double tmp = AOR_multiply(hiPrec[0], HEX_40000000, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45696, _mut45697, _mut45698, _mut45699);
        final double lna = AOR_minus(AOR_plus(hiPrec[0], tmp, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45700, _mut45701, _mut45702, _mut45703), tmp, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45704, _mut45705, _mut45706, _mut45707);
        final double lnb = AOR_plus(AOR_minus(hiPrec[0], lna, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45708, _mut45709, _mut45710, _mut45711), hiPrec[1], "org.apache.commons.math3.util.FastMath.log10_1429", _mut45712, _mut45713, _mut45714, _mut45715);
        final double rln10a = 0.4342944622039795;
        final double rln10b = 1.9699272335463627E-8;
        return AOR_plus(AOR_plus(AOR_plus(AOR_multiply(rln10b, lnb, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45716, _mut45717, _mut45718, _mut45719), AOR_multiply(rln10b, lna, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45720, _mut45721, _mut45722, _mut45723), "org.apache.commons.math3.util.FastMath.log10_1429", _mut45724, _mut45725, _mut45726, _mut45727), AOR_multiply(rln10a, lnb, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45728, _mut45729, _mut45730, _mut45731), "org.apache.commons.math3.util.FastMath.log10_1429", _mut45732, _mut45733, _mut45734, _mut45735), AOR_multiply(rln10a, lna, "org.apache.commons.math3.util.FastMath.log10_1429", _mut45736, _mut45737, _mut45738, _mut45739), "org.apache.commons.math3.util.FastMath.log10_1429", _mut45740, _mut45741, _mut45742, _mut45743);
    }

    /**
     * Computes the <a href="http://mathworld.wolfram.com/Logarithm.html">
     * logarithm</a> in a given base.
     *
     * Returns {@code NaN} if either argument is negative.
     * If {@code base} is 0 and {@code x} is positive, 0 is returned.
     * If {@code base} is positive and {@code x} is 0,
     * {@code Double.NEGATIVE_INFINITY} is returned.
     * If both arguments are 0, the result is {@code NaN}.
     *
     * @param base Base of the logarithm, must be greater than 0.
     * @param x Argument, must be greater than 0.
     * @return the value of the logarithm, i.e. the number {@code y} such that
     * <code>base<sup>y</sup> = x</code>.
     * @since 1.2 (previously in {@code MathUtils}, moved as of version 3.0)
     */
    public static double log(double base, double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.log_1463");
        return AOR_divide(log(x), log(base), "org.apache.commons.math3.util.FastMath.log_1463", _mut45744, _mut45745, _mut45746, _mut45747);
    }

    /**
     * Power function.  Compute x^y.
     *
     * @param x   a double
     * @param y   a double
     * @return double
     */
    public static double pow(final double x, final double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.pow_1474");
        if (ROR_equals(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45748, _mut45749, _mut45750, _mut45751, _mut45752)) {
            // y = -0 or y = +0
            return 1.0;
        } else {
            final long yBits = Double.doubleToRawLongBits(y);
            final int yRawExp = (int) ((yBits & MASK_DOUBLE_EXPONENT) >> 52);
            final long yRawMantissa = yBits & MASK_DOUBLE_MANTISSA;
            final long xBits = Double.doubleToRawLongBits(x);
            final int xRawExp = (int) ((xBits & MASK_DOUBLE_EXPONENT) >> 52);
            final long xRawMantissa = xBits & MASK_DOUBLE_MANTISSA;
            if (ROR_greater(yRawExp, 1085, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45753, _mut45754, _mut45755, _mut45756, _mut45757)) {
                if ((_mut45959 ? (((_mut45947 ? (ROR_equals(yRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45937, _mut45938, _mut45939, _mut45940, _mut45941) || ROR_not_equals(yRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45942, _mut45943, _mut45944, _mut45945, _mut45946)) : (ROR_equals(yRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45937, _mut45938, _mut45939, _mut45940, _mut45941) && ROR_not_equals(yRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45942, _mut45943, _mut45944, _mut45945, _mut45946)))) && ((_mut45958 ? (ROR_equals(xRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45948, _mut45949, _mut45950, _mut45951, _mut45952) || ROR_not_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45953, _mut45954, _mut45955, _mut45956, _mut45957)) : (ROR_equals(xRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45948, _mut45949, _mut45950, _mut45951, _mut45952) && ROR_not_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45953, _mut45954, _mut45955, _mut45956, _mut45957))))) : (((_mut45947 ? (ROR_equals(yRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45937, _mut45938, _mut45939, _mut45940, _mut45941) || ROR_not_equals(yRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45942, _mut45943, _mut45944, _mut45945, _mut45946)) : (ROR_equals(yRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45937, _mut45938, _mut45939, _mut45940, _mut45941) && ROR_not_equals(yRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45942, _mut45943, _mut45944, _mut45945, _mut45946)))) || ((_mut45958 ? (ROR_equals(xRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45948, _mut45949, _mut45950, _mut45951, _mut45952) || ROR_not_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45953, _mut45954, _mut45955, _mut45956, _mut45957)) : (ROR_equals(xRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45948, _mut45949, _mut45950, _mut45951, _mut45952) && ROR_not_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45953, _mut45954, _mut45955, _mut45956, _mut45957))))))) {
                    // NaN
                    return Double.NaN;
                } else if ((_mut45970 ? (ROR_equals(xRawExp, 1023, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45960, _mut45961, _mut45962, _mut45963, _mut45964) || ROR_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45965, _mut45966, _mut45967, _mut45968, _mut45969)) : (ROR_equals(xRawExp, 1023, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45960, _mut45961, _mut45962, _mut45963, _mut45964) && ROR_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45965, _mut45966, _mut45967, _mut45968, _mut45969)))) {
                    // x = -1.0 or x = +1.0
                    if (ROR_equals(yRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45981, _mut45982, _mut45983, _mut45984, _mut45985)) {
                        // y is infinite
                        return Double.NaN;
                    } else {
                        // y is a large even integer
                        return 1.0;
                    }
                } else {
                    // accuracy, at this magnitude it behaves just like infinity with regards to x
                    if ((ROR_greater(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45971, _mut45972, _mut45973, _mut45974, _mut45975)) ^ (ROR_less(xRawExp, 1023, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45976, _mut45977, _mut45978, _mut45979, _mut45980))) {
                        // or     y = -infinity (or large engouh) and abs(x) < 1.0
                        return Double.POSITIVE_INFINITY;
                    } else {
                        // or     y = -infinity (or large engouh) and abs(x) > 1.0
                        return +0.0;
                    }
                }
            } else {
                if (ROR_greater_equals(yRawExp, 1023, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45758, _mut45759, _mut45760, _mut45761, _mut45762)) {
                    // y may be an integral value, which should be handled specifically
                    final long yFullMantissa = IMPLICIT_HIGH_BIT | yRawMantissa;
                    if (ROR_less(yRawExp, 1075, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45763, _mut45764, _mut45765, _mut45766, _mut45767)) {
                        // normal number with negative shift that may have a fractional part
                        final long integralMask = (-1L) << (AOR_minus(1075, yRawExp, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45777, _mut45778, _mut45779, _mut45780));
                        if (ROR_equals((yFullMantissa & integralMask), yFullMantissa, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45781, _mut45782, _mut45783, _mut45784, _mut45785)) {
                            // all fractional bits are 0, the number is really integral
                            final long l = yFullMantissa >> (AOR_minus(1075, yRawExp, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45786, _mut45787, _mut45788, _mut45789));
                            return FastMath.pow(x, (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45790, _mut45791, _mut45792, _mut45793, _mut45794)) ? -l : l);
                        }
                    } else {
                        // we know it fits in a primitive long because yRawExp > 1085 has been handled above
                        final long l = yFullMantissa << (AOR_minus(yRawExp, 1075, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45768, _mut45769, _mut45770, _mut45771));
                        return FastMath.pow(x, (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45772, _mut45773, _mut45774, _mut45775, _mut45776)) ? -l : l);
                    }
                }
                if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45795, _mut45796, _mut45797, _mut45798, _mut45799)) {
                    // the integer powers have already been handled above
                    return ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45932, _mut45933, _mut45934, _mut45935, _mut45936) ? Double.POSITIVE_INFINITY : +0.0;
                } else if (ROR_equals(xRawExp, 2047, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45800, _mut45801, _mut45802, _mut45803, _mut45804)) {
                    if (ROR_equals(xRawMantissa, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45922, _mut45923, _mut45924, _mut45925, _mut45926)) {
                        // x = -infinity or x = +infinity
                        return (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45927, _mut45928, _mut45929, _mut45930, _mut45931)) ? +0.0 : Double.POSITIVE_INFINITY;
                    } else {
                        // NaN
                        return Double.NaN;
                    }
                } else if (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45805, _mut45806, _mut45807, _mut45808, _mut45809)) {
                    // the integer powers have already been handled above
                    return Double.NaN;
                } else {
                    // Split y into ya and yb such that y = ya+yb
                    final double tmp = AOR_multiply(y, HEX_40000000, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45810, _mut45811, _mut45812, _mut45813);
                    final double ya = AOR_minus((AOR_plus(y, tmp, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45814, _mut45815, _mut45816, _mut45817)), tmp, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45818, _mut45819, _mut45820, _mut45821);
                    final double yb = AOR_minus(y, ya, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45822, _mut45823, _mut45824, _mut45825);
                    /* Compute ln(x) */
                    final double[] lns = new double[2];
                    final double lores = log(x, lns);
                    if (Double.isInfinite(lores)) {
                        // don't allow this to be converted to NaN
                        return lores;
                    }
                    double lna = lns[0];
                    double lnb = lns[1];
                    /* resplit lns */
                    final double tmp1 = AOR_multiply(lna, HEX_40000000, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45826, _mut45827, _mut45828, _mut45829);
                    final double tmp2 = AOR_minus((AOR_plus(lna, tmp1, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45830, _mut45831, _mut45832, _mut45833)), tmp1, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45834, _mut45835, _mut45836, _mut45837);
                    lnb += AOR_minus(lna, tmp2, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45838, _mut45839, _mut45840, _mut45841);
                    lna = tmp2;
                    // y*ln(x) = (aa+ab)
                    final double aa = AOR_multiply(lna, ya, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45842, _mut45843, _mut45844, _mut45845);
                    final double ab = AOR_plus(AOR_plus(AOR_multiply(lna, yb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45846, _mut45847, _mut45848, _mut45849), AOR_multiply(lnb, ya, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45850, _mut45851, _mut45852, _mut45853), "org.apache.commons.math3.util.FastMath.pow_1474", _mut45854, _mut45855, _mut45856, _mut45857), AOR_multiply(lnb, yb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45858, _mut45859, _mut45860, _mut45861), "org.apache.commons.math3.util.FastMath.pow_1474", _mut45862, _mut45863, _mut45864, _mut45865);
                    lna = AOR_plus(aa, ab, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45866, _mut45867, _mut45868, _mut45869);
                    lnb = -(AOR_minus(AOR_minus(lna, aa, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45870, _mut45871, _mut45872, _mut45873), ab, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45874, _mut45875, _mut45876, _mut45877));
                    double z = AOR_divide(1.0, 120.0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45878, _mut45879, _mut45880, _mut45881);
                    z = AOR_plus(AOR_multiply(z, lnb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45882, _mut45883, _mut45884, _mut45885), (AOR_divide(1.0, 24.0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45886, _mut45887, _mut45888, _mut45889)), "org.apache.commons.math3.util.FastMath.pow_1474", _mut45890, _mut45891, _mut45892, _mut45893);
                    z = AOR_plus(AOR_multiply(z, lnb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45894, _mut45895, _mut45896, _mut45897), (AOR_divide(1.0, 6.0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45898, _mut45899, _mut45900, _mut45901)), "org.apache.commons.math3.util.FastMath.pow_1474", _mut45902, _mut45903, _mut45904, _mut45905);
                    z = AOR_plus(AOR_multiply(z, lnb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45906, _mut45907, _mut45908, _mut45909), 0.5, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45910, _mut45911, _mut45912, _mut45913);
                    z = AOR_plus(AOR_multiply(z, lnb, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45914, _mut45915, _mut45916, _mut45917), 1.0, "org.apache.commons.math3.util.FastMath.pow_1474", _mut45918, _mut45919, _mut45920, _mut45921);
                    z *= lnb;
                    final double result = exp(lna, z, null);
                    // result = result + result * z;
                    return result;
                }
            }
        }
    }

    /**
     * Raise a double to an int power.
     *
     * @param d Number to raise.
     * @param e Exponent.
     * @return d<sup>e</sup>
     * @since 3.1
     */
    public static double pow(double d, int e) {
        return pow(d, (long) e);
    }

    /**
     * Raise a double to a long power.
     *
     * @param d Number to raise.
     * @param e Exponent.
     * @return d<sup>e</sup>
     * @since 3.6
     */
    public static double pow(double d, long e) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.pow_1630");
        if (ROR_equals(e, 0, "org.apache.commons.math3.util.FastMath.pow_1630", _mut45986, _mut45987, _mut45988, _mut45989, _mut45990)) {
            return 1.0;
        } else if (ROR_greater(e, 0, "org.apache.commons.math3.util.FastMath.pow_1630", _mut45991, _mut45992, _mut45993, _mut45994, _mut45995)) {
            return new Split(d).pow(e).full;
        } else {
            return new Split(d).reciprocal().pow(-e).full;
        }
    }

    /**
     * Class operator on double numbers split into one 26 bits number and one 27 bits number.
     */
    private static class Split {

        /**
         * Split version of NaN.
         */
        public static final Split NAN = new Split(Double.NaN, 0);

        /**
         * Split version of positive infinity.
         */
        public static final Split POSITIVE_INFINITY = new Split(Double.POSITIVE_INFINITY, 0);

        /**
         * Split version of negative infinity.
         */
        public static final Split NEGATIVE_INFINITY = new Split(Double.NEGATIVE_INFINITY, 0);

        /**
         * Full number.
         */
        private final double full;

        /**
         * High order bits.
         */
        private final double high;

        /**
         * Low order bits.
         */
        private final double low;

        /**
         * Simple constructor.
         * @param x number to split
         */
        Split(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.Split_1664");
            full = x;
            high = Double.longBitsToDouble(Double.doubleToRawLongBits(x) & ((-1L) << 27));
            low = AOR_minus(x, high, "org.apache.commons.math3.util.FastMath.Split_1664", _mut45996, _mut45997, _mut45998, _mut45999);
        }

        /**
         * Simple constructor.
         * @param high high order bits
         * @param low low order bits
         */
        Split(final double high, final double low) {
            this(ROR_equals(high, 0.0, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46000, _mut46001, _mut46002, _mut46003, _mut46004) ? ((_mut46019 ? (ROR_equals(low, 0.0, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46009, _mut46010, _mut46011, _mut46012, _mut46013) || ROR_equals(Double.doubleToRawLongBits(high), Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46014, _mut46015, _mut46016, _mut46017, _mut46018)) : (ROR_equals(low, 0.0, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46009, _mut46010, _mut46011, _mut46012, _mut46013) && ROR_equals(Double.doubleToRawLongBits(high), Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46014, _mut46015, _mut46016, _mut46017, _mut46018))) ? /* negative zero */
            -0.0 : low) : AOR_plus(high, low, "org.apache.commons.math3.util.FastMath.Split_1674", _mut46005, _mut46006, _mut46007, _mut46008), high, low);
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.Split_1674");
        }

        /**
         * Simple constructor.
         * @param full full number
         * @param high high order bits
         * @param low low order bits
         */
        Split(final double full, final double high, final double low) {
            this.full = full;
            this.high = high;
            this.low = low;
        }

        /**
         * Multiply the instance by another one.
         * @param b other instance to multiply by
         * @return product
         */
        public Split multiply(final Split b) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.multiply_1693");
            // beware the following expressions must NOT be simplified, they rely on floating point arithmetic properties
            final Split mulBasic = new Split(AOR_multiply(full, b.full, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46020, _mut46021, _mut46022, _mut46023));
            final double mulError = AOR_minus(AOR_multiply(low, b.low, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46024, _mut46025, _mut46026, _mut46027), (AOR_minus((AOR_minus((AOR_minus(mulBasic.full, AOR_multiply(high, b.high, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46028, _mut46029, _mut46030, _mut46031), "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46032, _mut46033, _mut46034, _mut46035)), AOR_multiply(low, b.high, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46036, _mut46037, _mut46038, _mut46039), "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46040, _mut46041, _mut46042, _mut46043)), AOR_multiply(high, b.low, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46044, _mut46045, _mut46046, _mut46047), "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46048, _mut46049, _mut46050, _mut46051)), "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46052, _mut46053, _mut46054, _mut46055);
            return new Split(mulBasic.high, AOR_plus(mulBasic.low, mulError, "org.apache.commons.math3.util.FastMath.multiply_1693", _mut46056, _mut46057, _mut46058, _mut46059));
        }

        /**
         * Compute the reciprocal of the instance.
         * @return reciprocal of the instance
         */
        public Split reciprocal() {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.reciprocal_1703");
            final double approximateInv = AOR_divide(1.0, full, "org.apache.commons.math3.util.FastMath.reciprocal_1703", _mut46060, _mut46061, _mut46062, _mut46063);
            final Split splitInv = new Split(approximateInv);
            // beware the following expressions must NOT be simplified, they rely on floating point arithmetic properties
            final Split product = multiply(splitInv);
            final double error = AOR_plus((AOR_minus(product.high, 1, "org.apache.commons.math3.util.FastMath.reciprocal_1703", _mut46064, _mut46065, _mut46066, _mut46067)), product.low, "org.apache.commons.math3.util.FastMath.reciprocal_1703", _mut46068, _mut46069, _mut46070, _mut46071);
            // better accuracy estimate of reciprocal
            return Double.isNaN(error) ? splitInv : new Split(splitInv.high, AOR_minus(splitInv.low, AOR_divide(error, full, "org.apache.commons.math3.util.FastMath.reciprocal_1703", _mut46072, _mut46073, _mut46074, _mut46075), "org.apache.commons.math3.util.FastMath.reciprocal_1703", _mut46076, _mut46077, _mut46078, _mut46079));
        }

        /**
         * Computes this^e.
         * @param e exponent (beware, here it MUST be > 0; the only exclusion is Long.MIN_VALUE)
         * @return d^e, split in high and low bits
         * @since 3.6
         */
        private Split pow(final long e) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.pow_1724");
            // prepare result
            Split result = new Split(1);
            // d^(2p)
            Split d2p = new Split(full, high, low);
            for (long p = e; ROR_not_equals(p, 0, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46085, _mut46086, _mut46087, _mut46088, _mut46089); p >>>= 1) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.pow_1724");
                if (ROR_not_equals((p & 0x1), 0, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46080, _mut46081, _mut46082, _mut46083, _mut46084)) {
                    // accurate multiplication result = result * d^(2p) using Veltkamp TwoProduct algorithm
                    result = result.multiply(d2p);
                }
                // accurate squaring d^(2(p+1)) = d^(2p) * d^(2p) using Veltkamp TwoProduct algorithm
                d2p = d2p.multiply(d2p);
            }
            if (Double.isNaN(result.full)) {
                if (Double.isNaN(full)) {
                    return Split.NAN;
                } else {
                    // and the low order bits became NaN (because infinity - infinity = NaN)
                    if (ROR_less(FastMath.abs(full), 1, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46090, _mut46091, _mut46092, _mut46093, _mut46094)) {
                        return new Split(FastMath.copySign(0.0, full), 0.0);
                    } else if ((_mut46105 ? (ROR_less(full, 0, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46095, _mut46096, _mut46097, _mut46098, _mut46099) || ROR_equals((e & 0x1), 1, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46100, _mut46101, _mut46102, _mut46103, _mut46104)) : (ROR_less(full, 0, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46095, _mut46096, _mut46097, _mut46098, _mut46099) && ROR_equals((e & 0x1), 1, "org.apache.commons.math3.util.FastMath.pow_1724", _mut46100, _mut46101, _mut46102, _mut46103, _mut46104)))) {
                        return Split.NEGATIVE_INFINITY;
                    } else {
                        return Split.POSITIVE_INFINITY;
                    }
                }
            } else {
                return result;
            }
        }
    }

    /**
     *  Computes sin(x) - x, where |x| < 1/16.
     *  Use a Remez polynomial approximation.
     *  @param x a number smaller than 1/16
     *  @return sin(x) - x
     */
    private static double polySine(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.polySine_1772");
        double x2 = AOR_multiply(x, x, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46106, _mut46107, _mut46108, _mut46109);
        double p = 2.7553817452272217E-6;
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46110, _mut46111, _mut46112, _mut46113), -1.9841269659586505E-4, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46114, _mut46115, _mut46116, _mut46117);
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46118, _mut46119, _mut46120, _mut46121), 0.008333333333329196, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46122, _mut46123, _mut46124, _mut46125);
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46126, _mut46127, _mut46128, _mut46129), -0.16666666666666666, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46130, _mut46131, _mut46132, _mut46133);
        // p *= x;
        p = AOR_multiply(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46134, _mut46135, _mut46136, _mut46137), x, "org.apache.commons.math3.util.FastMath.polySine_1772", _mut46138, _mut46139, _mut46140, _mut46141);
        return p;
    }

    /**
     *  Computes cos(x) - 1, where |x| < 1/16.
     *  Use a Remez polynomial approximation.
     *  @param x a number smaller than 1/16
     *  @return cos(x) - 1
     */
    private static double polyCosine(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.polyCosine_1793");
        double x2 = AOR_multiply(x, x, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46142, _mut46143, _mut46144, _mut46145);
        double p = 2.479773539153719E-5;
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46146, _mut46147, _mut46148, _mut46149), -0.0013888888689039883, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46150, _mut46151, _mut46152, _mut46153);
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46154, _mut46155, _mut46156, _mut46157), 0.041666666666621166, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46158, _mut46159, _mut46160, _mut46161);
        p = AOR_plus(AOR_multiply(p, x2, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46162, _mut46163, _mut46164, _mut46165), -0.49999999999999994, "org.apache.commons.math3.util.FastMath.polyCosine_1793", _mut46166, _mut46167, _mut46168, _mut46169);
        p *= x2;
        return p;
    }

    /**
     *  Compute sine over the first quadrant (0 < x < pi/2).
     *  Use combination of table lookup and rational polynomial expansion.
     *  @param xa number from which sine is requested
     *  @param xb extra bits for x (may be 0.0)
     *  @return sin(xa + xb)
     */
    private static double sinQ(double xa, double xb) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.sinQ_1812");
        int idx = (int) (AOR_plus((AOR_multiply(xa, 8.0, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46170, _mut46171, _mut46172, _mut46173)), 0.5, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46174, _mut46175, _mut46176, _mut46177));
        // idx*0.125;
        final double epsilon = AOR_minus(xa, EIGHTHS[idx], "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46178, _mut46179, _mut46180, _mut46181);
        // Table lookups
        final double sintA = SINE_TABLE_A[idx];
        final double sintB = SINE_TABLE_B[idx];
        final double costA = COSINE_TABLE_A[idx];
        final double costB = COSINE_TABLE_B[idx];
        // Polynomial eval of sin(epsilon), cos(epsilon)
        double sinEpsA = epsilon;
        double sinEpsB = polySine(epsilon);
        final double cosEpsA = 1.0;
        final double cosEpsB = polyCosine(epsilon);
        // Split epsilon   xa + xb = x
        final double temp = AOR_multiply(sinEpsA, HEX_40000000, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46182, _mut46183, _mut46184, _mut46185);
        double temp2 = AOR_minus((AOR_plus(sinEpsA, temp, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46186, _mut46187, _mut46188, _mut46189)), temp, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46190, _mut46191, _mut46192, _mut46193);
        sinEpsB += AOR_minus(sinEpsA, temp2, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46194, _mut46195, _mut46196, _mut46197);
        sinEpsA = temp2;
        /* Compute sin(x) by angle addition formula */
        double result;
        // result += costA*sinEpsA + costA*sinEpsB + costB*sinEpsA + costB * sinEpsB;
        double a = 0;
        double b = 0;
        double t = sintA;
        double c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46198, _mut46199, _mut46200, _mut46201);
        double d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46202, _mut46203, _mut46204, _mut46205), t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46206, _mut46207, _mut46208, _mut46209));
        a = c;
        b += d;
        t = AOR_multiply(costA, sinEpsA, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46210, _mut46211, _mut46212, _mut46213);
        c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46214, _mut46215, _mut46216, _mut46217);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46218, _mut46219, _mut46220, _mut46221), t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46222, _mut46223, _mut46224, _mut46225));
        a = c;
        b += d;
        b = AOR_plus(AOR_plus(b, AOR_multiply(sintA, cosEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46226, _mut46227, _mut46228, _mut46229), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46230, _mut46231, _mut46232, _mut46233), AOR_multiply(costA, sinEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46234, _mut46235, _mut46236, _mut46237), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46238, _mut46239, _mut46240, _mut46241);
        b = AOR_plus(AOR_plus(AOR_plus(AOR_plus(b, sintB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46242, _mut46243, _mut46244, _mut46245), AOR_multiply(costB, sinEpsA, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46246, _mut46247, _mut46248, _mut46249), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46250, _mut46251, _mut46252, _mut46253), AOR_multiply(sintB, cosEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46254, _mut46255, _mut46256, _mut46257), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46258, _mut46259, _mut46260, _mut46261), AOR_multiply(costB, sinEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46262, _mut46263, _mut46264, _mut46265), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46266, _mut46267, _mut46268, _mut46269);
        if (ROR_not_equals(xb, 0.0, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46270, _mut46271, _mut46272, _mut46273, _mut46274)) {
            t = AOR_multiply((AOR_minus(AOR_multiply((AOR_plus(costA, costB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46275, _mut46276, _mut46277, _mut46278)), (AOR_plus(cosEpsA, cosEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46279, _mut46280, _mut46281, _mut46282)), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46283, _mut46284, _mut46285, _mut46286), AOR_multiply((AOR_plus(sintA, sintB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46287, _mut46288, _mut46289, _mut46290)), (AOR_plus(sinEpsA, sinEpsB, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46291, _mut46292, _mut46293, _mut46294)), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46295, _mut46296, _mut46297, _mut46298), "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46299, _mut46300, _mut46301, _mut46302)), // approximate cosine*xb
            xb, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46303, _mut46304, _mut46305, _mut46306);
            c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46307, _mut46308, _mut46309, _mut46310);
            d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46311, _mut46312, _mut46313, _mut46314), t, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46315, _mut46316, _mut46317, _mut46318));
            a = c;
            b += d;
        }
        result = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.sinQ_1812", _mut46319, _mut46320, _mut46321, _mut46322);
        return result;
    }

    /**
     * Compute cosine in the first quadrant by subtracting input from PI/2 and
     * then calling sinQ.  This is more accurate as the input approaches PI/2.
     *  @param xa number from which cosine is requested
     *  @param xb extra bits for x (may be 0.0)
     *  @return cos(xa + xb)
     */
    private static double cosQ(double xa, double xb) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.cosQ_1936");
        final double pi2a = 1.5707963267948966;
        final double pi2b = 6.123233995736766E-17;
        final double a = AOR_minus(pi2a, xa, "org.apache.commons.math3.util.FastMath.cosQ_1936", _mut46323, _mut46324, _mut46325, _mut46326);
        double b = -(AOR_plus(AOR_minus(a, pi2a, "org.apache.commons.math3.util.FastMath.cosQ_1936", _mut46327, _mut46328, _mut46329, _mut46330), xa, "org.apache.commons.math3.util.FastMath.cosQ_1936", _mut46331, _mut46332, _mut46333, _mut46334));
        b += AOR_minus(pi2b, xb, "org.apache.commons.math3.util.FastMath.cosQ_1936", _mut46335, _mut46336, _mut46337, _mut46338);
        return sinQ(a, b);
    }

    /**
     *  Compute tangent (or cotangent) over the first quadrant.   0 < x < pi/2
     *  Use combination of table lookup and rational polynomial expansion.
     *  @param xa number from which sine is requested
     *  @param xb extra bits for x (may be 0.0)
     *  @param cotanFlag if true, compute the cotangent instead of the tangent
     *  @return tan(xa+xb) (or cotangent, depending on cotanFlag)
     */
    private static double tanQ(double xa, double xb, boolean cotanFlag) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.tanQ_1955");
        int idx = (int) (AOR_plus((AOR_multiply(xa, 8.0, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46339, _mut46340, _mut46341, _mut46342)), 0.5, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46343, _mut46344, _mut46345, _mut46346));
        // idx*0.125;
        final double epsilon = AOR_minus(xa, EIGHTHS[idx], "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46347, _mut46348, _mut46349, _mut46350);
        // Table lookups
        final double sintA = SINE_TABLE_A[idx];
        final double sintB = SINE_TABLE_B[idx];
        final double costA = COSINE_TABLE_A[idx];
        final double costB = COSINE_TABLE_B[idx];
        // Polynomial eval of sin(epsilon), cos(epsilon)
        double sinEpsA = epsilon;
        double sinEpsB = polySine(epsilon);
        final double cosEpsA = 1.0;
        final double cosEpsB = polyCosine(epsilon);
        // Split epsilon   xa + xb = x
        double temp = AOR_multiply(sinEpsA, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46351, _mut46352, _mut46353, _mut46354);
        double temp2 = AOR_minus((AOR_plus(sinEpsA, temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46355, _mut46356, _mut46357, _mut46358)), temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46359, _mut46360, _mut46361, _mut46362);
        sinEpsB += AOR_minus(sinEpsA, temp2, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46363, _mut46364, _mut46365, _mut46366);
        sinEpsA = temp2;
        // result += costA*sinEpsA + costA*sinEpsB + costB*sinEpsA + costB * sinEpsB;
        double a = 0;
        double b = 0;
        // Compute sine
        double t = sintA;
        double c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46367, _mut46368, _mut46369, _mut46370);
        double d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46371, _mut46372, _mut46373, _mut46374), t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46375, _mut46376, _mut46377, _mut46378));
        a = c;
        b += d;
        t = AOR_multiply(costA, sinEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46379, _mut46380, _mut46381, _mut46382);
        c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46383, _mut46384, _mut46385, _mut46386);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46387, _mut46388, _mut46389, _mut46390), t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46391, _mut46392, _mut46393, _mut46394));
        a = c;
        b += d;
        b += AOR_plus(AOR_multiply(sintA, cosEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46395, _mut46396, _mut46397, _mut46398), AOR_multiply(costA, sinEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46399, _mut46400, _mut46401, _mut46402), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46403, _mut46404, _mut46405, _mut46406);
        b += AOR_plus(AOR_plus(AOR_plus(sintB, AOR_multiply(costB, sinEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46407, _mut46408, _mut46409, _mut46410), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46411, _mut46412, _mut46413, _mut46414), AOR_multiply(sintB, cosEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46415, _mut46416, _mut46417, _mut46418), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46419, _mut46420, _mut46421, _mut46422), AOR_multiply(costB, sinEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46423, _mut46424, _mut46425, _mut46426), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46427, _mut46428, _mut46429, _mut46430);
        double sina = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46431, _mut46432, _mut46433, _mut46434);
        double sinb = -(AOR_minus(AOR_minus(sina, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46435, _mut46436, _mut46437, _mut46438), b, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46439, _mut46440, _mut46441, _mut46442));
        a = b = c = d = 0.0;
        t = AOR_multiply(costA, cosEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46443, _mut46444, _mut46445, _mut46446);
        c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46447, _mut46448, _mut46449, _mut46450);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46451, _mut46452, _mut46453, _mut46454), t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46455, _mut46456, _mut46457, _mut46458));
        a = c;
        b += d;
        t = AOR_multiply(-sintA, sinEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46459, _mut46460, _mut46461, _mut46462);
        c = AOR_plus(a, t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46463, _mut46464, _mut46465, _mut46466);
        d = -(AOR_minus(AOR_minus(c, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46467, _mut46468, _mut46469, _mut46470), t, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46471, _mut46472, _mut46473, _mut46474));
        a = c;
        b += d;
        b += AOR_plus(AOR_plus(AOR_multiply(costB, cosEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46475, _mut46476, _mut46477, _mut46478), AOR_multiply(costA, cosEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46479, _mut46480, _mut46481, _mut46482), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46483, _mut46484, _mut46485, _mut46486), AOR_multiply(costB, cosEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46487, _mut46488, _mut46489, _mut46490), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46491, _mut46492, _mut46493, _mut46494);
        b -= AOR_plus(AOR_plus(AOR_multiply(sintB, sinEpsA, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46495, _mut46496, _mut46497, _mut46498), AOR_multiply(sintA, sinEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46499, _mut46500, _mut46501, _mut46502), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46503, _mut46504, _mut46505, _mut46506), AOR_multiply(sintB, sinEpsB, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46507, _mut46508, _mut46509, _mut46510), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46511, _mut46512, _mut46513, _mut46514);
        double cosa = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46515, _mut46516, _mut46517, _mut46518);
        double cosb = -(AOR_minus(AOR_minus(cosa, a, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46519, _mut46520, _mut46521, _mut46522), b, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46523, _mut46524, _mut46525, _mut46526));
        if (cotanFlag) {
            double tmp;
            tmp = cosa;
            cosa = sina;
            sina = tmp;
            tmp = cosb;
            cosb = sinb;
            sinb = tmp;
        }
        double est = AOR_divide(sina, cosa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46527, _mut46528, _mut46529, _mut46530);
        /* Split the estimate to get more accurate read on division rounding */
        temp = AOR_multiply(est, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46531, _mut46532, _mut46533, _mut46534);
        double esta = AOR_minus((AOR_plus(est, temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46535, _mut46536, _mut46537, _mut46538)), temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46539, _mut46540, _mut46541, _mut46542);
        double estb = AOR_minus(est, esta, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46543, _mut46544, _mut46545, _mut46546);
        temp = AOR_multiply(cosa, HEX_40000000, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46547, _mut46548, _mut46549, _mut46550);
        double cosaa = AOR_minus((AOR_plus(cosa, temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46551, _mut46552, _mut46553, _mut46554)), temp, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46555, _mut46556, _mut46557, _mut46558);
        double cosab = AOR_minus(cosa, cosaa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46559, _mut46560, _mut46561, _mut46562);
        // Correction for division rounding
        double err = AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(sina, AOR_multiply(esta, cosaa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46563, _mut46564, _mut46565, _mut46566), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46567, _mut46568, _mut46569, _mut46570), AOR_multiply(esta, cosab, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46571, _mut46572, _mut46573, _mut46574), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46575, _mut46576, _mut46577, _mut46578), AOR_multiply(estb, cosaa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46579, _mut46580, _mut46581, _mut46582), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46583, _mut46584, _mut46585, _mut46586), AOR_multiply(estb, cosab, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46587, _mut46588, _mut46589, _mut46590), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46591, _mut46592, _mut46593, _mut46594)), cosa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46595, _mut46596, _mut46597, _mut46598);
        // Change in est due to sinb
        err += AOR_divide(sinb, cosa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46599, _mut46600, _mut46601, _mut46602);
        // Change in est due to cosb
        err += AOR_divide(AOR_divide(AOR_multiply(-sina, cosb, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46603, _mut46604, _mut46605, _mut46606), cosa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46607, _mut46608, _mut46609, _mut46610), cosa, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46611, _mut46612, _mut46613, _mut46614);
        if (ROR_not_equals(xb, 0.0, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46615, _mut46616, _mut46617, _mut46618, _mut46619)) {
            // Approximate impact of xb
            double xbadj = AOR_plus(xb, AOR_multiply(AOR_multiply(est, est, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46620, _mut46621, _mut46622, _mut46623), xb, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46624, _mut46625, _mut46626, _mut46627), "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46628, _mut46629, _mut46630, _mut46631);
            if (cotanFlag) {
                xbadj = -xbadj;
            }
            err += xbadj;
        }
        return AOR_plus(est, err, "org.apache.commons.math3.util.FastMath.tanQ_1955", _mut46632, _mut46633, _mut46634, _mut46635);
    }

    /**
     * Reduce the input argument using the Payne and Hanek method.
     *  This is good for all inputs 0.0 < x < inf
     *  Output is remainder after dividing by PI/2
     *  The result array should contain 3 numbers.
     *  result[0] is the integer portion, so mod 4 this gives the quadrant.
     *  result[1] is the upper bits of the remainder
     *  result[2] is the lower bits of the remainder
     *
     * @param x number to reduce
     * @param result placeholder where to put the result
     */
    private static void reducePayneHanek(double x, double[] result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.reducePayneHanek_2103");
        /* Convert input double to bits */
        long inbits = Double.doubleToRawLongBits(x);
        int exponent = AOR_minus((int) ((inbits >> 52) & 0x7ff), 1023, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46636, _mut46637, _mut46638, _mut46639);
        /* Convert to fixed point representation */
        inbits &= 0x000fffffffffffffL;
        inbits |= 0x0010000000000000L;
        /* Normalize input to be between 0.5 and 1.0 */
        exponent++;
        inbits <<= 11;
        /* Based on the exponent, get a shifted copy of recip2pi */
        long shpi0;
        long shpiA;
        long shpiB;
        int idx = exponent >> 6;
        int shift = AOR_minus(exponent, (idx << 6), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46640, _mut46641, _mut46642, _mut46643);
        if (ROR_not_equals(shift, 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46644, _mut46645, _mut46646, _mut46647, _mut46648)) {
            shpi0 = (ROR_equals(idx, 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46662, _mut46663, _mut46664, _mut46665, _mut46666)) ? 0 : (RECIP_2PI[AOR_minus(idx, 1, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46667, _mut46668, _mut46669, _mut46670)] << shift);
            shpi0 |= RECIP_2PI[idx] >>> (AOR_minus(64, shift, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46671, _mut46672, _mut46673, _mut46674));
            shpiA = (RECIP_2PI[idx] << shift) | (RECIP_2PI[AOR_plus(idx, 1, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46675, _mut46676, _mut46677, _mut46678)] >>> (AOR_minus(64, shift, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46679, _mut46680, _mut46681, _mut46682)));
            shpiB = (RECIP_2PI[AOR_plus(idx, 1, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46683, _mut46684, _mut46685, _mut46686)] << shift) | (RECIP_2PI[AOR_plus(idx, 2, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46687, _mut46688, _mut46689, _mut46690)] >>> (AOR_minus(64, shift, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46691, _mut46692, _mut46693, _mut46694)));
        } else {
            shpi0 = (ROR_equals(idx, 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46649, _mut46650, _mut46651, _mut46652, _mut46653)) ? 0 : RECIP_2PI[AOR_minus(idx, 1, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46654, _mut46655, _mut46656, _mut46657)];
            shpiA = RECIP_2PI[idx];
            shpiB = RECIP_2PI[AOR_plus(idx, 1, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46658, _mut46659, _mut46660, _mut46661)];
        }
        /* Multiply input by shpiA */
        long a = inbits >>> 32;
        long b = inbits & 0xffffffffL;
        long c = shpiA >>> 32;
        long d = shpiA & 0xffffffffL;
        long ac = AOR_multiply(a, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46695, _mut46696, _mut46697, _mut46698);
        long bd = AOR_multiply(b, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46699, _mut46700, _mut46701, _mut46702);
        long bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46703, _mut46704, _mut46705, _mut46706);
        long ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46707, _mut46708, _mut46709, _mut46710);
        long prodB = AOR_plus(bd, (ad << 32), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46711, _mut46712, _mut46713, _mut46714);
        long prodA = AOR_plus(ac, (ad >>> 32), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46715, _mut46716, _mut46717, _mut46718);
        boolean bita = ROR_not_equals((bd & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46719, _mut46720, _mut46721, _mut46722, _mut46723);
        boolean bitb = ROR_not_equals((ad & 0x80000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46724, _mut46725, _mut46726, _mut46727, _mut46728);
        boolean bitsum = ROR_not_equals((prodB & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46729, _mut46730, _mut46731, _mut46732, _mut46733);
        /* Carry */
        if ((_mut46737 ? (((_mut46734 ? (bita || bitb) : (bita && bitb))) && ((_mut46736 ? (((_mut46735 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46735 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46734 ? (bita || bitb) : (bita && bitb))) || ((_mut46736 ? (((_mut46735 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46735 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prodA++;
        }
        bita = ROR_not_equals((prodB & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46738, _mut46739, _mut46740, _mut46741, _mut46742);
        bitb = ROR_not_equals((bc & 0x80000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46743, _mut46744, _mut46745, _mut46746, _mut46747);
        prodB += bc << 32;
        prodA += bc >>> 32;
        bitsum = ROR_not_equals((prodB & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46748, _mut46749, _mut46750, _mut46751, _mut46752);
        /* Carry */
        if ((_mut46756 ? (((_mut46753 ? (bita || bitb) : (bita && bitb))) && ((_mut46755 ? (((_mut46754 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46754 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46753 ? (bita || bitb) : (bita && bitb))) || ((_mut46755 ? (((_mut46754 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46754 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prodA++;
        }
        /* Multiply input by shpiB */
        c = shpiB >>> 32;
        d = shpiB & 0xffffffffL;
        ac = AOR_multiply(a, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46757, _mut46758, _mut46759, _mut46760);
        bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46761, _mut46762, _mut46763, _mut46764);
        ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46765, _mut46766, _mut46767, _mut46768);
        /* Collect terms */
        ac += (AOR_plus(bc, ad, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46769, _mut46770, _mut46771, _mut46772)) >>> 32;
        bita = ROR_not_equals((prodB & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46773, _mut46774, _mut46775, _mut46776, _mut46777);
        bitb = ROR_not_equals((ac & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46778, _mut46779, _mut46780, _mut46781, _mut46782);
        prodB += ac;
        bitsum = ROR_not_equals((prodB & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46783, _mut46784, _mut46785, _mut46786, _mut46787);
        /* Carry */
        if ((_mut46791 ? (((_mut46788 ? (bita || bitb) : (bita && bitb))) && ((_mut46790 ? (((_mut46789 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46789 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46788 ? (bita || bitb) : (bita && bitb))) || ((_mut46790 ? (((_mut46789 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46789 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prodA++;
        }
        /* Multiply by shpi0 */
        c = shpi0 >>> 32;
        d = shpi0 & 0xffffffffL;
        bd = AOR_multiply(b, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46792, _mut46793, _mut46794, _mut46795);
        bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46796, _mut46797, _mut46798, _mut46799);
        ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46800, _mut46801, _mut46802, _mut46803);
        prodA += AOR_plus(bd, ((AOR_plus(bc, ad, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46804, _mut46805, _mut46806, _mut46807)) << 32), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46808, _mut46809, _mut46810, _mut46811);
        /* This identifies the quadrant */
        int intPart = (int) (prodA >>> 62);
        /* Multiply by 4 */
        prodA <<= 2;
        prodA |= prodB >>> 62;
        prodB <<= 2;
        /* Multiply by PI/4 */
        a = prodA >>> 32;
        b = prodA & 0xffffffffL;
        c = PI_O_4_BITS[0] >>> 32;
        d = PI_O_4_BITS[0] & 0xffffffffL;
        ac = AOR_multiply(a, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46812, _mut46813, _mut46814, _mut46815);
        bd = AOR_multiply(b, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46816, _mut46817, _mut46818, _mut46819);
        bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46820, _mut46821, _mut46822, _mut46823);
        ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46824, _mut46825, _mut46826, _mut46827);
        long prod2B = AOR_plus(bd, (ad << 32), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46828, _mut46829, _mut46830, _mut46831);
        long prod2A = AOR_plus(ac, (ad >>> 32), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46832, _mut46833, _mut46834, _mut46835);
        bita = ROR_not_equals((bd & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46836, _mut46837, _mut46838, _mut46839, _mut46840);
        bitb = ROR_not_equals((ad & 0x80000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46841, _mut46842, _mut46843, _mut46844, _mut46845);
        bitsum = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46846, _mut46847, _mut46848, _mut46849, _mut46850);
        /* Carry */
        if ((_mut46854 ? (((_mut46851 ? (bita || bitb) : (bita && bitb))) && ((_mut46853 ? (((_mut46852 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46852 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46851 ? (bita || bitb) : (bita && bitb))) || ((_mut46853 ? (((_mut46852 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46852 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prod2A++;
        }
        bita = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46855, _mut46856, _mut46857, _mut46858, _mut46859);
        bitb = ROR_not_equals((bc & 0x80000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46860, _mut46861, _mut46862, _mut46863, _mut46864);
        prod2B += bc << 32;
        prod2A += bc >>> 32;
        bitsum = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46865, _mut46866, _mut46867, _mut46868, _mut46869);
        /* Carry */
        if ((_mut46873 ? (((_mut46870 ? (bita || bitb) : (bita && bitb))) && ((_mut46872 ? (((_mut46871 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46871 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46870 ? (bita || bitb) : (bita && bitb))) || ((_mut46872 ? (((_mut46871 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46871 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prod2A++;
        }
        /* Multiply input by pio4bits[1] */
        c = PI_O_4_BITS[1] >>> 32;
        d = PI_O_4_BITS[1] & 0xffffffffL;
        ac = AOR_multiply(a, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46874, _mut46875, _mut46876, _mut46877);
        bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46878, _mut46879, _mut46880, _mut46881);
        ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46882, _mut46883, _mut46884, _mut46885);
        /* Collect terms */
        ac += (AOR_plus(bc, ad, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46886, _mut46887, _mut46888, _mut46889)) >>> 32;
        bita = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46890, _mut46891, _mut46892, _mut46893, _mut46894);
        bitb = ROR_not_equals((ac & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46895, _mut46896, _mut46897, _mut46898, _mut46899);
        prod2B += ac;
        bitsum = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46900, _mut46901, _mut46902, _mut46903, _mut46904);
        /* Carry */
        if ((_mut46908 ? (((_mut46905 ? (bita || bitb) : (bita && bitb))) && ((_mut46907 ? (((_mut46906 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46906 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46905 ? (bita || bitb) : (bita && bitb))) || ((_mut46907 ? (((_mut46906 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46906 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prod2A++;
        }
        /* Multiply inputB by pio4bits[0] */
        a = prodB >>> 32;
        b = prodB & 0xffffffffL;
        c = PI_O_4_BITS[0] >>> 32;
        d = PI_O_4_BITS[0] & 0xffffffffL;
        ac = AOR_multiply(a, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46909, _mut46910, _mut46911, _mut46912);
        bc = AOR_multiply(b, c, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46913, _mut46914, _mut46915, _mut46916);
        ad = AOR_multiply(a, d, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46917, _mut46918, _mut46919, _mut46920);
        /* Collect terms */
        ac += (AOR_plus(bc, ad, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46921, _mut46922, _mut46923, _mut46924)) >>> 32;
        bita = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46925, _mut46926, _mut46927, _mut46928, _mut46929);
        bitb = ROR_not_equals((ac & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46930, _mut46931, _mut46932, _mut46933, _mut46934);
        prod2B += ac;
        bitsum = ROR_not_equals((prod2B & 0x8000000000000000L), 0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46935, _mut46936, _mut46937, _mut46938, _mut46939);
        /* Carry */
        if ((_mut46943 ? (((_mut46940 ? (bita || bitb) : (bita && bitb))) && ((_mut46942 ? (((_mut46941 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46941 ? (bita && bitb) : (bita || bitb))) && !bitsum)))) : (((_mut46940 ? (bita || bitb) : (bita && bitb))) || ((_mut46942 ? (((_mut46941 ? (bita && bitb) : (bita || bitb))) || !bitsum) : (((_mut46941 ? (bita && bitb) : (bita || bitb))) && !bitsum)))))) {
            prod2A++;
        }
        // High order 52 bits
        double tmpA = AOR_divide((prod2A >>> 12), TWO_POWER_52, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46944, _mut46945, _mut46946, _mut46947);
        // Low bits
        double tmpB = AOR_divide(AOR_divide((AOR_plus(((prod2A & 0xfffL) << 40), (prod2B >>> 24), "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46948, _mut46949, _mut46950, _mut46951)), TWO_POWER_52, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46952, _mut46953, _mut46954, _mut46955), TWO_POWER_52, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46956, _mut46957, _mut46958, _mut46959);
        double sumA = AOR_plus(tmpA, tmpB, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46960, _mut46961, _mut46962, _mut46963);
        double sumB = -(AOR_minus(AOR_minus(sumA, tmpA, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46964, _mut46965, _mut46966, _mut46967), tmpB, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46968, _mut46969, _mut46970, _mut46971));
        /* Multiply by PI/2 and return */
        result[0] = intPart;
        result[1] = AOR_multiply(sumA, 2.0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46972, _mut46973, _mut46974, _mut46975);
        result[2] = AOR_multiply(sumB, 2.0, "org.apache.commons.math3.util.FastMath.reducePayneHanek_2103", _mut46976, _mut46977, _mut46978, _mut46979);
    }

    /**
     * Sine function.
     *
     * @param x Argument.
     * @return sin(x)
     */
    public static double sin(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.sin_2321");
        boolean negative = false;
        int quadrant = 0;
        double xa;
        double xb = 0.0;
        /* Take absolute value of the input */
        xa = x;
        if (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.sin_2321", _mut46980, _mut46981, _mut46982, _mut46983, _mut46984)) {
            negative = true;
            xa = -xa;
        }
        /* Check for zero and negative zero */
        if (ROR_equals(xa, 0.0, "org.apache.commons.math3.util.FastMath.sin_2321", _mut46985, _mut46986, _mut46987, _mut46988, _mut46989)) {
            long bits = Double.doubleToRawLongBits(x);
            if (ROR_less(bits, 0, "org.apache.commons.math3.util.FastMath.sin_2321", _mut46990, _mut46991, _mut46992, _mut46993, _mut46994)) {
                return -0.0;
            }
            return 0.0;
        }
        if ((_mut47005 ? (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.sin_2321", _mut46995, _mut46996, _mut46997, _mut46998, _mut46999) && ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.sin_2321", _mut47000, _mut47001, _mut47002, _mut47003, _mut47004)) : (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.sin_2321", _mut46995, _mut46996, _mut46997, _mut46998, _mut46999) || ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.sin_2321", _mut47000, _mut47001, _mut47002, _mut47003, _mut47004)))) {
            return Double.NaN;
        }
        /* Perform any argument reduction */
        if (ROR_greater(xa, 3294198.0, "org.apache.commons.math3.util.FastMath.sin_2321", _mut47006, _mut47007, _mut47008, _mut47009, _mut47010)) {
            // PayneHanek.
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (ROR_greater(xa, 1.5707963267948966, "org.apache.commons.math3.util.FastMath.sin_2321", _mut47011, _mut47012, _mut47013, _mut47014, _mut47015)) {
            final CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        if (negative) {
            // Flip bit 1
            quadrant ^= 2;
        }
        switch(quadrant) {
            case 0:
                return sinQ(xa, xb);
            case 1:
                return cosQ(xa, xb);
            case 2:
                return -sinQ(xa, xb);
            case 3:
                return -cosQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    /**
     * Cosine function.
     *
     * @param x Argument.
     * @return cos(x)
     */
    public static double cos(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.cos_2388");
        int quadrant = 0;
        /* Take absolute value of the input */
        double xa = x;
        if (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47016, _mut47017, _mut47018, _mut47019, _mut47020)) {
            xa = -xa;
        }
        if ((_mut47031 ? (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47021, _mut47022, _mut47023, _mut47024, _mut47025) && ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47026, _mut47027, _mut47028, _mut47029, _mut47030)) : (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47021, _mut47022, _mut47023, _mut47024, _mut47025) || ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47026, _mut47027, _mut47028, _mut47029, _mut47030)))) {
            return Double.NaN;
        }
        /* Perform any argument reduction */
        double xb = 0;
        if (ROR_greater(xa, 3294198.0, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47032, _mut47033, _mut47034, _mut47035, _mut47036)) {
            // PayneHanek.
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (ROR_greater(xa, 1.5707963267948966, "org.apache.commons.math3.util.FastMath.cos_2388", _mut47037, _mut47038, _mut47039, _mut47040, _mut47041)) {
            final CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        switch(quadrant) {
            case 0:
                return cosQ(xa, xb);
            case 1:
                return -sinQ(xa, xb);
            case 2:
                return -cosQ(xa, xb);
            case 3:
                return sinQ(xa, xb);
            default:
                return Double.NaN;
        }
    }

    /**
     * Tangent function.
     *
     * @param x Argument.
     * @return tan(x)
     */
    public static double tan(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.tan_2442");
        boolean negative = false;
        int quadrant = 0;
        /* Take absolute value of the input */
        double xa = x;
        if (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47042, _mut47043, _mut47044, _mut47045, _mut47046)) {
            negative = true;
            xa = -xa;
        }
        /* Check for zero and negative zero */
        if (ROR_equals(xa, 0.0, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47047, _mut47048, _mut47049, _mut47050, _mut47051)) {
            long bits = Double.doubleToRawLongBits(x);
            if (ROR_less(bits, 0, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47052, _mut47053, _mut47054, _mut47055, _mut47056)) {
                return -0.0;
            }
            return 0.0;
        }
        if ((_mut47067 ? (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47057, _mut47058, _mut47059, _mut47060, _mut47061) && ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47062, _mut47063, _mut47064, _mut47065, _mut47066)) : (ROR_not_equals(xa, xa, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47057, _mut47058, _mut47059, _mut47060, _mut47061) || ROR_equals(xa, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47062, _mut47063, _mut47064, _mut47065, _mut47066)))) {
            return Double.NaN;
        }
        /* Perform any argument reduction */
        double xb = 0;
        if (ROR_greater(xa, 3294198.0, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47068, _mut47069, _mut47070, _mut47071, _mut47072)) {
            // PayneHanek.
            double[] reduceResults = new double[3];
            reducePayneHanek(xa, reduceResults);
            quadrant = ((int) reduceResults[0]) & 3;
            xa = reduceResults[1];
            xb = reduceResults[2];
        } else if (ROR_greater(xa, 1.5707963267948966, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47073, _mut47074, _mut47075, _mut47076, _mut47077)) {
            final CodyWaite cw = new CodyWaite(xa);
            quadrant = cw.getK() & 3;
            xa = cw.getRemA();
            xb = cw.getRemB();
        }
        if (ROR_greater(xa, 1.5, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47078, _mut47079, _mut47080, _mut47081, _mut47082)) {
            // Accuracy suffers between 1.5 and PI/2
            final double pi2a = 1.5707963267948966;
            final double pi2b = 6.123233995736766E-17;
            final double a = AOR_minus(pi2a, xa, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47083, _mut47084, _mut47085, _mut47086);
            double b = -(AOR_plus(AOR_minus(a, pi2a, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47087, _mut47088, _mut47089, _mut47090), xa, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47091, _mut47092, _mut47093, _mut47094));
            b += AOR_minus(pi2b, xb, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47095, _mut47096, _mut47097, _mut47098);
            xa = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47099, _mut47100, _mut47101, _mut47102);
            xb = -(AOR_minus(AOR_minus(xa, a, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47103, _mut47104, _mut47105, _mut47106), b, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47107, _mut47108, _mut47109, _mut47110));
            quadrant ^= 1;
            negative ^= true;
        }
        double result;
        if (ROR_equals((quadrant & 1), 0, "org.apache.commons.math3.util.FastMath.tan_2442", _mut47111, _mut47112, _mut47113, _mut47114, _mut47115)) {
            result = tanQ(xa, xb, false);
        } else {
            result = -tanQ(xa, xb, true);
        }
        if (negative) {
            result = -result;
        }
        return result;
    }

    /**
     * Arctangent function
     *  @param x a number
     *  @return atan(x)
     */
    public static double atan(double x) {
        return atan(x, 0.0, false);
    }

    /**
     * Internal helper function to compute arctangent.
     * @param xa number from which arctangent is requested
     * @param xb extra bits for x (may be 0.0)
     * @param leftPlane if true, result angle must be put in the left half plane
     * @return atan(xa + xb) (or angle shifted by {@code PI} if leftPlane is true)
     */
    private static double atan(double xa, double xb, boolean leftPlane) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.atan_2528");
        if (ROR_equals(xa, 0.0, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47116, _mut47117, _mut47118, _mut47119, _mut47120)) {
            // Matches +/- 0.0; return correct sign
            return leftPlane ? copySign(Math.PI, xa) : xa;
        }
        final boolean negate;
        if (ROR_less(xa, 0, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47121, _mut47122, _mut47123, _mut47124, _mut47125)) {
            // negative
            xa = -xa;
            xb = -xb;
            negate = true;
        } else {
            negate = false;
        }
        if (ROR_greater(xa, 1.633123935319537E16, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47126, _mut47127, _mut47128, _mut47129, _mut47130)) {
            // Very large input
            return (negate ^ leftPlane) ? (AOR_multiply(-Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47135, _mut47136, _mut47137, _mut47138)) : (AOR_multiply(Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47131, _mut47132, _mut47133, _mut47134));
        }
        /* Estimate the closest tabulated arctan value, compute eps = xa-tangentTable */
        final int idx;
        if (ROR_less(xa, 1, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47139, _mut47140, _mut47141, _mut47142, _mut47143)) {
            idx = (int) (AOR_plus((AOR_multiply((AOR_plus(AOR_multiply(AOR_multiply(-1.7168146928204136, xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47168, _mut47169, _mut47170, _mut47171), xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47172, _mut47173, _mut47174, _mut47175), 8.0, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47176, _mut47177, _mut47178, _mut47179)), xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47180, _mut47181, _mut47182, _mut47183)), 0.5, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47184, _mut47185, _mut47186, _mut47187));
        } else {
            final double oneOverXa = AOR_divide(1, xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47144, _mut47145, _mut47146, _mut47147);
            idx = (int) (AOR_plus(-(AOR_multiply((AOR_plus(AOR_multiply(AOR_multiply(-1.7168146928204136, oneOverXa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47148, _mut47149, _mut47150, _mut47151), oneOverXa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47152, _mut47153, _mut47154, _mut47155), 8.0, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47156, _mut47157, _mut47158, _mut47159)), oneOverXa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47160, _mut47161, _mut47162, _mut47163)), 13.07, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47164, _mut47165, _mut47166, _mut47167));
        }
        final double ttA = TANGENT_TABLE_A[idx];
        final double ttB = TANGENT_TABLE_B[idx];
        double epsA = AOR_minus(xa, ttA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47188, _mut47189, _mut47190, _mut47191);
        double epsB = -(AOR_plus(AOR_minus(epsA, xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47192, _mut47193, _mut47194, _mut47195), ttA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47196, _mut47197, _mut47198, _mut47199));
        epsB += AOR_minus(xb, ttB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47200, _mut47201, _mut47202, _mut47203);
        double temp = AOR_plus(epsA, epsB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47204, _mut47205, _mut47206, _mut47207);
        epsB = -(AOR_minus(AOR_minus(temp, epsA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47208, _mut47209, _mut47210, _mut47211), epsB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47212, _mut47213, _mut47214, _mut47215));
        epsA = temp;
        /* Compute eps = eps / (1.0 + xa*tangent) */
        temp = AOR_multiply(xa, HEX_40000000, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47216, _mut47217, _mut47218, _mut47219);
        double ya = AOR_minus(AOR_plus(xa, temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47220, _mut47221, _mut47222, _mut47223), temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47224, _mut47225, _mut47226, _mut47227);
        double yb = AOR_minus(AOR_plus(xb, xa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47228, _mut47229, _mut47230, _mut47231), ya, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47232, _mut47233, _mut47234, _mut47235);
        xa = ya;
        xb += yb;
        // if (idx > 8 || idx == 0)
        if (ROR_equals(idx, 0, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47236, _mut47237, _mut47238, _mut47239, _mut47240)) {
            // double denom = 1.0 / (1.0 + xa*tangentTableA[idx] + xb*tangentTableA[idx] + xa*tangentTableB[idx] + xb*tangentTableB[idx]);
            final double denom = AOR_divide(1d, (AOR_plus(1d, AOR_multiply((AOR_plus(xa, xb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47373, _mut47374, _mut47375, _mut47376)), (AOR_plus(ttA, ttB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47377, _mut47378, _mut47379, _mut47380)), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47381, _mut47382, _mut47383, _mut47384), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47385, _mut47386, _mut47387, _mut47388)), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47389, _mut47390, _mut47391, _mut47392);
            // double denom = 1.0 / (1.0 + xa*tangentTableA[idx]);
            ya = AOR_multiply(epsA, denom, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47393, _mut47394, _mut47395, _mut47396);
            yb = AOR_multiply(epsB, denom, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47397, _mut47398, _mut47399, _mut47400);
        } else {
            double temp2 = AOR_multiply(xa, ttA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47241, _mut47242, _mut47243, _mut47244);
            double za = AOR_plus(1d, temp2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47245, _mut47246, _mut47247, _mut47248);
            double zb = -(AOR_minus(AOR_minus(za, 1d, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47249, _mut47250, _mut47251, _mut47252), temp2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47253, _mut47254, _mut47255, _mut47256));
            temp2 = AOR_plus(AOR_multiply(xb, ttA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47257, _mut47258, _mut47259, _mut47260), AOR_multiply(xa, ttB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47261, _mut47262, _mut47263, _mut47264), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47265, _mut47266, _mut47267, _mut47268);
            temp = AOR_plus(za, temp2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47269, _mut47270, _mut47271, _mut47272);
            zb += -(AOR_minus(AOR_minus(temp, za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47273, _mut47274, _mut47275, _mut47276), temp2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47277, _mut47278, _mut47279, _mut47280));
            za = temp;
            zb += AOR_multiply(xb, ttB, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47281, _mut47282, _mut47283, _mut47284);
            ya = AOR_divide(epsA, za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47285, _mut47286, _mut47287, _mut47288);
            temp = AOR_multiply(ya, HEX_40000000, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47289, _mut47290, _mut47291, _mut47292);
            final double yaa = AOR_minus((AOR_plus(ya, temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47293, _mut47294, _mut47295, _mut47296)), temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47297, _mut47298, _mut47299, _mut47300);
            final double yab = AOR_minus(ya, yaa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47301, _mut47302, _mut47303, _mut47304);
            temp = AOR_multiply(za, HEX_40000000, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47305, _mut47306, _mut47307, _mut47308);
            final double zaa = AOR_minus((AOR_plus(za, temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47309, _mut47310, _mut47311, _mut47312)), temp, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47313, _mut47314, _mut47315, _mut47316);
            final double zab = AOR_minus(za, zaa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47317, _mut47318, _mut47319, _mut47320);
            /* Correct for rounding in division */
            yb = AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(epsA, AOR_multiply(yaa, zaa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47321, _mut47322, _mut47323, _mut47324), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47325, _mut47326, _mut47327, _mut47328), AOR_multiply(yaa, zab, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47329, _mut47330, _mut47331, _mut47332), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47333, _mut47334, _mut47335, _mut47336), AOR_multiply(yab, zaa, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47337, _mut47338, _mut47339, _mut47340), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47341, _mut47342, _mut47343, _mut47344), AOR_multiply(yab, zab, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47345, _mut47346, _mut47347, _mut47348), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47349, _mut47350, _mut47351, _mut47352)), za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47353, _mut47354, _mut47355, _mut47356);
            yb += AOR_divide(AOR_divide(AOR_multiply(-epsA, zb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47357, _mut47358, _mut47359, _mut47360), za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47361, _mut47362, _mut47363, _mut47364), za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47365, _mut47366, _mut47367, _mut47368);
            yb += AOR_divide(epsB, za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47369, _mut47370, _mut47371, _mut47372);
        }
        epsA = ya;
        epsB = yb;
        /* Evaluate polynomial */
        final double epsA2 = AOR_multiply(epsA, epsA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47401, _mut47402, _mut47403, _mut47404);
        yb = 0.07490822288864472;
        yb = AOR_minus(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47405, _mut47406, _mut47407, _mut47408), 0.09088450866185192, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47409, _mut47410, _mut47411, _mut47412);
        yb = AOR_plus(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47413, _mut47414, _mut47415, _mut47416), 0.11111095942313305, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47417, _mut47418, _mut47419, _mut47420);
        yb = AOR_minus(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47421, _mut47422, _mut47423, _mut47424), 0.1428571423679182, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47425, _mut47426, _mut47427, _mut47428);
        yb = AOR_plus(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47429, _mut47430, _mut47431, _mut47432), 0.19999999999923582, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47433, _mut47434, _mut47435, _mut47436);
        yb = AOR_minus(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47437, _mut47438, _mut47439, _mut47440), 0.33333333333333287, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47441, _mut47442, _mut47443, _mut47444);
        yb = AOR_multiply(AOR_multiply(yb, epsA2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47445, _mut47446, _mut47447, _mut47448), epsA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47449, _mut47450, _mut47451, _mut47452);
        ya = epsA;
        temp = AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47453, _mut47454, _mut47455, _mut47456);
        yb = -(AOR_minus(AOR_minus(temp, ya, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47457, _mut47458, _mut47459, _mut47460), yb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47461, _mut47462, _mut47463, _mut47464));
        ya = temp;
        /* Add in effect of epsB.   atan'(x) = 1/(1+x^2) */
        yb += AOR_divide(epsB, (AOR_plus(1d, AOR_multiply(epsA, epsA, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47465, _mut47466, _mut47467, _mut47468), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47469, _mut47470, _mut47471, _mut47472)), "org.apache.commons.math3.util.FastMath.atan_2528", _mut47473, _mut47474, _mut47475, _mut47476);
        final double eighths = EIGHTHS[idx];
        // result = yb + eighths[idx] + ya;
        double za = AOR_plus(eighths, ya, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47477, _mut47478, _mut47479, _mut47480);
        double zb = -(AOR_minus(AOR_minus(za, eighths, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47481, _mut47482, _mut47483, _mut47484), ya, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47485, _mut47486, _mut47487, _mut47488));
        temp = AOR_plus(za, yb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47489, _mut47490, _mut47491, _mut47492);
        zb += -(AOR_minus(AOR_minus(temp, za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47493, _mut47494, _mut47495, _mut47496), yb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47497, _mut47498, _mut47499, _mut47500));
        za = temp;
        double result = AOR_plus(za, zb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47501, _mut47502, _mut47503, _mut47504);
        if (leftPlane) {
            // Result is in the left plane
            final double resultb = -(AOR_minus(AOR_minus(result, za, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47505, _mut47506, _mut47507, _mut47508), zb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47509, _mut47510, _mut47511, _mut47512));
            final double pia = AOR_multiply(1.5707963267948966, 2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47513, _mut47514, _mut47515, _mut47516);
            final double pib = AOR_multiply(6.123233995736766E-17, 2, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47517, _mut47518, _mut47519, _mut47520);
            za = AOR_minus(pia, result, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47521, _mut47522, _mut47523, _mut47524);
            zb = -(AOR_plus(AOR_minus(za, pia, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47525, _mut47526, _mut47527, _mut47528), result, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47529, _mut47530, _mut47531, _mut47532));
            zb += AOR_minus(pib, resultb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47533, _mut47534, _mut47535, _mut47536);
            result = AOR_plus(za, zb, "org.apache.commons.math3.util.FastMath.atan_2528", _mut47537, _mut47538, _mut47539, _mut47540);
        }
        if (negate ^ leftPlane) {
            result = -result;
        }
        return result;
    }

    /**
     * Two arguments arctangent function
     * @param y ordinate
     * @param x abscissa
     * @return phase angle of point (x,y) between {@code -PI} and {@code PI}
     */
    public static double atan2(double y, double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.atan2_2681");
        if ((_mut47551 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47541, _mut47542, _mut47543, _mut47544, _mut47545) && ROR_not_equals(y, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47546, _mut47547, _mut47548, _mut47549, _mut47550)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47541, _mut47542, _mut47543, _mut47544, _mut47545) || ROR_not_equals(y, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47546, _mut47547, _mut47548, _mut47549, _mut47550)))) {
            return Double.NaN;
        }
        if (ROR_equals(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47552, _mut47553, _mut47554, _mut47555, _mut47556)) {
            final double result = AOR_multiply(x, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47557, _mut47558, _mut47559, _mut47560);
            final double invx = AOR_divide(1d, x, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47561, _mut47562, _mut47563, _mut47564);
            final double invy = AOR_divide(1d, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47565, _mut47566, _mut47567, _mut47568);
            if (ROR_equals(invx, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47569, _mut47570, _mut47571, _mut47572, _mut47573)) {
                // X is infinite
                if (ROR_greater(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47574, _mut47575, _mut47576, _mut47577, _mut47578)) {
                    // return +/- 0.0
                    return y;
                } else {
                    return copySign(Math.PI, y);
                }
            }
            if ((_mut47589 ? (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47579, _mut47580, _mut47581, _mut47582, _mut47583) && ROR_less(invx, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47584, _mut47585, _mut47586, _mut47587, _mut47588)) : (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47579, _mut47580, _mut47581, _mut47582, _mut47583) || ROR_less(invx, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47584, _mut47585, _mut47586, _mut47587, _mut47588)))) {
                if ((_mut47600 ? (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47590, _mut47591, _mut47592, _mut47593, _mut47594) && ROR_less(invy, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47595, _mut47596, _mut47597, _mut47598, _mut47599)) : (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47590, _mut47591, _mut47592, _mut47593, _mut47594) || ROR_less(invy, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47595, _mut47596, _mut47597, _mut47598, _mut47599)))) {
                    return -Math.PI;
                } else {
                    return Math.PI;
                }
            } else {
                return result;
            }
        }
        if (ROR_equals(y, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47601, _mut47602, _mut47603, _mut47604, _mut47605)) {
            if (ROR_equals(x, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47606, _mut47607, _mut47608, _mut47609, _mut47610)) {
                return AOR_multiply(Math.PI, F_1_4, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47611, _mut47612, _mut47613, _mut47614);
            }
            if (ROR_equals(x, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47615, _mut47616, _mut47617, _mut47618, _mut47619)) {
                return AOR_multiply(Math.PI, F_3_4, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47620, _mut47621, _mut47622, _mut47623);
            }
            return AOR_multiply(Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47624, _mut47625, _mut47626, _mut47627);
        }
        if (ROR_equals(y, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47628, _mut47629, _mut47630, _mut47631, _mut47632)) {
            if (ROR_equals(x, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47633, _mut47634, _mut47635, _mut47636, _mut47637)) {
                return AOR_multiply(-Math.PI, F_1_4, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47638, _mut47639, _mut47640, _mut47641);
            }
            if (ROR_equals(x, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47642, _mut47643, _mut47644, _mut47645, _mut47646)) {
                return AOR_multiply(-Math.PI, F_3_4, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47647, _mut47648, _mut47649, _mut47650);
            }
            return AOR_multiply(-Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47651, _mut47652, _mut47653, _mut47654);
        }
        if (ROR_equals(x, Double.POSITIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47655, _mut47656, _mut47657, _mut47658, _mut47659)) {
            if ((_mut47674 ? (ROR_greater(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47660, _mut47661, _mut47662, _mut47663, _mut47664) && ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47665, _mut47666, _mut47667, _mut47668), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47669, _mut47670, _mut47671, _mut47672, _mut47673)) : (ROR_greater(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47660, _mut47661, _mut47662, _mut47663, _mut47664) || ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47665, _mut47666, _mut47667, _mut47668), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47669, _mut47670, _mut47671, _mut47672, _mut47673)))) {
                return 0d;
            }
            if ((_mut47689 ? (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47675, _mut47676, _mut47677, _mut47678, _mut47679) && ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47680, _mut47681, _mut47682, _mut47683), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47684, _mut47685, _mut47686, _mut47687, _mut47688)) : (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47675, _mut47676, _mut47677, _mut47678, _mut47679) || ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47680, _mut47681, _mut47682, _mut47683), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47684, _mut47685, _mut47686, _mut47687, _mut47688)))) {
                return -0d;
            }
        }
        if (ROR_equals(x, Double.NEGATIVE_INFINITY, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47690, _mut47691, _mut47692, _mut47693, _mut47694)) {
            if ((_mut47709 ? (ROR_greater(y, 0.0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47695, _mut47696, _mut47697, _mut47698, _mut47699) && ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47700, _mut47701, _mut47702, _mut47703), 0.0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47704, _mut47705, _mut47706, _mut47707, _mut47708)) : (ROR_greater(y, 0.0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47695, _mut47696, _mut47697, _mut47698, _mut47699) || ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47700, _mut47701, _mut47702, _mut47703), 0.0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47704, _mut47705, _mut47706, _mut47707, _mut47708)))) {
                return Math.PI;
            }
            if ((_mut47724 ? (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47710, _mut47711, _mut47712, _mut47713, _mut47714) && ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47715, _mut47716, _mut47717, _mut47718), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47719, _mut47720, _mut47721, _mut47722, _mut47723)) : (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47710, _mut47711, _mut47712, _mut47713, _mut47714) || ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47715, _mut47716, _mut47717, _mut47718), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47719, _mut47720, _mut47721, _mut47722, _mut47723)))) {
                return -Math.PI;
            }
        }
        if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47725, _mut47726, _mut47727, _mut47728, _mut47729)) {
            if ((_mut47744 ? (ROR_greater(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47730, _mut47731, _mut47732, _mut47733, _mut47734) && ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47735, _mut47736, _mut47737, _mut47738), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47739, _mut47740, _mut47741, _mut47742, _mut47743)) : (ROR_greater(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47730, _mut47731, _mut47732, _mut47733, _mut47734) || ROR_greater(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47735, _mut47736, _mut47737, _mut47738), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47739, _mut47740, _mut47741, _mut47742, _mut47743)))) {
                return AOR_multiply(Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47745, _mut47746, _mut47747, _mut47748);
            }
            if ((_mut47763 ? (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47749, _mut47750, _mut47751, _mut47752, _mut47753) && ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47754, _mut47755, _mut47756, _mut47757), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47758, _mut47759, _mut47760, _mut47761, _mut47762)) : (ROR_less(y, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47749, _mut47750, _mut47751, _mut47752, _mut47753) || ROR_less(AOR_divide(1, y, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47754, _mut47755, _mut47756, _mut47757), 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47758, _mut47759, _mut47760, _mut47761, _mut47762)))) {
                return AOR_multiply(-Math.PI, F_1_2, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47764, _mut47765, _mut47766, _mut47767);
            }
        }
        // Compute ratio r = y/x
        final double r = AOR_divide(y, x, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47768, _mut47769, _mut47770, _mut47771);
        if (Double.isInfinite(r)) {
            // bypass calculations that can create NaN
            return atan(r, 0, ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47772, _mut47773, _mut47774, _mut47775, _mut47776));
        }
        double ra = doubleHighPart(r);
        double rb = AOR_minus(r, ra, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47777, _mut47778, _mut47779, _mut47780);
        // Split x
        final double xa = doubleHighPart(x);
        final double xb = AOR_minus(x, xa, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47781, _mut47782, _mut47783, _mut47784);
        rb += AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(y, AOR_multiply(ra, xa, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47785, _mut47786, _mut47787, _mut47788), "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47789, _mut47790, _mut47791, _mut47792), AOR_multiply(ra, xb, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47793, _mut47794, _mut47795, _mut47796), "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47797, _mut47798, _mut47799, _mut47800), AOR_multiply(rb, xa, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47801, _mut47802, _mut47803, _mut47804), "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47805, _mut47806, _mut47807, _mut47808), AOR_multiply(rb, xb, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47809, _mut47810, _mut47811, _mut47812), "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47813, _mut47814, _mut47815, _mut47816)), x, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47817, _mut47818, _mut47819, _mut47820);
        final double temp = AOR_plus(ra, rb, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47821, _mut47822, _mut47823, _mut47824);
        rb = -(AOR_minus(AOR_minus(temp, ra, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47825, _mut47826, _mut47827, _mut47828), rb, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47829, _mut47830, _mut47831, _mut47832));
        ra = temp;
        if (ROR_equals(ra, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47833, _mut47834, _mut47835, _mut47836, _mut47837)) {
            // Fix up the sign so atan works correctly
            ra = copySign(0d, y);
        }
        // Call atan
        final double result = atan(ra, rb, ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.atan2_2681", _mut47838, _mut47839, _mut47840, _mut47841, _mut47842));
        return result;
    }

    /**
     * Compute the arc sine of a number.
     * @param x number on which evaluation is done
     * @return arc sine of x
     */
    public static double asin(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.asin_2802");
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47843, _mut47844, _mut47845, _mut47846, _mut47847)) {
            return Double.NaN;
        }
        if ((_mut47858 ? (ROR_greater(x, 1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47848, _mut47849, _mut47850, _mut47851, _mut47852) && ROR_less(x, -1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47853, _mut47854, _mut47855, _mut47856, _mut47857)) : (ROR_greater(x, 1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47848, _mut47849, _mut47850, _mut47851, _mut47852) || ROR_less(x, -1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47853, _mut47854, _mut47855, _mut47856, _mut47857)))) {
            return Double.NaN;
        }
        if (ROR_equals(x, 1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47859, _mut47860, _mut47861, _mut47862, _mut47863)) {
            return AOR_divide(Math.PI, 2.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47864, _mut47865, _mut47866, _mut47867);
        }
        if (ROR_equals(x, -1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47868, _mut47869, _mut47870, _mut47871, _mut47872)) {
            return AOR_divide(-Math.PI, 2.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47873, _mut47874, _mut47875, _mut47876);
        }
        if (ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47877, _mut47878, _mut47879, _mut47880, _mut47881)) {
            // Matches +/- 0.0; return correct sign
            return x;
        }
        /* Split x */
        double temp = AOR_multiply(x, HEX_40000000, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47882, _mut47883, _mut47884, _mut47885);
        final double xa = AOR_minus(AOR_plus(x, temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47886, _mut47887, _mut47888, _mut47889), temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47890, _mut47891, _mut47892, _mut47893);
        final double xb = AOR_minus(x, xa, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47894, _mut47895, _mut47896, _mut47897);
        /* Square it */
        double ya = AOR_multiply(xa, xa, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47898, _mut47899, _mut47900, _mut47901);
        double yb = AOR_plus(AOR_multiply(AOR_multiply(xa, xb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47902, _mut47903, _mut47904, _mut47905), 2.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47906, _mut47907, _mut47908, _mut47909), AOR_multiply(xb, xb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47910, _mut47911, _mut47912, _mut47913), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47914, _mut47915, _mut47916, _mut47917);
        /* Subtract from 1 */
        ya = -ya;
        yb = -yb;
        double za = AOR_plus(1.0, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47918, _mut47919, _mut47920, _mut47921);
        double zb = -(AOR_minus(AOR_minus(za, 1.0, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47922, _mut47923, _mut47924, _mut47925), ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47926, _mut47927, _mut47928, _mut47929));
        temp = AOR_plus(za, yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47930, _mut47931, _mut47932, _mut47933);
        zb += -(AOR_minus(AOR_minus(temp, za, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47934, _mut47935, _mut47936, _mut47937), yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47938, _mut47939, _mut47940, _mut47941));
        za = temp;
        /* Square root */
        double y;
        y = sqrt(za);
        temp = AOR_multiply(y, HEX_40000000, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47942, _mut47943, _mut47944, _mut47945);
        ya = AOR_minus(AOR_plus(y, temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47946, _mut47947, _mut47948, _mut47949), temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47950, _mut47951, _mut47952, _mut47953);
        yb = AOR_minus(y, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47954, _mut47955, _mut47956, _mut47957);
        /* Extend precision of sqrt */
        yb += AOR_divide((AOR_minus(AOR_minus(AOR_minus(za, AOR_multiply(ya, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47958, _mut47959, _mut47960, _mut47961), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47962, _mut47963, _mut47964, _mut47965), AOR_multiply(AOR_multiply(2, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47966, _mut47967, _mut47968, _mut47969), yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47970, _mut47971, _mut47972, _mut47973), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47974, _mut47975, _mut47976, _mut47977), AOR_multiply(yb, yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47978, _mut47979, _mut47980, _mut47981), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47982, _mut47983, _mut47984, _mut47985)), (AOR_multiply(2.0, y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47986, _mut47987, _mut47988, _mut47989)), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47990, _mut47991, _mut47992, _mut47993);
        /* Contribution of zb to sqrt */
        double dx = AOR_divide(zb, (AOR_multiply(2.0, y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut47994, _mut47995, _mut47996, _mut47997)), "org.apache.commons.math3.util.FastMath.asin_2802", _mut47998, _mut47999, _mut48000, _mut48001);
        // Compute ratio r = x/y
        double r = AOR_divide(x, y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48002, _mut48003, _mut48004, _mut48005);
        temp = AOR_multiply(r, HEX_40000000, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48006, _mut48007, _mut48008, _mut48009);
        double ra = AOR_minus(AOR_plus(r, temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48010, _mut48011, _mut48012, _mut48013), temp, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48014, _mut48015, _mut48016, _mut48017);
        double rb = AOR_minus(r, ra, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48018, _mut48019, _mut48020, _mut48021);
        // Correct for rounding in division
        rb += AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(x, AOR_multiply(ra, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48022, _mut48023, _mut48024, _mut48025), "org.apache.commons.math3.util.FastMath.asin_2802", _mut48026, _mut48027, _mut48028, _mut48029), AOR_multiply(ra, yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48030, _mut48031, _mut48032, _mut48033), "org.apache.commons.math3.util.FastMath.asin_2802", _mut48034, _mut48035, _mut48036, _mut48037), AOR_multiply(rb, ya, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48038, _mut48039, _mut48040, _mut48041), "org.apache.commons.math3.util.FastMath.asin_2802", _mut48042, _mut48043, _mut48044, _mut48045), AOR_multiply(rb, yb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48046, _mut48047, _mut48048, _mut48049), "org.apache.commons.math3.util.FastMath.asin_2802", _mut48050, _mut48051, _mut48052, _mut48053)), y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48054, _mut48055, _mut48056, _mut48057);
        // Add in effect additional bits of sqrt.
        rb += AOR_divide(AOR_divide(AOR_multiply(-x, dx, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48058, _mut48059, _mut48060, _mut48061), y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48062, _mut48063, _mut48064, _mut48065), y, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48066, _mut48067, _mut48068, _mut48069);
        temp = AOR_plus(ra, rb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48070, _mut48071, _mut48072, _mut48073);
        rb = -(AOR_minus(AOR_minus(temp, ra, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48074, _mut48075, _mut48076, _mut48077), rb, "org.apache.commons.math3.util.FastMath.asin_2802", _mut48078, _mut48079, _mut48080, _mut48081));
        ra = temp;
        return atan(ra, rb, false);
    }

    /**
     * Compute the arc cosine of a number.
     * @param x number on which evaluation is done
     * @return arc cosine of x
     */
    public static double acos(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.acos_2878");
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48082, _mut48083, _mut48084, _mut48085, _mut48086)) {
            return Double.NaN;
        }
        if ((_mut48097 ? (ROR_greater(x, 1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48087, _mut48088, _mut48089, _mut48090, _mut48091) && ROR_less(x, -1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48092, _mut48093, _mut48094, _mut48095, _mut48096)) : (ROR_greater(x, 1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48087, _mut48088, _mut48089, _mut48090, _mut48091) || ROR_less(x, -1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48092, _mut48093, _mut48094, _mut48095, _mut48096)))) {
            return Double.NaN;
        }
        if (ROR_equals(x, -1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48098, _mut48099, _mut48100, _mut48101, _mut48102)) {
            return Math.PI;
        }
        if (ROR_equals(x, 1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48103, _mut48104, _mut48105, _mut48106, _mut48107)) {
            return 0.0;
        }
        if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48108, _mut48109, _mut48110, _mut48111, _mut48112)) {
            return AOR_divide(Math.PI, 2.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48113, _mut48114, _mut48115, _mut48116);
        }
        /* Split x */
        double temp = AOR_multiply(x, HEX_40000000, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48117, _mut48118, _mut48119, _mut48120);
        final double xa = AOR_minus(AOR_plus(x, temp, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48121, _mut48122, _mut48123, _mut48124), temp, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48125, _mut48126, _mut48127, _mut48128);
        final double xb = AOR_minus(x, xa, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48129, _mut48130, _mut48131, _mut48132);
        /* Square it */
        double ya = AOR_multiply(xa, xa, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48133, _mut48134, _mut48135, _mut48136);
        double yb = AOR_plus(AOR_multiply(AOR_multiply(xa, xb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48137, _mut48138, _mut48139, _mut48140), 2.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48141, _mut48142, _mut48143, _mut48144), AOR_multiply(xb, xb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48145, _mut48146, _mut48147, _mut48148), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48149, _mut48150, _mut48151, _mut48152);
        /* Subtract from 1 */
        ya = -ya;
        yb = -yb;
        double za = AOR_plus(1.0, ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48153, _mut48154, _mut48155, _mut48156);
        double zb = -(AOR_minus(AOR_minus(za, 1.0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48157, _mut48158, _mut48159, _mut48160), ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48161, _mut48162, _mut48163, _mut48164));
        temp = AOR_plus(za, yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48165, _mut48166, _mut48167, _mut48168);
        zb += -(AOR_minus(AOR_minus(temp, za, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48169, _mut48170, _mut48171, _mut48172), yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48173, _mut48174, _mut48175, _mut48176));
        za = temp;
        /* Square root */
        double y = sqrt(za);
        temp = AOR_multiply(y, HEX_40000000, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48177, _mut48178, _mut48179, _mut48180);
        ya = AOR_minus(AOR_plus(y, temp, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48181, _mut48182, _mut48183, _mut48184), temp, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48185, _mut48186, _mut48187, _mut48188);
        yb = AOR_minus(y, ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48189, _mut48190, _mut48191, _mut48192);
        /* Extend precision of sqrt */
        yb += AOR_divide((AOR_minus(AOR_minus(AOR_minus(za, AOR_multiply(ya, ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48193, _mut48194, _mut48195, _mut48196), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48197, _mut48198, _mut48199, _mut48200), AOR_multiply(AOR_multiply(2, ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48201, _mut48202, _mut48203, _mut48204), yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48205, _mut48206, _mut48207, _mut48208), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48209, _mut48210, _mut48211, _mut48212), AOR_multiply(yb, yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48213, _mut48214, _mut48215, _mut48216), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48217, _mut48218, _mut48219, _mut48220)), (AOR_multiply(2.0, y, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48221, _mut48222, _mut48223, _mut48224)), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48225, _mut48226, _mut48227, _mut48228);
        /* Contribution of zb to sqrt */
        yb += AOR_divide(zb, (AOR_multiply(2.0, y, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48229, _mut48230, _mut48231, _mut48232)), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48233, _mut48234, _mut48235, _mut48236);
        y = AOR_plus(ya, yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48237, _mut48238, _mut48239, _mut48240);
        yb = -(AOR_minus(AOR_minus(y, ya, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48241, _mut48242, _mut48243, _mut48244), yb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48245, _mut48246, _mut48247, _mut48248));
        // Compute ratio r = y/x
        double r = AOR_divide(y, x, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48249, _mut48250, _mut48251, _mut48252);
        // Did r overflow?
        if (Double.isInfinite(r)) {
            // so return the appropriate value
            return AOR_divide(Math.PI, 2, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48253, _mut48254, _mut48255, _mut48256);
        }
        double ra = doubleHighPart(r);
        double rb = AOR_minus(r, ra, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48257, _mut48258, _mut48259, _mut48260);
        // Correct for rounding in division
        rb += AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_minus(y, AOR_multiply(ra, xa, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48261, _mut48262, _mut48263, _mut48264), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48265, _mut48266, _mut48267, _mut48268), AOR_multiply(ra, xb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48269, _mut48270, _mut48271, _mut48272), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48273, _mut48274, _mut48275, _mut48276), AOR_multiply(rb, xa, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48277, _mut48278, _mut48279, _mut48280), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48281, _mut48282, _mut48283, _mut48284), AOR_multiply(rb, xb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48285, _mut48286, _mut48287, _mut48288), "org.apache.commons.math3.util.FastMath.acos_2878", _mut48289, _mut48290, _mut48291, _mut48292)), x, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48293, _mut48294, _mut48295, _mut48296);
        // Add in effect additional bits of sqrt.
        rb += AOR_divide(yb, x, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48297, _mut48298, _mut48299, _mut48300);
        temp = AOR_plus(ra, rb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48301, _mut48302, _mut48303, _mut48304);
        rb = -(AOR_minus(AOR_minus(temp, ra, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48305, _mut48306, _mut48307, _mut48308), rb, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48309, _mut48310, _mut48311, _mut48312));
        ra = temp;
        return atan(ra, rb, ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.acos_2878", _mut48313, _mut48314, _mut48315, _mut48316, _mut48317));
    }

    /**
     * Compute the cubic root of a number.
     * @param x number on which evaluation is done
     * @return cubic root of x
     */
    public static double cbrt(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.cbrt_2960");
        /* Convert input double to bits */
        long inbits = Double.doubleToRawLongBits(x);
        int exponent = AOR_minus((int) ((inbits >> 52) & 0x7ff), 1023, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48318, _mut48319, _mut48320, _mut48321);
        boolean subnormal = false;
        if (ROR_equals(exponent, -1023, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48322, _mut48323, _mut48324, _mut48325, _mut48326)) {
            if (ROR_equals(x, 0, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48327, _mut48328, _mut48329, _mut48330, _mut48331)) {
                return x;
            }
            /* Subnormal, so normalize */
            subnormal = true;
            // 2^54
            x *= 1.8014398509481984E16;
            inbits = Double.doubleToRawLongBits(x);
            exponent = AOR_minus((int) ((inbits >> 52) & 0x7ff), 1023, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48332, _mut48333, _mut48334, _mut48335);
        }
        if (ROR_equals(exponent, 1024, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48336, _mut48337, _mut48338, _mut48339, _mut48340)) {
            // Nan or infinity.  Don't care which.
            return x;
        }
        /* Divide the exponent by 3 */
        int exp3 = AOR_divide(exponent, 3, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48341, _mut48342, _mut48343, _mut48344);
        /* p2 will be the nearest power of 2 to x with its exponent divided by 3 */
        double p2 = Double.longBitsToDouble((inbits & 0x8000000000000000L) | (long) (((AOR_plus(exp3, 1023, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48345, _mut48346, _mut48347, _mut48348)) & 0x7ff)) << 52);
        /* This will be a number between 1 and 2 */
        final double mant = Double.longBitsToDouble((inbits & 0x000fffffffffffffL) | 0x3ff0000000000000L);
        /* Estimate the cube root of mant by polynomial */
        double est = -0.010714690733195933;
        est = AOR_plus(AOR_multiply(est, mant, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48349, _mut48350, _mut48351, _mut48352), 0.0875862700108075, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48353, _mut48354, _mut48355, _mut48356);
        est = AOR_plus(AOR_multiply(est, mant, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48357, _mut48358, _mut48359, _mut48360), -0.3058015757857271, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48361, _mut48362, _mut48363, _mut48364);
        est = AOR_plus(AOR_multiply(est, mant, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48365, _mut48366, _mut48367, _mut48368), 0.7249995199969751, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48369, _mut48370, _mut48371, _mut48372);
        est = AOR_plus(AOR_multiply(est, mant, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48373, _mut48374, _mut48375, _mut48376), 0.5039018405998233, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48377, _mut48378, _mut48379, _mut48380);
        est *= CBRTTWO[AOR_plus(AOR_remainder(exponent, 3, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48381, _mut48382, _mut48383, _mut48384), 2, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48385, _mut48386, _mut48387, _mut48388)];
        // Scale down x for the purpose of doing newtons method.  This avoids over/under flows.
        final double xs = AOR_divide(x, (AOR_multiply(AOR_multiply(p2, p2, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48389, _mut48390, _mut48391, _mut48392), p2, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48393, _mut48394, _mut48395, _mut48396)), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48397, _mut48398, _mut48399, _mut48400);
        est += AOR_divide((AOR_minus(xs, AOR_multiply(AOR_multiply(est, est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48401, _mut48402, _mut48403, _mut48404), est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48405, _mut48406, _mut48407, _mut48408), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48409, _mut48410, _mut48411, _mut48412)), (AOR_multiply(AOR_multiply(3, est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48413, _mut48414, _mut48415, _mut48416), est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48417, _mut48418, _mut48419, _mut48420)), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48421, _mut48422, _mut48423, _mut48424);
        est += AOR_divide((AOR_minus(xs, AOR_multiply(AOR_multiply(est, est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48425, _mut48426, _mut48427, _mut48428), est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48429, _mut48430, _mut48431, _mut48432), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48433, _mut48434, _mut48435, _mut48436)), (AOR_multiply(AOR_multiply(3, est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48437, _mut48438, _mut48439, _mut48440), est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48441, _mut48442, _mut48443, _mut48444)), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48445, _mut48446, _mut48447, _mut48448);
        // Do one round of Newton's method in extended precision to get the last bit right.
        double temp = AOR_multiply(est, HEX_40000000, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48449, _mut48450, _mut48451, _mut48452);
        double ya = AOR_minus(AOR_plus(est, temp, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48453, _mut48454, _mut48455, _mut48456), temp, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48457, _mut48458, _mut48459, _mut48460);
        double yb = AOR_minus(est, ya, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48461, _mut48462, _mut48463, _mut48464);
        double za = AOR_multiply(ya, ya, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48465, _mut48466, _mut48467, _mut48468);
        double zb = AOR_plus(AOR_multiply(AOR_multiply(ya, yb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48469, _mut48470, _mut48471, _mut48472), 2.0, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48473, _mut48474, _mut48475, _mut48476), AOR_multiply(yb, yb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48477, _mut48478, _mut48479, _mut48480), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48481, _mut48482, _mut48483, _mut48484);
        temp = AOR_multiply(za, HEX_40000000, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48485, _mut48486, _mut48487, _mut48488);
        double temp2 = AOR_minus(AOR_plus(za, temp, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48489, _mut48490, _mut48491, _mut48492), temp, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48493, _mut48494, _mut48495, _mut48496);
        zb += AOR_minus(za, temp2, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48497, _mut48498, _mut48499, _mut48500);
        za = temp2;
        zb = AOR_plus(AOR_plus(AOR_multiply(za, yb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48501, _mut48502, _mut48503, _mut48504), AOR_multiply(ya, zb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48505, _mut48506, _mut48507, _mut48508), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48509, _mut48510, _mut48511, _mut48512), AOR_multiply(zb, yb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48513, _mut48514, _mut48515, _mut48516), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48517, _mut48518, _mut48519, _mut48520);
        za *= ya;
        double na = AOR_minus(xs, za, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48521, _mut48522, _mut48523, _mut48524);
        double nb = -(AOR_plus(AOR_minus(na, xs, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48525, _mut48526, _mut48527, _mut48528), za, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48529, _mut48530, _mut48531, _mut48532));
        nb -= zb;
        est += AOR_divide((AOR_plus(na, nb, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48533, _mut48534, _mut48535, _mut48536)), (AOR_multiply(AOR_multiply(3, est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48537, _mut48538, _mut48539, _mut48540), est, "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48541, _mut48542, _mut48543, _mut48544)), "org.apache.commons.math3.util.FastMath.cbrt_2960", _mut48545, _mut48546, _mut48547, _mut48548);
        /* Scale by a power of two, so this is exact. */
        est *= p2;
        if (subnormal) {
            // 2^-18
            est *= 3.814697265625E-6;
        }
        return est;
    }

    /**
     *  Convert degrees to radians, with error of less than 0.5 ULP
     *  @param x angle in degrees
     *  @return x converted into radians
     */
    public static double toRadians(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.toRadians_3045");
        if ((_mut48554 ? (Double.isInfinite(x) && ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48549, _mut48550, _mut48551, _mut48552, _mut48553)) : (Double.isInfinite(x) || ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48549, _mut48550, _mut48551, _mut48552, _mut48553)))) {
            // Matches +/- 0.0; return correct sign
            return x;
        }
        // These are PI/180 split into high and low order bits
        final double facta = 0.01745329052209854;
        final double factb = 1.997844754509471E-9;
        double xa = doubleHighPart(x);
        double xb = AOR_minus(x, xa, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48555, _mut48556, _mut48557, _mut48558);
        double result = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(xb, factb, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48559, _mut48560, _mut48561, _mut48562), AOR_multiply(xb, facta, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48563, _mut48564, _mut48565, _mut48566), "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48567, _mut48568, _mut48569, _mut48570), AOR_multiply(xa, factb, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48571, _mut48572, _mut48573, _mut48574), "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48575, _mut48576, _mut48577, _mut48578), AOR_multiply(xa, facta, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48579, _mut48580, _mut48581, _mut48582), "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48583, _mut48584, _mut48585, _mut48586);
        if (ROR_equals(result, 0, "org.apache.commons.math3.util.FastMath.toRadians_3045", _mut48587, _mut48588, _mut48589, _mut48590, _mut48591)) {
            // ensure correct sign if calculation underflows
            result *= x;
        }
        return result;
    }

    /**
     *  Convert radians to degrees, with error of less than 0.5 ULP
     *  @param x angle in radians
     *  @return x converted into degrees
     */
    public static double toDegrees(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.toDegrees_3070");
        if ((_mut48597 ? (Double.isInfinite(x) && ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48592, _mut48593, _mut48594, _mut48595, _mut48596)) : (Double.isInfinite(x) || ROR_equals(x, 0.0, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48592, _mut48593, _mut48594, _mut48595, _mut48596)))) {
            // Matches +/- 0.0; return correct sign
            return x;
        }
        // These are 180/PI split into high and low order bits
        final double facta = 57.2957763671875;
        final double factb = 3.145894820876798E-6;
        double xa = doubleHighPart(x);
        double xb = AOR_minus(x, xa, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48598, _mut48599, _mut48600, _mut48601);
        return AOR_plus(AOR_plus(AOR_plus(AOR_multiply(xb, factb, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48602, _mut48603, _mut48604, _mut48605), AOR_multiply(xb, facta, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48606, _mut48607, _mut48608, _mut48609), "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48610, _mut48611, _mut48612, _mut48613), AOR_multiply(xa, factb, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48614, _mut48615, _mut48616, _mut48617), "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48618, _mut48619, _mut48620, _mut48621), AOR_multiply(xa, facta, "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48622, _mut48623, _mut48624, _mut48625), "org.apache.commons.math3.util.FastMath.toDegrees_3070", _mut48626, _mut48627, _mut48628, _mut48629);
    }

    /**
     * Absolute value.
     * @param x number from which absolute value is requested
     * @return abs(x)
     */
    public static int abs(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.abs_3091");
        final int i = x >>> 31;
        return AOR_plus((x ^ (AOR_plus(~i, 1, "org.apache.commons.math3.util.FastMath.abs_3091", _mut48630, _mut48631, _mut48632, _mut48633))), i, "org.apache.commons.math3.util.FastMath.abs_3091", _mut48634, _mut48635, _mut48636, _mut48637);
    }

    /**
     * Absolute value.
     * @param x number from which absolute value is requested
     * @return abs(x)
     */
    public static long abs(final long x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.abs_3101");
        final long l = x >>> 63;
        // add around
        return AOR_plus((x ^ (AOR_plus(~l, 1, "org.apache.commons.math3.util.FastMath.abs_3101", _mut48638, _mut48639, _mut48640, _mut48641))), l, "org.apache.commons.math3.util.FastMath.abs_3101", _mut48642, _mut48643, _mut48644, _mut48645);
    }

    /**
     * Absolute value.
     * @param x number from which absolute value is requested
     * @return abs(x)
     */
    public static float abs(final float x) {
        return Float.intBitsToFloat(MASK_NON_SIGN_INT & Float.floatToRawIntBits(x));
    }

    /**
     * Absolute value.
     * @param x number from which absolute value is requested
     * @return abs(x)
     */
    public static double abs(double x) {
        return Double.longBitsToDouble(MASK_NON_SIGN_LONG & Double.doubleToRawLongBits(x));
    }

    /**
     * Compute least significant bit (Unit in Last Position) for a number.
     * @param x number from which ulp is requested
     * @return ulp(x)
     */
    public static double ulp(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.ulp_3133");
        if (Double.isInfinite(x)) {
            return Double.POSITIVE_INFINITY;
        }
        return abs(AOR_minus(x, Double.longBitsToDouble(Double.doubleToRawLongBits(x) ^ 1), "org.apache.commons.math3.util.FastMath.ulp_3133", _mut48646, _mut48647, _mut48648, _mut48649));
    }

    /**
     * Compute least significant bit (Unit in Last Position) for a number.
     * @param x number from which ulp is requested
     * @return ulp(x)
     */
    public static float ulp(float x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.ulp_3145");
        if (Float.isInfinite(x)) {
            return Float.POSITIVE_INFINITY;
        }
        return abs(AOR_minus(x, Float.intBitsToFloat(Float.floatToIntBits(x) ^ 1), "org.apache.commons.math3.util.FastMath.ulp_3145", _mut48650, _mut48651, _mut48652, _mut48653));
    }

    /**
     * Multiply a double number by a power of 2.
     * @param d number to multiply
     * @param n power of 2
     * @return d &times; 2<sup>n</sup>
     */
    public static double scalb(final double d, final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.scalb_3158");
        // first simple and fast handling when 2^n can be represented using normal numbers
        if ((_mut48664 ? ((ROR_greater(n, -1023, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48654, _mut48655, _mut48656, _mut48657, _mut48658)) || (ROR_less(n, 1024, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48659, _mut48660, _mut48661, _mut48662, _mut48663))) : ((ROR_greater(n, -1023, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48654, _mut48655, _mut48656, _mut48657, _mut48658)) && (ROR_less(n, 1024, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48659, _mut48660, _mut48661, _mut48662, _mut48663))))) {
            return AOR_multiply(d, Double.longBitsToDouble(((long) (AOR_plus(n, 1023, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48665, _mut48666, _mut48667, _mut48668))) << 52), "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48669, _mut48670, _mut48671, _mut48672);
        }
        // handle special cases
        if ((_mut48679 ? ((_mut48673 ? (Double.isNaN(d) && Double.isInfinite(d)) : (Double.isNaN(d) || Double.isInfinite(d))) && (ROR_equals(d, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48674, _mut48675, _mut48676, _mut48677, _mut48678))) : ((_mut48673 ? (Double.isNaN(d) && Double.isInfinite(d)) : (Double.isNaN(d) || Double.isInfinite(d))) || (ROR_equals(d, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48674, _mut48675, _mut48676, _mut48677, _mut48678))))) {
            return d;
        }
        if (ROR_less(n, -2098, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48680, _mut48681, _mut48682, _mut48683, _mut48684)) {
            return (ROR_greater(d, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48685, _mut48686, _mut48687, _mut48688, _mut48689)) ? 0.0 : -0.0;
        }
        if (ROR_greater(n, 2097, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48690, _mut48691, _mut48692, _mut48693, _mut48694)) {
            return (ROR_greater(d, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48695, _mut48696, _mut48697, _mut48698, _mut48699)) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }
        // decompose d
        final long bits = Double.doubleToRawLongBits(d);
        final long sign = bits & 0x8000000000000000L;
        int exponent = ((int) (bits >>> 52)) & 0x7ff;
        long mantissa = bits & 0x000fffffffffffffL;
        // compute scaled exponent
        int scaledExponent = AOR_plus(exponent, n, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48700, _mut48701, _mut48702, _mut48703);
        if (ROR_less(n, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48704, _mut48705, _mut48706, _mut48707, _mut48708)) {
            // we are really in the case n <= -1023
            if (ROR_greater(scaledExponent, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48739, _mut48740, _mut48741, _mut48742, _mut48743)) {
                // both the input and the result are normal numbers, we only adjust the exponent
                return Double.longBitsToDouble(sign | (((long) scaledExponent) << 52) | mantissa);
            } else if (ROR_greater(scaledExponent, -53, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48744, _mut48745, _mut48746, _mut48747, _mut48748)) {
                // recover the hidden mantissa bit
                mantissa |= 1L << 52;
                // scales down complete mantissa, hence losing least significant bits
                final long mostSignificantLostBit = mantissa & (1L << (-scaledExponent));
                mantissa >>>= AOR_minus(1, scaledExponent, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48754, _mut48755, _mut48756, _mut48757);
                if (ROR_not_equals(mostSignificantLostBit, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48758, _mut48759, _mut48760, _mut48761, _mut48762)) {
                    // we need to add 1 bit to round up the result
                    mantissa++;
                }
                return Double.longBitsToDouble(sign | mantissa);
            } else {
                // no need to compute the mantissa, the number scales down to 0
                return (ROR_equals(sign, 0L, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48749, _mut48750, _mut48751, _mut48752, _mut48753)) ? 0.0 : -0.0;
            }
        } else {
            // we are really in the case n >= 1024
            if (ROR_equals(exponent, 0, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48709, _mut48710, _mut48711, _mut48712, _mut48713)) {
                // the input number is subnormal, normalize it
                while (ROR_not_equals((mantissa >>> 52), 1, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48724, _mut48725, _mut48726, _mut48727, _mut48728)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.scalb_3158");
                    mantissa <<= 1;
                    --scaledExponent;
                }
                ++scaledExponent;
                mantissa &= 0x000fffffffffffffL;
                if (ROR_less(scaledExponent, 2047, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48729, _mut48730, _mut48731, _mut48732, _mut48733)) {
                    return Double.longBitsToDouble(sign | (((long) scaledExponent) << 52) | mantissa);
                } else {
                    return (ROR_equals(sign, 0L, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48734, _mut48735, _mut48736, _mut48737, _mut48738)) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
                }
            } else if (ROR_less(scaledExponent, 2047, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48714, _mut48715, _mut48716, _mut48717, _mut48718)) {
                return Double.longBitsToDouble(sign | (((long) scaledExponent) << 52) | mantissa);
            } else {
                return (ROR_equals(sign, 0L, "org.apache.commons.math3.util.FastMath.scalb_3158", _mut48719, _mut48720, _mut48721, _mut48722, _mut48723)) ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
            }
        }
    }

    /**
     * Multiply a float number by a power of 2.
     * @param f number to multiply
     * @param n power of 2
     * @return f &times; 2<sup>n</sup>
     */
    public static float scalb(final float f, final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.scalb_3242");
        // first simple and fast handling when 2^n can be represented using normal numbers
        if ((_mut48773 ? ((ROR_greater(n, -127, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48763, _mut48764, _mut48765, _mut48766, _mut48767)) || (ROR_less(n, 128, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48768, _mut48769, _mut48770, _mut48771, _mut48772))) : ((ROR_greater(n, -127, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48763, _mut48764, _mut48765, _mut48766, _mut48767)) && (ROR_less(n, 128, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48768, _mut48769, _mut48770, _mut48771, _mut48772))))) {
            return AOR_multiply(f, Float.intBitsToFloat((AOR_plus(n, 127, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48774, _mut48775, _mut48776, _mut48777)) << 23), "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48778, _mut48779, _mut48780, _mut48781);
        }
        // handle special cases
        if ((_mut48788 ? ((_mut48782 ? (Float.isNaN(f) && Float.isInfinite(f)) : (Float.isNaN(f) || Float.isInfinite(f))) && (ROR_equals(f, 0f, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48783, _mut48784, _mut48785, _mut48786, _mut48787))) : ((_mut48782 ? (Float.isNaN(f) && Float.isInfinite(f)) : (Float.isNaN(f) || Float.isInfinite(f))) || (ROR_equals(f, 0f, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48783, _mut48784, _mut48785, _mut48786, _mut48787))))) {
            return f;
        }
        if (ROR_less(n, -277, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48789, _mut48790, _mut48791, _mut48792, _mut48793)) {
            return (ROR_greater(f, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48794, _mut48795, _mut48796, _mut48797, _mut48798)) ? 0.0f : -0.0f;
        }
        if (ROR_greater(n, 276, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48799, _mut48800, _mut48801, _mut48802, _mut48803)) {
            return (ROR_greater(f, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48804, _mut48805, _mut48806, _mut48807, _mut48808)) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        // decompose f
        final int bits = Float.floatToIntBits(f);
        final int sign = bits & 0x80000000;
        int exponent = (bits >>> 23) & 0xff;
        int mantissa = bits & 0x007fffff;
        // compute scaled exponent
        int scaledExponent = AOR_plus(exponent, n, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48809, _mut48810, _mut48811, _mut48812);
        if (ROR_less(n, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48813, _mut48814, _mut48815, _mut48816, _mut48817)) {
            // we are really in the case n <= -127
            if (ROR_greater(scaledExponent, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48848, _mut48849, _mut48850, _mut48851, _mut48852)) {
                // both the input and the result are normal numbers, we only adjust the exponent
                return Float.intBitsToFloat(sign | (scaledExponent << 23) | mantissa);
            } else if (ROR_greater(scaledExponent, -24, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48853, _mut48854, _mut48855, _mut48856, _mut48857)) {
                // recover the hidden mantissa bit
                mantissa |= 1 << 23;
                // scales down complete mantissa, hence losing least significant bits
                final int mostSignificantLostBit = mantissa & (1 << (-scaledExponent));
                mantissa >>>= AOR_minus(1, scaledExponent, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48863, _mut48864, _mut48865, _mut48866);
                if (ROR_not_equals(mostSignificantLostBit, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48867, _mut48868, _mut48869, _mut48870, _mut48871)) {
                    // we need to add 1 bit to round up the result
                    mantissa++;
                }
                return Float.intBitsToFloat(sign | mantissa);
            } else {
                // no need to compute the mantissa, the number scales down to 0
                return (ROR_equals(sign, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48858, _mut48859, _mut48860, _mut48861, _mut48862)) ? 0.0f : -0.0f;
            }
        } else {
            // we are really in the case n >= 128
            if (ROR_equals(exponent, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48818, _mut48819, _mut48820, _mut48821, _mut48822)) {
                // the input number is subnormal, normalize it
                while (ROR_not_equals((mantissa >>> 23), 1, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48833, _mut48834, _mut48835, _mut48836, _mut48837)) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.scalb_3242");
                    mantissa <<= 1;
                    --scaledExponent;
                }
                ++scaledExponent;
                mantissa &= 0x007fffff;
                if (ROR_less(scaledExponent, 255, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48838, _mut48839, _mut48840, _mut48841, _mut48842)) {
                    return Float.intBitsToFloat(sign | (scaledExponent << 23) | mantissa);
                } else {
                    return (ROR_equals(sign, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48843, _mut48844, _mut48845, _mut48846, _mut48847)) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
                }
            } else if (ROR_less(scaledExponent, 255, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48823, _mut48824, _mut48825, _mut48826, _mut48827)) {
                return Float.intBitsToFloat(sign | (scaledExponent << 23) | mantissa);
            } else {
                return (ROR_equals(sign, 0, "org.apache.commons.math3.util.FastMath.scalb_3242", _mut48828, _mut48829, _mut48830, _mut48831, _mut48832)) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
            }
        }
    }

    /**
     * Get the next machine representable number after a number, moving
     * in the direction of another number.
     * <p>
     * The ordering is as follows (increasing):
     * <ul>
     * <li>-INFINITY</li>
     * <li>-MAX_VALUE</li>
     * <li>-MIN_VALUE</li>
     * <li>-0.0</li>
     * <li>+0.0</li>
     * <li>+MIN_VALUE</li>
     * <li>+MAX_VALUE</li>
     * <li>+INFINITY</li>
     * <li></li>
     * <p>
     * If arguments compare equal, then the second argument is returned.
     * <p>
     * If {@code direction} is greater than {@code d},
     * the smallest machine representable number strictly greater than
     * {@code d} is returned; if less, then the largest representable number
     * strictly less than {@code d} is returned.</p>
     * <p>
     * If {@code d} is infinite and direction does not
     * bring it back to finite numbers, it is returned unchanged.</p>
     *
     * @param d base number
     * @param direction (the only important thing is whether
     * {@code direction} is greater or smaller than {@code d})
     * @return the next machine representable number in the specified direction
     */
    public static double nextAfter(double d, double direction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.nextAfter_3351");
        // handling of some important special cases
        if ((_mut48872 ? (Double.isNaN(d) && Double.isNaN(direction)) : (Double.isNaN(d) || Double.isNaN(direction)))) {
            return Double.NaN;
        } else if (ROR_equals(d, direction, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48873, _mut48874, _mut48875, _mut48876, _mut48877)) {
            return direction;
        } else if (Double.isInfinite(d)) {
            return (ROR_less(d, 0, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48888, _mut48889, _mut48890, _mut48891, _mut48892)) ? -Double.MAX_VALUE : Double.MAX_VALUE;
        } else if (ROR_equals(d, 0, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48878, _mut48879, _mut48880, _mut48881, _mut48882)) {
            return (ROR_less(direction, 0, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48883, _mut48884, _mut48885, _mut48886, _mut48887)) ? -Double.MIN_VALUE : Double.MIN_VALUE;
        }
        // can use raw bits since already dealt with infinity and NaN
        final long bits = Double.doubleToRawLongBits(d);
        final long sign = bits & 0x8000000000000000L;
        if ((ROR_less(direction, d, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48893, _mut48894, _mut48895, _mut48896, _mut48897)) ^ (ROR_equals(sign, 0L, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48898, _mut48899, _mut48900, _mut48901, _mut48902))) {
            return Double.longBitsToDouble(sign | (AOR_plus((bits & 0x7fffffffffffffffL), 1, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48907, _mut48908, _mut48909, _mut48910)));
        } else {
            return Double.longBitsToDouble(sign | (AOR_minus((bits & 0x7fffffffffffffffL), 1, "org.apache.commons.math3.util.FastMath.nextAfter_3351", _mut48903, _mut48904, _mut48905, _mut48906)));
        }
    }

    /**
     * Get the next machine representable number after a number, moving
     * in the direction of another number.
     * <p>
     * The ordering is as follows (increasing):
     * <ul>
     * <li>-INFINITY</li>
     * <li>-MAX_VALUE</li>
     * <li>-MIN_VALUE</li>
     * <li>-0.0</li>
     * <li>+0.0</li>
     * <li>+MIN_VALUE</li>
     * <li>+MAX_VALUE</li>
     * <li>+INFINITY</li>
     * <li></li>
     * <p>
     * If arguments compare equal, then the second argument is returned.
     * <p>
     * If {@code direction} is greater than {@code f},
     * the smallest machine representable number strictly greater than
     * {@code f} is returned; if less, then the largest representable number
     * strictly less than {@code f} is returned.</p>
     * <p>
     * If {@code f} is infinite and direction does not
     * bring it back to finite numbers, it is returned unchanged.</p>
     *
     * @param f base number
     * @param direction (the only important thing is whether
     * {@code direction} is greater or smaller than {@code f})
     * @return the next machine representable number in the specified direction
     */
    public static float nextAfter(final float f, final double direction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.nextAfter_3407");
        // handling of some important special cases
        if ((_mut48911 ? (Double.isNaN(f) && Double.isNaN(direction)) : (Double.isNaN(f) || Double.isNaN(direction)))) {
            return Float.NaN;
        } else if (ROR_equals(f, direction, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48912, _mut48913, _mut48914, _mut48915, _mut48916)) {
            return (float) direction;
        } else if (Float.isInfinite(f)) {
            return (ROR_less(f, 0f, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48927, _mut48928, _mut48929, _mut48930, _mut48931)) ? -Float.MAX_VALUE : Float.MAX_VALUE;
        } else if (ROR_equals(f, 0f, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48917, _mut48918, _mut48919, _mut48920, _mut48921)) {
            return (ROR_less(direction, 0, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48922, _mut48923, _mut48924, _mut48925, _mut48926)) ? -Float.MIN_VALUE : Float.MIN_VALUE;
        }
        final int bits = Float.floatToIntBits(f);
        final int sign = bits & 0x80000000;
        if ((ROR_less(direction, f, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48932, _mut48933, _mut48934, _mut48935, _mut48936)) ^ (ROR_equals(sign, 0, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48937, _mut48938, _mut48939, _mut48940, _mut48941))) {
            return Float.intBitsToFloat(sign | (AOR_plus((bits & 0x7fffffff), 1, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48946, _mut48947, _mut48948, _mut48949)));
        } else {
            return Float.intBitsToFloat(sign | (AOR_minus((bits & 0x7fffffff), 1, "org.apache.commons.math3.util.FastMath.nextAfter_3407", _mut48942, _mut48943, _mut48944, _mut48945)));
        }
    }

    /**
     * Get the largest whole number smaller than x.
     * @param x number from which floor is requested
     * @return a double number f such that f is an integer f <= x < f + 1.0
     */
    public static double floor(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.floor_3436");
        long y;
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48950, _mut48951, _mut48952, _mut48953, _mut48954)) {
            // NaN
            return x;
        }
        if ((_mut48965 ? (ROR_greater_equals(x, TWO_POWER_52, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48955, _mut48956, _mut48957, _mut48958, _mut48959) && ROR_less_equals(x, -TWO_POWER_52, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48960, _mut48961, _mut48962, _mut48963, _mut48964)) : (ROR_greater_equals(x, TWO_POWER_52, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48955, _mut48956, _mut48957, _mut48958, _mut48959) || ROR_less_equals(x, -TWO_POWER_52, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48960, _mut48961, _mut48962, _mut48963, _mut48964)))) {
            return x;
        }
        y = (long) x;
        if ((_mut48976 ? (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48966, _mut48967, _mut48968, _mut48969, _mut48970) || ROR_not_equals(y, x, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48971, _mut48972, _mut48973, _mut48974, _mut48975)) : (ROR_less(x, 0, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48966, _mut48967, _mut48968, _mut48969, _mut48970) && ROR_not_equals(y, x, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48971, _mut48972, _mut48973, _mut48974, _mut48975)))) {
            y--;
        }
        if (ROR_equals(y, 0, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48977, _mut48978, _mut48979, _mut48980, _mut48981)) {
            return AOR_multiply(x, y, "org.apache.commons.math3.util.FastMath.floor_3436", _mut48982, _mut48983, _mut48984, _mut48985);
        }
        return y;
    }

    /**
     * Get the smallest whole number larger than x.
     * @param x number from which ceil is requested
     * @return a double number c such that c is an integer c - 1.0 < x <= c
     */
    public static double ceil(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.ceil_3463");
        double y;
        if (ROR_not_equals(x, x, "org.apache.commons.math3.util.FastMath.ceil_3463", _mut48986, _mut48987, _mut48988, _mut48989, _mut48990)) {
            // NaN
            return x;
        }
        y = floor(x);
        if (ROR_equals(y, x, "org.apache.commons.math3.util.FastMath.ceil_3463", _mut48991, _mut48992, _mut48993, _mut48994, _mut48995)) {
            return y;
        }
        y += 1.0;
        if (ROR_equals(y, 0, "org.apache.commons.math3.util.FastMath.ceil_3463", _mut48996, _mut48997, _mut48998, _mut48999, _mut49000)) {
            return AOR_multiply(x, y, "org.apache.commons.math3.util.FastMath.ceil_3463", _mut49001, _mut49002, _mut49003, _mut49004);
        }
        return y;
    }

    /**
     * Get the whole number that is the nearest to x, or the even one if x is exactly half way between two integers.
     * @param x number from which nearest whole number is requested
     * @return a double number r such that r is an integer r - 0.5 <= x <= r + 0.5
     */
    public static double rint(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.rint_3488");
        double y = floor(x);
        double d = AOR_minus(x, y, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49005, _mut49006, _mut49007, _mut49008);
        if (ROR_greater(d, 0.5, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49009, _mut49010, _mut49011, _mut49012, _mut49013)) {
            if (ROR_equals(y, -1.0, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49014, _mut49015, _mut49016, _mut49017, _mut49018)) {
                // Preserve sign of operand
                return -0.0;
            }
            return AOR_plus(y, 1.0, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49019, _mut49020, _mut49021, _mut49022);
        }
        if (ROR_less(d, 0.5, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49023, _mut49024, _mut49025, _mut49026, _mut49027)) {
            return y;
        }
        /* half way, round to even */
        long z = (long) y;
        return ROR_equals((z & 1), 0, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49028, _mut49029, _mut49030, _mut49031, _mut49032) ? y : AOR_plus(y, 1.0, "org.apache.commons.math3.util.FastMath.rint_3488", _mut49033, _mut49034, _mut49035, _mut49036);
    }

    /**
     * Get the closest long to x.
     * @param x number from which closest long is requested
     * @return closest long to x
     */
    public static long round(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.round_3511");
        return (long) floor(AOR_plus(x, 0.5, "org.apache.commons.math3.util.FastMath.round_3511", _mut49037, _mut49038, _mut49039, _mut49040));
    }

    /**
     * Get the closest int to x.
     * @param x number from which closest int is requested
     * @return closest int to x
     */
    public static int round(final float x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.round_3519");
        return (int) floor(AOR_plus(x, 0.5f, "org.apache.commons.math3.util.FastMath.round_3519", _mut49041, _mut49042, _mut49043, _mut49044));
    }

    /**
     * Compute the minimum of two values
     * @param a first value
     * @param b second value
     * @return a if a is lesser or equal to b, b otherwise
     */
    public static int min(final int a, final int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.min_3528");
        return (ROR_less_equals(a, b, "org.apache.commons.math3.util.FastMath.min_3528", _mut49045, _mut49046, _mut49047, _mut49048, _mut49049)) ? a : b;
    }

    /**
     * Compute the minimum of two values
     * @param a first value
     * @param b second value
     * @return a if a is lesser or equal to b, b otherwise
     */
    public static long min(final long a, final long b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.min_3537");
        return (ROR_less_equals(a, b, "org.apache.commons.math3.util.FastMath.min_3537", _mut49050, _mut49051, _mut49052, _mut49053, _mut49054)) ? a : b;
    }

    /**
     * Compute the minimum of two values
     * @param a first value
     * @param b second value
     * @return a if a is lesser or equal to b, b otherwise
     */
    public static float min(final float a, final float b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.min_3546");
        if (ROR_greater(a, b, "org.apache.commons.math3.util.FastMath.min_3546", _mut49055, _mut49056, _mut49057, _mut49058, _mut49059)) {
            return b;
        }
        if (ROR_less(a, b, "org.apache.commons.math3.util.FastMath.min_3546", _mut49060, _mut49061, _mut49062, _mut49063, _mut49064)) {
            return a;
        }
        /* if either arg is NaN, return NaN */
        if (ROR_not_equals(a, b, "org.apache.commons.math3.util.FastMath.min_3546", _mut49065, _mut49066, _mut49067, _mut49068, _mut49069)) {
            return Float.NaN;
        }
        /* 0x80000000 == Float.floatToRawIntBits(-0.0d) */
        int bits = Float.floatToRawIntBits(a);
        if (ROR_equals(bits, 0x80000000, "org.apache.commons.math3.util.FastMath.min_3546", _mut49070, _mut49071, _mut49072, _mut49073, _mut49074)) {
            return a;
        }
        return b;
    }

    /**
     * Compute the minimum of two values
     * @param a first value
     * @param b second value
     * @return a if a is lesser or equal to b, b otherwise
     */
    public static double min(final double a, final double b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.min_3571");
        if (ROR_greater(a, b, "org.apache.commons.math3.util.FastMath.min_3571", _mut49075, _mut49076, _mut49077, _mut49078, _mut49079)) {
            return b;
        }
        if (ROR_less(a, b, "org.apache.commons.math3.util.FastMath.min_3571", _mut49080, _mut49081, _mut49082, _mut49083, _mut49084)) {
            return a;
        }
        /* if either arg is NaN, return NaN */
        if (ROR_not_equals(a, b, "org.apache.commons.math3.util.FastMath.min_3571", _mut49085, _mut49086, _mut49087, _mut49088, _mut49089)) {
            return Double.NaN;
        }
        /* 0x8000000000000000L == Double.doubleToRawLongBits(-0.0d) */
        long bits = Double.doubleToRawLongBits(a);
        if (ROR_equals(bits, 0x8000000000000000L, "org.apache.commons.math3.util.FastMath.min_3571", _mut49090, _mut49091, _mut49092, _mut49093, _mut49094)) {
            return a;
        }
        return b;
    }

    /**
     * Compute the maximum of two values
     * @param a first value
     * @param b second value
     * @return b if a is lesser or equal to b, a otherwise
     */
    public static int max(final int a, final int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.max_3596");
        return (ROR_less_equals(a, b, "org.apache.commons.math3.util.FastMath.max_3596", _mut49095, _mut49096, _mut49097, _mut49098, _mut49099)) ? b : a;
    }

    /**
     * Compute the maximum of two values
     * @param a first value
     * @param b second value
     * @return b if a is lesser or equal to b, a otherwise
     */
    public static long max(final long a, final long b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.max_3605");
        return (ROR_less_equals(a, b, "org.apache.commons.math3.util.FastMath.max_3605", _mut49100, _mut49101, _mut49102, _mut49103, _mut49104)) ? b : a;
    }

    /**
     * Compute the maximum of two values
     * @param a first value
     * @param b second value
     * @return b if a is lesser or equal to b, a otherwise
     */
    public static float max(final float a, final float b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.max_3614");
        if (ROR_greater(a, b, "org.apache.commons.math3.util.FastMath.max_3614", _mut49105, _mut49106, _mut49107, _mut49108, _mut49109)) {
            return a;
        }
        if (ROR_less(a, b, "org.apache.commons.math3.util.FastMath.max_3614", _mut49110, _mut49111, _mut49112, _mut49113, _mut49114)) {
            return b;
        }
        /* if either arg is NaN, return NaN */
        if (ROR_not_equals(a, b, "org.apache.commons.math3.util.FastMath.max_3614", _mut49115, _mut49116, _mut49117, _mut49118, _mut49119)) {
            return Float.NaN;
        }
        /* 0x80000000 == Float.floatToRawIntBits(-0.0d) */
        int bits = Float.floatToRawIntBits(a);
        if (ROR_equals(bits, 0x80000000, "org.apache.commons.math3.util.FastMath.max_3614", _mut49120, _mut49121, _mut49122, _mut49123, _mut49124)) {
            return b;
        }
        return a;
    }

    /**
     * Compute the maximum of two values
     * @param a first value
     * @param b second value
     * @return b if a is lesser or equal to b, a otherwise
     */
    public static double max(final double a, final double b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.max_3639");
        if (ROR_greater(a, b, "org.apache.commons.math3.util.FastMath.max_3639", _mut49125, _mut49126, _mut49127, _mut49128, _mut49129)) {
            return a;
        }
        if (ROR_less(a, b, "org.apache.commons.math3.util.FastMath.max_3639", _mut49130, _mut49131, _mut49132, _mut49133, _mut49134)) {
            return b;
        }
        /* if either arg is NaN, return NaN */
        if (ROR_not_equals(a, b, "org.apache.commons.math3.util.FastMath.max_3639", _mut49135, _mut49136, _mut49137, _mut49138, _mut49139)) {
            return Double.NaN;
        }
        /* 0x8000000000000000L == Double.doubleToRawLongBits(-0.0d) */
        long bits = Double.doubleToRawLongBits(a);
        if (ROR_equals(bits, 0x8000000000000000L, "org.apache.commons.math3.util.FastMath.max_3639", _mut49140, _mut49141, _mut49142, _mut49143, _mut49144)) {
            return b;
        }
        return a;
    }

    /**
     * Returns the hypotenuse of a triangle with sides {@code x} and {@code y}
     * - sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)<br/>
     * avoiding intermediate overflow or underflow.
     *
     * <ul>
     * <li> If either argument is infinite, then the result is positive infinity.</li>
     * <li> else, if either argument is NaN then the result is NaN.</li>
     * </ul>
     *
     * @param x a value
     * @param y a value
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     */
    public static double hypot(final double x, final double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.hypot_3673");
        if ((_mut49145 ? (Double.isInfinite(x) && Double.isInfinite(y)) : (Double.isInfinite(x) || Double.isInfinite(y)))) {
            return Double.POSITIVE_INFINITY;
        } else if ((_mut49146 ? (Double.isNaN(x) && Double.isNaN(y)) : (Double.isNaN(x) || Double.isNaN(y)))) {
            return Double.NaN;
        } else {
            final int expX = getExponent(x);
            final int expY = getExponent(y);
            if (ROR_greater(expX, AOR_plus(expY, 27, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49147, _mut49148, _mut49149, _mut49150), "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49151, _mut49152, _mut49153, _mut49154, _mut49155)) {
                // y is neglectible with respect to x
                return abs(x);
            } else if (ROR_greater(expY, AOR_plus(expX, 27, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49156, _mut49157, _mut49158, _mut49159), "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49160, _mut49161, _mut49162, _mut49163, _mut49164)) {
                // x is neglectible with respect to y
                return abs(y);
            } else {
                // find an intermediate scale to avoid both overflow and underflow
                final int middleExp = AOR_divide((AOR_plus(expX, expY, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49165, _mut49166, _mut49167, _mut49168)), 2, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49169, _mut49170, _mut49171, _mut49172);
                // scale parameters without losing precision
                final double scaledX = scalb(x, -middleExp);
                final double scaledY = scalb(y, -middleExp);
                // compute scaled hypotenuse
                final double scaledH = sqrt(AOR_plus(AOR_multiply(scaledX, scaledX, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49173, _mut49174, _mut49175, _mut49176), AOR_multiply(scaledY, scaledY, "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49177, _mut49178, _mut49179, _mut49180), "org.apache.commons.math3.util.FastMath.hypot_3673", _mut49181, _mut49182, _mut49183, _mut49184));
                // remove scaling
                return scalb(scaledH, middleExp);
            }
        }
    }

    /**
     * Computes the remainder as prescribed by the IEEE 754 standard.
     * The remainder value is mathematically equal to {@code x - y*n}
     * where {@code n} is the mathematical integer closest to the exact mathematical value
     * of the quotient {@code x/y}.
     * If two mathematical integers are equally close to {@code x/y} then
     * {@code n} is the integer that is even.
     * <p>
     * <ul>
     * <li>If either operand is NaN, the result is NaN.</li>
     * <li>If the result is not NaN, the sign of the result equals the sign of the dividend.</li>
     * <li>If the dividend is an infinity, or the divisor is a zero, or both, the result is NaN.</li>
     * <li>If the dividend is finite and the divisor is an infinity, the result equals the dividend.</li>
     * <li>If the dividend is a zero and the divisor is finite, the result equals the dividend.</li>
     * </ul>
     * <p><b>Note:</b> this implementation currently delegates to {@link StrictMath#IEEEremainder}
     * @param dividend the number to be divided
     * @param divisor the number by which to divide
     * @return the remainder, rounded
     */
    public static double IEEEremainder(double dividend, double divisor) {
        // TODO provide our own implementation
        return StrictMath.IEEEremainder(dividend, divisor);
    }

    /**
     * Convert a long to interger, detecting overflows
     * @param n number to convert to int
     * @return integer with same valie as n if no overflows occur
     * @exception MathArithmeticException if n cannot fit into an int
     * @since 3.4
     */
    public static int toIntExact(final long n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.toIntExact_3738");
        if ((_mut49195 ? (ROR_less(n, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.toIntExact_3738", _mut49185, _mut49186, _mut49187, _mut49188, _mut49189) && ROR_greater(n, Integer.MAX_VALUE, "org.apache.commons.math3.util.FastMath.toIntExact_3738", _mut49190, _mut49191, _mut49192, _mut49193, _mut49194)) : (ROR_less(n, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.toIntExact_3738", _mut49185, _mut49186, _mut49187, _mut49188, _mut49189) || ROR_greater(n, Integer.MAX_VALUE, "org.apache.commons.math3.util.FastMath.toIntExact_3738", _mut49190, _mut49191, _mut49192, _mut49193, _mut49194)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW);
        }
        return (int) n;
    }

    /**
     * Increment a number, detecting overflows.
     * @param n number to increment
     * @return n+1 if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static int incrementExact(final int n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.incrementExact_3751");
        if (ROR_equals(n, Integer.MAX_VALUE, "org.apache.commons.math3.util.FastMath.incrementExact_3751", _mut49196, _mut49197, _mut49198, _mut49199, _mut49200)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, n, 1);
        }
        return AOR_plus(n, 1, "org.apache.commons.math3.util.FastMath.incrementExact_3751", _mut49201, _mut49202, _mut49203, _mut49204);
    }

    /**
     * Increment a number, detecting overflows.
     * @param n number to increment
     * @return n+1 if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static long incrementExact(final long n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.incrementExact_3767");
        if (ROR_equals(n, Long.MAX_VALUE, "org.apache.commons.math3.util.FastMath.incrementExact_3767", _mut49205, _mut49206, _mut49207, _mut49208, _mut49209)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, n, 1);
        }
        return AOR_plus(n, 1, "org.apache.commons.math3.util.FastMath.incrementExact_3767", _mut49210, _mut49211, _mut49212, _mut49213);
    }

    /**
     * Decrement a number, detecting overflows.
     * @param n number to decrement
     * @return n-1 if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static int decrementExact(final int n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.decrementExact_3783");
        if (ROR_equals(n, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.decrementExact_3783", _mut49214, _mut49215, _mut49216, _mut49217, _mut49218)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, n, 1);
        }
        return AOR_minus(n, 1, "org.apache.commons.math3.util.FastMath.decrementExact_3783", _mut49219, _mut49220, _mut49221, _mut49222);
    }

    /**
     * Decrement a number, detecting overflows.
     * @param n number to decrement
     * @return n-1 if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static long decrementExact(final long n) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.decrementExact_3799");
        if (ROR_equals(n, Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.decrementExact_3799", _mut49223, _mut49224, _mut49225, _mut49226, _mut49227)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, n, 1);
        }
        return AOR_minus(n, 1, "org.apache.commons.math3.util.FastMath.decrementExact_3799", _mut49228, _mut49229, _mut49230, _mut49231);
    }

    /**
     * Add two numbers, detecting overflows.
     * @param a first number to add
     * @param b second number to add
     * @return a+b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static int addExact(final int a, final int b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.addExact_3816");
        // compute sum
        final int sum = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.addExact_3816", _mut49232, _mut49233, _mut49234, _mut49235);
        // check for overflow
        if ((_mut49246 ? (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3816", _mut49236, _mut49237, _mut49238, _mut49239, _mut49240) || ROR_less((sum ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3816", _mut49241, _mut49242, _mut49243, _mut49244, _mut49245)) : (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3816", _mut49236, _mut49237, _mut49238, _mut49239, _mut49240) && ROR_less((sum ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3816", _mut49241, _mut49242, _mut49243, _mut49244, _mut49245)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, a, b);
        }
        return sum;
    }

    /**
     * Add two numbers, detecting overflows.
     * @param a first number to add
     * @param b second number to add
     * @return a+b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static long addExact(final long a, final long b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.addExact_3837");
        // compute sum
        final long sum = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.addExact_3837", _mut49247, _mut49248, _mut49249, _mut49250);
        // check for overflow
        if ((_mut49261 ? (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3837", _mut49251, _mut49252, _mut49253, _mut49254, _mut49255) || ROR_less((sum ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3837", _mut49256, _mut49257, _mut49258, _mut49259, _mut49260)) : (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3837", _mut49251, _mut49252, _mut49253, _mut49254, _mut49255) && ROR_less((sum ^ b), 0, "org.apache.commons.math3.util.FastMath.addExact_3837", _mut49256, _mut49257, _mut49258, _mut49259, _mut49260)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, a, b);
        }
        return sum;
    }

    /**
     * Subtract two numbers, detecting overflows.
     * @param a first number
     * @param b second number to subtract from a
     * @return a-b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static int subtractExact(final int a, final int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.subtractExact_3858");
        // compute subtraction
        final int sub = AOR_minus(a, b, "org.apache.commons.math3.util.FastMath.subtractExact_3858", _mut49262, _mut49263, _mut49264, _mut49265);
        // check for overflow
        if ((_mut49276 ? (ROR_less((a ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3858", _mut49266, _mut49267, _mut49268, _mut49269, _mut49270) || ROR_greater_equals((sub ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3858", _mut49271, _mut49272, _mut49273, _mut49274, _mut49275)) : (ROR_less((a ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3858", _mut49266, _mut49267, _mut49268, _mut49269, _mut49270) && ROR_greater_equals((sub ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3858", _mut49271, _mut49272, _mut49273, _mut49274, _mut49275)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, a, b);
        }
        return sub;
    }

    /**
     * Subtract two numbers, detecting overflows.
     * @param a first number
     * @param b second number to subtract from a
     * @return a-b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static long subtractExact(final long a, final long b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.subtractExact_3879");
        // compute subtraction
        final long sub = AOR_minus(a, b, "org.apache.commons.math3.util.FastMath.subtractExact_3879", _mut49277, _mut49278, _mut49279, _mut49280);
        // check for overflow
        if ((_mut49291 ? (ROR_less((a ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3879", _mut49281, _mut49282, _mut49283, _mut49284, _mut49285) || ROR_greater_equals((sub ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3879", _mut49286, _mut49287, _mut49288, _mut49289, _mut49290)) : (ROR_less((a ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3879", _mut49281, _mut49282, _mut49283, _mut49284, _mut49285) && ROR_greater_equals((sub ^ b), 0, "org.apache.commons.math3.util.FastMath.subtractExact_3879", _mut49286, _mut49287, _mut49288, _mut49289, _mut49290)))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, a, b);
        }
        return sub;
    }

    /**
     * Multiply two numbers, detecting overflows.
     * @param a first number to multiply
     * @param b second number to multiply
     * @return a*b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static int multiplyExact(final int a, final int b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.multiplyExact_3900");
        if ((_mut49354 ? ((_mut49342 ? (((_mut49316 ? ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) || ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))) : ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) && ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))))) && ((_mut49341 ? ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) || ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))) : ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) && ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)))))))) : (((_mut49316 ? ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) || ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))) : ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) && ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))))) || ((_mut49341 ? ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) || ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))) : ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) && ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))))))) && ((_mut49353 ? ((ROR_equals(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49343, _mut49344, _mut49345, _mut49346, _mut49347)) || (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49348, _mut49349, _mut49350, _mut49351, _mut49352))) : ((ROR_equals(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49343, _mut49344, _mut49345, _mut49346, _mut49347)) && (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49348, _mut49349, _mut49350, _mut49351, _mut49352)))))) : ((_mut49342 ? (((_mut49316 ? ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) || ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))) : ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) && ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))))) && ((_mut49341 ? ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) || ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))) : ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) && ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)))))))) : (((_mut49316 ? ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) || ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))) : ((ROR_greater(b, 0, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49292, _mut49293, _mut49294, _mut49295, _mut49296)) && ((_mut49315 ? (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) && ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314)) : (ROR_greater(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49297, _mut49298, _mut49299, _mut49300), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49301, _mut49302, _mut49303, _mut49304, _mut49305) || ROR_less(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49306, _mut49307, _mut49308, _mut49309), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49310, _mut49311, _mut49312, _mut49313, _mut49314))))))) || ((_mut49341 ? ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) || ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))) : ((ROR_less(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49317, _mut49318, _mut49319, _mut49320, _mut49321)) && ((_mut49340 ? (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) && ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339)) : (ROR_greater(a, AOR_divide(Integer.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49322, _mut49323, _mut49324, _mut49325), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49326, _mut49327, _mut49328, _mut49329, _mut49330) || ROR_less(a, AOR_divide(Integer.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49331, _mut49332, _mut49333, _mut49334), "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49335, _mut49336, _mut49337, _mut49338, _mut49339))))))))) || ((_mut49353 ? ((ROR_equals(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49343, _mut49344, _mut49345, _mut49346, _mut49347)) || (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49348, _mut49349, _mut49350, _mut49351, _mut49352))) : ((ROR_equals(b, -1, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49343, _mut49344, _mut49345, _mut49346, _mut49347)) && (ROR_equals(a, Integer.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49348, _mut49349, _mut49350, _mut49351, _mut49352)))))))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, a, b);
        }
        return AOR_multiply(a, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3900", _mut49355, _mut49356, _mut49357, _mut49358);
    }

    /**
     * Multiply two numbers, detecting overflows.
     * @param a first number to multiply
     * @param b second number to multiply
     * @return a*b if no overflows occur
     * @exception MathArithmeticException if an overflow occurs
     * @since 3.4
     */
    public static long multiplyExact(final long a, final long b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.multiplyExact_3916");
        if ((_mut49421 ? ((_mut49409 ? (((_mut49383 ? ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) || ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))) : ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) && ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))))) && ((_mut49408 ? ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) || ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))) : ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) && ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)))))))) : (((_mut49383 ? ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) || ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))) : ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) && ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))))) || ((_mut49408 ? ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) || ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))) : ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) && ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))))))) && ((_mut49420 ? ((ROR_equals(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49410, _mut49411, _mut49412, _mut49413, _mut49414)) || (ROR_equals(a, Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49415, _mut49416, _mut49417, _mut49418, _mut49419))) : ((ROR_equals(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49410, _mut49411, _mut49412, _mut49413, _mut49414)) && (ROR_equals(a, Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49415, _mut49416, _mut49417, _mut49418, _mut49419)))))) : ((_mut49409 ? (((_mut49383 ? ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) || ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))) : ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) && ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))))) && ((_mut49408 ? ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) || ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))) : ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) && ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)))))))) : (((_mut49383 ? ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) || ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))) : ((ROR_greater(b, 0l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49359, _mut49360, _mut49361, _mut49362, _mut49363)) && ((_mut49382 ? (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) && ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381)) : (ROR_greater(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49364, _mut49365, _mut49366, _mut49367), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49368, _mut49369, _mut49370, _mut49371, _mut49372) || ROR_less(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49373, _mut49374, _mut49375, _mut49376), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49377, _mut49378, _mut49379, _mut49380, _mut49381))))))) || ((_mut49408 ? ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) || ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))) : ((ROR_less(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49384, _mut49385, _mut49386, _mut49387, _mut49388)) && ((_mut49407 ? (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) && ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406)) : (ROR_greater(a, AOR_divide(Long.MIN_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49389, _mut49390, _mut49391, _mut49392), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49393, _mut49394, _mut49395, _mut49396, _mut49397) || ROR_less(a, AOR_divide(Long.MAX_VALUE, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49398, _mut49399, _mut49400, _mut49401), "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49402, _mut49403, _mut49404, _mut49405, _mut49406))))))))) || ((_mut49420 ? ((ROR_equals(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49410, _mut49411, _mut49412, _mut49413, _mut49414)) || (ROR_equals(a, Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49415, _mut49416, _mut49417, _mut49418, _mut49419))) : ((ROR_equals(b, -1l, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49410, _mut49411, _mut49412, _mut49413, _mut49414)) && (ROR_equals(a, Long.MIN_VALUE, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49415, _mut49416, _mut49417, _mut49418, _mut49419)))))))) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_MULTIPLICATION, a, b);
        }
        return AOR_multiply(a, b, "org.apache.commons.math3.util.FastMath.multiplyExact_3916", _mut49422, _mut49423, _mut49424, _mut49425);
    }

    /**
     * Finds q such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0.
     * <p>
     * This methods returns the same value as integer division when
     * a and b are same signs, but returns a different value when
     * they are opposite (i.e. q is negative).
     * </p>
     * @param a dividend
     * @param b divisor
     * @return q such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0
     * @exception MathArithmeticException if b == 0
     * @see #floorMod(int, int)
     * @since 3.4
     */
    public static int floorDiv(final int a, final int b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.floorDiv_3938");
        if (ROR_equals(b, 0, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49426, _mut49427, _mut49428, _mut49429, _mut49430)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        final int m = AOR_remainder(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49431, _mut49432, _mut49433, _mut49434);
        if ((_mut49445 ? (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49435, _mut49436, _mut49437, _mut49438, _mut49439) && ROR_equals(m, 0, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49440, _mut49441, _mut49442, _mut49443, _mut49444)) : (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49435, _mut49436, _mut49437, _mut49438, _mut49439) || ROR_equals(m, 0, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49440, _mut49441, _mut49442, _mut49443, _mut49444)))) {
            // a an b have same sign, or division is exact
            return AOR_divide(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49454, _mut49455, _mut49456, _mut49457);
        } else {
            // a and b have opposite signs and division is not exact
            return AOR_minus((AOR_divide(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49446, _mut49447, _mut49448, _mut49449)), 1, "org.apache.commons.math3.util.FastMath.floorDiv_3938", _mut49450, _mut49451, _mut49452, _mut49453);
        }
    }

    /**
     * Finds q such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0.
     * <p>
     * This methods returns the same value as integer division when
     * a and b are same signs, but returns a different value when
     * they are opposite (i.e. q is negative).
     * </p>
     * @param a dividend
     * @param b divisor
     * @return q such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0
     * @exception MathArithmeticException if b == 0
     * @see #floorMod(long, long)
     * @since 3.4
     */
    public static long floorDiv(final long a, final long b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.floorDiv_3968");
        if (ROR_equals(b, 0l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49458, _mut49459, _mut49460, _mut49461, _mut49462)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        final long m = AOR_remainder(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49463, _mut49464, _mut49465, _mut49466);
        if ((_mut49477 ? (ROR_greater_equals((a ^ b), 0l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49467, _mut49468, _mut49469, _mut49470, _mut49471) && ROR_equals(m, 0l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49472, _mut49473, _mut49474, _mut49475, _mut49476)) : (ROR_greater_equals((a ^ b), 0l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49467, _mut49468, _mut49469, _mut49470, _mut49471) || ROR_equals(m, 0l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49472, _mut49473, _mut49474, _mut49475, _mut49476)))) {
            // a an b have same sign, or division is exact
            return AOR_divide(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49486, _mut49487, _mut49488, _mut49489);
        } else {
            // a and b have opposite signs and division is not exact
            return AOR_minus((AOR_divide(a, b, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49478, _mut49479, _mut49480, _mut49481)), 1l, "org.apache.commons.math3.util.FastMath.floorDiv_3968", _mut49482, _mut49483, _mut49484, _mut49485);
        }
    }

    /**
     * Finds r such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0.
     * <p>
     * This methods returns the same value as integer modulo when
     * a and b are same signs, but returns a different value when
     * they are opposite (i.e. q is negative).
     * </p>
     * @param a dividend
     * @param b divisor
     * @return r such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0
     * @exception MathArithmeticException if b == 0
     * @see #floorDiv(int, int)
     * @since 3.4
     */
    public static int floorMod(final int a, final int b) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.floorMod_3998");
        if (ROR_equals(b, 0, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49490, _mut49491, _mut49492, _mut49493, _mut49494)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        final int m = AOR_remainder(a, b, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49495, _mut49496, _mut49497, _mut49498);
        if ((_mut49509 ? (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49499, _mut49500, _mut49501, _mut49502, _mut49503) && ROR_equals(m, 0, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49504, _mut49505, _mut49506, _mut49507, _mut49508)) : (ROR_greater_equals((a ^ b), 0, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49499, _mut49500, _mut49501, _mut49502, _mut49503) || ROR_equals(m, 0, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49504, _mut49505, _mut49506, _mut49507, _mut49508)))) {
            // a an b have same sign, or division is exact
            return m;
        } else {
            // a and b have opposite signs and division is not exact
            return AOR_plus(b, m, "org.apache.commons.math3.util.FastMath.floorMod_3998", _mut49510, _mut49511, _mut49512, _mut49513);
        }
    }

    /**
     * Finds r such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0.
     * <p>
     * This methods returns the same value as integer modulo when
     * a and b are same signs, but returns a different value when
     * they are opposite (i.e. q is negative).
     * </p>
     * @param a dividend
     * @param b divisor
     * @return r such that a = q b + r with 0 <= r < b if b > 0 and b < r <= 0 if b < 0
     * @exception MathArithmeticException if b == 0
     * @see #floorDiv(long, long)
     * @since 3.4
     */
    public static long floorMod(final long a, final long b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.floorMod_4028");
        if (ROR_equals(b, 0l, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49514, _mut49515, _mut49516, _mut49517, _mut49518)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR);
        }
        final long m = AOR_remainder(a, b, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49519, _mut49520, _mut49521, _mut49522);
        if ((_mut49533 ? (ROR_greater_equals((a ^ b), 0l, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49523, _mut49524, _mut49525, _mut49526, _mut49527) && ROR_equals(m, 0l, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49528, _mut49529, _mut49530, _mut49531, _mut49532)) : (ROR_greater_equals((a ^ b), 0l, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49523, _mut49524, _mut49525, _mut49526, _mut49527) || ROR_equals(m, 0l, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49528, _mut49529, _mut49530, _mut49531, _mut49532)))) {
            // a an b have same sign, or division is exact
            return m;
        } else {
            // a and b have opposite signs and division is not exact
            return AOR_plus(b, m, "org.apache.commons.math3.util.FastMath.floorMod_4028", _mut49534, _mut49535, _mut49536, _mut49537);
        }
    }

    /**
     * Returns the first argument with the sign of the second argument.
     * A NaN {@code sign} argument is treated as positive.
     *
     * @param magnitude the value to return
     * @param sign the sign for the returned value
     * @return the magnitude with the same sign as the {@code sign} argument
     */
    public static double copySign(double magnitude, double sign) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.copySign_4053");
        // don't care about NaN
        final long m = Double.doubleToRawLongBits(magnitude);
        final long s = Double.doubleToRawLongBits(sign);
        if (ROR_greater_equals((m ^ s), 0, "org.apache.commons.math3.util.FastMath.copySign_4053", _mut49538, _mut49539, _mut49540, _mut49541, _mut49542)) {
            return magnitude;
        }
        // flip sign
        return -magnitude;
    }

    /**
     * Returns the first argument with the sign of the second argument.
     * A NaN {@code sign} argument is treated as positive.
     *
     * @param magnitude the value to return
     * @param sign the sign for the returned value
     * @return the magnitude with the same sign as the {@code sign} argument
     */
    public static float copySign(float magnitude, float sign) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.copySign_4074");
        // and negative otherwise.
        final int m = Float.floatToRawIntBits(magnitude);
        final int s = Float.floatToRawIntBits(sign);
        if (ROR_greater_equals((m ^ s), 0, "org.apache.commons.math3.util.FastMath.copySign_4074", _mut49543, _mut49544, _mut49545, _mut49546, _mut49547)) {
            return magnitude;
        }
        // flip sign
        return -magnitude;
    }

    /**
     * Return the exponent of a double number, removing the bias.
     * <p>
     * For double numbers of the form 2<sup>x</sup>, the unbiased
     * exponent is exactly x.
     * </p>
     * @param d number from which exponent is requested
     * @return exponent for d in IEEE754 representation, without bias
     */
    public static int getExponent(final double d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.getExponent_4096");
        // NaN and Infinite will return 1024 anywho so can use raw bits
        return AOR_minus((int) ((Double.doubleToRawLongBits(d) >>> 52) & 0x7ff), 1023, "org.apache.commons.math3.util.FastMath.getExponent_4096", _mut49548, _mut49549, _mut49550, _mut49551);
    }

    /**
     * Return the exponent of a float number, removing the bias.
     * <p>
     * For float numbers of the form 2<sup>x</sup>, the unbiased
     * exponent is exactly x.
     * </p>
     * @param f number from which exponent is requested
     * @return exponent for d in IEEE754 representation, without bias
     */
    public static int getExponent(final float f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.getExponent_4110");
        // NaN and Infinite will return the same exponent anywho so can use raw bits
        return AOR_minus(((Float.floatToRawIntBits(f) >>> 23) & 0xff), 127, "org.apache.commons.math3.util.FastMath.getExponent_4110", _mut49552, _mut49553, _mut49554, _mut49555);
    }

    /**
     * Print out contents of arrays, and check the length.
     * <p>used to generate the preset arrays originally.</p>
     * @param a unused
     */
    public static void main(String[] a) {
        PrintStream out = System.out;
        FastMathCalc.printarray(out, "EXP_INT_TABLE_A", EXP_INT_TABLE_LEN, ExpIntTable.EXP_INT_TABLE_A);
        FastMathCalc.printarray(out, "EXP_INT_TABLE_B", EXP_INT_TABLE_LEN, ExpIntTable.EXP_INT_TABLE_B);
        FastMathCalc.printarray(out, "EXP_FRAC_TABLE_A", EXP_FRAC_TABLE_LEN, ExpFracTable.EXP_FRAC_TABLE_A);
        FastMathCalc.printarray(out, "EXP_FRAC_TABLE_B", EXP_FRAC_TABLE_LEN, ExpFracTable.EXP_FRAC_TABLE_B);
        FastMathCalc.printarray(out, "LN_MANT", LN_MANT_LEN, lnMant.LN_MANT);
        FastMathCalc.printarray(out, "SINE_TABLE_A", SINE_TABLE_LEN, SINE_TABLE_A);
        FastMathCalc.printarray(out, "SINE_TABLE_B", SINE_TABLE_LEN, SINE_TABLE_B);
        FastMathCalc.printarray(out, "COSINE_TABLE_A", SINE_TABLE_LEN, COSINE_TABLE_A);
        FastMathCalc.printarray(out, "COSINE_TABLE_B", SINE_TABLE_LEN, COSINE_TABLE_B);
        FastMathCalc.printarray(out, "TANGENT_TABLE_A", SINE_TABLE_LEN, TANGENT_TABLE_A);
        FastMathCalc.printarray(out, "TANGENT_TABLE_B", SINE_TABLE_LEN, TANGENT_TABLE_B);
    }

    /**
     * Enclose large data table in nested static class so it's only loaded on first access.
     */
    private static class ExpIntTable {

        /**
         * Exponential evaluated at integer values,
         * exp(x) =  expIntTableA[x + EXP_INT_TABLE_MAX_INDEX] + expIntTableB[x+EXP_INT_TABLE_MAX_INDEX].
         */
        private static final double[] EXP_INT_TABLE_A;

        /**
         * Exponential evaluated at integer values,
         * exp(x) =  expIntTableA[x + EXP_INT_TABLE_MAX_INDEX] + expIntTableB[x+EXP_INT_TABLE_MAX_INDEX]
         */
        private static final double[] EXP_INT_TABLE_B;

        static {
            if (RECOMPUTE_TABLES_AT_RUNTIME) {
                EXP_INT_TABLE_A = new double[FastMath.EXP_INT_TABLE_LEN];
                EXP_INT_TABLE_B = new double[FastMath.EXP_INT_TABLE_LEN];
                final double[] tmp = new double[2];
                final double[] recip = new double[2];
                // Populate expIntTable
                for (int i = 0; ROR_less(i, FastMath.EXP_INT_TABLE_MAX_INDEX, "org.apache.commons.math3.util.FastMath.main_4120", _mut49577, _mut49578, _mut49579, _mut49580, _mut49581); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.main_4120");
                    FastMathCalc.expint(i, tmp);
                    EXP_INT_TABLE_A[AOR_plus(i, FastMath.EXP_INT_TABLE_MAX_INDEX, "org.apache.commons.math3.util.FastMath.main_4120", _mut49556, _mut49557, _mut49558, _mut49559)] = tmp[0];
                    EXP_INT_TABLE_B[AOR_plus(i, FastMath.EXP_INT_TABLE_MAX_INDEX, "org.apache.commons.math3.util.FastMath.main_4120", _mut49560, _mut49561, _mut49562, _mut49563)] = tmp[1];
                    if (ROR_not_equals(i, 0, "org.apache.commons.math3.util.FastMath.main_4120", _mut49564, _mut49565, _mut49566, _mut49567, _mut49568)) {
                        // Negative integer powers
                        FastMathCalc.splitReciprocal(tmp, recip);
                        EXP_INT_TABLE_A[AOR_minus(FastMath.EXP_INT_TABLE_MAX_INDEX, i, "org.apache.commons.math3.util.FastMath.main_4120", _mut49569, _mut49570, _mut49571, _mut49572)] = recip[0];
                        EXP_INT_TABLE_B[AOR_minus(FastMath.EXP_INT_TABLE_MAX_INDEX, i, "org.apache.commons.math3.util.FastMath.main_4120", _mut49573, _mut49574, _mut49575, _mut49576)] = recip[1];
                    }
                }
            } else {
                EXP_INT_TABLE_A = FastMathLiteralArrays.loadExpIntA();
                EXP_INT_TABLE_B = FastMathLiteralArrays.loadExpIntB();
            }
        }
    }

    /**
     * Enclose large data table in nested static class so it's only loaded on first access.
     */
    private static class ExpFracTable {

        /**
         * Exponential over the range of 0 - 1 in increments of 2^-10
         * exp(x/1024) =  expFracTableA[x] + expFracTableB[x].
         * 1024 = 2^10
         */
        private static final double[] EXP_FRAC_TABLE_A;

        /**
         * Exponential over the range of 0 - 1 in increments of 2^-10
         * exp(x/1024) =  expFracTableA[x] + expFracTableB[x].
         */
        private static final double[] EXP_FRAC_TABLE_B;

        static {
            if (RECOMPUTE_TABLES_AT_RUNTIME) {
                EXP_FRAC_TABLE_A = new double[FastMath.EXP_FRAC_TABLE_LEN];
                EXP_FRAC_TABLE_B = new double[FastMath.EXP_FRAC_TABLE_LEN];
                final double[] tmp = new double[2];
                // Populate expFracTable
                final double factor = AOR_divide(1d, (AOR_minus(EXP_FRAC_TABLE_LEN, 1, "org.apache.commons.math3.util.FastMath.main_4120", _mut49582, _mut49583, _mut49584, _mut49585)), "org.apache.commons.math3.util.FastMath.main_4120", _mut49586, _mut49587, _mut49588, _mut49589);
                for (int i = 0; ROR_less(i, EXP_FRAC_TABLE_A.length, "org.apache.commons.math3.util.FastMath.main_4120", _mut49594, _mut49595, _mut49596, _mut49597, _mut49598); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.main_4120");
                    FastMathCalc.slowexp(AOR_multiply(i, factor, "org.apache.commons.math3.util.FastMath.main_4120", _mut49590, _mut49591, _mut49592, _mut49593), tmp);
                    EXP_FRAC_TABLE_A[i] = tmp[0];
                    EXP_FRAC_TABLE_B[i] = tmp[1];
                }
            } else {
                EXP_FRAC_TABLE_A = FastMathLiteralArrays.loadExpFracA();
                EXP_FRAC_TABLE_B = FastMathLiteralArrays.loadExpFracB();
            }
        }
    }

    /**
     * Enclose large data table in nested static class so it's only loaded on first access.
     */
    private static class lnMant {

        /**
         * Extended precision logarithm table over the range 1 - 2 in increments of 2^-10.
         */
        private static final double[][] LN_MANT;

        static {
            if (RECOMPUTE_TABLES_AT_RUNTIME) {
                LN_MANT = new double[FastMath.LN_MANT_LEN][];
                // Populate lnMant table
                for (int i = 0; ROR_less(i, LN_MANT.length, "org.apache.commons.math3.util.FastMath.main_4120", _mut49599, _mut49600, _mut49601, _mut49602, _mut49603); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.main_4120");
                    final double d = Double.longBitsToDouble((((long) i) << 42) | 0x3ff0000000000000L);
                    LN_MANT[i] = FastMathCalc.slowLog(d);
                }
            } else {
                LN_MANT = FastMathLiteralArrays.loadLnMant();
            }
        }
    }

    /**
     * Enclose the Cody/Waite reduction (used in "sin", "cos" and "tan").
     */
    private static class CodyWaite {

        /**
         * k
         */
        private final int finalK;

        /**
         * remA
         */
        private final double finalRemA;

        /**
         * remB
         */
        private final double finalRemB;

        /**
         * @param xa Argument.
         */
        CodyWaite(double xa) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.CodyWaite_4239");
            // k = (int)(xa / 1.5707963267948966);
            int k = (int) (AOR_multiply(xa, 0.6366197723675814, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49604, _mut49605, _mut49606, _mut49607));
            // Compute remainder.
            double remA;
            double remB;
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.FastMath.CodyWaite_4239");
                double a = AOR_multiply(-k, 1.570796251296997, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49608, _mut49609, _mut49610, _mut49611);
                remA = AOR_plus(xa, a, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49612, _mut49613, _mut49614, _mut49615);
                remB = -(AOR_minus(AOR_minus(remA, xa, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49616, _mut49617, _mut49618, _mut49619), a, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49620, _mut49621, _mut49622, _mut49623));
                a = AOR_multiply(-k, 7.549789948768648E-8, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49624, _mut49625, _mut49626, _mut49627);
                double b = remA;
                remA = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49628, _mut49629, _mut49630, _mut49631);
                remB += -(AOR_minus(AOR_minus(remA, b, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49632, _mut49633, _mut49634, _mut49635), a, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49636, _mut49637, _mut49638, _mut49639));
                a = AOR_multiply(-k, 6.123233995736766E-17, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49640, _mut49641, _mut49642, _mut49643);
                b = remA;
                remA = AOR_plus(a, b, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49644, _mut49645, _mut49646, _mut49647);
                remB += -(AOR_minus(AOR_minus(remA, b, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49648, _mut49649, _mut49650, _mut49651), a, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49652, _mut49653, _mut49654, _mut49655));
                if (ROR_greater(remA, 0, "org.apache.commons.math3.util.FastMath.CodyWaite_4239", _mut49656, _mut49657, _mut49658, _mut49659, _mut49660)) {
                    break;
                }
                // to an even multiple of pi/2.
                --k;
            }
            this.finalK = k;
            this.finalRemA = remA;
            this.finalRemB = remB;
        }

        /**
         * @return k
         */
        int getK() {
            return finalK;
        }

        /**
         * @return remA
         */
        double getRemA() {
            return finalRemA;
        }

        /**
         * @return remB
         */
        double getRemB() {
            return finalRemB;
        }
    }
}
