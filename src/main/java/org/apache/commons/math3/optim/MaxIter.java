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

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Maximum number of iterations performed by an (iterative) algorithm.
 *
 * @since 3.1
 */
public class MaxIter implements OptimizationData {

    @Conditional
    public static boolean _mut59515 = false, _mut59516 = false, _mut59517 = false, _mut59518 = false, _mut59519 = false;

    /**
     * Allowed number of evalutations.
     */
    private final int maxIter;

    /**
     * @param max Allowed number of iterations.
     * @throws NotStrictlyPositiveException if {@code max <= 0}.
     */
    public MaxIter(int max) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.MaxIter.MaxIter_34");
        if (ROR_less_equals(max, 0, "org.apache.commons.math3.optim.MaxIter.MaxIter_34", _mut59515, _mut59516, _mut59517, _mut59518, _mut59519)) {
            throw new NotStrictlyPositiveException(max);
        }
        maxIter = max;
    }

    /**
     * Gets the maximum number of evaluations.
     *
     * @return the allowed number of evaluations.
     */
    public int getMaxIter() {
        return maxIter;
    }

    /**
     * Factory method that creates instance of this class that represents
     * a virtually unlimited number of iterations.
     *
     * @return a new instance suitable for allowing {@link Integer#MAX_VALUE}
     * evaluations.
     */
    public static MaxIter unlimited() {
        return new MaxIter(Integer.MAX_VALUE);
    }
}
