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
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the Nakagami distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Nakagami_distribution">Nakagami Distribution (Wikipedia)</a>
 *
 * @since 3.4
 */
public class NakagamiDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut58023 = false, _mut58024 = false, _mut58025 = false, _mut58026 = false, _mut58027 = false, _mut58028 = false, _mut58029 = false, _mut58030 = false, _mut58031 = false, _mut58032 = false, _mut58033 = false, _mut58034 = false, _mut58035 = false, _mut58036 = false, _mut58037 = false, _mut58038 = false, _mut58039 = false, _mut58040 = false, _mut58041 = false, _mut58042 = false, _mut58043 = false, _mut58044 = false, _mut58045 = false, _mut58046 = false, _mut58047 = false, _mut58048 = false, _mut58049 = false, _mut58050 = false, _mut58051 = false, _mut58052 = false, _mut58053 = false, _mut58054 = false, _mut58055 = false, _mut58056 = false, _mut58057 = false, _mut58058 = false, _mut58059 = false, _mut58060 = false, _mut58061 = false, _mut58062 = false, _mut58063 = false, _mut58064 = false, _mut58065 = false, _mut58066 = false, _mut58067 = false, _mut58068 = false, _mut58069 = false, _mut58070 = false, _mut58071 = false, _mut58072 = false, _mut58073 = false, _mut58074 = false, _mut58075 = false, _mut58076 = false, _mut58077 = false, _mut58078 = false, _mut58079 = false, _mut58080 = false, _mut58081 = false, _mut58082 = false, _mut58083 = false, _mut58084 = false, _mut58085 = false, _mut58086 = false, _mut58087 = false, _mut58088 = false, _mut58089 = false, _mut58090 = false, _mut58091 = false, _mut58092 = false, _mut58093 = false, _mut58094 = false, _mut58095 = false, _mut58096 = false, _mut58097 = false, _mut58098 = false, _mut58099 = false, _mut58100 = false, _mut58101 = false, _mut58102 = false, _mut58103 = false, _mut58104 = false, _mut58105 = false, _mut58106 = false, _mut58107 = false, _mut58108 = false, _mut58109 = false, _mut58110 = false, _mut58111 = false, _mut58112 = false, _mut58113 = false, _mut58114 = false, _mut58115 = false, _mut58116 = false, _mut58117 = false, _mut58118 = false, _mut58119 = false, _mut58120 = false, _mut58121 = false, _mut58122 = false, _mut58123 = false, _mut58124 = false, _mut58125 = false, _mut58126 = false, _mut58127 = false, _mut58128 = false, _mut58129 = false, _mut58130 = false, _mut58131 = false, _mut58132 = false, _mut58133 = false;

    /**
     * Default inverse cumulative probability accuracy.
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20141003;

    /**
     * The shape parameter.
     */
    private final double mu;

    /**
     * The scale parameter.
     */
    private final double omega;

    /**
     * Inverse cumulative probability accuracy.
     */
    private final double inverseAbsoluteAccuracy;

    /**
     * Build a new instance.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(double mu, double omega) {
        this(mu, omega, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * Build a new instance.
     * <p>
     * <b>Note:</b> this constructor will implicitly create an instance of
     * {@link Well19937c} as random generator to be used for sampling only (see
     * {@link #sample()} and {@link #sample(int)}). In case no sampling is
     * needed for the created distribution, it is advised to pass {@code null}
     * as random generator via the appropriate constructors to avoid the
     * additional initialisation overhead.
     *
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @param inverseAbsoluteAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(double mu, double omega, double inverseAbsoluteAccuracy) {
        this(new Well19937c(), mu, omega, inverseAbsoluteAccuracy);
    }

    /**
     * Build a new instance.
     *
     * @param rng Random number generator
     * @param mu shape parameter
     * @param omega scale parameter (must be positive)
     * @param inverseAbsoluteAccuracy the maximum absolute error in inverse
     * cumulative probability estimates (defaults to {@link #DEFAULT_INVERSE_ABSOLUTE_ACCURACY}).
     * @throws NumberIsTooSmallException if {@code mu < 0.5}
     * @throws NotStrictlyPositiveException if {@code omega <= 0}
     */
    public NakagamiDistribution(RandomGenerator rng, double mu, double omega, double inverseAbsoluteAccuracy) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NakagamiDistribution.NakagamiDistribution_100");
        if (ROR_less(mu, 0.5, "org.apache.commons.math3.distribution.NakagamiDistribution.NakagamiDistribution_100", _mut58023, _mut58024, _mut58025, _mut58026, _mut58027)) {
            throw new NumberIsTooSmallException(mu, 0.5, true);
        }
        if (ROR_less_equals(omega, 0, "org.apache.commons.math3.distribution.NakagamiDistribution.NakagamiDistribution_100", _mut58028, _mut58029, _mut58030, _mut58031, _mut58032)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, omega);
        }
        this.mu = mu;
        this.omega = omega;
        this.inverseAbsoluteAccuracy = inverseAbsoluteAccuracy;
    }

    /**
     * Access the shape parameter, {@code mu}.
     *
     * @return the shape parameter.
     */
    public double getShape() {
        return mu;
    }

    /**
     * Access the scale parameter, {@code omega}.
     *
     * @return the scale parameter.
     */
    public double getScale() {
        return omega;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected double getSolverAbsoluteAccuracy() {
        return inverseAbsoluteAccuracy;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NakagamiDistribution.density_140");
        if (ROR_less_equals(x, 0, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58033, _mut58034, _mut58035, _mut58036, _mut58037)) {
            return 0.0;
        }
        return AOR_multiply(AOR_multiply(AOR_divide(AOR_multiply(2.0, FastMath.pow(mu, mu), "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58038, _mut58039, _mut58040, _mut58041), (AOR_multiply(Gamma.gamma(mu), FastMath.pow(omega, mu), "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58042, _mut58043, _mut58044, _mut58045)), "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58046, _mut58047, _mut58048, _mut58049), FastMath.pow(x, AOR_minus(AOR_multiply(2, mu, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58050, _mut58051, _mut58052, _mut58053), 1, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58054, _mut58055, _mut58056, _mut58057)), "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58058, _mut58059, _mut58060, _mut58061), FastMath.exp(AOR_divide(AOR_multiply(AOR_multiply(-mu, x, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58062, _mut58063, _mut58064, _mut58065), x, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58066, _mut58067, _mut58068, _mut58069), omega, "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58070, _mut58071, _mut58072, _mut58073)), "org.apache.commons.math3.distribution.NakagamiDistribution.density_140", _mut58074, _mut58075, _mut58076, _mut58077);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NakagamiDistribution.cumulativeProbability_149");
        return Gamma.regularizedGammaP(mu, AOR_divide(AOR_multiply(AOR_multiply(mu, x, "org.apache.commons.math3.distribution.NakagamiDistribution.cumulativeProbability_149", _mut58078, _mut58079, _mut58080, _mut58081), x, "org.apache.commons.math3.distribution.NakagamiDistribution.cumulativeProbability_149", _mut58082, _mut58083, _mut58084, _mut58085), omega, "org.apache.commons.math3.distribution.NakagamiDistribution.cumulativeProbability_149", _mut58086, _mut58087, _mut58088, _mut58089));
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalMean_154");
        return AOR_multiply(AOR_divide(Gamma.gamma(AOR_plus(mu, 0.5, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalMean_154", _mut58090, _mut58091, _mut58092, _mut58093)), Gamma.gamma(mu), "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalMean_154", _mut58094, _mut58095, _mut58096, _mut58097), FastMath.sqrt(AOR_divide(omega, mu, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalMean_154", _mut58098, _mut58099, _mut58100, _mut58101)), "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalMean_154", _mut58102, _mut58103, _mut58104, _mut58105);
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159");
        double v = AOR_divide(Gamma.gamma(AOR_plus(mu, 0.5, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58106, _mut58107, _mut58108, _mut58109)), Gamma.gamma(mu), "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58110, _mut58111, _mut58112, _mut58113);
        return AOR_multiply(omega, (AOR_minus(1, AOR_multiply(AOR_multiply(AOR_divide(1, mu, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58114, _mut58115, _mut58116, _mut58117), v, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58118, _mut58119, _mut58120, _mut58121), v, "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58122, _mut58123, _mut58124, _mut58125), "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58126, _mut58127, _mut58128, _mut58129)), "org.apache.commons.math3.distribution.NakagamiDistribution.getNumericalVariance_159", _mut58130, _mut58131, _mut58132, _mut58133);
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        return 0;
    }

    /**
     * {@inheritDoc}
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
     */
    public boolean isSupportConnected() {
        return true;
    }
}
