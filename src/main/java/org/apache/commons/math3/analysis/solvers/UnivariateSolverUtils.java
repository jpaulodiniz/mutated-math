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
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Utility routines for {@link UnivariateSolver} objects.
 */
public class UnivariateSolverUtils {

    @Conditional
    public static boolean _mut99944 = false, _mut99945 = false, _mut99946 = false, _mut99947 = false, _mut99948 = false, _mut99949 = false, _mut99950 = false, _mut99951 = false, _mut99952 = false, _mut99953 = false, _mut99954 = false, _mut99955 = false, _mut99956 = false, _mut99957 = false, _mut99958 = false, _mut99959 = false, _mut99960 = false, _mut99961 = false, _mut99962 = false, _mut99963 = false, _mut99964 = false, _mut99965 = false, _mut99966 = false, _mut99967 = false, _mut99968 = false, _mut99969 = false, _mut99970 = false, _mut99971 = false, _mut99972 = false, _mut99973 = false, _mut99974 = false, _mut99975 = false, _mut99976 = false, _mut99977 = false, _mut99978 = false, _mut99979 = false, _mut99980 = false, _mut99981 = false, _mut99982 = false, _mut99983 = false, _mut99984 = false, _mut99985 = false, _mut99986 = false, _mut99987 = false, _mut99988 = false, _mut99989 = false, _mut99990 = false, _mut99991 = false, _mut99992 = false, _mut99993 = false, _mut99994 = false, _mut99995 = false, _mut99996 = false, _mut99997 = false, _mut99998 = false, _mut99999 = false, _mut100000 = false, _mut100001 = false, _mut100002 = false, _mut100003 = false, _mut100004 = false, _mut100005 = false, _mut100006 = false, _mut100007 = false, _mut100008 = false, _mut100009 = false, _mut100010 = false, _mut100011 = false, _mut100012 = false, _mut100013 = false, _mut100014 = false, _mut100015 = false, _mut100016 = false, _mut100017 = false, _mut100018 = false, _mut100019 = false, _mut100020 = false, _mut100021 = false, _mut100022 = false, _mut100023 = false, _mut100024 = false, _mut100025 = false, _mut100026 = false, _mut100027 = false, _mut100028 = false, _mut100029 = false, _mut100030 = false, _mut100031 = false, _mut100032 = false, _mut100033 = false, _mut100034 = false, _mut100035 = false, _mut100036 = false, _mut100037 = false, _mut100038 = false, _mut100039 = false, _mut100040 = false, _mut100041 = false, _mut100042 = false, _mut100043 = false, _mut100044 = false, _mut100045 = false, _mut100046 = false, _mut100047 = false, _mut100048 = false, _mut100049 = false, _mut100050 = false, _mut100051 = false, _mut100052 = false, _mut100053 = false, _mut100054 = false, _mut100055 = false, _mut100056 = false, _mut100057 = false, _mut100058 = false, _mut100059 = false, _mut100060 = false, _mut100061 = false, _mut100062 = false, _mut100063 = false, _mut100064 = false, _mut100065 = false, _mut100066 = false, _mut100067 = false, _mut100068 = false, _mut100069 = false, _mut100070 = false, _mut100071 = false, _mut100072 = false, _mut100073 = false, _mut100074 = false, _mut100075 = false, _mut100076 = false, _mut100077 = false, _mut100078 = false, _mut100079 = false, _mut100080 = false, _mut100081 = false, _mut100082 = false, _mut100083 = false, _mut100084 = false, _mut100085 = false, _mut100086 = false, _mut100087 = false, _mut100088 = false, _mut100089 = false, _mut100090 = false, _mut100091 = false, _mut100092 = false, _mut100093 = false, _mut100094 = false, _mut100095 = false, _mut100096 = false, _mut100097 = false, _mut100098 = false, _mut100099 = false, _mut100100 = false, _mut100101 = false, _mut100102 = false, _mut100103 = false, _mut100104 = false, _mut100105 = false, _mut100106 = false, _mut100107 = false, _mut100108 = false, _mut100109 = false, _mut100110 = false, _mut100111 = false, _mut100112 = false, _mut100113 = false, _mut100114 = false, _mut100115 = false, _mut100116 = false, _mut100117 = false, _mut100118 = false, _mut100119 = false, _mut100120 = false, _mut100121 = false, _mut100122 = false, _mut100123 = false, _mut100124 = false, _mut100125 = false, _mut100126 = false, _mut100127 = false, _mut100128 = false, _mut100129 = false, _mut100130 = false, _mut100131 = false, _mut100132 = false, _mut100133 = false, _mut100134 = false, _mut100135 = false, _mut100136 = false, _mut100137 = false, _mut100138 = false, _mut100139 = false, _mut100140 = false, _mut100141 = false;

    /**
     * Class contains only static methods.
     */
    private UnivariateSolverUtils() {
    }

    /**
     * Convenience method to find a zero of a univariate real function.  A default
     * solver is used.
     *
     * @param function Function.
     * @param x0 Lower bound for the interval.
     * @param x1 Upper bound for the interval.
     * @return a value where the function is zero.
     * @throws NoBracketingException if the function has the same sign at the
     * endpoints.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static double solve(UnivariateFunction function, double x0, double x1) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION);
        }
        final UnivariateSolver solver = new BrentSolver();
        return solver.solve(Integer.MAX_VALUE, function, x0, x1);
    }

    /**
     * Convenience method to find a zero of a univariate real function.  A default
     * solver is used.
     *
     * @param function Function.
     * @param x0 Lower bound for the interval.
     * @param x1 Upper bound for the interval.
     * @param absoluteAccuracy Accuracy to be used by the solver.
     * @return a value where the function is zero.
     * @throws NoBracketingException if the function has the same sign at the
     * endpoints.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static double solve(UnivariateFunction function, double x0, double x1, double absoluteAccuracy) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION);
        }
        final UnivariateSolver solver = new BrentSolver(absoluteAccuracy);
        return solver.solve(Integer.MAX_VALUE, function, x0, x1);
    }

    /**
     * Force a root found by a non-bracketing solver to lie on a specified side,
     * as if the solver were a bracketing one.
     *
     * @param maxEval maximal number of new evaluations of the function
     * (evaluations already done for finding the root should have already been subtracted
     * from this number)
     * @param f function to solve
     * @param bracketing bracketing solver to use for shifting the root
     * @param baseRoot original root found by a previous non-bracketing solver
     * @param min minimal bound of the search interval
     * @param max maximal bound of the search interval
     * @param allowedSolution the kind of solutions that the root-finding algorithm may
     * accept as solutions.
     * @return a root approximation, on the specified side of the exact root
     * @throws NoBracketingException if the function has the same sign at the
     * endpoints.
     */
    public static double forceSide(final int maxEval, final UnivariateFunction f, final BracketedUnivariateSolver<UnivariateFunction> bracketing, final double baseRoot, final double min, final double max, final AllowedSolution allowedSolution) throws NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102");
        if (allowedSolution == AllowedSolution.ANY_SIDE) {
            // no further bracketing required
            return baseRoot;
        }
        // find a very small interval bracketing the root
        final double step = FastMath.max(bracketing.getAbsoluteAccuracy(), FastMath.abs(AOR_multiply(baseRoot, bracketing.getRelativeAccuracy(), "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99944, _mut99945, _mut99946, _mut99947)));
        double xLo = FastMath.max(min, AOR_minus(baseRoot, step, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99948, _mut99949, _mut99950, _mut99951));
        double fLo = f.value(xLo);
        double xHi = FastMath.min(max, AOR_plus(baseRoot, step, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99952, _mut99953, _mut99954, _mut99955));
        double fHi = f.value(xHi);
        int remainingEval = AOR_minus(maxEval, 2, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99956, _mut99957, _mut99958, _mut99959);
        while (ROR_greater(remainingEval, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut100011, _mut100012, _mut100013, _mut100014, _mut100015)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102");
            if ((_mut99982 ? (((_mut99970 ? (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99960, _mut99961, _mut99962, _mut99963, _mut99964) || ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99965, _mut99966, _mut99967, _mut99968, _mut99969)) : (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99960, _mut99961, _mut99962, _mut99963, _mut99964) && ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99965, _mut99966, _mut99967, _mut99968, _mut99969)))) && ((_mut99981 ? (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99971, _mut99972, _mut99973, _mut99974, _mut99975) || ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99976, _mut99977, _mut99978, _mut99979, _mut99980)) : (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99971, _mut99972, _mut99973, _mut99974, _mut99975) && ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99976, _mut99977, _mut99978, _mut99979, _mut99980))))) : (((_mut99970 ? (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99960, _mut99961, _mut99962, _mut99963, _mut99964) || ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99965, _mut99966, _mut99967, _mut99968, _mut99969)) : (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99960, _mut99961, _mut99962, _mut99963, _mut99964) && ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99965, _mut99966, _mut99967, _mut99968, _mut99969)))) || ((_mut99981 ? (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99971, _mut99972, _mut99973, _mut99974, _mut99975) || ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99976, _mut99977, _mut99978, _mut99979, _mut99980)) : (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99971, _mut99972, _mut99973, _mut99974, _mut99975) && ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99976, _mut99977, _mut99978, _mut99979, _mut99980))))))) {
                // compute the root on the selected side
                return bracketing.solve(remainingEval, f, xLo, xHi, baseRoot, allowedSolution);
            }
            // try increasing the interval
            boolean changeLo = false;
            boolean changeHi = false;
            if (ROR_less(fLo, fHi, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99983, _mut99984, _mut99985, _mut99986, _mut99987)) {
                // increasing function
                if (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99998, _mut99999, _mut100000, _mut100001, _mut100002)) {
                    changeLo = true;
                } else {
                    changeHi = true;
                }
            } else if (ROR_greater(fLo, fHi, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99988, _mut99989, _mut99990, _mut99991, _mut99992)) {
                // decreasing function
                if (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut99993, _mut99994, _mut99995, _mut99996, _mut99997)) {
                    changeLo = true;
                } else {
                    changeHi = true;
                }
            } else {
                // unknown variation
                changeLo = true;
                changeHi = true;
            }
            // update the lower bound
            if (changeLo) {
                xLo = FastMath.max(min, AOR_minus(xLo, step, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut100003, _mut100004, _mut100005, _mut100006));
                fLo = f.value(xLo);
                remainingEval--;
            }
            // update the higher bound
            if (changeHi) {
                xHi = FastMath.min(max, AOR_plus(xHi, step, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut100007, _mut100008, _mut100009, _mut100010));
                fHi = f.value(xHi);
                remainingEval--;
            }
        }
        throw new NoBracketingException(LocalizedFormats.FAILED_BRACKETING, xLo, xHi, fLo, fHi, AOR_minus(maxEval, remainingEval, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide_102", _mut100016, _mut100017, _mut100018, _mut100019), maxEval, baseRoot, min, max);
    }

    /**
     * This method simply calls {@link #bracket(UnivariateFunction, double, double, double,
     * double, double, int) bracket(function, initial, lowerBound, upperBound, q, r, maximumIterations)}
     * with {@code q} and {@code r} set to 1.0 and {@code maximumIterations} set to {@code Integer.MAX_VALUE}.
     * <p>
     * <strong>Note: </strong> this method can take {@code Integer.MAX_VALUE}
     * iterations to throw a {@code ConvergenceException.}  Unless you are
     * confident that there is a root between {@code lowerBound} and
     * {@code upperBound} near {@code initial}, it is better to use
     * {@link #bracket(UnivariateFunction, double, double, double, double,double, int)
     * bracket(function, initial, lowerBound, upperBound, q, r, maximumIterations)},
     * explicitly specifying the maximum number of iterations.</p>
     *
     * @param function Function.
     * @param initial Initial midpoint of interval being expanded to
     * bracket a root.
     * @param lowerBound Lower bound (a is never lower than this value)
     * @param upperBound Upper bound (b never is greater than this
     * value).
     * @return a two-element array holding a and b.
     * @throws NoBracketingException if a root cannot be bracketted.
     * @throws NotStrictlyPositiveException if {@code maximumIterations <= 0}.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(function, initial, lowerBound, upperBound, 1.0, 1.0, Integer.MAX_VALUE);
    }

    /**
     * This method simply calls {@link #bracket(UnivariateFunction, double, double, double,
     * double, double, int) bracket(function, initial, lowerBound, upperBound, q, r, maximumIterations)}
     * with {@code q} and {@code r} set to 1.0.
     * @param function Function.
     * @param initial Initial midpoint of interval being expanded to
     * bracket a root.
     * @param lowerBound Lower bound (a is never lower than this value).
     * @param upperBound Upper bound (b never is greater than this
     * value).
     * @param maximumIterations Maximum number of iterations to perform
     * @return a two element array holding a and b.
     * @throws NoBracketingException if the algorithm fails to find a and b
     * satisfying the desired conditions.
     * @throws NotStrictlyPositiveException if {@code maximumIterations <= 0}.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound, int maximumIterations) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(function, initial, lowerBound, upperBound, 1.0, 1.0, maximumIterations);
    }

    /**
     * This method attempts to find two values a and b satisfying <ul>
     * <li> {@code lowerBound <= a < initial < b <= upperBound} </li>
     * <li> {@code f(a) * f(b) <= 0} </li>
     * </ul>
     * If {@code f} is continuous on {@code [a,b]}, this means that {@code a}
     * and {@code b} bracket a root of {@code f}.
     * <p>
     * The algorithm checks the sign of \( f(l_k) \) and \( f(u_k) \) for increasing
     * values of k, where \( l_k = max(lower, initial - \delta_k) \),
     * \( u_k = min(upper, initial + \delta_k) \), using recurrence
     * \( \delta_{k+1} = r \delta_k + q, \delta_0 = 0\) and starting search with \( k=1 \).
     * The algorithm stops when one of the following happens: <ul>
     * <li> at least one positive and one negative value have been found --  success!</li>
     * <li> both endpoints have reached their respective limits -- NoBracketingException </li>
     * <li> {@code maximumIterations} iterations elapse -- NoBracketingException </li></ul>
     * <p>
     * If different signs are found at first iteration ({@code k=1}), then the returned
     * interval will be \( [a, b] = [l_1, u_1] \). If different signs are found at a later
     * iteration {@code k>1}, then the returned interval will be either
     * \( [a, b] = [l_{k+1}, l_{k}] \) or \( [a, b] = [u_{k}, u_{k+1}] \). A root solver called
     * with these parameters will therefore start with the smallest bracketing interval known
     * at this step.
     * </p>
     * <p>
     * Interval expansion rate is tuned by changing the recurrence parameters {@code r} and
     * {@code q}. When the multiplicative factor {@code r} is set to 1, the sequence is a
     * simple arithmetic sequence with linear increase. When the multiplicative factor {@code r}
     * is larger than 1, the sequence has an asymptotically exponential rate. Note than the
     * additive parameter {@code q} should never be set to zero, otherwise the interval would
     * degenerate to the single initial point for all values of {@code k}.
     * </p>
     * <p>
     * As a rule of thumb, when the location of the root is expected to be approximately known
     * within some error margin, {@code r} should be set to 1 and {@code q} should be set to the
     * order of magnitude of the error margin. When the location of the root is really a wild guess,
     * then {@code r} should be set to a value larger than 1 (typically 2 to double the interval
     * length at each iteration) and {@code q} should be set according to half the initial
     * search interval length.
     * </p>
     * <p>
     * As an example, if we consider the trivial function {@code f(x) = 1 - x} and use
     * {@code initial = 4}, {@code r = 1}, {@code q = 2}, the algorithm will compute
     * {@code f(4-2) = f(2) = -1} and {@code f(4+2) = f(6) = -5} for {@code k = 1}, then
     * {@code f(4-4) = f(0) = +1} and {@code f(4+4) = f(8) = -7} for {@code k = 2}. Then it will
     * return the interval {@code [0, 2]} as the smallest one known to be bracketing the root.
     * As shown by this example, the initial value (here {@code 4}) may lie outside of the returned
     * bracketing interval.
     * </p>
     * @param function function to check
     * @param initial Initial midpoint of interval being expanded to
     * bracket a root.
     * @param lowerBound Lower bound (a is never lower than this value).
     * @param upperBound Upper bound (b never is greater than this
     * value).
     * @param q additive offset used to compute bounds sequence (must be strictly positive)
     * @param r multiplicative factor used to compute bounds sequence
     * @param maximumIterations Maximum number of iterations to perform
     * @return a two element array holding the bracketing values.
     * @exception NoBracketingException if function cannot be bracketed in the search interval
     */
    public static double[] bracket(final UnivariateFunction function, final double initial, final double lowerBound, final double upperBound, final double q, final double r, final int maximumIterations) throws NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295");
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION);
        }
        if (ROR_less_equals(q, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100020, _mut100021, _mut100022, _mut100023, _mut100024)) {
            throw new NotStrictlyPositiveException(q);
        }
        if (ROR_less_equals(maximumIterations, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100025, _mut100026, _mut100027, _mut100028, _mut100029)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, maximumIterations);
        }
        verifySequence(lowerBound, initial, upperBound);
        // initialize the recurrence
        double a = initial;
        double b = initial;
        double fa = Double.NaN;
        double fb = Double.NaN;
        double delta = 0;
        for (int numIterations = 0; (_mut100094 ? ((ROR_less(numIterations, maximumIterations, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100078, _mut100079, _mut100080, _mut100081, _mut100082)) || ((_mut100093 ? (ROR_greater(a, lowerBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100083, _mut100084, _mut100085, _mut100086, _mut100087) && ROR_less(b, upperBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100088, _mut100089, _mut100090, _mut100091, _mut100092)) : (ROR_greater(a, lowerBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100083, _mut100084, _mut100085, _mut100086, _mut100087) || ROR_less(b, upperBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100088, _mut100089, _mut100090, _mut100091, _mut100092))))) : ((ROR_less(numIterations, maximumIterations, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100078, _mut100079, _mut100080, _mut100081, _mut100082)) && ((_mut100093 ? (ROR_greater(a, lowerBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100083, _mut100084, _mut100085, _mut100086, _mut100087) && ROR_less(b, upperBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100088, _mut100089, _mut100090, _mut100091, _mut100092)) : (ROR_greater(a, lowerBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100083, _mut100084, _mut100085, _mut100086, _mut100087) || ROR_less(b, upperBound, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100088, _mut100089, _mut100090, _mut100091, _mut100092)))))); ++numIterations) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295");
            final double previousA = a;
            final double previousFa = fa;
            final double previousB = b;
            final double previousFb = fb;
            delta = AOR_plus(AOR_multiply(r, delta, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100030, _mut100031, _mut100032, _mut100033), q, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100034, _mut100035, _mut100036, _mut100037);
            a = FastMath.max(AOR_minus(initial, delta, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100038, _mut100039, _mut100040, _mut100041), lowerBound);
            b = FastMath.min(AOR_plus(initial, delta, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100042, _mut100043, _mut100044, _mut100045), upperBound);
            fa = function.value(a);
            fb = function.value(b);
            if (ROR_equals(numIterations, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100046, _mut100047, _mut100048, _mut100049, _mut100050)) {
                // we simply compare both sides of the initial interval
                if (ROR_less_equals(AOR_multiply(fa, fb, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100069, _mut100070, _mut100071, _mut100072), 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100073, _mut100074, _mut100075, _mut100076, _mut100077)) {
                    // the first interval already brackets a root
                    return new double[] { a, b };
                }
            } else {
                // we expect sign changes to occur at boundaries
                if (ROR_less_equals(AOR_multiply(fa, previousFa, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100051, _mut100052, _mut100053, _mut100054), 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100055, _mut100056, _mut100057, _mut100058, _mut100059)) {
                    // sign change detected at near lower bound
                    return new double[] { a, previousA };
                } else if (ROR_less_equals(AOR_multiply(fb, previousFb, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100060, _mut100061, _mut100062, _mut100063), 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.bracket_295", _mut100064, _mut100065, _mut100066, _mut100067, _mut100068)) {
                    // sign change detected at near upper bound
                    return new double[] { previousB, b };
                }
            }
        }
        // no bracketing found
        throw new NoBracketingException(a, b, fa, fb);
    }

    /**
     * Compute the midpoint of two values.
     *
     * @param a first value.
     * @param b second value.
     * @return the midpoint.
     */
    public static double midpoint(double a, double b) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.midpoint_366");
        return AOR_multiply((AOR_plus(a, b, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.midpoint_366", _mut100095, _mut100096, _mut100097, _mut100098)), 0.5, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.midpoint_366", _mut100099, _mut100100, _mut100101, _mut100102);
    }

    /**
     * Check whether the interval bounds bracket a root. That is, if the
     * values at the endpoints are not equal to zero, then the function takes
     * opposite signs at the endpoints.
     *
     * @param function Function.
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @return {@code true} if the function values have opposite signs at the
     * given points.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static boolean isBracketing(UnivariateFunction function, final double lower, final double upper) throws NullArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382");
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION);
        }
        final double fLo = function.value(lower);
        final double fHi = function.value(upper);
        return (_mut100125 ? (((_mut100113 ? (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100103, _mut100104, _mut100105, _mut100106, _mut100107) || ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100108, _mut100109, _mut100110, _mut100111, _mut100112)) : (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100103, _mut100104, _mut100105, _mut100106, _mut100107) && ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100108, _mut100109, _mut100110, _mut100111, _mut100112)))) && ((_mut100124 ? (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100114, _mut100115, _mut100116, _mut100117, _mut100118) || ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100119, _mut100120, _mut100121, _mut100122, _mut100123)) : (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100114, _mut100115, _mut100116, _mut100117, _mut100118) && ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100119, _mut100120, _mut100121, _mut100122, _mut100123))))) : (((_mut100113 ? (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100103, _mut100104, _mut100105, _mut100106, _mut100107) || ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100108, _mut100109, _mut100110, _mut100111, _mut100112)) : (ROR_greater_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100103, _mut100104, _mut100105, _mut100106, _mut100107) && ROR_less_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100108, _mut100109, _mut100110, _mut100111, _mut100112)))) || ((_mut100124 ? (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100114, _mut100115, _mut100116, _mut100117, _mut100118) || ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100119, _mut100120, _mut100121, _mut100122, _mut100123)) : (ROR_less_equals(fLo, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100114, _mut100115, _mut100116, _mut100117, _mut100118) && ROR_greater_equals(fHi, 0, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isBracketing_382", _mut100119, _mut100120, _mut100121, _mut100122, _mut100123))))));
    }

    /**
     * Check whether the arguments form a (strictly) increasing sequence.
     *
     * @param start First number.
     * @param mid Second number.
     * @param end Third number.
     * @return {@code true} if the arguments form an increasing sequence.
     */
    public static boolean isSequence(final double start, final double mid, final double end) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isSequence_402");
        return (_mut100136 ? ((ROR_less(start, mid, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isSequence_402", _mut100126, _mut100127, _mut100128, _mut100129, _mut100130)) || (ROR_less(mid, end, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isSequence_402", _mut100131, _mut100132, _mut100133, _mut100134, _mut100135))) : ((ROR_less(start, mid, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isSequence_402", _mut100126, _mut100127, _mut100128, _mut100129, _mut100130)) && (ROR_less(mid, end, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.isSequence_402", _mut100131, _mut100132, _mut100133, _mut100134, _mut100135))));
    }

    /**
     * Check that the endpoints specify an interval.
     *
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @throws NumberIsTooLargeException if {@code lower >= upper}.
     */
    public static void verifyInterval(final double lower, final double upper) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.verifyInterval_415");
        if (ROR_greater_equals(lower, upper, "org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.verifyInterval_415", _mut100137, _mut100138, _mut100139, _mut100140, _mut100141)) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, lower, upper, false);
        }
    }

    /**
     * Check that {@code lower < initial < upper}.
     *
     * @param lower Lower endpoint.
     * @param initial Initial value.
     * @param upper Upper endpoint.
     * @throws NumberIsTooLargeException if {@code lower >= initial} or
     * {@code initial >= upper}.
     */
    public static void verifySequence(final double lower, final double initial, final double upper) throws NumberIsTooLargeException {
        verifyInterval(lower, initial);
        verifyInterval(initial, upper);
    }

    /**
     * Check that the endpoints specify an interval and the end points
     * bracket a root.
     *
     * @param function Function.
     * @param lower Lower endpoint.
     * @param upper Upper endpoint.
     * @throws NoBracketingException if the function has the same sign at the
     * endpoints.
     * @throws NullArgumentException if {@code function} is {@code null}.
     */
    public static void verifyBracketing(UnivariateFunction function, final double lower, final double upper) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION);
        }
        verifyInterval(lower, upper);
        if (!isBracketing(function, lower, upper)) {
            throw new NoBracketingException(lower, upper, function.value(lower), function.value(upper));
        }
    }
}
