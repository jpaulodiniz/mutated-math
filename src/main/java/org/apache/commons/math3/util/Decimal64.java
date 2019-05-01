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

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.exception.DimensionMismatchException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class wraps a {@code double} value in an object. It is similar to the
 * standard class {@link Double}, while also implementing the
 * {@link RealFieldElement} interface.
 *
 * @since 3.1
 */
public class Decimal64 extends Number implements RealFieldElement<Decimal64>, Comparable<Decimal64> {

    @Conditional
    public static boolean _mut49675 = false, _mut49676 = false, _mut49677 = false, _mut49678 = false, _mut49679 = false, _mut49680 = false, _mut49681 = false, _mut49682 = false, _mut49683 = false, _mut49684 = false, _mut49685 = false, _mut49686 = false, _mut49687 = false, _mut49688 = false, _mut49689 = false, _mut49690 = false, _mut49691 = false, _mut49692 = false, _mut49693 = false, _mut49694 = false, _mut49695 = false, _mut49696 = false, _mut49697 = false, _mut49698 = false, _mut49699 = false, _mut49700 = false, _mut49701 = false, _mut49702 = false, _mut49703 = false, _mut49704 = false, _mut49705 = false, _mut49706 = false, _mut49707 = false, _mut49708 = false, _mut49709 = false, _mut49710 = false, _mut49711 = false, _mut49712 = false, _mut49713 = false, _mut49714 = false, _mut49715 = false, _mut49716 = false, _mut49717 = false, _mut49718 = false, _mut49719 = false, _mut49720 = false, _mut49721 = false, _mut49722 = false, _mut49723 = false, _mut49724 = false, _mut49725 = false, _mut49726 = false, _mut49727 = false, _mut49728 = false, _mut49729 = false, _mut49730 = false, _mut49731 = false, _mut49732 = false, _mut49733 = false, _mut49734 = false, _mut49735 = false, _mut49736 = false, _mut49737 = false, _mut49738 = false, _mut49739 = false, _mut49740 = false, _mut49741 = false, _mut49742 = false, _mut49743 = false, _mut49744 = false, _mut49745 = false, _mut49746 = false, _mut49747 = false, _mut49748 = false, _mut49749 = false, _mut49750 = false, _mut49751 = false, _mut49752 = false;

    /**
     * The constant value of {@code 0d} as a {@code Decimal64}.
     */
    public static final Decimal64 ZERO;

    /**
     * The constant value of {@code 1d} as a {@code Decimal64}.
     */
    public static final Decimal64 ONE;

    /**
     * The constant value of {@link Double#NEGATIVE_INFINITY} as a
     * {@code Decimal64}.
     */
    public static final Decimal64 NEGATIVE_INFINITY;

    /**
     * The constant value of {@link Double#POSITIVE_INFINITY} as a
     * {@code Decimal64}.
     */
    public static final Decimal64 POSITIVE_INFINITY;

    /**
     * The constant value of {@link Double#NaN} as a {@code Decimal64}.
     */
    public static final Decimal64 NAN;

    /**
     */
    private static final long serialVersionUID = 20120227L;

    static {
        ZERO = new Decimal64(0d);
        ONE = new Decimal64(1d);
        NEGATIVE_INFINITY = new Decimal64(Double.NEGATIVE_INFINITY);
        POSITIVE_INFINITY = new Decimal64(Double.POSITIVE_INFINITY);
        NAN = new Decimal64(Double.NaN);
    }

    /**
     * The primitive {@code double} value of this object.
     */
    private final double value;

    /**
     * Creates a new instance of this class.
     *
     * @param x the primitive {@code double} value of the object to be created
     */
    public Decimal64(final double x) {
        this.value = x;
    }

    /**
     * {@inheritDoc}
     */
    public Field<Decimal64> getField() {
        return Decimal64Field.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.add(a).equals(new Decimal64(this.doubleValue()
     * + a.doubleValue()))}.
     */
    public Decimal64 add(final Decimal64 a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.add_93");
        return new Decimal64(AOR_plus(this.value, a.value, "org.apache.commons.math3.util.Decimal64.add_93", _mut49675, _mut49676, _mut49677, _mut49678));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.subtract(a).equals(new Decimal64(this.doubleValue()
     * - a.doubleValue()))}.
     */
    public Decimal64 subtract(final Decimal64 a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.subtract_104");
        return new Decimal64(AOR_minus(this.value, a.value, "org.apache.commons.math3.util.Decimal64.subtract_104", _mut49679, _mut49680, _mut49681, _mut49682));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.negate().equals(new Decimal64(-this.doubleValue()))}.
     */
    public Decimal64 negate() {
        return new Decimal64(-this.value);
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.multiply(a).equals(new Decimal64(this.doubleValue()
     * * a.doubleValue()))}.
     */
    public Decimal64 multiply(final Decimal64 a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.multiply_125");
        return new Decimal64(AOR_multiply(this.value, a.value, "org.apache.commons.math3.util.Decimal64.multiply_125", _mut49683, _mut49684, _mut49685, _mut49686));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.multiply(n).equals(new Decimal64(n * this.doubleValue()))}.
     */
    public Decimal64 multiply(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.multiply_135");
        return new Decimal64(AOR_multiply(n, this.value, "org.apache.commons.math3.util.Decimal64.multiply_135", _mut49687, _mut49688, _mut49689, _mut49690));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.divide(a).equals(new Decimal64(this.doubleValue()
     * / a.doubleValue()))}.
     */
    public Decimal64 divide(final Decimal64 a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.divide_147");
        return new Decimal64(AOR_divide(this.value, a.value, "org.apache.commons.math3.util.Decimal64.divide_147", _mut49691, _mut49692, _mut49693, _mut49694));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation strictly enforces
     * {@code this.reciprocal().equals(new Decimal64(1.0
     * / this.doubleValue()))}.
     */
    public Decimal64 reciprocal() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.reciprocal_158");
        return new Decimal64(AOR_divide(1.0, this.value, "org.apache.commons.math3.util.Decimal64.reciprocal_158", _mut49695, _mut49696, _mut49697, _mut49698));
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code byte}.
     */
    @Override
    public byte byteValue() {
        return (byte) value;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code short}.
     */
    @Override
    public short shortValue() {
        return (short) value;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code int}.
     */
    @Override
    public int intValue() {
        return (int) value;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code long}.
     */
    @Override
    public long longValue() {
        return (long) value;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation performs casting to a {@code float}.
     */
    @Override
    public float floatValue() {
        return (float) value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doubleValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation returns the same value as
     * <center> {@code new Double(this.doubleValue()).compareTo(new
     * Double(o.doubleValue()))} </center>
     *
     * @see Double#compareTo(Double)
     */
    public int compareTo(final Decimal64 o) {
        return Double.compare(this.value, o.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.equals_244");
        if (obj instanceof Decimal64) {
            final Decimal64 that = (Decimal64) obj;
            return ROR_equals(Double.doubleToLongBits(this.value), Double.doubleToLongBits(that.value), "org.apache.commons.math3.util.Decimal64.equals_244", _mut49699, _mut49700, _mut49701, _mut49702, _mut49703);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * The current implementation returns the same value as
     * {@code new Double(this.doubleValue()).hashCode()}
     *
     * @see Double#hashCode()
     */
    @Override
    public int hashCode() {
        long v = Double.doubleToLongBits(value);
        return (int) (v ^ (v >>> 32));
    }

    /**
     * {@inheritDoc}
     *
     * The returned {@code String} is equal to
     * {@code Double.toString(this.doubleValue())}
     *
     * @see Double#toString(double)
     */
    @Override
    public String toString() {
        return Double.toString(value);
    }

    /**
     * Returns {@code true} if {@code this} double precision number is infinite
     * ({@link Double#POSITIVE_INFINITY} or {@link Double#NEGATIVE_INFINITY}).
     *
     * @return {@code true} if {@code this} number is infinite
     */
    public boolean isInfinite() {
        return Double.isInfinite(value);
    }

    /**
     * Returns {@code true} if {@code this} double precision number is
     * Not-a-Number ({@code NaN}), false otherwise.
     *
     * @return {@code true} if {@code this} is {@code NaN}
     */
    public boolean isNaN() {
        return Double.isNaN(value);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public double getReal() {
        return value;
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 add(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.add_315");
        return new Decimal64(AOR_plus(value, a, "org.apache.commons.math3.util.Decimal64.add_315", _mut49704, _mut49705, _mut49706, _mut49707));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 subtract(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.subtract_322");
        return new Decimal64(AOR_minus(value, a, "org.apache.commons.math3.util.Decimal64.subtract_322", _mut49708, _mut49709, _mut49710, _mut49711));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 multiply(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.multiply_329");
        return new Decimal64(AOR_multiply(value, a, "org.apache.commons.math3.util.Decimal64.multiply_329", _mut49712, _mut49713, _mut49714, _mut49715));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 divide(final double a) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.divide_336");
        return new Decimal64(AOR_divide(value, a, "org.apache.commons.math3.util.Decimal64.divide_336", _mut49716, _mut49717, _mut49718, _mut49719));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 remainder(final double a) {
        return new Decimal64(FastMath.IEEEremainder(value, a));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 remainder(final Decimal64 a) {
        return new Decimal64(FastMath.IEEEremainder(value, a.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 abs() {
        return new Decimal64(FastMath.abs(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 ceil() {
        return new Decimal64(FastMath.ceil(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 floor() {
        return new Decimal64(FastMath.floor(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 rint() {
        return new Decimal64(FastMath.rint(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public long round() {
        return FastMath.round(value);
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 signum() {
        return new Decimal64(FastMath.signum(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 copySign(final Decimal64 sign) {
        return new Decimal64(FastMath.copySign(value, sign.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 copySign(final double sign) {
        return new Decimal64(FastMath.copySign(value, sign));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 scalb(final int n) {
        return new Decimal64(FastMath.scalb(value, n));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 hypot(final Decimal64 y) {
        return new Decimal64(FastMath.hypot(value, y.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sqrt() {
        return new Decimal64(FastMath.sqrt(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cbrt() {
        return new Decimal64(FastMath.cbrt(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 rootN(final int n) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.rootN_441");
        if (ROR_less(value, 0, "org.apache.commons.math3.util.Decimal64.rootN_441", _mut49720, _mut49721, _mut49722, _mut49723, _mut49724)) {
            return new Decimal64(-FastMath.pow(-value, AOR_divide(1.0, n, "org.apache.commons.math3.util.Decimal64.rootN_441", _mut49729, _mut49730, _mut49731, _mut49732)));
        } else {
            return new Decimal64(FastMath.pow(value, AOR_divide(1.0, n, "org.apache.commons.math3.util.Decimal64.rootN_441", _mut49725, _mut49726, _mut49727, _mut49728)));
        }
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final double p) {
        return new Decimal64(FastMath.pow(value, p));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final int n) {
        return new Decimal64(FastMath.pow(value, n));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 pow(final Decimal64 e) {
        return new Decimal64(FastMath.pow(value, e.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 exp() {
        return new Decimal64(FastMath.exp(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 expm1() {
        return new Decimal64(FastMath.expm1(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 log() {
        return new Decimal64(FastMath.log(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 log1p() {
        return new Decimal64(FastMath.log1p(value));
    }

    /**
     * Base 10 logarithm.
     * @return base 10 logarithm of the instance
     * @since 3.2
     */
    public Decimal64 log10() {
        return new Decimal64(FastMath.log10(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cos() {
        return new Decimal64(FastMath.cos(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sin() {
        return new Decimal64(FastMath.sin(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 tan() {
        return new Decimal64(FastMath.tan(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 acos() {
        return new Decimal64(FastMath.acos(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 asin() {
        return new Decimal64(FastMath.asin(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atan() {
        return new Decimal64(FastMath.atan(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atan2(final Decimal64 x) {
        return new Decimal64(FastMath.atan2(value, x.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 cosh() {
        return new Decimal64(FastMath.cosh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 sinh() {
        return new Decimal64(FastMath.sinh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 tanh() {
        return new Decimal64(FastMath.tanh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 acosh() {
        return new Decimal64(FastMath.acosh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 asinh() {
        return new Decimal64(FastMath.asinh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 atanh() {
        return new Decimal64(FastMath.atanh(value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64[] a, final Decimal64[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.linearCombination_600");
        if (ROR_not_equals(a.length, b.length, "org.apache.commons.math3.util.Decimal64.linearCombination_600", _mut49733, _mut49734, _mut49735, _mut49736, _mut49737)) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        final double[] aDouble = new double[a.length];
        final double[] bDouble = new double[b.length];
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.Decimal64.linearCombination_600", _mut49738, _mut49739, _mut49740, _mut49741, _mut49742); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.linearCombination_600");
            aDouble[i] = a[i].value;
            bDouble[i] = b[i].value;
        }
        return new Decimal64(MathArrays.linearCombination(aDouble, bDouble));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double[] a, final Decimal64[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.linearCombination_617");
        if (ROR_not_equals(a.length, b.length, "org.apache.commons.math3.util.Decimal64.linearCombination_617", _mut49743, _mut49744, _mut49745, _mut49746, _mut49747)) {
            throw new DimensionMismatchException(a.length, b.length);
        }
        final double[] bDouble = new double[b.length];
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.util.Decimal64.linearCombination_617", _mut49748, _mut49749, _mut49750, _mut49751, _mut49752); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.Decimal64.linearCombination_617");
            bDouble[i] = b[i].value;
        }
        return new Decimal64(MathArrays.linearCombination(a, bDouble));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2, final Decimal64 a3, final Decimal64 b3) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2, final double a3, final Decimal64 b3) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final Decimal64 a1, final Decimal64 b1, final Decimal64 a2, final Decimal64 b2, final Decimal64 a3, final Decimal64 b3, final Decimal64 a4, final Decimal64 b4) {
        return new Decimal64(MathArrays.linearCombination(a1.value, b1.value, a2.value, b2.value, a3.value, b3.value, a4.value, b4.value));
    }

    /**
     * {@inheritDoc}
     * @since 3.2
     */
    public Decimal64 linearCombination(final double a1, final Decimal64 b1, final double a2, final Decimal64 b2, final double a3, final Decimal64 b3, final double a4, final Decimal64 b4) {
        return new Decimal64(MathArrays.linearCombination(a1, b1.value, a2, b2.value, a3, b3.value, a4, b4.value));
    }
}
