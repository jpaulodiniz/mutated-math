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

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Combinatorial utilities.
 *
 * @since 3.3
 */
public final class CombinatoricsUtils {

    @Conditional
    public static boolean _mut49753 = false, _mut49754 = false, _mut49755 = false, _mut49756 = false, _mut49757 = false, _mut49758 = false, _mut49759 = false, _mut49760 = false, _mut49761 = false, _mut49762 = false, _mut49763 = false, _mut49764 = false, _mut49765 = false, _mut49766 = false, _mut49767 = false, _mut49768 = false, _mut49769 = false, _mut49770 = false, _mut49771 = false, _mut49772 = false, _mut49773 = false, _mut49774 = false, _mut49775 = false, _mut49776 = false, _mut49777 = false, _mut49778 = false, _mut49779 = false, _mut49780 = false, _mut49781 = false, _mut49782 = false, _mut49783 = false, _mut49784 = false, _mut49785 = false, _mut49786 = false, _mut49787 = false, _mut49788 = false, _mut49789 = false, _mut49790 = false, _mut49791 = false, _mut49792 = false, _mut49793 = false, _mut49794 = false, _mut49795 = false, _mut49796 = false, _mut49797 = false, _mut49798 = false, _mut49799 = false, _mut49800 = false, _mut49801 = false, _mut49802 = false, _mut49803 = false, _mut49804 = false, _mut49805 = false, _mut49806 = false, _mut49807 = false, _mut49808 = false, _mut49809 = false, _mut49810 = false, _mut49811 = false, _mut49812 = false, _mut49813 = false, _mut49814 = false, _mut49815 = false, _mut49816 = false, _mut49817 = false, _mut49818 = false, _mut49819 = false, _mut49820 = false, _mut49821 = false, _mut49822 = false, _mut49823 = false, _mut49824 = false, _mut49825 = false, _mut49826 = false, _mut49827 = false, _mut49828 = false, _mut49829 = false, _mut49830 = false, _mut49831 = false, _mut49832 = false, _mut49833 = false, _mut49834 = false, _mut49835 = false, _mut49836 = false, _mut49837 = false, _mut49838 = false, _mut49839 = false, _mut49840 = false, _mut49841 = false, _mut49842 = false, _mut49843 = false, _mut49844 = false, _mut49845 = false, _mut49846 = false, _mut49847 = false, _mut49848 = false, _mut49849 = false, _mut49850 = false, _mut49851 = false, _mut49852 = false, _mut49853 = false, _mut49854 = false, _mut49855 = false, _mut49856 = false, _mut49857 = false, _mut49858 = false, _mut49859 = false, _mut49860 = false, _mut49861 = false, _mut49862 = false, _mut49863 = false, _mut49864 = false, _mut49865 = false, _mut49866 = false, _mut49867 = false, _mut49868 = false, _mut49869 = false, _mut49870 = false, _mut49871 = false, _mut49872 = false, _mut49873 = false, _mut49874 = false, _mut49875 = false, _mut49876 = false, _mut49877 = false, _mut49878 = false, _mut49879 = false, _mut49880 = false, _mut49881 = false, _mut49882 = false, _mut49883 = false, _mut49884 = false, _mut49885 = false, _mut49886 = false, _mut49887 = false, _mut49888 = false, _mut49889 = false, _mut49890 = false, _mut49891 = false, _mut49892 = false, _mut49893 = false, _mut49894 = false, _mut49895 = false, _mut49896 = false, _mut49897 = false, _mut49898 = false, _mut49899 = false, _mut49900 = false, _mut49901 = false, _mut49902 = false, _mut49903 = false, _mut49904 = false, _mut49905 = false, _mut49906 = false, _mut49907 = false, _mut49908 = false, _mut49909 = false, _mut49910 = false, _mut49911 = false, _mut49912 = false, _mut49913 = false, _mut49914 = false, _mut49915 = false, _mut49916 = false, _mut49917 = false, _mut49918 = false, _mut49919 = false, _mut49920 = false, _mut49921 = false, _mut49922 = false, _mut49923 = false, _mut49924 = false, _mut49925 = false, _mut49926 = false, _mut49927 = false, _mut49928 = false, _mut49929 = false, _mut49930 = false, _mut49931 = false, _mut49932 = false, _mut49933 = false, _mut49934 = false, _mut49935 = false, _mut49936 = false, _mut49937 = false, _mut49938 = false, _mut49939 = false, _mut49940 = false, _mut49941 = false, _mut49942 = false, _mut49943 = false, _mut49944 = false, _mut49945 = false, _mut49946 = false, _mut49947 = false, _mut49948 = false, _mut49949 = false, _mut49950 = false, _mut49951 = false, _mut49952 = false, _mut49953 = false, _mut49954 = false, _mut49955 = false, _mut49956 = false, _mut49957 = false, _mut49958 = false, _mut49959 = false, _mut49960 = false, _mut49961 = false, _mut49962 = false, _mut49963 = false, _mut49964 = false, _mut49965 = false, _mut49966 = false, _mut49967 = false, _mut49968 = false, _mut49969 = false, _mut49970 = false, _mut49971 = false, _mut49972 = false, _mut49973 = false, _mut49974 = false, _mut49975 = false, _mut49976 = false, _mut49977 = false, _mut49978 = false, _mut49979 = false, _mut49980 = false, _mut49981 = false, _mut49982 = false, _mut49983 = false, _mut49984 = false, _mut49985 = false, _mut49986 = false, _mut49987 = false, _mut49988 = false, _mut49989 = false, _mut49990 = false, _mut49991 = false, _mut49992 = false, _mut49993 = false, _mut49994 = false, _mut49995 = false, _mut49996 = false, _mut49997 = false, _mut49998 = false, _mut49999 = false, _mut50000 = false, _mut50001 = false, _mut50002 = false, _mut50003 = false, _mut50004 = false, _mut50005 = false, _mut50006 = false, _mut50007 = false, _mut50008 = false, _mut50009 = false, _mut50010 = false, _mut50011 = false, _mut50012 = false, _mut50013 = false, _mut50014 = false, _mut50015 = false, _mut50016 = false, _mut50017 = false, _mut50018 = false, _mut50019 = false, _mut50020 = false, _mut50021 = false, _mut50022 = false, _mut50023 = false, _mut50024 = false, _mut50025 = false, _mut50026 = false, _mut50027 = false, _mut50028 = false, _mut50029 = false, _mut50030 = false, _mut50031 = false, _mut50032 = false, _mut50033 = false, _mut50034 = false, _mut50035 = false, _mut50036 = false, _mut50037 = false, _mut50038 = false, _mut50039 = false, _mut50040 = false, _mut50041 = false, _mut50042 = false, _mut50043 = false, _mut50044 = false, _mut50045 = false, _mut50046 = false, _mut50047 = false, _mut50048 = false, _mut50049 = false, _mut50050 = false, _mut50051 = false, _mut50052 = false, _mut50053 = false, _mut50054 = false, _mut50055 = false, _mut50056 = false, _mut50057 = false, _mut50058 = false, _mut50059 = false, _mut50060 = false, _mut50061 = false, _mut50062 = false, _mut50063 = false, _mut50064 = false, _mut50065 = false, _mut50066 = false, _mut50067 = false, _mut50068 = false, _mut50069 = false, _mut50070 = false, _mut50071 = false, _mut50072 = false, _mut50073 = false, _mut50074 = false, _mut50075 = false, _mut50076 = false, _mut50077 = false, _mut50078 = false, _mut50079 = false, _mut50080 = false, _mut50081 = false, _mut50082 = false, _mut50083 = false, _mut50084 = false, _mut50085 = false, _mut50086 = false, _mut50087 = false, _mut50088 = false, _mut50089 = false, _mut50090 = false, _mut50091 = false, _mut50092 = false, _mut50093 = false, _mut50094 = false, _mut50095 = false, _mut50096 = false, _mut50097 = false, _mut50098 = false, _mut50099 = false, _mut50100 = false, _mut50101 = false, _mut50102 = false, _mut50103 = false, _mut50104 = false, _mut50105 = false, _mut50106 = false, _mut50107 = false, _mut50108 = false, _mut50109 = false, _mut50110 = false, _mut50111 = false, _mut50112 = false, _mut50113 = false, _mut50114 = false, _mut50115 = false, _mut50116 = false, _mut50117 = false, _mut50118 = false, _mut50119 = false, _mut50120 = false, _mut50121 = false, _mut50122 = false, _mut50123 = false, _mut50124 = false, _mut50125 = false, _mut50126 = false, _mut50127 = false, _mut50128 = false, _mut50129 = false, _mut50130 = false, _mut50131 = false, _mut50132 = false, _mut50133 = false, _mut50134 = false, _mut50135 = false, _mut50136 = false, _mut50137 = false, _mut50138 = false, _mut50139 = false, _mut50140 = false, _mut50141 = false, _mut50142 = false, _mut50143 = false, _mut50144 = false, _mut50145 = false, _mut50146 = false, _mut50147 = false, _mut50148 = false, _mut50149 = false, _mut50150 = false, _mut50151 = false, _mut50152 = false, _mut50153 = false, _mut50154 = false, _mut50155 = false, _mut50156 = false, _mut50157 = false, _mut50158 = false, _mut50159 = false, _mut50160 = false, _mut50161 = false, _mut50162 = false, _mut50163 = false, _mut50164 = false, _mut50165 = false, _mut50166 = false, _mut50167 = false;

    /**
     * All long-representable factorials
     */
    static final long[] FACTORIALS = new long[] { 1l, 1l, 2l, 6l, 24l, 120l, 720l, 5040l, 40320l, 362880l, 3628800l, 39916800l, 479001600l, 6227020800l, 87178291200l, 1307674368000l, 20922789888000l, 355687428096000l, 6402373705728000l, 121645100408832000l, 2432902008176640000l };

    /**
     * Stirling numbers of the second kind.
     */
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<long[][]>(null);

    /**
     * Private constructor (class contains only static methods).
     */
    private CombinatoricsUtils() {
    }

    /**
     * Returns an exact representation of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code MathIllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code long}. The
     * largest value of {@code n} for which all coefficients are
     * {@code  < Long.MAX_VALUE} is 66. If the computed value exceeds
     * {@code Long.MAX_VALUE} a {@code MathArithMeticException} is
     * thrown.</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     */
    public static long binomialCoefficient(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77");
        CombinatoricsUtils.checkBinomial(n, k);
        if ((_mut49763 ? ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49753, _mut49754, _mut49755, _mut49756, _mut49757)) && (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49758, _mut49759, _mut49760, _mut49761, _mut49762))) : ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49753, _mut49754, _mut49755, _mut49756, _mut49757)) || (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49758, _mut49759, _mut49760, _mut49761, _mut49762))))) {
            return 1;
        }
        if ((_mut49778 ? ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49764, _mut49765, _mut49766, _mut49767, _mut49768)) && (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49769, _mut49770, _mut49771, _mut49772), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49773, _mut49774, _mut49775, _mut49776, _mut49777))) : ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49764, _mut49765, _mut49766, _mut49767, _mut49768)) || (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49769, _mut49770, _mut49771, _mut49772), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49773, _mut49774, _mut49775, _mut49776, _mut49777))))) {
            return n;
        }
        // Use symmetry for large k
        if (ROR_greater(k, AOR_divide(n, 2, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49779, _mut49780, _mut49781, _mut49782), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49783, _mut49784, _mut49785, _mut49786, _mut49787)) {
            return binomialCoefficient(n, AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49788, _mut49789, _mut49790, _mut49791));
        }
        // (n choose k) == (n-1 choose k-1) * n / k
        long result = 1;
        if (ROR_less_equals(n, 61, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49792, _mut49793, _mut49794, _mut49795, _mut49796)) {
            // For n <= 61, the naive implementation cannot overflow.
            int i = AOR_plus(AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49856, _mut49857, _mut49858, _mut49859), 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49860, _mut49861, _mut49862, _mut49863);
            for (int j = 1; ROR_less_equals(j, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49872, _mut49873, _mut49874, _mut49875, _mut49876); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77");
                result = AOR_divide(AOR_multiply(result, i, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49864, _mut49865, _mut49866, _mut49867), j, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49868, _mut49869, _mut49870, _mut49871);
                i++;
            }
        } else if (ROR_less_equals(n, 66, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49797, _mut49798, _mut49799, _mut49800, _mut49801)) {
            // but we must take care not to overflow intermediate values.
            int i = AOR_plus(AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49827, _mut49828, _mut49829, _mut49830), 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49831, _mut49832, _mut49833, _mut49834);
            for (int j = 1; ROR_less_equals(j, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49851, _mut49852, _mut49853, _mut49854, _mut49855); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77");
                // result * (i/d).
                final long d = ArithmeticUtils.gcd(i, j);
                result = AOR_multiply((AOR_divide(result, (AOR_divide(j, d, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49835, _mut49836, _mut49837, _mut49838)), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49839, _mut49840, _mut49841, _mut49842)), (AOR_divide(i, d, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49843, _mut49844, _mut49845, _mut49846)), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49847, _mut49848, _mut49849, _mut49850);
                i++;
            }
        } else {
            // unnecessary.
            int i = AOR_plus(AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49802, _mut49803, _mut49804, _mut49805), 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49806, _mut49807, _mut49808, _mut49809);
            for (int j = 1; ROR_less_equals(j, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49822, _mut49823, _mut49824, _mut49825, _mut49826); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77");
                final long d = ArithmeticUtils.gcd(i, j);
                result = ArithmeticUtils.mulAndCheck(AOR_divide(result, (AOR_divide(j, d, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49810, _mut49811, _mut49812, _mut49813)), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49814, _mut49815, _mut49816, _mut49817), AOR_divide(i, d, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient_77", _mut49818, _mut49819, _mut49820, _mut49821));
                i++;
            }
        }
        return result;
    }

    /**
     * Returns a {@code double} representation of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code IllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code double}. The
     * largest value of {@code n} for which all coefficients are less than
     * Double.MAX_VALUE is 1029. If the computed value exceeds Double.MAX_VALUE,
     * Double.POSITIVE_INFINITY is returned</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     */
    public static double binomialCoefficientDouble(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158");
        CombinatoricsUtils.checkBinomial(n, k);
        if ((_mut49887 ? ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49877, _mut49878, _mut49879, _mut49880, _mut49881)) && (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49882, _mut49883, _mut49884, _mut49885, _mut49886))) : ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49877, _mut49878, _mut49879, _mut49880, _mut49881)) || (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49882, _mut49883, _mut49884, _mut49885, _mut49886))))) {
            return 1d;
        }
        if ((_mut49902 ? ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49888, _mut49889, _mut49890, _mut49891, _mut49892)) && (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49893, _mut49894, _mut49895, _mut49896), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49897, _mut49898, _mut49899, _mut49900, _mut49901))) : ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49888, _mut49889, _mut49890, _mut49891, _mut49892)) || (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49893, _mut49894, _mut49895, _mut49896), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49897, _mut49898, _mut49899, _mut49900, _mut49901))))) {
            return n;
        }
        if (ROR_greater(k, AOR_divide(n, 2, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49903, _mut49904, _mut49905, _mut49906), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49907, _mut49908, _mut49909, _mut49910, _mut49911)) {
            return binomialCoefficientDouble(n, AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49912, _mut49913, _mut49914, _mut49915));
        }
        if (ROR_less(n, 67, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49916, _mut49917, _mut49918, _mut49919, _mut49920)) {
            return binomialCoefficient(n, k);
        }
        double result = 1d;
        for (int i = 1; ROR_less_equals(i, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49933, _mut49934, _mut49935, _mut49936, _mut49937); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158");
            result *= AOR_divide((double) (AOR_plus(AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49921, _mut49922, _mut49923, _mut49924), i, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49925, _mut49926, _mut49927, _mut49928)), (double) i, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49929, _mut49930, _mut49931, _mut49932);
        }
        return FastMath.floor(AOR_plus(result, 0.5, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientDouble_158", _mut49938, _mut49939, _mut49940, _mut49941));
    }

    /**
     * Returns the natural {@code log} of the <a
     * href="http://mathworld.wolfram.com/BinomialCoefficient.html"> Binomial
     * Coefficient</a>, "{@code n choose k}", the number of
     * {@code k}-element subsets that can be selected from an
     * {@code n}-element set.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code 0 <= k <= n } (otherwise
     * {@code MathIllegalArgumentException} is thrown)</li>
     * </ul></p>
     *
     * @param n the size of the set
     * @param k the size of the subsets to be counted
     * @return {@code n choose k}
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if the result is too large to be
     * represented by a long integer.
     */
    public static double binomialCoefficientLog(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203");
        CombinatoricsUtils.checkBinomial(n, k);
        if ((_mut49952 ? ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49942, _mut49943, _mut49944, _mut49945, _mut49946)) && (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49947, _mut49948, _mut49949, _mut49950, _mut49951))) : ((ROR_equals(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49942, _mut49943, _mut49944, _mut49945, _mut49946)) || (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49947, _mut49948, _mut49949, _mut49950, _mut49951))))) {
            return 0;
        }
        if ((_mut49967 ? ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49953, _mut49954, _mut49955, _mut49956, _mut49957)) && (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49958, _mut49959, _mut49960, _mut49961), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49962, _mut49963, _mut49964, _mut49965, _mut49966))) : ((ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49953, _mut49954, _mut49955, _mut49956, _mut49957)) || (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49958, _mut49959, _mut49960, _mut49961), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49962, _mut49963, _mut49964, _mut49965, _mut49966))))) {
            return FastMath.log(n);
        }
        /*
         * For values small enough to do exact integer computation,
         * return the log of the exact value
         */
        if (ROR_less(n, 67, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49968, _mut49969, _mut49970, _mut49971, _mut49972)) {
            return FastMath.log(binomialCoefficient(n, k));
        }
        /*
         * Return the log of binomialCoefficientDouble for values that will not
         * overflow binomialCoefficientDouble
         */
        if (ROR_less(n, 1030, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49973, _mut49974, _mut49975, _mut49976, _mut49977)) {
            return FastMath.log(binomialCoefficientDouble(n, k));
        }
        if (ROR_greater(k, AOR_divide(n, 2, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49978, _mut49979, _mut49980, _mut49981), "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49982, _mut49983, _mut49984, _mut49985, _mut49986)) {
            return binomialCoefficientLog(n, AOR_minus(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49987, _mut49988, _mut49989, _mut49990));
        }
        /*
         * Sum logs for values that could overflow
         */
        double logSum = 0;
        // n!/(n-k)!
        for (int i = n - k + 1; ROR_less_equals(i, n, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49991, _mut49992, _mut49993, _mut49994, _mut49995); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203");
            logSum += FastMath.log(i);
        }
        // divide by k!
        for (int i = 2; ROR_less_equals(i, k, "org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203", _mut49996, _mut49997, _mut49998, _mut49999, _mut50000); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficientLog_203");
            logSum -= FastMath.log(i);
        }
        return logSum;
    }

    /**
     * Returns n!. Shorthand for {@code n} <a
     * href="http://mathworld.wolfram.com/Factorial.html"> Factorial</a>, the
     * product of the numbers {@code 1,...,n}.
     * <p>
     * <Strong>Preconditions</strong>:
     * <ul>
     * <li> {@code n >= 0} (otherwise
     * {@code MathIllegalArgumentException} is thrown)</li>
     * <li> The result is small enough to fit into a {@code long}. The
     * largest value of {@code n} for which {@code n!} does not exceed
     * Long.MAX_VALUE} is 20. If the computed value exceeds {@code Long.MAX_VALUE}
     * an {@code MathArithMeticException } is thrown.</li>
     * </ul>
     * </p>
     *
     * @param n argument
     * @return {@code n!}
     * @throws MathArithmeticException if the result is too large to be represented
     * by a {@code long}.
     * @throws NotPositiveException if {@code n < 0}.
     * @throws MathArithmeticException if {@code n > 20}: The factorial value is too
     * large to fit in a {@code long}.
     */
    public static long factorial(final int n) throws NotPositiveException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.factorial_275");
        if (ROR_less(n, 0, "org.apache.commons.math3.util.CombinatoricsUtils.factorial_275", _mut50001, _mut50002, _mut50003, _mut50004, _mut50005)) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (ROR_greater(n, 20, "org.apache.commons.math3.util.CombinatoricsUtils.factorial_275", _mut50006, _mut50007, _mut50008, _mut50009, _mut50010)) {
            throw new MathArithmeticException();
        }
        return FACTORIALS[n];
    }

    /**
     * Compute n!, the<a href="http://mathworld.wolfram.com/Factorial.html">
     * factorial</a> of {@code n} (the product of the numbers 1 to n), as a
     * {@code double}.
     * The result should be small enough to fit into a {@code double}: The
     * largest {@code n} for which {@code n!} does not exceed
     * {@code Double.MAX_VALUE} is 170. If the computed value exceeds
     * {@code Double.MAX_VALUE}, {@code Double.POSITIVE_INFINITY} is returned.
     *
     * @param n Argument.
     * @return {@code n!}
     * @throws NotPositiveException if {@code n < 0}.
     */
    public static double factorialDouble(final int n) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble_299");
        if (ROR_less(n, 0, "org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble_299", _mut50011, _mut50012, _mut50013, _mut50014, _mut50015)) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (ROR_less(n, 21, "org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble_299", _mut50016, _mut50017, _mut50018, _mut50019, _mut50020)) {
            return FACTORIALS[n];
        }
        return FastMath.floor(AOR_plus(FastMath.exp(CombinatoricsUtils.factorialLog(n)), 0.5, "org.apache.commons.math3.util.CombinatoricsUtils.factorialDouble_299", _mut50021, _mut50022, _mut50023, _mut50024));
    }

    /**
     * Compute the natural logarithm of the factorial of {@code n}.
     *
     * @param n Argument.
     * @return {@code n!}
     * @throws NotPositiveException if {@code n < 0}.
     */
    public static double factorialLog(final int n) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.factorialLog_317");
        if (ROR_less(n, 0, "org.apache.commons.math3.util.CombinatoricsUtils.factorialLog_317", _mut50025, _mut50026, _mut50027, _mut50028, _mut50029)) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, n);
        }
        if (ROR_less(n, 21, "org.apache.commons.math3.util.CombinatoricsUtils.factorialLog_317", _mut50030, _mut50031, _mut50032, _mut50033, _mut50034)) {
            return FastMath.log(FACTORIALS[n]);
        }
        double logSum = 0;
        for (int i = 2; ROR_less_equals(i, n, "org.apache.commons.math3.util.CombinatoricsUtils.factorialLog_317", _mut50035, _mut50036, _mut50037, _mut50038, _mut50039); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.factorialLog_317");
            logSum += FastMath.log(i);
        }
        return logSum;
    }

    /**
     * Returns the <a
     * href="http://mathworld.wolfram.com/StirlingNumberoftheSecondKind.html">
     * Stirling number of the second kind</a>, "{@code S(n,k)}", the number of
     * ways of partitioning an {@code n}-element set into {@code k} non-empty
     * subsets.
     * <p>
     * The preconditions are {@code 0 <= k <= n } (otherwise
     * {@code NotPositiveException} is thrown)
     * </p>
     * @param n the size of the set
     * @param k the number of non-empty subsets
     * @return {@code S(n,k)}
     * @throws NotPositiveException if {@code k < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     * @throws MathArithmeticException if some overflow happens, typically for n exceeding 25 and
     * k between 20 and n-2 (S(n,n-1) is handled specifically and does not overflow)
     * @since 3.1
     */
    public static long stirlingS2(final int n, final int k) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351");
        if (ROR_less(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50040, _mut50041, _mut50042, _mut50043, _mut50044)) {
            throw new NotPositiveException(k);
        }
        if (ROR_greater(k, n, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50045, _mut50046, _mut50047, _mut50048, _mut50049)) {
            throw new NumberIsTooLargeException(k, n, true);
        }
        long[][] stirlingS2 = STIRLING_S2.get();
        if (stirlingS2 == null) {
            // we must stop computation at row 26
            final int maxIndex = 26;
            stirlingS2 = new long[maxIndex][];
            stirlingS2[0] = new long[] { 1l };
            for (int i = 1; ROR_less(i, stirlingS2.length, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50079, _mut50080, _mut50081, _mut50082, _mut50083); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351");
                stirlingS2[i] = new long[AOR_plus(i, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50050, _mut50051, _mut50052, _mut50053)];
                stirlingS2[i][0] = 0;
                stirlingS2[i][1] = 1;
                stirlingS2[i][i] = 1;
                for (int j = 2; ROR_less(j, i, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50074, _mut50075, _mut50076, _mut50077, _mut50078); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351");
                    stirlingS2[i][j] = AOR_plus(AOR_multiply(j, stirlingS2[AOR_minus(i, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50054, _mut50055, _mut50056, _mut50057)][j], "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50058, _mut50059, _mut50060, _mut50061), stirlingS2[AOR_minus(i, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50066, _mut50067, _mut50068, _mut50069)][AOR_minus(j, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50062, _mut50063, _mut50064, _mut50065)], "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50070, _mut50071, _mut50072, _mut50073);
                }
            }
            // atomically save the cache
            STIRLING_S2.compareAndSet(null, stirlingS2);
        }
        if (ROR_less(n, stirlingS2.length, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50084, _mut50085, _mut50086, _mut50087, _mut50088)) {
            // the number is in the small cache
            return stirlingS2[n][k];
        } else {
            // use explicit formula to compute the number without caching it
            if (ROR_equals(k, 0, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50089, _mut50090, _mut50091, _mut50092, _mut50093)) {
                return 0;
            } else if ((_mut50104 ? (ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50094, _mut50095, _mut50096, _mut50097, _mut50098) && ROR_equals(k, n, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50099, _mut50100, _mut50101, _mut50102, _mut50103)) : (ROR_equals(k, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50094, _mut50095, _mut50096, _mut50097, _mut50098) || ROR_equals(k, n, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50099, _mut50100, _mut50101, _mut50102, _mut50103)))) {
                return 1;
            } else if (ROR_equals(k, 2, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50105, _mut50106, _mut50107, _mut50108, _mut50109)) {
                return AOR_minus((1l << (AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50150, _mut50151, _mut50152, _mut50153))), 1l, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50154, _mut50155, _mut50156, _mut50157);
            } else if (ROR_equals(k, AOR_minus(n, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50110, _mut50111, _mut50112, _mut50113), "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50114, _mut50115, _mut50116, _mut50117, _mut50118)) {
                return binomialCoefficient(n, 2);
            } else {
                // definition formula: note that this may trigger some overflow
                long sum = 0;
                long sign = (ROR_equals((k & 0x1), 0, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50119, _mut50120, _mut50121, _mut50122, _mut50123)) ? 1 : -1;
                for (int j = 1; ROR_less_equals(j, k, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50141, _mut50142, _mut50143, _mut50144, _mut50145); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351");
                    sign = -sign;
                    sum += AOR_multiply(AOR_multiply(sign, binomialCoefficient(k, j), "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50124, _mut50125, _mut50126, _mut50127), ArithmeticUtils.pow(j, n), "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50128, _mut50129, _mut50130, _mut50131);
                    if (ROR_less(sum, 0, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50132, _mut50133, _mut50134, _mut50135, _mut50136)) {
                        // there was an overflow somewhere
                        throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, n, 0, AOR_minus(stirlingS2.length, 1, "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50137, _mut50138, _mut50139, _mut50140));
                    }
                }
                return AOR_divide(sum, factorial(k), "org.apache.commons.math3.util.CombinatoricsUtils.stirlingS2_351", _mut50146, _mut50147, _mut50148, _mut50149);
            }
        }
    }

    /**
     * Returns an iterator whose range is the k-element subsets of {0, ..., n - 1}
     * represented as {@code int[]} arrays.
     * <p>
     * The arrays returned by the iterator are sorted in descending order and
     * they are visited in lexicographic order with significance from right to
     * left. For example, combinationsIterator(4, 2) returns an Iterator that
     * will generate the following sequence of arrays on successive calls to
     * {@code next()}:</p><p>
     * {@code [0, 1], [0, 2], [1, 2], [0, 3], [1, 3], [2, 3]}
     * </p><p>
     * If {@code k == 0} an Iterator containing an empty array is returned and
     * if {@code k == n} an Iterator containing [0, ..., n -1] is returned.</p>
     *
     * @param n Size of the set from which subsets are selected.
     * @param k Size of the subsets to be enumerated.
     * @return an {@link Iterator iterator} over the k-sets in n.
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     */
    public static Iterator<int[]> combinationsIterator(int n, int k) {
        return new Combinations(n, k).iterator();
    }

    /**
     * Check binomial preconditions.
     *
     * @param n Size of the set.
     * @param k Size of the subsets to be counted.
     * @throws NotPositiveException if {@code n < 0}.
     * @throws NumberIsTooLargeException if {@code k > n}.
     */
    public static void checkBinomial(final int n, final int k) throws NumberIsTooLargeException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial_450");
        if (ROR_less(n, k, "org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial_450", _mut50158, _mut50159, _mut50160, _mut50161, _mut50162)) {
            throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, k, n, true);
        }
        if (ROR_less(n, 0, "org.apache.commons.math3.util.CombinatoricsUtils.checkBinomial_450", _mut50163, _mut50164, _mut50165, _mut50166, _mut50167)) {
            throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, n);
        }
    }
}
