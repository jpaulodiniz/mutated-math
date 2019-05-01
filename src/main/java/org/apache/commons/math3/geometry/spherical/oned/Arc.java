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
package org.apache.commons.math3.geometry.spherical.oned;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents an arc on a circle.
 * @see ArcsSet
 * @since 3.3
 */
public class Arc {

    @Conditional
    public static boolean _mut85298 = false, _mut85299 = false, _mut85300 = false, _mut85301 = false, _mut85302 = false, _mut85303 = false, _mut85304 = false, _mut85305 = false, _mut85306 = false, _mut85307 = false, _mut85308 = false, _mut85309 = false, _mut85310 = false, _mut85311 = false, _mut85312 = false, _mut85313 = false, _mut85314 = false, _mut85315 = false, _mut85316 = false, _mut85317 = false, _mut85318 = false, _mut85319 = false, _mut85320 = false, _mut85321 = false, _mut85322 = false, _mut85323 = false, _mut85324 = false, _mut85325 = false, _mut85326 = false, _mut85327 = false, _mut85328 = false, _mut85329 = false, _mut85330 = false, _mut85331 = false, _mut85332 = false, _mut85333 = false, _mut85334 = false, _mut85335 = false, _mut85336 = false, _mut85337 = false, _mut85338 = false, _mut85339 = false, _mut85340 = false, _mut85341 = false, _mut85342 = false, _mut85343 = false, _mut85344 = false, _mut85345 = false, _mut85346 = false, _mut85347 = false, _mut85348 = false, _mut85349 = false, _mut85350 = false, _mut85351 = false, _mut85352 = false, _mut85353 = false, _mut85354 = false, _mut85355 = false, _mut85356 = false, _mut85357 = false, _mut85358 = false, _mut85359 = false, _mut85360 = false, _mut85361 = false, _mut85362 = false, _mut85363 = false, _mut85364 = false, _mut85365 = false, _mut85366 = false, _mut85367 = false, _mut85368 = false, _mut85369 = false, _mut85370 = false, _mut85371 = false, _mut85372 = false, _mut85373 = false, _mut85374 = false, _mut85375 = false, _mut85376 = false, _mut85377 = false, _mut85378 = false, _mut85379 = false;

    /**
     * The lower angular bound of the arc.
     */
    private final double lower;

    /**
     * The upper angular bound of the arc.
     */
    private final double upper;

    /**
     * Middle point of the arc.
     */
    private final double middle;

    /**
     * Tolerance below which angles are considered identical.
     */
    private final double tolerance;

    /**
     * Simple constructor.
     * <p>
     * If either {@code lower} is equals to {@code upper} or
     * the interval exceeds \( 2 \pi \), the arc is considered
     * to be the full circle and its initial defining boundaries
     * will be forgotten. {@code lower} is not allowed to be
     * greater than {@code upper} (an exception is thrown in this case).
     * {@code lower} will be canonicalized between 0 and \( 2 \pi \), and
     * upper shifted accordingly, so the {@link #getInf()} and {@link #getSup()}
     * may not return the value used at instance construction.
     * </p>
     * @param lower lower angular bound of the arc
     * @param upper upper angular bound of the arc
     * @param tolerance tolerance below which angles are considered identical
     * @exception NumberIsTooLargeException if lower is greater than upper
     */
    public Arc(final double lower, final double upper, final double tolerance) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61");
        this.tolerance = tolerance;
        if ((_mut85307 ? (Precision.equals(lower, upper, 0) && ROR_greater_equals((AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85298, _mut85299, _mut85300, _mut85301)), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85302, _mut85303, _mut85304, _mut85305, _mut85306)) : (Precision.equals(lower, upper, 0) || ROR_greater_equals((AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85298, _mut85299, _mut85300, _mut85301)), MathUtils.TWO_PI, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85302, _mut85303, _mut85304, _mut85305, _mut85306)))) {
            // the arc must cover the whole circle
            this.lower = 0;
            this.upper = MathUtils.TWO_PI;
            this.middle = FastMath.PI;
        } else if (ROR_less_equals(lower, upper, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85308, _mut85309, _mut85310, _mut85311, _mut85312)) {
            this.lower = MathUtils.normalizeAngle(lower, FastMath.PI);
            this.upper = AOR_plus(this.lower, (AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85313, _mut85314, _mut85315, _mut85316)), "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85317, _mut85318, _mut85319, _mut85320);
            this.middle = AOR_multiply(0.5, (AOR_plus(this.lower, this.upper, "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85321, _mut85322, _mut85323, _mut85324)), "org.apache.commons.math3.geometry.spherical.oned.Arc.Arc_61", _mut85325, _mut85326, _mut85327, _mut85328);
        } else {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, lower, upper, true);
        }
    }

    /**
     * Get the lower angular bound of the arc.
     * @return lower angular bound of the arc,
     * always between 0 and \( 2 \pi \)
     */
    public double getInf() {
        return lower;
    }

    /**
     * Get the upper angular bound of the arc.
     * @return upper angular bound of the arc,
     * always between {@link #getInf()} and {@link #getInf()} \( + 2 \pi \)
     */
    public double getSup() {
        return upper;
    }

    /**
     * Get the angular size of the arc.
     * @return angular size of the arc
     */
    public double getSize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.Arc.getSize_98");
        return AOR_minus(upper, lower, "org.apache.commons.math3.geometry.spherical.oned.Arc.getSize_98", _mut85329, _mut85330, _mut85331, _mut85332);
    }

    /**
     * Get the barycenter of the arc.
     * @return barycenter of the arc
     */
    public double getBarycenter() {
        return middle;
    }

    /**
     * Get the tolerance below which angles are considered identical.
     * @return tolerance below which angles are considered identical
     */
    public double getTolerance() {
        return tolerance;
    }

    /**
     * Check a point with respect to the arc.
     * @param point point to check
     * @return a code representing the point status: either {@link
     * Location#INSIDE}, {@link Location#OUTSIDE} or {@link Location#BOUNDARY}
     */
    public Location checkPoint(final double point) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121");
        final double normalizedPoint = MathUtils.normalizeAngle(point, middle);
        if ((_mut85351 ? (ROR_less(normalizedPoint, AOR_minus(lower, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85333, _mut85334, _mut85335, _mut85336), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85337, _mut85338, _mut85339, _mut85340, _mut85341) && ROR_greater(normalizedPoint, AOR_plus(upper, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85342, _mut85343, _mut85344, _mut85345), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85346, _mut85347, _mut85348, _mut85349, _mut85350)) : (ROR_less(normalizedPoint, AOR_minus(lower, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85333, _mut85334, _mut85335, _mut85336), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85337, _mut85338, _mut85339, _mut85340, _mut85341) || ROR_greater(normalizedPoint, AOR_plus(upper, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85342, _mut85343, _mut85344, _mut85345), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85346, _mut85347, _mut85348, _mut85349, _mut85350)))) {
            return Location.OUTSIDE;
        } else if ((_mut85370 ? (ROR_greater(normalizedPoint, AOR_plus(lower, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85352, _mut85353, _mut85354, _mut85355), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85356, _mut85357, _mut85358, _mut85359, _mut85360) || ROR_less(normalizedPoint, AOR_minus(upper, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85361, _mut85362, _mut85363, _mut85364), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85365, _mut85366, _mut85367, _mut85368, _mut85369)) : (ROR_greater(normalizedPoint, AOR_plus(lower, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85352, _mut85353, _mut85354, _mut85355), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85356, _mut85357, _mut85358, _mut85359, _mut85360) && ROR_less(normalizedPoint, AOR_minus(upper, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85361, _mut85362, _mut85363, _mut85364), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85365, _mut85366, _mut85367, _mut85368, _mut85369)))) {
            return Location.INSIDE;
        } else {
            return (ROR_greater_equals(getSize(), AOR_minus(MathUtils.TWO_PI, tolerance, "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85371, _mut85372, _mut85373, _mut85374), "org.apache.commons.math3.geometry.spherical.oned.Arc.checkPoint_121", _mut85375, _mut85376, _mut85377, _mut85378, _mut85379)) ? Location.INSIDE : Location.BOUNDARY;
        }
    }
}
