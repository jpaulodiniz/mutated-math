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
package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.univariate.BracketFinder;
import org.apache.commons.math3.optimization.univariate.BrentOptimizer;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Powell algorithm.
 * This code is translated and adapted from the Python version of this
 * algorithm (as implemented in module {@code optimize.py} v0.5 of
 * <em>SciPy</em>).
 * <br/>
 * The default stopping criterion is based on the differences of the
 * function value between two successive iterations. It is however possible
 * to define a custom convergence checker that might terminate the algorithm
 * earlier.
 * <br/>
 * The internal line search optimizer is a {@link BrentOptimizer} with a
 * convergence checker set to {@link SimpleUnivariateValueChecker}.
 *
 * @deprecated As of 3.1 (to be removed in 4.0).
 * @since 2.2
 */
@Deprecated
public class PowellOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {

    @Conditional
    public static boolean _mut74691 = false, _mut74692 = false, _mut74693 = false, _mut74694 = false, _mut74695 = false, _mut74696 = false, _mut74697 = false, _mut74698 = false, _mut74699 = false, _mut74700 = false, _mut74701 = false, _mut74702 = false, _mut74703 = false, _mut74704 = false, _mut74705 = false, _mut74706 = false, _mut74707 = false, _mut74708 = false, _mut74709 = false, _mut74710 = false, _mut74711 = false, _mut74712 = false, _mut74713 = false, _mut74714 = false, _mut74715 = false, _mut74716 = false, _mut74717 = false, _mut74718 = false, _mut74719 = false, _mut74720 = false, _mut74721 = false, _mut74722 = false, _mut74723 = false, _mut74724 = false, _mut74725 = false, _mut74726 = false, _mut74727 = false, _mut74728 = false, _mut74729 = false, _mut74730 = false, _mut74731 = false, _mut74732 = false, _mut74733 = false, _mut74734 = false, _mut74735 = false, _mut74736 = false, _mut74737 = false, _mut74738 = false, _mut74739 = false, _mut74740 = false, _mut74741 = false, _mut74742 = false, _mut74743 = false, _mut74744 = false, _mut74745 = false, _mut74746 = false, _mut74747 = false, _mut74748 = false, _mut74749 = false, _mut74750 = false, _mut74751 = false, _mut74752 = false, _mut74753 = false, _mut74754 = false, _mut74755 = false, _mut74756 = false, _mut74757 = false, _mut74758 = false, _mut74759 = false, _mut74760 = false, _mut74761 = false, _mut74762 = false, _mut74763 = false, _mut74764 = false, _mut74765 = false, _mut74766 = false, _mut74767 = false, _mut74768 = false, _mut74769 = false, _mut74770 = false, _mut74771 = false, _mut74772 = false, _mut74773 = false, _mut74774 = false, _mut74775 = false, _mut74776 = false, _mut74777 = false, _mut74778 = false, _mut74779 = false, _mut74780 = false, _mut74781 = false, _mut74782 = false, _mut74783 = false, _mut74784 = false, _mut74785 = false, _mut74786 = false, _mut74787 = false, _mut74788 = false, _mut74789 = false, _mut74790 = false, _mut74791 = false, _mut74792 = false, _mut74793 = false, _mut74794 = false, _mut74795 = false, _mut74796 = false, _mut74797 = false, _mut74798 = false, _mut74799 = false, _mut74800 = false, _mut74801 = false, _mut74802 = false, _mut74803 = false, _mut74804 = false, _mut74805 = false, _mut74806 = false, _mut74807 = false, _mut74808 = false, _mut74809 = false, _mut74810 = false, _mut74811 = false, _mut74812 = false, _mut74813 = false, _mut74814 = false, _mut74815 = false, _mut74816 = false, _mut74817 = false, _mut74818 = false, _mut74819 = false, _mut74820 = false, _mut74821 = false, _mut74822 = false, _mut74823 = false, _mut74824 = false, _mut74825 = false, _mut74826 = false, _mut74827 = false, _mut74828 = false, _mut74829 = false, _mut74830 = false, _mut74831 = false, _mut74832 = false, _mut74833 = false, _mut74834 = false, _mut74835 = false, _mut74836 = false, _mut74837 = false, _mut74838 = false, _mut74839 = false, _mut74840 = false, _mut74841 = false, _mut74842 = false, _mut74843 = false, _mut74844 = false, _mut74845 = false, _mut74846 = false, _mut74847 = false, _mut74848 = false, _mut74849 = false, _mut74850 = false, _mut74851 = false, _mut74852 = false, _mut74853 = false, _mut74854 = false, _mut74855 = false, _mut74856 = false, _mut74857 = false, _mut74858 = false, _mut74859 = false, _mut74860 = false;

    /**
     * Minimum relative tolerance.
     */
    private static final double MIN_RELATIVE_TOLERANCE = AOR_multiply(2, FastMath.ulp(1d), "org.apache.commons.math3.optimization.direct.PowellOptimizer.evaluateNewSimplex_195", _mut74691, _mut74692, _mut74693, _mut74694);

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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.PowellOptimizer_106");
        if (ROR_less(rel, MIN_RELATIVE_TOLERANCE, "org.apache.commons.math3.optimization.direct.PowellOptimizer.PowellOptimizer_106", _mut74695, _mut74696, _mut74697, _mut74698, _mut74699)) {
            throw new NumberIsTooSmallException(rel, MIN_RELATIVE_TOLERANCE, true);
        }
        if (ROR_less_equals(abs, 0, "org.apache.commons.math3.optimization.direct.PowellOptimizer.PowellOptimizer_106", _mut74700, _mut74701, _mut74702, _mut74703, _mut74704)) {
            throw new NotStrictlyPositiveException(abs);
        }
        relativeThreshold = rel;
        absoluteThreshold = abs;
        // Create the line search optimizer.
        line = new LineSearch(lineRel, lineAbs);
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
     * @since 3.1
     */
    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PointValuePair doOptimize() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162");
        final GoalType goal = getGoalType();
        final double[] guess = getStartPoint();
        final int n = guess.length;
        final double[][] direc = new double[n][n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74705, _mut74706, _mut74707, _mut74708, _mut74709); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162");
            direc[i][i] = 1;
        }
        final ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
        double[] x = guess;
        double fVal = computeObjectiveValue(x);
        double[] x1 = x.clone();
        int iter = 0;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162");
            ++iter;
            double fX = fVal;
            double fX2 = 0;
            double delta = 0;
            int bigInd = 0;
            double alphaMin = 0;
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74723, _mut74724, _mut74725, _mut74726, _mut74727); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162");
                final double[] d = MathArrays.copyOf(direc[i]);
                fX2 = fVal;
                final UnivariatePointValuePair optimum = line.search(x, d);
                fVal = optimum.getValue();
                alphaMin = optimum.getPoint();
                final double[][] result = newPointAndDirection(x, d, alphaMin);
                x = result[0];
                if (ROR_greater((AOR_minus(fX2, fVal, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74710, _mut74711, _mut74712, _mut74713)), delta, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74714, _mut74715, _mut74716, _mut74717, _mut74718)) {
                    delta = AOR_minus(fX2, fVal, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74719, _mut74720, _mut74721, _mut74722);
                    bigInd = i;
                }
            }
            // Default convergence check.
            boolean stop = ROR_less_equals(AOR_multiply(2, (AOR_minus(fX, fVal, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74728, _mut74729, _mut74730, _mut74731)), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74732, _mut74733, _mut74734, _mut74735), (AOR_plus(AOR_multiply(relativeThreshold, (AOR_plus(FastMath.abs(fX), FastMath.abs(fVal), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74736, _mut74737, _mut74738, _mut74739)), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74740, _mut74741, _mut74742, _mut74743), absoluteThreshold, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74744, _mut74745, _mut74746, _mut74747)), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74748, _mut74749, _mut74750, _mut74751, _mut74752);
            final PointValuePair previous = new PointValuePair(x1, fX);
            final PointValuePair current = new PointValuePair(x, fVal);
            if ((_mut74753 ? (!stop || checker != null) : (!stop && checker != null))) {
                stop = checker.converged(iter, previous, current);
            }
            if (stop) {
                if (goal == GoalType.MINIMIZE) {
                    return (ROR_less(fVal, fX, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74759, _mut74760, _mut74761, _mut74762, _mut74763)) ? current : previous;
                } else {
                    return (ROR_greater(fVal, fX, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74754, _mut74755, _mut74756, _mut74757, _mut74758)) ? current : previous;
                }
            }
            final double[] d = new double[n];
            final double[] x2 = new double[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74776, _mut74777, _mut74778, _mut74779, _mut74780); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162");
                d[i] = AOR_minus(x[i], x1[i], "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74764, _mut74765, _mut74766, _mut74767);
                x2[i] = AOR_minus(AOR_multiply(2, x[i], "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74768, _mut74769, _mut74770, _mut74771), x1[i], "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74772, _mut74773, _mut74774, _mut74775);
            }
            x1 = x.clone();
            fX2 = computeObjectiveValue(x2);
            if (ROR_greater(fX, fX2, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74781, _mut74782, _mut74783, _mut74784, _mut74785)) {
                double t = AOR_multiply(2, (AOR_minus(AOR_plus(fX, fX2, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74786, _mut74787, _mut74788, _mut74789), AOR_multiply(2, fVal, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74790, _mut74791, _mut74792, _mut74793), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74794, _mut74795, _mut74796, _mut74797)), "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74798, _mut74799, _mut74800, _mut74801);
                double temp = AOR_minus(AOR_minus(fX, fVal, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74802, _mut74803, _mut74804, _mut74805), delta, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74806, _mut74807, _mut74808, _mut74809);
                t *= AOR_multiply(temp, temp, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74810, _mut74811, _mut74812, _mut74813);
                temp = AOR_minus(fX, fX2, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74814, _mut74815, _mut74816, _mut74817);
                t -= AOR_multiply(AOR_multiply(delta, temp, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74818, _mut74819, _mut74820, _mut74821), temp, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74822, _mut74823, _mut74824, _mut74825);
                if (ROR_less(t, 0.0, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74826, _mut74827, _mut74828, _mut74829, _mut74830)) {
                    final UnivariatePointValuePair optimum = line.search(x, d);
                    fVal = optimum.getValue();
                    alphaMin = optimum.getPoint();
                    final double[][] result = newPointAndDirection(x, d, alphaMin);
                    x = result[0];
                    final int lastInd = AOR_minus(n, 1, "org.apache.commons.math3.optimization.direct.PowellOptimizer.doOptimize_162", _mut74831, _mut74832, _mut74833, _mut74834);
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
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.newPointAndDirection_266");
        final int n = p.length;
        final double[] nP = new double[n];
        final double[] nD = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.PowellOptimizer.newPointAndDirection_266", _mut74843, _mut74844, _mut74845, _mut74846, _mut74847); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.newPointAndDirection_266");
            nD[i] = AOR_multiply(d[i], optimum, "org.apache.commons.math3.optimization.direct.PowellOptimizer.newPointAndDirection_266", _mut74835, _mut74836, _mut74837, _mut74838);
            nP[i] = AOR_plus(p[i], nD[i], "org.apache.commons.math3.optimization.direct.PowellOptimizer.newPointAndDirection_266", _mut74839, _mut74840, _mut74841, _mut74842);
        }
        final double[][] result = new double[2][];
        result[0] = nP;
        result[1] = nD;
        return result;
    }

    /**
     * Class for finding the minimum of the objective function along a given
     * direction.
     */
    private class LineSearch extends BrentOptimizer {

        /**
         * Value that will pass the precondition check for {@link BrentOptimizer}
         * but will not pass the convergence check, so that the custom checker
         * will always decide when to stop the line search.
         */
        private static final double REL_TOL_UNUSED = 1e-15;

        /**
         * Value that will pass the precondition check for {@link BrentOptimizer}
         * but will not pass the convergence check, so that the custom checker
         * will always decide when to stop the line search.
         */
        private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;

        /**
         * Automatic bracketing.
         */
        private final BracketFinder bracket = new BracketFinder();

        /**
         * The "BrentOptimizer" default stopping criterion uses the tolerances
         * to check the domain (point) values, not the function values.
         * We thus create a custom checker to use function values.
         *
         * @param rel Relative threshold.
         * @param abs Absolute threshold.
         */
        LineSearch(double rel, double abs) {
            super(REL_TOL_UNUSED, ABS_TOL_UNUSED, new SimpleUnivariateValueChecker(rel, abs));
        }

        /**
         * Find the minimum of the function {@code f(p + alpha * d)}.
         *
         * @param p Starting point.
         * @param d Search direction.
         * @return the optimum.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the number of evaluations is exceeded.
         */
        public UnivariatePointValuePair search(final double[] p, final double[] d) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334");
            final int n = p.length;
            final UnivariateFunction f = new UnivariateFunction() {

                /**
                 * {@inheritDoc}
                 */
                public double value(double alpha) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334");
                    final double[] x = new double[n];
                    for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334", _mut74856, _mut74857, _mut74858, _mut74859, _mut74860); i++) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334");
                        x[i] = AOR_plus(p[i], AOR_multiply(alpha, d[i], "org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334", _mut74848, _mut74849, _mut74850, _mut74851), "org.apache.commons.math3.optimization.direct.PowellOptimizer.value_334", _mut74852, _mut74853, _mut74854, _mut74855);
                    }
                    final double obj = PowellOptimizer.this.computeObjectiveValue(x);
                    return obj;
                }
            };
            final GoalType goal = PowellOptimizer.this.getGoalType();
            bracket.search(f, goal, 0, 1);
            // generate the exception).
            return optimize(Integer.MAX_VALUE, f, goal, bracket.getLo(), bracket.getHi(), bracket.getMid());
        }
    }
}
