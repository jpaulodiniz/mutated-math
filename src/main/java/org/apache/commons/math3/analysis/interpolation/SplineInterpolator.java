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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Computes a natural (also known as "free", "unclamped") cubic spline interpolation for the data set.
 * <p>
 * The {@link #interpolate(double[], double[])} method returns a {@link PolynomialSplineFunction}
 * consisting of n cubic polynomials, defined over the subintervals determined by the x values,
 * {@code x[0] < x[i] ... < x[n].}  The x values are referred to as "knot points."
 * <p>
 * The value of the PolynomialSplineFunction at a point x that is greater than or equal to the smallest
 * knot point and strictly less than the largest knot point is computed by finding the subinterval to which
 * x belongs and computing the value of the corresponding polynomial at <code>x - x[i] </code> where
 * <code>i</code> is the index of the subinterval.  See {@link PolynomialSplineFunction} for more details.
 * </p>
 * <p>
 * The interpolating polynomials satisfy: <ol>
 * <li>The value of the PolynomialSplineFunction at each of the input x values equals the
 *  corresponding y value.</li>
 * <li>Adjacent polynomials are equal through two derivatives at the knot points (i.e., adjacent polynomials
 *  "match up" at the knot points, as do their first and second derivatives).</li>
 * </ol>
 * <p>
 * The cubic spline interpolation algorithm implemented is as described in R.L. Burden, J.D. Faires,
 * <u>Numerical Analysis</u>, 4th Ed., 1989, PWS-Kent, ISBN 0-53491-585-X, pp 126-131.
 * </p>
 */
public class SplineInterpolator implements UnivariateInterpolator {

    @Conditional
    public static boolean _mut95842 = false, _mut95843 = false, _mut95844 = false, _mut95845 = false, _mut95846 = false, _mut95847 = false, _mut95848 = false, _mut95849 = false, _mut95850 = false, _mut95851 = false, _mut95852 = false, _mut95853 = false, _mut95854 = false, _mut95855 = false, _mut95856 = false, _mut95857 = false, _mut95858 = false, _mut95859 = false, _mut95860 = false, _mut95861 = false, _mut95862 = false, _mut95863 = false, _mut95864 = false, _mut95865 = false, _mut95866 = false, _mut95867 = false, _mut95868 = false, _mut95869 = false, _mut95870 = false, _mut95871 = false, _mut95872 = false, _mut95873 = false, _mut95874 = false, _mut95875 = false, _mut95876 = false, _mut95877 = false, _mut95878 = false, _mut95879 = false, _mut95880 = false, _mut95881 = false, _mut95882 = false, _mut95883 = false, _mut95884 = false, _mut95885 = false, _mut95886 = false, _mut95887 = false, _mut95888 = false, _mut95889 = false, _mut95890 = false, _mut95891 = false, _mut95892 = false, _mut95893 = false, _mut95894 = false, _mut95895 = false, _mut95896 = false, _mut95897 = false, _mut95898 = false, _mut95899 = false, _mut95900 = false, _mut95901 = false, _mut95902 = false, _mut95903 = false, _mut95904 = false, _mut95905 = false, _mut95906 = false, _mut95907 = false, _mut95908 = false, _mut95909 = false, _mut95910 = false, _mut95911 = false, _mut95912 = false, _mut95913 = false, _mut95914 = false, _mut95915 = false, _mut95916 = false, _mut95917 = false, _mut95918 = false, _mut95919 = false, _mut95920 = false, _mut95921 = false, _mut95922 = false, _mut95923 = false, _mut95924 = false, _mut95925 = false, _mut95926 = false, _mut95927 = false, _mut95928 = false, _mut95929 = false, _mut95930 = false, _mut95931 = false, _mut95932 = false, _mut95933 = false, _mut95934 = false, _mut95935 = false, _mut95936 = false, _mut95937 = false, _mut95938 = false, _mut95939 = false, _mut95940 = false, _mut95941 = false, _mut95942 = false, _mut95943 = false, _mut95944 = false, _mut95945 = false, _mut95946 = false, _mut95947 = false, _mut95948 = false, _mut95949 = false, _mut95950 = false, _mut95951 = false, _mut95952 = false, _mut95953 = false, _mut95954 = false, _mut95955 = false, _mut95956 = false, _mut95957 = false, _mut95958 = false, _mut95959 = false, _mut95960 = false, _mut95961 = false, _mut95962 = false, _mut95963 = false, _mut95964 = false, _mut95965 = false, _mut95966 = false, _mut95967 = false, _mut95968 = false, _mut95969 = false, _mut95970 = false, _mut95971 = false, _mut95972 = false, _mut95973 = false, _mut95974 = false, _mut95975 = false, _mut95976 = false, _mut95977 = false, _mut95978 = false, _mut95979 = false, _mut95980 = false, _mut95981 = false, _mut95982 = false, _mut95983 = false, _mut95984 = false, _mut95985 = false, _mut95986 = false, _mut95987 = false, _mut95988 = false, _mut95989 = false, _mut95990 = false, _mut95991 = false, _mut95992 = false, _mut95993 = false, _mut95994 = false, _mut95995 = false, _mut95996 = false, _mut95997 = false, _mut95998 = false, _mut95999 = false, _mut96000 = false, _mut96001 = false, _mut96002 = false, _mut96003 = false, _mut96004 = false, _mut96005 = false, _mut96006 = false, _mut96007 = false, _mut96008 = false, _mut96009 = false, _mut96010 = false, _mut96011 = false, _mut96012 = false, _mut96013 = false, _mut96014 = false, _mut96015 = false, _mut96016 = false, _mut96017 = false, _mut96018 = false, _mut96019 = false, _mut96020 = false, _mut96021 = false, _mut96022 = false, _mut96023 = false, _mut96024 = false, _mut96025 = false, _mut96026 = false, _mut96027 = false, _mut96028 = false, _mut96029 = false, _mut96030 = false, _mut96031 = false, _mut96032 = false, _mut96033 = false, _mut96034 = false, _mut96035 = false, _mut96036 = false, _mut96037 = false, _mut96038 = false, _mut96039 = false, _mut96040 = false, _mut96041 = false, _mut96042 = false, _mut96043 = false, _mut96044 = false, _mut96045 = false, _mut96046 = false, _mut96047 = false, _mut96048 = false, _mut96049 = false, _mut96050 = false, _mut96051 = false, _mut96052 = false, _mut96053 = false, _mut96054 = false, _mut96055 = false, _mut96056 = false, _mut96057 = false, _mut96058 = false, _mut96059 = false, _mut96060 = false, _mut96061 = false, _mut96062 = false, _mut96063 = false, _mut96064 = false, _mut96065 = false, _mut96066 = false, _mut96067 = false, _mut96068 = false, _mut96069 = false, _mut96070 = false, _mut96071 = false;

    /**
     * Computes an interpolating function for the data set.
     * @param x the arguments for the interpolation points
     * @param y the values for the interpolation points
     * @return a function which interpolates the data set
     * @throws DimensionMismatchException if {@code x} and {@code y}
     * have different sizes.
     * @throws NonMonotonicSequenceException if {@code x} is not sorted in
     * strict increasing order.
     * @throws NumberIsTooSmallException if the size of {@code x} is smaller
     * than 3.
     */
    public PolynomialSplineFunction interpolate(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65");
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95842, _mut95843, _mut95844, _mut95845, _mut95846)) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (ROR_less(x.length, 3, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95847, _mut95848, _mut95849, _mut95850, _mut95851)) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, x.length, 3, true);
        }
        // Number of intervals.  The number of data points is n + 1.
        final int n = AOR_minus(x.length, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95852, _mut95853, _mut95854, _mut95855);
        MathArrays.checkOrder(x);
        // Differences between knot points
        final double[] h = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95864, _mut95865, _mut95866, _mut95867, _mut95868); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65");
            h[i] = AOR_minus(x[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95856, _mut95857, _mut95858, _mut95859)], x[i], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95860, _mut95861, _mut95862, _mut95863);
        }
        final double[] mu = new double[n];
        final double[] z = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95869, _mut95870, _mut95871, _mut95872)];
        mu[0] = 0d;
        z[0] = 0d;
        double g = 0;
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95989, _mut95990, _mut95991, _mut95992, _mut95993); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65");
            g = AOR_minus(AOR_multiply(2d, (AOR_minus(x[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95873, _mut95874, _mut95875, _mut95876)], x[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95877, _mut95878, _mut95879, _mut95880)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95881, _mut95882, _mut95883, _mut95884)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95885, _mut95886, _mut95887, _mut95888), AOR_multiply(h[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95889, _mut95890, _mut95891, _mut95892)], mu[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95893, _mut95894, _mut95895, _mut95896)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95897, _mut95898, _mut95899, _mut95900), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95901, _mut95902, _mut95903, _mut95904);
            mu[i] = AOR_divide(h[i], g, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95905, _mut95906, _mut95907, _mut95908);
            z[i] = AOR_divide((AOR_minus(AOR_divide(AOR_multiply(3d, (AOR_plus(AOR_minus(AOR_multiply(y[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95909, _mut95910, _mut95911, _mut95912)], h[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95913, _mut95914, _mut95915, _mut95916)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95917, _mut95918, _mut95919, _mut95920), AOR_multiply(y[i], (AOR_minus(x[AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95921, _mut95922, _mut95923, _mut95924)], x[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95925, _mut95926, _mut95927, _mut95928)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95929, _mut95930, _mut95931, _mut95932)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95933, _mut95934, _mut95935, _mut95936), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95937, _mut95938, _mut95939, _mut95940), AOR_multiply(y[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95941, _mut95942, _mut95943, _mut95944)], h[i], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95945, _mut95946, _mut95947, _mut95948), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95949, _mut95950, _mut95951, _mut95952)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95953, _mut95954, _mut95955, _mut95956), (AOR_multiply(h[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95957, _mut95958, _mut95959, _mut95960)], h[i], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95961, _mut95962, _mut95963, _mut95964)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95965, _mut95966, _mut95967, _mut95968), AOR_multiply(h[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95969, _mut95970, _mut95971, _mut95972)], z[AOR_minus(i, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95973, _mut95974, _mut95975, _mut95976)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95977, _mut95978, _mut95979, _mut95980), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95981, _mut95982, _mut95983, _mut95984)), g, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95985, _mut95986, _mut95987, _mut95988);
        }
        // cubic spline coefficients --  b is linear, c quadratic, d is cubic (original y's are constants)
        final double[] b = new double[n];
        final double[] c = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95994, _mut95995, _mut95996, _mut95997)];
        final double[] d = new double[n];
        z[n] = 0d;
        c[n] = 0d;
        for (int j = n - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96062, _mut96063, _mut96064, _mut96065, _mut96066); j--) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65");
            c[j] = AOR_minus(z[j], AOR_multiply(mu[j], c[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut95998, _mut95999, _mut96000, _mut96001)], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96002, _mut96003, _mut96004, _mut96005), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96006, _mut96007, _mut96008, _mut96009);
            b[j] = AOR_minus(AOR_divide((AOR_minus(y[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96010, _mut96011, _mut96012, _mut96013)], y[j], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96014, _mut96015, _mut96016, _mut96017)), h[j], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96018, _mut96019, _mut96020, _mut96021), AOR_divide(AOR_multiply(h[j], (AOR_plus(c[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96022, _mut96023, _mut96024, _mut96025)], AOR_multiply(2d, c[j], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96026, _mut96027, _mut96028, _mut96029), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96030, _mut96031, _mut96032, _mut96033)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96034, _mut96035, _mut96036, _mut96037), 3d, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96038, _mut96039, _mut96040, _mut96041), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96042, _mut96043, _mut96044, _mut96045);
            d[j] = AOR_divide((AOR_minus(c[AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96046, _mut96047, _mut96048, _mut96049)], c[j], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96050, _mut96051, _mut96052, _mut96053)), (AOR_multiply(3d, h[j], "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96054, _mut96055, _mut96056, _mut96057)), "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96058, _mut96059, _mut96060, _mut96061);
        }
        final PolynomialFunction[] polynomials = new PolynomialFunction[n];
        final double[] coefficients = new double[4];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65", _mut96067, _mut96068, _mut96069, _mut96070, _mut96071); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SplineInterpolator.interpolate_65");
            coefficients[0] = y[i];
            coefficients[1] = b[i];
            coefficients[2] = c[i];
            coefficients[3] = d[i];
            polynomials[i] = new PolynomialFunction(coefficients);
        }
        return new PolynomialSplineFunction(x, polynomials);
    }
}
