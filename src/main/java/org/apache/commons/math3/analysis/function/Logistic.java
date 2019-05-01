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
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <a href="http://en.wikipedia.org/wiki/Generalised_logistic_function">
 *  Generalised logistic</a> function.
 *
 * @since 3.0
 */
public class Logistic implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    @Conditional
    public static boolean _mut91823 = false, _mut91824 = false, _mut91825 = false, _mut91826 = false, _mut91827 = false, _mut91828 = false, _mut91829 = false, _mut91830 = false, _mut91831 = false, _mut91832 = false, _mut91833 = false, _mut91834 = false, _mut91835 = false, _mut91836 = false, _mut91837 = false, _mut91838 = false, _mut91839 = false, _mut91840 = false, _mut91841 = false, _mut91842 = false, _mut91843 = false, _mut91844 = false, _mut91845 = false, _mut91846 = false, _mut91847 = false, _mut91848 = false, _mut91849 = false, _mut91850 = false, _mut91851 = false, _mut91852 = false, _mut91853 = false, _mut91854 = false, _mut91855 = false, _mut91856 = false, _mut91857 = false, _mut91858 = false, _mut91859 = false, _mut91860 = false, _mut91861 = false, _mut91862 = false, _mut91863 = false, _mut91864 = false, _mut91865 = false, _mut91866 = false, _mut91867 = false, _mut91868 = false, _mut91869 = false, _mut91870 = false, _mut91871 = false, _mut91872 = false, _mut91873 = false, _mut91874 = false, _mut91875 = false, _mut91876 = false, _mut91877 = false, _mut91878 = false, _mut91879 = false, _mut91880 = false, _mut91881 = false, _mut91882 = false, _mut91883 = false, _mut91884 = false, _mut91885 = false, _mut91886 = false, _mut91887 = false, _mut91888 = false, _mut91889 = false, _mut91890 = false, _mut91891 = false, _mut91892 = false, _mut91893 = false, _mut91894 = false, _mut91895 = false, _mut91896 = false, _mut91897 = false, _mut91898 = false, _mut91899 = false, _mut91900 = false, _mut91901 = false, _mut91902 = false, _mut91903 = false, _mut91904 = false, _mut91905 = false, _mut91906 = false, _mut91907 = false, _mut91908 = false, _mut91909 = false, _mut91910 = false, _mut91911 = false, _mut91912 = false, _mut91913 = false, _mut91914 = false, _mut91915 = false, _mut91916 = false, _mut91917 = false, _mut91918 = false, _mut91919 = false, _mut91920 = false, _mut91921 = false, _mut91922 = false, _mut91923 = false, _mut91924 = false, _mut91925 = false, _mut91926 = false, _mut91927 = false, _mut91928 = false, _mut91929 = false, _mut91930 = false, _mut91931 = false, _mut91932 = false, _mut91933 = false, _mut91934 = false, _mut91935 = false, _mut91936 = false, _mut91937 = false, _mut91938 = false, _mut91939 = false, _mut91940 = false, _mut91941 = false, _mut91942 = false, _mut91943 = false, _mut91944 = false, _mut91945 = false;

    /**
     * Lower asymptote.
     */
    private final double a;

    /**
     * Upper asymptote.
     */
    private final double k;

    /**
     * Growth rate.
     */
    private final double b;

    /**
     * Parameter that affects near which asymptote maximum growth occurs.
     */
    private final double oneOverN;

    /**
     * Parameter that affects the position of the curve along the ordinate axis.
     */
    private final double q;

    /**
     * Abscissa of maximum growth.
     */
    private final double m;

    /**
     * @param k If {@code b > 0}, value of the function for x going towards +&infin;.
     * If {@code b < 0}, value of the function for x going towards -&infin;.
     * @param m Abscissa of maximum growth.
     * @param b Growth rate.
     * @param q Parameter that affects the position of the curve along the
     * ordinate axis.
     * @param a If {@code b > 0}, value of the function for x going towards -&infin;.
     * If {@code b < 0}, value of the function for x going towards +&infin;.
     * @param n Parameter that affects near which asymptote the maximum
     * growth occurs.
     * @throws NotStrictlyPositiveException if {@code n <= 0}.
     */
    public Logistic(double k, double m, double b, double q, double a, double n) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.Logistic_64");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.analysis.function.Logistic.Logistic_64", _mut91823, _mut91824, _mut91825, _mut91826, _mut91827)) {
            throw new NotStrictlyPositiveException(n);
        }
        this.k = k;
        this.m = m;
        this.b = b;
        this.q = q;
        this.a = a;
        oneOverN = AOR_divide(1, n, "org.apache.commons.math3.analysis.function.Logistic.Logistic_64", _mut91828, _mut91829, _mut91830, _mut91831);
    }

    /**
     * {@inheritDoc}
     */
    public double value(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.value_84");
        return value(AOR_minus(m, x, "org.apache.commons.math3.analysis.function.Logistic.value_84", _mut91832, _mut91833, _mut91834, _mut91835), k, b, q, a, oneOverN);
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
     * the {@link Logistic#Logistic(double,double,double,double,double,double)
     * logistic function}, ordered as follows:
     * <ul>
     *  <li>k</li>
     *  <li>m</li>
     *  <li>b</li>
     *  <li>q</li>
     *  <li>a</li>
     *  <li>n</li>
     * </ul>
     */
    public static class Parametric implements ParametricUnivariateFunction {

        /**
         * Computes the value of the sigmoid at {@code x}.
         *
         * @param x Value for which the function must be computed.
         * @param param Values for {@code k}, {@code m}, {@code b}, {@code q},
         * {@code a} and  {@code n}.
         * @return the value of the function.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 6.
         * @throws NotStrictlyPositiveException if {@code param[5] <= 0}.
         */
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.value_122");
            validateParameters(param);
            return Logistic.value(AOR_minus(param[1], x, "org.apache.commons.math3.analysis.function.Logistic.value_122", _mut91836, _mut91837, _mut91838, _mut91839), param[0], param[2], param[3], param[4], AOR_divide(1, param[5], "org.apache.commons.math3.analysis.function.Logistic.value_122", _mut91840, _mut91841, _mut91842, _mut91843));
        }

        /**
         * Computes the value of the gradient at {@code x}.
         * The components of the gradient vector are the partial
         * derivatives of the function with respect to each of the
         * <em>parameters</em>.
         *
         * @param x Value at which the gradient must be computed.
         * @param param Values for {@code k}, {@code m}, {@code b}, {@code q},
         * {@code a} and  {@code n}.
         * @return the gradient vector at {@code x}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 6.
         * @throws NotStrictlyPositiveException if {@code param[5] <= 0}.
         */
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.gradient_147");
            validateParameters(param);
            final double b = param[2];
            final double q = param[3];
            final double mMinusX = AOR_minus(param[1], x, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91844, _mut91845, _mut91846, _mut91847);
            final double oneOverN = AOR_divide(1, param[5], "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91848, _mut91849, _mut91850, _mut91851);
            final double exp = FastMath.exp(AOR_multiply(b, mMinusX, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91852, _mut91853, _mut91854, _mut91855));
            final double qExp = AOR_multiply(q, exp, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91856, _mut91857, _mut91858, _mut91859);
            final double qExp1 = AOR_plus(qExp, 1, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91860, _mut91861, _mut91862, _mut91863);
            final double factor1 = AOR_divide(AOR_multiply((AOR_minus(param[0], param[4], "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91864, _mut91865, _mut91866, _mut91867)), oneOverN, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91868, _mut91869, _mut91870, _mut91871), FastMath.pow(qExp1, oneOverN), "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91872, _mut91873, _mut91874, _mut91875);
            final double factor2 = AOR_divide(-factor1, qExp1, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91876, _mut91877, _mut91878, _mut91879);
            // Components of the gradient.
            final double gk = Logistic.value(mMinusX, 1, b, q, 0, oneOverN);
            final double gm = AOR_multiply(AOR_multiply(factor2, b, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91880, _mut91881, _mut91882, _mut91883), qExp, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91884, _mut91885, _mut91886, _mut91887);
            final double gb = AOR_multiply(AOR_multiply(factor2, mMinusX, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91888, _mut91889, _mut91890, _mut91891), qExp, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91892, _mut91893, _mut91894, _mut91895);
            final double gq = AOR_multiply(factor2, exp, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91896, _mut91897, _mut91898, _mut91899);
            final double ga = Logistic.value(mMinusX, 0, b, q, 1, oneOverN);
            final double gn = AOR_multiply(AOR_multiply(factor1, FastMath.log(qExp1), "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91900, _mut91901, _mut91902, _mut91903), oneOverN, "org.apache.commons.math3.analysis.function.Logistic.gradient_147", _mut91904, _mut91905, _mut91906, _mut91907);
            return new double[] { gk, gm, gb, gq, ga, gn };
        }

        /**
         * Validates parameters to ensure they are appropriate for the evaluation of
         * the {@link #value(double,double[])} and {@link #gradient(double,double[])}
         * methods.
         *
         * @param param Values for {@code k}, {@code m}, {@code b}, {@code q},
         * {@code a} and {@code n}.
         * @throws NullArgumentException if {@code param} is {@code null}.
         * @throws DimensionMismatchException if the size of {@code param} is
         * not 6.
         * @throws NotStrictlyPositiveException if {@code param[5] <= 0}.
         */
        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.validateParameters_187");
            if (param == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(param.length, 6, "org.apache.commons.math3.analysis.function.Logistic.validateParameters_187", _mut91908, _mut91909, _mut91910, _mut91911, _mut91912)) {
                throw new DimensionMismatchException(param.length, 6);
            }
            if (ROR_less_equals(param[5], 0, "org.apache.commons.math3.analysis.function.Logistic.validateParameters_187", _mut91913, _mut91914, _mut91915, _mut91916, _mut91917)) {
                throw new NotStrictlyPositiveException(param[5]);
            }
        }
    }

    /**
     * @param mMinusX {@code m - x}.
     * @param k {@code k}.
     * @param b {@code b}.
     * @param q {@code q}.
     * @param a {@code a}.
     * @param oneOverN {@code 1 / n}.
     * @return the value of the function.
     */
    private static double value(double mMinusX, double k, double b, double q, double a, double oneOverN) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.value_212");
        return AOR_plus(a, AOR_divide((AOR_minus(k, a, "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91918, _mut91919, _mut91920, _mut91921)), FastMath.pow(AOR_plus(1, AOR_multiply(q, FastMath.exp(AOR_multiply(b, mMinusX, "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91922, _mut91923, _mut91924, _mut91925)), "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91926, _mut91927, _mut91928, _mut91929), "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91930, _mut91931, _mut91932, _mut91933), oneOverN), "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91934, _mut91935, _mut91936, _mut91937), "org.apache.commons.math3.analysis.function.Logistic.value_212", _mut91938, _mut91939, _mut91940, _mut91941);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public DerivativeStructure value(final DerivativeStructure t) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.function.Logistic.value_224");
        return t.negate().add(m).multiply(b).exp().multiply(q).add(1).pow(oneOverN).reciprocal().multiply(AOR_minus(k, a, "org.apache.commons.math3.analysis.function.Logistic.value_224", _mut91942, _mut91943, _mut91944, _mut91945)).add(a);
    }
}
