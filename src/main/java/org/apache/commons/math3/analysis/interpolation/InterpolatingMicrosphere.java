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

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utility class for the {@link MicrosphereProjectionInterpolator} algorithm.
 *
 * @since 3.6
 */
public class InterpolatingMicrosphere {

    @Conditional
    public static boolean _mut92474 = false, _mut92475 = false, _mut92476 = false, _mut92477 = false, _mut92478 = false, _mut92479 = false, _mut92480 = false, _mut92481 = false, _mut92482 = false, _mut92483 = false, _mut92484 = false, _mut92485 = false, _mut92486 = false, _mut92487 = false, _mut92488 = false, _mut92489 = false, _mut92490 = false, _mut92491 = false, _mut92492 = false, _mut92493 = false, _mut92494 = false, _mut92495 = false, _mut92496 = false, _mut92497 = false, _mut92498 = false, _mut92499 = false, _mut92500 = false, _mut92501 = false, _mut92502 = false, _mut92503 = false, _mut92504 = false, _mut92505 = false, _mut92506 = false, _mut92507 = false, _mut92508 = false, _mut92509 = false, _mut92510 = false, _mut92511 = false, _mut92512 = false, _mut92513 = false, _mut92514 = false, _mut92515 = false, _mut92516 = false, _mut92517 = false, _mut92518 = false, _mut92519 = false, _mut92520 = false, _mut92521 = false, _mut92522 = false, _mut92523 = false, _mut92524 = false, _mut92525 = false, _mut92526 = false, _mut92527 = false, _mut92528 = false, _mut92529 = false, _mut92530 = false, _mut92531 = false, _mut92532 = false, _mut92533 = false, _mut92534 = false, _mut92535 = false, _mut92536 = false, _mut92537 = false, _mut92538 = false, _mut92539 = false, _mut92540 = false, _mut92541 = false, _mut92542 = false, _mut92543 = false, _mut92544 = false, _mut92545 = false, _mut92546 = false, _mut92547 = false, _mut92548 = false, _mut92549 = false, _mut92550 = false, _mut92551 = false, _mut92552 = false, _mut92553 = false, _mut92554 = false, _mut92555 = false, _mut92556 = false, _mut92557 = false, _mut92558 = false, _mut92559 = false, _mut92560 = false, _mut92561 = false, _mut92562 = false, _mut92563 = false, _mut92564 = false, _mut92565 = false, _mut92566 = false, _mut92567 = false, _mut92568 = false, _mut92569 = false, _mut92570 = false, _mut92571 = false, _mut92572 = false, _mut92573 = false, _mut92574 = false, _mut92575 = false, _mut92576 = false, _mut92577 = false, _mut92578 = false, _mut92579 = false, _mut92580 = false, _mut92581 = false;

    /**
     * Microsphere.
     */
    private final List<Facet> microsphere;

    /**
     * Microsphere data.
     */
    private final List<FacetData> microsphereData;

    /**
     * Space dimension.
     */
    private final int dimension;

    /**
     * Number of surface elements.
     */
    private final int size;

    /**
     * Maximum fraction of the facets that can be dark.
     */
    private final double maxDarkFraction;

    /**
     * Lowest non-zero illumination.
     */
    private final double darkThreshold;

    /**
     * Background value.
     */
    private final double background;

    /**
     * Create an unitialiazed sphere.
     * Sub-classes are responsible for calling the {@code add(double[]) add}
     * method in order to initialize all the sphere's facets.
     *
     * @param dimension Dimension of the data space.
     * @param size Number of surface elements of the sphere.
     * @param maxDarkFraction Maximum fraction of the facets that can be dark.
     * If the fraction of "non-illuminated" facets is larger, no estimation
     * of the value will be performed, and the {@code background} value will
     * be returned instead.
     * @param darkThreshold Value of the illumination below which a facet is
     * considered dark.
     * @param background Value returned when the {@code maxDarkFraction}
     * threshold is exceeded.
     * @throws NotStrictlyPositiveException if {@code dimension <= 0}
     * or {@code size <= 0}.
     * @throws NotPositiveException if {@code darkThreshold < 0}.
     * @throws OutOfRangeException if {@code maxDarkFraction} does not
     * belong to the interval {@code [0, 1]}.
     */
    protected InterpolatingMicrosphere(int dimension, int size, double maxDarkFraction, double darkThreshold, double background) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72");
        if (ROR_less_equals(dimension, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92474, _mut92475, _mut92476, _mut92477, _mut92478)) {
            throw new NotStrictlyPositiveException(dimension);
        }
        if (ROR_less_equals(size, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92479, _mut92480, _mut92481, _mut92482, _mut92483)) {
            throw new NotStrictlyPositiveException(size);
        }
        if ((_mut92494 ? (ROR_less(maxDarkFraction, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92484, _mut92485, _mut92486, _mut92487, _mut92488) && ROR_greater(maxDarkFraction, 1, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92489, _mut92490, _mut92491, _mut92492, _mut92493)) : (ROR_less(maxDarkFraction, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92484, _mut92485, _mut92486, _mut92487, _mut92488) || ROR_greater(maxDarkFraction, 1, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92489, _mut92490, _mut92491, _mut92492, _mut92493)))) {
            throw new OutOfRangeException(maxDarkFraction, 0, 1);
        }
        if (ROR_less(darkThreshold, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_72", _mut92495, _mut92496, _mut92497, _mut92498, _mut92499)) {
            throw new NotPositiveException(darkThreshold);
        }
        this.dimension = dimension;
        this.size = size;
        this.maxDarkFraction = maxDarkFraction;
        this.darkThreshold = darkThreshold;
        this.background = background;
        microsphere = new ArrayList<Facet>(size);
        microsphereData = new ArrayList<FacetData>(size);
    }

    /**
     * Create a sphere from randomly sampled vectors.
     *
     * @param dimension Dimension of the data space.
     * @param size Number of surface elements of the sphere.
     * @param rand Unit vector generator for creating the microsphere.
     * @param maxDarkFraction Maximum fraction of the facets that can be dark.
     * If the fraction of "non-illuminated" facets is larger, no estimation
     * of the value will be performed, and the {@code background} value will
     * be returned instead.
     * @param darkThreshold Value of the illumination below which a facet
     * is considered dark.
     * @param background Value returned when the {@code maxDarkFraction}
     * threshold is exceeded.
     * @throws DimensionMismatchException if the size of the generated
     * vectors does not match the dimension set in the constructor.
     * @throws NotStrictlyPositiveException if {@code dimension <= 0}
     * or {@code size <= 0}.
     * @throws NotPositiveException if {@code darkThreshold < 0}.
     * @throws OutOfRangeException if {@code maxDarkFraction} does not
     * belong to the interval {@code [0, 1]}.
     */
    public InterpolatingMicrosphere(int dimension, int size, double maxDarkFraction, double darkThreshold, double background, UnitSphereRandomVectorGenerator rand) {
        this(dimension, size, maxDarkFraction, darkThreshold, background);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_122");
        // randomly generated normals will represent a sphere.
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_122", _mut92500, _mut92501, _mut92502, _mut92503, _mut92504); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_122");
            add(rand.nextVector(), false);
        }
    }

    /**
     * Copy constructor.
     *
     * @param other Instance to copy.
     */
    protected InterpolatingMicrosphere(InterpolatingMicrosphere other) {
        dimension = other.dimension;
        size = other.size;
        maxDarkFraction = other.maxDarkFraction;
        darkThreshold = other.darkThreshold;
        background = other.background;
        // Field can be shared.
        microsphere = other.microsphere;
        // Field must be copied.
        microsphereData = new ArrayList<FacetData>(size);
        for (FacetData fd : other.microsphereData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.InterpolatingMicrosphere_142");
            microsphereData.add(new FacetData(fd.illumination(), fd.sample()));
        }
    }

    /**
     * Perform a copy.
     *
     * @return a copy of this instance.
     */
    public InterpolatingMicrosphere copy() {
        return new InterpolatingMicrosphere(this);
    }

    /**
     * Get the space dimensionality.
     *
     * @return the number of space dimensions.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Get the size of the sphere.
     *
     * @return the number of surface elements of the microspshere.
     */
    public int getSize() {
        return size;
    }

    /**
     * Estimate the value at the requested location.
     * This microsphere is placed at the given {@code point}, contribution
     * of the given {@code samplePoints} to each sphere facet is computed
     * (illumination) and the interpolation is performed (integration of
     * the illumination).
     *
     * @param point Interpolation point.
     * @param samplePoints Sampling data points.
     * @param sampleValues Sampling data values at the corresponding
     * {@code samplePoints}.
     * @param exponent Exponent used in the power law that computes
     * the weights (distance dimming factor) of the sample data.
     * @param noInterpolationTolerance When the distance between the
     * {@code point} and one of the {@code samplePoints} is less than
     * this value, no interpolation will be performed, and the value
     * of the sample will just be returned.
     * @return the estimated value at the given {@code point}.
     * @throws NotPositiveException if {@code exponent < 0}.
     */
    public double value(double[] point, double[][] samplePoints, double[] sampleValues, double exponent, double noInterpolationTolerance) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.value_206");
        if (ROR_less(exponent, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.value_206", _mut92505, _mut92506, _mut92507, _mut92508, _mut92509)) {
            throw new NotPositiveException(exponent);
        }
        clear();
        // microsphere's facets.
        final int numSamples = samplePoints.length;
        for (int i = 0; ROR_less(i, numSamples, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.value_206", _mut92515, _mut92516, _mut92517, _mut92518, _mut92519); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.value_206");
            // Vector between interpolation point and current sample point.
            final double[] diff = MathArrays.ebeSubtract(samplePoints[i], point);
            final double diffNorm = MathArrays.safeNorm(diff);
            if (ROR_less(FastMath.abs(diffNorm), noInterpolationTolerance, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.value_206", _mut92510, _mut92511, _mut92512, _mut92513, _mut92514)) {
                // actually (very close to) one of the sampled points.
                return sampleValues[i];
            }
            final double weight = FastMath.pow(diffNorm, -exponent);
            illuminate(diff, sampleValues[i], weight);
        }
        return interpolate();
    }

    /**
     * Replace {@code i}-th facet of the microsphere.
     * Method for initializing the microsphere facets.
     *
     * @param normal Facet's normal vector.
     * @param copy Whether to copy the given array.
     * @throws DimensionMismatchException if the length of {@code n}
     * does not match the space dimension.
     * @throws MaxCountExceededException if the method has been called
     * more times than the size of the sphere.
     */
    protected void add(double[] normal, boolean copy) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.add_249");
        if (ROR_greater_equals(microsphere.size(), size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.add_249", _mut92520, _mut92521, _mut92522, _mut92523, _mut92524)) {
            throw new MaxCountExceededException(size);
        }
        if (ROR_greater(normal.length, dimension, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.add_249", _mut92525, _mut92526, _mut92527, _mut92528, _mut92529)) {
            throw new DimensionMismatchException(normal.length, dimension);
        }
        microsphere.add(new Facet(copy ? normal.clone() : normal));
        microsphereData.add(new FacetData(0d, 0d));
    }

    /**
     * Interpolation.
     *
     * @return the value estimated from the current illumination of the
     * microsphere.
     */
    private double interpolate() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268");
        // Number of non-illuminated facets.
        int darkCount = 0;
        double value = 0;
        double totalWeight = 0;
        for (FacetData fd : microsphereData) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268");
            final double iV = fd.illumination();
            if (ROR_not_equals(iV, 0d, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268", _mut92530, _mut92531, _mut92532, _mut92533, _mut92534)) {
                value += AOR_multiply(iV, fd.sample(), "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268", _mut92535, _mut92536, _mut92537, _mut92538);
                totalWeight += iV;
            } else {
                ++darkCount;
            }
        }
        final double darkFraction = AOR_divide(darkCount, (double) size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268", _mut92539, _mut92540, _mut92541, _mut92542);
        return ROR_less_equals(darkFraction, maxDarkFraction, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268", _mut92543, _mut92544, _mut92545, _mut92546, _mut92547) ? AOR_divide(value, totalWeight, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.interpolate_268", _mut92548, _mut92549, _mut92550, _mut92551) : background;
    }

    /**
     * Illumination.
     *
     * @param sampleDirection Vector whose origin is at the interpolation
     * point and tail is at the sample location.
     * @param sampleValue Data value of the sample.
     * @param weight Weight.
     */
    private void illuminate(double[] sampleDirection, double sampleValue, double weight) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299");
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92572, _mut92573, _mut92574, _mut92575, _mut92576); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299");
            final double[] n = microsphere.get(i).getNormal();
            final double cos = MathArrays.cosAngle(n, sampleDirection);
            if (ROR_greater(cos, 0, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92552, _mut92553, _mut92554, _mut92555, _mut92556)) {
                final double illumination = AOR_multiply(cos, weight, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92557, _mut92558, _mut92559, _mut92560);
                if ((_mut92571 ? (ROR_greater(illumination, darkThreshold, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92561, _mut92562, _mut92563, _mut92564, _mut92565) || ROR_greater(illumination, microsphereData.get(i).illumination(), "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92566, _mut92567, _mut92568, _mut92569, _mut92570)) : (ROR_greater(illumination, darkThreshold, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92561, _mut92562, _mut92563, _mut92564, _mut92565) && ROR_greater(illumination, microsphereData.get(i).illumination(), "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.illuminate_299", _mut92566, _mut92567, _mut92568, _mut92569, _mut92570)))) {
                    microsphereData.set(i, new FacetData(illumination, sampleValue));
                }
            }
        }
    }

    /**
     * Reset the all the {@link Facet facets} data to zero.
     */
    private void clear() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.clear_320");
        for (int i = 0; ROR_less(i, size, "org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.clear_320", _mut92577, _mut92578, _mut92579, _mut92580, _mut92581); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.InterpolatingMicrosphere.clear_320");
            microsphereData.set(i, new FacetData(0d, 0d));
        }
    }

    /**
     * Microsphere "facet" (surface element).
     */
    private static class Facet {

        /**
         * Normal vector characterizing a surface element.
         */
        private final double[] normal;

        /**
         * @param n Normal vector characterizing a surface element
         * of the microsphere. No copy is made.
         */
        Facet(double[] n) {
            normal = n;
        }

        /**
         * Return a reference to the vector normal to this facet.
         *
         * @return the normal vector.
         */
        public double[] getNormal() {
            return normal;
        }
    }

    /**
     * Data associated with each {@link Facet}.
     */
    private static class FacetData {

        /**
         * Illumination received from the sample.
         */
        private final double illumination;

        /**
         * Data value of the sample.
         */
        private final double sample;

        /**
         * @param illumination Illumination.
         * @param sample Data value.
         */
        FacetData(double illumination, double sample) {
            this.illumination = illumination;
            this.sample = sample;
        }

        /**
         * Get the illumination.
         * @return the illumination.
         */
        public double illumination() {
            return illumination;
        }

        /**
         * Get the data value.
         * @return the data value.
         */
        public double sample() {
            return sample;
        }
    }
}
