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
package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateMatrixFunction;
import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Univariate functions differentiator using finite differences.
 * <p>
 * This class creates some wrapper objects around regular
 * {@link UnivariateFunction univariate functions} (or {@link
 * UnivariateVectorFunction univariate vector functions} or {@link
 * UnivariateMatrixFunction univariate matrix functions}). These
 * wrapper objects compute derivatives in addition to function
 * values.
 * </p>
 * <p>
 * The wrapper objects work by calling the underlying function on
 * a sampling grid around the current point and performing polynomial
 * interpolation. A finite differences scheme with n points is
 * theoretically able to compute derivatives up to order n-1, but
 * it is generally better to have a slight margin. The step size must
 * also be small enough in order for the polynomial approximation to
 * be good in the current point neighborhood, but it should not be too
 * small because numerical instability appears quickly (there are several
 * differences of close points). Choosing the number of points and
 * the step size is highly problem dependent.
 * </p>
 * <p>
 * As an example of good and bad settings, lets consider the quintic
 * polynomial function {@code f(x) = (x-1)*(x-0.5)*x*(x+0.5)*(x+1)}.
 * Since it is a polynomial, finite differences with at least 6 points
 * should theoretically recover the exact same polynomial and hence
 * compute accurate derivatives for any order. However, due to numerical
 * errors, we get the following results for a 7 points finite differences
 * for abscissae in the [-10, 10] range:
 * <ul>
 *   <li>step size = 0.25, second order derivative error about 9.97e-10</li>
 *   <li>step size = 0.25, fourth order derivative error about 5.43e-8</li>
 *   <li>step size = 1.0e-6, second order derivative error about 148</li>
 *   <li>step size = 1.0e-6, fourth order derivative error about 6.35e+14</li>
 * </ul>
 * <p>
 * This example shows that the small step size is really bad, even simply
 * for second order derivative!</p>
 *
 * @since 3.1
 */
public class FiniteDifferencesDifferentiator implements UnivariateFunctionDifferentiator, UnivariateVectorFunctionDifferentiator, UnivariateMatrixFunctionDifferentiator, Serializable {

    @Conditional
    public static boolean _mut96768 = false, _mut96769 = false, _mut96770 = false, _mut96771 = false, _mut96772 = false, _mut96773 = false, _mut96774 = false, _mut96775 = false, _mut96776 = false, _mut96777 = false, _mut96778 = false, _mut96779 = false, _mut96780 = false, _mut96781 = false, _mut96782 = false, _mut96783 = false, _mut96784 = false, _mut96785 = false, _mut96786 = false, _mut96787 = false, _mut96788 = false, _mut96789 = false, _mut96790 = false, _mut96791 = false, _mut96792 = false, _mut96793 = false, _mut96794 = false, _mut96795 = false, _mut96796 = false, _mut96797 = false, _mut96798 = false, _mut96799 = false, _mut96800 = false, _mut96801 = false, _mut96802 = false, _mut96803 = false, _mut96804 = false, _mut96805 = false, _mut96806 = false, _mut96807 = false, _mut96808 = false, _mut96809 = false, _mut96810 = false, _mut96811 = false, _mut96812 = false, _mut96813 = false, _mut96814 = false, _mut96815 = false, _mut96816 = false, _mut96817 = false, _mut96818 = false, _mut96819 = false, _mut96820 = false, _mut96821 = false, _mut96822 = false, _mut96823 = false, _mut96824 = false, _mut96825 = false, _mut96826 = false, _mut96827 = false, _mut96828 = false, _mut96829 = false, _mut96830 = false, _mut96831 = false, _mut96832 = false, _mut96833 = false, _mut96834 = false, _mut96835 = false, _mut96836 = false, _mut96837 = false, _mut96838 = false, _mut96839 = false, _mut96840 = false, _mut96841 = false, _mut96842 = false, _mut96843 = false, _mut96844 = false, _mut96845 = false, _mut96846 = false, _mut96847 = false, _mut96848 = false, _mut96849 = false, _mut96850 = false, _mut96851 = false, _mut96852 = false, _mut96853 = false, _mut96854 = false, _mut96855 = false, _mut96856 = false, _mut96857 = false, _mut96858 = false, _mut96859 = false, _mut96860 = false, _mut96861 = false, _mut96862 = false, _mut96863 = false, _mut96864 = false, _mut96865 = false, _mut96866 = false, _mut96867 = false, _mut96868 = false, _mut96869 = false, _mut96870 = false, _mut96871 = false, _mut96872 = false, _mut96873 = false, _mut96874 = false, _mut96875 = false, _mut96876 = false, _mut96877 = false, _mut96878 = false, _mut96879 = false, _mut96880 = false, _mut96881 = false, _mut96882 = false, _mut96883 = false, _mut96884 = false, _mut96885 = false, _mut96886 = false, _mut96887 = false, _mut96888 = false, _mut96889 = false, _mut96890 = false, _mut96891 = false, _mut96892 = false, _mut96893 = false, _mut96894 = false, _mut96895 = false, _mut96896 = false, _mut96897 = false, _mut96898 = false, _mut96899 = false, _mut96900 = false, _mut96901 = false, _mut96902 = false, _mut96903 = false, _mut96904 = false, _mut96905 = false, _mut96906 = false, _mut96907 = false, _mut96908 = false, _mut96909 = false, _mut96910 = false, _mut96911 = false, _mut96912 = false, _mut96913 = false, _mut96914 = false, _mut96915 = false, _mut96916 = false, _mut96917 = false, _mut96918 = false, _mut96919 = false, _mut96920 = false, _mut96921 = false, _mut96922 = false, _mut96923 = false, _mut96924 = false, _mut96925 = false, _mut96926 = false, _mut96927 = false, _mut96928 = false, _mut96929 = false, _mut96930 = false, _mut96931 = false, _mut96932 = false, _mut96933 = false, _mut96934 = false, _mut96935 = false, _mut96936 = false, _mut96937 = false, _mut96938 = false, _mut96939 = false, _mut96940 = false, _mut96941 = false, _mut96942 = false, _mut96943 = false, _mut96944 = false, _mut96945 = false, _mut96946 = false, _mut96947 = false, _mut96948 = false, _mut96949 = false, _mut96950 = false, _mut96951 = false, _mut96952 = false, _mut96953 = false, _mut96954 = false, _mut96955 = false, _mut96956 = false, _mut96957 = false, _mut96958 = false, _mut96959 = false, _mut96960 = false, _mut96961 = false, _mut96962 = false, _mut96963 = false, _mut96964 = false, _mut96965 = false, _mut96966 = false, _mut96967 = false, _mut96968 = false, _mut96969 = false, _mut96970 = false, _mut96971 = false, _mut96972 = false, _mut96973 = false, _mut96974 = false, _mut96975 = false, _mut96976 = false, _mut96977 = false, _mut96978 = false, _mut96979 = false, _mut96980 = false, _mut96981 = false, _mut96982 = false, _mut96983 = false, _mut96984 = false, _mut96985 = false, _mut96986 = false, _mut96987 = false, _mut96988 = false, _mut96989 = false, _mut96990 = false, _mut96991 = false, _mut96992 = false, _mut96993 = false, _mut96994 = false, _mut96995 = false, _mut96996 = false;

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 20120917L;

    /**
     * Number of points to use.
     */
    private final int nbPoints;

    /**
     * Step size.
     */
    private final double stepSize;

    /**
     * Half sample span.
     */
    private final double halfSampleSpan;

    /**
     * Lower bound for independent variable.
     */
    private final double tMin;

    /**
     * Upper bound for independent variable.
     */
    private final double tMax;

    /**
     * Build a differentiator with number of points and step size when independent variable is unbounded.
     * <p>
     * Beware that wrong settings for the finite differences differentiator
     * can lead to highly unstable and inaccurate results, especially for
     * high derivation orders. Using very small step sizes is often a
     * <em>bad</em> idea.
     * </p>
     * @param nbPoints number of points to use
     * @param stepSize step size (gap between each point)
     * @exception NotPositiveException if {@code stepsize <= 0} (note that
     * {@link NotPositiveException} extends {@link NumberIsTooSmallException})
     * @exception NumberIsTooSmallException {@code nbPoint <= 1}
     */
    public FiniteDifferencesDifferentiator(final int nbPoints, final double stepSize) throws NotPositiveException, NumberIsTooSmallException {
        this(nbPoints, stepSize, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    /**
     * Build a differentiator with number of points and step size when independent variable is bounded.
     * <p>
     * When the independent variable is bounded (tLower &lt; t &lt; tUpper), the sampling
     * points used for differentiation will be adapted to ensure the constraint holds
     * even near the boundaries. This means the sample will not be centered anymore in
     * these cases. At an extreme case, computing derivatives exactly at the lower bound
     * will lead the sample to be entirely on the right side of the derivation point.
     * </p>
     * <p>
     * Note that the boundaries are considered to be excluded for function evaluation.
     * </p>
     * <p>
     * Beware that wrong settings for the finite differences differentiator
     * can lead to highly unstable and inaccurate results, especially for
     * high derivation orders. Using very small step sizes is often a
     * <em>bad</em> idea.
     * </p>
     * @param nbPoints number of points to use
     * @param stepSize step size (gap between each point)
     * @param tLower lower bound for independent variable (may be {@code Double.NEGATIVE_INFINITY}
     * if there are no lower bounds)
     * @param tUpper upper bound for independent variable (may be {@code Double.POSITIVE_INFINITY}
     * if there are no upper bounds)
     * @exception NotPositiveException if {@code stepsize <= 0} (note that
     * {@link NotPositiveException} extends {@link NumberIsTooSmallException})
     * @exception NumberIsTooSmallException {@code nbPoint <= 1}
     * @exception NumberIsTooLargeException {@code stepSize * (nbPoints - 1) >= tUpper - tLower}
     */
    public FiniteDifferencesDifferentiator(final int nbPoints, final double stepSize, final double tLower, final double tUpper) throws NotPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141");
        if (ROR_less_equals(nbPoints, 1, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96768, _mut96769, _mut96770, _mut96771, _mut96772)) {
            throw new NumberIsTooSmallException(stepSize, 1, false);
        }
        this.nbPoints = nbPoints;
        if (ROR_less_equals(stepSize, 0, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96773, _mut96774, _mut96775, _mut96776, _mut96777)) {
            throw new NotPositiveException(stepSize);
        }
        this.stepSize = stepSize;
        halfSampleSpan = AOR_multiply(AOR_multiply(0.5, stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96778, _mut96779, _mut96780, _mut96781), (AOR_minus(nbPoints, 1, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96782, _mut96783, _mut96784, _mut96785)), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96786, _mut96787, _mut96788, _mut96789);
        if (ROR_greater_equals(AOR_multiply(2, halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96790, _mut96791, _mut96792, _mut96793), AOR_minus(tUpper, tLower, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96794, _mut96795, _mut96796, _mut96797), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96798, _mut96799, _mut96800, _mut96801, _mut96802)) {
            throw new NumberIsTooLargeException(AOR_multiply(2, halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96803, _mut96804, _mut96805, _mut96806), AOR_minus(tUpper, tLower, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96807, _mut96808, _mut96809, _mut96810), false);
        }
        final double safety = FastMath.ulp(halfSampleSpan);
        this.tMin = AOR_plus(AOR_plus(tLower, halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96811, _mut96812, _mut96813, _mut96814), safety, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96815, _mut96816, _mut96817, _mut96818);
        this.tMax = AOR_minus(AOR_minus(tUpper, halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96819, _mut96820, _mut96821, _mut96822), safety, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.FiniteDifferencesDifferentiator_141", _mut96823, _mut96824, _mut96825, _mut96826);
    }

    /**
     * Get the number of points to use.
     * @return number of points to use
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     * Get the step size.
     * @return step size
     */
    public double getStepSize() {
        return stepSize;
    }

    /**
     * Evaluate derivatives from a sample.
     * <p>
     * Evaluation is done using divided differences.
     * </p>
     * @param t evaluation abscissa value and derivatives
     * @param t0 first sample point abscissa
     * @param y function values sample {@code y[i] = f(t[i]) = f(t0 + i * stepSize)}
     * @return value and derivatives at {@code t}
     * @exception NumberIsTooLargeException if the requested derivation order
     * is larger or equal to the number of points
     */
    private DerivativeStructure evaluate(final DerivativeStructure t, final double t0, final double[] y) throws NumberIsTooLargeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193");
        // create divided differences diagonal arrays
        final double[] top = new double[nbPoints];
        final double[] bottom = new double[nbPoints];
        for (int i = 0; ROR_less(i, nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96860, _mut96861, _mut96862, _mut96863, _mut96864); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193");
            // update the bottom diagonal of the divided differences array
            bottom[i] = y[i];
            for (int j = 1; ROR_less_equals(j, i, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96855, _mut96856, _mut96857, _mut96858, _mut96859); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193");
                bottom[AOR_minus(i, j, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96827, _mut96828, _mut96829, _mut96830)] = AOR_divide((AOR_minus(bottom[AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96831, _mut96832, _mut96833, _mut96834), 1, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96835, _mut96836, _mut96837, _mut96838)], bottom[AOR_minus(i, j, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96839, _mut96840, _mut96841, _mut96842)], "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96843, _mut96844, _mut96845, _mut96846)), (AOR_multiply(j, stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96847, _mut96848, _mut96849, _mut96850)), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96851, _mut96852, _mut96853, _mut96854);
            }
            // update the top diagonal of the divided differences array
            top[i] = bottom[0];
        }
        // evaluate interpolation polynomial (represented by top diagonal) at t
        final int order = t.getOrder();
        final int parameters = t.getFreeParameters();
        final double[] derivatives = t.getAllDerivatives();
        final double dt0 = AOR_minus(t.getValue(), t0, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96865, _mut96866, _mut96867, _mut96868);
        DerivativeStructure interpolation = new DerivativeStructure(parameters, order, 0.0);
        DerivativeStructure monomial = null;
        for (int i = 0; ROR_less(i, nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96886, _mut96887, _mut96888, _mut96889, _mut96890); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193");
            if (ROR_equals(i, 0, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96869, _mut96870, _mut96871, _mut96872, _mut96873)) {
                // start with monomial(t) = 1
                monomial = new DerivativeStructure(parameters, order, 1.0);
            } else {
                // monomial(t) = (t - t0) * (t - t1) * ... * (t - t(i-1))
                derivatives[0] = AOR_minus(dt0, AOR_multiply((AOR_minus(i, 1, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96874, _mut96875, _mut96876, _mut96877)), stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96878, _mut96879, _mut96880, _mut96881), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.evaluate_193", _mut96882, _mut96883, _mut96884, _mut96885);
                final DerivativeStructure deltaX = new DerivativeStructure(parameters, order, derivatives);
                monomial = monomial.multiply(deltaX);
            }
            interpolation = interpolation.add(monomial.multiply(top[i]));
        }
        return interpolation;
    }

    /**
     * {@inheritDoc}
     * <p>The returned object cannot compute derivatives to arbitrary orders. The
     * value function will throw a {@link NumberIsTooLargeException} if the requested
     * derivation order is larger or equal to the number of points.
     * </p>
     */
    public UnivariateDifferentiableFunction differentiate(final UnivariateFunction function) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253");
        return new UnivariateDifferentiableFunction() {

            /**
             * {@inheritDoc}
             */
            public double value(final double x) throws MathIllegalArgumentException {
                return function.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public DerivativeStructure value(final DerivativeStructure t) throws MathIllegalArgumentException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253");
                // check we can achieve the requested derivation order with the sample
                if (ROR_greater_equals(t.getOrder(), nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253", _mut96891, _mut96892, _mut96893, _mut96894, _mut96895)) {
                    throw new NumberIsTooLargeException(t.getOrder(), nbPoints, false);
                }
                // compute sample position, trying to be centered if possible
                final double t0 = AOR_minus(FastMath.max(FastMath.min(t.getValue(), tMax), tMin), halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253", _mut96896, _mut96897, _mut96898, _mut96899);
                // compute sample points
                final double[] y = new double[nbPoints];
                for (int i = 0; ROR_less(i, nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253", _mut96908, _mut96909, _mut96910, _mut96911, _mut96912); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253");
                    y[i] = function.value(AOR_plus(t0, AOR_multiply(i, stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253", _mut96900, _mut96901, _mut96902, _mut96903), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_253", _mut96904, _mut96905, _mut96906, _mut96907));
                }
                // evaluate derivatives
                return evaluate(t, t0, y);
            }
        };
    }

    /**
     * {@inheritDoc}
     * <p>The returned object cannot compute derivatives to arbitrary orders. The
     * value function will throw a {@link NumberIsTooLargeException} if the requested
     * derivation order is larger or equal to the number of points.
     * </p>
     */
    public UnivariateDifferentiableVectorFunction differentiate(final UnivariateVectorFunction function) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293");
        return new UnivariateDifferentiableVectorFunction() {

            /**
             * {@inheritDoc}
             */
            public double[] value(final double x) throws MathIllegalArgumentException {
                return function.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public DerivativeStructure[] value(final DerivativeStructure t) throws MathIllegalArgumentException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293");
                // check we can achieve the requested derivation order with the sample
                if (ROR_greater_equals(t.getOrder(), nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96913, _mut96914, _mut96915, _mut96916, _mut96917)) {
                    throw new NumberIsTooLargeException(t.getOrder(), nbPoints, false);
                }
                // compute sample position, trying to be centered if possible
                final double t0 = AOR_minus(FastMath.max(FastMath.min(t.getValue(), tMax), tMin), halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96918, _mut96919, _mut96920, _mut96921);
                // compute sample points
                double[][] y = null;
                for (int i = 0; ROR_less(i, nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96940, _mut96941, _mut96942, _mut96943, _mut96944); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293");
                    final double[] v = function.value(AOR_plus(t0, AOR_multiply(i, stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96922, _mut96923, _mut96924, _mut96925), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96926, _mut96927, _mut96928, _mut96929));
                    if (ROR_equals(i, 0, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96930, _mut96931, _mut96932, _mut96933, _mut96934)) {
                        y = new double[v.length][nbPoints];
                    }
                    for (int j = 0; ROR_less(j, v.length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96935, _mut96936, _mut96937, _mut96938, _mut96939); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293");
                        y[j][i] = v[j];
                    }
                }
                // evaluate derivatives
                final DerivativeStructure[] value = new DerivativeStructure[y.length];
                for (int j = 0; ROR_less(j, value.length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293", _mut96945, _mut96946, _mut96947, _mut96948, _mut96949); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_293");
                    value[j] = evaluate(t, t0, y[j]);
                }
                return value;
            }
        };
    }

    /**
     * {@inheritDoc}
     * <p>The returned object cannot compute derivatives to arbitrary orders. The
     * value function will throw a {@link NumberIsTooLargeException} if the requested
     * derivation order is larger or equal to the number of points.
     * </p>
     */
    public UnivariateDifferentiableMatrixFunction differentiate(final UnivariateMatrixFunction function) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
        return new UnivariateDifferentiableMatrixFunction() {

            /**
             * {@inheritDoc}
             */
            public double[][] value(final double x) throws MathIllegalArgumentException {
                return function.value(x);
            }

            /**
             * {@inheritDoc}
             */
            public DerivativeStructure[][] value(final DerivativeStructure t) throws MathIllegalArgumentException {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                // check we can achieve the requested derivation order with the sample
                if (ROR_greater_equals(t.getOrder(), nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96950, _mut96951, _mut96952, _mut96953, _mut96954)) {
                    throw new NumberIsTooLargeException(t.getOrder(), nbPoints, false);
                }
                // compute sample position, trying to be centered if possible
                final double t0 = AOR_minus(FastMath.max(FastMath.min(t.getValue(), tMax), tMin), halfSampleSpan, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96955, _mut96956, _mut96957, _mut96958);
                // compute sample points
                double[][][] y = null;
                for (int i = 0; ROR_less(i, nbPoints, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96982, _mut96983, _mut96984, _mut96985, _mut96986); ++i) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                    final double[][] v = function.value(AOR_plus(t0, AOR_multiply(i, stepSize, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96959, _mut96960, _mut96961, _mut96962), "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96963, _mut96964, _mut96965, _mut96966));
                    if (ROR_equals(i, 0, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96967, _mut96968, _mut96969, _mut96970, _mut96971)) {
                        y = new double[v.length][v[0].length][nbPoints];
                    }
                    for (int j = 0; ROR_less(j, v.length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96977, _mut96978, _mut96979, _mut96980, _mut96981); ++j) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                        for (int k = 0; ROR_less(k, v[j].length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96972, _mut96973, _mut96974, _mut96975, _mut96976); ++k) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                            y[j][k][i] = v[j][k];
                        }
                    }
                }
                // evaluate derivatives
                final DerivativeStructure[][] value = new DerivativeStructure[y.length][y[0].length];
                for (int j = 0; ROR_less(j, value.length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96992, _mut96993, _mut96994, _mut96995, _mut96996); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                    for (int k = 0; ROR_less(k, y[j].length, "org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344", _mut96987, _mut96988, _mut96989, _mut96990, _mut96991); ++k) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator.value_344");
                        value[j][k] = evaluate(t, t0, y[j][k]);
                    }
                }
                return value;
            }
        };
    }
}
