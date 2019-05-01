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

import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

public class UnitSphereRandomVectorGenerator implements RandomVectorGenerator {

    @Conditional
    public static boolean _mut52221 = false, _mut52222 = false, _mut52223 = false, _mut52224 = false, _mut52225 = false, _mut52226 = false, _mut52227 = false, _mut52228 = false, _mut52229 = false, _mut52230 = false, _mut52231 = false, _mut52232 = false, _mut52233 = false, _mut52234 = false, _mut52235 = false, _mut52236 = false, _mut52237 = false, _mut52238 = false;

    /**
     * RNG used for generating the individual components of the vectors.
     */
    private final RandomGenerator rand;

    /**
     * Space dimension.
     */
    private final int dimension;

    /**
     * @param dimension Space dimension.
     * @param rand RNG for the individual components of the vectors.
     */
    public UnitSphereRandomVectorGenerator(final int dimension, final RandomGenerator rand) {
        this.dimension = dimension;
        this.rand = rand;
    }

    /**
     * Create an object that will use a default RNG ({@link MersenneTwister}),
     * in order to generate the individual components.
     *
     * @param dimension Space dimension.
     */
    public UnitSphereRandomVectorGenerator(final int dimension) {
        this(dimension, new MersenneTwister());
    }

    /**
     * {@inheritDoc}
     */
    public double[] nextVector() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60");
        final double[] v = new double[dimension];
        // normalizing to unit length.
        double normSq = 0;
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60", _mut52225, _mut52226, _mut52227, _mut52228, _mut52229); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60");
            final double comp = rand.nextGaussian();
            v[i] = comp;
            normSq += AOR_multiply(comp, comp, "org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60", _mut52221, _mut52222, _mut52223, _mut52224);
        }
        final double f = AOR_divide(1, FastMath.sqrt(normSq), "org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60", _mut52230, _mut52231, _mut52232, _mut52233);
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60", _mut52234, _mut52235, _mut52236, _mut52237, _mut52238); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UnitSphereRandomVectorGenerator.nextVector_60");
            v[i] *= f;
        }
        return v;
    }
}
