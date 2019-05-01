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

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://mathworld.wolfram.com/LaguerresMethod.html">
 * Laguerre's Method</a> for root finding of real coefficient polynomials.
 * For reference, see
 * <blockquote>
 *  <b>A First Course in Numerical Analysis</b>,
 *  ISBN 048641454X, chapter 8.
 * </blockquote>
 * Laguerre's method is global in the sense that it can start with any initial
 * approximation and be able to solve all roots from that point.
 * The algorithm requires a bracketing condition.
 *
 * @since 1.2
 */
public class LaguerreSolver extends AbstractPolynomialSolver {

    @Conditional
    public static boolean _mut99122 = false, _mut99123 = false, _mut99124 = false, _mut99125 = false, _mut99126 = false, _mut99127 = false, _mut99128 = false, _mut99129 = false, _mut99130 = false, _mut99131 = false, _mut99132 = false, _mut99133 = false, _mut99134 = false, _mut99135 = false, _mut99136 = false, _mut99137 = false, _mut99138 = false, _mut99139 = false, _mut99140 = false, _mut99141 = false, _mut99142 = false, _mut99143 = false, _mut99144 = false, _mut99145 = false, _mut99146 = false, _mut99147 = false, _mut99148 = false, _mut99149 = false, _mut99150 = false, _mut99151 = false, _mut99152 = false, _mut99153 = false, _mut99154 = false, _mut99155 = false, _mut99156 = false, _mut99157 = false, _mut99158 = false, _mut99159 = false, _mut99160 = false, _mut99161 = false, _mut99162 = false, _mut99163 = false, _mut99164 = false, _mut99165 = false, _mut99166 = false, _mut99167 = false, _mut99168 = false, _mut99169 = false, _mut99170 = false, _mut99171 = false, _mut99172 = false, _mut99173 = false, _mut99174 = false, _mut99175 = false, _mut99176 = false, _mut99177 = false, _mut99178 = false, _mut99179 = false, _mut99180 = false, _mut99181 = false, _mut99182 = false, _mut99183 = false, _mut99184 = false, _mut99185 = false, _mut99186 = false, _mut99187 = false, _mut99188 = false, _mut99189 = false, _mut99190 = false, _mut99191 = false, _mut99192 = false, _mut99193 = false, _mut99194 = false, _mut99195 = false, _mut99196 = false, _mut99197 = false, _mut99198 = false, _mut99199 = false, _mut99200 = false, _mut99201 = false, _mut99202 = false, _mut99203 = false, _mut99204 = false, _mut99205 = false, _mut99206 = false, _mut99207 = false, _mut99208 = false, _mut99209 = false, _mut99210 = false, _mut99211 = false, _mut99212 = false, _mut99213 = false, _mut99214 = false, _mut99215 = false, _mut99216 = false, _mut99217 = false, _mut99218 = false, _mut99219 = false, _mut99220 = false, _mut99221 = false, _mut99222 = false, _mut99223 = false, _mut99224 = false, _mut99225 = false, _mut99226 = false, _mut99227 = false, _mut99228 = false, _mut99229 = false, _mut99230 = false, _mut99231 = false, _mut99232 = false, _mut99233 = false, _mut99234 = false, _mut99235 = false, _mut99236 = false, _mut99237 = false, _mut99238 = false, _mut99239 = false, _mut99240 = false, _mut99241 = false, _mut99242 = false, _mut99243 = false, _mut99244 = false, _mut99245 = false, _mut99246 = false, _mut99247 = false, _mut99248 = false, _mut99249 = false, _mut99250 = false, _mut99251 = false, _mut99252 = false, _mut99253 = false, _mut99254 = false, _mut99255 = false, _mut99256 = false, _mut99257 = false, _mut99258 = false, _mut99259 = false;

    /**
     * Default absolute accuracy.
     */
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1e-6;

    /**
     * Complex solver.
     */
    private final ComplexSolver complexSolver = new ComplexSolver();

    /**
     * Construct a solver with default accuracy (1e-6).
     */
    public LaguerreSolver() {
        this(DEFAULT_ABSOLUTE_ACCURACY);
    }

    /**
     * Construct a solver.
     *
     * @param absoluteAccuracy Absolute accuracy.
     */
    public LaguerreSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     */
    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /**
     * Construct a solver.
     *
     * @param relativeAccuracy Relative accuracy.
     * @param absoluteAccuracy Absolute accuracy.
     * @param functionValueAccuracy Function value accuracy.
     */
    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90");
        final double min = getMin();
        final double max = getMax();
        final double initial = getStartValue();
        final double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, initial, max);
        // Return the initial guess if it is good enough.
        final double yInitial = computeObjectiveValue(initial);
        if (ROR_less_equals(FastMath.abs(yInitial), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99122, _mut99123, _mut99124, _mut99125, _mut99126)) {
            return initial;
        }
        // Return the first endpoint if it is good enough.
        final double yMin = computeObjectiveValue(min);
        if (ROR_less_equals(FastMath.abs(yMin), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99127, _mut99128, _mut99129, _mut99130, _mut99131)) {
            return min;
        }
        // Reduce interval if min and initial bracket the root.
        if (ROR_less(AOR_multiply(yInitial, yMin, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99132, _mut99133, _mut99134, _mut99135), 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99136, _mut99137, _mut99138, _mut99139, _mut99140)) {
            return laguerre(min, initial, yMin, yInitial);
        }
        // Return the second endpoint if it is good enough.
        final double yMax = computeObjectiveValue(max);
        if (ROR_less_equals(FastMath.abs(yMax), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99141, _mut99142, _mut99143, _mut99144, _mut99145)) {
            return max;
        }
        // Reduce interval if initial and max bracket the root.
        if (ROR_less(AOR_multiply(yInitial, yMax, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99146, _mut99147, _mut99148, _mut99149), 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.doSolve_90", _mut99150, _mut99151, _mut99152, _mut99153, _mut99154)) {
            return laguerre(initial, max, yInitial, yMax);
        }
        throw new NoBracketingException(min, max, yMin, yMax);
    }

    /**
     * Find a real root in the given interval.
     *
     * Despite the bracketing condition, the root returned by
     * {@link LaguerreSolver.ComplexSolver#solve(Complex[],Complex)} may
     * not be a real zero inside {@code [min, max]}.
     * For example, <code> p(x) = x<sup>3</sup> + 1, </code>
     * with {@code min = -2}, {@code max = 2}, {@code initial = 0}.
     * When it occurs, this code calls
     * {@link LaguerreSolver.ComplexSolver#solveAll(Complex[],Complex)}
     * in order to obtain all roots and picks up one real root.
     *
     * @param lo Lower bound of the search interval.
     * @param hi Higher bound of the search interval.
     * @param fLo Function value at the lower bound of the search interval.
     * @param fHi Function value at the higher bound of the search interval.
     * @return the point at which the function value is zero.
     * @deprecated This method should not be part of the public API: It will
     * be made private in version 4.0.
     */
    @Deprecated
    public double laguerre(double lo, double hi, double fLo, double fHi) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.laguerre_153");
        final Complex[] c = ComplexUtils.convertToComplex(getCoefficients());
        final Complex initial = new Complex(AOR_multiply(0.5, (AOR_plus(lo, hi, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.laguerre_153", _mut99155, _mut99156, _mut99157, _mut99158)), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.laguerre_153", _mut99159, _mut99160, _mut99161, _mut99162), 0);
        final Complex z = complexSolver.solve(c, initial);
        if (complexSolver.isRoot(lo, hi, z)) {
            return z.getReal();
        } else {
            double r = Double.NaN;
            // Solve all roots and select the one we are seeking.
            Complex[] root = complexSolver.solveAll(c, initial);
            for (int i = 0; ROR_less(i, root.length, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.laguerre_153", _mut99163, _mut99164, _mut99165, _mut99166, _mut99167); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.laguerre_153");
                if (complexSolver.isRoot(lo, hi, root[i])) {
                    r = root[i].getReal();
                    break;
                }
            }
            return r;
        }
    }

    /**
     * Find all complex roots for the polynomial with the given
     * coefficients, starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients Polynomial coefficients.
     * @param initial Start value.
     * @return the full set of complex roots of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded when solving for one of the roots
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}.
     * @throws NoDataException if the {@code coefficients} array is empty.
     * @since 3.1
     */
    public Complex[] solveAllComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveAllComplex(coefficients, initial, Integer.MAX_VALUE);
    }

    /**
     * Find all complex roots for the polynomial with the given
     * coefficients, starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients polynomial coefficients
     * @param initial start value
     * @param maxEval maximum number of evaluations
     * @return the full set of complex roots of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded when solving for one of the roots
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}
     * @throws NoDataException if the {@code coefficients} array is empty
     * @since 3.5
     */
    public Complex[] solveAllComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(maxEval, new PolynomialFunction(coefficients), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, initial);
        return complexSolver.solveAll(ComplexUtils.convertToComplex(coefficients), new Complex(initial, 0d));
    }

    /**
     * Find a complex root for the polynomial with the given coefficients,
     * starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients Polynomial coefficients.
     * @param initial Start value.
     * @return a complex root of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded.
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}.
     * @throws NoDataException if the {@code coefficients} array is empty.
     * @since 3.1
     */
    public Complex solveComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveComplex(coefficients, initial, Integer.MAX_VALUE);
    }

    /**
     * Find a complex root for the polynomial with the given coefficients,
     * starting from the given initial value.
     * <p>
     * Note: This method is not part of the API of {@link BaseUnivariateSolver}.</p>
     *
     * @param coefficients polynomial coefficients
     * @param initial start value
     * @param maxEval maximum number of evaluations
     * @return a complex root of the polynomial
     * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
     * if the maximum number of evaluations is exceeded
     * @throws NullArgumentException if the {@code coefficients} is
     * {@code null}
     * @throws NoDataException if the {@code coefficients} array is empty
     * @since 3.1
     */
    public Complex solveComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(maxEval, new PolynomialFunction(coefficients), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, initial);
        return complexSolver.solve(ComplexUtils.convertToComplex(coefficients), new Complex(initial, 0d));
    }

    /**
     * Class for searching all (complex) roots.
     */
    private class ComplexSolver {

        /**
         * Check whether the given complex root is actually a real zero
         * in the given interval, within the solver tolerance level.
         *
         * @param min Lower bound for the interval.
         * @param max Upper bound for the interval.
         * @param z Complex root.
         * @return {@code true} if z is a real zero.
         */
        public boolean isRoot(double min, double max, Complex z) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299");
            if (isSequence(min, z.getReal(), max)) {
                double tolerance = FastMath.max(AOR_multiply(getRelativeAccuracy(), z.abs(), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299", _mut99168, _mut99169, _mut99170, _mut99171), getAbsoluteAccuracy());
                return (_mut99182 ? ((ROR_less_equals(FastMath.abs(z.getImaginary()), tolerance, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299", _mut99172, _mut99173, _mut99174, _mut99175, _mut99176)) && (ROR_less_equals(z.abs(), getFunctionValueAccuracy(), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299", _mut99177, _mut99178, _mut99179, _mut99180, _mut99181))) : ((ROR_less_equals(FastMath.abs(z.getImaginary()), tolerance, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299", _mut99172, _mut99173, _mut99174, _mut99175, _mut99176)) || (ROR_less_equals(z.abs(), getFunctionValueAccuracy(), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.isRoot_299", _mut99177, _mut99178, _mut99179, _mut99180, _mut99181))));
            }
            return false;
        }

        /**
         * Find all complex roots for the polynomial with the given
         * coefficients, starting from the given initial value.
         *
         * @param coefficients Polynomial coefficients.
         * @param initial Start value.
         * @return the point at which the function value is zero.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the maximum number of evaluations is exceeded.
         * @throws NullArgumentException if the {@code coefficients} is
         * {@code null}.
         * @throws NoDataException if the {@code coefficients} array is empty.
         */
        public Complex[] solveAll(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321");
            if (coefficients == null) {
                throw new NullArgumentException();
            }
            final int n = AOR_minus(coefficients.length, 1, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99183, _mut99184, _mut99185, _mut99186);
            if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99187, _mut99188, _mut99189, _mut99190, _mut99191)) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            // Coefficients for deflated polynomial.
            final Complex[] c = new Complex[AOR_plus(n, 1, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99192, _mut99193, _mut99194, _mut99195)];
            for (int i = 0; ROR_less_equals(i, n, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99196, _mut99197, _mut99198, _mut99199, _mut99200); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321");
                c[i] = coefficients[i];
            }
            // Solve individual roots successively.
            final Complex[] root = new Complex[n];
            for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99218, _mut99219, _mut99220, _mut99221, _mut99222); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321");
                final Complex[] subarray = new Complex[AOR_plus(AOR_minus(n, i, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99201, _mut99202, _mut99203, _mut99204), 1, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99205, _mut99206, _mut99207, _mut99208)];
                System.arraycopy(c, 0, subarray, 0, subarray.length);
                root[i] = solve(subarray, initial);
                // Polynomial deflation using synthetic division.
                Complex newc = c[AOR_minus(n, i, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99209, _mut99210, _mut99211, _mut99212)];
                Complex oldc = null;
                for (int j = n - i - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321", _mut99213, _mut99214, _mut99215, _mut99216, _mut99217); j--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solveAll_321");
                    oldc = c[j];
                    c[j] = newc;
                    newc = oldc.add(newc.multiply(root[i]));
                }
            }
            return root;
        }

        /**
         * Find a complex root for the polynomial with the given coefficients,
         * starting from the given initial value.
         *
         * @param coefficients Polynomial coefficients.
         * @param initial Start value.
         * @return the point at which the function value is zero.
         * @throws org.apache.commons.math3.exception.TooManyEvaluationsException
         * if the maximum number of evaluations is exceeded.
         * @throws NullArgumentException if the {@code coefficients} is
         * {@code null}.
         * @throws NoDataException if the {@code coefficients} array is empty.
         */
        public Complex solve(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370");
            if (coefficients == null) {
                throw new NullArgumentException();
            }
            final int n = AOR_minus(coefficients.length, 1, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99223, _mut99224, _mut99225, _mut99226);
            if (ROR_equals(n, 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99227, _mut99228, _mut99229, _mut99230, _mut99231)) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            final double absoluteAccuracy = getAbsoluteAccuracy();
            final double relativeAccuracy = getRelativeAccuracy();
            final double functionValueAccuracy = getFunctionValueAccuracy();
            final Complex nC = new Complex(n, 0);
            final Complex n1C = new Complex(AOR_minus(n, 1, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99232, _mut99233, _mut99234, _mut99235), 0);
            Complex z = initial;
            Complex oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            while (true) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370");
                // d2v (second derivative value) simultaneously.
                Complex pv = coefficients[n];
                Complex dv = Complex.ZERO;
                Complex d2v = Complex.ZERO;
                for (int j = n - 1; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99236, _mut99237, _mut99238, _mut99239, _mut99240); j--) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370");
                    d2v = dv.add(z.multiply(d2v));
                    dv = pv.add(z.multiply(dv));
                    pv = coefficients[j].add(z.multiply(pv));
                }
                d2v = d2v.multiply(new Complex(2.0, 0.0));
                // Check for convergence.
                final double tolerance = FastMath.max(AOR_multiply(relativeAccuracy, z.abs(), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99241, _mut99242, _mut99243, _mut99244), absoluteAccuracy);
                if (ROR_less_equals((z.subtract(oldz)).abs(), tolerance, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99245, _mut99246, _mut99247, _mut99248, _mut99249)) {
                    return z;
                }
                if (ROR_less_equals(pv.abs(), functionValueAccuracy, "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99250, _mut99251, _mut99252, _mut99253, _mut99254)) {
                    return z;
                }
                // Now pv != 0, calculate the new approximation.
                final Complex G = dv.divide(pv);
                final Complex G2 = G.multiply(G);
                final Complex H = G2.subtract(d2v.divide(pv));
                final Complex delta = n1C.multiply((nC.multiply(H)).subtract(G2));
                // Choose a denominator larger in magnitude.
                final Complex deltaSqrt = delta.sqrt();
                final Complex dplus = G.add(deltaSqrt);
                final Complex dminus = G.subtract(deltaSqrt);
                final Complex denominator = ROR_greater(dplus.abs(), dminus.abs(), "org.apache.commons.math3.analysis.solvers.LaguerreSolver.solve_370", _mut99255, _mut99256, _mut99257, _mut99258, _mut99259) ? dplus : dminus;
                // p(x) = x^3 + 1, z = 0.
                if (denominator.equals(new Complex(0.0, 0.0))) {
                    z = z.add(new Complex(absoluteAccuracy, absoluteAccuracy));
                    oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    oldz = z;
                    z = z.subtract(nC.divide(denominator));
                }
                incrementEvaluationCount();
            }
        }
    }
}
