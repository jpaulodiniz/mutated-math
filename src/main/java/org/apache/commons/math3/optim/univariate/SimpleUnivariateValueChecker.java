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
package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optim.AbstractConvergenceChecker;
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
 * @since 3.1
 */
public class SimpleUnivariateValueChecker extends AbstractConvergenceChecker<UnivariatePointValuePair> {

    @Conditional
    public static boolean _mut58804 = false, _mut58805 = false, _mut58806 = false, _mut58807 = false, _mut58808 = false, _mut58809 = false, _mut58810 = false, _mut58811 = false, _mut58812 = false, _mut58813 = false, _mut58814 = false, _mut58815 = false, _mut58816 = false, _mut58817 = false, _mut58818 = false, _mut58819 = false, _mut58820 = false, _mut58821 = false, _mut58822 = false, _mut58823 = false, _mut58824 = false, _mut58825 = false, _mut58826 = false, _mut58827 = false, _mut58828 = false, _mut58829 = false, _mut58830 = false, _mut58831 = false, _mut58832 = false, _mut58833 = false, _mut58834 = false, _mut58835 = false, _mut58836 = false, _mut58837 = false, _mut58838 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.SimpleUnivariateValueChecker_85");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.SimpleUnivariateValueChecker_85", _mut58804, _mut58805, _mut58806, _mut58807, _mut58808)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112");
        if ((_mut58819 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58809, _mut58810, _mut58811, _mut58812, _mut58813) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58814, _mut58815, _mut58816, _mut58817, _mut58818)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58809, _mut58810, _mut58811, _mut58812, _mut58813) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58814, _mut58815, _mut58816, _mut58817, _mut58818)))) {
            return true;
        }
        final double p = previous.getValue();
        final double c = current.getValue();
        final double difference = FastMath.abs(AOR_minus(p, c, "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58820, _mut58821, _mut58822, _mut58823));
        final double size = FastMath.max(FastMath.abs(p), FastMath.abs(c));
        return (_mut58838 ? (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58824, _mut58825, _mut58826, _mut58827), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58828, _mut58829, _mut58830, _mut58831, _mut58832) && ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58833, _mut58834, _mut58835, _mut58836, _mut58837)) : (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58824, _mut58825, _mut58826, _mut58827), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58828, _mut58829, _mut58830, _mut58831, _mut58832) || ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.univariate.SimpleUnivariateValueChecker.converged_112", _mut58833, _mut58834, _mut58835, _mut58836, _mut58837)));
    }
}
