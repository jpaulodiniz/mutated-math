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
package org.apache.commons.math3.random;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class UncorrelatedRandomVectorGenerator implements RandomVectorGenerator {

    @Conditional
    public static boolean _mut51451 = false, _mut51452 = false, _mut51453 = false, _mut51454 = false, _mut51455 = false, _mut51456 = false, _mut51457 = false, _mut51458 = false, _mut51459 = false, _mut51460 = false, _mut51461 = false, _mut51462 = false, _mut51463 = false, _mut51464 = false, _mut51465 = false, _mut51466 = false, _mut51467 = false, _mut51468 = false;

    /**
     * Underlying scalar generator.
     */
    private final NormalizedRandomGenerator generator;

    /**
     * Mean vector.
     */
    private final double[] mean;

    /**
     * Standard deviation vector.
     */
    private final double[] standardDeviation;

    /**
     * Simple constructor.
     * <p>Build an uncorrelated random vector generator from
     * its mean and standard deviation vectors.</p>
     * @param mean expected mean values for each component
     * @param standardDeviation standard deviation for each component
     * @param generator underlying generator for uncorrelated normalized
     * components
     */
    public UncorrelatedRandomVectorGenerator(double[] mean, double[] standardDeviation, NormalizedRandomGenerator generator) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.UncorrelatedRandomVectorGenerator_52");
        if (ROR_not_equals(mean.length, standardDeviation.length, "org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.UncorrelatedRandomVectorGenerator_52", _mut51451, _mut51452, _mut51453, _mut51454, _mut51455)) {
            throw new DimensionMismatchException(mean.length, standardDeviation.length);
        }
        this.mean = mean.clone();
        this.standardDeviation = standardDeviation.clone();
        this.generator = generator;
    }

    /**
     * Simple constructor.
     * <p>Build a null mean random and unit standard deviation
     * uncorrelated vector generator</p>
     * @param dimension dimension of the vectors to generate
     * @param generator underlying generator for uncorrelated normalized
     * components
     */
    public UncorrelatedRandomVectorGenerator(int dimension, NormalizedRandomGenerator generator) {
        mean = new double[dimension];
        standardDeviation = new double[dimension];
        Arrays.fill(standardDeviation, 1.0);
        this.generator = generator;
    }

    /**
     * Generate an uncorrelated random vector.
     * @return a random vector as a newly built array of double
     */
    public double[] nextVector() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.nextVector_81");
        double[] random = new double[mean.length];
        for (int i = 0; ROR_less(i, random.length, "org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.nextVector_81", _mut51464, _mut51465, _mut51466, _mut51467, _mut51468); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.nextVector_81");
            random[i] = AOR_plus(mean[i], AOR_multiply(standardDeviation[i], generator.nextNormalizedDouble(), "org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.nextVector_81", _mut51456, _mut51457, _mut51458, _mut51459), "org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator.nextVector_81", _mut51460, _mut51461, _mut51462, _mut51463);
        }
        return random;
    }
}
