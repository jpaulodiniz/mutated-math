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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * <p>Represents an <a href="http://http://en.wikipedia.org/wiki/Empirical_distribution_function">
 * empirical probability distribution</a> -- a probability distribution derived
 * from observed data without making any assumptions about the functional form
 * of the population distribution that the data come from.</p>
 *
 * <p>An <code>EmpiricalDistribution</code> maintains data structures, called
 * <i>distribution digests</i>, that describe empirical distributions and
 * support the following operations: <ul>
 * <li>loading the distribution from a file of observed data values</li>
 * <li>dividing the input data into "bin ranges" and reporting bin frequency
 *     counts (data for histogram)</li>
 * <li>reporting univariate statistics describing the full set of data values
 *     as well as the observations within each bin</li>
 * <li>generating random values from the distribution</li>
 * </ul>
 * Applications can use <code>EmpiricalDistribution</code> to build grouped
 * frequency histograms representing the input data or to generate random values
 * "like" those in the input file -- i.e., the values generated will follow the
 * distribution of the values in the file.</p>
 *
 * <p>The implementation uses what amounts to the
 * <a href="http://nedwww.ipac.caltech.edu/level5/March02/Silverman/Silver2_6.html">
 * Variable Kernel Method</a> with Gaussian smoothing:<p>
 * <strong>Digesting the input file</strong>
 * <ol><li>Pass the file once to compute min and max.</li>
 * <li>Divide the range from min-max into <code>binCount</code> "bins."</li>
 * <li>Pass the data file again, computing bin counts and univariate
 *     statistics (mean, std dev.) for each of the bins </li>
 * <li>Divide the interval (0,1) into subintervals associated with the bins,
 *     with the length of a bin's subinterval proportional to its count.</li></ol>
 * <strong>Generating random values from the distribution</strong><ol>
 * <li>Generate a uniformly distributed value in (0,1) </li>
 * <li>Select the subinterval to which the value belongs.
 * <li>Generate a random Gaussian value with mean = mean of the associated
 *     bin and std dev = std dev of associated bin.</li></ol></p>
 *
 * <p>EmpiricalDistribution implements the {@link RealDistribution} interface
 * as follows.  Given x within the range of values in the dataset, let B
 * be the bin containing x and let K be the within-bin kernel for B.  Let P(B-)
 * be the sum of the probabilities of the bins below B and let K(B) be the
 * mass of B under K (i.e., the integral of the kernel density over B).  Then
 * set P(X < x) = P(B-) + P(B) * K(x) / K(B) where K(x) is the kernel distribution
 * evaluated at x. This results in a cdf that matches the grouped frequency
 * distribution at the bin endpoints and interpolates within bins using
 * within-bin kernels.</p>
 *
 *<strong>USAGE NOTES:</strong><ul>
 *<li>The <code>binCount</code> is set by default to 1000.  A good rule of thumb
 *    is to set the bin count to approximately the length of the input file divided
 *    by 10. </li>
 *<li>The input file <i>must</i> be a plain text file containing one valid numeric
 *    entry per line.</li>
 * </ul></p>
 */
public class EmpiricalDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut51910 = false, _mut51911 = false, _mut51912 = false, _mut51913 = false, _mut51914 = false, _mut51915 = false, _mut51916 = false, _mut51917 = false, _mut51918 = false, _mut51919 = false, _mut51920 = false, _mut51921 = false, _mut51922 = false, _mut51923 = false, _mut51924 = false, _mut51925 = false, _mut51926 = false, _mut51927 = false, _mut51928 = false, _mut51929 = false, _mut51930 = false, _mut51931 = false, _mut51932 = false, _mut51933 = false, _mut51934 = false, _mut51935 = false, _mut51936 = false, _mut51937 = false, _mut51938 = false, _mut51939 = false, _mut51940 = false, _mut51941 = false, _mut51942 = false, _mut51943 = false, _mut51944 = false, _mut51945 = false, _mut51946 = false, _mut51947 = false, _mut51948 = false, _mut51949 = false, _mut51950 = false, _mut51951 = false, _mut51952 = false, _mut51953 = false, _mut51954 = false, _mut51955 = false, _mut51956 = false, _mut51957 = false, _mut51958 = false, _mut51959 = false, _mut51960 = false, _mut51961 = false, _mut51962 = false, _mut51963 = false, _mut51964 = false, _mut51965 = false, _mut51966 = false, _mut51967 = false, _mut51968 = false, _mut51969 = false, _mut51970 = false, _mut51971 = false, _mut51972 = false, _mut51973 = false, _mut51974 = false, _mut51975 = false, _mut51976 = false, _mut51977 = false, _mut51978 = false, _mut51979 = false, _mut51980 = false, _mut51981 = false, _mut51982 = false, _mut51983 = false, _mut51984 = false, _mut51985 = false, _mut51986 = false, _mut51987 = false, _mut51988 = false, _mut51989 = false, _mut51990 = false, _mut51991 = false, _mut51992 = false, _mut51993 = false, _mut51994 = false, _mut51995 = false, _mut51996 = false, _mut51997 = false, _mut51998 = false, _mut51999 = false, _mut52000 = false, _mut52001 = false, _mut52002 = false, _mut52003 = false, _mut52004 = false, _mut52005 = false, _mut52006 = false, _mut52007 = false, _mut52008 = false, _mut52009 = false, _mut52010 = false, _mut52011 = false, _mut52012 = false, _mut52013 = false, _mut52014 = false, _mut52015 = false, _mut52016 = false, _mut52017 = false, _mut52018 = false, _mut52019 = false, _mut52020 = false, _mut52021 = false, _mut52022 = false, _mut52023 = false, _mut52024 = false, _mut52025 = false, _mut52026 = false, _mut52027 = false, _mut52028 = false, _mut52029 = false, _mut52030 = false, _mut52031 = false, _mut52032 = false, _mut52033 = false, _mut52034 = false, _mut52035 = false, _mut52036 = false, _mut52037 = false, _mut52038 = false, _mut52039 = false, _mut52040 = false, _mut52041 = false, _mut52042 = false, _mut52043 = false, _mut52044 = false, _mut52045 = false, _mut52046 = false, _mut52047 = false, _mut52048 = false, _mut52049 = false, _mut52050 = false, _mut52051 = false, _mut52052 = false, _mut52053 = false, _mut52054 = false, _mut52055 = false, _mut52056 = false, _mut52057 = false, _mut52058 = false, _mut52059 = false, _mut52060 = false, _mut52061 = false, _mut52062 = false, _mut52063 = false, _mut52064 = false, _mut52065 = false, _mut52066 = false, _mut52067 = false, _mut52068 = false, _mut52069 = false, _mut52070 = false, _mut52071 = false, _mut52072 = false, _mut52073 = false, _mut52074 = false, _mut52075 = false, _mut52076 = false, _mut52077 = false, _mut52078 = false, _mut52079 = false, _mut52080 = false, _mut52081 = false, _mut52082 = false, _mut52083 = false, _mut52084 = false, _mut52085 = false, _mut52086 = false, _mut52087 = false, _mut52088 = false, _mut52089 = false, _mut52090 = false, _mut52091 = false, _mut52092 = false, _mut52093 = false, _mut52094 = false, _mut52095 = false, _mut52096 = false, _mut52097 = false, _mut52098 = false, _mut52099 = false, _mut52100 = false, _mut52101 = false, _mut52102 = false, _mut52103 = false, _mut52104 = false, _mut52105 = false, _mut52106 = false, _mut52107 = false, _mut52108 = false, _mut52109 = false, _mut52110 = false, _mut52111 = false, _mut52112 = false, _mut52113 = false, _mut52114 = false, _mut52115 = false, _mut52116 = false, _mut52117 = false, _mut52118 = false, _mut52119 = false, _mut52120 = false, _mut52121 = false, _mut52122 = false, _mut52123 = false, _mut52124 = false, _mut52125 = false, _mut52126 = false, _mut52127 = false, _mut52128 = false, _mut52129 = false, _mut52130 = false, _mut52131 = false, _mut52132 = false, _mut52133 = false, _mut52134 = false, _mut52135 = false, _mut52136 = false, _mut52137 = false, _mut52138 = false, _mut52139 = false, _mut52140 = false, _mut52141 = false, _mut52142 = false, _mut52143 = false, _mut52144 = false, _mut52145 = false, _mut52146 = false, _mut52147 = false, _mut52148 = false, _mut52149 = false, _mut52150 = false, _mut52151 = false, _mut52152 = false, _mut52153 = false, _mut52154 = false, _mut52155 = false, _mut52156 = false, _mut52157 = false, _mut52158 = false, _mut52159 = false, _mut52160 = false, _mut52161 = false, _mut52162 = false, _mut52163 = false, _mut52164 = false, _mut52165 = false, _mut52166 = false, _mut52167 = false, _mut52168 = false, _mut52169 = false, _mut52170 = false, _mut52171 = false, _mut52172 = false, _mut52173 = false;

    /**
     * Default bin count
     */
    public static final int DEFAULT_BIN_COUNT = 1000;

    /**
     * Character set for file input
     */
    private static final String FILE_CHARSET = "US-ASCII";

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = 5729073523949762654L;

    /**
     * RandomDataGenerator instance to use in repeated calls to getNext()
     */
    protected final RandomDataGenerator randomData;

    /**
     * List of SummaryStatistics objects characterizing the bins
     */
    private final List<SummaryStatistics> binStats;

    /**
     * Sample statistics
     */
    private SummaryStatistics sampleStats = null;

    /**
     * Max loaded value
     */
    private double max = Double.NEGATIVE_INFINITY;

    /**
     * Min loaded value
     */
    private double min = Double.POSITIVE_INFINITY;

    /**
     * Grid size
     */
    private double delta = 0d;

    /**
     * number of bins
     */
    private final int binCount;

    /**
     * is the distribution loaded?
     */
    private boolean loaded = false;

    /**
     * upper bounds of subintervals in (0,1) "belonging" to the bins
     */
    private double[] upperBounds = null;

    /**
     * Creates a new EmpiricalDistribution with the default bin count.
     */
    public EmpiricalDistribution() {
        this(DEFAULT_BIN_COUNT);
    }

    /**
     * Creates a new EmpiricalDistribution with the specified bin count.
     *
     * @param binCount number of bins. Must be strictly positive.
     * @throws NotStrictlyPositiveException if {@code binCount <= 0}.
     */
    public EmpiricalDistribution(int binCount) {
        this(binCount, new RandomDataGenerator());
    }

    /**
     * Creates a new EmpiricalDistribution with the specified bin count using the
     * provided {@link RandomGenerator} as the source of random data.
     *
     * @param binCount number of bins. Must be strictly positive.
     * @param generator random data generator (may be null, resulting in default JDK generator)
     * @throws NotStrictlyPositiveException if {@code binCount <= 0}.
     * @since 3.0
     */
    public EmpiricalDistribution(int binCount, RandomGenerator generator) {
        this(binCount, new RandomDataGenerator(generator));
    }

    /**
     * Creates a new EmpiricalDistribution with default bin count using the
     * provided {@link RandomGenerator} as the source of random data.
     *
     * @param generator random data generator (may be null, resulting in default JDK generator)
     * @since 3.0
     */
    public EmpiricalDistribution(RandomGenerator generator) {
        this(DEFAULT_BIN_COUNT, generator);
    }

    /**
     * Creates a new EmpiricalDistribution with the specified bin count using the
     * provided {@link RandomDataImpl} instance as the source of random data.
     *
     * @param binCount number of bins
     * @param randomData random data generator (may be null, resulting in default JDK generator)
     * @since 3.0
     * @deprecated As of 3.1. Please use {@link #EmpiricalDistribution(int,RandomGenerator)} instead.
     */
    @Deprecated
    public EmpiricalDistribution(int binCount, RandomDataImpl randomData) {
        this(binCount, randomData.getDelegate());
    }

    /**
     * Creates a new EmpiricalDistribution with default bin count using the
     * provided {@link RandomDataImpl} as the source of random data.
     *
     * @param randomData random data generator (may be null, resulting in default JDK generator)
     * @since 3.0
     * @deprecated As of 3.1. Please use {@link #EmpiricalDistribution(RandomGenerator)} instead.
     */
    @Deprecated
    public EmpiricalDistribution(RandomDataImpl randomData) {
        this(DEFAULT_BIN_COUNT, randomData);
    }

    /**
     * Private constructor to allow lazy initialisation of the RNG contained
     * in the {@link #randomData} instance variable.
     *
     * @param binCount number of bins. Must be strictly positive.
     * @param randomData Random data generator.
     * @throws NotStrictlyPositiveException if {@code binCount <= 0}.
     */
    private EmpiricalDistribution(int binCount, RandomDataGenerator randomData) {
        super(randomData.getRandomGenerator());
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.EmpiricalDistribution_217");
        if (ROR_less_equals(binCount, 0, "org.apache.commons.math3.random.EmpiricalDistribution.EmpiricalDistribution_217", _mut51910, _mut51911, _mut51912, _mut51913, _mut51914)) {
            throw new NotStrictlyPositiveException(binCount);
        }
        this.binCount = binCount;
        this.randomData = randomData;
        binStats = new ArrayList<SummaryStatistics>();
    }

    /**
     * Computes the empirical distribution from the provided
     * array of numbers.
     *
     * @param in the input data array
     * @exception NullArgumentException if in is null
     */
    public void load(double[] in) throws NullArgumentException {
        DataAdapter da = new ArrayDataAdapter(in);
        try {
            da.computeStats();
            // new adapter for the second pass
            fillBinStats(new ArrayDataAdapter(in));
        } catch (IOException ex) {
            // Can't happen
            throw new MathInternalError();
        }
        loaded = true;
    }

    /**
     * Computes the empirical distribution using data read from a URL.
     *
     * <p>The input file <i>must</i> be an ASCII text file containing one
     * valid numeric entry per line.</p>
     *
     * @param url url of the input file
     *
     * @throws IOException if an IO error occurs
     * @throws NullArgumentException if url is null
     * @throws ZeroException if URL contains no data
     */
    public void load(URL url) throws IOException, NullArgumentException, ZeroException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.load_261");
        MathUtils.checkNotNull(url);
        Charset charset = Charset.forName(FILE_CHARSET);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
        try {
            DataAdapter da = new StreamDataAdapter(in);
            da.computeStats();
            if (ROR_equals(sampleStats.getN(), 0, "org.apache.commons.math3.random.EmpiricalDistribution.load_261", _mut51915, _mut51916, _mut51917, _mut51918, _mut51919)) {
                throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, url);
            }
            // new adapter for the second pass
            in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
            fillBinStats(new StreamDataAdapter(in));
            loaded = true;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Computes the empirical distribution from the input file.
     *
     * <p>The input file <i>must</i> be an ASCII text file containing one
     * valid numeric entry per line.</p>
     *
     * @param file the input file
     * @throws IOException if an IO error occurs
     * @throws NullArgumentException if file is null
     */
    public void load(File file) throws IOException, NullArgumentException {
        MathUtils.checkNotNull(file);
        Charset charset = Charset.forName(FILE_CHARSET);
        InputStream is = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(is, charset));
        try {
            DataAdapter da = new StreamDataAdapter(in);
            da.computeStats();
            // new adapter for second pass
            is = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader(is, charset));
            fillBinStats(new StreamDataAdapter(in));
            loaded = true;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Provides methods for computing <code>sampleStats</code> and
     * <code>beanStats</code> abstracting the source of data.
     */
    private abstract class DataAdapter {

        /**
         * Compute bin stats.
         *
         * @throws IOException  if an error occurs computing bin stats
         */
        public abstract void computeBinStats() throws IOException;

        /**
         * Compute sample statistics.
         *
         * @throws IOException if an error occurs computing sample stats
         */
        public abstract void computeStats() throws IOException;
    }

    /**
     * <code>DataAdapter</code> for data provided through some input stream
     */
    private class StreamDataAdapter extends DataAdapter {

        /**
         * Input stream providing access to the data
         */
        private BufferedReader inputStream;

        /**
         * Create a StreamDataAdapter from a BufferedReader
         *
         * @param in BufferedReader input stream
         */
        StreamDataAdapter(BufferedReader in) {
            super();
            inputStream = in;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void computeBinStats() throws IOException {
            String str = null;
            double val = 0.0d;
            while ((str = inputStream.readLine()) != null) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeBinStats_358");
                val = Double.parseDouble(str);
                SummaryStatistics stats = binStats.get(findBin(val));
                stats.addValue(val);
            }
            inputStream.close();
            inputStream = null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void computeStats() throws IOException {
            String str = null;
            double val = 0.0;
            sampleStats = new SummaryStatistics();
            while ((str = inputStream.readLine()) != null) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeStats_373");
                val = Double.parseDouble(str);
                sampleStats.addValue(val);
            }
            inputStream.close();
            inputStream = null;
        }
    }

    /**
     * <code>DataAdapter</code> for data provided as array of doubles.
     */
    private class ArrayDataAdapter extends DataAdapter {

        /**
         * Array of input  data values
         */
        private double[] inputArray;

        /**
         * Construct an ArrayDataAdapter from a double[] array
         *
         * @param in double[] array holding the data
         * @throws NullArgumentException if in is null
         */
        ArrayDataAdapter(double[] in) throws NullArgumentException {
            super();
            MathUtils.checkNotNull(in);
            inputArray = in;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void computeStats() throws IOException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeStats_408");
            sampleStats = new SummaryStatistics();
            for (int i = 0; ROR_less(i, inputArray.length, "org.apache.commons.math3.random.EmpiricalDistribution.computeStats_408", _mut51920, _mut51921, _mut51922, _mut51923, _mut51924); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeStats_408");
                sampleStats.addValue(inputArray[i]);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void computeBinStats() throws IOException {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeBinStats_417");
            for (int i = 0; ROR_less(i, inputArray.length, "org.apache.commons.math3.random.EmpiricalDistribution.computeBinStats_417", _mut51925, _mut51926, _mut51927, _mut51928, _mut51929); i++) {
                br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.computeBinStats_417");
                SummaryStatistics stats = binStats.get(findBin(inputArray[i]));
                stats.addValue(inputArray[i]);
            }
        }
    }

    /**
     * Fills binStats array (second pass through data file).
     *
     * @param da object providing access to the data
     * @throws IOException  if an IO error occurs
     */
    private void fillBinStats(final DataAdapter da) throws IOException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433");
        // Set up grid
        min = sampleStats.getMin();
        max = sampleStats.getMax();
        delta = AOR_divide((AOR_minus(max, min, "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51930, _mut51931, _mut51932, _mut51933)), ((double) binCount), "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51934, _mut51935, _mut51936, _mut51937);
        // Initialize binStats ArrayList
        if (!binStats.isEmpty()) {
            binStats.clear();
        }
        for (int i = 0; ROR_less(i, binCount, "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51938, _mut51939, _mut51940, _mut51941, _mut51942); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433");
            SummaryStatistics stats = new SummaryStatistics();
            binStats.add(i, stats);
        }
        // Filling data in binStats Array
        da.computeBinStats();
        // Assign upperBounds based on bin counts
        upperBounds = new double[binCount];
        upperBounds[0] = AOR_divide(((double) binStats.get(0).getN()), (double) sampleStats.getN(), "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51943, _mut51944, _mut51945, _mut51946);
        for (int i = 1; ROR_less(i, AOR_minus(binCount, 1, "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51959, _mut51960, _mut51961, _mut51962), "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51963, _mut51964, _mut51965, _mut51966, _mut51967); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433");
            upperBounds[i] = AOR_plus(upperBounds[AOR_minus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51947, _mut51948, _mut51949, _mut51950)], AOR_divide(((double) binStats.get(i).getN()), (double) sampleStats.getN(), "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51951, _mut51952, _mut51953, _mut51954), "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51955, _mut51956, _mut51957, _mut51958);
        }
        upperBounds[AOR_minus(binCount, 1, "org.apache.commons.math3.random.EmpiricalDistribution.fillBinStats_433", _mut51968, _mut51969, _mut51970, _mut51971)] = 1.0d;
    }

    /**
     * Returns the index of the bin to which the given value belongs
     *
     * @param value  the value whose bin we are trying to find
     * @return the index of the bin containing the value
     */
    private int findBin(double value) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.findBin_469");
        return FastMath.min(FastMath.max(AOR_minus((int) FastMath.ceil(AOR_divide((AOR_minus(value, min, "org.apache.commons.math3.random.EmpiricalDistribution.findBin_469", _mut51972, _mut51973, _mut51974, _mut51975)), delta, "org.apache.commons.math3.random.EmpiricalDistribution.findBin_469", _mut51976, _mut51977, _mut51978, _mut51979)), 1, "org.apache.commons.math3.random.EmpiricalDistribution.findBin_469", _mut51980, _mut51981, _mut51982, _mut51983), 0), AOR_minus(binCount, 1, "org.apache.commons.math3.random.EmpiricalDistribution.findBin_469", _mut51984, _mut51985, _mut51986, _mut51987));
    }

    /**
     * Generates a random value from this distribution.
     * <strong>Preconditions:</strong><ul>
     * <li>the distribution must be loaded before invoking this method</li></ul>
     * @return the random value.
     * @throws MathIllegalStateException if the distribution has not been loaded
     */
    public double getNextValue() throws MathIllegalStateException {
        if (!loaded) {
            throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED);
        }
        return sample();
    }

    /**
     * Returns a {@link StatisticalSummary} describing this distribution.
     * <strong>Preconditions:</strong><ul>
     * <li>the distribution must be loaded before invoking this method</li></ul>
     *
     * @return the sample statistics
     * @throws IllegalStateException if the distribution has not been loaded
     */
    public StatisticalSummary getSampleStats() {
        return sampleStats;
    }

    /**
     * Returns the number of bins.
     *
     * @return the number of bins.
     */
    public int getBinCount() {
        return binCount;
    }

    /**
     * Returns a List of {@link SummaryStatistics} instances containing
     * statistics describing the values in each of the bins.  The list is
     * indexed on the bin number.
     *
     * @return List of bin statistics.
     */
    public List<SummaryStatistics> getBinStats() {
        return binStats;
    }

    /**
     * <p>Returns a fresh copy of the array of upper bounds for the bins.
     * Bins are: <br/>
     * [min,upperBounds[0]],(upperBounds[0],upperBounds[1]],...,
     *  (upperBounds[binCount-2], upperBounds[binCount-1] = max].</p>
     *
     * <p>Note: In versions 1.0-2.0 of commons-math, this method
     * incorrectly returned the array of probability generator upper
     * bounds now returned by {@link #getGeneratorUpperBounds()}.</p>
     *
     * @return array of bin upper bounds
     * @since 2.1
     */
    public double[] getUpperBounds() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536");
        double[] binUpperBounds = new double[binCount];
        for (int i = 0; ROR_less(i, AOR_minus(binCount, 1, "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut52000, _mut52001, _mut52002, _mut52003), "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut52004, _mut52005, _mut52006, _mut52007, _mut52008); i++) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536");
            binUpperBounds[i] = AOR_plus(min, AOR_multiply(delta, (AOR_plus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut51988, _mut51989, _mut51990, _mut51991)), "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut51992, _mut51993, _mut51994, _mut51995), "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut51996, _mut51997, _mut51998, _mut51999);
        }
        binUpperBounds[AOR_minus(binCount, 1, "org.apache.commons.math3.random.EmpiricalDistribution.getUpperBounds_536", _mut52009, _mut52010, _mut52011, _mut52012)] = max;
        return binUpperBounds;
    }

    /**
     * <p>Returns a fresh copy of the array of upper bounds of the subintervals
     * of [0,1] used in generating data from the empirical distribution.
     * Subintervals correspond to bins with lengths proportional to bin counts.</p>
     *
     * <strong>Preconditions:</strong><ul>
     * <li>the distribution must be loaded before invoking this method</li></ul>
     *
     * <p>In versions 1.0-2.0 of commons-math, this array was (incorrectly) returned
     * by {@link #getUpperBounds()}.</p>
     *
     * @since 2.1
     * @return array of upper bounds of subintervals used in data generation
     * @throws NullPointerException unless a {@code load} method has been
     * called beforehand.
     */
    public double[] getGeneratorUpperBounds() {
        int len = upperBounds.length;
        double[] out = new double[len];
        System.arraycopy(upperBounds, 0, out, 0, len);
        return out;
    }

    /**
     * Property indicating whether or not the distribution has been loaded.
     *
     * @return true if the distribution has been loaded
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Reseeds the random number generator used by {@link #getNextValue()}.
     *
     * @param seed random generator seed
     * @since 3.0
     */
    public void reSeed(long seed) {
        randomData.reSeed(seed);
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    @Override
    public double probability(double x) {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Returns the kernel density normalized so that its integral over each bin
     * equals the bin mass.</p>
     *
     * <p>Algorithm description: <ol>
     * <li>Find the bin B that x belongs to.</li>
     * <li>Compute K(B) = the mass of B with respect to the within-bin kernel (i.e., the
     * integral of the kernel density over B).</li>
     * <li>Return k(x) * P(B) / K(B), where k is the within-bin kernel density
     * and P(B) is the mass of B.</li></ol></p>
     * @since 3.1
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.density_612");
        if ((_mut52023 ? (ROR_less(x, min, "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52013, _mut52014, _mut52015, _mut52016, _mut52017) && ROR_greater(x, max, "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52018, _mut52019, _mut52020, _mut52021, _mut52022)) : (ROR_less(x, min, "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52013, _mut52014, _mut52015, _mut52016, _mut52017) || ROR_greater(x, max, "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52018, _mut52019, _mut52020, _mut52021, _mut52022)))) {
            return 0d;
        }
        final int binIndex = findBin(x);
        final RealDistribution kernel = getKernel(binStats.get(binIndex));
        return AOR_divide(AOR_multiply(kernel.density(x), pB(binIndex), "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52024, _mut52025, _mut52026, _mut52027), kB(binIndex), "org.apache.commons.math3.random.EmpiricalDistribution.density_612", _mut52028, _mut52029, _mut52030, _mut52031);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Algorithm description:<ol>
     * <li>Find the bin B that x belongs to.</li>
     * <li>Compute P(B) = the mass of B and P(B-) = the combined mass of the bins below B.</li>
     * <li>Compute K(B) = the probability mass of B with respect to the within-bin kernel
     * and K(B-) = the kernel distribution evaluated at the lower endpoint of B</li>
     * <li>Return P(B-) + P(B) * [K(x) - K(B-)] / K(B) where
     * K(x) is the within-bin kernel distribution function evaluated at x.</li></ol>
     * If K is a constant distribution, we return P(B-) + P(B) (counting the full
     * mass of B).</p>
     *
     * @since 3.1
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636");
        if (ROR_less(x, min, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52032, _mut52033, _mut52034, _mut52035, _mut52036)) {
            return 0d;
        } else if (ROR_greater_equals(x, max, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52037, _mut52038, _mut52039, _mut52040, _mut52041)) {
            return 1d;
        }
        final int binIndex = findBin(x);
        final double pBminus = pBminus(binIndex);
        final double pB = pB(binIndex);
        final RealDistribution kernel = k(x);
        if (kernel instanceof ConstantRealDistribution) {
            if (ROR_less(x, kernel.getNumericalMean(), "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52042, _mut52043, _mut52044, _mut52045, _mut52046)) {
                return pBminus;
            } else {
                return AOR_plus(pBminus, pB, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52047, _mut52048, _mut52049, _mut52050);
            }
        }
        final double[] binBounds = getUpperBounds();
        final double kB = kB(binIndex);
        final double lower = ROR_equals(binIndex, 0, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52051, _mut52052, _mut52053, _mut52054, _mut52055) ? min : binBounds[AOR_minus(binIndex, 1, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52056, _mut52057, _mut52058, _mut52059)];
        final double withinBinCum = AOR_divide((AOR_minus(kernel.cumulativeProbability(x), kernel.cumulativeProbability(lower), "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52060, _mut52061, _mut52062, _mut52063)), kB, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52064, _mut52065, _mut52066, _mut52067);
        return AOR_plus(pBminus, AOR_multiply(pB, withinBinCum, "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52068, _mut52069, _mut52070, _mut52071), "org.apache.commons.math3.random.EmpiricalDistribution.cumulativeProbability_636", _mut52072, _mut52073, _mut52074, _mut52075);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Algorithm description:<ol>
     * <li>Find the smallest i such that the sum of the masses of the bins
     *  through i is at least p.</li>
     * <li>
     *   Let K be the within-bin kernel distribution for bin i.</br>
     *   Let K(B) be the mass of B under K. <br/>
     *   Let K(B-) be K evaluated at the lower endpoint of B (the combined
     *   mass of the bins below B under K).<br/>
     *   Let P(B) be the probability of bin i.<br/>
     *   Let P(B-) be the sum of the bin masses below bin i. <br/>
     *   Let pCrit = p - P(B-)<br/>
     * <li>Return the inverse of K evaluated at <br/>
     *    K(B-) + pCrit * K(B) / P(B) </li>
     *  </ol></p>
     *
     * @since 3.1
     */
    @Override
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681");
        if ((_mut52086 ? (ROR_less(p, 0.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52076, _mut52077, _mut52078, _mut52079, _mut52080) && ROR_greater(p, 1.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52081, _mut52082, _mut52083, _mut52084, _mut52085)) : (ROR_less(p, 0.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52076, _mut52077, _mut52078, _mut52079, _mut52080) || ROR_greater(p, 1.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52081, _mut52082, _mut52083, _mut52084, _mut52085)))) {
            throw new OutOfRangeException(p, 0, 1);
        }
        if (ROR_equals(p, 0.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52087, _mut52088, _mut52089, _mut52090, _mut52091)) {
            return getSupportLowerBound();
        }
        if (ROR_equals(p, 1.0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52092, _mut52093, _mut52094, _mut52095, _mut52096)) {
            return getSupportUpperBound();
        }
        int i = 0;
        while (ROR_less(cumBinP(i), p, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52097, _mut52098, _mut52099, _mut52100, _mut52101)) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681");
            i++;
        }
        final RealDistribution kernel = getKernel(binStats.get(i));
        final double kB = kB(i);
        final double[] binBounds = getUpperBounds();
        final double lower = ROR_equals(i, 0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52102, _mut52103, _mut52104, _mut52105, _mut52106) ? min : binBounds[AOR_minus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52107, _mut52108, _mut52109, _mut52110)];
        final double kBminus = kernel.cumulativeProbability(lower);
        final double pB = pB(i);
        final double pBminus = pBminus(i);
        final double pCrit = AOR_minus(p, pBminus, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52111, _mut52112, _mut52113, _mut52114);
        if (ROR_less_equals(pCrit, 0, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52115, _mut52116, _mut52117, _mut52118, _mut52119)) {
            return lower;
        }
        return kernel.inverseCumulativeProbability(AOR_plus(kBminus, AOR_divide(AOR_multiply(pCrit, kB, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52120, _mut52121, _mut52122, _mut52123), pB, "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52124, _mut52125, _mut52126, _mut52127), "org.apache.commons.math3.random.EmpiricalDistribution.inverseCumulativeProbability_681", _mut52128, _mut52129, _mut52130, _mut52131));
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public double getNumericalMean() {
        return sampleStats.getMean();
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public double getNumericalVariance() {
        return sampleStats.getVariance();
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public double getSupportLowerBound() {
        return min;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public double getSupportUpperBound() {
        return max;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    public boolean isSupportConnected() {
        return true;
    }

    /**
     * {@inheritDoc}
     * @since 3.1
     */
    @Override
    public void reseedRandomGenerator(long seed) {
        randomData.reSeed(seed);
    }

    /**
     * The probability of bin i.
     *
     * @param i the index of the bin
     * @return the probability that selection begins in bin i
     */
    private double pB(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.pB_785");
        return ROR_equals(i, 0, "org.apache.commons.math3.random.EmpiricalDistribution.pB_785", _mut52132, _mut52133, _mut52134, _mut52135, _mut52136) ? upperBounds[0] : AOR_minus(upperBounds[i], upperBounds[AOR_minus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.pB_785", _mut52137, _mut52138, _mut52139, _mut52140)], "org.apache.commons.math3.random.EmpiricalDistribution.pB_785", _mut52141, _mut52142, _mut52143, _mut52144);
    }

    /**
     * The combined probability of the bins up to but not including bin i.
     *
     * @param i the index of the bin
     * @return the probability that selection begins in a bin below bin i.
     */
    private double pBminus(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.pBminus_796");
        return ROR_equals(i, 0, "org.apache.commons.math3.random.EmpiricalDistribution.pBminus_796", _mut52145, _mut52146, _mut52147, _mut52148, _mut52149) ? 0 : upperBounds[AOR_minus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.pBminus_796", _mut52150, _mut52151, _mut52152, _mut52153)];
    }

    /**
     * Mass of bin i under the within-bin kernel of the bin.
     *
     * @param i index of the bin
     * @return the difference in the within-bin kernel cdf between the
     * upper and lower endpoints of bin i
     */
    @SuppressWarnings("deprecation")
    private double kB(int i) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.kB_807");
        final double[] binBounds = getUpperBounds();
        final RealDistribution kernel = getKernel(binStats.get(i));
        return ROR_equals(i, 0, "org.apache.commons.math3.random.EmpiricalDistribution.kB_807", _mut52154, _mut52155, _mut52156, _mut52157, _mut52158) ? kernel.cumulativeProbability(min, binBounds[0]) : kernel.cumulativeProbability(binBounds[AOR_minus(i, 1, "org.apache.commons.math3.random.EmpiricalDistribution.kB_807", _mut52159, _mut52160, _mut52161, _mut52162)], binBounds[i]);
    }

    /**
     * The within-bin kernel of the bin that x belongs to.
     *
     * @param x the value to locate within a bin
     * @return the within-bin kernel of the bin containing x
     */
    private RealDistribution k(double x) {
        final int binIndex = findBin(x);
        return getKernel(binStats.get(binIndex));
    }

    /**
     * The combined probability of the bins up to and including binIndex.
     *
     * @param binIndex maximum bin index
     * @return sum of the probabilities of bins through binIndex
     */
    private double cumBinP(int binIndex) {
        return upperBounds[binIndex];
    }

    /**
     * The within-bin smoothing kernel. Returns a Gaussian distribution
     * parameterized by {@code bStats}, unless the bin contains only one
     * observation, in which case a constant distribution is returned.
     *
     * @param bStats summary statistics for the bin
     * @return within-bin kernel parameterized by bStats
     */
    protected RealDistribution getKernel(SummaryStatistics bStats) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.random.EmpiricalDistribution.getKernel_844");
        if ((_mut52173 ? (ROR_equals(bStats.getN(), 1, "org.apache.commons.math3.random.EmpiricalDistribution.getKernel_844", _mut52163, _mut52164, _mut52165, _mut52166, _mut52167) && ROR_equals(bStats.getVariance(), 0, "org.apache.commons.math3.random.EmpiricalDistribution.getKernel_844", _mut52168, _mut52169, _mut52170, _mut52171, _mut52172)) : (ROR_equals(bStats.getN(), 1, "org.apache.commons.math3.random.EmpiricalDistribution.getKernel_844", _mut52163, _mut52164, _mut52165, _mut52166, _mut52167) || ROR_equals(bStats.getVariance(), 0, "org.apache.commons.math3.random.EmpiricalDistribution.getKernel_844", _mut52168, _mut52169, _mut52170, _mut52171, _mut52172)))) {
            return new ConstantRealDistribution(bStats.getMean());
        } else {
            return new NormalDistribution(randomData.getRandomGenerator(), bStats.getMean(), bStats.getStandardDeviation(), NormalDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
        }
    }
}
