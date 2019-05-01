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
package org.apache.commons.math3.optim;

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
 * @since 3.0
 */
public class SimpleValueChecker extends AbstractConvergenceChecker<PointValuePair> {

    @Conditional
    public static boolean _mut66797 = false, _mut66798 = false, _mut66799 = false, _mut66800 = false, _mut66801 = false, _mut66802 = false, _mut66803 = false, _mut66804 = false, _mut66805 = false, _mut66806 = false, _mut66807 = false, _mut66808 = false, _mut66809 = false, _mut66810 = false, _mut66811 = false, _mut66812 = false, _mut66813 = false, _mut66814 = false, _mut66815 = false, _mut66816 = false, _mut66817 = false, _mut66818 = false, _mut66819 = false, _mut66820 = false, _mut66821 = false, _mut66822 = false, _mut66823 = false, _mut66824 = false, _mut66825 = false, _mut66826 = false, _mut66827 = false, _mut66828 = false, _mut66829 = false, _mut66830 = false, _mut66831 = false;

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimpleValueChecker.SimpleValueChecker_83");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optim.SimpleValueChecker.SimpleValueChecker_83", _mut66797, _mut66798, _mut66799, _mut66800, _mut66801)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimpleValueChecker.converged_110");
        if ((_mut66812 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66802, _mut66803, _mut66804, _mut66805, _mut66806) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66807, _mut66808, _mut66809, _mut66810, _mut66811)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66802, _mut66803, _mut66804, _mut66805, _mut66806) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66807, _mut66808, _mut66809, _mut66810, _mut66811)))) {
            return true;
        }
        final double p = previous.getValue();
        final double c = current.getValue();
        final double difference = FastMath.abs(AOR_minus(p, c, "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66813, _mut66814, _mut66815, _mut66816));
        final double size = FastMath.max(FastMath.abs(p), FastMath.abs(c));
        return (_mut66831 ? (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66817, _mut66818, _mut66819, _mut66820), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66821, _mut66822, _mut66823, _mut66824, _mut66825) && ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66826, _mut66827, _mut66828, _mut66829, _mut66830)) : (ROR_less_equals(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66817, _mut66818, _mut66819, _mut66820), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66821, _mut66822, _mut66823, _mut66824, _mut66825) || ROR_less_equals(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimpleValueChecker.converged_110", _mut66826, _mut66827, _mut66828, _mut66829, _mut66830)));
    }
}
