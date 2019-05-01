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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Fits points to a {@link
 * org.apache.commons.math3.analysis.function.Gaussian.Parametric Gaussian}
 * function.
 * <br/>
 * The {@link #withStartPoint(double[]) initial guess values} must be passed
 * in the following order:
 * <ul>
 *  <li>Normalization</li>
 *  <li>Mean</li>
 *  <li>Sigma</li>
 * </ul>
 * The optimal values will be returned in the same order.
 *
 * <p>
 * Usage example:
 * <pre>
 *   WeightedObservedPoints obs = new WeightedObservedPoints();
 *   obs.add(4.0254623,  531026.0);
 *   obs.add(4.03128248, 984167.0);
 *   obs.add(4.03839603, 1887233.0);
 *   obs.add(4.04421621, 2687152.0);
 *   obs.add(4.05132976, 3461228.0);
 *   obs.add(4.05326982, 3580526.0);
 *   obs.add(4.05779662, 3439750.0);
 *   obs.add(4.0636168,  2877648.0);
 *   obs.add(4.06943698, 2175960.0);
 *   obs.add(4.07525716, 1447024.0);
 *   obs.add(4.08237071, 717104.0);
 *   obs.add(4.08366408, 620014.0);
 *   double[] parameters = GaussianCurveFitter.create().fit(obs.toList());
 * </pre>
 *
 * @since 3.3
 */
public class GaussianCurveFitter extends AbstractCurveFitter {

    @Conditional
    public static boolean _mut37981 = false, _mut37982 = false, _mut37983 = false, _mut37984 = false, _mut37985 = false, _mut37986 = false, _mut37987 = false, _mut37988 = false, _mut37989 = false, _mut37990 = false, _mut37991 = false, _mut37992 = false, _mut37993 = false, _mut37994 = false, _mut37995 = false, _mut37996 = false, _mut37997 = false, _mut37998 = false, _mut37999 = false, _mut38000 = false, _mut38001 = false, _mut38002 = false, _mut38003 = false, _mut38004 = false, _mut38005 = false, _mut38006 = false, _mut38007 = false, _mut38008 = false, _mut38009 = false, _mut38010 = false, _mut38011 = false, _mut38012 = false, _mut38013 = false, _mut38014 = false, _mut38015 = false, _mut38016 = false, _mut38017 = false, _mut38018 = false, _mut38019 = false, _mut38020 = false, _mut38021 = false, _mut38022 = false, _mut38023 = false, _mut38024 = false, _mut38025 = false, _mut38026 = false, _mut38027 = false, _mut38028 = false, _mut38029 = false, _mut38030 = false, _mut38031 = false, _mut38032 = false, _mut38033 = false, _mut38034 = false, _mut38035 = false, _mut38036 = false, _mut38037 = false, _mut38038 = false, _mut38039 = false, _mut38040 = false, _mut38041 = false, _mut38042 = false, _mut38043 = false, _mut38044 = false, _mut38045 = false, _mut38046 = false, _mut38047 = false, _mut38048 = false, _mut38049 = false, _mut38050 = false, _mut38051 = false, _mut38052 = false, _mut38053 = false, _mut38054 = false, _mut38055 = false, _mut38056 = false, _mut38057 = false, _mut38058 = false, _mut38059 = false, _mut38060 = false, _mut38061 = false, _mut38062 = false, _mut38063 = false, _mut38064 = false, _mut38065 = false, _mut38066 = false, _mut38067 = false, _mut38068 = false, _mut38069 = false, _mut38070 = false, _mut38071 = false, _mut38072 = false, _mut38073 = false, _mut38074 = false, _mut38075 = false, _mut38076 = false, _mut38077 = false, _mut38078 = false, _mut38079 = false, _mut38080 = false, _mut38081 = false, _mut38082 = false, _mut38083 = false, _mut38084 = false, _mut38085 = false, _mut38086 = false, _mut38087 = false, _mut38088 = false, _mut38089 = false, _mut38090 = false, _mut38091 = false, _mut38092 = false, _mut38093 = false, _mut38094 = false, _mut38095 = false, _mut38096 = false, _mut38097 = false, _mut38098 = false, _mut38099 = false, _mut38100 = false, _mut38101 = false, _mut38102 = false, _mut38103 = false, _mut38104 = false, _mut38105 = false, _mut38106 = false, _mut38107 = false, _mut38108 = false, _mut38109 = false, _mut38110 = false, _mut38111 = false, _mut38112 = false, _mut38113 = false, _mut38114 = false, _mut38115 = false, _mut38116 = false, _mut38117 = false, _mut38118 = false, _mut38119 = false, _mut38120 = false, _mut38121 = false, _mut38122 = false, _mut38123 = false, _mut38124 = false, _mut38125 = false, _mut38126 = false, _mut38127 = false, _mut38128 = false, _mut38129 = false, _mut38130 = false, _mut38131 = false, _mut38132 = false, _mut38133 = false, _mut38134 = false, _mut38135 = false, _mut38136 = false, _mut38137 = false, _mut38138 = false, _mut38139 = false, _mut38140 = false, _mut38141 = false, _mut38142 = false, _mut38143 = false, _mut38144 = false, _mut38145 = false, _mut38146 = false, _mut38147 = false, _mut38148 = false, _mut38149 = false, _mut38150 = false, _mut38151 = false, _mut38152 = false, _mut38153 = false, _mut38154 = false, _mut38155 = false, _mut38156 = false, _mut38157 = false, _mut38158 = false, _mut38159 = false, _mut38160 = false, _mut38161 = false;

    /**
     * Parametric function to be fitted.
     */
    private static final Gaussian.Parametric FUNCTION = new Gaussian.Parametric() {

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

    /**
     * Initial guess.
     */
    private final double[] initialGuess;

    /**
     * Maximum number of iterations of the optimization algorithm.
     */
    private final int maxIter;

    /**
     * Contructor used by the factory methods.
     *
     * @param initialGuess Initial guess. If set to {@code null}, the initial guess
     * will be estimated using the {@link ParameterGuesser}.
     * @param maxIter Maximum number of iterations of the optimization algorithm.
     */
    private GaussianCurveFitter(double[] initialGuess, int maxIter) {
        this.initialGuess = initialGuess;
        this.maxIter = maxIter;
    }

    /**
     * Creates a default curve fitter.
     * The initial guess for the parameters will be {@link ParameterGuesser}
     * computed automatically, and the maximum number of iterations of the
     * optimization algorithm is set to {@link Integer#MAX_VALUE}.
     *
     * @return a curve fitter.
     *
     * @see #withStartPoint(double[])
     * @see #withMaxIterations(int)
     */
    public static GaussianCurveFitter create() {
        return new GaussianCurveFitter(null, Integer.MAX_VALUE);
    }

    /**
     * Configure the start point (initial guess).
     * @param newStart new start point (initial guess)
     * @return a new instance.
     */
    public GaussianCurveFitter withStartPoint(double[] newStart) {
        return new GaussianCurveFitter(newStart.clone(), maxIter);
    }

    /**
     * Configure the maximum number of iterations.
     * @param newMaxIter maximum number of iterations
     * @return a new instance.
     */
    public GaussianCurveFitter withMaxIterations(int newMaxIter) {
        return new GaussianCurveFitter(initialGuess, newMaxIter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> observations) {
        // Prepare least-squares problem.
        final int len = observations.size();
        final double[] target = new double[len];
        final double[] weights = new double[len];
        int i = 0;
        for (WeightedObservedPoint obs : observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.getProblem_155");
            target[i] = obs.getY();
            weights[i] = obs.getWeight();
            ++i;
        }
        final AbstractCurveFitter.TheoreticalValuesFunction model = new AbstractCurveFitter.TheoreticalValuesFunction(FUNCTION, observations);
        final double[] startPoint = initialGuess != null ? initialGuess : // Compute estimation.
        new ParameterGuesser(observations).guess();
        // observed points.
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(maxIter).start(startPoint).target(target).weight(new DiagonalMatrix(weights)).model(model.getModelFunction(), model.getModelFunctionJacobian()).build();
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
        public ParameterGuesser(Collection<WeightedObservedPoint> observations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.ParameterGuesser_214");
            if (observations == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY);
            }
            if (ROR_less(observations.size(), 3, "org.apache.commons.math3.fitting.GaussianCurveFitter.ParameterGuesser_214", _mut37981, _mut37982, _mut37983, _mut37984, _mut37985)) {
                throw new NumberIsTooSmallException(observations.size(), 3, true);
            }
            final List<WeightedObservedPoint> sorted = sortObservations(observations);
            final double[] params = basicGuess(sorted.toArray(new WeightedObservedPoint[0]));
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
        private List<WeightedObservedPoint> sortObservations(Collection<WeightedObservedPoint> unsorted) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255");
            final List<WeightedObservedPoint> observations = new ArrayList<WeightedObservedPoint>(unsorted);
            final Comparator<WeightedObservedPoint> cmp = new Comparator<WeightedObservedPoint>() {

                /**
                 * {@inheritDoc}
                 */
                public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255");
                    if ((_mut37986 ? (p1 == null || p2 == null) : (p1 == null && p2 == null))) {
                        return 0;
                    }
                    if (p1 == null) {
                        return -1;
                    }
                    if (p2 == null) {
                        return 1;
                    }
                    final int cmpX = Double.compare(p1.getX(), p2.getX());
                    if (ROR_less(cmpX, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut37987, _mut37988, _mut37989, _mut37990, _mut37991)) {
                        return -1;
                    }
                    if (ROR_greater(cmpX, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut37992, _mut37993, _mut37994, _mut37995, _mut37996)) {
                        return 1;
                    }
                    final int cmpY = Double.compare(p1.getY(), p2.getY());
                    if (ROR_less(cmpY, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut37997, _mut37998, _mut37999, _mut38000, _mut38001)) {
                        return -1;
                    }
                    if (ROR_greater(cmpY, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut38002, _mut38003, _mut38004, _mut38005, _mut38006)) {
                        return 1;
                    }
                    final int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                    if (ROR_less(cmpW, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut38007, _mut38008, _mut38009, _mut38010, _mut38011)) {
                        return -1;
                    }
                    if (ROR_greater(cmpW, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.compare_255", _mut38012, _mut38013, _mut38014, _mut38015, _mut38016)) {
                        return 1;
                    }
                    return 0;
                }
            };
            Collections.sort(observations, cmp);
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302");
            final int maxYIdx = findMaxY(points);
            final double n = points[maxYIdx].getY();
            final double m = points[maxYIdx].getX();
            double fwhmApprox;
            try {
                final double halfY = AOR_plus(n, (AOR_divide((AOR_minus(m, n, "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38025, _mut38026, _mut38027, _mut38028)), 2, "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38029, _mut38030, _mut38031, _mut38032)), "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38033, _mut38034, _mut38035, _mut38036);
                final double fwhmX1 = interpolateXAtY(points, maxYIdx, -1, halfY);
                final double fwhmX2 = interpolateXAtY(points, maxYIdx, 1, halfY);
                fwhmApprox = AOR_minus(fwhmX2, fwhmX1, "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38037, _mut38038, _mut38039, _mut38040);
            } catch (OutOfRangeException e) {
                // TODO: Exceptions should not be used for flow control.
                fwhmApprox = AOR_minus(points[AOR_minus(points.length, 1, "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38017, _mut38018, _mut38019, _mut38020)].getX(), points[0].getX(), "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38021, _mut38022, _mut38023, _mut38024);
            }
            final double s = AOR_divide(fwhmApprox, (AOR_multiply(2, FastMath.sqrt(AOR_multiply(2, FastMath.log(2), "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38041, _mut38042, _mut38043, _mut38044)), "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38045, _mut38046, _mut38047, _mut38048)), "org.apache.commons.math3.fitting.GaussianCurveFitter.basicGuess_302", _mut38049, _mut38050, _mut38051, _mut38052);
            return new double[] { n, m, s };
        }

        /**
         * Finds index of point in specified points with the largest Y.
         *
         * @param points Points to search.
         * @return the index in specified points array.
         */
        private int findMaxY(WeightedObservedPoint[] points) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.findMaxY_328");
            int maxYIdx = 0;
            for (int i = 1; ROR_less(i, points.length, "org.apache.commons.math3.fitting.GaussianCurveFitter.findMaxY_328", _mut38058, _mut38059, _mut38060, _mut38061, _mut38062); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.findMaxY_328");
                if (ROR_greater(points[i].getY(), points[maxYIdx].getY(), "org.apache.commons.math3.fitting.GaussianCurveFitter.findMaxY_328", _mut38053, _mut38054, _mut38055, _mut38056, _mut38057)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38063, _mut38064, _mut38065, _mut38066, _mut38067)) {
                throw new ZeroException();
            }
            final WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
            final WeightedObservedPoint p1 = twoPoints[0];
            final WeightedObservedPoint p2 = twoPoints[1];
            if (ROR_equals(p1.getY(), y, "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38068, _mut38069, _mut38070, _mut38071, _mut38072)) {
                return p1.getX();
            }
            if (ROR_equals(p2.getY(), y, "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38073, _mut38074, _mut38075, _mut38076, _mut38077)) {
                return p2.getX();
            }
            return AOR_plus(p1.getX(), (AOR_divide((AOR_multiply((AOR_minus(y, p1.getY(), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38078, _mut38079, _mut38080, _mut38081)), (AOR_minus(p2.getX(), p1.getX(), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38082, _mut38083, _mut38084, _mut38085)), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38086, _mut38087, _mut38088, _mut38089)), (AOR_minus(p2.getY(), p1.getY(), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38090, _mut38091, _mut38092, _mut38093)), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38094, _mut38095, _mut38096, _mut38097)), "org.apache.commons.math3.fitting.GaussianCurveFitter.interpolateXAtY_352", _mut38098, _mut38099, _mut38100, _mut38101);
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389");
            if (ROR_equals(idxStep, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38102, _mut38103, _mut38104, _mut38105, _mut38106)) {
                throw new ZeroException();
            }
            for (int i = startIdx; ROR_less(idxStep, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38116, _mut38117, _mut38118, _mut38119, _mut38120) ? ROR_greater_equals(AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38130, _mut38131, _mut38132, _mut38133), 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38134, _mut38135, _mut38136, _mut38137, _mut38138) : ROR_less(AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38121, _mut38122, _mut38123, _mut38124), points.length, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38125, _mut38126, _mut38127, _mut38128, _mut38129); i += idxStep) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389");
                final WeightedObservedPoint p1 = points[i];
                final WeightedObservedPoint p2 = points[AOR_plus(i, idxStep, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38107, _mut38108, _mut38109, _mut38110)];
                if (isBetween(y, p1.getY(), p2.getY())) {
                    if (ROR_less(idxStep, 0, "org.apache.commons.math3.fitting.GaussianCurveFitter.getInterpolationPointsForY_389", _mut38111, _mut38112, _mut38113, _mut38114, _mut38115)) {
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429");
            return (_mut38161 ? (((_mut38149 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38139, _mut38140, _mut38141, _mut38142, _mut38143) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38144, _mut38145, _mut38146, _mut38147, _mut38148)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38139, _mut38140, _mut38141, _mut38142, _mut38143) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38144, _mut38145, _mut38146, _mut38147, _mut38148)))) && ((_mut38160 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38150, _mut38151, _mut38152, _mut38153, _mut38154) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38155, _mut38156, _mut38157, _mut38158, _mut38159)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38150, _mut38151, _mut38152, _mut38153, _mut38154) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38155, _mut38156, _mut38157, _mut38158, _mut38159))))) : (((_mut38149 ? (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38139, _mut38140, _mut38141, _mut38142, _mut38143) || ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38144, _mut38145, _mut38146, _mut38147, _mut38148)) : (ROR_greater_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38139, _mut38140, _mut38141, _mut38142, _mut38143) && ROR_less_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38144, _mut38145, _mut38146, _mut38147, _mut38148)))) || ((_mut38160 ? (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38150, _mut38151, _mut38152, _mut38153, _mut38154) || ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38155, _mut38156, _mut38157, _mut38158, _mut38159)) : (ROR_greater_equals(value, boundary2, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38150, _mut38151, _mut38152, _mut38153, _mut38154) && ROR_less_equals(value, boundary1, "org.apache.commons.math3.fitting.GaussianCurveFitter.isBetween_429", _mut38155, _mut38156, _mut38157, _mut38158, _mut38159))))));
        }
    }
}
