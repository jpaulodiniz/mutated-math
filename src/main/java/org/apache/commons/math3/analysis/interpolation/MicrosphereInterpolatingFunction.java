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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Interpolating function that implements the
 * <a href="http://www.dudziak.com/microsphere.php">Microsphere Projection</a>.
 *
 * @deprecated Code will be removed in 4.0.  Use {@link InterpolatingMicrosphere}
 * and {@link MicrosphereProjectionInterpolator} instead.
 */
@Deprecated
public class MicrosphereInterpolatingFunction implements MultivariateFunction {

    @Conditional
    public static boolean _mut95091 = false, _mut95092 = false, _mut95093 = false, _mut95094 = false, _mut95095 = false, _mut95096 = false, _mut95097 = false, _mut95098 = false, _mut95099 = false, _mut95100 = false, _mut95101 = false, _mut95102 = false, _mut95103 = false, _mut95104 = false, _mut95105 = false, _mut95106 = false, _mut95107 = false, _mut95108 = false, _mut95109 = false, _mut95110 = false, _mut95111 = false, _mut95112 = false, _mut95113 = false, _mut95114 = false, _mut95115 = false, _mut95116 = false, _mut95117 = false, _mut95118 = false, _mut95119 = false, _mut95120 = false, _mut95121 = false, _mut95122 = false, _mut95123 = false, _mut95124 = false, _mut95125 = false, _mut95126 = false, _mut95127 = false, _mut95128 = false, _mut95129 = false, _mut95130 = false, _mut95131 = false, _mut95132 = false, _mut95133 = false, _mut95134 = false, _mut95135 = false, _mut95136 = false, _mut95137 = false, _mut95138 = false, _mut95139 = false, _mut95140 = false, _mut95141 = false, _mut95142 = false, _mut95143 = false, _mut95144 = false, _mut95145 = false, _mut95146 = false;

    /**
     * Space dimension.
     */
    private final int dimension;

    /**
     * Internal accounting data for the interpolation algorithm.
     * Each element of the list corresponds to one surface element of
     * the microsphere.
     */
    private final List<MicrosphereSurfaceElement> microsphere;

    /**
     * Exponent used in the power law that computes the weights of the
     * sample data.
     */
    private final double brightnessExponent;

    /**
     * Sample data.
     */
    private final Map<RealVector, Double> samples;

    /**
     * Class for storing the accounting data needed to perform the
     * microsphere projection.
     */
    private static class MicrosphereSurfaceElement {

        /**
         * Normal vector characterizing a surface element.
         */
        private final RealVector normal;

        /**
         * Illumination received from the brightest sample.
         */
        private double brightestIllumination;

        /**
         * Brightest sample.
         */
        private Map.Entry<RealVector, Double> brightestSample;

        /**
         * @param n Normal vector characterizing a surface element
         * of the microsphere.
         */
        MicrosphereSurfaceElement(double[] n) {
            normal = new ArrayRealVector(n);
        }

        /**
         * Return the normal vector.
         * @return the normal vector
         */
        RealVector normal() {
            return normal;
        }

        /**
         * Reset "illumination" and "sampleIndex".
         */
        void reset() {
            brightestIllumination = 0;
            brightestSample = null;
        }

        /**
         * Store the illumination and index of the brightest sample.
         * @param illuminationFromSample illumination received from sample
         * @param sample current sample illuminating the element
         */
        void store(final double illuminationFromSample, final Map.Entry<RealVector, Double> sample) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.store_104");
            if (ROR_greater(illuminationFromSample, this.brightestIllumination, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.store_104", _mut95091, _mut95092, _mut95093, _mut95094, _mut95095)) {
                this.brightestIllumination = illuminationFromSample;
                this.brightestSample = sample;
            }
        }

        /**
         * Get the illumination of the element.
         * @return the illumination.
         */
        double illumination() {
            return brightestIllumination;
        }

        /**
         * Get the sample illuminating the element the most.
         * @return the sample.
         */
        Map.Entry<RealVector, Double> sample() {
            return brightestSample;
        }
    }

    /**
     * @param xval Arguments for the interpolation points.
     * {@code xval[i][0]} is the first component of interpolation point
     * {@code i}, {@code xval[i][1]} is the second component, and so on
     * until {@code xval[i][d-1]}, the last component of that interpolation
     * point (where {@code dimension} is thus the dimension of the sampled
     * space).
     * @param yval Values for the interpolation points.
     * @param brightnessExponent Brightness dimming factor.
     * @param microsphereElements Number of surface elements of the
     * microsphere.
     * @param rand Unit vector generator for creating the microsphere.
     * @throws DimensionMismatchException if the lengths of {@code yval} and
     * {@code xval} (equal to {@code n}, the number of interpolation points)
     * do not match, or the the arrays {@code xval[0]} ... {@code xval[n]},
     * have lengths different from {@code dimension}.
     * @throws NoDataException if there an array has zero-length.
     * @throws NullArgumentException if an argument is {@code null}.
     */
    public MicrosphereInterpolatingFunction(double[][] xval, double[] yval, int brightnessExponent, int microsphereElements, UnitSphereRandomVectorGenerator rand) throws DimensionMismatchException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148");
        if ((_mut95096 ? (xval == null && yval == null) : (xval == null || yval == null))) {
            throw new NullArgumentException();
        }
        if (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148", _mut95097, _mut95098, _mut95099, _mut95100, _mut95101)) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, yval.length, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148", _mut95102, _mut95103, _mut95104, _mut95105, _mut95106)) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        if (xval[0] == null) {
            throw new NullArgumentException();
        }
        dimension = xval[0].length;
        this.brightnessExponent = brightnessExponent;
        // Copy data samples.
        samples = new HashMap<RealVector, Double>(yval.length);
        for (int i = 0; ROR_less(i, xval.length, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148", _mut95112, _mut95113, _mut95114, _mut95115, _mut95116); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148");
            final double[] xvalI = xval[i];
            if (xvalI == null) {
                throw new NullArgumentException();
            }
            if (ROR_not_equals(xvalI.length, dimension, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148", _mut95107, _mut95108, _mut95109, _mut95110, _mut95111)) {
                throw new DimensionMismatchException(xvalI.length, dimension);
            }
            samples.put(new ArrayRealVector(xvalI), yval[i]);
        }
        microsphere = new ArrayList<MicrosphereSurfaceElement>(microsphereElements);
        // randomly generated normals will represent a sphere.
        for (int i = 0; ROR_less(i, microsphereElements, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148", _mut95117, _mut95118, _mut95119, _mut95120, _mut95121); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.MicrosphereInterpolatingFunction_148");
            microsphere.add(new MicrosphereSurfaceElement(rand.nextVector()));
        }
    }

    /**
     * @param point Interpolation point.
     * @return the interpolated value.
     * @throws DimensionMismatchException if point dimension does not math sample
     */
    public double value(double[] point) throws DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200");
        final RealVector p = new ArrayRealVector(point);
        // Reset.
        for (MicrosphereSurfaceElement md : microsphere) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200");
            md.reset();
        }
        // Compute contribution of each sample points to the microsphere elements illumination
        for (Map.Entry<RealVector, Double> sd : samples.entrySet()) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200");
            // Vector between interpolation point and current sample point.
            final RealVector diff = sd.getKey().subtract(p);
            final double diffNorm = diff.getNorm();
            if (ROR_less(FastMath.abs(diffNorm), FastMath.ulp(1d), "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200", _mut95122, _mut95123, _mut95124, _mut95125, _mut95126)) {
                // actually (very close to) one of the sampled points.
                return sd.getValue();
            }
            for (MicrosphereSurfaceElement md : microsphere) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200");
                final double w = FastMath.pow(diffNorm, -brightnessExponent);
                md.store(AOR_multiply(cosAngle(diff, md.normal()), w, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200", _mut95127, _mut95128, _mut95129, _mut95130), sd);
            }
        }
        // Interpolation calculation.
        double value = 0;
        double totalWeight = 0;
        for (MicrosphereSurfaceElement md : microsphere) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200");
            final double iV = md.illumination();
            final Map.Entry<RealVector, Double> sd = md.sample();
            if (sd != null) {
                value += AOR_multiply(iV, sd.getValue(), "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200", _mut95131, _mut95132, _mut95133, _mut95134);
                totalWeight += iV;
            }
        }
        return AOR_divide(value, totalWeight, "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.value_200", _mut95135, _mut95136, _mut95137, _mut95138);
    }

    /**
     * Compute the cosine of the angle between 2 vectors.
     *
     * @param v Vector.
     * @param w Vector.
     * @return the cosine of the angle between {@code v} and {@code w}.
     */
    private double cosAngle(final RealVector v, final RealVector w) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.cosAngle_250");
        return AOR_divide(v.dotProduct(w), (AOR_multiply(v.getNorm(), w.getNorm(), "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.cosAngle_250", _mut95139, _mut95140, _mut95141, _mut95142)), "org.apache.commons.math3.analysis.interpolation.MicrosphereInterpolatingFunction.cosAngle_250", _mut95143, _mut95144, _mut95145, _mut95146);
    }
}
