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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements a modification of the <a
 * href="http://mathworld.wolfram.com/BrentsMethod.html"> Brent algorithm</a>.
 * <p>
 * The changes with respect to the original Brent algorithm are:
 * <ul>
 *   <li>the returned value is chosen in the current interval according
 *   to user specified {@link AllowedSolution},</li>
 *   <li>the maximal order for the invert polynomial root search is
 *   user-specified instead of being invert quadratic only</li>
 * </ul><p>
 * The given interval must bracket the root.</p>
 */
public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {

    @Conditional
    public static boolean _mut99367 = false, _mut99368 = false, _mut99369 = false, _mut99370 = false, _mut99371 = false, _mut99372 = false, _mut99373 = false, _mut99374 = false, _mut99375 = false, _mut99376 = false, _mut99377 = false, _mut99378 = false, _mut99379 = false, _mut99380 = false, _mut99381 = false, _mut99382 = false, _mut99383 = false, _mut99384 = false, _mut99385 = false, _mut99386 = false, _mut99387 = false, _mut99388 = false, _mut99389 = false, _mut99390 = false, _mut99391 = false, _mut99392 = false, _mut99393 = false, _mut99394 = false, _mut99395 = false, _mut99396 = false, _mut99397 = false, _mut99398 = false, _mut99399 = false, _mut99400 = false, _mut99401 = false, _mut99402 = false, _mut99403 = false, _mut99404 = false, _mut99405 = false, _mut99406 = false, _mut99407 = false, _mut99408 = false, _mut99409 = false, _mut99410 = false, _mut99411 = false, _mut99412 = false, _mut99413 = false, _mut99414 = false, _mut99415 = false, _mut99416 = false, _mut99417 = false, _mut99418 = false, _mut99419 = false, _mut99420 = false, _mut99421 = false, _mut99422 = false, _mut99423 = false, _mut99424 = false, _mut99425 = false, _mut99426 = false, _mut99427 = false, _mut99428 = false, _mut99429 = false, _mut99430 = false, _mut99431 = false, _mut99432 = false, _mut99433 = false, _mut99434 = false, _mut99435 = false, _mut99436 = false, _mut99437 = false, _mut99438 = false, _mut99439 = false, _mut99440 = false, _mut99441 = false, _mut99442 = false, _mut99443 = false, _mut99444 = false, _mut99445 = false, _mut99446 = false, _mut99447 = false, _mut99448 = false, _mut99449 = false, _mut99450 = false, _mut99451 = false, _mut99452 = false, _mut99453 = false, _mut99454 = false, _mut99455 = false, _mut99456 = false, _mut99457 = false, _mut99458 = false, _mut99459 = false, _mut99460 = false, _mut99461 = false, _mut99462 = false, _mut99463 = false, _mut99464 = false, _mut99465 = false, _mut99466 = false, _mut99467 = false, _mut99468 = false, _mut99469 = false, _mut99470 = false, _mut99471 = false, _mut99472 = false, _mut99473 = false, _mut99474 = false, _mut99475 = false, _mut99476 = false, _mut99477 = false, _mut99478 = false, _mut99479 = false, _mut99480 = false, _mut99481 = false, _mut99482 = false, _mut99483 = false, _mut99484 = false, _mut99485 = false, _mut99486 = false, _mut99487 = false, _mut99488 = false, _mut99489 = false, _mut99490 = false, _mut99491 = false, _mut99492 = false, _mut99493 = false, _mut99494 = false, _mut99495 = false, _mut99496 = false, _mut99497 = false, _mut99498 = false, _mut99499 = false, _mut99500 = false, _mut99501 = false, _mut99502 = false, _mut99503 = false, _mut99504 = false, _mut99505 = false, _mut99506 = false, _mut99507 = false, _mut99508 = false, _mut99509 = false, _mut99510 = false, _mut99511 = false, _mut99512 = false, _mut99513 = false, _mut99514 = false, _mut99515 = false, _mut99516 = false, _mut99517 = false, _mut99518 = false, _mut99519 = false, _mut99520 = false, _mut99521 = false, _mut99522 = false, _mut99523 = false, _mut99524 = false, _mut99525 = false, _mut99526 = false, _mut99527 = false, _mut99528 = false, _mut99529 = false, _mut99530 = false, _mut99531 = false, _mut99532 = false, _mut99533 = false, _mut99534 = false, _mut99535 = false, _mut99536 = false, _mut99537 = false, _mut99538 = false, _mut99539 = false, _mut99540 = false, _mut99541 = false, _mut99542 = false, _mut99543 = false, _mut99544 = false, _mut99545 = false, _mut99546 = false, _mut99547 = false, _mut99548 = false, _mut99549 = false, _mut99550 = false, _mut99551 = false, _mut99552 = false, _mut99553 = false, _mut99554 = false, _mut99555 = false, _mut99556 = false, _mut99557 = false, _mut99558 = false, _mut99559 = false, _mut99560 = false, _mut99561 = false, _mut99562 = false, _mut99563 = false, _mut99564 = false, _mut99565 = false, _mut99566 = false, _mut99567 = false, _mut99568 = false, _mut99569 = false, _mut99570 = false, _mut99571 = false, _mut99572 = false, _mut99573 = false, _mut99574 = false, _mut99575 = false, _mut99576 = false, _mut99577 = false, _mut99578 = false, _mut99579 = false, _mut99580 = false, _mut99581 = false, _mut99582 = false, _mut99583 = false, _mut99584 = false, _mut99585 = false, _mut99586 = false, _mut99587 = false, _mut99588 = false, _mut99589 = false, _mut99590 = false, _mut99591 = false, _mut99592 = false, _mut99593 = false, _mut99594 = false, _mut99595 = false, _mut99596 = false, _mut99597 = false, _mut99598 = false, _mut99599 = false, _mut99600 = false, _mut99601 = false, _mut99602 = false, _mut99603 = false, _mut99604 = false, _mut99605 = false, _mut99606 = false, _mut99607 = false, _mut99608 = false, _mut99609 = false, _mut99610 = false, _mut99611 = false, _mut99612 = false, _mut99613 = false, _mut99614 = false, _mut99615 = false, _mut99616 = false, _mut99617 = false, _mut99618 = false, _mut99619 = false, _mut99620 = false, _mut99621 = false, _mut99622 = false, _mut99623 = false, _mut99624 = false, _mut99625 = false, _mut99626 = false, _mut99627 = false, _mut99628 = false, _mut99629 = false, _mut99630 = false, _mut99631 = false, _mut99632 = false, _mut99633 = false, _mut99634 = false, _mut99635 = false, _mut99636 = false, _mut99637 = false, _mut99638 = false, _mut99639 = false, _mut99640 = false, _mut99641 = false, _mut99642 = false, _mut99643 = false, _mut99644 = false, _mut99645 = false, _mut99646 = false, _mut99647 = false, _mut99648 = false, _mut99649 = false, _mut99650 = false, _mut99651 = false, _mut99652 = false, _mut99653 = false, _mut99654 = false, _mut99655 = false, _mut99656 = false, _mut99657 = false, _mut99658 = false, _mut99659 = false, _mut99660 = false, _mut99661 = false, _mut99662 = false, _mut99663 = false, _mut99664 = false, _mut99665 = false, _mut99666 = false, _mut99667 = false, _mut99668 = false, _mut99669 = false, _mut99670 = false, _mut99671 = false, _mut99672 = false, _mut99673 = false, _mut99674 = false, _mut99675 = false, _mut99676 = false, _mut99677 = false, _mut99678 = false, _mut99679 = false, _mut99680 = false, _mut99681 = false, _mut99682 = false, _mut99683 = false, _mut99684 = false, _mut99685 = false, _mut99686 = false, _mut99687 = false, _mut99688 = false, _mut99689 = false, _mut99690 = false, _mut99691 = false, _mut99692 = false, _mut99693 = false, _mut99694 = false, _mut99695 = false, _mut99696 = false, _mut99697 = false, _mut99698 = false, _mut99699 = false, _mut99700 = false, _mut99701 = false, _mut99702 = false, _mut99703 = false, _mut99704 = false, _mut99705 = false, _mut99706 = false, _mut99707 = false, _mut99708 = false, _mut99709 = false, _mut99710 = false, _mut99711 = false, _mut99712 = false, _mut99713 = false, _mut99714 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Default maximal order.
     */
    private static final int DEFAULT_MAXIMAL_ORDER = 5;

    /**
     * Maximal aging triggering an attempt to balance the bracketing interval.
     */
    private static final int MAXIMAL_AGING = 2;

    /**
     * Reduction factor for attempts to balance the bracketing interval.
     */
    private static final double REDUCTION_FACTOR = AOR_divide(1.0, 16.0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.getCoefficients_77", _mut99367, _mut99368, _mut99369, _mut99370);

    /**
     * Maximal order.
     */
    private final int maximalOrder;

    /**
     * The kinds of solutions that the algorithm may accept.
     */
    private AllowedSolution allowed;

    /**
     * Construct a solver with default accuracy and maximal order (1e-6 and 5 respectively)
     */
    public BracketingNthOrderBrentSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY, DEFAULT_MAXIMAL_ORDER);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     * @param maximalOrder maximal order.
     * @exception NumberIsTooSmallException if maximal order is lower than 2
     */
    public BracketingNthOrderBrentSolver(final double absoluteAccuracy, final int maximalOrder) throws NumberIsTooSmallException {
        super(absoluteAccuracy);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_79");
        if (ROR_less(maximalOrder, 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_79", _mut99371, _mut99372, _mut99373, _mut99374, _mut99375)) {
            throw new NumberIsTooSmallException(maximalOrder, 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param maximalOrder maximal order.
     * @exception NumberIsTooSmallException if maximal order is lower than 2
     */
    public BracketingNthOrderBrentSolver(final double relativeAccuracy, final double absoluteAccuracy, final int maximalOrder) throws NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_98");
        if (ROR_less(maximalOrder, 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_98", _mut99376, _mut99377, _mut99378, _mut99379, _mut99380)) {
            throw new NumberIsTooSmallException(maximalOrder, 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     * @param maximalOrder maximal order.
     * @exception NumberIsTooSmallException if maximal order is lower than 2
     */
    public BracketingNthOrderBrentSolver(final double relativeAccuracy, final double absoluteAccuracy, final double functionValueAccuracy, final int maximalOrder) throws NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_119");
        if (ROR_less(maximalOrder, 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.BracketingNthOrderBrentSolver_119", _mut99381, _mut99382, _mut99383, _mut99384, _mut99385)) {
            throw new NumberIsTooSmallException(maximalOrder, 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    /**
     * Get the maximal order.
     * @return maximal order
     */
    public int getMaximalOrder() {
        return maximalOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142");
        // prepare arrays with the first points
        final double[] x = new double[AOR_plus(maximalOrder, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99386, _mut99387, _mut99388, _mut99389)];
        final double[] y = new double[AOR_plus(maximalOrder, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99390, _mut99391, _mut99392, _mut99393)];
        x[0] = getMin();
        x[1] = getStartValue();
        x[2] = getMax();
        verifySequence(x[0], x[1], x[2]);
        // evaluate initial guess
        y[1] = computeObjectiveValue(x[1]);
        if (Precision.equals(y[1], 0.0, 1)) {
            // return the initial guess if it is a perfect root.
            return x[1];
        }
        // evaluate first  endpoint
        y[0] = computeObjectiveValue(x[0]);
        if (Precision.equals(y[0], 0.0, 1)) {
            // return the first endpoint if it is a perfect root.
            return x[0];
        }
        int nbPoints;
        int signChangeIndex;
        if (ROR_less(AOR_multiply(y[0], y[1], "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99394, _mut99395, _mut99396, _mut99397), 0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99398, _mut99399, _mut99400, _mut99401, _mut99402)) {
            // reduce interval if it brackets the root
            nbPoints = 2;
            signChangeIndex = 1;
        } else {
            // evaluate second endpoint
            y[2] = computeObjectiveValue(x[2]);
            if (Precision.equals(y[2], 0.0, 1)) {
                // return the second endpoint if it is a perfect root.
                return x[2];
            }
            if (ROR_less(AOR_multiply(y[1], y[2], "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99403, _mut99404, _mut99405, _mut99406), 0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99407, _mut99408, _mut99409, _mut99410, _mut99411)) {
                // use all computed point as a start sampling array for solving
                nbPoints = 3;
                signChangeIndex = 2;
            } else {
                throw new NoBracketingException(x[0], x[2], y[0], y[2]);
            }
        }
        // prepare a work array for inverse polynomial interpolation
        final double[] tmpX = new double[x.length];
        // current tightest bracketing of the root
        double xA = x[AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99412, _mut99413, _mut99414, _mut99415)];
        double yA = y[AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99416, _mut99417, _mut99418, _mut99419)];
        double absYA = FastMath.abs(yA);
        int agingA = 0;
        double xB = x[signChangeIndex];
        double yB = y[signChangeIndex];
        double absYB = FastMath.abs(yB);
        int agingB = 0;
        // search loop
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142");
            // check convergence of bracketing interval
            final double xTol = AOR_plus(getAbsoluteAccuracy(), AOR_multiply(getRelativeAccuracy(), FastMath.max(FastMath.abs(xA), FastMath.abs(xB)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99420, _mut99421, _mut99422, _mut99423), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99424, _mut99425, _mut99426, _mut99427);
            if ((_mut99442 ? ((ROR_less_equals((AOR_minus(xB, xA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99428, _mut99429, _mut99430, _mut99431)), xTol, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99432, _mut99433, _mut99434, _mut99435, _mut99436)) && (ROR_less(FastMath.max(absYA, absYB), getFunctionValueAccuracy(), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99437, _mut99438, _mut99439, _mut99440, _mut99441))) : ((ROR_less_equals((AOR_minus(xB, xA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99428, _mut99429, _mut99430, _mut99431)), xTol, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99432, _mut99433, _mut99434, _mut99435, _mut99436)) || (ROR_less(FastMath.max(absYA, absYB), getFunctionValueAccuracy(), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99437, _mut99438, _mut99439, _mut99440, _mut99441))))) {
                switch(allowed) {
                    case ANY_SIDE:
                        return ROR_less(absYA, absYB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99443, _mut99444, _mut99445, _mut99446, _mut99447) ? xA : xB;
                    case LEFT_SIDE:
                        return xA;
                    case RIGHT_SIDE:
                        return xB;
                    case BELOW_SIDE:
                        return (ROR_less_equals(yA, 0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99448, _mut99449, _mut99450, _mut99451, _mut99452)) ? xA : xB;
                    case ABOVE_SIDE:
                        return (ROR_less(yA, 0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99453, _mut99454, _mut99455, _mut99456, _mut99457)) ? xB : xA;
                    default:
                        // this should never happen
                        throw new MathInternalError();
                }
            }
            // target for the next evaluation point
            double targetY;
            if (ROR_greater_equals(agingA, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99458, _mut99459, _mut99460, _mut99461, _mut99462)) {
                // we keep updating the high bracket, try to compensate this
                final int p = AOR_minus(agingA, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99504, _mut99505, _mut99506, _mut99507);
                final double weightA = AOR_minus((1 << p), 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99508, _mut99509, _mut99510, _mut99511);
                final double weightB = AOR_plus(p, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99512, _mut99513, _mut99514, _mut99515);
                targetY = AOR_divide((AOR_minus(AOR_multiply(weightA, yA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99516, _mut99517, _mut99518, _mut99519), AOR_multiply(AOR_multiply(weightB, REDUCTION_FACTOR, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99520, _mut99521, _mut99522, _mut99523), yB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99524, _mut99525, _mut99526, _mut99527), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99528, _mut99529, _mut99530, _mut99531)), (AOR_plus(weightA, weightB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99532, _mut99533, _mut99534, _mut99535)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99536, _mut99537, _mut99538, _mut99539);
            } else if (ROR_greater_equals(agingB, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99463, _mut99464, _mut99465, _mut99466, _mut99467)) {
                // we keep updating the low bracket, try to compensate this
                final int p = AOR_minus(agingB, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99468, _mut99469, _mut99470, _mut99471);
                final double weightA = AOR_plus(p, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99472, _mut99473, _mut99474, _mut99475);
                final double weightB = AOR_minus((1 << p), 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99476, _mut99477, _mut99478, _mut99479);
                targetY = AOR_divide((AOR_minus(AOR_multiply(weightB, yB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99480, _mut99481, _mut99482, _mut99483), AOR_multiply(AOR_multiply(weightA, REDUCTION_FACTOR, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99484, _mut99485, _mut99486, _mut99487), yA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99488, _mut99489, _mut99490, _mut99491), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99492, _mut99493, _mut99494, _mut99495)), (AOR_plus(weightA, weightB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99496, _mut99497, _mut99498, _mut99499)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99500, _mut99501, _mut99502, _mut99503);
            } else {
                // bracketing is balanced, try to find the root itself
                targetY = 0;
            }
            // make a few attempts to guess a root,
            double nextX;
            int start = 0;
            int end = nbPoints;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142");
                // guess a value for current target, using inverse polynomial interpolation
                System.arraycopy(x, start, tmpX, start, AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99540, _mut99541, _mut99542, _mut99543));
                nextX = guessX(targetY, tmpX, y, start, end);
                if (!((_mut99554 ? ((ROR_greater(nextX, xA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99544, _mut99545, _mut99546, _mut99547, _mut99548)) || (ROR_less(nextX, xB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99549, _mut99550, _mut99551, _mut99552, _mut99553))) : ((ROR_greater(nextX, xA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99544, _mut99545, _mut99546, _mut99547, _mut99548)) && (ROR_less(nextX, xB, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99549, _mut99550, _mut99551, _mut99552, _mut99553)))))) {
                    // we try again with a lower interpolation order
                    if (ROR_greater_equals(AOR_minus(signChangeIndex, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99555, _mut99556, _mut99557, _mut99558), AOR_minus(end, signChangeIndex, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99559, _mut99560, _mut99561, _mut99562), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99563, _mut99564, _mut99565, _mut99566, _mut99567)) {
                        // we have more points before the sign change, drop the lowest point
                        ++start;
                    } else {
                        // we have more points after sign change, drop the highest point
                        --end;
                    }
                    // we need to do one more attempt
                    nextX = Double.NaN;
                }
            } while ((_mut99577 ? (Double.isNaN(nextX) || (ROR_greater(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99568, _mut99569, _mut99570, _mut99571), 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99572, _mut99573, _mut99574, _mut99575, _mut99576))) : (Double.isNaN(nextX) && (ROR_greater(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99568, _mut99569, _mut99570, _mut99571), 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99572, _mut99573, _mut99574, _mut99575, _mut99576)))));
            if (Double.isNaN(nextX)) {
                // fall back to bisection
                nextX = AOR_plus(xA, AOR_multiply(0.5, (AOR_minus(xB, xA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99578, _mut99579, _mut99580, _mut99581)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99582, _mut99583, _mut99584, _mut99585), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99586, _mut99587, _mut99588, _mut99589);
                start = AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99590, _mut99591, _mut99592, _mut99593);
                end = signChangeIndex;
            }
            // evaluate the function at the guessed root
            final double nextY = computeObjectiveValue(nextX);
            if (Precision.equals(nextY, 0.0, 1)) {
                // we don't need to bother about the allowed solutions setting
                return nextX;
            }
            if ((_mut99608 ? ((ROR_greater(nbPoints, 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99594, _mut99595, _mut99596, _mut99597, _mut99598)) || (ROR_not_equals(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99599, _mut99600, _mut99601, _mut99602), nbPoints, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99603, _mut99604, _mut99605, _mut99606, _mut99607))) : ((ROR_greater(nbPoints, 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99594, _mut99595, _mut99596, _mut99597, _mut99598)) && (ROR_not_equals(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99599, _mut99600, _mut99601, _mut99602), nbPoints, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99603, _mut99604, _mut99605, _mut99606, _mut99607))))) {
                // they are probably too far from the root, drop them from now on
                nbPoints = AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99627, _mut99628, _mut99629, _mut99630);
                System.arraycopy(x, start, x, 0, nbPoints);
                System.arraycopy(y, start, y, 0, nbPoints);
                signChangeIndex -= start;
            } else if (ROR_equals(nbPoints, x.length, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99609, _mut99610, _mut99611, _mut99612, _mut99613)) {
                // we have to drop one point in order to insert the new one
                nbPoints--;
                // keep the tightest bracketing interval as centered as possible
                if (ROR_greater_equals(signChangeIndex, AOR_divide((AOR_plus(x.length, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99614, _mut99615, _mut99616, _mut99617)), 2, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99618, _mut99619, _mut99620, _mut99621), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99622, _mut99623, _mut99624, _mut99625, _mut99626)) {
                    // we drop the lowest point, we have to shift the arrays and the index
                    System.arraycopy(x, 1, x, 0, nbPoints);
                    System.arraycopy(y, 1, y, 0, nbPoints);
                    --signChangeIndex;
                }
            }
            // (by construction, we know it lies inside the tightest bracketing interval)
            System.arraycopy(x, signChangeIndex, x, AOR_plus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99631, _mut99632, _mut99633, _mut99634), AOR_minus(nbPoints, signChangeIndex, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99635, _mut99636, _mut99637, _mut99638));
            x[signChangeIndex] = nextX;
            System.arraycopy(y, signChangeIndex, y, AOR_plus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99639, _mut99640, _mut99641, _mut99642), AOR_minus(nbPoints, signChangeIndex, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99643, _mut99644, _mut99645, _mut99646));
            y[signChangeIndex] = nextY;
            ++nbPoints;
            // update the bracketing interval
            if (ROR_less_equals(AOR_multiply(nextY, yA, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99647, _mut99648, _mut99649, _mut99650), 0, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve_142", _mut99651, _mut99652, _mut99653, _mut99654, _mut99655)) {
                // the sign change occurs before the inserted point
                xB = nextX;
                yB = nextY;
                absYB = FastMath.abs(yB);
                ++agingA;
                agingB = 0;
            } else {
                // the sign change occurs after the inserted point
                xA = nextX;
                yA = nextY;
                absYA = FastMath.abs(yA);
                agingA = 0;
                ++agingB;
                // update the sign change index
                signChangeIndex++;
            }
        }
    }

    /**
     * Guess an x value by n<sup>th</sup> order inverse polynomial interpolation.
     * <p>
     * The x value is guessed by evaluating polynomial Q(y) at y = targetY, where Q
     * is built such that for all considered points (x<sub>i</sub>, y<sub>i</sub>),
     * Q(y<sub>i</sub>) = x<sub>i</sub>.
     * </p>
     * @param targetY target value for y
     * @param x reference points abscissas for interpolation,
     * note that this array <em>is</em> modified during computation
     * @param y reference points ordinates for interpolation
     * @param start start index of the points to consider (inclusive)
     * @param end end index of the points to consider (exclusive)
     * @return guessed root (will be a NaN if two points share the same y)
     */
    private double guessX(final double targetY, final double[] x, final double[] y, final int start, final int end) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369");
        // compute Q Newton coefficients by divided differences
        for (int i = start; ROR_less(i, AOR_minus(end, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99689, _mut99690, _mut99691, _mut99692), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99693, _mut99694, _mut99695, _mut99696, _mut99697); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369");
            final int delta = AOR_minus(AOR_plus(i, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99656, _mut99657, _mut99658, _mut99659), start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99660, _mut99661, _mut99662, _mut99663);
            for (int j = end - 1; ROR_greater(j, i, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99684, _mut99685, _mut99686, _mut99687, _mut99688); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369");
                x[j] = AOR_divide((AOR_minus(x[j], x[AOR_minus(j, 1, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99664, _mut99665, _mut99666, _mut99667)], "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99668, _mut99669, _mut99670, _mut99671)), (AOR_minus(y[j], y[AOR_minus(j, delta, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99672, _mut99673, _mut99674, _mut99675)], "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99676, _mut99677, _mut99678, _mut99679)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99680, _mut99681, _mut99682, _mut99683);
            }
        }
        // evaluate Q(targetY)
        double x0 = 0;
        for (int j = end - 1; ROR_greater_equals(j, start, "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99710, _mut99711, _mut99712, _mut99713, _mut99714); --j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369");
            x0 = AOR_plus(x[j], AOR_multiply(x0, (AOR_minus(targetY, y[j], "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99698, _mut99699, _mut99700, _mut99701)), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99702, _mut99703, _mut99704, _mut99705), "org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.guessX_369", _mut99706, _mut99707, _mut99708, _mut99709);
        }
        return x0;
    }

    /**
     * {@inheritDoc}
     */
    public double solve(int maxEval, UnivariateFunction f, double min, double max, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(maxEval, f, min, max);
    }

    /**
     * {@inheritDoc}
     */
    public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(maxEval, f, min, max, startValue);
    }
}
