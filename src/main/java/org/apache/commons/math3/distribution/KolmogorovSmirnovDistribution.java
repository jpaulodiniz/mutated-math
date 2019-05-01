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
package org.apache.commons.math3.distribution;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.fraction.BigFractionField;
import org.apache.commons.math3.fraction.FractionConversionException;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the Kolmogorov-Smirnov distribution.
 *
 * <p>
 * Treats the distribution of the two-sided {@code P(D_n < d)} where
 * {@code D_n = sup_x |G(x) - G_n (x)|} for the theoretical cdf {@code G} and
 * the empirical cdf {@code G_n}.
 * </p>
 * <p>
 * This implementation is based on [1] with certain quick decisions for extreme
 * values given in [2].
 * </p>
 * <p>
 * In short, when wanting to evaluate {@code P(D_n < d)}, the method in [1] is
 * to write {@code d = (k - h) / n} for positive integer {@code k} and
 * {@code 0 <= h < 1}. Then {@code P(D_n < d) = (n! / n^n) * t_kk}, where
 * {@code t_kk} is the {@code (k, k)}'th entry in the special matrix
 * {@code H^n}, i.e. {@code H} to the {@code n}'th power.
 * </p>
 * <p>
 * References:
 * <ul>
 * <li>[1] <a href="http://www.jstatsoft.org/v08/i18/">
 * Evaluating Kolmogorov's Distribution</a> by George Marsaglia, Wai
 * Wan Tsang, and Jingbo Wang</li>
 * <li>[2] <a href="http://www.jstatsoft.org/v39/i11/">
 * Computing the Two-Sided Kolmogorov-Smirnov Distribution</a> by Richard Simard
 * and Pierre L'Ecuyer</li>
 * </ul>
 * Note that [1] contains an error in computing h, refer to
 * <a href="https://issues.apache.org/jira/browse/MATH-437">MATH-437</a> for details.
 * </p>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Kolmogorov-Smirnov_test">
 * Kolmogorov-Smirnov test (Wikipedia)</a>
 * @deprecated to be removed in version 4.0 -
 *  use {@link org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest}
 */
public class KolmogorovSmirnovDistribution implements Serializable {

    @Conditional
    public static boolean _mut55531 = false, _mut55532 = false, _mut55533 = false, _mut55534 = false, _mut55535 = false, _mut55536 = false, _mut55537 = false, _mut55538 = false, _mut55539 = false, _mut55540 = false, _mut55541 = false, _mut55542 = false, _mut55543 = false, _mut55544 = false, _mut55545 = false, _mut55546 = false, _mut55547 = false, _mut55548 = false, _mut55549 = false, _mut55550 = false, _mut55551 = false, _mut55552 = false, _mut55553 = false, _mut55554 = false, _mut55555 = false, _mut55556 = false, _mut55557 = false, _mut55558 = false, _mut55559 = false, _mut55560 = false, _mut55561 = false, _mut55562 = false, _mut55563 = false, _mut55564 = false, _mut55565 = false, _mut55566 = false, _mut55567 = false, _mut55568 = false, _mut55569 = false, _mut55570 = false, _mut55571 = false, _mut55572 = false, _mut55573 = false, _mut55574 = false, _mut55575 = false, _mut55576 = false, _mut55577 = false, _mut55578 = false, _mut55579 = false, _mut55580 = false, _mut55581 = false, _mut55582 = false, _mut55583 = false, _mut55584 = false, _mut55585 = false, _mut55586 = false, _mut55587 = false, _mut55588 = false, _mut55589 = false, _mut55590 = false, _mut55591 = false, _mut55592 = false, _mut55593 = false, _mut55594 = false, _mut55595 = false, _mut55596 = false, _mut55597 = false, _mut55598 = false, _mut55599 = false, _mut55600 = false, _mut55601 = false, _mut55602 = false, _mut55603 = false, _mut55604 = false, _mut55605 = false, _mut55606 = false, _mut55607 = false, _mut55608 = false, _mut55609 = false, _mut55610 = false, _mut55611 = false, _mut55612 = false, _mut55613 = false, _mut55614 = false, _mut55615 = false, _mut55616 = false, _mut55617 = false, _mut55618 = false, _mut55619 = false, _mut55620 = false, _mut55621 = false, _mut55622 = false, _mut55623 = false, _mut55624 = false, _mut55625 = false, _mut55626 = false, _mut55627 = false, _mut55628 = false, _mut55629 = false, _mut55630 = false, _mut55631 = false, _mut55632 = false, _mut55633 = false, _mut55634 = false, _mut55635 = false, _mut55636 = false, _mut55637 = false, _mut55638 = false, _mut55639 = false, _mut55640 = false, _mut55641 = false, _mut55642 = false, _mut55643 = false, _mut55644 = false, _mut55645 = false, _mut55646 = false, _mut55647 = false, _mut55648 = false, _mut55649 = false, _mut55650 = false, _mut55651 = false, _mut55652 = false, _mut55653 = false, _mut55654 = false, _mut55655 = false, _mut55656 = false, _mut55657 = false, _mut55658 = false, _mut55659 = false, _mut55660 = false, _mut55661 = false, _mut55662 = false, _mut55663 = false, _mut55664 = false, _mut55665 = false, _mut55666 = false, _mut55667 = false, _mut55668 = false, _mut55669 = false, _mut55670 = false, _mut55671 = false, _mut55672 = false, _mut55673 = false, _mut55674 = false, _mut55675 = false, _mut55676 = false, _mut55677 = false, _mut55678 = false, _mut55679 = false, _mut55680 = false, _mut55681 = false, _mut55682 = false, _mut55683 = false, _mut55684 = false, _mut55685 = false, _mut55686 = false, _mut55687 = false, _mut55688 = false, _mut55689 = false, _mut55690 = false, _mut55691 = false, _mut55692 = false, _mut55693 = false, _mut55694 = false, _mut55695 = false, _mut55696 = false, _mut55697 = false, _mut55698 = false, _mut55699 = false, _mut55700 = false, _mut55701 = false, _mut55702 = false, _mut55703 = false, _mut55704 = false, _mut55705 = false, _mut55706 = false, _mut55707 = false, _mut55708 = false, _mut55709 = false, _mut55710 = false, _mut55711 = false, _mut55712 = false, _mut55713 = false, _mut55714 = false, _mut55715 = false, _mut55716 = false, _mut55717 = false, _mut55718 = false, _mut55719 = false, _mut55720 = false, _mut55721 = false, _mut55722 = false, _mut55723 = false, _mut55724 = false, _mut55725 = false, _mut55726 = false, _mut55727 = false, _mut55728 = false, _mut55729 = false, _mut55730 = false, _mut55731 = false, _mut55732 = false, _mut55733 = false, _mut55734 = false, _mut55735 = false, _mut55736 = false, _mut55737 = false, _mut55738 = false, _mut55739 = false, _mut55740 = false, _mut55741 = false, _mut55742 = false, _mut55743 = false, _mut55744 = false, _mut55745 = false, _mut55746 = false, _mut55747 = false, _mut55748 = false, _mut55749 = false, _mut55750 = false, _mut55751 = false, _mut55752 = false, _mut55753 = false, _mut55754 = false, _mut55755 = false, _mut55756 = false, _mut55757 = false, _mut55758 = false, _mut55759 = false, _mut55760 = false, _mut55761 = false, _mut55762 = false, _mut55763 = false, _mut55764 = false, _mut55765 = false, _mut55766 = false, _mut55767 = false, _mut55768 = false, _mut55769 = false, _mut55770 = false, _mut55771 = false, _mut55772 = false, _mut55773 = false, _mut55774 = false, _mut55775 = false, _mut55776 = false, _mut55777 = false, _mut55778 = false, _mut55779 = false, _mut55780 = false, _mut55781 = false, _mut55782 = false, _mut55783 = false, _mut55784 = false, _mut55785 = false, _mut55786 = false, _mut55787 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = -4670676796862967187L;

    /**
     * Number of observations.
     */
    private int n;

    /**
     * @param n Number of observations
     * @throws NotStrictlyPositiveException if {@code n <= 0}
     */
    public KolmogorovSmirnovDistribution(int n) throws NotStrictlyPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.KolmogorovSmirnovDistribution_86");
        if (ROR_less_equals(n, 0, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.KolmogorovSmirnovDistribution_86", _mut55531, _mut55532, _mut55533, _mut55534, _mut55535)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_NUMBER_OF_SAMPLES, n);
        }
        this.n = n;
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] with quick
     * decisions for extreme values given in [2] (see above). The result is not
     * exact as with
     * {@link KolmogorovSmirnovDistribution#cdfExact(double)} because
     * calculations are based on {@code double} rather than
     * {@link org.apache.commons.math3.fraction.BigFraction}.
     *
     * @param d statistic
     * @return the two-sided probability of {@code P(D_n < d)}
     * @throws MathArithmeticException if algorithm fails to convert {@code h}
     * to a {@link org.apache.commons.math3.fraction.BigFraction} in expressing
     * {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     * {@code 0 <= h < 1}.
     */
    public double cdf(double d) throws MathArithmeticException {
        return this.cdf(d, false);
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] with quick
     * decisions for extreme values given in [2] (see above). The result is
     * exact in the sense that BigFraction/BigReal is used everywhere at the
     * expense of very slow execution time. Almost never choose this in real
     * applications unless you are very sure; this is almost solely for
     * verification purposes. Normally, you would choose
     * {@link KolmogorovSmirnovDistribution#cdf(double)}
     *
     * @param d statistic
     * @return the two-sided probability of {@code P(D_n < d)}
     * @throws MathArithmeticException if algorithm fails to convert {@code h}
     * to a {@link org.apache.commons.math3.fraction.BigFraction} in expressing
     * {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     * {@code 0 <= h < 1}.
     */
    public double cdfExact(double d) throws MathArithmeticException {
        return this.cdf(d, true);
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] with quick
     * decisions for extreme values given in [2] (see above).
     *
     * @param d statistic
     * @param exact whether the probability should be calculated exact using
     * {@link org.apache.commons.math3.fraction.BigFraction} everywhere at the
     * expense of very slow execution time, or if {@code double} should be used
     * convenient places to gain speed. Almost never choose {@code true} in real
     * applications unless you are very sure; {@code true} is almost solely for
     * verification purposes.
     * @return the two-sided probability of {@code P(D_n < d)}
     * @throws MathArithmeticException if algorithm fails to convert {@code h}
     * to a {@link org.apache.commons.math3.fraction.BigFraction} in expressing
     * {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     * {@code 0 <= h < 1}.
     */
    public double cdf(double d, boolean exact) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151");
        final double ninv = AOR_divide(1, ((double) n), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55536, _mut55537, _mut55538, _mut55539);
        final double ninvhalf = AOR_multiply(0.5, ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55540, _mut55541, _mut55542, _mut55543);
        if (ROR_less_equals(d, ninvhalf, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55544, _mut55545, _mut55546, _mut55547, _mut55548)) {
            return 0;
        } else if ((_mut55559 ? (ROR_less(ninvhalf, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55549, _mut55550, _mut55551, _mut55552, _mut55553) || ROR_less_equals(d, ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55554, _mut55555, _mut55556, _mut55557, _mut55558)) : (ROR_less(ninvhalf, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55549, _mut55550, _mut55551, _mut55552, _mut55553) && ROR_less_equals(d, ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55554, _mut55555, _mut55556, _mut55557, _mut55558)))) {
            double res = 1;
            double f = AOR_minus(AOR_multiply(2, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55592, _mut55593, _mut55594, _mut55595), ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55596, _mut55597, _mut55598, _mut55599);
            // n! f^n = n*f * (n-1)*f * ... * 1*x
            for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55604, _mut55605, _mut55606, _mut55607, _mut55608); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151");
                res *= AOR_multiply(i, f, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55600, _mut55601, _mut55602, _mut55603);
            }
            return res;
        } else if ((_mut55574 ? (ROR_less_equals(AOR_minus(1, ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55560, _mut55561, _mut55562, _mut55563), d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55564, _mut55565, _mut55566, _mut55567, _mut55568) || ROR_less(d, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55569, _mut55570, _mut55571, _mut55572, _mut55573)) : (ROR_less_equals(AOR_minus(1, ninv, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55560, _mut55561, _mut55562, _mut55563), d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55564, _mut55565, _mut55566, _mut55567, _mut55568) && ROR_less(d, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55569, _mut55570, _mut55571, _mut55572, _mut55573)))) {
            return AOR_minus(1, AOR_multiply(2, FastMath.pow(AOR_minus(1, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55580, _mut55581, _mut55582, _mut55583), n), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55584, _mut55585, _mut55586, _mut55587), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55588, _mut55589, _mut55590, _mut55591);
        } else if (ROR_less_equals(1, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.cdf_151", _mut55575, _mut55576, _mut55577, _mut55578, _mut55579)) {
            return 1;
        }
        return exact ? exactK(d) : roundedK(d);
    }

    /**
     * Calculates the exact value of {@code P(D_n < d)} using method described
     * in [1] and {@link org.apache.commons.math3.fraction.BigFraction} (see
     * above).
     *
     * @param d statistic
     * @return the two-sided probability of {@code P(D_n < d)}
     * @throws MathArithmeticException if algorithm fails to convert {@code h}
     * to a {@link org.apache.commons.math3.fraction.BigFraction} in expressing
     * {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     * {@code 0 <= h < 1}.
     */
    private double exactK(double d) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196");
        final int k = (int) FastMath.ceil(AOR_multiply(n, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196", _mut55609, _mut55610, _mut55611, _mut55612));
        final FieldMatrix<BigFraction> H = this.createH(d);
        final FieldMatrix<BigFraction> Hpower = H.power(n);
        BigFraction pFrac = Hpower.getEntry(AOR_minus(k, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196", _mut55613, _mut55614, _mut55615, _mut55616), AOR_minus(k, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196", _mut55617, _mut55618, _mut55619, _mut55620));
        for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196", _mut55621, _mut55622, _mut55623, _mut55624, _mut55625); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.exactK_196");
            pFrac = pFrac.multiply(i).divide(n);
        }
        /*
         * BigFraction.doubleValue converts numerator to double and the
         * denominator to double and divides afterwards. That gives NaN quite
         * easy. This does not (scale is the number of digits):
         */
        return pFrac.bigDecimalValue(20, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Calculates {@code P(D_n < d)} using method described in [1] and doubles
     * (see above).
     *
     * @param d statistic
     * @return the two-sided probability of {@code P(D_n < d)}
     * @throws MathArithmeticException if algorithm fails to convert {@code h}
     * to a {@link org.apache.commons.math3.fraction.BigFraction} in expressing
     * {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     * {@code 0 <= h < 1}.
     */
    private double roundedK(double d) throws MathArithmeticException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228");
        final int k = (int) FastMath.ceil(AOR_multiply(n, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55626, _mut55627, _mut55628, _mut55629));
        final FieldMatrix<BigFraction> HBigFraction = this.createH(d);
        final int m = HBigFraction.getRowDimension();
        /*
         * Here the rounding part comes into play: use
         * RealMatrix instead of FieldMatrix<BigFraction>
         */
        final RealMatrix H = new Array2DRowRealMatrix(m, m);
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55635, _mut55636, _mut55637, _mut55638, _mut55639); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228");
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55630, _mut55631, _mut55632, _mut55633, _mut55634); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228");
                H.setEntry(i, j, HBigFraction.getEntry(i, j).doubleValue());
            }
        }
        final RealMatrix Hpower = H.power(n);
        double pFrac = Hpower.getEntry(AOR_minus(k, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55640, _mut55641, _mut55642, _mut55643), AOR_minus(k, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55644, _mut55645, _mut55646, _mut55647));
        for (int i = 1; ROR_less_equals(i, n, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55652, _mut55653, _mut55654, _mut55655, _mut55656); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228");
            pFrac *= AOR_divide((double) i, (double) n, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.roundedK_228", _mut55648, _mut55649, _mut55650, _mut55651);
        }
        return pFrac;
    }

    /**
     *  Creates {@code H} of size {@code m x m} as described in [1] (see above).
     *
     *  @param d statistic
     *  @return H matrix
     *  @throws NumberIsTooLargeException if fractional part is greater than 1
     *  @throws FractionConversionException if algorithm fails to convert
     *  {@code h} to a {@link org.apache.commons.math3.fraction.BigFraction} in
     *  expressing {@code d} as {@code (k - h) / m} for integer {@code k, m} and
     *  {@code 0 <= h < 1}.
     */
    private FieldMatrix<BigFraction> createH(double d) throws NumberIsTooLargeException, FractionConversionException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
        int k = (int) FastMath.ceil(AOR_multiply(n, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55657, _mut55658, _mut55659, _mut55660));
        int m = AOR_minus(AOR_multiply(2, k, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55661, _mut55662, _mut55663, _mut55664), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55665, _mut55666, _mut55667, _mut55668);
        double hDouble = AOR_minus(k, AOR_multiply(n, d, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55669, _mut55670, _mut55671, _mut55672), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55673, _mut55674, _mut55675, _mut55676);
        if (ROR_greater_equals(hDouble, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55677, _mut55678, _mut55679, _mut55680, _mut55681)) {
            throw new NumberIsTooLargeException(hDouble, 1.0, false);
        }
        BigFraction h = null;
        try {
            h = new BigFraction(hDouble, 1.0e-20, 10000);
        } catch (FractionConversionException e1) {
            try {
                h = new BigFraction(hDouble, 1.0e-10, 10000);
            } catch (FractionConversionException e2) {
                h = new BigFraction(hDouble, 1.0e-5, 10000);
            }
        }
        final BigFraction[][] Hdata = new BigFraction[m][m];
        /*
         * Start by filling everything with either 0 or 1.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55700, _mut55701, _mut55702, _mut55703, _mut55704); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
            for (int j = 0; ROR_less(j, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55695, _mut55696, _mut55697, _mut55698, _mut55699); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
                if (ROR_less(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55682, _mut55683, _mut55684, _mut55685), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55686, _mut55687, _mut55688, _mut55689), 0, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55690, _mut55691, _mut55692, _mut55693, _mut55694)) {
                    Hdata[i][j] = BigFraction.ZERO;
                } else {
                    Hdata[i][j] = BigFraction.ONE;
                }
            }
        }
        /*
         * Setting up power-array to avoid calculating the same value twice:
         * hPowers[0] = h^1 ... hPowers[m-1] = h^m
         */
        final BigFraction[] hPowers = new BigFraction[m];
        hPowers[0] = h;
        for (int i = 1; ROR_less(i, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55709, _mut55710, _mut55711, _mut55712, _mut55713); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
            hPowers[i] = h.multiply(hPowers[AOR_minus(i, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55705, _mut55706, _mut55707, _mut55708)]);
        }
        /*
         * First column and last row has special values (each other reversed).
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55730, _mut55731, _mut55732, _mut55733, _mut55734); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
            Hdata[i][0] = Hdata[i][0].subtract(hPowers[i]);
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55714, _mut55715, _mut55716, _mut55717)][i] = Hdata[AOR_minus(m, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55726, _mut55727, _mut55728, _mut55729)][i].subtract(hPowers[AOR_minus(AOR_minus(m, i, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55718, _mut55719, _mut55720, _mut55721), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55722, _mut55723, _mut55724, _mut55725)]);
        }
        /*
         * [1] states: "For 1/2 < h < 1 the bottom left element of the matrix
         * should be (1 - 2*h^m + (2h - 1)^m )/m!" Since 0 <= h < 1, then if h >
         * 1/2 is sufficient to check:
         */
        if (ROR_equals(h.compareTo(BigFraction.ONE_HALF), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55735, _mut55736, _mut55737, _mut55738, _mut55739)) {
            Hdata[AOR_minus(m, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55740, _mut55741, _mut55742, _mut55743)][0] = Hdata[AOR_minus(m, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55744, _mut55745, _mut55746, _mut55747)][0].add(h.multiply(2).subtract(1).pow(m));
        }
        /*
         * Aside from the first column and last row, the (i, j)-th element is
         * 1/(i - j + 1)! if i - j + 1 >= 0, else 0. 1's and 0's are already
         * put, so only division with (i - j + 1)! is needed in the elements
         * that have 1's. There is no need to calculate (i - j + 1)! and then
         * divide - small steps avoid overflows.
         *
         * Note that i - j + 1 > 0 <=> i + 1 > j instead of j'ing all the way to
         * m. Also note that it is started at g = 2 because dividing by 1 isn't
         * really necessary.
         */
        for (int i = 0; ROR_less(i, m, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55783, _mut55784, _mut55785, _mut55786, _mut55787); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
            for (int j = 0; ROR_less(j, AOR_plus(i, 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55774, _mut55775, _mut55776, _mut55777), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55778, _mut55779, _mut55780, _mut55781, _mut55782); ++j) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
                if (ROR_greater(AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55748, _mut55749, _mut55750, _mut55751), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55752, _mut55753, _mut55754, _mut55755), 0, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55756, _mut55757, _mut55758, _mut55759, _mut55760)) {
                    for (int g = 2; ROR_less_equals(g, AOR_plus(AOR_minus(i, j, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55761, _mut55762, _mut55763, _mut55764), 1, "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55765, _mut55766, _mut55767, _mut55768), "org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268", _mut55769, _mut55770, _mut55771, _mut55772, _mut55773); ++g) {
                        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.KolmogorovSmirnovDistribution.createH_268");
                        Hdata[i][j] = Hdata[i][j].divide(g);
                    }
                }
            }
        }
        return new Array2DRowFieldMatrix<BigFraction>(BigFractionField.getInstance(), Hdata);
    }
}
