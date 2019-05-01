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
package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Gaussian_function">
 *  Gaussian</a> function.
 *
 * @since 3.0
 */
public class Gaussian implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut90964 = false, _mut90965 = false, _mut90966 = false, _mut90967 = false, _mut90968 = false, _mut90969 = false, _mut90970 = false, _mut90971 = false, _mut90972 = false, _mut90973 = false, _mut90974 = false, _mut90975 = false, _mut90976 = false, _mut90977 = false, _mut90978 = false, _mut90979 = false, _mut90980 = false, _mut90981 = false, _mut90982 = false, _mut90983 = false, _mut90984 = false, _mut90985 = false, _mut90986 = false, _mut90987 = false, _mut90988 = false, _mut90989 = false, _mut90990 = false, _mut90991 = false, _mut90992 = false, _mut90993 = false, _mut90994 = false, _mut90995 = false, _mut90996 = false, _mut90997 = false, _mut90998 = false, _mut90999 = false, _mut91000 = false, _mut91001 = false, _mut91002 = false, _mut91003 = false, _mut91004 = false, _mut91005 = false, _mut91006 = false, _mut91007 = false, _mut91008 = false, _mut91009 = false, _mut91010 = false, _mut91011 = false, _mut91012 = false, _mut91013 = false, _mut91014 = false, _mut91015 = false, _mut91016 = false, _mut91017 = false, _mut91018 = false, _mut91019 = false, _mut91020 = false, _mut91021 = false, _mut91022 = false, _mut91023 = false, _mut91024 = false, _mut91025 = false, _mut91026 = false, _mut91027 = false, _mut91028 = false, _mut91029 = false, _mut91030 = false, _mut91031 = false, _mut91032 = false, _mut91033 = false, _mut91034 = false, _mut91035 = false, _mut91036 = false, _mut91037 = false, _mut91038 = false, _mut91039 = false, _mut91040 = false, _mut91041 = false, _mut91042 = false, _mut91043 = false, _mut91044 = false, _mut91045 = false, _mut91046 = false, _mut91047 = false, _mut91048 = false, _mut91049 = false, _mut91050 = false, _mut91051 = false, _mut91052 = false, _mut91053 = false, _mut91054 = false, _mut91055 = false, _mut91056 = false, _mut91057 = false, _mut91058 = false, _mut91059 = false, _mut91060 = false, _mut91061 = false, _mut91062 = false, _mut91063 = false, _mut91064 = false, _mut91065 = false, _mut91066 = false, _mut91067 = false, _mut91068 = false, _mut91069 = false, _mut91070 = false, _mut91071 = false, _mut91072 = false, _mut91073 = false, _mut91074 = false, _mut91075 = false, _mut91076 = false, _mut91077 = false, _mut91078 = false, _mut91079 = false, _mut91080 = false, _mut91081 = false, _mut91082 = false, _mut91083 = false, _mut91084 = false, _mut91085 = false, _mut91086 = false, _mut91087 = false, _mut91088 = false, _mut91089 = false, _mut91090 = false, _mut91091 = false, _mut91092 = false, _mut91093 = false, _mut91094 = false, _mut91095 = false, _mut91096 = false, _mut91097 = false, _mut91098 = false, _mut91099 = false, _mut91100 = false, _mut91101 = false, _mut91102 = false, _mut91103 = false, _mut91104 = false, _mut91105 = false, _mut91106 = false, _mut91107 = false, _mut91108 = false, _mut91109 = false, _mut91110 = false, _mut91111 = false, _mut91112 = false, _mut91113 = false, _mut91114 = false, _mut91115 = false, _mut91116 = false, _mut91117 = false, _mut91118 = false, _mut91119 = false, _mut91120 = false, _mut91121 = false, _mut91122 = false, _mut91123 = false, _mut91124 = false, _mut91125 = false, _mut91126 = false, _mut91127 = false, _mut91128 = false, _mut91129 = false, _mut91130 = false, _mut91131 = false, _mut91132 = false, _mut91133 = false, _mut91134 = false, _mut91135 = false, _mut91136 = false, _mut91137 = false, _mut91138 = false, _mut91139 = false, _mut91140 = false, _mut91141 = false, _mut91142 = false, _mut91143 = false, _mut91144 = false, _mut91145 = false, _mut91146 = false, _mut91147 = false, _mut91148 = false, _mut91149 = false, _mut91150 = false, _mut91151 = false, _mut91152 = false, _mut91153 = false, _mut91154 = false, _mut91155 = false, _mut91156 = false, _mut91157 = false, _mut91158 = false, _mut91159 = false, _mut91160 = false, _mut91161 = false, _mut91162 = false, _mut91163 = false, _mut91164 = false, _mut91165 = false, _mut91166 = false, _mut91167 = false, _mut91168 = false;

    /**
     * Mean.
     */
    private final double mean;

    /**
     * Inverse of the standard deviation.
     */
    private final double is;

    /**
     * Inverse of twice the square of the standard deviation.
     */
    private final double i2s2;

    /**
     * Normalization factor.
     */
    private final double norm;

    /**
     * Gaussian with given normalization factor, mean and standard deviation.
     *
     * @param norm Normalization factor.
     * @param mean Mean.
     * @param sigma Standard deviation.
     * @throws NotStrictlyPositiveException if {@code sigma <= 0}.
     */
    public Gaussian(double norm, double mean, double sigma) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.Gaussian_58");
        if (ROR_less_equals(sigma, 0, "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_58", _mut90964, _mut90965, _mut90966, _mut90967, _mut90968)) {
            throw new NotStrictlyPositiveException(sigma);
        }
        this.norm = norm;
        this.mean = mean;
        this.is = AOR_divide(1, sigma, "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_58", _mut90969, _mut90970, _mut90971, _mut90972);
        this.i2s2 = AOR_multiply(AOR_multiply(0.5, is, "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_58", _mut90973, _mut90974, _mut90975, _mut90976), is, "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_58", _mut90977, _mut90978, _mut90979, _mut90980);
    }

    /**
     * Normalized gaussian with given mean and standard deviation.
     *
     * @param mean Mean.
     * @param sigma Standard deviation.
     * @throws NotStrictlyPositiveException if {@code sigma <= 0}.
     */
    public Gaussian(double mean, double sigma) throws NotStrictlyPositiveException {
        this(AOR_divide(1, (AOR_multiply(sigma, FastMath.sqrt(AOR_multiply(2, Math.PI, "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_79", _mut90981, _mut90982, _mut90983, _mut90984)), "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_79", _mut90985, _mut90986, _mut90987, _mut90988)), "org.apache.commons.math3.analysis.function.Gaussian.Gaussian_79", _mut90989, _mut90990, _mut90991, _mut90992), mean, sigma);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.Gaussian_79");
    }

    /**
     * Normalized gaussian with zero mean and unit standard deviation.
     */
    public Gaussian() {
        this(0, 1);
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_93");
        return value(AOR_minus(x, mean, "org.apache.commons.math3.analysis.function.Gaussian.value_93", _mut90993, _mut90994, _mut90995, _mut90996), norm, i2s2);
    }

    /**
     * {@inheritDoc}
     * @deprecated as of 3.1, replaced by {@link #value(DerivativeStructure)}
     */
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /**
     * Parametric function where the input array contains the parameters of
     * the Gaussian, ordered as follows:
     * <ul>
     *  <li>Norm</li>
     *  <li>Mean</li>
     *  <li>Standard deviation</li>
     * </ul>
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * Computes the value of the Gaussian at {@code x}.
         *
         * @param x Value for which the function must be computed.
         * @param param Values of norm, mean and standard deviation.
         * @return the value of the function.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         * @throws NotStrictlyPositiveException if {@code param[2]} is negative.
         */
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_126");
            validateParameters(param);
            final double diff = AOR_minus(x, param[1], "org.apache.commons.math3.analysis.function.Gaussian.value_126", _mut90997, _mut90998, _mut90999, _mut91000);
            final double i2s2 = AOR_divide(1, (AOR_multiply(AOR_multiply(2, param[2], "org.apache.commons.math3.analysis.function.Gaussian.value_126", _mut91001, _mut91002, _mut91003, _mut91004), param[2], "org.apache.commons.math3.analysis.function.Gaussian.value_126", _mut91005, _mut91006, _mut91007, _mut91008)), "org.apache.commons.math3.analysis.function.Gaussian.value_126", _mut91009, _mut91010, _mut91011, _mut91012);
            return Gaussian.value(diff, param[0], i2s2);
        }

        /**
         * Computes the value of the gradient at {@code x}.
         * The components of the gradient vector are the partial
         * derivatives of the function with respect to each of the
         * <em>parameters</em> (norm, mean and standard deviation).
         *
         * @param x Value at which the gradient must be computed.
         * @param param Values of norm, mean and standard deviation.
         * @return the gradient vector at {@code x}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         * @throws NotStrictlyPositiveException if {@code param[2]} is negative.
         */
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.gradient_151");
            validateParameters(param);
            final double norm = param[0];
            final double diff = AOR_minus(x, param[1], "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91013, _mut91014, _mut91015, _mut91016);
            final double sigma = param[2];
            final double i2s2 = AOR_divide(1, (AOR_multiply(AOR_multiply(2, sigma, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91017, _mut91018, _mut91019, _mut91020), sigma, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91021, _mut91022, _mut91023, _mut91024)), "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91025, _mut91026, _mut91027, _mut91028);
            final double n = Gaussian.value(diff, 1, i2s2);
            final double m = AOR_multiply(AOR_multiply(AOR_multiply(AOR_multiply(norm, n, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91029, _mut91030, _mut91031, _mut91032), 2, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91033, _mut91034, _mut91035, _mut91036), i2s2, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91037, _mut91038, _mut91039, _mut91040), diff, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91041, _mut91042, _mut91043, _mut91044);
            final double s = AOR_divide(AOR_multiply(m, diff, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91045, _mut91046, _mut91047, _mut91048), sigma, "org.apache.commons.math3.analysis.function.Gaussian.gradient_151", _mut91049, _mut91050, _mut91051, _mut91052);
            return new double[] { n, m, s };
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double,double[])} and {@link #gradient(double,double[])}
         * methods.
         *
         * @param param Values of norm, mean and standard deviation.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 3.
         * @throws NotStrictlyPositiveException if {@code param[2]} is negative.
         */
        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.validateParameters_180");
            if (param == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(param.length, 3, "org.apache.commons.math3.analysis.function.Gaussian.validateParameters_180", _mut91053, _mut91054, _mut91055, _mut91056, _mut91057)) {
                throw new DimensionMismatchException(param.length, 3);
            }
            if (ROR_less_equals(param[2], 0, "org.apache.commons.math3.analysis.function.Gaussian.validateParameters_180", _mut91058, _mut91059, _mut91060, _mut91061, _mut91062)) {
                throw new NotStrictlyPositiveException(param[2]);
            }
        }
    }

    /**
     * @param xMinusMean {@code x - mean}.
     * @param norm Normalization factor.
     * @param i2s2 Inverse of twice the square of the standard deviation.
     * @return the value of the Gaussian at {@code x}.
     */
    private static double value(double xMinusMean, double norm, double i2s2) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_202");
        return AOR_multiply(norm, FastMath.exp(AOR_multiply(AOR_multiply(-xMinusMean, xMinusMean, "org.apache.commons.math3.analysis.function.Gaussian.value_202", _mut91063, _mut91064, _mut91065, _mut91066), i2s2, "org.apache.commons.math3.analysis.function.Gaussian.value_202", _mut91067, _mut91068, _mut91069, _mut91070)), "org.apache.commons.math3.analysis.function.Gaussian.value_202", _mut91071, _mut91072, _mut91073, _mut91074);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_211");
        final double u = AOR_multiply(is, (AOR_minus(t.getValue(), mean, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91075, _mut91076, _mut91077, _mut91078)), "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91079, _mut91080, _mut91081, _mut91082);
        double[] f = new double[AOR_plus(t.getOrder(), 1, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91083, _mut91084, _mut91085, _mut91086)];
        // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
        final double[] p = new double[f.length];
        p[0] = 1;
        final double u2 = AOR_multiply(u, u, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91087, _mut91088, _mut91089, _mut91090);
        double coeff = AOR_multiply(norm, FastMath.exp(AOR_multiply(-0.5, u2, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91091, _mut91092, _mut91093, _mut91094)), "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91095, _mut91096, _mut91097, _mut91098);
        if (ROR_less_equals(coeff, Precision.SAFE_MIN, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91099, _mut91100, _mut91101, _mut91102, _mut91103)) {
            Arrays.fill(f, 0.0);
        } else {
            f[0] = coeff;
            for (int n = 1; ROR_less(n, f.length, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91164, _mut91165, _mut91166, _mut91167, _mut91168); ++n) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_211");
                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[n] = -p[AOR_minus(n, 1, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91104, _mut91105, _mut91106, _mut91107)];
                for (int k = n; ROR_greater_equals(k, 0, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91150, _mut91151, _mut91152, _mut91153, _mut91154); k -= 2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Gaussian.value_211");
                    v = AOR_plus(AOR_multiply(v, u2, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91108, _mut91109, _mut91110, _mut91111), p[k], "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91112, _mut91113, _mut91114, _mut91115);
                    if (ROR_greater(k, 2, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91116, _mut91117, _mut91118, _mut91119, _mut91120)) {
                        p[AOR_minus(k, 2, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91126, _mut91127, _mut91128, _mut91129)] = AOR_minus(AOR_multiply((AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91130, _mut91131, _mut91132, _mut91133)), p[AOR_minus(k, 1, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91134, _mut91135, _mut91136, _mut91137)], "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91138, _mut91139, _mut91140, _mut91141), p[AOR_minus(k, 3, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91142, _mut91143, _mut91144, _mut91145)], "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91146, _mut91147, _mut91148, _mut91149);
                    } else if (ROR_equals(k, 2, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91121, _mut91122, _mut91123, _mut91124, _mut91125)) {
                        p[0] = p[1];
                    }
                }
                if (ROR_equals((n & 0x1), 1, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91155, _mut91156, _mut91157, _mut91158, _mut91159)) {
                    v *= u;
                }
                coeff *= is;
                f[n] = AOR_multiply(coeff, v, "org.apache.commons.math3.analysis.function.Gaussian.value_211", _mut91160, _mut91161, _mut91162, _mut91163);
            }
        }
        return t.compose(f);
    }
}
