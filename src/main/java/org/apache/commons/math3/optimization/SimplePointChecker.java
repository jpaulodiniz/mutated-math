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
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public class SimplePointChecker<PAIR extends Pair<double[], ? extends Object>> extends AbstractConvergenceChecker<PAIR> {

    @Conditional
    public static boolean _mut72000 = false, _mut72001 = false, _mut72002 = false, _mut72003 = false, _mut72004 = false, _mut72005 = false, _mut72006 = false, _mut72007 = false, _mut72008 = false, _mut72009 = false, _mut72010 = false, _mut72011 = false, _mut72012 = false, _mut72013 = false, _mut72014 = false, _mut72015 = false, _mut72016 = false, _mut72017 = false, _mut72018 = false, _mut72019 = false, _mut72020 = false, _mut72021 = false, _mut72022 = false, _mut72023 = false, _mut72024 = false, _mut72025 = false, _mut72026 = false, _mut72027 = false, _mut72028 = false, _mut72029 = false, _mut72030 = false, _mut72031 = false, _mut72032 = false, _mut72033 = false, _mut72034 = false, _mut72035 = false, _mut72036 = false, _mut72037 = false, _mut72038 = false, _mut72039 = false;

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
     * Build an instance with default threshold.
     * @deprecated See {@link AbstractConvergenceChecker#AbstractConvergenceChecker()}
     */
    @Deprecated
    public SimplePointChecker() {
        maxIterationCount = ITERATION_CHECK_DISABLED;
    }

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimplePointChecker.SimplePointChecker_96");
        if (ROR_less_equals(maxIter, 0, "org.apache.commons.math3.optimization.SimplePointChecker.SimplePointChecker_96", _mut72000, _mut72001, _mut72002, _mut72003, _mut72004)) {
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimplePointChecker.converged_123");
        if ((_mut72015 ? (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72005, _mut72006, _mut72007, _mut72008, _mut72009) || ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72010, _mut72011, _mut72012, _mut72013, _mut72014)) : (ROR_not_equals(maxIterationCount, ITERATION_CHECK_DISABLED, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72005, _mut72006, _mut72007, _mut72008, _mut72009) && ROR_greater_equals(iteration, maxIterationCount, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72010, _mut72011, _mut72012, _mut72013, _mut72014)))) {
            return true;
        }
        final double[] p = previous.getKey();
        final double[] c = current.getKey();
        for (int i = 0; ROR_less(i, p.length, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72035, _mut72036, _mut72037, _mut72038, _mut72039); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.SimplePointChecker.converged_123");
            final double pi = p[i];
            final double ci = c[i];
            final double difference = FastMath.abs(AOR_minus(pi, ci, "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72016, _mut72017, _mut72018, _mut72019));
            final double size = FastMath.max(FastMath.abs(pi), FastMath.abs(ci));
            if ((_mut72034 ? (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72020, _mut72021, _mut72022, _mut72023), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72024, _mut72025, _mut72026, _mut72027, _mut72028) || ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72029, _mut72030, _mut72031, _mut72032, _mut72033)) : (ROR_greater(difference, AOR_multiply(size, getRelativeThreshold(), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72020, _mut72021, _mut72022, _mut72023), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72024, _mut72025, _mut72026, _mut72027, _mut72028) && ROR_greater(difference, getAbsoluteThreshold(), "org.apache.commons.math3.optimization.SimplePointChecker.converged_123", _mut72029, _mut72030, _mut72031, _mut72032, _mut72033)))) {
                return false;
            }
        }
        return true;
    }
}
