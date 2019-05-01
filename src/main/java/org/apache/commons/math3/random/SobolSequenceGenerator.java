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
package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of a Sobol sequence.
 * <p>
 * A Sobol sequence is a low-discrepancy sequence with the property that for all values of N,
 * its subsequence (x1, ... xN) has a low discrepancy. It can be used to generate pseudo-random
 * points in a space S, which are equi-distributed.
 * <p>
 * The implementation already comes with support for up to 1000 dimensions with direction numbers
 * calculated from <a href="http://web.maths.unsw.edu.au/~fkuo/sobol/">Stephen Joe and Frances Kuo</a>.
 * <p>
 * The generator supports two modes:
 * <ul>
 *   <li>sequential generation of points: {@link #nextVector()}</li>
 *   <li>random access to the i-th point in the sequence: {@link #skipTo(int)}</li>
 * </ul>
 *
 * @see <a href="http://en.wikipedia.org/wiki/Sobol_sequence">Sobol sequence (Wikipedia)</a>
 * @see <a href="http://web.maths.unsw.edu.au/~fkuo/sobol/">Sobol sequence direction numbers</a>
 *
 * @since 3.3
 */
public class SobolSequenceGenerator implements RandomVectorGenerator {

    @Conditional
    public static boolean _mut52816 = false, _mut52817 = false, _mut52818 = false, _mut52819 = false, _mut52820 = false, _mut52821 = false, _mut52822 = false, _mut52823 = false, _mut52824 = false, _mut52825 = false, _mut52826 = false, _mut52827 = false, _mut52828 = false, _mut52829 = false, _mut52830 = false, _mut52831 = false, _mut52832 = false, _mut52833 = false, _mut52834 = false, _mut52835 = false, _mut52836 = false, _mut52837 = false, _mut52838 = false, _mut52839 = false, _mut52840 = false, _mut52841 = false, _mut52842 = false, _mut52843 = false, _mut52844 = false, _mut52845 = false, _mut52846 = false, _mut52847 = false, _mut52848 = false, _mut52849 = false, _mut52850 = false, _mut52851 = false, _mut52852 = false, _mut52853 = false, _mut52854 = false, _mut52855 = false, _mut52856 = false, _mut52857 = false, _mut52858 = false, _mut52859 = false, _mut52860 = false, _mut52861 = false, _mut52862 = false, _mut52863 = false, _mut52864 = false, _mut52865 = false, _mut52866 = false, _mut52867 = false, _mut52868 = false, _mut52869 = false, _mut52870 = false, _mut52871 = false, _mut52872 = false, _mut52873 = false, _mut52874 = false, _mut52875 = false, _mut52876 = false, _mut52877 = false, _mut52878 = false, _mut52879 = false, _mut52880 = false, _mut52881 = false, _mut52882 = false, _mut52883 = false, _mut52884 = false, _mut52885 = false, _mut52886 = false, _mut52887 = false, _mut52888 = false, _mut52889 = false, _mut52890 = false, _mut52891 = false, _mut52892 = false, _mut52893 = false, _mut52894 = false, _mut52895 = false, _mut52896 = false, _mut52897 = false, _mut52898 = false, _mut52899 = false, _mut52900 = false, _mut52901 = false, _mut52902 = false, _mut52903 = false, _mut52904 = false, _mut52905 = false, _mut52906 = false, _mut52907 = false, _mut52908 = false, _mut52909 = false, _mut52910 = false, _mut52911 = false, _mut52912 = false, _mut52913 = false, _mut52914 = false, _mut52915 = false, _mut52916 = false, _mut52917 = false, _mut52918 = false, _mut52919 = false, _mut52920 = false, _mut52921 = false, _mut52922 = false, _mut52923 = false, _mut52924 = false, _mut52925 = false, _mut52926 = false, _mut52927 = false, _mut52928 = false, _mut52929 = false, _mut52930 = false, _mut52931 = false, _mut52932 = false, _mut52933 = false, _mut52934 = false, _mut52935 = false, _mut52936 = false, _mut52937 = false, _mut52938 = false, _mut52939 = false, _mut52940 = false, _mut52941 = false, _mut52942 = false, _mut52943 = false, _mut52944 = false, _mut52945 = false, _mut52946 = false, _mut52947 = false, _mut52948 = false, _mut52949 = false, _mut52950 = false, _mut52951 = false, _mut52952 = false, _mut52953 = false, _mut52954 = false, _mut52955 = false, _mut52956 = false, _mut52957 = false, _mut52958 = false, _mut52959 = false, _mut52960 = false, _mut52961 = false, _mut52962 = false, _mut52963 = false, _mut52964 = false, _mut52965 = false, _mut52966 = false, _mut52967 = false, _mut52968 = false, _mut52969 = false, _mut52970 = false, _mut52971 = false, _mut52972 = false, _mut52973 = false, _mut52974 = false, _mut52975 = false, _mut52976 = false, _mut52977 = false, _mut52978 = false, _mut52979 = false, _mut52980 = false, _mut52981 = false, _mut52982 = false, _mut52983 = false, _mut52984 = false;

    /**
     * The number of bits to use.
     */
    private static final int BITS = 52;

    /**
     * The scaling factor.
     */
    private static final double SCALE = FastMath.pow(2, BITS);

    /**
     * The maximum supported space dimension.
     */
    private static final int MAX_DIMENSION = 1000;

    /**
     * The resource containing the direction numbers.
     */
    private static final String RESOURCE_NAME = "/assets/org/apache/commons/math3/random/new-joe-kuo-6.1000";

    /**
     * Character set for file input.
     */
    private static final String FILE_CHARSET = "US-ASCII";

    /**
     * Space dimension.
     */
    private final int dimension;

    /**
     * The current index in the sequence.
     */
    private int count = 0;

    /**
     * The direction vector for each component.
     */
    private final long[][] direction;

    /**
     * The current state.
     */
    private final long[] x;

    /**
     * Construct a new Sobol sequence generator for the given space dimension.
     *
     * @param dimension the space dimension
     * @throws OutOfRangeException if the space dimension is outside the allowed range of [1, 1000]
     */
    public SobolSequenceGenerator(final int dimension) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91");
        if ((_mut52826 ? (ROR_less(dimension, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91", _mut52816, _mut52817, _mut52818, _mut52819, _mut52820) && ROR_greater(dimension, MAX_DIMENSION, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91", _mut52821, _mut52822, _mut52823, _mut52824, _mut52825)) : (ROR_less(dimension, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91", _mut52816, _mut52817, _mut52818, _mut52819, _mut52820) || ROR_greater(dimension, MAX_DIMENSION, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91", _mut52821, _mut52822, _mut52823, _mut52824, _mut52825)))) {
            throw new OutOfRangeException(dimension, 1, MAX_DIMENSION);
        }
        // initialize the other dimensions with direction numbers from a resource
        final InputStream is = getClass().getResourceAsStream(RESOURCE_NAME);
        if (is == null) {
            throw new MathInternalError();
        }
        this.dimension = dimension;
        // init data structures
        direction = new long[dimension][AOR_plus(BITS, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_91", _mut52827, _mut52828, _mut52829, _mut52830)];
        x = new long[dimension];
        try {
            initFromStream(is);
        } catch (IOException e) {
            // the internal resource file could not be read -> should not happen
            throw new MathInternalError();
        } catch (MathParseException e) {
            // the internal resource file could not be parsed -> should not happen
            throw new MathInternalError();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Construct a new Sobol sequence generator for the given space dimension with
     * direction vectors loaded from the given stream.
     * <p>
     * The expected format is identical to the files available from
     * <a href="http://web.maths.unsw.edu.au/~fkuo/sobol/">Stephen Joe and Frances Kuo</a>.
     * The first line will be ignored as it is assumed to contain only the column headers.
     * The columns are:
     * <ul>
     *  <li>d: the dimension</li>
     *  <li>s: the degree of the primitive polynomial</li>
     *  <li>a: the number representing the coefficients</li>
     *  <li>m: the list of initial direction numbers</li>
     * </ul>
     * Example:
     * <pre>
     * d       s       a       m_i
     * 2       1       0       1
     * 3       2       1       1 3
     * </pre>
     * <p>
     * The input stream <i>must</i> be an ASCII text containing one valid direction vector per line.
     *
     * @param dimension the space dimension
     * @param is the stream to read the direction vectors from
     * @throws NotStrictlyPositiveException if the space dimension is &lt; 1
     * @throws OutOfRangeException if the space dimension is outside the range [1, max], where
     *   max refers to the maximum dimension found in the input stream
     * @throws MathParseException if the content in the stream could not be parsed successfully
     * @throws IOException if an error occurs while reading from the input stream
     */
    public SobolSequenceGenerator(final int dimension, final InputStream is) throws NotStrictlyPositiveException, MathParseException, IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_156");
        if (ROR_less(dimension, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_156", _mut52831, _mut52832, _mut52833, _mut52834, _mut52835)) {
            throw new NotStrictlyPositiveException(dimension);
        }
        this.dimension = dimension;
        // init data structures
        direction = new long[dimension][AOR_plus(BITS, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_156", _mut52836, _mut52837, _mut52838, _mut52839)];
        x = new long[dimension];
        // initialize the other dimensions with direction numbers from the stream
        int lastDimension = initFromStream(is);
        if (ROR_less(lastDimension, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.SobolSequenceGenerator_156", _mut52840, _mut52841, _mut52842, _mut52843, _mut52844)) {
            throw new OutOfRangeException(dimension, 1, lastDimension);
        }
    }

    /**
     * Load the direction vector for each dimension from the given stream.
     * <p>
     * The input stream <i>must</i> be an ASCII text containing one
     * valid direction vector per line.
     *
     * @param is the input stream to read the direction vector from
     * @return the last dimension that has been read from the input stream
     * @throws IOException if the stream could not be read
     * @throws MathParseException if the content could not be parsed successfully
     */
    private int initFromStream(final InputStream is) throws MathParseException, IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187");
        // special case: dimension 1 -> use unit initialization
        for (int i = 1; ROR_less_equals(i, BITS, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52849, _mut52850, _mut52851, _mut52852, _mut52853); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187");
            direction[0][i] = 1l << (AOR_minus(BITS, i, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52845, _mut52846, _mut52847, _mut52848));
        }
        final Charset charset = Charset.forName(FILE_CHARSET);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        int dim = -1;
        try {
            // ignore first line
            reader.readLine();
            int lineNumber = 2;
            int index = 1;
            String line = null;
            while ((line = reader.readLine()) != null) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187");
                StringTokenizer st = new StringTokenizer(line, " ");
                try {
                    dim = Integer.parseInt(st.nextToken());
                    if ((_mut52864 ? (ROR_greater_equals(dim, 2, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52854, _mut52855, _mut52856, _mut52857, _mut52858) || ROR_less_equals(dim, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52859, _mut52860, _mut52861, _mut52862, _mut52863)) : (ROR_greater_equals(dim, 2, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52854, _mut52855, _mut52856, _mut52857, _mut52858) && ROR_less_equals(dim, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52859, _mut52860, _mut52861, _mut52862, _mut52863)))) {
                        // we have found the right dimension
                        final int s = Integer.parseInt(st.nextToken());
                        final int a = Integer.parseInt(st.nextToken());
                        final int[] m = new int[AOR_plus(s, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52865, _mut52866, _mut52867, _mut52868)];
                        for (int i = 1; ROR_less_equals(i, s, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52869, _mut52870, _mut52871, _mut52872, _mut52873); i++) {
                            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187");
                            m[i] = Integer.parseInt(st.nextToken());
                        }
                        initDirectionVector(index++, a, m);
                    }
                    if (ROR_greater(dim, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.initFromStream_187", _mut52874, _mut52875, _mut52876, _mut52877, _mut52878)) {
                        return dim;
                    }
                } catch (NoSuchElementException e) {
                    throw new MathParseException(line, lineNumber);
                } catch (NumberFormatException e) {
                    throw new MathParseException(line, lineNumber);
                }
                lineNumber++;
            }
        } finally {
            reader.close();
        }
        return dim;
    }

    /**
     * Calculate the direction numbers from the given polynomial.
     *
     * @param d the dimension, zero-based
     * @param a the coefficients of the primitive polynomial
     * @param m the initial direction numbers
     */
    private void initDirectionVector(final int d, final int a, final int[] m) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243");
        final int s = AOR_minus(m.length, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52879, _mut52880, _mut52881, _mut52882);
        for (int i = 1; ROR_less_equals(i, s, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52887, _mut52888, _mut52889, _mut52890, _mut52891); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243");
            direction[d][i] = ((long) m[i]) << (AOR_minus(BITS, i, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52883, _mut52884, _mut52885, _mut52886));
        }
        for (int i = s + 1; ROR_less_equals(i, BITS, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52925, _mut52926, _mut52927, _mut52928, _mut52929); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243");
            direction[d][i] = direction[d][AOR_minus(i, s, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52892, _mut52893, _mut52894, _mut52895)] ^ (direction[d][AOR_minus(i, s, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52896, _mut52897, _mut52898, _mut52899)] >> s);
            for (int k = 1; ROR_less_equals(k, AOR_minus(s, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52916, _mut52917, _mut52918, _mut52919), "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52920, _mut52921, _mut52922, _mut52923, _mut52924); k++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243");
                direction[d][i] ^= AOR_multiply(((a >> (AOR_minus(AOR_minus(s, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52900, _mut52901, _mut52902, _mut52903), k, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52904, _mut52905, _mut52906, _mut52907))) & 1), direction[d][AOR_minus(i, k, "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52908, _mut52909, _mut52910, _mut52911)], "org.apache.commons.math3.random.SobolSequenceGenerator.initDirectionVector_243", _mut52912, _mut52913, _mut52914, _mut52915);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public double[] nextVector() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257");
        final double[] v = new double[dimension];
        if (ROR_equals(count, 0, "org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257", _mut52930, _mut52931, _mut52932, _mut52933, _mut52934)) {
            count++;
            return v;
        }
        // find the index c of the rightmost 0
        int c = 1;
        int value = AOR_minus(count, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257", _mut52935, _mut52936, _mut52937, _mut52938);
        while (ROR_equals((value & 1), 1, "org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257", _mut52939, _mut52940, _mut52941, _mut52942, _mut52943)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257");
            value >>= 1;
            c++;
        }
        for (int i = 0; ROR_less(i, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257", _mut52948, _mut52949, _mut52950, _mut52951, _mut52952); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257");
            x[i] ^= direction[i][c];
            v[i] = AOR_divide((double) x[i], SCALE, "org.apache.commons.math3.random.SobolSequenceGenerator.nextVector_257", _mut52944, _mut52945, _mut52946, _mut52947);
        }
        count++;
        return v;
    }

    /**
     * Skip to the i-th point in the Sobol sequence.
     * <p>
     * This operation can be performed in O(1).
     *
     * @param index the index in the sequence to skip to
     * @return the i-th point in the Sobol sequence
     * @throws NotPositiveException if index &lt; 0
     */
    public double[] skipTo(final int index) throws NotPositiveException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289");
        if (ROR_equals(index, 0, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52953, _mut52954, _mut52955, _mut52956, _mut52957)) {
            // reset x vector
            Arrays.fill(x, 0);
        } else {
            final int i = AOR_minus(index, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52958, _mut52959, _mut52960, _mut52961);
            // compute the gray code of i = i XOR floor(i / 2)
            final long grayCode = i ^ (i >> 1);
            for (int j = 0; ROR_less(j, dimension, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52980, _mut52981, _mut52982, _mut52983, _mut52984); j++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289");
                long result = 0;
                for (int k = 1; ROR_less_equals(k, BITS, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52975, _mut52976, _mut52977, _mut52978, _mut52979); k++) {
                    br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289");
                    final long shift = grayCode >> (AOR_minus(k, 1, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52962, _mut52963, _mut52964, _mut52965));
                    if (ROR_equals(shift, 0, "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52966, _mut52967, _mut52968, _mut52969, _mut52970)) {
                        // stop, as all remaining bits will be zero
                        break;
                    }
                    // the k-th bit of i
                    final long ik = shift & 1;
                    result ^= AOR_multiply(ik, direction[j][k], "org.apache.commons.math3.random.SobolSequenceGenerator.skipTo_289", _mut52971, _mut52972, _mut52973, _mut52974);
                }
                x[j] = result;
            }
        }
        count = index;
        return nextVector();
    }

    /**
     * Returns the index i of the next point in the Sobol sequence that will be returned
     * by calling {@link #nextVector()}.
     *
     * @return the index of the next point
     */
    public int getNextIndex() {
        return count;
    }
}
