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

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Generates a piecewise-bicubic interpolating function.
 *
 * @since 2.2
 */
public class PiecewiseBicubicSplineInterpolator implements BivariateGridInterpolator {

    @Conditional
    public static boolean _mut96247 = false, _mut96248 = false, _mut96249 = false, _mut96250 = false, _mut96251 = false, _mut96252 = false, _mut96253 = false, _mut96254 = false, _mut96255 = false, _mut96256 = false, _mut96257 = false, _mut96258 = false, _mut96259 = false, _mut96260 = false, _mut96261 = false, _mut96262 = false, _mut96263 = false, _mut96264 = false, _mut96265 = false, _mut96266 = false;

    /**
     * {@inheritDoc}
     */
    public PiecewiseBicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36");
        if ((_mut96249 ? ((_mut96248 ? ((_mut96247 ? (xval == null && yval == null) : (xval == null || yval == null)) && fval == null) : ((_mut96247 ? (xval == null && yval == null) : (xval == null || yval == null)) || fval == null)) && fval[0] == null) : ((_mut96248 ? ((_mut96247 ? (xval == null && yval == null) : (xval == null || yval == null)) && fval == null) : ((_mut96247 ? (xval == null && yval == null) : (xval == null || yval == null)) || fval == null)) || fval[0] == null))) {
            throw new NullArgumentException();
        }
        if ((_mut96266 ? ((_mut96260 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96250, _mut96251, _mut96252, _mut96253, _mut96254) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96255, _mut96256, _mut96257, _mut96258, _mut96259)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96250, _mut96251, _mut96252, _mut96253, _mut96254) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96255, _mut96256, _mut96257, _mut96258, _mut96259))) && ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96261, _mut96262, _mut96263, _mut96264, _mut96265)) : ((_mut96260 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96250, _mut96251, _mut96252, _mut96253, _mut96254) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96255, _mut96256, _mut96257, _mut96258, _mut96259)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96250, _mut96251, _mut96252, _mut96253, _mut96254) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96255, _mut96256, _mut96257, _mut96258, _mut96259))) || ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.PiecewiseBicubicSplineInterpolator.interpolate_36", _mut96261, _mut96262, _mut96263, _mut96264, _mut96265)))) {
            throw new NoDataException();
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        return new PiecewiseBicubicSplineInterpolatingFunction(xval, yval, fval);
    }
}
