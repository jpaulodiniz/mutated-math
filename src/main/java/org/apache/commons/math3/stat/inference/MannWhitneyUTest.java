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

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * An implementation of the Mann-Whitney U test (also called Wilcoxon rank-sum test).
 */
public class MannWhitneyUTest {

    @Conditional
    public static boolean _mut5305 = false, _mut5306 = false, _mut5307 = false, _mut5308 = false, _mut5309 = false, _mut5310 = false, _mut5311 = false, _mut5312 = false, _mut5313 = false, _mut5314 = false, _mut5315 = false, _mut5316 = false, _mut5317 = false, _mut5318 = false, _mut5319 = false, _mut5320 = false, _mut5321 = false, _mut5322 = false, _mut5323 = false, _mut5324 = false, _mut5325 = false, _mut5326 = false, _mut5327 = false, _mut5328 = false, _mut5329 = false, _mut5330 = false, _mut5331 = false, _mut5332 = false, _mut5333 = false, _mut5334 = false, _mut5335 = false, _mut5336 = false, _mut5337 = false, _mut5338 = false, _mut5339 = false, _mut5340 = false, _mut5341 = false, _mut5342 = false, _mut5343 = false, _mut5344 = false, _mut5345 = false, _mut5346 = false, _mut5347 = false, _mut5348 = false, _mut5349 = false, _mut5350 = false, _mut5351 = false, _mut5352 = false, _mut5353 = false, _mut5354 = false, _mut5355 = false, _mut5356 = false, _mut5357 = false, _mut5358 = false, _mut5359 = false, _mut5360 = false, _mut5361 = false, _mut5362 = false, _mut5363 = false, _mut5364 = false, _mut5365 = false, _mut5366 = false, _mut5367 = false, _mut5368 = false, _mut5369 = false, _mut5370 = false, _mut5371 = false, _mut5372 = false, _mut5373 = false, _mut5374 = false, _mut5375 = false, _mut5376 = false, _mut5377 = false, _mut5378 = false, _mut5379 = false, _mut5380 = false, _mut5381 = false, _mut5382 = false, _mut5383 = false, _mut5384 = false, _mut5385 = false, _mut5386 = false, _mut5387 = false, _mut5388 = false, _mut5389 = false, _mut5390 = false, _mut5391 = false, _mut5392 = false, _mut5393 = false;

    /**
     * Ranking algorithm.
     */
    private NaturalRanking naturalRanking;

    /**
     * Create a test instance using where NaN's are left in place and ties get
     * the average of applicable ranks. Use this unless you are very sure of
     * what you are doing.
     */
    public MannWhitneyUTest() {
        naturalRanking = new NaturalRanking(NaNStrategy.FIXED, TiesStrategy.AVERAGE);
    }

    /**
     * Create a test instance using the given strategies for NaN's and ties.
     * Only use this if you are sure of what you are doing.
     *
     * @param nanStrategy
     *            specifies the strategy that should be used for Double.NaN's
     * @param tiesStrategy
     *            specifies the strategy that should be used for ties
     */
    public MannWhitneyUTest(final NaNStrategy nanStrategy, final TiesStrategy tiesStrategy) {
        naturalRanking = new NaturalRanking(nanStrategy, tiesStrategy);
    }

    /**
     * Ensures that the provided arrays fulfills the assumptions.
     *
     * @param x first sample
     * @param y second sample
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     */
    private void ensureDataConformance(final double[] x, final double[] y) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.ensureDataConformance_70");
        if ((_mut5305 ? (x == null && y == null) : (x == null || y == null))) {
            throw new NullArgumentException();
        }
        if ((_mut5316 ? (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.ensureDataConformance_70", _mut5306, _mut5307, _mut5308, _mut5309, _mut5310) && ROR_equals(y.length, 0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.ensureDataConformance_70", _mut5311, _mut5312, _mut5313, _mut5314, _mut5315)) : (ROR_equals(x.length, 0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.ensureDataConformance_70", _mut5306, _mut5307, _mut5308, _mut5309, _mut5310) || ROR_equals(y.length, 0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.ensureDataConformance_70", _mut5311, _mut5312, _mut5313, _mut5314, _mut5315)))) {
            throw new NoDataException();
        }
    }

    /**
     * Concatenate the samples into one array.
     * @param x first sample
     * @param y second sample
     * @return concatenated array
     */
    private double[] concatenateSamples(final double[] x, final double[] y) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.concatenateSamples_88");
        final double[] z = new double[AOR_plus(x.length, y.length, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.concatenateSamples_88", _mut5317, _mut5318, _mut5319, _mut5320)];
        System.arraycopy(x, 0, z, 0, x.length);
        System.arraycopy(y, 0, z, x.length, y.length);
        return z;
    }

    /**
     * Computes the <a
     * href="http://en.wikipedia.org/wiki/Mann%E2%80%93Whitney_U"> Mann-Whitney
     * U statistic</a> comparing mean for two independent samples possibly of
     * different length.
     * <p>
     * This statistic can be used to perform a Mann-Whitney U test evaluating
     * the null hypothesis that the two independent samples has equal mean.
     * </p>
     * <p>
     * Let X<sub>i</sub> denote the i'th individual of the first sample and
     * Y<sub>j</sub> the j'th individual in the second sample. Note that the
     * samples would often have different length.
     * </p>
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>All observations in the two samples are independent.</li>
     * <li>The observations are at least ordinal (continuous are also ordinal).</li>
     * </ul>
     * </p>
     *
     * @param x the first sample
     * @param y the second sample
     * @return Mann-Whitney U statistic (maximum of U<sup>x</sup> and U<sup>y</sup>)
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     */
    public double mannWhitneyU(final double[] x, final double[] y) throws NullArgumentException, NoDataException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125");
        ensureDataConformance(x, y);
        final double[] z = concatenateSamples(x, y);
        final double[] ranks = naturalRanking.rank(z);
        double sumRankX = 0;
        /*
         * The ranks for x is in the first x.length entries in ranks because x
         * is in the first x.length entries in z
         */
        for (int i = 0; ROR_less(i, x.length, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5321, _mut5322, _mut5323, _mut5324, _mut5325); ++i) {
            br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125");
            sumRankX += ranks[i];
        }
        /*
         * U1 = R1 - (n1 * (n1 + 1)) / 2 where R1 is sum of ranks for sample 1,
         * e.g. x, n1 is the number of observations in sample 1.
         */
        final double U1 = AOR_minus(sumRankX, AOR_divide((AOR_multiply((long) x.length, (AOR_plus(x.length, 1, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5326, _mut5327, _mut5328, _mut5329)), "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5330, _mut5331, _mut5332, _mut5333)), 2, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5334, _mut5335, _mut5336, _mut5337), "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5338, _mut5339, _mut5340, _mut5341);
        /*
         * It can be shown that U1 + U2 = n1 * n2
         */
        final double U2 = AOR_minus(AOR_multiply((long) x.length, y.length, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5342, _mut5343, _mut5344, _mut5345), U1, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyU_125", _mut5346, _mut5347, _mut5348, _mut5349);
        return FastMath.max(U1, U2);
    }

    /**
     * @param Umin smallest Mann-Whitney U value
     * @param n1 number of subjects in first sample
     * @param n2 number of subjects in second sample
     * @return two-sided asymptotic p-value
     * @throws ConvergenceException if the p-value can not be computed
     * due to a convergence error
     * @throws MaxCountExceededException if the maximum number of
     * iterations is exceeded
     */
    private double calculateAsymptoticPValue(final double Umin, final int n1, final int n2) throws ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167");
        /* long multiplication to avoid overflow (double not used due to efficiency
         * and to avoid precision loss)
         */
        final long n1n2prod = AOR_multiply((long) n1, n2, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5350, _mut5351, _mut5352, _mut5353);
        // http://en.wikipedia.org/wiki/Mann%E2%80%93Whitney_U#Normal_approximation
        final double EU = AOR_divide(n1n2prod, 2.0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5354, _mut5355, _mut5356, _mut5357);
        final double VarU = AOR_divide(AOR_multiply(n1n2prod, (AOR_plus(AOR_plus(n1, n2, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5358, _mut5359, _mut5360, _mut5361), 1, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5362, _mut5363, _mut5364, _mut5365)), "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5366, _mut5367, _mut5368, _mut5369), 12.0, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5370, _mut5371, _mut5372, _mut5373);
        final double z = AOR_divide((AOR_minus(Umin, EU, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5374, _mut5375, _mut5376, _mut5377)), FastMath.sqrt(VarU), "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5378, _mut5379, _mut5380, _mut5381);
        // pass a null rng to avoid unneeded overhead as we will not sample from this distribution
        final NormalDistribution standardNormal = new NormalDistribution(null, 0, 1);
        return AOR_multiply(2, standardNormal.cumulativeProbability(z), "org.apache.commons.math3.stat.inference.MannWhitneyUTest.calculateAsymptoticPValue_167", _mut5382, _mut5383, _mut5384, _mut5385);
    }

    /**
     * Returns the asymptotic <i>observed significance level</i>, or <a href=
     * "http://www.cas.lancs.ac.uk/glossary_v1.1/hyptest.html#pvalue">
     * p-value</a>, associated with a <a
     * href="http://en.wikipedia.org/wiki/Mann%E2%80%93Whitney_U"> Mann-Whitney
     * U statistic</a> comparing mean for two independent samples.
     * <p>
     * Let X<sub>i</sub> denote the i'th individual of the first sample and
     * Y<sub>j</sub> the j'th individual in the second sample. Note that the
     * samples would often have different length.
     * </p>
     * <p>
     * <strong>Preconditions</strong>:
     * <ul>
     * <li>All observations in the two samples are independent.</li>
     * <li>The observations are at least ordinal (continuous are also ordinal).</li>
     * </ul>
     * </p><p>
     * Ties give rise to biased variance at the moment. See e.g. <a
     * href="http://mlsc.lboro.ac.uk/resources/statistics/Mannwhitney.pdf"
     * >http://mlsc.lboro.ac.uk/resources/statistics/Mannwhitney.pdf</a>.</p>
     *
     * @param x the first sample
     * @param y the second sample
     * @return asymptotic p-value
     * @throws NullArgumentException if {@code x} or {@code y} are {@code null}.
     * @throws NoDataException if {@code x} or {@code y} are zero-length.
     * @throws ConvergenceException if the p-value can not be computed due to a
     * convergence error
     * @throws MaxCountExceededException if the maximum number of iterations
     * is exceeded
     */
    public double mannWhitneyUTest(final double[] x, final double[] y) throws NullArgumentException, NoDataException, ConvergenceException, MaxCountExceededException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyUTest_222");
        ensureDataConformance(x, y);
        final double Umax = mannWhitneyU(x, y);
        /*
         * It can be shown that U1 + U2 = n1 * n2
         */
        final double Umin = AOR_minus(AOR_multiply((long) x.length, y.length, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyUTest_222", _mut5386, _mut5387, _mut5388, _mut5389), Umax, "org.apache.commons.math3.stat.inference.MannWhitneyUTest.mannWhitneyUTest_222", _mut5390, _mut5391, _mut5392, _mut5393);
        return calculateAsymptoticPValue(Umin, x.length, y.length);
    }
}
