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
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * A mid point strategy based on the average of begin and end indices.
 * @since 3.4
 */
public class CentralPivotingStrategy implements PivotingStrategyInterface, Serializable {

    @Conditional
    public static boolean _mut39898 = false, _mut39899 = false, _mut39900 = false, _mut39901 = false, _mut39902 = false, _mut39903 = false, _mut39904 = false, _mut39905 = false, _mut39906 = false, _mut39907 = false, _mut39908 = false, _mut39909 = false, _mut39910 = false, _mut39911 = false, _mut39912 = false, _mut39913 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140713L;

    /**
     * {@inheritDoc}
     * This in particular picks a average of begin and end indices
     * @return The index corresponding to a simple average of
     * the first and the last element indices of the array slice
     * @throws MathIllegalArgumentException when indices exceeds range
     */
    public int pivotIndex(final double[] work, final int begin, final int end) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.CentralPivotingStrategy.pivotIndex_40");
        MathArrays.verifyValues(work, begin, AOR_minus(end, begin, "org.apache.commons.math3.util.CentralPivotingStrategy.pivotIndex_40", _mut39898, _mut39899, _mut39900, _mut39901));
        return AOR_plus(begin, AOR_divide((AOR_minus(end, begin, "org.apache.commons.math3.util.CentralPivotingStrategy.pivotIndex_40", _mut39902, _mut39903, _mut39904, _mut39905)), 2, "org.apache.commons.math3.util.CentralPivotingStrategy.pivotIndex_40", _mut39906, _mut39907, _mut39908, _mut39909), "org.apache.commons.math3.util.CentralPivotingStrategy.pivotIndex_40", _mut39910, _mut39911, _mut39912, _mut39913);
    }
}
