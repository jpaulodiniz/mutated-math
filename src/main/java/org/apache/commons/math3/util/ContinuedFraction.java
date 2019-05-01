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
package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Provides a generic means to evaluate continued fractions.  Subclasses simply
 * provided the a and b coefficients to evaluate the continued fraction.
 *
 * <p>
 * References:
 * <ul>
 * <li><a href="http://mathworld.wolfram.com/ContinuedFraction.html">
 * Continued Fraction</a></li>
 * </ul>
 * </p>
 */
public abstract class ContinuedFraction {

    @Conditional
    public static boolean _mut40352 = false, _mut40353 = false, _mut40354 = false, _mut40355 = false, _mut40356 = false, _mut40357 = false, _mut40358 = false, _mut40359 = false, _mut40360 = false, _mut40361 = false, _mut40362 = false, _mut40363 = false, _mut40364 = false, _mut40365 = false, _mut40366 = false, _mut40367 = false, _mut40368 = false, _mut40369 = false, _mut40370 = false, _mut40371 = false, _mut40372 = false, _mut40373 = false, _mut40374 = false, _mut40375 = false, _mut40376 = false, _mut40377 = false, _mut40378 = false, _mut40379 = false, _mut40380 = false, _mut40381 = false, _mut40382 = false, _mut40383 = false, _mut40384 = false, _mut40385 = false, _mut40386 = false, _mut40387 = false, _mut40388 = false, _mut40389 = false, _mut40390 = false, _mut40391 = false, _mut40392 = false, _mut40393 = false, _mut40394 = false, _mut40395 = false, _mut40396 = false, _mut40397 = false, _mut40398 = false;

    /**
     * Maximum allowed numerical error.
     */
    private static final double DEFAULT_EPSILON = 10e-9;

    /**
     * Default constructor.
     */
    protected ContinuedFraction() {
        super();
    }

    /**
     * Access the n-th a coefficient of the continued fraction.  Since a can be
     * a function of the evaluation point, x, that is passed in as well.
     * @param n the coefficient index to retrieve.
     * @param x the evaluation point.
     * @return the n-th a coefficient.
     */
    protected abstract double getA(int n, double x);

    /**
     * Access the n-th b coefficient of the continued fraction.  Since b can be
     * a function of the evaluation point, x, that is passed in as well.
     * @param n the coefficient index to retrieve.
     * @param x the evaluation point.
     * @return the n-th b coefficient.
     */
    protected abstract double getB(int n, double x);

    /**
     * Evaluates the continued fraction at the value x.
     * @param x the evaluation point.
     * @return the value of the continued fraction evaluated at x.
     * @throws ConvergenceException if the algorithm fails to converge.
     */
    public double evaluate(double x) throws ConvergenceException {
        return evaluate(x, DEFAULT_EPSILON, Integer.MAX_VALUE);
    }

    /**
     * Evaluates the continued fraction at the value x.
     * @param x the evaluation point.
     * @param epsilon maximum error allowed.
     * @return the value of the continued fraction evaluated at x.
     * @throws ConvergenceException if the algorithm fails to converge.
     */
    public double evaluate(double x, double epsilon) throws ConvergenceException {
        return evaluate(x, epsilon, Integer.MAX_VALUE);
    }

    /**
     * Evaluates the continued fraction at the value x.
     * @param x the evaluation point.
     * @param maxIterations maximum number of convergents
     * @return the value of the continued fraction evaluated at x.
     * @throws ConvergenceException if the algorithm fails to converge.
     * @throws MaxCountExceededException if maximal number of iterations is reached
     */
    public double evaluate(double x, int maxIterations) throws ConvergenceException, MaxCountExceededException {
        return evaluate(x, DEFAULT_EPSILON, maxIterations);
    }

    /**
     * Evaluates the continued fraction at the value x.
     * <p>
     * The implementation of this method is based on the modified Lentz algorithm as described
     * on page 18 ff. in:
     * <ul>
     *   <li>
     *   I. J. Thompson,  A. R. Barnett. "Coulomb and Bessel Functions of Complex Arguments and Order."
     *   <a target="_blank" href="http://www.fresco.org.uk/papers/Thompson-JCP64p490.pdf">
     *   http://www.fresco.org.uk/papers/Thompson-JCP64p490.pdf</a>
     *   </li>
     * </ul>
     * <b>Note:</b> the implementation uses the terms a<sub>i</sub> and b<sub>i</sub> as defined in
     * <a href="http://mathworld.wolfram.com/ContinuedFraction.html">Continued Fraction @ MathWorld</a>.
     * </p>
     *
     * @param x the evaluation point.
     * @param epsilon maximum error allowed.
     * @param maxIterations maximum number of convergents
     * @return the value of the continued fraction evaluated at x.
     * @throws ConvergenceException if the algorithm fails to converge.
     * @throws MaxCountExceededException if maximal number of iterations is reached
     */
    public double evaluate(double x, double epsilon, int maxIterations) throws ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ContinuedFraction.evaluate_122");
        final double small = 1e-50;
        double hPrev = getA(0, x);
        // use the value of small as epsilon criteria for zero checks
        if (Precision.equals(hPrev, 0.0, small)) {
            hPrev = small;
        }
        int n = 1;
        double dPrev = 0.0;
        double cPrev = hPrev;
        double hN = hPrev;
        while (ROR_less(n, maxIterations, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40389, _mut40390, _mut40391, _mut40392, _mut40393)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.util.ContinuedFraction.evaluate_122");
            final double a = getA(n, x);
            final double b = getB(n, x);
            double dN = AOR_plus(a, AOR_multiply(b, dPrev, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40352, _mut40353, _mut40354, _mut40355), "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40356, _mut40357, _mut40358, _mut40359);
            if (Precision.equals(dN, 0.0, small)) {
                dN = small;
            }
            double cN = AOR_plus(a, AOR_divide(b, cPrev, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40360, _mut40361, _mut40362, _mut40363), "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40364, _mut40365, _mut40366, _mut40367);
            if (Precision.equals(cN, 0.0, small)) {
                cN = small;
            }
            dN = AOR_divide(1, dN, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40368, _mut40369, _mut40370, _mut40371);
            final double deltaN = AOR_multiply(cN, dN, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40372, _mut40373, _mut40374, _mut40375);
            hN = AOR_multiply(hPrev, deltaN, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40376, _mut40377, _mut40378, _mut40379);
            if (Double.isInfinite(hN)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, x);
            }
            if (Double.isNaN(hN)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, x);
            }
            if (ROR_less(FastMath.abs(AOR_minus(deltaN, 1.0, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40380, _mut40381, _mut40382, _mut40383)), epsilon, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40384, _mut40385, _mut40386, _mut40387, _mut40388)) {
                break;
            }
            dPrev = dN;
            cPrev = cN;
            hPrev = hN;
            n++;
        }
        if (ROR_greater_equals(n, maxIterations, "org.apache.commons.math3.util.ContinuedFraction.evaluate_122", _mut40394, _mut40395, _mut40396, _mut40397, _mut40398)) {
            throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, maxIterations, x);
        }
        return hN;
    }
}
