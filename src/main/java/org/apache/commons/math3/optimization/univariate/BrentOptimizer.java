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
package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * For a function defined on some interval {@code (lo, hi)}, this class
 * finds an approximation {@code x} to the point at which the function
 * attains its minimum.
 * It implements Richard Brent's algorithm (from his book "Algorithms for
 * Minimization without Derivatives", p. 79) for finding minima of real
 * univariate functions.
 * <br/>
 * This code is an adaptation, partly based on the Python code from SciPy
 * (module "optimize.py" v0.5); the original algorithm is also modified
 * <ul>
 *  <li>to use an initial guess provided by the user,</li>
 *  <li>to ensure that the best point encountered is the one returned.</li>
 * </ul>
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.0
 */
@Deprecated
public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {

    @Conditional
    public static boolean _mut70732 = false, _mut70733 = false, _mut70734 = false, _mut70735 = false, _mut70736 = false, _mut70737 = false, _mut70738 = false, _mut70739 = false, _mut70740 = false, _mut70741 = false, _mut70742 = false, _mut70743 = false, _mut70744 = false, _mut70745 = false, _mut70746 = false, _mut70747 = false, _mut70748 = false, _mut70749 = false, _mut70750 = false, _mut70751 = false, _mut70752 = false, _mut70753 = false, _mut70754 = false, _mut70755 = false, _mut70756 = false, _mut70757 = false, _mut70758 = false, _mut70759 = false, _mut70760 = false, _mut70761 = false, _mut70762 = false, _mut70763 = false, _mut70764 = false, _mut70765 = false, _mut70766 = false, _mut70767 = false, _mut70768 = false, _mut70769 = false, _mut70770 = false, _mut70771 = false, _mut70772 = false, _mut70773 = false, _mut70774 = false, _mut70775 = false, _mut70776 = false, _mut70777 = false, _mut70778 = false, _mut70779 = false, _mut70780 = false, _mut70781 = false, _mut70782 = false, _mut70783 = false, _mut70784 = false, _mut70785 = false, _mut70786 = false, _mut70787 = false, _mut70788 = false, _mut70789 = false, _mut70790 = false, _mut70791 = false, _mut70792 = false, _mut70793 = false, _mut70794 = false, _mut70795 = false, _mut70796 = false, _mut70797 = false, _mut70798 = false, _mut70799 = false, _mut70800 = false, _mut70801 = false, _mut70802 = false, _mut70803 = false, _mut70804 = false, _mut70805 = false, _mut70806 = false, _mut70807 = false, _mut70808 = false, _mut70809 = false, _mut70810 = false, _mut70811 = false, _mut70812 = false, _mut70813 = false, _mut70814 = false, _mut70815 = false, _mut70816 = false, _mut70817 = false, _mut70818 = false, _mut70819 = false, _mut70820 = false, _mut70821 = false, _mut70822 = false, _mut70823 = false, _mut70824 = false, _mut70825 = false, _mut70826 = false, _mut70827 = false, _mut70828 = false, _mut70829 = false, _mut70830 = false, _mut70831 = false, _mut70832 = false, _mut70833 = false, _mut70834 = false, _mut70835 = false, _mut70836 = false, _mut70837 = false, _mut70838 = false, _mut70839 = false, _mut70840 = false, _mut70841 = false, _mut70842 = false, _mut70843 = false, _mut70844 = false, _mut70845 = false, _mut70846 = false, _mut70847 = false, _mut70848 = false, _mut70849 = false, _mut70850 = false, _mut70851 = false, _mut70852 = false, _mut70853 = false, _mut70854 = false, _mut70855 = false, _mut70856 = false, _mut70857 = false, _mut70858 = false, _mut70859 = false, _mut70860 = false, _mut70861 = false, _mut70862 = false, _mut70863 = false, _mut70864 = false, _mut70865 = false, _mut70866 = false, _mut70867 = false, _mut70868 = false, _mut70869 = false, _mut70870 = false, _mut70871 = false, _mut70872 = false, _mut70873 = false, _mut70874 = false, _mut70875 = false, _mut70876 = false, _mut70877 = false, _mut70878 = false, _mut70879 = false, _mut70880 = false, _mut70881 = false, _mut70882 = false, _mut70883 = false, _mut70884 = false, _mut70885 = false, _mut70886 = false, _mut70887 = false, _mut70888 = false, _mut70889 = false, _mut70890 = false, _mut70891 = false, _mut70892 = false, _mut70893 = false, _mut70894 = false, _mut70895 = false, _mut70896 = false, _mut70897 = false, _mut70898 = false, _mut70899 = false, _mut70900 = false, _mut70901 = false, _mut70902 = false, _mut70903 = false, _mut70904 = false, _mut70905 = false, _mut70906 = false, _mut70907 = false, _mut70908 = false, _mut70909 = false, _mut70910 = false, _mut70911 = false, _mut70912 = false, _mut70913 = false, _mut70914 = false, _mut70915 = false, _mut70916 = false, _mut70917 = false, _mut70918 = false, _mut70919 = false, _mut70920 = false, _mut70921 = false, _mut70922 = false, _mut70923 = false, _mut70924 = false, _mut70925 = false, _mut70926 = false, _mut70927 = false, _mut70928 = false, _mut70929 = false, _mut70930 = false, _mut70931 = false, _mut70932 = false, _mut70933 = false, _mut70934 = false, _mut70935 = false, _mut70936 = false, _mut70937 = false, _mut70938 = false, _mut70939 = false, _mut70940 = false, _mut70941 = false, _mut70942 = false, _mut70943 = false, _mut70944 = false, _mut70945 = false, _mut70946 = false, _mut70947 = false, _mut70948 = false, _mut70949 = false, _mut70950 = false, _mut70951 = false, _mut70952 = false, _mut70953 = false, _mut70954 = false, _mut70955 = false, _mut70956 = false, _mut70957 = false, _mut70958 = false, _mut70959 = false, _mut70960 = false, _mut70961 = false, _mut70962 = false, _mut70963 = false, _mut70964 = false, _mut70965 = false, _mut70966 = false, _mut70967 = false, _mut70968 = false, _mut70969 = false, _mut70970 = false, _mut70971 = false, _mut70972 = false, _mut70973 = false, _mut70974 = false, _mut70975 = false, _mut70976 = false, _mut70977 = false, _mut70978 = false, _mut70979 = false, _mut70980 = false, _mut70981 = false, _mut70982 = false, _mut70983 = false, _mut70984 = false, _mut70985 = false, _mut70986 = false, _mut70987 = false, _mut70988 = false, _mut70989 = false, _mut70990 = false, _mut70991 = false, _mut70992 = false, _mut70993 = false, _mut70994 = false, _mut70995 = false, _mut70996 = false, _mut70997 = false, _mut70998 = false, _mut70999 = false, _mut71000 = false, _mut71001 = false, _mut71002 = false, _mut71003 = false, _mut71004 = false, _mut71005 = false, _mut71006 = false, _mut71007 = false, _mut71008 = false, _mut71009 = false, _mut71010 = false, _mut71011 = false, _mut71012 = false, _mut71013 = false, _mut71014 = false, _mut71015 = false, _mut71016 = false, _mut71017 = false, _mut71018 = false, _mut71019 = false, _mut71020 = false, _mut71021 = false, _mut71022 = false, _mut71023 = false, _mut71024 = false, _mut71025 = false, _mut71026 = false, _mut71027 = false, _mut71028 = false, _mut71029 = false;

    /**
     * Golden section.
     */
    private static final double GOLDEN_SECTION = AOR_multiply(0.5, (AOR_minus(3, FastMath.sqrt(5), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_161", _mut70732, _mut70733, _mut70734, _mut70735)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_161", _mut70736, _mut70737, _mut70738, _mut70739);

    /**
     * Minimum relative tolerance.
     */
    private static final double MIN_RELATIVE_TOLERANCE = AOR_multiply(2, FastMath.ulp(1d), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_161", _mut70740, _mut70741, _mut70742, _mut70743);

    /**
     * Relative threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute threshold.
     */
    private final double absoluteThreshold;

    /**
     * The arguments are used implement the original stopping criterion
     * of Brent's algorithm.
     * {@code abs} and {@code rel} define a tolerance
     * {@code tol = rel |x| + abs}. {@code rel} should be no smaller than
     * <em>2 macheps</em> and preferably not much less than <em>sqrt(macheps)</em>,
     * where <em>macheps</em> is the relative machine precision. {@code abs} must
     * be positive.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param checker Additional, user-defined, convergence checking
     * procedure.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public BrentOptimizer(double rel, double abs, ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BrentOptimizer.BrentOptimizer_79");
        if (ROR_less(rel, MIN_RELATIVE_TOLERANCE, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.BrentOptimizer_79", _mut70744, _mut70745, _mut70746, _mut70747, _mut70748)) {
            throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
        }
        if (ROR_less_equals(abs, 0, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.BrentOptimizer_79", _mut70749, _mut70750, _mut70751, _mut70752, _mut70753)) {
            throw new NotStrictlyPositiveException(abs);
        }
        relativeThreshold = rel;
        absoluteThreshold = abs;
    }

    /**
     * The arguments are used for implementing the original stopping criterion
     * of Brent's algorithm.
     * {@code abs} and {@code rel} define a tolerance
     * {@code tol = rel |x| + abs}. {@code rel} should be no smaller than
     * <em>2 macheps</em> and preferably not much less than <em>sqrt(macheps)</em>,
     * where <em>macheps</em> is the relative machine precision. {@code abs} must
     * be positive.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public BrentOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected UnivariatePointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115");
        final boolean isMinim = getGoalType() == GoalType.MINIMIZE;
        final double lo = getMin();
        final double mid = getStartValue();
        final double hi = getMax();
        // Optional additional convergence criteria.
        final ConvergenceChecker<UnivariatePointValuePair> checker = getConvergenceChecker();
        double a;
        double b;
        if (ROR_less(lo, hi, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70754, _mut70755, _mut70756, _mut70757, _mut70758)) {
            a = lo;
            b = hi;
        } else {
            a = hi;
            b = lo;
        }
        double x = mid;
        double v = x;
        double w = x;
        double d = 0;
        double e = 0;
        double fx = computeObjectiveValue(x);
        if (!isMinim) {
            fx = -fx;
        }
        double fv = fx;
        double fw = fx;
        UnivariatePointValuePair previous = null;
        UnivariatePointValuePair current = new UnivariatePointValuePair(x, isMinim ? fx : -fx);
        // Best point encountered so far (which is the initial guess).
        UnivariatePointValuePair best = current;
        int iter = 0;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115");
            final double m = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70759, _mut70760, _mut70761, _mut70762)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70763, _mut70764, _mut70765, _mut70766);
            final double tol1 = AOR_plus(AOR_multiply(relativeThreshold, FastMath.abs(x), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70767, _mut70768, _mut70769, _mut70770), absoluteThreshold, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70771, _mut70772, _mut70773, _mut70774);
            final double tol2 = AOR_multiply(2, tol1, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70775, _mut70776, _mut70777, _mut70778);
            // Default stopping criterion.
            final boolean stop = ROR_less_equals(FastMath.abs(AOR_minus(x, m, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70779, _mut70780, _mut70781, _mut70782)), AOR_minus(tol2, AOR_multiply(0.5, (AOR_minus(b, a, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70783, _mut70784, _mut70785, _mut70786)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70787, _mut70788, _mut70789, _mut70790), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70791, _mut70792, _mut70793, _mut70794), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70795, _mut70796, _mut70797, _mut70798, _mut70799);
            if (!stop) {
                double p = 0;
                double q = 0;
                double r = 0;
                double u = 0;
                if (ROR_greater(FastMath.abs(e), tol1, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70800, _mut70801, _mut70802, _mut70803, _mut70804)) {
                    // Fit parabola.
                    r = AOR_multiply((AOR_minus(x, w, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70822, _mut70823, _mut70824, _mut70825)), (AOR_minus(fx, fv, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70826, _mut70827, _mut70828, _mut70829)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70830, _mut70831, _mut70832, _mut70833);
                    q = AOR_multiply((AOR_minus(x, v, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70834, _mut70835, _mut70836, _mut70837)), (AOR_minus(fx, fw, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70838, _mut70839, _mut70840, _mut70841)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70842, _mut70843, _mut70844, _mut70845);
                    p = AOR_minus(AOR_multiply((AOR_minus(x, v, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70846, _mut70847, _mut70848, _mut70849)), q, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70850, _mut70851, _mut70852, _mut70853), AOR_multiply((AOR_minus(x, w, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70854, _mut70855, _mut70856, _mut70857)), r, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70858, _mut70859, _mut70860, _mut70861), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70862, _mut70863, _mut70864, _mut70865);
                    q = AOR_multiply(2, (AOR_minus(q, r, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70866, _mut70867, _mut70868, _mut70869)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70870, _mut70871, _mut70872, _mut70873);
                    if (ROR_greater(q, 0, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70874, _mut70875, _mut70876, _mut70877, _mut70878)) {
                        p = -p;
                    } else {
                        q = -q;
                    }
                    r = e;
                    e = d;
                    if ((_mut70919 ? ((_mut70905 ? (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70879, _mut70880, _mut70881, _mut70882)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70883, _mut70884, _mut70885, _mut70886), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70887, _mut70888, _mut70889, _mut70890, _mut70891) || ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70892, _mut70893, _mut70894, _mut70895)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70896, _mut70897, _mut70898, _mut70899), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70900, _mut70901, _mut70902, _mut70903, _mut70904)) : (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70879, _mut70880, _mut70881, _mut70882)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70883, _mut70884, _mut70885, _mut70886), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70887, _mut70888, _mut70889, _mut70890, _mut70891) && ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70892, _mut70893, _mut70894, _mut70895)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70896, _mut70897, _mut70898, _mut70899), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70900, _mut70901, _mut70902, _mut70903, _mut70904))) || ROR_less(FastMath.abs(p), FastMath.abs(AOR_multiply(AOR_multiply(0.5, q, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70906, _mut70907, _mut70908, _mut70909), r, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70910, _mut70911, _mut70912, _mut70913)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70914, _mut70915, _mut70916, _mut70917, _mut70918)) : ((_mut70905 ? (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70879, _mut70880, _mut70881, _mut70882)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70883, _mut70884, _mut70885, _mut70886), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70887, _mut70888, _mut70889, _mut70890, _mut70891) || ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70892, _mut70893, _mut70894, _mut70895)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70896, _mut70897, _mut70898, _mut70899), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70900, _mut70901, _mut70902, _mut70903, _mut70904)) : (ROR_greater(p, AOR_multiply(q, (AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70879, _mut70880, _mut70881, _mut70882)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70883, _mut70884, _mut70885, _mut70886), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70887, _mut70888, _mut70889, _mut70890, _mut70891) && ROR_less(p, AOR_multiply(q, (AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70892, _mut70893, _mut70894, _mut70895)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70896, _mut70897, _mut70898, _mut70899), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70900, _mut70901, _mut70902, _mut70903, _mut70904))) && ROR_less(FastMath.abs(p), FastMath.abs(AOR_multiply(AOR_multiply(0.5, q, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70906, _mut70907, _mut70908, _mut70909), r, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70910, _mut70911, _mut70912, _mut70913)), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70914, _mut70915, _mut70916, _mut70917, _mut70918)))) {
                        // Parabolic interpolation step.
                        d = AOR_divide(p, q, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70937, _mut70938, _mut70939, _mut70940);
                        u = AOR_plus(x, d, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70941, _mut70942, _mut70943, _mut70944);
                        // f must not be evaluated too close to a or b.
                        if ((_mut70963 ? (ROR_less(AOR_minus(u, a, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70945, _mut70946, _mut70947, _mut70948), tol2, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70949, _mut70950, _mut70951, _mut70952, _mut70953) && ROR_less(AOR_minus(b, u, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70954, _mut70955, _mut70956, _mut70957), tol2, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70958, _mut70959, _mut70960, _mut70961, _mut70962)) : (ROR_less(AOR_minus(u, a, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70945, _mut70946, _mut70947, _mut70948), tol2, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70949, _mut70950, _mut70951, _mut70952, _mut70953) || ROR_less(AOR_minus(b, u, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70954, _mut70955, _mut70956, _mut70957), tol2, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70958, _mut70959, _mut70960, _mut70961, _mut70962)))) {
                            if (ROR_less_equals(x, m, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70964, _mut70965, _mut70966, _mut70967, _mut70968)) {
                                d = tol1;
                            } else {
                                d = -tol1;
                            }
                        }
                    } else {
                        // Golden section step.
                        if (ROR_less(x, m, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70920, _mut70921, _mut70922, _mut70923, _mut70924)) {
                            e = AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70929, _mut70930, _mut70931, _mut70932);
                        } else {
                            e = AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70925, _mut70926, _mut70927, _mut70928);
                        }
                        d = AOR_multiply(GOLDEN_SECTION, e, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70933, _mut70934, _mut70935, _mut70936);
                    }
                } else {
                    // Golden section step.
                    if (ROR_less(x, m, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70805, _mut70806, _mut70807, _mut70808, _mut70809)) {
                        e = AOR_minus(b, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70814, _mut70815, _mut70816, _mut70817);
                    } else {
                        e = AOR_minus(a, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70810, _mut70811, _mut70812, _mut70813);
                    }
                    d = AOR_multiply(GOLDEN_SECTION, e, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70818, _mut70819, _mut70820, _mut70821);
                }
                // Update by at least "tol1".
                if (ROR_less(FastMath.abs(d), tol1, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70969, _mut70970, _mut70971, _mut70972, _mut70973)) {
                    if (ROR_greater_equals(d, 0, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70978, _mut70979, _mut70980, _mut70981, _mut70982)) {
                        u = AOR_plus(x, tol1, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70987, _mut70988, _mut70989, _mut70990);
                    } else {
                        u = AOR_minus(x, tol1, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70983, _mut70984, _mut70985, _mut70986);
                    }
                } else {
                    u = AOR_plus(x, d, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70974, _mut70975, _mut70976, _mut70977);
                }
                double fu = computeObjectiveValue(u);
                if (!isMinim) {
                    fu = -fu;
                }
                // User-defined convergence checker.
                previous = current;
                current = new UnivariatePointValuePair(u, isMinim ? fu : -fu);
                best = best(best, best(previous, current, isMinim), isMinim);
                if ((_mut70991 ? (checker != null || checker.converged(iter, previous, current)) : (checker != null && checker.converged(iter, previous, current)))) {
                    return best;
                }
                // Update a, b, v, w and x.
                if (ROR_less_equals(fu, fx, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70992, _mut70993, _mut70994, _mut70995, _mut70996)) {
                    if (ROR_less(u, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71015, _mut71016, _mut71017, _mut71018, _mut71019)) {
                        b = x;
                    } else {
                        a = x;
                    }
                    v = w;
                    fv = fw;
                    w = x;
                    fw = fx;
                    x = u;
                    fx = fu;
                } else {
                    if (ROR_less(u, x, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut70997, _mut70998, _mut70999, _mut71000, _mut71001)) {
                        a = u;
                    } else {
                        b = u;
                    }
                    if ((_mut71007 ? (ROR_less_equals(fu, fw, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71002, _mut71003, _mut71004, _mut71005, _mut71006) && Precision.equals(w, x)) : (ROR_less_equals(fu, fw, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71002, _mut71003, _mut71004, _mut71005, _mut71006) || Precision.equals(w, x)))) {
                        v = w;
                        fv = fw;
                        w = u;
                        fw = fu;
                    } else if ((_mut71014 ? ((_mut71013 ? (ROR_less_equals(fu, fv, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71008, _mut71009, _mut71010, _mut71011, _mut71012) && Precision.equals(v, x)) : (ROR_less_equals(fu, fv, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71008, _mut71009, _mut71010, _mut71011, _mut71012) || Precision.equals(v, x))) && Precision.equals(v, w)) : ((_mut71013 ? (ROR_less_equals(fu, fv, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71008, _mut71009, _mut71010, _mut71011, _mut71012) && Precision.equals(v, x)) : (ROR_less_equals(fu, fv, "org.apache.commons.math3.optimization.univariate.BrentOptimizer.doOptimize_115", _mut71008, _mut71009, _mut71010, _mut71011, _mut71012) || Precision.equals(v, x))) || Precision.equals(v, w)))) {
                        v = u;
                        fv = fu;
                    }
                }
            } else {
                // Default termination (Brent's criterion).
                return best(best, best(previous, current, isMinim), isMinim);
            }
            ++iter;
        }
    }

    /**
     * Selects the best of two points.
     *
     * @param a Point and value.
     * @param b Point and value.
     * @param isMinim {@code true} if the selected point must be the one with
     * the lowest value.
     * @return the best point, or {@code null} if {@code a} and {@code b} are
     * both {@code null}. When {@code a} and {@code b} have the same function
     * value, {@code a} is returned.
     */
    private UnivariatePointValuePair best(UnivariatePointValuePair a, UnivariatePointValuePair b, boolean isMinim) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BrentOptimizer.best_300");
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (isMinim) {
            return ROR_less_equals(a.getValue(), b.getValue(), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.best_300", _mut71025, _mut71026, _mut71027, _mut71028, _mut71029) ? a : b;
        } else {
            return ROR_greater_equals(a.getValue(), b.getValue(), "org.apache.commons.math3.optimization.univariate.BrentOptimizer.best_300", _mut71020, _mut71021, _mut71022, _mut71023, _mut71024) ? a : b;
        }
    }
}
