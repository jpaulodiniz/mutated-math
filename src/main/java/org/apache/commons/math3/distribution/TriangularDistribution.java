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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the triangular real distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Triangular_distribution">
 * Triangular distribution (Wikipedia)</a>
 *
 * @since 3.0
 */
public class TriangularDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut57674 = false, _mut57675 = false, _mut57676 = false, _mut57677 = false, _mut57678 = false, _mut57679 = false, _mut57680 = false, _mut57681 = false, _mut57682 = false, _mut57683 = false, _mut57684 = false, _mut57685 = false, _mut57686 = false, _mut57687 = false, _mut57688 = false, _mut57689 = false, _mut57690 = false, _mut57691 = false, _mut57692 = false, _mut57693 = false, _mut57694 = false, _mut57695 = false, _mut57696 = false, _mut57697 = false, _mut57698 = false, _mut57699 = false, _mut57700 = false, _mut57701 = false, _mut57702 = false, _mut57703 = false, _mut57704 = false, _mut57705 = false, _mut57706 = false, _mut57707 = false, _mut57708 = false, _mut57709 = false, _mut57710 = false, _mut57711 = false, _mut57712 = false, _mut57713 = false, _mut57714 = false, _mut57715 = false, _mut57716 = false, _mut57717 = false, _mut57718 = false, _mut57719 = false, _mut57720 = false, _mut57721 = false, _mut57722 = false, _mut57723 = false, _mut57724 = false, _mut57725 = false, _mut57726 = false, _mut57727 = false, _mut57728 = false, _mut57729 = false, _mut57730 = false, _mut57731 = false, _mut57732 = false, _mut57733 = false, _mut57734 = false, _mut57735 = false, _mut57736 = false, _mut57737 = false, _mut57738 = false, _mut57739 = false, _mut57740 = false, _mut57741 = false, _mut57742 = false, _mut57743 = false, _mut57744 = false, _mut57745 = false, _mut57746 = false, _mut57747 = false, _mut57748 = false, _mut57749 = false, _mut57750 = false, _mut57751 = false, _mut57752 = false, _mut57753 = false, _mut57754 = false, _mut57755 = false, _mut57756 = false, _mut57757 = false, _mut57758 = false, _mut57759 = false, _mut57760 = false, _mut57761 = false, _mut57762 = false, _mut57763 = false, _mut57764 = false, _mut57765 = false, _mut57766 = false, _mut57767 = false, _mut57768 = false, _mut57769 = false, _mut57770 = false, _mut57771 = false, _mut57772 = false, _mut57773 = false, _mut57774 = false, _mut57775 = false, _mut57776 = false, _mut57777 = false, _mut57778 = false, _mut57779 = false, _mut57780 = false, _mut57781 = false, _mut57782 = false, _mut57783 = false, _mut57784 = false, _mut57785 = false, _mut57786 = false, _mut57787 = false, _mut57788 = false, _mut57789 = false, _mut57790 = false, _mut57791 = false, _mut57792 = false, _mut57793 = false, _mut57794 = false, _mut57795 = false, _mut57796 = false, _mut57797 = false, _mut57798 = false, _mut57799 = false, _mut57800 = false, _mut57801 = false, _mut57802 = false, _mut57803 = false, _mut57804 = false, _mut57805 = false, _mut57806 = false, _mut57807 = false, _mut57808 = false, _mut57809 = false, _mut57810 = false, _mut57811 = false, _mut57812 = false, _mut57813 = false, _mut57814 = false, _mut57815 = false, _mut57816 = false, _mut57817 = false, _mut57818 = false, _mut57819 = false, _mut57820 = false, _mut57821 = false, _mut57822 = false, _mut57823 = false, _mut57824 = false, _mut57825 = false, _mut57826 = false, _mut57827 = false, _mut57828 = false, _mut57829 = false, _mut57830 = false, _mut57831 = false, _mut57832 = false, _mut57833 = false, _mut57834 = false, _mut57835 = false, _mut57836 = false, _mut57837 = false, _mut57838 = false, _mut57839 = false, _mut57840 = false, _mut57841 = false, _mut57842 = false, _mut57843 = false, _mut57844 = false, _mut57845 = false, _mut57846 = false, _mut57847 = false, _mut57848 = false, _mut57849 = false, _mut57850 = false, _mut57851 = false, _mut57852 = false, _mut57853 = false, _mut57854 = false, _mut57855 = false, _mut57856 = false, _mut57857 = false, _mut57858 = false, _mut57859 = false, _mut57860 = false, _mut57861 = false, _mut57862 = false, _mut57863 = false, _mut57864 = false, _mut57865 = false, _mut57866 = false, _mut57867 = false, _mut57868 = false, _mut57869 = false, _mut57870 = false, _mut57871 = false, _mut57872 = false, _mut57873 = false, _mut57874 = false, _mut57875 = false, _mut57876 = false, _mut57877 = false, _mut57878 = false, _mut57879 = false, _mut57880 = false, _mut57881 = false, _mut57882 = false, _mut57883 = false, _mut57884 = false, _mut57885 = false, _mut57886 = false, _mut57887 = false, _mut57888 = false, _mut57889 = false, _mut57890 = false, _mut57891 = false, _mut57892 = false, _mut57893 = false, _mut57894 = false, _mut57895 = false, _mut57896 = false, _mut57897 = false, _mut57898 = false, _mut57899 = false, _mut57900 = false, _mut57901 = false, _mut57902 = false, _mut57903 = false, _mut57904 = false, _mut57905 = false, _mut57906 = false, _mut57907 = false, _mut57908 = false, _mut57909 = false, _mut57910 = false, _mut57911 = false, _mut57912 = false, _mut57913 = false, _mut57914 = false, _mut57915 = false, _mut57916 = false, _mut57917 = false, _mut57918 = false, _mut57919 = false, _mut57920 = false, _mut57921 = false, _mut57922 = false, _mut57923 = false, _mut57924 = false, _mut57925 = false, _mut57926 = false, _mut57927 = false, _mut57928 = false, _mut57929 = false, _mut57930 = false, _mut57931 = false, _mut57932 = false, _mut57933 = false, _mut57934 = false, _mut57935 = false, _mut57936 = false, _mut57937 = false, _mut57938 = false, _mut57939 = false, _mut57940 = false, _mut57941 = false, _mut57942 = false, _mut57943 = false, _mut57944 = false, _mut57945 = false, _mut57946 = false, _mut57947 = false, _mut57948 = false, _mut57949 = false, _mut57950 = false, _mut57951 = false, _mut57952 = false, _mut57953 = false, _mut57954 = false, _mut57955 = false, _mut57956 = false, _mut57957 = false, _mut57958 = false, _mut57959 = false, _mut57960 = false, _mut57961 = false, _mut57962 = false, _mut57963 = false, _mut57964 = false, _mut57965 = false, _mut57966 = false, _mut57967 = false, _mut57968 = false, _mut57969 = false, _mut57970 = false, _mut57971 = false, _mut57972 = false, _mut57973 = false, _mut57974 = false, _mut57975 = false, _mut57976 = false, _mut57977 = false, _mut57978 = false, _mut57979 = false, _mut57980 = false, _mut57981 = false, _mut57982 = false, _mut57983 = false, _mut57984 = false, _mut57985 = false, _mut57986 = false, _mut57987 = false, _mut57988 = false, _mut57989 = false, _mut57990 = false, _mut57991 = false, _mut57992 = false, _mut57993 = false, _mut57994 = false, _mut57995 = false, _mut57996 = false, _mut57997 = false, _mut57998 = false, _mut57999 = false, _mut58000 = false, _mut58001 = false, _mut58002 = false, _mut58003 = false, _mut58004 = false, _mut58005 = false, _mut58006 = false, _mut58007 = false, _mut58008 = false, _mut58009 = false, _mut58010 = false, _mut58011 = false, _mut58012 = false, _mut58013 = false, _mut58014 = false, _mut58015 = false, _mut58016 = false, _mut58017 = false, _mut58018 = false, _mut58019 = false, _mut58020 = false, _mut58021 = false, _mut58022 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20120112L;

    /**
     * Lower limit of this distribution (inclusive).
     */
    private final double a;

    /**
     * Upper limit of this distribution (inclusive).
     */
    private final double b;

    /**
     * Mode of this distribution.
     */
    private final double c;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Creates a triangular real distribution using the given lower limit,
     * upper limit, and mode.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param a Lower limit of this distribution (inclusive).
     * @param b Upper limit of this distribution (inclusive).
     * @param c Mode of this distribution.
     * @throws NumberIsTooLargeException if {@code a >= b} or if {@code c > b}.
     * @throws NumberIsTooSmallException if {@code c < a}.
     */
    public TriangularDistribution(double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
        this(new Well19937c(), a, c, b);
    }

    /**
     * Creates a triangular distribution.
     *
     * @param rng Random number generator.
     * @param a Lower limit of this distribution (inclusive).
     * @param b Upper limit of this distribution (inclusive).
     * @param c Mode of this distribution.
     * @throws NumberIsTooLargeException if {@code a >= b} or if {@code c > b}.
     * @throws NumberIsTooSmallException if {@code c < a}.
     * @since 3.1
     */
    public TriangularDistribution(RandomGenerator rng, double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.TriangularDistribution_81");
        if (ROR_greater_equals(a, b, "org.apache.commons.math3.distribution.TriangularDistribution.TriangularDistribution_81", _mut57674, _mut57675, _mut57676, _mut57677, _mut57678)) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, a, b, false);
        }
        if (ROR_less(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.TriangularDistribution_81", _mut57679, _mut57680, _mut57681, _mut57682, _mut57683)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, c, a, true);
        }
        if (ROR_greater(c, b, "org.apache.commons.math3.distribution.TriangularDistribution.TriangularDistribution_81", _mut57684, _mut57685, _mut57686, _mut57687, _mut57688)) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, c, b, true);
        }
        this.a = a;
        this.c = c;
        this.b = b;
        solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(a), FastMath.ulp(b));
    }

    /**
     * Returns the mode {@code c} of this distribution.
     *
     * @return the mode {@code c} of this distribution
     */
    public double getMode() {
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * For this distribution, the returned value is not really meaningful,
     * since exact formulas are implemented for the computation of the
     * {@link #inverseCumulativeProbability(double)} (no solver is invoked).
     * </p>
     * <p>
     * For lower limit {@code a} and upper limit {@code b}, the current
     * implementation returns {@code max(ulp(a), ulp(b)}.
     * </p>
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * For lower limit {@code a}, upper limit {@code b} and mode {@code c}, the
     * PDF is given by
     * <ul>
     * <li>{@code 2 * (x - a) / [(b - a) * (c - a)]} if {@code a <= x < c},</li>
     * <li>{@code 2 / (b - a)} if {@code x = c},</li>
     * <li>{@code 2 * (b - x) / [(b - a) * (b - c)]} if {@code c < x <= b},</li>
     * <li>{@code 0} otherwise.
     * </ul>
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.density_147");
        if (ROR_less(x, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57689, _mut57690, _mut57691, _mut57692, _mut57693)) {
            return 0;
        }
        if ((_mut57704 ? (ROR_less_equals(a, x, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57694, _mut57695, _mut57696, _mut57697, _mut57698) || ROR_less(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57699, _mut57700, _mut57701, _mut57702, _mut57703)) : (ROR_less_equals(a, x, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57694, _mut57695, _mut57696, _mut57697, _mut57698) && ROR_less(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57699, _mut57700, _mut57701, _mut57702, _mut57703)))) {
            double divident = AOR_multiply(2, (AOR_minus(x, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57705, _mut57706, _mut57707, _mut57708)), "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57709, _mut57710, _mut57711, _mut57712);
            double divisor = AOR_multiply((AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57713, _mut57714, _mut57715, _mut57716)), (AOR_minus(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57717, _mut57718, _mut57719, _mut57720)), "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57721, _mut57722, _mut57723, _mut57724);
            return AOR_divide(divident, divisor, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57725, _mut57726, _mut57727, _mut57728);
        }
        if (ROR_equals(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57729, _mut57730, _mut57731, _mut57732, _mut57733)) {
            return AOR_divide(2, (AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57734, _mut57735, _mut57736, _mut57737)), "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57738, _mut57739, _mut57740, _mut57741);
        }
        if ((_mut57752 ? (ROR_less(c, x, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57742, _mut57743, _mut57744, _mut57745, _mut57746) || ROR_less_equals(x, b, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57747, _mut57748, _mut57749, _mut57750, _mut57751)) : (ROR_less(c, x, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57742, _mut57743, _mut57744, _mut57745, _mut57746) && ROR_less_equals(x, b, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57747, _mut57748, _mut57749, _mut57750, _mut57751)))) {
            double divident = AOR_multiply(2, (AOR_minus(b, x, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57753, _mut57754, _mut57755, _mut57756)), "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57757, _mut57758, _mut57759, _mut57760);
            double divisor = AOR_multiply((AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57761, _mut57762, _mut57763, _mut57764)), (AOR_minus(b, c, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57765, _mut57766, _mut57767, _mut57768)), "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57769, _mut57770, _mut57771, _mut57772);
            return AOR_divide(divident, divisor, "org.apache.commons.math3.distribution.TriangularDistribution.density_147", _mut57773, _mut57774, _mut57775, _mut57776);
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * For lower limit {@code a}, upper limit {@code b} and mode {@code c}, the
     * CDF is given by
     * <ul>
     * <li>{@code 0} if {@code x < a},</li>
     * <li>{@code (x - a)^2 / [(b - a) * (c - a)]} if {@code a <= x < c},</li>
     * <li>{@code (c - a) / (b - a)} if {@code x = c},</li>
     * <li>{@code 1 - (b - x)^2 / [(b - a) * (b - c)]} if {@code c < x <= b},</li>
     * <li>{@code 1} if {@code x > b}.</li>
     * </ul>
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180");
        if (ROR_less(x, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57777, _mut57778, _mut57779, _mut57780, _mut57781)) {
            return 0;
        }
        if ((_mut57792 ? (ROR_less_equals(a, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57782, _mut57783, _mut57784, _mut57785, _mut57786) || ROR_less(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57787, _mut57788, _mut57789, _mut57790, _mut57791)) : (ROR_less_equals(a, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57782, _mut57783, _mut57784, _mut57785, _mut57786) && ROR_less(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57787, _mut57788, _mut57789, _mut57790, _mut57791)))) {
            double divident = AOR_multiply((AOR_minus(x, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57793, _mut57794, _mut57795, _mut57796)), (AOR_minus(x, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57797, _mut57798, _mut57799, _mut57800)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57801, _mut57802, _mut57803, _mut57804);
            double divisor = AOR_multiply((AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57805, _mut57806, _mut57807, _mut57808)), (AOR_minus(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57809, _mut57810, _mut57811, _mut57812)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57813, _mut57814, _mut57815, _mut57816);
            return AOR_divide(divident, divisor, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57817, _mut57818, _mut57819, _mut57820);
        }
        if (ROR_equals(x, c, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57821, _mut57822, _mut57823, _mut57824, _mut57825)) {
            return AOR_divide((AOR_minus(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57826, _mut57827, _mut57828, _mut57829)), (AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57830, _mut57831, _mut57832, _mut57833)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57834, _mut57835, _mut57836, _mut57837);
        }
        if ((_mut57848 ? (ROR_less(c, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57838, _mut57839, _mut57840, _mut57841, _mut57842) || ROR_less_equals(x, b, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57843, _mut57844, _mut57845, _mut57846, _mut57847)) : (ROR_less(c, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57838, _mut57839, _mut57840, _mut57841, _mut57842) && ROR_less_equals(x, b, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57843, _mut57844, _mut57845, _mut57846, _mut57847)))) {
            double divident = AOR_multiply((AOR_minus(b, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57849, _mut57850, _mut57851, _mut57852)), (AOR_minus(b, x, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57853, _mut57854, _mut57855, _mut57856)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57857, _mut57858, _mut57859, _mut57860);
            double divisor = AOR_multiply((AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57861, _mut57862, _mut57863, _mut57864)), (AOR_minus(b, c, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57865, _mut57866, _mut57867, _mut57868)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57869, _mut57870, _mut57871, _mut57872);
            return AOR_minus(1, (AOR_divide(divident, divisor, "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57873, _mut57874, _mut57875, _mut57876)), "org.apache.commons.math3.distribution.TriangularDistribution.cumulativeProbability_180", _mut57877, _mut57878, _mut57879, _mut57880);
        }
        return 1;
    }

    /**
     * {@inheritDoc}
     *
     * For lower limit {@code a}, upper limit {@code b}, and mode {@code c},
     * the mean is {@code (a + b + c) / 3}.
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.getNumericalMean_206");
        return AOR_divide((AOR_plus(AOR_plus(a, b, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalMean_206", _mut57881, _mut57882, _mut57883, _mut57884), c, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalMean_206", _mut57885, _mut57886, _mut57887, _mut57888)), 3, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalMean_206", _mut57889, _mut57890, _mut57891, _mut57892);
    }

    /**
     * {@inheritDoc}
     *
     * For lower limit {@code a}, upper limit {@code b}, and mode {@code c},
     * the variance is {@code (a^2 + b^2 + c^2 - a * b - a * c - b * c) / 18}.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216");
        return AOR_divide((AOR_minus(AOR_minus(AOR_minus(AOR_plus(AOR_plus(AOR_multiply(a, a, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57893, _mut57894, _mut57895, _mut57896), AOR_multiply(b, b, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57897, _mut57898, _mut57899, _mut57900), "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57901, _mut57902, _mut57903, _mut57904), AOR_multiply(c, c, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57905, _mut57906, _mut57907, _mut57908), "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57909, _mut57910, _mut57911, _mut57912), AOR_multiply(a, b, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57913, _mut57914, _mut57915, _mut57916), "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57917, _mut57918, _mut57919, _mut57920), AOR_multiply(a, c, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57921, _mut57922, _mut57923, _mut57924), "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57925, _mut57926, _mut57927, _mut57928), AOR_multiply(b, c, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57929, _mut57930, _mut57931, _mut57932), "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57933, _mut57934, _mut57935, _mut57936)), 18, "org.apache.commons.math3.distribution.TriangularDistribution.getNumericalVariance_216", _mut57937, _mut57938, _mut57939, _mut57940);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is equal to the lower limit parameter
     * {@code a} of the distribution.
     *
     * @return lower bound of the support
     */
    public double getSupportLowerBound() {
        return a;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is equal to the upper limit parameter
     * {@code b} of the distribution.
     *
     * @return upper bound of the support
     */
    public double getSupportUpperBound() {
        return b;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266");
        if ((_mut57951 ? (ROR_less(p, 0, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57941, _mut57942, _mut57943, _mut57944, _mut57945) && ROR_greater(p, 1, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57946, _mut57947, _mut57948, _mut57949, _mut57950)) : (ROR_less(p, 0, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57941, _mut57942, _mut57943, _mut57944, _mut57945) || ROR_greater(p, 1, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57946, _mut57947, _mut57948, _mut57949, _mut57950)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57952, _mut57953, _mut57954, _mut57955, _mut57956)) {
            return a;
        }
        if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57957, _mut57958, _mut57959, _mut57960, _mut57961)) {
            return b;
        }
        if (ROR_less(p, AOR_divide((AOR_minus(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57962, _mut57963, _mut57964, _mut57965)), (AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57966, _mut57967, _mut57968, _mut57969)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57970, _mut57971, _mut57972, _mut57973), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57974, _mut57975, _mut57976, _mut57977, _mut57978)) {
            return AOR_plus(a, FastMath.sqrt(AOR_multiply(AOR_multiply(p, (AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57979, _mut57980, _mut57981, _mut57982)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57983, _mut57984, _mut57985, _mut57986), (AOR_minus(c, a, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57987, _mut57988, _mut57989, _mut57990)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57991, _mut57992, _mut57993, _mut57994)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57995, _mut57996, _mut57997, _mut57998);
        }
        return AOR_minus(b, FastMath.sqrt(AOR_multiply(AOR_multiply((AOR_minus(1, p, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut57999, _mut58000, _mut58001, _mut58002)), (AOR_minus(b, a, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut58003, _mut58004, _mut58005, _mut58006)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut58007, _mut58008, _mut58009, _mut58010), (AOR_minus(b, c, "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut58011, _mut58012, _mut58013, _mut58014)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut58015, _mut58016, _mut58017, _mut58018)), "org.apache.commons.math3.distribution.TriangularDistribution.inverseCumulativeProbability_266", _mut58019, _mut58020, _mut58021, _mut58022);
    }
}
