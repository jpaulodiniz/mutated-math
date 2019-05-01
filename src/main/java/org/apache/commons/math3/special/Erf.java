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
package org.apache.commons.math3.special;

import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This is a utility class that provides computation methods related to the
 * error functions.
 */
public class Erf {

    @Conditional
    public static boolean _mut69782 = false, _mut69783 = false, _mut69784 = false, _mut69785 = false, _mut69786 = false, _mut69787 = false, _mut69788 = false, _mut69789 = false, _mut69790 = false, _mut69791 = false, _mut69792 = false, _mut69793 = false, _mut69794 = false, _mut69795 = false, _mut69796 = false, _mut69797 = false, _mut69798 = false, _mut69799 = false, _mut69800 = false, _mut69801 = false, _mut69802 = false, _mut69803 = false, _mut69804 = false, _mut69805 = false, _mut69806 = false, _mut69807 = false, _mut69808 = false, _mut69809 = false, _mut69810 = false, _mut69811 = false, _mut69812 = false, _mut69813 = false, _mut69814 = false, _mut69815 = false, _mut69816 = false, _mut69817 = false, _mut69818 = false, _mut69819 = false, _mut69820 = false, _mut69821 = false, _mut69822 = false, _mut69823 = false, _mut69824 = false, _mut69825 = false, _mut69826 = false, _mut69827 = false, _mut69828 = false, _mut69829 = false, _mut69830 = false, _mut69831 = false, _mut69832 = false, _mut69833 = false, _mut69834 = false, _mut69835 = false, _mut69836 = false, _mut69837 = false, _mut69838 = false, _mut69839 = false, _mut69840 = false, _mut69841 = false, _mut69842 = false, _mut69843 = false, _mut69844 = false, _mut69845 = false, _mut69846 = false, _mut69847 = false, _mut69848 = false, _mut69849 = false, _mut69850 = false, _mut69851 = false, _mut69852 = false, _mut69853 = false, _mut69854 = false, _mut69855 = false, _mut69856 = false, _mut69857 = false, _mut69858 = false, _mut69859 = false, _mut69860 = false, _mut69861 = false, _mut69862 = false, _mut69863 = false, _mut69864 = false, _mut69865 = false, _mut69866 = false, _mut69867 = false, _mut69868 = false, _mut69869 = false, _mut69870 = false, _mut69871 = false, _mut69872 = false, _mut69873 = false, _mut69874 = false, _mut69875 = false, _mut69876 = false, _mut69877 = false, _mut69878 = false, _mut69879 = false, _mut69880 = false, _mut69881 = false, _mut69882 = false, _mut69883 = false, _mut69884 = false, _mut69885 = false, _mut69886 = false, _mut69887 = false, _mut69888 = false, _mut69889 = false, _mut69890 = false, _mut69891 = false, _mut69892 = false, _mut69893 = false, _mut69894 = false, _mut69895 = false, _mut69896 = false, _mut69897 = false, _mut69898 = false, _mut69899 = false, _mut69900 = false, _mut69901 = false, _mut69902 = false, _mut69903 = false, _mut69904 = false, _mut69905 = false, _mut69906 = false, _mut69907 = false, _mut69908 = false, _mut69909 = false, _mut69910 = false, _mut69911 = false, _mut69912 = false, _mut69913 = false, _mut69914 = false, _mut69915 = false, _mut69916 = false, _mut69917 = false, _mut69918 = false, _mut69919 = false, _mut69920 = false, _mut69921 = false, _mut69922 = false, _mut69923 = false, _mut69924 = false, _mut69925 = false, _mut69926 = false, _mut69927 = false, _mut69928 = false, _mut69929 = false, _mut69930 = false, _mut69931 = false, _mut69932 = false, _mut69933 = false, _mut69934 = false, _mut69935 = false, _mut69936 = false, _mut69937 = false, _mut69938 = false, _mut69939 = false, _mut69940 = false, _mut69941 = false, _mut69942 = false, _mut69943 = false, _mut69944 = false, _mut69945 = false, _mut69946 = false, _mut69947 = false, _mut69948 = false, _mut69949 = false, _mut69950 = false, _mut69951 = false, _mut69952 = false, _mut69953 = false, _mut69954 = false, _mut69955 = false, _mut69956 = false, _mut69957 = false, _mut69958 = false, _mut69959 = false, _mut69960 = false, _mut69961 = false, _mut69962 = false, _mut69963 = false, _mut69964 = false, _mut69965 = false, _mut69966 = false, _mut69967 = false, _mut69968 = false, _mut69969 = false, _mut69970 = false, _mut69971 = false, _mut69972 = false, _mut69973 = false, _mut69974 = false, _mut69975 = false, _mut69976 = false, _mut69977 = false, _mut69978 = false, _mut69979 = false, _mut69980 = false, _mut69981 = false, _mut69982 = false, _mut69983 = false, _mut69984 = false, _mut69985 = false, _mut69986 = false, _mut69987 = false, _mut69988 = false, _mut69989 = false, _mut69990 = false, _mut69991 = false, _mut69992 = false, _mut69993 = false, _mut69994 = false, _mut69995 = false, _mut69996 = false, _mut69997 = false, _mut69998 = false, _mut69999 = false, _mut70000 = false, _mut70001 = false, _mut70002 = false, _mut70003 = false, _mut70004 = false, _mut70005 = false, _mut70006 = false, _mut70007 = false, _mut70008 = false, _mut70009 = false, _mut70010 = false, _mut70011 = false, _mut70012 = false, _mut70013 = false, _mut70014 = false, _mut70015 = false, _mut70016 = false, _mut70017 = false, _mut70018 = false, _mut70019 = false, _mut70020 = false, _mut70021 = false, _mut70022 = false, _mut70023 = false, _mut70024 = false, _mut70025 = false, _mut70026 = false, _mut70027 = false, _mut70028 = false, _mut70029 = false, _mut70030 = false, _mut70031 = false, _mut70032 = false, _mut70033 = false, _mut70034 = false, _mut70035 = false, _mut70036 = false, _mut70037 = false, _mut70038 = false, _mut70039 = false, _mut70040 = false, _mut70041 = false, _mut70042 = false, _mut70043 = false, _mut70044 = false, _mut70045 = false, _mut70046 = false, _mut70047 = false, _mut70048 = false, _mut70049 = false, _mut70050 = false, _mut70051 = false, _mut70052 = false, _mut70053 = false, _mut70054 = false, _mut70055 = false, _mut70056 = false, _mut70057 = false, _mut70058 = false, _mut70059 = false, _mut70060 = false, _mut70061 = false, _mut70062 = false, _mut70063 = false, _mut70064 = false, _mut70065 = false, _mut70066 = false, _mut70067 = false, _mut70068 = false, _mut70069 = false, _mut70070 = false, _mut70071 = false, _mut70072 = false, _mut70073 = false, _mut70074 = false, _mut70075 = false, _mut70076 = false, _mut70077 = false, _mut70078 = false, _mut70079 = false, _mut70080 = false, _mut70081 = false, _mut70082 = false, _mut70083 = false, _mut70084 = false, _mut70085 = false, _mut70086 = false, _mut70087 = false, _mut70088 = false, _mut70089 = false, _mut70090 = false, _mut70091 = false, _mut70092 = false, _mut70093 = false, _mut70094 = false, _mut70095 = false, _mut70096 = false, _mut70097 = false, _mut70098 = false, _mut70099 = false, _mut70100 = false, _mut70101 = false, _mut70102 = false, _mut70103 = false, _mut70104 = false, _mut70105 = false, _mut70106 = false, _mut70107 = false, _mut70108 = false, _mut70109 = false, _mut70110 = false, _mut70111 = false, _mut70112 = false, _mut70113 = false, _mut70114 = false, _mut70115 = false, _mut70116 = false, _mut70117 = false, _mut70118 = false, _mut70119 = false, _mut70120 = false, _mut70121 = false, _mut70122 = false, _mut70123 = false, _mut70124 = false, _mut70125 = false, _mut70126 = false, _mut70127 = false, _mut70128 = false, _mut70129 = false, _mut70130 = false, _mut70131 = false, _mut70132 = false, _mut70133 = false, _mut70134 = false, _mut70135 = false, _mut70136 = false, _mut70137 = false, _mut70138 = false, _mut70139 = false, _mut70140 = false, _mut70141 = false, _mut70142 = false, _mut70143 = false, _mut70144 = false, _mut70145 = false, _mut70146 = false, _mut70147 = false, _mut70148 = false, _mut70149 = false, _mut70150 = false, _mut70151 = false, _mut70152 = false, _mut70153 = false, _mut70154 = false, _mut70155 = false, _mut70156 = false, _mut70157 = false, _mut70158 = false, _mut70159 = false, _mut70160 = false, _mut70161 = false, _mut70162 = false, _mut70163 = false, _mut70164 = false, _mut70165 = false, _mut70166 = false, _mut70167 = false, _mut70168 = false, _mut70169 = false, _mut70170 = false, _mut70171 = false, _mut70172 = false, _mut70173 = false, _mut70174 = false, _mut70175 = false, _mut70176 = false, _mut70177 = false, _mut70178 = false, _mut70179 = false, _mut70180 = false, _mut70181 = false, _mut70182 = false, _mut70183 = false, _mut70184 = false, _mut70185 = false, _mut70186 = false, _mut70187 = false, _mut70188 = false, _mut70189 = false, _mut70190 = false, _mut70191 = false, _mut70192 = false, _mut70193 = false, _mut70194 = false, _mut70195 = false, _mut70196 = false, _mut70197 = false, _mut70198 = false, _mut70199 = false, _mut70200 = false, _mut70201 = false, _mut70202 = false, _mut70203 = false, _mut70204 = false, _mut70205 = false, _mut70206 = false, _mut70207 = false, _mut70208 = false, _mut70209 = false, _mut70210 = false, _mut70211 = false, _mut70212 = false, _mut70213 = false, _mut70214 = false, _mut70215 = false, _mut70216 = false, _mut70217 = false, _mut70218 = false, _mut70219 = false, _mut70220 = false, _mut70221 = false, _mut70222 = false, _mut70223 = false, _mut70224 = false, _mut70225 = false, _mut70226 = false, _mut70227 = false, _mut70228 = false, _mut70229 = false, _mut70230 = false, _mut70231 = false, _mut70232 = false, _mut70233 = false, _mut70234 = false, _mut70235 = false, _mut70236 = false, _mut70237 = false, _mut70238 = false, _mut70239 = false, _mut70240 = false, _mut70241 = false, _mut70242 = false, _mut70243 = false, _mut70244 = false, _mut70245 = false, _mut70246 = false, _mut70247 = false, _mut70248 = false, _mut70249 = false, _mut70250 = false, _mut70251 = false, _mut70252 = false, _mut70253 = false, _mut70254 = false, _mut70255 = false, _mut70256 = false, _mut70257 = false, _mut70258 = false, _mut70259 = false, _mut70260 = false, _mut70261 = false, _mut70262 = false, _mut70263 = false, _mut70264 = false, _mut70265 = false, _mut70266 = false, _mut70267 = false, _mut70268 = false, _mut70269 = false, _mut70270 = false, _mut70271 = false, _mut70272 = false, _mut70273 = false, _mut70274 = false, _mut70275 = false, _mut70276 = false, _mut70277 = false, _mut70278 = false, _mut70279 = false, _mut70280 = false, _mut70281 = false, _mut70282 = false, _mut70283 = false, _mut70284 = false, _mut70285 = false, _mut70286 = false, _mut70287 = false, _mut70288 = false, _mut70289 = false, _mut70290 = false, _mut70291 = false, _mut70292 = false, _mut70293 = false, _mut70294 = false, _mut70295 = false, _mut70296 = false, _mut70297 = false, _mut70298 = false, _mut70299 = false, _mut70300 = false, _mut70301 = false, _mut70302 = false, _mut70303 = false, _mut70304 = false, _mut70305 = false, _mut70306 = false, _mut70307 = false, _mut70308 = false, _mut70309 = false, _mut70310 = false, _mut70311 = false, _mut70312 = false, _mut70313 = false, _mut70314 = false, _mut70315 = false, _mut70316 = false, _mut70317 = false, _mut70318 = false, _mut70319 = false, _mut70320 = false, _mut70321 = false, _mut70322 = false, _mut70323 = false, _mut70324 = false, _mut70325 = false, _mut70326 = false, _mut70327 = false, _mut70328 = false, _mut70329 = false, _mut70330 = false, _mut70331 = false, _mut70332 = false, _mut70333 = false, _mut70334 = false, _mut70335 = false, _mut70336 = false, _mut70337 = false, _mut70338 = false, _mut70339 = false, _mut70340 = false, _mut70341 = false, _mut70342 = false, _mut70343 = false, _mut70344 = false, _mut70345 = false, _mut70346 = false, _mut70347 = false, _mut70348 = false, _mut70349 = false, _mut70350 = false, _mut70351 = false;

    /**
     * The number {@code X_CRIT} is used by {@link #erf(double, double)} internally.
     * This number solves {@code erf(x)=0.5} within 1ulp.
     * More precisely, the current implementations of
     * {@link #erf(double)} and {@link #erfc(double)} satisfy:<br/>
     * {@code erf(X_CRIT) < 0.5},<br/>
     * {@code erf(Math.nextUp(X_CRIT) > 0.5},<br/>
     * {@code erfc(X_CRIT) = 0.5}, and<br/>
     * {@code erfc(Math.nextUp(X_CRIT) < 0.5}
     */
    private static final double X_CRIT = 0.4769362762044697;

    /**
     * Default constructor.  Prohibit instantiation.
     */
    private Erf() {
    }

    /**
     * Returns the error function.
     *
     * <p>erf(x) = 2/&radic;&pi; <sub>0</sub>&int;<sup>x</sup> e<sup>-t<sup>2</sup></sup>dt </p>
     *
     * <p>This implementation computes erf(x) using the
     * {@link Gamma#regularizedGammaP(double, double, double, int) regularized gamma function},
     * following <a href="http://mathworld.wolfram.com/Erf.html"> Erf</a>, equation (3)</p>
     *
     * <p>The value returned is always between -1 and 1 (inclusive).
     * If {@code abs(x) > 40}, then {@code erf(x)} is indistinguishable from
     * either 1 or -1 as a double, so the appropriate extreme value is returned.
     * </p>
     *
     * @param x the value.
     * @return the error function erf(x)
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     * @see Gamma#regularizedGammaP(double, double, double, int)
     */
    public static double erf(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Erf.erf_65");
        if (ROR_greater(FastMath.abs(x), 40, "org.apache.commons.math3.special.Erf.erf_65", _mut69782, _mut69783, _mut69784, _mut69785, _mut69786)) {
            return ROR_greater(x, 0, "org.apache.commons.math3.special.Erf.erf_65", _mut69787, _mut69788, _mut69789, _mut69790, _mut69791) ? 1 : -1;
        }
        final double ret = Gamma.regularizedGammaP(0.5, AOR_multiply(x, x, "org.apache.commons.math3.special.Erf.erf_65", _mut69792, _mut69793, _mut69794, _mut69795), 1.0e-15, 10000);
        return ROR_less(x, 0, "org.apache.commons.math3.special.Erf.erf_65", _mut69796, _mut69797, _mut69798, _mut69799, _mut69800) ? -ret : ret;
    }

    /**
     * Returns the complementary error function.
     *
     * <p>erfc(x) = 2/&radic;&pi; <sub>x</sub>&int;<sup>&infin;</sup> e<sup>-t<sup>2</sup></sup>dt
     * <br/>
     *    = 1 - {@link #erf(double) erf(x)} </p>
     *
     * <p>This implementation computes erfc(x) using the
     * {@link Gamma#regularizedGammaQ(double, double, double, int) regularized gamma function},
     * following <a href="http://mathworld.wolfram.com/Erf.html"> Erf</a>, equation (3).</p>
     *
     * <p>The value returned is always between 0 and 2 (inclusive).
     * If {@code abs(x) > 40}, then {@code erf(x)} is indistinguishable from
     * either 0 or 2 as a double, so the appropriate extreme value is returned.
     * </p>
     *
     * @param x the value
     * @return the complementary error function erfc(x)
     * @throws org.apache.commons.math3.exception.MaxCountExceededException
     * if the algorithm fails to converge.
     * @see Gamma#regularizedGammaQ(double, double, double, int)
     * @since 2.2
     */
    public static double erfc(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Erf.erfc_96");
        if (ROR_greater(FastMath.abs(x), 40, "org.apache.commons.math3.special.Erf.erfc_96", _mut69801, _mut69802, _mut69803, _mut69804, _mut69805)) {
            return ROR_greater(x, 0, "org.apache.commons.math3.special.Erf.erfc_96", _mut69806, _mut69807, _mut69808, _mut69809, _mut69810) ? 0 : 2;
        }
        final double ret = Gamma.regularizedGammaQ(0.5, AOR_multiply(x, x, "org.apache.commons.math3.special.Erf.erfc_96", _mut69811, _mut69812, _mut69813, _mut69814), 1.0e-15, 10000);
        return ROR_less(x, 0, "org.apache.commons.math3.special.Erf.erfc_96", _mut69815, _mut69816, _mut69817, _mut69818, _mut69819) ? AOR_minus(2, ret, "org.apache.commons.math3.special.Erf.erfc_96", _mut69820, _mut69821, _mut69822, _mut69823) : ret;
    }

    /**
     * Returns the difference between erf(x1) and erf(x2).
     *
     * The implementation uses either erf(double) or erfc(double)
     * depending on which provides the most precise result.
     *
     * @param x1 the first value
     * @param x2 the second value
     * @return erf(x2) - erf(x1)
     */
    public static double erf(double x1, double x2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Erf.erf_114");
        if (ROR_greater(x1, x2, "org.apache.commons.math3.special.Erf.erf_114", _mut69824, _mut69825, _mut69826, _mut69827, _mut69828)) {
            return -erf(x2, x1);
        }
        return ROR_less(x1, -X_CRIT, "org.apache.commons.math3.special.Erf.erf_114", _mut69829, _mut69830, _mut69831, _mut69832, _mut69833) ? ROR_less(x2, 0.0, "org.apache.commons.math3.special.Erf.erf_114", _mut69853, _mut69854, _mut69855, _mut69856, _mut69857) ? AOR_minus(erfc(-x2), erfc(-x1), "org.apache.commons.math3.special.Erf.erf_114", _mut69862, _mut69863, _mut69864, _mut69865) : AOR_minus(erf(x2), erf(x1), "org.apache.commons.math3.special.Erf.erf_114", _mut69858, _mut69859, _mut69860, _mut69861) : (_mut69844 ? (ROR_greater(x2, X_CRIT, "org.apache.commons.math3.special.Erf.erf_114", _mut69834, _mut69835, _mut69836, _mut69837, _mut69838) || ROR_greater(x1, 0.0, "org.apache.commons.math3.special.Erf.erf_114", _mut69839, _mut69840, _mut69841, _mut69842, _mut69843)) : (ROR_greater(x2, X_CRIT, "org.apache.commons.math3.special.Erf.erf_114", _mut69834, _mut69835, _mut69836, _mut69837, _mut69838) && ROR_greater(x1, 0.0, "org.apache.commons.math3.special.Erf.erf_114", _mut69839, _mut69840, _mut69841, _mut69842, _mut69843))) ? AOR_minus(erfc(x1), erfc(x2), "org.apache.commons.math3.special.Erf.erf_114", _mut69849, _mut69850, _mut69851, _mut69852) : AOR_minus(erf(x2), erf(x1), "org.apache.commons.math3.special.Erf.erf_114", _mut69845, _mut69846, _mut69847, _mut69848);
    }

    /**
     * Returns the inverse erf.
     * <p>
     * This implementation is described in the paper:
     * <a href="http://people.maths.ox.ac.uk/gilesm/files/gems_erfinv.pdf">Approximating
     * the erfinv function</a> by Mike Giles, Oxford-Man Institute of Quantitative Finance,
     * which was published in GPU Computing Gems, volume 2, 2010.
     * The source code is available <a href="http://gpucomputing.net/?q=node/1828">here</a>.
     * </p>
     * @param x the value
     * @return t such that x = erf(t)
     * @since 3.2
     */
    public static double erfInv(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Erf.erfInv_142");
        // would induce rounding errors near the boundaries +/-1
        double w = -FastMath.log(AOR_multiply((AOR_minus(1.0, x, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69866, _mut69867, _mut69868, _mut69869)), (AOR_plus(1.0, x, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69870, _mut69871, _mut69872, _mut69873)), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69874, _mut69875, _mut69876, _mut69877));
        double p;
        if (ROR_less(w, 6.25, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69878, _mut69879, _mut69880, _mut69881, _mut69882)) {
            w -= 3.125;
            p = -3.6444120640178196996e-21;
            p = AOR_plus(-1.685059138182016589e-19, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70168, _mut70169, _mut70170, _mut70171), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70172, _mut70173, _mut70174, _mut70175);
            p = AOR_plus(1.2858480715256400167e-18, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70176, _mut70177, _mut70178, _mut70179), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70180, _mut70181, _mut70182, _mut70183);
            p = AOR_plus(1.115787767802518096e-17, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70184, _mut70185, _mut70186, _mut70187), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70188, _mut70189, _mut70190, _mut70191);
            p = AOR_plus(-1.333171662854620906e-16, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70192, _mut70193, _mut70194, _mut70195), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70196, _mut70197, _mut70198, _mut70199);
            p = AOR_plus(2.0972767875968561637e-17, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70200, _mut70201, _mut70202, _mut70203), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70204, _mut70205, _mut70206, _mut70207);
            p = AOR_plus(6.6376381343583238325e-15, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70208, _mut70209, _mut70210, _mut70211), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70212, _mut70213, _mut70214, _mut70215);
            p = AOR_plus(-4.0545662729752068639e-14, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70216, _mut70217, _mut70218, _mut70219), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70220, _mut70221, _mut70222, _mut70223);
            p = AOR_plus(-8.1519341976054721522e-14, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70224, _mut70225, _mut70226, _mut70227), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70228, _mut70229, _mut70230, _mut70231);
            p = AOR_plus(2.6335093153082322977e-12, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70232, _mut70233, _mut70234, _mut70235), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70236, _mut70237, _mut70238, _mut70239);
            p = AOR_plus(-1.2975133253453532498e-11, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70240, _mut70241, _mut70242, _mut70243), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70244, _mut70245, _mut70246, _mut70247);
            p = AOR_plus(-5.4154120542946279317e-11, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70248, _mut70249, _mut70250, _mut70251), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70252, _mut70253, _mut70254, _mut70255);
            p = AOR_plus(1.051212273321532285e-09, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70256, _mut70257, _mut70258, _mut70259), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70260, _mut70261, _mut70262, _mut70263);
            p = AOR_plus(-4.1126339803469836976e-09, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70264, _mut70265, _mut70266, _mut70267), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70268, _mut70269, _mut70270, _mut70271);
            p = AOR_plus(-2.9070369957882005086e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70272, _mut70273, _mut70274, _mut70275), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70276, _mut70277, _mut70278, _mut70279);
            p = AOR_plus(4.2347877827932403518e-07, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70280, _mut70281, _mut70282, _mut70283), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70284, _mut70285, _mut70286, _mut70287);
            p = AOR_plus(-1.3654692000834678645e-06, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70288, _mut70289, _mut70290, _mut70291), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70292, _mut70293, _mut70294, _mut70295);
            p = AOR_plus(-1.3882523362786468719e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70296, _mut70297, _mut70298, _mut70299), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70300, _mut70301, _mut70302, _mut70303);
            p = AOR_plus(0.0001867342080340571352, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70304, _mut70305, _mut70306, _mut70307), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70308, _mut70309, _mut70310, _mut70311);
            p = AOR_plus(-0.00074070253416626697512, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70312, _mut70313, _mut70314, _mut70315), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70316, _mut70317, _mut70318, _mut70319);
            p = AOR_plus(-0.0060336708714301490533, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70320, _mut70321, _mut70322, _mut70323), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70324, _mut70325, _mut70326, _mut70327);
            p = AOR_plus(0.24015818242558961693, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70328, _mut70329, _mut70330, _mut70331), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70332, _mut70333, _mut70334, _mut70335);
            p = AOR_plus(1.6536545626831027356, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70336, _mut70337, _mut70338, _mut70339), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70340, _mut70341, _mut70342, _mut70343);
        } else if (ROR_less(w, 16.0, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69883, _mut69884, _mut69885, _mut69886, _mut69887)) {
            w = AOR_minus(FastMath.sqrt(w), 3.25, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70020, _mut70021, _mut70022, _mut70023);
            p = 2.2137376921775787049e-09;
            p = AOR_plus(9.0756561938885390979e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70024, _mut70025, _mut70026, _mut70027), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70028, _mut70029, _mut70030, _mut70031);
            p = AOR_plus(-2.7517406297064545428e-07, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70032, _mut70033, _mut70034, _mut70035), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70036, _mut70037, _mut70038, _mut70039);
            p = AOR_plus(1.8239629214389227755e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70040, _mut70041, _mut70042, _mut70043), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70044, _mut70045, _mut70046, _mut70047);
            p = AOR_plus(1.5027403968909827627e-06, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70048, _mut70049, _mut70050, _mut70051), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70052, _mut70053, _mut70054, _mut70055);
            p = AOR_plus(-4.013867526981545969e-06, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70056, _mut70057, _mut70058, _mut70059), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70060, _mut70061, _mut70062, _mut70063);
            p = AOR_plus(2.9234449089955446044e-06, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70064, _mut70065, _mut70066, _mut70067), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70068, _mut70069, _mut70070, _mut70071);
            p = AOR_plus(1.2475304481671778723e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70072, _mut70073, _mut70074, _mut70075), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70076, _mut70077, _mut70078, _mut70079);
            p = AOR_plus(-4.7318229009055733981e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70080, _mut70081, _mut70082, _mut70083), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70084, _mut70085, _mut70086, _mut70087);
            p = AOR_plus(6.8284851459573175448e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70088, _mut70089, _mut70090, _mut70091), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70092, _mut70093, _mut70094, _mut70095);
            p = AOR_plus(2.4031110387097893999e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70096, _mut70097, _mut70098, _mut70099), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70100, _mut70101, _mut70102, _mut70103);
            p = AOR_plus(-0.0003550375203628474796, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70104, _mut70105, _mut70106, _mut70107), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70108, _mut70109, _mut70110, _mut70111);
            p = AOR_plus(0.00095328937973738049703, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70112, _mut70113, _mut70114, _mut70115), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70116, _mut70117, _mut70118, _mut70119);
            p = AOR_plus(-0.0016882755560235047313, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70120, _mut70121, _mut70122, _mut70123), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70124, _mut70125, _mut70126, _mut70127);
            p = AOR_plus(0.0024914420961078508066, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70128, _mut70129, _mut70130, _mut70131), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70132, _mut70133, _mut70134, _mut70135);
            p = AOR_plus(-0.0037512085075692412107, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70136, _mut70137, _mut70138, _mut70139), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70140, _mut70141, _mut70142, _mut70143);
            p = AOR_plus(0.005370914553590063617, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70144, _mut70145, _mut70146, _mut70147), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70148, _mut70149, _mut70150, _mut70151);
            p = AOR_plus(1.0052589676941592334, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70152, _mut70153, _mut70154, _mut70155), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70156, _mut70157, _mut70158, _mut70159);
            p = AOR_plus(3.0838856104922207635, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70160, _mut70161, _mut70162, _mut70163), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70164, _mut70165, _mut70166, _mut70167);
        } else if (!Double.isInfinite(w)) {
            w = AOR_minus(FastMath.sqrt(w), 5.0, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69888, _mut69889, _mut69890, _mut69891);
            p = -2.7109920616438573243e-11;
            p = AOR_plus(-2.5556418169965252055e-10, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69892, _mut69893, _mut69894, _mut69895), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69896, _mut69897, _mut69898, _mut69899);
            p = AOR_plus(1.5076572693500548083e-09, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69900, _mut69901, _mut69902, _mut69903), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69904, _mut69905, _mut69906, _mut69907);
            p = AOR_plus(-3.7894654401267369937e-09, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69908, _mut69909, _mut69910, _mut69911), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69912, _mut69913, _mut69914, _mut69915);
            p = AOR_plus(7.6157012080783393804e-09, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69916, _mut69917, _mut69918, _mut69919), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69920, _mut69921, _mut69922, _mut69923);
            p = AOR_plus(-1.4960026627149240478e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69924, _mut69925, _mut69926, _mut69927), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69928, _mut69929, _mut69930, _mut69931);
            p = AOR_plus(2.9147953450901080826e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69932, _mut69933, _mut69934, _mut69935), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69936, _mut69937, _mut69938, _mut69939);
            p = AOR_plus(-6.7711997758452339498e-08, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69940, _mut69941, _mut69942, _mut69943), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69944, _mut69945, _mut69946, _mut69947);
            p = AOR_plus(2.2900482228026654717e-07, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69948, _mut69949, _mut69950, _mut69951), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69952, _mut69953, _mut69954, _mut69955);
            p = AOR_plus(-9.9298272942317002539e-07, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69956, _mut69957, _mut69958, _mut69959), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69960, _mut69961, _mut69962, _mut69963);
            p = AOR_plus(4.5260625972231537039e-06, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69964, _mut69965, _mut69966, _mut69967), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69968, _mut69969, _mut69970, _mut69971);
            p = AOR_plus(-1.9681778105531670567e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69972, _mut69973, _mut69974, _mut69975), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69976, _mut69977, _mut69978, _mut69979);
            p = AOR_plus(7.5995277030017761139e-05, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69980, _mut69981, _mut69982, _mut69983), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69984, _mut69985, _mut69986, _mut69987);
            p = AOR_plus(-0.00021503011930044477347, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69988, _mut69989, _mut69990, _mut69991), "org.apache.commons.math3.special.Erf.erfInv_142", _mut69992, _mut69993, _mut69994, _mut69995);
            p = AOR_plus(-0.00013871931833623122026, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut69996, _mut69997, _mut69998, _mut69999), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70000, _mut70001, _mut70002, _mut70003);
            p = AOR_plus(1.0103004648645343977, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70004, _mut70005, _mut70006, _mut70007), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70008, _mut70009, _mut70010, _mut70011);
            p = AOR_plus(4.8499064014085844221, AOR_multiply(p, w, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70012, _mut70013, _mut70014, _mut70015), "org.apache.commons.math3.special.Erf.erfInv_142", _mut70016, _mut70017, _mut70018, _mut70019);
        } else {
            // instead of the correct positive infinity.
            p = Double.POSITIVE_INFINITY;
        }
        return AOR_multiply(p, x, "org.apache.commons.math3.special.Erf.erfInv_142", _mut70344, _mut70345, _mut70346, _mut70347);
    }

    /**
     * Returns the inverse erfc.
     * @param x the value
     * @return t such that x = erfc(t)
     * @since 3.2
     */
    public static double erfcInv(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.special.Erf.erfcInv_238");
        return erfInv(AOR_minus(1, x, "org.apache.commons.math3.special.Erf.erfcInv_238", _mut70348, _mut70349, _mut70350, _mut70351));
    }
}
