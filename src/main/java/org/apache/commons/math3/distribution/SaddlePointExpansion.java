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

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>
 * Utility class used by various distributions to accurately compute their
 * respective probability mass functions. The implementation for this class is
 * based on the Catherine Loader's <a target="_blank"
 * href="http://www.herine.net/stat/software/dbinom.html">dbinom</a> routines.
 * </p>
 * <p>
 * This class is not intended to be called directly.
 * </p>
 * <p>
 * References:
 * <ol>
 * <li>Catherine Loader (2000). "Fast and Accurate Computation of Binomial
 * Probabilities.". <a target="_blank"
 * href="http://www.herine.net/stat/papers/dbinom.pdf">
 * http://www.herine.net/stat/papers/dbinom.pdf</a></li>
 * </ol>
 * </p>
 *
 * @since 2.1
 */
final class SaddlePointExpansion {

    @Conditional
    public static boolean _mut53903 = false, _mut53904 = false, _mut53905 = false, _mut53906 = false, _mut53907 = false, _mut53908 = false, _mut53909 = false, _mut53910 = false, _mut53911 = false, _mut53912 = false, _mut53913 = false, _mut53914 = false, _mut53915 = false, _mut53916 = false, _mut53917 = false, _mut53918 = false, _mut53919 = false, _mut53920 = false, _mut53921 = false, _mut53922 = false, _mut53923 = false, _mut53924 = false, _mut53925 = false, _mut53926 = false, _mut53927 = false, _mut53928 = false, _mut53929 = false, _mut53930 = false, _mut53931 = false, _mut53932 = false, _mut53933 = false, _mut53934 = false, _mut53935 = false, _mut53936 = false, _mut53937 = false, _mut53938 = false, _mut53939 = false, _mut53940 = false, _mut53941 = false, _mut53942 = false, _mut53943 = false, _mut53944 = false, _mut53945 = false, _mut53946 = false, _mut53947 = false, _mut53948 = false, _mut53949 = false, _mut53950 = false, _mut53951 = false, _mut53952 = false, _mut53953 = false, _mut53954 = false, _mut53955 = false, _mut53956 = false, _mut53957 = false, _mut53958 = false, _mut53959 = false, _mut53960 = false, _mut53961 = false, _mut53962 = false, _mut53963 = false, _mut53964 = false, _mut53965 = false, _mut53966 = false, _mut53967 = false, _mut53968 = false, _mut53969 = false, _mut53970 = false, _mut53971 = false, _mut53972 = false, _mut53973 = false, _mut53974 = false, _mut53975 = false, _mut53976 = false, _mut53977 = false, _mut53978 = false, _mut53979 = false, _mut53980 = false, _mut53981 = false, _mut53982 = false, _mut53983 = false, _mut53984 = false, _mut53985 = false, _mut53986 = false, _mut53987 = false, _mut53988 = false, _mut53989 = false, _mut53990 = false, _mut53991 = false, _mut53992 = false, _mut53993 = false, _mut53994 = false, _mut53995 = false, _mut53996 = false, _mut53997 = false, _mut53998 = false, _mut53999 = false, _mut54000 = false, _mut54001 = false, _mut54002 = false, _mut54003 = false, _mut54004 = false, _mut54005 = false, _mut54006 = false, _mut54007 = false, _mut54008 = false, _mut54009 = false, _mut54010 = false, _mut54011 = false, _mut54012 = false, _mut54013 = false, _mut54014 = false, _mut54015 = false, _mut54016 = false, _mut54017 = false, _mut54018 = false, _mut54019 = false, _mut54020 = false, _mut54021 = false, _mut54022 = false, _mut54023 = false, _mut54024 = false, _mut54025 = false, _mut54026 = false, _mut54027 = false, _mut54028 = false, _mut54029 = false, _mut54030 = false, _mut54031 = false, _mut54032 = false, _mut54033 = false, _mut54034 = false, _mut54035 = false, _mut54036 = false, _mut54037 = false, _mut54038 = false, _mut54039 = false, _mut54040 = false, _mut54041 = false, _mut54042 = false, _mut54043 = false, _mut54044 = false, _mut54045 = false, _mut54046 = false, _mut54047 = false, _mut54048 = false, _mut54049 = false, _mut54050 = false, _mut54051 = false, _mut54052 = false, _mut54053 = false, _mut54054 = false, _mut54055 = false, _mut54056 = false, _mut54057 = false, _mut54058 = false, _mut54059 = false, _mut54060 = false, _mut54061 = false, _mut54062 = false, _mut54063 = false, _mut54064 = false, _mut54065 = false, _mut54066 = false, _mut54067 = false, _mut54068 = false, _mut54069 = false, _mut54070 = false, _mut54071 = false, _mut54072 = false, _mut54073 = false, _mut54074 = false, _mut54075 = false, _mut54076 = false, _mut54077 = false, _mut54078 = false, _mut54079 = false, _mut54080 = false, _mut54081 = false, _mut54082 = false, _mut54083 = false, _mut54084 = false, _mut54085 = false, _mut54086 = false, _mut54087 = false, _mut54088 = false, _mut54089 = false, _mut54090 = false, _mut54091 = false, _mut54092 = false, _mut54093 = false, _mut54094 = false, _mut54095 = false, _mut54096 = false, _mut54097 = false, _mut54098 = false, _mut54099 = false, _mut54100 = false, _mut54101 = false, _mut54102 = false, _mut54103 = false, _mut54104 = false, _mut54105 = false, _mut54106 = false, _mut54107 = false, _mut54108 = false, _mut54109 = false, _mut54110 = false, _mut54111 = false, _mut54112 = false, _mut54113 = false, _mut54114 = false, _mut54115 = false, _mut54116 = false, _mut54117 = false, _mut54118 = false, _mut54119 = false, _mut54120 = false, _mut54121 = false, _mut54122 = false, _mut54123 = false, _mut54124 = false, _mut54125 = false, _mut54126 = false, _mut54127 = false, _mut54128 = false, _mut54129 = false, _mut54130 = false, _mut54131 = false, _mut54132 = false, _mut54133 = false, _mut54134 = false, _mut54135 = false, _mut54136 = false, _mut54137 = false, _mut54138 = false, _mut54139 = false, _mut54140 = false, _mut54141 = false, _mut54142 = false, _mut54143 = false, _mut54144 = false, _mut54145 = false, _mut54146 = false, _mut54147 = false, _mut54148 = false, _mut54149 = false, _mut54150 = false, _mut54151 = false, _mut54152 = false, _mut54153 = false, _mut54154 = false, _mut54155 = false, _mut54156 = false, _mut54157 = false, _mut54158 = false, _mut54159 = false, _mut54160 = false, _mut54161 = false, _mut54162 = false, _mut54163 = false, _mut54164 = false, _mut54165 = false, _mut54166 = false, _mut54167 = false, _mut54168 = false, _mut54169 = false, _mut54170 = false;

    /**
     * 1/2 * log(2 &#960;).
     */
    private static final double HALF_LOG_2_PI = AOR_multiply(0.5, FastMath.log(MathUtils.TWO_PI), "org.apache.commons.math3.distribution.SaddlePointExpansion.sample_313", _mut53903, _mut53904, _mut53905, _mut53906);

    /**
     * exact Stirling expansion error for certain values.
     */
    private static final double[] EXACT_STIRLING_ERRORS = { 0.0, /* 0.0 */
    0.1534264097200273452913848, /* 0.5 */
    0.0810614667953272582196702, /* 1.0 */
    0.0548141210519176538961390, /* 1.5 */
    0.0413406959554092940938221, /* 2.0 */
    0.03316287351993628748511048, /* 2.5 */
    0.02767792568499833914878929, /* 3.0 */
    0.02374616365629749597132920, /* 3.5 */
    0.02079067210376509311152277, /* 4.0 */
    0.01848845053267318523077934, /* 4.5 */
    0.01664469118982119216319487, /* 5.0 */
    0.01513497322191737887351255, /* 5.5 */
    0.01387612882307074799874573, /* 6.0 */
    0.01281046524292022692424986, /* 6.5 */
    0.01189670994589177009505572, /* 7.0 */
    0.01110455975820691732662991, /* 7.5 */
    0.010411265261972096497478567, /* 8.0 */
    0.009799416126158803298389475, /* 8.5 */
    0.009255462182712732917728637, /* 9.0 */
    0.008768700134139385462952823, /* 9.5 */
    0.008330563433362871256469318, /* 10.0 */
    0.007934114564314020547248100, /* 10.5 */
    0.007573675487951840794972024, /* 11.0 */
    0.007244554301320383179543912, /* 11.5 */
    0.006942840107209529865664152, /* 12.0 */
    0.006665247032707682442354394, /* 12.5 */
    0.006408994188004207068439631, /* 13.0 */
    0.006171712263039457647532867, /* 13.5 */
    0.005951370112758847735624416, /* 14.0 */
    0.005746216513010115682023589, /* 14.5 */
    0.005554733551962801371038690 };

    /**
     * Default constructor.
     */
    private SaddlePointExpansion() {
        super();
    }

    /**
     * Compute the error of Stirling's series at the given value.
     * <p>
     * References:
     * <ol>
     * <li>Eric W. Weisstein. "Stirling's Series." From MathWorld--A Wolfram Web
     * Resource. <a target="_blank"
     * href="http://mathworld.wolfram.com/StirlingsSeries.html">
     * http://mathworld.wolfram.com/StirlingsSeries.html</a></li>
     * </ol>
     * </p>
     *
     * @param z the value.
     * @return the Striling's series error.
     */
    static double getStirlingError(double z) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106");
        double ret;
        if (ROR_less(z, 15.0, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53907, _mut53908, _mut53909, _mut53910, _mut53911)) {
            double z2 = AOR_multiply(2.0, z, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53952, _mut53953, _mut53954, _mut53955);
            if (ROR_equals(FastMath.floor(z2), z2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53956, _mut53957, _mut53958, _mut53959, _mut53960)) {
                ret = EXACT_STIRLING_ERRORS[(int) z2];
            } else {
                ret = AOR_minus(AOR_plus(AOR_minus(Gamma.logGamma(AOR_plus(z, 1.0, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53961, _mut53962, _mut53963, _mut53964)), AOR_multiply((AOR_plus(z, 0.5, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53965, _mut53966, _mut53967, _mut53968)), FastMath.log(z), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53969, _mut53970, _mut53971, _mut53972), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53973, _mut53974, _mut53975, _mut53976), z, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53977, _mut53978, _mut53979, _mut53980), HALF_LOG_2_PI, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53981, _mut53982, _mut53983, _mut53984);
            }
        } else {
            double z2 = AOR_multiply(z, z, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53912, _mut53913, _mut53914, _mut53915);
            ret = AOR_divide((AOR_minus(0.083333333333333333333, AOR_divide((AOR_minus(0.00277777777777777777778, AOR_divide((AOR_minus(0.00079365079365079365079365, AOR_divide((AOR_minus(0.000595238095238095238095238, AOR_divide(0.0008417508417508417508417508, z2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53916, _mut53917, _mut53918, _mut53919), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53920, _mut53921, _mut53922, _mut53923)), z2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53924, _mut53925, _mut53926, _mut53927), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53928, _mut53929, _mut53930, _mut53931)), z2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53932, _mut53933, _mut53934, _mut53935), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53936, _mut53937, _mut53938, _mut53939)), z2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53940, _mut53941, _mut53942, _mut53943), "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53944, _mut53945, _mut53946, _mut53947)), z, "org.apache.commons.math3.distribution.SaddlePointExpansion.getStirlingError_106", _mut53948, _mut53949, _mut53950, _mut53951);
        }
        return ret;
    }

    /**
     * A part of the deviance portion of the saddle point approximation.
     * <p>
     * References:
     * <ol>
     * <li>Catherine Loader (2000). "Fast and Accurate Computation of Binomial
     * Probabilities.". <a target="_blank"
     * href="http://www.herine.net/stat/papers/dbinom.pdf">
     * http://www.herine.net/stat/papers/dbinom.pdf</a></li>
     * </ol>
     * </p>
     *
     * @param x the x value.
     * @param mu the average.
     * @return a part of the deviance.
     */
    static double getDeviancePart(double x, double mu) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144");
        double ret;
        if (ROR_less(FastMath.abs(AOR_minus(x, mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut53985, _mut53986, _mut53987, _mut53988)), AOR_multiply(0.1, (AOR_plus(x, mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut53989, _mut53990, _mut53991, _mut53992)), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut53993, _mut53994, _mut53995, _mut53996), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut53997, _mut53998, _mut53999, _mut54000, _mut54001)) {
            double d = AOR_minus(x, mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54018, _mut54019, _mut54020, _mut54021);
            double v = AOR_divide(d, (AOR_plus(x, mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54022, _mut54023, _mut54024, _mut54025)), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54026, _mut54027, _mut54028, _mut54029);
            double s1 = AOR_multiply(v, d, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54030, _mut54031, _mut54032, _mut54033);
            double s = Double.NaN;
            double ej = AOR_multiply(AOR_multiply(2.0, x, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54034, _mut54035, _mut54036, _mut54037), v, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54038, _mut54039, _mut54040, _mut54041);
            v *= v;
            int j = 1;
            while (ROR_not_equals(s1, s, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54058, _mut54059, _mut54060, _mut54061, _mut54062)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144");
                s = s1;
                ej *= v;
                s1 = AOR_plus(s, AOR_divide(ej, (AOR_plus((AOR_multiply(j, 2, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54042, _mut54043, _mut54044, _mut54045)), 1, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54046, _mut54047, _mut54048, _mut54049)), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54050, _mut54051, _mut54052, _mut54053), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54054, _mut54055, _mut54056, _mut54057);
                ++j;
            }
            ret = s1;
        } else {
            ret = AOR_minus(AOR_plus(AOR_multiply(x, FastMath.log(AOR_divide(x, mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54002, _mut54003, _mut54004, _mut54005)), "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54006, _mut54007, _mut54008, _mut54009), mu, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54010, _mut54011, _mut54012, _mut54013), x, "org.apache.commons.math3.distribution.SaddlePointExpansion.getDeviancePart_144", _mut54014, _mut54015, _mut54016, _mut54017);
        }
        return ret;
    }

    /**
     * Compute the logarithm of the PMF for a binomial distribution
     * using the saddle point expansion.
     *
     * @param x the value at which the probability is evaluated.
     * @param n the number of trials.
     * @param p the probability of success.
     * @param q the probability of failure (1 - p).
     * @return log(p(x)).
     */
    static double logBinomialProbability(int x, int n, double p, double q) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177");
        double ret;
        if (ROR_equals(x, 0, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54063, _mut54064, _mut54065, _mut54066, _mut54067)) {
            if (ROR_less(p, 0.1, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54150, _mut54151, _mut54152, _mut54153, _mut54154)) {
                ret = AOR_minus(-getDeviancePart(n, AOR_multiply(n, q, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54159, _mut54160, _mut54161, _mut54162)), AOR_multiply(n, p, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54163, _mut54164, _mut54165, _mut54166), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54167, _mut54168, _mut54169, _mut54170);
            } else {
                ret = AOR_multiply(n, FastMath.log(q), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54155, _mut54156, _mut54157, _mut54158);
            }
        } else if (ROR_equals(x, n, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54068, _mut54069, _mut54070, _mut54071, _mut54072)) {
            if (ROR_less(q, 0.1, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54129, _mut54130, _mut54131, _mut54132, _mut54133)) {
                ret = AOR_minus(-getDeviancePart(n, AOR_multiply(n, p, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54138, _mut54139, _mut54140, _mut54141)), AOR_multiply(n, q, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54142, _mut54143, _mut54144, _mut54145), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54146, _mut54147, _mut54148, _mut54149);
            } else {
                ret = AOR_multiply(n, FastMath.log(p), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54134, _mut54135, _mut54136, _mut54137);
            }
        } else {
            ret = AOR_minus(AOR_minus(AOR_minus(AOR_minus(getStirlingError(n), getStirlingError(x), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54073, _mut54074, _mut54075, _mut54076), getStirlingError(AOR_minus(n, x, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54077, _mut54078, _mut54079, _mut54080)), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54081, _mut54082, _mut54083, _mut54084), getDeviancePart(x, AOR_multiply(n, p, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54085, _mut54086, _mut54087, _mut54088)), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54089, _mut54090, _mut54091, _mut54092), getDeviancePart(AOR_minus(n, x, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54093, _mut54094, _mut54095, _mut54096), AOR_multiply(n, q, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54097, _mut54098, _mut54099, _mut54100)), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54101, _mut54102, _mut54103, _mut54104);
            double f = AOR_divide((AOR_multiply(AOR_multiply(MathUtils.TWO_PI, x, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54105, _mut54106, _mut54107, _mut54108), (AOR_minus(n, x, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54109, _mut54110, _mut54111, _mut54112)), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54113, _mut54114, _mut54115, _mut54116)), n, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54117, _mut54118, _mut54119, _mut54120);
            ret = AOR_plus(AOR_multiply(-0.5, FastMath.log(f), "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54121, _mut54122, _mut54123, _mut54124), ret, "org.apache.commons.math3.distribution.SaddlePointExpansion.logBinomialProbability_177", _mut54125, _mut54126, _mut54127, _mut54128);
        }
        return ret;
    }
}
