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
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;
import gov.nasa.jpf.annotation.Conditional;
import static br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.*;

/**
 * This class implements the Laplace distribution.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Laplace_distribution">Laplace distribution (Wikipedia)</a>
 *
 * @since 3.4
 */
public class LaplaceDistribution extends AbstractRealDistribution {

    @Conditional
    public static boolean _mut54171 = false, _mut54172 = false, _mut54173 = false, _mut54174 = false, _mut54175 = false, _mut54176 = false, _mut54177 = false, _mut54178 = false, _mut54179 = false, _mut54180 = false, _mut54181 = false, _mut54182 = false, _mut54183 = false, _mut54184 = false, _mut54185 = false, _mut54186 = false, _mut54187 = false, _mut54188 = false, _mut54189 = false, _mut54190 = false, _mut54191 = false, _mut54192 = false, _mut54193 = false, _mut54194 = false, _mut54195 = false, _mut54196 = false, _mut54197 = false, _mut54198 = false, _mut54199 = false, _mut54200 = false, _mut54201 = false, _mut54202 = false, _mut54203 = false, _mut54204 = false, _mut54205 = false, _mut54206 = false, _mut54207 = false, _mut54208 = false, _mut54209 = false, _mut54210 = false, _mut54211 = false, _mut54212 = false, _mut54213 = false, _mut54214 = false, _mut54215 = false, _mut54216 = false, _mut54217 = false, _mut54218 = false, _mut54219 = false, _mut54220 = false, _mut54221 = false, _mut54222 = false, _mut54223 = false, _mut54224 = false, _mut54225 = false, _mut54226 = false, _mut54227 = false, _mut54228 = false, _mut54229 = false, _mut54230 = false, _mut54231 = false, _mut54232 = false, _mut54233 = false, _mut54234 = false, _mut54235 = false, _mut54236 = false, _mut54237 = false, _mut54238 = false, _mut54239 = false, _mut54240 = false, _mut54241 = false, _mut54242 = false, _mut54243 = false, _mut54244 = false, _mut54245 = false, _mut54246 = false, _mut54247 = false, _mut54248 = false, _mut54249 = false, _mut54250 = false, _mut54251 = false, _mut54252 = false, _mut54253 = false, _mut54254 = false, _mut54255 = false, _mut54256 = false, _mut54257 = false, _mut54258 = false, _mut54259 = false, _mut54260 = false, _mut54261 = false, _mut54262 = false, _mut54263 = false, _mut54264 = false, _mut54265 = false, _mut54266 = false, _mut54267 = false, _mut54268 = false, _mut54269 = false, _mut54270 = false, _mut54271 = false, _mut54272 = false, _mut54273 = false, _mut54274 = false, _mut54275 = false, _mut54276 = false, _mut54277 = false, _mut54278 = false;

    /**
     * Serializable version identifier.
     */
    private static final long serialVersionUID = 20141003;

    /**
     * The location parameter.
     */
    private final double mu;

    /**
     * The scale parameter.
     */
    private final double beta;

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
     * @param mu location parameter
     * @param beta scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public LaplaceDistribution(double mu, double beta) {
        this(new Well19937c(), mu, beta);
    }

    /**
     * Build a new instance.
     *
     * @param rng Random number generator
     * @param mu location parameter
     * @param beta scale parameter (must be positive)
     * @throws NotStrictlyPositiveException if {@code beta <= 0}
     */
    public LaplaceDistribution(RandomGenerator rng, double mu, double beta) {
        super(rng);
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LaplaceDistribution.LaplaceDistribution_69");
        if (ROR_less_equals(beta, 0.0, "org.apache.commons.math3.distribution.LaplaceDistribution.LaplaceDistribution_69", _mut54171, _mut54172, _mut54173, _mut54174, _mut54175)) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, beta);
        }
        this.mu = mu;
        this.beta = beta;
    }

    /**
     * Access the location parameter, {@code mu}.
     *
     * @return the location parameter.
     */
    public double getLocation() {
        return mu;
    }

    /**
     * Access the scale parameter, {@code beta}.
     *
     * @return the scale parameter.
     */
    public double getScale() {
        return beta;
    }

    /**
     * {@inheritDoc}
     */
    public double density(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LaplaceDistribution.density_99");
        return AOR_divide(FastMath.exp(AOR_divide(-FastMath.abs(AOR_minus(x, mu, "org.apache.commons.math3.distribution.LaplaceDistribution.density_99", _mut54176, _mut54177, _mut54178, _mut54179)), beta, "org.apache.commons.math3.distribution.LaplaceDistribution.density_99", _mut54180, _mut54181, _mut54182, _mut54183)), (AOR_multiply(2.0, beta, "org.apache.commons.math3.distribution.LaplaceDistribution.density_99", _mut54184, _mut54185, _mut54186, _mut54187)), "org.apache.commons.math3.distribution.LaplaceDistribution.density_99", _mut54188, _mut54189, _mut54190, _mut54191);
    }

    /**
     * {@inheritDoc}
     */
    public double cumulativeProbability(double x) {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104");
        if (ROR_less_equals(x, mu, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54192, _mut54193, _mut54194, _mut54195, _mut54196)) {
            return AOR_divide(FastMath.exp(AOR_divide((AOR_minus(x, mu, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54213, _mut54214, _mut54215, _mut54216)), beta, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54217, _mut54218, _mut54219, _mut54220)), 2.0, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54221, _mut54222, _mut54223, _mut54224);
        } else {
            return AOR_minus(1.0, AOR_divide(FastMath.exp(AOR_divide((AOR_minus(mu, x, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54197, _mut54198, _mut54199, _mut54200)), beta, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54201, _mut54202, _mut54203, _mut54204)), 2.0, "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54205, _mut54206, _mut54207, _mut54208), "org.apache.commons.math3.distribution.LaplaceDistribution.cumulativeProbability_104", _mut54209, _mut54210, _mut54211, _mut54212);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113");
        if ((_mut54235 ? (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54225, _mut54226, _mut54227, _mut54228, _mut54229) && ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54230, _mut54231, _mut54232, _mut54233, _mut54234)) : (ROR_less(p, 0.0, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54225, _mut54226, _mut54227, _mut54228, _mut54229) || ROR_greater(p, 1.0, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54230, _mut54231, _mut54232, _mut54233, _mut54234)))) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (ROR_equals(p, 0, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54236, _mut54237, _mut54238, _mut54239, _mut54240)) {
            return Double.NEGATIVE_INFINITY;
        } else if (ROR_equals(p, 1, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54241, _mut54242, _mut54243, _mut54244, _mut54245)) {
            return Double.POSITIVE_INFINITY;
        }
        double x = (ROR_greater(p, 0.5, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54246, _mut54247, _mut54248, _mut54249, _mut54250)) ? -Math.log(AOR_minus(2.0, AOR_multiply(2.0, p, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54255, _mut54256, _mut54257, _mut54258), "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54259, _mut54260, _mut54261, _mut54262)) : Math.log(AOR_multiply(2.0, p, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54251, _mut54252, _mut54253, _mut54254));
        return AOR_plus(mu, AOR_multiply(beta, x, "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54263, _mut54264, _mut54265, _mut54266), "org.apache.commons.math3.distribution.LaplaceDistribution.inverseCumulativeProbability_113", _mut54267, _mut54268, _mut54269, _mut54270);
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalMean() {
        return mu;
    }

    /**
     * {@inheritDoc}
     */
    public double getNumericalVariance() {
        br.ufmg.labsoft.mutvariants.schematalib.SchemataLibMethods.listener.listen("org.apache.commons.math3.distribution.LaplaceDistribution.getNumericalVariance_132");
        return AOR_multiply(AOR_multiply(2.0, beta, "org.apache.commons.math3.distribution.LaplaceDistribution.getNumericalVariance_132", _mut54271, _mut54272, _mut54273, _mut54274), beta, "org.apache.commons.math3.distribution.LaplaceDistribution.getNumericalVariance_132", _mut54275, _mut54276, _mut54277, _mut54278);
    }

    /**
     * {@inheritDoc}
     */
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
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
     */
    public boolean isSupportConnected() {
        return true;
    }
}
