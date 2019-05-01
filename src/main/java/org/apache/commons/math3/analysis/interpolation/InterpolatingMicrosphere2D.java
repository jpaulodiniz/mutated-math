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
package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utility class for the {@link MicrosphereProjectionInterpolator} algorithm.
 * For 2D interpolation, this class constructs the microsphere as a series of
 * evenly spaced facets (rather than generating random normals as in the
 * base implementation).
 *
 * @since 3.6
 */
public class InterpolatingMicrosphere2D extends InterpolatingMicrosphere {

    @Conditional
    public static boolean _mut94293 = false, _mut94294 = false, _mut94295 = false, _mut94296 = false, _mut94297 = false, _mut94298 = false, _mut94299 = false, _mut94300 = false, _mut94301 = false, _mut94302 = false, _mut94303 = false, _mut94304 = false, _mut94305 = false;

    /**
     * Space dimension.
     */
    private static final int DIMENSION = 2;

    /**
     * Create a sphere from vectors regularly sampled around a circle.
     *
     * @param size Number of surface elements of the sphere.
     * @param maxDarkFraction Maximum fraction of the facets that can be dark.
     * If the fraction of "non-illuminated" facets is larger, no estimation
     * of the value will be performed, and the {@code background} value will
     * be returned instead.
     * @param darkThreshold Value of the illumination below which a facet is
     * considered dark.
     * @param background Value returned when the {@code maxDarkFraction}
     * threshold is exceeded.
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if {@code size <= 0}.
     * @throws org.apache.commons.math3.exception.NotPositiveException if
     * {@code darkThreshold < 0}.
     * @throws org.apache.commons.math3.exception.OutOfRangeException if
     * {@code maxDarkFraction} does not belong to the interval {@code [0, 1]}.
     */
    public InterpolatingMicrosphere2D(int size, double maxDarkFraction, double darkThreshold, double background) {
        super(DIMENSION, size, maxDarkFraction, darkThreshold, background);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere2D.InterpolatingMicrosphere2D_53");
        // Generate the microsphere normals.
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere2D.InterpolatingMicrosphere2D_53", _mut94301, _mut94302, _mut94303, _mut94304, _mut94305); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere2D.InterpolatingMicrosphere2D_53");
            final double angle = AOR_divide(AOR_multiply(i, MathUtils.TWO_PI, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere2D.InterpolatingMicrosphere2D_53", _mut94293, _mut94294, _mut94295, _mut94296), size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere2D.InterpolatingMicrosphere2D_53", _mut94297, _mut94298, _mut94299, _mut94300);
            add(new double[] { FastMath.cos(angle), FastMath.sin(angle) }, false);
        }
    }

    /**
     * Copy constructor.
     *
     * @param other Instance to copy.
     */
    protected InterpolatingMicrosphere2D(InterpolatingMicrosphere2D other) {
        super(other);
    }

    /**
     * Perform a copy.
     *
     * @return a copy of this instance.
     */
    @Override
    public InterpolatingMicrosphere2D copy() {
        return new InterpolatingMicrosphere2D(this);
    }
}
