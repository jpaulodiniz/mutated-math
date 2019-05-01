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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Adapter extending bounded {@link MultivariateFunction} to an unbouded
 * domain using a penalty function.</p>
 *
 * <p>
 * This adapter can be used to wrap functions subject to simple bounds on
 * parameters so they can be used by optimizers that do <em>not</em> directly
 * support simple bounds.
 * </p>
 * <p>
 * The principle is that the user function that will be wrapped will see its
 * parameters bounded as required, i.e when its {@code value} method is called
 * with argument array {@code point}, the elements array will fulfill requirement
 * {@code lower[i] <= point[i] <= upper[i]} for all i. Some of the components
 * may be unbounded or bounded only on one side if the corresponding bound is
 * set to an infinite value. The optimizer will not manage the user function by
 * itself, but it will handle this adapter and it is this adapter that will take
 * care the bounds are fulfilled. The adapter {@link #value(double[])} method will
 * be called by the optimizer with unbound parameters, and the adapter will check
 * if the parameters is within range or not. If it is in range, then the underlying
 * user function will be called, and if it is not the value of a penalty function
 * will be returned instead.
 * </p>
 * <p>
 * This adapter is only a poor-man's solution to simple bounds optimization
 * constraints that can be used with simple optimizers like
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer
 * SimplexOptimizer}.
 * A better solution is to use an optimizer that directly supports simple bounds like
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer
 * CMAESOptimizer} or
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer
 * BOBYQAOptimizer}.
 * One caveat of this poor-man's solution is that if start point or start simplex
 * is completely outside of the allowed range, only the penalty function is used,
 * and the optimizer may converge without ever entering the range.
 * </p>
 *
 * @see MultivariateFunctionMappingAdapter
 *
 * @since 3.0
 */
public class MultivariateFunctionPenaltyAdapter implements MultivariateFunction {

    @Conditional
    public static boolean _mut60140 = false, _mut60141 = false, _mut60142 = false, _mut60143 = false, _mut60144 = false, _mut60145 = false, _mut60146 = false, _mut60147 = false, _mut60148 = false, _mut60149 = false, _mut60150 = false, _mut60151 = false, _mut60152 = false, _mut60153 = false, _mut60154 = false, _mut60155 = false, _mut60156 = false, _mut60157 = false, _mut60158 = false, _mut60159 = false, _mut60160 = false, _mut60161 = false, _mut60162 = false, _mut60163 = false, _mut60164 = false, _mut60165 = false, _mut60166 = false, _mut60167 = false, _mut60168 = false, _mut60169 = false, _mut60170 = false, _mut60171 = false, _mut60172 = false, _mut60173 = false, _mut60174 = false, _mut60175 = false, _mut60176 = false, _mut60177 = false, _mut60178 = false, _mut60179 = false, _mut60180 = false, _mut60181 = false, _mut60182 = false, _mut60183 = false, _mut60184 = false, _mut60185 = false, _mut60186 = false, _mut60187 = false, _mut60188 = false, _mut60189 = false, _mut60190 = false, _mut60191 = false, _mut60192 = false, _mut60193 = false, _mut60194 = false, _mut60195 = false, _mut60196 = false, _mut60197 = false, _mut60198 = false, _mut60199 = false, _mut60200 = false, _mut60201 = false, _mut60202 = false, _mut60203 = false, _mut60204 = false, _mut60205 = false, _mut60206 = false, _mut60207 = false, _mut60208 = false, _mut60209 = false, _mut60210 = false;

    /**
     * Underlying bounded function.
     */
    private final MultivariateFunction bounded;

    /**
     * Lower bounds.
     */
    private final double[] lower;

    /**
     * Upper bounds.
     */
    private final double[] upper;

    /**
     * Penalty offset.
     */
    private final double offset;

    /**
     * Penalty scales.
     */
    private final double[] scale;

    /**
     * Simple constructor.
     * <p>
     * When the optimizer provided points are out of range, the value of the
     * penalty function will be used instead of the value of the underlying
     * function. In order for this penalty to be effective in rejecting this
     * point during the optimization process, the penalty function value should
     * be defined with care. This value is computed as:
     * <pre>
     *   penalty(point) = offset + &sum;<sub>i</sub>[scale[i] * &radic;|point[i]-boundary[i]|]
     * </pre>
     * where indices i correspond to all the components that violates their boundaries.
     * </p>
     * <p>
     * So when attempting a function minimization, offset should be larger than
     * the maximum expected value of the underlying function and scale components
     * should all be positive. When attempting a function maximization, offset
     * should be lesser than the minimum expected value of the underlying function
     * and scale components should all be negative.
     * minimization, and lesser than the minimum expected value of the underlying
     * function when attempting maximization.
     * </p>
     * <p>
     * These choices for the penalty function have two properties. First, all out
     * of range points will return a function value that is worse than the value
     * returned by any in range point. Second, the penalty is worse for large
     * boundaries violation than for small violations, so the optimizer has an hint
     * about the direction in which it should search for acceptable points.
     * </p>
     * @param bounded bounded function
     * @param lower lower bounds for each element of the input parameters array
     * (some elements may be set to {@code Double.NEGATIVE_INFINITY} for
     * unbounded values)
     * @param upper upper bounds for each element of the input parameters array
     * (some elements may be set to {@code Double.POSITIVE_INFINITY} for
     * unbounded values)
     * @param offset base offset of the penalty function
     * @param scale scale of the penalty function
     * @exception DimensionMismatchException if lower bounds, upper bounds and
     * scales are not consistent, either according to dimension or to bounadary
     * values
     */
    public MultivariateFunctionPenaltyAdapter(final MultivariateFunction bounded, final double[] lower, final double[] upper, final double offset, final double[] scale) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122");
        // safety checks
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        MathUtils.checkNotNull(scale);
        if (ROR_not_equals(lower.length, upper.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122", _mut60140, _mut60141, _mut60142, _mut60143, _mut60144)) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        if (ROR_not_equals(lower.length, scale.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122", _mut60145, _mut60146, _mut60147, _mut60148, _mut60149)) {
            throw new DimensionMismatchException(lower.length, scale.length);
        }
        for (int i = 0; ROR_less(i, lower.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122", _mut60155, _mut60156, _mut60157, _mut60158, _mut60159); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122");
            // note the following test is written in such a way it also fails for NaN
            if (!(ROR_greater_equals(upper[i], lower[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_122", _mut60150, _mut60151, _mut60152, _mut60153, _mut60154))) {
                throw new NumberIsTooSmallException(upper[i], lower[i], true);
            }
        }
        this.bounded = bounded;
        this.lower = lower.clone();
        this.upper = upper.clone();
        this.offset = offset;
        this.scale = scale.clone();
    }

    /**
     * Computes the underlying function value from an unbounded point.
     * <p>
     * This method simply returns the value of the underlying function
     * if the unbounded point already fulfills the bounds, and compute
     * a replacement value using the offset and scale if bounds are
     * violated, without calling the function at all.
     * </p>
     * @param point unbounded point
     * @return either underlying function value or penalty function value
     */
    public double value(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161");
        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60206, _mut60207, _mut60208, _mut60209, _mut60210); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161");
            if ((_mut60170 ? ((ROR_less(point[i], lower[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60160, _mut60161, _mut60162, _mut60163, _mut60164)) && (ROR_greater(point[i], upper[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60165, _mut60166, _mut60167, _mut60168, _mut60169))) : ((ROR_less(point[i], lower[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60160, _mut60161, _mut60162, _mut60163, _mut60164)) || (ROR_greater(point[i], upper[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60165, _mut60166, _mut60167, _mut60168, _mut60169))))) {
                // bound violation starting at this component
                double sum = 0;
                for (int j = i; ROR_less(j, scale.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60197, _mut60198, _mut60199, _mut60200, _mut60201); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161");
                    final double overshoot;
                    if (ROR_less(point[j], lower[j], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60171, _mut60172, _mut60173, _mut60174, _mut60175)) {
                        overshoot = AOR_multiply(scale[j], (AOR_minus(lower[j], point[j], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60189, _mut60190, _mut60191, _mut60192)), "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60193, _mut60194, _mut60195, _mut60196);
                    } else if (ROR_greater(point[j], upper[j], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60176, _mut60177, _mut60178, _mut60179, _mut60180)) {
                        overshoot = AOR_multiply(scale[j], (AOR_minus(point[j], upper[j], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60181, _mut60182, _mut60183, _mut60184)), "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60185, _mut60186, _mut60187, _mut60188);
                    } else {
                        overshoot = 0;
                    }
                    sum += FastMath.sqrt(overshoot);
                }
                return AOR_plus(offset, sum, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter.value_161", _mut60202, _mut60203, _mut60204, _mut60205);
            }
        }
        // domain of the underlying function
        return bounded.value(point);
    }
}
