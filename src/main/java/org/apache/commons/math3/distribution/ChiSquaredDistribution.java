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

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * Implementation of the chi-squared distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Chi-squared_distribution">Chi-squared distribution (Wikipedia)</a>
 * @see <a href="http://mathworld.wolfram.com/Chi-SquaredDistribution.html">Chi-squared Distribution (MathWorld)</a>
 */
public class ChiSquaredDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut53386 = false, _mut53387 = false, _mut53388 = false, _mut53389 = false, _mut53390 = false, _mut53391 = false, _mut53392 = false, _mut53393 = false, _mut53394 = false, _mut53395 = false, _mut53396 = false, _mut53397 = false;

    /**
     * Default inverse cumulative probability accuracy
     * @since 2.1
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier
     */
    private static final long serialVersionUID = -8352658048349159782L;

    /**
     * Internal Gamma distribution.
     */
    private final GammaDistribution gamma;

    /**
     * Inverse cumulative probability accuracy
     */
    private final double solverAbsoluteAccuracy;

    /**
     * Create a Chi-Squared distribution with the given degrees of freedom.
     *
     * @param degreesOfFreedom Degrees of freedom.
     */
    public ChiSquaredDistribution(double degreesOfFreedom) {
        this(degreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a Chi-Squared distribution with the given degrees of freedom and
     * inverse cumulative probability accuracy.
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
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 2.1
     */
    public ChiSquaredDistribution(double degreesOfFreedom, double inverseCumAccuracy) {
        this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
    }

    /**
     * Create a Chi-Squared distribution with the given degrees of freedom.
     *
     * @param rng Random number generator.
     * @param degreesOfFreedom Degrees of freedom.
     * @since 3.3
     */
    public ChiSquaredDistribution(RandomGenerator rng, double degreesOfFreedom) {
        this(rng, degreesOfFreedom, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Create a Chi-Squared distribution with the given degrees of freedom and
     * inverse cumulative probability accuracy.
     *
     * @param rng Random number generator.
     * @param degreesOfFreedom Degrees of freedom.
     * @param inverseCumAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to
     * {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @since 3.1
     */
    public ChiSquaredDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ChiSquaredDistribution.ChiSquaredDistribution_94");
        gamma = new GammaDistribution(AOR_divide(degreesOfFreedom, 2, "org.apache.commons.math3.distribution.ChiSquaredDistribution.ChiSquaredDistribution_94", _mut53386, _mut53387, _mut53388, _mut53389), 2);
        solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    /**
     * Access the number of degrees of freedom.
     *
     * @return the degrees of freedom.
     */
    public double getDegreesOfFreedom() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ChiSquaredDistribution.getDegreesOfFreedom_108");
        return AOR_multiply(gamma.getShape(), 2.0, "org.apache.commons.math3.distribution.ChiSquaredDistribution.getDegreesOfFreedom_108", _mut53390, _mut53391, _mut53392, _mut53393);
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        return gamma.density(x);
    }

    /**
     * {@inheritDoc} *
     */
    @Override
    public double logDensity(double x) {
        return gamma.logDensity(x);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        return gamma.cumulativeProbability(x);
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
     * For {@code k} degrees of freedom, the mean is {@code k}.
     */
    public double getNumericalMean() {
        return getDegreesOfFreedom();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 2 * k}, where {@code k} is the number of degrees of freedom.
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.ChiSquaredDistribution.getNumericalVariance_148");
        return AOR_multiply(2, getDegreesOfFreedom(), "org.apache.commons.math3.distribution.ChiSquaredDistribution.getNumericalVariance_148", _mut53394, _mut53395, _mut53396, _mut53397);
    }

    /**
     * {@inheritDoc}
     *
     * The lower bound of the support is always 0 no matter the
     * degrees of freedom.
     *
     * @return zero.
     */
    public double getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * The upper bound of the support is always positive infinity no matter the
     * degrees of freedom.
     *
     * @return {@code Double.POSITIVE_INFINITY}.
     */
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSupportLowerBoundInclusive() {
        return true;
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
