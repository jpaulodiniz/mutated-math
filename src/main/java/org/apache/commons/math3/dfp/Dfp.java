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
package org.apache.commons.math3.dfp;

import java.util.Arrays;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 *  Decimal floating point library for Java
 *
 *  <p>Another floating point class.  This one is built using radix 10000
 *  which is 10<sup>4</sup>, so its almost decimal.</p>
 *
 *  <p>The design goals here are:
 *  <ol>
 *    <li>Decimal math, or close to it</li>
 *    <li>Settable precision (but no mix between numbers using different settings)</li>
 *    <li>Portability.  Code should be kept as portable as possible.</li>
 *    <li>Performance</li>
 *    <li>Accuracy  - Results should always be +/- 1 ULP for basic
 *         algebraic operation</li>
 *    <li>Comply with IEEE 854-1987 as much as possible.
 *         (See IEEE 854-1987 notes below)</li>
 *  </ol></p>
 *
 *  <p>Trade offs:
 *  <ol>
 *    <li>Memory foot print.  I'm using more memory than necessary to
 *         represent numbers to get better performance.</li>
 *    <li>Digits are bigger, so rounding is a greater loss.  So, if you
 *         really need 12 decimal digits, better use 4 base 10000 digits
 *         there can be one partially filled.</li>
 *  </ol></p>
 *
 *  <p>Numbers are represented  in the following form:
 *  <pre>
 *  n  =  sign &times; mant &times; (radix)<sup>exp</sup>;</p>
 *  </pre>
 *  where sign is &plusmn;1, mantissa represents a fractional number between
 *  zero and one.  mant[0] is the least significant digit.
 *  exp is in the range of -32767 to 32768</p>
 *
 *  <p>IEEE 854-1987  Notes and differences</p>
 *
 *  <p>IEEE 854 requires the radix to be either 2 or 10.  The radix here is
 *  10000, so that requirement is not met, but  it is possible that a
 *  subclassed can be made to make it behave as a radix 10
 *  number.  It is my opinion that if it looks and behaves as a radix
 *  10 number then it is one and that requirement would be met.</p>
 *
 *  <p>The radix of 10000 was chosen because it should be faster to operate
 *  on 4 decimal digits at once instead of one at a time.  Radix 10 behavior
 *  can be realized by adding an additional rounding step to ensure that
 *  the number of decimal digits represented is constant.</p>
 *
 *  <p>The IEEE standard specifically leaves out internal data encoding,
 *  so it is reasonable to conclude that such a subclass of this radix
 *  10000 system is merely an encoding of a radix 10 system.</p>
 *
 *  <p>IEEE 854 also specifies the existence of "sub-normal" numbers.  This
 *  class does not contain any such entities.  The most significant radix
 *  10000 digit is always non-zero.  Instead, we support "gradual underflow"
 *  by raising the underflow flag for numbers less with exponent less than
 *  expMin, but don't flush to zero until the exponent reaches MIN_EXP-digits.
 *  Thus the smallest number we can represent would be:
 *  1E(-(MIN_EXP-digits-1)*4),  eg, for digits=5, MIN_EXP=-32767, that would
 *  be 1e-131092.</p>
 *
 *  <p>IEEE 854 defines that the implied radix point lies just to the right
 *  of the most significant digit and to the left of the remaining digits.
 *  This implementation puts the implied radix point to the left of all
 *  digits including the most significant one.  The most significant digit
 *  here is the one just to the right of the radix point.  This is a fine
 *  detail and is really only a matter of definition.  Any side effects of
 *  this can be rendered invisible by a subclass.</p>
 * @see DfpField
 * @since 2.2
 */
public class Dfp implements RealFieldElement<Dfp> {

    @Conditional
    public static boolean _mut87025 = false, _mut87026 = false, _mut87027 = false, _mut87028 = false, _mut87029 = false, _mut87030 = false, _mut87031 = false, _mut87032 = false, _mut87033 = false, _mut87034 = false, _mut87035 = false, _mut87036 = false, _mut87037 = false, _mut87038 = false, _mut87039 = false, _mut87040 = false, _mut87041 = false, _mut87042 = false, _mut87043 = false, _mut87044 = false, _mut87045 = false, _mut87046 = false, _mut87047 = false, _mut87048 = false, _mut87049 = false, _mut87050 = false, _mut87051 = false, _mut87052 = false, _mut87053 = false, _mut87054 = false, _mut87055 = false, _mut87056 = false, _mut87057 = false, _mut87058 = false, _mut87059 = false, _mut87060 = false, _mut87061 = false, _mut87062 = false, _mut87063 = false, _mut87064 = false, _mut87065 = false, _mut87066 = false, _mut87067 = false, _mut87068 = false, _mut87069 = false, _mut87070 = false, _mut87071 = false, _mut87072 = false, _mut87073 = false, _mut87074 = false, _mut87075 = false, _mut87076 = false, _mut87077 = false, _mut87078 = false, _mut87079 = false, _mut87080 = false, _mut87081 = false, _mut87082 = false, _mut87083 = false, _mut87084 = false, _mut87085 = false, _mut87086 = false, _mut87087 = false, _mut87088 = false, _mut87089 = false, _mut87090 = false, _mut87091 = false, _mut87092 = false, _mut87093 = false, _mut87094 = false, _mut87095 = false, _mut87096 = false, _mut87097 = false, _mut87098 = false, _mut87099 = false, _mut87100 = false, _mut87101 = false, _mut87102 = false, _mut87103 = false, _mut87104 = false, _mut87105 = false, _mut87106 = false, _mut87107 = false, _mut87108 = false, _mut87109 = false, _mut87110 = false, _mut87111 = false, _mut87112 = false, _mut87113 = false, _mut87114 = false, _mut87115 = false, _mut87116 = false, _mut87117 = false, _mut87118 = false, _mut87119 = false, _mut87120 = false, _mut87121 = false, _mut87122 = false, _mut87123 = false, _mut87124 = false, _mut87125 = false, _mut87126 = false, _mut87127 = false, _mut87128 = false, _mut87129 = false, _mut87130 = false, _mut87131 = false, _mut87132 = false, _mut87133 = false, _mut87134 = false, _mut87135 = false, _mut87136 = false, _mut87137 = false, _mut87138 = false, _mut87139 = false, _mut87140 = false, _mut87141 = false, _mut87142 = false, _mut87143 = false, _mut87144 = false, _mut87145 = false, _mut87146 = false, _mut87147 = false, _mut87148 = false, _mut87149 = false, _mut87150 = false, _mut87151 = false, _mut87152 = false, _mut87153 = false, _mut87154 = false, _mut87155 = false, _mut87156 = false, _mut87157 = false, _mut87158 = false, _mut87159 = false, _mut87160 = false, _mut87161 = false, _mut87162 = false, _mut87163 = false, _mut87164 = false, _mut87165 = false, _mut87166 = false, _mut87167 = false, _mut87168 = false, _mut87169 = false, _mut87170 = false, _mut87171 = false, _mut87172 = false, _mut87173 = false, _mut87174 = false, _mut87175 = false, _mut87176 = false, _mut87177 = false, _mut87178 = false, _mut87179 = false, _mut87180 = false, _mut87181 = false, _mut87182 = false, _mut87183 = false, _mut87184 = false, _mut87185 = false, _mut87186 = false, _mut87187 = false, _mut87188 = false, _mut87189 = false, _mut87190 = false, _mut87191 = false, _mut87192 = false, _mut87193 = false, _mut87194 = false, _mut87195 = false, _mut87196 = false, _mut87197 = false, _mut87198 = false, _mut87199 = false, _mut87200 = false, _mut87201 = false, _mut87202 = false, _mut87203 = false, _mut87204 = false, _mut87205 = false, _mut87206 = false, _mut87207 = false, _mut87208 = false, _mut87209 = false, _mut87210 = false, _mut87211 = false, _mut87212 = false, _mut87213 = false, _mut87214 = false, _mut87215 = false, _mut87216 = false, _mut87217 = false, _mut87218 = false, _mut87219 = false, _mut87220 = false, _mut87221 = false, _mut87222 = false, _mut87223 = false, _mut87224 = false, _mut87225 = false, _mut87226 = false, _mut87227 = false, _mut87228 = false, _mut87229 = false, _mut87230 = false, _mut87231 = false, _mut87232 = false, _mut87233 = false, _mut87234 = false, _mut87235 = false, _mut87236 = false, _mut87237 = false, _mut87238 = false, _mut87239 = false, _mut87240 = false, _mut87241 = false, _mut87242 = false, _mut87243 = false, _mut87244 = false, _mut87245 = false, _mut87246 = false, _mut87247 = false, _mut87248 = false, _mut87249 = false, _mut87250 = false, _mut87251 = false, _mut87252 = false, _mut87253 = false, _mut87254 = false, _mut87255 = false, _mut87256 = false, _mut87257 = false, _mut87258 = false, _mut87259 = false, _mut87260 = false, _mut87261 = false, _mut87262 = false, _mut87263 = false, _mut87264 = false, _mut87265 = false, _mut87266 = false, _mut87267 = false, _mut87268 = false, _mut87269 = false, _mut87270 = false, _mut87271 = false, _mut87272 = false, _mut87273 = false, _mut87274 = false, _mut87275 = false, _mut87276 = false, _mut87277 = false, _mut87278 = false, _mut87279 = false, _mut87280 = false, _mut87281 = false, _mut87282 = false, _mut87283 = false, _mut87284 = false, _mut87285 = false, _mut87286 = false, _mut87287 = false, _mut87288 = false, _mut87289 = false, _mut87290 = false, _mut87291 = false, _mut87292 = false, _mut87293 = false, _mut87294 = false, _mut87295 = false, _mut87296 = false, _mut87297 = false, _mut87298 = false, _mut87299 = false, _mut87300 = false, _mut87301 = false, _mut87302 = false, _mut87303 = false, _mut87304 = false, _mut87305 = false, _mut87306 = false, _mut87307 = false, _mut87308 = false, _mut87309 = false, _mut87310 = false, _mut87311 = false, _mut87312 = false, _mut87313 = false, _mut87314 = false, _mut87315 = false, _mut87316 = false, _mut87317 = false, _mut87318 = false, _mut87319 = false, _mut87320 = false, _mut87321 = false, _mut87322 = false, _mut87323 = false, _mut87324 = false, _mut87325 = false, _mut87326 = false, _mut87327 = false, _mut87328 = false, _mut87329 = false, _mut87330 = false, _mut87331 = false, _mut87332 = false, _mut87333 = false, _mut87334 = false, _mut87335 = false, _mut87336 = false, _mut87337 = false, _mut87338 = false, _mut87339 = false, _mut87340 = false, _mut87341 = false, _mut87342 = false, _mut87343 = false, _mut87344 = false, _mut87345 = false, _mut87346 = false, _mut87347 = false, _mut87348 = false, _mut87349 = false, _mut87350 = false, _mut87351 = false, _mut87352 = false, _mut87353 = false, _mut87354 = false, _mut87355 = false, _mut87356 = false, _mut87357 = false, _mut87358 = false, _mut87359 = false, _mut87360 = false, _mut87361 = false, _mut87362 = false, _mut87363 = false, _mut87364 = false, _mut87365 = false, _mut87366 = false, _mut87367 = false, _mut87368 = false, _mut87369 = false, _mut87370 = false, _mut87371 = false, _mut87372 = false, _mut87373 = false, _mut87374 = false, _mut87375 = false, _mut87376 = false, _mut87377 = false, _mut87378 = false, _mut87379 = false, _mut87380 = false, _mut87381 = false, _mut87382 = false, _mut87383 = false, _mut87384 = false, _mut87385 = false, _mut87386 = false, _mut87387 = false, _mut87388 = false, _mut87389 = false, _mut87390 = false, _mut87391 = false, _mut87392 = false, _mut87393 = false, _mut87394 = false, _mut87395 = false, _mut87396 = false, _mut87397 = false, _mut87398 = false, _mut87399 = false, _mut87400 = false, _mut87401 = false, _mut87402 = false, _mut87403 = false, _mut87404 = false, _mut87405 = false, _mut87406 = false, _mut87407 = false, _mut87408 = false, _mut87409 = false, _mut87410 = false, _mut87411 = false, _mut87412 = false, _mut87413 = false, _mut87414 = false, _mut87415 = false, _mut87416 = false, _mut87417 = false, _mut87418 = false, _mut87419 = false, _mut87420 = false, _mut87421 = false, _mut87422 = false, _mut87423 = false, _mut87424 = false, _mut87425 = false, _mut87426 = false, _mut87427 = false, _mut87428 = false, _mut87429 = false, _mut87430 = false, _mut87431 = false, _mut87432 = false, _mut87433 = false, _mut87434 = false, _mut87435 = false, _mut87436 = false, _mut87437 = false, _mut87438 = false, _mut87439 = false, _mut87440 = false, _mut87441 = false, _mut87442 = false, _mut87443 = false, _mut87444 = false, _mut87445 = false, _mut87446 = false, _mut87447 = false, _mut87448 = false, _mut87449 = false, _mut87450 = false, _mut87451 = false, _mut87452 = false, _mut87453 = false, _mut87454 = false, _mut87455 = false, _mut87456 = false, _mut87457 = false, _mut87458 = false, _mut87459 = false, _mut87460 = false, _mut87461 = false, _mut87462 = false, _mut87463 = false, _mut87464 = false, _mut87465 = false, _mut87466 = false, _mut87467 = false, _mut87468 = false, _mut87469 = false, _mut87470 = false, _mut87471 = false, _mut87472 = false, _mut87473 = false, _mut87474 = false, _mut87475 = false, _mut87476 = false, _mut87477 = false, _mut87478 = false, _mut87479 = false, _mut87480 = false, _mut87481 = false, _mut87482 = false, _mut87483 = false, _mut87484 = false, _mut87485 = false, _mut87486 = false, _mut87487 = false, _mut87488 = false, _mut87489 = false, _mut87490 = false, _mut87491 = false, _mut87492 = false, _mut87493 = false, _mut87494 = false, _mut87495 = false, _mut87496 = false, _mut87497 = false, _mut87498 = false, _mut87499 = false, _mut87500 = false, _mut87501 = false, _mut87502 = false, _mut87503 = false, _mut87504 = false, _mut87505 = false, _mut87506 = false, _mut87507 = false, _mut87508 = false, _mut87509 = false, _mut87510 = false, _mut87511 = false, _mut87512 = false, _mut87513 = false, _mut87514 = false, _mut87515 = false, _mut87516 = false, _mut87517 = false, _mut87518 = false, _mut87519 = false, _mut87520 = false, _mut87521 = false, _mut87522 = false, _mut87523 = false, _mut87524 = false, _mut87525 = false, _mut87526 = false, _mut87527 = false, _mut87528 = false, _mut87529 = false, _mut87530 = false, _mut87531 = false, _mut87532 = false, _mut87533 = false, _mut87534 = false, _mut87535 = false, _mut87536 = false, _mut87537 = false, _mut87538 = false, _mut87539 = false, _mut87540 = false, _mut87541 = false, _mut87542 = false, _mut87543 = false, _mut87544 = false, _mut87545 = false, _mut87546 = false, _mut87547 = false, _mut87548 = false, _mut87549 = false, _mut87550 = false, _mut87551 = false, _mut87552 = false, _mut87553 = false, _mut87554 = false, _mut87555 = false, _mut87556 = false, _mut87557 = false, _mut87558 = false, _mut87559 = false, _mut87560 = false, _mut87561 = false, _mut87562 = false, _mut87563 = false, _mut87564 = false, _mut87565 = false, _mut87566 = false, _mut87567 = false, _mut87568 = false, _mut87569 = false, _mut87570 = false, _mut87571 = false, _mut87572 = false, _mut87573 = false, _mut87574 = false, _mut87575 = false, _mut87576 = false, _mut87577 = false, _mut87578 = false, _mut87579 = false, _mut87580 = false, _mut87581 = false, _mut87582 = false, _mut87583 = false, _mut87584 = false, _mut87585 = false, _mut87586 = false, _mut87587 = false, _mut87588 = false, _mut87589 = false, _mut87590 = false, _mut87591 = false, _mut87592 = false, _mut87593 = false, _mut87594 = false, _mut87595 = false, _mut87596 = false, _mut87597 = false, _mut87598 = false, _mut87599 = false, _mut87600 = false, _mut87601 = false, _mut87602 = false, _mut87603 = false, _mut87604 = false, _mut87605 = false, _mut87606 = false, _mut87607 = false, _mut87608 = false, _mut87609 = false, _mut87610 = false, _mut87611 = false, _mut87612 = false, _mut87613 = false, _mut87614 = false, _mut87615 = false, _mut87616 = false, _mut87617 = false, _mut87618 = false, _mut87619 = false, _mut87620 = false, _mut87621 = false, _mut87622 = false, _mut87623 = false, _mut87624 = false, _mut87625 = false, _mut87626 = false, _mut87627 = false, _mut87628 = false, _mut87629 = false, _mut87630 = false, _mut87631 = false, _mut87632 = false, _mut87633 = false, _mut87634 = false, _mut87635 = false, _mut87636 = false, _mut87637 = false, _mut87638 = false, _mut87639 = false, _mut87640 = false, _mut87641 = false, _mut87642 = false, _mut87643 = false, _mut87644 = false, _mut87645 = false, _mut87646 = false, _mut87647 = false, _mut87648 = false, _mut87649 = false, _mut87650 = false, _mut87651 = false, _mut87652 = false, _mut87653 = false, _mut87654 = false, _mut87655 = false, _mut87656 = false, _mut87657 = false, _mut87658 = false, _mut87659 = false, _mut87660 = false, _mut87661 = false, _mut87662 = false, _mut87663 = false, _mut87664 = false, _mut87665 = false, _mut87666 = false, _mut87667 = false, _mut87668 = false, _mut87669 = false, _mut87670 = false, _mut87671 = false, _mut87672 = false, _mut87673 = false, _mut87674 = false, _mut87675 = false, _mut87676 = false, _mut87677 = false, _mut87678 = false, _mut87679 = false, _mut87680 = false, _mut87681 = false, _mut87682 = false, _mut87683 = false, _mut87684 = false, _mut87685 = false, _mut87686 = false, _mut87687 = false, _mut87688 = false, _mut87689 = false, _mut87690 = false, _mut87691 = false, _mut87692 = false, _mut87693 = false, _mut87694 = false, _mut87695 = false, _mut87696 = false, _mut87697 = false, _mut87698 = false, _mut87699 = false, _mut87700 = false, _mut87701 = false, _mut87702 = false, _mut87703 = false, _mut87704 = false, _mut87705 = false, _mut87706 = false, _mut87707 = false, _mut87708 = false, _mut87709 = false, _mut87710 = false, _mut87711 = false, _mut87712 = false, _mut87713 = false, _mut87714 = false, _mut87715 = false, _mut87716 = false, _mut87717 = false, _mut87718 = false, _mut87719 = false, _mut87720 = false, _mut87721 = false, _mut87722 = false, _mut87723 = false, _mut87724 = false, _mut87725 = false, _mut87726 = false, _mut87727 = false, _mut87728 = false, _mut87729 = false, _mut87730 = false, _mut87731 = false, _mut87732 = false, _mut87733 = false, _mut87734 = false, _mut87735 = false, _mut87736 = false, _mut87737 = false, _mut87738 = false, _mut87739 = false, _mut87740 = false, _mut87741 = false, _mut87742 = false, _mut87743 = false, _mut87744 = false, _mut87745 = false, _mut87746 = false, _mut87747 = false, _mut87748 = false, _mut87749 = false, _mut87750 = false, _mut87751 = false, _mut87752 = false, _mut87753 = false, _mut87754 = false, _mut87755 = false, _mut87756 = false, _mut87757 = false, _mut87758 = false, _mut87759 = false, _mut87760 = false, _mut87761 = false, _mut87762 = false, _mut87763 = false, _mut87764 = false, _mut87765 = false, _mut87766 = false, _mut87767 = false, _mut87768 = false, _mut87769 = false, _mut87770 = false, _mut87771 = false, _mut87772 = false, _mut87773 = false, _mut87774 = false, _mut87775 = false, _mut87776 = false, _mut87777 = false, _mut87778 = false, _mut87779 = false, _mut87780 = false, _mut87781 = false, _mut87782 = false, _mut87783 = false, _mut87784 = false, _mut87785 = false, _mut87786 = false, _mut87787 = false, _mut87788 = false, _mut87789 = false, _mut87790 = false, _mut87791 = false, _mut87792 = false, _mut87793 = false, _mut87794 = false, _mut87795 = false, _mut87796 = false, _mut87797 = false, _mut87798 = false, _mut87799 = false, _mut87800 = false, _mut87801 = false, _mut87802 = false, _mut87803 = false, _mut87804 = false, _mut87805 = false, _mut87806 = false, _mut87807 = false, _mut87808 = false, _mut87809 = false, _mut87810 = false, _mut87811 = false, _mut87812 = false, _mut87813 = false, _mut87814 = false, _mut87815 = false, _mut87816 = false, _mut87817 = false, _mut87818 = false, _mut87819 = false, _mut87820 = false, _mut87821 = false, _mut87822 = false, _mut87823 = false, _mut87824 = false, _mut87825 = false, _mut87826 = false, _mut87827 = false, _mut87828 = false, _mut87829 = false, _mut87830 = false, _mut87831 = false, _mut87832 = false, _mut87833 = false, _mut87834 = false, _mut87835 = false, _mut87836 = false, _mut87837 = false, _mut87838 = false, _mut87839 = false, _mut87840 = false, _mut87841 = false, _mut87842 = false, _mut87843 = false, _mut87844 = false, _mut87845 = false, _mut87846 = false, _mut87847 = false, _mut87848 = false, _mut87849 = false, _mut87850 = false, _mut87851 = false, _mut87852 = false, _mut87853 = false, _mut87854 = false, _mut87855 = false, _mut87856 = false, _mut87857 = false, _mut87858 = false, _mut87859 = false, _mut87860 = false, _mut87861 = false, _mut87862 = false, _mut87863 = false, _mut87864 = false, _mut87865 = false, _mut87866 = false, _mut87867 = false, _mut87868 = false, _mut87869 = false, _mut87870 = false, _mut87871 = false, _mut87872 = false, _mut87873 = false, _mut87874 = false, _mut87875 = false, _mut87876 = false, _mut87877 = false, _mut87878 = false, _mut87879 = false, _mut87880 = false, _mut87881 = false, _mut87882 = false, _mut87883 = false, _mut87884 = false, _mut87885 = false, _mut87886 = false, _mut87887 = false, _mut87888 = false, _mut87889 = false, _mut87890 = false, _mut87891 = false, _mut87892 = false, _mut87893 = false, _mut87894 = false, _mut87895 = false, _mut87896 = false, _mut87897 = false, _mut87898 = false, _mut87899 = false, _mut87900 = false, _mut87901 = false, _mut87902 = false, _mut87903 = false, _mut87904 = false, _mut87905 = false, _mut87906 = false, _mut87907 = false, _mut87908 = false, _mut87909 = false, _mut87910 = false, _mut87911 = false, _mut87912 = false, _mut87913 = false, _mut87914 = false, _mut87915 = false, _mut87916 = false, _mut87917 = false, _mut87918 = false, _mut87919 = false, _mut87920 = false, _mut87921 = false, _mut87922 = false, _mut87923 = false, _mut87924 = false, _mut87925 = false, _mut87926 = false, _mut87927 = false, _mut87928 = false, _mut87929 = false, _mut87930 = false, _mut87931 = false, _mut87932 = false, _mut87933 = false, _mut87934 = false, _mut87935 = false, _mut87936 = false, _mut87937 = false, _mut87938 = false, _mut87939 = false, _mut87940 = false, _mut87941 = false, _mut87942 = false, _mut87943 = false, _mut87944 = false, _mut87945 = false, _mut87946 = false, _mut87947 = false, _mut87948 = false, _mut87949 = false, _mut87950 = false, _mut87951 = false, _mut87952 = false, _mut87953 = false, _mut87954 = false, _mut87955 = false, _mut87956 = false, _mut87957 = false, _mut87958 = false, _mut87959 = false, _mut87960 = false, _mut87961 = false, _mut87962 = false, _mut87963 = false, _mut87964 = false, _mut87965 = false, _mut87966 = false, _mut87967 = false, _mut87968 = false, _mut87969 = false, _mut87970 = false, _mut87971 = false, _mut87972 = false, _mut87973 = false, _mut87974 = false, _mut87975 = false, _mut87976 = false, _mut87977 = false, _mut87978 = false, _mut87979 = false, _mut87980 = false, _mut87981 = false, _mut87982 = false, _mut87983 = false, _mut87984 = false, _mut87985 = false, _mut87986 = false, _mut87987 = false, _mut87988 = false, _mut87989 = false, _mut87990 = false, _mut87991 = false, _mut87992 = false, _mut87993 = false, _mut87994 = false, _mut87995 = false, _mut87996 = false, _mut87997 = false, _mut87998 = false, _mut87999 = false, _mut88000 = false, _mut88001 = false, _mut88002 = false, _mut88003 = false, _mut88004 = false, _mut88005 = false, _mut88006 = false, _mut88007 = false, _mut88008 = false, _mut88009 = false, _mut88010 = false, _mut88011 = false, _mut88012 = false, _mut88013 = false, _mut88014 = false, _mut88015 = false, _mut88016 = false, _mut88017 = false, _mut88018 = false, _mut88019 = false, _mut88020 = false, _mut88021 = false, _mut88022 = false, _mut88023 = false, _mut88024 = false, _mut88025 = false, _mut88026 = false, _mut88027 = false, _mut88028 = false, _mut88029 = false, _mut88030 = false, _mut88031 = false, _mut88032 = false, _mut88033 = false, _mut88034 = false, _mut88035 = false, _mut88036 = false, _mut88037 = false, _mut88038 = false, _mut88039 = false, _mut88040 = false, _mut88041 = false, _mut88042 = false, _mut88043 = false, _mut88044 = false, _mut88045 = false, _mut88046 = false, _mut88047 = false, _mut88048 = false, _mut88049 = false, _mut88050 = false, _mut88051 = false, _mut88052 = false, _mut88053 = false, _mut88054 = false, _mut88055 = false, _mut88056 = false, _mut88057 = false, _mut88058 = false, _mut88059 = false, _mut88060 = false, _mut88061 = false, _mut88062 = false, _mut88063 = false, _mut88064 = false, _mut88065 = false, _mut88066 = false, _mut88067 = false, _mut88068 = false, _mut88069 = false, _mut88070 = false, _mut88071 = false, _mut88072 = false, _mut88073 = false, _mut88074 = false, _mut88075 = false, _mut88076 = false, _mut88077 = false, _mut88078 = false, _mut88079 = false, _mut88080 = false, _mut88081 = false, _mut88082 = false, _mut88083 = false, _mut88084 = false, _mut88085 = false, _mut88086 = false, _mut88087 = false, _mut88088 = false, _mut88089 = false, _mut88090 = false, _mut88091 = false, _mut88092 = false, _mut88093 = false, _mut88094 = false, _mut88095 = false, _mut88096 = false, _mut88097 = false, _mut88098 = false, _mut88099 = false, _mut88100 = false, _mut88101 = false, _mut88102 = false, _mut88103 = false, _mut88104 = false, _mut88105 = false, _mut88106 = false, _mut88107 = false, _mut88108 = false, _mut88109 = false, _mut88110 = false, _mut88111 = false, _mut88112 = false, _mut88113 = false, _mut88114 = false, _mut88115 = false, _mut88116 = false, _mut88117 = false, _mut88118 = false, _mut88119 = false, _mut88120 = false, _mut88121 = false, _mut88122 = false, _mut88123 = false, _mut88124 = false, _mut88125 = false, _mut88126 = false, _mut88127 = false, _mut88128 = false, _mut88129 = false, _mut88130 = false, _mut88131 = false, _mut88132 = false, _mut88133 = false, _mut88134 = false, _mut88135 = false, _mut88136 = false, _mut88137 = false, _mut88138 = false, _mut88139 = false, _mut88140 = false, _mut88141 = false, _mut88142 = false, _mut88143 = false, _mut88144 = false, _mut88145 = false, _mut88146 = false, _mut88147 = false, _mut88148 = false, _mut88149 = false, _mut88150 = false, _mut88151 = false, _mut88152 = false, _mut88153 = false, _mut88154 = false, _mut88155 = false, _mut88156 = false, _mut88157 = false, _mut88158 = false, _mut88159 = false, _mut88160 = false, _mut88161 = false, _mut88162 = false, _mut88163 = false, _mut88164 = false, _mut88165 = false, _mut88166 = false, _mut88167 = false, _mut88168 = false, _mut88169 = false, _mut88170 = false, _mut88171 = false, _mut88172 = false, _mut88173 = false, _mut88174 = false, _mut88175 = false, _mut88176 = false, _mut88177 = false, _mut88178 = false, _mut88179 = false, _mut88180 = false, _mut88181 = false, _mut88182 = false, _mut88183 = false, _mut88184 = false, _mut88185 = false, _mut88186 = false, _mut88187 = false, _mut88188 = false, _mut88189 = false, _mut88190 = false, _mut88191 = false, _mut88192 = false, _mut88193 = false, _mut88194 = false, _mut88195 = false, _mut88196 = false, _mut88197 = false, _mut88198 = false, _mut88199 = false, _mut88200 = false, _mut88201 = false, _mut88202 = false, _mut88203 = false, _mut88204 = false, _mut88205 = false, _mut88206 = false, _mut88207 = false, _mut88208 = false, _mut88209 = false, _mut88210 = false, _mut88211 = false, _mut88212 = false, _mut88213 = false, _mut88214 = false, _mut88215 = false, _mut88216 = false, _mut88217 = false, _mut88218 = false, _mut88219 = false, _mut88220 = false, _mut88221 = false, _mut88222 = false, _mut88223 = false, _mut88224 = false, _mut88225 = false, _mut88226 = false, _mut88227 = false, _mut88228 = false, _mut88229 = false, _mut88230 = false, _mut88231 = false, _mut88232 = false, _mut88233 = false, _mut88234 = false, _mut88235 = false, _mut88236 = false, _mut88237 = false, _mut88238 = false, _mut88239 = false, _mut88240 = false, _mut88241 = false, _mut88242 = false, _mut88243 = false, _mut88244 = false, _mut88245 = false, _mut88246 = false, _mut88247 = false, _mut88248 = false, _mut88249 = false, _mut88250 = false, _mut88251 = false, _mut88252 = false, _mut88253 = false, _mut88254 = false, _mut88255 = false, _mut88256 = false, _mut88257 = false, _mut88258 = false, _mut88259 = false, _mut88260 = false, _mut88261 = false, _mut88262 = false, _mut88263 = false, _mut88264 = false, _mut88265 = false, _mut88266 = false, _mut88267 = false, _mut88268 = false, _mut88269 = false, _mut88270 = false, _mut88271 = false, _mut88272 = false, _mut88273 = false, _mut88274 = false, _mut88275 = false, _mut88276 = false, _mut88277 = false, _mut88278 = false, _mut88279 = false, _mut88280 = false, _mut88281 = false, _mut88282 = false, _mut88283 = false, _mut88284 = false, _mut88285 = false, _mut88286 = false, _mut88287 = false, _mut88288 = false, _mut88289 = false, _mut88290 = false, _mut88291 = false, _mut88292 = false, _mut88293 = false, _mut88294 = false, _mut88295 = false, _mut88296 = false, _mut88297 = false, _mut88298 = false, _mut88299 = false, _mut88300 = false, _mut88301 = false, _mut88302 = false, _mut88303 = false, _mut88304 = false, _mut88305 = false, _mut88306 = false, _mut88307 = false, _mut88308 = false, _mut88309 = false, _mut88310 = false, _mut88311 = false, _mut88312 = false, _mut88313 = false, _mut88314 = false, _mut88315 = false, _mut88316 = false, _mut88317 = false, _mut88318 = false, _mut88319 = false, _mut88320 = false, _mut88321 = false, _mut88322 = false, _mut88323 = false, _mut88324 = false, _mut88325 = false, _mut88326 = false, _mut88327 = false, _mut88328 = false, _mut88329 = false, _mut88330 = false, _mut88331 = false, _mut88332 = false, _mut88333 = false, _mut88334 = false, _mut88335 = false, _mut88336 = false, _mut88337 = false, _mut88338 = false, _mut88339 = false, _mut88340 = false, _mut88341 = false, _mut88342 = false, _mut88343 = false, _mut88344 = false, _mut88345 = false, _mut88346 = false, _mut88347 = false, _mut88348 = false, _mut88349 = false, _mut88350 = false, _mut88351 = false, _mut88352 = false, _mut88353 = false, _mut88354 = false, _mut88355 = false, _mut88356 = false, _mut88357 = false, _mut88358 = false, _mut88359 = false, _mut88360 = false, _mut88361 = false, _mut88362 = false, _mut88363 = false, _mut88364 = false, _mut88365 = false, _mut88366 = false, _mut88367 = false, _mut88368 = false, _mut88369 = false, _mut88370 = false, _mut88371 = false, _mut88372 = false, _mut88373 = false, _mut88374 = false, _mut88375 = false, _mut88376 = false, _mut88377 = false, _mut88378 = false, _mut88379 = false, _mut88380 = false, _mut88381 = false, _mut88382 = false, _mut88383 = false, _mut88384 = false, _mut88385 = false, _mut88386 = false, _mut88387 = false, _mut88388 = false, _mut88389 = false, _mut88390 = false, _mut88391 = false, _mut88392 = false, _mut88393 = false, _mut88394 = false, _mut88395 = false, _mut88396 = false, _mut88397 = false, _mut88398 = false, _mut88399 = false, _mut88400 = false, _mut88401 = false, _mut88402 = false, _mut88403 = false, _mut88404 = false, _mut88405 = false, _mut88406 = false, _mut88407 = false, _mut88408 = false, _mut88409 = false, _mut88410 = false, _mut88411 = false, _mut88412 = false, _mut88413 = false, _mut88414 = false, _mut88415 = false, _mut88416 = false, _mut88417 = false, _mut88418 = false, _mut88419 = false, _mut88420 = false, _mut88421 = false, _mut88422 = false, _mut88423 = false, _mut88424 = false, _mut88425 = false, _mut88426 = false, _mut88427 = false, _mut88428 = false, _mut88429 = false, _mut88430 = false, _mut88431 = false, _mut88432 = false, _mut88433 = false, _mut88434 = false, _mut88435 = false, _mut88436 = false, _mut88437 = false, _mut88438 = false, _mut88439 = false, _mut88440 = false, _mut88441 = false, _mut88442 = false, _mut88443 = false, _mut88444 = false, _mut88445 = false, _mut88446 = false, _mut88447 = false, _mut88448 = false, _mut88449 = false, _mut88450 = false, _mut88451 = false, _mut88452 = false, _mut88453 = false, _mut88454 = false, _mut88455 = false, _mut88456 = false, _mut88457 = false, _mut88458 = false, _mut88459 = false, _mut88460 = false, _mut88461 = false, _mut88462 = false, _mut88463 = false, _mut88464 = false, _mut88465 = false, _mut88466 = false, _mut88467 = false, _mut88468 = false, _mut88469 = false, _mut88470 = false, _mut88471 = false, _mut88472 = false, _mut88473 = false, _mut88474 = false, _mut88475 = false, _mut88476 = false, _mut88477 = false, _mut88478 = false, _mut88479 = false, _mut88480 = false, _mut88481 = false, _mut88482 = false, _mut88483 = false, _mut88484 = false, _mut88485 = false, _mut88486 = false, _mut88487 = false, _mut88488 = false, _mut88489 = false, _mut88490 = false, _mut88491 = false, _mut88492 = false, _mut88493 = false, _mut88494 = false, _mut88495 = false, _mut88496 = false, _mut88497 = false, _mut88498 = false, _mut88499 = false, _mut88500 = false, _mut88501 = false, _mut88502 = false, _mut88503 = false, _mut88504 = false, _mut88505 = false, _mut88506 = false, _mut88507 = false, _mut88508 = false, _mut88509 = false, _mut88510 = false, _mut88511 = false, _mut88512 = false, _mut88513 = false, _mut88514 = false, _mut88515 = false, _mut88516 = false, _mut88517 = false, _mut88518 = false, _mut88519 = false, _mut88520 = false, _mut88521 = false, _mut88522 = false, _mut88523 = false, _mut88524 = false, _mut88525 = false, _mut88526 = false, _mut88527 = false, _mut88528 = false, _mut88529 = false, _mut88530 = false, _mut88531 = false, _mut88532 = false, _mut88533 = false, _mut88534 = false, _mut88535 = false, _mut88536 = false, _mut88537 = false, _mut88538 = false, _mut88539 = false, _mut88540 = false, _mut88541 = false, _mut88542 = false, _mut88543 = false, _mut88544 = false, _mut88545 = false, _mut88546 = false, _mut88547 = false, _mut88548 = false, _mut88549 = false, _mut88550 = false, _mut88551 = false, _mut88552 = false, _mut88553 = false, _mut88554 = false, _mut88555 = false, _mut88556 = false, _mut88557 = false, _mut88558 = false, _mut88559 = false, _mut88560 = false, _mut88561 = false, _mut88562 = false, _mut88563 = false, _mut88564 = false, _mut88565 = false, _mut88566 = false, _mut88567 = false, _mut88568 = false, _mut88569 = false, _mut88570 = false, _mut88571 = false, _mut88572 = false, _mut88573 = false, _mut88574 = false, _mut88575 = false, _mut88576 = false, _mut88577 = false, _mut88578 = false, _mut88579 = false, _mut88580 = false, _mut88581 = false, _mut88582 = false, _mut88583 = false, _mut88584 = false, _mut88585 = false, _mut88586 = false, _mut88587 = false, _mut88588 = false, _mut88589 = false, _mut88590 = false, _mut88591 = false, _mut88592 = false, _mut88593 = false, _mut88594 = false, _mut88595 = false, _mut88596 = false, _mut88597 = false, _mut88598 = false, _mut88599 = false, _mut88600 = false, _mut88601 = false, _mut88602 = false, _mut88603 = false, _mut88604 = false, _mut88605 = false, _mut88606 = false, _mut88607 = false, _mut88608 = false, _mut88609 = false, _mut88610 = false, _mut88611 = false, _mut88612 = false, _mut88613 = false, _mut88614 = false, _mut88615 = false, _mut88616 = false, _mut88617 = false, _mut88618 = false, _mut88619 = false, _mut88620 = false, _mut88621 = false, _mut88622 = false, _mut88623 = false, _mut88624 = false, _mut88625 = false, _mut88626 = false, _mut88627 = false, _mut88628 = false, _mut88629 = false, _mut88630 = false, _mut88631 = false, _mut88632 = false, _mut88633 = false, _mut88634 = false, _mut88635 = false, _mut88636 = false, _mut88637 = false, _mut88638 = false, _mut88639 = false, _mut88640 = false, _mut88641 = false, _mut88642 = false, _mut88643 = false, _mut88644 = false, _mut88645 = false, _mut88646 = false, _mut88647 = false, _mut88648 = false, _mut88649 = false, _mut88650 = false, _mut88651 = false, _mut88652 = false, _mut88653 = false, _mut88654 = false, _mut88655 = false, _mut88656 = false, _mut88657 = false, _mut88658 = false, _mut88659 = false, _mut88660 = false, _mut88661 = false, _mut88662 = false, _mut88663 = false, _mut88664 = false, _mut88665 = false, _mut88666 = false, _mut88667 = false, _mut88668 = false, _mut88669 = false, _mut88670 = false, _mut88671 = false, _mut88672 = false, _mut88673 = false, _mut88674 = false, _mut88675 = false, _mut88676 = false, _mut88677 = false, _mut88678 = false, _mut88679 = false, _mut88680 = false, _mut88681 = false, _mut88682 = false, _mut88683 = false, _mut88684 = false, _mut88685 = false, _mut88686 = false, _mut88687 = false, _mut88688 = false, _mut88689 = false, _mut88690 = false, _mut88691 = false, _mut88692 = false, _mut88693 = false, _mut88694 = false, _mut88695 = false, _mut88696 = false, _mut88697 = false, _mut88698 = false, _mut88699 = false, _mut88700 = false, _mut88701 = false, _mut88702 = false, _mut88703 = false, _mut88704 = false, _mut88705 = false, _mut88706 = false, _mut88707 = false, _mut88708 = false, _mut88709 = false, _mut88710 = false, _mut88711 = false, _mut88712 = false, _mut88713 = false, _mut88714 = false, _mut88715 = false, _mut88716 = false, _mut88717 = false, _mut88718 = false, _mut88719 = false, _mut88720 = false, _mut88721 = false, _mut88722 = false, _mut88723 = false, _mut88724 = false, _mut88725 = false, _mut88726 = false, _mut88727 = false, _mut88728 = false, _mut88729 = false, _mut88730 = false, _mut88731 = false, _mut88732 = false, _mut88733 = false, _mut88734 = false, _mut88735 = false, _mut88736 = false, _mut88737 = false, _mut88738 = false, _mut88739 = false, _mut88740 = false, _mut88741 = false, _mut88742 = false, _mut88743 = false, _mut88744 = false, _mut88745 = false, _mut88746 = false, _mut88747 = false, _mut88748 = false, _mut88749 = false, _mut88750 = false, _mut88751 = false, _mut88752 = false, _mut88753 = false, _mut88754 = false, _mut88755 = false, _mut88756 = false, _mut88757 = false, _mut88758 = false, _mut88759 = false, _mut88760 = false, _mut88761 = false, _mut88762 = false, _mut88763 = false, _mut88764 = false, _mut88765 = false, _mut88766 = false, _mut88767 = false, _mut88768 = false, _mut88769 = false, _mut88770 = false, _mut88771 = false, _mut88772 = false, _mut88773 = false, _mut88774 = false, _mut88775 = false, _mut88776 = false, _mut88777 = false, _mut88778 = false, _mut88779 = false, _mut88780 = false, _mut88781 = false, _mut88782 = false, _mut88783 = false, _mut88784 = false, _mut88785 = false, _mut88786 = false, _mut88787 = false, _mut88788 = false, _mut88789 = false, _mut88790 = false, _mut88791 = false, _mut88792 = false, _mut88793 = false, _mut88794 = false, _mut88795 = false, _mut88796 = false, _mut88797 = false, _mut88798 = false, _mut88799 = false, _mut88800 = false, _mut88801 = false, _mut88802 = false, _mut88803 = false, _mut88804 = false, _mut88805 = false, _mut88806 = false, _mut88807 = false, _mut88808 = false, _mut88809 = false, _mut88810 = false, _mut88811 = false, _mut88812 = false, _mut88813 = false, _mut88814 = false, _mut88815 = false, _mut88816 = false, _mut88817 = false, _mut88818 = false, _mut88819 = false, _mut88820 = false, _mut88821 = false, _mut88822 = false, _mut88823 = false, _mut88824 = false, _mut88825 = false, _mut88826 = false, _mut88827 = false, _mut88828 = false, _mut88829 = false, _mut88830 = false, _mut88831 = false, _mut88832 = false, _mut88833 = false, _mut88834 = false, _mut88835 = false, _mut88836 = false, _mut88837 = false, _mut88838 = false, _mut88839 = false, _mut88840 = false, _mut88841 = false, _mut88842 = false, _mut88843 = false, _mut88844 = false, _mut88845 = false, _mut88846 = false, _mut88847 = false, _mut88848 = false, _mut88849 = false, _mut88850 = false, _mut88851 = false, _mut88852 = false, _mut88853 = false, _mut88854 = false, _mut88855 = false, _mut88856 = false, _mut88857 = false, _mut88858 = false, _mut88859 = false, _mut88860 = false, _mut88861 = false, _mut88862 = false, _mut88863 = false, _mut88864 = false, _mut88865 = false, _mut88866 = false, _mut88867 = false, _mut88868 = false, _mut88869 = false, _mut88870 = false, _mut88871 = false, _mut88872 = false, _mut88873 = false, _mut88874 = false, _mut88875 = false, _mut88876 = false, _mut88877 = false, _mut88878 = false, _mut88879 = false, _mut88880 = false, _mut88881 = false, _mut88882 = false, _mut88883 = false, _mut88884 = false, _mut88885 = false, _mut88886 = false, _mut88887 = false, _mut88888 = false, _mut88889 = false, _mut88890 = false, _mut88891 = false, _mut88892 = false, _mut88893 = false, _mut88894 = false, _mut88895 = false, _mut88896 = false, _mut88897 = false, _mut88898 = false, _mut88899 = false, _mut88900 = false, _mut88901 = false, _mut88902 = false, _mut88903 = false, _mut88904 = false, _mut88905 = false, _mut88906 = false, _mut88907 = false, _mut88908 = false, _mut88909 = false, _mut88910 = false, _mut88911 = false, _mut88912 = false, _mut88913 = false, _mut88914 = false, _mut88915 = false, _mut88916 = false, _mut88917 = false, _mut88918 = false, _mut88919 = false, _mut88920 = false, _mut88921 = false, _mut88922 = false, _mut88923 = false, _mut88924 = false, _mut88925 = false, _mut88926 = false, _mut88927 = false, _mut88928 = false, _mut88929 = false, _mut88930 = false, _mut88931 = false, _mut88932 = false, _mut88933 = false, _mut88934 = false, _mut88935 = false, _mut88936 = false, _mut88937 = false, _mut88938 = false, _mut88939 = false, _mut88940 = false, _mut88941 = false, _mut88942 = false, _mut88943 = false, _mut88944 = false, _mut88945 = false, _mut88946 = false, _mut88947 = false, _mut88948 = false, _mut88949 = false, _mut88950 = false, _mut88951 = false, _mut88952 = false, _mut88953 = false, _mut88954 = false, _mut88955 = false, _mut88956 = false, _mut88957 = false, _mut88958 = false, _mut88959 = false, _mut88960 = false, _mut88961 = false, _mut88962 = false, _mut88963 = false, _mut88964 = false, _mut88965 = false, _mut88966 = false, _mut88967 = false, _mut88968 = false, _mut88969 = false, _mut88970 = false, _mut88971 = false, _mut88972 = false, _mut88973 = false, _mut88974 = false, _mut88975 = false, _mut88976 = false, _mut88977 = false, _mut88978 = false, _mut88979 = false, _mut88980 = false, _mut88981 = false, _mut88982 = false, _mut88983 = false, _mut88984 = false, _mut88985 = false, _mut88986 = false, _mut88987 = false, _mut88988 = false, _mut88989 = false, _mut88990 = false, _mut88991 = false, _mut88992 = false, _mut88993 = false, _mut88994 = false, _mut88995 = false, _mut88996 = false, _mut88997 = false, _mut88998 = false, _mut88999 = false, _mut89000 = false, _mut89001 = false, _mut89002 = false, _mut89003 = false, _mut89004 = false, _mut89005 = false, _mut89006 = false, _mut89007 = false, _mut89008 = false, _mut89009 = false, _mut89010 = false, _mut89011 = false, _mut89012 = false, _mut89013 = false, _mut89014 = false, _mut89015 = false, _mut89016 = false, _mut89017 = false, _mut89018 = false, _mut89019 = false, _mut89020 = false, _mut89021 = false, _mut89022 = false, _mut89023 = false, _mut89024 = false, _mut89025 = false, _mut89026 = false, _mut89027 = false, _mut89028 = false, _mut89029 = false, _mut89030 = false, _mut89031 = false, _mut89032 = false, _mut89033 = false, _mut89034 = false, _mut89035 = false, _mut89036 = false, _mut89037 = false, _mut89038 = false, _mut89039 = false, _mut89040 = false, _mut89041 = false, _mut89042 = false, _mut89043 = false, _mut89044 = false, _mut89045 = false, _mut89046 = false, _mut89047 = false, _mut89048 = false, _mut89049 = false, _mut89050 = false, _mut89051 = false, _mut89052 = false, _mut89053 = false, _mut89054 = false, _mut89055 = false, _mut89056 = false, _mut89057 = false, _mut89058 = false, _mut89059 = false, _mut89060 = false, _mut89061 = false, _mut89062 = false, _mut89063 = false, _mut89064 = false, _mut89065 = false, _mut89066 = false, _mut89067 = false, _mut89068 = false, _mut89069 = false, _mut89070 = false, _mut89071 = false, _mut89072 = false, _mut89073 = false, _mut89074 = false, _mut89075 = false, _mut89076 = false, _mut89077 = false, _mut89078 = false, _mut89079 = false, _mut89080 = false, _mut89081 = false, _mut89082 = false, _mut89083 = false, _mut89084 = false, _mut89085 = false, _mut89086 = false, _mut89087 = false, _mut89088 = false, _mut89089 = false, _mut89090 = false, _mut89091 = false, _mut89092 = false, _mut89093 = false, _mut89094 = false, _mut89095 = false, _mut89096 = false, _mut89097 = false, _mut89098 = false, _mut89099 = false, _mut89100 = false, _mut89101 = false, _mut89102 = false, _mut89103 = false, _mut89104 = false, _mut89105 = false, _mut89106 = false, _mut89107 = false, _mut89108 = false, _mut89109 = false, _mut89110 = false, _mut89111 = false, _mut89112 = false, _mut89113 = false, _mut89114 = false, _mut89115 = false, _mut89116 = false, _mut89117 = false, _mut89118 = false, _mut89119 = false, _mut89120 = false, _mut89121 = false, _mut89122 = false, _mut89123 = false, _mut89124 = false, _mut89125 = false, _mut89126 = false, _mut89127 = false, _mut89128 = false, _mut89129 = false, _mut89130 = false, _mut89131 = false, _mut89132 = false, _mut89133 = false, _mut89134 = false, _mut89135 = false, _mut89136 = false, _mut89137 = false, _mut89138 = false, _mut89139 = false, _mut89140 = false, _mut89141 = false, _mut89142 = false, _mut89143 = false, _mut89144 = false, _mut89145 = false, _mut89146 = false, _mut89147 = false, _mut89148 = false, _mut89149 = false, _mut89150 = false, _mut89151 = false, _mut89152 = false, _mut89153 = false, _mut89154 = false, _mut89155 = false, _mut89156 = false, _mut89157 = false, _mut89158 = false, _mut89159 = false, _mut89160 = false, _mut89161 = false, _mut89162 = false, _mut89163 = false, _mut89164 = false, _mut89165 = false, _mut89166 = false, _mut89167 = false, _mut89168 = false, _mut89169 = false, _mut89170 = false, _mut89171 = false, _mut89172 = false, _mut89173 = false, _mut89174 = false, _mut89175 = false, _mut89176 = false, _mut89177 = false, _mut89178 = false, _mut89179 = false, _mut89180 = false, _mut89181 = false, _mut89182 = false, _mut89183 = false, _mut89184 = false, _mut89185 = false, _mut89186 = false, _mut89187 = false, _mut89188 = false, _mut89189 = false, _mut89190 = false, _mut89191 = false, _mut89192 = false, _mut89193 = false, _mut89194 = false, _mut89195 = false, _mut89196 = false, _mut89197 = false, _mut89198 = false, _mut89199 = false, _mut89200 = false, _mut89201 = false, _mut89202 = false, _mut89203 = false, _mut89204 = false, _mut89205 = false, _mut89206 = false, _mut89207 = false, _mut89208 = false, _mut89209 = false, _mut89210 = false, _mut89211 = false, _mut89212 = false, _mut89213 = false, _mut89214 = false, _mut89215 = false, _mut89216 = false, _mut89217 = false, _mut89218 = false, _mut89219 = false, _mut89220 = false, _mut89221 = false, _mut89222 = false, _mut89223 = false, _mut89224 = false, _mut89225 = false, _mut89226 = false, _mut89227 = false, _mut89228 = false, _mut89229 = false, _mut89230 = false, _mut89231 = false, _mut89232 = false, _mut89233 = false, _mut89234 = false, _mut89235 = false, _mut89236 = false, _mut89237 = false, _mut89238 = false, _mut89239 = false, _mut89240 = false, _mut89241 = false, _mut89242 = false, _mut89243 = false, _mut89244 = false, _mut89245 = false, _mut89246 = false, _mut89247 = false, _mut89248 = false, _mut89249 = false, _mut89250 = false, _mut89251 = false, _mut89252 = false, _mut89253 = false, _mut89254 = false, _mut89255 = false, _mut89256 = false, _mut89257 = false, _mut89258 = false, _mut89259 = false, _mut89260 = false, _mut89261 = false, _mut89262 = false, _mut89263 = false, _mut89264 = false, _mut89265 = false, _mut89266 = false, _mut89267 = false, _mut89268 = false, _mut89269 = false, _mut89270 = false, _mut89271 = false, _mut89272 = false, _mut89273 = false, _mut89274 = false, _mut89275 = false, _mut89276 = false, _mut89277 = false, _mut89278 = false, _mut89279 = false, _mut89280 = false, _mut89281 = false, _mut89282 = false, _mut89283 = false, _mut89284 = false, _mut89285 = false, _mut89286 = false, _mut89287 = false, _mut89288 = false, _mut89289 = false, _mut89290 = false, _mut89291 = false, _mut89292 = false, _mut89293 = false, _mut89294 = false, _mut89295 = false, _mut89296 = false, _mut89297 = false, _mut89298 = false, _mut89299 = false, _mut89300 = false, _mut89301 = false, _mut89302 = false, _mut89303 = false, _mut89304 = false, _mut89305 = false, _mut89306 = false, _mut89307 = false, _mut89308 = false, _mut89309 = false, _mut89310 = false, _mut89311 = false, _mut89312 = false, _mut89313 = false, _mut89314 = false, _mut89315 = false, _mut89316 = false, _mut89317 = false, _mut89318 = false, _mut89319 = false, _mut89320 = false, _mut89321 = false, _mut89322 = false, _mut89323 = false, _mut89324 = false, _mut89325 = false, _mut89326 = false, _mut89327 = false, _mut89328 = false, _mut89329 = false, _mut89330 = false, _mut89331 = false, _mut89332 = false, _mut89333 = false, _mut89334 = false, _mut89335 = false, _mut89336 = false, _mut89337 = false, _mut89338 = false, _mut89339 = false, _mut89340 = false, _mut89341 = false, _mut89342 = false, _mut89343 = false, _mut89344 = false, _mut89345 = false, _mut89346 = false, _mut89347 = false, _mut89348 = false, _mut89349 = false, _mut89350 = false, _mut89351 = false, _mut89352 = false, _mut89353 = false, _mut89354 = false, _mut89355 = false, _mut89356 = false, _mut89357 = false, _mut89358 = false, _mut89359 = false, _mut89360 = false, _mut89361 = false, _mut89362 = false, _mut89363 = false, _mut89364 = false, _mut89365 = false, _mut89366 = false, _mut89367 = false, _mut89368 = false, _mut89369 = false, _mut89370 = false, _mut89371 = false, _mut89372 = false, _mut89373 = false, _mut89374 = false, _mut89375 = false, _mut89376 = false, _mut89377 = false, _mut89378 = false, _mut89379 = false, _mut89380 = false, _mut89381 = false, _mut89382 = false, _mut89383 = false, _mut89384 = false, _mut89385 = false, _mut89386 = false, _mut89387 = false, _mut89388 = false, _mut89389 = false, _mut89390 = false, _mut89391 = false, _mut89392 = false, _mut89393 = false, _mut89394 = false, _mut89395 = false, _mut89396 = false, _mut89397 = false, _mut89398 = false, _mut89399 = false, _mut89400 = false, _mut89401 = false, _mut89402 = false, _mut89403 = false, _mut89404 = false, _mut89405 = false, _mut89406 = false, _mut89407 = false, _mut89408 = false, _mut89409 = false, _mut89410 = false, _mut89411 = false, _mut89412 = false, _mut89413 = false, _mut89414 = false, _mut89415 = false, _mut89416 = false, _mut89417 = false, _mut89418 = false, _mut89419 = false, _mut89420 = false, _mut89421 = false, _mut89422 = false, _mut89423 = false, _mut89424 = false, _mut89425 = false, _mut89426 = false, _mut89427 = false, _mut89428 = false, _mut89429 = false, _mut89430 = false, _mut89431 = false, _mut89432 = false, _mut89433 = false, _mut89434 = false, _mut89435 = false, _mut89436 = false, _mut89437 = false, _mut89438 = false, _mut89439 = false, _mut89440 = false, _mut89441 = false, _mut89442 = false, _mut89443 = false, _mut89444 = false, _mut89445 = false, _mut89446 = false, _mut89447 = false, _mut89448 = false, _mut89449 = false, _mut89450 = false, _mut89451 = false, _mut89452 = false, _mut89453 = false, _mut89454 = false, _mut89455 = false, _mut89456 = false, _mut89457 = false, _mut89458 = false, _mut89459 = false, _mut89460 = false, _mut89461 = false, _mut89462 = false, _mut89463 = false, _mut89464 = false, _mut89465 = false, _mut89466 = false, _mut89467 = false, _mut89468 = false, _mut89469 = false, _mut89470 = false, _mut89471 = false, _mut89472 = false, _mut89473 = false, _mut89474 = false, _mut89475 = false, _mut89476 = false, _mut89477 = false, _mut89478 = false, _mut89479 = false, _mut89480 = false, _mut89481 = false, _mut89482 = false, _mut89483 = false, _mut89484 = false, _mut89485 = false, _mut89486 = false, _mut89487 = false, _mut89488 = false, _mut89489 = false, _mut89490 = false, _mut89491 = false, _mut89492 = false, _mut89493 = false, _mut89494 = false, _mut89495 = false, _mut89496 = false, _mut89497 = false, _mut89498 = false, _mut89499 = false, _mut89500 = false, _mut89501 = false, _mut89502 = false, _mut89503 = false, _mut89504 = false, _mut89505 = false, _mut89506 = false, _mut89507 = false, _mut89508 = false, _mut89509 = false, _mut89510 = false, _mut89511 = false, _mut89512 = false, _mut89513 = false, _mut89514 = false, _mut89515 = false, _mut89516 = false, _mut89517 = false, _mut89518 = false, _mut89519 = false, _mut89520 = false, _mut89521 = false, _mut89522 = false, _mut89523 = false, _mut89524 = false, _mut89525 = false, _mut89526 = false, _mut89527 = false, _mut89528 = false, _mut89529 = false, _mut89530 = false, _mut89531 = false, _mut89532 = false, _mut89533 = false, _mut89534 = false, _mut89535 = false, _mut89536 = false, _mut89537 = false, _mut89538 = false, _mut89539 = false, _mut89540 = false, _mut89541 = false, _mut89542 = false, _mut89543 = false, _mut89544 = false, _mut89545 = false, _mut89546 = false, _mut89547 = false, _mut89548 = false, _mut89549 = false, _mut89550 = false, _mut89551 = false, _mut89552 = false, _mut89553 = false, _mut89554 = false, _mut89555 = false, _mut89556 = false, _mut89557 = false, _mut89558 = false, _mut89559 = false, _mut89560 = false, _mut89561 = false, _mut89562 = false, _mut89563 = false, _mut89564 = false, _mut89565 = false, _mut89566 = false, _mut89567 = false, _mut89568 = false, _mut89569 = false, _mut89570 = false, _mut89571 = false, _mut89572 = false, _mut89573 = false, _mut89574 = false, _mut89575 = false, _mut89576 = false, _mut89577 = false, _mut89578 = false, _mut89579 = false, _mut89580 = false, _mut89581 = false, _mut89582 = false, _mut89583 = false, _mut89584 = false, _mut89585 = false, _mut89586 = false, _mut89587 = false, _mut89588 = false, _mut89589 = false, _mut89590 = false, _mut89591 = false, _mut89592 = false, _mut89593 = false, _mut89594 = false, _mut89595 = false, _mut89596 = false, _mut89597 = false, _mut89598 = false, _mut89599 = false, _mut89600 = false, _mut89601 = false, _mut89602 = false, _mut89603 = false, _mut89604 = false, _mut89605 = false, _mut89606 = false, _mut89607 = false, _mut89608 = false, _mut89609 = false, _mut89610 = false, _mut89611 = false, _mut89612 = false, _mut89613 = false, _mut89614 = false, _mut89615 = false, _mut89616 = false, _mut89617 = false, _mut89618 = false, _mut89619 = false, _mut89620 = false, _mut89621 = false, _mut89622 = false, _mut89623 = false, _mut89624 = false, _mut89625 = false, _mut89626 = false, _mut89627 = false, _mut89628 = false, _mut89629 = false, _mut89630 = false, _mut89631 = false, _mut89632 = false, _mut89633 = false, _mut89634 = false, _mut89635 = false, _mut89636 = false, _mut89637 = false, _mut89638 = false, _mut89639 = false, _mut89640 = false, _mut89641 = false, _mut89642 = false, _mut89643 = false, _mut89644 = false, _mut89645 = false, _mut89646 = false, _mut89647 = false, _mut89648 = false, _mut89649 = false, _mut89650 = false, _mut89651 = false, _mut89652 = false, _mut89653 = false, _mut89654 = false, _mut89655 = false, _mut89656 = false, _mut89657 = false, _mut89658 = false, _mut89659 = false, _mut89660 = false, _mut89661 = false, _mut89662 = false, _mut89663 = false, _mut89664 = false, _mut89665 = false, _mut89666 = false, _mut89667 = false, _mut89668 = false, _mut89669 = false, _mut89670 = false, _mut89671 = false, _mut89672 = false, _mut89673 = false, _mut89674 = false, _mut89675 = false, _mut89676 = false, _mut89677 = false, _mut89678 = false, _mut89679 = false, _mut89680 = false, _mut89681 = false, _mut89682 = false, _mut89683 = false, _mut89684 = false, _mut89685 = false, _mut89686 = false, _mut89687 = false;

    /**
     * The radix, or base of this system.  Set to 10000
     */
    public static final int RADIX = 10000;

    /**
     * The minimum exponent before underflow is signaled.  Flush to zero
     *  occurs at minExp-DIGITS
     */
    public static final int MIN_EXP = -32767;

    /**
     * The maximum exponent before overflow is signaled and results flushed
     *  to infinity
     */
    public static final int MAX_EXP = 32768;

    /**
     * The amount under/overflows are scaled by before going to trap handler
     */
    public static final int ERR_SCALE = 32760;

    /**
     * Indicator value for normal finite numbers.
     */
    public static final byte FINITE = 0;

    /**
     * Indicator value for Infinity.
     */
    public static final byte INFINITE = 1;

    /**
     * Indicator value for signaling NaN.
     */
    public static final byte SNAN = 2;

    /**
     * Indicator value for quiet NaN.
     */
    public static final byte QNAN = 3;

    /**
     * String for NaN representation.
     */
    private static final String NAN_STRING = "NaN";

    /**
     * String for positive infinity representation.
     */
    private static final String POS_INFINITY_STRING = "Infinity";

    /**
     * String for negative infinity representation.
     */
    private static final String NEG_INFINITY_STRING = "-Infinity";

    /**
     * Name for traps triggered by addition.
     */
    private static final String ADD_TRAP = "add";

    /**
     * Name for traps triggered by multiplication.
     */
    private static final String MULTIPLY_TRAP = "multiply";

    /**
     * Name for traps triggered by division.
     */
    private static final String DIVIDE_TRAP = "divide";

    /**
     * Name for traps triggered by square root.
     */
    private static final String SQRT_TRAP = "sqrt";

    /**
     * Name for traps triggered by alignment.
     */
    private static final String ALIGN_TRAP = "align";

    /**
     * Name for traps triggered by truncation.
     */
    private static final String TRUNC_TRAP = "trunc";

    /**
     * Name for traps triggered by nextAfter.
     */
    private static final String NEXT_AFTER_TRAP = "nextAfter";

    /**
     * Name for traps triggered by lessThan.
     */
    private static final String LESS_THAN_TRAP = "lessThan";

    /**
     * Name for traps triggered by greaterThan.
     */
    private static final String GREATER_THAN_TRAP = "greaterThan";

    /**
     * Name for traps triggered by newInstance.
     */
    private static final String NEW_INSTANCE_TRAP = "newInstance";

    /**
     * Mantissa.
     */
    protected int[] mant;

    /**
     * Sign bit: 1 for positive, -1 for negative.
     */
    protected byte sign;

    /**
     * Exponent.
     */
    protected int exp;

    /**
     * Indicator for non-finite / non-number values.
     */
    protected byte nans;

    /**
     * Factory building similar Dfp's.
     */
    private final DfpField field;

    /**
     * Makes an instance with a value of zero.
     * @param field field to which this instance belongs
     */
    protected Dfp(final DfpField field) {
        mant = new int[field.getRadixDigits()];
        sign = 1;
        exp = 0;
        nans = FINITE;
        this.field = field;
    }

    /**
     * Create an instance from a byte value.
     * @param field field to which this instance belongs
     * @param x value to convert to an instance
     */
    protected Dfp(final DfpField field, byte x) {
        this(field, (long) x);
    }

    /**
     * Create an instance from an int value.
     * @param field field to which this instance belongs
     * @param x value to convert to an instance
     */
    protected Dfp(final DfpField field, int x) {
        this(field, (long) x);
    }

    /**
     * Create an instance from a long value.
     * @param field field to which this instance belongs
     * @param x value to convert to an instance
     */
    protected Dfp(final DfpField field, long x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_210");
        // initialize as if 0
        mant = new int[field.getRadixDigits()];
        nans = FINITE;
        this.field = field;
        boolean isLongMin = false;
        if (ROR_equals(x, Long.MIN_VALUE, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87025, _mut87026, _mut87027, _mut87028, _mut87029)) {
            // we must shift it before taking its absolute value
            isLongMin = true;
            ++x;
        }
        // set the sign
        if (ROR_less(x, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87030, _mut87031, _mut87032, _mut87033, _mut87034)) {
            sign = -1;
            x = -x;
        } else {
            sign = 1;
        }
        exp = 0;
        while (ROR_not_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87055, _mut87056, _mut87057, _mut87058, _mut87059)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_210");
            System.arraycopy(mant, AOR_minus(mant.length, exp, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87035, _mut87036, _mut87037, _mut87038), mant, AOR_minus(AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87039, _mut87040, _mut87041, _mut87042), exp, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87043, _mut87044, _mut87045, _mut87046), exp);
            mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87047, _mut87048, _mut87049, _mut87050)] = (int) (AOR_remainder(x, RADIX, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87051, _mut87052, _mut87053, _mut87054));
            x /= RADIX;
            exp++;
        }
        if (isLongMin) {
            // we know in this case that fixing the last digit is sufficient
            for (int i = 0; ROR_less(i, AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87065, _mut87066, _mut87067, _mut87068), "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87069, _mut87070, _mut87071, _mut87072, _mut87073); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_210");
                if (ROR_not_equals(mant[i], 0, "org.apache.commons.math3.dfp.Dfp.Dfp_210", _mut87060, _mut87061, _mut87062, _mut87063, _mut87064)) {
                    mant[i]++;
                    break;
                }
            }
        }
    }

    /**
     * Create an instance from a double value.
     * @param field field to which this instance belongs
     * @param x value to convert to an instance
     */
    protected Dfp(final DfpField field, double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_257");
        // initialize as if 0
        mant = new int[field.getRadixDigits()];
        sign = 1;
        exp = 0;
        nans = FINITE;
        this.field = field;
        long bits = Double.doubleToLongBits(x);
        long mantissa = bits & 0x000fffffffffffffL;
        int exponent = AOR_minus((int) ((bits & 0x7ff0000000000000L) >> 52), 1023, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87074, _mut87075, _mut87076, _mut87077);
        if (ROR_equals(exponent, -1023, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87078, _mut87079, _mut87080, _mut87081, _mut87082)) {
            // Zero or sub-normal
            if (ROR_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87083, _mut87084, _mut87085, _mut87086, _mut87087)) {
                // make sure 0 has the right sign
                if (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87088, _mut87089, _mut87090, _mut87091, _mut87092)) {
                    sign = -1;
                }
                return;
            }
            exponent++;
            // Normalize the subnormal number
            while (ROR_equals((mantissa & 0x0010000000000000L), 0, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87093, _mut87094, _mut87095, _mut87096, _mut87097)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_257");
                exponent--;
                mantissa <<= 1;
            }
            mantissa &= 0x000fffffffffffffL;
        }
        if (ROR_equals(exponent, 1024, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87098, _mut87099, _mut87100, _mut87101, _mut87102)) {
            // infinity or NAN
            if (ROR_not_equals(x, x, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87103, _mut87104, _mut87105, _mut87106, _mut87107)) {
                sign = (byte) 1;
                nans = QNAN;
            } else if (ROR_less(x, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87108, _mut87109, _mut87110, _mut87111, _mut87112)) {
                sign = (byte) -1;
                nans = INFINITE;
            } else {
                sign = (byte) 1;
                nans = INFINITE;
            }
            return;
        }
        Dfp xdfp = new Dfp(field, mantissa);
        // Divide by 2^52, then add one
        xdfp = xdfp.divide(new Dfp(field, 4503599627370496l)).add(field.getOne());
        xdfp = xdfp.multiply(DfpMath.pow(field.getTwo(), exponent));
        if (ROR_not_equals((bits & 0x8000000000000000L), 0, "org.apache.commons.math3.dfp.Dfp.Dfp_257", _mut87113, _mut87114, _mut87115, _mut87116, _mut87117)) {
            xdfp = xdfp.negate();
        }
        System.arraycopy(xdfp.mant, 0, mant, 0, mant.length);
        sign = xdfp.sign;
        exp = xdfp.exp;
        nans = xdfp.nans;
    }

    /**
     * Copy constructor.
     * @param d instance to copy
     */
    public Dfp(final Dfp d) {
        mant = d.mant.clone();
        sign = d.sign;
        exp = d.exp;
        nans = d.nans;
        field = d.field;
    }

    /**
     * Create an instance from a String representation.
     * @param field field to which this instance belongs
     * @param s string representation of the instance
     */
    protected Dfp(final DfpField field, final String s) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
        // initialize as if 0
        mant = new int[field.getRadixDigits()];
        sign = 1;
        exp = 0;
        nans = FINITE;
        this.field = field;
        boolean decimalFound = false;
        // size of radix in decimal digits
        final int rsize = 4;
        // Starting offset into Striped
        final int offset = 4;
        final char[] striped = new char[AOR_plus(AOR_multiply(getRadixDigits(), rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87118, _mut87119, _mut87120, _mut87121), AOR_multiply(offset, 2, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87122, _mut87123, _mut87124, _mut87125), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87126, _mut87127, _mut87128, _mut87129)];
        // Check some special cases
        if (s.equals(POS_INFINITY_STRING)) {
            sign = (byte) 1;
            nans = INFINITE;
            return;
        }
        if (s.equals(NEG_INFINITY_STRING)) {
            sign = (byte) -1;
            nans = INFINITE;
            return;
        }
        if (s.equals(NAN_STRING)) {
            sign = (byte) 1;
            nans = QNAN;
            return;
        }
        // Check for scientific notation
        int p = s.indexOf("e");
        if (ROR_equals(p, -1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87130, _mut87131, _mut87132, _mut87133, _mut87134)) {
            // try upper case?
            p = s.indexOf("E");
        }
        final String fpdecimal;
        int sciexp = 0;
        if (ROR_not_equals(p, -1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87135, _mut87136, _mut87137, _mut87138, _mut87139)) {
            // scientific notation
            fpdecimal = s.substring(0, p);
            String fpexp = s.substring(AOR_plus(p, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87140, _mut87141, _mut87142, _mut87143));
            boolean negative = false;
            for (int i = 0; ROR_less(i, fpexp.length(), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87167, _mut87168, _mut87169, _mut87170, _mut87171); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
                if (fpexp.charAt(i) == '-') {
                    negative = true;
                    continue;
                }
                if ((_mut87154 ? (ROR_greater_equals(fpexp.charAt(i), '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87144, _mut87145, _mut87146, _mut87147, _mut87148) || ROR_less_equals(fpexp.charAt(i), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87149, _mut87150, _mut87151, _mut87152, _mut87153)) : (ROR_greater_equals(fpexp.charAt(i), '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87144, _mut87145, _mut87146, _mut87147, _mut87148) && ROR_less_equals(fpexp.charAt(i), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87149, _mut87150, _mut87151, _mut87152, _mut87153)))) {
                    sciexp = AOR_minus(AOR_plus(AOR_multiply(sciexp, 10, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87155, _mut87156, _mut87157, _mut87158), fpexp.charAt(i), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87159, _mut87160, _mut87161, _mut87162), '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87163, _mut87164, _mut87165, _mut87166);
                }
            }
            if (negative) {
                sciexp = -sciexp;
            }
        } else {
            // normal case
            fpdecimal = s;
        }
        // If there is a minus sign in the number then it is negative
        if (ROR_not_equals(fpdecimal.indexOf("-"), -1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87172, _mut87173, _mut87174, _mut87175, _mut87176)) {
            sign = -1;
        }
        // First off, find all of the leading zeros, trailing zeros, and significant digits
        p = 0;
        // Move p to first significant digit
        int decimalPos = 0;
        for (; ; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
            if ((_mut87187 ? (ROR_greater_equals(fpdecimal.charAt(p), '1', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87177, _mut87178, _mut87179, _mut87180, _mut87181) || ROR_less_equals(fpdecimal.charAt(p), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87182, _mut87183, _mut87184, _mut87185, _mut87186)) : (ROR_greater_equals(fpdecimal.charAt(p), '1', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87177, _mut87178, _mut87179, _mut87180, _mut87181) && ROR_less_equals(fpdecimal.charAt(p), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87182, _mut87183, _mut87184, _mut87185, _mut87186)))) {
                break;
            }
            if ((_mut87188 ? (decimalFound || fpdecimal.charAt(p) == '0') : (decimalFound && fpdecimal.charAt(p) == '0'))) {
                decimalPos--;
            }
            if (fpdecimal.charAt(p) == '.') {
                decimalFound = true;
            }
            p++;
            if (ROR_equals(p, fpdecimal.length(), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87189, _mut87190, _mut87191, _mut87192, _mut87193)) {
                break;
            }
        }
        // Copy the string onto Stripped
        int q = offset;
        striped[0] = '0';
        striped[1] = '0';
        striped[2] = '0';
        striped[3] = '0';
        int significantDigits = 0;
        for (; ; ) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
            if (ROR_equals(p, (fpdecimal.length()), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87194, _mut87195, _mut87196, _mut87197, _mut87198)) {
                break;
            }
            // Don't want to run pass the end of the array
            if (ROR_equals(q, AOR_plus(AOR_plus(AOR_multiply(mant.length, rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87199, _mut87200, _mut87201, _mut87202), offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87203, _mut87204, _mut87205, _mut87206), 1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87207, _mut87208, _mut87209, _mut87210), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87211, _mut87212, _mut87213, _mut87214, _mut87215)) {
                break;
            }
            if (fpdecimal.charAt(p) == '.') {
                decimalFound = true;
                decimalPos = significantDigits;
                p++;
                continue;
            }
            if ((_mut87226 ? (ROR_less(fpdecimal.charAt(p), '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87216, _mut87217, _mut87218, _mut87219, _mut87220) && ROR_greater(fpdecimal.charAt(p), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87221, _mut87222, _mut87223, _mut87224, _mut87225)) : (ROR_less(fpdecimal.charAt(p), '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87216, _mut87217, _mut87218, _mut87219, _mut87220) || ROR_greater(fpdecimal.charAt(p), '9', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87221, _mut87222, _mut87223, _mut87224, _mut87225)))) {
                p++;
                continue;
            }
            striped[q] = fpdecimal.charAt(p);
            q++;
            p++;
            significantDigits++;
        }
        // If the decimal point has been found then get rid of trailing zeros.
        if ((_mut87232 ? (decimalFound || ROR_not_equals(q, offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87227, _mut87228, _mut87229, _mut87230, _mut87231)) : (decimalFound && ROR_not_equals(q, offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87227, _mut87228, _mut87229, _mut87230, _mut87231)))) {
            for (; ; ) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
                q--;
                if (ROR_equals(q, offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87233, _mut87234, _mut87235, _mut87236, _mut87237)) {
                    break;
                }
                if (striped[q] == '0') {
                    significantDigits--;
                } else {
                    break;
                }
            }
        }
        // special case of numbers like "0.00000"
        if ((_mut87243 ? (decimalFound || ROR_equals(significantDigits, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87238, _mut87239, _mut87240, _mut87241, _mut87242)) : (decimalFound && ROR_equals(significantDigits, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87238, _mut87239, _mut87240, _mut87241, _mut87242)))) {
            decimalPos = 0;
        }
        // Implicit decimal point at end of number if not present
        if (!decimalFound) {
            decimalPos = AOR_minus(q, offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87244, _mut87245, _mut87246, _mut87247);
        }
        // set q to point to first sig digit
        q = offset;
        p = AOR_plus(AOR_minus(significantDigits, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87248, _mut87249, _mut87250, _mut87251), offset, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87252, _mut87253, _mut87254, _mut87255);
        while (ROR_greater(p, q, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87256, _mut87257, _mut87258, _mut87259, _mut87260)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
            if (striped[p] != '0') {
                break;
            }
            p--;
        }
        // Make sure the decimal is on a mod 10000 boundary
        int i = AOR_remainder((AOR_minus(AOR_minus((AOR_multiply(rsize, 100, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87261, _mut87262, _mut87263, _mut87264)), decimalPos, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87265, _mut87266, _mut87267, _mut87268), AOR_remainder(sciexp, rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87269, _mut87270, _mut87271, _mut87272), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87273, _mut87274, _mut87275, _mut87276)), rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87277, _mut87278, _mut87279, _mut87280);
        q -= i;
        decimalPos += i;
        // Make the mantissa length right by adding zeros at the end if necessary
        while (ROR_less((AOR_minus(p, q, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87286, _mut87287, _mut87288, _mut87289)), (AOR_multiply(mant.length, rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87290, _mut87291, _mut87292, _mut87293)), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87294, _mut87295, _mut87296, _mut87297, _mut87298)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
            for (i = 0; ROR_less(i, rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87281, _mut87282, _mut87283, _mut87284, _mut87285); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
                striped[++p] = '0';
            }
        }
        // and where the least significant digit is
        for (i = mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87351, _mut87352, _mut87353, _mut87354, _mut87355); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.Dfp_335");
            mant[i] = AOR_plus(AOR_plus(AOR_plus(AOR_multiply((AOR_minus(striped[q], '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87299, _mut87300, _mut87301, _mut87302)), 1000, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87303, _mut87304, _mut87305, _mut87306), AOR_multiply((AOR_minus(striped[AOR_plus(q, 1, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87307, _mut87308, _mut87309, _mut87310)], '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87311, _mut87312, _mut87313, _mut87314)), 100, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87315, _mut87316, _mut87317, _mut87318), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87319, _mut87320, _mut87321, _mut87322), AOR_multiply((AOR_minus(striped[AOR_plus(q, 2, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87323, _mut87324, _mut87325, _mut87326)], '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87327, _mut87328, _mut87329, _mut87330)), 10, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87331, _mut87332, _mut87333, _mut87334), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87335, _mut87336, _mut87337, _mut87338), (AOR_minus(striped[AOR_plus(q, 3, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87339, _mut87340, _mut87341, _mut87342)], '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87343, _mut87344, _mut87345, _mut87346)), "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87347, _mut87348, _mut87349, _mut87350);
            q += 4;
        }
        exp = AOR_divide((AOR_plus(decimalPos, sciexp, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87356, _mut87357, _mut87358, _mut87359)), rsize, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87360, _mut87361, _mut87362, _mut87363);
        if (ROR_less(q, striped.length, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87364, _mut87365, _mut87366, _mut87367, _mut87368)) {
            // Is there possible another digit?
            round(AOR_multiply((AOR_minus(striped[q], '0', "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87369, _mut87370, _mut87371, _mut87372)), 1000, "org.apache.commons.math3.dfp.Dfp.Dfp_335", _mut87373, _mut87374, _mut87375, _mut87376));
        }
    }

    /**
     * Creates an instance with a non-finite value.
     * @param field field to which this instance belongs
     * @param sign sign of the Dfp to create
     * @param nans code of the value, must be one of {@link #INFINITE},
     * {@link #SNAN},  {@link #QNAN}
     */
    protected Dfp(final DfpField field, final byte sign, final byte nans) {
        this.field = field;
        this.mant = new int[field.getRadixDigits()];
        this.sign = sign;
        this.exp = 0;
        this.nans = nans;
    }

    /**
     * Create an instance with a value of 0.
     * Use this internally in preference to constructors to facilitate subclasses
     * @return a new instance with a value of 0
     */
    public Dfp newInstance() {
        return new Dfp(getField());
    }

    /**
     * Create an instance from a byte value.
     * @param x value to convert to an instance
     * @return a new instance with value x
     */
    public Dfp newInstance(final byte x) {
        return new Dfp(getField(), x);
    }

    /**
     * Create an instance from an int value.
     * @param x value to convert to an instance
     * @return a new instance with value x
     */
    public Dfp newInstance(final int x) {
        return new Dfp(getField(), x);
    }

    /**
     * Create an instance from a long value.
     * @param x value to convert to an instance
     * @return a new instance with value x
     */
    public Dfp newInstance(final long x) {
        return new Dfp(getField(), x);
    }

    /**
     * Create an instance from a double value.
     * @param x value to convert to an instance
     * @return a new instance with value x
     */
    public Dfp newInstance(final double x) {
        return new Dfp(getField(), x);
    }

    /**
     * Create an instance by copying an existing one.
     * Use this internally in preference to constructors to facilitate subclasses.
     * @param d instance to copy
     * @return a new instance with the same value as d
     */
    public Dfp newInstance(final Dfp d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.newInstance_595");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), d.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.newInstance_595", _mut87377, _mut87378, _mut87379, _mut87380, _mut87381)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, NEW_INSTANCE_TRAP, d, result);
        }
        return new Dfp(d);
    }

    /**
     * Create an instance from a String representation.
     * Use this internally in preference to constructors to facilitate subclasses.
     * @param s string representation of the instance
     * @return a new instance parsed from specified string
     */
    public Dfp newInstance(final String s) {
        return new Dfp(field, s);
    }

    /**
     * Creates an instance with a non-finite value.
     * @param sig sign of the Dfp to create
     * @param code code of the value, must be one of {@link #INFINITE},
     * {@link #SNAN},  {@link #QNAN}
     * @return a new instance with a non-finite value
     */
    public Dfp newInstance(final byte sig, final byte code) {
        return field.newDfp(sig, code);
    }

    /**
     * Get the {@link org.apache.commons.math3.Field Field} (really a {@link DfpField}) to which the instance belongs.
     * <p>
     * The field is linked to the number of digits and acts as a factory
     * for {@link Dfp} instances.
     * </p>
     * @return {@link org.apache.commons.math3.Field Field} (really a {@link DfpField}) to which the instance belongs
     */
    public DfpField getField() {
        return field;
    }

    /**
     * Get the number of radix digits of the instance.
     * @return number of radix digits
     */
    public int getRadixDigits() {
        return field.getRadixDigits();
    }

    /**
     * Get the constant 0.
     * @return a Dfp with value zero
     */
    public Dfp getZero() {
        return field.getZero();
    }

    /**
     * Get the constant 1.
     * @return a Dfp with value one
     */
    public Dfp getOne() {
        return field.getOne();
    }

    /**
     * Get the constant 2.
     * @return a Dfp with value two
     */
    public Dfp getTwo() {
        return field.getTwo();
    }

    /**
     * Shift the mantissa left, and adjust the exponent to compensate.
     */
    protected void shiftLeft() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.shiftLeft_669");
        for (int i = mant.length - 1; ROR_greater(i, 0, "org.apache.commons.math3.dfp.Dfp.shiftLeft_669", _mut87386, _mut87387, _mut87388, _mut87389, _mut87390); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.shiftLeft_669");
            mant[i] = mant[AOR_minus(i, 1, "org.apache.commons.math3.dfp.Dfp.shiftLeft_669", _mut87382, _mut87383, _mut87384, _mut87385)];
        }
        mant[0] = 0;
        exp--;
    }

    /**
     * Shift the mantissa right, and adjust the exponent to compensate.
     */
    protected void shiftRight() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.shiftRight_681");
        for (int i = 0; ROR_less(i, AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.shiftRight_681", _mut87395, _mut87396, _mut87397, _mut87398), "org.apache.commons.math3.dfp.Dfp.shiftRight_681", _mut87399, _mut87400, _mut87401, _mut87402, _mut87403); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.shiftRight_681");
            mant[i] = mant[AOR_plus(i, 1, "org.apache.commons.math3.dfp.Dfp.shiftRight_681", _mut87391, _mut87392, _mut87393, _mut87394)];
        }
        mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.shiftRight_681", _mut87404, _mut87405, _mut87406, _mut87407)] = 0;
        exp++;
    }

    /**
     * Make our exp equal to the supplied one, this may cause rounding.
     *  Also causes de-normalized numbers.  These numbers are generally
     *  dangerous because most routines assume normalized numbers.
     *  Align doesn't round, so it will return the last digit destroyed
     *  by shifting right.
     *  @param e desired exponent
     *  @return last digit destroyed by shifting right
     */
    protected int align(int e) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.align_697");
        int lostdigit = 0;
        boolean inexact = false;
        int diff = AOR_minus(exp, e, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87408, _mut87409, _mut87410, _mut87411);
        int adiff = diff;
        if (ROR_less(adiff, 0, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87412, _mut87413, _mut87414, _mut87415, _mut87416)) {
            adiff = -adiff;
        }
        if (ROR_equals(diff, 0, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87417, _mut87418, _mut87419, _mut87420, _mut87421)) {
            return 0;
        }
        if (ROR_greater(adiff, (AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87422, _mut87423, _mut87424, _mut87425)), "org.apache.commons.math3.dfp.Dfp.align_697", _mut87426, _mut87427, _mut87428, _mut87429, _mut87430)) {
            // Special case
            Arrays.fill(mant, 0);
            exp = e;
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            dotrap(DfpField.FLAG_INEXACT, ALIGN_TRAP, this, this);
            return 0;
        }
        for (int i = 0; ROR_less(i, adiff, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87441, _mut87442, _mut87443, _mut87444, _mut87445); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.align_697");
            if (ROR_less(diff, 0, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87431, _mut87432, _mut87433, _mut87434, _mut87435)) {
                /* Keep track of loss -- only signal inexact after losing 2 digits.
                 * the first lost digit is returned to add() and may be incorporated
                 * into the result.
                 */
                if (ROR_not_equals(lostdigit, 0, "org.apache.commons.math3.dfp.Dfp.align_697", _mut87436, _mut87437, _mut87438, _mut87439, _mut87440)) {
                    inexact = true;
                }
                lostdigit = mant[0];
                shiftRight();
            } else {
                shiftLeft();
            }
        }
        if (inexact) {
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            dotrap(DfpField.FLAG_INEXACT, ALIGN_TRAP, this, this);
        }
        return lostdigit;
    }

    /**
     * Check if instance is less than x.
     * @param x number to check instance against
     * @return true if instance is less than x and neither are NaN, false otherwise
     */
    public boolean lessThan(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.lessThan_754");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.lessThan_754", _mut87446, _mut87447, _mut87448, _mut87449, _mut87450)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, x, result);
            return false;
        }
        /* if a nan is involved, signal invalid and return false */
        if ((_mut87451 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN()))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, x, newInstance(getZero()));
            return false;
        }
        return ROR_less(compare(this, x), 0, "org.apache.commons.math3.dfp.Dfp.lessThan_754", _mut87452, _mut87453, _mut87454, _mut87455, _mut87456);
    }

    /**
     * Check if instance is greater than x.
     * @param x number to check instance against
     * @return true if instance is greater than x and neither are NaN, false otherwise
     */
    public boolean greaterThan(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.greaterThan_779");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.greaterThan_779", _mut87457, _mut87458, _mut87459, _mut87460, _mut87461)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            dotrap(DfpField.FLAG_INVALID, GREATER_THAN_TRAP, x, result);
            return false;
        }
        /* if a nan is involved, signal invalid and return false */
        if ((_mut87462 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN()))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, GREATER_THAN_TRAP, x, newInstance(getZero()));
            return false;
        }
        return ROR_greater(compare(this, x), 0, "org.apache.commons.math3.dfp.Dfp.greaterThan_779", _mut87463, _mut87464, _mut87465, _mut87466, _mut87467);
    }

    /**
     * Check if instance is less than or equal to 0.
     * @return true if instance is not NaN and less than or equal to 0, false otherwise
     */
    public boolean negativeOrNull() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.negativeOrNull_803");
        if (isNaN()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        return (_mut87483 ? ((ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87468, _mut87469, _mut87470, _mut87471, _mut87472)) && ((_mut87482 ? ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87473, _mut87474, _mut87475, _mut87476)], 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87477, _mut87478, _mut87479, _mut87480, _mut87481)) || !isInfinite()) : ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87473, _mut87474, _mut87475, _mut87476)], 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87477, _mut87478, _mut87479, _mut87480, _mut87481)) && !isInfinite())))) : ((ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87468, _mut87469, _mut87470, _mut87471, _mut87472)) || ((_mut87482 ? ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87473, _mut87474, _mut87475, _mut87476)], 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87477, _mut87478, _mut87479, _mut87480, _mut87481)) || !isInfinite()) : ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87473, _mut87474, _mut87475, _mut87476)], 0, "org.apache.commons.math3.dfp.Dfp.negativeOrNull_803", _mut87477, _mut87478, _mut87479, _mut87480, _mut87481)) && !isInfinite())))));
    }

    /**
     * Check if instance is strictly less than 0.
     * @return true if instance is not NaN and less than or equal to 0, false otherwise
     */
    public boolean strictlyNegative() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.strictlyNegative_818");
        if (isNaN()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        return (_mut87499 ? ((ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87484, _mut87485, _mut87486, _mut87487, _mut87488)) || ((_mut87498 ? ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87489, _mut87490, _mut87491, _mut87492)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87493, _mut87494, _mut87495, _mut87496, _mut87497)) && isInfinite()) : ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87489, _mut87490, _mut87491, _mut87492)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87493, _mut87494, _mut87495, _mut87496, _mut87497)) || isInfinite())))) : ((ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87484, _mut87485, _mut87486, _mut87487, _mut87488)) && ((_mut87498 ? ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87489, _mut87490, _mut87491, _mut87492)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87493, _mut87494, _mut87495, _mut87496, _mut87497)) && isInfinite()) : ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87489, _mut87490, _mut87491, _mut87492)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyNegative_818", _mut87493, _mut87494, _mut87495, _mut87496, _mut87497)) || isInfinite())))));
    }

    /**
     * Check if instance is greater than or equal to 0.
     * @return true if instance is not NaN and greater than or equal to 0, false otherwise
     */
    public boolean positiveOrNull() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.positiveOrNull_833");
        if (isNaN()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        return (_mut87515 ? ((ROR_greater(sign, 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87500, _mut87501, _mut87502, _mut87503, _mut87504)) && ((_mut87514 ? ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87505, _mut87506, _mut87507, _mut87508)], 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87509, _mut87510, _mut87511, _mut87512, _mut87513)) || !isInfinite()) : ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87505, _mut87506, _mut87507, _mut87508)], 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87509, _mut87510, _mut87511, _mut87512, _mut87513)) && !isInfinite())))) : ((ROR_greater(sign, 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87500, _mut87501, _mut87502, _mut87503, _mut87504)) || ((_mut87514 ? ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87505, _mut87506, _mut87507, _mut87508)], 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87509, _mut87510, _mut87511, _mut87512, _mut87513)) || !isInfinite()) : ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87505, _mut87506, _mut87507, _mut87508)], 0, "org.apache.commons.math3.dfp.Dfp.positiveOrNull_833", _mut87509, _mut87510, _mut87511, _mut87512, _mut87513)) && !isInfinite())))));
    }

    /**
     * Check if instance is strictly greater than 0.
     * @return true if instance is not NaN and greater than or equal to 0, false otherwise
     */
    public boolean strictlyPositive() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.strictlyPositive_848");
        if (isNaN()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        return (_mut87531 ? ((ROR_greater(sign, 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87516, _mut87517, _mut87518, _mut87519, _mut87520)) || ((_mut87530 ? ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87521, _mut87522, _mut87523, _mut87524)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87525, _mut87526, _mut87527, _mut87528, _mut87529)) && isInfinite()) : ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87521, _mut87522, _mut87523, _mut87524)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87525, _mut87526, _mut87527, _mut87528, _mut87529)) || isInfinite())))) : ((ROR_greater(sign, 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87516, _mut87517, _mut87518, _mut87519, _mut87520)) && ((_mut87530 ? ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87521, _mut87522, _mut87523, _mut87524)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87525, _mut87526, _mut87527, _mut87528, _mut87529)) && isInfinite()) : ((ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87521, _mut87522, _mut87523, _mut87524)], 0, "org.apache.commons.math3.dfp.Dfp.strictlyPositive_848", _mut87525, _mut87526, _mut87527, _mut87528, _mut87529)) || isInfinite())))));
    }

    /**
     * Get the absolute value of instance.
     * @return absolute value of instance
     * @since 3.2
     */
    public Dfp abs() {
        Dfp result = newInstance(this);
        result.sign = 1;
        return result;
    }

    /**
     * Check if instance is infinite.
     * @return true if instance is infinite
     */
    public boolean isInfinite() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.isInfinite_873");
        return ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.isInfinite_873", _mut87532, _mut87533, _mut87534, _mut87535, _mut87536);
    }

    /**
     * Check if instance is not a number.
     * @return true if instance is not a number
     */
    public boolean isNaN() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.isNaN_880");
        return (_mut87547 ? ((ROR_equals(nans, QNAN, "org.apache.commons.math3.dfp.Dfp.isNaN_880", _mut87537, _mut87538, _mut87539, _mut87540, _mut87541)) && (ROR_equals(nans, SNAN, "org.apache.commons.math3.dfp.Dfp.isNaN_880", _mut87542, _mut87543, _mut87544, _mut87545, _mut87546))) : ((ROR_equals(nans, QNAN, "org.apache.commons.math3.dfp.Dfp.isNaN_880", _mut87537, _mut87538, _mut87539, _mut87540, _mut87541)) || (ROR_equals(nans, SNAN, "org.apache.commons.math3.dfp.Dfp.isNaN_880", _mut87542, _mut87543, _mut87544, _mut87545, _mut87546))));
    }

    /**
     * Check if instance is equal to zero.
     * @return true if instance is equal to zero
     */
    public boolean isZero() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.isZero_887");
        if (isNaN()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            dotrap(DfpField.FLAG_INVALID, LESS_THAN_TRAP, this, newInstance(getZero()));
            return false;
        }
        return (_mut87557 ? ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.isZero_887", _mut87548, _mut87549, _mut87550, _mut87551)], 0, "org.apache.commons.math3.dfp.Dfp.isZero_887", _mut87552, _mut87553, _mut87554, _mut87555, _mut87556)) || !isInfinite()) : ((ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.isZero_887", _mut87548, _mut87549, _mut87550, _mut87551)], 0, "org.apache.commons.math3.dfp.Dfp.isZero_887", _mut87552, _mut87553, _mut87554, _mut87555, _mut87556)) && !isInfinite()));
    }

    /**
     * Check if instance is equal to x.
     * @param other object to check instance against
     * @return true if instance is equal to x and neither are NaN, false otherwise
     */
    @Override
    public boolean equals(final Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.equals_903");
        if (other instanceof Dfp) {
            final Dfp x = (Dfp) other;
            if ((_mut87564 ? ((_mut87558 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN())) && ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.equals_903", _mut87559, _mut87560, _mut87561, _mut87562, _mut87563)) : ((_mut87558 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN())) || ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.equals_903", _mut87559, _mut87560, _mut87561, _mut87562, _mut87563)))) {
                return false;
            }
            return ROR_equals(compare(this, x), 0, "org.apache.commons.math3.dfp.Dfp.equals_903", _mut87565, _mut87566, _mut87567, _mut87568, _mut87569);
        }
        return false;
    }

    /**
     * Gets a hashCode for the instance.
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.hashCode_923");
        return AOR_plus(AOR_plus(AOR_plus(AOR_plus(17, (isZero() ? 0 : (sign << 8)), "org.apache.commons.math3.dfp.Dfp.hashCode_923", _mut87570, _mut87571, _mut87572, _mut87573), (nans << 16), "org.apache.commons.math3.dfp.Dfp.hashCode_923", _mut87574, _mut87575, _mut87576, _mut87577), exp, "org.apache.commons.math3.dfp.Dfp.hashCode_923", _mut87578, _mut87579, _mut87580, _mut87581), Arrays.hashCode(mant), "org.apache.commons.math3.dfp.Dfp.hashCode_923", _mut87582, _mut87583, _mut87584, _mut87585);
    }

    /**
     * Check if instance is not equal to x.
     * @param x number to check instance against
     * @return true if instance is not equal to x and neither are NaN, false otherwise
     */
    public boolean unequal(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.unequal_932");
        if ((_mut87592 ? ((_mut87586 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN())) && ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.unequal_932", _mut87587, _mut87588, _mut87589, _mut87590, _mut87591)) : ((_mut87586 ? (isNaN() && x.isNaN()) : (isNaN() || x.isNaN())) || ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.unequal_932", _mut87587, _mut87588, _mut87589, _mut87590, _mut87591)))) {
            return false;
        }
        return (_mut87593 ? (greaterThan(x) && lessThan(x)) : (greaterThan(x) || lessThan(x)));
    }

    /**
     * Compare two instances.
     * @param a first instance in comparison
     * @param b second instance in comparison
     * @return -1 if a<b, 1 if a>b and 0 if a==b
     *  Note this method does not properly handle NaNs or numbers with different precision.
     */
    private static int compare(final Dfp a, final Dfp b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.compare_946");
        // Ignore the sign of zero
        if ((_mut87624 ? ((_mut87618 ? ((_mut87612 ? (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) || ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611)) : (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) && ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611))) || ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87613, _mut87614, _mut87615, _mut87616, _mut87617)) : ((_mut87612 ? (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) || ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611)) : (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) && ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611))) && ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87613, _mut87614, _mut87615, _mut87616, _mut87617))) || ROR_equals(b.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87619, _mut87620, _mut87621, _mut87622, _mut87623)) : ((_mut87618 ? ((_mut87612 ? (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) || ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611)) : (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) && ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611))) || ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87613, _mut87614, _mut87615, _mut87616, _mut87617)) : ((_mut87612 ? (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) || ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611)) : (ROR_equals(a.mant[AOR_minus(a.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87594, _mut87595, _mut87596, _mut87597)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87598, _mut87599, _mut87600, _mut87601, _mut87602) && ROR_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87603, _mut87604, _mut87605, _mut87606)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87607, _mut87608, _mut87609, _mut87610, _mut87611))) && ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87613, _mut87614, _mut87615, _mut87616, _mut87617))) && ROR_equals(b.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87619, _mut87620, _mut87621, _mut87622, _mut87623)))) {
            return 0;
        }
        if (ROR_not_equals(a.sign, b.sign, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87625, _mut87626, _mut87627, _mut87628, _mut87629)) {
            if (ROR_equals(a.sign, -1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87630, _mut87631, _mut87632, _mut87633, _mut87634)) {
                return -1;
            } else {
                return 1;
            }
        }
        // deal with the infinities
        if ((_mut87645 ? (ROR_equals(a.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87635, _mut87636, _mut87637, _mut87638, _mut87639) || ROR_equals(b.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87640, _mut87641, _mut87642, _mut87643, _mut87644)) : (ROR_equals(a.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87635, _mut87636, _mut87637, _mut87638, _mut87639) && ROR_equals(b.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87640, _mut87641, _mut87642, _mut87643, _mut87644)))) {
            return a.sign;
        }
        if ((_mut87656 ? (ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87646, _mut87647, _mut87648, _mut87649, _mut87650) || ROR_equals(b.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87651, _mut87652, _mut87653, _mut87654, _mut87655)) : (ROR_equals(a.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87646, _mut87647, _mut87648, _mut87649, _mut87650) && ROR_equals(b.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87651, _mut87652, _mut87653, _mut87654, _mut87655)))) {
            return -b.sign;
        }
        if ((_mut87667 ? (ROR_equals(a.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87657, _mut87658, _mut87659, _mut87660, _mut87661) || ROR_equals(b.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87662, _mut87663, _mut87664, _mut87665, _mut87666)) : (ROR_equals(a.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87657, _mut87658, _mut87659, _mut87660, _mut87661) && ROR_equals(b.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87662, _mut87663, _mut87664, _mut87665, _mut87666)))) {
            return 0;
        }
        // Handle special case when a or b is zero, by ignoring the exponents
        if ((_mut87686 ? (ROR_not_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87668, _mut87669, _mut87670, _mut87671)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87672, _mut87673, _mut87674, _mut87675, _mut87676) || ROR_not_equals(a.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87677, _mut87678, _mut87679, _mut87680)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87681, _mut87682, _mut87683, _mut87684, _mut87685)) : (ROR_not_equals(b.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87668, _mut87669, _mut87670, _mut87671)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87672, _mut87673, _mut87674, _mut87675, _mut87676) && ROR_not_equals(a.mant[AOR_minus(b.mant.length, 1, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87677, _mut87678, _mut87679, _mut87680)], 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87681, _mut87682, _mut87683, _mut87684, _mut87685)))) {
            if (ROR_less(a.exp, b.exp, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87687, _mut87688, _mut87689, _mut87690, _mut87691)) {
                return -a.sign;
            }
            if (ROR_greater(a.exp, b.exp, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87692, _mut87693, _mut87694, _mut87695, _mut87696)) {
                return a.sign;
            }
        }
        // compare the mantissas
        for (int i = a.mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87707, _mut87708, _mut87709, _mut87710, _mut87711); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.compare_946");
            if (ROR_greater(a.mant[i], b.mant[i], "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87697, _mut87698, _mut87699, _mut87700, _mut87701)) {
                return a.sign;
            }
            if (ROR_less(a.mant[i], b.mant[i], "org.apache.commons.math3.dfp.Dfp.compare_946", _mut87702, _mut87703, _mut87704, _mut87705, _mut87706)) {
                return -a.sign;
            }
        }
        return 0;
    }

    /**
     * Round to nearest integer using the round-half-even method.
     *  That is round to nearest integer unless both are equidistant.
     *  In which case round to the even one.
     *  @return rounded value
     * @since 3.2
     */
    public Dfp rint() {
        return trunc(DfpField.RoundingMode.ROUND_HALF_EVEN);
    }

    /**
     * Round to an integer using the round floor mode.
     * That is, round toward -Infinity
     *  @return rounded value
     * @since 3.2
     */
    public Dfp floor() {
        return trunc(DfpField.RoundingMode.ROUND_FLOOR);
    }

    /**
     * Round to an integer using the round ceil mode.
     * That is, round toward +Infinity
     *  @return rounded value
     * @since 3.2
     */
    public Dfp ceil() {
        return trunc(DfpField.RoundingMode.ROUND_CEIL);
    }

    /**
     * Returns the IEEE remainder.
     * @param d divisor
     * @return this less n &times; d, where n is the integer closest to this/d
     * @since 3.2
     */
    public Dfp remainder(final Dfp d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.remainder_1033");
        final Dfp result = this.subtract(this.divide(d).rint().multiply(d));
        // IEEE 854-1987 says that if the result is zero, then it carries the sign of this
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.remainder_1033", _mut87712, _mut87713, _mut87714, _mut87715)], 0, "org.apache.commons.math3.dfp.Dfp.remainder_1033", _mut87716, _mut87717, _mut87718, _mut87719, _mut87720)) {
            result.sign = sign;
        }
        return result;
    }

    /**
     * Does the integer conversions with the specified rounding.
     * @param rmode rounding mode to use
     * @return truncated value
     */
    protected Dfp trunc(final DfpField.RoundingMode rmode) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.trunc_1050");
        boolean changed = false;
        if (isNaN()) {
            return newInstance(this);
        }
        if (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87721, _mut87722, _mut87723, _mut87724, _mut87725)) {
            return newInstance(this);
        }
        if (ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87726, _mut87727, _mut87728, _mut87729)], 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87730, _mut87731, _mut87732, _mut87733, _mut87734)) {
            // a is zero
            return newInstance(this);
        }
        /* If the exponent is less than zero then we can certainly
         * return zero */
        if (ROR_less(exp, 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87735, _mut87736, _mut87737, _mut87738, _mut87739)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            Dfp result = newInstance(getZero());
            result = dotrap(DfpField.FLAG_INEXACT, TRUNC_TRAP, this, result);
            return result;
        }
        if (ROR_greater_equals(exp, mant.length, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87740, _mut87741, _mut87742, _mut87743, _mut87744)) {
            return newInstance(this);
        }
        Dfp result = newInstance(this);
        for (int i = 0; ROR_less(i, AOR_minus(mant.length, result.exp, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87750, _mut87751, _mut87752, _mut87753), "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87754, _mut87755, _mut87756, _mut87757, _mut87758); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.trunc_1050");
            changed |= ROR_not_equals(result.mant[i], 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87745, _mut87746, _mut87747, _mut87748, _mut87749);
            result.mant[i] = 0;
        }
        if (changed) {
            switch(rmode) {
                case ROUND_FLOOR:
                    if (ROR_equals(result.sign, -1, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87759, _mut87760, _mut87761, _mut87762, _mut87763)) {
                        // then we must increment the mantissa by one
                        result = result.add(newInstance(-1));
                    }
                    break;
                case ROUND_CEIL:
                    if (ROR_equals(result.sign, 1, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87764, _mut87765, _mut87766, _mut87767, _mut87768)) {
                        // then we must increment the mantissa by one
                        result = result.add(getOne());
                    }
                    break;
                case ROUND_HALF_EVEN:
                default:
                    final Dfp half = newInstance("0.5");
                    // difference between this and result
                    Dfp a = subtract(result);
                    // force positive (take abs)
                    a.sign = 1;
                    if (a.greaterThan(half)) {
                        a = newInstance(getOne());
                        a.sign = sign;
                        result = result.add(a);
                    }
                    /**
                     * If exactly equal to 1/2 and odd then increment
                     */
                    if ((_mut87784 ? ((_mut87774 ? (a.equals(half) || ROR_greater(result.exp, 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87769, _mut87770, _mut87771, _mut87772, _mut87773)) : (a.equals(half) && ROR_greater(result.exp, 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87769, _mut87770, _mut87771, _mut87772, _mut87773))) || ROR_not_equals((result.mant[AOR_minus(mant.length, result.exp, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87775, _mut87776, _mut87777, _mut87778)] & 1), 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87779, _mut87780, _mut87781, _mut87782, _mut87783)) : ((_mut87774 ? (a.equals(half) || ROR_greater(result.exp, 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87769, _mut87770, _mut87771, _mut87772, _mut87773)) : (a.equals(half) && ROR_greater(result.exp, 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87769, _mut87770, _mut87771, _mut87772, _mut87773))) && ROR_not_equals((result.mant[AOR_minus(mant.length, result.exp, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87775, _mut87776, _mut87777, _mut87778)] & 1), 0, "org.apache.commons.math3.dfp.Dfp.trunc_1050", _mut87779, _mut87780, _mut87781, _mut87782, _mut87783)))) {
                        a = newInstance(getOne());
                        a.sign = sign;
                        result = result.add(a);
                    }
                    break;
            }
            // signal inexact
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            result = dotrap(DfpField.FLAG_INEXACT, TRUNC_TRAP, this, result);
            return result;
        }
        return result;
    }

    /**
     * Convert this to an integer.
     * If greater than 2147483647, it returns 2147483647. If less than -2147483648 it returns -2147483648.
     * @return converted number
     */
    public int intValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.intValue_1140");
        Dfp rounded;
        int result = 0;
        rounded = rint();
        if (rounded.greaterThan(newInstance(2147483647))) {
            return 2147483647;
        }
        if (rounded.lessThan(newInstance(-2147483648))) {
            return -2147483648;
        }
        for (int i = mant.length - 1; ROR_greater_equals(i, AOR_minus(mant.length, rounded.exp, "org.apache.commons.math3.dfp.Dfp.intValue_1140", _mut87793, _mut87794, _mut87795, _mut87796), "org.apache.commons.math3.dfp.Dfp.intValue_1140", _mut87797, _mut87798, _mut87799, _mut87800, _mut87801); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.intValue_1140");
            result = AOR_plus(AOR_multiply(result, RADIX, "org.apache.commons.math3.dfp.Dfp.intValue_1140", _mut87785, _mut87786, _mut87787, _mut87788), rounded.mant[i], "org.apache.commons.math3.dfp.Dfp.intValue_1140", _mut87789, _mut87790, _mut87791, _mut87792);
        }
        if (ROR_equals(rounded.sign, -1, "org.apache.commons.math3.dfp.Dfp.intValue_1140", _mut87802, _mut87803, _mut87804, _mut87805, _mut87806)) {
            result = -result;
        }
        return result;
    }

    /**
     * Get the exponent of the greatest power of 10000 that is
     *  less than or equal to the absolute value of this.  I.E.  if
     *  this is 10<sup>6</sup> then log10K would return 1.
     *  @return integer base 10000 logarithm
     */
    public int log10K() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.log10K_1170");
        return AOR_minus(exp, 1, "org.apache.commons.math3.dfp.Dfp.log10K_1170", _mut87807, _mut87808, _mut87809, _mut87810);
    }

    /**
     * Get the specified  power of 10000.
     * @param e desired power
     * @return 10000<sup>e</sup>
     */
    public Dfp power10K(final int e) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.power10K_1178");
        Dfp d = newInstance(getOne());
        d.exp = AOR_plus(e, 1, "org.apache.commons.math3.dfp.Dfp.power10K_1178", _mut87811, _mut87812, _mut87813, _mut87814);
        return d;
    }

    /**
     * Get the exponent of the greatest power of 10 that is less than or equal to abs(this).
     *  @return integer base 10 logarithm
     * @since 3.2
     */
    public int intLog10() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.intLog10_1188");
        if (ROR_greater(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87815, _mut87816, _mut87817, _mut87818)], 1000, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87819, _mut87820, _mut87821, _mut87822, _mut87823)) {
            return AOR_minus(AOR_multiply(exp, 4, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87824, _mut87825, _mut87826, _mut87827), 1, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87828, _mut87829, _mut87830, _mut87831);
        }
        if (ROR_greater(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87832, _mut87833, _mut87834, _mut87835)], 100, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87836, _mut87837, _mut87838, _mut87839, _mut87840)) {
            return AOR_minus(AOR_multiply(exp, 4, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87841, _mut87842, _mut87843, _mut87844), 2, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87845, _mut87846, _mut87847, _mut87848);
        }
        if (ROR_greater(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87849, _mut87850, _mut87851, _mut87852)], 10, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87853, _mut87854, _mut87855, _mut87856, _mut87857)) {
            return AOR_minus(AOR_multiply(exp, 4, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87858, _mut87859, _mut87860, _mut87861), 3, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87862, _mut87863, _mut87864, _mut87865);
        }
        return AOR_minus(AOR_multiply(exp, 4, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87866, _mut87867, _mut87868, _mut87869), 4, "org.apache.commons.math3.dfp.Dfp.intLog10_1188", _mut87870, _mut87871, _mut87872, _mut87873);
    }

    /**
     * Return the specified  power of 10.
     * @param e desired power
     * @return 10<sup>e</sup>
     */
    public Dfp power10(final int e) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.power10_1205");
        Dfp d = newInstance(getOne());
        if (ROR_greater_equals(e, 0, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87874, _mut87875, _mut87876, _mut87877, _mut87878)) {
            d.exp = AOR_plus(AOR_divide(e, 4, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87887, _mut87888, _mut87889, _mut87890), 1, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87891, _mut87892, _mut87893, _mut87894);
        } else {
            d.exp = AOR_divide((AOR_plus(e, 1, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87879, _mut87880, _mut87881, _mut87882)), 4, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87883, _mut87884, _mut87885, _mut87886);
        }
        switch(AOR_remainder((AOR_plus(AOR_remainder(e, 4, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87895, _mut87896, _mut87897, _mut87898), 4, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87899, _mut87900, _mut87901, _mut87902)), 4, "org.apache.commons.math3.dfp.Dfp.power10_1205", _mut87903, _mut87904, _mut87905, _mut87906)) {
            case 0:
                break;
            case 1:
                d = d.multiply(10);
                break;
            case 2:
                d = d.multiply(100);
                break;
            default:
                d = d.multiply(1000);
        }
        return d;
    }

    /**
     * Negate the mantissa of this by computing the complement.
     *  Leaves the sign bit unchanged, used internally by add.
     *  Denormalized numbers are handled properly here.
     *  @param extra ???
     *  @return ???
     */
    protected int complement(int extra) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.complement_1236");
        extra = AOR_minus(RADIX, extra, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87907, _mut87908, _mut87909, _mut87910);
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87919, _mut87920, _mut87921, _mut87922, _mut87923); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.complement_1236");
            mant[i] = AOR_minus(AOR_minus(RADIX, mant[i], "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87911, _mut87912, _mut87913, _mut87914), 1, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87915, _mut87916, _mut87917, _mut87918);
        }
        int rh = AOR_divide(extra, RADIX, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87924, _mut87925, _mut87926, _mut87927);
        extra -= AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87928, _mut87929, _mut87930, _mut87931);
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87948, _mut87949, _mut87950, _mut87951, _mut87952); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.complement_1236");
            final int r = AOR_plus(mant[i], rh, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87932, _mut87933, _mut87934, _mut87935);
            rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87936, _mut87937, _mut87938, _mut87939);
            mant[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87940, _mut87941, _mut87942, _mut87943), "org.apache.commons.math3.dfp.Dfp.complement_1236", _mut87944, _mut87945, _mut87946, _mut87947);
        }
        return extra;
    }

    /**
     * Add x to this.
     * @param x number to add
     * @return sum of this and x
     */
    public Dfp add(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.add_1258");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87953, _mut87954, _mut87955, _mut87956, _mut87957)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, ADD_TRAP, x, result);
        }
        /* handle special cases */
        if ((_mut87968 ? (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87958, _mut87959, _mut87960, _mut87961, _mut87962) && ROR_not_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87963, _mut87964, _mut87965, _mut87966, _mut87967)) : (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87958, _mut87959, _mut87960, _mut87961, _mut87962) || ROR_not_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87963, _mut87964, _mut87965, _mut87966, _mut87967)))) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if ((_mut87979 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87969, _mut87970, _mut87971, _mut87972, _mut87973) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87974, _mut87975, _mut87976, _mut87977, _mut87978)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87969, _mut87970, _mut87971, _mut87972, _mut87973) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87974, _mut87975, _mut87976, _mut87977, _mut87978)))) {
                return this;
            }
            if ((_mut87990 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87980, _mut87981, _mut87982, _mut87983, _mut87984) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87985, _mut87986, _mut87987, _mut87988, _mut87989)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87980, _mut87981, _mut87982, _mut87983, _mut87984) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87985, _mut87986, _mut87987, _mut87988, _mut87989)))) {
                return x;
            }
            if ((_mut88007 ? ((_mut88001 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87991, _mut87992, _mut87993, _mut87994, _mut87995) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87996, _mut87997, _mut87998, _mut87999, _mut88000)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87991, _mut87992, _mut87993, _mut87994, _mut87995) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87996, _mut87997, _mut87998, _mut87999, _mut88000))) || ROR_equals(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88002, _mut88003, _mut88004, _mut88005, _mut88006)) : ((_mut88001 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87991, _mut87992, _mut87993, _mut87994, _mut87995) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87996, _mut87997, _mut87998, _mut87999, _mut88000)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87991, _mut87992, _mut87993, _mut87994, _mut87995) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut87996, _mut87997, _mut87998, _mut87999, _mut88000))) && ROR_equals(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88002, _mut88003, _mut88004, _mut88005, _mut88006)))) {
                return x;
            }
            if ((_mut88024 ? ((_mut88018 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88008, _mut88009, _mut88010, _mut88011, _mut88012) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88013, _mut88014, _mut88015, _mut88016, _mut88017)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88008, _mut88009, _mut88010, _mut88011, _mut88012) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88013, _mut88014, _mut88015, _mut88016, _mut88017))) || ROR_not_equals(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88019, _mut88020, _mut88021, _mut88022, _mut88023)) : ((_mut88018 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88008, _mut88009, _mut88010, _mut88011, _mut88012) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88013, _mut88014, _mut88015, _mut88016, _mut88017)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88008, _mut88009, _mut88010, _mut88011, _mut88012) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88013, _mut88014, _mut88015, _mut88016, _mut88017))) && ROR_not_equals(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88019, _mut88020, _mut88021, _mut88022, _mut88023)))) {
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                Dfp result = newInstance(getZero());
                result.nans = QNAN;
                result = dotrap(DfpField.FLAG_INVALID, ADD_TRAP, x, result);
                return result;
            }
        }
        /* copy this and the arg */
        Dfp a = newInstance(this);
        Dfp b = newInstance(x);
        /* initialize the result object */
        Dfp result = newInstance(getZero());
        /* Make all numbers positive, but remember their sign */
        final byte asign = a.sign;
        final byte bsign = b.sign;
        a.sign = 1;
        b.sign = 1;
        /* The result will be signed like the arg with greatest magnitude */
        byte rsign = bsign;
        if (ROR_greater(compare(a, b), 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88025, _mut88026, _mut88027, _mut88028, _mut88029)) {
            rsign = asign;
        }
        /* Handle special case when a or b is zero, by setting the exponent
       of the zero number equal to the other one.  This avoids an alignment
       which would cause catastropic loss of precision */
        if (ROR_equals(b.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88030, _mut88031, _mut88032, _mut88033)], 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88034, _mut88035, _mut88036, _mut88037, _mut88038)) {
            b.exp = a.exp;
        }
        if (ROR_equals(a.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88039, _mut88040, _mut88041, _mut88042)], 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88043, _mut88044, _mut88045, _mut88046, _mut88047)) {
            a.exp = b.exp;
        }
        /* align number with the smaller exponent */
        int aextradigit = 0;
        int bextradigit = 0;
        if (ROR_less(a.exp, b.exp, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88048, _mut88049, _mut88050, _mut88051, _mut88052)) {
            aextradigit = a.align(b.exp);
        } else {
            bextradigit = b.align(a.exp);
        }
        /* complement the smaller of the two if the signs are different */
        if (ROR_not_equals(asign, bsign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88053, _mut88054, _mut88055, _mut88056, _mut88057)) {
            if (ROR_equals(asign, rsign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88058, _mut88059, _mut88060, _mut88061, _mut88062)) {
                bextradigit = b.complement(bextradigit);
            } else {
                aextradigit = a.complement(aextradigit);
            }
        }
        /* add the mantissas */
        int rh = 0;
        /* acts as a carry */
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88083, _mut88084, _mut88085, _mut88086, _mut88087); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.add_1258");
            final int r = AOR_plus(AOR_plus(a.mant[i], b.mant[i], "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88063, _mut88064, _mut88065, _mut88066), rh, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88067, _mut88068, _mut88069, _mut88070);
            rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88071, _mut88072, _mut88073, _mut88074);
            result.mant[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88075, _mut88076, _mut88077, _mut88078), "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88079, _mut88080, _mut88081, _mut88082);
        }
        result.exp = a.exp;
        result.sign = rsign;
        if ((_mut88098 ? (ROR_not_equals(rh, 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88088, _mut88089, _mut88090, _mut88091, _mut88092) || (ROR_equals(asign, bsign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88093, _mut88094, _mut88095, _mut88096, _mut88097))) : (ROR_not_equals(rh, 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88088, _mut88089, _mut88090, _mut88091, _mut88092) && (ROR_equals(asign, bsign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88093, _mut88094, _mut88095, _mut88096, _mut88097))))) {
            final int lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88099, _mut88100, _mut88101, _mut88102)] = rh;
            final int excp = result.round(lostdigit);
            if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88103, _mut88104, _mut88105, _mut88106, _mut88107)) {
                result = dotrap(excp, ADD_TRAP, x, result);
            }
        }
        /* normalize the result */
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88126, _mut88127, _mut88128, _mut88129, _mut88130); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.add_1258");
            if (ROR_not_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88108, _mut88109, _mut88110, _mut88111)], 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88112, _mut88113, _mut88114, _mut88115, _mut88116)) {
                break;
            }
            result.shiftLeft();
            if (ROR_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88117, _mut88118, _mut88119, _mut88120, _mut88121)) {
                result.mant[0] = AOR_plus(aextradigit, bextradigit, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88122, _mut88123, _mut88124, _mut88125);
                aextradigit = 0;
                bextradigit = 0;
            }
        }
        /* result is zero if after normalization the most sig. digit is zero */
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88131, _mut88132, _mut88133, _mut88134)], 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88135, _mut88136, _mut88137, _mut88138, _mut88139)) {
            result.exp = 0;
            if (ROR_not_equals(asign, bsign, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88140, _mut88141, _mut88142, _mut88143, _mut88144)) {
                // Per IEEE 854-1987 Section 6.3
                result.sign = 1;
            }
        }
        /* Call round to test for over/under flows */
        final int excp = result.round(AOR_plus(aextradigit, bextradigit, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88145, _mut88146, _mut88147, _mut88148));
        if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.add_1258", _mut88149, _mut88150, _mut88151, _mut88152, _mut88153)) {
            result = dotrap(excp, ADD_TRAP, x, result);
        }
        return result;
    }

    /**
     * Returns a number that is this number with the sign bit reversed.
     * @return the opposite of this
     */
    public Dfp negate() {
        Dfp result = newInstance(this);
        result.sign = (byte) -result.sign;
        return result;
    }

    /**
     * Subtract x from this.
     * @param x number to subtract
     * @return difference of this and a
     */
    public Dfp subtract(final Dfp x) {
        return add(x.negate());
    }

    /**
     * Round this given the next digit n using the current rounding mode.
     * @param n ???
     * @return the IEEE flag if an exception occurred
     */
    protected int round(int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.round_1424");
        boolean inc = false;
        switch(field.getRoundingMode()) {
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                // round up if n!=0
                inc = ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88154, _mut88155, _mut88156, _mut88157, _mut88158);
                break;
            case ROUND_HALF_UP:
                // round half up
                inc = ROR_greater_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88159, _mut88160, _mut88161, _mut88162, _mut88163);
                break;
            case ROUND_HALF_DOWN:
                // round half down
                inc = ROR_greater(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88164, _mut88165, _mut88166, _mut88167, _mut88168);
                break;
            case ROUND_HALF_EVEN:
                // round half-even
                inc = (_mut88185 ? (ROR_greater(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88169, _mut88170, _mut88171, _mut88172, _mut88173) && ((_mut88184 ? (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88174, _mut88175, _mut88176, _mut88177, _mut88178) || ROR_equals((mant[0] & 1), 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88179, _mut88180, _mut88181, _mut88182, _mut88183)) : (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88174, _mut88175, _mut88176, _mut88177, _mut88178) && ROR_equals((mant[0] & 1), 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88179, _mut88180, _mut88181, _mut88182, _mut88183))))) : (ROR_greater(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88169, _mut88170, _mut88171, _mut88172, _mut88173) || ((_mut88184 ? (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88174, _mut88175, _mut88176, _mut88177, _mut88178) || ROR_equals((mant[0] & 1), 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88179, _mut88180, _mut88181, _mut88182, _mut88183)) : (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88174, _mut88175, _mut88176, _mut88177, _mut88178) && ROR_equals((mant[0] & 1), 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88179, _mut88180, _mut88181, _mut88182, _mut88183))))));
                break;
            case ROUND_HALF_ODD:
                // round half-odd
                inc = (_mut88202 ? (ROR_greater(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88186, _mut88187, _mut88188, _mut88189, _mut88190) && ((_mut88201 ? (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88191, _mut88192, _mut88193, _mut88194, _mut88195) || ROR_equals((mant[0] & 1), 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88196, _mut88197, _mut88198, _mut88199, _mut88200)) : (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88191, _mut88192, _mut88193, _mut88194, _mut88195) && ROR_equals((mant[0] & 1), 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88196, _mut88197, _mut88198, _mut88199, _mut88200))))) : (ROR_greater(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88186, _mut88187, _mut88188, _mut88189, _mut88190) || ((_mut88201 ? (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88191, _mut88192, _mut88193, _mut88194, _mut88195) || ROR_equals((mant[0] & 1), 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88196, _mut88197, _mut88198, _mut88199, _mut88200)) : (ROR_equals(n, 5000, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88191, _mut88192, _mut88193, _mut88194, _mut88195) && ROR_equals((mant[0] & 1), 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88196, _mut88197, _mut88198, _mut88199, _mut88200))))));
                break;
            case ROUND_CEIL:
                // round ceil
                inc = (_mut88213 ? (ROR_equals(sign, 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88203, _mut88204, _mut88205, _mut88206, _mut88207) || ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88208, _mut88209, _mut88210, _mut88211, _mut88212)) : (ROR_equals(sign, 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88203, _mut88204, _mut88205, _mut88206, _mut88207) && ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88208, _mut88209, _mut88210, _mut88211, _mut88212)));
                break;
            case ROUND_FLOOR:
            default:
                // round floor
                inc = (_mut88224 ? (ROR_equals(sign, -1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88214, _mut88215, _mut88216, _mut88217, _mut88218) || ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88219, _mut88220, _mut88221, _mut88222, _mut88223)) : (ROR_equals(sign, -1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88214, _mut88215, _mut88216, _mut88217, _mut88218) && ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88219, _mut88220, _mut88221, _mut88222, _mut88223)));
                break;
        }
        if (inc) {
            // increment if necessary
            int rh = 1;
            for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88241, _mut88242, _mut88243, _mut88244, _mut88245); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.round_1424");
                final int r = AOR_plus(mant[i], rh, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88225, _mut88226, _mut88227, _mut88228);
                rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88229, _mut88230, _mut88231, _mut88232);
                mant[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88233, _mut88234, _mut88235, _mut88236), "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88237, _mut88238, _mut88239, _mut88240);
            }
            if (ROR_not_equals(rh, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88246, _mut88247, _mut88248, _mut88249, _mut88250)) {
                shiftRight();
                mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88251, _mut88252, _mut88253, _mut88254)] = rh;
            }
        }
        // check for exceptional cases and raise signals if necessary
        if (ROR_less(exp, MIN_EXP, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88255, _mut88256, _mut88257, _mut88258, _mut88259)) {
            // Gradual Underflow
            field.setIEEEFlagsBits(DfpField.FLAG_UNDERFLOW);
            return DfpField.FLAG_UNDERFLOW;
        }
        if (ROR_greater(exp, MAX_EXP, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88260, _mut88261, _mut88262, _mut88263, _mut88264)) {
            // Overflow
            field.setIEEEFlagsBits(DfpField.FLAG_OVERFLOW);
            return DfpField.FLAG_OVERFLOW;
        }
        if (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.Dfp.round_1424", _mut88265, _mut88266, _mut88267, _mut88268, _mut88269)) {
            // Inexact
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            return DfpField.FLAG_INEXACT;
        }
        return 0;
    }

    /**
     * Multiply this by x.
     * @param x multiplicand
     * @return product of this and x
     */
    public Dfp multiply(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1503");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88270, _mut88271, _mut88272, _mut88273, _mut88274)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, MULTIPLY_TRAP, x, result);
        }
        Dfp result = newInstance(getZero());
        /* handle special cases */
        if ((_mut88285 ? (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88275, _mut88276, _mut88277, _mut88278, _mut88279) && ROR_not_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88280, _mut88281, _mut88282, _mut88283, _mut88284)) : (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88275, _mut88276, _mut88277, _mut88278, _mut88279) || ROR_not_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88280, _mut88281, _mut88282, _mut88283, _mut88284)))) {
            if (isNaN()) {
                return this;
            }
            if (x.isNaN()) {
                return x;
            }
            if ((_mut88306 ? ((_mut88296 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88286, _mut88287, _mut88288, _mut88289, _mut88290) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88291, _mut88292, _mut88293, _mut88294, _mut88295)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88286, _mut88287, _mut88288, _mut88289, _mut88290) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88291, _mut88292, _mut88293, _mut88294, _mut88295))) || ROR_not_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88297, _mut88298, _mut88299, _mut88300)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88301, _mut88302, _mut88303, _mut88304, _mut88305)) : ((_mut88296 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88286, _mut88287, _mut88288, _mut88289, _mut88290) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88291, _mut88292, _mut88293, _mut88294, _mut88295)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88286, _mut88287, _mut88288, _mut88289, _mut88290) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88291, _mut88292, _mut88293, _mut88294, _mut88295))) && ROR_not_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88297, _mut88298, _mut88299, _mut88300)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88301, _mut88302, _mut88303, _mut88304, _mut88305)))) {
                result = newInstance(this);
                result.sign = (byte) (AOR_multiply(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88307, _mut88308, _mut88309, _mut88310));
                return result;
            }
            if ((_mut88331 ? ((_mut88321 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88311, _mut88312, _mut88313, _mut88314, _mut88315) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88316, _mut88317, _mut88318, _mut88319, _mut88320)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88311, _mut88312, _mut88313, _mut88314, _mut88315) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88316, _mut88317, _mut88318, _mut88319, _mut88320))) || ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88322, _mut88323, _mut88324, _mut88325)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88326, _mut88327, _mut88328, _mut88329, _mut88330)) : ((_mut88321 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88311, _mut88312, _mut88313, _mut88314, _mut88315) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88316, _mut88317, _mut88318, _mut88319, _mut88320)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88311, _mut88312, _mut88313, _mut88314, _mut88315) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88316, _mut88317, _mut88318, _mut88319, _mut88320))) && ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88322, _mut88323, _mut88324, _mut88325)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88326, _mut88327, _mut88328, _mut88329, _mut88330)))) {
                result = newInstance(x);
                result.sign = (byte) (AOR_multiply(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88332, _mut88333, _mut88334, _mut88335));
                return result;
            }
            if ((_mut88346 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88336, _mut88337, _mut88338, _mut88339, _mut88340) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88341, _mut88342, _mut88343, _mut88344, _mut88345)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88336, _mut88337, _mut88338, _mut88339, _mut88340) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88341, _mut88342, _mut88343, _mut88344, _mut88345)))) {
                result = newInstance(this);
                result.sign = (byte) (AOR_multiply(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88347, _mut88348, _mut88349, _mut88350));
                return result;
            }
            if ((_mut88393 ? (((_mut88371 ? ((_mut88361 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360))) || ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88362, _mut88363, _mut88364, _mut88365)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88366, _mut88367, _mut88368, _mut88369, _mut88370)) : ((_mut88361 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360))) && ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88362, _mut88363, _mut88364, _mut88365)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88366, _mut88367, _mut88368, _mut88369, _mut88370)))) && ((_mut88392 ? ((_mut88382 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381))) || ROR_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88383, _mut88384, _mut88385, _mut88386)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88387, _mut88388, _mut88389, _mut88390, _mut88391)) : ((_mut88382 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381))) && ROR_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88383, _mut88384, _mut88385, _mut88386)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88387, _mut88388, _mut88389, _mut88390, _mut88391))))) : (((_mut88371 ? ((_mut88361 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360))) || ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88362, _mut88363, _mut88364, _mut88365)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88366, _mut88367, _mut88368, _mut88369, _mut88370)) : ((_mut88361 ? (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360)) : (ROR_equals(x.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88351, _mut88352, _mut88353, _mut88354, _mut88355) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88356, _mut88357, _mut88358, _mut88359, _mut88360))) && ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88362, _mut88363, _mut88364, _mut88365)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88366, _mut88367, _mut88368, _mut88369, _mut88370)))) || ((_mut88392 ? ((_mut88382 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381))) || ROR_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88383, _mut88384, _mut88385, _mut88386)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88387, _mut88388, _mut88389, _mut88390, _mut88391)) : ((_mut88382 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) || ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88372, _mut88373, _mut88374, _mut88375, _mut88376) && ROR_equals(x.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88377, _mut88378, _mut88379, _mut88380, _mut88381))) && ROR_equals(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88383, _mut88384, _mut88385, _mut88386)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88387, _mut88388, _mut88389, _mut88390, _mut88391))))))) {
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                result = newInstance(getZero());
                result.nans = QNAN;
                result = dotrap(DfpField.FLAG_INVALID, MULTIPLY_TRAP, x, result);
                return result;
            }
        }
        // Big enough to hold even the largest result
        int[] product = new int[AOR_multiply(mant.length, 2, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88394, _mut88395, _mut88396, _mut88397)];
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88435, _mut88436, _mut88437, _mut88438, _mut88439); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1503");
            // acts as a carry
            int rh = 0;
            for (int j = 0; ROR_less(j, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88426, _mut88427, _mut88428, _mut88429, _mut88430); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1503");
                // multiply the 2 digits
                int r = AOR_multiply(mant[i], x.mant[j], "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88398, _mut88399, _mut88400, _mut88401);
                // add to the product digit with carry in
                r += AOR_plus(product[AOR_plus(i, j, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88402, _mut88403, _mut88404, _mut88405)], rh, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88406, _mut88407, _mut88408, _mut88409);
                rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88410, _mut88411, _mut88412, _mut88413);
                product[AOR_plus(i, j, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88414, _mut88415, _mut88416, _mut88417)] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88418, _mut88419, _mut88420, _mut88421), "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88422, _mut88423, _mut88424, _mut88425);
            }
            product[AOR_plus(i, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88431, _mut88432, _mut88433, _mut88434)] = rh;
        }
        // default, in case result is zero
        int md = AOR_minus(AOR_multiply(mant.length, 2, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88440, _mut88441, _mut88442, _mut88443), 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88444, _mut88445, _mut88446, _mut88447);
        for (int i = mant.length * 2 - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88453, _mut88454, _mut88455, _mut88456, _mut88457); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1503");
            if (ROR_not_equals(product[i], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88448, _mut88449, _mut88450, _mut88451, _mut88452)) {
                md = i;
                break;
            }
        }
        // Copy the digits into the result
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88470, _mut88471, _mut88472, _mut88473, _mut88474); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1503");
            result.mant[AOR_minus(AOR_minus(mant.length, i, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88458, _mut88459, _mut88460, _mut88461), 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88462, _mut88463, _mut88464, _mut88465)] = product[AOR_minus(md, i, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88466, _mut88467, _mut88468, _mut88469)];
        }
        // Fixup the exponent.
        result.exp = AOR_plus(AOR_minus(AOR_plus(AOR_plus(exp, x.exp, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88475, _mut88476, _mut88477, _mut88478), md, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88479, _mut88480, _mut88481, _mut88482), AOR_multiply(2, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88483, _mut88484, _mut88485, _mut88486), "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88487, _mut88488, _mut88489, _mut88490), 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88491, _mut88492, _mut88493, _mut88494);
        result.sign = (byte) ((ROR_equals(sign, x.sign, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88495, _mut88496, _mut88497, _mut88498, _mut88499)) ? 1 : -1);
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88500, _mut88501, _mut88502, _mut88503)], 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88504, _mut88505, _mut88506, _mut88507, _mut88508)) {
            // if result is zero, set exp to zero
            result.exp = 0;
        }
        final int excp;
        if (ROR_greater(md, (AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88509, _mut88510, _mut88511, _mut88512)), "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88513, _mut88514, _mut88515, _mut88516, _mut88517)) {
            excp = result.round(product[AOR_minus(md, mant.length, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88518, _mut88519, _mut88520, _mut88521)]);
        } else {
            // has no effect except to check status
            excp = result.round(0);
        }
        if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.multiply_1503", _mut88522, _mut88523, _mut88524, _mut88525, _mut88526)) {
            result = dotrap(excp, MULTIPLY_TRAP, x, result);
        }
        return result;
    }

    /**
     * Multiply this by a single digit x.
     * @param x multiplicand
     * @return product of this and x
     */
    public Dfp multiply(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiply_1609");
        if ((_mut88537 ? (ROR_greater_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiply_1609", _mut88527, _mut88528, _mut88529, _mut88530, _mut88531) || ROR_less(x, RADIX, "org.apache.commons.math3.dfp.Dfp.multiply_1609", _mut88532, _mut88533, _mut88534, _mut88535, _mut88536)) : (ROR_greater_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiply_1609", _mut88527, _mut88528, _mut88529, _mut88530, _mut88531) && ROR_less(x, RADIX, "org.apache.commons.math3.dfp.Dfp.multiply_1609", _mut88532, _mut88533, _mut88534, _mut88535, _mut88536)))) {
            return multiplyFast(x);
        } else {
            return multiply(newInstance(x));
        }
    }

    /**
     * Multiply this by a single digit 0&lt;=x&lt;radix.
     * There are speed advantages in this special case.
     * @param x multiplicand
     * @return product of this and x
     */
    private Dfp multiplyFast(final int x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiplyFast_1622");
        Dfp result = newInstance(this);
        /* handle special cases */
        if (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88538, _mut88539, _mut88540, _mut88541, _mut88542)) {
            if (isNaN()) {
                return this;
            }
            if ((_mut88553 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88543, _mut88544, _mut88545, _mut88546, _mut88547) || ROR_not_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88548, _mut88549, _mut88550, _mut88551, _mut88552)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88543, _mut88544, _mut88545, _mut88546, _mut88547) && ROR_not_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88548, _mut88549, _mut88550, _mut88551, _mut88552)))) {
                result = newInstance(this);
                return result;
            }
            if ((_mut88564 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88554, _mut88555, _mut88556, _mut88557, _mut88558) || ROR_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88559, _mut88560, _mut88561, _mut88562, _mut88563)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88554, _mut88555, _mut88556, _mut88557, _mut88558) && ROR_equals(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88559, _mut88560, _mut88561, _mut88562, _mut88563)))) {
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                result = newInstance(getZero());
                result.nans = QNAN;
                result = dotrap(DfpField.FLAG_INVALID, MULTIPLY_TRAP, newInstance(getZero()), result);
                return result;
            }
        }
        /* range check x */
        if ((_mut88575 ? (ROR_less(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88565, _mut88566, _mut88567, _mut88568, _mut88569) && ROR_greater_equals(x, RADIX, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88570, _mut88571, _mut88572, _mut88573, _mut88574)) : (ROR_less(x, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88565, _mut88566, _mut88567, _mut88568, _mut88569) || ROR_greater_equals(x, RADIX, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88570, _mut88571, _mut88572, _mut88573, _mut88574)))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            result = newInstance(getZero());
            result.nans = QNAN;
            result = dotrap(DfpField.FLAG_INVALID, MULTIPLY_TRAP, result, result);
            return result;
        }
        int rh = 0;
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88596, _mut88597, _mut88598, _mut88599, _mut88600); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.multiplyFast_1622");
            final int r = AOR_plus(AOR_multiply(mant[i], x, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88576, _mut88577, _mut88578, _mut88579), rh, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88580, _mut88581, _mut88582, _mut88583);
            rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88584, _mut88585, _mut88586, _mut88587);
            result.mant[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88588, _mut88589, _mut88590, _mut88591), "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88592, _mut88593, _mut88594, _mut88595);
        }
        int lostdigit = 0;
        if (ROR_not_equals(rh, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88601, _mut88602, _mut88603, _mut88604, _mut88605)) {
            lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88606, _mut88607, _mut88608, _mut88609)] = rh;
        }
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88610, _mut88611, _mut88612, _mut88613)], 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88614, _mut88615, _mut88616, _mut88617, _mut88618)) {
            // if result is zero, set exp to zero
            result.exp = 0;
        }
        final int excp = result.round(lostdigit);
        if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.multiplyFast_1622", _mut88619, _mut88620, _mut88621, _mut88622, _mut88623)) {
            result = dotrap(excp, MULTIPLY_TRAP, result, result);
        }
        return result;
    }

    /**
     * Divide this by divisor.
     * @param divisor divisor
     * @return quotient of this by divisor
     */
    public Dfp divide(Dfp divisor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
        // current status of the dividend
        int[] dividend;
        // quotient
        int[] quotient;
        // remainder
        int[] remainder;
        // current quotient digit we're working with
        int qd;
        // number of significant quotient digits we have
        int nsqd;
        // trial quotient digit
        int trial = 0;
        // minimum adjustment
        int minadj;
        // Flag to indicate a good trail digit
        boolean trialgood;
        // most sig digit in result
        int md = 0;
        // exceptions
        int excp;
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), divisor.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88624, _mut88625, _mut88626, _mut88627, _mut88628)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, DIVIDE_TRAP, divisor, result);
        }
        Dfp result = newInstance(getZero());
        /* handle special cases */
        if ((_mut88639 ? (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88629, _mut88630, _mut88631, _mut88632, _mut88633) && ROR_not_equals(divisor.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88634, _mut88635, _mut88636, _mut88637, _mut88638)) : (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88629, _mut88630, _mut88631, _mut88632, _mut88633) || ROR_not_equals(divisor.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88634, _mut88635, _mut88636, _mut88637, _mut88638)))) {
            if (isNaN()) {
                return this;
            }
            if (divisor.isNaN()) {
                return divisor;
            }
            if ((_mut88650 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88640, _mut88641, _mut88642, _mut88643, _mut88644) || ROR_equals(divisor.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88645, _mut88646, _mut88647, _mut88648, _mut88649)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88640, _mut88641, _mut88642, _mut88643, _mut88644) && ROR_equals(divisor.nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88645, _mut88646, _mut88647, _mut88648, _mut88649)))) {
                result = newInstance(this);
                result.sign = (byte) (AOR_multiply(sign, divisor.sign, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88651, _mut88652, _mut88653, _mut88654));
                return result;
            }
            if ((_mut88665 ? (ROR_equals(divisor.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88655, _mut88656, _mut88657, _mut88658, _mut88659) || ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88660, _mut88661, _mut88662, _mut88663, _mut88664)) : (ROR_equals(divisor.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88655, _mut88656, _mut88657, _mut88658, _mut88659) && ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88660, _mut88661, _mut88662, _mut88663, _mut88664)))) {
                result = newInstance(getZero());
                result.sign = (byte) (AOR_multiply(sign, divisor.sign, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88666, _mut88667, _mut88668, _mut88669));
                return result;
            }
            if ((_mut88680 ? (ROR_equals(divisor.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88670, _mut88671, _mut88672, _mut88673, _mut88674) || ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88675, _mut88676, _mut88677, _mut88678, _mut88679)) : (ROR_equals(divisor.nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88670, _mut88671, _mut88672, _mut88673, _mut88674) && ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88675, _mut88676, _mut88677, _mut88678, _mut88679)))) {
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                result = newInstance(getZero());
                result.nans = QNAN;
                result = dotrap(DfpField.FLAG_INVALID, DIVIDE_TRAP, divisor, result);
                return result;
            }
        }
        /* Test for divide by zero */
        if (ROR_equals(divisor.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88681, _mut88682, _mut88683, _mut88684)], 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88685, _mut88686, _mut88687, _mut88688, _mut88689)) {
            field.setIEEEFlagsBits(DfpField.FLAG_DIV_ZERO);
            result = newInstance(getZero());
            result.sign = (byte) (AOR_multiply(sign, divisor.sign, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88690, _mut88691, _mut88692, _mut88693));
            result.nans = INFINITE;
            result = dotrap(DfpField.FLAG_DIV_ZERO, DIVIDE_TRAP, divisor, result);
            return result;
        }
        // one extra digit needed
        dividend = new int[AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88694, _mut88695, _mut88696, _mut88697)];
        // two extra digits needed 1 for overflow, 1 for rounding
        quotient = new int[AOR_plus(mant.length, 2, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88698, _mut88699, _mut88700, _mut88701)];
        // one extra digit needed
        remainder = new int[AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88702, _mut88703, _mut88704, _mut88705)];
        dividend[mant.length] = 0;
        quotient[mant.length] = 0;
        quotient[AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88706, _mut88707, _mut88708, _mut88709)] = 0;
        remainder[mant.length] = 0;
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88710, _mut88711, _mut88712, _mut88713, _mut88714); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
            dividend[i] = mant[i];
            quotient[i] = 0;
            remainder[i] = 0;
        }
        /* outer loop.  Once per quotient digit */
        nsqd = 0;
        for (qd = mant.length + 1; ROR_greater_equals(qd, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88923, _mut88924, _mut88925, _mut88926, _mut88927); qd--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
            // r =  most sig 2 digits of dividend
            final int divMsb = AOR_plus(AOR_multiply(dividend[mant.length], RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88715, _mut88716, _mut88717, _mut88718), dividend[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88719, _mut88720, _mut88721, _mut88722)], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88723, _mut88724, _mut88725, _mut88726);
            int min = AOR_divide(divMsb, (AOR_plus(divisor.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88727, _mut88728, _mut88729, _mut88730)], 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88731, _mut88732, _mut88733, _mut88734)), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88735, _mut88736, _mut88737, _mut88738);
            int max = AOR_divide((AOR_plus(divMsb, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88739, _mut88740, _mut88741, _mut88742)), divisor.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88743, _mut88744, _mut88745, _mut88746)], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88747, _mut88748, _mut88749, _mut88750);
            trialgood = false;
            while (!trialgood) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
                // try the mean
                trial = AOR_divide((AOR_plus(min, max, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88751, _mut88752, _mut88753, _mut88754)), 2, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88755, _mut88756, _mut88757, _mut88758);
                /* Multiply by divisor and store as remainder */
                int rh = 0;
                for (int i = 0; ROR_less(i, AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88784, _mut88785, _mut88786, _mut88787), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88788, _mut88789, _mut88790, _mut88791, _mut88792); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
                    int dm = (ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88759, _mut88760, _mut88761, _mut88762, _mut88763)) ? divisor.mant[i] : 0;
                    final int r = AOR_plus((AOR_multiply(dm, trial, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88764, _mut88765, _mut88766, _mut88767)), rh, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88768, _mut88769, _mut88770, _mut88771);
                    rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88772, _mut88773, _mut88774, _mut88775);
                    remainder[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88776, _mut88777, _mut88778, _mut88779), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88780, _mut88781, _mut88782, _mut88783);
                }
                // carry in to aid the subtraction
                rh = 1;
                for (int i = 0; ROR_less(i, AOR_plus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88821, _mut88822, _mut88823, _mut88824), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88825, _mut88826, _mut88827, _mut88828, _mut88829); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
                    final int r = AOR_plus(AOR_plus((AOR_minus((AOR_minus(RADIX, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88793, _mut88794, _mut88795, _mut88796)), remainder[i], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88797, _mut88798, _mut88799, _mut88800)), dividend[i], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88801, _mut88802, _mut88803, _mut88804), rh, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88805, _mut88806, _mut88807, _mut88808);
                    rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88809, _mut88810, _mut88811, _mut88812);
                    remainder[i] = AOR_minus(r, AOR_multiply(rh, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88813, _mut88814, _mut88815, _mut88816), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88817, _mut88818, _mut88819, _mut88820);
                }
                /* Lets analyze what we have here */
                if (ROR_equals(rh, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88830, _mut88831, _mut88832, _mut88833, _mut88834)) {
                    // trial is too big -- negative remainder
                    max = AOR_minus(trial, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88835, _mut88836, _mut88837, _mut88838);
                    continue;
                }
                /* find out how far off the remainder is telling us we are */
                minadj = AOR_plus((AOR_multiply(remainder[mant.length], RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88839, _mut88840, _mut88841, _mut88842)), remainder[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88843, _mut88844, _mut88845, _mut88846)], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88847, _mut88848, _mut88849, _mut88850);
                minadj /= AOR_plus(divisor.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88851, _mut88852, _mut88853, _mut88854)], 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88855, _mut88856, _mut88857, _mut88858);
                if (ROR_greater_equals(minadj, 2, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88859, _mut88860, _mut88861, _mut88862, _mut88863)) {
                    // update the minimum
                    min = AOR_plus(trial, minadj, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88864, _mut88865, _mut88866, _mut88867);
                    continue;
                }
                // assume false
                trialgood = false;
                for (int i = mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88878, _mut88879, _mut88880, _mut88881, _mut88882); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
                    if (ROR_greater(divisor.mant[i], remainder[i], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88868, _mut88869, _mut88870, _mut88871, _mut88872)) {
                        trialgood = true;
                    }
                    if (ROR_less(divisor.mant[i], remainder[i], "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88873, _mut88874, _mut88875, _mut88876, _mut88877)) {
                        break;
                    }
                }
                if (ROR_not_equals(remainder[mant.length], 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88883, _mut88884, _mut88885, _mut88886, _mut88887)) {
                    trialgood = false;
                }
                if (trialgood == false) {
                    min = AOR_plus(trial, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88888, _mut88889, _mut88890, _mut88891);
                }
            }
            /* Great we have a digit! */
            quotient[qd] = trial;
            if ((_mut88902 ? (ROR_not_equals(trial, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88892, _mut88893, _mut88894, _mut88895, _mut88896) && ROR_not_equals(nsqd, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88897, _mut88898, _mut88899, _mut88900, _mut88901)) : (ROR_not_equals(trial, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88892, _mut88893, _mut88894, _mut88895, _mut88896) || ROR_not_equals(nsqd, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88897, _mut88898, _mut88899, _mut88900, _mut88901)))) {
                nsqd++;
            }
            if ((_mut88908 ? (field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN || ROR_equals(nsqd, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88903, _mut88904, _mut88905, _mut88906, _mut88907)) : (field.getRoundingMode() == DfpField.RoundingMode.ROUND_DOWN && ROR_equals(nsqd, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88903, _mut88904, _mut88905, _mut88906, _mut88907)))) {
                // We have enough for this mode
                break;
            }
            if (ROR_greater(nsqd, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88909, _mut88910, _mut88911, _mut88912, _mut88913)) {
                // We have enough digits
                break;
            }
            /* move the remainder into the dividend while left shifting */
            dividend[0] = 0;
            for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88918, _mut88919, _mut88920, _mut88921, _mut88922); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
                dividend[AOR_plus(i, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88914, _mut88915, _mut88916, _mut88917)] = remainder[i];
            }
        }
        // default
        md = mant.length;
        for (int i = mant.length + 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88933, _mut88934, _mut88935, _mut88936, _mut88937); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
            if (ROR_not_equals(quotient[i], 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88928, _mut88929, _mut88930, _mut88931, _mut88932)) {
                md = i;
                break;
            }
        }
        /* Copy the digits into the result */
        for (int i = 0; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88950, _mut88951, _mut88952, _mut88953, _mut88954); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1684");
            result.mant[AOR_minus(AOR_minus(mant.length, i, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88938, _mut88939, _mut88940, _mut88941), 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88942, _mut88943, _mut88944, _mut88945)] = quotient[AOR_minus(md, i, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88946, _mut88947, _mut88948, _mut88949)];
        }
        /* Fixup the exponent. */
        result.exp = AOR_minus(AOR_plus(AOR_minus(exp, divisor.exp, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88955, _mut88956, _mut88957, _mut88958), md, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88959, _mut88960, _mut88961, _mut88962), mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88963, _mut88964, _mut88965, _mut88966);
        result.sign = (byte) ((ROR_equals(sign, divisor.sign, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88967, _mut88968, _mut88969, _mut88970, _mut88971)) ? 1 : -1);
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88972, _mut88973, _mut88974, _mut88975)], 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88976, _mut88977, _mut88978, _mut88979, _mut88980)) {
            // if result is zero, set exp to zero
            result.exp = 0;
        }
        if (ROR_greater(md, (AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88981, _mut88982, _mut88983, _mut88984)), "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88985, _mut88986, _mut88987, _mut88988, _mut88989)) {
            excp = result.round(quotient[AOR_minus(md, mant.length, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88990, _mut88991, _mut88992, _mut88993)]);
        } else {
            excp = result.round(0);
        }
        if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.divide_1684", _mut88994, _mut88995, _mut88996, _mut88997, _mut88998)) {
            result = dotrap(excp, DIVIDE_TRAP, divisor, result);
        }
        return result;
    }

    /**
     * Divide by a single digit less than radix.
     *  Special case, so there are speed advantages. 0 &lt;= divisor &lt; radix
     * @param divisor divisor
     * @return quotient of this by divisor
     */
    public Dfp divide(int divisor) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1899");
        // Handle special cases
        if (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut88999, _mut89000, _mut89001, _mut89002, _mut89003)) {
            if (isNaN()) {
                return this;
            }
            if (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89004, _mut89005, _mut89006, _mut89007, _mut89008)) {
                return newInstance(this);
            }
        }
        // Test for divide by zero
        if (ROR_equals(divisor, 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89009, _mut89010, _mut89011, _mut89012, _mut89013)) {
            field.setIEEEFlagsBits(DfpField.FLAG_DIV_ZERO);
            Dfp result = newInstance(getZero());
            result.sign = sign;
            result.nans = INFINITE;
            result = dotrap(DfpField.FLAG_DIV_ZERO, DIVIDE_TRAP, getZero(), result);
            return result;
        }
        // range check divisor
        if ((_mut89024 ? (ROR_less(divisor, 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89014, _mut89015, _mut89016, _mut89017, _mut89018) && ROR_greater_equals(divisor, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89019, _mut89020, _mut89021, _mut89022, _mut89023)) : (ROR_less(divisor, 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89014, _mut89015, _mut89016, _mut89017, _mut89018) || ROR_greater_equals(divisor, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89019, _mut89020, _mut89021, _mut89022, _mut89023)))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            Dfp result = newInstance(getZero());
            result.nans = QNAN;
            result = dotrap(DfpField.FLAG_INVALID, DIVIDE_TRAP, result, result);
            return result;
        }
        Dfp result = newInstance(this);
        int rl = 0;
        for (int i = mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89045, _mut89046, _mut89047, _mut89048, _mut89049); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.divide_1899");
            final int r = AOR_plus(AOR_multiply(rl, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89025, _mut89026, _mut89027, _mut89028), result.mant[i], "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89029, _mut89030, _mut89031, _mut89032);
            final int rh = AOR_divide(r, divisor, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89033, _mut89034, _mut89035, _mut89036);
            rl = AOR_minus(r, AOR_multiply(rh, divisor, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89037, _mut89038, _mut89039, _mut89040), "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89041, _mut89042, _mut89043, _mut89044);
            result.mant[i] = rh;
        }
        if (ROR_equals(result.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89050, _mut89051, _mut89052, _mut89053)], 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89054, _mut89055, _mut89056, _mut89057, _mut89058)) {
            // normalize
            result.shiftLeft();
            // compute the next digit and put it in
            final int r = AOR_multiply(rl, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89059, _mut89060, _mut89061, _mut89062);
            final int rh = AOR_divide(r, divisor, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89063, _mut89064, _mut89065, _mut89066);
            rl = AOR_minus(r, AOR_multiply(rh, divisor, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89067, _mut89068, _mut89069, _mut89070), "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89071, _mut89072, _mut89073, _mut89074);
            result.mant[0] = rh;
        }
        // do the rounding
        final int excp = result.round(AOR_divide(AOR_multiply(rl, RADIX, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89075, _mut89076, _mut89077, _mut89078), divisor, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89079, _mut89080, _mut89081, _mut89082));
        if (ROR_not_equals(excp, 0, "org.apache.commons.math3.dfp.Dfp.divide_1899", _mut89083, _mut89084, _mut89085, _mut89086, _mut89087)) {
            result = dotrap(excp, DIVIDE_TRAP, result, result);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Dfp reciprocal() {
        return field.getOne().divide(this);
    }

    /**
     * Compute the square root.
     * @return square root of the instance
     * @since 3.2
     */
    public Dfp sqrt() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.sqrt_1968");
        // check for unusual cases
        if ((_mut89102 ? (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89088, _mut89089, _mut89090, _mut89091, _mut89092) || ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89093, _mut89094, _mut89095, _mut89096)], 0, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89097, _mut89098, _mut89099, _mut89100, _mut89101)) : (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89088, _mut89089, _mut89090, _mut89091, _mut89092) && ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89093, _mut89094, _mut89095, _mut89096)], 0, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89097, _mut89098, _mut89099, _mut89100, _mut89101)))) {
            // if zero
            return newInstance(this);
        }
        if (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89103, _mut89104, _mut89105, _mut89106, _mut89107)) {
            if ((_mut89118 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89108, _mut89109, _mut89110, _mut89111, _mut89112) || ROR_equals(sign, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89113, _mut89114, _mut89115, _mut89116, _mut89117)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89108, _mut89109, _mut89110, _mut89111, _mut89112) && ROR_equals(sign, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89113, _mut89114, _mut89115, _mut89116, _mut89117)))) {
                // if positive infinity
                return newInstance(this);
            }
            if (ROR_equals(nans, QNAN, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89119, _mut89120, _mut89121, _mut89122, _mut89123)) {
                return newInstance(this);
            }
            if (ROR_equals(nans, SNAN, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89124, _mut89125, _mut89126, _mut89127, _mut89128)) {
                Dfp result;
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                result = newInstance(this);
                result = dotrap(DfpField.FLAG_INVALID, SQRT_TRAP, null, result);
                return result;
            }
        }
        if (ROR_equals(sign, -1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89129, _mut89130, _mut89131, _mut89132, _mut89133)) {
            // if negative
            Dfp result;
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            result = newInstance(this);
            result.nans = QNAN;
            result = dotrap(DfpField.FLAG_INVALID, SQRT_TRAP, null, result);
            return result;
        }
        Dfp x = newInstance(this);
        /* Lets make a reasonable guess as to the size of the square root */
        if ((_mut89144 ? (ROR_less(x.exp, -1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89134, _mut89135, _mut89136, _mut89137, _mut89138) && ROR_greater(x.exp, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89139, _mut89140, _mut89141, _mut89142, _mut89143)) : (ROR_less(x.exp, -1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89134, _mut89135, _mut89136, _mut89137, _mut89138) || ROR_greater(x.exp, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89139, _mut89140, _mut89141, _mut89142, _mut89143)))) {
            x.exp = AOR_divide(this.exp, 2, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89145, _mut89146, _mut89147, _mut89148);
        }
        /* Coarsely estimate the mantissa */
        switch(AOR_divide(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89177, _mut89178, _mut89179, _mut89180)], 2000, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89181, _mut89182, _mut89183, _mut89184)) {
            case 0:
                x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89149, _mut89150, _mut89151, _mut89152)] = AOR_plus(AOR_divide(x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89153, _mut89154, _mut89155, _mut89156)], 2, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89157, _mut89158, _mut89159, _mut89160), 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89161, _mut89162, _mut89163, _mut89164);
                break;
            case 2:
                x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89165, _mut89166, _mut89167, _mut89168)] = 1500;
                break;
            case 3:
                x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89169, _mut89170, _mut89171, _mut89172)] = 2200;
                break;
            default:
                x.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89173, _mut89174, _mut89175, _mut89176)] = 3000;
        }
        Dfp dx = newInstance(x);
        Dfp px = getZero();
        Dfp ppx = getZero();
        while (x.unequal(px)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.sqrt_1968");
            dx = newInstance(x);
            dx.sign = -1;
            dx = dx.add(this.divide(x));
            dx = dx.divide(2);
            ppx = px;
            px = x;
            x = x.add(dx);
            if (x.equals(ppx)) {
                // alternating between two values
                break;
            }
            // is a sufficient test since dx is normalized
            if (ROR_equals(dx.mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89185, _mut89186, _mut89187, _mut89188)], 0, "org.apache.commons.math3.dfp.Dfp.sqrt_1968", _mut89189, _mut89190, _mut89191, _mut89192, _mut89193)) {
                break;
            }
        }
        return x;
    }

    /**
     * Get a string representation of the instance.
     * @return string representation of the instance
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.toString_2064");
        if (ROR_not_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89194, _mut89195, _mut89196, _mut89197, _mut89198)) {
            // if non-finite exceptional cases
            if (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89199, _mut89200, _mut89201, _mut89202, _mut89203)) {
                return (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89204, _mut89205, _mut89206, _mut89207, _mut89208)) ? NEG_INFINITY_STRING : POS_INFINITY_STRING;
            } else {
                return NAN_STRING;
            }
        }
        if ((_mut89219 ? (ROR_greater(exp, mant.length, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89209, _mut89210, _mut89211, _mut89212, _mut89213) && ROR_less(exp, -1, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89214, _mut89215, _mut89216, _mut89217, _mut89218)) : (ROR_greater(exp, mant.length, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89209, _mut89210, _mut89211, _mut89212, _mut89213) || ROR_less(exp, -1, "org.apache.commons.math3.dfp.Dfp.toString_2064", _mut89214, _mut89215, _mut89216, _mut89217, _mut89218)))) {
            return dfp2sci();
        }
        return dfp2string();
    }

    /**
     * Convert an instance to a string using scientific notation.
     * @return string representation of the instance in scientific notation
     */
    protected String dfp2sci() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
        char[] rawdigits = new char[AOR_multiply(mant.length, 4, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89220, _mut89221, _mut89222, _mut89223)];
        char[] outputbuffer = new char[AOR_plus(AOR_multiply(mant.length, 4, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89224, _mut89225, _mut89226, _mut89227), 20, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89228, _mut89229, _mut89230, _mut89231)];
        int p;
        int q;
        int e;
        int ae;
        int shf;
        // Get all the digits
        p = 0;
        for (int i = mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89272, _mut89273, _mut89274, _mut89275, _mut89276); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
            rawdigits[p++] = (char) (AOR_plus((AOR_divide(mant[i], 1000, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89232, _mut89233, _mut89234, _mut89235)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89236, _mut89237, _mut89238, _mut89239));
            rawdigits[p++] = (char) (AOR_plus((AOR_remainder((AOR_divide(mant[i], 100, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89240, _mut89241, _mut89242, _mut89243)), 10, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89244, _mut89245, _mut89246, _mut89247)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89248, _mut89249, _mut89250, _mut89251));
            rawdigits[p++] = (char) (AOR_plus((AOR_remainder((AOR_divide(mant[i], 10, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89252, _mut89253, _mut89254, _mut89255)), 10, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89256, _mut89257, _mut89258, _mut89259)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89260, _mut89261, _mut89262, _mut89263));
            rawdigits[p++] = (char) (AOR_plus((AOR_remainder((mant[i]), 10, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89264, _mut89265, _mut89266, _mut89267)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89268, _mut89269, _mut89270, _mut89271));
        }
        // Find the first non-zero one
        for (p = 0; ROR_less(p, rawdigits.length, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89277, _mut89278, _mut89279, _mut89280, _mut89281); p++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
            if (rawdigits[p] != '0') {
                break;
            }
        }
        shf = p;
        // Now do the conversion
        q = 0;
        if (ROR_equals(sign, -1, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89282, _mut89283, _mut89284, _mut89285, _mut89286)) {
            outputbuffer[q++] = '-';
        }
        if (ROR_not_equals(p, rawdigits.length, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89287, _mut89288, _mut89289, _mut89290, _mut89291)) {
            // there are non zero digits...
            outputbuffer[q++] = rawdigits[p++];
            outputbuffer[q++] = '.';
            while (ROR_less(p, rawdigits.length, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89292, _mut89293, _mut89294, _mut89295, _mut89296)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
                outputbuffer[q++] = rawdigits[p++];
            }
        } else {
            outputbuffer[q++] = '0';
            outputbuffer[q++] = '.';
            outputbuffer[q++] = '0';
            outputbuffer[q++] = 'e';
            outputbuffer[q++] = '0';
            return new String(outputbuffer, 0, 5);
        }
        outputbuffer[q++] = 'e';
        e = AOR_minus(AOR_minus(AOR_multiply(exp, 4, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89297, _mut89298, _mut89299, _mut89300), shf, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89301, _mut89302, _mut89303, _mut89304), 1, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89305, _mut89306, _mut89307, _mut89308);
        ae = e;
        if (ROR_less(e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89309, _mut89310, _mut89311, _mut89312, _mut89313)) {
            ae = -e;
        }
        // Find the largest p such that p < e
        for (p = 1000000000; ROR_greater(p, ae, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89314, _mut89315, _mut89316, _mut89317, _mut89318); p /= 10) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
        }
        if (ROR_less(e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89319, _mut89320, _mut89321, _mut89322, _mut89323)) {
            outputbuffer[q++] = '-';
        }
        while (ROR_greater(p, 0, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89332, _mut89333, _mut89334, _mut89335, _mut89336)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2sci_2086");
            outputbuffer[q++] = (char) (AOR_plus(AOR_divide(ae, p, "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89324, _mut89325, _mut89326, _mut89327), '0', "org.apache.commons.math3.dfp.Dfp.dfp2sci_2086", _mut89328, _mut89329, _mut89330, _mut89331));
            ae %= p;
            p /= 10;
        }
        return new String(outputbuffer, 0, q);
    }

    /**
     * Convert an instance to a string using normal notation.
     * @return string representation of the instance in normal notation
     */
    protected String dfp2string() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
        char[] buffer = new char[AOR_plus(AOR_multiply(mant.length, 4, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89337, _mut89338, _mut89339, _mut89340), 20, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89341, _mut89342, _mut89343, _mut89344)];
        int p = 1;
        int q;
        int e = exp;
        boolean pointInserted = false;
        buffer[0] = ' ';
        if (ROR_less_equals(e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89345, _mut89346, _mut89347, _mut89348, _mut89349)) {
            buffer[p++] = '0';
            buffer[p++] = '.';
            pointInserted = true;
        }
        while (ROR_less(e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89350, _mut89351, _mut89352, _mut89353, _mut89354)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
            buffer[p++] = '0';
            buffer[p++] = '0';
            buffer[p++] = '0';
            buffer[p++] = '0';
            e++;
        }
        for (int i = mant.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89400, _mut89401, _mut89402, _mut89403, _mut89404); i--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
            buffer[p++] = (char) (AOR_plus((AOR_divide(mant[i], 1000, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89355, _mut89356, _mut89357, _mut89358)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89359, _mut89360, _mut89361, _mut89362));
            buffer[p++] = (char) (AOR_plus((AOR_remainder((AOR_divide(mant[i], 100, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89363, _mut89364, _mut89365, _mut89366)), 10, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89367, _mut89368, _mut89369, _mut89370)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89371, _mut89372, _mut89373, _mut89374));
            buffer[p++] = (char) (AOR_plus((AOR_remainder((AOR_divide(mant[i], 10, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89375, _mut89376, _mut89377, _mut89378)), 10, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89379, _mut89380, _mut89381, _mut89382)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89383, _mut89384, _mut89385, _mut89386));
            buffer[p++] = (char) (AOR_plus((AOR_remainder((mant[i]), 10, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89387, _mut89388, _mut89389, _mut89390)), '0', "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89391, _mut89392, _mut89393, _mut89394));
            if (ROR_equals(--e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89395, _mut89396, _mut89397, _mut89398, _mut89399)) {
                buffer[p++] = '.';
                pointInserted = true;
            }
        }
        while (ROR_greater(e, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89405, _mut89406, _mut89407, _mut89408, _mut89409)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
            buffer[p++] = '0';
            buffer[p++] = '0';
            buffer[p++] = '0';
            buffer[p++] = '0';
            e--;
        }
        if (!pointInserted) {
            // Ensure we have a radix point!
            buffer[p++] = '.';
        }
        // Suppress leading zeros
        q = 1;
        while (buffer[q] == '0') {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
            q++;
        }
        if (buffer[q] == '.') {
            q--;
        }
        // Suppress trailing zeros
        while (buffer[AOR_minus(p, 1, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89410, _mut89411, _mut89412, _mut89413)] == '0') {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dfp2string_2167");
            p--;
        }
        // Insert sign
        if (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89414, _mut89415, _mut89416, _mut89417, _mut89418)) {
            buffer[--q] = '-';
        }
        return new String(buffer, q, AOR_minus(p, q, "org.apache.commons.math3.dfp.Dfp.dfp2string_2167", _mut89419, _mut89420, _mut89421, _mut89422));
    }

    /**
     * Raises a trap.  This does not set the corresponding flag however.
     *  @param type the trap type
     *  @param what - name of routine trap occurred in
     *  @param oper - input operator to function
     *  @param result - the result computed prior to the trap
     *  @return The suggested return value from the trap handler
     */
    public Dfp dotrap(int type, String what, Dfp oper, Dfp result) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.dotrap_2244");
        Dfp def = result;
        switch(type) {
            case DfpField.FLAG_INVALID:
                def = newInstance(getZero());
                def.sign = result.sign;
                def.nans = QNAN;
                break;
            case DfpField.FLAG_DIV_ZERO:
                if ((_mut89437 ? (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89423, _mut89424, _mut89425, _mut89426, _mut89427) || ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89428, _mut89429, _mut89430, _mut89431)], 0, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89432, _mut89433, _mut89434, _mut89435, _mut89436)) : (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89423, _mut89424, _mut89425, _mut89426, _mut89427) && ROR_not_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89428, _mut89429, _mut89430, _mut89431)], 0, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89432, _mut89433, _mut89434, _mut89435, _mut89436)))) {
                    // normal case, we are finite, non-zero
                    def = newInstance(getZero());
                    def.sign = (byte) (AOR_multiply(sign, oper.sign, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89438, _mut89439, _mut89440, _mut89441));
                    def.nans = INFINITE;
                }
                if ((_mut89456 ? (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89442, _mut89443, _mut89444, _mut89445, _mut89446) || ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89447, _mut89448, _mut89449, _mut89450)], 0, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89451, _mut89452, _mut89453, _mut89454, _mut89455)) : (ROR_equals(nans, FINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89442, _mut89443, _mut89444, _mut89445, _mut89446) && ROR_equals(mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89447, _mut89448, _mut89449, _mut89450)], 0, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89451, _mut89452, _mut89453, _mut89454, _mut89455)))) {
                    // 0/0
                    def = newInstance(getZero());
                    def.nans = QNAN;
                }
                if ((_mut89467 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89457, _mut89458, _mut89459, _mut89460, _mut89461) && ROR_equals(nans, QNAN, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89462, _mut89463, _mut89464, _mut89465, _mut89466)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89457, _mut89458, _mut89459, _mut89460, _mut89461) || ROR_equals(nans, QNAN, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89462, _mut89463, _mut89464, _mut89465, _mut89466)))) {
                    def = newInstance(getZero());
                    def.nans = QNAN;
                }
                if ((_mut89478 ? (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89468, _mut89469, _mut89470, _mut89471, _mut89472) && ROR_equals(nans, SNAN, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89473, _mut89474, _mut89475, _mut89476, _mut89477)) : (ROR_equals(nans, INFINITE, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89468, _mut89469, _mut89470, _mut89471, _mut89472) || ROR_equals(nans, SNAN, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89473, _mut89474, _mut89475, _mut89476, _mut89477)))) {
                    def = newInstance(getZero());
                    def.nans = QNAN;
                }
                break;
            case DfpField.FLAG_UNDERFLOW:
                if (ROR_less((AOR_plus(result.exp, mant.length, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89479, _mut89480, _mut89481, _mut89482)), MIN_EXP, "org.apache.commons.math3.dfp.Dfp.dotrap_2244", _mut89483, _mut89484, _mut89485, _mut89486, _mut89487)) {
                    def = newInstance(getZero());
                    def.sign = result.sign;
                } else {
                    // gradual underflow
                    def = newInstance(result);
                }
                result.exp += ERR_SCALE;
                break;
            case DfpField.FLAG_OVERFLOW:
                result.exp -= ERR_SCALE;
                def = newInstance(getZero());
                def.sign = result.sign;
                def.nans = INFINITE;
                break;
            default:
                def = result;
                break;
        }
        return trap(type, what, oper, def, result);
    }

    /**
     * Trap handler.  Subclasses may override this to provide trap
     *  functionality per IEEE 854-1987.
     *
     *  @param type  The exception type - e.g. FLAG_OVERFLOW
     *  @param what  The name of the routine we were in e.g. divide()
     *  @param oper  An operand to this function if any
     *  @param def   The default return value if trap not enabled
     *  @param result    The result that is specified to be delivered per
     *                   IEEE 854, if any
     *  @return the value that should be return by the operation triggering the trap
     */
    protected Dfp trap(int type, String what, Dfp oper, Dfp def, Dfp result) {
        return def;
    }

    /**
     * Returns the type - one of FINITE, INFINITE, SNAN, QNAN.
     * @return type of the number
     */
    public int classify() {
        return nans;
    }

    /**
     * Creates an instance that is the same as x except that it has the sign of y.
     * abs(x) = dfp.copysign(x, dfp.one)
     * @param x number to get the value from
     * @param y number to get the sign from
     * @return a number with the value of x and the sign of y
     */
    public static Dfp copysign(final Dfp x, final Dfp y) {
        Dfp result = x.newInstance(x);
        result.sign = y.sign;
        return result;
    }

    /**
     * Returns the next number greater than this one in the direction of x.
     * If this==x then simply returns this.
     * @param x direction where to look at
     * @return closest number next to instance in the direction of x
     */
    public Dfp nextAfter(final Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.nextAfter_2342");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(field.getRadixDigits(), x.field.getRadixDigits(), "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89488, _mut89489, _mut89490, _mut89491, _mut89492)) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, NEXT_AFTER_TRAP, x, result);
        }
        // if this is greater than x
        boolean up = false;
        if (this.lessThan(x)) {
            up = true;
        }
        if (ROR_equals(compare(this, x), 0, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89493, _mut89494, _mut89495, _mut89496, _mut89497)) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        final Dfp inc;
        Dfp result;
        if (up) {
            inc = newInstance(getOne());
            inc.exp = AOR_plus(AOR_minus(this.exp, mant.length, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89514, _mut89515, _mut89516, _mut89517), 1, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89518, _mut89519, _mut89520, _mut89521);
            inc.sign = this.sign;
            if (this.equals(getZero())) {
                inc.exp = AOR_minus(MIN_EXP, mant.length, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89522, _mut89523, _mut89524, _mut89525);
            }
            result = add(inc);
        } else {
            inc = newInstance(getOne());
            inc.exp = this.exp;
            inc.sign = this.sign;
            if (this.equals(inc)) {
                inc.exp = AOR_minus(this.exp, mant.length, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89506, _mut89507, _mut89508, _mut89509);
            } else {
                inc.exp = AOR_plus(AOR_minus(this.exp, mant.length, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89498, _mut89499, _mut89500, _mut89501), 1, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89502, _mut89503, _mut89504, _mut89505);
            }
            if (this.equals(getZero())) {
                inc.exp = AOR_minus(MIN_EXP, mant.length, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89510, _mut89511, _mut89512, _mut89513);
            }
            result = this.subtract(inc);
        }
        if ((_mut89536 ? (ROR_equals(result.classify(), INFINITE, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89526, _mut89527, _mut89528, _mut89529, _mut89530) || ROR_not_equals(this.classify(), INFINITE, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89531, _mut89532, _mut89533, _mut89534, _mut89535)) : (ROR_equals(result.classify(), INFINITE, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89526, _mut89527, _mut89528, _mut89529, _mut89530) && ROR_not_equals(this.classify(), INFINITE, "org.apache.commons.math3.dfp.Dfp.nextAfter_2342", _mut89531, _mut89532, _mut89533, _mut89534, _mut89535)))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            result = dotrap(DfpField.FLAG_INEXACT, NEXT_AFTER_TRAP, x, result);
        }
        if ((_mut89537 ? (result.equals(getZero()) || this.equals(getZero()) == false) : (result.equals(getZero()) && this.equals(getZero()) == false))) {
            field.setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            result = dotrap(DfpField.FLAG_INEXACT, NEXT_AFTER_TRAP, x, result);
        }
        return result;
    }

    /**
     * Convert the instance into a double.
     * @return a double approximating the instance
     * @see #toSplitDouble()
     */
    public double toDouble() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.toDouble_2414");
        if (isInfinite()) {
            if (lessThan(getZero())) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }
        if (isNaN()) {
            return Double.NaN;
        }
        Dfp y = this;
        boolean negate = false;
        int cmp0 = compare(this, getZero());
        if (ROR_equals(cmp0, 0, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89538, _mut89539, _mut89540, _mut89541, _mut89542)) {
            return ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89548, _mut89549, _mut89550, _mut89551, _mut89552) ? -0.0 : +0.0;
        } else if (ROR_less(cmp0, 0, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89543, _mut89544, _mut89545, _mut89546, _mut89547)) {
            y = negate();
            negate = true;
        }
        /* Find the exponent, first estimate by integer log10, then adjust.
         Should be faster than doing a natural logarithm.  */
        int exponent = (int) (AOR_multiply(y.intLog10(), 3.32, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89553, _mut89554, _mut89555, _mut89556));
        if (ROR_less(exponent, 0, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89557, _mut89558, _mut89559, _mut89560, _mut89561)) {
            exponent--;
        }
        Dfp tempDfp = DfpMath.pow(getTwo(), exponent);
        while ((_mut89562 ? (tempDfp.lessThan(y) && tempDfp.equals(y)) : (tempDfp.lessThan(y) || tempDfp.equals(y)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.toDouble_2414");
            tempDfp = tempDfp.multiply(2);
            exponent++;
        }
        exponent--;
        y = y.divide(DfpMath.pow(getTwo(), exponent));
        if (ROR_greater(exponent, -1023, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89563, _mut89564, _mut89565, _mut89566, _mut89567)) {
            y = y.subtract(getOne());
        }
        if (ROR_less(exponent, -1074, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89568, _mut89569, _mut89570, _mut89571, _mut89572)) {
            return 0;
        }
        if (ROR_greater(exponent, 1023, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89573, _mut89574, _mut89575, _mut89576, _mut89577)) {
            return negate ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        y = y.multiply(newInstance(4503599627370496l)).rint();
        String str = y.toString();
        str = str.substring(0, AOR_minus(str.length(), 1, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89578, _mut89579, _mut89580, _mut89581));
        long mantissa = Long.parseLong(str);
        if (ROR_equals(mantissa, 4503599627370496L, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89582, _mut89583, _mut89584, _mut89585, _mut89586)) {
            // Handle special case where we round up to next power of two
            mantissa = 0;
            exponent++;
        }
        /* Its going to be subnormal, so make adjustments */
        if (ROR_less_equals(exponent, -1023, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89587, _mut89588, _mut89589, _mut89590, _mut89591)) {
            exponent--;
        }
        while (ROR_less(exponent, -1023, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89592, _mut89593, _mut89594, _mut89595, _mut89596)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.toDouble_2414");
            exponent++;
            mantissa >>>= 1;
        }
        long bits = mantissa | ((AOR_plus(exponent, 1023L, "org.apache.commons.math3.dfp.Dfp.toDouble_2414", _mut89597, _mut89598, _mut89599, _mut89600)) << 52);
        double x = Double.longBitsToDouble(bits);
        if (negate) {
            x = -x;
        }
        return x;
    }

    /**
     * Convert the instance into a split double.
     * @return an array of two doubles which sum represent the instance
     * @see #toDouble()
     */
    public double[] toSplitDouble() {
        double[] split = new double[2];
        long mask = 0xffffffffc0000000L;
        split[0] = Double.longBitsToDouble(Double.doubleToLongBits(toDouble()) & mask);
        split[1] = subtract(newInstance(split[0])).toDouble();
        return split;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public double getReal() {
        return toDouble();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp add(final double a) {
        return add(newInstance(a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp subtract(final double a) {
        return subtract(newInstance(a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp multiply(final double a) {
        return multiply(newInstance(a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp divide(final double a) {
        return divide(newInstance(a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp remainder(final double a) {
        return remainder(newInstance(a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public long round() {
        return FastMath.round(toDouble());
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp signum() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.signum_2566");
        if ((_mut89601 ? (isNaN() && isZero()) : (isNaN() || isZero()))) {
            return this;
        } else {
            return newInstance(ROR_greater(sign, 0, "org.apache.commons.math3.dfp.Dfp.signum_2566", _mut89602, _mut89603, _mut89604, _mut89605, _mut89606) ? +1 : -1);
        }
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp copySign(final Dfp s) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.copySign_2577");
        if ((_mut89629 ? (((_mut89617 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89607, _mut89608, _mut89609, _mut89610, _mut89611) || ROR_greater_equals(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89612, _mut89613, _mut89614, _mut89615, _mut89616)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89607, _mut89608, _mut89609, _mut89610, _mut89611) && ROR_greater_equals(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89612, _mut89613, _mut89614, _mut89615, _mut89616)))) && ((_mut89628 ? (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89618, _mut89619, _mut89620, _mut89621, _mut89622) || ROR_less(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89623, _mut89624, _mut89625, _mut89626, _mut89627)) : (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89618, _mut89619, _mut89620, _mut89621, _mut89622) && ROR_less(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89623, _mut89624, _mut89625, _mut89626, _mut89627))))) : (((_mut89617 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89607, _mut89608, _mut89609, _mut89610, _mut89611) || ROR_greater_equals(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89612, _mut89613, _mut89614, _mut89615, _mut89616)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89607, _mut89608, _mut89609, _mut89610, _mut89611) && ROR_greater_equals(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89612, _mut89613, _mut89614, _mut89615, _mut89616)))) || ((_mut89628 ? (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89618, _mut89619, _mut89620, _mut89621, _mut89622) || ROR_less(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89623, _mut89624, _mut89625, _mut89626, _mut89627)) : (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89618, _mut89619, _mut89620, _mut89621, _mut89622) && ROR_less(s.sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2577", _mut89623, _mut89624, _mut89625, _mut89626, _mut89627))))))) {
            // Sign is currently OK
            return this;
        }
        // flip sign
        return negate();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp copySign(final double s) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.copySign_2587");
        long sb = Double.doubleToLongBits(s);
        if ((_mut89652 ? (((_mut89640 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89630, _mut89631, _mut89632, _mut89633, _mut89634) || ROR_greater_equals(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89635, _mut89636, _mut89637, _mut89638, _mut89639)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89630, _mut89631, _mut89632, _mut89633, _mut89634) && ROR_greater_equals(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89635, _mut89636, _mut89637, _mut89638, _mut89639)))) && ((_mut89651 ? (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89641, _mut89642, _mut89643, _mut89644, _mut89645) || ROR_less(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89646, _mut89647, _mut89648, _mut89649, _mut89650)) : (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89641, _mut89642, _mut89643, _mut89644, _mut89645) && ROR_less(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89646, _mut89647, _mut89648, _mut89649, _mut89650))))) : (((_mut89640 ? (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89630, _mut89631, _mut89632, _mut89633, _mut89634) || ROR_greater_equals(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89635, _mut89636, _mut89637, _mut89638, _mut89639)) : (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89630, _mut89631, _mut89632, _mut89633, _mut89634) && ROR_greater_equals(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89635, _mut89636, _mut89637, _mut89638, _mut89639)))) || ((_mut89651 ? (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89641, _mut89642, _mut89643, _mut89644, _mut89645) || ROR_less(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89646, _mut89647, _mut89648, _mut89649, _mut89650)) : (ROR_less(sign, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89641, _mut89642, _mut89643, _mut89644, _mut89645) && ROR_less(sb, 0, "org.apache.commons.math3.dfp.Dfp.copySign_2587", _mut89646, _mut89647, _mut89648, _mut89649, _mut89650))))))) {
            // Sign is currently OK
            return this;
        }
        // flip sign
        return negate();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp scalb(final int n) {
        return multiply(DfpMath.pow(getTwo(), n));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp hypot(final Dfp y) {
        return multiply(this).add(y.multiply(y)).sqrt();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp cbrt() {
        return rootN(3);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp rootN(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.rootN_2619");
        return (ROR_greater_equals(sign, 0, "org.apache.commons.math3.dfp.Dfp.rootN_2619", _mut89653, _mut89654, _mut89655, _mut89656, _mut89657)) ? DfpMath.pow(this, getOne().divide(n)) : DfpMath.pow(negate(), getOne().divide(n)).negate();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp pow(final double p) {
        return DfpMath.pow(this, newInstance(p));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp pow(final int n) {
        return DfpMath.pow(this, n);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp pow(final Dfp e) {
        return DfpMath.pow(this, e);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp exp() {
        return DfpMath.exp(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp expm1() {
        return DfpMath.exp(this).subtract(getOne());
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp log() {
        return DfpMath.log(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp log1p() {
        return DfpMath.log(this.add(getOne()));
    }

    /**
     * Get the exponent of the greatest power of 10 that is less than or equal to abs(this).
     *  @return integer base 10 logarithm
     *  @deprecated as of 3.2, replaced by {@link #intLog10()}, in 4.0 the return type
     *  will be changed to Dfp
     */
    @Deprecated
    public int log10() {
        return intLog10();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp cos() {
        return DfpMath.cos(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp sin() {
        return DfpMath.sin(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp tan() {
        return DfpMath.tan(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp acos() {
        return DfpMath.acos(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp asin() {
        return DfpMath.asin(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp atan() {
        return DfpMath.atan(this);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp atan2(final Dfp x) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.atan2_2738");
        // compute r = sqrt(x^2+y^2)
        final Dfp r = x.multiply(x).add(multiply(this)).sqrt();
        if (ROR_greater_equals(x.sign, 0, "org.apache.commons.math3.dfp.Dfp.atan2_2738", _mut89658, _mut89659, _mut89660, _mut89661, _mut89662)) {
            // compute atan2(y, x) = 2 atan(y / (r + x))
            return getTwo().multiply(divide(r.add(x)).atan());
        } else {
            // compute atan2(y, x) = +/- pi - 2 atan(y / (r - x))
            final Dfp tmp = getTwo().multiply(divide(r.subtract(x)).atan());
            final Dfp pmPi = newInstance((ROR_less_equals(tmp.sign, 0, "org.apache.commons.math3.dfp.Dfp.atan2_2738", _mut89663, _mut89664, _mut89665, _mut89666, _mut89667)) ? -FastMath.PI : FastMath.PI);
            return pmPi.subtract(tmp);
        }
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp cosh() {
        return DfpMath.exp(this).add(DfpMath.exp(negate())).divide(2);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp sinh() {
        return DfpMath.exp(this).subtract(DfpMath.exp(negate())).divide(2);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp tanh() {
        final Dfp ePlus = DfpMath.exp(this);
        final Dfp eMinus = DfpMath.exp(negate());
        return ePlus.subtract(eMinus).divide(ePlus.add(eMinus));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp acosh() {
        return multiply(this).subtract(getOne()).sqrt().add(this).log();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp asinh() {
        return multiply(this).add(getOne()).sqrt().add(this).log();
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp atanh() {
        return getOne().add(this).divide(getOne().subtract(this)).log().divide(2);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final Dfp[] a, final Dfp[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.linearCombination_2807");
        if (ROR_not_equals(a.length, b.length, "org.apache.commons.math3.dfp.Dfp.linearCombination_2807", _mut89668, _mut89669, _mut89670, _mut89671, _mut89672)) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = getZero();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.dfp.Dfp.linearCombination_2807", _mut89673, _mut89674, _mut89675, _mut89676, _mut89677); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.linearCombination_2807");
            r = r.add(a[i].multiply(b[i]));
        }
        return r;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final double[] a, final Dfp[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.linearCombination_2822");
        if (ROR_not_equals(a.length, b.length, "org.apache.commons.math3.dfp.Dfp.linearCombination_2822", _mut89678, _mut89679, _mut89680, _mut89681, _mut89682)) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        Dfp r = getZero();
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.dfp.Dfp.linearCombination_2822", _mut89683, _mut89684, _mut89685, _mut89686, _mut89687); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.Dfp.linearCombination_2822");
            r = r.add(b[i].multiply(a[i]));
        }
        return r;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final Dfp a1, final Dfp b1, final Dfp a2, final Dfp b2) {
        return a1.multiply(b1).add(a2.multiply(b2));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final double a1, final Dfp b1, final double a2, final Dfp b2) {
        return b1.multiply(a1).add(b2.multiply(a2));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final Dfp a1, final Dfp b1, final Dfp a2, final Dfp b2, final Dfp a3, final Dfp b3) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final double a1, final Dfp b1, final double a2, final Dfp b2, final double a3, final Dfp b3) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final Dfp a1, final Dfp b1, final Dfp a2, final Dfp b2, final Dfp a3, final Dfp b3, final Dfp a4, final Dfp b4) {
        return a1.multiply(b1).add(a2.multiply(b2)).add(a3.multiply(b3)).add(a4.multiply(b4));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Dfp linearCombination(final double a1, final Dfp b1, final double a2, final Dfp b2, final double a3, final Dfp b3, final double a4, final Dfp b4) {
        return b1.multiply(a1).add(b2.multiply(a2)).add(b3.multiply(a3)).add(b4.multiply(a4));
    }
}
