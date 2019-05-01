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

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Simple implementation of the {@link ConvergenceChecker} interface using
 * only objective function values.
 *
 * Convergence is considered to have been reached if either the relative
 * difference between the objective function values is smaller than a
 * threshold or if either the absolute difference between the objective
 * function values is smaller than another threshold.
 * <br/>
 * The {@link #converged(int,PointValuePair,PointValuePair) converged}
 * method will also return {@code true} if the number of iterations has been set
 * (see {@link #SimpleValueChecker(double,double,int) this constructor}).
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class SimpleValueChecker extends AbstractConvergenceChecker<PointValuePair> {

    @Conditional
    public static boolean _mut78939 = false, _mut78940 = false, _mut78941 = false, _mut78942 = false, _mut78943 = false, _mut78944 = false, _mut78945 = false, _mut78946 = false, _mut78947 = false, _mut78948 = false, _mut78949 = false, _mut78950 = false, _mut78951 = false, _mut78952 = false, _mut78953 = false, _mut78954 = false, _mut78955 = false, _mut78956 = false, _mut78957 = false, _mut78958 = false, _mut78959 = false, _mut78960 = false, _mut78961 = false, _mut78962 = false, _mut78963 = false, _mut78964 = false, _mut78965 = false, _mut78966 = false, _mut78967 = false, _mut78968 = false, _mut78969 = false, _mut78970 = false, _mut78971 = false, _mut78972 = false, _mut78973 = false;

    /**
     * If {@link #maxIterationCount} is set to this value, the number of
     * iterations will never cause
     * {@link #converged(int,PointValuePair,PointValuePair)}
     * to return {@code true}.
     */
    private static final int ITERATION_CHECK_DISABLED = -1;

    /**
     * Number of iterations after which the
     * {@link #converged(int,PointValuePair,PointValuePair)} method
     * will return true (unless the check is disabled).
     */
    private final int maxIterationCount;

    /**
     * Build an instance with default thresholds.
     * @deprecated See {@link AbstractConvergenceChecker#AbstractConvergenceChecker()}
     */
    @Deprecated
    public SimpleValueChecker() {
        maxIterationCount = ITERATION_CHECK_DISABLED;
    }

    /**
     * Build an instance with specified thresholds.
     *
     * In order to perform only relative checks, the absolute tolerance
     * must be set to a negative value. In order to perform only absolute
     * checks, the relative tolerance must be set to a negative value.
     *
     * @param relativeThreshold relative tolerance threshold
     * @param absoluteThreshold absolute tolerance threshold
     */
    public SimpleValueChecker(final double relativeThreshold, final double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        maxIterationCount = ITERATION_CHECK_DISABLED;
    }

    /**
     * Builds an instance with specified thresholds.
     *
     * In order to perform only relative checks, the absolute tolerance
     * must be set to a negative value. In order to perform only absolute
     * checks, the relative tolerance must be set to a negative value.
     *
     * @param relativeThreshold relative tolerance threshold
     * @param absoluteThreshold absolute tolerance threshold
     * @param maxIter Maximum iteration count.
     * @throws NotStrictlyPositiveException if {@code maxIter <= 0}.
     *
     * @since 3.1
     */
    public SimpleValueChecker(final double relativeThreshold, final double absoluteThreshold, final int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimpleValueChecker.SimpleValueChecker_94");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optimization.SimpleValueChecker.SimpleValueChecker_94", _mut78939, _mut78940, _mut78941, _mut78942, _mut78943)) {
            throw new NotStrictlyPositiveException(maxIter);
        }
        maxIterationCount = maxIter;
    }

    /**
     * Check if the optimization algorithm has converged considering the
     * last two points.
     * This method may be called several time from the same algorithm
     * iteration with different points. This can be detected by checking the
     * iteration number at each call if needed. Each time this method is
     * called, the previous and current point correspond to points with the
     * same role at each iteration, so they can be compared. As an example,
     * simplex-based algorithms call this method for all points of the simplex,
     * not only for the best or worst ones.
     *
     * @param iteration Index of current iteration
     * @param previous Best point in the previous iteration.
     * @param current Best point in the current iteration.
     * @return {@code true} if the algorithm has converged.
     */
    @Override
    public boolean converged(final int iteration, final PointValuePair previous, final PointValuePair current) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimpleValueChecker.converged_121");
        if ((_mut78954 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78944, _mut78945, _mut78946, _mut78947, _mut78948) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78949, _mut78950, _mut78951, _mut78952, _mut78953)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78944, _mut78945, _mut78946, _mut78947, _mut78948) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78949, _mut78950, _mut78951, _mut78952, _mut78953)))) {
            return true;
        }
        final double p = previous.getValue();
        final double c = current.getValue();
        final double difference = FastMath.abs(AOR_minus(p, c, "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78955, _mut78956, _mut78957, _mut78958));
        final double size = FastMath.max(FastMath.abs(p), FastMath.abs(c));
        return (_mut78973 ? (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78959, _mut78960, _mut78961, _mut78962), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78963, _mut78964, _mut78965, _mut78966, _mut78967) && ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78968, _mut78969, _mut78970, _mut78971, _mut78972)) : (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78959, _mut78960, _mut78961, _mut78962), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78963, _mut78964, _mut78965, _mut78966, _mut78967) || ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimpleValueChecker.converged_121", _mut78968, _mut78969, _mut78970, _mut78971, _mut78972)));
    }
}
