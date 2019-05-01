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
package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Representation of a rational number.
 *
 * implements Serializable since 2.0
 *
 * @since 1.1
 */
public class Fraction extends Number implements FieldElement<Fraction>, Comparable<Fraction>, Serializable {

    @Conditional
    public static boolean _mut703 = false, _mut704 = false, _mut705 = false, _mut706 = false, _mut707 = false, _mut708 = false, _mut709 = false, _mut710 = false, _mut711 = false, _mut712 = false, _mut713 = false, _mut714 = false, _mut715 = false, _mut716 = false, _mut717 = false, _mut718 = false, _mut719 = false, _mut720 = false, _mut721 = false, _mut722 = false, _mut723 = false, _mut724 = false, _mut725 = false, _mut726 = false, _mut727 = false, _mut728 = false, _mut729 = false, _mut730 = false, _mut731 = false, _mut732 = false, _mut733 = false, _mut734 = false, _mut735 = false, _mut736 = false, _mut737 = false, _mut738 = false, _mut739 = false, _mut740 = false, _mut741 = false, _mut742 = false, _mut743 = false, _mut744 = false, _mut745 = false, _mut746 = false, _mut747 = false, _mut748 = false, _mut749 = false, _mut750 = false, _mut751 = false, _mut752 = false, _mut753 = false, _mut754 = false, _mut755 = false, _mut756 = false, _mut757 = false, _mut758 = false, _mut759 = false, _mut760 = false, _mut761 = false, _mut762 = false, _mut763 = false, _mut764 = false, _mut765 = false, _mut766 = false, _mut767 = false, _mut768 = false, _mut769 = false, _mut770 = false, _mut771 = false, _mut772 = false, _mut773 = false, _mut774 = false, _mut775 = false, _mut776 = false, _mut777 = false, _mut778 = false, _mut779 = false, _mut780 = false, _mut781 = false, _mut782 = false, _mut783 = false, _mut784 = false, _mut785 = false, _mut786 = false, _mut787 = false, _mut788 = false, _mut789 = false, _mut790 = false, _mut791 = false, _mut792 = false, _mut793 = false, _mut794 = false, _mut795 = false, _mut796 = false, _mut797 = false, _mut798 = false, _mut799 = false, _mut800 = false, _mut801 = false, _mut802 = false, _mut803 = false, _mut804 = false, _mut805 = false, _mut806 = false, _mut807 = false, _mut808 = false, _mut809 = false, _mut810 = false, _mut811 = false, _mut812 = false, _mut813 = false, _mut814 = false, _mut815 = false, _mut816 = false, _mut817 = false, _mut818 = false, _mut819 = false, _mut820 = false, _mut821 = false, _mut822 = false, _mut823 = false, _mut824 = false, _mut825 = false, _mut826 = false, _mut827 = false, _mut828 = false, _mut829 = false, _mut830 = false, _mut831 = false, _mut832 = false, _mut833 = false, _mut834 = false, _mut835 = false, _mut836 = false, _mut837 = false, _mut838 = false, _mut839 = false, _mut840 = false, _mut841 = false, _mut842 = false, _mut843 = false, _mut844 = false, _mut845 = false, _mut846 = false, _mut847 = false, _mut848 = false, _mut849 = false, _mut850 = false, _mut851 = false, _mut852 = false, _mut853 = false, _mut854 = false, _mut855 = false, _mut856 = false, _mut857 = false, _mut858 = false, _mut859 = false, _mut860 = false, _mut861 = false, _mut862 = false, _mut863 = false, _mut864 = false, _mut865 = false, _mut866 = false, _mut867 = false, _mut868 = false, _mut869 = false, _mut870 = false, _mut871 = false, _mut872 = false, _mut873 = false, _mut874 = false, _mut875 = false, _mut876 = false, _mut877 = false, _mut878 = false, _mut879 = false, _mut880 = false, _mut881 = false, _mut882 = false, _mut883 = false, _mut884 = false, _mut885 = false, _mut886 = false, _mut887 = false, _mut888 = false, _mut889 = false, _mut890 = false, _mut891 = false, _mut892 = false, _mut893 = false, _mut894 = false, _mut895 = false, _mut896 = false, _mut897 = false, _mut898 = false, _mut899 = false, _mut900 = false, _mut901 = false, _mut902 = false, _mut903 = false, _mut904 = false, _mut905 = false, _mut906 = false, _mut907 = false, _mut908 = false, _mut909 = false, _mut910 = false, _mut911 = false, _mut912 = false, _mut913 = false, _mut914 = false, _mut915 = false, _mut916 = false, _mut917 = false, _mut918 = false, _mut919 = false, _mut920 = false, _mut921 = false, _mut922 = false, _mut923 = false, _mut924 = false, _mut925 = false, _mut926 = false, _mut927 = false, _mut928 = false, _mut929 = false, _mut930 = false, _mut931 = false, _mut932 = false, _mut933 = false, _mut934 = false, _mut935 = false, _mut936 = false, _mut937 = false, _mut938 = false, _mut939 = false, _mut940 = false, _mut941 = false, _mut942 = false, _mut943 = false, _mut944 = false, _mut945 = false, _mut946 = false, _mut947 = false, _mut948 = false, _mut949 = false, _mut950 = false, _mut951 = false, _mut952 = false, _mut953 = false, _mut954 = false, _mut955 = false, _mut956 = false, _mut957 = false, _mut958 = false, _mut959 = false, _mut960 = false, _mut961 = false, _mut962 = false, _mut963 = false, _mut964 = false, _mut965 = false, _mut966 = false, _mut967 = false, _mut968 = false, _mut969 = false, _mut970 = false, _mut971 = false, _mut972 = false, _mut973 = false, _mut974 = false, _mut975 = false, _mut976 = false, _mut977 = false, _mut978 = false, _mut979 = false, _mut980 = false, _mut981 = false, _mut982 = false, _mut983 = false, _mut984 = false, _mut985 = false, _mut986 = false, _mut987 = false, _mut988 = false, _mut989 = false, _mut990 = false, _mut991 = false, _mut992 = false, _mut993 = false, _mut994 = false, _mut995 = false, _mut996 = false, _mut997 = false, _mut998 = false, _mut999 = false, _mut1000 = false, _mut1001 = false, _mut1002 = false, _mut1003 = false, _mut1004 = false, _mut1005 = false, _mut1006 = false, _mut1007 = false, _mut1008 = false, _mut1009 = false, _mut1010 = false, _mut1011 = false, _mut1012 = false, _mut1013 = false, _mut1014 = false, _mut1015 = false, _mut1016 = false, _mut1017 = false, _mut1018 = false, _mut1019 = false, _mut1020 = false, _mut1021 = false, _mut1022 = false, _mut1023 = false, _mut1024 = false, _mut1025 = false, _mut1026 = false, _mut1027 = false;

    /**
     * A fraction representing "2 / 1".
     */
    public static final Fraction TWO = new Fraction(2, 1);

    /**
     * A fraction representing "1".
     */
    public static final Fraction ONE = new Fraction(1, 1);

    /**
     * A fraction representing "0".
     */
    public static final Fraction ZERO = new Fraction(0, 1);

    /**
     * A fraction representing "4/5".
     */
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);

    /**
     * A fraction representing "1/5".
     */
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);

    /**
     * A fraction representing "1/2".
     */
    public static final Fraction ONE_HALF = new Fraction(1, 2);

    /**
     * A fraction representing "1/4".
     */
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);

    /**
     * A fraction representing "1/3".
     */
    public static final Fraction ONE_THIRD = new Fraction(1, 3);

    /**
     * A fraction representing "3/5".
     */
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);

    /**
     * A fraction representing "3/4".
     */
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);

    /**
     * A fraction representing "2/5".
     */
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);

    /**
     * A fraction representing "2/4".
     */
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);

    /**
     * A fraction representing "2/3".
     */
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);

    /**
     * A fraction representing "-1 / 1".
     */
    public static final Fraction MINUS_ONE = new Fraction(-1, 1);

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 3698073679419233275L;

    /**
     * The default epsilon used for convergence.
     */
    private static final double DEFAULT_EPSILON = 1e-5;

    /**
     * The denominator.
     */
    private final int denominator;

    /**
     * The numerator.
     */
    private final int numerator;

    /**
     * Create a fraction given the double value.
     * @param value the double value to convert to a fraction.
     * @throws FractionConversionException if the continued fraction failed to
     *         converge.
     */
    public Fraction(double value) throws FractionConversionException {
        this(value, DEFAULT_EPSILON, 100);
    }

    /**
     * Create a fraction given the double value and maximum error allowed.
     * <p>
     * References:
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/ContinuedFraction.html">
     * Continued Fraction</a> equations (11) and (22)-(26)</li>
     * </ul>
     * </p>
     * @param value the double value to convert to a fraction.
     * @param epsilon maximum error allowed.  The resulting fraction is within
     *        {@code epsilon} of {@code value}, in absolute terms.
     * @param maxIterations maximum number of convergents
     * @throws FractionConversionException if the continued fraction failed to
     *         converge.
     */
    public Fraction(double value, double epsilon, int maxIterations) throws FractionConversionException {
        this(value, epsilon, Integer.MAX_VALUE, maxIterations);
    }

    /**
     * Create a fraction given the double value and maximum denominator.
     * <p>
     * References:
     * <ul>
     * <li><a href="http://mathworld.wolfram.com/ContinuedFraction.html">
     * Continued Fraction</a> equations (11) and (22)-(26)</li>
     * </ul>
     * </p>
     * @param value the double value to convert to a fraction.
     * @param maxDenominator The maximum allowed value for denominator
     * @throws FractionConversionException if the continued fraction failed to
     *         converge
     */
    public Fraction(double value, int maxDenominator) throws FractionConversionException {
        this(value, 0, maxDenominator, 100);
    }

    /**
     * Create a fraction given the double value and either the maximum error
     * allowed or the maximum number of denominator digits.
     * <p>
     *
     * NOTE: This constructor is called with EITHER
     *   - a valid epsilon value and the maxDenominator set to Integer.MAX_VALUE
     *     (that way the maxDenominator has no effect).
     * OR
     *   - a valid maxDenominator value and the epsilon value set to zero
     *     (that way epsilon only has effect if there is an exact match before
     *     the maxDenominator value is reached).
     * </p><p>
     *
     * It has been done this way so that the same code can be (re)used for both
     * scenarios. However this could be confusing to users if it were part of
     * the public API and this constructor should therefore remain PRIVATE.
     * </p>
     *
     * See JIRA issue ticket MATH-181 for more details:
     *
     *     https://issues.apache.org/jira/browse/MATH-181
     *
     * @param value the double value to convert to a fraction.
     * @param epsilon maximum error allowed.  The resulting fraction is within
     *        {@code epsilon} of {@code value}, in absolute terms.
     * @param maxDenominator maximum denominator value allowed.
     * @param maxIterations maximum number of convergents
     * @throws FractionConversionException if the continued fraction failed to
     *         converge.
     */
    private Fraction(double value, double epsilon, int maxDenominator, int maxIterations) throws FractionConversionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.Fraction_177");
        long overflow = Integer.MAX_VALUE;
        double r0 = value;
        long a0 = (long) FastMath.floor(r0);
        if (ROR_greater(FastMath.abs(a0), overflow, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut703, _mut704, _mut705, _mut706, _mut707)) {
            throw new FractionConversionException(value, a0, 1l);
        }
        // check for (almost) integer arguments, which should not go to iterations.
        if (ROR_less(FastMath.abs(AOR_minus(a0, value, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut708, _mut709, _mut710, _mut711)), epsilon, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut712, _mut713, _mut714, _mut715, _mut716)) {
            this.numerator = (int) a0;
            this.denominator = 1;
            return;
        }
        long p0 = 1;
        long q0 = 0;
        long p1 = a0;
        long q1 = 1;
        long p2 = 0;
        long q2 = 1;
        int n = 0;
        boolean stop = false;
        do {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.Fraction_177");
            ++n;
            double r1 = AOR_divide(1.0, (AOR_minus(r0, a0, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut717, _mut718, _mut719, _mut720)), "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut721, _mut722, _mut723, _mut724);
            long a1 = (long) FastMath.floor(r1);
            p2 = AOR_plus((AOR_multiply(a1, p1, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut725, _mut726, _mut727, _mut728)), p0, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut729, _mut730, _mut731, _mut732);
            q2 = AOR_plus((AOR_multiply(a1, q1, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut733, _mut734, _mut735, _mut736)), q0, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut737, _mut738, _mut739, _mut740);
            if ((_mut751 ? ((ROR_greater(FastMath.abs(p2), overflow, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut741, _mut742, _mut743, _mut744, _mut745)) && (ROR_greater(FastMath.abs(q2), overflow, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut746, _mut747, _mut748, _mut749, _mut750))) : ((ROR_greater(FastMath.abs(p2), overflow, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut741, _mut742, _mut743, _mut744, _mut745)) || (ROR_greater(FastMath.abs(q2), overflow, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut746, _mut747, _mut748, _mut749, _mut750))))) {
                // q2 may overflow in the next iteration; in this case return the last one.
                if ((_mut762 ? (ROR_equals(epsilon, 0.0, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut752, _mut753, _mut754, _mut755, _mut756) || ROR_less(FastMath.abs(q1), maxDenominator, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut757, _mut758, _mut759, _mut760, _mut761)) : (ROR_equals(epsilon, 0.0, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut752, _mut753, _mut754, _mut755, _mut756) && ROR_less(FastMath.abs(q1), maxDenominator, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut757, _mut758, _mut759, _mut760, _mut761)))) {
                    break;
                }
                throw new FractionConversionException(value, p2, q2);
            }
            double convergent = AOR_divide((double) p2, (double) q2, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut763, _mut764, _mut765, _mut766);
            if ((_mut787 ? ((_mut781 ? (ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut767, _mut768, _mut769, _mut770, _mut771) || ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut772, _mut773, _mut774, _mut775)), epsilon, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut776, _mut777, _mut778, _mut779, _mut780)) : (ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut767, _mut768, _mut769, _mut770, _mut771) && ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut772, _mut773, _mut774, _mut775)), epsilon, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut776, _mut777, _mut778, _mut779, _mut780))) || ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut782, _mut783, _mut784, _mut785, _mut786)) : ((_mut781 ? (ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut767, _mut768, _mut769, _mut770, _mut771) || ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut772, _mut773, _mut774, _mut775)), epsilon, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut776, _mut777, _mut778, _mut779, _mut780)) : (ROR_less(n, maxIterations, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut767, _mut768, _mut769, _mut770, _mut771) && ROR_greater(FastMath.abs(AOR_minus(convergent, value, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut772, _mut773, _mut774, _mut775)), epsilon, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut776, _mut777, _mut778, _mut779, _mut780))) && ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut782, _mut783, _mut784, _mut785, _mut786)))) {
                p0 = p1;
                p1 = p2;
                q0 = q1;
                q1 = q2;
                a0 = a1;
                r0 = r1;
            } else {
                stop = true;
            }
        } while (!stop);
        if (ROR_greater_equals(n, maxIterations, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut788, _mut789, _mut790, _mut791, _mut792)) {
            throw new FractionConversionException(value, maxIterations);
        }
        if (ROR_less(q2, maxDenominator, "org.apache.commons.math3.fraction.Fraction.Fraction_177", _mut793, _mut794, _mut795, _mut796, _mut797)) {
            this.numerator = (int) p2;
            this.denominator = (int) q2;
        } else {
            this.numerator = (int) p1;
            this.denominator = (int) q1;
        }
    }

    /**
     * Create a fraction from an int.
     * The fraction is num / 1.
     * @param num the numerator.
     */
    public Fraction(int num) {
        this(num, 1);
    }

    /**
     * Create a fraction given the numerator and denominator.  The fraction is
     * reduced to lowest terms.
     * @param num the numerator.
     * @param den the denominator.
     * @throws MathArithmeticException if the denominator is {@code zero}
     */
    public Fraction(int num, int den) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.Fraction_263");
        if (ROR_equals(den, 0, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut798, _mut799, _mut800, _mut801, _mut802)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, num, den);
        }
        if (ROR_less(den, 0, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut803, _mut804, _mut805, _mut806, _mut807)) {
            if ((_mut818 ? (ROR_equals(num, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut808, _mut809, _mut810, _mut811, _mut812) && ROR_equals(den, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut813, _mut814, _mut815, _mut816, _mut817)) : (ROR_equals(num, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut808, _mut809, _mut810, _mut811, _mut812) || ROR_equals(den, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut813, _mut814, _mut815, _mut816, _mut817)))) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, num, den);
            }
            num = -num;
            den = -den;
        }
        // reduce numerator and denominator by greatest common denominator.
        final int d = ArithmeticUtils.gcd(num, den);
        if (ROR_greater(d, 1, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut819, _mut820, _mut821, _mut822, _mut823)) {
            num /= d;
            den /= d;
        }
        // move sign to numerator.
        if (ROR_less(den, 0, "org.apache.commons.math3.fraction.Fraction.Fraction_263", _mut824, _mut825, _mut826, _mut827, _mut828)) {
            num = -num;
            den = -den;
        }
        this.numerator = num;
        this.denominator = den;
    }

    /**
     * Returns the absolute value of this fraction.
     * @return the absolute value.
     */
    public Fraction abs() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.abs_297");
        Fraction ret;
        if (ROR_greater_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.abs_297", _mut829, _mut830, _mut831, _mut832, _mut833)) {
            ret = this;
        } else {
            ret = negate();
        }
        return ret;
    }

    /**
     * Compares this object to another based on size.
     * @param object the object to compare to
     * @return -1 if this is less than {@code object}, +1 if this is greater
     *         than {@code object}, 0 if they are equal.
     */
    public int compareTo(Fraction object) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.compareTo_313");
        long nOd = AOR_multiply(((long) numerator), object.denominator, "org.apache.commons.math3.fraction.Fraction.compareTo_313", _mut834, _mut835, _mut836, _mut837);
        long dOn = AOR_multiply(((long) denominator), object.numerator, "org.apache.commons.math3.fraction.Fraction.compareTo_313", _mut838, _mut839, _mut840, _mut841);
        return (ROR_less(nOd, dOn, "org.apache.commons.math3.fraction.Fraction.compareTo_313", _mut842, _mut843, _mut844, _mut845, _mut846)) ? -1 : ((ROR_greater(nOd, dOn, "org.apache.commons.math3.fraction.Fraction.compareTo_313", _mut847, _mut848, _mut849, _mut850, _mut851)) ? +1 : 0);
    }

    /**
     * Gets the fraction as a {@code double}. This calculates the fraction as
     * the numerator divided by denominator.
     * @return the fraction as a {@code double}
     */
    @Override
    public double doubleValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.doubleValue_324");
        return AOR_divide((double) numerator, (double) denominator, "org.apache.commons.math3.fraction.Fraction.doubleValue_324", _mut852, _mut853, _mut854, _mut855);
    }

    /**
     * Test for the equality of two fractions.  If the lowest term
     * numerator and denominators are the same for both fractions, the two
     * fractions are considered to be equal.
     * @param other fraction to test for equality to this fraction
     * @return true if two fractions are equal, false if object is
     *         {@code null}, not an instance of {@link Fraction}, or not equal
     *         to this fraction instance.
     */
    @Override
    public boolean equals(Object other) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.equals_338");
        if (this == other) {
            return true;
        }
        if (other instanceof Fraction) {
            // denominators can be compared directly for equality.
            Fraction rhs = (Fraction) other;
            return (_mut866 ? ((ROR_equals(numerator, rhs.numerator, "org.apache.commons.math3.fraction.Fraction.equals_338", _mut856, _mut857, _mut858, _mut859, _mut860)) || (ROR_equals(denominator, rhs.denominator, "org.apache.commons.math3.fraction.Fraction.equals_338", _mut861, _mut862, _mut863, _mut864, _mut865))) : ((ROR_equals(numerator, rhs.numerator, "org.apache.commons.math3.fraction.Fraction.equals_338", _mut856, _mut857, _mut858, _mut859, _mut860)) && (ROR_equals(denominator, rhs.denominator, "org.apache.commons.math3.fraction.Fraction.equals_338", _mut861, _mut862, _mut863, _mut864, _mut865))));
        }
        return false;
    }

    /**
     * Gets the fraction as a {@code float}. This calculates the fraction as
     * the numerator divided by denominator.
     * @return the fraction as a {@code float}
     */
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * Access the denominator.
     * @return the denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Access the numerator.
     * @return the numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets a hashCode for the fraction.
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.hashCode_383");
        return AOR_plus(AOR_multiply(37, (AOR_plus(AOR_multiply(37, 17, "org.apache.commons.math3.fraction.Fraction.hashCode_383", _mut867, _mut868, _mut869, _mut870), numerator, "org.apache.commons.math3.fraction.Fraction.hashCode_383", _mut871, _mut872, _mut873, _mut874)), "org.apache.commons.math3.fraction.Fraction.hashCode_383", _mut875, _mut876, _mut877, _mut878), denominator, "org.apache.commons.math3.fraction.Fraction.hashCode_383", _mut879, _mut880, _mut881, _mut882);
    }

    /**
     * Gets the fraction as an {@code int}. This returns the whole number part
     * of the fraction.
     * @return the whole number fraction part
     */
    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    /**
     * Gets the fraction as a {@code long}. This returns the whole number part
     * of the fraction.
     * @return the whole number fraction part
     */
    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    /**
     * Return the additive inverse of this fraction.
     * @return the negation of this fraction.
     */
    public Fraction negate() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.negate_412");
        if (ROR_equals(numerator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.negate_412", _mut883, _mut884, _mut885, _mut886, _mut887)) {
            throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, numerator, denominator);
        }
        return new Fraction(-numerator, denominator);
    }

    /**
     * Return the multiplicative inverse of this fraction.
     * @return the reciprocal fraction
     */
    public Fraction reciprocal() {
        return new Fraction(denominator, numerator);
    }

    /**
     * <p>Adds the value of this fraction to another, returning the result in reduced form.
     * The algorithm follows Knuth, 4.5.1.</p>
     *
     * @param fraction  the fraction to add, must not be {@code null}
     * @return a {@code Fraction} instance with the resulting values
     * @throws NullArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the resulting numerator or denominator exceeds
     *  {@code Integer.MAX_VALUE}
     */
    public Fraction add(Fraction fraction) {
        return addSub(fraction, true);
    }

    /**
     * Add an integer to the fraction.
     * @param i the {@code integer} to add.
     * @return this + i
     */
    public Fraction add(final int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.add_446");
        return new Fraction(AOR_plus(numerator, AOR_multiply(i, denominator, "org.apache.commons.math3.fraction.Fraction.add_446", _mut888, _mut889, _mut890, _mut891), "org.apache.commons.math3.fraction.Fraction.add_446", _mut892, _mut893, _mut894, _mut895), denominator);
    }

    /**
     * <p>Subtracts the value of another fraction from the value of this one,
     * returning the result in reduced form.</p>
     *
     * @param fraction  the fraction to subtract, must not be {@code null}
     * @return a {@code Fraction} instance with the resulting values
     * @throws NullArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the resulting numerator or denominator
     *   cannot be represented in an {@code int}.
     */
    public Fraction subtract(Fraction fraction) {
        return addSub(fraction, false);
    }

    /**
     * Subtract an integer from the fraction.
     * @param i the {@code integer} to subtract.
     * @return this - i
     */
    public Fraction subtract(final int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.subtract_469");
        return new Fraction(AOR_minus(numerator, AOR_multiply(i, denominator, "org.apache.commons.math3.fraction.Fraction.subtract_469", _mut896, _mut897, _mut898, _mut899), "org.apache.commons.math3.fraction.Fraction.subtract_469", _mut900, _mut901, _mut902, _mut903), denominator);
    }

    /**
     * Implement add and subtract using algorithm described in Knuth 4.5.1.
     *
     * @param fraction the fraction to subtract, must not be {@code null}
     * @param isAdd true to add, false to subtract
     * @return a {@code Fraction} instance with the resulting values
     * @throws NullArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the resulting numerator or denominator
     *   cannot be represented in an {@code int}.
     */
    private Fraction addSub(Fraction fraction, boolean isAdd) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.addSub_483");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        // zero is identity for addition.
        if (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut904, _mut905, _mut906, _mut907, _mut908)) {
            return isAdd ? fraction : fraction.negate();
        }
        if (ROR_equals(fraction.numerator, 0, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut909, _mut910, _mut911, _mut912, _mut913)) {
            return this;
        }
        // of the time.
        int d1 = ArithmeticUtils.gcd(denominator, fraction.denominator);
        if (ROR_equals(d1, 1, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut914, _mut915, _mut916, _mut917, _mut918)) {
            // result is ( (u*v' +/- u'v) / u'v')
            int uvp = ArithmeticUtils.mulAndCheck(numerator, fraction.denominator);
            int upv = ArithmeticUtils.mulAndCheck(fraction.numerator, denominator);
            return new Fraction(isAdd ? ArithmeticUtils.addAndCheck(uvp, upv) : ArithmeticUtils.subAndCheck(uvp, upv), ArithmeticUtils.mulAndCheck(denominator, fraction.denominator));
        }
        // t = u(v'/d1) +/- v(u'/d1)
        BigInteger uvp = BigInteger.valueOf(numerator).multiply(BigInteger.valueOf(AOR_divide(fraction.denominator, d1, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut919, _mut920, _mut921, _mut922)));
        BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(AOR_divide(denominator, d1, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut923, _mut924, _mut925, _mut926)));
        BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);
        // d2 = gcd(t,d1) = gcd(t mod d1, d1)
        int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();
        int d2 = (ROR_equals(tmodd1, 0, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut927, _mut928, _mut929, _mut930, _mut931)) ? d1 : ArithmeticUtils.gcd(tmodd1, d1);
        // result is (t/d2) / (u'/d1)(v'/d2)
        BigInteger w = t.divide(BigInteger.valueOf(d2));
        if (ROR_greater(w.bitLength(), 31, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut932, _mut933, _mut934, _mut935, _mut936)) {
            throw new MathArithmeticException(LocalizedFormats.NUMERATOR_OVERFLOW_AFTER_MULTIPLY, w);
        }
        return new Fraction(w.intValue(), ArithmeticUtils.mulAndCheck(AOR_divide(denominator, d1, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut937, _mut938, _mut939, _mut940), AOR_divide(fraction.denominator, d2, "org.apache.commons.math3.fraction.Fraction.addSub_483", _mut941, _mut942, _mut943, _mut944)));
    }

    /**
     * <p>Multiplies the value of this fraction by another, returning the
     * result in reduced form.</p>
     *
     * @param fraction  the fraction to multiply by, must not be {@code null}
     * @return a {@code Fraction} instance with the resulting values
     * @throws NullArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the resulting numerator or denominator exceeds
     *  {@code Integer.MAX_VALUE}
     */
    public Fraction multiply(Fraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.multiply_540");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if ((_mut955 ? (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut945, _mut946, _mut947, _mut948, _mut949) && ROR_equals(fraction.numerator, 0, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut950, _mut951, _mut952, _mut953, _mut954)) : (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut945, _mut946, _mut947, _mut948, _mut949) || ROR_equals(fraction.numerator, 0, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut950, _mut951, _mut952, _mut953, _mut954)))) {
            return ZERO;
        }
        // make sure we don't overflow unless the result *must* overflow.
        int d1 = ArithmeticUtils.gcd(numerator, fraction.denominator);
        int d2 = ArithmeticUtils.gcd(fraction.numerator, denominator);
        return getReducedFraction(ArithmeticUtils.mulAndCheck(AOR_divide(numerator, d1, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut956, _mut957, _mut958, _mut959), AOR_divide(fraction.numerator, d2, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut960, _mut961, _mut962, _mut963)), ArithmeticUtils.mulAndCheck(AOR_divide(denominator, d2, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut964, _mut965, _mut966, _mut967), AOR_divide(fraction.denominator, d1, "org.apache.commons.math3.fraction.Fraction.multiply_540", _mut968, _mut969, _mut970, _mut971)));
    }

    /**
     * Multiply the fraction by an integer.
     * @param i the {@code integer} to multiply by.
     * @return this * i
     */
    public Fraction multiply(final int i) {
        return multiply(new Fraction(i));
    }

    /**
     * <p>Divide the value of this fraction by another.</p>
     *
     * @param fraction  the fraction to divide by, must not be {@code null}
     * @return a {@code Fraction} instance with the resulting values
     * @throws IllegalArgumentException if the fraction is {@code null}
     * @throws MathArithmeticException if the fraction to divide by is zero
     * @throws MathArithmeticException if the resulting numerator or denominator exceeds
     *  {@code Integer.MAX_VALUE}
     */
    public Fraction divide(Fraction fraction) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.divide_575");
        if (fraction == null) {
            throw new NullArgumentException(LocalizedFormats.FRACTION);
        }
        if (ROR_equals(fraction.numerator, 0, "org.apache.commons.math3.fraction.Fraction.divide_575", _mut972, _mut973, _mut974, _mut975, _mut976)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_FRACTION_TO_DIVIDE_BY, fraction.numerator, fraction.denominator);
        }
        return multiply(fraction.reciprocal());
    }

    /**
     * Divide the fraction by an integer.
     * @param i the {@code integer} to divide by.
     * @return this * i
     */
    public Fraction divide(final int i) {
        return divide(new Fraction(i));
    }

    /**
     * <p>
     * Gets the fraction percentage as a {@code double}. This calculates the
     * fraction as the numerator divided by denominator multiplied by 100.
     * </p>
     *
     * @return the fraction percentage as a {@code double}.
     */
    public double percentageValue() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.percentageValue_603");
        return AOR_multiply(100, doubleValue(), "org.apache.commons.math3.fraction.Fraction.percentageValue_603", _mut977, _mut978, _mut979, _mut980);
    }

    /**
     * <p>Creates a {@code Fraction} instance with the 2 parts
     * of a fraction Y/Z.</p>
     *
     * <p>Any negative signs are resolved to be on the numerator.</p>
     *
     * @param numerator  the numerator, for example the three in 'three sevenths'
     * @param denominator  the denominator, for example the seven in 'three sevenths'
     * @return a new fraction instance, with the numerator and denominator reduced
     * @throws MathArithmeticException if the denominator is {@code zero}
     */
    public static Fraction getReducedFraction(int numerator, int denominator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.getReducedFraction_618");
        if (ROR_equals(denominator, 0, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut981, _mut982, _mut983, _mut984, _mut985)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION, numerator, denominator);
        }
        if (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut986, _mut987, _mut988, _mut989, _mut990)) {
            // normalize zero.
            return ZERO;
        }
        // allow 2^k/-2^31 as a valid fraction (where k>0)
        if ((_mut1001 ? (ROR_equals(denominator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut991, _mut992, _mut993, _mut994, _mut995) || ROR_equals((numerator & 1), 0, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut996, _mut997, _mut998, _mut999, _mut1000)) : (ROR_equals(denominator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut991, _mut992, _mut993, _mut994, _mut995) && ROR_equals((numerator & 1), 0, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut996, _mut997, _mut998, _mut999, _mut1000)))) {
            numerator /= 2;
            denominator /= 2;
        }
        if (ROR_less(denominator, 0, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut1002, _mut1003, _mut1004, _mut1005, _mut1006)) {
            if ((_mut1017 ? (ROR_equals(numerator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut1007, _mut1008, _mut1009, _mut1010, _mut1011) && ROR_equals(denominator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut1012, _mut1013, _mut1014, _mut1015, _mut1016)) : (ROR_equals(numerator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut1007, _mut1008, _mut1009, _mut1010, _mut1011) || ROR_equals(denominator, Integer.MIN_VALUE, "org.apache.commons.math3.fraction.Fraction.getReducedFraction_618", _mut1012, _mut1013, _mut1014, _mut1015, _mut1016)))) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION, numerator, denominator);
            }
            numerator = -numerator;
            denominator = -denominator;
        }
        // simplify fraction.
        int gcd = ArithmeticUtils.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        return new Fraction(numerator, denominator);
    }

    /**
     * <p>
     * Returns the {@code String} representing this fraction, ie
     * "num / dem" or just "num" if the denominator is one.
     * </p>
     *
     * @return a string representation of the fraction.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fraction.Fraction.toString_655");
        String str = null;
        if (ROR_equals(denominator, 1, "org.apache.commons.math3.fraction.Fraction.toString_655", _mut1018, _mut1019, _mut1020, _mut1021, _mut1022)) {
            str = Integer.toString(numerator);
        } else if (ROR_equals(numerator, 0, "org.apache.commons.math3.fraction.Fraction.toString_655", _mut1023, _mut1024, _mut1025, _mut1026, _mut1027)) {
            str = "0";
        } else {
            str = numerator + " / " + denominator;
        }
        return str;
    }

    /**
     * {@inheritDoc}
     */
    public FractionField getField() {
        return FractionField.getInstance();
    }
}
