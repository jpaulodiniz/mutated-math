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
package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the representation of a real polynomial function in
 * <a href="http://mathworld.wolfram.com/LagrangeInterpolatingPolynomial.html">
 * Lagrange Form</a>. For reference, see <b>Introduction to Numerical
 * Analysis</b>, ISBN 038795452X, chapter 2.
 * <p>
 * The approximated function should be smooth enough for Lagrange polynomial
 * to work well. Otherwise, consider using splines instead.</p>
 *
 * @since 1.2
 */
public class PolynomialFunctionLagrangeForm implements UnivariateFunction {

    @Conditional
    public static boolean _mut90677 = false, _mut90678 = false, _mut90679 = false, _mut90680 = false, _mut90681 = false, _mut90682 = false, _mut90683 = false, _mut90684 = false, _mut90685 = false, _mut90686 = false, _mut90687 = false, _mut90688 = false, _mut90689 = false, _mut90690 = false, _mut90691 = false, _mut90692 = false, _mut90693 = false, _mut90694 = false, _mut90695 = false, _mut90696 = false, _mut90697 = false, _mut90698 = false, _mut90699 = false, _mut90700 = false, _mut90701 = false, _mut90702 = false, _mut90703 = false, _mut90704 = false, _mut90705 = false, _mut90706 = false, _mut90707 = false, _mut90708 = false, _mut90709 = false, _mut90710 = false, _mut90711 = false, _mut90712 = false, _mut90713 = false, _mut90714 = false, _mut90715 = false, _mut90716 = false, _mut90717 = false, _mut90718 = false, _mut90719 = false, _mut90720 = false, _mut90721 = false, _mut90722 = false, _mut90723 = false, _mut90724 = false, _mut90725 = false, _mut90726 = false, _mut90727 = false, _mut90728 = false, _mut90729 = false, _mut90730 = false, _mut90731 = false, _mut90732 = false, _mut90733 = false, _mut90734 = false, _mut90735 = false, _mut90736 = false, _mut90737 = false, _mut90738 = false, _mut90739 = false, _mut90740 = false, _mut90741 = false, _mut90742 = false, _mut90743 = false, _mut90744 = false, _mut90745 = false, _mut90746 = false, _mut90747 = false, _mut90748 = false, _mut90749 = false, _mut90750 = false, _mut90751 = false, _mut90752 = false, _mut90753 = false, _mut90754 = false, _mut90755 = false, _mut90756 = false, _mut90757 = false, _mut90758 = false, _mut90759 = false, _mut90760 = false, _mut90761 = false, _mut90762 = false, _mut90763 = false, _mut90764 = false, _mut90765 = false, _mut90766 = false, _mut90767 = false, _mut90768 = false, _mut90769 = false, _mut90770 = false, _mut90771 = false, _mut90772 = false, _mut90773 = false, _mut90774 = false, _mut90775 = false, _mut90776 = false, _mut90777 = false, _mut90778 = false, _mut90779 = false, _mut90780 = false, _mut90781 = false, _mut90782 = false, _mut90783 = false, _mut90784 = false, _mut90785 = false, _mut90786 = false, _mut90787 = false, _mut90788 = false, _mut90789 = false, _mut90790 = false, _mut90791 = false, _mut90792 = false, _mut90793 = false, _mut90794 = false, _mut90795 = false, _mut90796 = false, _mut90797 = false, _mut90798 = false, _mut90799 = false, _mut90800 = false, _mut90801 = false, _mut90802 = false, _mut90803 = false, _mut90804 = false, _mut90805 = false, _mut90806 = false, _mut90807 = false, _mut90808 = false, _mut90809 = false, _mut90810 = false, _mut90811 = false, _mut90812 = false, _mut90813 = false, _mut90814 = false, _mut90815 = false, _mut90816 = false, _mut90817 = false, _mut90818 = false, _mut90819 = false, _mut90820 = false, _mut90821 = false, _mut90822 = false, _mut90823 = false, _mut90824 = false, _mut90825 = false, _mut90826 = false, _mut90827 = false, _mut90828 = false, _mut90829 = false, _mut90830 = false, _mut90831 = false, _mut90832 = false, _mut90833 = false, _mut90834 = false, _mut90835 = false, _mut90836 = false, _mut90837 = false, _mut90838 = false, _mut90839 = false, _mut90840 = false, _mut90841 = false, _mut90842 = false, _mut90843 = false, _mut90844 = false, _mut90845 = false, _mut90846 = false, _mut90847 = false, _mut90848 = false, _mut90849 = false, _mut90850 = false, _mut90851 = false, _mut90852 = false, _mut90853 = false, _mut90854 = false, _mut90855 = false, _mut90856 = false, _mut90857 = false, _mut90858 = false, _mut90859 = false, _mut90860 = false, _mut90861 = false, _mut90862 = false, _mut90863 = false, _mut90864 = false, _mut90865 = false, _mut90866 = false, _mut90867 = false, _mut90868 = false, _mut90869 = false, _mut90870 = false, _mut90871 = false, _mut90872 = false, _mut90873 = false, _mut90874 = false, _mut90875 = false, _mut90876 = false, _mut90877 = false, _mut90878 = false;

    /**
     * The coefficients of the polynomial, ordered by degree -- i.e.
     * coefficients[0] is the constant term and coefficients[n] is the
     * coefficient of x^n where n is the degree of the polynomial.
     */
    private double[] coefficients;

    /**
     * Interpolating points (abscissas).
     */
    private final double[] x;

    /**
     * Function values at interpolating points.
     */
    private final double[] y;

    /**
     * Whether the polynomial coefficients are available.
     */
    private boolean coefficientsComputed;

    /**
     * Construct a Lagrange polynomial with the given abscissas and function
     * values. The order of interpolating points are not important.
     * <p>
     * The constructor makes copy of the input arrays and assigns them.</p>
     *
     * @param x interpolating points
     * @param y function values at interpolating points
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws NonMonotonicSequenceException
     * if two abscissae have the same value.
     */
    public PolynomialFunctionLagrangeForm(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        this.x = new double[x.length];
        this.y = new double[y.length];
        System.arraycopy(x, 0, this.x, 0, x.length);
        System.arraycopy(y, 0, this.y, 0, y.length);
        coefficientsComputed = false;
        if (!verifyInterpolationArray(x, y, false)) {
            MathArrays.sortInPlace(this.x, this.y);
            // Second check in case some abscissa is duplicated.
            verifyInterpolationArray(this.x, this.y, true);
        }
    }

    /**
     * Calculate the function value at the given point.
     *
     * @param z Point at which the function value is to be computed.
     * @return the function value.
     * @throws DimensionMismatchException if {@code x} and {@code y} have
     * different lengths.
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order.
     * @throws NumberIsTooSmallException if the size of {@code x} is less
     * than 2.
     */
    public double value(double z) {
        return evaluateInternal(x, y, z);
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the degree of the polynomial
     */
    public int degree() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.degree_107");
        return AOR_minus(x.length, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.degree_107", _mut90677, _mut90678, _mut90679, _mut90680);
    }

    /**
     * Returns a copy of the interpolating points array.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     *
     * @return a fresh copy of the interpolating points array
     */
    public double[] getInterpolatingPoints() {
        double[] out = new double[x.length];
        System.arraycopy(x, 0, out, 0, x.length);
        return out;
    }

    /**
     * Returns a copy of the interpolating values array.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     *
     * @return a fresh copy of the interpolating values array
     */
    public double[] getInterpolatingValues() {
        double[] out = new double[y.length];
        System.arraycopy(y, 0, out, 0, y.length);
        return out;
    }

    /**
     * Returns a copy of the coefficients array.
     * <p>
     * Changes made to the returned copy will not affect the polynomial.</p>
     * <p>
     * Note that coefficients computation can be ill-conditioned. Use with caution
     * and only when it is necessary.</p>
     *
     * @return a fresh copy of the coefficients array
     */
    public double[] getCoefficients() {
        if (!coefficientsComputed) {
            computeCoefficients();
        }
        double[] out = new double[coefficients.length];
        System.arraycopy(coefficients, 0, out, 0, coefficients.length);
        return out;
    }

    /**
     * Evaluate the Lagrange polynomial using
     * <a href="http://mathworld.wolfram.com/NevillesAlgorithm.html">
     * Neville's Algorithm</a>. It takes O(n^2) time.
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @param z Point at which the function value is to be computed.
     * @return the function value.
     * @throws DimensionMismatchException if {@code x} and {@code y} have
     * different lengths.
     * @throws NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order.
     * @throws NumberIsTooSmallException if the size of {@code x} is less
     * than 2.
     */
    public static double evaluate(double[] x, double[] y, double z) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (verifyInterpolationArray(x, y, false)) {
            return evaluateInternal(x, y, z);
        }
        // Array is not sorted.
        final double[] xNew = new double[x.length];
        final double[] yNew = new double[y.length];
        System.arraycopy(x, 0, xNew, 0, x.length);
        System.arraycopy(y, 0, yNew, 0, y.length);
        MathArrays.sortInPlace(xNew, yNew);
        // Second check in case some abscissa is duplicated.
        verifyInterpolationArray(xNew, yNew, true);
        return evaluateInternal(xNew, yNew, z);
    }

    /**
     * Evaluate the Lagrange polynomial using
     * <a href="http://mathworld.wolfram.com/NevillesAlgorithm.html">
     * Neville's Algorithm</a>. It takes O(n^2) time.
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @param z Point at which the function value is to be computed.
     * @return the function value.
     * @throws DimensionMismatchException if {@code x} and {@code y} have
     * different lengths.
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order.
     * @throws NumberIsTooSmallException if the size of {@code x} is less
     * than 2.
     */
    private static double evaluateInternal(double[] x, double[] y, double z) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206");
        int nearest = 0;
        final int n = x.length;
        final double[] c = new double[n];
        final double[] d = new double[n];
        double min_dist = Double.POSITIVE_INFINITY;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90690, _mut90691, _mut90692, _mut90693, _mut90694); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206");
            // initialize the difference arrays
            c[i] = y[i];
            d[i] = y[i];
            // find out the abscissa closest to z
            final double dist = FastMath.abs(AOR_minus(z, x[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90681, _mut90682, _mut90683, _mut90684));
            if (ROR_less(dist, min_dist, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90685, _mut90686, _mut90687, _mut90688, _mut90689)) {
                nearest = i;
                min_dist = dist;
            }
        }
        // initial approximation to the function value at z
        double value = y[nearest];
        for (int i = 1; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90761, _mut90762, _mut90763, _mut90764, _mut90765); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206");
            for (int j = 0; ROR_less(j, AOR_minus(n, i, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90735, _mut90736, _mut90737, _mut90738), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90739, _mut90740, _mut90741, _mut90742, _mut90743); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206");
                final double tc = AOR_minus(x[j], z, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90695, _mut90696, _mut90697, _mut90698);
                final double td = AOR_minus(x[AOR_plus(i, j, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90699, _mut90700, _mut90701, _mut90702)], z, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90703, _mut90704, _mut90705, _mut90706);
                final double divider = AOR_minus(x[j], x[AOR_plus(i, j, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90707, _mut90708, _mut90709, _mut90710)], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90711, _mut90712, _mut90713, _mut90714);
                // update the difference arrays
                final double w = AOR_divide((AOR_minus(c[AOR_plus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90715, _mut90716, _mut90717, _mut90718)], d[j], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90719, _mut90720, _mut90721, _mut90722)), divider, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90723, _mut90724, _mut90725, _mut90726);
                c[j] = AOR_multiply(tc, w, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90727, _mut90728, _mut90729, _mut90730);
                d[j] = AOR_multiply(td, w, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90731, _mut90732, _mut90733, _mut90734);
            }
            // sum up the difference terms to get the final value
            if (ROR_less(nearest, AOR_multiply(0.5, (AOR_plus(AOR_minus(n, i, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90744, _mut90745, _mut90746, _mut90747), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90748, _mut90749, _mut90750, _mut90751)), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90752, _mut90753, _mut90754, _mut90755), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.evaluateInternal_206", _mut90756, _mut90757, _mut90758, _mut90759, _mut90760)) {
                // fork down
                value += c[nearest];
            } else {
                nearest--;
                // fork up
                value += d[nearest];
            }
        }
        return value;
    }

    /**
     * Calculate the coefficients of Lagrange polynomial from the
     * interpolation data. It takes O(n^2) time.
     * Note that this computation can be ill-conditioned: Use with caution
     * and only when it is necessary.
     */
    protected void computeCoefficients() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
        final int n = AOR_plus(degree(), 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90766, _mut90767, _mut90768, _mut90769);
        coefficients = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90770, _mut90771, _mut90772, _mut90773, _mut90774); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
            coefficients[i] = 0.0;
        }
        // c[] are the coefficients of P(x) = (x-x[0])(x-x[1])...(x-x[n-1])
        final double[] c = new double[AOR_plus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90775, _mut90776, _mut90777, _mut90778)];
        c[0] = 1.0;
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90800, _mut90801, _mut90802, _mut90803, _mut90804); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
            for (int j = i; ROR_greater(j, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90791, _mut90792, _mut90793, _mut90794, _mut90795); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
                c[j] = AOR_minus(c[AOR_minus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90779, _mut90780, _mut90781, _mut90782)], AOR_multiply(c[j], x[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90783, _mut90784, _mut90785, _mut90786), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90787, _mut90788, _mut90789, _mut90790);
            }
            c[0] *= -x[i];
            c[AOR_plus(i, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90796, _mut90797, _mut90798, _mut90799)] = 1;
        }
        final double[] tc = new double[n];
        for (int i = 0; ROR_less(i, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90864, _mut90865, _mut90866, _mut90867, _mut90868); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
            // d = (x[i]-x[0])...(x[i]-x[i-1])(x[i]-x[i+1])...(x[i]-x[n-1])
            double d = 1;
            for (int j = 0; ROR_less(j, n, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90814, _mut90815, _mut90816, _mut90817, _mut90818); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
                if (ROR_not_equals(i, j, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90805, _mut90806, _mut90807, _mut90808, _mut90809)) {
                    d *= AOR_minus(x[i], x[j], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90810, _mut90811, _mut90812, _mut90813);
                }
            }
            final double t = AOR_divide(y[i], d, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90819, _mut90820, _mut90821, _mut90822);
            // actually c[n] = 1
            tc[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90823, _mut90824, _mut90825, _mut90826)] = c[n];
            coefficients[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90827, _mut90828, _mut90829, _mut90830)] += AOR_multiply(t, tc[AOR_minus(n, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90831, _mut90832, _mut90833, _mut90834)], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90835, _mut90836, _mut90837, _mut90838);
            for (int j = n - 2; ROR_greater_equals(j, 0, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90859, _mut90860, _mut90861, _mut90862, _mut90863); j--) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255");
                tc[j] = AOR_plus(c[AOR_plus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90839, _mut90840, _mut90841, _mut90842)], AOR_multiply(tc[AOR_plus(j, 1, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90843, _mut90844, _mut90845, _mut90846)], x[i], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90847, _mut90848, _mut90849, _mut90850), "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90851, _mut90852, _mut90853, _mut90854);
                coefficients[j] += AOR_multiply(t, tc[j], "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.computeCoefficients_255", _mut90855, _mut90856, _mut90857, _mut90858);
            }
        }
        coefficientsComputed = true;
    }

    /**
     * Check that the interpolation arrays are valid.
     * The arrays features checked by this method are that both arrays have the
     * same length and this length is at least 2.
     *
     * @param x Interpolating points array.
     * @param y Interpolating values array.
     * @param abort Whether to throw an exception if {@code x} is not sorted.
     * @throws DimensionMismatchException if the array lengths are different.
     * @throws NumberIsTooSmallException if the number of points is less than 2.
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     * if {@code x} is not sorted in strictly increasing order and {@code abort}
     * is {@code true}.
     * @return {@code false} if the {@code x} is not sorted in increasing order,
     * {@code true} otherwise.
     * @see #evaluate(double[], double[], double)
     * @see #computeCoefficients()
     */
    public static boolean verifyInterpolationArray(double[] x, double[] y, boolean abort) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.verifyInterpolationArray_315");
        if (ROR_not_equals(x.length, y.length, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.verifyInterpolationArray_315", _mut90869, _mut90870, _mut90871, _mut90872, _mut90873)) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (ROR_less(x.length, 2, "org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm.verifyInterpolationArray_315", _mut90874, _mut90875, _mut90876, _mut90877, _mut90878)) {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 2, x.length, true);
        }
        return MathArrays.checkOrder(x, MathArrays.OrderDirection.INCREASING, true, abort);
    }
}
