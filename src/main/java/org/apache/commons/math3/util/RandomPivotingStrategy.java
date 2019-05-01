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
package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.random.RandomGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A strategy of selecting random index between begin and end indices.
 * @since 3.4
 */
public class RandomPivotingStrategy implements PivotingStrategyInterface, Serializable {

    @Conditional
    public static boolean _mut40161 = false, _mut40162 = false, _mut40163 = false, _mut40164 = false, _mut40165 = false, _mut40166 = false, _mut40167 = false, _mut40168 = false, _mut40169 = false, _mut40170 = false, _mut40171 = false, _mut40172 = false, _mut40173 = false, _mut40174 = false, _mut40175 = false, _mut40176 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140713L;

    /**
     * Random generator to use for selecting pivot.
     */
    private final RandomGenerator random;

    /**
     * Simple constructor.
     * @param random random generator to use for selecting pivot
     */
    public RandomPivotingStrategy(final RandomGenerator random) {
        this.random = random;
    }

    /**
     * {@inheritDoc}
     * A uniform random pivot selection between begin and end indices
     * @return The index corresponding to a random uniformly selected
     * value between first and the last indices of the array slice
     * @throws MathIllegalArgumentException when indices exceeds range
     */
    public int pivotIndex(final double[] work, final int begin, final int end) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.RandomPivotingStrategy.pivotIndex_51");
        MathArrays.verifyValues(work, begin, AOR_minus(end, begin, "org.apache.commons.math3.util.RandomPivotingStrategy.pivotIndex_51", _mut40161, _mut40162, _mut40163, _mut40164));
        return AOR_plus(begin, random.nextInt(AOR_minus(AOR_minus(end, begin, "org.apache.commons.math3.util.RandomPivotingStrategy.pivotIndex_51", _mut40165, _mut40166, _mut40167, _mut40168), 1, "org.apache.commons.math3.util.RandomPivotingStrategy.pivotIndex_51", _mut40169, _mut40170, _mut40171, _mut40172)), "org.apache.commons.math3.util.RandomPivotingStrategy.pivotIndex_51", _mut40173, _mut40174, _mut40175, _mut40176);
    }
}
