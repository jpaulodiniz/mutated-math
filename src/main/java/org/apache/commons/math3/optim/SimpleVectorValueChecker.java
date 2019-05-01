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
 * function values is smaller than another threshold for all vectors elements.
 * <br/>
 * The {@link #converged(int,PointVectorValuePair,PointVectorValuePair) converged}
 * method will also return {@code true} if the number of iterations has been set
 * (see {@link #SimpleVectorValueChecker(double,double,int) this constructor}).
 *
 * @since 3.0
 */
public class SimpleVectorValueChecker extends AbstractConvergenceChecker<PointVectorValuePair> {

    @Conditional
    public static boolean _mut66727 = false, _mut66728 = false, _mut66729 = false, _mut66730 = false, _mut66731 = false, _mut66732 = false, _mut66733 = false, _mut66734 = false, _mut66735 = false, _mut66736 = false, _mut66737 = false, _mut66738 = false, _mut66739 = false, _mut66740 = false, _mut66741 = false, _mut66742 = false, _mut66743 = false, _mut66744 = false, _mut66745 = false, _mut66746 = false, _mut66747 = false, _mut66748 = false, _mut66749 = false, _mut66750 = false, _mut66751 = false, _mut66752 = false, _mut66753 = false, _mut66754 = false, _mut66755 = false, _mut66756 = false, _mut66757 = false, _mut66758 = false, _mut66759 = false, _mut66760 = false, _mut66761 = false, _mut66762 = false, _mut66763 = false, _mut66764 = false, _mut66765 = false, _mut66766 = false;

    /**
     * If {@link #maxIterationCount} is set to this value, the number of
     * iterations will never cause
     * {@link #converged(int,PointVectorValuePair,PointVectorValuePair)}
     * to return {@code true}.
     */
    private static final int ITERATION_CHECK_DISABLED = -1;

    /**
     * Number of iterations after which the
     * {@link #converged(int,PointVectorValuePair,PointVectorValuePair)} method
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
    public SimpleVectorValueChecker(final double relativeThreshold, final double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        maxIterationCount = ITERATION_CHECK_DISABLED;
    }

    /**
     * Builds an instance with specified tolerance thresholds and
     * iteration count.
     *
     * In order to perform only relative checks, the absolute tolerance
     * must be set to a negative value. In order to perform only absolute
     * checks, the relative tolerance must be set to a negative value.
     *
     * @param relativeThreshold Relative tolerance threshold.
     * @param absoluteThreshold Absolute tolerance threshold.
     * @param maxIter Maximum iteration count.
     * @throws NotStrictlyPositiveException if {@code maxIter <= 0}.
     *
     * @since 3.1
     */
    public SimpleVectorValueChecker(final double relativeThreshold, final double absoluteThreshold, final int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimpleVectorValueChecker.SimpleVectorValueChecker_85");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optim.SimpleVectorValueChecker.SimpleVectorValueChecker_85", _mut66727, _mut66728, _mut66729, _mut66730, _mut66731)) {
            throw new NotStrictlyPositiveException(maxIter);
        }
        maxIterationCount = maxIter;
    }

    /**
     * Check if the optimization algorithm has converged considering the
     * last two points.
     * This method may be called several times from the same algorithm
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
     * @return {@code true} if the arguments satify the convergence criterion.
     */
    @Override
    public boolean converged(final int iteration, final PointVectorValuePair previous, final PointVectorValuePair current) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112");
        if ((_mut66742 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66732, _mut66733, _mut66734, _mut66735, _mut66736) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66737, _mut66738, _mut66739, _mut66740, _mut66741)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66732, _mut66733, _mut66734, _mut66735, _mut66736) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66737, _mut66738, _mut66739, _mut66740, _mut66741)))) {
            return true;
        }
        final double[] p = previous.getValueRef();
        final double[] c = current.getValueRef();
        for (int i = 0; ROR_less(i, p.length, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66762, _mut66763, _mut66764, _mut66765, _mut66766); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112");
            final double pi = p[i];
            final double ci = c[i];
            final double difference = FastMath.abs(AOR_minus(pi, ci, "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66743, _mut66744, _mut66745, _mut66746));
            final double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
            if ((_mut66761 ? (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66747, _mut66748, _mut66749, _mut66750), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66751, _mut66752, _mut66753, _mut66754, _mut66755) || ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66756, _mut66757, _mut66758, _mut66759, _mut66760)) : (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66747, _mut66748, _mut66749, _mut66750), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66751, _mut66752, _mut66753, _mut66754, _mut66755) && ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimpleVectorValueChecker.converged_112", _mut66756, _mut66757, _mut66758, _mut66759, _mut66760)))) {
                return false;
            }
        }
        return true;
    }
}
