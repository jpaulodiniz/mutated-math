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

import java.math.BigDecimal;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utilities for comparing numbers.
 *
 * @since 3.0
 */
public class Precision {

    @Conditional
    public static boolean _mut50412 = false, _mut50413 = false, _mut50414 = false, _mut50415 = false, _mut50416 = false, _mut50417 = false, _mut50418 = false, _mut50419 = false, _mut50420 = false, _mut50421 = false, _mut50422 = false, _mut50423 = false, _mut50424 = false, _mut50425 = false, _mut50426 = false, _mut50427 = false, _mut50428 = false, _mut50429 = false, _mut50430 = false, _mut50431 = false, _mut50432 = false, _mut50433 = false, _mut50434 = false, _mut50435 = false, _mut50436 = false, _mut50437 = false, _mut50438 = false, _mut50439 = false, _mut50440 = false, _mut50441 = false, _mut50442 = false, _mut50443 = false, _mut50444 = false, _mut50445 = false, _mut50446 = false, _mut50447 = false, _mut50448 = false, _mut50449 = false, _mut50450 = false, _mut50451 = false, _mut50452 = false, _mut50453 = false, _mut50454 = false, _mut50455 = false, _mut50456 = false, _mut50457 = false, _mut50458 = false, _mut50459 = false, _mut50460 = false, _mut50461 = false, _mut50462 = false, _mut50463 = false, _mut50464 = false, _mut50465 = false, _mut50466 = false, _mut50467 = false, _mut50468 = false, _mut50469 = false, _mut50470 = false, _mut50471 = false, _mut50472 = false, _mut50473 = false, _mut50474 = false, _mut50475 = false, _mut50476 = false, _mut50477 = false, _mut50478 = false, _mut50479 = false, _mut50480 = false, _mut50481 = false, _mut50482 = false, _mut50483 = false, _mut50484 = false, _mut50485 = false, _mut50486 = false, _mut50487 = false, _mut50488 = false, _mut50489 = false, _mut50490 = false, _mut50491 = false, _mut50492 = false, _mut50493 = false, _mut50494 = false, _mut50495 = false, _mut50496 = false, _mut50497 = false, _mut50498 = false, _mut50499 = false, _mut50500 = false, _mut50501 = false, _mut50502 = false, _mut50503 = false, _mut50504 = false, _mut50505 = false, _mut50506 = false, _mut50507 = false, _mut50508 = false, _mut50509 = false, _mut50510 = false, _mut50511 = false, _mut50512 = false, _mut50513 = false, _mut50514 = false, _mut50515 = false, _mut50516 = false, _mut50517 = false, _mut50518 = false, _mut50519 = false, _mut50520 = false, _mut50521 = false, _mut50522 = false, _mut50523 = false, _mut50524 = false, _mut50525 = false, _mut50526 = false, _mut50527 = false, _mut50528 = false, _mut50529 = false, _mut50530 = false, _mut50531 = false, _mut50532 = false, _mut50533 = false, _mut50534 = false, _mut50535 = false, _mut50536 = false, _mut50537 = false, _mut50538 = false, _mut50539 = false, _mut50540 = false, _mut50541 = false, _mut50542 = false, _mut50543 = false, _mut50544 = false, _mut50545 = false, _mut50546 = false, _mut50547 = false, _mut50548 = false, _mut50549 = false, _mut50550 = false, _mut50551 = false, _mut50552 = false, _mut50553 = false, _mut50554 = false, _mut50555 = false, _mut50556 = false, _mut50557 = false, _mut50558 = false, _mut50559 = false, _mut50560 = false, _mut50561 = false, _mut50562 = false, _mut50563 = false, _mut50564 = false, _mut50565 = false, _mut50566 = false, _mut50567 = false, _mut50568 = false, _mut50569 = false, _mut50570 = false, _mut50571 = false, _mut50572 = false, _mut50573 = false, _mut50574 = false, _mut50575 = false, _mut50576 = false, _mut50577 = false, _mut50578 = false, _mut50579 = false, _mut50580 = false, _mut50581 = false, _mut50582 = false, _mut50583 = false, _mut50584 = false, _mut50585 = false, _mut50586 = false, _mut50587 = false, _mut50588 = false, _mut50589 = false, _mut50590 = false, _mut50591 = false, _mut50592 = false, _mut50593 = false, _mut50594 = false, _mut50595 = false, _mut50596 = false, _mut50597 = false, _mut50598 = false, _mut50599 = false, _mut50600 = false, _mut50601 = false, _mut50602 = false, _mut50603 = false, _mut50604 = false, _mut50605 = false, _mut50606 = false, _mut50607 = false, _mut50608 = false, _mut50609 = false, _mut50610 = false, _mut50611 = false, _mut50612 = false, _mut50613 = false, _mut50614 = false, _mut50615 = false, _mut50616 = false, _mut50617 = false, _mut50618 = false, _mut50619 = false, _mut50620 = false, _mut50621 = false, _mut50622 = false, _mut50623 = false, _mut50624 = false, _mut50625 = false, _mut50626 = false, _mut50627 = false, _mut50628 = false, _mut50629 = false, _mut50630 = false, _mut50631 = false, _mut50632 = false, _mut50633 = false, _mut50634 = false, _mut50635 = false, _mut50636 = false, _mut50637 = false, _mut50638 = false, _mut50639 = false, _mut50640 = false, _mut50641 = false, _mut50642 = false, _mut50643 = false, _mut50644 = false, _mut50645 = false, _mut50646 = false, _mut50647 = false, _mut50648 = false, _mut50649 = false, _mut50650 = false, _mut50651 = false, _mut50652 = false, _mut50653 = false, _mut50654 = false, _mut50655 = false, _mut50656 = false, _mut50657 = false, _mut50658 = false, _mut50659 = false, _mut50660 = false, _mut50661 = false, _mut50662 = false, _mut50663 = false, _mut50664 = false, _mut50665 = false, _mut50666 = false, _mut50667 = false, _mut50668 = false, _mut50669 = false, _mut50670 = false, _mut50671 = false, _mut50672 = false, _mut50673 = false, _mut50674 = false, _mut50675 = false, _mut50676 = false, _mut50677 = false, _mut50678 = false, _mut50679 = false, _mut50680 = false, _mut50681 = false, _mut50682 = false, _mut50683 = false, _mut50684 = false, _mut50685 = false, _mut50686 = false, _mut50687 = false, _mut50688 = false, _mut50689 = false, _mut50690 = false, _mut50691 = false, _mut50692 = false, _mut50693 = false, _mut50694 = false, _mut50695 = false, _mut50696 = false, _mut50697 = false, _mut50698 = false, _mut50699 = false, _mut50700 = false, _mut50701 = false, _mut50702 = false, _mut50703 = false, _mut50704 = false, _mut50705 = false, _mut50706 = false, _mut50707 = false, _mut50708 = false, _mut50709 = false, _mut50710 = false, _mut50711 = false, _mut50712 = false, _mut50713 = false, _mut50714 = false, _mut50715 = false, _mut50716 = false, _mut50717 = false, _mut50718 = false, _mut50719 = false, _mut50720 = false, _mut50721 = false, _mut50722 = false, _mut50723 = false, _mut50724 = false, _mut50725 = false, _mut50726 = false, _mut50727 = false, _mut50728 = false, _mut50729 = false, _mut50730 = false, _mut50731 = false, _mut50732 = false, _mut50733 = false, _mut50734 = false, _mut50735 = false, _mut50736 = false, _mut50737 = false, _mut50738 = false, _mut50739 = false, _mut50740 = false, _mut50741 = false, _mut50742 = false, _mut50743 = false, _mut50744 = false, _mut50745 = false, _mut50746 = false, _mut50747 = false, _mut50748 = false, _mut50749 = false, _mut50750 = false, _mut50751 = false, _mut50752 = false, _mut50753 = false, _mut50754 = false, _mut50755 = false, _mut50756 = false, _mut50757 = false, _mut50758 = false, _mut50759 = false, _mut50760 = false, _mut50761 = false, _mut50762 = false;

    /**
     * <p>
     * Largest double-precision floating-point number such that
     * {@code 1 + EPSILON} is numerically equal to 1. This value is an upper
     * bound on the relative error due to rounding real numbers to double
     * precision floating-point numbers.
     * </p>
     * <p>
     * In IEEE 754 arithmetic, this is 2<sup>-53</sup>.
     * </p>
     *
     * @see <a href="http://en.wikipedia.org/wiki/Machine_epsilon">Machine epsilon</a>
     */
    public static final double EPSILON;

    /**
     * Safe minimum, such that {@code 1 / SAFE_MIN} does not overflow.
     * <br/>
     * In IEEE 754 arithmetic, this is also the smallest normalized
     * number 2<sup>-1022</sup>.
     */
    public static final double SAFE_MIN;

    /**
     * Exponent offset in IEEE754 representation.
     */
    private static final long EXPONENT_OFFSET = 1023l;

    /**
     * Offset to order signed double numbers lexicographically.
     */
    private static final long SGN_MASK = 0x8000000000000000L;

    /**
     * Offset to order signed double numbers lexicographically.
     */
    private static final int SGN_MASK_FLOAT = 0x80000000;

    /**
     * Positive zero.
     */
    private static final double POSITIVE_ZERO = 0d;

    /**
     * Positive zero bits.
     */
    private static final long POSITIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(+0.0);

    /**
     * Negative zero bits.
     */
    private static final long NEGATIVE_ZERO_DOUBLE_BITS = Double.doubleToRawLongBits(-0.0);

    /**
     * Positive zero bits.
     */
    private static final int POSITIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(+0.0f);

    /**
     * Negative zero bits.
     */
    private static final int NEGATIVE_ZERO_FLOAT_BITS = Float.floatToRawIntBits(-0.0f);

    static {
        /*
         *  This was previously expressed as = 0x1.0p-53;
         *  However, OpenJDK (Sparc Solaris) cannot handle such small
         *  constants: MATH-721
         */
        EPSILON = Double.longBitsToDouble((AOR_minus(EXPONENT_OFFSET, 53l, "org.apache.commons.math3.util.Precision.remove_358", _mut50412, _mut50413, _mut50414, _mut50415)) << 52);
        /*
         * This was previously expressed as = 0x1.0p-1022;
         * However, OpenJDK (Sparc Solaris) cannot handle such small
         * constants: MATH-721
         */
        SAFE_MIN = Double.longBitsToDouble((AOR_minus(EXPONENT_OFFSET, 1022l, "org.apache.commons.math3.util.Precision.remove_358", _mut50416, _mut50417, _mut50418, _mut50419)) << 52);
    }

    /**
     * Private constructor.
     */
    private Precision() {
    }

    /**
     * Compares two numbers given some amount of allowed error.
     *
     * @param x the first number
     * @param y the second number
     * @param eps the amount of error to allow when checking for equality
     * @return <ul><li>0 if  {@link #equals(double, double, double) equals(x, y, eps)}</li>
     *       <li>&lt; 0 if !{@link #equals(double, double, double) equals(x, y, eps)} &amp;&amp; x &lt; y</li>
     *       <li>> 0 if !{@link #equals(double, double, double) equals(x, y, eps)} &amp;&amp; x > y or
     *       either argument is NaN</li></ul>
     */
    public static int compareTo(double x, double y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.compareTo_105");
        if (equals(x, y, eps)) {
            return 0;
        } else if (ROR_less(x, y, "org.apache.commons.math3.util.Precision.compareTo_105", _mut50420, _mut50421, _mut50422, _mut50423, _mut50424)) {
            return -1;
        }
        return 1;
    }

    /**
     * Compares two numbers given some amount of allowed error.
     * Two float numbers are considered equal if there are {@code (maxUlps - 1)}
     * (or fewer) floating point numbers between them, i.e. two adjacent floating
     * point numbers are considered equal.
     * Adapted from <a
     * href="http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">
     * Bruce Dawson</a>. Returns {@code false} if either of the arguments is NaN.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return <ul><li>0 if  {@link #equals(double, double, int) equals(x, y, maxUlps)}</li>
     *       <li>&lt; 0 if !{@link #equals(double, double, int) equals(x, y, maxUlps)} &amp;&amp; x &lt; y</li>
     *       <li>&gt; 0 if !{@link #equals(double, double, int) equals(x, y, maxUlps)} &amp;&amp; x > y
     *       or either argument is NaN</li></ul>
     */
    public static int compareTo(final double x, final double y, final int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.compareTo_132");
        if (equals(x, y, maxUlps)) {
            return 0;
        } else if (ROR_less(x, y, "org.apache.commons.math3.util.Precision.compareTo_132", _mut50425, _mut50426, _mut50427, _mut50428, _mut50429)) {
            return -1;
        }
        return 1;
    }

    /**
     * Returns true iff they are equal as defined by
     * {@link #equals(float,float,int) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal.
     */
    public static boolean equals(float x, float y) {
        return equals(x, y, 1);
    }

    /**
     * Returns true if both arguments are NaN or they are
     * equal as defined by {@link #equals(float,float) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal or both are NaN.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(float x, float y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_162");
        return ((_mut50440 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50430, _mut50431, _mut50432, _mut50433, _mut50434) && ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50435, _mut50436, _mut50437, _mut50438, _mut50439)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50430, _mut50431, _mut50432, _mut50433, _mut50434) || ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50435, _mut50436, _mut50437, _mut50438, _mut50439)))) ? !(ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50441, _mut50442, _mut50443, _mut50444, _mut50445) ^ ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_162", _mut50446, _mut50447, _mut50448, _mut50449, _mut50450)) : equals(x, y, 1);
    }

    /**
     * Returns true if the arguments are equal or within the range of allowed
     * error (inclusive).  Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other.
     * @since 2.2
     */
    public static boolean equals(float x, float y, float eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equals_177");
        return (_mut50460 ? (equals(x, y, 1) && ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equals_177", _mut50451, _mut50452, _mut50453, _mut50454)), eps, "org.apache.commons.math3.util.Precision.equals_177", _mut50455, _mut50456, _mut50457, _mut50458, _mut50459)) : (equals(x, y, 1) || ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equals_177", _mut50451, _mut50452, _mut50453, _mut50454)), eps, "org.apache.commons.math3.util.Precision.equals_177", _mut50455, _mut50456, _mut50457, _mut50458, _mut50459)));
    }

    /**
     * Returns true if the arguments are both NaN, are equal, or are within the range
     * of allowed error (inclusive).
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other,
     * or both are NaN.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(float x, float y, float eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_192");
        return (_mut50470 ? (equalsIncludingNaN(x, y) && (ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_192", _mut50461, _mut50462, _mut50463, _mut50464)), eps, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_192", _mut50465, _mut50466, _mut50467, _mut50468, _mut50469))) : (equalsIncludingNaN(x, y) || (ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_192", _mut50461, _mut50462, _mut50463, _mut50464)), eps, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_192", _mut50465, _mut50466, _mut50467, _mut50468, _mut50469))));
    }

    /**
     * Returns true if the arguments are equal or within the range of allowed
     * error (inclusive).
     * Two float numbers are considered equal if there are {@code (maxUlps - 1)}
     * (or fewer) floating point numbers between them, i.e. two adjacent floating
     * point numbers are considered equal.
     * Adapted from <a
     * href="http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">
     * Bruce Dawson</a>.  Returns {@code false} if either of the arguments is NaN.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if there are fewer than {@code maxUlps} floating
     * point values between {@code x} and {@code y}.
     * @since 2.2
     */
    public static boolean equals(final float x, final float y, final int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equals_214");
        final int xInt = Float.floatToRawIntBits(x);
        final int yInt = Float.floatToRawIntBits(y);
        final boolean isEqual;
        if (ROR_equals(((xInt ^ yInt) & SGN_MASK_FLOAT), 0, "org.apache.commons.math3.util.Precision.equals_214", _mut50471, _mut50472, _mut50473, _mut50474, _mut50475)) {
            // number have same sign, there is no risk of overflow
            isEqual = ROR_less_equals(FastMath.abs(AOR_minus(xInt, yInt, "org.apache.commons.math3.util.Precision.equals_214", _mut50511, _mut50512, _mut50513, _mut50514)), maxUlps, "org.apache.commons.math3.util.Precision.equals_214", _mut50515, _mut50516, _mut50517, _mut50518, _mut50519);
        } else {
            // number have opposite signs, take care of overflow
            final int deltaPlus;
            final int deltaMinus;
            if (ROR_less(xInt, yInt, "org.apache.commons.math3.util.Precision.equals_214", _mut50476, _mut50477, _mut50478, _mut50479, _mut50480)) {
                deltaPlus = AOR_minus(yInt, POSITIVE_ZERO_FLOAT_BITS, "org.apache.commons.math3.util.Precision.equals_214", _mut50489, _mut50490, _mut50491, _mut50492);
                deltaMinus = AOR_minus(xInt, NEGATIVE_ZERO_FLOAT_BITS, "org.apache.commons.math3.util.Precision.equals_214", _mut50493, _mut50494, _mut50495, _mut50496);
            } else {
                deltaPlus = AOR_minus(xInt, POSITIVE_ZERO_FLOAT_BITS, "org.apache.commons.math3.util.Precision.equals_214", _mut50481, _mut50482, _mut50483, _mut50484);
                deltaMinus = AOR_minus(yInt, NEGATIVE_ZERO_FLOAT_BITS, "org.apache.commons.math3.util.Precision.equals_214", _mut50485, _mut50486, _mut50487, _mut50488);
            }
            if (ROR_greater(deltaPlus, maxUlps, "org.apache.commons.math3.util.Precision.equals_214", _mut50497, _mut50498, _mut50499, _mut50500, _mut50501)) {
                isEqual = false;
            } else {
                isEqual = ROR_less_equals(deltaMinus, (AOR_minus(maxUlps, deltaPlus, "org.apache.commons.math3.util.Precision.equals_214", _mut50502, _mut50503, _mut50504, _mut50505)), "org.apache.commons.math3.util.Precision.equals_214", _mut50506, _mut50507, _mut50508, _mut50509, _mut50510);
            }
        }
        return (_mut50521 ? ((_mut50520 ? (isEqual || !Float.isNaN(x)) : (isEqual && !Float.isNaN(x))) || !Float.isNaN(y)) : ((_mut50520 ? (isEqual || !Float.isNaN(x)) : (isEqual && !Float.isNaN(x))) && !Float.isNaN(y)));
    }

    /**
     * Returns true if the arguments are both NaN or if they are equal as defined
     * by {@link #equals(float,float,int) equals(x, y, maxUlps)}.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if both arguments are NaN or if there are less than
     * {@code maxUlps} floating point values between {@code x} and {@code y}.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(float x, float y, int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_259");
        return ((_mut50532 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50522, _mut50523, _mut50524, _mut50525, _mut50526) && ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50527, _mut50528, _mut50529, _mut50530, _mut50531)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50522, _mut50523, _mut50524, _mut50525, _mut50526) || ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50527, _mut50528, _mut50529, _mut50530, _mut50531)))) ? !(ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50533, _mut50534, _mut50535, _mut50536, _mut50537) ^ ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_259", _mut50538, _mut50539, _mut50540, _mut50541, _mut50542)) : equals(x, y, maxUlps);
    }

    /**
     * Returns true iff they are equal as defined by
     * {@link #equals(double,double,int) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal.
     */
    public static boolean equals(double x, double y) {
        return equals(x, y, 1);
    }

    /**
     * Returns true if the arguments are both NaN or they are
     * equal as defined by {@link #equals(double,double) equals(x, y, 1)}.
     *
     * @param x first value
     * @param y second value
     * @return {@code true} if the values are equal or both are NaN.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(double x, double y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_284");
        return ((_mut50553 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50543, _mut50544, _mut50545, _mut50546, _mut50547) && ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50548, _mut50549, _mut50550, _mut50551, _mut50552)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50543, _mut50544, _mut50545, _mut50546, _mut50547) || ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50548, _mut50549, _mut50550, _mut50551, _mut50552)))) ? !(ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50554, _mut50555, _mut50556, _mut50557, _mut50558) ^ ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_284", _mut50559, _mut50560, _mut50561, _mut50562, _mut50563)) : equals(x, y, 1);
    }

    /**
     * Returns {@code true} if there is no double value strictly between the
     * arguments or the difference between them is within the range of allowed
     * error (inclusive). Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x First value.
     * @param y Second value.
     * @param eps Amount of allowed absolute error.
     * @return {@code true} if the values are two adjacent floating point
     * numbers or they are within range of each other.
     */
    public static boolean equals(double x, double y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equals_300");
        return (_mut50573 ? (equals(x, y, 1) && ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equals_300", _mut50564, _mut50565, _mut50566, _mut50567)), eps, "org.apache.commons.math3.util.Precision.equals_300", _mut50568, _mut50569, _mut50570, _mut50571, _mut50572)) : (equals(x, y, 1) || ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equals_300", _mut50564, _mut50565, _mut50566, _mut50567)), eps, "org.apache.commons.math3.util.Precision.equals_300", _mut50568, _mut50569, _mut50570, _mut50571, _mut50572)));
    }

    /**
     * Returns {@code true} if there is no double value strictly between the
     * arguments or the relative difference between them is less than or equal
     * to the given tolerance. Returns {@code false} if either of the arguments
     * is NaN.
     *
     * @param x First value.
     * @param y Second value.
     * @param eps Amount of allowed relative error.
     * @return {@code true} if the values are two adjacent floating point
     * numbers or they are within range of each other.
     * @since 3.1
     */
    public static boolean equalsWithRelativeTolerance(double x, double y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsWithRelativeTolerance_317");
        if (equals(x, y, 1)) {
            return true;
        }
        final double absoluteMax = FastMath.max(FastMath.abs(x), FastMath.abs(y));
        final double relativeDifference = FastMath.abs(AOR_divide((AOR_minus(x, y, "org.apache.commons.math3.util.Precision.equalsWithRelativeTolerance_317", _mut50574, _mut50575, _mut50576, _mut50577)), absoluteMax, "org.apache.commons.math3.util.Precision.equalsWithRelativeTolerance_317", _mut50578, _mut50579, _mut50580, _mut50581));
        return ROR_less_equals(relativeDifference, eps, "org.apache.commons.math3.util.Precision.equalsWithRelativeTolerance_317", _mut50582, _mut50583, _mut50584, _mut50585, _mut50586);
    }

    /**
     * Returns true if the arguments are both NaN, are equal or are within the range
     * of allowed error (inclusive).
     *
     * @param x first value
     * @param y second value
     * @param eps the amount of absolute error to allow.
     * @return {@code true} if the values are equal or within range of each other,
     * or both are NaN.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(double x, double y, double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_339");
        return (_mut50596 ? (equalsIncludingNaN(x, y) && (ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_339", _mut50587, _mut50588, _mut50589, _mut50590)), eps, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_339", _mut50591, _mut50592, _mut50593, _mut50594, _mut50595))) : (equalsIncludingNaN(x, y) || (ROR_less_equals(FastMath.abs(AOR_minus(y, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_339", _mut50587, _mut50588, _mut50589, _mut50590)), eps, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_339", _mut50591, _mut50592, _mut50593, _mut50594, _mut50595))));
    }

    /**
     * Returns true if the arguments are equal or within the range of allowed
     * error (inclusive).
     * <p>
     * Two float numbers are considered equal if there are {@code (maxUlps - 1)}
     * (or fewer) floating point numbers between them, i.e. two adjacent
     * floating point numbers are considered equal.
     * </p>
     * <p>
     * Adapted from <a
     * href="http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/">
     * Bruce Dawson</a>. Returns {@code false} if either of the arguments is NaN.
     * </p>
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if there are fewer than {@code maxUlps} floating
     * point values between {@code x} and {@code y}.
     */
    public static boolean equals(final double x, final double y, final int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equals_364");
        final long xInt = Double.doubleToRawLongBits(x);
        final long yInt = Double.doubleToRawLongBits(y);
        final boolean isEqual;
        if (ROR_equals(((xInt ^ yInt) & SGN_MASK), 0l, "org.apache.commons.math3.util.Precision.equals_364", _mut50597, _mut50598, _mut50599, _mut50600, _mut50601)) {
            // number have same sign, there is no risk of overflow
            isEqual = ROR_less_equals(FastMath.abs(AOR_minus(xInt, yInt, "org.apache.commons.math3.util.Precision.equals_364", _mut50637, _mut50638, _mut50639, _mut50640)), maxUlps, "org.apache.commons.math3.util.Precision.equals_364", _mut50641, _mut50642, _mut50643, _mut50644, _mut50645);
        } else {
            // number have opposite signs, take care of overflow
            final long deltaPlus;
            final long deltaMinus;
            if (ROR_less(xInt, yInt, "org.apache.commons.math3.util.Precision.equals_364", _mut50602, _mut50603, _mut50604, _mut50605, _mut50606)) {
                deltaPlus = AOR_minus(yInt, POSITIVE_ZERO_DOUBLE_BITS, "org.apache.commons.math3.util.Precision.equals_364", _mut50615, _mut50616, _mut50617, _mut50618);
                deltaMinus = AOR_minus(xInt, NEGATIVE_ZERO_DOUBLE_BITS, "org.apache.commons.math3.util.Precision.equals_364", _mut50619, _mut50620, _mut50621, _mut50622);
            } else {
                deltaPlus = AOR_minus(xInt, POSITIVE_ZERO_DOUBLE_BITS, "org.apache.commons.math3.util.Precision.equals_364", _mut50607, _mut50608, _mut50609, _mut50610);
                deltaMinus = AOR_minus(yInt, NEGATIVE_ZERO_DOUBLE_BITS, "org.apache.commons.math3.util.Precision.equals_364", _mut50611, _mut50612, _mut50613, _mut50614);
            }
            if (ROR_greater(deltaPlus, maxUlps, "org.apache.commons.math3.util.Precision.equals_364", _mut50623, _mut50624, _mut50625, _mut50626, _mut50627)) {
                isEqual = false;
            } else {
                isEqual = ROR_less_equals(deltaMinus, (AOR_minus(maxUlps, deltaPlus, "org.apache.commons.math3.util.Precision.equals_364", _mut50628, _mut50629, _mut50630, _mut50631)), "org.apache.commons.math3.util.Precision.equals_364", _mut50632, _mut50633, _mut50634, _mut50635, _mut50636);
            }
        }
        return (_mut50647 ? ((_mut50646 ? (isEqual || !Double.isNaN(x)) : (isEqual && !Double.isNaN(x))) || !Double.isNaN(y)) : ((_mut50646 ? (isEqual || !Double.isNaN(x)) : (isEqual && !Double.isNaN(x))) && !Double.isNaN(y)));
    }

    /**
     * Returns true if both arguments are NaN or if they are equal as defined
     * by {@link #equals(double,double,int) equals(x, y, maxUlps)}.
     *
     * @param x first value
     * @param y second value
     * @param maxUlps {@code (maxUlps - 1)} is the number of floating point
     * values between {@code x} and {@code y}.
     * @return {@code true} if both arguments are NaN or if there are less than
     * {@code maxUlps} floating point values between {@code x} and {@code y}.
     * @since 2.2
     */
    public static boolean equalsIncludingNaN(double x, double y, int maxUlps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.equalsIncludingNaN_409");
        return ((_mut50658 ? (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50648, _mut50649, _mut50650, _mut50651, _mut50652) && ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50653, _mut50654, _mut50655, _mut50656, _mut50657)) : (ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50648, _mut50649, _mut50650, _mut50651, _mut50652) || ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50653, _mut50654, _mut50655, _mut50656, _mut50657)))) ? !(ROR_not_equals(x, x, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50659, _mut50660, _mut50661, _mut50662, _mut50663) ^ ROR_not_equals(y, y, "org.apache.commons.math3.util.Precision.equalsIncludingNaN_409", _mut50664, _mut50665, _mut50666, _mut50667, _mut50668)) : equals(x, y, maxUlps);
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using the {@link BigDecimal#ROUND_HALF_UP} method.
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @return the rounded value.
     * @since 1.1 (previously in {@code MathUtils}, moved as of version 3.0)
     */
    public static double round(double x, int scale) {
        return round(x, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using the given method which is any method defined
     * in {@link BigDecimal}.
     * If {@code x} is infinite or {@code NaN}, then the value of {@code x} is
     * returned unchanged, regardless of the other parameters.
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @param roundingMethod Rounding method as defined in {@link BigDecimal}.
     * @return the rounded value.
     * @throws ArithmeticException if {@code roundingMethod == ROUND_UNNECESSARY}
     * and the specified scaling operation would require rounding.
     * @throws IllegalArgumentException if {@code roundingMethod} does not
     * represent a valid rounding mode.
     * @since 1.1 (previously in {@code MathUtils}, moved as of version 3.0)
     */
    public static double round(double x, int scale, int roundingMethod) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.round_443");
        try {
            final double rounded = (new BigDecimal(Double.toString(x)).setScale(scale, roundingMethod)).doubleValue();
            // MATH-1089: negative values rounded to zero should result in negative zero
            return ROR_equals(rounded, POSITIVE_ZERO, "org.apache.commons.math3.util.Precision.round_443", _mut50669, _mut50670, _mut50671, _mut50672, _mut50673) ? AOR_multiply(POSITIVE_ZERO, x, "org.apache.commons.math3.util.Precision.round_443", _mut50674, _mut50675, _mut50676, _mut50677) : rounded;
        } catch (NumberFormatException ex) {
            if (Double.isInfinite(x)) {
                return x;
            } else {
                return Double.NaN;
            }
        }
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using the {@link BigDecimal#ROUND_HALF_UP} method.
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @return the rounded value.
     * @since 1.1 (previously in {@code MathUtils}, moved as of version 3.0)
     */
    public static float round(float x, int scale) {
        return round(x, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Rounds the given value to the specified number of decimal places.
     * The value is rounded using the given method which is any method defined
     * in {@link BigDecimal}.
     *
     * @param x Value to round.
     * @param scale Number of digits to the right of the decimal point.
     * @param roundingMethod Rounding method as defined in {@link BigDecimal}.
     * @return the rounded value.
     * @since 1.1 (previously in {@code MathUtils}, moved as of version 3.0)
     * @throws MathArithmeticException if an exact operation is required but result is not exact
     * @throws MathIllegalArgumentException if {@code roundingMethod} is not a valid rounding method.
     */
    public static float round(float x, int scale, int roundingMethod) throws MathArithmeticException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.round_485");
        final float sign = FastMath.copySign(1f, x);
        final float factor = AOR_multiply((float) FastMath.pow(10.0f, scale), sign, "org.apache.commons.math3.util.Precision.round_485", _mut50678, _mut50679, _mut50680, _mut50681);
        return AOR_divide((float) roundUnscaled(AOR_multiply(x, factor, "org.apache.commons.math3.util.Precision.round_485", _mut50682, _mut50683, _mut50684, _mut50685), sign, roundingMethod), factor, "org.apache.commons.math3.util.Precision.round_485", _mut50686, _mut50687, _mut50688, _mut50689);
    }

    /**
     * Rounds the given non-negative value to the "nearest" integer. Nearest is
     * determined by the rounding method specified. Rounding methods are defined
     * in {@link BigDecimal}.
     *
     * @param unscaled Value to round.
     * @param sign Sign of the original, scaled value.
     * @param roundingMethod Rounding method, as defined in {@link BigDecimal}.
     * @return the rounded value.
     * @throws MathArithmeticException if an exact operation is required but result is not exact
     * @throws MathIllegalArgumentException if {@code roundingMethod} is not a valid rounding method.
     * @since 1.1 (previously in {@code MathUtils}, moved as of version 3.0)
     */
    private static double roundUnscaled(double unscaled, double sign, int roundingMethod) throws MathArithmeticException, MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.roundUnscaled_505");
        switch(roundingMethod) {
            case BigDecimal.ROUND_CEILING:
                if (ROR_equals(sign, -1, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50690, _mut50691, _mut50692, _mut50693, _mut50694)) {
                    unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                } else {
                    unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                }
                break;
            case BigDecimal.ROUND_DOWN:
                unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                break;
            case BigDecimal.ROUND_FLOOR:
                if (ROR_equals(sign, -1, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50695, _mut50696, _mut50697, _mut50698, _mut50699)) {
                    unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                } else {
                    unscaled = FastMath.floor(FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY));
                }
                break;
            case BigDecimal.ROUND_HALF_DOWN:
                {
                    unscaled = FastMath.nextAfter(unscaled, Double.NEGATIVE_INFINITY);
                    double fraction = AOR_minus(unscaled, FastMath.floor(unscaled), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50700, _mut50701, _mut50702, _mut50703);
                    if (ROR_greater(fraction, 0.5, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50704, _mut50705, _mut50706, _mut50707, _mut50708)) {
                        unscaled = FastMath.ceil(unscaled);
                    } else {
                        unscaled = FastMath.floor(unscaled);
                    }
                    break;
                }
            case BigDecimal.ROUND_HALF_EVEN:
                {
                    double fraction = AOR_minus(unscaled, FastMath.floor(unscaled), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50709, _mut50710, _mut50711, _mut50712);
                    if (ROR_greater(fraction, 0.5, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50713, _mut50714, _mut50715, _mut50716, _mut50717)) {
                        unscaled = FastMath.ceil(unscaled);
                    } else if (ROR_less(fraction, 0.5, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50718, _mut50719, _mut50720, _mut50721, _mut50722)) {
                        unscaled = FastMath.floor(unscaled);
                    } else {
                        // The following equality test is intentional and needed for rounding purposes
                        if (ROR_equals(AOR_divide(FastMath.floor(unscaled), 2.0, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50723, _mut50724, _mut50725, _mut50726), FastMath.floor(AOR_divide(FastMath.floor(unscaled), 2.0, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50727, _mut50728, _mut50729, _mut50730)), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50731, _mut50732, _mut50733, _mut50734, _mut50735)) {
                            // even
                            unscaled = FastMath.floor(unscaled);
                        } else {
                            // odd
                            unscaled = FastMath.ceil(unscaled);
                        }
                    }
                    break;
                }
            case BigDecimal.ROUND_HALF_UP:
                {
                    unscaled = FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY);
                    double fraction = AOR_minus(unscaled, FastMath.floor(unscaled), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50736, _mut50737, _mut50738, _mut50739);
                    if (ROR_greater_equals(fraction, 0.5, "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50740, _mut50741, _mut50742, _mut50743, _mut50744)) {
                        unscaled = FastMath.ceil(unscaled);
                    } else {
                        unscaled = FastMath.floor(unscaled);
                    }
                    break;
                }
            case BigDecimal.ROUND_UNNECESSARY:
                if (ROR_not_equals(unscaled, FastMath.floor(unscaled), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50745, _mut50746, _mut50747, _mut50748, _mut50749)) {
                    throw new MathArithmeticException();
                }
                break;
            case BigDecimal.ROUND_UP:
                // do not round if the discarded fraction is equal to zero
                if (ROR_not_equals(unscaled, FastMath.floor(unscaled), "org.apache.commons.math3.util.Precision.roundUnscaled_505", _mut50750, _mut50751, _mut50752, _mut50753, _mut50754)) {
                    unscaled = FastMath.ceil(FastMath.nextAfter(unscaled, Double.POSITIVE_INFINITY));
                }
                break;
            default:
                throw new MathIllegalArgumentException(LocalizedFormats.INVALID_ROUNDING_METHOD, roundingMethod, "ROUND_CEILING", BigDecimal.ROUND_CEILING, "ROUND_DOWN", BigDecimal.ROUND_DOWN, "ROUND_FLOOR", BigDecimal.ROUND_FLOOR, "ROUND_HALF_DOWN", BigDecimal.ROUND_HALF_DOWN, "ROUND_HALF_EVEN", BigDecimal.ROUND_HALF_EVEN, "ROUND_HALF_UP", BigDecimal.ROUND_HALF_UP, "ROUND_UNNECESSARY", BigDecimal.ROUND_UNNECESSARY, "ROUND_UP", BigDecimal.ROUND_UP);
        }
        return unscaled;
    }

    /**
     * Computes a number {@code delta} close to {@code originalDelta} with
     * the property that <pre><code>
     *   x + delta - x
     * </code></pre>
     * is exactly machine-representable.
     * This is useful when computing numerical derivatives, in order to reduce
     * roundoff errors.
     *
     * @param x Value.
     * @param originalDelta Offset value.
     * @return a number {@code delta} so that {@code x + delta} and {@code x}
     * differ by a representable floating number.
     */
    public static double representableDelta(double x, double originalDelta) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Precision.representableDelta_604");
        return AOR_minus(AOR_plus(x, originalDelta, "org.apache.commons.math3.util.Precision.representableDelta_604", _mut50755, _mut50756, _mut50757, _mut50758), x, "org.apache.commons.math3.util.Precision.representableDelta_604", _mut50759, _mut50760, _mut50761, _mut50762);
    }
}
