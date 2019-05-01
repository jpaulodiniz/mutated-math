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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Field for Decimal floating point instances.
 * @since 2.2
 */
public class DfpField implements Field<Dfp> {

    @Conditional
    public static boolean _mut89688 = false, _mut89689 = false, _mut89690 = false, _mut89691 = false, _mut89692 = false, _mut89693 = false, _mut89694 = false, _mut89695 = false, _mut89696 = false, _mut89697 = false, _mut89698 = false, _mut89699 = false, _mut89700 = false, _mut89701 = false, _mut89702 = false, _mut89703 = false, _mut89704 = false, _mut89705 = false, _mut89706 = false, _mut89707 = false, _mut89708 = false, _mut89709 = false, _mut89710 = false, _mut89711 = false, _mut89712 = false, _mut89713 = false, _mut89714 = false, _mut89715 = false, _mut89716 = false, _mut89717 = false, _mut89718 = false, _mut89719 = false, _mut89720 = false, _mut89721 = false, _mut89722 = false, _mut89723 = false, _mut89724 = false, _mut89725 = false, _mut89726 = false, _mut89727 = false, _mut89728 = false, _mut89729 = false, _mut89730 = false, _mut89731 = false, _mut89732 = false, _mut89733 = false, _mut89734 = false, _mut89735 = false, _mut89736 = false, _mut89737 = false, _mut89738 = false, _mut89739 = false, _mut89740 = false, _mut89741 = false, _mut89742 = false, _mut89743 = false, _mut89744 = false, _mut89745 = false, _mut89746 = false, _mut89747 = false, _mut89748 = false, _mut89749 = false, _mut89750 = false, _mut89751 = false, _mut89752 = false, _mut89753 = false, _mut89754 = false, _mut89755 = false, _mut89756 = false, _mut89757 = false, _mut89758 = false, _mut89759 = false, _mut89760 = false, _mut89761 = false, _mut89762 = false, _mut89763 = false, _mut89764 = false, _mut89765 = false, _mut89766 = false, _mut89767 = false, _mut89768 = false, _mut89769 = false, _mut89770 = false, _mut89771 = false, _mut89772 = false, _mut89773 = false, _mut89774 = false, _mut89775 = false, _mut89776 = false, _mut89777 = false, _mut89778 = false, _mut89779 = false, _mut89780 = false, _mut89781 = false, _mut89782 = false, _mut89783 = false, _mut89784 = false, _mut89785 = false, _mut89786 = false, _mut89787 = false, _mut89788 = false, _mut89789 = false, _mut89790 = false, _mut89791 = false, _mut89792 = false, _mut89793 = false, _mut89794 = false, _mut89795 = false, _mut89796 = false, _mut89797 = false, _mut89798 = false, _mut89799 = false, _mut89800 = false, _mut89801 = false, _mut89802 = false, _mut89803 = false, _mut89804 = false, _mut89805 = false;

    /**
     * Enumerate for rounding modes.
     */
    public enum RoundingMode {

        /**
         * Rounds toward zero (truncation).
         */
        ROUND_DOWN,
        /**
         * Rounds away from zero if discarded digit is non-zero.
         */
        ROUND_UP,
        /**
         * Rounds towards nearest unless both are equidistant in which case it rounds away from zero.
         */
        ROUND_HALF_UP,
        /**
         * Rounds towards nearest unless both are equidistant in which case it rounds toward zero.
         */
        ROUND_HALF_DOWN,
        /**
         * Rounds towards nearest unless both are equidistant in which case it rounds toward the even neighbor.
         * This is the default as  specified by IEEE 854-1987
         */
        ROUND_HALF_EVEN,
        /**
         * Rounds towards nearest unless both are equidistant in which case it rounds toward the odd neighbor.
         */
        ROUND_HALF_ODD,
        /**
         * Rounds towards positive infinity.
         */
        ROUND_CEIL,
        /**
         * Rounds towards negative infinity.
         */
        ROUND_FLOOR
    }

    /**
     * IEEE 854-1987 flag for invalid operation.
     */
    public static final int FLAG_INVALID = 1;

    /**
     * IEEE 854-1987 flag for division by zero.
     */
    public static final int FLAG_DIV_ZERO = 2;

    /**
     * IEEE 854-1987 flag for overflow.
     */
    public static final int FLAG_OVERFLOW = 4;

    /**
     * IEEE 854-1987 flag for underflow.
     */
    public static final int FLAG_UNDERFLOW = 8;

    /**
     * IEEE 854-1987 flag for inexact result.
     */
    public static final int FLAG_INEXACT = 16;

    /**
     * High precision string representation of &radic;2.
     */
    private static String sqr2String;

    /**
     * High precision string representation of &radic;2 / 2.
     */
    private static String sqr2ReciprocalString;

    /**
     * High precision string representation of &radic;3.
     */
    private static String sqr3String;

    /**
     * High precision string representation of &radic;3 / 3.
     */
    private static String sqr3ReciprocalString;

    /**
     * High precision string representation of &pi;.
     */
    private static String piString;

    /**
     * High precision string representation of e.
     */
    private static String eString;

    /**
     * High precision string representation of ln(2).
     */
    private static String ln2String;

    /**
     * High precision string representation of ln(5).
     */
    private static String ln5String;

    /**
     * High precision string representation of ln(10).
     */
    private static String ln10String;

    /**
     * The number of radix digits.
     * Note these depend on the radix which is 10000 digits,
     * so each one is equivalent to 4 decimal digits.
     */
    private final int radixDigits;

    /**
     * A {@link Dfp} with value 0.
     */
    private final Dfp zero;

    /**
     * A {@link Dfp} with value 1.
     */
    private final Dfp one;

    /**
     * A {@link Dfp} with value 2.
     */
    private final Dfp two;

    /**
     * A {@link Dfp} with value &radic;2.
     */
    private final Dfp sqr2;

    /**
     * A two elements {@link Dfp} array with value &radic;2 split in two pieces.
     */
    private final Dfp[] sqr2Split;

    /**
     * A {@link Dfp} with value &radic;2 / 2.
     */
    private final Dfp sqr2Reciprocal;

    /**
     * A {@link Dfp} with value &radic;3.
     */
    private final Dfp sqr3;

    /**
     * A {@link Dfp} with value &radic;3 / 3.
     */
    private final Dfp sqr3Reciprocal;

    /**
     * A {@link Dfp} with value &pi;.
     */
    private final Dfp pi;

    /**
     * A two elements {@link Dfp} array with value &pi; split in two pieces.
     */
    private final Dfp[] piSplit;

    /**
     * A {@link Dfp} with value e.
     */
    private final Dfp e;

    /**
     * A two elements {@link Dfp} array with value e split in two pieces.
     */
    private final Dfp[] eSplit;

    /**
     * A {@link Dfp} with value ln(2).
     */
    private final Dfp ln2;

    /**
     * A two elements {@link Dfp} array with value ln(2) split in two pieces.
     */
    private final Dfp[] ln2Split;

    /**
     * A {@link Dfp} with value ln(5).
     */
    private final Dfp ln5;

    /**
     * A two elements {@link Dfp} array with value ln(5) split in two pieces.
     */
    private final Dfp[] ln5Split;

    /**
     * A {@link Dfp} with value ln(10).
     */
    private final Dfp ln10;

    /**
     * Current rounding mode.
     */
    private RoundingMode rMode;

    /**
     * IEEE 854-1987 signals.
     */
    private int ieeeFlags;

    /**
     * Create a factory for the specified number of radix digits.
     * <p>
     * Note that since the {@link Dfp} class uses 10000 as its radix, each radix
     * digit is equivalent to 4 decimal digits. This implies that asking for
     * 13, 14, 15 or 16 decimal digits will really lead to a 4 radix 10000 digits in
     * all cases.
     * </p>
     * @param decimalDigits minimal number of decimal digits.
     */
    public DfpField(final int decimalDigits) {
        this(decimalDigits, true);
    }

    /**
     * Create a factory for the specified number of radix digits.
     * <p>
     * Note that since the {@link Dfp} class uses 10000 as its radix, each radix
     * digit is equivalent to 4 decimal digits. This implies that asking for
     * 13, 14, 15 or 16 decimal digits will really lead to a 4 radix 10000 digits in
     * all cases.
     * </p>
     * @param decimalDigits minimal number of decimal digits
     * @param computeConstants if true, the transcendental constants for the given precision
     * must be computed (setting this flag to false is RESERVED for the internal recursive call)
     */
    private DfpField(final int decimalDigits, final boolean computeConstants) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.DfpField_190");
        this.radixDigits = (ROR_less(decimalDigits, 13, "org.apache.commons.math3.dfp.DfpField.DfpField_190", _mut89688, _mut89689, _mut89690, _mut89691, _mut89692)) ? 4 : AOR_divide((AOR_plus(decimalDigits, 3, "org.apache.commons.math3.dfp.DfpField.DfpField_190", _mut89693, _mut89694, _mut89695, _mut89696)), 4, "org.apache.commons.math3.dfp.DfpField.DfpField_190", _mut89697, _mut89698, _mut89699, _mut89700);
        this.rMode = RoundingMode.ROUND_HALF_EVEN;
        this.ieeeFlags = 0;
        this.zero = new Dfp(this, 0);
        this.one = new Dfp(this, 1);
        this.two = new Dfp(this, 2);
        if (computeConstants) {
            // set up transcendental constants
            synchronized (DfpField.class) {
                // constants only once, we set a minimum number of digits
                computeStringConstants((ROR_less(decimalDigits, 67, "org.apache.commons.math3.dfp.DfpField.DfpField_190", _mut89701, _mut89702, _mut89703, _mut89704, _mut89705)) ? 200 : (AOR_multiply(3, decimalDigits, "org.apache.commons.math3.dfp.DfpField.DfpField_190", _mut89706, _mut89707, _mut89708, _mut89709)));
                // set up the constants at current field accuracy
                sqr2 = new Dfp(this, sqr2String);
                sqr2Split = split(sqr2String);
                sqr2Reciprocal = new Dfp(this, sqr2ReciprocalString);
                sqr3 = new Dfp(this, sqr3String);
                sqr3Reciprocal = new Dfp(this, sqr3ReciprocalString);
                pi = new Dfp(this, piString);
                piSplit = split(piString);
                e = new Dfp(this, eString);
                eSplit = split(eString);
                ln2 = new Dfp(this, ln2String);
                ln2Split = split(ln2String);
                ln5 = new Dfp(this, ln5String);
                ln5Split = split(ln5String);
                ln10 = new Dfp(this, ln10String);
            }
        } else {
            // dummy settings for unused constants
            sqr2 = null;
            sqr2Split = null;
            sqr2Reciprocal = null;
            sqr3 = null;
            sqr3Reciprocal = null;
            pi = null;
            piSplit = null;
            e = null;
            eSplit = null;
            ln2 = null;
            ln2Split = null;
            ln5 = null;
            ln5Split = null;
            ln10 = null;
        }
    }

    /**
     * Get the number of radix digits of the {@link Dfp} instances built by this factory.
     * @return number of radix digits
     */
    public int getRadixDigits() {
        return radixDigits;
    }

    /**
     * Set the rounding mode.
     *  If not set, the default value is {@link RoundingMode#ROUND_HALF_EVEN}.
     * @param mode desired rounding mode
     * Note that the rounding mode is common to all {@link Dfp} instances
     * belonging to the current {@link DfpField} in the system and will
     * affect all future calculations.
     */
    public void setRoundingMode(final RoundingMode mode) {
        rMode = mode;
    }

    /**
     * Get the current rounding mode.
     * @return current rounding mode
     */
    public RoundingMode getRoundingMode() {
        return rMode;
    }

    /**
     * Get the IEEE 854 status flags.
     * @return IEEE 854 status flags
     * @see #clearIEEEFlags()
     * @see #setIEEEFlags(int)
     * @see #setIEEEFlagsBits(int)
     * @see #FLAG_INVALID
     * @see #FLAG_DIV_ZERO
     * @see #FLAG_OVERFLOW
     * @see #FLAG_UNDERFLOW
     * @see #FLAG_INEXACT
     */
    public int getIEEEFlags() {
        return ieeeFlags;
    }

    /**
     * Clears the IEEE 854 status flags.
     * @see #getIEEEFlags()
     * @see #setIEEEFlags(int)
     * @see #setIEEEFlagsBits(int)
     * @see #FLAG_INVALID
     * @see #FLAG_DIV_ZERO
     * @see #FLAG_OVERFLOW
     * @see #FLAG_UNDERFLOW
     * @see #FLAG_INEXACT
     */
    public void clearIEEEFlags() {
        ieeeFlags = 0;
    }

    /**
     * Sets the IEEE 854 status flags.
     * @param flags desired value for the flags
     * @see #getIEEEFlags()
     * @see #clearIEEEFlags()
     * @see #setIEEEFlagsBits(int)
     * @see #FLAG_INVALID
     * @see #FLAG_DIV_ZERO
     * @see #FLAG_OVERFLOW
     * @see #FLAG_UNDERFLOW
     * @see #FLAG_INEXACT
     */
    public void setIEEEFlags(final int flags) {
        ieeeFlags = flags & (FLAG_INVALID | FLAG_DIV_ZERO | FLAG_OVERFLOW | FLAG_UNDERFLOW | FLAG_INEXACT);
    }

    /**
     * Sets some bits in the IEEE 854 status flags, without changing the already set bits.
     * <p>
     * Calling this method is equivalent to call {@code setIEEEFlags(getIEEEFlags() | bits)}
     * </p>
     * @param bits bits to set
     * @see #getIEEEFlags()
     * @see #clearIEEEFlags()
     * @see #setIEEEFlags(int)
     * @see #FLAG_INVALID
     * @see #FLAG_DIV_ZERO
     * @see #FLAG_OVERFLOW
     * @see #FLAG_UNDERFLOW
     * @see #FLAG_INEXACT
     */
    public void setIEEEFlagsBits(final int bits) {
        ieeeFlags |= bits & (FLAG_INVALID | FLAG_DIV_ZERO | FLAG_OVERFLOW | FLAG_UNDERFLOW | FLAG_INEXACT);
    }

    /**
     * Makes a {@link Dfp} with a value of 0.
     * @return a new {@link Dfp} with a value of 0
     */
    public Dfp newDfp() {
        return new Dfp(this);
    }

    /**
     * Create an instance from a byte value.
     * @param x value to convert to an instance
     * @return a new {@link Dfp} with the same value as x
     */
    public Dfp newDfp(final byte x) {
        return new Dfp(this, x);
    }

    /**
     * Create an instance from an int value.
     * @param x value to convert to an instance
     * @return a new {@link Dfp} with the same value as x
     */
    public Dfp newDfp(final int x) {
        return new Dfp(this, x);
    }

    /**
     * Create an instance from a long value.
     * @param x value to convert to an instance
     * @return a new {@link Dfp} with the same value as x
     */
    public Dfp newDfp(final long x) {
        return new Dfp(this, x);
    }

    /**
     * Create an instance from a double value.
     * @param x value to convert to an instance
     * @return a new {@link Dfp} with the same value as x
     */
    public Dfp newDfp(final double x) {
        return new Dfp(this, x);
    }

    /**
     * Copy constructor.
     * @param d instance to copy
     * @return a new {@link Dfp} with the same value as d
     */
    public Dfp newDfp(Dfp d) {
        return new Dfp(d);
    }

    /**
     * Create a {@link Dfp} given a String representation.
     * @param s string representation of the instance
     * @return a new {@link Dfp} parsed from specified string
     */
    public Dfp newDfp(final String s) {
        return new Dfp(this, s);
    }

    /**
     * Creates a {@link Dfp} with a non-finite value.
     * @param sign sign of the Dfp to create
     * @param nans code of the value, must be one of {@link Dfp#INFINITE},
     * {@link Dfp#SNAN},  {@link Dfp#QNAN}
     * @return a new {@link Dfp} with a non-finite value
     */
    public Dfp newDfp(final byte sign, final byte nans) {
        return new Dfp(this, sign, nans);
    }

    /**
     * Get the constant 0.
     * @return a {@link Dfp} with value 0
     */
    public Dfp getZero() {
        return zero;
    }

    /**
     * Get the constant 1.
     * @return a {@link Dfp} with value 1
     */
    public Dfp getOne() {
        return one;
    }

    /**
     * {@inheritDoc}
     */
    public Class<? extends FieldElement<Dfp>> getRuntimeClass() {
        return Dfp.class;
    }

    /**
     * Get the constant 2.
     * @return a {@link Dfp} with value 2
     */
    public Dfp getTwo() {
        return two;
    }

    /**
     * Get the constant &radic;2.
     * @return a {@link Dfp} with value &radic;2
     */
    public Dfp getSqr2() {
        return sqr2;
    }

    /**
     * Get the constant &radic;2 split in two pieces.
     * @return a {@link Dfp} with value &radic;2 split in two pieces
     */
    public Dfp[] getSqr2Split() {
        return sqr2Split.clone();
    }

    /**
     * Get the constant &radic;2 / 2.
     * @return a {@link Dfp} with value &radic;2 / 2
     */
    public Dfp getSqr2Reciprocal() {
        return sqr2Reciprocal;
    }

    /**
     * Get the constant &radic;3.
     * @return a {@link Dfp} with value &radic;3
     */
    public Dfp getSqr3() {
        return sqr3;
    }

    /**
     * Get the constant &radic;3 / 3.
     * @return a {@link Dfp} with value &radic;3 / 3
     */
    public Dfp getSqr3Reciprocal() {
        return sqr3Reciprocal;
    }

    /**
     * Get the constant &pi;.
     * @return a {@link Dfp} with value &pi;
     */
    public Dfp getPi() {
        return pi;
    }

    /**
     * Get the constant &pi; split in two pieces.
     * @return a {@link Dfp} with value &pi; split in two pieces
     */
    public Dfp[] getPiSplit() {
        return piSplit.clone();
    }

    /**
     * Get the constant e.
     * @return a {@link Dfp} with value e
     */
    public Dfp getE() {
        return e;
    }

    /**
     * Get the constant e split in two pieces.
     * @return a {@link Dfp} with value e split in two pieces
     */
    public Dfp[] getESplit() {
        return eSplit.clone();
    }

    /**
     * Get the constant ln(2).
     * @return a {@link Dfp} with value ln(2)
     */
    public Dfp getLn2() {
        return ln2;
    }

    /**
     * Get the constant ln(2) split in two pieces.
     * @return a {@link Dfp} with value ln(2) split in two pieces
     */
    public Dfp[] getLn2Split() {
        return ln2Split.clone();
    }

    /**
     * Get the constant ln(5).
     * @return a {@link Dfp} with value ln(5)
     */
    public Dfp getLn5() {
        return ln5;
    }

    /**
     * Get the constant ln(5) split in two pieces.
     * @return a {@link Dfp} with value ln(5) split in two pieces
     */
    public Dfp[] getLn5Split() {
        return ln5Split.clone();
    }

    /**
     * Get the constant ln(10).
     * @return a {@link Dfp} with value ln(10)
     */
    public Dfp getLn10() {
        return ln10;
    }

    /**
     * Breaks a string representation up into two {@link Dfp}'s.
     * The split is such that the sum of them is equivalent to the input string,
     * but has higher precision than using a single Dfp.
     * @param a string representation of the number to split
     * @return an array of two {@link Dfp Dfp} instances which sum equals a
     */
    private Dfp[] split(final String a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.split_528");
        Dfp[] result = new Dfp[2];
        boolean leading = true;
        int sp = 0;
        int sig = 0;
        char[] buf = new char[a.length()];
        for (int i = 0; ROR_less(i, buf.length, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89754, _mut89755, _mut89756, _mut89757, _mut89758); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.split_528");
            buf[i] = a.charAt(i);
            if ((_mut89720 ? (ROR_greater_equals(buf[i], '1', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89710, _mut89711, _mut89712, _mut89713, _mut89714) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89715, _mut89716, _mut89717, _mut89718, _mut89719)) : (ROR_greater_equals(buf[i], '1', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89710, _mut89711, _mut89712, _mut89713, _mut89714) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89715, _mut89716, _mut89717, _mut89718, _mut89719)))) {
                leading = false;
            }
            if (buf[i] == '.') {
                sig += AOR_remainder((AOR_minus(400, sig, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89721, _mut89722, _mut89723, _mut89724)), 4, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89725, _mut89726, _mut89727, _mut89728);
                leading = false;
            }
            if (ROR_equals(sig, AOR_multiply((AOR_divide(radixDigits, 2, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89729, _mut89730, _mut89731, _mut89732)), 4, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89733, _mut89734, _mut89735, _mut89736), "org.apache.commons.math3.dfp.DfpField.split_528", _mut89737, _mut89738, _mut89739, _mut89740, _mut89741)) {
                sp = i;
                break;
            }
            if ((_mut89753 ? ((_mut89752 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89742, _mut89743, _mut89744, _mut89745, _mut89746) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89747, _mut89748, _mut89749, _mut89750, _mut89751)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89742, _mut89743, _mut89744, _mut89745, _mut89746) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89747, _mut89748, _mut89749, _mut89750, _mut89751))) || !leading) : ((_mut89752 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89742, _mut89743, _mut89744, _mut89745, _mut89746) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89747, _mut89748, _mut89749, _mut89750, _mut89751)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89742, _mut89743, _mut89744, _mut89745, _mut89746) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89747, _mut89748, _mut89749, _mut89750, _mut89751))) && !leading))) {
                sig++;
            }
        }
        result[0] = new Dfp(this, new String(buf, 0, sp));
        for (int i = 0; ROR_less(i, buf.length, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89776, _mut89777, _mut89778, _mut89779, _mut89780); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.split_528");
            buf[i] = a.charAt(i);
            if ((_mut89775 ? ((_mut89769 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89759, _mut89760, _mut89761, _mut89762, _mut89763) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89764, _mut89765, _mut89766, _mut89767, _mut89768)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89759, _mut89760, _mut89761, _mut89762, _mut89763) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89764, _mut89765, _mut89766, _mut89767, _mut89768))) || ROR_less(i, sp, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89770, _mut89771, _mut89772, _mut89773, _mut89774)) : ((_mut89769 ? (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89759, _mut89760, _mut89761, _mut89762, _mut89763) || ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89764, _mut89765, _mut89766, _mut89767, _mut89768)) : (ROR_greater_equals(buf[i], '0', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89759, _mut89760, _mut89761, _mut89762, _mut89763) && ROR_less_equals(buf[i], '9', "org.apache.commons.math3.dfp.DfpField.split_528", _mut89764, _mut89765, _mut89766, _mut89767, _mut89768))) && ROR_less(i, sp, "org.apache.commons.math3.dfp.DfpField.split_528", _mut89770, _mut89771, _mut89772, _mut89773, _mut89774)))) {
                buf[i] = '0';
            }
        }
        result[1] = new Dfp(this, new String(buf));
        return result;
    }

    /**
     * Recompute the high precision string constants.
     * @param highPrecisionDecimalDigits precision at which the string constants mus be computed
     */
    private static void computeStringConstants(final int highPrecisionDecimalDigits) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computeStringConstants_576");
        if ((_mut89790 ? (sqr2String == null && ROR_less(sqr2String.length(), AOR_minus(highPrecisionDecimalDigits, 3, "org.apache.commons.math3.dfp.DfpField.computeStringConstants_576", _mut89781, _mut89782, _mut89783, _mut89784), "org.apache.commons.math3.dfp.DfpField.computeStringConstants_576", _mut89785, _mut89786, _mut89787, _mut89788, _mut89789)) : (sqr2String == null || ROR_less(sqr2String.length(), AOR_minus(highPrecisionDecimalDigits, 3, "org.apache.commons.math3.dfp.DfpField.computeStringConstants_576", _mut89781, _mut89782, _mut89783, _mut89784), "org.apache.commons.math3.dfp.DfpField.computeStringConstants_576", _mut89785, _mut89786, _mut89787, _mut89788, _mut89789)))) {
            // recompute the string representation of the transcendental constants
            final DfpField highPrecisionField = new DfpField(highPrecisionDecimalDigits, false);
            final Dfp highPrecisionOne = new Dfp(highPrecisionField, 1);
            final Dfp highPrecisionTwo = new Dfp(highPrecisionField, 2);
            final Dfp highPrecisionThree = new Dfp(highPrecisionField, 3);
            final Dfp highPrecisionSqr2 = highPrecisionTwo.sqrt();
            sqr2String = highPrecisionSqr2.toString();
            sqr2ReciprocalString = highPrecisionOne.divide(highPrecisionSqr2).toString();
            final Dfp highPrecisionSqr3 = highPrecisionThree.sqrt();
            sqr3String = highPrecisionSqr3.toString();
            sqr3ReciprocalString = highPrecisionOne.divide(highPrecisionSqr3).toString();
            piString = computePi(highPrecisionOne, highPrecisionTwo, highPrecisionThree).toString();
            eString = computeExp(highPrecisionOne, highPrecisionOne).toString();
            ln2String = computeLn(highPrecisionTwo, highPrecisionOne, highPrecisionTwo).toString();
            ln5String = computeLn(new Dfp(highPrecisionField, 5), highPrecisionOne, highPrecisionTwo).toString();
            ln10String = computeLn(new Dfp(highPrecisionField, 10), highPrecisionOne, highPrecisionTwo).toString();
        }
    }

    /**
     * Compute &pi; using Jonathan and Peter Borwein quartic formula.
     * @param one constant with value 1 at desired precision
     * @param two constant with value 2 at desired precision
     * @param three constant with value 3 at desired precision
     * @return &pi;
     */
    private static Dfp computePi(final Dfp one, final Dfp two, final Dfp three) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computePi_608");
        Dfp sqrt2 = two.sqrt();
        Dfp yk = sqrt2.subtract(one);
        Dfp four = two.add(two);
        Dfp two2kp3 = two;
        Dfp ak = two.multiply(three.subtract(two.multiply(sqrt2)));
        // So the limit here is considered sufficient for most purposes ...
        for (int i = 1; ROR_less(i, 20, "org.apache.commons.math3.dfp.DfpField.computePi_608", _mut89791, _mut89792, _mut89793, _mut89794, _mut89795); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computePi_608");
            final Dfp ykM1 = yk;
            final Dfp y2 = yk.multiply(yk);
            final Dfp oneMinusY4 = one.subtract(y2.multiply(y2));
            final Dfp s = oneMinusY4.sqrt().sqrt();
            yk = one.subtract(s).divide(one.add(s));
            two2kp3 = two2kp3.multiply(four);
            final Dfp p = one.add(yk);
            final Dfp p2 = p.multiply(p);
            ak = ak.multiply(p2.multiply(p2)).subtract(two2kp3.multiply(yk).multiply(one.add(yk).add(yk.multiply(yk))));
            if (yk.equals(ykM1)) {
                break;
            }
        }
        return one.divide(ak);
    }

    /**
     * Compute exp(a).
     * @param a number for which we want the exponential
     * @param one constant with value 1 at desired precision
     * @return exp(a)
     */
    public static Dfp computeExp(final Dfp a, final Dfp one) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computeExp_650");
        Dfp y = new Dfp(one);
        Dfp py = new Dfp(one);
        Dfp f = new Dfp(one);
        Dfp fi = new Dfp(one);
        Dfp x = new Dfp(one);
        for (int i = 0; ROR_less(i, 10000, "org.apache.commons.math3.dfp.DfpField.computeExp_650", _mut89796, _mut89797, _mut89798, _mut89799, _mut89800); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computeExp_650");
            x = x.multiply(a);
            y = y.add(x.divide(f));
            fi = fi.add(one);
            f = f.multiply(fi);
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y;
    }

    public static Dfp computeLn(final Dfp a, final Dfp one, final Dfp two) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computeLn_733");
        int den = 1;
        Dfp x = a.add(new Dfp(a.getField(), -1)).divide(a.add(one));
        Dfp y = new Dfp(x);
        Dfp num = new Dfp(x);
        Dfp py = new Dfp(y);
        for (int i = 0; ROR_less(i, 10000, "org.apache.commons.math3.dfp.DfpField.computeLn_733", _mut89801, _mut89802, _mut89803, _mut89804, _mut89805); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpField.computeLn_733");
            num = num.multiply(x);
            num = num.multiply(x);
            den += 2;
            Dfp t = num.divide(den);
            y = y.add(t);
            if (y.equals(py)) {
                break;
            }
            py = new Dfp(y);
        }
        return y.multiply(two);
    }
}
