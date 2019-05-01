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
package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class LeastSquaresConverter implements MultivariateFunction {

    @Conditional
    public static boolean _mut60211 = false, _mut60212 = false, _mut60213 = false, _mut60214 = false, _mut60215 = false, _mut60216 = false, _mut60217 = false, _mut60218 = false, _mut60219 = false, _mut60220 = false, _mut60221 = false, _mut60222 = false, _mut60223 = false, _mut60224 = false, _mut60225 = false, _mut60226 = false, _mut60227 = false, _mut60228 = false, _mut60229 = false, _mut60230 = false, _mut60231 = false, _mut60232 = false, _mut60233 = false, _mut60234 = false, _mut60235 = false, _mut60236 = false, _mut60237 = false, _mut60238 = false, _mut60239 = false, _mut60240 = false, _mut60241 = false, _mut60242 = false, _mut60243 = false, _mut60244 = false, _mut60245 = false, _mut60246 = false, _mut60247 = false, _mut60248 = false, _mut60249 = false, _mut60250 = false, _mut60251 = false;

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
     * Builds a simple converter for uncorrelated residuals with identical
     * weights.
     *
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
     * Builds a simple converter for uncorrelated residuals with the
     * specified weights.
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
     *
     * @param function vectorial residuals function to wrap
     * @param observations observations to be compared to objective function to compute residuals
     * @param weights weights to apply to the residuals
     * @throws DimensionMismatchException if the observations vector and the weights
     * vector dimensions do not match (objective function dimension is checked only when
     * the {@link #value(double[])} method is called)
     */
    public LeastSquaresConverter(final MultivariateVectorFunction function, final double[] observations, final double[] weights) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.LeastSquaresConverter_110");
        if (ROR_not_equals(observations.length, weights.length, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.LeastSquaresConverter_110", _mut60211, _mut60212, _mut60213, _mut60214, _mut60215)) {
            throw new DimensionMismatchException(observations.length, weights.length);
        }
        this.function = function;
        this.observations = observations.clone();
        this.weights = weights.clone();
        this.scale = null;
    }

    /**
     * Builds a simple converter for correlated residuals with the
     * specified weights.
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
     *
     * @param function vectorial residuals function to wrap
     * @param observations observations to be compared to objective function to compute residuals
     * @param scale scaling matrix
     * @throws DimensionMismatchException if the observations vector and the scale
     * matrix dimensions do not match (objective function dimension is checked only when
     * the {@link #value(double[])} method is called)
     */
    public LeastSquaresConverter(final MultivariateVectorFunction function, final double[] observations, final RealMatrix scale) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.LeastSquaresConverter_144");
        if (ROR_not_equals(observations.length, scale.getColumnDimension(), "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.LeastSquaresConverter_144", _mut60216, _mut60217, _mut60218, _mut60219, _mut60220)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157");
        // compute residuals
        final double[] residuals = function.value(point);
        if (ROR_not_equals(residuals.length, observations.length, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60221, _mut60222, _mut60223, _mut60224, _mut60225)) {
            throw new DimensionMismatchException(residuals.length, observations.length);
        }
        for (int i = 0; ROR_less(i, residuals.length, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60226, _mut60227, _mut60228, _mut60229, _mut60230); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157");
            residuals[i] -= observations[i];
        }
        // compute sum of squares
        double sumSquares = 0;
        if (weights != null) {
            for (int i = 0; ROR_less(i, residuals.length, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60247, _mut60248, _mut60249, _mut60250, _mut60251); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157");
                final double ri = residuals[i];
                sumSquares += AOR_multiply(AOR_multiply(weights[i], ri, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60239, _mut60240, _mut60241, _mut60242), ri, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60243, _mut60244, _mut60245, _mut60246);
            }
        } else if (scale != null) {
            for (final double yi : scale.operate(residuals)) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157");
                sumSquares += AOR_multiply(yi, yi, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60235, _mut60236, _mut60237, _mut60238);
            }
        } else {
            for (final double ri : residuals) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157");
                sumSquares += AOR_multiply(ri, ri, "org.apache.commons.math3.optim.nonlinear.scalar.LeastSquaresConverter.value_157", _mut60231, _mut60232, _mut60233, _mut60234);
            }
        }
        return sumSquares;
    }
}
