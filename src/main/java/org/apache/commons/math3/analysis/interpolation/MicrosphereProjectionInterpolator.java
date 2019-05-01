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

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.random.UnitSphereRandomVectorGenerator;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Interpolator that implements the algorithm described in
 * <em>William Dudziak</em>'s
 * <a href="http://www.dudziak.com/microsphere.pdf">MS thesis</a>.
 *
 * @since 3.6
 */
public class MicrosphereProjectionInterpolator implements MultivariateInterpolator {

    @Conditional
    public static boolean _mut96267 = false, _mut96268 = false, _mut96269 = false, _mut96270 = false, _mut96271 = false, _mut96272 = false, _mut96273 = false, _mut96274 = false, _mut96275 = false, _mut96276 = false, _mut96277 = false, _mut96278 = false, _mut96279 = false, _mut96280 = false, _mut96281 = false, _mut96282 = false, _mut96283 = false, _mut96284 = false, _mut96285 = false, _mut96286 = false, _mut96287 = false;

    /**
     * Brightness exponent.
     */
    private final double exponent;

    /**
     * Microsphere.
     */
    private final InterpolatingMicrosphere microsphere;

    /**
     * Whether to share the sphere.
     */
    private final boolean sharedSphere;

    /**
     * Tolerance value below which no interpolation is necessary.
     */
    private final double noInterpolationTolerance;

    /**
     * Create a microsphere interpolator.
     *
     * @param dimension Space dimension.
     * @param elements Number of surface elements of the microsphere.
     * @param exponent Exponent used in the power law that computes the
     * @param maxDarkFraction Maximum fraction of the facets that can be dark.
     * If the fraction of "non-illuminated" facets is larger, no estimation
     * of the value will be performed, and the {@code background} value will
     * be returned instead.
     * @param darkThreshold Value of the illumination below which a facet is
     * considered dark.
     * @param background Value returned when the {@code maxDarkFraction}
     * threshold is exceeded.
     * @param sharedSphere Whether the sphere can be shared among the
     * interpolating function instances.  If {@code true}, the instances
     * will share the same data, and thus will <em>not</em> be thread-safe.
     * @param noInterpolationTolerance When the distance between an
     * interpolated point and one of the sample points is less than this
     * value, no interpolation will be performed (the value of the sample
     * will be returned).
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if {@code dimension <= 0} or {@code elements <= 0}.
     * @throws NotPositiveException if {@code exponent < 0}.
     * @throws NotPositiveException if {@code darkThreshold < 0}.
     * @throws org.apache.commons.math3.exception.OutOfRangeException if
     * {@code maxDarkFraction} does not belong to the interval {@code [0, 1]}.
     */
    public MicrosphereProjectionInterpolator(int dimension, int elements, double maxDarkFraction, double darkThreshold, double background, double exponent, boolean sharedSphere, double noInterpolationTolerance) {
        this(new InterpolatingMicrosphere(dimension, elements, maxDarkFraction, darkThreshold, background, new UnitSphereRandomVectorGenerator(dimension)), exponent, sharedSphere, noInterpolationTolerance);
    }

    /**
     * Create a microsphere interpolator.
     *
     * @param microsphere Microsphere.
     * @param exponent Exponent used in the power law that computes the
     * weights (distance dimming factor) of the sample data.
     * @param sharedSphere Whether the sphere can be shared among the
     * interpolating function instances.  If {@code true}, the instances
     * will share the same data, and thus will <em>not</em> be thread-safe.
     * @param noInterpolationTolerance When the distance between an
     * interpolated point and one of the sample points is less than this
     * value, no interpolation will be performed (the value of the sample
     * will be returned).
     * @throws NotPositiveException if {@code exponent < 0}.
     */
    public MicrosphereProjectionInterpolator(InterpolatingMicrosphere microsphere, double exponent, boolean sharedSphere, double noInterpolationTolerance) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.MicrosphereProjectionInterpolator_106");
        if (ROR_less(exponent, 0, "org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.MicrosphereProjectionInterpolator_106", _mut96267, _mut96268, _mut96269, _mut96270, _mut96271)) {
            throw new NotPositiveException(exponent);
        }
        this.microsphere = microsphere;
        this.exponent = exponent;
        this.sharedSphere = sharedSphere;
        this.noInterpolationTolerance = noInterpolationTolerance;
    }

    /**
     * {@inheritDoc}
     *
     * @throws DimensionMismatchException if the space dimension of the
     * given samples does not match the space dimension of the microsphere.
     */
    public MultivariateFunction interpolate(final double[][] xval, final double[] yval) throws DimensionMismatchException, NoDataException, NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.value_155");
        if ((_mut96272 ? (xval == null && yval == null) : (xval == null || yval == null))) {
            throw new NullArgumentException();
        }
        if (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.interpolate_127", _mut96273, _mut96274, _mut96275, _mut96276, _mut96277)) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, yval.length, "org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.interpolate_127", _mut96278, _mut96279, _mut96280, _mut96281, _mut96282)) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        if (xval[0] == null) {
            throw new NullArgumentException();
        }
        final int dimension = microsphere.getDimension();
        if (ROR_not_equals(dimension, xval[0].length, "org.apache.commons.math3.analysis.interpolation.MicrosphereProjectionInterpolator.interpolate_127", _mut96283, _mut96284, _mut96285, _mut96286, _mut96287)) {
            throw new DimensionMismatchException(xval[0].length, dimension);
        }
        // Microsphere copy.
        final InterpolatingMicrosphere m = sharedSphere ? microsphere : microsphere.copy();
        return new MultivariateFunction() {

            /**
             * {inheritDoc}
             */
            public double value(double[] point) {
                return m.value(point, xval, yval, exponent, noInterpolationTolerance);
            }
        };
    }
}
