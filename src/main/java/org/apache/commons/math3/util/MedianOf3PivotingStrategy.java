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
 * Classic median of 3 strategy given begin and end indices.
 * @since 3.4
 */
public class MedianOf3PivotingStrategy implements PivotingStrategyInterface, Serializable {

    @Conditional
    public static boolean _mut50295 = false, _mut50296 = false, _mut50297 = false, _mut50298 = false, _mut50299 = false, _mut50300 = false, _mut50301 = false, _mut50302 = false, _mut50303 = false, _mut50304 = false, _mut50305 = false, _mut50306 = false, _mut50307 = false, _mut50308 = false, _mut50309 = false, _mut50310 = false, _mut50311 = false, _mut50312 = false, _mut50313 = false, _mut50314 = false, _mut50315 = false, _mut50316 = false, _mut50317 = false, _mut50318 = false, _mut50319 = false, _mut50320 = false, _mut50321 = false, _mut50322 = false, _mut50323 = false, _mut50324 = false, _mut50325 = false, _mut50326 = false, _mut50327 = false, _mut50328 = false, _mut50329 = false, _mut50330 = false, _mut50331 = false, _mut50332 = false, _mut50333 = false, _mut50334 = false, _mut50335 = false, _mut50336 = false, _mut50337 = false, _mut50338 = false, _mut50339 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20140713L;

    /**
     * {@inheritDoc}
     *  This in specific makes use of median of 3 pivoting.
     *  @return The index corresponding to a pivot chosen between the
     *  first, middle and the last indices of the array slice
     *  @throws MathIllegalArgumentException when indices exceeds range
     */
    public int pivotIndex(final double[] work, final int begin, final int end) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39");
        MathArrays.verifyValues(work, begin, AOR_minus(end, begin, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50295, _mut50296, _mut50297, _mut50298));
        final int inclusiveEnd = AOR_minus(end, 1, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50299, _mut50300, _mut50301, _mut50302);
        final int middle = AOR_plus(begin, AOR_divide((AOR_minus(inclusiveEnd, begin, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50303, _mut50304, _mut50305, _mut50306)), 2, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50307, _mut50308, _mut50309, _mut50310), "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50311, _mut50312, _mut50313, _mut50314);
        final double wBegin = work[begin];
        final double wMiddle = work[middle];
        final double wEnd = work[inclusiveEnd];
        if (ROR_less(wBegin, wMiddle, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50315, _mut50316, _mut50317, _mut50318, _mut50319)) {
            if (ROR_less(wMiddle, wEnd, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50330, _mut50331, _mut50332, _mut50333, _mut50334)) {
                return middle;
            } else {
                return ROR_less(wBegin, wEnd, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50335, _mut50336, _mut50337, _mut50338, _mut50339) ? inclusiveEnd : begin;
            }
        } else {
            if (ROR_less(wBegin, wEnd, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50320, _mut50321, _mut50322, _mut50323, _mut50324)) {
                return begin;
            } else {
                return ROR_less(wMiddle, wEnd, "org.apache.commons.math3.util.MedianOf3PivotingStrategy.pivotIndex_39", _mut50325, _mut50326, _mut50327, _mut50328, _mut50329) ? inclusiveEnd : middle;
            }
        }
    }
}
