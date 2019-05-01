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
package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optimization.GoalType;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Provide an interval that brackets a local optimum of a function.
 * This code is based on a Python implementation (from <em>SciPy</em>,
 * module {@code optimize.py} v0.5).
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.2
 */
@Deprecated
public class BracketFinder {

    @Conditional
    public static boolean _mut70431 = false, _mut70432 = false, _mut70433 = false, _mut70434 = false, _mut70435 = false, _mut70436 = false, _mut70437 = false, _mut70438 = false, _mut70439 = false, _mut70440 = false, _mut70441 = false, _mut70442 = false, _mut70443 = false, _mut70444 = false, _mut70445 = false, _mut70446 = false, _mut70447 = false, _mut70448 = false, _mut70449 = false, _mut70450 = false, _mut70451 = false, _mut70452 = false, _mut70453 = false, _mut70454 = false, _mut70455 = false, _mut70456 = false, _mut70457 = false, _mut70458 = false, _mut70459 = false, _mut70460 = false, _mut70461 = false, _mut70462 = false, _mut70463 = false, _mut70464 = false, _mut70465 = false, _mut70466 = false, _mut70467 = false, _mut70468 = false, _mut70469 = false, _mut70470 = false, _mut70471 = false, _mut70472 = false, _mut70473 = false, _mut70474 = false, _mut70475 = false, _mut70476 = false, _mut70477 = false, _mut70478 = false, _mut70479 = false, _mut70480 = false, _mut70481 = false, _mut70482 = false, _mut70483 = false, _mut70484 = false, _mut70485 = false, _mut70486 = false, _mut70487 = false, _mut70488 = false, _mut70489 = false, _mut70490 = false, _mut70491 = false, _mut70492 = false, _mut70493 = false, _mut70494 = false, _mut70495 = false, _mut70496 = false, _mut70497 = false, _mut70498 = false, _mut70499 = false, _mut70500 = false, _mut70501 = false, _mut70502 = false, _mut70503 = false, _mut70504 = false, _mut70505 = false, _mut70506 = false, _mut70507 = false, _mut70508 = false, _mut70509 = false, _mut70510 = false, _mut70511 = false, _mut70512 = false, _mut70513 = false, _mut70514 = false, _mut70515 = false, _mut70516 = false, _mut70517 = false, _mut70518 = false, _mut70519 = false, _mut70520 = false, _mut70521 = false, _mut70522 = false, _mut70523 = false, _mut70524 = false, _mut70525 = false, _mut70526 = false, _mut70527 = false, _mut70528 = false, _mut70529 = false, _mut70530 = false, _mut70531 = false, _mut70532 = false, _mut70533 = false, _mut70534 = false, _mut70535 = false, _mut70536 = false, _mut70537 = false, _mut70538 = false, _mut70539 = false, _mut70540 = false, _mut70541 = false, _mut70542 = false, _mut70543 = false, _mut70544 = false, _mut70545 = false, _mut70546 = false, _mut70547 = false, _mut70548 = false, _mut70549 = false, _mut70550 = false, _mut70551 = false, _mut70552 = false, _mut70553 = false, _mut70554 = false, _mut70555 = false, _mut70556 = false, _mut70557 = false, _mut70558 = false, _mut70559 = false, _mut70560 = false, _mut70561 = false, _mut70562 = false, _mut70563 = false, _mut70564 = false, _mut70565 = false, _mut70566 = false, _mut70567 = false, _mut70568 = false, _mut70569 = false, _mut70570 = false, _mut70571 = false, _mut70572 = false, _mut70573 = false, _mut70574 = false, _mut70575 = false, _mut70576 = false, _mut70577 = false, _mut70578 = false, _mut70579 = false, _mut70580 = false, _mut70581 = false, _mut70582 = false, _mut70583 = false, _mut70584 = false, _mut70585 = false, _mut70586 = false, _mut70587 = false, _mut70588 = false, _mut70589 = false, _mut70590 = false, _mut70591 = false, _mut70592 = false, _mut70593 = false, _mut70594 = false, _mut70595 = false, _mut70596 = false, _mut70597 = false, _mut70598 = false, _mut70599 = false, _mut70600 = false, _mut70601 = false, _mut70602 = false, _mut70603 = false, _mut70604 = false, _mut70605 = false, _mut70606 = false, _mut70607 = false, _mut70608 = false, _mut70609 = false, _mut70610 = false, _mut70611 = false, _mut70612 = false, _mut70613 = false, _mut70614 = false, _mut70615 = false, _mut70616 = false, _mut70617 = false, _mut70618 = false, _mut70619 = false, _mut70620 = false, _mut70621 = false, _mut70622 = false, _mut70623 = false, _mut70624 = false, _mut70625 = false, _mut70626 = false, _mut70627 = false, _mut70628 = false, _mut70629 = false, _mut70630 = false, _mut70631 = false, _mut70632 = false, _mut70633 = false, _mut70634 = false, _mut70635 = false, _mut70636 = false, _mut70637 = false, _mut70638 = false, _mut70639 = false, _mut70640 = false, _mut70641 = false, _mut70642 = false, _mut70643 = false, _mut70644 = false, _mut70645 = false, _mut70646 = false, _mut70647 = false, _mut70648 = false, _mut70649 = false, _mut70650 = false, _mut70651 = false, _mut70652 = false, _mut70653 = false, _mut70654 = false, _mut70655 = false, _mut70656 = false, _mut70657 = false, _mut70658 = false, _mut70659 = false, _mut70660 = false, _mut70661 = false, _mut70662 = false, _mut70663 = false, _mut70664 = false, _mut70665 = false, _mut70666 = false, _mut70667 = false, _mut70668 = false, _mut70669 = false, _mut70670 = false, _mut70671 = false, _mut70672 = false, _mut70673 = false, _mut70674 = false, _mut70675 = false;

    /**
     * Tolerance to avoid division by zero.
     */
    private static final double EPS_MIN = 1e-21;

    /**
     * Golden section.
     */
    private static final double GOLD = 1.618034;

    /**
     * Factor for expanding the interval.
     */
    private final double growLimit;

    /**
     * Counter for function evaluations.
     */
    private final Incrementor evaluations = new Incrementor();

    /**
     * Lower bound of the bracket.
     */
    private double lo;

    /**
     * Higher bound of the bracket.
     */
    private double hi;

    /**
     * Point inside the bracket.
     */
    private double mid;

    /**
     * Function value at {@link #lo}.
     */
    private double fLo;

    /**
     * Function value at {@link #hi}.
     */
    private double fHi;

    /**
     * Function value at {@link #mid}.
     */
    private double fMid;

    /**
     * Constructor with default values {@code 100, 50} (see the
     * {@link #BracketFinder(double,int) other constructor}).
     */
    public BracketFinder() {
        this(100, 50);
    }

    /**
     * Create a bracketing interval finder.
     *
     * @param growLimit Expanding factor.
     * @param maxEvaluations Maximum number of evaluations allowed for finding
     * a bracketing interval.
     */
    public BracketFinder(double growLimit, int maxEvaluations) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BracketFinder.BracketFinder_91");
        if (ROR_less_equals(growLimit, 0, "org.apache.commons.math3.optimization.univariate.BracketFinder.BracketFinder_91", _mut70431, _mut70432, _mut70433, _mut70434, _mut70435)) {
            throw new NotStrictlyPositiveException(growLimit);
        }
        if (ROR_less_equals(maxEvaluations, 0, "org.apache.commons.math3.optimization.univariate.BracketFinder.BracketFinder_91", _mut70436, _mut70437, _mut70438, _mut70439, _mut70440)) {
            throw new NotStrictlyPositiveException(maxEvaluations);
        }
        this.growLimit = growLimit;
        evaluations.setMaximalCount(maxEvaluations);
    }

    /**
     * Search new points that bracket a local optimum of the function.
     *
     * @param func Function whose optimum should be bracketed.
     * @param goal {@link GoalType Goal type}.
     * @param xA Initial point.
     * @param xB Initial point.
     * @throws TooManyEvaluationsException if the maximum number of evaluations
     * is exceeded.
     */
    public void search(UnivariateFunction func, GoalType goal, double xA, double xB) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BracketFinder.search_114");
        evaluations.resetCount();
        final boolean isMinim = goal == GoalType.MINIMIZE;
        double fA = eval(func, xA);
        double fB = eval(func, xB);
        if (isMinim ? ROR_less(fA, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70446, _mut70447, _mut70448, _mut70449, _mut70450) : ROR_greater(fA, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70441, _mut70442, _mut70443, _mut70444, _mut70445)) {
            double tmp = xA;
            xA = xB;
            xB = tmp;
            tmp = fA;
            fA = fB;
            fB = tmp;
        }
        double xC = AOR_plus(xB, AOR_multiply(GOLD, (AOR_minus(xB, xA, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70451, _mut70452, _mut70453, _mut70454)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70455, _mut70456, _mut70457, _mut70458), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70459, _mut70460, _mut70461, _mut70462);
        double fC = eval(func, xC);
        while (isMinim ? ROR_less(fC, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70666, _mut70667, _mut70668, _mut70669, _mut70670) : ROR_greater(fC, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70661, _mut70662, _mut70663, _mut70664, _mut70665)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.univariate.BracketFinder.search_114");
            double tmp1 = AOR_multiply((AOR_minus(xB, xA, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70463, _mut70464, _mut70465, _mut70466)), (AOR_minus(fB, fC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70467, _mut70468, _mut70469, _mut70470)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70471, _mut70472, _mut70473, _mut70474);
            double tmp2 = AOR_multiply((AOR_minus(xB, xC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70475, _mut70476, _mut70477, _mut70478)), (AOR_minus(fB, fA, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70479, _mut70480, _mut70481, _mut70482)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70483, _mut70484, _mut70485, _mut70486);
            double val = AOR_minus(tmp2, tmp1, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70487, _mut70488, _mut70489, _mut70490);
            double denom = ROR_less(FastMath.abs(val), EPS_MIN, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70491, _mut70492, _mut70493, _mut70494, _mut70495) ? AOR_multiply(2, EPS_MIN, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70500, _mut70501, _mut70502, _mut70503) : AOR_multiply(2, val, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70496, _mut70497, _mut70498, _mut70499);
            double w = AOR_minus(xB, AOR_divide((AOR_minus(AOR_multiply((AOR_minus(xB, xC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70504, _mut70505, _mut70506, _mut70507)), tmp2, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70508, _mut70509, _mut70510, _mut70511), AOR_multiply((AOR_minus(xB, xA, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70512, _mut70513, _mut70514, _mut70515)), tmp1, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70516, _mut70517, _mut70518, _mut70519), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70520, _mut70521, _mut70522, _mut70523)), denom, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70524, _mut70525, _mut70526, _mut70527), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70528, _mut70529, _mut70530, _mut70531);
            double wLim = AOR_plus(xB, AOR_multiply(growLimit, (AOR_minus(xC, xB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70532, _mut70533, _mut70534, _mut70535)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70536, _mut70537, _mut70538, _mut70539), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70540, _mut70541, _mut70542, _mut70543);
            double fW;
            if (ROR_greater(AOR_multiply((AOR_minus(w, xC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70544, _mut70545, _mut70546, _mut70547)), (AOR_minus(xB, w, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70548, _mut70549, _mut70550, _mut70551)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70552, _mut70553, _mut70554, _mut70555), 0, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70556, _mut70557, _mut70558, _mut70559, _mut70560)) {
                fW = eval(func, w);
                if (isMinim ? ROR_less(fW, fC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70634, _mut70635, _mut70636, _mut70637, _mut70638) : ROR_greater(fW, fC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70629, _mut70630, _mut70631, _mut70632, _mut70633)) {
                    xA = xB;
                    xB = w;
                    fA = fB;
                    fB = fW;
                    break;
                } else if (isMinim ? ROR_greater(fW, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70644, _mut70645, _mut70646, _mut70647, _mut70648) : ROR_less(fW, fB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70639, _mut70640, _mut70641, _mut70642, _mut70643)) {
                    xC = w;
                    fC = fW;
                    break;
                }
                w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70649, _mut70650, _mut70651, _mut70652)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70653, _mut70654, _mut70655, _mut70656), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70657, _mut70658, _mut70659, _mut70660);
                fW = eval(func, w);
            } else if (ROR_greater_equals(AOR_multiply((AOR_minus(w, wLim, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70561, _mut70562, _mut70563, _mut70564)), (AOR_minus(wLim, xC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70565, _mut70566, _mut70567, _mut70568)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70569, _mut70570, _mut70571, _mut70572), 0, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70573, _mut70574, _mut70575, _mut70576, _mut70577)) {
                w = wLim;
                fW = eval(func, w);
            } else if (ROR_greater(AOR_multiply((AOR_minus(w, wLim, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70578, _mut70579, _mut70580, _mut70581)), (AOR_minus(xC, w, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70582, _mut70583, _mut70584, _mut70585)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70586, _mut70587, _mut70588, _mut70589), 0, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70590, _mut70591, _mut70592, _mut70593, _mut70594)) {
                fW = eval(func, w);
                if (isMinim ? ROR_less(fW, fC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70612, _mut70613, _mut70614, _mut70615, _mut70616) : ROR_greater(fW, fC, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70607, _mut70608, _mut70609, _mut70610, _mut70611)) {
                    xB = xC;
                    xC = w;
                    w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70617, _mut70618, _mut70619, _mut70620)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70621, _mut70622, _mut70623, _mut70624), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70625, _mut70626, _mut70627, _mut70628);
                    fB = fC;
                    fC = fW;
                    fW = eval(func, w);
                }
            } else {
                w = AOR_plus(xC, AOR_multiply(GOLD, (AOR_minus(xC, xB, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70595, _mut70596, _mut70597, _mut70598)), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70599, _mut70600, _mut70601, _mut70602), "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70603, _mut70604, _mut70605, _mut70606);
                fW = eval(func, w);
            }
            xA = xB;
            fA = fB;
            xB = xC;
            fB = fC;
            xC = w;
            fC = fW;
        }
        lo = xA;
        fLo = fA;
        mid = xB;
        fMid = fB;
        hi = xC;
        fHi = fC;
        if (ROR_greater(lo, hi, "org.apache.commons.math3.optimization.univariate.BracketFinder.search_114", _mut70671, _mut70672, _mut70673, _mut70674, _mut70675)) {
            double tmp = lo;
            lo = hi;
            hi = tmp;
            tmp = fLo;
            fLo = fHi;
            fHi = tmp;
        }
    }

    /**
     * @return the number of evalutations.
     */
    public int getMaxEvaluations() {
        return evaluations.getMaximalCount();
    }

    /**
     * @return the number of evalutations.
     */
    public int getEvaluations() {
        return evaluations.getCount();
    }

    /**
     * @return the lower bound of the bracket.
     * @see #getFLo()
     */
    public double getLo() {
        return lo;
    }

    /**
     * Get function value at {@link #getLo()}.
     * @return function value at {@link #getLo()}
     */
    public double getFLo() {
        return fLo;
    }

    /**
     * @return the higher bound of the bracket.
     * @see #getFHi()
     */
    public double getHi() {
        return hi;
    }

    /**
     * Get function value at {@link #getHi()}.
     * @return function value at {@link #getHi()}
     */
    public double getFHi() {
        return fHi;
    }

    /**
     * @return a point in the middle of the bracket.
     * @see #getFMid()
     */
    public double getMid() {
        return mid;
    }

    /**
     * Get function value at {@link #getMid()}.
     * @return function value at {@link #getMid()}
     */
    public double getFMid() {
        return fMid;
    }

    /**
     * @param f Function.
     * @param x Argument.
     * @return {@code f(x)}
     * @throws TooManyEvaluationsException if the maximal number of evaluations is
     * exceeded.
     */
    private double eval(UnivariateFunction f, double x) {
        try {
            evaluations.incrementCount();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
        return f.value(x);
    }
}
