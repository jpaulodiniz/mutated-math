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
package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Calculates the Canberra distance between two points.
 *
 * @since 3.2
 */
public class CanberraDistance implements DistanceMeasure {

    @Conditional
    public static boolean _mut102710 = false, _mut102711 = false, _mut102712 = false, _mut102713 = false, _mut102714 = false, _mut102715 = false, _mut102716 = false, _mut102717 = false, _mut102718 = false, _mut102719 = false, _mut102720 = false, _mut102721 = false, _mut102722 = false, _mut102723 = false, _mut102724 = false, _mut102725 = false, _mut102726 = false, _mut102727 = false, _mut102728 = false, _mut102729 = false, _mut102730 = false, _mut102731 = false, _mut102732 = false, _mut102733 = false, _mut102734 = false, _mut102735 = false, _mut102736 = false, _mut102737 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -6972277381587032228L;

    /**
     * {@inheritDoc}
     */
    public double compute(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.distance.CanberraDistance.compute_34");
        MathArrays.checkEqualLength(a, b);
        double sum = 0;
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102733, _mut102734, _mut102735, _mut102736, _mut102737); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.distance.CanberraDistance.compute_34");
            final double num = FastMath.abs(AOR_minus(a[i], b[i], "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102710, _mut102711, _mut102712, _mut102713));
            final double denom = AOR_plus(FastMath.abs(a[i]), FastMath.abs(b[i]), "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102714, _mut102715, _mut102716, _mut102717);
            sum += (_mut102728 ? (ROR_equals(num, 0.0, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102718, _mut102719, _mut102720, _mut102721, _mut102722) || ROR_equals(denom, 0.0, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102723, _mut102724, _mut102725, _mut102726, _mut102727)) : (ROR_equals(num, 0.0, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102718, _mut102719, _mut102720, _mut102721, _mut102722) && ROR_equals(denom, 0.0, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102723, _mut102724, _mut102725, _mut102726, _mut102727))) ? 0.0 : AOR_divide(num, denom, "org.apache.commons.math3.ml.distance.CanberraDistance.compute_34", _mut102729, _mut102730, _mut102731, _mut102732);
        }
        return sum;
    }
}
