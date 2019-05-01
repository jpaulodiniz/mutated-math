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
package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the constant real distribution.
 *
 * @since 3.4
 */
public class ConstantRealDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut56014 = false, _mut56015 = false, _mut56016 = false, _mut56017 = false, _mut56018 = false, _mut56019 = false, _mut56020 = false, _mut56021 = false, _mut56022 = false, _mut56023 = false, _mut56024 = false, _mut56025 = false, _mut56026 = false, _mut56027 = false, _mut56028 = false, _mut56029 = false, _mut56030 = false, _mut56031 = false, _mut56032 = false, _mut56033 = false, _mut56034 = false;

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = -4157745166772046273L;

    /**
     * Constant value of the distribution
     */
    private final double value;

    /**
     * Create a constant real distribution with the given value.
     *
     * @param value the constant value of this distribution
     */
    public ConstantRealDistribution(double value) {
        // Avoid creating RandomGenerator
        super(null);
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ConstantRealDistribution.density_46");
        return ROR_equals(x, value, "org.apache.commons.math3.distribution.ConstantRealDistribution.density_46", _mut56014, _mut56015, _mut56016, _mut56017, _mut56018) ? 1 : 0;
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ConstantRealDistribution.cumulativeProbability_51");
        return ROR_less(x, value, "org.apache.commons.math3.distribution.ConstantRealDistribution.cumulativeProbability_51", _mut56019, _mut56020, _mut56021, _mut56022, _mut56023) ? 0 : 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ConstantRealDistribution.inverseCumulativeProbability_56");
        if ((_mut56034 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.ConstantRealDistribution.inverseCumulativeProbability_56", _mut56024, _mut56025, _mut56026, _mut56027, _mut56028) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.ConstantRealDistribution.inverseCumulativeProbability_56", _mut56029, _mut56030, _mut56031, _mut56032, _mut56033)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.ConstantRealDistribution.inverseCumulativeProbability_56", _mut56024, _mut56025, _mut56026, _mut56027, _mut56028) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.ConstantRealDistribution.inverseCumulativeProbability_56", _mut56029, _mut56030, _mut56031, _mut56032, _mut56033)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportUpperBound() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double sample() {
        return value;
    }

    /**
     * Override with no-op (there is no generator).
     * @param seed (ignored)
     */
    @Override
    public void reseedRandomGenerator(long seed) {
    }
}
