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
 * Implements the Fast Cosine Transform for transformation of one-dimensional
 * real data sets. For reference, see James S. Walker, <em>Fast Fourier
 * Transforms</em>, chapter 3 (ISBN 0849371635).
 * <p>
 * There are several variants of the discrete cosine transform. The present
 * implementation corresponds to DCT-I, with various normalization conventions,
 * which are specified by the parameter {@link DctNormalization}.
 * <p>
 * DCT-I is equivalent to DFT of an <em>even extension</em> of the data series.
 * More precisely, if x<sub>0</sub>, &hellip;, x<sub>N-1</sub> is the data set
 * to be cosine transformed, the extended data set
 * x<sub>0</sub><sup>&#35;</sup>, &hellip;, x<sub>2N-3</sub><sup>&#35;</sup>
 * is defined as follows
 * <ul>
 * <li>x<sub>k</sub><sup>&#35;</sup> = x<sub>k</sub> if 0 &le; k &lt; N,</li>
 * <li>x<sub>k</sub><sup>&#35;</sup> = x<sub>2N-2-k</sub>
 * if N &le; k &lt; 2N - 2.</li>
 * </ul>
 * <p>
 * Then, the standard DCT-I y<sub>0</sub>, &hellip;, y<sub>N-1</sub> of the real
 * data set x<sub>0</sub>, &hellip;, x<sub>N-1</sub> is equal to <em>half</em>
 * of the N first elements of the DFT of the extended data set
 * x<sub>0</sub><sup>&#35;</sup>, &hellip;, x<sub>2N-3</sub><sup>&#35;</sup>
 * <br/>
 * y<sub>n</sub> = (1 / 2) &sum;<sub>k=0</sub><sup>2N-3</sup>
 * x<sub>k</sub><sup>&#35;</sup> exp[-2&pi;i nk / (2N - 2)]
 * &nbsp;&nbsp;&nbsp;&nbsp;k = 0, &hellip;, N-1.
 * <p>
 * The present implementation of the discrete cosine transform as a fast cosine
 * transform requires the length of the data set to be a power of two plus one
 * (N&nbsp;=&nbsp;2<sup>n</sup>&nbsp;+&nbsp;1). Besides, it implicitly assumes
 * that the sampled function is even.
 *
 * @since 1.2
 */
public class FastCosineTransformer implements RealTransformer, Serializable {

    @Conditional
    public static boolean _mut1755 = false, _mut1756 = false, _mut1757 = false, _mut1758 = false, _mut1759 = false, _mut1760 = false, _mut1761 = false, _mut1762 = false, _mut1763 = false, _mut1764 = false, _mut1765 = false, _mut1766 = false, _mut1767 = false, _mut1768 = false, _mut1769 = false, _mut1770 = false, _mut1771 = false, _mut1772 = false, _mut1773 = false, _mut1774 = false, _mut1775 = false, _mut1776 = false, _mut1777 = false, _mut1778 = false, _mut1779 = false, _mut1780 = false, _mut1781 = false, _mut1782 = false, _mut1783 = false, _mut1784 = false, _mut1785 = false, _mut1786 = false, _mut1787 = false, _mut1788 = false, _mut1789 = false, _mut1790 = false, _mut1791 = false, _mut1792 = false, _mut1793 = false, _mut1794 = false, _mut1795 = false, _mut1796 = false, _mut1797 = false, _mut1798 = false, _mut1799 = false, _mut1800 = false, _mut1801 = false, _mut1802 = false, _mut1803 = false, _mut1804 = false, _mut1805 = false, _mut1806 = false, _mut1807 = false, _mut1808 = false, _mut1809 = false, _mut1810 = false, _mut1811 = false, _mut1812 = false, _mut1813 = false, _mut1814 = false, _mut1815 = false, _mut1816 = false, _mut1817 = false, _mut1818 = false, _mut1819 = false, _mut1820 = false, _mut1821 = false, _mut1822 = false, _mut1823 = false, _mut1824 = false, _mut1825 = false, _mut1826 = false, _mut1827 = false, _mut1828 = false, _mut1829 = false, _mut1830 = false, _mut1831 = false, _mut1832 = false, _mut1833 = false, _mut1834 = false, _mut1835 = false, _mut1836 = false, _mut1837 = false, _mut1838 = false, _mut1839 = false, _mut1840 = false, _mut1841 = false, _mut1842 = false, _mut1843 = false, _mut1844 = false, _mut1845 = false, _mut1846 = false, _mut1847 = false, _mut1848 = false, _mut1849 = false, _mut1850 = false, _mut1851 = false, _mut1852 = false, _mut1853 = false, _mut1854 = false, _mut1855 = false, _mut1856 = false, _mut1857 = false, _mut1858 = false, _mut1859 = false, _mut1860 = false, _mut1861 = false, _mut1862 = false, _mut1863 = false, _mut1864 = false, _mut1865 = false, _mut1866 = false, _mut1867 = false, _mut1868 = false, _mut1869 = false, _mut1870 = false, _mut1871 = false, _mut1872 = false, _mut1873 = false, _mut1874 = false, _mut1875 = false, _mut1876 = false, _mut1877 = false, _mut1878 = false, _mut1879 = false, _mut1880 = false, _mut1881 = false, _mut1882 = false, _mut1883 = false, _mut1884 = false, _mut1885 = false, _mut1886 = false, _mut1887 = false, _mut1888 = false, _mut1889 = false, _mut1890 = false, _mut1891 = false, _mut1892 = false, _mut1893 = false, _mut1894 = false, _mut1895 = false, _mut1896 = false, _mut1897 = false, _mut1898 = false, _mut1899 = false, _mut1900 = false, _mut1901 = false, _mut1902 = false, _mut1903 = false, _mut1904 = false, _mut1905 = false, _mut1906 = false, _mut1907 = false, _mut1908 = false, _mut1909 = false;

    /**
     * Serializable version identifier.
     */
    static final long serialVersionUID = 20120212L;

    /**
     * The type of DCT to be performed.
     */
    private final DctNormalization normalization;

    /**
     * Creates a new instance of this class, with various normalization
     * conventions.
     *
     * @param normalization the type of normalization to be applied to the
     * transformed data
     */
    public FastCosineTransformer(final DctNormalization normalization) {
        this.normalization = normalization;
    }

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalArgumentException if the length of the data array is
     * not a power of two plus one
     */
    public double[] transform(final double[] f, final TransformType type) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastCosineTransformer.transform_90");
        if (type == TransformType.FORWARD) {
            if (normalization == DctNormalization.ORTHOGONAL_DCT_I) {
                final double s = FastMath.sqrt(AOR_divide(2.0, (AOR_minus(f.length, 1, "org.apache.commons.math3.transform.FastCosineTransformer.transform_90", _mut1755, _mut1756, _mut1757, _mut1758)), "org.apache.commons.math3.transform.FastCosineTransformer.transform_90", _mut1759, _mut1760, _mut1761, _mut1762));
                return TransformUtils.scaleArray(fct(f), s);
            }
            return fct(f);
        }
        final double s2 = AOR_divide(2.0, (AOR_minus(f.length, 1, "org.apache.commons.math3.transform.FastCosineTransformer.transform_90", _mut1763, _mut1764, _mut1765, _mut1766)), "org.apache.commons.math3.transform.FastCosineTransformer.transform_90", _mut1767, _mut1768, _mut1769, _mut1770);
        final double s1;
        if (normalization == DctNormalization.ORTHOGONAL_DCT_I) {
            s1 = FastMath.sqrt(s2);
        } else {
            s1 = s2;
        }
        return TransformUtils.scaleArray(fct(f), s1);
    }

    /**
     * {@inheritDoc}
     *
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     * if the lower bound is greater than, or equal to the upper bound
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     * if the number of sample points is negative
     * @throws MathIllegalArgumentException if the number of sample points is
     * not a power of two plus one
     */
    public double[] transform(final UnivariateFunction f, final double min, final double max, final int n, final TransformType type) throws MathIllegalArgumentException {
        final double[] data = FunctionUtils.sample(f, min, max, n);
        return transform(data, type);
    }

    /**
     * Perform the FCT algorithm (including inverse).
     *
     * @param f the real data array to be transformed
     * @return the real transformed array
     * @throws MathIllegalArgumentException if the length of the data array is
     * not a power of two plus one
     */
    protected double[] fct(double[] f) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastCosineTransformer.fct_135");
        final double[] transformed = new double[f.length];
        final int n = AOR_minus(f.length, 1, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1771, _mut1772, _mut1773, _mut1774);
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_PLUS_ONE, Integer.valueOf(f.length));
        }
        if (ROR_equals(n, 1, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1775, _mut1776, _mut1777, _mut1778, _mut1779)) {
            // trivial case
            transformed[0] = AOR_multiply(0.5, (AOR_plus(f[0], f[1], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1780, _mut1781, _mut1782, _mut1783)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1784, _mut1785, _mut1786, _mut1787);
            transformed[1] = AOR_multiply(0.5, (AOR_minus(f[0], f[1], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1788, _mut1789, _mut1790, _mut1791)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1792, _mut1793, _mut1794, _mut1795);
            return transformed;
        }
        // construct a new array and perform FFT on it
        final double[] x = new double[n];
        x[0] = AOR_multiply(0.5, (AOR_plus(f[0], f[n], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1796, _mut1797, _mut1798, _mut1799)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1800, _mut1801, _mut1802, _mut1803);
        x[n >> 1] = f[n >> 1];
        // temporary variable for transformed[1]
        double t1 = AOR_multiply(0.5, (AOR_minus(f[0], f[n], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1804, _mut1805, _mut1806, _mut1807)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1808, _mut1809, _mut1810, _mut1811);
        for (int i = 1; ROR_less(i, (n >> 1), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1876, _mut1877, _mut1878, _mut1879, _mut1880); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastCosineTransformer.fct_135");
            final double a = AOR_multiply(0.5, (AOR_plus(f[i], f[AOR_minus(n, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1812, _mut1813, _mut1814, _mut1815)], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1816, _mut1817, _mut1818, _mut1819)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1820, _mut1821, _mut1822, _mut1823);
            final double b = AOR_multiply(FastMath.sin(AOR_divide(AOR_multiply(i, FastMath.PI, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1824, _mut1825, _mut1826, _mut1827), n, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1828, _mut1829, _mut1830, _mut1831)), (AOR_minus(f[i], f[AOR_minus(n, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1832, _mut1833, _mut1834, _mut1835)], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1836, _mut1837, _mut1838, _mut1839)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1840, _mut1841, _mut1842, _mut1843);
            final double c = AOR_multiply(FastMath.cos(AOR_divide(AOR_multiply(i, FastMath.PI, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1844, _mut1845, _mut1846, _mut1847), n, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1848, _mut1849, _mut1850, _mut1851)), (AOR_minus(f[i], f[AOR_minus(n, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1852, _mut1853, _mut1854, _mut1855)], "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1856, _mut1857, _mut1858, _mut1859)), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1860, _mut1861, _mut1862, _mut1863);
            x[i] = AOR_minus(a, b, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1864, _mut1865, _mut1866, _mut1867);
            x[AOR_minus(n, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1868, _mut1869, _mut1870, _mut1871)] = AOR_plus(a, b, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1872, _mut1873, _mut1874, _mut1875);
            t1 += c;
        }
        FastFourierTransformer transformer;
        transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex[] y = transformer.transform(x, TransformType.FORWARD);
        // reconstruct the FCT result for the original array
        transformed[0] = y[0].getReal();
        transformed[1] = t1;
        for (int i = 1; ROR_less(i, (n >> 1), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1905, _mut1906, _mut1907, _mut1908, _mut1909); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastCosineTransformer.fct_135");
            transformed[AOR_multiply(2, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1881, _mut1882, _mut1883, _mut1884)] = y[i].getReal();
            transformed[AOR_plus(AOR_multiply(2, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1885, _mut1886, _mut1887, _mut1888), 1, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1889, _mut1890, _mut1891, _mut1892)] = AOR_minus(transformed[AOR_minus(AOR_multiply(2, i, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1893, _mut1894, _mut1895, _mut1896), 1, "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1897, _mut1898, _mut1899, _mut1900)], y[i].getImaginary(), "org.apache.commons.math3.transform.FastCosineTransformer.fct_135", _mut1901, _mut1902, _mut1903, _mut1904);
        }
        transformed[n] = y[n >> 1].getReal();
        return transformed;
    }
}
