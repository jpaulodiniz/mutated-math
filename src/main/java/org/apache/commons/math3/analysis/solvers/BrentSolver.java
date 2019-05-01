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
package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the <a href="http://mathworld.wolfram.com/BrentsMethod.html">
 * Brent algorithm</a> for finding zeros of real univariate functions.
 * The function should be continuous but not necessarily smooth.
 * The {@code solve} method returns a zero {@code x} of the function {@code f}
 * in the given interval {@code [a, b]} to within a tolerance
 * {@code 2 eps abs(x) + t} where {@code eps} is the relative accuracy and
 * {@code t} is the absolute accuracy.
 * <p>The given interval must bracket the root.</p>
 * <p>
 *  The reference implementation is given in chapter 4 of
 *  <blockquote>
 *   <b>Algorithms for Minimization Without Derivatives</b>,
 *   <em>Richard P. Brent</em>,
 *   Dover, 2002
 *  </blockquote>
 *
 * @see BaseAbstractUnivariateSolver
 */
public class BrentSolver extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut100366 = false, _mut100367 = false, _mut100368 = false, _mut100369 = false, _mut100370 = false, _mut100371 = false, _mut100372 = false, _mut100373 = false, _mut100374 = false, _mut100375 = false, _mut100376 = false, _mut100377 = false, _mut100378 = false, _mut100379 = false, _mut100380 = false, _mut100381 = false, _mut100382 = false, _mut100383 = false, _mut100384 = false, _mut100385 = false, _mut100386 = false, _mut100387 = false, _mut100388 = false, _mut100389 = false, _mut100390 = false, _mut100391 = false, _mut100392 = false, _mut100393 = false, _mut100394 = false, _mut100395 = false, _mut100396 = false, _mut100397 = false, _mut100398 = false, _mut100399 = false, _mut100400 = false, _mut100401 = false, _mut100402 = false, _mut100403 = false, _mut100404 = false, _mut100405 = false, _mut100406 = false, _mut100407 = false, _mut100408 = false, _mut100409 = false, _mut100410 = false, _mut100411 = false, _mut100412 = false, _mut100413 = false, _mut100414 = false, _mut100415 = false, _mut100416 = false, _mut100417 = false, _mut100418 = false, _mut100419 = false, _mut100420 = false, _mut100421 = false, _mut100422 = false, _mut100423 = false, _mut100424 = false, _mut100425 = false, _mut100426 = false, _mut100427 = false, _mut100428 = false, _mut100429 = false, _mut100430 = false, _mut100431 = false, _mut100432 = false, _mut100433 = false, _mut100434 = false, _mut100435 = false, _mut100436 = false, _mut100437 = false, _mut100438 = false, _mut100439 = false, _mut100440 = false, _mut100441 = false, _mut100442 = false, _mut100443 = false, _mut100444 = false, _mut100445 = false, _mut100446 = false, _mut100447 = false, _mut100448 = false, _mut100449 = false, _mut100450 = false, _mut100451 = false, _mut100452 = false, _mut100453 = false, _mut100454 = false, _mut100455 = false, _mut100456 = false, _mut100457 = false, _mut100458 = false, _mut100459 = false, _mut100460 = false, _mut100461 = false, _mut100462 = false, _mut100463 = false, _mut100464 = false, _mut100465 = false, _mut100466 = false, _mut100467 = false, _mut100468 = false, _mut100469 = false, _mut100470 = false, _mut100471 = false, _mut100472 = false, _mut100473 = false, _mut100474 = false, _mut100475 = false, _mut100476 = false, _mut100477 = false, _mut100478 = false, _mut100479 = false, _mut100480 = false, _mut100481 = false, _mut100482 = false, _mut100483 = false, _mut100484 = false, _mut100485 = false, _mut100486 = false, _mut100487 = false, _mut100488 = false, _mut100489 = false, _mut100490 = false, _mut100491 = false, _mut100492 = false, _mut100493 = false, _mut100494 = false, _mut100495 = false, _mut100496 = false, _mut100497 = false, _mut100498 = false, _mut100499 = false, _mut100500 = false, _mut100501 = false, _mut100502 = false, _mut100503 = false, _mut100504 = false, _mut100505 = false, _mut100506 = false, _mut100507 = false, _mut100508 = false, _mut100509 = false, _mut100510 = false, _mut100511 = false, _mut100512 = false, _mut100513 = false, _mut100514 = false, _mut100515 = false, _mut100516 = false, _mut100517 = false, _mut100518 = false, _mut100519 = false, _mut100520 = false, _mut100521 = false, _mut100522 = false, _mut100523 = false, _mut100524 = false, _mut100525 = false, _mut100526 = false, _mut100527 = false, _mut100528 = false, _mut100529 = false, _mut100530 = false, _mut100531 = false, _mut100532 = false, _mut100533 = false, _mut100534 = false, _mut100535 = false, _mut100536 = false, _mut100537 = false, _mut100538 = false, _mut100539 = false, _mut100540 = false, _mut100541 = false, _mut100542 = false, _mut100543 = false, _mut100544 = false, _mut100545 = false, _mut100546 = false, _mut100547 = false, _mut100548 = false, _mut100549 = false, _mut100550 = false, _mut100551 = false, _mut100552 = false, _mut100553 = false, _mut100554 = false, _mut100555 = false, _mut100556 = false, _mut100557 = false, _mut100558 = false, _mut100559 = false, _mut100560 = false, _mut100561 = false, _mut100562 = false, _mut100563 = false, _mut100564 = false, _mut100565 = false, _mut100566 = false, _mut100567 = false, _mut100568 = false, _mut100569 = false, _mut100570 = false, _mut100571 = false, _mut100572 = false, _mut100573 = false, _mut100574 = false, _mut100575 = false, _mut100576 = false, _mut100577 = false, _mut100578 = false, _mut100579 = false, _mut100580 = false, _mut100581 = false, _mut100582 = false, _mut100583 = false, _mut100584 = false, _mut100585 = false, _mut100586 = false, _mut100587 = false, _mut100588 = false, _mut100589 = false, _mut100590 = false, _mut100591 = false, _mut100592 = false, _mut100593 = false, _mut100594 = false, _mut100595 = false, _mut100596 = false, _mut100597 = false, _mut100598 = false, _mut100599 = false, _mut100600 = false, _mut100601 = false, _mut100602 = false, _mut100603 = false, _mut100604 = false, _mut100605 = false, _mut100606 = false, _mut100607 = false, _mut100608 = false, _mut100609 = false, _mut100610 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default absolute accuracy (1e-6).
     */
    public BrentSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public BrentSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public BrentSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     *
     * @see BaseAbstractUnivariateSolver#BaseAbstractUnivariateSolver(double,double,double)
     */
    public BrentSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws NoBracketingException, TooManyEvaluationsException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92");
        double min = getMin();
        double max = getMax();
        final double initial = getStartValue();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, initial, max);
        // Return the initial guess if it is good enough.
        double yInitial = computeObjectiveValue(initial);
        if (ROR_less_equals(FastMath.abs(yInitial), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100366, _mut100367, _mut100368, _mut100369, _mut100370)) {
            return initial;
        }
        // Return the first endpoint if it is good enough.
        double yMin = computeObjectiveValue(min);
        if (ROR_less_equals(FastMath.abs(yMin), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100371, _mut100372, _mut100373, _mut100374, _mut100375)) {
            return min;
        }
        // Reduce interval if min and initial bracket the root.
        if (ROR_less(AOR_multiply(yInitial, yMin, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100376, _mut100377, _mut100378, _mut100379), 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100380, _mut100381, _mut100382, _mut100383, _mut100384)) {
            return brent(min, initial, yMin, yInitial);
        }
        // Return the second endpoint if it is good enough.
        double yMax = computeObjectiveValue(max);
        if (ROR_less_equals(FastMath.abs(yMax), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100385, _mut100386, _mut100387, _mut100388, _mut100389)) {
            return max;
        }
        // Reduce interval if initial and max bracket the root.
        if (ROR_less(AOR_multiply(yInitial, yMax, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100390, _mut100391, _mut100392, _mut100393), 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.doSolve_92", _mut100394, _mut100395, _mut100396, _mut100397, _mut100398)) {
            return brent(initial, max, yInitial, yMax);
        }
        throw new NoBracketingException(min, max, yMin, yMax);
    }

    /**
     * Search for a zero inside the provided interval.
     * This implementation is based on the algorithm described at page 58 of
     * the book
     * <blockquote>
     *  <b>Algorithms for Minimization Without Derivatives</b>,
     *  <it>Richard P. Brent</it>,
     *  Dover 0-486-41998-3
     * </blockquote>
     *
     * @param lo Lower bound of the search interval.
     * @param hi Higher bound of the search interval.
     * @param fLo Function value at the lower bound of the search interval.
     * @param fHi Function value at the higher bound of the search interval.
     * @return the value where the function is zero.
     */
    private double brent(double lo, double hi, double fLo, double fHi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151");
        double a = lo;
        double fa = fLo;
        double b = hi;
        double fb = fHi;
        double c = a;
        double fc = fa;
        double d = AOR_minus(b, a, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100399, _mut100400, _mut100401, _mut100402);
        double e = d;
        final double t = getAbsoluteAccuracy();
        final double eps = getRelativeAccuracy();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151");
            if (ROR_less(FastMath.abs(fc), FastMath.abs(fb), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100403, _mut100404, _mut100405, _mut100406, _mut100407)) {
                a = b;
                b = c;
                c = a;
                fa = fb;
                fb = fc;
                fc = fa;
            }
            final double tol = AOR_plus(AOR_multiply(AOR_multiply(2, eps, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100408, _mut100409, _mut100410, _mut100411), FastMath.abs(b), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100412, _mut100413, _mut100414, _mut100415), t, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100416, _mut100417, _mut100418, _mut100419);
            final double m = AOR_multiply(0.5, (AOR_minus(c, b, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100420, _mut100421, _mut100422, _mut100423)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100424, _mut100425, _mut100426, _mut100427);
            if ((_mut100433 ? (ROR_less_equals(FastMath.abs(m), tol, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100428, _mut100429, _mut100430, _mut100431, _mut100432) && Precision.equals(fb, 0)) : (ROR_less_equals(FastMath.abs(m), tol, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100428, _mut100429, _mut100430, _mut100431, _mut100432) || Precision.equals(fb, 0)))) {
                return b;
            }
            if ((_mut100444 ? (ROR_less(FastMath.abs(e), tol, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100434, _mut100435, _mut100436, _mut100437, _mut100438) && ROR_less_equals(FastMath.abs(fa), FastMath.abs(fb), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100439, _mut100440, _mut100441, _mut100442, _mut100443)) : (ROR_less(FastMath.abs(e), tol, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100434, _mut100435, _mut100436, _mut100437, _mut100438) || ROR_less_equals(FastMath.abs(fa), FastMath.abs(fb), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100439, _mut100440, _mut100441, _mut100442, _mut100443)))) {
                // Force bisection.
                d = m;
                e = d;
            } else {
                double s = AOR_divide(fb, fa, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100445, _mut100446, _mut100447, _mut100448);
                double p;
                double q;
                // it should NOT be replaced by proximity test.
                if (ROR_equals(a, c, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100449, _mut100450, _mut100451, _mut100452, _mut100453)) {
                    // Linear interpolation.
                    p = AOR_multiply(AOR_multiply(2, m, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100518, _mut100519, _mut100520, _mut100521), s, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100522, _mut100523, _mut100524, _mut100525);
                    q = AOR_minus(1, s, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100526, _mut100527, _mut100528, _mut100529);
                } else {
                    // Inverse quadratic interpolation.
                    q = AOR_divide(fa, fc, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100454, _mut100455, _mut100456, _mut100457);
                    final double r = AOR_divide(fb, fc, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100458, _mut100459, _mut100460, _mut100461);
                    p = AOR_multiply(s, (AOR_minus(AOR_multiply(AOR_multiply(AOR_multiply(2, m, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100462, _mut100463, _mut100464, _mut100465), q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100466, _mut100467, _mut100468, _mut100469), (AOR_minus(q, r, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100470, _mut100471, _mut100472, _mut100473)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100474, _mut100475, _mut100476, _mut100477), AOR_multiply((AOR_minus(b, a, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100478, _mut100479, _mut100480, _mut100481)), (AOR_minus(r, 1, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100482, _mut100483, _mut100484, _mut100485)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100486, _mut100487, _mut100488, _mut100489), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100490, _mut100491, _mut100492, _mut100493)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100494, _mut100495, _mut100496, _mut100497);
                    q = AOR_multiply(AOR_multiply((AOR_minus(q, 1, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100498, _mut100499, _mut100500, _mut100501)), (AOR_minus(r, 1, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100502, _mut100503, _mut100504, _mut100505)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100506, _mut100507, _mut100508, _mut100509), (AOR_minus(s, 1, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100510, _mut100511, _mut100512, _mut100513)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100514, _mut100515, _mut100516, _mut100517);
                }
                if (ROR_greater(p, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100530, _mut100531, _mut100532, _mut100533, _mut100534)) {
                    q = -q;
                } else {
                    p = -p;
                }
                s = e;
                e = d;
                if ((_mut100569 ? (ROR_greater_equals(p, AOR_minus(AOR_multiply(AOR_multiply(1.5, m, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100535, _mut100536, _mut100537, _mut100538), q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100539, _mut100540, _mut100541, _mut100542), FastMath.abs(AOR_multiply(tol, q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100543, _mut100544, _mut100545, _mut100546)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100547, _mut100548, _mut100549, _mut100550), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100551, _mut100552, _mut100553, _mut100554, _mut100555) && ROR_greater_equals(p, FastMath.abs(AOR_multiply(AOR_multiply(0.5, s, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100556, _mut100557, _mut100558, _mut100559), q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100560, _mut100561, _mut100562, _mut100563)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100564, _mut100565, _mut100566, _mut100567, _mut100568)) : (ROR_greater_equals(p, AOR_minus(AOR_multiply(AOR_multiply(1.5, m, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100535, _mut100536, _mut100537, _mut100538), q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100539, _mut100540, _mut100541, _mut100542), FastMath.abs(AOR_multiply(tol, q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100543, _mut100544, _mut100545, _mut100546)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100547, _mut100548, _mut100549, _mut100550), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100551, _mut100552, _mut100553, _mut100554, _mut100555) || ROR_greater_equals(p, FastMath.abs(AOR_multiply(AOR_multiply(0.5, s, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100556, _mut100557, _mut100558, _mut100559), q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100560, _mut100561, _mut100562, _mut100563)), "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100564, _mut100565, _mut100566, _mut100567, _mut100568)))) {
                    // Fall back to bisection.
                    d = m;
                    e = d;
                } else {
                    d = AOR_divide(p, q, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100570, _mut100571, _mut100572, _mut100573);
                }
            }
            a = b;
            fa = fb;
            if (ROR_greater(FastMath.abs(d), tol, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100574, _mut100575, _mut100576, _mut100577, _mut100578)) {
                b += d;
            } else if (ROR_greater(m, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100579, _mut100580, _mut100581, _mut100582, _mut100583)) {
                b += tol;
            } else {
                b -= tol;
            }
            fb = computeObjectiveValue(b);
            if ((_mut100606 ? (((_mut100594 ? (ROR_greater(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100584, _mut100585, _mut100586, _mut100587, _mut100588) || ROR_greater(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100589, _mut100590, _mut100591, _mut100592, _mut100593)) : (ROR_greater(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100584, _mut100585, _mut100586, _mut100587, _mut100588) && ROR_greater(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100589, _mut100590, _mut100591, _mut100592, _mut100593)))) && ((_mut100605 ? (ROR_less_equals(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100595, _mut100596, _mut100597, _mut100598, _mut100599) || ROR_less_equals(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100600, _mut100601, _mut100602, _mut100603, _mut100604)) : (ROR_less_equals(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100595, _mut100596, _mut100597, _mut100598, _mut100599) && ROR_less_equals(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100600, _mut100601, _mut100602, _mut100603, _mut100604))))) : (((_mut100594 ? (ROR_greater(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100584, _mut100585, _mut100586, _mut100587, _mut100588) || ROR_greater(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100589, _mut100590, _mut100591, _mut100592, _mut100593)) : (ROR_greater(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100584, _mut100585, _mut100586, _mut100587, _mut100588) && ROR_greater(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100589, _mut100590, _mut100591, _mut100592, _mut100593)))) || ((_mut100605 ? (ROR_less_equals(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100595, _mut100596, _mut100597, _mut100598, _mut100599) || ROR_less_equals(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100600, _mut100601, _mut100602, _mut100603, _mut100604)) : (ROR_less_equals(fb, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100595, _mut100596, _mut100597, _mut100598, _mut100599) && ROR_less_equals(fc, 0, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100600, _mut100601, _mut100602, _mut100603, _mut100604))))))) {
                c = a;
                fc = fa;
                d = AOR_minus(b, a, "org.apache.commons.math3.analysis.solvers.BrentSolver.brent_151", _mut100607, _mut100608, _mut100609, _mut100610);
                e = d;
            }
        }
    }
}
