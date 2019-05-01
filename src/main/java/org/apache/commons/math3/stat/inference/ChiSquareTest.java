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
package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implements Chi-Square test statistics.
 *
 * <p>This implementation handles both known and unknown distributions.</p>
 *
 * <p>Two samples tests can be used when the distribution is unknown <i>a priori</i>
 * but provided by one sample, or when the hypothesis under test is that the two
 * samples come from the same underlying distribution.</p>
 */
public class ChiSquareTest {

    @Conditional
    public static boolean _mut5962 = false, _mut5963 = false, _mut5964 = false, _mut5965 = false, _mut5966 = false, _mut5967 = false, _mut5968 = false, _mut5969 = false, _mut5970 = false, _mut5971 = false, _mut5972 = false, _mut5973 = false, _mut5974 = false, _mut5975 = false, _mut5976 = false, _mut5977 = false, _mut5978 = false, _mut5979 = false, _mut5980 = false, _mut5981 = false, _mut5982 = false, _mut5983 = false, _mut5984 = false, _mut5985 = false, _mut5986 = false, _mut5987 = false, _mut5988 = false, _mut5989 = false, _mut5990 = false, _mut5991 = false, _mut5992 = false, _mut5993 = false, _mut5994 = false, _mut5995 = false, _mut5996 = false, _mut5997 = false, _mut5998 = false, _mut5999 = false, _mut6000 = false, _mut6001 = false, _mut6002 = false, _mut6003 = false, _mut6004 = false, _mut6005 = false, _mut6006 = false, _mut6007 = false, _mut6008 = false, _mut6009 = false, _mut6010 = false, _mut6011 = false, _mut6012 = false, _mut6013 = false, _mut6014 = false, _mut6015 = false, _mut6016 = false, _mut6017 = false, _mut6018 = false, _mut6019 = false, _mut6020 = false, _mut6021 = false, _mut6022 = false, _mut6023 = false, _mut6024 = false, _mut6025 = false, _mut6026 = false, _mut6027 = false, _mut6028 = false, _mut6029 = false, _mut6030 = false, _mut6031 = false, _mut6032 = false, _mut6033 = false, _mut6034 = false, _mut6035 = false, _mut6036 = false, _mut6037 = false, _mut6038 = false, _mut6039 = false, _mut6040 = false, _mut6041 = false, _mut6042 = false, _mut6043 = false, _mut6044 = false, _mut6045 = false, _mut6046 = false, _mut6047 = false, _mut6048 = false, _mut6049 = false, _mut6050 = false, _mut6051 = false, _mut6052 = false, _mut6053 = false, _mut6054 = false, _mut6055 = false, _mut6056 = false, _mut6057 = false, _mut6058 = false, _mut6059 = false, _mut6060 = false, _mut6061 = false, _mut6062 = false, _mut6063 = false, _mut6064 = false, _mut6065 = false, _mut6066 = false, _mut6067 = false, _mut6068 = false, _mut6069 = false, _mut6070 = false, _mut6071 = false, _mut6072 = false, _mut6073 = false, _mut6074 = false, _mut6075 = false, _mut6076 = false, _mut6077 = false, _mut6078 = false, _mut6079 = false, _mut6080 = false, _mut6081 = false, _mut6082 = false, _mut6083 = false, _mut6084 = false, _mut6085 = false, _mut6086 = false, _mut6087 = false, _mut6088 = false, _mut6089 = false, _mut6090 = false, _mut6091 = false, _mut6092 = false, _mut6093 = false, _mut6094 = false, _mut6095 = false, _mut6096 = false, _mut6097 = false, _mut6098 = false, _mut6099 = false, _mut6100 = false, _mut6101 = false, _mut6102 = false, _mut6103 = false, _mut6104 = false, _mut6105 = false, _mut6106 = false, _mut6107 = false, _mut6108 = false, _mut6109 = false, _mut6110 = false, _mut6111 = false, _mut6112 = false, _mut6113 = false, _mut6114 = false, _mut6115 = false, _mut6116 = false, _mut6117 = false, _mut6118 = false, _mut6119 = false, _mut6120 = false, _mut6121 = false, _mut6122 = false, _mut6123 = false, _mut6124 = false, _mut6125 = false, _mut6126 = false, _mut6127 = false, _mut6128 = false, _mut6129 = false, _mut6130 = false, _mut6131 = false, _mut6132 = false, _mut6133 = false, _mut6134 = false, _mut6135 = false, _mut6136 = false, _mut6137 = false, _mut6138 = false, _mut6139 = false, _mut6140 = false, _mut6141 = false, _mut6142 = false, _mut6143 = false, _mut6144 = false, _mut6145 = false, _mut6146 = false, _mut6147 = false, _mut6148 = false, _mut6149 = false, _mut6150 = false, _mut6151 = false, _mut6152 = false, _mut6153 = false, _mut6154 = false, _mut6155 = false, _mut6156 = false, _mut6157 = false, _mut6158 = false, _mut6159 = false, _mut6160 = false, _mut6161 = false, _mut6162 = false, _mut6163 = false, _mut6164 = false, _mut6165 = false, _mut6166 = false, _mut6167 = false, _mut6168 = false, _mut6169 = false, _mut6170 = false, _mut6171 = false, _mut6172 = false, _mut6173 = false, _mut6174 = false, _mut6175 = false, _mut6176 = false, _mut6177 = false, _mut6178 = false, _mut6179 = false, _mut6180 = false, _mut6181 = false, _mut6182 = false, _mut6183 = false, _mut6184 = false, _mut6185 = false, _mut6186 = false, _mut6187 = false, _mut6188 = false, _mut6189 = false, _mut6190 = false, _mut6191 = false, _mut6192 = false, _mut6193 = false, _mut6194 = false, _mut6195 = false, _mut6196 = false, _mut6197 = false, _mut6198 = false, _mut6199 = false, _mut6200 = false, _mut6201 = false, _mut6202 = false, _mut6203 = false, _mut6204 = false, _mut6205 = false, _mut6206 = false, _mut6207 = false, _mut6208 = false, _mut6209 = false, _mut6210 = false, _mut6211 = false, _mut6212 = false, _mut6213 = false, _mut6214 = false, _mut6215 = false, _mut6216 = false, _mut6217 = false, _mut6218 = false, _mut6219 = false, _mut6220 = false, _mut6221 = false, _mut6222 = false, _mut6223 = false, _mut6224 = false, _mut6225 = false, _mut6226 = false, _mut6227 = false, _mut6228 = false, _mut6229 = false, _mut6230 = false, _mut6231 = false, _mut6232 = false, _mut6233 = false, _mut6234 = false, _mut6235 = false, _mut6236 = false, _mut6237 = false, _mut6238 = false, _mut6239 = false;

    /**
     * Construct a ChiSquareTest
     */
    public ChiSquareTest() {
        super();
    }

    /**
     * Computes the <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-Square statistic</a> comparing <code>observed</code> and <code>expected</code>
     * frequency counts.
     * <p>
     * This statistic can be used to perform a Chi-Square test evaluating the null
     * hypothesis that the observed counts follow the expected distribution.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return chiSquare test statistic
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     */
    public double chiSquare(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80");
        if (ROR_less(expected.length, 2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5962, _mut5963, _mut5964, _mut5965, _mut5966)) {
            throw new DimensionMismatchException(expected.length, 2);
        }
        if (ROR_not_equals(expected.length, observed.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5967, _mut5968, _mut5969, _mut5970, _mut5971)) {
            throw new DimensionMismatchException(expected.length, observed.length);
        }
        MathArrays.checkPositive(expected);
        MathArrays.checkNonNegative(observed);
        double sumExpected = 0d;
        double sumObserved = 0d;
        for (int i = 0; ROR_less(i, observed.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5972, _mut5973, _mut5974, _mut5975, _mut5976); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80");
            sumExpected += expected[i];
            sumObserved += observed[i];
        }
        double ratio = 1.0d;
        boolean rescale = false;
        if (ROR_greater(FastMath.abs(AOR_minus(sumExpected, sumObserved, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5977, _mut5978, _mut5979, _mut5980)), 10E-6, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5981, _mut5982, _mut5983, _mut5984, _mut5985)) {
            ratio = AOR_divide(sumObserved, sumExpected, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5986, _mut5987, _mut5988, _mut5989);
            rescale = true;
        }
        double sumSq = 0.0d;
        for (int i = 0; ROR_less(i, observed.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6022, _mut6023, _mut6024, _mut6025, _mut6026); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80");
            if (rescale) {
                final double dev = AOR_minus(observed[i], AOR_multiply(ratio, expected[i], "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6002, _mut6003, _mut6004, _mut6005), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6006, _mut6007, _mut6008, _mut6009);
                sumSq += AOR_divide(AOR_multiply(dev, dev, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6010, _mut6011, _mut6012, _mut6013), (AOR_multiply(ratio, expected[i], "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6014, _mut6015, _mut6016, _mut6017)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut6018, _mut6019, _mut6020, _mut6021);
            } else {
                final double dev = AOR_minus(observed[i], expected[i], "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5990, _mut5991, _mut5992, _mut5993);
                sumSq += AOR_divide(AOR_multiply(dev, dev, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5994, _mut5995, _mut5996, _mut5997), expected[i], "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_80", _mut5998, _mut5999, _mut6000, _mut6001);
            }
        }
        return sumSq;
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-square goodness of fit test</a> comparing the <code>observed</code>
     * frequency counts to those in the <code>expected</code> array.
     * <p>
     * The number returned is the smallest significance level at which one can reject
     * the null hypothesis that the observed counts conform to the frequency distribution
     * described by the expected counts.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @return p-value
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double chiSquareTest(final double[] expected, final long[] observed) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_154");
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, AOR_minus(expected.length, 1.0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_154", _mut6027, _mut6028, _mut6029, _mut6030));
        return AOR_minus(1.0, distribution.cumulativeProbability(chiSquare(expected, observed)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_154", _mut6031, _mut6032, _mut6033, _mut6034);
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm">
     * Chi-square goodness of fit test</a> evaluating the null hypothesis that the
     * observed counts conform to the frequency distribution described by the expected
     * counts, with significance level <code>alpha</code>.  Returns true iff the null
     * hypothesis can be rejected with 100 * (1 - alpha) percent confidence.
     * <p>
     * <strong>Example:</strong><br>
     * To test the hypothesis that <code>observed</code> follows
     * <code>expected</code> at the 99% level, use </p><p>
     * <code>chiSquareTest(expected, observed, 0.01) </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Expected counts must all be positive.
     * </li>
     * <li>Observed counts must all be &ge; 0.
     * </li>
     * <li>The observed and expected arrays must have the same length and
     * their common length must be at least 2.
     * <li> <code> 0 &lt; alpha &lt; 0.5 </code>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     * <p><strong>Note: </strong>This implementation rescales the
     * <code>expected</code> array if necessary to ensure that the sum of the
     * expected and observed counts are equal.</p>
     *
     * @param observed array of observed frequency counts
     * @param expected array of expected frequency counts
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws NotPositiveException if <code>observed</code> has negative entries
     * @throws NotStrictlyPositiveException if <code>expected</code> has entries that are
     * not strictly positive
     * @throws DimensionMismatchException if the arrays length is less than 2
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean chiSquareTest(final double[] expected, final long[] observed, final double alpha) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203");
        if ((_mut6045 ? ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203", _mut6035, _mut6036, _mut6037, _mut6038, _mut6039)) && (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203", _mut6040, _mut6041, _mut6042, _mut6043, _mut6044))) : ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203", _mut6035, _mut6036, _mut6037, _mut6038, _mut6039)) || (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203", _mut6040, _mut6041, _mut6042, _mut6043, _mut6044))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(chiSquareTest(expected, observed), alpha, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_203", _mut6046, _mut6047, _mut6048, _mut6049, _mut6050);
    }

    /**
     *  Computes the Chi-Square statistic associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     *  chi-square test of independence</a> based on the input <code>counts</code>
     *  array, viewed as a two-way table.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays
     *  must have the same length).
     * </li>
     * <li>The 2-way table represented by <code>counts</code> must have at
     *  least 2 columns and at least 2 rows.
     * </li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @return chiSquare test statistic
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has negative entries
     */
    public double chiSquare(final long[][] counts) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244");
        checkArray(counts);
        int nRows = counts.length;
        int nCols = counts[0].length;
        // compute row, column and total sums
        double[] rowSum = new double[nRows];
        double[] colSum = new double[nCols];
        double total = 0.0d;
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6056, _mut6057, _mut6058, _mut6059, _mut6060); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6051, _mut6052, _mut6053, _mut6054, _mut6055); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244");
                rowSum[row] += counts[row][col];
                colSum[col] += counts[row][col];
                total += counts[row][col];
            }
        }
        // compute expected counts and chi-square
        double sumSq = 0.0d;
        double expected = 0.0d;
        for (int row = 0; ROR_less(row, nRows, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6090, _mut6091, _mut6092, _mut6093, _mut6094); row++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244");
            for (int col = 0; ROR_less(col, nCols, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6085, _mut6086, _mut6087, _mut6088, _mut6089); col++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244");
                expected = AOR_divide((AOR_multiply(rowSum[row], colSum[col], "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6061, _mut6062, _mut6063, _mut6064)), total, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6065, _mut6066, _mut6067, _mut6068);
                sumSq += AOR_divide((AOR_multiply((AOR_minus(counts[row][col], expected, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6069, _mut6070, _mut6071, _mut6072)), (AOR_minus(counts[row][col], expected, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6073, _mut6074, _mut6075, _mut6076)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6077, _mut6078, _mut6079, _mut6080)), expected, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquare_244", _mut6081, _mut6082, _mut6083, _mut6084);
            }
        }
        return sumSq;
    }

    /**
     * Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a
     * <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     * chi-square test of independence</a> based on the input <code>counts</code>
     * array, viewed as a two-way table.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays must have
     *     the same length).
     * </li>
     * <li>The 2-way table represented by <code>counts</code> must have at least 2
     *     columns and at least 2 rows.
     * </li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @return p-value
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has negative entries
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public double chiSquareTest(final long[][] counts) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_309");
        checkArray(counts);
        double df = AOR_multiply((AOR_minus((double) counts.length, 1, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_309", _mut6095, _mut6096, _mut6097, _mut6098)), (AOR_minus((double) counts[0].length, 1, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_309", _mut6099, _mut6100, _mut6101, _mut6102)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_309", _mut6103, _mut6104, _mut6105, _mut6106);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(df);
        return AOR_minus(1, distribution.cumulativeProbability(chiSquare(counts)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_309", _mut6107, _mut6108, _mut6109, _mut6110);
    }

    /**
     * Performs a <a href="http://www.itl.nist.gov/div898/handbook/prc/section4/prc45.htm">
     * chi-square test of independence</a> evaluating the null hypothesis that the
     * classifications represented by the counts in the columns of the input 2-way table
     * are independent of the rows, with significance level <code>alpha</code>.
     * Returns true iff the null hypothesis can be rejected with 100 * (1 - alpha) percent
     * confidence.
     * <p>
     * The rows of the 2-way table are
     * <code>count[0], ... , count[count.length - 1] </code></p>
     * <p>
     * <strong>Example:</strong><br>
     * To test the null hypothesis that the counts in
     * <code>count[0], ... , count[count.length - 1] </code>
     *  all correspond to the same underlying probability distribution at the 99% level, use</p>
     * <p><code>chiSquareTest(counts, 0.01)</code></p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>All counts must be &ge; 0.
     * </li>
     * <li>The count array must be rectangular (i.e. all count[i] subarrays must have the
     *     same length).</li>
     * <li>The 2-way table represented by <code>counts</code> must have at least 2 columns and
     *     at least 2 rows.</li>
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param counts array representation of 2-way table
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not rectangular
     * @throws NotPositiveException if {@code counts} has any negative entries
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs computing the p-value
     */
    public boolean chiSquareTest(final long[][] counts, final double alpha) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359");
        if ((_mut6121 ? ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359", _mut6111, _mut6112, _mut6113, _mut6114, _mut6115)) && (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359", _mut6116, _mut6117, _mut6118, _mut6119, _mut6120))) : ((ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359", _mut6111, _mut6112, _mut6113, _mut6114, _mut6115)) || (ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359", _mut6116, _mut6117, _mut6118, _mut6119, _mut6120))))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(chiSquareTest(counts), alpha, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTest_359", _mut6122, _mut6123, _mut6124, _mut6125, _mut6126);
    }

    /**
     * <p>Computes a
     * <a href="http://www.itl.nist.gov/div898/software/dataplot/refman1/auxillar/chi2samp.htm">
     * Chi-Square two sample test statistic</a> comparing bin frequency counts
     * in <code>observed1</code> and <code>observed2</code>.  The
     * sums of frequency counts in the two samples are not required to be the
     * same.  The formula used to compute the test statistic is</p>
     * <code>
     * &sum;[(K * observed1[i] - observed2[i]/K)<sup>2</sup> / (observed1[i] + observed2[i])]
     * </code> where
     * <br/><code>K = &sqrt;[&sum(observed2 / &sum;(observed1)]</code>
     * </p>
     * <p>This statistic can be used to perform a Chi-Square test evaluating the
     * null hypothesis that both observed counts follow the same distribution.</p>
     * <p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must have
     * the same length and their common length must be at least 2.
     * </li></ul></p><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @return chiSquare test statistic
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at some index is zero
     * for both arrays
     * @since 1.2
     */
    public double chiSquareDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410");
        // Make sure lengths are same
        if (ROR_less(observed1.length, 2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6127, _mut6128, _mut6129, _mut6130, _mut6131)) {
            throw new DimensionMismatchException(observed1.length, 2);
        }
        if (ROR_not_equals(observed1.length, observed2.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6132, _mut6133, _mut6134, _mut6135, _mut6136)) {
            throw new DimensionMismatchException(observed1.length, observed2.length);
        }
        // Ensure non-negative counts
        MathArrays.checkNonNegative(observed1);
        MathArrays.checkNonNegative(observed2);
        // Compute and compare count sums
        long countSum1 = 0;
        long countSum2 = 0;
        boolean unequalCounts = false;
        double weight = 0.0;
        for (int i = 0; ROR_less(i, observed1.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6137, _mut6138, _mut6139, _mut6140, _mut6141); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410");
            countSum1 += observed1[i];
            countSum2 += observed2[i];
        }
        // Ensure neither sample is uniformly 0
        if ((_mut6152 ? (ROR_equals(countSum1, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6142, _mut6143, _mut6144, _mut6145, _mut6146) && ROR_equals(countSum2, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6147, _mut6148, _mut6149, _mut6150, _mut6151)) : (ROR_equals(countSum1, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6142, _mut6143, _mut6144, _mut6145, _mut6146) || ROR_equals(countSum2, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6147, _mut6148, _mut6149, _mut6150, _mut6151)))) {
            throw new ZeroException();
        }
        // Compare and compute weight only if different
        unequalCounts = ROR_not_equals(countSum1, countSum2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6153, _mut6154, _mut6155, _mut6156, _mut6157);
        if (unequalCounts) {
            weight = FastMath.sqrt(AOR_divide((double) countSum1, (double) countSum2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6158, _mut6159, _mut6160, _mut6161));
        }
        // Compute ChiSquare statistic
        double sumSq = 0.0d;
        double dev = 0.0d;
        double obs1 = 0.0d;
        double obs2 = 0.0d;
        for (int i = 0; ROR_less(i, observed1.length, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6201, _mut6202, _mut6203, _mut6204, _mut6205); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410");
            if ((_mut6172 ? (ROR_equals(observed1[i], 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6162, _mut6163, _mut6164, _mut6165, _mut6166) || ROR_equals(observed2[i], 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6167, _mut6168, _mut6169, _mut6170, _mut6171)) : (ROR_equals(observed1[i], 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6162, _mut6163, _mut6164, _mut6165, _mut6166) && ROR_equals(observed2[i], 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6167, _mut6168, _mut6169, _mut6170, _mut6171)))) {
                throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, i);
            } else {
                obs1 = observed1[i];
                obs2 = observed2[i];
                if (unequalCounts) {
                    // apply weights
                    dev = AOR_minus(AOR_divide(obs1, weight, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6177, _mut6178, _mut6179, _mut6180), AOR_multiply(obs2, weight, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6181, _mut6182, _mut6183, _mut6184), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6185, _mut6186, _mut6187, _mut6188);
                } else {
                    dev = AOR_minus(obs1, obs2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6173, _mut6174, _mut6175, _mut6176);
                }
                sumSq += AOR_divide((AOR_multiply(dev, dev, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6189, _mut6190, _mut6191, _mut6192)), (AOR_plus(obs1, obs2, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6193, _mut6194, _mut6195, _mut6196)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareDataSetsComparison_410", _mut6197, _mut6198, _mut6199, _mut6200);
            }
        }
        return sumSq;
    }

    /**
     * <p>Returns the <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a Chi-Square two sample test comparing
     * bin frequency counts in <code>observed1</code> and
     * <code>observed2</code>.
     * </p>
     * <p>The number returned is the smallest significance level at which one
     * can reject the null hypothesis that the observed counts conform to the
     * same distribution.
     * </p>
     * <p>See {@link #chiSquareDataSetsComparison(long[], long[])} for details
     * on the formula used to compute the test statistic. The degrees of
     * of freedom used to perform the test is one less than the common length
     * of the input observed count arrays.
     * </p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must
     * have the same length and
     * their common length must be at least 2.
     * </li></ul><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @return p-value
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at the same index is zero
     * for both arrays
     * @throws MaxCountExceededException if an error occurs computing the p-value
     * @since 1.2
     */
    public double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_507");
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final ChiSquaredDistribution distribution = new ChiSquaredDistribution(null, AOR_minus((double) observed1.length, 1, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_507", _mut6206, _mut6207, _mut6208, _mut6209));
        return AOR_minus(1, distribution.cumulativeProbability(chiSquareDataSetsComparison(observed1, observed2)), "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_507", _mut6210, _mut6211, _mut6212, _mut6213);
    }

    /**
     * <p>Performs a Chi-Square two sample test comparing two binned data
     * sets. The test evaluates the null hypothesis that the two lists of
     * observed counts conform to the same frequency distribution, with
     * significance level <code>alpha</code>.  Returns true iff the null
     * hypothesis can be rejected with 100 * (1 - alpha) percent confidence.
     * </p>
     * <p>See {@link #chiSquareDataSetsComparison(long[], long[])} for
     * details on the formula used to compute the Chisquare statistic used
     * in the test. The degrees of of freedom used to perform the test is
     * one less than the common length of the input observed count arrays.
     * </p>
     * <strong>Preconditions</strong>: <ul>
     * <li>Observed counts must be non-negative.
     * </li>
     * <li>Observed counts for a specific bin must not both be zero.
     * </li>
     * <li>Observed counts for a specific sample must not all be 0.
     * </li>
     * <li>The arrays <code>observed1</code> and <code>observed2</code> must
     * have the same length and their common length must be at least 2.
     * </li>
     * <li> <code> 0 < alpha < 0.5 </code>
     * </li></ul><p>
     * If any of the preconditions are not met, an
     * <code>IllegalArgumentException</code> is thrown.</p>
     *
     * @param observed1 array of observed frequency counts of the first data set
     * @param observed2 array of observed frequency counts of the second data set
     * @param alpha significance level of the test
     * @return true iff null hypothesis can be rejected with confidence
     * 1 - alpha
     * @throws DimensionMismatchException the the length of the arrays does not match
     * @throws NotPositiveException if any entries in <code>observed1</code> or
     * <code>observed2</code> are negative
     * @throws ZeroException if either all counts of <code>observed1</code> or
     * <code>observed2</code> are zero, or if the count at the same index is zero
     * for both arrays
     * @throws OutOfRangeException if <code>alpha</code> is not in the range (0, 0.5]
     * @throws MaxCountExceededException if an error occurs performing the test
     * @since 1.2
     */
    public boolean chiSquareTestDataSetsComparison(final long[] observed1, final long[] observed2, final double alpha) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561");
        if ((_mut6224 ? (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561", _mut6214, _mut6215, _mut6216, _mut6217, _mut6218) && ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561", _mut6219, _mut6220, _mut6221, _mut6222, _mut6223)) : (ROR_less_equals(alpha, 0, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561", _mut6214, _mut6215, _mut6216, _mut6217, _mut6218) || ROR_greater(alpha, 0.5, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561", _mut6219, _mut6220, _mut6221, _mut6222, _mut6223)))) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, alpha, 0, 0.5);
        }
        return ROR_less(chiSquareTestDataSetsComparison(observed1, observed2), alpha, "org.apache.commons.math3.stat.inference.ChiSquareTest.chiSquareTestDataSetsComparison_561", _mut6225, _mut6226, _mut6227, _mut6228, _mut6229);
    }

    /**
     * Checks to make sure that the input long[][] array is rectangular,
     * has at least 2 rows and 2 columns, and has all non-negative entries.
     *
     * @param in input 2-way table to check
     * @throws NullArgumentException if the array is null
     * @throws DimensionMismatchException if the array is not valid
     * @throws NotPositiveException if the array contains any negative entries
     */
    private void checkArray(final long[][] in) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.ChiSquareTest.checkArray_585");
        if (ROR_less(in.length, 2, "org.apache.commons.math3.stat.inference.ChiSquareTest.checkArray_585", _mut6230, _mut6231, _mut6232, _mut6233, _mut6234)) {
            throw new DimensionMismatchException(in.length, 2);
        }
        if (ROR_less(in[0].length, 2, "org.apache.commons.math3.stat.inference.ChiSquareTest.checkArray_585", _mut6235, _mut6236, _mut6237, _mut6238, _mut6239)) {
            throw new DimensionMismatchException(in[0].length, 2);
        }
        MathArrays.checkRectangular(in);
        MathArrays.checkNonNegative(in);
    }
}
