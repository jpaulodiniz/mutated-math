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
package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements <a href="http://mathworld.wolfram.com/Quaternion.html">
 * quaternions</a> (Hamilton's hypercomplex numbers).
 * <br/>
 * Instance of this class are guaranteed to be immutable.
 *
 * @since 3.1
 */
public final class Quaternion implements Serializable {

    @Conditional
    public static boolean _mut37504 = false, _mut37505 = false, _mut37506 = false, _mut37507 = false, _mut37508 = false, _mut37509 = false, _mut37510 = false, _mut37511 = false, _mut37512 = false, _mut37513 = false, _mut37514 = false, _mut37515 = false, _mut37516 = false, _mut37517 = false, _mut37518 = false, _mut37519 = false, _mut37520 = false, _mut37521 = false, _mut37522 = false, _mut37523 = false, _mut37524 = false, _mut37525 = false, _mut37526 = false, _mut37527 = false, _mut37528 = false, _mut37529 = false, _mut37530 = false, _mut37531 = false, _mut37532 = false, _mut37533 = false, _mut37534 = false, _mut37535 = false, _mut37536 = false, _mut37537 = false, _mut37538 = false, _mut37539 = false, _mut37540 = false, _mut37541 = false, _mut37542 = false, _mut37543 = false, _mut37544 = false, _mut37545 = false, _mut37546 = false, _mut37547 = false, _mut37548 = false, _mut37549 = false, _mut37550 = false, _mut37551 = false, _mut37552 = false, _mut37553 = false, _mut37554 = false, _mut37555 = false, _mut37556 = false, _mut37557 = false, _mut37558 = false, _mut37559 = false, _mut37560 = false, _mut37561 = false, _mut37562 = false, _mut37563 = false, _mut37564 = false, _mut37565 = false, _mut37566 = false, _mut37567 = false, _mut37568 = false, _mut37569 = false, _mut37570 = false, _mut37571 = false, _mut37572 = false, _mut37573 = false, _mut37574 = false, _mut37575 = false, _mut37576 = false, _mut37577 = false, _mut37578 = false, _mut37579 = false, _mut37580 = false, _mut37581 = false, _mut37582 = false, _mut37583 = false, _mut37584 = false, _mut37585 = false, _mut37586 = false, _mut37587 = false, _mut37588 = false, _mut37589 = false, _mut37590 = false, _mut37591 = false, _mut37592 = false, _mut37593 = false, _mut37594 = false, _mut37595 = false, _mut37596 = false, _mut37597 = false, _mut37598 = false, _mut37599 = false, _mut37600 = false, _mut37601 = false, _mut37602 = false, _mut37603 = false, _mut37604 = false, _mut37605 = false, _mut37606 = false, _mut37607 = false, _mut37608 = false, _mut37609 = false, _mut37610 = false, _mut37611 = false, _mut37612 = false, _mut37613 = false, _mut37614 = false, _mut37615 = false, _mut37616 = false, _mut37617 = false, _mut37618 = false, _mut37619 = false, _mut37620 = false, _mut37621 = false, _mut37622 = false, _mut37623 = false, _mut37624 = false, _mut37625 = false, _mut37626 = false, _mut37627 = false, _mut37628 = false, _mut37629 = false, _mut37630 = false, _mut37631 = false, _mut37632 = false, _mut37633 = false, _mut37634 = false, _mut37635 = false, _mut37636 = false, _mut37637 = false, _mut37638 = false, _mut37639 = false, _mut37640 = false, _mut37641 = false, _mut37642 = false, _mut37643 = false, _mut37644 = false, _mut37645 = false, _mut37646 = false, _mut37647 = false, _mut37648 = false, _mut37649 = false, _mut37650 = false, _mut37651 = false, _mut37652 = false, _mut37653 = false, _mut37654 = false, _mut37655 = false, _mut37656 = false, _mut37657 = false, _mut37658 = false, _mut37659 = false, _mut37660 = false, _mut37661 = false, _mut37662 = false, _mut37663 = false, _mut37664 = false, _mut37665 = false, _mut37666 = false, _mut37667 = false, _mut37668 = false, _mut37669 = false, _mut37670 = false, _mut37671 = false, _mut37672 = false, _mut37673 = false, _mut37674 = false, _mut37675 = false, _mut37676 = false, _mut37677 = false, _mut37678 = false, _mut37679 = false, _mut37680 = false, _mut37681 = false, _mut37682 = false, _mut37683 = false, _mut37684 = false, _mut37685 = false, _mut37686 = false, _mut37687 = false, _mut37688 = false, _mut37689 = false, _mut37690 = false, _mut37691 = false, _mut37692 = false, _mut37693 = false, _mut37694 = false, _mut37695 = false, _mut37696 = false, _mut37697 = false, _mut37698 = false, _mut37699 = false, _mut37700 = false, _mut37701 = false, _mut37702 = false, _mut37703 = false, _mut37704 = false, _mut37705 = false, _mut37706 = false, _mut37707 = false, _mut37708 = false, _mut37709 = false, _mut37710 = false, _mut37711 = false, _mut37712 = false, _mut37713 = false, _mut37714 = false, _mut37715 = false, _mut37716 = false, _mut37717 = false, _mut37718 = false, _mut37719 = false, _mut37720 = false, _mut37721 = false, _mut37722 = false, _mut37723 = false, _mut37724 = false, _mut37725 = false, _mut37726 = false, _mut37727 = false, _mut37728 = false, _mut37729 = false, _mut37730 = false, _mut37731 = false, _mut37732 = false, _mut37733 = false, _mut37734 = false, _mut37735 = false, _mut37736 = false, _mut37737 = false, _mut37738 = false, _mut37739 = false, _mut37740 = false, _mut37741 = false, _mut37742 = false, _mut37743 = false, _mut37744 = false, _mut37745 = false, _mut37746 = false, _mut37747 = false, _mut37748 = false, _mut37749 = false, _mut37750 = false, _mut37751 = false, _mut37752 = false, _mut37753 = false, _mut37754 = false, _mut37755 = false, _mut37756 = false, _mut37757 = false, _mut37758 = false, _mut37759 = false, _mut37760 = false, _mut37761 = false, _mut37762 = false, _mut37763 = false, _mut37764 = false, _mut37765 = false, _mut37766 = false, _mut37767 = false, _mut37768 = false, _mut37769 = false, _mut37770 = false, _mut37771 = false, _mut37772 = false, _mut37773 = false, _mut37774 = false, _mut37775 = false, _mut37776 = false, _mut37777 = false, _mut37778 = false, _mut37779 = false, _mut37780 = false, _mut37781 = false, _mut37782 = false, _mut37783 = false, _mut37784 = false, _mut37785 = false, _mut37786 = false, _mut37787 = false, _mut37788 = false, _mut37789 = false, _mut37790 = false, _mut37791 = false, _mut37792 = false, _mut37793 = false, _mut37794 = false, _mut37795 = false, _mut37796 = false, _mut37797 = false, _mut37798 = false, _mut37799 = false, _mut37800 = false, _mut37801 = false, _mut37802 = false, _mut37803 = false, _mut37804 = false, _mut37805 = false, _mut37806 = false, _mut37807 = false, _mut37808 = false, _mut37809 = false, _mut37810 = false, _mut37811 = false, _mut37812 = false, _mut37813 = false, _mut37814 = false, _mut37815 = false, _mut37816 = false, _mut37817 = false, _mut37818 = false, _mut37819 = false, _mut37820 = false, _mut37821 = false, _mut37822 = false, _mut37823 = false, _mut37824 = false, _mut37825 = false, _mut37826 = false, _mut37827 = false, _mut37828 = false, _mut37829 = false, _mut37830 = false, _mut37831 = false, _mut37832 = false, _mut37833 = false, _mut37834 = false, _mut37835 = false, _mut37836 = false, _mut37837 = false, _mut37838 = false;

    /**
     * Identity quaternion.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    /**
     * Zero quaternion.
     */
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

    /**
     * i
     */
    public static final Quaternion I = new Quaternion(0, 1, 0, 0);

    /**
     * j
     */
    public static final Quaternion J = new Quaternion(0, 0, 1, 0);

    /**
     * k
     */
    public static final Quaternion K = new Quaternion(0, 0, 0, 1);

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20092012L;

    /**
     * First component (scalar part).
     */
    private final double q0;

    /**
     * Second component (first vector part).
     */
    private final double q1;

    /**
     * Third component (second vector part).
     */
    private final double q2;

    /**
     * Fourth component (third vector part).
     */
    private final double q3;

    /**
     * Builds a quaternion from its components.
     *
     * @param a Scalar component.
     * @param b First vector component.
     * @param c Second vector component.
     * @param d Third vector component.
     */
    public Quaternion(final double a, final double b, final double c, final double d) {
        this.q0 = a;
        this.q1 = b;
        this.q2 = c;
        this.q3 = d;
    }

    /**
     * Builds a quaternion from scalar and vector parts.
     *
     * @param scalar Scalar part of the quaternion.
     * @param v Components of the vector part of the quaternion.
     *
     * @throws DimensionMismatchException if the array length is not 3.
     */
    public Quaternion(final double scalar, final double[] v) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.Quaternion_86");
        if (ROR_not_equals(v.length, 3, "org.apache.commons.math3.complex.Quaternion.Quaternion_86", _mut37504, _mut37505, _mut37506, _mut37507, _mut37508)) {
            throw new DimensionMismatchException(v.length, 3);
        }
        this.q0 = scalar;
        this.q1 = v[0];
        this.q2 = v[1];
        this.q3 = v[2];
    }

    /**
     * Builds a pure quaternion from a vector (assuming that the scalar
     * part is zero).
     *
     * @param v Components of the vector part of the pure quaternion.
     */
    public Quaternion(final double[] v) {
        this(0, v);
    }

    /**
     * Returns the conjugate quaternion of the instance.
     *
     * @return the conjugate quaternion
     */
    public Quaternion getConjugate() {
        return new Quaternion(q0, -q1, -q2, -q3);
    }

    /**
     * Returns the Hamilton product of two quaternions.
     *
     * @param q1 First quaternion.
     * @param q2 Second quaternion.
     * @return the product {@code q1} and {@code q2}, in that order.
     */
    public static Quaternion multiply(final Quaternion q1, final Quaternion q2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.multiply_124");
        // Components of the first quaternion.
        final double q1a = q1.getQ0();
        final double q1b = q1.getQ1();
        final double q1c = q1.getQ2();
        final double q1d = q1.getQ3();
        // Components of the second quaternion.
        final double q2a = q2.getQ0();
        final double q2b = q2.getQ1();
        final double q2c = q2.getQ2();
        final double q2d = q2.getQ3();
        // Components of the product.
        final double w = AOR_minus(AOR_minus(AOR_minus(AOR_multiply(q1a, q2a, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37509, _mut37510, _mut37511, _mut37512), AOR_multiply(q1b, q2b, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37513, _mut37514, _mut37515, _mut37516), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37517, _mut37518, _mut37519, _mut37520), AOR_multiply(q1c, q2c, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37521, _mut37522, _mut37523, _mut37524), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37525, _mut37526, _mut37527, _mut37528), AOR_multiply(q1d, q2d, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37529, _mut37530, _mut37531, _mut37532), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37533, _mut37534, _mut37535, _mut37536);
        final double x = AOR_minus(AOR_plus(AOR_plus(AOR_multiply(q1a, q2b, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37537, _mut37538, _mut37539, _mut37540), AOR_multiply(q1b, q2a, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37541, _mut37542, _mut37543, _mut37544), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37545, _mut37546, _mut37547, _mut37548), AOR_multiply(q1c, q2d, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37549, _mut37550, _mut37551, _mut37552), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37553, _mut37554, _mut37555, _mut37556), AOR_multiply(q1d, q2c, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37557, _mut37558, _mut37559, _mut37560), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37561, _mut37562, _mut37563, _mut37564);
        final double y = AOR_plus(AOR_plus(AOR_minus(AOR_multiply(q1a, q2c, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37565, _mut37566, _mut37567, _mut37568), AOR_multiply(q1b, q2d, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37569, _mut37570, _mut37571, _mut37572), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37573, _mut37574, _mut37575, _mut37576), AOR_multiply(q1c, q2a, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37577, _mut37578, _mut37579, _mut37580), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37581, _mut37582, _mut37583, _mut37584), AOR_multiply(q1d, q2b, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37585, _mut37586, _mut37587, _mut37588), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37589, _mut37590, _mut37591, _mut37592);
        final double z = AOR_plus(AOR_minus(AOR_plus(AOR_multiply(q1a, q2d, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37593, _mut37594, _mut37595, _mut37596), AOR_multiply(q1b, q2c, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37597, _mut37598, _mut37599, _mut37600), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37601, _mut37602, _mut37603, _mut37604), AOR_multiply(q1c, q2b, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37605, _mut37606, _mut37607, _mut37608), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37609, _mut37610, _mut37611, _mut37612), AOR_multiply(q1d, q2a, "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37613, _mut37614, _mut37615, _mut37616), "org.apache.commons.math3.complex.Quaternion.multiply_124", _mut37617, _mut37618, _mut37619, _mut37620);
        return new Quaternion(w, x, y, z);
    }

    /**
     * Returns the Hamilton product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the product of this instance with {@code q}, in that order.
     */
    public Quaternion multiply(final Quaternion q) {
        return multiply(this, q);
    }

    /**
     * Computes the sum of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the sum of {@code q1} and {@code q2}.
     */
    public static Quaternion add(final Quaternion q1, final Quaternion q2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.add_163");
        return new Quaternion(AOR_plus(q1.getQ0(), q2.getQ0(), "org.apache.commons.math3.complex.Quaternion.add_163", _mut37621, _mut37622, _mut37623, _mut37624), AOR_plus(q1.getQ1(), q2.getQ1(), "org.apache.commons.math3.complex.Quaternion.add_163", _mut37625, _mut37626, _mut37627, _mut37628), AOR_plus(q1.getQ2(), q2.getQ2(), "org.apache.commons.math3.complex.Quaternion.add_163", _mut37629, _mut37630, _mut37631, _mut37632), AOR_plus(q1.getQ3(), q2.getQ3(), "org.apache.commons.math3.complex.Quaternion.add_163", _mut37633, _mut37634, _mut37635, _mut37636));
    }

    /**
     * Computes the sum of the instance and another quaternion.
     *
     * @param q Quaternion.
     * @return the sum of this instance and {@code q}
     */
    public Quaternion add(final Quaternion q) {
        return add(this, q);
    }

    /**
     * Subtracts two quaternions.
     *
     * @param q1 First Quaternion.
     * @param q2 Second quaternion.
     * @return the difference between {@code q1} and {@code q2}.
     */
    public static Quaternion subtract(final Quaternion q1, final Quaternion q2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.subtract_188");
        return new Quaternion(AOR_minus(q1.getQ0(), q2.getQ0(), "org.apache.commons.math3.complex.Quaternion.subtract_188", _mut37637, _mut37638, _mut37639, _mut37640), AOR_minus(q1.getQ1(), q2.getQ1(), "org.apache.commons.math3.complex.Quaternion.subtract_188", _mut37641, _mut37642, _mut37643, _mut37644), AOR_minus(q1.getQ2(), q2.getQ2(), "org.apache.commons.math3.complex.Quaternion.subtract_188", _mut37645, _mut37646, _mut37647, _mut37648), AOR_minus(q1.getQ3(), q2.getQ3(), "org.apache.commons.math3.complex.Quaternion.subtract_188", _mut37649, _mut37650, _mut37651, _mut37652));
    }

    /**
     * Subtracts a quaternion from the instance.
     *
     * @param q Quaternion.
     * @return the difference between this instance and {@code q}.
     */
    public Quaternion subtract(final Quaternion q) {
        return subtract(this, q);
    }

    /**
     * Computes the dot-product of two quaternions.
     *
     * @param q1 Quaternion.
     * @param q2 Quaternion.
     * @return the dot product of {@code q1} and {@code q2}.
     */
    public static double dotProduct(final Quaternion q1, final Quaternion q2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.dotProduct_213");
        return AOR_plus(AOR_plus(AOR_plus(AOR_multiply(q1.getQ0(), q2.getQ0(), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37653, _mut37654, _mut37655, _mut37656), AOR_multiply(q1.getQ1(), q2.getQ1(), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37657, _mut37658, _mut37659, _mut37660), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37661, _mut37662, _mut37663, _mut37664), AOR_multiply(q1.getQ2(), q2.getQ2(), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37665, _mut37666, _mut37667, _mut37668), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37669, _mut37670, _mut37671, _mut37672), AOR_multiply(q1.getQ3(), q2.getQ3(), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37673, _mut37674, _mut37675, _mut37676), "org.apache.commons.math3.complex.Quaternion.dotProduct_213", _mut37677, _mut37678, _mut37679, _mut37680);
    }

    /**
     * Computes the dot-product of the instance by a quaternion.
     *
     * @param q Quaternion.
     * @return the dot product of this instance and {@code q}.
     */
    public double dotProduct(final Quaternion q) {
        return dotProduct(this, q);
    }

    /**
     * Computes the norm of the quaternion.
     *
     * @return the norm.
     */
    public double getNorm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.getNorm_236");
        return FastMath.sqrt(AOR_plus(AOR_plus(AOR_plus(AOR_multiply(q0, q0, "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37681, _mut37682, _mut37683, _mut37684), AOR_multiply(q1, q1, "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37685, _mut37686, _mut37687, _mut37688), "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37689, _mut37690, _mut37691, _mut37692), AOR_multiply(q2, q2, "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37693, _mut37694, _mut37695, _mut37696), "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37697, _mut37698, _mut37699, _mut37700), AOR_multiply(q3, q3, "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37701, _mut37702, _mut37703, _mut37704), "org.apache.commons.math3.complex.Quaternion.getNorm_236", _mut37705, _mut37706, _mut37707, _mut37708));
    }

    /**
     * Computes the normalized quaternion (the versor of the instance).
     * The norm of the quaternion must not be zero.
     *
     * @return a normalized quaternion.
     * @throws ZeroException if the norm of the quaternion is zero.
     */
    public Quaternion normalize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.normalize_250");
        final double norm = getNorm();
        if (ROR_less(norm, Precision.SAFE_MIN, "org.apache.commons.math3.complex.Quaternion.normalize_250", _mut37709, _mut37710, _mut37711, _mut37712, _mut37713)) {
            throw new ZeroException(LocalizedFormats.NORM, norm);
        }
        return new Quaternion(AOR_divide(q0, norm, "org.apache.commons.math3.complex.Quaternion.normalize_250", _mut37714, _mut37715, _mut37716, _mut37717), AOR_divide(q1, norm, "org.apache.commons.math3.complex.Quaternion.normalize_250", _mut37718, _mut37719, _mut37720, _mut37721), AOR_divide(q2, norm, "org.apache.commons.math3.complex.Quaternion.normalize_250", _mut37722, _mut37723, _mut37724, _mut37725), AOR_divide(q3, norm, "org.apache.commons.math3.complex.Quaternion.normalize_250", _mut37726, _mut37727, _mut37728, _mut37729));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.equals_266");
        if (this == other) {
            return true;
        }
        if (other instanceof Quaternion) {
            final Quaternion q = (Quaternion) other;
            return (_mut37752 ? ((_mut37746 ? ((_mut37740 ? (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) || ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739)) : (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) && ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739))) || ROR_equals(q2, q.getQ2(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37741, _mut37742, _mut37743, _mut37744, _mut37745)) : ((_mut37740 ? (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) || ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739)) : (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) && ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739))) && ROR_equals(q2, q.getQ2(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37741, _mut37742, _mut37743, _mut37744, _mut37745))) || ROR_equals(q3, q.getQ3(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37747, _mut37748, _mut37749, _mut37750, _mut37751)) : ((_mut37746 ? ((_mut37740 ? (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) || ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739)) : (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) && ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739))) || ROR_equals(q2, q.getQ2(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37741, _mut37742, _mut37743, _mut37744, _mut37745)) : ((_mut37740 ? (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) || ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739)) : (ROR_equals(q0, q.getQ0(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37730, _mut37731, _mut37732, _mut37733, _mut37734) && ROR_equals(q1, q.getQ1(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37735, _mut37736, _mut37737, _mut37738, _mut37739))) && ROR_equals(q2, q.getQ2(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37741, _mut37742, _mut37743, _mut37744, _mut37745))) && ROR_equals(q3, q.getQ3(), "org.apache.commons.math3.complex.Quaternion.equals_266", _mut37747, _mut37748, _mut37749, _mut37750, _mut37751)));
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.hashCode_285");
        // "Effective Java" (second edition, p. 47).
        int result = 17;
        for (double comp : new double[] { q0, q1, q2, q3 }) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.hashCode_285");
            final int c = MathUtils.hash(comp);
            result = AOR_plus(AOR_multiply(31, result, "org.apache.commons.math3.complex.Quaternion.hashCode_285", _mut37753, _mut37754, _mut37755, _mut37756), c, "org.apache.commons.math3.complex.Quaternion.hashCode_285", _mut37757, _mut37758, _mut37759, _mut37760);
        }
        return result;
    }

    /**
     * Checks whether this instance is equal to another quaternion
     * within a given tolerance.
     *
     * @param q Quaternion with which to compare the current quaternion.
     * @param eps Tolerance.
     * @return {@code true} if the each of the components are equal
     * within the allowed absolute error.
     */
    public boolean equals(final Quaternion q, final double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.equals_305");
        return (_mut37763 ? ((_mut37762 ? ((_mut37761 ? (Precision.equals(q0, q.getQ0(), eps) || Precision.equals(q1, q.getQ1(), eps)) : (Precision.equals(q0, q.getQ0(), eps) && Precision.equals(q1, q.getQ1(), eps))) || Precision.equals(q2, q.getQ2(), eps)) : ((_mut37761 ? (Precision.equals(q0, q.getQ0(), eps) || Precision.equals(q1, q.getQ1(), eps)) : (Precision.equals(q0, q.getQ0(), eps) && Precision.equals(q1, q.getQ1(), eps))) && Precision.equals(q2, q.getQ2(), eps))) || Precision.equals(q3, q.getQ3(), eps)) : ((_mut37762 ? ((_mut37761 ? (Precision.equals(q0, q.getQ0(), eps) || Precision.equals(q1, q.getQ1(), eps)) : (Precision.equals(q0, q.getQ0(), eps) && Precision.equals(q1, q.getQ1(), eps))) || Precision.equals(q2, q.getQ2(), eps)) : ((_mut37761 ? (Precision.equals(q0, q.getQ0(), eps) || Precision.equals(q1, q.getQ1(), eps)) : (Precision.equals(q0, q.getQ0(), eps) && Precision.equals(q1, q.getQ1(), eps))) && Precision.equals(q2, q.getQ2(), eps))) && Precision.equals(q3, q.getQ3(), eps)));
    }

    /**
     * Checks whether the instance is a unit quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the norm is 1 within the given tolerance,
     * {@code false} otherwise
     */
    public boolean isUnitQuaternion(double eps) {
        return Precision.equals(getNorm(), 1d, eps);
    }

    /**
     * Checks whether the instance is a pure quaternion within a given
     * tolerance.
     *
     * @param eps Tolerance (absolute error).
     * @return {@code true} if the scalar part of the quaternion is zero.
     */
    public boolean isPureQuaternion(double eps) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.isPureQuaternion_332");
        return ROR_less_equals(FastMath.abs(getQ0()), eps, "org.apache.commons.math3.complex.Quaternion.isPureQuaternion_332", _mut37764, _mut37765, _mut37766, _mut37767, _mut37768);
    }

    /**
     * Returns the polar form of the quaternion.
     *
     * @return the unit quaternion with positive scalar part.
     */
    public Quaternion getPositivePolarForm() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.getPositivePolarForm_341");
        if (ROR_less(getQ0(), 0, "org.apache.commons.math3.complex.Quaternion.getPositivePolarForm_341", _mut37769, _mut37770, _mut37771, _mut37772, _mut37773)) {
            final Quaternion unitQ = normalize();
            // are equivalent (i.e. represent the same rotation).
            return new Quaternion(-unitQ.getQ0(), -unitQ.getQ1(), -unitQ.getQ2(), -unitQ.getQ3());
        } else {
            return this.normalize();
        }
    }

    /**
     * Returns the inverse of this instance.
     * The norm of the quaternion must not be zero.
     *
     * @return the inverse.
     * @throws ZeroException if the norm (squared) of the quaternion is zero.
     */
    public Quaternion getInverse() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.getInverse_362");
        final double squareNorm = AOR_plus(AOR_plus(AOR_plus(AOR_multiply(q0, q0, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37774, _mut37775, _mut37776, _mut37777), AOR_multiply(q1, q1, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37778, _mut37779, _mut37780, _mut37781), "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37782, _mut37783, _mut37784, _mut37785), AOR_multiply(q2, q2, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37786, _mut37787, _mut37788, _mut37789), "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37790, _mut37791, _mut37792, _mut37793), AOR_multiply(q3, q3, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37794, _mut37795, _mut37796, _mut37797), "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37798, _mut37799, _mut37800, _mut37801);
        if (ROR_less(squareNorm, Precision.SAFE_MIN, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37802, _mut37803, _mut37804, _mut37805, _mut37806)) {
            throw new ZeroException(LocalizedFormats.NORM, squareNorm);
        }
        return new Quaternion(AOR_divide(q0, squareNorm, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37807, _mut37808, _mut37809, _mut37810), AOR_divide(-q1, squareNorm, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37811, _mut37812, _mut37813, _mut37814), AOR_divide(-q2, squareNorm, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37815, _mut37816, _mut37817, _mut37818), AOR_divide(-q3, squareNorm, "org.apache.commons.math3.complex.Quaternion.getInverse_362", _mut37819, _mut37820, _mut37821, _mut37822));
    }

    /**
     * Gets the first component of the quaternion (scalar part).
     *
     * @return the scalar part.
     */
    public double getQ0() {
        return q0;
    }

    /**
     * Gets the second component of the quaternion (first component
     * of the vector part).
     *
     * @return the first component of the vector part.
     */
    public double getQ1() {
        return q1;
    }

    /**
     * Gets the third component of the quaternion (second component
     * of the vector part).
     *
     * @return the second component of the vector part.
     */
    public double getQ2() {
        return q2;
    }

    /**
     * Gets the fourth component of the quaternion (third component
     * of the vector part).
     *
     * @return the third component of the vector part.
     */
    public double getQ3() {
        return q3;
    }

    /**
     * Gets the scalar part of the quaternion.
     *
     * @return the scalar part.
     * @see #getQ0()
     */
    public double getScalarPart() {
        return getQ0();
    }

    /**
     * Gets the three components of the vector part of the quaternion.
     *
     * @return the vector part.
     * @see #getQ1()
     * @see #getQ2()
     * @see #getQ3()
     */
    public double[] getVectorPart() {
        return new double[] { getQ1(), getQ2(), getQ3() };
    }

    /**
     * Multiplies the instance by a scalar.
     *
     * @param alpha Scalar factor.
     * @return a scaled quaternion.
     */
    public Quaternion multiply(final double alpha) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.complex.Quaternion.multiply_441");
        return new Quaternion(AOR_multiply(alpha, q0, "org.apache.commons.math3.complex.Quaternion.multiply_441", _mut37823, _mut37824, _mut37825, _mut37826), AOR_multiply(alpha, q1, "org.apache.commons.math3.complex.Quaternion.multiply_441", _mut37827, _mut37828, _mut37829, _mut37830), AOR_multiply(alpha, q2, "org.apache.commons.math3.complex.Quaternion.multiply_441", _mut37831, _mut37832, _mut37833, _mut37834), AOR_multiply(alpha, q3, "org.apache.commons.math3.complex.Quaternion.multiply_441", _mut37835, _mut37836, _mut37837, _mut37838));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final String sp = " ";
        final StringBuilder s = new StringBuilder();
        s.append("[").append(q0).append(sp).append(q1).append(sp).append(q2).append(sp).append(q3).append("]");
        return s.toString();
    }
}
