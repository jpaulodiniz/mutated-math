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

import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for all convergence checker implementations.
 *
 * @param <PAIR> Type of (point, value) pair.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 3.0
 */
@Deprecated
public abstract class AbstractConvergenceChecker<PAIR> implements ConvergenceChecker<PAIR> {

    @Conditional
    public static boolean _mut71030 = false, _mut71031 = false, _mut71032 = false, _mut71033 = false, _mut71034 = false, _mut71035 = false, _mut71036 = false, _mut71037 = false;

    /**
     * Default relative threshold.
     * @deprecated in 3.1 (to be removed in 4.0) because this value is too small
     * to be useful as a default (cf. MATH-798).
     */
    @Deprecated
    private static final double DEFAULT_RELATIVE_THRESHOLD = AOR_multiply(100, Precision.EPSILON, "org.apache.commons.math3.optimization.AbstractConvergenceChecker.getUpper_60", _mut71030, _mut71031, _mut71032, _mut71033);

    /**
     * Default absolute threshold.
     * @deprecated in 3.1 (to be removed in 4.0) because this value is too small
     * to be useful as a default (cf. MATH-798).
     */
    @Deprecated
    private static final double DEFAULT_ABSOLUTE_THRESHOLD = AOR_multiply(100, Precision.SAFE_MIN, "org.apache.commons.math3.optimization.AbstractConvergenceChecker.getUpper_60", _mut71034, _mut71035, _mut71036, _mut71037);

    /**
     * Relative tolerance threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute tolerance threshold.
     */
    private final double absoluteThreshold;

    /**
     * Build an instance with default thresholds.
     * @deprecated in 3.1 (to be removed in 4.0). Convergence thresholds are
     * problem-dependent. As this class is intended for users who want to set
     * their own convergence criterion instead of relying on an algorithm's
     * default procedure, they should also set the thresholds appropriately
     * (cf. MATH-798).
     */
    @Deprecated
    public AbstractConvergenceChecker() {
        this.relativeThreshold = DEFAULT_RELATIVE_THRESHOLD;
        this.absoluteThreshold = DEFAULT_ABSOLUTE_THRESHOLD;
    }

    /**
     * Build an instance with a specified thresholds.
     *
     * @param relativeThreshold relative tolerance threshold
     * @param absoluteThreshold absolute tolerance threshold
     */
    public AbstractConvergenceChecker(final double relativeThreshold, final double absoluteThreshold) {
        this.relativeThreshold = relativeThreshold;
        this.absoluteThreshold = absoluteThreshold;
    }

    /**
     * @return the relative threshold.
     */
    public double getRelativeThreshold() {
        return relativeThreshold;
    }

    /**
     * @return the absolute threshold.
     */
    public double getAbsoluteThreshold() {
        return absoluteThreshold;
    }

    /**
     * {@inheritDoc}
     */
    public abstract boolean converged(int iteration, PAIR previous, PAIR current);
}
