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
package org.apache.commons.math3.optimization;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

@Deprecated
public class LeastSquaresConverter implements MultivariateFunction {

    @Conditional
    public static boolean _mut72045 = false, _mut72046 = false, _mut72047 = false, _mut72048 = false, _mut72049 = false, _mut72050 = false, _mut72051 = false, _mut72052 = false, _mut72053 = false, _mut72054 = false, _mut72055 = false, _mut72056 = false, _mut72057 = false, _mut72058 = false, _mut72059 = false, _mut72060 = false, _mut72061 = false, _mut72062 = false, _mut72063 = false, _mut72064 = false, _mut72065 = false, _mut72066 = false, _mut72067 = false, _mut72068 = false, _mut72069 = false, _mut72070 = false, _mut72071 = false, _mut72072 = false, _mut72073 = false, _mut72074 = false, _mut72075 = false, _mut72076 = false, _mut72077 = false, _mut72078 = false, _mut72079 = false, _mut72080 = false, _mut72081 = false, _mut72082 = false, _mut72083 = false, _mut72084 = false, _mut72085 = false;

    /**
     * Underlying vectorial function.
     */
    private final MultivariateVectorFunction function;

    /**
     * Observations to be compared to objective function to compute residuals.
     */
    private final double[] observations;

    /**
     * Optional weights for the residuals.
     */
    private final double[] weights;

    /**
     * Optional scaling matrix (weight and correlations) for the residuals.
     */
    private final RealMatrix scale;

    /**
     * Build a simple converter for uncorrelated residuals with the same weight.
     * @param function vectorial residuals function to wrap
     * @param observations observations to be compared to objective function to compute residuals
     */
    public LeastSquaresConverter(final MultivariateVectorFunction function, final double[] observations) {
        this.function = function;
        this.observations = observations.clone();
        this.weights = null;
        this.scale = null;
    }

    /**
     * Build a simple converter for uncorrelated residuals with the specific weights.
     * <p>
     * The scalar objective function value is computed as:
     * <pre>
     * objective = &sum;weight<sub>i</sub>(observation<sub>i</sub>-objective<sub>i</sub>)<sup>2</sup>
     * </pre>
     * </p>
     * <p>
     * Weights can be used for example to combine residuals with different standard
     * deviations. As an example, consider a residuals array in which even elements
     * are angular measurements in degrees with a 0.01&deg; standard deviation and
     * odd elements are distance measurements in meters with a 15m standard deviation.
     * In this case, the weights array should be initialized with value
     * 1.0/(0.01<sup>2</sup>) in the even elements and 1.0/(15.0<sup>2</sup>) in the
     * odd elements (i.e. reciprocals of variances).
     * </p>
     * <p>
     * The array computed by the objective function, the observations array and the
     * weights array must have consistent sizes or a {@link DimensionMismatchException}
     * will be triggered while computing the scalar objective.
     * </p>
     * @param function vectorial residuals function to wrap
     * @param observations observations to be compared to objective function to compute residuals
     * @param weights weights to apply to the residuals
     * @exception DimensionMismatchException if the observations vector and the weights
     * vector dimensions do not match (objective function dimension is checked only when
     * the {@link #value(double[])} method is called)
     */
    public LeastSquaresConverter(final MultivariateVectorFunction function, final double[] observations, final double[] weights) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.LeastSquaresConverter_111");
        if (ROR_not_equals(observations.length, weights.length, "org.apache.commons.math3.optimization.LeastSquaresConverter.LeastSquaresConverter_111", _mut72045, _mut72046, _mut72047, _mut72048, _mut72049)) {
            throw new DimensionMismatchException(observations.length, weights.length);
        }
        this.function = function;
        this.observations = observations.clone();
        this.weights = weights.clone();
        this.scale = null;
    }

    /**
     * Build a simple converter for correlated residuals with the specific weights.
     * <p>
     * The scalar objective function value is computed as:
     * <pre>
     * objective = y<sup>T</sup>y with y = scale&times;(observation-objective)
     * </pre>
     * </p>
     * <p>
     * The array computed by the objective function, the observations array and the
     * the scaling matrix must have consistent sizes or a {@link DimensionMismatchException}
     * will be triggered while computing the scalar objective.
     * </p>
     * @param function vectorial residuals function to wrap
     * @param observations observations to be compared to objective function to compute residuals
     * @param scale scaling matrix
     * @throws DimensionMismatchException if the observations vector and the scale
     * matrix dimensions do not match (objective function dimension is checked only when
     * the {@link #value(double[])} method is called)
     */
    public LeastSquaresConverter(final MultivariateVectorFunction function, final double[] observations, final RealMatrix scale) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.LeastSquaresConverter_141");
        if (ROR_not_equals(observations.length, scale.getColumnDimension(), "org.apache.commons.math3.optimization.LeastSquaresConverter.LeastSquaresConverter_141", _mut72050, _mut72051, _mut72052, _mut72053, _mut72054)) {
            throw new DimensionMismatchException(observations.length, scale.getColumnDimension());
        }
        this.function = function;
        this.observations = observations.clone();
        this.weights = null;
        this.scale = scale.copy();
    }

    /**
     * {@inheritDoc}
     */
    public double value(final double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.value_153");
        // compute residuals
        final double[] residuals = function.value(point);
        if (ROR_not_equals(residuals.length, observations.length, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72055, _mut72056, _mut72057, _mut72058, _mut72059)) {
            throw new DimensionMismatchException(residuals.length, observations.length);
        }
        for (int i = 0; ROR_less(i, residuals.length, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72060, _mut72061, _mut72062, _mut72063, _mut72064); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.value_153");
            residuals[i] -= observations[i];
        }
        // compute sum of squares
        double sumSquares = 0;
        if (weights != null) {
            for (int i = 0; ROR_less(i, residuals.length, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72081, _mut72082, _mut72083, _mut72084, _mut72085); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.value_153");
                final double ri = residuals[i];
                sumSquares += AOR_multiply(AOR_multiply(weights[i], ri, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72073, _mut72074, _mut72075, _mut72076), ri, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72077, _mut72078, _mut72079, _mut72080);
            }
        } else if (scale != null) {
            for (final double yi : scale.operate(residuals)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.value_153");
                sumSquares += AOR_multiply(yi, yi, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72069, _mut72070, _mut72071, _mut72072);
            }
        } else {
            for (final double ri : residuals) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.LeastSquaresConverter.value_153");
                sumSquares += AOR_multiply(ri, ri, "org.apache.commons.math3.optimization.LeastSquaresConverter.value_153", _mut72065, _mut72066, _mut72067, _mut72068);
            }
        }
        return sumSquares;
    }
}
