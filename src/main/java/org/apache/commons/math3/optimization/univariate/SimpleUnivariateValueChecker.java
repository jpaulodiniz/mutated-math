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
package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optimization.AbstractConvergenceChecker;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Simple implementation of the
 * {@link org.apache.commons.math3.optimization.ConvergenceChecker} interface
 * that uses only objective function values.
 *
 * Convergence is considered to have been reached if either the relative
 * difference between the objective function values is smaller than a
 * threshold or if either the absolute difference between the objective
 * function values is smaller than another threshold.
 * <br/>
 * The {@link #converged(int,UnivariatePointValuePair,UnivariatePointValuePair)
 * converged} method will also return {@code true} if the number of iterations
 * has been set (see {@link #SimpleUnivariateValueChecker(double,double,int)
 * this constructor}).
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.1
 */
@Deprecated
public class SimpleUnivariateValueChecker extends AbstractConvergenceChecker<UnivariatePointValuePair> {

    @Conditional
    public static boolean _mut70396 = false, _mut70397 = false, _mut70398 = false, _mut70399 = false, _mut70400 = false, _mut70401 = false, _mut70402 = false, _mut70403 = false, _mut70404 = false, _mut70405 = false, _mut70406 = false, _mut70407 = false, _mut70408 = false, _mut70409 = false, _mut70410 = false, _mut70411 = false, _mut70412 = false, _mut70413 = false, _mut70414 = false, _mut70415 = false, _mut70416 = false, _mut70417 = false, _mut70418 = false, _mut70419 = false, _mut70420 = false, _mut70421 = false, _mut70422 = false, _mut70423 = false, _mut70424 = false, _mut70425 = false, _mut70426 = false, _mut70427 = false, _mut70428 = false, _mut70429 = false, _mut70430 = false;

    /**
     * If {@link #maxIterationCount} is set to this value, the number of
     * iterations will never cause
     * {@link #converged(int,UnivariatePointValuePair,UnivariatePointValuePair)}
     * to return {@code true}.
     */
    private static final int ITERATION_CHECK_DISABLED = -1;

    /**
     * Number of iterations after which the
     * {@link #converged(int,UnivariatePointValuePair,UnivariatePointValuePair)}
     * method will return true (unless the check is disabled).
     */
    private final int maxIterationCount;

    /**
     * Build an instance with default thresholds.
     * @deprecated See {@link AbstractConvergenceChecker#AbstractConvergenceChecker()}
     */
    @Deprecated
    public SimpleUnivariateValueChecker() {
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
    public SimpleUnivariateValueChecker(final double relativeThreshold, final double absoluteThreshold) {
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
    public SimpleUnivariateValueChecker(final double relativeThreshold, final double absoluteThreshold, final int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.SimpleUnivariateValueChecker_97");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.SimpleUnivariateValueChecker_97", _mut70396, _mut70397, _mut70398, _mut70399, _mut70400)) {
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
    public boolean converged(final int iteration, final UnivariatePointValuePair previous, final UnivariatePointValuePair current) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124");
        if ((_mut70411 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70401, _mut70402, _mut70403, _mut70404, _mut70405) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70406, _mut70407, _mut70408, _mut70409, _mut70410)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70401, _mut70402, _mut70403, _mut70404, _mut70405) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70406, _mut70407, _mut70408, _mut70409, _mut70410)))) {
            return true;
        }
        final double p = previous.getValue();
        final double c = current.getValue();
        final double difference = FastMath.abs(AOR_minus(p, c, "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70412, _mut70413, _mut70414, _mut70415));
        final double size = FastMath.max(FastMath.abs(p), FastMath.abs(c));
        return (_mut70430 ? (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70416, _mut70417, _mut70418, _mut70419), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70420, _mut70421, _mut70422, _mut70423, _mut70424) && ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70425, _mut70426, _mut70427, _mut70428, _mut70429)) : (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70416, _mut70417, _mut70418, _mut70419), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70420, _mut70421, _mut70422, _mut70423, _mut70424) || ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker.converged_124", _mut70425, _mut70426, _mut70427, _mut70428, _mut70429)));
    }
}
