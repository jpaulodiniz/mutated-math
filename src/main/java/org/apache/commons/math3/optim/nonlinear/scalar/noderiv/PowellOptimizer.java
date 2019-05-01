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
package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Powell's algorithm.
 * This code is translated and adapted from the Python version of this
 * algorithm (as implemented in module {@code optimize.py} v0.5 of
 * <em>SciPy</em>).
 * <br/>
 * The default stopping criterion is based on the differences of the
 * function value between two successive iterations. It is however possible
 * to define a custom convergence checker that might terminate the algorithm
 * earlier.
 * <br/>
 * Line search is performed by the {@link LineSearch} class.
 * <br/>
 * Constraints are not supported: the call to
 * {@link #optimize(OptimizationData[]) optimize} will throw
 * {@link MathUnsupportedOperationException} if bounds are passed to it.
 * In order to impose simple constraints, the objective function must be
 * wrapped in an adapter like
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionMappingAdapter
 * MultivariateFunctionMappingAdapter} or
 * {@link org.apache.commons.math3.optim.nonlinear.scalar.MultivariateFunctionPenaltyAdapter
 * MultivariateFunctionPenaltyAdapter}.
 *
 * @since 2.2
 */
public class PowellOptimizer extends MultivariateOptimizer {

    @Conditional
    public static boolean _mut61582 = false, _mut61583 = false, _mut61584 = false, _mut61585 = false, _mut61586 = false, _mut61587 = false, _mut61588 = false, _mut61589 = false, _mut61590 = false, _mut61591 = false, _mut61592 = false, _mut61593 = false, _mut61594 = false, _mut61595 = false, _mut61596 = false, _mut61597 = false, _mut61598 = false, _mut61599 = false, _mut61600 = false, _mut61601 = false, _mut61602 = false, _mut61603 = false, _mut61604 = false, _mut61605 = false, _mut61606 = false, _mut61607 = false, _mut61608 = false, _mut61609 = false, _mut61610 = false, _mut61611 = false, _mut61612 = false, _mut61613 = false, _mut61614 = false, _mut61615 = false, _mut61616 = false, _mut61617 = false, _mut61618 = false, _mut61619 = false, _mut61620 = false, _mut61621 = false, _mut61622 = false, _mut61623 = false, _mut61624 = false, _mut61625 = false, _mut61626 = false, _mut61627 = false, _mut61628 = false, _mut61629 = false, _mut61630 = false, _mut61631 = false, _mut61632 = false, _mut61633 = false, _mut61634 = false, _mut61635 = false, _mut61636 = false, _mut61637 = false, _mut61638 = false, _mut61639 = false, _mut61640 = false, _mut61641 = false, _mut61642 = false, _mut61643 = false, _mut61644 = false, _mut61645 = false, _mut61646 = false, _mut61647 = false, _mut61648 = false, _mut61649 = false, _mut61650 = false, _mut61651 = false, _mut61652 = false, _mut61653 = false, _mut61654 = false, _mut61655 = false, _mut61656 = false, _mut61657 = false, _mut61658 = false, _mut61659 = false, _mut61660 = false, _mut61661 = false, _mut61662 = false, _mut61663 = false, _mut61664 = false, _mut61665 = false, _mut61666 = false, _mut61667 = false, _mut61668 = false, _mut61669 = false, _mut61670 = false, _mut61671 = false, _mut61672 = false, _mut61673 = false, _mut61674 = false, _mut61675 = false, _mut61676 = false, _mut61677 = false, _mut61678 = false, _mut61679 = false, _mut61680 = false, _mut61681 = false, _mut61682 = false, _mut61683 = false, _mut61684 = false, _mut61685 = false, _mut61686 = false, _mut61687 = false, _mut61688 = false, _mut61689 = false, _mut61690 = false, _mut61691 = false, _mut61692 = false, _mut61693 = false, _mut61694 = false, _mut61695 = false, _mut61696 = false, _mut61697 = false, _mut61698 = false, _mut61699 = false, _mut61700 = false, _mut61701 = false, _mut61702 = false, _mut61703 = false, _mut61704 = false, _mut61705 = false, _mut61706 = false, _mut61707 = false, _mut61708 = false, _mut61709 = false, _mut61710 = false, _mut61711 = false, _mut61712 = false, _mut61713 = false, _mut61714 = false, _mut61715 = false, _mut61716 = false, _mut61717 = false, _mut61718 = false, _mut61719 = false, _mut61720 = false, _mut61721 = false, _mut61722 = false, _mut61723 = false, _mut61724 = false, _mut61725 = false, _mut61726 = false, _mut61727 = false, _mut61728 = false, _mut61729 = false, _mut61730 = false, _mut61731 = false, _mut61732 = false, _mut61733 = false, _mut61734 = false, _mut61735 = false, _mut61736 = false, _mut61737 = false, _mut61738 = false, _mut61739 = false;

    /**
     * Minimum relative tolerance.
     */
    private static final double MIN_RELATIVE_TOLERANCE = AOR_multiply(2, FastMath.ulp(1d), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.evaluateNewSimplex_192", _mut61582, _mut61583, _mut61584, _mut61585);

    /**
     * Relative threshold.
     */
    private final double relativeThreshold;

    /**
     * Absolute threshold.
     */
    private final double absoluteThreshold;

    /**
     * Line search.
     */
    private final LineSearch line;

    /**
     * This constructor allows to specify a user-defined convergence checker,
     * in addition to the parameters that control the default convergence
     * checking procedure.
     * <br/>
     * The internal line search tolerances are set to the square-root of their
     * corresponding value in the multivariate optimizer.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param checker Convergence checker.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs, ConvergenceChecker<PointValuePair> checker) {
        this(rel, abs, FastMath.sqrt(rel), FastMath.sqrt(abs), checker);
    }

    /**
     * This constructor allows to specify a user-defined convergence checker,
     * in addition to the parameters that control the default convergence
     * checking procedure and the line search tolerances.
     *
     * @param rel Relative threshold for this optimizer.
     * @param abs Absolute threshold for this optimizer.
     * @param lineRel Relative threshold for the internal line search optimizer.
     * @param lineAbs Absolute threshold for the internal line search optimizer.
     * @param checker Convergence checker.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.PowellOptimizer_109");
        if (ROR_less(rel, MIN_RELATIVE_TOLERANCE, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.PowellOptimizer_109", _mut61586, _mut61587, _mut61588, _mut61589, _mut61590)) {
            throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
        }
        if (ROR_less_equals(abs, 0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.PowellOptimizer_109", _mut61591, _mut61592, _mut61593, _mut61594, _mut61595)) {
            throw new NotStrictlyPositiveException(abs);
        }
        relativeThreshold = rel;
        absoluteThreshold = abs;
        // Create the line search optimizer.
        line = new LineSearch(this, lineRel, lineAbs, 1d);
    }

    /**
     * The parameters control the default convergence checking procedure.
     * <br/>
     * The internal line search tolerances are set to the square-root of their
     * corresponding value in the multivariate optimizer.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    /**
     * Builds an instance with the default convergence checking procedure.
     *
     * @param rel Relative threshold.
     * @param abs Absolute threshold.
     * @param lineRel Relative threshold for the internal line search optimizer.
     * @param lineAbs Absolute threshold for the internal line search optimizer.
     * @throws NotStrictlyPositiveException if {@code abs <= 0}.
     * @throws NumberIsTooSmallException if {@code rel < 2 * Math.ulp(1d)}.
     */
    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166");
        checkParameters();
        final GoalType goal = getGoalType();
        final double[] guess = getStartPoint();
        final int n = guess.length;
        final double[][] direc = new double[n][n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61596, _mut61597, _mut61598, _mut61599, _mut61600); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166");
            direc[i][i] = 1;
        }
        final ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
        double[] x = guess;
        double fVal = computeObjectiveValue(x);
        double[] x1 = x.clone();
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166");
            incrementIterationCount();
            double fX = fVal;
            double fX2 = 0;
            double delta = 0;
            int bigInd = 0;
            double alphaMin = 0;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61614, _mut61615, _mut61616, _mut61617, _mut61618); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166");
                final double[] d = MathArrays.copyOf(direc[i]);
                fX2 = fVal;
                final UnivariatePointValuePair optimum = line.search(x, d);
                fVal = optimum.getValue();
                alphaMin = optimum.getPoint();
                final double[][] result = newPointAndDirection(x, d, alphaMin);
                x = result[0];
                if (ROR_greater((AOR_minus(fX2, fVal, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61601, _mut61602, _mut61603, _mut61604)), delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61605, _mut61606, _mut61607, _mut61608, _mut61609)) {
                    delta = AOR_minus(fX2, fVal, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61610, _mut61611, _mut61612, _mut61613);
                    bigInd = i;
                }
            }
            // Default convergence check.
            boolean stop = ROR_less_equals(AOR_multiply(2, (AOR_minus(fX, fVal, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61619, _mut61620, _mut61621, _mut61622)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61623, _mut61624, _mut61625, _mut61626), (AOR_plus(AOR_multiply(relativeThreshold, (AOR_plus(FastMath.abs(fX), FastMath.abs(fVal), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61627, _mut61628, _mut61629, _mut61630)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61631, _mut61632, _mut61633, _mut61634), absoluteThreshold, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61635, _mut61636, _mut61637, _mut61638)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61639, _mut61640, _mut61641, _mut61642, _mut61643);
            final PointValuePair previous = new PointValuePair(x1, fX);
            final PointValuePair current = new PointValuePair(x, fVal);
            if ((_mut61644 ? (!stop || checker != null) : (!stop && checker != null))) {
                // User-defined stopping criteria.
                stop = checker.converged(getIterations(), previous, current);
            }
            if (stop) {
                if (goal == GoalType.MINIMIZE) {
                    return (ROR_less(fVal, fX, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61650, _mut61651, _mut61652, _mut61653, _mut61654)) ? current : previous;
                } else {
                    return (ROR_greater(fVal, fX, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61645, _mut61646, _mut61647, _mut61648, _mut61649)) ? current : previous;
                }
            }
            final double[] d = new double[n];
            final double[] x2 = new double[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61667, _mut61668, _mut61669, _mut61670, _mut61671); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166");
                d[i] = AOR_minus(x[i], x1[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61655, _mut61656, _mut61657, _mut61658);
                x2[i] = AOR_minus(AOR_multiply(2, x[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61659, _mut61660, _mut61661, _mut61662), x1[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61663, _mut61664, _mut61665, _mut61666);
            }
            x1 = x.clone();
            fX2 = computeObjectiveValue(x2);
            if (ROR_greater(fX, fX2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61672, _mut61673, _mut61674, _mut61675, _mut61676)) {
                double t = AOR_multiply(2, (AOR_minus(AOR_plus(fX, fX2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61677, _mut61678, _mut61679, _mut61680), AOR_multiply(2, fVal, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61681, _mut61682, _mut61683, _mut61684), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61685, _mut61686, _mut61687, _mut61688)), "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61689, _mut61690, _mut61691, _mut61692);
                double temp = AOR_minus(AOR_minus(fX, fVal, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61693, _mut61694, _mut61695, _mut61696), delta, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61697, _mut61698, _mut61699, _mut61700);
                t *= AOR_multiply(temp, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61701, _mut61702, _mut61703, _mut61704);
                temp = AOR_minus(fX, fX2, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61705, _mut61706, _mut61707, _mut61708);
                t -= AOR_multiply(AOR_multiply(delta, temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61709, _mut61710, _mut61711, _mut61712), temp, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61713, _mut61714, _mut61715, _mut61716);
                if (ROR_less(t, 0.0, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61717, _mut61718, _mut61719, _mut61720, _mut61721)) {
                    final UnivariatePointValuePair optimum = line.search(x, d);
                    fVal = optimum.getValue();
                    alphaMin = optimum.getPoint();
                    final double[][] result = newPointAndDirection(x, d, alphaMin);
                    x = result[0];
                    final int lastInd = AOR_minus(n, 1, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize_166", _mut61722, _mut61723, _mut61724, _mut61725);
                    direc[bigInd] = direc[lastInd];
                    direc[lastInd] = result[1];
                }
            }
        }
    }

    /**
     * Compute a new point (in the original space) and a new direction
     * vector, resulting from the line search.
     *
     * @param p Point used in the line search.
     * @param d Direction used in the line search.
     * @param optimum Optimum found by the line search.
     * @return a 2-element array containing the new point (at index 0) and
     * the new direction (at index 1).
     */
    private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.newPointAndDirection_271");
        final int n = p.length;
        final double[] nP = new double[n];
        final double[] nD = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.newPointAndDirection_271", _mut61734, _mut61735, _mut61736, _mut61737, _mut61738); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.newPointAndDirection_271");
            nD[i] = AOR_multiply(d[i], optimum, "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.newPointAndDirection_271", _mut61726, _mut61727, _mut61728, _mut61729);
            nP[i] = AOR_plus(p[i], nD[i], "org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.newPointAndDirection_271", _mut61730, _mut61731, _mut61732, _mut61733);
        }
        final double[][] result = new double[2][];
        result[0] = nP;
        result[1] = nD;
        return result;
    }

    /**
     * @throws MathUnsupportedOperationException if bounds were passed to the
     * {@link #optimize(OptimizationData[]) optimize} method.
     */
    private void checkParameters() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.checkParameters_293");
        if ((_mut61739 ? (getLowerBound() != null && getUpperBound() != null) : (getLowerBound() != null || getUpperBound() != null))) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT);
        }
    }
}
