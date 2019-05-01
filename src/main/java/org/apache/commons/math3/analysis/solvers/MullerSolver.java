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
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the <a href="http://mathworld.wolfram.com/MullersMethod.html">
 * Muller's Method</a> for root finding of real univariate functions. For
 * reference, see <b>Elementary Numerical Analysis</b>, ISBN 0070124477,
 * chapter 3.
 * <p>
 * Muller's method applies to both real and complex functions, but here we
 * restrict ourselves to real functions.
 * This class differs from {@link MullerSolver} in the way it avoids complex
 * operations.</p><p>
 * Muller's original method would have function evaluation at complex point.
 * Since our f(x) is real, we have to find ways to avoid that. Bracketing
 * condition is one way to go: by requiring bracketing in every iteration,
 * the newly computed approximation is guaranteed to be real.</p>
 * <p>
 * Normally Muller's method converges quadratically in the vicinity of a
 * zero, however it may be very slow in regions far away from zeros. For
 * example, f(x) = exp(x) - 1, min = -50, max = 100. In such case we use
 * bisection as a safety backup if it performs very poorly.</p>
 * <p>
 * The formulas here use divided differences directly.</p>
 *
 * @since 1.2
 * @see MullerSolver2
 */
public class MullerSolver extends AbstractUnivariateSolver {

    @Conditional
    public static boolean _mut100640 = false, _mut100641 = false, _mut100642 = false, _mut100643 = false, _mut100644 = false, _mut100645 = false, _mut100646 = false, _mut100647 = false, _mut100648 = false, _mut100649 = false, _mut100650 = false, _mut100651 = false, _mut100652 = false, _mut100653 = false, _mut100654 = false, _mut100655 = false, _mut100656 = false, _mut100657 = false, _mut100658 = false, _mut100659 = false, _mut100660 = false, _mut100661 = false, _mut100662 = false, _mut100663 = false, _mut100664 = false, _mut100665 = false, _mut100666 = false, _mut100667 = false, _mut100668 = false, _mut100669 = false, _mut100670 = false, _mut100671 = false, _mut100672 = false, _mut100673 = false, _mut100674 = false, _mut100675 = false, _mut100676 = false, _mut100677 = false, _mut100678 = false, _mut100679 = false, _mut100680 = false, _mut100681 = false, _mut100682 = false, _mut100683 = false, _mut100684 = false, _mut100685 = false, _mut100686 = false, _mut100687 = false, _mut100688 = false, _mut100689 = false, _mut100690 = false, _mut100691 = false, _mut100692 = false, _mut100693 = false, _mut100694 = false, _mut100695 = false, _mut100696 = false, _mut100697 = false, _mut100698 = false, _mut100699 = false, _mut100700 = false, _mut100701 = false, _mut100702 = false, _mut100703 = false, _mut100704 = false, _mut100705 = false, _mut100706 = false, _mut100707 = false, _mut100708 = false, _mut100709 = false, _mut100710 = false, _mut100711 = false, _mut100712 = false, _mut100713 = false, _mut100714 = false, _mut100715 = false, _mut100716 = false, _mut100717 = false, _mut100718 = false, _mut100719 = false, _mut100720 = false, _mut100721 = false, _mut100722 = false, _mut100723 = false, _mut100724 = false, _mut100725 = false, _mut100726 = false, _mut100727 = false, _mut100728 = false, _mut100729 = false, _mut100730 = false, _mut100731 = false, _mut100732 = false, _mut100733 = false, _mut100734 = false, _mut100735 = false, _mut100736 = false, _mut100737 = false, _mut100738 = false, _mut100739 = false, _mut100740 = false, _mut100741 = false, _mut100742 = false, _mut100743 = false, _mut100744 = false, _mut100745 = false, _mut100746 = false, _mut100747 = false, _mut100748 = false, _mut100749 = false, _mut100750 = false, _mut100751 = false, _mut100752 = false, _mut100753 = false, _mut100754 = false, _mut100755 = false, _mut100756 = false, _mut100757 = false, _mut100758 = false, _mut100759 = false, _mut100760 = false, _mut100761 = false, _mut100762 = false, _mut100763 = false, _mut100764 = false, _mut100765 = false, _mut100766 = false, _mut100767 = false, _mut100768 = false, _mut100769 = false, _mut100770 = false, _mut100771 = false, _mut100772 = false, _mut100773 = false, _mut100774 = false, _mut100775 = false, _mut100776 = false, _mut100777 = false, _mut100778 = false, _mut100779 = false, _mut100780 = false, _mut100781 = false, _mut100782 = false, _mut100783 = false, _mut100784 = false, _mut100785 = false, _mut100786 = false, _mut100787 = false, _mut100788 = false, _mut100789 = false, _mut100790 = false, _mut100791 = false, _mut100792 = false, _mut100793 = false, _mut100794 = false, _mut100795 = false, _mut100796 = false, _mut100797 = false, _mut100798 = false, _mut100799 = false, _mut100800 = false, _mut100801 = false, _mut100802 = false, _mut100803 = false, _mut100804 = false, _mut100805 = false, _mut100806 = false, _mut100807 = false, _mut100808 = false, _mut100809 = false, _mut100810 = false, _mut100811 = false, _mut100812 = false, _mut100813 = false, _mut100814 = false, _mut100815 = false, _mut100816 = false, _mut100817 = false, _mut100818 = false, _mut100819 = false, _mut100820 = false, _mut100821 = false, _mut100822 = false, _mut100823 = false, _mut100824 = false, _mut100825 = false, _mut100826 = false, _mut100827 = false, _mut100828 = false, _mut100829 = false, _mut100830 = false, _mut100831 = false, _mut100832 = false, _mut100833 = false, _mut100834 = false, _mut100835 = false, _mut100836 = false, _mut100837 = false, _mut100838 = false, _mut100839 = false, _mut100840 = false, _mut100841 = false, _mut100842 = false, _mut100843 = false, _mut100844 = false, _mut100845 = false, _mut100846 = false, _mut100847 = false, _mut100848 = false, _mut100849 = false, _mut100850 = false, _mut100851 = false, _mut100852 = false, _mut100853 = false, _mut100854 = false, _mut100855 = false, _mut100856 = false, _mut100857 = false, _mut100858 = false, _mut100859 = false, _mut100860 = false, _mut100861 = false, _mut100862 = false, _mut100863 = false, _mut100864 = false, _mut100865 = false, _mut100866 = false, _mut100867 = false, _mut100868 = false, _mut100869 = false, _mut100870 = false, _mut100871 = false, _mut100872 = false, _mut100873 = false, _mut100874 = false, _mut100875 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public MullerSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public MullerSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public MullerSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver.doSolve_82");
        final double min = getMin();
        final double max = getMax();
        final double initial = getStartValue();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, initial, max);
        // check for zeros before verifying bracketing
        final double fMin = computeObjectiveValue(min);
        if (ROR_less(FastMath.abs(fMin), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver.doSolve_82", _mut100640, _mut100641, _mut100642, _mut100643, _mut100644)) {
            return min;
        }
        final double fMax = computeObjectiveValue(max);
        if (ROR_less(FastMath.abs(fMax), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver.doSolve_82", _mut100645, _mut100646, _mut100647, _mut100648, _mut100649)) {
            return max;
        }
        final double fInitial = computeObjectiveValue(initial);
        if (ROR_less(FastMath.abs(fInitial), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver.doSolve_82", _mut100650, _mut100651, _mut100652, _mut100653, _mut100654)) {
            return initial;
        }
        verifyBracketing(min, max);
        if (isBracketing(min, initial)) {
            return solve(min, initial, fMin, fInitial);
        } else {
            return solve(initial, max, fInitial, fMax);
        }
    }

    /**
     * Find a real root in the given interval.
     *
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param fMin function value at the lower bound.
     * @param fMax function value at the upper bound.
     * @return the point at which the function value is zero.
     * @throws TooManyEvaluationsException if the allowed number of calls to
     * the function to be solved has been exhausted.
     */
    private double solve(double min, double max, double fMin, double fMax) throws TooManyEvaluationsException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129");
        final double relativeAccuracy = getRelativeAccuracy();
        final double absoluteAccuracy = getAbsoluteAccuracy();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        double x0 = min;
        double y0 = fMin;
        double x2 = max;
        double y2 = fMax;
        double x1 = AOR_multiply(0.5, (AOR_plus(x0, x2, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100655, _mut100656, _mut100657, _mut100658)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100659, _mut100660, _mut100661, _mut100662);
        double y1 = computeObjectiveValue(x1);
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129");
            // real roots and we choose one in [x0, x2] to be x.
            final double d01 = AOR_divide((AOR_minus(y1, y0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100663, _mut100664, _mut100665, _mut100666)), (AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100667, _mut100668, _mut100669, _mut100670)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100671, _mut100672, _mut100673, _mut100674);
            final double d12 = AOR_divide((AOR_minus(y2, y1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100675, _mut100676, _mut100677, _mut100678)), (AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100679, _mut100680, _mut100681, _mut100682)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100683, _mut100684, _mut100685, _mut100686);
            final double d012 = AOR_divide((AOR_minus(d12, d01, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100687, _mut100688, _mut100689, _mut100690)), (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100691, _mut100692, _mut100693, _mut100694)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100695, _mut100696, _mut100697, _mut100698);
            final double c1 = AOR_plus(d01, AOR_multiply((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100699, _mut100700, _mut100701, _mut100702)), d012, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100703, _mut100704, _mut100705, _mut100706), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100707, _mut100708, _mut100709, _mut100710);
            final double delta = AOR_minus(AOR_multiply(c1, c1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100711, _mut100712, _mut100713, _mut100714), AOR_multiply(AOR_multiply(4, y1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100715, _mut100716, _mut100717, _mut100718), d012, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100719, _mut100720, _mut100721, _mut100722), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100723, _mut100724, _mut100725, _mut100726);
            final double xplus = AOR_plus(x1, AOR_divide((AOR_multiply(-2.0, y1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100727, _mut100728, _mut100729, _mut100730)), (AOR_plus(c1, FastMath.sqrt(delta), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100731, _mut100732, _mut100733, _mut100734)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100735, _mut100736, _mut100737, _mut100738), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100739, _mut100740, _mut100741, _mut100742);
            final double xminus = AOR_plus(x1, AOR_divide((AOR_multiply(-2.0, y1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100743, _mut100744, _mut100745, _mut100746)), (AOR_minus(c1, FastMath.sqrt(delta), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100747, _mut100748, _mut100749, _mut100750)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100751, _mut100752, _mut100753, _mut100754), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100755, _mut100756, _mut100757, _mut100758);
            // one of them should lie in (x0, x2)
            final double x = isSequence(x0, xplus, x2) ? xplus : xminus;
            final double y = computeObjectiveValue(x);
            // check for convergence
            final double tolerance = FastMath.max(AOR_multiply(relativeAccuracy, FastMath.abs(x), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100759, _mut100760, _mut100761, _mut100762), absoluteAccuracy);
            if ((_mut100777 ? (ROR_less_equals(FastMath.abs(AOR_minus(x, oldx, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100763, _mut100764, _mut100765, _mut100766)), tolerance, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100767, _mut100768, _mut100769, _mut100770, _mut100771) && ROR_less_equals(FastMath.abs(y), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100772, _mut100773, _mut100774, _mut100775, _mut100776)) : (ROR_less_equals(FastMath.abs(AOR_minus(x, oldx, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100763, _mut100764, _mut100765, _mut100766)), tolerance, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100767, _mut100768, _mut100769, _mut100770, _mut100771) || ROR_less_equals(FastMath.abs(y), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100772, _mut100773, _mut100774, _mut100775, _mut100776)))) {
                return x;
            }
            // completes the proximity tests above it
            boolean bisect = (_mut100830 ? ((_mut100824 ? (((_mut100800 ? (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) || ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)) : (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) && ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)))) && ((_mut100823 ? (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) || ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)) : (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) && ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822))))) : (((_mut100800 ? (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) || ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)) : (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) && ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)))) || ((_mut100823 ? (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) || ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)) : (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) && ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)))))) && (ROR_equals(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100825, _mut100826, _mut100827, _mut100828, _mut100829))) : ((_mut100824 ? (((_mut100800 ? (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) || ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)) : (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) && ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)))) && ((_mut100823 ? (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) || ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)) : (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) && ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822))))) : (((_mut100800 ? (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) || ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)) : (ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100778, _mut100779, _mut100780, _mut100781, _mut100782) && ROR_greater((AOR_minus(x1, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100783, _mut100784, _mut100785, _mut100786)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100787, _mut100788, _mut100789, _mut100790)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100791, _mut100792, _mut100793, _mut100794), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100795, _mut100796, _mut100797, _mut100798, _mut100799)))) || ((_mut100823 ? (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) || ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)) : (ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100801, _mut100802, _mut100803, _mut100804, _mut100805) && ROR_greater((AOR_minus(x2, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100806, _mut100807, _mut100808, _mut100809)), AOR_multiply(0.95, (AOR_minus(x2, x0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100810, _mut100811, _mut100812, _mut100813)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100814, _mut100815, _mut100816, _mut100817), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100818, _mut100819, _mut100820, _mut100821, _mut100822)))))) || (ROR_equals(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100825, _mut100826, _mut100827, _mut100828, _mut100829))));
            // prepare the new bracketing interval for next iteration
            if (!bisect) {
                x0 = ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100856, _mut100857, _mut100858, _mut100859, _mut100860) ? x0 : x1;
                y0 = ROR_less(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100861, _mut100862, _mut100863, _mut100864, _mut100865) ? y0 : y1;
                x2 = ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100866, _mut100867, _mut100868, _mut100869, _mut100870) ? x2 : x1;
                y2 = ROR_greater(x, x1, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100871, _mut100872, _mut100873, _mut100874, _mut100875) ? y2 : y1;
                x1 = x;
                y1 = y;
                oldx = x;
            } else {
                double xm = AOR_multiply(0.5, (AOR_plus(x0, x2, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100831, _mut100832, _mut100833, _mut100834)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100835, _mut100836, _mut100837, _mut100838);
                double ym = computeObjectiveValue(xm);
                if (ROR_equals(AOR_plus(FastMath.signum(y0), FastMath.signum(ym), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100839, _mut100840, _mut100841, _mut100842), 0.0, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100843, _mut100844, _mut100845, _mut100846, _mut100847)) {
                    x2 = xm;
                    y2 = ym;
                } else {
                    x0 = xm;
                    y0 = ym;
                }
                x1 = AOR_multiply(0.5, (AOR_plus(x0, x2, "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100848, _mut100849, _mut100850, _mut100851)), "org.apache.commons.math3.analysis.solvers.MullerSolver.solve_129", _mut100852, _mut100853, _mut100854, _mut100855);
                y1 = computeObjectiveValue(x1);
                oldx = Double.POSITIVE_INFINITY;
            }
        }
    }
}
