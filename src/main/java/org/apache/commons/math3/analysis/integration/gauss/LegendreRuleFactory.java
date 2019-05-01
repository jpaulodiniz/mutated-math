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
package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Pair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Factory that creates Gauss-type quadrature rule using Legendre polynomials.
 * In this implementation, the lower and upper bounds of the natural interval
 * of integration are -1 and 1, respectively.
 * The Legendre polynomials are evaluated using the recurrence relation
 * presented in <a href="http://en.wikipedia.org/wiki/Abramowitz_and_Stegun">
 * Abramowitz and Stegun, 1964</a>.
 *
 * @since 3.1
 */
public class LegendreRuleFactory extends BaseRuleFactory<Double> {

    @Conditional
    public static boolean _mut101769 = false, _mut101770 = false, _mut101771 = false, _mut101772 = false, _mut101773 = false, _mut101774 = false, _mut101775 = false, _mut101776 = false, _mut101777 = false, _mut101778 = false, _mut101779 = false, _mut101780 = false, _mut101781 = false, _mut101782 = false, _mut101783 = false, _mut101784 = false, _mut101785 = false, _mut101786 = false, _mut101787 = false, _mut101788 = false, _mut101789 = false, _mut101790 = false, _mut101791 = false, _mut101792 = false, _mut101793 = false, _mut101794 = false, _mut101795 = false, _mut101796 = false, _mut101797 = false, _mut101798 = false, _mut101799 = false, _mut101800 = false, _mut101801 = false, _mut101802 = false, _mut101803 = false, _mut101804 = false, _mut101805 = false, _mut101806 = false, _mut101807 = false, _mut101808 = false, _mut101809 = false, _mut101810 = false, _mut101811 = false, _mut101812 = false, _mut101813 = false, _mut101814 = false, _mut101815 = false, _mut101816 = false, _mut101817 = false, _mut101818 = false, _mut101819 = false, _mut101820 = false, _mut101821 = false, _mut101822 = false, _mut101823 = false, _mut101824 = false, _mut101825 = false, _mut101826 = false, _mut101827 = false, _mut101828 = false, _mut101829 = false, _mut101830 = false, _mut101831 = false, _mut101832 = false, _mut101833 = false, _mut101834 = false, _mut101835 = false, _mut101836 = false, _mut101837 = false, _mut101838 = false, _mut101839 = false, _mut101840 = false, _mut101841 = false, _mut101842 = false, _mut101843 = false, _mut101844 = false, _mut101845 = false, _mut101846 = false, _mut101847 = false, _mut101848 = false, _mut101849 = false, _mut101850 = false, _mut101851 = false, _mut101852 = false, _mut101853 = false, _mut101854 = false, _mut101855 = false, _mut101856 = false, _mut101857 = false, _mut101858 = false, _mut101859 = false, _mut101860 = false, _mut101861 = false, _mut101862 = false, _mut101863 = false, _mut101864 = false, _mut101865 = false, _mut101866 = false, _mut101867 = false, _mut101868 = false, _mut101869 = false, _mut101870 = false, _mut101871 = false, _mut101872 = false, _mut101873 = false, _mut101874 = false, _mut101875 = false, _mut101876 = false, _mut101877 = false, _mut101878 = false, _mut101879 = false, _mut101880 = false, _mut101881 = false, _mut101882 = false, _mut101883 = false, _mut101884 = false, _mut101885 = false, _mut101886 = false, _mut101887 = false, _mut101888 = false, _mut101889 = false, _mut101890 = false, _mut101891 = false, _mut101892 = false, _mut101893 = false, _mut101894 = false, _mut101895 = false, _mut101896 = false, _mut101897 = false, _mut101898 = false, _mut101899 = false, _mut101900 = false, _mut101901 = false, _mut101902 = false, _mut101903 = false, _mut101904 = false, _mut101905 = false, _mut101906 = false, _mut101907 = false, _mut101908 = false, _mut101909 = false, _mut101910 = false, _mut101911 = false, _mut101912 = false, _mut101913 = false, _mut101914 = false, _mut101915 = false, _mut101916 = false, _mut101917 = false, _mut101918 = false, _mut101919 = false, _mut101920 = false, _mut101921 = false, _mut101922 = false, _mut101923 = false, _mut101924 = false, _mut101925 = false, _mut101926 = false, _mut101927 = false, _mut101928 = false, _mut101929 = false, _mut101930 = false, _mut101931 = false, _mut101932 = false, _mut101933 = false, _mut101934 = false, _mut101935 = false, _mut101936 = false, _mut101937 = false, _mut101938 = false, _mut101939 = false, _mut101940 = false, _mut101941 = false, _mut101942 = false, _mut101943 = false, _mut101944 = false, _mut101945 = false, _mut101946 = false, _mut101947 = false, _mut101948 = false, _mut101949 = false, _mut101950 = false, _mut101951 = false, _mut101952 = false, _mut101953 = false, _mut101954 = false, _mut101955 = false, _mut101956 = false, _mut101957 = false, _mut101958 = false, _mut101959 = false, _mut101960 = false, _mut101961 = false, _mut101962 = false, _mut101963 = false, _mut101964 = false, _mut101965 = false, _mut101966 = false, _mut101967 = false, _mut101968 = false, _mut101969 = false, _mut101970 = false, _mut101971 = false, _mut101972 = false, _mut101973 = false, _mut101974 = false, _mut101975 = false, _mut101976 = false, _mut101977 = false, _mut101978 = false, _mut101979 = false, _mut101980 = false, _mut101981 = false, _mut101982 = false, _mut101983 = false, _mut101984 = false, _mut101985 = false, _mut101986 = false, _mut101987 = false, _mut101988 = false, _mut101989 = false, _mut101990 = false, _mut101991 = false, _mut101992 = false, _mut101993 = false, _mut101994 = false, _mut101995 = false, _mut101996 = false, _mut101997 = false, _mut101998 = false, _mut101999 = false, _mut102000 = false, _mut102001 = false, _mut102002 = false, _mut102003 = false, _mut102004 = false, _mut102005 = false, _mut102006 = false;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pair<Double[], Double[]> computeRule(int numberOfPoints) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
        if (ROR_equals(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101769, _mut101770, _mut101771, _mut101772, _mut101773)) {
            // Break recursion.
            return new Pair<Double[], Double[]>(new Double[] { 0d }, new Double[] { 2d });
        }
        // to this method.
        final Double[] previousPoints = getRuleInternal(AOR_minus(numberOfPoints, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101774, _mut101775, _mut101776, _mut101777)).getFirst();
        // Compute next rule.
        final Double[] points = new Double[numberOfPoints];
        final Double[] weights = new Double[numberOfPoints];
        // Find i-th root of P[n+1] by bracketing.
        final int iMax = AOR_divide(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101778, _mut101779, _mut101780, _mut101781);
        for (int i = 0; ROR_less(i, iMax, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101964, _mut101965, _mut101966, _mut101967, _mut101968); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
            // Lower-bound of the interval.
            double a = (ROR_equals(i, 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101782, _mut101783, _mut101784, _mut101785, _mut101786)) ? -1 : previousPoints[AOR_minus(i, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101787, _mut101788, _mut101789, _mut101790)].doubleValue();
            // Upper-bound of the interval.
            double b = (ROR_equals(iMax, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101791, _mut101792, _mut101793, _mut101794, _mut101795)) ? 1 : previousPoints[i].doubleValue();
            // P[j-1](a)
            double pma = 1;
            // P[j](a)
            double pa = a;
            // P[j-1](b)
            double pmb = 1;
            // P[j](b)
            double pb = b;
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101848, _mut101849, _mut101850, _mut101851, _mut101852); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
                final int two_j_p_1 = AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101796, _mut101797, _mut101798, _mut101799), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101800, _mut101801, _mut101802, _mut101803);
                final int j_p_1 = AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101804, _mut101805, _mut101806, _mut101807);
                // P[j+1](a)
                final double ppa = AOR_divide((AOR_minus(AOR_multiply(AOR_multiply(two_j_p_1, a, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101808, _mut101809, _mut101810, _mut101811), pa, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101812, _mut101813, _mut101814, _mut101815), AOR_multiply(j, pma, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101816, _mut101817, _mut101818, _mut101819), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101820, _mut101821, _mut101822, _mut101823)), j_p_1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101824, _mut101825, _mut101826, _mut101827);
                // P[j+1](b)
                final double ppb = AOR_divide((AOR_minus(AOR_multiply(AOR_multiply(two_j_p_1, b, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101828, _mut101829, _mut101830, _mut101831), pb, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101832, _mut101833, _mut101834, _mut101835), AOR_multiply(j, pmb, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101836, _mut101837, _mut101838, _mut101839), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101840, _mut101841, _mut101842, _mut101843)), j_p_1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101844, _mut101845, _mut101846, _mut101847);
                pma = pa;
                pa = ppa;
                pmb = pb;
                pb = ppb;
            }
            // Middle of the interval.
            double c = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101853, _mut101854, _mut101855, _mut101856)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101857, _mut101858, _mut101859, _mut101860);
            // P[j-1](c)
            double pmc = 1;
            // P[j](c)
            double pc = c;
            boolean done = false;
            while (!done) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
                done = ROR_less_equals(AOR_minus(b, a, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101861, _mut101862, _mut101863, _mut101864), Math.ulp(c), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101865, _mut101866, _mut101867, _mut101868, _mut101869);
                pmc = 1;
                pc = c;
                for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101902, _mut101903, _mut101904, _mut101905, _mut101906); j++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
                    // P[j+1](c)
                    final double ppc = AOR_divide((AOR_minus(AOR_multiply(AOR_multiply((AOR_plus(AOR_multiply(2, j, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101870, _mut101871, _mut101872, _mut101873), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101874, _mut101875, _mut101876, _mut101877)), c, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101878, _mut101879, _mut101880, _mut101881), pc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101882, _mut101883, _mut101884, _mut101885), AOR_multiply(j, pmc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101886, _mut101887, _mut101888, _mut101889), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101890, _mut101891, _mut101892, _mut101893)), (AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101894, _mut101895, _mut101896, _mut101897)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101898, _mut101899, _mut101900, _mut101901);
                    pmc = pc;
                    pc = ppc;
                }
                // Now pc = P[n+1](c) and pmc = P[n](c).
                if (!done) {
                    if (ROR_less_equals(AOR_multiply(pa, pc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101907, _mut101908, _mut101909, _mut101910), 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101911, _mut101912, _mut101913, _mut101914, _mut101915)) {
                        b = c;
                        pmb = pmc;
                        pb = pc;
                    } else {
                        a = c;
                        pma = pmc;
                        pa = pc;
                    }
                    c = AOR_multiply(0.5, (AOR_plus(a, b, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101916, _mut101917, _mut101918, _mut101919)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101920, _mut101921, _mut101922, _mut101923);
                }
            }
            final double d = AOR_multiply(numberOfPoints, (AOR_minus(pmc, AOR_multiply(c, pc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101924, _mut101925, _mut101926, _mut101927), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101928, _mut101929, _mut101930, _mut101931)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101932, _mut101933, _mut101934, _mut101935);
            final double w = AOR_divide(AOR_multiply(2, (AOR_minus(1, AOR_multiply(c, c, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101936, _mut101937, _mut101938, _mut101939), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101940, _mut101941, _mut101942, _mut101943)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101944, _mut101945, _mut101946, _mut101947), (AOR_multiply(d, d, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101948, _mut101949, _mut101950, _mut101951)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101952, _mut101953, _mut101954, _mut101955);
            points[i] = c;
            weights[i] = w;
            final int idx = AOR_minus(AOR_minus(numberOfPoints, i, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101956, _mut101957, _mut101958, _mut101959), 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101960, _mut101961, _mut101962, _mut101963);
            points[idx] = -c;
            weights[idx] = w;
        }
        // a FindBugs warning.
        if (ROR_not_equals(AOR_remainder(numberOfPoints, 2, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101969, _mut101970, _mut101971, _mut101972), 0, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101973, _mut101974, _mut101975, _mut101976, _mut101977)) {
            double pmc = 1;
            for (int j = 1; ROR_less(j, numberOfPoints, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101990, _mut101991, _mut101992, _mut101993, _mut101994); j += 2) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34");
                pmc = AOR_divide(AOR_multiply(-j, pmc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101978, _mut101979, _mut101980, _mut101981), (AOR_plus(j, 1, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101982, _mut101983, _mut101984, _mut101985)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101986, _mut101987, _mut101988, _mut101989);
            }
            final double d = AOR_multiply(numberOfPoints, pmc, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101995, _mut101996, _mut101997, _mut101998);
            final double w = AOR_divide(2, (AOR_multiply(d, d, "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut101999, _mut102000, _mut102001, _mut102002)), "org.apache.commons.math3.analysis.integration.gauss.LegendreRuleFactory.computeRule_34", _mut102003, _mut102004, _mut102005, _mut102006);
            points[iMax] = 0d;
            weights[iMax] = w;
        }
        return new Pair<Double[], Double[]>(points, weights);
    }
}
