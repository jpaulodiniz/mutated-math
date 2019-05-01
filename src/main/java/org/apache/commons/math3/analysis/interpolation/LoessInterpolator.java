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

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://en.wikipedia.org/wiki/Local_regression">
 * Local Regression Algorithm</a> (also Loess, Lowess) for interpolation of
 * real univariate functions.
 * <p>
 * For reference, see
 * <a href="http://amstat.tandfonline.com/doi/abs/10.1080/01621459.1979.10481038">
 * William S. Cleveland - Robust Locally Weighted Regression and Smoothing
 * Scatterplots</a>
 * </p>
 * This class implements both the loess method and serves as an interpolation
 * adapter to it, allowing one to build a spline on the obtained loess fit.
 *
 * @since 2.0
 */
public class LoessInterpolator implements UnivariateInterpolator, Serializable {

    @Conditional
    public static boolean _mut91954 = false, _mut91955 = false, _mut91956 = false, _mut91957 = false, _mut91958 = false, _mut91959 = false, _mut91960 = false, _mut91961 = false, _mut91962 = false, _mut91963 = false, _mut91964 = false, _mut91965 = false, _mut91966 = false, _mut91967 = false, _mut91968 = false, _mut91969 = false, _mut91970 = false, _mut91971 = false, _mut91972 = false, _mut91973 = false, _mut91974 = false, _mut91975 = false, _mut91976 = false, _mut91977 = false, _mut91978 = false, _mut91979 = false, _mut91980 = false, _mut91981 = false, _mut91982 = false, _mut91983 = false, _mut91984 = false, _mut91985 = false, _mut91986 = false, _mut91987 = false, _mut91988 = false, _mut91989 = false, _mut91990 = false, _mut91991 = false, _mut91992 = false, _mut91993 = false, _mut91994 = false, _mut91995 = false, _mut91996 = false, _mut91997 = false, _mut91998 = false, _mut91999 = false, _mut92000 = false, _mut92001 = false, _mut92002 = false, _mut92003 = false, _mut92004 = false, _mut92005 = false, _mut92006 = false, _mut92007 = false, _mut92008 = false, _mut92009 = false, _mut92010 = false, _mut92011 = false, _mut92012 = false, _mut92013 = false, _mut92014 = false, _mut92015 = false, _mut92016 = false, _mut92017 = false, _mut92018 = false, _mut92019 = false, _mut92020 = false, _mut92021 = false, _mut92022 = false, _mut92023 = false, _mut92024 = false, _mut92025 = false, _mut92026 = false, _mut92027 = false, _mut92028 = false, _mut92029 = false, _mut92030 = false, _mut92031 = false, _mut92032 = false, _mut92033 = false, _mut92034 = false, _mut92035 = false, _mut92036 = false, _mut92037 = false, _mut92038 = false, _mut92039 = false, _mut92040 = false, _mut92041 = false, _mut92042 = false, _mut92043 = false, _mut92044 = false, _mut92045 = false, _mut92046 = false, _mut92047 = false, _mut92048 = false, _mut92049 = false, _mut92050 = false, _mut92051 = false, _mut92052 = false, _mut92053 = false, _mut92054 = false, _mut92055 = false, _mut92056 = false, _mut92057 = false, _mut92058 = false, _mut92059 = false, _mut92060 = false, _mut92061 = false, _mut92062 = false, _mut92063 = false, _mut92064 = false, _mut92065 = false, _mut92066 = false, _mut92067 = false, _mut92068 = false, _mut92069 = false, _mut92070 = false, _mut92071 = false, _mut92072 = false, _mut92073 = false, _mut92074 = false, _mut92075 = false, _mut92076 = false, _mut92077 = false, _mut92078 = false, _mut92079 = false, _mut92080 = false, _mut92081 = false, _mut92082 = false, _mut92083 = false, _mut92084 = false, _mut92085 = false, _mut92086 = false, _mut92087 = false, _mut92088 = false, _mut92089 = false, _mut92090 = false, _mut92091 = false, _mut92092 = false, _mut92093 = false, _mut92094 = false, _mut92095 = false, _mut92096 = false, _mut92097 = false, _mut92098 = false, _mut92099 = false, _mut92100 = false, _mut92101 = false, _mut92102 = false, _mut92103 = false, _mut92104 = false, _mut92105 = false, _mut92106 = false, _mut92107 = false, _mut92108 = false, _mut92109 = false, _mut92110 = false, _mut92111 = false, _mut92112 = false, _mut92113 = false, _mut92114 = false, _mut92115 = false, _mut92116 = false, _mut92117 = false, _mut92118 = false, _mut92119 = false, _mut92120 = false, _mut92121 = false, _mut92122 = false, _mut92123 = false, _mut92124 = false, _mut92125 = false, _mut92126 = false, _mut92127 = false, _mut92128 = false, _mut92129 = false, _mut92130 = false, _mut92131 = false, _mut92132 = false, _mut92133 = false, _mut92134 = false, _mut92135 = false, _mut92136 = false, _mut92137 = false, _mut92138 = false, _mut92139 = false, _mut92140 = false, _mut92141 = false, _mut92142 = false, _mut92143 = false, _mut92144 = false, _mut92145 = false, _mut92146 = false, _mut92147 = false, _mut92148 = false, _mut92149 = false, _mut92150 = false, _mut92151 = false, _mut92152 = false, _mut92153 = false, _mut92154 = false, _mut92155 = false, _mut92156 = false, _mut92157 = false, _mut92158 = false, _mut92159 = false, _mut92160 = false, _mut92161 = false, _mut92162 = false, _mut92163 = false, _mut92164 = false, _mut92165 = false, _mut92166 = false, _mut92167 = false, _mut92168 = false, _mut92169 = false, _mut92170 = false, _mut92171 = false, _mut92172 = false, _mut92173 = false, _mut92174 = false, _mut92175 = false, _mut92176 = false, _mut92177 = false, _mut92178 = false, _mut92179 = false, _mut92180 = false, _mut92181 = false, _mut92182 = false, _mut92183 = false, _mut92184 = false, _mut92185 = false, _mut92186 = false, _mut92187 = false, _mut92188 = false, _mut92189 = false, _mut92190 = false, _mut92191 = false, _mut92192 = false, _mut92193 = false, _mut92194 = false, _mut92195 = false, _mut92196 = false, _mut92197 = false, _mut92198 = false, _mut92199 = false, _mut92200 = false, _mut92201 = false, _mut92202 = false, _mut92203 = false, _mut92204 = false, _mut92205 = false, _mut92206 = false, _mut92207 = false, _mut92208 = false, _mut92209 = false, _mut92210 = false, _mut92211 = false, _mut92212 = false, _mut92213 = false, _mut92214 = false, _mut92215 = false, _mut92216 = false, _mut92217 = false, _mut92218 = false, _mut92219 = false, _mut92220 = false, _mut92221 = false, _mut92222 = false, _mut92223 = false, _mut92224 = false, _mut92225 = false, _mut92226 = false, _mut92227 = false, _mut92228 = false, _mut92229 = false, _mut92230 = false, _mut92231 = false, _mut92232 = false, _mut92233 = false, _mut92234 = false, _mut92235 = false, _mut92236 = false, _mut92237 = false, _mut92238 = false, _mut92239 = false, _mut92240 = false, _mut92241 = false, _mut92242 = false, _mut92243 = false, _mut92244 = false, _mut92245 = false, _mut92246 = false, _mut92247 = false, _mut92248 = false, _mut92249 = false, _mut92250 = false, _mut92251 = false, _mut92252 = false, _mut92253 = false, _mut92254 = false, _mut92255 = false, _mut92256 = false, _mut92257 = false, _mut92258 = false, _mut92259 = false, _mut92260 = false, _mut92261 = false, _mut92262 = false, _mut92263 = false, _mut92264 = false, _mut92265 = false, _mut92266 = false;

    /**
     * Default value of the bandwidth parameter.
     */
    public static final double DEFAULT_BANDWIDTH = 0.3;

    /**
     * Default value of the number of robustness iterations.
     */
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;

    /**
     * Default value for accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_ACCURACY = 1e-12;

    /**
     * serializable version identifier.
     */
    private static final long serialVersionUID = 5204927143605193821L;

    /**
     * The bandwidth parameter: when computing the loess fit at
     * a particular point, this fraction of source points closest
     * to the current point is taken into account for computing
     * a least-squares regression.
     * <p>
     * A sensible value is usually 0.25 to 0.5.</p>
     */
    private final double bandwidth;

    /**
     * The number of robustness iterations parameter: this many
     * robustness iterations are done.
     * <p>
     * A sensible value is usually 0 (just the initial fit without any
     * robustness iterations) to 4.</p>
     */
    private final int robustnessIters;

    /**
     * If the median residual at a certain robustness iteration
     * is less than this amount, no more iterations are done.
     */
    private final double accuracy;

    /**
     * Constructs a new {@link LoessInterpolator}
     * with a bandwidth of {@link #DEFAULT_BANDWIDTH},
     * {@link #DEFAULT_ROBUSTNESS_ITERS} robustness iterations
     * and an accuracy of {#link #DEFAULT_ACCURACY}.
     * See {@link #LoessInterpolator(double, int, double)} for an explanation of
     * the parameters.
     */
    public LoessInterpolator() {
        this.bandwidth = DEFAULT_BANDWIDTH;
        this.robustnessIters = DEFAULT_ROBUSTNESS_ITERS;
        this.accuracy = DEFAULT_ACCURACY;
    }

    /**
     * Construct a new {@link LoessInterpolator}
     * with given bandwidth and number of robustness iterations.
     * <p>
     * Calling this constructor is equivalent to calling {link {@link
     * #LoessInterpolator(double, int, double) LoessInterpolator(bandwidth,
     * robustnessIters, LoessInterpolator.DEFAULT_ACCURACY)}
     * </p>
     *
     * @param bandwidth  when computing the loess fit at
     * a particular point, this fraction of source points closest
     * to the current point is taken into account for computing
     * a least-squares regression.
     * A sensible value is usually 0.25 to 0.5, the default value is
     * {@link #DEFAULT_BANDWIDTH}.
     * @param robustnessIters This many robustness iterations are done.
     * A sensible value is usually 0 (just the initial fit without any
     * robustness iterations) to 4, the default value is
     * {@link #DEFAULT_ROBUSTNESS_ITERS}.
     *
     * @see #LoessInterpolator(double, int, double)
     */
    public LoessInterpolator(double bandwidth, int robustnessIters) {
        this(bandwidth, robustnessIters, DEFAULT_ACCURACY);
    }

    /**
     * Construct a new {@link LoessInterpolator}
     * with given bandwidth, number of robustness iterations and accuracy.
     *
     * @param bandwidth  when computing the loess fit at
     * a particular point, this fraction of source points closest
     * to the current point is taken into account for computing
     * a least-squares regression.
     * A sensible value is usually 0.25 to 0.5, the default value is
     * {@link #DEFAULT_BANDWIDTH}.
     * @param robustnessIters This many robustness iterations are done.
     * A sensible value is usually 0 (just the initial fit without any
     * robustness iterations) to 4, the default value is
     * {@link #DEFAULT_ROBUSTNESS_ITERS}.
     * @param accuracy If the median residual at a certain robustness iteration
     * is less than this amount, no more iterations are done.
     * @throws OutOfRangeException if bandwidth does not lie in the interval [0,1].
     * @throws NotPositiveException if {@code robustnessIters} is negative.
     * @see #LoessInterpolator(double, int)
     * @since 2.1
     */
    public LoessInterpolator(double bandwidth, int robustnessIters, double accuracy) throws OutOfRangeException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147");
        if ((_mut91964 ? (ROR_less(bandwidth, 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147", _mut91954, _mut91955, _mut91956, _mut91957, _mut91958) && ROR_greater(bandwidth, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147", _mut91959, _mut91960, _mut91961, _mut91962, _mut91963)) : (ROR_less(bandwidth, 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147", _mut91954, _mut91955, _mut91956, _mut91957, _mut91958) || ROR_greater(bandwidth, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147", _mut91959, _mut91960, _mut91961, _mut91962, _mut91963)))) {
            throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, bandwidth, 0, 1);
        }
        this.bandwidth = bandwidth;
        if (ROR_less(robustnessIters, 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.LoessInterpolator_147", _mut91965, _mut91966, _mut91967, _mut91968, _mut91969)) {
            throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, robustnessIters);
        }
        this.robustnessIters = robustnessIters;
        this.accuracy = accuracy;
    }

    /**
     * Compute an interpolating function by performing a loess fit
     * on the data at the original abscissae and then building a cubic spline
     * with a
     * {@link org.apache.commons.math3.analysis.interpolation.SplineInterpolator}
     * on the resulting fit.
     *
     * @param xval the arguments for the interpolation points
     * @param yval the values for the interpolation points
     * @return A cubic spline built upon a loess fit to the data at the original abscissae
     * @throws NonMonotonicSequenceException if {@code xval} not sorted in
     * strictly increasing order.
     * @throws DimensionMismatchException if {@code xval} and {@code yval} have
     * different sizes.
     * @throws NoDataException if {@code xval} or {@code yval} has zero size.
     * @throws NotFiniteNumberException if any of the arguments and values are
     * not finite real numbers.
     * @throws NumberIsTooSmallException if the bandwidth is too small to
     * accomodate the size of the input data (i.e. the bandwidth must be
     * larger than 2/n).
     */
    public final PolynomialSplineFunction interpolate(final double[] xval, final double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        return new SplineInterpolator().interpolate(xval, smooth(xval, yval));
    }

    /**
     * Compute a weighted loess fit on the data at the original abscissae.
     *
     * @param xval Arguments for the interpolation points.
     * @param yval Values for the interpolation points.
     * @param weights point weights: coefficients by which the robustness weight
     * of a point is multiplied.
     * @return the values of the loess fit at corresponding original abscissae.
     * @throws NonMonotonicSequenceException if {@code xval} not sorted in
     * strictly increasing order.
     * @throws DimensionMismatchException if {@code xval} and {@code yval} have
     * different sizes.
     * @throws NoDataException if {@code xval} or {@code yval} has zero size.
     * @throws NotFiniteNumberException if any of the arguments and values are
     *     not finite real numbers.
     * @throws NumberIsTooSmallException if the bandwidth is too small to
     * accomodate the size of the input data (i.e. the bandwidth must be
     * larger than 2/n).
     * @since 2.1
     */
    public final double[] smooth(final double[] xval, final double[] yval, final double[] weights) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213");
        if (ROR_not_equals(xval.length, yval.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91970, _mut91971, _mut91972, _mut91973, _mut91974)) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        final int n = xval.length;
        if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91975, _mut91976, _mut91977, _mut91978, _mut91979)) {
            throw new NoDataException();
        }
        checkAllFiniteReal(xval);
        checkAllFiniteReal(yval);
        checkAllFiniteReal(weights);
        MathArrays.checkOrder(xval);
        if (ROR_equals(n, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91980, _mut91981, _mut91982, _mut91983, _mut91984)) {
            return new double[] { yval[0] };
        }
        if (ROR_equals(n, 2, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91985, _mut91986, _mut91987, _mut91988, _mut91989)) {
            return new double[] { yval[0], yval[1] };
        }
        int bandwidthInPoints = (int) (AOR_multiply(bandwidth, n, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91990, _mut91991, _mut91992, _mut91993));
        if (ROR_less(bandwidthInPoints, 2, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91994, _mut91995, _mut91996, _mut91997, _mut91998)) {
            throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, bandwidthInPoints, 2, true);
        }
        final double[] res = new double[n];
        final double[] residuals = new double[n];
        final double[] sortedResiduals = new double[n];
        final double[] robustnessWeights = new double[n];
        // starting with all robustness weights set to 1.
        Arrays.fill(robustnessWeights, 1);
        for (int iter = 0; ROR_less_equals(iter, robustnessIters, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92193, _mut92194, _mut92195, _mut92196, _mut92197); ++iter) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213");
            final int[] bandwidthInterval = { 0, AOR_minus(bandwidthInPoints, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut91999, _mut92000, _mut92001, _mut92002) };
            // At each x, compute a local weighted linear regression
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92144, _mut92145, _mut92146, _mut92147, _mut92148); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213");
                final double x = xval[i];
                // a regression is to be made.
                if (ROR_greater(i, 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92003, _mut92004, _mut92005, _mut92006, _mut92007)) {
                    updateBandwidthInterval(xval, weights, i, bandwidthInterval);
                }
                final int ileft = bandwidthInterval[0];
                final int iright = bandwidthInterval[1];
                // farthest from x
                final int edge;
                if (ROR_greater(AOR_minus(xval[i], xval[ileft], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92008, _mut92009, _mut92010, _mut92011), AOR_minus(xval[iright], xval[i], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92012, _mut92013, _mut92014, _mut92015), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92016, _mut92017, _mut92018, _mut92019, _mut92020)) {
                    edge = ileft;
                } else {
                    edge = iright;
                }
                // (section "Weighted least squares")
                double sumWeights = 0;
                double sumX = 0;
                double sumXSquared = 0;
                double sumY = 0;
                double sumXY = 0;
                double denom = FastMath.abs(AOR_divide(1.0, (AOR_minus(xval[edge], x, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92021, _mut92022, _mut92023, _mut92024)), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92025, _mut92026, _mut92027, _mut92028));
                for (int k = ileft; ROR_less_equals(k, iright, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92070, _mut92071, _mut92072, _mut92073, _mut92074); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213");
                    final double xk = xval[k];
                    final double yk = yval[k];
                    final double dist = (ROR_less(k, i, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92029, _mut92030, _mut92031, _mut92032, _mut92033)) ? AOR_minus(x, xk, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92038, _mut92039, _mut92040, _mut92041) : AOR_minus(xk, x, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92034, _mut92035, _mut92036, _mut92037);
                    final double w = AOR_multiply(AOR_multiply(tricube(AOR_multiply(dist, denom, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92042, _mut92043, _mut92044, _mut92045)), robustnessWeights[k], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92046, _mut92047, _mut92048, _mut92049), weights[k], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92050, _mut92051, _mut92052, _mut92053);
                    final double xkw = AOR_multiply(xk, w, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92054, _mut92055, _mut92056, _mut92057);
                    sumWeights += w;
                    sumX += xkw;
                    sumXSquared += AOR_multiply(xk, xkw, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92058, _mut92059, _mut92060, _mut92061);
                    sumY += AOR_multiply(yk, w, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92062, _mut92063, _mut92064, _mut92065);
                    sumXY += AOR_multiply(yk, xkw, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92066, _mut92067, _mut92068, _mut92069);
                }
                final double meanX = AOR_divide(sumX, sumWeights, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92075, _mut92076, _mut92077, _mut92078);
                final double meanY = AOR_divide(sumY, sumWeights, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92079, _mut92080, _mut92081, _mut92082);
                final double meanXY = AOR_divide(sumXY, sumWeights, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92083, _mut92084, _mut92085, _mut92086);
                final double meanXSquared = AOR_divide(sumXSquared, sumWeights, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92087, _mut92088, _mut92089, _mut92090);
                final double beta;
                if (ROR_less(FastMath.sqrt(FastMath.abs(AOR_minus(meanXSquared, AOR_multiply(meanX, meanX, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92091, _mut92092, _mut92093, _mut92094), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92095, _mut92096, _mut92097, _mut92098))), accuracy, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92099, _mut92100, _mut92101, _mut92102, _mut92103)) {
                    beta = 0;
                } else {
                    beta = AOR_divide((AOR_minus(meanXY, AOR_multiply(meanX, meanY, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92104, _mut92105, _mut92106, _mut92107), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92108, _mut92109, _mut92110, _mut92111)), (AOR_minus(meanXSquared, AOR_multiply(meanX, meanX, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92112, _mut92113, _mut92114, _mut92115), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92116, _mut92117, _mut92118, _mut92119)), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92120, _mut92121, _mut92122, _mut92123);
                }
                final double alpha = AOR_minus(meanY, AOR_multiply(beta, meanX, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92124, _mut92125, _mut92126, _mut92127), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92128, _mut92129, _mut92130, _mut92131);
                res[i] = AOR_plus(AOR_multiply(beta, x, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92132, _mut92133, _mut92134, _mut92135), alpha, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92136, _mut92137, _mut92138, _mut92139);
                residuals[i] = FastMath.abs(AOR_minus(yval[i], res[i], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92140, _mut92141, _mut92142, _mut92143));
            }
            // iteration, they won't be needed anymore
            if (ROR_equals(iter, robustnessIters, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92149, _mut92150, _mut92151, _mut92152, _mut92153)) {
                break;
            }
            // because the preceding loop is a lot more expensive
            System.arraycopy(residuals, 0, sortedResiduals, 0, n);
            Arrays.sort(sortedResiduals);
            final double medianResidual = sortedResiduals[AOR_divide(n, 2, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92154, _mut92155, _mut92156, _mut92157)];
            if (ROR_less(FastMath.abs(medianResidual), accuracy, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92158, _mut92159, _mut92160, _mut92161, _mut92162)) {
                break;
            }
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92188, _mut92189, _mut92190, _mut92191, _mut92192); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213");
                final double arg = AOR_divide(residuals[i], (AOR_multiply(6, medianResidual, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92163, _mut92164, _mut92165, _mut92166)), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92167, _mut92168, _mut92169, _mut92170);
                if (ROR_greater_equals(arg, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92171, _mut92172, _mut92173, _mut92174, _mut92175)) {
                    robustnessWeights[i] = 0;
                } else {
                    final double w = AOR_minus(1, AOR_multiply(arg, arg, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92176, _mut92177, _mut92178, _mut92179), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92180, _mut92181, _mut92182, _mut92183);
                    robustnessWeights[i] = AOR_multiply(w, w, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_213", _mut92184, _mut92185, _mut92186, _mut92187);
                }
            }
        }
        return res;
    }

    /**
     * Compute a loess fit on the data at the original abscissae.
     *
     * @param xval the arguments for the interpolation points
     * @param yval the values for the interpolation points
     * @return values of the loess fit at corresponding original abscissae
     * @throws NonMonotonicSequenceException if {@code xval} not sorted in
     * strictly increasing order.
     * @throws DimensionMismatchException if {@code xval} and {@code yval} have
     * different sizes.
     * @throws NoDataException if {@code xval} or {@code yval} has zero size.
     * @throws NotFiniteNumberException if any of the arguments and values are
     * not finite real numbers.
     * @throws NumberIsTooSmallException if the bandwidth is too small to
     * accomodate the size of the input data (i.e. the bandwidth must be
     * larger than 2/n).
     */
    public final double[] smooth(final double[] xval, final double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_381");
        if (ROR_not_equals(xval.length, yval.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.smooth_381", _mut92198, _mut92199, _mut92200, _mut92201, _mut92202)) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        final double[] unitWeights = new double[xval.length];
        Arrays.fill(unitWeights, 1.0);
        return smooth(xval, yval, unitWeights);
    }

    /**
     * Given an index interval into xval that embraces a certain number of
     * points closest to {@code xval[i-1]}, update the interval so that it
     * embraces the same number of points closest to {@code xval[i]},
     * ignoring zero weights.
     *
     * @param xval Arguments array.
     * @param weights Weights array.
     * @param i Index around which the new interval should be computed.
     * @param bandwidthInterval a two-element array {left, right} such that:
     * {@code (left==0 or xval[i] - xval[left-1] > xval[right] - xval[i])}
     * and
     * {@code (right==xval.length-1 or xval[right+1] - xval[i] > xval[i] - xval[left])}.
     * The array will be updated.
     */
    private static void updateBandwidthInterval(final double[] xval, final double[] weights, final int i, final int[] bandwidthInterval) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412");
        final int left = bandwidthInterval[0];
        final int right = bandwidthInterval[1];
        // is closer to xval[i] than the leftmost point of the current interval
        int nextRight = nextNonzero(weights, right);
        if ((_mut92221 ? (ROR_less(nextRight, xval.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92203, _mut92204, _mut92205, _mut92206, _mut92207) || ROR_less(AOR_minus(xval[nextRight], xval[i], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92208, _mut92209, _mut92210, _mut92211), AOR_minus(xval[i], xval[left], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92212, _mut92213, _mut92214, _mut92215), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92216, _mut92217, _mut92218, _mut92219, _mut92220)) : (ROR_less(nextRight, xval.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92203, _mut92204, _mut92205, _mut92206, _mut92207) && ROR_less(AOR_minus(xval[nextRight], xval[i], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92208, _mut92209, _mut92210, _mut92211), AOR_minus(xval[i], xval[left], "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92212, _mut92213, _mut92214, _mut92215), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.updateBandwidthInterval_412", _mut92216, _mut92217, _mut92218, _mut92219, _mut92220)))) {
            int nextLeft = nextNonzero(weights, bandwidthInterval[0]);
            bandwidthInterval[0] = nextLeft;
            bandwidthInterval[1] = nextRight;
        }
    }

    /**
     * Return the smallest index {@code j} such that
     * {@code j > i && (j == weights.length || weights[j] != 0)}.
     *
     * @param weights Weights array.
     * @param i Index from which to start search.
     * @return the smallest compliant index.
     */
    private static int nextNonzero(final double[] weights, final int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436");
        int j = AOR_plus(i, 1, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436", _mut92222, _mut92223, _mut92224, _mut92225);
        while ((_mut92236 ? (ROR_less(j, weights.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436", _mut92226, _mut92227, _mut92228, _mut92229, _mut92230) || ROR_equals(weights[j], 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436", _mut92231, _mut92232, _mut92233, _mut92234, _mut92235)) : (ROR_less(j, weights.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436", _mut92226, _mut92227, _mut92228, _mut92229, _mut92230) && ROR_equals(weights[j], 0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436", _mut92231, _mut92232, _mut92233, _mut92234, _mut92235)))) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero_436");
            ++j;
        }
        return j;
    }

    /**
     * Compute the
     * <a href="http://en.wikipedia.org/wiki/Local_regression#Weight_function">tricube</a>
     * weight function
     *
     * @param x Argument.
     * @return <code>(1 - |x|<sup>3</sup>)<sup>3</sup></code> for |x| &lt; 1, 0 otherwise.
     */
    private static double tricube(final double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452");
        final double absX = FastMath.abs(x);
        if (ROR_greater_equals(absX, 1.0, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92237, _mut92238, _mut92239, _mut92240, _mut92241)) {
            return 0.0;
        }
        final double tmp = AOR_minus(1, AOR_multiply(AOR_multiply(absX, absX, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92242, _mut92243, _mut92244, _mut92245), absX, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92246, _mut92247, _mut92248, _mut92249), "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92250, _mut92251, _mut92252, _mut92253);
        return AOR_multiply(AOR_multiply(tmp, tmp, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92254, _mut92255, _mut92256, _mut92257), tmp, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.tricube_452", _mut92258, _mut92259, _mut92260, _mut92261);
    }

    /**
     * Check that all elements of an array are finite real numbers.
     *
     * @param values Values array.
     * @throws org.apache.commons.math3.exception.NotFiniteNumberException
     * if one of the values is not a finite real number.
     */
    private static void checkAllFiniteReal(final double[] values) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.checkAllFiniteReal_468");
        for (int i = 0; ROR_less(i, values.length, "org.apache.commons.math3.analysis.interpolation.LoessInterpolator.checkAllFiniteReal_468", _mut92262, _mut92263, _mut92264, _mut92265, _mut92266); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.LoessInterpolator.checkAllFiniteReal_468");
            MathUtils.checkFinite(values[i]);
        }
    }
}
