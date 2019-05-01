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
package org.apache.commons.math3.fitting;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
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
 * @deprecated As of 3.3. Please use {@link GaussianCurveFitter} and
 * {@link WeightedObservedPoints} instead.
 */
@Deprecated
public class GaussianFitter extends CurveFitter<Gaussian.Parametric> {

    @Conditional
    public static boolean _mut39717 = false, _mut39718 = false, _mut39719 = false, _mut39720 = false, _mut39721 = false, _mut39722 = false, _mut39723 = false, _mut39724 = false, _mut39725 = false, _mut39726 = false, _mut39727 = false, _mut39728 = false, _mut39729 = false, _mut39730 = false, _mut39731 = false, _mut39732 = false, _mut39733 = false, _mut39734 = false, _mut39735 = false, _mut39736 = false, _mut39737 = false, _mut39738 = false, _mut39739 = false, _mut39740 = false, _mut39741 = false, _mut39742 = false, _mut39743 = false, _mut39744 = false, _mut39745 = false, _mut39746 = false, _mut39747 = false, _mut39748 = false, _mut39749 = false, _mut39750 = false, _mut39751 = false, _mut39752 = false, _mut39753 = false, _mut39754 = false, _mut39755 = false, _mut39756 = false, _mut39757 = false, _mut39758 = false, _mut39759 = false, _mut39760 = false, _mut39761 = false, _mut39762 = false, _mut39763 = false, _mut39764 = false, _mut39765 = false, _mut39766 = false, _mut39767 = false, _mut39768 = false, _mut39769 = false, _mut39770 = false, _mut39771 = false, _mut39772 = false, _mut39773 = false, _mut39774 = false, _mut39775 = false, _mut39776 = false, _mut39777 = false, _mut39778 = false, _mut39779 = false, _mut39780 = false, _mut39781 = false, _mut39782 = false, _mut39783 = false, _mut39784 = false, _mut39785 = false, _mut39786 = false, _mut39787 = false, _mut39788 = false, _mut39789 = false, _mut39790 = false, _mut39791 = false, _mut39792 = false, _mut39793 = false, _mut39794 = false, _mut39795 = false, _mut39796 = false, _mut39797 = false, _mut39798 = false, _mut39799 = false, _mut39800 = false, _mut39801 = false, _mut39802 = false, _mut39803 = false, _mut39804 = false, _mut39805 = false, _mut39806 = false, _mut39807 = false, _mut39808 = false, _mut39809 = false, _mut39810 = false, _mut39811 = false, _mut39812 = false, _mut39813 = false, _mut39814 = false, _mut39815 = false, _mut39816 = false, _mut39817 = false, _mut39818 = false, _mut39819 = false, _mut39820 = false, _mut39821 = false, _mut39822 = false, _mut39823 = false, _mut39824 = false, _mut39825 = false, _mut39826 = false, _mut39827 = false, _mut39828 = false, _mut39829 = false, _mut39830 = false, _mut39831 = false, _mut39832 = false, _mut39833 = false, _mut39834 = false, _mut39835 = false, _mut39836 = false, _mut39837 = false, _mut39838 = false, _mut39839 = false, _mut39840 = false, _mut39841 = false, _mut39842 = false, _mut39843 = false, _mut39844 = false, _mut39845 = false, _mut39846 = false, _mut39847 = false, _mut39848 = false, _mut39849 = false, _mut39850 = false, _mut39851 = false, _mut39852 = false, _mut39853 = false, _mut39854 = false, _mut39855 = false, _mut39856 = false, _mut39857 = false, _mut39858 = false, _mut39859 = false, _mut39860 = false, _mut39861 = false, _mut39862 = false, _mut39863 = false, _mut39864 = false, _mut39865 = false, _mut39866 = false, _mut39867 = false, _mut39868 = false, _mut39869 = false, _mut39870 = false, _mut39871 = false, _mut39872 = false, _mut39873 = false, _mut39874 = false, _mut39875 = false, _mut39876 = false, _mut39877 = false, _mut39878 = false, _mut39879 = false, _mut39880 = false, _mut39881 = false, _mut39882 = false, _mut39883 = false, _mut39884 = false, _mut39885 = false, _mut39886 = false, _mut39887 = false, _mut39888 = false, _mut39889 = false, _mut39890 = false, _mut39891 = false, _mut39892 = false, _mut39893 = false, _mut39894 = false, _mut39895 = false, _mut39896 = false, _mut39897 = false;

    /**
     * Constructs an instance using the specified optimizer.
     *
     * @param optimizer Optimizer to use for the fitting.
     */
    public GaussianFitter(MultivariateVectorOptimizer optimizer) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.ParameterGuesser_148");
            if (observations == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
            }
            if (ROR_less(observations.length, 3, "org.apache.commons.math3.fitting.GaussianFitter.ParameterGuesser_148", _mut39717, _mut39718, _mut39719, _mut39720, _mut39721)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.compare_189");
            final WeightedObservedPoint[] observations = unsorted.clone();
            final Comparator<WeightedObservedPoint> cmp = new Comparator<WeightedObservedPoint>() {

                /**
                 * {@inheritDoc}
                 */
                public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.compare_189");
                    if ((_mut39722 ? (p1 == null || p2 == null) : (p1 == null && p2 == null))) {
                        return 0;
                    }
                    if (p1 == null) {
                        return -1;
                    }
                    if (p2 == null) {
                        return 1;
                    }
                    final int cmpX = Double.compare(p1.getX(), p2.getX());
                    if (ROR_less(cmpX, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39723, _mut39724, _mut39725, _mut39726, _mut39727)) {
                        return -1;
                    }
                    if (ROR_greater(cmpX, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39728, _mut39729, _mut39730, _mut39731, _mut39732)) {
                        return 1;
                    }
                    final int cmpY = Double.compare(p1.getY(), p2.getY());
                    if (ROR_less(cmpY, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39733, _mut39734, _mut39735, _mut39736, _mut39737)) {
                        return -1;
                    }
                    if (ROR_greater(cmpY, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39738, _mut39739, _mut39740, _mut39741, _mut39742)) {
                        return 1;
                    }
                    final int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                    if (ROR_less(cmpW, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39743, _mut39744, _mut39745, _mut39746, _mut39747)) {
                        return -1;
                    }
                    if (ROR_greater(cmpW, 0, "org.apache.commons.math3.fitting.GaussianFitter.compare_189", _mut39748, _mut39749, _mut39750, _mut39751, _mut39752)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236");
            final int maxYIdx = findMaxY(points);
            final double n = points[maxYIdx].getY();
            final double m = points[maxYIdx].getX();
            double fwhmApprox;
            try {
                final double halfY = AOR_plus(n, (AOR_divide((AOR_minus(m, n, "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39761, _mut39762, _mut39763, _mut39764)), 2, "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39765, _mut39766, _mut39767, _mut39768)), "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39769, _mut39770, _mut39771, _mut39772);
                final double fwhmX1 = interpolateXAtY(points, maxYIdx, -1, halfY);
                final double fwhmX2 = interpolateXAtY(points, maxYIdx, 1, halfY);
                fwhmApprox = AOR_minus(fwhmX2, fwhmX1, "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39773, _mut39774, _mut39775, _mut39776);
            } catch (OutOfRangeException e) {
                // TODO: Exceptions should not be used for flow control.
                fwhmApprox = AOR_minus(points[AOR_minus(points.length, 1, "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39753, _mut39754, _mut39755, _mut39756)].getX(), points[0].getX(), "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39757, _mut39758, _mut39759, _mut39760);
            }
            final double s = AOR_divide(fwhmApprox, (AOR_multiply(2, FastMath.sqrt(AOR_multiply(2, FastMath.log(2), "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39777, _mut39778, _mut39779, _mut39780)), "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39781, _mut39782, _mut39783, _mut39784)), "org.apache.commons.math3.fitting.GaussianFitter.basicGuess_236", _mut39785, _mut39786, _mut39787, _mut39788);
            return new double[] { n, m, s };
        }

        /**
         * Finds index of point in specified points with the largest Y.
         *
         * @param points Points to search.
         * @return the index in specified points array.
         */
        private int findMaxY(WeightedObservedPoint[] points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.findMaxY_262");
            int maxYIdx = 0;
            for (int i = 1; ROR_less(i, points.length, "org.apache.commons.math3.fitting.GaussianFitter.findMaxY_262", _mut39794, _mut39795, _mut39796, _mut39797, _mut39798); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.findMaxY_262");
                if (ROR_greater(points[i].getY(), points[maxYIdx].getY(), "org.apache.commons.math3.fitting.GaussianFitter.findMaxY_262", _mut39789, _mut39790, _mut39791, _mut39792, _mut39793)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39799, _mut39800, _mut39801, _mut39802, _mut39803)) {
                throw new ZeroException();
            }
            final WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
            final WeightedObservedPoint p1 = twoPoints[0];
            final WeightedObservedPoint p2 = twoPoints[1];
            if (ROR_equals(p1.getY(), y, "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39804, _mut39805, _mut39806, _mut39807, _mut39808)) {
                return p1.getX();
            }
            if (ROR_equals(p2.getY(), y, "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39809, _mut39810, _mut39811, _mut39812, _mut39813)) {
                return p2.getX();
            }
            return AOR_plus(p1.getX(), (AOR_divide((AOR_multiply((AOR_minus(y, p1.getY(), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39814, _mut39815, _mut39816, _mut39817)), (AOR_minus(p2.getX(), p1.getX(), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39818, _mut39819, _mut39820, _mut39821)), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39822, _mut39823, _mut39824, _mut39825)), (AOR_minus(p2.getY(), p1.getY(), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39826, _mut39827, _mut39828, _mut39829)), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39830, _mut39831, _mut39832, _mut39833)), "org.apache.commons.math3.fitting.GaussianFitter.interpolateXAtY_286", _mut39834, _mut39835, _mut39836, _mut39837);
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39838, _mut39839, _mut39840, _mut39841, _mut39842)) {
                throw new ZeroException();
            }
            for (int i = startIdx; ROR_less(idxStep, 0, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39852, _mut39853, _mut39854, _mut39855, _mut39856) ? ROR_greater_equals(AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39866, _mut39867, _mut39868, _mut39869), 0, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39870, _mut39871, _mut39872, _mut39873, _mut39874) : ROR_less(AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39857, _mut39858, _mut39859, _mut39860), points.length, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39861, _mut39862, _mut39863, _mut39864, _mut39865); i += idxStep) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323");
                final WeightedObservedPoint p1 = points[i];
                final WeightedObservedPoint p2 = points[AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39843, _mut39844, _mut39845, _mut39846)];
                if (isBetween(y, p1.getY(), p2.getY())) {
                    if (ROR_less(idxStep, 0, "org.apache.commons.math3.fitting.GaussianFitter.getInterpolationPointsForY_323", _mut39847, _mut39848, _mut39849, _mut39850, _mut39851)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianFitter.isBetween_363");
            return (_mut39897 ? (((_mut39885 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39875, _mut39876, _mut39877, _mut39878, _mut39879) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39880, _mut39881, _mut39882, _mut39883, _mut39884)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39875, _mut39876, _mut39877, _mut39878, _mut39879) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39880, _mut39881, _mut39882, _mut39883, _mut39884)))) && ((_mut39896 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39886, _mut39887, _mut39888, _mut39889, _mut39890) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39891, _mut39892, _mut39893, _mut39894, _mut39895)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39886, _mut39887, _mut39888, _mut39889, _mut39890) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39891, _mut39892, _mut39893, _mut39894, _mut39895))))) : (((_mut39885 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39875, _mut39876, _mut39877, _mut39878, _mut39879) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39880, _mut39881, _mut39882, _mut39883, _mut39884)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39875, _mut39876, _mut39877, _mut39878, _mut39879) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39880, _mut39881, _mut39882, _mut39883, _mut39884)))) || ((_mut39896 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39886, _mut39887, _mut39888, _mut39889, _mut39890) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39891, _mut39892, _mut39893, _mut39894, _mut39895)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39886, _mut39887, _mut39888, _mut39889, _mut39890) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianFitter.isBetween_363", _mut39891, _mut39892, _mut39893, _mut39894, _mut39895))))));
        }
    }
}
