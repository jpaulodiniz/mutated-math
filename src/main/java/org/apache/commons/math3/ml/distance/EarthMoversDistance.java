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
 * Calculates the Earh Mover's distance (also known as Wasserstein metric) between two distributions.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Earth_mover's_distance">Earth Mover's distance (Wikipedia)</a>
 *
 * @since 3.3
 */
public class EarthMoversDistance implements DistanceMeasure {

    @Conditional
    public static boolean _mut102738 = false, _mut102739 = false, _mut102740 = false, _mut102741 = false, _mut102742 = false, _mut102743 = false, _mut102744 = false, _mut102745 = false, _mut102746 = false, _mut102747 = false, _mut102748 = false, _mut102749 = false, _mut102750 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -5406732779747414922L;

    /**
     * {@inheritDoc}
     */
    public double compute(double[] a, double[] b) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.distance.EarthMoversDistance.compute_36");
        MathArrays.checkEqualLength(a, b);
        double lastDistance = 0;
        double totalDistance = 0;
        for (int i = 0; ROR_less(i, a.length, "org.apache.commons.math3.ml.distance.EarthMoversDistance.compute_36", _mut102746, _mut102747, _mut102748, _mut102749, _mut102750); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.ml.distance.EarthMoversDistance.compute_36");
            final double currentDistance = AOR_minus((AOR_plus(a[i], lastDistance, "org.apache.commons.math3.ml.distance.EarthMoversDistance.compute_36", _mut102738, _mut102739, _mut102740, _mut102741)), b[i], "org.apache.commons.math3.ml.distance.EarthMoversDistance.compute_36", _mut102742, _mut102743, _mut102744, _mut102745);
            totalDistance += FastMath.abs(currentDistance);
            lastDistance = currentDistance;
        }
        return totalDistance;
    }
}
