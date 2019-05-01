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
package org.apache.commons.math3.analysis.interpolation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableVectorFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.CombinatoricsUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Polynomial interpolator using both sample values and sample derivatives.
 * <p>
 * The interpolation polynomials match all sample points, including both values
 * and provided derivatives. There is one polynomial for each component of
 * the values vector. All polynomials have the same degree. The degree of the
 * polynomials depends on the number of points and number of derivatives at each
 * point. For example the interpolation polynomials for n sample points without
 * any derivatives all have degree n-1. The interpolation polynomials for n
 * sample points with the two extreme points having value and first derivative
 * and the remaining points having value only all have degree n+1. The
 * interpolation polynomial for n sample points with value, first and second
 * derivative for all points all have degree 3n-1.
 * </p>
 *
 * @since 3.1
 */
public class HermiteInterpolator implements UnivariateDifferentiableVectorFunction {

    @Conditional
    public static boolean _mut94983 = false, _mut94984 = false, _mut94985 = false, _mut94986 = false, _mut94987 = false, _mut94988 = false, _mut94989 = false, _mut94990 = false, _mut94991 = false, _mut94992 = false, _mut94993 = false, _mut94994 = false, _mut94995 = false, _mut94996 = false, _mut94997 = false, _mut94998 = false, _mut94999 = false, _mut95000 = false, _mut95001 = false, _mut95002 = false, _mut95003 = false, _mut95004 = false, _mut95005 = false, _mut95006 = false, _mut95007 = false, _mut95008 = false, _mut95009 = false, _mut95010 = false, _mut95011 = false, _mut95012 = false, _mut95013 = false, _mut95014 = false, _mut95015 = false, _mut95016 = false, _mut95017 = false, _mut95018 = false, _mut95019 = false, _mut95020 = false, _mut95021 = false, _mut95022 = false, _mut95023 = false, _mut95024 = false, _mut95025 = false, _mut95026 = false, _mut95027 = false, _mut95028 = false, _mut95029 = false, _mut95030 = false, _mut95031 = false, _mut95032 = false, _mut95033 = false, _mut95034 = false, _mut95035 = false, _mut95036 = false, _mut95037 = false, _mut95038 = false, _mut95039 = false, _mut95040 = false, _mut95041 = false, _mut95042 = false, _mut95043 = false, _mut95044 = false, _mut95045 = false, _mut95046 = false, _mut95047 = false, _mut95048 = false, _mut95049 = false, _mut95050 = false, _mut95051 = false, _mut95052 = false, _mut95053 = false, _mut95054 = false, _mut95055 = false, _mut95056 = false, _mut95057 = false, _mut95058 = false, _mut95059 = false, _mut95060 = false, _mut95061 = false, _mut95062 = false, _mut95063 = false, _mut95064 = false, _mut95065 = false, _mut95066 = false, _mut95067 = false, _mut95068 = false, _mut95069 = false, _mut95070 = false, _mut95071 = false, _mut95072 = false, _mut95073 = false, _mut95074 = false, _mut95075 = false, _mut95076 = false, _mut95077 = false, _mut95078 = false, _mut95079 = false, _mut95080 = false, _mut95081 = false, _mut95082 = false, _mut95083 = false, _mut95084 = false, _mut95085 = false, _mut95086 = false, _mut95087 = false, _mut95088 = false, _mut95089 = false, _mut95090 = false;

    /**
     * Sample abscissae.
     */
    private final List<Double> abscissae;

    /**
     * Top diagonal of the divided differences array.
     */
    private final List<double[]> topDiagonal;

    /**
     * Bottom diagonal of the divided differences array.
     */
    private final List<double[]> bottomDiagonal;

    /**
     * Create an empty interpolator.
     */
    public HermiteInterpolator() {
        this.abscissae = new ArrayList<Double>();
        this.topDiagonal = new ArrayList<double[]>();
        this.bottomDiagonal = new ArrayList<double[]>();
    }

    /**
     * Add a sample point.
     * <p>
     * This method must be called once for each sample point. It is allowed to
     * mix some calls with values only with calls with values and first
     * derivatives.
     * </p>
     * <p>
     * The point abscissae for all calls <em>must</em> be different.
     * </p>
     * @param x abscissa of the sample point
     * @param value value and derivatives of the sample point
     * (if only one row is passed, it is the value, if two rows are
     * passed the first one is the value and the second the derivative
     * and so on)
     * @exception ZeroException if the abscissa difference between added point
     * and a previous point is zero (i.e. the two points are at same abscissa)
     * @exception MathArithmeticException if the number of derivatives is larger
     * than 20, which prevents computation of a factorial
     */
    public void addSamplePoint(final double x, final double[]... value) throws ZeroException, MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86");
        for (int i = 0; ROR_less(i, value.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95043, _mut95044, _mut95045, _mut95046, _mut95047); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86");
            final double[] y = value[i].clone();
            if (ROR_greater(i, 1, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut94983, _mut94984, _mut94985, _mut94986, _mut94987)) {
                double inv = AOR_divide(1.0, CombinatoricsUtils.factorial(i), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut94988, _mut94989, _mut94990, _mut94991);
                for (int j = 0; ROR_less(j, y.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut94992, _mut94993, _mut94994, _mut94995, _mut94996); ++j) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86");
                    y[j] *= inv;
                }
            }
            // update the bottom diagonal of the divided differences array
            final int n = abscissae.size();
            bottomDiagonal.add(AOR_minus(n, i, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut94997, _mut94998, _mut94999, _mut95000), y);
            double[] bottom0 = y;
            for (int j = i; ROR_less(j, n, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95038, _mut95039, _mut95040, _mut95041, _mut95042); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86");
                final double[] bottom1 = bottomDiagonal.get(AOR_minus(n, (AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95001, _mut95002, _mut95003, _mut95004)), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95005, _mut95006, _mut95007, _mut95008));
                final double inv = AOR_divide(1.0, (AOR_minus(x, abscissae.get(AOR_minus(n, (AOR_plus(j, 1, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95009, _mut95010, _mut95011, _mut95012)), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95013, _mut95014, _mut95015, _mut95016)), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95017, _mut95018, _mut95019, _mut95020)), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95021, _mut95022, _mut95023, _mut95024);
                if (Double.isInfinite(inv)) {
                    throw new ZeroException(LocalizedFormats.DUPLICATED_ABSCISSA_DIVISION_BY_ZERO, x);
                }
                for (int k = 0; ROR_less(k, y.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95033, _mut95034, _mut95035, _mut95036, _mut95037); ++k) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86");
                    bottom1[k] = AOR_multiply(inv, (AOR_minus(bottom0[k], bottom1[k], "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95025, _mut95026, _mut95027, _mut95028)), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.addSamplePoint_86", _mut95029, _mut95030, _mut95031, _mut95032);
                }
                bottom0 = bottom1;
            }
            // update the top diagonal of the divided differences array
            topDiagonal.add(bottom0.clone());
            // update the abscissae array
            abscissae.add(x);
        }
    }

    /**
     * Compute the interpolation polynomials.
     * @return interpolation polynomials array
     * @exception NoDataException if sample is empty
     */
    public PolynomialFunction[] getPolynomials() throws NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129");
        // safety check
        checkInterpolation();
        // iteration initialization
        final PolynomialFunction zero = polynomial(0);
        PolynomialFunction[] polynomials = new PolynomialFunction[topDiagonal.get(0).length];
        for (int i = 0; ROR_less(i, polynomials.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129", _mut95048, _mut95049, _mut95050, _mut95051, _mut95052); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129");
            polynomials[i] = zero;
        }
        PolynomialFunction coeff = polynomial(1);
        // build the polynomials by iterating on the top diagonal of the divided differences array
        for (int i = 0; ROR_less(i, topDiagonal.size(), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129", _mut95058, _mut95059, _mut95060, _mut95061, _mut95062); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129");
            double[] tdi = topDiagonal.get(i);
            for (int k = 0; ROR_less(k, polynomials.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129", _mut95053, _mut95054, _mut95055, _mut95056, _mut95057); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.getPolynomials_129");
                polynomials[k] = polynomials[k].add(coeff.multiply(polynomial(tdi[k])));
            }
            coeff = coeff.multiply(polynomial(-abscissae.get(i), 1.0));
        }
        return polynomials;
    }

    /**
     * Interpolate value at a specified abscissa.
     * <p>
     * Calling this method is equivalent to call the {@link PolynomialFunction#value(double)
     * value} methods of all polynomials returned by {@link #getPolynomials() getPolynomials},
     * except it does not build the intermediate polynomials, so this method is faster and
     * numerically more stable.
     * </p>
     * @param x interpolation abscissa
     * @return interpolated value
     * @exception NoDataException if sample is empty
     */
    public double[] value(double x) throws NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167");
        // safety check
        checkInterpolation();
        final double[] value = new double[topDiagonal.get(0).length];
        double valueCoeff = 1;
        for (int i = 0; ROR_less(i, topDiagonal.size(), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167", _mut95076, _mut95077, _mut95078, _mut95079, _mut95080); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167");
            double[] dividedDifference = topDiagonal.get(i);
            for (int k = 0; ROR_less(k, value.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167", _mut95067, _mut95068, _mut95069, _mut95070, _mut95071); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167");
                value[k] += AOR_multiply(dividedDifference[k], valueCoeff, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167", _mut95063, _mut95064, _mut95065, _mut95066);
            }
            final double deltaX = AOR_minus(x, abscissae.get(i), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_167", _mut95072, _mut95073, _mut95074, _mut95075);
            valueCoeff *= deltaX;
        }
        return value;
    }

    /**
     * Interpolate value at a specified abscissa.
     * <p>
     * Calling this method is equivalent to call the {@link
     * PolynomialFunction#value(DerivativeStructure) value} methods of all polynomials
     * returned by {@link #getPolynomials() getPolynomials}, except it does not build the
     * intermediate polynomials, so this method is faster and numerically more stable.
     * </p>
     * @param x interpolation abscissa
     * @return interpolated value
     * @exception NoDataException if sample is empty
     */
    public DerivativeStructure[] value(final DerivativeStructure x) throws NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_199");
        // safety check
        checkInterpolation();
        final DerivativeStructure[] value = new DerivativeStructure[topDiagonal.get(0).length];
        Arrays.fill(value, x.getField().getZero());
        DerivativeStructure valueCoeff = x.getField().getOne();
        for (int i = 0; ROR_less(i, topDiagonal.size(), "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_199", _mut95086, _mut95087, _mut95088, _mut95089, _mut95090); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_199");
            double[] dividedDifference = topDiagonal.get(i);
            for (int k = 0; ROR_less(k, value.length, "org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_199", _mut95081, _mut95082, _mut95083, _mut95084, _mut95085); ++k) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.analysis.interpolation.HermiteInterpolator.value_199");
                value[k] = value[k].add(valueCoeff.multiply(dividedDifference[k]));
            }
            final DerivativeStructure deltaX = x.subtract(abscissae.get(i));
            valueCoeff = valueCoeff.multiply(deltaX);
        }
        return value;
    }

    /**
     * Check interpolation can be performed.
     * @exception NoDataException if interpolation cannot be performed
     * because sample is empty
     */
    private void checkInterpolation() throws NoDataException {
        if (abscissae.isEmpty()) {
            throw new NoDataException(LocalizedFormats.EMPTY_INTERPOLATION_SAMPLE);
        }
    }

    /**
     * Create a polynomial from its coefficients.
     * @param c polynomials coefficients
     * @return polynomial
     */
    private PolynomialFunction polynomial(double... c) {
        return new PolynomialFunction(c);
    }
}
