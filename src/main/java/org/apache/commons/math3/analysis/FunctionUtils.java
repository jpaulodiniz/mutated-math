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
package org.apache.commons.math3.analysis;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.analysis.function.Identity;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utilities for manipulating function objects.
 *
 * @since 3.0
 */
public class FunctionUtils {

    @Conditional
    public static boolean _mut89806 = false, _mut89807 = false, _mut89808 = false, _mut89809 = false, _mut89810 = false, _mut89811 = false, _mut89812 = false, _mut89813 = false, _mut89814 = false, _mut89815 = false, _mut89816 = false, _mut89817 = false, _mut89818 = false, _mut89819 = false, _mut89820 = false, _mut89821 = false, _mut89822 = false, _mut89823 = false, _mut89824 = false, _mut89825 = false, _mut89826 = false, _mut89827 = false, _mut89828 = false, _mut89829 = false, _mut89830 = false, _mut89831 = false, _mut89832 = false, _mut89833 = false, _mut89834 = false, _mut89835 = false, _mut89836 = false, _mut89837 = false, _mut89838 = false, _mut89839 = false, _mut89840 = false, _mut89841 = false, _mut89842 = false, _mut89843 = false, _mut89844 = false, _mut89845 = false, _mut89846 = false, _mut89847 = false, _mut89848 = false, _mut89849 = false, _mut89850 = false, _mut89851 = false, _mut89852 = false, _mut89853 = false, _mut89854 = false, _mut89855 = false, _mut89856 = false, _mut89857 = false, _mut89858 = false, _mut89859 = false, _mut89860 = false, _mut89861 = false, _mut89862 = false, _mut89863 = false, _mut89864 = false, _mut89865 = false, _mut89866 = false, _mut89867 = false, _mut89868 = false, _mut89869 = false, _mut89870 = false, _mut89871 = false, _mut89872 = false, _mut89873 = false, _mut89874 = false, _mut89875 = false, _mut89876 = false, _mut89877 = false, _mut89878 = false, _mut89879 = false, _mut89880 = false, _mut89881 = false, _mut89882 = false, _mut89883 = false, _mut89884 = false, _mut89885 = false, _mut89886 = false, _mut89887 = false, _mut89888 = false, _mut89889 = false, _mut89890 = false, _mut89891 = false, _mut89892 = false, _mut89893 = false, _mut89894 = false, _mut89895 = false, _mut89896 = false, _mut89897 = false, _mut89898 = false, _mut89899 = false, _mut89900 = false, _mut89901 = false, _mut89902 = false, _mut89903 = false, _mut89904 = false, _mut89905 = false, _mut89906 = false, _mut89907 = false, _mut89908 = false, _mut89909 = false, _mut89910 = false, _mut89911 = false, _mut89912 = false, _mut89913 = false, _mut89914 = false, _mut89915 = false, _mut89916 = false, _mut89917 = false, _mut89918 = false, _mut89919 = false, _mut89920 = false, _mut89921 = false, _mut89922 = false, _mut89923 = false, _mut89924 = false, _mut89925 = false, _mut89926 = false, _mut89927 = false, _mut89928 = false, _mut89929 = false, _mut89930 = false, _mut89931 = false, _mut89932 = false, _mut89933 = false, _mut89934 = false, _mut89935 = false, _mut89936 = false, _mut89937 = false, _mut89938 = false, _mut89939 = false, _mut89940 = false, _mut89941 = false, _mut89942 = false, _mut89943 = false, _mut89944 = false, _mut89945 = false, _mut89946 = false, _mut89947 = false, _mut89948 = false, _mut89949 = false, _mut89950 = false, _mut89951 = false, _mut89952 = false, _mut89953 = false, _mut89954 = false, _mut89955 = false, _mut89956 = false, _mut89957 = false, _mut89958 = false, _mut89959 = false, _mut89960 = false, _mut89961 = false, _mut89962 = false, _mut89963 = false, _mut89964 = false, _mut89965 = false, _mut89966 = false, _mut89967 = false, _mut89968 = false, _mut89969 = false, _mut89970 = false, _mut89971 = false, _mut89972 = false, _mut89973 = false, _mut89974 = false, _mut89975 = false, _mut89976 = false, _mut89977 = false, _mut89978 = false, _mut89979 = false, _mut89980 = false, _mut89981 = false, _mut89982 = false, _mut89983 = false, _mut89984 = false, _mut89985 = false, _mut89986 = false, _mut89987 = false, _mut89988 = false, _mut89989 = false, _mut89990 = false, _mut89991 = false, _mut89992 = false, _mut89993 = false, _mut89994 = false, _mut89995 = false, _mut89996 = false, _mut89997 = false, _mut89998 = false, _mut89999 = false, _mut90000 = false, _mut90001 = false, _mut90002 = false, _mut90003 = false, _mut90004 = false, _mut90005 = false, _mut90006 = false, _mut90007 = false, _mut90008 = false, _mut90009 = false, _mut90010 = false, _mut90011 = false, _mut90012 = false, _mut90013 = false, _mut90014 = false, _mut90015 = false, _mut90016 = false, _mut90017 = false, _mut90018 = false, _mut90019 = false, _mut90020 = false, _mut90021 = false, _mut90022 = false, _mut90023 = false, _mut90024 = false, _mut90025 = false, _mut90026 = false, _mut90027 = false, _mut90028 = false, _mut90029 = false, _mut90030 = false, _mut90031 = false, _mut90032 = false, _mut90033 = false, _mut90034 = false, _mut90035 = false, _mut90036 = false, _mut90037 = false, _mut90038 = false, _mut90039 = false, _mut90040 = false, _mut90041 = false, _mut90042 = false, _mut90043 = false, _mut90044 = false, _mut90045 = false, _mut90046 = false, _mut90047 = false, _mut90048 = false, _mut90049 = false, _mut90050 = false, _mut90051 = false, _mut90052 = false, _mut90053 = false, _mut90054 = false, _mut90055 = false, _mut90056 = false, _mut90057 = false, _mut90058 = false, _mut90059 = false, _mut90060 = false, _mut90061 = false, _mut90062 = false, _mut90063 = false, _mut90064 = false, _mut90065 = false, _mut90066 = false, _mut90067 = false, _mut90068 = false, _mut90069 = false, _mut90070 = false, _mut90071 = false, _mut90072 = false, _mut90073 = false, _mut90074 = false, _mut90075 = false, _mut90076 = false, _mut90077 = false;

    /**
     * Class only contains static methods.
     */
    private FunctionUtils() {
    }

    /**
     * Composes functions.
     * <p>
     * The functions in the argument list are composed sequentially, in the
     * given order.  For example, compose(f1,f2,f3) acts like f1(f2(f3(x))).</p>
     *
     * @param f List of functions.
     * @return the composite function.
     */
    public static UnivariateFunction compose(final UnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_53");
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_53");
                double r = x;
                for (int i = f.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.FunctionUtils.value_53", _mut89806, _mut89807, _mut89808, _mut89809, _mut89810); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_53");
                    r = f[i].value(r);
                }
                return r;
            }
        };
    }

    /**
     * Composes functions.
     * <p>
     * The functions in the argument list are composed sequentially, in the
     * given order.  For example, compose(f1,f2,f3) acts like f1(f2(f3(x))).</p>
     *
     * @param f List of functions.
     * @return the composite function.
     * @since 3.1
     */
    public static UnivariateDifferentiableFunction compose(final UnivariateDifferentiableFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_86");
        return new UnivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double t) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_77");
                double r = t;
                for (int i = f.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.FunctionUtils.value_77", _mut89811, _mut89812, _mut89813, _mut89814, _mut89815); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_77");
                    r = f[i].value(r);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             */
            public DerivativeStructure value(final DerivativeStructure t) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_86");
                DerivativeStructure r = t;
                for (int i = f.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.FunctionUtils.value_86", _mut89816, _mut89817, _mut89818, _mut89819, _mut89820); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_86");
                    r = f[i].value(r);
                }
                return r;
            }
        };
    }

    /**
     * Composes functions.
     * <p>
     * The functions in the argument list are composed sequentially, in the
     * given order.  For example, compose(f1,f2,f3) acts like f1(f2(f3(x))).</p>
     *
     * @param f List of functions.
     * @return the composite function.
     * @deprecated as of 3.1 replaced by {@link #compose(UnivariateDifferentiableFunction...)}
     */
    @Deprecated
    public static DifferentiableUnivariateFunction compose(final DifferentiableUnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_123");
        return new DifferentiableUnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_111");
                double r = x;
                for (int i = f.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.FunctionUtils.value_111", _mut89821, _mut89822, _mut89823, _mut89824, _mut89825); i--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_111");
                    r = f[i].value(r);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             */
            public UnivariateFunction derivative() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_123");
                return new UnivariateFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double value(double x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_123");
                        double p = 1;
                        double r = x;
                        for (int i = f.length - 1; ROR_greater_equals(i, 0, "org.apache.commons.math3.analysis.FunctionUtils.value_123", _mut89826, _mut89827, _mut89828, _mut89829, _mut89830); i--) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_123");
                            p *= f[i].derivative().value(r);
                            r = f[i].value(r);
                        }
                        return p;
                    }
                };
            }
        };
    }

    /**
     * Adds functions.
     *
     * @param f List of functions.
     * @return a function that computes the sum of the functions.
     */
    public static UnivariateFunction add(final UnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_146");
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_146");
                double r = f[0].value(x);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_146", _mut89831, _mut89832, _mut89833, _mut89834, _mut89835); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_146");
                    r += f[i].value(x);
                }
                return r;
            }
        };
    }

    /**
     * Adds functions.
     *
     * @param f List of functions.
     * @return a function that computes the sum of the functions.
     * @since 3.1
     */
    public static UnivariateDifferentiableFunction add(final UnivariateDifferentiableFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_178");
        return new UnivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double t) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_167");
                double r = f[0].value(t);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_167", _mut89836, _mut89837, _mut89838, _mut89839, _mut89840); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_167");
                    r += f[i].value(t);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             * @throws DimensionMismatchException if functions are not consistent with each other
             */
            public DerivativeStructure value(final DerivativeStructure t) throws DimensionMismatchException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_178");
                DerivativeStructure r = f[0].value(t);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_178", _mut89841, _mut89842, _mut89843, _mut89844, _mut89845); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_178");
                    r = r.add(f[i].value(t));
                }
                return r;
            }
        };
    }

    /**
     * Adds functions.
     *
     * @param f List of functions.
     * @return a function that computes the sum of the functions.
     * @deprecated as of 3.1 replaced by {@link #add(UnivariateDifferentiableFunction...)}
     */
    @Deprecated
    public static DifferentiableUnivariateFunction add(final DifferentiableUnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_213");
        return new DifferentiableUnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_201");
                double r = f[0].value(x);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_201", _mut89846, _mut89847, _mut89848, _mut89849, _mut89850); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_201");
                    r += f[i].value(x);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             */
            public UnivariateFunction derivative() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_213");
                return new UnivariateFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double value(double x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_213");
                        double r = f[0].derivative().value(x);
                        for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_213", _mut89851, _mut89852, _mut89853, _mut89854, _mut89855); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_213");
                            r += f[i].derivative().value(x);
                        }
                        return r;
                    }
                };
            }
        };
    }

    /**
     * Multiplies functions.
     *
     * @param f List of functions.
     * @return a function that computes the product of the functions.
     */
    public static UnivariateFunction multiply(final UnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_234");
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_234");
                double r = f[0].value(x);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_234", _mut89856, _mut89857, _mut89858, _mut89859, _mut89860); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_234");
                    r *= f[i].value(x);
                }
                return r;
            }
        };
    }

    /**
     * Multiplies functions.
     *
     * @param f List of functions.
     * @return a function that computes the product of the functions.
     * @since 3.1
     */
    public static UnivariateDifferentiableFunction multiply(final UnivariateDifferentiableFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_264");
        return new UnivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double t) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_255");
                double r = f[0].value(t);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_255", _mut89861, _mut89862, _mut89863, _mut89864, _mut89865); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_255");
                    r *= f[i].value(t);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             */
            public DerivativeStructure value(final DerivativeStructure t) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_264");
                DerivativeStructure r = f[0].value(t);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_264", _mut89866, _mut89867, _mut89868, _mut89869, _mut89870); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_264");
                    r = r.multiply(f[i].value(t));
                }
                return r;
            }
        };
    }

    /**
     * Multiplies functions.
     *
     * @param f List of functions.
     * @return a function that computes the product of the functions.
     * @deprecated as of 3.1 replaced by {@link #multiply(UnivariateDifferentiableFunction...)}
     */
    @Deprecated
    public static DifferentiableUnivariateFunction multiply(final DifferentiableUnivariateFunction... f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_298");
        return new DifferentiableUnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_286");
                double r = f[0].value(x);
                for (int i = 1; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_286", _mut89871, _mut89872, _mut89873, _mut89874, _mut89875); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_286");
                    r *= f[i].value(x);
                }
                return r;
            }

            /**
             * {@inheritDoc}
             */
            public UnivariateFunction derivative() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_298");
                return new UnivariateFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double value(double x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_298");
                        double sum = 0;
                        for (int i = 0; ROR_less(i, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_298", _mut89886, _mut89887, _mut89888, _mut89889, _mut89890); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_298");
                            double prod = f[i].derivative().value(x);
                            for (int j = 0; ROR_less(j, f.length, "org.apache.commons.math3.analysis.FunctionUtils.value_298", _mut89881, _mut89882, _mut89883, _mut89884, _mut89885); j++) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_298");
                                if (ROR_not_equals(i, j, "org.apache.commons.math3.analysis.FunctionUtils.value_298", _mut89876, _mut89877, _mut89878, _mut89879, _mut89880)) {
                                    prod *= f[j].value(x);
                                }
                            }
                            sum += prod;
                        }
                        return sum;
                    }
                };
            }
        };
    }

    /**
     * Returns the univariate function
     * {@code h(x) = combiner(f(x), g(x)).}
     *
     * @param combiner Combiner function.
     * @param f Function.
     * @param g Function.
     * @return the composite function.
     */
    public static UnivariateFunction combine(final BivariateFunction combiner, final UnivariateFunction f, final UnivariateFunction g) {
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                return combiner.value(f.value(x), g.value(x));
            }
        };
    }

    /**
     * Returns a MultivariateFunction h(x[]) defined by <pre> <code>
     * h(x[]) = combiner(...combiner(combiner(initialValue,f(x[0])),f(x[1]))...),f(x[x.length-1]))
     * </code></pre>
     *
     * @param combiner Combiner function.
     * @param f Function.
     * @param initialValue Initial value.
     * @return a collector function.
     */
    public static MultivariateFunction collector(final BivariateFunction combiner, final UnivariateFunction f, final double initialValue) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_351");
        return new MultivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double[] point) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_351");
                double result = combiner.value(initialValue, f.value(point[0]));
                for (int i = 1; ROR_less(i, point.length, "org.apache.commons.math3.analysis.FunctionUtils.value_351", _mut89891, _mut89892, _mut89893, _mut89894, _mut89895); i++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_351");
                    result = combiner.value(result, f.value(point[i]));
                }
                return result;
            }
        };
    }

    /**
     * Returns a MultivariateFunction h(x[]) defined by <pre> <code>
     * h(x[]) = combiner(...combiner(combiner(initialValue,x[0]),x[1])...),x[x.length-1])
     * </code></pre>
     *
     * @param combiner Combiner function.
     * @param initialValue Initial value.
     * @return a collector function.
     */
    public static MultivariateFunction collector(final BivariateFunction combiner, final double initialValue) {
        return collector(combiner, new Identity(), initialValue);
    }

    /**
     * Creates a unary function by fixing the first argument of a binary function.
     *
     * @param f Binary function.
     * @param fixed value to which the first argument of {@code f} is set.
     * @return the unary function h(x) = f(fixed, x)
     */
    public static UnivariateFunction fix1stArgument(final BivariateFunction f, final double fixed) {
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                return f.value(fixed, x);
            }
        };
    }

    /**
     * Creates a unary function by fixing the second argument of a binary function.
     *
     * @param f Binary function.
     * @param fixed value to which the second argument of {@code f} is set.
     * @return the unary function h(x) = f(x, fixed)
     */
    public static UnivariateFunction fix2ndArgument(final BivariateFunction f, final double fixed) {
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(double x) {
                return f.value(x, fixed);
            }
        };
    }

    /**
     * Samples the specified univariate real function on the specified interval.
     * <p>
     * The interval is divided equally into {@code n} sections and sample points
     * are taken from {@code min} to {@code max - (max - min) / n}; therefore
     * {@code f} is not sampled at the upper bound {@code max}.</p>
     *
     * @param f Function to be sampled
     * @param min Lower bound of the interval (included).
     * @param max Upper bound of the interval (excluded).
     * @param n Number of sample points.
     * @return the array of samples.
     * @throws NumberIsTooLargeException if the lower bound {@code min} is
     * greater than, or equal to the upper bound {@code max}.
     * @throws NotStrictlyPositiveException if the number of sample points
     * {@code n} is negative.
     */
    public static double[] sample(UnivariateFunction f, double min, double max, int n) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.sample_425");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89896, _mut89897, _mut89898, _mut89899, _mut89900)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, Integer.valueOf(n));
        }
        if (ROR_greater_equals(min, max, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89901, _mut89902, _mut89903, _mut89904, _mut89905)) {
            throw new NumberIsTooLargeException(min, max, false);
        }
        final double[] s = new double[n];
        final double h = AOR_divide((AOR_minus(max, min, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89906, _mut89907, _mut89908, _mut89909)), n, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89910, _mut89911, _mut89912, _mut89913);
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89922, _mut89923, _mut89924, _mut89925, _mut89926); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.sample_425");
            s[i] = f.value(AOR_plus(min, AOR_multiply(i, h, "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89914, _mut89915, _mut89916, _mut89917), "org.apache.commons.math3.analysis.FunctionUtils.sample_425", _mut89918, _mut89919, _mut89920, _mut89921));
        }
        return s;
    }

    /**
     * Convert a {@link UnivariateDifferentiableFunction} into a {@link DifferentiableUnivariateFunction}.
     *
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableUnivariateFunction} interface itself is deprecated
     */
    @Deprecated
    public static DifferentiableUnivariateFunction toDifferentiableUnivariateFunction(final UnivariateDifferentiableFunction f) {
        return new DifferentiableUnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public UnivariateFunction derivative() {
                return new UnivariateFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double value(final double x) {
                        return f.value(new DerivativeStructure(1, 1, 0, x)).getPartialDerivative(1);
                    }
                };
            }
        };
    }

    /**
     * Convert a {@link DifferentiableUnivariateFunction} into a {@link UnivariateDifferentiableFunction}.
     * <p>
     * Note that the converted function is able to handle {@link DerivativeStructure} up to order one.
     * If the function is called with higher order, a {@link NumberIsTooLargeException} is thrown.
     * </p>
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableUnivariateFunction} interface itself is deprecated
     */
    @Deprecated
    public static UnivariateDifferentiableFunction toUnivariateDifferential(final DifferentiableUnivariateFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_498");
        return new UnivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             * @exception NumberIsTooLargeException if derivation order is greater than 1
             */
            public DerivativeStructure value(final DerivativeStructure t) throws NumberIsTooLargeException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_498");
                switch(t.getOrder()) {
                    case 0:
                        return new DerivativeStructure(t.getFreeParameters(), 0, f.value(t.getValue()));
                    case 1:
                        {
                            final int parameters = t.getFreeParameters();
                            final double[] derivatives = new double[AOR_plus(parameters, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_498", _mut89927, _mut89928, _mut89929, _mut89930)];
                            derivatives[0] = f.value(t.getValue());
                            final double fPrime = f.derivative().value(t.getValue());
                            int[] orders = new int[parameters];
                            for (int i = 0; ROR_less(i, parameters, "org.apache.commons.math3.analysis.FunctionUtils.value_498", _mut89939, _mut89940, _mut89941, _mut89942, _mut89943); ++i) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_498");
                                orders[i] = 1;
                                derivatives[AOR_plus(i, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_498", _mut89931, _mut89932, _mut89933, _mut89934)] = AOR_multiply(fPrime, t.getPartialDerivative(orders), "org.apache.commons.math3.analysis.FunctionUtils.value_498", _mut89935, _mut89936, _mut89937, _mut89938);
                                orders[i] = 0;
                            }
                            return new DerivativeStructure(parameters, 1, derivatives);
                        }
                    default:
                        throw new NumberIsTooLargeException(t.getOrder(), 1, true);
                }
            }
        };
    }

    /**
     * Convert a {@link MultivariateDifferentiableFunction} into a {@link DifferentiableMultivariateFunction}.
     *
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableMultivariateFunction} interface itself is deprecated
     */
    @Deprecated
    public static DifferentiableMultivariateFunction toDifferentiableMultivariateFunction(final MultivariateDifferentiableFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_571");
        return new DifferentiableMultivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double[] x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public MultivariateFunction partialDerivative(final int k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_545");
                return new MultivariateFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double value(final double[] x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_545");
                        final int n = x.length;
                        // delegate computation to underlying function
                        final DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_545", _mut89949, _mut89950, _mut89951, _mut89952, _mut89953); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_545");
                            if (ROR_equals(i, k, "org.apache.commons.math3.analysis.FunctionUtils.value_545", _mut89944, _mut89945, _mut89946, _mut89947, _mut89948)) {
                                dsX[i] = new DerivativeStructure(1, 1, 0, x[i]);
                            } else {
                                dsX[i] = new DerivativeStructure(1, 1, x[i]);
                            }
                        }
                        final DerivativeStructure y = f.value(dsX);
                        // extract partial derivative
                        return y.getPartialDerivative(1);
                    }
                };
            }

            /**
             * {@inheritDoc}
             */
            public MultivariateVectorFunction gradient() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_571");
                return new MultivariateVectorFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double[] value(final double[] x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_571");
                        final int n = x.length;
                        // delegate computation to underlying function
                        final DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_571", _mut89954, _mut89955, _mut89956, _mut89957, _mut89958); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_571");
                            dsX[i] = new DerivativeStructure(n, 1, i, x[i]);
                        }
                        final DerivativeStructure y = f.value(dsX);
                        // extract gradient
                        final double[] gradient = new double[n];
                        final int[] orders = new int[n];
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_571", _mut89959, _mut89960, _mut89961, _mut89962, _mut89963); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_571");
                            orders[i] = 1;
                            gradient[i] = y.getPartialDerivative(orders);
                            orders[i] = 0;
                        }
                        return gradient;
                    }
                };
            }
        };
    }

    /**
     * Convert a {@link DifferentiableMultivariateFunction} into a {@link MultivariateDifferentiableFunction}.
     * <p>
     * Note that the converted function is able to handle {@link DerivativeStructure} elements
     * that all have the same number of free parameters and order, and with order at most 1.
     * If the function is called with inconsistent numbers of free parameters or higher order, a
     * {@link DimensionMismatchException} or a {@link NumberIsTooLargeException} will be thrown.
     * </p>
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableMultivariateFunction} interface itself is deprecated
     */
    @Deprecated
    public static MultivariateDifferentiableFunction toMultivariateDifferentiableFunction(final DifferentiableMultivariateFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
        return new MultivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double[] x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             * @exception NumberIsTooLargeException if derivation order is higher than 1
             * @exception DimensionMismatchException if numbers of free parameters are inconsistent
             */
            public DerivativeStructure value(final DerivativeStructure[] t) throws DimensionMismatchException, NumberIsTooLargeException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
                // check parameters and orders limits
                final int parameters = t[0].getFreeParameters();
                final int order = t[0].getOrder();
                final int n = t.length;
                if (ROR_greater(order, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89964, _mut89965, _mut89966, _mut89967, _mut89968)) {
                    throw new NumberIsTooLargeException(order, 1, true);
                }
                // check all elements in the array are consistent
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89979, _mut89980, _mut89981, _mut89982, _mut89983); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
                    if (ROR_not_equals(t[i].getFreeParameters(), parameters, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89969, _mut89970, _mut89971, _mut89972, _mut89973)) {
                        throw new DimensionMismatchException(t[i].getFreeParameters(), parameters);
                    }
                    if (ROR_not_equals(t[i].getOrder(), order, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89974, _mut89975, _mut89976, _mut89977, _mut89978)) {
                        throw new DimensionMismatchException(t[i].getOrder(), order);
                    }
                }
                // delegate computation to underlying function
                final double[] point = new double[n];
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89984, _mut89985, _mut89986, _mut89987, _mut89988); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
                    point[i] = t[i].getValue();
                }
                final double value = f.value(point);
                final double[] gradient = f.gradient().value(point);
                // merge value and gradient into one DerivativeStructure
                final double[] derivatives = new double[AOR_plus(parameters, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89989, _mut89990, _mut89991, _mut89992)];
                derivatives[0] = value;
                final int[] orders = new int[parameters];
                for (int i = 0; ROR_less(i, parameters, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut90006, _mut90007, _mut90008, _mut90009, _mut90010); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
                    orders[i] = 1;
                    for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut90001, _mut90002, _mut90003, _mut90004, _mut90005); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_626");
                        derivatives[AOR_plus(i, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89993, _mut89994, _mut89995, _mut89996)] += AOR_multiply(gradient[j], t[j].getPartialDerivative(orders), "org.apache.commons.math3.analysis.FunctionUtils.value_626", _mut89997, _mut89998, _mut89999, _mut90000);
                    }
                    orders[i] = 0;
                }
                return new DerivativeStructure(parameters, order, derivatives);
            }
        };
    }

    /**
     * Convert a {@link MultivariateDifferentiableVectorFunction} into a {@link DifferentiableMultivariateVectorFunction}.
     *
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableMultivariateVectorFunction} interface itself is deprecated
     */
    @Deprecated
    public static DifferentiableMultivariateVectorFunction toDifferentiableMultivariateVectorFunction(final MultivariateDifferentiableVectorFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
        return new DifferentiableMultivariateVectorFunction() {

            /**
             * {@inheritDoc}
             */
            public double[] value(final double[] x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public MultivariateMatrixFunction jacobian() {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
                return new MultivariateMatrixFunction() {

                    /**
                     * {@inheritDoc}
                     */
                    public double[][] value(final double[] x) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
                        final int n = x.length;
                        // delegate computation to underlying function
                        final DerivativeStructure[] dsX = new DerivativeStructure[n];
                        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_696", _mut90011, _mut90012, _mut90013, _mut90014, _mut90015); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
                            dsX[i] = new DerivativeStructure(n, 1, i, x[i]);
                        }
                        final DerivativeStructure[] y = f.value(dsX);
                        // extract Jacobian
                        final double[][] jacobian = new double[y.length][n];
                        final int[] orders = new int[n];
                        for (int i = 0; ROR_less(i, y.length, "org.apache.commons.math3.analysis.FunctionUtils.value_696", _mut90021, _mut90022, _mut90023, _mut90024, _mut90025); ++i) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
                            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.analysis.FunctionUtils.value_696", _mut90016, _mut90017, _mut90018, _mut90019, _mut90020); ++j) {
                                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_696");
                                orders[j] = 1;
                                jacobian[i][j] = y[i].getPartialDerivative(orders);
                                orders[j] = 0;
                            }
                        }
                        return jacobian;
                    }
                };
            }
        };
    }

    /**
     * Convert a {@link DifferentiableMultivariateVectorFunction} into a {@link MultivariateDifferentiableVectorFunction}.
     * <p>
     * Note that the converted function is able to handle {@link DerivativeStructure} elements
     * that all have the same number of free parameters and order, and with order at most 1.
     * If the function is called with inconsistent numbers of free parameters or higher order, a
     * {@link DimensionMismatchException} or a {@link NumberIsTooLargeException} will be thrown.
     * </p>
     * @param f function to convert
     * @return converted function
     * @deprecated this conversion method is temporary in version 3.1, as the {@link
     * DifferentiableMultivariateFunction} interface itself is deprecated
     */
    @Deprecated
    public static MultivariateDifferentiableVectorFunction toMultivariateDifferentiableVectorFunction(final DifferentiableMultivariateVectorFunction f) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
        return new MultivariateDifferentiableVectorFunction() {

            /**
             * {@inheritDoc}
             */
            public double[] value(final double[] x) {
                return f.value(x);
            }

            /**
             * {@inheritDoc}
             * @exception NumberIsTooLargeException if derivation order is higher than 1
             * @exception DimensionMismatchException if numbers of free parameters are inconsistent
             */
            public DerivativeStructure[] value(final DerivativeStructure[] t) throws DimensionMismatchException, NumberIsTooLargeException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                // check parameters and orders limits
                final int parameters = t[0].getFreeParameters();
                final int order = t[0].getOrder();
                final int n = t.length;
                if (ROR_greater(order, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90026, _mut90027, _mut90028, _mut90029, _mut90030)) {
                    throw new NumberIsTooLargeException(order, 1, true);
                }
                // check all elements in the array are consistent
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90041, _mut90042, _mut90043, _mut90044, _mut90045); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                    if (ROR_not_equals(t[i].getFreeParameters(), parameters, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90031, _mut90032, _mut90033, _mut90034, _mut90035)) {
                        throw new DimensionMismatchException(t[i].getFreeParameters(), parameters);
                    }
                    if (ROR_not_equals(t[i].getOrder(), order, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90036, _mut90037, _mut90038, _mut90039, _mut90040)) {
                        throw new DimensionMismatchException(t[i].getOrder(), order);
                    }
                }
                // delegate computation to underlying function
                final double[] point = new double[n];
                for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90046, _mut90047, _mut90048, _mut90049, _mut90050); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                    point[i] = t[i].getValue();
                }
                final double[] value = f.value(point);
                final double[][] jacobian = f.jacobian().value(point);
                // merge value and Jacobian into a DerivativeStructure array
                final DerivativeStructure[] merged = new DerivativeStructure[value.length];
                for (int k = 0; ROR_less(k, merged.length, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90073, _mut90074, _mut90075, _mut90076, _mut90077); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                    final double[] derivatives = new double[AOR_plus(parameters, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90051, _mut90052, _mut90053, _mut90054)];
                    derivatives[0] = value[k];
                    final int[] orders = new int[parameters];
                    for (int i = 0; ROR_less(i, parameters, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90068, _mut90069, _mut90070, _mut90071, _mut90072); ++i) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                        orders[i] = 1;
                        for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90063, _mut90064, _mut90065, _mut90066, _mut90067); ++j) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.FunctionUtils.value_753");
                            derivatives[AOR_plus(i, 1, "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90055, _mut90056, _mut90057, _mut90058)] += AOR_multiply(jacobian[k][j], t[j].getPartialDerivative(orders), "org.apache.commons.math3.analysis.FunctionUtils.value_753", _mut90059, _mut90060, _mut90061, _mut90062);
                        }
                        orders[i] = 0;
                    }
                    merged[k] = new DerivativeStructure(parameters, order, derivatives);
                }
                return merged;
            }
        };
    }
}
