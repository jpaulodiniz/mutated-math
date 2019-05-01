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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Adapter for classes implementing the {@link UnivariateInterpolator}
 * interface.
 * The data to be interpolated is assumed to be periodic. Thus values that are
 * outside of the range can be passed to the interpolation function: They will
 * be wrapped into the initial range before being passed to the class that
 * actually computes the interpolation.
 */
public class UnivariatePeriodicInterpolator implements UnivariateInterpolator {

    @Conditional
    public static boolean _mut92277 = false, _mut92278 = false, _mut92279 = false, _mut92280 = false, _mut92281 = false, _mut92282 = false, _mut92283 = false, _mut92284 = false, _mut92285 = false, _mut92286 = false, _mut92287 = false, _mut92288 = false, _mut92289 = false, _mut92290 = false, _mut92291 = false, _mut92292 = false, _mut92293 = false, _mut92294 = false, _mut92295 = false, _mut92296 = false, _mut92297 = false, _mut92298 = false, _mut92299 = false, _mut92300 = false, _mut92301 = false, _mut92302 = false, _mut92303 = false, _mut92304 = false, _mut92305 = false, _mut92306 = false, _mut92307 = false, _mut92308 = false, _mut92309 = false, _mut92310 = false, _mut92311 = false, _mut92312 = false, _mut92313 = false, _mut92314 = false, _mut92315 = false, _mut92316 = false, _mut92317 = false, _mut92318 = false, _mut92319 = false, _mut92320 = false, _mut92321 = false, _mut92322 = false, _mut92323 = false, _mut92324 = false, _mut92325 = false, _mut92326 = false, _mut92327 = false;

    /**
     * Default number of extension points of the samples array.
     */
    public static final int DEFAULT_EXTEND = 5;

    /**
     * Interpolator.
     */
    private final UnivariateInterpolator interpolator;

    /**
     * Period.
     */
    private final double period;

    /**
     * Number of extension points.
     */
    private final int extend;

    /**
     * Builds an interpolator.
     *
     * @param interpolator Interpolator.
     * @param period Period.
     * @param extend Number of points to be appended at the beginning and
     * end of the sample arrays in order to avoid interpolation failure at
     * the (periodic) boundaries of the orginal interval. The value is the
     * number of sample points which the original {@code interpolator} needs
     * on each side of the interpolated point.
     */
    public UnivariatePeriodicInterpolator(UnivariateInterpolator interpolator, double period, int extend) {
        this.interpolator = interpolator;
        this.period = period;
        this.extend = extend;
    }

    /**
     * Builds an interpolator.
     * Uses {@link #DEFAULT_EXTEND} as the number of extension points on each side
     * of the original abscissae range.
     *
     * @param interpolator Interpolator.
     * @param period Period.
     */
    public UnivariatePeriodicInterpolator(UnivariateInterpolator interpolator, double period) {
        this(interpolator, period, DEFAULT_EXTEND);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NumberIsTooSmallException if the number of extension points
     * is larger than the size of {@code xval}.
     */
    public UnivariateFunction interpolate(double[] xval, double[] yval) throws NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.value_119");
        if (ROR_less(xval.length, extend, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92277, _mut92278, _mut92279, _mut92280, _mut92281)) {
            throw new NumberIsTooSmallException(xval.length, extend, true);
        }
        MathArrays.checkOrder(xval);
        final double offset = xval[0];
        final int len = AOR_plus(xval.length, AOR_multiply(extend, 2, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92282, _mut92283, _mut92284, _mut92285), "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92286, _mut92287, _mut92288, _mut92289);
        final double[] x = new double[len];
        final double[] y = new double[len];
        for (int i = 0; ROR_less(i, xval.length, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92294, _mut92295, _mut92296, _mut92297, _mut92298); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84");
            final int index = AOR_plus(i, extend, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92290, _mut92291, _mut92292, _mut92293);
            x[index] = MathUtils.reduce(xval[i], period, offset);
            y[index] = yval[i];
        }
        // Wrap to enable interpolation at the boundaries.
        for (int i = 0; ROR_less(i, extend, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92323, _mut92324, _mut92325, _mut92326, _mut92327); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84");
            int index = AOR_plus(AOR_minus(xval.length, extend, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92299, _mut92300, _mut92301, _mut92302), i, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92303, _mut92304, _mut92305, _mut92306);
            x[i] = AOR_minus(MathUtils.reduce(xval[index], period, offset), period, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92307, _mut92308, _mut92309, _mut92310);
            y[i] = yval[index];
            index = AOR_plus(AOR_minus(len, extend, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92311, _mut92312, _mut92313, _mut92314), i, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92315, _mut92316, _mut92317, _mut92318);
            x[index] = AOR_plus(MathUtils.reduce(xval[i], period, offset), period, "org.apache.commons.math3.analysis.interpolation.UnivariatePeriodicInterpolator.interpolate_84", _mut92319, _mut92320, _mut92321, _mut92322);
            y[index] = yval[i];
        }
        MathArrays.sortInPlace(x, y);
        final UnivariateFunction f = interpolator.interpolate(x, y);
        return new UnivariateFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double x) throws MathIllegalArgumentException {
                return f.value(MathUtils.reduce(x, period, offset));
            }
        };
    }
}
