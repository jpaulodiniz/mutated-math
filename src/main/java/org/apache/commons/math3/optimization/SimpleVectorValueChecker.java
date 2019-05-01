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
 * function values is smaller than another threshold for all vectors elements.
 * <br/>
 * The {@link #converged(int,PointVectorValuePair,PointVectorValuePair) converged}
 * method will also return {@code true} if the number of iterations has been set
 * (see {@link #SimpleVectorValueChecker(double,double,int) this constructor}).
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class SimpleVectorValueChecker extends AbstractConvergenceChecker<PointVectorValuePair> {

    @Conditional
    public static boolean _mut72086 = false, _mut72087 = false, _mut72088 = false, _mut72089 = false, _mut72090 = false, _mut72091 = false, _mut72092 = false, _mut72093 = false, _mut72094 = false, _mut72095 = false, _mut72096 = false, _mut72097 = false, _mut72098 = false, _mut72099 = false, _mut72100 = false, _mut72101 = false, _mut72102 = false, _mut72103 = false, _mut72104 = false, _mut72105 = false, _mut72106 = false, _mut72107 = false, _mut72108 = false, _mut72109 = false, _mut72110 = false, _mut72111 = false, _mut72112 = false, _mut72113 = false, _mut72114 = false, _mut72115 = false, _mut72116 = false, _mut72117 = false, _mut72118 = false, _mut72119 = false, _mut72120 = false, _mut72121 = false, _mut72122 = false, _mut72123 = false, _mut72124 = false, _mut72125 = false;

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
     * Build an instance with default thresholds.
     * @deprecated See {@link AbstractConvergenceChecker#AbstractConvergenceChecker()}
     */
    @Deprecated
    public SimpleVectorValueChecker() {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimpleVectorValueChecker.SimpleVectorValueChecker_96");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.SimpleVectorValueChecker_96", _mut72086, _mut72087, _mut72088, _mut72089, _mut72090)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123");
        if ((_mut72101 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72091, _mut72092, _mut72093, _mut72094, _mut72095) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72096, _mut72097, _mut72098, _mut72099, _mut72100)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72091, _mut72092, _mut72093, _mut72094, _mut72095) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72096, _mut72097, _mut72098, _mut72099, _mut72100)))) {
            return true;
        }
        final double[] p = previous.getValueRef();
        final double[] c = current.getValueRef();
        for (int i = 0; ROR_less(i, p.length, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72121, _mut72122, _mut72123, _mut72124, _mut72125); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123");
            final double pi = p[i];
            final double ci = c[i];
            final double difference = FastMath.abs(AOR_minus(pi, ci, "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72102, _mut72103, _mut72104, _mut72105));
            final double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
            if ((_mut72120 ? (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72106, _mut72107, _mut72108, _mut72109), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72110, _mut72111, _mut72112, _mut72113, _mut72114) || ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72115, _mut72116, _mut72117, _mut72118, _mut72119)) : (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72106, _mut72107, _mut72108, _mut72109), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72110, _mut72111, _mut72112, _mut72113, _mut72114) && ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimpleVectorValueChecker.converged_123", _mut72115, _mut72116, _mut72117, _mut72118, _mut72119)))) {
                return false;
            }
        }
        return true;
    }
}
