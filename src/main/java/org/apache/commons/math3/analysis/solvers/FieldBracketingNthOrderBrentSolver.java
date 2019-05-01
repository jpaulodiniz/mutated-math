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

import org.apache.commons.math3.Field;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.analysis.RealFieldUnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
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
 *   to user specified {@link AllowedSolution}</li>
 *   <li>the maximal order for the invert polynomial root search is
 *   user-specified instead of being invert quadratic only</li>
 * </ul><p>
 * The given interval must bracket the root.</p>
 *
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldBracketingNthOrderBrentSolver<T extends RealFieldElement<T>> implements BracketedRealFieldUnivariateSolver<T> {

    @Conditional
    public static boolean _mut99732 = false, _mut99733 = false, _mut99734 = false, _mut99735 = false, _mut99736 = false, _mut99737 = false, _mut99738 = false, _mut99739 = false, _mut99740 = false, _mut99741 = false, _mut99742 = false, _mut99743 = false, _mut99744 = false, _mut99745 = false, _mut99746 = false, _mut99747 = false, _mut99748 = false, _mut99749 = false, _mut99750 = false, _mut99751 = false, _mut99752 = false, _mut99753 = false, _mut99754 = false, _mut99755 = false, _mut99756 = false, _mut99757 = false, _mut99758 = false, _mut99759 = false, _mut99760 = false, _mut99761 = false, _mut99762 = false, _mut99763 = false, _mut99764 = false, _mut99765 = false, _mut99766 = false, _mut99767 = false, _mut99768 = false, _mut99769 = false, _mut99770 = false, _mut99771 = false, _mut99772 = false, _mut99773 = false, _mut99774 = false, _mut99775 = false, _mut99776 = false, _mut99777 = false, _mut99778 = false, _mut99779 = false, _mut99780 = false, _mut99781 = false, _mut99782 = false, _mut99783 = false, _mut99784 = false, _mut99785 = false, _mut99786 = false, _mut99787 = false, _mut99788 = false, _mut99789 = false, _mut99790 = false, _mut99791 = false, _mut99792 = false, _mut99793 = false, _mut99794 = false, _mut99795 = false, _mut99796 = false, _mut99797 = false, _mut99798 = false, _mut99799 = false, _mut99800 = false, _mut99801 = false, _mut99802 = false, _mut99803 = false, _mut99804 = false, _mut99805 = false, _mut99806 = false, _mut99807 = false, _mut99808 = false, _mut99809 = false, _mut99810 = false, _mut99811 = false, _mut99812 = false, _mut99813 = false, _mut99814 = false, _mut99815 = false, _mut99816 = false, _mut99817 = false, _mut99818 = false, _mut99819 = false, _mut99820 = false, _mut99821 = false, _mut99822 = false, _mut99823 = false, _mut99824 = false, _mut99825 = false, _mut99826 = false, _mut99827 = false, _mut99828 = false, _mut99829 = false, _mut99830 = false, _mut99831 = false, _mut99832 = false, _mut99833 = false, _mut99834 = false, _mut99835 = false, _mut99836 = false, _mut99837 = false, _mut99838 = false, _mut99839 = false, _mut99840 = false, _mut99841 = false, _mut99842 = false, _mut99843 = false, _mut99844 = false, _mut99845 = false, _mut99846 = false, _mut99847 = false, _mut99848 = false, _mut99849 = false, _mut99850 = false, _mut99851 = false, _mut99852 = false, _mut99853 = false, _mut99854 = false, _mut99855 = false, _mut99856 = false, _mut99857 = false, _mut99858 = false, _mut99859 = false, _mut99860 = false, _mut99861 = false, _mut99862 = false, _mut99863 = false, _mut99864 = false, _mut99865 = false, _mut99866 = false, _mut99867 = false, _mut99868 = false, _mut99869 = false, _mut99870 = false, _mut99871 = false, _mut99872 = false, _mut99873 = false, _mut99874 = false, _mut99875 = false, _mut99876 = false, _mut99877 = false, _mut99878 = false, _mut99879 = false, _mut99880 = false, _mut99881 = false, _mut99882 = false, _mut99883 = false, _mut99884 = false, _mut99885 = false, _mut99886 = false, _mut99887 = false, _mut99888 = false, _mut99889 = false, _mut99890 = false, _mut99891 = false, _mut99892 = false, _mut99893 = false, _mut99894 = false, _mut99895 = false, _mut99896 = false, _mut99897 = false, _mut99898 = false, _mut99899 = false, _mut99900 = false, _mut99901 = false, _mut99902 = false, _mut99903 = false, _mut99904 = false, _mut99905 = false, _mut99906 = false, _mut99907 = false, _mut99908 = false, _mut99909 = false, _mut99910 = false, _mut99911 = false, _mut99912 = false, _mut99913 = false, _mut99914 = false, _mut99915 = false, _mut99916 = false, _mut99917 = false, _mut99918 = false, _mut99919 = false, _mut99920 = false, _mut99921 = false, _mut99922 = false, _mut99923 = false, _mut99924 = false, _mut99925 = false, _mut99926 = false, _mut99927 = false, _mut99928 = false, _mut99929 = false, _mut99930 = false, _mut99931 = false, _mut99932 = false, _mut99933 = false, _mut99934 = false, _mut99935 = false, _mut99936 = false, _mut99937 = false, _mut99938 = false, _mut99939 = false, _mut99940 = false, _mut99941 = false, _mut99942 = false, _mut99943 = false;

    /**
     * Maximal aging triggering an attempt to balance the bracketing interval.
     */
    private static final int MAXIMAL_AGING = 2;

    /**
     * Field to which the elements belong.
     */
    private final Field<T> field;

    /**
     * Maximal order.
     */
    private final int maximalOrder;

    /**
     * Function value accuracy.
     */
    private final T functionValueAccuracy;

    /**
     * Absolute accuracy.
     */
    private final T absoluteAccuracy;

    /**
     * Relative accuracy.
     */
    private final T relativeAccuracy;

    /**
     * Evaluations counter.
     */
    private IntegerSequence.Incrementor evaluations;

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     * @param maximalOrder maximal order.
     * @exception NumberIsTooSmallException if maximal order is lower than 2
     */
    public FieldBracketingNthOrderBrentSolver(final T relativeAccuracy, final T absoluteAccuracy, final T functionValueAccuracy, final int maximalOrder) throws NumberIsTooSmallException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.FieldBracketingNthOrderBrentSolver_81");
        if (ROR_less(maximalOrder, 2, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.FieldBracketingNthOrderBrentSolver_81", _mut99732, _mut99733, _mut99734, _mut99735, _mut99736)) {
            throw new NumberIsTooSmallException(maximalOrder, 2, true);
        }
        this.field = relativeAccuracy.getField();
        this.maximalOrder = maximalOrder;
        this.absoluteAccuracy = absoluteAccuracy;
        this.relativeAccuracy = relativeAccuracy;
        this.functionValueAccuracy = functionValueAccuracy;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    /**
     * Get the maximal order.
     * @return maximal order
     */
    public int getMaximalOrder() {
        return maximalOrder;
    }

    /**
     * Get the maximal number of function evaluations.
     *
     * @return the maximal number of function evaluations.
     */
    public int getMaxEvaluations() {
        return evaluations.getMaximalCount();
    }

    /**
     * Get the number of evaluations of the objective function.
     * The number of evaluations corresponds to the last call to the
     * {@code optimize} method. It is 0 if the method has not been
     * called yet.
     *
     * @return the number of evaluations of the objective function.
     */
    public int getEvaluations() {
        return evaluations.getCount();
    }

    /**
     * Get the absolute accuracy.
     * @return absolute accuracy
     */
    public T getAbsoluteAccuracy() {
        return absoluteAccuracy;
    }

    /**
     * Get the relative accuracy.
     * @return relative accuracy
     */
    public T getRelativeAccuracy() {
        return relativeAccuracy;
    }

    /**
     * Get the function accuracy.
     * @return function accuracy
     */
    public T getFunctionValueAccuracy() {
        return functionValueAccuracy;
    }

    /**
     * Solve for a zero in the given interval.
     * A solver may require that the interval brackets a single zero root.
     * Solvers that do require bracketing should be able to handle the case
     * where one of the endpoints is itself a root.
     *
     * @param maxEval Maximum number of evaluations.
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param allowedSolution The kind of solutions that the root-finding algorithm may
     * accept as solutions.
     * @return a value where the function is zero.
     * @exception NullArgumentException if f is null.
     * @exception NoBracketingException if root cannot be bracketed
     */
    public T solve(final int maxEval, final RealFieldUnivariateFunction<T> f, final T min, final T max, final AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(maxEval, f, min, max, min.add(max).divide(2), allowedSolution);
    }

    /**
     * Solve for a zero in the given interval, start at {@code startValue}.
     * A solver may require that the interval brackets a single zero root.
     * Solvers that do require bracketing should be able to handle the case
     * where one of the endpoints is itself a root.
     *
     * @param maxEval Maximum number of evaluations.
     * @param f Function to solve.
     * @param min Lower bound for the interval.
     * @param max Upper bound for the interval.
     * @param startValue Start value to use.
     * @param allowedSolution The kind of solutions that the root-finding algorithm may
     * accept as solutions.
     * @return a value where the function is zero.
     * @exception NullArgumentException if f is null.
     * @exception NoBracketingException if root cannot be bracketed
     */
    public T solve(final int maxEval, final RealFieldUnivariateFunction<T> f, final T min, final T max, final T startValue, final AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188");
        // Checks.
        MathUtils.checkNotNull(f);
        // Reset.
        evaluations = evaluations.withMaximalCount(maxEval).withStart(0);
        T zero = field.getZero();
        T nan = zero.add(Double.NaN);
        // prepare arrays with the first points
        final T[] x = MathArrays.buildArray(field, AOR_plus(maximalOrder, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99737, _mut99738, _mut99739, _mut99740));
        final T[] y = MathArrays.buildArray(field, AOR_plus(maximalOrder, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99741, _mut99742, _mut99743, _mut99744));
        x[0] = min;
        x[1] = startValue;
        x[2] = max;
        // evaluate initial guess
        evaluations.increment();
        y[1] = f.value(x[1]);
        if (Precision.equals(y[1].getReal(), 0.0, 1)) {
            // return the initial guess if it is a perfect root.
            return x[1];
        }
        // evaluate first endpoint
        evaluations.increment();
        y[0] = f.value(x[0]);
        if (Precision.equals(y[0].getReal(), 0.0, 1)) {
            // return the first endpoint if it is a perfect root.
            return x[0];
        }
        int nbPoints;
        int signChangeIndex;
        if (ROR_less(y[0].multiply(y[1]).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99745, _mut99746, _mut99747, _mut99748, _mut99749)) {
            // reduce interval if it brackets the root
            nbPoints = 2;
            signChangeIndex = 1;
        } else {
            // evaluate second endpoint
            evaluations.increment();
            y[2] = f.value(x[2]);
            if (Precision.equals(y[2].getReal(), 0.0, 1)) {
                // return the second endpoint if it is a perfect root.
                return x[2];
            }
            if (ROR_less(y[1].multiply(y[2]).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99750, _mut99751, _mut99752, _mut99753, _mut99754)) {
                // use all computed point as a start sampling array for solving
                nbPoints = 3;
                signChangeIndex = 2;
            } else {
                throw new NoBracketingException(x[0].getReal(), x[2].getReal(), y[0].getReal(), y[2].getReal());
            }
        }
        // prepare a work array for inverse polynomial interpolation
        final T[] tmpX = MathArrays.buildArray(field, x.length);
        // current tightest bracketing of the root
        T xA = x[AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99755, _mut99756, _mut99757, _mut99758)];
        T yA = y[AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99759, _mut99760, _mut99761, _mut99762)];
        T absXA = xA.abs();
        T absYA = yA.abs();
        int agingA = 0;
        T xB = x[signChangeIndex];
        T yB = y[signChangeIndex];
        T absXB = xB.abs();
        T absYB = yB.abs();
        int agingB = 0;
        // search loop
        while (true) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188");
            // check convergence of bracketing interval
            T maxX = ROR_less(absXA.subtract(absXB).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99763, _mut99764, _mut99765, _mut99766, _mut99767) ? absXB : absXA;
            T maxY = ROR_less(absYA.subtract(absYB).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99768, _mut99769, _mut99770, _mut99771, _mut99772) ? absYB : absYA;
            final T xTol = absoluteAccuracy.add(relativeAccuracy.multiply(maxX));
            if ((_mut99783 ? (ROR_less_equals(xB.subtract(xA).subtract(xTol).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99773, _mut99774, _mut99775, _mut99776, _mut99777) && ROR_less(maxY.subtract(functionValueAccuracy).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99778, _mut99779, _mut99780, _mut99781, _mut99782)) : (ROR_less_equals(xB.subtract(xA).subtract(xTol).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99773, _mut99774, _mut99775, _mut99776, _mut99777) || ROR_less(maxY.subtract(functionValueAccuracy).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99778, _mut99779, _mut99780, _mut99781, _mut99782)))) {
                switch(allowedSolution) {
                    case ANY_SIDE:
                        return ROR_less(absYA.subtract(absYB).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99784, _mut99785, _mut99786, _mut99787, _mut99788) ? xA : xB;
                    case LEFT_SIDE:
                        return xA;
                    case RIGHT_SIDE:
                        return xB;
                    case BELOW_SIDE:
                        return ROR_less_equals(yA.getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99789, _mut99790, _mut99791, _mut99792, _mut99793) ? xA : xB;
                    case ABOVE_SIDE:
                        return ROR_less(yA.getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99794, _mut99795, _mut99796, _mut99797, _mut99798) ? xB : xA;
                    default:
                        // this should never happen
                        throw new MathInternalError(null);
                }
            }
            // target for the next evaluation point
            T targetY;
            if (ROR_greater_equals(agingA, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99799, _mut99800, _mut99801, _mut99802, _mut99803)) {
                // we keep updating the high bracket, try to compensate this
                targetY = yB.divide(16).negate();
            } else if (ROR_greater_equals(agingB, MAXIMAL_AGING, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99804, _mut99805, _mut99806, _mut99807, _mut99808)) {
                // we keep updating the low bracket, try to compensate this
                targetY = yA.divide(16).negate();
            } else {
                // bracketing is balanced, try to find the root itself
                targetY = zero;
            }
            // make a few attempts to guess a root,
            T nextX;
            int start = 0;
            int end = nbPoints;
            do {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188");
                // guess a value for current target, using inverse polynomial interpolation
                System.arraycopy(x, start, tmpX, start, AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99809, _mut99810, _mut99811, _mut99812));
                nextX = guessX(targetY, tmpX, y, start, end);
                if (!((_mut99823 ? ((ROR_greater(nextX.subtract(xA).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99813, _mut99814, _mut99815, _mut99816, _mut99817)) || (ROR_less(nextX.subtract(xB).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99818, _mut99819, _mut99820, _mut99821, _mut99822))) : ((ROR_greater(nextX.subtract(xA).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99813, _mut99814, _mut99815, _mut99816, _mut99817)) && (ROR_less(nextX.subtract(xB).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99818, _mut99819, _mut99820, _mut99821, _mut99822)))))) {
                    // we try again with a lower interpolation order
                    if (ROR_greater_equals(AOR_minus(signChangeIndex, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99824, _mut99825, _mut99826, _mut99827), AOR_minus(end, signChangeIndex, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99828, _mut99829, _mut99830, _mut99831), "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99832, _mut99833, _mut99834, _mut99835, _mut99836)) {
                        // we have more points before the sign change, drop the lowest point
                        ++start;
                    } else {
                        // we have more points after sign change, drop the highest point
                        --end;
                    }
                    // we need to do one more attempt
                    nextX = nan;
                }
            } while ((_mut99846 ? (Double.isNaN(nextX.getReal()) || (ROR_greater(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99837, _mut99838, _mut99839, _mut99840), 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99841, _mut99842, _mut99843, _mut99844, _mut99845))) : (Double.isNaN(nextX.getReal()) && (ROR_greater(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99837, _mut99838, _mut99839, _mut99840), 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99841, _mut99842, _mut99843, _mut99844, _mut99845)))));
            if (Double.isNaN(nextX.getReal())) {
                // fall back to bisection
                nextX = xA.add(xB.subtract(xA).divide(2));
                start = AOR_minus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99847, _mut99848, _mut99849, _mut99850);
                end = signChangeIndex;
            }
            // evaluate the function at the guessed root
            evaluations.increment();
            final T nextY = f.value(nextX);
            if (Precision.equals(nextY.getReal(), 0.0, 1)) {
                // we don't need to bother about the allowed solutions setting
                return nextX;
            }
            if ((_mut99865 ? ((ROR_greater(nbPoints, 2, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99851, _mut99852, _mut99853, _mut99854, _mut99855)) || (ROR_not_equals(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99856, _mut99857, _mut99858, _mut99859), nbPoints, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99860, _mut99861, _mut99862, _mut99863, _mut99864))) : ((ROR_greater(nbPoints, 2, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99851, _mut99852, _mut99853, _mut99854, _mut99855)) && (ROR_not_equals(AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99856, _mut99857, _mut99858, _mut99859), nbPoints, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99860, _mut99861, _mut99862, _mut99863, _mut99864))))) {
                // they are probably too far from the root, drop them from now on
                nbPoints = AOR_minus(end, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99884, _mut99885, _mut99886, _mut99887);
                System.arraycopy(x, start, x, 0, nbPoints);
                System.arraycopy(y, start, y, 0, nbPoints);
                signChangeIndex -= start;
            } else if (ROR_equals(nbPoints, x.length, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99866, _mut99867, _mut99868, _mut99869, _mut99870)) {
                // we have to drop one point in order to insert the new one
                nbPoints--;
                // keep the tightest bracketing interval as centered as possible
                if (ROR_greater_equals(signChangeIndex, AOR_divide((AOR_plus(x.length, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99871, _mut99872, _mut99873, _mut99874)), 2, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99875, _mut99876, _mut99877, _mut99878), "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99879, _mut99880, _mut99881, _mut99882, _mut99883)) {
                    // we drop the lowest point, we have to shift the arrays and the index
                    System.arraycopy(x, 1, x, 0, nbPoints);
                    System.arraycopy(y, 1, y, 0, nbPoints);
                    --signChangeIndex;
                }
            }
            // (by construction, we know it lies inside the tightest bracketing interval)
            System.arraycopy(x, signChangeIndex, x, AOR_plus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99888, _mut99889, _mut99890, _mut99891), AOR_minus(nbPoints, signChangeIndex, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99892, _mut99893, _mut99894, _mut99895));
            x[signChangeIndex] = nextX;
            System.arraycopy(y, signChangeIndex, y, AOR_plus(signChangeIndex, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99896, _mut99897, _mut99898, _mut99899), AOR_minus(nbPoints, signChangeIndex, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99900, _mut99901, _mut99902, _mut99903));
            y[signChangeIndex] = nextY;
            ++nbPoints;
            // update the bracketing interval
            if (ROR_less_equals(nextY.multiply(yA).getReal(), 0, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.solve_188", _mut99904, _mut99905, _mut99906, _mut99907, _mut99908)) {
                // the sign change occurs before the inserted point
                xB = nextX;
                yB = nextY;
                absYB = yB.abs();
                ++agingA;
                agingB = 0;
            } else {
                // the sign change occurs after the inserted point
                xA = nextX;
                yA = nextY;
                absYA = yA.abs();
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
    private T guessX(final T targetY, final T[] x, final T[] y, final int start, final int end) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425");
        // compute Q Newton coefficients by divided differences
        for (int i = start; ROR_less(i, AOR_minus(end, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99930, _mut99931, _mut99932, _mut99933), "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99934, _mut99935, _mut99936, _mut99937, _mut99938); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425");
            final int delta = AOR_minus(AOR_plus(i, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99909, _mut99910, _mut99911, _mut99912), start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99913, _mut99914, _mut99915, _mut99916);
            for (int j = end - 1; ROR_greater(j, i, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99925, _mut99926, _mut99927, _mut99928, _mut99929); --j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425");
                x[j] = x[j].subtract(x[AOR_minus(j, 1, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99921, _mut99922, _mut99923, _mut99924)]).divide(y[j].subtract(y[AOR_minus(j, delta, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99917, _mut99918, _mut99919, _mut99920)]));
            }
        }
        // evaluate Q(targetY)
        T x0 = field.getZero();
        for (int j = end - 1; ROR_greater_equals(j, start, "org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425", _mut99939, _mut99940, _mut99941, _mut99942, _mut99943); --j) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.FieldBracketingNthOrderBrentSolver.guessX_425");
            x0 = x[j].add(x0.multiply(targetY.subtract(y[j])));
        }
        return x0;
    }
}
