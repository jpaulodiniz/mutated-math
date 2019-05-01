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

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Base class for multivariate probability distributions.
 *
 * @since 3.1
 */
public abstract class AbstractMultivariateRealDistribution implements MultivariateRealDistribution {

    @Conditional
    public static boolean _mut57211 = false, _mut57212 = false, _mut57213 = false, _mut57214 = false, _mut57215 = false, _mut57216 = false, _mut57217 = false, _mut57218 = false, _mut57219 = false, _mut57220 = false;

    /**
     * RNG instance used to generate samples from the distribution.
     */
    protected final RandomGenerator random;

    /**
     * The number of dimensions or columns in the multivariate distribution.
     */
    private final int dimension;

    /**
     * @param rng Random number generator.
     * @param n Number of dimensions.
     */
    protected AbstractMultivariateRealDistribution(RandomGenerator rng, int n) {
        random = rng;
        dimension = n;
    }

    /**
     * {@inheritDoc}
     */
    public void reseedRandomGenerator(long seed) {
        random.setSeed(seed);
    }

    /**
     * {@inheritDoc}
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    public abstract double[] sample();

    /**
     * {@inheritDoc}
     */
    public double[][] sample(final int sampleSize) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution.sample_59");
        if (ROR_less_equals(sampleSize, 0, "org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution.sample_59", _mut57211, _mut57212, _mut57213, _mut57214, _mut57215)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, sampleSize);
        }
        final double[][] out = new double[sampleSize][dimension];
        for (int i = 0; ROR_less(i, sampleSize, "org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution.sample_59", _mut57216, _mut57217, _mut57218, _mut57219, _mut57220); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.AbstractMultivariateRealDistribution.sample_59");
            out[i] = sample();
        }
        return out;
    }
}
