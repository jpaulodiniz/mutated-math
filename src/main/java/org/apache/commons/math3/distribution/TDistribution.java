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

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of Student's t-distribution.
 *
 * @see "<a href='http://en.wikipedia.org/wiki/Student&apos;s_t-distribution'>Student's t-distribution (Wikipedia)</a>"
 * @see "<a href='http://mathworld.wolfram.com/Studentst-Distribution.html'>Student's t-distribution (MathWorld)</a>"
 */
public class TDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut55886 = false, _mut55887 = false, _mut55888 = false, _mut55889 = false, _mut55890 = false, _mut55891 = false, _mut55892 = false, _mut55893 = false, _mut55894 = false, _mut55895 = false, _mut55896 = false, _mut55897 = false, _mut55898 = false, _mut55899 = false, _mut55900 = false, _mut55901 = false, _mut55902 = false, _mut55903 = false, _mut55904 = false, _mut55905 = false, _mut55906 = false, _mut55907 = false, _mut55908 = false, _mut55909 = false, _mut55910 = false, _mut55911 = false, _mut55912 = false, _mut55913 = false, _mut55914 = false, _mut55915 = false, _mut55916 = false, _mut55917 = false, _mut55918 = false, _mut55919 = false, _mut55920 = false, _mut55921 = false, _mut55922 = false, _mut55923 = false, _mut55924 = false, _mut55925 = false, _mut55926 = false, _mut55927 = false, _mut55928 = false, _mut55929 = false, _mut55930 = false, _mut55931 = false, _mut55932 = false, _mut55933 = false, _mut55934 = false, _mut55935 = false, _mut55936 = false, _mut55937 = false, _mut55938 = false, _mut55939 = false, _mut55940 = false, _mut55941 = false, _mut55942 = false, _mut55943 = false, _mut55944 = false, _mut55945 = false, _mut55946 = false, _mut55947 = false, _mut55948 = false, _mut55949 = false, _mut55950 = false, _mut55951 = false, _mut55952 = false, _mut55953 = false, _mut55954 = false, _mut55955 = false, _mut55956 = false, _mut55957 = false, _mut55958 = false, _mut55959 = false, _mut55960 = false, _mut55961 = false, _mut55962 = false, _mut55963 = false, _mut55964 = false, _mut55965 = false, _mut55966 = false, _mut55967 = false, _mut55968 = false, _mut55969 = false, _mut55970 = false, _mut55971 = false, _mut55972 = false, _mut55973 = false, _mut55974 = false, _mut55975 = false, _mut55976 = false, _mut55977 = false, _mut55978 = false, _mut55979 = false, _mut55980 = false, _mut55981 = false, _mut55982 = false, _mut55983 = false, _mut55984 = false, _mut55985 = false, _mut55986 = false, _mut55987 = false, _mut55988 = false, _mut55989 = false, _mut55990 = false, _mut55991 = false, _mut55992 = false, _mut55993 = false, _mut55994 = false, _mut55995 = false, _mut55996 = false, _mut55997 = false, _mut55998 = false, _mut55999 = false, _mut56000 = false, _mut56001 = false, _mut56002 = false, _mut56003 = false, _mut56004 = false, _mut56005 = false, _mut56006 = false, _mut56007 = false, _mut56008 = false, _mut56009 = false, _mut56010 = false, _mut56011 = false, _mut56012 = false, _mut56013 = false;

    /**
     * Default inverse cumulative probability accuracy.
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -5852615386664158222L;

    /**
     * The degrees of freedom.
     */
    private final double degreesOfFreedom;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Static computation factor based on degreesOfFreedom.
     */
    private final double factor;

    /**
     * Create a t distribution using the given degrees of freedom.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param degreesOfFreedom Degrees of freedom.
     * @throws NotStrictlyPositiveException if {@code degreesOfFreedom <= 0}
     */
    public TDistribution(double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(degreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a t distribution using the given degrees of freedom and the
     * specified inverse cumulative probability absolute accuracy.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param degreesOfFreedom Degrees of freedom.
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code degreesOfFreedom <= 0}
     * @since 2.1
     */
    public TDistribution(double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
    }

    /**
     * Creates a t distribution.
     *
     * @param rng Random number generator.
     * @param degreesOfFreedom Degrees of freedom.
     * @throws NotStrictlyPositiveException if {@code degreesOfFreedom <= 0}
     * @since 3.3
     */
    public TDistribution(RandomGenerator rng, double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(rng, degreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Creates a t distribution.
     *
     * @param rng Random number generator.
     * @param degreesOfFreedom Degrees of freedom.
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates
     * (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NotStrictlyPositiveException if {@code degreesOfFreedom <= 0}
     * @since 3.1
     */
    public TDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TDistribution.TDistribution_113");
        if (ROR_less_equals(degreesOfFreedom, 0, "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55886, _mut55887, _mut55888, _mut55889, _mut55890)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, degreesOfFreedom);
        }
        this.degreesOfFreedom = degreesOfFreedom;
        solverAbsoluteAccuracy = inverseCumAccuracy;
        final double n = degreesOfFreedom;
        final double nPlus1Over2 = AOR_divide((AOR_plus(n, 1, "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55891, _mut55892, _mut55893, _mut55894)), 2, "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55895, _mut55896, _mut55897, _mut55898);
        factor = AOR_minus(AOR_minus(Gamma.logGamma(nPlus1Over2), AOR_multiply(0.5, (AOR_plus(FastMath.log(FastMath.PI), FastMath.log(n), "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55899, _mut55900, _mut55901, _mut55902)), "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55903, _mut55904, _mut55905, _mut55906), "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55907, _mut55908, _mut55909, _mut55910), Gamma.logGamma(AOR_divide(n, 2, "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55911, _mut55912, _mut55913, _mut55914)), "org.apache.commons.math3.distribution.TDistribution.TDistribution_113", _mut55915, _mut55916, _mut55917, _mut55918);
    }

    /**
     * Access the degrees of freedom.
     *
     * @return the degrees of freedom.
     */
    public double getDegreesOfFreedom() {
        return degreesOfFreedom;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        return FastMath.exp(logDensity(x));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double logDensity(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TDistribution.logDensity_148");
        final double n = degreesOfFreedom;
        final double nPlus1Over2 = AOR_divide((AOR_plus(n, 1, "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55919, _mut55920, _mut55921, _mut55922)), 2, "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55923, _mut55924, _mut55925, _mut55926);
        return AOR_minus(factor, AOR_multiply(nPlus1Over2, FastMath.log(AOR_plus(1, AOR_divide(AOR_multiply(x, x, "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55927, _mut55928, _mut55929, _mut55930), n, "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55931, _mut55932, _mut55933, _mut55934), "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55935, _mut55936, _mut55937, _mut55938)), "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55939, _mut55940, _mut55941, _mut55942), "org.apache.commons.math3.distribution.TDistribution.logDensity_148", _mut55943, _mut55944, _mut55945, _mut55946);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156");
        double ret;
        if (ROR_equals(x, 0, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55947, _mut55948, _mut55949, _mut55950, _mut55951)) {
            ret = 0.5;
        } else {
            double t = Beta.regularizedBeta(AOR_divide(degreesOfFreedom, (AOR_plus(degreesOfFreedom, (AOR_multiply(x, x, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55952, _mut55953, _mut55954, _mut55955)), "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55956, _mut55957, _mut55958, _mut55959)), "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55960, _mut55961, _mut55962, _mut55963), AOR_multiply(0.5, degreesOfFreedom, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55964, _mut55965, _mut55966, _mut55967), 0.5);
            if (ROR_less(x, 0.0, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55968, _mut55969, _mut55970, _mut55971, _mut55972)) {
                ret = AOR_multiply(0.5, t, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55981, _mut55982, _mut55983, _mut55984);
            } else {
                ret = AOR_minus(1.0, AOR_multiply(0.5, t, "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55973, _mut55974, _mut55975, _mut55976), "org.apache.commons.math3.distribution.TDistribution.cumulativeProbability_156", _mut55977, _mut55978, _mut55979, _mut55980);
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return solverAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     *
     * For degrees of freedom parameter {@code df}, the mean is
     * <ul>
     *  <li>if {@code df > 1} then {@code 0},</li>
     * <li>else undefined ({@code Double.NaN}).</li>
     * </ul>
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TDistribution.getNumericalMean_191");
        final double df = getDegreesOfFreedom();
        if (ROR_greater(df, 1, "org.apache.commons.math3.distribution.TDistribution.getNumericalMean_191", _mut55985, _mut55986, _mut55987, _mut55988, _mut55989)) {
            return 0;
        }
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     *
     * For degrees of freedom parameter {@code df}, the variance is
     * <ul>
     *  <li>if {@code df > 2} then {@code df / (df - 2)},</li>
     *  <li>if {@code 1 < df <= 2} then positive infinity
     *  ({@code Double.POSITIVE_INFINITY}),</li>
     *  <li>else undefined ({@code Double.NaN}).</li>
     * </ul>
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212");
        final double df = getDegreesOfFreedom();
        if (ROR_greater(df, 2, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut55990, _mut55991, _mut55992, _mut55993, _mut55994)) {
            return AOR_divide(df, (AOR_minus(df, 2, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut55995, _mut55996, _mut55997, _mut55998)), "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut55999, _mut56000, _mut56001, _mut56002);
        }
        if ((_mut56013 ? (ROR_greater(df, 1, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut56003, _mut56004, _mut56005, _mut56006, _mut56007) || ROR_less_equals(df, 2, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut56008, _mut56009, _mut56010, _mut56011, _mut56012)) : (ROR_greater(df, 1, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut56003, _mut56004, _mut56005, _mut56006, _mut56007) && ROR_less_equals(df, 2, "org.apache.commons.math3.distribution.TDistribution.getNumericalVariance_212", _mut56008, _mut56009, _mut56010, _mut56011, _mut56012)))) {
            return Double.POSITIVE_INFINITY;
        }
        return Double.NaN;
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always negative infinity no matter the
     * parameters.
     *
     * @return lower bound of the support (always
     * {@code Double.NEGATIVE_INFINITY})
     */
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity no matter the
     * parameters.
     *
     * @return upper bound of the support (always
     * {@code Double.POSITIVE_INFINITY})
     */
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * The support of this distribution is connected.
     *
     * @return {@code true}
     */
    public boolean isSupportConnected() {
        return true;
    }
}
