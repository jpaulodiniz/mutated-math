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
import org.apache.commons.math3.util.Pair;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Simple implementation of the {@link ConvergenceChecker} interface using
 * only point coordinates.
 *
 * Convergence is considered to have been reached if either the relative
 * difference between each point coordinate are smaller than a threshold
 * or if either the absolute difference between the point coordinates are
 * smaller than another threshold.
 * <br/>
 * The {@link #converged(int,Pair,Pair) converged} method will also return
 * {@code true} if the number of iterations has been set (see
 * {@link #SimplePointChecker(double,double,int) this constructor}).
 *
 * @param <PAIR> Type of the (point, value) pair.
 * The type of the "value" part of the pair (not used by this class).
 *
 * @since 3.0
 */
public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>> extends AbstractConvergenceChecker<PAIR> {

    @Conditional
    public static boolean _mut59966 = false, _mut59967 = false, _mut59968 = false, _mut59969 = false, _mut59970 = false, _mut59971 = false, _mut59972 = false, _mut59973 = false, _mut59974 = false, _mut59975 = false, _mut59976 = false, _mut59977 = false, _mut59978 = false, _mut59979 = false, _mut59980 = false, _mut59981 = false, _mut59982 = false, _mut59983 = false, _mut59984 = false, _mut59985 = false, _mut59986 = false, _mut59987 = false, _mut59988 = false, _mut59989 = false, _mut59990 = false, _mut59991 = false, _mut59992 = false, _mut59993 = false, _mut59994 = false, _mut59995 = false, _mut59996 = false, _mut59997 = false, _mut59998 = false, _mut59999 = false, _mut60000 = false, _mut60001 = false, _mut60002 = false, _mut60003 = false, _mut60004 = false, _mut60005 = false;

    /**
     * If {@link #maxIterationCount} is set to this value, the number of
     * iterations will never cause {@link #converged(int, Pair, Pair)}
     * to return {@code true}.
     */
    private static final int ITERATION_CHECK_DISABLED = -1;

    /**
     * Number of iterations after which the
     * {@link #converged(int, Pair, Pair)} method
     * will return true (unless the check is disabled).
     */
    private final int maxIterationCount;

    /**
     * Build an instance with specified thresholds.
     * In order to perform only relative checks, the absolute tolerance
     * must be set to a negative value. In order to perform only absolute
     * checks, the relative tolerance must be set to a negative value.
     *
     * @param relativeThreshold relative tolerance threshold
     * @param absoluteThreshold absolute tolerance threshold
     */
    public SimplePointChecker(final double relativeThreshold, final double absoluteThreshold) {
        super(relativeThreshold, absoluteThreshold);
        maxIterationCount = ITERATION_CHECK_DISABLED;
    }

    /**
     * Builds an instance with specified thresholds.
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
    public SimplePointChecker(final double relativeThreshold, final double absoluteThreshold, final int maxIter) {
        super(relativeThreshold, absoluteThreshold);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimplePointChecker.SimplePointChecker_84");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optim.SimplePointChecker.SimplePointChecker_84", _mut59966, _mut59967, _mut59968, _mut59969, _mut59970)) {
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
    public boolean converged(final int iteration, final PAIR previous, final PAIR current) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimplePointChecker.converged_111");
        if ((_mut59981 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59971, _mut59972, _mut59973, _mut59974, _mut59975) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59976, _mut59977, _mut59978, _mut59979, _mut59980)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59971, _mut59972, _mut59973, _mut59974, _mut59975) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59976, _mut59977, _mut59978, _mut59979, _mut59980)))) {
            return true;
        }
        final double[] p = previous.getKey();
        final double[] c = current.getKey();
        for (int i = 0; ROR_less(i, p.length, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut60001, _mut60002, _mut60003, _mut60004, _mut60005); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.SimplePointChecker.converged_111");
            final double pi = p[i];
            final double ci = c[i];
            final double difference = FastMath.abs(AOR_minus(pi, ci, "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59982, _mut59983, _mut59984, _mut59985));
            final double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
            if ((_mut60000 ? (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59986, _mut59987, _mut59988, _mut59989), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59990, _mut59991, _mut59992, _mut59993, _mut59994) || ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59995, _mut59996, _mut59997, _mut59998, _mut59999)) : (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59986, _mut59987, _mut59988, _mut59989), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59990, _mut59991, _mut59992, _mut59993, _mut59994) && ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optim.SimplePointChecker.converged_111", _mut59995, _mut59996, _mut59997, _mut59998, _mut59999)))) {
                return false;
            }
        }
        return true;
    }
}
