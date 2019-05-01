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
package org.apache.commons.math3.transform;

import java.io.Serializable;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the Fast Sine Transform for transformation of one-dimensional real
 * data sets. For reference, see James S. Walker, <em>Fast Fourier
 * Transforms</em>, chapter 3 (ISBN 0849371635).
 * <p>
 * There are several variants of the discrete sine transform. The present
 * implementation corresponds to DST-I, with various normalization conventions,
 * which are specified by the parameter {@link DstNormalization}.
 * <strong>It should be noted that regardless to the convention, the first
 * element of the dataset to be transformed must be zero.</strong>
 * <p>
 * DST-I is equivalent to DFT of an <em>odd extension</em> of the data series.
 * More precisely, if x<sub>0</sub>, &hellip;, x<sub>N-1</sub> is the data set
 * to be sine transformed, the extended data set x<sub>0</sub><sup>&#35;</sup>,
 * &hellip;, x<sub>2N-1</sub><sup>&#35;</sup> is defined as follows
 * <ul>
 * <li>x<sub>0</sub><sup>&#35;</sup> = x<sub>0</sub> = 0,</li>
 * <li>x<sub>k</sub><sup>&#35;</sup> = x<sub>k</sub> if 1 &le; k &lt; N,</li>
 * <li>x<sub>N</sub><sup>&#35;</sup> = 0,</li>
 * <li>x<sub>k</sub><sup>&#35;</sup> = -x<sub>2N-k</sub> if N + 1 &le; k &lt;
 * 2N.</li>
 * </ul>
 * <p>
 * Then, the standard DST-I y<sub>0</sub>, &hellip;, y<sub>N-1</sub> of the real
 * data set x<sub>0</sub>, &hellip;, x<sub>N-1</sub> is equal to <em>half</em>
 * of i (the pure imaginary number) times the N first elements of the DFT of the
 * extended data set x<sub>0</sub><sup>&#35;</sup>, &hellip;,
 * x<sub>2N-1</sub><sup>&#35;</sup> <br />
 * y<sub>n</sub> = (i / 2) &sum;<sub>k=0</sub><sup>2N-1</sup>
 * x<sub>k</sub><sup>&#35;</sup> exp[-2&pi;i nk / (2N)]
 * &nbsp;&nbsp;&nbsp;&nbsp;k = 0, &hellip;, N-1.
 * <p>
 * The present implementation of the discrete sine transform as a fast sine
 * transform requires the length of the data to be a power of two. Besides,
 * it implicitly assumes that the sampled function is odd. In particular, the
 * first element of the data set must be 0, which is enforced in
 * {@link #transform(UnivariateFunction, double, double, int, TransformType)},
 * after sampling.
 *
 * @since 1.2
 */
public class FastSineTransformer implements RealTransformer, Serializable {

    @Conditional
    public static boolean _mut1608 = false, _mut1609 = false, _mut1610 = false, _mut1611 = false, _mut1612 = false, _mut1613 = false, _mut1614 = false, _mut1615 = false, _mut1616 = false, _mut1617 = false, _mut1618 = false, _mut1619 = false, _mut1620 = false, _mut1621 = false, _mut1622 = false, _mut1623 = false, _mut1624 = false, _mut1625 = false, _mut1626 = false, _mut1627 = false, _mut1628 = false, _mut1629 = false, _mut1630 = false, _mut1631 = false, _mut1632 = false, _mut1633 = false, _mut1634 = false, _mut1635 = false, _mut1636 = false, _mut1637 = false, _mut1638 = false, _mut1639 = false, _mut1640 = false, _mut1641 = false, _mut1642 = false, _mut1643 = false, _mut1644 = false, _mut1645 = false, _mut1646 = false, _mut1647 = false, _mut1648 = false, _mut1649 = false, _mut1650 = false, _mut1651 = false, _mut1652 = false, _mut1653 = false, _mut1654 = false, _mut1655 = false, _mut1656 = false, _mut1657 = false, _mut1658 = false, _mut1659 = false, _mut1660 = false, _mut1661 = false, _mut1662 = false, _mut1663 = false, _mut1664 = false, _mut1665 = false, _mut1666 = false, _mut1667 = false, _mut1668 = false, _mut1669 = false, _mut1670 = false, _mut1671 = false, _mut1672 = false, _mut1673 = false, _mut1674 = false, _mut1675 = false, _mut1676 = false, _mut1677 = false, _mut1678 = false, _mut1679 = false, _mut1680 = false, _mut1681 = false, _mut1682 = false, _mut1683 = false, _mut1684 = false, _mut1685 = false, _mut1686 = false, _mut1687 = false, _mut1688 = false, _mut1689 = false, _mut1690 = false, _mut1691 = false, _mut1692 = false, _mut1693 = false, _mut1694 = false, _mut1695 = false, _mut1696 = false, _mut1697 = false, _mut1698 = false, _mut1699 = false, _mut1700 = false, _mut1701 = false, _mut1702 = false, _mut1703 = false, _mut1704 = false, _mut1705 = false, _mut1706 = false, _mut1707 = false, _mut1708 = false, _mut1709 = false, _mut1710 = false, _mut1711 = false;

    /**
     * Serializable version identifier.
     */
    static final long serialVersionUID = 20120211L;

    /**
     * The type of DST to be performed.
     */
    private final DstNormalization normalization;

    /**
     * Creates a new instance of this class, with various normalization conventions.
     *
     * @param normalization the type of normalization to be applied to the transformed data
     */
    public FastSineTransformer(final DstNormalization normalization) {
        this.normalization = normalization;
    }

    /**
     * {@inheritDoc}
     *
     * The first element of the specified data set is required to be {@code 0}.
     *
     * @throws MathIllegalArgumentException if the length of the data array is
     *   not a power of two, or the first element of the data array is not zero
     */
    public double[] transform(final double[] f, final TransformType type) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastSineTransformer.transform_95");
        if (normalization == DstNormalization.ORTHOGONAL_DST_I) {
            final double s = FastMath.sqrt(AOR_divide(2.0, f.length, "org.apache.commons.math3.transform.FastSineTransformer.transform_95", _mut1608, _mut1609, _mut1610, _mut1611));
            return TransformUtils.scaleArray(fst(f), s);
        }
        if (type == TransformType.FORWARD) {
            return fst(f);
        }
        final double s = AOR_divide(2.0, f.length, "org.apache.commons.math3.transform.FastSineTransformer.transform_95", _mut1612, _mut1613, _mut1614, _mut1615);
        return TransformUtils.scaleArray(fst(f), s);
    }

    /**
     * {@inheritDoc}
     *
     * This implementation enforces {@code f(x) = 0.0} at {@code x = 0.0}.
     *
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     *   if the lower bound is greater than, or equal to the upper bound
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     *   if the number of sample points is negative
     * @throws MathIllegalArgumentException if the number of sample points is not a power of two
     */
    public double[] transform(final UnivariateFunction f, final double min, final double max, final int n, final TransformType type) {
        final double[] data = FunctionUtils.sample(f, min, max, n);
        data[0] = 0.0;
        return transform(data, type);
    }

    /**
     * Perform the FST algorithm (including inverse). The first element of the
     * data set is required to be {@code 0}.
     *
     * @param f the real data array to be transformed
     * @return the real transformed array
     * @throws MathIllegalArgumentException if the length of the data array is
     *   not a power of two, or the first element of the data array is not zero
     */
    protected double[] fst(double[] f) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastSineTransformer.fst_136");
        final double[] transformed = new double[f.length];
        if (!ArithmeticUtils.isPowerOfTwo(f.length)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, Integer.valueOf(f.length));
        }
        if (ROR_not_equals(f[0], 0.0, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1616, _mut1617, _mut1618, _mut1619, _mut1620)) {
            throw new MathIllegalArgumentException(LocalizedFormats.FIRST_ELEMENT_NOT_ZERO, Double.valueOf(f[0]));
        }
        final int n = f.length;
        if (ROR_equals(n, 1, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1621, _mut1622, _mut1623, _mut1624, _mut1625)) {
            // trivial case
            transformed[0] = 0.0;
            return transformed;
        }
        // construct a new array and perform FFT on it
        final double[] x = new double[n];
        x[0] = 0.0;
        x[n >> 1] = AOR_multiply(2.0, f[n >> 1], "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1626, _mut1627, _mut1628, _mut1629);
        for (int i = 1; ROR_less(i, (n >> 1), "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1674, _mut1675, _mut1676, _mut1677, _mut1678); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastSineTransformer.fst_136");
            final double a = AOR_multiply(FastMath.sin(AOR_divide(AOR_multiply(i, FastMath.PI, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1630, _mut1631, _mut1632, _mut1633), n, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1634, _mut1635, _mut1636, _mut1637)), (AOR_plus(f[i], f[AOR_minus(n, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1638, _mut1639, _mut1640, _mut1641)], "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1642, _mut1643, _mut1644, _mut1645)), "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1646, _mut1647, _mut1648, _mut1649);
            final double b = AOR_multiply(0.5, (AOR_minus(f[i], f[AOR_minus(n, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1650, _mut1651, _mut1652, _mut1653)], "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1654, _mut1655, _mut1656, _mut1657)), "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1658, _mut1659, _mut1660, _mut1661);
            x[i] = AOR_plus(a, b, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1662, _mut1663, _mut1664, _mut1665);
            x[AOR_minus(n, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1666, _mut1667, _mut1668, _mut1669)] = AOR_minus(a, b, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1670, _mut1671, _mut1672, _mut1673);
        }
        FastFourierTransformer transformer;
        transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] y = transformer.transform(x, TransformType.FORWARD);
        // reconstruct the FST result for the original array
        transformed[0] = 0.0;
        transformed[1] = AOR_multiply(0.5, y[0].getReal(), "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1679, _mut1680, _mut1681, _mut1682);
        for (int i = 1; ROR_less(i, (n >> 1), "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1707, _mut1708, _mut1709, _mut1710, _mut1711); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastSineTransformer.fst_136");
            transformed[AOR_multiply(2, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1683, _mut1684, _mut1685, _mut1686)] = -y[i].getImaginary();
            transformed[AOR_plus(AOR_multiply(2, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1687, _mut1688, _mut1689, _mut1690), 1, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1691, _mut1692, _mut1693, _mut1694)] = AOR_plus(y[i].getReal(), transformed[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1695, _mut1696, _mut1697, _mut1698), 1, "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1699, _mut1700, _mut1701, _mut1702)], "org.apache.commons.math3.transform.FastSineTransformer.fst_136", _mut1703, _mut1704, _mut1705, _mut1706);
        }
        return transformed;
    }
}
