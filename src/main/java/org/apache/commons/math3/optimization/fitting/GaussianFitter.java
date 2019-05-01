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
package org.apache.commons.math3.optimization.fitting;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Fits points to a {@link
 * org.apache.commons.math3.analysis.function.Gaussian.Parametric Gaussian} function.
 * <p>
 * Usage example:
 * <pre>
 *   GaussianFitter fitter = new GaussianFitter(
 *     new LevenbergMarquardtOptimizer());
 *   fitter.addObservedPoint(4.0254623,  531026.0);
 *   fitter.addObservedPoint(4.03128248, 984167.0);
 *   fitter.addObservedPoint(4.03839603, 1887233.0);
 *   fitter.addObservedPoint(4.04421621, 2687152.0);
 *   fitter.addObservedPoint(4.05132976, 3461228.0);
 *   fitter.addObservedPoint(4.05326982, 3580526.0);
 *   fitter.addObservedPoint(4.05779662, 3439750.0);
 *   fitter.addObservedPoint(4.0636168,  2877648.0);
 *   fitter.addObservedPoint(4.06943698, 2175960.0);
 *   fitter.addObservedPoint(4.07525716, 1447024.0);
 *   fitter.addObservedPoint(4.08237071, 717104.0);
 *   fitter.addObservedPoint(4.08366408, 620014.0);
 *   double[] parameters = fitter.fit();
 * </pre>
 *
 * @since 2.2
 * @deprecated As of 3.1 (to be removed in 4.0).
 */
@Deprecated
public class GaussianFitter extends CurveFitter<Gaussian.Parametric> {

    @Conditional
    public static boolean _mut71815 = false, _mut71816 = false, _mut71817 = false, _mut71818 = false, _mut71819 = false, _mut71820 = false, _mut71821 = false, _mut71822 = false, _mut71823 = false, _mut71824 = false, _mut71825 = false, _mut71826 = false, _mut71827 = false, _mut71828 = false, _mut71829 = false, _mut71830 = false, _mut71831 = false, _mut71832 = false, _mut71833 = false, _mut71834 = false, _mut71835 = false, _mut71836 = false, _mut71837 = false, _mut71838 = false, _mut71839 = false, _mut71840 = false, _mut71841 = false, _mut71842 = false, _mut71843 = false, _mut71844 = false, _mut71845 = false, _mut71846 = false, _mut71847 = false, _mut71848 = false, _mut71849 = false, _mut71850 = false, _mut71851 = false, _mut71852 = false, _mut71853 = false, _mut71854 = false, _mut71855 = false, _mut71856 = false, _mut71857 = false, _mut71858 = false, _mut71859 = false, _mut71860 = false, _mut71861 = false, _mut71862 = false, _mut71863 = false, _mut71864 = false, _mut71865 = false, _mut71866 = false, _mut71867 = false, _mut71868 = false, _mut71869 = false, _mut71870 = false, _mut71871 = false, _mut71872 = false, _mut71873 = false, _mut71874 = false, _mut71875 = false, _mut71876 = false, _mut71877 = false, _mut71878 = false, _mut71879 = false, _mut71880 = false, _mut71881 = false, _mut71882 = false, _mut71883 = false, _mut71884 = false, _mut71885 = false, _mut71886 = false, _mut71887 = false, _mut71888 = false, _mut71889 = false, _mut71890 = false, _mut71891 = false, _mut71892 = false, _mut71893 = false, _mut71894 = false, _mut71895 = false, _mut71896 = false, _mut71897 = false, _mut71898 = false, _mut71899 = false, _mut71900 = false, _mut71901 = false, _mut71902 = false, _mut71903 = false, _mut71904 = false, _mut71905 = false, _mut71906 = false, _mut71907 = false, _mut71908 = false, _mut71909 = false, _mut71910 = false, _mut71911 = false, _mut71912 = false, _mut71913 = false, _mut71914 = false, _mut71915 = false, _mut71916 = false, _mut71917 = false, _mut71918 = false, _mut71919 = false, _mut71920 = false, _mut71921 = false, _mut71922 = false, _mut71923 = false, _mut71924 = false, _mut71925 = false, _mut71926 = false, _mut71927 = false, _mut71928 = false, _mut71929 = false, _mut71930 = false, _mut71931 = false, _mut71932 = false, _mut71933 = false, _mut71934 = false, _mut71935 = false, _mut71936 = false, _mut71937 = false, _mut71938 = false, _mut71939 = false, _mut71940 = false, _mut71941 = false, _mut71942 = false, _mut71943 = false, _mut71944 = false, _mut71945 = false, _mut71946 = false, _mut71947 = false, _mut71948 = false, _mut71949 = false, _mut71950 = false, _mut71951 = false, _mut71952 = false, _mut71953 = false, _mut71954 = false, _mut71955 = false, _mut71956 = false, _mut71957 = false, _mut71958 = false, _mut71959 = false, _mut71960 = false, _mut71961 = false, _mut71962 = false, _mut71963 = false, _mut71964 = false, _mut71965 = false, _mut71966 = false, _mut71967 = false, _mut71968 = false, _mut71969 = false, _mut71970 = false, _mut71971 = false, _mut71972 = false, _mut71973 = false, _mut71974 = false, _mut71975 = false, _mut71976 = false, _mut71977 = false, _mut71978 = false, _mut71979 = false, _mut71980 = false, _mut71981 = false, _mut71982 = false, _mut71983 = false, _mut71984 = false, _mut71985 = false, _mut71986 = false, _mut71987 = false, _mut71988 = false, _mut71989 = false, _mut71990 = false, _mut71991 = false, _mut71992 = false, _mut71993 = false, _mut71994 = false, _mut71995 = false;

    /**
     * Constructs an instance using the specified optimizer.
     *
     * @param optimizer Optimizer to use for the fitting.
     */
    public GaussianFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    /**
     * Fits a Gaussian function to the observed points.
     *
     * @param initialGuess First guess values in the following order:
     * <ul>
     *  <li>Norm</li>
     *  <li>Mean</li>
     *  <li>Sigma</li>
     * </ul>
     * @return the parameters of the Gaussian function that best fits the
     * observed points (in the same order as above).
     * @since 3.0
     */
    public double[] fit(double[] initialGuess) {
        final Gaussian.Parametric f = new Gaussian.Parametric() {

            /**
             * {@inheritDoc}
             */
            @Override
            public double value(double x, double... p) {
                double v = Double.POSITIVE_INFINITY;
                try {
                    v = super.value(x, p);
                } catch (NotStrictlyPositiveException e) {
                }
                return v;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public double[] gradient(double x, double... p) {
                double[] v = { Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY };
                try {
                    v = super.gradient(x, p);
                } catch (NotStrictlyPositiveException e) {
                }
                return v;
            }
        };
        return fit(f, initialGuess);
    }

    /**
     * Fits a Gaussian function to the observed points.
     *
     * @return the parameters of the Gaussian function that best fits the
     * observed points (in the same order as above).
     */
    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
        return fit(guess);
    }

    /**
     * Guesses the parameters {@code norm}, {@code mean}, and {@code sigma}
     * of a {@link org.apache.commons.math3.analysis.function.Gaussian.Parametric}
     * based on the specified observed points.
     */
    public static class ParameterGuesser {

        /**
         * Normalization factor.
         */
        private final double norm;

        /**
         * Mean.
         */
        private final double mean;

        /**
         * Standard deviation.
         */
        private final double sigma;

        /**
         * Constructs instance with the specified observed points.
         *
         * @param observations Observed points from which to guess the
         * parameters of the Gaussian.
         * @throws NullArgumentException if {@code observations} is
         * {@code null}.
         * @throws NumberIsTooSmallException if there are less than 3
         * observations.
         */
        public ParameterGuesser(WeightedObservedPoint[] observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.ParameterGuesser_149");
            if (observations == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
            }
            if (ROR_less(observations.length, 3, "org.apache.commons.math3.optimization.fitting.GaussianFitter.ParameterGuesser_149", _mut71815, _mut71816, _mut71817, _mut71818, _mut71819)) {
                throw new NumberIsTooSmallException(observations.length, 3, true);
            }
            final WeightedObservedPoint[] sorted = sortObservations(observations);
            final double[] params = basicGuess(sorted);
            norm = params[0];
            mean = params[1];
            sigma = params[2];
        }

        /**
         * Gets an estimation of the parameters.
         *
         * @return the guessed parameters, in the following order:
         * <ul>
         *  <li>Normalization factor</li>
         *  <li>Mean</li>
         *  <li>Standard deviation</li>
         * </ul>
         */
        public double[] guess() {
            return new double[] { norm, mean, sigma };
        }

        /**
         * Sort the observations.
         *
         * @param unsorted Input observations.
         * @return the input observations, sorted.
         */
        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190");
            final WeightedObservedPoint[] observations = unsorted.clone();
            final Comparator<WeightedObservedPoint> cmp = new Comparator<WeightedObservedPoint>() {

                /**
                 * {@inheritDoc}
                 */
                public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190");
                    if ((_mut71820 ? (p1 == null || p2 == null) : (p1 == null && p2 == null))) {
                        return 0;
                    }
                    if (p1 == null) {
                        return -1;
                    }
                    if (p2 == null) {
                        return 1;
                    }
                    final int cmpX = Double.compare(p1.getX(), p2.getX());
                    if (ROR_less(cmpX, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71821, _mut71822, _mut71823, _mut71824, _mut71825)) {
                        return -1;
                    }
                    if (ROR_greater(cmpX, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71826, _mut71827, _mut71828, _mut71829, _mut71830)) {
                        return 1;
                    }
                    final int cmpY = Double.compare(p1.getY(), p2.getY());
                    if (ROR_less(cmpY, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71831, _mut71832, _mut71833, _mut71834, _mut71835)) {
                        return -1;
                    }
                    if (ROR_greater(cmpY, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71836, _mut71837, _mut71838, _mut71839, _mut71840)) {
                        return 1;
                    }
                    final int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                    if (ROR_less(cmpW, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71841, _mut71842, _mut71843, _mut71844, _mut71845)) {
                        return -1;
                    }
                    if (ROR_greater(cmpW, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.compare_190", _mut71846, _mut71847, _mut71848, _mut71849, _mut71850)) {
                        return 1;
                    }
                    return 0;
                }
            };
            Arrays.sort(observations, cmp);
            return observations;
        }

        /**
         * Guesses the parameters based on the specified observed points.
         *
         * @param points Observed points, sorted.
         * @return the guessed parameters (normalization factor, mean and
         * sigma).
         */
        private double[] basicGuess(WeightedObservedPoint[] points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237");
            final int maxYIdx = findMaxY(points);
            final double n = points[maxYIdx].getY();
            final double m = points[maxYIdx].getX();
            double fwhmApprox;
            try {
                final double halfY = AOR_plus(n, (AOR_divide((AOR_minus(m, n, "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71859, _mut71860, _mut71861, _mut71862)), 2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71863, _mut71864, _mut71865, _mut71866)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71867, _mut71868, _mut71869, _mut71870);
                final double fwhmX1 = interpolateXAtY(points, maxYIdx, -1, halfY);
                final double fwhmX2 = interpolateXAtY(points, maxYIdx, 1, halfY);
                fwhmApprox = AOR_minus(fwhmX2, fwhmX1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71871, _mut71872, _mut71873, _mut71874);
            } catch (OutOfRangeException e) {
                // TODO: Exceptions should not be used for flow control.
                fwhmApprox = AOR_minus(points[AOR_minus(points.length, 1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71851, _mut71852, _mut71853, _mut71854)].getX(), points[0].getX(), "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71855, _mut71856, _mut71857, _mut71858);
            }
            final double s = AOR_divide(fwhmApprox, (AOR_multiply(2, FastMath.sqrt(AOR_multiply(2, FastMath.log(2), "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71875, _mut71876, _mut71877, _mut71878)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71879, _mut71880, _mut71881, _mut71882)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.basicGuess_237", _mut71883, _mut71884, _mut71885, _mut71886);
            return new double[] { n, m, s };
        }

        /**
         * Finds index of point in specified points with the largest Y.
         *
         * @param points Points to search.
         * @return the index in specified points array.
         */
        private int findMaxY(WeightedObservedPoint[] points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.findMaxY_263");
            int maxYIdx = 0;
            for (int i = 1; ROR_less(i, points.length, "org.apache.commons.math3.optimization.fitting.GaussianFitter.findMaxY_263", _mut71892, _mut71893, _mut71894, _mut71895, _mut71896); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.findMaxY_263");
                if (ROR_greater(points[i].getY(), points[maxYIdx].getY(), "org.apache.commons.math3.optimization.fitting.GaussianFitter.findMaxY_263", _mut71887, _mut71888, _mut71889, _mut71890, _mut71891)) {
                    maxYIdx = i;
                }
            }
            return maxYIdx;
        }

        /**
         * Interpolates using the specified points to determine X at the
         * specified Y.
         *
         * @param points Points to use for interpolation.
         * @param startIdx Index within points from which to start the search for
         * interpolation bounds points.
         * @param idxStep Index step for searching interpolation bounds points.
         * @param y Y value for which X should be determined.
         * @return the value of X for the specified Y.
         * @throws ZeroException if {@code idxStep} is 0.
         * @throws OutOfRangeException if specified {@code y} is not within the
         * range of the specified {@code points}.
         */
        private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71897, _mut71898, _mut71899, _mut71900, _mut71901)) {
                throw new ZeroException();
            }
            final WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
            final WeightedObservedPoint p1 = twoPoints[0];
            final WeightedObservedPoint p2 = twoPoints[1];
            if (ROR_equals(p1.getY(), y, "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71902, _mut71903, _mut71904, _mut71905, _mut71906)) {
                return p1.getX();
            }
            if (ROR_equals(p2.getY(), y, "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71907, _mut71908, _mut71909, _mut71910, _mut71911)) {
                return p2.getX();
            }
            return AOR_plus(p1.getX(), (AOR_divide((AOR_multiply((AOR_minus(y, p1.getY(), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71912, _mut71913, _mut71914, _mut71915)), (AOR_minus(p2.getX(), p1.getX(), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71916, _mut71917, _mut71918, _mut71919)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71920, _mut71921, _mut71922, _mut71923)), (AOR_minus(p2.getY(), p1.getY(), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71924, _mut71925, _mut71926, _mut71927)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71928, _mut71929, _mut71930, _mut71931)), "org.apache.commons.math3.optimization.fitting.GaussianFitter.interpolateXAtY_287", _mut71932, _mut71933, _mut71934, _mut71935);
        }

        /**
         * Gets the two bounding interpolation points from the specified points
         * suitable for determining X at the specified Y.
         *
         * @param points Points to use for interpolation.
         * @param startIdx Index within points from which to start search for
         * interpolation bounds points.
         * @param idxStep Index step for search for interpolation bounds points.
         * @param y Y value for which X should be determined.
         * @return the array containing two points suitable for determining X at
         * the specified Y.
         * @throws ZeroException if {@code idxStep} is 0.
         * @throws OutOfRangeException if specified {@code y} is not within the
         * range of the specified {@code points}.
         */
        private WeightedObservedPoint[] getInterpolationPointsForY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71936, _mut71937, _mut71938, _mut71939, _mut71940)) {
                throw new ZeroException();
            }
            for (int i = startIdx; ROR_less(idxStep, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71950, _mut71951, _mut71952, _mut71953, _mut71954) ? ROR_greater_equals(AOR_plus(i, idxStep, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71964, _mut71965, _mut71966, _mut71967), 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71968, _mut71969, _mut71970, _mut71971, _mut71972) : ROR_less(AOR_plus(i, idxStep, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71955, _mut71956, _mut71957, _mut71958), points.length, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71959, _mut71960, _mut71961, _mut71962, _mut71963); i += idxStep) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324");
                final WeightedObservedPoint p1 = points[i];
                final WeightedObservedPoint p2 = points[AOR_plus(i, idxStep, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71941, _mut71942, _mut71943, _mut71944)];
                if (isBetween(y, p1.getY(), p2.getY())) {
                    if (ROR_less(idxStep, 0, "org.apache.commons.math3.optimization.fitting.GaussianFitter.getInterpolationPointsForY_324", _mut71945, _mut71946, _mut71947, _mut71948, _mut71949)) {
                        return new WeightedObservedPoint[] { p2, p1 };
                    } else {
                        return new WeightedObservedPoint[] { p1, p2 };
                    }
                }
            }
            // TODO: Exceptions should not be used for flow control.
            throw new OutOfRangeException(y, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        /**
         * Determines whether a value is between two other values.
         *
         * @param value Value to test whether it is between {@code boundary1}
         * and {@code boundary2}.
         * @param boundary1 One end of the range.
         * @param boundary2 Other end of the range.
         * @return {@code true} if {@code value} is between {@code boundary1} and
         * {@code boundary2} (inclusive), {@code false} otherwise.
         */
        private boolean isBetween(double value, double boundary1, double boundary2) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364");
            return (_mut71995 ? (((_mut71983 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71973, _mut71974, _mut71975, _mut71976, _mut71977) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71978, _mut71979, _mut71980, _mut71981, _mut71982)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71973, _mut71974, _mut71975, _mut71976, _mut71977) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71978, _mut71979, _mut71980, _mut71981, _mut71982)))) && ((_mut71994 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71984, _mut71985, _mut71986, _mut71987, _mut71988) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71989, _mut71990, _mut71991, _mut71992, _mut71993)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71984, _mut71985, _mut71986, _mut71987, _mut71988) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71989, _mut71990, _mut71991, _mut71992, _mut71993))))) : (((_mut71983 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71973, _mut71974, _mut71975, _mut71976, _mut71977) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71978, _mut71979, _mut71980, _mut71981, _mut71982)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71973, _mut71974, _mut71975, _mut71976, _mut71977) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71978, _mut71979, _mut71980, _mut71981, _mut71982)))) || ((_mut71994 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71984, _mut71985, _mut71986, _mut71987, _mut71988) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71989, _mut71990, _mut71991, _mut71992, _mut71993)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71984, _mut71985, _mut71986, _mut71987, _mut71988) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.optimization.fitting.GaussianFitter.isBetween_364", _mut71989, _mut71990, _mut71991, _mut71992, _mut71993))))));
        }
    }
}
