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
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

@Deprecated
public class MultivariateFunctionPenaltyAdapter implements MultivariateFunction {

    @Conditional
    public static boolean _mut74861 = false, _mut74862 = false, _mut74863 = false, _mut74864 = false, _mut74865 = false, _mut74866 = false, _mut74867 = false, _mut74868 = false, _mut74869 = false, _mut74870 = false, _mut74871 = false, _mut74872 = false, _mut74873 = false, _mut74874 = false, _mut74875 = false, _mut74876 = false, _mut74877 = false, _mut74878 = false, _mut74879 = false, _mut74880 = false, _mut74881 = false, _mut74882 = false, _mut74883 = false, _mut74884 = false, _mut74885 = false, _mut74886 = false, _mut74887 = false, _mut74888 = false, _mut74889 = false, _mut74890 = false, _mut74891 = false, _mut74892 = false, _mut74893 = false, _mut74894 = false, _mut74895 = false, _mut74896 = false, _mut74897 = false, _mut74898 = false, _mut74899 = false, _mut74900 = false, _mut74901 = false, _mut74902 = false, _mut74903 = false, _mut74904 = false, _mut74905 = false, _mut74906 = false, _mut74907 = false, _mut74908 = false, _mut74909 = false, _mut74910 = false, _mut74911 = false, _mut74912 = false, _mut74913 = false, _mut74914 = false, _mut74915 = false, _mut74916 = false, _mut74917 = false, _mut74918 = false, _mut74919 = false, _mut74920 = false, _mut74921 = false, _mut74922 = false, _mut74923 = false, _mut74924 = false, _mut74925 = false, _mut74926 = false, _mut74927 = false, _mut74928 = false, _mut74929 = false, _mut74930 = false, _mut74931 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124");
        // safety checks
        MathUtils.checkNotNull(lower);
        MathUtils.checkNotNull(upper);
        MathUtils.checkNotNull(scale);
        if (ROR_not_equals(lower.length, upper.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124", _mut74861, _mut74862, _mut74863, _mut74864, _mut74865)) {
            throw new DimensionMismatchException(lower.length, upper.length);
        }
        if (ROR_not_equals(lower.length, scale.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124", _mut74866, _mut74867, _mut74868, _mut74869, _mut74870)) {
            throw new DimensionMismatchException(lower.length, scale.length);
        }
        for (int i = 0; ROR_less(i, lower.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124", _mut74876, _mut74877, _mut74878, _mut74879, _mut74880); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124");
            // note the following test is written in such a way it also fails for NaN
            if (!(ROR_greater_equals(upper[i], lower[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.MultivariateFunctionPenaltyAdapter_124", _mut74871, _mut74872, _mut74873, _mut74874, _mut74875))) {
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
     * Compute the underlying function value from an unbounded point.
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163");
        for (int i = 0; ROR_less(i, scale.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74927, _mut74928, _mut74929, _mut74930, _mut74931); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163");
            if ((_mut74891 ? ((ROR_less(point[i], lower[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74881, _mut74882, _mut74883, _mut74884, _mut74885)) && (ROR_greater(point[i], upper[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74886, _mut74887, _mut74888, _mut74889, _mut74890))) : ((ROR_less(point[i], lower[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74881, _mut74882, _mut74883, _mut74884, _mut74885)) || (ROR_greater(point[i], upper[i], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74886, _mut74887, _mut74888, _mut74889, _mut74890))))) {
                // bound violation starting at this component
                double sum = 0;
                for (int j = i; ROR_less(j, scale.length, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74918, _mut74919, _mut74920, _mut74921, _mut74922); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163");
                    final double overshoot;
                    if (ROR_less(point[j], lower[j], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74892, _mut74893, _mut74894, _mut74895, _mut74896)) {
                        overshoot = AOR_multiply(scale[j], (AOR_minus(lower[j], point[j], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74910, _mut74911, _mut74912, _mut74913)), "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74914, _mut74915, _mut74916, _mut74917);
                    } else if (ROR_greater(point[j], upper[j], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74897, _mut74898, _mut74899, _mut74900, _mut74901)) {
                        overshoot = AOR_multiply(scale[j], (AOR_minus(point[j], upper[j], "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74902, _mut74903, _mut74904, _mut74905)), "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74906, _mut74907, _mut74908, _mut74909);
                    } else {
                        overshoot = 0;
                    }
                    sum += FastMath.sqrt(overshoot);
                }
                return AOR_plus(offset, sum, "org.apache.commons.math3.optimization.direct.MultivariateFunctionPenaltyAdapter.value_163", _mut74923, _mut74924, _mut74925, _mut74926);
            }
        }
        // domain of the underlying function
        return bounded.value(point);
    }
}
