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
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.ArithmeticUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements the <a href="http://www.archive.chipcenter.com/dsp/DSP000517F1.html">Fast Hadamard Transform</a> (FHT).
 * Transformation of an input vector x to the output vector y.
 * <p>
 * In addition to transformation of real vectors, the Hadamard transform can
 * transform integer vectors into integer vectors. However, this integer transform
 * cannot be inverted directly. Due to a scaling factor it may lead to rational results.
 * As an example, the inverse transform of integer vector (0, 1, 0, 1) is rational
 * vector (1/2, -1/2, 0, 0).
 *
 * @since 2.0
 */
public class FastHadamardTransformer implements RealTransformer, Serializable {

    @Conditional
    public static boolean _mut1910 = false, _mut1911 = false, _mut1912 = false, _mut1913 = false, _mut1914 = false, _mut1915 = false, _mut1916 = false, _mut1917 = false, _mut1918 = false, _mut1919 = false, _mut1920 = false, _mut1921 = false, _mut1922 = false, _mut1923 = false, _mut1924 = false, _mut1925 = false, _mut1926 = false, _mut1927 = false, _mut1928 = false, _mut1929 = false, _mut1930 = false, _mut1931 = false, _mut1932 = false, _mut1933 = false, _mut1934 = false, _mut1935 = false, _mut1936 = false, _mut1937 = false, _mut1938 = false, _mut1939 = false, _mut1940 = false, _mut1941 = false, _mut1942 = false, _mut1943 = false, _mut1944 = false, _mut1945 = false, _mut1946 = false, _mut1947 = false, _mut1948 = false, _mut1949 = false, _mut1950 = false, _mut1951 = false, _mut1952 = false, _mut1953 = false, _mut1954 = false, _mut1955 = false, _mut1956 = false, _mut1957 = false, _mut1958 = false, _mut1959 = false, _mut1960 = false, _mut1961 = false, _mut1962 = false, _mut1963 = false, _mut1964 = false, _mut1965 = false, _mut1966 = false, _mut1967 = false, _mut1968 = false, _mut1969 = false, _mut1970 = false, _mut1971 = false, _mut1972 = false, _mut1973 = false, _mut1974 = false, _mut1975 = false, _mut1976 = false, _mut1977 = false, _mut1978 = false, _mut1979 = false, _mut1980 = false, _mut1981 = false, _mut1982 = false, _mut1983 = false, _mut1984 = false, _mut1985 = false, _mut1986 = false, _mut1987 = false, _mut1988 = false, _mut1989 = false, _mut1990 = false, _mut1991 = false, _mut1992 = false, _mut1993 = false, _mut1994 = false, _mut1995 = false, _mut1996 = false, _mut1997 = false, _mut1998 = false, _mut1999 = false, _mut2000 = false, _mut2001 = false, _mut2002 = false, _mut2003 = false, _mut2004 = false, _mut2005 = false, _mut2006 = false, _mut2007 = false, _mut2008 = false, _mut2009 = false, _mut2010 = false, _mut2011 = false, _mut2012 = false, _mut2013 = false, _mut2014 = false, _mut2015 = false;

    /**
     * Serializable version identifier.
     */
    static final long serialVersionUID = 20120211L;

    /**
     * {@inheritDoc}
     *
     * @throws MathIllegalArgumentException if the length of the data array is
     * not a power of two
     */
    public double[] transform(final double[] f, final TransformType type) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.transform_50");
        if (type == TransformType.FORWARD) {
            return fht(f);
        }
        return TransformUtils.scaleArray(fht(f), AOR_divide(1.0, f.length, "org.apache.commons.math3.transform.FastHadamardTransformer.transform_50", _mut1910, _mut1911, _mut1912, _mut1913));
    }

    /**
     * {@inheritDoc}
     *
     * @throws org.apache.commons.math3.exception.NonMonotonicSequenceException
     *   if the lower bound is greater than, or equal to the upper bound
     * @throws org.apache.commons.math3.exception.NotStrictlyPositiveException
     *   if the number of sample points is negative
     * @throws MathIllegalArgumentException if the number of sample points is not a power of two
     */
    public double[] transform(final UnivariateFunction f, final double min, final double max, final int n, final TransformType type) {
        return transform(FunctionUtils.sample(f, min, max, n), type);
    }

    /**
     * Returns the forward transform of the specified integer data set.The
     * integer transform cannot be inverted directly, due to a scaling factor
     * which may lead to double results.
     *
     * @param f the integer data array to be transformed (signal)
     * @return the integer transformed array (spectrum)
     * @throws MathIllegalArgumentException if the length of the data array is not a power of two
     */
    public int[] transform(final int[] f) {
        return fht(f);
    }

    /**
     * The FHT (Fast Hadamard Transformation) which uses only subtraction and
     * addition. Requires {@code N * log2(N) = n * 2^n} additions.
     *
     * <h3>Short Table of manual calculation for N=8</h3>
     * <ol>
     * <li><b>x</b> is the input vector to be transformed,</li>
     * <li><b>y</b> is the output vector (Fast Hadamard transform of <b>x</b>),</li>
     * <li>a and b are helper rows.</li>
     * </ol>
     * <table align="center" border="1" cellpadding="3">
     * <tbody align="center">
     * <tr>
     *     <th>x</th>
     *     <th>a</th>
     *     <th>b</th>
     *     <th>y</th>
     * </tr>
     * <tr>
     *     <th>x<sub>0</sub></th>
     *     <td>a<sub>0</sub> = x<sub>0</sub> + x<sub>1</sub></td>
     *     <td>b<sub>0</sub> = a<sub>0</sub> + a<sub>1</sub></td>
     *     <td>y<sub>0</sub> = b<sub>0</sub >+ b<sub>1</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>1</sub></th>
     *     <td>a<sub>1</sub> = x<sub>2</sub> + x<sub>3</sub></td>
     *     <td>b<sub>0</sub> = a<sub>2</sub> + a<sub>3</sub></td>
     *     <td>y<sub>0</sub> = b<sub>2</sub> + b<sub>3</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>2</sub></th>
     *     <td>a<sub>2</sub> = x<sub>4</sub> + x<sub>5</sub></td>
     *     <td>b<sub>0</sub> = a<sub>4</sub> + a<sub>5</sub></td>
     *     <td>y<sub>0</sub> = b<sub>4</sub> + b<sub>5</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>3</sub></th>
     *     <td>a<sub>3</sub> = x<sub>6</sub> + x<sub>7</sub></td>
     *     <td>b<sub>0</sub> = a<sub>6</sub> + a<sub>7</sub></td>
     *     <td>y<sub>0</sub> = b<sub>6</sub> + b<sub>7</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>4</sub></th>
     *     <td>a<sub>0</sub> = x<sub>0</sub> - x<sub>1</sub></td>
     *     <td>b<sub>0</sub> = a<sub>0</sub> - a<sub>1</sub></td>
     *     <td>y<sub>0</sub> = b<sub>0</sub> - b<sub>1</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>5</sub></th>
     *     <td>a<sub>1</sub> = x<sub>2</sub> - x<sub>3</sub></td>
     *     <td>b<sub>0</sub> = a<sub>2</sub> - a<sub>3</sub></td>
     *     <td>y<sub>0</sub> = b<sub>2</sub> - b<sub>3</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>6</sub></th>
     *     <td>a<sub>2</sub> = x<sub>4</sub> - x<sub>5</sub></td>
     *     <td>b<sub>0</sub> = a<sub>4</sub> - a<sub>5</sub></td>
     *     <td>y<sub>0</sub> = b<sub>4</sub> - b<sub>5</sub></td>
     * </tr>
     * <tr>
     *     <th>x<sub>7</sub></th>
     *     <td>a<sub>3</sub> = x<sub>6</sub> - x<sub>7</sub></td>
     *     <td>b<sub>0</sub> = a<sub>6</sub> - a<sub>7</sub></td>
     *     <td>y<sub>0</sub> = b<sub>6</sub> - b<sub>7</sub></td>
     * </tr>
     * </tbody>
     * </table>
     *
     * <h3>How it works</h3>
     * <ol>
     * <li>Construct a matrix with {@code N} rows and {@code n + 1} columns,
     * {@code hadm[n+1][N]}.<br/>
     * <em>(If I use [x][y] it always means [row-offset][column-offset] of a
     * Matrix with n rows and m columns. Its entries go from M[0][0]
     * to M[n][N])</em></li>
     * <li>Place the input vector {@code x[N]} in the first column of the
     * matrix {@code hadm}.</li>
     * <li>The entries of the submatrix {@code D_top} are calculated as follows
     *     <ul>
     *         <li>{@code D_top} goes from entry {@code [0][1]} to
     *         {@code [N / 2 - 1][n + 1]},</li>
     *         <li>the columns of {@code D_top} are the pairwise mutually
     *         exclusive sums of the previous column.</li>
     *     </ul>
     * </li>
     * <li>The entries of the submatrix {@code D_bottom} are calculated as
     * follows
     *     <ul>
     *         <li>{@code D_bottom} goes from entry {@code [N / 2][1]} to
     *         {@code [N][n + 1]},</li>
     *         <li>the columns of {@code D_bottom} are the pairwise differences
     *         of the previous column.</li>
     *     </ul>
     * </li>
     * <li>The consputation of {@code D_top} and {@code D_bottom} are best
     * understood with the above example (for {@code N = 8}).
     * <li>The output vector {@code y} is now in the last column of
     * {@code hadm}.</li>
     * <li><em>Algorithm from <a href="http://www.archive.chipcenter.com/dsp/DSP000517F1.html">chipcenter</a>.</em></li>
     * </ol>
     * <h3>Visually</h3>
     * <table border="1" align="center" cellpadding="3">
     * <tbody align="center">
     * <tr>
     *     <td></td><th>0</th><th>1</th><th>2</th><th>3</th>
     *     <th>&hellip;</th>
     *     <th>n + 1</th>
     * </tr>
     * <tr>
     *     <th>0</th>
     *     <td>x<sub>0</sub></td>
     *     <td colspan="5" rowspan="5" align="center" valign="middle">
     *         &uarr;<br/>
     *         &larr; D<sub>top</sub> &rarr;<br/>
     *         &darr;
     *     </td>
     * </tr>
     * <tr><th>1</th><td>x<sub>1</sub></td></tr>
     * <tr><th>2</th><td>x<sub>2</sub></td></tr>
     * <tr><th>&hellip;</th><td>&hellip;</td></tr>
     * <tr><th>N / 2 - 1</th><td>x<sub>N/2-1</sub></td></tr>
     * <tr>
     *     <th>N / 2</th>
     *     <td>x<sub>N/2</sub></td>
     *     <td colspan="5" rowspan="5" align="center" valign="middle">
     *         &uarr;<br/>
     *         &larr; D<sub>bottom</sub> &rarr;<br/>
     *         &darr;
     *     </td>
     * </tr>
     * <tr><th>N / 2 + 1</th><td>x<sub>N/2+1</sub></td></tr>
     * <tr><th>N / 2 + 2</th><td>x<sub>N/2+2</sub></td></tr>
     * <tr><th>&hellip;</th><td>&hellip;</td></tr>
     * <tr><th>N</th><td>x<sub>N</sub></td></tr>
     * </tbody>
     * </table>
     *
     * @param x the real data array to be transformed
     * @return the real transformed array, {@code y}
     * @throws MathIllegalArgumentException if the length of the data array is not a power of two
     */
    protected double[] fht(double[] x) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_228");
        final int n = x.length;
        final int halfN = AOR_divide(n, 2, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1914, _mut1915, _mut1916, _mut1917);
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(n));
        }
        /*
         * Instead of creating a matrix with p+1 columns and n rows, we use two
         * one dimension arrays which we are used in an alternating way.
         */
        double[] yPrevious = new double[n];
        double[] yCurrent = x.clone();
        // iterate from left to right (column)
        for (int j = 1; ROR_less(j, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1960, _mut1961, _mut1962, _mut1963, _mut1964); j <<= 1) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_228");
            // switch columns
            final double[] yTmp = yCurrent;
            yCurrent = yPrevious;
            yPrevious = yTmp;
            // iterate from top to bottom (row)
            for (int i = 0; ROR_less(i, halfN, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1930, _mut1931, _mut1932, _mut1933, _mut1934); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_228");
                // Dtop: the top part works with addition
                final int twoI = AOR_multiply(2, i, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1918, _mut1919, _mut1920, _mut1921);
                yCurrent[i] = AOR_plus(yPrevious[twoI], yPrevious[AOR_plus(twoI, 1, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1922, _mut1923, _mut1924, _mut1925)], "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1926, _mut1927, _mut1928, _mut1929);
            }
            for (int i = halfN; ROR_less(i, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1955, _mut1956, _mut1957, _mut1958, _mut1959); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_228");
                // Dbottom: the bottom part works with subtraction
                final int twoI = AOR_multiply(2, i, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1935, _mut1936, _mut1937, _mut1938);
                yCurrent[i] = AOR_minus(yPrevious[AOR_minus(twoI, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1939, _mut1940, _mut1941, _mut1942)], yPrevious[AOR_plus(AOR_minus(twoI, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1943, _mut1944, _mut1945, _mut1946), 1, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1947, _mut1948, _mut1949, _mut1950)], "org.apache.commons.math3.transform.FastHadamardTransformer.fht_228", _mut1951, _mut1952, _mut1953, _mut1954);
            }
        }
        return yCurrent;
    }

    /**
     * Returns the forward transform of the specified integer data set. The FHT
     * (Fast Hadamard Transform) uses only subtraction and addition.
     *
     * @param x the integer data array to be transformed
     * @return the integer transformed array, {@code y}
     * @throws MathIllegalArgumentException if the length of the data array is not a power of two
     */
    protected int[] fht(int[] x) throws MathIllegalArgumentException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_279");
        final int n = x.length;
        final int halfN = AOR_divide(n, 2, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1965, _mut1966, _mut1967, _mut1968);
        if (!ArithmeticUtils.isPowerOfTwo(n)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO, Integer.valueOf(n));
        }
        /*
         * Instead of creating a matrix with p+1 columns and n rows, we use two
         * one dimension arrays which we are used in an alternating way.
         */
        int[] yPrevious = new int[n];
        int[] yCurrent = x.clone();
        // iterate from left to right (column)
        for (int j = 1; ROR_less(j, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut2011, _mut2012, _mut2013, _mut2014, _mut2015); j <<= 1) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_279");
            // switch columns
            final int[] yTmp = yCurrent;
            yCurrent = yPrevious;
            yPrevious = yTmp;
            // iterate from top to bottom (row)
            for (int i = 0; ROR_less(i, halfN, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1981, _mut1982, _mut1983, _mut1984, _mut1985); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_279");
                // Dtop: the top part works with addition
                final int twoI = AOR_multiply(2, i, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1969, _mut1970, _mut1971, _mut1972);
                yCurrent[i] = AOR_plus(yPrevious[twoI], yPrevious[AOR_plus(twoI, 1, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1973, _mut1974, _mut1975, _mut1976)], "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1977, _mut1978, _mut1979, _mut1980);
            }
            for (int i = halfN; ROR_less(i, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut2006, _mut2007, _mut2008, _mut2009, _mut2010); ++i) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.transform.FastHadamardTransformer.fht_279");
                // Dbottom: the bottom part works with subtraction
                final int twoI = AOR_multiply(2, i, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1986, _mut1987, _mut1988, _mut1989);
                yCurrent[i] = AOR_minus(yPrevious[AOR_minus(twoI, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1990, _mut1991, _mut1992, _mut1993)], yPrevious[AOR_plus(AOR_minus(twoI, n, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1994, _mut1995, _mut1996, _mut1997), 1, "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut1998, _mut1999, _mut2000, _mut2001)], "org.apache.commons.math3.transform.FastHadamardTransformer.fht_279", _mut2002, _mut2003, _mut2004, _mut2005);
            }
        }
        // return the last computed output vector y
        return yCurrent;
    }
}
