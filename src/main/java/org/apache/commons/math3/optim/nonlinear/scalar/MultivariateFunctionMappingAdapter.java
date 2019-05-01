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
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Logit;
import org.apache.commons.math3.analysis.function.Sigmoid;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Adapter for mapping bounded {@link MultivariateFunction} to unbounded ones.</p>
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
 * be called by the optimizer with unbound parameters, and the adapter will map
 * the unbounded value to the bounded range using appropriate functions like
 * {@link Sigmoid} for double bounded elements for example.
 * </p>
 * <p>
 * As the optimizer sees only unbounded parameters, it should be noted that the
 * start point or simplex expected by the optimizer should be unbounded, so the
 * user is responsible for converting his bounded point to unbounded by calling
 * {@link #boundedToUnbounded(double[])} before providing them to the optimizer.
 * For the same reason, the point returned by the {@link
 * org.apache.commons.math3.optimization.BaseMultivariateOptimizer#optimize(int,
 * MultivariateFunction, org.apache.commons.math3.optimization.GoalType, double[])}
 * method is unbounded. So to convert this point to bounded, users must call
 * {@link #unboundedToBounded(double[])} by themselves!</p>
 * <p>
 * This adapter is only a poor man solution to simple bounds optimization constraints
 * that can be used with simple optimizers like
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.SimplexOptimizer
 * SimplexOptimizer}.
 * A better solution is to use an optimizer that directly supports simple bounds like
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer
 * CMAESOptimizer} or
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.noderiv.BOBYQAOptimizer
 * BOBYQAOptimizer}.
 * One caveat of this poor-man's solution is that behavior near the bounds may be
 * numerically unstable as bounds are mapped from infinite values.
 * Another caveat is that convergence values are evaluated by the optimizer with
 * respect to unbounded variables, so there will be scales differences when
 * converted to bounded variables.
 * </p>
 *
 * @see MultivariateFunctionPenaltyAdapter
 *
 * @since 3.0
 */
public class MultivariateFunctionMappingAdapter implements MultivariateFunction {

    @Conditional
    public static boolean _mut60006 = false, _mut60007 = false, _mut60008 = false, _mut60009 = false, _mut60010 = false, _mut60011 = false, _mut60012 = false, _mut60013 = false, _mut60014 = false, _mut60015 = false, _mut60016 = false, _mut60017 = false, _mut60018 = false, _mut60019 = false, _mut60020 = false, _mut60021 = false, _mut60022 = false, _mut60023 = false, _mut60024 = false, _mut60025 = false, _mut60026 = false, _mut60027 = false, _mut60028 = false, _mut60029 = false, _mut60030 = false, _mut60031 = false, _mut60032 = false, _mut60033 = false, _mut60034 = false, _mut60035 = false, _mut60036 = false, _mut60037 = false, _mut60038 = false, _mut60039 = false, _mut60040 = false, _mut60041 = false, _mut60042 = false, _mut60043 = false, _mut60044 = false, _mut60045 = false, _mut60046 = false, _mut60047 = false, _mut60048 = false, _mut60049 = false, _mut60050 = false, _mut60051 = false;

    /**
     * Underlying bounded function.
     */
    private final MultivariateFunction bounded;

    /**
     * Mapping functions.
     */
    private final Mapper[] mappers;

    /**
     * Simple constructor.
     * @param bounded bounded function
     * @param lower lower bounds for each element of the input parameters array
     * (some elements may be set to {@code Double.NEGATIVE_INFINITY} for
     * unbounded values)
     * @param upper upper bounds for each element of the input parameters array
     * (some elements may be set to {@code Double.POSITIVE_INFINITY} for
     * unbounded values)
     * @exception DimensionMismatchException if lower and upper bounds are not
     * consistent, either according to dimension or to values
     */
    public MultivariateFunctionMappingAdapter(final MultivariateFunction bounded, final double[] lower, final double[] upper) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
        // safety checks
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        if (ROR_not_equals(lower.length, upper.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut60006, _mut60007, _mut60008, _mut60009, _mut60010)) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        for (int i = 0; ROR_less(i, lower.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut60016, _mut60017, _mut60018, _mut60019, _mut60020); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
            // note the following test is written in such a way it also fails for NaN
            if (!(ROR_greater_equals(upper[i], lower[i], "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut60011, _mut60012, _mut60013, _mut60014, _mut60015))) {
                throw new NumberIsTooSmallException(upper[i], lower[i], true);
            }
        }
        this.bounded = bounded;
        this.mappers = new Mapper[lower.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut60021, _mut60022, _mut60023, _mut60024, _mut60025); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
            if (Double.isInfinite(lower[i])) {
                if (Double.isInfinite(upper[i])) {
                    // element is unbounded, no transformation is needed
                    mappers[i] = new NoBoundsMapper();
                } else {
                    // element is simple-bounded on the upper side
                    mappers[i] = new UpperBoundMapper(upper[i]);
                }
            } else {
                if (Double.isInfinite(upper[i])) {
                    // element is simple-bounded on the lower side
                    mappers[i] = new LowerBoundMapper(lower[i]);
                } else {
                    // element is double-bounded
                    mappers[i] = new LowerUpperBoundMapper(lower[i], upper[i]);
                }
            }
        }
    }

    /**
     * Maps an array from unbounded to bounded.
     *
     * @param point Unbounded values.
     * @return the bounded values.
     */
    public double[] unboundedToBounded(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_142");
        // Map unbounded input point to bounded point.
        final double[] mapped = new double[mappers.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_142", _mut60026, _mut60027, _mut60028, _mut60029, _mut60030); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_142");
            mapped[i] = mappers[i].unboundedToBounded(point[i]);
        }
        return mapped;
    }

    /**
     * Maps an array from bounded to unbounded.
     *
     * @param point Bounded values.
     * @return the unbounded values.
     */
    public double[] boundedToUnbounded(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_158");
        // Map bounded input point to unbounded point.
        final double[] mapped = new double[mappers.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_158", _mut60031, _mut60032, _mut60033, _mut60034, _mut60035); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_158");
            mapped[i] = mappers[i].boundedToUnbounded(point[i]);
        }
        return mapped;
    }

    /**
     * Compute the underlying function value from an unbounded point.
     * <p>
     * This method simply bounds the unbounded point using the mappings
     * set up at construction and calls the underlying function using
     * the bounded point.
     * </p>
     * @param point unbounded value
     * @return underlying function value
     * @see #unboundedToBounded(double[])
     */
    public double value(double[] point) {
        return bounded.value(unboundedToBounded(point));
    }

    /**
     * Mapping interface.
     */
    private interface Mapper {

        /**
         * Maps a value from unbounded to bounded.
         *
         * @param y Unbounded value.
         * @return the bounded value.
         */
        double unboundedToBounded(double y);

        /**
         * Maps a value from bounded to unbounded.
         *
         * @param x Bounded value.
         * @return the unbounded value.
         */
        double boundedToUnbounded(double x);
    }

    /**
     * Local class for no bounds mapping.
     */
    private static class NoBoundsMapper implements Mapper {

        /**
         * {@inheritDoc}
         */
        public double unboundedToBounded(final double y) {
            return y;
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            return x;
        }
    }

    /**
     * Local class for lower bounds mapping.
     */
    private static class LowerBoundMapper implements Mapper {

        /**
         * Low bound.
         */
        private final double lower;

        /**
         * Simple constructor.
         *
         * @param lower lower bound
         */
        LowerBoundMapper(final double lower) {
            this.lower = lower;
        }

        /**
         * {@inheritDoc}
         */
        public double unboundedToBounded(final double y) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_230");
            return AOR_plus(lower, FastMath.exp(y), "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_230", _mut60036, _mut60037, _mut60038, _mut60039);
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_235");
            return FastMath.log(AOR_minus(x, lower, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_235", _mut60040, _mut60041, _mut60042, _mut60043));
        }
    }

    /**
     * Local class for upper bounds mapping.
     */
    private static class UpperBoundMapper implements Mapper {

        /**
         * Upper bound.
         */
        private final double upper;

        /**
         * Simple constructor.
         * @param upper upper bound
         */
        UpperBoundMapper(final double upper) {
            this.upper = upper;
        }

        /**
         * {@inheritDoc}
         */
        public double unboundedToBounded(final double y) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_255");
            return AOR_minus(upper, FastMath.exp(-y), "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.unboundedToBounded_255", _mut60044, _mut60045, _mut60046, _mut60047);
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_260");
            return -FastMath.log(AOR_minus(upper, x, "org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter.boundedToUnbounded_260", _mut60048, _mut60049, _mut60050, _mut60051));
        }
    }

    /**
     * Local class for lower and bounds mapping.
     */
    private static class LowerUpperBoundMapper implements Mapper {

        /**
         * Function from unbounded to bounded.
         */
        private final UnivariateFunction boundingFunction;

        /**
         * Function from bounded to unbounded.
         */
        private final UnivariateFunction unboundingFunction;

        /**
         * Simple constructor.
         *
         * @param lower lower bound
         * @param upper upper bound
         */
        LowerUpperBoundMapper(final double lower, final double upper) {
            boundingFunction = new Sigmoid(lower, upper);
            unboundingFunction = new Logit(lower, upper);
        }

        /**
         * {@inheritDoc}
         */
        public double unboundedToBounded(final double y) {
            return boundingFunction.value(y);
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            return unboundingFunction.value(x);
        }
    }
}
