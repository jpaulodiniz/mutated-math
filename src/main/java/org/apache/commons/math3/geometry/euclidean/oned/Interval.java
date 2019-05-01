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
package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class represents a 1D interval.
 * @see IntervalsSet
 * @since 3.0
 */
public class Interval {

    @Conditional
    public static boolean _mut83966 = false, _mut83967 = false, _mut83968 = false, _mut83969 = false, _mut83970 = false, _mut83971 = false, _mut83972 = false, _mut83973 = false, _mut83974 = false, _mut83975 = false, _mut83976 = false, _mut83977 = false, _mut83978 = false, _mut83979 = false, _mut83980 = false, _mut83981 = false, _mut83982 = false, _mut83983 = false, _mut83984 = false, _mut83985 = false, _mut83986 = false, _mut83987 = false, _mut83988 = false, _mut83989 = false, _mut83990 = false, _mut83991 = false, _mut83992 = false, _mut83993 = false, _mut83994 = false, _mut83995 = false, _mut83996 = false, _mut83997 = false, _mut83998 = false, _mut83999 = false, _mut84000 = false, _mut84001 = false, _mut84002 = false, _mut84003 = false, _mut84004 = false, _mut84005 = false, _mut84006 = false, _mut84007 = false, _mut84008 = false, _mut84009 = false, _mut84010 = false, _mut84011 = false, _mut84012 = false, _mut84013 = false, _mut84014 = false, _mut84015 = false, _mut84016 = false, _mut84017 = false, _mut84018 = false, _mut84019 = false, _mut84020 = false;

    /**
     * The lower bound of the interval.
     */
    private final double lower;

    /**
     * The upper bound of the interval.
     */
    private final double upper;

    /**
     * Simple constructor.
     * @param lower lower bound of the interval
     * @param upper upper bound of the interval
     */
    public Interval(final double lower, final double upper) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Interval.Interval_40");
        if (ROR_less(upper, lower, "org.apache.commons.math3.geometry.euclidean.oned.Interval.Interval_40", _mut83966, _mut83967, _mut83968, _mut83969, _mut83970)) {
            throw new NumberIsTooSmallException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, upper, lower, true);
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Get the lower bound of the interval.
     * @return lower bound of the interval
     * @since 3.1
     */
    public double getInf() {
        return lower;
    }

    /**
     * Get the lower bound of the interval.
     * @return lower bound of the interval
     * @deprecated as of 3.1, replaced by {@link #getInf()}
     */
    @Deprecated
    public double getLower() {
        return getInf();
    }

    /**
     * Get the upper bound of the interval.
     * @return upper bound of the interval
     * @since 3.1
     */
    public double getSup() {
        return upper;
    }

    /**
     * Get the upper bound of the interval.
     * @return upper bound of the interval
     * @deprecated as of 3.1, replaced by {@link #getSup()}
     */
    @Deprecated
    public double getUpper() {
        return getSup();
    }

    /**
     * Get the size of the interval.
     * @return size of the interval
     * @since 3.1
     */
    public double getSize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Interval.getSize_87");
        return AOR_minus(upper, lower, "org.apache.commons.math3.geometry.euclidean.oned.Interval.getSize_87", _mut83971, _mut83972, _mut83973, _mut83974);
    }

    /**
     * Get the length of the interval.
     * @return length of the interval
     * @deprecated as of 3.1, replaced by {@link #getSize()}
     */
    @Deprecated
    public double getLength() {
        return getSize();
    }

    /**
     * Get the barycenter of the interval.
     * @return barycenter of the interval
     * @since 3.1
     */
    public double getBarycenter() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Interval.getBarycenter_104");
        return AOR_multiply(0.5, (AOR_plus(lower, upper, "org.apache.commons.math3.geometry.euclidean.oned.Interval.getBarycenter_104", _mut83975, _mut83976, _mut83977, _mut83978)), "org.apache.commons.math3.geometry.euclidean.oned.Interval.getBarycenter_104", _mut83979, _mut83980, _mut83981, _mut83982);
    }

    /**
     * Get the midpoint of the interval.
     * @return midpoint of the interval
     * @deprecated as of 3.1, replaced by {@link #getBarycenter()}
     */
    @Deprecated
    public double getMidPoint() {
        return getBarycenter();
    }

    /**
     * Check a point with respect to the interval.
     * @param point point to check
     * @param tolerance tolerance below which points are considered to
     * belong to the boundary
     * @return a code representing the point status: either {@link
     * Location#INSIDE}, {@link Location#OUTSIDE} or {@link Location#BOUNDARY}
     * @since 3.1
     */
    public Location checkPoint(final double point, final double tolerance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125");
        if ((_mut84001 ? (ROR_less(point, AOR_minus(lower, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83983, _mut83984, _mut83985, _mut83986), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83987, _mut83988, _mut83989, _mut83990, _mut83991) && ROR_greater(point, AOR_plus(upper, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83992, _mut83993, _mut83994, _mut83995), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83996, _mut83997, _mut83998, _mut83999, _mut84000)) : (ROR_less(point, AOR_minus(lower, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83983, _mut83984, _mut83985, _mut83986), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83987, _mut83988, _mut83989, _mut83990, _mut83991) || ROR_greater(point, AOR_plus(upper, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83992, _mut83993, _mut83994, _mut83995), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut83996, _mut83997, _mut83998, _mut83999, _mut84000)))) {
            return Location.OUTSIDE;
        } else if ((_mut84020 ? (ROR_greater(point, AOR_plus(lower, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84002, _mut84003, _mut84004, _mut84005), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84006, _mut84007, _mut84008, _mut84009, _mut84010) || ROR_less(point, AOR_minus(upper, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84011, _mut84012, _mut84013, _mut84014), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84015, _mut84016, _mut84017, _mut84018, _mut84019)) : (ROR_greater(point, AOR_plus(lower, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84002, _mut84003, _mut84004, _mut84005), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84006, _mut84007, _mut84008, _mut84009, _mut84010) && ROR_less(point, AOR_minus(upper, tolerance, "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84011, _mut84012, _mut84013, _mut84014), "org.apache.commons.math3.geometry.euclidean.oned.Interval.checkPoint_125", _mut84015, _mut84016, _mut84017, _mut84018, _mut84019)))) {
            return Location.INSIDE;
        } else {
            return Location.BOUNDARY;
        }
    }
}
