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
package org.apache.commons.math3.optimization.direct;

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

@Deprecated
public class MultivariateFunctionMappingAdapter implements MultivariateFunction {

    @Conditional
    public static boolean _mut74613 = false, _mut74614 = false, _mut74615 = false, _mut74616 = false, _mut74617 = false, _mut74618 = false, _mut74619 = false, _mut74620 = false, _mut74621 = false, _mut74622 = false, _mut74623 = false, _mut74624 = false, _mut74625 = false, _mut74626 = false, _mut74627 = false, _mut74628 = false, _mut74629 = false, _mut74630 = false, _mut74631 = false, _mut74632 = false, _mut74633 = false, _mut74634 = false, _mut74635 = false, _mut74636 = false, _mut74637 = false, _mut74638 = false, _mut74639 = false, _mut74640 = false, _mut74641 = false, _mut74642 = false, _mut74643 = false, _mut74644 = false, _mut74645 = false, _mut74646 = false, _mut74647 = false, _mut74648 = false, _mut74649 = false, _mut74650 = false, _mut74651 = false, _mut74652 = false, _mut74653 = false, _mut74654 = false, _mut74655 = false, _mut74656 = false, _mut74657 = false, _mut74658 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
        // safety checks
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        if (ROR_not_equals(lower.length, upper.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut74613, _mut74614, _mut74615, _mut74616, _mut74617)) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        for (int i = 0; ROR_less(i, lower.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut74623, _mut74624, _mut74625, _mut74626, _mut74627); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
            // note the following test is written in such a way it also fails for NaN
            if (!(ROR_greater_equals(upper[i], lower[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut74618, _mut74619, _mut74620, _mut74621, _mut74622))) {
                throw new NumberIsTooSmallException(upper[i], lower[i], true);
            }
        }
        this.bounded = bounded;
        this.mappers = new Mapper[lower.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98", _mut74628, _mut74629, _mut74630, _mut74631, _mut74632); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.MultivariateFunctionMappingAdapter_98");
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
     * Map an array from unbounded to bounded.
     * @param point unbounded value
     * @return bounded value
     */
    public double[] unboundedToBounded(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_142");
        // map unbounded input point to bounded point
        final double[] mapped = new double[mappers.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_142", _mut74633, _mut74634, _mut74635, _mut74636, _mut74637); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_142");
            mapped[i] = mappers[i].unboundedToBounded(point[i]);
        }
        return mapped;
    }

    /**
     * Map an array from bounded to unbounded.
     * @param point bounded value
     * @return unbounded value
     */
    public double[] boundedToUnbounded(double[] point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_158");
        // map bounded input point to unbounded point
        final double[] mapped = new double[mappers.length];
        for (int i = 0; ROR_less(i, mappers.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_158", _mut74638, _mut74639, _mut74640, _mut74641, _mut74642); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_158");
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
         * Map a value from unbounded to bounded.
         * @param y unbounded value
         * @return bounded value
         */
        double unboundedToBounded(double y);

        /**
         * Map a value from bounded to unbounded.
         * @param x bounded value
         * @return unbounded value
         */
        double boundedToUnbounded(double x);
    }

    /**
     * Local class for no bounds mapping.
     */
    private static class NoBoundsMapper implements Mapper {

        /**
         * Simple constructor.
         */
        NoBoundsMapper() {
        }

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
         * @param lower lower bound
         */
        LowerBoundMapper(final double lower) {
            this.lower = lower;
        }

        /**
         * {@inheritDoc}
         */
        public double unboundedToBounded(final double y) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_235");
            return AOR_plus(lower, FastMath.exp(y), "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_235", _mut74643, _mut74644, _mut74645, _mut74646);
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_240");
            return FastMath.log(AOR_minus(x, lower, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_240", _mut74647, _mut74648, _mut74649, _mut74650));
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
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_260");
            return AOR_minus(upper, FastMath.exp(-y), "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.unboundedToBounded_260", _mut74651, _mut74652, _mut74653, _mut74654);
        }

        /**
         * {@inheritDoc}
         */
        public double boundedToUnbounded(final double x) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_265");
            return -FastMath.log(AOR_minus(upper, x, "org.apache.commons.math3.optimization.direct.MultivariateFunctionMappingAdapter.boundedToUnbounded_265", _mut74655, _mut74656, _mut74657, _mut74658));
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
