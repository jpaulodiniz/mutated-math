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
 * Subclass of {@link Dfp} which hides the radix-10000 artifacts of the superclass.
 * This should give outward appearances of being a decimal number with DIGITS*4-3
 * decimal digits. This class can be subclassed to appear to be an arbitrary number
 * of decimal digits less than DIGITS*4-3.
 * @since 2.2
 */
public class DfpDec extends Dfp {

    @Conditional
    public static boolean _mut86675 = false, _mut86676 = false, _mut86677 = false, _mut86678 = false, _mut86679 = false, _mut86680 = false, _mut86681 = false, _mut86682 = false, _mut86683 = false, _mut86684 = false, _mut86685 = false, _mut86686 = false, _mut86687 = false, _mut86688 = false, _mut86689 = false, _mut86690 = false, _mut86691 = false, _mut86692 = false, _mut86693 = false, _mut86694 = false, _mut86695 = false, _mut86696 = false, _mut86697 = false, _mut86698 = false, _mut86699 = false, _mut86700 = false, _mut86701 = false, _mut86702 = false, _mut86703 = false, _mut86704 = false, _mut86705 = false, _mut86706 = false, _mut86707 = false, _mut86708 = false, _mut86709 = false, _mut86710 = false, _mut86711 = false, _mut86712 = false, _mut86713 = false, _mut86714 = false, _mut86715 = false, _mut86716 = false, _mut86717 = false, _mut86718 = false, _mut86719 = false, _mut86720 = false, _mut86721 = false, _mut86722 = false, _mut86723 = false, _mut86724 = false, _mut86725 = false, _mut86726 = false, _mut86727 = false, _mut86728 = false, _mut86729 = false, _mut86730 = false, _mut86731 = false, _mut86732 = false, _mut86733 = false, _mut86734 = false, _mut86735 = false, _mut86736 = false, _mut86737 = false, _mut86738 = false, _mut86739 = false, _mut86740 = false, _mut86741 = false, _mut86742 = false, _mut86743 = false, _mut86744 = false, _mut86745 = false, _mut86746 = false, _mut86747 = false, _mut86748 = false, _mut86749 = false, _mut86750 = false, _mut86751 = false, _mut86752 = false, _mut86753 = false, _mut86754 = false, _mut86755 = false, _mut86756 = false, _mut86757 = false, _mut86758 = false, _mut86759 = false, _mut86760 = false, _mut86761 = false, _mut86762 = false, _mut86763 = false, _mut86764 = false, _mut86765 = false, _mut86766 = false, _mut86767 = false, _mut86768 = false, _mut86769 = false, _mut86770 = false, _mut86771 = false, _mut86772 = false, _mut86773 = false, _mut86774 = false, _mut86775 = false, _mut86776 = false, _mut86777 = false, _mut86778 = false, _mut86779 = false, _mut86780 = false, _mut86781 = false, _mut86782 = false, _mut86783 = false, _mut86784 = false, _mut86785 = false, _mut86786 = false, _mut86787 = false, _mut86788 = false, _mut86789 = false, _mut86790 = false, _mut86791 = false, _mut86792 = false, _mut86793 = false, _mut86794 = false, _mut86795 = false, _mut86796 = false, _mut86797 = false, _mut86798 = false, _mut86799 = false, _mut86800 = false, _mut86801 = false, _mut86802 = false, _mut86803 = false, _mut86804 = false, _mut86805 = false, _mut86806 = false, _mut86807 = false, _mut86808 = false, _mut86809 = false, _mut86810 = false, _mut86811 = false, _mut86812 = false, _mut86813 = false, _mut86814 = false, _mut86815 = false, _mut86816 = false, _mut86817 = false, _mut86818 = false, _mut86819 = false, _mut86820 = false, _mut86821 = false, _mut86822 = false, _mut86823 = false, _mut86824 = false, _mut86825 = false, _mut86826 = false, _mut86827 = false, _mut86828 = false, _mut86829 = false, _mut86830 = false, _mut86831 = false, _mut86832 = false, _mut86833 = false, _mut86834 = false, _mut86835 = false, _mut86836 = false, _mut86837 = false, _mut86838 = false, _mut86839 = false, _mut86840 = false, _mut86841 = false, _mut86842 = false, _mut86843 = false, _mut86844 = false, _mut86845 = false, _mut86846 = false, _mut86847 = false, _mut86848 = false, _mut86849 = false, _mut86850 = false, _mut86851 = false, _mut86852 = false, _mut86853 = false, _mut86854 = false, _mut86855 = false, _mut86856 = false, _mut86857 = false, _mut86858 = false, _mut86859 = false, _mut86860 = false, _mut86861 = false, _mut86862 = false, _mut86863 = false, _mut86864 = false, _mut86865 = false, _mut86866 = false, _mut86867 = false, _mut86868 = false, _mut86869 = false, _mut86870 = false, _mut86871 = false, _mut86872 = false, _mut86873 = false, _mut86874 = false, _mut86875 = false, _mut86876 = false, _mut86877 = false, _mut86878 = false, _mut86879 = false, _mut86880 = false, _mut86881 = false, _mut86882 = false, _mut86883 = false, _mut86884 = false, _mut86885 = false, _mut86886 = false, _mut86887 = false, _mut86888 = false, _mut86889 = false, _mut86890 = false, _mut86891 = false, _mut86892 = false, _mut86893 = false, _mut86894 = false, _mut86895 = false, _mut86896 = false, _mut86897 = false, _mut86898 = false, _mut86899 = false, _mut86900 = false, _mut86901 = false, _mut86902 = false, _mut86903 = false, _mut86904 = false, _mut86905 = false, _mut86906 = false, _mut86907 = false, _mut86908 = false, _mut86909 = false, _mut86910 = false, _mut86911 = false, _mut86912 = false, _mut86913 = false, _mut86914 = false, _mut86915 = false, _mut86916 = false, _mut86917 = false, _mut86918 = false, _mut86919 = false, _mut86920 = false, _mut86921 = false, _mut86922 = false, _mut86923 = false, _mut86924 = false, _mut86925 = false, _mut86926 = false, _mut86927 = false, _mut86928 = false, _mut86929 = false, _mut86930 = false, _mut86931 = false, _mut86932 = false, _mut86933 = false, _mut86934 = false, _mut86935 = false, _mut86936 = false, _mut86937 = false, _mut86938 = false, _mut86939 = false, _mut86940 = false, _mut86941 = false, _mut86942 = false, _mut86943 = false, _mut86944 = false, _mut86945 = false, _mut86946 = false, _mut86947 = false, _mut86948 = false, _mut86949 = false, _mut86950 = false, _mut86951 = false, _mut86952 = false, _mut86953 = false, _mut86954 = false, _mut86955 = false, _mut86956 = false, _mut86957 = false, _mut86958 = false, _mut86959 = false, _mut86960 = false, _mut86961 = false, _mut86962 = false, _mut86963 = false, _mut86964 = false, _mut86965 = false, _mut86966 = false, _mut86967 = false, _mut86968 = false, _mut86969 = false, _mut86970 = false, _mut86971 = false, _mut86972 = false, _mut86973 = false, _mut86974 = false, _mut86975 = false, _mut86976 = false, _mut86977 = false, _mut86978 = false, _mut86979 = false, _mut86980 = false, _mut86981 = false, _mut86982 = false, _mut86983 = false, _mut86984 = false, _mut86985 = false, _mut86986 = false, _mut86987 = false, _mut86988 = false, _mut86989 = false, _mut86990 = false, _mut86991 = false, _mut86992 = false, _mut86993 = false, _mut86994 = false, _mut86995 = false, _mut86996 = false, _mut86997 = false, _mut86998 = false, _mut86999 = false, _mut87000 = false, _mut87001 = false, _mut87002 = false, _mut87003 = false, _mut87004 = false, _mut87005 = false, _mut87006 = false, _mut87007 = false, _mut87008 = false, _mut87009 = false, _mut87010 = false, _mut87011 = false, _mut87012 = false, _mut87013 = false, _mut87014 = false, _mut87015 = false, _mut87016 = false, _mut87017 = false, _mut87018 = false, _mut87019 = false, _mut87020 = false, _mut87021 = false, _mut87022 = false, _mut87023 = false, _mut87024 = false;

    /**
     * Makes an instance with a value of zero.
     * @param factory factory linked to this instance
     */
    protected DfpDec(final DfpField factory) {
        super(factory);
    }

    /**
     * Create an instance from a byte value.
     * @param factory factory linked to this instance
     * @param x value to convert to an instance
     */
    protected DfpDec(final DfpField factory, byte x) {
        super(factory, x);
    }

    /**
     * Create an instance from an int value.
     * @param factory factory linked to this instance
     * @param x value to convert to an instance
     */
    protected DfpDec(final DfpField factory, int x) {
        super(factory, x);
    }

    /**
     * Create an instance from a long value.
     * @param factory factory linked to this instance
     * @param x value to convert to an instance
     */
    protected DfpDec(final DfpField factory, long x) {
        super(factory, x);
    }

    /**
     * Create an instance from a double value.
     * @param factory factory linked to this instance
     * @param x value to convert to an instance
     */
    protected DfpDec(final DfpField factory, double x) {
        super(factory, x);
        round(0);
    }

    /**
     * Copy constructor.
     * @param d instance to copy
     */
    public DfpDec(final Dfp d) {
        super(d);
        round(0);
    }

    /**
     * Create an instance from a String representation.
     * @param factory factory linked to this instance
     * @param s string representation of the instance
     */
    protected DfpDec(final DfpField factory, final String s) {
        super(factory, s);
        round(0);
    }

    /**
     * Creates an instance with a non-finite value.
     * @param factory factory linked to this instance
     * @param sign sign of the Dfp to create
     * @param nans code of the value, must be one of {@link #INFINITE},
     * {@link #SNAN},  {@link #QNAN}
     */
    protected DfpDec(final DfpField factory, final byte sign, final byte nans) {
        super(factory, sign, nans);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance() {
        return new DfpDec(getField());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final byte x) {
        return new DfpDec(getField(), x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final int x) {
        return new DfpDec(getField(), x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final long x) {
        return new DfpDec(getField(), x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final double x) {
        return new DfpDec(getField(), x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final Dfp d) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.newInstance_126");
        // make sure we don't mix number with different precision
        if (ROR_not_equals(getField().getRadixDigits(), d.getField().getRadixDigits(), "org.apache.commons.math3.dfp.DfpDec.newInstance_126", _mut86675, _mut86676, _mut86677, _mut86678, _mut86679)) {
            getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, "newInstance", d, result);
        }
        return new DfpDec(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final String s) {
        return new DfpDec(getField(), s);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp newInstance(final byte sign, final byte nans) {
        return new DfpDec(getField(), sign, nans);
    }

    /**
     * Get the number of decimal digits this class is going to represent.
     * Default implementation returns {@link #getRadixDigits()}*4-3. Subclasses can
     * override this to return something less.
     * @return number of decimal digits this class is going to represent
     */
    protected int getDecimalDigits() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.getDecimalDigits_158");
        return AOR_minus(AOR_multiply(getRadixDigits(), 4, "org.apache.commons.math3.dfp.DfpDec.getDecimalDigits_158", _mut86680, _mut86681, _mut86682, _mut86683), 3, "org.apache.commons.math3.dfp.DfpDec.getDecimalDigits_158", _mut86684, _mut86685, _mut86686, _mut86687);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int round(int in) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.round_163");
        int msb = mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86688, _mut86689, _mut86690, _mut86691)];
        if (ROR_equals(msb, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86692, _mut86693, _mut86694, _mut86695, _mut86696)) {
            // special case -- this == zero
            return 0;
        }
        int cmaxdigits = AOR_multiply(mant.length, 4, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86697, _mut86698, _mut86699, _mut86700);
        int lsbthreshold = 1000;
        while (ROR_greater(lsbthreshold, msb, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86701, _mut86702, _mut86703, _mut86704, _mut86705)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.round_163");
            lsbthreshold /= 10;
            cmaxdigits--;
        }
        final int digits = getDecimalDigits();
        final int lsbshift = AOR_minus(cmaxdigits, digits, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86706, _mut86707, _mut86708, _mut86709);
        final int lsd = AOR_divide(lsbshift, 4, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86710, _mut86711, _mut86712, _mut86713);
        lsbthreshold = 1;
        for (int i = 0; ROR_less(i, AOR_remainder(lsbshift, 4, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86714, _mut86715, _mut86716, _mut86717), "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86718, _mut86719, _mut86720, _mut86721, _mut86722); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.round_163");
            lsbthreshold *= 10;
        }
        final int lsb = mant[lsd];
        if ((_mut86741 ? (ROR_less_equals(lsbthreshold, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86723, _mut86724, _mut86725, _mut86726, _mut86727) || ROR_equals(digits, AOR_minus(AOR_multiply(4, mant.length, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86728, _mut86729, _mut86730, _mut86731), 3, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86732, _mut86733, _mut86734, _mut86735), "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86736, _mut86737, _mut86738, _mut86739, _mut86740)) : (ROR_less_equals(lsbthreshold, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86723, _mut86724, _mut86725, _mut86726, _mut86727) && ROR_equals(digits, AOR_minus(AOR_multiply(4, mant.length, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86728, _mut86729, _mut86730, _mut86731), 3, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86732, _mut86733, _mut86734, _mut86735), "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86736, _mut86737, _mut86738, _mut86739, _mut86740)))) {
            return super.round(in);
        }
        // not looking at this after this point
        int discarded = in;
        final int n;
        if (ROR_equals(lsbthreshold, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86742, _mut86743, _mut86744, _mut86745, _mut86746)) {
            // look to the next digit for rounding
            n = AOR_remainder((AOR_divide(mant[AOR_minus(lsd, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86767, _mut86768, _mut86769, _mut86770)], 1000, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86771, _mut86772, _mut86773, _mut86774)), 10, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86775, _mut86776, _mut86777, _mut86778);
            mant[AOR_minus(lsd, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86779, _mut86780, _mut86781, _mut86782)] %= 1000;
            discarded |= mant[AOR_minus(lsd, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86783, _mut86784, _mut86785, _mut86786)];
        } else {
            n = AOR_remainder((AOR_divide(AOR_multiply(lsb, 10, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86747, _mut86748, _mut86749, _mut86750), lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86751, _mut86752, _mut86753, _mut86754)), 10, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86755, _mut86756, _mut86757, _mut86758);
            discarded |= AOR_remainder(lsb, (AOR_divide(lsbthreshold, 10, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86759, _mut86760, _mut86761, _mut86762)), "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86763, _mut86764, _mut86765, _mut86766);
        }
        for (int i = 0; ROR_less(i, lsd, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86787, _mut86788, _mut86789, _mut86790, _mut86791); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.round_163");
            // need to know if there are any discarded bits
            discarded |= mant[i];
            mant[i] = 0;
        }
        mant[lsd] = AOR_multiply(AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86792, _mut86793, _mut86794, _mut86795), lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86796, _mut86797, _mut86798, _mut86799);
        final boolean inc;
        switch(getField().getRoundingMode()) {
            case ROUND_DOWN:
                inc = false;
                break;
            case ROUND_UP:
                // round up if n!=0
                inc = (_mut86810 ? ((ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86800, _mut86801, _mut86802, _mut86803, _mut86804)) && (ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86805, _mut86806, _mut86807, _mut86808, _mut86809))) : ((ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86800, _mut86801, _mut86802, _mut86803, _mut86804)) || (ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86805, _mut86806, _mut86807, _mut86808, _mut86809))));
                break;
            case ROUND_HALF_UP:
                // round half up
                inc = ROR_greater_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86811, _mut86812, _mut86813, _mut86814, _mut86815);
                break;
            case ROUND_HALF_DOWN:
                // round half down
                inc = ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86816, _mut86817, _mut86818, _mut86819, _mut86820);
                break;
            case ROUND_HALF_EVEN:
                inc = (_mut86859 ? ((_mut86837 ? ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86821, _mut86822, _mut86823, _mut86824, _mut86825)) && ((_mut86836 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835))))) : ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86821, _mut86822, _mut86823, _mut86824, _mut86825)) || ((_mut86836 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)))))) && // round half-even
                ((_mut86858 ? ((_mut86848 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847))) || ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86849, _mut86850, _mut86851, _mut86852)) & 1), 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86853, _mut86854, _mut86855, _mut86856, _mut86857)) : ((_mut86848 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847))) && ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86849, _mut86850, _mut86851, _mut86852)) & 1), 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86853, _mut86854, _mut86855, _mut86856, _mut86857))))) : ((_mut86837 ? ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86821, _mut86822, _mut86823, _mut86824, _mut86825)) && ((_mut86836 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835))))) : ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86821, _mut86822, _mut86823, _mut86824, _mut86825)) || ((_mut86836 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86826, _mut86827, _mut86828, _mut86829, _mut86830) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86831, _mut86832, _mut86833, _mut86834, _mut86835)))))) || // round half-even
                ((_mut86858 ? ((_mut86848 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847))) || ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86849, _mut86850, _mut86851, _mut86852)) & 1), 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86853, _mut86854, _mut86855, _mut86856, _mut86857)) : ((_mut86848 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86838, _mut86839, _mut86840, _mut86841, _mut86842) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86843, _mut86844, _mut86845, _mut86846, _mut86847))) && ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86849, _mut86850, _mut86851, _mut86852)) & 1), 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86853, _mut86854, _mut86855, _mut86856, _mut86857))))));
                break;
            case ROUND_HALF_ODD:
                inc = (_mut86898 ? ((_mut86876 ? ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86860, _mut86861, _mut86862, _mut86863, _mut86864)) && ((_mut86875 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874))))) : ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86860, _mut86861, _mut86862, _mut86863, _mut86864)) || ((_mut86875 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)))))) && // round half-odd
                ((_mut86897 ? ((_mut86887 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886))) || ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86888, _mut86889, _mut86890, _mut86891)) & 1), 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86892, _mut86893, _mut86894, _mut86895, _mut86896)) : ((_mut86887 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886))) && ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86888, _mut86889, _mut86890, _mut86891)) & 1), 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86892, _mut86893, _mut86894, _mut86895, _mut86896))))) : ((_mut86876 ? ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86860, _mut86861, _mut86862, _mut86863, _mut86864)) && ((_mut86875 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874))))) : ((ROR_greater(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86860, _mut86861, _mut86862, _mut86863, _mut86864)) || ((_mut86875 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86865, _mut86866, _mut86867, _mut86868, _mut86869) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86870, _mut86871, _mut86872, _mut86873, _mut86874)))))) || // round half-odd
                ((_mut86897 ? ((_mut86887 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886))) || ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86888, _mut86889, _mut86890, _mut86891)) & 1), 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86892, _mut86893, _mut86894, _mut86895, _mut86896)) : ((_mut86887 ? (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) || ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886)) : (ROR_equals(n, 5, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86877, _mut86878, _mut86879, _mut86880, _mut86881) && ROR_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86882, _mut86883, _mut86884, _mut86885, _mut86886))) && ROR_equals(((AOR_divide(lsb, lsbthreshold, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86888, _mut86889, _mut86890, _mut86891)) & 1), 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86892, _mut86893, _mut86894, _mut86895, _mut86896))))));
                break;
            case ROUND_CEIL:
                // round ceil
                inc = (_mut86915 ? ((ROR_equals(sign, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86899, _mut86900, _mut86901, _mut86902, _mut86903)) || ((_mut86914 ? (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86904, _mut86905, _mut86906, _mut86907, _mut86908) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86909, _mut86910, _mut86911, _mut86912, _mut86913)) : (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86904, _mut86905, _mut86906, _mut86907, _mut86908) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86909, _mut86910, _mut86911, _mut86912, _mut86913))))) : ((ROR_equals(sign, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86899, _mut86900, _mut86901, _mut86902, _mut86903)) && ((_mut86914 ? (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86904, _mut86905, _mut86906, _mut86907, _mut86908) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86909, _mut86910, _mut86911, _mut86912, _mut86913)) : (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86904, _mut86905, _mut86906, _mut86907, _mut86908) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86909, _mut86910, _mut86911, _mut86912, _mut86913))))));
                break;
            case ROUND_FLOOR:
            default:
                // round floor
                inc = (_mut86932 ? ((ROR_equals(sign, -1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86916, _mut86917, _mut86918, _mut86919, _mut86920)) || ((_mut86931 ? (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86921, _mut86922, _mut86923, _mut86924, _mut86925) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86926, _mut86927, _mut86928, _mut86929, _mut86930)) : (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86921, _mut86922, _mut86923, _mut86924, _mut86925) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86926, _mut86927, _mut86928, _mut86929, _mut86930))))) : ((ROR_equals(sign, -1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86916, _mut86917, _mut86918, _mut86919, _mut86920)) && ((_mut86931 ? (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86921, _mut86922, _mut86923, _mut86924, _mut86925) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86926, _mut86927, _mut86928, _mut86929, _mut86930)) : (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86921, _mut86922, _mut86923, _mut86924, _mut86925) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86926, _mut86927, _mut86928, _mut86929, _mut86930))))));
                break;
        }
        if (inc) {
            // increment if necessary
            int rh = lsbthreshold;
            for (int i = lsd; ROR_less(i, mant.length, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86945, _mut86946, _mut86947, _mut86948, _mut86949); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.round_163");
                final int r = AOR_plus(mant[i], rh, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86933, _mut86934, _mut86935, _mut86936);
                rh = AOR_divide(r, RADIX, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86937, _mut86938, _mut86939, _mut86940);
                mant[i] = AOR_remainder(r, RADIX, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86941, _mut86942, _mut86943, _mut86944);
            }
            if (ROR_not_equals(rh, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86950, _mut86951, _mut86952, _mut86953, _mut86954)) {
                shiftRight();
                mant[AOR_minus(mant.length, 1, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86955, _mut86956, _mut86957, _mut86958)] = rh;
            }
        }
        // Check for exceptional cases and raise signals if necessary
        if (ROR_less(exp, MIN_EXP, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86959, _mut86960, _mut86961, _mut86962, _mut86963)) {
            // Gradual Underflow
            getField().setIEEEFlagsBits(DfpField.FLAG_UNDERFLOW);
            return DfpField.FLAG_UNDERFLOW;
        }
        if (ROR_greater(exp, MAX_EXP, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86964, _mut86965, _mut86966, _mut86967, _mut86968)) {
            // Overflow
            getField().setIEEEFlagsBits(DfpField.FLAG_OVERFLOW);
            return DfpField.FLAG_OVERFLOW;
        }
        if ((_mut86979 ? (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86969, _mut86970, _mut86971, _mut86972, _mut86973) && ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86974, _mut86975, _mut86976, _mut86977, _mut86978)) : (ROR_not_equals(n, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86969, _mut86970, _mut86971, _mut86972, _mut86973) || ROR_not_equals(discarded, 0, "org.apache.commons.math3.dfp.DfpDec.round_163", _mut86974, _mut86975, _mut86976, _mut86977, _mut86978)))) {
            // Inexact
            getField().setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            return DfpField.FLAG_INEXACT;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dfp nextAfter(Dfp x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.dfp.DfpDec.nextAfter_291");
        final String trapName = "nextAfter";
        // make sure we don't mix number with different precision
        if (ROR_not_equals(getField().getRadixDigits(), x.getField().getRadixDigits(), "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut86980, _mut86981, _mut86982, _mut86983, _mut86984)) {
            getField().setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, trapName, x, result);
        }
        boolean up = false;
        Dfp result;
        Dfp inc;
        // if this is greater than x
        if (this.lessThan(x)) {
            up = true;
        }
        if (equals(x)) {
            return newInstance(x);
        }
        if (lessThan(getZero())) {
            up = !up;
        }
        if (up) {
            inc = power10(AOR_plus(AOR_minus(intLog10(), getDecimalDigits(), "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut86997, _mut86998, _mut86999, _mut87000), 1, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87001, _mut87002, _mut87003, _mut87004));
            inc = copysign(inc, this);
            if (this.equals(getZero())) {
                inc = power10K(AOR_minus(AOR_minus(MIN_EXP, mant.length, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87005, _mut87006, _mut87007, _mut87008), 1, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87009, _mut87010, _mut87011, _mut87012));
            }
            if (inc.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = add(inc);
            }
        } else {
            inc = power10(intLog10());
            inc = copysign(inc, this);
            if (this.equals(inc)) {
                inc = inc.divide(power10(getDecimalDigits()));
            } else {
                inc = inc.divide(power10(AOR_minus(getDecimalDigits(), 1, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut86985, _mut86986, _mut86987, _mut86988)));
            }
            if (this.equals(getZero())) {
                inc = power10K(AOR_minus(AOR_minus(MIN_EXP, mant.length, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut86989, _mut86990, _mut86991, _mut86992), 1, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut86993, _mut86994, _mut86995, _mut86996));
            }
            if (inc.equals(getZero())) {
                result = copysign(newInstance(getZero()), this);
            } else {
                result = subtract(inc);
            }
        }
        if ((_mut87023 ? (ROR_equals(result.classify(), INFINITE, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87013, _mut87014, _mut87015, _mut87016, _mut87017) || ROR_not_equals(this.classify(), INFINITE, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87018, _mut87019, _mut87020, _mut87021, _mut87022)) : (ROR_equals(result.classify(), INFINITE, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87013, _mut87014, _mut87015, _mut87016, _mut87017) && ROR_not_equals(this.classify(), INFINITE, "org.apache.commons.math3.dfp.DfpDec.nextAfter_291", _mut87018, _mut87019, _mut87020, _mut87021, _mut87022)))) {
            getField().setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            result = dotrap(DfpField.FLAG_INEXACT, trapName, x, result);
        }
        if ((_mut87024 ? (result.equals(getZero()) || this.equals(getZero()) == false) : (result.equals(getZero()) && this.equals(getZero()) == false))) {
            getField().setIEEEFlagsBits(DfpField.FLAG_INEXACT);
            result = dotrap(DfpField.FLAG_INEXACT, trapName, x, result);
        }
        return result;
    }
}
