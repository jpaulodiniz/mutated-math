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

public class UniformRandomGenerator implements NormalizedRandomGenerator {

    @Conditional
    public static boolean _mut51473 = false, _mut51474 = false, _mut51475 = false, _mut51476 = false, _mut51477 = false, _mut51478 = false, _mut51479 = false, _mut51480 = false, _mut51481 = false, _mut51482 = false, _mut51483 = false, _mut51484 = false;

    /**
     * Square root of three.
     */
    private static final double SQRT3 = FastMath.sqrt(3.0);

    /**
     * Underlying generator.
     */
    private final RandomGenerator generator;

    /**
     * Create a new generator.
     * @param generator underlying random generator to use
     */
    public UniformRandomGenerator(RandomGenerator generator) {
        this.generator = generator;
    }

    /**
     * Generate a random scalar with null mean and unit standard deviation.
     * <p>The number generated is uniformly distributed between -&sqrt;(3)
     * and +&sqrt;(3).</p>
     * @return a random scalar with null mean and unit standard deviation
     */
    public double nextNormalizedDouble() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.UniformRandomGenerator.nextNormalizedDouble_53");
        return AOR_multiply(SQRT3, (AOR_minus(AOR_multiply(2, generator.nextDouble(), "org.apache.commons.math3.random.UniformRandomGenerator.nextNormalizedDouble_53", _mut51473, _mut51474, _mut51475, _mut51476), 1.0, "org.apache.commons.math3.random.UniformRandomGenerator.nextNormalizedDouble_53", _mut51477, _mut51478, _mut51479, _mut51480)), "org.apache.commons.math3.random.UniformRandomGenerator.nextNormalizedDouble_53", _mut51481, _mut51482, _mut51483, _mut51484);
    }
}
