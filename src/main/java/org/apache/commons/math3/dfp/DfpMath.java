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

import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Mathematical routines for use with {@link Dfp}.
 * The constants are defined in {@link DfpField}
 * @since 2.2
 */
public class DfpMath {

    @Conditional
    public static boolean _mut86432 = false, _mut86433 = false, _mut86434 = false, _mut86435 = false, _mut86436 = false, _mut86437 = false, _mut86438 = false, _mut86439 = false, _mut86440 = false, _mut86441 = false, _mut86442 = false, _mut86443 = false, _mut86444 = false, _mut86445 = false, _mut86446 = false, _mut86447 = false, _mut86448 = false, _mut86449 = false, _mut86450 = false, _mut86451 = false, _mut86452 = false, _mut86453 = false, _mut86454 = false, _mut86455 = false, _mut86456 = false, _mut86457 = false, _mut86458 = false, _mut86459 = false, _mut86460 = false, _mut86461 = false, _mut86462 = false, _mut86463 = false, _mut86464 = false, _mut86465 = false, _mut86466 = false, _mut86467 = false, _mut86468 = false, _mut86469 = false, _mut86470 = false, _mut86471 = false, _mut86472 = false, _mut86473 = false, _mut86474 = false, _mut86475 = false, _mut86476 = false, _mut86477 = false, _mut86478 = false, _mut86479 = false, _mut86480 = false, _mut86481 = false, _mut86482 = false, _mut86483 = false, _mut86484 = false, _mut86485 = false, _mut86486 = false, _mut86487 = false, _mut86488 = false, _mut86489 = false, _mut86490 = false, _mut86491 = false, _mut86492 = false, _mut86493 = false, _mut86494 = false, _mut86495 = false, _mut86496 = false, _mut86497 = false, _mut86498 = false, _mut86499 = false, _mut86500 = false, _mut86501 = false, _mut86502 = false, _mut86503 = false, _mut86504 = false, _mut86505 = false, _mut86506 = false, _mut86507 = false, _mut86508 = false, _mut86509 = false, _mut86510 = false, _mut86511 = false, _mut86512 = false, _mut86513 = false, _mut86514 = false, _mut86515 = false, _mut86516 = false, _mut86517 = false, _mut86518 = false, _mut86519 = false, _mut86520 = false, _mut86521 = false, _mut86522 = false, _mut86523 = false, _mut86524 = false, _mut86525 = false, _mut86526 = false, _mut86527 = false, _mut86528 = false, _mut86529 = false, _mut86530 = false, _mut86531 = false, _mut86532 = false, _mut86533 = false, _mut86534 = false, _mut86535 = false, _mut86536 = false, _mut86537 = false, _mut86538 = false, _mut86539 = false, _mut86540 = false, _mut86541 = false, _mut86542 = false, _mut86543 = false, _mut86544 = false, _mut86545 = false, _mut86546 = false, _mut86547 = false, _mut86548 = false, _mut86549 = false, _mut86550 = false, _mut86551 = false, _mut86552 = false, _mut86553 = false, _mut86554 = false, _mut86555 = false, _mut86556 = false, _mut86557 = false, _mut86558 = false, _mut86559 = false, _mut86560 = false, _mut86561 = false, _mut86562 = false, _mut86563 = false, _mut86564 = false, _mut86565 = false, _mut86566 = false, _mut86567 = false, _mut86568 = false, _mut86569 = false, _mut86570 = false, _mut86571 = false, _mut86572 = false, _mut86573 = false, _mut86574 = false, _mut86575 = false, _mut86576 = false, _mut86577 = false, _mut86578 = false, _mut86579 = false, _mut86580 = false, _mut86581 = false, _mut86582 = false, _mut86583 = false, _mut86584 = false, _mut86585 = false, _mut86586 = false, _mut86587 = false, _mut86588 = false, _mut86589 = false, _mut86590 = false, _mut86591 = false, _mut86592 = false, _mut86593 = false, _mut86594 = false, _mut86595 = false, _mut86596 = false, _mut86597 = false, _mut86598 = false, _mut86599 = false, _mut86600 = false, _mut86601 = false, _mut86602 = false, _mut86603 = false, _mut86604 = false, _mut86605 = false, _mut86606 = false, _mut86607 = false, _mut86608 = false, _mut86609 = false, _mut86610 = false, _mut86611 = false, _mut86612 = false, _mut86613 = false, _mut86614 = false, _mut86615 = false, _mut86616 = false, _mut86617 = false, _mut86618 = false, _mut86619 = false, _mut86620 = false, _mut86621 = false, _mut86622 = false, _mut86623 = false, _mut86624 = false, _mut86625 = false, _mut86626 = false, _mut86627 = false, _mut86628 = false, _mut86629 = false, _mut86630 = false, _mut86631 = false, _mut86632 = false, _mut86633 = false, _mut86634 = false, _mut86635 = false, _mut86636 = false, _mut86637 = false, _mut86638 = false, _mut86639 = false, _mut86640 = false, _mut86641 = false, _mut86642 = false, _mut86643 = false, _mut86644 = false, _mut86645 = false, _mut86646 = false, _mut86647 = false, _mut86648 = false, _mut86649 = false, _mut86650 = false, _mut86651 = false, _mut86652 = false, _mut86653 = false, _mut86654 = false, _mut86655 = false, _mut86656 = false, _mut86657 = false, _mut86658 = false, _mut86659 = false, _mut86660 = false, _mut86661 = false, _mut86662 = false, _mut86663 = false, _mut86664 = false, _mut86665 = false, _mut86666 = false, _mut86667 = false, _mut86668 = false, _mut86669 = false, _mut86670 = false, _mut86671 = false, _mut86672 = false, _mut86673 = false, _mut86674 = false;

    /**
     * Name for traps triggered by pow.
     */
    private static final String POW_TRAP = "pow";

    /**
     * Private Constructor.
     */
    private DfpMath() {
    }

    /**
     * Breaks a string representation up into two dfp's.
     * <p>The two dfp are such that the sum of them is equivalent
     * to the input string, but has higher precision than using a
     * single dfp. This is useful for improving accuracy of
     * exponentiation and critical multiplies.
     * @param field field to which the Dfp must belong
     * @param a string representation to split
     * @return an array of two {@link Dfp} which sum is a
     */
    protected static Dfp[] split(final DfpField field, final String a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.split_44");
        Dfp[] result = new Dfp[2];
        char[] buf;
        boolean leading = true;
        int sp = 0;
        int sig = 0;
        buf = new char[a.length()];
        for (int i = 0; ROR_less(i, buf.length, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86476, _mut86477, _mut86478, _mut86479, _mut86480); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.split_44");
            buf[i] = a.charAt(i);
            if ((_mut86442 ? (ROR_greater_equals(buf[i], '1', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86432, _mut86433, _mut86434, _mut86435, _mut86436) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86437, _mut86438, _mut86439, _mut86440, _mut86441)) : (ROR_greater_equals(buf[i], '1', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86432, _mut86433, _mut86434, _mut86435, _mut86436) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86437, _mut86438, _mut86439, _mut86440, _mut86441)))) {
                leading = false;
            }
            if (buf[i] == '.') {
                sig += AOR_remainder((AOR_minus(400, sig, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86443, _mut86444, _mut86445, _mut86446)), 4, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86447, _mut86448, _mut86449, _mut86450);
                leading = false;
            }
            if (ROR_equals(sig, AOR_multiply((AOR_divide(field.getRadixDigits(), 2, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86451, _mut86452, _mut86453, _mut86454)), 4, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86455, _mut86456, _mut86457, _mut86458), "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86459, _mut86460, _mut86461, _mut86462, _mut86463)) {
                sp = i;
                break;
            }
            if ((_mut86475 ? ((_mut86474 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86464, _mut86465, _mut86466, _mut86467, _mut86468) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86469, _mut86470, _mut86471, _mut86472, _mut86473)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86464, _mut86465, _mut86466, _mut86467, _mut86468) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86469, _mut86470, _mut86471, _mut86472, _mut86473))) || !leading) : ((_mut86474 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86464, _mut86465, _mut86466, _mut86467, _mut86468) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86469, _mut86470, _mut86471, _mut86472, _mut86473)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86464, _mut86465, _mut86466, _mut86467, _mut86468) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86469, _mut86470, _mut86471, _mut86472, _mut86473))) && !leading))) {
                sig++;
            }
        }
        result[0] = field.newDfp(new String(buf, 0, sp));
        for (int i = 0; ROR_less(i, buf.length, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86498, _mut86499, _mut86500, _mut86501, _mut86502); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.split_44");
            buf[i] = a.charAt(i);
            if ((_mut86497 ? ((_mut86491 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86481, _mut86482, _mut86483, _mut86484, _mut86485) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86486, _mut86487, _mut86488, _mut86489, _mut86490)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86481, _mut86482, _mut86483, _mut86484, _mut86485) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86486, _mut86487, _mut86488, _mut86489, _mut86490))) || ROR_less(i, sp, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86492, _mut86493, _mut86494, _mut86495, _mut86496)) : ((_mut86491 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86481, _mut86482, _mut86483, _mut86484, _mut86485) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86486, _mut86487, _mut86488, _mut86489, _mut86490)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86481, _mut86482, _mut86483, _mut86484, _mut86485) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86486, _mut86487, _mut86488, _mut86489, _mut86490))) && ROR_less(i, sp, "org.apache.commons.math3.dfp.DfpMath.split_44", _mut86492, _mut86493, _mut86494, _mut86495, _mut86496)))) {
                buf[i] = '0';
            }
        }
        result[1] = field.newDfp(new String(buf));
        return result;
    }

    /**
     * Splits a {@link Dfp} into 2 {@link Dfp}'s such that their sum is equal to the input {@link Dfp}.
     * @param a number to split
     * @return two elements array containing the split number
     */
    protected static Dfp[] split(final Dfp a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.split_93");
        final Dfp[] result = new Dfp[2];
        final Dfp shift = a.multiply(a.power10K(AOR_divide(a.getRadixDigits(), 2, "org.apache.commons.math3.dfp.DfpMath.split_93", _mut86503, _mut86504, _mut86505, _mut86506)));
        result[0] = a.add(shift).subtract(shift);
        result[1] = a.subtract(result[0]);
        return result;
    }

    /**
     * Multiply two numbers that are split in to two pieces that are
     *  meant to be added together.
     *  Use binomial multiplication so ab = a0 b0 + a0 b1 + a1 b0 + a1 b1
     *  Store the first term in result0, the rest in result1
     *  @param a first factor of the multiplication, in split form
     *  @param b second factor of the multiplication, in split form
     *  @return a &times; b, in split form
     */
    protected static Dfp[] splitMult(final Dfp[] a, final Dfp[] b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.splitMult_109");
        final Dfp[] result = new Dfp[2];
        result[1] = a[0].getZero();
        result[0] = a[0].multiply(b[0]);
        if ((_mut86512 ? (ROR_equals(result[0].classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.splitMult_109", _mut86507, _mut86508, _mut86509, _mut86510, _mut86511) && result[0].equals(result[1])) : (ROR_equals(result[0].classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.splitMult_109", _mut86507, _mut86508, _mut86509, _mut86510, _mut86511) || result[0].equals(result[1])))) {
            return result;
        }
        result[1] = a[0].multiply(b[1]).add(a[1].multiply(b[0])).add(a[1].multiply(b[1]));
        return result;
    }

    /**
     * Divide two numbers that are split in to two pieces that are meant to be added together.
     * Inverse of split multiply above:
     *  (a+b) / (c+d) = (a/c) + ( (bc-ad)/(c**2+cd) )
     *  @param a dividend, in split form
     *  @param b divisor, in split form
     *  @return a / b, in split form
     */
    protected static Dfp[] splitDiv(final Dfp[] a, final Dfp[] b) {
        final Dfp[] result;
        result = new Dfp[2];
        result[0] = a[0].divide(b[0]);
        result[1] = a[1].multiply(b[0]).subtract(a[0].multiply(b[1]));
        result[1] = result[1].divide(b[0].multiply(b[0]).add(b[0].multiply(b[1])));
        return result;
    }

    /**
     * Raise a split base to the a power.
     * @param base number to raise
     * @param a power
     * @return base<sup>a</sup>
     */
    protected static Dfp splitPow(final Dfp[] base, int a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.splitPow_152");
        boolean invert = false;
        Dfp[] r = new Dfp[2];
        Dfp[] result = new Dfp[2];
        result[0] = base[0].getOne();
        result[1] = base[0].getZero();
        if (ROR_equals(a, 0, "org.apache.commons.math3.dfp.DfpMath.splitPow_152", _mut86513, _mut86514, _mut86515, _mut86516, _mut86517)) {
            // Special case a = 0
            return result[0].add(result[1]);
        }
        if (ROR_less(a, 0, "org.apache.commons.math3.dfp.DfpMath.splitPow_152", _mut86518, _mut86519, _mut86520, _mut86521, _mut86522)) {
            // If a is less than zero
            invert = true;
            a = -a;
        }
        // Exponentiate by successive squaring
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.splitPow_152");
            r[0] = new Dfp(base[0]);
            r[1] = new Dfp(base[1]);
            int trial = 1;
            int prevtrial;
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.splitPow_152");
                prevtrial = trial;
                trial *= 2;
                if (ROR_greater(trial, a, "org.apache.commons.math3.dfp.DfpMath.splitPow_152", _mut86523, _mut86524, _mut86525, _mut86526, _mut86527)) {
                    break;
                }
                r = splitMult(r, r);
            }
            trial = prevtrial;
            a -= trial;
            result = splitMult(result, r);
        } while (ROR_greater_equals(a, 1, "org.apache.commons.math3.dfp.DfpMath.splitPow_152", _mut86528, _mut86529, _mut86530, _mut86531, _mut86532));
        result[0] = result[0].add(result[1]);
        if (invert) {
            result[0] = base[0].getOne().divide(result[0]);
        }
        return result[0];
    }

    /**
     * Raises base to the power a by successive squaring.
     * @param base number to raise
     * @param a power
     * @return base<sup>a</sup>
     */
    public static Dfp pow(Dfp base, int a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.pow_210");
        boolean invert = false;
        Dfp result = base.getOne();
        if (ROR_equals(a, 0, "org.apache.commons.math3.dfp.DfpMath.pow_210", _mut86533, _mut86534, _mut86535, _mut86536, _mut86537)) {
            // Special case
            return result;
        }
        if (ROR_less(a, 0, "org.apache.commons.math3.dfp.DfpMath.pow_210", _mut86538, _mut86539, _mut86540, _mut86541, _mut86542)) {
            invert = true;
            a = -a;
        }
        // Exponentiate by successive squaring
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.pow_210");
            Dfp r = new Dfp(base);
            Dfp prevr;
            int trial = 1;
            int prevtrial;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.pow_210");
                prevr = new Dfp(r);
                prevtrial = trial;
                r = r.multiply(r);
                trial *= 2;
            } while (ROR_greater(a, trial, "org.apache.commons.math3.dfp.DfpMath.pow_210", _mut86543, _mut86544, _mut86545, _mut86546, _mut86547));
            r = prevr;
            trial = prevtrial;
            a -= trial;
            result = result.multiply(r);
        } while (ROR_greater_equals(a, 1, "org.apache.commons.math3.dfp.DfpMath.pow_210", _mut86548, _mut86549, _mut86550, _mut86551, _mut86552));
        if (invert) {
            result = base.getOne().divide(result);
        }
        return base.newInstance(result);
    }

    /**
     * Computes e to the given power.
     * a is broken into two parts, such that a = n+m  where n is an integer.
     * We use pow() to compute e<sup>n</sup> and a Taylor series to compute
     * e<sup>m</sup>.  We return e*<sup>n</sup> &times; e<sup>m</sup>
     * @param a power at which e should be raised
     * @return e<sup>a</sup>
     */
    public static Dfp exp(final Dfp a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.exp_263");
        final Dfp inta = a.rint();
        final Dfp fraca = a.subtract(inta);
        final int ia = inta.intValue();
        if (ROR_greater(ia, 2147483646, "org.apache.commons.math3.dfp.DfpMath.exp_263", _mut86553, _mut86554, _mut86555, _mut86556, _mut86557)) {
            // return +Infinity
            return a.newInstance((byte) 1, Dfp.INFINITE);
        }
        if (ROR_less(ia, -2147483646, "org.apache.commons.math3.dfp.DfpMath.exp_263", _mut86558, _mut86559, _mut86560, _mut86561, _mut86562)) {
            // return 0;
            return a.newInstance();
        }
        final Dfp einta = splitPow(a.getField().getESplit(), ia);
        final Dfp efraca = expInternal(fraca);
        return einta.multiply(efraca);
    }

    /**
     * Computes e to the given power.
     * Where -1 < a < 1.  Use the classic Taylor series.  1 + x**2/2! + x**3/3! + x**4/4!  ...
     * @param a power at which e should be raised
     * @return e<sup>a</sup>
     */
    protected static Dfp expInternal(final Dfp a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.expInternal_290");
        Dfp y = a.getOne();
        Dfp x = a.getOne();
        Dfp fact = a.getOne();
        Dfp py = new Dfp(y);
        for (int i = 1; ROR_less(i, 90, "org.apache.commons.math3.dfp.DfpMath.expInternal_290", _mut86563, _mut86564, _mut86565, _mut86566, _mut86567); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.expInternal_290");
            x = x.multiply(a);
            fact = fact.divide(i);
            y = y.add(x.multiply(fact));
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y;
    }

    /**
     * Returns the natural logarithm of a.
     * a is first split into three parts such that  a = (10000^h)(2^j)k.
     * ln(a) is computed by ln(a) = ln(5)*h + ln(2)*(h+j) + ln(k)
     * k is in the range 2/3 < k <4/3 and is passed on to a series expansion.
     * @param a number from which logarithm is requested
     * @return log(a)
     */
    public static Dfp log(Dfp a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.log_316");
        int lr;
        Dfp x;
        int ix;
        int p2 = 0;
        // Check the arguments somewhat here
        if ((_mut86569 ? ((_mut86568 ? (a.equals(a.getZero()) && a.lessThan(a.getZero())) : (a.equals(a.getZero()) || a.lessThan(a.getZero()))) && a.isNaN()) : ((_mut86568 ? (a.equals(a.getZero()) && a.lessThan(a.getZero())) : (a.equals(a.getZero()) || a.lessThan(a.getZero()))) || a.isNaN()))) {
            // negative, zero or NaN
            a.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            return a.dotrap(DfpField.FLAG_INVALID, "ln", a, a.newInstance((byte) 1, Dfp.QNAN));
        }
        if (ROR_equals(a.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.log_316", _mut86570, _mut86571, _mut86572, _mut86573, _mut86574)) {
            return a;
        }
        x = new Dfp(a);
        lr = x.log10K();
        x = x.divide(pow(a.newInstance(10000), lr));
        /* This puts x in the range 0-10000 */
        ix = x.floor().intValue();
        while (ROR_greater(ix, 2, "org.apache.commons.math3.dfp.DfpMath.log_316", _mut86575, _mut86576, _mut86577, _mut86578, _mut86579)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.log_316");
            ix >>= 1;
            p2++;
        }
        Dfp[] spx = split(x);
        Dfp[] spy = new Dfp[2];
        // use spy[0] temporarily as a divisor
        spy[0] = pow(a.getTwo(), p2);
        spx[0] = spx[0].divide(spy[0]);
        spx[1] = spx[1].divide(spy[0]);
        // Use spy[0] for comparison
        spy[0] = a.newInstance("1.33333");
        while (spx[0].add(spx[1]).greaterThan(spy[0])) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.log_316");
            spx[0] = spx[0].divide(2);
            spx[1] = spx[1].divide(2);
            p2++;
        }
        // X is now in the range of 2/3 < x < 4/3
        Dfp[] spz = logInternal(spx);
        spx[0] = a.newInstance(new StringBuilder().append(AOR_plus(p2, AOR_multiply(4, lr, "org.apache.commons.math3.dfp.DfpMath.log_316", _mut86580, _mut86581, _mut86582, _mut86583), "org.apache.commons.math3.dfp.DfpMath.log_316", _mut86584, _mut86585, _mut86586, _mut86587)).toString());
        spx[1] = a.getZero();
        spy = splitMult(a.getField().getLn2Split(), spx);
        spz[0] = spz[0].add(spy[0]);
        spz[1] = spz[1].add(spy[1]);
        spx[0] = a.newInstance(new StringBuilder().append(AOR_multiply(4, lr, "org.apache.commons.math3.dfp.DfpMath.log_316", _mut86588, _mut86589, _mut86590, _mut86591)).toString());
        spx[1] = a.getZero();
        spy = splitMult(a.getField().getLn5Split(), spx);
        spz[0] = spz[0].add(spy[0]);
        spz[1] = spz[1].add(spy[1]);
        return a.newInstance(spz[0].add(spz[1]));
    }

    /**
     * Computes the natural log of a number between 0 and 2.
     *  Let f(x) = ln(x),
     *
     *  We know that f'(x) = 1/x, thus from Taylor's theorum we have:
     *
     *           -----          n+1         n
     *  f(x) =   \           (-1)    (x - 1)
     *           /          ----------------    for 1 <= n <= infinity
     *           -----             n
     *
     *  or
     *                       2        3       4
     *                   (x-1)   (x-1)    (x-1)
     *  ln(x) =  (x-1) - ----- + ------ - ------ + ...
     *                     2       3        4
     *
     *  alternatively,
     *
     *                  2    3   4
     *                 x    x   x
     *  ln(x+1) =  x - -  + - - - + ...
     *                 2    3   4
     *
     *  This series can be used to compute ln(x), but it converges too slowly.
     *
     *  If we substitute -x for x above, we get
     *
     *                   2    3    4
     *                  x    x    x
     *  ln(1-x) =  -x - -  - -  - - + ...
     *                  2    3    4
     *
     *  Note that all terms are now negative.  Because the even powered ones
     *  absorbed the sign.  Now, subtract the series above from the previous
     *  one to get ln(x+1) - ln(1-x).  Note the even terms cancel out leaving
     *  only the odd ones
     *
     *                             3     5      7
     *                           2x    2x     2x
     *  ln(x+1) - ln(x-1) = 2x + --- + --- + ---- + ...
     *                            3     5      7
     *
     *  By the property of logarithms that ln(a) - ln(b) = ln (a/b) we have:
     *
     *                                3        5        7
     *      x+1           /          x        x        x          \
     *  ln ----- =   2 *  |  x  +   ----  +  ----  +  ---- + ...  |
     *      x-1           \          3        5        7          /
     *
     *  But now we want to find ln(a), so we need to find the value of x
     *  such that a = (x+1)/(x-1).   This is easily solved to find that
     *  x = (a-1)/(a+1).
     * @param a number from which logarithm is requested, in split form
     * @return log(a)
     */
    protected static Dfp[] logInternal(final Dfp[] a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.logInternal_434");
        /* Now we want to compute x = (a-1)/(a+1) but this is prone to
         * loss of precision.  So instead, compute x = (a/4 - 1/4) / (a/4 + 1/4)
         */
        Dfp t = a[0].divide(4).add(a[1].divide(4));
        Dfp x = t.add(a[0].newInstance("-0.25")).divide(t.add(a[0].newInstance("0.25")));
        Dfp y = new Dfp(x);
        Dfp num = new Dfp(x);
        Dfp py = new Dfp(y);
        int den = 1;
        for (int i = 0; ROR_less(i, 10000, "org.apache.commons.math3.dfp.DfpMath.logInternal_434", _mut86592, _mut86593, _mut86594, _mut86595, _mut86596); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.logInternal_434");
            num = num.multiply(x);
            num = num.multiply(x);
            den += 2;
            t = num.divide(den);
            y = y.add(t);
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        y = y.multiply(a[0].getTwo());
        return split(y);
    }

    /**
     * Computes x to the y power.<p>
     *
     *  Uses the following method:<p>
     *
     *  <ol>
     *  <li> Set u = rint(y), v = y-u
     *  <li> Compute a = v * ln(x)
     *  <li> Compute b = rint( a/ln(2) )
     *  <li> Compute c = a - b*ln(2)
     *  <li> x<sup>y</sup> = x<sup>u</sup>  *   2<sup>b</sup> * e<sup>c</sup>
     *  </ol>
     *  if |y| > 1e8, then we compute by exp(y*ln(x))   <p>
     *
     *  <b>Special Cases</b><p>
     *  <ul>
     *  <li>  if y is 0.0 or -0.0 then result is 1.0
     *  <li>  if y is 1.0 then result is x
     *  <li>  if y is NaN then result is NaN
     *  <li>  if x is NaN and y is not zero then result is NaN
     *  <li>  if |x| > 1.0 and y is +Infinity then result is +Infinity
     *  <li>  if |x| < 1.0 and y is -Infinity then result is +Infinity
     *  <li>  if |x| > 1.0 and y is -Infinity then result is +0
     *  <li>  if |x| < 1.0 and y is +Infinity then result is +0
     *  <li>  if |x| = 1.0 and y is +/-Infinity then result is NaN
     *  <li>  if x = +0 and y > 0 then result is +0
     *  <li>  if x = +Inf and y < 0 then result is +0
     *  <li>  if x = +0 and y < 0 then result is +Inf
     *  <li>  if x = +Inf and y > 0 then result is +Inf
     *  <li>  if x = -0 and y > 0, finite, not odd integer then result is +0
     *  <li>  if x = -0 and y < 0, finite, and odd integer then result is -Inf
     *  <li>  if x = -Inf and y > 0, finite, and odd integer then result is -Inf
     *  <li>  if x = -0 and y < 0, not finite odd integer then result is +Inf
     *  <li>  if x = -Inf and y > 0, not finite odd integer then result is +Inf
     *  <li>  if x < 0 and y > 0, finite, and odd integer then result is -(|x|<sup>y</sup>)
     *  <li>  if x < 0 and y > 0, finite, and not integer then result is NaN
     *  </ul>
     *  @param x base to be raised
     *  @param y power to which base should be raised
     *  @return x<sup>y</sup>
     */
    public static Dfp pow(Dfp x, final Dfp y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.pow_504");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(x.getField().getRadixDigits(), y.getField().getRadixDigits(), "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86597, _mut86598, _mut86599, _mut86600, _mut86601)) {
            x.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = x.newInstance(x.getZero());
            result.nans = Dfp.QNAN;
            return x.dotrap(DfpField.FLAG_INVALID, POW_TRAP, x, result);
        }
        final Dfp zero = x.getZero();
        final Dfp one = x.getOne();
        final Dfp two = x.getTwo();
        boolean invert = false;
        int ui;
        /* Check for special cases */
        if (y.equals(zero)) {
            return x.newInstance(one);
        }
        if (y.equals(one)) {
            if (x.isNaN()) {
                // Test for NaNs
                x.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
                return x.dotrap(DfpField.FLAG_INVALID, POW_TRAP, x, x);
            }
            return x;
        }
        if ((_mut86602 ? (x.isNaN() && y.isNaN()) : (x.isNaN() || y.isNaN()))) {
            // Test for NaNs
            x.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            return x.dotrap(DfpField.FLAG_INVALID, POW_TRAP, x, x.newInstance((byte) 1, Dfp.QNAN));
        }
        // X == 0
        if (x.equals(zero)) {
            if (Dfp.copysign(one, x).greaterThan(zero)) {
                // X == +0
                if (y.greaterThan(zero)) {
                    return x.newInstance(zero);
                } else {
                    return x.newInstance(x.newInstance((byte) 1, Dfp.INFINITE));
                }
            } else {
                // X == -0
                if ((_mut86609 ? ((_mut86608 ? (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86603, _mut86604, _mut86605, _mut86606, _mut86607) || y.rint().equals(y)) : (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86603, _mut86604, _mut86605, _mut86606, _mut86607) && y.rint().equals(y))) || !y.remainder(two).equals(zero)) : ((_mut86608 ? (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86603, _mut86604, _mut86605, _mut86606, _mut86607) || y.rint().equals(y)) : (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86603, _mut86604, _mut86605, _mut86606, _mut86607) && y.rint().equals(y))) && !y.remainder(two).equals(zero)))) {
                    // If y is odd integer
                    if (y.greaterThan(zero)) {
                        return x.newInstance(zero.negate());
                    } else {
                        return x.newInstance(x.newInstance((byte) -1, Dfp.INFINITE));
                    }
                } else {
                    // Y is not odd integer
                    if (y.greaterThan(zero)) {
                        return x.newInstance(zero);
                    } else {
                        return x.newInstance(x.newInstance((byte) 1, Dfp.INFINITE));
                    }
                }
            }
        }
        if (x.lessThan(zero)) {
            // Make x positive, but keep track of it
            x = x.negate();
            invert = true;
        }
        if ((_mut86615 ? (x.greaterThan(one) || ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86610, _mut86611, _mut86612, _mut86613, _mut86614)) : (x.greaterThan(one) && ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86610, _mut86611, _mut86612, _mut86613, _mut86614)))) {
            if (y.greaterThan(zero)) {
                return y;
            } else {
                return x.newInstance(zero);
            }
        }
        if ((_mut86621 ? (x.lessThan(one) || ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86616, _mut86617, _mut86618, _mut86619, _mut86620)) : (x.lessThan(one) && ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86616, _mut86617, _mut86618, _mut86619, _mut86620)))) {
            if (y.greaterThan(zero)) {
                return x.newInstance(zero);
            } else {
                return x.newInstance(Dfp.copysign(y, one));
            }
        }
        if ((_mut86627 ? (x.equals(one) || ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86622, _mut86623, _mut86624, _mut86625, _mut86626)) : (x.equals(one) && ROR_equals(y.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86622, _mut86623, _mut86624, _mut86625, _mut86626)))) {
            x.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            return x.dotrap(DfpField.FLAG_INVALID, POW_TRAP, x, x.newInstance((byte) 1, Dfp.QNAN));
        }
        if (ROR_equals(x.classify(), Dfp.INFINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86628, _mut86629, _mut86630, _mut86631, _mut86632)) {
            // x = +/- inf
            if (invert) {
                // negative infinity
                if ((_mut86639 ? ((_mut86638 ? (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86633, _mut86634, _mut86635, _mut86636, _mut86637) || y.rint().equals(y)) : (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86633, _mut86634, _mut86635, _mut86636, _mut86637) && y.rint().equals(y))) || !y.remainder(two).equals(zero)) : ((_mut86638 ? (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86633, _mut86634, _mut86635, _mut86636, _mut86637) || y.rint().equals(y)) : (ROR_equals(y.classify(), Dfp.FINITE, "org.apache.commons.math3.dfp.DfpMath.pow_504", _mut86633, _mut86634, _mut86635, _mut86636, _mut86637) && y.rint().equals(y))) && !y.remainder(two).equals(zero)))) {
                    // If y is odd integer
                    if (y.greaterThan(zero)) {
                        return x.newInstance(x.newInstance((byte) -1, Dfp.INFINITE));
                    } else {
                        return x.newInstance(zero.negate());
                    }
                } else {
                    // Y is not odd integer
                    if (y.greaterThan(zero)) {
                        return x.newInstance(x.newInstance((byte) 1, Dfp.INFINITE));
                    } else {
                        return x.newInstance(zero);
                    }
                }
            } else {
                // positive infinity
                if (y.greaterThan(zero)) {
                    return x;
                } else {
                    return x.newInstance(zero);
                }
            }
        }
        if ((_mut86640 ? (invert || !y.rint().equals(y)) : (invert && !y.rint().equals(y)))) {
            x.getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            return x.dotrap(DfpField.FLAG_INVALID, POW_TRAP, x, x.newInstance((byte) 1, Dfp.QNAN));
        }
        Dfp r;
        if ((_mut86641 ? (y.lessThan(x.newInstance(100000000)) || y.greaterThan(x.newInstance(-100000000))) : (y.lessThan(x.newInstance(100000000)) && y.greaterThan(x.newInstance(-100000000))))) {
            final Dfp u = y.rint();
            ui = u.intValue();
            final Dfp v = y.subtract(u);
            if (v.unequal(zero)) {
                final Dfp a = v.multiply(log(x));
                final Dfp b = a.divide(x.getField().getLn2()).rint();
                final Dfp c = a.subtract(b.multiply(x.getField().getLn2()));
                r = splitPow(split(x), ui);
                r = r.multiply(pow(two, b.intValue()));
                r = r.multiply(exp(c));
            } else {
                r = splitPow(split(x), ui);
            }
        } else {
            // very large exponent.  |y| > 1e8
            r = exp(log(x).multiply(y));
        }
        if ((_mut86643 ? ((_mut86642 ? (invert || y.rint().equals(y)) : (invert && y.rint().equals(y))) || !y.remainder(two).equals(zero)) : ((_mut86642 ? (invert || y.rint().equals(y)) : (invert && y.rint().equals(y))) && !y.remainder(two).equals(zero)))) {
            // if y is odd integer
            r = r.negate();
        }
        return x.newInstance(r);
    }

    /**
     * Computes sin(a)  Used when 0 < a < pi/4.
     * Uses the classic Taylor series.  x - x**3/3! + x**5/5!  ...
     * @param a number from which sine is desired, in split form
     * @return sin(a)
     */
    protected static Dfp sinInternal(Dfp[] a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.sinInternal_669");
        Dfp c = a[0].add(a[1]);
        Dfp y = c;
        c = c.multiply(c);
        Dfp x = y;
        Dfp fact = a[0].getOne();
        Dfp py = new Dfp(y);
        for (int i = 3; ROR_less(i, 90, "org.apache.commons.math3.dfp.DfpMath.sinInternal_669", _mut86652, _mut86653, _mut86654, _mut86655, _mut86656); i += 2) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.sinInternal_669");
            x = x.multiply(c);
            x = x.negate();
            // 1 over fact
            fact = fact.divide(AOR_multiply((AOR_minus(i, 1, "org.apache.commons.math3.dfp.DfpMath.sinInternal_669", _mut86644, _mut86645, _mut86646, _mut86647)), i, "org.apache.commons.math3.dfp.DfpMath.sinInternal_669", _mut86648, _mut86649, _mut86650, _mut86651));
            y = y.add(x.multiply(fact));
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y;
    }

    /**
     * Computes cos(a)  Used when 0 < a < pi/4.
     * Uses the classic Taylor series for cosine.  1 - x**2/2! + x**4/4!  ...
     * @param a number from which cosine is desired, in split form
     * @return cos(a)
     */
    protected static Dfp cosInternal(Dfp[] a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.cosInternal_699");
        final Dfp one = a[0].getOne();
        Dfp x = one;
        Dfp y = one;
        Dfp c = a[0].add(a[1]);
        c = c.multiply(c);
        Dfp fact = one;
        Dfp py = new Dfp(y);
        for (int i = 2; ROR_less(i, 90, "org.apache.commons.math3.dfp.DfpMath.cosInternal_699", _mut86665, _mut86666, _mut86667, _mut86668, _mut86669); i += 2) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.cosInternal_699");
            x = x.multiply(c);
            x = x.negate();
            // 1 over fact
            fact = fact.divide(AOR_multiply((AOR_minus(i, 1, "org.apache.commons.math3.dfp.DfpMath.cosInternal_699", _mut86657, _mut86658, _mut86659, _mut86660)), i, "org.apache.commons.math3.dfp.DfpMath.cosInternal_699", _mut86661, _mut86662, _mut86663, _mut86664));
            y = y.add(x.multiply(fact));
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y;
    }

    /**
     * computes the sine of the argument.
     * @param a number from which sine is desired
     * @return sin(a)
     */
    public static Dfp sin(final Dfp a) {
        final Dfp pi = a.getField().getPi();
        final Dfp zero = a.getField().getZero();
        boolean neg = false;
        /* First reduce the argument to the range of +/- PI */
        Dfp x = a.remainder(pi.multiply(2));
        /* This puts x in the range 0 < x < PI            */
        if (x.lessThan(zero)) {
            x = x.negate();
            neg = true;
        }
        if (x.greaterThan(pi.divide(2))) {
            x = pi.subtract(x);
        }
        Dfp y;
        if (x.lessThan(pi.divide(4))) {
            y = sinInternal(split(x));
        } else {
            final Dfp[] c = new Dfp[2];
            final Dfp[] piSplit = a.getField().getPiSplit();
            c[0] = piSplit[0].divide(2).subtract(x);
            c[1] = piSplit[1].divide(2);
            y = cosInternal(c);
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    /**
     * computes the cosine of the argument.
     * @param a number from which cosine is desired
     * @return cos(a)
     */
    public static Dfp cos(Dfp a) {
        final Dfp pi = a.getField().getPi();
        final Dfp zero = a.getField().getZero();
        boolean neg = false;
        /* First reduce the argument to the range of +/- PI */
        Dfp x = a.remainder(pi.multiply(2));
        /* This puts x in the range 0 < x < PI           */
        if (x.lessThan(zero)) {
            x = x.negate();
        }
        if (x.greaterThan(pi.divide(2))) {
            x = pi.subtract(x);
            neg = true;
        }
        Dfp y;
        if (x.lessThan(pi.divide(4))) {
            Dfp[] c = new Dfp[2];
            c[0] = x;
            c[1] = zero;
            y = cosInternal(c);
        } else {
            final Dfp[] c = new Dfp[2];
            final Dfp[] piSplit = a.getField().getPiSplit();
            c[0] = piSplit[0].divide(2).subtract(x);
            c[1] = piSplit[1].divide(2);
            y = sinInternal(c);
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    /**
     * computes the tangent of the argument.
     * @param a number from which tangent is desired
     * @return tan(a)
     */
    public static Dfp tan(final Dfp a) {
        return sin(a).divide(cos(a));
    }

    /**
     * computes the arc-tangent of the argument.
     * @param a number from which arc-tangent is desired
     * @return atan(a)
     */
    protected static Dfp atanInternal(final Dfp a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.atanInternal_836");
        Dfp y = new Dfp(a);
        Dfp x = new Dfp(y);
        Dfp py = new Dfp(y);
        for (int i = 3; ROR_less(i, 90, "org.apache.commons.math3.dfp.DfpMath.atanInternal_836", _mut86670, _mut86671, _mut86672, _mut86673, _mut86674); i += 2) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpMath.atanInternal_836");
            x = x.multiply(a);
            x = x.multiply(a);
            x = x.negate();
            y = y.add(x.divide(i));
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y;
    }

    /**
     * computes the arc tangent of the argument
     *
     *  Uses the typical taylor series
     *
     *  but may reduce arguments using the following identity
     * tan(x+y) = (tan(x) + tan(y)) / (1 - tan(x)*tan(y))
     *
     * since tan(PI/8) = sqrt(2)-1,
     *
     * atan(x) = atan( (x - sqrt(2) + 1) / (1+x*sqrt(2) - x) + PI/8.0
     * @param a number from which arc-tangent is desired
     * @return atan(a)
     */
    public static Dfp atan(final Dfp a) {
        final Dfp zero = a.getField().getZero();
        final Dfp one = a.getField().getOne();
        final Dfp[] sqr2Split = a.getField().getSqr2Split();
        final Dfp[] piSplit = a.getField().getPiSplit();
        boolean recp = false;
        boolean neg = false;
        boolean sub = false;
        final Dfp ty = sqr2Split[0].subtract(one).add(sqr2Split[1]);
        Dfp x = new Dfp(a);
        if (x.lessThan(zero)) {
            neg = true;
            x = x.negate();
        }
        if (x.greaterThan(one)) {
            recp = true;
            x = one.divide(x);
        }
        if (x.greaterThan(ty)) {
            Dfp[] sty = new Dfp[2];
            sub = true;
            sty[0] = sqr2Split[0].subtract(one);
            sty[1] = sqr2Split[1];
            Dfp[] xs = split(x);
            Dfp[] ds = splitMult(xs, sty);
            ds[0] = ds[0].add(one);
            xs[0] = xs[0].subtract(sty[0]);
            xs[1] = xs[1].subtract(sty[1]);
            xs = splitDiv(xs, ds);
            x = xs[0].add(xs[1]);
        }
        Dfp y = atanInternal(x);
        if (sub) {
            y = y.add(piSplit[0].divide(8)).add(piSplit[1].divide(8));
        }
        if (recp) {
            y = piSplit[0].divide(2).subtract(y).add(piSplit[1].divide(2));
        }
        if (neg) {
            y = y.negate();
        }
        return a.newInstance(y);
    }

    /**
     * computes the arc-sine of the argument.
     * @param a number from which arc-sine is desired
     * @return asin(a)
     */
    public static Dfp asin(final Dfp a) {
        return atan(a.divide(a.getOne().subtract(a.multiply(a)).sqrt()));
    }

    /**
     * computes the arc-cosine of the argument.
     * @param a number from which arc-cosine is desired
     * @return acos(a)
     */
    public static Dfp acos(Dfp a) {
        Dfp result;
        boolean negative = false;
        if (a.lessThan(a.getZero())) {
            negative = true;
        }
        // absolute value
        a = Dfp.copysign(a, a.getOne());
        result = atan(a.getOne().subtract(a.multiply(a)).sqrt().divide(a));
        if (negative) {
            result = a.getField().getPi().subtract(result);
        }
        return a.newInstance(result);
    }
}
