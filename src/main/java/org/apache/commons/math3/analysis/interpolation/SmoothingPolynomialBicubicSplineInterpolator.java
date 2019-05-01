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
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.fitting.PolynomialFitter;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Generates a bicubic interpolation function.
 * Prior to generating the interpolating function, the input is smoothed using
 * polynomial fitting.
 *
 * @since 2.2
 * @deprecated To be removed in 4.0 (see MATH-1166).
 */
@Deprecated
public class SmoothingPolynomialBicubicSplineInterpolator extends BicubicSplineInterpolator {

    @Conditional
    public static boolean _mut95349 = false, _mut95350 = false, _mut95351 = false, _mut95352 = false, _mut95353 = false, _mut95354 = false, _mut95355 = false, _mut95356 = false, _mut95357 = false, _mut95358 = false, _mut95359 = false, _mut95360 = false, _mut95361 = false, _mut95362 = false, _mut95363 = false, _mut95364 = false, _mut95365 = false, _mut95366 = false, _mut95367 = false, _mut95368 = false, _mut95369 = false, _mut95370 = false, _mut95371 = false, _mut95372 = false, _mut95373 = false, _mut95374 = false, _mut95375 = false, _mut95376 = false, _mut95377 = false, _mut95378 = false, _mut95379 = false, _mut95380 = false, _mut95381 = false, _mut95382 = false, _mut95383 = false, _mut95384 = false, _mut95385 = false, _mut95386 = false, _mut95387 = false, _mut95388 = false, _mut95389 = false, _mut95390 = false, _mut95391 = false, _mut95392 = false, _mut95393 = false, _mut95394 = false, _mut95395 = false, _mut95396 = false, _mut95397 = false, _mut95398 = false, _mut95399 = false, _mut95400 = false, _mut95401 = false, _mut95402 = false, _mut95403 = false, _mut95404 = false, _mut95405 = false, _mut95406 = false, _mut95407 = false, _mut95408 = false, _mut95409 = false, _mut95410 = false, _mut95411 = false, _mut95412 = false, _mut95413 = false, _mut95414 = false, _mut95415 = false, _mut95416 = false, _mut95417 = false, _mut95418 = false, _mut95419 = false, _mut95420 = false, _mut95421 = false, _mut95422 = false, _mut95423 = false, _mut95424 = false, _mut95425 = false, _mut95426 = false, _mut95427 = false, _mut95428 = false, _mut95429 = false, _mut95430 = false, _mut95431 = false, _mut95432 = false, _mut95433 = false, _mut95434 = false, _mut95435 = false, _mut95436 = false, _mut95437 = false, _mut95438 = false, _mut95439 = false, _mut95440 = false, _mut95441 = false, _mut95442 = false, _mut95443 = false, _mut95444 = false, _mut95445 = false, _mut95446 = false;

    /**
     * Fitter for x.
     */
    private final PolynomialFitter xFitter;

    /**
     * Degree of the fitting polynomial.
     */
    private final int xDegree;

    /**
     * Fitter for y.
     */
    private final PolynomialFitter yFitter;

    /**
     * Degree of the fitting polynomial.
     */
    private final int yDegree;

    /**
     * Default constructor. The degree of the fitting polynomials is set to 3.
     */
    public SmoothingPolynomialBicubicSplineInterpolator() {
        this(3);
    }

    /**
     * @param degree Degree of the polynomial fitting functions.
     * @exception NotPositiveException if degree is not positive
     */
    public SmoothingPolynomialBicubicSplineInterpolator(int degree) throws NotPositiveException {
        this(degree, degree);
    }

    /**
     * @param xDegree Degree of the polynomial fitting functions along the
     * x-dimension.
     * @param yDegree Degree of the polynomial fitting functions along the
     * y-dimension.
     * @exception NotPositiveException if degrees are not positive
     */
    public SmoothingPolynomialBicubicSplineInterpolator(int xDegree, int yDegree) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.SmoothingPolynomialBicubicSplineInterpolator_74");
        if (ROR_less(xDegree, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.SmoothingPolynomialBicubicSplineInterpolator_74", _mut95349, _mut95350, _mut95351, _mut95352, _mut95353)) {
            throw new NotPositiveException(xDegree);
        }
        if (ROR_less(yDegree, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.SmoothingPolynomialBicubicSplineInterpolator_74", _mut95354, _mut95355, _mut95356, _mut95357, _mut95358)) {
            throw new NotPositiveException(yDegree);
        }
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        final double safeFactor = 1e2;
        final SimpleVectorValueChecker checker = new SimpleVectorValueChecker(AOR_multiply(safeFactor, Precision.EPSILON, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.SmoothingPolynomialBicubicSplineInterpolator_74", _mut95359, _mut95360, _mut95361, _mut95362), AOR_multiply(safeFactor, Precision.SAFE_MIN, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.SmoothingPolynomialBicubicSplineInterpolator_74", _mut95363, _mut95364, _mut95365, _mut95366));
        xFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
        yFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BicubicSplineInterpolatingFunction interpolate(final double[] xval, final double[] yval, final double[][] fval) throws NoDataException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
        if ((_mut95383 ? ((_mut95377 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95367, _mut95368, _mut95369, _mut95370, _mut95371) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95372, _mut95373, _mut95374, _mut95375, _mut95376)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95367, _mut95368, _mut95369, _mut95370, _mut95371) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95372, _mut95373, _mut95374, _mut95375, _mut95376))) && ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95378, _mut95379, _mut95380, _mut95381, _mut95382)) : ((_mut95377 ? (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95367, _mut95368, _mut95369, _mut95370, _mut95371) && ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95372, _mut95373, _mut95374, _mut95375, _mut95376)) : (ROR_equals(xval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95367, _mut95368, _mut95369, _mut95370, _mut95371) || ROR_equals(yval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95372, _mut95373, _mut95374, _mut95375, _mut95376))) || ROR_equals(fval.length, 0, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95378, _mut95379, _mut95380, _mut95381, _mut95382)))) {
            throw new NoDataException();
        }
        if (ROR_not_equals(xval.length, fval.length, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95384, _mut95385, _mut95386, _mut95387, _mut95388)) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        final int xLen = xval.length;
        final int yLen = yval.length;
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95394, _mut95395, _mut95396, _mut95397, _mut95398); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
            if (ROR_not_equals(fval[i].length, yLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95389, _mut95390, _mut95391, _mut95392, _mut95393)) {
                throw new DimensionMismatchException(fval[i].length, yLen);
            }
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        // respect to variable x, fitting array fval[][j]
        final PolynomialFunction[] yPolyX = new PolynomialFunction[yLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95408, _mut95409, _mut95410, _mut95411, _mut95412); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
            xFitter.clearObservations();
            for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95399, _mut95400, _mut95401, _mut95402, _mut95403); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
                xFitter.addObservedPoint(1, xval[i], fval[i][j]);
            }
            // there are "xDegree" + 1).
            yPolyX[j] = new PolynomialFunction(xFitter.fit(new double[AOR_plus(xDegree, 1, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95404, _mut95405, _mut95406, _mut95407)]));
        }
        // values fval_1
        final double[][] fval_1 = new double[xLen][yLen];
        for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95418, _mut95419, _mut95420, _mut95421, _mut95422); j++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
            final PolynomialFunction f = yPolyX[j];
            for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95413, _mut95414, _mut95415, _mut95416, _mut95417); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
                fval_1[i][j] = f.value(xval[i]);
            }
        }
        // respect to variable y, fitting array fval_1[i][]
        final PolynomialFunction[] xPolyY = new PolynomialFunction[xLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95432, _mut95433, _mut95434, _mut95435, _mut95436); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
            yFitter.clearObservations();
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95423, _mut95424, _mut95425, _mut95426, _mut95427); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
                yFitter.addObservedPoint(1, yval[j], fval_1[i][j]);
            }
            // there are "yDegree" + 1).
            xPolyY[i] = new PolynomialFunction(yFitter.fit(new double[AOR_plus(yDegree, 1, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95428, _mut95429, _mut95430, _mut95431)]));
        }
        // values fval_2
        final double[][] fval_2 = new double[xLen][yLen];
        for (int i = 0; ROR_less(i, xLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95442, _mut95443, _mut95444, _mut95445, _mut95446); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
            final PolynomialFunction f = xPolyY[i];
            for (int j = 0; ROR_less(j, yLen, "org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96", _mut95437, _mut95438, _mut95439, _mut95440, _mut95441); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.SmoothingPolynomialBicubicSplineInterpolator.interpolate_96");
                fval_2[i][j] = f.value(yval[j]);
            }
        }
        return super.interpolate(xval, yval, fval_2);
    }
}
