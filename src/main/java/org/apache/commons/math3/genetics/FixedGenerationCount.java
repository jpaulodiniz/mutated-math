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
package org.apache.commons.math3.genetics;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Stops after a fixed number of generations. Each time {@link #isSatisfied(Population)} is invoked, a generation
 * counter is incremented. Once the counter reaches the configured <code>maxGenerations</code> value,
 * {@link #isSatisfied(Population)} returns true.
 *
 * @since 2.0
 */
public class FixedGenerationCount implements StoppingCondition {

    @Conditional
    public static boolean _mut2444 = false, _mut2445 = false, _mut2446 = false, _mut2447 = false, _mut2448 = false, _mut2449 = false, _mut2450 = false, _mut2451 = false, _mut2452 = false, _mut2453 = false;

    /**
     * Number of generations that have passed
     */
    private int numGenerations = 0;

    /**
     * Maximum number of generations (stopping criteria)
     */
    private final int maxGenerations;

    /**
     * Create a new FixedGenerationCount instance.
     *
     * @param maxGenerations number of generations to evolve
     * @throws NumberIsTooSmallException if the number of generations is &lt; 1
     */
    public FixedGenerationCount(final int maxGenerations) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.FixedGenerationCount.FixedGenerationCount_41");
        if (ROR_less_equals(maxGenerations, 0, "org.apache.commons.math3.genetics.FixedGenerationCount.FixedGenerationCount_41", _mut2444, _mut2445, _mut2446, _mut2447, _mut2448)) {
            throw new NumberIsTooSmallException(maxGenerations, 1, true);
        }
        this.maxGenerations = maxGenerations;
    }

    /**
     * Determine whether or not the given number of generations have passed. Increments the number of generations
     * counter if the maximum has not been reached.
     *
     * @param population ignored (no impact on result)
     * @return <code>true</code> IFF the maximum number of generations has been exceeded
     */
    public boolean isSatisfied(final Population population) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.genetics.FixedGenerationCount.isSatisfied_55");
        if (ROR_less(this.numGenerations, this.maxGenerations, "org.apache.commons.math3.genetics.FixedGenerationCount.isSatisfied_55", _mut2449, _mut2450, _mut2451, _mut2452, _mut2453)) {
            numGenerations++;
            return false;
        }
        return true;
    }

    /**
     * Returns the number of generations that have already passed.
     * @return the number of generations that have passed
     */
    public int getNumGenerations() {
        return numGenerations;
    }
}
